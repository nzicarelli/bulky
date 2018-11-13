package com.bulky.account;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@Table(name="account")
@NamedQueries({
	@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
})
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer aid;

	private Integer acredit;

	@Temporal(TemporalType.TIMESTAMP)
	private Date adtmod;

	private String alogo;

	private String aname;

	private Integer astatus;

	public Account() {
	}

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getAcredit() {
		return this.acredit;
	}

	public void setAcredit(Integer acredit) {
		this.acredit = acredit;
	}

	public Date getAdtmod() {
		return this.adtmod;
	}

	public void setAdtmod(Date adtmod) {
		this.adtmod = adtmod;
	}

	public String getAlogo() {
		return this.alogo;
	}

	public void setAlogo(String alogo) {
		this.alogo = alogo;
	}

	public String getAname() {
		return this.aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public Integer getAstatus() {
		return this.astatus;
	}

	public void setAstatus(Integer astatus) {
		this.astatus = astatus;
	}

}