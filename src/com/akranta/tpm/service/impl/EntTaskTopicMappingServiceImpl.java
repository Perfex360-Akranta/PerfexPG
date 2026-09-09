package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.dao.EntTlSkillReviewpointmstDao;
import com.akranta.tpm.dao.EntTlUniqpostopicLinkmstDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.EntTlSkillReviewpointmstDaoImpl;
import com.akranta.tpm.dao.impl.EntTlUniqpostopicLinkmstDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlUniqpostopicLinkdtl;
import com.akranta.tpm.model.EntTlUniqpostopicLinkmst;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.service.EntTaskTopicMappingService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class EntTaskTopicMappingServiceImpl implements EntTaskTopicMappingService {

	EntTlUniqpostopicLinkmstDao entTlTaskMappingTopicmstDao;
	private Validations validations;
	public EntTaskTopicMappingServiceImpl(DBActionTemplate dbActionTemplate) {
		entTlTaskMappingTopicmstDao = new EntTlUniqpostopicLinkmstDaoImpl(dbActionTemplate);
		// genTlMomatendanceDao = new
		// GenTlMomattendanceDaoImpl(dbActionTemplate);
		validations = new Validations();

	}
	@Override
	public EntTlUniqpostopicLinkmst create(EntTlUniqpostopicLinkmst newentTlTaskMappingTopicmst,EntTlUniqpostopicLinkmst existEntTlTaskMappingTopicmst) throws Exception
	{
		   CommonMessage.debugMsg("Create Block:::");
			try {
				CommonMessage.debugMsg("Inside Create");
				String validationsFor = "create";
				String xml = "TaskTopicMapping";
				CommonMessage.debugMsg("Validation XML Name:::::::::" + newentTlTaskMappingTopicmst.getTaskTopicDetails());
				CommonMessage.debugMsg("After Fill Values.............."+ newentTlTaskMappingTopicmst.getTmtmFlid());
				 
				if("SBU".equals(newentTlTaskMappingTopicmst.getTmtmElementType()))
					 throw new ValidationExceptions("TmkmElementType-required");
				 else if("PBU".equals(newentTlTaskMappingTopicmst.getTmtmElementType()))
					 throw new ValidationExceptions("TmkmElementType-required");
				 else if("LOCN".equals(newentTlTaskMappingTopicmst.getTmtmElementType()))
					 throw new ValidationExceptions("TmkmElementType-required");
				 else if("COMP".equals(newentTlTaskMappingTopicmst.getTmtmElementType()))
					 throw new ValidationExceptions("TmkmElementType-required");
				validations.validate(newentTlTaskMappingTopicmst, xml, validationsFor);
				if(newentTlTaskMappingTopicmst.getTaskTopicDetails() == null || newentTlTaskMappingTopicmst.getTaskTopicDetails().size()<=0 ){
					throw new ValidationExceptions("TaskTopicDetails-required,");
				}
				fillValuesMaster(newentTlTaskMappingTopicmst, existEntTlTaskMappingTopicmst);	 
				CommonMessage.debugMsg("fillvalue fill after");
				CommonMessage.debugMsg("After Fill Values");
				return entTlTaskMappingTopicmstDao.create(newentTlTaskMappingTopicmst);
			} catch (ValidationExceptions e) {
				throw new ValidationExceptions(e.getMessage());
			}
	}

	private EntTlUniqpostopicLinkmst fillValuesMaster(EntTlUniqpostopicLinkmst newentTlTaskMappingTopicmst,EntTlUniqpostopicLinkmst existEntTlTaskMappingTopicmst) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("In Side fill values::::::;");
		newentTlTaskMappingTopicmst.setTmtmActive("Y");

		String dateTime = CommonFunctions.dateTimeNow();

		newentTlTaskMappingTopicmst.setTmtmCreatedon(dateTime);

		newentTlTaskMappingTopicmst.setTmtmModifiedon(dateTime);
		if (newentTlTaskMappingTopicmst.getTmtmRoleKeyid() == null)
			newentTlTaskMappingTopicmst.setTmtmRoleKeyid("{}");
		
		if (newentTlTaskMappingTopicmst.getTmtmTopiKeyid() == null)
			newentTlTaskMappingTopicmst.setTmtmTopiKeyid("{}");
		
		if (newentTlTaskMappingTopicmst.getTmtmFlid() == null)
			newentTlTaskMappingTopicmst.setTmtmFlid("{}");
		
		if (newentTlTaskMappingTopicmst.getTmtmKsa() == null)
			newentTlTaskMappingTopicmst.setTmtmKsa("{}");
		
		if (newentTlTaskMappingTopicmst.getTmtmSkrmKeyid() == null)
			newentTlTaskMappingTopicmst.setTmtmSkrmKeyid("{}");	
		
		if (newentTlTaskMappingTopicmst.getTmtmCutoffmark() == null)
			newentTlTaskMappingTopicmst.setTmtmCutoffmark("{}");
		
		if (newentTlTaskMappingTopicmst.getTmtmElementId() == null)
			newentTlTaskMappingTopicmst.setTmtmElementId("-");
		if (newentTlTaskMappingTopicmst.getTmtmTempfield2() == null)
			newentTlTaskMappingTopicmst.setTmtmTempfield2("-");
		if (newentTlTaskMappingTopicmst.getTmtmTempfield3() == null)
			newentTlTaskMappingTopicmst.setTmtmTempfield3("-");
		if (newentTlTaskMappingTopicmst.getTmtmTempfield4() == null)
			newentTlTaskMappingTopicmst.setTmtmTempfield4("-");
		if (newentTlTaskMappingTopicmst.getTmtmTempfield5() == null)
			newentTlTaskMappingTopicmst.setTmtmTempfield5("-");
		if (newentTlTaskMappingTopicmst.getTmtmTempfield6() == null)
			newentTlTaskMappingTopicmst.setTmtmTempfield6("-");
	
	
		newentTlTaskMappingTopicmst.setTaskTopicDetails(fillValuesTasKTopicdtl(newentTlTaskMappingTopicmst,existEntTlTaskMappingTopicmst));
		//newentTlSkillReviewpointmst.setReviewPointDetails(fillValuesEntTldtl(newentTlSkillReviewpointmst,existEntTlSkillReviewpointmst));	
		return newentTlTaskMappingTopicmst;
		
	}
	private List<EntTlUniqpostopicLinkdtl> fillValuesTasKTopicdtl(EntTlUniqpostopicLinkmst newentTlTaskMappingTopicmst,EntTlUniqpostopicLinkmst existEntTlTaskMappingTopicmst) {
	
		CommonMessage.debugMsg("Detail 1");
		String dateTime = CommonFunctions.dateTimeNow();
		List<EntTlUniqpostopicLinkdtl> newEntTlTaskMappingTopicdtls = newentTlTaskMappingTopicmst.getTaskTopicDetails();
		List<EntTlUniqpostopicLinkdtl> exitEntTlTaskMappingTopicdtls = null;
		EntTlUniqpostopicLinkdtl exitEntTlTaskMappingTopicdtl = null;
		CommonMessage.debugMsg("Detail 2");
		if (existEntTlTaskMappingTopicmst != null) {
			exitEntTlTaskMappingTopicdtls = existEntTlTaskMappingTopicmst.getTaskTopicDetails();
			if (exitEntTlTaskMappingTopicdtls != null && exitEntTlTaskMappingTopicdtls.size() > 0)
				exitEntTlTaskMappingTopicdtl = exitEntTlTaskMappingTopicdtls.get(0);
		}

		List<EntTlUniqpostopicLinkdtl> newEntTlTaskMappingTopicList = new ArrayList<EntTlUniqpostopicLinkdtl>();
		for (EntTlUniqpostopicLinkdtl entMappingTopicdtl : newEntTlTaskMappingTopicdtls)

		{
			CommonMessage.debugMsg(" for loop Deatils fill values::::::");
			CommonMessage.debugMsg("Detail");
			entMappingTopicdtl.setTmtdCreatedon(dateTime);
			CommonMessage.debugMsg(" for loop Deatils fill values::::::  3");
			entMappingTopicdtl.setTmtdModifiedon(dateTime);

			CommonMessage.debugMsg(" for loop Deatils fill values::::::  3");
			entMappingTopicdtl.setTmtdActive("Y");
			entMappingTopicdtl.setTmtdCreatedby(newentTlTaskMappingTopicmst.getTmtmCreatedby());

			CommonMessage.debugMsg(" for loop Deatils fill values::::::  4");

			/*
			 * if(employeeBean.getEmpdBirthdate()==null)
			 * genTlEmployeedtl.setEmpdBirthdate(Constants.passNullDate);
			 */
			CommonMessage.debugMsg(" for loop Deatils fill values::::::  5");

			if (entMappingTopicdtl.getTmtdTmkmKeyid() == null)
				entMappingTopicdtl.setTmtdTmkmKeyid("{}");

			if (entMappingTopicdtl.getTmtdTmtmKeyid() == null)
				entMappingTopicdtl.setTmtdTmtmKeyid("{}");
			


			if (entMappingTopicdtl.getTmtdTempfield1() == null)
				entMappingTopicdtl.setTmtdTempfield1("-");

			if (entMappingTopicdtl.getTmtdTempfield2() == null)
				entMappingTopicdtl.setTmtdTempfield2("-");

			if (entMappingTopicdtl.getTmtdTempfield3() == null)
				entMappingTopicdtl.setTmtdTempfield3("-");

			if (entMappingTopicdtl.getTmtdTempfield4() == null)
				entMappingTopicdtl.setTmtdTempfield4("-");

			if (entMappingTopicdtl.getTmtdTempfield5() == null)
				entMappingTopicdtl.setTmtdTempfield5("-");
			

			if (entMappingTopicdtl.getTmtdTempfield6() == null)
				entMappingTopicdtl.setTmtdTempfield6("-");
			

			if (entMappingTopicdtl.getTmtdTempfield7() == null)
				entMappingTopicdtl.setTmtdTempfield7("-");

			newEntTlTaskMappingTopicList.add(entMappingTopicdtl);
		}
		CommonMessage.debugMsg("End Of  fillValues genTlMomdtl ");

		return newEntTlTaskMappingTopicList;
	}
	@Override
	public EntTlUniqpostopicLinkmst update(EntTlUniqpostopicLinkmst newentTlTaskMappingTopicmst,EntTlUniqpostopicLinkmst existEntTlTaskMappingTopicmst) throws Exception {
		// TODO Auto-generated method stub
		  CommonMessage.debugMsg("Create Block:::");
			try {
				CommonMessage.debugMsg("Inside Create");
				String validationsFor = "create";
				String xml = "SkillIndexRp";
				CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
				CommonMessage.debugMsg("After Fill Values.............."+ newentTlTaskMappingTopicmst.getTmtmFlid());
				validations.validate(newentTlTaskMappingTopicmst, xml, validationsFor);
				if(newentTlTaskMappingTopicmst.getTaskTopicDetails() == null /*|| newentTlTaskMappingTopicmst.getTaskTopicDetails().size()<=0 */){
				throw new ValidationExceptions("TaskTopicDetails-required,");
				}
				fillValuesMaster(newentTlTaskMappingTopicmst, existEntTlTaskMappingTopicmst);	 
				CommonMessage.debugMsg("fillvalue fill after");
				CommonMessage.debugMsg("After Fill Values");
				return entTlTaskMappingTopicmstDao.update(newentTlTaskMappingTopicmst);
			} catch (ValidationExceptions e) {
				throw new ValidationExceptions(e.getMessage());
			}
	}
	@Override
	public List<String[]> getTaskMainGrid(CommonFilter commonFilter) throws Exception {
		return this.entTlTaskMappingTopicmstDao.getTaskMainGrid(commonFilter);
	}
	@Override
	public EntTlUniqpostopicLinkmst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		return this.entTlTaskMappingTopicmstDao.select(mstkeyid);
	}
	@Override
	public List<String[]> getTopicDetail(CommonFilter commonFilter,String flid, String uniquePostion,String mstkeyid,String createmode,String topic) {
		// TODO Auto-generated method stub
		return this.entTlTaskMappingTopicmstDao.getTopicDetail(commonFilter,flid,uniquePostion,mstkeyid,createmode,topic);
	}
	@Override
	public Workbook TaskTopicExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return this.entTlTaskMappingTopicmstDao.TaskTopicExcel(commonFilter, colmodel,format);
	}
	@Override
	public EntTlUniqpostopicLinkmst delete(EntTlUniqpostopicLinkmst newEntTlTaskMappingTopicmst) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlTaskMappingTopicmstDao.delete(newEntTlTaskMappingTopicmst);
	}
	@Override
	public void DeleteTasklist(String keyid) throws BusinessApplicationExceptions, Exception {
		this.entTlTaskMappingTopicmstDao.DeleteTasklist(keyid);
		
	}

}
