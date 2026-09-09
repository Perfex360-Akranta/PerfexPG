package com.akranta.tpm.dao.impl;




import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.dao.EntTlTrainingmstDao;
import com.akranta.tpm.dao.sql.EntTlTrainingmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTrainingmst;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class EntTlTrainingmstDaoImpl implements EntTlTrainingmstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlTrainingmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlTrainingmst create(EntTlTrainingmst entTlTrainingmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTrainingmstSql entTlTrainingmstSql = new EntTlTrainingmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlTrainingmst.setTmstKeyid(dbActionTemplate.getSequenceNumber(EntTlTrainingmstSql.TBL_ENT_TL_TRAININGMST)); // set the sequnce number 
			sqls.add(EntTlTrainingmstSql.getInsertSql(entTlTrainingmstSql.getTmstDbFields(), entTlTrainingmst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlTrainingmst;
	}
	
	public EntTlTrainingmst update(EntTlTrainingmst entTlTrainingmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlTrainingmstSql entTlTrainingmstSql = new EntTlTrainingmstSql();
		try {

			sqls.add(EntTlTrainingmstSql.getUpdateSql(entTlTrainingmstSql.getTmstDbFields(), entTlTrainingmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlTrainingmst;
	}
	
	public EntTlTrainingmst delete(EntTlTrainingmst entTlTrainingmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlTrainingmstSql entTlTrainingmstSql = new EntTlTrainingmstSql();
		try {
			
			sqls.add(entTlTrainingmstSql.getDeleteSql(entTlTrainingmstSql.getTmstDbFields(), entTlTrainingmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlTrainingmst;
	}

	@Override
	public List<String[]> getTrainingGrid(CommonFilter commonFilter ,String typeCp ,String keyid) throws Exception
	{
		
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg("test to getTrainingGrid.....");
		
		paramValues.add(condParms+"TYPECP="+typeCp+";MSTKEYID="+keyid+";");
		paramValues.add(commonParams);
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST1.ENT_FN_FBFORMRATING", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	
	}

	@Override
	public List<String[]> getTrainingFebMain(CommonFilter commonFilter) throws Exception
	{
		
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg("test to............");
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST.ENT_FN_TRAININGFEEDBACK", paramValues);
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 

	}

	@Override
	public List<String[]> getTrainingGridIn(CommonFilter commonFilter,String typeIP,String keyid ) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg("test to getTrainingGrid.....");
		
		paramValues.add(condParms+"TYPECP="+typeIP+";MSTKEYID="+keyid+";");
		paramValues.add(commonParams);
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST1.ENT_FN_FBFORMRATING", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}

	@Override
	public EntTlTrainingmst select(String tmstKeyid) throws NoDataFoundException, SQLException, Exception
	{
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:" +tmstKeyid);
		EntTlTrainingmst newEntTlTrainingmst = new EntTlTrainingmst();		
		String sql = EntTlTrainingmstSql.getTrainingSelectSql();				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { tmstKeyid };
		newEntTlTrainingmst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+newEntTlTrainingmst.getTmstKeyid());
		return  newEntTlTrainingmst;
	}	
}

