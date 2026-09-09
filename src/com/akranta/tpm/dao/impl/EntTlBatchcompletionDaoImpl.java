package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.bean.ENTBatchComplnBean;
import com.akranta.tpm.dao.EntTlBatchcompletionDao;
import com.akranta.tpm.dao.sql.EntTlBatchEmployeeLinkSql;
import com.akranta.tpm.dao.sql.EntTlBatchcompletionSql;
import com.akranta.tpm.dao.sql.EntTlFeedbackRequestdtlSql;
import com.akranta.tpm.dao.sql.EntTlFeedbackRequestmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.WomTlCommunicationlogSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlBatchcompletion;
import com.akranta.tpm.model.EntTlFeedbackRequestdtl;
import com.akranta.tpm.model.EntTlFeedbackRequestmst;
import com.akranta.tpm.model.WomTlCommunicationlog;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Mail;

/* dao implementation */
public class EntTlBatchcompletionDaoImpl implements EntTlBatchcompletionDao {


	private DBActionTemplate dbActionTemplate; 
	private EntTlBatchcompletionSql entTlBatchcompletionSql;

	public EntTlBatchcompletionDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		entTlBatchcompletionSql = new EntTlBatchcompletionSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlBatchcompletion create(EntTlBatchcompletion entTlBatchcompletion,ENTBatchComplnBean entBatchComplnBean) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		//EntTlBatchcompletionSql entTlBatchcompletionSql = new EntTlBatchcompletionSql(); // contains dbtable,field names, Field types and related sqls  of master table
		String statusFlag = entBatchComplnBean.getStatusFlag();
		
		
			entTlBatchcompletion.setBcomKeyid(dbActionTemplate.getSequenceNumber("ENT_TL_BATCHCOMPLETION",10,"BCM","YYMM","Y")); // set the sequnce number 
			sqls.add(EntTlBatchcompletionSql.getInsertSql(entTlBatchcompletionSql.getBcomDbFields(), entTlBatchcompletion.getSaveArray())); // add insert sql for master table
			String employeeCount = dbActionTemplate.getSingleValue(entTlBatchcompletionSql.getEmployeeCount(entTlBatchcompletion.getBcomBachKeyid()));
			if(CommonFunctions.isValidKeyId(statusFlag) && statusFlag.length() >1)
				sqls.add(EntTlBatchcompletionSql.updateProgCalendar(entTlBatchcompletion.getBcomProgKeyid(), entTlBatchcompletion.getBcomEndDate(), employeeCount));
				//sqls.add(EntTlBatchcompletionSql.updateProgCal(entTlBatchcompletion.getBcomProgKeyid()));
			sqls.add(EntTlBatchcompletionSql.updateBatchMst(entTlBatchcompletion.getBcomStatus(),entTlBatchcompletion.getBcomBachKeyid(),entTlBatchcompletion.getBcomEntryDate()));
			sqls.add(EntTlBatchcompletionSql.updateBatchSchedule(entTlBatchcompletion.getBcomBachKeyid()));
			insertFBReqMstValues(entTlBatchcompletion,entBatchComplnBean,sqls);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
	
		return entTlBatchcompletion;
	}
	
	public EntTlBatchcompletion update(EntTlBatchcompletion entTlBatchcompletion)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		//EntTlBatchcompletionSql entTlBatchcompletionSql = new EntTlBatchcompletionSql();
		try {

			sqls.add(EntTlBatchcompletionSql.getUpdateSql(entTlBatchcompletionSql.getBcomDbFields(), entTlBatchcompletion.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlBatchcompletion;
	}
	
	public EntTlBatchcompletion delete(EntTlBatchcompletion entTlBatchcompletion)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		//EntTlBatchcompletionSql entTlBatchcompletionSql = new EntTlBatchcompletionSql();
		try {
			
			sqls.add(EntTlBatchcompletionSql.getDeleteSql(entTlBatchcompletionSql.getBcomDbFields(), entTlBatchcompletion.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlBatchcompletion;
	}
	public EntTlBatchcompletion updateMailSent(EntTlBatchcompletion entTlBatchcompletion,String dtlId) throws Exception
	{
		String updateMailSentSQL = EntTlBatchcompletionSql.updateMailSent(dtlId);
		CommonMessage.debugMsg("lAST : "+updateMailSentSQL);
		List<String> sqls = new ArrayList<String>();
		try {
			
		
				sqls.add(updateMailSentSQL);
				dbActionTemplate.executeStatements(sqls);
			
				
		}
		catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlBatchcompletion;
	}
	
	public List<String[]> getBatchComplnData(String progKeyId,String progYear) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();	
		paramValues.add(progKeyId);
		paramValues.add(progYear);
		List<String[]> dataList = dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_BATCHCOMP", paramValues);
		CommonMessage.debugMsg(dataList.size());
		return dataList;		
	}
	public List<String[]> getBatchComplnDatas(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();		
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		CommonMessage.debugMsg("commonFilter.getType()=="+commonFilter.getType());
		condParms+="MODETYPE="+commonFilter.getType() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_BATCHCOMPLETION", paramValues);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}
	public List<String[]> getEmpMailId(String batchId) throws Exception
	{
		String sql = EntTlBatchcompletionSql.getMailSql(batchId);
		CommonMessage.debugMsg(sql);
		List<String[]> mailIdArr = dbActionTemplate.getDataList(sql);
		return mailIdArr;
	}
	public EntTlBatchcompletion saveBatch(EntTlBatchcompletion newEntTlBatchcompletion) throws Exception
	{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 	
		//EntTlBatchcompletionSql entTlBatchcompletionSql = new EntTlBatchcompletionSql();
		newEntTlBatchcompletion.setBcomKeyid(dbActionTemplate.getSequenceNumber("ENT_TL_BATCHCOMPLETION",10,"BCM","YYMM","Y")); // set the sequnce number		
		sqls.add(EntTlBatchcompletionSql.getInsertSql(entTlBatchcompletionSql.getBcomDbFields(), newEntTlBatchcompletion.getSaveArray())); // add insert sql for master table
		//sqls.add(EntTlBatchcompletionSql.updateBatchMst(newEntTlBatchcompletion.getBcomBachKeyid()));
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		return newEntTlBatchcompletion;
	}
	
	public void insertFBReqMstValues(EntTlBatchcompletion entTlBatchcompletion,ENTBatchComplnBean entBatchComplnBean,List<String> sqls)
	{
		EntTlFeedbackRequestmst entTlFeedbackRequestmst = new EntTlFeedbackRequestmst();
		EntTlFeedbackRequestdtl entTlFeedbackRequestdtl = new EntTlFeedbackRequestdtl();
		EntTlFeedbackRequestmstSql entTlFeedbackRequestmstSql = new EntTlFeedbackRequestmstSql();
		EntTlFeedbackRequestdtlSql entTlFeedbackRequestdtlSql = new EntTlFeedbackRequestdtlSql();
		fillFBReqMstValues(entTlBatchcompletion,entBatchComplnBean,entTlFeedbackRequestmst,entTlFeedbackRequestdtl);
		try {
			entTlFeedbackRequestmst.setFrqmKeyid(dbActionTemplate.getSequenceNumber(EntTlFeedbackRequestmstSql.TBL_ENT_TL_FEEDBACK_REQUESTMST,10,"FRM","YYMM","Y"));
			sqls.add(EntTlFeedbackRequestmstSql.getInsertSql(entTlFeedbackRequestmstSql.getFrqmDbFields(), entTlFeedbackRequestmst.getSaveArray()));
			entTlFeedbackRequestdtl.setFrqdFrqmKeyid(entTlFeedbackRequestmst.getFrqmKeyid());
			String getEmployeeSql = EntTlFeedbackRequestmstSql.getEmpSql(entTlFeedbackRequestmst.getFrqmBachKeyid());
			CommonMessage.debugMsg("getEmployeeSql : "+getEmployeeSql);
			List<String[]> empIdArr = dbActionTemplate.getDataList(getEmployeeSql);
			for(String[] emp : empIdArr)
			{
				for(int i=0;i<emp.length;i++)
				{
					entTlFeedbackRequestdtl.setFrqdEmpmKeyid(emp[i]);
					String checkEmpSql = EntTlFeedbackRequestmstSql.checkEmp(emp[i]);
					if(CommonFunctions.isValidKeyId(entBatchComplnBean.getCheckMail()) && entBatchComplnBean.getCheckMail().equals("Y"))
					{
						String checkEmp = dbActionTemplate.getSingleValue(checkEmpSql);
						CommonMessage.debugMsg("Checking for updation : "+checkEmp);
						if(Integer.parseInt(checkEmp)<= 0)
							entTlFeedbackRequestdtl.setFrqdIsmailsent("N");
						else
							entTlFeedbackRequestdtl.setFrqdIsmailsent("Y");
						CommonMessage.debugMsg("Checked : "+entTlFeedbackRequestdtl.getFrqdIsmailsent());
					}
					entTlFeedbackRequestdtl.setFrqdKeyid(dbActionTemplate.getSequenceNumber(EntTlFeedbackRequestdtlSql.TBL_ENT_TL_FEEDBACK_REQUESTDTL,10,"FRD","YYMM","Y"));
					sqls.add(EntTlFeedbackRequestdtlSql.getInsertSql(entTlFeedbackRequestdtlSql.getFrqdDbFields(), entTlFeedbackRequestdtl.getSaveArray()));
					//Mail mail = new Mail();
					//mail.sendMail("info.akranta@gmail.com", "ksuresh.atpl@gmail.com", "Hi", "Test");
				}
			}
			entTlBatchcompletion.setEntTlFeedbackRequestmst(entTlFeedbackRequestmst);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void fillFBReqMstValues(EntTlBatchcompletion entTlBatchcompletion,ENTBatchComplnBean entBatchComplnBean,EntTlFeedbackRequestmst entTlFeedbackRequestmst,EntTlFeedbackRequestdtl entTlFeedbackRequestdtl)
	{
		entTlFeedbackRequestmst.setFrqmProgKeyid(entTlBatchcompletion.getBcomProgKeyid());
		entTlFeedbackRequestmst.setFrqmBachKeyid(entTlBatchcompletion.getBcomBachKeyid());
		entTlFeedbackRequestmst.setFrqmRequestDate(entTlBatchcompletion.getBcomEntryDate());
		entTlFeedbackRequestmst.setFrqmRequestBy(entTlBatchcompletion.getBcomEntryBy());
		entTlFeedbackRequestmst.setFrqmRequestRemarks(entTlBatchcompletion.getBcomRemarks());
		entTlFeedbackRequestmst.setFrqmTempfield1("-");
		entTlFeedbackRequestmst.setFrqmTempfield2("-");
		entTlFeedbackRequestmst.setFrqmTempfield3("-");
		entTlFeedbackRequestmst.setFrqmTempfield4("-");
		entTlFeedbackRequestmst.setFrqmTempfield5("-");
		entTlFeedbackRequestmst.setFrqmActive("Y");
		entTlFeedbackRequestmst.setFrqmCreatedby(entTlBatchcompletion.getBcomCreatedby());
		entTlFeedbackRequestmst.setFrqmCreatedon(entTlBatchcompletion.getBcomCreatedon());
		entTlFeedbackRequestmst.setFrqmModifiedon(entTlBatchcompletion.getBcomModifiedon());
		CommonMessage.debugMsg("Check Mail : "+entBatchComplnBean.getCheckMail());
		if(CommonFunctions.isValidKeyId(entBatchComplnBean.getCheckMail()) && entBatchComplnBean.getCheckMail().equals("Y"))
			entTlFeedbackRequestdtl.setFrqdIsmailsent("Y");
		else
			entTlFeedbackRequestdtl.setFrqdIsmailsent("N");
		
		CommonMessage.debugMsg("Check Mail after : "+entTlFeedbackRequestdtl.getFrqdIsmailsent());
		entTlFeedbackRequestdtl.setFrqdStatus(entTlBatchcompletion.getBcomStatus());
		entTlFeedbackRequestdtl.setFrqdFeedbackDate(entTlBatchcompletion.getBcomEntryDate());
		entTlFeedbackRequestdtl.setFrqdTempfield1("-");
		entTlFeedbackRequestdtl.setFrqdTempfield2("-");
		entTlFeedbackRequestdtl.setFrqdTempfield3("-");
		entTlFeedbackRequestdtl.setFrqdTempfield4("-");
		entTlFeedbackRequestdtl.setFrqdTempfield5("-");
		entTlFeedbackRequestdtl.setFrqdActive("Y");
		entTlFeedbackRequestdtl.setFrqdCreatedby(entTlBatchcompletion.getBcomCreatedby());
		entTlFeedbackRequestdtl.setFrqdCreatedon(entTlBatchcompletion.getBcomCreatedon());
		entTlFeedbackRequestdtl.setFrqdModifiedon(entTlBatchcompletion.getBcomModifiedon());
	}

	@Override
	public List<String[]> getManagerMailId(String batchId) throws Exception {
		String sql = EntTlBatchcompletionSql.getManagerMailSql(batchId);
		CommonMessage.debugMsg(sql);
		List<String[]> mailIdArr = dbActionTemplate.getDataList(sql);
		return mailIdArr;
	}

	@Override
	public String getBatchComplnremarks(String batchid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "Select distinct BCOM_REMARKS  from ENT_TL_BATCHCOMPLETION where BCOM_BACH_KEYID = '" + batchid + "'" ;
		
		CommonMessage.debugMsg("getBatchComplnremarks" + sql);
		return dbActionTemplate.getSingleValue(sql);
	}

	
}

