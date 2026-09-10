package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonMessage;
public class WomTlWomstSql {



	public static final String TBL_BAL_WOM_TL_WOMST = "BAL_WOM_TL_WOMST";  

	TableFieldType [] womsDbFields = null;

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
		, modifiedby, elementid, flid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getWomsDbFields() {
		return womsDbFields;
	}

	public WomTlWomstSql()
	{
		womsDbFields = new TableFieldType[ 115 ];
		for(int i = 0;i < 115; i++)
		{	
			womsDbFields[ i ] = new TableFieldType();
		}
		womsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WOMS_KEYID";
		womsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "WOMS_FACTORYID";
		womsDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "WOMS_SECTIONID";
		womsDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "WOMS_CELLID";
		womsDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "WOMS_MACHINEID";
		womsDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.directentry.ordinal() ].fieldName = "WOMS_DIRECTENTRY";
		womsDbFields[ tableFldConstants.directentry.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "WOMS_RELATEDTO";
		womsDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.loss.ordinal() ].fieldName = "WOMS_LOSS";
		womsDbFields[ tableFldConstants.loss.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.mouldid.ordinal() ].fieldName = "WOMS_MOULDID";
		womsDbFields[ tableFldConstants.mouldid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.workcenterid.ordinal() ].fieldName = "WOMS_WORKCENTERID";
		womsDbFields[ tableFldConstants.workcenterid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.costcenterid.ordinal() ].fieldName = "WOMS_COSTCENTERID";
		womsDbFields[ tableFldConstants.costcenterid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.occurreddate.ordinal() ].fieldName = "WOMS_OCCURREDDATE";
		womsDbFields[ tableFldConstants.occurreddate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "WOMS_SHIFTID";
		womsDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.shiftdate.ordinal() ].fieldName = "WOMS_SHIFTDATE";
		womsDbFields[ tableFldConstants.shiftdate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.priority.ordinal() ].fieldName = "WOMS_PRIORITY";
		womsDbFields[ tableFldConstants.priority.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.reporteddate.ordinal() ].fieldName = "WOMS_REPORTEDDATE";
		womsDbFields[ tableFldConstants.reporteddate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.reportedby.ordinal() ].fieldName = "WOMS_REPORTEDBY";
		womsDbFields[ tableFldConstants.reportedby.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.productionstop.ordinal() ].fieldName = "WOMS_PRODUCTIONSTOP";
		womsDbFields[ tableFldConstants.productionstop.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.machinecondition.ordinal() ].fieldName = "WOMS_MACHINECONDITION";
		womsDbFields[ tableFldConstants.machinecondition.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.activitytype.ordinal() ].fieldName = "WOMS_ACTIVITYTYPE";
		womsDbFields[ tableFldConstants.activitytype.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.alarmno.ordinal() ].fieldName = "WOMS_ALARMNO";
		womsDbFields[ tableFldConstants.alarmno.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "WOMS_TRADEID";
		womsDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "WOMS_ASSEMBLYID";
		womsDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.subassemblyid.ordinal() ].fieldName = "WOMS_SUBASSEMBLYID";
		womsDbFields[ tableFldConstants.subassemblyid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.failuretypeid.ordinal() ].fieldName = "WOMS_FAILURETYPEID";
		womsDbFields[ tableFldConstants.failuretypeid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.partlocation.ordinal() ].fieldName = "WOMS_PARTLOCATION";
		womsDbFields[ tableFldConstants.partlocation.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.spareid.ordinal() ].fieldName = "WOMS_SPAREID";
		womsDbFields[ tableFldConstants.spareid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldName = "WOMS_PHENOMENAID";
		womsDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.causeid.ordinal() ].fieldName = "WOMS_CAUSEID";
		womsDbFields[ tableFldConstants.causeid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.location.ordinal() ].fieldName = "WOMS_LOCATION";
		womsDbFields[ tableFldConstants.location.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.problem.ordinal() ].fieldName = "WOMS_PROBLEM";
		womsDbFields[ tableFldConstants.problem.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.bookingremarks.ordinal() ].fieldName = "WOMS_BOOKINGREMARKS";
		womsDbFields[ tableFldConstants.bookingremarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.status.ordinal() ].fieldName = "WOMS_STATUS";
		womsDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.requestapproved.ordinal() ].fieldName = "WOMS_REQUESTAPPROVED";
		womsDbFields[ tableFldConstants.requestapproved.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.requestapprovedby.ordinal() ].fieldName = "WOMS_REQUESTAPPROVEDBY";
		womsDbFields[ tableFldConstants.requestapprovedby.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.requestapproveddate.ordinal() ].fieldName = "WOMS_REQUESTAPPROVEDDATE";
		womsDbFields[ tableFldConstants.requestapproveddate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.requestapprovremarks.ordinal() ].fieldName = "WOMS_REQUESTAPPROVREMARKS";
		womsDbFields[ tableFldConstants.requestapprovremarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.accepteddate.ordinal() ].fieldName = "WOMS_ACCEPTEDDATE";
		womsDbFields[ tableFldConstants.accepteddate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.acceptedflag.ordinal() ].fieldName = "WOMS_ACCEPTEDFLAG";
		womsDbFields[ tableFldConstants.acceptedflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.acceptedby.ordinal() ].fieldName = "WOMS_ACCEPTEDBY";
		womsDbFields[ tableFldConstants.acceptedby.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.acceptedremarks.ordinal() ].fieldName = "WOMS_ACCEPTEDREMARKS";
		womsDbFields[ tableFldConstants.acceptedremarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.productionapproval.ordinal() ].fieldName = "WOMS_PRODUCTIONAPPROVAL";
		womsDbFields[ tableFldConstants.productionapproval.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.safetypermitsrequried.ordinal() ].fieldName = "WOMS_SAFETYPERMITSREQURIED";
		womsDbFields[ tableFldConstants.safetypermitsrequried.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.safetypermitid.ordinal() ].fieldName = "WOMS_SAFETYPERMITID";
		womsDbFields[ tableFldConstants.safetypermitid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.safetypermitapproved.ordinal() ].fieldName = "WOMS_SAFETYPERMITAPPROVED";
		womsDbFields[ tableFldConstants.safetypermitapproved.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.safetypermitcompleted.ordinal() ].fieldName = "WOMS_SAFETYPERMITCOMPLETED";
		womsDbFields[ tableFldConstants.safetypermitcompleted.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.safetypermitsignoff.ordinal() ].fieldName = "WOMS_SAFETYPERMITSIGNOFF";
		womsDbFields[ tableFldConstants.safetypermitsignoff.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.rescheduleflag.ordinal() ].fieldName = "WOMS_RESCHEDULEFLAG";
		womsDbFields[ tableFldConstants.rescheduleflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.rescheduledate.ordinal() ].fieldName = "WOMS_RESCHEDULEDATE";
		womsDbFields[ tableFldConstants.rescheduledate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.rescheduledremarks.ordinal() ].fieldName = "WOMS_RESCHEDULEDREMARKS";
		womsDbFields[ tableFldConstants.rescheduledremarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.allottedflag.ordinal() ].fieldName = "WOMS_ALLOTTEDFLAG";
		womsDbFields[ tableFldConstants.allottedflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.allotteddate.ordinal() ].fieldName = "WOMS_ALLOTTEDDATE";
		womsDbFields[ tableFldConstants.allotteddate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.allottedto.ordinal() ].fieldName = "WOMS_ALLOTTEDTO";
		womsDbFields[ tableFldConstants.allottedto.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.allottedremarks.ordinal() ].fieldName = "WOMS_ALLOTTEDREMARKS";
		womsDbFields[ tableFldConstants.allottedremarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.proposedstflag.ordinal() ].fieldName = "WOMS_PROPOSEDSTFLAG";
		womsDbFields[ tableFldConstants.proposedstflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.proposedendflag.ordinal() ].fieldName = "WOMS_PROPOSEDENDFLAG";
		womsDbFields[ tableFldConstants.proposedendflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.proposedstartdate.ordinal() ].fieldName = "WOMS_PROPOSEDSTARTDATE";
		womsDbFields[ tableFldConstants.proposedstartdate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.proposedenddate.ordinal() ].fieldName = "WOMS_PROPOSEDENDDATE";
		womsDbFields[ tableFldConstants.proposedenddate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.proposeddtacceptflag.ordinal() ].fieldName = "WOMS_PROPOSEDDTACCEPTFLAG";
		womsDbFields[ tableFldConstants.proposeddtacceptflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.rescheduledstflag.ordinal() ].fieldName = "WOMS_RESCHEDULEDSTFLAG";
		womsDbFields[ tableFldConstants.rescheduledstflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.rescheduledendflag.ordinal() ].fieldName = "WOMS_RESCHEDULEDENDFLAG";
		womsDbFields[ tableFldConstants.rescheduledendflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.rescheduleby.ordinal() ].fieldName = "WOMS_RESCHEDULEBY";
		womsDbFields[ tableFldConstants.rescheduleby.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.reschedulestartdate.ordinal() ].fieldName = "WOMS_RESCHEDULESTARTDATE";
		womsDbFields[ tableFldConstants.reschedulestartdate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.rescheduleenddate.ordinal() ].fieldName = "WOMS_RESCHEDULEENDDATE";
		womsDbFields[ tableFldConstants.rescheduleenddate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.rescheduleremarks.ordinal() ].fieldName = "WOMS_RESCHEDULEREMARKS";
		womsDbFields[ tableFldConstants.rescheduleremarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.productionstartflag.ordinal() ].fieldName = "WOMS_PRODUCTIONSTARTFLAG";
		womsDbFields[ tableFldConstants.productionstartflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.productionstartdate.ordinal() ].fieldName = "WOMS_PRODUCTIONSTARTDATE";
		womsDbFields[ tableFldConstants.productionstartdate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.productionby.ordinal() ].fieldName = "WOMS_PRODUCTIONBY";
		womsDbFields[ tableFldConstants.productionby.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.productionremarks.ordinal() ].fieldName = "WOMS_PRODUCTIONREMARKS";
		womsDbFields[ tableFldConstants.productionremarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.workstartflag.ordinal() ].fieldName = "WOMS_WORKSTARTFLAG";
		womsDbFields[ tableFldConstants.workstartflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.workstartdate.ordinal() ].fieldName = "WOMS_WORKSTARTDATE";
		womsDbFields[ tableFldConstants.workstartdate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.workendflag.ordinal() ].fieldName = "WOMS_WORKENDFLAG";
		womsDbFields[ tableFldConstants.workendflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.workenddate.ordinal() ].fieldName = "WOMS_WORKENDDATE";
		womsDbFields[ tableFldConstants.workenddate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.doneby.ordinal() ].fieldName = "WOMS_DONEBY";
		womsDbFields[ tableFldConstants.doneby.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.finalactivitytype.ordinal() ].fieldName = "WOMS_FINALACTIVITYTYPE";
		womsDbFields[ tableFldConstants.finalactivitytype.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.activityid.ordinal() ].fieldName = "WOMS_ACTIVITYID";
		womsDbFields[ tableFldConstants.activityid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.woapprovalflag.ordinal() ].fieldName = "WOMS_WOAPPROVALFLAG";
		womsDbFields[ tableFldConstants.woapprovalflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.woapprovalby.ordinal() ].fieldName = "WOMS_WOAPPROVALBY";
		womsDbFields[ tableFldConstants.woapprovalby.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.woapprovaldate.ordinal() ].fieldName = "WOMS_WOAPPROVALDATE";
		womsDbFields[ tableFldConstants.woapprovaldate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.machinereleaseflag.ordinal() ].fieldName = "WOMS_MACHINERELEASEFLAG";
		womsDbFields[ tableFldConstants.machinereleaseflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.machinereleaseddate.ordinal() ].fieldName = "WOMS_MACHINERELEASEDDATE";
		womsDbFields[ tableFldConstants.machinereleaseddate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.machinereleaseby.ordinal() ].fieldName = "WOMS_MACHINERELEASEBY";
		womsDbFields[ tableFldConstants.machinereleaseby.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.intorextequip.ordinal() ].fieldName = "WOMS_INTOREXTEQUIP";
		womsDbFields[ tableFldConstants.intorextequip.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.intorextequipdesc.ordinal() ].fieldName = "WOMS_INTOREXTEQUIPDESC";
		womsDbFields[ tableFldConstants.intorextequipdesc.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.standbyadditionalinfo.ordinal() ].fieldName = "WOMS_STANDBYADDITIONALINFO";
		womsDbFields[ tableFldConstants.standbyadditionalinfo.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.standbyremarks.ordinal() ].fieldName = "WOMS_STANDBYREMARKS";
		womsDbFields[ tableFldConstants.standbyremarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.sentforrepairflag.ordinal() ].fieldName = "WOMS_SENTFORREPAIRFLAG";
		womsDbFields[ tableFldConstants.sentforrepairflag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.sentrepairid.ordinal() ].fieldName = "WOMS_SENTREPAIRID";
		womsDbFields[ tableFldConstants.sentrepairid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.sentto.ordinal() ].fieldName = "WOMS_SENTTO";
		womsDbFields[ tableFldConstants.sentto.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.exceptedreturndate.ordinal() ].fieldName = "WOMS_EXCEPTEDRETURNDATE";
		womsDbFields[ tableFldConstants.exceptedreturndate.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.repairremarks.ordinal() ].fieldName = "WOMS_REPAIRREMARKS";
		womsDbFields[ tableFldConstants.repairremarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "WOMS_REMARKS";
		womsDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.jobopeningid.ordinal() ].fieldName = "WOMS_JOBOPENINGID";
		womsDbFields[ tableFldConstants.jobopeningid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.finalstatus.ordinal() ].fieldName = "WOMS_FINALSTATUS";
		womsDbFields[ tableFldConstants.finalstatus.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "WOMS_ORDERNO";
		womsDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.maintpriority.ordinal() ].fieldName = "WOMS_MAINTPRIORITY";
		womsDbFields[ tableFldConstants.maintpriority.ordinal() ].fieldType = 'N';

		womsDbFields[ tableFldConstants.allottedsource.ordinal() ].fieldName = "WOMS_ALLOTTEDSOURCE";
		womsDbFields[ tableFldConstants.allottedsource.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.allottedsupplier.ordinal() ].fieldName = "WOMS_ALLOTTEDSUPPLIER";
		womsDbFields[ tableFldConstants.allottedsupplier.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.numofactivities.ordinal() ].fieldName = "WOMS_NUMOFACTIVITIES";
		womsDbFields[ tableFldConstants.numofactivities.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.pwdmwono.ordinal() ].fieldName = "WOMS_PWDM_WONO";
		womsDbFields[ tableFldConstants.pwdmwono.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "WOMS_REFDOCTYPE";
		womsDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "WOMS_REFDOCID";
		womsDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.processid.ordinal() ].fieldName = "WOMS_PROCESSID";
		womsDbFields[ tableFldConstants.processid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.requiredstart.ordinal() ].fieldName = "WOMS_REQUIREDSTART";
		womsDbFields[ tableFldConstants.requiredstart.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.requiredend.ordinal() ].fieldName = "WOMS_REQUIREDEND";
		womsDbFields[ tableFldConstants.requiredend.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.departmentid.ordinal() ].fieldName = "WOMS_DEPARTMENTID";
		womsDbFields[ tableFldConstants.departmentid.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.plannergroup.ordinal() ].fieldName = "WOMS_PLANNERGROUP";
		womsDbFields[ tableFldConstants.plannergroup.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "WOMS_TEMPFIELD1";
		womsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldName = "WOMS_MODIFIEDBY";
		womsDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "WOMS_ELEMENTID";
		womsDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "WOMS_FLID";
		womsDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		womsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "WOMS_ACTIVE";
		womsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WOMS_CREATEDBY";
		womsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WOMS_CREATEDON";
		womsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WOMS_MODIFIEDON";
		womsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//MANO CHANGED THE TABLE NAME 
		return SqlUtils.getInsertSql(TBL_BAL_WOM_TL_WOMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//MANO CHANGED THE TABLE NAME
		String sql = SqlUtils.getUpdateSql(TBL_BAL_WOM_TL_WOMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//MANO CHANGED THE TABLE NAME
		String sql = "DELETE from " + TBL_BAL_WOM_TL_WOMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String selectWO()
	{
		//MANO CHANGED THE TABLE NAME
		String sql = "select * from "+ TBL_BAL_WOM_TL_WOMST+" where woms_keyid=?";
		return sql;
	}

	public static String getUpdateApprovalSql()
	{
		StringBuffer sb = new StringBuffer();
		//MANO CHANGED THE TABLE NAME
		sb.append("UPDATE "+ TBL_BAL_WOM_TL_WOMST+" SET WOMS_REQUESTAPPROVED = ?,WOMS_REQUESTAPPROVEDBY =?,");
		sb.append("WOMS_REQUESTAPPROVEDDATE =?,WOMS_REQUESTAPPROVREMARKS=?,WOMS_STATUS =? WHERE woms_keyid = ?");
		
		return sb.toString();
	}
	public static String getcancelWorkOrderSql()
	{
		StringBuffer sb = new StringBuffer();
		//MANO CHANGED THE TABLE NAME
		sb.append("UPDATE "+ TBL_BAL_WOM_TL_WOMST+" SET WOMS_REQUESTAPPROVED = ?,WOMS_STATUS =?,");
		sb.append("WOMS_ACTIVE =? WHERE woms_keyid = ?");
		
		return sb.toString();
	}
	public static String getUpdateCreationSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+ TBL_BAL_WOM_TL_WOMST+" SET WOMS_ACCEPTEDDATE = ?,WOMS_ACCEPTEDBY =?,");
		sb.append("WOMS_SAFETYPERMITSREQURIED =?,WOMS_Proposedstartdate = ?,WOMS_Proposedenddate = ?,");
		sb.append("WOMS_ACCEPTEDREMARKS =?,WOMS_ACCEPTEDFLAG = ?,WOMS_PROPOSEDSTFLAG = ?,WOMS_PROPOSEDENDFLAG = ?,");
		sb.append("WOMS_PRODUCTIONAPPROVAL = ?,WOMS_STATUS=? WHERE woms_keyid = ?");
		
		return sb.toString();
	}
	public static String getUpdateCreationAcceptanceSql()
	{
		StringBuffer sb = new StringBuffer();
		//MANO CHANGED THE TABLE NAME
		sb.append("UPDATE "+ TBL_BAL_WOM_TL_WOMST+" SET WOMS_RESCHEDULEDATE = ?,WOMS_RESCHEDULEBY =?,");
		sb.append("WOMS_RESCHEDULEFLAG =?,WOMS_RESCHEDULEDSTFLAG = ?,WOMS_RESCHEDULESTARTDATE = ?,");
		sb.append("WOMS_RESCHEDULEDENDFLAG =?,WOMS_RESCHEDULEENDDATE = ?,WOMS_RESCHEDULEREMARKS = ?,");
		sb.append("WOMS_STATUS=? WHERE woms_keyid = ?");
		
		return sb.toString();
	}
	public static String getUpdateAllocationSql()
	{
		StringBuffer sb = new StringBuffer();
		//MANO CHANGED THE TABLE NAME
		sb.append("UPDATE "+ TBL_BAL_WOM_TL_WOMST+" SET WOMS_ALLOTTEDDATE = ?,WOMS_ALLOTTEDTO =?,");
		sb.append("WOMS_ALLOTTEDFLAG =?,WOMS_ALLOTTEDREMARKS = ?,WOMS_ALLOTTEDSOURCE = ?,WOMS_ACTIVITYID = ?,");
		sb.append("WOMS_STATUS=?, WOMS_FINALSTATUS=?,WOMS_FINALACTIVITYTYPE=?,WOMS_MODIFIEDBY=?,WOMS_MODIFIEDON=?,WOMS_RELATEDTO = ?,WOMS_LOSS = ?,");
		sb.append("WOMS_ACCEPTEDFLAG=?,WOMS_ACCEPTEDDATE=? WHERE woms_keyid = ?");		
		return sb.toString();
	}
	public static String getUpdateCompletionSql()
	{
		StringBuffer sb = new StringBuffer();
		//sb.append("UPDATE "+ TBL_WOM_TL_WOMST+" SET WOMS_EXCEPTEDRETURNDATE = ?,WOMS_DONEBY =?,");
		//MANO CHANGED THE TABLE NAME
		sb.append("UPDATE "+ TBL_BAL_WOM_TL_WOMST+" SET WOMS_FINALACTIVITYTYPE = ?,WOMS_ACTIVITYID =?,");
		sb.append("WOMS_WORKSTARTFLAG =?,WOMS_WORKSTARTDATE=?,WOMS_WORKENDFLAG = ?,WOMS_WORKENDDATE=?,");
		sb.append("WOMS_DONEBY =?,WOMS_INTOREXTEQUIP=?,WOMS_INTOREXTEQUIPDESC = ?,");
		sb.append("WOMS_WOAPPROVALFLAG =?,WOMS_WOAPPROVALDATE=?,WOMS_WOAPPROVALBY = ?,");
		sb.append("WOMS_MACHINERELEASEFLAG =?,WOMS_MACHINERELEASEDDATE=?,WOMS_MACHINERELEASEBY = ?,");
		sb.append("WOMS_STATUS=? WHERE woms_keyid = ?");		
		return sb.toString();
	}
	public static String getUpdateProdAcceptanceSql()
	{
		StringBuffer sb = new StringBuffer();
		//MANO CHANGED THE TABLE NAME
		sb.append("UPDATE "+ TBL_BAL_WOM_TL_WOMST+" SET WOMS_PRODUCTIONSTARTDATE = ?,WOMS_PRODUCTIONBY =?,");
		sb.append("WOMS_PRODUCTIONREMARKS =?,WOMS_PRODUCTIONSTARTFLAG=?,");
		sb.append("WOMS_STATUS=?,WOMS_FINALSTATUS=?,WOMS_MODIFIEDBY=?,WOMS_MODIFIEDON=? WHERE woms_keyid = ?");		
		return sb.toString();
	}
	public static String getUpdateProdAcceptanceBDSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+ TableNames.TBL_BDM_TL_MST+" SET BDMS_WOPRODACCEPFLAG = ?,BDMS_PRODACCEPDATE =?,BDMS_DOWNTIME=?");
		sb.append(" WHERE BDMS_WNO = ?");
		return sb.toString();
	}
	public static String getUpdateProdAcceptanceUPMSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE PLM_TL_UNPLANNEDMAINTMST SET UPMM_WOPRODACCEPFLAG = ?,UPMM_PRODACCEPDATE =?,UPMM_DOWNTIME=?");
		sb.append(" WHERE UPMM_WNO = ?");
		return sb.toString();
	}
	public static String getUpdateApprovalBDSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+ TableNames.TBL_BDM_TL_MST+" SET BDMS_REMARKS = ?,Bdms_STATUS =?");
		sb.append(" WHERE BDMS_WNO = ?");
		
		return sb.toString();
	}
	public static String getUpdateApprovalBDDtlSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE BDM_TL_DTL SET Bdan_Actiontakenby = ?,Bdan_Isapproved =?,Bdan_Remarks=?,");
		sb.append("Bdan_Erppoststatus =?,Bdan_Status=?");
		sb.append(" WHERE Bdan_Bdms_keyid = ?");
		
		return sb.toString();
	}
	
	public static String inactiveWOSql(String keyId)
	{
		StringBuffer sb = new StringBuffer();
		//MANO CHANGED THE TABLE NAME
		sb.append("UPDATE "+ TBL_BAL_WOM_TL_WOMST+" SET WOMS_ACTIVE = 'N'");
		sb.append(" WHERE woms_keyid = '"+keyId+"'");
		
		return sb.toString();
	}
	public static String woInactiveSql()
	{
		StringBuffer sb = new StringBuffer();
		//MANO CHANGED THE TABLE NAME
		sb.append("UPDATE "+ TBL_BAL_WOM_TL_WOMST+" SET WOMS_ACTIVE = 'N'");
		sb.append(" WHERE woms_keyid IN(");
		
		return sb.toString();
	}
	public static String bdInactiveSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+ TableNames.TBL_BDM_TL_MST+" SET BDMS_ACTIVE = 'N'");
		sb.append(" WHERE BDMS_KEYID IN(");
		
		return sb.toString();
	}
	public static String bdDtlInactiveSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+ TableNames.TBL_BDM_TL_DTL+" SET BDAN_ACTIVE = 'N'");
		sb.append(" WHERE BDAN_BDMS_keyid IN(");
		
		return sb.toString();
	}
	
	public static String upmInactiveSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE PLM_TL_UNPLANNEDMAINTMST SET UPMM_ACTIVE = 'N'");
		sb.append(" WHERE UPMM_KEYID IN(");
		
		return sb.toString();
	}
	public static String upmDtlInactiveSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE PLM_TL_UNPLANNEDMAINTDTL SET UPMD_ACTIVE = 'N'");
		sb.append(" WHERE UPMD_UPMM_KEYID IN(");
		
		return sb.toString();
	}
	public static String maInactiveSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE PLM_TL_GENMAINTENANCE SET GMNT_ACTIVE = 'N'");
		sb.append(" WHERE GMNT_KEYID IN(");
		
		return sb.toString();
	}
	public static String MUInactiveSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE MLD_TL_MOULDUNLOADMST SET MUNL_ACTIVE = 'N'");
		sb.append(" WHERE MUNL_KEYID IN(");
		
		return sb.toString();
	}
	public static String abnInactiveSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+ TableNames.TBL_ABN_TL_ABNORMALITY+" SET ABNM_ACTIVE = 'N'");
		sb.append(" WHERE ABNM_KEYID IN(");
		
		return sb.toString();
	}
	public static String abnDtlInactiveSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+ AbnTlDtlSql.TBL_ABN_TL_DTL+" SET ABND_ACTIVE = 'N'");
		sb.append(" WHERE ABND_ABNORMALITYID IN(");
		
		return sb.toString();
	}
	
	public static String getUpdateAllocationBDSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+ TableNames.TBL_BDM_TL_MST+" SET BDMS_RECEIVEDDATE = ?, BDMS_RELATEDTO = ? ");
		sb.append(" WHERE BDMS_KEYID = ?");
		return sb.toString();
	}
	public static String getUpdateAllocationUMSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE  PLM_TL_UNPLANNEDMAINTMST SET UPMM_RECEIVEDDATE = ?,UPMM_RELATEDTO = ? ");
		sb.append(" WHERE UPMM_KEYID = ?");
		return sb.toString();
	}
	public static String getPCSFlag(String cellId,String shiftId,String occuredDate)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(*) AS CNT FROM PCS_TL_MST WHERE ");
		sb.append("PRLM_CELLID = '"+cellId+"' AND PRLM_SHIFTID = '"+shiftId+"' AND PRLM_ENTRYDATE = '"+occuredDate+"'");
		return sb.toString();
	}
	public static String insertPCSMaster()
	{
		StringBuffer sb = new StringBuffer();		
		sb.append("INSERT INTO PCS_TL_MST VALUES ");
		sb.append("(?,?,?,?,?,'{}',?,?,?,?,?,?,?, 'Y', '{}', '{}', 'N', 'S', 'T', '{}', '{}', 'Y',?,?,?)");		
		return sb.toString();
	}
	public static String insertPcsDetails(String tableName,String lossCol)
	{
		StringBuffer sb = new StringBuffer();		
		sb.append("INSERT INTO "+ tableName+"(PLDETAILSID,PLMASTERID,CELLID,MACHINEID,OPERATORS,");
		sb.append("CALENDARTIME,NOOFPRODUCTS,PRODUCTID,WNO,THEORITICALCYCLETIME,COMPLETEDFLAG,REMARKS,MODELCHANGEPART,"+lossCol+",");
		sb.append("ACTIVE,CREATEDBY,CREATEDON,MODIFIEDON) VALUES");
		sb.append("(?,?,?,?,'0','480','1',?,?,?,'P','{}','{}',?,'Y',?,?,?)");		
		return sb.toString();
	}

	public static String insertPcsWoLink()
	{
		StringBuffer sb = new StringBuffer();		
		sb.append("INSERT INTO PCS_TL_WORKORDERLINK VALUES ");
		sb.append("(?,?,?,?,?,?,?,'0','0','0','0','0','0', '{}', '{}', '{}','Y',?,?,?)");		
		return sb.toString();
	}
	public static String insertPcsLossReason()
	{
		StringBuffer sb = new StringBuffer();		
		sb.append("INSERT INTO PCS_TL_LOSSREASONLINK VALUES ");
		sb.append("(?,?,'-','-','-',?,?,'2','1',?,?,'1',?,?,?,?,'{}','{}','{}','{}','{}','Y',?,?,?)");
		return sb.toString();
	}
	public static String getPcsMaster(String cellId,String shiftid,String date)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT PRLM_KEYID FROM PCS_TL_MST WHERE PRLM_CELLID = '"+cellId+"' AND PRLM_SHIFTID = '"+shiftid+"' AND PRLM_ENTRYDATE = '"+date+"'");
		return sb.toString();
	}
	public static String isTableExists(String tableName)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT COUNT(*) FROM GEN_TL_SEQNOGEN WHERE SEQG_FORMNAME = '"+tableName+"'");
		return sb.toString();
	}
	public static String insertSeqNoGen()
	{
		StringBuffer sb = new StringBuffer();		
		sb.append("INSERT INTO GEN_TL_SEQNOGEN VALUES ");
		sb.append("(?,15,0,'PDE','YY','Y','PDE120000000000',?,2,null,null,1,1,null)");
		return sb.toString();
	}
	public static String checkPCSInsertEnable() {
		String sql = "Select BITAND(CNFM_SETTINGVALUE,1),BITAND(CNFM_SETTINGVALUE,2),BITAND(CNFM_SETTINGVALUE,4),BITAND(CNFM_SETTINGVALUE,8),BITAND(CNFM_SETTINGVALUE,16),BITAND(CNFM_SETTINGVALUE,32),CNFM_CODE FROM ADM_TL_CONFIGURATIONMST ";
		sql += "Where Cnfm_Code IN ('INSERTPCSFROMMSR','MSRACTIVITY') AND CNFM_ACTIVE='Y'";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}
	public static String checkEnableActivity() {
		String sql = "Select BITAND(CNFM_SETTINGVALUE,1),BITAND(CNFM_SETTINGVALUE,2),BITAND(CNFM_SETTINGVALUE,4),BITAND(CNFM_SETTINGVALUE,8),BITAND(CNFM_SETTINGVALUE,16),BITAND(CNFM_SETTINGVALUE,32),CNFM_CODE FROM ADM_TL_CONFIGURATIONMST ";
		sql += "Where Cnfm_Code = 'MSRACTIVITY' AND CNFM_ACTIVE='Y'";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}
	public static String checkAssmMandSql() {
		
		String sql = "SELECT CNFM_SETTINGVALUE FROM "+TableNames.TBL_ADM_TL_CONFIGURATIONMST+" WHERE CNFM_CODE='MSRACTASSMMAND'";
		return sql;
		
	}
	
	
	
	
	

}

