package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.SheTlActionplan;
import com.akranta.tpm.utils.CommonFunctions;

public class SheTlActionplanSql {

	public static final String TBL_SHE_TL_ACTIONPLAN = "SHE_TL_ACTIONPLAN";  

	TableFieldType [] sacpDbFields = null;

	public enum   tableFldConstants
	{
		keyid, sinc_keyid, description, created_date, created_by, resp_dept
		, responsiblity, target_date, status, remarks, completed_by, completed_date
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, tempfield6, tempfield7, tempfield8, tempfield9, tempfield10
	}

	public TableFieldType[] getSacpDbFields() {
		return sacpDbFields;
	}

	public SheTlActionplanSql()
	{
		sacpDbFields = new TableFieldType[ 22 ];
		for(int i = 0;i < 22; i++)
		{	
			sacpDbFields[ i ] = new TableFieldType();
		}
		sacpDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SACP_KEYID";
		sacpDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.sinc_keyid.ordinal() ].fieldName = "SACP_SINC_KEYID";
		sacpDbFields[ tableFldConstants.sinc_keyid.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.description.ordinal() ].fieldName = "SACP_DESCRIPTION";
		sacpDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.created_date.ordinal() ].fieldName = "SACP_CREATED_DATE";
		sacpDbFields[ tableFldConstants.created_date.ordinal() ].fieldType = 'D';

		sacpDbFields[ tableFldConstants.created_by.ordinal() ].fieldName = "SACP_CREATED_BY";
		sacpDbFields[ tableFldConstants.created_by.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.resp_dept.ordinal() ].fieldName = "SACP_RESP_DEPT";
		sacpDbFields[ tableFldConstants.resp_dept.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.responsiblity.ordinal() ].fieldName = "SACP_RESPONSIBLITY";
		sacpDbFields[ tableFldConstants.responsiblity.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.target_date.ordinal() ].fieldName = "SACP_TARGET_DATE";
		sacpDbFields[ tableFldConstants.target_date.ordinal() ].fieldType = 'D';

		sacpDbFields[ tableFldConstants.status.ordinal() ].fieldName = "SACP_STATUS";
		sacpDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		sacpDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "SACP_REMARKS";
		sacpDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.completed_by.ordinal() ].fieldName = "SACP_COMPLETED_BY";
		sacpDbFields[ tableFldConstants.completed_by.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.completed_date.ordinal() ].fieldName = "SACP_COMPLETED_DATE";
		sacpDbFields[ tableFldConstants.completed_date.ordinal() ].fieldType = 'D';

		sacpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SACP_TEMPFIELD1";
		sacpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SACP_TEMPFIELD2";
		sacpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SACP_TEMPFIELD3";
		sacpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SACP_TEMPFIELD4";
		sacpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SACP_TEMPFIELD5";
		sacpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "SACP_TEMPFIELD6";
		sacpDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "SACP_TEMPFIELD7";
		sacpDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "SACP_TEMPFIELD8";
		sacpDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "SACP_TEMPFIELD9";
		sacpDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'V';

		sacpDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "SACP_TEMPFIELD10";
		sacpDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'V';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_ACTIONPLAN, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SHE_TL_ACTIONPLAN, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_ACTIONPLAN ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getIncidentActionPlan(SheTlActionplan sheTlActionplan) {
		
		
		String sql  = " SELECT SACP_KEYID,SACP_SINC_KEYID,SACP_RESPONSIBLITY,SACP_DESCRIPTION,SACP_RESP_DEPT,EMPM_NAME,DECODE(TO_CHAR(SACP_TARGET_DATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(SACP_TARGET_DATE,'DD-MON-YYYY'))," ;
			   sql += " DECODE(SACP_STATUS,'P' ,'PENDING' , 'C' ,'COMPLETED'),SACP_REMARKS,DECODE(TO_CHAR(SACP_COMPLETED_DATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(SACP_COMPLETED_DATE,'DD-MON-YYYY')),SACP_COMPLETED_BY" ;
			   sql += " FROM "+TBL_SHE_TL_ACTIONPLAN +", GEN_TL_EMPLOYEEMST WHERE ";
			   if(CommonFunctions.isValidKeyId(sheTlActionplan.getSacpSincKeyid()))
				   sql += "SACP_SINC_KEYID = '"+sheTlActionplan.getSacpSincKeyid()+"' AND ";
			   sql += "EMPM_KEYID = SACP_RESPONSIBLITY";
		return sql;
	}
	
	public static String getActionplan(){
		return "SELECT * FROM "+TBL_SHE_TL_ACTIONPLAN+" WHERE SACP_KEYID=?";
	}

}

