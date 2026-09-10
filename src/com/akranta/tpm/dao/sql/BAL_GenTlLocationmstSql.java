package com.akranta.tpm.dao.sql;

public class BAL_GenTlLocationmstSql {

	public static final String TBL_GEN_TL_LOCATIONMST = "GEN_TL_LOCATIONMST";  

	TableFieldType [] locnDbFields = null;

	public enum   tableFldConstants
	{
		keyid, companyid, name, code, description, tempfield1, tempfield2
		, tempfield3, flid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getLocnDbFields() {
		return locnDbFields;
	}

	public BAL_GenTlLocationmstSql()
	{
		locnDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			locnDbFields[ i ] = new TableFieldType();
		}
		locnDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "LOCN_KEYID";
		locnDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		locnDbFields[ tableFldConstants.companyid.ordinal() ].fieldName = "LOCN_COMPANYID";
		locnDbFields[ tableFldConstants.companyid.ordinal() ].fieldType = 'V';

		locnDbFields[ tableFldConstants.name.ordinal() ].fieldName = "LOCN_NAME";
		locnDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		locnDbFields[ tableFldConstants.code.ordinal() ].fieldName = "LOCN_CODE";
		locnDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		locnDbFields[ tableFldConstants.description.ordinal() ].fieldName = "LOCN_DESCRIPTION";
		locnDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		locnDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "LOCN_TEMPFIELD1";
		locnDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		locnDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "LOCN_TEMPFIELD2";
		locnDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		locnDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "LOCN_TEMPFIELD3";
		locnDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		

		locnDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "LOCN_FLID";
		locnDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		locnDbFields[ tableFldConstants.active.ordinal() ].fieldName = "LOCN_ACTIVE";
		locnDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		locnDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "LOCN_CREATEDBY";
		locnDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		locnDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "LOCN_CREATEDON";
		locnDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		locnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "LOCN_MODIFIEDON";
		locnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_LOCATIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_LOCATIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_LOCATIONMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getLocationmstSql() {
		// TODO Auto-generated method stub
		return  " SELECT * from " + TBL_GEN_TL_LOCATIONMST + " where LOCN_KEYID = ? ";
	}
}

