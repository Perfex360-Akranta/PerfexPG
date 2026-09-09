package com.akranta.tpm.dao.sql;

public class GenTlMchsubmchlinkSql {

	public static final String TBL_GEN_TL_MCHSUBMCHLINK = "GEN_TL_MCHSUBMCHLINK";  

	TableFieldType [] scmlDbFields = null;

	public enum   tableFldConstants
	{
		parentmchid, sectionid, cellid, childmchid, createdon
	}

	public TableFieldType[] getScmlDbFields() {
		return scmlDbFields;
	}

	public GenTlMchsubmchlinkSql()
	{
		scmlDbFields = new TableFieldType[ 5 ];
		for(int i = 0;i < 5; i++)
		{	
			scmlDbFields[ i ] = new TableFieldType();
		}
		scmlDbFields[ tableFldConstants.parentmchid.ordinal() ].fieldName = "SCML_PARENTMCHID";
		scmlDbFields[ tableFldConstants.parentmchid.ordinal() ].fieldType = 'V';

		scmlDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "SCML_SECTIONID";
		scmlDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		scmlDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "SCML_CELLID";
		scmlDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		scmlDbFields[ tableFldConstants.childmchid.ordinal() ].fieldName = "SCML_CHILDMCHID";
		scmlDbFields[ tableFldConstants.childmchid.ordinal() ].fieldType = 'V';

		scmlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SCML_CREATEDON";
		scmlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MCHSUBMCHLINK, fieldTypeArr, dataArray);
	}

	

	public static String getDeleteSql(String bdNo)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MCHSUBMCHLINK ;
			   sql += " where scml_parentmchid = '" +  bdNo + "'";
		return sql;
	}
		
}

