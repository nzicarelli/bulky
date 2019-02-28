package com.bulky.action;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lead database table.
 * 
 */
@Entity
@Table(name="lead")
@NamedQueries({
	@NamedQuery(name=Lead.FIND_BY_ACCOUNT, query="SELECT l FROM Lead l WHERE l.laccount = :id ORDER BY l.ldtmod DESC"),
	@NamedQuery(name=Lead.FIND_BY_OWNER, query="SELECT l FROM Lead l WHERE l.lowner = :id ORDER BY l.ldtmod DESC"),
	@NamedQuery(name=Lead.FIND_BY_ASSIGN, query="SELECT l FROM Lead l WHERE l.lassign = :id ORDER BY l.ldtmod DESC"),
	@NamedQuery(name=Lead.FIND_BY_CUSTOMER, query="SELECT l FROM Lead l WHERE l.lfkcustomer = :id ORDER BY l.ldtmod DESC"),
	@NamedQuery(name=Lead.FIND_BY_ID, query="SELECT l FROM Lead l WHERE l.lid = :id ")
})
public class Lead implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_ASSIGN="Lead.findAssign";
	public static final String FIND_BY_OWNER="Lead.findOwner";
	public static final String FIND_BY_ACCOUNT="Lead.findAccount";
	public static final String FIND_BY_CUSTOMER= "Lead.findByCustomer";
	public static final String FIND_BY_ID = "Lead.findById";;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer lid;

	private Integer laccount;

	private Integer lassign;

	private String ldescr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ldtmod;

	private Integer lfkcustomer;

	private Integer lowner;

	private Integer lstatus;

	private Integer ltype;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ledtins;

	private Integer lefkmediaarrivo;
	
	private Boolean annullabile = true;
	

	public Lead() {
	}

	public Integer getLid() {
		return this.lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public Integer getLaccount() {
		return this.laccount;
	}

	public void setLaccount(Integer laccount) {
		this.laccount = laccount;
	}

	public Integer getLassign() {
		return this.lassign;
	}

	public void setLassign(Integer lassign) {
		this.lassign = lassign;
	}

	public String getLdescr() {
		return this.ldescr;
	}

	public void setLdescr(String ldescr) {
		this.ldescr = ldescr;
	}

	public Date getLdtmod() {
		return this.ldtmod;
	}

	public void setLdtmod(Date ldtmod) {
		this.ldtmod = ldtmod;
	}

	public Integer getLfkcustomer() {
		return this.lfkcustomer;
	}

	public void setLfkcustomer(Integer lfkcustomer) {
		this.lfkcustomer = lfkcustomer;
	}

	public Integer getLowner() {
		return this.lowner;
	}

	public void setLowner(Integer lowner) {
		this.lowner = lowner;
	}

	public Integer getLstatus() {
		return this.lstatus;
	}

	public void setLstatus(Integer lstatus) {
		this.lstatus = lstatus;
	}

	public Integer getLtype() {
		return this.ltype;
	}

	public void setLtype(Integer ltype) {
		this.ltype = ltype;
	}

	public Date getLedtins() {
		return ledtins;
	}

	public void setLedtins(Date ledtins) {
		this.ledtins = ledtins;
	}

	public Integer getLefkmediaarrivo() {
		return lefkmediaarrivo;
	}

	public void setLefkmediaarrivo(Integer lefkmediaarrivo) {
		this.lefkmediaarrivo = lefkmediaarrivo;
	}	
	
	public Boolean getAnnullabile() {
		return annullabile;
	}
	
	public void setAnnullabile(Boolean annullabile) {
		this.annullabile = annullabile;
	}
}