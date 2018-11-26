/**
 * 
 */
package com.bulky.controller.planning;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bulky.account.User.ROLES;
import com.bulky.customer.Address;
import com.bulky.customer.AddressZone;
import com.bulky.customer.CustomerRepository;
import com.bulky.error.DataException;
import com.bulky.plannig.PlanDetail;
import com.bulky.plannig.PlanningRepository;
import com.bulky.response.ResponseBuilder;
import com.bulky.response.ResponseData;
import com.bulky.security.TokenHelper;
import com.bulky.support.AppUtil;

/**
 * @author kaala
 *
 */
@Controller
public class PlanningController {
	
	@Autowired
	private PlanningRepository planRep;
	
	@Autowired
	private CustomerRepository customerRep;
	
	@Autowired
	private ResponseBuilder builder;
	
	@Autowired
	private TokenHelper tokenHelper;
	
	@PostMapping("api/planning/build")
	public @ResponseBody ResponseData buildPlanDetail(@RequestBody String payload, HttpServletRequest request) throws DataException {

		String userKind = tokenHelper.getUserKind(request);
		if (userKind==null || userKind.equals(ROLES.ROLE_CUSTOMER.name())) {
			return builder.fail(new Exception("Permessi non sufficienti"));
		}
		Integer idAccount = tokenHelper.getIdAccount(request);
		Integer userId = tokenHelper.getUserId(request);
		
		JSONObject plObj = AppUtil.toPayLoad(payload);
		Integer plnid = AppUtil.getIntegerValueOf(plObj, "plnid");
		// genera per esempio tutti i mercoledi' dalle 9,00 alle 15,00
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		for (int i=0; i<10; i++) {
			
			cal.set(Calendar.HOUR_OF_DAY, 9);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);			
			
			Date start = cal.getTime();
			cal.set(Calendar.HOUR_OF_DAY, 15);
			
			Date end = cal.getTime();
			
			PlanDetail detail = null ;
			try {
				detail = planRep.findByDate(idAccount, plnid, start, end);
			} catch (Exception e) {

			}
			if (detail == null) {
				detail = new PlanDetail();
				detail.setPldfkaccount(idAccount);
				detail.setPldfkplannig(plnid);
				detail.setPlddtins( new Date());
			}
			detail.setPlddescr( AppUtil.formatDateAsText( start, end , request.getLocale()) );
			detail.setPldtmod( new Date ());
			detail.setPldusrmod(userId);
			detail.setPlddatefrom(start);
			detail.setPlddateto(end);
			planRep.store(detail);
			cal.add(Calendar.DATE, 8);
			
			
		}
		
		return builder.success();
	}
	
	
	@PostMapping("api/planning/by-address")
	public @ResponseBody ResponseData listByAddress(@RequestBody String payload, HttpServletRequest request) throws DataException {
		String userKind = tokenHelper.getUserKind(request);		
		// Integer idAccount = tokenHelper.getIdAccount(request);
		Integer userId = tokenHelper.getUserId(request);		
		
		JSONObject plObj = AppUtil.toPayLoad(payload);
		Integer cusId = AppUtil.getIntegerValueOf(plObj, "cuid");
		Integer adid = AppUtil.getIntegerValueOf(plObj, "adid");
		if (AppUtil.isEmpty(cusId)) {
			if (userKind!=null && userKind.equals(ROLES.ROLE_CUSTOMER.name())) {
				cusId = userId;
			}else {
				return builder.insufficienParameters("cuid", request.getLocale());
			}
			
		}
		Address addr = null;
		try {
			addr = customerRep.findAddressById(adid);
		} catch (Exception e) {
			return builder.noDataFound(request.getLocale());
		}
		AddressZone zona = customerRep.lookupZone(addr);
		if (zona==null || zona.getAzfkzona()==null) {
			return builder.noZone4Address(addr, request.getLocale());
		}
		List<PlanDetail> detail = planRep.listPlanDetail(zona.getAzfkzona());		
		return builder.success(detail);
	}

}
