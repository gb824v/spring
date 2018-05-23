package com.att.eg.profile.mysubscriptions.info.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.ajsc.common.RequestScopeModifier;
import com.att.eg.profile.mysubscriptions.info.Application;
import com.att.eg.profile.mysubscriptions.info.MockResponses;
import com.att.eg.profile.mysubscriptions.info.model.ActiveSubscriptionsResponse;
import com.att.eg.profile.mysubscriptions.info.model.Product;
import com.att.eg.profile.mysubscriptions.info.model.ProductsResponse;
import com.att.eg.profile.mysubscriptions.info.model.SessionResponse;
import com.att.eg.profile.mysubscriptions.info.model.Subscription;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, RequestScopeModifier.class}, webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class QPUMSClientTest {

    private MockResponses mockResponses = new MockResponses();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Client client = Mockito.mock(Client.class);


    private QPUMSClient qpumsClient = new QPUMSClientImpl("file:///home/naveen/MySubscription/mysubscriptions-info/src/test/resources", "SampleProductsResponse.json",
            "SampleActiveSubscriptionsResponse.json", "response.json", client);

    public void setUpMocks(Class mockClassType, Object responseObject) {
        WebTarget mockWebTarget = Mockito.mock(WebTarget.class);
        Invocation.Builder mockBuilder = Mockito.mock(Invocation.Builder.class);
        Mockito.when(client.target(Mockito.anyString())).thenReturn(mockWebTarget);
        Mockito.when(mockWebTarget.queryParam(Mockito.anyString(),Mockito.anyString())).thenReturn(mockWebTarget);
        Mockito.when(mockWebTarget.path(Mockito.anyString())).thenReturn(mockWebTarget);
        Mockito.when(mockWebTarget.request(Mockito.anyString())).thenReturn(mockBuilder);
        Mockito.when(mockBuilder.get(Mockito.eq(mockClassType))).thenReturn(responseObject);
        Mockito.when(mockBuilder.post(Entity.json(Mockito.any()), Mockito.eq(mockClassType))).thenReturn(responseObject);
    }

    @Test
    public void testGetProductsShouldReturnAtleastOneBasePackage() {
        ProductsResponse productsResponse = mockResponses.getMockProductsResponse();
        setUpMocks(ProductsResponse.class, productsResponse);
        ProductsResponse response = qpumsClient.getProducts();
        assertNotNull(response);
        System.out.println(response);
        //should be at least one basic service
        boolean hasBase = false;
        for(Product product : response.getData()) {
            hasBase |= product.isBasicService();
        }
        assertEquals(true, hasBase);
    }
    
    @Test
    public void testGetActiveSubscriptionsShouldReturnAtleastOneBasePackage() {

        ActiveSubscriptionsResponse activeSubscriptionsResponse = mockResponses.getMockActiveSubscriptionsResponse();
        setUpMocks(ActiveSubscriptionsResponse.class, activeSubscriptionsResponse);
        ActiveSubscriptionsResponse activeSubscriptionsResponse1 = qpumsClient.getActiveSubscriptions("myString");
        assertNotNull(activeSubscriptionsResponse1);
        System.out.println(activeSubscriptionsResponse1);
        //should be at least one basic service
        boolean hasBase = false;
        for(Subscription subscription : activeSubscriptionsResponse1.getData().getSubscriptions()) {
            hasBase |= subscription.isBasePackage();
        }
        assertEquals(true, hasBase);
    }

    @Test
    public void testGetSessionShouldReturnCorrectSessionIdWhenResponseIsMockedFromSampleResponseJson() {
        SessionResponse sessionResponse = mockResponses.getMockSessionResponse();
        setUpMocks(SessionResponse.class, sessionResponse);
        SessionResponse sessionResponse1 = qpumsClient.getSession("canBeAnyString");
        assertNotNull(sessionResponse1);
        String sessionId = sessionResponse1.getData().getSession().getId();
        assertEquals("n2HL-ToPL-x4l9-04iJ-0B0H-QrM4-1T", sessionId);
    }



}
