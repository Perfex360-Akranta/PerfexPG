package com.akranta.tpm.dao.sql;

public class KznTlKkprojectprioritymstSql {

	public static final String TBL_KZN_TL_KKPROJECTPRIORITYMST = "KZN_TL_KKPROJECTPRIORITYMST";  

	TableFieldType [] kppmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, kzpm_keyid, flid, approvedby, projectscore, tempfield1
		, tempfield2, tempfield3, tempfield4, rank, createdby, active
		, createdon, modifiedon
	}

	public TableFieldType[] getKppmDbFields() {
		return kppmDbFields;
	}

	public KznTlKkprojectprioritymstSql()
	{
		kppmDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			kppmDbFields[ i ] = new TableFieldType();
		}
		kppmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KPPM_KEYID";
		kppmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kppmDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldName = "KPPM_KZPM_KEYID";
		kppmDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldType = 'V';

		kppmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "KPPM_FLID";
		kppmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		kppmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "KPPM_APPROVEDBY";
		kppmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		kppmDbFields[ tableFldConstants.projectscore.ordinal() ].fieldName = "KPPM_PROJECTSCORE";
		kppmDbFields[ tableFldConstants.projectscore.ordinal() ].fieldType = 'N';

		kppmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KPPM_TEMPFIELD1";
		kppmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		kppmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KPPM_TEMPFIELD2";
		kppmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		kppmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KPPM_TEMPFIELD3";
		kppmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		kppmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KPPM_TEMPFIELD4";
		kppmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		kppmDbFields[ tableFldConstants.rank.ordinal() ].fieldName = "KPPM_RANK";
		kppmDbFields[ tableFldConstants.rank.ordinal() ].fieldType = 'V';

		kppmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KPPM_CREATEDBY";
		kppmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kppmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KPPM_ACTIVE";
		kppmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kppmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KPPM_CREATEDON";
		kppmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kppmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KPPM_MODIFIEDON";
		kppmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_KKPROJECTPRIORITYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_KKPROJECTPRIORITYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_KKPROJECTPRIORITYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getUpdateMaster(int projectscore,String masterkeyid,String approvedby,String rank)
	{
		String sql = "Update KZN_TL_KKPROJECTPRIORITYMST set KPPM_PROJECTSCORE="+projectscore+" ,KPPM_APPROVEDBY='"+approvedby+"',KPPM_RANK='"+rank+"' where kppm_keyid='"+masterkeyid+"'";
		return sql;
	}

	public static String getDeleteMaster(String kppmKeyid) {
		
		String sql = " DELETE FROM  KZN_TL_KKPROJECTPRIORITYMST  WHERE KPPM_KEYID='"+kppmKeyid+"'";
		return sql;
	}
	public static String getDeleteDetail(String kppmKeyid) {
		
		String sql = "DELETE FROM KZN_TL_KKPROJECTPRIORITYDTL  WHERE KPPD_KPPM_KEYID='"+kppmKeyid+"'";
		return sql;
	}
}

