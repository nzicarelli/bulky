/**
 * 
 */
package com.bulky.controller.action;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bulky.account.User.ROLES;
import com.bulky.action.ActionRepository;
import com.bulky.action.Activity;
import com.bulky.action.CatgAction;
import com.bulky.action.Lead;
import com.bulky.action.LeadServiceUtil;
import com.bulky.error.DataException;
import com.bulky.response.ResponseBuilder;
import com.bulky.response.ResponseData;
import com.bulky.security.TokenHelper;
import com.bulky.support.AppUtil;

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
	
	@Autowired
	private TokenHelper tokenHelper;
	
	@Autowired
	private LeadServiceUtil leadService;
	
	@PostMapping("api/action/list")
	public @ResponseBody ResponseData listAction(@RequestBody String payload, HttpServletRequest request) throws DataException {
		
		String userKind = tokenHelper.getUserKind(request);
		Integer idAccount = tokenHelper.getIdAccount(request);
		ROLES r = null;
		if (userKind!=null) {
			r = ROLES.valueOf(userKind);
		}
		List<CatgAction> catga = actRep.findAll(idAccount,r);
		return builder.success(catga);
	}
	
	@PostMapping("api/action/store")
	public @ResponseBody ResponseData storeAction(@RequestBody String payload, HttpServletRequest request) throws DataException {
		Activity act = AppUtil.bindObject(payload, Activity.class);
		Lead lead = null;
		
		String userKind = tokenHelper.getUserKind(request);
		Integer idAccount = tokenHelper.getIdAccount(request);
		Integer idUtente = tokenHelper.getUserId(request); // customer id se si trata di un customer
		ROLES r = null;
		if (userKind!=null) {
			r = ROLES.valueOf(userKind);
		}
		if (AppUtil.isEmpty(act.getAfklead())){
			// crea Lead
			lead = leadService.buildLead(act);
			lead = actRep.store(lead);
			act.setAfklead(lead.getLid());
		}
		act.setAdtmod( new Date() );
		act = actRep.store(act);
		
		return builder.success(Arrays.asList(act));
	}

}
