package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlEmpmanagermstModel {

	private  Object [] saveArray = null;  
	private List <EntTlEmpmanagerdtl> methodentTlEmpmanagerdtllist;
	private EntTlEmpmanagerdtl entTlEmpmanagerdtl;

	public enum   tableFldConstants
	{
		keyid, manager_id, mailid, mobileno, tempfiled1, tempfiled2, tempfiled3
		, active, createdby, createdon, modifiedon
	}

	public EntTlEmpmanagermstModel()
	{
		saveArray = new  Object [ 11 ];
		methodentTlEmpmanagerdtllist = new ArrayList<EntTlEmpmanagerdtl>();
	}
	public void setEntTlEmpmanagerdtl(EntTlEmpmanagerdtl entTlEmpmanagerdtl) {
		this.entTlEmpmanagerdtl = entTlEmpmanagerdtl;
	}

	public EntTlEmpmanagerdtl getEntTlEmpmanagerdtl() {
		return entTlEmpmanagerdtl;
	}
	public List<EntTlEmpmanagerdtl> getmethodentTlEmpmanagerdtllist() 
	{
		
		return methodentTlEmpmanagerdtllist;
		
	}
	public void setmethodentTlEmpmanagerdtllist(List <EntTlEmpmanagerdtl> methodentTlEmpmanagerdtllist) {
		this.methodentTlEmpmanagerdtllist=methodentTlEmpmanagerdtllist;
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEemmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEemmKeyid(String eemmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = eemmKeyid;
	}

	public String getEemmManagerId() {
		return (String) saveArray[ tableFldConstants.manager_id.ordinal() ];
	}

	public void setEemmManagerId(String eemmManagerId) {
		saveArray[ tableFldConstants.manager_id.ordinal() ] = eemmManagerId;
	}

	public String getEemmMailid() {
		return (String) saveArray[ tableFldConstants.mailid.ordinal() ];
	}

	public void setEemmMailid(String eemmMailid) {
		saveArray[ tableFldConstants.mailid.ordinal() ] = eemmMailid;
	}

	public String getEemmMobileno() {
		return (String) saveArray[ tableFldConstants.mobileno.ordinal() ];
	}

	public void setEemmMobileno(String eemmMobileno) {
		saveArray[ tableFldConstants.mobileno.ordinal() ] = eemmMobileno;
	}

	public String getEemmTempfiled1() {
		return (String) saveArray[ tableFldConstants.tempfiled1.ordinal() ];
	}

	public void setEemmTempfiled1(String eemmTempfiled1) {
		saveArray[ tableFldConstants.tempfiled1.ordinal() ] = eemmTempfiled1;
	}

	public String getEemmTempfiled2() {
		return (String) saveArray[ tableFldConstants.tempfiled2.ordinal() ];
	}

	public void setEemmTempfiled2(String eemmTempfiled2) {
		saveArray[ tableFldConstants.tempfiled2.ordinal() ] = eemmTempfiled2;
	}

	public String getEemmTempfiled3() {
		return (String) saveArray[ tableFldConstants.tempfiled3.ordinal() ];
	}

	public void setEemmTempfiled3(String eemmTempfiled3) {
		saveArray[ tableFldConstants.tempfiled3.ordinal() ] = eemmTempfiled3;
	}

	public String getEemmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEemmActive(String eemmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = eemmActive;
	}

	public String getEemmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEemmCreatedby(String eemmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = eemmCreatedby;
	}

	public String getEemmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEemmCreatedon(String eemmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = eemmCreatedon;
	}

	public String getEemmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEemmModifiedon(String eemmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = eemmModifiedon;
	}

}

