package com.akranta.tpm.dao.sql;

public class PlmTlCriteriamstSql {

	public static final String TBL_PLM_TL_CRITERIAMST = "PLM_TL_CRITERIAMST";  

	TableFieldType [] criaDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, code, name, minimumpoints, maximumpoints, tradeid
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getCriaDbFields() {
		return criaDbFields;
	}

	public PlmTlCriteriamstSql()
	{
		criaDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			criaDbFields[ i ] = new TableFieldType();
		}
		criaDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CRIA_KEYID";
		criaDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		criaDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "CRIA_FLID";
		criaDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		criaDbFields[ tableFldConstants.code.ordinal() ].fieldName = "CRIA_CODE";
		criaDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		criaDbFields[ tableFldConstants.name.ordinal() ].fieldName = "CRIA_NAME";
		criaDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		criaDbFields[ tableFldConstants.minimumpoints.ordinal() ].fieldName = "CRIA_MINIMUMPOINTS";
		criaDbFields[ tableFldConstants.minimumpoints.ordinal() ].fieldType = 'N';

		criaDbFields[ tableFldConstants.maximumpoints.ordinal() ].fieldName = "CRIA_MAXIMUMPOINTS";
		criaDbFields[ tableFldConstants.maximumpoints.ordinal() ].fieldType = 'N';

		criaDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "CRIA_TRADEID";
		criaDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		criaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CRIA_TEMPFIELD2";
		criaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		criaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CRIA_TEMPFIELD3";
		criaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		criaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CRIA_TEMPFIELD4";
		criaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		criaDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "CRIA_TEMPFIELD5";
		criaDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		criaDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CRIA_ACTIVE";
		criaDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		criaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CRIA_CREATEDBY";
		criaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		criaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CRIA_CREATEDON";
		criaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		criaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CRIA_MODIFIEDON";
		criaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_CRITERIAMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_PLM_TL_CRITERIAMST, fieldTypeArr, dataArray));
		
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ) ; 
		sql.append(" = '"  ) ; 
		sql.append( (String)dataArray[ tableFldConstants.keyid.ordinal() ] );
		sql.append("'");
		return sql.toString();
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_PLM_TL_CRITERIAMST );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}

}

