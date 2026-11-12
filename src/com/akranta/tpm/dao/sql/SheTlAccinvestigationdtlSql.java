package com.akranta.tpm.dao.sql;

public class SheTlAccinvestigationdtlSql {

	public static final String TBL_SHE_TL_ACCINVESTIGATIONDTL = "SHE_TL_ACCINVESTIGATIONDTL";  

	TableFieldType [] aindDbFields = null;

	public enum   tableFldConstants
	{
		keyid, ainm_keyid, reclocation, recommendation, target, agency
		, remarks, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, tempfield6, tempfield7, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getAindDbFields() {
		return aindDbFields;
	}

	public SheTlAccinvestigationdtlSql()
	{
		aindDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			aindDbFields[ i ] = new TableFieldType();
		}
		aindDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "AIND_KEYID";
		aindDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.ainm_keyid.ordinal() ].fieldName = "AIND_AINM_KEYID";
		aindDbFields[ tableFldConstants.ainm_keyid.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.reclocation.ordinal() ].fieldName = "AIND_RECLOCATION";
		aindDbFields[ tableFldConstants.reclocation.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.recommendation.ordinal() ].fieldName = "AIND_RECOMMENDATION";
		aindDbFields[ tableFldConstants.recommendation.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.target.ordinal() ].fieldName = "AIND_TARGET";
		aindDbFields[ tableFldConstants.target.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.agency.ordinal() ].fieldName = "AIND_AGENCY";
		aindDbFields[ tableFldConstants.agency.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "AIND_REMARKS";
		aindDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "AIND_TEMPFIELD1";
		aindDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "AIND_TEMPFIELD2";
		aindDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "AIND_TEMPFIELD3";
		aindDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "AIND_TEMPFIELD4";
		aindDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "AIND_TEMPFIELD5";
		aindDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "AIND_TEMPFIELD6";
		aindDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "AIND_TEMPFIELD7";
		aindDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.active.ordinal() ].fieldName = "AIND_ACTIVE";
		aindDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		aindDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "AIND_CREATEDBY";
		aindDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		aindDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "AIND_CREATEDON";
		aindDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		aindDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "AIND_MODIFIEDON";
		aindDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_ACCINVESTIGATIONDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SHE_TL_ACCINVESTIGATIONDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_ACCINVESTIGATIONDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteDtlSql(String ainmKeyid) {
		String sql = "DELETE from " + TBL_SHE_TL_ACCINVESTIGATIONDTL +" WHERE AIND_AINM_KEYID='"+ainmKeyid+"'" ;
		return sql;
	}

}

