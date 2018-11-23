package com.bulky.action;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lead_catg_mediaarrivo database table.
 * 
 */
@Entity
@Table(name="lead_catg_mediaarrivo")
@NamedQuery(name="LeadCatgMediaarrivo.findAll", query="SELECT l FROM LeadCatgMediaarrivo l")
public class LeadCatgMediaarrivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer lcmaid;

	private String lcmadescrizione;

	private Integer lcmafkaccount;

	private Integer lcmafktipolead;

	private Boolean lcmaisweb;

	private Integer lcmaorder;

	private Boolean lcmavisiblecustomer;

	private Boolean lcmavisibleuser;

	public LeadCatgMediaarrivo() {
	}

	public Integer getLcmaid() {
		return this.lcmaid;
	}

	public void setLcmaid(Integer lcmaid) {
		this.lcmaid = lcmaid;
	}

	public String getLcmadescrizione() {
		return this.lcmadescrizione;
	}

	public void setLcmadescrizione(String lcmadescrizione) {
		this.lcmadescrizione = lcmadescrizione;
	}

	public Integer getLcmafkaccount() {
		return this.lcmafkaccount;
	}

	public void setLcmafkaccount(Integer lcmafkaccount) {
		this.lcmafkaccount = lcmafkaccount;
	}

	public Integer getLcmafktipolead() {
		return this.lcmafktipolead;
	}

	public void setLcmafktipolead(Integer lcmafktipolead) {
		this.lcmafktipolead = lcmafktipolead;
	}

	public Boolean getLcmaisweb() {
		return this.lcmaisweb;
	}

	public void setLcmaisweb(Boolean lcmaisweb) {
		this.lcmaisweb = lcmaisweb;
	}

	public Integer getLcmaorder() {
		return this.lcmaorder;
	}

	public void setLcmaorder(Integer lcmaorder) {
		this.lcmaorder = lcmaorder;
	}

	public Boolean getLcmavisiblecustomer() {
		return this.lcmavisiblecustomer;
	}

	public void setLcmavisiblecustomer(Boolean lcmavisiblecustomer) {
		this.lcmavisiblecustomer = lcmavisiblecustomer;
	}

	public Boolean getLcmavisibleuser() {
		return this.lcmavisibleuser;
	}

	public void setLcmavisibleuser(Boolean lcmavisibleuser) {
		this.lcmavisibleuser = lcmavisibleuser;
	}

}