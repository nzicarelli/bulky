package com.bulky.account;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bulky.customer.Customer;
import com.bulky.customer.CustomerRepository;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerRepository customerRep;

	@PostConstruct	
	protected void initialize() {
		User u = accountRepository.findOneByUname("admin");
		if (u==null) {
			u = new User();
			u.setUaccount(Integer.valueOf(0));
			u.setUname("admin");
			u.setUemail("admin@bulky.com");
			u.setUpasswd(passwordEncoder.encode("admin"));
			u.setUrole(User.ROLES.ROLE_ADMIN.name());
			save(u, false);
		}
//		save(new Account("user", "demo", "ROLE_USER"));
//		save(new Account("admin", "admin", "ROLE_ADMIN"));
	}

	@Transactional
	public User save(User account, boolean encodePasswd) {
		if (encodePasswd) {
			account.setUpasswd(passwordEncoder.encode(account.getUpasswd()));
		}
		account.setUdtmod( new Date());
		account = accountRepository.save(account);
		return account;
	}
	
	@Transactional(readOnly=true)
	public User findByEmail(String email) {
		return accountRepository.findOneByUemail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User account = accountRepository.findOneByUemail(username);
		
		if (account==null) {
			Customer cus = customerRep.findByUsername(username);
			if (cus!=null) {
				UserData udata = createUserFromCustomer(cus);
				udata.setAccountType("CUSTOMER");
				return udata;
			}
		}
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		UserData user = createUser(account);
		user.setAccountType("USER");
		return user;
	}
	
	private UserData createUserFromCustomer(Customer account) {		
		UserData user = new UserData(
				account.getCuusername(), 
				account.getCupassword(), 
				/*enabled*/(account.getCuenabled()!=null && account.getCuenabled().booleanValue()?true:false),
				/*accountNonExpired*/(account.getCuenabled()!=null && account.getCuenabled().booleanValue()?true:false),
				/*credentialsNonExpired*/(account.getCuenabled()!=null && account.getCuenabled().booleanValue()?true:false),
				/*accountNonLocked*/(account.getCuenabled()!=null && account.getCuenabled().booleanValue()?true:false),
				Collections.singleton(createAuthority(account)));
		user.setIdAccount(account.getCufkaccount());
		user.setName(account.getCuusername());
		user.setRole(User.ROLES.ROLE_CUSTOMER.name());
		user.setUserId(account.getCuid());
		
		return user;
	}

	private GrantedAuthority createAuthority(Customer account) {
		return new SimpleGrantedAuthority(User.ROLES.ROLE_CUSTOMER.name());
	}
	
	
	public void signin(Customer account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private Authentication authenticate(Customer account) {
		return new UsernamePasswordAuthenticationToken(createUserFromCustomer(account), null, Collections.singleton(createAuthority(account)));		
	}

	public void signin(User account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private Authentication authenticate(User account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));		
	}
	
	private UserData createUser(User account) {
		boolean accountNonExpired = true;// account.getUenable()==null || account.getUenable().booleanValue();
		boolean credentialsNonExpired = true; //account.getUenable()==null || account.getUenable().booleanValue();
		boolean accountNonLocked = true;// account.getUenable()==null || account.getUenable().booleanValue();
		boolean enabled = account.getUenable()==null || account.getUenable().booleanValue();
		UserData user = new UserData(account.getUemail(), account.getUpasswd(),
				enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,
				Collections.singleton(createAuthority(account)));
		
		//(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		user.setIdAccount(account.getUaccount());
		user.setName(account.getUname());
		user.setRole(account.getUrole());
		user.setUserId(account.getUid());
		
		return user;
	}

	private GrantedAuthority createAuthority(User account) {
		return new SimpleGrantedAuthority(account.getUrole());
	}
	
	public User findById(Integer userId) {		
		//return accountRepository.findOne(Long.valueOf(userId.intValue()));
		return accountRepository.findById(userId);
	}
	
	@Transactional(readOnly=true)
	public List<User> findAll(Integer idAccount) {
		return accountRepository.findAllByAccount(idAccount);
	}

}
