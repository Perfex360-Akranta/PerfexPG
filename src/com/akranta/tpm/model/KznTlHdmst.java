package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class KznTlHdmst {

	private  Object [] saveArray = null;  
	private String dbMode;
	private String selectionFlag;

	public enum   tableFldConstants
	{
		keyid, slno, kaizenid, refdoctype, refdocno, factoryid, sectionid
		, cellid, machineid, kaizenlink, kaizenlinktype, assemblyid, phenomenaid
		, causeid, lossid, targetdate, responsibilityid, status, woid
		, wofeedbackid, completeddate, completedby, remarks, active, createdby
		, createdon, modifiedon
	}

	public KznTlHdmst()
	{
		saveArray = new  Object [ 27 ];
	}

	/**
	 * @param saveArray the saveArray to set
	 */
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public Object[] getSaveArray() {
		return saveArray;
	}


	public String getKhdmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKhdmKeyid(String khdmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = khdmKeyid;
	}

	public String getKhdmSlno() {
		return (String) saveArray[ tableFldConstants.slno.ordinal() ];
	}

	public void setKhdmSlno(String khdmSlno) {
		saveArray[ tableFldConstants.slno.ordinal() ] = khdmSlno;
	}

	public String getKhdmKaizenid() {
		return (String) saveArray[ tableFldConstants.kaizenid.ordinal() ];
	}

	public void setKhdmKaizenid(String khdmKaizenid) {
		saveArray[ tableFldConstants.kaizenid.ordinal() ] = khdmKaizenid;
	}

	public String getKhdmRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setKhdmRefdoctype(String khdmRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = khdmRefdoctype;
	}

	public String getKhdmRefdocno() {
		return (String) saveArray[ tableFldConstants.refdocno.ordinal() ];
	}

	public void setKhdmRefdocno(String khdmRefdocno) {
		saveArray[ tableFldConstants.refdocno.ordinal() ] = khdmRefdocno;
	}

	public String getKhdmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setKhdmFactoryid(String khdmFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = khdmFactoryid;
	}

	public String getKhdmSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setKhdmSectionid(String khdmSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = khdmSectionid;
	}

	public String getKhdmCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setKhdmCellid(String khdmCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = khdmCellid;
	}

	public String getKhdmMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setKhdmMachineid(String khdmMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = khdmMachineid;
	}

	public String getKhdmKaizenlink() {
		return (String) saveArray[ tableFldConstants.kaizenlink.ordinal() ];
	}

	public void setKhdmKaizenlink(String khdmKaizenlink) {
		saveArray[ tableFldConstants.kaizenlink.ordinal() ] = khdmKaizenlink;
	}

	public String getKhdmKaizenlinktype() {
		return (String) saveArray[ tableFldConstants.kaizenlinktype.ordinal() ];
	}

	public void setKhdmKaizenlinktype(String khdmKaizenlinktype) {
		saveArray[ tableFldConstants.kaizenlinktype.ordinal() ] = khdmKaizenlinktype;
	}

	public String getKhdmAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setKhdmAssemblyid(String khdmAssemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = khdmAssemblyid;
	}

	public String getKhdmPhenomenaid() {
		return (String) saveArray[ tableFldConstants.phenomenaid.ordinal() ];
	}

	public void setKhdmPhenomenaid(String khdmPhenomenaid) {
		saveArray[ tableFldConstants.phenomenaid.ordinal() ] = khdmPhenomenaid;
	}

	public String getKhdmCauseid() {
		return (String) saveArray[ tableFldConstants.causeid.ordinal() ];
	}

	public void setKhdmCauseid(String khdmCauseid) {
		saveArray[ tableFldConstants.causeid.ordinal() ] = khdmCauseid;
	}

	public String getKhdmLossid() {
		return (String) saveArray[ tableFldConstants.lossid.ordinal() ];
	}

	public void setKhdmLossid(String khdmLossid) {
		saveArray[ tableFldConstants.lossid.ordinal() ] = khdmLossid;
	}

	public String getKhdmTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setKhdmTargetdate(String khdmTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = khdmTargetdate;
	}

	public String getKhdmResponsibilityid() {
		return (String) saveArray[ tableFldConstants.responsibilityid.ordinal() ];
	}

	public void setKhdmResponsibilityid(String khdmResponsibilityid) {
		saveArray[ tableFldConstants.responsibilityid.ordinal() ] = khdmResponsibilityid;
	}

	public String getKhdmStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setKhdmStatus(String khdmStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = khdmStatus;
	}

	public String getKhdmWoid() {
		return (String) saveArray[ tableFldConstants.woid.ordinal() ];
	}

	public void setKhdmWoid(String khdmWoid) {
		saveArray[ tableFldConstants.woid.ordinal() ] = khdmWoid;
	}

	public String getKhdmWofeedbackid() {
		return (String) saveArray[ tableFldConstants.wofeedbackid.ordinal() ];
	}

	public void setKhdmWofeedbackid(String khdmWofeedbackid) {
		saveArray[ tableFldConstants.wofeedbackid.ordinal() ] = khdmWofeedbackid;
	}

	public String getKhdmCompleteddate() {
		return (String) saveArray[ tableFldConstants.completeddate.ordinal() ];
	}

	public void setKhdmCompleteddate(String khdmCompleteddate) {
		saveArray[ tableFldConstants.completeddate.ordinal() ] = khdmCompleteddate;
	}

	public String getKhdmCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setKhdmCompletedby(String khdmCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = khdmCompletedby;
	}

	public String getKhdmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setKhdmRemarks(String khdmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = khdmRemarks;
	}

	public String getKhdmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKhdmActive(String khdmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = khdmActive;
	}

	public String getKhdmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKhdmCreatedby(String khdmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = khdmCreatedby;
	}

	public String getKhdmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKhdmCreatedon(String khdmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = khdmCreatedon;
	}

	public String getKhdmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKhdmModifiedon(String khdmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = khdmModifiedon;
	}

	public void setSelectionFlag(String selectionFlag) {
		this.selectionFlag = selectionFlag;
	}

	public String getSelectionFlag() {
		return selectionFlag;
	}

	public void setDbMode(String dbMode) {
		this.dbMode = dbMode;
	}

	public String getDbMode() {
		return dbMode;
	}

}

