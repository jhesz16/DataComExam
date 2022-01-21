package API;

import Driver.Setup;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.io.IOException;

public class Methods {

    public static Response response;

    public static JsonPath getJSONPath()
    {
        return response.jsonPath();
    }

    @Step("Get Request Header")
    public static RequestSpecification buildRequestHeader() throws IOException {
        RestAssured.baseURI = Setup.getProperty("ApiURI");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        return request;
    }

    @Step("Get Sample Data")
    public static JSONObject sampleData()
    {
        JSONObject requestParams = new JSONObject();
        requestParams.put("city", "Manila");
        requestParams.put("street", "Vigo");
        requestParams.put("name", "jc martin");
        requestParams.put("phone", "090-000-0000");
        requestParams.put("username", "someuser@gmail.com");
        return requestParams;
    }

    @Step("Build Request")
    public static RequestSpecification BuildRequest(JSONObject data) throws IOException {
        RequestSpecification request = buildRequestHeader();
        return request.body(data.toJSONString());
    }
}
