package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.MasterModuleGroupDao;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.MasterIntSqls;
import com.akranta.tpm.dao.sql.MenureightsSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.AuditmasterModel;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MasterModuleGroup;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class MasterModuleGroupDaoImpl implements MasterModuleGroupDao {
	

	private static final String TBL_GEN_TL_FUNCTIONALLOCN = "GEN_TL_FUNCTIONALLOCN";  
	private DBActionTemplate dbActionTemplate; 
	
	public MasterModuleGroupDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<MasterModuleGroup> getAllMasterModuleGroup(String userId,String pillar) throws Exception
	{
		try
		{
			List<String > paramValues = new ArrayList<String>();			
			paramValues.add(userId);
			paramValues.add(pillar);
			List<String[]> masterModuleGroup = dbActionTemplate.processFunctionCalls("GEN_FN_GETMASTERMODULE", paramValues);
			return fillMasterModule(masterModuleGroup);
		}
		catch (Exception e){
			throw new Exception(e.getMessage()); 
		}
	}

	public Workbook getauditdataexcel(CommonFilter commonFilter, JSONObject tblJSONObj,
			String format) throws Exception {
		// TODO Auto-generated method stub
		  
		ResultSet rs = null;
		   try{
			rs =gettasknoteRptResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format,0,0,0);
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet gettasknoteRptResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		 List<String> paramValues = getFilterParamValues(commonFilter);
		 CommonMessage.debugMsg("**get excel resultset***");
		 return dbActionTemplate.dbFunctionCall("ADM_PC_AUDITMASTER.ADM_PC_AUDITREPORTMODULE", paramValues);
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
	
	private List<MasterModuleGroup> fillMasterModule(List<String []> masterModuleGroupList)
	{
		   List<MasterModuleGroup> masterModuleGroups = new ArrayList<MasterModuleGroup>();
		   
		   for( String [] row : masterModuleGroupList )
		   {
			   MasterModuleGroup masterModule = new MasterModuleGroup();			
			   masterModule.setMenuCaption(row[ 0 ]); //MNUM_MASTINTEGSQL
			   masterModule.setMenuNumber(row[ 1 ]); //MMGR_MENUNO
			   masterModule.setMenuName(row[ 11 ]);// MNUM_MENUNAME
			   masterModule.setMenuFormName(row[ 3 ]);// MNUM_MASTINTEGORDERBYSQL
			   masterModule.setIsMMC(row[ 4 ]);//MMGR_ISMMC
			   masterModule.setModule(row[ 5 ]);//MMGR_MODULE
			   masterModule.setMenuRights(row[ 6 ]);//MENURIGHTS
			   masterModule.setTableName(row[ 7 ]);//MMGR_TABLENAME
			   masterModule.setNumberRecords(Integer.parseInt(row[ 8 ]));// MMGR_NOOFRECORDS
			   masterModule.setLastModidied(row[ 9 ].toUpperCase().replace(Constants.passNullDate.toUpperCase(),""));//LASTMODDATE
			   masterModule.setMenuFlag(row[ 10 ].equals("Y")?true:false);//ISMENUFLAG
			   masterModule.setLoadFormArgument(row[ 2 ]);//LOADFORMARGUMENT
			   
			   masterModuleGroups.add(masterModule);
		   }
		  return masterModuleGroups;
	}

public List<AuditmasterModel> getauditmastermodule(String userid,String module) throws Exception{
	
	CommonMessage.debugMsg("inside the getauditmaster module");
	List<String> paramValue=new ArrayList<String>();
	paramValue.add(userid);
	paramValue.add(module);
    List<String[]> getauditmaster=dbActionTemplate.processFunctionCalls("ADM_PC_AUDITMASTER.ADM_PC_GETAUDITMASTERMODULE",paramValue);
    CommonMessage.debugMsg("get Package Function Data Are"+getauditmaster);
    return fillauditmaster(getauditmaster);
}

private List<AuditmasterModel> fillauditmaster(List<String []> masterModuleGroupList)
{
	   List<AuditmasterModel> masterModuleGroups = new ArrayList<AuditmasterModel>();
	   
	   for( String [] row : masterModuleGroupList )
	   {
		   AuditmasterModel masterModule = new AuditmasterModel();
		   
		//  masterModule.setMenuCaption(row[ 0 ]); 
		//  masterModule.setMenuNumber(row[ 1 ]); 
		//  masterModule.setMenuName(row[ 11 ]);
		//  masterModule.setMenuFormName(row[ 3 ]);
		//  masterModule.setIsMMC(row[ 4 ]);
		//  masterModule.setModule(row[ 5 ]);
		//  masterModule.setMenuRights(row[ 6 ]);
		// masterModule.setTableName(row[ 7 ]);
		//  masterModule.setNumberRecords(Integer.parseInt(row[ 8 ]));
		//  masterModule.setLastModidied(row[ 9 ].toUpperCase().replace(Constants.passNullDate.toUpperCase(),""));//LASTMODDATE
		//   masterModule.setMenuFlag(row[ 10 ].equals("Y")?true:false);
		  masterModule.setAtdactive(row[0]);
		  masterModule.setAtdcreatedby(row[1]);
		  masterModule.setAtdcreatedon(row[2]);
		  masterModule.setAtdmodifyon(row[3]);
		  masterModule.setAtdtablename(row[4]);
		  masterModule.setAtdtriggerenable(row[5]);
		   masterModuleGroups.add(masterModule);
	   }
	  return masterModuleGroups;
}
	
	public List<String[]> getRelatedMst(String menuCaption,String menuName, String activeRecordFlag, GridParams gridParams) throws Exception
	{
		try
		{
			CommonMessage.debugMsg(" activeRecordFlag " +activeRecordFlag);
			List<String> paramValues = new ArrayList<String>();
			if( activeRecordFlag != null && activeRecordFlag.trim().equals("N"))
			{	
				paramValues.add("N");
				paramValues.add("N");
			}
			else{
				paramValues.add("Y");
				paramValues.add("Y");
			}
			
			paramValues.add(gridParams.getFromRow());
			paramValues.add(gridParams.getToRow());
			
			List<String[]> relatedMst = null;
			Boolean [ ] isFunction = new Boolean [ 1 ];
			isFunction [0] = true;
			
			
			String sql = MasterIntSqls.getRelatedSql(menuName,isFunction,"",gridParams);
	
			CommonMessage.debugMsg(" sql. " + sql + " paramValues "  +paramValues);
			if(isFunction[ 0 ].equals(true))
			{
				relatedMst = dbActionTemplate.processFunctionCallsWithColHeaders(sql, paramValues);
			}
			else{
				relatedMst = dbActionTemplate.getDataListWithColHeader(sql, paramValues);
			}
			return relatedMst;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			CommonMessage.debugMsg(" excepk "  +e.getMessage() );
			throw new Exception(e.getMessage()); 
		}
		//return null;
	}
	public String getMstDatasCount(String menuName,String activeRecordFlag,GridParams gridParams) throws Exception
	{
		try
		{
			Boolean [ ] isFunction = new Boolean [ 1 ];
			isFunction [0] = true;			
			String sql = 	null;		
			List<String> paramValues = new ArrayList<String>();
			if( activeRecordFlag != null && activeRecordFlag.trim().equals("N") ){	
				paramValues.add("N");
				paramValues.add("N");
			}
			else{
				paramValues.add("Y");
				paramValues.add("Y");
			}			
			if(isFunction[ 0 ] == true)
				sql = MasterIntSqls.getRelatedSql(menuName,isFunction, "count", gridParams);
			else	
				sql = MasterIntSqls.getRelatedSql(menuName,isFunction, "count", gridParams);
			CommonMessage.debugMsg("sql..."+sql);
  		    List<String[]> countMst = dbActionTemplate.getDataList(sql,paramValues);  		  
  		    String datas[] = countMst.get(0); 
  		    return datas[0].toString();			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
		
	}

	public Workbook generalMstFormExportExcel(String menuCaption,String menuName, String activeRecordFlag,JSONObject colModel,String format,GridParams gridParam) throws Exception {
		
		 ResultSet rs = null;
		 int colVal = 0;
		   try{
		
			rs =   getGeneralMstResultSet(menuCaption,menuName,activeRecordFlag,gridParam);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(0);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format, 0,-2,0 );
			
		   }finally{
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   } 		   
	}
	
	public ResultSet getGeneralMstResultSet(String menuCaption,String menuName,String activeRecordFlag,GridParams gridParams) throws Exception
	{
		try
		{
			Boolean [ ] isFunction = new Boolean [ 1 ];
			isFunction [0] = true;
			
			List<String> paramValues = new ArrayList<String>();
			if( activeRecordFlag != null && activeRecordFlag.equals("N") )
			{	
				paramValues.add("N");
				paramValues.add("N");
			}
			else{
				paramValues.add("Y");
				paramValues.add("Y");
			}			
			String sql = MasterIntSqls.getRelatedSql(menuName,isFunction,"ALL",gridParams);
			
			return  dbActionTemplate.getData(sql, paramValues.toArray());		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}		
	}

	public void getMakeactive(String menuCaption, String menuName,List<String> paramValues ) throws Exception {
		
			CommonMessage.debugMsg("menuName..."+menuName);			
			Boolean [ ] isFunction = new Boolean [ 1 ];
			isFunction [0] = true;
			List<String> sql =  new ArrayList<String>();
			List<String> sqls =new ArrayList<String>();			
			String keys = "'";		
			  
			for(int i=0;i<paramValues.size();i++)
			{
				keys = paramValues.get(i);				
			
				sql.add(MasterIntSqls.getMakeActiveSql(menuName,isFunction,keys));	
				if(isFunction[ 0 ].equals(true))
					 sql.add(MasterIntSqls.getFunctionLocActive(menuName,isFunction,keys));
			}
			CommonMessage.debugMsg("sqlTest..."+sql);			
			dbActionTemplate.executeStatements(sql);
	}

	
public List<String[]> getauditdata(CommonFilter commonFilter,GridParams gridparam)throws Exception{
	 
	 String innerSql=MasterIntSqls.getauditreportdata();
	 CommonMessage.debugMsg("The InnerSql Is"+innerSql);
	 String countSql=CommonFilterSqls.countSql(innerSql, gridparam.getGridFilters());
	 CommonMessage.debugMsg("The CountSql"+countSql);
	 String viewaudit=dbActionTemplate.getSingleValue(countSql);
	 long counts=Long.parseLong(viewaudit);
	 
	 if(counts >0){
		   String sql=CommonFilterSqls.addPaginationParams(innerSql,gridparam);
		    CommonMessage.debugMsg("The Sql Data"+sql);
		    gridparam.setTotalRecordCnt(counts);
		    List<String[]> auditdatacon=dbActionTemplate.getDataList(sql);
		    return auditdatacon;
	 }
   throw new NoDataFoundException("No Data Found");
}
	
	public List<AuditmasterModel> save(List<AuditmasterModel> auditsave)throws Exception{
	
			    CommonMessage.debugMsg("Dao Impl Save");
			    String insSql="INSERT INTO ADT_TL_TABLES VALUES(?,?,?,?,sysdate,sysdate)";
			    StringBuilder deleSql= new StringBuilder("DELETE FROM ADT_TL_TABLES WHERE ADT_TABLE_NAME IN(");
		        CommonMessage.debugMsg("auditsave.size==="+auditsave.size());
		        CommonMessage.debugMsg("The delete sql"+deleSql);
		        Map<Integer, List<Object[]>> datas = new HashMap<Integer,List<Object[]>>(); 
		        List<int[]> dataTypes = new ArrayList<int[]>();
		        int[] delDataType = new int[auditsave.size()]; 
				List<Object[]> valueList = new ArrayList<Object[]>();
				Object [] delData = new Object[auditsave.size()];
				int i = 0;
				List<String> sqls = new ArrayList<String>();
			    for(AuditmasterModel auditmasterModel : auditsave)	
				{
			    	deleSql.append("?,");
			    	delDataType[i] =Types.VARCHAR;
			    	delData[i] =auditmasterModel.getAtdtablename();
					Object[] value={auditmasterModel.getAtdtablename(),auditmasterModel.getAtdactive(),auditmasterModel.getAtdtriggerenable(),auditmasterModel.getAtdcreatedby()};
					valueList.add(value);
					i++;
				}		    
			    deleSql.deleteCharAt(deleSql.length()-1);
			    deleSql.append(")");
			    sqls.add(deleSql.toString());
			    sqls.add(insSql);
			    CommonMessage.debugMsg("The Sql Data Save"+sqls);
	 			int[] dataTypesinfo={Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				dataTypes.add(delDataType);
				dataTypes.add(dataTypesinfo);
				List<Object[]> delvalueList = new ArrayList<Object[]>(); 
				delvalueList.add(delData);
				datas.put(0,delvalueList);
				datas.put(1,valueList);
				CommonMessage.debugMsg("The datas"+datas);
				CommonMessage.debugMsg("Before Execute");
				dbActionTemplate.executeBatch(sqls,datas,dataTypes);
				CommonMessage.debugMsg("After Execute");
		        return auditsave;
	}
	
public List<String[]> auditreportdata(CommonFilter commonFilter)throws Exception{
	CommonMessage.debugMsg("DaoImpl");
	List<String> paramValues=new ArrayList<String>();
	String conditionalParms=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	CommonMessage.debugMsg("The CondParams"+conditionalParms);
	String commonParams=FilterCondSql.getGridCommonParams(commonFilter);
	CommonMessage.debugMsg("The Commonparam"+commonParams);
	paramValues.add(conditionalParms);
	paramValues.add(commonParams);
	List<String[]> dataList=dbActionTemplate.processFunctionCalls("ADM_PC_AUDITMASTER.ADM_PC_AUDITREPORTMODULE",paramValues);
	CommonMessage.debugMsg("The dataList"+dataList.size());
	if(commonFilter.getViewClick()=='Y'){	 	
     String totalcot=paramValues.get(0);
     CommonMessage.debugMsg("The Total Count"+totalcot);
     boolean isInteger = Pattern.matches("^\\d*$",totalcot);
   if(isInteger){
	  commonFilter.setTotalRecordCnt(Long.parseLong(totalcot));
   } 
  }
return dataList;
}
}
	/*private void functionLocationMakeActive(String menuName,List<String> paramValues) throws Exception {
		
		try
		{
			CommonMessage.debugMsg("Inside Function Loc Active....");
			Boolean [ ] isFunction = new Boolean [ 1 ];
			isFunction [0] = true;
			String keys = "'";
			for(int i=0;i<paramValues.size();i++)
			{
				keys = paramValues.get(i);
				
				CommonMessage.debugMsg("keys1....keys1...."+keys);
				CommonMessage.debugMsg("keys1....keys1...."+keys.length());
			
			//int len =  keys.length()-2;
			//keys = keys.substring(0,len);
			
			//String  sql = MasterIntSqls.getFuncLocActiveSql(keys);	
			//CommonMessage.debugMsg(sql);
			//String inactFun = dbActionTemplate.getSingleValue(sql);
			//CommonMessage.debugMsg("Function Loc Count...."+inactFun);
			//if(inactFun.length()>0){	
				String sqls = MasterIntSqls.getFunctionLocActive(menuName,isFunction,keys);
			  	CommonMessage.debugMsg("Function Loc Sql...."+sqls);
			  //	return sqls;
			  	//dbActionTemplate.executeStatement(sqls);
			//}
			}
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}*/
