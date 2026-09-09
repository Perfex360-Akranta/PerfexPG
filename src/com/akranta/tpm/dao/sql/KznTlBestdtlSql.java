package com.akranta.tpm.dao.sql;

public class KznTlBestdtlSql {

	public static final String TBL_KZN_TL_BESTDTL = "KZN_TL_BESTDTL";  

	TableFieldType [] kzbdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, kzbm_keyid, kaizenid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getKzbdDbFields() {
		return kzbdDbFields;
	}

	public KznTlBestdtlSql()
	{
		kzbdDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			kzbdDbFields[ i ] = new TableFieldType();
		}
		kzbdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KZBD_KEYID";
		kzbdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kzbdDbFields[ tableFldConstants.kzbm_keyid.ordinal() ].fieldName = "KZBD_KZBM_KEYID";
		kzbdDbFields[ tableFldConstants.kzbm_keyid.ordinal() ].fieldType = 'V';

		kzbdDbFields[ tableFldConstants.kaizenid.ordinal() ].fieldName = "KZBD_KAIZENID";
		kzbdDbFields[ tableFldConstants.kaizenid.ordinal() ].fieldType = 'V';

		kzbdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KZBD_TEMPFIELD1";
		kzbdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		kzbdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KZBD_TEMPFIELD2";
		kzbdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		kzbdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KZBD_TEMPFIELD3";
		kzbdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		kzbdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KZBD_TEMPFIELD4";
		kzbdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		kzbdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KZBD_TEMPFIELD5";
		kzbdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		kzbdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KZBD_ACTIVE";
		kzbdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kzbdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KZBD_CREATEDBY";
		kzbdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kzbdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KZBD_CREATEDON";
		kzbdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kzbdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KZBD_MODIFIEDON";
		kzbdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_BESTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_BESTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_BESTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getDeletemst(String kzbmKeyid)
	{
		String sql = "DELETE from " + TBL_KZN_TL_BESTDTL ;
		
		sql += " where  KZBD_KZBM_KEYID ='" +kzbmKeyid + "'";
		return sql;
	}
}

