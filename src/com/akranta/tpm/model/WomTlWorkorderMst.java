package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class WomTlWorkorderMst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, orderno, flid, cellid, machineid, processid, womscenterid
		, costcenterid, plannergroupid, womsgroupid, occurred_date, shiftid
		, shift_date, priority, reported_date, reported_by, is_prod_stopped
		, machine_condition, activity_type, alarmno, tradeid, assemblyid
		, subassemblyid, failuretypeid, partlocation, spareid, phenomenaid
		, causeid, location, problem, bookingremarks, status, req_approved_flag
		, req_approved_by, req_approved_date, req_approved_remarks, accepted_flag
		, accepted_date, accepted_by, accepted_remarks, allotted_flag
		, allotted_date, allotted_to, allotted_remarks, womsstart_flag
		, womsstart_date, womsend_flag, womsend_date, done_by, production_start_flag
		, production_start_date, production_by, production_remarks, final_activitytype
		, activityid, standby_info, standby_remarks, ext_repair_flag
		, ext_repairid, repair_remarks, ext_service_flag, ext_serviceid
		, remarks, final_status, refdoctype, refdocid
		, sapnotfn_flag,  sapnotfn_type, sapnotfn_no, sapnotfn_status 
		, saporder_flag, saporder_type, saporder_no, saporder_status 
		, wbs_elementid,  required_start, required_end
		, sapnotfn_message, saporder_message, yyrefno, fishbone_refno, saporder_date
		,problem_severity, standby_equipment, immediate_action, root_cause, counter_measure
		,execution_remarks,classificationid,analysedby ,completion_date, technicomp_date, busicomp_date
		,controlkey, temp2, temp3, temp4, temp5, 
		active, createdby, modifiedby, createdon, modifiedon
	}

	public WomTlWorkorderMst()
	{
		saveArray = new  Object [ 103 ];
	}

	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getWomsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setWomsKeyid(String womsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = womsKeyid;
	}

	public String getWomsOrderno() {
		return (String) saveArray[ tableFldConstants.orderno.ordinal() ];
	}

	public void setWomsOrderno(String womsOrderno) {
		saveArray[ tableFldConstants.orderno.ordinal() ] = womsOrderno;
	}

	public String getWomsFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setWomsFlid(String womsFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = womsFlid;
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

	public String getWomsProcessid() {
		return (String) saveArray[ tableFldConstants.processid.ordinal() ];
	}

	public void setWomsProcessid(String womsProcessid) {
		saveArray[ tableFldConstants.processid.ordinal() ] = womsProcessid;
	}

	public String getWomsWomscenterid() {
		return (String) saveArray[ tableFldConstants.womscenterid.ordinal() ];
	}

	public void setWomsWomscenterid(String womsWomscenterid) {
		saveArray[ tableFldConstants.womscenterid.ordinal() ] = womsWomscenterid;
	}

	public String getWomsCostcenterid() {
		return (String) saveArray[ tableFldConstants.costcenterid.ordinal() ];
	}

	public void setWomsCostcenterid(String womsCostcenterid) {
		saveArray[ tableFldConstants.costcenterid.ordinal() ] = womsCostcenterid;
	}

	public String getWomsPlannergroupid() {
		return (String) saveArray[ tableFldConstants.plannergroupid.ordinal() ];
	}

	public void setWomsPlannergroupid(String womsPlannergroupid) {
		saveArray[ tableFldConstants.plannergroupid.ordinal() ] = womsPlannergroupid;
	}

	public String getWomsWomsgroupid() {
		return (String) saveArray[ tableFldConstants.womsgroupid.ordinal() ];
	}

	public void setWomsWomsgroupid(String womsWomsgroupid) {
		saveArray[ tableFldConstants.womsgroupid.ordinal() ] = womsWomsgroupid;
	}

	public String getWomsOccurredDate() {
		return (String) saveArray[ tableFldConstants.occurred_date.ordinal() ];
	}

	public void setWomsOccurredDate(String womsOccurredDate) {
		saveArray[ tableFldConstants.occurred_date.ordinal() ] = womsOccurredDate;
	}

	public String getWomsShiftid() {
		return (String) saveArray[ tableFldConstants.shiftid.ordinal() ];
	}

	public void setWomsShiftid(String womsShiftid) {
		saveArray[ tableFldConstants.shiftid.ordinal() ] = womsShiftid;
	}

	public String getWomsShiftDate() {
		return (String) saveArray[ tableFldConstants.shift_date.ordinal() ];
	}

	public void setWomsShiftDate(String womsShiftDate) {
		saveArray[ tableFldConstants.shift_date.ordinal() ] = womsShiftDate;
	}

	public String getWomsPriority() {
		return (String) saveArray[ tableFldConstants.priority.ordinal() ];
	}

	public void setWomsPriority(String womsPriority) {
		saveArray[ tableFldConstants.priority.ordinal() ] = womsPriority;
	}

	public String getWomsReportedDate() {
		return (String) saveArray[ tableFldConstants.reported_date.ordinal() ];
	}

	public void setWomsReportedDate(String womsReportedDate) {
		saveArray[ tableFldConstants.reported_date.ordinal() ] = womsReportedDate;
	}

	public String getWomsReportedBy() {
		return (String) saveArray[ tableFldConstants.reported_by.ordinal() ];
	}

	public void setWomsReportedBy(String womsReportedBy) {
		saveArray[ tableFldConstants.reported_by.ordinal() ] = womsReportedBy;
	}

	public String getWomsIsProdStopped() {
		return (String) saveArray[ tableFldConstants.is_prod_stopped.ordinal() ];
	}

	public void setWomsIsProdStopped(String womsIsProdStopped) {
		saveArray[ tableFldConstants.is_prod_stopped.ordinal() ] = womsIsProdStopped;
	}

	public String getWomsMachineCondition() {
		return (String) saveArray[ tableFldConstants.machine_condition.ordinal() ];
	}

	public void setWomsMachineCondition(String womsMachineCondition) {
		saveArray[ tableFldConstants.machine_condition.ordinal() ] = womsMachineCondition;
	}

	public String getWomsActivityType() {
		return (String) saveArray[ tableFldConstants.activity_type.ordinal() ];
	}

	public void setWomsActivityType(String womsActivityType) {
		saveArray[ tableFldConstants.activity_type.ordinal() ] = womsActivityType;
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

	public String getWomsReqApprovedFlag() {
		return (String) saveArray[ tableFldConstants.req_approved_flag.ordinal() ];
	}

	public void setWomsReqApprovedFlag(String womsReqApprovedFlag) {
		saveArray[ tableFldConstants.req_approved_flag.ordinal() ] = womsReqApprovedFlag;
	}

	public String getWomsReqApprovedBy() {
		return (String) saveArray[ tableFldConstants.req_approved_by.ordinal() ];
	}

	public void setWomsReqApprovedBy(String womsReqApprovedBy) {
		saveArray[ tableFldConstants.req_approved_by.ordinal() ] = womsReqApprovedBy;
	}

	public String getWomsReqApprovedDate() {
		return (String) saveArray[ tableFldConstants.req_approved_date.ordinal() ];
	}

	public void setWomsReqApprovedDate(String womsReqApprovedDate) {
		saveArray[ tableFldConstants.req_approved_date.ordinal() ] = womsReqApprovedDate;
	}

	public String getWomsReqApprovedRemarks() {
		return (String) saveArray[ tableFldConstants.req_approved_remarks.ordinal() ];
	}

	public void setWomsReqApprovedRemarks(String womsReqApprovedRemarks) {
		saveArray[ tableFldConstants.req_approved_remarks.ordinal() ] = womsReqApprovedRemarks;
	}

	public String getWomsAcceptedFlag() {
		return (String) saveArray[ tableFldConstants.accepted_flag.ordinal() ];
	}

	public void setWomsAcceptedFlag(String womsAcceptedFlag) {
		saveArray[ tableFldConstants.accepted_flag.ordinal() ] = womsAcceptedFlag;
	}

	public String getWomsAcceptedDate() {
		return (String) saveArray[ tableFldConstants.accepted_date.ordinal() ];
	}

	public void setWomsAcceptedDate(String womsAcceptedDate) {
		saveArray[ tableFldConstants.accepted_date.ordinal() ] = womsAcceptedDate;
	}

	public String getWomsAcceptedBy() {
		return (String) saveArray[ tableFldConstants.accepted_by.ordinal() ];
	}

	public void setWomsAcceptedBy(String womsAcceptedBy) {
		saveArray[ tableFldConstants.accepted_by.ordinal() ] = womsAcceptedBy;
	}

	public String getWomsAcceptedRemarks() {
		return (String) saveArray[ tableFldConstants.accepted_remarks.ordinal() ];
	}

	public void setWomsAcceptedRemarks(String womsAcceptedRemarks) {
		saveArray[ tableFldConstants.accepted_remarks.ordinal() ] = womsAcceptedRemarks;
	}

	public String getWomsAllottedFlag() {
		return (String) saveArray[ tableFldConstants.allotted_flag.ordinal() ];
	}

	public void setWomsAllottedFlag(String womsAllottedFlag) {
		saveArray[ tableFldConstants.allotted_flag.ordinal() ] = womsAllottedFlag;
	}

	public String getWomsAllottedDate() {
		return (String) saveArray[ tableFldConstants.allotted_date.ordinal() ];
	}

	public void setWomsAllottedDate(String womsAllottedDate) {
		saveArray[ tableFldConstants.allotted_date.ordinal() ] = womsAllottedDate;
	}

	public String getWomsAllottedTo() {
		return (String) saveArray[ tableFldConstants.allotted_to.ordinal() ];
	}

	public void setWomsAllottedTo(String womsAllottedTo) {
		saveArray[ tableFldConstants.allotted_to.ordinal() ] = womsAllottedTo;
	}

	public String getWomsAllottedRemarks() {
		return (String) saveArray[ tableFldConstants.allotted_remarks.ordinal() ];
	}

	public void setWomsAllottedRemarks(String womsAllottedRemarks) {
		saveArray[ tableFldConstants.allotted_remarks.ordinal() ] = womsAllottedRemarks;
	}

	public String getWomsWomsstartFlag() {
		return (String) saveArray[ tableFldConstants.womsstart_flag.ordinal() ];
	}

	public void setWomsWomsstartFlag(String womsWomsstartFlag) {
		saveArray[ tableFldConstants.womsstart_flag.ordinal() ] = womsWomsstartFlag;
	}

	public String getWomsWomsstartDate() {
		return (String) saveArray[ tableFldConstants.womsstart_date.ordinal() ];
	}

	public void setWomsWomsstartDate(String womsWomsstartDate) {
		saveArray[ tableFldConstants.womsstart_date.ordinal() ] = womsWomsstartDate;
	}

	public String getWomsWomsendFlag() {
		return (String) saveArray[ tableFldConstants.womsend_flag.ordinal() ];
	}

	public void setWomsWomsendFlag(String womsWomsendFlag) {
		saveArray[ tableFldConstants.womsend_flag.ordinal() ] = womsWomsendFlag;
	}

	public String getWomsWomsendDate() {
		return (String) saveArray[ tableFldConstants.womsend_date.ordinal() ];
	}

	public void setWomsWomsendDate(String womsWomsendDate) {
		saveArray[ tableFldConstants.womsend_date.ordinal() ] = womsWomsendDate;
	}

	public String getWomsDoneBy() {
		return (String) saveArray[ tableFldConstants.done_by.ordinal() ];
	}

	public void setWomsDoneBy(String womsDoneBy) {
		saveArray[ tableFldConstants.done_by.ordinal() ] = womsDoneBy;
	}

	public String getWomsProductionStartFlag() {
		return (String) saveArray[ tableFldConstants.production_start_flag.ordinal() ];
	}

	public void setWomsProductionStartFlag(String womsProductionStartFlag) {
		saveArray[ tableFldConstants.production_start_flag.ordinal() ] = womsProductionStartFlag;
	}

	public String getWomsProductionStartDate() {
		return (String) saveArray[ tableFldConstants.production_start_date.ordinal() ];
	}

	public void setWomsProductionStartDate(String womsProductionStartDate) {
		saveArray[ tableFldConstants.production_start_date.ordinal() ] = womsProductionStartDate;
	}

	public String getWomsProductionBy() {
		return (String) saveArray[ tableFldConstants.production_by.ordinal() ];
	}

	public void setWomsProductionBy(String womsProductionBy) {
		saveArray[ tableFldConstants.production_by.ordinal() ] = womsProductionBy;
	}

	public String getWomsProductionRemarks() {
		return (String) saveArray[ tableFldConstants.production_remarks.ordinal() ];
	}

	public void setWomsProductionRemarks(String womsProductionRemarks) {
		saveArray[ tableFldConstants.production_remarks.ordinal() ] = womsProductionRemarks;
	}

	public String getWomsFinalActivitytype() {
		return (String) saveArray[ tableFldConstants.final_activitytype.ordinal() ];
	}

	public void setWomsFinalActivitytype(String womsFinalActivitytype) {
		saveArray[ tableFldConstants.final_activitytype.ordinal() ] = womsFinalActivitytype;
	}

	public String getWomsActivityid() {
		return (String) saveArray[ tableFldConstants.activityid.ordinal() ];
	}

	public void setWomsActivityid(String womsActivityid) {
		saveArray[ tableFldConstants.activityid.ordinal() ] = womsActivityid;
	}

	public String getWomsStandbyInfo() {
		return (String) saveArray[ tableFldConstants.standby_info.ordinal() ];
	}

	public void setWomsStandbyInfo(String womsStandbyInfo) {
		saveArray[ tableFldConstants.standby_info.ordinal() ] = womsStandbyInfo;
	}

	public String getWomsStandbyRemarks() {
		return (String) saveArray[ tableFldConstants.standby_remarks.ordinal() ];
	}

	public void setWomsStandbyRemarks(String womsStandbyRemarks) {
		saveArray[ tableFldConstants.standby_remarks.ordinal() ] = womsStandbyRemarks;
	}

	public String getWomsExtRepairFlag() {
		return (String) saveArray[ tableFldConstants.ext_repair_flag.ordinal() ];
	}

	public void setWomsExtRepairFlag(String womsExtRepairFlag) {
		saveArray[ tableFldConstants.ext_repair_flag.ordinal() ] = womsExtRepairFlag;
	}

	public String getWomsExtRepairid() {
		return (String) saveArray[ tableFldConstants.ext_repairid.ordinal() ];
	}

	public void setWomsExtRepairid(String womsExtRepairid) {
		saveArray[ tableFldConstants.ext_repairid.ordinal() ] = womsExtRepairid;
	}

	public String getWomsRepairRemarks() {
		return (String) saveArray[ tableFldConstants.repair_remarks.ordinal() ];
	}

	public void setWomsRepairRemarks(String womsRepairRemarks) {
		saveArray[ tableFldConstants.repair_remarks.ordinal() ] = womsRepairRemarks;
	}

	public String getWomsExtServiceFlag() {
		return (String) saveArray[ tableFldConstants.ext_service_flag.ordinal() ];
	}

	public void setWomsExtServiceFlag(String womsExtServiceFlag) {
		saveArray[ tableFldConstants.ext_service_flag.ordinal() ] = womsExtServiceFlag;
	}

	public String getWomsExtServiceid() {
		return (String) saveArray[ tableFldConstants.ext_serviceid.ordinal() ];
	}

	public void setWomsExtServiceid(String womsExtServiceid) {
		saveArray[ tableFldConstants.ext_serviceid.ordinal() ] = womsExtServiceid;
	}

	public String getWomsRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setWomsRemarks(String womsRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = womsRemarks;
	}

	public String getWomsFinalStatus() {
		return (String) saveArray[ tableFldConstants.final_status.ordinal() ];
	}

	public void setWomsFinalStatus(String womsFinalStatus) {
		saveArray[ tableFldConstants.final_status.ordinal() ] = womsFinalStatus;
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

	//sap
	public String getWomsSapnotfnFlag() {
		return (String) saveArray[ tableFldConstants.sapnotfn_flag.ordinal() ];
	}

	public void setWomsSapnotfnFlag(String womsSapnotfnFlag) {
		saveArray[ tableFldConstants.sapnotfn_flag.ordinal() ] = womsSapnotfnFlag;
	}

	public String getWomsSapnotfnType() {
		return (String) saveArray[ tableFldConstants.sapnotfn_type.ordinal() ];
	}

	public void setWomsSapnotfnType(String womsSapnotfnType) {
		saveArray[ tableFldConstants.sapnotfn_type.ordinal() ] = womsSapnotfnType;
	}
	
	public String getWomsSapnotfnNo() {
		return (String) saveArray[ tableFldConstants.sapnotfn_no.ordinal() ];
	}

	public void setWomsSapnotfnNo(String womsSapnotfnNo) {
		saveArray[ tableFldConstants.sapnotfn_no.ordinal() ] = womsSapnotfnNo;
	}


	public String getWomsSapnotfnStatus() {
		return (String) saveArray[ tableFldConstants.sapnotfn_status.ordinal() ];
	}

	public void setWomsSapnotfnStatus(String womsSapnotfnStatus) {
		saveArray[ tableFldConstants.sapnotfn_status.ordinal() ] = womsSapnotfnStatus;
	}
	
	public String getWomsSaporderFlag() {
		return (String) saveArray[ tableFldConstants.saporder_flag.ordinal() ];
	}

	public void setWomsSaporderFlag(String womsSaporderFlag) {
		saveArray[ tableFldConstants.saporder_flag.ordinal() ] = womsSaporderFlag;
	}

	public String getWomsSaporderType() {
		return (String) saveArray[ tableFldConstants.saporder_type.ordinal() ];
	}

	public void setWomsSaporderType(String womsSaporderType) {
		saveArray[ tableFldConstants.saporder_type.ordinal() ] = womsSaporderType;
	}

	public String getWomsSaporderNo() {
		return (String) saveArray[ tableFldConstants.saporder_no.ordinal() ];
	}

	public void setWomsSaporderNo(String womsSaporderNo) {
		saveArray[ tableFldConstants.saporder_no.ordinal() ] = womsSaporderNo;
	}

	public String getWomsSaporderStatus() {
		return (String) saveArray[ tableFldConstants.saporder_status.ordinal() ];
	}

	public void setWomsSaporderStatus(String womsSaporderStatus) {
		saveArray[ tableFldConstants.saporder_status.ordinal() ] = womsSaporderStatus;
	}

	public String getWomsWbsElementid() {
		return (String) saveArray[ tableFldConstants.wbs_elementid.ordinal() ];
	}

	public void setWomsWbsElementid(String wbsElementid) {
		saveArray[ tableFldConstants.wbs_elementid.ordinal() ] = wbsElementid;
	}

	
	public String getWomsRequiredStart() {
		return (String) saveArray[ tableFldConstants.required_start.ordinal() ];
	}

	public void setWomsRequiredStart(String womsrequiredStart) {
		saveArray[ tableFldConstants.required_start.ordinal() ] = womsrequiredStart;
	}

	public String getWomsRequiredEnd() {
		return (String) saveArray[ tableFldConstants.required_end.ordinal() ];
	}

	public void setWomsRequiredEnd(String womsrequiredEnd) {
		saveArray[ tableFldConstants.required_end.ordinal() ] = womsrequiredEnd;
	}

	public String getWomsSapnotfnMessage() {
		return (String) saveArray[ tableFldConstants.sapnotfn_message.ordinal() ];
	}

	public void setWomsSapnotfnMessage(String womssapnotfnMessage) {
		saveArray[ tableFldConstants.sapnotfn_message.ordinal() ] = womssapnotfnMessage;
	}

	public String getWomsSaporderMessage() {
		return (String) saveArray[ tableFldConstants.saporder_message.ordinal() ];
	}

	public void setWomsSaporderMessage(String womssaporderMessage) {
		saveArray[ tableFldConstants.saporder_message.ordinal() ] = womssaporderMessage;
	}

	public String getWomsYYrefno() {
		return (String) saveArray[ tableFldConstants.yyrefno.ordinal() ];
	}

	public void setWomsYYrefno(String womsYYrefno) {
		saveArray[ tableFldConstants.yyrefno.ordinal() ] = womsYYrefno;
	}

	public String getWomsFishbone_refno() {
		return (String) saveArray[ tableFldConstants.fishbone_refno.ordinal() ];
	}

	public void setWomsFishbone_refno(String womsFishbone_refno) {
		saveArray[ tableFldConstants.fishbone_refno.ordinal() ] = womsFishbone_refno;
	}

	public String getWomsSapOrderDate() {
		return (String) saveArray[ tableFldConstants.saporder_date.ordinal() ];
	}

	public void setWomsSapOrderDate(String womsSapOrderDate) {
		saveArray[ tableFldConstants.saporder_date.ordinal() ] = womsSapOrderDate;
	}

	
	public String getWomsProblemSeverity() {
		return (String) saveArray[ tableFldConstants.problem_severity.ordinal() ];
	}

	public void setWomsProblemSeverity(String womsproblem_severity) {
		saveArray[ tableFldConstants.problem_severity.ordinal() ] = womsproblem_severity;
	}
	
	public String getWomsStandbyEquipment() {
		return (String) saveArray[ tableFldConstants.standby_equipment.ordinal() ];
	}

	public void setWomsStandbyEquipment(String womsstandby_equipment) {
		saveArray[ tableFldConstants.standby_equipment.ordinal() ] = womsstandby_equipment;
	}
	
	public String getWomsImmediateAction() {
		return (String) saveArray[ tableFldConstants.immediate_action.ordinal() ];
	}

	public void setWomsImmediateAction(String womsimmediate_action) {
		saveArray[ tableFldConstants.immediate_action.ordinal() ] = womsimmediate_action;
	}
	
	
	public String getWomsRootCause() {
		return (String) saveArray[ tableFldConstants.root_cause.ordinal() ];
	}

	public void setWomsRootCause(String womsroot_cause) {
		saveArray[ tableFldConstants.root_cause.ordinal() ] = womsroot_cause;
	}
	
	public String getWomsCounterMeasure() {
		return (String) saveArray[ tableFldConstants.counter_measure.ordinal() ];
	}

	public void setWomsCounterMeasure(String womsCounterMeasure) {
		saveArray[ tableFldConstants.counter_measure.ordinal() ] = womsCounterMeasure;
	}

	
	public String getWomsExecutionRemarks() {
		return (String) saveArray[ tableFldConstants.execution_remarks.ordinal() ];
	}

	public void setWomsExecutionRemarks(String womsExecutionRemarks) {
		saveArray[ tableFldConstants.execution_remarks.ordinal() ] = womsExecutionRemarks;
	}
	
	public String getWomsClassificationid() {
		return (String) saveArray[ tableFldConstants.classificationid.ordinal() ];
	}

	public void setWomsClassificationid(String womsClassificationid) {
		saveArray[ tableFldConstants.classificationid.ordinal() ] = womsClassificationid;
	}
	
	public String getWomsAnalysedby() {
		return (String) saveArray[ tableFldConstants.analysedby.ordinal() ];
	}

	public void setWomsAnalysedby(String womsAnalysedby) {
		saveArray[ tableFldConstants.analysedby.ordinal() ] = womsAnalysedby;
	}
	
	public void setWomsCompletionDate(String womsCompletionDate) {
		saveArray[ tableFldConstants.completion_date.ordinal() ] = womsCompletionDate;
	}
	
	
	public String getWomsCompletionDate() {
		return (String) saveArray[ tableFldConstants.completion_date.ordinal() ];
	}

	
	public void setWomsTechnicompDate(String womsTechnicompDate) {
		saveArray[ tableFldConstants.technicomp_date.ordinal() ] = womsTechnicompDate;
	}
	
	public String getWomsTechnicompDate() {
		return (String) saveArray[ tableFldConstants.technicomp_date.ordinal() ];
	}

	public void setWomsBusicompDate(String womsBusicompDate) {
		saveArray[ tableFldConstants.busicomp_date.ordinal() ] = womsBusicompDate;
	}
	
	public String getWomsBusicompDate() {
		return (String) saveArray[ tableFldConstants.busicomp_date.ordinal() ];
	}

	public void setWomsControlKey(String womsControlKey) {
		saveArray[ tableFldConstants.controlkey.ordinal() ] = womsControlKey;
	}
	
	public String getWomsControlKey() {
		return (String) saveArray[ tableFldConstants.controlkey.ordinal() ];
	}

	public void setWomsTemp2(String womsTemp2) {
		saveArray[ tableFldConstants.temp2.ordinal() ] = womsTemp2;
	}
	
	public String getWomsTemp2() {
		return (String) saveArray[ tableFldConstants.temp2.ordinal() ];
	}


	public void setWomsTemp3(String womsTemp3) {
		saveArray[ tableFldConstants.temp3.ordinal() ] = womsTemp3;
	}
	
	public String getWomsTemp3() {
		return (String) saveArray[ tableFldConstants.temp3.ordinal() ];
	}

	public void setWomsTemp4(String womsTemp4) {
		saveArray[ tableFldConstants.temp4.ordinal() ] = womsTemp4;
	}
	
	public String getWomsTemp4() {
		return (String) saveArray[ tableFldConstants.temp4.ordinal() ];
	}

	public void setWomsTemp5(String womsTemp5) {
		saveArray[ tableFldConstants.temp5.ordinal() ] = womsTemp5;
	}
	
	public String getWomsTemp5() {
		return (String) saveArray[ tableFldConstants.temp5.ordinal() ];
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

	public String getWomsModifiedby() {
		return (String) saveArray[ tableFldConstants.modifiedby.ordinal() ];
	}

	public void setWomsModifiedby(String womsModifiedby) {
		saveArray[ tableFldConstants.modifiedby.ordinal() ] = womsModifiedby;
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

}

