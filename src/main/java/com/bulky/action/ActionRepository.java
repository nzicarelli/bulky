/**
 * 
 */
package com.bulky.action;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author kaala
 *
 */
@Repository
public class ActionRepository {
	
	@Autowired
	private EntityManager em;
	
	public List<CatgAction> findAll() {
		return em.createNamedQuery(CatgAction.FIND_ALL, CatgAction.class).getResultList();
	}

}
