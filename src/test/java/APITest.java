import Driver.Setup;
import API.Methods;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.get;

public class APITest {

    private static Response response;

    @BeforeMethod
    public static void setupClass() throws IOException {
        Methods.response = get(Setup.getProperty("ApiURL"));
        response = Methods.response;
    }

    @Description("TC1: Verify GET Users request")
    @Test(description = "TC1")
    public void verifyGETUsersRequest() {
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonPath path = response.jsonPath();
        ArrayList<String> string = path.get("username");
        Assert.assertEquals(string.size(), 10);
    }

    @Description("TC2: Verify GET User request by Id")
    @Test(description = "TC2")
    public void verifyGETUserRequestById() {
        Assert.assertEquals(response.getStatusCode(), 200);
        ArrayList<String> name = Methods.getJSONPath().get("name");

        Assert.assertEquals(name.get(7), "Nicholas Runolfsdottir V");
    }

    @Description("TC3: Verify POST Users request")
    @Test(description = "TC3")
    public void verifyPostUserRequest() throws IOException {
        JSONObject testData = Methods.sampleData();
        RequestSpecification request = Methods.BuildRequest(testData);
        Assert.assertEquals(request.post("/users").getStatusCode(), 201);
    }
}


