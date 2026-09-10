package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BAL_PlmTlMultipleResp {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, refid, alloted_empid, completed_empid, active
	}

	public BAL_PlmTlMultipleResp()
	{
		saveArray = new  Object [ 5 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPmrsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPmrsKeyid(String pmrsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pmrsKeyid;
	}

	public String getPmrsRefid() {
		return (String) saveArray[ tableFldConstants.refid.ordinal() ];
	}

	public void setPmrsRefid(String pmrsRefid) {
		saveArray[ tableFldConstants.refid.ordinal() ] = pmrsRefid;
	}

	public String getBdrsRespEmpid() {
		return (String) saveArray[ tableFldConstants.alloted_empid.ordinal() ];
	}

	public void setBdrsRespEmpid(String pmrsAllotedEmpid) {
		saveArray[ tableFldConstants.alloted_empid.ordinal() ] = pmrsAllotedEmpid;
	}

	public String getPmrsCompletedEmpid() {
		return (String) saveArray[ tableFldConstants.completed_empid.ordinal() ];
	}

	public void setPmrsCompletedEmpid(String pmrsCompletedEmpid) {
		saveArray[ tableFldConstants.completed_empid.ordinal() ] = pmrsCompletedEmpid;
	}

	public String getPmrsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPmrsActive(String pmrsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pmrsActive;
	}

}

