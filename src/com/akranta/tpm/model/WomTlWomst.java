package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class WomTlWomst {

	private  Object [] saveArray = null;  
	private String chkProcess;
	private String submitToSap;
	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, directentry, relatedto
		, loss, mouldid, workcenterid, costcenterid, occurreddate, shiftid
		, shiftdate, priority, reporteddate, reportedby, productionstop
		, machinecondition, activitytype, alarmno, tradeid, assemblyid
		, subassemblyid, failuretypeid, partlocation, spareid, phenomenaid
		, causeid, location, problem, bookingremarks, status, requestapproved
		, requestapprovedby, requestapproveddate, requestapprovremarks
		, accepteddate, acceptedflag, acceptedby, acceptedremarks, productionapproval
		, safetypermitsrequried, safetypermitid, safetypermitapproved
		, safetypermitcompleted, safetypermitsignoff, rescheduleflag
		, rescheduledate, rescheduledremarks, allottedflag, allotteddate
		, allottedto, allottedremarks, proposedstflag, proposedendflag
		, proposedstartdate, proposedenddate, proposeddtacceptflag, rescheduledstflag
		, rescheduledendflag, rescheduleby, reschedulestartdate, rescheduleenddate
		, rescheduleremarks, productionstartflag, productionstartdate
		, productionby, productionremarks, workstartflag, workstartdate
		, workendflag, workenddate, doneby, finalactivitytype, activityid
		, woapprovalflag, woapprovalby, woapprovaldate, machinereleaseflag
		, machinereleaseddate, machinereleaseby, intorextequip, intorextequipdesc
		, standbyadditionalinfo, standbyremarks, sentforrepairflag, sentrepairid
		, sentto, exceptedreturndate, repairremarks, remarks, jobopeningid
		, finalstatus, orderno, maintpriority, allottedsource, allottedsupplier
		, numofactivities, pwdmwono, refdoctype, refdocid, processid
		, requiredstart, requiredend, plannergroup, departmentid, tempfield1
		, modifiedby,elementid, flid, active, createdby, createdon, modifiedon
	}

	public WomTlWomst()
	{
		saveArray = new  Object [ 115 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}


	public String getWomsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWomsKeyid(String womsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = womsKeyid;
	}

	public String getWomsFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setWomsFactoryid(String womsFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = womsFactoryid;
	}

	public String getWomsSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setWomsSectionid(String womsSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = womsSectionid;
	}

	public String getWomsCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setWomsCellid(String womsCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = womsCellid;
	}

	public String getWomsMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setWomsMachineid(String womsMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = womsMachineid;
	}

	public String getWomsDirectentry() {
		return (String) saveArray[ tableFldConstants.directentry.ordinal() ];
	}

	public void setWomsDirectentry(String womsDirectentry) {
		saveArray[ tableFldConstants.directentry.ordinal() ] = womsDirectentry;
	}

	public String getWomsRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setWomsRelatedto(String womsRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = womsRelatedto;
	}

	public String getWomsLoss() {
		return (String) saveArray[ tableFldConstants.loss.ordinal() ];
	}

	public void setWomsLoss(String womsLoss) {
		saveArray[ tableFldConstants.loss.ordinal() ] = womsLoss;
	}

	public String getWomsMouldid() {
		return (String) saveArray[ tableFldConstants.mouldid.ordinal() ];
	}

	public void setWomsMouldid(String womsMouldid) {
		saveArray[ tableFldConstants.mouldid.ordinal() ] = womsMouldid;
	}

	public String getWomsWorkcenterid() {
		return (String) saveArray[ tableFldConstants.workcenterid.ordinal() ];
	}

	public void setWomsWorkcenterid(String womsWorkcenterid) {
		saveArray[ tableFldConstants.workcenterid.ordinal() ] = womsWorkcenterid;
	}

	public String getWomsCostcenterid() {
		return (String) saveArray[ tableFldConstants.costcenterid.ordinal() ];
	}

	public void setWomsCostcenterid(String womsCostcenterid) {
		saveArray[ tableFldConstants.costcenterid.ordinal() ] = womsCostcenterid;
	}

	public String getWomsOccurreddate() {
		return (String) saveArray[ tableFldConstants.occurreddate.ordinal() ];
	}

	public void setWomsOccurreddate(String womsOccurreddate) {
		saveArray[ tableFldConstants.occurreddate.ordinal() ] = womsOccurreddate;
	}

	public String getWomsShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setWomsShiftid(String womsShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = womsShiftid;
	}

	public String getWomsShiftdate() {
		return (String) saveArray[ tableFldConstants.shiftdate.ordinal() ];
	}

	public void setWomsShiftdate(String womsShiftdate) {
		saveArray[ tableFldConstants.shiftdate.ordinal() ] = womsShiftdate;
	}

	public String getWomsPriority() {
		return (String) saveArray[ tableFldConstants.priority.ordinal() ];
	}

	public void setWomsPriority(String womsPriority) {
		saveArray[ tableFldConstants.priority.ordinal() ] = womsPriority;
	}

	public String getWomsReporteddate() {
		return (String) saveArray[ tableFldConstants.reporteddate.ordinal() ];
	}

	public void setWomsReporteddate(String womsReporteddate) {
		saveArray[ tableFldConstants.reporteddate.ordinal() ] = womsReporteddate;
	}

	public String getWomsReportedby() {
		return (String) saveArray[ tableFldConstants.reportedby.ordinal() ];
	}

	public void setWomsReportedby(String womsReportedby) {
		saveArray[ tableFldConstants.reportedby.ordinal() ] = womsReportedby;
	}

	public String getWomsProductionstop() {
		return (String) saveArray[ tableFldConstants.productionstop.ordinal() ];
	}

	public void setWomsProductionstop(String womsProductionstop) {
		saveArray[ tableFldConstants.productionstop.ordinal() ] = womsProductionstop;
	}

	public String getWomsMachinecondition() {
		return (String) saveArray[ tableFldConstants.machinecondition.ordinal() ];
	}

	public void setWomsMachinecondition(String womsMachinecondition) {
		saveArray[ tableFldConstants.machinecondition.ordinal() ] = womsMachinecondition;
	}

	public String getWomsActivitytype() {
		return (String) saveArray[ tableFldConstants.activitytype.ordinal() ];
	}

	public void setWomsActivitytype(String womsActivitytype) {
		saveArray[ tableFldConstants.activitytype.ordinal() ] = womsActivitytype;
	}

	public String getWomsAlarmno() {
		return (String) saveArray[ tableFldConstants.alarmno.ordinal() ];
	}

	public void setWomsAlarmno(String womsAlarmno) {
		saveArray[ tableFldConstants.alarmno.ordinal() ] = womsAlarmno;
	}

	public String getWomsTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setWomsTradeid(String womsTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = womsTradeid;
	}

	public String getWomsAssemblyid() {
		return (String) saveArray[ tableFldConstants.assemblyid.ordinal() ];
	}

	public void setWomsAssemblyid(String womsAssemblyid) {
		saveArray[ tableFldConstants.assemblyid.ordinal() ] = womsAssemblyid;
	}

	public String getWomsSubassemblyid() {
		return (String) saveArray[ tableFldConstants.subassemblyid.ordinal() ];
	}

	public void setWomsSubassemblyid(String womsSubassemblyid) {
		saveArray[ tableFldConstants.subassemblyid.ordinal() ] = womsSubassemblyid;
	}

	public String getWomsFailuretypeid() {
		return (String) saveArray[ tableFldConstants.failuretypeid.ordinal() ];
	}

	public void setWomsFailuretypeid(String womsFailuretypeid) {
		saveArray[ tableFldConstants.failuretypeid.ordinal() ] = womsFailuretypeid;
	}

	public String getWomsPartlocation() {
		return (String) saveArray[ tableFldConstants.partlocation.ordinal() ];
	}

	public void setWomsPartlocation(String womsPartlocation) {
		saveArray[ tableFldConstants.partlocation.ordinal() ] = womsPartlocation;
	}

	public String getWomsSpareid() {
		return (String) saveArray[ tableFldConstants.spareid.ordinal() ];
	}

	public void setWomsSpareid(String womsSpareid) {
		saveArray[ tableFldConstants.spareid.ordinal() ] = womsSpareid;
	}

	public String getWomsPhenomenaid() {
		return (String) saveArray[ tableFldConstants.phenomenaid.ordinal() ];
	}

	public void setWomsPhenomenaid(String womsPhenomenaid) {
		saveArray[ tableFldConstants.phenomenaid.ordinal() ] = womsPhenomenaid;
	}

	public String getWomsCauseid() {
		return (String) saveArray[ tableFldConstants.causeid.ordinal() ];
	}

	public void setWomsCauseid(String womsCauseid) {
		saveArray[ tableFldConstants.causeid.ordinal() ] = womsCauseid;
	}

	public String getWomsLocation() {
		return (String) saveArray[ tableFldConstants.location.ordinal() ];
	}

	public void setWomsLocation(String womsLocation) {
		saveArray[ tableFldConstants.location.ordinal() ] = womsLocation;
	}

	public String getWomsProblem() {
		return (String) saveArray[ tableFldConstants.problem.ordinal() ];
	}

	public void setWomsProblem(String womsProblem) {
		saveArray[ tableFldConstants.problem.ordinal() ] = womsProblem;
	}

	public String getWomsBookingremarks() {
		return (String) saveArray[ tableFldConstants.bookingremarks.ordinal() ];
	}

	public void setWomsBookingremarks(String womsBookingremarks) {
		saveArray[ tableFldConstants.bookingremarks.ordinal() ] = womsBookingremarks;
	}

	public String getWomsStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setWomsStatus(String womsStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = womsStatus;
	}

	public String getWomsRequestapproved() {
		return (String) saveArray[ tableFldConstants.requestapproved.ordinal() ];
	}

	public void setWomsRequestapproved(String womsRequestapproved) {
		saveArray[ tableFldConstants.requestapproved.ordinal() ] = womsRequestapproved;
	}

	public String getWomsRequestapprovedby() {
		return (String) saveArray[ tableFldConstants.requestapprovedby.ordinal() ];
	}

	public void setWomsRequestapprovedby(String womsRequestapprovedby) {
		saveArray[ tableFldConstants.requestapprovedby.ordinal() ] = womsRequestapprovedby;
	}

	public String getWomsRequestapproveddate() {
		return (String) saveArray[ tableFldConstants.requestapproveddate.ordinal() ];
	}

	public void setWomsRequestapproveddate(String womsRequestapproveddate) {
		saveArray[ tableFldConstants.requestapproveddate.ordinal() ] = womsRequestapproveddate;
	}

	public String getWomsRequestapprovremarks() {
		return (String) saveArray[ tableFldConstants.requestapprovremarks.ordinal() ];
	}

	public void setWomsRequestapprovremarks(String womsRequestapprovremarks) {
		saveArray[ tableFldConstants.requestapprovremarks.ordinal() ] = womsRequestapprovremarks;
	}

	public String getWomsAccepteddate() {
		return (String) saveArray[ tableFldConstants.accepteddate.ordinal() ];
	}

	public void setWomsAccepteddate(String womsAccepteddate) {
		saveArray[ tableFldConstants.accepteddate.ordinal() ] = womsAccepteddate;
	}

	public String getWomsAcceptedflag() {
		return (String) saveArray[ tableFldConstants.acceptedflag.ordinal() ];
	}

	public void setWomsAcceptedflag(String womsAcceptedflag) {
		saveArray[ tableFldConstants.acceptedflag.ordinal() ] = womsAcceptedflag;
	}

	public String getWomsAcceptedby() {
		return (String) saveArray[ tableFldConstants.acceptedby.ordinal() ];
	}

	public void setWomsAcceptedby(String womsAcceptedby) {
		saveArray[ tableFldConstants.acceptedby.ordinal() ] = womsAcceptedby;
	}

	public String getWomsAcceptedremarks() {
		return (String) saveArray[ tableFldConstants.acceptedremarks.ordinal() ];
	}

	public void setWomsAcceptedremarks(String womsAcceptedremarks) {
		saveArray[ tableFldConstants.acceptedremarks.ordinal() ] = womsAcceptedremarks;
	}

	public String getWomsProductionapproval() {
		return (String) saveArray[ tableFldConstants.productionapproval.ordinal() ];
	}

	public void setWomsProductionapproval(String womsProductionapproval) {
		saveArray[ tableFldConstants.productionapproval.ordinal() ] = womsProductionapproval;
	}

	public String getWomsSafetypermitsrequried() {
		return (String) saveArray[ tableFldConstants.safetypermitsrequried.ordinal() ];
	}

	public void setWomsSafetypermitsrequried(String womsSafetypermitsrequried) {
		saveArray[ tableFldConstants.safetypermitsrequried.ordinal() ] = womsSafetypermitsrequried;
	}

	public String getWomsSafetypermitid() {
		return (String) saveArray[ tableFldConstants.safetypermitid.ordinal() ];
	}

	public void setWomsSafetypermitid(String womsSafetypermitid) {
		saveArray[ tableFldConstants.safetypermitid.ordinal() ] = womsSafetypermitid;
	}

	public String getWomsSafetypermitapproved() {
		return (String) saveArray[ tableFldConstants.safetypermitapproved.ordinal() ];
	}

	public void setWomsSafetypermitapproved(String womsSafetypermitapproved) {
		saveArray[ tableFldConstants.safetypermitapproved.ordinal() ] = womsSafetypermitapproved;
	}

	public String getWomsSafetypermitcompleted() {
		return (String) saveArray[ tableFldConstants.safetypermitcompleted.ordinal() ];
	}

	public void setWomsSafetypermitcompleted(String womsSafetypermitcompleted) {
		saveArray[ tableFldConstants.safetypermitcompleted.ordinal() ] = womsSafetypermitcompleted;
	}

	public String getWomsSafetypermitsignoff() {
		return (String) saveArray[ tableFldConstants.safetypermitsignoff.ordinal() ];
	}

	public void setWomsSafetypermitsignoff(String womsSafetypermitsignoff) {
		saveArray[ tableFldConstants.safetypermitsignoff.ordinal() ] = womsSafetypermitsignoff;
	}

	public String getWomsRescheduleflag() {
		return (String) saveArray[ tableFldConstants.rescheduleflag.ordinal() ];
	}

	public void setWomsRescheduleflag(String womsRescheduleflag) {
		saveArray[ tableFldConstants.rescheduleflag.ordinal() ] = womsRescheduleflag;
	}

	public String getWomsRescheduledate() {
		return (String) saveArray[ tableFldConstants.rescheduledate.ordinal() ];
	}

	public void setWomsRescheduledate(String womsRescheduledate) {
		saveArray[ tableFldConstants.rescheduledate.ordinal() ] = womsRescheduledate;
	}

	public String getWomsRescheduledremarks() {
		return (String) saveArray[ tableFldConstants.rescheduledremarks.ordinal() ];
	}

	public void setWomsRescheduledremarks(String womsRescheduledremarks) {
		saveArray[ tableFldConstants.rescheduledremarks.ordinal() ] = womsRescheduledremarks;
	}

	public String getWomsAllottedflag() {
		return (String) saveArray[ tableFldConstants.allottedflag.ordinal() ];
	}

	public void setWomsAllottedflag(String womsAllottedflag) {
		saveArray[ tableFldConstants.allottedflag.ordinal() ] = womsAllottedflag;
	}

	public String getWomsAllotteddate() {
		return (String) saveArray[ tableFldConstants.allotteddate.ordinal() ];
	}

	public void setWomsAllotteddate(String womsAllotteddate) {
		saveArray[ tableFldConstants.allotteddate.ordinal() ] = womsAllotteddate;
	}

	public String getWomsAllottedto() {
		return (String) saveArray[ tableFldConstants.allottedto.ordinal() ];
	}

	public void setWomsAllottedto(String womsAllottedto) {
		saveArray[ tableFldConstants.allottedto.ordinal() ] = womsAllottedto;
	}

	public String getWomsAllottedremarks() {
		return (String) saveArray[ tableFldConstants.allottedremarks.ordinal() ];
	}

	public void setWomsAllottedremarks(String womsAllottedremarks) {
		saveArray[ tableFldConstants.allottedremarks.ordinal() ] = womsAllottedremarks;
	}

	public String getWomsProposedstflag() {
		return (String) saveArray[ tableFldConstants.proposedstflag.ordinal() ];
	}

	public void setWomsProposedstflag(String womsProposedstflag) {
		saveArray[ tableFldConstants.proposedstflag.ordinal() ] = womsProposedstflag;
	}

	public String getWomsProposedendflag() {
		return (String) saveArray[ tableFldConstants.proposedendflag.ordinal() ];
	}

	public void setWomsProposedendflag(String womsProposedendflag) {
		saveArray[ tableFldConstants.proposedendflag.ordinal() ] = womsProposedendflag;
	}

	public String getWomsProposedstartdate() {
		return (String) saveArray[ tableFldConstants.proposedstartdate.ordinal() ];
	}

	public void setWomsProposedstartdate(String womsProposedstartdate) {
		saveArray[ tableFldConstants.proposedstartdate.ordinal() ] = womsProposedstartdate;
	}

	public String getWomsProposedenddate() {
		return (String) saveArray[ tableFldConstants.proposedenddate.ordinal() ];
	}

	public void setWomsProposedenddate(String womsProposedenddate) {
		saveArray[ tableFldConstants.proposedenddate.ordinal() ] = womsProposedenddate;
	}

	public String getWomsProposeddtacceptflag() {
		return (String) saveArray[ tableFldConstants.proposeddtacceptflag.ordinal() ];
	}

	public void setWomsProposeddtacceptflag(String womsProposeddtacceptflag) {
		saveArray[ tableFldConstants.proposeddtacceptflag.ordinal() ] = womsProposeddtacceptflag;
	}

	public String getWomsRescheduledstflag() {
		return (String) saveArray[ tableFldConstants.rescheduledstflag.ordinal() ];
	}

	public void setWomsRescheduledstflag(String womsRescheduledstflag) {
		saveArray[ tableFldConstants.rescheduledstflag.ordinal() ] = womsRescheduledstflag;
	}

	public String getWomsRescheduledendflag() {
		return (String) saveArray[ tableFldConstants.rescheduledendflag.ordinal() ];
	}

	public void setWomsRescheduledendflag(String womsRescheduledendflag) {
		saveArray[ tableFldConstants.rescheduledendflag.ordinal() ] = womsRescheduledendflag;
	}

	public String getWomsRescheduleby() {
		return (String) saveArray[ tableFldConstants.rescheduleby.ordinal() ];
	}

	public void setWomsRescheduleby(String womsRescheduleby) {
		saveArray[ tableFldConstants.rescheduleby.ordinal() ] = womsRescheduleby;
	}

	public String getWomsReschedulestartdate() {
		return (String) saveArray[ tableFldConstants.reschedulestartdate.ordinal() ];
	}

	public void setWomsReschedulestartdate(String womsReschedulestartdate) {
		saveArray[ tableFldConstants.reschedulestartdate.ordinal() ] = womsReschedulestartdate;
	}

	public String getWomsRescheduleenddate() {
		return (String) saveArray[ tableFldConstants.rescheduleenddate.ordinal() ];
	}

	public void setWomsRescheduleenddate(String womsRescheduleenddate) {
		saveArray[ tableFldConstants.rescheduleenddate.ordinal() ] = womsRescheduleenddate;
	}

	public String getWomsRescheduleremarks() {
		return (String) saveArray[ tableFldConstants.rescheduleremarks.ordinal() ];
	}

	public void setWomsRescheduleremarks(String womsRescheduleremarks) {
		saveArray[ tableFldConstants.rescheduleremarks.ordinal() ] = womsRescheduleremarks;
	}

	public String getWomsProductionstartflag() {
		return (String) saveArray[ tableFldConstants.productionstartflag.ordinal() ];
	}

	public void setWomsProductionstartflag(String womsProductionstartflag) {
		saveArray[ tableFldConstants.productionstartflag.ordinal() ] = womsProductionstartflag;
	}

	public String getWomsProductionstartdate() {
		return (String) saveArray[ tableFldConstants.productionstartdate.ordinal() ];
	}

	public void setWomsProductionstartdate(String womsProductionstartdate) {
		saveArray[ tableFldConstants.productionstartdate.ordinal() ] = womsProductionstartdate;
	}

	public String getWomsProductionby() {
		return (String) saveArray[ tableFldConstants.productionby.ordinal() ];
	}

	public void setWomsProductionby(String womsProductionby) {
		saveArray[ tableFldConstants.productionby.ordinal() ] = womsProductionby;
	}

	public String getWomsProductionremarks() {
		return (String) saveArray[ tableFldConstants.productionremarks.ordinal() ];
	}

	public void setWomsProductionremarks(String womsProductionremarks) {
		saveArray[ tableFldConstants.productionremarks.ordinal() ] = womsProductionremarks;
	}

	public String getWomsWorkstartflag() {
		return (String) saveArray[ tableFldConstants.workstartflag.ordinal() ];
	}

	public void setWomsWorkstartflag(String womsWorkstartflag) {
		saveArray[ tableFldConstants.workstartflag.ordinal() ] = womsWorkstartflag;
	}

	public String getWomsWorkstartdate() {
		return (String) saveArray[ tableFldConstants.workstartdate.ordinal() ];
	}

	public void setWomsWorkstartdate(String womsWorkstartdate) {
		saveArray[ tableFldConstants.workstartdate.ordinal() ] = womsWorkstartdate;
	}

	public String getWomsWorkendflag() {
		return (String) saveArray[ tableFldConstants.workendflag.ordinal() ];
	}

	public void setWomsWorkendflag(String womsWorkendflag) {
		saveArray[ tableFldConstants.workendflag.ordinal() ] = womsWorkendflag;
	}

	public String getWomsWorkenddate() {
		return (String) saveArray[ tableFldConstants.workenddate.ordinal() ];
	}

	public void setWomsWorkenddate(String womsWorkenddate) {
		saveArray[ tableFldConstants.workenddate.ordinal() ] = womsWorkenddate;
	}

	public String getWomsDoneby() {
		return (String) saveArray[ tableFldConstants.doneby.ordinal() ];
	}

	public void setWomsDoneby(String womsDoneby) {
		saveArray[ tableFldConstants.doneby.ordinal() ] = womsDoneby;
	}

	public String getWomsFinalactivitytype() {
		return (String) saveArray[ tableFldConstants.finalactivitytype.ordinal() ];
	}

	public void setWomsFinalactivitytype(String womsFinalactivitytype) {
		saveArray[ tableFldConstants.finalactivitytype.ordinal() ] = womsFinalactivitytype;
	}

	public String getWomsActivityid() {
		return (String) saveArray[ tableFldConstants.activityid.ordinal() ];
	}

	public void setWomsActivityid(String womsActivityid) {
		saveArray[ tableFldConstants.activityid.ordinal() ] = womsActivityid;
	}

	public String getWomsWoapprovalflag() {
		return (String) saveArray[ tableFldConstants.woapprovalflag.ordinal() ];
	}

	public void setWomsWoapprovalflag(String womsWoapprovalflag) {
		saveArray[ tableFldConstants.woapprovalflag.ordinal() ] = womsWoapprovalflag;
	}

	public String getWomsWoapprovalby() {
		return (String) saveArray[ tableFldConstants.woapprovalby.ordinal() ];
	}

	public void setWomsWoapprovalby(String womsWoapprovalby) {
		saveArray[ tableFldConstants.woapprovalby.ordinal() ] = womsWoapprovalby;
	}

	public String getWomsWoapprovaldate() {
		return (String) saveArray[ tableFldConstants.woapprovaldate.ordinal() ];
	}

	public void setWomsWoapprovaldate(String womsWoapprovaldate) {
		saveArray[ tableFldConstants.woapprovaldate.ordinal() ] = womsWoapprovaldate;
	}

	public String getWomsMachinereleaseflag() {
		return (String) saveArray[ tableFldConstants.machinereleaseflag.ordinal() ];
	}

	public void setWomsMachinereleaseflag(String womsMachinereleaseflag) {
		saveArray[ tableFldConstants.machinereleaseflag.ordinal() ] = womsMachinereleaseflag;
	}

	public String getWomsMachinereleaseddate() {
		return (String) saveArray[ tableFldConstants.machinereleaseddate.ordinal() ];
	}

	public void setWomsMachinereleaseddate(String womsMachinereleaseddate) {
		saveArray[ tableFldConstants.machinereleaseddate.ordinal() ] = womsMachinereleaseddate;
	}

	public String getWomsMachinereleaseby() {
		return (String) saveArray[ tableFldConstants.machinereleaseby.ordinal() ];
	}

	public void setWomsMachinereleaseby(String womsMachinereleaseby) {
		saveArray[ tableFldConstants.machinereleaseby.ordinal() ] = womsMachinereleaseby;
	}

	public String getWomsIntorextequip() {
		return (String) saveArray[ tableFldConstants.intorextequip.ordinal() ];
	}

	public void setWomsIntorextequip(String womsIntorextequip) {
		saveArray[ tableFldConstants.intorextequip.ordinal() ] = womsIntorextequip;
	}

	public String getWomsIntorextequipdesc() {
		return (String) saveArray[ tableFldConstants.intorextequipdesc.ordinal() ];
	}

	public void setWomsIntorextequipdesc(String womsIntorextequipdesc) {
		saveArray[ tableFldConstants.intorextequipdesc.ordinal() ] = womsIntorextequipdesc;
	}

	public String getWomsStandbyadditionalinfo() {
		return (String) saveArray[ tableFldConstants.standbyadditionalinfo.ordinal() ];
	}

	public void setWomsStandbyadditionalinfo(String womsStandbyadditionalinfo) {
		saveArray[ tableFldConstants.standbyadditionalinfo.ordinal() ] = womsStandbyadditionalinfo;
	}

	public String getWomsStandbyremarks() {
		return (String) saveArray[ tableFldConstants.standbyremarks.ordinal() ];
	}

	public void setWomsStandbyremarks(String womsStandbyremarks) {
		saveArray[ tableFldConstants.standbyremarks.ordinal() ] = womsStandbyremarks;
	}

	public String getWomsSentforrepairflag() {
		return (String) saveArray[ tableFldConstants.sentforrepairflag.ordinal() ];
	}

	public void setWomsSentforrepairflag(String womsSentforrepairflag) {
		saveArray[ tableFldConstants.sentforrepairflag.ordinal() ] = womsSentforrepairflag;
	}

	public String getWomsSentrepairid() {
		return (String) saveArray[ tableFldConstants.sentrepairid.ordinal() ];
	}

	public void setWomsSentrepairid(String womsSentrepairid) {
		saveArray[ tableFldConstants.sentrepairid.ordinal() ] = womsSentrepairid;
	}

	public String getWomsSentto() {
		return (String) saveArray[ tableFldConstants.sentto.ordinal() ];
	}

	public void setWomsSentto(String womsSentto) {
		saveArray[ tableFldConstants.sentto.ordinal() ] = womsSentto;
	}

	public String getWomsExceptedreturndate() {
		return (String) saveArray[ tableFldConstants.exceptedreturndate.ordinal() ];
	}

	public void setWomsExceptedreturndate(String womsExceptedreturndate) {
		saveArray[ tableFldConstants.exceptedreturndate.ordinal() ] = womsExceptedreturndate;
	}

	public String getWomsRepairremarks() {
		return (String) saveArray[ tableFldConstants.repairremarks.ordinal() ];
	}

	public void setWomsRepairremarks(String womsRepairremarks) {
		saveArray[ tableFldConstants.repairremarks.ordinal() ] = womsRepairremarks;
	}

	public String getWomsRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setWomsRemarks(String womsRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = womsRemarks;
	}

	public String getWomsJobopeningid() {
		return (String) saveArray[ tableFldConstants.jobopeningid.ordinal() ];
	}

	public void setWomsJobopeningid(String womsJobopeningid) {
		saveArray[ tableFldConstants.jobopeningid.ordinal() ] = womsJobopeningid;
	}

	public String getWomsFinalstatus() {
		return (String) saveArray[ tableFldConstants.finalstatus.ordinal() ];
	}

	public void setWomsFinalstatus(String womsFinalstatus) {
		saveArray[ tableFldConstants.finalstatus.ordinal() ] = womsFinalstatus;
	}

	public String getWomsOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setWomsOrderno(String womsOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = womsOrderno;
	}

	public String getWomsMaintpriority() {
		return (String) saveArray[ tableFldConstants.maintpriority.ordinal() ];
	}

	public void setWomsMaintpriority(String womsMaintpriority) {
		saveArray[ tableFldConstants.maintpriority.ordinal() ] = womsMaintpriority;
	}

	public String getWomsAllottedsource() {
		return (String) saveArray[ tableFldConstants.allottedsource.ordinal() ];
	}

	public void setWomsAllottedsource(String womsAllottedsource) {
		saveArray[ tableFldConstants.allottedsource.ordinal() ] = womsAllottedsource;
	}

	public String getWomsAllottedsupplier() {
		return (String) saveArray[ tableFldConstants.allottedsupplier.ordinal() ];
	}

	public void setWomsAllottedsupplier(String womsAllottedsupplier) {
		saveArray[ tableFldConstants.allottedsupplier.ordinal() ] = womsAllottedsupplier;
	}

	public String getWomsNumofactivities() {
		return (String) saveArray[ tableFldConstants.numofactivities.ordinal() ];
	}

	public void setWomsNumofactivities(String womsNumofactivities) {
		saveArray[ tableFldConstants.numofactivities.ordinal() ] = womsNumofactivities;
	}

	public String getWomsPwdmwono() {
		return (String) saveArray[ tableFldConstants.pwdmwono.ordinal() ];
	}

	public void setWomsPwdmwono(String womsPwdmwono) {
		saveArray[ tableFldConstants.pwdmwono.ordinal() ] = womsPwdmwono;
	}

	public String getWomsRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setWomsRefdoctype(String womsRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = womsRefdoctype;
	}

	public String getWomsRefdocid() {
		return (String) saveArray[ tableFldConstants.refdocid.ordinal() ];
	}

	public void setWomsRefdocid(String womsRefdocid) {
		saveArray[ tableFldConstants.refdocid.ordinal() ] = womsRefdocid;
	}

	public String getWomsProcessId() {
		return (String) saveArray[ tableFldConstants.processid.ordinal() ];
	}

	public void setWomsProcessId(String womsProcessId) {
		saveArray[ tableFldConstants.processid.ordinal() ] = womsProcessId;
	}

	public String getWomsDepartmentid() {
		return (String) saveArray[ tableFldConstants.departmentid.ordinal() ];
	}

	public void setWomsDepartmentid(String womsDepartmentid) {
		saveArray[ tableFldConstants.departmentid.ordinal() ] = womsDepartmentid;
	}

	public String getWomsPlannergroup() {
		return (String) saveArray[ tableFldConstants.plannergroup.ordinal() ];
	}

	public void setWomsPlannergroup(String womsPlannergroup) {
		saveArray[ tableFldConstants.plannergroup.ordinal() ] = womsPlannergroup;
	}

	public String getWomsRequiredend() {
		return (String) saveArray[ tableFldConstants.requiredend.ordinal() ];
	}

	public void setWomsRequiredend(String womsRequiredend) {
		saveArray[ tableFldConstants.requiredend.ordinal() ] = womsRequiredend;
	}

	public String getWomsRequiredstart() {
		return (String) saveArray[ tableFldConstants.requiredstart.ordinal() ];
	}

	public void setWomsRequiredstart(String womsRequiredstart) {
		saveArray[ tableFldConstants.requiredstart.ordinal() ] = womsRequiredstart;
	}

	public String getWomsTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setWomsTempfield1(String womsTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = womsTempfield1;
	}

	public String getWomsModifiedby() {
		return (String) saveArray[ tableFldConstants.modifiedby.ordinal() ];
	}

	public void setWomsModifiedby(String womsModifiedby) {
		saveArray[ tableFldConstants.modifiedby.ordinal() ] = womsModifiedby;
	}

	public String getWomsElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setWomsElementid(String womsElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = womsElementid;
	}

	public String getWomsFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setWomsFlid(String womsFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = womsFlid;
	}
	public String getWomsActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setWomsActive(String womsActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = womsActive;
	}

	public String getWomsCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setWomsCreatedby(String womsCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = womsCreatedby;
	}

	public String getWomsCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setWomsCreatedon(String womsCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = womsCreatedon;
	}

	public String getWomsModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setWomsModifiedon(String womsModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = womsModifiedon;
	}

	public String getChkProcess() {
		return chkProcess;
	}

	public void setChkProcess(String chkProcess) {
		this.chkProcess = chkProcess;
	}

	public String getSubmitToSap() {
		return submitToSap;
	}

	public void setSubmitToSap(String submitToSap) {
		this.submitToSap = submitToSap;
	}

}

