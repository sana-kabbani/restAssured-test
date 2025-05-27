package org.example;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DeleteTest {
    private static final String BASE_URL = "https://reqres.in/api";


    @Test
    public void deleteUserTest(){
        RestAssured.baseURI = BASE_URL;

        String requestBody = "{ \"name\": \"sena\", \"job\": \"Engineer\" }";

        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("/users/2");
        // Print the response code
        System.out.println("Status code: " + response.getStatusCode());

        // DELETE in reqres.in returns 204 No Content
        assertEquals(response.getStatusCode(), 204, "User was not deleted successfully");
        assertTrue(response.getTime() < 2000, "DELETE request too slow");

    }

}

