package com.akranta.tpm.dao.sql;

public class KznTlDmcprojectprioritymstSql {

	public static final String TBL_KZN_TL_DMCPROJECTPRIORITYMST = "KZN_TL_DMCPROJECTPRIORITYMST";  

	TableFieldType [] dmpmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, dmcm_keyid, flid, approvedby, projectscore, rank, tempfield1
		, tempfield2, tempfield3, tempfield4, createdby, active, createdon
		, modifiedon
	}

	public TableFieldType[] getDmpmDbFields() {
		return dmpmDbFields;
	}

	public KznTlDmcprojectprioritymstSql()
	{
		dmpmDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			dmpmDbFields[ i ] = new TableFieldType();
		}
		dmpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DMPM_KEYID";
		dmpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dmpmDbFields[ tableFldConstants.dmcm_keyid.ordinal() ].fieldName = "DMPM_DMCM_KEYID";
		dmpmDbFields[ tableFldConstants.dmcm_keyid.ordinal() ].fieldType = 'V';

		dmpmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "DMPM_FLID";
		dmpmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		dmpmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "DMPM_APPROVEDBY";
		dmpmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		dmpmDbFields[ tableFldConstants.projectscore.ordinal() ].fieldName = "DMPM_PROJECTSCORE";
		dmpmDbFields[ tableFldConstants.projectscore.ordinal() ].fieldType = 'N';

		dmpmDbFields[ tableFldConstants.rank.ordinal() ].fieldName = "DMPM_RANK";
		dmpmDbFields[ tableFldConstants.rank.ordinal() ].fieldType = 'V';

		dmpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "DMPM_TEMPFIELD1";
		dmpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		dmpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "DMPM_TEMPFIELD2";
		dmpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		dmpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "DMPM_TEMPFIELD3";
		dmpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		dmpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "DMPM_TEMPFIELD4";
		dmpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		dmpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DMPM_CREATEDBY";
		dmpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dmpmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DMPM_ACTIVE";
		dmpmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		dmpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DMPM_CREATEDON";
		dmpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dmpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DMPM_MODIFIEDON";
		dmpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_DMCPROJECTPRIORITYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_DMCPROJECTPRIORITYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_DMCPROJECTPRIORITYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getUpdateMaster(int projectscore,String masterkeyid,String approvedby,String rank)
	{
		String sql = "Update KZN_TL_DMCPROJECTPRIORITYMST set DMPM_PROJECTSCORE="+projectscore+" ,DMPM_APPROVEDBY='"+approvedby+"',DMPM_RANK='"+rank+"' where DMPM_keyid='"+masterkeyid+"'";
		return sql;
	}

	public static String getDeleteMaster(String kppmKeyid) {
		
		String sql = " DELETE FROM  KZN_TL_DMCPROJECTPRIORITYMST  WHERE DMPM_KEYID='"+kppmKeyid+"'";
		return sql;
	}
	public static String getDeleteDetail(String kppmKeyid) {
		
		String sql = "DELETE FROM KZN_TL_DMCPROJECTPRIORITYDTL  WHERE dmdl_DMPM_KEYID='"+kppmKeyid+"'";
		return sql;
	}

}

