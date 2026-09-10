package com.akranta.tpm.dao.sql;

public class BAL_BdmTlCausemstSql {

	public static final String TBL_BDM_TL_CAUSEMST = "BDM_TL_CAUSEMST";  

	TableFieldType [] bcsmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, code, phenomenaid, remarks, iscausedefined, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getBcsmDbFields() {
		return bcsmDbFields;
	}

	public BAL_BdmTlCausemstSql()
	{
		bcsmDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			bcsmDbFields[ i ] = new TableFieldType();
		}
		bcsmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "BCSM_KEYID";
		bcsmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		bcsmDbFields[ tableFldConstants.name.ordinal() ].fieldName = "BCSM_NAME";
		bcsmDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		bcsmDbFields[ tableFldConstants.code.ordinal() ].fieldName = "BCSM_CODE";
		bcsmDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		bcsmDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldName = "BCSM_PHENOMENAID";
		bcsmDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldType = 'V';

		bcsmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "BCSM_REMARKS";
		bcsmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		bcsmDbFields[ tableFldConstants.iscausedefined.ordinal() ].fieldName = "BCSM_ISCAUSEDEFINED";
		bcsmDbFields[ tableFldConstants.iscausedefined.ordinal() ].fieldType = 'C';

		bcsmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BCSM_ACTIVE";
		bcsmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		bcsmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "BCSM_CREATEDBY";
		bcsmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		bcsmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "BCSM_CREATEDON";
		bcsmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		bcsmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "BCSM_MODIFIEDON";
		bcsmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_CAUSEMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_CAUSEMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_CAUSEMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

