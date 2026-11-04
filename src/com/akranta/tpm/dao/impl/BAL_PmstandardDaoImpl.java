package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.FilterValues;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PmstandardDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.BAL_GeneralMaintenanceServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
//import com.akranta.tpm.model.BAL_PmstandardModel;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_PmstandardDaoImpl implements BAL_PmstandardDao {
	
	
	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;

	
	public BAL_PmstandardDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void BAL_PmstandardDaoImplJwt(String JwtToken) {
	    try {
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public List<String []> getPmstandard(CommonFilter commonFilter) throws Exception
	{
		try
		{
			System.out.println("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			if(UIUtils.isValidKeyId(commonFilter.getSectionId()))
					condParms+="SECTIONID="+commonFilter.getSectionId()+";";
			if(UIUtils.isValidKeyId(commonFilter.getCellId()))
				condParms+="CELLID="+commonFilter.getCellId()+";";
			if(UIUtils.isValidKeyId(commonFilter.getFactoryId()))
				condParms+="FACTORYID="+commonFilter.getFactoryId()+";";
			if(UIUtils.isValidKeyId(commonFilter.getMachineId()))
				condParms+="MACHINEID="+commonFilter.getMachineId()+";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
						
			System.out.println("ParamValues:"+paramValues);	
			List<String[]> dataList =   dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_PMSTANDARD", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public Workbook PMStandardReportExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			   CommonFunctions.debugMsg("Row :");
			rs =   getPMStandardReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			
			/*List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);*/
			return excelUtils.writeToExcel(rs,rptFormat,2,-1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	} 
	public Workbook PMStandardReportExportToExcel(CommonFilter commonFilter,JSONObject tableModel, String format) throws Exception {
		ResultSet rs = null;
		   try{
			CommonFunctions.debugMsg("in side daoimpl ");
			rs =   getPMStandardResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tableModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			//condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format, 2,-1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	} 
	private ResultSet getPMStandardResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		CommonFunctions.debugMsg("iN SIDE THE PM STANDARDS DAO   " +commonFilter.getMachineId() +" "+ commonFilter.getTrarId());
		
		return dbActionTemplate.dbFunctionCall("PLM_PC_PLANNEDMAINT.BDM_FN_PMSTANDARD", paramValues);
	}
	
	private ResultSet getPMStandardReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		//return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_PMSTANDARDRPTTEMP", paramValues);
		
		return dbActionTemplate.NewdbFunctionCall2("PLM_FN_PMSTANDARDRPTTEMP", paramValues);

	}
	
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		if(UIUtils.isValidKeyId(commonFilter.getMachineId()))
			condParms+="MACHINEID="+commonFilter.getMachineId()+";";
		if(UIUtils.isValidKeyId(commonFilter.getTrarId()))
			condParms+="TRADEID="+commonFilter.getTrarId()+";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	@Override
	public List<String[]> getPmstandardRptTemp(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String > paramValues = getFilterParamValues(commonFilter);
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_PMSTANDARDRPTTEMP", paramValues);
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("plm_fn_pmstandardrpttemp", paramValues);
			List<String[]> dataList = fnCallApi.callFunction("PLM_FN_PMSTANDARDRPTTEMP_SB", paramValues, 3, true);


			CommonFunctions.debugMsg("IN SIDE THE dao IMPL 29-02");
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
			return dataList;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public List<String[]> getPmstandardAssemblyRptTemp(CommonFilter commonFilter)
			throws Exception {
		try
		{
			List<String > paramValues = getFilterParamValues(commonFilter);
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.BDM_FN_PMSTANDARD", paramValues); 
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("PLM_FN_PMSTANDARDRPTTEMP", paramValues); 


			//BDM_FN_PMSTANDARDASSEMBLYRPT
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
			return dataList;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	
	
	

}
