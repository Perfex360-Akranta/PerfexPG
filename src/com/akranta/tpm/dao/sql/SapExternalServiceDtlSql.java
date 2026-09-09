package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.SapExternalRepairSql.tableFldConstants;

public class SapExternalServiceDtlSql {

	public static final String TBL_SAP_EXTERNAL_SERVICE_DTL = "SAP_EXTERNAL_SERVICE_DTL";  

	TableFieldType [] extdDbFields = null;

	public enum   tableFldConstants
	{
		extm_keyid, keyid, service_no, service_text, qty, value, uom
		, currency, cost_element,lineno,totalprice,tempfield2, 
		tempfield3,modifiedon, createdon, createdby, active
	}

	public TableFieldType[] getExtdDbFields() {
		return extdDbFields;
	}

	public SapExternalServiceDtlSql()
	{
		extdDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			extdDbFields[ i ] = new TableFieldType();
		}
		extdDbFields[ tableFldConstants.extm_keyid.ordinal() ].fieldName = "EXTD_EXTM_KEYID";
		extdDbFields[ tableFldConstants.extm_keyid.ordinal() ].fieldType = 'V';

		extdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EXTD_KEYID";
		extdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		extdDbFields[ tableFldConstants.service_no.ordinal() ].fieldName = "EXTD_SERVICE_NO";
		extdDbFields[ tableFldConstants.service_no.ordinal() ].fieldType = 'V';

		extdDbFields[ tableFldConstants.service_text.ordinal() ].fieldName = "EXTD_SERVICE_TEXT";
		extdDbFields[ tableFldConstants.service_text.ordinal() ].fieldType = 'V';

		extdDbFields[ tableFldConstants.qty.ordinal() ].fieldName = "EXTD_QTY";
		extdDbFields[ tableFldConstants.qty.ordinal() ].fieldType = 'N';

		extdDbFields[ tableFldConstants.value.ordinal() ].fieldName = "EXTD_VALUE";
		extdDbFields[ tableFldConstants.value.ordinal() ].fieldType = 'N';

		extdDbFields[ tableFldConstants.uom.ordinal() ].fieldName = "EXTD_UOM";
		extdDbFields[ tableFldConstants.uom.ordinal() ].fieldType = 'V';

		extdDbFields[ tableFldConstants.currency.ordinal() ].fieldName = "EXTD_CURRENCY";
		extdDbFields[ tableFldConstants.currency.ordinal() ].fieldType = 'V';

		extdDbFields[ tableFldConstants.cost_element.ordinal() ].fieldName = "EXTD_COST_ELEMENT";
		extdDbFields[ tableFldConstants.cost_element.ordinal() ].fieldType = 'V';

		extdDbFields[ tableFldConstants.lineno.ordinal() ].fieldName = "EXTD_LINENO";
		extdDbFields[ tableFldConstants.lineno.ordinal() ].fieldType = 'V';
		
		extdDbFields[ tableFldConstants.totalprice.ordinal() ].fieldName = "EXTD_TOTALPRICE";
		extdDbFields[ tableFldConstants.totalprice.ordinal() ].fieldType = 'V';
		
		extdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "EXTD_TEMPFIELD2";
		extdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		extdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "EXTD_TEMPFIELD3";
		extdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		extdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EXTD_MODIFIEDON";
		extdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

		extdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EXTD_CREATEDON";
		extdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		extdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EXTD_CREATEDBY";
		extdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		extdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EXTD_ACTIVE";
		extdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SAP_EXTERNAL_SERVICE_DTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SAP_EXTERNAL_SERVICE_DTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SAP_EXTERNAL_SERVICE_DTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

