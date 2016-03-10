package com.coolbeanzzz.jee.jaxrs;



import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


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
