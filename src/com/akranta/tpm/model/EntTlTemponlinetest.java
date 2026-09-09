package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTemponlinetest {

	private  Object [] saveArray = null;  
	private String noofInsert; 

	public enum   tableFldConstants  
	{
		keyid, employeeid, olpm_keyid, programid, topicid, questionid
		, answerid, teststarttime, markforreview, iscorrectans, tempfield2
		, tempfield3, tempfield4, active, createdby, createdon, modifiedon
	}

	public EntTlTemponlinetest()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTemtKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTemtKeyid(String temtKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = temtKeyid;
	}

	public String getTemtEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setTemtEmployeeid(String temtEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = temtEmployeeid;
	}

	public String getTemtOlpmKeyid() {
		return (String) saveArray[ tableFldConstants.olpm_keyid.ordinal() ];
	}

	public void setTemtOlpmKeyid(String temtOlpmKeyid) {
		saveArray[ tableFldConstants.olpm_keyid.ordinal() ] = temtOlpmKeyid;
	}

	public String getTemtProgramid() {
		return (String) saveArray[ tableFldConstants.programid.ordinal() ];
	}

	public void setTemtProgramid(String temtProgramid) {
		saveArray[ tableFldConstants.programid.ordinal() ] = temtProgramid;
	}

	public String getTemtTopicid() {
		return (String) saveArray[ tableFldConstants.topicid.ordinal() ];
	}

	public void setTemtTopicid(String temtTopicid) {
		saveArray[ tableFldConstants.topicid.ordinal() ] = temtTopicid;
	}

	public String getTemtQuestionid() {
		return (String) saveArray[ tableFldConstants.questionid.ordinal() ];
	}

	public void setTemtQuestionid(String temtQuestionid) {
		saveArray[ tableFldConstants.questionid.ordinal() ] = temtQuestionid;
	}

	public String getTemtAnswerid() {
		return (String) saveArray[ tableFldConstants.answerid.ordinal() ];
	}

	public void setTemtAnswerid(String temtAnswerid) {
		saveArray[ tableFldConstants.answerid.ordinal() ] = temtAnswerid;
	}

	public String getTemtTeststarttime() {
		return (String) saveArray[ tableFldConstants.teststarttime.ordinal() ];
	}

	public void setTemtTeststarttime(String temtTeststarttime) {
		saveArray[ tableFldConstants.teststarttime.ordinal() ] = temtTeststarttime;
	}

	public String getTemtMarkforreview() {
		return (String) saveArray[ tableFldConstants.markforreview.ordinal() ];
	}

	public void setTemtMarkforreview(String temtMarkforreview) {
		saveArray[ tableFldConstants.markforreview.ordinal() ] = temtMarkforreview;
	}

	public String getTemtIscorrectans() {  
		return (String) saveArray[ tableFldConstants.iscorrectans.ordinal() ];
	}

	public void setTemtIscorrectans(String temtIscorrectans) {
		saveArray[ tableFldConstants.iscorrectans.ordinal() ] = temtIscorrectans;
	}

	public String getTemtTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTemtTempfield2(String temtTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = temtTempfield2;
	}

	public String getTemtTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTemtTempfield3(String temtTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = temtTempfield3;
	}

	public String getTemtTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setTemtTempfield4(String temtTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = temtTempfield4;
	}

	public String getTemtActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTemtActive(String temtActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = temtActive;
	}

	public String getTemtCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTemtCreatedby(String temtCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = temtCreatedby;
	}

	public String getTemtCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTemtCreatedon(String temtCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = temtCreatedon;
	}

	public String getTemtModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTemtModifiedon(String temtModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = temtModifiedon;
	}

	public void setNoofInsert(String noofInsert) {
		this.noofInsert = noofInsert;
	}

	public String getNoofInsert() {
		return noofInsert;
	}

}

