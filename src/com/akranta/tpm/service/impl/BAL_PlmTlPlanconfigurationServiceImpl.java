package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.PlanConfigExceptions;
import com.akranta.tpm.Exceptions.SequenceNumGenException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.Exceptions.WoResponsibilityExpection;

import com.akranta.tpm.bean.BAL_PlanConfigurationBean;
import com.akranta.tpm.bean.BAL_WOResponsibilityBean;
import com.akranta.tpm.businessvalidations.PlanConfigurationValidations;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PlmTlPlanconfigurationDao;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.PcsTlProductionplanDao;
import com.akranta.tpm.dao.impl.BAL_PlmTlPlanconfigurationDaoImpl;
//import com.akranta.tpm.dao.PlmTlPlanconfigurationDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.GenSequenceNumber;
import com.akranta.tpm.dao.impl.PcsTlProductionplanDaoImpl;
//import com.akranta.tpm.dao.impl.PlmTlPlanconfigurationDaoImpl;
import com.akranta.tpm.dao.sql.PcsTlProductionplanSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BdmTlMst;
//import com.akranta.tpm.model.CliTlStandards;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlProductionplan;
import com.akranta.tpm.model.PlmTlGenmaintenance;
//import com.akranta.tpm.model.PlmTlMultiplemethodsmst;
import com.akranta.tpm.model.BAL_PlmTlPlanconfiguration;
//import com.akranta.tpm.model.PlmTlToolsdtl;
import com.akranta.tpm.model.BAL_PlmTlWorespdtl;
import com.akranta.tpm.model.BAL_PlmTlWorespmst;
import com.akranta.tpm.service.BAL_PlmTlPlanconfigurationService;
import com.akranta.tpm.service.api.BalWorespServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;


public class BAL_PlmTlPlanconfigurationServiceImpl implements BAL_PlmTlPlanconfigurationService {
	private CommonFilterDao commonFilterDao;
	private BAL_PlmTlPlanconfigurationDao plmTlPlanconfigurationDao;
	private PcsTlProductionplanDao pcsTlProductionplanDao;
	private BalWorespServiceApi balWorespServiceApi;
	private PlanConfigurationValidations planConfigurationValidations;
	private Validations validations ;
	
	public BAL_PlmTlPlanconfigurationServiceImpl(DBActionTemplate dbActionTemplate)
	{
		plmTlPlanconfigurationDao = new BAL_PlmTlPlanconfigurationDaoImpl(dbActionTemplate);
		pcsTlProductionplanDao = new PcsTlProductionplanDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
		planConfigurationValidations = new PlanConfigurationValidations(dbActionTemplate);
	}
	@Override
	public List<String[]> getdataPlanConfig(String factId, String machId, String cellId,String chkValue) {
		// TODO Auto-generated method stub
		return this.plmTlPlanconfigurationDao.getdataPlanConfig(factId,machId,cellId,chkValue);
	}
	
	 
	@Override 
	public void BAL_PlmTlPlanconfigurationImplJwt(String jwtToken) { 
	    try { 
	        balWorespServiceApi = new BalWorespServiceApi(jwtToken); 
	        ((BAL_PlmTlPlanconfigurationDaoImpl) plmTlPlanconfigurationDao)
	            .BAL_PlmTlPlanconfigurationDaoImplJwt(jwtToken);
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	}
	
	@Override
	public List<String[]> getYearHirerachy(String selYear) throws Exception {
		// TODO Auto-generated method stub
		//return this.plmTlPlanconfigurationDao.getYearHirerachy(selYear) ;
		return this.balWorespServiceApi.getNextDueDates();
	}
	public PcsTlProductionplan create(PcsTlProductionplan newPcsTlProductionplan,PcsTlProductionplan oldPcsTlProductionplan)throws ValidationExceptions, Exception
	{
		validations.validate(newPcsTlProductionplan,"ProdPlanCreation","create");
		if(newPcsTlProductionplan.getProdPlan() != null && newPcsTlProductionplan.getProdPlan().size()>0)
		{
			for(int i =0;i<newPcsTlProductionplan.getProdPlan().size();i++)
			{	
				PcsTlProductionplan productionPlan = (PcsTlProductionplan)newPcsTlProductionplan.getProdPlan().get(i);
				validations.validate(productionPlan,"ProdPlanCreation","Qty");
			}
		}
		fillProdPlanValues(newPcsTlProductionplan,oldPcsTlProductionplan);
		
		return pcsTlProductionplanDao.create(newPcsTlProductionplan);
	}
	@Override
	public BAL_PlmTlPlanconfiguration create(BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration,BAL_PlmTlPlanconfiguration existPlmTlPlanconfiguration,BAL_PlanConfigurationBean planConfigurationBean,String usrConfirmation) throws  Exception {
		// TODO Auto-generated method stub
	//	try {
			System.out.println("insdie service impl of planconfig create  " + newPlmTlPlanconfiguration.getPplcMonthly());
			String validationsFor = "create";
			if(!UIUtils.isValidKeyId(usrConfirmation)){
			//validations.validate(newPlmTlPlanconfiguration,"Planconfiguration",validationsFor);//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
				 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly()))
					 newPlmTlPlanconfiguration.setPplcYearly("01-"+newPlmTlPlanconfiguration.getPplcYearly());
				 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly2()))
					 newPlmTlPlanconfiguration.setPplcYearly2("01-"+newPlmTlPlanconfiguration.getPplcYearly2());
				 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly3()))
					 newPlmTlPlanconfiguration.setPplcYearly3("01-"+newPlmTlPlanconfiguration.getPplcYearly3());
				 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly4()))
					 newPlmTlPlanconfiguration.setPplcYearly4("01-"+newPlmTlPlanconfiguration.getPplcYearly4());
				 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly5()))
					 newPlmTlPlanconfiguration.setPplcYearly5("01-"+newPlmTlPlanconfiguration.getPplcYearly5());
				 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly6()))
					 newPlmTlPlanconfiguration.setPplcYearly6("01-"+newPlmTlPlanconfiguration.getPplcYearly6());
				 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly7()))
					 newPlmTlPlanconfiguration.setPplcYearly7("01-"+newPlmTlPlanconfiguration.getPplcYearly7());
				 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly8()))
					 newPlmTlPlanconfiguration.setPplcYearly8("01-"+newPlmTlPlanconfiguration.getPplcYearly8());
				 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly9()))
					 newPlmTlPlanconfiguration.setPplcYearly9("01-"+newPlmTlPlanconfiguration.getPplcYearly9());
				 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly10()))
					 newPlmTlPlanconfiguration.setPplcYearly10("01-"+newPlmTlPlanconfiguration.getPplcYearly10());
			 
			
				fillValues(newPlmTlPlanconfiguration,existPlmTlPlanconfiguration,planConfigurationBean);
				System.out.println("After Filling tool Values");
			}
			//chkToGenerateCalendar(newPlmTlPlanconfiguration,existPlmTlPlanconfiguration,planConfigurationBean);
			String machIdtocheck = newPlmTlPlanconfiguration.getPplcMachineid();
			
		
			CommonFunctions.debugMsg("bfr iF  "+newPlmTlPlanconfiguration.getPplcLevel());
			String chkData = null;
			String indentifir = null;
			
		
			chkData = machIdtocheck;
			if(newPlmTlPlanconfiguration.getPplcLevel().equals("A")){
				indentifir = "A";
			}
			else {
				indentifir = "M";
			}
			CommonFunctions.debugMsg("usrconfrm   "+usrConfirmation+"  chk dat   "+chkData+"====="+indentifir);
		
			
			if(!UIUtils.isValidKeyId(usrConfirmation)){
				CommonFunctions.debugMsg("usrconfrm   "+usrConfirmation);
				planConfigurationValidations.checkMachineExists(chkData,indentifir,usrConfirmation);
			}
			//plmTlPlanconfigurationDao.generateCalendar((newPlmTlPlanconfiguration.getPplcYearly()).substring(7,11),newPlmTlPlanconfiguration.getPplcFactoryid(),newPlmTlPlanconfiguration.getPplcSectionid(),newPlmTlPlanconfiguration.getPplcCellid(),newPlmTlPlanconfiguration.getPplcMachineid(),newPlmTlPlanconfiguration.getPplcAssemblyid(),newPlmTlPlanconfiguration.getPplcFrequency());
			CommonFunctions.debugMsg("chkapplyto all   "+planConfigurationBean.getChkApplytoall());
			BAL_PlmTlPlanconfiguration plmTlPlanconfiguration = null;
			if(!planConfigurationBean.getChkApplytoall().equals("N")){
			   plmTlPlanconfigurationDao.insertAllMach(newPlmTlPlanconfiguration,planConfigurationBean.getChkApplytoall());
			   
			}
			else{
				//plmTlPlanconfiguration =  plmTlPlanconfigurationDao.create(newPlmTlPlanconfiguration);
				plmTlPlanconfiguration = balWorespServiceApi.insertRecord(newPlmTlPlanconfiguration);
				
			}
			return  plmTlPlanconfiguration;
			
/*	   }catch (WoResponsibilityExpection e){
			
			System.out.println("serviceImpl excp  "+e.getMessage());
			
				CommonFunctions.debugMsg("usrConfirmation   :"+usrConfirmation);
			throw new WoResponsibilityExpection(e.getMessage());
	   }catch (SQLException e){
		
			System.out.println("serviceImpl excp  "+e.getMessage());
			
				CommonFunctions.debugMsg("usrConfirmation   :"+usrConfirmation);
			throw new PlanConfigExceptions(e.getMessage());
			
		}
*/		   
	}
	private void chkToGenerateCalendar(BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration,BAL_PlmTlPlanconfiguration existPlmTlPlanconfiguration,
			BAL_PlanConfigurationBean planConfigurationBean) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public BAL_PlmTlPlanconfiguration update(BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration,BAL_PlmTlPlanconfiguration existPlmTlPlanconfiguration,
			BAL_PlanConfigurationBean planConfigurationBean) throws ValidationExceptions {
		System.out.println("Update called");
		String validationsFor = "update";
		System.out.println("Inside the ServiceImpl update");
		//validations.validate(newPlmTlPlanconfiguration,"planconfiguration",validationsFor);
		 System.out.println(" after validatiop " );
		 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly()))
			 newPlmTlPlanconfiguration.setPplcYearly("01-"+newPlmTlPlanconfiguration.getPplcYearly());
			 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly2()))
		 	 newPlmTlPlanconfiguration.setPplcYearly2("01-"+newPlmTlPlanconfiguration.getPplcYearly2());
			 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly3()))
		 	 newPlmTlPlanconfiguration.setPplcYearly3("01-"+newPlmTlPlanconfiguration.getPplcYearly3());
			 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly4()))
		 	 newPlmTlPlanconfiguration.setPplcYearly4("01-"+newPlmTlPlanconfiguration.getPplcYearly4());
			 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly5()))
		 	 newPlmTlPlanconfiguration.setPplcYearly5("01-"+newPlmTlPlanconfiguration.getPplcYearly5());
			 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly6()))
		 	 newPlmTlPlanconfiguration.setPplcYearly6("01-"+newPlmTlPlanconfiguration.getPplcYearly6());
			 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly7()))
		 	 newPlmTlPlanconfiguration.setPplcYearly7("01-"+newPlmTlPlanconfiguration.getPplcYearly7());
			 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly8()))
		 	 newPlmTlPlanconfiguration.setPplcYearly8("01-"+newPlmTlPlanconfiguration.getPplcYearly8());
			 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly9()))
		 	 newPlmTlPlanconfiguration.setPplcYearly9("01-"+newPlmTlPlanconfiguration.getPplcYearly9());
			 if(UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly10()))
		 	 newPlmTlPlanconfiguration.setPplcYearly10("01-"+newPlmTlPlanconfiguration.getPplcYearly10());
		 
		 fillValues(newPlmTlPlanconfiguration,existPlmTlPlanconfiguration,planConfigurationBean);
		 
		 System.out.println(" after2 validatiop " +newPlmTlPlanconfiguration.getPplcHalfyearly() );
		 BAL_PlmTlPlanconfiguration plmTlPlanconfiguration = null;
		try {
			plmTlPlanconfiguration = plmTlPlanconfigurationDao.update(newPlmTlPlanconfiguration);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return plmTlPlanconfiguration;
		//return cliTlStandardsDao.update(newCliTlStandards);		

	}
	private BAL_PlmTlPlanconfiguration fillValues(BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration,
			BAL_PlmTlPlanconfiguration existPlmTlPlanconfiguration,
			BAL_PlanConfigurationBean planConfigurationBean) {
		// TODO Auto-generated method stub
	 
			newPlmTlPlanconfiguration.setPplcActive("Y");
		//	System.out.println("Form Mode   :"+planConfigurationBean.getFormMode());
			System.out.println("fillvalues  :");
			
			String dateTime = CommonFunctions.dateTimeNow();
			System.out.println(dateTime);
			if(newPlmTlPlanconfiguration.getPplcKeyid() == null )
				newPlmTlPlanconfiguration.setPplcCreatedon(dateTime);
			else
				newPlmTlPlanconfiguration.setPplcCreatedon(dateTime);
			
			System.out.println(dateTime);
			newPlmTlPlanconfiguration.setPplcModifiedon(dateTime);
			System.out.println("22222");
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcAssemblyid()) )
				newPlmTlPlanconfiguration.setPplcAssemblyid("{}");
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcCellid()) )
				newPlmTlPlanconfiguration.setPplcCellid("{}");			
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcFactoryid()) )
				newPlmTlPlanconfiguration.setPplcFactoryid("{}");			
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcFrequency()) )
				newPlmTlPlanconfiguration.setPplcFrequency("X");			
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcHalfyearly()) )
				newPlmTlPlanconfiguration.setPplcFrequency(dateTime);
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcLevel()) )
				newPlmTlPlanconfiguration.setPplcLevel("M");	
			System.out.println("111111111111111111111111111");
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcMachineid()) )
				newPlmTlPlanconfiguration.setPplcMachineid("{}");			
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcMonthly()) )
				newPlmTlPlanconfiguration.setPplcMonthly(Constants.futureNullDate);
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcQuarterly()) )
				newPlmTlPlanconfiguration.setPplcQuarterly(Constants.futureNullDate);			
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcSectionid()) )
				newPlmTlPlanconfiguration.setPplcSectionid("{}");			
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcElementid()) )
				newPlmTlPlanconfiguration.setPplcElementid("{}");			
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcTempfield1()) )
				newPlmTlPlanconfiguration.setPplcTempfield1("{}");
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcTempfield2()) )
				newPlmTlPlanconfiguration.setPplcTempfield2("{}");
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcTempfield3()) )
				newPlmTlPlanconfiguration.setPplcTempfield3("{}");
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcTempfield4()) )
				newPlmTlPlanconfiguration.setPplcTempfield4("{}");
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcWeekno()) )
				newPlmTlPlanconfiguration.setPplcWeekno("0");	
			System.out.println("22222222222222222222222222");
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly()) )
				newPlmTlPlanconfiguration.setPplcYearly(Constants.futureNullDate);
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly2()) )
				newPlmTlPlanconfiguration.setPplcYearly2(Constants.futureNullDate);
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly3()) )
				newPlmTlPlanconfiguration.setPplcYearly3(Constants.futureNullDate);
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly4()) )
				newPlmTlPlanconfiguration.setPplcYearly4(Constants.futureNullDate);
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly5()) )
				newPlmTlPlanconfiguration.setPplcYearly5(Constants.futureNullDate);
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly6()) )
				newPlmTlPlanconfiguration.setPplcYearly6(Constants.futureNullDate);
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly7()) )
				newPlmTlPlanconfiguration.setPplcYearly7(Constants.futureNullDate);
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly8()) )
				newPlmTlPlanconfiguration.setPplcYearly8(Constants.futureNullDate);
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly9()) )
				newPlmTlPlanconfiguration.setPplcYearly9(Constants.futureNullDate);
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcYearly10()) )
				newPlmTlPlanconfiguration.setPplcYearly10(Constants.futureNullDate);
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcElementid()) )
				newPlmTlPlanconfiguration.setPplcElementid("{}");
			if( ! UIUtils.isValidKeyId(newPlmTlPlanconfiguration.getPplcFlid()) )
				newPlmTlPlanconfiguration.setPplcFlid("{}");
			return newPlmTlPlanconfiguration;
		
	}
	@Override
	public List<ComboBox> getplnconfigTradeCombo(String string,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("TRDM_NAME");
		comboFilter.setIdField("TRDM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_TRADEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getplnconfigDesignationCombo(String string,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		/*ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("DESG_CODE ");
		comboFilter.setNameField("DESG_NAME ");
		comboFilter.setIdField("DESG_KEYID");
		
		comboFilter.setTableName(TableNames.TBL_GEN_TL_DESIGNATIONMST);
		return commonFilterDao.fillComboValues(comboFilter);*/
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setOrderByField("EMPM_CREATEDON desc");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}
	
	 //for filling PlmTlWorespmst table
	private List<BAL_PlmTlWorespmst> refTablesFillValues(BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration,BAL_PlmTlPlanconfiguration existPlmTlPlanconfiguration) 
	{
		System.out.println("Detail 1");
		String dateTime = CommonFunctions.dateTimeNow();
	  
		
		/***method master***/
		List<BAL_PlmTlWorespmst> newPlmTlWorespmstmsts = newPlmTlPlanconfiguration.getWoRespMast();
		List<BAL_PlmTlWorespmst> oldPlmTlWorespmstsmsts = null;
		BAL_PlmTlWorespmst oldPlmTlWorespmst  = null;
		System.out.println("methodDetail 2");
		
		if( existPlmTlPlanconfiguration != null)
		{
			oldPlmTlWorespmstsmsts = existPlmTlPlanconfiguration.getWoRespMast();
		
		if( oldPlmTlWorespmstsmsts != null && oldPlmTlWorespmstsmsts.size() > 0 )
		   
			oldPlmTlWorespmst = oldPlmTlWorespmstsmsts.get(0);
		}	
		System.out.println("Detail 3");
		List<BAL_PlmTlWorespmst> newPlmTlWorespmstList = new ArrayList<BAL_PlmTlWorespmst>();
		for( BAL_PlmTlWorespmst plmTlWorespmst : newPlmTlWorespmstmsts)
		{	
			if(plmTlWorespmst.getPwrmkeyid() == null )			
			{	
				
				plmTlWorespmst.setPwrmcreatedon(dateTime);
				//newPlmTlPlanconfiguration.set(newPlmTlPlanconfiguration.getClisEffectivedate());
			}	
			else{
				plmTlWorespmst.setPwrmcreatedon(oldPlmTlWorespmst.getPwrmcreatedon());
				//newPlmTlPlanconfiguration.setClisStartdate(existPlmTlPlanconfiguration.getClisEffectivedate());
			}
			plmTlWorespmst.setPwrmmodifiedon(dateTime);
			System.out.println("workdtl Detailbefor active  :-" + plmTlWorespmst.getPwrmmodifiedon());
			plmTlWorespmst.setPwrmactive("Y");
			plmTlWorespmst.setPwrmcreatedby(newPlmTlPlanconfiguration.getPplcCreatedby());
			
			
			plmTlWorespmst.setPwrmmodifiedon(dateTime);
			System.out.println("workdtl Detailafter active       :-" + plmTlWorespmst.getPwrmmodifiedon());
			/*if(plmTlWorespmst.getPwrm()== null)
			   plmTlWorespmst.setMlmmDuration("0");*/
			  
			if(plmTlWorespmst.getPwrmfactoryid()== null)
			   plmTlWorespmst.setPwrmfactoryid("{}");		
			  
			
			   
			if(plmTlWorespmst.getPwrmmachineid()== null)
			   plmTlWorespmst.setPwrmmachineid("{}");
			   
			if(plmTlWorespmst.getPwrmcellid()== null)
			   plmTlWorespmst.setPwrmcellid("{}");
			
			if(plmTlWorespmst.getPwrmsectionid()== null)
				   plmTlWorespmst.setPwrmsectionid("{}");
			
			if(plmTlWorespmst.getPwrmlevel()== null)
				   plmTlWorespmst.setPwrmlevel("M");
			   
			newPlmTlWorespmstList.add(plmTlWorespmst);
			  
		   }
		return newPlmTlWorespmstList;
		}
	//end of PlmTlWorespmst table
	 //for filling PlmTlWorespdtl  table
	private List<BAL_PlmTlWorespdtl > refdtlTablesFillValues(BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration,BAL_PlmTlPlanconfiguration existPlmTlPlanconfiguration) 
	{
		System.out.println("Detail 1");
		String dateTime = CommonFunctions.dateTimeNow();
	  
		
		/***method master***/
		List<BAL_PlmTlWorespdtl > newPlmTlWorespdtlmsts = newPlmTlPlanconfiguration.getWoRespDetail();
		List<BAL_PlmTlWorespdtl > oldPlmTlWorespdtlmsts = null;
		BAL_PlmTlWorespdtl  oldPlmTlWorespdtl   = null;
		System.out.println("methodDetail 2");
		
		if( existPlmTlPlanconfiguration != null)
		{
			oldPlmTlWorespdtlmsts = existPlmTlPlanconfiguration.getWoRespDetail();
		
		if( oldPlmTlWorespdtlmsts != null && oldPlmTlWorespdtlmsts.size() > 0 )
		   
			oldPlmTlWorespdtl  = oldPlmTlWorespdtlmsts.get(0);
		}	
		System.out.println("Detail 3");
		List<BAL_PlmTlWorespdtl > newPlmTlWorespdtlList = new ArrayList<BAL_PlmTlWorespdtl >();
		
		for( BAL_PlmTlWorespdtl  PlmTlWorespdtl  : newPlmTlWorespdtlmsts)
		{	
			if(PlmTlWorespdtl.getPwrdkeyid() == null )			
			{	
				
				PlmTlWorespdtl.setPwrdcreatedon(dateTime);
				//newPlmTlPlanconfiguration.set(newPlmTlPlanconfiguration.getClisEffectivedate());
			}	
			else{
				PlmTlWorespdtl .setPwrdcreatedon(oldPlmTlWorespdtl .getPwrdcreatedon());
				//newPlmTlPlanconfiguration.setClisStartdate(existPlmTlPlanconfiguration.getClisEffectivedate());
			}
			PlmTlWorespdtl.setPwrdmodifiedon(dateTime);
			System.out.println("workdtl Detailbefor active  :-" + PlmTlWorespdtl.getPwrdmodifiedon());
			PlmTlWorespdtl.setPwrdactive("Y");
			PlmTlWorespdtl.setPwrdcreatedby(newPlmTlPlanconfiguration.getPplcCreatedby());
			
			
			PlmTlWorespdtl.setPwrdmodifiedon(dateTime);
			System.out.println("workdtl Detailafter active       :-" + PlmTlWorespdtl.getPwrdmodifiedon());
			/*if(PlmTlWorespdtl .getPwrm()== null)
			   PlmTlWorespdtl .setMlmmDuration("0");*/
			  
			if(PlmTlWorespdtl.getPwrdmasterid()== null)
			   PlmTlWorespdtl.setPwrdmasterid("{}");		
			   
			if(PlmTlWorespdtl.getPwrdtradeid()== null)
			   PlmTlWorespdtl.setPwrdtradeid("{}");
			   
			if(PlmTlWorespdtl.getPwrdempid()== null)
			   PlmTlWorespdtl.setPwrdempid("{}");
			
			   
			newPlmTlWorespdtlList.add(PlmTlWorespdtl );
			  
		   }
		return newPlmTlWorespdtlList;
		}
	//end of PlmTlWorespdtl  table

	@Override
	public BAL_PlmTlWorespdtl saveWorkDetail(BAL_PlmTlWorespdtl newplmTlWorespdtl,
			BAL_PlmTlWorespmst existplmTlWorespdtl) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BAL_PlmTlWorespmst saveWorkresp(BAL_PlmTlWorespmst newplmTlWorespmst,
			BAL_PlmTlWorespmst existplmTlWorespmst,
			BAL_WOResponsibilityBean wOResponsibilityBean) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String[]> getwoRespGridadd(String workrepmstkeyid) {
		// TODO Auto-generated method stub
		return this.plmTlPlanconfigurationDao.getwoRespGridadd(workrepmstkeyid);
	}
	@Override
	public List<String[]> getwoRespGriddata(String workrepmstkeyid) {
		// TODO Auto-generated method stub
		return this.plmTlPlanconfigurationDao.getwoRespGriddata(workrepmstkeyid);
	}
	public List<String[]> getPlanEntry(String machineId,String entryDate,String productId,CommonFilter commonFilter){
		return this.plmTlPlanconfigurationDao.getPlanEntry(machineId,entryDate,productId,commonFilter);
	}
	@Override
	public BAL_PlmTlPlanconfiguration getplanConfigdata(String planConfigKey) throws Exception {
		// TODO Auto-generated method stub
		return this.plmTlPlanconfigurationDao.getplanConfigdata(planConfigKey);
	}
	
	private PcsTlProductionplan fillProdPlanValues(PcsTlProductionplan newPcsTlProductionplan,PcsTlProductionplan oldPcsTlProductionplan)
	{
		String dateTime = CommonFunctions.dateTimeNow();
		newPcsTlProductionplan.setPrplActive("Y");
		newPcsTlProductionplan.setPrplModifiedon(dateTime);
		newPcsTlProductionplan.setPrplTempfield1("-");
		newPcsTlProductionplan.setPrplTempfield2("-");
		newPcsTlProductionplan.setPrplTempfield3("-");
		newPcsTlProductionplan.setPrplTempfield4("-");
		newPcsTlProductionplan.setPrplTempfield5("-");
		
		if(! UIUtils.isValidKeyId(newPcsTlProductionplan.getPrplEntrydate()))
			newPcsTlProductionplan.setPrplEntrydate(Constants.passNullDate);
		
		if(! UIUtils.isValidKeyId(newPcsTlProductionplan.getPrplFactoryid()))
			newPcsTlProductionplan.setPrplFactoryid("{}");
		
		if(! UIUtils.isValidKeyId(newPcsTlProductionplan.getPrplSectionid()))
			newPcsTlProductionplan.setPrplSectionid("{}");
		
		if(! UIUtils.isValidKeyId(newPcsTlProductionplan.getPrplCellid()))
			newPcsTlProductionplan.setPrplCellid("{}");
		
		if(! UIUtils.isValidKeyId(newPcsTlProductionplan.getPrplMachineid()))
			newPcsTlProductionplan.setPrplMachineid("{}");
		
		if(! UIUtils.isValidKeyId(newPcsTlProductionplan.getPrplProductid()))
			newPcsTlProductionplan.setPrplProductid("{}");
		
		if(! UIUtils.isValidKeyId(newPcsTlProductionplan.getPrplPlandate()))
			newPcsTlProductionplan.setPrplPlandate(Constants.passNullDate);
		
		if(! UIUtils.isValidKeyId(newPcsTlProductionplan.getPrplPlanqty()))
			newPcsTlProductionplan.setPrplPlanqty("0");
		
		if(! UIUtils.isValidKeyId(newPcsTlProductionplan.getPrplRevisionno()))
			newPcsTlProductionplan.setPrplRevisionno("{}");
		
		if(! UIUtils.isValidKeyId(newPcsTlProductionplan.getPrplRevisiondate()))
			newPcsTlProductionplan.setPrplRevisiondate(Constants.futureNullDate);
		
		if(! UIUtils.isValidKeyId(newPcsTlProductionplan.getPrplPlannedby()))
			newPcsTlProductionplan.setPrplPlannedby("{}");			
		
		return newPcsTlProductionplan;
	}
	

}
