package com.akranta.tpm.dao.sql;

public class SheTlRiskassessmentdtlSql {

	public static final String TBL_SHE_TL_RISKASSESSMENTDTL = "SHE_TL_RISKASSESSMENTDTL";  

	TableFieldType [] rasdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, rasm_keyid, activity, consequence, hazard, cause, probablityid
		, seviorityid, riskval, risklevelid, controltypeid, controls
		, actplan, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getRasdDbFields() {
		return rasdDbFields;
	}

	public SheTlRiskassessmentdtlSql()
	{
		rasdDbFields = new TableFieldType[ 22 ];
		for(int i = 0;i < 22; i++)
		{	
			rasdDbFields[ i ] = new TableFieldType();
		}
		rasdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "RASD_KEYID";
		rasdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.rasm_keyid.ordinal() ].fieldName = "RASD_RASM_KEYID";
		rasdDbFields[ tableFldConstants.rasm_keyid.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.activity.ordinal() ].fieldName = "RASD_ACTIVITY";
		rasdDbFields[ tableFldConstants.activity.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.consequence.ordinal() ].fieldName = "RASD_CONSEQUENCE";
		rasdDbFields[ tableFldConstants.consequence.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.hazard.ordinal() ].fieldName = "RASD_HAZARD";
		rasdDbFields[ tableFldConstants.hazard.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.cause.ordinal() ].fieldName = "RASD_CAUSE";
		rasdDbFields[ tableFldConstants.cause.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.probablityid.ordinal() ].fieldName = "RASD_PROBABLITYID";
		rasdDbFields[ tableFldConstants.probablityid.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.seviorityid.ordinal() ].fieldName = "RASD_SEVIORITYID";
		rasdDbFields[ tableFldConstants.seviorityid.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.riskval.ordinal() ].fieldName = "RASD_RISKVAL";
		rasdDbFields[ tableFldConstants.riskval.ordinal() ].fieldType = 'N';

		rasdDbFields[ tableFldConstants.risklevelid.ordinal() ].fieldName = "RASD_RISKLEVELID";
		rasdDbFields[ tableFldConstants.risklevelid.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.controltypeid.ordinal() ].fieldName = "RASD_CONTROLTYPEID";
		rasdDbFields[ tableFldConstants.controltypeid.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.controls.ordinal() ].fieldName = "RASD_CONTROLS";
		rasdDbFields[ tableFldConstants.controls.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.actplan.ordinal() ].fieldName = "RASD_ACTPLAN";
		rasdDbFields[ tableFldConstants.actplan.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "RASD_TEMPFIELD1";
		rasdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "RASD_TEMPFIELD2";
		rasdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "RASD_TEMPFIELD3";
		rasdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "RASD_TEMPFIELD4";
		rasdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "RASD_TEMPFIELD5";
		rasdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "RASD_ACTIVE";
		rasdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		rasdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "RASD_CREATEDBY";
		rasdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		rasdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "RASD_CREATEDON";
		rasdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		rasdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "RASD_MODIFIEDON";
		rasdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_RISKASSESSMENTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SHE_TL_RISKASSESSMENTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_RISKASSESSMENTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

