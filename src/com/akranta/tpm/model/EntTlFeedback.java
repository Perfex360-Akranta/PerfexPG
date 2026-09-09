package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlFeedback {

	private  Object [] saveArray = null;  
	
	private List<EntTlFeedback> feedback;
	private List<EntTlFeedback> feedbackdatas;
	

	public enum   tableFldConstants
	{
		keyid, frqd_keyid, fbpm_keyid, ferm_keyid, answer, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public EntTlFeedback()
	{
		saveArray = new  Object [ 14 ];
		setFeedback(new ArrayList<EntTlFeedback>());
		setFeedbackdatas(new ArrayList<EntTlFeedback>());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	

	public List<EntTlFeedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<EntTlFeedback> feedback) {
		this.feedback = feedback;
	}

	public List<EntTlFeedback> getFeedbackdatas() {
		return feedbackdatas;
	}

	public void setFeedbackdatas(List<EntTlFeedback> feedbackdatas) {
		this.feedbackdatas = feedbackdatas;
	}

	public String getFeedKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFeedKeyid(String feedKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = feedKeyid;
	}

	public String getFeedFrqdKeyid() {
		return (String) saveArray[ tableFldConstants.frqd_keyid.ordinal() ];
	}

	public void setFeedFrqdKeyid(String feedFrqdKeyid) {
		saveArray[ tableFldConstants.frqd_keyid.ordinal() ] = feedFrqdKeyid;
	}

	public String getFeedFbpmKeyid() {
		return (String) saveArray[ tableFldConstants.fbpm_keyid.ordinal() ];
	}

	public void setFeedFbpmKeyid(String feedFbpmKeyid) {
		saveArray[ tableFldConstants.fbpm_keyid.ordinal() ] = feedFbpmKeyid;
	}

	public String getFeedFermKeyid() {
		return (String) saveArray[ tableFldConstants.ferm_keyid.ordinal() ];
	}

	public void setFeedFermKeyid(String feedFermKeyid) {
		saveArray[ tableFldConstants.ferm_keyid.ordinal() ] = feedFermKeyid;
	}

	public String getFeedAnswer() {
		return (String) saveArray[ tableFldConstants.answer.ordinal() ];
	}

	public void setFeedAnswer(String feedAnswer) {
		saveArray[ tableFldConstants.answer.ordinal() ] = feedAnswer;
	}

	public String getFeedTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFeedTempfield1(String feedTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = feedTempfield1;
	}

	public String getFeedTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFeedTempfield2(String feedTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = feedTempfield2;
	}

	public String getFeedTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFeedTempfield3(String feedTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = feedTempfield3;
	}

	public String getFeedTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setFeedTempfield4(String feedTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = feedTempfield4;
	}

	public String getFeedTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setFeedTempfield5(String feedTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = feedTempfield5;
	}

	public String getFeedActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFeedActive(String feedActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = feedActive;
	}

	public String getFeedCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFeedCreatedby(String feedCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = feedCreatedby;
	}

	public String getFeedCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFeedCreatedon(String feedCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = feedCreatedon;
	}

	public String getFeedModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFeedModifiedon(String feedModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = feedModifiedon;
	}

}

