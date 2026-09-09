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
import com.akranta.tpm.dao.GenTlJhactivitychartmstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlJhactivitychartdtlSql;
import com.akranta.tpm.dao.sql.GenTlJhactivitychartmstSql;
import com.akranta.tpm.dao.sql.KznTlEvaluationmstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlJhactivitychartdtl;
import com.akranta.tpm.model.GenTlJhactivitychartmst;
import com.akranta.tpm.model.KznTlEvaluationmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class GenTlJhactivitychartmstDaoImpl implements GenTlJhactivitychartmstDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlJhactivitychartmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlJhactivitychartmst create(GenTlJhactivitychartmst genTlJhactivitychartmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlJhactivitychartmstSql genTlJhactivitychartmstSql = new GenTlJhactivitychartmstSql(); // contains dbtable,field names, Field types and related sqls  of master table		
		GenTlJhactivitychartdtlSql genTlJhactivitychartdtlSql = new GenTlJhactivitychartdtlSql(); // contains dbtable,field names, Field types and related sqls  of master table

		try{
			List<GenTlJhactivitychartdtl> genTlJhactivitychartdtlList =genTlJhactivitychartmst.getGenTlJhactivitychartdtl();
			genTlJhactivitychartmst.setAchmKeyid(dbActionTemplate.getSequenceNumber(GenTlJhactivitychartmstSql.TBL_GEN_TL_JHACTIVITYCHARTMST, 10, "ACHM", "", "")); 
			sqls.add(GenTlJhactivitychartmstSql.getInsertSql(genTlJhactivitychartmstSql.getAchmDbFields(), genTlJhactivitychartmst.getSaveArray())); // add insert sql for master table
			if(genTlJhactivitychartdtlList!=null)
			{
				CommonMessage.debugMsg("inside dtl");
				for(GenTlJhactivitychartdtl genTlJhactivitychartdtl:genTlJhactivitychartdtlList){
					genTlJhactivitychartdtl.setJacdKeyid(dbActionTemplate.getSequenceNumber(GenTlJhactivitychartdtlSql.TBL_GEN_TL_JHACTIVITYCHARTDTL, 10, "JACD", "", ""));
					genTlJhactivitychartdtl.setJacdAchmKeyid(genTlJhactivitychartmst.getAchmKeyid());
					sqls.add(GenTlJhactivitychartdtlSql.getInsertSql(genTlJhactivitychartdtlSql.getJacdDbFields(), genTlJhactivitychartdtl.getSaveArray())); // add insert sql for master table
				}
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlJhactivitychartmst;
	}
	
	public GenTlJhactivitychartmst update(GenTlJhactivitychartmst genTlJhactivitychartmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlJhactivitychartmstSql genTlJhactivitychartmstSql = new GenTlJhactivitychartmstSql();
		GenTlJhactivitychartdtlSql genTlJhactivitychartdtlSql = new GenTlJhactivitychartdtlSql();
		List<GenTlJhactivitychartdtl> genTlJhactivitychartdtlList =genTlJhactivitychartmst.getGenTlJhactivitychartdtl();
		try {

			sqls.add(GenTlJhactivitychartmstSql.getUpdateSql(genTlJhactivitychartmstSql.getAchmDbFields(), genTlJhactivitychartmst.getSaveArray()));
			if(genTlJhactivitychartdtlList!=null)
			{
				CommonMessage.debugMsg("inside dtl");
				for(GenTlJhactivitychartdtl genTlJhactivitychartdtl:genTlJhactivitychartdtlList){
					if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdKeyid())){
						genTlJhactivitychartdtl.setJacdKeyid(dbActionTemplate.getSequenceNumber(GenTlJhactivitychartdtlSql.TBL_GEN_TL_JHACTIVITYCHARTDTL, 10, "JACD", "", ""));
						genTlJhactivitychartdtl.setJacdAchmKeyid(genTlJhactivitychartmst.getAchmKeyid());
						sqls.add(GenTlJhactivitychartdtlSql.getInsertSql(genTlJhactivitychartdtlSql.getJacdDbFields(), genTlJhactivitychartdtl.getSaveArray())); // add insert sql for master table
					}else
					{
						genTlJhactivitychartdtl.setJacdAchmKeyid(genTlJhactivitychartmst.getAchmKeyid());
						sqls.add(GenTlJhactivitychartdtlSql.getUpdateSql(genTlJhactivitychartdtlSql.getJacdDbFields(), genTlJhactivitychartdtl.getSaveArray())); // add insert sql for master table
					}
				}
			}
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlJhactivitychartmst;
	}
	
	public GenTlJhactivitychartmst delete(GenTlJhactivitychartmst genTlJhactivitychartmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlJhactivitychartmstSql genTlJhactivitychartmstSql = new GenTlJhactivitychartmstSql();
		GenTlJhactivitychartdtlSql genTlJhactivitychartdtlSql = new GenTlJhactivitychartdtlSql();
		try {
			
			sqls.add(genTlJhactivitychartdtlSql.getDeleteMstSql(genTlJhactivitychartmst.getAchmKeyid()));
			sqls.add(genTlJhactivitychartmstSql.getDeleteSql(genTlJhactivitychartmstSql.getAchmDbFields(), genTlJhactivitychartmst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlJhactivitychartmst;
	}

	@Override
	public List<String[]> getjhActDtl(CommonFilter commonFilter) throws Exception {
		CommonMessage.debugMsg("Inside daoimpl");
		List<String> paramValues = new ArrayList<String>();	
		
		String condParms ="";
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		if(UIUtils.isValidKeyId(commonFilter.getKey()))
			condParms+="MSTKEYID="+commonFilter.getKey()+";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
					
		List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("ADM_PC_ADMINISTRATION.ADM_FN_JHACTIVITYENTRYDTL", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt...."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		CommonMessage.debugMsg(dataList);
		return dataList;
	}

	@Override
	public GenTlJhactivitychartmst selectMstData(String mstKeyid)
			throws Exception {
		GenTlJhactivitychartmst genTlJhactivitychartmst = new GenTlJhactivitychartmst();
		String sql = GenTlJhactivitychartmstSql.selectmst();
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] {mstKeyid};
		genTlJhactivitychartmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		CommonMessage.debugMsg(genTlJhactivitychartmst.getAchmFrequency()+" masterkeyid  "+mstKeyid+"  sql   "+sql);
		return genTlJhactivitychartmst;
	}

	@Override
	public List<String[]> getjhAct(CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();	
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			paramValues.add(condParms);
			CommonMessage.debugMsg("condParms...."+condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("ADM_PC_ADMINISTRATION.ADM_FN_JHACTIVITYENTRYGRID", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
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

	@Override
	public Workbook getjhActGridExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception {
		ResultSet rs = null;
		try{	
			rs =   getJHActivityResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
		}finally{
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet getJHActivityResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.dbFunctionCall("ADM_PC_ADMINISTRATION.ADM_FN_JHACTIVITYENTRYGRID", paramValues);
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms =""; //FilterCondSql.get(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}
	
	
}

