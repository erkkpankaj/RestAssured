import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class POSTAPI {

	// Validate that Add place API is working fine
	// given- all input details
	// when- submit the API - resource, http method
	// then- validate the response
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// log().all is used to log all logs
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")

				// Moving the body part to separate package i.e. payloads - payload.java file
				.body(payload.AddPlace())
				// validating scope and header details as well log().all is used to log all logs
				.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				//equalTo is used to get the details from body
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)");
	}

}
