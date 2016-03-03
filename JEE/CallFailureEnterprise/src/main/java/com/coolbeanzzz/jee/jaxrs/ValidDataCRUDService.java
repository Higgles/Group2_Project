package com.coolbeanzzz.jee.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coolbeanzzz.development.entities.BaseDataList;
import com.coolbeanzzz.development.entities.ResultList;
import com.coolbeanzzz.development.services.BaseDataService;

@Path("/validdata")
public class ValidDataCRUDService {
	

	@Inject
	private BaseDataService service;
	

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getBaseData() {
    	ResultList baseData = new BaseDataList();
    	baseData.setDataCollection(service.getCatalog());
        return baseData;
    }

}