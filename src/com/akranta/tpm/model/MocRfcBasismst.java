package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class MocRfcBasismst {

	private  Object [] saveArray = null; 
	private MocRfcmst mocRfcmst;
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public enum   tableFldConstants
	{
		keyid,rfcid,basisid,tempfield1,tempfield2,tempfield3,
		tempfield4,tempfield5,createdby,active,createdon,modifiedon
	}

	public MocRfcBasismst()
	{
		saveArray = new  Object [ 32 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public String getRfcbKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setRfcbKeyid(String rfcbKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = rfcbKeyid;
	}
	

	public String getRfcbrfcid() {
		return (String) saveArray[ tableFldConstants.rfcid.ordinal() ];
	}

	public void setRfcbrfcid(String rfcbrfcid) {
		saveArray[ tableFldConstants.rfcid.ordinal() ] =rfcbrfcid;
	}

	public String getRfcbbasisid() {
		return (String) saveArray[ tableFldConstants.basisid.ordinal() ];
	}

	public void setRfcbbasisid(String rfcbbasisid) {
		saveArray[ tableFldConstants.basisid.ordinal() ] =rfcbbasisid;
	} 

	public String getRfcbTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}
	
	public void setRfcbTempfield1(String rfcbTempfield1){
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = rfcbTempfield1;
	} 
  
	public String getRfcbTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}
	
	public void setRfcbTempfield2(String rfcbTempfield2){
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = rfcbTempfield2;
	} 
  
	public String getRfcbTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}
	
	public void setRfcbTempfield3(String rfcbTempfield3){
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = rfcbTempfield3;
	}
 
	public String getRfcbTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}
	
	public void setRfcbTempfield4(String rfcbTempfield4){
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = rfcbTempfield4;
	} 
  
	public String getRfcbTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}
	
	public void setRfcbTempfield5(String rfcbTempfield5){
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = rfcbTempfield5;
	} 
  
   public String getRfcbCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}
	public void setRfcbCreatedby(String rfcbCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] =rfcbCreatedby;
	}
	
	public String getRfcbActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setRfcbActive(String rfcbActive) {
		saveArray[ tableFldConstants.active.ordinal() ] =rfcbActive;
	}

	public String getRfcbCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setRfcbCreatedon(String rfcbCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] =rfcbCreatedon;
	}
	
	public String getRfcbModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setRfcbModifiedon(String rfcbModifiedby) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] =rfcbModifiedby;
	}
	

	public void setMocRfcmst(MocRfcmst mocRfcmst) {
		this.mocRfcmst = mocRfcmst;
	}

	public MocRfcmst getMocRfcmst() {
		return mocRfcmst;
	} 

}

