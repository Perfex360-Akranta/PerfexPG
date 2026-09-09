package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;

public class AdmTlEmployeeDelPrvlSql {

	public static final String TBL_ADM_TL_EMPLOYEEDELPRVL= "ADM_TL_EMPLOYEEDELPRVL";  

	TableFieldType [] adm_DbFields = null;

	public enum   tableFldConstants
	{
		keyid,empm_keyid,empname,menunumber,parentnumber
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getadm_DbFields() {
		return adm_DbFields;
	}

	public AdmTlEmployeeDelPrvlSql()
	{
		adm_DbFields = new TableFieldType[ 9 ];
		for(int i = 0;i < 9; i++)
		{	
			adm_DbFields[ i ] = new TableFieldType();
		}
		adm_DbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "AEDP_KEYID";
		adm_DbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		adm_DbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "AEDP_EMPKEYID";
		adm_DbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';
		
		adm_DbFields[ tableFldConstants.empname.ordinal() ].fieldName = "AEDP_EMPNAME";
		adm_DbFields[ tableFldConstants.empname.ordinal() ].fieldType = 'V';
		
		adm_DbFields[ tableFldConstants.menunumber.ordinal() ].fieldName = "AEDP_MENUNUMBER";
		adm_DbFields[ tableFldConstants.menunumber.ordinal() ].fieldType = 'V';

		adm_DbFields[ tableFldConstants.parentnumber.ordinal() ].fieldName = "AEDP_PARENRNUMBER";
		adm_DbFields[ tableFldConstants.parentnumber.ordinal() ].fieldType = 'V';
		
		adm_DbFields[ tableFldConstants.active.ordinal() ].fieldName = "AEDP_ACTIVE";
		adm_DbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		adm_DbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "AEDP_CREATEDBY";
		adm_DbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		adm_DbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "AEDP_CREATEDON";
		adm_DbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		adm_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "AEDP_MODIFIEDON";
		adm_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
        
	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ADM_TL_EMPLOYEEDELPRVL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ADM_TL_EMPLOYEEDELPRVL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ADM_TL_EMPLOYEEDELPRVL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getRoleNameDetail(String flid,String roleId){
		   StringBuffer sql = new StringBuffer();
		   sql.append("SELECT EMPM_KEYID as txtaedpEmpmKeyid,EMPM_CODE AS EMPLOYEE_CODE, EMPM_NAME as txtaedpEmpName, FNLN_DESCRIPTION as FUNCTIONAL_LOCATION, LISTAGG(ROLE_NAME, ', ') WITHIN GROUP (ORDER BY ROLE_NAME) as ROLE_NAME");
		   sql.append(" FROM ADM_TL_USER_ROLE_LINK, ADM_TL_ROLEMST ,ADM_TL_USERMST, GEN_TL_EMPLOYEEMST, GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM");
		   sql.append(" WHERE USRM_KEYID = ARUL_USERID AND USRM_CCNO = EMPM_KEYID AND FNLN_KEYID = FRT_FNLN_KEYID AND EMPM_KEYID = FRT_EMPM_KEYID ");
		   sql.append(" AND ROLE_KEYID = FRT_ROLE_KEYID AND ROLE_KEYID = ARUL_ROLEID ");
		        if(UIUtils.isValidKeyId(roleId)) {
				     sql.append( " AND ROLE_KEYID = '" + roleId + "' ");
				  if(UIUtils.isValidKeyId(flid)){
				      sql.append(" AND USRM_CCNO IN(SELECT FRT_EMPM_KEYID FROM GEN_TL_FNLNROLETEAM ,GEN_MV_FLIDHIERARCHY ");
				  	  sql.append(" WHERE FLID = FRT_FNLN_KEYID AND INSTR(PARENTFLIDS || FLID,'" +flid +"') >0 ) ");
				  }
		        }
			    else if(UIUtils.isValidKeyId(flid))
				      sql.append(" AND USRM_CCNO IN(SELECT FRT_EMPM_KEYID FROM GEN_TL_FNLNROLETEAM WHERE FRT_FNLN_KEYID='" +flid +"')");
		              sql.append(" GROUP BY EMPM_KEYID, EMPM_CODE,EMPM_NAME , FNLN_DESCRIPTION ORDER BY FNLN_DESCRIPTION, EMPM_KEYID, EMPM_CODE,EMPM_NAME");
				      return sql.toString();  
	}
}

