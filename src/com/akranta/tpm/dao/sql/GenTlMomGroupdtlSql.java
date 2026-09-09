package com.akranta.tpm.dao.sql;

public class GenTlMomGroupdtlSql {

	public static final String TBL_GEN_TL_MOM_GROUPDTL = "GEN_TL_MOM_GROUPDTL";  

	TableFieldType [] mgrdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, mgrm_keyid, empm_keyid, tempfield1, tempfield2, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMgrdDbFields() {
		return mgrdDbFields;
	}

	public GenTlMomGroupdtlSql()
	{
		mgrdDbFields = new TableFieldType[ 9 ];
		for(int i = 0;i < 9; i++)
		{	
			mgrdDbFields[ i ] = new TableFieldType();
		}
		mgrdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MGRD_KEYID";
		mgrdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mgrdDbFields[ tableFldConstants.mgrm_keyid.ordinal() ].fieldName = "MGRD_MGRM_KEYID";
		mgrdDbFields[ tableFldConstants.mgrm_keyid.ordinal() ].fieldType = 'V';

		mgrdDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "MGRD_EMPM_KEYID";
		mgrdDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		mgrdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MGRD_TEMPFIELD1";
		mgrdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mgrdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MGRD_TEMPFIELD2";
		mgrdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mgrdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MGRD_ACTIVE";
		mgrdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mgrdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MGRD_CREATEDBY";
		mgrdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mgrdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MGRD_CREATEDON";
		mgrdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mgrdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MGRD_MODIFIEDON";
		mgrdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MOM_GROUPDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_GEN_TL_MOM_GROUPDTL, fieldTypeArr, dataArray));
		
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
		sql.append(TBL_GEN_TL_MOM_GROUPDTL );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}
	
	
	
	public static String DeleteGroupMember(String keyid) {
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_GEN_TL_MOM_GROUPDTL );
		sql.append( " where " );
		sql.append("MGRD_KEYID"); 
		sql.append( " = '" );
		sql.append(keyid);
		sql.append(	"'");
		
		return sql.toString();
	}

}

