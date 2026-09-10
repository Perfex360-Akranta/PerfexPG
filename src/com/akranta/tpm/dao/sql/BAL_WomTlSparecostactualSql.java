package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_WomTlSparecostplanSql.tableFldConstants;

public class BAL_WomTlSparecostactualSql {

	public static final String TBL_WOM_TL_SPARECOSTACTUAL = "WOM_TL_SPARECOSTACTUAL";  

	TableFieldType [] wscaDbFields = null;

	public enum   tableFldConstants
	{
		woid, sitreference, sparesid, quantity, rate, value, refdocno
		, doctype, requestedby, date, tempfield1, tempfield2, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getWscaDbFields() {
		return wscaDbFields;
	}

	public BAL_WomTlSparecostactualSql()
	{
		wscaDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			wscaDbFields[ i ] = new TableFieldType();
		}
		wscaDbFields[ tableFldConstants.woid.ordinal() ].fieldName = "WSCA_WOID";
		wscaDbFields[ tableFldConstants.woid.ordinal() ].fieldType = 'V';

		wscaDbFields[ tableFldConstants.sitreference.ordinal() ].fieldName = "WSCA_SITREFERENCE";
		wscaDbFields[ tableFldConstants.sitreference.ordinal() ].fieldType = 'V';

		wscaDbFields[ tableFldConstants.sparesid.ordinal() ].fieldName = "WSCA_SPARESID";
		wscaDbFields[ tableFldConstants.sparesid.ordinal() ].fieldType = 'V';

		wscaDbFields[ tableFldConstants.quantity.ordinal() ].fieldName = "WSCA_QUANTITY";
		wscaDbFields[ tableFldConstants.quantity.ordinal() ].fieldType = 'N';

		wscaDbFields[ tableFldConstants.rate.ordinal() ].fieldName = "WSCA_RATE";
		wscaDbFields[ tableFldConstants.rate.ordinal() ].fieldType = 'N';

		wscaDbFields[ tableFldConstants.value.ordinal() ].fieldName = "WSCA_VALUE";
		wscaDbFields[ tableFldConstants.value.ordinal() ].fieldType = 'N';

		wscaDbFields[ tableFldConstants.refdocno.ordinal() ].fieldName = "WSCA_REFDOCNO";
		wscaDbFields[ tableFldConstants.refdocno.ordinal() ].fieldType = 'V';

		wscaDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "WSCA_DOCTYPE";
		wscaDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		wscaDbFields[ tableFldConstants.requestedby.ordinal() ].fieldName = "WSCA_REQUESTEDBY";
		wscaDbFields[ tableFldConstants.requestedby.ordinal() ].fieldType = 'V';

		wscaDbFields[ tableFldConstants.date.ordinal() ].fieldName = "WSCA_DATE";
		wscaDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		wscaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "WSCA_TEMPFIELD1";
		wscaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		wscaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "WSCA_TEMPFIELD2";
		wscaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		wscaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WSCA_CREATEDBY";
		wscaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wscaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WSCA_CREATEDON";
		wscaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wscaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WSCA_MODIFIEDON";
		wscaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOM_TL_SPARECOSTACTUAL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOM_TL_SPARECOSTACTUAL, fieldTypeArr, dataArray);
		
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
		String sql = "DELETE from " + TBL_WOM_TL_SPARECOSTACTUAL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.woid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.woid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.requestedby.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.requestedby.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.sparesid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.sparesid.ordinal()] + "'";		
		return sql;
	}

}

