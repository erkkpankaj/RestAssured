package pojoClassesSerialization;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecsBuilderTest {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		AddPlace ap = new AddPlace();
		ap.setName("Jhons House");
		ap.setPhone_number("12345");
		ap.setAddress("29, Pune");
		ap.setLanguage("French-IN");
		ap.setWebsite("http://www.kkpankaj.com");
		ap.setAccuracy(50);

		// As we need to add type here which is an Array hence added items in ArrayList and passed it
		List<String> myList = new ArrayList<String>();
		myList.add("Windsor park - Lucknow Uttar Pradsesh");
		myList.add("Indira Nagar");
		ap.setTypes(myList);

		// As location method is accepting the Location object hence created the object of location class and used it method
		Location loc = new Location();
		loc.setLat(-333.4444);
		loc.setLng(443434);
		ap.setLocation(loc);

		// Creating the RequestSpecBuilder
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		/*
		 * //We need to break the response O/P using semicolon (;) , which originally was
		 * Response res=given().log().all().queryParam("key", "qaclick123")
				.body(ap)
				.when().post("/maps/api/place/add/json").
				then().assertThat().statusCode(200).extract().response(); 
		 * and return type will be converted into RequestSpecification only
		 */
		
		RequestSpecification res = given().spec(req).body(ap);
		
		// Creating the ResponeSpecBuilder
		ResponseSpecification resp= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		Response apiResponse = res.when().post("/maps/api/place/add/json").then().spec(resp).extract()
				.response();

		String responseString = apiResponse.asString();
		System.out.println("Response Value :::" + responseString);

	}

}
