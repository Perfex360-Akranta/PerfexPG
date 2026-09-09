/*Created By : BABU.D*/
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DeptFuncLinkBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntDeptFuncLinkDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.EntDeptFuncLinkDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.EntTlRoleDeptLinkmst;
import com.akranta.tpm.model.EntTlRoleEmpLink;
import com.akranta.tpm.model.EntTlRoleSkillLink;
import com.akranta.tpm.model.EntTlRoleSkillRating;
import com.akranta.tpm.model.PcsTlAncilliarytime;
import com.akranta.tpm.model.PcsTlLossreasonlink;
import com.akranta.tpm.model.EntTlRoleEmpLink;
import com.akranta.tpm.service.EntDeptFuncLinkService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
//import com.sun.corba.se.impl.orbutil.closure.Constant;



public class EntDeptFuncLinkServiceImpl implements EntDeptFuncLinkService {
	
	private CommonFilterDao commonFilterDao;
	private EntDeptFuncLinkDao entDeptFuncLinkDao;
	private Validations validations ;
	
	public EntDeptFuncLinkServiceImpl(DBActionTemplate dbActionTemplate)
	{
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		entDeptFuncLinkDao = new EntDeptFuncLinkDaoImpl(dbActionTemplate);
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
		comboFilter.setCodeField("ROLE_CODE");
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
		return entDeptFuncLinkDao.getSkillRatingCombo();
	}
	
	public List<ComboBox> getTargetRatingCombo(String fromCreation) throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("SKIL_CODE");
		comboFilter.setIdField("SKRM_KEYID");
		comboFilter.setNameField("SKRM_ORDERNO");
		comboFilter.setOrderByField("SKRM_ORDERNO");		
		comboFilter.setTableName(TableNames.TBL_ENT_TL_SKILL_RATINGMST);
		//if(UIUtils.isValidKeyId(fromCreation))
			comboFilter.setCondSql(" AND SKRM_ORDERNO <> 0");
		//comboFilter.setCondSql(" AND SKIL_ISCHILD ='Y' "); 
		return commonFilterDao.fillComboValues(comboFilter);			
		//return entDeptFuncLinkDao.getTargetRatingCombo();
	}

	public List<String[]> getRattings() throws Exception {
		return entDeptFuncLinkDao.getRattings();
	}

	public List<String[]> getEmployeeGrid(String ErdlKeyid) throws Exception {
		return this.entDeptFuncLinkDao.getEmployeeGrid(ErdlKeyid);
	}

	public List<String[]> getDeptFuncView(String deptId, String cellId, String roleId) throws Exception {
		return this.entDeptFuncLinkDao.getDeptFuncView(deptId, cellId, roleId);
	}
	public List<String[]> checkLinkExists(String deptId, String cellId, String roleId) throws Exception {
		return entDeptFuncLinkDao.checkLinkExists(deptId, cellId, roleId);
	}
	
	
	public List<String[]> getDeptFuncLinkGrid(String erdlKeyid)throws Exception {
		return entDeptFuncLinkDao.getDeptFuncLinkGrid(erdlKeyid);
	}
	public List<String[]> getDepartmentType(String deptId)throws Exception {
		return entDeptFuncLinkDao.getDepartmentType(deptId);
	}

	public EntTlRoleDeptLinkmst create(EntTlRoleDeptLinkmst newEntTlRoleDeptLinkmst,EntTlRoleSkillLink newEntTlRoleSkillLink,
			EntTlRoleSkillRating newEntTlRoleSkillRating, DeptFuncLinkBean deptFuncLinkBean) throws Exception {
		try {
			CommonMessage.debugMsg("creaate service impl");
			String validationsFor;
			
			if (deptFuncLinkBean.getErdlKeyid().equals(""))	
				validationsFor = "create";
			else 
				validationsFor = "create";
					
			validations.validate(newEntTlRoleDeptLinkmst,"EntDeptFuncLinkCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
				
			if(UIUtils.isValidKeyId(deptFuncLinkBean.getErslKeyid()))			
				validationsFor = "update";
			else
				validationsFor = "create";
			
			validations.validate(newEntTlRoleSkillLink,"EntDeptFuncLinkCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations			
			
			fillValues(newEntTlRoleDeptLinkmst,deptFuncLinkBean);
			fillRoleSkillLink(newEntTlRoleSkillLink,deptFuncLinkBean);

			if(newEntTlRoleDeptLinkmst.getEntTlRoleSkillRatingList()!= null && newEntTlRoleDeptLinkmst.getEntTlRoleSkillRatingList().size()>=0) 
				newEntTlRoleDeptLinkmst.setEntTlRoleSkillRatingList(fillRoleSkillRattingValues(newEntTlRoleDeptLinkmst,newEntTlRoleSkillRating));
//			newPcsTlDtl.setCellid(newEntTlRoleDeptLinkmst.getPrlmCellid());		
			CommonMessage.debugMsg("After Filling Values");
			//CommonMessage.debugMsg("Serv Impl BDMDETAIL Size : "+newEntTlRoleDeptLinkmst.getPcsDetail().size());			
			return entDeptFuncLinkDao.create(newEntTlRoleDeptLinkmst,newEntTlRoleSkillLink,newEntTlRoleSkillRating, deptFuncLinkBean);
			
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
	}
	
	public String deleteRoleSkill(String ErslKeyid) throws Exception {
		return entDeptFuncLinkDao.deleteRoleSkill(ErslKeyid);
	}
	
	public EntTlRoleEmpLink createErdlEmployee(EntTlRoleEmpLink newEntTlRoleEmpLink,DeptFuncLinkBean deptFuncLinkBean) throws Exception {
		try { 
			String validationsFor;		
			validationsFor = "create";				
			validations.validate(newEntTlRoleEmpLink,"EntDeptFuncLinkCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
			erdlEmployeeFillValues(newEntTlRoleEmpLink);		
			return entDeptFuncLinkDao.createErdlEmployee(newEntTlRoleEmpLink,deptFuncLinkBean);
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
	}
	public String deleteErdlEmployee(String erdlKeyid, String empId) throws ValidationExceptions, Exception {
		return entDeptFuncLinkDao.deleteErdlEmployee(erdlKeyid, empId);
	}


	public EntTlRoleDeptLinkmst fillValues(EntTlRoleDeptLinkmst newEntTlRoleDeptLinkmst,DeptFuncLinkBean deptFuncLinkBean) throws Exception {
		
		newEntTlRoleDeptLinkmst.setErdlActive("Y");		
		String dateTime = CommonFunctions.dateTimeNow();
		//CommonMessage.debugMsg("Time : "+pcsEntryBean.get());
		CommonMessage.debugMsg("Key ID : "+newEntTlRoleDeptLinkmst.getErdlKeyid());
		//CommonMessage.debugMsg("Old CreatedON : "+oldPcsTlMst.getBdmsCreatedon());
	
		if(newEntTlRoleDeptLinkmst.getErdlKeyid() == null )
			newEntTlRoleDeptLinkmst.setErdlCreatedon(dateTime);
		else
			newEntTlRoleDeptLinkmst.setErdlCreatedon(dateTime);
		
		if(!UIUtils.isValidKeyId(newEntTlRoleDeptLinkmst.getErdlElectiveSkills()))
			newEntTlRoleDeptLinkmst.setErdlElectiveSkills("0");
			
		CommonMessage.debugMsg("newEntTlRoleDeptLinkmst.getErdlCellFunKeyid().substring(0, 1)"+newEntTlRoleDeptLinkmst.getErdlCellFunKeyid().substring(0, 1));
		
		newEntTlRoleDeptLinkmst.setErdlCellFunction(newEntTlRoleDeptLinkmst.getErdlCellFunKeyid().substring(0, 1));
					
		if(!UIUtils.isValidKeyId(newEntTlRoleDeptLinkmst.getErdlTempfield1()))
			newEntTlRoleDeptLinkmst.setErdlTempfield1("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleDeptLinkmst.getErdlTempfield2()))
			newEntTlRoleDeptLinkmst.setErdlTempfield2("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleDeptLinkmst.getErdlTempfield3()))
			newEntTlRoleDeptLinkmst.setErdlTempfield3("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleDeptLinkmst.getErdlTempfield4()))
			newEntTlRoleDeptLinkmst.setErdlTempfield4("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleDeptLinkmst.getErdlTempfield5()))
			newEntTlRoleDeptLinkmst.setErdlTempfield5("-");

		newEntTlRoleDeptLinkmst.setErdlActive("Y");
		
		newEntTlRoleDeptLinkmst.setErdlModifiedon(dateTime);
	
		return newEntTlRoleDeptLinkmst;
	}
	
	
public EntTlRoleSkillLink fillRoleSkillLink(EntTlRoleSkillLink newEntTlRoleSkillLink,DeptFuncLinkBean deptFuncLinkBean) throws Exception {
		
		newEntTlRoleSkillLink.setErslActive("Y");		
		String dateTime = CommonFunctions.dateTimeNow();
			
		CommonMessage.debugMsg("newEntTlRoleSkillLink.getErslRatingNumber()"+newEntTlRoleSkillLink.getErslRatingNumber());
		
		newEntTlRoleSkillLink.setErslEffFromDate(dateTime);			
		newEntTlRoleSkillLink.setErslEffTillDate(Constants.futureNullDate);
				
		if (newEntTlRoleSkillLink.getErslRatingType().equals("A")) {
			newEntTlRoleSkillLink.setErslRatingType("A");
			newEntTlRoleSkillLink.setErslRatingNumber("-1");			
		}
		else {
			newEntTlRoleSkillLink.setErslRatingNumber(newEntTlRoleSkillLink.getErslRatingType());
			newEntTlRoleSkillLink.setErslRatingType("H");						
		}
		
		newEntTlRoleSkillLink.setErslOrderno("1");
		
		
		//if(newEntTlRoleSkillLink.getErslKeyid() == null )
		newEntTlRoleSkillLink.setErslCreatedon(dateTime);
					
		if(!UIUtils.isValidKeyId(newEntTlRoleSkillLink.getErslTempfield1()))
			newEntTlRoleSkillLink.setErslTempfield1("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleSkillLink.getErslTempfield2()))
			newEntTlRoleSkillLink.setErslTempfield2("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleSkillLink.getErslTempfield3()))
			newEntTlRoleSkillLink.setErslTempfield3("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleSkillLink.getErslTempfield4()))
			newEntTlRoleSkillLink.setErslTempfield4("-");
		if(!UIUtils.isValidKeyId(newEntTlRoleSkillLink.getErslTempfield5()))
			newEntTlRoleSkillLink.setErslTempfield5("-");

		newEntTlRoleSkillLink.setErslActive("Y");		
		newEntTlRoleSkillLink.setErslModifiedon(dateTime);
	
		return newEntTlRoleSkillLink;
	}
	
	private List<EntTlRoleSkillRating>  fillRoleSkillRattingValues(EntTlRoleDeptLinkmst newEntTlRoleDeptLinkmst,
			EntTlRoleSkillRating entTlRoleSkillRating) throws Exception 
	{						
		String dateTime = CommonFunctions.dateTimeNow();
		//List<EntTlRoleSkillLink> newEntTlRoleSkillLinks = newEntTlRoleDeptLinkmst.getEntTlRoleSkillLinkList();		
		//CommonMessage.debugMsg(newEntTlRoleDeptLinkmst.getEntTlRoleSkillLinkList().size() + " : Size");
		String rattingDetails = newEntTlRoleDeptLinkmst.getRattingDetails();
		String[] rattingDetList = rattingDetails.split(",") ;
		
		List<EntTlRoleSkillRating> newEntTlRoleSkillRating = new ArrayList<EntTlRoleSkillRating>();
		//for( EntTlRoleSkillRating  :newEntTlRoleSkillRatings)
		CommonMessage.debugMsg("rattingDetList.length"+rattingDetList.length);
		for (int i=0;i<rattingDetList.length;i++)
		{	
			EntTlRoleSkillRating entTlRoleSkillRating2 = new EntTlRoleSkillRating();
			
			String[] rattingData = rattingDetList[i].split(":") ;
			CommonMessage.debugMsg("rattingData[0]"+rattingData[0]);
			CommonMessage.debugMsg("rattingData[1]"+rattingData[1]);
			
			//entTlRoleSkillRating2.setErsrRatingNumber(rattingData[1]);
			//entTlRoleSkillRating2.setErsrOrderno(rattingData[0]);
			
			entTlRoleSkillRating2.setErsrMinpoints(rattingData[1]);			
						
			entTlRoleSkillRating2.setErsrErdlKeyid(newEntTlRoleDeptLinkmst.getErdlKeyid());
			entTlRoleSkillRating2.setErsrCreatedon(dateTime);		
			entTlRoleSkillRating2.setErsrEffFromDate(dateTime);			
			entTlRoleSkillRating2.setErsrEffTillDate(Constants.futureNullDate);
			
			entTlRoleSkillRating2.setErsrCreatedby(entTlRoleSkillRating.getErsrCreatedby());
			entTlRoleSkillRating2.setErsrSkilKeyid(entTlRoleSkillRating.getErsrSkilKeyid());
			
			String skrmKeyid = entDeptFuncLinkDao.getRattingKeyid(rattingData[0]);  
			String sklmKeyid = entDeptFuncLinkDao.getRattingLevel(skrmKeyid);
			
			entTlRoleSkillRating2.setErsrSkrmKeyid(skrmKeyid);
			entTlRoleSkillRating2.setErsrSklmKeyid(sklmKeyid);
			
			if(!UIUtils.isValidKeyId(entTlRoleSkillRating2.getErsrTempfield1()))
				entTlRoleSkillRating2.setErsrTempfield1("-");
			if(!UIUtils.isValidKeyId(entTlRoleSkillRating2.getErsrTempfield2()))
				entTlRoleSkillRating2.setErsrTempfield2("-");
			if(!UIUtils.isValidKeyId(entTlRoleSkillRating2.getErsrTempfield3()))
				entTlRoleSkillRating2.setErsrTempfield3("-");
			if(!UIUtils.isValidKeyId(entTlRoleSkillRating2.getErsrTempfield4()))
				entTlRoleSkillRating2.setErsrTempfield4("-");
			if(!UIUtils.isValidKeyId(entTlRoleSkillRating2.getErsrTempfield5()))
				entTlRoleSkillRating2.setErsrTempfield5("-");
			entTlRoleSkillRating2.setErsrActive("Y");		
			entTlRoleSkillRating2.setErsrModifiedon(dateTime);
						
			newEntTlRoleSkillRating.add(entTlRoleSkillRating2);

		}
			
		return newEntTlRoleSkillRating;
	}
	
	private EntTlRoleEmpLink erdlEmployeeFillValues(EntTlRoleEmpLink newEntTlRoleEmpLink) 
	{
			CommonMessage.debugMsg("Detail 1");
			//CommonMessage.debugMsg("Chk Box : " +bdFormBean.getIssparesY());		
			String dateTime = CommonFunctions.dateTimeNow();
			
			newEntTlRoleEmpLink.setErelCurrentSkillratingid("{}");
			newEntTlRoleEmpLink.setErelTargetSkillratingid("{}");
			newEntTlRoleEmpLink.setErelEffFromDate(dateTime);			
			newEntTlRoleEmpLink.setErelEffTillDate(Constants.futureNullDate);
			
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
		
		return newEntTlRoleEmpLink;
	}
}