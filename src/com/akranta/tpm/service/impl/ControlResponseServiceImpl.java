package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.ControlResponsePlanBean;
import com.akranta.tpm.dao.ControlResponseDao;
import com.akranta.tpm.dao.GenTlControlandresponseplanDao;
import com.akranta.tpm.dao.impl.ControlResponseDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlControlandresponseplanDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlControlandresponseplan;
import com.akranta.tpm.service.ControlResponseService;
import com.akranta.tpm.service.api.ControlResponseplanserviceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class ControlResponseServiceImpl implements ControlResponseService{
	private ControlResponseDao controlResponseDao;
	private GenTlControlandresponseplanDao genTlControlandresponseplanDao;
	private Validations validations;
	private ControlResponseplanserviceApi serviceApi;
	
public ControlResponseServiceImpl(DBActionTemplate dbActionTemplate) {
	controlResponseDao=new ControlResponseDaoImpl(dbActionTemplate);
	genTlControlandresponseplanDao=new GenTlControlandresponseplanDaoImpl(dbActionTemplate);
	validations = new Validations();
	
}
public void ControlResponseServiceImplJwt(String JwtToken){
	try{
		controlResponseDao.controlResponseDaoImplJwt(JwtToken);
		serviceApi = new ControlResponseplanserviceApi(JwtToken);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}}
	@Override
	public List<String[]> controlResponseReport(CommonFilter commonFilter)
			throws Exception {
		
		return this.controlResponseDao.controlResponseReport(commonFilter);
	}
	@Override
	public GenTlControlandresponseplan create(GenTlControlandresponseplan newGenTlControlandresponseplan,GenTlControlandresponseplan existGenTlControlandresponseplan,ControlResponsePlanBean controlresponseplanBean) throws Exception {
		
		CommonMessage.debugMsg(" Inside Service Impl ");
		
		String validationsFor = "create";
		String xml = "ControlandResponse";
		
		validations.validate(newGenTlControlandresponseplan, xml, validationsFor);
		
		fillvalues(newGenTlControlandresponseplan,existGenTlControlandresponseplan,controlresponseplanBean);
		//return genTlControlandresponseplanDao.create(newGenTlControlandresponseplan);
		return serviceApi.saveConrolResponsePlan(newGenTlControlandresponseplan);
	
	}
	@Override
	public GenTlControlandresponseplan update(GenTlControlandresponseplan newGenTlControlandresponseplan,GenTlControlandresponseplan existGenTlControlandresponseplan,ControlResponsePlanBean controlresponseplanBean) throws Exception {
		
		CommonMessage.debugMsg(" Inside Service Impl ");
		
		String validationsFor = "update";
		String xml = "ControlandResponse";
		
		validations.validate(newGenTlControlandresponseplan, xml, validationsFor);
		
		fillvalues(newGenTlControlandresponseplan,existGenTlControlandresponseplan,controlresponseplanBean);
		//return genTlControlandresponseplanDao.update(newGenTlControlandresponseplan);
		return serviceApi.saveConrolResponsePlan(newGenTlControlandresponseplan);
	}
	@Override
	public GenTlControlandresponseplan delete(GenTlControlandresponseplan newGenTlControlandresponseplan)
			throws Exception {
		// TODO Auto-generated method stub
		return genTlControlandresponseplanDao.delete(newGenTlControlandresponseplan);
	}
	@Override
	public GenTlControlandresponseplan getAllFillControl(String controlId)
			throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg(" Inside ServiceImpl :: Keyid "+controlId);
//		 return this.genTlControlandresponseplanDao.getAllFillControl(controlId);
		return serviceApi.getById(controlId);
	}
	@Override
	public Workbook getConresplnExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(" Inside Service Impl :: ");
		return genTlControlandresponseplanDao.getConresplnExcel(colmodel,format, commonFilter);
	}
	
	
	private void fillvalues(GenTlControlandresponseplan newGenTlControlandresponseplan,GenTlControlandresponseplan existGenTlControlandresponseplan,ControlResponsePlanBean controlresponseplanBean) {
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		CommonMessage.debugMsg(" Inside Service Impl  :: fillvalues ");
		
		if(newGenTlControlandresponseplan.getCarpFlid()== null)
			newGenTlControlandresponseplan.setCarpFlid("{}");
		
		if(newGenTlControlandresponseplan.getCarpElementid()== null)
			newGenTlControlandresponseplan.setCarpElementid("{}");
		
		if(newGenTlControlandresponseplan.getCarpProcessstep()== null)
			newGenTlControlandresponseplan.setCarpProcessstep("{}");
		
		if(newGenTlControlandresponseplan.getCarpKpov()== null)
			newGenTlControlandresponseplan.setCarpKpov("{}");
		
		if(newGenTlControlandresponseplan.getCarpFrequency()== null)
			newGenTlControlandresponseplan.setCarpFrequency("{}");
		
		if(newGenTlControlandresponseplan.getCarpWhererecorded()== null)
			newGenTlControlandresponseplan.setCarpWhererecorded("{}");
		
		if(newGenTlControlandresponseplan.getCarpControllimits()== null)
			newGenTlControlandresponseplan.setCarpControllimits("{}");
		
		if(newGenTlControlandresponseplan.getCarpMeasurementmethod()== null)
			newGenTlControlandresponseplan.setCarpMeasurementmethod("{}");
		
		if(newGenTlControlandresponseplan.getCarpWhomeasures()== null)
			newGenTlControlandresponseplan.setCarpWhomeasures("{}");
		
		if(newGenTlControlandresponseplan.getCarpDecisionrule()== null)
			newGenTlControlandresponseplan.setCarpDecisionrule("{}");
		
		if(newGenTlControlandresponseplan.getCarpUom()== null)
			newGenTlControlandresponseplan.setCarpUom("{}");
		
		if(newGenTlControlandresponseplan.getCarpSpeclimits()== null)
			newGenTlControlandresponseplan.setCarpSpeclimits("{}");
		
		if(newGenTlControlandresponseplan.getCarpSamplesize()== null)
			newGenTlControlandresponseplan.setCarpSamplesize("{}");
		
		if(newGenTlControlandresponseplan.getCarpInformto()== null)
			newGenTlControlandresponseplan.setCarpInformto("{}");
		
		if(newGenTlControlandresponseplan.getCarpCorrectiveaction()== null)
			newGenTlControlandresponseplan.setCarpCorrectiveaction("{}");
		
		if(newGenTlControlandresponseplan.getCarpTempfield1()== null)
			newGenTlControlandresponseplan.setCarpTempfield1("-");
		
		if(newGenTlControlandresponseplan.getCarpTempfield2()== null)
			newGenTlControlandresponseplan.setCarpTempfield2("-");
		
		if(newGenTlControlandresponseplan.getCarpTempfield3()== null)
			newGenTlControlandresponseplan.setCarpTempfield3("-");
		
		if(newGenTlControlandresponseplan.getCarpTempfield4()== null)
			newGenTlControlandresponseplan.setCarpTempfield4("-");
		
		if(newGenTlControlandresponseplan.getCarpTempfield5()== null)
			newGenTlControlandresponseplan.setCarpTempfield5("-");
		
		if(newGenTlControlandresponseplan.getCarpActive()== null)
			newGenTlControlandresponseplan.setCarpActive("Y");
		
		if(newGenTlControlandresponseplan.getCarpCreatedby()== null)
			newGenTlControlandresponseplan.setCarpCreatedby("{}");
		
		if(newGenTlControlandresponseplan.getCarpCreatedon()== null)
			newGenTlControlandresponseplan.setCarpCreatedon(dateTime);
		
		if(newGenTlControlandresponseplan.getCarpModifiedon()== null)
			newGenTlControlandresponseplan.setCarpModifiedon(dateTime);
		
		
		
	}
	


}
