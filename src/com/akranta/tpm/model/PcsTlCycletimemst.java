package com.akranta.tpm.model;


public class PcsTlCycletimemst {

	private  Object [] saveArray = null;  
	
	private String selectionid;
	public enum   tableFldConstants
	{
		keyid, cellid, productid, cycletime, manpower, fromdate, tilldate
		, prodgroupid, machineid, sectionid, factoryid, cavity, mandrels
		, tempfield1, tempfield2, tempfield3, active, createdby, createdon
		, modifiedon
	}

	public PcsTlCycletimemst()
	{
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		 this.saveArray = saveArray;
	}
	
	public String getCytmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCytmKeyid(String cytmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cytmKeyid;
	}

	public String getCytmCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setCytmCellid(String cytmCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = cytmCellid;
	}

	public String getCytmProductid() {
		return (String) saveArray[ tableFldConstants.productid.ordinal() ];
	}

	public void setCytmProductid(String cytmProductid) {
		saveArray[ tableFldConstants.productid.ordinal() ] = cytmProductid;
	}

	public String getCytmCycletime() {
		return (String) saveArray[ tableFldConstants.cycletime.ordinal() ];
	}

	public void setCytmCycletime(String cytmCycletime) {
		saveArray[ tableFldConstants.cycletime.ordinal() ] = cytmCycletime;
	}

	public String getCytmManpower() {
		return (String) saveArray[ tableFldConstants.manpower.ordinal() ];
	}

	public void setCytmManpower(String cytmManpower) {
		saveArray[ tableFldConstants.manpower.ordinal() ] = cytmManpower;
	}

	public String getCytmFromdate() {
		return (String) saveArray[ tableFldConstants.fromdate.ordinal() ];
	}

	public void setCytmFromdate(String cytmFromdate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = cytmFromdate;
	}

	public String getCytmTilldate() {
		return (String) saveArray[ tableFldConstants.tilldate.ordinal() ];
	}

	public void setCytmTilldate(String cytmTilldate) {
		saveArray[ tableFldConstants.tilldate.ordinal() ] = cytmTilldate;
	}

	public String getCytmProdgroupid() {
		return (String) saveArray[ tableFldConstants.prodgroupid.ordinal() ];
	}

	public void setCytmProdgroupid(String cytmProdgroupid) {
		saveArray[ tableFldConstants.prodgroupid.ordinal() ] = cytmProdgroupid;
	}

	public String getCytmMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setCytmMachineid(String cytmMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = cytmMachineid;
	}

	public String getCytmSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setCytmSectionid(String cytmSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = cytmSectionid;
	}

	public String getCytmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setCytmFactoryid(String cytmFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = cytmFactoryid;
	}

	public String getCytmCavity() {
		return (String) saveArray[ tableFldConstants.cavity.ordinal() ];
	}

	public void setCytmCavity(String cytmCavity) {
		saveArray[ tableFldConstants.cavity.ordinal() ] = cytmCavity;
	}

	public String getCytmMandrels() {
		return (String) saveArray[ tableFldConstants.mandrels.ordinal() ];
	}

	public void setCytmMandrels(String cytmMandrels) {
		saveArray[ tableFldConstants.mandrels.ordinal() ] = cytmMandrels;
	}

	public String getCytmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setCytmTempfield1(String cytmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = cytmTempfield1;
	}

	public String getCytmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setCytmTempfield2(String cytmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = cytmTempfield2;
	}

	public String getCytmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setCytmTempfield3(String cytmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = cytmTempfield3;
	}

	public String getCytmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCytmActive(String cytmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cytmActive;
	}

	public String getCytmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCytmCreatedby(String cytmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cytmCreatedby;
	}

	public String getCytmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCytmCreatedon(String cytmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cytmCreatedon;
	}

	public String getCytmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCytmModifiedon(String cytmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cytmModifiedon;
	}

		public void setSelectionid(String selectionid) {
		this.selectionid = selectionid;
	}

	public String getSelectionid() {
		return selectionid;
	}

}

