package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class SapExternalServiceDtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		extm_keyid, keyid, service_no, service_text, qty, value, uom
		, currency, cost_element,lineno,totalprice,tempfield2, 
		tempfield3,modifiedon, createdon, createdby, active
	}

	public SapExternalServiceDtl()
	{
		saveArray = new  Object [ 17 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getExtdExtmKeyid() {
		return (String) saveArray[ tableFldConstants.extm_keyid.ordinal() ];
	}

	public void setExtdExtmKeyid(String extdExtmKeyid) {
		saveArray[ tableFldConstants.extm_keyid.ordinal() ] = extdExtmKeyid;
	}

	public String getExtdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setExtdKeyid(String extdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = extdKeyid;
	}

	public String getExtdServiceNo() {
		return (String) saveArray[ tableFldConstants.service_no.ordinal() ];
	}

	public void setExtdServiceNo(String extdServiceNo) {
		saveArray[ tableFldConstants.service_no.ordinal() ] = extdServiceNo;
	}

	public String getExtdServiceText() {
		return (String) saveArray[ tableFldConstants.service_text.ordinal() ];
	}

	public void setExtdServiceText(String extdServiceText) {
		saveArray[ tableFldConstants.service_text.ordinal() ] = extdServiceText;
	}

	public String getExtdQty() {
		return (String) saveArray[ tableFldConstants.qty.ordinal() ];
	}

	public void setExtdQty(String extdQty) {
		saveArray[ tableFldConstants.qty.ordinal() ] = extdQty;
	}

	public String getExtdValue() {
		return (String) saveArray[ tableFldConstants.value.ordinal() ];
	}

	public void setExtdValue(String extdValue) {
		saveArray[ tableFldConstants.value.ordinal() ] = extdValue;
	}

	public String getExtdUom() {
		return (String) saveArray[ tableFldConstants.uom.ordinal() ];
	}

	public void setExtdUom(String extdUom) {
		saveArray[ tableFldConstants.uom.ordinal() ] = extdUom;
	}

	public String getExtdCurrency() {
		return (String) saveArray[ tableFldConstants.currency.ordinal() ];
	}

	public void setExtdCurrency(String extdCurrency) {
		saveArray[ tableFldConstants.currency.ordinal() ] = extdCurrency;
	}

	public String getExtdCostElement() {
		return (String) saveArray[ tableFldConstants.cost_element.ordinal() ];
	}

	public void setExtdCostElement(String extdCostElement) {
		saveArray[ tableFldConstants.cost_element.ordinal() ] = extdCostElement;
	}

	public String getExtdLineNo() {
		return (String) saveArray[ tableFldConstants.lineno.ordinal() ];
	}

	public void setExtdLineNo(String extdLineNo) {
		saveArray[ tableFldConstants.lineno.ordinal() ] = extdLineNo;
	}

	public String getExtdTotalPrice() {
		return (String) saveArray[ tableFldConstants.totalprice.ordinal() ];
	}

	public void setExtdTotalPrice(String extdTotalPrice) {
		saveArray[ tableFldConstants.totalprice.ordinal() ] = extdTotalPrice;
	}
	
	public String getExtdTempField2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setExtdTempField2(String extdTempField2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = extdTempField2;
	}
	public String getExtdTempField3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setExtdTempField3(String extdTempField3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = extdTempField3;
	}


	public String getExtdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setExtdModifiedon(String extdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = extdModifiedon;
	}

	public String getExtdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setExtdCreatedon(String extdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = extdCreatedon;
	}

	public String getExtdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setExtdCreatedby(String extdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = extdCreatedby;
	}

	public String getExtdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setExtdActive(String extdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = extdActive;
	}

	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}

}

