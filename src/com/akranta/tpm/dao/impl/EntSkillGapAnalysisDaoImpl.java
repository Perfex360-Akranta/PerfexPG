package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.dao.EntSkillGapAnalysisDao;
import com.akranta.tpm.dao.sql.EntReportRelatedSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class EntSkillGapAnalysisDaoImpl implements EntSkillGapAnalysisDao {
	private DBActionTemplate dbActionTemplate; 
	public EntSkillGapAnalysisDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	@Override
	public List<String[]> getSkillGapGrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String > paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg("paramvalues=" + paramValues);
		try
		{
			List<String[]> result=dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_RADAR_CHART", paramValues);
			/*if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}*/
			
			return result;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
		
	}
	@Override
	public Workbook getSkillGapExcel(JSONObject colmodel, String format,CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,0,0,0 );
			
		   }finally{
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_RADAR_CHART", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}	


	
	public List<String[]> getEmpAssesmentTopicRatings(String assessmentId,String fromSpoke ) throws NoDataFoundException, Exception{
		CommonMessage.debugMsg("In Dao Impl");
		Object [] args = {assessmentId };
		String sql = EntReportRelatedSqls.getEmpAssessmentTopicRatingSql(fromSpoke);
		CommonMessage.debugMsg("sql in dao impl:"+sql);
		return dbActionTemplate.getDataList(sql, args);
	}
	@Override
	public List<String[]> getRadarChart(String key, String date,String empId) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> result = null;
		String sql="SELECT DISTINCT PARENTNAMES,role_name,EMPM_NAME,EMPM_CODE,to_char(ASMM_EVALUATION_DATE,'DD-Mon-YYYY') as evaluationDate,EMPM_KEYID as keyid,ADD_MONTHS(ASMM_EVALUATION_DATE,3) AS NEXTEVLAUATIONDATE "+
		" FROM GEN_TL_ROLEMST, ENT_TL_ASSESSMENTMST,GEN_TL_EMPLOYEEMST,ENT_TL_SPOKEMST,ENT_TL_ASSESSMENTDTL,ENT_VW_TRAININGAREACHILDPATH "+"" +
		"WHERE ASMD_SPOK_KEYID = SPOK_KEYID AND EMPM_KEYID = ASMM_EMPM_KEYID AND ROLE_KEYID = ASMM_ROLE_KEYID"+
		" AND ASMM_KEYID = ASMD_ASMM_KEYID AND  ASMM_TRAR_KEYID = KEYID AND ASMM_EVALUATION_TYPE = 'POS' AND ASMM_TRAR_KEYID ='"+key+"'AND ASMM_EVALUATION_DATE='"+date+"'AND EMPM_KEYID ='"+empId+"'";
		CommonMessage.debugMsg(" sql " +sql);
		result=dbActionTemplate.getDataList(sql);
		return result;
	}

}
