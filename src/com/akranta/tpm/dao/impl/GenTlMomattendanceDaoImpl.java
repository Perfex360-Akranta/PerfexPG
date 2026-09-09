package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.dao.GenTlMomattendanceDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMomattendanceSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class GenTlMomattendanceDaoImpl implements GenTlMomattendanceDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlMomattendanceDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlMomattendance createatt(GenTlMomattendance genTlMomattendance) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlMomattendanceSql genTlMomattendanceSql = new GenTlMomattendanceSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			genTlMomattendance.setMomaKeyid(dbActionTemplate.getSequenceNumber(GenTlMomattendanceSql.TBL_GEN_TL_MOMATTENDANCE)); // set the sequnce number 
			sqls.add(GenTlMomattendanceSql.getInsertSql(genTlMomattendanceSql.getMomaDbFields(), genTlMomattendance.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlMomattendance;
	}
	
	public GenTlMomattendance updateatt(GenTlMomattendance genTlMomattendance)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlMomattendanceSql genTlMomattendanceSql = new GenTlMomattendanceSql();
		try {

			sqls.add(GenTlMomattendanceSql.getUpdateSql(genTlMomattendanceSql.getMomaDbFields(), genTlMomattendance.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlMomattendance;
	}
	
	public GenTlMomattendance delete(GenTlMomattendance genTlMomattendance)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlMomattendanceSql genTlMomattendanceSql = new GenTlMomattendanceSql();
		try {
			
			sqls.add(genTlMomattendanceSql.getDeleteSql(genTlMomattendanceSql.getMomaDbFields(), genTlMomattendance.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlMomattendance;
	}
	
	
	

	@Override
	public List<String[]> getMomeetingAtt(CommonFilter commonFilter) throws Exception
	{
		
		CommonMessage.debugMsg("Get Momeeting ATT ");
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
						
			CommonMessage.debugMsg("ParamValues:"+paramValues);	
			List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("GEN_PC_REPORTS.GEN_FN_MOMATTENDENCE", paramValues);
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
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
		}

	
}

