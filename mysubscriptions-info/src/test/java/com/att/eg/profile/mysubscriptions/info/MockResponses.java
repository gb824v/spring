package com.att.eg.profile.mysubscriptions.info;

import com.att.eg.profile.mysubscriptions.info.model.ActiveSubscriptionsResponse;
import com.att.eg.profile.mysubscriptions.info.model.ProductsResponse;
import com.att.eg.profile.mysubscriptions.info.model.SessionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MockResponses {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ProductsResponse getMockProductsResponse() {
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get("src/test/resources/SampleProductsResponse.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProductsResponse productsResponse = null;
        try {
            productsResponse = objectMapper.readValue(body, ProductsResponse.class);
            System.out.println(productsResponse.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productsResponse;
    }

    public SessionResponse getMockSessionResponse() {
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get("src/test/resources/SampleSessionResponse.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SessionResponse response = null;
        try {
            response = objectMapper.readValue(body, SessionResponse.class);
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public ActiveSubscriptionsResponse getMockActiveSubscriptionsResponse() {
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get("src/test/resources/SampleActiveSubscriptionsResponse.json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ActiveSubscriptionsResponse activeSubscriptionsResponse = null;
        try {
            activeSubscriptionsResponse = objectMapper.readValue(body, ActiveSubscriptionsResponse.class);
            System.out.println(activeSubscriptionsResponse.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return activeSubscriptionsResponse;
    }
}
