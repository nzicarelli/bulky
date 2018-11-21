/**
 * 
 */
package com.bulky.action;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bulky.account.User.ROLES;

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

}
