import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONFromExternalFile {

	// *********** provide the JSON from external source ***********//
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// log().all is used to log all logs
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")

		// We need to read the content of that file => convert into Bytes => then convert Byte data into String 
				.body(new String(Files.readAllBytes(Paths.get("C:\\Automation\\AddPlace.json.txt"))))
				.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)");
	}

}
