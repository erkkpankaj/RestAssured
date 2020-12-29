import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;

import files.payload;
import files.reuseableMethod;

public class JiraTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://localhost:8080";

		//Importance of Session filter in cookie in Rest Assured code
		// Session filter is used to get the Session details
		// In case to bypass HTTPs use relaxedHTTPSValidation 
		SessionFilter session = new SessionFilter();
		given().relaxedHTTPSValidation().log().all().header("Content-Type", "application/json")
		.body("{\r\n" + "    \"username\": \"kuldeep\",\"password\": \"12345\"\r\n" + "}").filter(session)
				.post("/rest/auth/1/session").then().extract().response().toString();

		// Add comments for JIRA issue
		given().pathParam("id", "10007").log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"This is my first comment.\",\r\n" + "    \"visibility\": {\r\n"
						+ "        \"type\": \"role\",\r\n" + "        \"value\": \"Administrators\"\r\n" + "    }\r\n"
						+ "}")
				.filter(session).post("rest/api/2/issue/{id}/comment").then().log().all().assertThat().statusCode(201);

		// Add Attachment for JIRA issue using multi-part method in Rest Assured
		// This is not working as not able to enable the Allow attachment permissions in JIRA
		/*
		 * given().header("X-Atlassian-Token","no-check").filter(session).pathParam(
		 * "id", "10007") .header("Content-Type","multipart/form-data")
		 * .multiPart("file",new File("jira.txt")) .when()
		 * .post("/rest/api/2/issue/{id}/attachments").then().log().all().assertThat().
		 * statusCode(200);
		 */
		 
		// Get JIRA issue 
		
		String issueDetail=  given().filter(session).pathParam("id","10007")
				.queryParam("fileds", "comment")
				.when().get("/rest/api/2/issue/{id}").then().log().all().extract().response().asString();
		System.out.println("The issue details ::::" + issueDetail);
	}

}
