package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_WomTlManpowercostplanSql.tableFldConstants;

public class BAL_WomTlSparecostplanSql {

	public static final String TBL_WOM_TL_SPARECOSTPLAN = "WOM_TL_SPARECOSTPLAN";  

	TableFieldType [] wscpDbFields = null;

	public enum   tableFldConstants
	{
		woid, doctype, sparesid, quantity, rate, value, refdocno, requestedby
		, date, tempfield1, tempfield2, tempfield3, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getWscpDbFields() {
		return wscpDbFields;
	}

	public BAL_WomTlSparecostplanSql()
	{
		wscpDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			wscpDbFields[ i ] = new TableFieldType();
		}
		wscpDbFields[ tableFldConstants.woid.ordinal() ].fieldName = "WSCP_WOID";
		wscpDbFields[ tableFldConstants.woid.ordinal() ].fieldType = 'V';

		wscpDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "WSCP_DOCTYPE";
		wscpDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		wscpDbFields[ tableFldConstants.sparesid.ordinal() ].fieldName = "WSCP_SPARESID";
		wscpDbFields[ tableFldConstants.sparesid.ordinal() ].fieldType = 'V';

		wscpDbFields[ tableFldConstants.quantity.ordinal() ].fieldName = "WSCP_QUANTITY";
		wscpDbFields[ tableFldConstants.quantity.ordinal() ].fieldType = 'N';

		wscpDbFields[ tableFldConstants.rate.ordinal() ].fieldName = "WSCP_RATE";
		wscpDbFields[ tableFldConstants.rate.ordinal() ].fieldType = 'N';

		wscpDbFields[ tableFldConstants.value.ordinal() ].fieldName = "WSCP_VALUE";
		wscpDbFields[ tableFldConstants.value.ordinal() ].fieldType = 'N';

		wscpDbFields[ tableFldConstants.refdocno.ordinal() ].fieldName = "WSCP_REFDOCNO";
		wscpDbFields[ tableFldConstants.refdocno.ordinal() ].fieldType = 'V';

		wscpDbFields[ tableFldConstants.requestedby.ordinal() ].fieldName = "WSCP_REQUESTEDBY";
		wscpDbFields[ tableFldConstants.requestedby.ordinal() ].fieldType = 'V';

		wscpDbFields[ tableFldConstants.date.ordinal() ].fieldName = "WSCP_DATE";
		wscpDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		wscpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "WSCP_TEMPFIELD1";
		wscpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		wscpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "WSCP_TEMPFIELD2";
		wscpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		wscpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "WSCP_TEMPFIELD3";
		wscpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		wscpDbFields[ tableFldConstants.active.ordinal() ].fieldName = "WSCP_ACTIVE";
		wscpDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		wscpDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WSCP_CREATEDBY";
		wscpDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wscpDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WSCP_CREATEDON";
		wscpDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wscpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WSCP_MODIFIEDON";
		wscpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOM_TL_SPARECOSTPLAN, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOM_TL_SPARECOSTPLAN, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.woid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.woid.ordinal() ] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.requestedby.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.requestedby.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.sparesid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.sparesid.ordinal()] + "'";		
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_WOM_TL_SPARECOSTPLAN ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.woid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.woid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.requestedby.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.requestedby.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.sparesid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.sparesid.ordinal()] + "'";		
		return sql;
	}

}

