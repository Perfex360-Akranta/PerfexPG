package com.akranta.tpm.model;

import java.util.List;

public class MOCReccommendation {

	private  Object [] saveArray = null;  
//	private GenTlActionplandtl isActionPlanDetail;
    private List<GenTlActionplanmst> ActionPlanmst;
	public enum   tableFldConstants
	{
		keyid, masterkeyid,mocid,category,reccommend,responsibility,targetDate,status,completeDate, actionplanid
		,tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public MOCReccommendation()
	{
		saveArray = new  Object [ 19 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getMocrKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMocrKeyid(String mocrKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = mocrKeyid;
	}

	public String getMocrmasterid() {
		return (String) saveArray[ tableFldConstants.masterkeyid.ordinal() ];
	}

	public void setMocrmasterid(String mocrmasterid) {
		saveArray[ tableFldConstants.masterkeyid.ordinal() ] = mocrmasterid;
	}

	public String getMocrMocid() {
		return (String) saveArray[ tableFldConstants.mocid.ordinal() ];
	}

	public void setMocrMocid(String mocrMocid) {
		saveArray[ tableFldConstants.mocid.ordinal() ] = mocrMocid;
	}

	public String getMocrCategory() {
		return (String) saveArray[ tableFldConstants.category.ordinal() ];
	}

	public void setMocrCategory(String mocrCategory) {
		saveArray[ tableFldConstants.category.ordinal() ] = mocrCategory;
	}
	

	public String getMocrrecmnd() {
		return (String) saveArray[ tableFldConstants.reccommend.ordinal() ];
	}

	public void setMocrrecmnd(String mocrrecmnd) {
		saveArray[ tableFldConstants.reccommend.ordinal() ] = mocrrecmnd;
	}


	public String getMocrResponsibility() {
		return (String) saveArray[ tableFldConstants.responsibility.ordinal() ];
	}

	public void setMocrResponsibility(String mocrResponsibility) {
		saveArray[ tableFldConstants.responsibility.ordinal() ] = mocrResponsibility;
	}

	public String getMocrTargetDate() {
		return (String) saveArray[ tableFldConstants.targetDate.ordinal() ];
	}

	public void setMocrTargetDate(String mocrTargetDate) {
		saveArray[ tableFldConstants.targetDate.ordinal() ] = mocrTargetDate;
	}

	
	public String getMocrStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setMocrStatus(String mocrStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = mocrStatus;
	}
	public String getMocrCompleteDate() {
		return (String) saveArray[ tableFldConstants.completeDate.ordinal() ];
	}

	public void setMocrCompleteDate(String mocrCompleteDate) {
		saveArray[ tableFldConstants.completeDate.ordinal() ] = mocrCompleteDate;
	}
	
	public String getMocrActionplanId() {
		return (String) saveArray[ tableFldConstants.actionplanid.ordinal() ];
	}

	public void setMocrActionplanId(String mocrActionplanId) {
		saveArray[ tableFldConstants.actionplanid.ordinal() ] = mocrActionplanId;
	}
	
	public String getMocrTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMocrTempfield1(String mocrTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = mocrTempfield1;
	}

	public String getMocrTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMocrTempfield2(String mocrTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = mocrTempfield2;
	}

	public String getMocrTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMocrTempfield3(String mocrTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = mocrTempfield3;
	}

	public String getMocrTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMocrTempfield4(String mocrTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = mocrTempfield4;
	}

	public String getMocrTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMocrTempfield5(String mocrTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = mocrTempfield5;
	}

	public String getMocrActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMocrActive(String mocrActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = mocrActive;
	}

	public String getMocrCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMocrCreatedby(String mocrCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = mocrCreatedby;
	}

	public String getMocrCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMocrCreatedon(String mocrCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = mocrCreatedon;
	}

	public String getMocrModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMocrModifiedon(String mocrModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = mocrModifiedon;
	}


public List<GenTlActionplanmst> getActionPlanmst() {
	return ActionPlanmst;
}
public void setActionPlanmst(List<GenTlActionplanmst> ActionPlanmst){
this.ActionPlanmst= ActionPlanmst;
}
}

