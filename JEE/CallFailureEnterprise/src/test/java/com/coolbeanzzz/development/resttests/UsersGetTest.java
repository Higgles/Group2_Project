package com.coolbeanzzz.development.resttests;

import org.jboss.arquillian.junit.InSequence;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class UsersGetTest {
	
	@Test
	public void testGetUsers() {
	given().
		auth().basic("admin", "admin").
	when().
		get("http://localhost:8080/CallFailureEnterprise/rest/users").
	then().
		statusCode(200);	
	}
}
