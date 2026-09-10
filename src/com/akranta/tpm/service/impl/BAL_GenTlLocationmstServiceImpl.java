
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlLocationmstBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.BAL_GenTlLocationmstDao;

import com.akranta.tpm.dao.impl.BAL_GenTlLocationmstDaoImpl;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlLocationmst;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;
import com.akranta.tpm.service.BAL_GenTlLocationmstService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_GenTlLocationmstServiceImpl implements BAL_GenTlLocationmstService {
	private BAL_GenTlLocationmstDao genTlLocationmstDao;
	private BAL_CommonFilterDao commonFilterDao;
	private Validations validations ;
	public BAL_GenTlLocationmstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlLocationmstDao =  new BAL_GenTlLocationmstDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	

	public List<String[]> getAllLocation(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlLocationmstDao.getGenTlLocationmst(commonFilter);
	}
    
	public void setGenTlLocationmstDao(BAL_GenTlLocationmstDao genTlLocationmstDao)
	{
		this.genTlLocationmstDao = genTlLocationmstDao;
	}

	public BAL_GenTlLocationmst create(BAL_GenTlLocationmst newGenTlLocationmst,BAL_GenTlLocationmst oldGenTlLocationmst,  GenTlLocationmstBean genTlLocationmstBean) throws ValidationExceptions,Exception {

		try {
			System.out.println("create");
			String validationsFor;
			
			/*if(genTlFactorymstBean.getFormActionMode() != null && genTlFactorymstBean.getFormActionMode().equals("whywhy") )
				validationsFor = "whywhy";
			else*/
				validationsFor = "create";
			
			validations.validate(newGenTlLocationmst,"GenTlLocationmst",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			fillValues(newGenTlLocationmst,oldGenTlLocationmst,genTlLocationmstBean);
			return genTlLocationmstDao.create(newGenTlLocationmst);
			//return newKznTlMst;
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}


	private BAL_GenTlLocationmst fillValues(BAL_GenTlLocationmst newGenTlLocationmst,BAL_GenTlLocationmst oldGenTlLocationmst,GenTlLocationmstBean genTlLocationmstBean) 
	{
		newGenTlLocationmst.setLocnActive("Y");
		System.out.println("llllll");
		String dateTime = CommonFunctions.dateTimeNow();
		System.out.println(dateTime);
		if(newGenTlLocationmst.getLocnKeyid() == null )
		{
			newGenTlLocationmst.setLocnCreatedon(dateTime);
			if( newGenTlLocationmst.getLocnFlid() == null )
				newGenTlLocationmst.setLocnFlid("{}");
		}
		else{
			
			newGenTlLocationmst.setLocnCreatedon(oldGenTlLocationmst.getLocnCreatedon());
			
			if( newGenTlLocationmst.getLocnFlid() == null )
				newGenTlLocationmst.setLocnFlid(oldGenTlLocationmst.getLocnFlid());
			
		}
		
		newGenTlLocationmst.setLocnModifiedon(dateTime);
		
		System.out.println("servi fill");
		if( newGenTlLocationmst.getLocnCode() == null )
			newGenTlLocationmst.setLocnCode("{}");
		if( newGenTlLocationmst.getLocnCompanyid() == null )
			newGenTlLocationmst.setLocnCompanyid("{}");
	
		if( newGenTlLocationmst.getLocnName() == null )
			newGenTlLocationmst.setLocnName("{}");
		if( newGenTlLocationmst.getLocnDescription() == null )
			newGenTlLocationmst.setLocnDescription("{}");
		if( newGenTlLocationmst.getLocnTempfield1() == null )
			newGenTlLocationmst.setLocnTempfield1("{}");
		if( newGenTlLocationmst.getLocnTempfield2() == null )
			newGenTlLocationmst.setLocnTempfield2("{}");
		if( newGenTlLocationmst.getLocnTempfield3() == null )
			newGenTlLocationmst.setLocnTempfield3("{}");
		//if( newGenTlLocationmst.getLocnFlid() == null )
			//newGenTlLocationmst.setLocnFlid("{}");
		
			fillFunctionLoc(newGenTlLocationmst);
		return newGenTlLocationmst;
		
	}
	
	private BAL_GenTlFunctionallocn fillFunctionLoc(BAL_GenTlLocationmst newGenTlLocationmst){
		System.out.println("setFnlnOriginalid");
		BAL_GenTlFunctionallocn  newGenTlFunctionallocn = new BAL_GenTlFunctionallocn();
		
		newGenTlFunctionallocn.setFnlnOriginalid(newGenTlLocationmst.getLocnKeyid());
		
	

		//CommonFunctions.isValidKeyId(newGenTlFunctionallocn.getFnlnElementid());
		if	(newGenTlLocationmst.getLocnCompanyid()!=null){
			CommonFunctions.debugMsg(" Service Impl1  ");
			newGenTlFunctionallocn.setFnlnElementid(newGenTlLocationmst.getLocnCompanyid()+"-"+newGenTlLocationmst.getLocnKeyid());
		}else {
			CommonFunctions.debugMsg(" Service Impl13  ");

			
			newGenTlFunctionallocn.setFnlnElementid(newGenTlLocationmst.getLocnKeyid());
		}
		CommonFunctions.debugMsg(" Service Impl133 ");

		newGenTlFunctionallocn.setFnlnElementtype("LCN");
		
		newGenTlFunctionallocn.setFnlnParentid(newGenTlLocationmst.getLocnCompanyid() );
		
		newGenTlFunctionallocn.setFnlnDisplaycode(newGenTlLocationmst.getLocnCode());
		newGenTlFunctionallocn.setFnlnDescription(newGenTlLocationmst.getLocnName());
		newGenTlFunctionallocn.setFnlnActive("Y");
		if(newGenTlFunctionallocn.getFnlnKeyid()==null)
		{
			if( newGenTlFunctionallocn.getFnlnKeyid() == null )
				newGenTlFunctionallocn.setFnlnKeyid("{}");
		}else
		{
			
		}
		newGenTlFunctionallocn.getFnlnKeyid();
		//newGenTlFunctionallocn.setFnlnKeyid(newGenTlLocationmst.getLocnFlid());
		CommonFunctions.debugMsg("Fllliiddd          "+newGenTlFunctionallocn.getFnlnKeyid());
		
		newGenTlLocationmst.setGenTlFunctionallocn(newGenTlFunctionallocn);
		CommonFunctions.debugMsg("ServiceImpl");
		return newGenTlFunctionallocn;
	}

	@Override
	public BAL_GenTlLocationmst update(BAL_GenTlLocationmst newGenTlLocationmst,BAL_GenTlLocationmst oldGenTlLocationmst,GenTlLocationmstBean genTlLocationmstBean) throws Exception {
		System.out.println("updatee");
		validations.validate(newGenTlLocationmst,"GenTlLocationmst","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		fillValues(newGenTlLocationmst,oldGenTlLocationmst,genTlLocationmstBean);
	
		return genTlLocationmstDao.update(newGenTlLocationmst);
	}
	

	@Override
	public BAL_GenTlLocationmst delete(BAL_GenTlLocationmst genTlLocationmst) throws Exception {
		// TODO Auto-generated method stub
		return genTlLocationmstDao.delete(genTlLocationmst);
	}




	@Override
	public BAL_GenTlLocationmst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlLocationmstDao.select(keyid);	
		}


	

}







