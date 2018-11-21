package com.bulky.customer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name="address")
@NamedQueries({
	@NamedQuery(name=Address.FIND_BY_CUSTOMER_ID, query="SELECT a FROM Address a WHERE a.adfkcustomer = :id ORDER BY a.adaddress ")	
})
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_CUSTOMER_ID = "Address.findByCustomerId";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer adid;

	private String adaddress;

	private String adcap;

	private String adcomune;

	@Temporal(TemporalType.TIMESTAMP)
	private Date addtins;

	@Temporal(TemporalType.TIMESTAMP)
	private Date addtmod;

	private Integer adfkaccount;

	private Integer adfkcustomer;

	private Integer adfkzona;

	private String adnormalizzato;

	private String adnum;

	private String adprov;

	private String adsiglaprov;

	private Integer adusermod;

	public Address() {
	}

	public Integer getAdid() {
		return this.adid;
	}

	public void setAdid(Integer adid) {
		this.adid = adid;
	}

	public String getAdaddress() {
		return this.adaddress;
	}

	public void setAdaddress(String adaddress) {
		this.adaddress = adaddress;
	}

	public String getAdcap() {
		return this.adcap;
	}

	public void setAdcap(String adcap) {
		this.adcap = adcap;
	}

	public String getAdcomune() {
		return this.adcomune;
	}

	public void setAdcomune(String adcomune) {
		this.adcomune = adcomune;
	}

	public Date getAddtins() {
		return this.addtins;
	}

	public void setAddtins(Date addtins) {
		this.addtins = addtins;
	}

	public Date getAddtmod() {
		return this.addtmod;
	}

	public void setAddtmod(Date addtmod) {
		this.addtmod = addtmod;
	}

	public Integer getAdfkaccount() {
		return this.adfkaccount;
	}

	public void setAdfkaccount(Integer adfkaccount) {
		this.adfkaccount = adfkaccount;
	}

	public Integer getAdfkcustomer() {
		return this.adfkcustomer;
	}

	public void setAdfkcustomer(Integer adfkcustomer) {
		this.adfkcustomer = adfkcustomer;
	}

	public Integer getAdfkzona() {
		return this.adfkzona;
	}

	public void setAdfkzona(Integer adfkzona) {
		this.adfkzona = adfkzona;
	}

	public String getAdnormalizzato() {
		return this.adnormalizzato;
	}

	public void setAdnormalizzato(String adnormalizzato) {
		this.adnormalizzato = adnormalizzato;
	}

	public String getAdnum() {
		return this.adnum;
	}

	public void setAdnum(String adnum) {
		this.adnum = adnum;
	}

	public String getAdprov() {
		return this.adprov;
	}

	public void setAdprov(String adprov) {
		this.adprov = adprov;
	}

	public String getAdsiglaprov() {
		return this.adsiglaprov;
	}

	public void setAdsiglaprov(String adsiglaprov) {
		this.adsiglaprov = adsiglaprov;
	}

	public Integer getAdusermod() {
		return this.adusermod;
	}

	public void setAdusermod(Integer adusermod) {
		this.adusermod = adusermod;
	}

}