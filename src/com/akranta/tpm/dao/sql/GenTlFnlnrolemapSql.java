package com.akranta.tpm.dao.sql;

public class GenTlFnlnrolemapSql {

	public static final String TBL_GEN_TL_FNLNROLEMAP = "GEN_TL_FNLNROLEMAP";  

	TableFieldType [] frlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, fnln_keyid, role_keyid, noofpersons, level, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getFrlDbFields() {
		return frlDbFields;
	}

	public GenTlFnlnrolemapSql()
	{
		frlDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			frlDbFields[ i ] = new TableFieldType();
		}
		frlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FRL_KEYID";
		frlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.fnln_keyid.ordinal() ].fieldName = "FRL_FNLN_KEYID";
		frlDbFields[ tableFldConstants.fnln_keyid.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "FRL_ROLE_KEYID";
		frlDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.noofpersons.ordinal() ].fieldName = "FRL_NOOFPERSONS";
		frlDbFields[ tableFldConstants.noofpersons.ordinal() ].fieldType = 'C';

		frlDbFields[ tableFldConstants.level.ordinal() ].fieldName = "FRL_LEVEL";
		frlDbFields[ tableFldConstants.level.ordinal() ].fieldType = 'N';

		frlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FRL_TEMPFIELD1";
		frlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FRL_TEMPFIELD2";
		frlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FRL_TEMPFIELD3";
		frlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FRL_TEMPFIELD4";
		frlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FRL_TEMPFIELD5";
		frlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FRL_ACTIVE";
		frlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		frlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FRL_CREATEDBY";
		frlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		frlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FRL_CREATEDON";
		frlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		frlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FRL_MODIFIEDON";
		frlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_FNLNROLEMAP, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_FNLNROLEMAP, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_FNLNROLEMAP ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " in ('" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "')";
		return sql;
	}
	public static String getDeleteTeamSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE FROM gen_tl_fnlnroleteam where FRT_FRL_KEYID " +
			" IN ('"+
			(String)dataArray[ tableFldConstants.keyid.ordinal()]+"')" ;		
		
		return sql;
	}
	public static String getDeleteTradeSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "delete from gen_tl_Teamtradelink where FRP_FRT_KEYID in " +
				"(select  FRT_KEYID from gen_tl_fnlnroleteam where  FRT_FRL_KEYID " +
			" IN ('"+
			(String)dataArray[ tableFldConstants.keyid.ordinal()]+"'))" ;		
		
		return sql;
	}

	public static String getUpdateRoleMstSql(Object [] dataArray) {
		// TODO Auto-generated method stub
		String sql = "UPDATE ADM_TL_ROLEMST SET ROLE_LEVEL="+ (String)dataArray[ tableFldConstants.level.ordinal()];		
		sql += " where ROLE_KEYID='" +  (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'";
		return sql;
	}

}

