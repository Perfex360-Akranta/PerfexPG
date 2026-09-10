package com.akranta.tpm.dao.sql;

public class BAL_BdmTlMultipleRespSql {

	public static final String TBL_BDM_TL_MULTIPLE_RESP = "BDM_TL_MULTIPLE_RESP";  

	TableFieldType [] bdrsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, refid, resp_empid, tempfield, active
	}

	public TableFieldType[] getBdrsDbFields() {
		return bdrsDbFields;
	}

	public BAL_BdmTlMultipleRespSql()
	{
		bdrsDbFields = new TableFieldType[ 5 ];
		for(int i = 0;i < 5; i++)
		{	
			bdrsDbFields[ i ] = new TableFieldType();
		}
		bdrsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "BDRS_KEYID";
		bdrsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		bdrsDbFields[ tableFldConstants.refid.ordinal() ].fieldName = "BDRS_REFID";
		bdrsDbFields[ tableFldConstants.refid.ordinal() ].fieldType = 'V';

		bdrsDbFields[ tableFldConstants.resp_empid.ordinal() ].fieldName = "BDRS_RESP_EMPID";
		bdrsDbFields[ tableFldConstants.resp_empid.ordinal() ].fieldType = 'V';

		bdrsDbFields[ tableFldConstants.tempfield.ordinal() ].fieldName = "BDRS_TEMPFIELD";
		bdrsDbFields[ tableFldConstants.tempfield.ordinal() ].fieldType = 'C';

		bdrsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BDRS_ACTIVE";
		bdrsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_MULTIPLE_RESP, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_BDM_TL_MULTIPLE_RESP, fieldTypeArr, dataArray));
		
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
		sql.append(TBL_BDM_TL_MULTIPLE_RESP );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}
	
	public static String getDeleteSqlstr(String bdmKeyID)
	{
		String sql = "DELETE from " + TBL_BDM_TL_MULTIPLE_RESP ;
		   sql += " where BDRS_REFID = '" +  bdmKeyID + "'";
	return sql;
	}

}

