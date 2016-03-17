//package com.coolbeanzzz.development.resttests;
//
//import static org.junit.Assert.*;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.junit.Test;
//
//import com.coolbeanzzz.development.entities.UsersList;
//import com.jayway.restassured.path.json.JsonPath;
//import com.jayway.restassured.*;
//
//public class UserRestTest {
//
//	@Test
//	public Response testGetUsers() {
//		
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
//		}
//	}
//
//
