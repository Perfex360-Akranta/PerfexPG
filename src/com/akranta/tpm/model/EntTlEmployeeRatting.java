package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlEmployeeRatting {

	private  Object [] saveArray = null;  
	private List<EntTlEmployeeRatting> entTlEmployeeRatting;
    private String level;
    private String topicId;
	public enum   tableFldConstants
	{
		keyid, empkeyid, ratting, date, remarks, rattingby, active, createdby
		, tempfield1, tempfield2, tempfield3, tempfield4, createdon, modifiedon
	}

	public EntTlEmployeeRatting()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEmraKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEmraKeyid(String emraKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = emraKeyid;
	}

	public String getEmraEmpkeyid() {
		return (String) saveArray[ tableFldConstants.empkeyid.ordinal() ];
	}

	public void setEmraEmpkeyid(String emraEmpkeyid) {
		saveArray[ tableFldConstants.empkeyid.ordinal() ] = emraEmpkeyid;
	}

	public String getEmraRatting() {
		return (String) saveArray[ tableFldConstants.ratting.ordinal() ];
	}

	public void setEmraRatting(String emraRatting) {
		saveArray[ tableFldConstants.ratting.ordinal() ] = emraRatting;
	}

	public String getEmraDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setEmraDate(String emraDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = emraDate;
	}

	public String getEmraRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setEmraRemarks(String emraRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = emraRemarks;
	}

	public String getEmraRattingby() {
		return (String) saveArray[ tableFldConstants.rattingby.ordinal() ];
	}

	public void setEmraRattingby(String emraRattingby) {
		saveArray[ tableFldConstants.rattingby.ordinal() ] = emraRattingby;
	}

	public String getEmraActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEmraActive(String emraActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = emraActive;
	}

	public String getEmraCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEmraCreatedby(String emraCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = emraCreatedby;
	}

	public String getEmraTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEmraTempfield1(String emraTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = emraTempfield1;
	}

	public String getEmraTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEmraTempfield2(String emraTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = emraTempfield2;
	}

	public String getEmraTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEmraTempfield3(String emraTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = emraTempfield3;
	}

	public String getEmraTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEmraTempfield4(String emraTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = emraTempfield4;
	}

	public String getEmraCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEmraCreatedon(String emraCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = emraCreatedon;
	}

	public String getEmraModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEmraModifiedon(String emraModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = emraModifiedon;
	}

	public List<EntTlEmployeeRatting> getEntTlEmployeeRatting() {
		return entTlEmployeeRatting;
	}

	public void setEntTlEmployeeRatting(List<EntTlEmployeeRatting> entTlEmployeeRatting) {
		this.entTlEmployeeRatting = entTlEmployeeRatting;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

}

