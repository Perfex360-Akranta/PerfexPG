package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class JhaTlAuditreportmst {

	private  Object [] saveArray = null;  
	private List<JhaTlAuditreportdtl> jhaTlAuditreportdtl;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, preparedby, uploadedby, uploadedfile
		, tempfield1, tempfield2, tempfield3, active, createdby, createdon
		, modifiedon
	}

	public JhaTlAuditreportmst()
	{
		saveArray = new  Object [ 14 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getAurmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAurmKeyid(String aurmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = aurmKeyid;
	}

	public String getAurmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setAurmFlid(String aurmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = aurmFlid;
	}

	public String getAurmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setAurmElementid(String aurmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = aurmElementid;
	}

	public String getAurmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setAurmDate(String aurmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = aurmDate;
	}

	public String getAurmPreparedby() {
		return (String) saveArray[ tableFldConstants.preparedby.ordinal() ];
	}

	public void setAurmPreparedby(String aurmPreparedby) {
		saveArray[ tableFldConstants.preparedby.ordinal() ] = aurmPreparedby;
	}

	public String getAurmUploadedby() {
		return (String) saveArray[ tableFldConstants.uploadedby.ordinal() ];
	}

	public void setAurmUploadedby(String aurmUploadedby) {
		saveArray[ tableFldConstants.uploadedby.ordinal() ] = aurmUploadedby;
	}

	public String getAurmUploadedfile() {
		return (String) saveArray[ tableFldConstants.uploadedfile.ordinal() ];
	}

	public void setAurmUploadedfile(String aurmUploadedfile) {
		saveArray[ tableFldConstants.uploadedfile.ordinal() ] = aurmUploadedfile;
	}

	public String getAurmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setAurmTempfield1(String aurmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = aurmTempfield1;
	}

	public String getAurmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setAurmTempfield2(String aurmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = aurmTempfield2;
	}

	public String getAurmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setAurmTempfield3(String aurmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = aurmTempfield3;
	}

	public String getAurmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAurmActive(String aurmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = aurmActive;
	}

	public String getAurmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAurmCreatedby(String aurmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = aurmCreatedby;
	}

	public String getAurmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAurmCreatedon(String aurmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = aurmCreatedon;
	}

	public String getAurmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAurmModifiedon(String aurmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = aurmModifiedon;
	}

	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public void setJhaTlAuditreportdtl(List<JhaTlAuditreportdtl> jhaTlAuditreportdtl) {
		this.jhaTlAuditreportdtl = jhaTlAuditreportdtl;
	}

	public List<JhaTlAuditreportdtl> getJhaTlAuditreportdtl() {
		return jhaTlAuditreportdtl;
	}
}

