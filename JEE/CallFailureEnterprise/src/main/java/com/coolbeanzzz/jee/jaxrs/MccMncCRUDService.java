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

import com.coolbeanzzz.development.entities.MccMncList;
import com.coolbeanzzz.development.services.MccMncService;

@Path("/mccmncs")
public class MccMncCRUDService {
	

	@Inject
	private MccMncService service;
	

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MccMncList getFailureClasses() {
    	MccMncList mccMncs = new MccMncList();
    	mccMncs.setMccMncCollection(service.getCatalog());
        return mccMncs;
    }

}