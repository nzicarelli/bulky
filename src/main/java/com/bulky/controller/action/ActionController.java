/**
 * 
 */
package com.bulky.controller.action;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
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
import com.bulky.support.StartLimit;

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
		Integer cuid = null;
		ROLES r = null;
		if (userKind!=null) {
			r = ROLES.valueOf(userKind);
		}
		if (r!=null && r.equals(ROLES.ROLE_CUSTOMER)) {
			cuid = idUtente;
		}else {
			if (act.getAassign() == null) {
				act.setAassign( idUtente );
			}
		}
		if (act.getAaccount() == null) {
			act.setAaccount(idAccount);
		}
		if (act.getAfkcustomer() == null && cuid!=null) {
			act.setAfkcustomer( cuid );
		}else {
			return builder.insufficienParameters("afkcustomer", request.getLocale());
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
	
	@PostMapping("api/action/list-lead")
	public @ResponseBody ResponseData listLead(@RequestBody String payload, HttpServletRequest request) throws DataException {
				
		JSONObject plo = AppUtil.toPayLoad(payload);
		String userKind = tokenHelper.getUserKind(request);
		Integer idAccount = tokenHelper.getIdAccount(request);
		Integer idUtente = tokenHelper.getUserId(request); // customer id se si trata di un customer
		Integer cuid = null;
		ROLES r = null;
		if (userKind!=null) {
			r = ROLES.valueOf(userKind);
		}
		if (r!=null && r.equals(ROLES.ROLE_CUSTOMER)) {
			cuid = idUtente;
		}else {
			cuid = AppUtil.getIntegerValueOf(plo, "cuid");
		}
		Date dal = AppUtil.getDateValueOf(plo,"dal");
		Date al = AppUtil.getDateValueOf(plo,"dal");
		StartLimit startLimit = AppUtil.startLimit(plo);
		
		List<Map<String,Object>> leads = actRep.listLead(idAccount, startLimit.getStart(), startLimit.getLimit(), cuid, dal, al);
		
		
		return builder.success(Arrays.asList(leads));
	}
	
	@PostMapping("api/action/list-activity-4lead")
	public @ResponseBody ResponseData listActivityByLead(@RequestBody String payload, HttpServletRequest request) throws DataException {
				
		JSONObject plo = AppUtil.toPayLoad(payload);
//		String userKind = tokenHelper.getUserKind(request);
		Integer idAccount = tokenHelper.getIdAccount(request);
		// Integer idUtente = tokenHelper.getUserId(request); // customer id se si trata di un customer
		
//		ROLES r = null;
//		if (userKind!=null) {
//			r = ROLES.valueOf(userKind);
//		}		
		StartLimit startLimit = AppUtil.startLimit(plo);
		Integer lead = AppUtil.getIntegerValueOf(plo, "lead");
		
		List<Map<String,Object>> leads = actRep.listActivityByLead(idAccount, startLimit.getStart(), startLimit.getLimit(), lead);
		
		
		return builder.success(Arrays.asList(leads));
	}
	
	
	
	

}
