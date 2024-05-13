package ApiTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

public class SecondAPITest {

    @Test
    public void test(){

        RestAssured.baseURI= "test path";
//        RestAssured.filters((req, res, ctx)-> {
//            req.auth().oauth2("access token");
//            return (req, res);
//
//
//        });
        RequestSpecification req = RestAssured.given();
        req.pathParam("id",34);  // setting Path parameter
        Response res = req.body("").get("/test/{id}"); //passing path param

        ResponseBody rb =  res.getBody();
        JsonPath js = JsonPath.from(res.asString());

        RestAssured.baseURI = "";
        RequestSpecification request  =  RestAssured.given().header("Content-Type","application-json");

        Response  response =  request.body("").post("/path");

        ResponseBody body = response.body();
        JsonPath path = body.jsonPath();

        path.get("").equals("");




    }

}
