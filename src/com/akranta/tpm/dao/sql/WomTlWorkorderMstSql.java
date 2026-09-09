package com.akranta.tpm.dao.sql;

public class WomTlWorkorderMstSql {

	public static final String TBL_WOM_TL_WORKORDER_MST = "WOM_TL_WORKORDER_MST";  

	TableFieldType [] womsDbFields = null;

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

	public TableFieldType[] getWomsDbFields() {
		return womsDbFields;
	}

	public WomTlWorkorderMstSql()
	{
		womsDbFields = new TableFieldType[ 103 ];
		for(int i = 0;i < 103; i++)
		{	
			womsDbFields[ i ] = new TableFieldType();
		}
		womsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WOMS_KEYID";
		womsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "WOMS_ORDERNO";
		womsDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "WOMS_FLID";
		womsDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "WOMS_CELLID";
		womsDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "WOMS_MACHINEID";
		womsDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.processid.ordinal() ].fieldName = "WOMS_PROCESSID";
		womsDbFields[ tableFldConstants.processid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.womscenterid.ordinal() ].fieldName = "WOMS_WOMSCENTERID";
		womsDbFields[ tableFldConstants.womscenterid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.costcenterid.ordinal() ].fieldName = "WOMS_COSTCENTERID";
		womsDbFields[ tableFldConstants.costcenterid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.plannergroupid.ordinal() ].fieldName = "WOMS_PLANNERGROUPID";
		womsDbFields[ tableFldConstants.plannergroupid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.womsgroupid.ordinal() ].fieldName = "WOMS_WOMSGROUPID";
		womsDbFields[ tableFldConstants.womsgroupid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.occurred_date.ordinal() ].fieldName = "WOMS_OCCURRED_DATE";
		womsDbFields[ tableFldConstants.occurred_date.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "WOMS_SHIFTID";
		womsDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.shift_date.ordinal() ].fieldName = "WOMS_SHIFT_DATE";
		womsDbFields[ tableFldConstants.shift_date.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.priority.ordinal() ].fieldName = "WOMS_PRIORITY";
		womsDbFields[ tableFldConstants.priority.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.reported_date.ordinal() ].fieldName = "WOMS_REPORTED_DATE";
		womsDbFields[ tableFldConstants.reported_date.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.reported_by.ordinal() ].fieldName = "WOMS_REPORTED_BY";
		womsDbFields[ tableFldConstants.reported_by.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.is_prod_stopped.ordinal() ].fieldName = "WOMS_IS_PROD_STOPPED";
		womsDbFields[ tableFldConstants.is_prod_stopped.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.machine_condition.ordinal() ].fieldName = "WOMS_MACHINE_CONDITION";
		womsDbFields[ tableFldConstants.machine_condition.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.activity_type.ordinal() ].fieldName = "WOMS_ACTIVITY_TYPE";
		womsDbFields[ tableFldConstants.activity_type.ordinal() ].fieldType = 'V';

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

		womsDbFields[ tableFldConstants.req_approved_flag.ordinal() ].fieldName = "WOMS_REQ_APPROVED_FLAG";
		womsDbFields[ tableFldConstants.req_approved_flag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.req_approved_by.ordinal() ].fieldName = "WOMS_REQ_APPROVED_BY";
		womsDbFields[ tableFldConstants.req_approved_by.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.req_approved_date.ordinal() ].fieldName = "WOMS_REQ_APPROVED_DATE";
		womsDbFields[ tableFldConstants.req_approved_date.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.req_approved_remarks.ordinal() ].fieldName = "WOMS_REQ_APPROVED_REMARKS";
		womsDbFields[ tableFldConstants.req_approved_remarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.accepted_flag.ordinal() ].fieldName = "WOMS_ACCEPTED_FLAG";
		womsDbFields[ tableFldConstants.accepted_flag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.accepted_date.ordinal() ].fieldName = "WOMS_ACCEPTED_DATE";
		womsDbFields[ tableFldConstants.accepted_date.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.accepted_by.ordinal() ].fieldName = "WOMS_ACCEPTED_BY";
		womsDbFields[ tableFldConstants.accepted_by.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.accepted_remarks.ordinal() ].fieldName = "WOMS_ACCEPTED_REMARKS";
		womsDbFields[ tableFldConstants.accepted_remarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.allotted_flag.ordinal() ].fieldName = "WOMS_ALLOTTED_FLAG";
		womsDbFields[ tableFldConstants.allotted_flag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.allotted_date.ordinal() ].fieldName = "WOMS_ALLOTTED_DATE";
		womsDbFields[ tableFldConstants.allotted_date.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.allotted_to.ordinal() ].fieldName = "WOMS_ALLOTTED_TO";
		womsDbFields[ tableFldConstants.allotted_to.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.allotted_remarks.ordinal() ].fieldName = "WOMS_ALLOTTED_REMARKS";
		womsDbFields[ tableFldConstants.allotted_remarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.womsstart_flag.ordinal() ].fieldName = "WOMS_WOMSSTART_FLAG";
		womsDbFields[ tableFldConstants.womsstart_flag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.womsstart_date.ordinal() ].fieldName = "WOMS_WOMSSTART_DATE";
		womsDbFields[ tableFldConstants.womsstart_date.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.womsend_flag.ordinal() ].fieldName = "WOMS_WOMSEND_FLAG";
		womsDbFields[ tableFldConstants.womsend_flag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.womsend_date.ordinal() ].fieldName = "WOMS_WOMSEND_DATE";
		womsDbFields[ tableFldConstants.womsend_date.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.done_by.ordinal() ].fieldName = "WOMS_DONE_BY";
		womsDbFields[ tableFldConstants.done_by.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.production_start_flag.ordinal() ].fieldName = "WOMS_PRODUCTION_START_FLAG";
		womsDbFields[ tableFldConstants.production_start_flag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.production_start_date.ordinal() ].fieldName = "WOMS_PRODUCTION_START_DATE";
		womsDbFields[ tableFldConstants.production_start_date.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.production_by.ordinal() ].fieldName = "WOMS_PRODUCTION_BY";
		womsDbFields[ tableFldConstants.production_by.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.production_remarks.ordinal() ].fieldName = "WOMS_PRODUCTION_REMARKS";
		womsDbFields[ tableFldConstants.production_remarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.final_activitytype.ordinal() ].fieldName = "WOMS_FINAL_ACTIVITYTYPE";
		womsDbFields[ tableFldConstants.final_activitytype.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.activityid.ordinal() ].fieldName = "WOMS_ACTIVITYID";
		womsDbFields[ tableFldConstants.activityid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.standby_info.ordinal() ].fieldName = "WOMS_STANDBY_INFO";
		womsDbFields[ tableFldConstants.standby_info.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.standby_remarks.ordinal() ].fieldName = "WOMS_STANDBY_REMARKS";
		womsDbFields[ tableFldConstants.standby_remarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.ext_repair_flag.ordinal() ].fieldName = "WOMS_EXT_REPAIR_FLAG";
		womsDbFields[ tableFldConstants.ext_repair_flag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.ext_repairid.ordinal() ].fieldName = "WOMS_EXT_REPAIRID";
		womsDbFields[ tableFldConstants.ext_repairid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.repair_remarks.ordinal() ].fieldName = "WOMS_REPAIR_REMARKS";
		womsDbFields[ tableFldConstants.repair_remarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.ext_service_flag.ordinal() ].fieldName = "WOMS_EXT_SERVICE_FLAG";
		womsDbFields[ tableFldConstants.ext_service_flag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.ext_serviceid.ordinal() ].fieldName = "WOMS_EXT_SERVICEID";
		womsDbFields[ tableFldConstants.ext_serviceid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "WOMS_REMARKS";
		womsDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.final_status.ordinal() ].fieldName = "WOMS_FINAL_STATUS";
		womsDbFields[ tableFldConstants.final_status.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "WOMS_REFDOCTYPE";
		womsDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "WOMS_REFDOCID";
		womsDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.sapnotfn_flag.ordinal() ].fieldName = "WOMS_SAPNOTFN_FLAG";
		womsDbFields[ tableFldConstants.sapnotfn_flag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.sapnotfn_type.ordinal() ].fieldName = "WOMS_SAPNOTFN_TYPE";
		womsDbFields[ tableFldConstants.sapnotfn_type.ordinal() ].fieldType = 'V';
		
		womsDbFields[ tableFldConstants.sapnotfn_no.ordinal() ].fieldName = "WOMS_SAPNOTFN_NO";
		womsDbFields[ tableFldConstants.sapnotfn_no.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.sapnotfn_status.ordinal() ].fieldName = "WOMS_SAPNOTFN_STATUS";
		womsDbFields[ tableFldConstants.sapnotfn_status.ordinal() ].fieldType = 'V';

		
		womsDbFields[ tableFldConstants.saporder_flag.ordinal() ].fieldName = "WOMS_SAPORDER_FLAG";
		womsDbFields[ tableFldConstants.saporder_flag.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.saporder_type.ordinal() ].fieldName = "WOMS_SAPORDER_TYPE";
		womsDbFields[ tableFldConstants.saporder_type.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.saporder_no.ordinal() ].fieldName = "WOMS_SAPORDER_NO";
		womsDbFields[ tableFldConstants.saporder_no.ordinal() ].fieldType = 'V';
		
		womsDbFields[ tableFldConstants.saporder_status.ordinal() ].fieldName = "WOMS_SAPORDER_STATUS";
		womsDbFields[ tableFldConstants.saporder_status.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.wbs_elementid.ordinal() ].fieldName = "WOMS_WBS_ELEMENTID";
		womsDbFields[ tableFldConstants.wbs_elementid.ordinal() ].fieldType = 'V';
		
		womsDbFields[ tableFldConstants.required_start.ordinal() ].fieldName = "WOMS_REQUIRED_START";
		womsDbFields[ tableFldConstants.required_start.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.required_end.ordinal() ].fieldName = "WOMS_REQUIRED_END";
		womsDbFields[ tableFldConstants.required_end.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.sapnotfn_message.ordinal() ].fieldName = "WOMS_SAPNOTFN_MESSAGE";
		womsDbFields[ tableFldConstants.sapnotfn_message.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.saporder_message.ordinal() ].fieldName = "WOMS_SAPORDER_MESSAGE";
		womsDbFields[ tableFldConstants.saporder_message.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.yyrefno.ordinal() ].fieldName = "WOMS_YYREFNO";
		womsDbFields[ tableFldConstants.yyrefno.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.fishbone_refno.ordinal() ].fieldName = "WOMS_FISHBONE_REFNO";
		womsDbFields[ tableFldConstants.fishbone_refno.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.saporder_date.ordinal() ].fieldName = "WOMS_SAPORDER_DATE";
		womsDbFields[ tableFldConstants.saporder_date.ordinal() ].fieldType = 'D';
		
		womsDbFields[ tableFldConstants.problem_severity.ordinal() ].fieldName = "WOMS_PROBLEM_SEVERITY";
		womsDbFields[ tableFldConstants.problem_severity.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.standby_equipment.ordinal() ].fieldName = "WOMS_STANDBY_EQUIPMENT";
		womsDbFields[ tableFldConstants.standby_equipment.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.immediate_action.ordinal() ].fieldName = "WOMS_IMMEDIATE_ACTION";
		womsDbFields[ tableFldConstants.immediate_action.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.root_cause.ordinal() ].fieldName = "WOMS_ROOT_CAUSE";
		womsDbFields[ tableFldConstants.root_cause.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.counter_measure.ordinal() ].fieldName = "WOMS_COUNTER_MEASURE";
		womsDbFields[ tableFldConstants.counter_measure.ordinal() ].fieldType = 'V';
		
		womsDbFields[ tableFldConstants.execution_remarks.ordinal() ].fieldName = "WOMS_EXECUTION_REMARKS";
		womsDbFields[ tableFldConstants.execution_remarks.ordinal() ].fieldType = 'V';
		
		womsDbFields[ tableFldConstants.classificationid.ordinal() ].fieldName = "WOMS_CLASSIFICATIONID";
		womsDbFields[ tableFldConstants.classificationid.ordinal() ].fieldType = 'V';
		
		womsDbFields[ tableFldConstants.analysedby.ordinal() ].fieldName = "WOMS_ANALYSEDBY";
		womsDbFields[ tableFldConstants.analysedby.ordinal() ].fieldType = 'V';
		
		womsDbFields[ tableFldConstants.completion_date.ordinal() ].fieldName = "WOMS_COMPLETION_DATE";
		womsDbFields[ tableFldConstants.completion_date.ordinal() ].fieldType = 'D';
		
		womsDbFields[ tableFldConstants.technicomp_date.ordinal() ].fieldName = "WOMS_TECHNICOMP_DATE";
		womsDbFields[ tableFldConstants.technicomp_date.ordinal() ].fieldType = 'D';
		
		womsDbFields[ tableFldConstants.busicomp_date.ordinal() ].fieldName = "WOMS_BUSICOMP_DATE";
		womsDbFields[ tableFldConstants.busicomp_date.ordinal() ].fieldType = 'D';
		
		womsDbFields[ tableFldConstants.controlkey.ordinal() ].fieldName = "WOMS_CONTROLKEY";
		womsDbFields[ tableFldConstants.controlkey.ordinal() ].fieldType = 'V';
		
		womsDbFields[ tableFldConstants.temp2.ordinal() ].fieldName = "WOMS_TEMP2";
		womsDbFields[ tableFldConstants.temp2.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.temp3.ordinal() ].fieldName = "WOMS_TEMP3";
		womsDbFields[ tableFldConstants.temp3.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.temp4.ordinal() ].fieldName = "WOMS_TEMP4";
		womsDbFields[ tableFldConstants.temp4.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.temp5.ordinal() ].fieldName = "WOMS_TEMP5";
		womsDbFields[ tableFldConstants.temp5.ordinal() ].fieldType = 'V';

		
		
		
		womsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "WOMS_ACTIVE";
		womsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		womsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WOMS_CREATEDBY";
		womsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldName = "WOMS_MODIFIEDBY";
		womsDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldType = 'V';

		womsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WOMS_CREATEDON";
		womsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		womsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WOMS_MODIFIEDON";
		womsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOM_TL_WORKORDER_MST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOM_TL_WORKORDER_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		
		/*String sql = "DELETE from " + TBL_WOM_TL_WORKORDER_MST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
			  */
		String sql = " UPDATE " + TBL_WOM_TL_WORKORDER_MST ;
		sql += "  SET WOMS_ACTIVE  = 'N' ";
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
			 
		
		return sql;
	}
	
	public static String selectWO()
	{
		String sql = "select * from "+ TBL_WOM_TL_WORKORDER_MST+" where woms_keyid=?";
		return sql;
	}
}

