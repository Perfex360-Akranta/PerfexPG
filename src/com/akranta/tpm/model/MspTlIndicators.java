package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class MspTlIndicators {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, name, code, parentid, factoryid, sectionid, cellid, level
		, sortno, remarks,pillar,title,tempfield,tempfield2,tempfield3, active, createdby, createdon, modifiedon
	}

	public MspTlIndicators()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getMspiKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMspiKeyid(String mspiKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mspiKeyid;
	}

	public String getMspiName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setMspiName(String mspiName) {
		saveArray[ tableFldConstants.name.ordinal() ] = mspiName;
	}

	public String getMspiCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setMspiCode(String mspiCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = mspiCode;
	}

	public String getMspiParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setMspiParentid(String mspiParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = mspiParentid;
	}

	public String getMspiFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setMspiFactoryid(String mspiFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = mspiFactoryid;
	}

	public String getMspiSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setMspiSectionid(String mspiSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = mspiSectionid;
	}

	public String getMspiCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setMspiCellid(String mspiCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = mspiCellid;
	}

	public String getMspiLevel() {
		return (String) saveArray[ tableFldConstants.level.ordinal() ];
	}

	public void setMspiLevel(String mspiLevel) {
		saveArray[ tableFldConstants.level.ordinal() ] = mspiLevel;
	}

	public String getMspiSortno() {
		return (String) saveArray[ tableFldConstants.sortno.ordinal() ];
	}

	public void setMspiSortno(String mspiSortno) {
		saveArray[ tableFldConstants.sortno.ordinal() ] = mspiSortno;
	}

	public String getMspiRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMspiRemarks(String mspiRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = mspiRemarks;
	}
	public String getMspiPillar() {
		return (String) saveArray[ tableFldConstants.pillar.ordinal() ];
	}

	public void setMspiPillar(String mspiPillar) {
		saveArray[ tableFldConstants.pillar.ordinal() ] = mspiPillar;
	}
	public String getMspiTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setMspiTitle(String mspiTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = mspiTitle;
	}
	public String getMspiTempfield() {
		return (String) saveArray[ tableFldConstants.tempfield.ordinal() ];
	}

	public void setMspiTempfield(String mspiTempfield) {
		saveArray[ tableFldConstants.tempfield.ordinal() ] = mspiTempfield;
	}
	public String getMspiTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMspiTempfield2(String mspiTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mspiTempfield2;
	}
	public String getMspiTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMspiTempfield3(String mspiTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mspiTempfield3;
	}
	public String getMspiActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMspiActive(String mspiActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mspiActive;
	}

	public String getMspiCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMspiCreatedby(String mspiCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mspiCreatedby;
	}

	public String getMspiCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMspiCreatedon(String mspiCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mspiCreatedon;
	}

	public String getMspiModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMspiModifiedon(String mspiModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mspiModifiedon;
	}

}

