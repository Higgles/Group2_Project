package com.coolbeanzzz.development.resttests;

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

	@Test
	public void testGetUsersWithoutLogin() {
	given().
		auth().basic("", ""). //remove login credentials
	when().
		get("http://localhost:8080/CallFailureEnterprise/rest/users").
	then().
		statusCode(401);	//unauthorized
	}
	
	@Test
	public void testGetUsersAgainstPOST() {
	given().
		auth().basic("admin", "admin").
	when().
		get("http://localhost:8080/CallFailureEnterprise/rest/users/login").
	then().
		statusCode(405);	//Method not allowed
	}
	
	

}
