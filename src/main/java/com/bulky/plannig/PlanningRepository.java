/**
 * 
 */
package com.bulky.plannig;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bulky.customer.AddressZone;

/**
 * @author kaala
 *
 */
@Repository
public class PlanningRepository {
	
	@Autowired
	private EntityManager em;
	
	@Transactional
	public PlanDetail store(PlanDetail detail) {
		return em.merge(detail);
	}

	@Transactional(readOnly=true)
	public PlanDetail findByDate(Integer idAccount, Integer plnid, Date start, Date end) {		
		return em.createQuery(" SELECT d FROM PlanDetail d WHERE d.pldfkplannig = :id "
				+ " AND d.pldfkaccount = :account "
				+ " AND d.plddatefrom=:from AND d.plddateto = :to  "
				,PlanDetail.class).setParameter("id", plnid)
				.setParameter("account", idAccount)
				.setParameter("from", start)
				.setParameter("to", end)
				.getSingleResult();
	}
	
	@Transactional(readOnly=true)
	public PlanDetail listDetail(Integer idAccount, Integer plnid, Date start, Date end) {		
		return em.createQuery(" SELECT d FROM PlanDetail d WHERE d.pldfkplannig = :id "
				+ " AND d.pldfkaccount = :account "
				+ " AND d.plddatefrom>from AND (d.plddateto <= :to  OR d.plddateto IS :to )"
				,PlanDetail.class).setParameter("id", plnid)
				.setParameter("account", idAccount)
				.setParameter("from", start)
				.setParameter("to", end)
				.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<PlanDetail> listPlanDetail(Integer idZona) {
		StringBuffer sb = new StringBuffer();		
		sb.append("SELECT "); 
		sb.append("pd.* "); 
		sb.append("FROM plan_detail pd,planning p "); 
		sb.append("WHERE pd.pldfkplannig = p.plnid "); 
		sb.append("AND p.plnfkzona = :id "); 
		sb.append("ORDER BY pd.plddatefrom ");
		
		return em.createNativeQuery(sb.toString(),PlanDetail.class)
				.setParameter("id", idZona)				
				.getResultList();
	}

}
