package com.akranta.tpm.dao.sql;

public class AdmTlUsercustompagesSql {

	public static final String TBL_ADM_TL_USERCUSTOMPAGES = "ADM_TL_USERCUSTOMPAGES";  

	TableFieldType [] uscpDbFields = null;

	public enum   tableFldConstants
	{
		keyid, usrm_keyid, pageuri, params, formheader, displayorder
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getUscpDbFields() {
		return uscpDbFields;
	}

	public AdmTlUsercustompagesSql()
	{
		uscpDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			uscpDbFields[ i ] = new TableFieldType();
		}
		uscpDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "USCP_KEYID";
		uscpDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		uscpDbFields[ tableFldConstants.usrm_keyid.ordinal() ].fieldName = "USCP_USRM_KEYID";
		uscpDbFields[ tableFldConstants.usrm_keyid.ordinal() ].fieldType = 'V';

		uscpDbFields[ tableFldConstants.pageuri.ordinal() ].fieldName = "USCP_PAGEURI";
		uscpDbFields[ tableFldConstants.pageuri.ordinal() ].fieldType = 'V';

		uscpDbFields[ tableFldConstants.params.ordinal() ].fieldName = "USCP_PARAMS";
		uscpDbFields[ tableFldConstants.params.ordinal() ].fieldType = 'V';

		uscpDbFields[ tableFldConstants.formheader.ordinal() ].fieldName = "USCP_FORMHEADER";
		uscpDbFields[ tableFldConstants.formheader.ordinal() ].fieldType = 'V';

		uscpDbFields[ tableFldConstants.displayorder.ordinal() ].fieldName = "USCP_DISPLAYORDER";
		uscpDbFields[ tableFldConstants.displayorder.ordinal() ].fieldType = 'N';

		uscpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "USCP_TEMPFIELD1";
		uscpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		uscpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "USCP_TEMPFIELD2";
		uscpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		uscpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "USCP_TEMPFIELD3";
		uscpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		uscpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "USCP_TEMPFIELD4";
		uscpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		uscpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "USCP_TEMPFIELD5";
		uscpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		uscpDbFields[ tableFldConstants.active.ordinal() ].fieldName = "USCP_ACTIVE";
		uscpDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		uscpDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "USCP_CREATEDBY";
		uscpDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		uscpDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "USCP_CREATEDON";
		uscpDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		uscpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "USCP_MODIFIEDON";
		uscpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ADM_TL_USERCUSTOMPAGES, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ADM_TL_USERCUSTOMPAGES, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ADM_TL_USERCUSTOMPAGES ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getUpdateSqlForUser(String uscpUsrmKeyid) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE "+TBL_ADM_TL_USERCUSTOMPAGES+" SET USCP_ACTIVE = 'N' WHERE USCP_USRM_KEYID = '"+uscpUsrmKeyid+"'");
		return sql.toString();
	}

	public String getDeleteSqlForUser(String url) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM "+TBL_ADM_TL_USERCUSTOMPAGES+" WHERE USCP_PAGEURI ='"+url+"'");
		return sql.toString();
	}

	public String getUserByKeyid() {
		return "SELECT * FROM "+TBL_ADM_TL_USERCUSTOMPAGES+ " WHERE USCP_USRM_KEYID = ? AND USCP_ACTIVE = 'Y'";
	}

	

	public String getMenuMstData(String userId) {
		
		StringBuffer sql = new StringBuffer();
		String userIdd=userId.trim();
		sql.append(" SELECT  ");
		sql.append(" CASE ");
		sql.append("    WHEN m.MNUM_ISMASTER = 'Y' THEN 'true' "); 
		sql.append(" ELSE 'false' ");
		sql.append(" END AS is_master, ");
		sql.append(" m.MNUM_TABLENAME, ");
		sql.append(" m.MNUM_SIMILARCOLUMN, ");
		sql.append(" u.USCP_FORMHEADER, ");
		sql.append(" u.USCP_PAGEURI ");
		sql.append(" FROM ");
		sql.append(" ADM_TL_MENUMST m ");
		sql.append(" RIGHT JOIN ");
		sql.append(" ADM_TL_USERCUSTOMPAGES u ");
		sql.append(" ON m.MNUM_LOADFORMARGUMENT = u.USCP_PAGEURI ");
		
		  sql.append(" WHERE "); 
		  sql.append(" u.USCP_ACTIVE = 'Y' ");
		  sql.append(" AND TRIM(u.USCP_USRM_KEYID) = '"+userIdd+"'");
		 
		/*
		 * sql.
		 * append(" SELECT DECODE(MNUM_ISMASTER,'Y','true','false'),MNUM_TABLENAME,MNUM_SIMILARCOLUMN,USCP_FORMHEADER,USCP_PAGEURI "
		 * ); sql.append(" FROM ADM_TL_MENUMST,ADM_TL_USERCUSTOMPAGES  "); sql.
		 * append(" WHERE MNUM_LOADFORMARGUMENT(+) = USCP_PAGEURI AND USCP_ACTIVE='Y' AND USCP_USRM_KEYID='"
		 * +userId+"'");
		 */
		return sql.toString();
	}
}

