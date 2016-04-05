/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.jee.jaxrs;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.coolbeanzzz.development.entities.BaseData;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.entities.ResultList;
import com.coolbeanzzz.development.services.BaseDataService;
import com.coolbeanzzz.development.tools.QueryOptions;

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
    public JSONObject getQ1(@QueryParam("manufacturer") String manufacturer, 
    		@QueryParam("model") String model,
    		@QueryParam("draw") int draw, 
    		@QueryParam("start") int start, 
    		@QueryParam("length") int length, 
    		@DefaultValue("false") @QueryParam("headings") boolean headings, 
    		@DefaultValue("") @QueryParam("search[value]") String searchTerm, 
    		@DefaultValue("0") @QueryParam("order[0][column]") int orderColumn, 
    		@DefaultValue("asc") @QueryParam("order[0][dir]") String orderDirection) {
    	ResultList baseData = new ResultList();
    	QueryOptions options = new QueryOptions(draw, start, length, headings, searchTerm, orderColumn, orderDirection);
    	List queryResults = (List) service.getUniqueEventIdsCauseCodeForPhoneType(manufacturer, model, options);
    	JSONObject result;
    	HashMap res = new HashMap();
    	res.put("draw", draw);
    	int total = (int) queryResults.remove(1);
    	if(!headings)
    		queryResults.remove(0);
    	res.put("recordsTotal", total);
    	res.put("recordsFiltered", total);
    	res.put("data", queryResults);
    	result = new JSONObject(res);
        return result;
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
    @SuppressWarnings("unchecked")
	@Path("/CB-4/{imsi}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getQ4(@PathParam("imsi") String imsi, @QueryParam("draw") int draw, 
    		@QueryParam("start") int start, @QueryParam("length") int length, 
    		@DefaultValue("false") @QueryParam("headings") boolean headings, 
    		@DefaultValue("") @QueryParam("search[value]") String searchTerm, 
    		@DefaultValue("0") @QueryParam("order[0][column]") int orderColumn, 
    		@DefaultValue("asc") @QueryParam("order[0][dir]") String orderDirection) {
    	List queryResults = (List) service.getEventIdsCauseCodeForIMSI(imsi, start, length, searchTerm, orderColumn, orderDirection);
    	JSONObject result;
    	HashMap res = new HashMap();
    	res.put("draw", draw);
    	long total = (long) queryResults.remove(1);
    	if(!headings)
    		queryResults.remove(0);
    	res.put("recordsTotal", total);
    	res.put("recordsFiltered", total);
    	res.put("data", queryResults);
    	result = new JSONObject(res);
        return result;
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
	 * Gets a list of results from a query
	 * @return A list of Base data results
	 */	
    @Path("/CB-12")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultList getQ12(String[] data) {    	
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getFailCountByImsiAndDate(data[0], data[1], data[2]));
    	return baseData;
    }
    
  
    /**
   	 * Gets a list of results from a query
   	 * @return A list of Base data results
   	 */	
    @Path("/CB-15")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultList getQ15(String[] dates) {    	
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getTop10MarketOperatorCellBetween2Dates(dates[0], dates[1]));
    	return baseData;
    }
        
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
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
    //@Consumes(MediaType.APPLICATION_JSON)
    public Collection<String> getAllImsis(@DefaultValue("1") @QueryParam("page") int page, @DefaultValue("") @QueryParam("term") String searchTerm, @DefaultValue("-1") @QueryParam("pageLimit") int pageLimit) {
        return service.getAllImsiValues(page, searchTerm, pageLimit);
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
