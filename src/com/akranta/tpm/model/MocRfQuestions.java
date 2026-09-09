package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class MocRfQuestions {

	private  Object [] saveArray = null; 
	private MocRfcmst mocRfcmst;
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public enum   tableFldConstants
	{
		keyid,masterrfcid,questionaireid,sortorder,response,tempfield1,tempfield2,tempfield3,
		tempfield4,tempfield5,createdby,active,createdon,modifiedon
	}

	public MocRfQuestions()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public String getRfcqKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setRfcqKeyid(String rfcqKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = rfcqKeyid;
	}
	

	public String getRfcqrfcid() {
		return (String) saveArray[ tableFldConstants.masterrfcid.ordinal() ];
	}

	public void setRfcqrfcid(String rfcqrfcid) {
		saveArray[ tableFldConstants.masterrfcid.ordinal() ] =rfcqrfcid;
	}

	public String getRfcqquestionaireid() {
		return (String) saveArray[ tableFldConstants.questionaireid.ordinal() ];
	}

	public void setRfcqquestionaireid(String rfcqquestionaireid) {
		saveArray[ tableFldConstants.questionaireid.ordinal() ] =rfcqquestionaireid;
	} 
	


	public String getRfcqsortorder() {
		return (String) saveArray[ tableFldConstants.sortorder.ordinal() ];
	}

	public void setRfcqsortorder(String rfcqsortorder) {
		saveArray[ tableFldConstants.sortorder.ordinal() ] =rfcqsortorder;
	} 

	public String getRfcqresponse() {
		return (String) saveArray[ tableFldConstants.response.ordinal() ];
	}

	public void setRfcqresponse(String rfcqresponse) {
		saveArray[ tableFldConstants.response.ordinal() ] =rfcqresponse;
	}
	

	public String getRfcqTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}
	
	public void setRfcqTempfield1(String rfcqTempfield1){
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = rfcqTempfield1;
	} 
  
	public String getRfcqTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}
	
	public void setRfcqTempfield2(String rfcqTempfield2){
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = rfcqTempfield2;
	} 
  
	public String getRfcqTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}
	
	public void setRfcqTempfield3(String rfcqTempfield3){
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = rfcqTempfield3;
	}
 
	public String getRfcqTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}
	
	public void setRfcqTempfield4(String rfcqTempfield4){
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = rfcqTempfield4;
	} 
  
	public String getRfcqTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}
	
	public void setRfcqTempfield5(String rfcqTempfield5){
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = rfcqTempfield5;
	} 
  
   public String getRfcqCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}
	public void setRfcqCreatedby(String rfcqCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] =rfcqCreatedby;
	}
	
	public String getRfcqActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setRfcqActive(String rfcqActive) {
		saveArray[ tableFldConstants.active.ordinal() ] =rfcqActive;
	}

	public String getRfcqCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setRfcqCreatedon(String rfcqCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] =rfcqCreatedon;
	}
	
	public String getRfcqModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setRfcqModifiedon(String rfcqModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] =rfcqModifiedon;
	}
	

	

}

