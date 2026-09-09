
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
/**
 * Author:N Arun
 * Created on:25.11.2011
 */
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.CompanyBean;
import com.akranta.tpm.bean.GenTlCompanymstBean;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlCompanymstDao;

import com.akranta.tpm.dao.impl.GenTlCompanymstDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;

import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlCompanymst;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.service.GenTlCompanymstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class GenTlCompanymstServiceImpl implements GenTlCompanymstService {
	private GenTlCompanymstDao genTlCompanymstDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	public GenTlCompanymstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlCompanymstDao =  new GenTlCompanymstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	

	public List<String[]> getAllCompany(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlCompanymstDao.getGenTlCompanymst(commonFilter);
	}
    
	public void setGenTlCompanymstDao(GenTlCompanymstDao genTlCompanymstDao)
	{
		this.genTlCompanymstDao = genTlCompanymstDao;
	}

	public GenTlCompanymst create(GenTlCompanymst newGenTlCompanymst,GenTlCompanymst oldGenTlCompanymst,  GenTlCompanymstBean genTlCompanymstBean) throws ValidationExceptions,Exception {

		try {
			CommonMessage.debugMsg("create service" + newGenTlCompanymst);
			String validationsFor;
			validationsFor = "create";
			validations.validate(newGenTlCompanymst,"companyCreation",validationsFor);
			fillValues(newGenTlCompanymst,oldGenTlCompanymst,genTlCompanymstBean);
			 CommonMessage.debugMsg("afete save fill ");
			// GenTlFunctionallocn  newGenTlFunctionallocn = fillFunctionLoc(newGenTlCompanymst);
			return genTlCompanymstDao.create(newGenTlCompanymst);
			
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}

	private GenTlCompanymst fillValues(GenTlCompanymst newGenTlCompanymst,GenTlCompanymst oldGenTlCompanymst,GenTlCompanymstBean genTlCompanymstBean) {
		CommonMessage.debugMsg("create service fill valye");
		newGenTlCompanymst.setCompActive("Y");
		CommonMessage.debugMsg("lllllll");
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		CommonMessage.debugMsg("lllllll"+ 	newGenTlCompanymst.getCompCreatedon());
		if(newGenTlCompanymst.getCompKeyid() == null )
			newGenTlCompanymst.setCompCreatedon(dateTime);
		else{
			CommonMessage.debugMsg("fillff" + oldGenTlCompanymst.getCompCreatedon() );
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
	private GenTlFunctionallocn fillFunctionLoc(GenTlCompanymst newGenTlCompanymst){
		CommonMessage.debugMsg("setFnlnOriginalid");
		CommonMessage.debugMsg("setFnlnOriginalid 123" +(newGenTlCompanymst.getCompKeyid())); 
		GenTlFunctionallocn  newGenTlFunctionallocn = new GenTlFunctionallocn();
		newGenTlFunctionallocn.setFnlnOriginalid(newGenTlCompanymst.getCompKeyid());
		newGenTlFunctionallocn.setFnlnElementid(newGenTlCompanymst.getCompKeyid());
		newGenTlFunctionallocn.setFnlnElementtype("CMP");
		newGenTlFunctionallocn.setFnlnParentid(newGenTlCompanymst.getCompKeyid());
		newGenTlFunctionallocn.setFnlnDisplaycode(newGenTlCompanymst.getCompCode());
		newGenTlFunctionallocn.setFnlnDescription(newGenTlCompanymst.getCompName());
		newGenTlFunctionallocn.setFnlnActive("Y");
		newGenTlCompanymst.setGenTlFunctionallocn(newGenTlFunctionallocn);
		CommonMessage.debugMsg("setFnlnOriginalid last");
		return newGenTlFunctionallocn;
	}


	public GenTlCompanymst update(GenTlCompanymst newGenTlCompanymst,GenTlCompanymst oldGenTlCompanymst,GenTlCompanymstBean genTlCompanymstBean) throws Exception {	// TODO Auto-generated method stub
			//return null;
		
		CommonMessage.debugMsg("updatee");
		validations.validate(newGenTlCompanymst,"companyCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		fillValues(newGenTlCompanymst,oldGenTlCompanymst,genTlCompanymstBean);
			
		return genTlCompanymstDao.update(newGenTlCompanymst);
	}
			
		

	@Override
	public GenTlCompanymst delete(GenTlCompanymst genTlCompanymst) throws Exception {
		
		return genTlCompanymstDao.delete(genTlCompanymst);
	}

	public GenTlCompanymst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlCompanymstDao.select(keyid);
	}

	public List<ComboBox> getCompanyComboList(CommonFilter commonFilter) throws Exception 
	{
		CommonMessage.debugMsg(" Company common service impl");
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
