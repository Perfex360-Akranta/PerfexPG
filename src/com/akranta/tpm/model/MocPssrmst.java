package com.akranta.tpm.model;

import java.util.List;

public class MocPssrmst {

	private  Object [] saveArray = null; 
//	private MocRfcmst mocRfcmst;
	private List<MocPssrdtl> mocPssrdtl;
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public enum   tableFldConstants
	{
		keyid,rfcid,process,date,mocdetails,tempfield1,tempfield2,tempfield3,
		tempfield4,tempfield5,createdby,active,createdon,modifiedon
	}

	public MocPssrmst()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public String getPsrmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPsrmKeyid(String psrmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = psrmKeyid;
	}
	

	public String getPsrmrfcid() {
		return (String) saveArray[ tableFldConstants.rfcid.ordinal() ];
	}

	public void setPsrmrfcid(String psrmrfcid) {
		saveArray[ tableFldConstants.rfcid.ordinal() ] =psrmrfcid;
	}

	public String getPsrmprocess() {
		return (String) saveArray[ tableFldConstants.process.ordinal() ];
	}

	public void setPsrmprocess(String psrmprocess) {
		saveArray[ tableFldConstants.process.ordinal() ] =psrmprocess;
	} 
	
	public String getPsrmdate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setPsrmdate(String psrmdate) {
		saveArray[ tableFldConstants.date.ordinal() ] =psrmdate;
	} 

	public String getPsrmmocdetail() {
		return (String) saveArray[ tableFldConstants.mocdetails.ordinal() ];
	}

	public void setPsrmmocdetail(String psrmmocdetail) {
		saveArray[ tableFldConstants.mocdetails.ordinal() ] =psrmmocdetail;
	} 


	public String getPsrmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}
	
	public void setPsrmTempfield1(String PsrmTempfield1){
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = PsrmTempfield1;
	} 
  
	public String getPsrmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}
	
	public void setPsrmTempfield2(String PsrmTempfield2){
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = PsrmTempfield2;
	} 
  
	public String getPsrmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}
	
	public void setPsrmTempfield3(String PsrmTempfield3){
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = PsrmTempfield3;
	}
 
	public String getPsrmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}
	
	public void setPsrmTempfield4(String PsrmTempfield4){
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = PsrmTempfield4;
	} 
  
	public String getPsrmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}
	
	public void setPsrmTempfield5(String PsrmTempfield5){
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = PsrmTempfield5;
	} 
  
   public String getPsrmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}
	public void setPsrmCreatedby(String PsrmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] =PsrmCreatedby;
	}
	
	public String getPsrmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPsrmActive(String PsrmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] =PsrmActive;
	}

	public String getPsrmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPsrmCreatedon(String PsrmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] =PsrmCreatedon;
	}
	
	public String getPsrmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPsrmModifiedon(String PsrmModifiedby) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] =PsrmModifiedby;
	}
	
	public  List<MocPssrdtl> getPssrDetails(){
		  return mocPssrdtl;
	  }
	  public void setPssrDetails(List<MocPssrdtl> mocPssrdtl){
		  this.mocPssrdtl=mocPssrdtl;
	  }
/*	public void setMocRfcmst(MocRfcmst mocRfcmst) {
		this.mocRfcmst = mocRfcmst;
	}

	public MocRfcmst getMocRfcmst() {
		return mocRfcmst;
	} */

}

