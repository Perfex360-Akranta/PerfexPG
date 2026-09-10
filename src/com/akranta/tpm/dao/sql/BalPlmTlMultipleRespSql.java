package com.akranta.tpm.dao.sql;

public class BalPlmTlMultipleRespSql {

	public static final String TBL_PLM_TL_MULTIPLE_RESP = "BAL_PLM_TL_MULTIPLE_RESP";  

	TableFieldType [] pmrsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, refid, alloted_empid, completed_empid, active
	}

	public TableFieldType[] getPmrsDbFields() {
		return pmrsDbFields;
	}

	public BalPlmTlMultipleRespSql()
	{
		pmrsDbFields = new TableFieldType[ 5 ];
		for(int i = 0;i < 5; i++)
		{	
			pmrsDbFields[ i ] = new TableFieldType();
		}
		pmrsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PMRS_KEYID";
		pmrsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pmrsDbFields[ tableFldConstants.refid.ordinal() ].fieldName = "PMRS_REFID";
		pmrsDbFields[ tableFldConstants.refid.ordinal() ].fieldType = 'V';

		pmrsDbFields[ tableFldConstants.alloted_empid.ordinal() ].fieldName = "PMRS_ALLOTED_EMPID";
		pmrsDbFields[ tableFldConstants.alloted_empid.ordinal() ].fieldType = 'V';

		pmrsDbFields[ tableFldConstants.completed_empid.ordinal() ].fieldName = "PMRS_COMPLETED_EMPID";
		pmrsDbFields[ tableFldConstants.completed_empid.ordinal() ].fieldType = 'V';

		pmrsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PMRS_ACTIVE";
		pmrsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_MULTIPLE_RESP, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_PLM_TL_MULTIPLE_RESP, fieldTypeArr, dataArray));
		
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
		sql.append(TBL_PLM_TL_MULTIPLE_RESP );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}
	public static String getDeleteAlloted(String pmsWOno)
	{
		StringBuilder sql=new StringBuilder();
		sql.append("delete from ");
		sql.append(TBL_PLM_TL_MULTIPLE_RESP);
		sql.append(" where PMRS_REFID='"+pmsWOno+"'");
		sql.append(" and PMRS_COMPLETED_EMPID='-'");
		return sql.toString();
	}
	public static String getDeleteCompleted(String pmsWOno)
	{
		StringBuilder sql=new StringBuilder();
		sql.append("delete from ");
		sql.append(TBL_PLM_TL_MULTIPLE_RESP);
		sql.append(" where PMRS_REFID='"+pmsWOno+"'");
		sql.append(" and PMRS_COMPLETED_EMPID='-'");
		return sql.toString();
	}

}

