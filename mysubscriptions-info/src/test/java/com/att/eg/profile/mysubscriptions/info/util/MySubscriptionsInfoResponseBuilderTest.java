package com.att.eg.profile.mysubscriptions.info.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Matchers.any;

import com.att.eg.profile.mysubscriptions.info.MockProvider;
import com.att.eg.profile.mysubscriptions.info.MockResponses;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.ajsc.common.RequestScopeModifier;
import com.att.ajsc.common.couchbase.repository.impl.CouchBaseDAO;
import com.att.eg.profile.mysubscriptions.info.Application;
import com.att.eg.profile.mysubscriptions.info.model.ActiveSubscriptionsResponse;
import com.att.eg.profile.mysubscriptions.info.model.EProfile;
import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsInfoResponse;
import com.att.eg.profile.mysubscriptions.info.model.SessionResponse;
import com.att.eg.profile.mysubscriptions.info.model.ProductsResponse;
import com.att.eg.profile.mysubscriptions.info.service.QPUMSClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, RequestScopeModifier.class }, webEnvironment=WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {
		MockProvider.class
})
@ActiveProfiles("test")
@DirtiesContext
public class MySubscriptionsInfoResponseBuilderTest {
	private final static String PROFILE_ID = "299713807_TMBR";

	private final static String WRONG_PROFILE_ID = "WRONG PROFILE ID";

	private final static int NO_CDVR_INFO = -1;

	private final static String[] NO_ADDONS = new String[] {};

	private final static String[] BASE_PACKAGE_ARRAY = {
			"Gotta Have It",
			"Go Big",
			"Live A Little",
			"Abra Cadabra"
	};

	private final static  String[][] ADDONS_ARRAY = new String[][] {
			new String[] {"HBO"},
			new String[] {"HBO", "Cinemax"},
			new String[] {"Cinemax", "HBO"},
			new String[] {"HBO", "Cinemax", "Showtime"},
			new String[] {"Showtime", "Cinemax", "HBO"},
			new String[] {"Cinemax", "HBO", "Showtime"},
	};

	private final static int[] CDVR_HOURS_ARRAY = new int[] {
			100,
			200,
			300,
			1000,
			10000,
			110,
			150,
			199,
			999
	};

	private final static int DEFAULT_CDVR_HOURS = 10;
	
	MySubscriptionsInfoResponseBuilder responseBuilder;
	
	@Autowired
    @Mock
    CouchBaseDAO<EProfile> mockCouchBaseDao;

	@Autowired
	@Mock
	QPUMSClient qpumsClient;

	@Autowired
	@Mock
	ColorCodeBuilder colorCodeBuilder;


	private EProfile mockEProfile;
	private MockResponses mockResponses = new MockResponses();

	private MySubscriptionsInfoResponse response;

	private String profileId;
	private SessionResponse sessionResponse;
	private ProductsResponse productsResponse;
	private ActiveSubscriptionsResponse activeSubscriptionsResponse;


	@Before
	public void setUp() throws Exception {
		mockEProfile = new EProfile();
		ResponseBuilderUtil responseBuilderUtil = new ResponseBuilderUtil(qpumsClient);
		responseBuilder = new MySubscriptionsInfoResponseBuilderImpl(mockCouchBaseDao, colorCodeBuilder, qpumsClient, responseBuilderUtil);
		sessionResponse = null;
		productsResponse = null;
		activeSubscriptionsResponse = null;
	}


	//test full combination
	@Test
	public void builderReturnsCorrectInfoWhenPackageCodeHasAddOnsAndCdvrHours() throws Exception {
		givenProfileIdIs(PROFILE_ID);
		for(String basePackage : BASE_PACKAGE_ARRAY) {
			for(String[] addOns : ADDONS_ARRAY) {
				for(int cdvrHours : CDVR_HOURS_ARRAY) {
					String packageCode = generatePackageCode(basePackage, addOns, cdvrHours);
					givenPackageCodeIs(packageCode);
					whenResponseIsBuiltUsingProfileId();
					thenDisplayNameIs(basePackage);
					thenAddOnsCountIs(addOns.length);
					thenCDvrHoursIs(cdvrHours);
				}
			}
		}
	}

	//test combination package + addons
	@Test
	public void builderReturnsCorrectInfoWhenPackageCodeHasAddOnsButNoCdvrHours() throws Exception {
		givenProfileIdIs(PROFILE_ID);
		for(String basePackage : BASE_PACKAGE_ARRAY) {
			for (String[] addOns : ADDONS_ARRAY) {
				String packageCode = generatePackageCode(basePackage, addOns, NO_CDVR_INFO);
				givenPackageCodeIs(packageCode);
				whenResponseIsBuiltUsingProfileId();
				thenDisplayNameIs(basePackage);
				thenAddOnsCountIs(addOns.length);
				thenCDvrHoursIs(DEFAULT_CDVR_HOURS);
			}
		}
	}

	//test combination package + cdvr
	@Test
	public void builderReturnsCorrectInfoWhenPackageCodeHasNoAddOnsButHasCdvrHours() throws Exception {
		givenProfileIdIs(PROFILE_ID);
		for(String basePackage : BASE_PACKAGE_ARRAY) {
			for(int cdvrHours : CDVR_HOURS_ARRAY) {
				String packageCode = generatePackageCode(basePackage, NO_ADDONS, cdvrHours);
				givenPackageCodeIs(packageCode);
				whenResponseIsBuiltUsingProfileId();
				thenDisplayNameIs(basePackage);
				thenAddOnsCountIs(NO_ADDONS.length);
				thenCDvrHoursIs(cdvrHours);
			}
		}
	}

	//test base packages without additional subscriptions
	@Test
	public void builderReturnsCorrectInfoWhenPackageCodeHasNoAddOnsOrCdvrHours() throws Exception {
		givenProfileIdIs(PROFILE_ID);
		for(String basePackage : BASE_PACKAGE_ARRAY) {
			givenPackageCodeIs(generatePackageCode(basePackage, NO_ADDONS, NO_CDVR_INFO));
			whenResponseIsBuiltUsingProfileId();
			thenDisplayNameIs(basePackage);
			thenAddOnsCountIs(NO_ADDONS.length);
			thenCDvrHoursIs(DEFAULT_CDVR_HOURS);
		}
	}

	//test null profile id
	@Test
	public void builderReturnsNullWhenNoProfileIdIsProvided() throws Exception {
		givenProfileIdIs(null);
		whenResponseIsBuiltUsingProfileId();
		thenResponseIs(null);
	}

	//test non-existing profile id
	@Test
	public void builderReturnsNullWhenProfileIdDoesntExist() throws Exception {
		givenProfileIdIs(WRONG_PROFILE_ID);
		whenResponseIsBuiltUsingProfileId();
		thenResponseIs(null);
	}

	@Test
	public void builderReturnsCorrectInfoWhenResponseIsGeneratedFromSecureToken() throws Exception {
		givenProfileIdIs(PROFILE_ID);
		givenSessionResponseIs(MockResponses.SAMPLE_SESSION_RESPONSE);
		givenProductsResponseIs(MockResponses.SAMPLE_PRODUCTS_RESPONSE);
		givenActiveSubscriptionsResponseIs(MockResponses.SAMPLE_ACTIVE_SUBSCRIPTIONS_RESPONSE);
		whenResponseIsBuiltUsingSecureToken();
		thenDisplayNameIs("Go Big");
		thenChannelCountIs("100+ Live Channels");
	}

	@Test
	public void builderReturnsActiveSubscription() {
		givenProfileIdIs(PROFILE_ID);
		givenSessionResponseIs(MockResponses.SAMPLE_SESSION_RESPONSE);
		givenProductsResponseIs(MockResponses.SAMPLE_PRODUCTS_RESPONSE);
		givenActiveSubscriptionsResponseIs(MockResponses.SAMPLE_FINAL_BILL_ACTIVE_SUBSCRIPTIONS_RESPONSE);
		whenResponseIsBuiltUsingSecureToken();
		thenDisplayNameIs("Just Right");
		thenChannelCountIs("80+ Live Channels");
	}

	private void givenActiveSubscriptionsResponseIs(String sampleName) {
		activeSubscriptionsResponse = mockResponses.getMockActiveSubscriptionsResponse(sampleName);
	}

	private void givenProductsResponseIs(String sampleName) {
		productsResponse = mockResponses.getMockProductsResponse(sampleName);
	}

	private void givenSessionResponseIs(String sampleName) {
		sessionResponse = mockResponses.getMockSessionResponse(sampleName);
	}

	private void thenChannelCountIs(String count) {
		assertEquals(count, response.getBasePackageInfo().getChannelCount());
	}

	private String generatePackageCode(String basePackage, String[] addOns, int cdrvHours) {
		String code = basePackage;
		if(addOns != null) {
			for(String addOn : addOns) {
				code += " + " + addOn;
			}
		}
		if(cdrvHours >= 0) {
			code += " + DVR " + cdrvHours + " HOURS";
		}
		return code;
	}

	private void givenProfileIdIs(String profileId) {
		this.profileId = profileId;
		if(PROFILE_ID.equals(profileId)) {
			doReturn(mockEProfile).when(mockCouchBaseDao).findById(profileId);
		}
	}

	private void givenPackageCodeIs(String packageCode) {
		System.out.println("givenPackageCodeIs: " + packageCode);
		mockEProfile.setPackageCode(packageCode);
	}

	private void whenResponseIsBuiltUsingProfileId() {
		response = responseBuilder.buildResponse(profileId, null);
	}

	private void whenResponseIsBuiltUsingSecureToken() {
		doReturn(sessionResponse).when(qpumsClient).getSession(any());
		doReturn(productsResponse).when(qpumsClient).getProducts();
		doReturn(activeSubscriptionsResponse).when(qpumsClient).getActiveSubscriptions(any());
		response = responseBuilder.buildResponse(profileId, QPUMSClient.ATS_TOKEN);
	}

	private void thenDisplayNameIs(String displayName) {
		assertEquals(displayName, response.getBasePackageInfo().getDisplayName());
	}

	private void thenAddOnsCountIs(int addOnsCount) {
		assertEquals(addOnsCount + " Packages", response.getAddOnsInfo().getAddOnsCount());
		assertEquals(addOnsCount,response.getAddOnsInfo().getAddOns().length);
	}

	private void thenCDvrHoursIs(int cdvrHours) {
		assertEquals(cdvrHours + " Hours", response.getCDvrInfo().getAvailableHours());
	}

	private void thenResponseIs(MySubscriptionsInfoResponse response) {
		assertEquals(response, this.response);
	}

}
