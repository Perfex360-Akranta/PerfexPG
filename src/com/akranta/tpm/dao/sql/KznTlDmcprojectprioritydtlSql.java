package com.akranta.tpm.dao.sql;

public class KznTlDmcprojectprioritydtlSql {

	public static final String TBL_KZN_TL_DMCPROJECTPRIORITYDTL = "KZN_TL_DMCPROJECTPRIORITYDTL";  

	TableFieldType [] dmdlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, dmpm_keyid, kkpm_keyid, score, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, createdby, active, createdon
		, modifiedon
	}

	public TableFieldType[] getDmdlDbFields() {
		return dmdlDbFields;
	}

	public KznTlDmcprojectprioritydtlSql()
	{
		dmdlDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			dmdlDbFields[ i ] = new TableFieldType();
		}
		dmdlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DMDL_KEYID";
		dmdlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dmdlDbFields[ tableFldConstants.dmpm_keyid.ordinal() ].fieldName = "DMDL_DMPM_KEYID";
		dmdlDbFields[ tableFldConstants.dmpm_keyid.ordinal() ].fieldType = 'V';

		dmdlDbFields[ tableFldConstants.kkpm_keyid.ordinal() ].fieldName = "DMDL_KKPM_KEYID";
		dmdlDbFields[ tableFldConstants.kkpm_keyid.ordinal() ].fieldType = 'V';

		dmdlDbFields[ tableFldConstants.score.ordinal() ].fieldName = "DMDL_SCORE";
		dmdlDbFields[ tableFldConstants.score.ordinal() ].fieldType = 'N';

		dmdlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "DMDL_TEMPFIELD1";
		dmdlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		dmdlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "DMDL_TEMPFIELD2";
		dmdlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		dmdlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "DMDL_TEMPFIELD3";
		dmdlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		dmdlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "DMDL_TEMPFIELD4";
		dmdlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		dmdlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "DMDL_TEMPFIELD5";
		dmdlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		dmdlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DMDL_CREATEDBY";
		dmdlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dmdlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DMDL_ACTIVE";
		dmdlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		dmdlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DMDL_CREATEDON";
		dmdlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dmdlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DMDL_MODIFIEDON";
		dmdlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_DMCPROJECTPRIORITYDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_DMCPROJECTPRIORITYDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_DMCPROJECTPRIORITYDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getUpdateDetails(int score,String masterkeyid,String parameterkeyid)
	{
		String sql = "Update KZN_TL_DMCPROJECTPRIORITYDTL set DMDL_SCORE ="+score+" where DMDL_dmpm_keyid ='"+masterkeyid+"' and DMDL_kkpm_keyid='"+parameterkeyid+"'";
		
		return sql;
	}
}

