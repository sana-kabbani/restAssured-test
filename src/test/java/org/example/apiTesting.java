package org.example;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class apiTesting {
    @Test
    public void verifyStatusCode(){
        RestAssured.baseURI = "https://reqres.in/api";

        Response response = RestAssured.given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/users/1");
        System.out.println(response.getBody().asString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode,200,"Unexpected status code");



    }
    @Test()
    public void verifyResponseBody(){
        RestAssured.baseURI = "https://reqres.in/api";
// Send Get
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer your_token")
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/users/1");
        System.out.println(response.getBody().asString());

        int statusCode = response.getStatusCode();
        assertEquals(statusCode,200,"Unexpected status code");
        String email = response.path("data.email");
        System.out.println(email + "*******");

        assertTrue(email.endsWith("@reqres.in"), "Unexpected email address");
        assertTrue(response.getTime() < 1000, "Response time exceeded 1 second");




    }
    @Test
    public void createUserWithPOST(){
        RestAssured.baseURI = "https://reqres.in/api";

        String requestBody = "{ \"name\": \"sena\", \"job\": \"student\" }";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(requestBody)
                .when()
                .post("/users");

        System.out.println(response.getBody().asString());

        assertEquals(response.getStatusCode(), 201, "Unexpected status code");

        String name = response.path("name");
        assertEquals(name, "sena", "Unexpected name in response");
    }

}
