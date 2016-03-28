package com.coolbeanzzz.development.resttests;

import static com.jayway.restassured.RestAssured.given;


import org.junit.Test;

public class MccMncGetTest {

	@Test
	public void testGetMccMnc() {
		given().
			auth().basic("admin", "admin").
		when().
			get("CallFailureEnterprise/rest/mccmncs").
		then().
			statusCode(200);
	}

}
