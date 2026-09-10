package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlFactorymstBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.BAL_GenTlFactorymstDao;

import com.akranta.tpm.dao.impl.BAL_GenTlFactorymstDaoImpl;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.BAL_GenTlFactorymst;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;
import com.akranta.tpm.service.BAL_GenTlFactorymstService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_GenTlFactorymstServiceImpl implements BAL_GenTlFactorymstService {
	private BAL_GenTlFactorymstDao genTlFactorymstDao;
	private BAL_CommonFilterDao commonFilterDao;
	private Validations validations ;
	public BAL_GenTlFactorymstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlFactorymstDao =  new BAL_GenTlFactorymstDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	

	public List<String[]> getAllFactory(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlFactorymstDao.getGenTlFactorymst(commonFilter);
	}
    
	public void setGenTlFactorymstDao(BAL_GenTlFactorymstDao genTlFactorymstDao)
	{
		this.genTlFactorymstDao = genTlFactorymstDao;
	}

	public BAL_GenTlFactorymst create(BAL_GenTlFactorymst newGenTlFactorymst,BAL_GenTlFactorymst oldGenTlFactorymst,  BAL_GenTlFactorymstBean genTlFactorymstBean) throws ValidationExceptions,Exception {

			String validationsFor;
			
			validationsFor = "create";
			CommonFunctions.debugMsg(" validationsFor validationsFor" + validationsFor );
			validations.validate(newGenTlFactorymst,"factoryCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations\
			CommonFunctions.debugMsg(" validationsFor validationsFor" + validationsFor );
			fillValues(newGenTlFactorymst,oldGenTlFactorymst,genTlFactorymstBean);
			
			return genTlFactorymstDao.create(newGenTlFactorymst);
			//return null;
			
		
	}


	private BAL_GenTlFactorymst fillValues(BAL_GenTlFactorymst newGenTlFactorymst,BAL_GenTlFactorymst oldGenTlFactorymst,BAL_GenTlFactorymstBean genTlFactorymstBean) 
	{
		newGenTlFactorymst.setFactActive("Y");
		//System.out.println("llllll");
		String dateTime = CommonFunctions.dateTimeNow();
		//System.out.println(dateTime);
		if(newGenTlFactorymst.getFactKeyid() == null )
		{
			newGenTlFactorymst.setFactCreatedon(dateTime);
		}
		else
			newGenTlFactorymst.setFactCreatedon(oldGenTlFactorymst.getFactCreatedon());
		
		
		newGenTlFactorymst.setFactModifiedon(dateTime);
		
		
		if( newGenTlFactorymst.getFactCode() == null )
			newGenTlFactorymst.setFactCode("{}");
		if( newGenTlFactorymst.getFactCompanyid() == null )
			newGenTlFactorymst.setFactCompanyid("{}");
	
		if( newGenTlFactorymst.getFactName() == null )
			newGenTlFactorymst.setFactName("{}");
		if( newGenTlFactorymst.getFactAddress() == null )
			newGenTlFactorymst.setFactAddress("{}");
		if( newGenTlFactorymst.getFactLocationid() == null )
			newGenTlFactorymst.setFactLocationid("{}");
		
		if(  newGenTlFactorymst.getFactFlid() == null )
			newGenTlFactorymst.setFactFlid("{}");
		
		//fillFunctionLoc(newGenTlFactorymst);
		return newGenTlFactorymst;
		
	}
	
	private BAL_GenTlFunctionallocn fillFunctionLoc(BAL_GenTlFactorymst newGenTlFactorymst){
		System.out.println("setFnlnOriginalid" + newGenTlFactorymst.getFactLocationid());
		BAL_GenTlFunctionallocn  newGenTlFunctionallocn = new BAL_GenTlFunctionallocn();
		System.out.println("   ff" +CommonFunctions.isValidKeyId(newGenTlFactorymst.getFactLocationid()));
	
		if	(CommonFunctions.isValidKeyId(newGenTlFactorymst.getFactLocationid())){
		
			newGenTlFunctionallocn.setFnlnParentid(newGenTlFactorymst.getFactCompanyid() +"-"+newGenTlFactorymst.getFactLocationid() );
		}else {
			
			newGenTlFunctionallocn.setFnlnParentid(newGenTlFactorymst.getFactCompanyid() );
		}
		newGenTlFunctionallocn.setFnlnElementtype("F");
	
		newGenTlFunctionallocn.setFnlnDisplaycode(newGenTlFactorymst.getFactCode());
		newGenTlFunctionallocn.setFnlnDescription(newGenTlFactorymst.getFactName());
		newGenTlFunctionallocn.setFnlnActive("Y");
		newGenTlFactorymst.setGenTlFunctionallocn(newGenTlFunctionallocn);
		return newGenTlFunctionallocn;
	}

	@Override
	public BAL_GenTlFactorymst update(BAL_GenTlFactorymst newGenTlFactorymst,BAL_GenTlFactorymst oldGenTlFactorymst,BAL_GenTlFactorymstBean genTlFactorymstBean) throws Exception {
		System.out.println("updatee");
		validations.validate(newGenTlFactorymst,"factoryCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		fillValues(newGenTlFactorymst,oldGenTlFactorymst,genTlFactorymstBean);
	
		return genTlFactorymstDao.update(newGenTlFactorymst);
	}
	

	@Override
	public BAL_GenTlFactorymst delete(BAL_GenTlFactorymst genTlFactorymst) throws Exception {
		// TODO Auto-generated method stub
		return genTlFactorymstDao.delete(genTlFactorymst);
	}

	@Override
	public List<ComboBox> getGenTlFactorymstcombo(String compId) throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("LOCN_CODE");
		comboFilter.setNameField("LOCN_NAME");
		comboFilter.setIdField("LOCN_KEYID");
		comboFilter.setCondSql("AND  LOCN_COMPANYID ='"+ compId +"'");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_LOCATIONMST);
		return commonFilterDao.fillComboValues(comboFilter);
			
	}


	@Override
	public BAL_GenTlFactorymst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlFactorymstDao.select(keyid);	
		}


	

}







