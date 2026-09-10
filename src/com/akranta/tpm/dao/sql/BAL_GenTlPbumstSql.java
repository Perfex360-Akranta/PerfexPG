package com.akranta.tpm.dao.sql;

public class BAL_GenTlPbumstSql {

	public static final String TBL_GEN_TL_PBUMST = "GEN_TL_PBUMST";  

	TableFieldType [] pbutDbFields = null;

	public enum   tableFldConstants
	{
		keyid, factoryid, sbuid, flid, name, code, description, tempfield1
		, tempfield2, tempfield3, tempfield4, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getPbutDbFields() {
		return pbutDbFields;
	}

	public BAL_GenTlPbumstSql()
	{
		pbutDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			pbutDbFields[ i ] = new TableFieldType();
		}
		pbutDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PBUT_KEYID";
		pbutDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pbutDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "PBUT_FACTORYID";
		pbutDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		pbutDbFields[ tableFldConstants.sbuid.ordinal() ].fieldName = "PBUT_SBUID";
		pbutDbFields[ tableFldConstants.sbuid.ordinal() ].fieldType = 'V';

		pbutDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "PBUT_FLID";
		pbutDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		pbutDbFields[ tableFldConstants.name.ordinal() ].fieldName = "PBUT_NAME";
		pbutDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		pbutDbFields[ tableFldConstants.code.ordinal() ].fieldName = "PBUT_CODE";
		pbutDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		pbutDbFields[ tableFldConstants.description.ordinal() ].fieldName = "PBUT_DESCRIPTION";
		pbutDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		pbutDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PBUT_TEMPFIELD1";
		pbutDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		pbutDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PBUT_TEMPFIELD2";
		pbutDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		pbutDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PBUT_TEMPFIELD3";
		pbutDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		pbutDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PBUT_TEMPFIELD4";
		pbutDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		pbutDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PBUT_ACTIVE";
		pbutDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pbutDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PBUT_CREATEDBY";
		pbutDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pbutDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PBUT_CREATEDON";
		pbutDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pbutDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PBUT_MODIFIEDON";
		pbutDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_PBUMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_PBUMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_PBUMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getGenTlPbumstSql() {
		// TODO Auto-generated method stub
		return " SELECT * from " + TBL_GEN_TL_PBUMST + " where PBUT_KEYID = ?  ";
	}

}

