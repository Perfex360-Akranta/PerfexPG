package com.akranta.tpm.dao.sql;

public class EntTlEmpskillhistorySql {

	public static final String TBL_ENT_TL_EMPSKILLHISTORY = "ENT_TL_EMPSKILLHISTORY";  

	TableFieldType [] eeshDbFields = null;

	public enum   tableFldConstants
	{
		keyid, topicid, assesmentid, assessdby, currentratting, employeeid
		, skilupdateddate, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, tempfield6, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getEeshDbFields() {
		return eeshDbFields;
	}

	public EntTlEmpskillhistorySql()
	{
		eeshDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			eeshDbFields[ i ] = new TableFieldType();
		}
		eeshDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EESH_KEYID";
		eeshDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		eeshDbFields[ tableFldConstants.topicid.ordinal() ].fieldName = "EESH_TOPICID";
		eeshDbFields[ tableFldConstants.topicid.ordinal() ].fieldType = 'V';

		eeshDbFields[ tableFldConstants.assesmentid.ordinal() ].fieldName = "EESH_ASSESMENTID";
		eeshDbFields[ tableFldConstants.assesmentid.ordinal() ].fieldType = 'V';

		eeshDbFields[ tableFldConstants.assessdby.ordinal() ].fieldName = "EESH_ASSESSDBY";
		eeshDbFields[ tableFldConstants.assessdby.ordinal() ].fieldType = 'V';

		eeshDbFields[ tableFldConstants.currentratting.ordinal() ].fieldName = "EESH_CURRENTRATTING";
		eeshDbFields[ tableFldConstants.currentratting.ordinal() ].fieldType = 'V';

		eeshDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "EESH_EMPLOYEEID";
		eeshDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		eeshDbFields[ tableFldConstants.skilupdateddate.ordinal() ].fieldName = "EESH_SKILUPDATEDDATE";
		eeshDbFields[ tableFldConstants.skilupdateddate.ordinal() ].fieldType = 'D';

		eeshDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "EESH_TEMPFIELD1";
		eeshDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		eeshDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "EESH_TEMPFIELD2";
		eeshDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		eeshDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "EESH_TEMPFIELD3";
		eeshDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		eeshDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "EESH_TEMPFIELD4";
		eeshDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		eeshDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "EESH_TEMPFIELD5";
		eeshDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		eeshDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "EESH_TEMPFIELD6";
		eeshDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		eeshDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EESH_ACTIVE";
		eeshDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		eeshDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EESH_CREATEDBY";
		eeshDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		eeshDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EESH_CREATEDON";
		eeshDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		eeshDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EESH_MODIFIEDON";
		eeshDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_EMPSKILLHISTORY, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_EMPSKILLHISTORY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_EMPSKILLHISTORY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

