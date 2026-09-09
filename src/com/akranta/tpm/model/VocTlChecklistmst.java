package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class VocTlChecklistmst {
 
	private VocTlChecklistdtl voctlchecklistdtl;
	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, flid, elementid, title, fromdate, todate, tempfield3
		, active, createdby, createdon, modifiedon
	}

	public VocTlChecklistmst()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getVchmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setVchmKeyid(String vchmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = vchmKeyid;
	}

	public String getVchmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setVchmFlid(String vchmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = vchmFlid;
	}

	public String getVchmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setVchmElementid(String vchmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = vchmElementid;
	}

	public String getVchmTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setVchmTitle(String vchmTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = vchmTitle;
	}

	public String getVchmFromdate() {
		return (String) saveArray[ tableFldConstants.fromdate.ordinal() ];
	}

	public void setVchmFromdate(String vchmFromdate) {
		saveArray[ tableFldConstants.fromdate.ordinal() ] = vchmFromdate;
	}

	public String getVchmTodate() {
		return (String) saveArray[ tableFldConstants.todate.ordinal() ];
	}

	public void setVchmTodate(String vchmTodate) {
		saveArray[ tableFldConstants.todate.ordinal() ] = vchmTodate;
	}

	public String getVchmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setVchmTempfield3(String vchmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = vchmTempfield3;
	}

	public String getVchmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setVchmActive(String vchmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = vchmActive;
	}

	public String getVchmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setVchmCreatedby(String vchmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = vchmCreatedby;
	}

	public String getVchmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setVchmCreatedon(String vchmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = vchmCreatedon;
	}

	public String getVchmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setVchmModifiedon(String vchmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = vchmModifiedon;
	}

	public VocTlChecklistdtl setVoctlchecklistdtl(VocTlChecklistdtl voctlchecklistdtl) {
		this.voctlchecklistdtl = voctlchecklistdtl;
		return voctlchecklistdtl;
	}

	public VocTlChecklistdtl getVoctlchecklistdtl() {
		return voctlchecklistdtl;
	}

	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		 this.saveArray = saveArray;
	}

}

