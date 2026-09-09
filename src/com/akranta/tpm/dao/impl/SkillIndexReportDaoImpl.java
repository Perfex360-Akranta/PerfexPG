package com.akranta.tpm.dao.impl;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.SkillIndexReportDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;

public class SkillIndexReportDaoImpl implements SkillIndexReportDao {

	private DBActionTemplate dbActionTemplate;

	FunctionCallApi fnCallApi;

	public SkillIndexReportDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public void SkillIndexReportDaoImplJwt(String JwtToken) {
		try {

			fnCallApi = new FunctionCallApi(JwtToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String callProcedureAverageSkillIndex(CommonFilter commonFilter) {

		try {
			if ((UIUtils.isValidKeyId(commonFilter.getSect())) && (!UIUtils.isValidKeyId(commonFilter.getCellId()))) {

				List<String> paramValuesDmt = new ArrayList<>();

				paramValuesDmt.add(commonFilter.getdFromDate() + "-H1"); // P_REVIEW_HALF
				paramValuesDmt.add(commonFilter.getFlid()); // P_DMT_FLID

				dbActionTemplate.processPLSQLProceduresNew("SP_SYNC_DMT_EMPLOYEE_COUNTS", paramValuesDmt, null);

				List<String> paramValuesDmt2 = new ArrayList<>();
				paramValuesDmt2.add(commonFilter.getdFromDate() + "-H2"); // P_REVIEW_HALF
				paramValuesDmt2.add(commonFilter.getFlid()); // P_DMT_FLID

				dbActionTemplate.processPLSQLProceduresNew("SP_SYNC_DMT_EMPLOYEE_COUNTS", paramValuesDmt2, null);
			} else if ((UIUtils.isValidKeyId(commonFilter.getSect()))
					&& (UIUtils.isValidKeyId(commonFilter.getCellId()))) {
				List<String> paramValuesJH = new ArrayList<>();

				paramValuesJH.add(commonFilter.getdFromDate() + "-H1"); // P_REVIEW_HALF
				paramValuesJH.add(commonFilter.getFlid()); // P_DMT_FLID

				dbActionTemplate.processPLSQLProceduresNew("SP_SYNC_JH_FLID_EMPLOYEE_COUNTS", paramValuesJH, null);

				List<String> paramValuesJH2 = new ArrayList<>();
				paramValuesJH2.add(commonFilter.getdFromDate() + "-H2"); // P_REVIEW_HALF
				paramValuesJH2.add(commonFilter.getFlid()); // P_DMT_FLID

				dbActionTemplate.processPLSQLProceduresNew("SP_SYNC_JH_FLID_EMPLOYEE_COUNTS", paramValuesJH2, null);

			}
			return "PROCEDURE-SUCESSS";
		} catch (Exception e) {
			e.printStackTrace();
			return "PROCEDURE-FAILED";
		}
	}

	public List<String[]> getSkillIndex(CommonFilter commonFilter) throws Exception {
		try {
			boolean isInteger = false;
			List<String> paramValues = getFilterParamValues(commonFilter);
			// List<String[]> dataList =
			// dbActionTemplate.processFunctionCalls("ENT_FN_SKILLINDEXREPORT",
			// paramValues);

			List<String[]> dataList = fnCallApi.callFunction("ENT_FN_SKILLINDEXREPORT_SB", paramValues, 6, true);
			CommonMessage.debugMsg("dataList53453      " + dataList.size());
			// ENT_PC_EDUANDTRAINING.ENT_FN_SKILLINDEXREPORT
			// ENT_FN_SKILLINDEXCHART
			CommonMessage.debugMsg("commonFilter.getViewClick()==" + commonFilter.getViewClick());

			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}

	}

	private List<String> getFilterParamValues(CommonFilter commonFilter) {

		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																				// +";FROMTOROW="+commonFilter.getFromRow()
																				// +" AND " + commonFilter.getToRow()
																				// +";";

		List<String> paramValues = new ArrayList<String>();

		condParms += "FLID=" + commonFilter.getFlid() + ";UNIQPOSID=" + commonFilter.getUniquePos() + ";";

		paramValues.add(condParms);
		paramValues.add(commonParams);

		return paramValues;
	}

	public List<String[]> getTopicTask(CommonFilter commonFilter) throws Exception {
		String sql = getTopicTask();
		CommonMessage.debugMsg("sql..." + sql);
		return dbActionTemplate.getDataList(sql);
	}

	private String getTopicTask() {
		String sql = " SELECT TOPI_NAME FROM ENT_TL_TOPICMST ";
		return sql;
	}

	@Override
	public List<String[]> getTask(CommonFilter commonFilter) throws Exception {
		String sql = " SELECT TMKD_TASK,'','' FROM ENT_TL_TASKDTL ";
		return dbActionTemplate.getDataList(sql);
	}

	// Query change - Swetha - Skill Assessment - 13 November
	@Override
	public List<String[]> getSkillIndexRadarChart(CommonFilter commonFilter) throws Exception {
		StringBuffer sql = new StringBuffer();
		CommonMessage.debugMsg("commonFilter.getFromDate()" + commonFilter.getFromDate());

		/*
		 * sql.append(
		 * " SELECT SIAD_EMPM_KEYID,  SPOK_CODE,ROUND(SUM(DISTINCT SIAD_SCORE)/COUNT(distinct SIAD_CRITERIAID),2)  "
		 * ); sql.append(
		 * " FROM ENT_TL_SKILLINDEXASSESSDTL,ENT_TL_SKILL_REVIEWPOINTMST,ENT_TL_SKILL_REVIEWPOINTDET,ENT_TL_SPOKEMST,ENT_TL_SKILLINDEXASSESSMST "
		 * ); sql.append(
		 * " WHERE SIAD_CRITERIAID = SIRD_SPOK_KEYID(+) AND SIRD_SPOK_KEYID(+) = SPOK_KEYID AND SIAM_KEYID = SIAD_SIAM_KEYID "
		 * ); sql.append( " AND TO_CHAR(SIAM_REVIEWDATE,'DD-MON-YYYY') = '" +
		 * commonFilter.getFromDate() + "' "); //sql.append(
		 * " AND  SIAD_EMPM_KEYID ='EMP01196' "); sql.append(
		 * " and siam_keyid=siad_siam_keyid AND SIAM_FLID = '"+commonFilter.getFlid()
		 * +"' AND SIAM_UNIQUEPOSID = '"+commonFilter.getUniquePos()+"' "); sql.append(
		 * " AND SIAD_EMPM_KEYID IN ("+commonFilter.getEmmLinkKeyId()+") "); sql.append(
		 * " GROUP BY SIAD_EMPM_KEYID,  SPOK_CODE  "); sql.append(
		 * " ORDER BY  SIAD_EMPM_KEYID, SPOK_CODE ");
		 */

//		sql.append( " SELECT SIAD_EMPM_KEYID,  SPOK_CODE, ROUND(SUM(SIAD_SCORE)/COUNT(distinct SIAD_CRITERIAID),2) ");
//		sql.append( " FROM ENT_TL_SKILLINDEXASSESSDTL,ENT_TL_SKILLINDEXASSESSMST , ENT_TL_SKILL_REVIEWPOINTDET, ENT_TL_SPOKEMST ");
//		sql.append( " WHERE SIAM_KEYID = SIAD_SIAM_KEYID  AND SIRD_KEYID = SIAD_REVIEWID AND SIRD_SPOK_KEYID(+) = SPOK_KEYID ");
//		sql.append( " AND TO_CHAR(SIAM_REVIEWDATE,'DD-MON-YYYY') = '" + commonFilter.getFromDate() + "' ");
//		sql.append( " and siam_keyid=siad_siam_keyid AND SIAM_FLID = '"+commonFilter.getFlid()+"' AND SIAM_UNIQUEPOSID = '"+commonFilter.getUniquePos()+"' ");
//		sql.append( " AND SIAD_EMPM_KEYID IN ("+commonFilter.getEmmLinkKeyId()+") ");
//		sql.append( " GROUP BY SIAD_EMPM_KEYID, SPOK_CODE   ");
//		sql.append( " ORDER BY  SIAD_EMPM_KEYID, SPOK_CODE ");

		sql.append(" SELECT d.siad_empm_keyid, s.spok_code, ");
		sql.append("        ROUND(SUM(d.siad_score)::numeric / COUNT(DISTINCT d.siad_criteriaid), 2) AS avg_score ");
		sql.append(" FROM ent_tl_skillindexassessdtl d ");
		sql.append(" JOIN ent_tl_skillindexassessmst m ON m.siam_keyid = d.siad_siam_keyid ");
		sql.append(" JOIN ent_tl_skill_reviewpointdet r ON r.sird_keyid = d.siad_reviewid ");
		sql.append(" LEFT JOIN ent_tl_spokemst s ON s.spok_keyid = r.sird_spok_keyid ");
		sql.append(" WHERE m.siam_reviewdate::date = TO_DATE('" + commonFilter.getFromDate() + "', 'DD-MON-YYYY') ");
		sql.append("   AND m.siam_flid = '" + commonFilter.getFlid() + "' ");
		sql.append("   AND m.siam_uniqueposid = '" + commonFilter.getUniquePos() + "' ");
		sql.append("   AND d.siad_empm_keyid IN (" + commonFilter.getEmmLinkKeyId() + ") ");
		sql.append(" GROUP BY d.siad_empm_keyid, s.spok_code ");
		sql.append(" ORDER BY d.siad_empm_keyid, s.spok_code ");

		CommonMessage.debugMsg("sql==" + sql.toString());
		return dbActionTemplate.getDataList(sql.toString());
	}

	public Workbook skillIndexReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
			throws Exception {
		ResultSet rs = null;
		try {

			rs = getSkillIndexReportResultSet(commonFilter);
			CommonMessage.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254, 0, 0)); // red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short) 14);
			condFormat.setFontBoldWeight((short) 20);
			/*
			 * condFormat.setFromCol(13); condFormat.setToCol(-1);
			 */
			/*
			 * condFormat.setOperator(ComparisonOperator.EQUAL); condFormat.setCondValue(
			 * (char)252+""); //Tick condFormat.setIdentfier("tick");
			 * condFormats.add(condFormat);
			 */
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs, rptFormat, 6, 0, 2);//change here 0->2

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet getSkillIndexReportResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg("Inside the rs.........." + paramValues);
		ResultSet rs = null;
		CommonMessage.debugMsg("commonFilter.getAbnIsHSE()...." + commonFilter.getAbnIsHSE());

		rs = dbActionTemplate.NewdbFunctionCall2("ENT_FN_SKILLINDEXREPORT_SB", paramValues);

		CommonMessage.debugMsg("rs value.....");
		return rs;
	}

	@Override
	public List<String[]> getskillIndexChart(CommonFilter commonFilter) throws Exception {
		try {
			boolean isInteger = false;
			String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																					// +";FROMTOROW="+commonFilter.getFromRow()
																					// +" AND " +
																					// commonFilter.getToRow() +";";

			List<String> paramValues = new ArrayList<String>();

			condParms += "FLID=" + commonFilter.getFlid() + ";UNIQPOSID=" + commonFilter.getUniquePos() + ";"
					+ ";EMPIDS=" + commonFilter.getEmmLinkKeyId() + ";";

			paramValues.add(condParms);
			paramValues.add(commonParams);
			// List<String[]> dataList =
			// dbActionTemplate.processFunctionCalls("ENT_FN_SKILLINDEXCHART", paramValues);

			List<String[]> dataList = fnCallApi.callFunction("ENT_FN_SKILLINDEXCHART_SB", paramValues, 5, true);

//			CommonMessage.debugMsg("Printing the graph");
//			for(String[] arr:dataList) 
//			{
//				CommonMessage.debugMsg(Arrays.toString(arr));
//			}
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}

	}

	// graph

	/*
	 * @Override public List<String[]> getavgSkillScoreGraph(CommonFilter
	 * commonFilter) throws Exception { try {
	 * 
	 * List<String > paramValues = new ArrayList<String>();
	 * CommonMessage.debugMsg("Inside  visual Work place Month DAO Impl"); String
	 * condParms = FilterCondSql.getSafetyRelatedStr(commonFilter); String
	 * commonParams = FilterCondSql.getGridCommonParams(commonFilter); //
	 * "ISTOTALCNT="+commonFilter.getViewClick()
	 * +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow()
	 * +";";
	 * 
	 * String type=commonFilter.getAbnViewType();
	 * 
	 * CommonMessage.debugMsg(" Checking for type "+type);
	 * 
	 * String dril=commonFilter.getDrillLevel(); String total="A";
	 * total=commonFilter.getTotal(); if(total==null){ total="A"; }
	 * CommonMessage.debugMsg("In side DAO Impl"+dril);
	 * 
	 * if(dril.equals("CMP")||dril.equals("COMP")){
	 * CommonMessage.debugMsg("In side DAO Impl CMP"+dril);
	 * 
	 * condParms+="DRILLLEVEL=COMP;"; } else if(dril.equals("SECT")){
	 * CommonMessage.debugMsg("In side DAO Impl SECT"+dril);
	 * 
	 * condParms+="DRILLLEVEL=SECT;"; }
	 * 
	 * else if(dril.equals("CELL")){ condParms+="DRILLLEVEL=CELL;"; }
	 * 
	 * if(UIUtils.isValidKeyId(type)) condParms +="TYPE="+type+";";
	 * 
	 * paramValues.add(condParms); paramValues.add(commonParams);
	 * CommonMessage.debugMsg(total+"Inside Visual Month DAO Impl " + paramValues);
	 * List<String[]> impVsCompList = null;
	 * 
	 * if(dril.equals("EMP")){ CommonMessage.debugMsg("Inside  Employee " + dril);
	 * 
	 * //impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "ENT_FN_AVGSKILLINDEXEMP", paramValues);
	 * 
	 * impVsCompList = fnCallApi.callFunction("ENT_FN_AVGSKILLINDEXEMP_SB",
	 * paramValues,3,false);
	 * 
	 * 
	 * 
	 * for (String[] arr : impVsCompList) {
	 * CommonMessage.debugMsg(Arrays.toString(arr)); }
	 * 
	 * CommonMessage.debugMsg(); if(total.equals("Z")){ impVsCompList =
	 * dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXZERO", paramValues);
	 * CommonMessage.debugMsg("Inside Visual Month Dao in side IF " + paramValues);
	 * 
	 * } else impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXEMP", paramValues);
	 * 
	 * 
	 * } else if(dril.equals("CELL")){
	 * 
	 * //impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "ENT_FN_AVGSKILINDXSCORCELL", paramValues); impVsCompList =
	 * fnCallApi.callFunction("ENT_FN_AVGSKILINDXSCORCELL_SB", paramValues,3,false);
	 * for (String[] arr : impVsCompList) {
	 * CommonMessage.debugMsg(Arrays.toString(arr)); }
	 * 
	 * } else{ if(total.equals("Z")){ impVsCompList =
	 * dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXZERO", paramValues);
	 * CommonMessage.debugMsg("Inside Visual Month Dao in side IF  " + paramValues);
	 * 
	 * } else{ //impVsCompList =
	 * dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXEMP", paramValues);
	 * CommonMessage.debugMsg(paramValues+"out side  Employee " + dril);
	 * 
	 * 
	 * 
	 * //impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "ENT_FN_AVGSKILLINDEXSCORE", paramValues); impVsCompList =
	 * fnCallApi.callFunction("ENT_FN_AVGSKILLINDEXSCORE_SB", paramValues,3,false);
	 * CommonMessage.debugMsg("printig the DMT S"); for (String[] arr :
	 * impVsCompList) { CommonMessage.debugMsg(Arrays.toString(arr)); } //
	 * impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXZERO", paramValues);
	 * 
	 * CommonMessage.debugMsg("Inside Visual Month Dao in side Impl " +
	 * paramValues);
	 * 
	 * //} } else{
	 * CommonMessage.debugMsg("Inside Visual Month Dao in side Else Impl " +
	 * paramValues);
	 * 
	 * impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXSCORE", paramValues);
	 * 
	 * } if( commonFilter.getViewClick() == 'Y'){ String totalCnt =
	 * paramValues.get(0); boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	 * if( isInteger ){ commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt)); }
	 * }
	 * 
	 * return impVsCompList;
	 * 
	 * }
	 * 
	 * catch (Exception e) { e.printStackTrace(); throw new
	 * Exception(e.getMessage()); } }
	 */
	/*
	 * @Override public List<String[]> getavgSkillScoreGraph(CommonFilter
	 * commonFilter) throws Exception { try {
	 * 
	 * List<String > paramValues = new ArrayList<String>();
	 * CommonMessage.debugMsg("Inside  visual Work place Month DAO Impl"); String
	 * condParms = FilterCondSql.getSafetyRelatedStr(commonFilter); String
	 * commonParams = FilterCondSql.getGridCommonParams(commonFilter); //
	 * "ISTOTALCNT="+commonFilter.getViewClick()
	 * +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow()
	 * +";";
	 * 
	 * String type=commonFilter.getAbnViewType();
	 * 
	 * CommonMessage.debugMsg(" Checking for type "+type);
	 * 
	 * String dril=commonFilter.getDrillLevel(); String total="A";
	 * total=commonFilter.getTotal(); if(total==null){ total="A"; }
	 * CommonMessage.debugMsg("In side DAO Impl"+dril);
	 * 
	 * if(dril.equals("CMP")||dril.equals("COMP")){
	 * CommonMessage.debugMsg("In side DAO Impl CMP"+dril);
	 * 
	 * condParms+="DRILLLEVEL=COMP;"; } else if(dril.equals("SECT")){
	 * CommonMessage.debugMsg("In side DAO Impl SECT"+dril);
	 * 
	 * condParms+="DRILLLEVEL=SECT;"; }
	 * 
	 * else if(dril.equals("CELL")){ condParms+="DRILLLEVEL=CELL;";//Swetha
	 * if(UIUtils.isValidKeyId(commonFilter.getYear()))//Swetha condParms
	 * +="HALFYEAR="+commonFilter.getYear()+";"; //Swetha
	 * 
	 * 
	 * }
	 * 
	 * if(UIUtils.isValidKeyId(type)) condParms +="TYPE="+type+";";
	 * 
	 * paramValues.add(condParms); paramValues.add(commonParams);
	 * CommonMessage.debugMsg(total+"Inside Visual Month DAO Impl " + paramValues);
	 * List<String[]> impVsCompList = null;
	 * 
	 * if(dril.equals("EMP")){ CommonMessage.debugMsg("Inside  Employee " + dril);
	 * 
	 * //impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "ENT_FN_AVGSKILLINDEXEMP", paramValues); //impVsCompList =
	 * fnCallApi.callFunction("ENT_FN_AVGSKILLINDEXEMP_SB",
	 * paramValues,3,false);//cHANGES HERE
	 * 
	 * impVsCompList = fnCallApi.callFunction("ENT_FN_AVGSKILLINDEXEMP_SB",
	 * paramValues,3,false);//cHANGES HERE
	 * 
	 * 
	 * 
	 * for (String[] arr : impVsCompList) {
	 * CommonMessage.debugMsg(Arrays.toString(arr)); }
	 * 
	 * CommonMessage.debugMsg(); if(total.equals("Z")){ impVsCompList =
	 * dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXZERO", paramValues);
	 * CommonMessage.debugMsg("Inside Visual Month Dao in side IF " + paramValues);
	 * 
	 * } else impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXEMP", paramValues);
	 * 
	 * 
	 * } else if(dril.equals("CELL")){
	 * 
	 * //impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "ENT_FN_AVGSKILINDXSCORCELL", paramValues); //impVsCompList =
	 * fnCallApi.callFunction("ENT_FN_AVGSKILINDXSCORCELL_SB", paramValues,3,false);
	 * 
	 * impVsCompList = fnCallApi.callFunction("ENT_FN_AVGSKILINDXSCORCELL_JHAVG",
	 * paramValues,3,false);//Swetha //ent_fn_avgskilindxscorcell_new_sb for
	 * (String[] arr : impVsCompList) {
	 * CommonMessage.debugMsg(Arrays.toString(arr)); }
	 * 
	 * } else{ if(total.equals("Z")){ impVsCompList =
	 * dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXZERO", paramValues);
	 * CommonMessage.debugMsg("Inside Visual Month Dao in side IF  " + paramValues);
	 * 
	 * } else{ //impVsCompList =
	 * dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXEMP", paramValues);
	 * CommonMessage.debugMsg(paramValues+"out side  Employee " + dril);
	 * 
	 * 
	 * 
	 * //impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "ENT_FN_AVGSKILLINDEXSCORE", paramValues); impVsCompList =
	 * fnCallApi.callFunction("ENT_FN_AVGSKILLINDEXSCORE_DMTAVG",
	 * paramValues,3,false);//Swetha CommonMessage.debugMsg("printig the DMT S");
	 * for (String[] arr : impVsCompList) {
	 * CommonMessage.debugMsg(Arrays.toString(arr)); } // impVsCompList =
	 * dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXZERO", paramValues);
	 * 
	 * CommonMessage.debugMsg("Inside Visual Month Dao in side Impl " +
	 * paramValues);
	 * 
	 * //} } else{
	 * CommonMessage.debugMsg("Inside Visual Month Dao in side Else Impl " +
	 * paramValues);
	 * 
	 * impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXSCORE", paramValues);
	 * 
	 * } if( commonFilter.getViewClick() == 'Y'){ String totalCnt =
	 * paramValues.get(0); boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	 * if( isInteger ){ commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt)); }
	 * }
	 * 
	 * return impVsCompList;
	 * 
	 * }
	 * 
	 * catch (Exception e) { e.printStackTrace(); throw new
	 * Exception(e.getMessage()); } }
	 */

	/*
	 * @Override public List<String[]> getavgSkillScoreGraph(CommonFilter
	 * commonFilter) throws Exception { try {
	 * 
	 * List<String > paramValues = new ArrayList<String>();
	 * CommonMessage.debugMsg("Inside  visual Work place Month DAO Impl"); String
	 * condParms = FilterCondSql.getSafetyRelatedStr(commonFilter); String
	 * commonParams = FilterCondSql.getGridCommonParams(commonFilter); //
	 * "ISTOTALCNT="+commonFilter.getViewClick()
	 * +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow()
	 * +";";
	 * 
	 * String type=commonFilter.getAbnViewType();
	 * 
	 * CommonMessage.debugMsg(" Checking for type "+type);
	 * 
	 * String dril=commonFilter.getDrillLevel(); String total="A";
	 * total=commonFilter.getTotal(); if(total==null){ total="A"; }
	 * CommonMessage.debugMsg("In side DAO Impl"+dril);
	 * 
	 * if(dril.equals("CMP")||dril.equals("COMP")){
	 * CommonMessage.debugMsg("In side DAO Impl CMP"+dril);
	 * 
	 * condParms+="DRILLLEVEL=COMP;"; } else if(dril.equals("SECT")){
	 * CommonMessage.debugMsg("In side DAO Impl SECT"+dril);
	 * 
	 * condParms+="DRILLLEVEL=SECT;"; }
	 * 
	 * else if(dril.equals("CELL")){ condParms+="DRILLLEVEL=CELL;";
	 * if(UIUtils.isValidKeyId(commonFilter.getYear())) condParms
	 * +="HALFYEAR="+commonFilter.getYear()+";";
	 * 
	 * 
	 * } else if(dril.equals("EMP")){//Changed Here Swetha
	 * //condParms+="DRILLLEVEL=CELL;";
	 * if(UIUtils.isValidKeyId(commonFilter.getYear())) condParms
	 * +="HALFYEAR="+commonFilter.getYear()+";";
	 * 
	 * 
	 * }//Changed Here Swetha
	 * 
	 * 
	 * if(UIUtils.isValidKeyId(type)) condParms +="TYPE="+type+";";
	 * 
	 * 
	 * paramValues.add(condParms); paramValues.add(commonParams);
	 * CommonMessage.debugMsg(total+"Inside Visual Month DAO Impl " + paramValues);
	 * List<String[]> impVsCompList = null;
	 * 
	 * if(dril.equals("EMP")){ CommonMessage.debugMsg("Inside  Employee " + dril);
	 * 
	 * //impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "ENT_FN_AVGSKILLINDEXEMP", paramValues); //impVsCompList =
	 * fnCallApi.callFunction("ENT_FN_AVGSKILLINDEXEMP_SB",
	 * paramValues,3,false);//cHANGES HERE
	 * 
	 * impVsCompList = fnCallApi.callFunction("ENT_FN_AVGSKILLINDEXEMP_SB",
	 * paramValues,3,false);//cHANGES HERE
	 * 
	 * 
	 * 
	 * for (String[] arr : impVsCompList) {
	 * CommonMessage.debugMsg(Arrays.toString(arr)); }
	 * 
	 * CommonMessage.debugMsg(); if(total.equals("Z")){ impVsCompList =
	 * dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXZERO", paramValues);
	 * CommonMessage.debugMsg("Inside Visual Month Dao in side IF " + paramValues);
	 * 
	 * } else impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXEMP", paramValues);
	 * 
	 * 
	 * } else if(dril.equals("CELL")){
	 * 
	 * //impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "ENT_FN_AVGSKILINDXSCORCELL", paramValues); //impVsCompList =
	 * fnCallApi.callFunction("ENT_FN_AVGSKILINDXSCORCELL_SB", paramValues,3,false);
	 * 
	 * impVsCompList = fnCallApi.callFunction("ENT_FN_AVGSKILINDXSCORCELL_JHAVG",
	 * paramValues,3,false); //ent_fn_avgskilindxscorcell_new_sb for (String[] arr :
	 * impVsCompList) { CommonMessage.debugMsg(Arrays.toString(arr)); }
	 * 
	 * } else{ if(total.equals("Z")){ impVsCompList =
	 * dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXZERO", paramValues);
	 * CommonMessage.debugMsg("Inside Visual Month Dao in side IF  " + paramValues);
	 * 
	 * } else{ //impVsCompList =
	 * dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXEMP", paramValues);
	 * CommonMessage.debugMsg(paramValues+"out side  Employee " + dril);
	 * 
	 * 
	 * 
	 * //impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "ENT_FN_AVGSKILLINDEXSCORE", paramValues); impVsCompList =
	 * fnCallApi.callFunction("ENT_FN_AVGSKILLINDEXSCORE_DMTAVG",
	 * paramValues,3,false); CommonMessage.debugMsg("printig the DMT S"); for
	 * (String[] arr : impVsCompList) {
	 * CommonMessage.debugMsg(Arrays.toString(arr)); } // impVsCompList =
	 * dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXZERO", paramValues);
	 * 
	 * CommonMessage.debugMsg("Inside Visual Month Dao in side Impl " +
	 * paramValues);
	 * 
	 * //} } else{
	 * CommonMessage.debugMsg("Inside Visual Month Dao in side Else Impl " +
	 * paramValues);
	 * 
	 * impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders(
	 * "KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXSCORE", paramValues);
	 * 
	 * } if( commonFilter.getViewClick() == 'Y'){ String totalCnt =
	 * paramValues.get(0); boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	 * if( isInteger ){ commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt)); }
	 * }
	 * 
	 * return impVsCompList;
	 * 
	 * }
	 * 
	 * catch (Exception e) { e.printStackTrace(); throw new
	 * Exception(e.getMessage()); } }
	 */
	
	
	@Override
	public List<String[]> getavgSkillScoreGraph(CommonFilter commonFilter) throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			System.out.println("Inside  visual Work place Month DAO Impl");
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			String type=commonFilter.getAbnViewType();
			
			CommonFunctions.debugMsg(" Checking for type "+type);
			
			String dril=commonFilter.getDrillLevel();
			String total="A";
			total=commonFilter.getTotal(); 
			if(total==null){
				total="A";
			}
			System.out.println("In side DAO Impl"+dril);
			
			if(dril.equals("CMP")||dril.equals("COMP")){
				System.out.println("In side DAO Impl CMP"+dril);

				condParms+="DRILLLEVEL=COMP;";
			}
			else if(dril.equals("SECT")){
				System.out.println("In side DAO Impl SECT"+dril);

				condParms+="DRILLLEVEL=SECT;";
			}
			
			else if(dril.equals("CELL")){
				condParms+="DRILLLEVEL=CELL;";
				if(UIUtils.isValidKeyId(commonFilter.getYear()))
					condParms +="HALFYEAR="+commonFilter.getYear()+";";				
				
				
			}
			else if(dril.equals("EMP")){//Changed Here Swetha
				//condParms+="DRILLLEVEL=CELL;";
				if(UIUtils.isValidKeyId(commonFilter.getYear()))
					condParms +="HALFYEAR="+commonFilter.getYear()+";";				
				
				
			}//Changed Here Swetha
			
			
			if(UIUtils.isValidKeyId(type))
				condParms +="TYPE="+type+";";	
			
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			System.out.println(total+"Inside Visual Month DAO Impl " + paramValues);
			List<String[]> impVsCompList = null;
			
			if(dril.equals("EMP")){
				System.out.println("Inside  Employee " + dril);

				//impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_FN_AVGSKILLINDEXEMP", paramValues);
				//impVsCompList = fnCallApi.callFunction("ENT_FN_AVGSKILLINDEXEMP_SB", paramValues,3,false);//cHANGES HERE
				
				impVsCompList = fnCallApi.callFunction("ENT_FN_AVGSKILLINDEXEMP_SB", paramValues,3,false);//cHANGES HERE 
				
				
				
				for (String[] arr : impVsCompList) {
				    System.out.println(Arrays.toString(arr));
				}

				/*System.out.println();
				if(total.equals("Z")){
					impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXZERO", paramValues);
					System.out.println("Inside Visual Month Dao in side IF " + paramValues);
					
				}
				else
					impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXEMP", paramValues);

			*/
				}
			else if(dril.equals("CELL"))
			{
				
				//impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_FN_AVGSKILINDXSCORCELL", paramValues);
				//impVsCompList = fnCallApi.callFunction("ENT_FN_AVGSKILINDXSCORCELL_SB", paramValues,3,false);
				
				if(UIUtils.isValidKeyId(commonFilter.getYear()))
					impVsCompList = fnCallApi.callFunction("ENT_FN_AVGSKILINDXSCORCELL_JHAVG", paramValues,3,false);
				else
					impVsCompList = fnCallApi.callFunction("ENT_FN_AVGSKILINDXSCORCELL_JHAVG_NEW", paramValues,3,false);
				//ent_fn_avgskilindxscorcell_new_sb
				for (String[] arr : impVsCompList) {
				    System.out.println(Arrays.toString(arr));
				}

			}
				else{
					/*if(total.equals("Z")){
						impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXZERO", paramValues);
						System.out.println("Inside Visual Month Dao in side IF  " + paramValues);
						
					}
					else{*/
				//impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXEMP", paramValues);
					System.out.println(paramValues+"out side  Employee " + dril);
					
					

					//impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_FN_AVGSKILLINDEXSCORE", paramValues);
					impVsCompList = fnCallApi.callFunction("ENT_FN_AVGSKILLINDEXSCORE_DMTAVG", paramValues,3,false);
					System.out.println("printig the DMT S");
					for (String[] arr : impVsCompList) {
					    System.out.println(Arrays.toString(arr));
					}
				//	impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXZERO", paramValues);

				System.out.println("Inside Visual Month Dao in side Impl " + paramValues);
				
					//}
				}
			/*else{
				System.out.println("Inside Visual Month Dao in side Else Impl " + paramValues);

			 impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZENREPORTS.ENT_FN_AVGSKILLINDEXSCORE", paramValues);
			
			}*/
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
			return impVsCompList;			
		
	}
	
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
	}
	
	
	@Override
	public Workbook avgSkillGraphExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
			throws Exception {
		ResultSet rs = null;
		try {
			CommonMessage.debugMsg("Entered in to service impl JH WISE");

			rs = getavgSkillResultSet(commonFilter);

			CommonMessage.debugMsg("Entered into FINAL ");

			CommonMessage.debugMsg("rss==" + rs);

			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			CommonMessage.debugMsg("colModel==" + colModel);
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
			return excelUtils.writeToExcel(rs, rptFormat, 3, 0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}
	
	private ResultSet getavgSkillResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getavgSkillFilterParamValues(commonFilter);

		String drilLevel = commonFilter.getDrillLevel();
		System.out.println(drilLevel + "drilLeveldrilLeveldrilLevel IN DaoImpl");
		ResultSet rs = null;
		if (drilLevel.equals("EMP")) {
			//rs = dbActionTemplate.NewdbFunctionCall2("ENT_FN_AVGSKILLINDEXEMP", paramValues);
			rs = dbActionTemplate.NewdbFunctionCall2("ENT_FN_AVGSKILLINDEXEMP_SB", paramValues);//FUNCTION CHANGE

		} else if (drilLevel.equals("CELL")) // ent_fn_avgskilindxscorcell
		{
			//rs = dbActionTemplate.NewdbFunctionCall2("ENT_FN_AVGSKILINDXSCORCELL", paramValues);
			rs = dbActionTemplate.NewdbFunctionCall2("ENT_FN_AVGSKILINDXSCORCELL_JHAVG", paramValues);//FUNCTION CHANGE

		} else {
			//rs = dbActionTemplate.NewdbFunctionCall2("ENT_FN_AVGSKILLINDEXSCORE", paramValues);
			rs = dbActionTemplate.NewdbFunctionCall2("ENT_FN_AVGSKILLINDEXSCORE_DMTAVG", paramValues);//FUNCTION CHANGE
		}
		return rs;
	}

	private List<String> getavgSkillFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();

		CommonFunctions.debugMsg(" commonFilter" + commonFilter);
		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		condParms +="HALFYEAR="+commonFilter.getYear()+";";//ADDED HALF YEAR VALUE
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		
		CommonFunctions.debugMsg(" condPArams" + condParms);
		paramValues.add(condParms);
		paramValues.add(commonParams);

		return paramValues;
	}

	/*
	 * private ResultSet getavgSkillResultSet(CommonFilter commonFilter) throws
	 * Exception { List<String> paramValues =
	 * getavgSkillFilterParamValues(commonFilter);
	 * 
	 * String drilLevel = commonFilter.getDrillLevel();
	 * CommonMessage.debugMsg(drilLevel + "drilLeveldrilLeveldrilLevel IN DaoImpl");
	 * ResultSet rs = null; if (drilLevel.equals("EMP")) { rs =
	 * dbActionTemplate.NewdbFunctionCall2("ENT_FN_AVGSKILLINDEXEMP", paramValues);
	 * 
	 * } else if (drilLevel.equals("CELL")) // ent_fn_avgskilindxscorcell { rs =
	 * dbActionTemplate.NewdbFunctionCall2("ENT_FN_AVGSKILINDXSCORCELL",
	 * paramValues);
	 * 
	 * } else { rs =
	 * dbActionTemplate.NewdbFunctionCall2("ENT_FN_AVGSKILLINDEXSCORE",
	 * paramValues); } return rs; }
	 * 
	 * private List<String> getavgSkillFilterParamValues(CommonFilter commonFilter)
	 * { List<String> paramValues = new ArrayList<String>();
	 * 
	 * CommonMessage.debugMsg(" commonFilter" + commonFilter); String condParms =
	 * FilterCondSql.getSafetyRelatedStr(commonFilter); String commonParams =
	 * FilterCondSql.getGridCommonParams(commonFilter);
	 * CommonMessage.debugMsg(" condPArams" + condParms);
	 * paramValues.add(condParms); paramValues.add(commonParams);
	 * 
	 * return paramValues; }
	 */

	@Override
	public List<String[]> getTradeWiseSkillIndex(CommonFilter commonFilter) throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();

			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			condParms = condParms + "ETPMCODE=" + commonFilter.getEmpWise() + ";";
			CommonMessage.debugMsg("condParms --->" + condParms);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg("commonParams--->" + commonParams);
			paramValues.add(condParms);

			paramValues.add(commonParams);

			// List<String[]> dataList =
			// dbActionTemplate.processFunctionCallsWithColHeaders("ENT_FN_TRADEWISESKILLINDEX",
			// paramValues);

			List<String[]> dataList = fnCallApi.callFunction("ENT_FN_TRADEWISESKILLINDEX_SB", paramValues, 3, false);
			CommonMessage.debugMsg("Inside daoimpl 5: " + dataList.size());
			int size = dataList.size();
			CommonMessage.debugMsg("Size :" + size);
			/*
			 * if(size>3){ String last[] = dataList.get(size-1); String lastTemp[] = new
			 * String[last.length]; CommonMessage.debugMsg("last :" + last.length); try {
			 * for (int i=0; i<last.length;i++) { CommonMessage.debugMsg(i + " : "
			 * +last[i]); if(i<=1){ lastTemp[i]=last[i]; }else{ double value =
			 * Double.parseDouble(last[i]); DecimalFormat df= new DecimalFormat("#.00");
			 * df.setRoundingMode(RoundingMode.CEILING); double avgValue= (
			 * value/(double)(size-4)); lastTemp[i]=df.format(avgValue); } } } catch
			 * (Exception e) { CommonMessage.debugMsg( " : " +e); }
			 * 
			 * List<String[]> dataListModified =new ArrayList<String[]>(dataList.size());
			 * for(int i=0;i<dataList.size()-1 ;i++){ dataListModified.add(dataList.get(i));
			 * } dataListModified.add(lastTemp); dataList = dataListModified; }
			 */
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
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
	public String getFnlnDescription(String flid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT FNLN_DESCRIPTION  FROM  GEN_MV_FLIDHIERARCHY  where FLID= '");
		sql.append(flid).append("'");
		CommonMessage.debugMsg("SQL     " + sql);
		String fnlnDescripntion = dbActionTemplate.getSingleValue(sql.toString());
		return fnlnDescripntion;
	}

	public List<String[]> getTradeWiseColumnData(CommonFilter commonFilter) throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();

			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			condParms = condParms + "ETPMCODE=" + commonFilter.getEmpWise() + ";";
			CommonMessage.debugMsg("condParms --->" + condParms);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg("commonParams--->" + commonParams);
			paramValues.add(condParms);

			paramValues.add(commonParams);

			List<String[]> dataList = dbActionTemplate.processFunctionCalls("ENT_FN_AVGSKILLCOLUMN", paramValues);
			CommonMessage.debugMsg("Inside daoimpl 5: " + dataList.size());
			int size = dataList.size();
			CommonMessage.debugMsg("Size :" + size);

			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
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

	public Workbook tradewiseSkillGraphExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
			throws Exception {
		ResultSet rs = null;
		try {
			rs = gettradewiseSkillResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			return excelUtils.writeToExcel(rs, rptFormat, 2, 0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet gettradewiseSkillResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = gettradewiseFilterParamValues(commonFilter);
		return dbActionTemplate.NewdbFunctionCall2("ENT_FN_TRADEWISESKILLINDEX", paramValues);
	}

	private List<String> gettradewiseFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		condParms = condParms + "ETPMCODE=" + commonFilter.getEmpWise() + ";";
		CommonMessage.debugMsg("condParms --->" + condParms);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		CommonMessage.debugMsg("commonParams--->" + commonParams);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}

}
