package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.MasterMenuReptDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class MasterMenuReptDaoImpl implements MasterMenuReptDao {

	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;
	public void MasterMenuReptDaoImplJwt(String jwtToken) {
	    try {
	        fnCallApi = new FunctionCallApi(jwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public MasterMenuReptDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	/*
	 * @Override public List<String[]> getMasterMenuRept(CommonFilter commonFilter)
	 * throws Exception { try { List<String> paramValues = new ArrayList<String>();
	 * 
	 * String condParms = FilterCondSql.getAuditRelatedStr(commonFilter); String
	 * commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	 * 
	 * String maintype = commonFilter.getMaintMode();
	 * 
	 * CommonFunctions.debugMsg("maintype Dao Impl :: " + maintype);
	 * 
	 * 
	 * if(UIUtils.isValidKeyId(commonFilter.getMaintMode())) { condParms +=
	 * "MAINTYPE=" + commonFilter.getMaintMode() + ";"; }
	 * 
	 * 
	 * paramValues.add(condParms); paramValues.add(commonParams);
	 * 
	 * List<String[]> dataList = null;
	 * 
	 * // Main function call to GEN_FN_MASTERMENUREPT dataList =
	 * dbActionTemplate.processFunctionCalls("GEN_FN_MASTERACCESSREVIEW",
	 * paramValues);
	 * 
	 * CommonFunctions.debugMsg("Length...." + dataList.size());
	 * 
	 * if(commonFilter.getViewClick() == 'Y') { String totalCnt =
	 * paramValues.get(0); boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	 * if(isInteger) { commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt)); } }
	 * return dataList; } catch (Exception e) { throw new Exception(e.getMessage());
	 * } }
	 */
	@Override
	public List<String[]> getMasterMenuRept(CommonFilter commonFilter) throws Exception {
		try {
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			
			String maintype = commonFilter.getMaintMode();
			
			CommonFunctions.debugMsg("maintype Dao Impl :: " + maintype);
			
			/*
			 * // ---- NEW: guard - only call the function if a Location filter was actually
			 * provided ---- if (!UIUtils.isValidKeyId(commonFilter.getFlid()) &&
			 * !UIUtils.isValidKeyId(commonFilter.getLocation())) { CommonFunctions.
			 * debugMsg("No Location filter provided - skipping GEN_FN_MASTERACCESSREVIEW call"
			 * ); return new ArrayList<String[]>(); // empty grid, no DB hit } //
			 * -----------------------------------------------------------------------------
			 * --------
			 */			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList = null;
			
			// Main function call to GEN_FN_MASTERMENUREPT
		//	dataList = dbActionTemplate.processFunctionCalls("GEN_FN_MASTERACCESSREVIEW", paramValues);
			dataList =fnCallApi.callFunction("GEN_FN_MASTERACCESSREVIEW_SB", paramValues,3,true);
			
			
			CommonFunctions.debugMsg("Length...." + dataList.size());
			
			if(commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	@Override
	public Workbook getMasterMenuReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		ResultSet rs = null;
		try {
			rs = getMasterMenuReport(commonFilter);
			CommonFunctions.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
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
			return excelUtils.writeToExcel(rs, format, 2, 0, 0);
		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	// Private helper methods

	private ResultSet getMasterMenuReport(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

		paramValues.add(condParms);
		paramValues.add(commonParams);

		CommonFunctions.debugMsg("Inside the rs.........." + paramValues);
		ResultSet rs = dbActionTemplate.NewdbFunctionCall2("GEN_FN_MASTERACCESSREVIEW", paramValues);
		CommonFunctions.debugMsg("rs value.....");
		return rs;
	}
}
