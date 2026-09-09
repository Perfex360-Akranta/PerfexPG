package com.akranta.tpm.dao.sql;

public class PlmTlUnplannedmaintdtlSql {

	public static final String TBL_PLM_TL_UNPLANNEDMAINTDTL = "PLM_TL_UNPLANNEDMAINTDTL";  

	TableFieldType [] upmdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, upmm_keyid, finalphenomena, finalcause, finalaction, tradeid
		, countermeasure, wwrequired, wwno, rootcause, preventivemeasure
		, rootcauseid, countermeasureid, preventivemeasureid, breakdowntime
		, worktime, classificationid, categoryid, issparesreplaced, alarmno
		, manpowercost, contractorcost, sparescost, othercost, status
		, remarks, actiontakenby, completedby, costcentre, erppoststatus
		, problemseverity, failuretype, isapproved, approverdby, tempfield1
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getUpmdDbFields() {
		return upmdDbFields;
	}

	public PlmTlUnplannedmaintdtlSql()
	{
		upmdDbFields = new TableFieldType[ 39 ];
		for(int i = 0;i < 39; i++)
		{	
			upmdDbFields[ i ] = new TableFieldType();
		}
		upmdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "UPMD_KEYID";
		upmdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.upmm_keyid.ordinal() ].fieldName = "UPMD_UPMM_KEYID";
		upmdDbFields[ tableFldConstants.upmm_keyid.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.finalphenomena.ordinal() ].fieldName = "UPMD_FINALPHENOMENA";
		upmdDbFields[ tableFldConstants.finalphenomena.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.finalcause.ordinal() ].fieldName = "UPMD_FINALCAUSE";
		upmdDbFields[ tableFldConstants.finalcause.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.finalaction.ordinal() ].fieldName = "UPMD_FINALACTION";
		upmdDbFields[ tableFldConstants.finalaction.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "UPMD_TRADEID";
		upmdDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "UPMD_COUNTERMEASURE";
		upmdDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.wwrequired.ordinal() ].fieldName = "UPMD_WWREQUIRED";
		upmdDbFields[ tableFldConstants.wwrequired.ordinal() ].fieldType = 'C';

		upmdDbFields[ tableFldConstants.wwno.ordinal() ].fieldName = "UPMD_WWNO";
		upmdDbFields[ tableFldConstants.wwno.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "UPMD_ROOTCAUSE";
		upmdDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.preventivemeasure.ordinal() ].fieldName = "UPMD_PREVENTIVEMEASURE";
		upmdDbFields[ tableFldConstants.preventivemeasure.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.rootcauseid.ordinal() ].fieldName = "UPMD_ROOTCAUSEID";
		upmdDbFields[ tableFldConstants.rootcauseid.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.countermeasureid.ordinal() ].fieldName = "UPMD_COUNTERMEASUREID";
		upmdDbFields[ tableFldConstants.countermeasureid.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.preventivemeasureid.ordinal() ].fieldName = "UPMD_PREVENTIVEMEASUREID";
		upmdDbFields[ tableFldConstants.preventivemeasureid.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.breakdowntime.ordinal() ].fieldName = "UPMD_BREAKDOWNTIME";
		upmdDbFields[ tableFldConstants.breakdowntime.ordinal() ].fieldType = 'N';

		upmdDbFields[ tableFldConstants.worktime.ordinal() ].fieldName = "UPMD_WORKTIME";
		upmdDbFields[ tableFldConstants.worktime.ordinal() ].fieldType = 'N';

		upmdDbFields[ tableFldConstants.classificationid.ordinal() ].fieldName = "UPMD_CLASSIFICATIONID";
		upmdDbFields[ tableFldConstants.classificationid.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.categoryid.ordinal() ].fieldName = "UPMD_CATEGORYID";
		upmdDbFields[ tableFldConstants.categoryid.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.issparesreplaced.ordinal() ].fieldName = "UPMD_ISSPARESREPLACED";
		upmdDbFields[ tableFldConstants.issparesreplaced.ordinal() ].fieldType = 'C';

		upmdDbFields[ tableFldConstants.alarmno.ordinal() ].fieldName = "UPMD_ALARMNO";
		upmdDbFields[ tableFldConstants.alarmno.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldName = "UPMD_MANPOWERCOST";
		upmdDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldType = 'N';

		upmdDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldName = "UPMD_CONTRACTORCOST";
		upmdDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldType = 'N';

		upmdDbFields[ tableFldConstants.sparescost.ordinal() ].fieldName = "UPMD_SPARESCOST";
		upmdDbFields[ tableFldConstants.sparescost.ordinal() ].fieldType = 'N';

		upmdDbFields[ tableFldConstants.othercost.ordinal() ].fieldName = "UPMD_OTHERCOST";
		upmdDbFields[ tableFldConstants.othercost.ordinal() ].fieldType = 'N';

		upmdDbFields[ tableFldConstants.status.ordinal() ].fieldName = "UPMD_STATUS";
		upmdDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		upmdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "UPMD_REMARKS";
		upmdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.actiontakenby.ordinal() ].fieldName = "UPMD_ACTIONTAKENBY";
		upmdDbFields[ tableFldConstants.actiontakenby.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "UPMD_COMPLETEDBY";
		upmdDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.costcentre.ordinal() ].fieldName = "UPMD_COSTCENTRE";
		upmdDbFields[ tableFldConstants.costcentre.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.erppoststatus.ordinal() ].fieldName = "UPMD_ERPPOSTSTATUS";
		upmdDbFields[ tableFldConstants.erppoststatus.ordinal() ].fieldType = 'C';

		upmdDbFields[ tableFldConstants.problemseverity.ordinal() ].fieldName = "UPMD_PROBLEMSEVERITY";
		upmdDbFields[ tableFldConstants.problemseverity.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.failuretype.ordinal() ].fieldName = "UPMD_FAILURETYPE";
		upmdDbFields[ tableFldConstants.failuretype.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.isapproved.ordinal() ].fieldName = "UPMD_ISAPPROVED";
		upmdDbFields[ tableFldConstants.isapproved.ordinal() ].fieldType = 'C';

		upmdDbFields[ tableFldConstants.approverdby.ordinal() ].fieldName = "UPMD_APPROVERDBY";
		upmdDbFields[ tableFldConstants.approverdby.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "UPMD_TEMPFIELD1";
		upmdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "UPMD_ACTIVE";
		upmdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		upmdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "UPMD_CREATEDBY";
		upmdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		upmdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "UPMD_CREATEDON";
		upmdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		upmdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "UPMD_MODIFIEDON";
		upmdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_UNPLANNEDMAINTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_UNPLANNEDMAINTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_UNPLANNEDMAINTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

