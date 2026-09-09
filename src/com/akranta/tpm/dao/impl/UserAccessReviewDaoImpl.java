package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.UserAccessReviewDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class UserAccessReviewDaoImpl implements UserAccessReviewDao {

	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;

	public void UserAccessReviewDaoImplJwt(String jwtToken) {
		try {
			fnCallApi = new FunctionCallApi(jwtToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserAccessReviewDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	@Override
	public List<String[]> getUserAccessReviewRept(CommonFilter commonFilter) throws Exception {
		try {
			List<String> paramValues = new ArrayList<String>();

			// Same condition/common param builders used by the other Sec/Access reports.
			// getAuditRelatedStr already knows how to translate CommonFilter fields
			// (e.g. LOCATIONID) into the "FIELD=VALUE;" condition-param format the
			// DB function expects.
			String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			CommonFunctions.debugMsg("UserAccessReview condParms :: " + condParms);

			paramValues.add(condParms);
			paramValues.add(commonParams);

			List<String[]> dataList = null;

			// Main function call to GEN_FN_USERACCESSREVIEW_SB
			dataList = fnCallApi.callFunction("GEN_FN_USERACCESSREVIEW_SB", paramValues, 3, true);

			CommonFunctions.debugMsg("Length...." + dataList.size());

			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Workbook getUserAccessReviewExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		ResultSet rs = null;
		try {
			rs = getUserAccessReviewReport(commonFilter);
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
			return excelUtils.writeToExcel(rs, format, 2,1, 0);
		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	// Private helper methods

	private ResultSet getUserAccessReviewReport(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

		paramValues.add(condParms);
		paramValues.add(commonParams);

		CommonFunctions.debugMsg("Inside the rs.........." + paramValues);
		ResultSet rs = dbActionTemplate.NewdbFunctionCall2("GEN_FN_USERACCESSREVIEW", paramValues);
		CommonFunctions.debugMsg("rs value.....");
		return rs;
	}
}
