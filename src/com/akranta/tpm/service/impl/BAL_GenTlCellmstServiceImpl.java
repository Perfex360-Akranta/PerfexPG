package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_CompanyBean;

import com.akranta.tpm.bean.BAL_GenTlCellmstBean;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.BAL_GenTlCellmstDao;

import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.BAL_GenTlCellmstDaoImpl;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;

import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;

import com.akranta.tpm.model.BAL_GenTlCellmst;
import com.akranta.tpm.service.BAL_GenTlCellmstService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_GenTlCellmstServiceImpl implements BAL_GenTlCellmstService {
	private BAL_GenTlCellmstDao genTlCellmstDao;
	private BAL_CommonFilterDao commonFilterDao;
	private Validations validations ;
	public BAL_GenTlCellmstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlCellmstDao =  new BAL_GenTlCellmstDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	

	public List<String[]> getAllGenTlCellmst(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlCellmstDao.getGenTlCellmst(commonFilter);
	}
    
	public void setGenTlCellmstDao(BAL_GenTlCellmstDao genTlCellmstDao)
	{
		this.genTlCellmstDao = genTlCellmstDao;
	}

	public BAL_GenTlCellmst create(BAL_GenTlCellmst newGenTlCellmst,BAL_GenTlCellmst oldGenTlCellmst,  BAL_GenTlCellmstBean genTlCellmstBean) throws ValidationExceptions,Exception {

		try {
			System.out.println("before save fill service");
			String validationsFor;
			
			validationsFor = "create";
			
			validations.validate(newGenTlCellmst,"cellCreation",validationsFor);
			fillValues(newGenTlCellmst,oldGenTlCellmst,genTlCellmstBean);
			System.out.println("after save fill ");
			return genTlCellmstDao.create(newGenTlCellmst);
		
			
		}catch (ValidationExceptions e){
				System.out.println(e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	public String getCompany(BAL_GenTlCellmst genTlCellmst) throws SQLException
	{
		return genTlCellmstDao.getCompany(genTlCellmst);
	}

	private BAL_GenTlCellmst fillValues(BAL_GenTlCellmst newGenTlCellmst,BAL_GenTlCellmst oldGenTlCellmst,BAL_GenTlCellmstBean genTlCellmstBean) {
		newGenTlCellmst.setCellActive("Y");
		System.out.println("lllllll");
		String dateTime = CommonFunctions.dateTimeNow();
		System.out.println(dateTime);
		
		if(newGenTlCellmst.getCellKeyid() == null )   
			newGenTlCellmst.setCellCreatedon(dateTime);
		else
			//newGenTlCellmst.setCellCreatedon(oldGenTlCellmst.getCellCreatedon());
			newGenTlCellmst.setCellCreatedon(dateTime);
		    newGenTlCellmst.setCellModifiedon(dateTime);
		
			System.out.println("fillvalue start");
		if( newGenTlCellmst.getCellCode() == null )
			newGenTlCellmst.setCellCode("{}");
		
		if( newGenTlCellmst.getCellCompanyid() == null )
			newGenTlCellmst.setCellCompanyid("{}");
	
		if( newGenTlCellmst.getCellName() == null )
			newGenTlCellmst.setCellName("{}");
		
		if( newGenTlCellmst.getCellCellorder()== null )
			newGenTlCellmst.setCellCellorder("0");
		
		if( newGenTlCellmst.getCellFactoryid() == null )
			newGenTlCellmst.setCellFactoryid("{}");
	
		if( newGenTlCellmst.getCellSectionid() == null )
			newGenTlCellmst.setCellSectionid("{}");
		
		if( newGenTlCellmst.getCellCostcentreid() == null )
			newGenTlCellmst.setCellCostcentreid("{}");
		
		if( newGenTlCellmst.getCellEffectivedate() == null )
			newGenTlCellmst.setCellEffectivedate(dateTime);
		
		if( newGenTlCellmst.getCellInactivateddate() == null )
			newGenTlCellmst.setCellInactivateddate(dateTime);
			
		if( newGenTlCellmst.getCellLevelno()== null )
			newGenTlCellmst.setCellLevelno("0");	
		
		if( newGenTlCellmst.getCellPcname()== null )	
			newGenTlCellmst.setCellPcname("{}");
		
		if( newGenTlCellmst.getCellSectiongroup()== null )
			newGenTlCellmst.setCellSectiongroup("{}");
		
	
		if( newGenTlCellmst.getCellKeyid()== null )
			newGenTlCellmst.setCellKeyid("{}");	
		
		if( newGenTlCellmst.getCellFlid()== null)
			newGenTlCellmst.setCellFlid("{}");
		
			fillFunctionLoc(newGenTlCellmst);
		return newGenTlCellmst;
		
	}
	
	private BAL_GenTlFunctionallocn fillFunctionLoc(BAL_GenTlCellmst newGenTlCellmst){
		System.out.println("setFnlnOriginalid");
		BAL_GenTlFunctionallocn  newGenTlFunctionallocn = new BAL_GenTlFunctionallocn();
		
		newGenTlFunctionallocn.setFnlnOriginalid(newGenTlCellmst.getCellKeyid());
		newGenTlFunctionallocn.setFnlnElementid(newGenTlCellmst.getCellKeyid());
		

			newGenTlFunctionallocn.setFnlnElementid(newGenTlCellmst.getCellCompanyid()+"-"+newGenTlCellmst.getCellFactoryid()+"-"+newGenTlCellmst.getCellSectionid()+"-"+newGenTlCellmst.getCellKeyid());
		
		newGenTlFunctionallocn.setFnlnElementtype("JH");
		
		newGenTlFunctionallocn.setFnlnParentid(newGenTlCellmst.getCellFactoryid()+"-"+newGenTlCellmst.getCellSectionid() );
		
		newGenTlFunctionallocn.setFnlnDisplaycode(newGenTlCellmst.getCellCode());
		newGenTlFunctionallocn.setFnlnDescription(newGenTlCellmst.getCellName());
		newGenTlFunctionallocn.setFnlnActive("Y");
		if(newGenTlFunctionallocn.getFnlnKeyid()==null)
		{
			if( newGenTlFunctionallocn.getFnlnKeyid() == null )
				newGenTlFunctionallocn.setFnlnKeyid("{}");
		}else
		{
			
		}
		newGenTlFunctionallocn.getFnlnKeyid();
		newGenTlCellmst.setGenTlFunctionallocn(newGenTlFunctionallocn);
		return newGenTlFunctionallocn;
	}

	public BAL_GenTlCellmst update(BAL_GenTlCellmst newGenTlCellmst,BAL_GenTlCellmst oldGenTlCellmst,BAL_GenTlCellmstBean genTlCellmstBean) throws Exception {	// TODO Auto-generated method stub
			
				System.out.println("updatee" +newGenTlCellmst);
				validations.validate(newGenTlCellmst,"cellCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
				fillValues(newGenTlCellmst,oldGenTlCellmst,genTlCellmstBean);
			
			return genTlCellmstDao.update(newGenTlCellmst);
	}
			
	
	@Override
	public BAL_GenTlCellmst delete(String delemode,BAL_GenTlCellmst genTlCellmst) throws Exception {
		// TODO Auto-generated method stub
		return genTlCellmstDao.delete(delemode,genTlCellmst);
	}

	
	

	@Override
	public BAL_GenTlCellmst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlCellmstDao.select(keyid);
	}


	@Override
	public List<ComboBox> getGenTlCellmstcombo(String condSql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public BAL_GenTlCellmst fillcellcontrol(String cellkeyid) throws Exception {
		return this.genTlCellmstDao.fillcellcontrol(cellkeyid);
	}


}
