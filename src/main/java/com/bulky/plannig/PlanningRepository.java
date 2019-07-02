/**
 * 
 */
package com.bulky.plannig;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bulky.support.AppUtil;

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

	@Transactional(readOnly=true)
	public List<Planning> listPlanningByZone(Integer idZona, Date dal, Date al) {
		
		TypedQuery<Planning> q = em.createQuery("SELECT c FROM Planning c WHERE c.plnfkzona = :id AND (c.plndal>=:dal OR :dal IS NULL ) AND (c.plnal<= :al OR :al IS NULL )  ORDER BY c.plndescr ",Planning.class);
		q.setParameter("dal", dal);
		q.setParameter("al", al);
		q.setParameter("id", idZona);
		return q.getResultList();
	}

	@Transactional(readOnly=true)
	public List<Planning> findPlanningByZone(Date dal, Date al,Integer idZona) {
		return em.createQuery("SELECT c FROM Planning c WHERE c.plnfkzona = :id AND c.plndal = :dal AND c.plnal = :al ORDER BY c.plndescr ",Planning.class).
				setParameter("id", idZona).
				setParameter("dal", dal).
				setParameter("al", al).
				getResultList();
	}

	@Transactional(readOnly=true)
	public Planning findPlanningById(Integer id) {
		return em.createQuery("SELECT c FROM Planning c WHERE c.plnfkzona = :plnid  ",Planning.class).
				setParameter("id", id).			
				getSingleResult();
	}


	@Transactional(readOnly=true)
	public List<PlanDetail> listPlanningDeatil(Integer idPlan) {
		return em.createQuery("SELECT c FROM PlanDetail c WHERE c.pldfkplannig = :id ORDER BY c.plddatefrom,c.plddateto ",PlanDetail.class).
				setParameter("id", idPlan).getResultList();
	}

	@Transactional(readOnly=true)
	public List<Map<String,Object>> listZoneAndPlanning(Integer accountId) {
		List<Map<String, Object>> results = new ArrayList<>();
		List<?> addrs = em.createNativeQuery("SELECT " +
				"plnid,plndescr,plnfkzona,zid,zdescr " +
				"FROM planning p " +
				"INNER JOIN catgzone z ON p.plnfkzona = z.zid " +
				"WHERE p.plnfkaccount = :account " +
				"ORDER BY z.zdescr,p.plndescr " )
				.setParameter("account", accountId)				
				.getResultList();
		String[] keys = {"plnid","plndescr","plnfkzona","zid","zdescr"};

		if (addrs!=null && addrs.size()>0) {
			for( Object o: addrs) {
				Map<String,Object> v = AppUtil.toMap(keys,(Object[])o);
				results.add(v);
			}
		}
		return results;
	}

	@Transactional(readOnly=true)
	public Map<String,Object> calcQty(Integer accountId, Integer idPlanDetail) {
		Map<String, Object> results = new HashMap<>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT "); 
		sb.append("SUM(cr.crincombro * ab.bqty) as incombro,SUM(ab.bqty) as qty "); 
		sb.append("FROM act_booking ab "); 
		sb.append("INNER JOIN catg_rifiuti cr ON cr.crid = ab.bfkcatg "); 
		sb.append("WHERE bfkplandetail = :pd ");
		List<?> qtys = em.createNativeQuery(sb.toString())
				.setParameter("pd", idPlanDetail)				
				.getResultList();
		String[] keys = {"incombro","qty"};

		if (qtys!=null && qtys.size()>0) {
			for( Object o: qtys) {
				Map<String,Object> v = AppUtil.toMap(keys,(Object[])o);
				results = v;
			}
		}
		return results;
	}

	@Transactional(readOnly=true)
	public List<Map<String,Object>> listPlanDetail4Customer(Integer accountId, Integer idPlanDetail, Integer idPlanning) {
		List<Map<String, Object>> results = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT "); 
		sb.append("SUM(ab.bqty) as qty, "); 
		sb.append("SUM( (ab.bqty*cr.crincombro)) AS incombro, "); 
		sb.append("pl.plnid, "); 
		sb.append("pl.plndescr, "); 
		sb.append("cu.cuid, "); 
		sb.append("cu.cuname, "); 
		sb.append("cu.cusurname, "); 
		sb.append("pd.pldfill, "); 
		sb.append("addr.adcomune, "); 
		sb.append("addr.adsiglaprov, "); 
		sb.append("addr.adaddress, "); 
		sb.append("addr.adnum, "); 
		sb.append("addr.adcap, "); 
		sb.append("pd.pldid, "); 
		sb.append("pd.plddatefrom, ");
		sb.append("ab.bfkactivity ");
		sb.append("FROM act_booking ab "); 
		sb.append("INNER JOIN plan_detail pd ON pd.pldid = ab.bfkplandetail "); 
		sb.append("INNER JOIN catg_rifiuti cr ON cr.crid = ab.bfkcatg "); 
		sb.append("INNER JOIN customer cu ON cu.cuid = ab.bfkcustomer "); 
		sb.append("INNER JOIN planning pl ON pl.plnid = pd.pldfkplannig "); 
		sb.append("INNER JOIN address addr ON addr.adid = ab.bfkaddress "); 
		sb.append("WHERE ab.baccount = :account AND (pd.pldid = :pd OR :pd IS NULL) AND ( plnid = :plnid OR :plnid IS NULL) "); 
		sb.append("GROUP BY pl.plnid,pl.plndescr,cu.cuid,cu.cuname,cu.cusurname "); 
		sb.append("ORDER BY pl.plndescr,cu.cusurname,cu.cuname ");
		List<?> qtys = em.createNativeQuery(sb.toString())
				.setParameter("pd", idPlanDetail)
				.setParameter("plnid", idPlanning)
				.setParameter("account", accountId)		
				.getResultList();
		String[] keys = {"qty","incombro","plnid","plndescr","cuid","cuname","cusurname","pldfill","adcomune","adsiglaprov","adaddress",
				"adnum","adcap","pldid","plddatefrom", "bfkactivity"};
		Map<Integer,Integer> totXpnd = new HashMap<>();
		if (qtys!=null && qtys.size()>0) {
			for( Object o: qtys) {
				Map<String,Object> v = AppUtil.toMap(keys,(Object[])o);
				results.add( v );
				Integer pndid = (Integer)v.get("pldid");
				double incombro = 0;
				if (v.containsKey("incombro")) {
					Object val = v.get("incombro");
					if (val instanceof Number) {
						Number new_name = (Number) val;
						incombro = new_name.doubleValue();
					}
				}				
				Integer vx = totXpnd.get(pndid);
				if (vx == null) {
					vx = Integer.valueOf(0);
				}
				vx = Integer.valueOf( vx.intValue() + ((int)incombro) );
				totXpnd.put(pndid, vx);
			}
			for(Map<String,Object> r : results) {
				Integer pndid = (Integer)r.get("pldid");
				Integer size = totXpnd.get(pndid);
				Integer pldfill = (Integer)r.get("pldfill");
				r.put("size", size);
				double perc =  0;
				if (pldfill!=null && pldfill.intValue()>0) {
					perc = (size!=null?size.doubleValue():0)/pldfill.doubleValue();
					perc = perc * 100.0;
					perc = Math.round(perc *10.0) / 10.0;
				}
				r.put("perc", Double.valueOf(perc));
			}
		}
		return results;
	}

	@Transactional
	public Planning storePlanning(Planning planning) {		
		return em.merge(planning);
	}
	@Transactional
	public PlanDetail deletePlanDetailById(Integer id) {
		PlanDetail detail = em.createNamedQuery( PlanDetail.FIND_BY_ID,PlanDetail.class)
				.setParameter("id", id).getSingleResult();
		em.remove(detail);		
		return detail;
	}
}
