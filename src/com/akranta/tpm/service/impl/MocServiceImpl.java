package com.akranta.tpm.service.impl;


import java.io.File;
import com.akranta.tpm.utils.CommonMessage ;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.xml.bind.ValidationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.MocDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.MocDaoImpl;
import com.akranta.tpm.dao.sql.HazopMstSql;
import com.akranta.tpm.dao.sql.KznTlKaizenbankmstSql;
import com.akranta.tpm.dao.sql.MocPssrmstSql;
import com.akranta.tpm.dao.sql.MocRfcmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.dao.sql.WhatifMstSql;
import com.akranta.tpm.exportreport.MOCExcelTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.HazopDtl;
import com.akranta.tpm.model.HazopMst;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.model.MOCPssrReccommend;
import com.akranta.tpm.model.MOCReccommendation;
import com.akranta.tpm.model.MocClosure;
import com.akranta.tpm.model.MocPssrdtl;
import com.akranta.tpm.model.MocPssrmst;
import com.akranta.tpm.model.MocRfQuestions;
import com.akranta.tpm.model.MocRfcBasismst;
import com.akranta.tpm.model.MocRfcmst;
import com.akranta.tpm.model.MocTeamConfigmst;
//import com.akranta.tpm.model.StpTlServicerequestmst;
import com.akranta.tpm.model.WhatifDtl;
import com.akranta.tpm.model.WhatifMst;
import com.akranta.tpm.service.MocService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Mail;
import com.akranta.tpm.utils.NotesMailClient;
import com.akranta.tpm.utils.Validations;
public class MocServiceImpl implements  MocService{
	DBActionTemplate  dbActionTemplate ;
	MocDao mocDao;
	NotesMailClient notesMail;
	CommonFilterDao commonFilterDao;
	private Validations validations ;
	public MocServiceImpl(DBActionTemplate dbActionTemplate){
		 this.dbActionTemplate  =dbActionTemplate;
		 mocDao=new MocDaoImpl(dbActionTemplate);
		commonFilterDao=new CommonFilterDaoImpl(dbActionTemplate);
		notesMail=new NotesMailClient();
}
	@Override
public List<String[]> getQuestionaire(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getQuestionaire(commonFilter);
}
@Override
public List<String[]> getPSSR(String unsafeact) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getPSSR(unsafeact);
}
@Override
public List<String[]> getCRBasis(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("In Service Impl");
	return mocDao.getCRBasis(commonFilter);
}

@Override
public String getProbablityVal(String prob) throws Exception {
	
	return  mocDao.getProbablityVal(prob);
}


@Override
public String getSeviorityVal(String sev) throws Exception {

	return  mocDao.getSeviorityVal(sev);
}

@Override
public String getRiskLevel(String riskVal)
		throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getRiskLevel(riskVal);
}
@Override
public List<String[]> getBasisofChange(String MocKeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getBasisofChange(MocKeyid);
}
@Override
public List<String[]> getMOCTeam(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getMOCTeam(commonFilter);
}
@Override
public List<String[]> getMOCClosure(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getMOCClosure(commonFilter);
}
@Override
public List<String[]> getMocRelatedData(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getMocRelatedData(commonFilter);
}
public Workbook getMOCModificationExcel(JSONObject colmodel, String format,
		CommonFilter commonFilter) throws  Exception {
	// TODO Auto-generated method stub
	return mocDao.getMOCModificationExcel(colmodel,format, commonFilter);
	}
@Override
public List<ComboBox> getMocTypeComboList(ComboFilter combofilter) throws Exception {
	// TODO Auto-generated method stub
CommonMessage.debugMsg("In Type Service Impl");
        String Tablename="MOC_TL_MOCTYPE";
        CommonMessage.debugMsg("Table"+Tablename);
		combofilter.setIdField("MOC_TYP_KEYID");
		combofilter.setNameField("MOC_TYP_NAME");
		combofilter.setCodeField("MOC_TYP_CODE");
		combofilter.setTableName(Tablename);
		return commonFilterDao.fillComboValues(combofilter);
	
}
@Override
public List<ComboBox> getMocNatureComboList(ComboFilter combofilter) throws Exception {
	// TODO Auto-generated method stub
	String TableName="MOC_TL_MOCNATURE";
	CommonMessage.debugMsg("In Nature Service Impl"+TableName);
	combofilter.setIdField("MOC_NAT_KEYID");
	combofilter.setNameField("MOC_NAT_NAME");
	combofilter.setCodeField("MOC_NAT_CODE");
	combofilter.setTableName(TableName);
	return commonFilterDao.fillComboValues(combofilter);
}
@Override
public MocRfcmst createMOC(MocRfcmst mocRfcmst, MocRfcmst existMocRfcmst,MocRfcBasismst mocRfcBasismst,MocTeamConfigmst mocTeamConfigmst)
		throws Exception {
	try {
		CommonMessage.debugMsg("serviceimpl 1");

		CommonMessage.debugMsg("serviceimpl 2");
		fillValues(mocRfcmst,existMocRfcmst);
		return this.mocDao.createMOC(mocRfcmst,mocRfcBasismst,mocTeamConfigmst);
	}catch (ValidationExceptions e){
		CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
		throw new ValidationExceptions(e.getMessage());
	}
}
@Override
public List<MocRfcBasismst> createMOCBasis(List<MocRfcBasismst> mocRfcBasismst, String createdBy, MocRfcmst mocRfcmst)
		throws Exception {

		// TODO Auto-generated method stub
		CommonMessage.debugMsg("In Service Impl");
		 return mocDao.createBasis(fillValuesMOCBasis(mocRfcBasismst),createdBy,mocRfcmst); 
	
}
private List<MocRfcBasismst> fillValuesMOCBasis(List<MocRfcBasismst> mocRfcBasismst) {
	// TODO Auto-generated method stub
	List<MocRfcBasismst> newDescLinks =mocRfcBasismst;
	List<MocRfcBasismst> newLinkList = new ArrayList<MocRfcBasismst>();
	int index=0;
	if(mocRfcBasismst!=null)
	for( MocRfcBasismst genDescriptionLink : newDescLinks)
	{	
		String dateTime = CommonFunctions.dateTimeNow();
		MocRfcBasismst getDescLink1 = newDescLinks.get(index);
		index++;
		genDescriptionLink.setRfcbActive("Y");
		genDescriptionLink.setRfcbModifiedon(dateTime);
		genDescriptionLink.setRfcbCreatedon(dateTime);
        		
		/*if(!UIUtils.isValidKeyId(getDescLink1.getFodcDesciption())){
			genDescriptionLink.setFodcDesciption("{}");
			 }*/
		if( genDescriptionLink.getRfcbrfcid() == null )
			genDescriptionLink.setRfcbrfcid("{}");
		if( genDescriptionLink.getRfcbCreatedby() == null )
			genDescriptionLink.setRfcbCreatedby("{}");
		
		if( genDescriptionLink.getRfcbbasisid() == null )
			genDescriptionLink.setRfcbbasisid("");
		if( genDescriptionLink.getRfcbTempfield1() == null )
			genDescriptionLink.setRfcbTempfield1("-");
		if( genDescriptionLink.getRfcbTempfield2() == null )
			genDescriptionLink.setRfcbTempfield2("-");
		if( genDescriptionLink.getRfcbTempfield3() == null )
			genDescriptionLink.setRfcbTempfield3("-");
		if( genDescriptionLink.getRfcbTempfield4() == null )
			genDescriptionLink.setRfcbTempfield4("-");
		if( genDescriptionLink.getRfcbTempfield5() == null )
			genDescriptionLink.setRfcbTempfield5("-");
		
newLinkList.add(genDescriptionLink);
}
return newLinkList;
}
@Override
public MocRfcmst updateMOC(MocRfcmst mocRfcmst, MocRfcmst existMocRfcmst,String MocId,String Nature ,String Desc,String Detail, String Dmt,String Jh,String Initiator,String MOCTitle,String MocType)
		throws Exception {
	CommonMessage.debugMsg("Inside Service Impl Update");
/*	CommonFunctions.debugMsg("Inside Create");
	String validationsFor = "update";
	String xmlName="NewNearmiss";*/
	
	//fillValues(mocRfcmst,existMocRfcmst);
	return mocDao.update(mocRfcmst,MocId,Nature,Desc,Detail,Dmt,Jh,Initiator,MOCTitle,MocType);
}
@Override
public List<MocRfcBasismst> updateMOCBasis(List<MocRfcBasismst> mocRfcBasismst, String createdBy, MocRfcmst mocRfcmst)
		throws Exception {
	// TODO Auto-generated method stub
	return null;
}
private MocRfcmst fillValues(MocRfcmst mocRfcmst, MocRfcmst existMocRfcmst) {
	// TODO Auto-generated method stub
	
	CommonMessage.debugMsg("Inside FillValueees1");
	String dateTime = CommonFunctions.dateTimeNow();
	mocRfcmst.setRfcmActive("Y");
	mocRfcmst.setRfcmModifiedon(dateTime);
	mocRfcmst.setRfcmCreatedon(dateTime);
	
	
	if( mocRfcmst.getRfcmkaizenid() == null )
		mocRfcmst.setRfcmkaizenid("{}");
	
	
	if( mocRfcmst.getRfcmsuggestionid() == null )
		mocRfcmst.setRfcmsuggestionid("{}");

	if( mocRfcmst.getRfcmflid() == null )
		mocRfcmst.setRfcmflid("{}");

	if( mocRfcmst.getRfcmdmtid() == null )
		mocRfcmst.setRfcmdmtid("{}");
	if( mocRfcmst.getRfcmjhid() == null )
		mocRfcmst.setRfcmjhid("{}");
    if( mocRfcmst.getRfcmdate() == null )
		mocRfcmst.setRfcmdate(dateTime);
	
    if( mocRfcmst.getRfcmempid() == null )
		mocRfcmst.setRfcmempid("{}");
	if( mocRfcmst.getRfcmtitle() == null )
		mocRfcmst.setRfcmtitle("{}");
	if( mocRfcmst.getRfcmtype() == null )
		mocRfcmst.setRfcmtype("{}");
	if( mocRfcmst.getRfcmnature() == null )
		mocRfcmst.setRfcmnature("{}");
	if( mocRfcmst.getRfcmdetail() == null )
		mocRfcmst.setRfcmdetail("{}");
	if( mocRfcmst.getRfcmdescription() == null )
		mocRfcmst.setRfcmdescription("{}");
	if( mocRfcmst.getRfcmemergencyno() == null )
		mocRfcmst.setRfcmemergencyno("{}");
	
	if( mocRfcmst.getRfcmdetailimg() == null )
		mocRfcmst.setRfcmdetailimg("{}");
	if( mocRfcmst.getRfcmdescimg() == null )
		mocRfcmst.setRfcmdescimg("{}");
	if( mocRfcmst.getRfcmstatus() == null )
		mocRfcmst.setRfcmstatus("{}");
	if( mocRfcmst.getRfcmTempfield1() == null )
		mocRfcmst.setRfcmTempfield1("{}");
	if( mocRfcmst.getRfcmTempfield2() == null )
		mocRfcmst.setRfcmTempfield2("{}");
	if( mocRfcmst.getRfcmTempfield3() == null )
		mocRfcmst.setRfcmTempfield3("{}");
	if( mocRfcmst.getRfcmTempfield4() == null )
		mocRfcmst.setRfcmTempfield4("{}");
	if( mocRfcmst.getRfcmTempfield5() == null )
		mocRfcmst.setRfcmTempfield5("{}");
	if( mocRfcmst.getRfcmTempfield6() == null )
		mocRfcmst.setRfcmTempfield6("{}");
	if( mocRfcmst.getRfcmTempfield7() == null )
		mocRfcmst.setRfcmTempfield7("{}");
	if( mocRfcmst.getRfcmTempfield8() == null )
		mocRfcmst.setRfcmTempfield8("{}");
	if( mocRfcmst.getRfcmTempfield9() == null )
		mocRfcmst.setRfcmTempfield9("{}");
	if( mocRfcmst.getRfcmTempfield10() == null )
		mocRfcmst.setRfcmTempfield10("{}");

	
		 return mocRfcmst;
	
}



@Override
public List<MocTeamConfigmst> createMOCTeam(List<MocTeamConfigmst> mocTeamConfigmst, String createdBy,
		MocRfcmst mocRfcmst) throws Exception {
	
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("In Service Impl Team Config");
	 return mocDao.createTeam(fillValuesMOCTeam(mocTeamConfigmst),createdBy,mocRfcmst); 


}
private List<MocTeamConfigmst> fillValuesMOCTeam(List<MocTeamConfigmst> mocTeamConfigmst) {
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("In Fill Val MOC team");
	List<MocTeamConfigmst> newDescLinks =mocTeamConfigmst;
	List<MocTeamConfigmst> newLinkList = new ArrayList<MocTeamConfigmst>();
	
	int index=0;
	if(mocTeamConfigmst!=null)
	for( MocTeamConfigmst teamLink : newDescLinks)
	{	
		CommonMessage.debugMsg("In FMCT");
		String dateTime = CommonFunctions.dateTimeNow();
		MocTeamConfigmst getDescLink1 = newDescLinks.get(index);
		index++;
		teamLink.setMctcActive("Y");
		teamLink.setMctcModifiedon(dateTime);
		teamLink.setMctcCreatedon(dateTime);
      /*  		
		if(!UIUtils.isValidKeyId(getDescLink1.getFodcDesciption())){
			genDescriptionLink.setFodcDesciption("{}");
			 }*/
		if( teamLink.getMctcmasterid() == null )
			teamLink.setMctcmasterid("{}");
		
		if( teamLink.getMctcroleid() == null )
			teamLink.setMctcroleid("{}");
		if( teamLink.getMctcgroupno() == null )
			teamLink.setMctcgroupno("1");
		
		if( teamLink.getMctcInitial() == null )
			teamLink.setMctcInitial("{}");
		
		if( teamLink.getMctcHazop() == null )
			teamLink.setMctcHazop("-");
		if( teamLink.getMctcfinal() == null )
			teamLink.setMctcfinal("-");
		if( teamLink.getMctcempid() == null )
			teamLink.setMctcempid("-");
		if( teamLink.getMctcInitialYN() == null )
			teamLink.setMctcInitialYN("-");
		if( teamLink.getMctcInaprovedby() == null )
			teamLink.setMctcInaprovedby("{}");
		if( teamLink.getMctcInaprovedDte() == null )
			teamLink.setMctcInaprovedDte("");
		if( teamLink.getMctcInaprovedStatus() == null )
			teamLink.setMctcInaprovedStatus("-");
		if( teamLink.getMctcInaprovedRem() == null )
			teamLink.setMctcInaprovedRem("{}");
		if( teamLink.getMctcInaproverwkflg() == null )
			teamLink.setMctcInaproverwkflg("-");
		if( teamLink.getMctcInaproverwkdte() == null )
			teamLink.setMctcInaproverwkdte("");
		if( teamLink.getMctcInaproverwkrem() == null )
			teamLink.setMctcInaproverwkrem("{}");
	    if( teamLink.getMctcTempfield1() == null )
			teamLink.setMctcTempfield1("-");
		if( teamLink.getMctcTempfield2() == null )
			teamLink.setMctcTempfield2("-");
		if( teamLink.getMctcTempfield3() == null )
			teamLink.setMctcTempfield3("-");
		
		if( teamLink.getMctcHazopYN() == null )
			teamLink.setMctcHazopYN("-");
		if( teamLink.getMctcHzaprovedby() == null )
			teamLink.setMctcHzaprovedby("{}");
		if( teamLink.getMctcHzaprovedDte() == null )
			teamLink.setMctcHzaprovedDte("");
		if( teamLink.getMctcHzaprovedStatus() == null )
			teamLink.setMctcHzaprovedStatus("-");
		if( teamLink.getMctcHzaprovedRem() == null )
			teamLink.setMctcHzaprovedRem("{}");
		if( teamLink.getMctcHzaproverwkflg() == null )
			teamLink.setMctcHzaproverwkflg("-");
		if( teamLink.getMctcHzaproverwkdte() == null )
			teamLink.setMctcHzaproverwkdte("");
		if( teamLink.getMctcHzaproverwkrem() == null )
			teamLink.setMctcHzaproverwkrem("{}");
	    if( teamLink.getMctcTempfield4() == null )
			teamLink.setMctcTempfield4("-");
		if( teamLink.getMctcTempfield5() == null )
			teamLink.setMctcTempfield5("-");
		if( teamLink.getMctcTempfield6() == null )
			teamLink.setMctcTempfield6("-");
		
		if( teamLink.getMctcFinalYN() == null )
			teamLink.setMctcFinalYN("-");
		if( teamLink.getMctcFaaprovedby() == null )
			teamLink.setMctcFaaprovedby("{}");
		if( teamLink.getMctcFaaprovedDte() == null )
			teamLink.setMctcFaaprovedDte("");
		if( teamLink.getMctcFaaprovedStatus() == null )
			teamLink.setMctcFaaprovedStatus("-");
		if( teamLink.getMctcFaaprovedRem() == null )
			teamLink.setMctcFaaprovedRem("{}");
		if( teamLink.getMctcFaaproverwkflg() == null )
			teamLink.setMctcFaaproverwkflg("-");
		if( teamLink.getMctcFaaproverwkdte() == null )
			teamLink.setMctcFaaproverwkdte("");
		if( teamLink.getMctcFaaproverwkrem() == null )
			teamLink.setMctcFaaproverwkrem("{}");
	    if( teamLink.getMctcTempfield7() == null )
			teamLink.setMctcTempfield7("-");
		if( teamLink.getMctcTempfield8() == null )
			teamLink.setMctcTempfield8("-");
		if( teamLink.getMctcTempfield9() == null )
			teamLink.setMctcTempfield9("-");
		
	if( teamLink.getMctcTempfield10()  == null )
			teamLink.setMctcTempfield10("-");
	
		
newLinkList.add(teamLink);
}
	return newLinkList;
}
@Override
public MocRfcmst getGridMocData(String mocKeyid) throws Exception {

	MocRfcmst mocRfcmst=new MocRfcmst();
	MocRfcmstSql mocRfcmstSql=new MocRfcmstSql();
	String sql="SELECT * FROM MOC_TL_RFCMST WHERE MOC_RFC_KEYID=?";
	CommonMessage.debugMsg("sql:::"+sql);
	Object args[]=new Object[]{mocKeyid};
	mocRfcmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	
	return mocRfcmst;
}
@Override
public List<MocRfQuestions> createMocRfQuestions(List<MocRfQuestions> employeeAddList,String MocKeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.createQuestionsNew(fillValuesSessionEmployee(employeeAddList,MocKeyid),MocKeyid); 
}
private List<MocRfQuestions> fillValuesSessionEmployee(List<MocRfQuestions> employeeAddList,String MocKeyid) {
	
	// TODO Auto-generated method stub
	List<MocRfQuestions> newEntTlSessionEmployeeLinks =employeeAddList;
		List<MocRfQuestions> newEntTlBatchEmployeeLinkList = new ArrayList<MocRfQuestions>();
		int index=0;
		if(employeeAddList!=null)
		for( MocRfQuestions genEntTlSessionEmployeeLink : newEntTlSessionEmployeeLinks)
		{	
			String dateTime = CommonFunctions.dateTimeNow();
			MocRfQuestions genEntTlBatchEmployeeLink1 = newEntTlSessionEmployeeLinks.get(index);
			index++;
			genEntTlSessionEmployeeLink.setRfcqActive("Y");
			genEntTlSessionEmployeeLink.setRfcqCreatedon(dateTime);
			genEntTlSessionEmployeeLink.setRfcqrfcid(MocKeyid);
		//	genEntTlSessionEmployeeLink.setEtceCreatedby("EMPOOO1");
			genEntTlSessionEmployeeLink.setRfcqModifiedon(dateTime);
			
			
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfcqrfcid())){
				genEntTlSessionEmployeeLink.setRfcqrfcid("{}");
			 }
			
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfcqquestionaireid())){
				genEntTlSessionEmployeeLink.setRfcqquestionaireid("{}");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfcqsortorder())){
				genEntTlSessionEmployeeLink.setRfcqsortorder("1");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfcqresponse())){
				genEntTlSessionEmployeeLink.setRfcqresponse("-");
			 }
			
			
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfcqTempfield1())){
				genEntTlSessionEmployeeLink.setRfcqTempfield1("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfcqTempfield2())){
				genEntTlSessionEmployeeLink.setRfcqTempfield2("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfcqTempfield3())){
				genEntTlSessionEmployeeLink.setRfcqTempfield3("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfcqTempfield4())){
				genEntTlSessionEmployeeLink.setRfcqTempfield4("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfcqTempfield5())){
				genEntTlSessionEmployeeLink.setRfcqTempfield5("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfcqCreatedby())){
				genEntTlSessionEmployeeLink.setRfcqCreatedby("{}");
			 }
			newEntTlBatchEmployeeLinkList.add(genEntTlSessionEmployeeLink);
	}
	return newEntTlBatchEmployeeLinkList;
}
@Override
public List<MocRfcBasismst> createBasis(List<MocRfcBasismst> employeeAddList, String mocKeyid) throws Exception {
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("In Service Impl");
	 return mocDao.createBasis(fillValuesMOCBasis(employeeAddList),mocKeyid); 
}
@Override
public List<String[]> getApprovalList(String mocKeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getApprovalList(mocKeyid);
}
@Override
public List<String[]> getMOCTeamSuccess(CommonFilter commonFilter, String mocKeyId) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getMOCTeamSuccess(commonFilter,mocKeyId);
}


@Override
public List<ComboBox> getMocGuideWordComboList(ComboFilter combofilter) throws Exception {
	// TODO Auto-generated method stub
	String Table="MOC_TL_GUIDEWORDMST";
	combofilter.setIdField("MGWM_KEYID");
	combofilter.setNameField("MGWM_NAME");
	combofilter.setTableName(Table);
	return commonFilterDao.fillComboValues(combofilter);
}
/*@Override
public List<String[]> getInitialApproval(String keyid, String empId, String status, String date, String remarks)
		throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getInitialApproval(keyid,empId,status,date,remarks);
}*/
@Override
public MocTeamConfigmst getInitialApproval(MocTeamConfigmst mocTeamConfigmst, MocTeamConfigmst existMocTeamConfigmst,
		String keyid, String empId, String status, String date, String remarks,String MocKeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getInitialApproval(mocTeamConfigmst,existMocTeamConfigmst,keyid,empId,status,date,remarks,MocKeyid);
}
@Override
public List<String[]> getHazopApprovalList(String mocKeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getHazopApprovalList(mocKeyid);
}
@Override
public List<String[]> getFinalApprovalList(String mocKeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getFinalApprovalList(mocKeyid);
}
@Override
public MocTeamConfigmst getFinalApproval(MocTeamConfigmst mocTeamConfigmst, MocTeamConfigmst existMocTeamConfigmst,
		String keyid, String empId, String status, String date, String remarks,String MocKeyId) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getFinalApproval(mocTeamConfigmst,existMocTeamConfigmst,keyid,empId,status,date,remarks,MocKeyId);
}
@Override
public MocTeamConfigmst getHazopApproval(MocTeamConfigmst mocTeamConfigmst, MocTeamConfigmst existMocTeamConfigmst,
		String keyid, String empId, String status, String date, String remarks,String MocKeyId) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getHazopApproval(mocTeamConfigmst,existMocTeamConfigmst,keyid,empId,status,date,remarks, MocKeyId);
}
@Override
public MocPssrmst createPssrCheckList(MocPssrmst newwMocPssrmst, MocPssrmst existMocPssrmst,String Keyid) throws Exception {
	// TODO Auto-generated method stub
	try {
		CommonMessage.debugMsg("serviceimpl 1");

		CommonMessage.debugMsg("serviceimpl 2");
		fillValuesPssr(newwMocPssrmst,existMocPssrmst,Keyid);
		return this.mocDao.createPssrCheckListC(newwMocPssrmst,existMocPssrmst,Keyid); 
	}catch (ValidationExceptions e){
		CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
		throw new ValidationExceptions(e.getMessage());
	}
}
private MocPssrmst fillValuesPssr(MocPssrmst newwMocPssrmst, MocPssrmst existMocPssrmst,String Keyid) {

	CommonMessage.debugMsg("Inside FillValueees1");
	String dateTime = CommonFunctions.dateTimeNow();
	newwMocPssrmst.setPsrmActive("Y");
	newwMocPssrmst.setPsrmModifiedon(dateTime);
	newwMocPssrmst.setPsrmCreatedon(dateTime);
	newwMocPssrmst.setPsrmrfcid(Keyid);
	
	if( newwMocPssrmst.getPsrmrfcid() == null )
		newwMocPssrmst.setPsrmrfcid("{}");
	
	
	if( newwMocPssrmst.getPsrmprocess() == null )
		newwMocPssrmst.setPsrmprocess("{}");

	if( newwMocPssrmst.getPsrmdate() == null )
		newwMocPssrmst.setPsrmdate(dateTime);

	if( newwMocPssrmst.getPsrmmocdetail() == null )
		newwMocPssrmst.setPsrmmocdetail("{}");
	
	if( newwMocPssrmst.getPsrmTempfield1() == null )
		newwMocPssrmst.setPsrmTempfield1("{}");
	if( newwMocPssrmst.getPsrmTempfield2() == null )
		newwMocPssrmst.setPsrmTempfield2("{}");
	if( newwMocPssrmst.getPsrmTempfield3() == null )
		newwMocPssrmst.setPsrmTempfield3("{}");
	if( newwMocPssrmst.getPsrmTempfield4() == null )
		newwMocPssrmst.setPsrmTempfield4("{}");
	if( newwMocPssrmst.getPsrmTempfield5() == null )
		newwMocPssrmst.setPsrmTempfield5("{}");
	//newwMocPssrmst.setPssrDetails(fillValuesPssrDetails(newwMocPssrmst));
    return newwMocPssrmst;

}

@Override
public List<ComboBox> getSeviorityComboList(ComboFilter sivFilterComboFilter)throws Exception {
	// TODO Auto-generated method stub
	sivFilterComboFilter.setNameField("SIVM_CODE");
	sivFilterComboFilter.setIdField("SIVM_CODE");
	sivFilterComboFilter.setTableName(TableNames.TBL_SHE_TL_SEVIORITYMST);
	return commonFilterDao.fillComboValues(sivFilterComboFilter);
}

@Override
public List<ComboBox> getRiskLevelComboList(ComboFilter riskLevelFilterComboFilter) throws Exception {
	// TODO Auto-generated method stub
	riskLevelFilterComboFilter.setNameField("PRBM_CODE");
	riskLevelFilterComboFilter.setIdField("PRBM_CODE");
	riskLevelFilterComboFilter.setTableName(TableNames.TBL_SHE_TL_PROBABLITYMST);
	return commonFilterDao.fillComboValues(riskLevelFilterComboFilter);
}
@Override
public List<ComboBox> getProbablityComboList(
		ComboFilter probablityComboFilter) throws Exception {
	// TODO Auto-generated method stub
	probablityComboFilter.setNameField("PRBM_CODE");
	probablityComboFilter.setIdField("PRBM_CODE");
	probablityComboFilter.setTableName(TableNames.TBL_SHE_TL_PROBABLITYMST);
	return commonFilterDao.fillComboValues(probablityComboFilter);
}


public HazopMst createHazop(HazopMst newHazopMst,HazopMst existHazopMst,String Keyid) throws BusinessApplicationExceptions,ValidationException,Exception{

	newHazopMst=fillHazopval(newHazopMst,existHazopMst);
	return mocDao.createHazop(newHazopMst,Keyid);
	}
public HazopMst UpdateHazop(HazopMst newHazopMst,HazopMst existHazopMst, String HazopKeyId,String CreatedBy) throws Exception{
	newHazopMst=fillHazopval(newHazopMst,existHazopMst);
	return mocDao.UpdateHazop(newHazopMst,HazopKeyId);
}
public void DeleteHazopRow(String keyid) throws Exception 
{
	this.mocDao.DeleteHazopRow(keyid);

}
public  HazopMst fillHazopval(HazopMst newHazopmst,HazopMst existHazopMst){
	String dateTime=CommonFunctions.dateTimeNow();
	newHazopmst.setHzomActive("Y");
	newHazopmst.setHzomCreatedon(dateTime);
	newHazopmst.setHzomModifiedon(dateTime);
	if(newHazopmst.getHzomDate()==null){
		newHazopmst.setHzomDate(dateTime);
	}
	if(newHazopmst.getHzomDesignintent()==null){
		newHazopmst.setHzomDesignintent("-");
	}
	if(newHazopmst.getHzomFacility()==null){
		newHazopmst.setHzomFacility("-");
	}
	if(newHazopmst.getHzomKzbnKeyid()==null){
		newHazopmst.setHzomKzbnKeyid("-");
	}
	if(newHazopmst.getHzomMocmKeyid()==null){
		newHazopmst.setHzomMocmKeyid("-");
	}
	if(newHazopmst.getHzomNode()==null){
		newHazopmst.setHzomNode("-");
	}
	if(newHazopmst.getHzomPidno()==null){
		newHazopmst.setHzomPidno("-");
	}
	if(newHazopmst.getHzomTeam()==null){
		newHazopmst.setHzomTeam("-");
	}
	if(newHazopmst.getHzomFlid()==null){
		newHazopmst.setHzomFlid("-");
	}
	if(newHazopmst.getHzomDirecthazop()==null){
		newHazopmst.setHzomDirecthazop("N");
	}
	
	if(newHazopmst.getHzomTempfield3()==null){
		newHazopmst.setHzomTempfield3("-");
	}
	if(newHazopmst.getHzomTempfield4()==null){
		newHazopmst.setHzomTempfield4("-");
	}
	if(newHazopmst.getHzomTempfield5()==null){
		newHazopmst.setHzomTempfield5("-");
	}
	newHazopmst.setHazopDetails(fillHazopDtlval(newHazopmst));
	return newHazopmst;
}
public List<HazopDtl> fillHazopDtlval(HazopMst newHazopMst){
	String dateTime = CommonFunctions.dateTimeNow();
	List<HazopDtl> hazopdtlList=newHazopMst.getHazopDetails();
		if (newHazopMst.getHazopDetails()!=null){
		for(int i=0 ;i<=hazopdtlList.size()-1;i++){	
			hazopdtlList.get(i).setMohdCreatedon(dateTime);
			hazopdtlList.get(i).setMohdModifiedon(dateTime);
			hazopdtlList.get(i).setMohdCreatedby(newHazopMst.getHzomCreatedby());
			hazopdtlList.get(i).setMohdActive("Y");
		   
			if(hazopdtlList.get(i).getMohdGuideword()==null){
				hazopdtlList.get(i).setMohdGuideword("-");
			}
			if(hazopdtlList.get(i).getMohdCauses()==null){
				hazopdtlList.get(i).setMohdCauses("-");
			}
			if(hazopdtlList.get(i).getMohdCosequeces()==null){
				hazopdtlList.get(i).setMohdCosequeces("-");
			}
			if(hazopdtlList.get(i).getMohdDeviation()==null){
				hazopdtlList.get(i).setMohdDeviation("-");
			}
			if(hazopdtlList.get(i).getMohdRowNum()==null){
				hazopdtlList.get(i).setMohdRowNum("0");
			}
			if(hazopdtlList.get(i).getMohdParameter()==null){
				hazopdtlList.get(i).setMohdParameter("-");
			}
			if(hazopdtlList.get(i).getMohdWithSafeGuards()==null){
				hazopdtlList.get(i).setMohdWithSafeGuards("-");
			}
			if(hazopdtlList.get(i).getMohdLikeHood1()==null){
				hazopdtlList.get(i).setMohdLikeHood1("1");
			}
			if(hazopdtlList.get(i).getMohdSeverity1()==null){
				hazopdtlList.get(i).setMohdSeverity1("1");
			}
			if(hazopdtlList.get(i).getMohdRisk1()==null){
				hazopdtlList.get(i).setMohdRisk1("1");
			}
			if(hazopdtlList.get(i).getMohdWithoutSafeGuards()==null){
				hazopdtlList.get(i).setMohdWithoutSafeGuards("-");
			}
			
			if(hazopdtlList.get(i).getMohdLikeHood2()==null){
				hazopdtlList.get(i).setMohdLikeHood2("1");
			}
			if(hazopdtlList.get(i).getMohdSeverity2()==null){
				hazopdtlList.get(i).setMohdSeverity2("1");
			}
			if(hazopdtlList.get(i).getMohdRisk2()==null){
				hazopdtlList.get(i).setMohdRisk2("1");
			}
			if(hazopdtlList.get(i).getMohdRemarks()==null){
				hazopdtlList.get(i).setMohdRemarks("-");
			}
			if(hazopdtlList.get(i).getMohdResponsibility()==null){
				hazopdtlList.get(i).setMohdResponsibility("-");
			}
			if(hazopdtlList.get(i).getMohdTarget()==null){
				hazopdtlList.get(i).setMohdTarget(dateTime);
			}
			if(hazopdtlList.get(i).getMohdStatus()==null){
				hazopdtlList.get(i).setMohdStatus("-");
			}
			if(hazopdtlList.get(i).getMohdTempfield4()==null){
				hazopdtlList.get(i).setMohdTempfield4("-");
			}
			if(hazopdtlList.get(i).getMohdTempfield5()==null){
				hazopdtlList.get(i).setMohdTempfield5("-");
			}
	}
	CommonFunctions.debugMsg("End Of  fillValues hazopdtlList");
	
}
	return hazopdtlList;
}
public WhatifMst createWhatif(WhatifMst newWhatifMst,WhatifMst existWhatifMst,String Keyid) throws BusinessApplicationExceptions,ValidationException,Exception{
	CommonFunctions.debugMsg("create");
//	validations.validate(newWhatifMst,"RiskAssessment","create");		
	newWhatifMst=fillWhatifval(newWhatifMst,existWhatifMst);
	return  mocDao.createWhatif(newWhatifMst,Keyid);
}
public WhatifMst UpdateWhatif(WhatifMst newWhatifMst,WhatifMst existWhatifMst,String CreatedBy,String WhatifKeyId) throws Exception{
	newWhatifMst=fillWhatifval(newWhatifMst,existWhatifMst);
	return  mocDao.UpdateWhatif(newWhatifMst,CreatedBy,WhatifKeyId);
}
public void DeleteWhatifRow(String keyid) throws Exception 
{
	this.mocDao.DeleteWhatifRow(keyid);

}
public List<String[]> getWhatIfList(CommonFilter commonFilter) throws Exception{
     return mocDao.getWhatIfList(commonFilter);
}
public List<String[]> getHazopList(CommonFilter commonFilter) throws Exception{
	return mocDao.getHazopList(commonFilter);
}
public WhatifMst fillWhatifval(WhatifMst newWhatifMst ,WhatifMst existWhatifMst){
	String dateTime=CommonFunctions.dateTimeNow();
	newWhatifMst.setWifmActive("Y");
	newWhatifMst.setWifmCreatedon(dateTime);
	newWhatifMst.setWifmModifiedon(dateTime);
	if(newWhatifMst.getWifmKeyid()==null){
		newWhatifMst.setWifmKeyid("{}");
	}
	if(newWhatifMst.getWifmMocmKeyid()==null){
		 newWhatifMst.setWifmMocmKeyid("{}");
	}
	if(newWhatifMst.getWifmKzbnKeyid()==null){
	  newWhatifMst.setWifmKzbnKeyid("{}");	
	}
	if(newWhatifMst.getWifmDate()==null){
		newWhatifMst.setWifmDate(dateTime);
	}
	if(newWhatifMst.getWifmFacility()==null){
		newWhatifMst.setWifmFacility("{}");
	}
	if(newWhatifMst.getWifmTeam()==null){
		newWhatifMst.setWifmTeam("{}");
	}
	if(newWhatifMst.getWifmTempfield1()==null){
		newWhatifMst.setWifmTempfield1("-");
	}
	if(newWhatifMst.getWifmTempfield2()==null){
		newWhatifMst.setWifmTempfield2("-");
	}
	if(newWhatifMst.getWifmTempfield3()==null){
		newWhatifMst.setWifmTempfield3("-");
	}
	if(newWhatifMst.getWifmTempfield4()==null){
		newWhatifMst.setWifmTempfield4("-");
	}
	if(newWhatifMst.getWifmTempfield5()==null){
		newWhatifMst.setWifmTempfield5("-");
	}
	newWhatifMst.setWhatifDetails(fillValuesWhatidDetails(newWhatifMst));
	return newWhatifMst;
			
}
private List<WhatifDtl> fillValuesWhatidDetails(WhatifMst newWhatifMst) {
	// TODO Auto-generated method stub
	String dateTime = CommonFunctions.dateTimeNow();
	List<WhatifDtl> whatiddtlList=newWhatifMst.getWhatifDetails();
	//CommonFunctions.debugMsg("start Of  fillValues genTlFnlnrolemapList:"+ sheTlRiskassessmentdtlList.size());
	if (newWhatifMst.getWhatifDetails()!=null){
		for(int i=0 ;i<=whatiddtlList.size()-1;i++){	
			whatiddtlList.get(i).setWifdCreatedon(dateTime);
			whatiddtlList.get(i).setWifdModifiedon(dateTime);
			whatiddtlList.get(i).setWifdCreatedby(newWhatifMst.getWifmCreatedby());
			whatiddtlList.get(i).setWifdActive("Y");
		   
			if(whatiddtlList.get(i).getWifdCauses()==null){
				whatiddtlList.get(i).setWifdCauses("{}");
			}
			if(whatiddtlList.get(i).getWifdCosequeces()==null){
				whatiddtlList.get(i).setWifdCosequeces("{}");
			}
			if(whatiddtlList.get(i).getWifdRemarks()==null){
				whatiddtlList.get(i).setWifdRemarks("{}");
			}
			if(whatiddtlList.get(i).getWifdWithoutSafeGuards()==null){
				whatiddtlList.get(i).setWifdWithSafeGuards("{}");
			}
            if(whatiddtlList.get(i).getWifdLikeHood1()==null){
            	whatiddtlList.get(i).setWifdLikeHood1("1");
            }
            if(whatiddtlList.get(i).getWifdRisk1()==null){
            	whatiddtlList.get(i).setWifdRisk1("1");
            }
            if(whatiddtlList.get(i).getWifdSeverity1()==null){
            	whatiddtlList.get(i).setWifdSeverity1("1");
            }
            if(whatiddtlList.get(i).getWifdLikeHood2()==null){
            	whatiddtlList.get(i).setWifdLikeHood2("1");
            }
            if(whatiddtlList.get(i).getWifdRisk2()==null){
            	whatiddtlList.get(i).setWifdRisk2("1");
            }
            if(whatiddtlList.get(i).getWifdSeverity2()==null){
            	whatiddtlList.get(i).setWifdSeverity2("1");
            } 
            
            if(whatiddtlList.get(i).getWifdWhatIf()==null){
            	whatiddtlList.get(i).setWifdWhatIf("{}");	
            }
           if(whatiddtlList.get(i).getWifdRowNum()==null){
        	   whatiddtlList.get(i).setWifdRowNum("0");   
           }
           
           if(whatiddtlList.get(i).getWifdTempfield1()==null){
        	   whatiddtlList.get(i).setWifdTempfield1("-");
           
           }
           if(whatiddtlList.get(i).getWifdTempfield2()==null){
        	   whatiddtlList.get(i).setWifdTempfield2("-");
           
           }
           if(whatiddtlList.get(i).getWifdTempfield3()==null){
        	   whatiddtlList.get(i).setWifdTempfield3("-");
           
           }
           if(whatiddtlList.get(i).getWifdTempfield4()==null){
        	   whatiddtlList.get(i).setWifdTempfield4("-");
           
           }
           if(whatiddtlList.get(i).getWifdTempfield5()==null){
        	   whatiddtlList.get(i).setWifdTempfield5("-");
           
           }
	}
	CommonFunctions.debugMsg("End Of  fillValues whatiddtlList");
	
}
	return whatiddtlList;
}
@Override
public List<MocClosure> createMocClosureQuestions(List<MocClosure> mocClosureList, String mocKeyidClosure)
		throws Exception {
	// TODO Auto-generated method stub
	return mocDao.createClosureQuestionsNew(fillValuesClosure(mocClosureList,mocKeyidClosure),mocKeyidClosure); 
}
private List<MocClosure> fillValuesClosure(List<MocClosure> mocClosureList, String mocKeyidClosure) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
	List<MocClosure> newEntTlSessionEmployeeLinks =mocClosureList;
		List<MocClosure> newEntTlBatchEmployeeLinkList = new ArrayList<MocClosure>();
		int index=0;
		if(mocClosureList!=null)
		for( MocClosure genEntTlSessionEmployeeLink : newEntTlSessionEmployeeLinks)
		{	
			String dateTime = CommonFunctions.dateTimeNow();
			MocClosure genEntTlBatchEmployeeLink1 = newEntTlSessionEmployeeLinks.get(index);
			index++;
			genEntTlSessionEmployeeLink.setRfccActive("Y");
			genEntTlSessionEmployeeLink.setRfccCreatedon(dateTime);
			genEntTlSessionEmployeeLink.setRfccrfcid(mocKeyidClosure);
		//	genEntTlSessionEmployeeLink.setEtceCreatedby("EMPOOO1");
			genEntTlSessionEmployeeLink.setRfccModifiedon(dateTime);
			
			
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfccrfcid())){
				genEntTlSessionEmployeeLink.setRfccrfcid("{}");
			 }
			
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfccclosureid())){
				genEntTlSessionEmployeeLink.setRfccclosureid("{}");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfccsortorder())){
				genEntTlSessionEmployeeLink.setRfccsortorder("1");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfccresponse())){
				genEntTlSessionEmployeeLink.setRfccresponse("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfccresponseNo())){
				genEntTlSessionEmployeeLink.setRfccresponseNo("-");
			 }
			
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfccTempfield1())){
				genEntTlSessionEmployeeLink.setRfccTempfield1("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfccTempfield2())){
				genEntTlSessionEmployeeLink.setRfccTempfield2("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfccTempfield3())){
				genEntTlSessionEmployeeLink.setRfccTempfield3("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfccTempfield4())){
				genEntTlSessionEmployeeLink.setRfccTempfield4("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfccTempfield5())){
				genEntTlSessionEmployeeLink.setRfccTempfield5("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getRfccCreatedby())){
				genEntTlSessionEmployeeLink.setRfccCreatedby("{}");
			 }
			newEntTlBatchEmployeeLinkList.add(genEntTlSessionEmployeeLink);
	}
	return newEntTlBatchEmployeeLinkList;
}



private List<MocPssrdtl> fillValuesPssrDetails(List<MocPssrdtl> mocPssrdtl) {
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("In Fill Val MOC team");
	List<MocPssrdtl> newDescLinks =mocPssrdtl;
	List<MocPssrdtl> newLinkList = new ArrayList<MocPssrdtl>();
	
	int index=0;
	if(mocPssrdtl!=null)
	for( MocPssrdtl mocPssrdtlList : newDescLinks)
	{	
		CommonMessage.debugMsg("In FMCT");
		String dateTime = CommonFunctions.dateTimeNow();
		MocPssrdtl getDescLink1 = newDescLinks.get(index);
		index++;
		/*teamLink.setMctcActive("Y");
		teamLink.setMctcModifiedon(dateTime);
		teamLink.setMctcCreatedon(dateTime);*/
      /*  		
		if(!UIUtils.isValidKeyId(getDescLink1.getFodcDesciption())){
			genDescriptionLink.setFodcDesciption("{}");
			 }*/
		mocPssrdtlList.setPsrdCreatedon(dateTime);
		mocPssrdtlList.setPsrdModifiedon(dateTime);
		//mocPssrdtlList.setPsrdCreatedby(mocPssrdtl.getPsrmCreatedby());
		mocPssrdtlList.setPsrdActive("Y");
	   
		if(mocPssrdtlList.getPsrdmasterid()==null){
			mocPssrdtlList.setPsrdmasterid("{}");
		}
		if(mocPssrdtlList.getPsrdMocid()==null){
			mocPssrdtlList.setPsrdMocid("{}");
		}
		if(mocPssrdtlList.getPsrdrownum()==null){
			mocPssrdtlList.setPsrdrownum("0");
		}
		if(mocPssrdtlList.getPsrdQstnid()==null){
			mocPssrdtlList.setPsrdQstnid("{}");
		}
		if(mocPssrdtlList.getPsrdobsrv()==null){
			mocPssrdtlList.setPsrdobsrv("{}");
		}
        
     
       if(mocPssrdtlList.getPsrdTempfield1()==null){
    	   mocPssrdtlList.setPsrdTempfield1("-");
       
       }
       if(mocPssrdtlList.getPsrdTempfield2()==null){
    	   mocPssrdtlList.setPsrdTempfield2("-");
       
       }
       if(mocPssrdtlList.getPsrdTempfield3()==null){
    	   mocPssrdtlList.setPsrdTempfield3("-");
       
       }
       if(mocPssrdtlList.getPsrdTempfield4()==null){
    	   mocPssrdtlList.setPsrdTempfield4("-");
       
       }
       if(mocPssrdtlList.getPsrdTempfield5()==null){
    	   mocPssrdtlList.setPsrdTempfield5("-");
       
       }
	
		
newLinkList.add(mocPssrdtlList);
}
	return newLinkList;
}
@Override
public List<MocPssrdtl> createPssrCheckListdtl(List<MocPssrdtl> mocPssrdtl, String createdBy, MocPssrmst newwMocPssrmst)
		throws Exception {
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("In Service Impl Team Config");
	 return mocDao.createPssrCheckListdtl(fillValuesPssrDetails(mocPssrdtl),createdBy,newwMocPssrmst);
}


public String getActionDKeyid(String Keyid) throws Exception{
	return mocDao.getActionDKeyid(Keyid);
}
public String getPssrKeyid(String mocKeyid) throws Exception{
	return mocDao.getPssrKeyid(mocKeyid);
}
private List<MOCPssrReccommend> fillValuesReccommend(List<MOCPssrReccommend> mocReccommendList, String mocKeyidRec) {
	// TODO Auto-generated method stub
	List<MOCPssrReccommend> newEntTlSessionEmployeeLinks =mocReccommendList;
	List<MOCPssrReccommend> newEntTlBatchEmployeeLinkList = new ArrayList<MOCPssrReccommend>();
	int index=0;
	if(mocReccommendList!=null)
	for( MOCPssrReccommend genEntTlSessionEmployeeLink : newEntTlSessionEmployeeLinks)
	{	
		String dateTime = CommonFunctions.dateTimeNow();
		MOCPssrReccommend genEntTlBatchEmployeeLink1 = newEntTlSessionEmployeeLinks.get(index);
		index++;
		genEntTlSessionEmployeeLink.setPsrrActive("Y");
		genEntTlSessionEmployeeLink.setPsrrCreatedon(dateTime);
		genEntTlSessionEmployeeLink.setPsrrmasterid(mocKeyidRec);
	//	genEntTlSessionEmployeeLink.setEtceCreatedby("EMPOOO1");
		genEntTlSessionEmployeeLink.setPsrrModifiedon(dateTime);
		
		
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrmasterid())){
			genEntTlSessionEmployeeLink.setPsrrmasterid("{}");
		 }
		
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrrecmnd())){
			genEntTlSessionEmployeeLink.setPsrrrecmnd("{}");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrCategory())){
			genEntTlSessionEmployeeLink.setPsrrCategory("{}");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPssrResponsibility())){
			genEntTlSessionEmployeeLink.setPssrResponsibility("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrTargetDate())){
			genEntTlSessionEmployeeLink.setPsrrTargetDate(dateTime);
		 }
		
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrStatus())){
			genEntTlSessionEmployeeLink.setPsrrStatus("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrCompleteDate())){
			genEntTlSessionEmployeeLink.setPsrrCompleteDate(dateTime);
		 }
		
		
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrActionplanId())){
			genEntTlSessionEmployeeLink.setPsrrActionplanId("-");
		 }
		
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrTempfield1())){
			genEntTlSessionEmployeeLink.setPsrrTempfield1("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrTempfield2())){
			genEntTlSessionEmployeeLink.setPsrrTempfield2("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrTempfield3())){
			genEntTlSessionEmployeeLink.setPsrrTempfield3("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrTempfield4())){
			genEntTlSessionEmployeeLink.setPsrrTempfield4("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrTempfield5())){
			genEntTlSessionEmployeeLink.setPsrrTempfield5("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrCreatedby())){
			genEntTlSessionEmployeeLink.setPsrrCreatedby("{}");
		 }
		newEntTlBatchEmployeeLinkList.add(genEntTlSessionEmployeeLink);
}
return newEntTlBatchEmployeeLinkList;
}
@Override
public GenTlActionplanmst createActionPlan(GenTlActionplanmst newActionplanmst, GenTlActionplandtl newGenTlActionplandtl,
		String MocKeyidRec) throws Exception {
	// TODO Auto-generated method stub
	fillValuesActionPlanMaster(newActionplanmst,newGenTlActionplandtl);
	return mocDao.createActionPlan(newActionplanmst,newGenTlActionplandtl,MocKeyidRec);
}

private void fillValuesActionPlanMaster(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl newGenTlActionplandtl)throws Exception 
{
	String dateTime = CommonFunctions.dateTimeNow();
	genTlActionplanmst.setAplmActive("Y");	
	genTlActionplanmst.setAplmCreatedon(dateTime);
	genTlActionplanmst.setAplmModifiedon(dateTime);
	if(genTlActionplanmst.getAplmPillarid()==null)
		genTlActionplanmst.setAplmPillarid("{}");		
	if(genTlActionplanmst.getAplmMasterrefid()==null)
		genTlActionplanmst.setAplmMasterrefid("{}");
	if(genTlActionplanmst.getAplmDetailrefid()==null)
		genTlActionplanmst.setAplmDetailrefid("{}");
	if(genTlActionplanmst.getAplmMaintask()==null)
		genTlActionplanmst.setAplmMaintask("{}");
	if(genTlActionplanmst.getAplmRefdoctype()==null)
		genTlActionplanmst.setAplmRefdoctype("{}");
	if(genTlActionplanmst.getAplmStatus()==null)
		genTlActionplanmst.setAplmStatus("P");
	if(genTlActionplanmst.getAplmRemarks()==null)
		genTlActionplanmst.setAplmRemarks("{}");

	if(!UIUtils.isValidDate(genTlActionplanmst.getAplmPlandate()))
		genTlActionplanmst.setAplmPlandate(dateTime);
	
	if(genTlActionplanmst.getAplmTempfiled2()==null)
		genTlActionplanmst.setAplmTempfiled2("-");
	if(genTlActionplanmst.getAplmTempfiled3()==null)
		genTlActionplanmst.setAplmTempfiled3("-");
	if(genTlActionplanmst.getAplmTempfiled4()==null)
		genTlActionplanmst.setAplmTempfiled4("-");
	if(genTlActionplanmst.getAplmTempfiled5()==null)
		genTlActionplanmst.setAplmTempfiled5("-");	
	if(genTlActionplanmst.getAplmCreatedby()==null)
		genTlActionplanmst.setAplmCreatedby("{}");
	if(genTlActionplanmst.getAplmCreatedby()==null)
		genTlActionplanmst.setAplmCreatedby("{}");
	if(genTlActionplanmst.getAplmElementid()==null)
		genTlActionplanmst.setAplmElementid("{}");
	if(genTlActionplanmst.getAplmFlid()==null)
		genTlActionplanmst.setAplmFlid("{}");
	if(genTlActionplanmst.getAplmElementid()==null)
		genTlActionplanmst.setAplmElementid("{}");
	
	fillActionplandtlValues(newGenTlActionplandtl,genTlActionplanmst);		
	genTlActionplanmst.setAplmRemarks(newGenTlActionplandtl.getApldRemarks());
}

private void fillActionplandtlValues(GenTlActionplandtl genTlActionplandtl,GenTlActionplanmst genTlActionplanmst) {
	String dateTime = CommonFunctions.dateTimeNow();
	if(genTlActionplandtl.getApldTargetdate()==null)
		genTlActionplandtl.setApldTargetdate(dateTime);
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldActive()))
		genTlActionplandtl.setApldActive("Y");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldAplmKeyid()))
		genTlActionplandtl.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCreatedby()))
		genTlActionplandtl.setApldCreatedby(genTlActionplanmst.getAplmCreatedby());
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldActive()))
		genTlActionplandtl.setApldActive("Y");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldActionplan()))
		genTlActionplandtl.setApldActionplan("{}");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldHowtodo()))
		genTlActionplandtl.setApldHowtodo("{}");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCountermeasure()))
		genTlActionplandtl.setApldCountermeasure("{}");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldRemarks()))
		genTlActionplandtl.setApldRemarks("{}");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldResponsibility()))
		genTlActionplandtl.setApldResponsibility("{}");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldStatus()))
		genTlActionplandtl.setApldStatus("P");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTradeid()))
		genTlActionplandtl.setApldTradeid("{}");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCompletedby()))
		genTlActionplandtl.setApldCompletedby("{}");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCompleatedon()))
		genTlActionplandtl.setApldCompleatedon(Constants.futureNullDate);
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldModifiedon()))
		genTlActionplandtl.setApldModifiedon(CommonFunctions.dateTimeNow());
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCreatedon()))
		genTlActionplandtl.setApldCreatedon(CommonFunctions.dateTimeNow());
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldOthers()))
		genTlActionplandtl.setApldOthers("-");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled2()))
		genTlActionplandtl.setApldTempfiled2("-");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled3()))
		genTlActionplandtl.setApldTempfiled3("-");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled4()))
		genTlActionplandtl.setApldTempfiled4("-");
	if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled5()))
		genTlActionplandtl.setApldTempfiled5("-");
	}
@Override
public List<String[]> getPssrReccommendation(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getPssrReccommendation(commonFilter);
}
@Override
public WhatifMst getWhatIfMstData(String whatifKey) throws Exception {
	// TODO Auto-generated method stub
	WhatifMst whatifMst=new WhatifMst();
	WhatifMstSql whatifMstSql=new WhatifMstSql();
	String sql="SELECT * FROM MOC_TL_WHATIFMST WHERE WIFM_KEYID=?";
	CommonMessage.debugMsg("sql:::"+sql);
	Object args[]=new Object[]{whatifKey};
	whatifMst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	
	return whatifMst;
}
public HazopMst getHazopMstData(String mocKeyid) throws Exception{
	HazopMst HazopMst=new HazopMst();
	HazopMstSql hazopMstSql=new HazopMstSql();
	String sql="SELECT * FROM MOC_TL_HAZOPMST WHERE HZOM_KEYID=?";
	CommonMessage.debugMsg("sql:::"+sql);
	Object args[]=new Object[]{mocKeyid};
	HazopMst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	
	return HazopMst;
}
@Override
public MocPssrmst getPssrMstData(String pssrKey) throws Exception {
	// TODO Auto-generated method stub
	MocPssrmst MocPssrmst=new MocPssrmst();
	MocPssrmstSql mocPssrmstSql=new MocPssrmstSql();
	String sql="SELECT * FROM MOC_TL_PSSRCHECKLISTMST WHERE PSRM_KEYID=?";
	CommonMessage.debugMsg("sql:::"+sql);
	Object args[]=new Object[]{pssrKey};
	MocPssrmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	
	return MocPssrmst;
}
@Override
public KznTlKaizenbankmst getGridKaizenData(String suggestionId) throws Exception {
	// TODO Auto-generated method stub
	KznTlKaizenbankmst KznTlKaizenbankmst=new KznTlKaizenbankmst();
	KznTlKaizenbankmstSql KznTlKaizenbankmstSql=new KznTlKaizenbankmstSql();
	String sql="SELECT * FROM KZN_TL_KAIZENBANKMST WHERE KZBN_KEYID=?";
	CommonMessage.debugMsg("sql:::"+sql);
	Object args[]=new Object[]{suggestionId};
	KznTlKaizenbankmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	
	return KznTlKaizenbankmst;
}
public String getPssrCount(String mocKeyid) throws Exception{
	return mocDao.getPssrCount(mocKeyid);
}
@Override
public String getApprovalCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getApprovalCount(mockeyid);
}
@Override
public String getCompletedApprovalCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getCompletedApprovalCount(mockeyid);
}
@Override
public String getMaxGroupCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getMaxGroupCount(mockeyid);
}
@Override
public String getNextGroupCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getNextGroupCount(mockeyid);
}
@Override
public String getHazopApprovalCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getHazopApprovalCount(mockeyid);
}
@Override
public String getHazopCompletedApprovalCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getHazopCompletedApprovalCount(mockeyid);
}
@Override
public String getHazopMaxGroupCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getHazopMaxGroupCount(mockeyid);
}
@Override
public String getHazopNextGroupCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getHazopNextGroupCount(mockeyid);
}
@Override
public String getFinalApprovalCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getFinalApprovalCount(mockeyid);
}
@Override
public String getFinalCompletedApprovalCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getFinalCompletedApprovalCount(mockeyid);
}
@Override
public String getFinalMaxGroupCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getFinalMaxGroupCount(mockeyid);
}
@Override
public String getFinalNextGroupCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getFinalNextGroupCount(mockeyid);
}
@Override
public List<String[]> getHazopReccommendation(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getHazopReccommendation(commonFilter);
}
@Override
public String getMOCStatusCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getMOCStatusCount(mockeyid);
}
@Override
public List<MOCReccommendation> createReccommendation(List<MOCReccommendation> mOCReccommendationList,
		String mocKeyidRec, String detailid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.createReccommendation(fillValuesReccommendation(mOCReccommendationList,mocKeyidRec,detailid), mocKeyidRec,detailid); 

}
private List<MOCReccommendation> fillValuesReccommendation(List<MOCReccommendation> mOCReccommendationList, String mocKeyidRec,String detailid) {
	// TODO Auto-generated method stub
	List<MOCReccommendation> newEntTlSessionEmployeeLinks =mOCReccommendationList;
	List<MOCReccommendation> newEntTlBatchEmployeeLinkList = new ArrayList<MOCReccommendation>();
	int index=0;
	if(mOCReccommendationList!=null)
	for( MOCReccommendation genEntTlSessionEmployeeLink : newEntTlSessionEmployeeLinks)
	{	
		String dateTime = CommonFunctions.dateTimeNow();
		MOCReccommendation genEntTlBatchEmployeeLink1 = newEntTlSessionEmployeeLinks.get(index);
		index++;
		genEntTlSessionEmployeeLink.setMocrActive("Y");
		genEntTlSessionEmployeeLink.setMocrCreatedon(dateTime);
		genEntTlSessionEmployeeLink.setMocrMocid(mocKeyidRec);
		  
		//	genEntTlSessionEmployeeLink.setEtceCreatedby("EMPOOO1");
		genEntTlSessionEmployeeLink.setMocrModifiedon(dateTime);
		
		
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrmasterid())){
			genEntTlSessionEmployeeLink.setMocrmasterid("{}");
		 }
		
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrrecmnd())){
			genEntTlSessionEmployeeLink.setMocrrecmnd("{}");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrCategory())){
			genEntTlSessionEmployeeLink.setMocrCategory("{}");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrResponsibility())){
			genEntTlSessionEmployeeLink.setMocrResponsibility("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrTargetDate())){
			genEntTlSessionEmployeeLink.setMocrTargetDate(dateTime);
		 }
		
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrStatus())){
			genEntTlSessionEmployeeLink.setMocrStatus("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrCompleteDate())){
			genEntTlSessionEmployeeLink.setMocrCompleteDate(dateTime);
		 }
		
		
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrActionplanId())){
			genEntTlSessionEmployeeLink.setMocrActionplanId("-");
		 }
		
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrTempfield1())){
			genEntTlSessionEmployeeLink.setMocrTempfield1("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrTempfield2())){
			genEntTlSessionEmployeeLink.setMocrTempfield2("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrTempfield3())){
			genEntTlSessionEmployeeLink.setMocrTempfield3("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrTempfield4())){
			genEntTlSessionEmployeeLink.setMocrTempfield4("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrTempfield5())){
			genEntTlSessionEmployeeLink.setMocrTempfield5("-");
		 }
		if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getMocrCreatedby())){
			genEntTlSessionEmployeeLink.setMocrCreatedby("{}");
		 }
		newEntTlBatchEmployeeLinkList.add(genEntTlSessionEmployeeLink);
}
return newEntTlBatchEmployeeLinkList;

}
@Override
public GenTlActionplanmst createActionPlan(GenTlActionplanmst newActionplanmst, GenTlActionplandtl newActionplandtl,
		String mocKeyidRec, String PrrrKeyid) throws Exception {
	// TODO Auto-generated method stub
	fillValuesActionPlanMaster(newActionplanmst,newActionplandtl);
	return mocDao.createActionPlanRec(newActionplanmst,newActionplandtl,mocKeyidRec,PrrrKeyid);
}
/*@Override
public MOCReccommendation getUpdateReccommendation(MOCReccommendation newMOCReccommendation,
		MOCReccommendation existMOCReccommendation, String recKeyid, String responsiblity, String actionPlanStatus,
		String targetDate, String mocKeyidRec) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getUpdateReccommendation(newMOCReccommendation,existMOCReccommendation,recKeyid,responsiblity,actionPlanStatus,targetDate,mocKeyidRec);
}*/


@Override
public String getMocCompletionUpdate(String keyId) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getMocCompletionUpdate(keyId);
}
@Override
public String getFinalMaxGroupCountStatus(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getFinalMaxGroupCountStatus(mockeyid);
}
public String getnextApprovalKeyid(String mocKeyid) throws Exception{
	return mocDao.getnextApprovalKeyid(mocKeyid);
}
public String getHazopnextApprovalKeyid(String mocKeyid) throws Exception{
	  return mocDao.getHazopnextApprovalKeyid(mocKeyid);
}
public String getFinalApprovalKeyid(String mocKeyid) throws Exception{
   return mocDao.getFinalApprovalKeyid(mocKeyid);
}
@Override
public String getWhatifHazopCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getWhatifHazopCount(mockeyid);
}
@Override
public MOCReccommendation UpdateReccommendation(String mocKeyidRec,
		String detailid, String targetDate, String actionPlanStatus,
		String responsiblity) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.UpdateReccommendation(mocKeyidRec,detailid,targetDate,responsiblity,actionPlanStatus);
}
@Override
public MOCPssrReccommend UpdatePssrReccommendation(String mocKeyidRec,
		String psrrKeyId, String targetDate, String actionPlanStatus,
		String responsiblity) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.UpdatePssrReccommendation(mocKeyidRec,psrrKeyId,targetDate,responsiblity,actionPlanStatus);

}
@Override
public List<MOCPssrReccommend> createPSSRCheckReccommendation(List<MOCPssrReccommend> mOCPssrReccommendList,
		String mocKeyidRec, String detailid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.createPSSRCheckReccommendation(fillValuesPSSRCHECKReccommendation(mOCPssrReccommendList,mocKeyidRec,detailid), mocKeyidRec,detailid); 

}

private List<MOCPssrReccommend> fillValuesPSSRCHECKReccommendation(List<MOCPssrReccommend> mOCPssrReccommendList, String mocKeyidRec,
		String detailid) {
	// TODO Auto-generated method stub
	List<MOCPssrReccommend> newEntTlSessionEmployeeLinks =mOCPssrReccommendList;
	List<MOCPssrReccommend> newEntTlBatchEmployeeLinkList = new ArrayList<MOCPssrReccommend>();
	int index=0;
	if(mOCPssrReccommendList!=null)
		for( MOCPssrReccommend genEntTlSessionEmployeeLink : newEntTlSessionEmployeeLinks)
		{	
			String dateTime = CommonFunctions.dateTimeNow();
			MOCPssrReccommend genEntTlBatchEmployeeLink1 = newEntTlSessionEmployeeLinks.get(index);
			index++;
			genEntTlSessionEmployeeLink.setPsrrActive("Y");
			genEntTlSessionEmployeeLink.setPsrrCreatedon(dateTime);
			genEntTlSessionEmployeeLink.setPsrrmasterid(mocKeyidRec);
		//	genEntTlSessionEmployeeLink.setEtceCreatedby("EMPOOO1");
			genEntTlSessionEmployeeLink.setPsrrModifiedon(dateTime);
			
			
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrmasterid())){
				genEntTlSessionEmployeeLink.setPsrrmasterid("{}");
			 }
			
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrrecmnd())){
				genEntTlSessionEmployeeLink.setPsrrrecmnd("{}");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrCategory())){
				genEntTlSessionEmployeeLink.setPsrrCategory("{}");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPssrResponsibility())){
				genEntTlSessionEmployeeLink.setPssrResponsibility("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrTargetDate())){
				genEntTlSessionEmployeeLink.setPsrrTargetDate(dateTime);
			 }
			
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrStatus())){
				genEntTlSessionEmployeeLink.setPsrrStatus("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrCompleteDate())){
				genEntTlSessionEmployeeLink.setPsrrCompleteDate(dateTime);
			 }
			
			
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrActionplanId())){
				genEntTlSessionEmployeeLink.setPsrrActionplanId("-");
			 }
			
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrTempfield1())){
				genEntTlSessionEmployeeLink.setPsrrTempfield1("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrTempfield2())){
				genEntTlSessionEmployeeLink.setPsrrTempfield2("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrTempfield3())){
				genEntTlSessionEmployeeLink.setPsrrTempfield3("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrTempfield4())){
				genEntTlSessionEmployeeLink.setPsrrTempfield4("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrTempfield5())){
				genEntTlSessionEmployeeLink.setPsrrTempfield5("-");
			 }
			if(!UIUtils.isValidKeyId(genEntTlSessionEmployeeLink.getPsrrCreatedby())){
				genEntTlSessionEmployeeLink.setPsrrCreatedby("{}");
			 }
			newEntTlBatchEmployeeLinkList.add(genEntTlSessionEmployeeLink);
	}
	return newEntTlBatchEmployeeLinkList;
}


@Override
public MOCPssrReccommend UpdatePSSRReccommendation(String mocKeyidRec, String detailid, String targetDate,
		String actionPlanStatus, String responsiblity,String reccommendation) throws Exception {
			
			return mocDao.UpdatePSSRReccommendation(mocKeyidRec,detailid,targetDate,responsiblity,actionPlanStatus,reccommendation);
	
}
@Override
public String getClosureCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return mocDao.getClosureCount( mockeyid) ;
}
@Override
public Workbook MocExcelSheet(String format, String path, String mocKeyid, String flid, String DMT, String JH,String currentDate, String nature, String type, String detail, String pCHange, String title, String initiator,String MOCDate,String WhatifFacility,String WhatifTeam,String WhatifDate,String suggestion,String SuggestionId,String hazopFacility, String hazopTeam, String hazopNode, String hazopDesign, String hazopDate,String pidNo,String PssrFacility,String MOCDetails)
		throws Exception {
	// TODO Auto-generated method stub
CommonMessage.debugMsg(" in side service impl  "+mocKeyid);
		// TODO Auto-generated method stub
		List<String[]> MOCWorkFLow=mocDao.MOCWorkflow(flid,mocKeyid,MOCDate,JH,DMT,format,path);
		List<String[]> RFC=mocDao.RFC(mocKeyid);
		List<String[]> Question=mocDao.Question(mocKeyid);
		List<String[]> WhatIf=mocDao.WhatIf(mocKeyid);
		List<String[]> Hazop=mocDao.Hazop(mocKeyid);
		List<String[]> WHReccommend=mocDao.WHReccommend(mocKeyid);
		List<String[]> PssrCheck=mocDao.PssrCheck(mocKeyid);
		List<String[]> PssrReccommend=mocDao.PssrReccommend(mocKeyid);
		List<String[]> MOCClosure=mocDao.MOCClosure(mocKeyid);
		List<String[]> InitialApprovals=mocDao.InitialApprovals(mocKeyid);
		List<String[]> HazopApprovals=mocDao.HazopApprovals(mocKeyid);
		List<String[]> FinalApprovals=mocDao.FinalApprovals(mocKeyid);
		Workbook wb=(new MOCExcelTemplate(dbActionTemplate)).fillvaluesMOC(MOCWorkFLow,RFC,Question,WhatIf,Hazop,WHReccommend,PssrCheck,PssrReccommend,MOCClosure,InitialApprovals,HazopApprovals,FinalApprovals,format,path,DMT,JH,MOCDate,mocKeyid,suggestion,initiator,title,type,nature,pCHange,SuggestionId,detail,WhatifFacility,WhatifTeam,WhatifDate,hazopFacility,hazopTeam,hazopNode,hazopDesign,hazopDate,pidNo,PssrFacility,MOCDetails);
		String fileName =path + "/MOC_Details_"+JH+"_"+mocKeyid + "_" + UIUtils.now() +format;

		FileOutputStream out = new FileOutputStream( fileName );
		
		
	    wb.write(out); 
	    out.close();
	    out = null;
	    wb = null;

	    CommonFunctions.debugMsg(" File Write Completed " );
		
		CommonMessage.debugMsg(" Before sendLotusNotesMail" );
		
		List<String> attachmentFiles = new ArrayList<String>();
		attachmentFiles.add(fileName);
		CommonFunctions.debugMsg(" attachmentFiles."+fileName );
		
		//attachmentFiles = attachFileManagerFiles(attachmentFiles,mocKeyid);
		
		//UIUtils.sendLotusNotesMail(request,response,mailIds,null,subject.toString(),totalContent.toString(),fileName);
		//UIUtils.sendLotusNotesMailAttachments(request,response,mailIds,null,subject.toString(),totalContent.toString(),attachmentFiles);
		sendMail(attachmentFiles,mocKeyid);
		CommonMessage.debugMsg(" After sendLotusNotesMail" );
		//UIUtils.delete( f );
		
		//sendMail(wb,mocKeyid);
		
		CommonMessage.debugMsg(" fileNamefileNamefileNamefileName "+fileName);
		
		return wb;
	}


public Workbook NewDashboardExcelView(String format, String path, String mocKeyid, String flid, String DMT, String JH,String currentDate, String nature, String type, String detail, String pCHange, String title, String initiator,String MOCDate,String WhatifFacility,String WhatifTeam,String WhatifDate,String suggestion,String SuggestionId,String hazopFacility, String hazopTeam, String hazopNode, String hazopDesign, String hazopDate,String pidNo,String PssrFacility,String MOCDetails)
		throws Exception {
	// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<String[]> MOCWorkFLow=mocDao.MOCWorkflow(flid,mocKeyid,MOCDate,JH,DMT,format,path);
		List<String[]> RFC=mocDao.RFC(mocKeyid);
		List<String[]> Question=mocDao.Question(mocKeyid);
		List<String[]> WhatIf=mocDao.WhatIf(mocKeyid);
		List<String[]> Hazop=mocDao.Hazop(mocKeyid);
		List<String[]> WHReccommend=mocDao.WHReccommend(mocKeyid);
		List<String[]> PssrCheck=mocDao.PssrCheck(mocKeyid);
		List<String[]> PssrReccommend=mocDao.PssrReccommend(mocKeyid);
		List<String[]> MOCClosure=mocDao.MOCClosure(mocKeyid);
		List<String[]> InitialApprovals=mocDao.InitialApprovals(mocKeyid);
		List<String[]> HazopApprovals=mocDao.HazopApprovals(mocKeyid);
		List<String[]> FinalApprovals=mocDao.FinalApprovals(mocKeyid);
		Workbook wb=(new MOCExcelTemplate(dbActionTemplate)).fillvaluesMOC(MOCWorkFLow,RFC,Question,WhatIf,Hazop,WHReccommend,PssrCheck,PssrReccommend,MOCClosure,InitialApprovals,HazopApprovals,FinalApprovals,format,path,DMT,JH,MOCDate,mocKeyid,suggestion,initiator,title,type,nature,pCHange,SuggestionId,detail,WhatifFacility,WhatifTeam,WhatifDate,hazopFacility,hazopTeam,hazopNode,hazopDesign,hazopDate,pidNo,PssrFacility,MOCDetails);
		
	//	String fileName =path + "/MOC_Details_"+JH+"_"+mocKeyid + "_" + UIUtils.now() +format;

	//	FileOutputStream out = new FileOutputStream( fileName );
		
		
	//    wb.write(out); 
	//    out.close();
	//    out = null;
	//    wb = null;

	    CommonFunctions.debugMsg(" File Write Completed ");
			
		/*
		String content = UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting", "MOMMeetingMailContent");
		String disclaimerNote = UIUtils.getPropertyValue("com.akranta.tpm.resources.MinOfMeeting", "MOMMeetingMailDisclaimer");
		
		StringBuilder subject = new StringBuilder(mType);
		subject.append(" Meeting - ");
		subject.append(momDate);
		
		StringBuilder totalContent = new StringBuilder();
		totalContent.append(content);
		totalContent.append("\r\n");
		totalContent.append("\r\n");
		totalContent.append(disclaimerNote);*/
		
		return wb;
	}

private void sendMail(List<String> attachmentFiles,String mocKeyid) throws Exception{
	List<String []> emailList  =mocDao.getRecipientId(mocKeyid);
	
	String emailIds=buildToMailIds(emailList);
	String employeeId=buildToEmployeeIds(emailList);

	String content="http://10.35.13.242:8080/PerfexApprovals/mocApproval_input.mocApp?mocKeyid="+mocKeyid+"";

	//String content="http://localhost:8080/PerfexApprovals/mocApproval_input.mocApp?mocKeyid="+mocKeyid+"";


	String str=employeeId;
	 String[] empIds = str.split(","); 
	 String[] empEmail=emailIds.split(",");
	 for(int i=0; i<empIds.length; i++)  
	 {  
	 //prints the tokens  
		 String emp=empIds[i];
		String contents=content;
		contents+="&empId="+emp+"";
		 String sendTo=empEmail[i];
		 CommonMessage.debugMsg(content +" content in side the Service Impl");
		notesMail.send(sendTo,"","MOC Approval Mail",contents,attachmentFiles);

		contents=content;
	 }  

	

/*
	if(emailList.size()>0 )
	{
		for(String[]mailDetails : emailList)  
		{		
			try {
				
				//send(String sendTo, String copyTo, String subject, String content,List<String > attachmentFile) throws Exception
				String content="http://localhost:8080/PerfexApprvals/mocApproval_input.mocApp?mocKeyid="+mocKeyid+"";
			//	notesMail.send(mailDetails[0], "", " MOC Details Of: "+mocKeyid,subject,mailDetails[1],attachmentFiles);
				notesMail.send(emailIds,"","MOC Test Mail",content,attachmentFiles);
				//send(String sendTo, String copyTo, String subject, String content,List<String > attachmentFile) throws Exception
				
			}
			catch (MessagingException e) {
				e.printStackTrace();
				throw new BusinessApplicationExceptions("mailNotSent,");
			}
			
		}
	}
*/
}


private String buildToMailIds(List<String[]> mailIds){
	StringBuilder mailIdsStr = new StringBuilder();
	for(String [] mailid : mailIds){
		if(UIUtils.isValidEmail(mailid[1]) ){
			mailIdsStr.append(mailid[1]);
			mailIdsStr.append(',');
		}	
	}
	if(  mailIdsStr.length() > 0 )
		mailIdsStr.deleteCharAt(mailIdsStr.lastIndexOf(","));
	
	return mailIdsStr.toString();
}

private String buildToEmployeeIds(List<String[]> mailIds){
	StringBuilder empIdsStr = new StringBuilder();
	for(String [] mailid : mailIds){
		if(UIUtils.isValidKeyId(mailid[0]) ){
			empIdsStr.append(mailid[0]);
			empIdsStr.append(',');
		}	
	}
	if(  empIdsStr.length() > 0 )
		empIdsStr.deleteCharAt(empIdsStr.lastIndexOf(","));
	
	return empIdsStr.toString();
}

/*private void sendMail(StpTlServicerequestmst newStpTlServicerequestmst,String folderPath,String sendFlag,String mode) throws Exception
{
	CommonFunctions.debugMsg("folderPath "+folderPath);
	CommonFunctions.debugMsg("folderPath "+newStpTlServicerequestmst.getSermReportedby());
	CommonFunctions.debugMsg("folderPath "+newStpTlServicerequestmst.getSermDescription());
	Mail mail = new Mail(commonFilterDao.getDBActionTemplate());		
	//List<String []> mailList  =stpTlServicerequestmstDao.getRecipientId(mode);
	CommonFunctions.debugMsg("mailList:"+mailList.size());
	List<String []> fileDetails  =stpTlServicerequestmstDao.getFileDetails(newStpTlServicerequestmst.getSermKeyid());
	
	List<String> fileInfo = new ArrayList<String>();
	CommonFunctions.debugMsg("Size : "+fileDetails.size());
	if(fileDetails.size()>0)
	{
		for(String[]fileDetailsArr : fileDetails)
		{
			fileInfo.add(folderPath+fileDetailsArr[1]);
			CommonFunctions.debugMsg("Service Impl : "+folderPath+fileDetailsArr[1]);
		}
	}
	else
	{
		if(UIUtils.isValidKeyId(sendFlag))
		{
			if(sendFlag.equals("Y"))
				sendFlag = "N";
		}
	}
	CommonFunctions.debugMsg("sendFlag:"+sendFlag);
	if(mailList.size()>0 )
	{
		for(String[]mailDetails : mailList)
		{		
			try {
				mail.sendMailAttachment("", mailDetails[0], "PERFEX Service Booking-"+newStpTlServicerequestmst.getSermKeyid(),getSubject(newStpTlServicerequestmst),mailDetails[1],fileInfo);
			}
			catch (MessagingException e) {
				e.printStackTrace();
				throw new BusinessApplicationExceptions("mailNotSent,");
			}
			
		}
	}
	
		
}

*/
/*

public Workbook NewDashboardExcelView(String mocKeyid)		throws Exception {
	// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		List<String[]> MOCWorkFLow=mocDao.MOCWorkflow(mocKeyid);
		List<String[]> RFC=mocDao.RFC(mocKeyid);
		List<String[]> Question=mocDao.Question(mocKeyid);
		List<String[]> WhatIf=mocDao.WhatIf(mocKeyid);
		List<String[]> Hazop=mocDao.Hazop(mocKeyid);
		List<String[]> WHReccommend=mocDao.WHReccommend(mocKeyid);
		List<String[]> PssrCheck=mocDao.PssrCheck(mocKeyid);
		List<String[]> PssrReccommend=mocDao.PssrReccommend(mocKeyid);
		List<String[]> MOCClosure=mocDao.MOCClosure(mocKeyid);
		List<String[]> InitialApprovals=mocDao.InitialApprovals(mocKeyid);
		List<String[]> HazopApprovals=mocDao.HazopApprovals(mocKeyid);
		List<String[]> FinalApprovals=mocDao.FinalApprovals(mocKeyid);
		Workbook wb=(new MOCExcelTemplate(dbActionTemplate)).fillvaluesMOC(MOCWorkFLow,RFC,Question,WhatIf,Hazop,WHReccommend,PssrCheck,PssrReccommend,MOCClosure,InitialApprovals,HazopApprovals,FinalApprovals,format,path,DMT,JH,MOCDate,mocKeyid,suggestion,initiator,title,type,nature,pCHange,SuggestionId,detail,WhatifFacility,WhatifTeam,WhatifDate, ,hazopTeam,hazopNode,hazopDesign,hazopDate,pidNo,PssrFacility,MOCDetails);
	    return wb;
	} */
@Override
public String getDMTName(String dMTId) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getDmtName(dMTId);
}
@Override
public String getJHName(String jHId) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getJHName(jHId);
}
@Override
public String getPssrReccommendationCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getPssrReccommendationCount(mockeyid);
}
@Override
public String getPssrReccommendationCompleted(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getPssrReccommendationCompleted(mockeyid);
}
@Override
public List<String[]> getPSITeamMailIds(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getPSITeamMailIds(mockeyid);
}
@Override
public String getJHLeader(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getJHLeader(sugflid);
}
@Override
public String getDMTLeader(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getDMTLeader(sugflid);
}
@Override
public String getProcess(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getProcess(sugflid);
}
@Override
public String getMech(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getMech(sugflid);
}
@Override
public String getInstrument(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getInstrument(sugflid);
}
@Override
public String getCivil(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getCivil(sugflid);
}
@Override
public String getElect(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getElect(sugflid);
}
@Override
public String getEHSHead(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getEHSHead(sugflid);
}
@Override
public String getEHSManager(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getEHSManager(sugflid);
}
@Override
public String getPbuHead(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	return this.mocDao.getPbuHead(sugflid);
}



}




