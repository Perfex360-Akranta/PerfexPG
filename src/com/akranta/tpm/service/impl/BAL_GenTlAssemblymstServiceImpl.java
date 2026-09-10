package com.akranta.tpm.service.impl;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
/**
 * Author:N Arun
 * Created on:25.11.2011
 */
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlAssemblymstBean;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.BAL_GenTlAssemblymstDao;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;

import com.akranta.tpm.dao.impl.BAL_AssemblyDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.service.BAL_GenTlAssemblymstService;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.service.api.BALBreakDownDropdownServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.Validations;

import net.sf.json.JSONObject;

public class BAL_GenTlAssemblymstServiceImpl implements BAL_GenTlAssemblymstService {
	private BAL_GenTlAssemblymstDao genTlAssemblymstDao;	
	private BAL_CommonFilterDao commonFilterDao;
	private Validations validations ;
	private BALBreakDownDropdownServiceApi serviceApi;
	//private BAL_GenTlAssemblymstServiceApi genTlAssemblymstServiceApi;
	public BAL_GenTlAssemblymstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlAssemblymstDao =  new BAL_AssemblyDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	

	public List<String[]> getAllAssembly(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlAssemblymstDao.getGenTlAssemblymst(commonFilter);
	}
	
	
    
	public void setGenTlAssemblymstDao(BAL_GenTlAssemblymstDao genTlAssemblymstDao)
	{
		this.genTlAssemblymstDao = genTlAssemblymstDao;
	}
	
	public void BAL_GenTlAssemblymstServiceImplJwt(String JwtToken){
    	try{
    		genTlAssemblymstDao.BAL_GenTlAssemblymstDaoImplJwt(JwtToken);
    		
    		serviceApi = new BALBreakDownDropdownServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }

	public GenTlAssemblymst create(GenTlAssemblymst newGenTlAssemblymst,GenTlAssemblymst oldGenTlAssemblymst,  BAL_GenTlAssemblymstBean genTlAssemblymstBean) throws ValidationExceptions,Exception {

		try {
			
			String validationsFor;
			validationsFor = "create";
			validations.validate(newGenTlAssemblymst,"assemblycreation",validationsFor);
			fillValues(newGenTlAssemblymst,oldGenTlAssemblymst,genTlAssemblymstBean);
			GenTlFunctionallocn genTlFunctionallocn  = null;
			System.out.println(genTlAssemblymstBean.toString());
			if(genTlAssemblymstBean != null)
			{
				if(UIUtils.isValidKeyId(genTlAssemblymstBean.getRefreshFlag()))
				{
					genTlFunctionallocn = new GenTlFunctionallocn();
					if(UIUtils.isValidKeyId(genTlAssemblymstBean.getMachineId()))
						genTlFunctionallocn.setFnlnOriginalid(genTlAssemblymstBean.getMachineId());					
					if(UIUtils.isValidKeyId(newGenTlAssemblymst.getAssmName()))
						genTlFunctionallocn.setFnlnDescription(newGenTlAssemblymst.getAssmName());
					genTlFunctionallocn.setFnlnElementtype("A");
					genTlFunctionallocn.setFnlnActive("Y");
					
				}
			}
			//return genTlAssemblymstDao.create(newGenTlAssemblymst,genTlFunctionallocn);
			return serviceApi.saveAssemblyApi(newGenTlAssemblymst, genTlFunctionallocn);
			
			
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}

	private GenTlAssemblymst fillValues(GenTlAssemblymst newGenTlAssemblymst,GenTlAssemblymst oldGenTlAssemblymst,BAL_GenTlAssemblymstBean genTlAssemblymstBean) {
		//System.out.println("Entered into fill values");
		newGenTlAssemblymst.setAssmActive("Y");
		//System.out.println("Entered into fill values");
		String dateTime = CommonFunctions.pg_dateTimeNow();// change here 
		//System.out.println("Entered into fill values");
		//if(newGenTlAssemblymst.getAssmKeyid() == null )
			newGenTlAssemblymst.setAssmCreatedon(dateTime);
//		else
//			newGenTlAssemblymst.setAssmCreatedon(oldGenTlAssemblymst.getAssmCreatedon());
		
		System.out.println("Entered into fill values after time");
		
			newGenTlAssemblymst.setAssmModifiedon(dateTime);
		
		
		if( newGenTlAssemblymst.getAssmCode() == null )
			newGenTlAssemblymst.setAssmCode("{}");
			
		if( newGenTlAssemblymst.getAssmDescription() == null )
			newGenTlAssemblymst.setAssmDescription("{}");
			
		if( newGenTlAssemblymst.getAssmName() == null )
			newGenTlAssemblymst.setAssmName("{}");
		
		if( newGenTlAssemblymst.getAssmRemarks() == null )
			newGenTlAssemblymst.setAssmRemarks("{}");
		
		if( newGenTlAssemblymst.getAssmType() == null )
			newGenTlAssemblymst.setAssmType("ASM");
		
		if( newGenTlAssemblymst.getAssmKeyid() == null )
			newGenTlAssemblymst.setAssmKeyid("{}");
		
	
		
		if( newGenTlAssemblymst.getAssmRelatedto() == null )
			newGenTlAssemblymst.setAssmRelatedto("{}");
		
		
	
		
		return newGenTlAssemblymst;
		
	}


	public GenTlAssemblymst update(GenTlAssemblymst newGenTlAssemblymst,GenTlAssemblymst oldGenTlAssemblymst,BAL_GenTlAssemblymstBean genTlAssemblymstBean) throws Exception {	// TODO Auto-generated method stub
			//return null;
		
		System.out.println("updatee");
		validations.validate(newGenTlAssemblymst,"assemblycreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		fillValues(newGenTlAssemblymst,oldGenTlAssemblymst,genTlAssemblymstBean);
		GenTlFunctionallocn genTlFunctionallocn  = null;
		System.out.println("updatee one");
		System.out.println(newGenTlAssemblymst.getAssmKeyid());
		System.out.println("updatee two");
		//System.out.println(oldGenTlAssemblymst.getAssmKeyid());
		System.out.println("updatee three");
		System.out.println(genTlAssemblymstBean.getFormMode());
		
		if(genTlAssemblymstBean != null)
		{
			
			if(UIUtils.isValidKeyId(genTlAssemblymstBean.getRefreshFlag()))
			{
			
				genTlFunctionallocn = new GenTlFunctionallocn();
				if(UIUtils.isValidKeyId(genTlAssemblymstBean.getMachineId()))
					genTlFunctionallocn.setFnlnOriginalid(genTlAssemblymstBean.getMachineId());					
				if(UIUtils.isValidKeyId(newGenTlAssemblymst.getAssmName()))
					genTlFunctionallocn.setFnlnDescription(newGenTlAssemblymst.getAssmName());
				genTlFunctionallocn.setFnlnElementtype("A");
				genTlFunctionallocn.setFnlnActive("Y");
				
			}
		}	
		//return genTlAssemblymstDao.update(newGenTlAssemblymst,genTlFunctionallocn);
		return serviceApi.updateAssemblyApi(newGenTlAssemblymst,genTlFunctionallocn);
	}
			
		

	@Override
	public GenTlAssemblymst delete(String delemode,GenTlAssemblymst genTlAssemblymst) throws ValidationExceptions, Exception {
		
		//return genTlAssemblymstDao.delete(delemode,genTlAssemblymst);
		return serviceApi.deleteAssemblyApi(delemode, genTlAssemblymst);
	}
	
	//added by priyanka on 16/06/2026
	
	/*
	 * @Override public void initJwt(String jwtToken) {
	 * genTlAssemblymstDao.BAL_GenTlAssemblymstDaoImplJwt(jwtToken); }
	 */
	
	@Override
	public GenTlAssemblymst select(String keyid) throws Exception {
	    //return genTlAssemblymstDao.select(keyid);
	    return serviceApi.selectAssemblyApi(keyid);
	}
	// ── getAssemblyGridData ────────────────────────────────────────────────
	// ── getAssemblyGridData ────────────────────────────────────────────────
	/*
	 * @Override public List<String[]> getAssemblyGridData(CommonParams
	 * commonParams) throws Exception { CommonFilter commonFilter = new
	 * CommonFilter(); // Map pagination/sort params from CommonParams into
	 * CommonFilter //commonFilter.setPage(commonParams.getPage());
	 * //commonFilter.setRows(commonParams.getRows());
	 * //commonFilter.setSidx(commonParams.getSidx());
	 * //commonFilter.setSord(commonParams.getSord());
	 * //commonFilter.setFilters(commonParams.getFilters());
	 * //commonFilter.setSearchField(commonParams.getSearchField());
	 * //commonFilter.setSearchString(commonParams.getSearchString());
	 * //commonFilter.setSearchOper(commonParams.getSearchOper());
	 * //commonFilter.setViewClick('Y');
	 * 
	 * return genTlAssemblymstDao.getAssemblyList(commonFilter); }
	 */


	// ── getAssemblyColModel ────────────────────────────────────────────────
	/*
	 * @Override public JSONObject getAssemblyColModel() throws Exception { return
	 * genTlAssemblymstDao.getAssemblyColModel(); }
	 */


	// ── getAssemblyGridCount ───────────────────────────────────────────────
	/*
	 * @Override public int getAssemblyGridCount(CommonParams commonParams) throws
	 * Exception { CommonFilter commonFilter = new CommonFilter();
	 * //commonFilter.setPage(commonParams.getPage());
	 * //commonFilter.setRows(commonParams.getRows());
	 * commonFilter.setViewClick('Y');
	 * genTlAssemblymstDao.getAssemblyList(commonFilter); return
	 * genTlAssemblymstDao.getAssemblyCount(commonFilter); }
	 */
	
	@Override
	public List<String[]> getAssemblyGridData(CommonFilter commonFilter) throws Exception {
	    return genTlAssemblymstDao.getAssemblyList(commonFilter);
	}





	
/*	public GenTlAssemblymst assmformfill(String keyId) {
		// TODO Auto-generated method stub
		return this.genTlAssemblymstDao.assmformfill(keyId);
	}*/

}
