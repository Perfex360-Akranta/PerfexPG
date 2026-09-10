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
import com.akranta.tpm.dao.BAL_WhywhyReportDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlWhywhymstSql;
import com.akranta.tpm.dao.sql.BAL_BreakDownSql;

import com.akranta.tpm.dao.sql.BAL_BdmTlWhywhydtlSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlCriticalprocessSql;
import com.akranta.tpm.dao.sql.OplTlMstSql;
//import com.akranta.tpm.dao.sql.QtmTlSopmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;



import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;
public class BAL_WhywhyReportDaoImpl implements BAL_WhywhyReportDao {
	
	
	private DBActionTemplate dbActionTemplate; 
	private BAL_BdmTlWhywhymstSql bdmTlWhywhymstSql;
	public BAL_WhywhyReportDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		bdmTlWhywhymstSql = new BAL_BdmTlWhywhymstSql();
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		
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
				CommonFunctions.debugMsg(paramValues.get(0));
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			 System.out.println(" fileName " + paramValues);
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
				CommonFunctions.debugMsg(paramValues.get(0));
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			 System.out.println(" fileName " + paramValues);
			return dataList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	@Override
	public List<GenTlAllmoduleimgfile> getyyImage(List<GenTlAllmoduleimgfile> yyImgList) throws NoDataFoundException,Exception {
		System.out.println("Inside Dao impl EXL Image ");
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
			
				System.out.println(" fileName " + fileNamePath);
				
		
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
			System.out.println("roowid"+rowId);
		
		
		}
	
		catch(Exception e)
		{
			System.out.println("Exception in getStudent dao impl"+e.getMessage());
		}
		return studentList;
		
	}

	@Override
	public List<String[]> getAllwhywhyStdExl(String rowId) throws Exception {
	
		 
		
		CommonFunctions.debugMsg("Inside export daoimpl");
		List<String> paramValues = new ArrayList<String>();
		paramValues.add( rowId);
		
		//paramValues.add("BDKEYID="+ rowId+";");
		
		CommonFunctions.debugMsg("param Values :-" +paramValues.get(0));
		List<String[]> whyReport = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYREPORTEXL", paramValues);
		
		CommonFunctions.debugMsg("Inside v: " +whyReport);
		return whyReport;
	}

	@Override
	public Workbook getwhywhyStdExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
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
			CommonFunctions.debugMsg("Inside export daoimpl Excel");
			List<String> paramValues = new ArrayList<String>();				
		
			paramValues.add(rowId);
			
			CommonFunctions.debugMsg("param Values :-" +paramValues.get(0));
		
			
			Map<Integer, List<String[]>> whyReport = dbActionTemplate.processDbFunCallMultCursor("QTM_PC_QUALITY.QTM_FN_WHYWHYREPORTEXL", paramValues,2);
			
			
			for(int i = 0; i <  whyReport.size();i++ )
			{
				List<String[]> lists =  whyReport.get(i);
				CommonFunctions.debugMsg("List values......"+whyReport.get(i));
			}
			
			CommonFunctions.debugMsg("Inside v: " +whyReport.toString());
			CommonFunctions.debugMsg("Inside ParamVal: " +paramValues);
			
			//return ImproprojshtReport;
		//	return whyReport;
			return whyReport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

	

	@Override
	public List<String[]> getProposed(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		String yyno = commonFilter.getYyNo();
		//StringBuffer sql= new StringBuffer();
	
		String sql = bdmTlWhywhymstSql.counterMeasure(commonFilter);
		
		/*sql.append(" select 'Proposed preventive counter measures given below','Proposed preventive counter measures given below', ");
		sql.append(" 'Proposed preventive counter measures given below','Responsibility','Date','Status',0 as dataorder  from dual ");
		sql.append(" union  ");
		sql.append(" SELECT DISTINCT CNAME,max(KEYID),max(AA),max(EMPNAME),max(CDATE),max(BB),1 as dataorder FROM( ");
		sql.append("  SELECT 'Change in Workpractice / Training / OPL' AS CNAME,OPLM_KEYID AS KEYID,'' AS AA,EMPM_NAME AS EMPNAME,TO_CHAR(OPLM_PREPAREDDATE) AS CDATE,'' AS BB ");
		sql.append(" FROM OPL_TL_MST,GEN_TL_EMPLOYEEMST WHERE OPLM_PREPAREDID = EMPM_KEYID AND OPLM_REFDOCTYPE = 'YY'  ");
		//if(UIUtils.isValidKeyId(yyno))
			sql.append("AND OPLM_REFDOCNO = '"+yyno+"' ");
		sql.append(" UNION   SELECT 'Change in Workpractice / Training / OPL' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB  FROM DUAL ");
		sql.append(" UNION   SELECT 'Routine Activity (CLTI)' AS CNAME,CLIS_KEYID AS KEYID,'' AS AA,EMPM_NAME AS EMPNAME,TO_CHAR(CLIS_EFFECTIVEDATE) AS CDATE,'' AS BB "); 
		sql.append(" FROM CLI_TL_STANDARDS,GEN_TL_EMPLOYEEMST WHERE CLIS_RESPONSIBILITYID = EMPM_KEYID AND CLIS_REFDOCTYPE = 'YY'  ");
//		if(UIUtils.isValidKeyId(yyno))
			sql.append("AND CLIS_REFDOCNO = '"+yyno+"' ");
		sql.append(" UNION   SELECT 'Routine Activity (CLTI)' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB  FROM DUAL ");
		sql.append(" UNION   SELECT 'Condition Monitoring' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB  FROM DUAL ");
		sql.append(" UNION   SELECT 'Condition Monitoring' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB  FROM DUAL  ");
		sql.append(" UNION   SELECT 'Preventive Maintenance',PMSD_KEYID,'',EMPM_NAME,TO_CHAR(PMSD_EFFECTIVEDATE),'' ");
		sql.append(" FROM PLM_TL_STANDARDS,GEN_TL_EMPLOYEEMST WHERE PMSD_PREPAREDBYID = EMPM_KEYID AND PMSD_REFDOCTYPE = 'YY' ");
		//if(UIUtils.isValidKeyId(yyno))
			sql.append("AND PMSD_REFDOCNO = '"+yyno+"' ");
		sql.append(" UNION   SELECT 'Preventive Maintenance' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB  FROM DUAL ");
		sql.append(" UNION   SELECT 'Preventive Maintenance' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB  FROM DUAL ");
		sql.append(" UNION   SELECT 'Modification (Kaizen)' AS CNAME,KZNM_KEYID AS KEYID,'' AS AA,EMPM_NAME AS EMPNAME,TO_CHAR(KZNM_DATE) AS CDATE,'' AS BB "); 
		sql.append(" FROM KZN_TL_MST,GEN_TL_EMPLOYEEMST WHERE KZNM_PREPAREDID = EMPM_KEYID AND KZNM_REFDOCTYPE = 'YY'  ");
		//if(UIUtils.isValidKeyId(yyno))
			sql.append("AND KZNM_REFDOCNO = '"+yyno+"' ");
		sql.append(" UNION   SELECT 'Modification (Kaizen)' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB  FROM DUAL ");
		sql.append(" UNION   SELECT 'SOP','','','','',''  FROM DUAL ) group by cname order by dataorder");
		System.out.println("sql....."+sql);
		*/
		
		 
		List<String> params = null ;
		
		List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(sql, params);
		return gridData;
	}
	@Override
	public List<String[]> getMainGrid() throws Exception {     //
		
			String sqls = "";
			List<String[]> getMain = null; 
			BAL_BdmTlWhywhymstSql  bdmTlWhywhymstSql = new BAL_BdmTlWhywhymstSql();
			
			String sql =BAL_BdmTlWhywhymstSql.getMainGrid();
				System.out.println(" sql "+sql);
			
				return dbActionTemplate.getDataList(sql);
	}
	@Override
	public List<String[]> getYYEffectiveness(CommonFilter commonFilter) throws Exception {
		String sql="";
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYEFFECTIVENESS", paramValues);

	}

	@Override
	public List<String[]> getanalysis() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getanalysis(String masdetkeyid) throws Exception {      //grid datas
		
		/*{     //
			
			String sqls = "";
			List<String[]> getMain = null; 
			BdmTlWhywhymstSql  bdmTlWhywhymstSql = new BdmTlWhywhymstSql();
			
			String sql =BdmTlWhywhymstSql.getanalysis();
				System.out.println(" sql "+sql);
			
				return dbActionTemplate.getDataList(sql);
	}*/
		
		/*
		 * StringBuffer sql= new StringBuffer();
		 * sql.append("select 'txtWwdtKeyid','txtWwdtWhy','Answer','Delete' from dual");
		 * if(UIUtils.isValidKeyId(masdetkeyid)){ sql.append(" UNION ALL ");
		 * sql.append(" SELECT * FROM ( " ); sql.
		 * append(" SELECT WWDT_KEYID,WWDT_WHY, WWDT_ANSWER,'' FROM BDM_TL_WHYWHYDTL WHERE WWDT_WWMS_KEYID = '"
		 * +masdetkeyid+"'" ); sql.append(" ORDER BY WWDT_KEYID) "); }
		 * System.out.println("sql....."+sql); List<String[]> gridData =
		 * dbActionTemplate.getDataList(sql.toString());
		 * System.out.println("Grid value::::::::"+ gridData.size()); return gridData;
		 * 
		 * }
		 */
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
		String sql = BAL_BdmTlWhywhymstSql.selectData(cellId,keyid,formName);
		System.out.println("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}

	@Override
	public Map<Integer, List<String[]>> getwhywhyExlView(String rowId,String format) throws Exception {
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(rowId);
			Map<Integer, List<String[]>> whyReport = dbActionTemplate.processDbFunCallMultCursor("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYREPORTEXLVIEW", paramValues,2);
			
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
			System.out.println("rs value::::::::"+ rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format,2,0,0 );
			
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
		return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYEFFECTIVENESS", paramValues);

	 }

	@Override
	public List<String[]> getyyDoneby(String masterkeyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "";
		
		StringBuffer sf = new StringBuffer();
		sf.append(" select '',WWDB_KEYID,WWDB_WWMS_KEYID,EMPM_NAME || ' - ' || EMPM_CODE as \"Employee\",'' as \"Delete\"  ");
		sf.append(" from BDM_TL_YYDONEBYMST, GEN_TL_EMPLOYEEMST ");
		sf.append(" where WWDB_WWMS_KEYID ='"+masterkeyid+"'  ");
		sf.append(" and WWDB_EMPM_KEYID = EMPM_KEYID ");
		
		sql = sf.toString(); 

		CommonFunctions.debugMsg("getyyDoneby....."+sql); 	
		List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql,null);
		return dataList ;
	}

}

