package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class JhaTlFiveSAuditareamst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, areaname, areacode, responsibiltyid, parentid, areatype
		, frequency, circleid, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public JhaTlFiveSAuditareamst()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}

	public String getFvasKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFvasKeyid(String fvasKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fvasKeyid;
	}

	public String getFvasAreaname() {
		return (String) saveArray[ tableFldConstants.areaname.ordinal() ];
	}

	public void setFvasAreaname(String fvasAreaname) {
		saveArray[ tableFldConstants.areaname.ordinal() ] = fvasAreaname;
	}

	public String getFvasAreacode() {
		return (String) saveArray[ tableFldConstants.areacode.ordinal() ];
	}

	public void setFvasAreacode(String fvasAreacode) {
		saveArray[ tableFldConstants.areacode.ordinal() ] = fvasAreacode;
	}

	public String getFvasResponsibiltyid() {
		return (String) saveArray[ tableFldConstants.responsibiltyid.ordinal() ];
	}

	public void setFvasResponsibiltyid(String fvasResponsibiltyid) {
		saveArray[ tableFldConstants.responsibiltyid.ordinal() ] = fvasResponsibiltyid;
	}

	public String getFvasParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setFvasParentid(String fvasParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = fvasParentid;
	}

	public String getFvasAreatype() {
		return (String) saveArray[ tableFldConstants.areatype.ordinal() ];
	}

	public void setFvasAreatype(String fvasAreatype) {
		saveArray[ tableFldConstants.areatype.ordinal() ] = fvasAreatype;
	}

	public String getFvasFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setFvasFrequency(String fvasFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = fvasFrequency;
	}

	public String getFvasCircleid() {
		return (String) saveArray[ tableFldConstants.circleid.ordinal() ];
	}

	public void setFvasCircleid(String fvasCircleid) {
		saveArray[ tableFldConstants.circleid.ordinal() ] = fvasCircleid;
	}

	public String getFvasTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFvasTempfield1(String fvasTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = fvasTempfield1;
	}

	public String getFvasTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFvasTempfield2(String fvasTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fvasTempfield2;
	}

	public String getFvasTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFvasTempfield3(String fvasTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fvasTempfield3;
	}

	public String getFvasTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFvasTempfield4(String fvasTempfield4) {
	saveArray[ tableFldConstants.tempfield4.ordinal() ] = fvasTempfield4;
	}

	public String getFvasTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFvasTempfield5(String fvasTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = fvasTempfield5;
	}

	public String getFvasActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFvasActive(String fvasActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fvasActive;
	}

	public String getFvasCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFvasCreatedby(String fvasCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fvasCreatedby;
	}

	public String getFvasCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFvasCreatedon(String fvasCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fvasCreatedon;
	}

	public String getFvasModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFvasModifiedon(String fvasModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fvasModifiedon;
	}

	

}

