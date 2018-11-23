package com.bulky.plannig;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the planning database table.
 * 
 */
@Entity
@NamedQuery(name="Planning.findAll", query="SELECT p FROM Planning p")
public class Planning implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer plnid;

	private String plndescr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date plndtins;

	@Temporal(TemporalType.TIMESTAMP)
	private Date plndtmod;

	private Integer plnfkaccount;

	private Integer plnfkzona;

	private Integer plnowner;

	private Integer plnusrmod;

	public Planning() {
	}

	public Integer getPlnid() {
		return this.plnid;
	}

	public void setPlnid(Integer plnid) {
		this.plnid = plnid;
	}

	public String getPlndescr() {
		return this.plndescr;
	}

	public void setPlndescr(String plndescr) {
		this.plndescr = plndescr;
	}

	public Date getPlndtins() {
		return this.plndtins;
	}

	public void setPlndtins(Date plndtins) {
		this.plndtins = plndtins;
	}

	public Date getPlndtmod() {
		return this.plndtmod;
	}

	public void setPlndtmod(Date plndtmod) {
		this.plndtmod = plndtmod;
	}

	public Integer getPlnfkaccount() {
		return this.plnfkaccount;
	}

	public void setPlnfkaccount(Integer plnfkaccount) {
		this.plnfkaccount = plnfkaccount;
	}

	public Integer getPlnfkzona() {
		return this.plnfkzona;
	}

	public void setPlnfkzona(Integer plnfkzona) {
		this.plnfkzona = plnfkzona;
	}

	public Integer getPlnowner() {
		return this.plnowner;
	}

	public void setPlnowner(Integer plnowner) {
		this.plnowner = plnowner;
	}

	public Integer getPlnusrmod() {
		return this.plnusrmod;
	}

	public void setPlnusrmod(Integer plnusrmod) {
		this.plnusrmod = plnusrmod;
	}

}