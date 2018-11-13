package com.bulky.account;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name=User.FIND_BY_ACCOUNT, query="SELECT u FROM User u WHERE u.uaccount = :id ORDER BY u.uname "),
	@NamedQuery(name=User.FIND_BY_USERNAME, query="SELECT u FROM User u WHERE u.uemail = :email ORDER BY u.uname ")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_ACCOUNT ="User.FindByAccount";
	public static final String FIND_BY_USERNAME ="User.FindByUsername";
	
	public static enum ROLES {ROLE_ADMIN,ROLE_ACCOUNT,ROLE_USER,ROLE_EXTERNAL};


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer uid;

	private Integer uaccount;

	private String ucode01;

	private String ucode02;

	@Temporal(TemporalType.TIMESTAMP)
	private Date udtmod;

	private String uemail;

	private Boolean uenable;

	private Integer umod;

	private String uname;

	private String upasswd;

	private String urole;

	private Integer utype;

	public User() {
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getUaccount() {
		return this.uaccount;
	}

	public void setUaccount(Integer uaccount) {
		this.uaccount = uaccount;
	}

	public String getUcode01() {
		return this.ucode01;
	}

	public void setUcode01(String ucode01) {
		this.ucode01 = ucode01;
	}

	public String getUcode02() {
		return this.ucode02;
	}

	public void setUcode02(String ucode02) {
		this.ucode02 = ucode02;
	}

	public Date getUdtmod() {
		return this.udtmod;
	}

	public void setUdtmod(Date udtmod) {
		this.udtmod = udtmod;
	}

	public String getUemail() {
		return this.uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public Boolean getUenable() {
		return this.uenable;
	}

	public void setUenable(Boolean uenable) {
		this.uenable = uenable;
	}

	public Integer getUmod() {
		return this.umod;
	}

	public void setUmod(Integer umod) {
		this.umod = umod;
	}

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpasswd() {
		return this.upasswd;
	}

	public void setUpasswd(String upasswd) {
		this.upasswd = upasswd;
	}

	public String getUrole() {
		return this.urole;
	}

	public void setUrole(String urole) {
		this.urole = urole;
	}

	public Integer getUtype() {
		return this.utype;
	}

	public void setUtype(Integer utype) {
		this.utype = utype;
	}

}