/**
 * 
 */
package com.bulky.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kaala
 *
 */
@Service
public class LeadServiceUtil {
	
	@Autowired
	private ActionRepository actRep;
	
	public Lead buildLead(Activity act ) {
		CatgAction catg = actRep.findCatgById(act.getAfktype());
		Integer idTipoLead = catg.getCafktlead();
		Lead lead = new Lead();
		lead.setLaccount( act.getAaccount() );
		//FIXME DA IMPLEMENTARE IL MECCANISMO DI ASSEGNAZIONE
		// lead.setLassign( );
		lead.setLdescr(act.getAtitle());
		lead.setLdtmod( new Date() );
		lead.setLfkcustomer(act.getAfkcustomer() );
		lead.setLtype(idTipoLead );
		lead.setLaccount( act.getAaccount());
		return lead;
	}

}
