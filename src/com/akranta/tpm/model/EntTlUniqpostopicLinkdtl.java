package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlUniqpostopicLinkdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, tmtm_keyid, tmkd_keyid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, tempfield6, tempfield7, active, createdby
		, createdon, modifiedon
	}

	public EntTlUniqpostopicLinkdtl()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTmtdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTmtdKeyid(String tmtdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tmtdKeyid;
	}

	public String getTmtdTmtmKeyid() {
		return (String) saveArray[ tableFldConstants.tmtm_keyid.ordinal() ];
	}

	public void setTmtdTmtmKeyid(String tmtdTmtmKeyid) {
		saveArray[ tableFldConstants.tmtm_keyid.ordinal() ] = tmtdTmtmKeyid;
	}

	public String getTmtdTmkmKeyid() {
		return (String) saveArray[ tableFldConstants.tmkd_keyid.ordinal() ];
	}

	public void setTmtdTmkmKeyid(String tmtdTmkdKeyid) {
		saveArray[ tableFldConstants.tmkd_keyid.ordinal() ] = tmtdTmkdKeyid;
	}

	public String getTmtdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setTmtdTempfield1(String tmtdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = tmtdTempfield1;
	}

	public String getTmtdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTmtdTempfield2(String tmtdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tmtdTempfield2;
	}

	public String getTmtdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTmtdTempfield3(String tmtdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = tmtdTempfield3;
	}

	public String getTmtdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setTmtdTempfield4(String tmtdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = tmtdTempfield4;
	}

	public String getTmtdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setTmtdTempfield5(String tmtdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = tmtdTempfield5;
	}

	public String getTmtdTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setTmtdTempfield6(String tmtdTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = tmtdTempfield6;
	}

	public String getTmtdTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setTmtdTempfield7(String tmtdTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = tmtdTempfield7;
	}

	public String getTmtdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTmtdActive(String tmtdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = tmtdActive;
	}

	public String getTmtdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTmtdCreatedby(String tmtdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tmtdCreatedby;
	}

	public String getTmtdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTmtdCreatedon(String tmtdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tmtdCreatedon;
	}

	public String getTmtdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTmtdModifiedon(String tmtdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tmtdModifiedon;
	}

}

