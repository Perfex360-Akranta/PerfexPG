package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class WhatifMst {
	private  Object [] saveArray = null;  
	private List<WhatifDtl> whatifDtl;

	public enum   tableFldConstants
	{
		keyid,mocmkeyid,kzbnkeyid,date,facility,team,
		tempfield1 ,tempfield2,tempfield3, tempfield4, tempfield5,
		active, createdby, createdon, modifiedon
	}


	public WhatifMst()
	{
		saveArray = new  Object [  15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getWifmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWifmKeyid(String wifmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wifmKeyid;
	}
	
  public  List<WhatifDtl> getWhatifDetails(){
	  return whatifDtl;
  }
  public void setWhatifDetails(List<WhatifDtl> whatifDtl){
	  this.whatifDtl=whatifDtl;
  }
	public String getWifmMocmKeyid() {
		return (String) saveArray[ tableFldConstants.mocmkeyid.ordinal() ];
	}

	public void setWifmMocmKeyid(String wifmMocmKeyid) {
		saveArray[ tableFldConstants.mocmkeyid.ordinal() ] =wifmMocmKeyid;
	}
	
	public String getWifmKzbnKeyid() {
		return (String) saveArray[ tableFldConstants.kzbnkeyid.ordinal() ];
	}

	public void setWifmKzbnKeyid(String wifmKzbnKeyid) {
		saveArray[ tableFldConstants.kzbnkeyid.ordinal() ] =wifmKzbnKeyid;
	}
	
	public String getWifmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setWifmDate(String wifmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = wifmDate;
	}
	
	public String getWifmFacility() {
		return (String) saveArray[ tableFldConstants.facility.ordinal() ];
	}

	public void setWifmFacility(String wifmFacility) {
		saveArray[ tableFldConstants.facility.ordinal() ] = wifmFacility;
	}
	
	public String getWifmTeam() {
		return (String) saveArray[ tableFldConstants.team.ordinal() ];
	}

	public void setWifmTeam(String wifmTeam) {
		saveArray[ tableFldConstants.team.ordinal() ]=wifmTeam;
	}
	
	public String getWifmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setWifmTempfield1(String wifmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = wifmTempfield1;
	}
	
	public String getWifmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setWifmTempfield2(String wifmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = wifmTempfield2;
	}
	public String getWifmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setWifmTempfield3(String wifmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = wifmTempfield3;
	}
	public String getWifmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setWifmTempfield4(String wifmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = wifmTempfield4;
	}
	public String getWifmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setWifmTempfield5(String wifmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = wifmTempfield5;
	}

	

	
	public String getWifmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWifmActive(String wifmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = wifmActive;
	}

	public String getWifmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWifmCreatedby(String wifmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] =wifmCreatedby;
	}

	public String getWifmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWifmCreatedon(String wifmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] =wifmCreatedon;
	}

	public String getWifmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWifmModifiedon(String wifmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] =wifmModifiedon;
	}

}

