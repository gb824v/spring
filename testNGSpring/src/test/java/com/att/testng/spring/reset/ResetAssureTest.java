package com.att.testng.spring.reset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@ContextConfiguration("classpath:resetAssureContext.xml")
public class ResetAssureTest extends AbstractTestNGSpringContextTests {
    @Autowired
    ApplicationConfiguration conf;
    @Autowired
    TGApi tgapi;
    private String accesstoken = null;

    @BeforeClass
    public void setUp() {
        this.accesstoken = tgapi.getAccessToken();
    }

    @Test(testName = "Test success case of download and go endpoint",
            description = "Expecting to receive 'dRights.dlToken' token",
            groups = {"full"})

    public void testDownloadEndpoint() {
        System.out.println(accesstoken);

    }

}