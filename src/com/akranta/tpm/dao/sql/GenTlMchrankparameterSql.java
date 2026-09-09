package com.akranta.tpm.dao.sql;

//import com.akranta.tpm.dao.sql.KpiTlActualSql.tableFldConstants;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlMchrankparameterSql {

	public static final String TBL_GEN_TL_MCHRANKPARAMETER = "GEN_TL_MCHRANKPARAMETER";  

	TableFieldType [] mrkpDbFields = null;

	public enum   tableFldConstants
	{
		keyid, parametername, slno, resultarea, maximummarks, isgrouped
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMrkpDbFields() {
		return mrkpDbFields;
	}

	public GenTlMchrankparameterSql()
	{
		mrkpDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			mrkpDbFields[ i ] = new TableFieldType();
		}
		mrkpDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MRKP_KEYID";
		mrkpDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mrkpDbFields[ tableFldConstants.parametername.ordinal() ].fieldName = "MRKP_PARAMETERNAME";
		mrkpDbFields[ tableFldConstants.parametername.ordinal() ].fieldType = 'V';

		mrkpDbFields[ tableFldConstants.slno.ordinal() ].fieldName = "MRKP_SLNO";
		mrkpDbFields[ tableFldConstants.slno.ordinal() ].fieldType = 'N';

		mrkpDbFields[ tableFldConstants.resultarea.ordinal() ].fieldName = "MRKP_RESULTAREA";
		mrkpDbFields[ tableFldConstants.resultarea.ordinal() ].fieldType = 'C';

		mrkpDbFields[ tableFldConstants.maximummarks.ordinal() ].fieldName = "MRKP_MAXIMUMMARKS";
		mrkpDbFields[ tableFldConstants.maximummarks.ordinal() ].fieldType = 'N';

		mrkpDbFields[ tableFldConstants.isgrouped.ordinal() ].fieldName = "MRKP_ISGROUPED";
		mrkpDbFields[ tableFldConstants.isgrouped.ordinal() ].fieldType = 'C';

		mrkpDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MRKP_ACTIVE";
		mrkpDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mrkpDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MRKP_CREATEDBY";
		mrkpDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mrkpDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MRKP_CREATEDON";
		mrkpDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mrkpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MRKP_MODIFIEDON";
		mrkpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MCHRANKPARAMETER, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MCHRANKPARAMETER, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MCHRANKPARAMETER ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getSelectSql(TableFieldType[] fieldTypeArr,
			Object[] dataArray) {
		String sql = "select MRKP_KEYID,MRKP_PARAMETERNAME,MRKP_RESULTAREA ,MRKP_MAXIMUMMARKS, MRKP_CREATEDON from " + TBL_GEN_TL_MCHRANKPARAMETER ;		
		sql += " where 1=1 " ;
		
		sql += "and " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		
		return sql;
	}

	public static String selectAllsql(CommonFilter commonFilter) {
		String sql="select MRKP_KEYID,MRKP_RESULTAREA ,MRKP_PARAMETERNAME,MRKP_MAXIMUMMARKS, MRKP_CREATEDON";
		sql+=" from GEN_TL_MCHRANKPARAMETER";
	   sql+= FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) ;
		return sql;
	}

	public static String deleteMchRankParam(String keyid) {
		return " DELETE FROM " + TBL_GEN_TL_MCHRANKPARAMETER + " WHERE MRKP_KEYID ='"+keyid+"'";
	}

}

