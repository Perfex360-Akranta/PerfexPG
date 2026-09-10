package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.bean.WOFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.WomTlWomstDao;
import com.akranta.tpm.dao.WomTlWorkorderMstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.WomTlWomstDaoImpl;
import com.akranta.tpm.dao.impl.WomTlWorkorderMstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.BdmTlDtl;
import com.akranta.tpm.model.BdmTlMst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MldTlMouldunloadmst;
import com.akranta.tpm.model.PlmTlGenmaintenance;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.model.WomTlWorkorderMst;
import com.akranta.tpm.model.WomsTlTaskmst;
import com.akranta.tpm.service.WorkOrderService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.BdmServiceApi;


public class WorkOrderServiceImpl implements WorkOrderService {
	private Validations validations ;
	private CommonFilterDao commonFilterDao;
	private WomTlWomstDao womTlWomstDao;
	private BdmServiceApi bdmServiceApi;
	
	private WomTlWorkorderMstDao womTlWorkorderMstDao;
	
	public WorkOrderServiceImpl(DBActionTemplate dbActionTemplate)
	{
		validations = new Validations();
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		womTlWomstDao = new WomTlWomstDaoImpl(dbActionTemplate);
		
		womTlWorkorderMstDao = new WomTlWorkorderMstDaoImpl(dbActionTemplate);
	}
	
	public void WorkOrderServiceImplJwt(String JwtToken) {
		try {
			womTlWomstDao.WomTlWomstDaoImplJwt(JwtToken);
			bdmServiceApi = new BdmServiceApi(JwtToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}


	public  WomTlWorkorderMst createNew(WomTlWorkorderMst newWomTlWorkorderMst, WomTlWorkorderMst existWomTlWorkorderMst, WOFormBean woFormBean)throws ValidationExceptions, Exception {
		try {
			String validationsFor;
			validationsFor = "create";		
			
			if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsOccurredDate()))
			{
				if(UIUtils.isValidKeyId(woFormBean.getWomsOccuredtime()))
					woFormBean.setOccuredDateTime(newWomTlWorkorderMst.getWomsOccurredDate() + " "+woFormBean.getWomsOccuredtime());
			}
			//else
			//	woFormBean.setOccuredDateTime(Constants.passNullDate);
		
			CommonMessage.debugMsg("newWomTlWorkorderMst.getWomsReportedDate()=="+newWomTlWorkorderMst.getWomsReportedDate());
			if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsReportedDate()))
			{
				if(UIUtils.isValidKeyId(woFormBean.getWomsReportedtime()))
					woFormBean.setReportedDateTime(newWomTlWorkorderMst.getWomsReportedDate() + " "+woFormBean.getWomsReportedtime());
			}	
			//else
			//	woFormBean.setReportedDateTime(Constants.passNullDate);
			
			validations.validate(newWomTlWorkorderMst,"wocreationnew",validationsFor);
			validations.validate(woFormBean,"wocreationnew",validationsFor);
			
			fillValuesNew(newWomTlWorkorderMst,existWomTlWorkorderMst,woFormBean);
			
			if(UIUtils.isValidKeyId(woFormBean.getMandFieldFlag()))
			{
				if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsActivityType()))
				{
					if(newWomTlWorkorderMst.getWomsActivityType().equals("X"))
						newWomTlWorkorderMst.setWomsActivityType("-");
				}
				validations.validate(newWomTlWorkorderMst,"wocreation","configurationBasedFields");
			}
			newWomTlWorkorderMst.setWomsAllottedFlag("N");
			newWomTlWorkorderMst.setWomsStatus("B");
			newWomTlWorkorderMst.setWomsFinalStatus("BOOKED");
			/*BdmTlMst bdmTlMst = new BdmTlMst();				
			if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsActivitytype()) && newWomTlWorkorderMst.getWomsActivitytype().equals("B"))
			{
				fillBdValues(newWomTlWorkorderMst,woFormBean,bdmTlMst);					
				bdmTlMst.setBdmsStatus(newWomTlWorkorderMst.getWomsStatus());					
			}*/
			WomsTlTaskmst womsTlTaskmst = new WomsTlTaskmst();
			String lineNo="";
			//lineNo=getLineNo("WOMS_TL_TASKMST", womsTlTaskmst.getWtmsWomsKeyid() , "WTMS_WOMS_KEYID");
			//CommonMessage.debugMsg("Line no for task: "+lineNo);
			//if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsActivityno()))
			//	womsTlTaskmst.setWtmsActivityno(lineNo);
			
			fillValuesTaskmst( womsTlTaskmst);
			return womTlWorkorderMstDao.create(newWomTlWorkorderMst,womsTlTaskmst);
	}
	catch(ValidationExceptions e){			
		CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
		throw new ValidationExceptions(e.getMessage());
	}	
	}
	public  WomTlWorkorderMst updateNew(WomTlWorkorderMst newWomTlWorkorderMst, WomTlWorkorderMst existWomTlWorkorderMst, WOFormBean woFormBean)throws ValidationExceptions, Exception {
		CommonMessage.debugMsg(" second Service Impl ");
		String validationsFor = "updateRequest";
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsOccurredDate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsOccuredtime()))
				woFormBean.setOccuredDateTime(newWomTlWorkorderMst.getWomsOccurredDate() + " "+woFormBean.getWomsOccuredtime());
		}
		else
			woFormBean.setOccuredDateTime(Constants.passNullDate);
	
		CommonMessage.debugMsg("newWomTlWorkorderMst.getWomsReportedDate()=="+newWomTlWorkorderMst.getWomsReportedDate());
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsReportedDate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsReportedtime()))
				woFormBean.setReportedDateTime(newWomTlWorkorderMst.getWomsReportedDate() + " "+woFormBean.getWomsReportedtime());
		}	
		//else
		//	woFormBean.setReportedDateTime(Constants.passNullDate);
		//CommonMessage.debugMsg("ChkProcess  "+newWomTlWorkorderMst.getChkProcess());
		validations.validate(newWomTlWorkorderMst,"wocreationnew",validationsFor);
		validations.validate(woFormBean,"wocreationnew",validationsFor);
		
		fillValuesNew(newWomTlWorkorderMst,existWomTlWorkorderMst,woFormBean);
		
		if(UIUtils.isValidKeyId(woFormBean.getMandFieldFlag()))
		{
			if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsActivityType()))
			{
				if(newWomTlWorkorderMst.getWomsActivityType().equals("X"))
					newWomTlWorkorderMst.setWomsActivityType("-");
			}
			validations.validate(newWomTlWorkorderMst,"wocreation","configurationBasedFields");
		}
		newWomTlWorkorderMst.setWomsStatus("B");
		newWomTlWorkorderMst.setWomsFinalStatus("BOOKED");
		
		WomsTlTaskmst womsTlTaskmst = new WomsTlTaskmst();
		String lineNo="";
		//lineNo=getLineNo("WOMS_TL_TASKMST", womsTlTaskmst.getWtmsWomsKeyid() , "WTMS_WOMS_KEYID");
		//CommonMessage.debugMsg("Line no for task: "+lineNo);
		
		//if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsActivityno()))
		//	womsTlTaskmst.setWtmsActivityno(lineNo);
		
		fillValuesTaskmst( womsTlTaskmst);
		return womTlWorkorderMstDao.update(newWomTlWorkorderMst, womsTlTaskmst);
		
	}

	public WomTlWorkorderMst deleteNew(WomTlWorkorderMst womTlWorkorderMst) throws Exception
	{
		return womTlWorkorderMstDao.delete(womTlWorkorderMst);		
	}
	
	
	
	public WomTlWomst create(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,WOFormBean woFormBean ) throws ValidationExceptions, Exception
	{
		try {
				String validationsFor;
				validationsFor = "create";		
				
				if(UIUtils.isValidKeyId(newWomTlWomst.getWomsOccurreddate()))
				{
					if(UIUtils.isValidKeyId(woFormBean.getWomsOccuredtime()))
						woFormBean.setOccuredDateTime(newWomTlWomst.getWomsOccurreddate() + " "+woFormBean.getWomsOccuredtime());
				}
				else
					woFormBean.setOccuredDateTime(Constants.passNullDate);
			
				if(UIUtils.isValidKeyId(newWomTlWomst.getWomsReporteddate()))
				{
					if(UIUtils.isValidKeyId(woFormBean.getWomsReportedtime()))
						woFormBean.setReportedDateTime(newWomTlWomst.getWomsReporteddate() + " "+woFormBean.getWomsReportedtime());
				}	
				else
					woFormBean.setReportedDateTime(Constants.passNullDate);
				
				validations.validate(newWomTlWomst,"wocreation",validationsFor);
				validations.validate(woFormBean,"wocreation",validationsFor);
				fillValues(newWomTlWomst,oldWomTlWomst,woFormBean);
				if(UIUtils.isValidKeyId(woFormBean.getMandFieldFlag()))
				{
					if(UIUtils.isValidKeyId(newWomTlWomst.getWomsActivitytype()))
					{
						if(newWomTlWomst.getWomsActivitytype().equals("X"))
							newWomTlWomst.setWomsActivitytype("-");
					}
					validations.validate(newWomTlWomst,"wocreation","configurationBasedFields");
				}
				newWomTlWomst.setWomsAllottedflag("N");
				newWomTlWomst.setWomsStatus("B");
				newWomTlWomst.setWomsFinalstatus("BOOKED");
				/*BdmTlMst bdmTlMst = new BdmTlMst();				
				if(UIUtils.isValidKeyId(newWomTlWomst.getWomsActivitytype()) && newWomTlWomst.getWomsActivitytype().equals("B"))
				{
					fillBdValues(newWomTlWomst,woFormBean,bdmTlMst);					
					bdmTlMst.setBdmsStatus(newWomTlWomst.getWomsStatus());					
				}*/
				return womTlWomstDao.create(newWomTlWomst);
		}
		catch(ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}			
		
	}
	public WomTlWomst update(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,WOFormBean woFormBean )  throws Exception
	{
		CommonMessage.debugMsg(" second Service Impl ");
		String validationsFor = "updateRequest";
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsOccurreddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsOccuredtime()))
				woFormBean.setOccuredDateTime(newWomTlWomst.getWomsOccurreddate() + " "+woFormBean.getWomsOccuredtime());
		}
		else
			woFormBean.setOccuredDateTime(Constants.passNullDate);
	
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsReporteddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsReportedtime()))
				woFormBean.setReportedDateTime(newWomTlWomst.getWomsReporteddate() + " "+woFormBean.getWomsReportedtime());
		}	
		else
			woFormBean.setReportedDateTime(Constants.passNullDate);
		CommonMessage.debugMsg("ChkProcess  "+newWomTlWomst.getChkProcess());
		validations.validate(newWomTlWomst,"wocreation",validationsFor);
		validations.validate(woFormBean,"wocreation",validationsFor);
		fillValues(newWomTlWomst,oldWomTlWomst,woFormBean);
		if(UIUtils.isValidKeyId(woFormBean.getMandFieldFlag()))
		{
			if(UIUtils.isValidKeyId(newWomTlWomst.getWomsActivitytype()))
			{
				if(newWomTlWomst.getWomsActivitytype().equals("X"))
					newWomTlWomst.setWomsActivitytype("-");
			}
			validations.validate(newWomTlWomst,"wocreation","configurationBasedFields");
		}
		newWomTlWomst.setWomsStatus("B");
		newWomTlWomst.setWomsFinalstatus("BOOKED");
		return womTlWomstDao.update(newWomTlWomst);
	}
	public WomTlWomst updateApproval(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception
	{
		CommonMessage.debugMsg("  3 Service Impl ");
		String validationsFor = "updateApproval";
		CommonMessage.debugMsg(validationsFor);
		validations.validate(newWomTlWomst,"wocreation",validationsFor);
		validations.validate(woFormBean,"wocreation",validationsFor);
		fillValues(newWomTlWomst,oldWomTlWomst,woFormBean);
		return womTlWomstDao.updateApproval(newWomTlWomst,oldWomTlWomst,woFormBean);
	}
	public WomTlWomst cancelWorkOrder(WomTlWomst womTlWomst)  throws Exception
	{
		womTlWomst.setWomsRequestapproved("C");
		womTlWomst.setWomsActive("N");
		womTlWomst.setWomsStatus("D");
		return womTlWomstDao.cancelWorkOrder(womTlWomst);
	}
	public WomTlWomst updateCreation(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception
	{
		CommonMessage.debugMsg("  4 Service Impl ");
		String validationsFor = "updateCreation";		
		validations.validate(newWomTlWomst,"wocreation",validationsFor);		
		validations.validate(woFormBean,"wocreation",validationsFor);
		fillValues(newWomTlWomst,oldWomTlWomst,woFormBean);
		BdmTlMst bdmTlMst = new BdmTlMst();
		newWomTlWomst.setWomsStatus("R");
		if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsKeyid()) && oldWomTlWomst.getWomsActivitytype().equals("BD"))
		{
			fillBdValues(oldWomTlWomst,woFormBean,bdmTlMst);
			bdmTlMst.setBdmsRemarks(newWomTlWomst.getWomsAcceptedremarks());
			bdmTlMst.setBdmsStatus(newWomTlWomst.getWomsStatus());
			if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
			{	
				BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0);
				bdmDetail.setBdanActiontakenby(newWomTlWomst.getWomsAcceptedby());
				bdmDetail.setBdanRemarks(newWomTlWomst.getWomsAcceptedremarks());
				bdmDetail.setBdanStatus(newWomTlWomst.getWomsStatus());
				bdmDetail.setBdanErppoststatus(newWomTlWomst.getWomsStatus());
				
			}
		}
		
		return womTlWomstDao.updateCreation(newWomTlWomst,oldWomTlWomst,woFormBean,bdmTlMst);
	}
	public WomTlWomst updateAcceptance(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception
	{
		CommonMessage.debugMsg(" 5 Service Impl ");
		String validationsFor = "updateAcceptance";
		CommonMessage.debugMsg(validationsFor);
		validations.validate(newWomTlWomst,"wocreation",validationsFor);
		validations.validate(woFormBean,"wocreation",validationsFor);
		fillValues(newWomTlWomst,oldWomTlWomst,woFormBean);	
		BdmTlMst bdmTlMst = new BdmTlMst();
		newWomTlWomst.setWomsStatus("P");
		if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsKeyid()) && oldWomTlWomst.getWomsActivitytype().equals("BD"))
		{
			fillBdValues(oldWomTlWomst,woFormBean,bdmTlMst);
			bdmTlMst.setBdmsRemarks(newWomTlWomst.getWomsRescheduleremarks());
			bdmTlMst.setBdmsStatus(newWomTlWomst.getWomsStatus());
			if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
			{	
				BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0);
				bdmDetail.setBdanActiontakenby(newWomTlWomst.getWomsRescheduleby());
				bdmDetail.setBdanRemarks(newWomTlWomst.getWomsRescheduleremarks());
				bdmDetail.setBdanStatus(newWomTlWomst.getWomsStatus());
				bdmDetail.setBdanErppoststatus(newWomTlWomst.getWomsStatus());			
				CommonMessage.debugMsg(bdmTlMst.getBdmsRemarks());
			}
		}
		
		return womTlWomstDao.updateAcceptance(newWomTlWomst,oldWomTlWomst,woFormBean,bdmTlMst);
	}
	public WomTlWomst updateAllocation(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception
	{
		CommonMessage.debugMsg("  6 Service Impl ");
		
		String validationsFor = "updateAllocation";
		CommonMessage.debugMsg(validationsFor + newWomTlWomst.getWomsAllotteddate() + " - "+ newWomTlWomst.getWomsOccurreddate() + " - OO - " + oldWomTlWomst.getWomsOccurreddate() + " - OR - " + oldWomTlWomst.getWomsReporteddate() );
		
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsFinalactivitytype()) && newWomTlWomst.getWomsFinalactivitytype().equals("X"))
			newWomTlWomst.setWomsFinalactivitytype("-");
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsAllotteddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsAllottedtime()))
				woFormBean.setAllottedDateTime(newWomTlWomst.getWomsAllotteddate() + " "+woFormBean.getWomsAllottedtime());
		}
		else
			woFormBean.setAllottedDateTime(Constants.passNullDate);
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsAccepteddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsAcceptedtime()))
				woFormBean.setAcceptedDateTime(newWomTlWomst.getWomsAccepteddate() + " "+woFormBean.getWomsAcceptedtime());
		}
		else
			woFormBean.setAcceptedDateTime(Constants.passNullDate);
	
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsReporteddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsReportedtime()))
				woFormBean.setReportedDateTime(newWomTlWomst.getWomsReporteddate() + " "+woFormBean.getWomsReportedtime());
		}	
		else
			woFormBean.setReportedDateTime(Constants.passNullDate);
		validations.validate(newWomTlWomst,"wocreation",validationsFor);
		validations.validate(woFormBean,"wocreation",validationsFor);
		
		fillValues(newWomTlWomst,oldWomTlWomst,woFormBean);
		
		BdmTlMst bdmTlMst = new BdmTlMst();
		AbnTlAbnormality abnTlAbnormality = null; 
		PlmTlGenmaintenance plmTlGenmaintenance = null; 
		MldTlMouldunloadmst mldTlMouldunloadmst = null;
		
		if(newWomTlWomst.getWomsStatus().substring(0, 1).equals("P"))
		{
			newWomTlWomst.setWomsStatus("E");	
			newWomTlWomst.setWomsFinalstatus("PRODUCTION APPROVED WITHOUT COMPLETION");
		}
		else
		{
			newWomTlWomst.setWomsStatus("L");
			newWomTlWomst.setWomsFinalstatus("ALLOTTED");
		}
		
		/*if(UIUtils.isValidKeyId(woFormBean.getWomsActtype()))
		{
		  newWomTlWomst.setWomsActivitytype(woFormBean.getWomsActtype());
		}*/
		if(UIUtils.isValidKeyId(woFormBean.getWomsRelateto()))
		{
		  newWomTlWomst.setWomsRelatedto(woFormBean.getWomsRelateto());
		}
		CommonMessage.debugMsg("woFormBean.getAllottedBy()" +woFormBean.getAllottedBy());
		if(UIUtils.isValidKeyId(woFormBean.getAllottedBy()))
		{
		  newWomTlWomst.setWomsAllottedsource(woFormBean.getAllottedBy());
		}
		
		if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsKeyid()))
		{
			if(newWomTlWomst.getWomsFinalactivitytype().equals("BD") || newWomTlWomst.getWomsFinalactivitytype().equals("U"))
			{
				fillBdValues(oldWomTlWomst,woFormBean,bdmTlMst);
				//if(UIUtils.isValidKeyId(newWomTlWomst.getWomsAllottedremarks()))
				bdmTlMst.setBdmsRelatedto(newWomTlWomst.getWomsRelatedto());
				bdmTlMst.setBdmsRemarks("{}");
				bdmTlMst.setBdmsStatus("A");
				bdmTlMst.setBdmsWoallottedflag(newWomTlWomst.getWomsAllottedflag());
				bdmTlMst.setBdmsReceiveddate(newWomTlWomst.getWomsAllotteddate());
				bdmTlMst.setBdmsProductionstop(newWomTlWomst.getWomsProductionstop());
				//bdmTlMst.setBdmsBookedby(woFormBean.getAllottedBy());
				bdmTlMst.setBdmsBookedby(oldWomTlWomst.getWomsReportedby());
				bdmTlMst.setBdmsWostartflag("N");
				bdmTlMst.setBdmsWoendflag("N");
				
				if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsProductionstartflag()))
					bdmTlMst.setBdmsWoprodaccepflag(oldWomTlWomst.getWomsProductionstartflag());
				else
					bdmTlMst.setBdmsWoprodaccepflag("N");
				
				bdmTlMst.setBdmsWostarttime(Constants.passNullDate);
				bdmTlMst.setBdmsWoendtime(Constants.futureNullDate);
				
				if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsProductionstartdate()))
					bdmTlMst.setBdmsProdaccepdate(oldWomTlWomst.getWomsProductionstartdate());
				else
					bdmTlMst.setBdmsProdaccepdate(Constants.futureNullDate);
				
				if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
				{	
					BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0);		
					//if(UIUtils.isValidKeyId(newWomTlWomst.getWomsAllottedremarks()))
					bdmDetail.setBdanRemarks("{}");
					bdmDetail.setBdanStatus(newWomTlWomst.getWomsStatus());
					bdmDetail.setBdanErppoststatus("A");		
					bdmDetail.setBdanCompletedby(newWomTlWomst.getWomsAllottedto());
					//bdmDetail.setBdanCompletedby(woFormBean.getAllottedBy());
					
				}
			}
			else if(newWomTlWomst.getWomsFinalactivitytype().equals("AB"))
			{
				abnTlAbnormality = new AbnTlAbnormality();
				fillAbnValues(oldWomTlWomst,woFormBean,abnTlAbnormality);
			
				abnTlAbnormality.setAbnmStatus("P");
				//abnTlAbnormality.setAbnmTypeid("ABT0006");
				abnTlAbnormality.setAbnmDetectedby(woFormBean.getAllottedBy());
				abnTlAbnormality.setAbnmTargetdate(newWomTlWomst.getWomsAllotteddate());
				CommonMessage.debugMsg("DETECTED BY "+newWomTlWomst.getWomsAllottedremarks());				
				abnTlAbnormality.setAbnmTargetremarks(newWomTlWomst.getWomsAllottedremarks());
				
				CommonMessage.debugMsg("DETECTED BY "+abnTlAbnormality.getAbnmTargetremarks());
				
				if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsOccurreddate()))
					newWomTlWomst.setWomsOccurreddate(oldWomTlWomst.getWomsOccurreddate());
				if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsReporteddate()))
					newWomTlWomst.setWomsReporteddate(oldWomTlWomst.getWomsReporteddate());
				if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsReportedby()))
					newWomTlWomst.setWomsReportedby(oldWomTlWomst.getWomsReportedby());
				if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsAssemblyid()))
					newWomTlWomst.setWomsAssemblyid(oldWomTlWomst.getWomsAssemblyid());
				
			}
			else if(newWomTlWomst.getWomsFinalactivitytype().equals("GN") || newWomTlWomst.getWomsFinalactivitytype().equals("S"))
			{
				plmTlGenmaintenance = new PlmTlGenmaintenance();
				fillGMValues(oldWomTlWomst,woFormBean,plmTlGenmaintenance);
				
				if(UIUtils.isValidKeyId(newWomTlWomst.getWomsFinalactivitytype()))
				{
					if(newWomTlWomst.getWomsFinalactivitytype().equals("S"))
						plmTlGenmaintenance.setGmntActivitytype(newWomTlWomst.getWomsFinalactivitytype());
					else
						plmTlGenmaintenance.setGmntActivitytype("O");
				}
			
				plmTlGenmaintenance.setGmntCompletedby(woFormBean.getAllottedBy());
				//if(UIUtils.isValidKeyId(newWomTlWomst.getWomsAllottedremarks()))
				plmTlGenmaintenance.setGmntRemarks(newWomTlWomst.getWomsAllottedremarks());
				plmTlGenmaintenance.setGmntStatus("P");
				plmTlGenmaintenance.setGmntCompletedby(woFormBean.getAllottedBy());
				plmTlGenmaintenance.setGmntTargetdate(newWomTlWomst.getWomsAllotteddate());				
				plmTlGenmaintenance.setGmntWostartdate(newWomTlWomst.getWomsAllotteddate());
				plmTlGenmaintenance.setGmntWoenddate(newWomTlWomst.getWomsAllotteddate());		
				plmTlGenmaintenance.setGmntReportedby(woFormBean.getAllottedBy());
				//plmTlGenmaintenance.setGmntTargetdate(newWomTlWomst.getWomsAllotteddate());
				plmTlGenmaintenance.setGmntReceiveddate(newWomTlWomst.getWomsAccepteddate());
				
			}			
			else if(newWomTlWomst.getWomsFinalactivitytype().equals("L"))
			{
				mldTlMouldunloadmst = new MldTlMouldunloadmst();
				fillMouldUnloadValues(oldWomTlWomst,woFormBean,mldTlMouldunloadmst);
				if(UIUtils.isValidKeyId(newWomTlWomst.getWomsFinalactivitytype()))
				{
					if(newWomTlWomst.getWomsFinalactivitytype().equals("L"))
						mldTlMouldunloadmst.setMunlActivitytype(newWomTlWomst.getWomsFinalactivitytype());
					else
						mldTlMouldunloadmst.setMunlActivitytype("O");
				}
				mldTlMouldunloadmst.setMunlCompletedby(woFormBean.getAllottedBy());
				mldTlMouldunloadmst.setMunlRemarks(newWomTlWomst.getWomsAllottedremarks());
				mldTlMouldunloadmst.setMunlStatus("P");			
				mldTlMouldunloadmst.setMunlAllocateddate(newWomTlWomst.getWomsAllotteddate());
				mldTlMouldunloadmst.setMunlTargetdate(newWomTlWomst.getWomsAllotteddate());				
				mldTlMouldunloadmst.setMunlWostartdate(newWomTlWomst.getWomsAllotteddate());
				mldTlMouldunloadmst.setMunlWoenddate(newWomTlWomst.getWomsAllotteddate());		
				mldTlMouldunloadmst.setMunlReportedby(woFormBean.getAllottedBy());
				mldTlMouldunloadmst.setMunlReceiveddate(newWomTlWomst.getWomsAccepteddate());
			}
			
		}
		/*if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsKeyid()) && oldWomTlWomst.getWomsActivitytype().equals("B"))
		{
			fillBdValues(oldWomTlWomst,woFormBean,bdmTlMst);
			bdmTlMst.setBdmsRemarks(newWomTlWomst.getWomsAllottedremarks());
			bdmTlMst.setBdmsStatus(newWomTlWomst.getWomsStatus());
			bdmTlMst.setBdmsWoallottedflag(newWomTlWomst.getWomsAllottedflag());
			bdmTlMst.setBdmsReceiveddate(newWomTlWomst.getWomsAllotteddate());
			if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
			{	
				BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0);				
				bdmDetail.setBdanRemarks(newWomTlWomst.getWomsAllottedremarks());
				bdmDetail.setBdanStatus(newWomTlWomst.getWomsStatus());
				bdmDetail.setBdanErppoststatus(newWomTlWomst.getWomsStatus());				
				CommonMessage.debugMsg(bdmTlMst.getBdmsRemarks());
			}
		}*/
		//return null;
		return womTlWomstDao.updateAllocation(newWomTlWomst,oldWomTlWomst,woFormBean,bdmTlMst,abnTlAbnormality,plmTlGenmaintenance,mldTlMouldunloadmst);
	}
	public WomTlWomst updateCompletion(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception
	{
		CommonMessage.debugMsg("  7 Service Impl ");
		
		String validationsFor = "updateCompletion";
		CommonMessage.debugMsg(validationsFor);
		validations.validate(newWomTlWomst,"wocreation",validationsFor);
		validations.validate(woFormBean,"wocreation",validationsFor);
		fillValues(newWomTlWomst,oldWomTlWomst,woFormBean);		
		BdmTlMst bdmTlMst = new BdmTlMst();
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsStatus()))
		{
			if(newWomTlWomst.getWomsStatus().substring(0, 1).equals("P"))
				newWomTlWomst.setWomsStatus("P");
			else
				newWomTlWomst.setWomsStatus("C");
		}
			
		if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsOccurreddate()))
			newWomTlWomst.setWomsOccurreddate(oldWomTlWomst.getWomsOccurreddate());
		if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsReporteddate()))
			newWomTlWomst.setWomsReporteddate(oldWomTlWomst.getWomsReporteddate());
		if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsReportedby()))
			newWomTlWomst.setWomsReportedby(oldWomTlWomst.getWomsReportedby());
		if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsAssemblyid()))
			newWomTlWomst.setWomsAssemblyid(oldWomTlWomst.getWomsAssemblyid());
		
		if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsKeyid()) && oldWomTlWomst.getWomsActivitytype().equals("BD"))
		{
			fillBdValues(oldWomTlWomst,woFormBean,bdmTlMst);
			
			bdmTlMst.setBdmsStatus(newWomTlWomst.getWomsStatus());
			bdmTlMst.setBdmsWostartflag(newWomTlWomst.getWomsWorkstartflag());
			bdmTlMst.setBdmsWostarttime(newWomTlWomst.getWomsWorkstartdate());
			bdmTlMst.setBdmsWoendflag(newWomTlWomst.getWomsWorkendflag());
			bdmTlMst.setBdmsWoendtime(newWomTlWomst.getWomsWorkenddate());
			
			
			if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
			{	
				BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0);
				bdmDetail.setBdanActiontakenby(newWomTlWomst.getWomsDoneby());
				if(UIUtils.isValidKeyId(woFormBean.getWomsFinalaction()))
					bdmDetail.setBdanFinalaction(woFormBean.getWomsFinalaction());
				else
					bdmDetail.setBdanFinalaction("{}");
				if(UIUtils.isValidKeyId(woFormBean.getWomsCountermeasure()))
					bdmDetail.setBdanCountermeasure(woFormBean.getWomsCountermeasure());
				else
					bdmDetail.setBdanCountermeasure("{}");
				if(UIUtils.isValidKeyId(woFormBean.getWomsRootcause()))
					bdmDetail.setBdanRootcause(woFormBean.getWomsRootcause());
				else
					bdmDetail.setBdanRootcause("{}");				
				bdmDetail.setBdanRemarks(newWomTlWomst.getWomsRescheduleremarks());
				bdmDetail.setBdanStatus(newWomTlWomst.getWomsStatus());
				bdmDetail.setBdanErppoststatus(newWomTlWomst.getWomsStatus());			
				CommonMessage.debugMsg(bdmTlMst.getBdmsRemarks());
			}
		}
		return womTlWomstDao.updateCompletion(newWomTlWomst,oldWomTlWomst,woFormBean,bdmTlMst);
	}
	public List<String[]>  checkOverlap(String occuredDate,String prodStartDate,String woId,String actType)  throws Exception
	{
		return womTlWomstDao.checkOverlap(occuredDate, prodStartDate, woId,actType);
	}
	public WomTlWomst updateProdAcceptance(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean,List<String[]> overLapFlag )  throws Exception
	{
		CommonMessage.debugMsg("8 Service Impl ");
		
		String validationsFor = "updateProdAcceptance";
		CommonMessage.debugMsg(validationsFor);
		validations.validate(newWomTlWomst,"wocreation",validationsFor);
		//validations.validate(woFormBean,"wocreation",validationsFor);
		fillValues(newWomTlWomst,oldWomTlWomst,woFormBean);
		CommonMessage.debugMsg("Status : "+newWomTlWomst.getWomsStatus() + " Old -  "+oldWomTlWomst.getWomsStatus());
		//BdmTlMst bdmTlMst = new BdmTlMst();
		if(newWomTlWomst.getWomsStatus().substring(0, 1).equals("C"))
		{
			newWomTlWomst.setWomsStatus("P");
			newWomTlWomst.setWomsFinalstatus("PRODUCTION APPROVED");
		}
		else
		{
			newWomTlWomst.setWomsStatus("E");
			newWomTlWomst.setWomsFinalstatus("PRODUCTION APPROVED WITHOUT COMPLETION");
		}
		/*if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsActivityid()))
			bdmTlMst.setBdmsKeyid(oldWomTlWomst.getWomsActivityid());
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsProductionstartflag()))
				bdmTlMst.setBdmsWoprodaccepflag(newWomTlWomst.getWomsProductionstartflag());
		if(UIUtils.isValidKeyId(newWomTlWomst.getwomspro*/
		return womTlWomstDao.updateProdAcceptance(newWomTlWomst,oldWomTlWomst,woFormBean,overLapFlag);
		//return null;
	}
	public WomTlWomst delete(WomTlWomst womTlWomst) throws Exception
	{
		return womTlWomstDao.delete(womTlWomst);		
	}
	
	public WomsTlTaskmst createTaskList(List<WomsTlTaskmst> womsTlTaskmstList) throws Exception {
		String lineNo="";
		for(int i =0;i<womsTlTaskmstList.size();i++) {
			WomsTlTaskmst womsTlTaskmst = (WomsTlTaskmst)womsTlTaskmstList.get(i);
			fillValuesTaskmst(womsTlTaskmst);
			womsTlTaskmstList.set(i, womsTlTaskmst);
		}
		return womTlWorkorderMstDao.createTaskList(womsTlTaskmstList);
	}
	
	public List<String[]> getWorkOrderList(CommonFilter commonFilter)
	throws Exception {
		// TODO Auto-generated method stub
		return womTlWomstDao.getWorkOrderList(commonFilter);
	}
	
	public List<String[]> getSapEquipmentDetail(String empNo) throws Exception {
		return womTlWomstDao.getSapEquipmentDetail(empNo);
	}
	
	public List<String[]> getWorkOrder(CommonFilter commonFilter) throws Exception
	{
		return womTlWomstDao.getWorkOrder(commonFilter);
	}
	public WomTlWomst deleteWorkOrder(WomTlWomst womTlWomst)throws Exception 
	{
		return womTlWomstDao.delete(womTlWomst);
	}
	public WomTlWomst select(String keyid) throws Exception
	{
		//return womTlWomstDao.select(keyid);
		return bdmServiceApi.getWorkOrder(keyid);
		
		
	}
	
	public WomTlWorkorderMst selectNew(String keyid) throws Exception {
		return womTlWorkorderMstDao.select(keyid);
	}
	
	public List<ComboBox> getSapNotificationType(ComboFilter comboFilter) throws Exception {
		
		comboFilter.setCodeField("GMNT_NAME");
		comboFilter.setNameField("GMNT_CODE");
		comboFilter.setIdField("GMNT_KEYID");	
		comboFilter.setTableName("GEN_TL_MAINT_NOTIF_TYPE");
		StringBuffer sb = new StringBuffer();
		
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	public List<ComboBox> getSapOrderType(ComboFilter comboFilter,String cntrlKey) throws Exception {
		
		comboFilter.setCodeField("GMOT_NAME");
		comboFilter.setNameField("GMOT_CODE");
		comboFilter.setIdField("GMOT_KEYID");	
		comboFilter.setTableName("GEN_TL_MAINT_ORDER_TYPE");
		StringBuffer sb = new StringBuffer();
		if(UIUtils.isValidKeyId(cntrlKey)){
		 sb.append(" AND GMOT_KEYID IN(SELECT conf_ordertype_keyid FROM conf_tl_saporder_wbslink WHERE conf_controlkey='"+cntrlKey+"') ");
		 comboFilter.setCondSql(sb.toString());
		}
		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getAllSpares(ComboFilter comboFilter) throws Exception {
		
		comboFilter.setCodeField("SPRM_PARTNO");
		comboFilter.setNameField("SPRM_PARTNAME");
		comboFilter.setIdField("SPRM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SPARESMST);
		StringBuffer sb = new StringBuffer();
		
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	public List<ComboBox> getMould(String condSql,String active,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		
		comboFilter.setCodeField("MLDM_DESCRIPTION");
		comboFilter.setIdField("MLDM_MOULDID");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MOULDMST);
		StringBuffer sb = new StringBuffer();
		if(UIUtils.isValidKeyId(condSql))
		{
			
			sb.append("AND MLDM_MOULDID IN(SELECT MMLK_MOULDID FROM "+TableNames.TBL_GEN_TL_MOULDMACHINELINK);
			sb.append(" WHERE MMLK_MACHINEID = '"+condSql+"')");			
		}
		if(UIUtils.isValidKeyId(active))
			sb.append(" AND MLDM_MOULD_STATUS <> 'UNLOADED'");
		if(UIUtils.isValidKeyId(sb.toString()))
			comboFilter.setCondSql(sb.toString());
		return commonFilterDao.fillComboValues(comboFilter);					
	}
	public List<ComboBox> getAlarm(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("BALM_CODE");
		comboFilter.setIdField("BALM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_BDM_TL_ALARMMST);
		return commonFilterDao.fillComboValues(comboFilter);					
	}
	public List<ComboBox> getFailureType(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("FLTM_CODE");
		comboFilter.setNameField("FLTM_NAME");
		comboFilter.setIdField("FLTM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_BDM_TL_FAILURETYPEMST);
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getPhenomena(String condSql,ComboFilter comboFilter) throws Exception
	{
	
		//ComboFilter comboFilter = new ComboFilter();		
		comboFilter.setNameField("BPHM_PHENOMENANAME");
		comboFilter.setIdField("BPHM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_BDM_TL_PHENOMENAMST);
		if(UIUtils.isValidKeyId(condSql))
		{
			StringBuffer sb = new StringBuffer();
			sb.append(" AND BPHM_ASSEMBLYID = '"+condSql+"'");				
			sb.append(" UNION SELECT DISTINCT BPHM_KEYID id ,BPHM_PHENOMENANAME text  from "+TableNames.TBL_BDM_TL_PHENOMENAMST);
			sb.append(" where BPHM_PHENOMENANAME = 'NOT DEFINED'");
			comboFilter.setCondSql(sb.toString());
		}
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getCause(String condSql,String phenId,String assmId,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("BPCL_DISPLAYCODE");
		comboFilter.setIdField("BPCL_ORIGINALID");	
		condSql = "AND BPCL_ELEMENTTYPE = 'CAS'";
		if (UIUtils.isValidKeyId(phenId))
		{
			condSql = "AND INSTR(BPCL_PARENTID, '"+phenId+"')>0 ";
		}
		if (UIUtils.isValidKeyId(assmId))
		{
			condSql = "AND INSTR(BPCL_PARENTID, '"+assmId+"')>0 ";
		}
			comboFilter.setCondSql(condSql); 
			
		//comboFilter.setTableName(TableNames.TBL_BDM_TL_CAUSEMST);
		comboFilter.setTableName(TableNames.TBL_BDM_TL_PHNCAUSELINK);
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getSpare(String condSql,String assmId,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		
		comboFilter.setNameField("FNLN_DISPLAYCODE");
		comboFilter.setIdField("FNLN_ORIGINALID");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_FUNCTIONALLOCN);
		condSql = "AND FNLN_ELEMENTTYPE = 'SPR'";
		
		if (UIUtils.isValidKeyId(assmId))
			condSql += "AND INSTR(FNLN_PARENTID, '"+assmId+"')>0 ";
		
		comboFilter.setCondSql(condSql); 
		return commonFilterDao.fillComboValues(comboFilter);	
	}
	public List<ComboBox> getActivityCombo(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		
		comboFilter.setNameField("TEXT");
		comboFilter.setIdField("ID");	
		comboFilter.setTableName(TableNames.VIEW_MSRACTIVITY);
		comboFilter.setCondSql(condSql); 
		return womTlWomstDao.fillComboValues(comboFilter);	
	}
	
	
	public String getShift(ShiftBean shiftBean)
	{		
		return this.womTlWomstDao.getShift(shiftBean);		
	}
	public String getYYId(String refDocId)
	{
		return this.womTlWomstDao.getYYId(refDocId);
	}
	public String getRelatedFieldVal(String refDocId,String actType)
	{
		return this.womTlWomstDao.getRelatedFieldVal(refDocId,actType);
	}
	public String checkActivityConfig()
	{
		return this.womTlWomstDao.checkActivityConfig();
	}
	public List<String[]> checkPCSInsertEnable() throws Exception
	{
		return this.womTlWomstDao.checkPCSInsertEnable();
	}
	public String checkAssmMand() throws Exception
	{
		return this.womTlWomstDao.checkAssmMand();
	}
	public Workbook workOrderExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.womTlWomstDao.workOrderRpt(commonFilter,colmodel,rptFormat);
	}
	public Workbook workOrderViewExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.womTlWomstDao.workOrderRpt(commonFilter,colmodel,rptFormat);
	}

	private WomTlWorkorderMst fillValuesNew(WomTlWorkorderMst newWomTlWorkorderMst,WomTlWorkorderMst oldWomTlWorkorderMst,  WOFormBean woFormBean)
	{
		CommonMessage.debugMsg("  First Service Impl ");
		newWomTlWorkorderMst.setWomsActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();

		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsKeyid()) )
			newWomTlWorkorderMst.setWomsCreatedon(dateTime);
		else {
			/*if (oldWomTlWorkorderMst!=null)
				newWomTlWorkorderMst.setWomsCreatedon(oldWomTlWorkorderMst.getWomsCreatedon());*/
			
				newWomTlWorkorderMst.setWomsCreatedon(dateTime);
		}
		
		newWomTlWorkorderMst.setWomsModifiedon(dateTime);
		
		//CommonMessage.debugMsg( " bean Mode "  + woFormBean.getWoMode() );
		
		String reportedDate = "";//Constants.passNullDate;
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsReportedDate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsReportedtime()))
				reportedDate = newWomTlWorkorderMst.getWomsReportedDate() + " "+woFormBean.getWomsReportedtime();
			else
				reportedDate = newWomTlWorkorderMst.getWomsReportedDate() ;
		}		
	
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsCellid()))
			newWomTlWorkorderMst.setWomsCellid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsMachineid()))
			newWomTlWorkorderMst.setWomsMachineid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsWomscenterid()))
			newWomTlWorkorderMst.setWomsWomscenterid("WKCM0166");//CHANGED FOR TESTING SAP
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsCostcenterid()))
			newWomTlWorkorderMst.setWomsCostcenterid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsPlannergroupid()))
			newWomTlWorkorderMst.setWomsPlannergroupid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsWomsgroupid()))
			newWomTlWorkorderMst.setWomsWomsgroupid("{}");
		
		//if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsElementid()))
		//	newWomTlWorkorderMst.setWomsElementid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsFlid()))
			newWomTlWorkorderMst.setWomsFlid("{}");
		
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsOccurredDate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsOccuredtime()))
				newWomTlWorkorderMst.setWomsOccurredDate(newWomTlWorkorderMst.getWomsOccurredDate() + " "+woFormBean.getWomsOccuredtime());
		}
		else
			newWomTlWorkorderMst.setWomsOccurredDate(Constants.passNullDate);
		
		/*if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsShiftid()))
		{
			newWomTlWorkorderMst.setWomsShiftid(newWomTlWorkorderMst.getWomsShiftid());
		}
		else*/
			newWomTlWorkorderMst.setWomsShiftid("-");
		
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsShiftDate()))
		{
			newWomTlWorkorderMst.setWomsShiftDate(newWomTlWorkorderMst.getWomsShiftDate());
		}
		else			
			newWomTlWorkorderMst.setWomsShiftDate(Constants.passNullDate);
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsActivityType())){
			if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSapnotfnType())){
				newWomTlWorkorderMst.setWomsActivityType("-");
			}
			else{
				CommonMessage.debugMsg("inside act else");
				newWomTlWorkorderMst.setWomsActivityType(newWomTlWorkorderMst.getWomsSapnotfnType());
			}
		}
			
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsPriority()))
			newWomTlWorkorderMst.setWomsPriority("X");
		
		newWomTlWorkorderMst.setWomsReportedDate(reportedDate);
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsReportedBy()))
			newWomTlWorkorderMst.setWomsReportedBy(woFormBean.getWomsBookedby());
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsIsProdStopped()))
			newWomTlWorkorderMst.setWomsIsProdStopped("Y");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsMachineCondition()))
			newWomTlWorkorderMst.setWomsMachineCondition("S");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsActivityType()))
			newWomTlWorkorderMst.setWomsActivityType("X");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAlarmno()))
			newWomTlWorkorderMst.setWomsAlarmno("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsTradeid()))
			newWomTlWorkorderMst.setWomsTradeid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAssemblyid()))
			newWomTlWorkorderMst.setWomsAssemblyid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSubassemblyid()))
			newWomTlWorkorderMst.setWomsSubassemblyid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsFailuretypeid()))
			newWomTlWorkorderMst.setWomsFailuretypeid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsPartlocation()))
			newWomTlWorkorderMst.setWomsPartlocation("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSpareid()))
			newWomTlWorkorderMst.setWomsSpareid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsPhenomenaid()))
			newWomTlWorkorderMst.setWomsPhenomenaid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsCauseid()))
			newWomTlWorkorderMst.setWomsCauseid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsLocation()))
			newWomTlWorkorderMst.setWomsLocation("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsProblem()))
			newWomTlWorkorderMst.setWomsProblem("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsBookingremarks()))
			newWomTlWorkorderMst.setWomsBookingremarks("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsStatus()))
			newWomTlWorkorderMst.setWomsStatus("X");
		
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAcceptedDate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsAcceptedtime()))
				newWomTlWorkorderMst.setWomsAcceptedDate(newWomTlWorkorderMst.getWomsAcceptedDate() + " "+woFormBean.getWomsAcceptedtime());
		}
		else
			newWomTlWorkorderMst.setWomsAcceptedDate(Constants.passNullDate);
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAcceptedFlag()))
			newWomTlWorkorderMst.setWomsAcceptedFlag("N");
		
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsReqApprovedFlag()))
			newWomTlWorkorderMst.setWomsReqApprovedFlag("R");		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsReqApprovedBy()))
			newWomTlWorkorderMst.setWomsReqApprovedBy("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsReqApprovedDate()))
			newWomTlWorkorderMst.setWomsReqApprovedDate(Constants.passNullDate);
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsReqApprovedRemarks()))
			newWomTlWorkorderMst.setWomsReqApprovedRemarks("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAcceptedBy())) 
			if (oldWomTlWorkorderMst!=null && UIUtils.isValidKeyId(oldWomTlWorkorderMst.getWomsAcceptedBy()))
				newWomTlWorkorderMst.setWomsAcceptedBy(oldWomTlWorkorderMst.getWomsAcceptedBy());
			else
			newWomTlWorkorderMst.setWomsAcceptedBy(woFormBean.getWomsBookedby());
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAcceptedBy()))
			newWomTlWorkorderMst.setWomsAcceptedBy("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAcceptedFlag()))
			newWomTlWorkorderMst.setWomsAcceptedFlag("Y");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAcceptedRemarks()))
			newWomTlWorkorderMst.setWomsAcceptedRemarks(newWomTlWorkorderMst.getWomsBookingremarks());
			
		//if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsProductionapproval()))
			//newWomTlWorkorderMst.setWomsProductionApproval("N");
			
		//if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSafetypermitsrequried()))
			//newWomTlWorkorderMst.setWomsSafetypermitsrequried("N");
		//if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSafetypermitid()))
		//	newWomTlWorkorderMst.setWomsSafetypermitid("{}");
		//if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsRescheduleflag()))
			//newWomTlWorkorderMst.setWomsRescheduleflag("A");
		/*if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsRescheduledate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsRescheduletime()))
				newWomTlWorkorderMst.setWomsRescheduledate(newWomTlWorkorderMst.getWomsRescheduledate() + " "+woFormBean.getWomsRescheduletime());
		}
		else*/
			//newWomTlWorkorderMst.setWomsRescheduledate(reportedDate);
		
		//if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsRescheduleremarks()))
		//	newWomTlWorkorderMst.setWomsRescheduleremarks(newWomTlWorkorderMst.getWomsBookingremarks());
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAllottedFlag()))
			newWomTlWorkorderMst.setWomsAllottedFlag("N");
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAllottedDate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsAllottedtime()))
				newWomTlWorkorderMst.setWomsAllottedDate(newWomTlWorkorderMst.getWomsAllottedDate() + " "+woFormBean.getWomsAllottedtime());
		}
		else
			newWomTlWorkorderMst.setWomsAllottedDate(Constants.passNullDate);
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAllottedTo()))
			newWomTlWorkorderMst.setWomsAllottedTo("EMP00240");//CHANGED FOR TESTING SAP
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAllottedRemarks()))
			newWomTlWorkorderMst.setWomsAllottedRemarks("{}");
				
		if(newWomTlWorkorderMst.getWomsStatus().substring(0, 1).equals("P"))
		{
			if(oldWomTlWorkorderMst!=null && UIUtils.isValidKeyId(oldWomTlWorkorderMst.getWomsProductionStartFlag()))
				newWomTlWorkorderMst.setWomsProductionStartFlag(oldWomTlWorkorderMst.getWomsProductionStartFlag());	
			else
				newWomTlWorkorderMst.setWomsProductionStartFlag("N");
			if(oldWomTlWorkorderMst!=null &&  UIUtils.isValidKeyId(oldWomTlWorkorderMst.getWomsProductionStartDate()))
			{
				newWomTlWorkorderMst.setWomsProductionStartDate(oldWomTlWorkorderMst.getWomsProductionStartDate());
			}
			else
				newWomTlWorkorderMst.setWomsProductionStartDate(Constants.passNullDate);
		
			if(oldWomTlWorkorderMst!=null && UIUtils.isValidKeyId(oldWomTlWorkorderMst.getWomsProductionBy()))
				newWomTlWorkorderMst.setWomsProductionBy(oldWomTlWorkorderMst.getWomsProductionBy());
			else
				newWomTlWorkorderMst.setWomsProductionBy("{}");
			
			if(oldWomTlWorkorderMst!=null && UIUtils.isValidKeyId(oldWomTlWorkorderMst.getWomsProductionRemarks()))
				newWomTlWorkorderMst.setWomsProductionRemarks(oldWomTlWorkorderMst.getWomsProductionRemarks());
			else
				newWomTlWorkorderMst.setWomsProductionRemarks("{}");
		}
		else
		{
			if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsProductionStartFlag()))
				newWomTlWorkorderMst.setWomsProductionStartFlag("N");	
			if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsProductionStartDate()))
			{
				if(UIUtils.isValidKeyId(woFormBean.getWomsProductionstarttime()))
					newWomTlWorkorderMst.setWomsProductionStartDate(newWomTlWorkorderMst.getWomsProductionStartDate() + " "+woFormBean.getWomsProductionstarttime());
			}
			else
				newWomTlWorkorderMst.setWomsProductionStartDate(Constants.passNullDate);
		
			if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsProductionBy()))
				newWomTlWorkorderMst.setWomsProductionBy("{}");
			if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsProductionRemarks()))
				newWomTlWorkorderMst.setWomsProductionRemarks("{}");
		}
		
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsWomsstartDate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsWorkstarttime()))
				newWomTlWorkorderMst.setWomsWomsstartDate(newWomTlWorkorderMst.getWomsWomsstartDate() + " "+woFormBean.getWomsWorkstarttime());
		}
		else
		{
			newWomTlWorkorderMst.setWomsWomsstartDate(Constants.passNullDate);
		}
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsWomsendDate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsWorkendtime()))
				newWomTlWorkorderMst.setWomsWomsendDate(newWomTlWorkorderMst.getWomsWomsendDate() + " "+woFormBean.getWomsWorkendtime());
		}
		else
		{
			newWomTlWorkorderMst.setWomsWomsendDate(Constants.futureNullDate);
		}
		
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsWomsstartFlag()))
			newWomTlWorkorderMst.setWomsWomsstartFlag("N");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsWomsendFlag()))
			newWomTlWorkorderMst.setWomsWomsendFlag("N");
		
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsDoneBy()))
			newWomTlWorkorderMst.setWomsDoneBy("{}");
			
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsFinalActivitytype()))
			newWomTlWorkorderMst.setWomsFinalActivitytype("X");
		else
			newWomTlWorkorderMst.setWomsFinalActivitytype(newWomTlWorkorderMst.getWomsFinalActivitytype());
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsActivityid()))
			newWomTlWorkorderMst.setWomsActivityid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsProductionStartFlag()))
			newWomTlWorkorderMst.setWomsProductionStartFlag("N");	
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsProductionBy()))
			newWomTlWorkorderMst.setWomsProductionBy("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsProductionRemarks()))
			newWomTlWorkorderMst.setWomsProductionRemarks("{}");		
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsStandbyInfo()))
			newWomTlWorkorderMst.setWomsStandbyInfo("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsStandbyRemarks()))
			newWomTlWorkorderMst.setWomsStandbyRemarks("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsExtRepairFlag()))
			newWomTlWorkorderMst.setWomsExtRepairFlag("N");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsExtRepairid()))
			newWomTlWorkorderMst.setWomsExtRepairid("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsRepairRemarks()))
			newWomTlWorkorderMst.setWomsRepairRemarks("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsExtServiceFlag()))
			newWomTlWorkorderMst.setWomsExtServiceFlag("N");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsExtServiceid()))
			newWomTlWorkorderMst.setWomsExtServiceid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsRemarks()))
			newWomTlWorkorderMst.setWomsRemarks("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsRefdoctype()))
			newWomTlWorkorderMst.setWomsRefdoctype("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsRefdocid()))
			newWomTlWorkorderMst.setWomsRefdocid("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsProcessid()))
			newWomTlWorkorderMst.setWomsProcessid("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsPlannergroupid()))
			newWomTlWorkorderMst.setWomsPlannergroupid("-");//CHANGED FOR TESTING SAP
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSapnotfnFlag()))
			newWomTlWorkorderMst.setWomsSapnotfnFlag("-");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSapnotfnType()))
			newWomTlWorkorderMst.setWomsSapnotfnType("-");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSapnotfnNo()))
			newWomTlWorkorderMst.setWomsSapnotfnNo("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSapnotfnStatus()))
			newWomTlWorkorderMst.setWomsSapnotfnStatus("-");
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSaporderFlag()))
			newWomTlWorkorderMst.setWomsSaporderFlag("-");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSaporderType()))
			newWomTlWorkorderMst.setWomsSaporderType("-");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSaporderNo()))
			newWomTlWorkorderMst.setWomsSaporderNo("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSaporderStatus()))
			newWomTlWorkorderMst.setWomsSaporderStatus("-");
		
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsWbsElementid()))
			newWomTlWorkorderMst.setWomsWbsElementid("{}");
		
		//if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsRequiredStart()))
		//	newWomTlWorkorderMst.setWomsRequiredStart(Constants.passNullDate);
		
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsRequiredStart()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getRequiredStartTime()))
				newWomTlWorkorderMst.setWomsRequiredStart(newWomTlWorkorderMst.getWomsRequiredStart() + " "+woFormBean.getRequiredStartTime());
		}
		else
			newWomTlWorkorderMst.setWomsRequiredStart(Constants.passNullDate);
	
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsRequiredEnd()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getRequiredEndTime()))
				newWomTlWorkorderMst.setWomsRequiredEnd(newWomTlWorkorderMst.getWomsRequiredEnd() + " "+woFormBean.getRequiredEndTime());
		}
		else
			newWomTlWorkorderMst.setWomsRequiredEnd(Constants.futureNullDate);
		//if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsRequiredEnd()))
		//	newWomTlWorkorderMst.setWomsRequiredEnd(Constants.passNullDate);
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSapnotfnMessage()))
			newWomTlWorkorderMst.setWomsSapnotfnMessage("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSaporderMessage()))
			newWomTlWorkorderMst.setWomsSaporderMessage("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsYYrefno()))
			newWomTlWorkorderMst.setWomsYYrefno("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsFishbone_refno()))
			newWomTlWorkorderMst.setWomsFishbone_refno("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsSapOrderDate()))
			newWomTlWorkorderMst.setWomsSapOrderDate(newWomTlWorkorderMst.getWomsCreatedon());
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsProblemSeverity()))
			newWomTlWorkorderMst.setWomsProblemSeverity("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsStandbyEquipment()))
			newWomTlWorkorderMst.setWomsStandbyEquipment("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsImmediateAction()))
			newWomTlWorkorderMst.setWomsImmediateAction("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsRootCause()))
			newWomTlWorkorderMst.setWomsRootCause("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsCounterMeasure()))
			newWomTlWorkorderMst.setWomsCounterMeasure("{}");
		
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsExecutionRemarks()))
			newWomTlWorkorderMst.setWomsExecutionRemarks("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsClassificationid()))
			newWomTlWorkorderMst.setWomsClassificationid("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsAnalysedby()))
			newWomTlWorkorderMst.setWomsAnalysedby("{}");
		
		
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsCompletionDate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsCompleteiontime()))
				newWomTlWorkorderMst.setWomsCompletionDate(newWomTlWorkorderMst.getWomsCompletionDate() + " "+woFormBean.getWomsCompleteiontime());
		}
		else
			newWomTlWorkorderMst.setWomsCompletionDate(Constants.passNullDate);

		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsTechnicompDate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsTechnicomptime()))
				newWomTlWorkorderMst.setWomsTechnicompDate(newWomTlWorkorderMst.getWomsTechnicompDate() + " "+woFormBean.getWomsTechnicomptime());
		}
		else
			newWomTlWorkorderMst.setWomsTechnicompDate(Constants.passNullDate);

		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsBusicompDate()))
		{
			CommonMessage.debugMsg("woFormBean.getWomsBusiComptime()==="+woFormBean.getWomsBusiComptime());
			if(UIUtils.isValidKeyId(woFormBean.getWomsBusiComptime()))
				newWomTlWorkorderMst.setWomsBusicompDate(newWomTlWorkorderMst.getWomsBusicompDate() + " "+woFormBean.getWomsBusiComptime());
		}
		else
			newWomTlWorkorderMst.setWomsBusicompDate(Constants.passNullDate);

		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsControlKey()))
			newWomTlWorkorderMst.setWomsControlKey("CK01");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsTemp2()))
			newWomTlWorkorderMst.setWomsTemp2("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsTemp3()))
			newWomTlWorkorderMst.setWomsTemp3("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsTemp4()))
			newWomTlWorkorderMst.setWomsTemp4("{}");
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsTemp5()))
			newWomTlWorkorderMst.setWomsTemp5("{}");

		
		CommonMessage.debugMsg(" flid.. " +newWomTlWorkorderMst.getWomsFlid());
		
		if(!UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsFlid()))
			newWomTlWorkorderMst.setWomsFlid("{}");
		
		if(UIUtils.isValidKeyId(newWomTlWorkorderMst.getWomsCreatedby()))
			newWomTlWorkorderMst.setWomsModifiedby(newWomTlWorkorderMst.getWomsCreatedby());
		else
			newWomTlWorkorderMst.setWomsModifiedby("{}");
		
		
		return newWomTlWorkorderMst;
		
	}
	
	private WomTlWomst fillValues(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean)
	{
		CommonMessage.debugMsg("  First Service Impl ");
		newWomTlWomst.setWomsActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();

		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsKeyid()) )
			newWomTlWomst.setWomsCreatedon(dateTime);
		else
			newWomTlWomst.setWomsCreatedon(oldWomTlWomst.getWomsCreatedon());
		
		newWomTlWomst.setWomsModifiedon(dateTime);
		
		//CommonMessage.debugMsg( " bean Mode "  + woFormBean.getWoMode() );
		
		String reportedDate = Constants.passNullDate;
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsReporteddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsReportedtime()))
				reportedDate = newWomTlWomst.getWomsReporteddate() + " "+woFormBean.getWomsReportedtime();
		}		
		
	
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsFactoryid()))
			newWomTlWomst.setWomsFactoryid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsSectionid()))
			newWomTlWomst.setWomsSectionid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsCellid()))
			newWomTlWomst.setWomsCellid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsMachineid()))
			newWomTlWomst.setWomsMachineid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsWorkcenterid()))
			newWomTlWomst.setWomsWorkcenterid("WKCM0166");//CHANGED FOR TESTING SAP
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsCostcenterid()))
			newWomTlWomst.setWomsCostcenterid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsElementid()))
			newWomTlWomst.setWomsElementid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsFlid()))
			newWomTlWomst.setWomsFlid("{}");
		
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsOccurreddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsOccuredtime()))
				newWomTlWomst.setWomsOccurreddate(newWomTlWomst.getWomsOccurreddate() + " "+woFormBean.getWomsOccuredtime());
		}
		else
			newWomTlWomst.setWomsOccurreddate(Constants.passNullDate);
		
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsShiftid()))
		{
			newWomTlWomst.setWomsShiftid(newWomTlWomst.getWomsShiftid());
		}
		else
			newWomTlWomst.setWomsShiftid("{}");
		
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsShiftdate()))
		{
			newWomTlWomst.setWomsShiftdate(newWomTlWomst.getWomsShiftdate());
		}
		else
			newWomTlWomst.setWomsShiftdate("{}");
		
	
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsPriority()))
			newWomTlWomst.setWomsPriority("X");
		
		newWomTlWomst.setWomsReporteddate(reportedDate);
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsReportedby()))
			newWomTlWomst.setWomsReportedby(woFormBean.getWomsBookedby());
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsProductionstop()))
			newWomTlWomst.setWomsProductionstop("y");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsMachinecondition()))
			newWomTlWomst.setWomsMachinecondition("S");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsActivitytype()))
			newWomTlWomst.setWomsActivitytype("X");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsAlarmno()))
			newWomTlWomst.setWomsAlarmno("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsTradeid()))
			newWomTlWomst.setWomsTradeid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsAssemblyid()))
			newWomTlWomst.setWomsAssemblyid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsSubassemblyid()))
			newWomTlWomst.setWomsSubassemblyid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsFailuretypeid()))
			newWomTlWomst.setWomsFailuretypeid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsPartlocation()))
			newWomTlWomst.setWomsPartlocation("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsSpareid()))
			newWomTlWomst.setWomsSpareid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsPhenomenaid()))
			newWomTlWomst.setWomsPhenomenaid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsCauseid()))
			newWomTlWomst.setWomsCauseid("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsLocation()))
			newWomTlWomst.setWomsLocation("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsProblem()))
			newWomTlWomst.setWomsProblem("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsBookingremarks()))
			newWomTlWomst.setWomsBookingremarks("{}");
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsStatus()))
			newWomTlWomst.setWomsStatus("X");
		
		/*if(UIUtils.isValidKeyId(newWomTlWomst.getWomsAccepteddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsAcceptedtime()))
				newWomTlWomst.setWomsAccepteddate(newWomTlWomst.getWomsAccepteddate() + " "+woFormBean.getWomsAcceptedtime());
		}
		else*/
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsAcceptedflag()))
			newWomTlWomst.setWomsAcceptedflag("N");
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsAccepteddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsAcceptedtime()))
				newWomTlWomst.setWomsAccepteddate(newWomTlWomst.getWomsAccepteddate() + " "+woFormBean.getWomsAcceptedtime());
		}
		else
			newWomTlWomst.setWomsAccepteddate(Constants.passNullDate);
			//newWomTlWomst.setWomsAccepteddate(reportedDate);
	
		//if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsAcceptedby()))
			newWomTlWomst.setWomsAcceptedby(woFormBean.getWomsBookedby());
		//if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsAcceptedflag()))
			//newWomTlWomst.setWomsAcceptedflag("Y");
		//if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsAcceptedremarks()))
			newWomTlWomst.setWomsAcceptedremarks(newWomTlWomst.getWomsBookingremarks());
			
		//if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsProductionapproval()))
			newWomTlWomst.setWomsProductionapproval("N");
			
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsSafetypermitsrequried()))
			newWomTlWomst.setWomsSafetypermitsrequried("N");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsSafetypermitid()))
			newWomTlWomst.setWomsSafetypermitid("{}");
		//if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRescheduleflag()))
			newWomTlWomst.setWomsRescheduleflag("A");
		/*if(UIUtils.isValidKeyId(newWomTlWomst.getWomsRescheduledate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsRescheduletime()))
				newWomTlWomst.setWomsRescheduledate(newWomTlWomst.getWomsRescheduledate() + " "+woFormBean.getWomsRescheduletime());
		}
		else*/
			newWomTlWomst.setWomsRescheduledate(reportedDate);
		
		//if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRescheduleremarks()))
			newWomTlWomst.setWomsRescheduleremarks(newWomTlWomst.getWomsBookingremarks());
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsAllottedflag()))
			newWomTlWomst.setWomsAllottedflag("N");
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsAllotteddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsAllottedtime()))
				newWomTlWomst.setWomsAllotteddate(newWomTlWomst.getWomsAllotteddate() + " "+woFormBean.getWomsAllottedtime());
		}
		else
			newWomTlWomst.setWomsAllotteddate(Constants.passNullDate);
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsAllottedto()))
			newWomTlWomst.setWomsAllottedto("EMP00240");//CHANGED FOR TESTING SAP
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsAllottedremarks()))
			newWomTlWomst.setWomsAllottedremarks("{}");
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsProposedstartdate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsProposedstarttime()))
				newWomTlWomst.setWomsProposedstartdate(newWomTlWomst.getWomsProposedstartdate() + " "+woFormBean.getWomsProposedstarttime());
		}
		else
			newWomTlWomst.setWomsProposedstartdate(Constants.passNullDate + " "+"00:00");
		
		CommonMessage.debugMsg("Proposed Start Date : "+newWomTlWomst.getWomsProposedstartdate());
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsProposedenddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsProposedendtime()))
				newWomTlWomst.setWomsProposedenddate(newWomTlWomst.getWomsProposedenddate() + " "+woFormBean.getWomsProposedendtime());
		}
		else
			newWomTlWomst.setWomsProposedenddate(Constants.futureNullDate  + " "+"00:00");	
		CommonMessage.debugMsg("Proposed End Date : "+newWomTlWomst.getWomsProposedenddate());
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsProposedstflag()))
			newWomTlWomst.setWomsProposedstflag("N");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsProposedendflag()))
			newWomTlWomst.setWomsProposedendflag("N");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsProposeddtacceptflag()))
			newWomTlWomst.setWomsProposeddtacceptflag("X");
		
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsReschedulestartdate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsReschedulestarttime()))
				newWomTlWomst.setWomsReschedulestartdate(newWomTlWomst.getWomsReschedulestartdate() + " "+woFormBean.getWomsReschedulestarttime());
		}
		else
			newWomTlWomst.setWomsReschedulestartdate(Constants.passNullDate  + " "+"00:00");
		
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsRescheduleenddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsRescheduleendtime()))
				newWomTlWomst.setWomsRescheduleenddate(newWomTlWomst.getWomsRescheduleenddate() + " "+woFormBean.getWomsRescheduleendtime());
		}
		else
			newWomTlWomst.setWomsRescheduleenddate(Constants.futureNullDate  + " "+"00:00");		
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRescheduledstflag()))
			newWomTlWomst.setWomsRescheduledstflag("N");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRescheduledendflag()))
			newWomTlWomst.setWomsRescheduledendflag("N");		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRescheduledremarks()))
			newWomTlWomst.setWomsRescheduledremarks("{}");
		//if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRescheduleby()))
			newWomTlWomst.setWomsRescheduleby("{}");
			
		if(newWomTlWomst.getWomsStatus().substring(0, 1).equals("P"))
		{
			if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsProductionstartflag()))
				newWomTlWomst.setWomsProductionstartflag(oldWomTlWomst.getWomsProductionstartflag());	
			else
				newWomTlWomst.setWomsProductionstartflag("N");
			if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsProductionstartdate()))
			{
				newWomTlWomst.setWomsProductionstartdate(oldWomTlWomst.getWomsProductionstartdate());
			}
			else
				newWomTlWomst.setWomsProductionstartdate(Constants.passNullDate);
		
			if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsProductionby()))
				newWomTlWomst.setWomsProductionby(oldWomTlWomst.getWomsProductionby());
			else
				newWomTlWomst.setWomsProductionby("{}");
			
			if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsProductionremarks()))
				newWomTlWomst.setWomsProductionremarks(oldWomTlWomst.getWomsProductionremarks());
			else
				newWomTlWomst.setWomsProductionremarks("{}");
		}
		else
		{
			if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsProductionstartflag()))
				newWomTlWomst.setWomsProductionstartflag("N");	
			if(UIUtils.isValidKeyId(newWomTlWomst.getWomsProductionstartdate()))
			{
				if(UIUtils.isValidKeyId(woFormBean.getWomsProductionstarttime()))
					newWomTlWomst.setWomsProductionstartdate(newWomTlWomst.getWomsProductionstartdate() + " "+woFormBean.getWomsProductionstarttime());
			}
			else
				newWomTlWomst.setWomsProductionstartdate(Constants.passNullDate);
		
			if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsProductionby()))
				newWomTlWomst.setWomsProductionby("{}");
			if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsProductionremarks()))
				newWomTlWomst.setWomsProductionremarks("{}");
		}
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsWorkstartdate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsWorkstarttime()))
				newWomTlWomst.setWomsWorkstartdate(newWomTlWomst.getWomsWorkstartdate() + " "+woFormBean.getWomsWorkstarttime());
		}
		else
		{
			newWomTlWomst.setWomsWorkstartdate(Constants.passNullDate);
		}
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsWorkenddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsWorkendtime()))
				newWomTlWomst.setWomsWorkenddate(newWomTlWomst.getWomsWorkenddate() + " "+woFormBean.getWomsWorkendtime());
		}
		else
		{
			newWomTlWomst.setWomsWorkenddate(Constants.futureNullDate);
		}
		
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsWorkstartflag()))
			newWomTlWomst.setWomsWorkstartflag("N");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsWorkendflag()))
			newWomTlWomst.setWomsWorkendflag("N");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsDoneby()))
			newWomTlWomst.setWomsDoneby("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsFinalactivitytype()))
			newWomTlWomst.setWomsFinalactivitytype("X");
		else
			newWomTlWomst.setWomsFinalactivitytype(newWomTlWomst.getWomsActivitytype());
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsActivityid()))
			newWomTlWomst.setWomsActivityid("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsWoapprovalby()))
			newWomTlWomst.setWomsWoapprovalby("{}");
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsWoapprovaldate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsWoapprovaltime()))
				newWomTlWomst.setWomsWoapprovaldate(newWomTlWomst.getWomsWoapprovaldate() + " "+woFormBean.getWomsWoapprovaltime());
		}
		else
		{
			newWomTlWomst.setWomsWoapprovaldate(Constants.passNullDate);
		}
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsWoapprovalflag()))
			newWomTlWomst.setWomsWoapprovalflag("N");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsMachinereleaseflag()))
			newWomTlWomst.setWomsMachinereleaseflag("N");
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsMachinereleaseddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsMachinereleasetime()))
				newWomTlWomst.setWomsMachinereleaseddate(newWomTlWomst.getWomsMachinereleaseddate() + " "+woFormBean.getWomsMachinereleasetime());
		}
		else
		{
			newWomTlWomst.setWomsMachinereleaseddate(Constants.passNullDate);
		}
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsMachinereleaseby()))
			newWomTlWomst.setWomsMachinereleaseby("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsIntorextequip()))
			newWomTlWomst.setWomsIntorextequip("N");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsIntorextequipdesc()))
			newWomTlWomst.setWomsIntorextequipdesc("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsStandbyadditionalinfo()))
			newWomTlWomst.setWomsStandbyadditionalinfo("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsStandbyremarks()))
			newWomTlWomst.setWomsStandbyremarks("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsSentforrepairflag()))
			newWomTlWomst.setWomsSentforrepairflag("X");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsSentrepairid()))
			newWomTlWomst.setWomsSentrepairid("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsSentrepairid()))
			newWomTlWomst.setWomsSentrepairid("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsSentto()))
			newWomTlWomst.setWomsSentto("{}");
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsExceptedreturndate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsExceptedreturntime()))
				newWomTlWomst.setWomsExceptedreturndate(newWomTlWomst.getWomsExceptedreturndate() + " "+woFormBean.getWomsExceptedreturntime());
		}
		else
			newWomTlWomst.setWomsExceptedreturndate(Constants.passNullDate);
	
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRepairremarks()))
			newWomTlWomst.setWomsRepairremarks("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRemarks()))
			newWomTlWomst.setWomsRemarks("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsJobopeningid()))
			newWomTlWomst.setWomsJobopeningid("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsFinalstatus()))
			newWomTlWomst.setWomsFinalstatus("{}");		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsOrderno()))
			newWomTlWomst.setWomsOrderno("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRequestapproved()))
		{
			//if(!UIUtils.isValidKeyId(woFormBean.getWomsRequestcancel()))
				newWomTlWomst.setWomsRequestapproved("A");
			//else
				//newWomTlWomst.setWomsRequestapproved(woFormBean.getWomsRequestcancel());
		}
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRequestapprovedby()))
		{
			if(!UIUtils.isValidKeyId(woFormBean.getWomsBookedby()))
			{
				newWomTlWomst.setWomsRequestapprovedby(woFormBean.getWomsBookedby());
			}
			else
				newWomTlWomst.setWomsRequestapprovedby("{}");
		}
			
		//if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRequestapprovremarks()))
			newWomTlWomst.setWomsRequestapprovremarks(newWomTlWomst.getWomsBookingremarks());
			
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsSafetypermitcompleted()))
			newWomTlWomst.setWomsSafetypermitcompleted("X");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsSafetypermitapproved()))
			newWomTlWomst.setWomsSafetypermitapproved("X");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsSafetypermitsignoff()))
			newWomTlWomst.setWomsSafetypermitsignoff("X");
	/*	if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsPermitapproved()))
			newWomTlWomst.setWomsPermitapproved("X");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsPermitcompleted()))
			newWomTlWomst.setWomsPermitcompleted("X");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsPermitsignoff()))
			newWomTlWomst.setWomsPermitsignoff("X");*/
		/*if(UIUtils.isValidKeyId(newWomTlWomst.getWomsRequestapproveddate()))
		{
			if(UIUtils.isValidKeyId(woFormBean.getWomsApprovaltime()))
				newWomTlWomst.setWomsRequestapproveddate(newWomTlWomst.getWomsRequestapproveddate() + " "+woFormBean.getWomsApprovaltime());
		}
		else*/
			newWomTlWomst.setWomsRequestapproveddate(reportedDate);
		
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsMaintpriority()))
			newWomTlWomst.setWomsMaintpriority("0");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsAllottedsource()))
			newWomTlWomst.setWomsAllottedsource("X");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsAllottedsupplier()))
			newWomTlWomst.setWomsAllottedsupplier("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsDirectentry()))
			newWomTlWomst.setWomsDirectentry("Y");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRelatedto()))
			newWomTlWomst.setWomsRelatedto("X");
		if(newWomTlWomst.getWomsActivitytype().equals("AB"))
		{
			if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsLoss()))
			{
				newWomTlWomst.setWomsLoss("{}");
				newWomTlWomst.setWomsDirectentry("N");
			}
			else
			{
				if(newWomTlWomst.getWomsLoss().equals("U"))
					newWomTlWomst.setWomsLoss("UNPLANNED MAINTENANCE");
				else if(newWomTlWomst.getWomsLoss().equals("J"))
					newWomTlWomst.setWomsLoss("JH TAG REMOVAL");
				else if(newWomTlWomst.getWomsLoss().equals("M"))
					newWomTlWomst.setWomsLoss("M AND A");
				newWomTlWomst.setWomsDirectentry("Y");
			}
		}
		else
		{
			if(newWomTlWomst.getWomsFinalactivitytype().equals("AB"))
			{
				if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsLoss()))
				{
					newWomTlWomst.setWomsLoss("{}");
					newWomTlWomst.setWomsDirectentry("N");
				}
				else
				{
					if(newWomTlWomst.getWomsLoss().equals("U"))
						newWomTlWomst.setWomsLoss("UNPLANNED MAINTENANCE");
					else if(newWomTlWomst.getWomsLoss().equals("J"))
						newWomTlWomst.setWomsLoss("JH TAG REMOVAL");
					else if(newWomTlWomst.getWomsLoss().equals("M"))
						newWomTlWomst.setWomsLoss("M AND A");
					newWomTlWomst.setWomsDirectentry("Y");
				}
			}
			else
				newWomTlWomst.setWomsLoss("{}");	
		}
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsMouldid()))
			newWomTlWomst.setWomsMouldid("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsNumofactivities()))
			newWomTlWomst.setWomsNumofactivities("0");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsPwdmwono()))
			newWomTlWomst.setWomsPwdmwono("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRefdoctype()))
			newWomTlWomst.setWomsRefdoctype("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRefdocid()))
			newWomTlWomst.setWomsRefdocid("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsProcessId()))
			newWomTlWomst.setWomsProcessId("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRequiredstart()))
			newWomTlWomst.setWomsRequiredstart("-");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsRequiredend()))
			newWomTlWomst.setWomsRequiredend("-");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsDepartmentid()))
			newWomTlWomst.setWomsDepartmentid("-");//CHANGED FOR TESTING SAP
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsPlannergroup()))
			newWomTlWomst.setWomsPlannergroup("-");//CHANGED FOR TESTING SAP
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsTempfield1()))
			newWomTlWomst.setWomsTempfield1("{}");
		CommonMessage.debugMsg(" flid.. " +newWomTlWomst.getWomsFlid());
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsElementid()))
			newWomTlWomst.setWomsElementid("{}");
		if(!UIUtils.isValidKeyId(newWomTlWomst.getWomsFlid()))
			newWomTlWomst.setWomsFlid("{}");
		
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsCreatedby()))
			newWomTlWomst.setWomsModifiedby(newWomTlWomst.getWomsCreatedby());
		else
			newWomTlWomst.setWomsModifiedby("{}");
		
		
		return newWomTlWomst;
		
	}
	private BdmTlMst fillBdValues(WomTlWomst womTlWomst,WOFormBean woFormBean,BdmTlMst bdmTlMst) {
		
		
		bdmTlMst.setBdmsEntrydate(womTlWomst.getWomsShiftdate());
		bdmTlMst.setBdmsShiftid(womTlWomst.getWomsShiftid());
		bdmTlMst.setBdmsFactoryid(womTlWomst.getWomsFactoryid());
		bdmTlMst.setBdmsSectionid(womTlWomst.getWomsSectionid());
		bdmTlMst.setBdmsCellid(womTlWomst.getWomsCellid());
		bdmTlMst.setBdmsMachineid(womTlWomst.getWomsMachineid());
		if(UIUtils.isValidKeyId(womTlWomst.getWomsAssemblyid()))
			bdmTlMst.setBdmsAssemblyid(womTlWomst.getWomsAssemblyid());
		else
			bdmTlMst.setBdmsAssemblyid("{}");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsPartlocation()))
			bdmTlMst.setBdmsPartlocationid(womTlWomst.getWomsPartlocation());
		else
			bdmTlMst.setBdmsPartlocationid("{}");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsAlarmno()))
			bdmTlMst.setBdmsAlarmdescription(womTlWomst.getWomsAlarmno());
		else
			bdmTlMst.setBdmsAlarmdescription("{}");		
		CommonMessage.debugMsg("womTlWomst.getWomsProductionstop()  :"+womTlWomst.getWomsProductionstop());
		if("Y".equals(womTlWomst.getWomsProductionstop()))
			bdmTlMst.setBdmsProductionstop("Y");
		else
			bdmTlMst.setBdmsProductionstop("N");
		bdmTlMst.setBdmsBdtype("E");
		
		bdmTlMst.setBdmsReporteddate(womTlWomst.getWomsOccurreddate());
		bdmTlMst.setBdmsReceiveddate(womTlWomst.getWomsReporteddate());
		bdmTlMst.setBdmsWostarttime(womTlWomst.getWomsReporteddate());
		bdmTlMst.setBdmsWoendtime(womTlWomst.getWomsReporteddate());
		bdmTlMst.setBdmsBreaktime("0");
		bdmTlMst.setBdmsActualworktime("0");
		bdmTlMst.setBdmsDowntime("0");
		bdmTlMst.setBdmsProdaccepdate(womTlWomst.getWomsReporteddate());
		bdmTlMst.setBdmsBookedphenomena("{}");
		bdmTlMst.setBdmsPhenomenadescription("{}");
		bdmTlMst.setBdmsBookedcause("{}");
		bdmTlMst.setBdmsIsstandby("N");
		bdmTlMst.setBdmsStandbyequipment("{}");
		bdmTlMst.setBdmsBreakdowntime("0");
		
		bdmTlMst.setBdmsImmediateaction("-");
		bdmTlMst.setBdmsTempfield3("-");
		bdmTlMst.setBdmsTempfield4("-");
		bdmTlMst.setBdmsTempfield5("-");
		bdmTlMst.setBdmsTempfield6("-");
		bdmTlMst.setBdmsTempfield7("-");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsPhenomenaid()))
			bdmTlMst.setBdmsFinalphenomena(womTlWomst.getWomsPhenomenaid());
		else
			bdmTlMst.setBdmsFinalphenomena("{}");	
		if(UIUtils.isValidKeyId(womTlWomst.getWomsCauseid()))
			bdmTlMst.setBdmsFinalcause(womTlWomst.getWomsCauseid());
		else
			bdmTlMst.setBdmsFinalcause("{}");	
		if(UIUtils.isValidKeyId(womTlWomst.getWomsTradeid()))
			bdmTlMst.setBdmsBookedtrade(womTlWomst.getWomsTradeid());
		else
			bdmTlMst.setBdmsBookedtrade("{}");	
		if(UIUtils.isValidKeyId(womTlWomst.getWomsElementid()))
			bdmTlMst.setBdmsElementid(bdmTlMst.getBdmsElementid());
		else
			bdmTlMst.setBdmsElementid("{}");
			
		if(UIUtils.isValidKeyId(womTlWomst.getWomsFlid()))
			bdmTlMst.setBdmsFlid(womTlWomst.getWomsFlid());
		else
			bdmTlMst.setBdmsFlid("{}");
			
		if(UIUtils.isValidKeyId(womTlWomst.getWomsTradeid()))
			bdmTlMst.setBdmsFinaltrade(womTlWomst.getWomsTradeid());
		else
			bdmTlMst.setBdmsFinaltrade("{}");	
		if(UIUtils.isValidKeyId(womTlWomst.getWomsProblem()))
			bdmTlMst.setBdmsProblemdescription(womTlWomst.getWomsProblem());
		else
			bdmTlMst.setBdmsProblemdescription("{}");	
		
		bdmTlMst.setBdmsIsbdlocked("X");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsStatus()))
			bdmTlMst.setBdmsStatus(womTlWomst.getWomsStatus());
		else
			bdmTlMst.setBdmsStatus("X");	
		
		bdmTlMst.setBdmsShiftincharge("{}");
		if(UIUtils.isValidKeyId(woFormBean.getWomsBookedby()))
			bdmTlMst.setBdmsBookedby(woFormBean.getWomsBookedby());
		else
			bdmTlMst.setBdmsBookedby("{}");	
		//if(UIUtils.isValidKeyId(womTlWomst.getWomsBookingremarks()))
			//bdmTlMst.setBdmsRemarks(womTlWomst.getWomsBookingremarks());
		//else
			bdmTlMst.setBdmsRemarks("{}");	
		
		bdmTlMst.setBdmsBookingtype("ONL");
		
		bdmTlMst.setBdmsBdrelatedto("XXX");

		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsKeyid()))
			bdmTlMst.setBdmsWno(womTlWomst.getWomsKeyid());
		else
			bdmTlMst.setBdmsWno("{}");	
		if(UIUtils.isValidKeyId(womTlWomst.getWomsSpareid()))
			bdmTlMst.setBdmsSpareid(womTlWomst.getWomsSpareid());
		else
			bdmTlMst.setBdmsSpareid("{}");	
		if(UIUtils.isValidKeyId(womTlWomst.getWomsPriority()))
			bdmTlMst.setBdmsPriority(womTlWomst.getWomsPriority());
		else
			bdmTlMst.setBdmsPriority("0");	
		if(UIUtils.isValidKeyId(womTlWomst.getWomsAllottedflag()))
			bdmTlMst.setBdmsWoallottedflag(womTlWomst.getWomsAllottedflag());
		else
			bdmTlMst.setBdmsWoallottedflag("X");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsWorkstartflag()))
			bdmTlMst.setBdmsWostartflag(womTlWomst.getWomsWorkstartflag());
		else
			bdmTlMst.setBdmsWostartflag("X");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsWorkendflag()))
			bdmTlMst.setBdmsWoendflag(womTlWomst.getWomsWorkendflag());
		else
			bdmTlMst.setBdmsWoendflag("X");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsProductionstartflag()))
			bdmTlMst.setBdmsWoprodaccepflag(womTlWomst.getWomsProductionstartflag());
		else
			bdmTlMst.setBdmsWoprodaccepflag("X");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsSubassemblyid()))
			bdmTlMst.setBdmsSubassemblyid(womTlWomst.getWomsSubassemblyid());
		else
			bdmTlMst.setBdmsSubassemblyid("{}");
		//if(UIUtils.isValidKeyId(womTlWomst.getWomsStandbyadditionalinfo()))
			
		if(UIUtils.isValidKeyId(womTlWomst.getWomsProcessId()))
			bdmTlMst.setBdmsProcessid(womTlWomst.getWomsProcessId());
		else
			bdmTlMst.setBdmsProcessid("{}");
		
		bdmTlMst.setBdmsRepeatedbdflag("N");
		bdmTlMst.setBdmsRepeatedbdno("{}");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsRelatedto()))
			bdmTlMst.setBdmsRelatedto(womTlWomst.getWomsRelatedto());
		else
			bdmTlMst.setBdmsRelatedto("{}");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMouldid()))
			bdmTlMst.setBdmsMould(womTlWomst.getWomsMouldid());
		else
			bdmTlMst.setBdmsMould("{}");		
		bdmTlMst.setBdmsActive("Y");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsCreatedby()))
			bdmTlMst.setBdmsCreatedby(womTlWomst.getWomsCreatedby());
		else
			bdmTlMst.setBdmsCreatedby("{}");
		
		bdmTlMst.setBdmsCreatedon(womTlWomst.getWomsCreatedon());
		bdmTlMst.setBdmsModifiedon(womTlWomst.getWomsModifiedon());
		bdmTlMst.setBdmDetail(detailBDDetailFillValues(bdmTlMst,woFormBean,womTlWomst));
		
		
		return bdmTlMst;
	
	}
	private List<BdmTlDtl> detailBDDetailFillValues(BdmTlMst bdmTlMst,WOFormBean woFormBean,WomTlWomst womTlWomst) 
	{
		
		BdmTlDtl bdmTlDtls = new BdmTlDtl();
		List<BdmTlDtl> bdmTlDtlList = new ArrayList<BdmTlDtl>();
			if(UIUtils.isValidKeyId(womTlWomst.getWomsPhenomenaid()))
				bdmTlDtls.setBdanFinalphenomena(womTlWomst.getWomsPhenomenaid());
			else
				bdmTlDtls.setBdanFinalphenomena("{}");
			if(UIUtils.isValidKeyId(womTlWomst.getWomsCauseid()))
				bdmTlDtls.setBdanFinalcause(womTlWomst.getWomsCauseid());
			else
				bdmTlDtls.setBdanFinalcause("{}");
			
			bdmTlDtls.setBdanFinalaction("{}");
			if(UIUtils.isValidKeyId(womTlWomst.getWomsTradeid()))
				bdmTlDtls.setBdanTradeid(womTlWomst.getWomsTradeid());
			else
				bdmTlDtls.setBdanTradeid("{}");
			
			bdmTlDtls.setBdanCountermeasure("{}");			
			bdmTlDtls.setBdanWwrequired("N");
			bdmTlDtls.setBdanWwno("{}");
			bdmTlDtls.setBdanRootcause("{}");
			bdmTlDtls.setBdanPreventivemeasure("{}");
			bdmTlDtls.setBdanRootcauseid("{}");
			bdmTlDtls.setBdanCountermeasureid("{}");
			bdmTlDtls.setBdanPreventivemeasureid("{}");
			bdmTlDtls.setBdanBreakdowntime("0");
			bdmTlDtls.setBdanWorktime("0");
			
				bdmTlDtls.setBdanClassificationid("{}");
			
			bdmTlDtls.setBdanCategoryid("{}");
			bdmTlDtls.setBdanIssparesreplaced("N");
			if(UIUtils.isValidKeyId(womTlWomst.getWomsAlarmno()))
				bdmTlDtls.setBdanAlarmno(womTlWomst.getWomsAlarmno());
			else
				bdmTlDtls.setBdanAlarmno("{}");
			
			bdmTlDtls.setBdanManpowercost("0");
			bdmTlDtls.setBdanContractorcost("0");
			bdmTlDtls.setBdanSparescost("0");
			bdmTlDtls.setBdanOthercost("0");
			if(UIUtils.isValidKeyId(womTlWomst.getWomsStatus()))
				bdmTlDtls.setBdanStatus(womTlWomst.getWomsStatus());
			else
				bdmTlDtls.setBdanStatus("X");
			//if(UIUtils.isValidKeyId(womTlWomst.getWomsBookingremarks()))
			//	bdmTlDtls.setBdanRemarks(womTlWomst.getWomsBookingremarks());
			//else
				bdmTlDtls.setBdanRemarks("{}");
			if(UIUtils.isValidKeyId(womTlWomst.getWomsRequestapprovedby()))
				bdmTlDtls.setBdanActiontakenby(womTlWomst.getWomsRequestapprovedby());
			else
				bdmTlDtls.setBdanActiontakenby("{}");
			if(UIUtils.isValidKeyId(woFormBean.getWomsBookedby()))
				bdmTlDtls.setBdanCompletedby(woFormBean.getWomsBookedby());
			else
				bdmTlDtls.setBdanCompletedby("{}");
			if(UIUtils.isValidKeyId(womTlWomst.getWomsCostcenterid()))
				bdmTlDtls.setBdanCostcentre(womTlWomst.getWomsCostcenterid());
			else
				bdmTlDtls.setBdanCostcentre("{}");
			if(UIUtils.isValidKeyId(womTlWomst.getWomsStatus()))
				bdmTlDtls.setBdanErppoststatus(womTlWomst.getWomsStatus());
			else
				bdmTlDtls.setBdanErppoststatus("X");
			if(UIUtils.isValidKeyId(womTlWomst.getWomsPriority()))
				bdmTlDtls.setBdanProblemseverity(womTlWomst.getWomsPriority());
			else
				bdmTlDtls.setBdanProblemseverity("X");
			if(UIUtils.isValidKeyId(womTlWomst.getWomsFailuretypeid()))
				bdmTlDtls.setBdanFailuretype(womTlWomst.getWomsFailuretypeid());
			else
				bdmTlDtls.setBdanFailuretype("{}");
			if(UIUtils.isValidKeyId(womTlWomst.getWomsRequestapproved()))
				bdmTlDtls.setBdanIsapproved(womTlWomst.getWomsRequestapproved());
			else
				bdmTlDtls.setBdanIsapproved("X");
			if(UIUtils.isValidKeyId(womTlWomst.getWomsWoapprovalby()))
				bdmTlDtls.setBdanApproverdby(womTlWomst.getWomsWoapprovalby());
			else
				bdmTlDtls.setBdanApproverdby("{}");
			
			bdmTlDtls.setBdanErpnumber("0");

			if(UIUtils.isValidKeyId(womTlWomst.getWomsActive()))
				bdmTlDtls.setBdanActive(womTlWomst.getWomsActive());
			else
				bdmTlDtls.setBdanActive("N");
			if(UIUtils.isValidKeyId(womTlWomst.getWomsCreatedby()))
				bdmTlDtls.setBdanCreatedby(womTlWomst.getWomsCreatedby());
			else
				bdmTlDtls.setBdanCreatedby("{}");		
		
			bdmTlDtls.setBdanCreatedon(womTlWomst.getWomsCreatedon());
			bdmTlDtls.setBdanModifiedon(womTlWomst.getWomsModifiedon());
			bdmTlDtlList.add(bdmTlDtls);
			
		
		return bdmTlDtlList;

		
	}
	private AbnTlAbnormality fillAbnValues(WomTlWomst womTlWomst,WOFormBean woFormBean,AbnTlAbnormality newAbnTlAbnormality) {
		String dateTime = CommonFunctions.dateTimeNow();
		
		newAbnTlAbnormality.setAbnmActive("Y");		
		newAbnTlAbnormality.setAbnmCreatedby(womTlWomst.getWomsCreatedby());
		newAbnTlAbnormality.setAbnmCreatedon(womTlWomst.getWomsCreatedon());		
		newAbnTlAbnormality.setAbnmModifiedon(womTlWomst.getWomsModifiedon());	
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsSectionid()))
			newAbnTlAbnormality.setAbnmSectionid(womTlWomst.getWomsSectionid());
		else
			newAbnTlAbnormality.setAbnmSectionid("{}");
		
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsCellid()))
			newAbnTlAbnormality.setAbnmCellid(womTlWomst.getWomsCellid());
		else
			newAbnTlAbnormality.setAbnmCellid("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMachineid()))
			newAbnTlAbnormality.setAbnmEquipmentid(womTlWomst.getWomsMachineid());
		else
			newAbnTlAbnormality.setAbnmEquipmentid("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsAssemblyid()))
			newAbnTlAbnormality.setAbnmAssemblyid(womTlWomst.getWomsAssemblyid());
		else
			newAbnTlAbnormality.setAbnmAssemblyid("{}");
		
		
		if(UIUtils.isValidKeyId(woFormBean.getWomsBookedby()))		
			newAbnTlAbnormality.setAbnmDetectedby(woFormBean.getWomsBookedby());		
		else		
			newAbnTlAbnormality.setAbnmDetectedby("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsDoneby()))
			newAbnTlAbnormality.setAbnmCompletedby(womTlWomst.getWomsDoneby());
		else
			newAbnTlAbnormality.setAbnmCompletedby("{}");
		
		newAbnTlAbnormality.setAbnmDate(womTlWomst.getWomsShiftdate());
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsOccurreddate()))
			newAbnTlAbnormality.setAbnmDetectiondate(womTlWomst.getWomsOccurreddate());
		else
			newAbnTlAbnormality.setAbnmDetectiondate(dateTime);
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsReporteddate()))
		{
			newAbnTlAbnormality.setAbnmWoreceiveddate(womTlWomst.getWomsReporteddate());
			newAbnTlAbnormality.setAbnmTargetdate(womTlWomst.getWomsReporteddate());
		}
		else
		{
			newAbnTlAbnormality.setAbnmWoreceiveddate(dateTime);
			newAbnTlAbnormality.setAbnmTargetdate(dateTime);
		}
		
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsWoapprovaldate()))
			newAbnTlAbnormality.setAbnmFeedbackdate(womTlWomst.getWomsWoapprovaldate());
		else
			newAbnTlAbnormality.setAbnmFeedbackdate(dateTime);
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsProblem()))		
			newAbnTlAbnormality.setAbnmDescription(womTlWomst.getWomsProblem());		
		else		
			newAbnTlAbnormality.setAbnmDescription("{}");
		
		CommonMessage.debugMsg(womTlWomst.getWomsBookingremarks() + " : Booking Remarks");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsBookingremarks()))	
		{
			newAbnTlAbnormality.setAbnmRemarks(womTlWomst.getWomsBookingremarks());
			newAbnTlAbnormality.setAbnmTargetremarks(womTlWomst.getWomsBookingremarks());
			newAbnTlAbnormality.setAbnmDetailedesc(womTlWomst.getWomsBookingremarks());
		}
		else
		{
			newAbnTlAbnormality.setAbnmRemarks("{}");
			newAbnTlAbnormality.setAbnmTargetremarks("{}");
			newAbnTlAbnormality.setAbnmDetailedesc("{}");
		}
		
		newAbnTlAbnormality.setAbnmDowntime("0");
		newAbnTlAbnormality.setAbnmResponsetime("0");			
		newAbnTlAbnormality.setAbnmRevisionno("{}");
		newAbnTlAbnormality.setAbnmSubtype("{}");
		newAbnTlAbnormality.setAbnmTagclassid("{}");
		newAbnTlAbnormality.setAbnmFeedbackid("{}");
		newAbnTlAbnormality.setAbnmImpactid("{}");
		newAbnTlAbnormality.setAbnmPriority("{}");
		newAbnTlAbnormality.setAbnmPreventivemeasure("{}");
		newAbnTlAbnormality.setAbnmRefdocid(womTlWomst.getWomsKeyid());
		newAbnTlAbnormality.setAbnmRefdoctype("WOM");
		newAbnTlAbnormality.setAbnmCountermeasure("{}");
		newAbnTlAbnormality.setAbnmBlockdiagramref("{}");
		newAbnTlAbnormality.setAbnmCategoryid("{}");
		newAbnTlAbnormality.setAbnmContaminant("{}");
		newAbnTlAbnormality.setAbnmMode("{}");
		newAbnTlAbnormality.setAbnmPillar("JH");
		newAbnTlAbnormality.setAbnmFactoryid(womTlWomst.getWomsFactoryid());
		newAbnTlAbnormality.setAbnmSafetypatrol("JH");
		
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsRelatedto()))
			newAbnTlAbnormality.setAbnmRelatedto(womTlWomst.getWomsRelatedto());
		else
			newAbnTlAbnormality.setAbnmRelatedto("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMouldid()))	
			newAbnTlAbnormality.setAbnmMould(womTlWomst.getWomsMouldid());	
		else
			newAbnTlAbnormality.setAbnmMould("{}");
		
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsTradeid()))
			newAbnTlAbnormality.setAbnmTradeid(womTlWomst.getWomsTradeid());
		else
			newAbnTlAbnormality.setAbnmTradeid("{}");	
		
		newAbnTlAbnormality.setAbnmTypeid("{}");
		newAbnTlAbnormality.setAbnmWhatcause("{}");			
		newAbnTlAbnormality.setAbnmWhyabnhappened("{}");
		newAbnTlAbnormality.setAbnmWodetailid("{}");
		newAbnTlAbnormality.setAbnmWomasterid("{}");		
		newAbnTlAbnormality.setAbnmWorktime("0");	
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsWorkstartdate()))
			newAbnTlAbnormality.setAbnmWostarttime(womTlWomst.getWomsWorkstartdate());
		else
			newAbnTlAbnormality.setAbnmWostarttime(Constants.passNullDate);
		if(UIUtils.isValidKeyId(womTlWomst.getWomsWorkenddate()))
			newAbnTlAbnormality.setAbnmWoendtime(womTlWomst.getWomsWorkenddate());
		else
			newAbnTlAbnormality.setAbnmWoendtime(Constants.futureNullDate);
		if(UIUtils.isValidKeyId(womTlWomst.getWomsShiftid()))
			newAbnTlAbnormality.setAbnmShiftid(womTlWomst.getWomsShiftid());
		else
			newAbnTlAbnormality.setAbnmShiftid("{}");
			
			
			
		return newAbnTlAbnormality;
		
	}
	
	private PlmTlGenmaintenance fillGMValues(WomTlWomst womTlWomst,WOFormBean woFormBean,PlmTlGenmaintenance newPlmTlGenmaintenance) {
		CommonMessage.debugMsg(" Allotted BY : "+woFormBean.getAllottedBy());
		newPlmTlGenmaintenance.setGmntActive(womTlWomst.getWomsActive());		
		String dateTime = CommonFunctions.dateTimeNow();
		if(UIUtils.isValidKeyId(womTlWomst.getWomsCreatedby()))
			newPlmTlGenmaintenance.setGmntCreatedby(womTlWomst.getWomsCreatedby());
		else
			newPlmTlGenmaintenance.setGmntCreatedby("{}");
		newPlmTlGenmaintenance.setGmntCreatedon(womTlWomst.getWomsCreatedon());		
		newPlmTlGenmaintenance.setGmntModifiedon(womTlWomst.getWomsModifiedon());
		newPlmTlGenmaintenance.setGmntAction("{}");
		
		newPlmTlGenmaintenance.setGmntActivitytype("O");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsReporteddate()))
		{
			newPlmTlGenmaintenance.setGmntAllocateddate(womTlWomst.getWomsReporteddate());
			newPlmTlGenmaintenance.setGmntBookeddate(womTlWomst.getWomsReporteddate());
			newPlmTlGenmaintenance.setGmntCompleteddate(womTlWomst.getWomsReporteddate());
			newPlmTlGenmaintenance.setGmntReceiveddate(womTlWomst.getWomsReporteddate());
			newPlmTlGenmaintenance.setGmntTargetdate(womTlWomst.getWomsReporteddate());
			newPlmTlGenmaintenance.setGmntWoenddate(womTlWomst.getWomsReporteddate());
			newPlmTlGenmaintenance.setGmntWostartdate(womTlWomst.getWomsReporteddate());
		}
		else
		{
			newPlmTlGenmaintenance.setGmntAllocateddate(dateTime);		
			newPlmTlGenmaintenance.setGmntBookeddate(dateTime);
			newPlmTlGenmaintenance.setGmntCompleteddate(dateTime.substring(0, 11));
			newPlmTlGenmaintenance.setGmntReceiveddate(dateTime.substring(0,11));
			newPlmTlGenmaintenance.setGmntTargetdate(dateTime);
			newPlmTlGenmaintenance.setGmntWoenddate(Constants.passNullDate);
			newPlmTlGenmaintenance.setGmntWostartdate(Constants.passNullDate);
		}
		if(UIUtils.isValidKeyId(woFormBean.getWomsBookedby()))
		{
			newPlmTlGenmaintenance.setGmntCompletedby(woFormBean.getWomsBookedby());
			newPlmTlGenmaintenance.setGmntReportedby(woFormBean.getWomsBookedby());
		}
		else
		{
			newPlmTlGenmaintenance.setGmntCompletedby("{}");
			newPlmTlGenmaintenance.setGmntReportedby("{}");
		}
		
		newPlmTlGenmaintenance.setGmntContractorcost("0");
		newPlmTlGenmaintenance.setGmntCountermeasure("{}");
		newPlmTlGenmaintenance.setGmntDowntime("0");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsFactoryid()) )
			newPlmTlGenmaintenance.setGmntFactoryid(womTlWomst.getWomsFactoryid());
		else			
			newPlmTlGenmaintenance.setGmntFactoryid("{}");
		
		newPlmTlGenmaintenance.setGmntIsyy("Y");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsCellid()) )
			newPlmTlGenmaintenance.setGmntLineid(womTlWomst.getWomsCellid());
		else			
			newPlmTlGenmaintenance.setGmntLineid("{}");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMachineid()) )
			newPlmTlGenmaintenance.setGmntMachineid(womTlWomst.getWomsMachineid());
		else			
			newPlmTlGenmaintenance.setGmntMachineid("{}");
		
		newPlmTlGenmaintenance.setGmntManpowercost("0");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMachinecondition()) )
			newPlmTlGenmaintenance.setGmntMchcondition(womTlWomst.getWomsMachinecondition());
		else			
			newPlmTlGenmaintenance.setGmntMchcondition("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMouldid()) )
			newPlmTlGenmaintenance.setGmntMouldid(womTlWomst.getWomsMouldid());
		else			
			newPlmTlGenmaintenance.setGmntMouldid("{}");
	
		if(UIUtils.isValidKeyId(womTlWomst.getWomsOccurreddate()) )
			newPlmTlGenmaintenance.setGmntOccureddate(womTlWomst.getWomsOccurreddate());
		else
			newPlmTlGenmaintenance.setGmntOccureddate(dateTime);
		newPlmTlGenmaintenance.setGmntOthercost("0");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsPartlocation()) )
			newPlmTlGenmaintenance.setGmntPartlocation(womTlWomst.getWomsPartlocation());
		else
			newPlmTlGenmaintenance.setGmntPartlocation("{}");
		
		newPlmTlGenmaintenance.setGmntPctrmeasure("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsPhenomenaid()) )
			newPlmTlGenmaintenance.setGmntPhenid(womTlWomst.getWomsPhenomenaid());
		else
			newPlmTlGenmaintenance.setGmntPhenid("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsProblem()) )
			newPlmTlGenmaintenance.setGmntProblem(womTlWomst.getWomsProblem());
		else
			newPlmTlGenmaintenance.setGmntProblem("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsKeyid()) )
			newPlmTlGenmaintenance.setGmntRefdocid(womTlWomst.getWomsKeyid());
		else
			newPlmTlGenmaintenance.setGmntRefdocid("{}");
		
		
		newPlmTlGenmaintenance.setGmntRefdoctype("WOM");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsRelatedto()) )
			newPlmTlGenmaintenance.setGmntRelatedto(womTlWomst.getWomsRelatedto());
		else
			newPlmTlGenmaintenance.setGmntRelatedto("{}");
		
		//if(UIUtils.isValidKeyId(womTlWomst.getWomsBookingremarks()) )
		//	newPlmTlGenmaintenance.setGmntRemarks(womTlWomst.getWomsBookingremarks());
		//else
			newPlmTlGenmaintenance.setGmntRemarks("{}");
		
		newPlmTlGenmaintenance.setGmntResponsetime("0");
		newPlmTlGenmaintenance.setGmntRootcause("{}");
		newPlmTlGenmaintenance.setGmntRootcauseid("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsSectionid()))
			newPlmTlGenmaintenance.setGmntSectionid(womTlWomst.getWomsSectionid());
		else
			newPlmTlGenmaintenance.setGmntSectionid("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsShiftid()))
			newPlmTlGenmaintenance.setGmntShift(womTlWomst.getWomsShiftid());
		else
			newPlmTlGenmaintenance.setGmntShift("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsShiftdate()))
			newPlmTlGenmaintenance.setGmntShiftdate(womTlWomst.getWomsShiftdate());
		else
			newPlmTlGenmaintenance.setGmntShiftdate("{}");
		
		newPlmTlGenmaintenance.setGmntSparecost("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsAssemblyid()))
			newPlmTlGenmaintenance.setGmntStationid(womTlWomst.getWomsAssemblyid());
		else
			newPlmTlGenmaintenance.setGmntStationid("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsStatus()))
			newPlmTlGenmaintenance.setGmntStatus(womTlWomst.getWomsStatus());
		else
			newPlmTlGenmaintenance.setGmntStatus("P");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsTradeid()))
			newPlmTlGenmaintenance.setGmntTrade(womTlWomst.getWomsTradeid());
		else
			newPlmTlGenmaintenance.setGmntTrade("{}");
		
		newPlmTlGenmaintenance.setGmntTempfield1("-");
		newPlmTlGenmaintenance.setGmntTempfield2("-");
		newPlmTlGenmaintenance.setGmntTempfield3("-");
		newPlmTlGenmaintenance.setGmntTempfield4("-");
		newPlmTlGenmaintenance.setGmntTempfield5("-");
		newPlmTlGenmaintenance.setGmntTempfield6("-");
		newPlmTlGenmaintenance.setGmntTempfield7("-");
		newPlmTlGenmaintenance.setGmntTempfield8("-");
		newPlmTlGenmaintenance.setGmntTempfield9("-");
		newPlmTlGenmaintenance.setGmntTempfield10("-");
		newPlmTlGenmaintenance.setGmntWorkhours("0");
		newPlmTlGenmaintenance.setGmntYyno("0");
		
		return newPlmTlGenmaintenance;

	}
	private MldTlMouldunloadmst fillMouldUnloadValues(WomTlWomst womTlWomst,WOFormBean woFormBean,MldTlMouldunloadmst mldTlMouldunloadmst) {
		CommonMessage.debugMsg(" Allotted BY : "+woFormBean.getAllottedBy());
		mldTlMouldunloadmst.setMunlActive(womTlWomst.getWomsActive());		
		String dateTime = CommonFunctions.dateTimeNow();
		if(UIUtils.isValidKeyId(womTlWomst.getWomsCreatedby()))
			mldTlMouldunloadmst.setMunlCreatedby(womTlWomst.getWomsCreatedby());
		else
			mldTlMouldunloadmst.setMunlCreatedby("{}");
		mldTlMouldunloadmst.setMunlCreatedon(womTlWomst.getWomsCreatedon());		
		mldTlMouldunloadmst.setMunlModifiedon(womTlWomst.getWomsModifiedon());
		mldTlMouldunloadmst.setMunlAction("{}");
		
		mldTlMouldunloadmst.setMunlActivitytype("O");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsReporteddate()))
		{
			mldTlMouldunloadmst.setMunlAllocateddate(womTlWomst.getWomsReporteddate());
			mldTlMouldunloadmst.setMunlBookeddate(womTlWomst.getWomsReporteddate());
			mldTlMouldunloadmst.setMunlCompleteddate(womTlWomst.getWomsReporteddate());
			mldTlMouldunloadmst.setMunlReceiveddate(womTlWomst.getWomsReporteddate());
			mldTlMouldunloadmst.setMunlTargetdate(womTlWomst.getWomsReporteddate());
			mldTlMouldunloadmst.setMunlWoenddate(womTlWomst.getWomsReporteddate());
			mldTlMouldunloadmst.setMunlWostartdate(womTlWomst.getWomsReporteddate());
		}
		else
		{
			mldTlMouldunloadmst.setMunlAllocateddate(dateTime);		
			mldTlMouldunloadmst.setMunlBookeddate(dateTime);
			mldTlMouldunloadmst.setMunlCompleteddate(dateTime.substring(0, 11));
			mldTlMouldunloadmst.setMunlReceiveddate(dateTime.substring(0,11));
			mldTlMouldunloadmst.setMunlTargetdate(dateTime);
			mldTlMouldunloadmst.setMunlWoenddate(Constants.passNullDate);
			mldTlMouldunloadmst.setMunlWostartdate(Constants.passNullDate);
		}
		
		if(UIUtils.isValidKeyId(woFormBean.getWomsBookedby()))
		{
			mldTlMouldunloadmst.setMunlCompletedby(woFormBean.getWomsBookedby());
			mldTlMouldunloadmst.setMunlReportedby(woFormBean.getWomsBookedby());
		}
		else
		{
			mldTlMouldunloadmst.setMunlCompletedby("{}");
			mldTlMouldunloadmst.setMunlReportedby("{}");
		}
		
		mldTlMouldunloadmst.setMunlContractorcost("0");
		mldTlMouldunloadmst.setMunlCountermeasure("{}");
		mldTlMouldunloadmst.setMunlDowntime("0");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsFactoryid()) )
			mldTlMouldunloadmst.setMunlFactoryid(womTlWomst.getWomsFactoryid());
		else			
			mldTlMouldunloadmst.setMunlFactoryid("{}");
		
		mldTlMouldunloadmst.setMunlIsyy("Y");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsCellid()) )
			mldTlMouldunloadmst.setMunlLineid(womTlWomst.getWomsCellid());
		else			
			mldTlMouldunloadmst.setMunlLineid("{}");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMachineid()) )
			mldTlMouldunloadmst.setMunlMachineid(womTlWomst.getWomsMachineid());
		else			
			mldTlMouldunloadmst.setMunlMachineid("{}");
		
		mldTlMouldunloadmst.setMunlManpowercost("0");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMachinecondition()) )
			mldTlMouldunloadmst.setMunlMchcondition(womTlWomst.getWomsMachinecondition());
		else			
			mldTlMouldunloadmst.setMunlMchcondition("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsMouldid()) )
			mldTlMouldunloadmst.setMunlMouldid(womTlWomst.getWomsMouldid());
		else			
			mldTlMouldunloadmst.setMunlMouldid("{}");
	
		
		mldTlMouldunloadmst.setMunlOthercost("0");
		if(UIUtils.isValidKeyId(womTlWomst.getWomsPartlocation()) )
			mldTlMouldunloadmst.setMunlPartlocation(womTlWomst.getWomsPartlocation());
		else
			mldTlMouldunloadmst.setMunlPartlocation("{}");
		
		mldTlMouldunloadmst.setMunlPctrmeasure("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsPhenomenaid()) )
			mldTlMouldunloadmst.setMunlPhenid(womTlWomst.getWomsPhenomenaid());
		else
			mldTlMouldunloadmst.setMunlPhenid("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsProblem()) )
			mldTlMouldunloadmst.setMunlReason(womTlWomst.getWomsProblem());
		else
			mldTlMouldunloadmst.setMunlReason("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsKeyid()) )
			mldTlMouldunloadmst.setMunlRefdocid(womTlWomst.getWomsKeyid());
		else
			mldTlMouldunloadmst.setMunlRefdocid("{}");
		
		
		mldTlMouldunloadmst.setMunlRefdoctype("WOM");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsRelatedto()) )
			mldTlMouldunloadmst.setMunlRelatedto(womTlWomst.getWomsRelatedto());
		else
			mldTlMouldunloadmst.setMunlRelatedto("{}");
		
		mldTlMouldunloadmst.setMunlRemarks("{}");
		
		mldTlMouldunloadmst.setMunlResponsetime("0");
		mldTlMouldunloadmst.setMunlRootcause("{}");
		mldTlMouldunloadmst.setMunlRootcauseid("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsSectionid()))
			mldTlMouldunloadmst.setMunlSectionid(womTlWomst.getWomsSectionid());
		else
			mldTlMouldunloadmst.setMunlSectionid("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsShiftid()))
			mldTlMouldunloadmst.setMunlShift(womTlWomst.getWomsShiftid());
		else
			mldTlMouldunloadmst.setMunlShift("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsShiftdate()))
			mldTlMouldunloadmst.setMunlShiftdate(womTlWomst.getWomsShiftdate());
		else
			mldTlMouldunloadmst.setMunlShiftdate("{}");
		
		mldTlMouldunloadmst.setMunlSparecost("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsAssemblyid()))
			mldTlMouldunloadmst.setMunlStationid(womTlWomst.getWomsAssemblyid());
		else
			mldTlMouldunloadmst.setMunlStationid("{}");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsStatus()))
			mldTlMouldunloadmst.setMunlStatus(womTlWomst.getWomsStatus());
		else
			mldTlMouldunloadmst.setMunlStatus("P");
		
		if(UIUtils.isValidKeyId(womTlWomst.getWomsTradeid()))
			mldTlMouldunloadmst.setMunlTrade(womTlWomst.getWomsTradeid());
		else
			mldTlMouldunloadmst.setMunlTrade("{}");
		
		mldTlMouldunloadmst.setMunlTempfield1("-");
		mldTlMouldunloadmst.setMunlTempfield2("-");
		mldTlMouldunloadmst.setMunlTempfield3("-");
		mldTlMouldunloadmst.setMunlTempfield4("-");
		mldTlMouldunloadmst.setMunlTempfield5("-");
		mldTlMouldunloadmst.setMunlTempfield6("-");
		mldTlMouldunloadmst.setMunlTempfield7("-");
		mldTlMouldunloadmst.setMunlTempfield8("-");
		mldTlMouldunloadmst.setMunlTempfield9("-");
		mldTlMouldunloadmst.setMunlTempfield10("-");
		mldTlMouldunloadmst.setMunlWorkhours("0");
		mldTlMouldunloadmst.setMunlYyno("0");
		
		return mldTlMouldunloadmst;

	}

	public WomsTlTaskmst fillValuesTaskmst( WomsTlTaskmst womsTlTaskmst) {

		String dateTime = CommonFunctions.dateTimeNow();
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsKeyid())) {
			womsTlTaskmst.setWtmsCreatedon(dateTime);		
			womsTlTaskmst.setWtmsModifiedon(dateTime);
		}
		
		if (!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsCreatedon()))
			womsTlTaskmst.setWtmsCreatedon(dateTime);		
		if (!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsModifiedon()))
			womsTlTaskmst.setWtmsModifiedon(dateTime);
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsCreatedby()))
			womsTlTaskmst.setWtmsCreatedby("-");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsJobType()))
			womsTlTaskmst.setWtmsJobType("-");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsFrequency()))
			womsTlTaskmst.setWtmsFrequency("0");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsAssemblyid()))
			womsTlTaskmst.setWtmsAssemblyid("-");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsStatus()))
			womsTlTaskmst.setWtmsStatus("P");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsObservation()))
			womsTlTaskmst.setWtmsObservation("-");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsResponsibility()))
			womsTlTaskmst.setWtmsResponsibility("-");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsTargetDate()))
			womsTlTaskmst.setWtmsTargetDate(dateTime);
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsPlannedDuration()))
			womsTlTaskmst.setWtmsPlannedDuration("0");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsActualDuration()))
			womsTlTaskmst.setWtmsActualDuration("0");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsActionTaken()))
			womsTlTaskmst.setWtmsActionTaken("-");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsCbmReading()))
			womsTlTaskmst.setWtmsCbmReading("0");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsZoneColor()))
			womsTlTaskmst.setWtmsZoneColor("-");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsCbmAction()))
			womsTlTaskmst.setWtmsCbmAction("-");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsAdjustedReading()))
			womsTlTaskmst.setWtmsAdjustedReading("0");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsNextDueDate()))
			womsTlTaskmst.setWtmsNextDueDate(Constants.futureNullDate);
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsRemarks()))
			womsTlTaskmst.setWtmsRemarks("-");

		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsIdealConditionType()))
			womsTlTaskmst.setWtmsIdealConditionType("-");

		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsIdealCondition()))
			womsTlTaskmst.setWtmsIdealCondition("-");

		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsActualCondition()))
			womsTlTaskmst.setWtmsActualCondition("-");

		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsNotiRefno()))
			womsTlTaskmst.setWtmsNotiRefno("-");

		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsNotiStatus()))
			womsTlTaskmst.setWtmsNotiStatus("-");
		
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsWomsRefno()))
			womsTlTaskmst.setWtmsWomsRefno("-");

		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsWomsStatus()))
			womsTlTaskmst.setWtmsWomsStatus("-");
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsExtrepairflag()))
			womsTlTaskmst.setWtmsExtrepairflag("N");
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsExtserviceflag()))
			womsTlTaskmst.setWtmsExtserviceflag("N");
		//if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsActivityno()))
		//	womsTlTaskmst.setWtmsActivityno("-");
		if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsActionplanid()))
			womsTlTaskmst.setWtmsActionplanid("-");
		womsTlTaskmst.setWtmsTemp5("-");
		womsTlTaskmst.setWtmsTemp6("-");
		womsTlTaskmst.setWtmsTemp7("-");
		womsTlTaskmst.setWtmsTemp8("-");
		womsTlTaskmst.setWtmsTemp9("-");

		return womsTlTaskmst;
	}
	@Override
	public List<String[]> getOrder() throws Exception {
		return womTlWomstDao.getOrder();
	}
	public List<String[]> getStdWoShMainGrid() throws Exception {
		return womTlWomstDao.getStdWoShMainGrid();
	}
	public List<String[]> getOrderMainGrid() throws Exception {
		return womTlWomstDao.getOrderMainGrid();
	}
	public List<String[]> getOrderExernal() throws Exception {
		return womTlWomstDao.getOrderExernal();
	}
	
	public List<String[]> getSapqueue(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return womTlWomstDao.getSapqueue(commonFilter);
	}

	public List<String[]> getTaskDetails(CommonFilter commonFilter,	String keyid) throws Exception {
		return womTlWomstDao.getTaskDetails(commonFilter,keyid);
	}

	
	public List<String[]> getSparesDetails(CommonFilter commonFilter,	String keyid) throws Exception {
		return womTlWomstDao.getSparesDetails(commonFilter,keyid);
	}

	public List<String[]> getResourceInfo(CommonFilter commonFilter,	String keyid) throws Exception {
		return womTlWomstDao.getResourceInfo(commonFilter,keyid);
	}
	
	public List<String[]> getOtherCost(CommonFilter commonFilter,	String womsId,String taskId) throws Exception {
		return womTlWomstDao.getOtherCost(commonFilter,womsId,taskId);
	}
	

	public List<ComboBox> getOtherCostType(ComboFilter comboFilter)
			throws Exception {
		comboFilter.setCodeField("WOTY_CODE");
		comboFilter.setNameField("WOTY_TYPE");
		comboFilter.setIdField("WOTY_KEYID");	
		comboFilter.setTableName("WOM_TL_OTHERCOST_TYPEMST");
		StringBuffer sb = new StringBuffer();
		
		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getCostcenter(ComboFilter comboFilter,String cellid) throws Exception{
		comboFilter.setCodeField("cstm_code");
		comboFilter.setNameField("cstm_name");
		comboFilter.setIdField("cstm_keyid");
		//comboFilter.setIdField("cstm_code");
		comboFilter.setTableName("gen_tl_costcentremst,Gen_vw_JHCostcenter_Link");
		StringBuffer sb = new StringBuffer();
		sb.append(" and cstm_keyid =costcenter ");
		if(UIUtils.isValidKeyId(cellid))
			sb.append(" and JH='"+cellid+"' ");
		comboFilter.setCondSql(sb.toString());
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	@Override
	public List<ComboBox> getResources(ComboFilter comboFilter, String type, String flid, String others) throws Exception{
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		StringBuffer condSql = new StringBuffer();
		condSql.append(" AND EMPM_KEYID IN(select EMPM_KEYID FROM GEN_TL_EMPLOYEEMST,GEN_TL_TEAMTRADELINK,GEN_TL_FNLNROLETEAM ");
		   condSql.append("where FRP_FRT_KEYID = FRT_KEYID and FRT_EMPM_KEYID = empm_keyid and EMPM_active = 'Y' ");
		   if(type.equals("R"))
				 condSql.append( "AND empm_employeetype ='R' ");
			else 
			 condSql.append( "AND empm_employeetype not Like 'R' ");
	 if(UIUtils.isValidKeyId(flid) && "Y".equals(others))  {
			condSql.append("AND FRT_FNLN_KEYID NOT IN  ( SELECT flid FROM gen_mv_flidhierarchy WHERE "); 
		    condSql.append(" INSTR (parentflids || '-' || flid, '"+flid+"') >0 ))");  
			
		}
		else{
			 condSql.append("AND FRT_FNLN_KEYID  IN  ( SELECT flid FROM gen_mv_flidhierarchy WHERE "); 
			    condSql.append(" INSTR (parentflids || '-' || flid, '"+flid+"') >0 ))"); 
		}
		CommonMessage.debugMsg("resource combo condsql:"+condSql.toString());
		comboFilter.setCondSql(condSql.toString()); 
		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getMaterialGroup(ComboFilter comboFilter)	throws Exception {
			comboFilter.setNameField("GMGP_DIVISION");
			comboFilter.setIdField("GMGP_KEYID");
			comboFilter.setCodeField("GMGP_CODE");
			comboFilter.setTableName("GEN_TL_MATERIAL_GROUP");
			/*String condSql = null;
			if(type.equals("R"))
			 condSql = "AND empm_employeetype ='R' ";
			else 
			 condSql = "AND empm_employeetype not Like 'R' ";
			comboFilter.setCondSql(condSql);*/ 
			return commonFilterDao.fillComboValues(comboFilter);
}
	public List<ComboBox> getVendor(ComboFilter comboFilter)	throws Exception {
		comboFilter.setNameField("PNOR_NAME");
		comboFilter.setIdField("PNOR_KEYID");
		comboFilter.setCodeField("PNOR_SHORTNAME");
		comboFilter.setTableName("GEN_TL_PARTNORMST");
		/*String condSql = null;
		if(type.equals("R"))
		 condSql = "AND empm_employeetype ='R' ";
		else 
		 condSql = "AND empm_employeetype not Like 'R' ";
		comboFilter.setCondSql(condSql);*/ 
		return commonFilterDao.fillComboValues(comboFilter);
}
	public List<ComboBox> getPurchaseGroup(ComboFilter comboFilter)	throws Exception {
		comboFilter.setNameField("GPRG_NAME");
		comboFilter.setIdField("GPRG_KEYID");
		comboFilter.setCodeField("GPRG_CODE");
		comboFilter.setTableName("GEN_TL_PURCHASE_GROUP");
		/*String condSql = null;
		if(type.equals("R"))
		 condSql = "AND empm_employeetype ='R' ";
		else 
		 condSql = "AND empm_employeetype not Like 'R' ";
		comboFilter.setCondSql(condSql);*/ 
		return commonFilterDao.fillComboValues(comboFilter);
}
	public List<ComboBox> getSapRequistions(ComboFilter comboFilter,String flid)	throws Exception {
		comboFilter.setNameField("Reqrt_Requester_Name");
		comboFilter.setIdField("REQRT_RELEASE_KEYID");
		comboFilter.setCodeField("REQRT_RELEASE_CODE");
		comboFilter.setTableName("GEN_TL_REQUISITIONER"); 
		if(UIUtils.isValidKeyId(flid)){
			comboFilter.setCondSql(" and reqrt_plant in(select locn_code from gen_vw_fnln where fnln_keyid='"+flid+"')");
		}
		return commonFilterDao.fillComboValues(comboFilter);
}

	@Override
	public void reSubmitSAP() throws Exception {
		// TODO Auto-generated method stub
		womTlWomstDao.reSubmitSAP();
	}
	public List<ComboBox> getPurchaseOrg(ComboFilter comboFilter)	throws Exception {
		comboFilter.setNameField("GPOR_NAME");
		comboFilter.setIdField("GPOR_KEYID");
		comboFilter.setCodeField("GPOR_CODE");
		comboFilter.setTableName("GEN_TL_PURCHASE_ORG");
		return commonFilterDao.fillComboValues(comboFilter);
}
	@Override
	public String allowUpdate(String womsKeyid,String type) throws Exception {
		// TODO Auto-generated method stub
		return womTlWorkorderMstDao.allowUpdate(womsKeyid,type);
	}

	public String getLocationBasedMandfield(String loginFlid) throws Exception{
		return womTlWorkorderMstDao.getLocationBasedMandfield(loginFlid); 
	}

	@Override
	public String submitMOrder(String woKey, String type) throws Exception {
		// TODO Auto-generated method stub
		return womTlWorkorderMstDao.submitMorderToSap(woKey, type) ;
	}

	@Override
	public String deleteWO(WomTlWorkorderMst newWomTlWorkorderMst)
			throws Exception {
		// TODO Auto-generated method stub
		return womTlWorkorderMstDao.deleteWO(newWomTlWorkorderMst) ;
	}

	@Override
	public String getLineNo(String tableName, String condId,String condIdField) throws Exception
			 {
		// TODO Auto-generated method stub
		return  womTlWorkorderMstDao.getLineNo(tableName,condId,condIdField) ;
	}
	public void updateControlKey(String womsKey, String cntrlKey) throws Exception{
		
			womTlWomstDao.updateControlKey(womsKey,cntrlKey);
	}

	@Override
	public List<String[]> getSapReservationDetail(String keyId)
			throws Exception {
		// TODO Auto-generated method stub
		return womTlWomstDao.getSapReservationDetail(keyId);
	}
	public List<String[]> getSapStatus(String womsId, String type) throws Exception{
		return womTlWomstDao.getSapStatus(womsId,type);
	}

	@Override
	public List<ComboBox> getEquipmentBOM(String mchId, ComboFilter comboFilter)
			throws Exception {
		// TODO Auto-generated method stub
		String tblName="gen_tl_equipment_bom,gen_tl_material_mst";
		comboFilter.setCodeField("GBOM_COMPONENT");
		comboFilter.setIdField("GBOM_KEYID");
		comboFilter.setNameField("GMM_DESC");
		comboFilter.setTableName(tblName);
		StringBuffer sb = new StringBuffer();
		if(UIUtils.isValidKeyId(mchId))
		{		
			sb.append(" AND GBOM_EQUIPMENT_NO in (select mchm_machineno from gen_tl_machinemst where mchm_keyid='"+mchId+"')" );
			sb.append(" and gbom_component=gmm_number ");
						
		}
	
		if(UIUtils.isValidKeyId(sb.toString()))
			comboFilter.setCondSql(sb.toString());
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getWbsElementId(ComboFilter comboFilter,
			String orderType, String workCenter, String controlKey)
			throws Exception {
		// TODO Auto-generated method stub
		String tblName=" SAP_TL_WKCTR_WBS_BUDGET_LINK,conf_tl_saporder_wbslink ";
		comboFilter.setIdField("SWWB_KEYID");
		comboFilter.setCodeField(" decode(trim(CONF_WBSTYPE),'R',swwb_wbs_regular,swwb_wbs_fixed) ");
		comboFilter.setTableName(tblName);
		StringBuffer sb = new StringBuffer();
		if(UIUtils.isValidKeyId(controlKey))
		{
			sb.append(" and conf_controlkey='"+controlKey+"' ");
		}
		if(UIUtils.isValidKeyId(orderType))
		{
			sb.append(" and conf_ordertype_keyid='"+orderType+"' ");
		}
		if(UIUtils.isValidKeyId(workCenter))
		{
			sb.append(" and swwb_workcenter in(select wkcm_code from GEN_TL_WORKCENTREMST where wkcm_keyid= '"+workCenter+"' or trim(wkcm_code)= '"+workCenter+"' ) ");
		}
		comboFilter.setCondSql(sb.toString());
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<String[]> getPrintHeaderData(String womsKey) throws Exception {
		// TODO Auto-generated method stub
		return womTlWorkorderMstDao.getPrintHeaderData(womsKey);
	}

	@Override
	public List<String[]> getPrintMaterialData(String womsKey, String taskId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSubContractCheck(String womsKey, String taskId)
			throws Exception {
		// TODO Auto-generated method stub
		 return womTlWorkorderMstDao.getSubContractCheck(womsKey,taskId);
	}

	@Override
	public int getMatProvIndCnt(String womsKey, String taskId)
			throws Exception {
		// TODO Auto-generated method stub
		return womTlWorkorderMstDao.getMatProvIndCnt(womsKey,taskId);
	}

	@Override
	public List<ComboBox> getSapFunctionallocn(ComboFilter comboFilter)
			throws Exception {
		// TODO Auto-generated method stub
		//comboFilter.setNameField("SAP_FNLN");
		comboFilter.setIdField("Perfex_flid");
		comboFilter.setCodeField("SAP_FNLN");
		comboFilter.setTableName("sap_vw_functionallocn");
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<String[]> getSapFnlnDetail(String flid,String fnlnTxt) throws Exception {
		// TODO Auto-generated method stub
		return womTlWorkorderMstDao.getSapFnlnDetail(flid,fnlnTxt) ;
	}

	@Override
	public List<String[]> getDefSapFnln(String flid, String fnlnTxt)
			throws Exception {
		// TODO Auto-generated method stub
		return womTlWorkorderMstDao.getDefSapFnln(flid,fnlnTxt) ;
	}
}
 