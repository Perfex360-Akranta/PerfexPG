package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.AbnormalityReportDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;


public class AbnormalityReportDaoImpl implements AbnormalityReportDao {
	
	
	private DBActionTemplate dbActionTemplate;
	
	public AbnormalityReportDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String []> getAbnormalityGeneral(CommonFilter commonFilter) throws Exception
	{
		CommonMessage.debugMsg("inside get abn");
		
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
//			if (commonFilter.getAbnIsHSE().equals("Y"))
//				condParms+="REFDOCTYPE=SFT;";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			CommonMessage.debugMsg("commonFilter.getAbnIsHSE()...."+commonFilter.getAbnIsHSE());

			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ABN_PC_ABNORMALITY.ABN_FN_ABNREPORTGENERAL", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
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
	public Workbook abnormalityGeneralReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getAbnormalityReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
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
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	} 
	
	
	private ResultSet getAbnormalityReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);

		return dbActionTemplate.dbFunctionCall("ABN_PC_ABNORMALITY.ABN_FN_ABNREPORTGENERAL", paramValues);
	}
	
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
//		if (commonFilter.getAbnIsHSE().equals("Y"))
//			condParms+="REFDOCTYPE=SFT;";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	@Override
	public List<String[]> getUnsafeDrillDn(CommonFilter commonFilter,
			String parentId) throws Exception {

		CommonMessage.debugMsg("inside get abn");
		
		try
		{
			List<String[]> dataList =  null;
			String sql="";
			/*List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;

			dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST.TEST_FN_ACTIONPLANDRILLDWN", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}*/
			sql+="SELECT '50 TPH COAL PLANT' COMPANY, 1 UNSAFEPL, 3 UNSAFEAC, 3 UNSAFEPL,8 UNSAFEAC, 3 UNSAFEPL, 3 UNSAFEAC FROM DUAL UNION ";
			sql+="SELECT 'FH 123 - SHEETER 1,' COMPANY, 3 UNSAFEPL, 2 UNSAFEAC, 2 UNSAFEPL,5 UNSAFEAC, 5 UNSAFEPL, 3 UNSAFEAC FROM DUAL UNION ";
			sql+="SELECT 'DISC CHIPPER 1' COMPANY, 4 UNSAFEPL, 7 UNSAFEAC, 5 UNSAFEPL,7 UNSAFEAC, 3 UNSAFEPL, 2 UNSAFEAC FROM DUAL UNION ";
			sql+="SELECT 'FIBRELINE 2' COMPANY, 1 UNSAFEPL, 2 UNSAFEAC, 3 UNSAFEPL,8 UNSAFEAC, 9 UNSAFEPL, 2 UNSAFEAC FROM DUAL UNION ";
			sql+="SELECT 'CLO2 PLANT' COMPANY, 3 UNSAFEPL, 1 UNSAFEAC, 8 UNSAFEPL,3 UNSAFEAC, 7 UNSAFEPL, 5 UNSAFEAC FROM DUAL UNION ";
			sql+="SELECT 'FIBRELINE 1' COMPANY, 2 UNSAFEPL, 3 UNSAFEAC, 0 UNSAFEPL,9 UNSAFEAC, 1 UNSAFEPL, 8 UNSAFEAC FROM DUAL UNION ";
			sql+="SELECT 'CAUSTICIZING' COMPANY, 1 UNSAFEPL, 5 UNSAFEAC, 2 UNSAFEPL,8 UNSAFEAC, 3 UNSAFEPL, 0 UNSAFEAC FROM DUAL UNION  ";
			sql+="SELECT 'Total' COMPANY, 14 UNSAFEPL, 23 UNSAFEAC, 23 UNSAFEPL,48 UNSAFEAC, 31 UNSAFEPL, 23 UNSAFEAC FROM DUAL ";
			CommonMessage.debugMsg("sql   "+sql);
			dataList =  dbActionTemplate.getDataList(sql);
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public List<String[]> getUnsafeMonth(CommonFilter commonFilter) throws Exception {
CommonMessage.debugMsg("inside get abn");
		
		try
		{
			List<String[]> dataList =  null;
			String sql="";
			sql+="SELECT 'Unsafe Act' AS UNSAFEACT, '1' AS ABN, '3' AS NM, '5' AS INC, '6' AS ABN, '1' AS NM, '7' AS INC, '6' AS ABN, '3' AS NM, '2' AS INC";
			sql+=", '5' AS ABN, '9' AS NM, '2' AS INC, '5' AS ABN, '8' AS NM, '2' AS INC FROM DUAL UNION ";
			sql+="SELECT 'Unsafe Condition' AS UNSAFECOND, '3' AS ABN, '1' AS NM, '2' AS INC, '9' AS ABN, '4' AS NM, '1' AS INC, '7' AS ABN, '6' AS NM, '3' AS INC";
			sql+=", '3' AS ABN, '5' AS NM, '7' AS INC, '1' AS ABN, '3' AS NM, '4' AS INC FROM DUAL  ";
			CommonMessage.debugMsg("sql   "+sql);
			dataList =  dbActionTemplate.getDataList(sql);
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
}


