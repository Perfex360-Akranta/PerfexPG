package com.akranta.tpm.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlAssessmentmstDao;
import com.akranta.tpm.dao.sql.EntTlAssessmentChecklistSql;
import com.akranta.tpm.dao.sql.EntTlAssessmentdtlSql;
import com.akranta.tpm.dao.sql.EntTlAssessmentmstSql;
import com.akranta.tpm.dao.sql.EntTlMultiskilldialymapSql;
import com.akranta.tpm.dao.sql.EntTlMultiskillempmapSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlAssessmentChecklist;
import com.akranta.tpm.model.EntTlAssessmentdtl;
import com.akranta.tpm.model.EntTlAssessmentmst;
import com.akranta.tpm.model.EntTlMultiskilldialymap;
import com.akranta.tpm.model.EntTlMultiskillempmap;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class EntTlAssessmentmstDaoImpl implements EntTlAssessmentmstDao {

	private DBActionTemplate dbActionTemplate; 
	EntTlAssessmentmstSql entTlAssessmentmstSql;
	EntTlAssessmentdtlSql entTlAssessmentdtlSql;
	EntTlAssessmentChecklistSql entTlAssessmentChecklistSql;
	public EntTlAssessmentmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		entTlAssessmentmstSql = new EntTlAssessmentmstSql();
		entTlAssessmentdtlSql = new EntTlAssessmentdtlSql();
		entTlAssessmentChecklistSql=new EntTlAssessmentChecklistSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlAssessmentmst create(EntTlAssessmentmst entTlAssessmentmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 		
		try{
		
			entTlAssessmentmst.setAsmmKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_ENT_TL_ASSESSMENTMST, 12, "ASM", null,null)); // set the sequnce number 
			sqls.add(entTlAssessmentmstSql.getInsertSql(entTlAssessmentmstSql.getAsmmDbFields(), entTlAssessmentmst.getSaveArray())); // add insert sql for master table
			int evaluationNo=Integer.parseInt(entTlAssessmentmst.getAsmmEvaluationNo());
			if (evaluationNo>1){
				evaluationNo=evaluationNo-1;
				String status="Y";
				sqls.add( entTlAssessmentmstSql.getUpdateExists(entTlAssessmentmstSql.getAsmmDbFields(), entTlAssessmentmst.getSaveArray(),Integer.toString(evaluationNo),status)); // add insert sql for master table
			}
					
			if (entTlAssessmentmst.getEntTlAssessmentdtl() != null
					&& entTlAssessmentmst.getEntTlAssessmentdtl().size() > 0) 
			{	
				for (int i = 0; i < entTlAssessmentmst.getEntTlAssessmentdtl().size(); i++) {
					EntTlAssessmentdtl entTlAssessmentdtl = (EntTlAssessmentdtl) entTlAssessmentmst
							.getEntTlAssessmentdtl().get(i); 
					sqls.add(addEntTlAssessmentdtl(entTlAssessmentmst,entTlAssessmentdtl));
					if (entTlAssessmentdtl.getEntTlAssessmentChecklist() != null
							&& entTlAssessmentdtl.getEntTlAssessmentChecklist().size() > 0) 
					{	
						for (int j = 0; j < entTlAssessmentdtl.getEntTlAssessmentChecklist().size(); j++) {
							EntTlAssessmentChecklist entTlAssessmentChecklist = (EntTlAssessmentChecklist) entTlAssessmentdtl
									.getEntTlAssessmentChecklist().get(j);							
							sqls.add(addEntTlAssessmentChecklist(entTlAssessmentdtl,entTlAssessmentChecklist));
							
						}						
					}
				}				
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlAssessmentmst;
	}
	
	public EntTlAssessmentmst update(EntTlAssessmentmst entTlAssessmentmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		try {
			sqls.add(entTlAssessmentmstSql.getUpdateSql(entTlAssessmentmstSql.getAsmmDbFields(), entTlAssessmentmst.getSaveArray()));
			if (entTlAssessmentmst.getEntTlAssessmentdtl() != null
					&& entTlAssessmentmst.getEntTlAssessmentdtl().size() > 0) 
			{	
				for (int i = 0; i < entTlAssessmentmst.getEntTlAssessmentdtl().size(); i++) {
					EntTlAssessmentdtl entTlAssessmentdtl = (EntTlAssessmentdtl) entTlAssessmentmst
							.getEntTlAssessmentdtl().get(i); 
					sqls.add(addEntTlAssessmentdtl(entTlAssessmentmst,entTlAssessmentdtl));
					if (entTlAssessmentdtl.getEntTlAssessmentChecklist() != null
							&& entTlAssessmentdtl.getEntTlAssessmentChecklist().size() > 0) 
					{	
						for (int j = 0; j < entTlAssessmentdtl.getEntTlAssessmentChecklist().size(); j++) {
							EntTlAssessmentChecklist entTlAssessmentChecklist = (EntTlAssessmentChecklist) entTlAssessmentdtl
									.getEntTlAssessmentChecklist().get(j); 
							sqls.add(addEntTlAssessmentChecklist(entTlAssessmentdtl,entTlAssessmentChecklist));
						}						
					}
				}				
			}
			dbActionTemplate.executeStatements(sqls);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}		
		return entTlAssessmentmst;
	}
	
	public EntTlAssessmentmst delete(EntTlAssessmentmst entTlAssessmentmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		String sql=null;		
		
		EntTlAssessmentmst newEntTlAssessmentmst=new EntTlAssessmentmst();
		try {
			String sql1 = "SELECT COUNT(*) FROM " + TableNames.TBL_ENT_TL_ASSESSMENTDTL + " WHERE ASMD_ASMM_KEYID='"+entTlAssessmentmst.getAsmmKeyid() + "'";
			CommonMessage.debugMsg("Count sql1....."+sql1);
			String returnData = dbActionTemplate.getSingleValue(sql1);
			int count = Integer.parseInt(returnData);
			if (entTlAssessmentmst.getEntTlAssessmentdtl().size()>0){
				for (int i = 0; i < entTlAssessmentmst.getEntTlAssessmentdtl().size(); i++) {
					EntTlAssessmentdtl entTlAssessmentdtl = (EntTlAssessmentdtl) entTlAssessmentmst
							.getEntTlAssessmentdtl().get(i); 
					
					sqls.add("DELETE FROM " + TableNames.TBL_ENT_TL_ASSESSMENT_CHECKLIST + " WHERE ASCL_ASMD_KEYID='"+entTlAssessmentdtl.getAsmdKeyid() +"'");
					sqls.add(entTlAssessmentdtlSql.getDeleteSql(entTlAssessmentdtlSql.getAsmdDbFields(), entTlAssessmentdtl.getSaveArray()));
				}	
				
				if(count==1){					
					sqls.add(entTlAssessmentmstSql.getDeleteSql(entTlAssessmentmstSql.getAsmmDbFields(), entTlAssessmentmst.getSaveArray()));
				}
				
				int evaluationNo=Integer.parseInt(entTlAssessmentmst.getAsmmEvaluationNo());
				if (evaluationNo>1){
					evaluationNo=evaluationNo-1;
					String status="N";
					sqls.add( entTlAssessmentmstSql.getUpdateExists(entTlAssessmentmstSql.getAsmmDbFields(), entTlAssessmentmst.getSaveArray(),Integer.toString(evaluationNo),status)); // add insert sql for master table
				}				
			}
			else{
				sql="DELETE FROM " + TableNames.TBL_ENT_TL_ASSESSMENT_CHECKLIST + " WHERE ASCL_ASMD_KEYID in (";
				sql=sql +  " SELECT ASMD_KEYID FROM " + TableNames.TBL_ENT_TL_ASSESSMENTDTL + " WHERE ASMD_ASMM_KEYID='"+entTlAssessmentmst.getAsmmKeyid() + "'" +") ";
				sqls.add(sql);
				sqls.add("DELETE FROM " + TableNames.TBL_ENT_TL_ASSESSMENTDTL + " WHERE ASMD_ASMM_KEYID='"+entTlAssessmentmst.getAsmmKeyid() + "'");
				sqls.add(entTlAssessmentmstSql.getDeleteSql(entTlAssessmentmstSql.getAsmmDbFields(), entTlAssessmentmst.getSaveArray()));
			}
			
			dbActionTemplate.executeStatements(sqls);
			
			if(count>1)
				return entTlAssessmentmst;
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return newEntTlAssessmentmst;
	}
	
	@Override
	public EntTlAssessmentmst select(EntTlAssessmentmst entTlAssessmentmst) throws Exception 
	{
		CommonMessage.debugMsg("Inside the dao impl");
		String sql = entTlAssessmentmstSql.getSeleteSql(entTlAssessmentmstSql.getAsmmDbFields(), entTlAssessmentmst.getSaveArray());				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		//String Qaud_keyid=EntTlAssessmentmst.getQaudkeyid();
		Object [] args =  new Object [] {};
		entTlAssessmentmst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+entTlAssessmentmst.getAsmmKeyid());		
		return  entTlAssessmentmst;
	}	
	
	@Override	
	public List<EntTlAssessmentdtl> selectAssessmentList(EntTlAssessmentdtl entTlAssessmentdtl)throws Exception
	{		
		String sql =entTlAssessmentdtlSql.getSelectSql(entTlAssessmentdtlSql.getAsmdDbFields(), entTlAssessmentdtl.getSaveArray());
		CommonMessage.debugMsg("DAO SQL : "+sql);		
		List resultList = dbActionTemplate.getDataList(sql.toString());
		return  fillDetailsList(resultList) ;
	}
	
	@Override	
	public List<String[]> getAssessmentList(EntTlAssessmentdtl EntTlAssessmentdtl) throws Exception
	{		
		String sql =entTlAssessmentdtlSql.getSelectSql(entTlAssessmentdtlSql.getAsmdDbFields(), EntTlAssessmentdtl.getSaveArray());
		CommonMessage.debugMsg("DAO SQL : "+sql);
		List resultList = dbActionTemplate.getDataList(sql.toString());
		return  resultList;
	}
	
	@Override
	public List<String[]> getMainGrid(CommonFilter commonFilter) throws Exception {	
		try
		{
			
			List<String> paramValues = new ArrayList<String>();		
			//String condParms =FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			String condParams=FilterCondSql.getETRelatedStr(commonFilter);
			CommonMessage.debugMsg("commonParams");
			paramValues.add(condParams);
			paramValues.add(commonParams);
			
			return dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_CLASSTAROLE", paramValues);						
			//return operator;			
		}
		
		catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}
	
	@Override
	public List<String[]> getAssessmentGrid(CommonFilter commonFilter,String trainingAreaId,String roleId,String evlType) throws Exception {
		List<String> paramValues = new ArrayList<String>();		
		String sql="";
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		sql="ROLEID="+roleId+";TRAININGAREAID="+trainingAreaId+";";
		if(CommonFunctions.isValidKeyId(evlType))
			sql=sql+"EVALUATIONTYPE="  +evlType +";";
		if(CommonFunctions.isValidKeyId(commonFilter.getAssType()))
			sql=sql+"ASSTYPE="  +commonFilter.getAssType() +";";
		paramValues.add(sql);
		paramValues.add(commonParams);
		return dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_ROLEEMPEVALUATION", paramValues);	
	}
	
	@Override
	public List<String[]> getAll(CommonFilter commonFilter) throws Exception {
		
			CommonMessage.debugMsg("inside dao Impl");
			List<String> params = new ArrayList<String>();				
			String sql = entTlAssessmentmstSql.getAll(commonFilter);
			CommonMessage.debugMsg("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);			
			return operator;
	}
	
	@Override
	public int selectCount(CommonFilter commonFilter) throws Exception {		
		//CommonMessage.debugMsg("Before fetch the data");			
		String sql = entTlAssessmentmstSql.getCountAll(commonFilter);
		CommonMessage.debugMsg("Count sql....."+sql);
		String returnData = dbActionTemplate.getSingleValue(sql);
		int retData = Integer.parseInt(returnData);
		CommonMessage.debugMsg(retData);
		return  retData;
		
	}
	
	@Override
	public int selectAssessmentCount(CommonFilter commonFilter,String progId,String batchId) throws Exception {		
		//CommonMessage.debugMsg("Before fetch the data");
		String sql = entTlAssessmentmstSql.getAssessmentCount(commonFilter,progId,batchId);
		CommonMessage.debugMsg("Count sql....."+sql);
		String returnData = dbActionTemplate.getSingleValue(sql);
		int retData = Integer.parseInt(returnData);
		CommonMessage.debugMsg(retData);
		return  retData;		
	}
	
	@Override
	public String getEvaluationNo(EntTlAssessmentmst newEntTlAssessmentmst)throws Exception {
		String sql = entTlAssessmentmstSql.getEvaluationNo(entTlAssessmentmstSql.getAsmmDbFields(), newEntTlAssessmentmst.getSaveArray());
		String returnData = dbActionTemplate.getSingleValue(sql);		
		CommonMessage.debugMsg(returnData);
		return  returnData;
	}
	
	@Override
	public EntTlAssessmentmst getEvaluationLatest(EntTlAssessmentmst newEntTlAssessmentmst)throws Exception {
		String sql = entTlAssessmentmstSql.getEvaluationLatest(entTlAssessmentmstSql.getAsmmDbFields(), newEntTlAssessmentmst.getSaveArray());
		List resultList = dbActionTemplate.getDataList(sql.toString());
		return  fillMaster(resultList) ;	
	}
	
	@Override
	public EntTlAssessmentdtl getAssessmentDetails(EntTlAssessmentdtl entTlAssessmentdtl)throws Exception {
		EntTlAssessmentdtl newEntTlAssessmentdtl=new EntTlAssessmentdtl(); 
		
		String sql = entTlAssessmentdtlSql.getSelectSql(entTlAssessmentdtlSql.getAsmdDbFields(), entTlAssessmentdtl.getSaveArray());				
		CommonMessage.debugMsg("sql:"+sql);	
		List resultList = dbActionTemplate.getDataList(sql.toString());
		return  fillDetails(resultList) ;	
	}	
	
	@Override
	public List<String[]> getCheckList(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl)throws Exception {		 
		String sql = entTlAssessmentmstSql.getCheckList(entTlAssessmentmstSql.getAsmmDbFields(), entTlAssessmentmst.getSaveArray(),entTlAssessmentdtl.getAsmdTopiKeyid(),entTlAssessmentdtl.getAsmdKeyid(),entTlAssessmentdtl.getAsmdCurrentRate());				
		CommonMessage.debugMsg("sql:"+sql);	
		List resultList = dbActionTemplate.getDataList(sql.toString());
		return  resultList ;	
	}
	
	public List<String[]> getAssessmentDetailsgridData(EntTlAssessmentmst entTlAssessmentmst)throws Exception{
		CommonFilter commonFilter=new CommonFilter();
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		List<String > paramValues = new ArrayList<String>();
		/*entTlAssessmentmst.setAsmmEmpmKeyid(empmKeyid);
		entTlAssessmentmst.setAsmmRoleKeyid(roleKeyid);*/		
		CommonMessage.debugMsg("EntTlAssessmentmst.getAsmmEmpmKeyid()"+entTlAssessmentmst.getAsmmEmpmKeyid());
		CommonMessage.debugMsg("EntTlAssessmentmst.getAsmmRoleKeyid()"+entTlAssessmentmst.getAsmmRoleKeyid());
		CommonMessage.debugMsg("getAsmmEvaluationNo()"+entTlAssessmentmst.getAsmmEvaluationNo());
		String sql="";
		if(CommonFunctions.isValidKeyId(entTlAssessmentmst.getAsmmEmpmKeyid()))
			sql="EMPLOYEEID="+entTlAssessmentmst.getAsmmEmpmKeyid()+";";
		if(CommonFunctions.isValidKeyId(entTlAssessmentmst.getAsmmEmpmKeyid()))
			sql=sql+"ROLEID="+entTlAssessmentmst.getAsmmRoleKeyid()+";";
		if(CommonFunctions.isValidKeyId(entTlAssessmentmst.getAsmmEvaluationNo()))
			sql=sql+ "EVALUATIONNO="+entTlAssessmentmst.getAsmmEvaluationNo()+ ";";
		if(CommonFunctions.isValidKeyId(entTlAssessmentmst.getAsmmEvaluationDate()))
			sql=sql+"EVALUATIONDATE="+entTlAssessmentmst.getAsmmEvaluationDate()+";";
		if(CommonFunctions.isValidKeyId(entTlAssessmentmst.getAsmmIsLocked()))
			sql=sql+"ISLOCKED="+entTlAssessmentmst.getAsmmIsLocked()+";";
		if(CommonFunctions.isValidKeyId(entTlAssessmentmst.getAsmmEvaluationType()))
			sql=sql+"EVALUATIONTYPE="  +entTlAssessmentmst.getAsmmEvaluationType() +";";
		if(CommonFunctions.isValidKeyId(entTlAssessmentmst.getAsmmTrarKeyid()))
			sql=sql+"TRAININGAREAID="  +entTlAssessmentmst.getAsmmTrarKeyid() +";";
		
		paramValues.add(sql);
		//paramValues.add("");
		paramValues.add("FILTERCOND=;ISTOTALCNT=;");
		List<String[]> resultList;	
		resultList = dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_TAROLEEMPLOYEE", paramValues);
		//CommonMessage.debugMsg(" resultList size " + resultList.size());
		return resultList;
	}
	
	public List<String[]> getTrnAreaRoleEmpView(EntTlAssessmentmst entTlAssessmentmst)throws Exception{
		CommonFilter commonFilter=new CommonFilter();
		String sql="";
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		List<String > paramValues = new ArrayList<String>();
		//paramValues.add("EMPLOYEEID="+entTlAssessmentmst.getAsmmEmpmKeyid()+";ROLEID="+entTlAssessmentmst.getAsmmRoleKeyid()+";");
		sql="EMPLOYEEID="+entTlAssessmentmst.getAsmmEmpmKeyid()+";ROLEID="+entTlAssessmentmst.getAsmmRoleKeyid()+";";	
		CommonMessage.debugMsg(" EVALUATIONTYPE : " + entTlAssessmentmst.getAsmmEvaluationType());
		if(CommonFunctions.isValidKeyId(entTlAssessmentmst.getAsmmEvaluationType()))
			sql=sql+"EVALUATIONTYPE="  +entTlAssessmentmst.getAsmmEvaluationType() +";";
		paramValues.add(sql);
		paramValues.add(commonParams);
		List<String[]> resultList;	
		resultList = dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_PREVEMPEVALUATION", paramValues);
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return resultList;
	}
	
	@SuppressWarnings("static-access")
	private String addEntTlAssessmentdtl(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl) throws Exception {
		String sql=null;		
		entTlAssessmentdtl.setAsmdAsmmKeyid(entTlAssessmentmst.getAsmmKeyid());
		if(!CommonFunctions.isValidKeyId(entTlAssessmentdtl.getAsmdKeyid())){
			entTlAssessmentdtl.setAsmdKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_ENT_TL_ASSESSMENTDTL, 12, "ASD", null,null)); // set the sequnce number
			sql=entTlAssessmentdtlSql.getInsertSql(entTlAssessmentdtlSql.getAsmdDbFields(),entTlAssessmentdtl.getSaveArray());
		}
		else{
			sql=entTlAssessmentdtlSql.getUpdateSql(entTlAssessmentdtlSql.getAsmdDbFields(),entTlAssessmentdtl.getSaveArray());
		}			
		return sql;
	}
	
	private String addEntTlAssessmentChecklist(EntTlAssessmentdtl entTlAssessmentdtl,EntTlAssessmentChecklist entTlAssessmentChecklist) throws Exception {
		String sql=null;		
		entTlAssessmentChecklist.setAsclAsmdKeyid(entTlAssessmentdtl.getAsmdKeyid());
		if(!CommonFunctions.isValidKeyId(entTlAssessmentChecklist.getAsclKeyid())){
			entTlAssessmentChecklist.setAsclKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_ENT_TL_ASSESSMENT_CHECKLIST, 12, "ASC", null,null)); // set the sequnce number
			sql=entTlAssessmentChecklistSql.getInsertSql(entTlAssessmentChecklistSql.getAsclDbFields(),entTlAssessmentChecklist.getSaveArray());
		}
		else{
			CommonMessage.debugMsg("getAsclActive:"+entTlAssessmentChecklist.getAsclActive());
			if(entTlAssessmentChecklist.getAsclActive().equals("Y"))
				sql=entTlAssessmentChecklistSql.getUpdateSql(entTlAssessmentChecklistSql.getAsclDbFields(),entTlAssessmentChecklist.getSaveArray());
			else if(entTlAssessmentChecklist.getAsclActive().equals("N"))
				sql=entTlAssessmentChecklistSql.getDeleteSql(entTlAssessmentChecklistSql.getAsclDbFields(),entTlAssessmentChecklist.getSaveArray());
				 //sql = "DELETE from " + TableNames.TBL_ENT_TL_ASSESSMENT_CHECKLIST + " where ASCL_KEYID='" + entTlAssessmentChecklist.getAsclKeyid() + "'";
			
		}	
		CommonMessage.debugMsg("Checklist sql" + sql);
		return sql;
	}
	
	private List<EntTlAssessmentdtl> fillDetailsList(List<String[]> resultList)throws SQLException {
		List<EntTlAssessmentdtl> entTlAssessmentdtl = new ArrayList<EntTlAssessmentdtl>();
		for (String[] row : resultList) {
			EntTlAssessmentdtl newEntTlAssessmentdtl = new EntTlAssessmentdtl();
			newEntTlAssessmentdtl.setAsmdKeyid(row[0]);
			newEntTlAssessmentdtl.setAsmdAsmmKeyid(row[1]);
			newEntTlAssessmentdtl.setAsmdSpokKeyid(row[2]);	
			newEntTlAssessmentdtl.setAsmdTopiKeyid(row[3]);				
			newEntTlAssessmentdtl.setAsmdCutoff(row[4]);	
			newEntTlAssessmentdtl.setAsmdScore(row[5]);	
			newEntTlAssessmentdtl.setAsmdResult(row[6]);
			newEntTlAssessmentdtl.setAsmdProgKeyid(row[7]);
			newEntTlAssessmentdtl.setAsmdBachKeyid(row[8]);			
			newEntTlAssessmentdtl.setAsmdCurrentRate(row[9]);	
			newEntTlAssessmentdtl.setAsmdPreviousRate(row[10]);	
			entTlAssessmentdtl.add(newEntTlAssessmentdtl);
		}
		return entTlAssessmentdtl;
	}
	
	private EntTlAssessmentdtl fillDetails(List<String[]> resultList)throws SQLException {
		EntTlAssessmentdtl newEntTlAssessmentdtl = new EntTlAssessmentdtl();
		for (String[] row : resultList) {
			newEntTlAssessmentdtl.setAsmdKeyid(row[0]);
			newEntTlAssessmentdtl.setAsmdAsmmKeyid(row[1]);
			newEntTlAssessmentdtl.setAsmdSpokKeyid(row[2]);	
			newEntTlAssessmentdtl.setAsmdTopiKeyid(row[3]);				
			newEntTlAssessmentdtl.setAsmdCutoff(row[4]);	
			newEntTlAssessmentdtl.setAsmdScore(row[5]);	
			newEntTlAssessmentdtl.setAsmdResult(row[6]);
			newEntTlAssessmentdtl.setAsmdProgKeyid(row[7]);
			newEntTlAssessmentdtl.setAsmdBachKeyid(row[8]);			
			newEntTlAssessmentdtl.setAsmdCurrentRate(row[9]);	
			newEntTlAssessmentdtl.setAsmdPreviousRate(row[10]);	
		}
		return newEntTlAssessmentdtl;
	}
	
	private EntTlAssessmentmst fillMaster(List<String[]> resultList)throws SQLException {
		EntTlAssessmentmst newEntTlAssessmentmst = new EntTlAssessmentmst();
		for (String[] row : resultList) {
			newEntTlAssessmentmst.setAsmmKeyid(row[0]);
			newEntTlAssessmentmst.setAsmmEvaluationDesc(row[1]);	
			newEntTlAssessmentmst.setAsmmEvaluationDate(row[2]);
			newEntTlAssessmentmst.setAsmmEvaluationNo(row[3]);
			newEntTlAssessmentmst.setAsmmEvaluationType(row[4]);
			newEntTlAssessmentmst.setAsmmIsLocked(row[5]);	
			newEntTlAssessmentmst.setAsmmTrarKeyid(row[6]);
			newEntTlAssessmentmst.setAsmmRoleKeyid(row[7]);	
			newEntTlAssessmentmst.setAsmmEmpmKeyid(row[8]);
		}
		return newEntTlAssessmentmst;
	}	
	
	private List<EntTlAssessmentChecklist> fillCheckList(List<String[]> resultList)throws SQLException {
		List<EntTlAssessmentChecklist> entTlAssessmentChecklist = new ArrayList<EntTlAssessmentChecklist>();
		for (String[] row : resultList) {
			EntTlAssessmentChecklist newEntTlAssessmentChecklist = new EntTlAssessmentChecklist();
			newEntTlAssessmentChecklist.setAsclChekKeyid(row[0]);			
			entTlAssessmentChecklist.add(newEntTlAssessmentChecklist);
		}
		return entTlAssessmentChecklist;
	}
	
	@Override
	public Workbook getTrnAreaRoleEmpexcel(EntTlAssessmentmst entTlAssessmentmst,CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
	   ResultSet rs = null;
	   try{
		rs =   getTrnAreaResultSet(entTlAssessmentmst,commonFilter);
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
	}
	
	@Override
	public Workbook getAssesmentListexcel(EntTlAssessmentmst entTlAssessmentmst,CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
	   ResultSet rs = null;
	   try{
		
		rs =   getAssesmentListResultSet(entTlAssessmentmst,commonFilter);
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
	}
	
	private ResultSet getAssesmentListResultSet(EntTlAssessmentmst entTlAssessmentmst,CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getAssesmentListParamValues(entTlAssessmentmst,commonFilter);
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_ROLEEMPEVALUATION", paramValues);
	}
	
	private List<String> getAssesmentListParamValues(EntTlAssessmentmst entTlAssessmentmst,CommonFilter commonFilter){
		
		List<String> paramValues = new ArrayList<String>();	
		
		String trnAreaId=entTlAssessmentmst.getAsmmTrarKeyid();
		String roleId=entTlAssessmentmst.getAsmmRoleKeyid();
		String condParms = "ROLEID="+roleId+";";
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		
		//paramValues.add(commonParams);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
	private ResultSet getTrnAreaResultSet(EntTlAssessmentmst entTlAssessmentmst,CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(entTlAssessmentmst,commonFilter);
		
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_PREVEMPEVALUATION", paramValues);
	}
	
	private List<String> getFilterParamValues(EntTlAssessmentmst entTlAssessmentmst,CommonFilter commonFilter){
		
		List<String> paramValues = new ArrayList<String>();	
		
		String empId=entTlAssessmentmst.getAsmmEmpmKeyid();
		String roleId=entTlAssessmentmst.getAsmmRoleKeyid();
		String condParms = "EMPLOYEEID="+empId+";ROLEID="+roleId+";";
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		
		//paramValues.add(commonParams);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
	@Override
	public Workbook getbatchProgexcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
	   ResultSet rs = null;
	   try{
		
		rs =   getbatchProgResultSet(commonFilter);
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
	}
	
	@Override
	public String selectTrainingAreaPath(EntTlAssessmentmst entTlAssessmentmst)throws Exception{
		String trainningPath =null;
		String employee =null;
		String role =null;
		String sql=null;
		String returnData =null;
		sql = entTlAssessmentmstSql.getTrainingAreaPath(entTlAssessmentmstSql.getAsmmDbFields(), entTlAssessmentmst.getSaveArray());				
		CommonMessage.debugMsg("TrainingArea sql:"+sql);			
		trainningPath = dbActionTemplate.getSingleValue(sql);
		sql = entTlAssessmentmstSql.getRoleName(entTlAssessmentmstSql.getAsmmDbFields(), entTlAssessmentmst.getSaveArray());				
		CommonMessage.debugMsg("Role sql:"+sql);			
		role = dbActionTemplate.getSingleValue(sql);
		sql = entTlAssessmentmstSql.getEmployeeName(entTlAssessmentmstSql.getAsmmDbFields(), entTlAssessmentmst.getSaveArray());				
		CommonMessage.debugMsg("Employee sql:"+sql);			
		employee = dbActionTemplate.getSingleValue(sql);
		trainningPath=trainningPath.substring(1, trainningPath.length());
		returnData=trainningPath + "                -                   " + role  + "                   -                  " + employee;
		CommonMessage.debugMsg(returnData);
		return  returnData;
	}
	
	private ResultSet getbatchProgResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getbatchProgfilter(commonFilter);
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_CLASSTAROLE", paramValues);
	}
	
	private List<String> getbatchProgfilter(CommonFilter commonFilter){		
		List<String> paramValues = new ArrayList<String>();	
		String condParms = "";
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);		
		//paramValues.add(commonParams);
		paramValues.add(commonParams);		
		return paramValues;
	}	
	public String getSkillRating(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl)throws Exception{
		String rating="";
		String[] ratingArr;
		CommonMessage.debugMsg("skill ratting Dao impl");
		CommonMessage.debugMsg("---"+entTlAssessmentdtl.getAsmdTopiKeyid()+"----"+entTlAssessmentdtl.getAsmdScore()+"----"+entTlAssessmentdtl.getAsmdResult());
		String sql=entTlAssessmentmstSql.getSkillRating(entTlAssessmentmstSql.getAsmmDbFields(),
				entTlAssessmentmst.getSaveArray(),entTlAssessmentdtl.getAsmdTopiKeyid(),
				entTlAssessmentdtl.getAsmdScore(),entTlAssessmentdtl.getAsmdResult());
		CommonMessage.debugMsg("getSkillRating sql:"+sql);
		rating = dbActionTemplate.getSingleValue(sql);
		
		CommonMessage.debugMsg("getSkillRating :"+rating);
		return rating;
	}
	
	public String getPreviousRating(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl)throws Exception{
		String rating="";
		String[] ratingArr;
		
		String sql=entTlAssessmentmstSql.getPreviousRating(entTlAssessmentmstSql.getAsmmDbFields(),
				entTlAssessmentmst.getSaveArray(),entTlAssessmentdtl.getAsmdTopiKeyid());
		
		CommonMessage.debugMsg("getPreviousRating sql:"+sql);
		
		List resultList = dbActionTemplate.getDataList(sql);
		if (resultList.size()>0){							
			ratingArr=(String[]) resultList.get(0);	
			rating=ratingArr[0];		
		}
		CommonMessage.debugMsg("getPreviousRating :"+rating);
		return rating;
	}

	@Override
	public String getMaxEvlDate(EntTlAssessmentmst entTlAssessmentmst) {
		String maxDate="";
		String sql=entTlAssessmentmstSql.getMaxEvlDate(entTlAssessmentmstSql.getAsmmDbFields(),entTlAssessmentmst.getSaveArray());
		CommonMessage.debugMsg("sql:"+sql);
		try {
			maxDate = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		CommonMessage.debugMsg("maxDate:"+maxDate);
		return maxDate;
	}

	@Override
	public String getMinEvlDate(EntTlAssessmentmst entTlAssessmentmst) {
		String minDate="";
		String sql=entTlAssessmentmstSql.getMinEvlDate(entTlAssessmentmstSql.getAsmmDbFields(),entTlAssessmentmst.getSaveArray());
		CommonMessage.debugMsg("sql:"+sql);
		try {
			minDate = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		CommonMessage.debugMsg("minDate:"+minDate);
		return minDate;
	}

	@Override
	public Boolean preEvlExists(EntTlAssessmentmst entTlAssessmentmst) {
		String evlCount="";
		String sql=entTlAssessmentmstSql.preEvlExists(entTlAssessmentmstSql.getAsmmDbFields(),entTlAssessmentmst.getSaveArray());
		CommonMessage.debugMsg("sql:"+sql);
		try {
			evlCount = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		CommonMessage.debugMsg("evlCount:"+evlCount);
		if(Integer.parseInt(evlCount)>0)
			return true;		
		else
			return false;
		
	}

	@Override
	public String getCutOff(String assEmpKeyId, String topicId,String assRoleKeyId, String evalType,String mode, String trarkey, String result, String asmdKeyId) throws Exception {
		// TODO Auto-generated method stub
		String getcutOf=entTlAssessmentmstSql.getMinCutOff(assEmpKeyId,topicId,assRoleKeyId,evalType, mode,trarkey,  result,  asmdKeyId);
		List<String[]> resSetCutOf = dbActionTemplate.getDataList(getcutOf);
		String resCutOf = "0";
		if(resSetCutOf.size()>0)
			resCutOf = resSetCutOf.get(0)[0]+"/"+resSetCutOf.get(0)[1];
		CommonMessage.debugMsg("resCutOf  :"+resCutOf);
		
			
		
		return resCutOf;
	}

	@Override
	public void checkEvalDateEqCurrentDate(String asmmEvaluationType,
			String asmmEvaluationDate, String asmmEmpmKeyid) throws Exception {
		// TODO Auto-generated method stub
		String sql ="select count(ASMM_EMPM_KEYID) from ENT_TL_ASSESSMENTMST where  ASMM_EMPM_KEYID = '"+asmmEmpmKeyid+"' AND  to_date(ASMM_EVALUATION_DATE )='"+asmmEvaluationDate+"' AND ASMM_EVALUATION_TYPE ='"+asmmEvaluationType+"'";
		String countEvalDate = dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg("countEvalDate  "+countEvalDate);
		if(Integer.parseInt(countEvalDate )>0){
			throw new ValidationExceptions("EvalDateExist,"); 
			
		}
	}

	@Override
	public String getpreExist(String assEmpKeyId, String topicId, String evalType)
			throws Exception {
		// TODO Auto-generated method stub
		if(evalType.equals("POS") ){
			String checkPresql = "Select ASMM_EVALUATION_TYPE from ENT_TL_ASSESSMENTMST,ENT_TL_ASSESSMENTDTL where ";
			checkPresql += "ASMM_KEYID = ASMD_ASMM_KEYID AND  ASMM_EMPM_KEYID ='"+assEmpKeyId+"' AND ASMD_TOPI_KEYID ='"+topicId+"' AND ASMM_EVALUATION_TYPE='PRE'";
			CommonMessage.debugMsg("checkPresql  :"+checkPresql);
			String checkPreExist = dbActionTemplate.getSingleValue(checkPresql);
			if(!UIUtils.isValidKeyId(checkPreExist)){
				 throw new BusinessApplicationExceptions("Warning! Pre Training Assessment Does Not Exist For This Employee");
			}
	  }
		return null;
	}

	@Override
	public String getProgid(String batchId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT BACH_PROG_KEYID from ENT_TL_BATCHMST where BACH_KEYID = '"+batchId+"'";
		return dbActionTemplate.getSingleValue(sql);
	}

	@Override
	public List<String[]> getMultiSkillfillgriddata(String flid) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
	    
	 
		sql.append(  "  SELECT EMPM_NAME,EMPM_ROLEID FROM GEN_TL_FNLNROLETEAM,GEN_TL_EMPLOYEEMST");
		    
		sql.append(  "    WHERE  FRT_EMPM_KEYID =EMPM_KEYID AND  FRT_FNLN_KEYID='"+flid+"'");
		/*String sql1 ="";//select '1','Area 1' from dual union all  select '1','Area 1' from dual;
		sql.append(" select ' ','Employee 1','1 ',' ',' ','1 ',' ',' ',' ',' ',' ',' ' from dual union all");
		sql.append(" select ' ','Employee 2',' ',' 1',' ',' ','1 ',' ',' ',' ',' ',' ' from dual union all");
		sql.append(" select ' ','Employee 3',' ',' ','1 ',' ','1 ',' ',' ',' ',' 1',' ' from dual union all");
		sql.append(" select ' ','Employee 4',' ',' ',' ',' ',' ',' ',' ',' ',' ',' 1' from dual union all");
	    sql.append(" select ' ','Employee 5',' ',' ','1 ',' ','1 ',' ',' ',' ',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 6',' ',' ',' ','1 ',' ',' ',' ',' ',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 7',' ',' ','1 ',' ','1 ',' ',' ',' 1',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 8',' ','1 ',' ',' ',' ',' ',' 1',' ',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 9',' ','1 ',' ',' ',' 1',' ',' ',' ',' ',' ' from dual ");
	    sql = sql.toString();*/
		CommonMessage.debugMsg("sql..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		//CommonMessage.debugMsg("Grid value" + gridData.get(1));
		return gridData;
	}

	@Override
	public List<String[]> getMultiSkillAssessmentfillgriddata()
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		String sql1 ="";//select '1','Area 1' from dual union all  select '1','Area 1' from dual;
		sql.append(" select ' ','Employee 1','1 ',' ','1 ',' ',' ',' ',' ',' ',' ',' ' from dual union all");
		sql.append(" select ' ','Employee 2',' ',' ',' 1',' ',' 1',' ','1 ',' ',' ',' ' from dual union all");
		sql.append(" select ' ','Employee 3','1 ','1 ',' ',' ',' ',' ',' ',' ',' 1',' ' from dual union all");
		sql.append(" select ' ','Employee 4','1 ','1 ',' ','1 ',' ',' ',' ',' ',' ',' 1' from dual union all");
	    sql.append(" select ' ','Employee 5',' ',' ','1 ',' ',' 1',' ',' ',' ',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 6',' ',' ',' ','1 ',' ',' ',' ',' ',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 7',' 1',' ',' ',' ',' ',' ',' ',' 1',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 8',' ',' ',' ',' ',' ',' ',' 1',' ',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 9',' ','1 ','1 ',' ',' 1',' ',' ',' ',' ',' ' from dual union all ");
	    sql.append(" select ' ','Employee 10',' ',' ',' ',' 1',' ',' ',' ','1 ',' ',' 1' from dual");
	    
	    sql1 = sql.toString();
		CommonMessage.debugMsg("sql..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql1);
		CommonMessage.debugMsg("Grid value" + gridData.get(1));
		return gridData;
	}

	@Override
	public List<String[]> getEmployeerole(CommonFilter commonFilter)
			throws Exception {
		//String KEYID = commonFilter.getKey() ;
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		//condParms=condParms+";KEYID="+KEYID+";";
		//paramValues.add(condParms);
		//paramValues.add(commonParams);

		//String flids ="";
		//String batchId ="";
		//String month ="JAN-1801";
		
		//if (UIUtils.isValidKeyId(commonFilter.getProgram()))
			//progId=commonFilter.getProgram();
		//if (UIUtils.isValidKeyId(commonFilter.getBK()))
			//batchId=commonFilter.getBK();
		//if (UIUtils.isValidKeyId(commonFilter.getFromMonth()))
			//month=commonFilter.getFromMonth();
		
		//condParms+= "PROGID="+progId+";BATCHID="+batchId+";FORMONTH="+month+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_MULTISKILLEMP", paramValues);
		
		CommonMessage.debugMsg("test to............");
		
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
	
		return dataList;
	}

	@Override
	public EntTlMultiskillempmap create(
			EntTlMultiskillempmap newEntTlMultiskillempmap) throws Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlMultiskillempmapSql  entTlMultiskillempmapSql  = new EntTlMultiskillempmapSql (); 
		//lstEntTlMultiskillempmap=null;
	 try
		  {
			for(int i=0;i<newEntTlMultiskillempmap.getEntTlMultiskillempmap().size();i++)
			{
		    newEntTlMultiskillempmap.setMuseKeyid(dbActionTemplate.getSequenceNumber(EntTlMultiskillempmapSql.TBL_ENT_TL_MULTISKILLEMPMAP, 10, "MUSE", "", "Y")); // set the sequnce number 
			sqls.add(EntTlMultiskillempmapSql.getInsertSql(entTlMultiskillempmapSql.getMuseDbFields(), newEntTlMultiskillempmap.getSaveArray())); // add insert sql for master table
			
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		  }
		catch(Exception e)
		{
           e.printStackTrace();
		}
		return newEntTlMultiskillempmap;
	}

	@Override
	public EntTlMultiskillempmap create(
			List<EntTlMultiskillempmap> lstEntTlMultiskillempmap)
			throws Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlMultiskillempmap  newEntTlMultiskillempmap=null;
		EntTlMultiskillempmapSql  entTlMultiskillempmapSql  = new EntTlMultiskillempmapSql ();
		try
		{
			int i = 0;
			//if(newEntTlMultiskillempmap.getMuseFlid() !=null || newEntTlMultiskillempmap.getMuseEmployeeid() !=null) 
			//{
			//sqls.add(EntTlMultiskillempmapSql.getDeleteSql(entTlMultiskillempmapSql.getMuseDbFields(), newEntTlMultiskillempmap.getSaveArray()));
			//}
		  // sqls.add(" Delete  from ENT_TL_MULTISKILLEMPMAP where muse_keyid = "+newEntTlMultiskillempmap+" "); // 
			sqls.add("DELETE FROM "+entTlMultiskillempmapSql.TBL_ENT_TL_MULTISKILLEMPMAP+" WHERE MUSE_Flid ='"+lstEntTlMultiskillempmap.get(i).getMuseFlid()+"'");
			
			//sqls.add("DELETE FROM "+entTlMultiskillempmapSql.TBL_ENT_TL_MULTISKILLEMPMAP+" WHERE MUSE_Flid ='"+lstEntTlMultiskillempmap.get(0).getMuseFlid()+"' and   MUSE_EMPLOYEEID='"+lstEntTlMultiskillempmap.get(0).getMuseEmployeeid()+"'");
	       
		   
		   for (EntTlMultiskillempmap Multiskillempmap : lstEntTlMultiskillempmap)
			{
	    	  // sqls.add(" Delete  from ENT_TL_MULTISKILLEMPMAP where muse_flid ="+Multiskillempmap+" ");
	    	   if( ! UIUtils.isValidKeyId (Multiskillempmap.getMuseKeyid()))
	    	   {
				CommonMessage.debugMsg("daoimpl44");
			    Multiskillempmap.setMuseKeyid(dbActionTemplate.getSequenceNumber(EntTlMultiskillempmapSql.TBL_ENT_TL_MULTISKILLEMPMAP,10, "MUSE", "", "Y")); // set the sequnce number
				 sqls.add(EntTlMultiskillempmapSql.getInsertSql(entTlMultiskillempmapSql.getMuseDbFields(), Multiskillempmap.getSaveArray())); // add insert sql for master table
			  }
	    	/* else
			 {
	    		 CommonMessage.debugMsg("Delete");
				 sqls.add(EntTlMultiskillempmapSql.getDeleteSql(entTlMultiskillempmapSql.getMuseDbFields(), Multiskillempmap.getSaveArray())); // add insert sql for master table
			 }*/
			}
	        dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		   
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public EntTlMultiskillempmap deleteMultiSkill(
			EntTlMultiskillempmap newEntTlMultiskillempmap) throws Exception {
		List<String> sqls = new ArrayList<String>();
		EntTlMultiskillempmapSql  entTlMultiskillempmapSql  = new EntTlMultiskillempmapSql();
		try {
		
			sqls.add(EntTlMultiskillempmapSql.getDeleteSql(entTlMultiskillempmapSql.getMuseDbFields(), newEntTlMultiskillempmap.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
            e.printStackTrace();
		}
		return newEntTlMultiskillempmap;
	}

	@Override
	public List<String[]> getemployeeMaster(CommonFilter commonFilter)
			throws Exception {
		String KEYID = commonFilter.getKey() ;
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		condParms=condParms+";KEYID="+KEYID+";";
		//paramValues.add(condParms);
		//paramValues.add(commonParams);
        paramValues.add(condParms);
		paramValues.add(commonParams);
		List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_MULTISKILLGRID", paramValues);
		CommonMessage.debugMsg("test to............");
		
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
	
		return dataList;
	}

	@Override
	public EntTlMultiskillempmap selectmaster(String flid) throws Exception {
		
		try{
			EntTlMultiskillempmapSql  entTlMultiskillempmapSql  = new EntTlMultiskillempmapSql();
			EntTlMultiskillempmap  newEntTlMultiskillempmap=new EntTlMultiskillempmap();//model
			String sql = entTlMultiskillempmapSql.selectmaster();
			Object [] args =  new Object [] {flid };
			CommonMessage.debugMsg("sql:::::"+sql);
			CommonMessage.debugMsg("keyid:::::"+flid);
			newEntTlMultiskillempmap.setSaveArray(dbActionTemplate.getDataArr(sql,args));
	    	//CommonMessage.debugMsg(sql);
			//CommonMessage.debugMsg(keyId);
			return newEntTlMultiskillempmap;
          }catch(Exception e){
			
		}
		return null;
	}

	@Override
	public List<String[]> getemployeeDate(CommonFilter commonFilter)
			throws Exception {
		  
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		
		String date = commonFilter.getFromDate();
		if(UIUtils.isValidKeyId(date))
			condParms +=";Date="+date;
		CommonMessage.debugMsg("condParams=="+condParms);
	
	
		 paramValues.add(condParms);
         paramValues.add(commonParams);
	     List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_MULTISKILLEMPDATE", paramValues);
		
		CommonMessage.debugMsg("test to............");
		
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
	
		return dataList;
	}

	@Override
	public Workbook getmultiSkillExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		 try
		 {
				rs =   getmultiResultSet(commonFilter);
				CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
				
				CommonMessage.debugMsg("daoimpl");
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				
				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(254,0,0)); //red font
				condFormat.setFontName("Wingdings");
				condFormat.setFontHeightPoint((short)104);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setFromCol(103);
				condFormat.setToCol(-10);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue( (char)252+""); //Tick
				condFormat.setIdentfier("tick");
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
				CommonMessage.debugMsg("daoimpl22222");
				return excelUtils.writeToExcel(rs,format,2,0,0 );
				
				
			   }finally{
				   
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }  
		}

	private ResultSet getmultiResultSet(CommonFilter commonFilter) throws Exception {
		
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg("result");
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_MULTISKILLGRID", paramValues);
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}

	@Override
	public List<String[]> getemployees(CommonFilter commonFilter)
			throws Exception {
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
        paramValues.add(condParms);
		paramValues.add(commonParams);
	     List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_MULTIDATEGRID", paramValues);
		
		CommonMessage.debugMsg("test to............");
		
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
	
		return dataList;
	}

	@Override
	public EntTlMultiskilldialymap selectFlid(String flid,String date) throws Exception {
		try{
			EntTlMultiskilldialymapSql   entTlMultiskilldialymapSql   = new EntTlMultiskilldialymapSql  ();
			CommonMessage.debugMsg(flid+" flid------------ date:::::"+date);
			EntTlMultiskilldialymap   newEntTlMultiskilldialymap =new EntTlMultiskilldialymap ();//model
			String sql = entTlMultiskilldialymapSql.selectFlid();
			Object [] args =  new Object [] {flid ,date};
			CommonMessage.debugMsg(date+" date sql:::::"+sql);
			CommonMessage.debugMsg("flid :::::"+flid);
			newEntTlMultiskilldialymap.setSaveArray(dbActionTemplate.getDataArr(sql,args));
	    	//CommonMessage.debugMsg(sql);
			//CommonMessage.debugMsg(keyId);
			return newEntTlMultiskilldialymap;
          }catch(Exception e){
			
		}
		return null;
	}

	@Override
	public Workbook getmultiSkillDate(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		 try
		 {
				rs =   getmultiSkillDate(commonFilter);
				CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
				
				CommonMessage.debugMsg("daoimpl");
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				
				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(254,0,0)); //red font
				condFormat.setFontName("Wingdings");
				condFormat.setFontHeightPoint((short)104);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setFromCol(103);
				condFormat.setToCol(-10);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue( (char)252+""); //Tick
				condFormat.setIdentfier("tick");
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
				CommonMessage.debugMsg("daoimpl22222");
				return excelUtils.writeToExcel(rs,format,2,0,0 );
				
				
			   }finally{
				   
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }  
		}

	private ResultSet getmultiSkillDate(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues1(commonFilter);
		CommonMessage.debugMsg("result");
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_MULTIDATEGRID", paramValues);
	}

	private List<String> getFilterParamValues1(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}

	@Override
	public List<String[]> getemployeeReport(CommonFilter commonFilter)
			throws Exception {
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
        paramValues.add(condParms);
		paramValues.add(commonParams);
	     List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST.ENT_FN_MULTISKILLREPORT", paramValues);
		
		CommonMessage.debugMsg("test to............");
		
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
	
		return dataList;
	}

	@Override
	public Workbook getmultiSkillReport(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		 try
		 {
				rs =   getmultiSkillReport(commonFilter);
				CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
				
				CommonMessage.debugMsg("daoimpl");
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				
				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(254,0,0)); //red font
				condFormat.setFontName("Wingdings");
				condFormat.setFontHeightPoint((short)104);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setFromCol(103);
				condFormat.setToCol(-10);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue( (char)252+""); //Tick
				condFormat.setIdentfier("tick");
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
				CommonMessage.debugMsg("daoimpl22222");
				return excelUtils.writeToExcel(rs,format,2,0,0 );
				
				
			   }finally{
				   
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }  
		}

	private ResultSet getmultiSkillReport(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValuesre(commonFilter);
		CommonMessage.debugMsg("result");
		return dbActionTemplate.dbFunctionCall("TEST_PC_TEST.ENT_FN_MULTISKILLREPORT", paramValues);
	}

	private List<String> getFilterParamValuesre(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}

	}


