package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SheTlRiskassessmentmst {

	private  Object [] saveArray = null;  
	private List<SheTlRiskassessmentdtl> sheTlRiskassessmentdtlList;
	private String elementid;

	public enum   tableFldConstants
	{
		keyid, flid, area, title, date, preparedby, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public SheTlRiskassessmentmst()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] dataArr) {
		 this.saveArray = dataArr;
	}
	
	public List<SheTlRiskassessmentdtl> getRiskDetails() {
		return sheTlRiskassessmentdtlList;
	}

	public void setRiskDetails(List<SheTlRiskassessmentdtl> sheTlRiskassessmentdtlList) {
		this.sheTlRiskassessmentdtlList = sheTlRiskassessmentdtlList;
	}
	
	public String getRasmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setRasmKeyid(String rasmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = rasmKeyid;
	}

	public String getRasmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setRasmFlid(String rasmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = rasmFlid;
	}

	public String getRasmArea() {
		return (String) saveArray[ tableFldConstants.area.ordinal() ];
	}

	public void setRasmArea(String rasmArea) {
		saveArray[ tableFldConstants.area.ordinal() ] = rasmArea;
	}

	public String getRasmTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setRasmTitle(String rasmTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = rasmTitle;
	}

	public String getRasmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setRasmDate(String rasmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = rasmDate;
	}

	public String getRasmPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setRasmPreparedby(String rasmPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = rasmPreparedby;
	}

	public String getRasmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setRasmTempfield1(String rasmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = rasmTempfield1;
	}

	public String getRasmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setRasmTempfield2(String rasmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = rasmTempfield2;
	}

	public String getRasmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setRasmTempfield3(String rasmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = rasmTempfield3;
	}

	public String getRasmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setRasmTempfield4(String rasmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = rasmTempfield4;
	}

	public String getRasmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setRasmTempfield5(String rasmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = rasmTempfield5;
	}

	public String getRasmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setRasmActive(String rasmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = rasmActive;
	}

	public String getRasmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setRasmCreatedby(String rasmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = rasmCreatedby;
	}

	public String getRasmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setRasmCreatedon(String rasmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = rasmCreatedon;
	}

	public String getRasmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setRasmModifiedon(String rasmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = rasmModifiedon;
	}

	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	public String getElementid() {
		return elementid;
	}
}

