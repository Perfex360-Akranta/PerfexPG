package com.akranta.tpm.model;

public class GenTlMmcdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, masterid, tablename, columnname, displayname, selectiontype
		, popuptablename, popupcolumnname, popuptablekeyid, iscomboselection
		, combodisplayname, combosaveinfo, savingorder, ismandatory, nonmandatoryvalue
		, columntobedisplayed, columndisplayorder, autogenerationid, autogenerationname
		, conditiontable, conditionfield, conditionvalue, defaulttable
		, defaultcondition, defaultcheck, condfield, conditionno, active
		, createdby, createdon, modifiedon
	}

	public GenTlMmcdtl()
	{
		saveArray = new  Object [ 31 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getMscnKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMscnKeyid(String mscnKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mscnKeyid;
	}

	public String getMscnMasterid() {
		return (String) saveArray[ tableFldConstants.masterid.ordinal() ];
	}

	public void setMscnMasterid(String mscnMasterid) {
		saveArray[ tableFldConstants.masterid.ordinal() ] = mscnMasterid;
	}

	public String getMscnTablename() {
		return (String) saveArray[ tableFldConstants.tablename.ordinal() ];
	}

	public void setMscnTablename(String mscnTablename) {
		saveArray[ tableFldConstants.tablename.ordinal() ] = mscnTablename;
	}

	public String getMscnColumnname() {
		return (String) saveArray[ tableFldConstants.columnname.ordinal() ];
	}

	public void setMscnColumnname(String mscnColumnname) {
		saveArray[ tableFldConstants.columnname.ordinal() ] = mscnColumnname;
	}

	public String getMscnDisplayname() {
		return (String) saveArray[ tableFldConstants.displayname.ordinal() ];
	}

	public void setMscnDisplayname(String mscnDisplayname) {
		saveArray[ tableFldConstants.displayname.ordinal() ] = mscnDisplayname;
	}

	public String getMscnSelectiontype() {
		return (String) saveArray[ tableFldConstants.selectiontype.ordinal() ];
	}

	public void setMscnSelectiontype(String mscnSelectiontype) {
		saveArray[ tableFldConstants.selectiontype.ordinal() ] = mscnSelectiontype;
	}

	public String getMscnPopuptablename() {
		return (String) saveArray[ tableFldConstants.popuptablename.ordinal() ];
	}

	public void setMscnPopuptablename(String mscnPopuptablename) {
		saveArray[ tableFldConstants.popuptablename.ordinal() ] = mscnPopuptablename;
	}

	public String getMscnPopupcolumnname() {
		return (String) saveArray[ tableFldConstants.popupcolumnname.ordinal() ];
	}

	public void setMscnPopupcolumnname(String mscnPopupcolumnname) {
		saveArray[ tableFldConstants.popupcolumnname.ordinal() ] = mscnPopupcolumnname;
	}

	public String getMscnPopuptablekeyid() {
		return (String) saveArray[ tableFldConstants.popuptablekeyid.ordinal() ];
	}

	public void setMscnPopuptablekeyid(String mscnPopuptablekeyid) {
		saveArray[ tableFldConstants.popuptablekeyid.ordinal() ] = mscnPopuptablekeyid;
	}

	public String getMscnIscomboselection() {
		return (String) saveArray[ tableFldConstants.iscomboselection.ordinal() ];
	}

	public void setMscnIscomboselection(String mscnIscomboselection) {
		saveArray[ tableFldConstants.iscomboselection.ordinal() ] = mscnIscomboselection;
	}

	public String getMscnCombodisplayname() {
		return (String) saveArray[ tableFldConstants.combodisplayname.ordinal() ];
	}

	public void setMscnCombodisplayname(String mscnCombodisplayname) {
		saveArray[ tableFldConstants.combodisplayname.ordinal() ] = mscnCombodisplayname;
	}

	public String getMscnCombosaveinfo() {
		return (String) saveArray[ tableFldConstants.combosaveinfo.ordinal() ];
	}

	public void setMscnCombosaveinfo(String mscnCombosaveinfo) {
		saveArray[ tableFldConstants.combosaveinfo.ordinal() ] = mscnCombosaveinfo;
	}

	public String getMscnSavingorder() {
		return (String) saveArray[ tableFldConstants.savingorder.ordinal() ];
	}

	public void setMscnSavingorder(String mscnSavingorder) {
		saveArray[ tableFldConstants.savingorder.ordinal() ] = mscnSavingorder;
	}

	public String getMscnIsmandatory() {
		return (String) saveArray[ tableFldConstants.ismandatory.ordinal() ];
	}

	public void setMscnIsmandatory(String mscnIsmandatory) {
		saveArray[ tableFldConstants.ismandatory.ordinal() ] = mscnIsmandatory;
	}

	public String getMscnNonmandatoryvalue() {
		return (String) saveArray[ tableFldConstants.nonmandatoryvalue.ordinal() ];
	}

	public void setMscnNonmandatoryvalue(String mscnNonmandatoryvalue) {
		saveArray[ tableFldConstants.nonmandatoryvalue.ordinal() ] = mscnNonmandatoryvalue;
	}

	public String getMscnColumntobedisplayed() {
		return (String) saveArray[ tableFldConstants.columntobedisplayed.ordinal() ];
	}

	public void setMscnColumntobedisplayed(String mscnColumntobedisplayed) {
		saveArray[ tableFldConstants.columntobedisplayed.ordinal() ] = mscnColumntobedisplayed;
	}

	public String getMscnColumndisplayorder() {
		return (String) saveArray[ tableFldConstants.columndisplayorder.ordinal() ];
	}

	public void setMscnColumndisplayorder(String mscnColumndisplayorder) {
		saveArray[ tableFldConstants.columndisplayorder.ordinal() ] = mscnColumndisplayorder;
	}

	public String getMscnAutogenerationid() {
		return (String) saveArray[ tableFldConstants.autogenerationid.ordinal() ];
	}

	public void setMscnAutogenerationid(String mscnAutogenerationid) {
		saveArray[ tableFldConstants.autogenerationid.ordinal() ] = mscnAutogenerationid;
	}

	public String getMscnAutogenerationname() {
		return (String) saveArray[ tableFldConstants.autogenerationname.ordinal() ];
	}

	public void setMscnAutogenerationname(String mscnAutogenerationname) {
		saveArray[ tableFldConstants.autogenerationname.ordinal() ] = mscnAutogenerationname;
	}

	public String getMscnConditiontable() {
		return (String) saveArray[ tableFldConstants.conditiontable.ordinal() ];
	}

	public void setMscnConditiontable(String mscnConditiontable) {
		saveArray[ tableFldConstants.conditiontable.ordinal() ] = mscnConditiontable;
	}

	public String getMscnConditionfield() {
		return (String) saveArray[ tableFldConstants.conditionfield.ordinal() ];
	}

	public void setMscnConditionfield(String mscnConditionfield) {
		saveArray[ tableFldConstants.conditionfield.ordinal() ] = mscnConditionfield;
	}

	public String getMscnConditionvalue() {
		return (String) saveArray[ tableFldConstants.conditionvalue.ordinal() ];
	}

	public void setMscnConditionvalue(String mscnConditionvalue) {
		saveArray[ tableFldConstants.conditionvalue.ordinal() ] = mscnConditionvalue;
	}

	public String getMscnDefaulttable() {
		return (String) saveArray[ tableFldConstants.defaulttable.ordinal() ];
	}

	public void setMscnDefaulttable(String mscnDefaulttable) {
		saveArray[ tableFldConstants.defaulttable.ordinal() ] = mscnDefaulttable;
	}

	public String getMscnDefaultcondition() {
		return (String) saveArray[ tableFldConstants.defaultcondition.ordinal() ];
	}

	public void setMscnDefaultcondition(String mscnDefaultcondition) {
		saveArray[ tableFldConstants.defaultcondition.ordinal() ] = mscnDefaultcondition;
	}

	public String getMscnDefaultcheck() {
		return (String) saveArray[ tableFldConstants.defaultcheck.ordinal() ];
	}

	public void setMscnDefaultcheck(String mscnDefaultcheck) {
		saveArray[ tableFldConstants.defaultcheck.ordinal() ] = mscnDefaultcheck;
	}

	public String getMscnCondfield() {
		return (String) saveArray[ tableFldConstants.condfield.ordinal() ];
	}

	public void setMscnCondfield(String mscnCondfield) {
		saveArray[ tableFldConstants.condfield.ordinal() ] = mscnCondfield;
	}

	public String getMscnConditionno() {
		return (String) saveArray[ tableFldConstants.conditionno.ordinal() ];
	}

	public void setMscnConditionno(String mscnConditionno) {
		saveArray[ tableFldConstants.conditionno.ordinal() ] = mscnConditionno;
	}

	public String getMscnActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMscnActive(String mscnActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mscnActive;
	}

	public String getMscnCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMscnCreatedby(String mscnCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mscnCreatedby;
	}

	public String getMscnCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMscnCreatedon(String mscnCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mscnCreatedon;
	}

	public String getMscnModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMscnModifiedon(String mscnModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mscnModifiedon;
	}

}

