package com.bulky.action;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the activity database table.
 * 
 */
@Entity
@Table(name="activity")
@NamedQuery(name="Activity.findAll", query="SELECT a FROM Activity a")
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer aid;

	private Integer aaccount;

	private Integer aassign;

	private String adescr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date adtmod;

	private Integer afkcustomer;

	private Integer afktype;

	private Integer aowner;

	private Integer astatus;

	private String asubject;

	private String atitle;
	
	private Integer afklead;
	
	private Integer alead_from_status;
	
	private Integer alead_to_status;
	
	

	public Activity() {
	}

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getAaccount() {
		return this.aaccount;
	}

	public void setAaccount(Integer aaccount) {
		this.aaccount = aaccount;
	}

	public Integer getAassign() {
		return this.aassign;
	}

	public void setAassign(Integer aassign) {
		this.aassign = aassign;
	}

	public String getAdescr() {
		return this.adescr;
	}

	public void setAdescr(String adescr) {
		this.adescr = adescr;
	}

	public Date getAdtmod() {
		return this.adtmod;
	}

	public void setAdtmod(Date adtmod) {
		this.adtmod = adtmod;
	}

	public Integer getAfkcustomer() {
		return this.afkcustomer;
	}

	public void setAfkcustomer(Integer afkcustomer) {
		this.afkcustomer = afkcustomer;
	}

	public Integer getAfktype() {
		return this.afktype;
	}

	public void setAfktype(Integer afktype) {
		this.afktype = afktype;
	}

	public Integer getAowner() {
		return this.aowner;
	}

	public void setAowner(Integer aowner) {
		this.aowner = aowner;
	}

	public Integer getAstatus() {
		return this.astatus;
	}

	public void setAstatus(Integer astatus) {
		this.astatus = astatus;
	}

	public String getAsubject() {
		return this.asubject;
	}

	public void setAsubject(String asubject) {
		this.asubject = asubject;
	}

	public String getAtitle() {
		return this.atitle;
	}

	public void setAtitle(String atitle) {
		this.atitle = atitle;
	}

	public Integer getAfklead() {
		return afklead;
	}

	public void setAfklead(Integer afklead) {
		this.afklead = afklead;
	}

	public void setAlead_from_status(Integer alead_from_status) {
		this.alead_from_status = alead_from_status;
	}
	
	public Integer getAlead_from_status() {
		return alead_from_status;
	}
	
	public void setAlead_to_status(Integer alead_to_status) {
		this.alead_to_status = alead_to_status;
	}
	
	public Integer getAlead_to_status() {
		return alead_to_status;
	}
}