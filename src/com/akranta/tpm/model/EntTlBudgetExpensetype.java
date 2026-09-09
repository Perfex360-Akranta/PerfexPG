package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlBudgetExpensetype {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, description, code, tempfield1, tempfield2, tempfield3
		, active, createdby, createdon, modifiedon
	}

	public EntTlBudgetExpensetype()
	{
		saveArray = new  Object [ 10 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEbetKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEbetKeyid(String ebetKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = ebetKeyid;
	}

	public String getEbetDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setEbetDescription(String ebetDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = ebetDescription;
	}

	public String getEbetCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setEbetCode(String ebetCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = ebetCode;
	}

	public String getEbetTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEbetTempfield1(String ebetTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = ebetTempfield1;
	}

	public String getEbetTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEbetTempfield2(String ebetTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = ebetTempfield2;
	}

	public String getEbetTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEbetTempfield3(String ebetTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = ebetTempfield3;
	}

	public String getEbetActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEbetActive(String ebetActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = ebetActive;
	}

	public String getEbetCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEbetCreatedby(String ebetCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = ebetCreatedby;
	}

	public String getEbetCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEbetCreatedon(String ebetCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = ebetCreatedon;
	}

	public String getEbetModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEbetModifiedon(String ebetModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = ebetModifiedon;
	}

}

