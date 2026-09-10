package com.akranta.tpm.dao.sql;

public class BAL_PlmTlMultiplemethodsmstSql {

	public static final String TBL_PLM_TL_MULTIPLEMETHODSMST = "PLM_TL_MULTIPLEMETHODSMST";  

	TableFieldType [] mlmmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, refdocid, refdoctype, methoddescription, duration, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMlmmDbFields() {
		return mlmmDbFields;
	}

	public BAL_PlmTlMultiplemethodsmstSql()
	{
		mlmmDbFields = new TableFieldType[ 9 ];
		for(int i = 0;i < 9; i++)
		{	
			mlmmDbFields[ i ] = new TableFieldType();
		}
		mlmmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MLMM_KEYID";
		mlmmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mlmmDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "MLMM_REFDOCID";
		mlmmDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		mlmmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "MLMM_REFDOCTYPE";
		mlmmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		mlmmDbFields[ tableFldConstants.methoddescription.ordinal() ].fieldName = "MLMM_METHODDESCRIPTION";
		mlmmDbFields[ tableFldConstants.methoddescription.ordinal() ].fieldType = 'V';

		mlmmDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "MLMM_DURATION";
		mlmmDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'N';

		mlmmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MLMM_ACTIVE";
		mlmmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mlmmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MLMM_CREATEDBY";
		mlmmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mlmmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MLMM_CREATEDON";
		mlmmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mlmmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MLMM_MODIFIEDON";
		mlmmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_MULTIPLEMETHODSMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_MULTIPLEMETHODSMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_MULTIPLEMETHODSMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.refdocid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.refdocid.ordinal()] + "'";
		return sql;
	}

	public static String updatejhclitmthddatasql(String keyId) {
		// TODO Auto-generated method stub
		return "UPDATE "+ TBL_PLM_TL_MULTIPLEMETHODSMST +" SET MLMM_ACTIVE = 'N' Where MLMM_REFDOCID= '"+keyId+"'" ;
	}

	public String getmethodCategorySql(String jhclitkeyID) {
		// TODO Auto-generated method stub
		System.out.println("sql method master" + jhclitkeyID);
		return "SELECT * FROM " + TBL_PLM_TL_MULTIPLEMETHODSMST + "  Where MLMM_REFDOCID= '"+jhclitkeyID+"'" ;
	}
	
	public static String inactivatejhclitmthddatasql(String keyId,String sysdate,String createdby) {
		// TODO Auto-generated method stub
		return "UPDATE "+ TBL_PLM_TL_MULTIPLEMETHODSMST +" SET MLMM_ACTIVE = 'N' , MLMM_MODIFIEDON  ='"+sysdate+"',MLMM_CREATEDBY ='"+createdby+"' Where MLMM_REFDOCID= '"+keyId+"'" ;
		   
	}

}

