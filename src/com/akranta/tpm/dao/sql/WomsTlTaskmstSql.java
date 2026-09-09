package com.akranta.tpm.dao.sql;

public class WomsTlTaskmstSql {

	public static final String TBL_WOMS_TL_TASKMST = "WOMS_TL_TASKMST";  

	TableFieldType [] wtmsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, task, woms_keyid, job_type, frequency, assemblyid, status
		, observation, responsibility, target_date, planned_duration
		, actual_duration, action_taken, cbm_reading, zone_color, cbm_action
		, adjusted_reading, next_due_date, remarks, ideal_condition_type
		, ideal_condition, actual_condition, noti_refno, noti_status
		, woms_refno, woms_status, extserviceflag, extrepairflag, activityno, actionplanid, temp5
		, temp6, temp7, temp8, temp9, createdby, createdon, modifiedon
	}

	public TableFieldType[] getWtmsDbFields() {
		return wtmsDbFields;
	}

	public WomsTlTaskmstSql()
	{
		wtmsDbFields = new TableFieldType[ 38 ];
		for(int i = 0;i < 38; i++)
		{	
			wtmsDbFields[ i ] = new TableFieldType();
		}
		wtmsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WTMS_KEYID";
		wtmsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.task.ordinal() ].fieldName = "WTMS_TASK";
		wtmsDbFields[ tableFldConstants.task.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.woms_keyid.ordinal() ].fieldName = "WTMS_WOMS_KEYID";
		wtmsDbFields[ tableFldConstants.woms_keyid.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.job_type.ordinal() ].fieldName = "WTMS_JOB_TYPE";
		wtmsDbFields[ tableFldConstants.job_type.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.frequency.ordinal() ].fieldName = "WTMS_FREQUENCY";
		wtmsDbFields[ tableFldConstants.frequency.ordinal() ].fieldType = 'N';

		wtmsDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "WTMS_ASSEMBLYID";
		wtmsDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.status.ordinal() ].fieldName = "WTMS_STATUS";
		wtmsDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.observation.ordinal() ].fieldName = "WTMS_OBSERVATION";
		wtmsDbFields[ tableFldConstants.observation.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "WTMS_RESPONSIBILITY";
		wtmsDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.target_date.ordinal() ].fieldName = "WTMS_TARGET_DATE";
		wtmsDbFields[ tableFldConstants.target_date.ordinal() ].fieldType = 'D';

		wtmsDbFields[ tableFldConstants.planned_duration.ordinal() ].fieldName = "WTMS_PLANNED_DURATION";
		wtmsDbFields[ tableFldConstants.planned_duration.ordinal() ].fieldType = 'N';

		wtmsDbFields[ tableFldConstants.actual_duration.ordinal() ].fieldName = "WTMS_ACTUAL_DURATION";
		wtmsDbFields[ tableFldConstants.actual_duration.ordinal() ].fieldType = 'N';

		wtmsDbFields[ tableFldConstants.action_taken.ordinal() ].fieldName = "WTMS_ACTION_TAKEN";
		wtmsDbFields[ tableFldConstants.action_taken.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.cbm_reading.ordinal() ].fieldName = "WTMS_CBM_READING";
		wtmsDbFields[ tableFldConstants.cbm_reading.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.zone_color.ordinal() ].fieldName = "WTMS_ZONE_COLOR";
		wtmsDbFields[ tableFldConstants.zone_color.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.cbm_action.ordinal() ].fieldName = "WTMS_CBM_ACTION";
		wtmsDbFields[ tableFldConstants.cbm_action.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.adjusted_reading.ordinal() ].fieldName = "WTMS_ADJUSTED_READING";
		wtmsDbFields[ tableFldConstants.adjusted_reading.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.next_due_date.ordinal() ].fieldName = "WTMS_NEXT_DUE_DATE";
		wtmsDbFields[ tableFldConstants.next_due_date.ordinal() ].fieldType = 'D';

		wtmsDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "WTMS_REMARKS";
		wtmsDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.ideal_condition_type.ordinal() ].fieldName = "WTMS_IDEAL_CONDITION_TYPE";
		wtmsDbFields[ tableFldConstants.ideal_condition_type.ordinal() ].fieldType = 'C';

		wtmsDbFields[ tableFldConstants.ideal_condition.ordinal() ].fieldName = "WTMS_IDEAL_CONDITION";
		wtmsDbFields[ tableFldConstants.ideal_condition.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.actual_condition.ordinal() ].fieldName = "WTMS_ACTUAL_CONDITION";
		wtmsDbFields[ tableFldConstants.actual_condition.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.noti_refno.ordinal() ].fieldName = "WTMS_NOTI_REFNO";
		wtmsDbFields[ tableFldConstants.noti_refno.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.noti_status.ordinal() ].fieldName = "WTMS_NOTI_STATUS";
		wtmsDbFields[ tableFldConstants.noti_status.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.woms_refno.ordinal() ].fieldName = "WTMS_WOMS_REFNO";
		wtmsDbFields[ tableFldConstants.woms_refno.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.woms_status.ordinal() ].fieldName = "WTMS_WOMS_STATUS";
		wtmsDbFields[ tableFldConstants.woms_status.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.extrepairflag.ordinal() ].fieldName = "WTMS_EXTREPAIR_FLAG";
		wtmsDbFields[ tableFldConstants.extrepairflag.ordinal() ].fieldType = 'C';

		wtmsDbFields[ tableFldConstants.extserviceflag.ordinal() ].fieldName = "WTMS_EXTSERVICE_FLAG";
		wtmsDbFields[ tableFldConstants.extserviceflag.ordinal() ].fieldType = 'C';

		wtmsDbFields[ tableFldConstants.activityno.ordinal() ].fieldName = "WTMS_ACTIVITYNO";
		wtmsDbFields[ tableFldConstants.activityno.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldName = "WTMS_ACTIONPLANID";
		wtmsDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.temp5.ordinal() ].fieldName = "WTMS_TEMP5";
		wtmsDbFields[ tableFldConstants.temp5.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.temp6.ordinal() ].fieldName = "WTMS_TEMP6";
		wtmsDbFields[ tableFldConstants.temp6.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.temp7.ordinal() ].fieldName = "WTMS_TEMP7";
		wtmsDbFields[ tableFldConstants.temp7.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.temp8.ordinal() ].fieldName = "WTMS_TEMP8";
		wtmsDbFields[ tableFldConstants.temp8.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.temp9.ordinal() ].fieldName = "WTMS_TEMP9";
		wtmsDbFields[ tableFldConstants.temp9.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WTMS_CREATEDBY";
		wtmsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wtmsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WTMS_CREATEDON";
		wtmsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wtmsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WTMS_MODIFIEDON";
		wtmsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOMS_TL_TASKMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOMS_TL_TASKMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_WOMS_TL_TASKMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

