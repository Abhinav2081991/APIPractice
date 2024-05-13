package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class CookieTest {


    public static void main(String[] args) {

        RestAssured.baseURI = "https://bookstore.toolsqa.com";

        RequestSpecification request =
                RestAssured.given().header("Content-Type","application/json");
//                        .cookie("FCNEC", "%5B%5B%22AKsRol-62aDirUoFtlsAA5vIrIcIW82JoQZpbtTVEtcGgrsdnz08N81f4qq3lcTH9FWEJBd7uDwAffWIZArss81W0y44_gmGhMHqzoMCB8kOEi5eMOVOBbJ5ch0JXorBRM5x2YsZHLojGoD1oJ8lto4H58DuqNUAlg%3D%3D%22%5D%5D");

        Response response = request.get("/BookStore/v1/Books");

        System.out.println(response.getStatusCode());

//        Map<String,String> s = response.getCookies();
//        System.out.println(s);

        Cookie  cookie = response.getDetailedCookie("panoramaId");
        System.out.println(cookie.getName());
        System.out.println(cookie.getValue());


//        Cookies cookies = response.getDetailedCookies();
//
//        for(Cookie c : cookies) {
//
//            System.out.println(c.getName());
//            System.out.println(c.getValue());
//
//        }

    }
}
