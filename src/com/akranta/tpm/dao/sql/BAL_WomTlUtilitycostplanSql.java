package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_WomTlUtilitycostactualSql.tableFldConstants;

public class BAL_WomTlUtilitycostplanSql {

	public static final String TBL_WOM_TL_UTILITYCOSTPLAN = "WOM_TL_UTILITYCOSTPLAN";  

	TableFieldType [] utcpDbFields = null;

	public enum   tableFldConstants
	{
		wokeyid, doctype, utilitymstid, requestedby, quantity, minutes
		, cost, totalvalue, remarks, date, tempfield2, tempfield3, tempfield4
		, tempfield5, createdby, createdon, modifiedon
	}

	public TableFieldType[] getUtcpDbFields() {
		return utcpDbFields;
	}

	public BAL_WomTlUtilitycostplanSql()
	{
		utcpDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			utcpDbFields[ i ] = new TableFieldType();
		}
		utcpDbFields[ tableFldConstants.wokeyid.ordinal() ].fieldName = "UTCP_WOKEYID";
		utcpDbFields[ tableFldConstants.wokeyid.ordinal() ].fieldType = 'V';

		utcpDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "UTCP_DOCTYPE";
		utcpDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		utcpDbFields[ tableFldConstants.utilitymstid.ordinal() ].fieldName = "UTCP_UTILITYMSTID";
		utcpDbFields[ tableFldConstants.utilitymstid.ordinal() ].fieldType = 'V';

		utcpDbFields[ tableFldConstants.requestedby.ordinal() ].fieldName = "UTCP_REQUESTEDBY";
		utcpDbFields[ tableFldConstants.requestedby.ordinal() ].fieldType = 'V';

		utcpDbFields[ tableFldConstants.quantity.ordinal() ].fieldName = "UTCP_QUANTITY";
		utcpDbFields[ tableFldConstants.quantity.ordinal() ].fieldType = 'N';

		utcpDbFields[ tableFldConstants.minutes.ordinal() ].fieldName = "UTCP_MINUTES";
		utcpDbFields[ tableFldConstants.minutes.ordinal() ].fieldType = 'N';

		utcpDbFields[ tableFldConstants.cost.ordinal() ].fieldName = "UTCP_COST";
		utcpDbFields[ tableFldConstants.cost.ordinal() ].fieldType = 'N';

		utcpDbFields[ tableFldConstants.totalvalue.ordinal() ].fieldName = "UTCP_TOTALVALUE";
		utcpDbFields[ tableFldConstants.totalvalue.ordinal() ].fieldType = 'N';

		utcpDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "UTCP_REMARKS";
		utcpDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		utcpDbFields[ tableFldConstants.date.ordinal() ].fieldName = "UTCP_DATE";
		utcpDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		utcpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "UTCP_TEMPFIELD2";
		utcpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		utcpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "UTCP_TEMPFIELD3";
		utcpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		utcpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "UTCP_TEMPFIELD4";
		utcpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		utcpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "UTCP_TEMPFIELD5";
		utcpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		utcpDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "UTCP_CREATEDBY";
		utcpDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		utcpDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "UTCP_CREATEDON";
		utcpDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		utcpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "UTCP_MODIFIEDON";
		utcpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOM_TL_UTILITYCOSTPLAN, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOM_TL_UTILITYCOSTPLAN, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.wokeyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.wokeyid.ordinal() ] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.requestedby.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.requestedby.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.utilitymstid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.utilitymstid.ordinal()] + "'";		
		
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_WOM_TL_UTILITYCOSTPLAN ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.wokeyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.wokeyid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.requestedby.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.requestedby.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.utilitymstid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.utilitymstid.ordinal()] + "'";		
		
		return sql;
	}

}

