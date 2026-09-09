package com.akranta.tpm.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.KaizenRewardReportDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.FunctionCallApi;

public class KaizenRewardReportDaoImpl implements KaizenRewardReportDao {
	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;
	public KaizenRewardReportDaoImpl(DBActionTemplate dbActionTemplate) {
		// TODO Auto-generated constructor stub
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void KaizenRewardReportDaoImplJwt(String JwtToken) 
  	{
  		try{
  			//machinemasterserviceapi = new MachineMasterServiceApi(JwtToken);
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

	@Override
	public List<String[]> getKaizenRewardGridData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try{
		List<String> paramValues = new ArrayList<String>();
		

		//String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		CommonMessage.debugMsg("The condParms"+condParms);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

		paramValues.add(condParms);

		paramValues.add(commonParams);
		

		//List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENREWARDSCHEME",	paramValues);
		
		List<String[]> dataList = fnCallApi.callFunction("KZN_FN_KAIZENREWARDSCHEME_sb",paramValues,3,true);
		
		

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
	public Workbook getKaizenRewardGridDataExportExcel(	CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws IOException, SQLException {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 CommonMessage.debugMsg("U are in Dao------------");
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("KZN_FN_KAIZENREWARDSCHEME", paramValues);
		 	
		 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format,2,0,0 );
		 		  
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
		return null;
	}
	
	
	public Workbook getDtlKaizenPendingGridDataExportExcel(	CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws IOException, SQLException {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 CommonMessage.debugMsg("U are in Dao------------");
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("KZN_FN_KAIZENVIEW", paramValues);
		 	
		 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format,2,0,0 );
		 		  
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
		return null;
	}

	@Override
	public List<String[]> getKaizenPendingGridData(CommonFilter commonFilter)
			throws Exception {
		try{
			List<String> paramValues = new ArrayList<String>();
		
			

			//List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  visual Work place Month DAO Impl");
			
			String type=commonFilter.getAbnViewType();
			
			CommonMessage.debugMsg(" Checking for type "+type);
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			CommonMessage.debugMsg(commonFilter.getFromMonth()+"   The condParms  "+condParms);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			
			String dril=commonFilter.getDrillLevel();
			String total="A";
			total=commonFilter.getTotal(); 
			if(total==null){
				total="A";
			}
			CommonMessage.debugMsg("In side DAO Impl"+dril);
			
			if(dril.equals("CMP")||dril.equals("COMP")){
				CommonMessage.debugMsg("In side DAO Impl CMP"+dril);

				condParms+="DRILLLEVEL=SEC;";
			}
			else if(dril.equals("LCN")){
				CommonMessage.debugMsg("In side DAO Impl LOCN "+dril);

				condParms+="DRILLLEVEL=SEC;";
			}
			else if(dril.equals("SBU")){
				CommonMessage.debugMsg("In side DAO Impl SBU "+dril);

				condParms+="DRILLLEVEL=SEC;";
			}
			else if(dril.equals("PBU")){
				CommonMessage.debugMsg("In side DAO Impl PBU"+dril);

				condParms+="DRILLLEVEL=SEC;";
			}
			else if(dril.equals("SEC")){
				CommonMessage.debugMsg("In side DAO Impl SECT"+dril);

				condParms+="DRILLLEVEL=SEC;";
			}
			
			else if(dril.equals("CEL")){
				condParms+="DRILLLEVEL=CEL;";
			}
			
			if(UIUtils.isValidKeyId(type))
				condParms +="TYPE="+type+";";				
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList=null;
		   if(dril.equals("DTL")){
			   
			   dataList=dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENVIEW",	paramValues);
			  // dataList=fnCallApi.callFunction("KZN_FN_KAIZENVIEW_SB",	paramValues,2,true);
		   }
			
		   else{
		   //dataList = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENRPENDINGSUMMARY",	paramValues);
		   dataList = fnCallApi.callFunction("KZN_FN_KAIZENRPENDINGSUMMARY_SB",	paramValues,2,true);
		   }
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
	public Workbook getKaizenPendingGridDataExportExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		// TODO Auto-generated method stub
				List<String> paramValues = new ArrayList<String>();				
				 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
				 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
				 paramValues.add(condParms);	 
				 paramValues.add(commonParams);
				 CommonMessage.debugMsg("U are in Dao------------");
				 ResultSet rs = null;
				 try{
				 	rs=dbActionTemplate.NewdbFunctionCall2("KZN_FN_KAIZENRPENDINGSUMMARY_SB", paramValues);
				 	
				 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
					ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
					return excelUtils.writeToExcel(rs,format,2,1,0 );
					
				 }catch(Exception e){
					 e.printStackTrace();
				 }
				 finally{
					   if( rs != null)
				 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
					     
				 }
				return null;
			}

	@Override
	public List<String[]> getPendingKaizenReport(CommonFilter commonFilter)
			throws Exception {
		List<String> paramValues = new ArrayList<String>();
		
		

		//List<String > paramValues = new ArrayList<String>();
		CommonMessage.debugMsg("Inside  visual Work place Month DAO Impl");
		
		String type=commonFilter.getAbnViewType();
		
		CommonMessage.debugMsg(" Checking for type "+type);
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		CommonMessage.debugMsg("The condParms"+condParms);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

		
		String dril=commonFilter.getDrillLevel();
		String total="A";
		total=commonFilter.getTotal(); 
		if(total==null){
			total="A";
		}
		condParms +="TYPE=DTL;";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
	  
		   
		//List<String[]>  dataList=dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENVIEW",	paramValues);
		List<String[]> dataList=fnCallApi.callFunction("KZN_FN_KAIZENVIEW_SB",	paramValues,2,true);
	  
	
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


	
	}
}

