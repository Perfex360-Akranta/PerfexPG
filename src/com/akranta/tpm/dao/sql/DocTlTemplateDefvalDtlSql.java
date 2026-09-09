package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;

public class DocTlTemplateDefvalDtlSql {

	public static final String TBL_DOC_TL_TEMPLATE_DEFVAL_DTL = "DOC_TL_TEMPLATE_DEFVAL_DTL";  

	TableFieldType [] dtpvDbFields = null;

	public enum   tableFldConstants
	{
		keyid, dtpm_keyid, dtpd_keyid, keyword, value, dmdm_keyid, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getDtpvDbFields() {
		return dtpvDbFields;
	}

	public DocTlTemplateDefvalDtlSql()
	{
		dtpvDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			dtpvDbFields[ i ] = new TableFieldType();
		}
		dtpvDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DTPV_KEYID";
		dtpvDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dtpvDbFields[ tableFldConstants.dtpm_keyid.ordinal() ].fieldName = "DTPV_DTPM_KEYID";
		dtpvDbFields[ tableFldConstants.dtpm_keyid.ordinal() ].fieldType = 'V';

		dtpvDbFields[ tableFldConstants.dtpd_keyid.ordinal() ].fieldName = "DTPV_DTPD_KEYID";
		dtpvDbFields[ tableFldConstants.dtpd_keyid.ordinal() ].fieldType = 'V';

		dtpvDbFields[ tableFldConstants.keyword.ordinal() ].fieldName = "DTPV_KEYWORD";
		dtpvDbFields[ tableFldConstants.keyword.ordinal() ].fieldType = 'V';

		dtpvDbFields[ tableFldConstants.value.ordinal() ].fieldName = "DTPV_VALUE";
		dtpvDbFields[ tableFldConstants.value.ordinal() ].fieldType = 'V';

		dtpvDbFields[ tableFldConstants.dmdm_keyid.ordinal() ].fieldName = "DTPV_DMDM_KEYID";
		dtpvDbFields[ tableFldConstants.dmdm_keyid.ordinal() ].fieldType = 'V';

		dtpvDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "DTPV_TEMPFIELD2";
		dtpvDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		dtpvDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "DTPV_TEMPFIELD3";
		dtpvDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		dtpvDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "DTPV_TEMPFIELD4";
		dtpvDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		dtpvDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "DTPV_TEMPFIELD5";
		dtpvDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		dtpvDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DTPV_ACTIVE";
		dtpvDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		dtpvDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DTPV_CREATEDBY";
		dtpvDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dtpvDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DTPV_CREATEDON";
		dtpvDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dtpvDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DTPV_MODIFIEDON";
		dtpvDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_DOC_TL_TEMPLATE_DEFVAL_DTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_DOC_TL_TEMPLATE_DEFVAL_DTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_DOC_TL_TEMPLATE_DEFVAL_DTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getTypeKeywordsSql(String id,String fileId,String flag)
	{
		String sql = "SELECT ";
		if(UIUtils.isValidKeyId(flag))
		{
			if(flag.equals("Y"))
			{
				  sql += "DISTINCT DTPD_KEYID,DTPD_KEYWORD,";
				  sql+="'',";
			}
			else
			{
				sql+="DTPV_KEYID,DTPD_KEYID,DTPD_KEYWORD,";
				if(UIUtils.isValidKeyId(fileId))
					sql+="DTPV_VALUE,";
				else
					  sql+="'',";
			}
		}
		else
		{
			sql+="DTPV_KEYID,DTPD_KEYID,DTPD_KEYWORD,";
			if(UIUtils.isValidKeyId(fileId))
				sql+="DTPV_VALUE,";
			else
				  sql+="'',";
		}
			 
		sql += "DTPD_TYPE FROM DOC_TL_TEMPLATE_DEF_DTL,DOC_TL_TEMPLATE_DEFVAL_DTL WHERE DTPD_KEYID=DtpV_DTPD_KEYID(+)";
		sql += " AND Dtpd_Dtpm_Keyid = '"+id+"'";
		if(UIUtils.isValidKeyId(fileId))
			sql += " AND Dtpv_Dmdm_Keyid(+) = '"+fileId+"'";
		sql += " ORDER BY DTPD_KEYID";
		return sql;
	}
	public static String getModifiedDateSql(String id)
	{
		String sql = "SELECT to_char(DTPV_MODIFIEDON,'DD-MON-YYYY HH24:MM') FROM "+TBL_DOC_TL_TEMPLATE_DEFVAL_DTL;
			   sql += " WHERE DTPV_KEYID='"+id+"'";
		return sql;
	}
}

