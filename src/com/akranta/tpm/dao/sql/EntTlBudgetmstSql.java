package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlBudgetmstSql {

	public static final String TBL_ENT_TL_BUDGETMST = "ENT_TL_BUDGETMST";  

	TableFieldType [] budgDbFields = null;

	public enum   tableFldConstants
	{
		keyid, batch_keyid, bsdl_keyid, ebet_keyid, date, billno, billdate
		, quantity, amount, remarks, enteredby, accounthead, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getBudgDbFields() {
		return budgDbFields;
	}

	public EntTlBudgetmstSql()
	{
		budgDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			budgDbFields[ i ] = new TableFieldType();
		}
		budgDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "BUDG_KEYID";
		budgDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		budgDbFields[ tableFldConstants.batch_keyid.ordinal() ].fieldName = "BUDG_BATCH_KEYID";
		budgDbFields[ tableFldConstants.batch_keyid.ordinal() ].fieldType = 'V';

		budgDbFields[ tableFldConstants.bsdl_keyid.ordinal() ].fieldName = "BUDG_BSDL_KEYID";
		budgDbFields[ tableFldConstants.bsdl_keyid.ordinal() ].fieldType = 'V';

		budgDbFields[ tableFldConstants.ebet_keyid.ordinal() ].fieldName = "BUDG_EBET_KEYID";
		budgDbFields[ tableFldConstants.ebet_keyid.ordinal() ].fieldType = 'V';

		budgDbFields[ tableFldConstants.date.ordinal() ].fieldName = "BUDG_DATE";
		budgDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		budgDbFields[ tableFldConstants.billno.ordinal() ].fieldName = "BUDG_BILLNO";
		budgDbFields[ tableFldConstants.billno.ordinal() ].fieldType = 'V';

		budgDbFields[ tableFldConstants.billdate.ordinal() ].fieldName = "BUDG_BILLDATE";
		budgDbFields[ tableFldConstants.billdate.ordinal() ].fieldType = 'D';

		budgDbFields[ tableFldConstants.quantity.ordinal() ].fieldName = "BUDG_QUANTITY";
		budgDbFields[ tableFldConstants.quantity.ordinal() ].fieldType = 'N';

		budgDbFields[ tableFldConstants.amount.ordinal() ].fieldName = "BUDG_AMOUNT";
		budgDbFields[ tableFldConstants.amount.ordinal() ].fieldType = 'N';

		budgDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "BUDG_REMARKS";
		budgDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		budgDbFields[ tableFldConstants.enteredby.ordinal() ].fieldName = "BUDG_ENTEREDBY";
		budgDbFields[ tableFldConstants.enteredby.ordinal() ].fieldType = 'V';

		budgDbFields[ tableFldConstants.accounthead.ordinal() ].fieldName = "BUDG_ACCOUNTHEAD";
		budgDbFields[ tableFldConstants.accounthead.ordinal() ].fieldType = 'V';

		budgDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "BUDG_TEMPFIELD3";
		budgDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		budgDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "BUDG_TEMPFIELD4";
		budgDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		budgDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "BUDG_TEMPFIELD5";
		budgDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		budgDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BUDG_ACTIVE";
		budgDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		budgDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "BUDG_CREATEDBY";
		budgDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		budgDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "BUDG_CREATEDON";
		budgDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		budgDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "BUDG_MODIFIEDON";
		budgDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_BUDGETMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_BUDGETMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_BUDGETMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getBudgetSql(String batchId)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT BUDG_KEYID,BUDG_EBET_KEYID,EBET_DESCRIPTION,DECODE(to_char(BUDG_DATE,'DD-MON-YYYY'),'31-DEC-2100','',to_char(BUDG_DATE,'DD-MON-YYYY')),");
		sb.append("BUDG_BILLNO,DECODE(to_char(BUDG_BILLDATE,'DD-MON-YYYY'),'31-DEC-2100','',to_char(BUDG_BILLDATE,'DD-MON-YYYY')),");
		sb.append("BUDG_QUANTITY,BUDG_AMOUNT,BUDG_REMARKS,BUDG_ENTEREDBY,BUDG_ACCOUNTHEAD ");
		sb.append("FROM "+TBL_ENT_TL_BUDGETMST+","+TableNames.TBL_ENT_TL_BUDGET_EXPENSETYPE+" WHERE 1=1");
		sb.append(" AND BUDG_EBET_KEYID = EBET_KEYID");
		if(CommonFunctions.isValidKeyId(batchId))
			sb.append(" AND BUDG_BATCH_KEYID = '"+batchId+"'");
		sb.append(" ORDER BY BUDG_KEYID DESC");
		return sb.toString();
	}
	public static String getBudgetFromKeySql()
	{
		return "SELECT * FROM "+TBL_ENT_TL_BUDGETMST+" WHERE BUDG_KEYID = ?";
	}

}

