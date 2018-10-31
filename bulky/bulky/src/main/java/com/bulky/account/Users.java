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
	private String emal;
	
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

	
		

	
}
