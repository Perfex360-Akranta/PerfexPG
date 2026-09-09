
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import oracle.sql.DATE;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.CompanyBean;
import com.akranta.tpm.bean.GenTlShiftmstBean;

import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlShiftmstDao;

import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.GenTlShiftmstDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;

import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlShiftmst;

import com.akranta.tpm.service.GenTlShiftmstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class GenTlShiftmstServiceImpl implements GenTlShiftmstService {
	private GenTlShiftmstDao genTlShiftmstDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	public GenTlShiftmstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlShiftmstDao =  new GenTlShiftmstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	

	public List<String[]> getAllGenTlShiftmst(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlShiftmstDao.getGenTlShiftmstShiftorder(commonFilter);
	}
    
	public void setGenTlShiftmstDao(GenTlShiftmstDao genTlShiftmstDao)
	{
		this.genTlShiftmstDao = genTlShiftmstDao;
	}

	public GenTlShiftmst create(GenTlShiftmst newGenTlShiftmst,GenTlShiftmst oldGenTlShiftmst,  GenTlShiftmstBean genTlShiftmstBean) throws ValidationExceptions,Exception 
	{

		try
		{
			CommonMessage.debugMsg("create");
			String validationsFor;
		
			validationsFor = "create";
			CommonMessage.debugMsg("service impl in ");
			validations.validate(newGenTlShiftmst,"shiftCreation",validationsFor);
			CommonMessage.debugMsg("service impl inside");
			fillValues(newGenTlShiftmst,oldGenTlShiftmst,genTlShiftmstBean);
			CommonMessage.debugMsg("service impl ");
			return genTlShiftmstDao.create(newGenTlShiftmst);
			
			
		}catch (ValidationExceptions e)
		{

			throw new ValidationExceptions(e.getMessage());
		}	
	 }

	private GenTlShiftmst fillValues(GenTlShiftmst newGenTlShiftmst,GenTlShiftmst oldGenTlShiftmst,GenTlShiftmstBean genTlShiftmstBean)
	{
		newGenTlShiftmst.setSftmActive("Y");
		CommonMessage.debugMsg("lllllll");
		String dateTime = CommonFunctions.pg_pldateTimeNow();
		CommonMessage.debugMsg(dateTime);
		String dateStr = dateTime.substring(0,19);
        CommonMessage.debugMsg(dateStr);
        
		if(newGenTlShiftmst.getSftmKeyid() == null )
			newGenTlShiftmst.setSftmCreatedon(dateTime);
		else
			newGenTlShiftmst.setSftmCreatedon(dateTime);
			newGenTlShiftmst.setSftmModifiedon(dateTime);
		
			CommonMessage.debugMsg("fillvalue start");
		if( newGenTlShiftmst.getSftmCode() == null )
			newGenTlShiftmst.setSftmCode("{}");
		
		if( newGenTlShiftmst.getSftmKeyid() == null )
			newGenTlShiftmst.setSftmKeyid("{}");
		
		if( newGenTlShiftmst.getSftmBreaktime() == null )
			newGenTlShiftmst.setSftmBreaktime("{}");
	
		if( newGenTlShiftmst.getSftmName() == null )
			newGenTlShiftmst.setSftmName("{}");
		
		if( newGenTlShiftmst.getSftmCellid()== null )
			newGenTlShiftmst.setSftmCellid("{}");
		
		if( newGenTlShiftmst.getSftmFactoryid() == null )
			newGenTlShiftmst.setSftmFactoryid("{}");
	
		if( newGenTlShiftmst.getSftmSectionid() == null )
			newGenTlShiftmst.setSftmSectionid("{}");
		
		if( newGenTlShiftmst.getSftmElementid() == null )  ////ttttt
			newGenTlShiftmst.setSftmElementid("{}");
		
		if( newGenTlShiftmst.getSftmFlid() == null )  ////ttttt
			newGenTlShiftmst.setSftmFlid("{}");
		
		
		if( newGenTlShiftmst.getSftmDescription() == null )
			newGenTlShiftmst.setSftmDescription("{}");
		
		if( newGenTlShiftmst.getSftmEffectivedate() == null )
			newGenTlShiftmst.setSftmEffectivedate(Constants.passNullDate);
		
		if( newGenTlShiftmst.getSftmEndtime() == null )
			newGenTlShiftmst.setSftmEndtime(dateStr);
		else if ( newGenTlShiftmst.getSftmEndtime() != null )
			CommonMessage.debugMsg("End time " + dateStr.substring(0, 11) + " "+ newGenTlShiftmst.getSftmEndtime());
			newGenTlShiftmst.setSftmEndtime( dateStr.substring(0, 11) + " "+ newGenTlShiftmst.getSftmEndtime());
			//CommonMessage.debugMsg("End time last" +dateStr);
		if( newGenTlShiftmst.getSftmShiftorder()== null )
			newGenTlShiftmst.setSftmShiftorder("{}");	
		
		if( newGenTlShiftmst.getSftmStarttime()== null )	
			newGenTlShiftmst.setSftmStarttime(dateStr);
		else if( newGenTlShiftmst.getSftmStarttime()!= null )	
			newGenTlShiftmst.setSftmStarttime(dateStr.substring(0, 11) + " "+ newGenTlShiftmst.getSftmStarttime());
		
		if( newGenTlShiftmst.getSftmInactivedate()== null )
			newGenTlShiftmst.setSftmInactivedate(Constants.passNullDate);
		
		if( newGenTlShiftmst.getSftmDuration()== null )
			newGenTlShiftmst.setSftmDuration(dateStr);
		else if ( newGenTlShiftmst.getSftmDuration()!= null )
			newGenTlShiftmst.setSftmDuration(dateStr.substring(0, 11) + " "+ newGenTlShiftmst.getSftmDuration());
		
		
		CommonMessage.debugMsg("fillvalue last");
		
		
		
		return newGenTlShiftmst;
		
	}


	public GenTlShiftmst update(GenTlShiftmst newGenTlShiftmst,GenTlShiftmst oldGenTlShiftmst,GenTlShiftmstBean genTlShiftmstBean) throws Exception 
	{	// TODO Auto-generated method stub
			
				CommonMessage.debugMsg("updatee" +newGenTlShiftmst);
				validations.validate(newGenTlShiftmst,"shiftCreation","update");
				fillValues(newGenTlShiftmst,oldGenTlShiftmst,genTlShiftmstBean);
			
				return genTlShiftmstDao.update(newGenTlShiftmst);
	}
			
	
	@Override
	public GenTlShiftmst delete(GenTlShiftmst genTlShiftmst) throws Exception {
		// TODO Auto-generated method stub
		return genTlShiftmstDao.delete(genTlShiftmst);
	}


	public List<ComboBox> getGenTlShiftmstShiftorder(String condSql) throws Exception
	{
		
		CommonMessage.debugMsg( " service impl");
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("SFTM_NAME");
		comboFilter.setIdField("SFTM_CODE");
		comboFilter.setOrderByField("SFTM_SHIFTORDER");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SHIFTMST);
		CommonMessage.debugMsg("ccccc     "+comboFilter);
	
		return commonFilterDao.fillComboValues(comboFilter);
			
	 }



	@Override
	public GenTlShiftmst select(String keyid) throws Exception
	{
		// TODO Auto-generated method stub
		return this.genTlShiftmstDao.select(keyid);
	}


	@Override
	public List<ComboBox> getdepartmentcombo(ComboFilter comboFilter) throws Exception {
		
		comboFilter.setNameField("SFTM_SHIFTORDER");
		comboFilter.setIdField("SFTM_SHIFTORDER");
		comboFilter.setOrderByField("SFTM_SHIFTORDER");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SHIFTMST);
		CommonMessage.debugMsg("ccccc     "+comboFilter);
	
		return commonFilterDao.fillComboValues(comboFilter);
	}

}

