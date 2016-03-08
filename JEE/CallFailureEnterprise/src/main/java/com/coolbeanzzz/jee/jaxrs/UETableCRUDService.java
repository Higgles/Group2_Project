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

}