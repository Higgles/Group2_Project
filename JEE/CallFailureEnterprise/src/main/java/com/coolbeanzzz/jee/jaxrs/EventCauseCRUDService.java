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
}