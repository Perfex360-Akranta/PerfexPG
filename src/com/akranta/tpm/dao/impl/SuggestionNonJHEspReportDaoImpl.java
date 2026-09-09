package com.akranta.tpm.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.SuggestionNonJHEspReportDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class SuggestionNonJHEspReportDaoImpl implements SuggestionNonJHEspReportDao {

private DBActionTemplate dbActionTemplate;
FunctionCallApi fnCallApi;
	
	public SuggestionNonJHEspReportDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void SuggestionNonJHEspReportDaoImplJwt(String JwtToken) 
	{
		try{
	
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public Workbook getSuggestionSummaryGridDataExportExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String rptFormat) throws IOException, SQLException {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 CommonMessage.debugMsg("U are in Dao------------");
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("GEN_FN_ESP_SUGGESTION", paramValues);
		 	
		 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
		 		  
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
		return null;
	}
	

	@Override
	public List<String[]> getSuggestionSummaryGridData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try {

			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			

			//String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
	        CommonMessage.debugMsg("The condParms"+condParms);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			paramValues.add(condParms);

			paramValues.add(commonParams);
			

			//List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_ESP_SUGGESTION",	paramValues);
			List<String[]> dataList = fnCallApi.callFunction("GEN_FN_ESP_SUGGESTION_SB",	paramValues,3,true);

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

	
	
	
	
}


