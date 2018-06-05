package com.att.eg.profile.mysubscriptions.info.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;

import com.att.eg.profile.mysubscriptions.info.MockProvider;
import com.att.eg.profile.mysubscriptions.info.MockResponses;
import com.att.eg.profile.mysubscriptions.info.model.ActiveSubscriptionsResponse;
import com.att.eg.profile.mysubscriptions.info.model.AddOn;
import com.att.eg.profile.mysubscriptions.info.model.AddOnsInfo;
import com.att.eg.profile.mysubscriptions.info.model.BasePackageInfo;
import com.att.eg.profile.mysubscriptions.info.model.CDvrInfo;
import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsCarouselResponse;
import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsInfoResponse;
import com.att.eg.profile.mysubscriptions.info.model.ProductsResponse;
import com.att.eg.profile.mysubscriptions.info.model.Resource;
import com.att.eg.profile.mysubscriptions.info.model.SessionResponse;
import com.att.eg.profile.mysubscriptions.info.model.UxReference;
import com.att.eg.profile.mysubscriptions.info.service.QPUMSClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.ajsc.common.RequestScopeModifier;
import com.att.eg.profile.mysubscriptions.info.Application;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, RequestScopeModifier.class }, webEnvironment=WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {
        MockProvider.class
})
@ActiveProfiles("test")
@DirtiesContext
public class MySubscriptionsCarouselResponseBuilderTest {

    private final static String BASE_PACKAGE = "Gotta Have It";
    private final static String DEFAULT_DVR_HOURS = "10 Hours";
    private final static String[] ADDONS = new String[] {"HBO", "STARZ", "CINEMAX"};
    private final static String ADDON_COUNT = ADDONS.length + " PACKAGES";

    private UxReference uxReference;
    private MySubscriptionsInfoResponse mySubscriptionsInfoResponse;
    private MySubscriptionsCarouselResponse mySubscriptionsCarouselResponse;

    @Autowired
    private MySubscriptionsInfoResponseBuilder infoResponseBuilder;

    @Autowired
    private QPUMSClient qpumsClient;

    private MockResponses mockResponses = new MockResponses();

    private MySubscriptionsCarouselResponseBuilder carouselResponseBuilder;
    private String profileId;
    private ResponseBuilderUtil responseBuilderUtil;
    private SessionResponse sessionResponse;
    private ProductsResponse productsResponse;
    private ActiveSubscriptionsResponse activeSubscriptionsResponse;


    @Before
    public void setUp() throws Exception {
        profileId = "554215007_TMBR";
        uxReference = null;
        mySubscriptionsInfoResponse = null;
        mySubscriptionsCarouselResponse = null;
        sessionResponse = null;
        productsResponse = null;
        activeSubscriptionsResponse = null;
        responseBuilderUtil = new ResponseBuilderUtil(qpumsClient);
        carouselResponseBuilder = new MySubscriptionsCarouselResponseBuilderImpl(infoResponseBuilder, new CarouselItemConverter(new ColorCodeBuilderImpl(), responseBuilderUtil), responseBuilderUtil);
    }

    private MySubscriptionsInfoResponse generateMockResponse() {
        BasePackageInfo basePackageInfo = new BasePackageInfo();
        basePackageInfo.setDisplayName(BASE_PACKAGE);
        AddOnsInfo addOnsInfo = new AddOnsInfo();
        addOnsInfo.setAddOnsCount(ADDON_COUNT);
        AddOn[] addOns = new AddOn[ADDONS.length];
        for(int i = 0; i < ADDONS.length; i++) {
            addOns[i] = new AddOn();
            addOns[i].setDisplayName(ADDONS[i]);
        }
        addOnsInfo.setAddOns(addOns);
        CDvrInfo cDvrInfo = new CDvrInfo();
        cDvrInfo.setAvailableHours(DEFAULT_DVR_HOURS);
        return new MySubscriptionsInfoResponse(basePackageInfo, addOnsInfo, cDvrInfo, null);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void carouselOnlyReturnsOnePackageType() {
        givenResponseForProfileId(generateMockResponse());
        for(UxReference uxReference : UxReference.values()) {
            givenCarouselTypeIs(uxReference);
            whenResponseIsBuilt();
            String packageType = responseBuilderUtil.getPackageTypeByReference(uxReference);
            thenPackageTypeShouldBe(packageType);
        }
    }

    @Test
    public void packageCarouselReturnsNoMoreThanOneCurrent() {
        givenResponseForProfileId(generateMockResponse());
        givenCarouselTypeIs(UxReference.MYSUBSCRIPTIONS_CHANNELPACKAGESREFERENCE);
        whenResponseIsBuilt();
        thenThereShouldBeNoMoreCurrentThan(1);
    }

    @Test
    public void cdvrCarouselReturnsNoMoreThanOneCurrent() {
        givenResponseForProfileId(generateMockResponse());
        givenCarouselTypeIs(UxReference.MYSUBSCRIPTIONS_TRUECLOUDSTORAGEREFERENCE);
        whenResponseIsBuilt();
        thenThereShouldBeNoMoreCurrentThan(1);
    }

    @Test
    public void packageCarouselReturnsDataCorrectly() {
        givenResponseForProfileId(generateMockResponse());
        givenCarouselTypeIs(UxReference.MYSUBSCRIPTIONS_CHANNELPACKAGESREFERENCE);
        whenResponseIsBuilt();
        thenResourcesSizeIs(1);
        thenResourceParameterEquals(0, "displayName", BASE_PACKAGE);
    }

    @Test
    public void addonsCarouselReturnsDataCorrectly() {
        givenResponseForProfileId(generateMockResponse());
        givenCarouselTypeIs(UxReference.MYSUBSCRIPTIONS_ADDONSREFERENCE);
        whenResponseIsBuilt();
        thenResourcesSizeIs(ADDONS.length);
        for(int i = 0; i < ADDONS.length; i++) {
            thenResourceParameterEquals(i, "displayName", ADDONS[i]);
        }
    }

    @Test
    public void cdvrCarouselReturnsDataCorrectly() {
        givenResponseForProfileId(generateMockResponse());
        givenCarouselTypeIs(UxReference.MYSUBSCRIPTIONS_TRUECLOUDSTORAGEREFERENCE);
        whenResponseIsBuilt();
        thenResourcesSizeIs(1);
        thenResourceParameterEquals(0, "availableHours", DEFAULT_DVR_HOURS);
    }

    @Test
    public void packageCarouselReturnsDataCorrectlyWithSecureToken() {
        givenResponseForProfileId(mockResponses.getMockSessionResponse(MockResponses.SAMPLE_SESSION_RESPONSE),
                mockResponses.getMockProductsResponse(MockResponses.SAMPLE_PRODUCTS_RESPONSE),
                mockResponses.getMockActiveSubscriptionsResponse(MockResponses.SAMPLE_ACTIVE_SUBSCRIPTIONS_RESPONSE));
        givenCarouselTypeIs(UxReference.MYSUBSCRIPTIONS_CHANNELPACKAGESREFERENCE);
        whenResponseIsBuiltUsingSecureToken();
        thenResourcesSizeIs(4);
        thenResourceParameterEquals(0, "displayName", "Live a Little");
        thenResourceParameterEquals(0, "isCurrent", false);
        thenResourceParameterEquals(0, "priceUsd", "35.00");
        thenResourceParameterEquals(0, "channelCount", "60+ Live Channels");

        thenResourceParameterEquals(1, "displayName", "Just Right");
        thenResourceParameterEquals(1, "isCurrent", false);
        thenResourceParameterEquals(1, "priceUsd", "50.00");
        thenResourceParameterEquals(1, "channelCount", "80+ Live Channels");

        thenResourceParameterEquals(2, "displayName", "Go Big");
        thenResourceParameterEquals(2, "isCurrent", true);
        thenResourceParameterEquals(2, "priceUsd", "60.00");
        thenResourceParameterEquals(2, "channelCount", "100+ Live Channels");

        thenResourceParameterEquals(3, "displayName", "Gotta Have It");
        thenResourceParameterEquals(3, "isCurrent", false);
        thenResourceParameterEquals(3, "priceUsd", "70.00");
        thenResourceParameterEquals(3, "channelCount", "120+ Live Channels");
    }

    @Test
    public void packageCarouselReturnsOnlyOneCurrentPackage() {
        givenResponseForProfileId(mockResponses.getMockSessionResponse(MockResponses.SAMPLE_SESSION_RESPONSE),
                mockResponses.getMockProductsResponse(MockResponses.SAMPLE_PRODUCTS_RESPONSE),
                mockResponses.getMockActiveSubscriptionsResponse(MockResponses.SAMPLE_FINAL_BILL_ACTIVE_SUBSCRIPTIONS_RESPONSE));
        givenCarouselTypeIs(UxReference.MYSUBSCRIPTIONS_CHANNELPACKAGESREFERENCE);
        whenResponseIsBuiltUsingSecureToken();
        thenResourcesSizeIs(4);
        thenResourceParameterEquals(0, "displayName", "Live a Little");
        thenResourceParameterEquals(0, "isCurrent", false);
        thenResourceParameterEquals(0, "priceUsd", "35.00");
        thenResourceParameterEquals(0, "channelCount", "60+ Live Channels");

        thenResourceParameterEquals(1, "displayName", "Just Right");
        thenResourceParameterEquals(1, "isCurrent", true);
        thenResourceParameterEquals(1, "priceUsd", "50.00");
        thenResourceParameterEquals(1, "channelCount", "80+ Live Channels");

        thenResourceParameterEquals(2, "displayName", "Go Big");
        thenResourceParameterEquals(2, "isCurrent", false);
        thenResourceParameterEquals(2, "priceUsd", "60.00");
        thenResourceParameterEquals(2, "channelCount", "100+ Live Channels");

        thenResourceParameterEquals(3, "displayName", "Gotta Have It");
        thenResourceParameterEquals(3, "isCurrent", false);
        thenResourceParameterEquals(3, "priceUsd", "70.00");
        thenResourceParameterEquals(3, "channelCount", "120+ Live Channels");
    }

    @Test
    public void addonsCarouselReturnsDataCorrectlyWithSecureToken() {
        givenResponseForProfileId(mockResponses.getMockSessionResponse(MockResponses.SAMPLE_SESSION_RESPONSE),
                mockResponses.getMockProductsResponse(MockResponses.SAMPLE_PRODUCTS_RESPONSE),
                mockResponses.getMockActiveSubscriptionsResponse(MockResponses.SAMPLE_ACTIVE_SUBSCRIPTIONS_RESPONSE));
        givenCarouselTypeIs(UxReference.MYSUBSCRIPTIONS_ADDONSREFERENCE);
        whenResponseIsBuiltUsingSecureToken();
        //only active subscriptions counts for add-on
        thenResourcesSizeIs(1);
        thenResourceParameterEquals(0, "displayName", "HBO");
        thenResourceParameterEquals(0, "isCurrent", true);
        thenResourceParameterEquals(0, "priceUsd", "5.00");
    }

    @Test
    public void cdvrCarouselReturnsDataCorrectlyWithSecureToken() {
        givenResponseForProfileId(mockResponses.getMockSessionResponse(MockResponses.SAMPLE_SESSION_RESPONSE),
                                    mockResponses.getMockProductsResponse(MockResponses.SAMPLE_PRODUCTS_RESPONSE),
                                    mockResponses.getMockActiveSubscriptionsResponse(MockResponses.SAMPLE_ACTIVE_SUBSCRIPTIONS_RESPONSE));
        givenCarouselTypeIs(UxReference.MYSUBSCRIPTIONS_TRUECLOUDSTORAGEREFERENCE);
        whenResponseIsBuiltUsingSecureToken();
        thenResourcesSizeIs(1);
        thenResourceParameterEquals(0, "availableHours", "20 Hours");
        thenResourceParameterEquals(0, "isCurrent", true);
        thenResourceParameterEquals(0, "priceUsd", "0.00");
    }


    private void thenResourceParameterEquals(int index, String key, Object value) {
        assertNotNull(mySubscriptionsCarouselResponse);
        List<Resource> resources = mySubscriptionsCarouselResponse.getResources();
        assertNotNull(resources);
        assertEquals(true, resources.size() > index);
        Resource resource = resources.get(index);
        Object resourceValue = resource.getAdditionalProperties().get(key);
        assertEquals(value, resourceValue);
    }

    private void thenResourcesSizeIs(int size) {
        assertNotNull(mySubscriptionsCarouselResponse);
        List<Resource> resources = mySubscriptionsCarouselResponse.getResources();
        assertNotNull(resources);
        assertEquals(size, resources.size());
    }

    private void thenThereShouldBeNoMoreCurrentThan(int maxCount) {
        assertNotNull(mySubscriptionsCarouselResponse);
        List<Resource> resources = mySubscriptionsCarouselResponse.getResources();
        assertNotNull(resources);
        int count = 0;
        for(Resource resource : resources) {
            Boolean isCurrent = (Boolean) resource.getAdditionalProperties().get("isCurrent");
            if(isCurrent != null && isCurrent.booleanValue()) {
                count++;
            }
        }
        assertEquals(true, count <= maxCount);
    }

    private void givenCarouselTypeIs(UxReference uxReference) {
        this.uxReference = uxReference;
    }

    private void givenResponseForProfileId(MySubscriptionsInfoResponse mySubscriptionsInfoResponse) {
        this.mySubscriptionsInfoResponse = mySubscriptionsInfoResponse;
    }

    private void givenResponseForProfileId(SessionResponse sessionResponse, ProductsResponse productsResponse, ActiveSubscriptionsResponse activeSubscriptionsResponse) {
        this.sessionResponse = sessionResponse;
        this.productsResponse = productsResponse;
        this.activeSubscriptionsResponse = activeSubscriptionsResponse;
    }

    private void whenResponseIsBuilt() {
        doReturn(mySubscriptionsInfoResponse).when(infoResponseBuilder).buildResponse(eq(profileId), any());
        mySubscriptionsCarouselResponse = carouselResponseBuilder.buildResponse(profileId, null, uxReference);
    }

    private void whenResponseIsBuiltUsingSecureToken() {
        doReturn(sessionResponse).when(qpumsClient).getSession(any());
        doReturn(productsResponse).when(qpumsClient).getProducts();
        doReturn(activeSubscriptionsResponse).when(qpumsClient).getActiveSubscriptions(any());
        mySubscriptionsCarouselResponse = carouselResponseBuilder.buildResponse(profileId, QPUMSClient.ATS_TOKEN, uxReference);
    }

    private void thenPackageTypeShouldBe(String packageType) {
        assertNotNull(mySubscriptionsCarouselResponse);
        List<Resource> resources = mySubscriptionsCarouselResponse.getResources();
        assertNotNull(resources);
        assertEquals(true, resources.size() > 0);
        for(Resource resource : resources) {
            assertEquals(CarouselItemConverter.RESOURCE_TYPE_PACKAGE, resource.getResourceType());
            String resourcePackageType = (String) resource.getAdditionalProperties().get("packageType");
            assertEquals(packageType, resourcePackageType);
        }
    }

}
