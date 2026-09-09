package com.akranta.tpm.model;

import java.util.List;

public class PcsTlLossphenfactorylink {

	private  Object [] saveArray = null;
	 
	private List <PcsTlLossphenfactorylink> methodPcsTlLossphenfactorylink;
	private String ppflIsDelete="";

	public enum   tableFldConstants
	{	
		keyid, plpm_keyid, factoryid, tempfield1, tempfield2, active
		, createdby, createdon, modifiedon
	}
	public PcsTlLossphenfactorylink()
	{
		saveArray = new  Object [ 9 ];
	}
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPpflKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPpflKeyid(String ppflKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = ppflKeyid;
	}

	public String getPpflPlpmKeyid() {
		return (String) saveArray[ tableFldConstants.plpm_keyid.ordinal() ];
	}

	public void setPpflPlpmKeyid(String ppflPlpmKeyid) {
		saveArray[ tableFldConstants.plpm_keyid.ordinal() ] = ppflPlpmKeyid;
	}

	public String getPpflFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setPpflFactoryid(String ppflFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = ppflFactoryid;
	}

	public String getPpflTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPpflTempfield1(String ppflTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = ppflTempfield1;
	}

	public String getPpflTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPpflTempfield2(String ppflTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = ppflTempfield2;
	}

	public String getPpflActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPpflActive(String ppflActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = ppflActive;
	}

	public String getPpflCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPpflCreatedby(String ppflCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = ppflCreatedby;
	}

	public String getPpflCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPpflCreatedon(String ppflCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = ppflCreatedon;
	}

	public String getPpflModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPpflModifiedon(String ppflModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = ppflModifiedon;
	}
	
	public void setmethodPillarFactlink(
			List<PcsTlLossphenfactorylink> pillarFactLinkList) {
		this.methodPcsTlLossphenfactorylink =pillarFactLinkList;
		
	}
	public List <PcsTlLossphenfactorylink>  getmethodPillarFactlink(
			) {
		return this.methodPcsTlLossphenfactorylink ;
		
	}

	public void setIsDelete(String ppflIsDelete) {
		this.ppflIsDelete = ppflIsDelete;
	}

	public String getIsDelete() {
		return this.ppflIsDelete;
	}

}

