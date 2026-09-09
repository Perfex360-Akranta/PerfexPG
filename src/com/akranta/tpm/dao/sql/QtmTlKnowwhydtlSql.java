package com.akranta.tpm.dao.sql;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class QtmTlKnowwhydtlSql {

	public static final String TBL_QTM_TL_KNOWWHYDTL = "QTM_TL_KNOWWHYDTL";  

	TableFieldType [] knwdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, knwm_keyid, possiblecauses, knowwhy, solution, normalcondition
		, sustenanceaction, tempfield2, tempfield3, tempfield4, tempfield5
		, createdby, active, createdon, modifiedon
	}

	public TableFieldType[] getKnwdDbFields() {
		return knwdDbFields;
	}

	public QtmTlKnowwhydtlSql()
	{
		knwdDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			knwdDbFields[ i ] = new TableFieldType();
		}
		knwdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KNWD_KEYID";
		knwdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		knwdDbFields[ tableFldConstants.knwm_keyid.ordinal() ].fieldName = "KNWD_KNWM_KEYID";
		knwdDbFields[ tableFldConstants.knwm_keyid.ordinal() ].fieldType = 'V';

		knwdDbFields[ tableFldConstants.possiblecauses.ordinal() ].fieldName = "KNWD_POSSIBLECAUSES";
		knwdDbFields[ tableFldConstants.possiblecauses.ordinal() ].fieldType = 'V';

		knwdDbFields[ tableFldConstants.knowwhy.ordinal() ].fieldName = "KNWD_KNOWWHY";
		knwdDbFields[ tableFldConstants.knowwhy.ordinal() ].fieldType = 'V';

		knwdDbFields[ tableFldConstants.solution.ordinal() ].fieldName = "KNWD_SOLUTION";
		knwdDbFields[ tableFldConstants.solution.ordinal() ].fieldType = 'V';

		knwdDbFields[ tableFldConstants.normalcondition.ordinal() ].fieldName = "KNWD_NORMALCONDITION";
		knwdDbFields[ tableFldConstants.normalcondition.ordinal() ].fieldType = 'V';

		knwdDbFields[ tableFldConstants.sustenanceaction.ordinal() ].fieldName = "KNWD_SUSTENANCEACTION";
		knwdDbFields[ tableFldConstants.sustenanceaction.ordinal() ].fieldType = 'V';

		knwdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KNWD_TEMPFIELD2";
		knwdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		knwdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KNWD_TEMPFIELD3";
		knwdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		knwdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KNWD_TEMPFIELD4";
		knwdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		knwdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KNWD_TEMPFIELD5";
		knwdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		knwdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KNWD_CREATEDBY";
		knwdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		knwdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KNWD_ACTIVE";
		knwdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		knwdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KNWD_CREATEDON";
		knwdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		knwdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KNWD_MODIFIEDON";
		knwdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_KNOWWHYDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_KNOWWHYDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_KNOWWHYDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteForMasterSql(TableFieldType [] fieldTypeArr, String masterID)
	{
		String sql = "DELETE from " + TBL_QTM_TL_KNOWWHYDTL ;
		sql += " where " + fieldTypeArr[ tableFldConstants.knwm_keyid.ordinal() ].fieldName  +
			  " = '" +  masterID + "'";
		return sql;
	}

	public static String getDelete(String dtlkeyid) {
		// TODO Auto-generated method stub
		String Sql=" DELETE FROM " + TBL_QTM_TL_KNOWWHYDTL + " WHERE KNWD_KEYID = '"+dtlkeyid+"'";
		CommonMessage.debugMsg(Sql);
		CommonMessage.debugMsg(" after"+dtlkeyid);
		return Sql;
	}

		
}



