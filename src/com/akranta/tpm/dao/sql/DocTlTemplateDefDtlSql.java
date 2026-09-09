package com.akranta.tpm.dao.sql;

public class DocTlTemplateDefDtlSql {

	public static final String TBL_DOC_TL_TEMPLATE_DEF_DTL = "DOC_TL_TEMPLATE_DEF_DTL";  

	TableFieldType [] dtpdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, dtpm_keyid, keyword, type, tempfield, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getDtpdDbFields() {
		return dtpdDbFields;
	}

	public DocTlTemplateDefDtlSql()
	{
		dtpdDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			dtpdDbFields[ i ] = new TableFieldType();
		}
		dtpdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DTPD_KEYID";
		dtpdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dtpdDbFields[ tableFldConstants.dtpm_keyid.ordinal() ].fieldName = "DTPD_DTPM_KEYID";
		dtpdDbFields[ tableFldConstants.dtpm_keyid.ordinal() ].fieldType = 'V';

		dtpdDbFields[ tableFldConstants.keyword.ordinal() ].fieldName = "DTPD_KEYWORD";
		dtpdDbFields[ tableFldConstants.keyword.ordinal() ].fieldType = 'V';

		dtpdDbFields[ tableFldConstants.type.ordinal() ].fieldName = "DTPD_TYPE";
		dtpdDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		dtpdDbFields[ tableFldConstants.tempfield.ordinal() ].fieldName = "DTPD_TEMPFIELD";
		dtpdDbFields[ tableFldConstants.tempfield.ordinal() ].fieldType = 'C';

		dtpdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "DTPD_TEMPFIELD2";
		dtpdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		dtpdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "DTPD_TEMPFIELD3";
		dtpdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		dtpdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "DTPD_TEMPFIELD4";
		dtpdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		dtpdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "DTPD_TEMPFIELD5";
		dtpdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		dtpdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DTPD_ACTIVE";
		dtpdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		dtpdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DTPD_CREATEDBY";
		dtpdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dtpdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DTPD_CREATEDON";
		dtpdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dtpdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DTPD_MODIFIEDON";
		dtpdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_DOC_TL_TEMPLATE_DEF_DTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_DOC_TL_TEMPLATE_DEF_DTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_DOC_TL_TEMPLATE_DEF_DTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getKeiIdCountSql(String dtpmKeyid) {
		
		String sql = "SELECT count(*) FROM DOC_TL_TEMPLATE_DEF_DTL WHERE DTPD_dtpm_KEYID='"+dtpmKeyid+"'";
		return sql;
	}

}

