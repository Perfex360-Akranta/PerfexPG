package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_PlmTlShutdowncal {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, companyid, locationid, factoryid, sectionid, cellid, machineid
		, assemblyid, frequnit, fromdate, tilldate, relatedto, mouldid
		,  flid,elementid,tempfield3, active, createdby, createdon
		, modifiedon
	}

	public BAL_PlmTlShutdowncal()
	{
		saveArray = new  Object [ 20];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getSdclKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setSdclKeyid(String sdclKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = sdclKeyid;
	}

	public String getSdclCompanyid() {
		return (String) saveArray[ tableFldConstants.companyid.ordinal() ];
	}

	public void setSdclCompanyid(String sdclCompanyid) {
		saveArray[ tableFldConstants.companyid.ordinal() ] = sdclCompanyid;
	}

	public String getSdclLocationid() {
		return (String) saveArray[ tableFldConstants.locationid.ordinal() ];
	}

	public void setSdclLocationid(String sdclLocationid) {
		saveArray[ tableFldConstants.locationid.ordinal() ] = sdclLocationid;
	}

	public String getSdclFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setSdclFactoryid(String sdclFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = sdclFactoryid;
	}

	public String getSdclSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setSdclSectionid(String sdclSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = sdclSectionid;
	}

	public String getSdclCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setSdclCellid(String sdclCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = sdclCellid;
	}

	public String getSdclMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setSdclMachineid(String sdclMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = sdclMachineid;
	}

	public String getSdclAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setSdclAssemblyid(String sdclAssemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = sdclAssemblyid;
	}

	public String getSdclFrequnit() {
		return (String) saveArray[ tableFldConstants.frequnit.ordinal() ];
	}

	public void setSdclFrequnit(String sdclFrequnit) {
		saveArray[ tableFldConstants.frequnit.ordinal() ] = sdclFrequnit;
	}

	public String getSdclFromdate() {
		return (String) saveArray[ tableFldConstants.fromdate.ordinal() ];
	}

	public void setSdclFromdate(String sdclFromdate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = sdclFromdate;
	}

	public String getSdclTilldate() {
		return (String) saveArray[ tableFldConstants.tilldate.ordinal() ];
	}

	public void setSdclTilldate(String sdclTilldate) {
		saveArray[ tableFldConstants.tilldate.ordinal() ] = sdclTilldate;
	}

	public String getSdclRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setSdclRelatedto(String sdclRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = sdclRelatedto;
	}

	public String getSdclMouldid() {
		return (String) saveArray[ tableFldConstants.mouldid.ordinal() ];
	}

	public void setSdclMouldid(String sdclMouldid) {
		saveArray[ tableFldConstants.mouldid.ordinal() ] = sdclMouldid;
	}

	public String getSdclTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setSdclTempfield3(String sdclTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = sdclTempfield3;
	}

	public String getSdclActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setSdclActive(String sdclActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = sdclActive;
	}

	public String getSdclCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setSdclCreatedby(String sdclCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = sdclCreatedby;
	}

	public String getSdclCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setSdclCreatedon(String sdclCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = sdclCreatedon;
	}

	public String getSdclModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setSdclModifiedon(String sdclModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = sdclModifiedon;
	}

	public String getSdclFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}
	public void setSdclFlid(String sdclFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = sdclFlid;
		
	}
	public String getSdclElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}
	public void setSdclElementid(String sdclElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = sdclElementid;
		
	}
}

