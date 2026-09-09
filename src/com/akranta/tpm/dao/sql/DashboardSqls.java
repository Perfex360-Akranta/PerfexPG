package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;

public class DashboardSqls {
	
	public static String getDashboardPillars(){
		return "select distinct dash_pillarname,dash_pillarcode,DASH_IMAGEPATH,DASH_ORDER from adm_tl_dashboad order by DASH_ORDER";
	}
	
	public static String getDashboardRptDetailsSql(String pillarCode,String userid){
		String condsql;
		String sql ;
		
		if("JHL".equals(pillarCode))
			condsql = " and dash_keyid in( SELECT dash_keyid  FROM adm_tl_dashboad WHERE dash_roleid = 'AROL0006')";
		else if("DMT".equals(pillarCode))
			condsql = " and dash_keyid in( SELECT dash_keyid  FROM adm_tl_dashboad WHERE dash_roleid = 'AROL0006' union SELECT dash_keyid  FROM adm_tl_dashboad WHERE dash_keyid in ('DSH00036'))";
		else
			condsql = "and dash_pillarcode = '"+ pillarCode +"'" ;
		  sql = " select dash_keyid as key_id,dash_title as title,dash_reportUrl report_Url,dash_type as type from adm_tl_dashboad where dash_active ='Y' "+condsql;
		  sql = sql + " ORDER BY DASH_ORDER ";
		 return  sql;
	}
}
