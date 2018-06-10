package com.javacodegeeks.testng.spring.reset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

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

    }

    @Test(testName = "Test success case of download and go endpoint",
            description = "Expecting to receive 'dRights.dlToken' token",
            groups = {"full"})
    public void testDownloadEndpoint() {
        System.out.println("Calling DnG endpoint ...");

        //String apiHost = "api-dev.aeg.cloud";
        String contentId = "bb51949d-3a59-418b-878f-4f2c2aac7cdb";
        String url = "https://{{apiHost}}/right/authorization/downloadngo-fc/feature/wallabies/rights/dng?contentid={{contentId}}&latlong=33.930324,-118.386054&proximity=O&reserveCTicket=true&network=WiFi/";

        url = url.replace("{{apiHost}}", conf.getDng_host());
        url = url.replace("{{contentId}}", contentId);

        Response res = given().header("Content-Type", "application/json").header("Authorization", accesstoken).when().get(url);

        Assert.assertEquals(200, res.getStatusCode());
        System.out.println("Successfully received a status of 200");

        String json = res.asString();
        JsonPath jp = new JsonPath(json);

        boolean authorized = jp.get("authorized");
        Assert.assertEquals(authorized, true);
        System.out.println("Successfully received authorized = true");

        String dlToken = jp.get("dRights.dlToken");
        Assert.assertNotNull(dlToken);
        System.out.println("Successfully received dRights.dlToken");
    }


}