package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.EntTlFeedbackparammst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlFeedbackparammstSql {

	public static final String TBL_ENT_TL_FEEDBACKPARAMMST = "ENT_TL_FEEDBACKPARAMMST";  

	TableFieldType [] fbpmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, code, description, parentid, effective_date, inactive_date
		, type, remarks, displayorder, ischild, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getFbpmDbFields() {
		return fbpmDbFields;
	}

	public EntTlFeedbackparammstSql()
	{
		fbpmDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			fbpmDbFields[ i ] = new TableFieldType();
		}
		fbpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FBPM_KEYID";
		fbpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fbpmDbFields[ tableFldConstants.code.ordinal() ].fieldName = "FBPM_CODE";
		fbpmDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		fbpmDbFields[ tableFldConstants.description.ordinal() ].fieldName = "FBPM_DESCRIPTION";
		fbpmDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		fbpmDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "FBPM_PARENTID";
		fbpmDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		fbpmDbFields[ tableFldConstants.effective_date.ordinal() ].fieldName = "FBPM_EFFECTIVE_DATE";
		fbpmDbFields[ tableFldConstants.effective_date.ordinal() ].fieldType = 'D';

		fbpmDbFields[ tableFldConstants.inactive_date.ordinal() ].fieldName = "FBPM_INACTIVE_DATE";
		fbpmDbFields[ tableFldConstants.inactive_date.ordinal() ].fieldType = 'D';

		fbpmDbFields[ tableFldConstants.type.ordinal() ].fieldName = "FBPM_TYPE";
		fbpmDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		fbpmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "FBPM_REMARKS";
		fbpmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		fbpmDbFields[ tableFldConstants.displayorder.ordinal() ].fieldName = "FBPM_DISPLAYORDER";
		fbpmDbFields[ tableFldConstants.displayorder.ordinal() ].fieldType = 'V';

		fbpmDbFields[ tableFldConstants.ischild.ordinal() ].fieldName = "FBPM_ISCHILD";
		fbpmDbFields[ tableFldConstants.ischild.ordinal() ].fieldType = 'C';

		fbpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FBPM_TEMPFIELD1";
		fbpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		fbpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FBPM_TEMPFIELD2";
		fbpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		fbpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FBPM_TEMPFIELD3";
		fbpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		fbpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FBPM_TEMPFIELD4";
		fbpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		fbpmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FBPM_TEMPFIELD5";
		fbpmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		fbpmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FBPM_ACTIVE";
		fbpmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fbpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FBPM_CREATEDBY";
		fbpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fbpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FBPM_CREATEDON";
		fbpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fbpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FBPM_MODIFIEDON";
		fbpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_FEEDBACKPARAMMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_FEEDBACKPARAMMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_FEEDBACKPARAMMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getInactiveSql(String keyId)
	{
		String sql = "UPDATE "+TBL_ENT_TL_FEEDBACKPARAMMST+" SET FBPM_ACTIVE ='N' WHERE FBPM_KEYID ='"+keyId+"'";
		return sql;
	}
	public static String updateInactiveDateSql(String keyId,String inactiveDate)
	{
		String sql = "UPDATE "+TBL_ENT_TL_FEEDBACKPARAMMST+" SET FBPM_ACTIVE ='N'";
		if(CommonFunctions.isValidKeyId(inactiveDate))
			   sql += ",FBPM_INACTIVE_DATE='"+inactiveDate+"'";
			   sql += " WHERE FBPM_KEYID ='"+keyId+"'";
			   
		return sql;
	}
	public static String updateIsChildSql(String keyId)
	{
		String sql = "UPDATE "+TBL_ENT_TL_FEEDBACKPARAMMST+" SET FBPM_ISCHILD ='N'";
		if(CommonFunctions.isValidKeyId(keyId))
			 sql += " WHERE FBPM_KEYID ='"+keyId+"'";
		return sql;
	}
	public static String getDispOrderSql(String parentId)
	{
		String sql = "SELECT MAX(FBPM_DISPLAYORDER) FROM ENT_TL_FEEDBACKPARAMMST";
		if(CommonFunctions.isValidKeyId(parentId))
		{
			   sql += " WHERE FBPM_PARENTID='"+parentId+"'";
			   sql += " AND FBPM_KEYID<>FBPM_PARENTID ";
		}
		else
			 sql += " WHERE FBPM_KEYID=FBPM_PARENTID ";
		CommonMessage.debugMsg(sql);
		return sql;
	}
	public static String getParentSql(String parentId)
	{
		String sql = "SELECT FBPM_KEYID FROM ENT_TL_FEEDBACKPARAMMST";
			   sql += " WHERE FBPM_PARENTID='"+parentId+"'";
		       sql += " AND FBPM_KEYID<>FBPM_PARENTID ";
		return sql;
		
	}
	
	public static String getFeedback() {
		// TODO Auto-generated method stub
		return  " SELECT * from " + TBL_ENT_TL_FEEDBACKPARAMMST + " where FBPM_KEYID = ? ";
	}
	public static String getFBTreeSql(EntTlFeedbackparammst entTlFeedbackparammst)
	{
		String parentId = entTlFeedbackparammst.getFbpmParentid();
		String id = entTlFeedbackparammst.getFbpmKeyid();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT FBPM_KEYID,FBPM_DISPLAYORDER,FBPM_PARENTID,FBPM_TYPE,FBPM_DESCRIPTION");
		sb.append(" FROM "+TBL_ENT_TL_FEEDBACKPARAMMST+" WHERE 1=1");
		if (CommonFunctions.isValidKeyId(parentId))
		{
			if(parentId.equals("1"))
				sb.append(" AND FBPM_KEYID=FBPM_PARENTID ");
				
			else
			{
				sb.append(" AND FBPM_KEYID<>FBPM_PARENTID ");
				sb.append(" AND FBPM_PARENTID='" + id + "'");
			}
		}
		sb.append(" AND FBPM_ACTIVE = 'Y'");
		return sb.toString();
	}
	public static String getEmployeesSql(String progId,String fromDate,String toDate)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DISTINCT EMPM_KEYID,EMPM_NAME,EMPM_EMPLOYEENUMBER FROM GEN_TL_EMPLOYEEMST,");
		sb.append("ENT_TL_BATCHMST,ENT_TL_BATCH_EMPLOYEE_LINK WHERE EMPM_KEYID =BSTD_EMPM_KEYID");
		sb.append(" AND BSTD_BACH_KEYID=BACH_KEYID AND BACH_PROG_KEYID='"+progId+"'");
		CommonMessage.debugMsg(sb.toString());
		return sb.toString();
	}
	public static String getQuestionSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT FBPM_KEYID,FBPM_DESCRIPTION, FEED_ANSWER FROM ENT_TL_FEEDBACK,ENT_TL_FEEDBACKPARAMMST");
		sb.append(" WHERE FEED_FBPM_KEYID(+)=FBPM_KEYID AND FBPM_ISCHILD='Y' AND FBPM_TYPE = 'QA'");
		return sb.toString();
	}
}

