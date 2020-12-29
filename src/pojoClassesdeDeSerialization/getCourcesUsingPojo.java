package pojoClassesdeDeSerialization;

public class getCourcesUsingPojo {
	
	private String services;
	private String expertise;
	// As courses is nested JOSN, which is returning the Array of JOSN, so change the return type as object 
	//private String Courses;
	
	private pojoClassesdeDeSerialization.Courses Courses;
	
	private String instructor;
	private String linkedIn;
	
 // Need to generate the getters / setters for these variable using "ALT+SHIFT+S"
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public pojoClassesdeDeSerialization.Courses getCourses() {
		return Courses;
	}
	public void setCourses(pojoClassesdeDeSerialization.Courses courses) {
		Courses = courses;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	

	
}

