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
    private String accesstoken = null;

    @BeforeClass
    public void setUp() {
        // downloadngo.fc.TestManager.loadGlobalParameters(); // NOTE: Unremark if you want to bypass suite and run just this class
        // 1. Call TGuard:
        System.out.println("Calling Call TGuard...");
        String protocol = "https";
        String TGuard_Host = "tstagesms.stage.att.net";
        String TGuard_Path = "commonLogin/nxsATS/TokenGen";
        String userPassword = "test1ng";
        String appId1 = "aGm70P8TdIZ%2FluduHKmrYwcyVgU%3D";
        //String appId = "F8LZ2DSQLX6RDIHDStbn5Vy8oAg=";
        String userId = "qayslid_021417424079302270240";

        // code that will be invoked when this test is instantiated
        // here goes authentication
        String url = "{{protocol}}://{{TGuard_Host}}/{{TGuard_Path}}?password={{userPassword}}&client_type=TVOS&appID={{appId}}&client_version=f7d7503c-12da-480e-9e6e-4f1012208fda%252F22&respType=json&rememberMe=on&rememberID=on&TG_OP=TokenGen&userid={{userId}}";

        url = url.replace("{{protocol}}", protocol);
        url = url.replace("{{TGuard_Host}}", TGuard_Host);
        url = url.replace("{{TGuard_Path}}", TGuard_Path);
        url = url.replace("{{userPassword}}", userPassword);
        url = url.replace("{{appId}}", conf.getAppid());
        url = url.replace("{{userId}}", userId);

        Response res = given().header("Content-Type", "application/x-www-form-urlencoded").when().post(url);

        //Response res = RestAssured.post(url);
        Assert.assertEquals(200, res.getStatusCode());

        String json = res.asString();
        JsonPath jp = new JsonPath(json);

        String atsToken = jp.get("atsToken");
        Assert.assertNotNull(atsToken); // null when property is missing
        Assert.assertNotEquals(atsToken, "");

        System.out.println("Successfully received a TGuard token " + atsToken);
        // String tToken = atsToken;

        // 2: Call AuthN (authn-token-ios):
        System.out.println("Calling AuthN...");

        // {{protocol}}://{{apiHost}}/{{AuthNURL}}
        //String apiHost = "api-dev.aeg.cloud";
        //String envUrl = "api-stage.aeg.cloud";
        String authNUrl = "https://{{envUrl}}/authn-token?reqParams=AUTHGROUPS";
        authNUrl = authNUrl.replace("{{envUrl}}", conf.getAuthn_url());

        String client_id = "iOS";
        String deviceClassId = "8B8FA169-A5F0-4EAF-856A-F42403AAD41F";
        String siteUserId = "dotcomsqa11@gmail.com";
        String authNContent = "deviceClassId={{deviceClassId}}&client_id={{client_id}}&siteUserId={{siteUserId}}&tToken={{atsToken}}";
        authNContent = authNContent.replace("{{client_id}}", client_id);
        authNContent = authNContent.replace("{{deviceClassId}}", deviceClassId);
        authNContent = authNContent.replace("{{siteUserId}}", siteUserId);
        authNContent = authNContent.replace("{{authNContent}}", authNContent);
        authNContent = authNContent.replace("{{atsToken}}", atsToken);

        authNUrl += "?" + authNContent;

        Response res1 = given().header("Content-Type", "application/x-www-form-urlencoded").when().post(authNUrl);

        //Response res = RestAssured.post(url);
        Assert.assertEquals(2500, res1.getStatusCode());

        String json1 = res1.asString();
        JsonPath jp1 = new JsonPath(json1);

        String responseStatus = jp1.get("responseStatus.status");
        Assert.assertEquals(responseStatus, "success");
        String access_token = jp1.get("access_token");
        Assert.assertNotNull(access_token); // null when property is missing

        String accesstoken = "Bearer " + access_token;
        this.accesstoken = accesstoken;

        System.out.println("Successfully received an AuthN token");
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