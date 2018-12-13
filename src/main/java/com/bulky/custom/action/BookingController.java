/**
 * 
 */
package com.bulky.custom.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bulky.account.User.ROLES;
import com.bulky.action.ActBooking;
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
public class BookingController {

	@Autowired
	private ActionRepository actRep;

	@Autowired
	private ResponseBuilder builder;

	@Autowired
	private TokenHelper tokenHelper;

	@Autowired
	private LeadServiceUtil leadService;

	@PostMapping("api/booking/store")
	public @ResponseBody ResponseData storeBookingAction(@RequestBody String payload, HttpServletRequest request) throws DataException {

		// riceve in input una lista di act_booking
		// id customer
		// id indirizzo
		// id planning
		// lista booking
		// tipolead
		// tipo azione
		try {
			JSONObject plo = AppUtil.toPayLoad(payload);
			Integer idIndirizzo = AppUtil.getIntegerValueOf(plo, "adid");
			// Integer idPlannig = AppUtil.getIntegerValueOf(plo, "plnid");
			Integer idPlanDetail = AppUtil.getIntegerValueOf(plo, "pldid");

			// Integer idTipoLead = AppUtil.getIntegerValueOf(plo, "tlead");
			Integer idCatgAction = AppUtil.getIntegerValueOf(plo, "tact");

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
				idUtente = null;
			}else {
				cuid = AppUtil.getIntegerValueOf(plo, "cuid");
			}
			if (AppUtil.isEmpty(cuid) || AppUtil.isEmpty(idCatgAction) || AppUtil.isEmpty(idPlanDetail)) {
				return builder.insufficienParameters("cuid,pldid or tact", request.getLocale());
			}
			List<ActBooking> bookingList = AppUtil.bindToList(plo.getJSONArray("bks").toString(), ActBooking.class);
			CatgAction catg = actRep.findCatgById(idCatgAction);
			Activity a = new Activity();
			a.setAfkcustomer(cuid);
			// FIXME DA FARE LE POLITICHE DI ASSEGNAZIONE CONTATTO
			a.setAassign( idUtente );
			a.setAdescr( null );
			a.setAtitle(catg.getCadescr());
			a.setAfktype(idCatgAction);
			a.setAowner(idUtente);
			a.setAstatus(catg.getCastatus());

			Lead lead = leadService.buildLead(a);
			lead = actRep.store(lead);
			a.setAfklead( lead.getLid() );
			a = actRep.store(a);

			for (ActBooking act: bookingList) {
				act.setBaccount(idAccount);
				act.setBdtmod( new Date() );
				act.setBfkactivity(a.getAid());
				act.setBfkplandetail(idPlanDetail);
				act.setBfkaddress(idIndirizzo);
				act.setBfkcustomer(cuid);
			}
			actRep.store( bookingList );

			return builder.success(bookingList);
		}catch (Exception e) {
			return builder.fail(e);
		}
		
		
	}
	
}
