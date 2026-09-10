package com.akranta.tpm.dao.sql;

public class BAL_WomTlCommunicationlogSql {

	public static final String TBL_WOM_TL_COMMUNICATIONLOG = "WOM_TL_COMMUNICATIONLOG";  

	TableFieldType [] wcmlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, wonumber, date, communicationtext, level, enteredby, displayorderno
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getWcmlDbFields() {
		return wcmlDbFields;
	}

	public BAL_WomTlCommunicationlogSql()
	{
		wcmlDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			wcmlDbFields[ i ] = new TableFieldType();
		}
		wcmlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WCML_KEYID";
		wcmlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		wcmlDbFields[ tableFldConstants.wonumber.ordinal() ].fieldName = "WCML_WONUMBER";
		wcmlDbFields[ tableFldConstants.wonumber.ordinal() ].fieldType = 'V';

		wcmlDbFields[ tableFldConstants.date.ordinal() ].fieldName = "WCML_DATE";
		wcmlDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		wcmlDbFields[ tableFldConstants.communicationtext.ordinal() ].fieldName = "WCML_COMMUNICATIONTEXT";
		wcmlDbFields[ tableFldConstants.communicationtext.ordinal() ].fieldType = 'V';

		wcmlDbFields[ tableFldConstants.level.ordinal() ].fieldName = "WCML_LEVEL";
		wcmlDbFields[ tableFldConstants.level.ordinal() ].fieldType = 'V';

		wcmlDbFields[ tableFldConstants.enteredby.ordinal() ].fieldName = "WCML_ENTEREDBY";
		wcmlDbFields[ tableFldConstants.enteredby.ordinal() ].fieldType = 'V';

		wcmlDbFields[ tableFldConstants.displayorderno.ordinal() ].fieldName = "WCML_DISPLAYORDERNO";
		wcmlDbFields[ tableFldConstants.displayorderno.ordinal() ].fieldType = 'N';

		wcmlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "WCML_ACTIVE";
		wcmlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		wcmlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WCML_CREATEDBY";
		wcmlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wcmlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WCML_CREATEDON";
		wcmlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wcmlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WCML_MODIFIEDON";
		wcmlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOM_TL_COMMUNICATIONLOG, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOM_TL_COMMUNICATIONLOG, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_WOM_TL_COMMUNICATIONLOG ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

