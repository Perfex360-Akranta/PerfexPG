package com.akranta.tpm.dao.sql;

public class KznTlProjectChecklistLinkSql {

	public static final String TBL_KZN_TL_PROJECT_CHECKLIST_LINK = "KZN_TL_PROJECT_CHECKLIST_LINK";  

	TableFieldType [] pcllDbFields = null;

	public enum   tableFldConstants
	{
		keyid, projectid, checklistid, include, verifiedby, verifiedstatus
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPcllDbFields() {
		return pcllDbFields;
	}

	public KznTlProjectChecklistLinkSql()
	{
		pcllDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			pcllDbFields[ i ] = new TableFieldType();
		}
		pcllDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PCLL_KEYID";
		pcllDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pcllDbFields[ tableFldConstants.projectid.ordinal() ].fieldName = "PCLL_PROJECTID";
		pcllDbFields[ tableFldConstants.projectid.ordinal() ].fieldType = 'V';

		pcllDbFields[ tableFldConstants.checklistid.ordinal() ].fieldName = "PCLL_CHECKLISTID";
		pcllDbFields[ tableFldConstants.checklistid.ordinal() ].fieldType = 'V';

		pcllDbFields[ tableFldConstants.include.ordinal() ].fieldName = "PCLL_INCLUDE";
		pcllDbFields[ tableFldConstants.include.ordinal() ].fieldType = 'C';

		pcllDbFields[ tableFldConstants.verifiedby.ordinal() ].fieldName = "PCLL_VERIFIEDBY";
		pcllDbFields[ tableFldConstants.verifiedby.ordinal() ].fieldType = 'V';

		pcllDbFields[ tableFldConstants.verifiedstatus.ordinal() ].fieldName = "PCLL_VERIFIEDSTATUS";
		pcllDbFields[ tableFldConstants.verifiedstatus.ordinal() ].fieldType = 'C';

		pcllDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PCLL_TEMPFIELD1";
		pcllDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		pcllDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PCLL_TEMPFIELD2";
		pcllDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		pcllDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PCLL_TEMPFIELD3";
		pcllDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		pcllDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PCLL_TEMPFIELD4";
		pcllDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		pcllDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "PCLL_TEMPFIELD5";
		pcllDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		pcllDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PCLL_ACTIVE";
		pcllDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pcllDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PCLL_CREATEDBY";
		pcllDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pcllDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PCLL_CREATEDON";
		pcllDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pcllDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PCLL_MODIFIEDON";
		pcllDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_PROJECT_CHECKLIST_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_KZN_TL_PROJECT_CHECKLIST_LINK, fieldTypeArr, dataArray));
		
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ) ; 
		sql.append(" = '"  ) ; 
		sql.append( (String)dataArray[ tableFldConstants.keyid.ordinal() ] );
		sql.append("'");
		return sql.toString();
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_KZN_TL_PROJECT_CHECKLIST_LINK );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}

}

