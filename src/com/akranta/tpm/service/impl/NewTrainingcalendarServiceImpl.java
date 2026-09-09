package com.akranta.tpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.NewTrgCalDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.NewTrgCalDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalmst;
import com.akranta.tpm.model.EntTlTrgCalEmp;
import com.akranta.tpm.model.EntTlTrgCalSession;
import com.akranta.tpm.model.EntTlTrgCalUnqp;
import com.akranta.tpm.model.EntTlTrgFaculty;
import com.akranta.tpm.model.EntTlTtgCalEmpatScore;
import com.akranta.tpm.service.NewTrainingcalendarService;
import com.akranta.tpm.service.api.FishBoneServiceApi;
import com.akranta.tpm.service.api.NewTrainingcalendarServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

import net.sf.json.JSONObject;

public class NewTrainingcalendarServiceImpl implements NewTrainingcalendarService{

	private NewTrgCalDao newTrgCalDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations;
	private DBActionTemplate  dbActionTemplate ; 
	NewTrainingcalendarServiceApi newTrgServApi;
	
	public NewTrainingcalendarServiceImpl(DBActionTemplate dbActionTemplate) {		
		this.dbActionTemplate = dbActionTemplate;
		newTrgCalDao = new NewTrgCalDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public void NewTrainingcalendarServiceImplJwt(String JwtToken){
	    try{
	   // 	oplTlMstDao.OplTlMstDaoImplJwt(JwtToken);   // dao side
	        // (Optional) if you want service-level direct access
	    	newTrgCalDao.NewTrgCalDaoImplJwt(JwtToken);
	    	newTrgServApi = new NewTrainingcalendarServiceApi(JwtToken);
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
@Override
	public List<String[]> getFaculty(String progKeyid) throws Exception {
		// TODO Auto-generated method stub
	//	return newTrgCalDao.getFaculty(progKeyid);
		return newTrgServApi.getFaculty(progKeyid);
	}
@Override
	public List<String[]> getAllEmployee(CommonFilter commonFilter,GridParams gridParams) throws Exception {
	//	return newTrgCalDao.getAllEmployee(commonFilter,gridParams);
		return newTrgServApi.getAllEmployee(commonFilter,gridParams);
	}
	@Override
	public List<String[]> getsession(String progKeyid) throws Exception {
		// TODO Auto-generated method stub
	//	return newTrgCalDao.getsession(progKeyid);
		return newTrgServApi.getsession(progKeyid);
	}
	@Override
	  public List<String[]> getAllUniqueEmployee(CommonFilter commonFilter,GridParams gridParams) throws Exception{
	//	  return newTrgCalDao.getAllUniqueEmployee(commonFilter,gridParams);
		  return newTrgServApi.getAllUniqueEmployee(commonFilter,gridParams);
	  }
	  @Override
	  public List<String[]> getNewUniqPosData(String keyid) throws Exception{
		  return newTrgCalDao.getNewUniqPosData(keyid);
	  }
	  @Override
	  public List<ComboBox> session(ComboFilter comboFilter,CommonFilter commonfilter) throws Exception{
		    comboFilter.setCodeField("ETCS_NAME");
		    comboFilter.setIdField("ETCS_KEYID"); 
		    comboFilter.setTableName("ENT_TL_TRGCALSESSION");
		    //////17oct2016//////////////////
		    comboFilter.setCondSql("AND ETCS_ETCM_KEYID = '"+commonfilter.getKey()+"'");
		    ////////////////////////////////////
		    CommonMessage.debugMsg("The comboFilter"+comboFilter);
			return commonFilterDao.fillComboValues(comboFilter);
	  }
	  @Override
	  public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter)
	  		throws Exception {
	  	// TODO Auto-generated method stub
	//  	 return newTrgCalDao.getAllUniqueEmployeePopup(commonFilter);
	  	return newTrgServApi.getAllUniqueEmployeePopup(commonFilter);
	  }
	  public List<EntTlTrgCalEmp> createSessionEmployee(List<EntTlTrgCalEmp> entTlSessionEmployeeLink) throws Exception{
		 // return newTrgCalDao.createSessionEmployee(fillValuesSessionEmployee(entTlSessionEmployeeLink)); 
		  return newTrgServApi.createSessionEmployee(fillValuesSessionEmployee(entTlSessionEmployeeLink)); 
	  }
	  public List<EntTlTrgCalEmp>  DeleteSessionEmployee(List<EntTlTrgCalEmp> entTlSessionEmployeeLink) throws Exception{
		  return newTrgCalDao. DeleteSessionEmployee(entTlSessionEmployeeLink); 
	  }
	  public List<EntTlTrgCalEmp> createSessionEmployeeUpdate(List<EntTlTrgCalEmp> entTlSessionEmployeeLink) throws Exception{
		  return newTrgCalDao.createSessionEmployeeUpdate(entTlSessionEmployeeLink); 
	  }
	  public List<String[]> getflid(String originalid) throws Exception{
		  return newTrgCalDao.getflid(originalid);
	  }
	  
	  @Override
		public EntTlTragcalmst create(EntTlTragcalmst newentTlTragcalmst,
				EntTlTragcalmst existntTlTragcalmst)
				throws ValidationExceptions,Exception, BusinessApplicationExceptions {
			// TODO Auto-generated method stub
		        String XmlName="NewTrainingCalendar";
				String validationsFor = "create";
				CommonMessage.debugMsg("Inside the ServiceImpl Create");
		 	validations.validate(newentTlTragcalmst,XmlName,validationsFor);
				 if(newentTlTragcalmst.getsessionMaster()!= null )
					 CommonMessage.debugMsg("Inside the Sessionmaster");
					 validations.validate(newentTlTragcalmst.getsessionMaster(),XmlName,validationsFor);
				     fillValuess(newentTlTragcalmst,existntTlTragcalmst);
				     CommonMessage.debugMsg("After Filling tool Values");
				//for inserting in clisCalendar procedure
		//	       return  newTrgCalDao.create(newentTlTragcalmst);
			       return  newTrgServApi.create(newentTlTragcalmst);
		}

	       @Override
			public EntTlTragcalmst update(EntTlTragcalmst newentTlTragcalmst,
					EntTlTragcalmst existntTlTragcalmst)
					throws ValidationExceptions,Exception, BusinessApplicationExceptions {
				// TODO Auto-generated method stub
	    	        String XmlName="NewTrainingCalendar";
					String validationsFor = "update";
					CommonMessage.debugMsg("Inside the ServiceImpl Create");
					
					validations.validate(newentTlTragcalmst,XmlName,validationsFor);
					
					if(newentTlTragcalmst.getsessionMaster()!= null )
						 CommonMessage.debugMsg("Inside the Sessionmaster");
						 validations.validate(newentTlTragcalmst.getsessionMaster(),XmlName,validationsFor);
					     fillValuess(newentTlTragcalmst,existntTlTragcalmst);
					     CommonMessage.debugMsg("After Filling tool Values");
					//for inserting in clisCalendar procedure
				//       return  newTrgCalDao.update(newentTlTragcalmst);
				       return  newTrgServApi.update(newentTlTragcalmst);
			}
	       public EntTlTragcalmst deleteTrainingCal(EntTlTragcalmst newEntTlTragcalmst) throws Exception{
	    	 //    return newTrgCalDao.deleteTrainingCal(newEntTlTragcalmst);
	    	     return  newTrgServApi.deleteTrainingCal(newEntTlTragcalmst);
	       }


	       private List<EntTlTrgCalEmp> fillValuesSessionEmployee( List<EntTlTrgCalEmp> entTlSessionEmployeeLink) {
	   		
	   		List<EntTlTrgCalEmp> newEntTlSessionEmployeeLinks =entTlSessionEmployeeLink;
	   		List<EntTlTrgCalEmp> newEntTlBatchEmployeeLinkList = new ArrayList<EntTlTrgCalEmp>();
	   		int index=0;
	   		if(entTlSessionEmployeeLink!=null)
	   		for( EntTlTrgCalEmp genEntTlSessionEmployeeLink : newEntTlSessionEmployeeLinks)
	   		{	
	   			String dateTime = CommonFunctions.pg_dateTimeNow();
	   			EntTlTrgCalEmp genEntTlBatchEmployeeLink1 = newEntTlSessionEmployeeLinks.get(index);
	   			index++;
	   			genEntTlSessionEmployeeLink.setEtceActive("Y");
	   			genEntTlSessionEmployeeLink.setEtceCreatedon(dateTime);
	   		//	genEntTlSessionEmployeeLink.setEtceCreatedby("EMPOOO1");
	   			genEntTlSessionEmployeeLink.setEtceModifiedon(dateTime);
	   			
	   			
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceEtcmKeyid())){
	   				genEntTlSessionEmployeeLink.setEtceEtcmKeyid("{}");
	   			 }
	   			
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceEtcsKeyid())){
	   				genEntTlSessionEmployeeLink.setEtceEtcsKeyid("{}");
	   			 }
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceEmpmKeyid())){
	   				genEntTlSessionEmployeeLink.setEtceEmpmKeyid("{}");
	   			 }
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceDateAdd())){
	   				genEntTlSessionEmployeeLink.setEtceDateAdd(dateTime);
	   			 }
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceRoleKeyid())){
	   				genEntTlSessionEmployeeLink.setEtceRoleKeyid("{}");
	   			 }
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceRoleDmt())){
	   				genEntTlSessionEmployeeLink.setEtceRoleDmt("{}");
	   			 }
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceRoleJh())){
	   				genEntTlSessionEmployeeLink.setEtceRoleJh("{}");
	   			 }
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceTempfield1())){
	   				genEntTlSessionEmployeeLink.setEtceTempfield1("-");
	   			 }
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceTempfield2())){
	   				genEntTlSessionEmployeeLink.setEtceTempfield2("-");
	   			 }
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceTempfield3())){
	   				genEntTlSessionEmployeeLink.setEtceTempfield3("-");
	   			 }
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceTempfield4())){
	   				genEntTlSessionEmployeeLink.setEtceTempfield4("-");
	   			 }
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceTempfield5())){
	   				genEntTlSessionEmployeeLink.setEtceTempfield5("-");
	   			 }
	   			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getEtceCreatedby())){
	   				genEntTlSessionEmployeeLink.setEtceCreatedby("{}");
	   			 }
	   			newEntTlBatchEmployeeLinkList.add(genEntTlSessionEmployeeLink);
	   	}
	   	return newEntTlBatchEmployeeLinkList;
	   	}       
	       
private void   fillValuess(EntTlTragcalmst entTlTragcalmst,EntTlTragcalmst existntTlTragcalmst) throws Exception{
	    
	String dateTime=CommonFunctions.pg_dateTimeNow();
	//entTlTragcalmst.setEtcmCreatedDateTime(dateTime);
	entTlTragcalmst.setEtcmActive("Y");
	entTlTragcalmst.setEtcmCreatedOn(dateTime);
	entTlTragcalmst.setEtcmModifiedOn(dateTime);
	
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmFlid())){
		entTlTragcalmst.setEtcmFlid("{}");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmLocation())){
		entTlTragcalmst.setEtcmLocation("{}");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmDmt())){
		entTlTragcalmst.setEtcmDmt("{}");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmJh())){
		entTlTragcalmst.setEtcmJh("{}");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmTopicid())){
		entTlTragcalmst.setEtcmTopicid("{}");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmCreatedDateTime())){
		entTlTragcalmst.setEtcmCreatedDateTime(dateTime);
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmRemarks())){
		entTlTragcalmst.setEtcmRemarks("-");
	 }
	 
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmCalendarDate())){
		entTlTragcalmst.setEtcmCalendarDate(dateTime);
	 }
	 
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmGeneral())){
		entTlTragcalmst.setEtcmGeneral("N");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmUniqueposition())){
		entTlTragcalmst.setEtcmUniqueposition("N");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmMSD())){
		entTlTragcalmst.setEtcmMSD("N");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmChkCompleted())){
		entTlTragcalmst.setEtcmChkCompleted("N");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmCompletedDate())){
		entTlTragcalmst.setEtcmCompletedDate(dateTime);
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmCompletedBy())){
		entTlTragcalmst.setEtcmCompletedBy("-");
	 } 
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmMaxDuration())){
		entTlTragcalmst.setEtcmMaxDuration("0");
	 } 
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmFunction())){
		entTlTragcalmst.setEtcmFunction("-");
	 } 
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmVenue())){
		entTlTragcalmst.setEtcmVenue("-");
	 } 
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmPermittedStrength())){
		entTlTragcalmst.setEtcmPermittedStrength("0");
	 } 
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmMaterialsReady())){
		entTlTragcalmst.setEtcmMaterialsReady("N");
	 } 
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmAssessmentReq())){
		entTlTragcalmst.setEtcmAssessmentReq("N");
	 } 
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmMarksBased())){
		entTlTragcalmst.setEtcmMarksBased("N");
	 } 
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmFileManagedId())){
		entTlTragcalmst.setEtcmFileManagedId("-");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmFileManagedId())){
		entTlTragcalmst.setEtcmFileManagedId("-");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmAnchoredby())){
		entTlTragcalmst.setEtcmAnchoredby("-");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmTrainingfunction())){
		entTlTragcalmst.setEtcmTrainingfunction("-");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmRating())){
		entTlTragcalmst.setEtcmRating("0");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmComments())){
		entTlTragcalmst.setEtcmComments("-");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmTopiccategory())){
		entTlTragcalmst.setEtcmTopiccategory("-");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmTempfield6())){
		entTlTragcalmst.setEtcmTempfield6("-");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmTempfield7())){
		entTlTragcalmst.setEtcmTempfield7("-");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmTempfield8())){
		entTlTragcalmst.setEtcmTempfield8("-");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmTempfield9())){
		entTlTragcalmst.setEtcmTempfield9("-");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmTempfield10())){
		entTlTragcalmst.setEtcmTempfield10("-");
	 }
	if(!UIUtils.isValidKeyId(entTlTragcalmst.getEtcmCreatedBy())){
		entTlTragcalmst.setEtcmCreatedBy("{}");
	 }

	  if(entTlTragcalmst.getsessionMaster()!=null){
		  fillSessionValues(entTlTragcalmst.getsessionMaster());
	  }
	if(entTlTragcalmst.getFaculty()!= null){
		CommonMessage.debugMsg("GetFaculty Info Service");
		fillFacultyValues(entTlTragcalmst.getFaculty());
	}
      if(entTlTragcalmst.getRoleLink() !=null){
		
		fillUniquePositionValues(entTlTragcalmst.getRoleLink());
	}
}

private void fillSessionValues(EntTlTrgCalSession newntTlTrgCalSession) throws Exception{
	
	newntTlTrgCalSession.setEtcsActive("Y");
	CommonMessage.debugMsg("lllllll");
	String dateTime = CommonFunctions.dateTimeNow();
	CommonMessage.debugMsg(dateTime);
	if( ! UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsKeyid()))
	{
		newntTlTrgCalSession.setEtcsCreatedon(dateTime);
		
	}
	else{
	}  
	newntTlTrgCalSession.setEtcsModifiedon(dateTime);

	
	
	 if(!UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsEtcmKeyid())){
		 newntTlTrgCalSession.setEtcsEtcmKeyid("-");
	 }
	
	 if(!UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsEtcmFlid())){
		 newntTlTrgCalSession.setEtcsEtcmFlid("-");
	 }
		 
	 if(!UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsName())){
		 newntTlTrgCalSession.setEtcsName("-");  
	 }
	 if(!UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsSessionDate())){
		 newntTlTrgCalSession.setEtcsSessionDate(dateTime);
	 }
	 if(!UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsFromDate())){
		 newntTlTrgCalSession.setEtcsFromDate(Constants.passNullDate); 
	 } 
	 if(!UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsTillDate())){
		 newntTlTrgCalSession.setEtcsTillDate(Constants.passNullDate); 
	 }
	 if(!UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsDateAdd())){
		 newntTlTrgCalSession.setEtcsDateAdd(dateTime); 
	 }
	 if(!UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsTempfield1())){
		 newntTlTrgCalSession.setEtcsTempfield1("-"); 
	 }
	 if(!UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsTempfield2())){
		 newntTlTrgCalSession.setEtcsTempfield2("-"); 
	 }
	 if(!UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsTempfield3())){
		 newntTlTrgCalSession.setEtcsTempfield3("-"); 
	 }
	 if(!UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsTempfield4())){
		 newntTlTrgCalSession.setEtcsTempfield4("-");
	 }
	 if(!UIUtils.isValidKeyId(newntTlTrgCalSession.getEtcsTempfield5())){
		 newntTlTrgCalSession.setEtcsTempfield5("-"); 
	 }	
}

public void fillFacultyValues(EntTlTrgFaculty newentTlTrgFaculty) throws Exception{

	//facultyTopicLink.setFtlkActive("Y");
	newentTlTrgFaculty.setEtcfActive("Y");
	String dateTime = CommonFunctions.dateTimeNow();
	if( ! UIUtils.isValidKeyId(newentTlTrgFaculty.getEtcfKeyid()))
	{
		//facultyTopicLink.setFtlkCreatedon(dateTime);
		newentTlTrgFaculty.setEtcfCreatedon(dateTime);
		
	}
	//facultyTopicLink.setFtlkModifiedon(dateTime);
	newentTlTrgFaculty.setEtcfModifiedon(dateTime);
	
	if( !UIUtils.isValidKeyId(newentTlTrgFaculty.getEtcfEtcmKeyid() ) )
		newentTlTrgFaculty.setEtcfEtcmKeyid("{}");
	if( !UIUtils.isValidKeyId(newentTlTrgFaculty.getEtcfEtcmFlid() ) )
		newentTlTrgFaculty.setEtcfEtcmFlid("{}");
	
	if( !UIUtils.isValidKeyId(newentTlTrgFaculty.getEtcfFacultyId() ) )
		newentTlTrgFaculty.setEtcfFacultyId("{}");
	if( !UIUtils.isValidKeyId(newentTlTrgFaculty.getEtcfFacultytype() ) )
		newentTlTrgFaculty.setEtcfFacultytype("-");
	
	if( !UIUtils.isValidKeyId(newentTlTrgFaculty.getEtcfDateadd()) )
		newentTlTrgFaculty.setEtcfDateadd(dateTime);
	
	if( !UIUtils.isValidKeyId(newentTlTrgFaculty.getEtcfTempfield1())) 
		newentTlTrgFaculty.setEtcfTempfield1("-");
	
	if( !UIUtils.isValidKeyId(newentTlTrgFaculty.getEtcfTempfield2())) 
		newentTlTrgFaculty.setEtcfTempfield2("-");
	if( !UIUtils.isValidKeyId(newentTlTrgFaculty.getEtcfTempfield3())) 
		newentTlTrgFaculty.setEtcfTempfield3("-");
	if( !UIUtils.isValidKeyId(newentTlTrgFaculty.getEtcfTempfield4())) 
		newentTlTrgFaculty.setEtcfTempfield4("-");
	if( !UIUtils.isValidKeyId(newentTlTrgFaculty.getEtcfTempfield5())) 
		newentTlTrgFaculty.setEtcfTempfield5("-");
	
	if( !UIUtils.isValidKeyId(newentTlTrgFaculty.getEtcfCreatedby()))
		newentTlTrgFaculty.setEtcfCreatedby("{}");	
}

public void fillUniquePositionValues(EntTlTrgCalUnqp newentTlTrgCalUnqp){
	  
	  newentTlTrgCalUnqp.setEtcuActive("Y");
	  String dateTime=CommonFunctions.dateTimeNow();
	  if(! UIUtils.isValidKeyId(newentTlTrgCalUnqp.getEtcuKeyid())){
		  newentTlTrgCalUnqp.setEtcuCreatedon(dateTime);
	  }
	  newentTlTrgCalUnqp.setEtcuModifiedon(dateTime);
    
	  if(! UIUtils.isValidKeyId(newentTlTrgCalUnqp.getEtcuEtcmKeyid())){
		  newentTlTrgCalUnqp.setEtcuEtcmKeyid("{}");
	  }
	  
	  if(! UIUtils.isValidKeyId(newentTlTrgCalUnqp.getEtcuRoleKeyid())){
		  newentTlTrgCalUnqp.setEtcuRoleKeyid("{}");
	  }
	  if(! UIUtils.isValidKeyId(newentTlTrgCalUnqp.getEtcuRoleDmt())){
		  newentTlTrgCalUnqp.setEtcuRoleDmt("{}");
	  }
	  if(! UIUtils.isValidKeyId(newentTlTrgCalUnqp.getEtcuRoleJh())){
		  newentTlTrgCalUnqp.setEtcuRoleJh("{}");
	  }
	  if(! UIUtils.isValidKeyId(newentTlTrgCalUnqp.getEtcuDateAdd())){
		  newentTlTrgCalUnqp.setEtcuDateAdd(dateTime);
	  }
	  if(! UIUtils.isValidKeyId(newentTlTrgCalUnqp.getEtcuTempfield1())){
		  newentTlTrgCalUnqp.setEtcuTempfield1("-");
	  }
	  if(! UIUtils.isValidKeyId(newentTlTrgCalUnqp.getEtcuTempfield2())){
		  newentTlTrgCalUnqp.setEtcuTempfield2("-");
	  }
	  if(! UIUtils.isValidKeyId(newentTlTrgCalUnqp.getEtcuTempfield3())){
		  newentTlTrgCalUnqp.setEtcuTempfield3("-");
	  }
	  if(! UIUtils.isValidKeyId(newentTlTrgCalUnqp.getEtcuTempfield4())){
		  newentTlTrgCalUnqp.setEtcuTempfield4("-");
	  }
	  if(! UIUtils.isValidKeyId(newentTlTrgCalUnqp.getEtcuTempfield5())){
		  newentTlTrgCalUnqp.setEtcuTempfield5("-");
	  }
	  if(! UIUtils.isValidKeyId(newentTlTrgCalUnqp.getEtcuCreatedby())){
		  newentTlTrgCalUnqp.setEtcuCreatedby("{}");
	  }
}

private List<EntTlTtgCalEmpatScore> fillValuesEmployeeAttendance(List<EntTlTtgCalEmpatScore> entTlEmployeeAttendanceLink){  		
		List<EntTlTtgCalEmpatScore> newEntTlEmployeeAttendanceLinks =entTlEmployeeAttendanceLink;
		List<EntTlTtgCalEmpatScore> newEntTlEmployeeLinkList = new ArrayList<EntTlTtgCalEmpatScore>();
		int index=0;
		if(entTlEmployeeAttendanceLink!=null)
		for( EntTlTtgCalEmpatScore genEntTlEmployeeAttendanceLink : newEntTlEmployeeAttendanceLinks)
		{	
			String dateTime = CommonFunctions.dateTimeNow();
			EntTlTtgCalEmpatScore genEntTlEmployeeLink1 = newEntTlEmployeeAttendanceLinks.get(index);
			index++;
			genEntTlEmployeeAttendanceLink.setEtcaActive("Y");
			genEntTlEmployeeAttendanceLink.setEtcaCreatedon(dateTime);
			//genEntTlEmployeeAttendanceLink.setEtcaCreatedby("EMP0001");
			genEntTlEmployeeAttendanceLink.setEtcaModifiedon(dateTime);
			
			CommonMessage.debugMsg("genEntTlEmployeeAttendanceLink.getEtcaType()"+genEntTlEmployeeAttendanceLink.getEtcaType());
	        
			  
			if(!UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaEtcmKeyid())){
				genEntTlEmployeeAttendanceLink.setEtcaEtcmKeyid("{}");
			}
			if(!UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaEtceEmpmKeyid())){
				genEntTlEmployeeAttendanceLink.setEtcaEtceEmpmKeyid("{}");
			}
			if(!UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaEtceKeyid())){
				genEntTlEmployeeAttendanceLink.setEtcaEtceKeyid("{}");
			}
			if(!UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaAssessmentCom())){
				genEntTlEmployeeAttendanceLink.setEtcaAssessmentCom("N");
			}
			
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaMaxMarks())){
				genEntTlEmployeeAttendanceLink.setEtcaMaxMarks("0");
			}
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaCutOff())){
				genEntTlEmployeeAttendanceLink.setEtcaCutOff("0");
			}
			
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaType()))
				genEntTlEmployeeAttendanceLink.setEtcaType("-");
			else{  
				if("O".equals(genEntTlEmployeeAttendanceLink.getEtcaType().trim()))
					genEntTlEmployeeAttendanceLink.setEtcaType("O");
				else
					genEntTlEmployeeAttendanceLink.setEtcaType("W");
			}			    
			
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaDept())){
				genEntTlEmployeeAttendanceLink.setEtcaDept("{}");
			}
			
			
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaPresentAbsent()))
				genEntTlEmployeeAttendanceLink.setEtcaPresentAbsent("-");
			/*else{
				   if("P".equals(genEntTlEmployeeAttendanceLink.getEtcaPresentAbsent().trim()))
					   genEntTlEmployeeAttendanceLink.setEtcaPresentAbsent("P");
				   else
					   genEntTlEmployeeAttendanceLink.setEtcaPresentAbsent("A");  
			}*/
			
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaAddDate())){
				genEntTlEmployeeAttendanceLink.setEtcaAddDate(dateTime);
			}
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaScore())){
				genEntTlEmployeeAttendanceLink.setEtcaScore("0");
			}
			
			if(!UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaResult()))
				genEntTlEmployeeAttendanceLink.setEtcaResult("-");
			else{
				
				if("P".equals(genEntTlEmployeeAttendanceLink.getEtcaResult().trim()))
					genEntTlEmployeeAttendanceLink.setEtcaResult("P");
				else
					genEntTlEmployeeAttendanceLink.setEtcaResult("F");
			}
			
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaRemarks())){
				genEntTlEmployeeAttendanceLink.setEtcaRemarks("-");
			}
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaScoreDate())){
				genEntTlEmployeeAttendanceLink.setEtcaScoreDate(dateTime);
			}
       
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaFileMgnId())){
				genEntTlEmployeeAttendanceLink.setEtcaFileMgnId("-");
			}
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaTempfield1())){
				genEntTlEmployeeAttendanceLink.setEtcaTempfield1("-");
			}
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaTempfield2())){
				genEntTlEmployeeAttendanceLink.setEtcaTempfield2("-");
			}
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaTempfield3())){
				genEntTlEmployeeAttendanceLink.setEtcaTempfield3("-");
			}
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaTempfield4())){
				genEntTlEmployeeAttendanceLink.setEtcaTempfield4("-");
			}
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaTempfield5())){
				genEntTlEmployeeAttendanceLink.setEtcaTempfield5("-");
			}
			if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaCreatedby())){
				genEntTlEmployeeAttendanceLink.setEtcaCreatedby("{}");
			}	
			newEntTlEmployeeLinkList.add(genEntTlEmployeeAttendanceLink);
	}
	return newEntTlEmployeeLinkList;
	}

private EntTlTrgCalEmp fillEmployeeByValues(EntTlTrgCalEmp newEntTlTrgCalEmp,EntTlTrgCalEmp existEntTlTrgCalEmp) {
	String dateTime = CommonFunctions.pg_dateTimeNow();
	CommonMessage.debugMsg("datetime"+dateTime);
	newEntTlTrgCalEmp.setEtceActive("Y");
	newEntTlTrgCalEmp.setEtceCreatedon(dateTime);
	newEntTlTrgCalEmp.setEtceModifiedon(dateTime);
    
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceEtcmKeyid()))
		  newEntTlTrgCalEmp.setEtceEtcmKeyid("{}");
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceEtcsKeyid()))
		  newEntTlTrgCalEmp.setEtceEtcsKeyid("{}");
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceEmpmKeyid()))
		 newEntTlTrgCalEmp.setEtceEmpmKeyid("{}");
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceDateAdd()))
		  newEntTlTrgCalEmp.setEtceDateAdd(dateTime);
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceRoleKeyid()))
		newEntTlTrgCalEmp.setEtceRoleKeyid("{}");
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceRoleDmt()))
		newEntTlTrgCalEmp.setEtceRoleDmt("{}");
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceRoleJh()))
		 newEntTlTrgCalEmp.setEtceRoleJh("{}");
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceTempfield1()))
		newEntTlTrgCalEmp.setEtceTempfield1("-");
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceTempfield2()))
		newEntTlTrgCalEmp.setEtceTempfield2("-");
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceTempfield3()))
		newEntTlTrgCalEmp.setEtceTempfield3("-");
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceTempfield4()))
		newEntTlTrgCalEmp.setEtceTempfield4("-");
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceTempfield5()))
		newEntTlTrgCalEmp.setEtceTempfield5("-");
	  if(!UIUtils.isValidKeyId(newEntTlTrgCalEmp.getEtceCreatedby()))
		 newEntTlTrgCalEmp.setEtceCreatedby("{}");
	     return newEntTlTrgCalEmp;
}
public List<String[]> getTrainingCalendarList(GridParams gridparams,CommonFilter commonFilter) throws Exception {
	return newTrgCalDao.getTrainingCalendarList(gridparams,commonFilter);
}
public List<String[]> getEmpWiseTrainingReport(CommonFilter commonFilter) throws Exception {
	return newTrgCalDao.getEmpWiseTrainingReport(commonFilter);
}
public EntTlTragcalmst getselectdata(String calid) throws Exception {
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("entering select data impl  ========== ");
//	return newTrgCalDao.getselectdata(calid);
	return newTrgServApi.getselectdata(calid);
}

@Override
public String IsTrainingCompleted(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return newTrgCalDao.IsTrainingCompleted(commonFilter);
}

@Override
public List<String[]> getElementId(String loginflid, String loginlevel,
		String loginElementid, String empId) throws Exception {
	// TODO Auto-generated method stub
	return newTrgCalDao.getElementId(loginflid,loginlevel,loginElementid,empId);
}

@Override
public String getempdata(String calid) throws Exception {
	// TODO Auto-generated method stub
	//return newTrgCalDao.getempdata(calid);
	return newTrgServApi.getempdata(calid);
	
}

@Override
public String FacultyCheck(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return newTrgCalDao.FacultyCheck(commonFilter);
}

@Override
public String chkSessionDate(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
//	return newTrgCalDao.chkSessionDate(commonFilter);
	return newTrgServApi.chkSessionDate(commonFilter);
}

public List<EntTlTtgCalEmpatScore> createEmployeeAttendance(List<EntTlTtgCalEmpatScore> entEmployeeScore,CommonFilter commonfilter) throws Exception{
	//  return newTrgCalDao.createEmployeeAttendance(fillValuesEmployeeAttendance(entEmployeeScore),commonfilter);
	  return newTrgServApi.createEmployeeAttendance(fillValuesEmployeeAttendance(entEmployeeScore),commonfilter);
}

public EntTlTrgCalEmp EmployeebyCreate(EntTlTrgCalEmp newEntTlTrgCalEmp,EntTlTrgCalEmp existEntTlTrgCalEmp) throws Exception{
	   fillEmployeeByValues(newEntTlTrgCalEmp, existEntTlTrgCalEmp);
//	   return newTrgCalDao.EmployeebyCreate(newEntTlTrgCalEmp);
	   return newTrgServApi.EmployeebyCreate(newEntTlTrgCalEmp);
}

public EntTlTrgCalEmp EmployeeDelete(EntTlTrgCalEmp newEntTlTrgCalEmp) throws Exception{
	return newTrgCalDao.EmployeeDelete(newEntTlTrgCalEmp);
}
@Override
public String chkUniqueposition(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	// return newTrgCalDao.chkUniqueposition(commonFilter);
	 return newTrgServApi.chkUniqueposition(commonFilter);
}

@Override
public String chkEmployee(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	 return newTrgCalDao.chkEmployee(commonFilter);
}

@Override
public List<String[]> chkJHforRole(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	// return newTrgCalDao.chkJHforRole(commonFilter);
	 return newTrgServApi.chkJHforRole(commonFilter);
}
public String deleteDetailRecord(String keyId, String gridId, String TrainingId)throws Exception,BusinessApplicationExceptions{
   // return newTrgCalDao.deleteDetailRecord(keyId,gridId,TrainingId);
    return newTrgServApi.deleteDetailRecord(keyId,gridId,TrainingId);	
}

@Override
public List<EntTlTtgCalEmpatScore> UpdateEmployeeAttendance(
		List<EntTlTtgCalEmpatScore> employeeAddList) throws Exception {
	// TODO Auto-generated method stub
	return newTrgCalDao.UpdateEmployeeAttendance(fillValuesEmployeeAttendance(employeeAddList));
}

@Override
public String getempattn(String calid) throws Exception {
	// TODO Auto-generated method stub
//	return newTrgCalDao.getempattn(calid);
	return newTrgServApi.getempattn(calid);
}

@Override
public String getMaxmarks(String calid) throws Exception {
	// TODO Auto-generated method stub
//return newTrgCalDao.getMaxmarks(calid);
	return newTrgServApi.getMaxmarks(calid);
}

@Override
public String getCutoff(String calid) throws Exception {
	// TODO Auto-generated method stub
//	return newTrgCalDao.getCutoff(calid);
	return newTrgServApi.getCutoff(calid);
}

@Override
public String getAssesType(String calid) throws Exception {
	// TODO Auto-generated method stub
//	return newTrgCalDao.getAssesType(calid);
	return newTrgServApi.getAssesType(calid);
}

@Override
public String chkAssesmentComplted(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
//	return newTrgCalDao.chkAssesmentComplted(commonFilter);
	return newTrgServApi.chkAssesmentComplted(commonFilter);
}

@Override
public List<ComboBox> getTopic(CommonFilter commonFilter,
		ComboFilter comboFilter) throws Exception {
	// TODO Auto-generated method stub
	comboFilter.setIdField("TOPI_KEYID");
	comboFilter.setNameField("TOPI_NAME");
	comboFilter.setTableName(TableNames.TBL_ENT_Tl_TOPICMST);
	
	//getRange returns roleID
	 if(UIUtils.isValidKeyId(commonFilter.getRange())){
		 CommonMessage.debugMsg("Range is"+commonFilter.getRange());
		 comboFilter.setCondSql(" AND TOPI_KEYID in ( select TMTM_TOPI_KEYID from ENT_TL_UNIQPOSTOPIC_LINKMST where TMTM_ROLE_KEYID = '"+commonFilter.getRange()+"')" );
	 }
	 
	/* if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		 String cnd ="";
		 cnd = " AND TOPI_keyid in (SELECT TMTM_TOPI_KEYID FROM ENT_TL_UNIQPOSTOPIC_LINKMST,GEN_MV_FLIDHIERARCHY ";
		 cnd+= " WHERE INSTR( PARENTFLIDS||FLID ,'"+commonFilter.getFlid()+"')>0  " ;
		 cnd+= " AND FLID    = TMTM_FLID ) ";
		 CommonMessage.debugMsg("Flid is"+commonFilter.getFlid());
		 cnd = " AND TOPI_LOCATIONID=(SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='"+commonFilter.getLoss()+"')";
		 comboFilter.setCondSql(cnd);
	 } 	*/
	 if(UIUtils.isValidKeyId(commonFilter.getRelatedToMchMld())){
		 CommonMessage.debugMsg("Related is"+commonFilter.getRelatedToMchMld());
		 comboFilter.setCondSql(" AND TOPI_RELATEDTO = '" + commonFilter.getRelatedToMchMld()+"'");
	 }
	//CommonMessage.debugMsg(""+);
	/*String  sql = "select topi_keyid from  ent_tl_topicmst where TOPI_ISCHILD = 'Y' and TOPI_KEYID in ( ";
			sql	+="select trar_refid from ent_vw_trainingareachildpath ";
			sql	+="where instr(CHILDPATH, (SELECT trar_keyid FROM ent_tl_trainingarea ";
			sql	+="WHERE trar_parentid = '"+trarkeyid+"' ";
			sql	+="and trar_refid = '"+condsql+"')) > 0 and trar_reftype <> 'SPK')";*/
	 
	 CommonMessage.debugMsg(" In side the Fill Combo Topic"+comboFilter);
	return commonFilterDao.fillComboValues(comboFilter);	
}

@Override
public List<ComboBox> getFacultyComboList(CommonFilter commonFilter,
		ComboFilter comboFilter) throws Exception {
	// TODO Auto-generated method stub
	
	comboFilter.setIdField("EMPM_CODE");
//	comboFilter.setCodeField("EMPM_CODE");
	comboFilter.setNameField("EMPROLE");
	
	String flid = commonFilter.getFlid();
	String sectid=commonFilter.getSect();
	String locnid=commonFilter.getAbnAllch();
	//CommonMessage.debugMsg("locnid in serv"+locnid);
	
	if (UIUtils.isValidKeyId(locnid)) {
		StringBuffer sb = new StringBuffer();
	
	//   sb.append("	SELECT DISTINCT EMPM_KEYID id ,EMPM_NAME||'-'||EMPM_CODE text  from GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST where 1 = 1"); 
		//sb.append(" AND FRT_ROLE_KEYID=ROLE_KEYID AND FRT_EMPM_KEYID=EMPM_KEYID AND EMPM_ACTIVE='Y' AND FRT_ROLE_KEYID IN('AROL0005','AROL0003') AND FRT_FNLN_KEYID In (Select Fnln_Keyid From Gen_Tl_Functionallocn Where  " );
		sb.append("  AND EMPID=empm_keyid AND FLID IN (SELECT FNLN_KEYID FROM GEN_VW_FNLN WHERE ");
		sb.append(" FNLN_ORIGINALID IN ('"+locnid+"'))");
		//sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID='" + flid + "') ) " );
		//CommonMessage.debugMsg("inside of ser impl1234"+sb.toString());
		comboFilter.setCondSql(sb.toString());
	 
	}
	else{
		StringBuffer sb = new StringBuffer();
		//sb.append(" AND FRT_ROLE_KEYID=ROLE_KEYID AND FRT_EMPM_KEYID=EMPM_KEYID AND EMPM_ACTIVE='Y'  AND FRT_ROLE_KEYID IN('AROL0005','AROL0003')  " );
	//	sb.append(" FNLN_ORIGINALID IN ('"+sectid+"'))");
		//sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID='" + flid + "') ) " );
		comboFilter.setCondSql(sb.toString());
	
	}
	//comboFilter.setTableName("GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST");
	comboFilter.setTableName("ENT_VW_FACULTYMST , GEN_TL_EMPLOYEEMST");
	
	return commonFilterDao.fillComboValues(comboFilter);
}
public Workbook getAllEmployeeExcel(JSONObject colmodel, String format,
		CommonFilter commonFilter) throws  Exception {
	// TODO Auto-generated method stub
	return newTrgCalDao.getAllEmployeeExcel(colmodel,format, commonFilter);
}

public Workbook getTrainingCalendarListExcel(JSONObject colmodel, String format,
	CommonFilter commonFilter) throws  Exception {
// TODO Auto-generated method stub
return newTrgCalDao.getTrainingCalendarListExcel(colmodel,format, commonFilter);
}

public Workbook getEmpWiseTrainingCalendarListExcel(JSONObject colmodel, String format,
		CommonFilter commonFilter) throws  Exception {
	// TODO Auto-generated method stub
	return newTrgCalDao.getEmpWiseTrainingCalendarListExcel(colmodel,format, commonFilter);
	}

@Override
public String getTrainingMode(String topicid) throws Exception {
	// TODO Auto-generated method stub
	return newTrgCalDao.getTrainingMode(topicid);
}

@Override
public String getTrainingTopicType(String topicid) throws Exception {
	// TODO Auto-generated method stub
	return newTrgCalDao.getTrainingTopicType(topicid);
}
public List<String[]> gwtJHRoleUniquePos(CommonFilter commonFilter) throws Exception{
//	return newTrgCalDao.gwtJHRoleUniquePos(commonFilter);
	return newTrgServApi.gwtJHRoleUniquePos(commonFilter);
}

public String getJHFlid(String Flid)throws Exception{
	return newTrgCalDao.getJHFlid(Flid);
}
public List<EntTlTrgCalUnqp> CreateMultipleUnique(List<EntTlTrgCalUnqp> entTlTrgCalUnqpLink) throws Exception{
	return newTrgCalDao.CreateMultipleUnique(fillValuesMultipleUniquePos(entTlTrgCalUnqpLink));
}


private List<EntTlTrgCalUnqp> fillValuesMultipleUniquePos( List<EntTlTrgCalUnqp> entTlTrgCalUnqpLink){
	List<EntTlTrgCalUnqp> newEntTlSessionEmployeeLinks=entTlTrgCalUnqpLink;
	List<EntTlTrgCalUnqp> newEntTlTrgCalUnqpLinkList = new ArrayList<EntTlTrgCalUnqp>();
	int index=0;
	if(entTlTrgCalUnqpLink!=null)
	for( EntTlTrgCalUnqp genEntTlTrgCalUnqp : newEntTlSessionEmployeeLinks){	
		String dateTime = CommonFunctions.dateTimeNow();			
		EntTlTrgCalUnqp genEntTlTrgCalUnqpLink1=newEntTlSessionEmployeeLinks.get(index);
		index++;	
		genEntTlTrgCalUnqpLink1.setEtcuActive("Y");
		genEntTlTrgCalUnqpLink1.setEtcuCreatedon(dateTime);
		genEntTlTrgCalUnqpLink1.setEtcuModifiedon(dateTime);
		genEntTlTrgCalUnqpLink1.setEtcuDateAdd(dateTime);
			
		if(!UIUtils.isValidKeyId(genEntTlTrgCalUnqpLink1.getEtcuEtcmKeyid())){
			genEntTlTrgCalUnqpLink1.setEtcuEtcmKeyid("{}");
		 }
		
		if(!UIUtils.isValidKeyId(genEntTlTrgCalUnqpLink1.getEtcuRoleDmt())){
			genEntTlTrgCalUnqpLink1.setEtcuRoleDmt("{}");
		 }

		if(!UIUtils.isValidKeyId(genEntTlTrgCalUnqpLink1.getEtcuRoleJh())){
			genEntTlTrgCalUnqpLink1.setEtcuRoleJh("{}");
		 }
		if(!UIUtils.isValidKeyId(genEntTlTrgCalUnqpLink1.getEtcuCreatedby())){
			genEntTlTrgCalUnqpLink1.setEtcuCreatedby("{}");
		 }
		
		if(!UIUtils.isValidKeyId(genEntTlTrgCalUnqpLink1.getEtcuTempfield1())){
			genEntTlTrgCalUnqpLink1.setEtcuTempfield1("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlTrgCalUnqpLink1.getEtcuTempfield2())){
			genEntTlTrgCalUnqpLink1.setEtcuTempfield2("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlTrgCalUnqpLink1.getEtcuTempfield3())){
			genEntTlTrgCalUnqpLink1.setEtcuTempfield3("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlTrgCalUnqpLink1.getEtcuTempfield4())){
			genEntTlTrgCalUnqpLink1.setEtcuTempfield4("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlTrgCalUnqpLink1.getEtcuTempfield5())){
			genEntTlTrgCalUnqpLink1.setEtcuTempfield5("-");
		 }
		
		newEntTlTrgCalUnqpLinkList.add(genEntTlTrgCalUnqpLink1);
}
return newEntTlTrgCalUnqpLinkList;
}

   @Override
	public List<ComboBox> getETTradeComboList(ComboFilter comboFilter) throws Exception{
	    String TableName="ENT_TL_TRGCALFUNCTION";
	    comboFilter.setIdField("ETFN_KEYID");
		comboFilter.setNameField("ETFN_NAME");
		comboFilter.setTableName(TableName);
		return commonFilterDao.fillComboValues(comboFilter);
	}
   
   
   @Override
   public String getAssessmentComStatus(String calid) throws Exception {
       return newTrgServApi.getAssessmentComStatus(calid);
   }

  

    @Override
      public String resetAssessmentForMaintenance(CommonFilter commonFilter) throws Exception {
          return newTrgServApi.resetAssessmentForMaintenance(commonFilter.getKey());
      }


}
