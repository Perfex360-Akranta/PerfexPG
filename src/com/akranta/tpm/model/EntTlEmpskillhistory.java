package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlEmpskillhistory {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, topicid, assesmentid, assessdby, currentratting, employeeid
		, skilupdateddate, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, tempfield6, active, createdby, createdon, modifiedon
	}

	public EntTlEmpskillhistory()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEeshKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEeshKeyid(String eeshKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = eeshKeyid;
	}

	public String getEeshTopicid() {
		return (String) saveArray[ tableFldConstants.topicid.ordinal() ];
	}

	public void setEeshTopicid(String eeshTopicid) {
		saveArray[ tableFldConstants.topicid.ordinal() ] = eeshTopicid;
	}

	public String getEeshAssesmentid() {
		return (String) saveArray[ tableFldConstants.assesmentid.ordinal() ];
	}

	public void setEeshAssesmentid(String eeshAssesmentid) {
		saveArray[ tableFldConstants.assesmentid.ordinal() ] = eeshAssesmentid;
	}

	public String getEeshAssessdby() {
		return (String) saveArray[ tableFldConstants.assessdby.ordinal() ];
	}

	public void setEeshAssessdby(String eeshAssessdby) {
		saveArray[ tableFldConstants.assessdby.ordinal() ] = eeshAssessdby;
	}

	public String getEeshCurrentratting() {
		return (String) saveArray[ tableFldConstants.currentratting.ordinal() ];
	}

	public void setEeshCurrentratting(String eeshCurrentratting) {
		saveArray[ tableFldConstants.currentratting.ordinal() ] = eeshCurrentratting;
	}

	public String getEeshEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setEeshEmployeeid(String eeshEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = eeshEmployeeid;
	}

	public String getEeshSkilupdateddate() {
		return (String) saveArray[ tableFldConstants.skilupdateddate.ordinal() ];
	}

	public void setEeshSkilupdateddate(String eeshSkilupdateddate) {
		saveArray[ tableFldConstants.skilupdateddate.ordinal() ] = eeshSkilupdateddate;
	}

	public String getEeshTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEeshTempfield1(String eeshTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = eeshTempfield1;
	}

	public String getEeshTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEeshTempfield2(String eeshTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = eeshTempfield2;
	}

	public String getEeshTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEeshTempfield3(String eeshTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = eeshTempfield3;
	}

	public String getEeshTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEeshTempfield4(String eeshTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = eeshTempfield4;
	}

	public String getEeshTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setEeshTempfield5(String eeshTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = eeshTempfield5;
	}

	public String getEeshTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setEeshTempfield6(String eeshTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = eeshTempfield6;
	}

	public String getEeshActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEeshActive(String eeshActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = eeshActive;
	}

	public String getEeshCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEeshCreatedby(String eeshCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = eeshCreatedby;
	}

	public String getEeshCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEeshCreatedon(String eeshCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = eeshCreatedon;
	}

	public String getEeshModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEeshModifiedon(String eeshModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = eeshModifiedon;
	}

}

