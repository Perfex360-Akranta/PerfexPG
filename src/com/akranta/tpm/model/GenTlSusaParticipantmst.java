package com.akranta.tpm.model;


public class GenTlSusaParticipantmst {

	private  Object [] saveArray = null;
	
		

	public enum   tableFldConstants
	{    
		keyid, susn_keyid, empm_keyid,
		 tempfield1, tempfield2,tempfield3, active, createdby, createdon, modifiedon
	}
	
	
	public GenTlSusaParticipantmst()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSustKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSustKeyid(String sustKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sustKeyid;
	}

	public String getSustSusaKeyid() {
		return (String) saveArray[ tableFldConstants.susn_keyid.ordinal() ];
	}

	public void setSustSusaKeyid(String sustSusnKeyid) {
		saveArray[ tableFldConstants.susn_keyid.ordinal() ] = sustSusnKeyid;
	}


	public String getSustEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setSustEmpmKeyid(String sustEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = sustEmpmKeyid;
	}

	public String getSustTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setSustTempfield1(String sustTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = sustTempfield1;
	}
	public String getSustTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setSustTempfield2(String sustTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = sustTempfield2;
	}

	public String getSustTempfield3(){
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSustTempfield3(String sustTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = sustTempfield3;
	}

	public String getSustActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSustActive(String sustActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = sustActive;
	}

	public String getSustCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSustCreatedby(String sustCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = sustCreatedby;
	}

	public String getSustCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSustCreatedon(String sustCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = sustCreatedon;
	}

	public String getSustModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSustModifiedon(String sustModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = sustModifiedon;
	}

	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}




	
		

	
}