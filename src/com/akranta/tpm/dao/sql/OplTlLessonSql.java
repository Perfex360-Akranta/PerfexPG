package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.OplTlMstSql.tableFldConstants;
import com.akranta.tpm.utils.CommonMessage;
public class OplTlLessonSql {

	public static final String TBL_OPL_TL_LESSON = "OPL_TL_LESSON";  

	TableFieldType [] opllDbFields = null;

	public enum   tableFldConstants
	{
		oplid, employeeid, date, active
	}

	public TableFieldType[] getOpllDbFields() {
		return opllDbFields;
	}
	

	public OplTlLessonSql()
	{
		opllDbFields = new TableFieldType[ 4 ];
		for(int i = 0;i < 4; i++)
		{	
			opllDbFields[ i ] = new TableFieldType();
		}
	
		
		opllDbFields[ tableFldConstants.oplid.ordinal() ].fieldName = "OPLL_OPLID";
		opllDbFields[ tableFldConstants.oplid.ordinal() ].fieldType = 'V';

		opllDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "OPLL_EMPLOYEEID";
		opllDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		opllDbFields[ tableFldConstants.date.ordinal() ].fieldName = "OPLL_DATE";
		opllDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		opllDbFields[ tableFldConstants.active.ordinal() ].fieldName = "OPLL_ACTIVE";
		opllDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';
		

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{	
		CommonMessage.debugMsg("Inside sqllessonmst");
		return SqlUtils.getInsertSql(TBL_OPL_TL_LESSON, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_OPL_TL_LESSON, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.oplid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.oplid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, String oplId)
	{
		String sql = "DELETE from " + TBL_OPL_TL_LESSON ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.oplid.ordinal()].fieldName  +
			  " = '" + oplId + "'";
		return sql;
	}
	
		/*	public static String getDeleteSql(String OpllOplid)
	{
		CommonMessage.debugMsg("OpllOplid OpllOplid"+OpllOplid);
		String sql = "DELETE from " + TBL_OPL_TL_LESSON + " where OPLL_OPLID = ?";
		
		//sql += " where " + fieldTypeArr[tableFldConstants.oplid.ordinal()].fieldName  +
		//	  " = '" +  (String)dataArray[ tableFldConstants.oplid.ordinal()] + "'";
		return sql;
	}
*/	
	public static String getInsertIntoLessonSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{	
		CommonMessage.debugMsg("Inside sqllessonmst");
		return SqlUtils.getInsertSql(TBL_OPL_TL_LESSON, fieldTypeArr, dataArray);
	}





}

