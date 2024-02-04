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
        Response res = req.body("").get();

        ResponseBody rb =  res.getBody();

        JsonPath js = JsonPath.from(res.asString());
        js.get("books.text").;






    }

}
