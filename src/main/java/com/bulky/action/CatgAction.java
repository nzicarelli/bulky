package com.bulky.action;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the catg_action database table.
 * 
 */
@Entity
@Table(name="catg_action")
@NamedQueries({
	@NamedQuery(name=CatgAction.FIND_ALL, query="SELECT c FROM CatgAction c WHERE c.viewLevel>0  ORDER BY c.caid "),
	@NamedQuery(name=CatgAction.FIND_BY_ID, query="SELECT c FROM CatgAction c WHERE c.caid = :id  "),
	@NamedQuery(name=CatgAction.FIND_BY_LEAD, query="SELECT c FROM CatgAction c WHERE c.viewLevel>0 AND c.cafktlead = :id ORDER BY c.cadescr  ")
})
public class CatgAction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL="CatgAction.findAll";

	public static final String FIND_BY_ID = "CatgAction.FINDBYID";
	public static final String FIND_BY_LEAD = "CatgAction.FINDBYTIPOLEAD";
	
	public static enum DOC_TYPE {STANDARD, BULKY};

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer caid;

	private String cadescr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cadtins;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cadtmod;

	private Boolean caenabled;

	private Integer cafkaccount;

	private Integer cafktlead;

	private String canote;

	private Integer castatus;

	private String catitle;

	private Integer causermod;

	@Column(name="cacusenable" ,columnDefinition="BIT NULL")
	private Boolean customerEnabled;
	
	@Column(name="cadoctype" ,columnDefinition="VARCHAR(100) NULL")
	private String cadoctype;
	
	@Column(name="caviewlevel")
	private Integer viewLevel;
	
	
	@Column(name="ca_lead_from_status")
	private Integer fromStatoLead;
	
	
	@Column(name="ca_lead_to_status")
	private Integer toStatoLead;
		
	

	public CatgAction() {
	}

	public Integer getCaid() {
		return this.caid;
	}

	public void setCaid(Integer caid) {
		this.caid = caid;
	}

	public String getCadescr() {
		return this.cadescr;
	}

	public void setCadescr(String cadescr) {
		this.cadescr = cadescr;
	}

	public Date getCadtins() {
		return this.cadtins;
	}

	public void setCadtins(Date cadtins) {
		this.cadtins = cadtins;
	}

	public Date getCadtmod() {
		return this.cadtmod;
	}

	public void setCadtmod(Date cadtmod) {
		this.cadtmod = cadtmod;
	}

	public Boolean getCaenabled() {
		return this.caenabled;
	}

	public void setCaenabled(Boolean caenabled) {
		this.caenabled = caenabled;
	}

	public Integer getCafkaccount() {
		return this.cafkaccount;
	}

	public void setCafkaccount(Integer cafkaccount) {
		this.cafkaccount = cafkaccount;
	}

	public Integer getCafktlead() {
		return this.cafktlead;
	}

	public void setCafktlead(Integer cafktlead) {
		this.cafktlead = cafktlead;
	}

	public String getCanote() {
		return this.canote;
	}

	public void setCanote(String canote) {
		this.canote = canote;
	}

	public Integer getCastatus() {
		return this.castatus;
	}

	public void setCastatus(Integer castatus) {
		this.castatus = castatus;
	}

	public String getCatitle() {
		return this.catitle;
	}

	public void setCatitle(String catitle) {
		this.catitle = catitle;
	}

	public Integer getCausermod() {
		return this.causermod;
	}

	public void setCausermod(Integer causermod) {
		this.causermod = causermod;
	}
	
	public Boolean getCustomerEnabled() {
		return customerEnabled;
	}

	public void setCustomerEnabled(Boolean customerEnabled) {
		this.customerEnabled = customerEnabled;
	}

	public String getCadoctype() {
		return cadoctype;
	}

	public void setCadoctype(String cadoctype) {
		this.cadoctype = cadoctype;
	}
	
	public Integer getViewLevel() {
		return viewLevel;
	}
	public void setViewLevel(Integer viewLevel) {
		this.viewLevel = viewLevel;
	}
	
	public Integer getFromStatoLead() {
		return fromStatoLead;
	}
	public void setFromStatoLead(Integer fromStatoLead) {
		this.fromStatoLead = fromStatoLead;
	}
	
	public Integer getToStatoLead() {
		return toStatoLead;
	}
	
	public void setToStatoLead(Integer toStatoLead) {
		this.toStatoLead = toStatoLead;
	}

}