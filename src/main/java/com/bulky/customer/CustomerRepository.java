/**
 * 
 */
package com.bulky.customer;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bulky.account.User;

/**
 * @author kaala
 *
 */
@Repository
public class CustomerRepository {

	@Autowired
	private EntityManager em;

	@Transactional(readOnly=true)
	public Customer findByUsername(String userName) {
		return em.createNamedQuery(Customer.FIND_BY_USERNAME,Customer.class).setParameter("name", userName).getSingleResult();
	}

	@Transactional(readOnly=true)
	public Customer findByCfPiva(String cucf, String cupiva) {
		return em.createNamedQuery(Customer.FIND_BY_CF_PIVA,Customer.class)
				.setParameter("cf", cucf)
				.setParameter("piva", cupiva)
				.getSingleResult();
	}

	@Transactional
	public Customer store(Customer user) {
		return em.merge(user);
		
	}
	
	@Transactional(readOnly=true)
	public Customer findById(Integer cuid) {
		return em.createNamedQuery(Customer.FIND_BY_ID,Customer.class).setParameter("id", cuid).getSingleResult();
	}


}
