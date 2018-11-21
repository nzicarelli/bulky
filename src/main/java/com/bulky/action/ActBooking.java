package com.bulky.action;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the act_booking database table.
 * 
 */
@Entity
@Table(name="act_booking")
@NamedQuery(name="ActBooking.findAll", query="SELECT a FROM ActBooking a")
public class ActBooking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer bid;

	private Integer baccount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date bdate;

	private String bdescr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date bdtmod;

	private Integer bfkactivity;

	private Integer bfkaddress;

	private Integer bfkcatg;

	private Integer bfkcustomer;

	private String bnote;

	private Integer bqty;

	private Integer bstatus;

	public ActBooking() {
	}

	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Integer getBaccount() {
		return this.baccount;
	}

	public void setBaccount(Integer baccount) {
		this.baccount = baccount;
	}

	public Date getBdate() {
		return this.bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public String getBdescr() {
		return this.bdescr;
	}

	public void setBdescr(String bdescr) {
		this.bdescr = bdescr;
	}

	public Date getBdtmod() {
		return this.bdtmod;
	}

	public void setBdtmod(Date bdtmod) {
		this.bdtmod = bdtmod;
	}

	public Integer getBfkactivity() {
		return this.bfkactivity;
	}

	public void setBfkactivity(Integer bfkactivity) {
		this.bfkactivity = bfkactivity;
	}

	public Integer getBfkaddress() {
		return this.bfkaddress;
	}

	public void setBfkaddress(Integer bfkaddress) {
		this.bfkaddress = bfkaddress;
	}

	public Integer getBfkcatg() {
		return this.bfkcatg;
	}

	public void setBfkcatg(Integer bfkcatg) {
		this.bfkcatg = bfkcatg;
	}

	public Integer getBfkcustomer() {
		return this.bfkcustomer;
	}

	public void setBfkcustomer(Integer bfkcustomer) {
		this.bfkcustomer = bfkcustomer;
	}

	public String getBnote() {
		return this.bnote;
	}

	public void setBnote(String bnote) {
		this.bnote = bnote;
	}

	public Integer getBqty() {
		return this.bqty;
	}

	public void setBqty(Integer bqty) {
		this.bqty = bqty;
	}

	public Integer getBstatus() {
		return this.bstatus;
	}

	public void setBstatus(Integer bstatus) {
		this.bstatus = bstatus;
	}

}