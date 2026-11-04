package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_MTTRMTBFDao;

import com.akranta.tpm.dao.sql.BreakDownSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;



public class  BAL_MTTRMTBFDaoImpl implements  BAL_MTTRMTBFDao {
	


	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	
	public  BAL_MTTRMTBFDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void BAL_MTTRMTBFDaoImplJwt(String JwtToken) 
	{
		try{
			
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public List<String[]> getAllMTTRMTBF(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
		
			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 List<String[]> mTTRMTBFList ;
			System.out.println("relCondStr="+commonFilter.getCbooptions());
			/*if(FilterCondSql.getComboSelectionId(commonFilter.getCbooptions()).equals("BF"))
			{
				mTTRMTBFList = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_BDMTBF", paramValues);
			}else{*/
			
			 	//mTTRMTBFList = dbActionTemplate.processFunctionCalls("BDM_FN_BDMTTRMTBF", paramValues);
			
			mTTRMTBFList = fnCallApi.callFunction("BDM_FN_BDMTTRMTBF_SB", paramValues, 2, false);
		//	}
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
		
			return  mTTRMTBFList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getMTTRMTBFExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat,String type) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter,type);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter,String type) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		if(type.equals("MTBF"))
			return dbActionTemplate.NewdbFunctionCall2("BDM_FN_BDMTBF", paramValues);
		else
			return dbActionTemplate.NewdbFunctionCall2("BDM_FN_BDMTTRMTBF", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
	public List<String[]> getAllMTBF(CommonFilter commonFilter)	throws Exception {
		
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
		
			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 List<String[]> mTTRMTBFList ;
			
			System.out.println("relCondStr="+commonFilter.getCbooptions());
			
				//mTTRMTBFList = dbActionTemplate.processFunctionCalls("BDM_FN_BDMTBF", paramValues);
			
			mTTRMTBFList = fnCallApi.callFunction("BDM_FN_BDMTBF_SB", paramValues, 2, false);
				
				
		
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
		
			return  mTTRMTBFList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
		
	}
	@Override
	public void getRefresh(CommonFilter commonFilter) throws Exception {
		
		 List<String > refreshParamValues = new ArrayList<String>();
		
		 String fromDate = commonFilter.getFromDate();
		 String toDate= commonFilter.getToDate();
		 String machine = FilterCondSql.getComboSelectionId(commonFilter.getMachine());
		 String cell = FilterCondSql.getComboSelectionId(commonFilter.getCell());
		 if("Y".equals(commonFilter.getMonwise()))
		 {
			 fromDate = "01-"+commonFilter.getFromMonth();
		     toDate = "01-"+commonFilter.getToMonth();
		 }
		 if(!UIUtils.isValidKeyId(machine))
			 machine = "{}";
		 if(!UIUtils.isValidKeyId(cell))
			 cell = "{}";
			 
		 refreshParamValues.add(fromDate);
		 refreshParamValues.add(toDate);
		 refreshParamValues.add(machine);
		 refreshParamValues.add(cell);
		 refreshParamValues.add(FilterCondSql.getComboSelectionId(commonFilter.getSection()));
		 refreshParamValues.add(FilterCondSql.getComboSelectionId(commonFilter.getFactory()));
		 CommonFunctions.debugMsg("refreshParamValues..."+refreshParamValues);
		//if("Y".equals(commonFilter.getRefresh()))
			dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_INSERTCALTIMEFROMPCS1", refreshParamValues);
		
	}
	@Override
	public List<String[]> getchart1(String filterString, String month) throws Exception {
		// TODO Auto-generated method stub
		
		String sql=" select MCHM_MACHINENAME,BDMS_KEYID,WWMS_KEYID,''"; 
		sql+=" from gen_tl_Machinemst,bdm_tl_mst,BDM_TL_WHYWHYMST";
		sql+=" where BDMS_SECTIONID = WWMS_SECTIONID and MCHM_FLID = WWMS_FLID";
		sql+=" and BDMS_SECTIONID = '"+filterString+"' and to_CHAR(BDMS_ENTRYDATE,'MON-YYYY') ='"+month+"'"; 
		CommonFunctions.debugMsg("Sql:::::   "+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql);
		return dataList;
	}	



}