/**
 * 
 */
package com.bulky.controller.planning;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.bulky.customer.Address;
import com.bulky.customer.AddressZone;
import com.bulky.customer.Catgzone;
import com.bulky.customer.CustomerRepository;
import com.bulky.error.DataException;
import com.bulky.plannig.PlanDetail;
import com.bulky.plannig.Planning;
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
	
	@PostMapping("api/planning/list-zone")
	public @ResponseBody ResponseData listZoneByAccount(@RequestBody String payload, HttpServletRequest request) throws DataException {		
		Integer idAccount = tokenHelper.getIdAccount(request);		
		List<Catgzone > zone = customerRep.listZone(idAccount);		
		return builder.success(zone);
	}
	
	@PostMapping("api/planning/list-zone-by-comune")
	public @ResponseBody ResponseData listZoneByComune(@RequestBody String payload, HttpServletRequest request) throws DataException {		
		Integer idAccount = tokenHelper.getIdAccount(request);	
		JSONObject plObj = AppUtil.toPayLoad(payload);
		String comune = AppUtil.getStringValueOf(plObj, "comune");				
		List<Catgzone > zone = customerRep.listZoneByComune2(idAccount, comune);	
		return builder.success(zone);
	}
	
	@PostMapping("api/planning/list-detail-zone")
	public @ResponseBody ResponseData listZoneDetail(@RequestBody String payload, HttpServletRequest request) throws DataException {		
		Integer idAccount = tokenHelper.getIdAccount(request);	
		JSONObject plObj = AppUtil.toPayLoad(payload);
		Integer idZone = AppUtil.getIntegerValueOf(plObj, "idzona");				
		List<AddressZone > zone = customerRep.listAddressZone(idAccount, idZone);	
		return builder.success(zone);
	}
	
	@PostMapping("api/planning/list-planning")
	public @ResponseBody ResponseData listPlanningByZona(@RequestBody String payload, HttpServletRequest request) throws DataException {		
		// Integer idAccount = tokenHelper.getIdAccount(request);		
		
		JSONObject plObj = AppUtil.toPayLoad(payload);
		Integer id = AppUtil.getIntegerValueOf(plObj, "zone");
		Date dal = AppUtil.getDateValueOf(plObj, "dal");
		Date al = AppUtil.getDateValueOf(plObj, "al");
		if (AppUtil.isEmpty(id)) {			
			return builder.insufficienParameters("zone", request.getLocale());
		}
		List<Planning > plans = planRep.listPlanningByZone(id, dal, al);		
		return builder.success(plans);
	}
	
	@PostMapping("api/planning/list-planning-detail")
	public @ResponseBody ResponseData listPlanningDetail(@RequestBody String payload, HttpServletRequest request) throws DataException {		
		Integer idAccount = tokenHelper.getIdAccount(request);		
		
		JSONObject plObj = AppUtil.toPayLoad(payload);
		Integer id = AppUtil.getIntegerValueOf(plObj, "planning");
		
		if (AppUtil.isEmpty(id)) {			
			return builder.insufficienParameters("planning", request.getLocale());
		}
		List<PlanDetail > plans = planRep.listPlanningDeatil(id);
		List<Map<String,Object>> planMap = new ArrayList<>();
		if ( plans!=null) {
			for(PlanDetail pd:plans) {
				Map<String,Object> qty = planRep.calcQty(idAccount, pd.getPldid());
				Map<String,Object> map = new HashMap<>();
				map.put("plan", pd);
				map.put("qty", qty);
				planMap.add(map);
			}
		}
		return builder.success(planMap);
	}
	
	@PostMapping("api/planning/list-zone-planning")
	public @ResponseBody ResponseData listZoneAndPlanningByAccount(@RequestBody String payload, HttpServletRequest request) throws DataException {		
		Integer idAccount = tokenHelper.getIdAccount(request);		
		List<Map<String,Object> > zone = planRep.listZoneAndPlanning(idAccount);		
		return builder.success(zone);
	}
	
	
	@PostMapping("api/planning/list-plan-customers")
	public @ResponseBody ResponseData listPlanning4Customers(@RequestBody String payload, HttpServletRequest request) throws DataException {		
		Integer idAccount = tokenHelper.getIdAccount(request);		
		
		JSONObject plObj = AppUtil.toPayLoad(payload);
		Integer idDetailPlan = AppUtil.getIntegerValueOf(plObj, "pldid");
		Integer idPlanning = AppUtil.getIntegerValueOf(plObj, "plnid");
		
		List<Map<String, Object>> plannings = planRep.listPlanDetail4Customer(idAccount,idDetailPlan, idPlanning);
				
		return builder.success(plannings);
	}
	
	@PostMapping("api/planning/list-disticnt-address")
	public @ResponseBody ResponseData listDistinctAddress(@RequestBody String payload, HttpServletRequest request) throws DataException {		
		Integer idAccount = tokenHelper.getIdAccount(request);
				
		JSONObject plObj = AppUtil.toPayLoad(payload);
		String comune = AppUtil.getStringValueOf(plObj, "comune");				
		List<? > zone = customerRep.listdDistinctAddressZone(idAccount, comune);	
		String[] keys = {"adaddress","adcap"};		
		List<Map<String,Object>> res = new ArrayList<>();
		
		if (zone!=null && zone.size()>0) {
			for( Object o: zone) {
				Map<String,Object> v = AppUtil.toMap(keys,(Object[])o);
				res.add( v );
			}
		}
		return builder.success(res);
	}
	
	@PostMapping("api/planning/save-address-zona")
	public @ResponseBody ResponseData saveAddressZone(@RequestBody String payload, HttpServletRequest request) throws DataException {		
		Integer idAccount = tokenHelper.getIdAccount(request);
		Integer userId = tokenHelper.getUserId(request);
		try {
			List<AddressZone> zones = AppUtil.bindToList(payload, AddressZone.class);
			for (int i = 0; zones != null && i < zones.size(); i++) {

			}
			customerRep.storeZoneList(zones, idAccount, userId);
		} catch (Exception e) {
			return builder.fail(e);
		}
		return builder.success();
	}
	
	@PostMapping("api/planning/save-zona")
	public @ResponseBody ResponseData saveZone(@RequestBody String payload, HttpServletRequest request) throws DataException {		
		Integer idAccount = tokenHelper.getIdAccount(request);
		Integer userId = tokenHelper.getUserId(request);
		try {
			//
			JSONObject plObj = new JSONObject(payload);
			String plZone = plObj.getJSONObject("z").toString();
			// {"dom":false,"lun":true,"mart":false,"sab":false,"giov":false,"merc":true,"ven":true}
			JSONObject days = plObj.getJSONObject("d");
			//2019-06-29T22:00:00.000Z
//			String szDal = plObj.getString("dal");
//			String szAl = plObj.getString("al");
			Date dal = AppUtil.getDateValueOf(plObj, "dal");
			Date al = AppUtil.getDateValueOf(plObj, "al");
			
			Catgzone zona = AppUtil.bindObject(plZone, Catgzone.class);
			
			zona = customerRep.storeZone(zona, idAccount, userId);
			
			Integer plnid = AppUtil.getIntegerValueOf(plObj, "plnid");
			
			Planning planning = null;
			if (!AppUtil.isEmpty(plnid)) {
				try {
					planning = planRep.findPlanningById(plnid);
				} catch (Exception e) {
				}
			}
			if (planning == null) {
				try {
					List<Planning> pls = planRep.findPlanningByZone(dal, al, zona.getZid());
					if (pls!=null && pls.size()>0) {
						planning = pls.get(0);
					}
				} catch (Exception e) {
				}
			}
			if (planning == null) {
				// crea uno nuovo
				planning = new Planning();
				planning.setPlnfkaccount(idAccount);
				planning.setPlnal(dal);
				planning.setPlndal(al);
				planning.setPlndtins( new Date () );
				planning.setPlnusrmod(userId);
				planning.setPlnowner( userId );
				planning.setPlnfkzona( zona.getZid() );
				planning.setPlndescr(AppUtil.formatDalAlAsText( dal, al , request.getLocale()));
				planning = planRep.storePlanning( planning );
			}
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime( dal );
			
			cal.set(Calendar.HOUR_OF_DAY, 8);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);			
									
			boolean dom = AppUtil.getBoolVal(days,"dom");
			boolean lun = AppUtil.getBoolVal(days,"lun");
			boolean mar = AppUtil.getBoolVal(days,"mart");
			boolean merc = AppUtil.getBoolVal(days,"merc");
			boolean gio = AppUtil.getBoolVal(days,"giov");
			boolean ven = AppUtil.getBoolVal(days,"ven");
			boolean sab = AppUtil.getBoolVal(days,"sab");
			
			GregorianCalendar cal2 = new GregorianCalendar();
			cal2.setTime( cal.getTime() );
			while ( cal.getTimeInMillis()<al.getTime()) {
				int dayOfweek = cal.get(Calendar.DAY_OF_WEEK);
				Date start = null;
				Date end = null;
				switch (dayOfweek) {
				case Calendar.SUNDAY:
					if (dom) {
						start = cal.getTime();
						cal.set(Calendar.HOUR_OF_DAY, 18);
						end = cal.getTime();
						cal.setTime( start );
					}
					break;
				case Calendar.MONDAY:
					if (lun) {
						start = cal.getTime();
						cal.set(Calendar.HOUR_OF_DAY, 18);
						end = cal.getTime();
						cal.setTime( start );
					}
					break;
				case Calendar.TUESDAY:
					if (mar) {
						start = cal.getTime();
						cal.set(Calendar.HOUR_OF_DAY, 18);
						end = cal.getTime();
						cal.setTime( start );
					}
					break;
				case Calendar.WEDNESDAY:
					if (merc) {
						start = cal.getTime();
						cal.set(Calendar.HOUR_OF_DAY, 18);
						end = cal.getTime();
						cal.setTime( start );
					}
					break;
				case Calendar.THURSDAY:
					if (gio) {
						start = cal.getTime();
						cal.set(Calendar.HOUR_OF_DAY, 18);
						end = cal.getTime();
						cal.setTime( start );
					}
					break;
				case Calendar.FRIDAY:
					if (ven) {
						start = cal.getTime();
						cal.set(Calendar.HOUR_OF_DAY, 18);
						end = cal.getTime();
						cal.setTime( start );
					}
					break;
				case Calendar.SATURDAY:
					if (sab) {
						start = cal.getTime();
						cal.set(Calendar.HOUR_OF_DAY, 18);
						end = cal.getTime();
						cal.setTime( start );
					}
					break;

				default:
					break;
				}
				if (start!=null && end!=null) {
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
					detail.setPldfkplannig(planning.getPlnid());
					planRep.store(detail);					
				}
				cal.add(Calendar.DATE, 1);
			}
				
			List<Catgzone> rs = new ArrayList<>();
			rs.add(zona);
			return builder.success(rs);
		} catch (Exception e) {
			return builder.fail(e);
		}
		
	}
	
	@PostMapping("api/planning/delete-planning-detail")
	public @ResponseBody ResponseData deletePlanningDetail(@RequestBody String payload, HttpServletRequest request) throws DataException {		
		Integer idAccount = tokenHelper.getIdAccount(request);		
		
		JSONObject plObj = AppUtil.toPayLoad(payload);
		Integer id = AppUtil.getIntegerValueOf(plObj, "pldid");
		
		if (AppUtil.isEmpty(id)) {			
			return builder.insufficienParameters("pldid", request.getLocale());
		}
		try {
			planRep.deletePlanDetailById(id);
		} catch (Exception e) {
			return builder.fail(e);
		}
		return builder.success();
	}


}
