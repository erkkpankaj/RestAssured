
import org.junit.Test;
import org.junit.Assert;
import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCources() {
		int sum = 0;
		JsonPath js = new JsonPath(payload.CoursePrice());
		int courseCount = js.getInt("courses.size()");

		for (int i = 0; i < courseCount; i++) {
			int coursePrice = js.get("courses[" + i + "].price");
			int courseCopies = js.get("courses[" + i + "].copies");
			int amount = coursePrice * courseCopies;
			System.out.println("The total amount of each course::::" + amount);
			sum = sum + amount;
		}
		System.out.println("The total courses amount::::" + sum);
		int purchaseAmount = js.get("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
	}

}
