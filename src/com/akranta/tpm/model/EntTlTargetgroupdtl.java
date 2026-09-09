package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTargetgroupdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, tgtm_keyid, empid, uniquepositionid, tempfield1, tempfield2
		, tempfield3, active, createdby, createdon, modifiedon
	}

	public EntTlTargetgroupdtl()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTgtdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTgtdKeyid(String tgtdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tgtdKeyid;
	}

	public String getTgtdTgtmKeyid() {
		return (String) saveArray[ tableFldConstants.tgtm_keyid.ordinal() ];
	}

	public void setTgtdTgtmKeyid(String tgtdTgtmKeyid) {
		saveArray[ tableFldConstants.tgtm_keyid.ordinal() ] = tgtdTgtmKeyid;
	}

	public String getTgtdEmpid() {
		return (String) saveArray[ tableFldConstants.empid.ordinal() ];
	}

	public void setTgtdEmpid(String tgtdEmpid) {
		saveArray[ tableFldConstants.empid.ordinal() ] = tgtdEmpid;
	}

	public String getTgtdUniquepositionid() {
		return (String) saveArray[ tableFldConstants.uniquepositionid.ordinal() ];
	}

	public void setTgtdUniquepositionid(String tgtdUniquepositionid) {
		saveArray[ tableFldConstants.uniquepositionid.ordinal() ] = tgtdUniquepositionid;
	}

	public String getTgtdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setTgtdTempfield1(String tgtdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = tgtdTempfield1;
	}

	public String getTgtdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTgtdTempfield2(String tgtdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tgtdTempfield2;
	}

	public String getTgtdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTgtdTempfield3(String tgtdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = tgtdTempfield3;
	}

	public String getTgtdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTgtdActive(String tgtdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = tgtdActive;
	}

	public String getTgtdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTgtdCreatedby(String tgtdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tgtdCreatedby;
	}

	public String getTgtdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTgtdCreatedon(String tgtdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tgtdCreatedon;
	}

	public String getTgtdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTgtdModifiedon(String tgtdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tgtdModifiedon;
	}
	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		 this.saveArray = saveArray;
	}
}

