package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_WomTlOthercostactualSql.tableFldConstants;

public class BAL_WomTlOthercostplanSql {

	public static final String TBL_WOM_TL_OTHERCOSTPLAN = "WOM_TL_OTHERCOSTPLAN";  

	TableFieldType [] otcpDbFields = null;

	public enum   tableFldConstants
	{
		woid, doctype, othercostmstid, requestedby, amount, remarks, date
		, tempfield2, tempfield3, tempfield4, tempfield5, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getOtcpDbFields() {
		return otcpDbFields;
	}

	public BAL_WomTlOthercostplanSql()
	{
		otcpDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			otcpDbFields[ i ] = new TableFieldType();
		}
		otcpDbFields[ tableFldConstants.woid.ordinal() ].fieldName = "OTCP_WOID";
		otcpDbFields[ tableFldConstants.woid.ordinal() ].fieldType = 'V';

		otcpDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "OTCP_DOCTYPE";
		otcpDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		otcpDbFields[ tableFldConstants.othercostmstid.ordinal() ].fieldName = "OTCP_OTHERCOSTMSTID";
		otcpDbFields[ tableFldConstants.othercostmstid.ordinal() ].fieldType = 'V';

		otcpDbFields[ tableFldConstants.requestedby.ordinal() ].fieldName = "OTCP_REQUESTEDBY";
		otcpDbFields[ tableFldConstants.requestedby.ordinal() ].fieldType = 'V';

		otcpDbFields[ tableFldConstants.amount.ordinal() ].fieldName = "OTCP_AMOUNT";
		otcpDbFields[ tableFldConstants.amount.ordinal() ].fieldType = 'N';

		otcpDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "OTCP_REMARKS";
		otcpDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		otcpDbFields[ tableFldConstants.date.ordinal() ].fieldName = "OTCP_DATE";
		otcpDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		otcpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "OTCP_TEMPFIELD2";
		otcpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		otcpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "OTCP_TEMPFIELD3";
		otcpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		otcpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "OTCP_TEMPFIELD4";
		otcpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		otcpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "OTCP_TEMPFIELD5";
		otcpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		otcpDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OTCP_CREATEDBY";
		otcpDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		otcpDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OTCP_CREATEDON";
		otcpDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		otcpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "OTCP_MODIFIEDON";
		otcpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOM_TL_OTHERCOSTPLAN, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOM_TL_OTHERCOSTPLAN, fieldTypeArr, dataArray);
		
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
		String sql = "DELETE from " + TBL_WOM_TL_OTHERCOSTPLAN ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.woid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.woid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.requestedby.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.requestedby.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.othercostmstid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.othercostmstid.ordinal()] + "'";		
		return sql;
	}

}

