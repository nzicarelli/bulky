package com.bulky.customer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@NamedQueries({
	@NamedQuery(name=Customer.FIND_BY_USERNAME, query="SELECT c FROM Customer c WHERE c.cuusername = :name ORDER BY c.cuid "),
	@NamedQuery(name=Customer.FIND_BY_ID, query="SELECT c FROM Customer c WHERE c.cuid = :id ORDER BY c.cuid "),
	@NamedQuery(name=Customer.FIND_BY_CF_PIVA, query="SELECT c FROM Customer c WHERE c.cucf = :cf OR c.cupiva= :piva ORDER BY c.cuid ")
})
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_USERNAME="Customer.FindByUsername";
	public static final String FIND_BY_CF_PIVA="Customer.FindByCfPiva";

	public static final String FIND_BY_ID = "Customer.FindById";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cuid;

	private String cucf;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cudtins;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cudtmod;

	private Boolean cuenabled;

	private Integer cufkaccount;

	private Integer cufktype;

	private String cuname;

	private String cunote;

	private String cupassword;

	private String cupiva;

	private String cusurname;

	private Integer cuusermod;

	private String cuusername;
	
	private String cucode01;
	
	private String cucode02;

	public Customer() {
	}

	public Integer getCuid() {
		return this.cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}

	public String getCucf() {
		return this.cucf;
	}

	public void setCucf(String cucf) {
		this.cucf = cucf;
	}

	public Date getCudtins() {
		return this.cudtins;
	}

	public void setCudtins(Date cudtins) {
		this.cudtins = cudtins;
	}

	public Date getCudtmod() {
		return this.cudtmod;
	}

	public void setCudtmod(Date cudtmod) {
		this.cudtmod = cudtmod;
	}

	public Boolean getCuenabled() {
		return this.cuenabled;
	}

	public void setCuenabled(Boolean cuenabled) {
		this.cuenabled = cuenabled;
	}

	public Integer getCufkaccount() {
		return this.cufkaccount;
	}

	public void setCufkaccount(Integer cufkaccount) {
		this.cufkaccount = cufkaccount;
	}

	public Integer getCufktype() {
		return this.cufktype;
	}

	public void setCufktype(Integer cufktype) {
		this.cufktype = cufktype;
	}

	public String getCuname() {
		return this.cuname;
	}

	public void setCuname(String cuname) {
		this.cuname = cuname;
	}

	public String getCunote() {
		return this.cunote;
	}

	public void setCunote(String cunote) {
		this.cunote = cunote;
	}

	public String getCupassword() {
		return this.cupassword;
	}

	public void setCupassword(String cupassword) {
		this.cupassword = cupassword;
	}

	public String getCupiva() {
		return this.cupiva;
	}

	public void setCupiva(String cupiva) {
		this.cupiva = cupiva;
	}

	public String getCusurname() {
		return this.cusurname;
	}

	public void setCusurname(String cusurname) {
		this.cusurname = cusurname;
	}

	public Integer getCuusermod() {
		return this.cuusermod;
	}

	public void setCuusermod(Integer cuusermod) {
		this.cuusermod = cuusermod;
	}

	public String getCuusername() {
		return this.cuusername;
	}

	public void setCuusername(String cuusername) {
		this.cuusername = cuusername;
	}

	public String getCucode01() {
		return cucode01;
	}

	public void setCucode01(String cucode01) {
		this.cucode01 = cucode01;
	}

	public String getCucode02() {
		return cucode02;
	}

	public void setCucode02(String cucode02) {
		this.cucode02 = cucode02;
	}

	
}