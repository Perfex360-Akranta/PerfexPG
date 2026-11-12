package com.akranta.tpm.dao.sql;

public class SheTlPlnjobemplinkSql {

	public static final String TBL_SHE_TL_PLNJOBEMPLINK = "SHE_TL_PLNJOBEMPLINK";  

	TableFieldType [] pjelDbFields = null;

	public enum   tableFldConstants
	{
		keyid, pjob_keyid, pjob_employeeid, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPjelDbFields() {
		return pjelDbFields;
	}

	public SheTlPlnjobemplinkSql()
	{
		pjelDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			pjelDbFields[ i ] = new TableFieldType();
		}
		pjelDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PJEL_KEYID";
		pjelDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pjelDbFields[ tableFldConstants.pjob_keyid.ordinal() ].fieldName = "PJEL_PJOB_KEYID";
		pjelDbFields[ tableFldConstants.pjob_keyid.ordinal() ].fieldType = 'V';

		pjelDbFields[ tableFldConstants.pjob_employeeid.ordinal() ].fieldName = "PJEL_PJOB_EMPLOYEEID";
		pjelDbFields[ tableFldConstants.pjob_employeeid.ordinal() ].fieldType = 'V';

		pjelDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PJEL_TEMPFIELD1";
		pjelDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		pjelDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PJEL_TEMPFIELD2";
		pjelDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		pjelDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PJEL_TEMPFIELD3";
		pjelDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		pjelDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PJEL_TEMPFIELD4";
		pjelDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		pjelDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PJEL_ACTIVE";
		pjelDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pjelDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PJEL_CREATEDBY";
		pjelDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pjelDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PJEL_CREATEDON";
		pjelDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pjelDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PJEL_MODIFIEDON";
		pjelDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_PLNJOBEMPLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_SHE_TL_PLNJOBEMPLINK, fieldTypeArr, dataArray));
		
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
		sql.append(TBL_SHE_TL_PLNJOBEMPLINK );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.pjob_keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.pjob_keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}
	public String DeleteEmp(String pjoKeyid) {
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_SHE_TL_PLNJOBEMPLINK );
		sql.append( " where PJEL_PJOB_KEYID='"+pjoKeyid+"'" );
		return sql.toString();
		
	}
}

