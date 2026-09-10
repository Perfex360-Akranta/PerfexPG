package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_WomTlServicecostplanSql.tableFldConstants;

public class BAL_WomTlServicecostactualSql {

	public static final String TBL_WOM_TL_SERVICECOSTACTUAL = "WOM_TL_SERVICECOSTACTUAL";  

	TableFieldType [] svcaDbFields = null;

	public enum   tableFldConstants
	{
		woid, doctype, serviceid, billno, billvalue, billdateflag, billdate
		, jobdescription, remarks, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, createdby, createdon, modifiedon
	}

	public TableFieldType[] getSvcaDbFields() {
		return svcaDbFields;
	}

	public BAL_WomTlServicecostactualSql()
	{
		svcaDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			svcaDbFields[ i ] = new TableFieldType();
		}
		svcaDbFields[ tableFldConstants.woid.ordinal() ].fieldName = "SVCA_WOID";
		svcaDbFields[ tableFldConstants.woid.ordinal() ].fieldType = 'V';

		svcaDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "SVCA_DOCTYPE";
		svcaDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		svcaDbFields[ tableFldConstants.serviceid.ordinal() ].fieldName = "SVCA_SERVICEID";
		svcaDbFields[ tableFldConstants.serviceid.ordinal() ].fieldType = 'V';

		svcaDbFields[ tableFldConstants.billno.ordinal() ].fieldName = "SVCA_BILLNO";
		svcaDbFields[ tableFldConstants.billno.ordinal() ].fieldType = 'V';

		svcaDbFields[ tableFldConstants.billvalue.ordinal() ].fieldName = "SVCA_BILLVALUE";
		svcaDbFields[ tableFldConstants.billvalue.ordinal() ].fieldType = 'N';

		svcaDbFields[ tableFldConstants.billdateflag.ordinal() ].fieldName = "SVCA_BILLDATEFLAG";
		svcaDbFields[ tableFldConstants.billdateflag.ordinal() ].fieldType = 'C';

		svcaDbFields[ tableFldConstants.billdate.ordinal() ].fieldName = "SVCA_BILLDATE";
		svcaDbFields[ tableFldConstants.billdate.ordinal() ].fieldType = 'D';

		svcaDbFields[ tableFldConstants.jobdescription.ordinal() ].fieldName = "SVCA_JOBDESCRIPTION";
		svcaDbFields[ tableFldConstants.jobdescription.ordinal() ].fieldType = 'V';

		svcaDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "SVCA_REMARKS";
		svcaDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		svcaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SVCA_TEMPFIELD1";
		svcaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		svcaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SVCA_TEMPFIELD2";
		svcaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		svcaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SVCA_TEMPFIELD3";
		svcaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		svcaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SVCA_TEMPFIELD4";
		svcaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		svcaDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SVCA_TEMPFIELD5";
		svcaDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		svcaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SVCA_CREATEDBY";
		svcaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		svcaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SVCA_CREATEDON";
		svcaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		svcaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SVCA_MODIFIEDON";
		svcaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOM_TL_SERVICECOSTACTUAL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOM_TL_SERVICECOSTACTUAL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.woid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.woid.ordinal() ] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.serviceid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.serviceid.ordinal()] + "'";
		
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_WOM_TL_SERVICECOSTACTUAL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.woid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.woid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.serviceid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.serviceid.ordinal()] + "'";
		
		return sql;
	}

}

