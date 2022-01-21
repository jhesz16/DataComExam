import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.get;

public class testAPI {

    public final String url = "https://jsonplaceholder.typicode.com/users";
    public final String uri = "https://jsonplaceholder.typicode.com";

    @BeforeMethod
    public static void setupClass() {
    }

    @Description("TC1: Verify GET Users request")
    @Test(description = "TC1")
    public void verifyGETUsersRequest() {

        Response response = get(url);
        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath path = response.jsonPath();
        ArrayList<String> string = path.get("username");
        Assert.assertEquals(string.size(), 10);
    }

    @Description("TC2: Verify GET User request by Id")
    @Test(description = "TC2")
    public void verifyGETUserRequestById() {
        Response response = get(url);
        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath path = response.jsonPath();
        ArrayList<String> name = path.get("name");
        Assert.assertEquals(name.get(7), "Nicholas Runolfsdottir V");
    }

    @Description("TC3: Verify POST Users request")
    @Test(description = "TC3")
    public void verifyPostUserRequest() {
        RestAssured.baseURI = uri;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject requestParams = new JSONObject();
        requestParams.put("city", "Manila");
        requestParams.put("street", "Vigo");
        requestParams.put("name", "jc martin");
        requestParams.put("phone", "090-000-0000");
        requestParams.put("username", "someuser@gmail.com");

        request.body(requestParams.toJSONString());
        Response response = request.post("/users");

        Assert.assertEquals(response.getStatusCode(), 201);

    }

}


