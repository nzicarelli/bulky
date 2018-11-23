package com.bulky.customer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the catgzone database table.
 * 
 */
@Entity
@Table(name="catgzone")
@NamedQuery(name="Catgzone.findAll", query="SELECT c FROM Catgzone c")
public class Catgzone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer zid;

	private String zdescr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date zdtins;

	@Temporal(TemporalType.TIMESTAMP)
	private Date zdtmod;

	private Integer zfkaccount;

	private Integer zusermod;

	public Catgzone() {
	}

	public Integer getZid() {
		return this.zid;
	}

	public void setZid(Integer zid) {
		this.zid = zid;
	}

	public String getZdescr() {
		return this.zdescr;
	}

	public void setZdescr(String zdescr) {
		this.zdescr = zdescr;
	}

	public Date getZdtins() {
		return this.zdtins;
	}

	public void setZdtins(Date zdtins) {
		this.zdtins = zdtins;
	}

	public Date getZdtmod() {
		return this.zdtmod;
	}

	public void setZdtmod(Date zdtmod) {
		this.zdtmod = zdtmod;
	}

	public Integer getZfkaccount() {
		return this.zfkaccount;
	}

	public void setZfkaccount(Integer zfkaccount) {
		this.zfkaccount = zfkaccount;
	}

	public Integer getZusermod() {
		return this.zusermod;
	}

	public void setZusermod(Integer zusermod) {
		this.zusermod = zusermod;
	}

}