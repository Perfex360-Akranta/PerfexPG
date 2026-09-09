package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BdmTlMstDao;
import com.akranta.tpm.dao.BdmTlWwblamstDao;
import com.akranta.tpm.dao.CommonFilterDao;

import com.akranta.tpm.dao.impl.BdmTlWwblamstDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import com.akranta.tpm.model.BdmTlWwbladtl;
import com.akranta.tpm.model.BdmTlWwblamst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFishbonemst;
import com.akranta.tpm.service.WwblaService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.ComplaintGalleryServiceApi;
import com.akranta.tpm.service.api.WwblaServiceApi;


public class WwblaServiceImpl implements WwblaService{
	
		private static final long serialVersionUID = 1L;
	       
	    
		private CommonFilterDao commonFilterDao;
		private BdmTlWwblamstDao  bdmTlWwblamstDao;
		private Validations validations ;
		
		 private WwblaServiceApi wwblaserviceapi;

		String validationsFor;
	    public WwblaServiceImpl(DBActionTemplate dbActionTemplate){
	    	try{
	        commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
	        bdmTlWwblamstDao = new BdmTlWwblamstDaoImpl(dbActionTemplate);
	        validations = new Validations();
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	        // TODO Auto-generated constructor stub
	    }
		
	
	    public void WwblaServiceImplJwt(String JwtToken){
	    	try{
	    		bdmTlWwblamstDao.BdmTlWwblamstDaoImplJwt(JwtToken);
	    		wwblaserviceapi = new WwblaServiceApi(JwtToken);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	        // TODO Auto-generated constructor stub
	    }



	private BdmTlWwblamst fillValuesWwbla(BdmTlWwblamst newBdmTlWwblamst,
			BdmTlWwblamst existBdmTlWwblamst)  throws Exception
			{
		newBdmTlWwblamst.setWwblActive("Y");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		
		String date = newBdmTlWwblamst.getWwblPrepareddate();
		newBdmTlWwblamst.setWwblPrepareddate(CommonFunctions.pg_getDateTimeFromDate(date));
		

		newBdmTlWwblamst.setWwblCreatedon(dateTime);

		newBdmTlWwblamst.setWwblModifiedon(dateTime);
		
		if (newBdmTlWwblamst.getWwblKeyid() == null)
			newBdmTlWwblamst.setWwblKeyid("-");
		
		if (newBdmTlWwblamst.getWwblFlid() == null)
			newBdmTlWwblamst.setWwblFlid("-");

		if (newBdmTlWwblamst.getWwblPreparedby() == null)
			newBdmTlWwblamst.setWwblPreparedby("-");
		
		if (newBdmTlWwblamst.getWwblPrepareddate() == null)
			newBdmTlWwblamst.setWwblPrepareddate(dateTime);
		
		
		if (newBdmTlWwblamst.getWwblProblem() == null)
			newBdmTlWwblamst.setWwblProblem("{}");
		
		if (newBdmTlWwblamst.getWwblPhenomena() == null)
			newBdmTlWwblamst.setWwblPhenomena("{}");
		
		if (newBdmTlWwblamst.getWwblMechanism() == null)
			newBdmTlWwblamst.setWwblMechanism("{}");
		
		
		if (newBdmTlWwblamst.getWwblLopcId() == null)
			newBdmTlWwblamst.setWwblLopcId("-");
		
		if (newBdmTlWwblamst.getWwblLopcEmpId() == null)
			newBdmTlWwblamst.setWwblLopcEmpId("-");
		
		if (newBdmTlWwblamst.getWwblLopcYn() == null)
			newBdmTlWwblamst.setWwblLopcYn("-");
		
		if (newBdmTlWwblamst.getWwblActive() == null)
			newBdmTlWwblamst.setWwblActive("-");
		
		if(newBdmTlWwblamst.getWwblCreatedby()==null)
			newBdmTlWwblamst.setWwblCreatedby("-");
		
		if(newBdmTlWwblamst.getWwblCreatedon()==null)
			newBdmTlWwblamst.setWwblCreatedon(dateTime);
			
		if(newBdmTlWwblamst.getWwblModifiedon()==null)
		   newBdmTlWwblamst.setWwblModifiedon(dateTime);
		
		if(newBdmTlWwblamst.getWwblInvestigation()==null)
			   newBdmTlWwblamst.setWwblInvestigation("W");
		return existBdmTlWwblamst;
		
		
	}


	


	
	public BdmTlWwblamst create(BdmTlWwblamst newBdmTlWwblamst,
			BdmTlWwblamst existBdmTlWwblamst) throws ValidationExceptions, Exception {
		
		
		validationsFor = "create";
		//validation.validate(newBdmTlWwblamst,"Wwbla",validationsFor);
		CommonMessage.debugMsg("Service impl inside validate ckeck ok");
		fillValuesWwbla(newBdmTlWwblamst,existBdmTlWwblamst);
		CommonMessage.debugMsg("Service impl inside fillvalues ckeck ok");
		//return bdmTlWwblamstDao.create(newBdmTlWwblamst,existBdmTlWwblamst);
		return wwblaserviceapi.saveWwbla(newBdmTlWwblamst,existBdmTlWwblamst);
		
	}


	public BdmTlWwblamst update(BdmTlWwblamst newBdmTlWwblamst,
			BdmTlWwblamst existBdmTlWwblamst) throws Exception {
		validationsFor = "create";
		//validation.validate(newBdmTlWwblamst,"Wwbla",validationsFor);
		CommonMessage.debugMsg("Service impl inside validate ckeck ok");
		fillValuesWwbla(newBdmTlWwblamst,existBdmTlWwblamst);
		CommonMessage.debugMsg("Service impl inside fillvalues ckeck ok");
		//return bdmTlWwblamstDao.update(newBdmTlWwblamst,existBdmTlWwblamst);
		return wwblaserviceapi.saveWwbla(newBdmTlWwblamst,existBdmTlWwblamst);
		
	}

	@Override
	public List<String[]> getAllWwblaGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return bdmTlWwblamstDao.getAllWwblaGrid(commonFilter);  
	}
	@Override
	public Workbook getWwblaExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlWwblamstDao.getWwblaExcel(colmodel,format,commonFilter);
	}

	@Override
	public List<BdmTlWwbladtl> getWwblaValues(BdmTlWwbladtl bdmTlWwbladtl,
			BdmTlWwblamst bdmTlWwblamst, String id, String masterId)
			throws Exception {
		CommonMessage.debugMsg(" Inside Service Impl For Loadval ::");
    	//return bdmTlWwblamstDao.getWwblaValues(bdmTlWwbladtl,bdmTlWwblamst,id,masterId);
    	return wwblaserviceapi.getWwblaValues(bdmTlWwbladtl,bdmTlWwblamst,id,masterId);
	}






	@Override
	public GenTlFishbonemst getFillControl(String wwblaKeyid) {
		// TODO Auto-generated method stub
		return null;
	}






	@Override
	public List<String[]> getSearchNode(String searchNode,String originalId)
			throws Exception {
		return bdmTlWwblamstDao.getSearchNode(searchNode,originalId); 
	}






	@Override
	public BdmTlWwbladtl createChildEntry(BdmTlWwbladtl newBdmTlWwbladtl,
			BdmTlWwbladtl existBdmTlWwbladtl, String editval) throws Exception {
		String validationsFor;
		validationsFor = "create";
		
		CommonMessage.debugMsg(" Inside Service Impl :: "+" editval :: "+editval);
		
		String xml = "WwblaChildEntry";
		
		if("Editval".equals(editval)){
			CommonMessage.debugMsg("  Inside Service Impl  :: fishBoneBean.getFormModes() ");
		    //validations.validate(newBdmTlWwbladtl, xml, "cause");

		}else{
			
			//validations.validate(newBdmTlWwbladtl, xml, validationsFor);
		
		}
			
		fillValuesWwbla(newBdmTlWwbladtl, existBdmTlWwbladtl);
		CommonMessage.debugMsg("invest check print2"+newBdmTlWwbladtl.getWwbdReoccur());
		
		//return bdmTlWwblamstDao.createChildEntry(newBdmTlWwbladtl,existBdmTlWwbladtl,editval);
		return wwblaserviceapi.saveWwblaDetail(newBdmTlWwbladtl,existBdmTlWwbladtl,editval);
	}






	private void fillValuesWwbla(BdmTlWwbladtl newBdmTlWwbladtl,
			BdmTlWwbladtl existBdmTlWwbladtl) {
		// TODO Auto-generated method stub
		newBdmTlWwbladtl.setWwbdActive("Y");
		CommonMessage.debugMsg("invest check print"+newBdmTlWwbladtl.getWwbdReoccur());
		String dateTime = CommonFunctions.pg_dateTimeNow();

		newBdmTlWwbladtl.setWwbdCreatedon(dateTime);

		newBdmTlWwbladtl.setWwbdModifiedon(dateTime);
		
		
		String date = newBdmTlWwbladtl.getWwbdCompletedon();
		newBdmTlWwbladtl.setWwbdCompletedon(CommonFunctions.pg_getDateTimeFromDate(date));
		
		
		if (newBdmTlWwbladtl.getWwbdWwblKeyid() == null)
			newBdmTlWwbladtl.setWwbdWwblKeyid("{}");

		if (newBdmTlWwbladtl.getWwbdPhenomenaFactor() == null)
			newBdmTlWwbladtl.setWwbdPhenomenaFactor("{}");
		
		if (newBdmTlWwbladtl.getWwbdVerification() == null)
			newBdmTlWwbladtl.setWwbdVerification("-");
		
		if (newBdmTlWwbladtl.getWwbdIslastfactor() == null)
			newBdmTlWwbladtl.setWwbdIslastfactor("-");
		
		if (newBdmTlWwbladtl.getWwbdParentid() == null)
			newBdmTlWwbladtl.setWwbdParentid("{}");
		
		if (newBdmTlWwbladtl.getWwbdOrderno() == null)
			newBdmTlWwbladtl.setWwbdOrderno("1");
		
		if (newBdmTlWwbladtl.getWwbdLevelno() == null)
			newBdmTlWwbladtl.setWwbdLevelno("1");
		
		if (newBdmTlWwbladtl.getWwbdCountermeasure() == null)
			newBdmTlWwbladtl.setWwbdCountermeasure("{}");
		
		if (newBdmTlWwbladtl.getWwbdSkilltype() == null)
			newBdmTlWwbladtl.setWwbdSkilltype("-");		
				
		if (newBdmTlWwbladtl.getWwbdResponsiblity() == null)
			newBdmTlWwbladtl.setWwbdResponsiblity("-");
		String date1= newBdmTlWwbladtl.getWwbdTargetDate();
		newBdmTlWwbladtl.setWwbdTargetDate(CommonFunctions.pg_getDateTimeFromDate(date1));
		
		
		if (newBdmTlWwbladtl.getWwbdTargetDate() == null)
			newBdmTlWwbladtl.setWwbdTargetDate(Constants.pgPassNullDateTime);    //31-Dec-2100
		
		if (newBdmTlWwbladtl.getWwbdStatus() == null)
			newBdmTlWwbladtl.setWwbdStatus("P");
		
		if (newBdmTlWwbladtl.getWwbdReoccur() == null)
			newBdmTlWwbladtl.setWwbdReoccur("-");
		
		if (newBdmTlWwbladtl.getWwbdAction() == null)
			newBdmTlWwbladtl.setWwbdAction("-");
		
		if (newBdmTlWwbladtl.getwwbdCompletedBy() == null)
			newBdmTlWwbladtl.setWwbdCompletedBy("-");
		
		if (newBdmTlWwbladtl.getWwbdCompletedon() == null)
			newBdmTlWwbladtl.setWwbdCompletedon(Constants.pgPassNullDateTime);
		
		if (newBdmTlWwbladtl.getWwbdRemarks() == null)
			newBdmTlWwbladtl.setWwbdRemarks("-");
		
		
	}

	public BdmTlWwbladtl updateChildEntry(BdmTlWwbladtl newBdmTlWwbladtl,
			BdmTlWwbladtl existBdmTlWwbladtl) throws Exception {
		// TODO Auto-generated method stub
		String validationsFor;
		validationsFor = "update";
		String xml = "WwblaChildEntry";
		validations.validate(newBdmTlWwbladtl, xml, validationsFor);
		fillValuesWwbla(newBdmTlWwbladtl, existBdmTlWwbladtl);
		return bdmTlWwblamstDao.updateChildEntry(newBdmTlWwbladtl,existBdmTlWwbladtl);
	}
	public BdmTlWwbladtl deleteWWBLAChildEntry(BdmTlWwbladtl newBdmTlWwbladtl)
			throws Exception {
		// TODO Auto-generated method stub
		//return bdmTlWwblamstDao.deleteWWBLAChildEntry(newBdmTlWwbladtl);
		
		return wwblaserviceapi.deleteWWBLAChildEntry(newBdmTlWwbladtl);
	}
	public List<String[]> WwblachildData(String dtlId) throws Exception {
		// TODO Auto-generated method stub
		//return bdmTlWwblamstDao.WwblachildData(dtlId);
		return wwblaserviceapi.recallWwblaDetail(dtlId);
	}
public BdmTlWwblamst delete(BdmTlWwblamst newBdmTlWwblamst) throws Exception {
		return bdmTlWwblamstDao.delete(newBdmTlWwblamst);
	}
	public BdmTlWwblamst getWwbla(String keyid) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("ggggg");
		return bdmTlWwblamstDao.getWwbla(keyid);
	}
	
}
