package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;

public class KznTlBestmstSql {

	public static final String TBL_KZN_TL_BESTMST = "KZN_TL_BESTMST";  

	TableFieldType [] kzbmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, employeeid, date, month, level, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getKzbmDbFields() {
		return kzbmDbFields;
	}

	public KznTlBestmstSql()
	{
		kzbmDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			kzbmDbFields[ i ] = new TableFieldType();
		}
		kzbmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KZBM_KEYID";
		kzbmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kzbmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "KZBM_FLID";
		kzbmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		kzbmDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "KZBM_EMPLOYEEID";
		kzbmDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		kzbmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "KZBM_DATE";
		kzbmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		kzbmDbFields[ tableFldConstants.month.ordinal() ].fieldName = "KZBM_MONTH";
		kzbmDbFields[ tableFldConstants.month.ordinal() ].fieldType = 'V';

		kzbmDbFields[ tableFldConstants.level.ordinal() ].fieldName = "KZBM_LEVEL";
		kzbmDbFields[ tableFldConstants.level.ordinal() ].fieldType = 'V';

		kzbmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KZBM_TEMPFIELD1";
		kzbmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		kzbmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KZBM_TEMPFIELD2";
		kzbmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		kzbmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KZBM_TEMPFIELD3";
		kzbmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		kzbmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KZBM_TEMPFIELD4";
		kzbmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		kzbmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KZBM_TEMPFIELD5";
		kzbmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		kzbmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KZBM_ACTIVE";
		kzbmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kzbmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KZBM_CREATEDBY";
		kzbmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kzbmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KZBM_CREATEDON";
		kzbmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kzbmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KZBM_MODIFIEDON";
		kzbmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_BESTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_BESTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_BESTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String selectmst(CommonFilter commonFilter) {
		String sql = " SELECT KZBM_KEYID,KZBM_EMPLOYEEID,TO_CHAR(KZBM_DATE,'DD-MON-YYYY'),KZBM_MONTH,KZBM_LEVEL,TO_CHAR(KZBM_CREATEDON,'DD-MON-YYYY') from " + TBL_KZN_TL_BESTMST +" WHERE 1=1 AND  KZBM_FLID = '"+commonFilter.getFlid()+"' AND TO_DATE(KZBM_MONTH,'MON-YYYY') =TO_DATE('"+commonFilter.getFromMonth()+"','MON-YYYY') AND KZBM_LEVEL= '"+commonFilter.getKznBankType()+"'";
		return sql;
	}

	public static String selectmstData() {
		String sql = "SELECT * from " + TBL_KZN_TL_BESTMST + " WHERE KZBM_KEYID= ?" ;
		return sql;
	}

}

