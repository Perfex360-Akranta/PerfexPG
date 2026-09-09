package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class MocRfcmstNew {

	private  Object [] saveArray = null; 
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public enum   tableFldConstants
	{
		keyid,kaizenid,suggestionid,flid,dmtid,jhid,date,empid,title,type,
		nature,detail,description,emergencyno,detailimg,descimg,status,
		capex,noofdays,tempfield3,tempfield4,tempfield5,tempfield6,tempfield7,tempfield8,tempfield9,tempfield10,
	active,createdby,createdon,modifiedon,modifiedby
	}

	public MocRfcmstNew()
	{
		saveArray = new  Object [ 32 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public String getRfcmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setRfcmKeyid(String rfcmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = rfcmKeyid;
	}
	

	public String getRfcmkaizenid() {
		return (String) saveArray[ tableFldConstants.kaizenid.ordinal() ];
	}

	public void setRfcmkaizenid(String rfcmkaizenid) {
		saveArray[ tableFldConstants.kaizenid.ordinal() ] =rfcmkaizenid;
	}

	public String getRfcmsuggestionid() {
		return (String) saveArray[ tableFldConstants.suggestionid.ordinal() ];
	}

	public void setRfcmsuggestionid(String rfcmsuggestionid) {
		saveArray[ tableFldConstants.suggestionid.ordinal() ] =rfcmsuggestionid;
	}

	public String getRfcmflid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setRfcmflid(String rfcmflid) {
		saveArray[ tableFldConstants.flid.ordinal() ] =rfcmflid;
	} 

	public String getRfcmdmtid() {
		return (String) saveArray[ tableFldConstants.dmtid.ordinal() ];
	}

	public void setRfcmdmtid(String rfcmdmtid) {
		saveArray[ tableFldConstants.dmtid.ordinal() ] =rfcmdmtid;
	} 
	public String getRfcmjhid() {
		return (String) saveArray[ tableFldConstants.jhid.ordinal() ];
	}

	public void setRfcmjhid(String rfcmjhid) {
		saveArray[ tableFldConstants.jhid.ordinal() ] =rfcmjhid;
	} 
	
	public String getRfcmdate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setRfcmdate(String rfcmdate) {
		saveArray[ tableFldConstants.date.ordinal() ] =rfcmdate;
	} 	
	public String getRfcmempid() {
		return (String) saveArray[ tableFldConstants.empid.ordinal() ];
	}

	public void setRfcmempid(String rfcmempid) {
		saveArray[ tableFldConstants.empid.ordinal() ] =rfcmempid;
	}
	
	public String getRfcmtitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setRfcmtitle(String rfcmtitle) {
		saveArray[ tableFldConstants.title.ordinal() ] =rfcmtitle;
	} 
	
	public String getRfcmtype() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setRfcmtype(String rfcmtype) {
		saveArray[ tableFldConstants.type.ordinal() ] =rfcmtype;
	} 
	
	public String getRfcmnature() {
		return (String) saveArray[ tableFldConstants.nature.ordinal() ];
	}

	public void setRfcmnature(String rfcmnature) {
		saveArray[ tableFldConstants.nature.ordinal() ] =rfcmnature;
	} 
	
	
	public String getRfcmdetail() {
		return (String) saveArray[ tableFldConstants.detail.ordinal() ];
	}

	public void setRfcmdetail(String rfcmdetail) {
		saveArray[ tableFldConstants.detail.ordinal() ] =rfcmdetail;
	} 
	
	public String getRfcmdescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setRfcmdescription(String rfcmdescription) {
		saveArray[ tableFldConstants.description.ordinal() ] =rfcmdescription;
	} 
	
	public String getRfcmemergencyno() {
		return (String) saveArray[ tableFldConstants.emergencyno.ordinal() ];
	}

	public void setRfcmemergencyno(String rfcmemergencyno) {
		saveArray[ tableFldConstants.emergencyno.ordinal() ] =rfcmemergencyno;
	} 
	
	public String getRfcmdetailimg() {
		return (String) saveArray[ tableFldConstants.detailimg.ordinal() ];
	}

	public void setRfcmdetailimg(String rfcmdetailimg) {
		saveArray[ tableFldConstants.detailimg.ordinal() ] =rfcmdetailimg;
	} 
	
	public String getRfcmdescimg() {
		return (String) saveArray[ tableFldConstants.descimg.ordinal() ];
	}

	public void setRfcmdescimg(String rfcmdescimg) {
		saveArray[ tableFldConstants.descimg.ordinal() ] =rfcmdescimg;
	} 

	
	
	public String getRfcmstatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setRfcmstatus(String rfcmstatus) {
		saveArray[ tableFldConstants.status.ordinal() ] =rfcmstatus;
	} 
	
	//Vignesh adding 11may 2026
		// Alias method for CapEx, but store in same tempfield1 position
		
		/*
	public String getRfcmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}
	
	public void setRfcmTempfield1(String rfcmTempfield1){
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = rfcmTempfield1;
	} 
  
	*/
	public String getRfcmCapex() {
	    return (String) saveArray[tableFldConstants.capex.ordinal()];
	}

	public void setRfcmCapex(String rfcmcapex) {
	    saveArray[tableFldConstants.capex.ordinal()] = rfcmcapex;
	}
	
	
	public String getRfcmNoofdays() {
	    return (String) saveArray[tableFldConstants.noofdays.ordinal()];
	}

	public void setRfcmNoofdays(String rfcmNoofdays) {
	    saveArray[tableFldConstants.noofdays.ordinal()] = rfcmNoofdays;
	}
	
	/*  Vignesh adding 11may 2026
	public String getRfcmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}
	
	public void setRfcmTempfield2(String rfcmTempfield2){
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = rfcmTempfield2;
	} 
    */
	
	public String getRfcmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}
	
	public void setRfcmTempfield3(String rfcmTempfield3){
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = rfcmTempfield3;
	}
 
	public String getRfcmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}
	
	public void setRfcmTempfield4(String rfcmTempfield4){
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = rfcmTempfield4;
	} 
  
	public String getRfcmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}
	
	public void setRfcmTempfield5(String rfcmTempfield5){
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = rfcmTempfield5;
	} 
  
	public String getRfcmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}
	
	public void setRfcmTempfield6(String rfcmTempfield6){
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = rfcmTempfield6;
	} 
	
	
	
	public String getRfcmTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}
	
	public void setRfcmTempfield7(String rfcmTempfield7){
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = rfcmTempfield7;
	} 
  
	public String getRfcmTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}
	
	public void setRfcmTempfield8(String rfcmTempfield8){
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = rfcmTempfield8;
	} 
  
	public String getRfcmTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}
	
	public void setRfcmTempfield9(String rfcmTempfield9){
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = rfcmTempfield9;
	}
	
	public String getRfcmTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}
	
	public void setRfcmTempfield10(String rfcmTempfield10){
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = rfcmTempfield10;
	}
	
	
	
	public String getRfcmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setRfcmActive(String rfcmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] =rfcmActive;
	}
	public String getRfcmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}
	public void setRfcmCreatedby(String rfcmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] =rfcmCreatedby;
	}
	public String getRfcmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setRfcmCreatedon(String rfcmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] =rfcmCreatedon;
	}
	
	public String getRfcmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setRfcmModifiedon(String rfcmModifiedby) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] =rfcmModifiedby;
	}
	
	public String getRfcmModifiedby() {
		return (String) saveArray[ tableFldConstants.modifiedby.ordinal() ];
	}

	public void setRfcmModifiedby(String rfcmModifiedby) {
		saveArray[ tableFldConstants.modifiedby.ordinal() ] =rfcmModifiedby;
	}


}

