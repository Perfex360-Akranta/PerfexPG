package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.bean.WorkOrderDetailsBean;
import com.akranta.tpm.bean.WorkOrderDetailsLstBean;

public class PlmTlWofeedback {

	private  Object [] saveArray = null;  
	private PlmTlSparecostactual spareCostActual;
	private PlmTlSpareconsumed spareConsumed;
    private List<WorkOrderDetailsLstBean> wobean;
	public enum   tableFldConstants
	{
		feedbackid, wodetailid, feedbackdate, machineid, status, action
		, startdate, enddate, completeddate, duration, isprodstopped
		, prodstartdate, currentreading, adjustedreading, uom, whywhyflag
		, whywhyid, amcflag, amcdetailid, spareflag, sparecost, manpowercost
		, contractorcost, othercost, observation, feedback, completedby
		, rescheduleflag, reschedulereason, nextinspectiondate, remarks
		, rootcause, countermeasure, mchcondition,machinetakeovertime, createdby, modifiedon
		, createdon
	}

	public PlmTlWofeedback()
	{
		saveArray = new  Object [ 38 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWofbFeedbackid() {
		return (String) saveArray[ tableFldConstants.feedbackid.ordinal() ];
	}

	public void setWofbFeedbackid(String wofbFeedbackid) {
		saveArray[ tableFldConstants.feedbackid.ordinal() ] = wofbFeedbackid;
	}

	public String getWofbWodetailid() {
		return (String) saveArray[ tableFldConstants.wodetailid.ordinal() ];
	}

	public void setWofbWodetailid(String wofbWodetailid) {
		saveArray[ tableFldConstants.wodetailid.ordinal() ] = wofbWodetailid;
	}

	public String getWofbFeedbackdate() {
		return (String) saveArray[ tableFldConstants.feedbackdate.ordinal() ];
	}

	public void setWofbFeedbackdate(String wofbFeedbackdate) {
		saveArray[ tableFldConstants.feedbackdate.ordinal() ] = wofbFeedbackdate;
	}

	public String getWofbMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setWofbMachineid(String wofbMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = wofbMachineid;
	}

	public String getWofbStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setWofbStatus(String wofbStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = wofbStatus;
	}

	public String getWofbAction() {
		return (String) saveArray[ tableFldConstants.action.ordinal() ];
	}

	public void setWofbAction(String wofbAction) {
		saveArray[ tableFldConstants.action.ordinal() ] = wofbAction;
	}

	public String getWofbStartdate() {
		return (String) saveArray[ tableFldConstants.startdate.ordinal() ];
	}

	public void setWofbStartdate(String wofbStartdate) {
		saveArray[ tableFldConstants.startdate.ordinal() ] = wofbStartdate;
	}

	public String getWofbEnddate() {
		return (String) saveArray[ tableFldConstants.enddate.ordinal() ];
	}

	public void setWofbEnddate(String wofbEnddate) {
		saveArray[ tableFldConstants.enddate.ordinal() ] = wofbEnddate;
	}

	public String getWofbCompleteddate() {
		return (String) saveArray[ tableFldConstants.completeddate.ordinal() ];
	}

	public void setWofbCompleteddate(String wofbCompleteddate) {
		saveArray[ tableFldConstants.completeddate.ordinal() ] = wofbCompleteddate;
	}

	public String getWofbDuration() {
		return (String) saveArray[ tableFldConstants.duration.ordinal() ];
	}

	public void setWofbDuration(String wofbDuration) {
		saveArray[ tableFldConstants.duration.ordinal() ] = wofbDuration;
	}

	public String getWofbIsprodstopped() {
		return (String) saveArray[ tableFldConstants.isprodstopped.ordinal() ];
	}

	public void setWofbIsprodstopped(String wofbIsprodstopped) {
		saveArray[ tableFldConstants.isprodstopped.ordinal() ] = wofbIsprodstopped;
	}

	public String getWofbProdstartdate() {
		return (String) saveArray[ tableFldConstants.prodstartdate.ordinal() ];
	}

	public void setWofbProdstartdate(String wofbProdstartdate) {
		saveArray[ tableFldConstants.prodstartdate.ordinal() ] = wofbProdstartdate;
	}

	public String getWofbCurrentreading() {
		return (String) saveArray[ tableFldConstants.currentreading.ordinal() ];
	}

	public void setWofbCurrentreading(String wofbCurrentreading) {
		saveArray[ tableFldConstants.currentreading.ordinal() ] = wofbCurrentreading;
	}

	public String getWofbAdjustedreading() {
		return (String) saveArray[ tableFldConstants.adjustedreading.ordinal() ];
	}

	public void setWofbAdjustedreading(String wofbAdjustedreading) {
		saveArray[ tableFldConstants.adjustedreading.ordinal() ] = wofbAdjustedreading;
	}

	public String getWofbUom() {
		return (String) saveArray[ tableFldConstants.uom.ordinal() ];
	}

	public void setWofbUom(String wofbUom) {
		saveArray[ tableFldConstants.uom.ordinal() ] = wofbUom;
	}

	public String getWofbWhywhyflag() {
		return (String) saveArray[ tableFldConstants.whywhyflag.ordinal() ];
	}

	public void setWofbWhywhyflag(String wofbWhywhyflag) {
		saveArray[ tableFldConstants.whywhyflag.ordinal() ] = wofbWhywhyflag;
	}

	public String getWofbWhywhyid() {
		return (String) saveArray[ tableFldConstants.whywhyid.ordinal() ];
	}

	public void setWofbWhywhyid(String wofbWhywhyid) {
		saveArray[ tableFldConstants.whywhyid.ordinal() ] = wofbWhywhyid;
	}

	public String getWofbAmcflag() {
		return (String) saveArray[ tableFldConstants.amcflag.ordinal() ];
	}

	public void setWofbAmcflag(String wofbAmcflag) {
		saveArray[ tableFldConstants.amcflag.ordinal() ] = wofbAmcflag;
	}

	public String getWofbAmcdetailid() {
		return (String) saveArray[ tableFldConstants.amcdetailid.ordinal() ];
	}

	public void setWofbAmcdetailid(String wofbAmcdetailid) {
		saveArray[ tableFldConstants.amcdetailid.ordinal() ] = wofbAmcdetailid;
	}

	public String getWofbSpareflag() {
		return (String) saveArray[ tableFldConstants.spareflag.ordinal() ];
	}

	public void setWofbSpareflag(String wofbSpareflag) {
		saveArray[ tableFldConstants.spareflag.ordinal() ] = wofbSpareflag;
	}

	public String getWofbSparecost() {
		return (String) saveArray[ tableFldConstants.sparecost.ordinal() ];
	}

	public void setWofbSparecost(String wofbSparecost) {
		saveArray[ tableFldConstants.sparecost.ordinal() ] = wofbSparecost;
	}

	public String getWofbManpowercost() {
		return (String) saveArray[ tableFldConstants.manpowercost.ordinal() ];
	}

	public void setWofbManpowercost(String wofbManpowercost) {
		saveArray[ tableFldConstants.manpowercost.ordinal() ] = wofbManpowercost;
	}

	public String getWofbContractorcost() {
		return (String) saveArray[ tableFldConstants.contractorcost.ordinal() ];
	}

	public void setWofbContractorcost(String wofbContractorcost) {
		saveArray[ tableFldConstants.contractorcost.ordinal() ] = wofbContractorcost;
	}

	public String getWofbOthercost() {
		return (String) saveArray[ tableFldConstants.othercost.ordinal() ];
	}

	public void setWofbOthercost(String wofbOthercost) {
		saveArray[ tableFldConstants.othercost.ordinal() ] = wofbOthercost;
	}

	public String getWofbObservation() {
		return (String) saveArray[ tableFldConstants.observation.ordinal() ];
	}

	public void setWofbObservation(String wofbObservation) {
		saveArray[ tableFldConstants.observation.ordinal() ] = wofbObservation;
	}

	public String getWofbFeedback() {
		return (String) saveArray[ tableFldConstants.feedback.ordinal() ];
	}

	public void setWofbFeedback(String wofbFeedback) {
		saveArray[ tableFldConstants.feedback.ordinal() ] = wofbFeedback;
	}

	public String getWofbCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setWofbCompletedby(String wofbCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = wofbCompletedby;
	}

	public String getWofbRescheduleflag() {
		return (String) saveArray[ tableFldConstants.rescheduleflag.ordinal() ];
	}

	public void setWofbRescheduleflag(String wofbRescheduleflag) {
		saveArray[ tableFldConstants.rescheduleflag.ordinal() ] = wofbRescheduleflag;
	}

	public String getWofbReschedulereason() {
		return (String) saveArray[ tableFldConstants.reschedulereason.ordinal() ];
	}

	public void setWofbReschedulereason(String wofbReschedulereason) {
		saveArray[ tableFldConstants.reschedulereason.ordinal() ] = wofbReschedulereason;
	}

	public String getWofbNextinspectiondate() {
		return (String) saveArray[ tableFldConstants.nextinspectiondate.ordinal() ];
	}

	public void setWofbNextinspectiondate(String wofbNextinspectiondate) {
		saveArray[ tableFldConstants.nextinspectiondate.ordinal() ] = wofbNextinspectiondate;
	}

	public String getWofbRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setWofbRemarks(String wofbRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = wofbRemarks;
	}

	public String getWofbRootcause() {
		return (String) saveArray[ tableFldConstants.rootcause.ordinal() ];
	}

	public void setWofbRootcause(String wofbRootcause) {
		saveArray[ tableFldConstants.rootcause.ordinal() ] = wofbRootcause;
	}

	public String getWofbCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setWofbCountermeasure(String wofbCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = wofbCountermeasure;
	}

	public String getWofbMchcondition() {
		return (String) saveArray[ tableFldConstants.mchcondition.ordinal() ];
	}

	public void setWofbMchcondition(String wofbMchcondition) {
		saveArray[ tableFldConstants.mchcondition.ordinal() ] = wofbMchcondition;
	}
	public String getWofbMachinetakeovertime() {
		return (String) saveArray[ tableFldConstants.machinetakeovertime.ordinal() ];
	}

	public void setWofbMachinetakeovertime(String wofbMachinetakeovertime) {
		saveArray[ tableFldConstants.machinetakeovertime.ordinal() ] = wofbMachinetakeovertime;
	}

	public String getWofbCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWofbCreatedby(String wofbCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = wofbCreatedby;
	}

	public String getWofbModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWofbModifiedon(String wofbModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = wofbModifiedon;
	}

	public String getWofbCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWofbCreatedon(String wofbCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = wofbCreatedon;
	}

	public void setSpareConsumed(PlmTlSpareconsumed spareConsumed) {
		this.spareConsumed = spareConsumed;
	}

	public PlmTlSpareconsumed getSpareConsumed() {
		return spareConsumed;
	}

	public void setSpareCostActual(PlmTlSparecostactual spareCostActual) {
		this.spareCostActual = spareCostActual;
	}

	public PlmTlSparecostactual getSpareCostActual() {
		return spareCostActual;
	}

	public void setWobean(List<WorkOrderDetailsLstBean> wobean) {
		this.wobean = wobean;
	}

	public List<WorkOrderDetailsLstBean> getWobean() {
		return wobean;
	}

}

