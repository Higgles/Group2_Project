package com.coolbeanzzz.development.resttests;

import static com.jayway.restassured.RestAssured.*;

import org.junit.Test;

public class ErroneousGetTest {

	@Test
	public void testGetErroneousData() {
		given().
			auth().basic("admin", "admin").
		when().
			get("CallFailureEnterprise/rest/erroneousdata").
		then().
			statusCode(200);
	}

}
