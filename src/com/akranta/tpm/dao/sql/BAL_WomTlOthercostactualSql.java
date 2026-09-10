package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_WomTlUtilitycostactualSql.tableFldConstants;

public class BAL_WomTlOthercostactualSql {

	public static final String TBL_WOM_TL_OTHERCOSTACTUAL = "WOM_TL_OTHERCOSTACTUAL";  

	TableFieldType [] otcdDbFields = null;

	public enum   tableFldConstants
	{
		woid, doctype, othercostmstid, requestedby, amount, remarks, date
		, tempfield1, tempfield2, tempfield3, tempfield4, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getOtcdDbFields() {
		return otcdDbFields;
	}

	public BAL_WomTlOthercostactualSql()
	{
		otcdDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			otcdDbFields[ i ] = new TableFieldType();
		}
		otcdDbFields[ tableFldConstants.woid.ordinal() ].fieldName = "OTCD_WOID";
		otcdDbFields[ tableFldConstants.woid.ordinal() ].fieldType = 'V';

		otcdDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "OTCD_DOCTYPE";
		otcdDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		otcdDbFields[ tableFldConstants.othercostmstid.ordinal() ].fieldName = "OTCD_OTHERCOSTMSTID";
		otcdDbFields[ tableFldConstants.othercostmstid.ordinal() ].fieldType = 'V';

		otcdDbFields[ tableFldConstants.requestedby.ordinal() ].fieldName = "OTCD_REQUESTEDBY";
		otcdDbFields[ tableFldConstants.requestedby.ordinal() ].fieldType = 'V';

		otcdDbFields[ tableFldConstants.amount.ordinal() ].fieldName = "OTCD_AMOUNT";
		otcdDbFields[ tableFldConstants.amount.ordinal() ].fieldType = 'N';

		otcdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "OTCD_REMARKS";
		otcdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		otcdDbFields[ tableFldConstants.date.ordinal() ].fieldName = "OTCD_DATE";
		otcdDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		otcdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "OTCD_TEMPFIELD1";
		otcdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		otcdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "OTCD_TEMPFIELD2";
		otcdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		otcdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "OTCD_TEMPFIELD3";
		otcdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		otcdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "OTCD_TEMPFIELD4";
		otcdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		otcdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OTCD_CREATEDBY";
		otcdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		otcdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OTCD_CREATEDON";
		otcdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		otcdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "OTCD_MODIFIEDON";
		otcdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOM_TL_OTHERCOSTACTUAL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOM_TL_OTHERCOSTACTUAL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.woid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.woid.ordinal() ] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.requestedby.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.requestedby.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.othercostmstid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.othercostmstid.ordinal()] + "'";				
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_WOM_TL_OTHERCOSTACTUAL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.woid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.woid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.requestedby.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.requestedby.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.othercostmstid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.othercostmstid.ordinal()] + "'";		
		return sql;
	}


	
}

