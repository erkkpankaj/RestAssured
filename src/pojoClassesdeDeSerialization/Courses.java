package pojoClassesdeDeSerialization;

import java.util.List;

public class Courses {

	// Class for nested JSON

	private List<WebAutomation> webAutomation;
	private List<Api> api;
	private List<Mobile> mobile;

	// Need to generate the getters / setters for these variable using "ALT+SHIFT+S"

	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}

	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}

	public List<Api> getApi() {
		return api;
	}

	public void setApi(List<Api> api) {
		this.api = api;
	}

	public List<Mobile> getMobile() {
		return mobile;
	}

	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}

}
