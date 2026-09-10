package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_GenTlCellmst {

	private  Object [] saveArray = null;
	private BAL_GenTlFunctionallocn genTlFunctionallocn;  
	

	public enum   tableFldConstants
	{
		keyid, companyid, factoryid, sectionid, sectiongroup, code, name
		, levelno, pcname, cellorder, effectivedate, inactivateddate
		, costcentreid, flid,active, createdby, createdon, modifiedon
	}

	public BAL_GenTlCellmst()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}
	public String getCellKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setCellKeyid(String cellKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = cellKeyid;
	}

	public String getCellCompanyid() {
		return (String) saveArray[ tableFldConstants.companyid.ordinal() ];
	}

	public void setCellCompanyid(String cellCompanyid) {
		saveArray[ tableFldConstants.companyid.ordinal() ] = cellCompanyid;
	}

	public String getCellFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setCellFactoryid(String cellFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = cellFactoryid;
	}

	public String getCellSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setCellSectionid(String cellSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = cellSectionid;
	}

	public String getCellSectiongroup() {
		return (String) saveArray[ tableFldConstants.sectiongroup.ordinal() ];
	}

	public void setCellSectiongroup(String cellSectiongroup) {
		saveArray[ tableFldConstants.sectiongroup.ordinal() ] = cellSectiongroup;
	}

	public String getCellCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setCellCode(String cellCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = cellCode;
	}

	public String getCellName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setCellName(String cellName) {
		saveArray[ tableFldConstants.name.ordinal() ] = cellName;
	}

	public String getCellLevelno() {
		return (String) saveArray[ tableFldConstants.levelno.ordinal() ];
	}

	public void setCellLevelno(String cellLevelno) {
		saveArray[ tableFldConstants.levelno.ordinal() ] = cellLevelno;
	}

	public String getCellPcname() {
		return (String) saveArray[ tableFldConstants.pcname.ordinal() ];
	}

	public void setCellPcname(String cellPcname) {
		saveArray[ tableFldConstants.pcname.ordinal() ] = cellPcname;
	}

	public String getCellCellorder() {
		return (String) saveArray[ tableFldConstants.cellorder.ordinal() ];
	}

	public void setCellCellorder(String cellCellorder) {
		saveArray[ tableFldConstants.cellorder.ordinal() ] = cellCellorder;
	}

	public String getCellEffectivedate() {
		return (String) saveArray[ tableFldConstants.effectivedate.ordinal() ];
	}

	public void setCellEffectivedate(String cellEffectivedate) {
		saveArray[ tableFldConstants.effectivedate.ordinal() ] = cellEffectivedate;
	}

	public String getCellInactivateddate() {
		return (String) saveArray[ tableFldConstants.inactivateddate.ordinal() ];
	}

	public void setCellInactivateddate(String cellInactivateddate) {
		saveArray[ tableFldConstants.inactivateddate.ordinal() ] = cellInactivateddate;
	}

	public String getCellCostcentreid() {
		return (String) saveArray[ tableFldConstants.costcentreid.ordinal() ];
	}

	public void setCellCostcentreid(String cellCostcentreid) {
		saveArray[ tableFldConstants.costcentreid.ordinal() ] = cellCostcentreid;
	}
	
	public String getCellFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setCellFlid(String cellFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = cellFlid;
	}


	public String getCellActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setCellActive(String cellActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = cellActive;
	}

	public String getCellCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCellCreatedby(String cellCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = cellCreatedby;
	}

	public String getCellCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCellCreatedon(String cellCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = cellCreatedon;
	}

	public String getCellModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setCellModifiedon(String cellModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = cellModifiedon;
	}

	public void setGenTlFunctionallocn(BAL_GenTlFunctionallocn genTlFunctionallocn) {
		this.genTlFunctionallocn = genTlFunctionallocn;
	}

	public BAL_GenTlFunctionallocn getGenTlFunctionallocn() {
		return genTlFunctionallocn;
	}
}

