package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class StdTlStdworksheetmstSql {

	public static final String TBL_STD_TL_STDWORKSHEETMST = "STD_TL_STDWORKSHEETMST";  

	TableFieldType [] stwsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, date, by, approvedby, flid, elementid, process, budgetedtime
		, type, cycletime, tempfield3, tempfield4, tempfield5
		, createdby, active, createdon, modifiedon
	}

	public TableFieldType[] getStwsDbFields() {
		return stwsDbFields;
	}

	public StdTlStdworksheetmstSql()
	{
		stwsDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			stwsDbFields[ i ] = new TableFieldType();
		}
		stwsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "STWS_KEYID";
		stwsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.date.ordinal() ].fieldName = "STWS_DATE";
		stwsDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		stwsDbFields[ tableFldConstants.by.ordinal() ].fieldName = "STWS_BY";
		stwsDbFields[ tableFldConstants.by.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "STWS_APPROVEDBY";
		stwsDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "STWS_FLID";
		stwsDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "STWS_ELEMENTID";
		stwsDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.process.ordinal() ].fieldName = "STWS_PROCESS";
		stwsDbFields[ tableFldConstants.process.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.budgetedtime.ordinal() ].fieldName = "STWS_BUDGETEDTIME";
		stwsDbFields[ tableFldConstants.budgetedtime.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.type.ordinal() ].fieldName = "STWS_TYPE";
		stwsDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.cycletime.ordinal() ].fieldName = "STWS_CYCLETIME";
		stwsDbFields[ tableFldConstants.cycletime.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "STWS_TEMPFIELD3";
		stwsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "STWS_TEMPFIELD4";
		stwsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "STWS_TEMPFIELD5";
		stwsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "STWS_CREATEDBY";
		stwsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		stwsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "STWS_ACTIVE";
		stwsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		stwsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "STWS_CREATEDON";
		stwsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		stwsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "STWS_MODIFIEDON";
		stwsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_STD_TL_STDWORKSHEETMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_STD_TL_STDWORKSHEETMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_STD_TL_STDWORKSHEETMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String selectmaster() {
		
		String sql = "";
		/*sql = "	SELECT STWS_KEYID   AS KEYID, TO_CHAR(STWS_DATE ,'DD-MON-YYYY') as dates ,STWS_BY   AS Bys ," +
				" STWS_APPROVEDBY AS Approved,STWS_FLID as  functionl,STWS_ELEMENTID as elem, STWS_PROCESS      AS PROCESS ," +
				" STWS_BUDGETEDTIME AS Budgettime FROM std_tl_stdworksheetmst WHERE STWS_KEYID = ? ";*/
		sql = "SELECT * FROM std_tl_stdworksheetmst WHERE STWS_KEYID = ?";
		return sql;
		
	}

	public static String selectData(String keyid) {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg(" Inside SQL File :: "+keyid);
		
		String sql= " select QPOM_BUDGETTIME ";
		sql+= " from QTM_TL_PROCESSMST " ;
		sql+= " where QPOM_KEYID='"+keyid+"'";
	    return sql;
	}

}

