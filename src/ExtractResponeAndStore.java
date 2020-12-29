import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class ExtractResponeAndStore {


	// *********** Extract response and store in string ***********//
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		// log().all is used to log all logs
	String response=	given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				// Moving the body part to separate package i.e. files :: payload.java file
				.body(payload.AddPlace())
				// validating scope and header details as well log().all is used to log all logs
				.when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();	
	System.out.println("Response :::::" + response);
	//JsonPath is used for parsing JSON
	JsonPath js = new JsonPath(response);
	String place_id=js.getString("place_id");
	System.out.println("Place Id :::::" + place_id );
	
	}

}
