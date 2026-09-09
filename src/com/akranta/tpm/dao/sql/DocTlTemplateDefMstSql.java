package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;

public class DocTlTemplateDefMstSql {

	public static final String TBL_DOC_TL_TEMPLATE_DEF_MST = "DOC_TL_TEMPLATE_DEF_MST";  

	TableFieldType [] dtpmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, documenttype, code, tempfield, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getDtpmDbFields() {
		return dtpmDbFields;
	}

	public DocTlTemplateDefMstSql()
	{
		dtpmDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			dtpmDbFields[ i ] = new TableFieldType();
		}
		dtpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DTPM_KEYID";
		dtpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dtpmDbFields[ tableFldConstants.documenttype.ordinal() ].fieldName = "DTPM_DOCUMENTTYPE";
		dtpmDbFields[ tableFldConstants.documenttype.ordinal() ].fieldType = 'V';

		dtpmDbFields[ tableFldConstants.code.ordinal() ].fieldName = "DTPM_CODE";
		dtpmDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		dtpmDbFields[ tableFldConstants.tempfield.ordinal() ].fieldName = "DTPM_TEMPFIELD";
		dtpmDbFields[ tableFldConstants.tempfield.ordinal() ].fieldType = 'C';

		dtpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "DTPM_TEMPFIELD2";
		dtpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		dtpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "DTPM_TEMPFIELD3";
		dtpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		dtpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "DTPM_TEMPFIELD4";
		dtpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		dtpmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "DTPM_TEMPFIELD5";
		dtpmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		dtpmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DTPM_ACTIVE";
		dtpmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		dtpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DTPM_CREATEDBY";
		dtpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dtpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DTPM_CREATEDON";
		dtpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dtpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DTPM_MODIFIEDON";
		dtpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_DOC_TL_TEMPLATE_DEF_MST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_DOC_TL_TEMPLATE_DEF_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_DOC_TL_TEMPLATE_DEF_MST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getCountAll(CommonFilter commonFilter) {
		StringBuffer sql = new StringBuffer();
		  sql.append (" select count(*)  from  (");
		   sql.append(" SELECT rownum AS slno, A.* FROM(");
		   sql.append( " SELECT DISTINCT " );
		   sql.append("  DTPM_KEYID AS KEYID,DTPM_DOCUMENTTYPE AS DOCUMENTTYPE  from DOC_TL_TEMPLATE_DEF_MST ");
		   //sql.append(" where DTPM_ACTIVE= 'Y' ");
		  // sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
		   sql.append(" ORDER BY DTPM_KEYID DESC )A where  1 = 1 ) ");
		
		   
		return sql.toString();
	}

	public String getDocTempGrid(String keyId) {
		 String  sql= "select * from DOC_TL_TEMPLATE_DEF_MST WHERE DTPM_KEYID = ?";
		return sql;
	}

}

