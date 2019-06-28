/**
 * 
 */
package com.bulky.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

	@Transactional(readOnly=true)
	public List<Catgzone> listZone(Integer accountId) {
		return em.createQuery("SELECT c FROM Catgzone c WHERE c.zfkaccount = :id ORDER BY c.zdescr ",Catgzone.class).
				setParameter("id", accountId).getResultList();
	}




	@Transactional(readOnly=true)
	public List<Catgzone> listZoneByComune(Integer accountId, String comune) {
		return em.createQuery("SELECT c FROM Catgzone c WHERE c.zfkaccount = :id AND EXISTS ( "
				+ " SELECT x FROM AddressZone x WHERE x.azfkaccount = c.zfkaccount AND x.azfkzona = c.id AND x.azcomune = :comune "
				+ ") ORDER BY c.zdescr ",Catgzone.class).
				setParameter("id", accountId).
				setParameter("comune", comune ).
				getResultList();
	}
	
	@Transactional(readOnly=true)
	public List<Catgzone> listZoneByComune2(Integer accountId, String comune) {
		return em.createQuery("SELECT c FROM Catgzone c WHERE c.zfkaccount = :id AND c.zcomune = :comune ORDER BY c.zdescr ",Catgzone.class).
				setParameter("id", accountId).
				setParameter("comune", comune ).
				getResultList();
	}

	@Transactional(readOnly=true)
	public List<AddressZone> listAddressZone(Integer accountId, Integer idZona) {
		return em.createQuery("SELECT c FROM AddressZone c WHERE c.azfkaccount = :id AND c.azfkzona = :zona  ORDER BY c.azcomune,c.azcap, c.azaddress ",AddressZone.class).
				setParameter("id", accountId).
				setParameter("zona", idZona ).
				getResultList();
	}

	@Transactional(readOnly=true)
	public List<?> listdDistinctAddressZone(Integer accountId, String comune) {
		return em.createQuery("SELECT DISTINCT c.adaddress,c.adcap FROM Address c WHERE c.adfkaccount = :id AND c.adcomune = :comune  ORDER BY c.adcap,c.adaddress ").
				setParameter("id", accountId).
				setParameter("comune", comune ).
				getResultList();
	}
	@Transactional
	public void storeZoneList(List<AddressZone> zones, Integer idAccount, Integer userId) {
		Query q = em.createQuery(" DELETE FROM AddressZone WHERE azfkzona = :zona ");
		for (int i = 0;zones!=null && i < zones.size(); i++) {
			AddressZone z = zones.get(i);
			if (i==0) {
				q.setParameter("zona", z.getAzfkzona());
				q.executeUpdate();
			}
			z.setAzdtins( new Date() );
			z.setAzdtmod( new Date() );
			z.setAzfkaccount(idAccount);
			z.setAzusermod(userId);
			em.merge(z);
		}

	}
	
	@Transactional
	public Catgzone storeZone(Catgzone zona, Integer idAccount, Integer userId) {
		zona.setZdtmod( new Date());
		if (zona.getZid()==null) {
			zona.setZdtins( new Date());			
		}
		zona.setZfkaccount(idAccount);
		zona.setZdtmod( new Date());
		zona.setZusermod(userId);
		return em.merge(zona);
		
	}


}
