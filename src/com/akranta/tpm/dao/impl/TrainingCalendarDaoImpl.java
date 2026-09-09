package com.akranta.tpm.dao.impl;

 import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.bind.ValidationException;

import net.sf.json.JSONObject;

import oracle.net.aso.d;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.ProgrammstBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.TrainingCalendarDao;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.EntBatchMstSql;
import com.akranta.tpm.dao.sql.EntTlBatchScheduleSql;
import com.akranta.tpm.dao.sql.EntTlFacultytopiclinkSql;
import com.akranta.tpm.dao.sql.EntTlProgCalendarSql;
import com.akranta.tpm.dao.sql.EntTlProgTargetRolesSql;
import com.akranta.tpm.dao.sql.EntTlProgTargetSkillsSql;
import com.akranta.tpm.dao.sql.EntTlProgrammstSql;
import com.akranta.tpm.dao.sql.EntTlRoleTopicLinkSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntBatchMst;
import com.akranta.tpm.model.EntTlBatchSchedule;
import com.akranta.tpm.model.EntTlProgCalendar;
import com.akranta.tpm.model.EntTlProgTargetRoles;
import com.akranta.tpm.model.EntTlProgrammst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
public class TrainingCalendarDaoImpl implements TrainingCalendarDao{
	
	private DBActionTemplate dbActionTemplate;
	private CommonFilterDao commonFilterdao;
	EntTlProgrammstSql entTlProgrammstSql ;
	EntTlProgTargetRolesSql entTlProgTargetRolesSql ;
	EntBatchMstSql entBatchMstSql ;
	EntTlProgCalendarSql entTlProgCalendarSql;
	EntTlFacultytopiclinkSql entTlFacultytopiclinkSql ;
	EntTlProgTargetSkillsSql entTlProgTargetSkillsSql;
	EntTlRoleTopicLinkSql entTlRoleTopicLinkSql;
	public TrainingCalendarDaoImpl(DBActionTemplate dbActionTemplate) {
		///commonFilterdao = new CommonFilterDaoImpl(dbActionTemplate);
		this.dbActionTemplate = dbActionTemplate;
		entTlProgrammstSql = new EntTlProgrammstSql();
		entTlProgTargetRolesSql = new EntTlProgTargetRolesSql();
		entBatchMstSql = new EntBatchMstSql();
		entTlProgCalendarSql = new EntTlProgCalendarSql();
		entTlFacultytopiclinkSql = new EntTlFacultytopiclinkSql();
		entTlProgTargetSkillsSql = new EntTlProgTargetSkillsSql();
		entTlRoleTopicLinkSql    = new EntTlRoleTopicLinkSql();
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}	
	@Override
	public List<String[]> getTrainingCalendarList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		String functionName = "RPT_PC_CARD.RPT_FN_ETTRNCALENDAR";//commonFilter.getFunctionName();
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			
			String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			condParms+=";MODE="+commonFilter.getType()+";";
			CommonMessage.debugMsg( " FromModeDaoImpl  "+commonFilter.getType());
			paramValues.add(condParms);
			paramValues.add(commonParams);
						
			CommonMessage.debugMsg("ParamValues:"+paramValues);	
			List<String[]> dataList =   dbActionTemplate.processFunctionCalls(functionName, paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg(dataList);
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public EntTlProgrammst create(EntTlProgrammst newEntTlProgrammst, ProgrammstBean programmstBean)
			throws Exception, BusinessApplicationExceptions, ValidationException {
		// TODO Auto-generated method stub
		//CommonMessage.debugMsg("IsUniquePositiion:" +programmstBean.)
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			CommonMessage.debugMsg("insideDAOIMPL");
			if(newEntTlProgrammst.getBatchmaster()!= null)
				CommonMessage.debugMsg("bacthsessiondta "+newEntTlProgrammst.getBatchmaster().getBachMaxsize());
			if(newEntTlProgrammst.getFacultyTopicLink() != null)
				CommonMessage.debugMsg("facultylinkdta "+newEntTlProgrammst.getFacultyTopicLink().getFtlkFacultyid());
			//CommonMessage.debugMsg("DAO  getIsUniquePositionLink():" +newEntTlProgrammst.getRoleTopicLink().getIsUniquePositionLink());
			String roleTopicLink=newEntTlProgrammst.getRoleTopicLink().getIsUniquePositionLink();
			CommonMessage.debugMsg("newEntTlProgrammst.getProgType() : " + newEntTlProgrammst.getProgType());
		/*	if(entTlProgTargetSkills.getPrtsTopiKeyid().equals(skillKey)){
			CommonMessage.debugMsg("Topic  available " );
			
			sqls.add(EntTlProgTargetSkillsSql.getUpdateSql(entTlProgrammstSql.getProgDbFields(), newEntTlProgrammst.getSaveArray())); // add insert sql for master table
		}
		else{*/
		 EntTlProgTargetRoles newEntTlProgTargetRoles = new EntTlProgTargetRoles();
		 
		 //newEntTlProgrammst.setProgKeyid(dbActionTemplate.getSequenceNumber(EntTlProgrammstSql.TBL_ENT_TL_PROGRAMMST, 10, "PRG", "", "")); // set the sequnce number
		 //added on 10-May-2014 by babu
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append( " Select PROG_KEYID from ENT_TL_PROGRAMMST  where PROG_KEYID = '"+newEntTlProgrammst.getProgKeyid()+"' ");
		sqlBuf.append( " AND UPPER(PROG_MONTH) = '" + newEntTlProgrammst.getProgMonth()+ "' ");
		//sqlBuf.append( " AND PROG_UNIQUEPOS  = '" + newEntTlProgrammst.getProgUniquepos() + "' ");
		String isExists = dbActionTemplate.getSingleValue(sqlBuf.toString());
		CommonMessage.debugMsg("isExists"+isExists);
		//
		if (!UIUtils.isValidKeyId(isExists)) {
			//newEntTlProgrammst.setProgCode(newEntTlProgrammst.getProgKeyid());
			newEntTlProgrammst.setProgKeyid(dbActionTemplate.getSequenceNumber(EntTlProgrammstSql.TBL_ENT_TL_PROGRAMMST, 10, "PRG", "", "")); // set the sequnce number
			sqls.add(EntTlProgrammstSql.getInsertSql(entTlProgrammstSql.getProgDbFields(), newEntTlProgrammst.getSaveArray())); // add insert sql for master table
		}
		else {
			newEntTlProgrammst.setProgKeyid(isExists);
			//newEntTlProgrammst.setProgCode(newEntTlProgrammst.getProgKeyid());
			sqls.add(EntTlProgrammstSql.getUpdateSql(entTlProgrammstSql.getProgDbFields(), newEntTlProgrammst.getSaveArray())); // add insert sql for master table
		}
		//if(!roleTopicLink.equals("N")){
		if(!newEntTlProgrammst.getProgType().equals("G")){
			newEntTlProgTargetRoles.setPrtrKeyid(dbActionTemplate.getSequenceNumber(EntTlProgTargetRolesSql.TBL_ENT_TL_PROG_TARGET_ROLES, 10, "PRR", "", "")); // set the sequnce number
			newEntTlProgTargetRoles.setPrtrProgKeyid(newEntTlProgrammst.getProgKeyid());
			newEntTlProgTargetRoles.setPrtrTrarKeyid(newEntTlProgrammst.getRoleTopicLink().getRtlkRtalKeyid());
			
			newEntTlProgTargetRoles = fillValuesRole(newEntTlProgTargetRoles,newEntTlProgrammst);
			sqls.add(EntTlProgTargetRolesSql.getInsertSql(entTlProgTargetRolesSql.getPrtrDbFields() , newEntTlProgTargetRoles.getSaveArray()));
		}
			 EntTlProgCalendar newEntTlProgCalendar = new EntTlProgCalendar();
			 if(UIUtils.isValidKeyId(newEntTlProgrammst.getEcalkeyid()))
				 newEntTlProgCalendar.setEcalKeyid(newEntTlProgrammst.getEcalkeyid());
			 else {
				 String elementId = newEntTlProgrammst.getProgElementid();
			 	String location = null;
			 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,TableNames.TBL_ENT_TL_PROG_CALENDAR);

			 	newEntTlProgCalendar.setEcalKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi, 12, "EP", "", ""));
				 //newEntTlProgCalendar.setEcalKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_ENT_TL_PROG_CALENDAR, 12, "EPC", "", ""));
			 }
			 newEntTlProgrammst.setEcalkeyid(newEntTlProgCalendar.getEcalKeyid());
			 CommonMessage.debugMsg("testt......."+newEntTlProgrammst.getBatchmaster());
			 //CommonMessage.debugMsg("BachKeyid=== "+newEntTlProgrammst.getBatchmaster().getBachKeyid());
			 if( newEntTlProgrammst.getBatchmaster() != null && 
					 !UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getBachKeyid())
					 && !Constants.futureNullDate.equals(newEntTlProgrammst.getBatchmaster().getBachTilldate())
					 && UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getBachTilldate()) 
					 ){
			CommonMessage.debugMsg("Inside Batch Execute");
			 newEntTlProgrammst.getBatchmaster().setBachKeyid(dbActionTemplate.getSequenceNumber(entBatchMstSql.TBL_ENT_TL_BATCHMST,8,"BAT","","" ));
			 newEntTlProgrammst.getBatchmaster().setBachProgKeyid(newEntTlProgrammst.getProgKeyid());
			 newEntTlProgrammst.getBatchmaster().setBachCreatedby(newEntTlProgrammst.getProgCreatedby());
			 String bachName = getBachName(newEntTlProgrammst.getBatchmaster());
			 newEntTlProgrammst.getBatchmaster().setBachName(bachName);
			 CommonMessage.debugMsg("after bacth sesson name");
			 EntTlBatchScheduleSql entTlBatchScheduleSql = new EntTlBatchScheduleSql(); // contains dbtable,field names, Field types and related sqls  of master table
			 EntTlBatchSchedule entTlBatchSchedule =newEntTlProgrammst.getEntTlBatchSchedule();  
			 entTlBatchSchedule.setBsdlProgKeyid(newEntTlProgrammst.getProgKeyid() );
			 entTlBatchSchedule.setBsdlBachKeyid(newEntTlProgrammst.getBatchmaster().getBachKeyid());
			 entTlBatchSchedule.setBsdlCreatedon(newEntTlProgrammst.getProgCreatedon() );
			 entTlBatchSchedule.setBsdlModifiedon(newEntTlProgrammst.getProgModifiedon());
			 entTlBatchSchedule.setBsdlEcalKeyid(newEntTlProgCalendar.getEcalKeyid());
			 CommonMessage.debugMsg("before schedule");
			 entTlBatchSchedule.setBsdlKeyid(dbActionTemplate.getSequenceNumber(EntTlBatchScheduleSql.TBL_ENT_TL_BATCH_SCHEDULE, 10, "BSDL",null,null));
			 sqls.add(EntTlBatchScheduleSql.getInsertSql(entTlBatchScheduleSql.getBsdlDbFields(), entTlBatchSchedule.getSaveArray())); // add insert sql for master table
			 sqls.add(EntBatchMstSql.getInsertSql(entBatchMstSql.getBachDbFields(),newEntTlProgrammst.getBatchmaster().getSaveArray()));
			 CommonMessage.debugMsg("after schedule");
			 CommonMessage.debugMsg("entTlBatchSchedule.getBsdlKeyid(  "+entTlBatchSchedule.getBsdlKeyid());
			 newEntTlProgCalendar.setEcalPlanTilldate(newEntTlProgrammst.getBatchmaster().getBachTilldate());
			
			 /**ProgTopicSkillRating**/
			 CommonMessage.debugMsg("Inside ProgTargetRating Execute");
			 newEntTlProgrammst.getProgTargetSkills().setPrtsKeyid(dbActionTemplate.getSequenceNumber(entTlProgTargetSkillsSql.TBL_ENT_TL_PROG_TARGET_SKILLS, 10, "PRT", "", "")); // set the sequnce number
			 CommonMessage.debugMsg("targetKeyid  "+newEntTlProgrammst.getProgTargetSkills().getPrtsKeyid());
			 sqls.add(EntTlProgTargetSkillsSql.getInsertSql(entTlProgTargetSkillsSql.getPrtsDbFields(),newEntTlProgrammst.getProgTargetSkills().getSaveArray()));
		 }
		 else{
			 newEntTlProgCalendar.setEcalPlanTilldate("01-"+newEntTlProgrammst.getProgMonth());
		 }
		 if(newEntTlProgrammst.getFacultyTopicLink() != null){
			 CommonMessage.debugMsg("Inside Faculty Execute");
			 newEntTlProgrammst.getFacultyTopicLink().setFtlkKeyid(dbActionTemplate.getSequenceNumber(entTlFacultytopiclinkSql.TBL_ENT_TL_FACULTYTOPICLINK , 10, "FTL", "",""));
			 newEntTlProgrammst.getFacultyTopicLink().setFtlkTopicid(newEntTlProgrammst.getProgKeyid());
			 newEntTlProgrammst.getFacultyTopicLink().setFtlkCreatedby(newEntTlProgrammst.getProgCreatedby());
			 sqls.add(EntTlFacultytopiclinkSql.getInsertSql(entTlFacultytopiclinkSql.getFtlkDbFields(), newEntTlProgrammst.getFacultyTopicLink().getSaveArray()));
		 }
		// if(!roleTopicLink.equals("N")){newEntTlProgrammst.getProgType()
		 if(!newEntTlProgrammst.getProgType().equals("G")){
			 if(newEntTlProgrammst.getRoleTopicLink() != null){
				 CommonMessage.debugMsg("Inside Role Execute");
				 //added on 06-dec-2014 d.babu
				 String allUnique = programmstBean.getAllUniquePosition();
				 CommonMessage.debugMsg("programmstBean.getAllUniquePosition()"+allUnique);
				 if (UIUtils.isValidKeyId(allUnique) && allUnique.equals("Y")) {
					 
					 CommonMessage.debugMsg("allUniqueDao==="+allUnique);
					 StringBuffer sf = new StringBuffer();
					 sf.append(" SELECT DISTINCT ROLE_KEYID from  Ent_Vw_Rolemst    ");
					 sf.append(" where INSTR( PARENTFLIDS||FLID ,'" + newEntTlProgrammst.getProgTrarKeyid() + "')>0     ");
					 sf.append(" AND ROLE_KEYID in( select  TMTM_ROLE_KEYID from  ENT_TL_UNIQPOSTOPIC_LINKMST  ");
					 sf.append("where TMTM_TOPI_KEYID ='" + newEntTlProgrammst.getProgCode() + "' ) " );
					 
					 List<String[]> roleIds = dbActionTemplate.getDataList(sf.toString());
					 
					 CommonMessage.debugMsg("roleIds.size()==="+roleIds.size());
					 for ( int i=0;i<roleIds.size();i++) {
						 newEntTlProgrammst.getRoleTopicLink().setRtlkRtalKeyid(roleIds.get(i)[0]);
						 CommonMessage.debugMsg("roleIds.get(i)[0]==="+roleIds.get(i)[0]);
						 newEntTlProgrammst.getRoleTopicLink().setRtlkKeyid(dbActionTemplate.getSequenceNumber(entTlRoleTopicLinkSql.TBL_ENT_TL_ROLE_TOPIC_LINK , 10, "RTA", "",""));
						 newEntTlProgrammst.getRoleTopicLink().setRtlkTopiKeyid(newEntTlProgrammst.getProgKeyid());
						 sqls.add(EntTlRoleTopicLinkSql.getInsertSql(entTlRoleTopicLinkSql.getRtlkDbFields() , newEntTlProgrammst.getRoleTopicLink().getSaveArray()));
					 }
					 
				 } else {
					 newEntTlProgrammst.getRoleTopicLink().setRtlkKeyid(dbActionTemplate.getSequenceNumber(entTlRoleTopicLinkSql.TBL_ENT_TL_ROLE_TOPIC_LINK , 10, "RTA", "",""));
					 newEntTlProgrammst.getRoleTopicLink().setRtlkTopiKeyid(newEntTlProgrammst.getProgKeyid());
					 sqls.add(EntTlRoleTopicLinkSql.getInsertSql(entTlRoleTopicLinkSql.getRtlkDbFields() , newEntTlProgrammst.getRoleTopicLink().getSaveArray()));
				 }
			 }
		 }
		 
		 
		 
		 newEntTlProgCalendar.setEcalPlanMonth(newEntTlProgrammst.getProgMonth());
		if(newEntTlProgrammst.getProgRepeatedProgram().equals("Y")){
			if("W".equals(newEntTlProgrammst.getProgFrequency())){}
			else if("M".equals(newEntTlProgrammst.getProgFrequency())){}
			else if("Q".equals(newEntTlProgrammst.getProgFrequency())){}
			else if("F".equals(newEntTlProgrammst.getProgFrequency())){}
			else if("Y".equals(newEntTlProgrammst.getProgFrequency())){}
				
			fillCalendarValues(newEntTlProgCalendar,newEntTlProgrammst);
			sqls.add(EntTlProgCalendarSql.getInsertSql(entTlProgCalendarSql.getEcalDbFields(), newEntTlProgCalendar.getSaveArray()));	
		}else{
			fillCalendarValues(newEntTlProgCalendar,newEntTlProgrammst);
			sqls.add(EntTlProgCalendarSql.getInsertSql(entTlProgCalendarSql.getEcalDbFields(), newEntTlProgCalendar.getSaveArray()));
		}
		 
		//}
		
		CommonMessage.debugMsg("inside Inserted skills");
	/*}
	else{
		throw new BusinessApplicationExceptions("Skill Already Exist For Program");
	}*/
	 dbActionTemplate.executeStatements(sqls); // execute the block of sqls
	
	return newEntTlProgrammst;
	
	}
		
	private EntTlProgTargetRoles fillValuesRole(EntTlProgTargetRoles newEntTlProgTargetRoles, EntTlProgrammst newEntTlProgrammst ) {
		// TODO Auto-generated method stub
		newEntTlProgTargetRoles.setPrtrActive("Y");
		
		CommonMessage.debugMsg("fillValuesRole  :");
		
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		if(newEntTlProgTargetRoles.getPrtrKeyid() == null )
			newEntTlProgTargetRoles.setPrtrCreatedon(dateTime);
		else
			newEntTlProgTargetRoles.setPrtrCreatedon(newEntTlProgrammst.getProgCreatedon());
		newEntTlProgTargetRoles.setPrtrCreatedby(newEntTlProgrammst.getProgCreatedby() );
		newEntTlProgTargetRoles.setPrtrModifiedon(dateTime);
		
		if(newEntTlProgTargetRoles.getPrtrEffFromDate() == null )
			newEntTlProgTargetRoles.setPrtrEffFromDate(Constants.passNullDate);
		if(newEntTlProgTargetRoles.getPrtrEffTillDate() == null )
			newEntTlProgTargetRoles.setPrtrEffTillDate(Constants.futureNullDate);
		if(newEntTlProgTargetRoles.getPrtrProgKeyid()== null )
			newEntTlProgTargetRoles.setPrtrProgKeyid("{}");
		if(newEntTlProgTargetRoles.getPrtrTrarKeyid()== null )
			newEntTlProgTargetRoles.setPrtrTrarKeyid("{}");
		
		if(newEntTlProgTargetRoles.getPrtrTempfield1() == null )
			newEntTlProgTargetRoles.setPrtrTempfield1("X");
		if(newEntTlProgTargetRoles.getPrtrTempfield2() == null )
			newEntTlProgTargetRoles.setPrtrTempfield2("X");
		if(newEntTlProgTargetRoles.getPrtrTempfield3() == null )
			newEntTlProgTargetRoles.setPrtrTempfield3("X");
		if(newEntTlProgTargetRoles.getPrtrTempfield4() == null )
			newEntTlProgTargetRoles.setPrtrTempfield4("X");
		if(newEntTlProgTargetRoles.getPrtrTempfield5() == null )
			newEntTlProgTargetRoles.setPrtrTempfield5("X");
		return newEntTlProgTargetRoles;
	}
	private EntTlProgCalendar fillCalendarValues(EntTlProgCalendar newEntTlProgCalendar,
			EntTlProgrammst newEntTlProgrammst) {
		String dateTime = CommonFunctions.dateTimeNow();
		newEntTlProgCalendar.setEcalActive("Y");
		newEntTlProgCalendar.setEcalActualAudiencecount("0");
		newEntTlProgCalendar.setEcalActualFromdate("01-"+newEntTlProgrammst.getProgMonth());
		newEntTlProgCalendar.setEcalActualTilldate(Constants.futureNullDate);
		newEntTlProgCalendar.setEcalApprovedby("{}");
		newEntTlProgCalendar.setEcalApproveddate(Constants.futureNullDate);
		newEntTlProgCalendar.setEcalApprovedflag("-");
		newEntTlProgCalendar.setEcalApprovedremarks("{}");
		newEntTlProgCalendar.setEcalBudget("0");
		newEntTlProgCalendar.setEcalCreatedby(newEntTlProgrammst.getProgCreatedby());
		newEntTlProgCalendar.setEcalCreatedon(dateTime);
		newEntTlProgCalendar.setEcalModifiedon(dateTime);
		newEntTlProgCalendar.setEcalMonthwise("-");
		newEntTlProgCalendar.setEcalPlanAudiencecount("0");
		newEntTlProgCalendar.setEcalPlanFromdate(Constants.futureNullDate);
		newEntTlProgCalendar.setEcalPlanTilldate(Constants.futureNullDate);
		newEntTlProgCalendar.setEcalPlanWeek("0");
		newEntTlProgCalendar.setEcalProgId(newEntTlProgrammst.getProgKeyid());
		newEntTlProgCalendar.setEcalRequestby("{}");
		newEntTlProgCalendar.setEcalRequestdate(Constants.futureNullDate);
		newEntTlProgCalendar.setEcalRequestremarks("{}");
		newEntTlProgCalendar.setEcalStatus("C");
		newEntTlProgCalendar.setEcalTempfield2("-");
		newEntTlProgCalendar.setEcalTempfield3("-");
		newEntTlProgCalendar.setEcalTempfield4("-");
		newEntTlProgCalendar.setEcalTempfield5("-");
				return newEntTlProgCalendar;
		// TODO Auto-generated method stub
		
	}
	@Override
	public EntTlProgrammst deleteRec(EntTlProgrammst newEntTlProgrammst)
			throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("inside delete Daoimpl");
		CommonMessage.debugMsg("newEntTlProgrammst.getProgType() : " + newEntTlProgrammst.getProgType());
		try{
			//ENT_TL_PROG_TARGET_ROLES
			 //ENT_TL_PROG_TARGET_SKILLS
			String getBcomStatus = "SELECT BCOM_STATUS from  ENT_TL_BATCHCOMPLETION  where BCOM_PROG_KEYID = '"+newEntTlProgrammst.getProgKeyid()+"'" ;
			String bcomStatus = dbActionTemplate.getSingleValue(getBcomStatus);
			CommonMessage.debugMsg(getBcomStatus +"  bcomStatus   "+bcomStatus);
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			//if("C".equals(bcomStatus)){
			
                sqls.add(" delete from ENT_TL_BATCH_EMPLOYEE_LINK where BSTD_BACH_KEYID in ("+
					" select BACH_KEYID from ENT_TL_BATCHMST where BACH_PROG_KEYID = '"+newEntTlProgrammst.getProgKeyid()+"')");
			    sqls.add("delete from ENT_TL_ROLE_TOPIC_LINK where RTLK_TOPI_KEYID= '"+newEntTlProgrammst.getProgKeyid()+"'");
			    sqls.add("delete from "+TableNames.TBL_ENT_TL_BATCH_SCHEDULE+" where BSDL_PROG_KEYID = '"+newEntTlProgrammst.getProgKeyid()+"'");
				sqls.add("delete from "+TableNames.TBL_ENT_TL_PROG_TARGET_SKILLS  +" where PRTS_PROG_KEYID = '"+newEntTlProgrammst.getProgKeyid()+"'");
				sqls.add("delete from "+TableNames.TBL_ENT_TL_BATCHMST+" where BACH_PROG_KEYID = '"+newEntTlProgrammst.getProgKeyid()+"'");
				sqls.add("delete from "+TableNames.TBL_ENT_TL_PROG_CALENDAR +" where ECAL_PROG_ID = '"+newEntTlProgrammst.getProgKeyid()+"'");
				sqls.add("delete from "+TableNames.TBL_ENT_TL_PROG_TARGET_ROLES  +" where PRTR_PROG_KEYID = '"+newEntTlProgrammst.getProgKeyid()+"'");
				sqls.add("delete from "+TableNames.TBL_ENT_TL_FACULTYTOPICLINK +" where FTLK_TOPICID = '"+newEntTlProgrammst.getProgKeyid()+"'");
				sqls.add("delete from "+TableNames.TBL_ENT_TL_PROGRAMMST +" where PROG_KEYID = '"+newEntTlProgrammst.getProgKeyid()+"'");
				
			    //sqls.add("delete from  ENT_TL_BATCHCOMPLETION  where BCOM_PROG_KEYID = '"+newEntTlProgrammst.getProgKeyid()+"'");
			
			//	sqls.add(" delete from ENT_TL_NOMINATIONDTL where  NODD_NOMM_KEYID in( select NOMM_KEYID from ENT_TL_NOMINATIONMST where NOMM_PROG_KEYID = '"+newEntTlProgrammst.getProgKeyid()+"')");
            //   sqls.add(" delete from ENT_TL_NOMINATIONMST where NOMM_PROG_KEYID = '"+newEntTlProgrammst.getProgKeyid()+"'");

				dbActionTemplate.executeStatements(sqls);
			/*}
			else{
				throw new Exception("Data Not Deleted");
			}*/
			 
			//return "Data Deleted Successfully";
		}catch(Exception e){
			//return "Data Not Deleted";
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return newEntTlProgrammst;
		
	}
	public void updateBatchName(String progKeyid) throws BusinessApplicationExceptions, Exception {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" UPDATE ENT_TL_BATCHMST A SET A.BACH_NAME = (  ");
		sql.append(" select bachname from ( ");
		sql.append(" select 'Session'||ROWNUM bachname, dt from ");
		sql.append(" ( ");
		sql.append(" SELECT B.BACH_TILLDATE dt  FROM "); 
		sql.append(" ENT_TL_BATCHMST B WHERE B.BACH_PROG_KEYID = '"+progKeyid+"' ");
		sql.append(" ORDER BY BACH_TILLDATE  ");
		sql.append(" )S  ");
		sql.append(" ) where a.BACH_TILLDATE = dt ) where ");
		sql.append(" A.BACH_PROG_KEYID = '"+progKeyid+"'  ");
		CommonMessage.debugMsg(" inside batch update name ");
		dbActionTemplate.executeStatement(sql.toString());	
	}
	
	@Override
	public String getVenuAlloated(String venuId, String fromTime, String tillTime) throws Exception {
	
		StringBuffer sf = new StringBuffer();
		sf.append(" SELECT MAX(BACH_KEYID) FROM Ent_tl_batchmst ");
		sf.append(" WHERE BACH_VENU_KEYID = '" + venuId + "'  ");
		sf.append(" AND (  bach_fromdate BETWEEN to_date('" + fromTime + "','DD-MON-YYYY HH24:MI') AND to_date('" + tillTime + "','DD-MON-YYYY HH24:MI') ");
		sf.append(" OR   bach_tilldate BETWEEN to_date('" + fromTime + "','DD-MON-YYYY HH24:MI') AND to_date('" + tillTime + "','DD-MON-YYYY HH24:MI') ) ");
		
		CommonMessage.debugMsg(" sffff= " + sf.toString());
		
		return dbActionTemplate.getSingleValue(sf.toString());
	}
	
	@Override
	public EntTlProgrammst getprogdata(String topicID, String progMonth, String uniquePos,String progkeyid,String flid) throws Exception {
		// TODO Auto-generated method stub
		try{
			EntTlProgrammst entTlProgrammst = new EntTlProgrammst();
			 StringBuilder sql = new StringBuilder();
		
			if(UIUtils.isValidKeyId(topicID) && UIUtils.isValidKeyId(progMonth) && UIUtils.isValidKeyId(flid) ){
				progMonth=progMonth.toUpperCase();
				sql.append( "select * from ").append(TableNames.TBL_ENT_TL_PROGRAMMST );
				sql.append(" where UPPER(PROG_CODE) = ? AND UPPER(PROG_MONTH) = UPPER(?) AND PROG_TRAR_KEYID = ? ");
			 //CommonMessage.debugMsg("sql1==="+sql.toString());
				Object args [] = new Object [] { topicID,progMonth,flid };
				entTlProgrammst.setSaveArray(dbActionTemplate.getDataArr(sql.toString(), args));
			}
			else if (UIUtils.isValidKeyId(progkeyid) && UIUtils.isValidKeyId(progMonth))
			{
				 //sql = EntTlProgrammstSql.getFormData();
				//CommonMessage.debugMsg("sql2==="+progkeyid + "=="+ progMonth);
				 Object args [] = new Object [] { progkeyid,progMonth };
				 entTlProgrammst.setSaveArray(dbActionTemplate.getDataArr(EntTlProgrammstSql.getFormData(), args));
			}else
			{
				 sql.append("select * from ").append( TableNames.TBL_ENT_TL_PROGRAMMST ).append( " where PROG_KEYID = ? ");
				 Object args [] = new Object [] { progkeyid};
				 //CommonMessage.debugMsg("sql3==="+sql.toString());
				 entTlProgrammst.setSaveArray(dbActionTemplate.getDataArr(sql.toString(), args));
			}
			
			String progTgtRolsql = "select PRTR_KEYID  from ent_tl_prog_target_roles  where PRTR_PROG_KEYID = '"+progkeyid+"'";
			String progCalsql = "select ECAL_KEYID from ent_Tl_Prog_Calendar where ECAL_PROG_ID = '"+progkeyid+"'";
			//String batchSchedulesql = "select ECAL_KEYID from ent_Tl_Prog_Calendar where ECAL_PROG_ID = '"+progkeyid+"'";
			
			entTlProgrammst.setPrtrkeyid(dbActionTemplate.getSingleValue(progTgtRolsql));
			entTlProgrammst.setEcalkeyid( dbActionTemplate.getSingleValue(progCalsql));
			//entTlProgrammst.setBsdlKeyid(dbActionTemplate.getSingleValue(batchSchedulesql));
			
			return entTlProgrammst;
		}catch(Exception e){
			  throw new Exception(e);
		}
	}
	@Override
	public List<String[]> getFaculty(String progId, String month, String uniqPos) throws Exception {
		// TODO Auto-generated method stub

		StringBuilder sql = new StringBuilder( "select '',FTYM_EMPM_KEYID,ftlk_keyid,FTYM_NAME  as \"Faculty\",'' as \"Delete\"  ");
		sql.append(" from ENT_TL_FACULTYTOPICLINK,ENT_TL_FACULTYMST, ENT_TL_PROGRAMMST " );
		sql.append(" where PROG_KEYID ='").append(progId).append("'  " );
		sql.append(" AND UPPER(PROG_MONTH) = UPPER('").append(month).append("')" );
		sql.append(" AND FTLK_TOPICID = PROG_KEYID and FTLK_FACULTYID = FTYM_KEYID ");
		 //AND PROG_UNIQUEPOS ='"+uniqPos+"'
		//CommonMessage.debugMsg("facultySql....."+sql);
		List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql.toString(),null);
		return dataList ;
	}
	@Override
	public List<String[]> getbatch(String progKeyid, String month, String uniqPos) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select BACH_MAXSIZE as maxsze,BACH_VENU_KEYID as venuid,BACH_KEYID,BACH_NAME," +
				" TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') as   \"Session\", " + 
				" TO_CHAR(BACH_FROMDATE,'HH24:MI') as   \"From Time\", " +
				" TO_CHAR(BACH_TILLDATE,'HH24:MI') as   \"To Time\", " +
				" '' as \"Add Employee\",'' as \"Delete\" " +
				" from Ent_tl_batchmst, ENT_TL_PROGRAMMST  where BACH_PROG_KEYID = '"+progKeyid+"'" + 
				" AND UPPER(PROG_MONTH) = UPPER('"+month+"') " + 
				" AND BACH_PROG_KEYID = PROG_KEYID  order by BACH_NAME";
		//AND PROG_UNIQUEPOS ='"+uniqPos+"'
		CommonMessage.debugMsg("BATCH....."+sql);
		List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql,null);
		return dataList ;
	}
	@Override
	public EntTlProgrammst update(EntTlProgrammst newEntTlProgrammst, ProgrammstBean programmstBean)
			throws Exception, ValidationException, BusinessApplicationExceptions {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside DAOIMPL Execute..." );
		CommonMessage.debugMsg("Inside DAOIMPL Execute Update.." );
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		EntTlProgTargetRoles newEntTlProgTargetRoles = new EntTlProgTargetRoles();
		/**ProgramMst Update**/
		StringBuffer sqlBuf = new StringBuffer();
		
		sqlBuf.append( " Select PROG_KEYID from ENT_TL_PROGRAMMST  where PROG_CODE = '"+newEntTlProgrammst.getProgCode()+"' ");
		//added on 03-aug-2014 - babu
		sqlBuf.append( " AND UPPER(PROG_MONTH) = UPPER('"+newEntTlProgrammst.getProgMonth()+"') AND PROG_TRAR_KEYID = '"+newEntTlProgrammst.getProgTrarKeyid()+"' "); 
		//sqlBuf.append( " AND UPPER(PROG_MONTH) = '"+newEntTlProgrammst.getProgMonth()+"' ");
		//sqlBuf.append( " AND PROG_UNIQUEPOS  = '"+newEntTlProgrammst.getProgUniquepos()+"' ");
		CommonMessage.debugMsg("sqlBuf=="+sqlBuf.toString());
		String isExists = dbActionTemplate.getSingleValue(sqlBuf.toString());
		CommonMessage.debugMsg("isExists"+isExists);
		CommonMessage.debugMsg("ProgKeyid()=="+newEntTlProgrammst.getProgKeyid());
		//
		if (!UIUtils.isValidKeyId(isExists)) { 
			//newEntTlProgrammst.setProgCode(newEntTlProgrammst.getProgKeyid());
			newEntTlProgrammst.setProgKeyid(dbActionTemplate.getSequenceNumber(EntTlProgrammstSql.TBL_ENT_TL_PROGRAMMST, 10, "PRG", "", "")); // set the sequnce number
			sqls.add(EntTlProgrammstSql.getInsertSql(entTlProgrammstSql.getProgDbFields(), newEntTlProgrammst.getSaveArray())); // add insert sql for master table
		}
		else {
			newEntTlProgrammst.setProgKeyid(isExists);
			//newEntTlProgrammst.setProgCode(newEntTlProgrammst.getProgKeyid());
			sqls.add(EntTlProgrammstSql.getUpdateSql(entTlProgrammstSql.getProgDbFields(), newEntTlProgrammst.getSaveArray())); // add insert sql for master table
		}

		/////sqls.add(EntTlProgrammstSql.getUpdateSql(entTlProgrammstSql.getProgDbFields(), newEntTlProgrammst.getSaveArray())); // add insert sql for master table
		/*Checking the program type*/
		/**ProgTargetRoles Update**/
		CommonMessage.debugMsg("newEntTlProgrammst.getPrtrkeyid()  = "+newEntTlProgrammst.getPrtrkeyid());
		newEntTlProgTargetRoles.setPrtrKeyid(newEntTlProgrammst.getPrtrkeyid()); 
		newEntTlProgTargetRoles.setPrtrProgKeyid(newEntTlProgrammst.getProgKeyid());
		/*Checking the program type*/
		if(!newEntTlProgrammst.getProgType().equals("G")){
			if (newEntTlProgrammst.getRoleTopicLink()!=null ) {
				newEntTlProgTargetRoles.setPrtrTrarKeyid(newEntTlProgrammst.getRoleTopicLink().getRtlkRtalKeyid());
				fillValuesRole(newEntTlProgTargetRoles,newEntTlProgrammst);
	
				if(!UIUtils.isValidKeyId(newEntTlProgrammst.getPrtrkeyid())){
					newEntTlProgTargetRoles.setPrtrKeyid(dbActionTemplate.getSequenceNumber(EntTlProgTargetRolesSql.TBL_ENT_TL_PROG_TARGET_ROLES, 10, "PRR", "", "")); // set the sequnce number
					newEntTlProgrammst.setPrtrkeyid(newEntTlProgTargetRoles.getPrtrKeyid());
					sqls.add(EntTlProgTargetRolesSql.getInsertSql(entTlProgTargetRolesSql.getPrtrDbFields() , newEntTlProgTargetRoles.getSaveArray()));
				}
				else
				sqls.add(EntTlProgTargetRolesSql.getUpdateSql(entTlProgTargetRolesSql.getPrtrDbFields() , newEntTlProgTargetRoles.getSaveArray()));
			}
		}
		/**ProgCalendar Update**/
		EntTlProgCalendar newEntTlProgCalendar = new EntTlProgCalendar();
		CommonMessage.debugMsg("newEntTlProgrammst.getEcalkeyid() "+newEntTlProgrammst.getEcalkeyid());
		newEntTlProgCalendar.setEcalKeyid(newEntTlProgrammst.getEcalkeyid());
		newEntTlProgCalendar.setEcalPlanMonth(newEntTlProgrammst.getProgMonth());
		if(newEntTlProgrammst.getBatchmaster() != null )
		newEntTlProgCalendar.setEcalPlanTilldate(newEntTlProgrammst.getBatchmaster().getBachTilldate());
		else{
			String tillDatesql = "select ECAL_PLAN_TILLDATE from ent_Tl_Prog_Calendar where ECAL_PROG_ID = '"+newEntTlProgrammst.getProgKeyid()+"'";
			String tilldate = dbActionTemplate.getSingleValue(tillDatesql);
			CommonMessage.debugMsg("tillDatesql  :"+tillDatesql);
			CommonMessage.debugMsg("tilldate   :"+tilldate);
			newEntTlProgCalendar.setEcalPlanTilldate(tilldate);
		}
			
		fillCalendarValues(newEntTlProgCalendar,newEntTlProgrammst);
		if(UIUtils.isValidKeyId(newEntTlProgrammst.getEcalkeyid()))
			sqls.add(EntTlProgCalendarSql.getUpdateSql(entTlProgCalendarSql.getEcalDbFields(), newEntTlProgCalendar.getSaveArray()));
		else{
			newEntTlProgCalendar.setEcalKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_ENT_TL_PROG_CALENDAR, 12, "EPC", "", ""));
			newEntTlProgrammst.setEcalkeyid(newEntTlProgCalendar.getEcalKeyid());
			sqls.add(EntTlProgCalendarSql.getInsertSql(entTlProgCalendarSql.getEcalDbFields(), newEntTlProgCalendar.getSaveArray()));
		}
		CommonMessage.debugMsg("before Faculty link  ");
		/**Batch Insert/Update**/		
	
		//CommonMessage.debugMsg("newEntTlProgrammst.getBatchmaster().getIsbatch()"+newEntTlProgrammst.getBatchmaster().getIsbatch());
		//CommonMessage.debugMsg("newEntTlProgrammst.getBatchmaster() "+newEntTlProgrammst.getBatchmaster() );
		//CommonMessage.debugMsg("BachKeyid=== "+newEntTlProgrammst.getBatchmaster().getBachKeyid());
		
		if(newEntTlProgrammst.getBatchmaster() != null && UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getIsbatch())) {
			 CommonMessage.debugMsg("Inside Batch Execute");
			 newEntTlProgrammst.getBatchmaster().setBachProgKeyid(newEntTlProgrammst.getProgKeyid());
			 newEntTlProgrammst.getBatchmaster().setBachCreatedby(newEntTlProgrammst.getProgCreatedby());
			 CommonMessage.debugMsg("UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getBachKeyid()"+UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getBachKeyid()));
			 //CommonMessage.debugMsg("UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getBachKeyid()"+UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getBachKeyid());
			 
			 EntTlBatchScheduleSql entTlBatchScheduleSql = new EntTlBatchScheduleSql(); // contains dbtable,field names, Field types and related sqls  of master table
			 EntTlBatchSchedule entTlBatchSchedule =newEntTlProgrammst.getEntTlBatchSchedule();  
			
			 String chekExistsSQl = "select  Bsdl_Keyid from Ent_Tl_Batch_Schedule where Bsdl_Bach_Keyid='"+newEntTlProgrammst.getBatchmaster().getBachKeyid()+"'";
			 String  chekExists = dbActionTemplate.getSingleValue(chekExistsSQl);
			  CommonMessage.debugMsg("chekExistschekExistsinsert  "+chekExists);
			
			 if( newEntTlProgrammst.getBatchmaster() != null && 
					 !UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getBachKeyid())
					 && !Constants.futureNullDate.equals(newEntTlProgrammst.getBatchmaster().getBachTilldate())
					 && UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getBachTilldate()) 
			){
				 String bachName = getBachName(newEntTlProgrammst.getBatchmaster());
				 newEntTlProgrammst.getBatchmaster().setBachName(bachName);
				 newEntTlProgrammst.getBatchmaster().setBachKeyid(dbActionTemplate.getSequenceNumber(entBatchMstSql.TBL_ENT_TL_BATCHMST,8,"BAT","","" ));
				 sqls.add(EntBatchMstSql.getInsertSql(entBatchMstSql.getBachDbFields(),newEntTlProgrammst.getBatchmaster().getSaveArray()));
				 
				 
				
				 /**ProgTopicSkillRating**/
				 CommonMessage.debugMsg("Inside ProgTargetRating Execute");
				 if(!UIUtils.isValidKeyId(newEntTlProgrammst.getPrtrkeyid())){
				  newEntTlProgrammst.getProgTargetSkills().setPrtsKeyid(dbActionTemplate.getSequenceNumber(entTlProgTargetSkillsSql.TBL_ENT_TL_PROG_TARGET_SKILLS, 10, "PRT", "", "")); // set the sequnce number
				  sqls.add(EntTlProgTargetSkillsSql.getInsertSql(entTlProgTargetSkillsSql.getPrtsDbFields(),newEntTlProgrammst.getProgTargetSkills().getSaveArray()));
				 }
				 else{
					 newEntTlProgrammst.getProgTargetSkills().setPrtsKeyid(chekExists);
					 
					 sqls.add(EntTlProgTargetSkillsSql.getUpdateSql(entTlProgTargetSkillsSql.getPrtsDbFields(),newEntTlProgrammst.getProgTargetSkills().getSaveArray()));
				 }
				 CommonMessage.debugMsg("targetKeyid  "+newEntTlProgrammst.getProgTargetSkills().getPrtsKeyid());
				 
			 }
		else{
			 CommonMessage.debugMsg("Inside UPDATE Execute");
			 
			 newEntTlProgrammst.getBatchmaster().setBachCreatedon(newEntTlProgrammst.getProgCreatedon());
			 /**ProgTopicSkillRating**/
			 CommonMessage.debugMsg("Inside ProgTargetRating Execute");
			  
			 CommonMessage.debugMsg("targetKeyidUpdate  "+newEntTlProgrammst.getProgTargetSkills().getPrtsKeyid());
			 sqls.add(EntTlProgTargetSkillsSql.getUpdateSql(entTlProgTargetSkillsSql.getPrtsDbFields(),newEntTlProgrammst.getProgTargetSkills().getSaveArray()));
			 sqls.add(EntBatchMstSql.getUpdateSql(entBatchMstSql.getBachDbFields(),newEntTlProgrammst.getBatchmaster().getSaveArray()));
			}
			  /**Batch Schedule**/
			 //if(!UIUtils.isValidKeyId(newEntTlProgrammst.getBsdlKeyid())){
			 entTlBatchSchedule.setBsdlProgKeyid(newEntTlProgrammst.getProgKeyid() );
			 entTlBatchSchedule.setBsdlBachKeyid(newEntTlProgrammst.getBatchmaster().getBachKeyid());
			 entTlBatchSchedule.setBsdlCreatedon(newEntTlProgrammst.getProgCreatedon() );
			 entTlBatchSchedule.setBsdlModifiedon(newEntTlProgrammst.getProgModifiedon());
			 entTlBatchSchedule.setBsdlEcalKeyid(newEntTlProgCalendar.getEcalKeyid());
			 if(!UIUtils.isValidKeyId(chekExists)){
				 entTlBatchSchedule.setBsdlKeyid(dbActionTemplate.getSequenceNumber(EntTlBatchScheduleSql.TBL_ENT_TL_BATCH_SCHEDULE, 10, "BSDL",null,null));
				 sqls.add(EntTlBatchScheduleSql.getInsertSql(entTlBatchScheduleSql.getBsdlDbFields(), entTlBatchSchedule.getSaveArray())); // add insert sql	 
			 }
			 else{
				 sqls.add(EntTlBatchScheduleSql.getUpdateSql(entTlBatchScheduleSql.getBsdlDbFields(), entTlBatchSchedule.getSaveArray())); // add insert sql
			 }
			 /**End**/
		}/**Faculty Insert/Update**/
		 if(newEntTlProgrammst.getFacultyTopicLink() != null  ){
			 CommonMessage.debugMsg("Inside Faculty Execute");
			 newEntTlProgrammst.getFacultyTopicLink().setFtlkTopicid(newEntTlProgrammst.getProgKeyid());
			 newEntTlProgrammst.getFacultyTopicLink().setFtlkCreatedby(newEntTlProgrammst.getProgCreatedby());
			 if( !UIUtils.isValidKeyId(newEntTlProgrammst.getFacultyTopicLink().getFtlkKeyid())){
				 
				newEntTlProgrammst.getFacultyTopicLink().setFtlkKeyid(dbActionTemplate.getSequenceNumber(entTlFacultytopiclinkSql.TBL_ENT_TL_FACULTYTOPICLINK , 10, "FTL", "",""));
				sqls.add(EntTlFacultytopiclinkSql.getInsertSql(entTlFacultytopiclinkSql.getFtlkDbFields(), newEntTlProgrammst.getFacultyTopicLink().getSaveArray()));
			 }
		 
		 else{
			 newEntTlProgrammst.getFacultyTopicLink().setFtlkCreatedon(newEntTlProgrammst.getProgCreatedon() );
			 sqls.add(EntTlFacultytopiclinkSql.getUpdateSql(entTlFacultytopiclinkSql.getFtlkDbFields(), newEntTlProgrammst.getFacultyTopicLink().getSaveArray()));
		 	}
		 } /**End**/
		 /**UniquePosition Insert/Update**/
		 /*Checking the program type*/

		 if(!newEntTlProgrammst.getProgType().equals("G")){
			 if(newEntTlProgrammst.getRoleTopicLink() != null){
				 CommonMessage.debugMsg("Inside Role Execute");
				 
					String allUnique = programmstBean.getAllUniquePosition();
					 CommonMessage.debugMsg("programmstBean.getAllUniquePosition()"+allUnique);
					 if (UIUtils.isValidKeyId(allUnique) && allUnique.equals("Y")) {
						 
						 CommonMessage.debugMsg("allUniqueDao==="+allUnique);
						 StringBuffer sf = new StringBuffer();
						 sf.append(" SELECT DISTINCT ROLE_KEYID from  Ent_Vw_Rolemst    ");
						 sf.append(" where INSTR( PARENTFLIDS||FLID ,'" + newEntTlProgrammst.getProgTrarKeyid() + "')>0     ");
						 sf.append(" AND ROLE_KEYID in( select  TMTM_ROLE_KEYID from  ENT_TL_UNIQPOSTOPIC_LINKMST  ");
						 sf.append("where TMTM_TOPI_KEYID ='" + newEntTlProgrammst.getProgCode() + "' ) " );
						 
						 List<String[]> roleIds = dbActionTemplate.getDataList(sf.toString());
						 
						 CommonMessage.debugMsg("roleIds.size()==="+roleIds.size());
						 for ( int i=0;i<roleIds.size();i++) {
							 newEntTlProgrammst.getRoleTopicLink().setRtlkRtalKeyid(roleIds.get(i)[0]);
							 CommonMessage.debugMsg("roleIds.get(i)[0]==="+roleIds.get(i)[0]);
							 newEntTlProgrammst.getRoleTopicLink().setRtlkKeyid(dbActionTemplate.getSequenceNumber(entTlRoleTopicLinkSql.TBL_ENT_TL_ROLE_TOPIC_LINK , 10, "RTA", "",""));
							 newEntTlProgrammst.getRoleTopicLink().setRtlkTopiKeyid(newEntTlProgrammst.getProgKeyid());
							 sqls.add(EntTlRoleTopicLinkSql.getInsertSql(entTlRoleTopicLinkSql.getRtlkDbFields() , newEntTlProgrammst.getRoleTopicLink().getSaveArray()));
						 }
						 
					 } else {
						 newEntTlProgrammst.getRoleTopicLink().setRtlkTopiKeyid(newEntTlProgrammst.getProgKeyid());
						 if(!UIUtils.isValidKeyId(newEntTlProgrammst.getRoleTopicLink().getRtlkKeyid())){
							 newEntTlProgrammst.getRoleTopicLink().setRtlkKeyid(dbActionTemplate.getSequenceNumber(entTlRoleTopicLinkSql.TBL_ENT_TL_ROLE_TOPIC_LINK , 10, "RTA", "",""));	 
							 sqls.add(EntTlRoleTopicLinkSql.getInsertSql(entTlRoleTopicLinkSql.getRtlkDbFields() , newEntTlProgrammst.getRoleTopicLink().getSaveArray()));
						 }else{
							 newEntTlProgrammst.getRoleTopicLink().setRtlkCreatedon(newEntTlProgrammst.getProgCreatedon() );
							 sqls.add(EntTlRoleTopicLinkSql.getUpdateSql(entTlRoleTopicLinkSql.getRtlkDbFields() , newEntTlProgrammst.getRoleTopicLink().getSaveArray()));
						 }
					}
			 }
		 }
		 /**End**/
		 CommonMessage.debugMsg("before executeStatements  ");
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			 
			return newEntTlProgrammst;
	}
	public String getBachName(EntBatchMst batchmaster) throws Exception {
		// TODO Auto-generated method stub
		String batchMonth = batchmaster.getBachTilldate() ;
		CommonMessage.debugMsg("batchMonth,,....."+batchMonth);
		if (batchMonth.length()>0) { 
			batchMonth = batchMonth.substring(batchMonth.indexOf("-"),batchMonth.length());
		 	batchMonth = batchMonth.substring(1);
		}
		 
		 String countSql= "select Count(*) from ENT_TL_BATCHMST where 1=1 " +
		 		"AND  to_char(BACH_TILLDATE,'MON-YYYY')  = trim(upper('"+batchMonth+"')) AND  BACH_PROG_KEYID = trim('"+batchmaster.getBachProgKeyid()+"')";
		 CommonMessage.debugMsg("countSql  "+countSql );
		 String Count = dbActionTemplate.getSingleValue(countSql);
		 
		 int newCnt = Integer.parseInt(Count);
		 newCnt = newCnt+1;
		 CommonMessage.debugMsg("countSql  "+countSql+" newCnt  "+newCnt);
		 String bachName = "Session"+Integer.toString(newCnt);
		 CommonMessage.debugMsg("batchMonth   "+batchMonth +" bachName  "+bachName);
		return bachName;
	}
	@Override
	public String deleteDetail(String keyId, String gridId, String topicId) throws Exception,BusinessApplicationExceptions{
		// TODO Auto-generated method stub
		try{
			String sqls ="";
			CommonMessage.debugMsg("dgridId "+gridId);
		if("facultyGrd".equals(gridId))
		{
			CommonMessage.debugMsg("detail List 123 ");
			String DeleteRefer="Select COUNT(*) from   ENT_TL_BATCH_SCHEDULE where 1=1  AND  bsdl_prog_keyid='"+topicId+"' AND BSDL_STATUS IN ('C')";		
			String detailList=dbActionTemplate.getSingleValue(DeleteRefer);
			 CommonMessage.debugMsg("detail List  555"+detailList);
			if(Integer.parseInt(detailList)>0)
			{
			  CommonMessage.debugMsg("detail List  555"+detailList);
			  throw  new BusinessApplicationExceptions("TopicReference,");
			  
			}else
				sqls = "delete from "+TableNames.TBL_ENT_TL_FACULTYTOPICLINK +" where FTLK_KEYID = '"+keyId+"'";
				
		}else if("uniqPosGrd".equals(gridId)){
			sqls = "delete from ENT_TL_ROLE_TOPIC_LINK where RTLK_KEYID = '"+keyId+"'" ;
		}
		else{
			sqls = "delete from "+TableNames.TBL_ENT_TL_BATCHMST+" where BACH_KEYID = '"+keyId+"'" ;
		}CommonMessage.debugMsg(gridId+"  SQls Delete  "+sqls );
		dbActionTemplate.executeStatement(sqls);
		if(!"facultyGrd".equals(gridId))
			updateBatchName(topicId);
		return "Data Deleted Successfully";
		}
		catch(BusinessApplicationExceptions e){
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
			return "Data Not Deleted";	
		}
		
	}
	@Override
	public List<String[]> getUpbasedEmployee(CommonFilter commonFilter,String progKeyid,String bachId,String flid,String progType,String roleId, String newFlid) throws Exception {
		CommonMessage.debugMsg("Inside daoimpl getUpbasedEmployee");
		//List<String> paramValues = new ArrayList<String>();			
		//CommonMessage.debugMsg("getUpbasedEmployeeCount :" +progType);
		//CommonMessage.debugMsg("if(!progType.equals('gt')) :" +(!progType.equals("gt")));
		//String sql = "select ''as selVal,'' as bachkeyid ,empm_keyid,empm_name,'' from gen_tl_employeemst where empm_roleid = '"+uniquePostionId+"'";
	 
	   StringBuilder sql = new StringBuilder();
	   sql.append("  SELECT * FROM ( " );
	   sql.append( " SELECT DISTINCT '' AS selval, bstd_bach_keyid AS bachkeyid, empm_keyid, ");
	   //sql.append(" empm_name, bstd_keyid,EMPM_CODE,ROLE_NAME,PARENTS,CURSKIL FROM gen_tl_employeemst,gen_TL_rolemst,gen_tl_fnlnroleteam,gen_mv_flidhierarchy, (SELECT nodd_empm_keyid FROM ent_tl_nominationmst, ");
	   
	   sql.append(" empm_name, bstd_keyid,EMPM_CODE,ROLE_NAME,PARENTS,CURSKIL ,'' as \"Delete\" FROM gen_tl_employeemst,gen_TL_rolemst,");
	   //sql.append(" gen_tl_fnlnroleteam,gen_mv_flidhierarchy, ");
	   sql.append(" ( SELECT frt_empm_keyid,MIN(FRT_FNLN_KEYID) AS FRT_FNLN_KEYID  FROM gen_tl_fnlnroleteam , gen_mv_flidhierarchy ");
	   sql.append(" WHERE FLID = FRT_FNLN_KEYID ");
	   if(progType.equals("gt")){	 
		   if(UIUtils.isValidKeyId(roleId))
			   sql.append(" AND FRT_ROLE_KEYID='").append(roleId).append("'");
	   }
	   
	   sql.append(" AND INSTR(PARENTFLIDS||FLID,'").append( flid).append('\'').append(") > 0 GROUP BY frt_empm_keyid  ");
	  /*
	   if(progType.equals("gt")){	   
		   sql.append(" UNION ALL ");
		   sql.append(" SELECT frt_empm_keyid,MIN(FRT_FNLN_KEYID) AS FRT_FNLN_KEYID  FROM gen_tl_fnlnroleteam , gen_mv_flidhierarchy ");
		   sql.append(" WHERE FLID = FRT_FNLN_KEYID ");
		   if(UIUtils.isValidKeyId(roleId))
			   sql.append(" AND FRT_ROLE_KEYID='").append(roleId).append("'");
		   
		   sql.append(" AND INSTR(PARENTFLIDS||FLID,'").append( newFlid).append('\'').append(") > 0 GROUP BY frt_empm_keyid  ");   
	   }  */
	   
	   sql.append(" )");
	   sql.append(" ,gen_mv_flidhierarchy, "); 
	   
	   sql.append(" (SELECT nodd_empm_keyid FROM ent_tl_nominationmst, ");	   
	   sql.append("	ent_tl_nominationdtl WHERE nomm_keyid = nodd_nomm_keyid ");
	   sql.append("	AND nodd_empm_keyid NOT IN ( SELECT bstd_empm_keyid FROM ent_tl_batch_employee_link,ENT_TL_BATCHMST WHERE bstd_bach_keyid <>  '"+bachId+"' and bach_keyid = bstd_bach_keyid and bach_prog_keyid = '" ).append(  progKeyid ).append(  "'  ) ");
	   sql.append(" ),ent_tl_batch_employee_link, " ) ;
	   sql.append(" (SELECT ASMM_EMPM_KEYID ASSEMPMID,ASMD_PROG_KEYID ASSPROGID,SKRM_DESCRIPTION CURSKIL,SKRM_KEYID CURSKILID, ASMM_EVALUATION_DATE TRNDATE ");
	   sql.append("	FROM ENT_TL_ASSESSMENTMST,ENT_TL_ASSESSMENTDTL,ENT_TL_SKILL_RATINGMST ");
	   sql.append(" WHERE ASMM_KEYID=ASMD_ASMM_KEYID AND SKRM_ORDERNO=ASMD_CURRENT_RATE)");
	   sql.append("  WHERE 1 = 1 AND empm_keyid = frt_empm_keyid AND FLID = FRT_FNLN_KEYID ");
	   
	   if(progType.equals("gt"))
		   sql.append("  AND EMPM_ROLEID = ROLE_KEYID (+) ");
	   else
		   sql.append("  AND EMPM_ROLEID = ROLE_KEYID ");
	   
	   
	   sql.append("  AND ASSEMPMID(+)=EMPM_KEYID AND  empm_keyid = nodd_empm_keyid(+) ");
	   sql.append(" AND bstd_empm_keyid(+) = empm_keyid ");
	   
	   if(!progType.equals("gt")){
		   sql.append(" AND empm_roleid  ");
		   sql.append(" in (select RTLK_RTAL_KEYID from ENT_TL_ROLE_TOPIC_LINK where RTLK_TOPI_KEYID ='").append( progKeyid).append( "')");
	   }
	   sql.append(" AND   BSTD_BACH_KEYID(+) = '").append( bachId).append('\'');
	   //sql.append(" AND   frt_fnln_keyid  = '").append( flid).append('\'');
	   sql.append(" AND "); 
	/*   if(progType.equals("gt")){
		   sql.append(" ( "); 
	   }*/
	   sql.append("INSTR(PARENTFLIDS||FLID,'").append( flid).append('\'').append(") > 0 ");
	   /*
	   if(progType.equals("gt"))
		   sql.append("  OR INSTR(PARENTFLIDS||FLID,'").append( newFlid).append('\'').append(") > 0 )");   
	     */
	   sql.append("AND EMPM_ACTIVE='Y'");
	   sql.append(" AND empm_keyid NOT IN ( SELECT bstd_empm_keyid FROM ent_tl_batch_employee_link, ENT_TL_BATCHMST WHERE bstd_bach_keyid <> '").append( bachId).append( "' and bach_keyid = bstd_bach_keyid and bach_prog_keyid = '").append( progKeyid).append( "' ) ");

	   sql.append(" order by BSTD_KEYID asc ");
	   sql.append( " ) WHERE 1 = 1 " );
	   sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
	   String countsql = CommonFilterSqls.countSql(sql.toString(), commonFilter.getGridFilter());
	   String cntStr = dbActionTemplate.getSingleValue(countsql);
	   int count = Integer.parseInt(cntStr);
	   commonFilter.setTotalRecordCnt(count);
	   List<String[]> getEmpList ;
	   if( count > 0  ){
			
			GridParams gridParams = new GridParams();
			gridParams.setFromRow(commonFilter.getFromRow());
			gridParams.setToRow(commonFilter.getToRow());
			CommonMessage.debugMsg("From Row  :" +commonFilter.getFromRow());
			CommonMessage.debugMsg("To Row  :" +commonFilter.getToRow());
			
			String oSql =  CommonFilterSqls.addPaginationParams(sql.toString(), gridParams);
			CommonMessage.debugMsg("osql " + oSql);
			
			getEmpList = dbActionTemplate.getDataList(oSql.toString());
			CommonMessage.debugMsg("getEmpList :" +getEmpList.size());
			CommonMessage.debugMsg("osql  :" +oSql);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = cntStr;//paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
			
			return getEmpList;
			
			
		}
	   
		//List<String[]> getEmpList = dbActionTemplate.getDataList(sql.toString());
		
	   throw new NoDataFoundException("No Data Found");
	}
	
	public String getUpbasedEmployeeCount(CommonFilter commonFilter,String progKeyid,String bachId,String flid,String progType,String roleId,String newFlid) throws Exception {
		//CommonMessage.debugMsg("Inside daoimpl count");
		List<String> paramValues = new ArrayList<String>();			
		///CommonMessage.debugMsg("getUpbasedEmployeeCount :" +progType);
		//CommonMessage.debugMsg("if(!progType.equals('gt')) :" +(!progType.equals("gt")));
		//String sql = "select ''as selVal,'' as bachkeyid ,empm_keyid,empm_name,'' from gen_tl_employeemst where empm_roleid = '"+uniquePostionId+"'";
	 
	   StringBuilder sql = new StringBuilder();
	   sql.append("  SELECT * FROM ( " );
	   sql.append( " SELECT DISTINCT '' AS selval, bstd_bach_keyid AS bachkeyid, empm_keyid, ");
	   //sql.append(" empm_name, bstd_keyid,EMPM_CODE,ROLE_NAME,PARENTS,CURSKIL FROM gen_tl_employeemst,gen_TL_rolemst,gen_tl_fnlnroleteam,gen_mv_flidhierarchy, (SELECT nodd_empm_keyid FROM ent_tl_nominationmst, ");
	   
	   sql.append(" empm_name, bstd_keyid,EMPM_CODE,ROLE_NAME,PARENTS,CURSKIL ,'' as \"Delete\" FROM gen_tl_employeemst,gen_TL_rolemst,");
	   //sql.append(" gen_tl_fnlnroleteam,gen_mv_flidhierarchy, ");
	   sql.append(" ( SELECT frt_empm_keyid,MIN(FRT_FNLN_KEYID) AS FRT_FNLN_KEYID  FROM gen_tl_fnlnroleteam , gen_mv_flidhierarchy ");
	   sql.append(" WHERE FLID = FRT_FNLN_KEYID ");
	   if(progType.equals("gt")){	 
		   if(UIUtils.isValidKeyId(roleId))
			   sql.append(" AND FRT_ROLE_KEYID='").append(roleId).append("'");
	   }
	   
	   sql.append(" AND INSTR(PARENTFLIDS||FLID,'").append( flid).append('\'').append(") > 0 GROUP BY frt_empm_keyid  ");
	  /*
	   if(progType.equals("gt")){	   
		   sql.append(" UNION ALL ");
		   sql.append(" SELECT frt_empm_keyid,MIN(FRT_FNLN_KEYID) AS FRT_FNLN_KEYID  FROM gen_tl_fnlnroleteam , gen_mv_flidhierarchy ");
		   sql.append(" WHERE FLID = FRT_FNLN_KEYID ");
		   if(UIUtils.isValidKeyId(roleId))
			   sql.append(" AND FRT_ROLE_KEYID='").append(roleId).append("'");
		   
		   sql.append(" AND INSTR(PARENTFLIDS||FLID,'").append( newFlid).append('\'').append(") > 0 GROUP BY frt_empm_keyid  ");   
	   }  
	   */
	   sql.append(" )");
	   sql.append(" ,gen_mv_flidhierarchy, "); 
	   
	   sql.append(" (SELECT nodd_empm_keyid FROM ent_tl_nominationmst, ");	   
	   sql.append("	ent_tl_nominationdtl WHERE nomm_keyid = nodd_nomm_keyid ");
	   sql.append("	AND nodd_empm_keyid NOT IN ( SELECT bstd_empm_keyid FROM ent_tl_batch_employee_link,ENT_TL_BATCHMST WHERE bstd_bach_keyid <>  '"+bachId+"' and bach_keyid = bstd_bach_keyid and bach_prog_keyid = '" ).append(  progKeyid ).append(  "'  ) ");
	   sql.append(" ),ent_tl_batch_employee_link, " ) ;
	   sql.append(" (SELECT ASMM_EMPM_KEYID ASSEMPMID,ASMD_PROG_KEYID ASSPROGID,SKRM_DESCRIPTION CURSKIL,SKRM_KEYID CURSKILID, ASMM_EVALUATION_DATE TRNDATE ");
	   sql.append("	FROM ENT_TL_ASSESSMENTMST,ENT_TL_ASSESSMENTDTL,ENT_TL_SKILL_RATINGMST ");
	   sql.append(" WHERE ASMM_KEYID=ASMD_ASMM_KEYID AND SKRM_ORDERNO=ASMD_CURRENT_RATE)");
	   sql.append("  WHERE 1 = 1 AND empm_keyid = frt_empm_keyid AND FLID = FRT_FNLN_KEYID ");
	   
	   if(progType.equals("gt"))
		   sql.append("  AND EMPM_ROLEID = ROLE_KEYID (+) ");
	   else
		   sql.append("  AND EMPM_ROLEID = ROLE_KEYID ");
	   
	   
	   sql.append("  AND ASSEMPMID(+)=EMPM_KEYID AND  empm_keyid = nodd_empm_keyid(+) ");
	   sql.append(" AND bstd_empm_keyid(+) = empm_keyid ");
	   
	   if(!progType.equals("gt")){
		   sql.append(" AND empm_roleid  ");
		   sql.append(" in (select RTLK_RTAL_KEYID from ENT_TL_ROLE_TOPIC_LINK where RTLK_TOPI_KEYID ='").append( progKeyid).append( "')");
	   }
	   sql.append(" AND   BSTD_BACH_KEYID(+) = '").append( bachId).append('\'');
	   //sql.append(" AND   frt_fnln_keyid  = '").append( flid).append('\'');
	   sql.append(" AND "); 
	  /* if(progType.equals("gt")){
		   sql.append(" ( "); 
	   }*/
	   sql.append("INSTR(PARENTFLIDS||FLID,'").append( flid).append('\'').append(") > 0 ");
	   /*
	   if(progType.equals("gt"))
		   sql.append("  OR INSTR(PARENTFLIDS||FLID,'").append( newFlid).append('\'').append(") > 0"); */
	  // sql.append(" )");
	   
	   sql.append(" AND empm_keyid NOT IN ( SELECT bstd_empm_keyid FROM ent_tl_batch_employee_link, ENT_TL_BATCHMST WHERE bstd_bach_keyid <> '").append( bachId).append( "' and bach_keyid = bstd_bach_keyid and bach_prog_keyid = '").append( progKeyid).append( "' ) ");

	   sql.append(" order by BSTD_KEYID asc ");
	   sql.append( " ) WHERE 1 = 1 " );
	   CommonMessage.debugMsg(sql);
	   //CommonMessage.debugMsg("sql Test 1:" +sql);
	   sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
	   //CommonMessage.debugMsg("Test 2 :" +sql);
	   String countsql = CommonFilterSqls.countSql(sql.toString(), commonFilter.getGridFilter());
	   String cntStr = dbActionTemplate.getSingleValue(countsql);
	//   CommonMessage.debugMsg("Count from Dao:"+cntStr );
	   return cntStr;
	  
	}
	@Override
	public Workbook getTrngCalReportExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub

		   ResultSet rs = null;
		   try{
			
			rs =   getTrngCalReportResultSet(commonFilter);
			CommonMessage.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,rptFormat, 3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet getTrngCalReportResultSet(CommonFilter commonFilter) throws Exception
	{
		CommonMessage.debugMsg("Inside daoimpl");
		List<String> paramValues = new ArrayList<String>();	
		String functionName = "RPT_PC_CARD.RPT_FN_ETTRNCALENDAR";//commonFilter.getFunctionName();
		
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
					
		CommonMessage.debugMsg("ParamValues:"+paramValues);	

		ResultSet rs = null;
		
		rs = dbActionTemplate.dbFunctionCall(functionName, paramValues);
		
		CommonMessage.debugMsg("rs value.....");
		return rs;
	}
	/**Added For Report**/
	@Override
	public List<String[]> getTrainingCalendarRPT(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		String functionName = "RPT_PC_CARD.RPT_FN_ETTRNCALENDAR_REPORT";//commonFilter.getFunctionName();
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
						
			CommonMessage.debugMsg("ParamValues:"+paramValues);	
			List<String[]> dataList =   dbActionTemplate.processFunctionCalls(functionName, paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg(dataList);
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public List<String[]> getselectProgId(String topicid) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:" +topicid);	
		String sql = " select TMTM_FLID  from ENT_TL_UNIQPOSTOPIC_LINKMST where  TMTM_TOPI_KEYID='"+topicid+"' ";
		List<String[]> ProgID=dbActionTemplate.getDataList(sql);
		/*Boolean keyidExists = dbActionTemplate.checkDuplicateValue("ENT_TL_PROGRAMMST","PROG_KEYID",progId,null );
		CommonMessage.debugMsg("keyidExists  "+keyidExists );
		EntTlProgrammst newEntTLProgrammst = new EntTlProgrammst(); 
		if(keyidExists){
			newEntTLProgrammst = getprogdata(progId);
			String progData = newEntTLProgrammst.getProgKeyid()+"-"+newEntTLProgrammst.getProgMonth()+"-"+newEntTLProgrammst.getProgUniquepos()
			+"-"+newEntTLProgrammst.getBsdlKeyid()+"-"+newEntTLProgrammst.getEcalkeyid()+"-"+newEntTLProgrammst.getProgMaterialReady()
			+"-"+newEntTLProgrammst.getProgMaxDuration()+"-"+newEntTLProgrammst.getProgFunction();
			ProgID.add(progData);
		}
		CommonMessage.debugMsg("newEntTLProgrammst "+newEntTLProgrammst.getBsdlKeyid());*/
		CommonMessage.debugMsg("ProgID "+ProgID);
		 
		return ProgID;
	}
	@Override
	public Workbook getTrngCalExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		  ResultSet rs = null;
		   try{
			
			rs =   getTrngCalResultSet(commonFilter);
			CommonMessage.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getTrngCalResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside daoimpl");
		List<String> paramValues = new ArrayList<String>();	
		String functionName = "RPT_PC_CARD.RPT_FN_ETTRNCALENDAR";//commonFilter.getFunctionName();
		
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
					
		CommonMessage.debugMsg("ParamValues:"+paramValues);	

		ResultSet rs = null;
		
		rs = dbActionTemplate.dbFunctionCall(functionName, paramValues);
		
		CommonMessage.debugMsg("rs value.....");
		return rs;
	}
	@Override
	public Boolean getFrequecyData(String topicID) throws Exception {
		// TODO Auto-generated method stub
		Boolean Status=true;
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:" +topicID);	
		String sql = "  Select Count(*) from  ENT_TL_BATCH_SCHEDULE  where  BSDL_PROG_KEYID='"+topicID+"' AND BSDL_STATUS='P'";
		String ProgID=dbActionTemplate.getSingleValue(sql);
		String sqls = "  Select Count(*) from  ENT_TL_BATCH_SCHEDULE  where  BSDL_PROG_KEYID='' AND BSDL_STATUS='' ";
		String ProgCount=dbActionTemplate.getSingleValue(sqls);
		if(Integer.parseInt(ProgID)>0){
			Status=false;			
		}
		if(Integer.parseInt(ProgCount)>0){
			Status=true;			
		}
		CommonMessage.debugMsg("ProgID "+ProgID);
		CommonMessage.debugMsg("ProgCount "+ProgCount);
		return Status;
	}
	@Override
	public String getPermStrength(String venuId) throws Exception {
		// TODO Auto-generated method stub
		String getPermitedStrength = "select venu_maxcapacity from ENT_TL_VENUEMST where venu_keyid='"+venuId+"'";
		String permitedStrngth =  dbActionTemplate.getSingleValue(getPermitedStrength);
		return permitedStrngth;
	}
	@Override
	public List<String[]> getUniqPosData(String progKeyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "";
		/*sql = "select '',RTLK_KEYID,RTLK_RTAL_KEYID,ROLE_NAME  as \"Unique Position\",'' as \"Delete\"  "+
		" from ENT_TL_ROLE_TOPIC_LINK, GEN_TL_ROLEMST " +
		" where RTLK_TOPI_KEYID ='"+progKeyid+"'  " +
		 " and RTLK_RTAL_KEYID = ROLE_KEYID ";*/
		
		StringBuffer sf = new StringBuffer();
		sf.append(" select '',RTLK_KEYID,RTLK_RTAL_KEYID,ROLE_NAME || ' - ' || FNLN_DISPLAYCODE   as \"Unique Position\",'' as \"Delete\"  ");
		sf.append(" from ENT_TL_ROLE_TOPIC_LINK, Ent_Vw_Rolemst ");
		sf.append(" where RTLK_TOPI_KEYID ='"+progKeyid+"'  ");
		sf.append(" and RTLK_RTAL_KEYID = ROLE_KEYID ");
		
		sql = sf.toString(); 

		CommonMessage.debugMsg("facultySql....."+sql);
		List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql,null);
		return dataList ;
	}	
}
