package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;



import com.akranta.tpm.bean.QPointBean;
import com.akranta.tpm.bean.QparameterBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.QtmTlQpointdtlDao;
import com.akranta.tpm.dao.QtmTlQpointmstDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.QtmTlQpointdtlDaoImpl;
import com.akranta.tpm.dao.impl.QtmTlQpointmstDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlKnowwhydtl;
import com.akranta.tpm.model.QtmTlQpoint;
import com.akranta.tpm.model.QtmTlQpointdtl;
import com.akranta.tpm.model.QtmTlQpointdtls;
import com.akranta.tpm.model.QtmTlQpointmst;
import com.akranta.tpm.service.QtmTIQpointmstService;
import com.akranta.tpm.service.api.QpointServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class QtmTIQpointServiceImpl implements QtmTIQpointmstService {
	
	

	private QtmTlQpointmstDao qtmTlQpointmstDao;
	private QtmTlQpointdtlDao qtmTlQpointdtlDao;
	 private QtmTlQpointmst    qtmtiqpointmst;
	 private QtmTlQpointdtl     qtmTlQpointdtl; 
	 private Validations validations;
	 private QpointServiceApi serviceApi;
	 String validationsFor;
	 
	 public QtmTIQpointServiceImpl(DBActionTemplate dbActionTemplate) throws Exception{ 
		 qtmTlQpointmstDao=new  QtmTlQpointmstDaoImpl(dbActionTemplate);
		 validations = new Validations();
		 qtmTlQpointdtlDao = new  QtmTlQpointdtlDaoImpl(dbActionTemplate);
    	
   
       
    }
	 public void QtmTIQpointmstServiceImplJwt(String JwtToken){
	    	try{
	    		qtmTlQpointmstDao.QtmTlQpointmstDaoImplJwt(JwtToken);
	    		serviceApi = new QpointServiceApi(JwtToken);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}}
  
	@Override
	public QtmTlQpointmst create(QtmTlQpointmst newqtmtiqpointmst,
			QtmTlQpointmst existqtmtiqpointmst) throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("inside service impl");
		validationsFor = "create";
		validations.validate(newqtmtiqpointmst, "Qparameter",
				validationsFor);
		fillValues(newqtmtiqpointmst,existqtmtiqpointmst);//
		return qtmTlQpointmstDao.create(newqtmtiqpointmst);
	
}
	
	@Override
	public QtmTlQpoint createQpoint(QtmTlQpoint newQtmTlQpoint,
			QtmTlQpoint existQtmTlQpoint, QPointBean qPointBean)
			throws Exception {
		// TODO Auto-generated method stub
		
		
		String validationsFor = "create";
		String xml = "QPointValidation";
		
		validations.validate(newQtmTlQpoint, xml, validationsFor);
		
		fillValuesQtmTlQpoint(newQtmTlQpoint,existQtmTlQpoint,qPointBean);//
		//return qtmTlQpointmstDao.createQpoint(newQtmTlQpoint);
		return serviceApi.saveQpoint(newQtmTlQpoint);
	}



	@Override
	public QtmTlQpoint updateQpoint(QtmTlQpoint newQtmTlQpoint,
			QtmTlQpoint existQtmTlQpoint, QPointBean qPointBean)
			throws Exception {
		// TODO Auto-generated method stub
		
		String validationsFor = "update";
		String xml = "QPointValidation";
		
		validations.validate(newQtmTlQpoint, xml, validationsFor);
		
		fillValuesQtmTlQpoint(newQtmTlQpoint,existQtmTlQpoint,qPointBean);//
		//return qtmTlQpointmstDao.updateQpoint(newQtmTlQpoint);
		return serviceApi.saveQpoint(newQtmTlQpoint);
	}


	private QtmTlQpoint fillValuesQtmTlQpoint(QtmTlQpoint newQtmTlQpoint,QtmTlQpoint existQtmTlQpoint, QPointBean qPointBean) {
		// TODO Auto-generated method stub
		
		
		newQtmTlQpoint.setQptmActive("Y");
		
		newQtmTlQpoint.setQptmActive("Y");

		String dateTime = CommonFunctions.pg_dateTimeNow();

		newQtmTlQpoint.setQptmCreatedon(dateTime);

		newQtmTlQpoint.setQptmModifiedon(dateTime);
		
		if (newQtmTlQpoint.getQptmFlid() == null)
			newQtmTlQpoint.setQptmFlid("{}");
		
		if (newQtmTlQpoint.getQptmElementid() == null)
			newQtmTlQpoint.setQptmElementid("{}");
		
		if (newQtmTlQpoint.getQptmArea() == null)
			newQtmTlQpoint.setQptmArea("{}");
		
		if (newQtmTlQpoint.getQptmKpov() == null)
			newQtmTlQpoint.setQptmKpov("{}");
		
		if (newQtmTlQpoint.getQptmQpoint() == null)
			newQtmTlQpoint.setQptmQpoint("{}");
		
		if (newQtmTlQpoint.getQptmPreparedby() == null)
			newQtmTlQpoint.setQptmPreparedby("{}");
		
		String date = newQtmTlQpoint.getQptmDate();
		
		newQtmTlQpoint.setQptmDate(CommonFunctions.pg_getDateTimeFromDate(date));
		if (newQtmTlQpoint.getQptmDate() == null)
			newQtmTlQpoint.setQptmDate(dateTime);
		
		if (newQtmTlQpoint.getQptmNooflocations() == null)
			newQtmTlQpoint.setQptmNooflocations("{}");
		
		if (newQtmTlQpoint.getQptmTempfield1() == null)
			newQtmTlQpoint.setQptmTempfield1("-");
		
		if (newQtmTlQpoint.getQptmTempfield2() == null)
			newQtmTlQpoint.setQptmTempfield2("-");

		if (newQtmTlQpoint.getQptmTempfield3() == null)
			newQtmTlQpoint.setQptmTempfield3("-");
		
		if (newQtmTlQpoint.getQptmTempfield4() == null)
			newQtmTlQpoint.setQptmTempfield4("-");
		
		if (newQtmTlQpoint.getQptmTempfield5() == null)
			newQtmTlQpoint.setQptmTempfield5("-");  
		
		if(newQtmTlQpoint.getQtmTlQpointdtls()!=null)
			newQtmTlQpoint.setQtmTlQpointdtls(fillValuesnewQtmTlQpointdtl( newQtmTlQpoint, existQtmTlQpoint, qPointBean));
		
			 return newQtmTlQpoint;	
		
	}

	private QtmTlQpointdtls fillValuesnewQtmTlQpointdtl(
			QtmTlQpoint newQtmTlQpoint, QtmTlQpoint existQtmTlQpoint,
			QPointBean qPointBean) {
		// TODO Auto-generated method stub
		QtmTlQpointdtls  newQtmTlQpointdtls = newQtmTlQpoint.getQtmTlQpointdtls();
		CommonMessage.debugMsg("serviceimpl 4");
		String dateTime = CommonFunctions.pg_dateTimeNow();
	//	List<QtmTlQpointdtls> qtmTlQpointdtls = new ArrayList<QtmTlQpointdtls>();
		
			if(UIUtils.isValidKeyId(newQtmTlQpoint.getQptmKeyid())) 
			{
				
				newQtmTlQpointdtls.setQptdQptmKeyid(newQtmTlQpoint.getQptmKeyid());
			}
		
		
		newQtmTlQpointdtls.setQptdActive("Y");
		 
		 CommonMessage.debugMsg("serviceimpl 41");
		 newQtmTlQpointdtls.setQptdModifiedon(dateTime);
		 newQtmTlQpointdtls.setQptdCreatedon(dateTime);


		
		if( newQtmTlQpointdtls.getQptdQpoint() == null )
			newQtmTlQpointdtls.setQptdQpoint("{}");


		if( newQtmTlQpointdtls.getQptdNooflocations() == null )
			newQtmTlQpointdtls.setQptdNooflocations("0");
		
		
		CommonMessage.debugMsg("serviceimpl 43");
		if( newQtmTlQpointdtls.getQptdTempfield1() == null )
			newQtmTlQpointdtls.setQptdTempfield1("-");
		
		
		if( newQtmTlQpointdtls.getQptdTempfield2() == null )
			newQtmTlQpointdtls.setQptdTempfield2("-");
		
	
		if( newQtmTlQpointdtls.getQptdTempfield3() == null )
			newQtmTlQpointdtls.setQptdTempfield3("-");
		
		if( newQtmTlQpointdtls.getQptdTempfield4() == null )
			newQtmTlQpointdtls.setQptdTempfield4("-");
		
		if( newQtmTlQpointdtls.getQptdTempfield5() == null )
			newQtmTlQpointdtls.setQptdTempfield5("-");
			CommonMessage.debugMsg("serviceimpl 5");
			
		
		return newQtmTlQpointdtls;
	}

	private QtmTlQpointmst fillValues(QtmTlQpointmst newqtmtiqpointmst,
			QtmTlQpointmst existqtmtiqpointmst) {
		
		
		
		String dateTime = CommonFunctions.dateTimeNow();
		//   qtmtiqpointmst.setStudemail("{}");
		   newqtmtiqpointmst.setQpmActive("Y");
		   	
				
			   newqtmtiqpointmst.setQpmCreatedon(dateTime);			
								
				
			
		   newqtmtiqpointmst.setQpmModifiedon(dateTime);
		
		if(    newqtmtiqpointmst.getQpmKeyid() == null )
			   newqtmtiqpointmst.setQpmKeyid("{}");
		
		if(    newqtmtiqpointmst.getQpmFlnid() == null )
			   newqtmtiqpointmst.setQpmFlnid("{}");
		
		if(    newqtmtiqpointmst.getQpmPreparedby() == null )
			   newqtmtiqpointmst.setQpmPreparedby("{}");
		
		if(    newqtmtiqpointmst.getQpmApprovedby() == null )
			   newqtmtiqpointmst.setQpmApprovedby("{}");
		
		if(    newqtmtiqpointmst.getQpmPrepareddate() == null )
			   newqtmtiqpointmst.setQpmPrepareddate(dateTime);
		
		if(    newqtmtiqpointmst.getQpmApproveddate() == null )
			   newqtmtiqpointmst.setQpmApproveddate(dateTime);
		
		
		if(    newqtmtiqpointmst.getQpmTempfield1() == null )
			   newqtmtiqpointmst.setQpmTempfield1("-");
	
		if(    newqtmtiqpointmst.getQpmTempfield2() == null )
			   newqtmtiqpointmst.setQpmTempfield2("-");
		
		if(    newqtmtiqpointmst.getQpmTempfield3() == null )
			   newqtmtiqpointmst.setQpmTempfield3("-");
		
		if(    newqtmtiqpointmst.getQpmTempfield4() == null )
			   newqtmtiqpointmst.setQpmTempfield4("-");
		
		if(    newqtmtiqpointmst.getQpmActive() == null )
			   newqtmtiqpointmst.setQpmActive("{}");
		
		if(    newqtmtiqpointmst.getQpmCreatedby() == null )
			   newqtmtiqpointmst.setQpmCreatedby("{}");
		
	
		
		
		if(    newqtmtiqpointmst.getQpmModifiedon() == null )
			   newqtmtiqpointmst.setQpmModifiedon("{}");
		
		
	
		return newqtmtiqpointmst  ;
		
		
		
	}

	@Override
	public QtmTlQpointmst update(QtmTlQpointmst newqtmtiqpointmst,
			QtmTlQpointmst existqtmtiqpointmst, QparameterBean para) throws Exception{
		
		
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("inside service impl");
		validationsFor = "update";
		validations.validate(newqtmtiqpointmst, "Qparameter",validationsFor);
		fillValues(newqtmtiqpointmst,existqtmtiqpointmst);//
		// TODO Auto-generated method stub
		return qtmTlQpointmstDao.update(newqtmtiqpointmst);
	
		
	}



	@Override
	public QtmTlQpointmst select(String keyid) throws Exception {
		
		return this. qtmTlQpointmstDao.select(keyid);
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<String[]> getmaster(String masterKeyid) throws Exception {
		// TODO Auto-generated method stub
		return  qtmTlQpointmstDao.getmaster(masterKeyid);
	}



	@Override
	public List<String[]> getAllSop() throws Exception {
		// TODO Auto-generated method stub
		return  qtmTlQpointmstDao.getAllSop();
	}



	@Override
	public QtmTlQpointmst delete(QtmTlQpointmst newqtmtiqpointmst,
			QtmTlQpointmst existQtmTlSopmst, QparameterBean bean)
			throws Exception {
		// TODO Auto-generated method stub
		return qtmTlQpointmstDao.delete(newqtmtiqpointmst);
	}



	@Override
	public QtmTlQpointdtl getalldetail(String detailKeyid) throws Exception {
		// TODO Auto-generated method stub
		return  qtmTlQpointmstDao.getalldetail(detailKeyid);
	}



	@Override
	public QtmTlQpointdtl create(QtmTlQpointdtl newqtmTlQpointdtl,
			QtmTlQpointdtl existqtmTlQpointdtl) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("inside service impl");
		
		fillValues(newqtmTlQpointdtl,existqtmTlQpointdtl);//
		CommonMessage.debugMsg("inside service impl");
		return qtmTlQpointdtlDao.create(newqtmTlQpointdtl);
	
		
	}



	private QtmTlQpointdtl fillValues(QtmTlQpointdtl newqtmTlQpointdtl,
			QtmTlQpointdtl existqtmTlQpointdtl)
	{
		
		 CommonMessage.debugMsg("fiil");

		
		String dateTime = CommonFunctions.dateTimeNow();
		//   qtmtiqpointmst.setStudemail("{}");
		newqtmTlQpointdtl.setQpdActive("Y");
		   	
	
		
		
		
		if( newqtmTlQpointdtl.getQpdKeyid() == null )			
		{	
			newqtmTlQpointdtl.setQpdCreatedon(dateTime);			
		}					
		else
		{	
			CommonMessage.debugMsg("Else");
			newqtmTlQpointdtl.setQpdCreatedon(dateTime);	
		}
		newqtmTlQpointdtl.setQpdModifiedon(dateTime);
		
		
		if( newqtmTlQpointdtl== null )
			newqtmTlQpointdtl.setQpdKeyid("{}");

		if(    newqtmTlQpointdtl.getQpdQpmkeyid() == null )
			newqtmTlQpointdtl.setQpdQpmkeyid("{}");
		
		
		
		if(    newqtmTlQpointdtl.getQpdKeyid() == null )
			newqtmTlQpointdtl.setQpdKeyid("{}");
		
		if(    newqtmTlQpointdtl.getQpdQparameter() == null )
			newqtmTlQpointdtl.setQpdQparameter("{}");
		
		if(    newqtmTlQpointdtl.getQpdSpecification() == null )
			newqtmTlQpointdtl.setQpdSpecification("{}");
		
		if(   newqtmTlQpointdtl.getQpdEffectofparam() == null )
			newqtmTlQpointdtl.setQpdEffectofparam("{}");
		
		if(    newqtmTlQpointdtl.getQpdMeasuringequip() == null )
			newqtmTlQpointdtl.setQpdMeasuringequip("{}");
		
		if(    newqtmTlQpointdtl.getQpdMonitoringmethod() == null )
			newqtmTlQpointdtl.setQpdMonitoringmethod("{}");
		
		
		if(    newqtmTlQpointdtl.getQpdFourm() == null )
			newqtmTlQpointdtl.setQpdFourm("-");
	
		if(    newqtmTlQpointdtl.getQpdFrequency() == null )
			newqtmTlQpointdtl.setQpdFrequency("-");
		
		if(    newqtmTlQpointdtl.getQpdTempfield1() == null )
			newqtmTlQpointdtl.setQpdTempfield1("-");
		
		if(   newqtmTlQpointdtl.getQpdTempfield2() == null )
			newqtmTlQpointdtl.setQpdTempfield2("-");
		if(    newqtmTlQpointdtl.getQpdTempfield3() == null )
			newqtmTlQpointdtl.setQpdTempfield3("-");
		
		if(   newqtmTlQpointdtl.getQpdTempfield4() == null )
			newqtmTlQpointdtl.setQpdTempfield4("-");
		
		if(   newqtmTlQpointdtl.getQpdActive() == null )
			newqtmTlQpointdtl.setQpdActive("Y");
		
		if(   newqtmTlQpointdtl.getQpdCreatedby() == null )
			newqtmTlQpointdtl.setQpdCreatedby("{}");
		
	
		
		
		if(    newqtmTlQpointdtl.getQpdModifiedon() == null )
			newqtmTlQpointdtl.setQpdModifiedon("{}");
		
		
	
		return newqtmTlQpointdtl ;
		
             
				
	}



	@Override
	public QtmTlQpointdtl update(QtmTlQpointdtl newqtmTlQpointdtl,
			QtmTlQpointdtl existqtmTlQpointdtl) throws Exception {
		
CommonMessage.debugMsg("inside service impl");
		
		fillValues(newqtmTlQpointdtl, existqtmTlQpointdtl);//
		// TODO Auto-generated method stub
		return qtmTlQpointdtlDao.update(newqtmTlQpointdtl);
	
		
	}



	@Override
	public QtmTlQpointdtl delete(QtmTlQpointdtl newQtmTlpointdtl,
			QtmTlQpointdtl existQtmTlpointdtl) throws Exception {
		// TODO Auto-generated method stub
		return qtmTlQpointdtlDao.delete(newQtmTlpointdtl);
	}



	@Override
	public List<String[]> getPoint(String type) throws Exception {
		return this. qtmTlQpointmstDao.getPoint(type);
	}



	@Override
	public List<String[]> getQPointNewGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this. qtmTlQpointmstDao.getQPointNewGrid(commonFilter);
	}



	@Override
	public List<String[]> getQPointFormNewGrid(CommonFilter commonFilter,String keyId,String area, String kpov, String preparedby)
			throws Exception {
		// TODO Auto-generated method stub
		return this. qtmTlQpointmstDao.getQPointFormNewGrid(commonFilter,keyId,area,kpov,preparedby);
		
	}

	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this. qtmTlQpointmstDao.FillControlData(keyid);
	}

	@Override
	public QtmTlQpoint deleteQPoint(QtmTlQpoint newQtmTlQpoint)
			throws Exception {
		// TODO Auto-generated method stub
		return this. qtmTlQpointmstDao.deleteQPoint(newQtmTlQpoint);
	}

	@Override
	public Workbook getQPointExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this. qtmTlQpointmstDao.getQPointExcel(colmodel,format,commonFilter);
	}

	@Override
	public QtmTlQpoint getFilControlData(String keyid) throws IOException {
		// TODO Auto-generated method stub
			return serviceApi.getById(keyid);
		
	}
	public List<String[]> getQptscnt(CommonFilter commonFilter)	throws Exception {
		return this.qtmTlQpointmstDao.getQptscnt(commonFilter);
	}
	
	public Workbook qPpointCumulativeExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception
	{
		return this.qtmTlQpointmstDao.qPpointCumulativeExportExcel(commonFilter, colModel, rptFormat);
	}
}
