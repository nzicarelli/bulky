package com.bulky.plannig;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the plan_detail database table.
 * 
 */
@Entity
@Table(name="plan_detail")
@NamedQuery(name="PlanDetail.findAll", query="SELECT p FROM PlanDetail p")
public class PlanDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pldid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date plddatefrom;

	@Temporal(TemporalType.TIMESTAMP)
	private Date plddateto;

	private String plddescr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date plddtins;

	private Integer pldfkaccount;

	private Integer pldfkplannig;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pldtmod;

	private Integer pldusrmod;
	
	private Integer pldfill;

	public PlanDetail() {
	}

	public Integer getPldid() {
		return this.pldid;
	}

	public void setPldid(Integer pldid) {
		this.pldid = pldid;
	}

	public Date getPlddatefrom() {
		return this.plddatefrom;
	}

	public void setPlddatefrom(Date plddatefrom) {
		this.plddatefrom = plddatefrom;
	}

	public Date getPlddateto() {
		return this.plddateto;
	}

	public void setPlddateto(Date plddateto) {
		this.plddateto = plddateto;
	}

	public String getPlddescr() {
		return this.plddescr;
	}

	public void setPlddescr(String plddescr) {
		this.plddescr = plddescr;
	}

	public Date getPlddtins() {
		return this.plddtins;
	}

	public void setPlddtins(Date plddtins) {
		this.plddtins = plddtins;
	}

	public Integer getPldfkaccount() {
		return this.pldfkaccount;
	}

	public void setPldfkaccount(Integer pldfkaccount) {
		this.pldfkaccount = pldfkaccount;
	}

	public Integer getPldfkplannig() {
		return this.pldfkplannig;
	}

	public void setPldfkplannig(Integer pldfkplannig) {
		this.pldfkplannig = pldfkplannig;
	}

	public Date getPldtmod() {
		return this.pldtmod;
	}

	public void setPldtmod(Date pldtmod) {
		this.pldtmod = pldtmod;
	}

	public Integer getPldusrmod() {
		return this.pldusrmod;
	}

	public void setPldusrmod(Integer pldusrmod) {
		this.pldusrmod = pldusrmod;
	}

	public Integer getPldfill() {
		return pldfill;
	}

	public void setPldfill(Integer pldfill) {
		this.pldfill = pldfill;
	}

}