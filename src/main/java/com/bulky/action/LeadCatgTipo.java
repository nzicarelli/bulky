package com.bulky.action;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lead_catg_tipo database table.
 * 
 */
@Entity
@Table(name="lead_catg_tipo")
@NamedQuery(name="LeadCatgTipo.findAll", query="SELECT l FROM LeadCatgTipo l")
public class LeadCatgTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer lctid;

	private String lctdescrizione;

	private Integer lctfkaccount;

	private Integer lctorder;

	private Boolean lctvisiblecustomer;

	private Boolean lctvisibleuser;

	public LeadCatgTipo() {
	}

	public Integer getLctid() {
		return this.lctid;
	}

	public void setLctid(Integer lctid) {
		this.lctid = lctid;
	}

	public String getLctdescrizione() {
		return this.lctdescrizione;
	}

	public void setLctdescrizione(String lctdescrizione) {
		this.lctdescrizione = lctdescrizione;
	}

	public Integer getLctfkaccount() {
		return this.lctfkaccount;
	}

	public void setLctfkaccount(Integer lctfkaccount) {
		this.lctfkaccount = lctfkaccount;
	}

	public Integer getLctorder() {
		return this.lctorder;
	}

	public void setLctorder(Integer lctorder) {
		this.lctorder = lctorder;
	}

	public Boolean getLctvisiblecustomer() {
		return this.lctvisiblecustomer;
	}

	public void setLctvisiblecustomer(Boolean lctvisiblecustomer) {
		this.lctvisiblecustomer = lctvisiblecustomer;
	}

	public Boolean getLctvisibleuser() {
		return this.lctvisibleuser;
	}

	public void setLctvisibleuser(Boolean lctvisibleuser) {
		this.lctvisibleuser = lctvisibleuser;
	}

}