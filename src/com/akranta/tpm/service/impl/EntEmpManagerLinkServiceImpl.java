package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EmpMangerLinkBean;
//import com.akranta.tpm.bean.MouldFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlEmpmanagerdtlDao;
import com.akranta.tpm.dao.impl.EntTlEmpmanagerdtlDaoImpl;
import com.akranta.tpm.dao.EntTlEmpmanagermstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.EntTlEmpmanagermstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.EntTlEmpmanagermstModel;
import com.akranta.tpm.model.EntTlEmpmanagerdtl;
//import com.akranta.tpm.model.GenTlMouldmachinelink;
import com.akranta.tpm.model.GenTlEmployeemst;
//import com.akranta.tpm.model.GenTlMouldmst;
import com.akranta.tpm.service.EntEmpMangerLinkService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class EntEmpManagerLinkServiceImpl implements EntEmpMangerLinkService{
	private CommonFilterDao commonFilterDao;
	private EntTlEmpmanagermstDao entTlEmpmanagermstDao;
	private EntTlEmpmanagerdtlDao entTlEmpmanagerdtlDao;
	
	private Validations validations ;
	String dateTime = CommonFunctions.dateTimeNow();
	public EntEmpManagerLinkServiceImpl(DBActionTemplate dbActionTemplate)
	{
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		entTlEmpmanagermstDao = new EntTlEmpmanagermstDaoImpl(dbActionTemplate);
		entTlEmpmanagerdtlDao = new EntTlEmpmanagerdtlDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	public List<String[]> getEmployeeDet(String ManIdField , String EmpIdField)throws Exception
	{
		return this.entTlEmpmanagerdtlDao.getEmployeeDet(ManIdField,EmpIdField);
	}
	
	public List<String[]> getEmployeeDetView()throws Exception
	{
		return this.entTlEmpmanagerdtlDao.getEmployeeDetView();
	}
	public EntTlEmpmanagerdtl select(String manIdField) throws Exception
	{
		return this.entTlEmpmanagerdtlDao.select(manIdField);
	}
	
	public List<ComboBox> getManagerId(String condSql) throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);

		return commonFilterDao.fillComboValues(comboFilter);	
	}
	public List<ComboBox> getEmployeeId(String condSql) throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);

		return commonFilterDao.fillComboValues(comboFilter);	
	}
	@Override
	
	public EntTlEmpmanagermstModel create(EntTlEmpmanagermstModel entTlEmpmanagermstModel,	EntTlEmpmanagermstModel oldEntTlEmpmanagermstModel, EmpMangerLinkBean empMangerLinkBean)throws ValidationExceptions, Exception {
	try {			
		
			String validationsFor;
			validationsFor = "create";		
			
			validations.validate(entTlEmpmanagermstModel,"EntEmpManLinkValidation",validationsFor);			
			validations.validate(empMangerLinkBean,"EntEmpManLinkValidation",validationsFor);
			
			EntTlEmpmanagerdtl newEntTlEmpmanagerdtl = entTlEmpmanagermstModel.getEntTlEmpmanagerdtl();			
			validations.validate(newEntTlEmpmanagerdtl,"EntEmpManLinkValidation",validationsFor);
			
			fillValues(entTlEmpmanagermstModel,oldEntTlEmpmanagermstModel,empMangerLinkBean);
			
			return entTlEmpmanagermstDao.create(entTlEmpmanagermstModel);		
			
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			
			throw new ValidationExceptions(e.getMessage());
		}
		
		
	}

	@Override
	public EntTlEmpmanagermstModel update(EntTlEmpmanagermstModel entTlEmpmanagermstModel,EntTlEmpmanagermstModel oldEntTlEmpmanagermstModel,  EmpMangerLinkBean empMangerLinkBean )  throws Exception	
	 {
		
		try {
			
			String validationsFor;
			validationsFor = "update";		
			validations.validate(entTlEmpmanagermstModel,"EntEmpManLinkValidation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations			
		
			EntTlEmpmanagerdtl newEntTlEmpmanagerdtl = entTlEmpmanagermstModel.getEntTlEmpmanagerdtl();			
			validations.validate(newEntTlEmpmanagerdtl,"EntEmpManLinkValidation",validationsFor);
			
			fillValues(entTlEmpmanagermstModel,oldEntTlEmpmanagermstModel,empMangerLinkBean);
			
			return entTlEmpmanagermstDao.update(entTlEmpmanagermstModel);		
			
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	private EntTlEmpmanagermstModel fillValues(EntTlEmpmanagermstModel entTlEmpmanagermstModel,EntTlEmpmanagermstModel oldEntTlEmpmanagermstModel, EmpMangerLinkBean empMangerLinkBean) {
		
		
	
		if(!UIUtils.isValidKeyId(entTlEmpmanagermstModel.getEemmManagerId()))
			entTlEmpmanagermstModel.setEemmManagerId("{}");					
		if(!UIUtils.isValidKeyId(entTlEmpmanagermstModel.getEemmMailid()))
			entTlEmpmanagermstModel.setEemmMailid("{}");
		if(!UIUtils.isValidKeyId(entTlEmpmanagermstModel.getEemmMobileno()))
			entTlEmpmanagermstModel.setEemmMobileno("0");
		if(!UIUtils.isValidKeyId(entTlEmpmanagermstModel.getEemmTempfiled1()))
			entTlEmpmanagermstModel.setEemmTempfiled1("-");
		if(!UIUtils.isValidKeyId(entTlEmpmanagermstModel.getEemmTempfiled2()))
			entTlEmpmanagermstModel.setEemmTempfiled2("-");
		if(!UIUtils.isValidKeyId(entTlEmpmanagermstModel.getEemmTempfiled3()))
			entTlEmpmanagermstModel.setEemmTempfiled3("-");		
			entTlEmpmanagermstModel.setEemmActive("Y");
			entTlEmpmanagermstModel.setEemmCreatedby(entTlEmpmanagermstModel.getEemmCreatedby());
			entTlEmpmanagermstModel.setEemmCreatedon(dateTime);
			entTlEmpmanagermstModel.setEemmModifiedon(dateTime);
			if(entTlEmpmanagermstModel.getEntTlEmpmanagerdtl() != null){
				 CommonMessage.debugMsg("detail52");
			entTlEmpmanagermstModel.setEntTlEmpmanagerdtl(detailFillValues(entTlEmpmanagermstModel,oldEntTlEmpmanagermstModel));	
			}
			
			CommonMessage.debugMsg("------------------"+entTlEmpmanagermstModel.getEemmModifiedon());
		return entTlEmpmanagermstModel;
		}
	
	private EntTlEmpmanagerdtl  detailFillValues(EntTlEmpmanagermstModel entTlEmpmanagermstModel,EntTlEmpmanagermstModel oldEntTlEmpmanagermstModel) 
	{
		EntTlEmpmanagerdtl newEntTlEmpmanagerdtl = entTlEmpmanagermstModel.getEntTlEmpmanagerdtl();
		if(newEntTlEmpmanagerdtl != null)
			 CommonMessage.debugMsg("detail25");
		CommonMessage.debugMsg(newEntTlEmpmanagerdtl.getEemdEemmKeyid() + " : 999Size");
		
		//EntTlEmpmanagerdtl entTlEmpmanagerdtl = new EntTlEmpmanagerdtl();
		//for( EntTlEmpmanagerdtl entTlEmpmanagerdtl :newEntTlEmpmanagerdtl)
		//{	CommonMessage.debugMsg(" INSIDE FOR ");
			if(!UIUtils.isValidKeyId(newEntTlEmpmanagerdtl.getEemdEemmKeyid())){
				CommonMessage.debugMsg(newEntTlEmpmanagerdtl.getEemdEemmKeyid() + " : 955559Size");
				newEntTlEmpmanagerdtl.setEemdEemmKeyid("{}");
			}
			
			if(!UIUtils.isValidKeyId(newEntTlEmpmanagerdtl.getEemdEmpmKeyid()))
			newEntTlEmpmanagerdtl.setEemdEmpmKeyid("{}");
			if(!UIUtils.isValidKeyId(newEntTlEmpmanagerdtl.getEemdMailid()))
			newEntTlEmpmanagerdtl.setEemdMailid("{}");
			if(!UIUtils.isValidKeyId(newEntTlEmpmanagerdtl.getEemdMobileno()))
			newEntTlEmpmanagerdtl.setEemdMobileno("0");
			if(!UIUtils.isValidKeyId(newEntTlEmpmanagerdtl.getEemdTempfiled1()))
			newEntTlEmpmanagerdtl.setEemdTempfiled1("-");
			if(!UIUtils.isValidKeyId(newEntTlEmpmanagerdtl.getEemdTempfiled2()))
			newEntTlEmpmanagerdtl.setEemdTempfiled2("-");
			if(!UIUtils.isValidKeyId(newEntTlEmpmanagerdtl.getEemdTempfiled3()))
			newEntTlEmpmanagerdtl.setEemdTempfiled3("-");
			
			newEntTlEmpmanagerdtl.setEemdActive("Y");
			newEntTlEmpmanagerdtl.setEemdCreatedby(entTlEmpmanagermstModel.getEemmCreatedby());
			newEntTlEmpmanagerdtl.setEemdCreatedon(dateTime);
			newEntTlEmpmanagerdtl.setEemdModifiedon(dateTime);
			
			CommonMessage.debugMsg(newEntTlEmpmanagerdtl.getEemdActive() + " : Mould ID");			
			//newEntTlEmpmanagerdtllist.add(newEntTlEmpmanagerdtl);	
		//}
			
		return newEntTlEmpmanagerdtl;
	}
	@Override
	public String delete(EntTlEmpmanagermstModel entTlEmpmanagermstModel) throws Exception 
	{
		// TODO Auto-generated method stub
		return entTlEmpmanagermstDao.delete( entTlEmpmanagermstModel);
	}
	
	@Override
	public void DeleteEmployeeDet(String EmpDetailkeyId) throws Exception {
		// TODO Auto-generated method stub
		entTlEmpmanagermstDao.DeleteEmployeeDet(EmpDetailkeyId);
	}
	
}
