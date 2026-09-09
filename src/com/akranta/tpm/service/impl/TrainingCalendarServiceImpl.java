 package com.akranta.tpm.service.impl;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ProgrammstBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
//import com.akranta.tpm.dao.EntTlRoleTrainingareaLinkDao;
import com.akranta.tpm.dao.TrainingCalendarDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
//import com.akranta.tpm.dao.impl.EntTlRoleTrainingareaLinkDaoImpl;
import com.akranta.tpm.dao.impl.TrainingCalendarDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntBatchMst;
import com.akranta.tpm.model.EntTlBatchSchedule;
import com.akranta.tpm.model.EntTlFacultytopiclink;
import com.akranta.tpm.model.EntTlProgTargetSkills;
import com.akranta.tpm.model.EntTlProgrammst;
import com.akranta.tpm.model.EntTlRoleTopicLink;
import com.akranta.tpm.model.EntTlUniqpostopicLinkmst;
import com.akranta.tpm.service.TrainingCalendarService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class TrainingCalendarServiceImpl implements TrainingCalendarService {

	private TrainingCalendarDao trainingCalendarDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	 
	public TrainingCalendarServiceImpl(DBActionTemplate dbActionTemplate) {		
		trainingCalendarDao = new TrainingCalendarDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	@Override
	public List<String[]> getTrainingCalendarList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.trainingCalendarDao.getTrainingCalendarList(commonFilter);
	}

	@Override
	public List<ComboBox> getTopic(CommonFilter commonFilter, ComboFilter comboFilter)
			throws Exception {
		// TODO Auto-generated method stub
		comboFilter.setIdField("TOPI_KEYID");
		comboFilter.setNameField("TOPI_NAME");
		comboFilter.setTableName(TableNames.TBL_ENT_Tl_TOPICMST);
		//getRange returns roleID
		 if(UIUtils.isValidKeyId(commonFilter.getRange())){
			 comboFilter.setCondSql(" AND TOPI_KEYID in ( select TMTM_TOPI_KEYID from ENT_TL_UNIQPOSTOPIC_LINKMST where TMTM_ROLE_KEYID = '"+commonFilter.getRange()+"')" );
		 }if(UIUtils.isValidKeyId(commonFilter.getFlid())){
			 String cnd ="";
			 cnd = " AND TOPI_keyid in (SELECT TMTM_TOPI_KEYID FROM ENT_TL_UNIQPOSTOPIC_LINKMST,GEN_MV_FLIDHIERARCHY ";
			 cnd+= " WHERE INSTR( PARENTFLIDS||FLID ,'"+commonFilter.getFlid()+"')>0  " ;
			 cnd+= " AND FLID    = TMTM_FLID ) ";
			 comboFilter.setCondSql(cnd);
		 } 	
		 if(UIUtils.isValidKeyId(commonFilter.getRelatedToMchMld())){
			 comboFilter.setCondSql(" AND TOPI_RELATEDTO = '" + commonFilter.getRelatedToMchMld()+"'");
		 }
		CommonMessage.debugMsg( "");
		/*String  sql = "select topi_keyid from  ent_tl_topicmst where TOPI_ISCHILD = 'Y' and TOPI_KEYID in ( ";
				sql	+="select trar_refid from ent_vw_trainingareachildpath ";
				sql	+="where instr(CHILDPATH, (SELECT trar_keyid FROM ent_tl_trainingarea ";
				sql	+="WHERE trar_parentid = '"+trarkeyid+"' ";
				sql	+="and trar_refid = '"+condsql+"')) > 0 and trar_reftype <> 'SPK')";*/
		 
		return commonFilterDao.fillComboValues(comboFilter);	
	}

	@Override
	public EntTlProgrammst create(EntTlProgrammst newEntTlProgrammst,
			EntTlProgrammst existEntTlProgrammst, ProgrammstBean programmstBean)
			throws ValidationExceptions,Exception, BusinessApplicationExceptions {
		// TODO Auto-generated method stub
			String validationsFor = "createTrngCal";
			CommonMessage.debugMsg("Inside the ServiceImpl Create");
			 validations.validate(newEntTlProgrammst,"Entprogram",validationsFor);
			 
			 if(newEntTlProgrammst.getBatchmaster() != null )
				 validations.validate(newEntTlProgrammst.getBatchmaster(),"Entprogram",validationsFor);
			fillValues(newEntTlProgrammst,existEntTlProgrammst,programmstBean);
			CommonMessage.debugMsg("After Filling tool Values");
			//for inserting in clisCalendar procedure
			
		return  trainingCalendarDao.create(newEntTlProgrammst,programmstBean);
	}

	private EntTlProgrammst fillValues(EntTlProgrammst newEntTlProgrammst,
			EntTlProgrammst existEntTlProgrammst, ProgrammstBean programmstBean) {
		// TODO Auto-generated method stub
		if(newEntTlProgrammst.getProgTopicId() != null ){
			CommonMessage.debugMsg("fillvalues  :"+newEntTlProgrammst.getProgKeyid());
			CommonMessage.debugMsg("fillvaluescrdton   :"+newEntTlProgrammst.getProgCreatedon());
	       }
		
			String dateTime = CommonFunctions.dateTimeNow();
			CommonMessage.debugMsg(dateTime);
			
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgKeyid()))
				newEntTlProgrammst.setProgCreatedon(dateTime);
			else{
				if(UIUtils.isValidKeyId(existEntTlProgrammst.getProgCreatedon()))
					newEntTlProgrammst.setProgCreatedon(existEntTlProgrammst.getProgCreatedon());
				else
					newEntTlProgrammst.setProgCreatedon(dateTime);
				
			}
			//newEntTlProgrammst.setProgCreatedon(dateTime);
			newEntTlProgrammst.setProgModifiedon(dateTime);
			
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgBenifit()  ))
				newEntTlProgrammst.setProgBenifit("{}");
			
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgCode()  ))
				newEntTlProgrammst.setProgCode("{}");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgContactInfo()  ))
				newEntTlProgrammst.setProgContactInfo("{}");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTrarKeyid()  ))
				newEntTlProgrammst.setProgTrarKeyid("{}");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgIsEvaluationNeed()  ))
				newEntTlProgrammst.setProgIsEvaluationNeed("N");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgMaxDuration()  ))
				newEntTlProgrammst.setProgMaxDuration("0");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgMinDuration()  ))
				newEntTlProgrammst.setProgMinDuration("0");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgName()  ))
				newEntTlProgrammst.setProgName("{}");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgPurpose()  ))
				newEntTlProgrammst.setProgPurpose("{}");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgRemarks()  ))
				newEntTlProgrammst.setProgRemarks("{}");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgType()  ))
				newEntTlProgrammst.setProgType("X");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgSpokeKeyid()  ))
				newEntTlProgrammst.setProgSpokeKeyid("{}");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgFunction()  ))
				newEntTlProgrammst.setProgFunction("{}");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgUniquepos()))
				newEntTlProgrammst.setProgUniquepos("{}");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgMonth()  ))
				newEntTlProgrammst.setProgMonth("X");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgMaterialReady() ))
				newEntTlProgrammst.setProgMaterialReady("N");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTgtmKeyid()  ))
				newEntTlProgrammst.setProgTgtmKeyid("{}");
			
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgRepeatedProgram()))
				newEntTlProgrammst.setProgRepeatedProgram("N");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgFrequency()))
				newEntTlProgrammst.setProgFrequency("{}");
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgEffectiveFrom()))
				newEntTlProgrammst.setProgEffectiveFrom(Constants.passNullDate);
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgEffectiveTill()))
				newEntTlProgrammst.setProgEffectiveTill(Constants.futureNullDate);
			
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgElementid()))
				newEntTlProgrammst.setProgElementid("{}");
			
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTempfield1()))
				newEntTlProgrammst.setProgTempfield1("-");
			
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTempfield2()))
				newEntTlProgrammst.setProgTempfield2("-");
			
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTempfield3()))
				newEntTlProgrammst.setProgTempfield3("-");
			CommonMessage.debugMsg("New Record  "+newEntTlProgrammst.getProgEffectiveTill());
			CommonMessage.debugMsg("New Record  "+newEntTlProgrammst.getProgElementid());
			CommonMessage.debugMsg("New Record  "+newEntTlProgrammst.getProgTempfield1());
			CommonMessage.debugMsg("New Record  "+newEntTlProgrammst.getProgTempfield2());
			CommonMessage.debugMsg("New Record  "+newEntTlProgrammst.getProgTempfield3());
			 
			if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgActive()))
				newEntTlProgrammst.setProgActive("Y");
			if(newEntTlProgrammst.getBatchmaster()!= null){
				 fillBatchValues(newEntTlProgrammst.getBatchmaster());
				 fillProgTargetSkils(newEntTlProgrammst.getBatchmaster(),newEntTlProgrammst);
				 fillBatchSchedule(newEntTlProgrammst.getEntTlBatchSchedule(),newEntTlProgrammst);
			}
			if(newEntTlProgrammst.getFacultyTopicLink() != null)
				 fillFacultyLinkValues(newEntTlProgrammst.getFacultyTopicLink());
			if(newEntTlProgrammst.getRoleTopicLink() != null)
				 fillRoleLinkValues(newEntTlProgrammst.getRoleTopicLink(),newEntTlProgrammst);
			CommonMessage.debugMsg(dateTime);
			
			return newEntTlProgrammst;
		
	}

	private EntTlRoleTopicLink fillRoleLinkValues(EntTlRoleTopicLink roleTopicLink,
			EntTlProgrammst newEntTlProgrammst) {
		String dateTime = CommonFunctions.dateTimeNow();
		 
		if(!UIUtils.isValidKeyId(roleTopicLink.getRtlkCreatedby()))
			roleTopicLink.setRtlkCreatedby(newEntTlProgrammst.getProgCreatedby());
			roleTopicLink.setRtlkActive("Y");
			roleTopicLink.setRtlkCreatedon(dateTime);
			roleTopicLink.setRtlkModifiedon(dateTime);
			roleTopicLink.setRtlkSpokId("{}");
			roleTopicLink.setRtlkTargetskrmKeyid("{}");
			roleTopicLink.setRtlkTempfield1("-");
			roleTopicLink.setRtlkTempfield2("-");
			roleTopicLink.setRtlkTempfield3("-");
			roleTopicLink.setRtlkTempfield4("-");
			roleTopicLink.setRtlkTempfield5("-");
			
			return roleTopicLink;
		// TODO Auto-generated method stub
		
	}

	private EntTlBatchSchedule fillBatchSchedule(EntTlBatchSchedule entTlBatchSchedule,
			EntTlProgrammst newEntTlProgrammst) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.dateTimeNow();
		 
		if(!UIUtils.isValidKeyId(entTlBatchSchedule.getBsdlCreatedby()))
			entTlBatchSchedule.setBsdlCreatedby(newEntTlProgrammst.getProgCreatedby());
			entTlBatchSchedule.setBsdlActive("Y");
			entTlBatchSchedule.setBsdlDuration(newEntTlProgrammst.getProgMaxDuration());
			entTlBatchSchedule.setBsdlFromtime(dateTime);
			entTlBatchSchedule.setBsdlScheduleDate(newEntTlProgrammst.getBatchmaster().getBachTilldate());
			entTlBatchSchedule.setBsdlStatus("P");
			entTlBatchSchedule.setBsdlTempfield1("-");
			entTlBatchSchedule.setBsdlTempfield2("-");
			entTlBatchSchedule.setBsdlTempfield3("-");
			entTlBatchSchedule.setBsdlTempfield4("-");
			entTlBatchSchedule.setBsdlTempfield5("-");
			entTlBatchSchedule.setBsdlTilltime(dateTime);   
			 
		return entTlBatchSchedule;
	}

	private EntTlProgTargetSkills fillProgTargetSkils(EntBatchMst batchmaster, EntTlProgrammst newEntTlProgrammst) {
		// TODO Auto-generated method stub
		
		newEntTlProgrammst.getProgTargetSkills().setPrtsActive("Y");
		
		CommonMessage.debugMsg("fillvalues skills :");
		
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		if(newEntTlProgrammst.getProgTargetSkills().getPrtsKeyid() == null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsCreatedon(dateTime);
		else
			newEntTlProgrammst.getProgTargetSkills().setPrtsCreatedon(dateTime);
		
		newEntTlProgrammst.getProgTargetSkills().setPrtsModifiedon(dateTime);
		newEntTlProgrammst.getProgTargetSkills().setPrtsCreatedby(newEntTlProgrammst.getProgCreatedby());
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTargetSkills().getPrtsEffFromDate()  ))
			newEntTlProgrammst.getProgTargetSkills().setPrtsEffFromDate(dateTime);
		if(!UIUtils.isValidKeyId(newEntTlProgrammst.getProgTargetSkills().getPrtsEffTillDate() ) )
			newEntTlProgrammst.getProgTargetSkills().setPrtsEffTillDate(Constants.futureNullDate);
		if(newEntTlProgrammst.getProgTargetSkills().getPrtsProgKeyid()== null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsProgKeyid("{}");
		if(newEntTlProgrammst.getProgTargetSkills().getPrtsSkilDeliverymode() == null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsSkilDeliverymode("{}");
		if(newEntTlProgrammst.getProgTargetSkills().getPrtsSkilEvaluvationtype() == null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsSkilEvaluvationtype("{}");
		if(newEntTlProgrammst.getProgTargetSkills().getPrtsTopiKeyid() == null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsTopiKeyid("{}");
		if(newEntTlProgrammst.getProgTargetSkills().getPrtsImpactSkillrate() == null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsImpactSkillrate("{}");
		if(newEntTlProgrammst.getProgTargetSkills().getPrtsOrderno() == null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsOrderno("0");
		if(newEntTlProgrammst.getProgTargetSkills().getPrtsRatingType() == null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsRatingType("A");

		if(newEntTlProgrammst.getProgTargetSkills().getPrtsSpokeKeyid() == null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsSpokeKeyid("{}");
		//if(newEntTlProgrammst.getProgTargetSkills().getPrtsTempfield1() == null )
		//	newEntTlProgrammst.getProgTargetSkills().setPrtsTempfield1("X");

		if(newEntTlProgrammst.getProgTargetSkills().getPrtsTempfield2() == null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsTempfield2("X");
		if(newEntTlProgrammst.getProgTargetSkills().getPrtsTempfield3() == null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsTempfield3("X");
		if(newEntTlProgrammst.getProgTargetSkills().getPrtsTempfield4() == null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsTempfield4("X");
		if(newEntTlProgrammst.getProgTargetSkills().getPrtsTempfield5() == null )
			newEntTlProgrammst.getProgTargetSkills().setPrtsTempfield5("X");
		
		 
		CommonMessage.debugMsg(dateTime);
		
		return newEntTlProgrammst.getProgTargetSkills();
		// TODO Auto-generated method stub
		
	}

	private EntTlFacultytopiclink fillFacultyLinkValues(EntTlFacultytopiclink facultyTopicLink) {
		facultyTopicLink.setFtlkActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		if( ! UIUtils.isValidKeyId(facultyTopicLink.getFtlkKeyid())  )
		{
			facultyTopicLink.setFtlkCreatedon(dateTime);
			
		}
		facultyTopicLink.setFtlkModifiedon(dateTime);
		if( !UIUtils.isValidKeyId(facultyTopicLink.getFtlkFacultyid()  ) )
			facultyTopicLink.setFtlkFacultyid("{}");
		if( !UIUtils.isValidKeyId(facultyTopicLink.getFtlkTempfield1()  ) )
			facultyTopicLink.setFtlkTempfield1("-");
		if( !UIUtils.isValidKeyId(facultyTopicLink.getFtlkTempfield2()  ) )
			facultyTopicLink.setFtlkTempfield2("-");
		if( !UIUtils.isValidKeyId(facultyTopicLink.getFtlkTempfield3()  ) )
			facultyTopicLink.setFtlkTempfield3("-");
		if( !UIUtils.isValidKeyId(facultyTopicLink.getFtlkTempfield4()  ) )
			facultyTopicLink.setFtlkTempfield4("-");
		if( !UIUtils.isValidKeyId(facultyTopicLink.getFtlkTempfield5()  ) )
			facultyTopicLink.setFtlkTempfield5("-");
		if( !UIUtils.isValidKeyId(facultyTopicLink.getFtlkTempfield6()  ) )
			facultyTopicLink.setFtlkTempfield6("-");
		if( !UIUtils.isValidKeyId(facultyTopicLink.getFtlkTopicid()  ) )
			facultyTopicLink.setFtlkTopicid("{}");
		return facultyTopicLink;
	}

	private EntBatchMst fillBatchValues(EntBatchMst newEntBatchMst) {
		 
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("create service fill value");
		newEntBatchMst.setBachActive("Y");
		CommonMessage.debugMsg("lllllll");
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		CommonMessage.debugMsg("lllllll"+ 	newEntBatchMst.getBachCreatedon());
		CommonMessage.debugMsg("newBatchMst.getBatchKeyid()"+newEntBatchMst.getBachKeyid());
		if( ! UIUtils.isValidKeyId(newEntBatchMst.getBachKeyid())  )
		{
			newEntBatchMst.setBachCreatedon(dateTime);
			
		}
		else{
			//newEntBatchMst.setBachCreatedon(oldEntBatchMst.getBachCreatedon());
			//newEntBatchMst.setBachModifiedon(oldEntBatchMst.getBachModifiedon());
		}  
		newEntBatchMst.setBachModifiedon(dateTime);
		
		/*if( newEntBatchMst.getBachCode() == null )
			newEntBatchMst.setBachCode("{}");
		CommonMessage.debugMsg("fill2gs77777777777777777777777777777777777777" );
		
		if( newEntBatchMst.getBachName() == null )
			newEntBatchMst.setBachName("{}");*/
		
		if( !UIUtils.isValidKeyId(newEntBatchMst.getBachCode() ) )
			newEntBatchMst.setBachCode("{}");
		if( !UIUtils.isValidKeyId(newEntBatchMst.getBachVenuKeyid()) )
			newEntBatchMst.setBachVenuKeyid("{}");
		if( !UIUtils.isValidKeyId(newEntBatchMst.getBachMinsize()) )
			newEntBatchMst.setBachMinsize("0");
		if( !UIUtils.isValidKeyId(newEntBatchMst.getBachMaxsize()) )
			newEntBatchMst.setBachMaxsize(newEntBatchMst.getBachMinsize() );
		if( newEntBatchMst.getBachFactKeyid() == null )
			newEntBatchMst.setBachFactKeyid("{}");
		if( newEntBatchMst.getBachFromdate() == null )
			newEntBatchMst.setBachFromdate(Constants.passNullDate);
		
		if( newEntBatchMst.getBachTilldate() == null )
			newEntBatchMst.setBachTilldate(Constants.futureNullDate);
		/*if( newEntBatchMst.getBachTilldate() != null )
			newEntBatchMst.setBachFromdate(newEntBatchMst.getBachTilldate());
		*/
		if( newEntBatchMst.getBachMinsize() == null )
			newEntBatchMst.setBachMinsize("0");
		
		if( newEntBatchMst.getBachMaxsize() == null )
			newEntBatchMst.setBachMaxsize("0");
		
		if( newEntBatchMst.getBachDuration() == null )
			newEntBatchMst.setBachDuration("1");
		
		if( newEntBatchMst.getBachStatus() == null )
			newEntBatchMst.setBachStatus("P");
		
		
		
		if( newEntBatchMst.getBachRemarks() == null )
			newEntBatchMst.setBachRemarks("-");
		
		if( newEntBatchMst.getBachCompletedate() == null )
			newEntBatchMst.setBachCompletedate(newEntBatchMst.getBachTilldate());
		
		if( newEntBatchMst.getBachTempfield2() == null )
			newEntBatchMst.setBachTempfield2("-");
		
		if( newEntBatchMst.getBachTempfield3() == null )
			newEntBatchMst.setBachTempfield3("-");
		
		if( newEntBatchMst.getBachTempfield4() == null )
			newEntBatchMst.setBachTempfield4("-");
		
		if( newEntBatchMst.getBachTempfield5() == null )
			newEntBatchMst.setBachTempfield5("-");
		
		
		return newEntBatchMst;
	}

	@Override
	public EntTlProgrammst update(EntTlProgrammst newEntTlProgrammst,
			EntTlProgrammst existEntTlProgrammst, ProgrammstBean programmstBean)
			throws ValidationExceptions,Exception, BusinessApplicationExceptions {
		// TODO Auto-generated method stub
			String validationsFor = "updateTrngCal";
			CommonMessage.debugMsg("Inside the ServiceImpl update");
			 validations.validate(newEntTlProgrammst,"Entprogram",validationsFor);
			 if(newEntTlProgrammst.getBatchmaster() != null )
				 validations.validate(newEntTlProgrammst.getBatchmaster(),"Entprogram",validationsFor);
			fillValues(newEntTlProgrammst,existEntTlProgrammst,programmstBean);
			CommonMessage.debugMsg("After Filling tool Values");
			//for inserting in clisCalendar procedure
			 
		return  trainingCalendarDao.update(newEntTlProgrammst,programmstBean);

	}

	@Override
	public EntTlProgrammst deleteRec(EntTlProgrammst newEntTlProgrammst)
			throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("inside delete serviceimpl");
		return trainingCalendarDao.deleteRec(newEntTlProgrammst);
	}

	@Override
	public String getVenuAlloated(String venuId, String fromTime, String tillTime) throws Exception {
		return trainingCalendarDao.getVenuAlloated(venuId, fromTime, tillTime);
	}
	@Override
	public EntTlProgrammst getprogdata(String topicID, String progMonth, String uniquePos,String porkeyid,String flid) throws Exception {
		// TODO Auto-generated method stub
		return trainingCalendarDao.getprogdata(topicID, progMonth,uniquePos , porkeyid,flid);
	}

	@Override
	public List<String[]> getFaculty(String progKeyid, String month, String uniqPos) throws Exception {
		// TODO Auto-generated method stub
		return trainingCalendarDao.getFaculty(progKeyid, month, uniqPos);
	}

	@Override
	public List<String[]> getbatch(String progKeyid, String month, String uniqPos) throws Exception {
		// TODO Auto-generated method stub
		return trainingCalendarDao.getbatch(progKeyid, month, uniqPos);
	}

	@Override
	public String deleteDetail(String keyId, String gridId, String topicId) throws Exception,BusinessApplicationExceptions{
		// TODO Auto-generated method stub
		return trainingCalendarDao.deleteDetail(keyId,gridId,topicId);
	}

	@Override
	public List<String[]> getUpbasedEmployee( CommonFilter commonFilter,String progKeyid,String bachId,String flid,String progType,String roleId,String newFlid) throws Exception {
		
		return trainingCalendarDao.getUpbasedEmployee(commonFilter,progKeyid,bachId,flid,progType,roleId,newFlid);
	}

	@Override
	public Workbook getTrngCalReportExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		return trainingCalendarDao.getTrngCalReportExportExcel(commonFilter, colmodel, format) ;
	}

	@Override
	public List<String[]> getTrainingCalendarRPT(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return trainingCalendarDao.getTrainingCalendarRPT(commonFilter);
	}

	@Override
	public List<String[]>  selectProgId(String progId) throws Exception {
		// TODO Auto-generated method stub
		return trainingCalendarDao.getselectProgId(progId);
	}

	@Override
	public Workbook getTrngCalExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		return trainingCalendarDao.getTrngCalExportExcel(commonFilter, colmodel, format) ;
	}

	@Override
	public Boolean getFrequecyData(String topicID) throws Exception {
		// TODO Auto-generated method stub
		return trainingCalendarDao.getFrequecyData(topicID);
	}

	@Override
	public  String getPermStrength(String venuId) throws Exception {
		// TODO Auto-generated method stub
		return trainingCalendarDao.getPermStrength(venuId);
	}

	@Override
	public List<String[]> getUniqPosData(String progKeyid) throws Exception {
		// TODO Auto-generated method stub
		return trainingCalendarDao.getUniqPosData(progKeyid);
	}

	@Override
	public String getUpbasedEmployeeCount(CommonFilter commonFilter,
			String progKeyid, String bachId, String flid, String progType,String roleId,String  newFlid)
			throws Exception {
		
		return trainingCalendarDao.getUpbasedEmployeeCount(commonFilter, progKeyid, bachId, flid, progType,roleId,newFlid);
	}
	
	public List<ComboBox> getFacultyComboList(CommonFilter commonFilter,
	        ComboFilter comboFilter) throws Exception {

	    comboFilter.setIdField("EMPID");
	    comboFilter.setNameField("EMPROLE");

	    String sectId            = commonFilter.getSect();
	    String loginFlid         = commonFilter.getFlid();
	    String locnid            = commonFilter.getAbnAllch();
	    String facultyFilterType = commonFilter.getIsFacultyLevel(); // "dmt" or "other"

	    StringBuffer sb = new StringBuffer();

	    if (UIUtils.isValidKeyId(sectId)) {


	    	
	    	if (UIUtils.isValidKeyId(sectId)) {

	    	    if ("other".equals(facultyFilterType)) {
	    	        // Other: has a known sect AND it's NOT this DMT
	    	        // (faculty mapped at LCN/higher level with no sect are NOT in other)
	    	        sb.append(" AND faculty_sect_keyid IS NOT NULL");
	    	        sb.append(" AND faculty_sect_keyid <> '" + sectId + "'");
	    	        sb.append(" AND emp_sect_keyid <> '" + sectId + "'");
	    	        if (UIUtils.isValidKeyId(locnid)) {
	    	            sb.append(" AND locn_keyid = '" + locnid + "'");
	    	        }
	    	    } else {
	    	        // Main: mapped at this DMT OR employee of this DMT
	    	        //       OR mapped at LCN/higher level (no sect) — available to all DMTs
	    	        sb.append(" AND (");
	    	        sb.append("   faculty_sect_keyid = '" + sectId + "'");        // mapped at this DMT
	    	        sb.append("   OR emp_sect_keyid = '" + sectId + "'");         // employee of this DMT
	    	        sb.append("   OR (faculty_sect_keyid IS NULL");               // LCN-level mapping
	    	        if (UIUtils.isValidKeyId(locnid)) {
	    	            sb.append("       AND locn_keyid = '" + locnid + "'");    // same location
	    	        }
	    	        sb.append("   )");
	    	        sb.append(" )");
	    	    }

	    } 
	    	

	    } else if (UIUtils.isValidKeyId(loginFlid)) {

	        String resolvedSect = commonFilterDao.resolveSectId(loginFlid);
	        CommonMessage.debugMsg("loginFlid: " + loginFlid + " => resolvedSect: " + resolvedSect);

	        if (UIUtils.isValidKeyId(resolvedSect)) {
	            if ("other".equals(facultyFilterType)) {
	                sb.append(" AND faculty_sect_keyid IS NOT NULL");
	                sb.append(" AND faculty_sect_keyid <> '" + resolvedSect + "'");
	                sb.append(" AND emp_sect_keyid <> '" + resolvedSect + "'");
	                if (UIUtils.isValidKeyId(locnid)) {
	                    sb.append(" AND locn_keyid = '" + locnid + "'");
	                }
	            } else {
	                sb.append(" AND (");
	                sb.append("   faculty_sect_keyid = '" + resolvedSect + "'");
	                sb.append("   OR emp_sect_keyid = '" + resolvedSect + "'");
	                sb.append("   OR (faculty_sect_keyid IS NULL");
	                if (UIUtils.isValidKeyId(locnid)) {
	                    sb.append("       AND locn_keyid = '" + locnid + "'");
	                }
	                sb.append("   )");
	                sb.append(" )");
	            }
	        } else {
	            if (UIUtils.isValidKeyId(locnid)) {
	                sb.append(" AND locn_keyid = '" + locnid + "'");
	            }
	        }

	    }
	    

	    comboFilter.setCondSql(sb.toString());
	    comboFilter.setTableName("ENT_VW_FACULTYMST_NEW");
	    return commonFilterDao.fillComboValues(comboFilter);
	}
	
	
	
//	public List<ComboBox> getFacultyComboList(CommonFilter commonFilter,
//	        ComboFilter comboFilter) throws Exception {
//
//	    comboFilter.setIdField("EMPID");
//	    comboFilter.setNameField("EMPROLE");
//
//	    String sectId            = commonFilter.getSect();   // set when JSP passes sectId directly
//	    String loginFlid         = commonFilter.getFlid();   // set when flId or loginFlid is used
//	    String locnid            = commonFilter.getAbnAllch();
//	    String facultyFilterType = commonFilter.getIsFacultyLevel(); // "dmt" or "other"
//
//	    StringBuffer sb = new StringBuffer();
//
//	    if (UIUtils.isValidKeyId(sectId)) {
//	        // Best case: sectId came directly from JSP (e.g. SEC0000016) — use it straight away
//	        CommonMessage.debugMsg("Using sectId directly: " + sectId);
//	        if ("other".equals(facultyFilterType)) {
//	            sb.append(" AND sect_keyid <> '" + sectId + "'");
//	            if (UIUtils.isValidKeyId(locnid)) {
//	                sb.append(" AND locn_keyid = '" + locnid + "'");
//	            }
//	        } else {
//	            sb.append(" AND sect_keyid = '" + sectId + "'");
//	        }
//
//	    } else if (UIUtils.isValidKeyId(loginFlid)) {
//	        // Page load: resolve flId/loginFlid to SEC via resolveSectId
//	        String resolvedSect = commonFilterDao.resolveSectId(loginFlid);
//	        CommonMessage.debugMsg("loginFlid: " + loginFlid + " => resolvedSect: " + resolvedSect);
//
//	        if (UIUtils.isValidKeyId(resolvedSect)) {
//	            if ("other".equals(facultyFilterType)) {
//	                sb.append(" AND sect_keyid <> '" + resolvedSect + "'");
//	                if (UIUtils.isValidKeyId(locnid)) {
//	                    sb.append(" AND locn_keyid = '" + locnid + "'");
//	                }
//	            } else {
//	                sb.append(" AND sect_keyid = '" + resolvedSect + "'");
//	            }
//	        } else {
//	            // LCN/CMP level — fallback to location
//	            if (UIUtils.isValidKeyId(locnid)) {
//	                sb.append(" AND locn_keyid = '" + locnid + "'");
//	            }
//	        }
//
//	    } else if (UIUtils.isValidKeyId(locnid)) {
//	        // No flid at all — location fallback
//	        sb.append(" AND locn_keyid = '" + locnid + "'");
//	    }
//
//	    comboFilter.setCondSql(sb.toString());
//	    comboFilter.setTableName("ENT_VW_FACULTYMST_NEW");
//	    return commonFilterDao.fillComboValues(comboFilter);
//	}
//	public List<ComboBox> getFacultyComboList(CommonFilter commonFilter,
//			ComboFilter comboFilter) throws Exception {
//		// TODO Auto-generated method stub
//		
//		comboFilter.setIdField("EMPID");
//	//	comboFilter.setCodeField("EMPM_CODE");
//		comboFilter.setNameField("EMPROLE");
//		
//		String flid = commonFilter.getFlid();
//		String sectid=commonFilter.getSect();
//		String locnid=commonFilter.getAbnAllch();
//		//CommonMessage.debugMsg("locnid in serv"+locnid);
//		
//		if (UIUtils.isValidKeyId(locnid)) {
//			StringBuffer sb = new StringBuffer();
//		
//		//   sb.append("	SELECT DISTINCT EMPM_KEYID id ,EMPM_NAME||'-'||EMPM_CODE text  from GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST where 1 = 1"); 
//			//sb.append(" AND FRT_ROLE_KEYID=ROLE_KEYID AND FRT_EMPM_KEYID=EMPM_KEYID AND EMPM_ACTIVE='Y' AND FRT_ROLE_KEYID IN('AROL0005','AROL0003') AND FRT_FNLN_KEYID In (Select Fnln_Keyid From Gen_Tl_Functionallocn Where  " );
//			sb.append(" AND FLID IN (SELECT FNLN_KEYID FROM GEN_VW_FNLN WHERE ");
//			sb.append(" FNLN_ORIGINALID IN ('"+locnid+"'))");
//			//sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID='" + flid + "') ) " );
//			//CommonMessage.debugMsg("inside of ser impl1234"+sb.toString());
//			comboFilter.setCondSql(sb.toString());
//		 
//		}
//		else{
//			StringBuffer sb = new StringBuffer();
//			//sb.append(" AND FRT_ROLE_KEYID=ROLE_KEYID AND FRT_EMPM_KEYID=EMPM_KEYID AND EMPM_ACTIVE='Y'  AND FRT_ROLE_KEYID IN('AROL0005','AROL0003')  " );
//		//	sb.append(" FNLN_ORIGINALID IN ('"+sectid+"'))");
//			//sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID='" + flid + "') ) " );
//			comboFilter.setCondSql(sb.toString());
//		
//		}
//		//comboFilter.setTableName("GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST");
//		comboFilter.setTableName("ENT_VW_FACULTYMST");
//		
//		return commonFilterDao.fillComboValues(comboFilter);
//	}

}
