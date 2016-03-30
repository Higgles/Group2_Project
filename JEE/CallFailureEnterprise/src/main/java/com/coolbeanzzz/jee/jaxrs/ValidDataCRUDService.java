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
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getQ1(String[] input) {
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getUniqueEventIdsCauseCodeForPhoneType(input[0], input[1]));
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
    @Path("/CB-4/{imsi}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getQ4(@PathParam("imsi") String imsi) {
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getEventIdsCauseCodeForIMSI(imsi));
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
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultList getCB6(String[] data) {
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getFailCountByPhoneModel(data[0], data[1], data[2], data[3]));
        return baseData;
    }
    
    /**
	 * As a Customer Service Rep, I want to count, for a given IMSI, 
     * the number of failures they have had during a given time period.
	 * @param imsi to be checked
	 * @param dateStart start date for time period
	 * @param dateEnd end date for time period
	 * @return a collection of FailureTable rows from underlying table
	 */	
    @Path("/CB-12")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public ResultList getQ12(String[] data) {    	
    	ResultList baseData = new ResultList();
    	//the next line has hard coded values used for testing
   	baseData.setDataCollection(service.getFailCountByImsiAndDate("344930000000011",
    			"2013-01-10 17:11:00",
    		"2013-01-15 17:11:00"));
    	//baseData.setDataCollection(service.getFailCountByImsiAndDate(data[0], data[1], data[2]));
    	return baseData;
    }
    
	/**
	 * Top 10 Market/Operator/Cell ID combinations that had call failures during a time period
	 * @param dateStart start date for time period
	 * @param dateEnd end date for time period
	 * @return a collection of FailureTable rows from underlying table
	 */
    @Path("/CB-15")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public ResultList getQ15(String[] dates) {    	
    	ResultList baseData = new ResultList();
    	//the next line has hard coded values used for testing
   	baseData.setDataCollection(service.getTop10MarketOperatorCellBetween2Dates("2011-01-10 17:11:00","2015-01-15 17:11:00"));
    	//baseData.setDataCollection(service.getTop10MarketOperatorCellBetween2Dates(dates[0], dates[1]));
    	return baseData;
    }
    
    /**
  	 * As a Support Engineer I want to display, for a given failure Cause Class, the IMSIs that were affected.
  	 * @param Failure Class
  	 * @return a collection of IMSI rows from underlying table
  	 */
    @Path("/CB-19/{failure}")
    @GET
 	@Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
 	public ResultList getQ19(@PathParam("failure") String failure) {    	
 	   	ResultList baseData = new ResultList();
 	   	baseData.setDataCollection(service.getIMSIsforFailureClass(failure));
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
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/failure")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<String> getAllFailures() {
        return service.getAllFailureValues();
    }
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/CB-17/{imsi}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getQ17(@PathParam("imsi") String imsi) {
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getUniqueCauseCodeForIMSI(imsi));
        return baseData;
    }
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/CB-18")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultList getQ18(String[] dates) {    	
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getTop10ImsiListBetween2Dates(dates[0], dates[1]));
    	return baseData;
    }
        
}
