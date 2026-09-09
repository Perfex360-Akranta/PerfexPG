package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTaskMappingTopicmst {

	private  Object [] saveArray = null;  
	private List<EntTlTaskMappingTopicdtl> TaskTopicDetails;

	public enum   tableFldConstants
	{
		keyid, flid, role_keyid, topi_keyid, skrm_keyid, ksa, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, tempfield6
		, cutoffmark, active, createdby, createdon, modifiedon
	}

	public EntTlTaskMappingTopicmst()
	{
		setTaskTopicDetails(new ArrayList<EntTlTaskMappingTopicdtl> ());
		saveArray = new  Object [ 17 ];
	}
	

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTmtmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTmtmKeyid(String tmtmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tmtmKeyid;
	}

	public String getTmtmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setTmtmFlid(String tmtmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = tmtmFlid;
	}

	public String getTmtmRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setTmtmRoleKeyid(String tmtmRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = tmtmRoleKeyid;
	}

	public String getTmtmTopiKeyid() {
		return (String) saveArray[ tableFldConstants.topi_keyid.ordinal() ];
	}

	public void setTmtmTopiKeyid(String tmtmTopiKeyid) {
		saveArray[ tableFldConstants.topi_keyid.ordinal() ] = tmtmTopiKeyid;
	}

	public String getTmtmSkrmKeyid() {
		return (String) saveArray[ tableFldConstants.skrm_keyid.ordinal() ];
	}

	public void setTmtmSkrmKeyid(String tmtmSkrmKeyid) {
		saveArray[ tableFldConstants.skrm_keyid.ordinal() ] = tmtmSkrmKeyid;
	}

	public String getTmtmKsa() {
		return (String) saveArray[ tableFldConstants.ksa.ordinal() ];
	}

	public void setTmtmKsa(String tmtmKsa) {
		saveArray[ tableFldConstants.ksa.ordinal() ] = tmtmKsa;
	}

	public String getTmtmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setTmtmTempfield1(String tmtmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = tmtmTempfield1;
	}

	public String getTmtmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTmtmTempfield2(String tmtmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tmtmTempfield2;
	}

	public String getTmtmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTmtmTempfield3(String tmtmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = tmtmTempfield3;
	}

	public String getTmtmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setTmtmTempfield4(String tmtmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = tmtmTempfield4;
	}

	public String getTmtmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setTmtmTempfield5(String tmtmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = tmtmTempfield5;
	}

	public String getTmtmTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setTmtmTempfield6(String tmtmTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = tmtmTempfield6;
	}

	public String getTmtmCutoffmark() {
		return (String) saveArray[ tableFldConstants.cutoffmark.ordinal() ];
	}

	public void setTmtmCutoffmark(String tmtmCutoffmark) {
		saveArray[ tableFldConstants.cutoffmark.ordinal() ] = tmtmCutoffmark;
	}

	public String getTmtmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTmtmActive(String tmtmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = tmtmActive;
	}

	public String getTmtmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTmtmCreatedby(String tmtmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tmtmCreatedby;
	}

	public String getTmtmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTmtmCreatedon(String tmtmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tmtmCreatedon;
	}

	public String getTmtmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTmtmModifiedon(String tmtmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tmtmModifiedon;
	}
	public void setTaskTopicDetails(List<EntTlTaskMappingTopicdtl> taskGridList1) {
		this.TaskTopicDetails = taskGridList1;
		
	}

	public List<EntTlTaskMappingTopicdtl> getTaskTopicDetails() {
		return TaskTopicDetails;
	}


	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
		
	}
	
}

