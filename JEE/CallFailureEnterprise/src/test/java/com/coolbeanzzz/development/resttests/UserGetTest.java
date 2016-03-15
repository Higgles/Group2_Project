package com.coolbeanzzz.development.resttests;

import static org.junit.Assert.*;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hamcrest.Matcher;
import org.junit.Test;

import com.coolbeanzzz.development.entities.UsersList;
import static com.jayway.restassured.RestAssured.*;

public class UserGetTest {

	@Test
	public void testGetUsers() {
		
//			given().
//		        contentType("application/json").
//		    when().
//		        get("http://localhost:8080/CallFailureEnterprise/rest/users").
//		    then().
//		        statusCode(200);
		
		
        expect().
        	statusCode(200).
        when().
        	get("http://localhost:8080/CallFailureEnterprise/rest/users");
			
	}
	
}
//	
//		  expect().
//		  statusCode(200).when().get("http://localhost:8080/CallFailureEnterprise/rest/users");//testGetUsers();
//		  String json = res.toString();
//		  JsonPath jp = new JsonPath(json);
//		  assertEquals("SysAd", jp.get("userType"));
//		  assertEquals("Admin", jp.get("userName"));
//		  assertEquals("Admin", jp.get("passKey"));
//		  assertEquals("1", jp.get("id"));
//		  return res;
//		 
		

