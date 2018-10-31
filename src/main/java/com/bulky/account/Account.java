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

@Entity
@Table(name = "account")
public class Account implements java.io.Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="aid")
	private Integer id;
	
	@Column(name="acredit")
	private Integer credit;
	
	@Column(name = "aname")
	private String name;
	
	@Column(name = "alogo")
	private String logo;
	
	@Column(name="astatus")
	private Integer status;
	
	@Column(name="adtmod")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtmod;
	
//	@JsonIgnore
//	private String password;

//	private String role = "ROLE_USER";

//	private Instant created;
		

    protected Account() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getDtmod() {
		return dtmod;
	}

	public void setDtmod(Date dtmod) {
		this.dtmod = dtmod;
	}
		

	
}
