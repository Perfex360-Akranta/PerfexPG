package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class MldTlMouldunloadmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, shiftdate, bookeddate, receiveddate, allocateddate, wostartdate
		, woenddate, responsetime, workhours, downtime, refdoctype, refdocid
		, mouldid, factoryid, sectionid, lineid, machineid, stationid
		, phenid, rootcauseid, shift, trade, partlocation, activitytype
		, mchcondition, manpowercost, contractorcost, othercost, sparecost
		, reason, rootcause, countermeasure, action, reportedby, targetdate
		, status, isyy, yyno, completedby, completeddate, remarks, pctrmeasure
		, relatedto, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, tempfield6, tempfield7, tempfield8, tempfield9, tempfield10
		, active, createdby, createdon, modifiedon
	}

	public MldTlMouldunloadmst()
	{
		saveArray = new  Object [ 57 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {		
		 this.saveArray = saveArray;
	}
	public String getMunlKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setMunlKeyid(String munlKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = munlKeyid;
	}

	public String getMunlShiftdate() {
		return (String) saveArray[ tableFldConstants.shiftdate.ordinal() ];
	}

	public void setMunlShiftdate(String munlShiftdate) {
		saveArray[ tableFldConstants.shiftdate.ordinal() ] = munlShiftdate;
	}

	public String getMunlBookeddate() {
		return (String) saveArray[ tableFldConstants.bookeddate.ordinal() ];
	}

	public void setMunlBookeddate(String munlBookeddate) {
		saveArray[ tableFldConstants.bookeddate.ordinal() ] = munlBookeddate;
	}

	public String getMunlReceiveddate() {
		return (String) saveArray[ tableFldConstants.receiveddate.ordinal() ];
	}

	public void setMunlReceiveddate(String munlReceiveddate) {
		saveArray[ tableFldConstants.receiveddate.ordinal() ] = munlReceiveddate;
	}

	public String getMunlAllocateddate() {
		return (String) saveArray[ tableFldConstants.allocateddate.ordinal() ];
	}

	public void setMunlAllocateddate(String munlAllocateddate) {
		saveArray[ tableFldConstants.allocateddate.ordinal() ] = munlAllocateddate;
	}

	public String getMunlWostartdate() {
		return (String) saveArray[ tableFldConstants.wostartdate.ordinal() ];
	}

	public void setMunlWostartdate(String munlWostartdate) {
		saveArray[ tableFldConstants.wostartdate.ordinal() ] = munlWostartdate;
	}

	public String getMunlWoenddate() {
		return (String) saveArray[ tableFldConstants.woenddate.ordinal() ];
	}

	public void setMunlWoenddate(String munlWoenddate) {
		saveArray[ tableFldConstants.woenddate.ordinal() ] = munlWoenddate;
	}

	public String getMunlResponsetime() {
		return (String) saveArray[ tableFldConstants.responsetime.ordinal() ];
	}

	public void setMunlResponsetime(String munlResponsetime) {
		saveArray[ tableFldConstants.responsetime.ordinal() ] = munlResponsetime;
	}

	public String getMunlWorkhours() {
		return (String) saveArray[ tableFldConstants.workhours.ordinal() ];
	}

	public void setMunlWorkhours(String munlWorkhours) {
		saveArray[ tableFldConstants.workhours.ordinal() ] = munlWorkhours;
	}

	public String getMunlDowntime() {
		return (String) saveArray[ tableFldConstants.downtime.ordinal() ];
	}

	public void setMunlDowntime(String munlDowntime) {
		saveArray[ tableFldConstants.downtime.ordinal() ] = munlDowntime;
	}

	public String getMunlRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setMunlRefdoctype(String munlRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = munlRefdoctype;
	}

	public String getMunlRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setMunlRefdocid(String munlRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = munlRefdocid;
	}

	public String getMunlMouldid() {
		return (String) saveArray[ tableFldConstants.mouldid.ordinal() ];
	}

	public void setMunlMouldid(String munlMouldid) {
		saveArray[ tableFldConstants.mouldid.ordinal() ] = munlMouldid;
	}

	public String getMunlFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setMunlFactoryid(String munlFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = munlFactoryid;
	}

	public String getMunlSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setMunlSectionid(String munlSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = munlSectionid;
	}

	public String getMunlLineid() {
		return (String) saveArray[ tableFldConstants.lineid.ordinal() ];
	}

	public void setMunlLineid(String munlLineid) {
		saveArray[ tableFldConstants.lineid.ordinal() ] = munlLineid;
	}

	public String getMunlMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setMunlMachineid(String munlMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = munlMachineid;
	}

	public String getMunlStationid() {
		return (String) saveArray[ tableFldConstants.stationid.ordinal() ];
	}

	public void setMunlStationid(String munlStationid) {
		saveArray[ tableFldConstants.stationid.ordinal() ] = munlStationid;
	}

	public String getMunlPhenid() {
		return (String) saveArray[ tableFldConstants.phenid.ordinal() ];
	}

	public void setMunlPhenid(String munlPhenid) {
		saveArray[ tableFldConstants.phenid.ordinal() ] = munlPhenid;
	}

	public String getMunlRootcauseid() {
		return (String) saveArray[ tableFldConstants.rootcauseid.ordinal() ];
	}

	public void setMunlRootcauseid(String munlRootcauseid) {
		saveArray[ tableFldConstants.rootcauseid.ordinal() ] = munlRootcauseid;
	}

	public String getMunlShift() {
		return (String) saveArray[ tableFldConstants.shift.ordinal() ];
	}

	public void setMunlShift(String munlShift) {
		saveArray[ tableFldConstants.shift.ordinal() ] = munlShift;
	}

	public String getMunlTrade() {
		return (String) saveArray[ tableFldConstants.trade.ordinal() ];
	}

	public void setMunlTrade(String munlTrade) {
		saveArray[ tableFldConstants.trade.ordinal() ] = munlTrade;
	}

	public String getMunlPartlocation() {
		return (String) saveArray[ tableFldConstants.partlocation.ordinal() ];
	}

	public void setMunlPartlocation(String munlPartlocation) {
		saveArray[ tableFldConstants.partlocation.ordinal() ] = munlPartlocation;
	}

	public String getMunlActivitytype() {
		return (String) saveArray[ tableFldConstants.activitytype.ordinal() ];
	}

	public void setMunlActivitytype(String munlActivitytype) {
		saveArray[ tableFldConstants.activitytype.ordinal() ] = munlActivitytype;
	}

	public String getMunlMchcondition() {
		return (String) saveArray[ tableFldConstants.mchcondition.ordinal() ];
	}

	public void setMunlMchcondition(String munlMchcondition) {
		saveArray[ tableFldConstants.mchcondition.ordinal() ] = munlMchcondition;
	}

	public String getMunlManpowercost() {
		return (String) saveArray[ tableFldConstants.manpowercost.ordinal() ];
	}

	public void setMunlManpowercost(String munlManpowercost) {
		saveArray[ tableFldConstants.manpowercost.ordinal() ] = munlManpowercost;
	}

	public String getMunlContractorcost() {
		return (String) saveArray[ tableFldConstants.contractorcost.ordinal() ];
	}

	public void setMunlContractorcost(String munlContractorcost) {
		saveArray[ tableFldConstants.contractorcost.ordinal() ] = munlContractorcost;
	}

	public String getMunlOthercost() {
		return (String) saveArray[ tableFldConstants.othercost.ordinal() ];
	}

	public void setMunlOthercost(String munlOthercost) {
		saveArray[ tableFldConstants.othercost.ordinal() ] = munlOthercost;
	}

	public String getMunlSparecost() {
		return (String) saveArray[ tableFldConstants.sparecost.ordinal() ];
	}

	public void setMunlSparecost(String munlSparecost) {
		saveArray[ tableFldConstants.sparecost.ordinal() ] = munlSparecost;
	}

	public String getMunlReason() {
		return (String) saveArray[ tableFldConstants.reason.ordinal() ];
	}

	public void setMunlReason(String munlReason) {
		saveArray[ tableFldConstants.reason.ordinal() ] = munlReason;
	}

	public String getMunlRootcause() {
		return (String) saveArray[ tableFldConstants.rootcause.ordinal() ];
	}

	public void setMunlRootcause(String munlRootcause) {
		saveArray[ tableFldConstants.rootcause.ordinal() ] = munlRootcause;
	}

	public String getMunlCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setMunlCountermeasure(String munlCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = munlCountermeasure;
	}

	public String getMunlAction() {
		return (String) saveArray[ tableFldConstants.action.ordinal() ];
	}

	public void setMunlAction(String munlAction) {
		saveArray[ tableFldConstants.action.ordinal() ] = munlAction;
	}

	public String getMunlReportedby() {
		return (String) saveArray[ tableFldConstants.reportedby.ordinal() ];
	}

	public void setMunlReportedby(String munlReportedby) {
		saveArray[ tableFldConstants.reportedby.ordinal() ] = munlReportedby;
	}

	public String getMunlTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setMunlTargetdate(String munlTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = munlTargetdate;
	}

	public String getMunlStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setMunlStatus(String munlStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = munlStatus;
	}

	public String getMunlIsyy() {
		return (String) saveArray[ tableFldConstants.isyy.ordinal() ];
	}

	public void setMunlIsyy(String munlIsyy) {
		saveArray[ tableFldConstants.isyy.ordinal() ] = munlIsyy;
	}

	public String getMunlYyno() {
		return (String) saveArray[ tableFldConstants.yyno.ordinal() ];
	}

	public void setMunlYyno(String munlYyno) {
		saveArray[ tableFldConstants.yyno.ordinal() ] = munlYyno;
	}

	public String getMunlCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setMunlCompletedby(String munlCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = munlCompletedby;
	}

	public String getMunlCompleteddate() {
		return (String) saveArray[ tableFldConstants.completeddate.ordinal() ];
	}

	public void setMunlCompleteddate(String munlCompleteddate) {
		saveArray[ tableFldConstants.completeddate.ordinal() ] = munlCompleteddate;
	}

	public String getMunlRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setMunlRemarks(String munlRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = munlRemarks;
	}

	public String getMunlPctrmeasure() {
		return (String) saveArray[ tableFldConstants.pctrmeasure.ordinal() ];
	}

	public void setMunlPctrmeasure(String munlPctrmeasure) {
		saveArray[ tableFldConstants.pctrmeasure.ordinal() ] = munlPctrmeasure;
	}

	public String getMunlRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setMunlRelatedto(String munlRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = munlRelatedto;
	}

	public String getMunlTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setMunlTempfield1(String munlTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = munlTempfield1;
	}

	public String getMunlTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setMunlTempfield2(String munlTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = munlTempfield2;
	}

	public String getMunlTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setMunlTempfield3(String munlTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = munlTempfield3;
	}

	public String getMunlTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setMunlTempfield4(String munlTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = munlTempfield4;
	}

	public String getMunlTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setMunlTempfield5(String munlTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = munlTempfield5;
	}

	public String getMunlTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setMunlTempfield6(String munlTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = munlTempfield6;
	}

	public String getMunlTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setMunlTempfield7(String munlTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = munlTempfield7;
	}

	public String getMunlTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setMunlTempfield8(String munlTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = munlTempfield8;
	}

	public String getMunlTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setMunlTempfield9(String munlTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = munlTempfield9;
	}

	public String getMunlTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setMunlTempfield10(String munlTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = munlTempfield10;
	}

	public String getMunlActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setMunlActive(String munlActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = munlActive;
	}

	public String getMunlCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setMunlCreatedby(String munlCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = munlCreatedby;
	}

	public String getMunlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setMunlCreatedon(String munlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = munlCreatedon;
	}

	public String getMunlModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setMunlModifiedon(String munlModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = munlModifiedon;
	}

}

