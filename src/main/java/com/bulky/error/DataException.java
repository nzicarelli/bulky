/**
 * 
 */
package com.bulky.error;

/**
 * @author kaala
 *
 */
public class DataException extends Exception{

	
	
	private static final long serialVersionUID = 1L;
	
	
	public DataException(Exception e) {
		super(e);
	}
	
	public DataException(String msg) {
		super(msg);
	}


}
