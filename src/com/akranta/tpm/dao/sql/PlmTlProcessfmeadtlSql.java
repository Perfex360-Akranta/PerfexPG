package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.PlmTlDesignfmeadtlSql.tableFldConstants;

public class PlmTlProcessfmeadtlSql {

	public static final String TBL_PLM_TL_PROCESSFMEADTL = "PLM_TL_PROCESSFMEADTL";  

	TableFieldType [] fmpdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, fmpm_keyid, processstep, keyprocessinput, potentialfailmode
		, potentialeffectfail, potentialcausefail, severity_keyid, occurrence_keyid
		, detection_keyid, rpn, currentcontrol, actionplan, reseverity_keyid
		, reoccurrence_keyid, redetection_keyid, rerpn, reviewby, redate
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFmpdDbFields() {
		return fmpdDbFields;
	}

	public PlmTlProcessfmeadtlSql()
	{
		fmpdDbFields = new TableFieldType[ 28 ];
		for(int i = 0;i < 28; i++)
		{	
			fmpdDbFields[ i ] = new TableFieldType();
		}
		fmpdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FMPD_KEYID";
		fmpdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.fmpm_keyid.ordinal() ].fieldName = "FMPD_FMPM_KEYID";
		fmpdDbFields[ tableFldConstants.fmpm_keyid.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.processstep.ordinal() ].fieldName = "FMPD_PROCESSSTEP";
		fmpdDbFields[ tableFldConstants.processstep.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.keyprocessinput.ordinal() ].fieldName = "FMPD_KEYPROCESSINPUT";
		fmpdDbFields[ tableFldConstants.keyprocessinput.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.potentialfailmode.ordinal() ].fieldName = "FMPD_POTENTIALFAILMODE";
		fmpdDbFields[ tableFldConstants.potentialfailmode.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.potentialeffectfail.ordinal() ].fieldName = "FMPD_POTENTIALEFFECTFAIL";
		fmpdDbFields[ tableFldConstants.potentialeffectfail.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.potentialcausefail.ordinal() ].fieldName = "FMPD_POTENTIALCAUSEFAIL";
		fmpdDbFields[ tableFldConstants.potentialcausefail.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.severity_keyid.ordinal() ].fieldName = "FMPD_SEVERITY_KEYID";
		fmpdDbFields[ tableFldConstants.severity_keyid.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.occurrence_keyid.ordinal() ].fieldName = "FMPD_OCCURRENCE_KEYID";
		fmpdDbFields[ tableFldConstants.occurrence_keyid.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.detection_keyid.ordinal() ].fieldName = "FMPD_DETECTION_KEYID";
		fmpdDbFields[ tableFldConstants.detection_keyid.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.rpn.ordinal() ].fieldName = "FMPD_RPN";
		fmpdDbFields[ tableFldConstants.rpn.ordinal() ].fieldType = 'N';

		fmpdDbFields[ tableFldConstants.currentcontrol.ordinal() ].fieldName = "FMPD_CURRENTCONTROL";
		fmpdDbFields[ tableFldConstants.currentcontrol.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.actionplan.ordinal() ].fieldName = "FMPD_ACTIONPLAN";
		fmpdDbFields[ tableFldConstants.actionplan.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.reseverity_keyid.ordinal() ].fieldName = "FMPD_RESEVERITY_KEYID";
		fmpdDbFields[ tableFldConstants.reseverity_keyid.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.reoccurrence_keyid.ordinal() ].fieldName = "FMPD_REOCCURRENCE_KEYID";
		fmpdDbFields[ tableFldConstants.reoccurrence_keyid.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.redetection_keyid.ordinal() ].fieldName = "FMPD_REDETECTION_KEYID";
		fmpdDbFields[ tableFldConstants.redetection_keyid.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.rerpn.ordinal() ].fieldName = "FMPD_RERPN";
		fmpdDbFields[ tableFldConstants.rerpn.ordinal() ].fieldType = 'N';

		fmpdDbFields[ tableFldConstants.reviewby.ordinal() ].fieldName = "FMPD_REVIEWBY";
		fmpdDbFields[ tableFldConstants.reviewby.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.redate.ordinal() ].fieldName = "FMPD_REDATE";
		fmpdDbFields[ tableFldConstants.redate.ordinal() ].fieldType = 'D';

		fmpdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FMPD_TEMPFIELD1";
		fmpdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		fmpdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FMPD_TEMPFIELD2";
		fmpdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		fmpdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FMPD_TEMPFIELD3";
		fmpdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		fmpdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FMPD_TEMPFIELD4";
		fmpdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		fmpdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FMPD_TEMPFIELD5";
		fmpdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		fmpdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FMPD_ACTIVE";
		fmpdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fmpdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FMPD_CREATEDBY";
		fmpdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fmpdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FMPD_CREATEDON";
		fmpdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fmpdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FMPD_MODIFIEDON";
		fmpdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_PROCESSFMEADTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_PROCESSFMEADTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_PROCESSFMEADTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteForMasterSql(TableFieldType [] fieldTypeArr, String masterID)
	{
		String sql = "DELETE from " + TBL_PLM_TL_PROCESSFMEADTL ;
		sql += " where " + fieldTypeArr[ tableFldConstants.fmpm_keyid.ordinal() ].fieldName  +
			  " = '" +  masterID + "'";
		return sql;
	}

	public String getDeleteSql(String fmpmKeyid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql = "DELETE from " + TBL_PLM_TL_PROCESSFMEADTL ;
		sql += " where FMPD_FMPM_KEYID= '" +  fmpmKeyid + "'";
		return sql;
	}
	public String getUpdateReviewSql(TableFieldType [] fieldTypeArr, Object [] dataArray){
		// TODO Auto-generated method stub
		String sql = "UPDATE " + TBL_PLM_TL_PROCESSFMEADTL +" SET FMPD_RESEVERITY_KEYID='{}',FMPD_REOCCURRENCE_KEYID='{}'," +
				"FMPD_REDETECTION_KEYID='{}',FMPD_RERPN='0',FMPD_REVIEWBY='{}',FMPD_REDATE=SYSDATE " ;
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}



}

