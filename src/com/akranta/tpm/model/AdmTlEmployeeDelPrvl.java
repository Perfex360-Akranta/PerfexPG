package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class AdmTlEmployeeDelPrvl {

	private  Object [] saveArray = null;  
    private List<AdmTlEmployeeDelPrvl> methodAdmTlEmployeeDelPrvl;	


	public enum   tableFldConstants
	{
		  keyid,empm_keyid,empname,menunumber,parentnumber
		,active,createdby,createdon,modifiedon
	}

	public AdmTlEmployeeDelPrvl()
	{
		saveArray = new  Object [ 9 ];
		methodAdmTlEmployeeDelPrvl=new ArrayList<AdmTlEmployeeDelPrvl>();
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public List<AdmTlEmployeeDelPrvl> getmethodAdmTlEmployeeDelPrvl() 
	{
		return methodAdmTlEmployeeDelPrvl;
	}
	public void setmethodAdmTlEmployeeDelPrvl(List <AdmTlEmployeeDelPrvl> methodAdmTlEmployeeDelPrvl) {
		this.methodAdmTlEmployeeDelPrvl=methodAdmTlEmployeeDelPrvl;
	}

	public String getAedpKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setAedpKeyid(String aedpKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = aedpKeyid;
	}

	public String getAedpEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setAedpEmpmKeyid(String aedpEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = aedpEmpmKeyid;
	}
	public String getAedpEmpName() {
		return (String) saveArray[ tableFldConstants.empname.ordinal() ];
	}

	public void setAedpEmpName(String aedpEmpName) {
		saveArray[ tableFldConstants.empname.ordinal() ] = aedpEmpName;
	}
	
	
	
	public String getAedpMenunumber() {
		return (String) saveArray[ tableFldConstants.menunumber.ordinal() ];
	}

	public void setAedpMenunumber(String aedpMenunumber) {
		saveArray[ tableFldConstants.menunumber.ordinal() ] = aedpMenunumber;
	}

	public String getAedpParentnumber() {
		return (String) saveArray[ tableFldConstants.parentnumber.ordinal() ];
	}

	public void setAedpParentnumber(String aedpParentnumber) {
		saveArray[ tableFldConstants.parentnumber.ordinal() ] = aedpParentnumber;
	}
	public String getAedpActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setAedpActive(String aedpActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = aedpActive;
	}

	public String getAedpCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setAedpCreatedby(String aedpCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = aedpCreatedby;
	}

	public String getAedpCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setAedpCreatedon(String aedpCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = aedpCreatedon;
	}

	public String getAedpModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setAedpModifiedon(String aedpModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = aedpModifiedon;
	}

}

