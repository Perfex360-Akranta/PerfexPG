
package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.OplDrilldownDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.CommonMessage;


public class OplDrilldownDaoImpl implements OplDrilldownDao {
	


	private DBActionTemplate dbActionTemplate; 
	
	public OplDrilldownDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String[]> getAllopldrill(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  opl DAO Impl");
			
		/*	paramValues.add(commonFilter.getDrillLevel());
			paramValues.add(commonFilter.getFromDate());
			paramValues.add(commonFilter.getToDate());
			paramValues.add((commonFilter.getCompany() != null ? (commonFilter.getCompany().getId()!=null? commonFilter.getCompany().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getFactory() != null ? (commonFilter.getFactory().getId()!=null? commonFilter.getFactory().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getSection() != null ? (commonFilter.getSection().getId()!=null? commonFilter.getSection().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getCell() != null ? (commonFilter.getCell().getId()!=null? commonFilter.getCell().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getEqpGroup() != null ? (commonFilter.getEqpGroup().getId()!=null? commonFilter.getEqpGroup().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getCircle() != null ? (commonFilter.getCircle().getId()!=null? commonFilter.getCircle().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getCostCenter() != null ? (commonFilter.getCostCenter().getId()!=null? commonFilter.getCostCenter().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getJhStep() != null ? (commonFilter.getJhStep().getId()!=null? commonFilter.getJhStep().getId():"{}"):"{}"));
			paramValues.add((commonFilter.getMachineRank() != null ? (commonFilter.getMachineRank().getId()!=null? commonFilter.getMachineRank().getId():"{}"):"{}"));
			//paramValues.add(commonFilter.getFromRow());
			//paramValues.add(commonFilter.getToRow());
		*/	
			
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);

			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside  opl DAO Impl " + paramValues);
			
			List<String[]> oplDrilldowntList = dbActionTemplate.processFunctionCalls("OPL_PC_ONEPOINTLESSION.OPL_FN_OPLDRILLDWN", paramValues);
				
			
			return oplDrilldowntList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getAllopldrillExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 0,1,0 );
			
		   }finally{
			   if( rs != null)
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("OPL_PC_ONEPOINTLESSION.OPL_FN_OPLDRILLDWN", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}	


}