package com.akranta.tpm.dao.impl;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GenTlHolidaymstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlHolidaymstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlHolidaymstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.GenTlHolidaymst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.GenTlHolidaymstServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class GenTlHolidaymstDaoImpl implements GenTlHolidaymstDao {


	private DBActionTemplate dbActionTemplate; 
	private GenTlHolidaymstServiceApi genTlHolidaymstServiceApi;
	FunctionCallApi fnCallApi;


	public void GenTlHolidaymstDaoImplJwt(String JwtToken) {
		try {
			genTlHolidaymstServiceApi = new GenTlHolidaymstServiceApi(JwtToken);
			fnCallApi = new FunctionCallApi(JwtToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GenTlHolidaymstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlHolidaymst create(GenTlHolidaymst genTlHolidaymst,GenTlHolidaymstBean genTlHolidaymstBean) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); 
		GenTlHolidaymstSql genTlHolidaymstSql = new GenTlHolidaymstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		CommonFunctions.debugMsg("Inside Save in DaoImpl");
		String newKeyId = dbActionTemplate.getSequenceNumber(GenTlHolidaymstSql.TBL_GEN_TL_HOLIDAYMST,9,"HOL","","");
		newKeyId = ensureUniqueHolidayKeyId(newKeyId, "HOL");
		genTlHolidaymst.setHolmKeyid(newKeyId); // set the sequnce number
		if(genTlHolidaymst.getHolmHolidayflag().equals("H")){
			sqls.add(GenTlHolidaymstSql.getInsertSql(genTlHolidaymstSql.getHolmDbFields(), genTlHolidaymst.getSaveArray()));
			CommonFunctions.debugMsg(sqls);
			dbActionTemplate.executeStatements(sqls);
		}
		else if(genTlHolidaymst.getHolmHolidayflag().equals("W")){
			SaveWeeklyVal(genTlHolidaymst,genTlHolidaymstBean);
		}
		
		return genTlHolidaymst;
	}//---------gopi-----------------------
	private String ensureUniqueHolidayKeyId(String keyId, String prefix) throws Exception {
		int maxAttempts = 20;
		int attempt = 0;

		while (attempt < maxAttempts) {
			boolean exists = dbActionTemplate.checkDuplicateValue(
					GenTlHolidaymstSql.TBL_GEN_TL_HOLIDAYMST,
					"HOLM_KEYID",
					keyId,
					null);

			if (!exists) {
				return keyId;
			}

			CommonFunctions.debugMsg("Duplicate keyid found: " + keyId + " , regenerating...");

			String numericPart = keyId.substring(prefix.length());
			long nextNum = Long.parseLong(numericPart) + 1;

			String format = "%0" + numericPart.length() + "d";
			keyId = prefix + String.format(format, nextNum);

			attempt++;
		}

		throw new Exception("Unable to generate unique Holiday Key ID after " + maxAttempts + " attempts");
	}
	//-----------------------gopi-----------------------
	public void SaveWeeklyVal(GenTlHolidaymst genTlHolidaymst,GenTlHolidaymstBean genTlHolidaymstBean) throws Exception {
		
		CommonFunctions.debugMsg("Inside save procedure");
		CommonFunctions.debugMsg("day..."+genTlHolidaymstBean.getBeanDay()+",year..."+genTlHolidaymstBean.getBeanYear()+",Factory..."+genTlHolidaymst.getHolmFactory());
		List<String> ParamValues = new ArrayList<String>();
		
		ParamValues.add(genTlHolidaymstBean.getBeanYear());
		ParamValues.add(genTlHolidaymst.getHolmFactory());
		ParamValues.add(genTlHolidaymstBean.getBeanDay());
		
		System.out.println("inParamValues  :"+ParamValues);
		 Object [] outParams = new Object[ 1 ];
		 System.out.println("outParams   :"+outParams);
		 dbActionTemplate.processPLSQLProcedures("GEN_PC_COMMONFUNCTIONS.GEN_PR_HOLIDAYWKLYINS", ParamValues,outParams);
		 System.out.println("outParamsAfter   :"+outParams.toString());
		
	}

	
	public GenTlHolidaymst update(GenTlHolidaymst genTlHolidaymst,GenTlHolidaymstBean genTlHolidaymstBean)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlHolidaymstSql genTlHolidaymstSql = new GenTlHolidaymstSql();
		if(genTlHolidaymst.getHolmHolidayflag().equals("H")){
		try {
			sqls.add(GenTlHolidaymstSql.getUpdateSql(genTlHolidaymstSql.getHolmDbFields(), genTlHolidaymst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
		}
		
		catch (Exception e) {
		
			throw new Exception(e.getMessage());
		}
		}
		else if(genTlHolidaymst.getHolmHolidayflag().equals("W")){
			SaveWeeklyVal(genTlHolidaymst,genTlHolidaymstBean);
		}
		return genTlHolidaymst;
	}
	
public GenTlHolidaymst delete(GenTlHolidaymst genTlHolidaymst,GenTlHolidaymstBean genTlHolidaymstBean)
			throws Exception {

		
		
		try {
			
			if(genTlHolidaymst.getHolmHolidayflag().equals("H")){
			
				String sqls = GenTlHolidaymstSql.deleteNew(genTlHolidaymst.getHolmKeyid());
				
				dbActionTemplate.executeStatement(sqls);
			}
			else if(genTlHolidaymst.getHolmHolidayflag().equals("W"))
			{
				deleteWeeklyVal(genTlHolidaymst,genTlHolidaymstBean);
			}
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlHolidaymst;
	}
public void deleteWeeklyVal(GenTlHolidaymst genTlHolidaymst,GenTlHolidaymstBean genTlHolidaymstBean) throws Exception {

/*	String year = genTlHolidaymst.getHolmDate();
	year = year.substring(7, 11);*/
	
	String year = genTlHolidaymstBean.getBeanYear();
	String flag = genTlHolidaymst.getHolmHolidayflag();
	String keyId = genTlHolidaymst.getHolmFactory();
	//String desc = genTlHolidaymst.getHolmDescription();
	String desc = genTlHolidaymstBean.getBeanDay();
	CommonFunctions.debugMsg("year.."+year+"flag.."+flag+"keyId.."+keyId+"desc.."+desc);
	String sql  = GenTlHolidaymstSql.getDeleteHolidaymstSql(year,flag,keyId,desc);
	CommonFunctions.debugMsg(sql);
	
	 dbActionTemplate.executeStatement(sql);
	//CommonFunctions.debugMsg("data....."+Sqls);
	
}
	
	public GenTlHolidaymst select(String keyid) throws Exception {
		
		GenTlHolidaymst genTlHolidaymst = new GenTlHolidaymst();
		String sql = GenTlHolidaymstSql.getRecalHolidaymstSql();
		CommonFunctions.debugMsg(sql);
		Object args [] = new Object [] { keyid };
		genTlHolidaymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		CommonFunctions.debugMsg("Data In DaoImpl ......"+genTlHolidaymst.getHolmCreatedby());
		
		return genTlHolidaymst;
	}

	



	@Override
	public GenTlHolidaymst delete(GenTlHolidaymst genTlHolidaymst)
			throws Exception {
		
		return null;
	}

	@Override
	public List<String[]> getAllHoliday() throws Exception {
		
		String sql = GenTlHolidaymstSql.getAllHolidaymstSql();
		CommonFunctions.debugMsg(sql);
		List<String[]> Sqls = dbActionTemplate.getDataList(sql);
		CommonFunctions.debugMsg(Sqls);
		return Sqls;
	}

	@Override
	public List<String[]> selectAll(String keyId,String flag,String year) throws Exception {
		String sql = null;
		CommonFunctions.debugMsg("flag to identify..."+flag);
		sql = GenTlHolidaymstSql.getHolidaymstSqlCnt();
		CommonFunctions.debugMsg(sql);
		Object args [] = new Object [] { year,flag,keyId };
		List<String[]> Sqls = dbActionTemplate.getDataList(sql, args);
		CommonFunctions.debugMsg(Sqls);
		return Sqls;
		
		
	}

	@Override
	public GenTlHolidaymst flagSelect(String flag) throws Exception {
		GenTlHolidaymst genTlHolidaymst = new GenTlHolidaymst();
		String sql = GenTlHolidaymstSql.getFlagHolidaymstSql();
		CommonFunctions.debugMsg(sql);
		Object args [] = new Object [] { flag };
		
		genTlHolidaymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		CommonFunctions.debugMsg("Data In DaoImpl ......"+genTlHolidaymst.getHolmCreatedby());
		
		return genTlHolidaymst;
	}

	@Override
	public List<String[]> getHoliday() throws Exception {
		String sql = GenTlHolidaymstSql.getHolidaySql();
		List<String[]> Sqls = dbActionTemplate.getDataList(sql);
		CommonFunctions.debugMsg(Sqls);
		return Sqls;
	}

	@Override
	public Workbook HolidayExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getImprovementVsCompleteResultSet(commonFilter);
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

	private ResultSet getImprovementVsCompleteResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("KZN_PC_KAIZEN.JHN_FN_KZNIDENCOMP", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	@Override
	public List<String[]> selectAll(String flag, String year,GridParams gridParams) throws Exception {
		String sql = null;
		CommonFunctions.debugMsg("flag to identify..."+flag);
		
		sql = GenTlHolidaymstSql.getHolidayYearFlagSql(gridParams);
		CommonFunctions.debugMsg(sql);
		Object args [] = new Object [] { year,flag };
		List<String[]> Sqls = dbActionTemplate.getDataList(sql, args);
		CommonFunctions.debugMsg(Sqls);
		return Sqls;
	}/*
	
	@Override
	public List<String[]> selectAll(String flag, String year, GridParams gridParams) throws Exception {
	    String sql = null;
	    CommonFunctions.debugMsg("flag to identify..."+flag);
	    sql = GenTlHolidaymstSql.getHolidayYearFlagSql(gridParams);
	    CommonFunctions.debugMsg(sql);

	    List<String> paramValues = new ArrayList<String>();
	    String condParms = "";

	    if (UIUtils.isValidKeyId(year))
	        condParms += "YEAR=" + year + ";";
	    if (UIUtils.isValidKeyId(flag))
	        condParms += "HOLIDAYFLAG=" + flag + ";";

	    String commonParams = "ISTOTALCNT=Y;FROMTOROW=" + gridParams.getFromRow()
	            + " AND " + gridParams.getToRow() + ";GRIDFILTER=;";

	    paramValues.add(condParms);
	    paramValues.add(commonParams);

	    List<String[]> Sqls = fnCallApi.callFunction("GEN_FN_HOLIDAYMST_SB", paramValues, 3, true);
	    CommonFunctions.debugMsg(Sqls);
	    return Sqls;
	}*/

	/*@Override
	public List<String[]> getAllHolidayCount(String flag, String year)	throws Exception {
		String sql = null;
		CommonFunctions.debugMsg("flag to identify..."+flag);
		
		sql = GenTlHolidaymstSql.getHolidayYearFlagSqlCount();
		CommonFunctions.debugMsg(sql);
		Object args [] = new Object [] { year,flag };
		List<String[]> Sqls = dbActionTemplate.getDataList(sql, args);
		CommonFunctions.debugMsg(Sqls);
		return Sqls;
	}*/
	
	//---------Added by gopi-----------------
	@Override
	public List<String[]> getAllHolidayCount(String flag, String year, String keyId) throws Exception {
	    CommonFunctions.debugMsg("flag to identify..."+flag+", year..."+year+", keyId..."+keyId);

	    List<String> paramValues = new ArrayList<String>();
	    String condParms = "";

	    if (UIUtils.isValidKeyId(year))
	        condParms += "YEAR=" + year + ";";
	    if (UIUtils.isValidKeyId(flag))
	        condParms += "HOLIDAYFLAG=" + flag + ";";
	    if (UIUtils.isValidKeyId(keyId))
	        condParms += "FACTORYID=" + keyId + ";";

	    String commonParams = "ISTOTALCNT=Y;FROMTOROW=;GRIDFILTER=;";

	    paramValues.add(condParms);
	    paramValues.add(commonParams);

	    List<String[]> Sqls = fnCallApi.callFunction("GEN_FN_HOLIDAYMST_SB", paramValues, 1, false);
	    CommonFunctions.debugMsg(Sqls);
	    return Sqls;
	}

	@Override
	public List<String[]> selectHolidayFactory(String keyId, String flag,String year, GridParams gridParams) throws Exception {
		String sql = null;
		CommonFunctions.debugMsg("flag to identify..."+flag);
		sql = GenTlHolidaymstSql.getHolidaymstSql(gridParams);
		CommonFunctions.debugMsg(sql);
		Object args [] = new Object [] { year,flag,keyId };
		List<String[]> Sqls = dbActionTemplate.getDataList(sql, args);
		CommonFunctions.debugMsg(Sqls);
		return Sqls;
	}
	
	/*
	
	@Override
	public List<String[]> selectHolidayFactory(String keyId, String flag, String year, GridParams gridParams) throws Exception {
		String sql = null;
		CommonFunctions.debugMsg("flag to identify..."+flag);
		sql = GenTlHolidaymstSql.getHolidaymstSql(gridParams);
		CommonFunctions.debugMsg(sql);

		List<String> paramValues = new ArrayList<String>();
		String condParms = "";

		if (UIUtils.isValidKeyId(year))
			condParms += "YEAR=" + year + ";";
		if (UIUtils.isValidKeyId(flag))
			condParms += "HOLIDAYFLAG=" + flag + ";";
		if (UIUtils.isValidKeyId(keyId))
			condParms += "FACTORYID=" + keyId + ";";

		String commonParams = "ISTOTALCNT=Y;FROMTOROW=" + gridParams.getFromRow()
				+ " AND " + gridParams.getToRow() + ";GRIDFILTER=;";

		paramValues.add(condParms);
		paramValues.add(commonParams);

		List<String[]> Sqls = fnCallApi.callFunction("GEN_FN_HOLIDAYMST_SB", paramValues, 3, true);
		CommonFunctions.debugMsg(Sqls);
		return Sqls;
	}*/

	@Override
	public Workbook HolidayFactoryExportExcel(String keyId, String flag,String year, GridParams gridParams, JSONObject colModel,String format) throws Exception {
		 ResultSet rs = null;
		 
		   try
		   {		
				rs =   getHolidayMstResultSet(keyId,flag,year,gridParams);
				ExcelUtils excelUtils = new ExcelUtils(colModel);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(254,0,0)); //red font
				condFormat.setFontName("Wingdings");
				condFormat.setFontHeightPoint((short)14);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setFromCol(0);
				condFormat.setToCol(-1);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue( (char)252+""); //Tick
				condFormat.setIdentfier("tick");
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
				return excelUtils.writeToExcel(rs,format, 0,0,0 );
			
		   }finally{
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   } 		 
	}

	private ResultSet getHolidayMstResultSet(String keyId, String flag,	String year, GridParams gridParams) throws Exception {
		try
		{
			String sql = "";
			List<String> paramValues = new ArrayList<String>();
			paramValues.add("");
			if(UIUtils.isValidKeyId(keyId))
				sql = GenTlHolidaymstSql.getHolidaymstSqlWithFact(keyId,flag,year,gridParams);
			else
				sql = GenTlHolidaymstSql.getHolidaymstSqlExcel(flag,year,gridParams);
			CommonFunctions.debugMsg("sql Excel..."+sql);
			return  dbActionTemplate.getData(sql);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}	
	}

/*	public GenTlHolidaymst delete(GenTlHolidaymst newgenTlHolidaymst,GenTlHolidaymstBean genTlHolidaymstBean) throws Exception {
		
		List<String> sqls = new ArrayList<String>();
		GenTlHolidaymstSql genTlHolidaymstdelete = new GenTlHolidaymstSql();
		try {
			
			sqls.add(GenTlHolidaymst.getDeleteSql(genTlHolidaymstdelete.getHolmDbFields(), newgenTlHolidaymst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return newgenTlHolidaymst;
		
	}*/
	
}

