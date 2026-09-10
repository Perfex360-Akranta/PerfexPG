package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlSectionmstBean;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.BAL_GenTlSectionmstDao;

import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlSectionmstDaoImpl;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.BAL_GenTlSectionmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;
import com.akranta.tpm.model.BAL_GenTlPbumst;
import com.akranta.tpm.model.BAL_GenTlSectionmst;
import com.akranta.tpm.service.BAL_GenTlSectionmstService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_GenTlSectionmstServiceImpl implements BAL_GenTlSectionmstService {
	private BAL_GenTlSectionmstDao genTlSectionmstDao;
	private BAL_CommonFilterDao commonFilterDao;
	private Validations validations ;
	public BAL_GenTlSectionmstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlSectionmstDao =  new BAL_GenTlSectionmstDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	

	public List<String[]> getAllSection(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlSectionmstDao.getGenTlSectionmst(commonFilter);
	}
    
	public void setGenTlSectionmstDao(BAL_GenTlSectionmstDao genTlSectionmstDao)
	{
		this.genTlSectionmstDao = genTlSectionmstDao;
	}

	public BAL_GenTlSectionmst create(BAL_GenTlSectionmst newGenTlSectionmst,BAL_GenTlSectionmst oldGenTlSectionmst,  BAL_GenTlSectionmstBean genTlSectionmstBean) throws ValidationExceptions,Exception {

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

	private BAL_GenTlSectionmst fillValues(BAL_GenTlSectionmst newGenTlSectionmst,BAL_GenTlSectionmst oldGenTlSectionmst,BAL_GenTlSectionmstBean genTlSectionmstBean) {
		newGenTlSectionmst.setSectActive("Y");
		CommonFunctions.debugMsg("seccccfill");
		String dateTime = CommonFunctions.dateTimeNow();
		System.out.println(dateTime);
		if(newGenTlSectionmst.getSectKeyid() == null )
			newGenTlSectionmst.setSectCreatedon(dateTime);
		else
			//newGenTlSectionmst.setSectCreatedon(oldGenTlSectionmst.getSectCreatedon());
			newGenTlSectionmst.setSectCreatedon(dateTime);
		//System.out.println(dateTime);
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
		CommonFunctions.debugMsg("secccc7777");
		return newGenTlSectionmst;
	
	}
	
	private BAL_GenTlFunctionallocn fillFunctionLoc(BAL_GenTlSectionmst newGenTlSectionmst){
		
		BAL_GenTlFunctionallocn  newGenTlFunctionallocn = new BAL_GenTlFunctionallocn();
		
		newGenTlFunctionallocn.setFnlnOriginalid(newGenTlSectionmst.getSectKeyid());
		
		//newGenTlFunctionallocn.setFnlnOriginalid(newGenTlSectionmst.getSectFactoryid());
		
		//CommonFunctions.isValidKeyId(newGenTlFunctionallocn.getFnlnElementid());
		//if	(newGenTlSectionmst.getSectFactoryid()!=null){
		
		//newGenTlFunctionallocn.setFnlnElementid(newGenTlLocationmst.getLocnCompanyid()+"-"+newGenTlLocationmst.getLocnKeyid());
			newGenTlFunctionallocn.setFnlnElementid(newGenTlSectionmst.getSectCompanyid()+"-"+newGenTlSectionmst.getSectKeyid());
		//}else {
			
		//	newGenTlFunctionallocn.setFnlnElementid(newGenTlSectionmst.getSectKeyid());
		//}
		newGenTlFunctionallocn.setFnlnElementtype("DMT");
		
		
		newGenTlFunctionallocn.setFnlnParentid(newGenTlSectionmst.getSectCompanyid());
		
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


	public BAL_GenTlSectionmst update(BAL_GenTlSectionmst newGenTlSectionmst,BAL_GenTlSectionmst oldGenTlSectionmst,BAL_GenTlSectionmstBean genTlSectionmstBean) throws Exception {
			// TODO Auto-generated method stub
			//return null;

			validations.validate(newGenTlSectionmst,"sectionCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			fillValues(newGenTlSectionmst,oldGenTlSectionmst,genTlSectionmstBean);
			
			return genTlSectionmstDao.update(newGenTlSectionmst);
	}

	@Override
	public BAL_GenTlSectionmst delete(String delemode,BAL_GenTlSectionmst genTlSectionmst) throws ValidationExceptions,Exception{
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
	public BAL_GenTlSectionmst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlSectionmstDao.select(keyid);	
		}


	@Override
	public BAL_GenTlSectionmst filldmtcontrol(String dMTkeyid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlSectionmstDao.filldmtcontrol(dMTkeyid);	
	}


 
	}
