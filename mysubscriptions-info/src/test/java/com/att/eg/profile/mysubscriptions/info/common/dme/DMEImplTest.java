package com.att.eg.profile.mysubscriptions.info.common.dme;

import com.att.aft.dme2.handler.DME2RestfulHandler;
import com.att.ajsc.dme2.handler.DME2RestfulHandlerWrapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.when;

/**
 * Created by vs5946 on 6/25/17.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(DME2RestfulHandlerWrapper.class)
public class DMEImplTest {

    private final DME2RestfulHandler.ResponseInfo responseMock = Mockito.mock(DME2RestfulHandler.ResponseInfo.class);

    private final DMEImpl dme = new DMEImpl(new HashMap<>());

    private final DME.Params params_default = new DME.Params(
            "",
            "",
            0,
            "" + "",
            null,
            null,
            "",
            "",
            "",
            "",
            "DEFAULT",
            false);

    private final DME.Params params_routOffer_null = new DME.Params(
            "",
            "",
            0,
            "" + "",
            null,
            null,
            "",
            "",
            "",
            null,
            "DEFAULT",
            false);

    private final DME.Params params_routOffer_exist = new DME.Params(
            "",
            "",
            0,
            "" + "",
            null,
            null,
            "",
            "",
            "",
            "BLUE",
            "DEFAULT",
            false);


    @Before
    public void init() throws Exception {

        MockitoAnnotations.initMocks(this);

        PowerMockito.mockStatic(DME2RestfulHandler.class);
        PowerMockito.mockStatic(DME2RestfulHandlerWrapper.class);

        when(responseMock.getCode()).thenReturn(200);
        when(responseMock.getBody()).thenReturn("Hello World");
        when(responseMock.getHeaders()).thenReturn(new HashMap<>());
        when(responseMock.toString()).thenReturn("");


        PowerMockito.when(DME2RestfulHandlerWrapper.callService(
                any(String.class),
                any(Integer.class),
                any(String.class),
                any(),
                any(Map.class),
                any(Map.class),
                any(String.class),
                anyBoolean())).thenReturn(responseMock);
    }

    @Test
    public void shouldPassCorrectResponseWhenCalled() throws Exception {


        DME.Response response = dme.callService(params_default);

        assertThat(response.getBody()).isEqualTo("Hello World");
        assertThat(response.getCode()).isEqualTo(200);

    }

    @Test
    public void shouldPassCorrectResponseWhenCalledRouteOfferNull() throws Exception {


        DME.Response response = dme.callService(params_routOffer_null);

        assertThat(response.getBody()).isEqualTo("Hello World");
        assertThat(response.getCode()).isEqualTo(200);

    }

    @Test
    public void shouldPassCorrectResponseWhenCalledRouteOfferExist() throws Exception {


        DME.Response response = dme.callService(params_routOffer_exist);

        assertThat(response.getBody()).isEqualTo("Hello World");
        assertThat(response.getCode()).isEqualTo(200);

    }
}
