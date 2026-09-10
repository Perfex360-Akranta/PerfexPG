package com.akranta.tpm.model;

import java.util.List;

public class BAL_BdmTlWhywhydtl {

	private  Object [] saveArray = null;  
	//private List<BdmTlWhywhydtl> bdmTlWhywhydtl;
	

	public enum   tableFldConstants
	{
		keyid, wwmsKeyid, slno, why, answer, action, createdby, createdon
		, modifiedon
	}

	public BAL_BdmTlWhywhydtl()
	{
		saveArray = new  Object [ 9 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWwdtKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWwdtKeyid(String wwdtKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = wwdtKeyid;
	}

	public String getWwdtWwmsKeyid() {
		return (String) saveArray[ tableFldConstants.wwmsKeyid.ordinal() ];
	}

	public void setWwdtWwmsKeyid(String wwdtWwmsKeyid) {
		saveArray[ tableFldConstants.wwmsKeyid.ordinal() ] = wwdtWwmsKeyid;
	}

	public String getWwdtSlno() {
		return (String) saveArray[ tableFldConstants.slno.ordinal() ];
	}

	public void setWwdtSlno(String wwdtSlno) {
		saveArray[ tableFldConstants.slno.ordinal() ] = wwdtSlno;
	}

	public String getWwdtWhy() {
		return (String) saveArray[ tableFldConstants.why.ordinal() ];
	}

	public void setWwdtWhy(String wwdtWhy) {
		saveArray[ tableFldConstants.why.ordinal() ] = wwdtWhy;
	}

	public String getWwdtAnswer() {
		return (String) saveArray[ tableFldConstants.answer.ordinal() ];
	}

	public void setWwdtAnswer(String wwdtAnswer) {
		saveArray[ tableFldConstants.answer.ordinal() ] = wwdtAnswer;
	}

	public String getWwdtAction() {
		return (String) saveArray[ tableFldConstants.action.ordinal() ];
	}

	public void setWwdtAction(String wwdtAction) {
		saveArray[ tableFldConstants.action.ordinal() ] = wwdtAction;
	}

	public String getWwdtCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWwdtCreatedby(String wwdtCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wwdtCreatedby;
	}

	public String getWwdtCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWwdtCreatedon(String wwdtCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wwdtCreatedon;
	}

	public String getWwdtModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWwdtModifiedon(String wwdtModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wwdtModifiedon;
	}



}

