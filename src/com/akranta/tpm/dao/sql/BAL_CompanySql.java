package com.akranta.tpm.dao.sql;

import java.sql.Types;

public class BAL_CompanySql {
	
	public static final String TBL_GEN_TL_COMPANYMST = "GEN_TL_COMPANYMST";
	public static final int [] comp_datatypes = new int [] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
												Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.DATE };
								
	public static String getInsertSqlForCOMP()
	{
		return "insert into " + TBL_GEN_TL_COMPANYMST + 
					 " ( comp_keyid,comp_name,comp_code,comp_address, comp_active, comp_createdby, comp_createdon, comp_modifiedon ) " +
					 " values( ?,?,?,?,?,?,?,?) ";
	}
	public static String getUpdateSqlForCOMP( )
	{
		return "update " + TBL_GEN_TL_COMPANYMST + 
					 " set comp_name = ?, comp_code = ?,comp_address = ?, comp_active = ?, " +
					 " comp_createdby = ?, comp_createdon = ?, comp_modifiedon = ?  " +
					 " where comp_keyid = ? ";
	}
	public static String getDeleteSqlForCOMP()
	{
		return " delete from " + TBL_GEN_TL_COMPANYMST +
				 " where comp_keyid = ? ";
	}
	
	public static String getSelectSqlForComp()
	{
		return " Select * from " +  TBL_GEN_TL_COMPANYMST +  " where comp_keyid = ? ";
	}
	public static String getAllCompanySqlForComp()
	{
		return " Select * from " +  TBL_GEN_TL_COMPANYMST ;
	}

}
