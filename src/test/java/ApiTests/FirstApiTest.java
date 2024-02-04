package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FirstApiTest {


//    @Test
//    public void firstTestCreateUser(){
//
//
//        RestAssured.baseURI = "https://bookstore.toolsqa.com";
//        RequestSpecification requestSpecification =
//                RestAssured.given().header("Content-Type","application/json");
//
////        JSONObject jobj = new JSONObject();
////        jobj.put("UserName","Abhinav@1991");
////        jobj.put("Password","testPassword");
//
//        Response response = requestSpecification.body("{\n" +
//                "  \"userName\": \"Abhinav@\",\n" +
//                "  \"password\": \"testPassword@123\"\n" +
//                "}").post("/Account/v1/User");
//
//        System.out.println(response.getStatusLine());
//
//        ResponseBody rbody = response.getBody();
//        System.out.println(rbody.asString());
//
//        System.out.println(response.getStatusCode());
//        System.out.println(response.body().toString());
//        Assert.assertEquals(response.getStatusCode(),201);
//
//    }


    @Test
    public void endToEndTest1(){

        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        RequestSpecification requestSpecification = RestAssured.given();


        Response response = requestSpecification.header("Content-Type","application/json")
                .body("{\n" +
                        "  \"userName\": \"Abhinav@\",\n" +
                        "  \"password\": \"testPassword@123\"\n" +
                        "}").post("/Account/v1/User");
        String userID = JsonPath.from(response.body().asString()).get("userId");
        System.out.println(response.body().asString());
        System.out.println(userID);

          response = requestSpecification.body("{\n" +
                "  \"userName\": \"Abhinav@\",\n" +
                "  \"password\": \"testPassword@123\"\n" +
                "}").post("/Account/v1/GenerateToken");

         System.out.println(response.body().asString());
//         Headers header  = response.headers();
//         header.getValue("<header Name>");

         String token = JsonPath.from(response.asString()).get("token");

         System.out.println(token);
        System.out.println(userID);

        response = requestSpecification.header("Content-Type","application/json")
                .get("/BookStore/v1/Books");

         ResponseBody rbody = response.getBody();
         System.out.println(rbody.asString());

         JsonPath jpath  =  JsonPath.from(rbody.asString());
         String isbn = jpath.getString("books.findAll { it.pages == 234 }.isbn");

//         List<String> stringfprbooks = jpath.get("$.books[?(@.pages ==234)].isbn");
         System.out.println(isbn);

         List<String> authors = jpath.getList("books.author");
         for(String author: authors){
             System.out.println(author);
         }

//         String isbn = jpath.getString("books[1].isbn");
//         System.out.println(isbn);

        List<Map<String, String>> books = jpath.getList("books");
            System.out.println(books.get(0).get("description"));


//        requestSpecification = RestAssured.given()
//                .header("Content-Type","application/json")
//                .header("Authentication","Bearer "+ token);


        response = requestSpecification.body("{\n" +
                "  \"userId\": \"3547781a-0ef2-47af-a54d-14f1c3efdc2e\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\":" + isbn +
                "    }\n" +
                "  ]\n" +
                "}").post("/BookStore/v1/Books");

        ResponseBody rbodyNewBook = response.getBody();
        System.out.println(rbodyNewBook.asString());






    }

}
