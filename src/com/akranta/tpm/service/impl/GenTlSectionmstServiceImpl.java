package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlSectionmstBean;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlSectionmstDao;

import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlSectionmstDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;

import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GenTlPbumst;
import com.akranta.tpm.model.GenTlSectionmst;
import com.akranta.tpm.service.GenTlSectionmstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class GenTlSectionmstServiceImpl implements GenTlSectionmstService {
	private GenTlSectionmstDao genTlSectionmstDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	public GenTlSectionmstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlSectionmstDao =  new GenTlSectionmstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	

	public List<String[]> getAllSection(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlSectionmstDao.getGenTlSectionmst(commonFilter);
	}
    
	public void setGenTlSectionmstDao(GenTlSectionmstDao genTlSectionmstDao)
	{
		this.genTlSectionmstDao = genTlSectionmstDao;
	}

	public GenTlSectionmst create(GenTlSectionmst newGenTlSectionmst,GenTlSectionmst oldGenTlSectionmst,  GenTlSectionmstBean genTlSectionmstBean) throws ValidationExceptions,Exception {

		try {
			
			String validationsFor;
			
			/*if(genTlSectionmstBean.getFormActionMode() != null && genTlSectionmstBean.getFormActionMode().equals("whywhy") )
				validationsFor = "whywhy";
			else*/
				validationsFor = "create";
			
			validations.validate(newGenTlSectionmst,"sectionCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			fillValues(newGenTlSectionmst,oldGenTlSectionmst,genTlSectionmstBean);
			
			return genTlSectionmstDao.create(newGenTlSectionmst);
			//return newGenTlAssemblymst;
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}

	private GenTlSectionmst fillValues(GenTlSectionmst newGenTlSectionmst,GenTlSectionmst oldGenTlSectionmst,GenTlSectionmstBean genTlSectionmstBean) {
		newGenTlSectionmst.setSectActive("Y");
		CommonMessage.debugMsg("seccccfill");
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		if(newGenTlSectionmst.getSectKeyid() == null )
			newGenTlSectionmst.setSectCreatedon(dateTime);
		else
			//newGenTlSectionmst.setSectCreatedon(oldGenTlSectionmst.getSectCreatedon());
			newGenTlSectionmst.setSectCreatedon(dateTime);
		//CommonMessage.debugMsg(dateTime);
		newGenTlSectionmst.setSectModifiedon(dateTime);
		
		
		if( newGenTlSectionmst.getSectCode() == null )
			newGenTlSectionmst.setSectCode("{}");
		if( newGenTlSectionmst.getSectFactoryid() == null )
			newGenTlSectionmst.setSectFactoryid("{}");	
		if( newGenTlSectionmst.getSectName() == null )
			newGenTlSectionmst.setSectName("{}");
		if( newGenTlSectionmst.getSectCompanyid() == null )
			newGenTlSectionmst.setSectCompanyid("{}");
		if( newGenTlSectionmst.getSectKeyid() == null )
			newGenTlSectionmst.setSectKeyid("{}");
		if( newGenTlSectionmst.getSectSectiongroup() == null )
			newGenTlSectionmst.setSectSectiongroup("{}");
		
		if( newGenTlSectionmst.getSectFlid() == null )
			newGenTlSectionmst.setSectFlid("{}");
		
		
		fillFunctionLoc(newGenTlSectionmst);
		CommonMessage.debugMsg("secccc7777");
		return newGenTlSectionmst;
	
	}
	
	private GenTlFunctionallocn fillFunctionLoc(GenTlSectionmst newGenTlSectionmst){
		
		GenTlFunctionallocn  newGenTlFunctionallocn = new GenTlFunctionallocn();
		
		newGenTlFunctionallocn.setFnlnOriginalid(newGenTlSectionmst.getSectKeyid());
		
		//newGenTlFunctionallocn.setFnlnOriginalid(newGenTlSectionmst.getSectFactoryid());
		
		//CommonFunctions.isValidKeyId(newGenTlFunctionallocn.getFnlnElementid());
		//if	(newGenTlSectionmst.getSectFactoryid()!=null){
		
		//newGenTlFunctionallocn.setFnlnElementid(newGenTlLocationmst.getLocnCompanyid()+"-"+newGenTlLocationmst.getLocnKeyid());
		//	newGenTlFunctionallocn.setFnlnElementid(newGenTlSectionmst.getSectCompanyid()+"-"+newGenTlSectionmst.getSectKeyid());
		//}else {
			
		//	newGenTlFunctionallocn.setFnlnElementid(newGenTlSectionmst.getSectKeyid());
		//}
		newGenTlFunctionallocn.setFnlnElementtype("L");
		
		
		//newGenTlFunctionallocn.setFnlnParentid(newGenTlSectionmst.getSectCompanyid());
		
		newGenTlFunctionallocn.setFnlnElementid(newGenTlSectionmst.getSectCompanyid()+"-"+newGenTlSectionmst.getLocation()+"-"+newGenTlSectionmst.getSbu()+"-"+newGenTlSectionmst.getSectFactoryid()+"-"+newGenTlSectionmst.getSectKeyid());
	    newGenTlFunctionallocn.setFnlnParentid(newGenTlSectionmst.getSectCompanyid()+"-"+newGenTlSectionmst.getLocation()+"-"+newGenTlSectionmst.getSbu()+"-"+newGenTlSectionmst.getSectFactoryid());
		
		
		newGenTlFunctionallocn.setFnlnDisplaycode(newGenTlSectionmst.getSectCode());
		newGenTlFunctionallocn.setFnlnDescription(newGenTlSectionmst.getSectName());
		newGenTlFunctionallocn.setFnlnActive("Y");
		if(newGenTlFunctionallocn.getFnlnKeyid()==null)
		{
			if( newGenTlFunctionallocn.getFnlnKeyid() == null )
				newGenTlFunctionallocn.setFnlnKeyid("{}");
		}else
		{
			
		}
		newGenTlFunctionallocn.getFnlnKeyid();
		newGenTlSectionmst.setGenTlFunctionallocn(newGenTlFunctionallocn);
		return newGenTlFunctionallocn;
	}


	public GenTlSectionmst update(GenTlSectionmst newGenTlSectionmst,GenTlSectionmst oldGenTlSectionmst,GenTlSectionmstBean genTlSectionmstBean) throws Exception {
			// TODO Auto-generated method stub
			//return null;

			validations.validate(newGenTlSectionmst,"sectionCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			fillValues(newGenTlSectionmst,oldGenTlSectionmst,genTlSectionmstBean);
			
			return genTlSectionmstDao.update(newGenTlSectionmst);
	}

	@Override
	public GenTlSectionmst delete(String delemode,GenTlSectionmst genTlSectionmst) throws ValidationExceptions,Exception{
		// TODO Auto-generated method stub
		return genTlSectionmstDao.delete(delemode,genTlSectionmst);
	}

	@Override
	public List<ComboBox> getGenTlSectionmstcombo(String condSql) throws Exception {
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("SECT_CODE");
		comboFilter.setNameField("SECT_NAME");
		comboFilter.setIdField("SECT_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SECTIONMST);
		return commonFilterDao.fillComboValues(comboFilter);
		
			
	}
	public GenTlSectionmst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlSectionmstDao.select(keyid);	
		}


	@Override
	public GenTlSectionmst filldmtcontrol(String dMTkeyid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlSectionmstDao.filldmtcontrol(dMTkeyid);	
	}


	@Override
	public String getfunctionalid(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


 
	}
