package com.akranta.tpm.model;

import java.util.List;

public class MOCPssrReccommend {

	private  Object [] saveArray = null;  
	private List<MOCPssrReccommend> mOCPssrReccommendList;
    private List<GenTlActionplanmst> ActionPlanmst;
	public enum   tableFldConstants
	{
		keyid, masterkeyid,reccommend, category, responsibility,targetDate,status,completeDate, actionplanid
		,tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public MOCPssrReccommend()
	{
		saveArray = new  Object [ 18 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	public List<MOCPssrReccommend> getPSSRDetails() {
		return mOCPssrReccommendList;
	}

	public void setPSSRDetails(List<MOCPssrReccommend> mOCPssrReccommendList) {
		this.mOCPssrReccommendList = mOCPssrReccommendList;
	}
	public List<GenTlActionplanmst> getActionPlanmst() {
		return ActionPlanmst;
	}
	
	public void setActionPlanmst(List<GenTlActionplanmst> ActionPlanmst){
	this.ActionPlanmst= ActionPlanmst;
	}
	
	public String getPsrrKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPsrrKeyid(String psrrKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = psrrKeyid;
	}

	public String getPsrrmasterid() {
		return (String) saveArray[ tableFldConstants.masterkeyid.ordinal() ];
	}

	public void setPsrrmasterid(String psrrmasterid) {
		saveArray[ tableFldConstants.masterkeyid.ordinal() ] = psrrmasterid;
	}

	

	public String getPsrrrecmnd() {
		return (String) saveArray[ tableFldConstants.reccommend.ordinal() ];
	}

	public void setPsrrrecmnd(String psrrrecmnd) {
		saveArray[ tableFldConstants.reccommend.ordinal() ] = psrrrecmnd;
	}
	public String getPsrrCategory() {
		return (String) saveArray[ tableFldConstants.category.ordinal() ];
	}

	public void setPsrrCategory(String psrrCategory) {
		saveArray[ tableFldConstants.category.ordinal() ] = psrrCategory;
	}
	

	public String getPssrResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setPssrResponsibility(String pssrResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = pssrResponsibility;
	}

	public String getPsrrTargetDate() {
		return (String) saveArray[ tableFldConstants.targetDate.ordinal() ];
	}

	public void setPsrrTargetDate(String psrrTargetDate) {
		saveArray[ tableFldConstants.targetDate.ordinal() ] = psrrTargetDate;
	}

	
	public String getPsrrStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setPsrrStatus(String psrrStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = psrrStatus;
	}
	public String getPsrrCompleteDate() {
		return (String) saveArray[ tableFldConstants.completeDate.ordinal() ];
	}

	public void setPsrrCompleteDate(String psrrCompleteDate) {
		saveArray[ tableFldConstants.completeDate.ordinal() ] = psrrCompleteDate;
	}
	
	public String getPsrrActionplanId() {
		return (String) saveArray[ tableFldConstants.actionplanid.ordinal() ];
	}

	public void setPsrrActionplanId(String psrrActionplanId) {
		saveArray[ tableFldConstants.actionplanid.ordinal() ] = psrrActionplanId;
	}
	
	public String getPsrrTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setPsrrTempfield1(String psrrTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = psrrTempfield1;
	}

	public String getPsrrTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPsrrTempfield2(String psrrTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = psrrTempfield2;
	}

	public String getPsrrTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPsrrTempfield3(String psrrTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = psrrTempfield3;
	}

	public String getPsrrTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setPsrrTempfield4(String psrrTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = psrrTempfield4;
	}

	public String getPsrrTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setPsrrTempfield5(String psrrTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = psrrTempfield5;
	}

	public String getPsrrActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPsrrActive(String psrrActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = psrrActive;
	}

	public String getPsrrCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPsrrCreatedby(String psrrCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = psrrCreatedby;
	}

	public String getPsrrCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPsrrCreatedon(String psrrCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = psrrCreatedon;
	}

	public String getPsrrModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPsrrModifiedon(String psrrModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = psrrModifiedon;
	}
}

