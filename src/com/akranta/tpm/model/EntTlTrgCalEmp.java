package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTrgCalEmp {

	private  Object [] saveArray = null;  
    private List<EntTlTrgCalEmp> methodEntTlTrgCalEmp;	

	public enum   tableFldConstants
	{
		keyid,etcm_keyid,etcs_keyid,empm_keyid,dateadd,role_keyid,roledmt,rolejh
		,tempfield1, tempfield2, tempfield3, tempfield4,tempfield5, createdby,active
		, createdon, modifiedon
	}

	public EntTlTrgCalEmp()
	{
		saveArray = new  Object [ 17 ];
		methodEntTlTrgCalEmp=new ArrayList<EntTlTrgCalEmp>();
	}
	
	public List<EntTlTrgCalEmp> getmethodEntTlTrgCalEmp() 
	{
		return methodEntTlTrgCalEmp;
	}
	public void setmethodEntTlTrgCalEmp(List <EntTlTrgCalEmp> methodEntTlTrgCalEmp) {
		this.methodEntTlTrgCalEmp=methodEntTlTrgCalEmp;
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getEtceKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEtceKeyid(String etceKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = etceKeyid;
	}

	public String getEtceEtcmKeyid() {
		return (String) saveArray[ tableFldConstants.etcm_keyid.ordinal() ];
	}

	public void setEtceEtcmKeyid(String etceEtcmKeyid) {
		saveArray[ tableFldConstants.etcm_keyid.ordinal() ] = etceEtcmKeyid;
	}
	public String getEtceEtcsKeyid() {
		return (String) saveArray[ tableFldConstants.etcs_keyid.ordinal() ];
	}

	public void setEtceEtcsKeyid(String etceEtcsKeyid) {
		saveArray[ tableFldConstants.etcs_keyid.ordinal() ] = etceEtcsKeyid;
	}

	public String getEtceEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setEtceEmpmKeyid(String etceEmpmkeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = etceEmpmkeyid;
	}

	public String getEtceDateAdd() {
		return (String) saveArray[ tableFldConstants.dateadd.ordinal() ];
	}

	public void setEtceDateAdd(String etceDateAdd) {
		saveArray[ tableFldConstants.dateadd.ordinal() ] = etceDateAdd;
	}

	public String getEtceRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setEtceRoleKeyid(String etceRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = etceRoleKeyid;
	}
	public String getEtceRoleDmt() {
		return (String) saveArray[ tableFldConstants.roledmt.ordinal() ];
	}

	public void setEtceRoleDmt(String etceRoleDmt) {
		saveArray[ tableFldConstants.roledmt.ordinal() ] = etceRoleDmt;
	}
	public String getEtceRoleJh() {
		return (String) saveArray[ tableFldConstants.rolejh.ordinal() ];
	}

	public void setEtceRoleJh(String etceRoleJh) {
		saveArray[ tableFldConstants.rolejh.ordinal() ] = etceRoleJh;
	}

	public String getEtceTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEtceTempfield1(String etceTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = etceTempfield1;
	}

	public String getEtceTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEtceTempfield2(String etceTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = etceTempfield2;
	}
	
	
	public String getEtceTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEtceTempfield3(String etceTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = etceTempfield3;
	}
	
	public String getEtceTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEtceTempfield4(String etceTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = etceTempfield4;
	}
	
	public String getEtceTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setEtceTempfield5(String etceTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = etceTempfield5;
	}
     
	public String getEtceCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEtceCreatedby(String etceCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = etceCreatedby;
	}
	

	public String getEtceActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEtceActive(String etceActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = etceActive;
	}

	
	public String getEtceCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEtceCreatedon(String etceCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = etceCreatedon;
	}

	public String getEtceModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEtceModifiedon(String etceModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = etceModifiedon;
	}

}

