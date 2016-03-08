/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.jee.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.coolbeanzzz.development.entities.BaseDataList;
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
    	ResultList baseData = new BaseDataList();
    	baseData.setDataCollection(service.getCatalog());
        return baseData;
    }
    
    /**
     * Gets a list of results from a query
     * @return A list of Base data results
     */
    @Path("/q1")
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
    @Path("/q5")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getQ5() {
    	String date1 = "2013-01-11 17:15:00";
    	String date2 = "2013-01-11 17:16:45";
    	ResultList baseData = new ResultList();
    	baseData.setDataCollection(service.getImsiListBetween2Dates(date1,date2));
    	return baseData;
    }

}