package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.dao.EntTlTrainingNeedindenfymstDao;
import com.akranta.tpm.dao.sql.EntTlTrainingNeedindenfymstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTrainingNeedindenfymst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class EntTlTrainingNeedindenfymstDaoImpl implements EntTlTrainingNeedindenfymstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlTrainingNeedindenfymstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlTrainingNeedindenfymst create(EntTlTrainingNeedindenfymst entTlTrainingNeedindenfymst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTrainingNeedindenfymstSql entTlTrainingNeedindenfymstSql = new EntTlTrainingNeedindenfymstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlTrainingNeedindenfymst.setTnimKeyid(dbActionTemplate.getSequenceNumber(EntTlTrainingNeedindenfymstSql.TBL_ENT_TL_TRAINING_NEEDINDENFYMST)); // set the sequnce number 
			sqls.add(EntTlTrainingNeedindenfymstSql.getInsertSql(entTlTrainingNeedindenfymstSql.getTnimDbFields(), entTlTrainingNeedindenfymst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlTrainingNeedindenfymst;
	}
	
	public EntTlTrainingNeedindenfymst update(EntTlTrainingNeedindenfymst entTlTrainingNeedindenfymst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlTrainingNeedindenfymstSql entTlTrainingNeedindenfymstSql = new EntTlTrainingNeedindenfymstSql();
		try {

			sqls.add(EntTlTrainingNeedindenfymstSql.getUpdateSql(entTlTrainingNeedindenfymstSql.getTnimDbFields(), entTlTrainingNeedindenfymst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlTrainingNeedindenfymst;
	}
	
	public EntTlTrainingNeedindenfymst delete(EntTlTrainingNeedindenfymst entTlTrainingNeedindenfymst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlTrainingNeedindenfymstSql entTlTrainingNeedindenfymstSql = new EntTlTrainingNeedindenfymstSql();
		try {
			
			sqls.add(entTlTrainingNeedindenfymstSql.getDeleteSql(entTlTrainingNeedindenfymstSql.getTnimDbFields(), entTlTrainingNeedindenfymst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlTrainingNeedindenfymst;
	}

	@Override
	public List<String[]> getTopic(String type, CommonFilter commonFilter,String flid) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("TrainingGrid::::: ");
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg("test to getTrainingGrid.....");		
	    paramValues.add(type);
	    paramValues.add(flid);
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST1.KPI_FN_TRAININGNEEDINENTIFY", paramValues);
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
	
}

