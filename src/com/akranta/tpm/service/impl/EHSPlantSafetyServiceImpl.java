package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.AbnormalityBean;
import com.akranta.tpm.bean.SheTlPlantSafetyBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;

import com.akranta.tpm.dao.SheTlPlantSafetymstDao;
import com.akranta.tpm.dao.SheTlPlantSafetydtlDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import com.akranta.tpm.dao.impl.SheTlPlantSafetydtlDaoImpl;
import com.akranta.tpm.dao.impl.SheTlPlantSafetymstDaoImpl;
import com.akranta.tpm.exportreport.PlanSafetyExcelTemplate;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.model.SheTlPlantSafetydtl;
import com.akranta.tpm.model.SheTlPlantSafetymst;
import com.akranta.tpm.service.EHSPlantSafetyService;
import com.akranta.tpm.service.api.SheTlPlantSafetyServiceApi;
import com.akranta.tpm.service.api.AbnormalityServiceApi;

import com.akranta.tpm.service.api.KaizenBankServiceAPI;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

import net.sf.json.JSONObject;

public class EHSPlantSafetyServiceImpl implements EHSPlantSafetyService{

	
	private SheTlPlantSafetydtlDao sheTlPlantSafetydtlDao;
	private SheTlPlantSafetymstDao sheTlPlantSafetymstDao;
	private  SheTlPlantSafetyServiceApi sheTlPlantSafetyServiceApi;
	 private AbnormalityServiceApi abnormalatiyserviceapi;
	 private KaizenBankServiceAPI kaizenbankserviceapi;
	private Validations validations;
	private CommonFilterDao commonFilterDao;
	DBActionTemplate dbActionTemplate;
	public EHSPlantSafetyServiceImpl(DBActionTemplate dbActionTemplate)
	{
		sheTlPlantSafetymstDao =  new SheTlPlantSafetymstDaoImpl(dbActionTemplate);
		sheTlPlantSafetydtlDao =  new SheTlPlantSafetydtlDaoImpl(dbActionTemplate);
		commonFilterDao =  new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
		this.dbActionTemplate = dbActionTemplate;

	}
	
	public void EHSPlantSafetyServiceImplJwt(String JwtToken){
    	try{
    		sheTlPlantSafetymstDao.SheTlPlantSafetymstDaoImplJwt(JwtToken);
    		sheTlPlantSafetyServiceApi = new SheTlPlantSafetyServiceApi(JwtToken);
    		abnormalatiyserviceapi    = new  AbnormalityServiceApi (JwtToken);
    		kaizenbankserviceapi = new KaizenBankServiceAPI (JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	@Override
	public SheTlPlantSafetymst create(SheTlPlantSafetymst newSheTlPlantSafetymst,SheTlPlantSafetymst existSheTlPlantSafetymst,SheTlPlantSafetyBean sheTlPlantSafetyBean)throws ValidationExceptions, BusinessApplicationExceptions,Exception {
		String validationsFor = "create";
		//validations.validate(  newSheTlPlantSafetymst,"SafetyInspection","create");		
		newSheTlPlantSafetymst=fillValues( newSheTlPlantSafetymst,existSheTlPlantSafetymst);
		//return  sheTlPlantSafetymstDao.create(newSheTlPlantSafetymst) ;
		return sheTlPlantSafetyServiceApi.savePlantSafety  (newSheTlPlantSafetymst) ;
	}

	
	@Override
	public SheTlPlantSafetymst update(SheTlPlantSafetymst newSheTlPlantSafetymst,SheTlPlantSafetymst existSheTlPlantSafetymst,SheTlPlantSafetyBean sheTlPlantSafetyBean)throws ValidationExceptions, BusinessApplicationExceptions,Exception {
		// TODO Auto-generated method stub
		validations.validate(  newSheTlPlantSafetymst,"SafetyInspection","update");		
		newSheTlPlantSafetymst=fillValues( newSheTlPlantSafetymst,existSheTlPlantSafetymst);
		//return  sheTlPlantSafetymstDao.update( newSheTlPlantSafetymst) ;
		return sheTlPlantSafetyServiceApi.savePlantSafety  (newSheTlPlantSafetymst) ;
	}

	@Override
	public SheTlPlantSafetymst delete(SheTlPlantSafetymst newSheTlPlantSafetymst,SheTlPlantSafetydtl sheTlPlantSafetydtl)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			return   sheTlPlantSafetymstDao.delete(newSheTlPlantSafetymst,sheTlPlantSafetydtl);	
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage());
		}	
	}
	@Override
	public List<String[]> getCheck(String keyid,String auditflid,String auditdate,String type) throws Exception {
		return this.sheTlPlantSafetymstDao.getCheck(keyid,auditflid,auditdate,type);
	}
	@Override
	public String singleResponsiblty(String detailkeyid, String responsevalue, String targetdate,SheTlPlantSafetymst newSheTlPlantSafetymst,SheTlPlantSafetydtl sheTlPlantSafetydtl) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		//return   sheTlPlantSafetymstDao.singleResponsiblty(detailkeyid,responsevalue,targetdate,newSheTlPlantSafetymst,sheTlPlantSafetydtl);
		return   sheTlPlantSafetyServiceApi.singleResponsiblty(detailkeyid,responsevalue,targetdate,newSheTlPlantSafetymst,sheTlPlantSafetydtl);
	}
	@Override
	public String updateActionCLosure(String detailkeyid, String responsevalue, String targetdate,String revisedate,String status,String recomndaction,String remarks,String abnKeyid) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		//return   sheTlPlantSafetymstDao.updateActionClosure(detailkeyid,responsevalue,targetdate,revisedate,status,recomndaction,remarks,abnKeyid);
		return   sheTlPlantSafetyServiceApi.updateActionClosure(detailkeyid,responsevalue,targetdate,revisedate,status,recomndaction,remarks,abnKeyid);
	}

	@Override
	public SheTlPlantSafetymst getvaluesmst(String keyid) throws Exception {
		// TODO Auto-generated method stub
		//return this.sheTlPlantSafetymstDao.getvaluesmst(keyid);
		return this.sheTlPlantSafetyServiceApi.getPlantSafetyByKeyid(keyid);
	}
	@Override
	public List<String[]> deletedtl(String dtlid) throws Exception {
		return this.sheTlPlantSafetymstDao.deletedtl(dtlid);
	}
	@Override
	public SheTlPlantSafetydtl getvaluesdtl(String keyid) throws Exception {
		// TODO Auto-generated method stub
		//return this.sheTlPlantSafetymstDao.getvaluesdtl(keyid);
		return this.sheTlPlantSafetyServiceApi.getvaluesdtl(keyid);
	}
	@Override
	public List<String[]> getSafetyDetailsList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sheTlPlantSafetymstDao.getSafetyDetailsList(commonFilter);
	}
	@Override
	public Workbook AuditExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,String fromat, String type, String formmode ) throws Exception {
		// TODO Auto-generated method stub
		 return sheTlPlantSafetymstDao.getAuditExportExcel(commonFilter,tblJSONObj,fromat,type,formmode);
	}

	@Override
	public List<String[]> getSafetygridDetail(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sheTlPlantSafetymstDao.getSafetygridDetail(commonFilter);
	}
	@Override
	public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid,String empid) throws NoDataFoundException, Exception {
		// TODO Auto-generated method stub
		return   sheTlPlantSafetymstDao.getElementId(loginflid,loginlevel,loginElementid,empid);
	}
	@Override
	public List<String[]> safetyPopup(CommonFilter commonFilter)throws Exception {
		return this.sheTlPlantSafetymstDao.safetyPopup(commonFilter);
	}
	@Override
	public 	List<String[]> ExitsAuditData(String combotype, String flId, String datevalue) throws Exception {
		// TODO Auto-generated method stub
		return   sheTlPlantSafetymstDao.getExistAuditRecord(combotype,flId,datevalue);
	}
	@Override
	public List<String[]> resupdate(String dtlid,String Resid,String targetdate)throws Exception {
		//return this.sheTlPlantSafetymstDao.resupdate(dtlid,Resid,targetdate);
		return this.sheTlPlantSafetyServiceApi.resUpdate(dtlid,Resid,targetdate);
	}
	@Override
	public List<String[]> verifyupdate(String dtlid,String Verid,String verifydate)throws Exception {
		//return this.sheTlPlantSafetymstDao.resupdate(dtlid,Verid,verifydate);
		return this.sheTlPlantSafetyServiceApi.verifyUpdate(dtlid,Verid,verifydate);
	}
	@Override
	public String getResponse(String modiefiedid) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * String res=
		 * dbActionTemplate.getSingleValue("SHE_TL_PLANTSAFETYDTL","SPSD_RESPONSIBILITY"
		 * ,"SPSD_KEYID", modiefiedid); System.out.println("res"+res); return res;
		 */
		 return sheTlPlantSafetyServiceApi.getResponsibility (modiefiedid);
	}
	@Override
	public List<String> getCheckDeleteList(String detailkeyid,String apmdelete) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		//return sheTlPlantSafetymstDao.getCheckDeleteList(detailkeyid,apmdelete);
		return sheTlPlantSafetyServiceApi.checkDeleteList(detailkeyid,apmdelete);
	}

	@Override
	public String getObser(String modiefiedid) throws SQLException {
	
		// TODO Auto-generated method stub
		//String res= dbActionTemplate.getSingleValue("SHE_TL_PLANTSAFETYDTL","SPSD_OBSERVATIONS","SPSD_KEYID", modiefiedid);
			//return res;
		return sheTlPlantSafetyServiceApi.getObservations(modiefiedid);
		}
	@Override
	public String UpdateVerfification(String detailkeyid, String responsevalue, String targetdate,
			String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		//return   sheTlPlantSafetymstDao.UpdateVerfification(detailkeyid,responsevalue,targetdate,verifystatus,remarks);
		
		return   sheTlPlantSafetyServiceApi.updateVerification(detailkeyid,responsevalue,targetdate,verifystatus,remarks);
	}
	

	private SheTlPlantSafetymst fillValues(SheTlPlantSafetymst newSheTlPlantSafetymst,SheTlPlantSafetymst existSheTlPlantSafetymst) {
		
		String dateTime = CommonFunctions.pg_dateTimeNow();		
		if( ! UIUtils.isValidKeyId(newSheTlPlantSafetymst.getSpsmKeyid()))
		{
		newSheTlPlantSafetymst.setSpsmActive("Y");
		newSheTlPlantSafetymst.setSpsmCreatedon(dateTime);
		newSheTlPlantSafetymst.setSpsmModifiedon(dateTime);
		}
		else{
			/*newSheTlPlantSafetymst.setSpsmCreatedon(existSheTlPlantSafetymst.setSdvamCreatedon());
			newSheTlPlantSafetymst.setSpsmCreatedon(existSheTlPlantSafetymst.setSdvamModifiedon());*/
			newSheTlPlantSafetymst.setSpsmActive("Y");
			newSheTlPlantSafetymst.setSpsmCreatedon(dateTime);
			newSheTlPlantSafetymst.setSpsmModifiedon(dateTime);
		}
		
		if(newSheTlPlantSafetymst.getSpsmFlid() == null )
			newSheTlPlantSafetymst.setSpsmFlid("{}");
		
		String date = newSheTlPlantSafetymst.getSpsmDate();
		newSheTlPlantSafetymst.setSpsmDate(CommonFunctions.pg_getDateTimeFromDate(date));
		
		if(newSheTlPlantSafetymst.getSpsmDate() == null )
			newSheTlPlantSafetymst.setSpsmDate(dateTime);
		
		if(newSheTlPlantSafetymst.getSpsmDoneby() == null )
			newSheTlPlantSafetymst.setSpsmDoneby("{}");
		
		if(newSheTlPlantSafetymst.getSpsmType() == null )
			newSheTlPlantSafetymst.setSpsmType("{}");
		
		if(newSheTlPlantSafetymst.getSpsmMembers() == null )
			newSheTlPlantSafetymst.setSpsmMembers("{}");
		
		if(newSheTlPlantSafetymst.getSpsmRemarks() == null )
			newSheTlPlantSafetymst.setSpsmRemarks("{}");
		
		if(newSheTlPlantSafetymst.getSpsmTempfield1() == null )
			newSheTlPlantSafetymst.setSpsmTempfield1("-");
		
		if(newSheTlPlantSafetymst.getSpsmTempfield2() == null )
			newSheTlPlantSafetymst.setSpsmTempfield2("-");
		
		if(newSheTlPlantSafetymst.getSpsmTempfield3() == null )
			newSheTlPlantSafetymst.setSpsmTempfield3("-");		
		
		if(newSheTlPlantSafetymst.getSpsmTempfield4() == null )
			newSheTlPlantSafetymst.setSpsmTempfield4("-");
		
		if(newSheTlPlantSafetymst.getSpsmTempfield5() == null )
			newSheTlPlantSafetymst.setSpsmTempfield5("-");
		
		newSheTlPlantSafetymst.setSafetyDetails(fillValuesSafetyDetails(newSheTlPlantSafetymst,existSheTlPlantSafetymst));
		return newSheTlPlantSafetymst;	
		
	}
	private List<SheTlPlantSafetydtl> fillValuesSafetyDetails(SheTlPlantSafetymst newSheTlPlantSafetymst,SheTlPlantSafetymst existSheTlPlantSafetymst) {
		
		List<SheTlPlantSafetydtl> sheTlPlantSafetydtlList=newSheTlPlantSafetymst.getSafetyDetails();
		List<SheTlPlantSafetydtl> sheTlPlantSafetydtlListNew=new ArrayList<SheTlPlantSafetydtl>();
		for(int i=0;i<sheTlPlantSafetydtlList.size();i++){
			SheTlPlantSafetydtl newSheTlPlantSafetydtl=sheTlPlantSafetydtlList.get(i);
			String dateTime = CommonFunctions.pg_dateTimeNow();		
			if( ! UIUtils.isValidKeyId(newSheTlPlantSafetydtl.getSpsdKeyid()))
			{
				newSheTlPlantSafetydtl.setSpsdActive("Y");
				newSheTlPlantSafetydtl.setSpsdCreatedon(dateTime);
				newSheTlPlantSafetydtl.setSpsdModifiedon(dateTime);
			}
			else{
				newSheTlPlantSafetydtl.setSpsdActive("Y");
				newSheTlPlantSafetydtl.setSpsdCreatedon(dateTime);
				newSheTlPlantSafetydtl.setSpsdModifiedon(dateTime);
			} 
		
				if( newSheTlPlantSafetydtl.getSpsdSpsmKeyid() == null )
					newSheTlPlantSafetydtl.setSpsdSpsmKeyid("{}");
				
				if (newSheTlPlantSafetydtl.getSpsdObserve() == null)
					newSheTlPlantSafetydtl.setSpsdObserve("{}");
	
				if (newSheTlPlantSafetydtl.getSpsdType() == null)
					newSheTlPlantSafetydtl.setSpsdType("{}");
				
				if (newSheTlPlantSafetydtl.getSpsdRelatedto() == null)
					newSheTlPlantSafetydtl.setSpsdRelatedto("{}");
				
				if (newSheTlPlantSafetydtl.getSpsdRecomAction() == null)
					newSheTlPlantSafetydtl.setSpsdRecomAction("{}");
				
				if (newSheTlPlantSafetydtl.getSpsdCorrectAction() == null)
					newSheTlPlantSafetydtl.setSpsdCorrectAction("-");
				
				if (newSheTlPlantSafetydtl.getSpsdPreventiveAction() == null)
					newSheTlPlantSafetydtl.setSpsdPreventiveAction("{}");
				
				if (newSheTlPlantSafetydtl.getSpsdPreventiveAction() == null)
					newSheTlPlantSafetydtl.setSpsdPreventiveAction("{}");
				
				if (newSheTlPlantSafetydtl.getSpsdResponsibilty() == null)
					newSheTlPlantSafetydtl.setSpsdResponsibilty("{}");
				
				if (newSheTlPlantSafetydtl.getSpsdResponRemarks() == null)
					newSheTlPlantSafetydtl.setSpsdResponRemarks("-");
				
				if (newSheTlPlantSafetydtl.getSpsdStatus() == null)
					newSheTlPlantSafetydtl.setSpsdStatus("{}");
				
				String date = newSheTlPlantSafetydtl.getSpsdTargetDate();
				newSheTlPlantSafetydtl.setSpsdTargetDate(CommonFunctions.pg_getDateTimeFromDate(date));
				
				if (newSheTlPlantSafetydtl.getSpsdTargetDate() == null)
					newSheTlPlantSafetydtl.setSpsdTargetDate(dateTime);
				
				if (newSheTlPlantSafetydtl.getSpsdActiontaken() == null)
					newSheTlPlantSafetydtl.setSpsdActiontaken("{}");
				
				if (newSheTlPlantSafetydtl.getSpsdCompletedby() == null)
					newSheTlPlantSafetydtl.setSpsdCompletedby("{}");
				
				String date1 = newSheTlPlantSafetydtl.getSpsdCompletedate();
				newSheTlPlantSafetydtl.setSpsdCompletedate(CommonFunctions.pg_getDateTimeFromDate(date1));
				
				
				if (newSheTlPlantSafetydtl.getSpsdCompletedate() == null)
					newSheTlPlantSafetydtl.setSpsdCompletedate(Constants.pgPassNullDateTime);
				
				if (newSheTlPlantSafetydtl.getSpsdCompleteRemarks() == null)
					newSheTlPlantSafetydtl.setSpsdCompleteRemarks("-");
				
				
				if (newSheTlPlantSafetydtl.getSpsdVerified() == null)
					newSheTlPlantSafetydtl.setSpsdVerified("-");
				
				if (newSheTlPlantSafetydtl.getSpsdVerifiedBy()== null)
					newSheTlPlantSafetydtl.setSpsdVerifiedBy("{}");
				
				String date2 = newSheTlPlantSafetydtl.getSpsdVerifiedDate();
				newSheTlPlantSafetydtl.setSpsdVerifiedDate(CommonFunctions.pg_getDateTimeFromDate(date2));
				
				
				if (newSheTlPlantSafetydtl.getSpsdVerifiedDate()== null)
					newSheTlPlantSafetydtl.setSpsdVerifiedDate(Constants.pgPassNullDateTime);
				
				if (newSheTlPlantSafetydtl.getSpsdVerifiedRemarks() == null)
					newSheTlPlantSafetydtl.setSpsdVerifiedRemarks("-");
				
				if (newSheTlPlantSafetydtl.getSpsdTempfield1() == null)
					newSheTlPlantSafetydtl.setSpsdTempfield1("-");
				
				if (newSheTlPlantSafetydtl.getSpsdTempfield2() == null)
					newSheTlPlantSafetydtl.setSpsdTempfield2("-");
				
				if (newSheTlPlantSafetydtl.getSpsdTempfield2() == null)
					newSheTlPlantSafetydtl.setSpsdTempfield2("-");
				
				if (newSheTlPlantSafetydtl.getSpsdTempfield3() == null)
					newSheTlPlantSafetydtl.setSpsdTempfield3("-");
				
				if (newSheTlPlantSafetydtl.getSpsdTempfield4() == null)
					newSheTlPlantSafetydtl.setSpsdTempfield4("-");
				
				if (newSheTlPlantSafetydtl.getSpsdTempfield5() == null)				
					newSheTlPlantSafetydtl.setSpsdTempfield5("-");
				
				if (newSheTlPlantSafetydtl.getSpsdCreatedby() == null)				
					newSheTlPlantSafetydtl.setSpsdCreatedby("{}");
				
				String date3 = newSheTlPlantSafetydtl.getSpsdReviseddate();
				newSheTlPlantSafetydtl.setSpsdReviseddate(CommonFunctions.pg_getDateTimeFromDate(date3));
				
				
				if (newSheTlPlantSafetydtl.getSpsdReviseddate() == null)				
					newSheTlPlantSafetydtl.setSpsdReviseddate(Constants.pgPassNullDateTime);
				
				
				sheTlPlantSafetydtlListNew.add(newSheTlPlantSafetydtl);
			}
				return sheTlPlantSafetydtlList;
	}

	@Override
	public String getdetailvalue(String keyid) throws SQLException {
		// TODO Auto-generated method stub
		//return  sheTlPlantSafetymstDao.getdetailvalue( keyid) ;
		return sheTlPlantSafetyServiceApi.getDetailValue(keyid);
	}

	@Override
	public List<String[]> getSafetyReportDetails(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return  sheTlPlantSafetymstDao.getSafetyReportDetails(commonFilter);
	}
	
	public Workbook getPlantSafetyExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws IOException, SQLException, Exception {
		// TODO Auto-generated method stub
		return sheTlPlantSafetymstDao.getPlantSafetyExcel(colmodel,format, commonFilter);
	}

	@Override
	public String Response(String modiefiedid) throws SQLException {
		// TODO Auto-generated method stub
		//String res= dbActionTemplate.getSingleValue("SHE_TL_PLANTSAFETYDTL","SPSD_RESPREMARKS","SPSD_KEYID", modiefiedid);
		//return res;
		return sheTlPlantSafetyServiceApi.getResponseRemarks(modiefiedid);
	}
	
	public Workbook PlanSafetyExcelView(String plansafetykeyid, String flid,String format,String path)throws Exception{
		
	    List<String[]> PlansafetyData=sheTlPlantSafetymstDao.PlansafeList(plansafetykeyid,flid);
	    List<String[]> Plansafafetydetaildata=sheTlPlantSafetymstDao.PlansafeDetailList(plansafetykeyid,flid);
	    List<String[]> PlanSafetyVerify=sheTlPlantSafetymstDao.PlanSafetyVerify(plansafetykeyid,flid);
		Workbook wb=(new PlanSafetyExcelTemplate(dbActionTemplate)).fillValues(PlansafetyData,Plansafafetydetaildata,PlanSafetyVerify,plansafetykeyid,path);
		return wb;
	}

	@Override
	public String getMaster(String modiefiedid) throws SQLException {
		// TODO Auto-generated method stub
		//String res= dbActionTemplate.getSingleValue("SHE_TL_PLANTSAFETYDTL","SPSD_SPSM_KEYID","SPSD_KEYID", modiefiedid);
		//return res;
		return sheTlPlantSafetyServiceApi.getMasterKeyid(modiefiedid);

	}

	@Override
	public AbnTlAbnormality create(AbnTlAbnormality abnTlAbnormality) throws Exception {
		// TODO Auto-generated method stub
		fillValues(abnTlAbnormality);
		//return sheTlPlantSafetymstDao.createPsiAbn(abnTlAbnormality);	
		return abnormalatiyserviceapi.insertRecord(abnTlAbnormality);
	}

	private AbnTlAbnormality fillValues(AbnTlAbnormality newAbnTlAbnormality) throws BusinessApplicationExceptions 
	{
			CommonFunctions.debugMsg("Inside fill Values");
			newAbnTlAbnormality.setAbnmActive("Y");
			String dateTime = CommonFunctions.pg_dateTimeNow();
			String currentDate = CommonFunctions.getDate();
			CommonFunctions.debugMsg("Inside fill Values"+currentDate);
			
				newAbnTlAbnormality.setAbnmCreatedon(dateTime);			
							
		

			newAbnTlAbnormality.setAbnmModifiedon(dateTime);
			System.out.println("fill values:");
			
			 newAbnTlAbnormality.setAbnmMultipleabn("N");
			 
			if( newAbnTlAbnormality.getAbnmAssemblyid() == null )
				newAbnTlAbnormality.setAbnmAssemblyid("{}");
		
			if( newAbnTlAbnormality.getAbnmBlockdiagramref() == null )
				newAbnTlAbnormality.setAbnmBlockdiagramref("{}");
			
			if( newAbnTlAbnormality.getAbnmCategoryid() == null )
				newAbnTlAbnormality.setAbnmCategoryid("{}");
			
			//if( newAbnTlAbnormality.getAbnmCellid() == null )
				newAbnTlAbnormality.setAbnmCellid("{}");
			
			if( newAbnTlAbnormality.getAbnmCompletedby() == null )
				newAbnTlAbnormality.setAbnmCompletedby("{}");
			
			if( newAbnTlAbnormality.getAbnmCountermeasure() == null )
				newAbnTlAbnormality.setAbnmCountermeasure("{}");
			
			if( newAbnTlAbnormality.getAbnmDate() == null )
				newAbnTlAbnormality.setAbnmDate(currentDate);
			
			if( newAbnTlAbnormality.getAbnmCreatedby() == null )
				newAbnTlAbnormality.setAbnmCreatedby("{}");
			
			if( newAbnTlAbnormality.getAbnmDescription() == null )
				newAbnTlAbnormality.setAbnmDescription("{}");
			
			if( newAbnTlAbnormality.getAbnmDetailedesc() == null )
				newAbnTlAbnormality.setAbnmDetailedesc("{}");
			
			
			
			if( newAbnTlAbnormality.getAbnmDetectiondate() == null )
				newAbnTlAbnormality.setAbnmDetectiondate(dateTime);
			
			if( newAbnTlAbnormality.getAbnmDowntime() == null )
				newAbnTlAbnormality.setAbnmDowntime("0");
			
			if( newAbnTlAbnormality.getAbnmEquipmentid() == null )
				newAbnTlAbnormality.setAbnmEquipmentid("{}");
			
			if( newAbnTlAbnormality.getAbnmFeedbackdate() == null )
				newAbnTlAbnormality.setAbnmFeedbackdate(Constants.pgPassNullDateTime);
			
			if( newAbnTlAbnormality.getAbnmFeedbackid() == null )
				newAbnTlAbnormality.setAbnmFeedbackid("{}");
			
			if( newAbnTlAbnormality.getAbnmImpactid() == null )
				newAbnTlAbnormality.setAbnmImpactid("{}");
			
			if( newAbnTlAbnormality.getAbnmPriority() == null )
				newAbnTlAbnormality.setAbnmPriority("{}");
			
			if( newAbnTlAbnormality.getAbnmPreventivemeasure() == null )
				newAbnTlAbnormality.setAbnmPreventivemeasure("{}");
			
			if( newAbnTlAbnormality.getAbnmRefdocid() == null )
				newAbnTlAbnormality.setAbnmRefdocid("{}");
			
			
			if( newAbnTlAbnormality.getAbnmRefdoctype() == null )
				newAbnTlAbnormality.setAbnmRefdoctype("{}");
			
			if( newAbnTlAbnormality.getAbnmRemarks() == null )
				newAbnTlAbnormality.setAbnmRemarks("{}");
			
			if( newAbnTlAbnormality.getAbnmResponsetime() == null )
				newAbnTlAbnormality.setAbnmResponsetime("0");			
			
			if( newAbnTlAbnormality.getAbnmRevisionno() == null )
				newAbnTlAbnormality.setAbnmRevisionno("{}");
			
			if( newAbnTlAbnormality.getAbnmSectionid() == null )
				newAbnTlAbnormality.setAbnmSectionid("{}");
			
			newAbnTlAbnormality.setAbnmShiftid("{}");
			
			//getShift(newAbnTlAbnormality,abnBean);
			
		/*	
			if(abnBean.getFormActionMode().equals("Completed")||(abnBean.getFormActionMode().equals("View")))
				newAbnTlAbnormality.setAbnmStatus("C");
			else
				newAbnTlAbnormality.setAbnmStatus("P");*/
			
			
			if( newAbnTlAbnormality.getAbnmSubtype() == null )
				newAbnTlAbnormality.setAbnmSubtype("{}");
			
			if( newAbnTlAbnormality.getAbnmTagclassid() == null )
				newAbnTlAbnormality.setAbnmTagclassid("{}");			
			
			if( newAbnTlAbnormality.getAbnmTargetdate() == null )
				newAbnTlAbnormality.setAbnmTargetdate(currentDate);
			CommonFunctions.debugMsg("rEMARKS : "+newAbnTlAbnormality.getAbnmTargetremarks());
			if( newAbnTlAbnormality.getAbnmTargetremarks() == null )
				newAbnTlAbnormality.setAbnmTargetremarks("{}");
			CommonFunctions.debugMsg("rEMARKS : "+newAbnTlAbnormality.getAbnmTargetremarks());
			if( newAbnTlAbnormality.getAbnmContaminant() == null )
				newAbnTlAbnormality.setAbnmContaminant("{}");
			
			if( newAbnTlAbnormality.getAbnmMode() == null )
				newAbnTlAbnormality.setAbnmMode("{}");
		
			//if( newAbnTlAbnormality.getAbnmFactoryid() == null )
				newAbnTlAbnormality.setAbnmFactoryid("{}");
			
			
			if( newAbnTlAbnormality.getAbnmSafetypatrol() == null )
				newAbnTlAbnormality.setAbnmSafetypatrol("{}");
			
			if( newAbnTlAbnormality.getAbnmRelatedto() == null )
				newAbnTlAbnormality.setAbnmRelatedto("{}");
			
			if(!UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmMould()))
				newAbnTlAbnormality.setAbnmMould("{}");		
			
			if( newAbnTlAbnormality.getAbnmTradeid() == null )
				newAbnTlAbnormality.setAbnmTradeid("{}");
			
			if( newAbnTlAbnormality.getAbnmTypeid() == null )
				newAbnTlAbnormality.setAbnmTypeid("{}");
			
			if( newAbnTlAbnormality.getAbnmWhatcause() == null )
				newAbnTlAbnormality.setAbnmWhatcause("{}");			
			
			if( newAbnTlAbnormality.getAbnmWhyabnhappened() == null )
				newAbnTlAbnormality.setAbnmWhyabnhappened("{}");
			
			if( newAbnTlAbnormality.getAbnmWodetailid() == null )
				newAbnTlAbnormality.setAbnmWodetailid("{}");
		
			if( newAbnTlAbnormality.getAbnmWomasterid() == null )
				newAbnTlAbnormality.setAbnmWomasterid("{}");
			
			CommonFunctions.debugMsg("pillar id....."+newAbnTlAbnormality.getAbnmPillarid());
			CommonFunctions.debugMsg("element id....."+newAbnTlAbnormality.getAbnmElementid());
			if( newAbnTlAbnormality.getAbnmPillarid() == null )
				newAbnTlAbnormality.setAbnmPillarid("{}");
			
			if( newAbnTlAbnormality.getAbnmRepeatedabn() == null )
				newAbnTlAbnormality.setAbnmRepeatedabn("{}");
			
			if( newAbnTlAbnormality.getAbnmAfeemid() == null )
				newAbnTlAbnormality.setAbnmAfeemid("{}");
			
			if( newAbnTlAbnormality.getAbnmEffectivedate() == null )
				newAbnTlAbnormality.setAbnmEffectivedate(Constants.pgPassNullDateTime);
			
			if( newAbnTlAbnormality.getAbnmNotifysap() == null )
				newAbnTlAbnormality.setAbnmNotifysap("-");
			
			if( newAbnTlAbnormality.getAbnmTentativrDate() == null )
				newAbnTlAbnormality.setAbnmTentativrDate(Constants.pgPassNullDateTime);
			
			
			if( newAbnTlAbnormality.getAbnmShutdownid() == null )
				newAbnTlAbnormality.setAbnmShutdownid("{}");
			
			if( newAbnTlAbnormality.getAbnmShutdownmaint() == null )
				newAbnTlAbnormality.setAbnmShutdownmaint("-");
			
			
			if( newAbnTlAbnormality.getAbnmAccecpatncerequired() == null )
				newAbnTlAbnormality.setAbnmAccecpatncerequired("-");
			
			if( newAbnTlAbnormality.getAbnmAccecptDate() == null )
				newAbnTlAbnormality.setAbnmAccecptDate(Constants.pgPassNullDateTime);
			
			if( newAbnTlAbnormality.getAbnmAccecpted() == null )
				newAbnTlAbnormality.setAbnmAccecpted("-");
			
			if( newAbnTlAbnormality.getAbnmElementid() == null)
				newAbnTlAbnormality.setAbnmElementid("{}");
			
			if( newAbnTlAbnormality.getAbnmWoreceiveddate() == null )
				newAbnTlAbnormality.setAbnmWoreceiveddate(Constants.pgPassNullDateTime);
			
			if( newAbnTlAbnormality.getAbnmOthers() == null )
				newAbnTlAbnormality.setAbnmOthers("-");
			if(newAbnTlAbnormality.getAbnmResponsibleid()==null)
				newAbnTlAbnormality.setAbnmResponsibleid("-");
			
			CommonFunctions.debugMsg("newAbnTlAbnormality.getAbnmRepOthers()....."+newAbnTlAbnormality.getAbnmRepOthers());
			if( newAbnTlAbnormality.getAbnmRepOthers() == null )
				newAbnTlAbnormality.setAbnmRepOthers("-");
			 if(newAbnTlAbnormality.getAbnmResponsibleid()==null)
		    	  newAbnTlAbnormality.setAbnmResponsibleid("-");
			      newAbnTlAbnormality.setAbnmMultipleabn("N");	
			if( newAbnTlAbnormality.getAbnmTempfield4() == null )
				newAbnTlAbnormality.setAbnmTempfield4("-");
			
			if( newAbnTlAbnormality.getAbnmTempfield5() == null )
				newAbnTlAbnormality.setAbnmTempfield5("-");
			
			if( newAbnTlAbnormality.getAbnmTempfield6() == null )
				newAbnTlAbnormality.setAbnmTempfield6("-");
			
			if( newAbnTlAbnormality.getAbnmTempfield7() == null )
				newAbnTlAbnormality.setAbnmTempfield7("-");
			
			if( newAbnTlAbnormality.getAbnmTempfield8() == null )
				newAbnTlAbnormality.setAbnmTempfield8("-");
			
			if( newAbnTlAbnormality.getAbnmTempfield9() == null )
				newAbnTlAbnormality.setAbnmTempfield9("-");
			
			if( newAbnTlAbnormality.getAbnmTempfield10() == null )
				newAbnTlAbnormality.setAbnmTempfield10("-");
			
			if( newAbnTlAbnormality.getAbnmWorktime() == null )
				newAbnTlAbnormality.setAbnmWorktime("0");	
			
			CommonFunctions.debugMsg("Wostarttime"+newAbnTlAbnormality.getAbnmWostarttime());
			CommonFunctions.debugMsg("Woendtime"+newAbnTlAbnormality.getAbnmWoendtime());

			
			if( newAbnTlAbnormality.getAbnmWostarttime() == null )
				newAbnTlAbnormality.setAbnmWostarttime(CommonFunctions.pg_getDateTimeFromDate(currentDate));
			
					
	
			return newAbnTlAbnormality; 
			
		}

	@Override
	public AbnTlAbnormality updateAbnormality(AbnTlAbnormality abnTlAbnormality) throws Exception {
		// TODO Auto-generated method stub
		fillValues(abnTlAbnormality);
		//return sheTlPlantSafetymstDao.UpdatePSIAbn(abnTlAbnormality);	
		return abnormalatiyserviceapi.insertRecord(abnTlAbnormality);
	}

	@Override
	public KznTlKaizenbankmst  createSuggestion(KznTlKaizenbankmst newKznTlKaizenbankmst) throws Exception {
		
		try {
			fillValues(newKznTlKaizenbankmst);
		} catch (Exception e) {
			
			e.printStackTrace();
		
      }	
		//return sheTlPlantSafetymstDao.createPSISuggetion(newKznTlKaizenbankmst);
		return kaizenbankserviceapi.saveKznTlKaizenBankMst(newKznTlKaizenbankmst);
		
	}
	
	
	private KznTlKaizenbankmst fillValues(KznTlKaizenbankmst newKznTlKaizenbankmst) throws Exception
	{
		newKznTlKaizenbankmst.setKzbnActive("Y");
		
		
		CommonFunctions.debugMsg(" getKzbnImplementedon "+newKznTlKaizenbankmst.getKzbnImplementedon());
		CommonFunctions.debugMsg(" getKzbnImplementedon "+newKznTlKaizenbankmst.getKzbnImplementedby());
		CommonFunctions.debugMsg(" getKzbnImplementedon "+newKznTlKaizenbankmst.getKzbnImpremarks());
		String dateTime = CommonFunctions.pg_dateTimeNow();
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnBenefit()) )
			newKznTlKaizenbankmst.setKzbnBenefit("{}");
		if(!UIUtils.isValidKeyId( newKznTlKaizenbankmst.getKzbnCompletedon()) )
			newKznTlKaizenbankmst.setKzbnCompletedon(Constants.pgFutureNullDateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnKaizen()) )
			newKznTlKaizenbankmst.setKzbnKaizen("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnPqcdsme()) )
			newKznTlKaizenbankmst.setKzbnPqcdsme("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnAccrejremarks()) )
			newKznTlKaizenbankmst.setKzbnAccrejremarks("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnStatus()) )
			newKznTlKaizenbankmst.setKzbnStatus("-");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnResponsibility()) )
			newKznTlKaizenbankmst.setKzbnResponsibility(newKznTlKaizenbankmst.getKzbnSuggestedby());
		
		if(!UIUtils.isValidKeyId( newKznTlKaizenbankmst.getKzbnResponsibility()) ){
			newKznTlKaizenbankmst.setKzbnResponsibility("{}");
		}
		/*if(!UIUtils.isValidKeyId( newKznTlKaizenbankmst.getKzbnResponsibility()) ){
			if(!UIUtils.isValidKeyId( newKznTlKaizenbankmst.getKzbnSuggestedby()) ){
				if(UIUtils.isValidKeyId( newKznTlKaizenbankmst.getKzbnAcrejby()) ){
					CommonFunctions.debugMsg("inside res acreby");
					newKznTlKaizenbankmst.setKzbnResponsibility(newKznTlKaizenbankmst.getKzbnAcrejby());
				}
			}
		} */
		
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnSuggestedby()) )
			newKznTlKaizenbankmst.setKzbnSuggestedby("-");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnTargetdate()) )
			newKznTlKaizenbankmst.setKzbnTargetdate(Constants.pgFutureNullDateTime);
		else 
			newKznTlKaizenbankmst.setKzbnAcceptrejon(dateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnDate()) )
			newKznTlKaizenbankmst.setKzbnDate(dateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnAccrejremarks()) )
			newKznTlKaizenbankmst.setKzbnAccrejremarks("-");
		if(!UIUtils.isValidKeyId( newKznTlKaizenbankmst.getKzbnCompremarks()) )
			newKznTlKaizenbankmst.setKzbnCompremarks("-");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnImplementedby()))
			newKznTlKaizenbankmst.setKzbnImplementedby("-");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnFlid()))
			newKznTlKaizenbankmst.setKzbnFlid("-");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnAcrejby()))
			newKznTlKaizenbankmst.setKzbnAcrejby("-");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnImpremarks()))
			newKznTlKaizenbankmst.setKzbnImpremarks("-");
		
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnAcceptrejon()))
			newKznTlKaizenbankmst.setKzbnAcceptrejon(Constants.pgFutureNullDateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnImplementedon()))
			newKznTlKaizenbankmst.setKzbnImplementedon(Constants.pgFutureNullDateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnVerifiedon()))
			newKznTlKaizenbankmst.setKzbnVerifiedon(Constants.pgFutureNullDateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnVerifiedby()))
			newKznTlKaizenbankmst.setKzbnVerifiedby("-");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnCompletedby()) )
			newKznTlKaizenbankmst.setKzbnCompletedby("-");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnVerifyremarks()) )
			newKznTlKaizenbankmst.setKzbnVerifyremarks("-");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnElementid()) )
			newKznTlKaizenbankmst.setKzbnElementid("{}");
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnEhsrelated()))
			newKznTlKaizenbankmst.setKzbnEhsrelated("N");
		else if(newKznTlKaizenbankmst.getKzbnEhsrelated().equalsIgnoreCase("ON"))
			newKznTlKaizenbankmst.setKzbnEhsrelated("Y");
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnEhsstatus()))
			newKznTlKaizenbankmst.setKzbnEhsstatus("X");
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnRefdoctype()))
			newKznTlKaizenbankmst.setKzbnRefdoctype("-");
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnRefdocno()))
			newKznTlKaizenbankmst.setKzbnRefdocno("-");
		
		 CommonFunctions.debugMsg(" Inside Service Impl Fill Values :: "+newKznTlKaizenbankmst.getkzbnOthers());
		   
	    if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getkzbnOthers()))
			newKznTlKaizenbankmst.setkzbnOthers("N");
	    else if(newKznTlKaizenbankmst.getkzbnOthers().equalsIgnoreCase("ON"))
			newKznTlKaizenbankmst.setkzbnOthers("Y");
	
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnImplementcost()))
			newKznTlKaizenbankmst.setKzbnImplementcost("0");
		
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnApprovalflag()))
			newKznTlKaizenbankmst.getKzbnApprovalflag("-");
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnmocrequired()))
			newKznTlKaizenbankmst.setKzbnmocrequired("-");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnCreatedby()) )
			newKznTlKaizenbankmst.setKzbnCreatedby("{}");
		if( newKznTlKaizenbankmst.getKzbnKeyid() == null )
			//newKznTlKaizenbankmst.setKzbnCreatedon(existKznTlKaizenbankmst.getKzbnCreatedon());
		
			newKznTlKaizenbankmst.setKzbnCreatedon(dateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnModifiedon()) )
			newKznTlKaizenbankmst.setKzbnModifiedon(dateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnNonJhEsp()) )
			newKznTlKaizenbankmst.setKzbnNonJhEsp("N");
		
		
		//String elementId = kaizenBankDao.getElementID (newKznTlKaizenbankmst.getKzbnFlid());
		//newKznTlKaizenbankmst.setKzbnElementid(elementId);
		return newKznTlKaizenbankmst; 
		
	}

	@Override
	public String getElementId(String flid) throws Exception {
		
		return sheTlPlantSafetymstDao.getElementId(flid);
	}

	@Override
	public KznTlKaizenbankmst UpdateSuggestion(KznTlKaizenbankmst newKznTlKaizenbankmst) throws Exception {
		
		fillValues(newKznTlKaizenbankmst);
		//return sheTlPlantSafetymstDao.updatePSISuggetion(newKznTlKaizenbankmst);
		return kaizenbankserviceapi.saveKznTlKaizenBankMst(newKznTlKaizenbankmst);
	}
	
	
	

}
