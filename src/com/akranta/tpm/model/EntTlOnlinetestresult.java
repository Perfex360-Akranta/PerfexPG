package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlOnlinetestresult {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, empm_keyid, testdate, testid, programid, topicid, noofquestion
		, questionanswered, correctanswer, totalmark, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, tempfield7
		, tempfield8, active, createdby, createdon, modifiedon
	}

	public EntTlOnlinetestresult()
	{
		saveArray = new  Object [ 22 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getOtrsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setOtrsKeyid(String otrsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = otrsKeyid;
	}

	public String getOtrsEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setOtrsEmpmKeyid(String otrsEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = otrsEmpmKeyid;
	}

	public String getOtrsTestdate() {
		return (String) saveArray[ tableFldConstants.testdate.ordinal() ];
	}

	public void setOtrsTestdate(String otrsTestdate) {
		saveArray[ tableFldConstants.testdate.ordinal() ] = otrsTestdate;
	}

	public String getOtrsTestid() {
		return (String) saveArray[ tableFldConstants.testid.ordinal() ];
	}

	public void setOtrsTestid(String otrsTestid) {
		saveArray[ tableFldConstants.testid.ordinal() ] = otrsTestid;
	}

	public String getOtrsProgramid() {
		return (String) saveArray[ tableFldConstants.programid.ordinal() ];
	}

	public void setOtrsProgramid(String otrsProgramid) {
		saveArray[ tableFldConstants.programid.ordinal() ] = otrsProgramid;
	}

	public String getOtrsTopicid() {
		return (String) saveArray[ tableFldConstants.topicid.ordinal() ];
	}

	public void setOtrsTopicid(String otrsTopicid) {
		saveArray[ tableFldConstants.topicid.ordinal() ] = otrsTopicid;
	}

	public String getOtrsNoofquestion() {
		return (String) saveArray[ tableFldConstants.noofquestion.ordinal() ];
	}

	public void setOtrsNoofquestion(String otrsNoofquestion) {
		saveArray[ tableFldConstants.noofquestion.ordinal() ] = otrsNoofquestion;
	}

	public String getOtrsQuestionanswered() {
		return (String) saveArray[ tableFldConstants.questionanswered.ordinal() ];
	}

	public void setOtrsQuestionanswered(String otrsQuestionanswered) {
		saveArray[ tableFldConstants.questionanswered.ordinal() ] = otrsQuestionanswered;
	}

	public String getOtrsCorrectanswer() {
		return (String) saveArray[ tableFldConstants.correctanswer.ordinal() ];
	}

	public void setOtrsCorrectanswer(String otrsCorrectanswer) {
		saveArray[ tableFldConstants.correctanswer.ordinal() ] = otrsCorrectanswer;
	}

	public String getOtrsTotalmark() {
		return (String) saveArray[ tableFldConstants.totalmark.ordinal() ];
	}

	public void setOtrsTotalmark(String otrsTotalmark) {
		saveArray[ tableFldConstants.totalmark.ordinal() ] = otrsTotalmark;
	}

	public String getOtrsTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setOtrsTempfield1(String otrsTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = otrsTempfield1;
	}

	public String getOtrsTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setOtrsTempfield2(String otrsTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = otrsTempfield2;
	}

	public String getOtrsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setOtrsTempfield3(String otrsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = otrsTempfield3;
	}

	public String getOtrsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setOtrsTempfield4(String otrsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = otrsTempfield4;
	}

	public String getOtrsTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setOtrsTempfield5(String otrsTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = otrsTempfield5;
	}

	public String getOtrsTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setOtrsTempfield6(String otrsTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = otrsTempfield6;
	}

	public String getOtrsTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setOtrsTempfield7(String otrsTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = otrsTempfield7;
	}

	public String getOtrsTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setOtrsTempfield8(String otrsTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = otrsTempfield8;
	}

	public String getOtrsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setOtrsActive(String otrsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = otrsActive;
	}

	public String getOtrsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setOtrsCreatedby(String otrsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = otrsCreatedby;
	}

	public String getOtrsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setOtrsCreatedon(String otrsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = otrsCreatedon;
	}

	public String getOtrsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setOtrsModifiedon(String otrsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = otrsModifiedon;
	}

}

