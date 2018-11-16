package com.bulky.account;

import java.util.Collections;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		UserData user = createUser(account);
		 
		return user;
	}
	
	public void signin(User account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private Authentication authenticate(User account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));		
	}
	
	private UserData createUser(User account) {
		UserData user = new UserData(account.getUemail(), account.getUpasswd(), Collections.singleton(createAuthority(account)));
		user.setIdAccount(account.getUaccount());
		user.setName(account.getUname());
		user.setRole(account.getUrole());
		
		return user;
	}

	private GrantedAuthority createAuthority(User account) {
		return new SimpleGrantedAuthority(account.getUrole());
	}
	
	public User findById(Integer userId) {
		return accountRepository.findOne(Long.valueOf(userId.intValue()));
	}

}
