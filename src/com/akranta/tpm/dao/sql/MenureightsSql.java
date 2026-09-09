package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
public class MenureightsSql {
	public static String getSelectroleName(String flid, String roleId, String userId){
	StringBuffer sql = new StringBuffer();
   sql.append("SELECT EMPM_CODE as EMPLOYEE_CODE, EMPM_NAME as EMPLOYEE_NAME, FNLN_DESCRIPTION as FUNCTIONAL_LOCATION, LISTAGG(ROLE_NAME, ', ') WITHIN GROUP (ORDER BY ROLE_NAME) as ROLE_NAME");
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
	   if(UIUtils.isValidKeyId(userId))
	      sql.append(" AND USRM_CCNO ='"+ userId +"'");
          sql.append(" GROUP BY EMPM_CODE, EMPM_NAME , FNLN_DESCRIPTION ORDER BY FNLN_DESCRIPTION, EMPM_CODE, EMPM_NAME");
		  return sql.toString();
	}
	
   public static String getValue(String functional,String role,String menuid){
	       StringBuffer sql1=new StringBuffer();
	       sql1.append(" SELECT ROLE_NAME as Role_Name, MENUPATH as Menu_Name");
	       sql1.append(" FROM ADM_VW_MENUPATH , ADM_TL_ROLE_MENU_LINK, ADM_TL_ROLEMST");
	       sql1.append(" WHERE MNUM_ACTIVE = 'Y' AND ROLE_KEYID = ARML_ROLEID AND MNUM_MENUNUMBER = ARML_MENUID");
	         if(UIUtils.isValidKeyId(role)){
		       sql1.append(" AND ROLE_KEYID='"+ role +"' ");
		       CommonMessage.debugMsg("the RoleId menuReport"+role);
	         if(UIUtils.isValidKeyId(functional)){
		            sql1.append(" AND ROLE_KEYID IN(SELECT FRT_ROLE_KEYID FROM GEN_TL_FNLNROLETEAM,GEN_MV_FLIDHIERARCHY");
		            sql1.append(" WHERE FLID =FRT_FNLN_KEYID AND INSTR(PARENTFLIDS || FLID,'" +functional+ "') >0 ) ");
	         }
	         }
	         else if(UIUtils.isValidKeyId(functional))
	          sql1.append(" AND ROLE_KEYID IN(SELECT FRT_ROLE_KEYID FROM GEN_TL_FNLNROLETEAM WHERE FRT_FNLN_KEYID='"+functional+"')");
	         if(UIUtils.isValidKeyId(menuid))
	    	   sql1.append(" AND ARML_MENUID='"+ menuid+ "' ");
		       sql1.append(" ORDER BY ROLE_NAME ");
	           return sql1.toString();
  }  
}



