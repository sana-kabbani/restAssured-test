package org.example;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class getTest {
    private static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void getUsersList_checkPaginationAndData() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1")
                .queryParam("page", 1)
                .when()
                .get("/users");

        assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        assertTrue(response.getTime() < 1000, "Get request too slow");

        List<Map<String, Object>> users = response.jsonPath().getList("data");

        System.out.println("===== USERS ON PAGE 1 =====");
        for (Map<String, Object> user : users) {
            System.out.println("ID       : " + user.get("id"));
            System.out.println("Name     : " + user.get("first_name") + " " + user.get("last_name"));
            System.out.println("Email    : " + user.get("email"));
            System.out.println("Avatar   : " + user.get("avatar"));
            System.out.println("-----------------------------");
        }
    }


}
