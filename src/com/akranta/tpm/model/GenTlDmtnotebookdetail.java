package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlDmtnotebookdetail {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, dmtm_keyid, type, detlsofisuediscd, actionplanid, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public GenTlDmtnotebookdetail()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getDmtdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDmtdKeyid(String dmtdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dmtdKeyid;
	}

	public String getDmtdDmtmKeyid() {
		return (String) saveArray[ tableFldConstants.dmtm_keyid.ordinal() ];
	}

	public void setDmtdDmtmKeyid(String dmtdDmtmKeyid) {
		saveArray[ tableFldConstants.dmtm_keyid.ordinal() ] = dmtdDmtmKeyid;
	}

	public String getDmtdType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setDmtdType(String dmtdType) {
		saveArray[ tableFldConstants.type.ordinal() ] = dmtdType;
	}

	public String getDmtdDetlsofisuediscd() {
		return (String) saveArray[ tableFldConstants.detlsofisuediscd.ordinal() ];
	}

	public void setDmtdDetlsofisuediscd(String dmtdDetlsofisuediscd) {
		saveArray[ tableFldConstants.detlsofisuediscd.ordinal() ] = dmtdDetlsofisuediscd;
	}

	public String getDmtdActionplanid() {
		return (String) saveArray[ tableFldConstants.actionplanid.ordinal() ];
	}

	public void setDmtdActionplanid(String dmtdActionplanid) {
		saveArray[ tableFldConstants.actionplanid.ordinal() ] = dmtdActionplanid;
	}

	public String getDmtdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setDmtdTempfield1(String dmtdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = dmtdTempfield1;
	}

	public String getDmtdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setDmtdTempfield2(String dmtdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = dmtdTempfield2;
	}

	public String getDmtdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setDmtdTempfield3(String dmtdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = dmtdTempfield3;
	}

	public String getDmtdTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setDmtdTempfield4(String dmtdTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = dmtdTempfield4;
	}

	public String getDmtdTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setDmtdTempfield5(String dmtdTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = dmtdTempfield5;
	}

	public String getDmtdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDmtdActive(String dmtdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dmtdActive;
	}

	public String getDmtdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDmtdCreatedby(String dmtdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dmtdCreatedby;
	}

	public String getDmtdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDmtdCreatedon(String dmtdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dmtdCreatedon;
	}

	public String getDmtdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDmtdModifiedon(String dmtdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dmtdModifiedon;
	}

}

