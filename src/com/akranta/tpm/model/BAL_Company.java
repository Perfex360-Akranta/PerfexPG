package com.akranta.tpm.model;

import java.util.Date;

public class BAL_Company {
	
	private String keyid;
	private String name;
	private String code;
	private String address;
	private String active;
	private String createdby;
	private Date createdon;
	private Date modifiedon;
	
	public String getKeyid() {
		return keyid;
	}
	public void setKeyid(String keyid) {
		System.out.println("keyid " + keyid);
		this.keyid = keyid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedon() {
		return createdon;
	}
	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}
	public Date getModifiedon() {
		return modifiedon;
	}
	public void setModifiedon(Date modifiedon) {
		this.modifiedon = modifiedon;
	}
}
