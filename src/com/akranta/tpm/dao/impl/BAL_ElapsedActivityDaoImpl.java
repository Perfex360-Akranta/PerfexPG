package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_ElapsedActivityDao;

import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_ElapsedActivityDaoImpl implements BAL_ElapsedActivityDao {

	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;

	

	public BAL_ElapsedActivityDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void BAL_ElapsedActivityDaoImplJwt(String JwtToken) {
	    try {
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public List<String[]> getAllElapsedMaintActivity(CommonFilter commonFilter)
			throws Exception {
		try {
			System.out.println("Inside DAO Impl");

			List<String> paramValues = new ArrayList<String>();
			/*
			String sql = "  select '','Blower Motor','Monthly','','12-Nov-2013','24-Nov-2013','12','PRM ','120mints','Completed' FROM DUAL";
			sql += " UNION ALL select '','Expander Safety Door','Weekly','','12-Oct-2013','20-Oct-2013','8','CBM ','200mints','Completed' FROM DUAL";
			sql += " UNION ALL  select '','SubAssembly','Quarterly','','2-Dec-2013','22-Jan-2014','51',' CBM','420mints','Pending' FROM DUAL";
			sql += " UNION ALL  select '','GearBox','Weekly','','12-Dec-2013','24-Dec-2013','12','PRM ','300mints','Pending' FROM DUAL";
			sql += " UNION ALL  select '','Index Senser','Monthly','','2-Nov-2013','24-Jan-2014','52','CBM ','250mints','Pending' FROM DUAL";
			sql += " UNION ALL  select '','SubAssembly','Fort-Nightly','','12-Aug-2013','20-Aug-2013','8','PRM ','100mints','Completed' FROM DUAL";
			sql += " UNION ALL  select '','Injection Unit','Monthly','','12-Nov-2013','24-Nov-2013','12','PRM ','320mints','Completed' FROM DUAL";
			sql += " UNION ALL  select '','SubAssembly','Halfyearly','','25-Nov-2013','15-Mar-2013','95','CBM ','220mints','Pending' FROM DUAL";
			sql += " UNION ALL  select '','Cavity','Monthly','','12-Nov-2013','24-Nov-2013','12','CBM ','500mints','Completed' FROM DUAL";
			sql += " UNION ALL  select '','SubAssembly','Weekly','','5-March-2013','15-June-2013','100','PRM ','600mints','Completed' FROM DUAL";
			System.out.println("sql " + sql);*/
			// List<String[]> elapsedMaintAct =
			// dbActionTemplate.getDataList(sql);

			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql
					.getGridCommonParams(commonFilter);
			if(UIUtils.isValidKeyId(commonFilter.getSectionId()))
				condParms +=";SECTIONID="+commonFilter.getSectionId();
			if(UIUtils.isValidKeyId(commonFilter.getCellId()))
				condParms +=";CELLID="+commonFilter.getCellId();
			if(UIUtils.isValidKeyId(commonFilter.getMachineId()))
				condParms +=";MACHINEID="+commonFilter.getMachineId();
			if(UIUtils.isValidKeyId(commonFilter.getFactoryId()))
				condParms += ";FACTORYID="+commonFilter.getFactoryId();
			
			paramValues.add(condParms);
			paramValues.add(commonParams);

			System.out.println("paramValues" + paramValues);
			//List<String[]> elapsedMaintAct = dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_ELAPSEDMAINTCOUNT",paramValues);
			//List<String[]> elapsedMaintAct = dbActionTemplate.processFunctionCalls("PLM_FN_ELAPSEDMAINTCOUNT", paramValues);
			
			List<String[]> elapsedMaintAct = fnCallApi.callFunction("PLM_FN_ELAPSEDMAINTCOUNT_SB", paramValues, 3, true);

			

			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return elapsedMaintAct;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Workbook elapsedMaintReportExportExcel(CommonFilter commonFilter,
			JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		try {
			rs = getElapsedMaintReportResultSet(commonFilter);
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
			return excelUtils.writeToExcel(rs, rptFormat, 2,0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs
					.getStatement().getConnection());
		}
	}

	private ResultSet getElapsedMaintReportResultSet(CommonFilter commonFilter)
			throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		ResultSet rs = null;
		//rs = dbActionTemplate.dbFunctionCall("PLM_PC_PLANNEDMAINT.PLM_FN_ELAPSEDMAINTCOUNT", paramValues);
		rs = dbActionTemplate.NewdbFunctionCall2("PLM_FN_ELAPSEDMAINTCOUNT", paramValues);
		return rs;
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();

		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonFunctions.debugMsg("paramValues............" + paramValues);
		return paramValues;
	}
}
