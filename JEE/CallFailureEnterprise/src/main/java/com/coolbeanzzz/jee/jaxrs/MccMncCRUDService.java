package com.coolbeanzzz.jee.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coolbeanzzz.development.entities.ResultList;
import com.coolbeanzzz.development.services.MccMncService;

@Path("/mccmncs")
public class MccMncCRUDService {
	

	@Inject
	private MccMncService service;
	

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getMccMncs() {
    	ResultList mccMncs = new ResultList();
    	mccMncs.setDataCollection(service.getCatalog());
        return mccMncs;
    }

}