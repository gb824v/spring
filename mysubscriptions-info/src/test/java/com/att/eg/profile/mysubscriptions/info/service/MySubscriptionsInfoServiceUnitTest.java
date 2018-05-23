package com.att.eg.profile.mysubscriptions.info.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Response;
import com.att.ajsc.common.RequestScopeModifier;
import com.att.eg.profile.mysubscriptions.info.Application;
import com.att.eg.profile.mysubscriptions.info.model.AddOnsInfo;
import com.att.eg.profile.mysubscriptions.info.model.BasePackageInfo;
import com.att.eg.profile.mysubscriptions.info.model.CDvrInfo;
import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsInfoResponse;
import com.att.eg.profile.mysubscriptions.info.util.MySubscriptionsInfoResponseBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, RequestScopeModifier.class }, webEnvironment=WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class MySubscriptionsInfoServiceUnitTest {

	private final static String PROFILE_ID = "123456_TMBR";
	private final static String BASE_PACKAGE = "Gotta Have It";
	private final static String DEFAULT_DVR_HOURS = "10 HOURS";
	private final static String ADDON_COUNT = "2 PACKAGES";
	private final static int SUCCESS_STATUS = Response.Status.OK.getStatusCode();
	private final static int NO_CONTENT_STATUS = Response.Status.NO_CONTENT.getStatusCode();
	private final static int INVALID_PARAM_STATUS = Response.Status.BAD_REQUEST.getStatusCode();
	
	@InjectMocks()
	@Autowired
	MySubscriptionsService service;
	
	@Autowired
    @Mock
    MySubscriptionsInfoResponseBuilder mockResponseBuilder;

	private String profileId;

	private Response response;

	@Before
	public void setUp() throws Exception {
		profileId = "";
	}

	private MySubscriptionsInfoResponse generateMockResponse() {
		BasePackageInfo basePackageInfo = new BasePackageInfo();
		basePackageInfo.setDisplayName(BASE_PACKAGE);
		AddOnsInfo addOns = new AddOnsInfo();
		addOns.setAddOnsCount(ADDON_COUNT);
		CDvrInfo cDvrInfo = new CDvrInfo();
		cDvrInfo.setAvailableHours(DEFAULT_DVR_HOURS);
		return new MySubscriptionsInfoResponse(basePackageInfo, addOns, cDvrInfo, null);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void serviceReturnsSuccessStatusWhenResponseIsBuiltSuccessfully() throws Exception {
		givenProfileIdIs(PROFILE_ID);
		givenResponseForProfileId(generateMockResponse());
		whenRequestIsSent();
		thenStatusCodeIs(SUCCESS_STATUS);
	}
	
	@Test
	public void serviceReturnsNoContentStatusWhenResponseIsNotBuiltSuccessfully() throws Exception {
		givenProfileIdIs(PROFILE_ID);
		givenResponseForProfileId(null);
		whenRequestIsSent();
		thenStatusCodeIs(NO_CONTENT_STATUS);
	}


	@Test
	public void serviceReturnsInvalidParamsStatusWhenEmptyProfileIdIsPassed() throws Exception {
		givenProfileIdIs("");
		whenRequestIsSent();
		thenStatusCodeIs(INVALID_PARAM_STATUS);
	}

	@Test
	public void serviceReturnsInvalidParamsStatusWhenNoProfileIdIsPassed() throws Exception {
		givenProfileIdIs(null);
		whenRequestIsSent();
		thenStatusCodeIs(INVALID_PARAM_STATUS);
	}

	private void givenProfileIdIs(String id) {
		profileId = id;
	}

	private void givenResponseForProfileId(MySubscriptionsInfoResponse mySubscriptionsInfoResponse) {
		doReturn(mySubscriptionsInfoResponse).when(mockResponseBuilder).buildResponse(profileId, QPUMSClient.ATS_TOKEN);
	}

	private void whenRequestIsSent() {
		response = service.getInfo(profileId, QPUMSClient.ATS_TOKEN);
	}

	private void thenStatusCodeIs(int code) {
		assertNotNull(response);
		assertEquals(code, response.getStatus());
	}

}
