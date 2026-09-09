package com.akranta.tpm.dao.sql;

public class QtmTlCriticalprocessdtlSql {

	public static final String TBL_QTM_TL_CRITICALPROCESSDTL = "QTM_TL_CRITICALPROCESSDTL";  

	TableFieldType [] crpdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, crpp_keyid, method, unit, value, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getCrpdDbFields() {
		return crpdDbFields;
	}

	public QtmTlCriticalprocessdtlSql()
	{
		crpdDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			crpdDbFields[ i ] = new TableFieldType();
		}
		crpdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CRPD_KEYID";
		crpdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		crpdDbFields[ tableFldConstants.crpp_keyid.ordinal() ].fieldName = "CRPD_CRPP_KEYID";
		crpdDbFields[ tableFldConstants.crpp_keyid.ordinal() ].fieldType = 'V';

		crpdDbFields[ tableFldConstants.method.ordinal() ].fieldName = "CRPD_METHOD";
		crpdDbFields[ tableFldConstants.method.ordinal() ].fieldType = 'V';

		crpdDbFields[ tableFldConstants.unit.ordinal() ].fieldName = "CRPD_UNIT";
		crpdDbFields[ tableFldConstants.unit.ordinal() ].fieldType = 'V';

		crpdDbFields[ tableFldConstants.value.ordinal() ].fieldName = "CRPD_VALUE";
		crpdDbFields[ tableFldConstants.value.ordinal() ].fieldType = 'N';

		crpdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CRPD_TEMPFIELD1";
		crpdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		crpdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CRPD_TEMPFIELD2";
		crpdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		crpdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CRPD_TEMPFIELD3";
		crpdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		crpdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CRPD_TEMPFIELD4";
		crpdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		crpdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "CRPD_TEMPFIELD5";
		crpdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		crpdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CRPD_ACTIVE";
		crpdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		crpdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CRPD_CREATEDBY";
		crpdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		crpdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CRPD_CREATEDON";
		crpdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		crpdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CRPD_MODIFIEDON";
		crpdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_CRITICALPROCESSDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_QTM_TL_CRITICALPROCESSDTL, fieldTypeArr, dataArray));
		
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
		sql.append(TBL_QTM_TL_CRITICALPROCESSDTL );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}

}

