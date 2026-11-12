package com.akranta.tpm.model;
import java.util.List;

public class EhsTlInspectionmst {

	private  Object [] saveArray = null;  
	
	private List<EhsTlInspectiondtl> ehsTlInspectiondtlList;


	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public enum   tableFldConstants
	{
		keyid, type, date, flid, area, inspected_by, members_present
		, remarks, tempfield1, tempfield2, tempfield3, active, createdby
		, createdon, modifiedon
	}

	public EhsTlInspectionmst()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getInsmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setInsmKeyid(String insmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = insmKeyid;
	}

	public String getInsmType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setInsmType(String insmType) {
		saveArray[ tableFldConstants.type.ordinal() ] = insmType;
	}

	public String getInsmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setInsmDate(String insmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = insmDate;
	}

	public String getInsmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setInsmFlid(String insmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = insmFlid;
	}

	public String getInsmArea() {
		return (String) saveArray[ tableFldConstants.area.ordinal() ];
	}

	public void setInsmArea(String insmArea) {
		saveArray[ tableFldConstants.area.ordinal() ] = insmArea;
	}

	public String getInsmInspectedBy() {
		return (String) saveArray[ tableFldConstants.inspected_by.ordinal() ];
	}

	public void setInsmInspectedBy(String insmInspectedBy) {
		saveArray[ tableFldConstants.inspected_by.ordinal() ] = insmInspectedBy;
	}

	public String getInsmMembersPresent() {
		return (String) saveArray[ tableFldConstants.members_present.ordinal() ];
	}

	public void setInsmMembersPresent(String insmMembersPresent) {
		saveArray[ tableFldConstants.members_present.ordinal() ] = insmMembersPresent;
	}

	public String getInsmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setInsmRemarks(String insmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = insmRemarks;
	}

	public String getInsmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setInsmTempfield1(String insmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = insmTempfield1;
	}

	public String getInsmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setInsmTempfield2(String insmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = insmTempfield2;
	}

	public String getInsmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setInsmTempfield3(String insmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = insmTempfield3;
	}

	public String getInsmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setInsmActive(String insmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = insmActive;
	}

	public String getInsmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setInsmCreatedby(String insmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = insmCreatedby;
	}

	public String getInsmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setInsmCreatedon(String insmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = insmCreatedon;
	}

	public String getInsmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setInsmModifiedon(String insmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = insmModifiedon;
	}

	public List<EhsTlInspectiondtl> getEhsTlInspectiondtlList() {
		return ehsTlInspectiondtlList;
	}

	public void setEhsTlInspectiondtlList(
			List<EhsTlInspectiondtl> ehsTlInspectiondtlList) {
		this.ehsTlInspectiondtlList = ehsTlInspectiondtlList;
	}

}

