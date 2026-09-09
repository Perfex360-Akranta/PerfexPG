package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTaskdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, tmkm_keyid, task, ksa, ksadec, basicemnt, tempfield3
		, tempfield4, tempfield5, skrm_keyid, spoke_keyid, active, createdby
		, createdon, modifiedon
	}
	

	public EntTlTaskdtl()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTmkdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTmkdKeyid(String tmkdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tmkdKeyid;
	}

	public String getTmkdTmkmKeyid() {
		return (String) saveArray[ tableFldConstants.tmkm_keyid.ordinal() ];
	}

	public void setTmkdTmkmKeyid(String tmkdTmkmKeyid) {
		saveArray[ tableFldConstants.tmkm_keyid.ordinal() ] = tmkdTmkmKeyid;
	}

	public String getTmkdTask() {
		return (String) saveArray[ tableFldConstants.task.ordinal() ];
	}

	public void setTmkdTask(String tmkdTask) {
		saveArray[ tableFldConstants.task.ordinal() ] = tmkdTask;
	}

	public String getTmkdKsa() {
		return (String) saveArray[ tableFldConstants.ksa.ordinal() ];
	}

	public void setTmkdKsa(String tmkdKsa) {
		saveArray[ tableFldConstants.ksa.ordinal() ] = tmkdKsa;
	}
	
	public String getTmkdKsadesc() {
		return (String) saveArray[ tableFldConstants.ksadec.ordinal() ];
	}

	public void setTmkdKsadesc(String tmkdKsadesc) {
		saveArray[ tableFldConstants.ksadec.ordinal() ] = tmkdKsadesc;
	}

	public String getTmkdBasicemnt() {
		return (String) saveArray[ tableFldConstants.basicemnt.ordinal() ];
	}
	
	public void setTmkdBasicemnt(String tmkdBasicemnt) {
		saveArray[ tableFldConstants.basicemnt.ordinal() ] = tmkdBasicemnt;
	}

	public String getTmkdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTmkdTempfield3(String tmkdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = tmkdTempfield3;
	}

	public String getTmkdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setTmkdTempfield4(String tmkdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = tmkdTempfield4;
	}

	public String getTmkdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setTmkdTempfield5(String tmkdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = tmkdTempfield5;
	}

	public String getTmkdSkrmKeyid() {
		return (String) saveArray[ tableFldConstants.skrm_keyid.ordinal() ];
	}

	public void setTmkdSkrmKeyid(String tmkdSkrmKeyid) {
		saveArray[ tableFldConstants.skrm_keyid.ordinal() ] = tmkdSkrmKeyid;
	}

	public String getTmkdSpokeKeyid() {
		return (String) saveArray[ tableFldConstants.spoke_keyid.ordinal() ];
	}

	public void setTmkdSpokeKeyid(String tmkdSpokeKeyid) {
		saveArray[ tableFldConstants.spoke_keyid.ordinal() ] = tmkdSpokeKeyid;
	}

	public String getTmkdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTmkdActive(String tmkdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = tmkdActive;
	}

	public String getTmkdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTmkdCreatedby(String tmkdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tmkdCreatedby;
	}

	public String getTmkdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTmkdCreatedon(String tmkdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tmkdCreatedon;
	}

	public String getTmkdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTmkdModifiedon(String tmkdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tmkdModifiedon;
	}

}

