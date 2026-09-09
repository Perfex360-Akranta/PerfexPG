package com.akranta.tpm.dao.impl;




import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlFeedbackparammstDao;
import com.akranta.tpm.dao.sql.EntTlFeedbackparammstSql;
import com.akranta.tpm.model.EntTlFeedbackparammst;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class EntTlFeedbackparammstDaoImpl implements EntTlFeedbackparammstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlFeedbackparammstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlFeedbackparammst create(EntTlFeedbackparammst entTlFeedbackparammst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlFeedbackparammstSql entTlFeedbackparammstSql = new EntTlFeedbackparammstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlFeedbackparammst.setFbpmKeyid(dbActionTemplate.getSequenceNumber(EntTlFeedbackparammstSql.TBL_ENT_TL_FEEDBACKPARAMMST, 8, "FBP", "", "Y")); // set the sequnce number
			String getParent = null;
			
			if(!UIUtils.isValidKeyId(entTlFeedbackparammst.getFbpmParentid()))
				entTlFeedbackparammst.setFbpmParentid(entTlFeedbackparammst.getFbpmKeyid());
			else
			{
				String getParentSql = EntTlFeedbackparammstSql.getParentSql(entTlFeedbackparammst.getFbpmParentid());
					   getParent 	= dbActionTemplate.getSingleValue(getParentSql);
					   sqls.add(EntTlFeedbackparammstSql.updateIsChildSql(getParent));
				
			}
			sqls.add(EntTlFeedbackparammstSql.getInsertSql(entTlFeedbackparammstSql.getFbpmDbFields(), entTlFeedbackparammst.getSaveArray())); // add insert sql for master table
			
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlFeedbackparammst;
	}
	
	public EntTlFeedbackparammst update(EntTlFeedbackparammst entTlFeedbackparammst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlFeedbackparammstSql entTlFeedbackparammstSql = new EntTlFeedbackparammstSql();
		try {
			String isChild = dbActionTemplate.getSingleValue(EntTlFeedbackparammstSql.TBL_ENT_TL_FEEDBACKPARAMMST, "FBPM_ISCHILD", "FBPM_KEYID", entTlFeedbackparammst.getFbpmKeyid());
			entTlFeedbackparammst.setFbpmIschild(isChild);
			sqls.add(EntTlFeedbackparammstSql.getUpdateSql(entTlFeedbackparammstSql.getFbpmDbFields(), entTlFeedbackparammst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlFeedbackparammst;
	}
	
	public EntTlFeedbackparammst delete(EntTlFeedbackparammst entTlFeedbackparammst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlFeedbackparammstSql entTlFeedbackparammstSql = new EntTlFeedbackparammstSql();
		try {
			
			//sqls.add(EntTlFeedbackparammstSql.getDeleteSql(entTlFeedbackparammstSql.getFbpmDbFields(), entTlFeedbackparammst.getSaveArray()));
			sqls.add(EntTlFeedbackparammstSql.getInactiveSql(entTlFeedbackparammst.getFbpmKeyid()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlFeedbackparammst;
	}
	public List<EntTlFeedbackparammst> getAllFeedback(EntTlFeedbackparammst entTlFeedbackparammst)throws Exception
	{
		String sql = EntTlFeedbackparammstSql.getFBTreeSql(entTlFeedbackparammst);
		CommonMessage.debugMsg("SQL : "+sql);
		List resultList = dbActionTemplate.getDataList(sql);		
		return fillFeedback(resultList);		
	}
	public EntTlFeedbackparammst select(String keyid) throws Exception
	{
		EntTlFeedbackparammst entTlFeedbackparammst = new EntTlFeedbackparammst();
		String sql = EntTlFeedbackparammstSql.getFeedback();		
		Object args [] = new Object [] { keyid };
		entTlFeedbackparammst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		if(CommonFunctions.isValidKeyId(entTlFeedbackparammst.getFbpmEffectiveDate()))
		{
			String effectiveDate = entTlFeedbackparammst.getFbpmEffectiveDate();
			if(effectiveDate.length()>10)
				entTlFeedbackparammst.setFbpmEffectiveDate(effectiveDate.substring(0, effectiveDate.indexOf(" ")));
			
		}
		return entTlFeedbackparammst;
	}
	public EntTlFeedbackparammst inactiveFB(EntTlFeedbackparammst entTlFeedbackparammst)throws Exception
	{
		List<String> sqls = new ArrayList<String>();
		CommonMessage.debugMsg("Inactive Date : "+entTlFeedbackparammst.getFbpmInactiveDate());
		String sql = EntTlFeedbackparammstSql.updateInactiveDateSql(entTlFeedbackparammst.getFbpmKeyid(),entTlFeedbackparammst.getFbpmInactiveDate());
		try {

			sqls.add(sql);			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlFeedbackparammst;
		
	}
	public String getDisplayOrder(String parentId) throws Exception
	{
		String sql = EntTlFeedbackparammstSql.getDispOrderSql(parentId);
		String dispOrder = dbActionTemplate.getSingleValue(sql);
		return dispOrder;
	}
	public List<String[]> getEmployees(String progId,String fromDate,String toDate) throws Exception
	{
		String sql = EntTlFeedbackparammstSql.getEmployeesSql(progId,fromDate,toDate);
		List<String[]> empList = dbActionTemplate.getDataList(sql);
		return empList;
	}
	public List<String[]> getQuestions() throws Exception
	{
		String sql = EntTlFeedbackparammstSql.getQuestionSql();
		CommonMessage.debugMsg(sql);
		List<String[]> questionList = dbActionTemplate.getDataList(sql);
		return questionList;
	}
	public List<String[]> getFBDatas(String empId,String progId) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();	
		
		paramValues.add(progId);
		paramValues.add(empId);
		
		List<String[]> dataList =  dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_FBFACULTYVENUEDT", paramValues);
		return dataList; 
	}
	public List<String[]> getRating(String empId) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();	
		paramValues.add(empId);
		
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_FBRATING", paramValues);
		return dataList; 
		
	}
	
	private List<EntTlFeedbackparammst> fillFeedback(List<String []> resultList) throws SQLException
	{
	
		   List<EntTlFeedbackparammst> fb = new ArrayList<EntTlFeedbackparammst>();
		   for( String [] row : resultList )
		   {
			   EntTlFeedbackparammst efb = new EntTlFeedbackparammst();
			   efb.setFbpmKeyid(row[0]);
			   efb.setFbpmDisplayorder(row[1]);
			   efb.setFbpmParentid(row[2]);
			   efb.setFbpmType(row[3]);
			   efb.setFbpmDescription(row[4]);			
			   fb.add(efb);			   
		   }
  		  return fb;
	 }
}

