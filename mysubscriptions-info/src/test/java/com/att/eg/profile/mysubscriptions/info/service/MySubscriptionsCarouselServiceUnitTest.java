package com.att.eg.profile.mysubscriptions.info.service;

import com.att.ajsc.common.RequestScopeModifier;
import com.att.eg.profile.mysubscriptions.info.Application;
import com.att.eg.profile.mysubscriptions.info.model.AddOn;
import com.att.eg.profile.mysubscriptions.info.model.AddOnsInfo;
import com.att.eg.profile.mysubscriptions.info.model.BasePackageInfo;
import com.att.eg.profile.mysubscriptions.info.model.CDvrInfo;
import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsCarouselResponse;
import com.att.eg.profile.mysubscriptions.info.model.MySubscriptionsInfoResponse;
import com.att.eg.profile.mysubscriptions.info.model.UxReference;
import com.att.eg.profile.mysubscriptions.info.util.CarouselItemConverter;
import com.att.eg.profile.mysubscriptions.info.util.MySubscriptionsCarouselResponseBuilder;
import com.att.eg.profile.mysubscriptions.info.util.MySubscriptionsCarouselResponseBuilderImpl;
import com.att.eg.profile.mysubscriptions.info.util.MySubscriptionsInfoResponseBuilder;
import com.att.eg.profile.mysubscriptions.info.util.ResponseBuilderUtil;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, RequestScopeModifier.class }, webEnvironment=WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class MySubscriptionsCarouselServiceUnitTest {

    private final static String PROFILE_ID = "123456_TMBR";
    private final static String BASE_PACKAGE = "Gotta Have It";
    private final static String DEFAULT_DVR_HOURS = "10 HOURS";
    private final static String[] ADDONS = new String[] {"HBO", "STARZ", "CINEMAX"};
    private final static String ADDON_COUNT = ADDONS.length + " PACKAGES";
    private final static int SUCCESS_STATUS = Response.Status.OK.getStatusCode();
    private final static int NO_CONTENT_STATUS = Response.Status.NO_CONTENT.getStatusCode();
    private final static int INVALID_PARAM_STATUS = Response.Status.BAD_REQUEST.getStatusCode();
    private final static String[] CAROUSEL_TYPES = new String[] {"MySubscriptions_ChannelPackagesReference",
                                                                "MySubscriptions_AddOnsReference",
                                                                 "MySubscriptions_TrueCloudStorageReference"};

    @InjectMocks()
    @Autowired
    MySubscriptionsService service;

    @Autowired
    @Mock
    MySubscriptionsInfoResponseBuilder mockInfoResponseBuilder;

    @Autowired
    @Mock
    MySubscriptionsCarouselResponseBuilder mockCarouselResponseBuilder;

    @Autowired
    @Mock
    private ResponseBuilderUtil responseBuilderUtil;

    @Autowired
    @Mock
    CarouselItemConverter carouselItemConverter;


    private String profileId;
    private String carouselType;

    private Response response;


    @Before
    public void setUp() throws Exception {
        profileId = "";
        carouselType = "";
        response = null;
    }

    private MySubscriptionsInfoResponse generateMockInfoResponse() {
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
    public void serviceReturnsSuccessStatusWhenResponseIsBuiltSuccessfully() {
        givenProfileIdIs(PROFILE_ID);
        for(String carouselType : CAROUSEL_TYPES) {
            givenCarouselTypeIs(carouselType);
            givenResponseForProfileId(generateMockInfoResponse());
            whenRequestIsSent();
            thenStatusCodeIs(SUCCESS_STATUS);
        }
    }

    @Test
    public void serviceReturnsNoContentStatusWhenResponseIsNotBuiltSuccessfully() {
        givenProfileIdIs(PROFILE_ID);
        for(String carouselType : CAROUSEL_TYPES) {
            givenCarouselTypeIs(carouselType);
            givenResponseForProfileId(null);
            whenRequestIsSent();
            thenStatusCodeIs(NO_CONTENT_STATUS);
        }
    }


    @Test
    public void serviceReturnsInvalidParamsStatusWhenEmptyProfileIdIsPassed() {
        givenProfileIdIs("");
        for(String carouselType : CAROUSEL_TYPES) {
            givenCarouselTypeIs(carouselType);
            whenRequestIsSent();
            thenStatusCodeIs(INVALID_PARAM_STATUS);
        }
    }

    @Test
    public void serviceReturnsInvalidParamsStatusWhenNoProfileIdIsPassed() {
        givenProfileIdIs(null);
        for(String carouselType : CAROUSEL_TYPES) {
            givenCarouselTypeIs(carouselType);
            whenRequestIsSent();
            thenStatusCodeIs(INVALID_PARAM_STATUS);
        }
    }

    private void givenProfileIdIs(String id) {
        profileId = id;
    }

    private void givenCarouselTypeIs(String type) {
        carouselType = type;
    }

    private void givenResponseForProfileId(MySubscriptionsInfoResponse mySubscriptionsInfoResponse) {
        doReturn(mySubscriptionsInfoResponse).when(mockInfoResponseBuilder).buildResponse(profileId, null);
        if(mySubscriptionsInfoResponse != null) {
            //use real instance here just to build mock response easier
            UxReference uxReference = UxReference.valueOf(carouselType.toUpperCase());
            MySubscriptionsCarouselResponse mySubscriptionsCarouselResponse = new MySubscriptionsCarouselResponseBuilderImpl(mockInfoResponseBuilder, carouselItemConverter, responseBuilderUtil).buildResponse(profileId, null, uxReference);
            doReturn(mySubscriptionsCarouselResponse).when(mockCarouselResponseBuilder).buildResponse(eq(profileId), any(), eq(uxReference));
        }
    }

    private void whenRequestIsSent() {
        response = service.getCarousel(profileId, null, carouselType);
    }

    private void thenStatusCodeIs(int code) {
        assertNotNull(response);
        assertEquals(code, response.getStatus());
    }

}
