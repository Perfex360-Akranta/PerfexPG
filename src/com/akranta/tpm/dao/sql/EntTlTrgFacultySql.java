package com.akranta.tpm.dao.sql;

public class EntTlTrgFacultySql {

	public static final String TBL_ENT_TL_TRGFACULTY = "ENT_TL_TRGFACULTY";  

	TableFieldType [] ftymDbFields = null;

	public enum   tableFldConstants
	{
		keyid, etcm_keyid, etcm_flid, facultyid, facultytype, dateadd
		,tempfield1, tempfield2, tempfield3, tempfield4,tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getFtymDbFields() {
		return ftymDbFields;
	}

	public EntTlTrgFacultySql()
	{
		ftymDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			ftymDbFields[ i ] = new TableFieldType();
		}
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ETCF_KEYID";
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.etcm_keyid.ordinal() ].fieldName = "ETCF_ETCM_KEYID";
		ftymDbFields[ tableFldConstants.etcm_keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.etcm_flid.ordinal() ].fieldName = "ETCF_ETCM_FLID";
		ftymDbFields[ tableFldConstants.etcm_flid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.facultyid.ordinal() ].fieldName = "ETCF_FACULTYID";
		ftymDbFields[ tableFldConstants.facultyid.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.facultytype.ordinal() ].fieldName = "ETCF_FACULTYTYPE";
		ftymDbFields[ tableFldConstants.facultytype.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.dateadd.ordinal() ].fieldName = "ETCF_DATEADD";
		ftymDbFields[ tableFldConstants.dateadd.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ETCF_TEMPFIELD1";
		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ETCF_TEMPFIELD2";
		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ETCF_TEMPFIELD3";
		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ETCF_TEMPFIELD4";
		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ETCF_TEMPFIELD5";
		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ETCF_ACTIVE";
		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ETCF_CREATEDBY";
		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ETCF_CREATEDON";
		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ETCF_MODIFIEDON";
		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TRGFACULTY, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TRGFACULTY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TRGFACULTY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

