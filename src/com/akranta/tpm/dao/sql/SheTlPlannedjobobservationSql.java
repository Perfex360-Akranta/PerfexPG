package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;

public class SheTlPlannedjobobservationSql {

	public static final String TBL_SHE_TL_PLANNEDJOBOBSERVATION = "SHE_TL_PLANNEDJOBOBSERVATION";  

	TableFieldType [] pjobDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, employeeid, date, location_description
		, task_observed, type, notification, reasonid, is_property_damage
		, is_efficient, is_comply, is_detrimental, corrections, deviations
		, is_complemented, is_follow_up, standard_procedure, manager
		, follow_up_action, follow_up_date, follow_up_done, practicecond
		, followuppjono,folowuptaskobserved,sopno, tempfield5, active, createdby
		, createdon, modifiedon,actionplanid
	}

	public TableFieldType[] getPjobDbFields() {
		return pjobDbFields;
	}

	public SheTlPlannedjobobservationSql()
	{
		pjobDbFields = new TableFieldType[ 33 ];
		for(int i = 0;i < 33; i++)
		{	
			pjobDbFields[ i ] = new TableFieldType();
		}
		pjobDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PJOB_KEYID";
		pjobDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "PJOB_FLID";
		pjobDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "PJOB_ELEMENTID";
		pjobDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "PJOB_EMPLOYEEID";
		pjobDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.date.ordinal() ].fieldName = "PJOB_DATE";
		pjobDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		pjobDbFields[ tableFldConstants.location_description.ordinal() ].fieldName = "PJOB_LOCATION_DESCRIPTION";
		pjobDbFields[ tableFldConstants.location_description.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.task_observed.ordinal() ].fieldName = "PJOB_TASK_OBSERVED";
		pjobDbFields[ tableFldConstants.task_observed.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.type.ordinal() ].fieldName = "PJOB_TYPE";
		pjobDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		pjobDbFields[ tableFldConstants.notification.ordinal() ].fieldName = "PJOB_NOTIFICATION";
		pjobDbFields[ tableFldConstants.notification.ordinal() ].fieldType = 'C';

		pjobDbFields[ tableFldConstants.reasonid.ordinal() ].fieldName = "PJOB_REASONID";
		pjobDbFields[ tableFldConstants.reasonid.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.is_property_damage.ordinal() ].fieldName = "PJOB_IS_PROPERTY_DAMAGE";
		pjobDbFields[ tableFldConstants.is_property_damage.ordinal() ].fieldType = 'C';

		pjobDbFields[ tableFldConstants.is_efficient.ordinal() ].fieldName = "PJOB_IS_EFFICIENT";
		pjobDbFields[ tableFldConstants.is_efficient.ordinal() ].fieldType = 'C';

		pjobDbFields[ tableFldConstants.is_comply.ordinal() ].fieldName = "PJOB_IS_COMPLY";
		pjobDbFields[ tableFldConstants.is_comply.ordinal() ].fieldType = 'C';

		pjobDbFields[ tableFldConstants.is_detrimental.ordinal() ].fieldName = "PJOB_IS_DETRIMENTAL";
		pjobDbFields[ tableFldConstants.is_detrimental.ordinal() ].fieldType = 'C';

		pjobDbFields[ tableFldConstants.corrections.ordinal() ].fieldName = "PJOB_CORRECTIONS";
		pjobDbFields[ tableFldConstants.corrections.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.deviations.ordinal() ].fieldName = "PJOB_DEVIATIONS";
		pjobDbFields[ tableFldConstants.deviations.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.is_complemented.ordinal() ].fieldName = "PJOB_IS_COMPLEMENTED";
		pjobDbFields[ tableFldConstants.is_complemented.ordinal() ].fieldType = 'C';

		pjobDbFields[ tableFldConstants.is_follow_up.ordinal() ].fieldName = "PJOB_IS_FOLLOW_UP";
		pjobDbFields[ tableFldConstants.is_follow_up.ordinal() ].fieldType = 'C';

		pjobDbFields[ tableFldConstants.standard_procedure.ordinal() ].fieldName = "PJOB_STANDARD_PROCEDURE";
		pjobDbFields[ tableFldConstants.standard_procedure.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.manager.ordinal() ].fieldName = "PJOB_MANAGER";
		pjobDbFields[ tableFldConstants.manager.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.follow_up_action.ordinal() ].fieldName = "PJOB_FOLLOW_UP_ACTION";
		pjobDbFields[ tableFldConstants.follow_up_action.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.follow_up_date.ordinal() ].fieldName = "PJOB_FOLLOW_UP_DATE";
		pjobDbFields[ tableFldConstants.follow_up_date.ordinal() ].fieldType = 'D';

		pjobDbFields[ tableFldConstants.follow_up_done.ordinal() ].fieldName = "PJOB_FOLLOW_UP_DONE";
		pjobDbFields[ tableFldConstants.follow_up_done.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.practicecond.ordinal() ].fieldName = "PJOB_PRACTICECOND";
		pjobDbFields[ tableFldConstants.practicecond.ordinal() ].fieldType = 'V';
		
		pjobDbFields[ tableFldConstants.followuppjono.ordinal() ].fieldName = "PJOB_FOLLOWUP_PJONO";
		pjobDbFields[ tableFldConstants.followuppjono.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.folowuptaskobserved.ordinal() ].fieldName = "PJOB_FOLLOWUP_TASKOBSERVED";
		pjobDbFields[ tableFldConstants.folowuptaskobserved.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.sopno.ordinal() ].fieldName = "PJOB_SOPNO";
		pjobDbFields[ tableFldConstants.sopno.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "PJOB_TEMPFIELD5";
		pjobDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PJOB_ACTIVE";
		pjobDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pjobDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PJOB_CREATEDBY";
		pjobDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pjobDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PJOB_CREATEDON";
		pjobDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pjobDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PJOB_MODIFIEDON";
		pjobDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
		pjobDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldName = "PJOB_ACTIONPLAN_ID";
		pjobDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldType = 'V';
	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_PLANNEDJOBOBSERVATION, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SHE_TL_PLANNEDJOBOBSERVATION, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_PLANNEDJOBOBSERVATION ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String select(String keyid) {
		String sql = "SELECT * from " + TBL_SHE_TL_PLANNEDJOBOBSERVATION +" WHERE PJOB_KEYID=?";
		return sql ;
	}

	public static String selectDep(CommonFilter commonFilter) {
		String sql = " SELECT SUBSTR(LISTAGG(EMPM_DEPARTMENTID||',') WITHIN GROUP(ORDER BY EMPM_DEPARTMENTID), 0 , LENGTH(LISTAGG(EMPM_DEPARTMENTID||',')  ";
		sql+=" WITHIN GROUP(ORDER BY EMPM_DEPARTMENTID))-1)  FROM GEN_TL_EMPLOYEEMST  WHERE EMPM_KEYID IN ("+commonFilter.getEmpch()+")";
		return sql;
	}
   public static String UpdateActionPlan(String pojKeyid,String aplmKeyid) throws Exception{
	   return "UPDATE SHE_TL_PLANNEDJOBOBSERVATION SET PJOB_ACTIONPLAN_ID='"+aplmKeyid+"' WHERE PJOB_KEYID='"+pojKeyid+"' ";
   }
}

