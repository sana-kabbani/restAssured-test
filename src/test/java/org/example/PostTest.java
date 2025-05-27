package org.example;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PostTest {
    private static final String BASE_URL = "https://reqres.in/api";


    @Test
    public void createUserWithPOST(){
        RestAssured.baseURI = BASE_URL;

        String requestBody = "{ \"name\": \"sena\", \"job\": \"Engineer\" }";

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
        assertTrue(response.getTime() < 2000, "Post request too slow");

    }

}

