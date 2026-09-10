package com.akranta.tpm.dao.sql;

public class BAL_GenTlFactorymstSql {

	public static final String TBL_GEN_TL_FACTORYMST = "GEN_TL_FACTORYMST";  

	TableFieldType [] factDbFields = null;

	public enum   tableFldConstants
	{
		keyid, companyid, name, code, address, locationid,flid, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getFactDbFields() {
		return factDbFields;
	}

	public BAL_GenTlFactorymstSql()
	{
		factDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			factDbFields[ i ] = new TableFieldType();
		}
		factDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FACT_KEYID";
		factDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		factDbFields[ tableFldConstants.companyid.ordinal() ].fieldName = "FACT_COMPANYID";
		factDbFields[ tableFldConstants.companyid.ordinal() ].fieldType = 'V';

		factDbFields[ tableFldConstants.name.ordinal() ].fieldName = "FACT_NAME";
		factDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		factDbFields[ tableFldConstants.code.ordinal() ].fieldName = "FACT_CODE";
		factDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		factDbFields[ tableFldConstants.address.ordinal() ].fieldName = "FACT_ADDRESS";
		factDbFields[ tableFldConstants.address.ordinal() ].fieldType = 'V';

		factDbFields[ tableFldConstants.locationid.ordinal() ].fieldName = "FACT_LOCATIONID";
		factDbFields[ tableFldConstants.locationid.ordinal() ].fieldType = 'V';

		factDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "FACT_FLID";
		factDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		factDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FACT_ACTIVE";
		factDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		factDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FACT_CREATEDBY";
		factDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		factDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FACT_CREATEDON";
		factDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		factDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FACT_MODIFIEDON";
		factDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		
		return SqlUtils.getInsertSql(TBL_GEN_TL_FACTORYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_FACTORYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_FACTORYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getFactorymstSql() {
		// TODO Auto-generated method stub
		return  " SELECT * from " + TBL_GEN_TL_FACTORYMST + " where FACT_KEYID = ? ";
	}

}

