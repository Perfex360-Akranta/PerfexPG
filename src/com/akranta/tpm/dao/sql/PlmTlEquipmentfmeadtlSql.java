package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.PlmTlProcessfmeadtlSql.tableFldConstants;

public class PlmTlEquipmentfmeadtlSql {

	public static final String TBL_PLM_TL_EQUIPMENTFMEADTL = "PLM_TL_EQUIPMENTFMEADTL";  

	TableFieldType [] fmedDbFields = null;

	public enum   tableFldConstants
	{
		keyid, fmeq_keyid, function, component, functionfail, potentialfailmode
		, potentialeffectfail, potentialcausefail, severity_keyid, occurrence_keyid
		, detection_keyid, rpn, currentcontrol, actionplan, reseverity_keyid
		, reoccurrence_keyid, redetection_keyid, rerpn, reviewby, redate
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFmedDbFields() {
		return fmedDbFields;
	}

	public PlmTlEquipmentfmeadtlSql()
	{
		fmedDbFields = new TableFieldType[ 29 ];
		for(int i = 0;i < 29; i++)
		{	
			fmedDbFields[ i ] = new TableFieldType();
		}
		fmedDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FMED_KEYID";
		fmedDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.fmeq_keyid.ordinal() ].fieldName = "FMED_FMEQ_KEYID";
		fmedDbFields[ tableFldConstants.fmeq_keyid.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.function.ordinal() ].fieldName = "FMED_FUNCTION";
		fmedDbFields[ tableFldConstants.function.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.component.ordinal() ].fieldName = "FMED_COMPONENT";
		fmedDbFields[ tableFldConstants.component.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.functionfail.ordinal() ].fieldName = "FMED_FUNCTIONFAIL";
		fmedDbFields[ tableFldConstants.functionfail.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.potentialfailmode.ordinal() ].fieldName = "FMED_POTENTIALFAILMODE";
		fmedDbFields[ tableFldConstants.potentialfailmode.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.potentialeffectfail.ordinal() ].fieldName = "FMED_POTENTIALEFFECTFAIL";
		fmedDbFields[ tableFldConstants.potentialeffectfail.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.potentialcausefail.ordinal() ].fieldName = "FMED_POTENTIALCAUSEFAIL";
		fmedDbFields[ tableFldConstants.potentialcausefail.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.severity_keyid.ordinal() ].fieldName = "FMED_SEVERITY_KEYID";
		fmedDbFields[ tableFldConstants.severity_keyid.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.occurrence_keyid.ordinal() ].fieldName = "FMED_OCCURRENCE_KEYID";
		fmedDbFields[ tableFldConstants.occurrence_keyid.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.detection_keyid.ordinal() ].fieldName = "FMED_DETECTION_KEYID";
		fmedDbFields[ tableFldConstants.detection_keyid.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.rpn.ordinal() ].fieldName = "FMED_RPN";
		fmedDbFields[ tableFldConstants.rpn.ordinal() ].fieldType = 'N';

		fmedDbFields[ tableFldConstants.currentcontrol.ordinal() ].fieldName = "FMED_CURRENTCONTROL";
		fmedDbFields[ tableFldConstants.currentcontrol.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.actionplan.ordinal() ].fieldName = "FMED_ACTIONPLAN";
		fmedDbFields[ tableFldConstants.actionplan.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.reseverity_keyid.ordinal() ].fieldName = "FMED_RESEVERITY_KEYID";
		fmedDbFields[ tableFldConstants.reseverity_keyid.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.reoccurrence_keyid.ordinal() ].fieldName = "FMED_REOCCURRENCE_KEYID";
		fmedDbFields[ tableFldConstants.reoccurrence_keyid.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.redetection_keyid.ordinal() ].fieldName = "FMED_REDETECTION_KEYID";
		fmedDbFields[ tableFldConstants.redetection_keyid.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.rerpn.ordinal() ].fieldName = "FMED_RERPN";
		fmedDbFields[ tableFldConstants.rerpn.ordinal() ].fieldType = 'N';

		fmedDbFields[ tableFldConstants.reviewby.ordinal() ].fieldName = "FMED_REVIEWBY";
		fmedDbFields[ tableFldConstants.reviewby.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.redate.ordinal() ].fieldName = "FMED_REDATE";
		fmedDbFields[ tableFldConstants.redate.ordinal() ].fieldType = 'D';

		fmedDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FMED_TEMPFIELD1";
		fmedDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		fmedDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FMED_TEMPFIELD2";
		fmedDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		fmedDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FMED_TEMPFIELD3";
		fmedDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		fmedDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FMED_TEMPFIELD4";
		fmedDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		fmedDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FMED_TEMPFIELD5";
		fmedDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		fmedDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FMED_ACTIVE";
		fmedDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fmedDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FMED_CREATEDBY";
		fmedDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fmedDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FMED_CREATEDON";
		fmedDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fmedDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FMED_MODIFIEDON";
		fmedDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_EQUIPMENTFMEADTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_EQUIPMENTFMEADTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_EQUIPMENTFMEADTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteForMasterSql(TableFieldType [] fieldTypeArr, String masterID)
	{
		String sql = "DELETE from " + TBL_PLM_TL_EQUIPMENTFMEADTL ;
		sql += " where " + fieldTypeArr[ tableFldConstants.fmeq_keyid.ordinal() ].fieldName  +
			  " = '" +  masterID + "'";
		return sql;
	}

	public String getDeleteAllSql(String fmeqKeyid) {
		// TODO Auto-generated method stub
		String sql = "DELETE from " + TBL_PLM_TL_EQUIPMENTFMEADTL ;
		sql += " where FMED_FMEQ_KEYID= '" +  fmeqKeyid + "'";
		return sql;
	}
	
	public String getUpdateReviewSql(TableFieldType [] fieldTypeArr, Object [] dataArray){
		// TODO Auto-generated method stub
		String sql = "UPDATE " + TBL_PLM_TL_EQUIPMENTFMEADTL +" SET FMED_RESEVERITY_KEYID='{}',FMED_REOCCURRENCE_KEYID='{}'," +
				"FMED_REDETECTION_KEYID='{}',FMED_RERPN='0',FMED_REVIEWBY='{}',FMED_REDATE=SYSDATE " ;
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

