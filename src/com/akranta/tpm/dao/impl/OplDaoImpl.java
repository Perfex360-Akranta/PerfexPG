package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.dao.OPLDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.OplTlMstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.OplTlMstServiceApi;

public class OplDaoImpl implements  OPLDao {
private DBActionTemplate dbActionTemplate;
//-- added by vignesh -- //
OplTlMstServiceApi oplServiceApi;
FunctionCallApi fnCallApi;
	
	public OplDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	//-- added by vignesh -- //
	public void OplDaoImplJwt(String JwtToken) {
	    try {
	        oplServiceApi = new OplTlMstServiceApi(JwtToken);
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//-- added by vignesh -- //
	
	public List<String []> getOplReport(CommonFilter commonFilter) throws Exception
	{
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			
			//paramValues.add(commonFilter.getFromDate());
			//paramValues.add(commonFilter.getToDate());
			
			//paramValues.add((commonFilter.getFactory() != null ? (commonFilter.getFactory().getId()!=null? commonFilter.getFactory().getId():"{}"):"{}"));
			//paramValues.add((commonFilter.getSection() != null ? (commonFilter.getSection().getId()!=null? commonFilter.getSection().getId():"{}"):"{}"));
			//paramValues.add((commonFilter.getCostCenter()!= null ? (commonFilter.getCostCenter().getId()!=null? commonFilter.getCostCenter().getId():"{}"):"{}"));
			//paramValues.add((commonFilter.getCell() != null ? (commonFilter.getCell().getId()!=null? commonFilter.getCell().getId():"{}"):"{}"));
			//paramValues.add((commonFilter.getEqpGroup() != null ? (commonFilter.getEqpGroup().getId()!=null? commonFilter.getEqpGroup().getId():"{}"):"{}"));
			//paramValues.add((commonFilter.getMachine() != null ? (commonFilter.getMachine().getId()!=null? commonFilter.getMachine().getId():"{}"):"{}"));
			//paramValues.add((commonFilter.getJhStep() != null ? (commonFilter.getJhStep().getId()!=null? commonFilter.getJhStep().getId():"{}"):"{}"));
			//paramValues.add((commonFilter.getMachineRank() != null ? (commonFilter.getMachineRank().getId()!=null? commonFilter.getMachineRank().getId():"{}"):"{}"));
			//paramValues.add((commonFilter.getOpltype()!= null ? (commonFilter.getOpltype() !=null? commonFilter.getOpltype():"{}"):"{}"));
			//paramValues.add((commonFilter.getOplNoid()!= null ? (commonFilter.getOplNoid().getId()!=null? commonFilter.getOplNoid().getId():"{}"):"{}"));
			//paramValues.add((commonFilter.getThemelike()!= null ? (commonFilter.getThemelike()!=null? commonFilter.getThemelike():"{}"):"{}"));
			//paramValues.add((commonFilter.getLessonlike()!= null ? (commonFilter.getLessonlike()!=null? commonFilter.getLessonlike():"{}"):"{}"));
			//paramValues.add(commonFilter.getFromRow());
			//paramValues.add(commonFilter.getToRow());
			
		
			
			
			
			 String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);

			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			
			paramValues.add(condParms);
		
			paramValues.add(commonParams);
			
			CommonMessage.debugMsg("paramValues"+paramValues);
			//List<String[]> OplReport = dbActionTemplate.processFunctionCalls("OPL_FN_OPLREPORT", paramValues);
			List<String[]> OplReport = fnCallApi.callFunction("OPL_FN_OPLREPORT_SB", paramValues, 3, true) ;
			CommonMessage.debugMsg("OplReport   :"+OplReport.size() );
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return OplReport;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	} 
		public List<String []> getOplexlReport(CommonFilter commonFilter) throws Exception
		{
			try
			{
				CommonMessage.debugMsg("Inside Exprt daoimpl");
				List<String> paramValues = new ArrayList<String>();
				
				paramValues.add(commonFilter.getOplid());
						
				CommonMessage.debugMsg("commonFilter.getOplid()"+paramValues);
		
				List<String[]> OplexlReport = dbActionTemplate.processFunctionCalls("OPL_FN_ONEPAGEREPORT", paramValues);
                 CommonMessage.debugMsg("DaoImplOplexlReport   :"+OplexlReport);
				return OplexlReport;
			
			}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}



	@Override
	public List<String[]> getoplcountmodify(CommonFilter commonFilter)throws Exception {
		try
		{

			CommonMessage.debugMsg("Inside getAlloplcountmodifyList");
			List<String> paramValues = new ArrayList<String>();
			
			paramValues.add(commonFilter.getFromDate());
			paramValues.add(commonFilter.getToDate());
			
			paramValues.add((commonFilter.getFactory() != null ? (commonFilter.getFactory().getId()!=null? commonFilter.getFactory().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getSection() != null ? (commonFilter.getSection().getId()!=null? commonFilter.getSection().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getCostCenter()!= null ? (commonFilter.getCostCenter().getId()!=null? commonFilter.getCostCenter().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getCell() != null ? (commonFilter.getCell().getId()!=null? commonFilter.getCell().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getEqpGroup() != null ? (commonFilter.getEqpGroup().getId()!=null? commonFilter.getEqpGroup().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getMachine() != null ? (commonFilter.getMachine().getId()!=null? commonFilter.getMachine().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getJhStep() != null ? (commonFilter.getJhStep().getId()!=null? commonFilter.getJhStep().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getMachineRank() != null ? (commonFilter.getMachineRank().getId()!=null? commonFilter.getMachineRank().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getOpltype()!= null ? (commonFilter.getOpltype() !=null? commonFilter.getOpltype():"{}"):"{}"));
			paramValues.add((commonFilter.getOplNoid()!= null ? (commonFilter.getOplNoid().getId()!=null? commonFilter.getOplNoid().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getThemelike()!= null ? (commonFilter.getThemelike()!=null? commonFilter.getThemelike():"{}"):"{}"));
			paramValues.add((commonFilter.getLessonlike()!= null ? (commonFilter.getLessonlike()!=null? commonFilter.getLessonlike():"{}"):"{}"));
			paramValues.add(commonFilter.getFromRow());
			paramValues.add(commonFilter.getToRow());
			
			CommonMessage.debugMsg("paramValues getAlloplcountmodifyList"+paramValues);
	
			List<String[]> OplReport = dbActionTemplate.processFunctionCalls("OPL_FN_OPLREPORTCOUNT", paramValues);
             CommonMessage.debugMsg("DaoImplOplReport   :"+OplReport);
			return OplReport;
		
		}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
	}
}

	@Override
	public List<GenTlAllmoduleimgfile>  getoplImage(List<GenTlAllmoduleimgfile> oplImgList)throws NoDataFoundException, Exception
	{
			CommonMessage.debugMsg("Inside Dao impl EXL Image ");
			//List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
			List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
			for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:oplImgList)
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
	public List<String[]> getAllStudent(String oplId) throws Exception {
		// TODO Auto-generated method stub
		
		
		List<String[]> studentList=null;
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(oplId);
			CommonMessage.debugMsg("opllOplid"+oplId);
			String sql = OplTlMstSql.getOplStudentSql();
			CommonMessage.debugMsg("String sql="+sql);
			studentList = dbActionTemplate.getDataList(sql,paramValues);
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getStudent dao impl"+e.getMessage());
		}
		return studentList;
		
	}

	@Override
	public List<String[]> getOplExcelReport(String oPLId, String format,CommonFilter commonFilter)throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside export daoimpl Excel");
			List<String> paramValues = new ArrayList<String>();				
		
			paramValues.add(oPLId);
			
			CommonMessage.debugMsg("param Values :-" +paramValues.get(0));
		
			List<String[]> oplExcelReport = dbActionTemplate.processFunctionCalls("OPL_FN_ONEPAGEREPORT", paramValues);
			 CommonMessage.debugMsg("DaoImplOplexlReport   :"+oplExcelReport);	
			
			
			CommonMessage.debugMsg("Inside v: " +oplExcelReport.toString());
			CommonMessage.debugMsg("Inside ParamVal: " +paramValues);
			
			//return ImproprojshtReport;
			return oplExcelReport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

	@Override
	public Map<Integer, List<String[]>> OplExcelReport(String oPLId,String format, CommonFilter commonFilter) throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside export daoimpl Excel");
			List<String> paramValues = new ArrayList<String>();				
		
			paramValues.add(oPLId);
			
			CommonMessage.debugMsg("param Values :-" +paramValues.get(0));
		
			Map<Integer, List<String[]>> oplExportReport = dbActionTemplate.processDbFunCallMultCursor("OPL_FN_ONEPAGEREPORT", paramValues,2);
			
			for(int i = 0; i <  oplExportReport.size();i++ )
			{
				CommonMessage.debugMsg("Inside v: " +oplExportReport.get(i).size() );
			}
			
			CommonMessage.debugMsg("Inside v: " +oplExportReport.toString());
			CommonMessage.debugMsg("Inside ParamVal: " +paramValues);
			
			//return ImproprojshtReport;
			return oplExportReport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

	@Override
	public Workbook getAlloplxl(CommonFilter commonFilter, JSONObject colmodel,	String rptFormat) throws Exception {
		  ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.NewdbFunctionCall2("OPL_FN_OPLREPORT", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	@Override
	public List<String[]> getImprovementProjSheetkaiexlReportFunctllocn(
			String flid) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();  //,MCHM_MACHINENO

		sql.append(" select SBUT_NAME,PBUT_NAME,SECT_NAME,CELL_NAME,MCHM_MACHINENAME ");
		sql.append(" from gen_vw_fnln where fnln_keyid='"+flid+"' ");

		List<String[]>  functllocn  = dbActionTemplate.getDataList(sql.toString());
	    return functllocn;
		
	}	
	

	@Override
	public List<String[]> getOPLSummaryGridData(CommonFilter commonFilter)
			throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			

			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
           CommonMessage.debugMsg("The condParms"+condParms);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg("commonParams"+commonParams);
			paramValues.add(condParms);

			paramValues.add(commonParams);
			

		//	List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_OPL_SUMMARY_REPORT",	paramValues);
       // -- Vignesh

			List<String[]> dataList =  fnCallApi.callFunction("GEN_FN_OPL_SUMMARY_REPORT_SB",	paramValues,3,true);

			
			
			CommonMessage.debugMsg("Inside daoimpl 5: " + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("Test --->" + dataList.size());
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	@Override
	public Workbook getOplSummaryGridDataExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)throws Exception {
		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("GEN_FN_OPL_SUMMARY_REPORT", paramValues);
		 	ExcelUtils excelUtils = new ExcelUtils(colModel);
			return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
	}



	
}
