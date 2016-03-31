package com.coolbeanzzz.development.resttests;

import static com.jayway.restassured.RestAssured.given;


import org.junit.Test;

public class ValidDataGetTest {

	@Test
	public void testGetValidData() {
		given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/validdata").
	then().
		statusCode(200);
	}

}
