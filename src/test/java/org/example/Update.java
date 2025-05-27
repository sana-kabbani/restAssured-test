package org.example;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Update {
    private static final String BASE_URL = "https://reqres.in/api";


    @Test
    public void createUserWithPOST(){
        RestAssured.baseURI = BASE_URL;
        String updatedBody = "{ \"name\": \"sena\", \"job\": \"Engineer\" }";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("x-api-key", "reqres-free-v1")
                .body(updatedBody)
                .when()
                .put("/users/2");

        System.out.println(response.getBody().asString());

        assertEquals(response.getStatusCode(), 200, "Failed to update user");

        // Optional: validate response data
        String job = response.path("job");
        assertEquals(job, "Engineer", "Job title was not updated correctly");
        assertTrue(response.getTime() < 2000, "Update request too slow");


    }

}

