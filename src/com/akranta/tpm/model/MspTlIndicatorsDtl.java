package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class MspTlIndicatorsDtl {

	private  Object [] saveArray = null;  
	private List<MspTlIndicatorsMst> mspTlIndicatorsMst;

	public enum   tableFldConstants
	{
		keyid, mspi_keyid, name, code, parentid, level, sortno, remarks
		,  tempfield, tempfield2, tempfield3, active, createdby
		, createdon, modifiedon
	}

	public MspTlIndicatorsDtl()
	{
		saveArray = new  Object [ 15 ];
		setMspTlIndicatorsMst(new ArrayList<MspTlIndicatorsMst> ());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public List<MspTlIndicatorsMst> getMspTlIndicatorsMst() {
		return mspTlIndicatorsMst;
	}

	public void setMspTlIndicatorsMst(List<MspTlIndicatorsMst> mspTlIndicatorsMst) {
		this.mspTlIndicatorsMst = mspTlIndicatorsMst;
	}

	public String getMsidKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMsidKeyid(String msidKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = msidKeyid;
	}

	public String getMsidMspiKeyid() {
		return (String) saveArray[ tableFldConstants.mspi_keyid.ordinal() ];
	}

	public void setMsidMspiKeyid(String msidMspiKeyid) {
		saveArray[ tableFldConstants.mspi_keyid.ordinal() ] = msidMspiKeyid;
	}

	public String getMsidName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setMsidName(String msidName) {
		saveArray[ tableFldConstants.name.ordinal() ] = msidName;
	}

	public String getMsidCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setMsidCode(String msidCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = msidCode;
	}

	public String getMsidParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setMsidParentid(String msidParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = msidParentid;
	}

	public String getMsidLevel() {
		return (String) saveArray[ tableFldConstants.level.ordinal() ];
	}

	public void setMsidLevel(String msidLevel) {
		saveArray[ tableFldConstants.level.ordinal() ] = msidLevel;
	}

	public String getMsidSortno() {
		return (String) saveArray[ tableFldConstants.sortno.ordinal() ];
	}

	public void setMsidSortno(String msidSortno) {
		saveArray[ tableFldConstants.sortno.ordinal() ] = msidSortno;
	}

	public String getMsidRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMsidRemarks(String msidRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = msidRemarks;
	}
	

	public String getMsidTempfield() {
		return (String) saveArray[ tableFldConstants.tempfield.ordinal() ];
	}

	public void setMsidTempfield(String msidTempfield) {
		saveArray[ tableFldConstants.tempfield.ordinal() ] = msidTempfield;
	}

	public String getMsidTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMsidTempfield2(String msidTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = msidTempfield2;
	}

	public String getMsidTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMsidTempfield3(String msidTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = msidTempfield3;
	}

	public String getMsidActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMsidActive(String msidActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = msidActive;
	}

	public String getMsidCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMsidCreatedby(String msidCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = msidCreatedby;
	}

	public String getMsidCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMsidCreatedon(String msidCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = msidCreatedon;
	}

	public String getMsidModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMsidModifiedon(String msidModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = msidModifiedon;
	}

}

