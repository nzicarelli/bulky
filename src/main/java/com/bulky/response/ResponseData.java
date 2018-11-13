/**
 * 
 */
package com.bulky.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bulky.support.web.Message;

/**
 * @author kaala
 *
 */
public class ResponseData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private boolean success = false;
	private String msg = null;
	private Message message = null;
	private List<?> output = new ArrayList<>();
	
	public ResponseData(boolean success, String message,List<?> output,
			Throwable tr, Message detail) {
		 this.success = success;
		 this.output = output;
		 this.message = detail;
		 this.msg = message;
	}
	
	public ResponseData() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	

	public List<?> getOutput() {
		return output;
	}

	public void setOutput(List<?> output) {
		this.output = output;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	
	
	
}
