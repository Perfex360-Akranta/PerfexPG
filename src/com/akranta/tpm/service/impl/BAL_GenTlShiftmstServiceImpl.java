
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import oracle.sql.DATE;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_CompanyBean;
import com.akranta.tpm.bean.BAL_GenTlShiftmstBean;

import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.BAL_GenTlShiftmstDao;

import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.GenTlShiftmstDaoImpl;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.BAL_GenTlShiftmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_GenTlShiftmst;

import com.akranta.tpm.service.BAL_GenTlShiftmstService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_GenTlShiftmstServiceImpl implements BAL_GenTlShiftmstService {
	private BAL_GenTlShiftmstDao genTlShiftmstDao;
	private BAL_CommonFilterDao commonFilterDao;
	private Validations validations ;
	public BAL_GenTlShiftmstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlShiftmstDao =  new BAL_GenTlShiftmstDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	

	public List<String[]> getAllGenTlShiftmst(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlShiftmstDao.getGenTlShiftmstShiftorder(commonFilter);
	}
    
	public void setGenTlShiftmstDao(BAL_GenTlShiftmstDao genTlShiftmstDao)
	{
		this.genTlShiftmstDao = genTlShiftmstDao;
	}

	public BAL_GenTlShiftmst create(BAL_GenTlShiftmst newGenTlShiftmst,BAL_GenTlShiftmst oldGenTlShiftmst,  BAL_GenTlShiftmstBean genTlShiftmstBean) throws ValidationExceptions,Exception 
	{

		try
		{
			System.out.println("create");
			String validationsFor;
		
			validationsFor = "create";
			System.out.println("service impl in ");
			validations.validate(newGenTlShiftmst,"shiftCreation",validationsFor);
			System.out.println("service impl inside");
			fillValues(newGenTlShiftmst,oldGenTlShiftmst,genTlShiftmstBean);
			System.out.println("service impl ");
			return genTlShiftmstDao.create(newGenTlShiftmst);
			
			
		}catch (ValidationExceptions e)
		{

			throw new ValidationExceptions(e.getMessage());
		}	
	 }

	private BAL_GenTlShiftmst fillValues(BAL_GenTlShiftmst newGenTlShiftmst,BAL_GenTlShiftmst oldGenTlShiftmst,BAL_GenTlShiftmstBean genTlShiftmstBean)
	{
		newGenTlShiftmst.setSftmActive("Y");
		System.out.println("lllllll");
		String dateTime = CommonFunctions.dateTimeNow();
		System.out.println(dateTime);
		String dateStr = dateTime.substring(0,20);
        System.out.println(dateStr);
        
		if(newGenTlShiftmst.getSftmKeyid() == null )
			newGenTlShiftmst.setSftmCreatedon(dateTime);
		else
			newGenTlShiftmst.setSftmCreatedon(dateTime);
			newGenTlShiftmst.setSftmModifiedon(dateTime);
		
			System.out.println("fillvalue start");
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
			System.out.println("End time " + dateStr.substring(0, 11) + " "+ newGenTlShiftmst.getSftmEndtime());
			newGenTlShiftmst.setSftmEndtime( dateStr.substring(0, 11) + " "+ newGenTlShiftmst.getSftmEndtime());
			//System.out.println("End time last" +dateStr);
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
		
		
		System.out.println("fillvalue last");
		
		
		
		return newGenTlShiftmst;
		
	}


	public BAL_GenTlShiftmst update(BAL_GenTlShiftmst newGenTlShiftmst,BAL_GenTlShiftmst oldGenTlShiftmst,BAL_GenTlShiftmstBean genTlShiftmstBean) throws Exception 
	{	// TODO Auto-generated method stub
			
				System.out.println("updatee" +newGenTlShiftmst);
				validations.validate(newGenTlShiftmst,"shiftCreation","update");
				fillValues(newGenTlShiftmst,oldGenTlShiftmst,genTlShiftmstBean);
			
				return genTlShiftmstDao.update(newGenTlShiftmst);
	}
			
	
	@Override
	public BAL_GenTlShiftmst delete(BAL_GenTlShiftmst genTlShiftmst) throws Exception {
		// TODO Auto-generated method stub
		return genTlShiftmstDao.delete(genTlShiftmst);
	}


	public List<ComboBox> getGenTlShiftmstShiftorder(String condSql) throws Exception
	{
		
		System.out.println( " service impl");
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("SFTM_NAME");
		comboFilter.setIdField("SFTM_CODE");
		comboFilter.setOrderByField("SFTM_SHIFTORDER");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SHIFTMST);
		CommonFunctions.debugMsg("ccccc     "+comboFilter);
	
		return commonFilterDao.fillComboValues(comboFilter);
			
	 }



	@Override
	public BAL_GenTlShiftmst select(String keyid) throws Exception
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
		CommonFunctions.debugMsg("ccccc     "+comboFilter);
	
		return commonFilterDao.fillComboValues(comboFilter);
	}

}

