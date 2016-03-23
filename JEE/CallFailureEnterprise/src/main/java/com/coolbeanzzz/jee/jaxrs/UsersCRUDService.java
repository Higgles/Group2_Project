package com.coolbeanzzz.jee.jaxrs;

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
import javax.ws.rs.PathParam;
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
	
	/**
	 * Get list of users
	 * @return UsersList list of all users
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UsersList getUsers() {
    	UsersList users = new UsersList();
    	users.setUsersCollection(service.getAllUsers());  
        return users;
    }
    
    /**
     * Check logged in user's role and return a response to the user page
     * @return response user page
     * @throws URISyntaxException
     */
    @GET
    @Path("/userCheck")
    public Response checkRole() throws URISyntaxException{
    	java.net.URI location = null;
    	Subject currentUser = SecurityUtils.getSubject();
    	if(currentUser.hasRole("SysAd")){
    		location = new java.net.URI("http://localhost:8080/CallFailureEnterprise/admin/upload.jsp");
    	}
    	else{
    		location = new java.net.URI("http://localhost:8080/CallFailureEnterprise/query.jsp");
    	}    	
    	
    	return Response.temporaryRedirect(location).build();
    }
    
    /**
     * Get username and role of current logged in user
     * @return collection with username and role
     */
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
    	else if(currentUser.hasRole("NetManEng")){
    		role = "Network Management Engineer";
    	}
    	else if(currentUser.hasRole("SupEng")){
    		role = "Support Engineer";
    	}
    	else if(currentUser.hasRole("CSR")){
    		role = "Customer Support Representative";
    	}
    	else if(currentUser.hasRole("CSR")){
    		role = "Customer Service Representative";
    	}
    	else if(currentUser.hasRole("NetManEng")){
    		role = "Network Management Engineer";
    	}
    	
    	user.add(username);
    	user.add(role);
    	
    	return user;
    }
    
    /**
     * Add user to system
     * @param user
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addUser(Users user) {
		user.setId(0);
		service.addUser(user);
	}

   
    
    @DELETE
    @Path("/{username}")
	public void removeUser(@PathParam("username") String username) {
		service.removeUser(username);
	}
    
    /**
     * Update user details
     * @param updatedUser
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(Users updatedUser) {
		service.updateUser(updatedUser);
	}
    
}
