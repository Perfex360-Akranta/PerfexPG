package com.akranta.tpm.service.impl;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.StdWorSheetkBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.StandardizedWorkSheetDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.StandardizedWorkSheetDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.StdTlStdworksheetdtl;
import com.akranta.tpm.model.StdTlStdworksheetmst;
import com.akranta.tpm.service.StandardizedWorkSheetService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.WhywhyServiceApi;
import com.akranta.tpm.service.api.WorksheetServiceApi;

public class StandardizedWorkSheetServiceImpl implements StandardizedWorkSheetService{

	private Validations validations ;
	private CommonFilterDao commonFilterDao;
	private StandardizedWorkSheetDao standardizedWorkSheetDao;
	private WorksheetServiceApi worksheetserviceapi; 
	
	public StandardizedWorkSheetServiceImpl(DBActionTemplate dbActionTemplate)
	{
		validations = new Validations();
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		standardizedWorkSheetDao = new StandardizedWorkSheetDaoImpl(dbActionTemplate);
	}
	
	public void StandardizedWorkSheetServiceImplJwt(String JwtToken){
    	try{
    		standardizedWorkSheetDao.StandardizedWorkSheetDaoImplJwt(JwtToken);
    		worksheetserviceapi = new WorksheetServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	public List<String[]> getStdWoShMainGrid() throws Exception {
		return standardizedWorkSheetDao.getStdWoShMainGrid();
	}

	@Override
	public List<String[]> getAllworkshtdtl(CommonFilter commonFilter)
			throws Exception {
		return standardizedWorkSheetDao.getAllworkshtdtl(commonFilter);
	}

	@Override
	public List<String[]> getAllMaster(CommonFilter commonFilter)
			throws Exception {
		return standardizedWorkSheetDao.getAllMaster(commonFilter);
	}

	@Override
	public StdTlStdworksheetmst create(StdTlStdworksheetmst newStdTlStdworksheetmst,StdTlStdworksheetmst existStdTlStdworksheetmst,StdWorSheetkBean stdWorSheetkBean) throws Exception {
		
		try
		{
	      validations.validate( newStdTlStdworksheetmst,"StdWork","create");
	      fillValues(newStdTlStdworksheetmst,existStdTlStdworksheetmst,stdWorSheetkBean);//
		  //return standardizedWorkSheetDao.create(newStdTlStdworksheetmst) ;
		  return worksheetserviceapi.saveWorksheet(newStdTlStdworksheetmst) ;
		  
		 
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
}
       private StdTlStdworksheetmst fillValues(StdTlStdworksheetmst newStdTlStdworksheetmst,StdTlStdworksheetmst existStdTlStdworksheetmst,StdWorSheetkBean stdWorSheetkBean) {
		String dateTime = CommonFunctions.pg_dateTimeNow();
         newStdTlStdworksheetmst.setStwsActive("Y");
		if( newStdTlStdworksheetmst.getStwsKeyid() == null )			
		{	
           newStdTlStdworksheetmst.setStwsCreatedon(dateTime);			
		}					
		else
		{	
		newStdTlStdworksheetmst.setStwsCreatedon(dateTime);
		}
		 newStdTlStdworksheetmst.setStwsModifiedon(dateTime);
		 
		 String date = newStdTlStdworksheetmst.getStwsDate();
		 newStdTlStdworksheetmst.setStwsDate(CommonFunctions.pg_getDateTimeFromDate(date));
		
	   if( newStdTlStdworksheetmst.getStwsProcess() == null )
			 newStdTlStdworksheetmst.setStwsProcess("{}");
	    if( newStdTlStdworksheetmst.getStwsFlid() == null )
			 newStdTlStdworksheetmst.setStwsFlid("{}");
		
		if( newStdTlStdworksheetmst.getStwsBudgetedtime() == null)
			 newStdTlStdworksheetmst.setStwsBudgetedtime("{}");
		if( newStdTlStdworksheetmst.getStwsElementid() == null)
			 newStdTlStdworksheetmst.setStwsElementid("{}");
		
		
		if( newStdTlStdworksheetmst.getStwsDate() == null )
			 newStdTlStdworksheetmst.setStwsDate(dateTime);
		
		if( newStdTlStdworksheetmst.getStwsApprovedby() == null )
			 newStdTlStdworksheetmst.setStwsApprovedby("{}");
		
		if( newStdTlStdworksheetmst.getStwsBy() == null )
			 newStdTlStdworksheetmst.setStwsBy("{}");
		if( newStdTlStdworksheetmst.getStwsCreatedby() == null )
			 newStdTlStdworksheetmst.setStwsCreatedby("{}");
		
		if( newStdTlStdworksheetmst.getStwsType() == null )
			 newStdTlStdworksheetmst.setStwsType("-");
		 
		if( newStdTlStdworksheetmst.getStwsCycletime() == null )
			 newStdTlStdworksheetmst.setStwsCycletime("{}");
		
		if( newStdTlStdworksheetmst.getStwsTempfield3() == null )
			 newStdTlStdworksheetmst.setStwsTempfield3("-");
		
		if( newStdTlStdworksheetmst.getStwsTempfield4() == null )
			 newStdTlStdworksheetmst.setStwsTempfield4("-");
		
		if( newStdTlStdworksheetmst.getStwsTempfield5() == null )
			 newStdTlStdworksheetmst.setStwsTempfield5("-");
		
		if( newStdTlStdworksheetmst.getStwsCreatedby() == null )
			 newStdTlStdworksheetmst.setStwsCreatedby("{}");
		
		 if(newStdTlStdworksheetmst.getstdTlStdworksheetdtl()!= null)
		 {
		//newStdTlStdworksheetmst.setStdTlStdworksheetdtl(fillValuesdtl( newStdTlStdworksheetmst, existStdTlStdworksheetmst,stdWorSheetkBean));
		newStdTlStdworksheetmst.setStdTlStdworksheetdtl(fillValuesdtl(newStdTlStdworksheetmst, existStdTlStdworksheetmst,stdWorSheetkBean));
		 }
		return newStdTlStdworksheetmst;
		// TODO Auto-generated method stub
}
private List<StdTlStdworksheetdtl> fillValuesdtl(StdTlStdworksheetmst newStdTlStdworksheetmst,StdTlStdworksheetmst existStdTlStdworksheetmst,StdWorSheetkBean stdWorSheetkBean) {
		List<StdTlStdworksheetdtl> newStdTlStdworksheetdtl =newStdTlStdworksheetmst.getstdTlStdworksheetdtl();
		
	//	StdTlStdworksheetdtl  newStdTlStdworksheetdtl=new StdTlStdworksheetdtl();
		 CommonMessage.debugMsg("serviceimpl 4");
		 String dateTime = CommonFunctions.dateTimeNow();
	     List<StdTlStdworksheetdtl> lstStdTlStdworksheetdtl= new ArrayList<StdTlStdworksheetdtl>();
		 if(newStdTlStdworksheetmst.getstdTlStdworksheetdtl()!= null)
		 {
		for (StdTlStdworksheetdtl Stdworksheetdtl : newStdTlStdworksheetdtl)
		{
		    Stdworksheetdtl.setStwdCreatedon(dateTime);
			Stdworksheetdtl.setStwdModifiedon(dateTime);

			Stdworksheetdtl.setStwdActive("Y");
			if (!UIUtils.isValidKeyId(newStdTlStdworksheetmst.getStwsCreatedby()))
				Stdworksheetdtl.setStwdCreatedby("{}");
			else
				Stdworksheetdtl.setStwdCreatedby(newStdTlStdworksheetmst.getStwsCreatedby());

			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdStwsKeyid()))
				Stdworksheetdtl.setStwdStwsKeyid("{}");
			
			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdTypeofmanpower()))
				Stdworksheetdtl.setStwdTypeofmanpower("{}");
			
			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdMajorsteps()))
				Stdworksheetdtl.setStwdMajorsteps("{}");
			
			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdMantime()))
				Stdworksheetdtl.setStwdMantime("{}");
			
			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdProcesstime()))
				Stdworksheetdtl.setStwdProcesstime("{}");
			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdTraveltime()))
				Stdworksheetdtl.setStwdTraveltime("{}");
			
			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdWaittime()))
				Stdworksheetdtl.setStwdWaittime("{}");
			
			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdCreatedby()))
				Stdworksheetdtl.setStwdCreatedby("{}");
			
			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdTempfield2()))
				Stdworksheetdtl.setStwdTempfield2("-");
			
			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdTempfield3()))
				Stdworksheetdtl.setStwdTempfield3("-");
			
			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdTempfield4()))
				Stdworksheetdtl.setStwdTempfield4("-");
			
			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdTempfield5()))
				Stdworksheetdtl.setStwdTempfield5("-");
			
			if (!UIUtils.isValidKeyId(Stdworksheetdtl.getStwdTempfield1()))
				Stdworksheetdtl.setStwdTempfield1("-");

			lstStdTlStdworksheetdtl.add(Stdworksheetdtl);
		}
		CommonMessage.debugMsg("End Of  fillValues Stdworksheetdtl ");
     }
		return lstStdTlStdworksheetdtl;
	}
	@Override
	public StdTlStdworksheetmst update(StdTlStdworksheetmst newStdTlStdworksheetmst,StdTlStdworksheetmst existStdTlStdworksheetmst,StdWorSheetkBean stdWorSheetkBean) throws Exception {
		try
		{
		validations.validate( newStdTlStdworksheetmst,"StdWork","update");
		fillValues( newStdTlStdworksheetmst,existStdTlStdworksheetmst,stdWorSheetkBean);//
		//return standardizedWorkSheetDao.update(newStdTlStdworksheetmst) ;
		return worksheetserviceapi.saveWorksheet(newStdTlStdworksheetmst) ;
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
}
	
 @Override
	public StdTlStdworksheetmst selectmaster(String keyid) throws Exception {
		return worksheetserviceapi.getCompleteWorksheetData(keyid);
	}

	@Override
	public StdTlStdworksheetmst delete(
			StdTlStdworksheetmst newStdTlStdworksheetmst,
			StdTlStdworksheetmst existStdTlStdworksheetmst,
			StdWorSheetkBean stdWorSheetkBean) throws Exception {
		return standardizedWorkSheetDao.delete(newStdTlStdworksheetmst);
	}

	@Override
	public Workbook getStdWorkExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return standardizedWorkSheetDao.getStdWorkExcel(colmodel,format,commonFilter);
	}

	@Override
	public void DeleteStdRow(String keyid) throws Exception {
		this.standardizedWorkSheetDao.DeleteStdRow(keyid);
	}

	@Override
	public void DeleteStdWorkRow(
			List<StdTlStdworksheetdtl> lstStdTlStdworksheetdtl)
			throws Exception {
		this.standardizedWorkSheetDao.DeleteStdWorkRow(lstStdTlStdworksheetdtl);
	}

	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return standardizedWorkSheetDao.FillControlData(keyid);
	}
}


