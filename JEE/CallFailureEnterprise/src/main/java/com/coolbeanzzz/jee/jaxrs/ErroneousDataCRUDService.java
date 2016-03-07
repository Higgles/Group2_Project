/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.jee.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coolbeanzzz.development.entities.ResultList;
import com.coolbeanzzz.development.services.ErroneousDataService;

@Path("/erroneousdata")
public class ErroneousDataCRUDService {
	

	@Inject
	private ErroneousDataService service;
	
	/**
	 * Gets a resultlis of all data within the Erroneous table
	 * @return list of erroneous results
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getErroneousData() {
    	ResultList erroneousData = new ResultList();
    	erroneousData.setDataCollection(service.getCatalog());
        return erroneousData;
    }
}