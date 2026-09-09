package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.KpiTlActualKkDao;
import com.akranta.tpm.dao.sql.KpiTlActualKkSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KpiTlActualKk;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.JHAuditSheetCreationItcServiApi;
import com.akranta.tpm.service.api.KpiTlActualandIndicatorServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class KpiTlActualKkDaoImpl implements KpiTlActualKkDao {
	
	private KpiTlActualandIndicatorServiceApi kpiTlActualandIndicatorServiceApi;
	FunctionCallApi fnCallApi;
	
	
	
	private DBActionTemplate dbActionTemplate;
	KpiTlActualKkSql newKpiTlActualKkSql;

	public KpiTlActualKkDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		newKpiTlActualKkSql = new KpiTlActualKkSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void KpiTlActualKkDaoImplJwt(String JwtToken) 
	{
		try{
			kpiTlActualandIndicatorServiceApi = new KpiTlActualandIndicatorServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public List<KpiTlActualKk> create(List<KpiTlActualKk> KpiTlActualKk)
			throws ValidationExceptions, BusinessApplicationExceptions, Exception {

		List<String> sqls = new ArrayList<String>();
		try {
			if (KpiTlActualKk != null && KpiTlActualKk.size() > 0) {
				for (KpiTlActualKk newKpiTlActualKk : KpiTlActualKk) {
					if (newKpiTlActualKk.getKaukActive().equals("Y")) {
						if (!UIUtils.isValidKeyId(newKpiTlActualKk.getKaukKeyid())) {
							newKpiTlActualKk.setKaukKeyid(dbActionTemplate
									.getSequenceNumber(TableNames.TBL_KPI_TL_ACTUAL, 10, "KAU", null, null)); // set the
																												// sequnce
																												// number
							sqls.add(newKpiTlActualKkSql.getInsertSql(newKpiTlActualKkSql.getKaukDbFields(),
									newKpiTlActualKk.getSaveArray())); // add insert sql for master table}
						} else {
							sqls.add(newKpiTlActualKkSql.getUpdateSql(newKpiTlActualKkSql.getKaukDbFields(),
									newKpiTlActualKk.getSaveArray()));
						}
					} else if (newKpiTlActualKk.getKaukActive().equals("N")) {
						sqls.add(newKpiTlActualKkSql.getDeleteSql(newKpiTlActualKkSql.getKaukDbFields(),
								newKpiTlActualKk.getSaveArray()));
					}
				}
				dbActionTemplate.executeStatements(sqls);
			}
		} catch (BusinessApplicationExceptions e) {
			CommonMessage.debugMsg("e.getMessage():" + e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return KpiTlActualKk;
	}

	public List<KpiTlActualKk> update(List<KpiTlActualKk> KpiTlActualKk) throws Exception {

		List<String> sqls = new ArrayList<String>();
		try {
			if (KpiTlActualKk != null && KpiTlActualKk.size() > 0) {
				for (KpiTlActualKk newKpiTlActualKk : KpiTlActualKk) {
					sqls.add(newKpiTlActualKkSql.getUpdateSql(newKpiTlActualKkSql.getKaukDbFields(),
							newKpiTlActualKk.getSaveArray()));
				}
				dbActionTemplate.executeStatements(sqls);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		return KpiTlActualKk;
	}

	public List<KpiTlActualKk> delete(List<KpiTlActualKk> KpiTlActualKk) throws Exception {
		List<String> sqls = new ArrayList<String>();
		try {
			if (KpiTlActualKk != null && KpiTlActualKk.size() > 0) {
				for (KpiTlActualKk newKpiTlActualKk : KpiTlActualKk) {
					sqls.add(newKpiTlActualKkSql.getDeleteSql(newKpiTlActualKkSql.getKaukDbFields(),
							newKpiTlActualKk.getSaveArray()));
				}
				dbActionTemplate.executeStatements(sqls);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return KpiTlActualKk;
	}

	public String getPillarKeyId(String pillarCode) throws Exception {
		String sql = newKpiTlActualKkSql.getPillarKeyIdSql(pillarCode);
		CommonMessage.debugMsg(" sql: " + sql);
		String pillarKeyId = dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg(" pillarKeyId: " + pillarKeyId);

		return pillarKeyId;
	}

	public KpiTlActualKk select(KpiTlActualKk newKpiTlActualKk) throws Exception {
		String sql = newKpiTlActualKkSql.getSelectSql(newKpiTlActualKkSql.getKaukDbFields(),
				newKpiTlActualKk.getSaveArray());
		CommonMessage.debugMsg("DAO SQL : " + sql);
		CommonMessage.debugMsg("sql" + sql);
		Object[] args = new Object[] {};
		newKpiTlActualKk.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		CommonMessage.debugMsg("DAO Query:" + newKpiTlActualKk.getKaukKeyid());
		return newKpiTlActualKk;
	}

	public List<String[]> selectSchedule(KpiTlActualKk newKpiTlActualKk) throws Exception {
		String sql = null;
		// sql=newKpiTlActualKkSql.getRefersSql(newKpiTlActualKkSql.getKaukDbFields(),
		// newKpiTlActualKk.getSaveArray());
		CommonMessage.debugMsg(" sql " + sql);
		List<String[]> resultList = dbActionTemplate.getDataList(sql.toString());
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return resultList;
	}

	public List<KpiTlActualKk> selectList(KpiTlActualKk newKpiTlActualKk) throws Exception {
		String sql = null;
		sql = newKpiTlActualKkSql.getDeleteSql(newKpiTlActualKkSql.getKaukDbFields(), newKpiTlActualKk.getSaveArray());
		CommonMessage.debugMsg(" sql: " + sql);
		List<String[]> resultList = dbActionTemplate.getDataList(sql.toString());
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return fillKpiActualQmList(resultList);
	}

	public List<String[]> getCostOfQualitygridData(KpiTlActualKk newKpiTlActualKk, GridParams gridParams,
			CommonFilter commonFilter) throws Exception {
		CommonMessage.debugMsg("U r in DAO");
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		commonParams = commonParams + commonFilter.getTemp();
		CommonMessage.debugMsg("commonParams test :" + commonParams);
		CommonMessage.debugMsg("commonFilter.getTemp() :" + commonFilter.getTemp());

		List<String> paramValues = new ArrayList<String>();
		String Sql = "";
		CommonMessage.debugMsg(" monthYear " + newKpiTlActualKk.getKaukMonthyear());
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukIndicatorid())) {
			CommonMessage.debugMsg("Keyid available  " + newKpiTlActualKk.getKaukIndicatorid());
			Sql = Sql + "INDICATORID=" + newKpiTlActualKk.getKaukIndicatorid() + ";";
		}
		if (CommonFunctions.isValidKeyId(commonFilter.getType())) {
			CommonMessage.debugMsg(" Type:" + commonFilter.getType());
			Sql = Sql + "TYPE=" + commonFilter.getType() + ";";
		}
		if (CommonFunctions.isValidKeyId(commonFilter.getSafetyMode())) {

			Sql = Sql + "FORMMODE=" + commonFilter.getSafetyMode() + ";";
			CommonMessage.debugMsg("The FormMode" + Sql);
		}

		/*
		 * if (CommonFunctions.isValidKeyId(indicatorId)) { for(int i= 0;
		 * i<=indicatorList.length;i++){
		 * CommonMessage.debugMsg("Keyid available  "+newKpiTlActualKk.
		 * getKaukIndicatorid()); Sql=Sql+"INDICATORID=" +
		 * newKpiTlActualKk.getKaukIndicatorid() + ";" ; } }
		 */

		CommonMessage.debugMsg("Keyid Not available " + newKpiTlActualKk.getKaukIndicatorid());

		/*
		 * if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukDeptid()))
		 * Sql=Sql+"FACTORYID=" + newKpiTlActualKk.getKaukDeptid() + ";" ;
		 */
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukDeptid()))
			Sql = Sql + "FLID=" + newKpiTlActualKk.getKaukDeptid() + ";";
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukDepttype()))
			Sql = Sql + "SECTIONID=" + newKpiTlActualKk.getKaukDepttype() + ";";
		/*
		 * if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukFactoryid()))
		 * Sql=Sql+"FACTORYID=" + newKpiTlActualKk.getKaukFactoryid() + ";" ; if
		 * (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukSectionid()))
		 * Sql=Sql+"SECTIONID=" + newKpiTlActualKk.getKaukSectionid() + ";" ; if
		 * (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukCellid()))
		 * Sql=Sql+"CELLID=" + newKpiTlActualKk.getKaukCellid() + ";" ;
		 */
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukPillarid()))
			Sql = Sql + "PILLARID=" + newKpiTlActualKk.getKaukPillarid() + ";";
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukCalendaryear()))
			Sql = Sql + "CALENDARYEAR=" + newKpiTlActualKk.getKaukCalendaryear() + ";";
		/*if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukFreqtype()))
			Sql = Sql + "FREQTYPE=" + newKpiTlActualKk.getKaukFreqtype() + ";";
		else
			Sql = Sql + "FREQTYPE=D;";*/
		
		
	    /* ------------- ✅ FIXED FREQTYPE BLOCK -------------- */
	    String freq = newKpiTlActualKk.getKaukFreqtype();

	    if ("M".equals(freq)) {
	        Sql = Sql + "FREQTYPE=M;";
	    } else if ("D".equals(freq)) {
	        Sql = Sql + "FREQTYPE=D;";
	    } else {
	        Sql = Sql + "FREQTYPE=M;";   // Default Monthly
	    }
	    /* ---------------------------------------------------- */
	    
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukMonthyear()))
			Sql = Sql + "FROMMONTH=" + newKpiTlActualKk.getKaukMonthyear() + ";";
		CommonMessage.debugMsg("Frquency type" + newKpiTlActualKk.getKaukFreqtype());
		if (!UIUtils.isValidKeyId(gridParams.getFromRow())) {

			gridParams.setFromRow("1");
			gridParams.setToRow("100");

		}
		if (gridParams.getFromRow().equals("101") && gridParams.getToRow().equals("100")) {

			Long totalRowCount = gridParams.getTotalRecordCnt();
			totalRowCount.toString();
			gridParams.setToRow(totalRowCount.toString() + 100);
			CommonMessage.debugMsg("totalRowCount.toString()" + totalRowCount.toString());
		}
		// CommonMessage.debugMsg("toRow "+gridParams.getFromRow() +"---"+
		// gridParams.getToRow());
		// CommonMessage.debugMsg("toRow And from row
		// "+gridParams.getFromRow().equals("101")+" && "+
		// gridParams.getToRow().equals("100"));

		Sql = Sql + "FROMTOROW=" + gridParams.getFromRow() + " AND " + gridParams.getToRow() + ";";
		Sql = Sql + "FROMEXL=N;";
		if (newKpiTlActualKk.getKaukIsactual().equals("Y")) {
			Sql = Sql + "KPIMODE=A;";
		} else {
			Sql = Sql + "KPIMODE=T;";
		}
		// paramValues.add(gridParams.getFromRow());
		// paramValues.add(gridParams.getToRow());
		// INDICATORID PILLARID
		List<String[]> resultList;
		CommonMessage.debugMsg("indicatorIDDD  " + newKpiTlActualKk.getKaukIndicatorid());
		String paramData = null;
		if ("dashbd".equals(newKpiTlActualKk.getKaukTempfield1())) {
			/*
			 * if("KIN0000559".equals(newKpiTlActualKk.getKaukIndicatorid())) paramData =
			 * "INDICATORID=KIN0000559;PILLARID=TGT005;CALENDARYEAR=2013;FREQTYPE=M;FROMMONTH=APR-2013;FROMTOROW=1 AND 100;"
			 * ; else if("KIN0000565".equals(newKpiTlActualKk.getKaukIndicatorid()))
			 * paramData =
			 * "INDICATORID=KIN0000565;PILLARID=TGT005;CALENDARYEAR=2013;FREQTYPE=M;FROMMONTH=APR-2013;FROMTOROW=1 AND 100;"
			 * ;
			 */
			CommonMessage.debugMsg("paramData  " + paramData);
			paramValues.add(paramData);
			paramValues.add(commonParams);
			//resultList = dbActionTemplate.processFunctionCalls("KPI_FN_BMINDICATOR", paramValues);
			
			resultList = fnCallApi.callFunction("KPI_FN_BMINDICATOR_SB", paramValues,1,false);
			
		} else {
			paramValues.add(Sql);
			paramValues.add(commonParams);
			if (newKpiTlActualKk.getKaukIsactual().equals("Y")) {
				CommonMessage.debugMsg("Phase 1");
				//resultList = dbActionTemplate.processFunctionCalls("KPI_FN_BMINDICATOR", paramValues);
				resultList = fnCallApi.callFunction("KPI_FN_BMINDICATOR_SB", paramValues,1,false);
			} else {
				//resultList = dbActionTemplate.processFunctionCalls("KPI_FN_BMINDICATOR", paramValues);
				resultList = fnCallApi.callFunction("KPI_FN_BMINDICATOR_SB", paramValues,1,false);
				CommonMessage.debugMsg("Phase 2");
				// resultList =
				// dbActionTemplate.processFunctionCalls("KPI_PC_KEYPERFORMANCE.KPI_FN_BMINDICATORFORTGT",
				// paramValues);
			}

		}
		// CommonMessage.debugMsg("fromVaue :"+newKpiTlActualKk.getKaukTempfield1()+"
		// "+newKpiTlActualKk.getKaukIsactual());

		if (commonFilter.getViewClick() == 'Y') {
			String totalCnt = paramValues.get(0);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if (isInteger) {
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		CommonMessage.debugMsg(" resultList size " + resultList.size());

		return resultList;
	}

	public List<String[]> getDatesList(KpiTlActualKk newKpiTlActualKk) throws Exception {
		String sql = newKpiTlActualKkSql.getSelectDateSql(newKpiTlActualKkSql.getKaukDbFields(),
				newKpiTlActualKk.getSaveArray());
		CommonMessage.debugMsg(" DATE sql " + sql);
		List<String[]> resultList = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return resultList;
	}

	public String getStartMonth() throws Exception {
		String sql = null;
		sql = newKpiTlActualKkSql.getSelectStartMonthSql();
		CommonMessage.debugMsg(" sql: " + sql);
		String resultList = dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg(" getStartMonth: " + resultList);
		return resultList;
	}

	private List<KpiTlActualKk> fillKpiActualQmList(List<String[]> resultList) throws SQLException {
		List<KpiTlActualKk> menus = new ArrayList<KpiTlActualKk>();
		for (String[] row : resultList) {
			KpiTlActualKk newKpiTlActualKk = new KpiTlActualKk();
			/*
			 * newKpiTlActualKk.setKaukKeyid(row[0]);
			 * newKpiTlActualKk.setKaukIndicatorid(row[1]);
			 * newKpiTlActualKk.setKaukFactoryid(row[2]);
			 * newKpiTlActualKk.setKaukSectionid(row[3]);
			 * newKpiTlActualKk.setKaukCellid(row[4]);
			 * newKpiTlActualKk.setKaukPillarid(row[5]);
			 * newKpiTlActualKk.setKaukCalendaryear(row[6]);
			 * newKpiTlActualKk.setKaukMonthyear(row[7]);
			 * newKpiTlActualKk.setKaukExcellencevalue(row[8]);
			 * newKpiTlActualKk.setKaukBenchmarkvalue(row[9]);
			 * newKpiTlActualKk.setKaukValue(row[10]);
			 * newKpiTlActualKk.setKaukIsactual(row[11]);
			 * newKpiTlActualKk.setKaukFreqtype(row[12]);
			 * newKpiTlActualKk.setKaukStatus(row[13]);
			 * newKpiTlActualKk.setKaukTempfield1(row[14]);
			 * newKpiTlActualKk.setKaukTempfield2(row[15]);
			 * newKpiTlActualKk.setKaukTempfield3(row[16]); menus.add(newKpiTlActualKk);
			 */
			newKpiTlActualKk.setKaukKeyid(row[0]);
			newKpiTlActualKk.setKaukIndicatorid(row[1]);
			newKpiTlActualKk.setKaukDeptid(row[2]);
			newKpiTlActualKk.setKaukDepttype(row[3]);
			newKpiTlActualKk.setKaukPillarid(row[4]);
			newKpiTlActualKk.setKaukCalendaryear(row[5]);
			newKpiTlActualKk.setKaukMonthyear(row[6]);
			newKpiTlActualKk.setKaukExcellencevalue(row[7]);
			newKpiTlActualKk.setKaukBenchmarkvalue(row[8]);
			newKpiTlActualKk.setKaukValue(row[9]);
			newKpiTlActualKk.setKaukIsactual(row[10]);
			newKpiTlActualKk.setKaukFreqtype(row[11]);
			newKpiTlActualKk.setKaukStatus(row[12]);
			newKpiTlActualKk.setKaukTempfield1(row[13]);
			newKpiTlActualKk.setKaukTempfield2(row[14]);
			newKpiTlActualKk.setKaukTempfield3(row[15]);
			menus.add(newKpiTlActualKk);
		}
		return menus;
	}

	@Override
	public List<String[]> getListOfIndicators(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		String pillarId = commonFilter.getPillarWise();
		String rptType = commonFilter.getKK();
		String type = commonFilter.getType();
		List<String> paramValues = new ArrayList<String>();
		paramValues.add("FREQTYPE=" + rptType + ";TYPE=" + type + ";");
		paramValues.add(commonFilter.getFlid());
		CommonMessage.debugMsg("ParamValues:" + paramValues);
		List<String[]> indicatorList;
		//indicatorList = dbActionTemplate.processFunctionCalls("KPI_FN_BMINDICATORPOPUP", paramValues);
		indicatorList = fnCallApi.callFunction("KPI_FN_BMINDICATORPOPUP_SB", paramValues,1,true);
		CommonMessage.debugMsg(" indicatorList size " + indicatorList.size());
		return indicatorList;
	}

	@Override
	public List<String[]> getKPIRemarks(CommonFilter commonFilter) throws Exception {

		List<String> paramValues = new ArrayList<String>();
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
//		 paramValues.add("INDICATORID=" +commonFilter.getIndicator()+";");
		
		String condParam = "INDICATORID=" + commonFilter.getIndicator() + ";";
		String kprmDate = commonFilter.getFromDate();
		if (kprmDate != null && !kprmDate.isEmpty() && !kprmDate.equals("01-Jan-1801")) {
		    condParam += "KPRMDATE=" + kprmDate + ";";
		}
		paramValues.add(condParam);
		// sriram 20-nov-2025
		//paramValues.add("KPRM_INDICATORID=" + commonFilter.getIndicator() + ";");
		paramValues.add(commonParams);
		//List<String[]> remarksData = dbActionTemplate.processFunctionCalls("KPI_FN_REMARKS", paramValues);
		List<String[]> remarksData = fnCallApi.callFunction("KPI_FN_REMARKS_SB", paramValues,3,true);
		CommonMessage.debugMsg("Inside daoimpl 5: " + remarksData.size());
		if (commonFilter.getViewClick() == 'Y') {
			String totalCnt = paramValues.get(0);
			CommonMessage.debugMsg("totalCnt...." + totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if (isInteger) {
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		CommonMessage.debugMsg("Test --->" + remarksData.size());
		return remarksData;
		/*
		 * sql.append(" SELECT * FROM(SELECT 'Remarks',0 AS DATAORDER FROM DUAL");
		 * sql.append(" UNION SELECT 'Remarks1' AS Remarks,1 AS DATAORDER FROM DUAL");
		 * sql.append(" UNION SELECT 'Remarks2' AS Remarks,2 AS DATAORDER FROM DUAL");
		 * sql.append(" UNION SELECT 'Remarks3' AS Remarks,2 AS DATAORDER FROM DUAL");
		 * sql.append(" UNION SELECT 'Remarks4' AS Remarks,2 AS DATAORDER FROM DUAL");
		 * sql.append(" UNION SELECT '' AS Remarks,3 DATAORDER FROM DUAL");
		 * sql.append(" ) ORDER BY DATAORDER");
		 */
		/*
		 * StringBuffer sql = new StringBuffer(); sql.
		 * append(" SELECT * FROM( SELECT 'cmbKprmKeyid' as cmbKprmKeyid,'Remarks'  as Remarks,0 AS DATAORDER FROM DUAL"
		 * ); sql.
		 * append(" UNION SELECT KPRM_KEYID, KPRM_REMARKS  ,1 AS DATAORDER  FROM KPI_TL_KPIREMARKS "
		 * ); sql.append(" WHERE KPRM_INDICATORID = '" + commonFilter.getIndicator() +
		 * "' "); sql.append(" ) ORDER BY DATAORDER ");
		 * CommonMessage.debugMsg("sql String...."+sql.toString()); List<String[]>
		 * getKPIRemarks = dbActionTemplate.getDataList(sql.toString()); return
		 * getKPIRemarks;
		 */
	}

	@Override
	public List<String[]> getWeeksList(KpiTlActualKk newKpiTlActualKk) throws Exception {
		// TODO Auto-generated method stub
		String sql = newKpiTlActualKkSql.getSelectWeekSql(newKpiTlActualKkSql.getKaukDbFields(),
				newKpiTlActualKk.getSaveArray());
		CommonMessage.debugMsg(" DATE sql " + sql);
		List<String[]> resultList = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return resultList;
	}

	@Override
	public Workbook getActualKKListExl(KpiTlActualKk newKpiTlActualKk, GridParams gridParams, CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try {
			rs = getActualKKListResultSet(commonFilter, newKpiTlActualKk, gridParams);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			int a = (int) gridParams.getTotalRecordCnt();
			CommonMessage.debugMsg("**LAST ROW** " + a);
			/*
			 * XLConditionalFormats condFormatEmpty = new XLConditionalFormats();
			 * condFormatEmpty.setBgColor(new RGB(102,178,255));
			 * condFormatEmpty.setFontColor(new RGB(254,0,0)); //red font
			 * condFormatEmpty.setFontName("Arial");
			 * condFormatEmpty.setFontHeightPoint((short)13);
			 * condFormatEmpty.setFontBoldWeight((short)13); condFormatEmpty.setFromCol(-1);
			 * condFormatEmpty.setToCol(a+13);
			 * condFormatEmpty.setOperator(ComparisonOperator.EQUAL);
			 * condFormatEmpty.setCondValue("Actual"); //NULL
			 * 
			 */
		
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setBgColor(new RGB(102, 178, 255));
			condFormat.setFontColor(new RGB(0, 0, 254)); // red font
			condFormat.setFontName("Arial");
			condFormat.setFontHeightPoint((short) 13);
			condFormat.setFontBoldWeight((short) 13);
			condFormat.setFromCol(-1);
			condFormat.setToCol(a + 13);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("Target"); // Tick
			condFormat.setDbChkColIndx(3);
			condFormats.add(condFormat);
			// condFormats.add(condFormatEmpty);

			/*
			 * Workbook wb = null; Sheet sheet = wb.getSheetAt(0);
			 * sheet.createFreezePane(3,0); Row row = null; row = sheet.getRow(9);
			 * HSSFCellStyle cellStyle = (HSSFCellStyle) wb.createCellStyle(); cellStyle =
			 * (HSSFCellStyle) wb.createCellStyle();
			 * cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
			 * cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			 * row.setRowStyle(cellStyle);
			 */

			excelUtils.setCondFormats(condFormats);

			return excelUtils.writeToExcel(rs, format, 2, 0, 0);
		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet getActualKKListResultSet(CommonFilter commonFilter, KpiTlActualKk newKpiTlActualKk,
			GridParams gridParams) throws Exception {

		// String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

		List<String> paramValues = new ArrayList<String>();
		// String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																				// +";FROMTOROW="+commonFilter.getFromRow()
																				// +" AND " + commonFilter.getToRow()
																				// +";";
		commonParams = commonParams + commonFilter.getTemp();
		CommonMessage.debugMsg("commonParams test :" + commonParams);
		CommonMessage.debugMsg("commonFilter.getTemp() :" + commonFilter.getTemp());

		String Sql = "";
		CommonMessage.debugMsg(" monthYear " + newKpiTlActualKk.getKaukMonthyear());
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukIndicatorid())) {
			CommonMessage.debugMsg("Keyid available  " + newKpiTlActualKk.getKaukIndicatorid());
			Sql = Sql + "INDICATORID=" + newKpiTlActualKk.getKaukIndicatorid() + ";";
		}
		if (CommonFunctions.isValidKeyId(commonFilter.getType())) {
			CommonMessage.debugMsg(" Type:" + commonFilter.getType());
			Sql = Sql + "TYPE=" + commonFilter.getType() + ";";
		}

		// sriram 20-NOv-2025
		if (CommonFunctions.isValidKeyId(commonFilter.getSafetyMode())) {
			Sql = Sql + "FORMMODE=" + commonFilter.getSafetyMode() + ";";
			CommonMessage.debugMsg("x=x===== The FormMode in Excel Export: " + Sql);
		}
		CommonMessage.debugMsg("Keyid Not available " + newKpiTlActualKk.getKaukIndicatorid());

		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukDeptid()))
			Sql = Sql + "FLID=" + newKpiTlActualKk.getKaukDeptid() + ";";
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukDepttype()))
			Sql = Sql + "SECTIONID=" + newKpiTlActualKk.getKaukDepttype() + ";";
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukPillarid()))
			Sql = Sql + "PILLARID=" + newKpiTlActualKk.getKaukPillarid() + ";";
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukCalendaryear()))
			Sql = Sql + "CALENDARYEAR=" + newKpiTlActualKk.getKaukCalendaryear() + ";";
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukFreqtype()))
			Sql = Sql + "FREQTYPE=" + newKpiTlActualKk.getKaukFreqtype() + ";";
		else
			
			Sql = Sql + "FREQTYPE=M;";
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukMonthyear()))
			Sql = Sql + "FROMMONTH=" + newKpiTlActualKk.getKaukMonthyear() + ";";

		if (!UIUtils.isValidKeyId(gridParams.getFromRow())) {
			gridParams.setFromRow("1");
			gridParams.setToRow("100");
		}
		if (gridParams.getFromRow().equals("101") && gridParams.getToRow().equals("100")) {
			Long totalRowCount = gridParams.getTotalRecordCnt();
			totalRowCount.toString();
			gridParams.setToRow(totalRowCount.toString() + 100);
			CommonMessage.debugMsg("totalRowCount.toString()" + totalRowCount.toString());
		}
		Sql = Sql + "FROMTOROW=" + gridParams.getFromRow() + " AND " + gridParams.getToRow() + ";";
		Sql = Sql + "FROMEXL=Y;";
		if (newKpiTlActualKk.getKaukIsactual().equals("Y")) {
			Sql = Sql + "KPIMODE=A;";
		} else {
			Sql = Sql + "KPIMODE=T;";
		}
		paramValues.add(Sql);
		paramValues.add(commonParams);

		return dbActionTemplate.NewdbFunctionCall2("KPI_FN_BMINDICATOR_SB", paramValues);
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

	@Override
	public List<String[]> getCostOfQualitygridDataNewDM(KpiTlActualKk newKpiTlActualKk, GridParams gridParams,
			CommonFilter commonFilter) throws Exception {
		CommonMessage.debugMsg("U r in DAO");
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		commonParams = commonParams + commonFilter.getTemp();
		CommonMessage.debugMsg("commonParams test :" + commonParams);
		CommonMessage.debugMsg("commonFilter.getTemp() :" + commonFilter.getTemp());
		// String Month=commonFilter.getFromMonth() ;
		// CommonMessage.debugMsg("Month in dao chk"+Month);
		List<String> paramValues = new ArrayList<String>();
		String Sql = "";
		CommonMessage.debugMsg(" monthYear " + newKpiTlActualKk.getKaukMonthyear());
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukIndicatorid())) {
			CommonMessage.debugMsg("Keyid available  " + newKpiTlActualKk.getKaukIndicatorid());
			Sql = Sql + "INDICATORID=" + newKpiTlActualKk.getKaukIndicatorid() + ";";
		}
		if (CommonFunctions.isValidKeyId(commonFilter.getType())) {
			CommonMessage.debugMsg(" Type:" + commonFilter.getType());
			Sql = Sql + "TYPE=" + commonFilter.getType() + ";";
		}

		/*
		 * if (CommonFunctions.isValidKeyId(commonFilter.getFromMonth())) {
		 * CommonMessage.debugMsg(" From Month:"+commonFilter.getFromMonth());
		 * Sql=Sql+"month=" + commonFilter.getFromMonth() + ";" ; }
		 */

		if (CommonFunctions.isValidKeyId(commonFilter.getToMonth())) {
			CommonMessage.debugMsg(" To Month:" + commonFilter.getToMonth());
			Sql = Sql + "month=" + commonFilter.getToMonth() + ";";
		}

		/*
		 * if (CommonFunctions.isValidKeyId(indicatorId)) { for(int i= 0;
		 * i<=indicatorList.length;i++){
		 * CommonMessage.debugMsg("Keyid available  "+newKpiTlActualKk.
		 * getKaukIndicatorid()); Sql=Sql+"INDICATORID=" +
		 * newKpiTlActualKk.getKaukIndicatorid() + ";" ; } }
		 */

		CommonMessage.debugMsg("Keyid Not available " + newKpiTlActualKk.getKaukIndicatorid());

		/*
		 * if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukDeptid()))
		 * Sql=Sql+"FACTORYID=" + newKpiTlActualKk.getKaukDeptid() + ";" ;
		 */
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukDeptid()))
			Sql = Sql + "FLID=" + newKpiTlActualKk.getKaukDeptid() + ";";
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukDepttype()))
			Sql = Sql + "SECTIONID=" + newKpiTlActualKk.getKaukDepttype() + ";";
		/*
		 * if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukFactoryid()))
		 * Sql=Sql+"FACTORYID=" + newKpiTlActualKk.getKaukFactoryid() + ";" ; if
		 * (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukSectionid()))
		 * Sql=Sql+"SECTIONID=" + newKpiTlActualKk.getKaukSectionid() + ";" ; if
		 * (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukCellid()))
		 * Sql=Sql+"CELLID=" + newKpiTlActualKk.getKaukCellid() + ";" ;
		 */
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukPillarid()))
			Sql = Sql + "PILLARID=" + newKpiTlActualKk.getKaukPillarid() + ";";
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukCalendaryear()))
			Sql = Sql + "CALENDARYEAR=" + newKpiTlActualKk.getKaukCalendaryear() + ";";
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukFreqtype()))
			Sql = Sql + "FREQTYPE=" + newKpiTlActualKk.getKaukFreqtype() + ";";
		else
			Sql = Sql + "FREQTYPE=M;";
		if (CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukMonthyear()))
			CommonMessage.debugMsg("Sql in check" + newKpiTlActualKk.getKaukMonthyear());
		Sql = Sql + "FROMMONTH=" + commonFilter.getFromMonth() + ";";
		CommonMessage.debugMsg("Sql in check" + Sql);

		if (!UIUtils.isValidKeyId(gridParams.getFromRow())) {

			gridParams.setFromRow("1");
			gridParams.setToRow("100");

		}
		if (gridParams.getFromRow().equals("101") && gridParams.getToRow().equals("100")) {

			Long totalRowCount = gridParams.getTotalRecordCnt();
			totalRowCount.toString();
			gridParams.setToRow(totalRowCount.toString() + 100);
			CommonMessage.debugMsg("totalRowCount.toString()" + totalRowCount.toString());
		}
		// CommonMessage.debugMsg("toRow "+gridParams.getFromRow() +"---"+
		// gridParams.getToRow());
		// CommonMessage.debugMsg("toRow And from row
		// "+gridParams.getFromRow().equals("101")+" && "+
		// gridParams.getToRow().equals("100"));

		Sql = Sql + "FROMTOROW=" + gridParams.getFromRow() + " AND " + gridParams.getToRow() + ";";
		Sql = Sql + "FROMEXL=N;";
		if (newKpiTlActualKk.getKaukIsactual().equals("Y")) {
			Sql = Sql + "KPIMODE=A;";
		} else {
			Sql = Sql + "KPIMODE=T;";
		}
		// paramValues.add(gridParams.getFromRow());
		// paramValues.add(gridParams.getToRow());
		// INDICATORID PILLARID
		List<String[]> resultList;
		CommonMessage.debugMsg("indicatorIDDD  " + newKpiTlActualKk.getKaukIndicatorid());
		String paramData = null;
		if ("dashbd".equals(newKpiTlActualKk.getKaukTempfield1())) {
			/*
			 * if("KIN0000559".equals(newKpiTlActualKk.getKaukIndicatorid())) paramData =
			 * "INDICATORID=KIN0000559;PILLARID=TGT005;CALENDARYEAR=2013;FREQTYPE=M;FROMMONTH=APR-2013;FROMTOROW=1 AND 100;"
			 * ; else if("KIN0000565".equals(newKpiTlActualKk.getKaukIndicatorid()))
			 * paramData =
			 * "INDICATORID=KIN0000565;PILLARID=TGT005;CALENDARYEAR=2013;FREQTYPE=M;FROMMONTH=APR-2013;FROMTOROW=1 AND 100;"
			 * ;
			 */
			CommonMessage.debugMsg("paramData  " + paramData);
			paramValues.add(paramData);
			paramValues.add(commonParams);
			resultList = dbActionTemplate.processFunctionCalls("KPI_FN_NEWBMINDICATORNEWDM", paramValues);
		} else {
			paramValues.add(Sql);
			paramValues.add(commonParams);
			if (newKpiTlActualKk.getKaukIsactual().equals("Y")) {
				CommonMessage.debugMsg("Phase 1");
				resultList = dbActionTemplate.processFunctionCalls("KPI_FN_NEWBMINDICATORNEWDM", paramValues);
				for (String[] row : resultList) {
					CommonMessage.debugMsg(Arrays.toString(row));
				}
			} else {
				resultList = dbActionTemplate.processFunctionCalls("KPI_FN_NEWBMINDICATORNEWDM", paramValues);
				for (String[] row : resultList) {
					CommonMessage.debugMsg(Arrays.toString(row));
				}
				CommonMessage.debugMsg("Phase 2");
				// resultList =
				// dbActionTemplate.processFunctionCalls("KPI_PC_KEYPERFORMANCE.KPI_FN_BMINDICATORFORTGT",
				// paramValues);
			}

		}
		// CommonMessage.debugMsg("fromVaue :"+newKpiTlActualKk.getKaukTempfield1()+"
		// "+newKpiTlActualKk.getKaukIsactual());

		if (commonFilter.getViewClick() == 'Y') {
			String totalCnt = paramValues.get(0);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if (isInteger) {
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		CommonMessage.debugMsg(" resultList size " + resultList.size());

		return resultList;
	}

	@Override
	public List<String[]> getIndicatorRemarks(String indicatorid) throws Exception {
		// TODO Auto-generated method stub
		// KIN0000133
		String sql = "SELECT  TO_CHAR(KPRM_DATE,'DD-MON-YYYY'),KPRM_REMARKS from KPI_TL_KPIREMARKS, KPI_TL_ACTUAL where 1=1 AND KPRM_INDICATORID = KAUK_KEYID AND KAUK_INDICATORID IN ('"
				+ indicatorid + "')  ORDER BY KPRM_DATE DESC";
		// String sql="SELECT TO_CHAR(KPRM_DATE,'DD-MON-YYYY'),KPRM_REMARKS from
		// KPI_TL_KPIREMARKS where KPRM_INDICATORID='"+"KIN0000133"+"'";
//	
		CommonMessage.debugMsg("sql:::" + sql);
		List<String[]> resultList = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg("resultList:::" + resultList);
		return resultList;
	}
/*public List<String[]> getKPIRemarksReport(CommonFilter commonFilter) throws Exception {
		
		List<String> paramValues = new ArrayList<String>();
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		
		
		paramValues.add("INDICATORID=" +commonFilter.getIndicator()+";RPTTYPE="+commonFilter.getFreq()+";");
		
		/*if (CommonFunctions.isValidKeyId(commonFilter.getFreq())){
		paramValues.add("RPTTYPE=" +commonFilter.getFreq()+";");
		}*/
		/*paramValues.add(commonParams);				
		List<String[]> remarksData=   dbActionTemplate.processFunctionCalls("KPI_FN_REMARKS", paramValues);
		CommonMessage.debugMsg("Inside daoimpl 5: " + remarksData.size());
		if (commonFilter.getViewClick() == 'Y') {
			String totalCnt = paramValues.get(0);
			CommonMessage.debugMsg("totalCnt...." + totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if (isInteger) {
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		CommonMessage.debugMsg("Test --->" + remarksData.size());
		return remarksData;
}*/

	
	
	@Override
	public List<String[]> getKPIRemarksReport(CommonFilter commonFilter) throws Exception {

		List<String> paramValues = new ArrayList<String>();
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		//paramValues.add("INDICATORID=" + commonFilter.getIndicator() + ";RPTTYPE="+commonFilter.getFreq()+";");
		//paramValues.add("INDICATORID=" +commonFilter.getIndicator()+";");
		String condParam = "INDICATORID=" + commonFilter.getIndicator() + ";";
		String kprmDate = commonFilter.getFromDate();
		if (kprmDate != null && !kprmDate.isEmpty() && !kprmDate.equals("01-Jan-1801")) {
		    condParam += "KPRMDATE=" + kprmDate + ";";
		}
		paramValues.add(condParam);
		paramValues.add(commonParams);
		//List<String[]> remarksData = dbActionTemplate.processFunctionCalls("KPI_FN_REMARKS", paramValues);
		List<String[]> remarksData = fnCallApi.callFunction("KPI_FN_REMARKS_SB", paramValues,3,true);
		
		CommonMessage.debugMsg("Inside daoimpl 5: " + remarksData.size());
		if (commonFilter.getViewClick() == 'Y') {
			String totalCnt = paramValues.get(0);
			CommonMessage.debugMsg("totalCnt...." + totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if (isInteger) {
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		CommonMessage.debugMsg("Test --->" + remarksData.size());
		return remarksData;
		
	}
	

	@Override
	public List<String[]> getKPIkeyid(String kinkid, String flid, String year, String freq) throws Exception {
		// TODO Auto-generated method stub
		String sql = null;
		if (freq.equals("M")) {
			// sql="select KAUK_KEYID from KPI_TL_INDICATOR,KPI_TL_ACTUAL where
			// KINK_KEYID=KAUK_INDICATORID AND KINK_KEYID='"+kinkid+"' AND
			// KAUK_CALENDARYEAR='"+year+"' AND KAUK_ACTIVE='Y' AND KAUK_FREQTYPE='M' and
			// KAUK_DEPTID='"+flid+"'" ;
			// sql="select KAUK_KEYID from KPI_TL_INDICATOR,KPI_TL_ACTUAL where
			// KINK_KEYID=KAUK_INDICATORID AND KINK_KEYID='"+kinkid+"' AND KAUK_ACTIVE='Y'
			// AND KAUK_FREQTYPE='M' and KAUK_DEPTID='"+flid+"' AND
			// KAUK_CALENDARYEAR='"+year+"'" ;
			sql = "select KAUK_KEYID from KPI_TL_ACTUAL where    KAUK_INDICATORID='" + kinkid
					+ "'   AND KAUK_ACTIVE='Y'  AND KAUK_FREQTYPE='M'  and KAUK_DEPTID='" + flid + "' ";
		} else {
			// sql="select KAUK_KEYID from KPI_TL_INDICATOR,KPI_TL_ACTUAL where
			// KINK_KEYID=KAUK_INDICATORID AND KINK_KEYID='"+kinkid+"' AND KAUK_ACTIVE='Y'
			// AND KAUK_FREQTYPE='D' and KAUK_DEPTID='"+flid+"' AND
			// KAUK_CALENDARYEAR='"+year+"'";
			sql = "select KAUK_KEYID from KPI_TL_INDICATOR,KPI_TL_ACTUAL where  KINK_KEYID=KAUK_INDICATORID AND  KINK_KEYID='"
					+ kinkid + "' AND KAUK_ACTIVE='Y' AND KAUK_FREQTYPE='D'  and KAUK_DEPTID='" + flid
					+ "' AND KAUK_CALENDARYEAR='" + year + "'";
		}
		CommonMessage.debugMsg("sql" + sql);
		List<String[]> resultList = dbActionTemplate.getDataList(sql);
		return resultList;
	}

	@Override
	public String getKPIyear(String kinkid) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql = "select KAUK_CALENDARYEAR from KPI_TL_INDICATOR,KPI_TL_ACTUAL where  KINK_KEYID=KAUK_INDICATORID AND  KINK_KEYID IN ("
				+ kinkid + ")";
		CommonMessage.debugMsg("sql" + sql);
		return dbActionTemplate.getSingleValue(sql);
	}

	@Override
	public List<String[]> KPIDeviationData(String flid, String year, String CurrDate, String CurrMonthYear,
			String frequency) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		if (frequency.equals("D")) {
			sql.append(
					" Select KAUK_INDICATORID,KINK_INDICATORNAME,UOM ,KAUK_MONTHYEAR,KAUK_FREQTYPE,Target,Actual, Deviation");
			sql.append(
					" from(SELECT KAUK_INDICATORID,KINK_INDICATORNAME,UOM,KAUK_MONTHYEAR,KAUK_FREQTYPE,Target, Actual, ");
			sql.append(
					" (case when actual<target then 'Deviation' else '-' end) as Deviation from (SELECT KAUK_INDICATORID,KINK_INDICATORNAME, UOMM_DESCRIPTION as UOM,KAUK_MONTHYEAR, KAUK_FREQTYPE, ");
			sql.append(
					" max(decode(kauk_isactual, 'N', kauk_value)) Target, max(decode(kauk_isactual, 'Y', kauk_value)) Actual FROM KPI_TL_ACTUAL,ADM_TL_UOMMST,KPI_TL_INDICATOR WHERE 1=1 and KINK_KEYID=KAUK_INDICATORID and UOMM_KEYID=KINK_UOMID AND KAUK_DEPTID = '"
							+ flid + "'");
			sql.append(" AND KAUK_CALENDARYEAR='" + year + "' AND KAUK_FREQTYPE='" + frequency
					+ "'  AND  TO_CHAR(KAUK_MONTHYEAR,'DD-Mon-yyyy')='" + CurrDate
					+ "' group by KAUK_INDICATORID,KINK_INDICATORNAME,UOMM_DESCRIPTION,KAUK_MONTHYEAR,KAUK_FREQTYPE))where deviation<>'-' ");
			CommonMessage.debugMsg("KPI Deviation Data:Daily::" + sql);
		} else {
			sql.append(
					" Select KAUK_INDICATORID,KINK_INDICATORNAME,UOM ,KAUK_MONTHYEAR,KAUK_FREQTYPE,Target,Actual, Deviation");
			sql.append(
					" from(SELECT KAUK_INDICATORID,KINK_INDICATORNAME,UOM,KAUK_MONTHYEAR,KAUK_FREQTYPE,Target, Actual, ");
			sql.append(
					" (case when actual<target then 'Deviation' else '-' end) as Deviation from (SELECT KAUK_INDICATORID,KINK_INDICATORNAME, UOMM_DESCRIPTION as UOM,KAUK_MONTHYEAR, KAUK_FREQTYPE, ");
			sql.append(
					" max(decode(kauk_isactual, 'N', kauk_value)) Target, max(decode(kauk_isactual, 'Y', kauk_value)) Actual FROM KPI_TL_ACTUAL,ADM_TL_UOMMST,KPI_TL_INDICATOR WHERE 1=1 and KINK_KEYID=KAUK_INDICATORID and UOMM_KEYID=KINK_UOMID AND KAUK_DEPTID = '"
							+ flid + "'");
			sql.append(" AND KAUK_CALENDARYEAR='" + year + "' AND KAUK_FREQTYPE='" + frequency
					+ "' AND TO_CHAR(KAUK_MONTHYEAR,'Mon-yyyy')='" + CurrMonthYear
					+ "' group by KAUK_INDICATORID,KINK_INDICATORNAME,UOMM_DESCRIPTION,KAUK_MONTHYEAR,KAUK_FREQTYPE))where deviation<>'-' ");
			CommonMessage.debugMsg("KPI Deviation Data::Monthly:" + sql);
		}

		List<String[]> TotalList = dbActionTemplate.getDataList(sql.toString());
		return TotalList;
	}

	/*
	 * public List<String[]> getKPIEmpMailIds(String flid,String location,String
	 * rolename) throws Exception{ StringBuilder sql=new StringBuilder(); String
	 * enableEmail="Y"; sql.
	 * append("SELECT DISTINCT EMPM_NAME, EMPM_EMAIL  FROM Gen_Tl_Fnlnroleteam,Adm_Tl_Roleorder,GEN_MV_FLIDHIERARCHY,gen_tl_employeemst "
	 * ); sql.
	 * append(" WHERE 1=1 AND Role_Keyid = Frt_Role_Keyid AND Flid = Frt_Fnln_Keyid AND Frt_Fnln_Keyid='"
	 * +flid+"' "); sql.append(" AND FRT_ROLE_KEYID='"
	 * +rolename+"' AND FRT_EMPM_KEYID=EMPM_KEYID AND EMPM_ENABLEEMAIL='"
	 * +enableEmail+"' "); CommonMessage.debugMsg("Mail List:::"+sql); List<String[]>
	 * MailList=dbActionTemplate.getDataList(sql.toString()); return MailList;
	 * 
	 * }
	 */
	public List<String[]> getKPIEmpMailIds(String flid, String location, String rolename) throws Exception {
		StringBuilder sql = new StringBuilder();
		String enableEmail = "Y";
		sql.append(
				"SELECT DISTINCT EMPM_NAME, EMPM_EMAIL  FROM Gen_Tl_Fnlnroleteam,Adm_Tl_Roleorder,GEN_MV_FLIDHIERARCHY,gen_tl_employeemst ");
		sql.append(
				" WHERE 1=1 AND Role_Keyid = Frt_Role_Keyid AND Flid = Frt_Fnln_Keyid AND Frt_Fnln_Keyid IN('FNL000124053','FNL000124032') ");
		sql.append(" AND FRT_ROLE_KEYID IN('AROL0006','AROL0003') AND FRT_EMPM_KEYID=EMPM_KEYID AND EMPM_ENABLEEMAIL='"
				+ enableEmail + "' ");
		CommonMessage.debugMsg("Mail List:::" + sql);
		List<String[]> MailList = dbActionTemplate.getDataList(sql.toString());
		return MailList;

	}

	public String getKPIDeviationCount(String flid, String year, String CurrDate, String CurrMonthYear,
			String frequency) throws Exception {
		StringBuilder sql = new StringBuilder();

		if (frequency.equals("D")) {
//		    sql.append(" select count(deviation) from (Select KAUK_INDICATORID,KINK_INDICATORNAME,UOM ,KAUK_MONTHYEAR,KAUK_FREQTYPE,Target,Actual, Deviation");
//			sql.append(" from(SELECT KAUK_INDICATORID,KINK_INDICATORNAME,UOM,KAUK_MONTHYEAR,KAUK_FREQTYPE,Target, Actual, ");
//			sql.append(" (case when actual<target then 'Deviation' else '-' end) as Deviation from (SELECT KAUK_INDICATORID,KINK_INDICATORNAME, UOMM_DESCRIPTION as UOM,KAUK_MONTHYEAR, KAUK_FREQTYPE, ");
//			sql.append(" max(decode(kauk_isactual, 'N', kauk_value)) Target, max(decode(kauk_isactual, 'Y', kauk_value)) Actual FROM KPI_TL_ACTUAL,ADM_TL_UOMMST,KPI_TL_INDICATOR WHERE 1=1 and KINK_KEYID=KAUK_INDICATORID and UOMM_KEYID=KINK_UOMID AND KAUK_DEPTID = '"+flid+"'");
//			sql.append(" AND KAUK_CALENDARYEAR='"+year+"' AND KAUK_FREQTYPE='"+frequency+"'  AND  TO_CHAR(KAUK_MONTHYEAR,'DD-Mon-yyyy')='"+CurrDate+"' group by KAUK_INDICATORID,KINK_INDICATORNAME,UOMM_DESCRIPTION,KAUK_MONTHYEAR,KAUK_FREQTYPE))where deviation<>'-')");

			// sriram 17-Nov-2025

			sql.append(" SELECT COUNT(deviation) FROM (");
			sql.append(
					"   SELECT KAUK_INDICATORID, KINK_INDICATORNAME, UOM, KAUK_MONTHYEAR, KAUK_FREQTYPE, Target, Actual, Deviation");
			sql.append("   FROM (");
			sql.append(
					"     SELECT KAUK_INDICATORID, KINK_INDICATORNAME, UOM, KAUK_MONTHYEAR, KAUK_FREQTYPE, Target, Actual,");
			sql.append("       CASE WHEN actual < target THEN 'Deviation' ELSE '-' END AS Deviation");
			sql.append("     FROM (");
			sql.append(
					"       SELECT KAUK_INDICATORID, KINK_INDICATORNAME, UOMM_DESCRIPTION AS UOM, KAUK_MONTHYEAR, KAUK_FREQTYPE,");
			sql.append("         MAX(CASE WHEN kauk_isactual = 'N' THEN kauk_value END) AS Target,");
			sql.append("         MAX(CASE WHEN kauk_isactual = 'Y' THEN kauk_value END) AS Actual");
			sql.append("       FROM KPI_TL_ACTUAL, ADM_TL_UOMMST, KPI_TL_INDICATOR");
			sql.append("       WHERE KINK_KEYID = KAUK_INDICATORID");
			sql.append("         AND UOMM_KEYID = KINK_UOMID");
			sql.append("         AND KAUK_DEPTID = '" + flid + "'");
			sql.append("         AND KAUK_CALENDARYEAR = '" + year + "'");
			sql.append("         AND KAUK_FREQTYPE = '" + frequency + "'");
			sql.append("         AND TO_CHAR(KAUK_MONTHYEAR, 'DD-Mon-yyyy') = '" + CurrDate + "'");
			sql.append(
					"       GROUP BY KAUK_INDICATORID, KINK_INDICATORNAME, UOMM_DESCRIPTION, KAUK_MONTHYEAR, KAUK_FREQTYPE");
			sql.append("     ) AS inner_query");
			sql.append("   ) AS middle_query");
			sql.append("   WHERE deviation <> '-'");
			sql.append(" ) AS outer_query");

			CommonMessage.debugMsg("KPI Deviation Count::D:" + sql);
		} /*else {
			sql.append(
					" select count(deviation) from (Select KAUK_INDICATORID,KINK_INDICATORNAME,UOM ,KAUK_MONTHYEAR,KAUK_FREQTYPE,Target,Actual, Deviation");
			sql.append(
					" from(SELECT KAUK_INDICATORID,KINK_INDICATORNAME,UOM,KAUK_MONTHYEAR,KAUK_FREQTYPE,Target, Actual, ");
			sql.append(
					" (case when actual<target then 'Deviation' else '-' end) as Deviation from (SELECT KAUK_INDICATORID,KINK_INDICATORNAME, UOMM_DESCRIPTION as UOM,KAUK_MONTHYEAR, KAUK_FREQTYPE, ");
			sql.append(
					" max(decode(kauk_isactual, 'N', kauk_value)) Target, max(decode(kauk_isactual, 'Y', kauk_value)) Actual FROM KPI_TL_ACTUAL,ADM_TL_UOMMST,KPI_TL_INDICATOR WHERE 1=1 and KINK_KEYID=KAUK_INDICATORID and UOMM_KEYID=KINK_UOMID AND KAUK_DEPTID = '"
							+ flid + "'");
			sql.append(" AND KAUK_CALENDARYEAR='" + year + "' AND KAUK_FREQTYPE='" + frequency
					+ "' AND TO_CHAR(KAUK_MONTHYEAR,'Mon-yyyy')='" + CurrMonthYear
					+ "' group by KAUK_INDICATORID,KINK_INDICATORNAME,UOMM_DESCRIPTION,KAUK_MONTHYEAR,KAUK_FREQTYPE))where deviation<>'-')");
			CommonMessage.debugMsg("Data:::" + sql);
		}*/
		
		// sriram 23-NOv-2025
		else {
		    sql.append(
		        " SELECT COUNT(deviation) FROM (SELECT KAUK_INDICATORID, KINK_INDICATORNAME, UOM, KAUK_MONTHYEAR, KAUK_FREQTYPE, Target, Actual, Deviation");
		    sql.append(
		        " FROM (SELECT KAUK_INDICATORID, KINK_INDICATORNAME, UOM, KAUK_MONTHYEAR, KAUK_FREQTYPE, Target, Actual, ");
		    sql.append(
		        " (CASE WHEN actual < target THEN 'Deviation' ELSE '-' END) AS Deviation FROM (SELECT KAUK_INDICATORID, KINK_INDICATORNAME, UOMM_DESCRIPTION AS UOM, KAUK_MONTHYEAR, KAUK_FREQTYPE, ");
		    sql.append(
		        " MAX(CASE WHEN kauk_isactual = 'N' THEN kauk_value ELSE NULL END) AS Target, MAX(CASE WHEN kauk_isactual = 'Y' THEN kauk_value ELSE NULL END) AS Actual FROM KPI_TL_ACTUAL, ADM_TL_UOMMST, KPI_TL_INDICATOR WHERE 1=1 AND KINK_KEYID = KAUK_INDICATORID AND UOMM_KEYID = KINK_UOMID AND KAUK_DEPTID = '"
		            + flid + "'");
		    sql.append(" AND KAUK_CALENDARYEAR = '" + year + "' AND KAUK_FREQTYPE = '" + frequency
		            + "' AND TO_CHAR(KAUK_MONTHYEAR, 'Mon-yyyy') = '" + CurrMonthYear
		            + "' GROUP BY KAUK_INDICATORID, KINK_INDICATORNAME, UOMM_DESCRIPTION, KAUK_MONTHYEAR, KAUK_FREQTYPE) AS subquery1) AS subquery2 WHERE deviation <> '-') AS subquery3");
		    CommonMessage.debugMsg("else Data:::" + sql);
		}
		// List<String[]> TotalList=dbActionTemplate.getDataList(sql.toString());
		return dbActionTemplate.getSingleValue(sql.toString());
		// return sql.toString();
	}

	@Override
	public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT FNLN_ELEMENTID,FNLN_KEYID,ROLE_LEVEL,ROLE_NAME,ROLE_KEYID FROM  GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST  ");
		sql.append(" WHERE FNLN_KEYID = FRT_FNLN_KEYID  and FRT_ROLE_KEYID = ROLE_KEYID  ");
		if (UIUtils.isValidKeyId(loginflid))
			sql.append(" AND FRT_FNLN_KEYID  = '" + loginflid + "' ");
		sql.append(" AND FRT_EMPM_KEYID = '" + empId + "'  AND ROLE_LEVEL= '" + loginlevel + "'");
		CommonMessage.debugMsg("The RoleLevel:::" + sql);
		Object[] args = {};
		List<String[]> userDatas = dbActionTemplate.getDataList(sql.toString(), args);
		return userDatas;
	}
	/*
	 * @Override public List<String[]> getrpakpidata(CommonFilter commonFilter)
	 * throws Exception { // TODO Auto-generated method stub String result=null;
	 * 
	 * String
	 * sql="select RPAM_INDICATORID from RPA_TL_KPIMAPPING WHERE RPAM_INDICATORID='"
	 * +commonFilter.getKey()+"' AND  RPAM_ISACTIVE='Y' ";
	 * CommonMessage.debugMsg("Query"+sql); String dataListing=dbActionTemplate.
	 * getSingleValue("select RPAM_INDICATORID from RPA_TL_KPIMAPPING WHERE RPAM_INDICATORID='"
	 * +commonFilter.getKey()+"' AND  RPAM_ISACTIVE='Y'  ");
	 * CommonMessage.debugMsg("COUNT OF RPAKPI  is"+dataListing); //return dataListing;
	 * Object [] args = {} ; List<String []> userDatas =
	 * dbActionTemplate.getDataList(sql.toString(),args); return userDatas; }
	 */

	@Override
	public List<String[]> gerpakpidata(CommonFilter commonFilter) throws Exception {
		String result = null;

		String sql = "select RPAM_INDICATORID from RPA_TL_KPIMAPPING WHERE RPAM_INDICATORID='" + commonFilter.getKey()
				+ "' AND  RPAM_ISACTIVE='Y' ";
		CommonMessage.debugMsg("Query" + sql);
		String dataListing = dbActionTemplate
				.getSingleValue("select RPAM_INDICATORID from RPA_TL_KPIMAPPING WHERE RPAM_INDICATORID='"
						+ commonFilter.getKey() + "' AND  RPAM_ISACTIVE='Y'  ");
		CommonMessage.debugMsg("COUNT OF RPAKPI  is" + dataListing);
		// return dataListing;
		Object[] args = {};
		List<String[]> userDatas = dbActionTemplate.getDataList(sql.toString(), args);
		return userDatas;
	}

}
