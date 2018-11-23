package com.bulky.action;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lead_catg_stato database table.
 * 
 */
@Entity
@Table(name="lead_catg_stato")
@NamedQuery(name="LeadCatgStato.findAll", query="SELECT l FROM LeadCatgStato l")
public class LeadCatgStato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer lcsid;

	private String lcsdescrizione;

	private Integer lcsfkaccount;

	private Integer lcsfktipolead;

	private Integer lcsorder;

	private Boolean lcsvisiblecustomer;

	private Boolean lcsvisibleuser;

	public LeadCatgStato() {
	}

	public Integer getLcsid() {
		return this.lcsid;
	}

	public void setLcsid(Integer lcsid) {
		this.lcsid = lcsid;
	}

	public String getLcsdescrizione() {
		return this.lcsdescrizione;
	}

	public void setLcsdescrizione(String lcsdescrizione) {
		this.lcsdescrizione = lcsdescrizione;
	}

	public Integer getLcsfkaccount() {
		return this.lcsfkaccount;
	}

	public void setLcsfkaccount(Integer lcsfkaccount) {
		this.lcsfkaccount = lcsfkaccount;
	}

	public Integer getLcsfktipolead() {
		return this.lcsfktipolead;
	}

	public void setLcsfktipolead(Integer lcsfktipolead) {
		this.lcsfktipolead = lcsfktipolead;
	}

	public Integer getLcsorder() {
		return this.lcsorder;
	}

	public void setLcsorder(Integer lcsorder) {
		this.lcsorder = lcsorder;
	}

	public Boolean getLcsvisiblecustomer() {
		return this.lcsvisiblecustomer;
	}

	public void setLcsvisiblecustomer(Boolean lcsvisiblecustomer) {
		this.lcsvisiblecustomer = lcsvisiblecustomer;
	}

	public Boolean getLcsvisibleuser() {
		return this.lcsvisibleuser;
	}

	public void setLcsvisibleuser(Boolean lcsvisibleuser) {
		this.lcsvisibleuser = lcsvisibleuser;
	}

}