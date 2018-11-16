/**
 * 
 */
package com.bulky.account;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author kaala
 *
 */
public class UserData extends  org.springframework.security.core.userdetails.User{
	
	private static final long serialVersionUID = 1L;
	private String role;
	private Integer idAccount;
	private String name;
	
	

	public UserData(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);		
	}
	
	public UserData(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, true, true, true, true, authorities);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
