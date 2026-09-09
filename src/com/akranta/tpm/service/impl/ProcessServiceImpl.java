package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.CriticalProcessNew;
import com.akranta.tpm.dao.ProcessDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.ProcessDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlCriticalprocessdtl;
import com.akranta.tpm.model.QtmTlCriticalprocessmst;
import com.akranta.tpm.service.ProcessService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.CriticalProcessServiceApi;

public class ProcessServiceImpl implements ProcessService {
	
	
	private static final long serialVersionUID = 1L;
	private CriticalProcessServiceApi criticalprocessapi;
	
    private ProcessDao processDao;
    private Validations validations;
    public ProcessServiceImpl(DBActionTemplate dbActionTemplate) throws Exception{ 
    	processDao=new  ProcessDaoImpl(dbActionTemplate);
    	validations = new Validations();
    }
    
    public void ProcessServiceImplJwt(String JwtToken){
    	try{
    		processDao.ProcessDaoImplJwt(JwtToken);
   		criticalprocessapi = new CriticalProcessServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<String[]> getAllProcess(CommonFilter commonFilter) throws Exception {
		return this.processDao.getAllProcess(commonFilter);
    }


	public QtmTlCriticalprocessmst select(String crppKeyid)throws Exception{
		//return this.processDao.select(crppKeyid);
		return this.criticalprocessapi.getCompleteCriticalProcessData(crppKeyid);
    }

	
	@Override
	public List<String[]> getFillMainGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.processDao.getFillMainGrid(commonFilter);
	}

	@Override
	public QtmTlCriticalprocessmst create(QtmTlCriticalprocessmst newQtmTlCriticalprocessmst,
			   QtmTlCriticalprocessmst existQtmTlCriticalprocessmst,CriticalProcessNew criticalProcessNew)
		   
	       throws Exception {
		validations.validate(newQtmTlCriticalprocessmst,"criticalProcessValidations","create");
		if(newQtmTlCriticalprocessmst.getQtmTlCriticalprocessdtl() != null)
			validations.validate(newQtmTlCriticalprocessmst.getQtmTlCriticalprocessdtl(),"criticalProcessValidations","detail");
		fillValuesQtmTlQpoint(newQtmTlCriticalprocessmst,existQtmTlCriticalprocessmst,criticalProcessNew);//
		//return this.processDao.create(newQtmTlCriticalprocessmst);
		return this.criticalprocessapi.saveCriticalProcess(newQtmTlCriticalprocessmst);
	}
	@Override
	public QtmTlCriticalprocessmst update(QtmTlCriticalprocessmst newQtmTlCriticalprocessmst,
		   QtmTlCriticalprocessmst existQtmTlCriticalprocessmst,CriticalProcessNew criticalProcessNew) throws Exception {
		validations.validate(newQtmTlCriticalprocessmst,"criticalProcessValidations","update");
		if(newQtmTlCriticalprocessmst.getQtmTlCriticalprocessdtl() != null)
			validations.validate(newQtmTlCriticalprocessmst.getQtmTlCriticalprocessdtl(),"criticalProcessValidations","detail");		
		
		fillValuesQtmTlQpoint(newQtmTlCriticalprocessmst,existQtmTlCriticalprocessmst,criticalProcessNew);//
		//return this.processDao.update(newQtmTlCriticalprocessmst);
		return this.criticalprocessapi.saveCriticalProcess(newQtmTlCriticalprocessmst);
	}
	
	@Override
	public void delete(String crppKeyid) throws Exception {
		// TODO Auto-generated method stub
		 //this.processDao.delete(crppKeyid);
		this.criticalprocessapi.deleteCriticalProcessMaster(crppKeyid);
	}
	
	public void deleteDtl(String crpdKeyid)throws Exception{
		//this.processDao.deleteDtl(crpdKeyid);
		this.criticalprocessapi.deleteCriticalProcessDetail(crpdKeyid);
	}
	
	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.processDao.FillControlData(keyid);
	}
	
	


	@Override
	public Workbook getCriticalProcessExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.processDao.getCriticalProcessExcel(colmodel,format,commonFilter);
	}

	
	private void fillValuesQtmTlQpoint(QtmTlCriticalprocessmst newQtmTlCriticalprocessmst,
			QtmTlCriticalprocessmst existQtmTlCriticalprocessmst,CriticalProcessNew criticalProcessNew) {
		newQtmTlCriticalprocessmst.setCrppActive("Y");
		
		newQtmTlCriticalprocessmst.setCrppActive("Y");

		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		String date = newQtmTlCriticalprocessmst.getCrppDate();
		newQtmTlCriticalprocessmst.setCrppDate(CommonFunctions.pg_getDateTimeFromDate(date));
		
		
		newQtmTlCriticalprocessmst.setCrppCreatedon(dateTime);

		newQtmTlCriticalprocessmst.setCrppModifiedon(dateTime);
		
		QtmTlCriticalprocessdtl qtmTlCriticalprocessdtl = newQtmTlCriticalprocessmst.getQtmTlCriticalprocessdtl();
		
		if (newQtmTlCriticalprocessmst.getCrppFlid() == null)
			newQtmTlCriticalprocessmst.setCrppFlid("{}");
		
		if (newQtmTlCriticalprocessmst.getCrppElementid() == null)
			newQtmTlCriticalprocessmst.setCrppElementid("{}");
		
		if (newQtmTlCriticalprocessmst.getCrppDate() == null)
			newQtmTlCriticalprocessmst.setCrppDate(dateTime);
		
		if (newQtmTlCriticalprocessmst.getCrppParameter() == null)
			newQtmTlCriticalprocessmst.setCrppParameter("{}");
		
		if (newQtmTlCriticalprocessmst.getCrppTempfield1() == null)
			newQtmTlCriticalprocessmst.setCrppTempfield1("-");
		
		if (newQtmTlCriticalprocessmst.getCrppTempfield2() == null)
			newQtmTlCriticalprocessmst.setCrppTempfield2("-");
		
		if (newQtmTlCriticalprocessmst.getCrppTempfield3() == null)
			newQtmTlCriticalprocessmst.setCrppTempfield3("-");
		
		if (newQtmTlCriticalprocessmst.getCrppTempfield4() == null)
			newQtmTlCriticalprocessmst.setCrppTempfield4("-");
		
		if (newQtmTlCriticalprocessmst.getCrppTempfield5() == null)
			newQtmTlCriticalprocessmst.setCrppTempfield5("-");
		
		if( qtmTlCriticalprocessdtl != null){
			
			if( qtmTlCriticalprocessdtl.getCrpdMethod() == null )
				qtmTlCriticalprocessdtl.setCrpdMethod("-");
			if( qtmTlCriticalprocessdtl.getCrpdUnit() == null )
				qtmTlCriticalprocessdtl.setCrpdUnit("{}");
			if( qtmTlCriticalprocessdtl.getCrpdValue() == null )
				qtmTlCriticalprocessdtl.setCrpdValue("0");
			
			if( qtmTlCriticalprocessdtl.getCrpdMin() == null )
				qtmTlCriticalprocessdtl.setCrpdMin("-");
			
			if( qtmTlCriticalprocessdtl.getCrpdMax() == null )
				qtmTlCriticalprocessdtl.setCrpdMax("-");
			
			if( qtmTlCriticalprocessdtl.getCrpdTempfield3() == null )
				qtmTlCriticalprocessdtl.setCrpdTempfield3("-");
			
			if( qtmTlCriticalprocessdtl.getCrpdTempfield4() == null )
				qtmTlCriticalprocessdtl.setCrpdTempfield4("-");
			
			if( qtmTlCriticalprocessdtl.getCrpdTempfield5() == null )
				qtmTlCriticalprocessdtl.setCrpdTempfield5("-");
			
			if( qtmTlCriticalprocessdtl.getCrpdCreatedby() == null )
				qtmTlCriticalprocessdtl.setCrpdCreatedby(newQtmTlCriticalprocessmst.getCrppCreatedby());
			
			if( qtmTlCriticalprocessdtl.getCrpdActive() == null )
				qtmTlCriticalprocessdtl.setCrpdActive("Y");
			
			qtmTlCriticalprocessdtl.setCrpdCreatedon(dateTime);
			qtmTlCriticalprocessdtl.setCrpdModifiedon(dateTime);
			
			newQtmTlCriticalprocessmst.setQtmTlCriticalprocessdtl(qtmTlCriticalprocessdtl);
		}
		
	}


	
	@Override
	public Workbook getprocessExcel(JSONObject colmodel, String format,	CommonFilter commonFilter) throws Exception {
		return this.processDao.getprocessExcel(colmodel,format,commonFilter);
	}
	
}
