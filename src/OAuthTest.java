import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

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
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g4Cawwk5BHKhZfdezusGVZUluquPQEcyvEXlOCXvksZszujw51JhRnomP0zBF1suw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
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

		String resposne = given().queryParam("access_token", accessToken).when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(resposne);

	}

}
