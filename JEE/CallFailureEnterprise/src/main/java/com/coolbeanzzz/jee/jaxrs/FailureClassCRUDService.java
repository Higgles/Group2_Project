package com.coolbeanzzz.jee.jaxrs;

import java.util.*;

import javax.ejb.EJB;
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

import com.coolbeanzzz.development.entities.FailureClassList;
import com.coolbeanzzz.development.services.FailureClassService;

@Path("/failureclasses")
public class FailureClassCRUDService {
	

	@Inject
	private FailureClassService service;
	

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FailureClassList getFailureClasses() {
        FailureClassList failureClasses = new FailureClassList();
        failureClasses.setFailureClassCollection(service.getCatalog());
        return failureClasses;
    }



//    @GET
//	@Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//	public CompactDisc getCompactDisc(@PathParam("id") int id) {
//		return library.get(id);
//	}

}