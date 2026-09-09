package com.akranta.tpm.dao.sql;

public class KznTlKkprojectprioritydtlSql {

	public static final String TBL_KZN_TL_KKPROJECTPRIORITYDTL = "KZN_TL_KKPROJECTPRIORITYDTL";  

	TableFieldType [] kppdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, kppm_keyid, kkpm_keyid, score, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, createdby, active, createdon
		, modifiedon
	}

	public TableFieldType[] getKppdDbFields() {
		return kppdDbFields;
	}

	public KznTlKkprojectprioritydtlSql()
	{
		kppdDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			kppdDbFields[ i ] = new TableFieldType();
		}
		kppdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KPPD_KEYID";
		kppdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kppdDbFields[ tableFldConstants.kppm_keyid.ordinal() ].fieldName = "KPPD_KPPM_KEYID";
		kppdDbFields[ tableFldConstants.kppm_keyid.ordinal() ].fieldType = 'V';

		kppdDbFields[ tableFldConstants.kkpm_keyid.ordinal() ].fieldName = "KPPD_KKPM_KEYID";
		kppdDbFields[ tableFldConstants.kkpm_keyid.ordinal() ].fieldType = 'V';

		kppdDbFields[ tableFldConstants.score.ordinal() ].fieldName = "KPPD_SCORE";
		kppdDbFields[ tableFldConstants.score.ordinal() ].fieldType = 'N';

		kppdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KPPD_TEMPFIELD1";
		kppdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		kppdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KPPD_TEMPFIELD2";
		kppdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		kppdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KPPD_TEMPFIELD3";
		kppdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		kppdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KPPD_TEMPFIELD4";
		kppdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		kppdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KPPD_TEMPFIELD5";
		kppdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		kppdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KPPD_CREATEDBY";
		kppdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kppdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KPPD_ACTIVE";
		kppdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kppdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KPPD_CREATEDON";
		kppdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kppdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KPPD_MODIFIEDON";
		kppdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_KKPROJECTPRIORITYDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_KKPROJECTPRIORITYDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.kppm_keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.kppm_keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_KKPROJECTPRIORITYDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.kppm_keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.kppm_keyid.ordinal()] + "'";
		return sql;
	}
	public static String getUpdateDetails(int score,String masterkeyid,String parameterkeyid)
	{
		String sql = "Update KZN_TL_KKPROJECTPRIORITYDTL set KPPD_SCORE ="+score+" where kppd_kppm_keyid ='"+masterkeyid+"' and kppd_kkpm_keyid='"+parameterkeyid+"'";
		
		return sql;
	}

}

