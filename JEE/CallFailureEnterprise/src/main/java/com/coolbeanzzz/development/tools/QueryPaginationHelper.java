/**
 * @author CoolBeanzzz
 */
package com.coolbeanzzz.development.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import com.coolbeanzzz.development.tools.QueryOptions;

public class QueryPaginationHelper {
	
	/**
	 * Reduces query results down to the number needed for pagination
	 * @param originalResults Original list of results
	 * @param options query options dictating number of records to return
	 * @param headings Array of headings to be placed at the top of a resulting table
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getQueryResultList(List originalResults, QueryOptions options, String[] headings){
		int resultSize = originalResults.size();
		List resultList = new ArrayList();
		for(int i = options.getStart();i< options.getStart()+options.getLength() && i< resultSize; i++){
			resultList.add(originalResults.get(i));
		}
		
		resultList.add(0, resultSize);
		resultList.add(0, headings);
		return resultList;
	}
	
	/**
	 * Packages query results into json object for transit back to browser
	 * @param queryResults list of query results to be sent to browser
	 * @param options query options to be sent back to browser
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JSONObject getQueryResultsAsJSON(List queryResults, QueryOptions options){
    	JSONObject result;
    	HashMap resultsMap = new HashMap();
    	resultsMap.put("draw", options.getDraw());
    	int total = (int) queryResults.remove(1);
    	if(!options.isHeadings())
    		queryResults.remove(0);
    	resultsMap.put("recordsTotal", total);
    	resultsMap.put("recordsFiltered", total);
    	resultsMap.put("data", queryResults);
    	result = new JSONObject(resultsMap);
        return result;
    }
}
