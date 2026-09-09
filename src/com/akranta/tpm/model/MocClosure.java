package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class MocClosure {

	private  Object [] saveArray = null; 
	private MocRfcmst mocRfcmst;
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public enum   tableFldConstants
	{
		keyid,masterrfcid,closureid,sortorder,responseY,responseN,tempfield1,tempfield2,tempfield3,
		tempfield4,tempfield5,createdby,active,createdon,modifiedon
	}

	public MocClosure()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public String getRfccKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setRfccKeyid(String rfccKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = rfccKeyid;
	}
	

	public String getRfccrfcid() {
		return (String) saveArray[ tableFldConstants.masterrfcid.ordinal() ];
	}

	public void setRfccrfcid(String rfccrfcid) {
		saveArray[ tableFldConstants.masterrfcid.ordinal() ] =rfccrfcid;
	}

	public String getRfccclosureid() {
		return (String) saveArray[ tableFldConstants.closureid.ordinal() ];
	}

	public void setRfccclosureid(String rfccclosureid) {
		saveArray[ tableFldConstants.closureid.ordinal() ] =rfccclosureid;
	} 
	


	public String getRfccsortorder() {
		return (String) saveArray[ tableFldConstants.sortorder.ordinal() ];
	}

	public void setRfccsortorder(String rfccsortorder) {
		saveArray[ tableFldConstants.sortorder.ordinal() ] =rfccsortorder;
	} 



	public String getRfccresponse() {
		return (String) saveArray[ tableFldConstants.responseY.ordinal() ];
	}

	public void setRfccresponse(String rfccresponse) {
		saveArray[ tableFldConstants.responseY.ordinal() ] =rfccresponse;
	}
	
	public String getRfccresponseNo() {
		return (String) saveArray[ tableFldConstants.responseN.ordinal() ];
	}

	public void setRfccresponseNo(String rfccresponseNo) {
		saveArray[ tableFldConstants.responseN.ordinal() ] =rfccresponseNo;
	}
	public String getRfccTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}
	
	public void setRfccTempfield1(String rfccTempfield1){
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = rfccTempfield1;
	} 
  
	public String getRfccTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}
	
	public void setRfccTempfield2(String rfccTempfield2){
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = rfccTempfield2;
	} 
  
	public String getRfccTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}
	
	public void setRfccTempfield3(String rfccTempfield3){
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = rfccTempfield3;
	}
 
	public String getRfccTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}
	
	public void setRfccTempfield4(String rfccTempfield4){
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = rfccTempfield4;
	} 
  
	public String getRfccTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}
	
	public void setRfccTempfield5(String rfccTempfield5){
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = rfccTempfield5;
	} 
  
   public String getRfccCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}
	public void setRfccCreatedby(String rfccCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] =rfccCreatedby;
	}
	
	public String getRfccActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setRfccActive(String rfccActive) {
		saveArray[ tableFldConstants.active.ordinal() ] =rfccActive;
	}

	public String getRfccCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setRfccCreatedon(String rfccCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] =rfccCreatedon;
	}
	
	public String getRfccModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setRfccModifiedon(String rfccModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] =rfccModifiedon;
	}
	

	

}

