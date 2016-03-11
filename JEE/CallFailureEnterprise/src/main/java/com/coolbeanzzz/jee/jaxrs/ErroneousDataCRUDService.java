/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.jee.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coolbeanzzz.development.entities.ErroneousData;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.entities.ResultList;
import com.coolbeanzzz.development.services.ErroneousDataService;

@Path("/erroneousdata")
public class ErroneousDataCRUDService {
	

	@Inject
	private ErroneousDataService service;
	
	/**
	 * Gets a resultlist of all data within the Erroneous table
	 * @return list of erroneous results
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getErroneousData() {
    	ResultList erroneousData = new ResultList();
    	erroneousData.setDataCollection(service.getCatalog());
        return erroneousData;
    }
    
    /**
     * Adds new entry to failure table in database
     * @param newEntry to be added to database
     * @return added failuretable entry
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	public FailureTable addNewEntry(ErroneousData newEntry) {
    	newEntry.setId(0);
		service.addNewEntry(newEntry);
		return newEntry;
	}
}