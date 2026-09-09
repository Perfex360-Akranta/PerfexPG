package com.akranta.tpm.dao.sql;

public class QtmTlCriticalprocessmstSql {

	public static final String TBL_QTM_TL_CRITICALPROCESSMST = "QTM_TL_CRITICALPROCESSMST";  

	TableFieldType [] crppDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, parameter, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getCrppDbFields() {
		return crppDbFields;
	}

	public QtmTlCriticalprocessmstSql()
	{
		crppDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			crppDbFields[ i ] = new TableFieldType();
		}
		crppDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CRPP_KEYID";
		crppDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "CRPP_FLID";
		crppDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "CRPP_ELEMENTID";
		crppDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.date.ordinal() ].fieldName = "CRPP_DATE";
		crppDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		crppDbFields[ tableFldConstants.parameter.ordinal() ].fieldName = "CRPP_PARAMETER";
		crppDbFields[ tableFldConstants.parameter.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CRPP_TEMPFIELD1";
		crppDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		crppDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CRPP_TEMPFIELD2";
		crppDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		crppDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CRPP_TEMPFIELD3";
		crppDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		crppDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CRPP_TEMPFIELD4";
		crppDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		crppDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "CRPP_TEMPFIELD5";
		crppDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		crppDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CRPP_ACTIVE";
		crppDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		crppDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CRPP_CREATEDBY";
		crppDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CRPP_CREATEDON";
		crppDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		crppDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CRPP_MODIFIEDON";
		crppDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_CRITICALPROCESSMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_QTM_TL_CRITICALPROCESSMST, fieldTypeArr, dataArray));
		
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ) ; 
		sql.append(" = '"  ) ; 
		sql.append( (String)dataArray[ tableFldConstants.keyid.ordinal() ] );
		sql.append("'");
		return sql.toString();
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, String masterId)
	{
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_QTM_TL_CRITICALPROCESSMST );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append(masterId);
		sql.append(	"'");
		return sql.toString();
	}

	public static String getDeleteAllDtlSql(String masterId)
	{
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(QtmTlCriticalprocessdtlSql.TBL_QTM_TL_CRITICALPROCESSDTL );
		sql.append( " where  CRPD_CRPP_KEYID ='" );
		sql.append(masterId );
		sql.append('\'');
		return sql.toString();
	}
	public static String getDeleteDtlSql(String detailId )
	{
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(QtmTlCriticalprocessdtlSql.TBL_QTM_TL_CRITICALPROCESSDTL );
		sql.append( " where  CRPD_KEYID ='" );
		sql.append(detailId );
		sql.append('\'');
		return sql.toString();
	}
	
	public static String getSelectSql( )
	{
		StringBuilder sql = new StringBuilder( "SELECT * FROM " );
		sql.append(TBL_QTM_TL_CRITICALPROCESSMST ) ;
		sql.append( " WHERE CRPP_KEYID = ? ");
		return sql.toString();
	}
}

