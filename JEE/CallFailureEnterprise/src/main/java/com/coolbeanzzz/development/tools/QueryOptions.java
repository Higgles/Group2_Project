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

	public int getDraw() {
		return draw;
	}
	
	public void setDraw(int draw) {
		this.draw = draw;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public boolean isHeadings() {
		return headings;
	}
	
	public void setHeadings(boolean headings) {
		this.headings = headings;
	}
	
	public String getSearchTerm() {
		return searchTerm;
	}
	
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	public int getOrderColumn() {
		return orderColumn;
	}
	
	public void setOrderColumn(int orderColumn) {
		this.orderColumn = orderColumn;
	}
	
	public String getOrderDirection() {
		return orderDirection;
	}
	
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
}
