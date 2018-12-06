/**
 * 
 */
package com.bulky.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bulky.account.User.ROLES;
import com.bulky.support.AppUtil;

/**
 * @author kaala
 *
 */
@Repository
public class ActionRepository {
	
	@Autowired
	private EntityManager em;
	
	@Transactional(readOnly=true)
	public List<CatgAction> findAll(Integer idAccount,ROLES r) {
		if (r==null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT c FROM CatgAction c WHERE c.cafkaccount = :id AND c.caenabled = :enabled ");
		
		if (r.equals(ROLES.ROLE_CUSTOMER)) {
			sb.append(" AND c.customerEnabled = :customerEnabled ");			
		}
		
		sb.append(" ORDER BY c.caid ");
		TypedQuery<CatgAction> tq = em.createQuery(sb.toString(), CatgAction.class);
		tq.setParameter("id", idAccount);
		tq.setParameter("enabled", Boolean.TRUE);
		if (r.equals(ROLES.ROLE_CUSTOMER)) {
			tq.setParameter("customerEnabled", Boolean.TRUE);
		}		
		return tq.getResultList();
	}
	
	@Transactional(readOnly=true)
	public CatgAction findCatgById(Integer id) {
		return em.createNamedQuery(CatgAction.FIND_BY_ID,CatgAction.class).setParameter("id", id).getSingleResult();				
	}

	@Transactional
	public Lead store(Lead lead) {	
		if (lead.getLdtmod()==null) {
			lead.setLdtmod( new Date() );
		}
		if (lead.getLedtins()==null) {
			lead.setLedtins( new Date() );
		}
		return em.merge(lead);
	}
	@Transactional
	public Activity store(Activity act) {
		return em.merge(act);
	}
	
	
	@Transactional(readOnly=true)
	public List<Map<String,Object>> listLead(Integer account, int start, int limit, Integer cuid, Date dal, Date al) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT "); 
		sb.append("lid, "); 
		sb.append("laccount, "); 
		sb.append("ltype, "); 
		sb.append("lowner, "); 
		sb.append("lassign, "); 
		sb.append("ldtmod, "); 
		sb.append("lstatus, "); 
		sb.append("ldescr, "); 
		sb.append("lfkcustomer, "); 
		sb.append("ledtins, "); 
		sb.append("lefkmediaarrivo, "); 
		sb.append("lctdescrizione, "); 
		sb.append("lcmadescrizione, "); 
		sb.append("lcsdescrizione, "); 
		sb.append("cuname, "); 
		sb.append("cusurname, "); 
		sb.append("nact.N "); 
		sb.append("FROM lead l "); 
		sb.append("LEFT OUTER JOIN lead_catg_tipo t on l.ltype = t.lctid "); 
		sb.append("LEFT OUTER JOIN lead_catg_mediaarrivo m ON m.lcmaid = l.lefkmediaarrivo "); 
		sb.append("LEFT OUTER JOIN lead_catg_stato s ON s.lcsid = l.lstatus "); 
		sb.append("LEFT OUTER JOIN customer c ON c.cuid = l.lfkcustomer "); 
		sb.append("LEFT OUTER JOIN "); 
		sb.append("( "); 
		sb.append("   SELECT "); 
		sb.append("   COUNT(*) AS N, "); 
		sb.append("   afklead "); 
		sb.append("   FROM activity "); 
		sb.append("   group by afklead "); 
		sb.append(") "); 
		sb.append("nact ON afklead = l.lid "); 
		sb.append("WHERE l.laccount = :account "); 
		sb.append("AND (lfkcustomer = :cuid OR :cuid IS NULL ) "); 
		sb.append("AND (ledtins>=:dal OR :dal IS NULL) AND (ledtins<=:al OR :al IS NULL ) "); 
		sb.append("ORDER BY ledtins DESC,cuname,cusurname LIMIT :start,:limit ");
		
		List<?> results = em.createNativeQuery(sb.toString())
				.setParameter("cuid", cuid)
				.setParameter("start", start)
				.setParameter("limit", limit)
				.setParameter("account", account)
				.setParameter("dal", dal)
				.setParameter("al", al)
				.getResultList();
		List<Map<String,Object>> rs = new ArrayList<>();
		String[] keys = {"lid","laccount","ltype","lowner","lassign","ldtmod","lstatus","ldescr","lfkcustomer","ledtins","lefkmediaarrivo",
				"lctdescrizione","lcmadescrizione","lcsdescrizione","cuname","cusurname","N"};
		
		if (results!=null && results.size()>0) {
			for( Object o: results) {
				Map<String,Object> v = AppUtil.toMap(keys,(Object[])o);
				rs.add(v);
			}
		}
		return rs;
	}
		
	
	@Transactional(readOnly=true)
	public List<Map<String,Object>> listActivityByLead(Integer account, int start, int limit, Integer lead) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT "); 
		sb.append("aid,aaccount,afktype,aowner,aassign,adtmod,astatus,adescr,atitle,asubject,afkcustomer, "); 
		sb.append("afklead,cuname,cusurname,cadescr,asdescr "); 
		sb.append("FROM activity a "); 
		sb.append("INNER JOIN lead l ON l.lid = a.afklead "); 
		sb.append("INNER JOIN customer c ON c.cuid = l.lfkcustomer "); 
		sb.append("LEFT OUTER JOIN catg_action ca ON ca.caid = a.afktype "); 
		sb.append("LEFT OUTER JOIN act_status st ON st.asid = a.astatus "); 
		sb.append("WHERE a.aaccount = :account "); 
		sb.append("AND a.afklead = :lead "); 
		sb.append("ORDER BY a.adtmod DESC LIMIT :start,:limit ");
		
		List<?> results = em.createNativeQuery(sb.toString())
				.setParameter("lead", lead)
				.setParameter("start", start)
				.setParameter("limit", limit)
				.setParameter("account", account)				
				.getResultList();
		List<Map<String,Object>> rs = new ArrayList<>();
		String[] keys = {"aid","aaccount","afktype","aowner","aassign","adtmod","astatus","adescr","atitle","asubject","afkcustomer",
				"afklead","cuname","cusurname","cadescr","asdescr"};
		
		if (results!=null && results.size()>0) {
			for( Object o: results) {
				Map<String,Object> v = AppUtil.toMap(keys,(Object[])o);
				rs.add(v);
			}
		}
		return rs;
	}
	
	
	@Transactional(readOnly=true)
	public List<LeadCatgTipo> listCatgTipoLead(Integer idAccount) {
		return em.createNamedQuery(LeadCatgTipo.FIND_BY_ACCOUNT,LeadCatgTipo.class).setParameter("id", idAccount).getResultList();				
	}
	
	@Transactional(readOnly=true)
	public List<CatgAction> findCatgActionByTipoLead(Integer id) {
		return em.createNamedQuery(CatgAction.FIND_BY_LEAD,CatgAction.class).setParameter("id", id).getResultList();				
	}
	
	

}
