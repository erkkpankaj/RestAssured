package pogoClassesSerialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

public class Serialization {
	
	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		AddPlace ap= new AddPlace();
		ap.setName("Jhons House");
		ap.setPhone_number("12345");
		ap.setAddress("29, Pune");
		ap.setLanguage("French-IN");
		ap.setWebsite("http://www.kkpankaj.com");
		ap.setAccuracy(50);
			
		//As we need to add type here which is an Array hence added items in ArrayList and passed it
		List<String>myList = new ArrayList<String>();
		myList.add("Windsor park - Lucknow");
		myList.add("Indira Nagar");
		ap.setTypes(myList);
		
		//As location method is accepting the Location object hence created the object of location class and used it method
		Location loc= new Location();
		loc.setLat(-333.4444);
		loc.setLng(443434);
		ap.setLocation(loc);
				
		Response res=given().log().all().queryParam("key", "qaclick123")
				.body(ap)
				.when().post("/maps/api/place/add/json").
				then().assertThat().statusCode(200).extract().response();

				String responseString=res.asString();
				System.out.println("Response Value :::" + responseString);

	}

}
