package com.akranta.tpm.dao.sql;

public class KznTlSubcategorymstSql {

	public static final String TBL_KZN_TL_SUBCATEGORYMST = "KZN_TL_SUBCATEGORYMST";  

	TableFieldType [] kscmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, kctm_keyid, subcatname, subcatcode, slno, tempfield1, tempfield2
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getKscmDbFields() {
		return kscmDbFields;
	}

	public KznTlSubcategorymstSql()
	{
		kscmDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			kscmDbFields[ i ] = new TableFieldType();
		}
		kscmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KSCM_KEYID";
		kscmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kscmDbFields[ tableFldConstants.kctm_keyid.ordinal() ].fieldName = "KSCM_KCTM_KEYID";
		kscmDbFields[ tableFldConstants.kctm_keyid.ordinal() ].fieldType = 'V';

		kscmDbFields[ tableFldConstants.subcatname.ordinal() ].fieldName = "KSCM_SUBCATNAME";
		kscmDbFields[ tableFldConstants.subcatname.ordinal() ].fieldType = 'V';

		kscmDbFields[ tableFldConstants.subcatcode.ordinal() ].fieldName = "KSCM_SUBCATCODE";
		kscmDbFields[ tableFldConstants.subcatcode.ordinal() ].fieldType = 'V';

		kscmDbFields[ tableFldConstants.slno.ordinal() ].fieldName = "KSCM_SLNO";
		kscmDbFields[ tableFldConstants.slno.ordinal() ].fieldType = 'N';

		kscmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KSCM_TEMPFIELD1";
		kscmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		kscmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KSCM_TEMPFIELD2";
		kscmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		kscmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KSCM_ACTIVE";
		kscmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kscmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KSCM_CREATEDBY";
		kscmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kscmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KSCM_CREATEDON";
		kscmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kscmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KSCM_MODIFIEDON";
		kscmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_SUBCATEGORYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_SUBCATEGORYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_SUBCATEGORYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		sql +=" AND "  + fieldTypeArr[tableFldConstants.active.ordinal()].fieldName  +
			" = 'Y'";
		return sql;
	}


	
	public static String getUpdateSubSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql=" UPDATE KZN_TL_SUBCATEGORYMST SET KSCM_SLNO = KSCM_SLNO - 1 ";
		
		sql+= " where " + fieldTypeArr[tableFldConstants.slno.ordinal()].fieldName  +
		  " > ( SELECT KSCM_SLNO SLNO  FROM KZN_TL_SUBCATEGORYMST  ";
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "' )";
		sql += " AND " + fieldTypeArr[tableFldConstants.kctm_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.kctm_keyid.ordinal() ] + "'";
		return sql;
	}

	

}

