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


//    @GET
//	@Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//	public CompactDisc getCompactDisc(@PathParam("id") int id) {
//		return library.get(id);
//	}

}