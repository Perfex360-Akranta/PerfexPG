package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlHolidaymstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlHolidaymstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.GenTlHolidaymstDaoImpl;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlHolidaymst;
import com.akranta.tpm.service.GenTlHolidaymstService;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.GenTlHolidaymstServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class GenTlHolidaymstServiceImpl implements GenTlHolidaymstService{

	private GenTlHolidaymstDao genTlHolidaymstDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	private GenTlHolidaymstServiceApi genTlHolidaymstServiceApi;
	FunctionCallApi fnCallApi;


	public GenTlHolidaymstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlHolidaymstDao =  new GenTlHolidaymstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	 public void GenTlHolidaymstServiceImplJwt(String JwtToken){
	    	try{
	    		genTlHolidaymstDao.GenTlHolidaymstDaoImplJwt(JwtToken);	    
	    		genTlHolidaymstServiceApi = new GenTlHolidaymstServiceApi(JwtToken);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	        // TODO Auto-generated constructor stub
	    }
	 
	
	public GenTlHolidaymst select(String keyid) throws Exception {
		
		return this.genTlHolidaymstDao.select(keyid);
	}

	
	public GenTlHolidaymst create(GenTlHolidaymst newGenTlHolidaymst,GenTlHolidaymst existGenTlAssemblymst,GenTlHolidaymstBean genTlHolidaymstBean) throws ValidationExceptions,Exception {
		
		
		try {			
			String validationsFor;			
			validationsFor = "create";
			CommonFunctions.debugMsg("Validation..");
			
			validations.validate(newGenTlHolidaymst,"HolidayCreation",validationsFor);
			fillValues(newGenTlHolidaymst,existGenTlAssemblymst,genTlHolidaymstBean);				
			//return genTlHolidaymstDao.create(newGenTlHolidaymst,genTlHolidaymstBean);
			return genTlHolidaymstServiceApi.saveHoliday(newGenTlHolidaymst);

		
			
		}catch (ValidationExceptions e){

			System.out.println("e.getMessage(): to shown..."+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (Exception e){

			System.out.println("e.getMessage()123:"+e.getMessage());
			throw new Exception(e.getMessage());
			
		}
		
	}


	private GenTlHolidaymst fillValues(GenTlHolidaymst newGenTlHolidaymst,GenTlHolidaymst existGenTlAssemblymst,GenTlHolidaymstBean genTlHolidaymstBean) {
		
		String dateTime = CommonFunctions.dateTimeNow();		
					
		if(newGenTlHolidaymst.getHolmKeyid() == null )			
		{	
			newGenTlHolidaymst.setHolmCreatedon(dateTime);			
		}					
		else
		{	
			newGenTlHolidaymst.setHolmCreatedon(existGenTlAssemblymst.getHolmCreatedon());
		}
		
		newGenTlHolidaymst.setHolmModifiedon(dateTime);
		
		String date = newGenTlHolidaymst.getHolmDate();
		newGenTlHolidaymst.setHolmDate(CommonFunctions.pg_getDateTimeFromDate(date));
		
		
		if( newGenTlHolidaymst.getHolmDate() == null )
			newGenTlHolidaymst.setHolmDate(Constants.pgPassNullDate);
		
		if( newGenTlHolidaymst.getHolmDescription() == null )
			newGenTlHolidaymst.setHolmDescription("{}");			
		CommonFunctions.debugMsg("Description..."+newGenTlHolidaymst.getHolmDescription());
		if( newGenTlHolidaymst.getHolmFactory() == null )
			newGenTlHolidaymst.setHolmFactory("{}");
		
		if( newGenTlHolidaymst.getHolmHolidayflag() == null )
			newGenTlHolidaymst.setHolmHolidayflag("W");
		
		if( newGenTlHolidaymst.getHolmName() == null )
			newGenTlHolidaymst.setHolmName("{}");
		
		if( newGenTlHolidaymst.getHolmCreatedby() == null )
			newGenTlHolidaymst.setHolmCreatedby("{}");
		
		//System.out.println("SubEquipment");
		
		return newGenTlHolidaymst; 
		
	}

	public GenTlHolidaymst update(GenTlHolidaymst newGenTlHolidaymst,GenTlHolidaymst existGenTlAssemblymst,GenTlHolidaymstBean genTlHolidaymstBean) throws Exception {
		
		validations.validate(newGenTlHolidaymst,"EquipmentCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		
		fillValues(newGenTlHolidaymst,existGenTlAssemblymst,genTlHolidaymstBean);	
		System.out.println("Aft Fill val");	
			
		//return genTlHolidaymstDao.update(newGenTlHolidaymst,genTlHolidaymstBean);
		return genTlHolidaymstServiceApi.saveHoliday(newGenTlHolidaymst);

		
	}

	
	public GenTlHolidaymst delete(String string,GenTlHolidaymst newGenTlAssemblymst) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComboBox> getYear(String string) throws Exception {
		List<ComboBox> populatemnth  = new ArrayList<ComboBox>();
		ComboBox yrData = null;
		int yearsrt = 2000;
		for(int i=0;i<201;i++)
		{
			yrData=new ComboBox();
			yrData.setId((yearsrt+i)+"");
			yrData.setText((yearsrt+i)+"");
			populatemnth.add(yrData);
		}
		
		return populatemnth;
		
	}

	@Override
	public List<String[]> getAllHoliday() throws Exception {
		return this.genTlHolidaymstDao.getAllHoliday();
	}

	@Override
	public List<String[]> selectAll(String keyId,String flag,String year) throws Exception {
		return this.genTlHolidaymstDao.selectAll(keyId,flag,year);
	}

	@Override
	public GenTlHolidaymst delete(GenTlHolidaymst newgenTlHolidaymst,GenTlHolidaymstBean genTlHolidaymstBean) throws Exception {
		//return this.genTlHolidaymstDao.delete(newgenTlHolidaymst,genTlHolidaymstBean);
		return genTlHolidaymstServiceApi.deleteHoliday(newgenTlHolidaymst.getHolmKeyid()) ? newgenTlHolidaymst : null;
	}


	@Override
	public List<String[]> getHoliday() throws Exception {
		return this.genTlHolidaymstDao.getHoliday();
	}


	public GenTlHolidaymst flagSelect(String flag)throws Exception {
		return this.genTlHolidaymstDao.flagSelect(flag);
	}

	@Override
	public Workbook HolidayExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.genTlHolidaymstDao.HolidayExportExcel(commonFilter,colModel,rptFormat);
	}

	@Override
	public List<String[]> getAllHoliday(String flag, String year,GridParams gridParams)throws Exception {
		return this.genTlHolidaymstDao.selectAll(flag,year,gridParams);
		 //return genTlHolidaymstServiceApi.getAllHolidays(null, flag, year);
	}

	@Override
	public List<String[]> getAllHolidayCount(String flag, String year, String keyId)throws Exception {
	    return this.genTlHolidaymstDao.getAllHolidayCount(flag,year,keyId);
	}

	@Override
	public List<String[]> selectHolidayFactory(String keyId, String flag,String year, GridParams gridParams) throws Exception {
		return this.genTlHolidaymstDao.selectHolidayFactory(keyId,flag,year,gridParams);
	}

	@Override
	public Workbook HolidayFactoryExportExcel(String keyId, String flag,String year, GridParams gridParams, JSONObject tableModel, String format)throws Exception {
		return this.genTlHolidaymstDao.HolidayFactoryExportExcel(keyId,flag,year,gridParams,tableModel,format);
	}


}
