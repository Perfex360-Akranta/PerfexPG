package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_WomTlSparecostplanSql.tableFldConstants;

public class BAL_WomTlServicecostplanSql {

	public static final String TBL_WOM_TL_SERVICECOSTPLAN = "WOM_TL_SERVICECOSTPLAN";  

	TableFieldType [] svcpDbFields = null;

	public enum   tableFldConstants
	{
		woid, doctype, serviceid, billno, billvalue, billdateflag, billdate
		, jobdescription, remarks, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, createdby, createdon, modifiedon
	}

	public TableFieldType[] getSvcpDbFields() {
		return svcpDbFields;
	}

	public BAL_WomTlServicecostplanSql()
	{
		svcpDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			svcpDbFields[ i ] = new TableFieldType();
		}
		svcpDbFields[ tableFldConstants.woid.ordinal() ].fieldName = "SVCP_WOID";
		svcpDbFields[ tableFldConstants.woid.ordinal() ].fieldType = 'V';

		svcpDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "SVCP_DOCTYPE";
		svcpDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		svcpDbFields[ tableFldConstants.serviceid.ordinal() ].fieldName = "SVCP_SERVICEID";
		svcpDbFields[ tableFldConstants.serviceid.ordinal() ].fieldType = 'V';

		svcpDbFields[ tableFldConstants.billno.ordinal() ].fieldName = "SVCP_BILLNO";
		svcpDbFields[ tableFldConstants.billno.ordinal() ].fieldType = 'V';

		svcpDbFields[ tableFldConstants.billvalue.ordinal() ].fieldName = "SVCP_BILLVALUE";
		svcpDbFields[ tableFldConstants.billvalue.ordinal() ].fieldType = 'N';

		svcpDbFields[ tableFldConstants.billdateflag.ordinal() ].fieldName = "SVCP_BILLDATEFLAG";
		svcpDbFields[ tableFldConstants.billdateflag.ordinal() ].fieldType = 'C';

		svcpDbFields[ tableFldConstants.billdate.ordinal() ].fieldName = "SVCP_BILLDATE";
		svcpDbFields[ tableFldConstants.billdate.ordinal() ].fieldType = 'D';

		svcpDbFields[ tableFldConstants.jobdescription.ordinal() ].fieldName = "SVCP_JOBDESCRIPTION";
		svcpDbFields[ tableFldConstants.jobdescription.ordinal() ].fieldType = 'V';

		svcpDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "SVCP_REMARKS";
		svcpDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		svcpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SVCP_TEMPFIELD1";
		svcpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		svcpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SVCP_TEMPFIELD2";
		svcpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		svcpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SVCP_TEMPFIELD3";
		svcpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		svcpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SVCP_TEMPFIELD4";
		svcpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		svcpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SVCP_TEMPFIELD5";
		svcpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		svcpDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SVCP_CREATEDBY";
		svcpDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		svcpDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SVCP_CREATEDON";
		svcpDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		svcpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SVCP_MODIFIEDON";
		svcpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOM_TL_SERVICECOSTPLAN, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOM_TL_SERVICECOSTPLAN, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.woid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.woid.ordinal() ] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.serviceid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.serviceid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_WOM_TL_SERVICECOSTPLAN ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.woid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.woid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.serviceid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.serviceid.ordinal()] + "'";
		return sql;
	}

}

