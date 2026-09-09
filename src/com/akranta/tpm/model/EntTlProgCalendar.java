package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlProgCalendar {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{		
		keyid, prog_id, plan_fromdate, plan_tilldate, plan_month, plan_week
		, plan_audiencecount, requestby, requestdate, requestremarks
		, approvedflag, approvedby, approveddate, approvedremarks, actual_fromdate
		, actual_tilldate, actual_audiencecount, status, monthwise,budget
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public EntTlProgCalendar()
	{
		saveArray = new  Object [ 28 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	public String getEcalKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEcalKeyid(String ecalKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = ecalKeyid;
	}

	public String getEcalProgId() {
		return (String) saveArray[ tableFldConstants.prog_id.ordinal() ];
	}

	public void setEcalProgId(String ecalProgId) {
		saveArray[ tableFldConstants.prog_id.ordinal() ] = ecalProgId;
	}

	public String getEcalPlanFromdate() {
		return (String) saveArray[ tableFldConstants.plan_fromdate.ordinal() ];
	}

	public void setEcalPlanFromdate(String ecalPlanFromdate) {
		saveArray[ tableFldConstants.plan_fromdate.ordinal() ] = ecalPlanFromdate;
	}

	public String getEcalPlanTilldate() {
		return (String) saveArray[ tableFldConstants.plan_tilldate.ordinal() ];
	}

	public void setEcalPlanTilldate(String ecalPlanTilldate) {
		saveArray[ tableFldConstants.plan_tilldate.ordinal() ] = ecalPlanTilldate;
	}

	public String getEcalPlanMonth() {
		return (String) saveArray[ tableFldConstants.plan_month.ordinal() ];
	}

	public void setEcalPlanMonth(String ecalPlanMonth) {
		saveArray[ tableFldConstants.plan_month.ordinal() ] = ecalPlanMonth;
	}

	public String getEcalPlanWeek() {
		return (String) saveArray[ tableFldConstants.plan_week.ordinal() ];
	}

	public void setEcalPlanWeek(String ecalPlanWeek) {
		saveArray[ tableFldConstants.plan_week.ordinal() ] = ecalPlanWeek;
	}

	public String getEcalPlanAudiencecount() {
		return (String) saveArray[ tableFldConstants.plan_audiencecount.ordinal() ];
	}

	public void setEcalPlanAudiencecount(String ecalPlanAudiencecount) {
		saveArray[ tableFldConstants.plan_audiencecount.ordinal() ] = ecalPlanAudiencecount;
	}

	public String getEcalRequestby() {
		return (String) saveArray[ tableFldConstants.requestby.ordinal() ];
	}

	public void setEcalRequestby(String ecalRequestby) {
		saveArray[ tableFldConstants.requestby.ordinal() ] = ecalRequestby;
	}

	public String getEcalRequestdate() {
		return (String) saveArray[ tableFldConstants.requestdate.ordinal() ];
	}

	public void setEcalRequestdate(String ecalRequestdate) {
		saveArray[ tableFldConstants.requestdate.ordinal() ] = ecalRequestdate;
	}

	public String getEcalRequestremarks() {
		return (String) saveArray[ tableFldConstants.requestremarks.ordinal() ];
	}

	public void setEcalRequestremarks(String ecalRequestremarks) {
		saveArray[ tableFldConstants.requestremarks.ordinal() ] = ecalRequestremarks;
	}

	public String getEcalApprovedflag() {
		return (String) saveArray[ tableFldConstants.approvedflag.ordinal() ];
	}

	public void setEcalApprovedflag(String ecalApprovedflag) {
		saveArray[ tableFldConstants.approvedflag.ordinal() ] = ecalApprovedflag;
	}

	public String getEcalApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setEcalApprovedby(String ecalApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = ecalApprovedby;
	}

	public String getEcalApproveddate() {
		return (String) saveArray[ tableFldConstants.approveddate.ordinal() ];
	}

	public void setEcalApproveddate(String ecalApproveddate) {
		saveArray[ tableFldConstants.approveddate.ordinal() ] = ecalApproveddate;
	}

	public String getEcalApprovedremarks() {
		return (String) saveArray[ tableFldConstants.approvedremarks.ordinal() ];
	}

	public void setEcalApprovedremarks(String ecalApprovedremarks) {
		saveArray[ tableFldConstants.approvedremarks.ordinal() ] = ecalApprovedremarks;
	}

	public String getEcalActualFromdate() {
		return (String) saveArray[ tableFldConstants.actual_fromdate.ordinal() ];
	}

	public void setEcalActualFromdate(String ecalActualFromdate) {
		saveArray[ tableFldConstants.actual_fromdate.ordinal() ] = ecalActualFromdate;
	}

	public String getEcalActualTilldate() {
		return (String) saveArray[ tableFldConstants.actual_tilldate.ordinal() ];
	}

	public void setEcalActualTilldate(String ecalActualTilldate) {
		saveArray[ tableFldConstants.actual_tilldate.ordinal() ] = ecalActualTilldate;
	}

	public String getEcalActualAudiencecount() {
		return (String) saveArray[ tableFldConstants.actual_audiencecount.ordinal() ];
	}

	public void setEcalActualAudiencecount(String ecalActualAudiencecount) {
		saveArray[ tableFldConstants.actual_audiencecount.ordinal() ] = ecalActualAudiencecount;
	}

	public String getEcalStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setEcalStatus(String ecalStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = ecalStatus;
	}

	public String getEcalMonthwise() {
		return (String) saveArray[ tableFldConstants.monthwise.ordinal() ];
	}

	public void setEcalMonthwise(String ecalMonthwise) {
		saveArray[ tableFldConstants.monthwise.ordinal() ] = ecalMonthwise;
	}

	public String getEcalBudget() {
		return (String) saveArray[ tableFldConstants.budget.ordinal() ];
	}

	public void setEcalBudget(String ecalBudget) {
		saveArray[ tableFldConstants.budget.ordinal() ] = ecalBudget;
	}	

	public String getEcalTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEcalTempfield2(String ecalTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = ecalTempfield2;
	}

	public String getEcalTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEcalTempfield3(String ecalTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = ecalTempfield3;
	}

	public String getEcalTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEcalTempfield4(String ecalTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = ecalTempfield4;
	}

	public String getEcalTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setEcalTempfield5(String ecalTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = ecalTempfield5;
	}

	public String getEcalActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEcalActive(String ecalActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = ecalActive;
	}

	public String getEcalCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEcalCreatedby(String ecalCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = ecalCreatedby;
	}

	public String getEcalCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEcalCreatedon(String ecalCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = ecalCreatedon;
	}

	public String getEcalModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEcalModifiedon(String ecalModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = ecalModifiedon;
	}

}

