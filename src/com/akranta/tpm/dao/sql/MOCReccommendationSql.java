package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;

public class MOCReccommendationSql {

	public static final String TBL_MOC_TL_RECCOMENDATIONS = "MOC_TL_RECCOMENDATIONS"; 
	

	TableFieldType [] mocrDbFields = null;

	public enum   tableFldConstants
	{
		keyid, masterkeyid,mocid,category,reccommend,responsibility,targetDate,status, completeDate,actionplanid
		,tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getmocrDbFields() {
		return mocrDbFields;
	}

	public MOCReccommendationSql()
	{
		mocrDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			mocrDbFields[ i ] = new TableFieldType();
		}
		mocrDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MOCR_KEYID";
		mocrDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mocrDbFields[ tableFldConstants.masterkeyid.ordinal() ].fieldName = "MOCR_WH_KEYID";
		mocrDbFields[ tableFldConstants.masterkeyid.ordinal() ].fieldType = 'V';
		
		mocrDbFields[ tableFldConstants.mocid.ordinal() ].fieldName = "MOCR_MOC_KEYID";
		mocrDbFields[ tableFldConstants.mocid.ordinal() ].fieldType = 'V';
		
		mocrDbFields[ tableFldConstants.category.ordinal() ].fieldName = "MOCR_CATEGORY";
		mocrDbFields[ tableFldConstants.category.ordinal() ].fieldType = 'V';

		mocrDbFields[ tableFldConstants.reccommend.ordinal() ].fieldName = "MOCR_RECOMMEND";
		mocrDbFields[ tableFldConstants.reccommend.ordinal() ].fieldType = 'V';

	

		mocrDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "MOCR_RESPONSIBLEID";
		mocrDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		mocrDbFields[ tableFldConstants.targetDate.ordinal() ].fieldName = "MOCR_TARGETDATE";
		mocrDbFields[ tableFldConstants.targetDate.ordinal() ].fieldType = 'D';
		
		mocrDbFields[ tableFldConstants.status.ordinal() ].fieldName = "MOCR_STATUS";
		mocrDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'V';
		
		mocrDbFields[ tableFldConstants.completeDate.ordinal() ].fieldName = "MOCR_COMPLETEDDATE";
		mocrDbFields[ tableFldConstants.completeDate.ordinal() ].fieldType = 'D';
		
		mocrDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldName = "MOCR_ACTIONPLANID";
		mocrDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldType = 'V';

		mocrDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MOCR_TEMPFIELD1";
		mocrDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mocrDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MOCR_TEMPFIELD2";
		mocrDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mocrDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MOCR_TEMPFIELD3";
		mocrDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		mocrDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MOCR_TEMPFIELD4";
		mocrDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		mocrDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MOCR_TEMPFIELD5";
		mocrDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		mocrDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MOCR_ACTIVE";
		mocrDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mocrDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MOCR_CREATEDBY";
		mocrDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mocrDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MOCR_CREATEDON";
		mocrDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mocrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MOCR_MODIFIEDON";
		mocrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql;
		 sql=SqlUtils.getInsertSql(TBL_MOC_TL_RECCOMENDATIONS, fieldTypeArr, dataArray);
		 System.out.println("Insert detail Sql Query"+sql);
		 return sql;
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MOC_TL_RECCOMENDATIONS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(String mstkeyid,TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MOC_TL_RECCOMENDATIONS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		sql +="  AND MOMD_MOMS_KEYID='"+mstkeyid+"'";
		return sql;
	}

	public static String DeleteMomRow(String keyid)
	{
		// TODO Auto-generated method stub
		String sql=" DELETE FROM " + TBL_MOC_TL_RECCOMENDATIONS + " WHERE MOMD_KEYID ='"+keyid+"'";
		CommonFunctions.debugMsg("Delete Row Sql: "+sql);
		return sql;
	}

	public static String DeleteMocRecByMoc(String keyid) 
	{
		
		String sql = "DELETE FROM " + TBL_MOC_TL_RECCOMENDATIONS;
	    sql += " WHERE MOCR_MOC_KEYID = '" + keyid + "'";
	    return sql;
	}

}

