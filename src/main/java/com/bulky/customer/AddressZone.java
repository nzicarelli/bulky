package com.bulky.customer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the address_zone database table.
 * 
 */
@Entity
@Table(name="address_zone")
@NamedQuery(name="AddressZone.findAll", query="SELECT a FROM AddressZone a")
public class AddressZone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer azid;

	private String azaddress;

	private String azcap;

	private String azcomune;

	@Temporal(TemporalType.TIMESTAMP)
	private Date azdtins;

	@Temporal(TemporalType.TIMESTAMP)
	private Date azdtmod;

	private Integer azfkaccount;

	private Integer azfkzona;

	private String azprov;

	private String azsiglaprov;

	private Integer azusermod;

	public AddressZone() {
	}

	public Integer getAzid() {
		return this.azid;
	}

	public void setAzid(Integer azid) {
		this.azid = azid;
	}

	public String getAzaddress() {
		return this.azaddress;
	}

	public void setAzaddress(String azaddress) {
		this.azaddress = azaddress;
	}

	public String getAzcap() {
		return this.azcap;
	}

	public void setAzcap(String azcap) {
		this.azcap = azcap;
	}

	public String getAzcomune() {
		return this.azcomune;
	}

	public void setAzcomune(String azcomune) {
		this.azcomune = azcomune;
	}

	public Date getAzdtins() {
		return this.azdtins;
	}

	public void setAzdtins(Date azdtins) {
		this.azdtins = azdtins;
	}

	public Date getAzdtmod() {
		return this.azdtmod;
	}

	public void setAzdtmod(Date azdtmod) {
		this.azdtmod = azdtmod;
	}

	public Integer getAzfkaccount() {
		return this.azfkaccount;
	}

	public void setAzfkaccount(Integer azfkaccount) {
		this.azfkaccount = azfkaccount;
	}

	public Integer getAzfkzona() {
		return this.azfkzona;
	}

	public void setAzfkzona(Integer azfkzona) {
		this.azfkzona = azfkzona;
	}

	public String getAzprov() {
		return this.azprov;
	}

	public void setAzprov(String azprov) {
		this.azprov = azprov;
	}

	public String getAzsiglaprov() {
		return this.azsiglaprov;
	}

	public void setAzsiglaprov(String azsiglaprov) {
		this.azsiglaprov = azsiglaprov;
	}

	public Integer getAzusermod() {
		return this.azusermod;
	}

	public void setAzusermod(Integer azusermod) {
		this.azusermod = azusermod;
	}

}