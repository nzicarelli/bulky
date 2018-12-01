/**
 * 
 */
package com.bulky.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bulky.account.Account;
import com.bulky.support.AppUtil;

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
	
	@Transactional(readOnly=true)
	public Account findAccountById(Integer cuid) {
		return em.createNamedQuery(Account.FIND_BY_ID,Account.class).setParameter("id", cuid).getSingleResult();
	}
	
	@Transactional(readOnly=true)
	public List<Address> listAddressByCustomer(Integer cuid) {
		return em.createNamedQuery(Address.FIND_BY_CUSTOMER_ID,Address.class).setParameter("id", cuid).getResultList();
	}
	
	@Transactional(readOnly=true)
	public Address findAddressById(Integer adid) {
		return em.createNamedQuery(Address.FIND_BY_ID,Address.class).setParameter("id", adid).getSingleResult();
	}

	@Transactional(readOnly=true)
	public AddressZone lookupZone(Address addr) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT "); 
		sb.append("a "); 
		sb.append("FROM AddressZone a "); 
		sb.append("WHERE a.azcomune = :comune "); 
		sb.append("AND ( a.azaddress = :address OR a.azaddress IS NULL ) "); 
		sb.append("ORDER BY a.azaddress DESC ");
		
		return em.createQuery(sb.toString(),AddressZone.class)
				.setParameter("comune", addr.getAdcomune())
				.setParameter("address", addr.getAdaddress())
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Customer> listCustomerByComune(Integer account,String comune, int start, int limit) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT "); 
		sb.append("DISTINCT c.* "); 
		sb.append("FROM customer c "); 
		sb.append("LEFT OUTER JOIN address a ON c.cuid = a.adfkcustomer "); 
		sb.append("WHERE c.cufkaccount = :account AND  a.adcomune = :comune "); 
		sb.append("ORDER BY c.cusurname,c.cuname LIMIT :start,:limit ");
		
		return em.createNativeQuery(sb.toString(), Customer.class)
				.setParameter("comune", comune)
				.setParameter("start", start)
				.setParameter("limit", limit)
				.setParameter("account", account)
				.getResultList();
	}
	
	@Transactional(readOnly=true)
	public List<Map<String, Object>> listComuni(Integer account, Integer cuid) {
		List<Map<String, Object>> results = new ArrayList<>();
		List<?> addrs = em.createQuery("SELECT DISTINCT a.adcap, a.adcomune, a.adsiglaprov "
				+ " FROM Address a WHERE (a.adfkcustomer=:cuid OR :cuid IS NULL ) AND a.adfkaccount=:account "
				+ " ORDER BY a.adcomune, a.adsiglaprov " )
				.setParameter("account", account)
				.setParameter("cuid", cuid)
				.getResultList();
		String[] keys = {"adcap","adcomune","adsiglaprov"};
		
		if (addrs!=null && addrs.size()>0) {
			for( Object o: addrs) {
				Map<String,Object> v = AppUtil.toMap(keys,(Object[])o);
				results.add(v);
			}
		}
		return results;
	}
	
	
	
	


}
