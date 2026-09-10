package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_WomTlManpowercostplanSql.tableFldConstants;

public class BAL_WomTlUtilitycostactualSql {

	public static final String TBL_WOM_TL_UTILITYCOSTACTUAL = "WOM_TL_UTILITYCOSTACTUAL";  

	TableFieldType [] utcaDbFields = null;

	public enum   tableFldConstants
	{
		wokeyid, doctype, utilitymstid, requestedby, quantity, minutes
		, cost, totalvalue, remarks, date, tempfield2, tempfield3, tempfield4
		, tempfield5, createdby, createdon, modifiedon
	}

	public TableFieldType[] getUtcaDbFields() {
		return utcaDbFields;
	}

	public BAL_WomTlUtilitycostactualSql()
	{
		utcaDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			utcaDbFields[ i ] = new TableFieldType();
		}
		utcaDbFields[ tableFldConstants.wokeyid.ordinal() ].fieldName = "UTCA_WOKEYID";
		utcaDbFields[ tableFldConstants.wokeyid.ordinal() ].fieldType = 'V';

		utcaDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "UTCA_DOCTYPE";
		utcaDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		utcaDbFields[ tableFldConstants.utilitymstid.ordinal() ].fieldName = "UTCA_UTILITYMSTID";
		utcaDbFields[ tableFldConstants.utilitymstid.ordinal() ].fieldType = 'V';

		utcaDbFields[ tableFldConstants.requestedby.ordinal() ].fieldName = "UTCA_REQUESTEDBY";
		utcaDbFields[ tableFldConstants.requestedby.ordinal() ].fieldType = 'V';

		utcaDbFields[ tableFldConstants.quantity.ordinal() ].fieldName = "UTCA_QUANTITY";
		utcaDbFields[ tableFldConstants.quantity.ordinal() ].fieldType = 'N';

		utcaDbFields[ tableFldConstants.minutes.ordinal() ].fieldName = "UTCA_MINUTES";
		utcaDbFields[ tableFldConstants.minutes.ordinal() ].fieldType = 'N';

		utcaDbFields[ tableFldConstants.cost.ordinal() ].fieldName = "UTCA_COST";
		utcaDbFields[ tableFldConstants.cost.ordinal() ].fieldType = 'N';

		utcaDbFields[ tableFldConstants.totalvalue.ordinal() ].fieldName = "UTCA_TOTALVALUE";
		utcaDbFields[ tableFldConstants.totalvalue.ordinal() ].fieldType = 'N';

		utcaDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "UTCA_REMARKS";
		utcaDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		utcaDbFields[ tableFldConstants.date.ordinal() ].fieldName = "UTCA_DATE";
		utcaDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		utcaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "UTCA_TEMPFIELD2";
		utcaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		utcaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "UTCA_TEMPFIELD3";
		utcaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		utcaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "UTCA_TEMPFIELD4";
		utcaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		utcaDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "UTCA_TEMPFIELD5";
		utcaDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		utcaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "UTCA_CREATEDBY";
		utcaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		utcaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "UTCA_CREATEDON";
		utcaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		utcaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "UTCA_MODIFIEDON";
		utcaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOM_TL_UTILITYCOSTACTUAL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOM_TL_UTILITYCOSTACTUAL, fieldTypeArr, dataArray);
		
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
		String sql = "DELETE from " + TBL_WOM_TL_UTILITYCOSTACTUAL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.wokeyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.wokeyid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.requestedby.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.requestedby.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.utilitymstid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.utilitymstid.ordinal()] + "'";		
		
		return sql;
	}

}

