package com.akranta.tpm.dao.sql;

public class GenTlMomGroupmstSql {

	public static final String TBL_GEN_TL_MOM_GROUPMST = "GEN_TL_MOM_GROUPMST";
	public static final String TBL_GEN_TL_EMPLOYEEMST = "GEN_TL_EMPLOYEEMST";  
	public static final String TBL_GEN_TL_MOM_GROUPDTL = "GEN_TL_MOM_GROUPDTL";  
	public static final String TBL_GEN_TL_FNLNROLETEAM = "GEN_TL_FNLNROLETEAM";
	public static final String TBL_ADM_TL_ROLEMST = "ADM_TL_ROLEMST";
	TableFieldType [] mgrmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, pillarid, flid, emailid, tempfield2, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getMgrmDbFields() {
		return mgrmDbFields;
	}

	public GenTlMomGroupmstSql()
	{
		mgrmDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			mgrmDbFields[ i ] = new TableFieldType();
		}
		mgrmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MGRM_KEYID";
		mgrmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mgrmDbFields[ tableFldConstants.name.ordinal() ].fieldName = "MGRM_NAME";
		mgrmDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		mgrmDbFields[ tableFldConstants.pillarid.ordinal() ].fieldName = "MGRM_PILLARID";
		mgrmDbFields[ tableFldConstants.pillarid.ordinal() ].fieldType = 'V';

		mgrmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "MGRM_FLID";
		mgrmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		mgrmDbFields[ tableFldConstants.emailid.ordinal() ].fieldName = "MGRM_EMAILID";
		mgrmDbFields[ tableFldConstants.emailid.ordinal() ].fieldType = 'V';

		mgrmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MGRM_TEMPFIELD2";
		mgrmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mgrmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MGRM_ACTIVE";
		mgrmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mgrmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MGRM_CREATEDBY";
		mgrmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mgrmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MGRM_CREATEDON";
		mgrmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mgrmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MGRM_MODIFIEDON";
		mgrmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MOM_GROUPMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_GEN_TL_MOM_GROUPMST, fieldTypeArr, dataArray));
		
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
		sql.append(TBL_GEN_TL_MOM_GROUPMST );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}

	
	/*
	 * public static String getGroupGridDataSql(String mstkeyid) { StringBuilder
	 * sql=new StringBuilder(); sql.append(
	 * "SELECT MGRD_MGRM_KEYID, MGRD_KEYID, EMPM_KEYID, EMPM_CODE as Code, EMPM_NAME as Name FROM "
	 * ); sql.append(TBL_GEN_TL_MOM_GROUPDTL); sql.append(" join ");
	 * sql.append(TBL_GEN_TL_EMPLOYEEMST); sql.append(" on ");
	 * sql.append("GEN_TL_MOM_GROUPDTL.MGRD_EMPM_KEYID"); sql.append(" = ");
	 * sql.append("GEN_TL_EMPLOYEEMST.EMPM_KEYID"); sql.append(" Where ");
	 * sql.append(" MGRD_MGRM_KEYID"); sql.append(" = '"); sql.append(mstkeyid);
	 * sql.append("'"); sql.append("AND EMPM_ACTIVE='Y'");
	 * 
	 * return sql.toString(); }
	 */
	//mano
	public static String getGroupGridDataSql(String mstkeyid) {
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT MGRD_MGRM_KEYID, MGRD_KEYID, EMPM_KEYID, EMPM_CODE as Code, EMPM_NAME as Name FROM ");
	    sql.append(TBL_GEN_TL_MOM_GROUPDTL);
	    sql.append(" JOIN ");
	    sql.append(TBL_GEN_TL_EMPLOYEEMST);
	    sql.append(" ON ");
	    sql.append(TBL_GEN_TL_MOM_GROUPDTL);
	    sql.append(".MGRD_EMPM_KEYID = ");
	    sql.append(TBL_GEN_TL_EMPLOYEEMST);
	    sql.append(".EMPM_KEYID");
	    sql.append(" WHERE MGRD_MGRM_KEYID = '");
	    sql.append(mstkeyid);
	    sql.append("' AND EMPM_ACTIVE = 'Y'");
	    
	    return sql.toString();
	}
	/*
	 * public static String getempGridData(String functional, String mstkeyid) {
	 * 
	 * StringBuilder sql =new StringBuilder(); sql.
	 * append("SELECT DISTINCT EMPM_KEYID AS KEYID, EMPM_CODE AS CODE, EMPM_NAME AS Name, listagg(ROLE_NAME,',') WITHIN GROUP (ORDER BY ROLE_NAME) AS Role FROM "
	 * ); sql.append(TBL_GEN_TL_EMPLOYEEMST); sql.append(", ");
	 * sql.append(TBL_GEN_TL_FNLNROLETEAM); sql.append(" join ");
	 * sql.append(TBL_ADM_TL_ROLEMST); sql.append(" on ");
	 * sql.append("GEN_TL_FNLNROLETEAM.FRT_ROLE_KEYID = ADM_TL_ROLEMST.ROLE_KEYID");
	 * sql.append(" where "); sql.append("empm_KEYID"); sql.append("=");
	 * sql.append("FRT_EMPM_KEYID"); sql.append(" AND FRT_FNLN_KEYID");
	 * sql.append("= '"); sql.append(functional); sql.append("'");
	 * sql.append(" and "); sql.append("EMPM_KEYID"); sql.append(" not in (");
	 * sql.append(" select MGRD_EMPM_KEYID from ");
	 * sql.append(TBL_GEN_TL_MOM_GROUPDTL); sql.append(" where ");
	 * sql.append("mgrd_mgrm_keyid"); sql.append("= '"); sql.append(mstkeyid);
	 * sql.append("')"); sql.append("group by ");
	 * sql.append("EMPM_KEYID, EMPM_CODE, EMPM_NAME"); return sql.toString(); }
	 */
	//mano
	public static String getempGridData(String functional, String mstkeyid) {
	    
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT DISTINCT EMPM_KEYID AS KEYID, EMPM_CODE AS CODE, EMPM_NAME AS Name, ");
	    sql.append("string_agg(ROLE_NAME, ',' ORDER BY ROLE_NAME) AS Role FROM ");
	    sql.append(TBL_GEN_TL_EMPLOYEEMST);
	    sql.append(", ");
	    sql.append(TBL_GEN_TL_FNLNROLETEAM);
	    sql.append(" JOIN ");
	    sql.append(TBL_ADM_TL_ROLEMST);
	    sql.append(" ON ");
	    sql.append("GEN_TL_FNLNROLETEAM.FRT_ROLE_KEYID = ADM_TL_ROLEMST.ROLE_KEYID");
	    sql.append(" WHERE ");
	    sql.append("empm_KEYID");
	    sql.append(" = ");
	    sql.append("FRT_EMPM_KEYID");
	    sql.append(" AND FRT_FNLN_KEYID");
	    sql.append(" = '");
	    sql.append(functional);
	    sql.append("'");
	    sql.append(" AND ");
	    sql.append("EMPM_KEYID");
	    sql.append(" NOT IN (");
	    sql.append("SELECT MGRD_EMPM_KEYID FROM ");
	    sql.append(TBL_GEN_TL_MOM_GROUPDTL);
	    sql.append(" WHERE ");
	    sql.append("mgrd_mgrm_keyid");
	    sql.append(" = '");
	    sql.append(mstkeyid);
	    sql.append("')");
	    sql.append(" GROUP BY ");
	    sql.append("EMPM_KEYID, EMPM_CODE, EMPM_NAME");
	    return sql.toString();
	}

	/*
	 * public static String getEmpgroupViewGridData() {
	 * 
	 * StringBuilder sql =new StringBuilder(); sql.
	 * append("SELECT MG.MGRM_KEYID,MG.MGRM_NAME as EmpgroupName, COUNT(*) as NOOFMEMBERS FROM "
	 * ); sql.append(TBL_GEN_TL_MOM_GROUPMST); sql.append(" MG"); sql.append(" , ");
	 * sql.append(TBL_GEN_TL_MOM_GROUPDTL); sql.append(" MD");
	 * sql.append(" Where "); sql.append(" MG.MGRM_KEYID "); sql.append(" = ");
	 * sql.append(" MD.MGRD_MGRM_KEYID "); sql.append(" GROUP BY ");
	 * sql.append(" MG.MGRM_KEYID "); sql.append(" , ");
	 * sql.append(" MG.MGRM_NAME ");
	 * 
	 * return sql.toString();
	 * 
	 * }
	 */
	public static String getEmpgroupViewGridData() {
	    
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT MG.MGRM_KEYID, MG.MGRM_NAME as EmpgroupName, COUNT(*) as NOOFMEMBERS FROM ");
	    sql.append(TBL_GEN_TL_MOM_GROUPMST);
	    sql.append(" MG");
	    sql.append(" INNER JOIN ");
	    sql.append(TBL_GEN_TL_MOM_GROUPDTL);
	    sql.append(" MD");
	    sql.append(" ON ");
	    sql.append(" MG.MGRM_KEYID = MD.MGRD_MGRM_KEYID ");
	    sql.append(" GROUP BY ");
	    sql.append(" MG.MGRM_KEYID, ");
	    sql.append(" MG.MGRM_NAME");
	    
	    return sql.toString();
	}
	public static String getGroupmstSelectSql() {
	
		StringBuilder sql =new StringBuilder();
		sql.append("SELECT MGRM_KEYID,MGRM_NAME,MGRM_PILLARID,MGRM_FLID,MGRM_EMAILID from ");
	    sql.append(TBL_GEN_TL_MOM_GROUPMST);
	    sql.append(" Where ");
	    sql.append("MGRM_KEYID");
		sql.append(" = ");
		sql.append("?");
		return sql.toString();
	}

	public static String getGroupDetailGridData(String mstkeyid) {
		StringBuilder sql=new StringBuilder(); 
    	sql.append( "SELECT 0,MGRD_MGRM_KEYID, MGRD_KEYID,EMPM_KEYID, EMPM_CODE as Code, EMPM_NAME as Name FROM ");
    	sql.append(TBL_GEN_TL_MOM_GROUPDTL);
		sql.append(" join ");
		sql.append(TBL_GEN_TL_EMPLOYEEMST);
		sql.append(" on ");
		sql.append("GEN_TL_MOM_GROUPDTL.MGRD_EMPM_KEYID");
		sql.append(" = ");
		sql.append("GEN_TL_EMPLOYEEMST.EMPM_KEYID");
		sql.append(" Where ");
		sql.append(" MGRD_MGRM_KEYID");
		sql.append("= '");
		sql.append(mstkeyid);
		sql.append("'");
		return sql.toString();
	}

}

