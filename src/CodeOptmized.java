import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;
import files.reuseableMethod;

public class CodeOptmized {

	// GET API

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// log().all is used to log all logs
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")

				// Moving the body part to separate package i.e. payloads - payload.java file
				.body(payload.AddPlace())

				// validating scope and header details as well log().all is used to log all logs
				.when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)").extract().response()
				.asString();
		System.out.println("Response :::::" + response);

		// JsonPath is used for parsing JSON
		JsonPath js = new JsonPath(response);
		String place_id = js.getString("place_id");
		System.out.println("Place Id :::::" + place_id);

		// Update place using PUT API

		String newAddress = "20 winter walk, Mumbai";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + place_id + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("maps/api/place/update/json").then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// GET API
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
				.when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract()
				.response().asString();

		// Used reusable method here defined in files :: reuseableMethod.java 
		JsonPath js1 = reuseableMethod.rawToJson(getPlaceResponse);
		// JsonPath js1 = new JsonPath(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println("Actual Address :::::" + actualAddress);
		Assert.assertEquals(actualAddress, newAddress);

	}

}
