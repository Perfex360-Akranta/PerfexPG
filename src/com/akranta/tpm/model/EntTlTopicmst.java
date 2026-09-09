package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.sql.TableFieldType;

public class EntTlTopicmst {

	private  Object [] saveArray = null;  
	
	private String trRoleid;
	public enum   tableFldConstants
	{
		keyid, locationid, code, name, parentid, ischild, evaluationtypeid
		, type, remarks, effective_date, inactive_date,spokeid, relatedto
		, trainingmode, category, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public EntTlTopicmst()
	{
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getTopiKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setTopiKeyid(String topiKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = topiKeyid;
	}

	public String getTopiLocationid() {
		return (String) saveArray[ tableFldConstants.locationid.ordinal() ];
	}

	public void setTopiLocationid(String topiLocationid) {
		saveArray[ tableFldConstants.locationid.ordinal() ] = topiLocationid;
	}

	public String getTopiCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setTopiCode(String topiCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = topiCode;
	}

	public String getTopiName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setTopiName(String topiName) {
		saveArray[ tableFldConstants.name.ordinal() ] = topiName;
	}

	public String getTopiParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setTopiParentid(String topiParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = topiParentid;
	}

	public String getTopiIschild() {
		return (String) saveArray[ tableFldConstants.ischild.ordinal() ];
	}

	public void setTopiIschild(String topiIschild) {
		saveArray[ tableFldConstants.ischild.ordinal() ] = topiIschild;
	}

	public String getTopiEvaluationtypeid() {
		return (String) saveArray[ tableFldConstants.evaluationtypeid.ordinal() ];
	}

	public void setTopiEvaluationtypeid(String topiEvaluationtypeid) {
		saveArray[ tableFldConstants.evaluationtypeid.ordinal() ] = topiEvaluationtypeid;
	}

	public String getTopiType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setTopiType(String topiType) {
		saveArray[ tableFldConstants.type.ordinal() ] = topiType;
	}

	public String getTopiRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setTopiRemarks(String topiRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = topiRemarks;
	}

	public String getTopiEffectiveDate() {
		return (String) saveArray[ tableFldConstants.effective_date.ordinal() ];
	}

	public void setTopiEffectiveDate(String topiEffectiveDate) {
		saveArray[ tableFldConstants.effective_date.ordinal() ] = topiEffectiveDate;
	}

	public String getTopiInactiveDate() {
		return (String) saveArray[ tableFldConstants.inactive_date.ordinal() ];
	}

	public void setTopiInactiveDate(String topiInactiveDate) {
		saveArray[ tableFldConstants.inactive_date.ordinal() ] = topiInactiveDate;
	}

	public String getTopiSpokeid() {
		return (String) saveArray[ tableFldConstants.spokeid.ordinal() ];
	}

	public void setTopiSpokeid(String topiSpokeid) {
		saveArray[ tableFldConstants.spokeid.ordinal() ] = topiSpokeid;
	}

	public String getTopiRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setTopiRelatedto(String topiRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = topiRelatedto;
	}

	public String getTopiTrainingmode() {
		return (String) saveArray[ tableFldConstants.trainingmode.ordinal() ];
	}

	public void setTopiTrainingmode(String topitrainingmode) {
		saveArray[ tableFldConstants.trainingmode.ordinal() ] = topitrainingmode;
	}

	public String getTopiCategory() {
		return (String) saveArray[ tableFldConstants.category.ordinal() ];
	}

	public void setTopiCategory(String topiCategory) {
		saveArray[ tableFldConstants.category.ordinal() ] =topiCategory;
	}

	public String getTopiTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setTopiTempfield5(String topiTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = topiTempfield5;
	}

	public String getTopiActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setTopiActive(String topiActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = topiActive;
	}

	public String getTopiCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setTopiCreatedby(String topiCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = topiCreatedby;
	}

	public String getTopiCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setTopiCreatedon(String topiCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = topiCreatedon;
	}

	public String getTopiModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setTopiModifiedon(String topiModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = topiModifiedon;
	}

	public void setTrRoleid(String trRoleid) {
		this.trRoleid = trRoleid;
	}

	public String getTrRoleid() {
		return trRoleid;
	}

	

}

