package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.KznTlMstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.KznTlMstDaoImpl;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlDocupdates;
import com.akranta.tpm.model.KznTlGraphdata;
import com.akranta.tpm.model.KznTlHdmst;
import com.akranta.tpm.model.KznTlLosslink;
import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.model.KznTlPillarlink;
import com.akranta.tpm.model.QtmTlCustcomplaintdtl;
import com.akranta.tpm.service.KaizenServices;
import com.akranta.tpm.service.KaizenUploadService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class KaizenUploadServiceImpl implements KaizenUploadService {

	private KznTlMstDao kznTlMstDao ; 
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	public KaizenUploadServiceImpl(DBActionTemplate dbActionTemplate)
	{
		kznTlMstDao = new KznTlMstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public void setKznTlMstDao(KznTlMstDao kznTlMstDao)
	{
		this.kznTlMstDao = kznTlMstDao;
	}
   @Override
	public KznTlMst updateKaizenUpload(KznTlMst newKznTlMst,KznTlMst oldKznTlMst,  KaizenFormBean kaizenFormBean, String apprvallevel,String status )  throws Exception{
		CommonMessage.debugMsg("Inside the serviceImpl");
		fillValues(newKznTlMst,oldKznTlMst,kaizenFormBean,apprvallevel,status);  
		return kznTlMstDao.updateKaizenUpload(newKznTlMst);
	}
    
   @Override
	public KznTlMst createKaizenUpload(KznTlMst newKznTlMst,KznTlMst oldKznTlMst,  KaizenFormBean kaizenFormBean, String apprvallevel,String status) throws BusinessApplicationExceptions,ValidationExceptions,Exception {

		try {
			
			fillValues(newKznTlMst,oldKznTlMst,kaizenFormBean,apprvallevel,status);
			return kznTlMstDao.createKaizenUpload(newKznTlMst);
			
		}catch (ValidationExceptions e){
			e.printStackTrace();
			throw new ValidationExceptions(e.getMessage());
		}	
	}
   
	private KznTlMst fillValues(KznTlMst newKznTlMst,KznTlMst oldKznTlMst, KaizenFormBean kaizenFormBean, String apprvallevel,String status) throws Exception
	{
	
		CommonMessage.debugMsg("Inside the fillvalues");	
		newKznTlMst.setKznmActive("Y");
		CommonMessage.debugMsg("lllllll");
		String dateTime = CommonFunctions.dateTimeNow();
		String currentDate= CommonFunctions.getDate();
		CommonMessage.debugMsg(dateTime);		
		newKznTlMst.setKznmKaizenUpload("Y");
		newKznTlMst.setKznmResultarea("-");
		newKznTlMst.setKznmStatus(status);
		//newKznTlMst.setKznmStatus("-");
		newKznTlMst.setKznmRelatedto("-");
		//newKznTlMst.setKznmResultareasec("-");
		if(newKznTlMst.getKznmKeyid() == null ){
			newKznTlMst.setKznmCreatedon(dateTime);
			newKznTlMst.setKznmModifiedon(dateTime);
		}else
		{
			newKznTlMst.setKznmCreatedon(dateTime);
			newKznTlMst.setKznmModifiedon(dateTime);
		}

		
		CommonMessage.debugMsg(dateTime);
	     
		newKznTlMst.setKznmStartdate(dateTime);
		newKznTlMst.setKznmEnddate(dateTime);
		
		if( newKznTlMst.getKznmTpmpillarid() == null )
			newKznTlMst.setKznmTpmpillarid("{}");
		if( newKznTlMst.getKznmMachineid() == null )
			newKznTlMst.setKznmMachineid("{}");
	
		if( newKznTlMst.getKznmKpiid() == null )
			newKznTlMst.setKznmKpiid("{}");

		if( newKznTlMst.getKznmActivitypillarid() == null )
			newKznTlMst.setKznmActivitypillarid("{}");
		
		if( newKznTlMst.getKznmActive() == null )
			newKznTlMst.setKznmActive("Y");
		
		
		if( newKznTlMst.getKznmLossid() == null )
			newKznTlMst.setKznmLossid("{}");
		if(UIUtils.isValidKeyId(newKznTlMst.getKznmFlid())){
			String elementId = kznTlMstDao.getElementID(newKznTlMst.getKznmFlid());
			newKznTlMst.setKznmElementid(elementId);
		}else
			newKznTlMst.setKznmElementid("{}");
		
		if(!UIUtils.isValidKeyId(newKznTlMst.getKznmKzbnkeyid()))
			newKznTlMst.setKznmKzbnkeyid("{}");
		CommonMessage.debugMsg("kznmkzbn keyid    "+newKznTlMst.getKznmKzbnkeyid());
		String resultArea ="";
		String resultAreaSec =new String();
		
		
		if( kaizenFormBean.getResultAreaP() != null )
			resultArea = kaizenFormBean.getResultAreaP();
		if( kaizenFormBean.getResultAreaQ() != null )
			resultArea += kaizenFormBean.getResultAreaQ();
		if( kaizenFormBean.getResultAreaC() != null )
			resultArea += kaizenFormBean.getResultAreaC();
		if( kaizenFormBean.getResultAreaD() != null )
			resultArea += kaizenFormBean.getResultAreaD();
		if( kaizenFormBean.getResultAreaS() != null )
			resultArea += kaizenFormBean.getResultAreaS();
		if( kaizenFormBean.getResultAreaM() != null )
			resultArea += kaizenFormBean.getResultAreaM();
		if( kaizenFormBean.getResultAreaE() != null )
			resultArea += kaizenFormBean.getResultAreaE();
		
		if(UIUtils.isValidKeyId(kaizenFormBean.getResultAreaSecP()) )
			resultAreaSec = kaizenFormBean.getResultAreaSecP();
		if( UIUtils.isValidKeyId(kaizenFormBean.getResultAreaSecQ()) )
			resultAreaSec += kaizenFormBean.getResultAreaSecQ();
		if( UIUtils.isValidKeyId(kaizenFormBean.getResultAreaSecC()) )
			resultAreaSec += kaizenFormBean.getResultAreaSecC();
		if(UIUtils.isValidKeyId( kaizenFormBean.getResultAreaSecD()))
			resultAreaSec += kaizenFormBean.getResultAreaSecD();
		if( UIUtils.isValidKeyId(kaizenFormBean.getResultAreaSecS()) )
			resultAreaSec += kaizenFormBean.getResultAreaSecS();
		if(UIUtils.isValidKeyId( kaizenFormBean.getResultAreaSecM()) )
			resultAreaSec += kaizenFormBean.getResultAreaSecM();
		if( UIUtils.isValidKeyId(kaizenFormBean.getResultAreaSecE()) )
			resultAreaSec += kaizenFormBean.getResultAreaSecE();
		
		newKznTlMst.setKznmRelatedto("MCH");
		CommonMessage.debugMsg("resultArea before setting ="+resultArea);
		CommonMessage.debugMsg("resultAreaSec before setting ="+resultAreaSec);
		if(UIUtils.isValidKeyId(resultAreaSec)){
			newKznTlMst.setKznmResultareasec(resultAreaSec);
		}
		else
			newKznTlMst.setKznmResultareasec("{}");
		    newKznTlMst.setKznmResultarea(resultArea);
		
		if(newKznTlMst.getKznmIswhywhy()!=null && newKznTlMst.getKznmIswhywhy().trim().equals("Y"))
		{
			newKznTlMst.setKznmIswhywhy("Y");
			CommonMessage.debugMsg("kaizenFormBean.getKznmIswhywhy()10"+newKznTlMst.getKznmAnalysis());
			newKznTlMst.setKznmAnalysis("<**>");
		}
		else{ 
			newKznTlMst.setKznmIswhywhy("N");
			CommonMessage.debugMsg("kaizenFormBean.getKznmIswhywhy()11"+newKznTlMst.getKznmAnalysis());
			if(!UIUtils.isValidKeyId(newKznTlMst.getKznmAnalysis()))
					newKznTlMst.setKznmAnalysis("{}");
		}
		CommonMessage.debugMsg("kaizenFormBean.getKznmIswhywhy()"+newKznTlMst.getKznmIswhywhy());
		CommonMessage.debugMsg("kaizenFormBean.getKznmIswhywhy()"+newKznTlMst.getKznmAnalysis());
		if( !UIUtils.isValidKeyId( newKznTlMst.getKznmBenchmark () ))
			newKznTlMst.setKznmBenchmark ("0");
		if( !UIUtils.isValidKeyId( newKznTlMst.getKznmTarget () ))
			newKznTlMst.setKznmTarget ("0");
		
		if( newKznTlMst.getKznmTeammembers() == null )
			newKznTlMst.setKznmTeammembers("<**>");
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmUtiliseforfuture()) )
			newKznTlMst.setKznmUtiliseforfuture("N");
		
		if(!UIUtils.isValidKeyId(newKznTlMst.getKznmUtiliseforfuture()))
			newKznTlMst.setKznmUtiliseforfuture("N");
		else if(newKznTlMst.getKznmUtiliseforfuture().equalsIgnoreCase("ON"))
			newKznTlMst.setKznmUtiliseforfuture("Y");
		
		
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmCellid()) )
			newKznTlMst.setKznmCellid("{}");
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmSectionid()) )
			newKznTlMst.setKznmSectionid("{}");
		
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmCircleid()) )
			newKznTlMst.setKznmCircleid("{}");
		
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmCostcentreid()) )
			newKznTlMst.setKznmCostcentreid("{}");
		
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmAssemblyid()) )
			newKznTlMst.setKznmAssemblyid("{}");
		
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmFactoryid()) )
			newKznTlMst.setKznmFactoryid("{}");
		
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmMachineid()) )
			newKznTlMst.setKznmMachineid("{}");
		
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmBenefittype()) )
			newKznTlMst.setKznmBenefittype("{}");
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmBenefitvalue()) )
			newKznTlMst.setKznmBenefitvalue("{}");
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmCostperequipment()) )
			newKznTlMst.setKznmCostperequipment("0");
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmCostperhour()) )
			newKznTlMst.setKznmCostperhour("0");
		if(! UIUtils.isValidKeyId(newKznTlMst.getKznmVerifyamount()) )
			newKznTlMst.setKznmVerifyamount("-");


		if( ! UIUtils.isValidKeyId(newKznTlMst.getKznmFipRequired()) )
			newKznTlMst.setKznmFipRequired("-");
		if( ! UIUtils.isValidKeyId(newKznTlMst.getKznmFipNumber()))
			newKznTlMst.setKznmFipNumber("{}");
		
	
		
		CommonMessage.debugMsg("Utilise For future   "+newKznTlMst.getKznmUtiliseforfuture());
		String ideaIndGrp =new String();
		
		if(UIUtils.isValidKeyId( kaizenFormBean.getIdeagroupindividualG()))
			ideaIndGrp = kaizenFormBean.getIdeagroupindividualG();
		if(UIUtils.isValidKeyId( kaizenFormBean.getIdeagroupindividualI()))
			ideaIndGrp = kaizenFormBean.getIdeagroupindividualI();
		CommonMessage.debugMsg("ideaIndGrp   "+ideaIndGrp);
			newKznTlMst.setKznmIdeagroupindividual(ideaIndGrp);
			CommonMessage.debugMsg("getKznmIdeagroupindividual()   "+newKznTlMst.getKznmIdeagroupindividual());
			
			newKznTlMst.setKznmIdeagroupindividual("-");
		if( newKznTlMst.getKznmTheme() == null )
			newKznTlMst.setKznmTheme("<**>");
		if( newKznTlMst.getKznmThemecategoryid() == null )
			newKznTlMst.setKznmThemecategoryid("{}");
		
	/*	if( newKznTlMst.getKznmBenchmark() == null )
			newKznTlMst.setKznmBenchmark("<**>");
	*/	
		if( newKznTlMst.getKznmPresentproblem() == null )
			newKznTlMst.setKznmPresentproblem("<**>");
	
		if( newKznTlMst.getKznmIdea() == null )
			newKznTlMst.setKznmIdea("<**>");
		
		if( newKznTlMst.getKznmPresentimage() == null )
			newKznTlMst.setKznmPresentimage("{}");
		
		if( newKznTlMst.getKznmWwmsKeyid() == null )
			newKznTlMst.setKznmWwmsKeyid("{}");
		
		if( newKznTlMst.getKznmRootcause() == null )
			newKznTlMst.setKznmRootcause("<**>");
		
		if( newKznTlMst.getKznmCountermeasure() == null )
			newKznTlMst.setKznmCountermeasure("<**>");
		
		if( newKznTlMst.getKznmAfterimage() == null )
			newKznTlMst.setKznmAfterimage("<**>");
		
		if( newKznTlMst.getKznmResultdescription() == null )
			newKznTlMst.setKznmResultdescription("<**>");
		
		if( newKznTlMst.getKznmResultimage() == null )
			newKznTlMst.setKznmResultimage("{}");
		
		if( newKznTlMst.getKznmBenefits() == null )
			newKznTlMst.setKznmBenefits("<**>");
		
		if( newKznTlMst.getKznmBenefitsimage() == null )
			newKznTlMst.setKznmBenefitsimage("<**>");
		
		String temp="X";

		CommonMessage.debugMsg("kaizenFormBean.getProviding()" + kaizenFormBean.getProviding());
		CommonMessage.debugMsg("kaizenFormBean.getChanging()" + kaizenFormBean.getChanging());
		
		if(  kaizenFormBean.getProviding()!=null && kaizenFormBean.getProviding().trim().equalsIgnoreCase("P") )
		{
			CommonMessage.debugMsg("ISPROVIDNG "+kaizenFormBean.getProviding());
			newKznTlMst.setKznmIsprovidingchanging("P");
			CommonMessage.debugMsg("ISPROVIDNG "+kaizenFormBean.getProviding());
		}
		else if( kaizenFormBean.getChanging()!=null && kaizenFormBean.getChanging().trim().equalsIgnoreCase("C"))
		{	CommonMessage.debugMsg("CHANGE "+kaizenFormBean.getProviding());
			 newKznTlMst.setKznmIsprovidingchanging("C");
			 CommonMessage.debugMsg("CHANGE "+newKznTlMst.getKznmIsprovidingchanging());
		}
		else
		{
			newKznTlMst.setKznmIsprovidingchanging(temp);
			CommonMessage.debugMsg("ELSE" +newKznTlMst.getKznmIsprovidingchanging());
		}
		
		CommonMessage.debugMsg("REVERSE"+kaizenFormBean.getReversible());
		CommonMessage.debugMsg("IRRREVERSE"+kaizenFormBean.getIrreversible());
		
		if(kaizenFormBean.getReversible()!=null && kaizenFormBean.getReversible().trim().equalsIgnoreCase("R"))
		{
			CommonMessage.debugMsg("If"+kaizenFormBean.getReversible());
			newKznTlMst.setKznmReversibleirreversible("R");
		}
		else if(kaizenFormBean.getIrreversible()!=null && kaizenFormBean.getIrreversible().trim().equalsIgnoreCase("I"))
		{
			CommonMessage.debugMsg("Irreverese"+kaizenFormBean.getIrreversible());
			newKznTlMst.setKznmReversibleirreversible("I");
			CommonMessage.debugMsg("Irreverese"+newKznTlMst.getKznmReversibleirreversible());
		}
		else
		{
			CommonMessage.debugMsg("Else in reverse");
			newKznTlMst.setKznmReversibleirreversible(temp);
		}
		
		CommonMessage.debugMsg("REVERSE LAST ="+newKznTlMst.getKznmReversibleirreversible());
		
		if( newKznTlMst.getKznmKaizenlink() == null)
			newKznTlMst.setKznmKaizenlink("N");
		
		if( newKznTlMst.getKznmKaizenlinktype() == null)
			newKznTlMst.setKznmKaizenlinktype("{}");
		
		CommonMessage.debugMsg(" newKznTlMst.getKznmAssemblyid()" + newKznTlMst.getKznmAssemblyid()+"-"  );
		if( newKznTlMst.getKznmAssemblyid() == null)
			newKznTlMst.setKznmAssemblyid("{}");
		
		if( newKznTlMst.getKznmPhenomenaid() == null)
			newKznTlMst.setKznmPhenomenaid("{}");
		
		if( newKznTlMst.getKznmCauseid() == null)
			newKznTlMst.setKznmCauseid("{}");
		
		if( newKznTlMst.getKznmMaterialcost() == null)
			newKznTlMst.setKznmMaterialcost("<**>");
		
		if( newKznTlMst.getKznmLabourcost() == null)
			newKznTlMst.setKznmLabourcost("<**>");
		
		if( newKznTlMst.getKznmHowtosustain() == null)
			newKznTlMst.setKznmHowtosustain("<**>");
		
		if( newKznTlMst.getKznmAdditionaldetails() == null)
			newKznTlMst.setKznmAdditionaldetails("<**>");
		
		if( newKznTlMst.getKznmAdditionalimage() == null)
			newKznTlMst.setKznmAdditionalimage("<**>");
		
		if( newKznTlMst.getKznmAdditionalimage() == null)
			newKznTlMst.setKznmAdditionalimage("<**>");
		
		CommonMessage.debugMsg("kaizenFormBean.getHdRequiredY()" +kaizenFormBean.getHdRequiredY() );
		CommonMessage.debugMsg("kaizenFormBean.getHdRequiredN()" +kaizenFormBean.getHdRequiredN() );
		if(kaizenFormBean.getHdRequiredY()!=null && kaizenFormBean.getHdRequiredY().trim().equals("Y"))
			newKznTlMst.setKznmIshdpossible("Y");
		else 
			newKznTlMst.setKznmIshdpossible("N");
		
		//CommonMessage.debugMsg("newKznTlMst.getKznmIshdpossible()" +newKznTlMst.getKznmIshdpossible());	
		CommonMessage.debugMsg("WOO "+kaizenFormBean.getWoRequiredY());
		if(kaizenFormBean.getWoRequiredY()!=null && kaizenFormBean.getWoRequiredY().trim().equals("Y"))
		{	newKznTlMst.setKznmIsworequired("Y");
		 CommonMessage.debugMsg("WOO is Y"+newKznTlMst.getKznmIsworequired());
		}
		else if(kaizenFormBean.getWoRequiredN()!=null && kaizenFormBean.getWoRequiredN().trim().equals("N"))
		 {	newKznTlMst.setKznmIsworequired("N");
		 CommonMessage.debugMsg("WOO is N"+newKznTlMst.getKznmIsworequired());
		 }
		else
			
		{	 CommonMessage.debugMsg("WOO Else");
			newKznTlMst.setKznmIsworequired("X");
			 CommonMessage.debugMsg("WOO Else"+newKznTlMst.getKznmIsworequired());	
		}
		
		 CommonMessage.debugMsg("WOO "+newKznTlMst.getKznmIsworequired());
		if( newKznTlMst.getKznmNoofhds() == null )
			newKznTlMst.setKznmNoofhds("0");
		 
		if( newKznTlMst.getKznmPreparedid() == null )
			newKznTlMst.setKznmPreparedid("{}");
		
		if( newKznTlMst.getKznmPrepareddate() == null )
			newKznTlMst.setKznmPrepareddate(Constants.passNullDate);
		
		if( newKznTlMst.getKznmApprovedid() == null )
			newKznTlMst.setKznmApprovedid("{}");
		
		if( newKznTlMst.getKznmApproveddate() == null )
			newKznTlMst.setKznmApproveddate(Constants.passNullDate);
		
		if( newKznTlMst.getKznmRefdoctype() == null)
			newKznTlMst.setKznmRefdoctype("<**>");
		
		if( newKznTlMst.getKznmRefdocno() == null)
			newKznTlMst.setKznmRefdocno("<**>");
		
		CommonMessage.debugMsg("kaizenFormBean.getFormMode()"+kaizenFormBean.getFormMode());
		//if( kaizenFormBean.getFormMode().equals("CREATE")  )
			//newKznTlMst.setKznmStatus("A");
		//else
		//	newKznTlMst.setKznmStatus("C");
		
		if( newKznTlMst.getKznmWoid() == null )
			newKznTlMst.setKznmWoid("{}");
		
		if( newKznTlMst.getKznmWofeedbackid() == null )
			newKznTlMst.setKznmWofeedbackid("{}");
		
		if( newKznTlMst.getKznmCompleteddate() == null)
			newKznTlMst.setKznmCompleteddate(Constants.passNullDate);
		
		if( newKznTlMst.getKznmCompletedid() == null)
			newKznTlMst.setKznmCompletedid("{}");
		
		if( newKznTlMst.getKznmRemarks() == null)
			newKznTlMst.setKznmRemarks("<**>");
		
		if( newKznTlMst.getKznmOperations() == null)
			newKznTlMst.setKznmOperations("<**>");
		
		if( newKznTlMst.getKznmWhattosustain() == null)
			newKznTlMst.setKznmWhattosustain("<**>");
		
		if( newKznTlMst.getKznmSustainfreq() == null)
			newKznTlMst.setKznmSustainfreq("<**>");
		
		if( newKznTlMst.getKznmTotalcost() == null)
			newKznTlMst.setKznmTotalcost("<**>");
		
		if( newKznTlMst.getKznmCircleid() == null)
			newKznTlMst.setKznmCircleid("{}");
		
		if( newKznTlMst.getKznmDepartmentid() == null)
			newKznTlMst.setKznmDepartmentid("{}");
		
		if( newKznTlMst.getKznmCostcentreid() == null)
			newKznTlMst.setKznmCostcentreid("{}");
		
		newKznTlMst.setKznmIstpmkzn("R");
		
		if( newKznTlMst.getKznmMaterialno() == null)
			newKznTlMst.setKznmMaterialno("<**>");
		
		if(newKznTlMst.getKznmIsworthformp()==null)
			newKznTlMst.setKznmIsworthformp("N");
		else if(newKznTlMst.getKznmIsworthformp().equalsIgnoreCase("ON"))
			newKznTlMst.setKznmIsworthformp("Y");
		
		if(newKznTlMst.getKznmMouldid()==null)
			newKznTlMst.setKznmMouldid("{}");
		
		newKznTlMst.setKznmCreationflag("N");
	
		CommonMessage.debugMsg("In fill Service impl="+newKznTlMst);
		newKznTlMst.setPillarLink( fillPillarLinkValues(newKznTlMst,oldKznTlMst));//Pillar Link
		
		newKznTlMst.setLossLink(fillLossLinkValues(newKznTlMst,oldKznTlMst));//Loss Link
		
		//CommonMessage.debugMsg("In Main Fill Values="+newKznTlMst.getLossLink().size());
		//CommonMessage.debugMsg("In Main Fill Values="+newKznTlMst.getLossLink());
		
	//	newKznTlMst.setGraphData(fillKznGraphDataValues(newKznTlMst,oldKznTlMst));//Graph Data

		return newKznTlMst; 
	}
	
	private List<KznTlPillarlink> fillPillarLinkValues(KznTlMst newKznTlMst,KznTlMst oldKznTlMst) 
	{
		CommonMessage.debugMsg("Inside pillar fillval");
		
		String currentDate = CommonFunctions.getDate();
		List<KznTlPillarlink> newOplTlPillarlink = newKznTlMst.getPillarLink();
		List<KznTlPillarlink> oldKznTlPillarLinks = null;
		KznTlPillarlink oldKznTlPillarLink  = null;

		if( oldKznTlMst != null)
		{
			CommonMessage.debugMsg("before insert INSIDE IF");
			oldKznTlPillarLinks = oldKznTlMst.getPillarLink();

			if( oldKznTlPillarLinks != null && oldKznTlPillarLinks.size() > 0 ){
				CommonMessage.debugMsg("before insert IF IFF ");
				oldKznTlPillarLink = oldKznTlPillarLinks.get(0);
				CommonMessage.debugMsg(oldKznTlPillarLink.getKzplCreatedon()+"before insert INSIDE IF "+oldKznTlPillarLinks.size());
			}
		}	
		CommonMessage.debugMsg("before insert");
		
		List<KznTlPillarlink> newOplTlPillarlinkList = new ArrayList<KznTlPillarlink>();
		if(newOplTlPillarlink != null)
		{
			for( KznTlPillarlink kznTlPillarlink : newOplTlPillarlink)
			{	
				CommonMessage.debugMsg("newKznTlMst.getKznmKeyid()="+newKznTlMst.getKznmCreatedon());
				kznTlPillarlink.setKzplActive("Y");
		
				/*if(kznTlPillarlink.getDbMode().equals("INSERT"))			
				{*/	
					kznTlPillarlink.setKzplCreatedon(currentDate);
				/*}	
				else
				{
					kznTlPillarlink.setKzplCreatedon(oldKznTlPillarLink.getKzplCreatedon());
				}*/
				CommonMessage.debugMsg("newKznTlMst.getKznmKeyid()="+newKznTlMst.getKznmKeyid());
			
				
				kznTlPillarlink.setKzplModifiedon(currentDate);
				
				if( kznTlPillarlink.getKzplTpmpillarid() == null )
					kznTlPillarlink.setKzplTpmpillarid("{}");
				
				if(kznTlPillarlink.getKzplKzncategoryid() == null)
					kznTlPillarlink.setKzplKzncategoryid("{}");
					
				kznTlPillarlink.setKzplCreatedby(newKznTlMst.getKznmCreatedby());
				
				if(kznTlPillarlink.getSelectionFlag().equals("DELETE"))
					kznTlPillarlink.setDbMode("DELETE");
							   
				if(kznTlPillarlink.getSelectionFlag().equals("UPDATE"))
					kznTlPillarlink.setDbMode("UPDATE");
				
				
				newOplTlPillarlinkList.add(kznTlPillarlink);
			}
		}
		return newOplTlPillarlinkList;
    }
	
	
	private List<KznTlLosslink> fillLossLinkValues(KznTlMst newKznTlMst,KznTlMst oldKznTlMst) 
	{
		CommonMessage.debugMsg("Inside loss fillval");
		String currentDate = CommonFunctions.getDate();
		List<KznTlLosslink> newKznTlLosslink = newKznTlMst.getLossLink();
		List<KznTlLosslink> oldKznTlLosslinks = null;
		KznTlLosslink oldKznTlLosslink  = null;
		
		if( oldKznTlMst != null)
		{
			oldKznTlLosslinks = oldKznTlMst.getLossLink();
		
			if( oldKznTlLosslinks != null && oldKznTlLosslinks.size() > 0 )
				oldKznTlLosslink = oldKznTlLosslinks.get(0);
		}	
		List<KznTlLosslink> LosslinkList = new ArrayList<KznTlLosslink>();
		List<KznTlLosslink> newKznTlLosslinkList = new ArrayList<KznTlLosslink>();
		if(newKznTlLosslink != null)
		{
			for( KznTlLosslink kznTlLosslink : newKznTlLosslink)
			{	
				CommonMessage.debugMsg("LOss id="+kznTlLosslink.getKzllLossid());
				String lossKeyid =kznTlLosslink.getKzllLossid();
			
				String[] splitLossKeyid = lossKeyid.split( ",\\s*" );
				StringBuffer splitLossId=new StringBuffer(lossKeyid);
				//splitLossId.append(lossKeyid.split( ",\\s*" ));
				
				
				for ( int i=0;i<splitLossKeyid.length;i++)
				{
					KznTlLosslink newkznTlLosslink = new KznTlLosslink();
							
					CommonMessage.debugMsg("splitLossKeyid["+i+"]="+splitLossKeyid[i]);
					
					newkznTlLosslink.setKzllActive("Y");
					newkznTlLosslink.setKzllKaizenid(kznTlLosslink.getKzllKaizenid());
					newkznTlLosslink.setKzllTpmpillarid(kznTlLosslink.getKzllTpmpillarid());
					newkznTlLosslink.setKzllCreatedby(newKznTlMst.getKznmCreatedby());
					newkznTlLosslink.setKzllCreatedon(currentDate);
					newkznTlLosslink.setKzllLossid(splitLossKeyid[i]);
					newkznTlLosslink.setKzllModifiedon(currentDate);
					CommonMessage.debugMsg("Loss KeyID="+newkznTlLosslink.getKzllLossid());
					
					newkznTlLosslink.setSelectLossFlag(kznTlLosslink.getSelectLossFlag());
					//newKznTlLosslink.add(newkznTlLosslink);
					newKznTlLosslinkList.add(newkznTlLosslink);
				//	LosslinkList.add(newkznTlLosslink);
					
				}
				kznTlLosslink.setKzllCreatedon(currentDate);
				
				CommonMessage.debugMsg("newKznTlMst.getKznmKeyid()="+newKznTlMst.getKznmKeyid());
			
			CommonMessage.debugMsg("kznTlLosslink.getKzllCreatedon"+kznTlLosslink.getKzllCreatedon());
				
				//newKznTlLosslinkList.add(kznTlLosslink);
			}
		}
		return newKznTlLosslinkList;
    }

	@Override
	public KznTlMst select(String kznKeyid)throws Exception  {
		// TODO Auto-generated method stub
		return kznTlMstDao.select(kznKeyid);
	}

	@Override
	public  KznTlMst getkznImage( String fileName, String filePath, KznTlMst kznTlMst)throws NoDataFoundException, Exception
	{
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside Service Impl img");
		List<GenTlAllmoduleimgfile> kznImgList = null;
		

		kznImgList = getKaizenImage(fileName,filePath,kznTlMst.getKznmKeyid());
		
		if(kznImgList != null)
		{
			for( GenTlAllmoduleimgfile genTlAllmoduleimgfile : kznImgList){
				if( "AFT".equals(genTlAllmoduleimgfile.getImflImagetype() ))
						kznTlMst.setKznmAfterimage(genTlAllmoduleimgfile.getImflFilename());
				else if( "PRE".equals(genTlAllmoduleimgfile.getImflImagetype()) )
						kznTlMst.setKznmPresentimage(genTlAllmoduleimgfile.getImflFilename());
				else if( "RES".equals(genTlAllmoduleimgfile.getImflImagetype()) )
					kznTlMst.setKznmResultimage(genTlAllmoduleimgfile.getImflFilename());
				else if( "BEN".equals(genTlAllmoduleimgfile.getImflImagetype()) )
					kznTlMst.setKznmBenefitsimage(genTlAllmoduleimgfile.getImflFilename());
			}
			kznTlMst.setAllmoduleimgfile(kznImgList);
		}
		return kznTlMst;
	}

	@Override
	public String getkaizenTheme(String kznbKeyid) throws Exception {
		return kznTlMstDao.getkaizenTheme(kznbKeyid);
	}


	@Override
	public String getkaizenBenefit(String kznbKeyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getkaizenBenefit(kznbKeyid);
	}


	@Override
	public String getThemename(String benefit) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getThemename(benefit);
	}


	@Override
	public String getkaizenPcdqsme(String kznbKeyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getkaizenPcdqsme(kznbKeyid);
	}
	public List<GenTlAllmoduleimgfile> getKaizenImage(String fileName, String filePath, String kznKeyId)throws NoDataFoundException, Exception{
		CommonMessage.debugMsg("kaizen img...test");
		List<GenTlAllmoduleimgfile> kznImgList = new ArrayList<GenTlAllmoduleimgfile>();
		CommonMessage.debugMsg("kaizen img...");
		if(kznKeyId!=null)
		{
			CommonMessage.debugMsg("fileName:"+fileName+",path:"+filePath+",kaizId:"+kznKeyId);
			
			GenTlAllmoduleimgfile kznBeforeImage = new GenTlAllmoduleimgfile();
				
			kznBeforeImage.setImflBlobimage(filePath);
			kznBeforeImage.setImflFilename(fileName);
			kznBeforeImage.setImflRefkeyid(kznKeyId);
			kznBeforeImage.setImflRefdoctype("KZN");
			kznBeforeImage.setImflImagetype("PRE");
			kznImgList.add(kznBeforeImage);
			
			GenTlAllmoduleimgfile kznAfterImage = new GenTlAllmoduleimgfile();
			
			kznAfterImage.setImflBlobimage(filePath);
			kznAfterImage.setImflFilename(fileName);
			kznAfterImage.setImflRefkeyid(kznKeyId);
			kznAfterImage.setImflRefdoctype("KZN");
			kznAfterImage.setImflImagetype("AFT");
			kznImgList.add(kznAfterImage);
			
			GenTlAllmoduleimgfile kznResultImage = new GenTlAllmoduleimgfile();
			
			kznResultImage.setImflBlobimage(filePath);
			kznResultImage.setImflFilename(fileName);
			kznResultImage.setImflRefkeyid(kznKeyId);
			kznResultImage.setImflRefdoctype("KZN");
			kznResultImage.setImflImagetype("RES");
			kznImgList.add(kznResultImage);
			CommonMessage.debugMsg("kznImgList:"+kznImgList);
			
            GenTlAllmoduleimgfile kznBenefitsImage = new GenTlAllmoduleimgfile();
			
			kznBenefitsImage.setImflBlobimage(filePath);
			kznBenefitsImage.setImflFilename(fileName);
			kznBenefitsImage.setImflRefkeyid(kznKeyId);
			kznBenefitsImage.setImflRefdoctype("KZN");
			kznBenefitsImage.setImflImagetype("BEN");
			kznImgList.add(kznBenefitsImage);
			CommonMessage.debugMsg("kznImgList:"+kznImgList);
		}
		kznImgList = kznTlMstDao.getKznImage(kznImgList );
		
		return kznImgList;
	}

	@Override
	public String selectKznb(String kznbKeyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.selectKznb(kznbKeyid);
	}

	
	
}
