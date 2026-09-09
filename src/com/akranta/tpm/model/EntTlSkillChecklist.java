package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlSkillChecklist {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, skil_keyid, name, orderno, remarks, effective_date, inactive_date
		,skrm_keyid,   tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public EntTlSkillChecklist()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getChekKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setChekKeyid(String chekKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = chekKeyid;
	}

	public String getChekSkilKeyid() {
		return (String) saveArray[ tableFldConstants.skil_keyid.ordinal() ];
	}

	public void setChekSkilKeyid(String chekSkilKeyid) {
		saveArray[ tableFldConstants.skil_keyid.ordinal() ] = chekSkilKeyid;
	}

	public String getChekName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setChekName(String chekName) {
		saveArray[ tableFldConstants.name.ordinal() ] = chekName;
	}

	public String getChekOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setChekOrderno(String chekOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = chekOrderno;
	}

	public String getChekRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setChekRemarks(String chekRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = chekRemarks;
	}

	public String getChekEffectiveDate() {
		return (String) saveArray[ tableFldConstants.effective_date.ordinal() ];
	}

	public void setChekEffectiveDate(String chekEffectiveDate) {
		saveArray[ tableFldConstants.effective_date.ordinal() ] = chekEffectiveDate;
	}

	public String getChekInactiveDate() {
		return (String) saveArray[ tableFldConstants.inactive_date.ordinal() ];
	}

	public void setChekInactiveDate(String chekInactiveDate) {
		saveArray[ tableFldConstants.inactive_date.ordinal() ] = chekInactiveDate;
	}

	public String getChekSkrmKeyid() {
		return (String) saveArray[ tableFldConstants.skrm_keyid.ordinal() ];
	}

	public void setChekSkrmKeyid(String chekSkrmKeyid) {
		saveArray[ tableFldConstants.skrm_keyid.ordinal() ] = chekSkrmKeyid;
	}
	


	public String getChekTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setChekTempfield2(String chekTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = chekTempfield2;
	}

	public String getChekTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setChekTempfield3(String chekTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = chekTempfield3;
	}

	public String getChekTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setChekTempfield4(String chekTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = chekTempfield4;
	}

	public String getChekTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setChekTempfield5(String chekTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = chekTempfield5;
	}

	public String getChekActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setChekActive(String chekActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = chekActive;
	}

	public String getChekCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setChekCreatedby(String chekCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = chekCreatedby;
	}

	public String getChekCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setChekCreatedon(String chekCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = chekCreatedon;
	}

	public String getChekModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setChekModifiedon(String chekModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = chekModifiedon;
	}

	public void setSaveArray(Object [] saveArray) {
		this.saveArray = saveArray;
	}

}

