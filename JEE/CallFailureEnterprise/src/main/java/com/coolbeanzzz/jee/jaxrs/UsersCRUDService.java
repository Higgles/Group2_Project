package com.coolbeanzzz.jee.jaxrs;



import java.awt.List;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.coolbeanzzz.development.entities.Users;
import com.coolbeanzzz.development.entities.UsersList;
import com.coolbeanzzz.development.services.UsersService;

@Path("/users")
public class UsersCRUDService {


	@Inject
	private UsersService service;
	

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UsersList getUsers() {
    	UsersList users = new UsersList();
    	users.setUsersCollection(service.getAllUsers());  
        return users;
    }
    
    @POST
    @Path("/login")
   	@Consumes(MediaType.APPLICATION_JSON)
   	public String checkUser() {
    	
    	return "hello";
   	}
    
    @GET
    @Path("/userCheck")
    public Response checkRole() throws URISyntaxException{
    	java.net.URI location = null;
    	Subject currentUser = SecurityUtils.getSubject();
    	if(currentUser.hasRole("SysAd")){
    		location = new java.net.URI("http://localhost:8080/CallFailureEnterprise/upload.html");
    	}
    	else if(currentUser.hasRole("SupEng")){
    		location = new java.net.URI("http://localhost:8080/CallFailureEnterprise/variable.html");
    	}
    	return Response.temporaryRedirect(location).build();
    }
    
    @GET
    @Path("/currentUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<String> currentUser(){
    	Subject currentUser = SecurityUtils.getSubject();
    	ArrayList<String> user = new ArrayList<String>();
    	String role = "";
    	String username = "";
    	
    	username = currentUser.getPrincipal().toString();
    	
    	if(currentUser.hasRole("SysAd")){
    		role = "System Administrator";
    	}
    	else if(currentUser.hasRole("SupEng")){
    		role = "Support Engineer";
    	}
    	
    	user.add(username);
    	user.add(role);
    	
    	return user;
    }
    
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Users addUsers(Users user) {
		user.setId(0); // make sure the ID is not set
		service.addUser(user);
		return user;
	}

//    @DELETE
//    @Path("/{id}")
//	public void removeUser(@PathParam("id") Users user) {
//		service.removeUser(user);
//	}
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(Users updatedUser) {
		service.updateUserUsingId(updatedUser.getId());  
	}
    
}
