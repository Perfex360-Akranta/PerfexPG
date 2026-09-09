package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class VocTlChecklistcustomer {

	private  Object [] saveArray = null;  
	private List<VocTlChecklistcustomer> customerlist;
	private String flag;

	public enum   tableFldConstants
	{
		keyid, vocc_keyid, vchm_keyid, tempfield2, tempfield3, tempfield4
		, active, createdby, createdon, modifiedon
	}

	public VocTlChecklistcustomer()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getVclcKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setVclcKeyid(String vclcKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = vclcKeyid;
	}

	public String getVclcVoccKeyid() {
		return (String) saveArray[ tableFldConstants.vocc_keyid.ordinal() ];
	}

	public void setVclcVoccKeyid(String vclcVoccKeyid) {
		saveArray[ tableFldConstants.vocc_keyid.ordinal() ] = vclcVoccKeyid;
	}

	public String getVclcVchmKeyid() {
		return (String) saveArray[ tableFldConstants.vchm_keyid.ordinal() ];
	}

	public void setVclcVchmKeyid(String vclcVchmKeyid) {
		saveArray[ tableFldConstants.vchm_keyid.ordinal() ] = vclcVchmKeyid;
	}

	public String getVclcTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setVclcTempfield2(String vclcTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = vclcTempfield2;
	}

	public String getVclcTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setVclcTempfield3(String vclcTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = vclcTempfield3;
	}

	public String getVclcTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setVclcTempfield4(String vclcTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = vclcTempfield4;
	}

	public String getVclcActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setVclcActive(String vclcActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = vclcActive;
	}

	public String getVclcCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setVclcCreatedby(String vclcCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = vclcCreatedby;
	}

	public String getVclcCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setVclcCreatedon(String vclcCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = vclcCreatedon;
	}

	public String getVclcModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setVclcModifiedon(String vclcModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = vclcModifiedon;
	}

	public void setCustomerlist(List<VocTlChecklistcustomer> customerlist) {
		this.customerlist = customerlist;
	}

	public List<VocTlChecklistcustomer> getCustomerlist() {
		return customerlist;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

}

