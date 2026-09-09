package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.AccessReviewrptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;

public class AccessReviewrptDaoImpl implements AccessReviewrptDao {
	private DBActionTemplate dbActionTemplate;
	public AccessReviewrptDaoImpl(DBActionTemplate dbActionTemplate) {
		
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate=dbActionTemplate;
	}
	@Override
	public List<String[]> getAccessReview(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			CommonMessage.debugMsg("before ");
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
						
			CommonMessage.debugMsg("ParamValues are:"+paramValues);	
		//	dbActionTemplate.processFunctionCalls("TEST4_PC_TEST4.ADM_FN_ACCREVRPT", paramValues);
			//List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("TEST4_PC_TEST4.ADM_FN_ACCREVRPT", paramValues);
			List<String[]> dataList = dbActionTemplate.processFunctionCalls("ADM_FN_ACCREVRPT", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("total count is"+totalCnt);
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("data is:::"+dataList);
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getAccessReviewrptExcel(JSONObject colmodel, String format, CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		 try{
				
				rs = getAccessReviewrptResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
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
				condFormat.setIdentfier("tick");
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
				return excelUtils.writeToExcel(rs,format, 2,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		}
	private ResultSet getAccessReviewrptResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return dbActionTemplate.NewdbFunctionCall2("ADM_FN_ACCREVRPT", paramValues);
		
	}
	
}
	

