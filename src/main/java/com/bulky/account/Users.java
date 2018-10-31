package com.bulky.account;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class Users implements java.io.Serializable {
	
	public static enum ROLES {ROLE_ADMIN,ROLE_ACCOUNT,ROLE_USER,ROLE_EXTERNAL};

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uid")
	private Integer id;
	
	@Column(name="uname")
	private String name;
	
	@JsonIgnore
	@Column(name = "upasswd")
	private String password;
	
	@Column(name = "uemail")
	private String email;
	
	@Column(name="ucode01")
	private String code01;
	
	@Column(name="ucode02")
	private String code02;
		
	@Column(name="uaccount")
	private Integer accountId;
	
	@Column(name="utype")
	private Integer userType;
	
	@Column(name="urole")
	private String role = "ROLE_USER";
	
	@Column(name="udtmod")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtmod;
		
	@Column(name="umod")
	private Integer userLastUpdate;
	

    public Users() {

	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCode01() {
		return code01;
	}


	public void setCode01(String code01) {
		this.code01 = code01;
	}


	public String getCode02() {
		return code02;
	}


	public void setCode02(String code02) {
		this.code02 = code02;
	}


	public Integer getAccountId() {
		return accountId;
	}


	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}


	public Integer getUserType() {
		return userType;
	}


	public void setUserType(Integer userType) {
		this.userType = userType;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Date getDtmod() {
		return dtmod;
	}


	public void setDtmod(Date dtmod) {
		this.dtmod = dtmod;
	}


	public Integer getUserLastUpdate() {
		return userLastUpdate;
	}


	public void setUserLastUpdate(Integer userLastUpdate) {
		this.userLastUpdate = userLastUpdate;
	}

	
		

	
}
