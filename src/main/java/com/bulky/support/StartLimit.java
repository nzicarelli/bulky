/**
 * 
 */
package com.bulky.support;

/**
 * @author nando
 *
 */
public class StartLimit {
	private int start = 0;
	private int limit = 1000;
	
	public StartLimit() {
		
	}	
	
	public StartLimit(int start, int limit) {
		this.start = start;
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	

}
