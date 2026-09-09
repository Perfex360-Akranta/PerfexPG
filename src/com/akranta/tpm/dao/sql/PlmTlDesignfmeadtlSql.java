package com.akranta.tpm.dao.sql;

public class PlmTlDesignfmeadtlSql {

	public static final String TBL_PLM_TL_DESIGNFMEADTL = "PLM_TL_DESIGNFMEADTL";  

	TableFieldType [] fmddDbFields = null;

	public enum   tableFldConstants
	{
		keyid, fmdm_keyid, item, function, potentialfailmode, potentialeffectfail
		, potentialcausefail, severity_keyid, occurrence_keyid, detection_keyid
		, rpn, currentcontrol, actionplan, reseverity_keyid, reoccurrence_keyid
		, redetection_keyid, rerpn, reviewby, redate, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getFmddDbFields() {
		return fmddDbFields;
	}

	public PlmTlDesignfmeadtlSql()
	{
		fmddDbFields = new TableFieldType[ 28 ];
		for(int i = 0;i < 28; i++)
		{	
			fmddDbFields[ i ] = new TableFieldType();
		}
		fmddDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FMDD_KEYID";
		fmddDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.fmdm_keyid.ordinal() ].fieldName = "FMDD_FMDM_KEYID";
		fmddDbFields[ tableFldConstants.fmdm_keyid.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.item.ordinal() ].fieldName = "FMDD_ITEM";
		fmddDbFields[ tableFldConstants.item.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.function.ordinal() ].fieldName = "FMDD_FUNCTION";
		fmddDbFields[ tableFldConstants.function.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.potentialfailmode.ordinal() ].fieldName = "FMDD_POTENTIALFAILMODE";
		fmddDbFields[ tableFldConstants.potentialfailmode.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.potentialeffectfail.ordinal() ].fieldName = "FMDD_POTENTIALEFFECTFAIL";
		fmddDbFields[ tableFldConstants.potentialeffectfail.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.potentialcausefail.ordinal() ].fieldName = "FMDD_POTENTIALCAUSEFAIL";
		fmddDbFields[ tableFldConstants.potentialcausefail.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.severity_keyid.ordinal() ].fieldName = "FMDD_SEVERITY_KEYID";
		fmddDbFields[ tableFldConstants.severity_keyid.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.occurrence_keyid.ordinal() ].fieldName = "FMDD_OCCURRENCE_KEYID";
		fmddDbFields[ tableFldConstants.occurrence_keyid.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.detection_keyid.ordinal() ].fieldName = "FMDD_DETECTION_KEYID";
		fmddDbFields[ tableFldConstants.detection_keyid.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.rpn.ordinal() ].fieldName = "FMDD_RPN";
		fmddDbFields[ tableFldConstants.rpn.ordinal() ].fieldType = 'N';

		fmddDbFields[ tableFldConstants.currentcontrol.ordinal() ].fieldName = "FMDD_CURRENTCONTROL";
		fmddDbFields[ tableFldConstants.currentcontrol.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.actionplan.ordinal() ].fieldName = "FMDD_ACTIONPLAN";
		fmddDbFields[ tableFldConstants.actionplan.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.reseverity_keyid.ordinal() ].fieldName = "FMDD_RESEVERITY_KEYID";
		fmddDbFields[ tableFldConstants.reseverity_keyid.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.reoccurrence_keyid.ordinal() ].fieldName = "FMDD_REOCCURRENCE_KEYID";
		fmddDbFields[ tableFldConstants.reoccurrence_keyid.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.redetection_keyid.ordinal() ].fieldName = "FMDD_REDETECTION_KEYID";
		fmddDbFields[ tableFldConstants.redetection_keyid.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.rerpn.ordinal() ].fieldName = "FMDD_RERPN";
		fmddDbFields[ tableFldConstants.rerpn.ordinal() ].fieldType = 'N';

		fmddDbFields[ tableFldConstants.reviewby.ordinal() ].fieldName = "FMDD_REVIEWBY";
		fmddDbFields[ tableFldConstants.reviewby.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.redate.ordinal() ].fieldName = "FMDD_REDATE";
		fmddDbFields[ tableFldConstants.redate.ordinal() ].fieldType = 'D';

		fmddDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FMDD_TEMPFIELD1";
		fmddDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		fmddDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FMDD_TEMPFIELD2";
		fmddDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		fmddDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FMDD_TEMPFIELD3";
		fmddDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		fmddDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FMDD_TEMPFIELD4";
		fmddDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		fmddDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FMDD_TEMPFIELD5";
		fmddDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		fmddDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FMDD_ACTIVE";
		fmddDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fmddDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FMDD_CREATEDBY";
		fmddDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fmddDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FMDD_CREATEDON";
		fmddDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fmddDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FMDD_MODIFIEDON";
		fmddDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_DESIGNFMEADTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_DESIGNFMEADTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_DESIGNFMEADTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteForMasterSql(TableFieldType [] fieldTypeArr, String masterID)
	{
		String sql = "DELETE from " + TBL_PLM_TL_DESIGNFMEADTL ;
		sql += " where " + fieldTypeArr[ tableFldConstants.fmdm_keyid.ordinal() ].fieldName  +
			  " = '" +  masterID + "'";
		return sql;
	}

	public String getDeleteAllSql(String fmdmKeyid) {
		// TODO Auto-generated method stub
		String sql = "DELETE from " + TBL_PLM_TL_DESIGNFMEADTL ;
		sql += " where FMDD_FMDM_KEYID= '" +  fmdmKeyid + "'";
		return sql;
	}

	public String getUpdateReviewSql(TableFieldType [] fieldTypeArr, Object [] dataArray){
		// TODO Auto-generated method stub
		String sql = "UPDATE " + TBL_PLM_TL_DESIGNFMEADTL +" SET FMDD_RESEVERITY_KEYID='{}',FMDD_REOCCURRENCE_KEYID='{}'," +
				"FMDD_REDETECTION_KEYID='{}',FMDD_RERPN='0',FMDD_REVIEWBY='{}',FMDD_REDATE=SYSDATE " ;
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}


}

