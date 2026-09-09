package com.akranta.tpm.dao.sql;

public class EntTlTrgCalEmpSql {

	public static final String TBL_ENT_TL_TRGCALEMP = "ENT_TL_TRGCALEMP";  

	TableFieldType [] ftymDbFields = null;

	public enum   tableFldConstants
	{
		keyid,etcm_keyid,etcs_keyid,empm_keyid,dateadd,role_keyid,roledmt,rolejh
		,tempfield1, tempfield2, tempfield3, tempfield4,tempfield5, createdby,active
		, createdon, modifiedon
	}

	public TableFieldType[] getFtymDbFields() {
		return ftymDbFields;
	}

	public EntTlTrgCalEmpSql()
	{
		ftymDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			ftymDbFields[ i ] = new TableFieldType();
		}
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ETCE_KEYID";
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.etcm_keyid.ordinal() ].fieldName = "ETCE_ETCM_KEYID";
		ftymDbFields[ tableFldConstants.etcm_keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.etcs_keyid.ordinal() ].fieldName = "ETCE_ETCS_KEYID";
		ftymDbFields[ tableFldConstants.etcs_keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "ETCE_EMPM_KEYID";
		ftymDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.dateadd.ordinal() ].fieldName = "ETCE_DATEADD";
		ftymDbFields[ tableFldConstants.dateadd.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "ETCE_ROLE_KEYID";
		ftymDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.roledmt.ordinal() ].fieldName = "ETCE_ROLEDMT";
		ftymDbFields[ tableFldConstants.roledmt.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.rolejh.ordinal() ].fieldName = "ETCE_ROLEJH";
		ftymDbFields[ tableFldConstants.rolejh.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ETCE_TEMPFIELD1";
		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ETCE_TEMPFIELD2";
		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ETCE_TEMPFIELD3";
		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ETCE_TEMPFIELD4";
		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ETCE_TEMPFIELD5";
		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ETCE_CREATEDBY";
		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ETCE_ACTIVE";
		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ETCE_CREATEDON";
		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ETCE_MODIFIEDON";
		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TRGCALEMP, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TRGCALEMP, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TRGCALEMP ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

