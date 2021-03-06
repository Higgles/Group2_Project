/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.jee.jaxrs;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.entities.ResultList;
import com.coolbeanzzz.development.services.FailureClassService;

@Path("/failureclasses")
public class FailureClassCRUDService {
	

	@Inject
	private FailureClassService service;
	
	/**
	 * Gets all failure class records from the underlying table within the database
	 * @return a list of failure class results
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getFailureClasses() {
        ResultList failureClasses = new ResultList();
        failureClasses.setDataCollection(service.getCatalog());
        return failureClasses;
    }
    
    /**
     * Gets a list of results from a query
     * @return A list of description results
     */
    @Path("/descriptions")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<String> getAllDescriptions(@DefaultValue("1") @QueryParam("page") int page, @DefaultValue("") @QueryParam("term") String searchTerm, @DefaultValue("-1") @QueryParam("pageLimit") int pageLimit) {
        return service.getAllDescriptions(page, searchTerm, pageLimit);
    }

    /**
     * Adds new entry to failure table in database
     * @param newEntry to be added to database
     * @return added failuretable entry
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	public FailureTable addNewEntry(FailureClass newEntry) {
		service.addNewEntry(newEntry);
		return newEntry;
	}
}