package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.AbnStraficationDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class AbnStraficationDaoImpl implements AbnStraficationDao {

	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;

	public AbnStraficationDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void AbnStraficationDaoImplJwt(String JwtToken) 
	{
		try{
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public List<String[]> getAbnStrafication(CommonFilter commonFilter) throws Exception {
		try {
			List<String> paramValues = getFilterParamValues(
					commonFilter); /*
									 * new ArrayList<String>();
									 * 
									 * String condParms =
									 * FilterCondSql.getAbnRelatedConditionStr(
									 * commonFilter); String commonParams =
									 * FilterCondSql.getGridCommonParams(
									 * commonFilter); //
									 * "ISTOTALCNT="+commonFilter.getViewClick()
									 * +";FROMTOROW="+commonFilter.getFromRow()
									 * +" AND " + commonFilter.getToRow() +";";
									 * 
									 * //if
									 * (commonFilter.getAbnIsHSE().equals("Y"))
									 * //condParms+="REFDOCTYPE=SFT;";
									 * 
									 * paramValues.add(condParms);
									 * paramValues.add(commonParams);
									 */
			List<String[]> dataList = null;

//			dataList = dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_ABNSTRATIFICATION", paramValues);
			dataList = fnCallApi.callFunction("ABN_FN_ABNSTRATIFICATION_SB", paramValues,4,true);
			CommonMessage.debugMsg("The DataList Size" + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt) + 1);
				}
			}
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Workbook AbnStartificationReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
			throws Exception {
		ResultSet rs = null;
		try {

		/*	rs = getAbnormalityReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254, 0, 0)); // red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short) 14);
			condFormat.setFontBoldWeight((short) 20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue((char) 252 + ""); // Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs, rptFormat, 3, 0, 0);*/
			
			
			rs = getAbnormalityReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			
			return excelUtils.writeToExcel(rs,rptFormat, 3,1,0 );
		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet getAbnormalityReportResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
	//	List<String[]> dataList = null;
		CommonMessage.debugMsg("#AbnType value#::" + commonFilter.getStatus());
		if (commonFilter.getStatus() != null && commonFilter.getStatus().equals("abnimpact")) {
			 return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNSTRATIFICATIONIMPACT",paramValues);
		} else if (commonFilter.getStatus() != null && commonFilter.getStatus().equals("abntype")) {
			 return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNSTRATIFICATIONTYPE",paramValues);
		} else if (commonFilter.getStatus() != null && commonFilter.getStatus().equals("abncategory")) {
			 return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNSTRATIFICATIONCATY",paramValues);
		}else if (commonFilter.getStatus() != null && commonFilter.getStatus().equals("AbnStratificationRptType")) {
			 return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNSTRATIFICATIONNEWT",paramValues);
		}else if (commonFilter.getStatus() != null && commonFilter.getStatus().equals("AbnStratificationRptImpact")) {
			 return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNSTRATIFICATIONIMPA1",paramValues);
		}else {
		return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNSTRATIFICATION", paramValues);
		}
		
		
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();

		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																				// +";FROMTOROW="+commonFilter.getFromRow()
																				// +"
																				// AND
																				// "
																				// +
																				// commonFilter.getToRow()
																				// +";";

		paramValues.add(condParms);
		paramValues.add(commonParams);

		return paramValues;
	}

	public List<String[]> getAlStraficationDD(CommonFilter commonFilter) throws Exception {
		try {
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			if (commonFilter.getStatus() != null) {
			}
			paramValues.add(condParms);
			paramValues.add(commonParams);

			List<String[]> dataList = null;
			CommonMessage.debugMsg("#AbnType value#::" + commonFilter.getStatus());
			CommonMessage.debugMsg("#AbnType value#::" + commonFilter.getStatus()  +", Start :"+ CommonFunctions.dateTimeNow());
			if (commonFilter.getStatus().equals("abnimpact")) {
//				dataList = dbActionTemplate.processFunctionCalls("ABN_FN_ABNSTRATIFICATIONIMPACT",paramValues);
				dataList = fnCallApi.callFunction("ABN_FN_ABNSTRATIFICATIONIMPACT_SB",paramValues,5,true);
			} else if (commonFilter.getStatus().equals("abntype")) {
//				dataList = dbActionTemplate.processFunctionCalls("ABN_FN_ABNSTRATIFICATIONTYPE",paramValues);
				dataList = fnCallApi.callFunction("ABN_FN_ABNSTRATIFICATIONTYPE_SB",paramValues,5,true);
			} else {
//				dataList = dbActionTemplate.processFunctionCalls("ABN_FN_ABNSTRATIFICATIONCATY",paramValues);
				dataList = fnCallApi.callFunction("ABN_FN_ABNSTRATIFICATIONCATY_SB",paramValues,5,true);
			}
			CommonMessage.debugMsg("#AbnType value#::" + commonFilter.getStatus()  +", End :"+ CommonFunctions.dateTimeNow());
			
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt) + 1);
				}
			}
			return dataList;
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	public Workbook StartificationReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
			throws Exception {
		ResultSet rs = null;
		try {
/*
			rs = getStratificationReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254, 0, 0)); // red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short) 14);
			condFormat.setFontBoldWeight((short) 20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue((char) 252 + ""); // Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs, rptFormat, 3, 0, 0);*/
			rs = getAbnormalityReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			
			return excelUtils.writeToExcel(rs,rptFormat, 3,0,0 );

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet getStratificationReportResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);

		return dbActionTemplate.dbFunctionCall("TEST_PC_TESTSKILLINDX.SVC_FN_STRATIFICATION", paramValues);
	}

	/*********************************************/

	@Override
	public List<String[]> getAbnStraficationType(CommonFilter commonFilter) throws Exception {
		try {
			List<String> paramValues = getFilterParamValues(
					commonFilter); /*
									 * new ArrayList<String>();
									 * 
									 * String condParms =
									 * FilterCondSql.getAbnRelatedConditionStr(
									 * commonFilter); String commonParams =
									 * FilterCondSql.getGridCommonParams(
									 * commonFilter); //
									 * "ISTOTALCNT="+commonFilter.getViewClick()
									 * +";FROMTOROW="+commonFilter.getFromRow()
									 * +" AND " + commonFilter.getToRow() +";";
									 * 
									 * //if
									 * (commonFilter.getAbnIsHSE().equals("Y"))
									 * //condParms+="REFDOCTYPE=SFT;";
									 * 
									 * paramValues.add(condParms);
									 * paramValues.add(commonParams);
									 */
			List<String[]> dataList = null;

//			dataList = dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_ABNSTRATIFICATIONNEWT", paramValues);
			dataList = fnCallApi.callFunction("ABN_FN_ABNSTRATIFICATIONNEWT_SB", paramValues,4,true);
			CommonMessage.debugMsg("The DataList Size" + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt) + 1);
				}
			}
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<String[]> getAbnStraficationImpact(CommonFilter commonFilter) throws Exception {
		try {
			List<String> paramValues = getFilterParamValues(
					commonFilter); /*
									 * new ArrayList<String>();
									 * 
									 * String condParms =
									 * FilterCondSql.getAbnRelatedConditionStr(
									 * commonFilter); String commonParams =
									 * FilterCondSql.getGridCommonParams(
									 * commonFilter); //
									 * "ISTOTALCNT="+commonFilter.getViewClick()
									 * +";FROMTOROW="+commonFilter.getFromRow()
									 * +" AND " + commonFilter.getToRow() +";";
									 * 
									 * //if
									 * (commonFilter.getAbnIsHSE().equals("Y"))
									 * //condParms+="REFDOCTYPE=SFT;";
									 * 
									 * paramValues.add(condParms);
									 * paramValues.add(commonParams);
									 */
			List<String[]> dataList = null;

//			dataList = dbActionTemplate.processFunctionCallsWithColHeaders("ABN_FN_ABNSTRATIFICATIONIMPA1", paramValues);
			dataList = fnCallApi.callFunction("ABN_FN_ABNSTRATIFICATIONIMPA1_SB", paramValues,4,true);
			CommonMessage.debugMsg("The DataList Size" + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt) + 1);
				}
			}
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Workbook StartificationIdeVsComExportExcel(CommonFilter commonFilter, JSONObject colmodel, String format)
			throws Exception {
		ResultSet rs = null;
		try {
		/*	rs = getStratificationIdnVsComResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254, 0, 0)); // red font
			condFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormat.setFontHeightPoint((short) 8);
			condFormat.setFontBoldWeight((short) 20);
			condFormat.setDbChkColIndx(3);
			condFormat.setFromCol(2);
			condFormat.setToCol(2);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("RED"); // Tick
			condFormat.setIdentfier("RED");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs, format, 3, 0, 0);*/
			rs = getAbnormalityReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format, 4,1,0 );
		}

		finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}

	}

	private List<String> getFilterStratificationParamValues(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

		if (commonFilter.getStatus() != null) {
		}
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}

	private ResultSet getStratificationIdnVsComResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterStratificationParamValues(commonFilter);
		if (commonFilter.getStatus().equals("abnimpact"))
			return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNSTRATIFICATIONIMPACT", paramValues);
		else if (commonFilter.getStatus().equals("abntype"))
			return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNSTRATIFICATIONTYPE", paramValues);
		else
			return dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNSTRATIFICATIONCATY", paramValues);
	}
}