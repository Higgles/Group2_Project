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

import com.coolbeanzzz.development.entities.EventCause;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.entities.ResultList;
import com.coolbeanzzz.development.services.EventCauseService;

@Path("/eventcauses")
public class EventCauseCRUDService {
	

	@Inject
	private EventCauseService service;
	
	/**
	 * Gets all eventcause records within the EventCause table of the database
	 * @return a list of results
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getEventCauses() {
        ResultList eventCauses = new ResultList();
        eventCauses.setDataCollection(service.getCatalog());
        return eventCauses;
    }
    
    /**
     * Adds new entry to failure table in database
     * @param newEntry to be added to database
     * @return added failuretable entry
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	public FailureTable addNewEntry(EventCause newEntry) {
		service.addNewEntry(newEntry);
		return newEntry;
	}
}