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

import com.coolbeanzzz.development.entities.EventCauseList;
import com.coolbeanzzz.development.services.EventCauseService;

@Path("/eventcauses")
public class EventCauseCRUDService {
	

	@Inject
	private EventCauseService service;
	

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EventCauseList getEventCauses() {
        EventCauseList eventCauses = new EventCauseList();
        eventCauses.setEventCauseCollection(service.getCatalog());
        return eventCauses;
    }

}