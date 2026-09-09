package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class KznTlProjectdmaicstatus {

	private  Object [] saveArray = null;  
	String isDelete=null;

	public enum   tableFldConstants
	{
		keyid, kzpm_keyid, stage, verifieddate, verifiedby, remarks, status
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, createdby, active, createdon, modifiedon
	}

	public KznTlProjectdmaicstatus()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getKpdsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKpdsKeyid(String kpdsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kpdsKeyid;
	}

	public String getKpdsKzpmKeyid() {
		return (String) saveArray[ tableFldConstants.kzpm_keyid.ordinal() ];
	}

	public void setKpdsKzpmKeyid(String kpdsKzpmKeyid) {
		saveArray[ tableFldConstants.kzpm_keyid.ordinal() ] = kpdsKzpmKeyid;
	}

	public String getKpdsStage() {
		return (String) saveArray[ tableFldConstants.stage.ordinal() ];
	}

	public void setKpdsStage(String kpdsStage) {
		saveArray[ tableFldConstants.stage.ordinal() ] = kpdsStage;
	}

	public String getKpdsVerifieddate() {
		return (String) saveArray[ tableFldConstants.verifieddate.ordinal() ];
	}

	public void setKpdsVerifieddate(String kpdsVerifieddate) {
		saveArray[ tableFldConstants.verifieddate.ordinal() ] = kpdsVerifieddate;
	}

	public String getKpdsVerifiedby() {
		return (String) saveArray[ tableFldConstants.verifiedby.ordinal() ];
	}

	public void setKpdsVerifiedby(String kpdsVerifiedby) {
		saveArray[ tableFldConstants.verifiedby.ordinal() ] = kpdsVerifiedby;
	}

	public String getKpdsRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setKpdsRemarks(String kpdsRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = kpdsRemarks;
	}

	public String getKpdsStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setKpdsStatus(String kpdsStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = kpdsStatus;
	}

	public String getKpdsTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKpdsTempfield1(String kpdsTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kpdsTempfield1;
	}

	public String getKpdsTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKpdsTempfield2(String kpdsTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kpdsTempfield2;
	}

	public String getKpdsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKpdsTempfield3(String kpdsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kpdsTempfield3;
	}

	public String getKpdsTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKpdsTempfield4(String kpdsTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kpdsTempfield4;
	}

	public String getKpdsTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKpdsTempfield5(String kpdsTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = kpdsTempfield5;
	}

	public String getKpdsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKpdsCreatedby(String kpdsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kpdsCreatedby;
	}

	public String getKpdsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKpdsActive(String kpdsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kpdsActive;
	}

	public String getKpdsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKpdsCreatedon(String kpdsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kpdsCreatedon;
	}

	public String getKpdsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKpdsModifiedon(String kpdsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kpdsModifiedon;
	}

	public String getIsDelete() {		
		// TODO Auto-generated method stub
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
}

