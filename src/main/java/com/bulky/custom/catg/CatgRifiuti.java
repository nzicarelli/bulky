package com.bulky.custom.catg;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the catg_rifiuti database table.
 * 
 */
@Entity
@Table(name="catg_rifiuti")
@NamedQueries({
	@NamedQuery(name=CatgRifiuti.FIND_ALL, query="SELECT c FROM CatgRifiuti c ORDER BY c.crtype, c.crname")	
})
public class CatgRifiuti implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL = "CatgRifiuti.findAll";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer crid;

	private Integer craccount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date crdtmod;

	private Integer crincombro;

	private String crname;

	private String crnote;

	private Integer crqtymax;

	private Integer crqtymin;

	private String crtype;

	private String crudm;

	private Integer crusermod;

	public CatgRifiuti() {
	}

	public Integer getCrid() {
		return this.crid;
	}

	public void setCrid(Integer crid) {
		this.crid = crid;
	}

	public Integer getCraccount() {
		return this.craccount;
	}

	public void setCraccount(Integer craccount) {
		this.craccount = craccount;
	}

	public Date getCrdtmod() {
		return this.crdtmod;
	}

	public void setCrdtmod(Date crdtmod) {
		this.crdtmod = crdtmod;
	}

	public Integer getCrincombro() {
		return this.crincombro;
	}

	public void setCrincombro(Integer crincombro) {
		this.crincombro = crincombro;
	}

	public String getCrname() {
		return this.crname;
	}

	public void setCrname(String crname) {
		this.crname = crname;
	}

	public String getCrnote() {
		return this.crnote;
	}

	public void setCrnote(String crnote) {
		this.crnote = crnote;
	}

	public Integer getCrqtymax() {
		return this.crqtymax;
	}

	public void setCrqtymax(Integer crqtymax) {
		this.crqtymax = crqtymax;
	}

	public Integer getCrqtymin() {
		return this.crqtymin;
	}

	public void setCrqtymin(Integer crqtymin) {
		this.crqtymin = crqtymin;
	}

	public String getCrtype() {
		return this.crtype;
	}

	public void setCrtype(String crtype) {
		this.crtype = crtype;
	}

	public String getCrudm() {
		return this.crudm;
	}

	public void setCrudm(String crudm) {
		this.crudm = crudm;
	}

	public Integer getCrusermod() {
		return this.crusermod;
	}

	public void setCrusermod(Integer crusermod) {
		this.crusermod = crusermod;
	}

}