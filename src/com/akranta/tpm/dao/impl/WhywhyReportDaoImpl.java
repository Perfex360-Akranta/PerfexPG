package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;



import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.WhywhyReportDao;
import com.akranta.tpm.dao.sql.BdmTlWhywhymstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.WhywhyServiceApi;

import com.akranta.tpm.utils.ExcelUtils;
public class WhywhyReportDaoImpl implements WhywhyReportDao {
	
	
	private DBActionTemplate dbActionTemplate; 
	private BdmTlWhywhymstSql bdmTlWhywhymstSql;
	private WhywhyServiceApi whywhyServiceApi;
	FunctionCallApi fnCallApi;
	
	public WhywhyReportDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		bdmTlWhywhymstSql = new BdmTlWhywhymstSql();
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		
	}
	
	public void WhywhyReportDaoImplJwt(String JwtToken) 
	{
		try{
			whywhyServiceApi = new WhywhyServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<String[]> getWhywhyStd(CommonFilter commonFilter) throws Exception
	{
		try
		{

			List<String> paramValues = new ArrayList<String>();
			
				
			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 List<String[]> dataList =  dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYREPORT", paramValues);
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg(paramValues.get(0));
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			 CommonMessage.debugMsg(" fileName " + paramValues);
			return dataList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

	
	public List<String[]> getWhywhyQtyStd(CommonFilter commonFilter) throws Exception
	{
		try
		{

			List<String> paramValues = new ArrayList<String>();
			
				
			 String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("QTM_PC_QUALITY.QTM_FN_YYSTANDARDRPT", paramValues);
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg(paramValues.get(0));
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			 CommonMessage.debugMsg(" fileName " + paramValues);
			return dataList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	@Override
	public List<GenTlAllmoduleimgfile> getyyImage(List<GenTlAllmoduleimgfile> yyImgList) throws NoDataFoundException,Exception {
		CommonMessage.debugMsg("Inside Dao impl EXL Image ");
		//List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
		List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
		for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:yyImgList)
		{
			String condSql = " IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";
						
			String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_FILENAME", "IMFL_REFKEYID", genTlAllmoduleimgfile.getImflRefkeyid(),condSql);
			if( fileName != null )
			{
				if( fileName.lastIndexOf("/") > -1 )
				fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
			
				String fileNamePath = genTlAllmoduleimgfile.getImflBlobimage()+  fileName; 
			
				String imgFileName = genTlAllmoduleimgfile.getImflFilename()+fileName;
				genTlAllmoduleimgfile.setImflFilename(fileNamePath);
			
				CommonMessage.debugMsg(" fileName " + fileNamePath);
				
		
				genTlAllmoduleimgList.add(genTlAllmoduleimgfile);
			}
		}
		
		return genTlAllmoduleimgList;
}

	
	@Override
	public List<String[]> getWhywhy(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getAllStudent(String rowId) throws Exception {

		List<String[]> studentList=null;
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(rowId);
			CommonMessage.debugMsg("roowid"+rowId);
		
		
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getStudent dao impl"+e.getMessage());
		}
		return studentList;
		
	}

	@Override
	public List<String[]> getAllwhywhyStdExl(String rowId) throws Exception {
	
		 
		
		CommonMessage.debugMsg("Inside export daoimpl");
		List<String> paramValues = new ArrayList<String>();
		paramValues.add( rowId);
		
		//paramValues.add("BDKEYID="+ rowId+";");
		
		CommonMessage.debugMsg("param Values :-" +paramValues.get(0));
		List<String[]> whyReport = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYREPORTEXL", paramValues);
		
		CommonMessage.debugMsg("Inside v: " +whyReport);
		return whyReport;
	}

	@Override
	public Workbook getwhywhyStdExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	
	@Override
	public Workbook getAllwhywhyQtyExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet getReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("QTM_PC_QUALITY.QTM_FN_YYSTANDARDRPT", paramValues);
	}
	
	
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYREPORT", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	@Override
	//public List<String[]> getwhywhyRptExl(String rowId,String format) throws Exception {
	public Map<Integer, List<String[]>> getwhywhyRptExl(String rowId,String format) throws Exception {
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(rowId);
			Map<Integer, List<String[]>> whyReport = dbActionTemplate.processDbFunCallMultCursor("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYREPORTEXL", paramValues,2);
			
			return whyReport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}		
		
	
	public Map<Integer, List<String[]>> getwhywhyQtyRptExl(String rowId,String format) throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside export daoimpl Excel");
			List<String> paramValues = new ArrayList<String>();				
		
			paramValues.add(rowId);
			
			CommonMessage.debugMsg("param Values :-" +paramValues.get(0));
		
			
			Map<Integer, List<String[]>> whyReport = dbActionTemplate.processDbFunCallMultCursor("QTM_PC_QUALITY.QTM_FN_WHYWHYREPORTEXL", paramValues,2);
			
			
			for(int i = 0; i <  whyReport.size();i++ )
			{
				List<String[]> lists =  whyReport.get(i);
				CommonMessage.debugMsg("List values......"+whyReport.get(i));
			}
			
			CommonMessage.debugMsg("Inside v: " +whyReport.toString());
			CommonMessage.debugMsg("Inside ParamVal: " +paramValues);
			
			//return ImproprojshtReport;
		//	return whyReport;
			return whyReport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

	

	/*
	 * @Override public List<String[]> getProposed(CommonFilter commonFilter) throws
	 * Exception { // TODO Auto-generated method stub String yyno =
	 * commonFilter.getYyNo(); //StringBuffer sql= new StringBuffer();
	 * 
	 * String sql = bdmTlWhywhymstSql.counterMeasure(commonFilter);
	 * CommonMessage.debugMsg(" In side the Dao Impl  Proposed grid 12");
	 * 
	 * 
	 * List<String> params = null ;
	 * 
	 * List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(sql,
	 * params); return gridData; }
	 */ 
	
	/*
	 * @Override public List<String[]> getProposed(CommonFilter commonFilter) throws
	 * Exception { String yyno = commonFilter.getYyNo();
	 * 
	 * // Generate the full SQL String sql =
	 bdmTlWhywhymstSql.counterMeasure(commonFilter);
	 * CommonMessage.debugMsg("Inside DAO Impl Proposed grid 12: " + sql);
	 * 
	 * // No parameters used in this query right now List<String> params = null;
	 * 
	 * // Fetch data with column headers (everything is String[]) List<String[]>
	 * gridData = dbActionTemplate.getDataListWithColHeader(sql, params);
	 * 
	 * return gridData; }
	 */
	
	/*
	 * @Override public List<String[]> getProposed(CommonFilter commonFilter) throws
	 * Exception { String __yyno__ = commonFilter.getYyNo();
	 * 
	 * // Get filter parameters List<String> paramValues =
	 * getFilterParamValues(commonFilter);
	 * 
	 * CommonMessage.debugMsg("Inside DAO Impl Proposed grid - paramValues: " +
	 * paramValues);
	 * 
	 * // Call the PostgreSQL function List<String[]> dataList =
	 * fnCallApi.callFunction("bdm_fn_countermeasure_sb", paramValues, 3, false);
	 * 
	 * // Handle total record count if view click if (commonFilter.getViewClick() ==
	 * 'Y') { String totalCnt = paramValues.get(0);
	 * CommonMessage.debugMsg("count: " + totalCnt); boolean isInteger =
	 * Pattern.matches("^\\d*$", totalCnt); if (isInteger) {
	 * commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt)); } }
	 * 
	 * CommonMessage.debugMsg("commonFilter.setTotalRecordCnt: " +
	 * commonFilter.getTotalRecordCnt());
	 * 
	 * return dataList; }
	 */
	
	
	@Override
	public List<String[]> getProposed(CommonFilter commonFilter) throws Exception {
	    String yyno = commonFilter.getYyNo();
	    
	    List<String> paramValues = new ArrayList<String>();
	    String condParams = "";
	    String commonParams = "";
	    
	    // Build condition parameters
	    condParams = yyno;
	    
	    // Build common parameters (filters, pagination)
	    if (commonFilter.getGridFilter() != null) {
	        commonParams += "GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) + ";";
	    }
	    
	    if (commonFilter.getFromRow() != null && commonFilter.getToRow() != null) {
	        commonParams += "FROMTOROW=" + commonFilter.getFromRow() + " AND " + commonFilter.getToRow() + ";";
	    }
	    commonParams += "ISGETCOL=" +commonFilter.getIsGetCol()+ ";";
	    
	    // Add parameters in order
	    paramValues.add(condParams);   // vcondparam
	    paramValues.add(commonParams);  // vcommonparam
	    
	    CommonMessage.debugMsg("Calling bdm_fn_countermeasure_sb with yyno: " + yyno);
	    CommonMessage.debugMsg("Common params: " + commonParams);
	    
	    // Call function: 2 OUT params (totalcnt, cur), include headers = true
	    List<String[]> dataList = fnCallApi.callFunctionWithHeaders("bdm_fn_countermeasure_sb", paramValues, 2, true );
	    
	    // Get total count from first OUT parameter
	    if (paramValues.size() > 0) {
	        String totalCnt = paramValues.get(0);
	        CommonMessage.debugMsg("totalCnt: " + totalCnt);
	        if (Pattern.matches("^\\d*$", totalCnt)) {
	            commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
	        }
	    }
	    
	    CommonMessage.debugMsg("Total record count: " + commonFilter.getTotalRecordCnt());
	    
	    return dataList;
	}
	
	@Override
	public List<String[]> getMainGrid() throws Exception {     //
		
			String sqls = "";
			List<String[]> getMain = null; 
			BdmTlWhywhymstSql  bdmTlWhywhymstSql = new BdmTlWhywhymstSql();
			
			String sql =BdmTlWhywhymstSql.getMainGrid();
				CommonMessage.debugMsg(" sql "+sql);
			
				return dbActionTemplate.getDataList(sql);
	}
	@Override
	public List<String[]> getYYEffectiveness(CommonFilter commonFilter) throws Exception {
		
		String sql="";
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		// List<String[]> dataList = dbActionTemplate.processFunctionCalls("BDM_FN_WHYWHYEFFECTIVENESS", paramValues);
	//	dataList = null;
		
		CommonMessage.debugMsg("paramvalues  " + paramValues);
		List<String[]>  dataList = fnCallApi.callFunction("BDM_FN_WHYWHYEFFECTIVENESS_SB", paramValues,3,true );
		
		
		 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("count:"+paramValues.get(0));
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			 CommonMessage.debugMsg(" commonFilter.setTotalRecordCnt " + commonFilter.getTotalRecordCnt());
		 return dataList;
	}

	@Override
	public List<String[]> getanalysis() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getanalysis(String masdetkeyid) throws Exception {      //grid datas
		
		
		/*----------------------------------MANO---------------------------------*/
		StringBuffer sql = new StringBuffer();
		sql.append("select 'txtWwdtKeyid','txtWwdtWhy','Answer','Delete'");  // removed dual
		if(UIUtils.isValidKeyId(masdetkeyid)){
		    sql.append(" UNION ALL ");
		    sql.append(" SELECT * FROM ( " );
		    sql.append(" SELECT WWDT_KEYID, WWDT_WHY, WWDT_ANSWER,'' " );
		    sql.append(" FROM BDM_TL_WHYWHYDTL WHERE WWDT_WWMS_KEYID = '"+masdetkeyid+"'" );
		    sql.append(" ORDER BY WWDT_KEYID) t "); // added alias
		}
		CommonMessage.debugMsg("sql....."+sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		CommonMessage.debugMsg("Grid value::::::::"+ gridData.size());
		return gridData;

		
	}

	@Override
	public List<String[]> FillControlData(String cellId,String keyid, String formName) throws Exception {
		// TODO Auto-generated method stub
		String sql = BdmTlWhywhymstSql.selectData(cellId,keyid,formName);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}

	@Override
	public Map<Integer, List<String[]>> getwhywhyExlView(String rowId,String format) throws Exception {
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(rowId);
			Map<Integer, List<String[]>> whyReport = dbActionTemplate.processDbFunCallMultCursor("BDM_FN_WHYWHYREPORTEXLVIEW", paramValues,2);
			
			return whyReport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

	@Override
	public Workbook WhyWhyEffectivenessExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getWhyWhyEffectivenessReport(commonFilter);
			CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format,3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  

	}	
	
	private ResultSet getWhyWhyEffectivenessReport(CommonFilter commonFilter) throws Exception 
	 {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.NewdbFunctionCall2("BDM_FN_WHYWHYEFFECTIVENESS_SB", paramValues);

	 }

		/*
		 * @Override public List<String[]> getyyDoneby(String masterkeyid) throws
		 * Exception { // TODO Auto-generated method stub String sql = "";
		 * 
		 * StringBuffer sf = new StringBuffer(); sf.
		 * append(" select '',WWDB_KEYID,WWDB_WWMS_KEYID,EMPM_NAME || ' - ' || EMPM_CODE as \"Employee\",'' as \"Delete\"  "
		 * ); sf.append(" from BDM_TL_YYDONEBYMST, GEN_TL_EMPLOYEEMST ");
		 * sf.append(" where WWDB_WWMS_KEYID ='"+masterkeyid+"'  ");
		 * sf.append(" and WWDB_EMPM_KEYID = EMPM_KEYID ");
		 * 
		 * sql = sf.toString();
		 * 
		 * CommonMessage.debugMsg("getyyDoneby....."+sql); List<String[]> dataList =
		 * dbActionTemplate.getDataListWithColHeader(sql,null); return dataList ; }
		 * public List<String[]> getProbAttby(String masterkeyid) throws Exception {
		 * String sql = "";
		 * 
		 * StringBuffer sf = new StringBuffer(); sf.
		 * append(" select '',WWPA_KEYID,WWPA_WWMS_KEYID,EMPM_NAME || ' - ' || EMPM_CODE as \"Employee\",'' as \"Delete\"  "
		 * ); sf.append(" from BDM_TL_YYPROBLEMATTBYMST, GEN_TL_EMPLOYEEMST ");
		 * sf.append(" where WWPA_WWMS_KEYID ='"+masterkeyid+"'  ");
		 * sf.append(" and WWPA_EMPM_KEYID = EMPM_KEYID ");
		 * 
		 * sql = sf.toString();
		 * 
		 * CommonMessage.debugMsg("getProbAttby....."+sql); List<String[]> dataList =
		 * dbActionTemplate.getDataListWithColHeader(sql,null);
		 * 
		 * return dataList ; }
		 */ 
	@Override
	public List<String[]> getyyDoneby(String masterkeyid) throws Exception {
	    String sql = "";

	    StringBuffer sf = new StringBuffer();
	    sf.append(" SELECT '', ")
	      .append("db.WWDB_KEYID, ")
	      .append("db.WWDB_WWMS_KEYID, ")
	      .append("COALESCE(emp.EMPM_NAME, '') || ' - ' || COALESCE(emp.EMPM_CODE, '') AS \"Employee\", ")
	      .append("'' AS \"Delete\" ")
	      .append(" FROM BDM_TL_YYDONEBYMST db ")
	      .append(" JOIN GEN_TL_EMPLOYEEMST emp ON db.WWDB_EMPM_KEYID = emp.EMPM_KEYID ")
	      .append(" WHERE db.WWDB_WWMS_KEYID = '" + masterkeyid + "' ");

	    sql = sf.toString();
CommonMessage.debugMsg(sql+"query done");
	    CommonMessage.debugMsg("getyyDoneby....." + sql);
	    List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql, null);
	    return dataList;
	}

	public List<String[]> getProbAttby(String masterkeyid) throws Exception {
	    String sql = "";

	    StringBuffer sf = new StringBuffer();
	    sf.append(" SELECT '', ")
	      .append("pa.WWPA_KEYID, ")
	      .append("pa.WWPA_WWMS_KEYID, ")
	      .append("COALESCE(emp.EMPM_NAME, '') || ' - ' || COALESCE(emp.EMPM_CODE, '') AS \"Employee\", ")
	      .append("'' AS \"Delete\" ")
	      .append(" FROM BDM_TL_YYPROBLEMATTBYMST pa ")
	      .append(" JOIN GEN_TL_EMPLOYEEMST emp ON pa.WWPA_EMPM_KEYID = emp.EMPM_KEYID ")
	      .append(" WHERE pa.WWPA_WWMS_KEYID = '" + masterkeyid + "' ");

	    sql = sf.toString();

	    CommonMessage.debugMsg("getProbAttby....." + sql);
	    List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql, null);
CommonMessage.debugMsg(dataList+"query working");
	    return dataList;
	   
	}

}

