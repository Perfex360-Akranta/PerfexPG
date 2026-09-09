package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.SapExternalServiceDtl.tableFldConstants;

public class SapExternalRepair {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		extm_keyid, keyid, component_no, requirement_qty, uom, item_category
		, storage_location, mat_rework_indi, partno,lineno,tempfield1,tempfield2, 
		tempfield3, active, createdby, createdon, modifiedon
	}

	public SapExternalRepair()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getExtrExtmKeyid() {
		return (String) saveArray[ tableFldConstants.extm_keyid.ordinal() ];
	}

	public void setExtrExtmKeyid(String extrExtmKeyid) {
		saveArray[ tableFldConstants.extm_keyid.ordinal() ] = extrExtmKeyid;
	}

	public String getExtrKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setExtrKeyid(String extrKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = extrKeyid;
	}

	public String getExtrComponentNo() {
		return (String) saveArray[ tableFldConstants.component_no.ordinal() ];
	}

	public void setExtrComponentNo(String extrComponentNo) {
		saveArray[ tableFldConstants.component_no.ordinal() ] = extrComponentNo;
	}

	public String getExtrRequirementQty() {
		return (String) saveArray[ tableFldConstants.requirement_qty.ordinal() ];
	}

	public void setExtrRequirementQty(String extrRequirementQty) {
		saveArray[ tableFldConstants.requirement_qty.ordinal() ] = extrRequirementQty;
	}

	public String getExtrUom() {
		return (String) saveArray[ tableFldConstants.uom.ordinal() ];
	}

	public void setExtrUom(String extrUom) {
		saveArray[ tableFldConstants.uom.ordinal() ] = extrUom;
	}

	public String getExtrItemCategory() {
		return (String) saveArray[ tableFldConstants.item_category.ordinal() ];
	}

	public void setExtrItemCategory(String extrItemCategory) {
		saveArray[ tableFldConstants.item_category.ordinal() ] = extrItemCategory;
	}

	public String getExtrStorageLocation() {
		return (String) saveArray[ tableFldConstants.storage_location.ordinal() ];
	}

	public void setExtrStorageLocation(String extrStorageLocation) {
		saveArray[ tableFldConstants.storage_location.ordinal() ] = extrStorageLocation;
	}

	public String getExtrMatReworkIndi() {
		return (String) saveArray[ tableFldConstants.mat_rework_indi.ordinal() ];
	}

	public void setExtrMatReworkIndi(String extrMatReworkIndi) {
		saveArray[ tableFldConstants.mat_rework_indi.ordinal() ] = extrMatReworkIndi;
	}

	public String getExtrPartno() {
		return (String) saveArray[ tableFldConstants.partno.ordinal() ];
	}

	public void setExtrPartno(String extrPartno) {
		saveArray[ tableFldConstants.partno.ordinal() ] = extrPartno;
	}

	public String getExtrLineNo() {
		return (String) saveArray[ tableFldConstants.lineno.ordinal() ];
	}

	public void setExtrLineNo(String extrLineNo) {
		saveArray[ tableFldConstants.lineno.ordinal() ] = extrLineNo;
	}

	public String getExtrTempField1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setExtrTempField1(String extrTempField1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = extrTempField1;
	}
	
	public String getExtrTempField2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setExtrTempField2(String extrTempField2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = extrTempField2;
	}
	public String getExtrTempField3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setExtrTempField3(String extrTempField3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = extrTempField3;
	}
	public String getExtrActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setExtrActive(String extrActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = extrActive;
	}

	public String getExtrCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setExtrCreatedby(String extrCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = extrCreatedby;
	}

	public String getExtrCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setExtrCreatedon(String extrCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = extrCreatedon;
	}

	public String getExtrModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setExtrModifiedon(String extrModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = extrModifiedon;
	}

	public void setSaveArray(Object[] dataArr) {
		// TODO Auto-generated method stub
		this.saveArray = dataArr;
	}

}

