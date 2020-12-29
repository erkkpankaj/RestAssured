package pojoClassesdeDeSerialization;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import files.payload;
import files.reuseableMethod;

public class OAuthTest {

	public static void main(String[] args) throws InterruptedException {
	
		// Hit the URL to get this URL + provide GMail credentials:::: https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g4r022ZeGWwthdconEyWGz6J2D6CnCu437HZ2QkAMKzgmDoWG9LfLLbPwc5vUAzjg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none##";
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&scope")[0];
		System.out.println("The code is :::: " + code);

		String accessTokenrespone = given().urlEncodingEnabled(false).queryParams("code", code)
				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type", "authorization_code").when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();
		System.out.println(accessTokenrespone);
		JsonPath js = new JsonPath(accessTokenrespone);
		String accessToken = js.getString("access_token");

	
		// Here we are performing de-serialization (reading the JOSN response) using getCourcesUsingPojo class
		// Object created for getCourcesUsingPojo class and it has all the JSON response values now

		getCourcesUsingPojo gcp= given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
				.when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(getCourcesUsingPojo.class);
		
		// These methods 'getLinkedIn and getInstructor' are part of getCourcesUsingPojo class only 
		System.out.println(gcp.getLinkedIn());
		System.out.println(gcp.getInstructor());	
		System.out.println(gcp.getCourses().getApi().get(1).getCourseTitle());
		System.out.println(gcp.getCourses().getApi().get(1).getPrice());
	
	
		List<Api> apiCourses= gcp.getCourses().getApi();
		for (int i=0;i<apiCourses.size();i++)

		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI webServices testing"));		
			{
				apiCourses.get(i).getPrice();
			}
		}
		
		// Get the courses name of WebAutomation
	List<WebAutomation> w=	gcp.getCourses().getWebAutomation();
	for(int j=0;j<=w.size();j++)
	{
	System.out.println(w.get(j).getCourseTitle());	
	}
}
}
