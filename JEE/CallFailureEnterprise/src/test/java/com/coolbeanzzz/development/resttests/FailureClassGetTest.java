package com.coolbeanzzz.development.resttests;

import static com.jayway.restassured.RestAssured.given;


import org.junit.Test;

public class FailureClassGetTest {

	@Test
	public void testGetFailureClass() {
		given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/failureclasses").
	then().
		statusCode(200);
	}

}
