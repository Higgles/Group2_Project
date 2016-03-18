package com.coolbeanzzz.development.resttests;

import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.*;

import org.junit.Test;

public class ErroneousGetTest {

	@Test
	public void testGetErroneousData() {
		given().
			auth().basic("admin", "admin").
		when().
			get("http://localhost:8080/CallFailureEnterprise/rest/erroneousdata").
		then().
			statusCode(200);
	}

}
