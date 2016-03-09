/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.jee.jaxrs;

import java.math.BigInteger;

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
    @Path("/q2")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResultList getQ2() {
    	ResultList baseData = new ResultList();
//    	BigInteger big = new BigInteger("344930000000011");
    	baseData.setDataCollection(service.getEventIdsCauseCodeForIMSI("240210000000013"));
        return baseData;
    }

}