package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_BdmTlShiftwisesplit {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, assemblyid, phenomenaid
		, causeid, bdentrydate, bdsplitdate, shiftid, bdno, downtime
		, noplantime, relatedto, activityno, active, createdby, createdon
		, modifiedon
	}

	public BAL_BdmTlShiftwisesplit()
	{
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getBdssKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setBdssKeyid(String bdsskeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = bdsskeyid;
	}

	public String getBdssFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setBdssFactoryid(String bdssfactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = bdssfactoryid;
	}

	public String getBdssSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setBdssSectionid(String bdsssectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = bdsssectionid;
	}

	public String getBdssCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setBdssCellid(String bdsscellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = bdsscellid;
	}

	public String getBdssMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setBdssMachineid(String bdssmachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = bdssmachineid;
	}

	public String getBdssAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setBdssAssemblyid(String bdssassemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = bdssassemblyid;
	}

	public String getBdssPhenomenaid() {
		return (String) saveArray[ tableFldConstants.phenomenaid.ordinal() ];
	}

	public void setBdssPhenomenaid(String bdssphenomenaid) {
		saveArray[ tableFldConstants.phenomenaid.ordinal() ] = bdssphenomenaid;
	}

	public String getBdssCauseid() {
		return (String) saveArray[ tableFldConstants.causeid.ordinal() ];
	}

	public void setBdssCauseid(String bdsscauseid) {
		saveArray[ tableFldConstants.causeid.ordinal() ] = bdsscauseid;
	}

	public String getBdssBdentrydate() {
		return (String) saveArray[ tableFldConstants.bdentrydate.ordinal() ];
	}

	public void setBdssBdentrydate(String bdssbdentrydate) {
		saveArray[ tableFldConstants.bdentrydate.ordinal() ] = bdssbdentrydate;
	}

	public String getBdssBdsplitdate() {
		return (String) saveArray[ tableFldConstants.bdsplitdate.ordinal() ];
	}

	public void setBdssBdsplitdate(String bdssbdsplitdate) {
		saveArray[ tableFldConstants.bdsplitdate.ordinal() ] = bdssbdsplitdate;
	}

	public String getBdssShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setBdssShiftid(String bdssshiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = bdssshiftid;
	}

	public String getBdssBdno() {
		return (String) saveArray[ tableFldConstants.bdno.ordinal() ];
	}

	public void setBdssBdno(String bdssbdno) {
		saveArray[ tableFldConstants.bdno.ordinal() ] = bdssbdno;
	}

	public String getBdssDowntime() {
		return (String) saveArray[ tableFldConstants.downtime.ordinal() ];
	}

	public void setBdssDowntime(String bdssdowntime) {
		saveArray[ tableFldConstants.downtime.ordinal() ] = bdssdowntime;
	}

	public String getBdssNoplantime() {
		return (String) saveArray[ tableFldConstants.noplantime.ordinal() ];
	}

	public void setBdssNoplantime(String bdssnoplantime) {
		saveArray[ tableFldConstants.noplantime.ordinal() ] = bdssnoplantime;
	}

	public String getBdssRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setBdssRelatedto(String bdssrelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = bdssrelatedto;
	}

	public String getBdssActivityno() {
		return (String) saveArray[ tableFldConstants.activityno.ordinal() ];
	}

	public void setBdssActivityno(String bdssactivityno) {
		saveArray[ tableFldConstants.activityno.ordinal() ] = bdssactivityno;
	}

	public String getBdssActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBdssActive(String bdssactive) {
		saveArray[ tableFldConstants.active.ordinal() ] = bdssactive;
	}

	public String getBdssCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setBdssCreatedby(String bdsscreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = bdsscreatedby;
	}

	public String getBdssCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setBdssCreatedon(String bdsscreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = bdsscreatedon;
	}

	public String getBdssModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setBdssModifiedon(String bdssmodifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = bdssmodifiedon;
	}

}

