package com.akranta.tpm.model;
import java.util.List;

public class PlmTlGenmaintenance {

	private  Object [] saveArray = null;  
	private List<GenTlTeamDoucmentLink> teamList = null;
	// Added by vignesh 
	private List<BdmTlSetupadjsplit> setupAdjList = null;
	// Added by vignesh 
	public enum   tableFldConstants
	{
		keyid, occureddate, shiftdate, bookeddate, receiveddate, allocateddate
		, wostartdate, woenddate, responsetime, workhours, downtime, refdoctype
		, refdocid, factoryid, sectionid, lineid, machineid, stationid
		, phenid, rootcauseid, shift, trade, partlocation, activitytype
		, mchcondition, manpowercost, contractorcost, othercost, sparecost
		, problem, rootcause, countermeasure, action, reportedby, targetdate
		, status, isyy, yyno, completedby, completeddate, remarks, pctrmeasure
		, relatedto, mouldid, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, tempfield6, tempfield7, tempfield8, tempfield9
		, tempfield10, elementid,flid,active, createdby, createdon, modifiedon
	}

	public PlmTlGenmaintenance()
	{
		saveArray = new  Object [ 60 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	
	// --- Added by Vignesh --//
	 
	  public void setBdmTlSetupadjsplit(List<BdmTlSetupadjsplit> bdmTlSetupadjsplit) { 
		  setupAdjList  = bdmTlSetupadjsplit ; 
		  }
		
		  public List<BdmTlSetupadjsplit> getBdmTlSetupadjsplit() { 
			  return setupAdjList ;
		                   }
		 
		// --- Added by Vignesh --//

	public String getGmntKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setGmntKeyid(String gmntKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = gmntKeyid;
	}

	public String getGmntOccureddate() {
		return (String) saveArray[ tableFldConstants.occureddate.ordinal() ];
	}

	public void setGmntOccureddate(String gmntOccureddate) {
		saveArray[ tableFldConstants.occureddate.ordinal() ] = gmntOccureddate;
	}

	public String getGmntShiftdate() {
		return (String) saveArray[ tableFldConstants.shiftdate.ordinal() ];
	}

	public void setGmntShiftdate(String gmntShiftdate) {
		saveArray[ tableFldConstants.shiftdate.ordinal() ] = gmntShiftdate;
	}

	public String getGmntBookeddate() {
		return (String) saveArray[ tableFldConstants.bookeddate.ordinal() ];
	}

	public void setGmntBookeddate(String gmntBookeddate) {
		saveArray[ tableFldConstants.bookeddate.ordinal() ] = gmntBookeddate;
	}

	public String getGmntReceiveddate() {
		return (String) saveArray[ tableFldConstants.receiveddate.ordinal() ];
	}

	public void setGmntReceiveddate(String gmntReceiveddate) {
		saveArray[ tableFldConstants.receiveddate.ordinal() ] = gmntReceiveddate;
	}

	public String getGmntAllocateddate() {
		return (String) saveArray[ tableFldConstants.allocateddate.ordinal() ];
	}

	public void setGmntAllocateddate(String gmntAllocateddate) {
		saveArray[ tableFldConstants.allocateddate.ordinal() ] = gmntAllocateddate;
	}

	public String getGmntWostartdate() {
		return (String) saveArray[ tableFldConstants.wostartdate.ordinal() ];
	}

	public void setGmntWostartdate(String gmntWostartdate) {
		saveArray[ tableFldConstants.wostartdate.ordinal() ] = gmntWostartdate;
	}

	public String getGmntWoenddate() {
		return (String) saveArray[ tableFldConstants.woenddate.ordinal() ];
	}

	public void setGmntWoenddate(String gmntWoenddate) {
		saveArray[ tableFldConstants.woenddate.ordinal() ] = gmntWoenddate;
	}

	public String getGmntResponsetime() {
		return (String) saveArray[ tableFldConstants.responsetime.ordinal() ];
	}

	public void setGmntResponsetime(String gmntResponsetime) {
		saveArray[ tableFldConstants.responsetime.ordinal() ] = gmntResponsetime;
	}

	public String getGmntWorkhours() {
		return (String) saveArray[ tableFldConstants.workhours.ordinal() ];
	}

	public void setGmntWorkhours(String gmntWorkhours) {
		saveArray[ tableFldConstants.workhours.ordinal() ] = gmntWorkhours;
	}

	public String getGmntDowntime() {
		return (String) saveArray[ tableFldConstants.downtime.ordinal() ];
	}

	public void setGmntDowntime(String gmntDowntime) {
		saveArray[ tableFldConstants.downtime.ordinal() ] = gmntDowntime;
	}

	public String getGmntRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setGmntRefdoctype(String gmntRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = gmntRefdoctype;
	}

	public String getGmntRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setGmntRefdocid(String gmntRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = gmntRefdocid;
	}

	public String getGmntFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setGmntFactoryid(String gmntFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = gmntFactoryid;
	}

	public String getGmntSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setGmntSectionid(String gmntSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = gmntSectionid;
	}

	public String getGmntLineid() {
		return (String) saveArray[ tableFldConstants.lineid.ordinal() ];
	}

	public void setGmntLineid(String gmntLineid) {
		saveArray[ tableFldConstants.lineid.ordinal() ] = gmntLineid;
	}

	public String getGmntMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setGmntMachineid(String gmntMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = gmntMachineid;
	}

	public String getGmntStationid() {
		return (String) saveArray[ tableFldConstants.stationid.ordinal() ];
	}

	public void setGmntStationid(String gmntStationid) {
		saveArray[ tableFldConstants.stationid.ordinal() ] = gmntStationid;
	}

	public String getGmntPhenid() {
		return (String) saveArray[ tableFldConstants.phenid.ordinal() ];
	}

	public void setGmntPhenid(String gmntPhenid) {
		saveArray[ tableFldConstants.phenid.ordinal() ] = gmntPhenid;
	}

	public String getGmntRootcauseid() {
		return (String) saveArray[ tableFldConstants.rootcauseid.ordinal() ];
	}

	public void setGmntRootcauseid(String gmntRootcauseid) {
		saveArray[ tableFldConstants.rootcauseid.ordinal() ] = gmntRootcauseid;
	}

	public String getGmntShift() {
		return (String) saveArray[ tableFldConstants.shift.ordinal() ];
	}

	public void setGmntShift(String gmntShift) {
		saveArray[ tableFldConstants.shift.ordinal() ] = gmntShift;
	}

	public String getGmntTrade() {
		return (String) saveArray[ tableFldConstants.trade.ordinal() ];
	}

	public void setGmntTrade(String gmntTrade) {
		saveArray[ tableFldConstants.trade.ordinal() ] = gmntTrade;
	}

	public String getGmntPartlocation() {
		return (String) saveArray[ tableFldConstants.partlocation.ordinal() ];
	}

	public void setGmntPartlocation(String gmntPartlocation) {
		saveArray[ tableFldConstants.partlocation.ordinal() ] = gmntPartlocation;
	}

	public String getGmntActivitytype() {
		return (String) saveArray[ tableFldConstants.activitytype.ordinal() ];
	}

	public void setGmntActivitytype(String gmntActivitytype) {
		saveArray[ tableFldConstants.activitytype.ordinal() ] = gmntActivitytype;
	}

	public String getGmntMchcondition() {
		return (String) saveArray[ tableFldConstants.mchcondition.ordinal() ];
	}

	public void setGmntMchcondition(String gmntMchcondition) {
		saveArray[ tableFldConstants.mchcondition.ordinal() ] = gmntMchcondition;
	}

	public String getGmntManpowercost() {
		return (String) saveArray[ tableFldConstants.manpowercost.ordinal() ];
	}

	public void setGmntManpowercost(String gmntManpowercost) {
		saveArray[ tableFldConstants.manpowercost.ordinal() ] = gmntManpowercost;
	}

	public String getGmntContractorcost() {
		return (String) saveArray[ tableFldConstants.contractorcost.ordinal() ];
	}

	public void setGmntContractorcost(String gmntContractorcost) {
		saveArray[ tableFldConstants.contractorcost.ordinal() ] = gmntContractorcost;
	}

	public String getGmntOthercost() {
		return (String) saveArray[ tableFldConstants.othercost.ordinal() ];
	}

	public void setGmntOthercost(String gmntOthercost) {
		saveArray[ tableFldConstants.othercost.ordinal() ] = gmntOthercost;
	}

	public String getGmntSparecost() {
		return (String) saveArray[ tableFldConstants.sparecost.ordinal() ];
	}

	public void setGmntSparecost(String gmntSparecost) {
		saveArray[ tableFldConstants.sparecost.ordinal() ] = gmntSparecost;
	}

	public String getGmntProblem() {
		return (String) saveArray[ tableFldConstants.problem.ordinal() ];
	}

	public void setGmntProblem(String gmntProblem) {
		saveArray[ tableFldConstants.problem.ordinal() ] = gmntProblem;
	}

	public String getGmntRootcause() {
		return (String) saveArray[ tableFldConstants.rootcause.ordinal() ];
	}

	public void setGmntRootcause(String gmntRootcause) {
		saveArray[ tableFldConstants.rootcause.ordinal() ] = gmntRootcause;
	}

	public String getGmntCountermeasure() {
		return (String) saveArray[ tableFldConstants.countermeasure.ordinal() ];
	}

	public void setGmntCountermeasure(String gmntCountermeasure) {
		saveArray[ tableFldConstants.countermeasure.ordinal() ] = gmntCountermeasure;
	}

	public String getGmntAction() {
		return (String) saveArray[ tableFldConstants.action.ordinal() ];
	}

	public void setGmntAction(String gmntAction) {
		saveArray[ tableFldConstants.action.ordinal() ] = gmntAction;
	}

	public String getGmntReportedby() {
		return (String) saveArray[ tableFldConstants.reportedby.ordinal() ];
	}

	public void setGmntReportedby(String gmntReportedby) {
		saveArray[ tableFldConstants.reportedby.ordinal() ] = gmntReportedby;
	}

	public String getGmntTargetdate() {
		return (String) saveArray[ tableFldConstants.targetdate.ordinal() ];
	}

	public void setGmntTargetdate(String gmntTargetdate) {
		saveArray[ tableFldConstants.targetdate.ordinal() ] = gmntTargetdate;
	}

	public String getGmntStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setGmntStatus(String gmntStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = gmntStatus;
	}

	public String getGmntIsyy() {
		return (String) saveArray[ tableFldConstants.isyy.ordinal() ];
	}

	public void setGmntIsyy(String gmntIsyy) {
		saveArray[ tableFldConstants.isyy.ordinal() ] = gmntIsyy;
	}

	public String getGmntYyno() {
		return (String) saveArray[ tableFldConstants.yyno.ordinal() ];
	}

	public void setGmntYyno(String gmntYyno) {
		saveArray[ tableFldConstants.yyno.ordinal() ] = gmntYyno;
	}

	public String getGmntCompletedby() {
		return (String) saveArray[ tableFldConstants.completedby.ordinal() ];
	}

	public void setGmntCompletedby(String gmntCompletedby) {
		saveArray[ tableFldConstants.completedby.ordinal() ] = gmntCompletedby;
	}

	public String getGmntCompleteddate() {
		return (String) saveArray[ tableFldConstants.completeddate.ordinal() ];
	}

	public void setGmntCompleteddate(String gmntCompleteddate) {
		saveArray[ tableFldConstants.completeddate.ordinal() ] = gmntCompleteddate;
	}

	public String getGmntRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setGmntRemarks(String gmntRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = gmntRemarks;
	}

	public String getGmntPctrmeasure() {
		return (String) saveArray[ tableFldConstants.pctrmeasure.ordinal() ];
	}

	public void setGmntPctrmeasure(String gmntPctrmeasure) {
		saveArray[ tableFldConstants.pctrmeasure.ordinal() ] = gmntPctrmeasure;
	}

	public String getGmntRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setGmntRelatedto(String gmntRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = gmntRelatedto;
	}

	public String getGmntMouldid() {
		return (String) saveArray[ tableFldConstants.mouldid.ordinal() ];
	}

	public void setGmntMouldid(String gmntMouldid) {
		saveArray[ tableFldConstants.mouldid.ordinal() ] = gmntMouldid;
	}

	public String getGmntTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setGmntTempfield1(String gmntTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = gmntTempfield1;
	}

	public String getGmntTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setGmntTempfield2(String gmntTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = gmntTempfield2;
	}

	public String getGmntTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setGmntTempfield3(String gmntTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = gmntTempfield3;
	}

	public String getGmntTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setGmntTempfield4(String gmntTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = gmntTempfield4;
	}

	public String getGmntTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setGmntTempfield5(String gmntTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = gmntTempfield5;
	}

	public String getGmntTempfield6() {
		return (String) saveArray[ tableFldConstants.tempfield6.ordinal() ];
	}

	public void setGmntTempfield6(String gmntTempfield6) {
		saveArray[ tableFldConstants.tempfield6.ordinal() ] = gmntTempfield6;
	}

	public String getGmntTempfield7() {
		return (String) saveArray[ tableFldConstants.tempfield7.ordinal() ];
	}

	public void setGmntTempfield7(String gmntTempfield7) {
		saveArray[ tableFldConstants.tempfield7.ordinal() ] = gmntTempfield7;
	}

	public String getGmntTempfield8() {
		return (String) saveArray[ tableFldConstants.tempfield8.ordinal() ];
	}

	public void setGmntTempfield8(String gmntTempfield8) {
		saveArray[ tableFldConstants.tempfield8.ordinal() ] = gmntTempfield8;
	}

	public String getGmntTempfield9() {
		return (String) saveArray[ tableFldConstants.tempfield9.ordinal() ];
	}

	public void setGmntTempfield9(String gmntTempfield9) {
		saveArray[ tableFldConstants.tempfield9.ordinal() ] = gmntTempfield9;
	}

	public String getGmntTempfield10() {
		return (String) saveArray[ tableFldConstants.tempfield10.ordinal() ];
	}

	public void setGmntTempfield10(String gmntTempfield10) {
		saveArray[ tableFldConstants.tempfield10.ordinal() ] = gmntTempfield10;
	}

	public String getGmntElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setGmntElementid(String gmntElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = gmntElementid;
	}
	
	public String getGmntFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setGmntFlid(String gmntFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = gmntFlid;
	}
	
	public String getGmntActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setGmntActive(String gmntActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = gmntActive;
	}

	public String getGmntCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setGmntCreatedby(String gmntCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = gmntCreatedby;
	}

	public String getGmntCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setGmntCreatedon(String gmntCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = gmntCreatedon;
	}

	public String getGmntModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setGmntModifiedon(String gmntModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = gmntModifiedon;
	}

	public void setTeamList(List<GenTlTeamDoucmentLink> teamList) {
		this.teamList = teamList;
	}

	public List<GenTlTeamDoucmentLink> getTeamList() {
		return teamList;
	}

}

