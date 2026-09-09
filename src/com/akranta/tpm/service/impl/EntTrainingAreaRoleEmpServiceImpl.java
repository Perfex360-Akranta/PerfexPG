/*Created By : BABU.D*/
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DeptFuncLinkBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlRoleTrainingareaLinkDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.EntTlRoleTrainingareaLinkDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.TrainingAreaReportExcelTemplate;
//import com.akranta.tpm.exportreport.TrainingAreaReportExcelTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlRoleEmpLink;
import com.akranta.tpm.model.EntTlRoleTopicLink;
import com.akranta.tpm.model.EntTlRoleTopicRating;
import com.akranta.tpm.model.EntTlRoleTrainingareaLink;
import com.akranta.tpm.service.EntTrainingAreaRoleEmpService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;


public class EntTrainingAreaRoleEmpServiceImpl implements EntTrainingAreaRoleEmpService {
	
	private CommonFilterDao commonFilterDao;
	
	private Validations validations ;
	private EntTlRoleTrainingareaLinkDao entTlRoleTrainingareaLinkDao;
	public EntTrainingAreaRoleEmpServiceImpl(DBActionTemplate dbActionTemplate)
	{
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		
		entTlRoleTrainingareaLinkDao= new EntTlRoleTrainingareaLinkDaoImpl(dbActionTemplate);
		validations = new Validations();
 	}
	
	public List<ComboBox> getDepartmentCombo() throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("DEPT_CODE");
		comboFilter.setIdField("DEPT_KEYID");
		comboFilter.setNameField("DEPT_NAME");
		comboFilter.setOrderByField("DEPT_NAME");		
		comboFilter.setTableName(TableNames.TBL_GEN_TL_DEPARTMENTMST);		
		return commonFilterDao.fillComboValues(comboFilter);	
	}
	
	public List<ComboBox> getTrainingAreaCombo(String type) throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("TRAR_NAME");
		if( UIUtils.isValidKeyId(type) )
		{
			comboFilter.setCondSql(" AND  TRAR_REFTYPE = '" + type +"' AND TRAR_REFTYPE <> 'SPK' AND TRAR_REFTYPE <> 'TOP' ");
		}
		
		comboFilter.setIdField("TRAR_KEYID");
		comboFilter.setNameField("TRAR_NAME");
		comboFilter.setOrderByField("TRAR_NAME");		
		comboFilter.setTableName(TableNames.TBL_ENT_TL_TRAININGAREA);
		
		return commonFilterDao.fillComboValues(comboFilter);	
	}
	
	public List<ComboBox> getDeptFunctionCombo(String deptId) throws Exception  {
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("DPTF_CODE");
		comboFilter.setIdField("DPTF_KEYID");
		comboFilter.setNameField("DPTF_NAME");
		comboFilter.setOrderByField("DPTF_NAME");		
		
		if (UIUtils.isValidKeyId(deptId)) 
			comboFilter.setCondSql(" AND DPTF_DEPT_KEYID = '" + deptId + "' ");
		
		comboFilter.setTableName(TableNames.TBL_GEN_TL_DEPTFUNCTION);		
		return commonFilterDao.fillComboValues(comboFilter);	
	}

	public List<ComboBox> getRoleCombo() throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setIdField("ROLE_KEYID");
		comboFilter.setNameField("ROLE_NAME");
		comboFilter.setOrderByField("ROLE_NAME");		
		comboFilter.setTableName(TableNames.TBL_GEN_TL_ROLEMST);
		return commonFilterDao.fillComboValues(comboFilter);	
	}

	public List<ComboBox> getSkillCombo() throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("SKIL_CODE");
		comboFilter.setIdField("SKIL_KEYID");
		comboFilter.setNameField("SKIL_NAME");
		comboFilter.setOrderByField("SKIL_NAME");		
		comboFilter.setTableName(TableNames.TBL_ENT_TL_SKILLMST);
		comboFilter.setCondSql(" AND SKIL_ISCHILD ='Y' "); 
		return commonFilterDao.fillComboValues(comboFilter);	
	}
	
	public List<ComboBox> getSkillRatingCombo() throws Exception {
		return entTlRoleTrainingareaLinkDao.getSkillRatingCombo();
	}
	
	public List<ComboBox> getTargetRatingCombo(String fromCreation) throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("SKIL_CODE");
		comboFilter.setIdField("SKRM_KEYID");
		comboFilter.setNameField("SKRM_ORDERNO");
		comboFilter.setCodeField("SKRM_DESCRIPTION");
		
		comboFilter.setOrderByField("SKRM_ORDERNO");		
		comboFilter.setTableName(TableNames.TBL_ENT_TL_SKILL_RATINGMST);
		//if(UIUtils.isValidKeyId(fromCreation))
			comboFilter.setCondSql(" AND SKRM_ORDERNO <> 0");
		//comboFilter.setCondSql(" AND SKIL_ISCHILD ='Y' "); 
		return commonFilterDao.fillComboValues(comboFilter);			
		//return entTlRoleTrainingareaLinkDao.getTargetRatingCombo();
	}

	public List<String[]> getRattings() throws Exception {
		return entTlRoleTrainingareaLinkDao.getRattings();
	}

	public List<String[]> getEmployeeGrid(String ErdlKeyid) throws Exception {
		return this.entTlRoleTrainingareaLinkDao.getEmployeeGrid(ErdlKeyid);
	}

	public List<String[]> getAllTrainAreaRoleView(CommonFilter commonFilter) throws Exception {
		return this.entTlRoleTrainingareaLinkDao.getAllTrainAreaRoleView(commonFilter);
	}
	public List<String[]> checkLinkExists(String deptId, String cellId, String roleId) throws Exception {
		return entTlRoleTrainingareaLinkDao.checkLinkExists(deptId, cellId, roleId);
	}
	
	
	public List<String[]> getDeptFuncLinkGrid(String condparam)throws Exception {
		return entTlRoleTrainingareaLinkDao.getDeptFuncLinkGrid(condparam);
	}
	public List<String[]> getDepartmentType(String deptId)throws Exception {
		return entTlRoleTrainingareaLinkDao.getDepartmentType(deptId);
	}

	public EntTlRoleTrainingareaLink create(EntTlRoleTrainingareaLink newEntTlRoleTrainingareaLink) throws Exception {
		try {
			String validationsFor;
			
			if (!UIUtils.isValidKeyId(newEntTlRoleTrainingareaLink.getRtalKeyid()))	
				validationsFor = "create";
			else 
				validationsFor = "update";
					
			validations.validate(newEntTlRoleTrainingareaLink,"EntDeptFuncLinkCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			
			EntTlRoleTopicLink entTlRoleTopicLink = newEntTlRoleTrainingareaLink.getEntTlRoleTopicLink();  
			
			if(UIUtils.isValidKeyId(entTlRoleTopicLink.getRtlkKeyid()))			
				validationsFor = "update";
			else
				validationsFor = "create";
			
			validations.validate(entTlRoleTopicLink,"EntDeptFuncLinkCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations			
			
			fillEntTlRoleTrainingareaLink(newEntTlRoleTrainingareaLink);
			entTlRoleTopicLink.setRtlkCreatedby(newEntTlRoleTrainingareaLink.getRtalCreatedby());
			fillRoleTopicLink(entTlRoleTopicLink);

			return entTlRoleTrainingareaLinkDao.create(newEntTlRoleTrainingareaLink);
			
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
	}
	
	private void fillEntTlRoleTrainingareaLink(
			EntTlRoleTrainingareaLink newEntTlRoleTrainingareaLink) {
		// TODO Auto-generated method stub
		newEntTlRoleTrainingareaLink.setRtalActive("Y");		
		String dateTime = CommonFunctions.dateTimeNow();
			
		newEntTlRoleTrainingareaLink.setRtalCreatedon(dateTime);
		
		if(!UIUtils.isValidKeyId(newEntTlRoleTrainingareaLink.getRtalKeyid()))
			newEntTlRoleTrainingareaLink.setRtalKeyid("{}");
		if(!UIUtils.isValidKeyId(newEntTlRoleTrainingareaLink.getRtalRoleKeyid()))
			newEntTlRoleTrainingareaLink.setRtalRoleKeyid("{}");
		if(!UIUtils.isValidKeyId(newEntTlRoleTrainingareaLink.getRtalTempfield1()))
			newEntTlRoleTrainingareaLink.setRtalTempfield1("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleTrainingareaLink.getRtalTempfield2()))
			newEntTlRoleTrainingareaLink.setRtalTempfield2("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleTrainingareaLink.getRtalTempfield3()))
			newEntTlRoleTrainingareaLink.setRtalTempfield3("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleTrainingareaLink.getRtalTempfield4()))
			newEntTlRoleTrainingareaLink.setRtalTempfield4("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleTrainingareaLink.getRtalTempfield5()))
			newEntTlRoleTrainingareaLink.setRtalTempfield5("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleTrainingareaLink.getRtalTrarKeyid()))
			newEntTlRoleTrainingareaLink.setRtalTrarKeyid("{}");

		if(!UIUtils.isValidKeyId(newEntTlRoleTrainingareaLink.getRtalModifiedon()))
			newEntTlRoleTrainingareaLink.setRtalModifiedon(dateTime);}

	public String deleteRoleSkill(String ErslKeyid) throws Exception {
		return entTlRoleTrainingareaLinkDao.deleteRoleSkill(ErslKeyid);
	}
	
	public EntTlRoleEmpLink createErdlEmployee(EntTlRoleEmpLink newEntTlRoleEmpLink,String from,String rtalKeyID,String created) throws BusinessApplicationExceptions,Exception {
		try {
			CommonMessage.debugMsg("from in service impl:"+from);
			CommonMessage.debugMsg("newEntTlRoleEmpLink.getErelEmpmKeyid()0:"+newEntTlRoleEmpLink.getErelEmpmKeyid());
			//CommonMessage.debugMsg("newEntTlRoleEmpLink.getErelKeyid()0:"+newEntTlRoleEmpLink.getErelKeyid());
			if(from.equals("cmbSave"))
			{String validationsFor;		
			validationsFor = "create";	
			List <EntTlRoleEmpLink> methodslist = newEntTlRoleEmpLink.getmethodEntTlRoleEmployeeLink();
			if(methodslist==null)
			{
				validations.validate(newEntTlRoleEmpLink,"EntDeptFuncLinkCreation",validationsFor);	
			}
			else if(methodslist!=null && methodslist.size()>0)
			for(EntTlRoleEmpLink multipleMethod:methodslist)
			{
				validations.validate(multipleMethod,"EntDeptFuncLinkCreation",validationsFor);
			}			
		//validations.validate(newEntTlRoleEmpLink,"EntDeptFuncLinkCreation",validationsFor);}//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
			
		}erdlEmployeeFillValues(newEntTlRoleEmpLink,rtalKeyID, created);		
		return entTlRoleTrainingareaLinkDao.createErdlEmployee(newEntTlRoleEmpLink);}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
	}
	public String deleteErdlEmployee(String erdlKeyid, String empId,EntTlRoleEmpLink newEntTlRoleEmpLink) throws ValidationExceptions, Exception {
		return entTlRoleTrainingareaLinkDao.deleteErdlEmployee(erdlKeyid, empId,newEntTlRoleEmpLink);
	}

	private EntTlRoleEmpLink erdlEmployeeFillValues(EntTlRoleEmpLink newEntTlRoleEmpLink,String rtalKeyID,String created) 
	{
		CommonMessage.debugMsg("newEntTlRoleEmpLink.getErelEmpmKeyid()1:"+newEntTlRoleEmpLink.getErelEmpmKeyid());
		//CommonMessage.debugMsg("newEntTlRoleEmpLink.getErelKeyid()1:"+newEntTlRoleEmpLink.getErelKeyid());
			CommonMessage.debugMsg("Detail 1");
			//CommonMessage.debugMsg("Chk Box : " +bdFormBean.getIssparesY());		
			String dateTime = CommonFunctions.dateTimeNow();
			
			newEntTlRoleEmpLink.setErelCurrentSkillratingid("{}");
			newEntTlRoleEmpLink.setErelTargetSkillratingid("{}");
			newEntTlRoleEmpLink.setErelEffFromDate(dateTime);			
			newEntTlRoleEmpLink.setErelEffTillDate(Constants.futureNullDate);
			newEntTlRoleEmpLink.setErelRtalKeyid(rtalKeyID);
			newEntTlRoleEmpLink.setErelCreatedby(created);
			
			if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelTempfield1()))
				newEntTlRoleEmpLink.setErelTempfield1("-");
			if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelTempfield2()))
				newEntTlRoleEmpLink.setErelTempfield2("-");
			if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelTempfield3()))
				newEntTlRoleEmpLink.setErelTempfield3("-");
			if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelTempfield4()))
				newEntTlRoleEmpLink.setErelTempfield4("-");
			if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelTempfield5()))
				newEntTlRoleEmpLink.setErelTempfield5("-");
			
			newEntTlRoleEmpLink.setErelCreatedon(dateTime);
			newEntTlRoleEmpLink.setErelModifiedon(dateTime);
			newEntTlRoleEmpLink.setErelActive("Y");
			CommonMessage.debugMsg("newEntTlRoleEmpLink.getmethodEntTlRoleEmployeeLink():"+newEntTlRoleEmpLink.getmethodEntTlRoleEmployeeLink());
			if(newEntTlRoleEmpLink.getmethodEntTlRoleEmployeeLink()!=null)
			newEntTlRoleEmpLink.setmethodEntTlRoleEmployeeLink(refTablesFillValues(newEntTlRoleEmpLink,rtalKeyID,created));
		
		return newEntTlRoleEmpLink;
	}
	private List<EntTlRoleEmpLink> refTablesFillValues(
			EntTlRoleEmpLink newEntTlRoleEmpLink,String rtalKeyID,String created) {
		
		// TODO Auto-generated method stub
		List<EntTlRoleEmpLink> newEntTlRoleEmpLinks = newEntTlRoleEmpLink.getmethodEntTlRoleEmployeeLink();
		List<EntTlRoleEmpLink> newEntTlRoleEmpLinkList = new ArrayList<EntTlRoleEmpLink>();
		int index=0;
		for( EntTlRoleEmpLink entTlRoleEmpLink : newEntTlRoleEmpLinks)
		{	
			String dateTime = CommonFunctions.dateTimeNow();
			
			entTlRoleEmpLink.setErelCurrentSkillratingid("{}");
			entTlRoleEmpLink.setErelTargetSkillratingid("{}");
			entTlRoleEmpLink.setErelEffFromDate(dateTime);			
			entTlRoleEmpLink.setErelEffTillDate(Constants.futureNullDate);
			entTlRoleEmpLink.setErelRtalKeyid(rtalKeyID);
			entTlRoleEmpLink.setErelCreatedby(created);
			EntTlRoleEmpLink entTlRoleEmpLink1 = (EntTlRoleEmpLink)newEntTlRoleEmpLink.getmethodEntTlRoleEmployeeLink().get(index);
			index++;
			CommonMessage.debugMsg("entTlRoleEmpLink1.getErelEmpmKeyid():"+entTlRoleEmpLink1.getErelEmpmKeyid());
			//CommonMessage.debugMsg("entTlRoleEmpLink1.getErelKeyid():"+entTlRoleEmpLink1.getErelKeyid());
			entTlRoleEmpLink.setErelEmpmKeyid(entTlRoleEmpLink1.getErelEmpmKeyid());
			//entTlRoleEmpLink.setErelKeyid(entTlRoleEmpLink1.getErelKeyid());
			if(!UIUtils.isValidKeyId(entTlRoleEmpLink.getErelTempfield1()))
				entTlRoleEmpLink.setErelTempfield1("-");
			if(!UIUtils.isValidKeyId(entTlRoleEmpLink.getErelTempfield2()))
				entTlRoleEmpLink.setErelTempfield2("-");
			if(!UIUtils.isValidKeyId(entTlRoleEmpLink.getErelTempfield3()))
				entTlRoleEmpLink.setErelTempfield3("-");
			if(!UIUtils.isValidKeyId(entTlRoleEmpLink.getErelTempfield4()))
				entTlRoleEmpLink.setErelTempfield4("-");
			if(!UIUtils.isValidKeyId(entTlRoleEmpLink.getErelTempfield5()))
				entTlRoleEmpLink.setErelTempfield5("-");
			
			entTlRoleEmpLink.setErelCreatedon(dateTime);
			entTlRoleEmpLink.setErelModifiedon(dateTime);
			entTlRoleEmpLink.setErelActive("Y");
			newEntTlRoleEmpLinkList.add(entTlRoleEmpLink);
		}
		
		return newEntTlRoleEmpLinkList;
	}

	public EntTlRoleEmpLink fillValues(EntTlRoleEmpLink newEntTlRoleEmpLink,DeptFuncLinkBean deptFuncLinkBean) throws Exception {
		
		newEntTlRoleEmpLink.setErelActive("Y");		
		String dateTime = CommonFunctions.dateTimeNow();
		//CommonMessage.debugMsg("Time : "+pcsEntryBean.get());
		CommonMessage.debugMsg("Key ID : "+newEntTlRoleEmpLink.getErelKeyid());
		//CommonMessage.debugMsg("Old CreatedON : "+oldPcsTlMst.getBdmsCreatedon());
	
		if(newEntTlRoleEmpLink.getErelKeyid() == null )
			newEntTlRoleEmpLink.setErelCreatedon(dateTime);
		else
			newEntTlRoleEmpLink.setErelCreatedon(dateTime);
		if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelCurrentSkillratingid()))
			newEntTlRoleEmpLink.setErelCurrentSkillratingid("{}");
		if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelEffFromDate()))
			newEntTlRoleEmpLink.setErelEffFromDate(dateTime);
		if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelEffTillDate()))
			newEntTlRoleEmpLink.setErelEffTillDate(Constants.futureNullDate);
		if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelEmpmKeyid()))
			newEntTlRoleEmpLink.setErelEmpmKeyid("{}");
	//	if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelRtalKeyid()))
	//		newEntTlRoleEmpLink.setErelRtalKeyid("{}");
		if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelTargetSkillratingid()))
			newEntTlRoleEmpLink.setErelTargetSkillratingid("{}");
		if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelTempfield1()))
			newEntTlRoleEmpLink.setErelTempfield1("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelTempfield2()))
			newEntTlRoleEmpLink.setErelTempfield2("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelTempfield3()))
			newEntTlRoleEmpLink.setErelTempfield3("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelTempfield4()))
			newEntTlRoleEmpLink.setErelTempfield4("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleEmpLink.getErelTempfield5()))
			newEntTlRoleEmpLink.setErelTempfield5("-");

		newEntTlRoleEmpLink.setErelActive("Y");
		
		newEntTlRoleEmpLink.setErelModifiedon(dateTime);
	
		return newEntTlRoleEmpLink;
	}
	
	
public EntTlRoleTopicLink fillRoleTopicLink(EntTlRoleTopicLink newEntTlRoleTopicLink) throws Exception {
		
		newEntTlRoleTopicLink.setRtlkActive("Y");		
		String dateTime = CommonFunctions.dateTimeNow();
			
		CommonMessage.debugMsg("inside fillRoleTopicLink");
		
		newEntTlRoleTopicLink.setRtlkCreatedon(dateTime);
		newEntTlRoleTopicLink.setRtlkModifiedon(dateTime);
		
		
		if(!UIUtils.isValidKeyId(newEntTlRoleTopicLink.getRtlkRtalKeyid()))
			newEntTlRoleTopicLink.setRtlkRtalKeyid("{}");
		if(!UIUtils.isValidKeyId(newEntTlRoleTopicLink.getRtlkSpokId()))
			newEntTlRoleTopicLink.setRtlkSpokId("{}");
		if(!UIUtils.isValidKeyId(newEntTlRoleTopicLink.getRtlkTargetskrmKeyid()))
			newEntTlRoleTopicLink.setRtlkTargetskrmKeyid("{}");
		if(!UIUtils.isValidKeyId(newEntTlRoleTopicLink.getRtlkTopiKeyid()))
			newEntTlRoleTopicLink.setRtlkTopiKeyid("{}");
		if(!UIUtils.isValidKeyId(newEntTlRoleTopicLink.getRtlkTempfield1()))
			newEntTlRoleTopicLink.setRtlkTempfield1("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleTopicLink.getRtlkTempfield2()))
			newEntTlRoleTopicLink.setRtlkTempfield2("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleTopicLink.getRtlkTempfield3()))
			newEntTlRoleTopicLink.setRtlkTempfield3("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleTopicLink.getRtlkTempfield4()))
			newEntTlRoleTopicLink.setRtlkTempfield4("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleTopicLink.getRtlkTempfield5()))
			newEntTlRoleTopicLink.setRtlkTempfield5("-");
		
		fillRoleTopicRating(newEntTlRoleTopicLink);
		
		return newEntTlRoleTopicLink;
	}
	
	private void  fillRoleTopicRating(EntTlRoleTopicLink newEntTlRoleTopicLink) throws Exception 
	{						
		String dateTime = CommonFunctions.dateTimeNow();
		List<EntTlRoleTopicRating> entTlRoleTopicRatingList = newEntTlRoleTopicLink.getEntTlRoleTopicRatingList();
		List<EntTlRoleTopicRating> newEntTlRoleTopicRatingList = new ArrayList<EntTlRoleTopicRating>();
		for(EntTlRoleTopicRating newEntTlRoleTopicRating : entTlRoleTopicRatingList){
		
			//newEntTlRoleTopicRating.setErsrRatingNumber(rattingData[1]);
			//newEntTlRoleTopicRating.setErsrOrderno(rattingData[0]);
			newEntTlRoleTopicRating.setRtrlCreatedon(dateTime);		
			CommonMessage.debugMsg(" newEntTlRoleTopicRating.getRtrlCriteriadesc() " + newEntTlRoleTopicRating.getRtrlCriteriadesc());
			if(!UIUtils.isValidKeyId(newEntTlRoleTopicRating.getRtrlCriteriadesc()))
				newEntTlRoleTopicRating.setRtrlCriteriadesc("{}");
			if(!UIUtils.isValidKeyId(newEntTlRoleTopicRating.getRtrlCutoff()))
				newEntTlRoleTopicRating.setRtrlCutoff("0");
			if(!UIUtils.isValidKeyId(newEntTlRoleTopicRating.getRtrlOrderno()))
				newEntTlRoleTopicRating.setRtrlOrderno("0");
			if(!UIUtils.isValidKeyId(newEntTlRoleTopicRating.getRtrlRtlkKeyid()))
				newEntTlRoleTopicRating.setRtrlRtlkKeyid("{}");
			if(!UIUtils.isValidKeyId(newEntTlRoleTopicRating.getRtrlSkrmKeyid()))
				newEntTlRoleTopicRating.setRtrlSkrmKeyid("{}");
			if(!UIUtils.isValidKeyId(newEntTlRoleTopicRating.getRtrlTempfield1()))
				newEntTlRoleTopicRating.setRtrlTempfield1("-");
			if(!UIUtils.isValidKeyId(newEntTlRoleTopicRating.getRtrlTempfield2()))
				newEntTlRoleTopicRating.setRtrlTempfield2("-");
			if(!UIUtils.isValidKeyId(newEntTlRoleTopicRating.getRtrlTempfield3()))
				newEntTlRoleTopicRating.setRtrlTempfield3("-");
			if(!UIUtils.isValidKeyId(newEntTlRoleTopicRating.getRtrlTempfield4()))
				newEntTlRoleTopicRating.setRtrlTempfield4("-");
			if(!UIUtils.isValidKeyId(newEntTlRoleTopicRating.getRtrlTempfield5()))
				newEntTlRoleTopicRating.setRtrlTempfield5("-");
			
			newEntTlRoleTopicRating.setRtrlActive("Y");		
			newEntTlRoleTopicRating.setRtrlModifiedon(dateTime);
			newEntTlRoleTopicRating.setRtrlCreatedby(newEntTlRoleTopicLink.getRtlkCreatedby());
			
			newEntTlRoleTopicRatingList.add(newEntTlRoleTopicRating);
		}	
		newEntTlRoleTopicLink.setEntTlRoleTopicRatingList(newEntTlRoleTopicRatingList);
		//return newEntTlRoleTopicRatingList;
	}
	
		

	

	@Override
	public List<ComboBox> getevalCombo(String condsql) throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("EVAL_CODE");
		comboFilter.setIdField("EVAL_KEYID");
		comboFilter.setNameField("EVAL_NAME");
				
		comboFilter.setTableName(TableNames.TBL_ENT_TL_EVALUATIONTYPEMST);
		
		return commonFilterDao.fillComboValues(comboFilter);	
	}

	@Override
	public List<ComboBox> getTopic(String condsql,String trarkeyid) throws Exception {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			ComboFilter comboFilter = new ComboFilter();
			//comboFilter.setCodeField("TOPI_CODE");
			comboFilter.setIdField("TOPI_KEYID");
			comboFilter.setNameField("TOPI_NAME");
			comboFilter.setTableName(TableNames.TBL_ENT_Tl_TOPICMST);
			CommonMessage.debugMsg(condsql);
			/*String  sql = "select topi_keyid from  ent_tl_topicmst where TOPI_ISCHILD = 'Y' and TOPI_KEYID in ( ";
					sql	+="select trar_refid from ent_vw_trainingareachildpath ";
					sql	+="where instr(CHILDPATH, (SELECT trar_keyid FROM ent_tl_trainingarea ";
					sql	+="WHERE trar_parentid = '"+trarkeyid+"' ";
					sql	+="and trar_refid = '"+condsql+"')) > 0 and trar_reftype <> 'SPK')";*/
			String sql = "select topicid from  ENT_VW_SPOKETOPIC where SPOKID = '"+condsql+"' AND  instr(CHILDPATH, '"+trarkeyid+"') > 0  AND LEAF = 1";
			if(UIUtils.isValidKeyId(condsql) && UIUtils.isValidKeyId(trarkeyid))
				comboFilter.setCondSql("AND TOPI_KEYID in ("+sql+")");
			return commonFilterDao.fillComboValues(comboFilter);	
		}
	
	@Override
	public List<ComboBox> getTrnAreacomboVals(String elementtype,String parentId) throws Exception {
		// TODO Auto-generated method stub
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("TOPI_CODE");
		comboFilter.setIdField("TRAR_KEYID");
		comboFilter.setNameField("TRAR_NAME");
		comboFilter.setTableName(TableNames.TBL_ENT_TL_TRAININGAREA);
		CommonMessage.debugMsg(elementtype);
		if(UIUtils.isValidKeyId(parentId))
			comboFilter.setCondSql(" AND TRAR_ELEMENTTYPE ='"+elementtype+"' AND TRAR_PARENTID ='"+parentId+"'");
		if(UIUtils.isValidKeyId(elementtype) && !UIUtils.isValidKeyId(parentId)){
			comboFilter.setCondSql("AND TRAR_ELEMENTTYPE ='"+elementtype+"'");
		
		}
		return commonFilterDao.fillComboValues(comboFilter);	
	}
	
	@Override
	public List<String[]> getevalGrdData(String trainAreaId, String topiId, String roleId, String spokId)
			throws Exception {
		// TODO Auto-generated method stub
		return entTlRoleTrainingareaLinkDao.getevalGrdData(trainAreaId,topiId,roleId, spokId);
	}
	
	public EntTlRoleTrainingareaLink create(
			EntTlRoleTrainingareaLink newEntTlRoleTrainingareaLink,
			EntTlRoleTopicLink newEntTlRoleTopicLink,
			EntTlRoleTopicRating newEntTlRoleTopicRating)throws Exception ,ValidationExceptions{
		try{
			fillEntTlRoleTrainingareaLink(newEntTlRoleTrainingareaLink);
			fillRoleTopicLink(newEntTlRoleTopicLink);
			CommonMessage.debugMsg("After Filling Values" +newEntTlRoleTopicLink.getRtlkModifiedon());
						
			return entTlRoleTrainingareaLinkDao.create( newEntTlRoleTrainingareaLink,newEntTlRoleTopicLink,newEntTlRoleTopicRating);
			
		}catch (Exception e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			
		}
		return newEntTlRoleTrainingareaLink;
		
	}

	@Override
	public EntTlRoleEmpLink create(EntTlRoleEmpLink newEntTlRoleEmpLink,
			EntTlRoleTopicLink newEntTlRoleTopicLink,
			EntTlRoleTrainingareaLink newEntTlRoleTrainingareaLink,
			EntTlRoleTopicRating newEntTlRoleTopicRating,
			DeptFuncLinkBean deptFuncLinkBean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntTlRoleTrainingareaLink getEntTlRoleTrainingareaLink(
			String roleTrainAreaId) throws Exception {
		return entTlRoleTrainingareaLinkDao.getEntTlRoleTrainingareaLink(roleTrainAreaId);
	}

	@Override
	public String deleteTopicRating(String rtalKeyid, String rtlkKeyid)
			throws Exception {
		// TODO Auto-generated method stub
		return entTlRoleTrainingareaLinkDao.deleteTopicRating( rtalKeyid,  rtlkKeyid);
	}

	@Override
	public String getcheckmanuf(String reftype) throws Exception {
		// TODO Auto-generated method stub
		return entTlRoleTrainingareaLinkDao.getcheckmanuf(reftype);
	}

	@Override
	public String getChildVals(String keyId) throws Exception {
		// TODO Auto-generated method stub
		return entTlRoleTrainingareaLinkDao.getChildVals(keyId);
	}

	@Override
	public String getdispFuncloc(String trarKeyid) throws Exception {
		// TODO Auto-generated method stub
		return entTlRoleTrainingareaLinkDao.getdispFuncloc(trarKeyid);
	}

	@Override
	public String getspokId(String topKey) throws Exception {
		// TODO Auto-generated method stub
		return entTlRoleTrainingareaLinkDao.getspokId(topKey);
	}

	@Override
	public List<ComboBox> getSpokeKeyidCombo(String condsql) throws Exception {
		// TODO Auto-generated method stub
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("SPOK_NAME");
		comboFilter.setIdField("SPOK_KEYID");
		if(UIUtils.isValidKeyId(condsql))
			comboFilter.setCondSql("AND SPOK_KEYID in (select distinct TRAR_REFID from ENT_TL_TRAININGAREA  where TRAR_PARENTID ='"+condsql+"')");
		comboFilter.setTableName(TableNames.TBL_ENT_TL_SPOKEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public String getdispFunclockey(String trarKeyid) throws Exception {
		// TODO Auto-generated method stub
		return entTlRoleTrainingareaLinkDao.getdispFunclockey(trarKeyid);
	}

	@Override
	public Workbook getViewExcel(String condparam,String format,String path,String roleName,String trcl) throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, List<String[]>> excelData = entTlRoleTrainingareaLinkDao.getViewExcel(condparam);
		Workbook wb = ( new TrainingAreaReportExcelTemplate()).fillValues(excelData,format,path,roleName,trcl);
		
		CommonMessage.debugMsg("Service implto be gae........."+wb);
		return wb;
	}

	@Override
	public List<String[]> getEmployeeList(String erdlKeyid,GridParams gridParams, String empFilter) throws Exception {
		// TODO Auto-generated method stub
		return entTlRoleTrainingareaLinkDao.getEmployeeList(erdlKeyid,gridParams, empFilter);
	}

	@Override
	public String saveErdlEmployee(String employeelist, String rtalKeyID,
			String usrm_ccno) throws Exception {
		// TODO Auto-generated method stub
		return entTlRoleTrainingareaLinkDao.saveErdlEmployee( employeelist,  rtalKeyID, usrm_ccno);
	}

	@Override
	public Workbook getEmpRoleExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return entTlRoleTrainingareaLinkDao.getEmpRoleExcel(colmodel,format,commonFilter);
	}

	

	
	
}