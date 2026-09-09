package com.akranta.tpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.TrainingBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.TrainingBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
//import com.akranta.tpm.dao.StpTlServiceprocessdtlDao;
import com.akranta.tpm.dao.GridBasedTrainigCalendarDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GridBasedTrainigCalendarDaoImpl;
//import com.akranta.tpm.dao.impl.StpTlServiceprocessdtlDaoImpl;
//import com.akranta.tpm.dao.impl.StpTlServicerequestmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalmst;
import com.akranta.tpm.model.EntTlTrgCalSession;
import com.akranta.tpm.model.EntTlTrgCalUnqp;
import com.akranta.tpm.model.EntTlTrgFaculty;
import com.akranta.tpm.model.EntTlTtgCalEmpatScore;
import com.akranta.tpm.service.GridBasedTrainingCalendarService;
import com.akranta.tpm.service.api.GridBasedTrainingCalendarServiceApi;
import com.akranta.tpm.service.api.NewTrainingcalendarServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class GridBasedTrainingCalendarServiceImpl implements GridBasedTrainingCalendarService {


	private  GridBasedTrainigCalendarDao gridBasedTrainigCalendarDao;
	//private StpTlServiceprocessdtlDao stpTlServiceprocessdtlDao;

	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	NewTrainingcalendarServiceApi newTrgServApi;
	GridBasedTrainingCalendarServiceApi gridTrgServApi ;
	
		public GridBasedTrainingCalendarServiceImpl(DBActionTemplate dbActionTemplate) 
		{
			gridBasedTrainigCalendarDao =  new GridBasedTrainigCalendarDaoImpl(dbActionTemplate);
		//	stpTlServiceprocessdtlDao =new StpTlServiceprocessdtlDaoImpl(dbActionTemplate);
			commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
			validations = new Validations();		
		}
		public void GridBasedTrainingCalendarServiceImplJwt(String JwtToken){
		    try{
		   // 	oplTlMstDao.OplTlMstDaoImplJwt(JwtToken);   // dao side
		        // (Optional) if you want service-level direct access
		    //	newTrgCalDao.NewTrgCalDaoImplJwt(JwtToken);
		    newTrgServApi = new NewTrainingcalendarServiceApi(JwtToken);
		    gridTrgServApi = new GridBasedTrainingCalendarServiceApi(JwtToken);
		    
		    } catch(Exception e){
		        e.printStackTrace();
		    }
		}
		
		@Override
		public List<String[]> getElementId(String loginflid, String loginlevel,
				String loginElementid, String empId) throws Exception {
			// TODO Auto-generated method stub
			return gridBasedTrainigCalendarDao.getElementId(loginflid, loginlevel, loginElementid,  empId);
		}

		@Override
		public List<String[]> getListTrgCalendar(CommonFilter commonFilter,
				GridParams gridParams) throws Exception {
			// TODO Auto-generated method stub
		//	return gridBasedTrainigCalendarDao.getListTrgCalendar(commonFilter,gridParams);
			return gridTrgServApi.getListTrgCalendar(commonFilter,gridParams);
		}

		@Override
		public List<String[]> getAllUniqueEmployeePopup(
				CommonFilter commonFilter) throws Exception {
			// TODO Auto-generated method stub
		//	return gridBasedTrainigCalendarDao.getAllUniqueEmployeePopup(commonFilter);
			return gridTrgServApi.getAllUniqueEmployeePopup(commonFilter);
		}

		@Override
		public List<String[]> getsession(String trgcalKeyid) throws Exception {
			// TODO Auto-generated method stub
		//	return gridBasedTrainigCalendarDao.getsession(trgcalKeyid);
			return gridTrgServApi.getsession(trgcalKeyid);
		}

		@Override
		public List<String[]> getNewUniqPosData(String trainingKeyid)
				throws Exception {
			// TODO Auto-generated method stub
	//		return gridBasedTrainigCalendarDao.getNewUniqPosData(trainingKeyid);
			return gridTrgServApi.getNewUniqPosData(trainingKeyid);
		}

		@Override
		public EntTlTragcalmst create(EntTlTragcalmst newentTlTragcalmst,EntTlTragcalmst existEntTlTragcalmst) throws Exception , BusinessApplicationExceptions {
				// TODO Auto-generated method stub
			        String XmlName="NewTrainingCalendar";
					String validationsFor = "create";
					CommonMessage.debugMsg("Inside the ServiceImpl Create");
			 //	validations.validate(newentTlTragcalmst,XmlName,validationsFor);
					// if(newentTlTragcalmst.getsessionMaster()!= null )
						 CommonMessage.debugMsg("Inside the Sessionmaster");
						// validations.validate(newentTlTragcalmst.getsessionMaster(),XmlName,validationsFor);
					     fillValuess(newentTlTragcalmst,existEntTlTragcalmst);
					     CommonMessage.debugMsg("After Filling tool Values");
					//for inserting in clisCalendar procedure
				       return  gridBasedTrainigCalendarDao.create(newentTlTragcalmst);
			}
			
		//}

		@Override
		public EntTlTragcalmst update(EntTlTragcalmst newentTlTragcalmst,
				EntTlTragcalmst existEntTlTragcalmst) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<EntTlTtgCalEmpatScore> employeeAttendance(
				List<EntTlTtgCalEmpatScore> employeeAddList,CommonFilter commonfilter) {
				  try {
					return gridBasedTrainigCalendarDao.employeeAttendance(fillValuesEmployeeAttendance(employeeAddList),commonfilter);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		

		@Override
		public String getempattn(String masterid) {
			// TODO Auto-generated method stub
			try {
			//	return  gridBasedTrainigCalendarDao.getempattn(masterid);
				return  gridTrgServApi.getempattn(masterid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			return null;
		}
		private List<EntTlTtgCalEmpatScore> fillValuesEmployeeAttendance(List<EntTlTtgCalEmpatScore> entTlEmployeeAttendanceLink){  		
			List<EntTlTtgCalEmpatScore> newEntTlEmployeeAttendanceLinks =entTlEmployeeAttendanceLink;
			List<EntTlTtgCalEmpatScore> newEntTlEmployeeLinkList = new ArrayList<EntTlTtgCalEmpatScore>();
			int index=0;
			if(entTlEmployeeAttendanceLink!=null)
			for( EntTlTtgCalEmpatScore genEntTlEmployeeAttendanceLink : newEntTlEmployeeAttendanceLinks)
			{	
				String dateTime = CommonFunctions.pg_dateTimeNow();
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
						genEntTlEmployeeAttendanceLink.setEtcaType("0");
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

	
		private void   fillValuess(EntTlTragcalmst entTlTragcalmst,EntTlTragcalmst existntTlTragcalmst) throws Exception{
		    
			String dateTime=CommonFunctions.dateTimeNow();
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
				  CommonMessage.debugMsg("getsessionMaster Info Service");
				  fillSessionValues(entTlTragcalmst.getsessionMaster());
			  }
			 	if(entTlTragcalmst.getFaculty()!= null){
				CommonMessage.debugMsg("GetFaculty Info ert Service");
				fillFacultyValues(entTlTragcalmst.getFaculty());
			}
		      if(entTlTragcalmst.getRoleLink() !=null){
		    	  CommonMessage.debugMsg("fillUniquePositionValues Info Service");
				fillUniquePositionValues(entTlTragcalmst.getRoleLink());
			}
		      
		}
		
		private void fillSessionValues(EntTlTrgCalSession newntTlTrgCalSession) throws Exception{
			
			newntTlTrgCalSession.setEtcsActive("Y");
			CommonMessage.debugMsg("lllllll  fillSessionValues");
			String dateTime = CommonFunctions.pg_dateTimeNow();
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
			 
			 CommonMessage.debugMsg(newntTlTrgCalSession.getEtcsTillDate()+"in sid ethe serice impl"+newntTlTrgCalSession.getEtcsFromDate());
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

		@Override
		public List<EntTlTragcalmst> create(List<EntTlTragcalmst> abnormalityList,TrainingBean bean) throws Exception {
			
			CommonMessage.debugMsg(abnormalityList.get(0).getFaculty()+" In sid the Fill Values");
			EntTlTragcalmst entTlTragcalmst=new EntTlTragcalmst();

				if(abnormalityList.get(0).getFaculty()!= null){
					
					entTlTragcalmst.setFaculty(abnormalityList.get(0).getFaculty());
					
			CommonMessage.debugMsg("GetFaculty Info Service3e");
			fillFacultyValues(entTlTragcalmst.getFaculty());
		}
	      if(abnormalityList.get(0).getRoleLink() !=null){
				CommonMessage.debugMsg("GetFaculty Info Service 2");
				entTlTragcalmst.setRoleLink(abnormalityList.get(0).getRoleLink());
			fillUniquePositionValues(entTlTragcalmst.getRoleLink());
		}
	      if(abnormalityList.get(0).getsessionMaster() !=null){
				CommonMessage.debugMsg("GetFaculty Info Service 2");
				entTlTragcalmst.setsessionMaster(abnormalityList.get(0).getsessionMaster());
				fillSessionValues(entTlTragcalmst.getsessionMaster());
		}
				//	return gridBasedTrainigCalendarDao.GrdBsdTrgCalcreate(TrngCalfillValues(abnormalityList,bean));
					return gridTrgServApi.GrdBsdTrgCalcreate(TrngCalfillValues(abnormalityList,bean));

		}


		@Override
		public EntTlTrgCalSession createSession(EntTlTrgCalSession newentTlTrgCalSession,EntTlTrgCalSession existntTlTrgCalSession) throws Exception {
			// TODO Auto-generated method stub
			  fillSessionValues(newentTlTrgCalSession);

	//		return gridBasedTrainigCalendarDao.createSession(newentTlTrgCalSession);
			return gridTrgServApi.createSession(newentTlTrgCalSession); // must call api from here 
		}
		
		public void fillFacultyValues(EntTlTrgFaculty  newentTlTrgFaculty) throws Exception{

			//facultyTopicLink.setFtlkActive("Y");
			//EntTlTrgFaculty newentTlTrgFaculty =new EntTlTrgFaculty();
			CommonMessage.debugMsg(" In side the Servide impl Fill values");
			newentTlTrgFaculty.setEtcfActive("Y");
			String dateTime = CommonFunctions.pg_dateTimeNow();
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
			CommonMessage.debugMsg(" In side the Servide impl fillUniquePositionValues values");

			  newentTlTrgCalUnqp.setEtcuActive("Y");
			  String dateTime=CommonFunctions.pg_dateTimeNow();
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
			 
			 if(UIUtils.isValidKeyId(commonFilter.getFlid())){
				 String cnd ="";
				 cnd = " AND TOPI_LOCATIONID = '"+commonFilter.getFlid()+"'" ;
				/* cnd+= " AND FLID    = TMTM_FLID ) ";*/
				 CommonMessage.debugMsg("Flid is"+commonFilter.getFlid());
				 comboFilter.setCondSql(cnd);
			 } 	
			 if(UIUtils.isValidKeyId(commonFilter.getRelatedToMchMld())){
				 CommonMessage.debugMsg("Related is"+commonFilter.getRelatedToMchMld());
				 comboFilter.setCondSql(" AND TOPI_RELATEDTO = '" + commonFilter.getRelatedToMchMld()+"'");
			 }
			
			 CommonMessage.debugMsg(" In side the Fill Combo Topic"+comboFilter);
			return commonFilterDao.fillComboValues(comboFilter);	
		}
		private List<EntTlTragcalmst> TrngCalfillValues(List<EntTlTragcalmst> newEntTlTragcalmst,
				TrainingBean newAbnormalityBean) {
			// TODO Auto-generated method stub
			
			List<EntTlTragcalmst> trgCalLink =newEntTlTragcalmst;
	   		List<EntTlTragcalmst> trgCalDtlLinkList = new ArrayList<EntTlTragcalmst>();
	   		int index=0;
			String currentDate = CommonFunctions.pg_getDate();
			//EntTlTragcalmst trgCaldata;
	   		if(trgCalLink!=null)
	   		for( EntTlTragcalmst trgCaldata : trgCalLink)
	   		{	
	   			String dateTime = CommonFunctions.pg_dateTimeNow();
	   			EntTlTragcalmst genEntTlBatchEmployeeLink1 = trgCalLink.get(index);
	   			index++;
	   			trgCaldata.setEtcmActive("Y");
	   			trgCaldata.setEtcmModifiedOn(dateTime);
	   			trgCaldata.setEtcmCreatedOn(dateTime);
	   		//	String tagClass=abnormalitydata.getAbnmTagclassid();
	   		//	String Countermeaure=abnormalitydata.getAbnmCountermeasure();
	   			CommonMessage.debugMsg(trgCaldata.getEtcmDmt()+"  The Countermeaure:::"+trgCaldata.getEtcmFlid());
	   			
	   			

				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmFlid())){
					trgCaldata.setEtcmFlid("{}");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmLocation())){
					trgCaldata.setEtcmLocation("{}");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmDmt())){
					trgCaldata.setEtcmDmt("{}");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmJh())){
					trgCaldata.setEtcmJh("{}");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTopicid())){
					trgCaldata.setEtcmTopicid("{}");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmCreatedDateTime())){
					trgCaldata.setEtcmCreatedDateTime(dateTime);
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmRemarks())){
					trgCaldata.setEtcmRemarks("-");
				 }
				 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmCalendarDate())){
					trgCaldata.setEtcmCalendarDate(dateTime);
				 }
				 if(trgCaldata.getEtcmGeneral().equals("UQ")){
					 trgCaldata.setEtcmUniqueposition("Y");
					 trgCaldata.setEtcmGeneral("N");
					 trgCaldata.setEtcmMSD("N");
				 }
				 else if (trgCaldata.getEtcmGeneral().equals("GN")){
					 trgCaldata.setEtcmUniqueposition("N");
					 trgCaldata.setEtcmGeneral("Y");
					 trgCaldata.setEtcmMSD("N");
				 }
				 else if ((trgCaldata.getEtcmGeneral().equals("MS"))){
					 trgCaldata.setEtcmMSD("Y");
					 trgCaldata.setEtcmUniqueposition("N");
					 trgCaldata.setEtcmGeneral("N");
				 }
				 else{
					 trgCaldata.setEtcmUniqueposition("N");
					 trgCaldata.setEtcmGeneral("Y");
					 trgCaldata.setEtcmMSD("N");
					 
				 }
				/*if(!UIUtils.isValidKeyId(trgCaldata.getEtcmGeneral())){
					trgCaldata.setEtcmGeneral("N");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmUniqueposition())){
					trgCaldata.setEtcmUniqueposition("N");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmMSD())){
					trgCaldata.setEtcmMSD("N");
				 }*/
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmChkCompleted())){
					trgCaldata.setEtcmChkCompleted("N");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmCompletedDate())){
					trgCaldata.setEtcmCompletedDate(dateTime);
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmCompletedBy())){
					trgCaldata.setEtcmCompletedBy("-");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmMaxDuration())){
					trgCaldata.setEtcmMaxDuration("0");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmFunction())){
					trgCaldata.setEtcmFunction("-");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmVenue())){
					trgCaldata.setEtcmVenue("-");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmPermittedStrength())){
					trgCaldata.setEtcmPermittedStrength("0");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmMaterialsReady())){
					trgCaldata.setEtcmMaterialsReady("N");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmAssessmentReq())){
					trgCaldata.setEtcmAssessmentReq("N");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmMarksBased())){
					trgCaldata.setEtcmMarksBased("N");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmFileManagedId())){
					trgCaldata.setEtcmFileManagedId("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmFileManagedId())){
					trgCaldata.setEtcmFileManagedId("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmAnchoredby())){
					trgCaldata.setEtcmAnchoredby("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTrainingfunction())){
					trgCaldata.setEtcmTrainingfunction("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmRating())){
					trgCaldata.setEtcmRating("0");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmComments())){
					trgCaldata.setEtcmComments("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTopiccategory())){
					trgCaldata.setEtcmTopiccategory("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTempfield6())){
					/*if(trgCaldata.getEtcmTempfield6().equals("NO")){
						trgCaldata.setEtcmTempfield6("N");

					}
					else if(trgCaldata.getEtcmTempfield6().equals("YES")){
							trgCaldata.setEtcmTempfield6("Y");
					}
					else{*/
						trgCaldata.setEtcmTempfield6("N");

					
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTempfield7())){
					trgCaldata.setEtcmTempfield7("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTempfield8())){
					trgCaldata.setEtcmTempfield8("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTempfield9())){
					trgCaldata.setEtcmTempfield9("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTempfield10())){
					trgCaldata.setEtcmTempfield10("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmCreatedBy())){
					trgCaldata.setEtcmCreatedBy("{}");
				 }
				trgCalDtlLinkList.add(trgCaldata);
	   		}
			return trgCalDtlLinkList;
		}

		@Override
		public EntTlTragcalmst create(List<EntTlTragcalmst> abnormalityList,
				EntTlTragcalmst existEntTlTragcalmst) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EntTlTragcalmst create(List<EntTlTragcalmst> abnormalityList)
				throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public EntTlTrgCalSession updateSession(
				EntTlTrgCalSession newentTlTrgCalSession,
				EntTlTrgCalSession existntTlTrgCalSession) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String chkSessionDate(CommonFilter commonFilter)
				throws Exception {
			// TODO Auto-generated method stub
			return gridBasedTrainigCalendarDao.chkSessionDate(commonFilter);
		}

		@Override
		public String chkUniqueposition(CommonFilter commonFilter)
				throws Exception {
			// TODO Auto-generated method stub
		//	return  gridBasedTrainigCalendarDao.chkUniqueposition(commonFilter);
			return  gridTrgServApi.chkUniqueposition(commonFilter);
		}

		@Override
		public EntTlTrgCalUnqp createUniquePostion(
				EntTlTrgCalUnqp newentTlTrgCalUnqp,
				EntTlTrgCalUnqp existntTlTrgCalUnqp) throws Exception {
			// TODO Auto-generated method stub
			fillUniquePositionValues(newentTlTrgCalUnqp);
			CommonMessage.debugMsg(newentTlTrgCalUnqp.getEtcuRoleDmt() +" In side the Service impl"+newentTlTrgCalUnqp.getEtcuEtcmKeyid());
		//	return gridBasedTrainigCalendarDao.createUniquePostion(newentTlTrgCalUnqp);
			return gridTrgServApi.createUniquePostion(newentTlTrgCalUnqp);
		}

		private void UniquePositionFilValues(EntTlTrgCalUnqp newentTlTrgCalUnqp) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public EntTlTrgCalUnqp updateUniquePostion(
				EntTlTrgCalUnqp newentTlTrgCalUnqp,
				EntTlTrgCalUnqp existntTlTrgCalUnqp) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<String[]> getFaculty(String progKeyid) throws Exception {
			// TODO Auto-generated method stub
		//	return gridBasedTrainigCalendarDao.getFaculty(progKeyid);
			return gridTrgServApi.getFaculty(progKeyid);
		}

		@Override
		public String FacultyCheck(CommonFilter commonFilter) throws Exception {
			// TODO Auto-generated method stub
			return gridBasedTrainigCalendarDao.FacultyCheck(commonFilter);
		}

		@Override
		public EntTlTrgFaculty createFaculty(
				EntTlTrgFaculty newEntTlTrgFaculty,
				EntTlTrgFaculty existEntTlTrgFaculty) throws Exception {
			// TODO Auto-generated method stub
			fillFacultyValues(newEntTlTrgFaculty);
		//	return gridBasedTrainigCalendarDao.createFaculty(newEntTlTrgFaculty);
			return gridTrgServApi.createFaculty(newEntTlTrgFaculty);
		}

		@Override
		public EntTlTrgFaculty updateFaculty(
				EntTlTrgFaculty newEntTlTrgFaculty,
				EntTlTrgFaculty existEntTlTrgFaculty) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<String[]> getAllEmployee(CommonFilter commonFilter,
				GridParams gridParams) throws Exception {
			// TODO Auto-generated method stub
		//	return gridBasedTrainigCalendarDao.getAllEmployee(commonFilter,gridParams);
			return gridTrgServApi.getAllEmployee(commonFilter,gridParams);
		}

		@Override
	
			
			public List<ComboBox> getRoleComboList(CommonFilter commonFilter)throws Exception {
				ComboFilter  training = commonFilter.getRoleId();
				training.setIdField("ROLE_KEYID");		
				training.setNameField("ROLE_NAME");	
				CommonMessage.debugMsg(commonFilter.getFlid() +  " commonfiltertopic ");
				CommonMessage.debugMsg(commonFilter.getSectionId() +  " commonfiltertopic ");

				String keyIdCnd="";
				if(UIUtils.isValidKeyId(commonFilter.getKey() ))
					keyIdCnd = " AND ROLE_KEYID = '" + commonFilter.getKey() + "' "; 

				
				//if(UIUtils.isValidKeyId(commonFilter.getSectionId())) {
					training.setNameField("ROLE_NAME  ||'-' || FNLN_DISPLAYCODE");
					String cnd = "  AND ROLE_FLID = FLID ";
					
					//changed on 04-aug-2014
					if (UIUtils.isValidKeyId(commonFilter.getSectionId()))
						cnd+= " AND INSTR( PARENTFLIDS||FLID ,(SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID='"+commonFilter.getSectionId()+"'))>0    ";
					else				
						cnd+= " AND FLID  = (SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID='"+commonFilter.getCellId()+"') ";
					//cnd+= " AND INSTR( PARENTFLIDS||FLID ,'"+commonFilter.getFlid()+"')>0    ";  
					training.setCondSql( cnd + keyIdCnd);
				    CommonMessage.debugMsg("keyIdCnd:"+keyIdCnd);
				    CommonMessage.debugMsg("con: in side the Dao Impl"+cnd);
					// training.setTableName(" GEN_TL_ROLEMST, GEN_MV_FLIDHIERARCHY ");
					training.setTableName(" Ent_Vw_Rolemst  ");
				/*}
				else
					training.setTableName(TableNames.TBL_GEN_TL_ROLEMST);	
				*///select  TMTM_ROLE_KEYID from  ent_tl_task_mapping_topicmst where TMTM_TOPI_KEYID ='TOP0000072'
				CommonMessage.debugMsg("Training Data"+keyIdCnd);
				return commonFilterDao.fillComboValues(training);
			}

		@Override
		public List<String[]> gwtJHRoleUniquePos(CommonFilter commonFilter)
				throws Exception {
			// TODO Auto-generated method stub
		//	return gridBasedTrainigCalendarDao.gwtJHRoleUniquePos(commonFilter);
			return gridTrgServApi.gwtJHRoleUniquePos(commonFilter);
		}

		@Override
		public List<EntTlTtgCalEmpatScore> createEmployeeAttendance(
				List<EntTlTtgCalEmpatScore> employeeAddList,
				CommonFilter commonfilter) throws Exception {
			
			CommonMessage.debugMsg(" In sid the sERVICE mpl");
			// TODO Auto-generated method stub
		//	  return gridBasedTrainigCalendarDao.createEmployeeAttendance(fillValuesEmployeeAttendanceList(employeeAddList),commonfilter);
			  return gridTrgServApi.createEmployeeAttendance(fillValuesEmployeeAttendanceList(employeeAddList),commonfilter);

		}
			
		private List<EntTlTtgCalEmpatScore> fillValuesEmployeeAttendanceList(List<EntTlTtgCalEmpatScore> entTlEmployeeAttendanceLink){  		
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
						genEntTlEmployeeAttendanceLink.setEtcaType("0");
					else
						genEntTlEmployeeAttendanceLink.setEtcaType("W");
				}			    
				
				if(! UIUtils.isValidKeyId(genEntTlEmployeeAttendanceLink.getEtcaDept())){
					genEntTlEmployeeAttendanceLink.setEtcaDept("-");
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

		@Override
		public List<EntTlTragcalmst> update(
				List<EntTlTragcalmst> abnormalityList,
				TrainingBean bean) throws Exception {
			// TODO Auto-generated method stub
		//	return gridBasedTrainigCalendarDao.GrdBsdTrgCalUpdate(TrngCalfillValues(abnormalityList,bean));
			return gridTrgServApi.GrdBsdTrgCalUpdate(TrngCalfillValues(abnormalityList,bean));

		}
public void DeleteCal(String keyid) throws Exception{
//	this.gridBasedTrainigCalendarDao.DeleteCal(keyid);
	this.gridTrgServApi.DeleteCal(keyid);
}

public List<String[]> getListTrgCalendarModify(CommonFilter commonFilter,
		GridParams gridParams) throws Exception {
	// TODO Auto-generated method stub
//	return gridBasedTrainigCalendarDao.getListTrgCalendarModify(commonFilter,gridParams);
	return gridTrgServApi.getListTrgCalendarModify(commonFilter,gridParams);
}
public List<String[]> getListTrgCalendarView(CommonFilter commonFilter,
		GridParams gridParams) throws Exception {
	// TODO Auto-generated method stub
//	return gridBasedTrainigCalendarDao.getListTrgCalendarView(commonFilter,gridParams);
	return gridTrgServApi.getListTrgCalendarView(commonFilter,gridParams);
}
public String deleteDetailRecord(String keyId, String gridId, String topicId) throws Exception,BusinessApplicationExceptions {
	//return gridBasedTrainigCalendarDao.deleteDetailRecord(keyId,gridId,topicId);
	return gridTrgServApi.deleteDetailRecord(keyId,gridId,topicId);
}

@Override
public List<EntTlTrgCalUnqp> CreateMultipleUnique(
		List<EntTlTrgCalUnqp> uniqueAddList) throws Exception {
	// TODO Auto-generated method stub
//	return gridBasedTrainigCalendarDao.CreateMultipleUnique(fillValuesMultipleUniquePos(uniqueAddList));
	return newTrgServApi.CreateMultipleUnique(fillValuesMultipleUniquePos(uniqueAddList));
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
	//	@Override
		/*public EntTlTragcalmst create(List<EntTlTragcalmst> abnormalityList,
				EntTlTragcalmst existEntTlTragcalmst) throws Exception {
			// TODO Auto-generated method stub
			return gridBasedTrainigCalendarDao.create(TrngCalfillValues(abnormalityList,existEntTlTragcalmst));

		}
	   		
		
		private List<EntTlTragcalmst> TrngCalfillValues(List<EntTlTragcalmst> newEntTlTragcalmst,
				AbnormalityBean newAbnormalityBean) {
			// TODO Auto-generated method stub
			
			List<EntTlTragcalmst> trgCalLink =newEntTlTragcalmst;
	   		List<EntTlTragcalmst> trgCalDtlLinkList = new ArrayList<EntTlTragcalmst>();
	   		int index=0;
			String currentDate = CommonFunctions.getDate();
			//EntTlTragcalmst trgCaldata;
	   		if(trgCalLink!=null)
	   		for( EntTlTragcalmst trgCaldata : trgCalLink)
	   		{	
	   			String dateTime = CommonFunctions.dateTimeNow();
	   			EntTlTragcalmst genEntTlBatchEmployeeLink1 = trgCalLink.get(index);
	   			index++;
	   			trgCaldata.setEtcmActive("Y");
	   			trgCaldata.setEtcmModifiedOn(dateTime);
	   			trgCaldata.setEtcmCreatedOn(dateTime);
	   		//	String tagClass=abnormalitydata.getAbnmTagclassid();
	   		//	String Countermeaure=abnormalitydata.getAbnmCountermeasure();
	   		//	CommonMessage.debugMsg("The Countermeaure:::"+Countermeaure);
	   			
	   			

				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmFlid())){
					trgCaldata.setEtcmFlid("{}");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmLocation())){
					trgCaldata.setEtcmLocation("{}");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmDmt())){
					trgCaldata.setEtcmDmt("{}");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmJh())){
					trgCaldata.setEtcmJh("{}");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTopicid())){
					trgCaldata.setEtcmTopicid("{}");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmCreatedDateTime())){
					trgCaldata.setEtcmCreatedDateTime(dateTime);
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmRemarks())){
					trgCaldata.setEtcmRemarks("-");
				 }
				 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmCalendarDate())){
					trgCaldata.setEtcmCalendarDate(dateTime);
				 }
				 if(trgCaldata.getEtcmGeneral().equals("UQ")){
					 trgCaldata.setEtcmUniqueposition("Y");
					 trgCaldata.setEtcmGeneral("N");
					 trgCaldata.setEtcmMSD("N");
				 }
				 else if (trgCaldata.getEtcmGeneral().equals("GN")){
					 trgCaldata.setEtcmUniqueposition("N");
					 trgCaldata.setEtcmGeneral("Y");
					 trgCaldata.setEtcmMSD("N");
				 }
				 else if ((trgCaldata.getEtcmGeneral().equals("MS"))){
					 trgCaldata.setEtcmMSD("Y");
					 trgCaldata.setEtcmUniqueposition("N");
					 trgCaldata.setEtcmGeneral("N");
				 }
				 else{
					 trgCaldata.setEtcmUniqueposition("N");
					 trgCaldata.setEtcmGeneral("Y");
					 trgCaldata.setEtcmMSD("N");
					 
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmGeneral())){
					trgCaldata.setEtcmGeneral("N");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmUniqueposition())){
					trgCaldata.setEtcmUniqueposition("N");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmMSD())){
					trgCaldata.setEtcmMSD("N");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmChkCompleted())){
					trgCaldata.setEtcmChkCompleted("N");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmCompletedDate())){
					trgCaldata.setEtcmCompletedDate(dateTime);
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmCompletedBy())){
					trgCaldata.setEtcmCompletedBy("-");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmMaxDuration())){
					trgCaldata.setEtcmMaxDuration("0");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmFunction())){
					trgCaldata.setEtcmFunction("-");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmVenue())){
					trgCaldata.setEtcmVenue("-");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmPermittedStrength())){
					trgCaldata.setEtcmPermittedStrength("0");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmMaterialsReady())){
					trgCaldata.setEtcmMaterialsReady("N");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmAssessmentReq())){
					trgCaldata.setEtcmAssessmentReq("N");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmMarksBased())){
					trgCaldata.setEtcmMarksBased("N");
				 } 
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmFileManagedId())){
					trgCaldata.setEtcmFileManagedId("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmFileManagedId())){
					trgCaldata.setEtcmFileManagedId("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmAnchoredby())){
					trgCaldata.setEtcmAnchoredby("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTrainingfunction())){
					trgCaldata.setEtcmTrainingfunction("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmRating())){
					trgCaldata.setEtcmRating("0");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmComments())){
					trgCaldata.setEtcmComments("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTopiccategory())){
					trgCaldata.setEtcmTopiccategory("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTempfield6())){
					trgCaldata.setEtcmTempfield6("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTempfield7())){
					trgCaldata.setEtcmTempfield7("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTempfield8())){
					trgCaldata.setEtcmTempfield8("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTempfield9())){
					trgCaldata.setEtcmTempfield9("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmTempfield10())){
					trgCaldata.setEtcmTempfield10("-");
				 }
				if(!UIUtils.isValidKeyId(trgCaldata.getEtcmCreatedBy())){
					trgCaldata.setEtcmCreatedBy("{}");
				 }
				trgCalDtlLinkList.add(trgCaldata);
	   		}
			return trgCalDtlLinkList;
		}*/

@Override
public EntTlTragcalmst getselectdata(String calendarkeyid) throws Exception {
	// TODO Auto-generated method stub
//	return  gridBasedTrainigCalendarDao.getselectdata(calendarkeyid);
	return  gridTrgServApi.getselectdata(calendarkeyid);
	
}

@Override
public String getAssesType(String calendarkeyid) throws Exception {
	// TODO Auto-generated method stub
//	return newTrgCalDao.getAssesType(calid);
	return gridTrgServApi.getAssesType(calendarkeyid);
}

@Override
public String getempdata(String calendarkeyid) throws Exception {
	// TODO Auto-generated method stub
// return gridBasedTrainigCalendarDao.getempdata(calendarkeyid);
	return gridTrgServApi.getempdata(calendarkeyid);
}

@Override
public String getMaxmarks(String calendarkeyid) throws Exception {
	// TODO Auto-generated method stub
//	return gridBasedTrainigCalendarDao.getMaxmarks(calendarkeyid);
	return gridTrgServApi.getMaxmarks(calendarkeyid);
}

@Override
public String getCutoff(String calendarkeyid) throws Exception {
	// TODO Auto-generated method stub
	//return gridBasedTrainigCalendarDao.getCutoff(calendarkeyid);
	return gridTrgServApi.getCutoff(calendarkeyid);
}

@Override
public String chkAssesmentComplted(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	// return gridBasedTrainigCalendarDao.chkAssesmentComplted(commonFilter);
	return gridTrgServApi.chkAssesmentComplted(commonFilter);
}

@Override
public String getTrgDateData(String keyId) throws Exception {
	// TODO Auto-generated method stub
	return gridBasedTrainigCalendarDao.getTrgDateData(keyId);
}
public String IsTrainingCompleted(CommonFilter commonFilter)
		throws Exception{
	return gridBasedTrainigCalendarDao.IsTrainingCompleted(commonFilter);
	}

@Override
public List<ComboBox> getUniquePosition(CommonFilter commonFilter,
		ComboFilter comboFilter) throws Exception {
	comboFilter.setIdField("ROLE_KEYID");
	comboFilter.setNameField("ROLE_NAME");
	comboFilter.setTableName(TableNames.TBL_GEN_TL_ROLEMST); 
	//getRange returns roleID
	 if(UIUtils.isValidKeyId(commonFilter.getKey())){
		 CommonMessage.debugMsg("Range is"+commonFilter.getKey());
		 comboFilter.setCondSql(" AND ROLE_KEYID IN ( SELECT ETCU_ROLE_KEYID  FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID = '"+commonFilter.getKey()+"')" );
	 }
	 return commonFilterDao.fillComboValues(comboFilter);
}

public List<ComboBox> getFaculty(CommonFilter commonFilter,
		ComboFilter comboFilter) throws Exception {
	comboFilter.setIdField("FTYM_KEYID");
	comboFilter.setNameField("FTYM_NAME"); 
	comboFilter.setTableName(TableNames.TBL_ENT_TL_FACULTYMST); 
	//getRange returns roleID
	 if(UIUtils.isValidKeyId(commonFilter.getKey())){
		 CommonMessage.debugMsg("Range is"+commonFilter.getKey());
		 comboFilter.setCondSql(" AND FTYM_KEYID IN ( SELECT ETCF_FACULTYID  FROM ENT_TL_TRGFACULTY WHERE ETCF_ETCM_KEYID = '"+commonFilter.getKey()+"')" );
	 }
	 return commonFilterDao.fillComboValues(comboFilter);
}

@Override
public Workbook getTrainingCalendarListExcel(JSONObject colmodel,String format, CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return gridBasedTrainigCalendarDao.getTrainingCalendarListExcel(colmodel,format,commonFilter);
}

	}


