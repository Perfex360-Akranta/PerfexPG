/*Author MANIKANDAN*/
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_CliTlStandardFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_CliTlStandardsDao;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.impl.BAL_CliTlStandardsDaoImpl;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BAL_BdmTlYycountermeasurelink;
import com.akranta.tpm.model.BAL_CliTlStandards;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.BAL_PlmTlMultiplemethodsmst;
//import com.akranta.tpm.model.PlmTlStandards;
import com.akranta.tpm.model.BAL_PlmTlToolsdtl;
import com.akranta.tpm.service.BAL_CliTlStandardsService;
import com.akranta.tpm.service.api.CliTlStandardsServiceApi;
import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.CliTlStandardsServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;



public class BAL_CliTlStandardServiceImpl implements BAL_CliTlStandardsService {

	private BAL_CliTlStandardsDao cliTlStandardsDao ; 
	private BAL_CommonFilterDao commonFilterDao;
	private Validations validations ;
	private CliTlStandardsServiceApi cltiapi;
	FunctionCallApi fnCallApi;
	public BAL_CliTlStandardServiceImpl(DBActionTemplate dbActionTemplate)
	{
		cliTlStandardsDao = new BAL_CliTlStandardsDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public void setCliTlStandardsDao(BAL_CliTlStandardsDao cliTlStandardsDao)
	{
		this.cliTlStandardsDao = cliTlStandardsDao;
	}
	
	public void BAL_CliTlStandardsServiceImplJwt(String JwtToken){
		try{
			cliTlStandardsDao.BAL_CliTlStandardsDaoImplJwt(JwtToken);
			cltiapi = new CliTlStandardsServiceApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    // TODO Auto-generated constructor stub
	}
	public BAL_CliTlStandards create(BAL_CliTlStandards newCliTlStandards,BAL_CliTlStandards oldCliTlStandards,  BAL_CliTlStandardFormBean cliTlStandardFormBean) throws ValidationExceptions,Exception {

		try {
			
			String validationsFor;
			validationsFor = "create";
			
			validations.validate(newCliTlStandards,"BAL_clitcreation",validationsFor);//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
			List <BAL_PlmTlMultiplemethodsmst> methodslist = newCliTlStandards.getMethodDetail();
			for(BAL_PlmTlMultiplemethodsmst multipleMethod:methodslist)
			{
				validations.validate(multipleMethod,"BAL_clitcreation",validationsFor);
			}
			System.out.println("After validation");
			//**for counterMeasure**//
			//**for counterMeasure**//
			BAL_BdmTlYycountermeasurelink counterMeasurelist = newCliTlStandards.getCountermeasureLink();
		//**for tools*//*
			List <BAL_PlmTlToolsdtl> toolsPickUplist = newCliTlStandards.getToolsDetail();
			System.out.println("dasf---"+toolsPickUplist.size());
			for(BAL_PlmTlToolsdtl toolsPickUp:toolsPickUplist)
			{
				System.out.println("tool validate");
				
				validations.validate(toolsPickUp,"BAL_clitcreation",validationsFor);
			}
			/***/
			fillValues(newCliTlStandards,oldCliTlStandards,cliTlStandardFormBean);
			System.out.println("After Filling tool Values");
			String dkeyId = cliTlStandardFormBean.getBdmDockKey();
			//for inserting in clisCalendar procedure
			//BAL_CliTlStandards cliTlStandards = cliTlStandardsDao.create(newCliTlStandards,dkeyId);
			BAL_CliTlStandards cliTlStandards = cltiapi.saveCliTlStandards(newCliTlStandards,dkeyId);
 		    cliTlStandardsDao.generateCalendar(newCliTlStandards.getClisKeyid(),newCliTlStandards.getClisStartdate(),"Y");

			return cliTlStandards;
		}catch (ValidationExceptions e){
			System.out.println("validation"+e.getMessage());

			throw new ValidationExceptions(e.getMessage());
		}	
	}

////////////////////////////////////value="${requestScope.genTlAssemblymst.assmKeyid}"
	/**for tools**/
	private List<BAL_PlmTlToolsdtl> refToolTablesFillValues(BAL_CliTlStandards newCliTlStandards,BAL_CliTlStandards oldCliTlStandards) 
	{
		List<BAL_PlmTlToolsdtl> plmTlToolsdtlList = newCliTlStandards.getToolsDetail();
		List<BAL_PlmTlToolsdtl> oldplmTlToolsdtlList = null;
		BAL_PlmTlToolsdtl oldPlmTlToolsdtl = null;
		String dateTime = CommonFunctions.dateTimeNow();
	
		if( oldCliTlStandards != null)
		{
			oldplmTlToolsdtlList = oldCliTlStandards.getToolsDetail();
		
		if( oldplmTlToolsdtlList != null && oldplmTlToolsdtlList.size() > 0 )
		   
			oldPlmTlToolsdtl = oldplmTlToolsdtlList.get(0);
		}
		List<BAL_PlmTlToolsdtl> newPlmTlToolsdtlList = new ArrayList<BAL_PlmTlToolsdtl>();
		for( BAL_PlmTlToolsdtl plmTlToolsdtl : plmTlToolsdtlList)
		{	
			if(plmTlToolsdtl.getPtldKeyid() == null )			
			{	
				
				plmTlToolsdtl.setPtldCreatedon(dateTime);
			}	
			else{
				plmTlToolsdtl.setPtldCreatedon(oldPlmTlToolsdtl.getPtldCreatedon());
			}
			plmTlToolsdtl.setPtldModifiedon(dateTime);
			
			System.out.println("toolDetail getPtldModifiedon  : " + plmTlToolsdtl.getPtldModifiedon());
			System.out.println("toolDetail getPtldStandardid  : " + plmTlToolsdtl.getPtldStandardid());
			System.out.println("toolDetail getPtldToolid      : " + plmTlToolsdtl.getPtldToolid());
			
			plmTlToolsdtl.setPtldActive("Y");
			plmTlToolsdtl.setPtldCreatedby(newCliTlStandards.getClisCreatedby());
			
			
			plmTlToolsdtl.setPtldModifiedon(dateTime);
			System.out.println("toolDetailafter active" + plmTlToolsdtl.getPtldModifiedon());
			
			   
			if(plmTlToolsdtl.getPtldToolid()== null)
				plmTlToolsdtl.setPtldToolid("{}");		
			
			if(plmTlToolsdtl.getPtldStandardid()== null)
				plmTlToolsdtl.setPtldStandardid("{}");
			
			newPlmTlToolsdtlList.add(plmTlToolsdtl);
		 }
		return newPlmTlToolsdtlList;
	}
	/**end of tools **/
	/**Counter Measure **/
	private BAL_BdmTlYycountermeasurelink refCountermeasureFillValues(
			BAL_CliTlStandards newCliTlStandards,BAL_CliTlStandards oldCliTlStandards) {
		// TODO Auto-generated method stub
		BAL_BdmTlYycountermeasurelink bdmTlYycountermeasure = newCliTlStandards.getCountermeasureLink();

		
		String dateTime = CommonFunctions.dateTimeNow();
		CommonFunctions.debugMsg("Counter m  sertghjk");
		
		
			if(bdmTlYycountermeasure.getYycmKeyid() == null )			
			{	
				
				bdmTlYycountermeasure.setYycmCreatedon(dateTime);
			}	
			
			
			
			bdmTlYycountermeasure.setYycmActive("Y");
			bdmTlYycountermeasure.setYycmCreatedby(newCliTlStandards.getClisCreatedby());
			
			
			bdmTlYycountermeasure.setYycmModifieyon(dateTime);
			if(bdmTlYycountermeasure.getYycmWoid()== null)
				bdmTlYycountermeasure.setYycmWoid("{}");
			
			if(bdmTlYycountermeasure.getYycmCountermsrid()== null)
				bdmTlYycountermeasure.setYycmCountermsrid("{}");		
			CommonFunctions.debugMsg("stdpermlink   "+bdmTlYycountermeasure.getYycmCountermsrid());
			
			CommonFunctions.debugMsg("permid   "+bdmTlYycountermeasure.getYycmKeyid());
			if(bdmTlYycountermeasure.getYycmRefdoctype()== null)
				bdmTlYycountermeasure.setYycmRefdoctype("{}");
			
			if(bdmTlYycountermeasure.getYycmTempfield1()== null)
				bdmTlYycountermeasure.setYycmTempfield1("-");
			
			if(bdmTlYycountermeasure.getYycmTempfield2()== null)
				bdmTlYycountermeasure.setYycmTempfield2("-");
			
			if(bdmTlYycountermeasure.getYycmTempfield3()== null)
				bdmTlYycountermeasure.setYycmTempfield3("-");
			
			if(bdmTlYycountermeasure.getYycmTempfield4()== null)
				bdmTlYycountermeasure.setYycmTempfield4("-");
			
			if(bdmTlYycountermeasure.getYycmTempfield5()== null)
				bdmTlYycountermeasure.setYycmTempfield5("-");
			
		return bdmTlYycountermeasure;
	}
	 //for filling method table
	private List<BAL_PlmTlMultiplemethodsmst> refTablesFillValues(BAL_CliTlStandards newCliTlStandards,BAL_CliTlStandards oldCliTlStandards) 
	{
		System.out.println("Detail 1");
		String dateTime = CommonFunctions.dateTimeNow();
	  
		
		/***method master***/
		List<BAL_PlmTlMultiplemethodsmst> newPlmTlMultiplemethodsmsts = newCliTlStandards.getMethodDetail();
		List<BAL_PlmTlMultiplemethodsmst> oldPlmTlMultiplemethodsmsts = null;
		BAL_PlmTlMultiplemethodsmst oldPlmTlMultiplemethodsmst  = null;
		System.out.println("methodDetail 2");
		
		if( oldCliTlStandards != null)
		{
			oldPlmTlMultiplemethodsmsts = oldCliTlStandards.getMethodDetail();
		
		if( oldPlmTlMultiplemethodsmsts != null && oldPlmTlMultiplemethodsmsts.size() > 0 )
		   
			oldPlmTlMultiplemethodsmst = oldPlmTlMultiplemethodsmsts.get(0);
		}	
		System.out.println("Detail 3");
		List<BAL_PlmTlMultiplemethodsmst> newPlmTlMultiplemethodsmstList = new ArrayList<BAL_PlmTlMultiplemethodsmst>();
		for( BAL_PlmTlMultiplemethodsmst plmTlMultiplemethodsmst : newPlmTlMultiplemethodsmsts)
		{	
			if(plmTlMultiplemethodsmst.getMlmmKeyid() == null )			
			{	
				
				plmTlMultiplemethodsmst.setMlmmCreatedon(dateTime);
				newCliTlStandards.setClisStartdate(newCliTlStandards.getClisEffectivedate());
			}	
			else{
				plmTlMultiplemethodsmst.setMlmmCreatedon(oldPlmTlMultiplemethodsmst.getMlmmCreatedon());
				newCliTlStandards.setClisStartdate(oldCliTlStandards.getClisEffectivedate());
			}
			plmTlMultiplemethodsmst.setMlmmModifiedon(dateTime);
			System.out.println("method Detailbefor active  :-" + plmTlMultiplemethodsmst.getMlmmModifiedon());
			plmTlMultiplemethodsmst.setMlmmActive("Y");
			plmTlMultiplemethodsmst.setMlmmCreatedby(newCliTlStandards.getClisCreatedby());
			
			
			plmTlMultiplemethodsmst.setMlmmModifiedon(dateTime);
			System.out.println("method Detailafter active       :-" + plmTlMultiplemethodsmst.getMlmmModifiedon());
			System.out.println("Methoddescription after active  :-" + plmTlMultiplemethodsmst.getMlmmMethoddescription());
			System.out.println("MethodDuration after active     :-" + plmTlMultiplemethodsmst.getMlmmDuration());
			if(plmTlMultiplemethodsmst.getMlmmDuration()== null)
			   plmTlMultiplemethodsmst.setMlmmDuration("0");
			  
			if(plmTlMultiplemethodsmst.getMlmmMethoddescription()== null)
			   plmTlMultiplemethodsmst.setMlmmMethoddescription("{}");		
			  
			
			   
			if(plmTlMultiplemethodsmst.getMlmmRefdocid()== null)
			   plmTlMultiplemethodsmst.setMlmmRefdocid("{}");
			   
			if(plmTlMultiplemethodsmst.getMlmmRefdoctype()== null)
			   plmTlMultiplemethodsmst.setMlmmRefdoctype("{}");
			   
			   newPlmTlMultiplemethodsmstList.add(plmTlMultiplemethodsmst);
			  
		   }
		return newPlmTlMultiplemethodsmstList;
		}
	//end of method table
	/*
	 * private BAL_CliTlStandards fillValues(BAL_CliTlStandards
	 * newCliTlStandards,BAL_CliTlStandards
	 * oldCliTlStandards,BAL_CliTlStandardFormBean cliTlStandardFormBean) { // TODO
	 * Auto-generated method stub
	 * 
	 * newCliTlStandards.setClisActive("Y");
	 * System.out.println("Form Mode   :"+cliTlStandardFormBean.getFormMode());
	 * System.out.println("lllllll  :");
	 * 
	 * String dateTime = CommonFunctions.pg_dateTimeNow();
	 * System.out.println(dateTime); if(newCliTlStandards.getClisKeyid() == null )
	 * newCliTlStandards.setClisCreatedon(dateTime); else //
	 * newCliTlStandards.setClisCreatedon(oldCliTlStandards.getClisCreatedon());
	 * newCliTlStandards.setClisCreatedon(dateTime);
	 * 
	 * 
	 * System.out.println(dateTime); newCliTlStandards.setClisModifiedon(dateTime);
	 * if( newCliTlStandards.getClisAssemblyid() == null )
	 * //newCliTlStandards.setClisAssemblyid("MMA/0155");
	 * newCliTlStandards.setClisAssemblyid("{}");
	 * 
	 * if( newCliTlStandards.getClisActivitytype() == null )
	 * newCliTlStandards.setClisActivitytype("-");
	 * 
	 * if( newCliTlStandards.getClisEffectivedate() == null )
	 * newCliTlStandards.setClisEffectivedate(newCliTlStandards.getClisNextduedate()
	 * );
	 * 
	 * if( newCliTlStandards.getClisCauseid() == null )
	 * newCliTlStandards.setClisCauseid("{}"); if( newCliTlStandards.getClisCellid()
	 * == null ) newCliTlStandards.setClisCellid("{}"); if(
	 * newCliTlStandards.getClisMachineid() == null )
	 * newCliTlStandards.setClisMachineid("{}"); if(
	 * newCliTlStandards.getClisCorrectiveaction() == null )
	 * newCliTlStandards.setClisCorrectiveaction("{}"); if(
	 * newCliTlStandards.getClisCreatedby() == null )
	 * newCliTlStandards.setClisCreatedby("<* *>"); if(
	 * newCliTlStandards.getClisCreatedon() == null )
	 * newCliTlStandards.setClisCreatedon("<* *>"); if(
	 * newCliTlStandards.getClisModifiedon() == null )
	 * newCliTlStandards.setClisModifiedon("<* *>");
	 * 
	 * 
	 * 
	 * newCliTlStandards.setClisDate(dateTime);
	 * 
	 * if( newCliTlStandards.getClisDepartmentmgr() == null )
	 * newCliTlStandards.setClisDepartmentmgr("{}");
	 * 
	 * // if( newCliTlStandards.getClisEffectivedate() == null ) //
	 * newCliTlStandards.setClisEffectivedate(Constants.passNullDate);
	 * 
	 * if( newCliTlStandards.getClisFactoryid() == null )
	 * newCliTlStandards.setClisFactoryid("<* *>"); if(
	 * newCliTlStandards.getClisFormatno() == null )
	 * newCliTlStandards.setClisFormatno("{}");
	 * 
	 * if( newCliTlStandards.getClisFrequency() == null )
	 * newCliTlStandards.setClisFrequency("0"); if(
	 * newCliTlStandards.getClisFrequencyunit() == null )
	 * newCliTlStandards.setClisFrequencyunit("D"); if(
	 * newCliTlStandards.getClisGroupleader() == null )
	 * newCliTlStandards.setClisGroupleader("{}"); if(
	 * newCliTlStandards.getClisGroupno() == null )
	 * newCliTlStandards.setClisGroupno("{}"); if(
	 * newCliTlStandards.getClisHowmethod() == null )
	 * newCliTlStandards.setClisHowmethod("{}"); if(
	 * newCliTlStandards.getClisHowmuchduration() == null )
	 * newCliTlStandards.setClisHowmuchduration("0");
	 * 
	 * if( newCliTlStandards.getClisInactivateddate() == null )
	 * newCliTlStandards.setClisInactivateddate(Constants.pgPassNullDateTime); if(
	 * newCliTlStandards.getClisIssuedate() == null )
	 * newCliTlStandards.setClisIssuedate(Constants.pgPassNullDateTime); if(
	 * newCliTlStandards.getClisIssueno() == null )
	 * newCliTlStandards.setClisIssueno("{}");
	 * 
	 * if( newCliTlStandards.getClisIstoolsreq() == null )
	 * newCliTlStandards.setClisIstoolsreq("N");
	 * System.out.println(" ClisIstoolsreq "+
	 * newCliTlStandards.getClisIstoolsreq()); if( newCliTlStandards.getClisJhid()
	 * == null ) newCliTlStandards.setClisJhid("{}"); if(
	 * newCliTlStandards.getClisKeyid() == null )
	 * newCliTlStandards.setClisKeyid("{}"); if(
	 * newCliTlStandards.getClisLastdonedate() == null )
	 * newCliTlStandards.setClisLastdonedate(Constants.pgPassNullDateTime); if(
	 * newCliTlStandards.getClisLastfeedbackdate() == null )
	 * newCliTlStandards.setClisLastfeedbackdate(Constants.pgPassNullDateTime); if(
	 * newCliTlStandards.getClisLastfeedbackid() == null )
	 * newCliTlStandards.setClisLastfeedbackid("{}"); if(
	 * newCliTlStandards.getClisLastweekno() == null )
	 * newCliTlStandards.setClisLastweekno("0"); if(
	 * newCliTlStandards.getClisLastwogendate() == null )
	 * newCliTlStandards.setClisLastwogendate(Constants.pgPassNullDateTime); if(
	 * newCliTlStandards.getClisLastwoid() == null )
	 * newCliTlStandards.setClisLastwoid("{}"); if(
	 * newCliTlStandards.getClisMachineid() == null )
	 * newCliTlStandards.setClisMachineid("{}"); if(
	 * newCliTlStandards.getClisMonthweekno() == null )
	 * newCliTlStandards.setClisMonthweekno("0"); String date=
	 * newCliTlStandards.getClisNextduedate();
	 * newCliTlStandards.setClisNextduedate(CommonFunctions.pg_getDateTimeFromDate(
	 * date)); if( newCliTlStandards.getClisNextduedate() == null )
	 * newCliTlStandards.setClisNextduedate(Constants.pgPassNullDateTime); if(
	 * newCliTlStandards.getClisNextdueweekno() == null )
	 * newCliTlStandards.setClisNextdueweekno("0"); if(
	 * newCliTlStandards.getClisPhenomenaid() == null )
	 * newCliTlStandards.setClisPhenomenaid("{}"); if(
	 * newCliTlStandards.getClisPreparedbyid() == null )
	 * newCliTlStandards.setClisPreparedbyid("{}"); if(
	 * newCliTlStandards.getClisRefdocno() == null )
	 * newCliTlStandards.setClisRefdocno("{}"); if(
	 * newCliTlStandards.getClisRefdoctype() == null )
	 * newCliTlStandards.setClisRefdoctype("{}"); if(
	 * newCliTlStandards.getClisResponsibilitydesgid() == null )
	 * newCliTlStandards.setClisResponsibilitydesgid("{}"); if(
	 * newCliTlStandards.getClisResponsibilityid() == null )
	 * newCliTlStandards.setClisResponsibilityid("{}"); if(
	 * newCliTlStandards.getClisSectionid()== null )
	 * newCliTlStandards.setClisSectionid("{}"); if(
	 * newCliTlStandards.getClisSectionmgr()== null )
	 * newCliTlStandards.setClisSectionmgr("{}"); if(
	 * newCliTlStandards.getClisShiftid()== null )
	 * newCliTlStandards.setClisShiftid("{}"); if(
	 * newCliTlStandards.getClisStandard()== null )
	 * newCliTlStandards.setClisStandard("{}"); if( newCliTlStandards.getClisFlid()
	 * == null ) newCliTlStandards.setClisFlid("{}"); if(
	 * newCliTlStandards.getClisElementid() == null )
	 * newCliTlStandards.setClisElementid("{}");
	 * 
	 * //System.out.println((oldCliTlStandards.getClisStartdate()).substring(0,11)+
	 * "@##@#@#@#@" + cliTlStandardFormBean.getFormMode());
	 * if(cliTlStandardFormBean.getFormMode().equals("UPDATE")){
	 * 
	 * newCliTlStandards.setClisStartdate((oldCliTlStandards.getClisStartdate()));
	 * 
	 * }else{
	 * 
	 * newCliTlStandards.setClisStartdate(newCliTlStandards.getClisEffectivedate());
	 * 
	 * }
	 * 
	 * System.out.println("Start Date   "+newCliTlStandards.getClisStartdate());
	 * 
	 * if( newCliTlStandards.getClisStartweekno()== null )
	 * newCliTlStandards.setClisStartweekno("0"); if(
	 * newCliTlStandards.getClisTime()== null ) newCliTlStandards.setClisTime("{}");
	 * if( newCliTlStandards.getClisTradeid()== null )
	 * newCliTlStandards.setClisTradeid("{}"); if(
	 * newCliTlStandards.getClisWhatactivity()== null )
	 * newCliTlStandards.setClisWhatactivity("{}");System.out.println("xcv"); if(
	 * newCliTlStandards.getClisWherelocation()== null )
	 * newCliTlStandards.setClisWherelocation("{}"); System.out.println("sdf");
	 * System.out.println("newCliTlStandards.getClisWherelocation() :"
	 * +newCliTlStandards.getClisWherelocation()); if(
	 * newCliTlStandards.getClisWhyifnotdone()== null )
	 * newCliTlStandards.setClisWhyifnotdone("{}"); if(
	 * newCliTlStandards.getClisWogenflag()== null )
	 * newCliTlStandards.setClisWogenflag("N");
	 * 
	 * newCliTlStandards.setMethodDetail(refTablesFillValues(newCliTlStandards,
	 * oldCliTlStandards));
	 * newCliTlStandards.setToolsDetail(refToolTablesFillValues(newCliTlStandards,
	 * oldCliTlStandards));
	 * 
	 * if(newCliTlStandards.getCountermeasureLink() != null )
	 * newCliTlStandards.setCountermeasureLink(refCountermeasureFillValues(
	 * newCliTlStandards,oldCliTlStandards));
	 * 
	 * return newCliTlStandards;
	 * 
	 * }
	 */
	private BAL_CliTlStandards fillValues(BAL_CliTlStandards newCliTlStandards, BAL_CliTlStandards oldCliTlStandards, BAL_CliTlStandardFormBean cliTlStandardFormBean) {

	    newCliTlStandards.setClisActive("Y");
	    System.out.println("Form Mode   :" + cliTlStandardFormBean.getFormMode());
	    System.out.println("lllllll  :");

	    String dateTime = CommonFunctions.pg_dateTimeNow();
	    System.out.println(dateTime);
	    if (newCliTlStandards.getClisKeyid() == null)
	        newCliTlStandards.setClisCreatedon(dateTime);
	    else
	        newCliTlStandards.setClisCreatedon(dateTime);

	    System.out.println(dateTime);
	    newCliTlStandards.setClisModifiedon(dateTime);
	    if (newCliTlStandards.getClisAssemblyid() == null)
	        newCliTlStandards.setClisAssemblyid("{}");

	    if (newCliTlStandards.getClisActivitytype() == null)
	        newCliTlStandards.setClisActivitytype("-");

	    // ---- FIX: effectivedate — fallback to nextduedate, then convert raw UI date to PG timestamp ----
	    if (newCliTlStandards.getClisEffectivedate() == null
	            || newCliTlStandards.getClisEffectivedate().trim().isEmpty()
	            || "{}".equals(newCliTlStandards.getClisEffectivedate()))
	        newCliTlStandards.setClisEffectivedate(newCliTlStandards.getClisNextduedate());

	    newCliTlStandards.setClisEffectivedate(
	            CommonFunctions.pg_getDateTimeFromDate(newCliTlStandards.getClisEffectivedate()));
	    // ---- END FIX ----

	    if (newCliTlStandards.getClisCauseid() == null)
	        newCliTlStandards.setClisCauseid("{}");
	    if (newCliTlStandards.getClisCellid() == null)
	        newCliTlStandards.setClisCellid("{}");
	    if (newCliTlStandards.getClisMachineid() == null)
	        newCliTlStandards.setClisMachineid("{}");
	    if (newCliTlStandards.getClisCorrectiveaction() == null)
	        newCliTlStandards.setClisCorrectiveaction("{}");
	    if (newCliTlStandards.getClisCreatedby() == null)
	        newCliTlStandards.setClisCreatedby("<* *>");
	    if (newCliTlStandards.getClisCreatedon() == null)
	        newCliTlStandards.setClisCreatedon("<* *>");
	    if (newCliTlStandards.getClisModifiedon() == null)
	        newCliTlStandards.setClisModifiedon("<* *>");

	    newCliTlStandards.setClisDate(dateTime);

	    if (newCliTlStandards.getClisDepartmentmgr() == null)
	        newCliTlStandards.setClisDepartmentmgr("{}");

	    if (newCliTlStandards.getClisFactoryid() == null)
	        newCliTlStandards.setClisFactoryid("<* *>");
	    if (newCliTlStandards.getClisFormatno() == null)
	        newCliTlStandards.setClisFormatno("{}");

	    if (newCliTlStandards.getClisFrequency() == null)
	        newCliTlStandards.setClisFrequency("0");
	    if (newCliTlStandards.getClisFrequencyunit() == null)
	        newCliTlStandards.setClisFrequencyunit("D");
	    if (newCliTlStandards.getClisGroupleader() == null)
	        newCliTlStandards.setClisGroupleader("{}");
	    if (newCliTlStandards.getClisGroupno() == null)
	        newCliTlStandards.setClisGroupno("{}");
	    if (newCliTlStandards.getClisHowmethod() == null)
	        newCliTlStandards.setClisHowmethod("{}");
	    if (newCliTlStandards.getClisHowmuchduration() == null)
	        newCliTlStandards.setClisHowmuchduration("0");

	    if (newCliTlStandards.getClisInactivateddate() == null)
	        newCliTlStandards.setClisInactivateddate(Constants.pgPassNullDateTime);
	    if (newCliTlStandards.getClisIssuedate() == null)
	        newCliTlStandards.setClisIssuedate(Constants.pgPassNullDateTime);
	    if (newCliTlStandards.getClisIssueno() == null)
	        newCliTlStandards.setClisIssueno("{}");

	    if (newCliTlStandards.getClisIstoolsreq() == null)
	        newCliTlStandards.setClisIstoolsreq("N");
	    System.out.println(" ClisIstoolsreq " + newCliTlStandards.getClisIstoolsreq());
	    if (newCliTlStandards.getClisJhid() == null)
	        newCliTlStandards.setClisJhid("{}");
	    if (newCliTlStandards.getClisKeyid() == null)
	        newCliTlStandards.setClisKeyid("{}");

	    // ---- FIX: lastdonedate — LocalDate column, must be date-only ----
	    String lastDoneDate = newCliTlStandards.getClisLastdonedate();
	    if (lastDoneDate != null && !lastDoneDate.trim().isEmpty() && !"{}".equals(lastDoneDate)) {
	        newCliTlStandards.setClisLastdonedate(
	                toIsoDate(CommonFunctions.pg_getDateTimeFromDate(lastDoneDate)));
	    } else {
	        newCliTlStandards.setClisLastdonedate(toIsoDate(Constants.pgPassNullDateTime));
	    }
	    // ---- END FIX ----

	    if (newCliTlStandards.getClisLastfeedbackdate() == null)
	        newCliTlStandards.setClisLastfeedbackdate(Constants.pgPassNullDateTime);
	    if (newCliTlStandards.getClisLastfeedbackid() == null)
	        newCliTlStandards.setClisLastfeedbackid("{}");
	    if (newCliTlStandards.getClisLastweekno() == null)
	        newCliTlStandards.setClisLastweekno("0");
	    if (newCliTlStandards.getClisLastwogendate() == null)
	        newCliTlStandards.setClisLastwogendate(Constants.pgPassNullDateTime);
	    if (newCliTlStandards.getClisLastwoid() == null)
	        newCliTlStandards.setClisLastwoid("{}");
	    if (newCliTlStandards.getClisMachineid() == null)
	        newCliTlStandards.setClisMachineid("{}");
	    if (newCliTlStandards.getClisMonthweekno() == null)
	        newCliTlStandards.setClisMonthweekno("0");

	    // ---- FIX: nextduedate — LocalDate column, must be date-only ----
	    String nextDueDate = newCliTlStandards.getClisNextduedate();
	    if (nextDueDate != null && !nextDueDate.trim().isEmpty() && !"{}".equals(nextDueDate)) {
	        newCliTlStandards.setClisNextduedate(
	                toIsoDate(CommonFunctions.pg_getDateTimeFromDate(nextDueDate)));
	    } else {
	        newCliTlStandards.setClisNextduedate(toIsoDate(Constants.pgPassNullDateTime));
	    }
	    // ---- END FIX ----

	    if (newCliTlStandards.getClisNextdueweekno() == null)
	        newCliTlStandards.setClisNextdueweekno("0");
	    if (newCliTlStandards.getClisPhenomenaid() == null)
	        newCliTlStandards.setClisPhenomenaid("{}");
	    if (newCliTlStandards.getClisPreparedbyid() == null)
	        newCliTlStandards.setClisPreparedbyid("{}");
	    if (newCliTlStandards.getClisRefdocno() == null)
	        newCliTlStandards.setClisRefdocno("{}");
	    if (newCliTlStandards.getClisRefdoctype() == null)
	        newCliTlStandards.setClisRefdoctype("{}");
	    if (newCliTlStandards.getClisResponsibilitydesgid() == null)
	        newCliTlStandards.setClisResponsibilitydesgid("{}");
	    if (newCliTlStandards.getClisResponsibilityid() == null)
	        newCliTlStandards.setClisResponsibilityid("{}");
	    if (newCliTlStandards.getClisSectionid() == null)
	        newCliTlStandards.setClisSectionid("{}");
	    if (newCliTlStandards.getClisSectionmgr() == null)
	        newCliTlStandards.setClisSectionmgr("{}");
	    if (newCliTlStandards.getClisShiftid() == null)
	        newCliTlStandards.setClisShiftid("{}");
	    if (newCliTlStandards.getClisStandard() == null)
	        newCliTlStandards.setClisStandard("{}");
	    if (newCliTlStandards.getClisFlid() == null)
	        newCliTlStandards.setClisFlid("{}");
	    if (newCliTlStandards.getClisElementid() == null)
	        newCliTlStandards.setClisElementid("{}");

	    // ---- FIX: startdate — LocalDate column, derived from already-converted effectivedate / old value ----
	    if (cliTlStandardFormBean.getFormMode().equals("UPDATE")) {

	        newCliTlStandards.setClisStartdate(toIsoDate(oldCliTlStandards.getClisStartdate()));

	    } else {

	        newCliTlStandards.setClisStartdate(toIsoDate(newCliTlStandards.getClisEffectivedate()));

	    }
	    // ---- END FIX ----

	    System.out.println("Start Date   " + newCliTlStandards.getClisStartdate());

	    if (newCliTlStandards.getClisStartweekno() == null)
	        newCliTlStandards.setClisStartweekno("0");
	    if (newCliTlStandards.getClisTime() == null)
	        newCliTlStandards.setClisTime("{}");
	    if (newCliTlStandards.getClisTradeid() == null)
	        newCliTlStandards.setClisTradeid("{}");
	    if (newCliTlStandards.getClisWhatactivity() == null)
	        newCliTlStandards.setClisWhatactivity("{}");
	    System.out.println("xcv");
	    if (newCliTlStandards.getClisWherelocation() == null)
	        newCliTlStandards.setClisWherelocation("{}");
	    System.out.println("sdf");
	    System.out.println("newCliTlStandards.getClisWherelocation() :" + newCliTlStandards.getClisWherelocation());
	    if (newCliTlStandards.getClisWhyifnotdone() == null)
	        newCliTlStandards.setClisWhyifnotdone("{}");
	    if (newCliTlStandards.getClisWogenflag() == null)
	        newCliTlStandards.setClisWogenflag("N");

	    newCliTlStandards.setMethodDetail(refTablesFillValues(newCliTlStandards, oldCliTlStandards));
	    newCliTlStandards.setToolsDetail(refToolTablesFillValues(newCliTlStandards, oldCliTlStandards));

	    if (newCliTlStandards.getCountermeasureLink() != null)
	        newCliTlStandards.setCountermeasureLink(refCountermeasureFillValues(newCliTlStandards, oldCliTlStandards));

	    return newCliTlStandards;
	}

	/**
	 * "yyyy-MM-dd'T'HH:mm:ss" -> "yyyy-MM-dd"
	 * Use for any field mapped to a LocalDate column on the backend
	 * (startdate, nextduedate, lastdonedate) — those reject a time part.
	 */
	private String toIsoDate(String pgTimestamp) {
	    if (pgTimestamp == null || pgTimestamp.trim().isEmpty()
	            || "{}".equals(pgTimestamp) || pgTimestamp.length() < 10) {
	        return pgTimestamp;
	    }
	    return pgTimestamp.substring(0, 10);
	}

	@Override
	public BAL_CliTlStandards update(BAL_CliTlStandards newCliTlStandards,BAL_CliTlStandards oldCliTlStandards,BAL_CliTlStandardFormBean cliTlStandardFormBean) throws ValidationExceptions,Exception {
		// TODO Auto-generated method stub
 	 try {

		    System.out.println("Update called");
			String validationsFor = "create";
			System.out.println("Inside the ServiceImpl update");
			validations.validate(newCliTlStandards,"BAL_clitcreation",validationsFor);//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
			//validations.validate(newCliTlStandards,"clitcreation","update");//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
			 System.out.println(" after validatiop " );
			/*for method*/
			List<BAL_PlmTlMultiplemethodsmst> newPlmTlMultiplemethodsmsts = newCliTlStandards.getMethodDetail();
			String dkeyId = cliTlStandardFormBean.getBdmDockKey();
			for( BAL_PlmTlMultiplemethodsmst plmTlMultiplemethodsmst : newPlmTlMultiplemethodsmsts)
			{	
			    System.out.println(" methodname " + plmTlMultiplemethodsmst.getMlmmMethoddescription());
				validations.validate(plmTlMultiplemethodsmst,"BAL_clitcreation",validationsFor);//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
			}
			//**for counterMeasure**//
			BAL_BdmTlYycountermeasurelink counterMeasurelist = newCliTlStandards.getCountermeasureLink();
			/*for TOOLS*/
			
			List<BAL_PlmTlToolsdtl> plmTlToolsdtlList = newCliTlStandards.getToolsDetail();
//		if(plmTlToolsdtlList!=null || !plmTlToolsdtlList.isEmpty()){	
//				BAL_PlmTlToolsdtl plmTlToolsdtl =plmTlToolsdtlList.get(0);
//				System.out.println("TOOLS  " + plmTlToolsdtl.getPtldToolid());
//				validations.validate(plmTlToolsdtl,"BAL_clitcreation",validationsFor);//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
//			}
			if(plmTlToolsdtlList != null && !plmTlToolsdtlList.isEmpty()){
			    BAL_PlmTlToolsdtl plmTlToolsdtl = plmTlToolsdtlList.get(0);
			    System.out.println("TOOLS  " + plmTlToolsdtl.getPtldToolid());
			    validations.validate(plmTlToolsdtl,"BAL_clitcreation",validationsFor);
			}
			
			System.out.println("After");
			fillValues(newCliTlStandards,oldCliTlStandards,cliTlStandardFormBean);
			//BAL_CliTlStandards cliTlStandards = cliTlStandardsDao.update(newCliTlStandards,dkeyId);
			BAL_CliTlStandards cliTlStandards = cltiapi.saveCliTlStandards(newCliTlStandards,dkeyId);
			if( ! cliTlStandards.getClisFrequencyunit().equals(oldCliTlStandards.getClisFrequencyunit()) || ! cliTlStandards.getClisActivitytype().equals(oldCliTlStandards.getClisActivitytype()) )
				cliTlStandardsDao.generateCalendar(newCliTlStandards.getClisKeyid(),newCliTlStandards.getClisStartdate(),"Y");
			
 		   return cliTlStandards;
			//return cliTlStandardsDao.update(newCliTlStandards);	
		}catch (ValidationExceptions e){
			
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	

	}
	//multiple1
	@Override
	public List<String[]> getMultipleClitList(CommonFilter commonFilter,String filePath) throws Exception {
	    return cliTlStandardsDao.getMultipleClitList(commonFilter,filePath);
	}
	
	@Override
	public void deleteImage(String clisKeyid, String imgType, String refDoc) throws BusinessApplicationExceptions, Exception {
	    cliTlStandardsDao.deleteImage(clisKeyid, imgType, refDoc);
	}
	
	@Override
	public List<GenTlAllmoduleimgfile> getClitEqpImages(String mchId, String fileDir, String imagePath) throws Exception {
	    return cliTlStandardsDao.getClitEqpImages(mchId, fileDir, imagePath);
	}


	@Override
	public BAL_CliTlStandards delete(BAL_CliTlStandards cliTlStandards)throws Exception 
	{
		// TODO Auto-generated method stub
		System.out.println("delete");
		//return cliTlStandardsDao.delete(cliTlStandards);
		cltiapi.deleteCliTlStandards(cliTlStandards.getClisKeyid());
	    return cliTlStandards;
	}

	@Override
	public List<ComboBox> getClitTradeCombo(CommonFilter commonFilter,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//maintComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("TRDM_CODE");
		comboFilter.setIdField("TRDM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_TRADEMST);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}
	
	@Override
	public List<ComboBox> getClitShiftCombo(CommonFilter commonFilter,String condSql,ComboFilter comboFilter ) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("SFTM_NAME");
		comboFilter.setCodeField("SFTM_CODE");
		comboFilter.setIdField("SFTM_KEYID");
		if(UIUtils.isValidKeyId(condSql))
			comboFilter.setCondSql("AND SFTM_FACTORYID ='"+ condSql +"'");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SHIFTMST);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}
	@Override
	public List<ComboBox> getclitDesignationCombo(CommonFilter commonFilter,String desgId,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("DESG_CODE ");
		comboFilter.setNameField("DESG_NAME ");
		comboFilter.setIdField("DESG_KEYID");
		if(UIUtils.isValidKeyId(desgId))
			comboFilter.setCondSql("AND  DESG_KEYID ='"+ desgId +"'");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_DESIGNATIONMST);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}
	@Override
	public List<ComboBox> getclitResponsibilityCombo(CommonFilter commonFilter,String condSql,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
//		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		
		return commonFilterDao.fillComboValues(comboFilter);
		
	}
	@Override
	public List<ComboBox> getclitpreparedbyCombo(CommonFilter commonFilter,String condSql) throws Exception {
		// TODO Auto-generated method stub
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}
	
	@Override
	public List<String[]> getAlljhclitmachinearea(String mchId) {
		// TODO Auto-generated method stub
		return this.cliTlStandardsDao.getAlljhclitmachinearea(mchId);
	}

	@Override
	public List<String[]> getAlljhclitstandards(String mchId,String jhasmId) {
		// TODO Auto-generated method stub
		return this.cliTlStandardsDao.getAlljhclitstandards(mchId,jhasmId);
	}

	@Override
	public BAL_CliTlStandards jhclitformfill(String stdId) {
		// TODO Auto-generated method stub
		return this.cliTlStandardsDao.jhclitformfill(stdId);
	}

	@Override
	public List<String[]> getAlljhclitcountList(List<String> paramValues) {
		// TODO Auto-generated method stub
		return this.cliTlStandardsDao.getAlljhclitcountList(paramValues);
	}

	@Override
	public List<String[]> getAlljhclitcountmodifyList(List<String> paramValues) {
		// TODO Auto-generated method stub
		return this.cliTlStandardsDao.getAlljhclitcountmodifyList(paramValues);
	}

	@Override
	public List<String[]> getAllcountmodifyList(String flag,String machineIDview) {
		// TODO Auto-generated method stub
		return this.cliTlStandardsDao.getAllcountmodifyList(flag,machineIDview);
	}

	@Override
	public List<Object> getmethodlist(String jhclitkeyID) throws Exception {
	try {
		{
			// TODO Auto-generated method stub
			return this.cliTlStandardsDao.getmethodlist(jhclitkeyID);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

	
	}

	@Override
	public List<ComboBox> getclitmachineAreaCombo(CommonFilter commonFilter,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Assembly machine Area");
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("MCAM_CODE");
		comboFilter.setNameField("MCAM_NAME");
		comboFilter.setIdField("MCAM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MACHINEAREAMST);
		return commonFilterDao.fillComboValues(comboFilter);
		
	}

	@Override
	public List<ComboBox> getclitDeptmgrCombo(CommonFilter commonFilter,String condSql,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getclitSectMgrCombo(CommonFilter commonFilter,String condSql) throws Exception {
		// TODO Auto-generated method stub
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getclitGroupleadCombo(CommonFilter commonFilter,String condSql) throws Exception {
		// TODO Auto-generated method stub
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<String[]> getAlljhclittools(String toolclisid) {
		// TODO Auto-generated method stub
		return this.cliTlStandardsDao.getAlljhclittools(toolclisid);
	}

	@Override
	public Workbook clistdrptExportExcel(CommonFilter commonFilter,
			JSONObject colModel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.cliTlStandardsDao.clistdrptExportExcel(commonFilter,  colModel,rptFormat);
	}

	@Override
	public Workbook getClitExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return this.cliTlStandardsDao.getClitExcel(colmodel,  format,commonFilter);
	}	


/*	@Override
	public List<String[]> getAddMachine(String machineID, String getEquipmentId ) {
		// TODO Auto-generated method stub
		 return cliTlStandardsDao.getAddMachine(machineID,getEquipmentId);
	}

	@Override
	public String geteqpGroup(String machineID) {
		// TODO Auto-generated method stub
		return cliTlStandardsDao.geteqpGroup(machineID);
	}
*/
	

	

	
	
}
