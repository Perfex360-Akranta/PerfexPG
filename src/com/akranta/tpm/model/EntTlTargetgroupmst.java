package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTargetgroupmst {

	private List<EntTlTargetgroupdtl> Targetgroupdtl ;
	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, title, flid, tempfield2, tempfield3, active, createdby
		, createdon, modifiedon
	}

	public EntTlTargetgroupmst()
	{
		saveArray = new  Object [ 9 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getTgtmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTgtmKeyid(String tgtmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = tgtmKeyid;
	}

	public String getTgtmTitle() {
		return (String) saveArray[ tableFldConstants.title.ordinal() ];
	}

	public void setTgtmTitle(String tgtmTitle) {
		saveArray[ tableFldConstants.title.ordinal() ] = tgtmTitle;
	}

	public String getTgtmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setTgtmFlid(String tgtmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = tgtmFlid;
	}

	public String getTgtmTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setTgtmTempfield2(String tgtmTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = tgtmTempfield2;
	}

	public String getTgtmTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setTgtmTempfield3(String tgtmTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = tgtmTempfield3;
	}

	public String getTgtmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTgtmActive(String tgtmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = tgtmActive;
	}

	public String getTgtmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTgtmCreatedby(String tgtmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = tgtmCreatedby;
	}

	public String getTgtmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTgtmCreatedon(String tgtmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = tgtmCreatedon;
	}

	public String getTgtmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTgtmModifiedon(String tgtmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = tgtmModifiedon;
	}

	public void setTargetgroupdtl(List<EntTlTargetgroupdtl> targetgroupdtl) {
		Targetgroupdtl = targetgroupdtl;
	}

	public List<EntTlTargetgroupdtl> getTargetgroupdtl() {
		return Targetgroupdtl;
	}
	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		 this.saveArray = saveArray;
	}
}

