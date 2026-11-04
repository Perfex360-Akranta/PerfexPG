
package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.EquipmentDownTimeDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.VisualsopServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;



public class BAL_EquipmentDownTimeDaoImpl implements EquipmentDownTimeDao {
	
	FunctionCallApi fnCallApi;

	private DBActionTemplate dbActionTemplate; 
	
	public BAL_EquipmentDownTimeDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	//jwt
public void BAL_EquipmentDownTimeDaoImplJwt(String jwtToken) {
		
		try{
		fnCallApi = new FunctionCallApi(jwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	public List<String[]> getAllEquipmentDownTime(CommonFilter commonFilter) throws Exception
	{
		try
		{
			//CommonFunctions.debugMsg("Remove Blank..............."+commonFilter.getRemoveBlank());
			 List<String > paramValues = new ArrayList<String>();
			 commonFilter.setBreakup(FilterCondSql.getBreakup(commonFilter.getBreakUpList()));
			 CommonFunctions.debugMsg(commonFilter.getBreakup());
			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 List<String[]> rootRptList;
		//	 System.out.println("paramvalues1111111111111" + commonFilter.getDrillLevel());
		/*	 if(DrillLevelConstants.ASSEMBLY.equals(commonFilter.getDrillLevel()  ))	
			 {
			 	rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_BREAKDOWN.BDM_FN_EQPFAILUREFORASSM", paramValues);
			 	CommonFunctions.debugMsg("assssssembly"+rootRptList);
			 }
			
			 else if(DrillLevelConstants.PHENOMENA.equals(commonFilter.getDrillLevel()  ))	{
				rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_BREAKDOWN.BDM_FN_EQPFAILUREFORPHEN", paramValues);			
			 }
			 else
			 {
		*/		
				// rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_BREAKDOWN.BDM_FN_EQPFAILURE", paramValues);			
			//rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("bdm_fn_eqpfailure", paramValues);
			rootRptList = fnCallApi.callFunction("bdm_fn_eqpfailure_sb", paramValues,4,true);
			 
		//	 }
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }
			return rootRptList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	public List<String[]> getSumOfEquipmentDownTimeForDrill(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 List<String[]> rootRptList;
		//	 System.out.println("paramvalues1111111111111" + commonFilter.getDrillLevel());
		/*	 if(DrillLevelConstants.ASSEMBLY.equals(commonFilter.getDrillLevel()  ))	
			 {
			 	rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_BREAKDOWN.BDM_FN_EQPFAILUREFORASSM", paramValues);
			 	CommonFunctions.debugMsg("assssssembly"+rootRptList);
			 }
			
			 else if(DrillLevelConstants.PHENOMENA.equals(commonFilter.getDrillLevel()  ))	{
				rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_BREAKDOWN.BDM_FN_EQPFAILUREFORPHEN", paramValues);			
			 }
			 else 
			 {
				*/ 
				 rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_BREAKDOWN.BDM_FN_EQPFAILUREGROUPDRILL", paramValues);			
			// }
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }
			return rootRptList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	@Override
	public List<String[]> getAllcount(CommonFilter commonFilter)throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
		
			System.out.println("Inside  DAO Impl DrillValue equipment 5222222222222222211111111111" + commonFilter.getDrillLevel());
			
			paramValues.add(commonFilter.getDrillLevel());
			paramValues.add(commonFilter.getMonwise());
			//paramValues.add(commonFilter.getMonwise()!= null ? commonFilter.getMonwise():"{}");	
		 //	paramValues.add((commonFilter.getMonwise() != null ? (commonFilter.getMonwise().getId()!=null? commonFilter.getMonwise().getId():"{}"):"{}"));
			paramValues.add(commonFilter.getFromDate());
			paramValues.add(commonFilter.getToDate());
			//paramValues.add((commonFilter.getCompany() != null ? (commonFilter.getCompany().getId()!=null? commonFilter.getCompany().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getFactory() != null ? (commonFilter.getFactory().getId()!=null? commonFilter.getFactory().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getSection() != null ? (commonFilter.getSection().getId()!=null? commonFilter.getSection().getId():"{}"):"{}"));
			
			paramValues.add((commonFilter.getCell() != null ? (commonFilter.getCell().getId()!=null? commonFilter.getCell().getId():"{}"):"{}"));
			
			
			paramValues.add((commonFilter.getMachine() != null ? (commonFilter.getMachine().getId()!=null? commonFilter.getMachine().getId():"{}"):"{}"));
			
			paramValues.add((commonFilter.getAssembly() != null ? (commonFilter.getAssembly().getId()!=null? commonFilter.getAssembly().getId():"{}"):"{}"));
			
			//paramValues.add(commonFilter.getSpares()==null ? "()":commonFilter.getSpares());// != null ? (commonFilter.getSpares().getId()!=null? commonFilter.getSpares().getId():"{}"):"{}"));
			paramValues.add(commonFilter.getSpares()!= null ? commonFilter.getSpares():"{}");	
			//paramValues.add(commonFilter.getFromRow());
			//paramValues.add(commonFilter.getToRow());
			
			System.out.println("paramvalues" + paramValues);
			List<String[]> countRptList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_BREAKDOWN.BDM_FN_EQPFAILURECOUNT", paramValues);			
			System.out.println(" ::: 99- " + countRptList.size());
			return countRptList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	
   }
	@Override
	public Workbook getdownTimeReport(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		   ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat,4,1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.NewdbFunctionCall2("BDM_FN_EQPFAILURE_SB", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}		
		
}

