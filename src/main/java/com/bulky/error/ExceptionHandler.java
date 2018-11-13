package com.bulky.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bulky.response.ResponseBuilder;
import com.bulky.response.ResponseData;
import com.google.common.base.Throwables;

/**
 * General error handler for the application.
 */
@ControllerAdvice
class ExceptionHandler {
	
	@Autowired
	private ResponseBuilder responseBuilder;

	/**
	 * Handle exceptions thrown by handlers.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)	
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error/general");
		modelAndView.addObject("errorMessage", Throwables.getRootCause(exception));
		return modelAndView;
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = UsernameNotFoundException.class)	
	public @ResponseBody ResponseData usernameNotFoundException(Exception exception, WebRequest request) {
		return responseBuilder.userNotFound(request.getLocale());	
	}
	
	
}