/**
 * 
 */
package com.bulky.response;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.bulky.support.web.Message;

/**
 * @author kaala
 *
 */
@Service
public class ResponseBuilder {
	
	@Autowired
	private MessageSource messageSource;
	
	public ResponseData userAlreadyExists(String email, Locale locale) {
		Object [] data = {email};
		String msgKey = "user.already.exists";
		String sz = messageSource.getMessage(msgKey,data, locale);
		ResponseData r = new ResponseData(false,sz,null, null, new Message(msgKey, Message.Type.DANGER));
		return r;
	}

	public ResponseData success() {
		
		ResponseData r = new ResponseData(true,"Operation OK",null, null, new Message("OK", Message.Type.SUCCESS));
		return r;
	}

	public ResponseData fail(Exception e) {
		ResponseData r = new ResponseData(false,e.getMessage(), null,e, new Message("KO", Message.Type.DANGER));
		return r;
	}

	public ResponseData customerNotFound(String email, Locale locale) {
		Object [] data = {email};
		String msgKey = "customer.not.found";
		String sz = messageSource.getMessage(msgKey,data, locale);
		ResponseData r = new ResponseData(false,sz,null, null, new Message(msgKey, Message.Type.DANGER));
		return r;
	}

	public ResponseData customerAlreadyExists(String email, Locale locale) {
		Object [] data = {email};
		String msgKey = "customer.already.exists";
		String sz = messageSource.getMessage(msgKey,data, locale);
		ResponseData r = new ResponseData(false,sz,null, null, new Message(msgKey, Message.Type.DANGER));
		return r;
	}

	public ResponseData userNotFound(Locale locale) {
		Object [] data = null;
		String msgKey = "user.not.found";
		String sz = messageSource.getMessage(msgKey,data, locale);
		ResponseData r = new ResponseData(false,sz,null, null, new Message(msgKey, Message.Type.DANGER));
		return r;
	}

	public ResponseData success(List<?> data) {
		ResponseData r = new ResponseData(true,"Operation OK",data, null, new Message("OK", Message.Type.SUCCESS));
		return r;
	}

	public ResponseData insufficienParameters(String params, Locale locale) {
		Object [] data = {params};
		String msgKey = "insufficient.parameters";
		String sz = messageSource.getMessage(msgKey,data, locale);
		ResponseData r = new ResponseData(false,sz,null, null, new Message(msgKey, Message.Type.DANGER));
		return r;
	}
}
