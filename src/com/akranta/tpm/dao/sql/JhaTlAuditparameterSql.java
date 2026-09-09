package com.akranta.tpm.dao.sql;

public class JhaTlAuditparameterSql {

	public static final String TBL_JHA_TL_AUDITPARAMETER = "JHA_TL_AUDITPARAMETER";

	private static final String TBL_JHA_TL_AUDITTEMPLATE = "JHA_TL_AUDITTEMPLATE";

	private static final String TBL_JHA_TL_TEMPLATEGRADELINK = "JHA_TL_TEMPLATEGRADELINK";

	  

	TableFieldType [] jhapDbFields = null;

	public enum   tableFldConstants
	{
		
		keyid, templatename, templatecode, auditpillar,audittype, auditlevel,evidence, remarks, revisionno, revisiondate,
		criteriamax,tempfield2,tempfield3,tempfield4,tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getJhapDbFields() {
		return jhapDbFields;
	}

	public JhaTlAuditparameterSql()
	{
		jhapDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i <19; i++)
		{	
			jhapDbFields[ i ] = new TableFieldType();
		}
		jhapDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "JHAP_KEYID";
		jhapDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		jhapDbFields[ tableFldConstants.templatename.ordinal() ].fieldName = "JHAP_TEMPLATENAME";
		jhapDbFields[ tableFldConstants.templatename.ordinal() ].fieldType = 'V';

		jhapDbFields[ tableFldConstants.templatecode.ordinal() ].fieldName = "JHAP_TEMPLATECODE";
		jhapDbFields[ tableFldConstants.templatecode.ordinal() ].fieldType = 'V';
		
		jhapDbFields[ tableFldConstants.auditpillar.ordinal() ].fieldName = "JHAP_AUDITPILLAR";
		jhapDbFields[ tableFldConstants.auditpillar.ordinal() ].fieldType = 'C';
		
		jhapDbFields[ tableFldConstants.audittype.ordinal() ].fieldName = "JHAP_AUDITTYPE";
		jhapDbFields[ tableFldConstants.audittype.ordinal() ].fieldType = 'C';

		jhapDbFields[ tableFldConstants.auditlevel.ordinal() ].fieldName = "JHAP_AUDITLEVEL";
		jhapDbFields[ tableFldConstants.auditlevel.ordinal() ].fieldType = 'C';

		jhapDbFields[ tableFldConstants.evidence.ordinal() ].fieldName = "JHAP_EVIDENCE";
		jhapDbFields[ tableFldConstants.evidence.ordinal() ].fieldType = 'C';

		jhapDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "JHAP_REMARKS";
		jhapDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		jhapDbFields[ tableFldConstants.revisionno.ordinal() ].fieldName = "JHAP_REVISIONNO";
		jhapDbFields[ tableFldConstants.revisionno.ordinal() ].fieldType = 'V';

		jhapDbFields[ tableFldConstants.revisiondate.ordinal() ].fieldName = "JHAP_REVISIONDATE";
		jhapDbFields[ tableFldConstants.revisiondate.ordinal() ].fieldType = 'D';
		
		jhapDbFields[ tableFldConstants.criteriamax.ordinal() ].fieldName = "JHAP_CRITERIAMAX";
		jhapDbFields[ tableFldConstants.criteriamax.ordinal() ].fieldType = 'V';
		
		jhapDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "JHAP_TEMPFIELD2";
		jhapDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		jhapDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "JHAP_TEMPFIELD3";
		jhapDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		jhapDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "JHAP_TEMPFIELD4";
		jhapDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';
		
		jhapDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "JHAP_TEMPFIELD5";
		jhapDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		jhapDbFields[ tableFldConstants.active.ordinal() ].fieldName = "JHAP_ACTIVE";
		jhapDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		jhapDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "JHAP_CREATEDBY";
		jhapDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		jhapDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "JHAP_CREATEDON";
		jhapDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		jhapDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "JHAP_MODIFIEDON";
		jhapDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
	}
	

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_AUDITPARAMETER, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_AUDITPARAMETER, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_AUDITPARAMETER ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getAuditTemplateDeleteSql(String parameterId)
	{
		String sql = "DELETE from " + TBL_JHA_TL_AUDITTEMPLATE +" WHERE JAUT_KEYID='"+parameterId+"'";
		return sql;
	}
	
	public static String getTemplateGradeDeleteSql(String parameterId)
	{
		String sql = "DELETE from " + TBL_JHA_TL_TEMPLATEGRADELINK+" WHERE JTGL_TEMPLATEID='"+parameterId+"'";
		return sql;
	}

	public static String getDeleteAuditTemplateSql(String templateId) {
		
		String sql = "DELETE from " + TBL_JHA_TL_AUDITTEMPLATE +" WHERE JAUT_MASTERID='"+templateId+"'";
		return sql;
	}

	public static String getDeleteTemplateGradeSql(String templateId) {
		
		String sql = "DELETE from " + TBL_JHA_TL_TEMPLATEGRADELINK+" WHERE JTGL_AUDITMASTERID='"+templateId+"'";
		return sql;
	}
}

