package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class GenTlResponsibilitylink {

	private  Object [] saveArray = null;  
	
	
private List<GenTlResponsibilitylink> resposiablelinkDtl=null;
private String flag;
private GenTlActplnNonemployee genTlActplnNonemployee;
	public enum   tableFldConstants
	{
		keyid, refdocid, refdoctype, employeeid, targetdate, status, completedon
		, tempfiled1, tempfiled2, tempfiled3, tempfiled4, tempfiled5
		, active, createdby, createdon, modifiedon
	}

	public GenTlResponsibilitylink()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getRsplKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setRsplKeyid(String rsplKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = rsplKeyid;
	}

	public String getRsplRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setRsplRefdocid(String rsplRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = rsplRefdocid;
	}

	public String getRsplRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setRsplRefdoctype(String rsplRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = rsplRefdoctype;
	}

	public String getRsplEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setRsplEmployeeid(String rsplEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = rsplEmployeeid;
	}

	public String getRsplTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setRsplTargetdate(String rsplTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = rsplTargetdate;
	}

	public String getRsplStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setRsplStatus(String rsplStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = rsplStatus;
	}

	public String getRsplCompletedon() {
		return (String) saveArray[ tableFldConstants.completedon.ordinal() ];
	}

	public void setRsplCompletedon(String rsplCompletedon) {
		saveArray[ tableFldConstants.completedon.ordinal() ] = rsplCompletedon;
	}

	public String getRsplTempfiled1() {
		return (String) saveArray[ tableFldConstants.tempfiled1.ordinal() ];
	}

	public void setRsplTempfiled1(String rsplTempfiled1) {
		saveArray[ tableFldConstants.tempfiled1.ordinal() ] = rsplTempfiled1;
	}

	public String getRsplTempfiled2() {
		return (String) saveArray[ tableFldConstants.tempfiled2.ordinal() ];
	}

	public void setRsplTempfiled2(String rsplTempfiled2) {
		saveArray[ tableFldConstants.tempfiled2.ordinal() ] = rsplTempfiled2;
	}

	public String getRsplTempfiled3() {
		return (String) saveArray[ tableFldConstants.tempfiled3.ordinal() ];
	}

	public void setRsplTempfiled3(String rsplTempfiled3) {
		saveArray[ tableFldConstants.tempfiled3.ordinal() ] = rsplTempfiled3;
	}

	public String getRsplTempfiled4() {
		return (String) saveArray[ tableFldConstants.tempfiled4.ordinal() ];
	}

	public void setRsplTempfiled4(String rsplTempfiled4) {
		saveArray[ tableFldConstants.tempfiled4.ordinal() ] = rsplTempfiled4;
	}

	public String getRsplTempfiled5() {
		return (String) saveArray[ tableFldConstants.tempfiled5.ordinal() ];
	}

	public void setRsplTempfiled5(String rsplTempfiled5) {
		saveArray[ tableFldConstants.tempfiled5.ordinal() ] = rsplTempfiled5;
	}

	public String getRsplActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setRsplActive(String rsplActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = rsplActive;
	}

	public String getRsplCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setRsplCreatedby(String rsplCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = rsplCreatedby;
	}

	public String getRsplCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setRsplCreatedon(String rsplCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = rsplCreatedon;
	}

	public String getRsplModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setRsplModifiedon(String rsplModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = rsplModifiedon;
	}

	public void setResposiablelinkDtl(List<GenTlResponsibilitylink> resposiablelinkDtl) {
		this.resposiablelinkDtl = resposiablelinkDtl;
	}

	public List<GenTlResponsibilitylink> getResposiablelinkDtl() {
		return resposiablelinkDtl;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setGenTlActplnNonemployee(GenTlActplnNonemployee genTlActplnNonemployee) {
		this.genTlActplnNonemployee = genTlActplnNonemployee;
	}

	public GenTlActplnNonemployee getGenTlActplnNonemployee() {
		return genTlActplnNonemployee;
	}

}

