package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.sql.TableFieldType;

public class MocTeamConfigmst {

	private  Object [] saveArray = null; 
	private MocRfcmst mocRfcmst;
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public enum   tableFldConstants
	{
		keyid,masterid,roleid,groupnum,initial,hazop,finalapp,empid,initialYN,inapprovedby,inapprovedate,inapprovestatus,
		inapproverem,inapproverwkflg,inapproverwkdte,inapproverwkrem,tempfield1,tempfield2,tempfield3,hazopYN,hzapprovedby,hzapprovedate,hzapprovestatus,
		hzapproverem,hzapproverwkflg,hzapproverwkdte,hzapproverwkrem,tempfield4,tempfield5,tempfield6,finalYN,faapprovedby,faapprovedate,faapprovestatus
		,faapproverem,faapproverwkflg,faapproverwkdte,faapproverwkrem,tempfield7,tempfield8,tempfield9,tempfield10,active,createdby,createdon,modifiedon
	}

	public MocTeamConfigmst()
	{
		saveArray = new  Object [ 46 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public String getMctcKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMctcKeyid(String mctcKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mctcKeyid;
	}
	

	public String getMctcmasterid() {
		return (String) saveArray[ tableFldConstants.masterid.ordinal() ];
	}

	public void setMctcmasterid(String mctcmasterid) {
		saveArray[ tableFldConstants.masterid.ordinal() ] =mctcmasterid;
	}

	public String getMctcroleid() {
		return (String) saveArray[ tableFldConstants.roleid.ordinal() ];
	}

	public void setMctcroleid(String mctcroleid) {
		saveArray[ tableFldConstants.roleid.ordinal() ] =mctcroleid;
	}

	public String getMctcgroupno() {
		return (String) saveArray[ tableFldConstants.groupnum.ordinal() ];
	}

	public void setMctcgroupno(String mctcgroupno) {
		saveArray[ tableFldConstants.groupnum.ordinal() ] =mctcgroupno;
	} 

	public String getMctcInitial() {
		return (String) saveArray[ tableFldConstants.initial.ordinal() ];
	}

	public void setMctcInitial(String mctcInitial) {
		saveArray[ tableFldConstants.initial.ordinal() ] =mctcInitial;
	} 
	public String getMctcHazop() {
		return (String) saveArray[ tableFldConstants.hazop.ordinal() ];
	}

	public void setMctcHazop(String mctcHazop) {
		saveArray[ tableFldConstants.hazop.ordinal() ] =mctcHazop;
	} 
	
	public String getMctcfinal() {
		return (String) saveArray[ tableFldConstants.finalapp.ordinal() ];
	}

	public void setMctcfinal(String mctcfinal) {
		saveArray[ tableFldConstants.finalapp.ordinal() ] =mctcfinal;
	} 	
	public String getMctcempid() {
		return (String) saveArray[ tableFldConstants.empid.ordinal() ];
	}

	public void setMctcempid(String Mctcempid) {
		saveArray[ tableFldConstants.empid.ordinal() ] =Mctcempid;
	}
	
	public String getMctcInitialYN() {
		return (String) saveArray[ tableFldConstants.initialYN.ordinal() ];
	}

	public void setMctcInitialYN(String MctcInitialYN) {
		saveArray[ tableFldConstants.initialYN.ordinal() ] =MctcInitialYN;
	} 
	
	public String getMctcInaprovedby() {
		return (String) saveArray[ tableFldConstants.inapprovedby.ordinal() ];
	}

	public void setMctcInaprovedby(String mctcInaprovedby) {
		saveArray[ tableFldConstants.inapprovedby.ordinal() ] =mctcInaprovedby;
	} 
	
	public String getMctcInaprovedDte() {
		return (String) saveArray[ tableFldConstants.inapprovedate.ordinal() ];
	}

	public void setMctcInaprovedDte(String mctcInaprovedDte) {
		saveArray[ tableFldConstants.inapprovedate.ordinal() ] =mctcInaprovedDte;
	} 
	
	
	public String getMctcInaprovedStatus() {
		return (String) saveArray[ tableFldConstants.inapprovestatus.ordinal() ];
	}

	public void setMctcInaprovedStatus(String mctcInaprovedStatus) {
		saveArray[ tableFldConstants.inapprovestatus.ordinal() ] =mctcInaprovedStatus;
	} 
	
	public String getMctcInaprovedRem() {
		return (String) saveArray[ tableFldConstants.inapproverem.ordinal() ];
	}

	public void setMctcInaprovedRem(String mctcInaprovedRem) {
		saveArray[ tableFldConstants.inapproverem.ordinal() ] =mctcInaprovedRem;
	} 
	
	public String getMctcInaproverwkflg() {
		return (String) saveArray[ tableFldConstants.inapproverwkflg.ordinal() ];
	}

	public void setMctcInaproverwkflg(String mctcInaproverwkflg) {
		saveArray[ tableFldConstants.inapproverwkflg.ordinal() ] =mctcInaproverwkflg;
	} 
	
	public String getMctcInaproverwkdte() {
		return (String) saveArray[ tableFldConstants.inapproverwkdte.ordinal() ];
	}

	public void setMctcInaproverwkdte(String mctcInaproverwkdte) {
		saveArray[ tableFldConstants.inapproverwkdte.ordinal() ] =mctcInaproverwkdte;
	} 
	
	public String getMctcInaproverwkrem() {
		return (String) saveArray[ tableFldConstants.inapproverwkrem.ordinal() ];
	}

	public void setMctcInaproverwkrem(String mctcInaproverwkrem) {
		saveArray[ tableFldConstants.inapproverwkrem.ordinal() ] =mctcInaproverwkrem;
	} 

public String getMctcTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}
	
	public void setMctcTempfield1(String mctcTempfield1){
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mctcTempfield1;
	} 
  
	public String getMctcTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}
	
	public void setMctcTempfield2(String mctcTempfield2){
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mctcTempfield2;
	} 
  
	public String getMctcTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}
	
	public void setMctcTempfield3(String mctcTempfield3){
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mctcTempfield3;
	}
	
	public String getMctcHazopYN() {
		return (String) saveArray[ tableFldConstants.hazopYN.ordinal() ];
	}

	public void setMctcHazopYN(String MctcHazopYN) {
		saveArray[ tableFldConstants.hazopYN.ordinal() ] =MctcHazopYN;
	} 
	
	public String getMctcHzaprovedby() {
		return (String) saveArray[ tableFldConstants.hzapprovedby.ordinal() ];
	}

	public void setMctcHzaprovedby(String mctcHzaprovedby) {
		saveArray[ tableFldConstants.hzapprovedby.ordinal() ] =mctcHzaprovedby;
	} 
	
	public String getMctcHzaprovedDte() {
		return (String) saveArray[ tableFldConstants.hzapprovedate.ordinal() ];
	}

	public void setMctcHzaprovedDte(String mctcHzaprovedDte) {
		saveArray[ tableFldConstants.hzapprovedate.ordinal() ] =mctcHzaprovedDte;
	} 
	
	
	public String getMctcHzaprovedStatus() {
		return (String) saveArray[ tableFldConstants.hzapprovestatus.ordinal() ];
	}

	public void setMctcHzaprovedStatus(String mctcHzaprovedStatus) {
		saveArray[ tableFldConstants.hzapprovestatus.ordinal() ] =mctcHzaprovedStatus;
	} 
	
	public String getMctcHzaprovedRem() {
		return (String) saveArray[ tableFldConstants.hzapproverem.ordinal() ];
	}

	public void setMctcHzaprovedRem(String mctcHzaprovedRem) {
		saveArray[ tableFldConstants.hzapproverem.ordinal() ] =mctcHzaprovedRem;
	} 
	
	public String getMctcHzaproverwkflg() {
		return (String) saveArray[ tableFldConstants.hzapproverwkflg.ordinal() ];
	}

	public void setMctcHzaproverwkflg(String mctcHzaproverwkflg) {
		saveArray[ tableFldConstants.hzapproverwkflg.ordinal() ] =mctcHzaproverwkflg;
	}
	
	public String getMctcHzaproverwkdte() {
		return (String) saveArray[ tableFldConstants.hzapproverwkdte.ordinal() ];
	}

	public void setMctcHzaproverwkdte(String mctcHzaproverwkdte) {
		saveArray[ tableFldConstants.hzapproverwkdte.ordinal() ] =mctcHzaproverwkdte;
	} 
	
	public String getMctcHzaproverwkrem() {
		return (String) saveArray[ tableFldConstants.hzapproverwkrem.ordinal() ];
	}

	public void setMctcHzaproverwkrem(String mctcHzaproverwkrem) {
		saveArray[ tableFldConstants.hzapproverwkrem.ordinal() ] =mctcHzaproverwkrem;
	} 
	
public String getMctcTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}
	
	public void setMctcTempfield4(String mctcTempfield4){
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = mctcTempfield4;
	} 
  
	public String getMctcTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}
	
	public void setMctcTempfield5(String mctcTempfield5){
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = mctcTempfield5;
	} 
  
	public String getMctcTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}
	
	public void setMctcTempfield6(String mctcTempfield6){
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = mctcTempfield6;
	} 
	
	
	public String getMctcFinalYN() {
		return (String) saveArray[ tableFldConstants.finalYN.ordinal() ];
	}

	public void setMctcFinalYN(String MctcFinalYN) {
		saveArray[ tableFldConstants.hazopYN.ordinal() ] =MctcFinalYN;
	} 
	
	public String getMctcFaaprovedby() {
		return (String) saveArray[ tableFldConstants.faapprovedby.ordinal() ];
	}

	public void setMctcFaaprovedby(String mctcFaaprovedby) {
		saveArray[ tableFldConstants.faapprovedby.ordinal() ] =mctcFaaprovedby;
	} 
	
	public String getMctcFaaprovedDte() {
		return (String) saveArray[ tableFldConstants.faapprovedate.ordinal() ];
	}

	public void setMctcFaaprovedDte(String mctcFaaprovedDte) {
		saveArray[ tableFldConstants.faapprovedate.ordinal() ] =mctcFaaprovedDte;
	} 
	
	
	public String getMctcFaaprovedStatus() {
		return (String) saveArray[ tableFldConstants.faapprovestatus.ordinal() ];
	}

	public void setMctcFaaprovedStatus(String mctcFaaprovedStatus) {
		saveArray[ tableFldConstants.faapprovestatus.ordinal() ] =mctcFaaprovedStatus;
	} 
	
	public String getMctcFaaprovedRem() {
		return (String) saveArray[ tableFldConstants.faapproverem.ordinal() ];
	}

	public void setMctcFaaprovedRem(String mctcFaaprovedRem) {
		saveArray[ tableFldConstants.faapproverem.ordinal() ] =mctcFaaprovedRem;
	} 
	
	public String getMctcFaaproverwkflg() {
		return (String) saveArray[ tableFldConstants.faapproverwkflg.ordinal() ];
	}

	public void setMctcFaaproverwkflg(String mctcFaaproverwkflg) {
		saveArray[ tableFldConstants.faapproverwkflg.ordinal() ] =mctcFaaproverwkflg;
	}
	
	public String getMctcFaaproverwkdte() {
		return (String) saveArray[ tableFldConstants.faapproverwkdte.ordinal() ];
	}

	public void setMctcFaaproverwkdte(String mctcFaaproverwkdte) {
		saveArray[ tableFldConstants.faapproverwkdte.ordinal() ] =mctcFaaproverwkdte;
	} 
	
	public String getMctcFaaproverwkrem() {
		return (String) saveArray[ tableFldConstants.faapproverwkrem.ordinal() ];
	}

	public void setMctcFaaproverwkrem(String mctcFaaproverwkrem) {
		saveArray[ tableFldConstants.faapproverwkrem.ordinal() ] =mctcFaaproverwkrem;
	} 
	
	public String getMctcTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}
	
	public void setMctcTempfield7(String mctcTempfield7){
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = mctcTempfield7;
	} 
  
	public String getMctcTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}
	
	public void setMctcTempfield8(String mctcTempfield8){
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = mctcTempfield8;
	} 
  
	public String getMctcTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}
	
	public void setMctcTempfield9(String mctcTempfield9){
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = mctcTempfield9;
	}
	
	public String getMctcTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}
	
	public void setMctcTempfield10(String mctcTempfield10){
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = mctcTempfield10;
	}
	
	
	
	public String getMctcActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMctcActive(String mctcActive) {
		saveArray[ tableFldConstants.active.ordinal() ] =mctcActive;
	}
	public String getMctcCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}
	public void setMctcCreatedby(String mctcCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] =mctcCreatedby;
	}
	public String getMctcCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMctcCreatedon(String mctcCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] =mctcCreatedon;
	}
	
	public String getMctcModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMctcModifiedon(String mctcModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] =mctcModifiedon;
	}


	public void setMctcRfcmst(MocRfcmst mocRfcmst) {
		this.mocRfcmst = mocRfcmst;
	}

	public MocRfcmst getMctcRfcmst() {
		return mocRfcmst;
	}


}

