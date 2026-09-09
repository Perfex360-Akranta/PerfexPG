package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlDmtnotebookmaster {

	private  Object [] saveArray = null;  
	
	private List<GenTlDmtnotebookdetail> lstGenTlDmtnotebookdetail;
	public enum   tableFldConstants
	{
		keyid, issuesdiscussed, flid, date, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public GenTlDmtnotebookmaster()
	{
		saveArray = new  Object [ 13 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getDmtmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setDmtmKeyid(String dmtmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = dmtmKeyid;
	}

	public String getDmtmIssuesdiscussed() {
		return (String) saveArray[ tableFldConstants.issuesdiscussed.ordinal() ];
	}

	public void setDmtmIssuesdiscussed(String dmtmIssuesdiscussed) {
		saveArray[ tableFldConstants.issuesdiscussed.ordinal() ] = dmtmIssuesdiscussed;
	}

	public String getDmtmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setDmtmFlid(String dmtmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = dmtmFlid;
	}

	public String getDmtmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setDmtmDate(String dmtmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = dmtmDate;
	}

	public String getDmtmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setDmtmTempfield1(String dmtmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = dmtmTempfield1;
	}

	public String getDmtmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setDmtmTempfield2(String dmtmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = dmtmTempfield2;
	}

	public String getDmtmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setDmtmTempfield3(String dmtmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = dmtmTempfield3;
	}

	public String getDmtmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setDmtmTempfield4(String dmtmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = dmtmTempfield4;
	}

	public String getDmtmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setDmtmTempfield5(String dmtmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = dmtmTempfield5;
	}

	public String getDmtmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setDmtmActive(String dmtmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = dmtmActive;
	}

	public String getDmtmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setDmtmCreatedby(String dmtmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = dmtmCreatedby;
	}

	public String getDmtmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setDmtmCreatedon(String dmtmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = dmtmCreatedon;
	}

	public String getDmtmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setDmtmModifiedon(String dmtmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = dmtmModifiedon;
	}

	public void setDmtNotebookdtldata(List<GenTlDmtnotebookdetail> lstGenTlDmtnotebookdetail) {
		// TODO Auto-generated method stub      gentldmtnotebookdetail
		this.lstGenTlDmtnotebookdetail = lstGenTlDmtnotebookdetail;
	}
	public List<GenTlDmtnotebookdetail> getDmtNotebookdtldata() {
		return lstGenTlDmtnotebookdetail;
	}
	public void setSaveArray(Object [] saveArray) {
		 this.saveArray = saveArray;
	}

	

}

