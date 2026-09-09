package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BdmTlCriticalityassessmentDao;
import com.akranta.tpm.dao.CriticalityAssessmentDao;
import com.akranta.tpm.dao.KaizenEvaluationDao;
import com.akranta.tpm.dao.impl.BdmTlCriticalityassessmentDaoImpl;
import com.akranta.tpm.dao.impl.CriticalityAssessmentDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.KaizenEvaluationDaoImpl;
import com.akranta.tpm.model.BdmTlCriticalityassessment;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.EntTlTrainingneedmst;
import com.akranta.tpm.service.CriticalityAssessmentService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.CriticalityServiceApi;

public class CriticalityAssessmentServiceImpl implements CriticalityAssessmentService{
	
private CriticalityAssessmentDao criticalityAssessmentDao;
private BdmTlCriticalityassessmentDao bdmTlCriticalityassessmentDao;
private Validations validations ;
private CriticalityServiceApi criticalityserviceapi;
	
	public CriticalityAssessmentServiceImpl(DBActionTemplate actionTemplate) {
		
		criticalityAssessmentDao = new CriticalityAssessmentDaoImpl(actionTemplate); 
		bdmTlCriticalityassessmentDao = new BdmTlCriticalityassessmentDaoImpl(actionTemplate); 
		validations = new Validations();
		
	}
	
	public void CriticalityAssessmentServiceImplJwt(String JwtToken){
    	try{
    		bdmTlCriticalityassessmentDao.BdmTlCriticalityassessmentDaoImplJwt(JwtToken);
    		criticalityserviceapi = new CriticalityServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	

	@Override
	public List<String[]> getCriticality() throws Exception {
		return this.bdmTlCriticalityassessmentDao.getCriticality();  //same
	}


	@Override
	public List<String[]> getCriticalityfillgriddata(CommonFilter commonFilter1,String flid,String crytype ) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlCriticalityassessmentDao.getCriticalityfillgriddata(commonFilter1,flid,crytype);
	}


	@Override
	public List<BdmTlCriticalityassessment> create(List<BdmTlCriticalityassessment> CrtieriaGridList1)throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("ServiceIMPL");
		/*String validationsFor = "create";
		String xml="Criticalitycreation";
		for ( BdmTlCriticalityassessment  CrtieriaGridList : CrtieriaGridList1)
		{
			validations.validate(CrtieriaGridList,xml,validationsFor);
		}*/
		FillValues(CrtieriaGridList1);
		//return this.bdmTlCriticalityassessmentDao.create(CrtieriaGridList1);
		
		return this.criticalityserviceapi.saveBdmTlRequest(CrtieriaGridList1);
		
	}


	private void FillValues(List<BdmTlCriticalityassessment> CrtieriaGridList1) throws Exception {
		// TODO Auto-generated method stub
	
		String dateTime = CommonFunctions.pg_dateTimeNow();				
	    for (BdmTlCriticalityassessment bdmTlCriticalityassessment : CrtieriaGridList1)
		{
	    	    CommonMessage.debugMsg(" DAte fill vlues :::"+bdmTlCriticalityassessment.getCasmDate());
				CommonMessage.debugMsg("In fill  "+bdmTlCriticalityassessment.getCasmCreatedon());
				if(!CommonFunctions.isValidKeyId(bdmTlCriticalityassessment.getCasmKeyid())){
					bdmTlCriticalityassessment.setCasmCreatedon(dateTime);
					bdmTlCriticalityassessment.setCasmModifiedon(dateTime);
				}
				if(bdmTlCriticalityassessment.getCasmActive()==null){
					bdmTlCriticalityassessment.setCasmActive("Y");
				}
				
				
				if(bdmTlCriticalityassessment.getCasmFlid()==null)
					bdmTlCriticalityassessment.setCasmFlid("{}");
				
				if(UIUtils.isValidKeyId(bdmTlCriticalityassessment.getCasmFlid())){
					String elementId=bdmTlCriticalityassessmentDao.getElementID(bdmTlCriticalityassessment.getCasmFlid());
					bdmTlCriticalityassessment.setCasmElementid(elementId);
				}
				else
					bdmTlCriticalityassessment.setCasmElementid("{}");
				if(bdmTlCriticalityassessment.getCasmEquipmentid()==null)
					bdmTlCriticalityassessment.setCasmEquipmentid("{}");
				
				if(bdmTlCriticalityassessment.getCasmCriteriaid()==null)
					bdmTlCriticalityassessment.setCasmCriteriaid("{}");
				
				String date = bdmTlCriticalityassessment.getCasmDate();
				bdmTlCriticalityassessment.setCasmDate(CommonFunctions.pg_getDateTimeFromDate(date));
				
				if(bdmTlCriticalityassessment.getCasmDate()==null)
					bdmTlCriticalityassessment.setCasmDate("{}");
				
				if(bdmTlCriticalityassessment.getCasmDoneby()==null)
					bdmTlCriticalityassessment.setCasmDoneby("{}");
				
				if(bdmTlCriticalityassessment.getCasmRemarks()==null)
					bdmTlCriticalityassessment.setCasmRemarks("{}");
				
				if(bdmTlCriticalityassessment.getCasmScores()==null)
					bdmTlCriticalityassessment.setCasmScores("{}");
				
				if(bdmTlCriticalityassessment.getCasmTradeid()==null)
					bdmTlCriticalityassessment.setCasmTradeid("-");
				
				if(bdmTlCriticalityassessment.getCasmTempfield2()==null)
					bdmTlCriticalityassessment.setCasmTempfield2("-");
				if(bdmTlCriticalityassessment.getCasmTempfield3()==null)
					bdmTlCriticalityassessment.setCasmTempfield3("-");
				if(bdmTlCriticalityassessment.getCasmTempfield4()==null)
					bdmTlCriticalityassessment.setCasmTempfield4("-");
				if(bdmTlCriticalityassessment.getCasmTempfield5()==null)
					bdmTlCriticalityassessment.setCasmTempfield5("-");
				if(bdmTlCriticalityassessment.getCasmTempfield6()==null)
					bdmTlCriticalityassessment.setCasmTempfield6("-");
				if(bdmTlCriticalityassessment.getCasmTempfield7()==null)
					bdmTlCriticalityassessment.setCasmTempfield7("-");
				if(bdmTlCriticalityassessment.getCasmTempfield8()==null)
					bdmTlCriticalityassessment.setCasmTempfield8("-");
				if(bdmTlCriticalityassessment.getCasmTempfield9()==null)
					bdmTlCriticalityassessment.setCasmTempfield9("-");
				if(bdmTlCriticalityassessment.getCasmTempfield10()==null)
					bdmTlCriticalityassessment.setCasmTempfield10("-");
				
				if(bdmTlCriticalityassessment.getCasmCreatedon()==null)
					bdmTlCriticalityassessment.setCasmCreatedon(dateTime);
				if(bdmTlCriticalityassessment.getCasmModifiedon()==null)
					bdmTlCriticalityassessment.setCasmModifiedon(dateTime);
				
//
				//CrtieriaGridList1.get(i).setCasmModifiedon(dateTime);  USR0001
			}
		}
		
	


	@Override
	public List<BdmTlCriticalityassessment> update(List<BdmTlCriticalityassessment> CrtieriaGridList1,List<BdmTlCriticalityassessment> existBdmTlCriticalityassessment)throws Exception {
		// TODO Auto-generated method stub
		//FillValues(CrtieriaGridList1,existBdmTlCriticalityassessment);
		//return this.bdmTlCriticalityassessmentDao.update(CrtieriaGridList1,existBdmTlCriticalityassessment);
		
		return this.criticalityserviceapi.saveBdmTlRequest(CrtieriaGridList1,existBdmTlCriticalityassessment);
	}


	@Override
	public BdmTlCriticalityassessment deleteCritical(BdmTlCriticalityassessment oldBdmTlCriticalityassessment) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlCriticalityassessmentDao.deleteCritical(oldBdmTlCriticalityassessment);
		
	}


	@Override
	public Workbook getCriticalassmntExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlCriticalityassessmentDao.getCriticalassmntExcel(colmodel,format, commonFilter);
	}


	@Override
	public List<String[]> getCriAssMainGrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlCriticalityassessmentDao.getCriAssMainGrid(commonFilter);
	}


	@Override
	public String DeleteCriteriaList(String criteriaDeleteList) {
		// TODO Auto-generated method stub
		return criticalityserviceapi.deleteCriteriaList(criteriaDeleteList);
	}


	@Override
	public String DeleteFlidListMst(String criteriaDeleteFlidList) {
		// TODO Auto-generated method stub
		return bdmTlCriticalityassessmentDao.DeleteFlidListMst(criteriaDeleteFlidList);
	}


	@Override
	public List<String[]> getCriticalassmntcritria( String flid ,String equm, String total,String trade) throws Exception {
		// TODO Auto-generated method stub
		//return bdmTlCriticalityassessmentDao.getCriticalassmntcritria(flid , equm,  total,trade );
		return criticalityserviceapi.getCriteriaKeyId(flid , equm,  total,trade );
	}


	@Override
	public String getCriticalassmntremarks(String flid, String equm)
			throws Exception {
		// TODO Auto-generated method stub
		//return bdmTlCriticalityassessmentDao.getCriticalassmntremarks(flid , equm);
		return criticalityserviceapi.getCriticalityAssessmentRemarks(flid , equm);
	}
	@Override
	public List<String[]> getcriticalReport(CommonFilter commonFilter)
			throws Exception {
		return bdmTlCriticalityassessmentDao.getcriticalReport(commonFilter);
	}


	@Override
	public String getFlid(String parentFlid) throws Exception {
		 
		// TODO Auto-generated method stub
		//return bdmTlCriticalityassessmentDao.getFlid(parentFlid);
		CommonMessage.debugMsg("Getting FLID for parentserviceimpl : " + parentFlid);
		
		return criticalityserviceapi.getFlid(parentFlid);
	}


	@Override
	public Workbook getcriticalReporcExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return bdmTlCriticalityassessmentDao.getcriticalReporcExcel(colmodel,format,commonFilter);
	}
	@Override
	public Workbook getCriticalityAssessmentMstExcel(JSONObject colmodel, String format, CommonFilter commonFilter) throws Exception {
	    return bdmTlCriticalityassessmentDao.getCriticalityAssessmentMstExcel(colmodel, format, commonFilter);
	}

}
