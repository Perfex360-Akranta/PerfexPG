package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTrainingneedmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, type, typeid, topicid, date, remarks, elementid
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public EntTlTrainingneedmst()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTnimKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTnimKeyid(String tnimKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tnimKeyid;
	}

	public String getTnimFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setTnimFlid(String tnimFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = tnimFlid;
	}

	public String getTnimType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setTnimType(String tnimType) {
		saveArray[ tableFldConstants.type.ordinal() ] = tnimType;
	}

	public String getTnimTypeid() {
		return (String) saveArray[ tableFldConstants.typeid.ordinal() ];
	}

	public void setTnimTypeid(String tnimUniqueid) {
		saveArray[ tableFldConstants.typeid.ordinal() ] = tnimUniqueid;
	}

	public String getTnimTopicid() {
		return (String) saveArray[ tableFldConstants.topicid.ordinal() ];
	}

	public void setTnimTopicid(String tnimTopicid) {
		saveArray[ tableFldConstants.topicid.ordinal() ] = tnimTopicid;
	}

	public String getTnimDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setTnimDate(String tnimDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = tnimDate;
	}

	public String getTnimRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setTnimRemarks(String tnimRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = tnimRemarks;
	}

	public String getTnimElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setTnimElementid(String tnimElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = tnimElementid;
	}

	public String getTnimTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTnimTempfield2(String tnimTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tnimTempfield2;
	}

	public String getTnimTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTnimTempfield3(String tnimTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = tnimTempfield3;
	}

	public String getTnimTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setTnimTempfield4(String tnimTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = tnimTempfield4;
	}

	public String getTnimTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setTnimTempfield5(String tnimTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = tnimTempfield5;
	}

	public String getTnimActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTnimActive(String tnimActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = tnimActive;
	}

	public String getTnimCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTnimCreatedby(String tnimCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tnimCreatedby;
	}

	public String getTnimCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTnimCreatedon(String tnimCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tnimCreatedon;
	}

	public String getTnimModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTnimModifiedon(String tnimModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tnimModifiedon;
	}

}

