/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.jee.jaxrs;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.entities.ResultList;
import com.coolbeanzzz.development.entities.UETable;
import com.coolbeanzzz.development.services.UETableService;

@Path("/uetables")
public class UETableCRUDService {
	

	@Inject
	private UETableService service;
	
	/**
	 * Gets a list of UETable records from the table within the database
	 * @return a list of ueTable results
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getUETableClasses() {
    	ResultList ueTables = new ResultList();
    	ueTables.setDataCollection(service.getCatalog());
        return ueTables;
    }
    
    /**
	 * Gets a list of UETable records from the table within the database
	 * @return a list of ueTable results
	 */
    @GET
    @Path("/manufacturers")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<String> getUeTableManufacturers() {
    	return service.getAllManufacturers();
    }
    
    /**
	 * Gets a list of models relating to the entered manufacturer from the database
	 * @return a list of string models
	 */
    @GET
    @Path("/models/{manufacturer}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<String> getUeTableModels(@PathParam("manufacturer") String manufacturer) {
    	return service.getModelsForManufacturer(manufacturer);
    }
    
    /**
	 * Gets a list of tac values relating to the entered manufacturer and model from the database
	 * @return a list of integer tacs
	 */
    @POST
    @Path("/uetypes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<Integer> getUeTypes(String[] data) {
    	return service.getUETypes(data[0], data[1]);
    }
    
    /**
     * Adds new entry to failure table in database
     * @param newEntry to be added to database
     * @return added failuretable entry
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	public FailureTable addNewEntry(UETable newEntry) {
		service.addNewEntry(newEntry);
		return newEntry;
	}

}