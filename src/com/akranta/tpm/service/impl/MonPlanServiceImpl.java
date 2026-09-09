package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.MilestoneBean;
import com.akranta.tpm.bean.WorkOrderDetailsBean;
import com.akranta.tpm.bean.WorkOrderDetailsLstBean;
import com.akranta.tpm.bean.WorkOrderFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.MonPlanDao;
import com.akranta.tpm.dao.MspTlIndicatorsDao;
import com.akranta.tpm.dao.MspTlIndicatorsMstDao;
import com.akranta.tpm.dao.MspTlMstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.MonPlanDaoImpl;
import com.akranta.tpm.dao.impl.MspTlIndicatorsDaoImpl;
import com.akranta.tpm.dao.impl.MspTlIndicatorsMstDaoImpl;
import com.akranta.tpm.dao.impl.MspTlMstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MspTlDtl;
import com.akranta.tpm.model.MspTlIndicators;
import com.akranta.tpm.model.MspTlIndicatorsMst;
import com.akranta.tpm.model.MspTlMst;
import com.akranta.tpm.model.PlmTlSpareconsumed;
import com.akranta.tpm.model.PlmTlSparecostactual;
import com.akranta.tpm.model.PlmTlWofeedback;
import com.akranta.tpm.model.PlmTlWorksummary;
import com.akranta.tpm.service.MonthlyPlanService;
import com.akranta.tpm.model.MspTlIndicatorsDtl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class MonPlanServiceImpl implements MonthlyPlanService {
	
	private MonPlanDao monPlanDao;
	private MspTlIndicatorsDao mspTlIndicatorsDao;
	private MspTlIndicatorsMstDao mspTlIndicatorsMstDao;
	private MspTlMstDao mspTlMstDao;	
	private Validations validations ;
	private CommonFilterDao commonFilterDao;
	
	public MonPlanServiceImpl(DBActionTemplate dbActionTemplate)
	{
		monPlanDao =  new MonPlanDaoImpl(dbActionTemplate);
		mspTlIndicatorsDao =  new MspTlIndicatorsDaoImpl(dbActionTemplate);
		mspTlIndicatorsMstDao =  new MspTlIndicatorsMstDaoImpl(dbActionTemplate);
		mspTlMstDao =  new MspTlMstDaoImpl(dbActionTemplate);
		validations = new Validations();
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
	}
	
	public List<String []> getMonPlan(CommonFilter commonFilter)  throws Exception{
		return this.monPlanDao.getMonPlan(commonFilter);
	}
	
	@Override
	public List<String[]> getfillgridheader() throws Exception {
		// TODO Auto-generated method stub
		return this.monPlanDao.getfillgridheader();
	}
	
	@Override
	public List<String[]> getfillgriddata(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.monPlanDao.getfillgriddata();
	}

	@Override
	public Workbook getMonPlanExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.monPlanDao.getMonPlanExcel(commonFilter,colmodel,rptFormat);
	}

	@Override
	public List<String[]> getAllwoGenData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.monPlanDao.getAllwoGenData(commonFilter);
	}

	@Override
	public List<String[]> getAllwoCompData(CommonFilter commonFilter,String viewChecked) throws Exception {
		// TODO Auto-generated method stub
		return this.monPlanDao.getAllwoCompData(commonFilter, viewChecked);
	}

	@Override
	public List<String[]> getspareData(String pmstdId) throws Exception {
		// TODO Auto-generated method stub
		return this.monPlanDao.getspareData(pmstdId);
	}
	@Override
	public List<String[]> getupdatecancelData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.monPlanDao.getupdatecancelData(commonFilter);
	}
	@Override
	public List<String[]> getupdatecancelselctData(CommonFilter commonFilter,String workorderno, String wOgenCancl) throws Exception {
		// TODO Auto-generated method stub
		return this.monPlanDao.getupdatecancelselctData(commonFilter,workorderno,wOgenCancl);
	}
	@Override
	public PlmTlWofeedback saveWOD(PlmTlWofeedback newPlmTlWofeedback,PlmTlWofeedback existPlmTlWofeedback,WorkOrderDetailsBean workOrderDetailsBean) throws Exception {
		// TODO Auto-generated method stub
		try {
			CommonMessage.debugMsg("insdie service impl of wodetails create");
			String validationsFor = "create";
			CommonMessage.debugMsg("span ID   :"+workOrderDetailsBean.getWofbStarttime());
			CommonMessage.debugMsg("occured time     :"+newPlmTlWofeedback.getWofbStartdate()+" "+workOrderDetailsBean.getWofbStarttime());
			validations.validate(newPlmTlWofeedback,"workOrderDetails",validationsFor);//com.akranta.validations.tpm.validations.workorderDetails.xml - defined rules for server side validations
			List<WorkOrderDetailsLstBean> griddata = newPlmTlWofeedback.getWobean();
			//CommonMessage.debugMsg("dasf---"+griddata.size());
			/*if(newPlmTlWofeedback.getWobean()!= null){
				for(WorkOrderDetailsLstBean griddat:griddata)
				{
					newPlmTlWofeedback.setWofbAction(griddat.getWofbAction());
					newPlmTlWofeedback.setWofbDuration(griddat.getWofbDuration());
					newPlmTlWofeedback.setWofbWodetailid(griddat.getWksmWodetailid());
					
				}
			}*/
			PlmTlSpareconsumed spareConsumed = newPlmTlWofeedback.getSpareConsumed();
			PlmTlSparecostactual spareCostActual = newPlmTlWofeedback.getSpareCostActual();
			fillValues(newPlmTlWofeedback,existPlmTlWofeedback,workOrderDetailsBean);
			CommonMessage.debugMsg("After Filling tool Values");
			return monPlanDao.saveWOD(newPlmTlWofeedback,workOrderDetailsBean);
			
		}catch (ValidationExceptions e){
			
			CommonMessage.debugMsg("validation"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
			
		}
			
	}
	@Override
	public String savegrdWOD(WorkOrderDetailsBean workOrderDetailsBean,WorkOrderFormBean newworkOrderFormBean) throws Exception {
		// TODO Auto-generated method stub
		try {
			CommonMessage.debugMsg("insdie service impl of wodetails create");
			String validationsFor = "grdcreate";
//			PlmTlWorksummary plmTlWorksummary= new PlmTlWorksummary();
			
			List<WorkOrderDetailsLstBean> griddata = newworkOrderFormBean.getGrdWoBean();
			CommonMessage.debugMsg("dasf---"+griddata.size());
			PlmTlWofeedback existPlmTlWofeedback = null;
			PlmTlWofeedback newPlmTlWofeedback =null;
			List<PlmTlWofeedback > newPlmTlWofeedbackList = new ArrayList<PlmTlWofeedback >();
			workOrderDetailsBean.getGrdWoBean();
			if(griddata!= null){
				
				for(WorkOrderDetailsLstBean griddat:griddata)
				{
					newPlmTlWofeedback = new PlmTlWofeedback();
					newPlmTlWofeedback.setWofbAction(griddat.getWofbAction());
					newPlmTlWofeedback.setWofbDuration(griddat.getWofbDuration());
					newPlmTlWofeedback.setWofbWodetailid(griddat.getWksmWodetailid());
					newPlmTlWofeedback.setWofbMachineid(griddat.getMachineId());
					newPlmTlWofeedback.setWofbCompletedby(newworkOrderFormBean.getWofbCompletedby());
					newPlmTlWofeedback.setWofbCreatedby(newworkOrderFormBean.getWofbCreatedby());
					workOrderDetailsBean.setFromMonth(newworkOrderFormBean.getStartDate());
					//plmTlWorksummary.setWksmWodetailid(griddat.getWksmWodetailid());
					newPlmTlWofeedback.setSpareConsumed(workOrderDetailsBean.getSpareConsumed());
					newPlmTlWofeedback.setSpareCostActual(workOrderDetailsBean.getSpareCostActual());
					/*newworkOrderDetailsBean.setSpareConsumed(
					newworkOrderDetailsBean.setSpareCostActual(newPlmTlSparecostactual);*/
					CommonMessage.debugMsg("woDetailID-grd   :"+griddat.getWksmWodetailid());
					CommonMessage.debugMsg("plmTlWofeedback.:"+newworkOrderFormBean.getWofbCompletedby()+"      :"+newPlmTlWofeedback.getWofbCompletedby());
					CommonMessage.debugMsg("woDetailID   :"+newPlmTlWofeedback.getWofbWodetailid());
					validations.validate(newPlmTlWofeedback,"workOrderDetails",validationsFor);//com.akranta.validations.tpm.validations.workorderDetails.xml - defined rules for server side validations
					fillValues(newPlmTlWofeedback,existPlmTlWofeedback,workOrderDetailsBean);
					newPlmTlWofeedbackList.add(newPlmTlWofeedback);
				}
			}
			
			/*CommonMessage.debugMsg("outside      :"+nwPlmTlWofeedback.getWofbWodetailid());
			PlmTlSpareconsumed spareConsumed = nwPlmTlWofeedback.getSpareConsumed();
			PlmTlSparecostactual spareCostActual = nwPlmTlWofeedback.getSpareCostActual();*/
			newworkOrderFormBean.setPlmtlFeedback(newPlmTlWofeedbackList);
			CommonMessage.debugMsg("workOrderDetailsBean pmcalSpares "+workOrderDetailsBean.getPmCalendarId());
			String returnSave = monPlanDao.savegrdWOD(newPlmTlWofeedbackList,workOrderDetailsBean);
			CommonMessage.debugMsg("After Filling tool Values");
			return returnSave;
			
		}catch (ValidationExceptions e){
			
			CommonMessage.debugMsg("validation"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
			
		}
			
	}
	private PlmTlWofeedback fillValues(PlmTlWofeedback newPlmTlWofeedback,PlmTlWofeedback existPlmTlWofeedback,WorkOrderDetailsBean workOrderDetailsBean) {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("ProdStart Time   :"+workOrderDetailsBean.getWofbProdStarttime());
		CommonMessage.debugMsg("fillvalues  :");
		
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		if(newPlmTlWofeedback.getWofbFeedbackid() == null )
			newPlmTlWofeedback.setWofbCreatedon(dateTime);
		else
			newPlmTlWofeedback.setWofbCreatedon(existPlmTlWofeedback.getWofbCreatedon());
		
		CommonMessage.debugMsg(dateTime);
		newPlmTlWofeedback.setWofbModifiedon(dateTime);
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbAction()) )
			newPlmTlWofeedback.setWofbAction("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbAdjustedreading()) )
			newPlmTlWofeedback.setWofbAdjustedreading("0");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbAmcdetailid()) )
			newPlmTlWofeedback.setWofbAmcdetailid("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbAmcflag()) )
			newPlmTlWofeedback.setWofbAmcflag("-");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbCompletedby()) )
			newPlmTlWofeedback.setWofbCompletedby("{}");
		CommonMessage.debugMsg("1");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbCompleteddate()) )
			newPlmTlWofeedback.setWofbCompleteddate(Constants.passNullDate);
		CommonMessage.debugMsg("2");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbMachinetakeovertime()) )
			newPlmTlWofeedback.setWofbMachinetakeovertime(Constants.passNullDate);
		else
			newPlmTlWofeedback.setWofbMachinetakeovertime(newPlmTlWofeedback.getWofbMachinetakeovertime()+" "+workOrderDetailsBean.getWofbMachinetkeovrtime());
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbContractorcost()) )
			newPlmTlWofeedback.setWofbContractorcost("0");
		CommonMessage.debugMsg("3");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbCountermeasure()) )
			newPlmTlWofeedback.setWofbCountermeasure("{}");
		CommonMessage.debugMsg("4");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbCurrentreading()) )
			newPlmTlWofeedback.setWofbCurrentreading("0");
		CommonMessage.debugMsg("5");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbDuration()) )
			newPlmTlWofeedback.setWofbDuration("0");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbEnddate()) )
			newPlmTlWofeedback.setWofbEnddate(Constants.futureNullDate);
		else
			newPlmTlWofeedback.setWofbEnddate(newPlmTlWofeedback.getWofbEnddate()+" "+workOrderDetailsBean.getWofbEndtime());
		CommonMessage.debugMsg("6");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbFeedback()) )
			newPlmTlWofeedback.setWofbFeedback("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbFeedbackdate()) )
			newPlmTlWofeedback.setWofbFeedbackdate(dateTime);
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbIsprodstopped()) )
			newPlmTlWofeedback.setWofbIsprodstopped("-");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbAdjustedreading()) )
			newPlmTlWofeedback.setWofbAdjustedreading("0");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbMachineid()) )
			newPlmTlWofeedback.setWofbMachineid("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbManpowercost()) )
			newPlmTlWofeedback.setWofbManpowercost("0");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbMchcondition()) )
			newPlmTlWofeedback.setWofbMchcondition("-");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbNextinspectiondate()) )
			newPlmTlWofeedback.setWofbNextinspectiondate(Constants.passNullDate);
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbObservation()) )
			newPlmTlWofeedback.setWofbObservation("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbOthercost()) )
			newPlmTlWofeedback.setWofbOthercost("0");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbProdstartdate()) )
			newPlmTlWofeedback.setWofbProdstartdate(Constants.passNullDate);
		else
			newPlmTlWofeedback.setWofbProdstartdate(newPlmTlWofeedback.getWofbProdstartdate()+" "+workOrderDetailsBean.getWofbProdStarttime());
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbRemarks()) )
			newPlmTlWofeedback.setWofbRemarks("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbRescheduleflag()) )
			newPlmTlWofeedback.setWofbRescheduleflag("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbReschedulereason()) )
			newPlmTlWofeedback.setWofbReschedulereason("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbRootcause()) )
			newPlmTlWofeedback.setWofbRootcause("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbSparecost()) )
			newPlmTlWofeedback.setWofbSparecost("0");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbSpareflag()) )
			newPlmTlWofeedback.setWofbSpareflag("-");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbStartdate()) )
			newPlmTlWofeedback.setWofbStartdate(Constants.passNullDate);
		else
			newPlmTlWofeedback.setWofbStartdate(newPlmTlWofeedback.getWofbStartdate()+" "+workOrderDetailsBean.getWofbStarttime());
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbStatus()) )
			newPlmTlWofeedback.setWofbStatus("Y");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbUom()) )
			newPlmTlWofeedback.setWofbUom("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbWhywhyflag()) )
			newPlmTlWofeedback.setWofbWhywhyflag("-");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbWhywhyid()) )
			newPlmTlWofeedback.setWofbWhywhyid("{}");
		if( ! UIUtils.isValidKeyId(newPlmTlWofeedback.getWofbWodetailid()) )
			newPlmTlWofeedback.setWofbWodetailid("{}");
		CommonMessage.debugMsg("before spare");
		if(newPlmTlWofeedback.getSpareConsumed() != null )
			newPlmTlWofeedback.setSpareConsumed(refSpareConsumedFillValues(newPlmTlWofeedback,existPlmTlWofeedback));
		if(newPlmTlWofeedback.getSpareCostActual() != null )
			newPlmTlWofeedback.setSpareCostActual(refSpareCostActualFillValues(newPlmTlWofeedback,existPlmTlWofeedback));
		
		return newPlmTlWofeedback;
	}

	private PlmTlSparecostactual refSpareCostActualFillValues(PlmTlWofeedback newPlmTlWofeedback,PlmTlWofeedback existPlmTlWofeedback) {
		// TODO Auto-generated method stub
		PlmTlSparecostactual plmTlSparecostactual = newPlmTlWofeedback.getSpareCostActual();
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("costactual  PscaQuantity()  "+plmTlSparecostactual.getPscaQuantity());
			if(plmTlSparecostactual.getPscaPmcalendarid() == null )			
			{	
				plmTlSparecostactual.setPscaCreatedon(dateTime);
			}	
			plmTlSparecostactual.setPscaCreatedby(newPlmTlWofeedback.getWofbCreatedby());
			plmTlSparecostactual.setPscaModifiedon(dateTime);
			if(!UIUtils.isValidKeyId(plmTlSparecostactual.getPscaDate()))
				plmTlSparecostactual.setPscaDate(dateTime);
			if(!UIUtils.isValidKeyId(plmTlSparecostactual.getPscaDoctype()))
				plmTlSparecostactual.setPscaDoctype("{}");
			if(!UIUtils.isValidKeyId(plmTlSparecostactual.getPscaQuantity()))
				plmTlSparecostactual.setPscaQuantity("0");
			if(!UIUtils.isValidKeyId(plmTlSparecostactual.getPscaRate()))
				plmTlSparecostactual.setPscaRate("0");
			if(!UIUtils.isValidKeyId(plmTlSparecostactual.getPscaRefdocno()))
				plmTlSparecostactual.setPscaRefdocno("{}");
			if(!UIUtils.isValidKeyId(plmTlSparecostactual.getPscaRequestedby()))
				plmTlSparecostactual.setPscaRequestedby("{}");
			if(!UIUtils.isValidKeyId(plmTlSparecostactual.getPscaSitreference()))
				plmTlSparecostactual.setPscaSitreference("PM");
			if(!UIUtils.isValidKeyId(plmTlSparecostactual.getPscaSparesid()))
				plmTlSparecostactual.setPscaSparesid("{}");
			if(!UIUtils.isValidKeyId(plmTlSparecostactual.getPscaTempfield1()))
				plmTlSparecostactual.setPscaTempfield1("{}");
			if(!UIUtils.isValidKeyId(plmTlSparecostactual.getPscaTempfield2()))
				plmTlSparecostactual.setPscaTempfield2("{}");
			if(!UIUtils.isValidKeyId(plmTlSparecostactual.getPscaValue()))
				plmTlSparecostactual.setPscaValue("0");
			
		return plmTlSparecostactual;
	}

	private PlmTlSpareconsumed refSpareConsumedFillValues(PlmTlWofeedback newPlmTlWofeedback,PlmTlWofeedback existPlmTlWofeedback) {
		// TODO Auto-generated method stub
		PlmTlSpareconsumed plmTlSpareconsumed = newPlmTlWofeedback.getSpareConsumed();
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("spareconsumed PspcQuantity   " + plmTlSpareconsumed.getPspcQuantity());
			if(plmTlSpareconsumed.getPspcKeyid() == null )			
			{	
				plmTlSpareconsumed.setPspcCreatedon(dateTime);
			}	
			plmTlSpareconsumed.setPspcCreatedby(newPlmTlWofeedback.getWofbCreatedby());
			plmTlSpareconsumed.setPspcModifiedon(dateTime);
			if(!UIUtils.isValidKeyId(plmTlSpareconsumed.getPspcCost()))
				plmTlSpareconsumed.setPspcCost("0");
			if(!UIUtils.isValidKeyId(plmTlSpareconsumed.getPspcIsactivitydone()))
				plmTlSpareconsumed.setPspcIsactivitydone("Y");
			if(!UIUtils.isValidKeyId(plmTlSpareconsumed.getPspcQuantity()))
				plmTlSpareconsumed.setPspcQuantity("0");
			if(!UIUtils.isValidKeyId(plmTlSpareconsumed.getPspcPmcalendarid()))
				plmTlSpareconsumed.setPspcPmcalendarid("{}");
			if(!UIUtils.isValidKeyId(plmTlSpareconsumed.getPspcRemarks()))
				plmTlSpareconsumed.setPspcRemarks("{}");
			if(!UIUtils.isValidKeyId(plmTlSpareconsumed.getPspcSpareid()))
				plmTlSpareconsumed.setPspcSpareid("{}");
			if(!UIUtils.isValidKeyId(plmTlSpareconsumed.getPspcWodetailid()))
				plmTlSpareconsumed.setPspcWodetailid("{}");
			return plmTlSpareconsumed;
	}

	@Override
	public String cancelallocated(String workorderno, String cancelWoId, String noofActivites) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		return monPlanDao.cancelallocated(workorderno,cancelWoId,noofActivites);
	}

	@Override
	public String generateWO(String selActforWoGen, String strtDate) throws Exception {
		// TODO Auto-generated method stub
		return monPlanDao.generateWO( selActforWoGen,strtDate);
	}
	@Override
	public String generateWOForABN(String selActforWoGen, String strtDate)
			throws Exception {
		// TODO Auto-generated method stub
		return monPlanDao.generateWOForABN( selActforWoGen,strtDate);
	}
	@Override
	public String saveReschedule(String datas) throws Exception {
		// TODO Auto-generated method stub
		return monPlanDao.saveReschedule(datas);
	}

	@Override
	public List<String[]> getAllModifyForm(CommonFilter commonFilter,String weekNO)
			throws Exception {
		// TODO Auto-generated method stub
		return monPlanDao.getAllModifyForm(commonFilter,weekNO);
	}

	@Override
	public String updateallocated(String workorderno, String updateWoId,
			String allotedtocombo) throws Exception {
		// TODO Auto-generated method stub
		return monPlanDao.updateallocated(workorderno,updateWoId,allotedtocombo);
	}

	@Override
	public String modifyCompWo(WorkOrderDetailsLstBean newworkOrderDetailsBean,
			String completedBy) throws Exception {
		// TODO Auto-generated method stub
		return monPlanDao.modifyCompWo(newworkOrderDetailsBean,completedBy);
	}

	@Override
	public List<String[]> getkaizenData(CommonFilter commonFilter, String kAIZEN)
			throws Exception {
		// TODO Auto-generated method stub
		return monPlanDao.getkaizenData(commonFilter,kAIZEN);
	}

	@Override
	public String generateWOForKZN(String selActforWoKzn, String strtDate)
			throws Exception {
		// TODO Auto-generated method stub
		return monPlanDao.generateWOForKZN(selActforWoKzn,strtDate);
	}
	
	public List<MspTlIndicators> getMasterPlanActivities(MspTlIndicators mspTlIndicators) throws Exception
	{
		return mspTlIndicatorsDao.getMasterPlanActivities(mspTlIndicators);
	}
	public List<MspTlIndicatorsDtl> getIndicators(MspTlIndicatorsDtl mspTlIndicatorsDtl,MspTlIndicatorsMst mspTlIndicatorsMst) throws Exception
	{
		return mspTlIndicatorsMstDao.getMasterPlanActivities(mspTlIndicatorsDtl,mspTlIndicatorsMst);
	}
	public List<String[]> getActivitiesForSubcategory(MspTlIndicators mspTlIndicators) throws Exception
	{
		return mspTlIndicatorsDao.getActivitiesForSubcategory(mspTlIndicators);
	}
	public List<String[]> getIndicatorsForSubcategory(MspTlIndicatorsDtl mspTlIndicatorsDtl,MspTlIndicatorsMst mspTlIndicatorsMst) throws Exception
	{
		return mspTlIndicatorsMstDao.getActivitiesForSubcategory(mspTlIndicatorsDtl,mspTlIndicatorsMst);
	}
	public List<String[]> getColorsFromConfiguration() throws Exception
	{
		return mspTlMstDao.getColorsFromConfiguration();
	}
	public MspTlIndicators create(MspTlIndicators newMspTlIndicators,MspTlIndicators oldMspTlIndicators,MilestoneBean msBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		String validationsFor="create";					
		validations.validate(newMspTlIndicators,"MspIndicators",validationsFor);	
		fillValues( newMspTlIndicators,  oldMspTlIndicators);		
		return mspTlIndicatorsDao.create(newMspTlIndicators,msBean);
	}
	public MspTlIndicators update(MspTlIndicators newMspTlIndicators,MspTlIndicators oldMspTlIndicators,MilestoneBean msBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		String validationsFor="update";					
		validations.validate(newMspTlIndicators,"MspIndicators",validationsFor);	
		fillValues( newMspTlIndicators,  oldMspTlIndicators);		
		return mspTlIndicatorsDao.update(newMspTlIndicators,msBean);
	}
	public MspTlMst createMilestone(MspTlMst newMspTlMst,MspTlMst oldMspTlMst, MilestoneBean msBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		String validationsFor="create";					
		//validations.validate(msBean,"MspIndicators",validationsFor);
		//validations.validate(newMspTlMst,"MspIndicators",validationsFor);
		fillMilestoneValues( newMspTlMst,  oldMspTlMst,msBean);		
		CommonMessage.debugMsg("After Mst fill values");
		if(newMspTlMst.getMspTlDtl()!= null && newMspTlMst.getMspTlDtl().size()>0) // check for detail table data
		{
			CommonMessage.debugMsg("MilleStoneCreation");
			for(int i =0;i<newMspTlMst.getMspTlDtl().size();i++)
			{
				CommonMessage.debugMsg("MilleStoneCreation12345");
				CommonMessage.debugMsg("i : "+i);
				MspTlDtl mspTlDtl = newMspTlMst.getMspTlDtl().get(i);
				msBean.setDtlTargetDate(mspTlDtl.getMspdTargetdate());
				validations.validate(mspTlDtl,"MspIndicators",validationsFor);	
				validations.validate(msBean,"MspIndicators","TargetDate");	
				if(UIUtils.isValidKeyId(msBean.getFormMode()))
				{	
					CommonMessage.debugMsg("MilleStoneCreation1111");
					if(msBean.getFormMode().indexOf("ACTUAL")>=0)
					{
						CommonMessage.debugMsg("MilleStoneCreation0000");
						CommonMessage.debugMsg("STATUs : "+mspTlDtl.getMspdStatus());
						if(UIUtils.isValidKeyId(mspTlDtl.getMspdStatus()))
						{
							if("C".equalsIgnoreCase(mspTlDtl.getMspdStatus()))
							{
								CommonMessage.debugMsg("STATUS : "+mspTlDtl.getMspdStatus());
							  validations.validate(mspTlDtl,"MspIndicators","Actual");
							  CommonMessage.debugMsg("STATUS2 : "+mspTlDtl.getMspdStatus());
							  msBean.setDtlCompletedDate(mspTlDtl.getMspdCompletedate());
							  validations.validate(msBean,"MspIndicators","ActualDate");
							  CommonMessage.debugMsg("STATUS 3: "+mspTlDtl.getMspdStatus());
							}
						}
						
					}
				}
				if(UIUtils.isValidKeyId(mspTlDtl.getMspdActive()))
				{
					CommonMessage.debugMsg(mspTlDtl.getMspdActive() +" : "+msBean.getRevisedTargetDate());
					if(mspTlDtl.getMspdActive().equals("Y"))
					{
						msBean.setRevisedTargetDate(mspTlDtl.getMspdTempfield5());
						validations.validate(msBean,"MspIndicators","Revised");				
					}
				}
					
			}
		}
		
		return mspTlMstDao.create(newMspTlMst,msBean);
	}
	public MspTlMst updateMilestone(MspTlMst newMspTlMst,MspTlMst oldMspTlMst, MilestoneBean msBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		String validationsFor="update";					
		validations.validate(oldMspTlMst,"MspIndicators",validationsFor);	
		fillMilestoneValues( newMspTlMst,  oldMspTlMst,msBean);		
		return  null;//mspTlMstDao.update(newMspTlMst);
	}
	public MspTlIndicators assignParent(MspTlIndicators mspTlIndicators)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		
		if(!UIUtils.isValidKeyId(mspTlIndicators.getMspiParentid()))
		{
			String level = mspTlIndicators.getMspiLevel();
			if(UIUtils.isValidKeyId(level))
			{
				if(level.trim().equals("2"))
					throw new ValidationExceptions("Category");
				else
					throw new ValidationExceptions("SubCategory");
			}
		}					
		validations.validate(mspTlIndicators,"MspIndicators","assign");	
		return mspTlIndicatorsDao.assignParent(mspTlIndicators);
	}
	public MspTlIndicators delete(MspTlIndicators newMspTlIndicators)throws  Exception
	{
		return mspTlIndicatorsDao.delete(newMspTlIndicators);
	}
	public MspTlIndicatorsDtl createInd(MspTlIndicatorsDtl newMspTlIndicatorsDtl,MspTlIndicatorsDtl oldMspTlIndicatorsDtl,MspTlIndicatorsMst newMspTlIndicatorsMst,MilestoneBean msBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		try
		{
			//String chbEnableDate = null;
		String validationsFor="create";					
		validations.validate(newMspTlIndicatorsMst,"MspIndicators",validationsFor);
		validations.validate(newMspTlIndicatorsDtl,"MspIndicators",validationsFor);	
		CommonMessage.debugMsg("CreateMode     "+msBean.getMilestnFromDt());
		CommonMessage.debugMsg("CreateModetodate     "+msBean.getMilestnToDt());
		
		
		fillIndDetailValues( newMspTlIndicatorsDtl,  oldMspTlIndicatorsDtl);		
		fillIndMstValues(newMspTlIndicatorsMst);
		if(msBean.getTitle().indexOf("Activity")>=0)
		{
		validations.validate(msBean,"MspIndicators",validationsFor);
		}
		
		return mspTlIndicatorsMstDao.create(newMspTlIndicatorsMst, newMspTlIndicatorsDtl, msBean);
		}
		catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}
	}
	/*private void fillvaluesBean(MilestoneBean msBean) {
		
		if (msBean.getMilestnFromDt() == null)
			msBean.setMilestnFromDt(dateTime);

		if (msBean.getMilestnToDt() == null)
			msBean.setMilestnToDt("{}");
		
	}*/

	public MspTlIndicatorsDtl updateInd(MspTlIndicatorsDtl newMspTlIndicatorsDtl,MspTlIndicatorsDtl oldMspTlIndicatorsDtl,MspTlIndicatorsMst newMspTlIndicatorsMst,MilestoneBean msBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		try
		{
		String validationsFor="update";					
		validations.validate(newMspTlIndicatorsMst,"MspIndicators",validationsFor);
		validations.validate(newMspTlIndicatorsDtl,"MspIndicators",validationsFor);
		CommonMessage.debugMsg("milestoneservicedate     "+msBean.getMilestnFromDt());
		CommonMessage.debugMsg("milestoneservicedate     "+msBean.getMilestnToDt());
		validations.validate(msBean,"MspIndicators",validationsFor);
		fillIndDetailValues( newMspTlIndicatorsDtl,  oldMspTlIndicatorsDtl);
		//fillvaluesBean(msBean);   
		fillIndMstValues(newMspTlIndicatorsMst);
		//fillMilestoneValues( msBean);		
		return mspTlIndicatorsMstDao.update(newMspTlIndicatorsMst,newMspTlIndicatorsDtl,msBean);
	}
		catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}
	}
	public MspTlIndicatorsDtl deleteInd(MspTlIndicatorsMst mspTlIndicatorsMst,MspTlIndicatorsDtl mspTlIndicatorsDtl)throws  Exception
	{
		return mspTlIndicatorsMstDao.delete(mspTlIndicatorsMst,mspTlIndicatorsDtl);
	}
	public MspTlIndicatorsDtl assignToParent(MspTlIndicatorsDtl mspTlIndicatorsDtl)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		
		if(!UIUtils.isValidKeyId(mspTlIndicatorsDtl.getMsidParentid()))
		{
			String level = mspTlIndicatorsDtl.getMsidLevel();
			if(UIUtils.isValidKeyId(level))
			{
				if(level.trim().equals("2"))
					throw new ValidationExceptions("Category");
				else
					throw new ValidationExceptions("SubCategory");
			}
		}					
		validations.validate(mspTlIndicatorsDtl,"MspIndicators","assign");	
		return mspTlIndicatorsMstDao.assignParent(mspTlIndicatorsDtl);
	}
	public MspTlDtl deleteMilestone(MspTlDtl mspTlDtl)throws  Exception
	{
		return mspTlMstDao.deleteMilestone(mspTlDtl);
	}
	public MspTlMst deleteMilestone(MspTlMst mspTlMst,MilestoneBean msBean)throws Exception
	{
		/*if(UIUtils.isValidKeyId(msBean.getFormMode()))
		{		
			if(msBean.getFormMode().indexOf("PLAN")>=0)
			{
				mspTlMst.setMpmsPlanstartdate(msBean.getMilestoneFromDt());
				mspTlMst.setMpmsPlantilldate(msBean.getMilestoneToDt());
				mspTlMst.setMpmsActualstartdate(msBean.getMilestoneFromDt());
				mspTlMst.setMpmsActualtilldate(msBean.getMilestoneToDt());			
			}
			else if(msBean.getFormMode().indexOf("ACTUAL")>=0)
			{
				newMspTlMst.setMpmsActualstartdate(msBean.getMilestoneFromDt());
				newMspTlMst.setMpmsActualtilldate(msBean.getMilestoneToDt());
				newMspTlMst.setMpmsPlanstartdate(Constants.passNullDate);
				newMspTlMst.setMpmsPlantilldate(Constants.futureNullDate);
				newMspTlMst.setMpmsStatus("C");
				int diff = CommonFunctions.getDateDiff(newMspTlMst.getMpmsActualstartdate()+" 00:00", newMspTlMst.getMpmsActualtilldate()+" 00:00");
				newMspTlMst.setMpmsActualduration(Integer.toString(diff));
				newMspTlMst.setMpmsPlanduration("0");
			}
				
		}*/
		if(mspTlMst.getMspTlDtl()!= null && mspTlMst.getMspTlDtl().size()>0)
		{
			for(int i =0;i<mspTlMst.getMspTlDtl().size();i++)
			{
				MspTlDtl mspTlDtl = mspTlMst.getMspTlDtl().get(i);
				CommonMessage.debugMsg("Status : "+mspTlDtl.getMspdStatus());
				if(UIUtils.isValidKeyId(mspTlDtl.getMspdStatus()))
				{
					if("C".equalsIgnoreCase(mspTlDtl.getMspdStatus()))
					{
						throw new BusinessApplicationExceptions("Completed,");
					}
				}
			}
		}
		return mspTlMstDao.deleteMilestone(mspTlMst,msBean);
	}
	public MspTlIndicators select(String nodeId,String cellId) 	throws Exception 
	{
		return mspTlIndicatorsDao.select(nodeId, cellId);
	}
	public MspTlIndicatorsDtl selectIndicatorsDtl(String nodeId,String Flid) 	throws Exception
	{
		return mspTlIndicatorsMstDao.select(nodeId, Flid);
	}
	public MspTlMst selectMst(String indicatorId,String cellId,String fromDate,String toDate,String flag)throws Exception
	{
		return mspTlMstDao.selectMst(indicatorId, cellId, fromDate,toDate,flag);
	}
	public List<String[]> getPlanActual(CommonFilter commonFilter,String pillar,String masterkeyid)throws Exception
	{
		//return mspTlIndicatorsDao.getPlanActual(commonFilter,pillar);
		return mspTlIndicatorsMstDao.getPlanActual(commonFilter,pillar,masterkeyid);
	}
	public List<String[]> getAllMilestones(CommonFilter commonFilter,String indicatorId,String cellId,String mode,String fromDate) throws Exception
	{
		return mspTlMstDao.getAllMilestones(commonFilter,indicatorId,cellId,mode,fromDate);
	}
	public List<String[]> getAllHistory(String dtlId) throws Exception
	{
		return mspTlMstDao.getAllHistory(dtlId);
	}
	public  List<String[]> getSearchIndicator(String indicatorId,String indName) throws Exception
	{
		//return mspTlIndicatorsDao.getSearchIndicator(indicatorId, indName);
		return mspTlIndicatorsMstDao.getSearchIndicator(indicatorId, indName);
	}
	public List<String[]> getIndicatorsGrid(CommonFilter commonFilter,String pillar) throws Exception
	{
		//return mspTlIndicatorsDao.getIndicatorsGrid(commonFilter);
		return mspTlIndicatorsMstDao.getIndicatorsGrid(commonFilter,pillar);
	}
	public String getTitle(String cellId,String pillar,String title) throws Exception
	{
		//return mspTlIndicatorsDao.getTitle(cellId);
		return mspTlIndicatorsMstDao.getTitle(cellId,pillar, title);
	}
	public List<ComboBox> getCategoryComboList(String cellId)throws Exception
	{
		ComboFilter category = new ComboFilter();		
		category.setIdField("MSPI_KEYID");		
		category.setNameField("MSPI_NAME");
		category.setCodeField("MSPI_CODE");
		category.setTableName(TableNames.TBL_MSP_TL_INDICATORS);
		StringBuffer sb = new StringBuffer();
		sb.append(" AND MSPI_LEVEL = '1' ");
		if(UIUtils.isValidKeyId(cellId))
			sb.append(" and mspi_cellid = '"+cellId+"'");
		category.setCondSql(sb.toString());
		return commonFilterDao.fillComboValues(category);
		
	}
	public List<ComboBox> getSubCategoryComboList(String cellId,String parentId)throws Exception
	{
		ComboFilter category = new ComboFilter();		
		category.setIdField("MSPI_KEYID");		
		category.setNameField("MSPI_NAME");
		category.setCodeField("MSPI_CODE");
		category.setTableName(TableNames.TBL_MSP_TL_INDICATORS);
		StringBuffer sb = new StringBuffer();
		sb.append(" AND MSPI_LEVEL = '2' ");
		if(UIUtils.isValidKeyId(cellId))
			sb.append(" and mspi_cellid = '"+cellId+"'");
		if(UIUtils.isValidKeyId(parentId))
			sb.append(" and MSPI_PARENTID = '"+parentId+"'");
		category.setCondSql(sb.toString());
		return commonFilterDao.fillComboValues(category);
	}
	private MspTlIndicators  fillValues(MspTlIndicators newMspTlIndicators,MspTlIndicators oldMspTlIndicators)
	{
		String dateTime = CommonFunctions.dateTimeNow();
		newMspTlIndicators.setMspiActive("Y");
		newMspTlIndicators.setMspiCreatedon(dateTime);
		if(oldMspTlIndicators != null)
		{
			if(UIUtils.isValidKeyId(oldMspTlIndicators.getMspiModifiedon()))
				newMspTlIndicators.setMspiModifiedon(oldMspTlIndicators.getMspiModifiedon());
			else
				newMspTlIndicators.setMspiModifiedon(dateTime);
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiName()))
			{
				if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiName()))
					newMspTlIndicators.setMspiName("{}");
				else
					newMspTlIndicators.setMspiName(oldMspTlIndicators.getMspiName());
			}
			
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiCode()))
			{
				if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiCode()))
					newMspTlIndicators.setMspiCode("{}");
				else
					newMspTlIndicators.setMspiCode(oldMspTlIndicators.getMspiCode());
			}
			
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiRemarks()))
			{
				if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiRemarks()))
					newMspTlIndicators.setMspiRemarks("{}");
				else
					newMspTlIndicators.setMspiRemarks(oldMspTlIndicators.getMspiRemarks());
			}			
			
			
			if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiParentid()))
				newMspTlIndicators.setMspiParentid("{}");
			else
				newMspTlIndicators.setMspiParentid(oldMspTlIndicators.getMspiParentid());
			if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiFactoryid()))
				newMspTlIndicators.setMspiFactoryid("{}");
			else
				newMspTlIndicators.setMspiFactoryid(oldMspTlIndicators.getMspiFactoryid());
			if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiSectionid()))
				newMspTlIndicators.setMspiSectionid("{}");
			else
				newMspTlIndicators.setMspiSectionid(oldMspTlIndicators.getMspiSectionid());
			if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiCellid()))
				newMspTlIndicators.setMspiCellid("{}");
			else
				newMspTlIndicators.setMspiCellid(oldMspTlIndicators.getMspiCellid());
			if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiLevel()))
				newMspTlIndicators.setMspiLevel("1");
			else
			{
				newMspTlIndicators.setMspiLevel(oldMspTlIndicators.getMspiLevel());
			}
			if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiSortno()))
				newMspTlIndicators.setMspiSortno("{}");
			else
				newMspTlIndicators.setMspiSortno(oldMspTlIndicators.getMspiSortno());
			if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiCreatedby()))
				newMspTlIndicators.setMspiCreatedby("{}");
			else
				newMspTlIndicators.setMspiCreatedby(oldMspTlIndicators.getMspiCreatedby());
			
			if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiKeyid()))
				newMspTlIndicators.setMspiKeyid("{}");
			else
				newMspTlIndicators.setMspiKeyid(oldMspTlIndicators.getMspiKeyid());
			
			if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiPillar()))
				newMspTlIndicators.setMspiPillar("{}");
			else
				newMspTlIndicators.setMspiPillar(oldMspTlIndicators.getMspiPillar());
			
			if(!UIUtils.isValidKeyId(oldMspTlIndicators.getMspiTitle()))
				newMspTlIndicators.setMspiTitle("{}");
			else
				newMspTlIndicators.setMspiTitle(oldMspTlIndicators.getMspiTitle());
			
			newMspTlIndicators.setMspiTempfield("-");
			//newMspTlIndicators.setMspiTempfield2("-");
			//newMspTlIndicators.setMspiTempfield3("-");
		}
		else
		{
			newMspTlIndicators.setMspiModifiedon(dateTime);
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiName()))
				newMspTlIndicators.setMspiName("{}");
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiCode()))
				newMspTlIndicators.setMspiCode("{}");
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiRemarks()))
				newMspTlIndicators.setMspiRemarks("{}");
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiParentid()))
				newMspTlIndicators.setMspiParentid("{}");
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiFactoryid()))
				newMspTlIndicators.setMspiFactoryid("{}");
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiSectionid()))
				newMspTlIndicators.setMspiSectionid("{}");
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiCellid()))
				newMspTlIndicators.setMspiCellid("{}");
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiLevel()))
				newMspTlIndicators.setMspiLevel("1");
			else
			{
				int level = Integer.parseInt(newMspTlIndicators.getMspiLevel().trim())+1;
				newMspTlIndicators.setMspiLevel(Integer.toString(level));
			}
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiSortno()))
				newMspTlIndicators.setMspiSortno("{}");
			
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiPillar()))
				newMspTlIndicators.setMspiPillar("{}");
			if(!UIUtils.isValidKeyId(newMspTlIndicators.getMspiTitle()))
				newMspTlIndicators.setMspiTitle("{}");
			newMspTlIndicators.setMspiTempfield("-");
			//newMspTlIndicators.setMspiTempfield2("-");
			//newMspTlIndicators.setMspiTempfield3("-");
		}
		return newMspTlIndicators;
	}
	private MspTlIndicatorsMst  fillIndMstValues(MspTlIndicatorsMst newMspTlIndicatorsMst)
	{
		String dateTime = CommonFunctions.dateTimeNow();
		newMspTlIndicatorsMst.setMspiActive("Y");
		newMspTlIndicatorsMst.setMspiCreatedon(dateTime);
		newMspTlIndicatorsMst.setMspiModifiedon(dateTime);
		newMspTlIndicatorsMst.setMspiTempfield1("-");
		//newMspTlIndicatorsMst.setMspiTempfield2("-");
		//newMspTlIndicatorsMst.setMspiTempfield3("-");
		if(!UIUtils.isValidKeyId(newMspTlIndicatorsMst.getMspiFactoryid()))
			newMspTlIndicatorsMst.setMspiFactoryid("{}");
		if(!UIUtils.isValidKeyId(newMspTlIndicatorsMst.getMspiSectionid()))
			newMspTlIndicatorsMst.setMspiSectionid("{}");
		if(!UIUtils.isValidKeyId(newMspTlIndicatorsMst.getMspiCellid()))
			newMspTlIndicatorsMst.setMspiCellid("{}");
		if(!UIUtils.isValidKeyId(newMspTlIndicatorsMst.getMspiFlid()))
			newMspTlIndicatorsMst.setMspiFlid("{}");
		if(!UIUtils.isValidKeyId(newMspTlIndicatorsMst.getMspiElementid()))
			newMspTlIndicatorsMst.setMspiElementid("{}");
		if(!UIUtils.isValidKeyId(newMspTlIndicatorsMst.getMspiCreatedby()))
			newMspTlIndicatorsMst.setMspiCreatedby("{}");
		
		
		if(!UIUtils.isValidKeyId(newMspTlIndicatorsMst.getMspiTitle()))
			newMspTlIndicatorsMst.setMspiTitle("{}");
		if(!UIUtils.isValidKeyId(newMspTlIndicatorsMst.getMspiPillar()))
			newMspTlIndicatorsMst.setMspiPillar("{}");
		
		return newMspTlIndicatorsMst;
	}
	private MspTlIndicatorsDtl  fillIndDetailValues(MspTlIndicatorsDtl newMspTlIndicatorsDtl,MspTlIndicatorsDtl oldMspTlIndicatorsDtl)
	{
		String dateTime = CommonFunctions.dateTimeNow();
		newMspTlIndicatorsDtl.setMsidActive("Y");
		newMspTlIndicatorsDtl.setMsidCreatedon(dateTime);
		if(oldMspTlIndicatorsDtl != null)
		{
			if(UIUtils.isValidKeyId(oldMspTlIndicatorsDtl.getMsidModifiedon()))
				newMspTlIndicatorsDtl.setMsidModifiedon(oldMspTlIndicatorsDtl.getMsidModifiedon());
			else
				newMspTlIndicatorsDtl.setMsidModifiedon(dateTime);
			if(!UIUtils.isValidKeyId(newMspTlIndicatorsDtl.getMsidName()))
			{
				if(!UIUtils.isValidKeyId(oldMspTlIndicatorsDtl.getMsidName()))
					newMspTlIndicatorsDtl.setMsidName("{}");
				else
					newMspTlIndicatorsDtl.setMsidName(oldMspTlIndicatorsDtl.getMsidName());
			}
			
			if(!UIUtils.isValidKeyId(newMspTlIndicatorsDtl.getMsidCode()))
			{
				if(!UIUtils.isValidKeyId(oldMspTlIndicatorsDtl.getMsidCode()))
					newMspTlIndicatorsDtl.setMsidCode("{}");
				else
					newMspTlIndicatorsDtl.setMsidCode(oldMspTlIndicatorsDtl.getMsidCode());
			}
			
			if(!UIUtils.isValidKeyId(newMspTlIndicatorsDtl.getMsidRemarks()))
			{
				if(!UIUtils.isValidKeyId(oldMspTlIndicatorsDtl.getMsidRemarks()))
					newMspTlIndicatorsDtl.setMsidRemarks("{}");
				else
					newMspTlIndicatorsDtl.setMsidRemarks(oldMspTlIndicatorsDtl.getMsidRemarks());
			}			
			
			
			if(!UIUtils.isValidKeyId(oldMspTlIndicatorsDtl.getMsidParentid()))
				newMspTlIndicatorsDtl.setMsidParentid("{}");
			else
				newMspTlIndicatorsDtl.setMsidParentid(oldMspTlIndicatorsDtl.getMsidParentid());
			
			if(!UIUtils.isValidKeyId(oldMspTlIndicatorsDtl.getMsidLevel()))
				newMspTlIndicatorsDtl.setMsidLevel("1");
			else
			{
				newMspTlIndicatorsDtl.setMsidLevel(oldMspTlIndicatorsDtl.getMsidLevel());
			}
			if(!UIUtils.isValidKeyId(oldMspTlIndicatorsDtl.getMsidSortno()))
				newMspTlIndicatorsDtl.setMsidSortno("{}");
			else
				newMspTlIndicatorsDtl.setMsidSortno(oldMspTlIndicatorsDtl.getMsidSortno());
			if(!UIUtils.isValidKeyId(oldMspTlIndicatorsDtl.getMsidCreatedby()))
				newMspTlIndicatorsDtl.setMsidCreatedby("{}");
			else
				newMspTlIndicatorsDtl.setMsidCreatedby(oldMspTlIndicatorsDtl.getMsidCreatedby());
			
			if(!UIUtils.isValidKeyId(oldMspTlIndicatorsDtl.getMsidKeyid()))
				newMspTlIndicatorsDtl.setMsidKeyid("{}");
			else
				newMspTlIndicatorsDtl.setMsidKeyid(oldMspTlIndicatorsDtl.getMsidKeyid());			
			
			
			
			newMspTlIndicatorsDtl.setMsidTempfield("-");
			newMspTlIndicatorsDtl.setMsidTempfield2("-");
			newMspTlIndicatorsDtl.setMsidTempfield3("-");
		}
		else
		{
			newMspTlIndicatorsDtl.setMsidModifiedon(dateTime);
			if(!UIUtils.isValidKeyId(newMspTlIndicatorsDtl.getMsidName()))
				newMspTlIndicatorsDtl.setMsidName("{}");
			if(!UIUtils.isValidKeyId(newMspTlIndicatorsDtl.getMsidCode()))
				newMspTlIndicatorsDtl.setMsidCode("{}");
			if(!UIUtils.isValidKeyId(newMspTlIndicatorsDtl.getMsidRemarks()))
				newMspTlIndicatorsDtl.setMsidRemarks("{}");
			if(!UIUtils.isValidKeyId(newMspTlIndicatorsDtl.getMsidParentid()))
				newMspTlIndicatorsDtl.setMsidParentid("{}");
			
			if(!UIUtils.isValidKeyId(newMspTlIndicatorsDtl.getMsidLevel()))
				newMspTlIndicatorsDtl.setMsidLevel("1");
			else
			{
				int level = Integer.parseInt(newMspTlIndicatorsDtl.getMsidLevel().trim())+1;
				newMspTlIndicatorsDtl.setMsidLevel(Integer.toString(level));
			}
			if(!UIUtils.isValidKeyId(newMspTlIndicatorsDtl.getMsidSortno()))
				newMspTlIndicatorsDtl.setMsidSortno("{}");			
			
			newMspTlIndicatorsDtl.setMsidTempfield("-");
			newMspTlIndicatorsDtl.setMsidTempfield2("-");
			newMspTlIndicatorsDtl.setMsidTempfield3("-");
		}
		return newMspTlIndicatorsDtl;
	}
	private MspTlMst  fillMilestoneValues(MspTlMst newMspTlMst,MspTlMst oldMspTlMst,MilestoneBean msBean) throws ParseException
	{
		String dateTime = CommonFunctions.dateTimeNow();
		newMspTlMst.setMpmsActive("Y");
		newMspTlMst.setMpmsCreatedon(dateTime);
		newMspTlMst.setMpmsModifiedon(dateTime);
		if(UIUtils.isValidKeyId(msBean.getFormMode()))
		{		
			if(msBean.getFormMode().indexOf("PLAN")>=0)
			{
				newMspTlMst.setMpmsPlanstartdate(msBean.getMilestoneFromDt());
				newMspTlMst.setMpmsPlantilldate(msBean.getMilestoneToDt());
				newMspTlMst.setMpmsActualstartdate(msBean.getMilestoneFromDt());
				newMspTlMst.setMpmsActualtilldate(msBean.getMilestoneToDt());
				//newMspTlMst.setMpmsStatus("P");
				int diff = CommonFunctions.getDateDiff(newMspTlMst.getMpmsPlanstartdate()+" 00:00", newMspTlMst.getMpmsPlantilldate()+" 00:00");
				newMspTlMst.setMpmsPlanduration(Integer.toString(diff));
				newMspTlMst.setMpmsActualduration(Integer.toString(diff));
				
			}
			else if(msBean.getFormMode().indexOf("ACTUAL")>=0)
			{
				newMspTlMst.setMpmsActualstartdate(msBean.getMilestoneFromDt());
				newMspTlMst.setMpmsActualtilldate(msBean.getMilestoneToDt());
				newMspTlMst.setMpmsPlanstartdate(Constants.passNullDate);
				newMspTlMst.setMpmsPlantilldate(Constants.futureNullDate);
			//	newMspTlMst.setMpmsStatus("C");
				int diff = CommonFunctions.getDateDiff(newMspTlMst.getMpmsActualstartdate()+" 00:00", newMspTlMst.getMpmsActualtilldate()+" 00:00");
				newMspTlMst.setMpmsActualduration(Integer.toString(diff));
				newMspTlMst.setMpmsPlanduration("0");
			}
				
		}
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsStatus()))
			newMspTlMst.setMpmsStatus("P");
		
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsIndicatorid()))
			newMspTlMst.setMpmsIndicatorid("{}");
		
		/*if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsElementid()))
			newMspTlMst.setMpmsElementid("{}");*/
		
		
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsFactoryid()))
			newMspTlMst.setMpmsFactoryid("{}");
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsSectionid()))
			newMspTlMst.setMpmsSectionid("{}");
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsCellid()))
			newMspTlMst.setMpmsCellid("{}");
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsResponsibility()))
			newMspTlMst.setMpmsResponsibility("{}");
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsCompletedby()))
			newMspTlMst.setMpmsCompletedby("{}");
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsPlanstartweek()))
			newMspTlMst.setMpmsPlanstartweek("0");
		
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsPlanduration()))
			newMspTlMst.setMpmsPlantillweek("0");
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsActualduration()))
			newMspTlMst.setMpmsPlantillweek("0");
		
		
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsPlantillweek()))
			newMspTlMst.setMpmsPlantillweek("0");
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsActualstartweek()))
			newMspTlMst.setMpmsActualstartweek("0");
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsElementid()))
			newMspTlMst.setMpmsElementid("{}");
		
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsCreatedby()))  ///ttt
			newMspTlMst.setMpmsCreatedby("{}");
		
		
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsActive()))
			newMspTlMst.setMpmsActive("Y");
		
		CommonMessage.debugMsg("newMspTlMst.getMpmsElementid" +newMspTlMst.getMpmsElementid());
		
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsFlid()))
			newMspTlMst.setMpmsFlid("{}");
		
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsActive()))
			newMspTlMst.setMpmsActualstartweek("Y");
		
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsCreatedby()))
			newMspTlMst.setMpmsActualstartweek("{}");
		
		
		
		//getMpmsElementid  getMpmsFlid            getMpmsActive   getMpmsCreatedby
		if(!UIUtils.isValidKeyId(newMspTlMst.getMpmsActualtillweek()))
			newMspTlMst.setMpmsActualtillweek("0");
		newMspTlMst.setMpmsTempfield1("-");
		//newMspTlMst.setMpmsElementid("-");  //TTT
		
		newMspTlMst.setMpmsTempfield2("-");
		newMspTlMst.setMpmsTempfield3("-");
		newMspTlMst.setMpmsTempfield4("-");
		newMspTlMst.setMpmsTempfield5("-");
		newMspTlMst.setMpmsTempfield6("-");
		newMspTlMst.setMpmsTempfield7("-");
		newMspTlMst.setMpmsTempfield8("-");
		newMspTlMst.setMpmsTempfield9("-");
		newMspTlMst.setMpmsTempfield10("-");
		
		return newMspTlMst;
	}
	
	public Workbook getPlanActualExcel(String pillar,JSONObject jsonObject,CommonFilter commonFilter,String format) throws Exception {
		return mspTlMstDao.getPlanActualExcel(pillar,jsonObject,commonFilter,format);
	}
	@Override
	public Workbook getAllHistoryExcel(String dtlId,JSONObject jsonObject,String format) throws Exception {
		return mspTlMstDao.getAllHistoryExcel(dtlId,jsonObject,format);
	}
	public Workbook getAllIndicatorsExcel(JSONObject jsonObject,CommonFilter commonFilter,String format,String pillar)throws Exception
	{
		//return mspTlIndicatorsDao.getAllIndicatorsExcel(jsonObject,commonFilter,format);
		return mspTlIndicatorsMstDao.getAllIndicatorsExcel(jsonObject,commonFilter,format,pillar);
	}

	

	@Override
	public List<String[]> getMonthlyPlanRpt(CommonFilter commonFilter) throws Exception {
		return this.monPlanDao.getMonthlyPlanRpt(commonFilter);
	}

	@Override
	public Workbook getMonthlyPlanRptExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		return this.monPlanDao.getMonthlyPlanRptExportExcel(commonFilter,colmodel,rptFormat);
	}

	@Override
	public List<ComboBox> getActivityComboList(String cellId) throws Exception {
		ComboFilter category = new ComboFilter();		
		category.setIdField("MSPI_KEYID");		
		category.setNameField("MSPI_NAME");
		category.setCodeField("MSPI_CODE");
		category.setTableName(TableNames.TBL_MSP_TL_INDICATORS);
		StringBuffer sb = new StringBuffer();
		sb.append(" AND MSPI_LEVEL = '3' ");
		if(UIUtils.isValidKeyId(cellId))
			sb.append(" and mspi_cellid = '"+cellId+"'");
		//if(UIUtils.isValidKeyId(parentId))
			//sb.append(" and MSPI_PARENTID = '"+parentId+"'");
		category.setCondSql(sb.toString());
		return commonFilterDao.fillComboValues(category);
	}

	@Override
	public List<String[]> getfillActivity(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.monPlanDao.getfillActivity(  commonFilter );
	}

	@Override
	public String generateWOSDM(String selActforWoGen, String params)
			throws Exception {
		// TODO Auto-generated method stub
		return monPlanDao.generateWOSDM( selActforWoGen,params);
	}

	@Override
	public List<String[]> getdatapopup(String indicatorId,String flag) throws Exception {
		return this.monPlanDao.getdatapopup(indicatorId,flag);
	}

	@Override
	public MspTlIndicatorsMst create(MspTlIndicatorsMst newMspTlIndicatorsMst,MspTlIndicatorsMst existMspTlIndicatorsMst, MilestoneBean msBean)
			throws Exception {
			validations.validate(newMspTlIndicatorsMst,"MasterActual","create");
			 fillIndMstValues(newMspTlIndicatorsMst);
			
			return mspTlIndicatorsMstDao.create(newMspTlIndicatorsMst, existMspTlIndicatorsMst, msBean);
	}

	@Override
	public MspTlIndicatorsMst update(MspTlIndicatorsMst newMspTlIndicatorsMst,MspTlIndicatorsMst existMspTlIndicatorsMst, MilestoneBean msBean)
			throws Exception {
			validations.validate(newMspTlIndicatorsMst,"MasterActual","Update");
	         fillIndMstValues(newMspTlIndicatorsMst);
			
			return mspTlIndicatorsMstDao.update(newMspTlIndicatorsMst, existMspTlIndicatorsMst, msBean);
	}

	@Override
	public MspTlIndicatorsMst selectMasKeyid(String masterkeyid) throws Exception 
	{
		
		return mspTlIndicatorsMstDao.selectMasKeyid(masterkeyid);
	}

	@Override
	public List<String[]> getplandate(String nodeId) throws Exception {
		
		return mspTlIndicatorsMstDao.getplandate(nodeId);
		
	}

	

	


	

	
	

}
