package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;

public class MOCPssrReccommendSql {

	public static final String TBL_PSSR_TL_RECCOMENDATIONS = "PSSR_TL_RECCOMENDATIONS"; 
	

	TableFieldType [] psrrDbFields = null;

	public enum   tableFldConstants
	{
		keyid, masterkeyid,reccommend, category, responsibility,targetDate,status, completeDate,actionplanid
		,tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getpsrrDbFields() {
		return psrrDbFields;
	}

	public MOCPssrReccommendSql()
	{
		psrrDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			psrrDbFields[ i ] = new TableFieldType();
		}
		psrrDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PSRR_KEYID";
		psrrDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		psrrDbFields[ tableFldConstants.masterkeyid.ordinal() ].fieldName = "PSRR_MOC_KEYID";
		psrrDbFields[ tableFldConstants.masterkeyid.ordinal() ].fieldType = 'V';

		psrrDbFields[ tableFldConstants.reccommend.ordinal() ].fieldName = "PSRR_RECOMMEND";
		psrrDbFields[ tableFldConstants.reccommend.ordinal() ].fieldType = 'V';

		psrrDbFields[ tableFldConstants.category.ordinal() ].fieldName = "PSRR_CATEGORY";
		psrrDbFields[ tableFldConstants.category.ordinal() ].fieldType = 'V';

		psrrDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "PSRR_RESPONSIBLEID";
		psrrDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		psrrDbFields[ tableFldConstants.targetDate.ordinal() ].fieldName = "PSRR_TARGETDATE";
		psrrDbFields[ tableFldConstants.targetDate.ordinal() ].fieldType = 'D';
		
		psrrDbFields[ tableFldConstants.status.ordinal() ].fieldName = "PSRR_STATUS";
		psrrDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'V';
		
		psrrDbFields[ tableFldConstants.completeDate.ordinal() ].fieldName = "PSRR_COMPLETEDDATE";
		psrrDbFields[ tableFldConstants.completeDate.ordinal() ].fieldType = 'D';
		
		psrrDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldName = "PSRR_ACTIONPLANID";
		psrrDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldType = 'V';

		psrrDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PSRR_TEMPFIELD1";
		psrrDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		psrrDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PSRR_TEMPFIELD2";
		psrrDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		psrrDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PSRR_TEMPFIELD3";
		psrrDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		psrrDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PSRR_TEMPFIELD4";
		psrrDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		psrrDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "PSRR_TEMPFIELD5";
		psrrDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		psrrDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PSRR_ACTIVE";
		psrrDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		psrrDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PSRR_CREATEDBY";
		psrrDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		psrrDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PSRR_CREATEDON";
		psrrDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		psrrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PSRR_MODIFIEDON";
		psrrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql;
		 sql=SqlUtils.getInsertSql(TBL_PSSR_TL_RECCOMENDATIONS, fieldTypeArr, dataArray);
		 System.out.println("Insert detail Sql Query"+sql);
		 return sql;
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PSSR_TL_RECCOMENDATIONS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(String mstkeyid,TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PSSR_TL_RECCOMENDATIONS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		sql +="  AND MOMD_MOMS_KEYID='"+mstkeyid+"'";
		return sql;
	}

	public static String DeleteMomRow(String keyid)
	{
		// TODO Auto-generated method stub
		String sql=" DELETE FROM " + TBL_PSSR_TL_RECCOMENDATIONS + " WHERE MOMD_KEYID ='"+keyid+"'";
		CommonFunctions.debugMsg("Delete Row Sql: "+sql);
		return sql;
	}
 
	
	// vignesh 07sep2026
	public static String DeletePssrReccommendationRow(String keyid)
	{
	    String sql =
	        " DELETE FROM " +
	        TBL_PSSR_TL_RECCOMENDATIONS +
	        " WHERE PSRR_KEYID = '" + keyid + "'";

	    CommonFunctions.debugMsg(
	        "Delete PSSR Recommendation Sql: " + sql
	    );

	    return sql;
	}

}

