package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.net.InetAddress;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ENTBatchComplnBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlBatchcompletionDao;
import com.akranta.tpm.dao.EntTlBudgetmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.EntTlBatchcompletionDaoImpl;
import com.akranta.tpm.dao.impl.EntTlBudgetmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlBatchcompletion;
import com.akranta.tpm.model.EntTlBudgetmst;
import com.akranta.tpm.service.ENTBatchComplnService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Mail;
import com.akranta.tpm.utils.Validations;

public class EntBatchComplnServiceImpl implements ENTBatchComplnService{
	
	private CommonFilterDao commonFilterDao;
	private EntTlBatchcompletionDao entTlBatchcompletionDao;
	private EntTlBudgetmstDao entTlBudgetmstDao;
	private Validations validations ;
	public EntBatchComplnServiceImpl(DBActionTemplate dbActionTemplate)
	{
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		entTlBudgetmstDao = new EntTlBudgetmstDaoImpl(dbActionTemplate);
		entTlBatchcompletionDao = new EntTlBatchcompletionDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public List<ComboBox> getProgkeyidCombo(String condsql,ComboFilter comboFilter) throws Exception {
		
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("PROG_NAME");
		comboFilter.setIdField("PROG_KEYID");
		if( UIUtils.isValidKeyId(condsql)  ){
			comboFilter.setCondSql(" AND PROG_KEYID = '" + condsql + "'");
		}
		comboFilter.setTableName(TableNames.TBL_ENT_TL_PROGRAMMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<ComboBox> getExpenseTypeCombo(String condsql,ComboFilter comboFilter) throws Exception {
		
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("EBET_DESCRIPTION");
		comboFilter.setIdField("EBET_KEYID");		
		comboFilter.setTableName(TableNames.TBL_ENT_TL_BUDGET_EXPENSETYPE);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	public List<String[]> getBatchComplnData(String progKeyId,String progYear) throws Exception
	{
		return entTlBatchcompletionDao.getBatchComplnData(progKeyId,progYear);
	}
	public List<String[]> getBatchComplnDatas(CommonFilter commonFilter) throws Exception
	{
		return entTlBatchcompletionDao.getBatchComplnDatas(commonFilter);
	}
	public List<String[]> getBudget(String batchId) throws Exception
	{
		return entTlBudgetmstDao.getBudget(batchId);
	}
	public EntTlBudgetmst getBudgetFromKey(String budgetId) throws Exception
	{
		return entTlBudgetmstDao.getBudgetFromKey(budgetId);
	}
	public EntTlBatchcompletion saveBatch(EntTlBatchcompletion newEntTlBatchcompletion,EntTlBatchcompletion oldEntTlBatchcompletion,ENTBatchComplnBean entBatchComplnBean,String localAddr) throws Exception
	{
		try {
			CommonMessage.debugMsg("Status");
			if(!UIUtils.isValidKeyId(entBatchComplnBean.getStatusFlag()))
				validations.validate(newEntTlBatchcompletion,"EntBatchCompln","Cancel");
			CommonMessage.debugMsg("Status.......");
			fillBatchValues(newEntTlBatchcompletion,oldEntTlBatchcompletion);
			
			entTlBatchcompletionDao.create(newEntTlBatchcompletion,entBatchComplnBean);
			CommonMessage.debugMsg(entBatchComplnBean.getCheckMail()+"<--------------newEntTlBatchcompletion......"+newEntTlBatchcompletion.getEntTlFeedbackRequestmst().getFrqmKeyid());
			
			if(UIUtils.isValidKeyId(entBatchComplnBean.getCheckMail())&& entBatchComplnBean.getCheckMail().equals("Y"))
				sendMail(newEntTlBatchcompletion,localAddr);
			 
		}
		catch (ValidationExceptions e){			
			throw new ValidationExceptions(e.getMessage());
		}	
		
		return newEntTlBatchcompletion;
	}
	public EntTlBudgetmst saveBudget(EntTlBudgetmst newEntTlBudgetmst,EntTlBudgetmst oldEntTlBudgetmst)throws Exception
	{
		validations.validate(newEntTlBudgetmst,"EntBatchCompln","SaveBudget");
		fillBudgetValues(newEntTlBudgetmst,oldEntTlBudgetmst);
		return entTlBudgetmstDao.create(newEntTlBudgetmst);
	}
	public EntTlBudgetmst updateBudget(EntTlBudgetmst newEntTlBudgetmst,EntTlBudgetmst oldEntTlBudgetmst)throws Exception
	{
		validations.validate(newEntTlBudgetmst,"EntBatchCompln","SaveBudget");
		fillBudgetValues(newEntTlBudgetmst,oldEntTlBudgetmst);
		return entTlBudgetmstDao.update(newEntTlBudgetmst);
	}
	public EntTlBudgetmst deleteBudget(EntTlBudgetmst newEntTlBudgetmst)throws Exception
	{
		return entTlBudgetmstDao.delete(newEntTlBudgetmst);
	}
	private void sendMail(EntTlBatchcompletion entTlBatchcompletion,String localAddr) throws Exception
	{
		Mail mail = new Mail(commonFilterDao.getDBActionTemplate());
		
		String batchId = entTlBatchcompletion.getEntTlFeedbackRequestmst().getFrqmBachKeyid();
		String fromDate = entTlBatchcompletion.getBcomStartDate();
		String toDate = entTlBatchcompletion.getBcomEndDate();
		
		CommonMessage.debugMsg("Batch : "+batchId);
		List<String []> mailList  = null;
		List<String []> empDtlToManager  = null;
			
				if(UIUtils.isValidKeyId(batchId)){
					mailList = entTlBatchcompletionDao.getEmpMailId(batchId);
					empDtlToManager = entTlBatchcompletionDao.getManagerMailId(batchId);
				}
				CommonMessage.debugMsg("Size Mail List : "+mailList.size());
				if(empDtlToManager.size()>0)
					sendMailForManager(empDtlToManager,mail);
				if(mailList.size()>0)
				{
					String idVal=",";
					for(String[]empDetails : mailList)
					{		
						CommonMessage.debugMsg(empDetails[1] + " : "+empDetails[4]+" : "+empDetails[2]+"-"+empDetails[3]+"..............>"+empDetails[0]);
						try {
							//mail.sendMail("", empDetails[1], empDetails[2]+"-"+empDetails[3],getSubject(empDetails[2],empDetails[6],empDetails[5],fromDate,toDate,localAddr));
							idVal += "'"+empDetails[4]+"',";
						} catch (Exception e) {
							
						}
					}
					//entTlBatchcompletion = entTlBatchcompletionDao.updateMailSent(entTlBatchcompletion,idVal.substring(1,idVal.length()-1));
				}
		
	}
	private void sendMailForManager(List<String[]> empDtlToManager, Mail mail) {
		
		//for(String[]empDetails : empDtlToManager)
		//{		
			//CommonMessage.debugMsg(empDetails[1] + " : "+empDetails[4]+" : "+empDetails[2]+"-"+empDetails[3]+"..............>"+empDetails[0]);
			try {
				String subject = getMailText(empDtlToManager);
				mail.sendMail("", empDtlToManager.get(0)[4], empDtlToManager.get(0)[1]+"-"+empDtlToManager.get(0)[0],subject);
				
			} catch(Exception e) {				
			}
		//}
	}

	private static String getMailText(List<String[]> empDtlToManager) {
		
		StringBuilder subject = new StringBuilder();	
		subject.append(UIUtils.getPropertyValue("com.akranta.tpm.resources.ENTBatchCompln", "managerMailTextForBatchComp"));
		String empData = subject.toString();
		for(String[]empDetails : empDtlToManager)
		{			
			empData = empData.replace("@EmployeeDetail", "<tr><td>"+empDetails[1]+"</td> <td>"+empDetails[6]+"</td> <td>"+empDetails[5]+"</td></tr>");			
		}
		
		CommonMessage.debugMsg("test13.name..."+empData.replace("@ManagerName", empDtlToManager.get(0)[3]));
		empData = empData.replace("@ManagerName", empDtlToManager.get(0)[3]);
		CommonMessage.debugMsg("subject........"+empData);
		return empData;
	}

	private EntTlBatchcompletion fillBatchValues(EntTlBatchcompletion newEntTlBatchcompletion,EntTlBatchcompletion oldEntTlBatchcompletion)
	{
		newEntTlBatchcompletion.setBcomActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		
		newEntTlBatchcompletion.setBcomCreatedon(dateTime);
		newEntTlBatchcompletion.setBcomModifiedon(dateTime);
		
		if(!UIUtils.isValidKeyId(newEntTlBatchcompletion.getBcomEntryDate()))
			newEntTlBatchcompletion.setBcomEntryDate(dateTime);
		
		if(!UIUtils.isValidKeyId(newEntTlBatchcompletion.getBcomEntryBy()))
			newEntTlBatchcompletion.setBcomEntryBy(newEntTlBatchcompletion.getBcomEntryBy());
		
		if(!UIUtils.isValidKeyId(newEntTlBatchcompletion.getBcomProgKeyid()))
			newEntTlBatchcompletion.setBcomProgKeyid("{}");
		
		if(!UIUtils.isValidKeyId(newEntTlBatchcompletion.getBcomBachKeyid()))
			newEntTlBatchcompletion.setBcomBachKeyid("{}");
		
		if(!UIUtils.isValidKeyId(newEntTlBatchcompletion.getBcomStartDate()))
			newEntTlBatchcompletion.setBcomStartDate(Constants.passNullDate);
		
		if(!UIUtils.isValidKeyId(newEntTlBatchcompletion.getBcomEndDate()))
			newEntTlBatchcompletion.setBcomEndDate(Constants.futureNullDate);
		
		if(!UIUtils.isValidKeyId(newEntTlBatchcompletion.getBcomStatus()))
		{
			/*String status = newEntTlBatchcompletion.getBcomStatus();
			CommonMessage.debugMsg("Service Impl Status : "+status + " ------------------> "+status.substring(0, 1));
			if(status.substring(0, 1).equals("C"))
			 newEntTlBatchcompletion.setBcomStatus("C");
			else*/
			 newEntTlBatchcompletion.setBcomStatus("P");
		}
		if(!UIUtils.isValidKeyId(newEntTlBatchcompletion.getBcomRemarks()))
			newEntTlBatchcompletion.setBcomRemarks("{}");
		newEntTlBatchcompletion.setBcomTempfield1("-");
		newEntTlBatchcompletion.setBcomTempfield2("-");
		newEntTlBatchcompletion.setBcomTempfield3("-");
		newEntTlBatchcompletion.setBcomTempfield4("-");
		newEntTlBatchcompletion.setBcomTempfield5("-");
		
		return newEntTlBatchcompletion;
	}
	private EntTlBudgetmst fillBudgetValues(EntTlBudgetmst newEntTlBudgetmst,EntTlBudgetmst oldEntTlBudgetmst)
	{
		String dateTime = CommonFunctions.dateTimeNow();
		newEntTlBudgetmst.setBudgActive("Y");
		
		newEntTlBudgetmst.setBudgModifiedon(dateTime);
		newEntTlBudgetmst.setBudgBsdlKeyid("{}");
		
		if(oldEntTlBudgetmst != null)
		{
			if(UIUtils.isValidKeyId(oldEntTlBudgetmst.getBudgCreatedon()))
				newEntTlBudgetmst.setBudgCreatedon(oldEntTlBudgetmst.getBudgCreatedon());
			else
				newEntTlBudgetmst.setBudgCreatedon(dateTime);
			
			
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgDate()))
			{
				if(UIUtils.isValidKeyId(oldEntTlBudgetmst.getBudgDate()))
					newEntTlBudgetmst.setBudgDate(oldEntTlBudgetmst.getBudgDate());
				else
					newEntTlBudgetmst.setBudgDate(Constants.futureNullDate);
			}
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgBilldate()))
			{
				if(UIUtils.isValidKeyId(oldEntTlBudgetmst.getBudgBilldate()))
					newEntTlBudgetmst.setBudgBilldate(oldEntTlBudgetmst.getBudgBilldate());
				else
					newEntTlBudgetmst.setBudgBilldate(Constants.futureNullDate);
			}
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgBillno()))
			{
				if(UIUtils.isValidKeyId(oldEntTlBudgetmst.getBudgBillno()))
					newEntTlBudgetmst.setBudgBillno(oldEntTlBudgetmst.getBudgBillno());
				else
					newEntTlBudgetmst.setBudgBillno("{}");
			}
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgRemarks()))
			{
				if(UIUtils.isValidKeyId(oldEntTlBudgetmst.getBudgRemarks()))
					newEntTlBudgetmst.setBudgRemarks(oldEntTlBudgetmst.getBudgRemarks());
				else
					newEntTlBudgetmst.setBudgRemarks("{}");
			}
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgQuantity()))
			{
				if(UIUtils.isValidKeyId(oldEntTlBudgetmst.getBudgQuantity()))
					newEntTlBudgetmst.setBudgQuantity(oldEntTlBudgetmst.getBudgQuantity());
				else
					newEntTlBudgetmst.setBudgQuantity("0");
			}
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgAmount()))
			{
				if(UIUtils.isValidKeyId(oldEntTlBudgetmst.getBudgAmount()))
					newEntTlBudgetmst.setBudgAmount(oldEntTlBudgetmst.getBudgAmount());
				else
					newEntTlBudgetmst.setBudgAmount("0");
			}
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgEnteredby()))
			{
				if(UIUtils.isValidKeyId(oldEntTlBudgetmst.getBudgEnteredby()))
					newEntTlBudgetmst.setBudgEnteredby(oldEntTlBudgetmst.getBudgEnteredby());
				else
					newEntTlBudgetmst.setBudgEnteredby("{}");
			}
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgAccounthead()))
			{
				if(UIUtils.isValidKeyId(oldEntTlBudgetmst.getBudgAccounthead()))
					newEntTlBudgetmst.setBudgAccounthead(oldEntTlBudgetmst.getBudgAccounthead());
				else
					newEntTlBudgetmst.setBudgAccounthead("{}");
			}
		}
		else
		{
			newEntTlBudgetmst.setBudgCreatedon(dateTime);
			
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgDate()))
				newEntTlBudgetmst.setBudgDate(Constants.futureNullDate);
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgBilldate()))
				newEntTlBudgetmst.setBudgBilldate(Constants.futureNullDate);
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgBillno()))
				newEntTlBudgetmst.setBudgBillno("{}");
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgRemarks()))
				newEntTlBudgetmst.setBudgRemarks("{}");
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgQuantity()))
				newEntTlBudgetmst.setBudgQuantity("0");
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgAmount()))
				newEntTlBudgetmst.setBudgAmount("0");
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgEnteredby()))
				newEntTlBudgetmst.setBudgEnteredby("{}");
			if(!UIUtils.isValidKeyId(newEntTlBudgetmst.getBudgAccounthead()))
				newEntTlBudgetmst.setBudgAccounthead("-");
		}
		
		
		
		newEntTlBudgetmst.setBudgTempfield3("-");
		newEntTlBudgetmst.setBudgTempfield4("-");
		newEntTlBudgetmst.setBudgTempfield5("-");
		
		return newEntTlBudgetmst;
	}
	public static String getSubject(String empName,String progName,String batch,String fromDate,String toDate,String localAddr)
	{
	
		//StringBuffer sb = new StringBuffer();
		//String newline = System.getProperty("line.separator");
		String subject = "";
		subject += "Dear "+empName+",<br>"; 		
		subject += "Thank you attending the Training Program "+progName + "-"+batch+" from "+ fromDate+" to "+toDate+".<br>"; 		
		subject += "As part of our efforts to continuously improve training delivery and experience, we request you to give your feedback by clicking on the URL below<br>"; 	
		subject += localAddr+"<br>"; 
		subject += "Best Regards,<br>"; 		
		subject += "Training and Development Team<br>"; 
		
		return subject;
	}

	@Override
	public String getBatchComplnremarks(String batchid) throws Exception {
		// TODO Auto-generated method stub
		return entTlBatchcompletionDao.getBatchComplnremarks(batchid);
	}

}
