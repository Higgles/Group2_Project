package com.coolbeanzzz.development.resttests;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class UsersGetTest {
	
	@Test
	public void testGetUsers() {
	given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/users").
	then().
		statusCode(200);	
	}
}
