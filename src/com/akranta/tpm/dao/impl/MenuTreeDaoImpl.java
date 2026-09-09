package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.MenuTreeDao;
import com.akranta.tpm.dao.sql.AdmTlMenumstSql;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.MenureightsSql;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MenuTree;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class MenuTreeDaoImpl implements MenuTreeDao {

	private DBActionTemplate dbActionTemplate;
	public MenuTreeDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate =dbActionTemplate;
	}
	
	public  List<MenuTree> getMenuTreeValues(String userId) throws Exception
	{
		try
		{
			//ReportProcedures reportProcedures = new ReportProcedures();
			
			//reportProcedures.setFunctionName("ADM_PC_ADMINISTRATION.ADM_FN_MENUMST");
			
			
		//	List<String []> resultList =  reportProcedures.execute();
			List<String> paramValues = new ArrayList<String>();
			List<String []> resultList = dbActionTemplate.   processFunctionCalls("ADM_PC_ADMINISTRATION.ADM_FN_MENUMST", paramValues);
			//return fillMenuTree(rs);
			return fillMenuTree(resultList);
			
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg(e.toString());
		}
		return null;
	}

	public  List<MenuTree> getAllMneus(MenuTree menuTree,String userid) throws Exception
	{
		try
		{
		/*	ReportProcedures reportProcedures = new ReportProcedures();
			
			reportProcedures.setFunctionName("ADM_PC_ADMINISTRATION.ADM_FN_GETUSERMENUS");
			reportProcedures.getParamValues().add(menuTree.getParentNumber());
			reportProcedures.getParamValues().add(userid);
			List resultList = reportProcedures.execute();
		*/	
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(menuTree.getParentNumber());
			String condition = "";
			
			if(Integer.parseInt(menuTree.getParentNumber())==0) {
				condition=" AND	MNUM_MENULEVEL= 1";
			}
			else if(Integer.parseInt(menuTree.getParentNumber())!=-1) {
				condition=" AND MNUM_PARENTNUMBER <> MNUM_MENUNUMBER AND MNUM_PARENTNUMBER =  '"+ menuTree.getParentNumber()+"'"; 
			}
			paramValues.add(userid);
			
			
			/*
			 * String sql=
			 * " SELECT MNUM_MENUNUMBER,MNUM_PARENTNUMBER,MNUM_MENUNAME,MNUM_MENUCAPTION,MNUM_FORMNAME, \r\n"
			 * +" MNUM_MODE,MNUM_ISPARENT,MNUM_MENULEVEL,MNUM_IMAGEINDEX,MNUM_ISMASTER,MNUM_ROOTNUMBER,MNUM_SORTNUMBER, \r\n"
			 * +" TRIM(MNUM_TABLENAME),TRIM(MNUM_LOADFORMARGUMENT),TRIM(MNUM_MASTINTEGSQL),TRIM(MNUM_MASTINTEGORDERBYSQL), \r\n"
			 * +" MNUM_SIMILARCOLUMN,MNUM_SHORTCUTKEY,MNUM_ACTIVE,MNUM_CREATEDON,ARML_MENUID,ARUL_USERID \r\n"
			 * 
			 * +" FROM ADM_VW_MENUMST , ADM_VW_MENUUSERRIGHTS \r\n"
			 * +" WHERE MNUM_MENUNUMBER = ARML_MENUID \r\n"
			 * +" AND MNUM_PARENTNUMBER IN (SELECT DISTINCT B.MNUM_MENUNUMBER AS PARENTNO \r\n"
			 * +" FROM ADM_VW_MENUMST A, ADM_VW_MENUMST B \r\n"
			 * +" WHERE A.MNUM_PARENTNUMBER=B.MNUM_MENUNUMBER) \r\n"
			 * +" AND ARUL_USERID = '"+userid.trim()+"'  "+condition+" "
			 * +" ORDER BY   mnum_menulevel, mnum_sortnumber, mnum_parentnumber,mnum_menunumber "
			 * ;
			 */
			
			
			/*
			 * String sql=
			 * " SELECT MNUM_MENUNUMBER,MNUM_PARENTNUMBER,MNUM_MENUNAME,MNUM_MENUCAPTION,MNUM_FORMNAME, "
			 * +" MNUM_MODE,MNUM_ISPARENT,MNUM_MENULEVEL,MNUM_IMAGEINDEX,MNUM_ISMASTER,MNUM_ROOTNUMBER,MNUM_SORTNUMBER, "
			 * +" TRIM(MNUM_TABLENAME),TRIM(MNUM_LOADFORMARGUMENT),TRIM(MNUM_MASTINTEGSQL),TRIM(MNUM_MASTINTEGORDERBYSQL), "
			 * +" MNUM_SIMILARCOLUMN,MNUM_SHORTCUTKEY,MNUM_ACTIVE,MNUM_CREATEDON,ARML_MENUID,ARUL_USERID "
			 * +" FROM ADM_VW_MENUMST M JOIN ADM_VW_MENUUSERRIGHTS ON MNUM_MENUNUMBER = ARML_MENUID "
			 * +"  WHERE   "
			 * +" EXISTS  ( SELECT 1  FROM ADM_VW_MENUMST A JOIN ADM_VW_MENUMST B ON A.MNUM_PARENTNUMBER=B.MNUM_MENUNUMBER "
			 * +" WHERE  M.MNUM_PARENTNUMBER = B.MNUM_MENUNUMBER  ) AND "
			 * +"  ARUL_USERID = '"+userid.trim()+"'  "+condition+" "
			 * +" ORDER BY   mnum_menulevel, mnum_sortnumber, mnum_parentnumber,mnum_menunumber "
			 * ;
			 */
			
			String sql= " SELECT MNUM_MENUNUMBER,MNUM_PARENTNUMBER,MNUM_MENUNAME,MNUM_MENUCAPTION,MNUM_FORMNAME, "
					 +" MNUM_MODE,MNUM_ISPARENT,MNUM_MENULEVEL,MNUM_IMAGEINDEX,MNUM_ISMASTER,MNUM_ROOTNUMBER,MNUM_SORTNUMBER, "
					 +" TRIM(MNUM_TABLENAME),TRIM(MNUM_LOADFORMARGUMENT),TRIM(MNUM_MASTINTEGSQL),TRIM(MNUM_MASTINTEGORDERBYSQL), "
					 +" MNUM_SIMILARCOLUMN,MNUM_SHORTCUTKEY,MNUM_ACTIVE,MNUM_CREATEDON,ARML_MENUID,ARUL_USERID "
					 +" FROM ADM_VW_MENUMST M JOIN ADM_VW_MENUUSERRIGHTS ON MNUM_MENUNUMBER = ARML_MENUID "
				     +"  WHERE   "
//				     +" EXISTS  ( SELECT 1  FROM ADM_VW_MENUMST A JOIN ADM_VW_MENUMST B ON A.MNUM_PARENTNUMBER=B.MNUM_MENUNUMBER "
//				     +" WHERE  M.MNUM_PARENTNUMBER = B.MNUM_MENUNUMBER  ) AND "
				     +"  ARUL_USERID = '"+userid.trim()+"'  "+condition+" "
				     +" ORDER BY   mnum_menulevel, mnum_sortnumber, mnum_parentnumber,mnum_menunumber ";
			
			CommonMessage.debugMsg("sqlsqlsqlsql"+ sql );
			List<String []> resultList = dbActionTemplate.getDataList(sql);
			
			CommonMessage.debugMsg(" resultListresultList   "+resultList.size());
			//return fillMenuTree(rs);
			return fillMenuTree(resultList);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
		
	}
		
	public  List<MenuTree> getUserRoleMenus(MenuTree menuTree,String userid) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(menuTree.getParentNumber());
			paramValues.add(userid);
			List<String []> resultList = dbActionTemplate.processFunctionCalls("ADM_FN_GETUSERROLEMENUS", paramValues);
			return fillMenuTree(resultList);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	public List<String[]> getChildPath(String menuCaption, String userId) throws NoDataFoundException, Exception{
		String sql = AdmTlMenumstSql.getMenuSearchSql();
		Object [] args = { "%"+menuCaption + "%",userId };
		
		return  dbActionTemplate.getDataList(sql, args);
	}
	
	public List<String[]> getMenuChildPathByMenuNumber(String menuNumber, String userId) throws NoDataFoundException, Exception{
		String sql = AdmTlMenumstSql.getMenuSearchByMenuNumberSql();
		
		Object [] args = { menuNumber,userId };
		
		return  dbActionTemplate.getDataList(sql, args);
	}
	
/*	private List<MenuTree> fillMenuTree(ResultSet rs) throws SQLException
	{
		
		   List<MenuTree> menus = new ArrayList<MenuTree>();	
		   while(rs.next())
		   {
				MenuTree tempMenuTree = new MenuTree();
				
				tempMenuTree.setMenuNumber(rs.getString(0));
				tempMenuTree.setParentNumber(rs.getString(1));
				tempMenuTree.setMenuName(rs.getString(2));
				tempMenuTree.setMenuCaption(rs.getString(3));
				tempMenuTree.setParent(rs.getString(4).equals("Y") ? true:false);
				tempMenuTree.setMenuLevel(rs.getString(7));
				tempMenuTree.setMenuSortNumber(rs.getString(11));
				menus.add(tempMenuTree);
			}
			return menus;
	 }
 */
	private List<MenuTree> fillMenuTree(List<String []> resultList) throws SQLException
	{
		CommonMessage.debugMsg(" In side the FillMenu tree");
		
		   List<MenuTree> menus = new ArrayList<MenuTree>();
		   for( String [] row : resultList )
		   {
			    MenuTree tempMenuTree = new MenuTree();
			    tempMenuTree.setReportFileName(row[ 4 ]);
				tempMenuTree.setMenuNumber(row[ 0 ]);
				tempMenuTree.setParentNumber(row[ 1 ]);
				tempMenuTree.setMenuName(row[ 13 ]);
				tempMenuTree.setMenuCaption(row[ 14 ]);
				tempMenuTree.setParent(row[ 6 ].equals("Y") ? true:false);
				tempMenuTree.setMenuLevel(row[ 7 ]);
				tempMenuTree.setMaster(row[ 9 ].equals("Y") ? true:false);
				tempMenuTree.setMenuSortNumber(row[ 11 ]);
				tempMenuTree.setFormName(row[ 15 ]);
				tempMenuTree.setRelatedFilter(row[ 16 ]);
				tempMenuTree.setFilterNeed(  row[ 12 ].isEmpty() ? 'N' : row[ 12 ].charAt(0) );
				
				menus.add(tempMenuTree);
		   }
  		  return menus;
	 }

	@Override
	public List<String[]> getAllQlinkList(MenuTree menuTree, String usrm_keyid)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{	
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(menuTree.getParentNumber());
			paramValues.add(usrm_keyid);
			/*Added for Demo purpose*/
			String sql = "select * from gen_vw_quicklink_menu order by pillar";
			List<String []> resultQlinkList = dbActionTemplate.getDataList(sql);
			/*Commented for Demo purpose*/
			//List<String []> resultQlinkList = dbActionTemplate.processFunctionCalls("ADM_PC_ADMINISTRATION.ADM_FN_GETQLINKMENUS", paramValues);
			//return fillMenuTree(rs);
			return resultQlinkList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	public List<String[]> addmenuview1(CommonFilter commonfilter,GridParams gridparam)throws Exception{
		String functional=commonfilter.getFlid();	    
 	    String roleId=commonfilter.getActionKeyId();
 	    String userId=commonfilter.getVisualKeyId();
	    String innerSql=MenureightsSql.getSelectroleName(functional, roleId, userId);
	    String countSql=CommonFilterSqls.countSql(innerSql,gridparam.getGridFilters());
		String viewinfo=dbActionTemplate.getSingleValue(countSql);
	    long counts=Long.parseLong(viewinfo);
	    if( counts >0){
	    	String sql = CommonFilterSqls.addPaginationParams(innerSql,gridparam); 
	    	gridparam.setTotalRecordCnt(counts);
	    	List<String[]>datacon=dbActionTemplate.getDataList(sql);
		  	return datacon;
	    }
	    throw new NoDataFoundException("No Data Found");
}
		public Workbook getmenuRightsExcel(CommonFilter commonFilter, JSONObject tblJSONObj,
				String format) throws Exception {
			// TODO Auto-generated method stub
			ResultSet rs = null;
			   try{
				rs =gettasknoteRptResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				return excelUtils.writeToExcel(rs,format,0,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		}
		private ResultSet gettasknoteRptResultSet(CommonFilter commonFilter) throws Exception {
			// TODO Auto-generated method stub
			List<String> paramValues = getFilterParamValues(commonFilter);
		    String functional=commonFilter.getFlid();	    
	 	    String roleId=commonFilter.getActionKeyId();
	 	    String userId=commonFilter.getVisualKeyId();
			String sql = MenureightsSql.getSelectroleName(functional,roleId,userId);
		    return dbActionTemplate.getData(sql);
		}
		private List<String> getFilterParamValues(CommonFilter commonFilter){
			// TODO Auto-generated method stub
			List<String> paramValues = new ArrayList<String>();
			String condParms ="";
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			paramValues.add(condParms);
			paramValues.add(commonParams);
			return paramValues;
		}
		
		public List<String[]> addmenureport(CommonFilter commonfilter,GridParams gridParam)throws Exception{
			String functional=commonfilter.getFlid();
			String role=commonfilter.getRoleId() != null? commonfilter.getRoleId().getId():"";
			String menu=commonfilter.getMainkeyid();
			String innnerSql=MenureightsSql.getValue(functional,role,menu);
			String countsSql=CommonFilterSqls.countSql(innnerSql,gridParam.getGridFilters());
			String viewinformation=dbActionTemplate.getSingleValue(countsSql);
		    long counting=Long.parseLong(viewinformation);
		    if( counting >0){
		    	String Sql = CommonFilterSqls.addPaginationParams(innnerSql,gridParam); 
		    	gridParam.setTotalRecordCnt(counting);
		    	List<String[]>datacontent=dbActionTemplate.getDataList(Sql);
			  	return datacontent;
		    }
			throw new NoDataFoundException("No Data Found Exception");
			}
		public Workbook getmenurightreportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,
				String format) throws Exception {
			// TODO Auto-generated method stub
			   ResultSet rs = null;
			   try{
				rs =gettasknoteRptResultSets(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				return excelUtils.writeToExcel(rs,format,0,0,0 );
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		}
		private ResultSet gettasknoteRptResultSets(CommonFilter commonFilter) throws Exception {
			// TODO Auto-generated method stub
			List<String> paramValues = getFilterParamValue(commonFilter);   
			String functional=commonFilter.getFlid();
			String role=commonFilter.getRoleId() != null? commonFilter.getRoleId().getId():"";
			String menu=commonFilter.getMainkeyid();
			String Sqlcon=MenureightsSql.getValue(functional,role,menu);
		    return dbActionTemplate.getData(Sqlcon);
		}
		private List<String> getFilterParamValue(CommonFilter commonFilter){
			// TODO Auto-generated method stub
			List<String> paramValues = new ArrayList<String>();
			String condParms ="";
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			paramValues.add(condParms);
			paramValues.add(commonParams);
			return paramValues;
		}
}
