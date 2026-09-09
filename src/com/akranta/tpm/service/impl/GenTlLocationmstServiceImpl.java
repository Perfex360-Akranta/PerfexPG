
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlLocationmstBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlLocationmstDao;

import com.akranta.tpm.dao.impl.GenTlLocationmstDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlLocationmst;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.service.GenTlLocationmstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class GenTlLocationmstServiceImpl implements GenTlLocationmstService {
	private GenTlLocationmstDao genTlLocationmstDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	public GenTlLocationmstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlLocationmstDao =  new GenTlLocationmstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	

	public List<String[]> getAllLocation(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlLocationmstDao.getGenTlLocationmst(commonFilter);
	}
    
	public void setGenTlLocationmstDao(GenTlLocationmstDao genTlLocationmstDao)
	{
		this.genTlLocationmstDao = genTlLocationmstDao;
	}

	public GenTlLocationmst create(GenTlLocationmst newGenTlLocationmst,GenTlLocationmst oldGenTlLocationmst,  GenTlLocationmstBean genTlLocationmstBean) throws ValidationExceptions,Exception {

		try {
			CommonMessage.debugMsg("create");
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


	private GenTlLocationmst fillValues(GenTlLocationmst newGenTlLocationmst,GenTlLocationmst oldGenTlLocationmst,GenTlLocationmstBean genTlLocationmstBean) 
	{
		newGenTlLocationmst.setLocnActive("Y");
		CommonMessage.debugMsg("llllll");
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
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
		
		CommonMessage.debugMsg("servi fill");
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
	
	private GenTlFunctionallocn fillFunctionLoc(GenTlLocationmst newGenTlLocationmst){
		CommonMessage.debugMsg("setFnlnOriginalid");
		GenTlFunctionallocn  newGenTlFunctionallocn = new GenTlFunctionallocn();
		
		newGenTlFunctionallocn.setFnlnOriginalid(newGenTlLocationmst.getLocnKeyid());
		
	

		//CommonFunctions.isValidKeyId(newGenTlFunctionallocn.getFnlnElementid());
		if	(newGenTlLocationmst.getLocnCompanyid()!=null){
			CommonMessage.debugMsg(" Service Impl1  ");
			newGenTlFunctionallocn.setFnlnElementid(newGenTlLocationmst.getLocnCompanyid()+"-"+newGenTlLocationmst.getLocnKeyid());
		}else {
			CommonMessage.debugMsg(" Service Impl13  ");

			
			newGenTlFunctionallocn.setFnlnElementid(newGenTlLocationmst.getLocnKeyid());
		}
		CommonMessage.debugMsg(" Service Impl133 ");

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
		CommonMessage.debugMsg("Fllliiddd          "+newGenTlFunctionallocn.getFnlnKeyid());
		
		newGenTlLocationmst.setGenTlFunctionallocn(newGenTlFunctionallocn);
		CommonMessage.debugMsg("ServiceImpl");
		return newGenTlFunctionallocn;
	}

	@Override
	public GenTlLocationmst update(GenTlLocationmst newGenTlLocationmst,GenTlLocationmst oldGenTlLocationmst,GenTlLocationmstBean genTlLocationmstBean) throws Exception {
		CommonMessage.debugMsg("updatee");
		validations.validate(newGenTlLocationmst,"GenTlLocationmst","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		fillValues(newGenTlLocationmst,oldGenTlLocationmst,genTlLocationmstBean);
	
		return genTlLocationmstDao.update(newGenTlLocationmst);
	}
	

	@Override
	public GenTlLocationmst delete(GenTlLocationmst genTlLocationmst) throws Exception {
		// TODO Auto-generated method stub
		return genTlLocationmstDao.delete(genTlLocationmst);
	}




	@Override
	public GenTlLocationmst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlLocationmstDao.select(keyid);	
		}


	

}







