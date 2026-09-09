package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.dao.EntTrainingAreaDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.ExcelUtils;

public class EntTrainingAreaDaoImpl implements EntTrainingAreaDao {
	private DBActionTemplate dbActionTemplate; 
	public EntTrainingAreaDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	@Override
	public List<String[]> getTrainingAreaGrid(CommonFilter commonFilter,String training)
			throws Exception {
		List<String > paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		try
		{	List<String[]> result = new ArrayList<String[]>();
			if(training.equals("true"))
			result=dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGAREA", paramValues);
			else if(training.equals("false"))
			result=dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_TRAINERSLIST", paramValues);
			return result;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
		
	}

	@Override
	public Workbook getTrainingAreaExcel(JSONObject colmodel, String format,CommonFilter commonFilter)
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
		
		 
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGAREA", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}	

}
