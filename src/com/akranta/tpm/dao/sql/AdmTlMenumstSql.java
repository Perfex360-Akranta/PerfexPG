package com.akranta.tpm.dao.sql;

public class AdmTlMenumstSql {
	
	public static String getMenuSearchSql(){
		
		return " select distinct  childPath from " + TableNames.TBL_ADM_VW_MENUTREESEARCH  +
		 	   " where upper(menucaption) like upper(?) and USRT_USERID = ? " ; // and childPath  is not null " ;
	}

	public static String getMenuSearchByMenuNumberSql(){
		
		return " select distinct  childPath from " + TableNames.TBL_ADM_VW_MENUTREESEARCH  +
		 	   " where menunumber = ? and USRT_USERID = ? " ; // and childPath  is not null " ;
	}

}
