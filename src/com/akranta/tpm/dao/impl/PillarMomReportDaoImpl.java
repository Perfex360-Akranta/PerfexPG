package com.akranta.tpm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PillarMomReportDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;

public class PillarMomReportDaoImpl implements PillarMomReportDao {
private DBActionTemplate dbActionTemplate; 
FunctionCallApi fnCallApi;
	
	public PillarMomReportDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void PillarMomReportDaoImplJwt(String JwtToken) 
	{
		try{
	
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	
private List<String> getFilterParamValues(CommonFilter commonFilter){
		
	String condParms=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	CommonMessage.debugMsg(" Checking 1 ");
	List<String> paramValues = new ArrayList<String>();		

	CommonMessage.debugMsg(" Checking for Momtype"+commonFilter.getMaintMode());
	if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
		   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
	
	if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
		condParms+="PILLARID="+commonFilter.getPillarWise()+";";
	
	paramValues.add(condParms);
	paramValues.add(commonParams); 
	
		 
		return paramValues; 
	}
	
	public List<String[]> getPillarMomAttd(CommonFilter commonFilter) throws Exception
	{
		try
		{ List<String[]> dataList=null;
			List<String > paramValues = getFilterParamValues(commonFilter);
			CommonMessage.debugMsg("Indide the DSO Impl "+commonFilter);
			if(commonFilter.getMaintMode().equals("D")) {
				//dataList= dbActionTemplate.processFunctionCallsWithColHeaders("GEN_PC_REPORTS.GEN_FN_MOMATTDNCPILLARRPT", paramValues);
				
				dataList= fnCallApi.callFunction("GEN_FN_MOMATTDNCPILLARRPT_SB", paramValues,3,false);
			}
			else {
				//dataList= dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_MOMATTDNCPILLARRPTCHAMP", paramValues);
				//GEN_FN_MOMATTDNCPILLARRPTCHAMP
				dataList= fnCallApi.callFunction("GEN_FN_MOMATTDNCPILLARRPTCHAMP_SB", paramValues,3,false);
			}
			
		//	List<String[]> dataList =  dbActionTemplate.processFunctionCalls("COM_PC_TESAUGUST.BDM_FN_ACTWISERPT", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....123"+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
			return dataList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	
	
	public Workbook getPillarMomAttdExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) {
		// TODO Auto-generated method stub
		 ResultSet rs = null;
		   try{
			List<String> paramValues = getFilterParamValues(commonFilter);
			//rs =  dbActionTemplate.dbFunctionCall("MOM_PC_PILLARMEETING.GEN_FN_MOMATTDNCPILLARRPT", paramValues);
			if(commonFilter.getMaintMode().equals("D")) {
			rs =  dbActionTemplate.NewdbFunctionCall2("GEN_FN_MOMATTDNCPILLARRPT_SB", paramValues);
			}
			else 
			{
				rs =  dbActionTemplate.NewdbFunctionCall2("GEN_FN_MOMATTDNCPILLARRPTCHAMP_SB", paramValues);
			}
				//Workbook wb = moMeetingService.getmomAttReportExcel(colmodel,format,commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format,  3, 0,0 );
			
		   }
		   catch(Exception e)
		   {
			   CommonMessage.debugMsg("activityWiseRptExportExcel Exception :"+e.getMessage());
		   }
		   
		   finally{
			   if( rs != null){
				   Connection con = null;
				try {
					con = rs.getStatement().getConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   rs = null;
				   if( con != null )
				   {
					   try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   }
				   
			   }	   
		   }
		return null ;
	}
}
