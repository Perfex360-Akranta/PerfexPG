package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlTrgCalUnqp {

	private  Object [] saveArray = null;  
	private String isUniquePosition;

	public enum   tableFldConstants
	{
		keyid, etcm_keyid, role_keyid, roledmt, rolejh, dateadd
		,tempfield1, tempfield2, tempfield3, tempfield4,tempfield5, createdby,active
		, createdon, modifiedon
	}

	public EntTlTrgCalUnqp()
	{
		saveArray = new  Object [ 15 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getEtcuKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEtcuKeyid(String etcuKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = etcuKeyid;
	}

	public String getEtcuEtcmKeyid() {
		return (String) saveArray[ tableFldConstants.etcm_keyid.ordinal() ];
	}

	public void setEtcuEtcmKeyid(String etcuEtcmKeyid) {
		saveArray[ tableFldConstants.etcm_keyid.ordinal() ] = etcuEtcmKeyid;
	}

	public String getEtcuRoleKeyid() {
		return (String) saveArray[ tableFldConstants.role_keyid.ordinal() ];
	}

	public void setEtcuRoleKeyid(String etcuRoleKeyid) {
		saveArray[ tableFldConstants.role_keyid.ordinal() ] = etcuRoleKeyid;
	}

	public String getEtcuRoleDmt() {
		return (String) saveArray[ tableFldConstants.roledmt.ordinal() ];
	}

	public void setEtcuRoleDmt(String etcuRoledmt) {
		saveArray[ tableFldConstants.roledmt.ordinal() ] = etcuRoledmt;
	}

	public String getEtcuRoleJh() {
		return (String) saveArray[ tableFldConstants.rolejh.ordinal() ];
	}

	public void setEtcuRoleJh(String etcuRolejh) {
		saveArray[ tableFldConstants.rolejh.ordinal() ] = etcuRolejh;
	}
	public String getEtcuDateAdd() {
		return (String) saveArray[ tableFldConstants.dateadd.ordinal() ];
	}

	public void setEtcuDateAdd(String etcuDateadd) {
		saveArray[ tableFldConstants.dateadd.ordinal() ] = etcuDateadd;
	}
	public String getEtcuTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEtcuTempfield1(String etcuTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = etcuTempfield1;
	}

	public String getEtcuTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEtcuTempfield2(String etcuTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = etcuTempfield2;
	}
	
	
	public String getEtcuTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEtcuTempfield3(String etcuTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = etcuTempfield3;
	}
	
	public String getEtcuTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEtcuTempfield4(String etcuTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = etcuTempfield4;
	}
	
	public String getEtcuTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setEtcuTempfield5(String etcuTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = etcuTempfield5;
	}
	public String getEtcuCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEtcuCreatedby(String etcuCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = etcuCreatedby;
	}

	public String getEtcuActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEtcuActive(String etcuActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = etcuActive;
	}

	public String getEtcuCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEtcuCreatedon(String etcuCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = etcuCreatedon;
	}

	public String getEtcuModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEtcuModifiedon(String etcuModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = etcuModifiedon;
	}
	public void setIsUniquePosition(String isUniquePosition) {
		this.isUniquePosition = isUniquePosition;
	}
	public String getIsUniquePosition() {
		return isUniquePosition;
	}
}

