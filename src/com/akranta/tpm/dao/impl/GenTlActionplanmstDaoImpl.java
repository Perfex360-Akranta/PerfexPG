package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.ActionPlanParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlActionplanmstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlActionplandtlSql;
import com.akranta.tpm.dao.sql.GenTlActionplanmstSql;
import com.akranta.tpm.dao.sql.GenTlMomKpiLinkSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.CommonMessage;

/* dao implementation */
public class GenTlActionplanmstDaoImpl implements GenTlActionplanmstDao {

	private DBActionTemplate dbActionTemplate;
	private GenTlActionplanmstSql genTlActionplanmstSql;
	FunctionCallApi fnCallApi;
	
	public GenTlActionplanmstDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		 genTlActionplanmstSql = new GenTlActionplanmstSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void GenTlActionplanmstDaoImplJwt(String JwtToken) 
	{
		try{
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public GenTlActionplanmst getActionPlanDetailAdd(String keyId)
			throws Exception {
		GenTlActionplanmst genTlActionplanmst = new GenTlActionplanmst();
		Object args[] = new Object[] { keyId };
		String sql = " SELECT * FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID = ? ";
		genTlActionplanmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlActionplanmst;

	}

	public GenTlActionplanmst create(GenTlActionplanmst genTlActionplanmst,
			GenTlActionplandtl genTlActionplandtl) throws BusinessApplicationExceptions, Exception {
       CommonMessage.debugMsg("iNSIDE dAO1");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		GenTlActionplanmstSql genTlActionplanmstSql = new GenTlActionplanmstSql();  // contains
																					// dbtable,field
																					// names,
																					// Field
																					// types
																					// and
																					// related
																					// sqls
																					// of
																					// master
																					// table
		GenTlActionplandtlSql genTlActionplandtlSql = new GenTlActionplandtlSql();
		String elementId = genTlActionplanmst.getAplmElementid();
	 	String location = null;
	 	String seqIdentf = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlActionplanmstSql.TBL_GEN_TL_ACTIONPLANMST);

		try {
			
			genTlActionplanmst.setAplmKeyid(dbActionTemplate.getSequenceNumber(seqIdentf, 10, "APB","YY", "Y")); // set the sequnce number

			CommonMessage.debugMsg("Action plan keyid : "+genTlActionplanmst.getAplmKeyid());			//genTlActionplanmst.setAplmKeyid(dbActionTemplate.getSequenceNumber(
			//		GenTlActionplanmstSql.TBL_GEN_TL_ACTIONPLANMST, 10, "APLM",
			//		"", "")); // set the sequnce number

			// //CommonMessage.debugMsg("master key in create....."+genTlActionplanmst.getAplmKeyid());
			genTlActionplandtl.setApldAplmKeyid(genTlActionplanmst
					.getAplmKeyid());
			sqls.add(GenTlActionplanmstSql.getInsertSql(
					genTlActionplanmstSql.getAplmDbFields(),
					genTlActionplanmst.getSaveArray())); // add insert sql for
			
			//String selectall=genTlActionplandtl.getActionplanidenfr();
			List<GenTlActionplandtl> genTlActionplandtls= genTlActionplandtl.getActionplanlist();
			
			if( genTlActionplandtls !=null && genTlActionplandtls.size()>0){
				
		            for(GenTlActionplandtl genTlActionplandtlss:genTlActionplandtls)
					{
		            	genTlActionplandtlss.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,13,"APLD", "YY", "Y"));
		            	genTlActionplandtlss.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
		            	genTlActionplandtlss.setApldHowtodo(genTlActionplandtl.getApldHowtodo());
		            	genTlActionplandtlss.setApldActionplan(genTlActionplandtl.getApldActionplan());
		            	genTlActionplandtlss.setApldTradeid(genTlActionplandtl.getApldTradeid());
		            	genTlActionplandtlss.setApldStatus(genTlActionplandtl.getApldStatus());
		            	genTlActionplandtlss.setApldTargetdate(genTlActionplandtl.getApldTargetdate());
		            	genTlActionplandtlss.setApldCompleatedon(genTlActionplandtl.getApldCompleatedon());
		            	genTlActionplandtlss.setApldCompletedby(genTlActionplandtl.getApldCompletedby());
		            	genTlActionplandtlss.setApldCountermeasure(genTlActionplandtl.getApldCountermeasure());
		            	genTlActionplandtlss.setApldCreatedby(genTlActionplandtl.getApldCreatedby());
		            	genTlActionplandtlss.setApldCreatedon(genTlActionplandtl.getApldCreatedon());
		            	genTlActionplandtlss.setApldRemarks(genTlActionplandtl.getApldRemarks());
		            	genTlActionplandtlss.setApldOthers(genTlActionplandtl.getApldOthers());
		            	genTlActionplandtlss.setApldTempfiled2(genTlActionplandtl.getApldTempfiled2());
		            	genTlActionplandtlss.setApldTempfiled3(genTlActionplandtl.getApldTempfiled3());
		            	genTlActionplandtlss.setApldTempfiled4(genTlActionplandtl.getApldTempfiled4());
		            	genTlActionplandtlss.setApldTempfiled5(genTlActionplandtl.getApldTempfiled5());
		            	genTlActionplandtlss.setApldActive(genTlActionplandtl.getApldActive());
		            	genTlActionplandtlss.setApldModifiedon(genTlActionplandtl.getApldModifiedon());
		            	genTlActionplandtlss.setApldResponsibility(genTlActionplandtlss.getApldResponsibility());
						sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtlss.getSaveArray()));// add insert sql for detail table
					} 
	            }
			 else{
			   if (!CommonFunctions
					.isValidKeyId(genTlActionplandtl.getApldKeyid())) {
				// //CommonMessage.debugMsg("master key in create....."+genTlActionplanmst.getAplmKeyid());
				genTlActionplandtl.setApldKeyid(dbActionTemplate
						.getSequenceNumber(
								GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,
								10, "APLD", "", "")); // set the sequnce number
				sqls.add(GenTlActionplandtlSql.getInsertSql(
						genTlActionplandtlSql.getApldDbFields(),
						genTlActionplandtl.getSaveArray()));
			}}
			sqls.add(GenTlActionplanmstSql.getUpdateStausSql(
					genTlActionplanmstSql.getAplmDbFields(),
					genTlActionplanmst.getSaveArray())); // add insert sql for
															// master table
			
			String sql = GenTlActionplanmstSql
					.getRefDocUpdateSql(genTlActionplanmst);
			
			if (CommonFunctions.isValidKeyId(sql))
				sqls.add(sql);
			dbActionTemplate.executeStatements(sqls); // execute the block of
														// sqls
			CommonMessage.debugMsg(" Inside 8 ");

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return genTlActionplanmst;
	}

	public GenTlActionplanmst update(
			GenTlActionplanmst existGenTlActionplanmst,
			GenTlActionplanmst genTlActionplanmst,
			GenTlActionplandtl genTlActionplandtl) throws BusinessApplicationExceptions,Exception {
		List<String> sqls = new ArrayList<String>();
		GenTlActionplanmstSql genTlActionplanmstSql = new GenTlActionplanmstSql();
		GenTlActionplandtlSql genTlActionplandtlSql = new GenTlActionplandtlSql();
		CommonMessage.debugMsg(" Inside 9 ");
		try {
			//sqls.add(GenTlActionplanmstSql.getUpdateSql(
				//	genTlActionplanmstSql.getAplmDbFields(),
					//genTlActionplanmst.getSaveArray(),status));
			genTlActionplandtl.setApldAplmKeyid(genTlActionplanmst
					.getAplmKeyid());
			if (CommonFunctions.isValidKeyId(genTlActionplandtl.getApldKeyid())) {CommonMessage.debugMsg(" Inside 10 ");
				sqls.add(GenTlActionplandtlSql.getUpdateSql(
						genTlActionplandtlSql.getApldDbFields(),
						genTlActionplandtl.getSaveArray()));
			} else {CommonMessage.debugMsg(" Inside 11 ");
			
               List<GenTlActionplandtl> genTlActionplandtls= genTlActionplandtl.getActionplanlist();
			
			   if(genTlActionplandtls !=null && genTlActionplandtls.size()>0){
				
		            for(GenTlActionplandtl genTlActionplandtlss:genTlActionplandtls)
					{
		            	genTlActionplandtlss.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,10,"APLD", "", ""));
		            	genTlActionplandtlss.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
		            	genTlActionplandtlss.setApldHowtodo(genTlActionplandtl.getApldHowtodo());
		            	genTlActionplandtlss.setApldActionplan(genTlActionplandtl.getApldActionplan());
		            	genTlActionplandtlss.setApldTradeid(genTlActionplandtl.getApldTradeid());
		            	genTlActionplandtlss.setApldStatus(genTlActionplandtl.getApldStatus());
		            	genTlActionplandtlss.setApldTargetdate(genTlActionplandtl.getApldTargetdate());
		            	genTlActionplandtlss.setApldCompleatedon(genTlActionplandtl.getApldCompleatedon());
		            	genTlActionplandtlss.setApldCompletedby(genTlActionplandtl.getApldCompletedby());
		            	genTlActionplandtlss.setApldCountermeasure(genTlActionplandtl.getApldCountermeasure());
		            	genTlActionplandtlss.setApldCreatedby(genTlActionplandtl.getApldCreatedby());
		            	genTlActionplandtlss.setApldCreatedon(genTlActionplandtl.getApldCreatedon());
		            	genTlActionplandtlss.setApldRemarks(genTlActionplandtl.getApldRemarks());
		            	genTlActionplandtlss.setApldOthers(genTlActionplandtl.getApldOthers());
		            	genTlActionplandtlss.setApldTempfiled2(genTlActionplandtl.getApldTempfiled2());
		            	genTlActionplandtlss.setApldTempfiled3(genTlActionplandtl.getApldTempfiled3());
		            	genTlActionplandtlss.setApldTempfiled4(genTlActionplandtl.getApldTempfiled4());
		            	genTlActionplandtlss.setApldTempfiled5(genTlActionplandtl.getApldTempfiled5());
		            	genTlActionplandtlss.setApldActive(genTlActionplandtl.getApldActive());
		            	genTlActionplandtlss.setApldModifiedon(genTlActionplandtl.getApldModifiedon());
		            	genTlActionplandtlss.setApldResponsibility(genTlActionplandtlss.getApldResponsibility());
						sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtlss.getSaveArray()));// add insert sql for detail table
					} 
			  }else{
				genTlActionplandtl.setApldKeyid(dbActionTemplate
						.getSequenceNumber(
								GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,
								10, "APLD", "", "")); // set the sequnce number
				sqls.add(GenTlActionplandtlSql.getInsertSql(
						genTlActionplandtlSql.getApldDbFields(),
						genTlActionplandtl.getSaveArray()));CommonMessage.debugMsg(" Inside 12 ");
						
			  }		
			}
			sqls.add(GenTlActionplanmstSql.getUpdateStausSql(
					genTlActionplanmstSql.getAplmDbFields(),
					genTlActionplanmst.getSaveArray())); // add insert sql for
															// master table
			CommonMessage.debugMsg(" Inside 13 ");
			String sql = GenTlActionplanmstSql
					.getRefDocUpdateSql(genTlActionplanmst);
			if (CommonFunctions.isValidKeyId(sql))
				sqls.add(sql);
			dbActionTemplate.executeStatements(sqls);

		} catch (Exception e) {CommonMessage.debugMsg(" Inside 14 ");
			// TODO Auto-generated catch block
			throw new BusinessApplicationExceptions(e.getMessage());
		}

		return genTlActionplanmst;
	}

	public GenTlActionplanmst delete(GenTlActionplanmst genTlActionplanmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlActionplanmstSql genTlActionplanmstSql = new GenTlActionplanmstSql();
		GenTlActionplandtlSql genTlActionplandtlSql = new GenTlActionplandtlSql();
		try {
			// //CommonMessage.debugMsg("master key:::"+genTlActionplanmst.getAplmKeyid());
			sqls.add(genTlActionplandtlSql.getDeleteSql(genTlActionplanmst
					.getAplmKeyid()));
			sqls.add(genTlActionplanmstSql.getDeleteSql(
					genTlActionplanmstSql.getAplmDbFields(),
					genTlActionplanmst.getSaveArray()));
			sqls.add(GenTlActionplanmstSql.getUpdateStausSql(
					genTlActionplanmstSql.getAplmDbFields(),
					genTlActionplanmst.getSaveArray())); // add insert sql for
															// master table
			String sql = GenTlActionplanmstSql
					.getRefDocUpdateSql(genTlActionplanmst);
			if (CommonFunctions.isValidKeyId(sql))
				sqls.add(sql);
			// CommonMessage.debugMsg("sql...."+sqls.toString());
			dbActionTemplate.executeStatements(sqls);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return genTlActionplanmst;
	}

	@Override
	public List<String[]> getActionPlanReport(CommonFilter commonFilter)
			throws Exception {
		try {
			// CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
			String commonParams = FilterCondSql
					.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
														// +";FROMTOROW="+commonFilter.getFromRow()
														// +" AND " +
														// commonFilter.getToRow()
														// +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			// CommonMessage.debugMsg("paramValues"+paramValues);
			List<String[]> gridData = dbActionTemplate
					.processFunctionCallsWithColHeaders(
							"ACTIONPLAN.PLM_FN_ACTIONPLANMASTER", paramValues);

			return gridData;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	/*
	 * @Override public List<String[]> getActionPlanReport(CommonFilter
	 * commonFilter) throws Exception { StringBuffer sql=new StringBuffer();
	 * List<String> params=null;
	 * sql.append(GenTlActionplanmstSql.getActionPlanReportSql());
	 * //CommonMessage.debugMsg("sql...."+sql.toString()); List<String[]>
	 * gridData = dbActionTemplate.getDataListWithColHeader(sql.toString(),
	 * params); return gridData; }
	 */
	
	
	@Override
	public List<String[]> getActionPlanDetail(CommonFilter commonFilter)
			throws Exception {
		try {
			// CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql
					.getAbnRelatedConditionStr(commonFilter);
			// CommonMessage.debugMsg("condParms...."+condParms);
			String commonParams = FilterCondSql
					.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
														// +";FROMTOROW="+commonFilter.getFromRow()
														// +" AND " +
														// commonFilter.getToRow()
														// +";";

			// CommonMessage.debugMsg("DETAILREFID...."+commonFilter.getKey());
			if (UIUtils.isValidKeyId(commonFilter.getKey())) {
				condParms += ";DETAILREFID=" + commonFilter.getKey();
			}

			if (UIUtils.isValidKeyId(commonFilter.getActionKeyId())) {
				condParms += ";MASTERKEYID=" + commonFilter.getActionKeyId();
			}
			if (UIUtils.isValidKeyId(commonFilter.getRefdocid())) {
				condParms += ";MASTERREFID=" + commonFilter.getRefdocid();
			}

			// CommonMessage.debugMsg("paramValues..11111111"+condParms);

			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> gridData = fnCallApi.callFunction("plm_fn_actionplandetail_sb", paramValues,3,false);
			//List<String[]> gridData = dbActionTemplate.processFunctionCallsWithColHeaders("plm_fn_actionplandetail_sb", paramValues);

			return gridData;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		// StringBuffer sql=new StringBuffer();
		// List<String> params=null;
		// sql.append(GenTlActionplanmstSql.getActionPlanDetailReportSql(keyId));
		// //CommonMessage.debugMsg("sql...."+sql.toString());
		// List<String[]> gridData =
		// dbActionTemplate.getDataListWithColHeader(sql.toString(), params);
	}

	@Override
	public GenTlActionplanmst getAllFillControl(
			GenTlActionplanmst genTlActionplanmst) throws Exception {
		StringBuffer sql = new StringBuffer();
		String count = "";
		sql.append(GenTlActionplanmstSql
				.getActionPlanFillControl(genTlActionplanmst));

		String dtlkeyId = genTlActionplanmst.getAplmDetailrefid();
		String keyId = genTlActionplanmst.getAplmDetailrefid();
		String cntCond = " AND APLM_DETAILREFID ='"
				+ genTlActionplanmst.getAplmDetailrefid() + "' ";

		if (!UIUtils.isValidDate(keyId)) {
			keyId = genTlActionplanmst.getAplmMasterrefid();
			cntCond = " AND APLM_MASTERREFID='"
					+ genTlActionplanmst.getAplmMasterrefid() + "' ";
		}

		count = dbActionTemplate
				.getSingleValue("Select Count(*) from (select * from gen_tl_actionplanmst where 1=1 "
						+ cntCond + ")");

		if (Integer.parseInt(count) > 0) {
			Object args[] = new Object[] { dtlkeyId };
			genTlActionplanmst.setSaveArray(dbActionTemplate.getDataArr(
					sql.toString(), args));
		}
		return genTlActionplanmst;

	}
	
	@Override
	public GenTlActionplanmst getAllFillRemainerControl(String actPlanKeyId)
			throws Exception {
		// TODO Auto-generated method stub
		
		GenTlActionplanmst genTlActionplanmst = new GenTlActionplanmst();		
		String sql = GenTlActionplanmstSql.getRemainderDataSql();				
		Object [] args =  new Object [] { actPlanKeyId };
		genTlActionplanmst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		return  genTlActionplanmst;
	}

	@Override
	public List<String[]> getMultiSelectEmp(CommonFilter commonFilter)
			throws Exception {
		StringBuffer sql = new StringBuffer();

		if (UIUtils.isValidKeyId(commonFilter.getActionKeyId())) {
			sql.append(" SELECT * FROM(SELECT 'Employee Name','Employee Number','TargetDate','CheckVal' as checkval,'EmployeeId' as EMPM_KEYID,'RSPL_REFDOCID' as RSPL_REFDOCID, 0 AS DATAORDER FROM DUAL");
			sql.append(" UNION SELECT EMPM_NAME AS Employee,EMPM_EMPLOYEENUMBER AS EMPLOYEENUMBER,to_char(RSPL_TARGETDATE,'DD-MON-YYYY') as TargetDate,'' as checkval,EMPM_KEYID as EMPM_KEYID,RSPL_KEYID, 1 AS DATAORDER FROM ");
			sql.append("  GEN_TL_RESPONSIBILITYLINK, gen_tl_employeemst ,GEN_TL_FNLNROLETEAM  where RSPL_EMPLOYEEID(+) = empm_keyid  and FRT_EMPM_KEYID = empm_keyid");
			if (UIUtils.isValidKeyId(commonFilter.getActionKeyId()))
				sql.append(" AND RSPL_REFDOCID(+) ='"
						+ commonFilter.getActionKeyId() + "' ");
			sql.append(" AND FRT_FNLN_KEYID ='");
			sql.append(commonFilter.getFlid());
			sql.append("'");
			sql.append(" ) ORDER BY DATAORDER ");
		} else {
			sql.append("SELECT   * FROM (SELECT 'Employee Name', 'Employee Number', 'TargetDate','CheckVal' AS checkval, 'EmployeeId' AS empm_keyid, 0 AS dataorder  UNION SELECT empm_name AS employee, empm_employeenumber AS employeenumber,");
			sql.append(" '' AS targetdate, '' AS checkval, empm_keyid AS empm_keyid,");
			sql.append("  1 AS dataorder");
			sql.append(" FROM gen_tl_employeemst ");
			sql.append(" LEFT JOIN GEN_TL_FNLNROLETEAM  ON EMPM_KEYID = FRT_EMPM_KEYID ");
			sql.append(" WHERE FRT_FNLN_KEYID ='");
			sql.append(commonFilter.getFlid());
			sql.append("')Order BY dataorder");
		}

		// if(UIUtils.isValidKeyId(commonFilter.getSectionId()))
		// CommonMessage.debugMsg(sql.toString());
		return dbActionTemplate.getDataList(sql.toString());

	}

	private ResultSet getActionPlanReportResultSet(CommonFilter commonFilter)
			throws Exception {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																				// +";FROMTOROW="+commonFilter.getFromRow()
																				// +" AND "
																				// +
																				// commonFilter.getToRow()
																				// +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall(
				"ACTIONPLAN.PLM_FN_ACTIONPLANMASTER", paramValues);
	}

	@Override
	public Workbook actionPlanExportExcel(CommonFilter commonFilter,
			JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		try {

			rs = getActionPlanReportResultSet(commonFilter);
			// CommonMessage.debugMsg("rs value::::::::"+
			// rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			XLConditionalFormats condFormat = new XLConditionalFormats();
			// condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short) 14);
			condFormat.setFontBoldWeight((short) 20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			// condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue((char) 252 + ""); // Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs, rptFormat, 3, 0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet getActionPlanReportDetailResultSet(
			CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql
				.getAbnRelatedConditionStr(commonFilter);
		// CommonMessage.debugMsg("condParms...."+condParms);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																				// +";FROMTOROW="+commonFilter.getFromRow()
																				// +" AND "
																				// +
																				// commonFilter.getToRow()
																				// +";";
		if (UIUtils.isValidKeyId(commonFilter.getActionKeyId())) {
			condParms += "MASTERKEYID=" + commonFilter.getActionKeyId();
		}
		if (UIUtils.isValidKeyId(commonFilter.getRefdocid())) {
			condParms += "DETAILREFID=" + commonFilter.getRefdocid();
		}
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall(
				"ACTIONPLAN.PLM_FN_ACTIONPLANDETAIL", paramValues);
	}

	@Override
	public Workbook actionPlanDetailExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format) throws Exception {
		ResultSet rs = null;
		try {

			rs = getActionPlanReportDetailResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			XLConditionalFormats condFormat = new XLConditionalFormats();
			// condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short) 14);
			condFormat.setFontBoldWeight((short) 20);
			condFormat.setFromCol(2);
			condFormat.setToCol(-1);
			// condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue((char) 252 + ""); // Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs, format,3, 0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs
					.getStatement().getConnection());
		}
	}
	public List<String[]> getAllEmployeActionPlans_old(
            ActionPlanParams actionPlanParams) throws NoDataFoundException,
            Exception {

        String innerSql = null;
        List<Object> listArgs = new ArrayList<Object>();
        Object[] args;
        if (actionPlanParams.MODE_VIEW.equals(actionPlanParams.getMode())) {
        	CommonMessage.debugMsg("in side if  "+actionPlanParams.MODE_VIEW.equals(actionPlanParams.getMode()));
            innerSql = GenTlActionplanmstSql
                    .getAllEmployeeActionPlan(actionPlanParams);
                 if (CommonFunctions.isValidKeyId(actionPlanParams.getFlid())) {
                     listArgs.add(actionPlanParams.getFlid());
                 }
            if (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromDate())) {
                listArgs.add(actionPlanParams.getDtFromDate());
            }
            if (CommonFunctions.isValidKeyId(actionPlanParams.getDtToDate())) {
                listArgs.add(actionPlanParams.getDtToDate());
            }
            if (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromMonth())) {
                listArgs.add(actionPlanParams.getDtFromMonth());
            }
            if (CommonFunctions.isValidKeyId(actionPlanParams.getDtToMonth())) {
                listArgs.add(actionPlanParams.getDtToMonth());
            
                 }
        } else {
            innerSql = GenTlActionplanmstSql.getEmployeeActionPlan(actionPlanParams);
           
            if(actionPlanParams.getKeyid()==null){
                listArgs.add(actionPlanParams.getEmployeeId());
               
            }
           
            if (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromDate())) {
                listArgs.add(actionPlanParams.getDtFromDate());
            }
            if (CommonFunctions.isValidKeyId(actionPlanParams.getDtToDate())) {
                listArgs.add(actionPlanParams.getDtToDate());
            }
            if (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromMonth())) {
                listArgs.add(actionPlanParams.getDtFromMonth());
            }
            if (CommonFunctions.isValidKeyId(actionPlanParams.getDtToMonth())) {
                listArgs.add(actionPlanParams.getDtToMonth());
            }
            if (CommonFunctions.isValidKeyId(actionPlanParams.getFlid())) {
                //listArgs.add(actionPlanParams.getFlid());
            }
            CommonMessage.debugMsg(" innerSql " + innerSql);
          //  com.akranta.tpm.utils.CommonMessage.debugMsg(" innerSql " + innerSql);
        }

        args = listArgs.toArray();
        for (int i = 0; i < args.length; i++) {
            com.akranta.tpm.utils.CommonMessage.debugMsg(i + "  == " +args[i]);
            CommonMessage.debugMsg(i + "  == " +args[i]);
        }
       
        String sql = GenTlActionplanmstSql.countSql(innerSql, actionPlanParams);
        com.akranta.tpm.utils.CommonMessage.debugMsg(" sql " + sql);
        CommonMessage.debugMsg(" sql.... " + sql);
        com.akranta.tpm.utils.CommonMessage.debugMsg(" args " + args);
        CommonMessage.debugMsg(" args " + args);
        List<String[]> data = dbActionTemplate.getDataList(sql, args);
           
        int count = 0;
        if (data.size()>0) {
            count = Integer.parseInt(data.get(0)[0]);
        }
        
        CommonMessage.debugMsg(" COUNT " + count);
        actionPlanParams.setTotalRecordCnt(count);
        if (count > 0) {
            sql = GenTlActionplanmstSql.addPaginationParams(innerSql,
                    actionPlanParams);
            return dbActionTemplate.getDataList(sql, args);
        }
        throw new NoDataFoundException("No Data Found==");
        // return null;
    }
	
	public List<String[]> getAllEmployeActionPlans(
            ActionPlanParams actionPlanParams) throws NoDataFoundException,
            Exception {

		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getActionPlanRelatedConditionStr(actionPlanParams);
		// CommonMessage.debugMsg("condParms...."+condParms);
		String commonParams = FilterCondSql.getGridActionPlanParams(actionPlanParams); 
		
       
		paramValues.add(condParms);
		paramValues.add(commonParams);
		String functionName ;
		if(actionPlanParams.MODE_VIEW.equals(actionPlanParams.getMode())) {
			functionName ="gen_fn_allemployeeactionplan_sb";
		}else {
			functionName ="gen_fn_employeeactionplan_sb";
		}
		List<String[]> gridData = fnCallApi.callFunction(functionName, paramValues,1,false);
	  // List<String[]> gridData = dbActionTemplate.processFunctionCallsWithColHeaders(functionName, paramValues);
		String totalCnt = paramValues.get(0); 
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		if(  isInteger ){
			actionPlanParams.setTotalRecordCnt(Long.parseLong(totalCnt));
		}
	
	return gridData;
		
	/*
	 * String innerSql = null; List<Object> listArgs = new ArrayList<Object>();
	 * Object[] args; if
	 * (actionPlanParams.MODE_VIEW.equals(actionPlanParams.getMode())) {
	 * CommonMessage.debugMsg("in side if  "+actionPlanParams.MODE_VIEW.equals(
	 * actionPlanParams.getMode())); innerSql = GenTlActionplanmstSql
	 * .getAllEmployeeActionPlan(actionPlanParams); if
	 * (CommonFunctions.isValidKeyId(actionPlanParams.getFlid())) {
	 * listArgs.add(actionPlanParams.getFlid()); } if
	 * (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromDate())) {
	 * listArgs.add(actionPlanParams.getDtFromDate()); } if
	 * (CommonFunctions.isValidKeyId(actionPlanParams.getDtToDate())) {
	 * listArgs.add(actionPlanParams.getDtToDate()); } if
	 * (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromMonth())) {
	 * listArgs.add(actionPlanParams.getDtFromMonth()); } if
	 * (CommonFunctions.isValidKeyId(actionPlanParams.getDtToMonth())) {
	 * listArgs.add(actionPlanParams.getDtToMonth());
	 * 
	 * } } else { innerSql =
	 * GenTlActionplanmstSql.getEmployeeActionPlan(actionPlanParams);
	 * 
	 * if(actionPlanParams.getKeyid()==null){
	 * listArgs.add(actionPlanParams.getEmployeeId());
	 * 
	 * }
	 * 
	 * if (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromDate())) {
	 * listArgs.add(actionPlanParams.getDtFromDate()); } if
	 * (CommonFunctions.isValidKeyId(actionPlanParams.getDtToDate())) {
	 * listArgs.add(actionPlanParams.getDtToDate()); } if
	 * (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromMonth())) {
	 * listArgs.add(actionPlanParams.getDtFromMonth()); } if
	 * (CommonFunctions.isValidKeyId(actionPlanParams.getDtToMonth())) {
	 * listArgs.add(actionPlanParams.getDtToMonth()); } if
	 * (CommonFunctions.isValidKeyId(actionPlanParams.getFlid())) {
	 * //listArgs.add(actionPlanParams.getFlid()); } CommonMessage.debugMsg(" innerSql "
	 * + innerSql); // com.akranta.tpm.utils.CommonMessage.debugMsg(" innerSql " +
	 * innerSql); }
	 * 
	 * args = listArgs.toArray(); for (int i = 0; i < args.length; i++) {
	 * com.akranta.tpm.utils.CommonMessage.debugMsg(i + "  == " +args[i]);
	 * CommonMessage.debugMsg(i + "  == " +args[i]); }
	 * 
	 * String sql = GenTlActionplanmstSql.countSql(innerSql, actionPlanParams);
	 * com.akranta.tpm.utils.CommonMessage.debugMsg(" sql " + sql);
	 * CommonMessage.debugMsg(" sql.... " + sql);
	 * com.akranta.tpm.utils.CommonMessage.debugMsg(" args " + args);
	 * CommonMessage.debugMsg(" args " + args); List<String[]> data =
	 * dbActionTemplate.getDataList(sql, args);
	 * 
	 * int count = 0; if (data.size()>0) { count = Integer.parseInt(data.get(0)[0]);
	 * }
	 * 
	 * CommonMessage.debugMsg(" COUNT " + count);
	 * actionPlanParams.setTotalRecordCnt(count); if (count > 0) { sql =
	 * GenTlActionplanmstSql.addPaginationParams(innerSql, actionPlanParams); return
	 * dbActionTemplate.getDataList(sql, args); } throw new
	 * NoDataFoundException("No Data Found==");
	 */
        // return null;
    }
//	public void saveActionPlanCompletion(
//			List<GenTlActionplandtl> genActionPlanList) throws Exception {
//		String sql = GenTlActionplanmstSql.updateActionPlanCompletionSql();
//		String sql1 = GenTlActionplanmstSql.updateActionPlanMstCompletionSql();
//		List<Object[]> dataList = new ArrayList<Object[]>();
//		CommonMessage.debugMsg("actionPlanDatasql : "+sql);
//		CommonMessage.debugMsg("actionPlanmst : "+sql1);
//		// CommonMessage.debugMsg(" sql " + sql);
//		for (GenTlActionplandtl genTlActionplandtl : genActionPlanList) {
//			CommonMessage.debugMsg("actionPlanmst : "+genTlActionplandtl.getApldAplmKeyid());
//			Object[] dtlDatatmp = {
////					CommonFunctions.converto_Date(CommonFunctions.isValidKeyId(genTlActionplandtl.getApldTargetdate()) ? genTlActionplandtl.getApldTargetdate() : "31-Dec-2100"),
//					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldTargetdate()) ? genTlActionplandtl.getApldTargetdate() : "31-Dec-2100",
//					genTlActionplandtl.getApldStatus(),
////					CommonFunctions.converto_Date(CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCompleatedon()) ? genTlActionplandtl.getApldCompleatedon() : "31-Dec-2100"),
//					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCompleatedon()) ? genTlActionplandtl.getApldCompleatedon() : "31-Dec-2100",
//					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCompletedby()) ? genTlActionplandtl.getApldCompletedby() : "{}",
//					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCountermeasure()) ? genTlActionplandtl.getApldCountermeasure() : "-",
//					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldRemarks()) ? genTlActionplandtl.getApldRemarks() : "-",
//					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldResponsibility()) ? genTlActionplandtl.getApldResponsibility() : "{}",
//					genTlActionplandtl.getApldKeyid() };
//			CommonMessage.debugMsg("object : "+dtlDatatmp);
//			CommonMessage.debugMsg("object : "+dtlDatatmp.toString());
//			dataList.add(dtlDatatmp);
//		}
//		int[] dataType = { Types.VARCHAR, Types.CHAR, Types.VARCHAR, Types.VARCHAR,
//				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR };
//
//		dbActionTemplate.executeBatch(sql, dataList, dataType);
//	}
	
//	public void saveActionPlanCompletion(
//			List<GenTlActionplandtl> genActionPlanList) throws Exception {
//		String sqlDtl = GenTlActionplanmstSql.updateActionPlanCompletionSql();
//		String sqlMst = GenTlActionplanmstSql.updateActionPlanMstCompletionSql();
//		
//		List<String> sqls = new ArrayList<>();
//	    sqls.add(sqlDtl);
//	    sqls.add(sqlMst);
//	    
//	    Map<Integer, List<Object[]>> valueListMap = new HashMap<>();
//	    List<int[]> dataTypesList = new ArrayList<>();
//
//	    List<Object[]> dtlList = new ArrayList<>();
//	    List<Object[]> mstList = new ArrayList<>();
//	    
////		List<Object[]> dataList = new ArrayList<Object[]>();
//		CommonMessage.debugMsg("actionPlanDatasql : "+sqlDtl);
//		CommonMessage.debugMsg("actionPlanmst : "+sqlMst);
//		// CommonMessage.debugMsg(" sql " + sql);
//		for (GenTlActionplandtl genTlActionplandtl : genActionPlanList) {
//			CommonMessage.debugMsg("actionPlanmst : "+genTlActionplandtl.getApldAplmKeyid());
//			Object[] dtlDatatmp = {
////					CommonFunctions.converto_Date(CommonFunctions.isValidKeyId(genTlActionplandtl.getApldTargetdate()) ? genTlActionplandtl.getApldTargetdate() : "31-Dec-2100"),
//					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldTargetdate()) ? genTlActionplandtl.getApldTargetdate() : "31-Dec-2100",
//					genTlActionplandtl.getApldStatus(),
////					CommonFunctions.converto_Date(CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCompleatedon()) ? genTlActionplandtl.getApldCompleatedon() : "31-Dec-2100"),
//					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCompleatedon()) ? genTlActionplandtl.getApldCompleatedon() : "31-Dec-2100",
//					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCompletedby()) ? genTlActionplandtl.getApldCompletedby() : "{}",
//					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCountermeasure()) ? genTlActionplandtl.getApldCountermeasure() : "-",
//					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldRemarks()) ? genTlActionplandtl.getApldRemarks() : "-",
//					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldResponsibility()) ? genTlActionplandtl.getApldResponsibility() : "{}",
//					genTlActionplandtl.getApldKeyid() };
//			CommonMessage.debugMsg("object : "+dtlDatatmp);
//			CommonMessage.debugMsg("object : "+dtlDatatmp.toString());
////			dataList.add(dtlDatatmp);
//			dtlList.add(dtlDatatmp);
//
//	        // For Mst table — depends on your SQL, adjust accordingly
//	        Object[] mstParams = {
//	            genTlActionplandtl.getApldAplmKeyid(),genTlActionplandtl.getApldAplmKeyid()
//	        };
//	        mstList.add(mstParams);
//		}
//		
//		valueListMap.put(0, dtlList);
//	    valueListMap.put(1, mstList);
//		int[] dtlTypes = { Types.VARCHAR, Types.CHAR, Types.VARCHAR, Types.VARCHAR,
//				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR };
//		int[] mstTypes = { Types.VARCHAR,Types.VARCHAR };
//
//	    dataTypesList.add(dtlTypes);
//	    dataTypesList.add(mstTypes);
//
//		dbActionTemplate.executeBatch(sqls, valueListMap, dataTypesList);
//	}
//	
	public void saveActionPlanCompletion(
			List<GenTlActionplandtl> genActionPlanList) throws Exception {
		String sqlDtl = GenTlActionplanmstSql.updateActionPlanCompletionSql();
		String sqlMst = GenTlActionplanmstSql.updateActionPlanMstCompletionSql();
		String sqlJHA = GenTlActionplanmstSql.updateJHAuditNcSatusSql();
		
		List<String> sqls = new ArrayList<>();
	    sqls.add(sqlDtl);
	    sqls.add(sqlMst);
	    sqls.add(sqlJHA);
	    
	    Map<Integer, List<Object[]>> valueListMap = new HashMap<>();
	    List<int[]> dataTypesList = new ArrayList<>();

	    List<Object[]> dtlList = new ArrayList<>();
	    List<Object[]> mstList = new ArrayList<>();
	    List<Object[]> jhaList = new ArrayList<>();
	    
//		List<Object[]> dataList = new ArrayList<Object[]>();
		CommonMessage.debugMsg("actionPlanDatasql : "+sqlDtl);
		CommonMessage.debugMsg("actionPlanmst : "+sqlMst);
		// CommonMessage.debugMsg(" sql " + sql);
		for (GenTlActionplandtl genTlActionplandtl : genActionPlanList) {
			CommonMessage.debugMsg("actionPlanmst : "+genTlActionplandtl.getApldAplmKeyid());
			Object[] dtlDatatmp = {
//					CommonFunctions.converto_Date(CommonFunctions.isValidKeyId(genTlActionplandtl.getApldTargetdate()) ? genTlActionplandtl.getApldTargetdate() : "31-Dec-2100"),
					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldTargetdate()) ? genTlActionplandtl.getApldTargetdate() : "31-Dec-2100",
					genTlActionplandtl.getApldStatus(),
//					CommonFunctions.converto_Date(CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCompleatedon()) ? genTlActionplandtl.getApldCompleatedon() : "31-Dec-2100"),
					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCompleatedon()) ? genTlActionplandtl.getApldCompleatedon() : "31-Dec-2100",
					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCompletedby()) ? genTlActionplandtl.getApldCompletedby() : "{}",
					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldCountermeasure()) ? genTlActionplandtl.getApldCountermeasure() : "-",
					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldRemarks()) ? genTlActionplandtl.getApldRemarks() : "-",
					CommonFunctions.isValidKeyId(genTlActionplandtl.getApldResponsibility()) ? genTlActionplandtl.getApldResponsibility() : "{}",
					genTlActionplandtl.getApldKeyid() };
			CommonMessage.debugMsg("object : "+dtlDatatmp);
			CommonMessage.debugMsg("object : "+dtlDatatmp.toString());
//			dataList.add(dtlDatatmp);
			dtlList.add(dtlDatatmp);

	        // For Mst table — depends on your SQL, adjust accordingly
	        Object[] mstParams = {
	            genTlActionplandtl.getApldAplmKeyid(),genTlActionplandtl.getApldAplmKeyid()
	        };
	        mstList.add(mstParams);
	        
	        Object[] jhaParams = {
		            genTlActionplandtl.getApldAplmKeyid()
		        };
		        jhaList.add(jhaParams);
		}
		
		valueListMap.put(0, dtlList);
	    valueListMap.put(1, mstList);
	    valueListMap.put(2, jhaList);
	    
		int[] dtlTypes = { Types.VARCHAR, Types.CHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR };
		int[] mstTypes = { Types.VARCHAR,Types.VARCHAR };
		int[] jhaTypes = { Types.VARCHAR };

	    dataTypesList.add(dtlTypes);
	    dataTypesList.add(mstTypes);
	    dataTypesList.add(jhaTypes);

		dbActionTemplate.executeBatch(sqls, valueListMap, dataTypesList);
	}

	public Workbook employeWiseActionPlanExportExcel(
			ActionPlanParams actionPlanParams, JSONObject colModel,
			String format) throws NoDataFoundException, Exception {
		ResultSet rs = null;
		
		try{
			
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getActionPlanRelatedConditionStr(actionPlanParams);
			// CommonMessage.debugMsg("condParms...."+condParms);
			String commonParams = FilterCondSql.getGridActionPlanParamsOnly(actionPlanParams); 
			
	       
			paramValues.add(condParms);
			paramValues.add(commonParams);
			String functionName ;
			if(actionPlanParams.MODE_VIEW.equals(actionPlanParams.getMode())) {
				functionName ="gen_fn_allemployeeactionplan_sb";
			}else {
				functionName ="gen_fn_employeeactionplan_sb";
			}
//			List<String[]> gridData = fnCallApi.callFunction(functionName, paramValues,1,false);
//
//			String innerSql = null;
//			Object[] args = null;
//			if (actionPlanParams.MODE_VIEW.equals(actionPlanParams.getMode())) {
//				CommonMessage.debugMsg(" if "+actionPlanParams.getDtFromMonth());
//				innerSql = GenTlActionplanmstSql.getAllEmployeeActionPlan(actionPlanParams);
//				
//				if(UIUtils.isValidDate(actionPlanParams.getDtFromMonth())){
//					args =	new Object[3];
//					CommonMessage.debugMsg("in side the IF og det Data..");
//					args[0] = actionPlanParams.getFlid();
//					args[1] = actionPlanParams.getDtFromMonth();
//					args[2] = actionPlanParams.getDtToMonth();
//				}
//				else{
//					args =	new Object[1];
//					CommonMessage.debugMsg("in side the IF og det Data..");
//					args[0] = actionPlanParams.getFlid();
//				}
//	
//			} else {
//				CommonMessage.debugMsg("else");
//				innerSql = GenTlActionplanmstSql.getEmployeeActionPlan();
//				
//				
//				
//				if(UIUtils.isValidDate(actionPlanParams.getDtFromMonth())){
//					args =	new Object[3];
//					CommonMessage.debugMsg("in side the IF og det Data..");
//					args[0] = actionPlanParams.getFlid();
//					args[1] = actionPlanParams.getDtFromMonth();
//					args[2] = actionPlanParams.getDtToMonth();
//				}
//				else{
//					args =	new Object[1];
//					CommonMessage.debugMsg("in side the IF og det Data..");
//					args[0] = actionPlanParams.getEmployeeId();
//				}
//			}
//			String sql = GenTlActionplanmstSql.addPaginationParams(innerSql,actionPlanParams);
//			CommonMessage.debugMsg(" innerSql " + sql);
			// Object [] args = {actionPlanParams.getEmployeeId() };
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			rs = dbActionTemplate.NewdbFunctionCall2(functionName, paramValues);
			return excelUtils.writeToExcel(rs, format, 1,1, 0);

		} 
		catch(Exception e){
			
			e.printStackTrace();
		}
		return  null;
		
		/*finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}*/
	}
	
	
	@Override
	public List<String[]> getActionPlanresponsibilitygrid(
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = "";
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		/*CommonMessage.debugMsg(" Flid 12"+commonFilter.getFlid());
		CommonMessage.debugMsg(" Id 34 "+commonFilter.getLocation().getId());
		CommonMessage.debugMsg(" Others 56 "+commonFilter.getOther());
		CommonMessage.debugMsg(" Id 34 "+commonFilter.getType());
		CommonMessage.debugMsg(" Dao Impl 2 outside :: "+commonFilter.getAbnImp());
		CommonMessage.debugMsg(" commonFilter.getActionKeyId() "+commonFilter.getActionKeyId());
		CommonMessage.debugMsg(" commonFilter.getTrarId() "+commonFilter.getTrarId());*/
		
		if(UIUtils.isValidKeyId(commonFilter.getTrarId())&&UIUtils.isValidKeyId(commonFilter.getKey())){
			    condParms += "TRADEID="+commonFilter.getTrarId()+";";
			    condParms += "ROLEID="+commonFilter.getKey()+";";
			    condParms += "FLID=" + commonFilter.getFlid()+";";
			    if(UIUtils.isValidKeyId(commonFilter.getOther())){
					condParms += "LOCATIONID=" +commonFilter.getLocation().getId()+";";
					condParms += "OTHER=" +commonFilter.getOther()+";";   
				}
		}else if (UIUtils.isValidKeyId(commonFilter.getKey())) {
			    condParms += "ROLEID=" + commonFilter.getKey()+";";
			    condParms += "FLID=" + commonFilter.getFlid()+";";
			if(UIUtils.isValidKeyId(commonFilter.getOther())){
				condParms += "LOCATIONID=" +commonFilter.getLocation().getId()+";";
				condParms += "OTHER=" +commonFilter.getOther()+";";   
			}
		}else if (UIUtils.isValidKeyId(commonFilter.getTrarId())) {  
			    condParms += "TRADEID=" + commonFilter.getTrarId()+";";
			    condParms += "FLID=" + commonFilter.getFlid()+";";
			if(UIUtils.isValidKeyId(commonFilter.getOther())){
				condParms += "LOCATIONID=" +commonFilter.getLocation().getId()+";";
				condParms += "OTHER=" +commonFilter.getOther()+";";   
			}
		   }else if("Production".equals(commonFilter.getType()) ||"Dmt".equals(commonFilter.getType())||"Others".equals(commonFilter.getType())
				 ||"JH".equals(commonFilter.getType()) || UIUtils.isValidKeyId(commonFilter.getType())){
			    condParms +="TYPE="+commonFilter.getType()+";";
			    condParms +="FLID=" + commonFilter.getFlid()+";";
			    if(UIUtils.isValidKeyId(commonFilter.getOther())){
					condParms += "LOCATIONID=" +commonFilter.getLocation().getId()+";";
					condParms += "OTHER=" +commonFilter.getOther()+";";   
				}
			if("Pillar".equals(commonFilter.getType())){
				condParms +="PILLARID=" + commonFilter.getPillarWise()+";";
			}
		}else{
			condParms += "FLID=" + commonFilter.getFlid()+";";
			if(UIUtils.isValidKeyId(commonFilter.getOther())){
				condParms += "LOCATIONID=" +commonFilter.getLocation().getId()+";";
				condParms += "OTHER=" +commonFilter.getOther()+";";   
			}
		}
		
		/*if (UIUtils.isValidKeyId(commonFilter.getOther())&& commonFilter.getOther()!="Others") {
			CommonMessage.debugMsg(" Dao Impl 1 ");
			condParms += "ROLEID=" + commonFilter.getActionKeyId()+";";
			condParms += "LOCATIONID=" +commonFilter.getLocation().getId()+";";
			condParms += "OTHER=" +commonFilter.getOther()+";";
		}
		else if(("Production".equals(commonFilter.getType()) ||"Dmt".equals(commonFilter.getType())||"Others".equals(commonFilter.getType())) && UIUtils.isValidKeyId(commonFilter.getType())){
			condParms +="TYPE="+commonFilter.getType()+";";
			condParms +="FLID=" + commonFilter.getFlid()+";";
		
		}	else if ("Trade".equals(commonFilter.getAp())||"otherTrd".equals(commonFilter.getAp())||UIUtils.isValidKeyId(commonFilter.getAp())){
			CommonMessage.debugMsg(" Dao Impl 11 ");
			condParms += "ROLE=" +commonFilter.getAp()+";";
			condParms += "ROLEID=" + commonFilter.getActionKeyId()+";";
			condParms += "LOCATIONID=" +commonFilter.getLocation().getId()+";";
			condParms += "CELLID=" +commonFilter.getCellId()+";";
			condParms += "TRADEID=" +commonFilter.getTrarId()+";";
			condParms += "FLID=" + commonFilter.getFlid()+";";
		}else if("Pillar".equals(commonFilter.getType()) && UIUtils.isValidKeyId(commonFilter.getType())){
			condParms += "TYPE="+commonFilter.getType()+";";
			condParms += "PILLARID="+commonFilter.getPillarWise()+";";
			condParms += "REFDOCID="+commonFilter.getRefdocid()+";";
			condParms += "FLID=" + commonFilter.getFlid()+";";
		}
		else if (UIUtils.isValidKeyId(commonFilter.getFlid()) && ! UIUtils.isValidKeyId(commonFilter.getAp())) {
			CommonMessage.debugMsg(" Dao Impl 2 "+commonFilter.getAbnImp());
			condParms += "ROLEID=" + commonFilter.getAbnImp()+";";
			condParms += "FLID=" + commonFilter.getFlid()+";";
		}else if (UIUtils.isValidKeyId(commonFilter.getFlid()) && UIUtils.isValidKeyId(commonFilter.getAp())) {
			CommonMessage.debugMsg(" Dao Impl 3 ");
			condParms += "ROLE=" + commonFilter.getAp()+";";
			condParms += "FLID=" + commonFilter.getFlid()+";";
			condParms += "ROLEID=" + commonFilter.getActionKeyId()+";";
			condParms += "LOCATIONID=" +commonFilter.getLocation().getId()+";";
		}
		*/
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		CommonMessage.debugMsg(" Dao Impl 4 ");
		
		//List<String[]> dataList = dbActionTemplate.processFunctionCalls("kzn_fn_addactionplanresponsenew", paramValues);
		List<String[]> dataList = fnCallApi.callFunction("kzn_fn_addactionplanresponsenew_sb", paramValues,3,false);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
			
		}
		
		return dataList; 
	}
	
	@Override
	public List<String[]> getElementId(String loginflid, String loginlevel,
			String loginElementid, String empId) throws Exception {
	    StringBuffer sql =new StringBuffer();
		sql.append("SELECT FNLN_ELEMENTID,FNLN_KEYID,ROLE_LEVEL,ROLE_NAME,ROLE_KEYID FROM  GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST  ");
			sql.append(" WHERE FNLN_KEYID = FRT_FNLN_KEYID  and FRT_ROLE_KEYID = ROLE_KEYID  ");
		if(UIUtils.isValidKeyId(loginflid))
				sql.append(" AND FRT_FNLN_KEYID  = '"+ loginflid +"' ");
			
			sql.append(" AND FRT_EMPM_KEYID = '"+ empId +"'  AND ROLE_LEVEL= '"+ loginlevel +"'");
			CommonMessage.debugMsg("The Sql Data:"+sql.toString());
			Object [] args = {} ;
			List<String []> userDatas = dbActionTemplate.getDataList(sql.toString(),args);
			return userDatas;
	}

	
	
/*
	@Override
	public List<ComboBox> getmompillargroupbasedemployee(String pillarid,
			String type, String flid, ComboFilter empComboFilter, String refid)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		if("JH".equals(type) || "Others".equals(type)){
			
			sql.append(" SELECT DISTINCT FRT_EMPM_KEYID ID, EMPM_NAME AS TEXT FROM (SELECT  DISTINCT FRT_EMPM_KEYID "); 
			sql.append(" from GEN_TL_EMPLOYEEMST,ADM_tl_rolemst,gen_mv_flidhierarchy, ");
			sql.append(" GEN_TL_MOMATTENDANCE,GEN_TL_FNLNROLETEAM,GEN_TL_MOMMST where 1 = 1 "); 
			sql.append(" AND empm_keyid(+) = FRT_EMPM_KEYID AND FRT_ROLE_KEYID = ROLE_KEYID(+) ");
			sql.append(" AND moma_employeeid (+) = empm_keyid AND FLID = FRT_FNLN_KEYID ");
			sql.append(" AND MOMA_MOMS_KEYID = MOMS_KEYID (+) ");
		if("Others".equals(type))
			sql.append(" AND FLID = '"+flid+"' ");
		else
			sql.append(" AND ( INSTR (parentflids || '-' || flid, '"+flid+"' ) >0) ");
		
		sql.append(" AND MOMA_MOMS_KEYID(+)='' UNION ");
		sql.append(" SELECT moma_employeeid FROM GEN_TL_MOMATTENDANCE WHERE MOMA_MOMS_KEYID(+)='"+refid+"' ), ");
		sql.append(" GEN_TL_EMPLOYEEMST WHERE FRT_EMPM_KEYID = EMPM_KEYID ORDER BY ID ");
		 
	}else{
		sql.append(" SELECT DISTINCT FRT_EMPM_KEYID ID, EMPM_NAME AS TEXT FROM (SELECT  DISTINCT FRT_EMPM_KEYID "); 
		sql.append(" from GEN_TL_EMPLOYEEMST,ADM_tl_rolemst,gen_mv_flidhierarchy, ");
		sql.append(" GEN_TL_MOMATTENDANCE,GEN_TL_FNLNROLETEAM,GEN_TL_MOMMST where 1 = 1 "); 
		sql.append(" AND empm_keyid(+) = FRT_EMPM_KEYID AND FRT_ROLE_KEYID = ROLE_KEYID(+) ");
		sql.append(" AND moma_employeeid (+) = empm_keyid AND FLID = FRT_FNLN_KEYID ");
		sql.append(" AND MOMA_MOMS_KEYID = MOMS_KEYID (+) AND ( INSTR (parentflids || '-' || flid, '"+flid+"' ) >0) AND MOMA_MOMS_KEYID(+)=''");
		sql.append(" AND ROLE_KEYID IN ( select mrmp_role_keyid from  GEN_TL_MEETINGTYPE_ROLE_MAP where mrmp_meeting_type = upper('"+type+"')");
		sql.append(" and mrmp_pillar_id='"+pillarid+"') ");
		sql.append(" UNION SELECT moma_employeeid FROM GEN_TL_MOMATTENDANCE WHERE MOMA_MOMS_KEYID(+)='"+refid+"' ), ");
		sql.append(" GEN_TL_EMPLOYEEMST WHERE FRT_EMPM_KEYID = EMPM_KEYID ORDER BY ID ");
	}
		
		ResultSet rs = null; 
		Connection connection = null;
		
		CommonMessage.debugMsg(" sql1234 Checking " + sql);
		try{
			rs = dbActionTemplate.getData(sql.toString()) ;
			connection = rs.getStatement().getConnection();
			
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));				
				comboList.add(compComb);
			}
			return comboList;
		}finally{			
			DBActionTemplate.closeConnection(rs,null,null,null,connection);			
		}	
	
	}*/
	
}
