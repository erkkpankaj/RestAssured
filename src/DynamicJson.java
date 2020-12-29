

import files.payload;
import files.reuseableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.Test;




// *********** Providing the JOSN data dynamically ***********//

public class DynamicJson {
	
	@Test
	public void addBook()
	{
		RestAssured.baseURI="http://216.10.245.166";
	String Response=	given().header("Content-Type", "application/json")
		.body(payload.AddBook("istqbqa","202020")).when()
		.post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
	JsonPath js = reuseableMethod.rawToJson(Response);
		String id=js.get("ID");
		System.out.println("Book ID ::::" + id);

		
				
	}

}
