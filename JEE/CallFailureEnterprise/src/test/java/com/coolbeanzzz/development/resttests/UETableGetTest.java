package com.coolbeanzzz.development.resttests;

import static com.jayway.restassured.RestAssured.given;


import org.junit.Test;

public class UETableGetTest {

	@Test
	public void testGetUETable() {
	given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/uetables").
	then().
		statusCode(200);
	}

}
