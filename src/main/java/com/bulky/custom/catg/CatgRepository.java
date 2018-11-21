/**
 * 
 */
package com.bulky.custom.catg;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bulky.account.User.ROLES;

/**
 * @author kaala
 *
 */
@Repository
public class CatgRepository {
	
	@Autowired
	private EntityManager em;
	
	@Transactional(readOnly=true)
	public List<CatgRifiuti> listAll(Integer idAccount,ROLES role){		
		return em.createNamedQuery(CatgRifiuti.FIND_ALL,CatgRifiuti.class).getResultList();
	}

}
