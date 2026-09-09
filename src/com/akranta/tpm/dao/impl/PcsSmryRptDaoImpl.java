

package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
//import org.omg.CORBA.Request;

import com.akranta.tpm.controller.DrillLevelConstants;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsSmryRptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;



public class PcsSmryRptDaoImpl implements PcsSmryRptDao {
	


	private DBActionTemplate dbActionTemplate; 
	
	public PcsSmryRptDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public List<String[]> getAllPcsSummary(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
		
			
			
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter) ;
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 CommonMessage.debugMsg("bEFORE   :"+commonFilter.getToRow());
			 if("monthly".equals(commonFilter.getWoapproval()))
					condParms += "ISFORMTD=Y";
			 if(commonFilter.getWostatus().equals("forTabDaily") || commonFilter.getWostatus().equals("forTabMonthly")){
				 /**added to show pagination  proper**/
				 int torow = Integer.parseInt(commonFilter.getToRow());
				 torow = torow+2;
				 commonFilter.setToRow(Integer.toString(torow));
			 }
			 CommonMessage.debugMsg("after  :"+commonFilter.getToRow());
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 List<String[]> rootRptList = null;
		
			  CommonMessage.debugMsg("paramvalues" + paramValues);
			  CommonMessage.debugMsg("STATUD "+commonFilter.getWostatus());
			  CommonMessage.debugMsg("getWodetailid "+commonFilter.getWodetailid());
			  CommonMessage.debugMsg(commonFilter.getWostatus()+" ~~~ "+commonFilter.getWoapproval());
			  if(UIUtils.isValidKeyId(commonFilter.getWostatus())){
				  
				  if(commonFilter.getWostatus().equals("forTabDaily") ){
					  
					  rootRptList = dbActionTemplate.processFunctionCalls ("PCS_PC_PRODLOG.PCS_FN_PRODUCTIONRPT", paramValues);
					  if( commonFilter.getViewClick() == 'Y'){
							String totalCnt = paramValues.get(0); 
							boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
							if(  isInteger ){
								commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
							}
						 }
				  }
				  else if(commonFilter.getWostatus().equals("forTab")){
					  rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("PCS_PC_PRODLOG.PCS_FN_PRODSUMMARY", paramValues);
					  if( commonFilter.getViewClick() == 'Y'){
							String totalCnt = paramValues.get(0); 
							boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
							if(  isInteger ){
								commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
							}
						 }
				  }
				  else if(commonFilter.getWostatus().equals("forTabMT")){
					  rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("PCS_PC_PRODLOG.PCS_FN_PRODSUMMARY", paramValues);
					  if( commonFilter.getViewClick() == 'Y'){
							String totalCnt = paramValues.get(0); 
							boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
							if(  isInteger ){
								commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
							}
						 }
				  }
				  else if(commonFilter.getWostatus().equals("forTabMonthly")){
					  
					  rootRptList = dbActionTemplate.processFunctionCalls ("PCS_PC_PRODLOG.PCS_FN_PRODUCTIONRPT", paramValues);
					  if( commonFilter.getViewClick() == 'Y'){
							String totalCnt = paramValues.get(0); 
							boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
							if(  isInteger ){
								commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
							}
						 }
				  }
			  }
			  if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				 }
			 CommonMessage.debugMsg(" ::: 99- " + rootRptList.size());
//		}
			 
			return rootRptList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getAllPcsSummaryExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat,String type) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter,type);
			CommonMessage.debugMsg(colmodel.toString());
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			if("remarks".equals(type))
				return excelUtils.writeToExcel(rs,rptFormat,0,0,0 );
			else if("forTab".equals(type))
				return excelUtils.writeToExcel(rs,rptFormat,0,0,0 );
			else if("forTabMT".equals(type))
				return excelUtils.writeToExcel(rs,rptFormat,0,0,0 );
			else
				return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter,String type) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		ResultSet rootRptList = null;
		if(UIUtils.isValidKeyId(commonFilter.getWostatus())){
			CommonMessage.debugMsg("Type....."+type);
			  if(type.equals("forTab"))
				  rootRptList = dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_PRODSUMMARY", paramValues);
			  else if(type.equals("forTabMT")){
				  rootRptList = dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_PRODSUMMARY", paramValues);
			  }
			  else if(type.equals("forTabDaily") || type.equals("forTabMonthly")){				  
				  rootRptList = dbActionTemplate.dbFunctionCall ("PCS_PC_PRODLOG.PCS_FN_PRODUCTIONRPT", paramValues);
			  }
			  else if(type.equals("remarks"))
			  {
				  rootRptList = dbActionTemplate.dbFunctionCall ("PCS_PC_PRODLOG.PCS_FN_PRODREMARKSRPT", paramValues);
			  }
		  }
		return rootRptList;
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		if("monthly".equals(commonFilter.getWoapproval()))
			condParms += "ISFORMTD=Y";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	@Override
	public List<String[]> getpcsRemarks(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter) ;
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 List<String[]> remarksRptList;
			 CommonMessage.debugMsg("paramvalues" + paramValues);
			 remarksRptList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_PRODREMARKSRPT", paramValues);
			 CommonMessage.debugMsg(" remarksRptList ::: 99- " + remarksRptList.size());
			 /*if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }*/
			return remarksRptList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
	}		
		
		
}

