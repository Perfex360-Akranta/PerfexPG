package com.akranta.tpm.dao.sql;

public class GenTlMachineskillmstSql {

	public static final String TBL_GEN_TL_MACHINESKILLMST = "GEN_TL_MACHINESKILLMST";  

	TableFieldType [] mskmDbFields = null;

	public enum   tableFldConstants
	{
		machineid, skilldescription, skillfordepartment, tempfield1, tempfield2
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMskmDbFields() {
		return mskmDbFields;
	}

	public GenTlMachineskillmstSql()
	{
		mskmDbFields = new TableFieldType[ 9 ];
		for(int i = 0;i < 9; i++)
		{	
			mskmDbFields[ i ] = new TableFieldType();
		}
		mskmDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "MSKM_MACHINEID";
		mskmDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		mskmDbFields[ tableFldConstants.skilldescription.ordinal() ].fieldName = "MSKM_SKILLDESCRIPTION";
		mskmDbFields[ tableFldConstants.skilldescription.ordinal() ].fieldType = 'V';

		mskmDbFields[ tableFldConstants.skillfordepartment.ordinal() ].fieldName = "MSKM_SKILLFORDEPARTMENT";
		mskmDbFields[ tableFldConstants.skillfordepartment.ordinal() ].fieldType = 'C';

		mskmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MSKM_TEMPFIELD1";
		mskmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mskmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MSKM_TEMPFIELD2";
		mskmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mskmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MSKM_ACTIVE";
		mskmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mskmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MSKM_CREATEDBY";
		mskmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mskmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MSKM_CREATEDON";
		mskmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mskmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MSKM_MODIFIEDON";
		mskmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql=SqlUtils.getInsertSql(TBL_GEN_TL_MACHINESKILLMST, fieldTypeArr, dataArray);
		
		return sql;
	}

/*	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MACHINESKILLMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MACHINESKILLMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}*/



	public static String getOperatorDeleteSql(String mchmKeyid) {
		String sql = "DELETE from " + TBL_GEN_TL_MACHINESKILLMST + " where MSKM_MACHINEID= '"+mchmKeyid+"' and MSKM_SKILLFORDEPARTMENT= 'O' ";
		return sql;
	}

	public static String getMaintainceDeleteSql(String mchmKeyid) {
		String sql = "DELETE from " + TBL_GEN_TL_MACHINESKILLMST + " where MSKM_MACHINEID= '"+mchmKeyid+"' and MSKM_SKILLFORDEPARTMENT= 'M' ";
		return sql;
	}

}

