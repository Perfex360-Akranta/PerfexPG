package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.JhaTlAuditparameterSql.tableFldConstants;

public class JhaTlAudittemplateSql {

	public static final String TBL_JHA_TL_AUDITTEMPLATE = "JHA_TL_AUDITTEMPLATE";  

	TableFieldType [] jautDbFields = null;

	public enum   tableFldConstants
	{
		keyid, masterid, parametername, parameterdescription,evidence, maximumpoints,
		reviewptslno,criteriaslno,tempfield3,tempfield4,tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getJautDbFields() {
		return jautDbFields;
	}

	public JhaTlAudittemplateSql()
	{
		jautDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			jautDbFields[ i ] = new TableFieldType();
		}
		jautDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "JAUT_KEYID";
		jautDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		jautDbFields[ tableFldConstants.masterid.ordinal() ].fieldName = "JAUT_MASTERID";
		jautDbFields[ tableFldConstants.masterid.ordinal() ].fieldType = 'V';

		jautDbFields[ tableFldConstants.parametername.ordinal() ].fieldName = "JAUT_PARAMETERNAME";
		jautDbFields[ tableFldConstants.parametername.ordinal() ].fieldType = 'V';

		jautDbFields[ tableFldConstants.parameterdescription.ordinal() ].fieldName = "JAUT_PARAMETERDESCRIPTION";
		jautDbFields[ tableFldConstants.parameterdescription.ordinal() ].fieldType = 'V';
		
		jautDbFields[ tableFldConstants.evidence.ordinal() ].fieldName = "JAUT_EVIDENCE";
		jautDbFields[ tableFldConstants.evidence.ordinal() ].fieldType = 'V';

		jautDbFields[ tableFldConstants.maximumpoints.ordinal() ].fieldName = "JAUT_MAXIMUMPOINTS";
		jautDbFields[ tableFldConstants.maximumpoints.ordinal() ].fieldType = 'N';
		
		jautDbFields[ tableFldConstants.reviewptslno.ordinal() ].fieldName = "JAUT_REVIEWPTSLNO";
		jautDbFields[ tableFldConstants.reviewptslno.ordinal() ].fieldType = 'V';
		
		jautDbFields[ tableFldConstants.criteriaslno.ordinal() ].fieldName = "JAUT_CRITERIASLNO";
		jautDbFields[ tableFldConstants.criteriaslno.ordinal() ].fieldType = 'V';
		
		jautDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "JAUT_TEMPFIELD3";
		jautDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		jautDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "JAUT_TEMPFIELD4";
		jautDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';
		
		jautDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "JAUT_TEMPFIELD5";
		jautDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		jautDbFields[ tableFldConstants.active.ordinal() ].fieldName = "JAUT_ACTIVE";
		jautDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		jautDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "JAUT_CREATEDBY";
		jautDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		jautDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "JAUT_CREATEDON";
		jautDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		jautDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "JAUT_MODIFIEDON";
		jautDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_AUDITTEMPLATE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_AUDITTEMPLATE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_AUDITTEMPLATE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

