package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PlmTlSparecostactual {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		pmcalendarid, sitreference, sparesid, quantity, rate, value, refdocno
		, doctype, requestedby, date, tempfield1, tempfield2, createdby
		, createdon, modifiedon
	}

	public PlmTlSparecostactual()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPscaPmcalendarid() {
		return (String) saveArray[ tableFldConstants.pmcalendarid.ordinal() ];
	}

	public void setPscaPmcalendarid(String pscaPmcalendarid) {
		saveArray[ tableFldConstants.pmcalendarid.ordinal() ] = pscaPmcalendarid;
	}

	public String getPscaSitreference() {
		return (String) saveArray[ tableFldConstants.sitreference.ordinal() ];
	}

	public void setPscaSitreference(String pscaSitreference) {
		saveArray[ tableFldConstants.sitreference.ordinal() ] = pscaSitreference;
	}

	public String getPscaSparesid() {
		return (String) saveArray[ tableFldConstants.sparesid.ordinal() ];
	}

	public void setPscaSparesid(String pscaSparesid) {
		saveArray[ tableFldConstants.sparesid.ordinal() ] = pscaSparesid;
	}

	public String getPscaQuantity() {
		return (String) saveArray[ tableFldConstants.quantity.ordinal() ];
	}

	public void setPscaQuantity(String pscaQuantity) {
		saveArray[ tableFldConstants.quantity.ordinal() ] = pscaQuantity;
	}

	public String getPscaRate() {
		return (String) saveArray[ tableFldConstants.rate.ordinal() ];
	}

	public void setPscaRate(String pscaRate) {
		saveArray[ tableFldConstants.rate.ordinal() ] = pscaRate;
	}

	public String getPscaValue() {
		return (String) saveArray[ tableFldConstants.value.ordinal() ];
	}

	public void setPscaValue(String pscaValue) {
		saveArray[ tableFldConstants.value.ordinal() ] = pscaValue;
	}

	public String getPscaRefdocno() {
		return (String) saveArray[ tableFldConstants.refdocno.ordinal() ];
	}

	public void setPscaRefdocno(String pscaRefdocno) {
		saveArray[ tableFldConstants.refdocno.ordinal() ] = pscaRefdocno;
	}

	public String getPscaDoctype() {
		return (String) saveArray[ tableFldConstants.doctype.ordinal() ];
	}

	public void setPscaDoctype(String pscaDoctype) {
		saveArray[ tableFldConstants.doctype.ordinal() ] = pscaDoctype;
	}

	public String getPscaRequestedby() {
		return (String) saveArray[ tableFldConstants.requestedby.ordinal() ];
	}

	public void setPscaRequestedby(String pscaRequestedby) {
		saveArray[ tableFldConstants.requestedby.ordinal() ] = pscaRequestedby;
	}

	public String getPscaDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setPscaDate(String pscaDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = pscaDate;
	}

	public String getPscaTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPscaTempfield1(String pscaTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = pscaTempfield1;
	}

	public String getPscaTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPscaTempfield2(String pscaTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = pscaTempfield2;
	}

	public String getPscaCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPscaCreatedby(String pscaCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pscaCreatedby;
	}

	public String getPscaCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPscaCreatedon(String pscaCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pscaCreatedon;
	}

	public String getPscaModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPscaModifiedon(String pscaModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pscaModifiedon;
	}

}

