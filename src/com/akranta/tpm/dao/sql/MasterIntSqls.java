package com.akranta.tpm.dao.sql;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MasterIntSqls {
	
	

	public static String getRelatedSql(String menuName,Boolean [] isFunction,String count, GridParams gridParams) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, ClassNotFoundException, InstantiationException
	{
		CommonMessage.debugMsg(" menuNamemenuNamemenuName "+ menuName);
			String sql = UIUtils.getPropertyValue("com.akranta.tpm.resources.getMstSql", menuName);
			String[] getSql = sql.split("-");
			
			if(getSql[1].equals("true"))
			{
				if(count.equals("count"))
				{
					sql = "select count(*) from "+getSql[2];
				}
				else
				{
					isFunction [ 0 ] = true;
					sql = getSql[0];
				}
			}
			else if(getSql[1].equals("false"))
			{
				String condSql ="";
				if( gridParams != null)
					condSql = FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				
				
				isFunction [ 0 ] = false;
				Class c1 = Class.forName("com.akranta.tpm.dao.sql.GenMstSql");
			
				Method meth1 = c1.getMethod(getSql[0]);
				
				Object theObject1 = c1.newInstance();
			
			
				sql = (String) meth1.invoke(theObject1);
			
				if(count.equals("count"))
				{
					sql = "select count(*) from ("+sql+ " ) WHERE 1 = 1 " + condSql;
				}
				else if(count.equals("ALL")) {
					sql = " select * from ( " + sql + ") where 1 = 1 " + condSql;
				}
				else
				{
 				//	sql = "SELECT  * from ( select ROWNUM as slno, '' AS TICKVAL, '' AS TICK ,a.* from ( select  * from ( " + sql +" )  where 1 = 1 " + condSql + " ) a ) where slno >= ? and slno <= ?  ORDER BY KEYID DESC ";
					
					// sriram-3 oct16-2025
					sql = "SELECT * from ( select ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) as slno, '' AS TICKVAL, '' AS TICK ,a.* from ( select * from ( " + sql +" ) where 1 = 1 " + condSql + " ) a ) sub where slno >= CAST(? AS INTEGER) and slno <= CAST(? AS INTEGER) ORDER BY KEYID DESC ";
				}
				
			}
			
		//	CommonMessage.debugMsg(sql);
			return sql;
	}

	public static String getMakeActiveSql(String menuName,Boolean[] isFunction, String keyId) {
		CommonMessage.debugMsg("check123"+menuName);
		String sql = UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralActivate", menuName);	
		CommonMessage.debugMsg(sql);
		String[] getSql = sql.split("-");		
		CommonMessage.debugMsg("getSql[2]...."+getSql[2]+"getSql[4]..."+getSql[4]+"getSql[3]....."+getSql[3]);
		String sqls = "UPDATE "+getSql[2]+" SET "+getSql[4]+ "= 'Y' "+" WHERE " +getSql[3] + " in ('"+keyId+"')";
		
		CommonMessage.debugMsg(sqls);
		return sqls;
	}

	public static String getFuncLocActiveSql(String keys) {
		return  "SELECT count (*)  from GEN_TL_FUNCTIONALLOCN where FNLN_ORIGINALID = '"+keys+"'";
	}

	public static String getFunctionLocActive(String menuName,Boolean[] isFunction, String keys) {
		CommonMessage.debugMsg("check123");
		String sql = UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralActivate", menuName);	
		CommonMessage.debugMsg(sql);
		String[] getSql = sql.split("-");	
		String sqls= " UPDATE "+getSql[5]+"  SET FNLN_ACTIVE = 'Y' where "+getSql[6]+" =  '"+keys+"'";
		return sqls;
	}

	public static String getCheckFuncLoc(String menuName, Boolean[] isFunction,String keys) {
		
		CommonMessage.debugMsg("check123"+menuName);
		//String sql = UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralActivate", menuName);	
		//CommonMessage.debugMsg(sql);
		//String[] getSql = sql.split("-");		
		//CommonMessage.debugMsg("getSql[2]...."+getSql[2]+"getSql[4]..."+getSql[4]+"getSql[3]....."+getSql[3]);
		String sqls = " select * from  GEN_TL_FUNCTIONALLOCN where fnln_originalid = '"+keys+"'";
		CommonMessage.debugMsg(sqls);
		return sqls;
		
	}
	
 public static String getauditreportdata(){
	 return "SELECT EMPM_KEYID, EMPM_CODE,EMPM_NAME,EMPM_JOINEDDATE FROM GEN_TL_EMPLOYEEMST,GEN_TL_MASTERMODULEGROUP,adm_vw_menuuserrights,ADT_TL_TABLES WHERE ARML_MENUID=MMGR_PARENTNO AND ADT_TABLE_NAME =MMGR_TABLENAME";	 
 }
}
