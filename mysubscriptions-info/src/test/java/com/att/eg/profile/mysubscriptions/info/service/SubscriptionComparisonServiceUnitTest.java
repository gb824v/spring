package com.att.eg.profile.mysubscriptions.info.service;

import com.att.ajsc.common.RequestScopeModifier;
import com.att.eg.profile.mysubscriptions.info.Application;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, RequestScopeModifier.class }, webEnvironment=WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class SubscriptionComparisonServiceUnitTest {

    private final static String PROFILE_ID = "123456_TMBR";
    private String profileId;

    @Before
    public void setUp() {
        profileId = "";
    }

    @After
    public void tearDown(){
    }
    @Test
    public void serviceReturnsInvalidParamsStatusWhenEmptyProfileIdIsPassed() {
    }

    @Test
    public void serviceReturnsInvalidParamsStatusWhenNoProfileIdIsPassed() {
    }

    private void givenProfileIdIs(String id) {
        profileId = id;
    }
}
