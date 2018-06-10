package com.att.testng.spring.reset;
/**
 * Author: Guy Bitan
 */
import java.util.HashMap;

import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TGApi {
    private String tgHost = "tstagesms.stage.att.net";
    private String tgAppId = "F8LZ2DSQLX6RDIHDStbn5Vy8oAg=";
    private String tgPort = "443";
    private String tgUrl = "/commonLogin/nxsATS/TokenGen";
    private String user = "qayslid_020717319663930400151";
    private String password="test1ng";
    private String tgop = "TokenGen";
    private String tgRememberId = "off";
    private String authNPort = "443";
    private String euthNClientId = "DTV_WEB";
    private String restType ="json";
    private String url = "https://api-stage.aeg.cloud/authn-token";
    private String authNUrl="/" + url.split("/")[3];
    private String authNhost=url.split("/")[2];
    private String accessToken;

    /**
     * Get tguard access token. 1 - Gets tguard token by calling tguard token
     * generator api. 2 - Use the token from above to get the access token by
     * calling tguard authn token api.
     *
     * @return access token
     * @throws Exception
     */
    public String getAccessToken() {
        JsonPath jsonPath = getTguardToken();
        if (jsonPath != null) {
            accessToken = jsonPath.get("atsToken").toString();
        }
        if (!accessToken.isEmpty()) {
            String url = String.format(String.format("https://%s:%s%s", authNhost, authNPort, authNUrl));
            HashMap<String, String> params = new HashMap<>();
            Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
            params.put("client_id", euthNClientId);
            params.put("tToken", accessToken);
            Response response = given().header(header).queryParams(params).when().post(url);
            accessToken = new JsonPath(response.asString()).get("access_token");
        }
        return accessToken;
    }

    /**
     * Get tguard token by calling tguard token generator api.
     *
     * @return token
     * @throws Exception
     */
    private JsonPath getTguardToken() {
        String url = String.format("https://%s:%s%s", tgHost, tgPort, tgUrl);
        HashMap<String, String> params = new HashMap<>();
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        params.put("userid", user);
        params.put("password", password);
        params.put("TG_OP", tgop);
        params.put("appID", tgAppId);
        params.put("rememberId", tgRememberId);
        params.put("respType", restType);
        Response response = given().header(header).queryParams(params).when().post(url);
        return new JsonPath(response.asString());

    }
}