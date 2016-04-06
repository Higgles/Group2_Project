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

	@Test
	public void testGetUniqueEventIdsCauseCodeForPhoneType() {
		given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/validdata/CB-8?manufacturer=Alcatel%20Radiotelephone%20(LAVAL)&model=9109%20PA").
	then().
		statusCode(200);
	}
	
	@Test
	public void testGetNoOfCallFailuresAndDurationForImsiInDateRange() {
		given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/validdata/CB-7?fromdate=2010-04-06%2023:22&todate=2016-04-06%2023:22").
	then().
		statusCode(200);
	}
	
	@Test
	public void testGetEventIdsCauseCodeForIMSI() {
		given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/validdata/CB-4/344930000000011").
	then().
		statusCode(200);
	}
	
	@Test
	public void testGetImsiListBetween2Dates() {
		given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/validdata/CB-5?fromdate=2010-04-06%2023:22&todate=2016-04-06%2023:22").
	then().
		statusCode(200);
	}
	
	@Test
	public void testGetFailCountByPhoneModel() {
		given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/validdata/CB-6?manufacturer=Alcatel%20Radiotelephone%20(LAVAL)&model=9109%20PA&fromdate=2010-04-06%2023:22&todate=2016-04-06%2023:22").
	then().
		statusCode(200);
	}
	
	@Test
	public void testGetFailCountByImsiAndDate() {
		given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/validdata/CB-12?imsi=344930000000011&fromdate=2010-04-06%2023:22&todate=2016-04-06%2023:22").
	then().
		statusCode(200);
	}
	
	@Test
	public void testGetTop10MarketOperatorCellBetween2Dates() {
		given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/validdata/CB-15?fromdate=2010-04-06%2023:22&todate=2016-04-06%2023:22").
	then().
		statusCode(200);
	}
	
	@Test
	public void testGetIMSIsforFailureClass() {
		given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/validdata/CB-19/EMERGENCY").
	then().
		statusCode(200);
	}
	
	@Test
	public void testGetUniqueCauseCodeForIMSI() {
		given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/validdata/CB-17/344930000000011").
	then().
		statusCode(200);
	}
	
	@Test
	public void testGetTop10ImsiListBetween2Dates() {
		given().
		auth().basic("admin", "admin").
	when().
		get("CallFailureEnterprise/rest/validdata/CB-18?fromdate=2010-04-06%2023:22&todate=2016-04-06%2023:22").
	then().
		statusCode(200);
	}
}
