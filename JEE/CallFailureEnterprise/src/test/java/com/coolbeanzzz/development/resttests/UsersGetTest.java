package com.coolbeanzzz.development.resttests;

import org.jboss.arquillian.junit.InSequence;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class UsersGetTest {

	@Test
	@InSequence(1)
	public void testGetUsersWithoutLogin() {
	given().
		auth().basic("k", "k"). //remove login credentials
	when().
		get("http://localhost:8080/CallFailureEnterprise/rest/users").
	then().
		statusCode(401);	//unauthorized
	}
	
	@Test
	@InSequence(2)
	public void testGetUsers() {
	given().
		auth().basic("admin", "admin").
	when().
		get("http://localhost:8080/CallFailureEnterprise/rest/users").
	then().
		statusCode(200);	
	}
}
