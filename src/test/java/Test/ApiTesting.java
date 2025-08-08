package Test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ApiTesting {

    @Test
    public void testGetStatusCode() {
        // Create a test in the Extent report or any other reporting mechanism if needed
        // For this example, assume a simple logging mechanism
        
        System.out.println("Running API test to check status code");

        // Retrieve the base URI from the configuration
        String baseUri = "https://api.example.com"; // replace with your actual base URI
        String endpoint = "/orders"; // Replace with your actual endpoint

        // Send the GET request and get the response
        Response response = given()
                                .baseUri(baseUri)
                            .when()
                                .get(endpoint)
                            .then()
                                .extract().response();

        // Get the status code from the response
        int statusCode = response.getStatusCode();

        // Log the result
        if (statusCode == 200) {
            System.out.println("API responded with status code 200");
        } else {
            System.out.println("API responded with status code " + statusCode);
        }
    }
}
