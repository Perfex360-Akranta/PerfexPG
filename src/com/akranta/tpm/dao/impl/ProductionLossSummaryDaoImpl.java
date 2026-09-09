package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.ProductionLossSummaryDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class ProductionLossSummaryDaoImpl implements ProductionLossSummaryDao{

	
	private DBActionTemplate dbActionTemplate; 
	public ProductionLossSummaryDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String[]> getProductionLossSummaryRpt(CommonFilter commonFilter)throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  opl DAO Impl");
			int newTorow = Integer.parseInt(commonFilter.getToRow())+1;
			commonFilter.setToRow(Integer.toString(newTorow));
			String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			String  chktype = commonFilter.getWostatus();//for chk type machine or loss
			CommonMessage.debugMsg("chktype    "+chktype);
			if(UIUtils.isValidKeyId(chktype)){
				if(chktype.equals("Mach"))
				condParms += "ISMACHINEWISE=Y";
			}
		
				
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			CommonMessage.debugMsg("paramValues:"+paramValues);
			List<String[]> dataList;			
			//dataList = dbActionTemplate.processFunctionCallsWithColHeaders("PCS_PC_PRODLOG.PCS_FN_PCSLOSSSUMMARY", paramValues);
			dataList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_PCSLOSSSUMMARY3", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg(dataList);
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getProdLossSmryExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getProdLossSmryResultSet(commonFilter);
			
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			
			//condFormat.setFontHeightPoint((short)14);
			//condFormat.setFontBoldWeight((short)20);
		//	condFormat.setFromCol(0);
			//condFormat.setToCol(-1);
			int colSrt = 0;
			int rowSrt = 0;
			if("Mach".equals(commonFilter.getWostatus())){
				colSrt = 3;
			}
			else
				rowSrt = 1;
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,rptFormat, rowSrt,colSrt,0);
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}
	private ResultSet getProdLossSmryResultSet(CommonFilter commonFilter) throws Exception
	{
		
		List<String > paramValues = new ArrayList<String>();
		CommonMessage.debugMsg("Inside  opl DAO Impl");		
			
		String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		String  chktype = commonFilter.getWostatus();
		if(UIUtils.isValidKeyId(chktype)){
			if(chktype.equals("Mach"))
			condParms += "ISMACHINEWISE=Y";
		}
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		
		CommonMessage.debugMsg("paramValues:"+paramValues);
		
		return  dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_PCSLOSSSUMMARY3", paramValues);			 
		
		
	}
}
