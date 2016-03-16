package com.coolbeanzzz.development.resttests;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

public class EventCauseGetTest {

	@Test
	public void testGetEventCause() {
		given().
			auth().basic("admin", "admin").
		when().
			get("http://localhost:8080/CallFailureEnterprise/rest/eventcauses").
		then().
			statusCode(200);
	}
}
