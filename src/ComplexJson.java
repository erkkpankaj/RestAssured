import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	// *********** Complex JSON ***********//
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js = new JsonPath(payload.CoursePrice());

		// Print number of courses retrun by API
		int courseCount = js.getInt("courses.size()");
		System.out.println("Course Count :::::" + courseCount);
		
		
		// Print purchase amount
				int totalAmount = js.getInt("dashboard.purchaseAmount");
				System.out.println("Total amount :::::" + totalAmount);
				
				// Print first course details 
				String firstCourse = js.get("courses[0].title");
				System.out.println("First cource title :::::" + firstCourse);
				
				// Print all courses and their prices 	
				
				for(int i=0; i<courseCount;i++)
				{
					String courseTitle=js.get("courses["+i+"].title");
					//Here we can print O/P in same line with syso, just convert the returned values in to String by using toString()
					System.out.println(js.get("courses["+i+"].price").toString());
					System.out.println("Course titles ::::"  + courseTitle);
				}
				
				
				// Print number of copies sold by RPA

				for(int i=0; i<courseCount;i++)
				{
					String courseTitle=js.get("courses["+i+"].title");
					if(courseTitle.equalsIgnoreCase("RPA"))
					{
					int copiesSold=js.get("courses["+i+"].copies");
					System.out.println("Cpoies sold for RPA cources::::"  + copiesSold);
					break;
					}
				}
				
	}
}
