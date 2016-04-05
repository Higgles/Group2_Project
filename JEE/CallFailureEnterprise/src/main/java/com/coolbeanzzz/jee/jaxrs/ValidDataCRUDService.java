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
    	QueryOptions options = new QueryOptions(draw, start, length, headings, searchTerm, orderColumn, orderDirection);
    	List queryResults = (List) service.getUniqueEventIdsCauseCodeForPhoneType(manufacturer, model, options);
    	return this.getQueryResultsAsJSON(queryResults, options);
    }
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/CB-7")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getQ7(@QueryParam("fromdate") String fromdate, 
    		@QueryParam("todate") String todate, 
    		@QueryParam("draw") int draw, 
    		@QueryParam("start") int start, 
    		@QueryParam("length") int length, 
    		@DefaultValue("false") @QueryParam("headings") boolean headings, 
    		@DefaultValue("") @QueryParam("search[value]") String searchTerm, 
    		@DefaultValue("0") @QueryParam("order[0][column]") int orderColumn, 
    		@DefaultValue("asc") @QueryParam("order[0][dir]") String orderDirection) {
    	ResultList baseData = new ResultList();
    	QueryOptions options = new QueryOptions(draw, start, length, headings, searchTerm, orderColumn, orderDirection);
    	List queryResults = (List) service.getNoOfCallFailuresAndDurationForImsiInDateRange(fromdate, todate, options);
    	return this.getQueryResultsAsJSON(queryResults, options);
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
    	QueryOptions options = new QueryOptions(draw, start, length, headings, searchTerm, orderColumn, orderDirection);
    	List queryResults = (List) service.getEventIdsCauseCodeForIMSI(imsi, options);
    	return this.getQueryResultsAsJSON(queryResults, options);
    }

    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/CB-5")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getQ5(@QueryParam("fromdate") String fromdate, 
    		@QueryParam("todate") String todate, 
    		@QueryParam("draw") int draw, 
    		@QueryParam("start") int start, 
    		@QueryParam("length") int length, 
    		@DefaultValue("false") @QueryParam("headings") boolean headings, 
    		@DefaultValue("") @QueryParam("search[value]") String searchTerm, 
    		@DefaultValue("0") @QueryParam("order[0][column]") int orderColumn, 
    		@DefaultValue("asc") @QueryParam("order[0][dir]") String orderDirection) {  
    	QueryOptions options = new QueryOptions(draw, start, length, headings, searchTerm, orderColumn, orderDirection);
    	List queryResults = (List) service.getImsiListBetween2Dates(fromdate, todate, options);
    	return this.getQueryResultsAsJSON(queryResults, options);
    }
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/CB-6")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getCB6(@QueryParam("manufacturer") String manufacturer, 
    		@QueryParam("model") String model,
    		@QueryParam("fromdate") String fromdate, 
    		@QueryParam("todate") String todate, 
    		@QueryParam("draw") int draw, 
    		@QueryParam("start") int start, 
    		@QueryParam("length") int length, 
    		@DefaultValue("false") @QueryParam("headings") boolean headings, 
    		@DefaultValue("") @QueryParam("search[value]") String searchTerm, 
    		@DefaultValue("0") @QueryParam("order[0][column]") int orderColumn, 
    		@DefaultValue("asc") @QueryParam("order[0][dir]") String orderDirection) {        
        QueryOptions options = new QueryOptions(draw, start, length, headings, searchTerm, orderColumn, orderDirection);
    	List queryResults = (List) service.getFailCountByPhoneModel(manufacturer, model, fromdate, todate, options);
    	return this.getQueryResultsAsJSON(queryResults, options);
    }
    
    /**
	 * Gets a list of results from a query
	 * @return A list of Base data results
	 */	
    @Path("/CB-12")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getQ12(@QueryParam("imsi") String imsi,
    		@QueryParam("fromdate") String fromdate, 
    		@QueryParam("todate") String todate, 
    		@QueryParam("draw") int draw, 
    		@QueryParam("start") int start, 
    		@QueryParam("length") int length, 
    		@DefaultValue("false") @QueryParam("headings") boolean headings, 
    		@DefaultValue("") @QueryParam("search[value]") String searchTerm, 
    		@DefaultValue("0") @QueryParam("order[0][column]") int orderColumn, 
    		@DefaultValue("asc") @QueryParam("order[0][dir]") String orderDirection) {    	
    	QueryOptions options = new QueryOptions(draw, start, length, headings, searchTerm, orderColumn, orderDirection);
    	List queryResults = (List) service.getFailCountByImsiAndDate(imsi, fromdate, todate, options);
    	return this.getQueryResultsAsJSON(queryResults, options);
    }
    
  
    /**
   	 * Gets a list of results from a query
   	 * @return A list of Base data results
   	 */	
    @Path("/CB-15")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getQ15(@QueryParam("fromdate") String fromdate, 
    		@QueryParam("todate") String todate, 
    		@QueryParam("draw") int draw, 
    		@QueryParam("start") int start, 
    		@QueryParam("length") int length, 
    		@DefaultValue("false") @QueryParam("headings") boolean headings, 
    		@DefaultValue("") @QueryParam("search[value]") String searchTerm, 
    		@DefaultValue("0") @QueryParam("order[0][column]") int orderColumn, 
    		@DefaultValue("asc") @QueryParam("order[0][dir]") String orderDirection) {    	    	
    	QueryOptions options = new QueryOptions(draw, start, length, headings, searchTerm, orderColumn, orderDirection);
    	List queryResults = (List) service.getTop10MarketOperatorCellBetween2Dates(fromdate, todate, options);
    	return this.getQueryResultsAsJSON(queryResults, options);
    }
        
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/CB-19/{failure}")
    @GET
 	@Produces(MediaType.APPLICATION_JSON)
 	public JSONObject getQ19(@PathParam("failure") String failure,
 			@QueryParam("draw") int draw, 
    		@QueryParam("start") int start, 
    		@QueryParam("length") int length, 
    		@DefaultValue("false") @QueryParam("headings") boolean headings, 
    		@DefaultValue("") @QueryParam("search[value]") String searchTerm, 
    		@DefaultValue("0") @QueryParam("order[0][column]") int orderColumn, 
    		@DefaultValue("asc") @QueryParam("order[0][dir]") String orderDirection) {    	
 	   	QueryOptions options = new QueryOptions(draw, start, length, headings, searchTerm, orderColumn, orderDirection);
    	List queryResults = (List) service.getIMSIsforFailureClass(failure, options);
    	return this.getQueryResultsAsJSON(queryResults, options);
 	}
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/CB-17/{imsi}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getQ17(@PathParam("imsi") String imsi,
    		@QueryParam("draw") int draw, 
    		@QueryParam("start") int start, 
    		@QueryParam("length") int length, 
    		@DefaultValue("false") @QueryParam("headings") boolean headings, 
    		@DefaultValue("") @QueryParam("search[value]") String searchTerm, 
    		@DefaultValue("0") @QueryParam("order[0][column]") int orderColumn, 
    		@DefaultValue("asc") @QueryParam("order[0][dir]") String orderDirection) {        
        QueryOptions options = new QueryOptions(draw, start, length, headings, searchTerm, orderColumn, orderDirection);
    	List queryResults = (List) service.getUniqueCauseCodeForIMSI(imsi, options);
    	return this.getQueryResultsAsJSON(queryResults, options);
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
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private JSONObject getQueryResultsAsJSON(List queryResults, QueryOptions options){
    	JSONObject result;
    	HashMap res = new HashMap();
    	res.put("draw", options.getDraw());
    	int total = (int) queryResults.remove(1);
    	if(!options.isHeadings())
    		queryResults.remove(0);
    	res.put("recordsTotal", total);
    	res.put("recordsFiltered", total);
    	res.put("data", queryResults);
    	result = new JSONObject(res);
        return result;
    }
}
