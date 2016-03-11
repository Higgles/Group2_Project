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

import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.entities.MccMnc;
import com.coolbeanzzz.development.entities.ResultList;
import com.coolbeanzzz.development.services.MccMncService;

@Path("/mccmncs")
public class MccMncCRUDService {
	

	@Inject
	private MccMncService service;
	
	/**
	 * Gets a list of MccMnc records from the MccMnc table within the database
	 * @return a list of MccMnc records
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getMccMncs() {
    	ResultList mccMncs = new ResultList();
    	mccMncs.setDataCollection(service.getCatalog());
        return mccMncs;
    }
    
    /**
     * Adds new entry to failure table in database
     * @param newEntry to be added to database
     * @return added failuretable entry
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	public FailureTable addNewEntry(MccMnc newEntry) {
		service.addNewEntry(newEntry);
		return newEntry;
	}

}