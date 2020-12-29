package pojoClassesSerialization;

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
		ap.setWebsite("http://www.kumarworls.com");
		ap.setAccuracy(50);
			
		//As we need to add type here which is an Array hence added items in ArrayList and passed it
		List<String>myList = new ArrayList<String>();
		myList.add("IVY Estate Villas- Pune");
		myList.add("Wagholi");
		ap.setTypes(myList);
		
		//As location method is accepting the Location object hence created the object of location class and used it method
		Location loc= new Location();
		loc.setLat(-1233.44);
		loc.setLng(98998);
		ap.setLocation(loc);
				
		Response res=given().log().all().queryParam("key", "qaclick123")
				.body(ap)
				.when().post("/maps/api/place/add/json").
				then().assertThat().statusCode(200).extract().response();

				String responseString=res.asString();
				System.out.println("Response Value :::" + responseString);
				
//We can also use the DeSerilization here as well using getmethods:
				
				System.out.println("Accurcy value ::: " + ap.getAccuracy());
				System.out.println("Address value ::: "+ ap.getAddress());
				System.out.println("Name value ::: "  + ap.getName());
				System.out.println("Phone number value ::: " + ap.getPhone_number());
				System.out.println("Language value ::: "+ ap.getLanguage());
				System.out.println("Website name ::: "  + ap.getWebsite());
				System.out.println("Lat value ::: "  + ap.getLocation().getLat());
				System.out.println("Lng value ::: "  + ap.getLocation().getLng());
				System.out.println("Type 1 value ::: "  + ap.getTypes().get(0));
				System.out.println("Type 2 value ::: "  + ap.getTypes().get(1));			

	}

}
