package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class JhaTlAuditreportdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, aurm_keyid, slno, orderno,sheetno,sheetname, auditelement, mm, ma, observations
		, remarks, tempfield1, tempfield2, tempfield3, active, createdby
		, createdon, modifiedon
	}

	public JhaTlAuditreportdtl()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getAurdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAurdKeyid(String aurdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = aurdKeyid;
	}

	public String getAurdAurmKeyid() {
		return (String) saveArray[ tableFldConstants.aurm_keyid.ordinal() ];
	}

	public void setAurdAurmKeyid(String aurdAurmKeyid) {
		saveArray[ tableFldConstants.aurm_keyid.ordinal() ] = aurdAurmKeyid;
	}

	public String getAurdSlno() {
		return (String) saveArray[ tableFldConstants.slno.ordinal() ];
	}

	public void setAurdSlno(String aurdSlno) {
		saveArray[ tableFldConstants.slno.ordinal() ] = aurdSlno;
	}

	public String getAurdOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setAurdOrderno(String aurdOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = aurdOrderno;
	}
	
	public String getAurdSheetno() {
		return (String) saveArray[ tableFldConstants.sheetno.ordinal() ];
	}

	public void setAurdSheetno(String aurdSheetno) {
		saveArray[ tableFldConstants.sheetno.ordinal() ] = aurdSheetno;
	}
	
	public String getAurdSheetname() {
		return (String) saveArray[ tableFldConstants.sheetname.ordinal() ];
	}

	public void setAurdSheetname(String aurdSheetname) {
		saveArray[ tableFldConstants.sheetname.ordinal() ] = aurdSheetname;
	}

	public String getAurdAuditelement() {
		return (String) saveArray[ tableFldConstants.auditelement.ordinal() ];
	}

	public void setAurdAuditelement(String aurdAuditelement) {
		saveArray[ tableFldConstants.auditelement.ordinal() ] = aurdAuditelement;
	}

	public String getAurdMm() {
		return (String) saveArray[ tableFldConstants.mm.ordinal() ];
	}

	public void setAurdMm(String aurdMm) {
		saveArray[ tableFldConstants.mm.ordinal() ] = aurdMm;
	}

	public String getAurdMa() {
		return (String) saveArray[ tableFldConstants.ma.ordinal() ];
	}

	public void setAurdMa(String aurdMa) {
		saveArray[ tableFldConstants.ma.ordinal() ] = aurdMa;
	}

	public String getAurdObservations() {
		return (String) saveArray[ tableFldConstants.observations.ordinal() ];
	}

	public void setAurdObservations(String aurdObservations) {
		saveArray[ tableFldConstants.observations.ordinal() ] = aurdObservations;
	}

	public String getAurdRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setAurdRemarks(String aurdRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = aurdRemarks;
	}

	public String getAurdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setAurdTempfield1(String aurdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = aurdTempfield1;
	}

	public String getAurdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setAurdTempfield2(String aurdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = aurdTempfield2;
	}

	public String getAurdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setAurdTempfield3(String aurdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = aurdTempfield3;
	}

	public String getAurdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAurdActive(String aurdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = aurdActive;
	}

	public String getAurdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAurdCreatedby(String aurdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = aurdCreatedby;
	}

	public String getAurdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAurdCreatedon(String aurdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = aurdCreatedon;
	}

	public String getAurdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAurdModifiedon(String aurdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = aurdModifiedon;
	}

}

