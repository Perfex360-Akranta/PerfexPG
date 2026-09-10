
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
/**
 * Author:N Arun
 * Created on:25.11.2011
 */
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_CompanyBean;
import com.akranta.tpm.bean.BAL_GenTlCompanymstBean;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.BAL_GenTlCompanymstDao;

import com.akranta.tpm.dao.impl.GenTlCompanymstDaoImpl;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.BAL_GenTlCompanymstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlCompanymst;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;
import com.akranta.tpm.service.BAL_GenTlCompanymstService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_GenTlCompanymstServiceImpl implements BAL_GenTlCompanymstService {
	private BAL_GenTlCompanymstDao genTlCompanymstDao;
	private BAL_CommonFilterDao commonFilterDao;
	private Validations validations ;
	public BAL_GenTlCompanymstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlCompanymstDao =  new BAL_GenTlCompanymstDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	

	public List<String[]> getAllCompany(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlCompanymstDao.getGenTlCompanymst(commonFilter);
	}
    
	public void setGenTlCompanymstDao(BAL_GenTlCompanymstDao genTlCompanymstDao)
	{
		this.genTlCompanymstDao = genTlCompanymstDao;
	}

	public BAL_GenTlCompanymst create(BAL_GenTlCompanymst newGenTlCompanymst,BAL_GenTlCompanymst oldGenTlCompanymst,  BAL_GenTlCompanymstBean genTlCompanymstBean) throws ValidationExceptions,Exception {

		try {
			CommonFunctions.debugMsg("create service" + newGenTlCompanymst);
			String validationsFor;
			validationsFor = "create";
			validations.validate(newGenTlCompanymst,"companyCreation",validationsFor);
			fillValues(newGenTlCompanymst,oldGenTlCompanymst,genTlCompanymstBean);
			 CommonFunctions.debugMsg("afete save fill ");
			// GenTlFunctionallocn  newGenTlFunctionallocn = fillFunctionLoc(newGenTlCompanymst);
			return genTlCompanymstDao.create(newGenTlCompanymst);
			
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}

	private BAL_GenTlCompanymst fillValues(BAL_GenTlCompanymst newGenTlCompanymst,BAL_GenTlCompanymst oldGenTlCompanymst,BAL_GenTlCompanymstBean genTlCompanymstBean) {
		CommonFunctions.debugMsg("create service fill valye");
		newGenTlCompanymst.setCompActive("Y");
		CommonFunctions.debugMsg("lllllll");
		String dateTime = CommonFunctions.dateTimeNow();
		System.out.println(dateTime);
		CommonFunctions.debugMsg("lllllll"+ 	newGenTlCompanymst.getCompCreatedon());
		if(newGenTlCompanymst.getCompKeyid() == null )
			newGenTlCompanymst.setCompCreatedon(dateTime);
		else{
			CommonFunctions.debugMsg("fillff" + oldGenTlCompanymst.getCompCreatedon() );
			newGenTlCompanymst.setCompCreatedon(oldGenTlCompanymst.getCompCreatedon());
			}
			newGenTlCompanymst.setCompModifiedon(dateTime);
		if( newGenTlCompanymst.getCompCode() == null )
			newGenTlCompanymst.setCompCode("{}");
			
		if( newGenTlCompanymst.getCompName() == null )
			newGenTlCompanymst.setCompName("{}");
		
		if( newGenTlCompanymst.getCompAddress() == null )
			newGenTlCompanymst.setCompAddress("{}");
		
		if( newGenTlCompanymst.getCompKeyid() == null )
			newGenTlCompanymst.setCompKeyid("{}");
	
		if( newGenTlCompanymst.getCompKeyid() == null )
			newGenTlCompanymst.setCompKeyid("{}");
	
		fillFunctionLoc(newGenTlCompanymst);
		return newGenTlCompanymst;
		
	}
	private BAL_GenTlFunctionallocn fillFunctionLoc(BAL_GenTlCompanymst newGenTlCompanymst){
		CommonFunctions.debugMsg("setFnlnOriginalid");
		CommonFunctions.debugMsg("setFnlnOriginalid 123" +(newGenTlCompanymst.getCompKeyid())); 
		BAL_GenTlFunctionallocn  newGenTlFunctionallocn = new BAL_GenTlFunctionallocn();
		newGenTlFunctionallocn.setFnlnOriginalid(newGenTlCompanymst.getCompKeyid());
		newGenTlFunctionallocn.setFnlnElementid(newGenTlCompanymst.getCompKeyid());
		newGenTlFunctionallocn.setFnlnElementtype("CMP");
		newGenTlFunctionallocn.setFnlnParentid(newGenTlCompanymst.getCompKeyid());
		newGenTlFunctionallocn.setFnlnDisplaycode(newGenTlCompanymst.getCompCode());
		newGenTlFunctionallocn.setFnlnDescription(newGenTlCompanymst.getCompName());
		newGenTlFunctionallocn.setFnlnActive("Y");
		newGenTlCompanymst.setGenTlFunctionallocn(newGenTlFunctionallocn);
		CommonFunctions.debugMsg("setFnlnOriginalid last");
		return newGenTlFunctionallocn;
	}


	public BAL_GenTlCompanymst update(BAL_GenTlCompanymst newGenTlCompanymst,BAL_GenTlCompanymst oldGenTlCompanymst,BAL_GenTlCompanymstBean genTlCompanymstBean) throws Exception {	// TODO Auto-generated method stub
			//return null;
		
		CommonFunctions.debugMsg("updatee");
		validations.validate(newGenTlCompanymst,"companyCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		fillValues(newGenTlCompanymst,oldGenTlCompanymst,genTlCompanymstBean);
			
		return genTlCompanymstDao.update(newGenTlCompanymst);
	}
			
		

	@Override
	public BAL_GenTlCompanymst delete(BAL_GenTlCompanymst genTlCompanymst) throws Exception {
		
		return genTlCompanymstDao.delete(genTlCompanymst);
	}

	public BAL_GenTlCompanymst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlCompanymstDao.select(keyid);
	}

	public List<ComboBox> getCompanyComboList(CommonFilter commonFilter) throws Exception 
	{
		CommonFunctions.debugMsg(" Company common service impl");
		ComboFilter comp = commonFilter.getCompany();
		comp.setIdField("COMP_KEYID");
		comp.setCodeField("COMP_CODE");
		comp.setNameField("COMP_NAME");
		comp.setTableName(TableNames.TBL_GEN_TL_COMPANYMST);
		
		return commonFilterDao.fillComboValues(comp);
	}

	
/*	public GenTlCompanymst assmformfill(String keyId) {
		// TODO Auto-generated method stub
		return this.genTlCompanymstDao.assmformfill(keyId);
	}*/

}
