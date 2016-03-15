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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coolbeanzzz.development.entities.BaseData;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.entities.ResultList;
import com.coolbeanzzz.development.services.BaseDataService;

@Path("/validdata")
public class ValidDataCRUDService {
	

	@Inject
	private BaseDataService service;
	
	/**
	 * Gets a list of base data records from the table within the database
	 * @return
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getBaseData() {
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getCatalog());
        return baseData;
    }
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/CB-8")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getQ1() {
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getUniqueEventIdsCauseCodeForPhoneType(21060800));
        return baseData;
    }
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/CB-7")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultList getQ7(String[] dates) {
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getNoOfCallFailuresAndDurationForImsiInDateRange(dates[0], dates[1]));
    	return baseData;
    }
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/CB-4")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getQ4() {
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getEventIdsCauseCodeForIMSI("240210000000013"));
        return baseData;
    }

    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/CB-5")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultList getQ5(String[] dates) {    	
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getImsiListBetween2Dates(dates[0], dates[1]));
    	return baseData;
    }
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/CB-6")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getCB6() {
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getFailCountByPhoneModel(21060800, "2013-01-11 17:15:00", "2013-01-11 17:26:00"));
        return baseData;
    }
    
    /**
     * Adds new entry to failure table in database
     * @param newEntry to be added to database
     * @return added failuretable entry
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	public FailureTable addNewEntry(BaseData newEntry) {
    	newEntry.setId(0);
		service.addNewEntry(newEntry);
		return newEntry;
	}
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/imsis")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<String> getAllImsis() {
        return service.getAllImsiValues();
    }

}
