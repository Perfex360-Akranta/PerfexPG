package com.akranta.tpm.dao.sql;

public class BAL_PlmTlToolsdtlSql {

	public static final String TBL_BAL_PLM_TL_TOOLSDTL = "PLM_TL_TOOLSDTL";  

	TableFieldType [] ptldDbFields = null;

	public enum   tableFldConstants
	{
		keyid, standardid, toolid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPtldDbFields() {
		return ptldDbFields;
	}

	public BAL_PlmTlToolsdtlSql()
	{
		ptldDbFields = new TableFieldType[ 7 ];
		for(int i = 0;i < 7; i++)
		{	
			ptldDbFields[ i ] = new TableFieldType();
		}
		ptldDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PTLD_KEYID";
		ptldDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ptldDbFields[ tableFldConstants.standardid.ordinal() ].fieldName = "PTLD_STANDARDID";
		ptldDbFields[ tableFldConstants.standardid.ordinal() ].fieldType = 'V';

		ptldDbFields[ tableFldConstants.toolid.ordinal() ].fieldName = "PTLD_TOOLID";
		ptldDbFields[ tableFldConstants.toolid.ordinal() ].fieldType = 'V';

		ptldDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PTLD_ACTIVE";
		ptldDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ptldDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PTLD_CREATEDBY";
		ptldDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ptldDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PTLD_CREATEDON";
		ptldDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ptldDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PTLD_MODIFIEDON";
		ptldDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BAL_PLM_TL_TOOLSDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BAL_PLM_TL_TOOLSDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BAL_PLM_TL_TOOLSDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String inactivatejhclittooldatasql(String keyId,String sysdate,String createdby) {
		// TODO Auto-generated method stub
		return "UPDATE "+ TBL_BAL_PLM_TL_TOOLSDTL +" SET PTLD_ACTIVE = 'N' , PTLD_MODIFIEDON  ='"+sysdate+"',PTLD_CREATEDBY ='"+createdby+"' Where PTLD_STANDARDID= '"+keyId+"'" ;
	}

	public static String getdelTools(String standardId) {
		// TODO Auto-generated method stub
		String sql = "delete from "+ TBL_BAL_PLM_TL_TOOLSDTL;
		return null;
	}
}

