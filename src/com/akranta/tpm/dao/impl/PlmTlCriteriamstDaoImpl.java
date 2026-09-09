package com.akranta.tpm.dao.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.dao.PlmTlCriteriamstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.PlmTlCriteriamstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PlmTlCriteriamst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class PlmTlCriteriamstDaoImpl implements PlmTlCriteriamstDao {


	private DBActionTemplate dbActionTemplate; 

	public PlmTlCriteriamstDaoImpl(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public PlmTlCriteriamst create(PlmTlCriteriamst plmTlCriteriamst) 	throws Exception {
		List<String> sqls = new ArrayList<String>(); 
		PlmTlCriteriamstSql plmTlCriteriamstSql = new PlmTlCriteriamstSql(); 
		try{	
			//CommonMessage.debugMsg("create dao impl");
			plmTlCriteriamst.setCriaKeyid(dbActionTemplate.getSequenceNumber(PlmTlCriteriamstSql.TBL_PLM_TL_CRITERIAMST, 10,"CRA" , "", "")); 
			//CommonMessage.debugMsg("create dao impl");
			sqls.add(PlmTlCriteriamstSql.getInsertSql(plmTlCriteriamstSql.getCriaDbFields(), plmTlCriteriamst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls); 	
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return plmTlCriteriamst;
	}
	
	public PlmTlCriteriamst update(PlmTlCriteriamst plmTlCriteriamst)	throws Exception { 
		List<String> sqls = new ArrayList<String>();
		PlmTlCriteriamstSql plmTlCriteriamstSql = new PlmTlCriteriamstSql();
		try {
			sqls.add(PlmTlCriteriamstSql.getUpdateSql(plmTlCriteriamstSql.getCriaDbFields(), plmTlCriteriamst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		return plmTlCriteriamst;
	}
	
	public PlmTlCriteriamst delete(PlmTlCriteriamst plmTlCriteriamst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		PlmTlCriteriamstSql plmTlCriteriamstSql = new PlmTlCriteriamstSql();
		try {
			sqls.add(plmTlCriteriamstSql.getDeleteSql(plmTlCriteriamstSql.getCriaDbFields(), plmTlCriteriamst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return plmTlCriteriamst;
	}

	@Override
	public List<String[]> getCriteriaMstList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			dataList =  dbActionTemplate.processFunctionCalls("PLM_FN_CRITERIAMSTGRID", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if( isInteger ){
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
	
}

