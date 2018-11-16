/**
 * 
 */
package com.bulky.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bulky.action.ActionRepository;
import com.bulky.action.CatgAction;
import com.bulky.error.DataException;
import com.bulky.response.ResponseBuilder;
import com.bulky.response.ResponseData;

/**
 * @author kaala
 *
 */
@Controller
public class ActionController {
	
	@Autowired
	private ActionRepository actRep;
	
	@Autowired
	private ResponseBuilder builder;
	
	@PostMapping("api/action/list")
	public @ResponseBody ResponseData listAction(@RequestBody String payload, HttpServletRequest request) throws DataException {
		
		List<CatgAction> catga = actRep.findAll();
		return builder.success(catga);
	}

}
