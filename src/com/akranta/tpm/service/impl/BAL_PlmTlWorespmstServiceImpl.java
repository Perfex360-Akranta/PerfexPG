 package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_WOResponsibilityBean;
//import com.akranta.tpm.bean.WOResponsibilityBean;
import com.akranta.tpm.dao.BAL_PlmTlWorespmstDao;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.BAL_PlmTlWorespmstDaoImpl;
import com.akranta.tpm.model.BAL_PlmTlWorespdtl;
import com.akranta.tpm.model.BAL_PlmTlWorespmst;
import com.akranta.tpm.service.BAL_PlmTlWorespmstService;
import com.akranta.tpm.service.api.BalWorespServiceApi;
import com.akranta.tpm.service.BAL_PlmTlWorespmstService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_PlmTlWorespmstServiceImpl implements  BAL_PlmTlWorespmstService {
private BAL_PlmTlWorespmstDao plmTlWorespmstDao ;
private BalWorespServiceApi balWorespServiceApi;
private Validations validations ;
	public BAL_PlmTlWorespmstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		plmTlWorespmstDao = new BAL_PlmTlWorespmstDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	public void setPlmTlWorespmstDao(BAL_PlmTlWorespmstDao plmTlWorespmstDao)
	{
		this.plmTlWorespmstDao = plmTlWorespmstDao;
	}
	
	@Override
	public void BAL_PlmTlWorespmstServiceImplJwt(String jwtToken) {
        try {
            balWorespServiceApi = new BalWorespServiceApi(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public BAL_PlmTlWorespmst saveWorkresp(BAL_PlmTlWorespmst newplmTlWorespmst,BAL_PlmTlWorespmst existplmTlWorespmst,BAL_WOResponsibilityBean wOResponsibilityBean)throws Exception{
//		public PlmTlPlanconfiguration create(PlmTlPlanconfiguration newPlmTlPlanconfiguration,PlmTlPlanconfiguration existPlmTlPlanconfiguration,PlanConfigurationBean planConfigurationBean) throws Exception {
			// TODO Auto-generated method stub
			try {
				CommonFunctions.debugMsg("insdie service impl of workorder resp create");
				String validationsFor = "create";
			
				//validations.validate(newPlmTlPlanconfiguration,"Planconfiguration",validationsFor);//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
				List<BAL_PlmTlWorespdtl>  workRespdtlList = newplmTlWorespmst.getWoRespDetail();
				CommonFunctions.debugMsg("workRespdtlList size    "+workRespdtlList.size());
				fillValues(newplmTlWorespmst,existplmTlWorespmst,wOResponsibilityBean);
				//PlmTlWorespmst plmTlWorespmst =plmTlWorespmstDao.create(newplmTlWorespmst);
				CommonFunctions.debugMsg("After Filling detail Values");
				
				//return  plmTlWorespmstDao.create(newplmTlWorespmst);
				return this.balWorespServiceApi.insertRecord(newplmTlWorespmst);
				
			   }catch (ValidationExceptions e){
				
				CommonFunctions.debugMsg("validation"+e.getMessage());
				throw new ValidationExceptions(e.getMessage());
				
			}	
			
		}
		
		@Override
		public BAL_PlmTlWorespmst update(BAL_PlmTlWorespmst newplmTlWorespmst,BAL_PlmTlWorespmst existplmTlWorespmst,BAL_WOResponsibilityBean wOResponsibilityBean)throws ValidationExceptions {
		
			CommonFunctions.debugMsg("Update called");
			String validationsFor = "update";
			CommonFunctions.debugMsg("Inside the ServiceImpl update");
			//validations.validate(newPlmTlPlanconfiguration,"planconfiguration",validationsFor);
			List<BAL_PlmTlWorespdtl> workRespdtlList =newplmTlWorespmst.getWoRespDetail();
			for( BAL_PlmTlWorespdtl plmTlWorespdtl : workRespdtlList)
			{	
				CommonFunctions.debugMsg("tradeId  " + plmTlWorespdtl.getPwrdempid()+"--"+plmTlWorespdtl.getPwrdtradeid());
			//	validations.validate(plmTlToolsdtl,"clitcreation",validationsFor);//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
			}
			 fillValues(newplmTlWorespmst,existplmTlWorespmst,wOResponsibilityBean);
			 BAL_PlmTlWorespmst plmTlWorespmst = null;
			try {
				//plmTlWorespmst = plmTlWorespmstDao.update(newplmTlWorespmst);
				plmTlWorespmst = this.balWorespServiceApi.updateRecord(newplmTlWorespmst);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Update failed: " + e.getMessage()); 
			}
			 return plmTlWorespmst;
			//return cliTlStandardsDao.update(newplmTlWorespmst);		

		}
	

		private BAL_PlmTlWorespmst fillValues(BAL_PlmTlWorespmst newplmTlWorespmst,BAL_PlmTlWorespmst existplmTlWorespmst,
				BAL_WOResponsibilityBean wOResponsibilityBean) {
			// TODO Auto-generated method stub
			newplmTlWorespmst.setPwrmactive("Y");
			CommonFunctions.debugMsg("Form Modecreatae   :"+wOResponsibilityBean.getFormMode());
			CommonFunctions.debugMsg("lllllll  :");
			
			String dateTime = CommonFunctions.dateTimeNow();
			CommonFunctions.debugMsg("bfr if "+dateTime);
			CommonFunctions.debugMsg(newplmTlWorespmst.getPwrmkeyid()+"createdon   :");
			if(newplmTlWorespmst.getPwrmkeyid() == null )
				newplmTlWorespmst.setPwrmcreatedon(dateTime);
			else
				newplmTlWorespmst.setPwrmcreatedon(dateTime);
			
			CommonFunctions.debugMsg("createdon   :"+newplmTlWorespmst.getPwrmcreatedon());
			CommonFunctions.debugMsg("sdfgs  "+dateTime);
			CommonFunctions.debugMsg("0.0");
			newplmTlWorespmst.setPwrmmodifiedon(dateTime);
			CommonFunctions.debugMsg("0");
			if( newplmTlWorespmst.getPwrmcellid() == null )
				newplmTlWorespmst.setPwrmcellid("{}");
			CommonFunctions.debugMsg("1");
			if( newplmTlWorespmst.getPwrmfactoryid() == null )
				newplmTlWorespmst.setPwrmfactoryid("{}");
			CommonFunctions.debugMsg("2");
			if( newplmTlWorespmst.getPwrmgeneral() == null )
				newplmTlWorespmst.setPwrmgeneral("N");
			CommonFunctions.debugMsg("3");
			if( newplmTlWorespmst.getPwrmlevel() == null )
				newplmTlWorespmst.setPwrmlevel("M");
			CommonFunctions.debugMsg("4");
			if( newplmTlWorespmst.getPwrmmachineid() == null )
				newplmTlWorespmst.setPwrmmachineid("{}");
			CommonFunctions.debugMsg("5");
			if( newplmTlWorespmst.getPwrmsectionid() == null )
				newplmTlWorespmst.setPwrmsectionid("{}");
			CommonFunctions.debugMsg("6");
			if( newplmTlWorespmst.getPwrmtempfield1() == null )
				newplmTlWorespmst.setPwrmtempfield1("{}");
			CommonFunctions.debugMsg("7");
			if( newplmTlWorespmst.getPwrmtempfield2() == null )
				newplmTlWorespmst.setPwrmtempfield2("{}");
			CommonFunctions.debugMsg("8");
			if( newplmTlWorespmst.getPwrmtempfield3() == null )
				newplmTlWorespmst.setPwrmtempfield3("{}");
			CommonFunctions.debugMsg("9");
			if( newplmTlWorespmst.getPwrmtempfield4() == null )
				newplmTlWorespmst.setPwrmtempfield4("{}");
			CommonFunctions.debugMsg("10");
			if( newplmTlWorespmst.getPwrmtradewise()== null )
				newplmTlWorespmst.setPwrmtradewise("Y");
			CommonFunctions.debugMsg("11");
			CommonFunctions.debugMsg("factoryid   "+newplmTlWorespmst.getPwrmfactoryid());
			newplmTlWorespmst.setWoRespDetail(refdtlTablesFillValues(newplmTlWorespmst,existplmTlWorespmst));
			return newplmTlWorespmst;
		}
		 //for filling PlmTlWorespdtl  table
		private List<BAL_PlmTlWorespdtl > refdtlTablesFillValues(BAL_PlmTlWorespmst newplmTlWorespmst,BAL_PlmTlWorespmst existplmTlWorespmst) 
		{
			CommonFunctions.debugMsg("Detail 1");
			String dateTime = CommonFunctions.dateTimeNow();
		  
			
			/***method master***/
			List<BAL_PlmTlWorespdtl > newPlmTlWorespdtlmsts = newplmTlWorespmst.getWoRespDetail();
			List<BAL_PlmTlWorespdtl > oldPlmTlWorespdtlmsts = null;
			BAL_PlmTlWorespdtl  oldPlmTlWorespdtl   = null;
			CommonFunctions.debugMsg("methodDetail 2");
			
			if( existplmTlWorespmst != null)
			{
				oldPlmTlWorespdtlmsts = existplmTlWorespmst.getWoRespDetail();
			
			if( oldPlmTlWorespdtlmsts != null && oldPlmTlWorespdtlmsts.size() > 0 )
			   
				oldPlmTlWorespdtl  = oldPlmTlWorespdtlmsts.get(0);
			}	
			CommonFunctions.debugMsg("Detail 3");
			List<BAL_PlmTlWorespdtl > newPlmTlWorespdtlList = new ArrayList<BAL_PlmTlWorespdtl >();
			
			for( BAL_PlmTlWorespdtl  PlmTlWorespdtl  : newPlmTlWorespdtlmsts)
			{	
				if(PlmTlWorespdtl.getPwrdkeyid() == null )			
				{	
					
					PlmTlWorespdtl.setPwrdcreatedon(dateTime);
					PlmTlWorespdtl .setPwrdeffectfrom(dateTime);
					PlmTlWorespdtl .setPwrdeffecttill(Constants.futureNullDate);
					//PlmTlWorespdtl .setPwrdmasterid(newplmTlWorespmst.getPwrmmachineid());
					//newplmTlWorespmst.set(newplmTlWorespmst.getClisEffectivedate());
				}	
				else{
					PlmTlWorespdtl .setPwrdcreatedon(oldPlmTlWorespdtl .getPwrdcreatedon());
					PlmTlWorespdtl .setPwrdeffectfrom(oldPlmTlWorespdtl .getPwrdcreatedon());
					//PlmTlWorespdtl .setPwrdmasterid(oldPlmTlWorespdtl.getPwrdmasterid());
					//newplmTlWorespmst.setClisStartdate(existPlmTlPlanconfiguration.getClisEffectivedate());
				}
				CommonFunctions.debugMsg("befiore if");
				//if(PlmTlWorespdtl.getPwrdtradeid().equals(oldPlmTlWorespdtl.getPwrdtradeid()))
					//PlmTlWorespdtl.setPwrdempid(oldPlmTlWorespdtl.getPwrdempid());
				//CommonFunctions.debugMsg(PlmTlWorespdtl.getPwrdtradeid()+"  old  "+oldPlmTlWorespdtl.getPwrdtradeid());
				//CommonFunctions.debugMsg(PlmTlWorespdtl.getPwrdempid()+"  old  "+oldPlmTlWorespdtl.getPwrdempid());
				PlmTlWorespdtl.setPwrdmodifiedon(dateTime);
				CommonFunctions.debugMsg("workdtl Detailbefor active  :-" + PlmTlWorespdtl.getPwrdmodifiedon());
				PlmTlWorespdtl.setPwrdactive("Y");
				PlmTlWorespdtl.setPwrdcreatedby(newplmTlWorespmst.getPwrmcreatedby());
				
				
				PlmTlWorespdtl.setPwrdmodifiedon(dateTime);
				CommonFunctions.debugMsg("workdtl Detailafter active       :-" + PlmTlWorespdtl .getPwrdmodifiedon());
				/*if(PlmTlWorespdtl .getPwrm()== null)
				   PlmTlWorespdtl .setMlmmDuration("0");*/
				  
				if(PlmTlWorespdtl.getPwrdmasterid()== null)
				   PlmTlWorespdtl.setPwrdmasterid("{}");		
				   
				if(PlmTlWorespdtl.getPwrdtradeid()== null)
				   PlmTlWorespdtl.setPwrdtradeid("{}");
				   
				if(PlmTlWorespdtl.getPwrdempid()== null)
				   PlmTlWorespdtl.setPwrdempid("{}");
				
				if(PlmTlWorespdtl.getPwrdtempfield1()== null)
					   PlmTlWorespdtl.setPwrdtempfield1("{}");
				
				if(PlmTlWorespdtl.getPwrdtempfield2()== null)
					   PlmTlWorespdtl.setPwrdtempfield2("{}");
				
				if(PlmTlWorespdtl.getPwrdtempfield3()== null)
					   PlmTlWorespdtl.setPwrdtempfield3("{}");
				
				if(PlmTlWorespdtl.getPwrdtempfield4()== null)
					   PlmTlWorespdtl.setPwrdtempfield4("{}");
				   
				newPlmTlWorespdtlList.add(PlmTlWorespdtl );
				  
			   }
			return newPlmTlWorespdtlList;
			}
		//end of PlmTlWorespdtl  table
		@Override
		public String delSelectedTrade(String worespdtlId) throws BusinessApplicationExceptions, Exception {
			// TODO Auto-generated method stub
			return this.plmTlWorespmstDao.delSelectedTrade(worespdtlId);
		}
	}

