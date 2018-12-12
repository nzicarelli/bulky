/**
 * 
 */
package com.bulky.controller.action;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
import com.bulky.action.LeadCatgTipo;
import com.bulky.action.LeadServiceUtil;
import com.bulky.customer.Address;
import com.bulky.customer.Customer;
import com.bulky.customer.CustomerRepository;
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
	
	@Autowired
	private CustomerRepository customerRep;

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
			lead.setLaccount(idAccount);
			//FIXME MANCA LA GESTIONE DEL MEDIA DI ARRIVO
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
		Date al = AppUtil.getDateValueOf(plo,"al");
		StartLimit startLimit = AppUtil.startLimit(plo);

		List<Map<String,Object>> leads = actRep.listLead(idAccount, startLimit.getStart(), startLimit.getLimit(), cuid, dal, al);


		return builder.success(leads);
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


		return builder.success(leads);
	}

	@PostMapping("api/action/list-tipo-lead")
	public @ResponseBody ResponseData listTipoLead(@RequestBody String payload, HttpServletRequest request) throws DataException {

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


		List<LeadCatgTipo> leads = actRep.listCatgTipoLead(idAccount);


		return builder.success(leads);
	}

	@PostMapping("api/action/list-catgactivity-4lead")
	public @ResponseBody ResponseData listActivity4Lead(@RequestBody String payload, HttpServletRequest request) throws DataException {
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
		Integer idTipoLead = AppUtil.getIntegerValueOf(plo, "tlead");

		List<CatgAction> leads = actRep.findCatgActionByTipoLead(idTipoLead);


		return builder.success(leads);
	}

	@PostMapping("api/action/lead-store")
	public @ResponseBody ResponseData storeLead(@RequestBody String payload, HttpServletRequest request) throws DataException {
		Lead lead = AppUtil.bindObject(payload, Lead.class);


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
			if (lead.getLassign() == null) {
				lead.setLassign( idUtente );
			}
			if (lead.getLowner() == null) {
				lead.setLowner( idUtente );
			}
		}
		if (lead.getLaccount() == null) {
			lead.setLaccount(idAccount);
		}
		if (lead.getLfkcustomer() == null && cuid!=null) {
			lead.setLfkcustomer( cuid );
		}
		if (lead.getLfkcustomer() == null || lead.getLtype() == null ) {
			return builder.insufficienParameters("lfkcustomer,ltype", request.getLocale());
		}
		lead.setLdtmod( new Date() );
		if (lead.getLid()!=null) {
			Lead old = actRep.findLeadById( lead.getLid() );
			if (old!=null) {
				lead.setLedtins(old.getLedtins());
				lead.setLowner( old.getLowner() );
				lead.setLefkmediaarrivo( old.getLefkmediaarrivo() );
				lead.setLassign( old.getLassign() );
			}
		}
		lead = actRep.store(lead);

		return builder.success(Arrays.asList(lead));
	}

	@PostMapping("api/action/lead-load")
	public @ResponseBody ResponseData loadLeadById(@RequestBody String payload, HttpServletRequest request) throws DataException {
		Lead lead = null;
		JSONObject plo = AppUtil.toPayLoad(payload);
		Integer id = AppUtil.getIntegerValueOf(plo, "id");
		if(AppUtil.isEmpty(id)) {
			return builder.insufficienParameters("id", request.getLocale());
		}
		lead = actRep.findLeadById(id);
		if (lead == null ) {
			return builder.fail(new Exception("Data Not Found"));
		}
		Customer customer = customerRep.findById(lead.getLfkcustomer());
		List<Address> addrs = customerRep.listAddressByCustomer(lead.getLfkcustomer());
		Map<String,Object> result = new HashMap<>();
		result.put("lead", lead);
		result.put("customer", customer);
		result.put("addresses", addrs);
		return builder.success(result);		
	}





}
