package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.CompanyBean;

import com.akranta.tpm.bean.GenTlCellmstBean;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.FunctionalLocnDao;
import com.akranta.tpm.dao.GenTlCellmstDao;

import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.FunctionalLocnDaoImpl;
import com.akranta.tpm.dao.impl.GenTlCellmstDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;

import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.GenTlFunctionallocn;

import com.akranta.tpm.model.GenTlCellmst;
import com.akranta.tpm.service.GenTlCellmstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ReqtParamNameConst;
import com.akranta.tpm.utils.Validations;

public class GenTlCellmstServiceImpl implements GenTlCellmstService {
	private GenTlCellmstDao genTlCellmstDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	private FunctionalLocnDao functionalLocnDao; 
	public GenTlCellmstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlCellmstDao =  new GenTlCellmstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
		functionalLocnDao = new FunctionalLocnDaoImpl(dbActionTemplate);
		
	}
	

	public List<String[]> getAllGenTlCellmst(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlCellmstDao.getGenTlCellmst(commonFilter);
	}
    
	public void setGenTlCellmstDao(GenTlCellmstDao genTlCellmstDao)
	{
		this.genTlCellmstDao = genTlCellmstDao;
	}

	public GenTlCellmst create(GenTlCellmst newGenTlCellmst,GenTlCellmst oldGenTlCellmst,  GenTlCellmstBean genTlCellmstBean) throws ValidationExceptions,Exception {

		try {
			CommonMessage.debugMsg("before save fill service");
			String validationsFor;
			
			validationsFor = "create";
			
			validations.validate(newGenTlCellmst,"cellCreation",validationsFor);
			fillValues(newGenTlCellmst,oldGenTlCellmst,genTlCellmstBean);
			CommonMessage.debugMsg("after save fill ");
			return genTlCellmstDao.create(newGenTlCellmst);
		
			
		}catch (ValidationExceptions e){
				CommonMessage.debugMsg(e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	public String getCompany(GenTlCellmst genTlCellmst) throws SQLException
	{
		return genTlCellmstDao.getCompany(genTlCellmst);
	}
	
	/*public String getLocation(GenTlCellmst genTlCellmst) throws SQLException
	{
		return genTlCellmstDao.getLocation(genTlCellmst);
	}*/
	
	public List<ComboBox> getLocationComboList(CommonFilter commonFilter)throws Exception {
		

		ComboFilter locn = commonFilter.getLocation();
		locn.setIdField("LOCN_KEYID");
		locn.setCodeField("LOCN_CODE");
		locn.setNameField("LOCN_NAME");
		locn.setTableName(TableNames.TBL_GEN_TL_LOCATIONMST);
		
		StringBuffer condSql = new StringBuffer();
		locn.setCondSql("AND LOCN_KEYID IN(SELECT FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN)");
		
		return commonFilterDao.fillComboValues(locn);
	}
	
	private GenTlCellmst fillValues(GenTlCellmst newGenTlCellmst,GenTlCellmst oldGenTlCellmst,GenTlCellmstBean genTlCellmstBean) {
		newGenTlCellmst.setCellActive("Y");
		CommonMessage.debugMsg("lllllll");
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		
		if(newGenTlCellmst.getCellKeyid() == null )   
			newGenTlCellmst.setCellCreatedon(dateTime);
		else
			//newGenTlCellmst.setCellCreatedon(oldGenTlCellmst.getCellCreatedon());
			newGenTlCellmst.setCellCreatedon(dateTime);
		    newGenTlCellmst.setCellModifiedon(dateTime);
		
			CommonMessage.debugMsg("fillvalue start");
		
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
	
	private GenTlFunctionallocn fillFunctionLoc(GenTlCellmst newGenTlCellmst){
		CommonMessage.debugMsg("setFnlnOriginalid");
		GenTlFunctionallocn  newGenTlFunctionallocn = new GenTlFunctionallocn();
		
		//newGenTlFunctionallocn.setFnlnOriginalid(newGenTlCellmst.getCellKeyid());
		//newGenTlFunctionallocn.setFnlnElementid(newGenTlCellmst.getCellKeyid());
		

			//newGenTlFunctionallocn.setFnlnElementid(newGenTlCellmst.getCellCompanyid()+"-"+newGenTlCellmst.getLocation()+"-"+newGenTlCellmst.getCellFactoryid()+"-"+newGenTlCellmst.getCellSectionid()+"-"+newGenTlCellmst.getCellKeyid());
		
			CommonMessage.debugMsg("INSIDE THE CELL MST SERVICE IMPL:::::"+newGenTlCellmst.getLocation());
			
		newGenTlFunctionallocn.setFnlnElementtype("C");
		
		//newGenTlFunctionallocn.setFnlnParentid(newGenTlCellmst.getCellFactoryid()+"-"+newGenTlCellmst.getCellSectionid() );
		
		
		
		newGenTlFunctionallocn.setFnlnElementid(newGenTlCellmst.getCellCompanyid()+"-"+newGenTlCellmst.getLocation()+"-"+newGenTlCellmst.getSbu()+"-"+newGenTlCellmst.getCellFactoryid()+"-"+newGenTlCellmst.getCellSectionid()+"-"+newGenTlCellmst.getCellKeyid());
		
		
		newGenTlFunctionallocn.setFnlnOriginalid(newGenTlCellmst.getCellKeyid());
		
		//newGenTlFunctionallocn.setFnlnParentid(genTlCellmst.getCompany()+"-"+genTlCellmst.getLocation()+"-"+genTlCellmst.getPbutSbuid());//+"-"+genTlCellmst.getCellSectionid()
		newGenTlFunctionallocn.setFnlnParentid(newGenTlCellmst.getCellCompanyid()+"-"+newGenTlCellmst.getLocation()+"-"+newGenTlCellmst.getSbu()+"-"+newGenTlCellmst.getCellFactoryid()+"-"+newGenTlCellmst.getCellSectionid() );
		
		
		
	//	newGenTlFunctionallocn.setFnlnParentid(newGenTlCellmst.getCellCompanyid()+"-"+newGenTlCellmst.getLocation()+"-"+newGenTlCellmst.getCellFactoryid()+"-"+newGenTlCellmst.getCellSectionid() );
		
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

	public GenTlCellmst update(GenTlCellmst newGenTlCellmst,GenTlCellmst oldGenTlCellmst,GenTlCellmstBean genTlCellmstBean) throws Exception {	// TODO Auto-generated method stub
			
				CommonMessage.debugMsg("updatee" +newGenTlCellmst);
				//validations.validate(newGenTlCellmst,"cellCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
				fillValues(newGenTlCellmst,oldGenTlCellmst,genTlCellmstBean);
			
			return genTlCellmstDao.update(newGenTlCellmst);
	}
			
	
	@Override
	public GenTlCellmst delete(String delemode,GenTlCellmst genTlCellmst) throws Exception {
		// TODO Auto-generated method stub
		return genTlCellmstDao.delete(delemode,genTlCellmst);
	}

	
	

	@Override
	public GenTlCellmst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlCellmstDao.select(keyid);
	}


	@Override
	public List<ComboBox> getGenTlCellmstcombo(String condSql) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public GenTlCellmst fillcellcontrol(String cellkeyid) throws Exception {
		return this.genTlCellmstDao.fillcellcontrol(cellkeyid);
	}


	@Override
	public String getfunctionalid(String elemId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
