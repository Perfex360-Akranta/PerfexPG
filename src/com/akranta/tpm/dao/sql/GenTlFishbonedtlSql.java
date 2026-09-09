package com.akranta.tpm.dao.sql;

public class GenTlFishbonedtlSql {

	public static final String TBL_GEN_TL_FISHBONEDTL = "GEN_TL_FISHBONEDTL";  
	public static final String FishBoneId = "FB001";
	TableFieldType [] fisdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, fism_keyid, cause, parentid, orderno, levelno, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon, remarks
	}

	public TableFieldType[] getFisdDbFields() {
		return fisdDbFields;
	}

	public GenTlFishbonedtlSql()
	{
		fisdDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			fisdDbFields[ i ] = new TableFieldType();
		}
		fisdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FISD_KEYID";
		fisdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fisdDbFields[ tableFldConstants.fism_keyid.ordinal() ].fieldName = "FISD_FISM_KEYID";
		fisdDbFields[ tableFldConstants.fism_keyid.ordinal() ].fieldType = 'V';

		fisdDbFields[ tableFldConstants.cause.ordinal() ].fieldName = "FISD_CAUSE";
		fisdDbFields[ tableFldConstants.cause.ordinal() ].fieldType = 'V';

		fisdDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "FISD_PARENTID";
		fisdDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		fisdDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "FISD_ORDERNO";
		fisdDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'N';

		fisdDbFields[ tableFldConstants.levelno.ordinal() ].fieldName = "FISD_LEVELNO";
		fisdDbFields[ tableFldConstants.levelno.ordinal() ].fieldType = 'N';

		fisdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FISD_TEMPFIELD1";
		fisdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		fisdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FISD_TEMPFIELD2";
		fisdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		fisdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FISD_TEMPFIELD3";
		fisdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		fisdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FISD_TEMPFIELD4";
		fisdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		fisdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FISD_TEMPFIELD5";
		fisdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		fisdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FISD_ACTIVE";
		fisdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fisdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FISD_CREATEDBY";
		fisdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fisdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FISD_CREATEDON";
		fisdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fisdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FISD_MODIFIEDON";
		fisdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
		// ✅ NEW COLUMN
        fisdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "FISD_REMARKS";
        fisdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';


	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_FISHBONEDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_FISHBONEDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_FISHBONEDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

