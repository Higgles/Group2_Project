/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.jee.jaxrs;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.coolbeanzzz.development.entities.ErroneousData;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.services.ErroneousDataService;
import com.coolbeanzzz.development.tools.QueryOptions;
import com.coolbeanzzz.development.tools.QueryPaginationHelper;

@Path("/erroneousdata")
public class ErroneousDataCRUDService{
	

	@Inject
	private ErroneousDataService service;
	
	/**
	 * Gets a resultlist of all data within the Erroneous table
	 * @param draw 
	 * @param start starting row to return
	 * @param length number of rows to return
	 * @param headings whether headings should be returned or not
	 * @param searchTerm search term for narrowing down rows returned
	 * @param orderColumn column to order data by
	 * @param orderDirection direction that column should be ordered
	 * @return list of erroneous results
	 */
    @SuppressWarnings("rawtypes")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getErroneousData(@QueryParam("draw") int draw, 
    		@QueryParam("start") int start, 
    		@QueryParam("length") int length, 
    		@DefaultValue("false") @QueryParam("headings") boolean headings, 
    		@DefaultValue("") @QueryParam("search[value]") String searchTerm, 
    		@DefaultValue("0") @QueryParam("order[0][column]") int orderColumn, 
    		@DefaultValue("asc") @QueryParam("order[0][dir]") String orderDirection) {
    	QueryOptions options = new QueryOptions(draw, start, length, headings, searchTerm, orderColumn, orderDirection);
    	List queryResults = (List) service.getAllResults(options);
    	return QueryPaginationHelper.getQueryResultsAsJSON(queryResults, options);
    }
    
    /**
     * Adds new entry to failure table in database
     * @param newEntry to be added to database
     * @return added failuretable entry
     */
    @POST
	@Consumes(MediaType.APPLICATION_JSON)
	public FailureTable addNewEntry(ErroneousData newEntry) {
    	newEntry.setId(0);
		service.addNewEntry(newEntry);
		return newEntry;
	}
}