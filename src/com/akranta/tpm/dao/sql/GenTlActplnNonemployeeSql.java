package com.akranta.tpm.dao.sql;

public class GenTlActplnNonemployeeSql {

	public static final String TBL_GEN_TL_ACTPLN_NONEMPLOYEE = "GEN_TL_ACTPLN_NONEMPLOYEE";  

	TableFieldType [] nactDbFields = null;

	public enum   tableFldConstants
	{
		
		keyid, name, apld_keyid, refdocid, remarks, tempfiled1, tempfiled2
		, tempfiled3, tempfiled4, tempfiled5, tempfiled6, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getNactDbFields() {
		return nactDbFields;
	}

	public GenTlActplnNonemployeeSql()
	{
		nactDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			nactDbFields[ i ] = new TableFieldType();
		}
		nactDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "NACT_KEYID";
		nactDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		nactDbFields[ tableFldConstants.name.ordinal() ].fieldName = "NACT_NAME";
		nactDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		nactDbFields[ tableFldConstants.apld_keyid.ordinal() ].fieldName = "NACT_APLD_KEYID";
		nactDbFields[ tableFldConstants.apld_keyid.ordinal() ].fieldType = 'V';

		nactDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "NACT_REFDOCID";
		nactDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		nactDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "NACT_REMARKS";
		nactDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		nactDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldName = "NACT_TEMPFILED1";
		nactDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldType = 'C';

		nactDbFields[ tableFldConstants.tempfiled2.ordinal() ].fieldName = "NACT_TEMPFILED2";
		nactDbFields[ tableFldConstants.tempfiled2.ordinal() ].fieldType = 'C';

		nactDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldName = "NACT_TEMPFILED3";
		nactDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldType = 'C';

		nactDbFields[ tableFldConstants.tempfiled4.ordinal() ].fieldName = "NACT_TEMPFILED4";
		nactDbFields[ tableFldConstants.tempfiled4.ordinal() ].fieldType = 'C';

		nactDbFields[ tableFldConstants.tempfiled5.ordinal() ].fieldName = "NACT_TEMPFILED5";
		nactDbFields[ tableFldConstants.tempfiled5.ordinal() ].fieldType = 'C';

		nactDbFields[ tableFldConstants.tempfiled6.ordinal() ].fieldName = "NACT_TEMPFILED6";
		nactDbFields[ tableFldConstants.tempfiled6.ordinal() ].fieldType = 'C';

		nactDbFields[ tableFldConstants.active.ordinal() ].fieldName = "NACT_ACTIVE";
		nactDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		nactDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "NACT_CREATEDBY";
		nactDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		nactDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "NACT_CREATEDON";
		nactDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		nactDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "NACT_MODIFIEDON";
		nactDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_ACTPLN_NONEMPLOYEE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_ACTPLN_NONEMPLOYEE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_ACTPLN_NONEMPLOYEE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

