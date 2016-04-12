/**
 * @author CoolBeanzzz
 */
package com.coolbeanzzz.development.tools;

public class QueryOptions {

	private int draw; 
	private int start; 
	private int length; 
	private boolean headings; 
	private String searchTerm; 
	private int orderColumn; 
	private String orderDirection;
	
	public QueryOptions(int draw, int start, int length, boolean headings,
			String searchTerm, int orderColumn, String orderDirection) {
		this.draw = draw;
		this.start = start;
		this.length = length;
		this.headings = headings;
		this.searchTerm = searchTerm;
		this.orderColumn = orderColumn;
		this.orderDirection = orderDirection;
	}
	
	/**
	 * Get draw number of query to be used for ordering pagination queries
	 * @return draw number
	 */
	public int getDraw() {
		return draw;
	}
	
	/**
	 * Sets the draw value of the query
	 * @param draw value to set the query to
	 */
	public void setDraw(int draw) {
		this.draw = draw;
	}
	
	/**
	 * Gets the starting record to be returned to the user
	 * @return the starting record of the query
	 */
	public int getStart() {
		return start;
	}
	
	/**
	 * Sets the starting record number to be returned to the user
	 * @param start record number to be returned to user
	 */
	public void setStart(int start) {
		this.start = start;
	}
	
	/**
	 * Gets the maximum number of records which will be returned to the user
	 * @return the number of records which will be shown to the user
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * Sets the maximum number of records which will be shown to the user
	 * @param length 
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	/**
	 * Gets whether the query headings will be returned with the query results
	 * @return headings value
	 */
	public boolean isHeadings() {
		return headings;
	}
	
	/**
	 * Sets whether the query headings will be returned with the query results
	 * @param headings new value of headings variable
	 */
	public void setHeadings(boolean headings) {
		this.headings = headings;
	}
	
	/**
	 * Gets the query searchterm
	 * @return query searchterm
	 */
	public String getSearchTerm() {
		return searchTerm;
	}
	
	/**
	 * Sets the query searchterm
	 * @param searchTerm
	 */
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	/**
	 * Gets the column number which the results will be ordered by
	 * @return order column number
	 */
	public int getOrderColumn() {
		return orderColumn;
	}
	
	/**
	 * Sets the column number which the query results will be ordered by
	 * @param orderColumn
	 */
	public void setOrderColumn(int orderColumn) {
		this.orderColumn = orderColumn;
	}
	
	/**
	 * Gets the direction which the query results will be ordered
	 * @return query order direction
	 */
	public String getOrderDirection() {
		return orderDirection;
	}
	
	/**
	 * Sets query order direction
	 * @param orderDirection
	 */
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
}
