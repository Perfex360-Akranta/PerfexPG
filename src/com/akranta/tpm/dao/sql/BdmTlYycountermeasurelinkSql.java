package com.akranta.tpm.dao.sql;

public class BdmTlYycountermeasurelinkSql {

	public static final String TBL_BDM_TL_YYCOUNTERMEASURELINK = "BDM_TL_YYCOUNTERMEASURELINK";  

	TableFieldType [] yycmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, yyid, refdoctype, countermsrid, woid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifieyon
	}

	public TableFieldType[] getYycmDbFields() {
		return yycmDbFields;
	}

	public BdmTlYycountermeasurelinkSql()
	{
		yycmDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			yycmDbFields[ i ] = new TableFieldType();
		}
		yycmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "YYCM_KEYID";
		yycmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		yycmDbFields[ tableFldConstants.yyid.ordinal() ].fieldName = "YYCM_YYID";
		yycmDbFields[ tableFldConstants.yyid.ordinal() ].fieldType = 'V';

		yycmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "YYCM_REFDOCTYPE";
		yycmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'C';

		yycmDbFields[ tableFldConstants.countermsrid.ordinal() ].fieldName = "YYCM_COUNTERMSRID";
		yycmDbFields[ tableFldConstants.countermsrid.ordinal() ].fieldType = 'V';

		yycmDbFields[ tableFldConstants.woid.ordinal() ].fieldName = "YYCM_WOID";
		yycmDbFields[ tableFldConstants.woid.ordinal() ].fieldType = 'V';

		yycmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "YYCM_TEMPFIELD1";
		yycmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		yycmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "YYCM_TEMPFIELD2";
		yycmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		yycmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "YYCM_TEMPFIELD3";
		yycmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		yycmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "YYCM_TEMPFIELD4";
		yycmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		yycmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "YYCM_TEMPFIELD5";
		yycmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		yycmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "YYCM_ACTIVE";
		yycmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		yycmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "YYCM_CREATEDBY";
		yycmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'C';

		yycmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "YYCM_CREATEDON";
		yycmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		yycmDbFields[ tableFldConstants.modifieyon.ordinal() ].fieldName = "YYCM_MODIFIEYON";
		yycmDbFields[ tableFldConstants.modifieyon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_YYCOUNTERMEASURELINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_YYCOUNTERMEASURELINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_YYCOUNTERMEASURELINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteCounterMeasureLinkSql(TableFieldType [] fieldTypeArr,	String counterMsrId,String refDocType) 
	{
		String sql = "DELETE from " + TBL_BDM_TL_YYCOUNTERMEASURELINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.countermsrid.ordinal()].fieldName  +
			  " = '" +  counterMsrId + "'"+
			  "AND "+ fieldTypeArr[tableFldConstants.refdoctype.ordinal()].fieldName   +
			  " = '"+refDocType+"'" ;
		return sql;
	}
	public static String getDeleteYYSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_YYCOUNTERMEASURELINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.yyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.yyid.ordinal()] + "'" +
			  "AND "+ fieldTypeArr[tableFldConstants.refdoctype.ordinal()].fieldName   +
			  " = '" +  (String)dataArray[ tableFldConstants.refdoctype.ordinal()] + "'";
		return sql;
	}
	public static String getCMSql(String refDoc,String yy)
	{
		String sql = "SELECT YYCM_KEYID FROM "+TBL_BDM_TL_YYCOUNTERMEASURELINK+" WHERE YYCM_REFDOCTYPE='"+refDoc+"'";
			   sql += " AND YYCM_YYID='"+yy+"'";
			   return sql;
	}
}

