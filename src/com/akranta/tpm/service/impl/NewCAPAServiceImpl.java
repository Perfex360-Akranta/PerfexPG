package com.akranta.tpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.NewCAPADao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.NewCAPADaoImpl;

import com.akranta.tpm.dao.sql.QtmTlCapaSql;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlCapa;
import com.akranta.tpm.model.QtmTlCapaActionmst;
import com.akranta.tpm.model.QtmTlCapaWorkflow;
import com.akranta.tpm.service.NewCAPAService;
import com.akranta.tpm.service.api.KaizenBankServiceAPI;
import com.akranta.tpm.service.api.NewcapaServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

import net.sf.json.JSONObject;

public class NewCAPAServiceImpl implements NewCAPAService{
	private Validations validations ;
	private NewcapaServiceApi newcapaServiceApi;
	DBActionTemplate  dbActionTemplate ;
	CommonFilterDao commonFilterDao;
	NewCAPADao newCapaDao;
	NewCAPADaoImpl newCAPADaoImpl;
public NewCAPAServiceImpl(DBActionTemplate dbActionTemplate){
		validations = new Validations();
		this.dbActionTemplate  =dbActionTemplate;
		commonFilterDao=new CommonFilterDaoImpl(dbActionTemplate);
		newCapaDao=new NewCAPADaoImpl(dbActionTemplate);
}
public void NewCAPAServiceImplJwt(String JwtToken){
	try{
		newCapaDao.NewCAPADaoImplJwt(JwtToken);
		newcapaServiceApi = new NewcapaServiceApi(JwtToken);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}


public List<ComboBox> getDefect(ComboFilter comboFilter,String flid) throws Exception {
	comboFilter.setNameField("GQDM_DEFECT");
	comboFilter.setIdField("GQDM_KEYID");
	comboFilter.setCondSql("AND GQDM_FLID='"+flid+"'");
	comboFilter.setTableName("GEN_TL_QUALITYDEFECTMST");
	return commonFilterDao.fillComboValues(comboFilter);
}
@Override
public QtmTlCapa create(QtmTlCapa newQtmTlCapa, QtmTlCapa existQtmTlCapa) throws Exception {
	// TODO Auto-generated method stub
	 String XmlName="NewCAPAValidations";
	    String validationsFor = "create";
	   validations.validate(newQtmTlCapa,XmlName,validationsFor);
		fillValues(newQtmTlCapa,existQtmTlCapa);
//     return newCapaDao.create(newQtmTlCapa);
		return newcapaServiceApi.saveQtmTlCapa(newQtmTlCapa);
}
private void fillValues(QtmTlCapa newQtmTlCapa, QtmTlCapa existQtmTlCapa) {
	// TODO Auto-generated method stub
	String dateTime=CommonFunctions.pg_dateTimeNow();
	newQtmTlCapa.setQcapActive("Y");
	newQtmTlCapa.setQcapCreatedon(dateTime);
	newQtmTlCapa.setQcapModifiedon(dateTime);
	
	if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapFlid())){
		newQtmTlCapa.setQcapFlid("{}");
	 }
	String date = newQtmTlCapa.getQcapDate();
	newQtmTlCapa.setQcapDate(CommonFunctions.pg_getDateTimeFromDate(date));
	
	if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapDate())){
		newQtmTlCapa.setQcapDate(dateTime);
	}
   if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapQualityIssue())){
	   newQtmTlCapa.setQcapQualityIssue("{}");
   }
   if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapQuantityeffect())){
	   newQtmTlCapa.setQcapQuantityeffect("0");
   }
   if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapDefect())){
	   newQtmTlCapa.setQcapDefect("{}");
   }
   if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapUom())){
	   newQtmTlCapa.setQcapUom("{}");
   }
   if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapTodmt())){
	   newQtmTlCapa.setQcapTodmt("{}");
   }
   if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapTojh())){
	   newQtmTlCapa.setQcapTojh("{}");
   }
   if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapComplaintraisedby())){
	   newQtmTlCapa.setQcapComplaintraisedby("{}");
   }
    if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapTodmtleader())){
    	newQtmTlCapa.setQcapTodmtleader("{}");
    }
    
       
     if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapAreaincharge())){
         	newQtmTlCapa.setQcapAreaincharge("{}");
         	
         	  if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapType())){
         		   newQtmTlCapa.setQcapType("{}");
         	   }
         	   if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapTempfield2())){
         		   newQtmTlCapa.setQcapTempfield2("{}");
         	   }
     if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapTempfield3())){
	   newQtmTlCapa.setQcapTempfield3("{}");
   }
   if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapTempfield4())){
	   newQtmTlCapa.setQcapTempfield4("{}");
   }
   if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapTempfield5())){
	   newQtmTlCapa.setQcapTempfield5("{}");
   }
   if(!UIUtils.isValidKeyId(newQtmTlCapa.getQcapCreatedby())){
	   newQtmTlCapa.setQcapCreatedby("{}");
   } 
     }
}
@Override
public QtmTlCapa update(QtmTlCapa newQtmTlCapa, QtmTlCapa existQtmTlCapa) throws Exception {
	// TODO Auto-generated method stub
	fillValues(newQtmTlCapa,existQtmTlCapa);
	return newCapaDao.update( newQtmTlCapa);
   //return newcapaServiceApi.saveQtmTlCapa(newQtmTlCapa);
}
@Override
public List<String[]> getDmtLeaderApproval(CommonFilter commonFilter, String capaId) throws Exception {
	// TODO Auto-generated method stub
	return newCapaDao.getDmtLeaderApproval(commonFilter,capaId);
}
@Override
public QtmTlCapaWorkflow createWorkflow(QtmTlCapaWorkflow qtmTlCapaWorkflow,
		String capaId) throws Exception {
	// TODO Auto-generated method stub
	System.out.println("Inside Workflow");
	fillValuesWorkflow(qtmTlCapaWorkflow,capaId);
//    return newCapaDao.create(qtmTlCapaWorkflow,capaId);
	return newcapaServiceApi.saveQtmTlCapaWorkflow(qtmTlCapaWorkflow,capaId);
}
private void fillValuesWorkflow(QtmTlCapaWorkflow qtmTlCapaWorkflow,
		String capaId) {
	String dateTime=CommonFunctions.pg_dateTimeNow();
	qtmTlCapaWorkflow.setQcwfActive("Y");
	qtmTlCapaWorkflow.setQcwfCreatedon(dateTime);
	qtmTlCapaWorkflow.setQcwfModifiedon(dateTime);
	qtmTlCapaWorkflow.setQcwfCaparefid(capaId);
	if(!UIUtils.isValidKeyId(qtmTlCapaWorkflow.getQcwfRoleid())){
		qtmTlCapaWorkflow.setQcwfRoleid("{}");
	 }
	if(!UIUtils.isValidKeyId(qtmTlCapaWorkflow.getQcwfApproveby())){
		qtmTlCapaWorkflow.setQcwfApproveby("{}");
	}
   if(!UIUtils.isValidKeyId(qtmTlCapaWorkflow.getQcwfStatus())){
	   qtmTlCapaWorkflow.setQcwfStatus("-");
   }
   if(!UIUtils.isValidKeyId(qtmTlCapaWorkflow.getQcwfDate())){
	   qtmTlCapaWorkflow.setQcwfDate("");
   }
   if(!UIUtils.isValidKeyId(qtmTlCapaWorkflow.getQcwfRemarks())){
	   qtmTlCapaWorkflow.setQcwfRemarks("{}");
   }
           	  if(!UIUtils.isValidKeyId(qtmTlCapaWorkflow.getQcwfTempfield1())){
         		   qtmTlCapaWorkflow.setQcwfTempfield1("-");
         	   }
         	   if(!UIUtils.isValidKeyId(qtmTlCapaWorkflow.getQcwfTempfield2())){
         		   qtmTlCapaWorkflow.setQcwfTempfield2("-");
         	   }
     if(!UIUtils.isValidKeyId(qtmTlCapaWorkflow.getQcwfTempfield3())){
	   qtmTlCapaWorkflow.setQcwfTempfield3("-");
   }
   if(!UIUtils.isValidKeyId(qtmTlCapaWorkflow.getQcwfTempfield4())){
	   qtmTlCapaWorkflow.setQcwfTempfield4("-");
   }
  
   if(!UIUtils.isValidKeyId(qtmTlCapaWorkflow.getQcwfCreatedby())){
	   qtmTlCapaWorkflow.setQcwfCreatedby("{}");
   } 
     }
@Override
public List<String[]> getCAPARelatedData(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return newCapaDao.getCAPARelatedData(commonFilter);
}

// --------Vignesh 

public  Workbook getExcelreport(CommonFilter commonFilter,
		JSONObject colModel, String format) throws Exception {
	// TODO Auto-generated method stub

	return newCapaDao.getExcelreport(commonFilter,colModel,format);
}
//------------- Vignesh added -------------------------------//
@Override
public QtmTlCapa getCapaData(String capaId) throws Exception {
	// TODO Auto-generated method stub
	QtmTlCapa qtmTlCapa=new QtmTlCapa();
	QtmTlCapaSql qtmTlCapaSql=new QtmTlCapaSql();
	String sql="SELECT * FROM QTM_TL_CAPA WHERE QCAP_KEYID=?";
	System.out.println("sql:::"+sql);
	Object args[]=new Object[]{capaId};
	qtmTlCapa.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	
	return qtmTlCapa;
}
@Override
public QtmTlCapaWorkflow getComplaintApproval(QtmTlCapaWorkflow qtmTlCapaWorkflow,
		QtmTlCapaWorkflow existQtmTlCapaWorkflow, String keyid, String empId, String status, String date,
		String remarks, String capaId) throws Exception {
	// TODO Auto-generated method stub
	//return this.newCapaDao.getComplaintApproval(qtmTlCapaWorkflow,existQtmTlCapaWorkflow,keyid,empId,status,date,remarks,capaId);
	
	System.out.println(
	        "updateCapaWorkflow called with -> " +
	        "keyid: [" + keyid + "], " +
	        "empId: [" + empId + "], " +
	        "status: [" + status + "], " +
	        "date: [" + date + "], " +
	        "remarks: [" + remarks + "], " +
	        "capaId: [" + capaId + "]"
	);
	return this.newcapaServiceApi.updateCapaWorkflow(qtmTlCapaWorkflow,empId,status,date,remarks,capaId);
	
	 

}
@Override
public List<QtmTlCapaActionmst> createCAPA(List<QtmTlCapaActionmst> qtmTlCapaActionmst, String createdBy,String CapaMasterKeyid)
		throws Exception {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
		System.out.println("In Service Impl Team Config");
		// return mocDao.createTeam(fillValuesMOCTeam(mocTeamConfigmst),createdBy,qtmTlCapaActionmst); 
//	return this.newCapaDao.createCAPA(fillValuesCAPA(qtmTlCapaActionmst,createdBy),createdBy,CapaMasterKeyid);
	
	return this.newcapaServiceApi.saveQtmTlCapaActionmst(fillValuesCAPA(qtmTlCapaActionmst,createdBy),createdBy,CapaMasterKeyid);
}
private List<QtmTlCapaActionmst> fillValuesCAPA(List<QtmTlCapaActionmst> qtmTlCapaActionmst,String createdBy) throws Exception {
	// TODO Auto-generated method stub
	System.out.println("In Fill Val CAPA");
	List<QtmTlCapaActionmst> newDescLinks =qtmTlCapaActionmst;
	List<QtmTlCapaActionmst> newLinkList = new ArrayList<QtmTlCapaActionmst>();
	
	int index=0;
	if(qtmTlCapaActionmst!=null)
	for( QtmTlCapaActionmst teamLink : newDescLinks)
	{	
		System.out.println("In FMCT");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		QtmTlCapaActionmst getDescLink1 = newDescLinks.get(index);
		index++;
		teamLink.setQcpaActive("Y");
		teamLink.setQcpaModifiedon(dateTime);
		teamLink.setQcpaCreatedon(dateTime);
		teamLink.setQcpaCreatedby(createdBy);
      /*  		
		if(!UIUtils.isValidKeyId(getDescLink1.getFodcDesciption())){
			genDescriptionLink.setFodcDesciption("{}");
			 }*/
		if( teamLink.getQcpaQcapkeyid() == null )
			teamLink.setQcpaQcapkeyid("{}");
		
		if( teamLink.getQcpaCorrectiveaction() == null )
			teamLink.setQcpaCorrectiveaction("{}");
		if( teamLink.getQcpaRootcause() == null )
			teamLink.setQcpaRootcause("{}");
		
		if( teamLink.getQcpaPreventiveaction() == null )
			teamLink.setQcpaPreventiveaction("{}");
		
		if( teamLink.getQcpa4mcondition() == null )
			teamLink.setQcpa4mcondition("-");
		if( teamLink.getQcpaDefecttype() == null )
			teamLink.setQcpaDefecttype("-");
		if( teamLink.getQcpaResponsibleid() == null )
			teamLink.setQcpaResponsibleid("-");
		if( teamLink.getQcpaTargetdate() == null )
			teamLink.setQcpaTargetdate(dateTime);
		
		
		teamLink.setQcpaTargetdate(CommonFunctions.pg_getDateTimeFromDate(teamLink.getQcpaTargetdate()));
		
		if( teamLink.getQcpaStatus() == null )
			teamLink.setQcpaStatus("{}");
		if( teamLink.getQcpaCompletedon() == null )
			teamLink.setQcpaCompletedon(dateTime);
		if( teamLink.getQcpaActionplanid() == null )
			teamLink.setQcpaActionplanid("-");
		if( teamLink.getQcpaWhywhyid() == null )
			teamLink.setQcpaWhywhyid("{}");
		if( teamLink.getQcpaSuggestionid() == null )
			teamLink.setQcpaSuggestionid("-");
	
	    if( teamLink.getQcpaTempfield1() == null )
			teamLink.setQcpaTempfield1("-");
		if( teamLink.getQcpaTempfield2() == null )
			teamLink.setQcpaTempfield2("-");
		if( teamLink.getQcpaTempfield3() == null )
			teamLink.setQcpaTempfield3("-");
		
		
	    if( teamLink.getQcpaTempfield4() == null )
			teamLink.setQcpaTempfield4("-");
		if( teamLink.getQcpaTempfield5() == null )
			teamLink.setQcpaTempfield5("-");
		if( teamLink.getQcpaTempfield6() == null )
			teamLink.setQcpaTempfield6("-");
		
		
	    if( teamLink.getQcpaTempfield7() == null )
			teamLink.setQcpaTempfield7("-");
		if( teamLink.getQcpaTempfield8() == null )
			teamLink.setQcpaTempfield8("-");
		if( teamLink.getQcpaTempfield9() == null )
			teamLink.setQcpaTempfield9("-");
		
	if( teamLink.getQcpaTempfield10()  == null )
			teamLink.setQcpaTempfield10("-");
	
		
newLinkList.add(teamLink);
}
	return newLinkList;
}
@Override
public QtmTlCapaActionmst getCapaActionPlanSave(QtmTlCapaActionmst qtmTlCapaActionmst,
		QtmTlCapaActionmst existQtmTlCapaActionmst, String keyid, String preventiveAction, String rootCause,
		String targetDate, String status, String mType, String defect,String CreatedBy,String correctiveAction,String Responsibility) throws Exception {
	// TODO Auto-generated method stub
	
	return this.newCapaDao.createCAPA(fillValuesCAPActionPlan(qtmTlCapaActionmst,CreatedBy,keyid,preventiveAction,rootCause,targetDate,status,mType,defect,correctiveAction, Responsibility),CreatedBy,keyid,preventiveAction,rootCause,targetDate,status,mType,defect);

}
private QtmTlCapaActionmst fillValuesCAPActionPlan(QtmTlCapaActionmst qtmTlCapaActionmst, String createdBy,String keyid,String preventiveAction,String rootCause,String targetDate,String status,String mType,String defect,String correctiveAction,String Responsibility) {
	
	System.out.println("Inside FillValueees1");
	String dateTime = CommonFunctions.dateTimeNow();
	qtmTlCapaActionmst.setQcpaActive("Y");
	qtmTlCapaActionmst.setQcpaModifiedon(dateTime);
	qtmTlCapaActionmst.setQcpaCreatedon(dateTime);
	qtmTlCapaActionmst.setQcpaCreatedby(createdBy);
	qtmTlCapaActionmst.setQcpaCorrectiveaction(correctiveAction);
	qtmTlCapaActionmst.setQcpaPreventiveaction(preventiveAction);
	qtmTlCapaActionmst.setQcpaRootcause(rootCause);
	qtmTlCapaActionmst.setQcpaTargetdate(targetDate);
	qtmTlCapaActionmst.setQcpaDefecttype(defect);
	qtmTlCapaActionmst.setQcpaResponsibleid(Responsibility);
	qtmTlCapaActionmst.setQcpa4mcondition(mType);
	qtmTlCapaActionmst.setQcpaQcapkeyid(keyid);
	
	if( qtmTlCapaActionmst.getQcpaQcapkeyid() == null )
		qtmTlCapaActionmst.setQcpaQcapkeyid("{}");
	
	if( qtmTlCapaActionmst.getQcpaCorrectiveaction() == null )
		qtmTlCapaActionmst.setQcpaCorrectiveaction("{}");
	if( qtmTlCapaActionmst.getQcpaRootcause() == null )
		qtmTlCapaActionmst.setQcpaRootcause("{}");
	
	if( qtmTlCapaActionmst.getQcpaPreventiveaction() == null )
		qtmTlCapaActionmst.setQcpaPreventiveaction("{}");
	
	if( qtmTlCapaActionmst.getQcpa4mcondition() == null )
		qtmTlCapaActionmst.setQcpa4mcondition("-");
	if( qtmTlCapaActionmst.getQcpaDefecttype() == null )
		qtmTlCapaActionmst.setQcpaDefecttype("-");
	if( qtmTlCapaActionmst.getQcpaResponsibleid() == null )
		qtmTlCapaActionmst.setQcpaResponsibleid("-");
	if( qtmTlCapaActionmst.getQcpaTargetdate() == null )
		qtmTlCapaActionmst.setQcpaTargetdate(dateTime);
	if( qtmTlCapaActionmst.getQcpaStatus() == null )
		qtmTlCapaActionmst.setQcpaStatus("-");
	if( qtmTlCapaActionmst.getQcpaCompletedon() == null )
		qtmTlCapaActionmst.setQcpaCompletedon(dateTime);
	if( qtmTlCapaActionmst.getQcpaActionplanid() == null )
		qtmTlCapaActionmst.setQcpaActionplanid("-");
	if( qtmTlCapaActionmst.getQcpaWhywhyid() == null )
		qtmTlCapaActionmst.setQcpaWhywhyid("{}");
	if( qtmTlCapaActionmst.getQcpaSuggestionid() == null )
		qtmTlCapaActionmst.setQcpaSuggestionid("-");

    if( qtmTlCapaActionmst.getQcpaTempfield1() == null )
		qtmTlCapaActionmst.setQcpaTempfield1("-");
	if( qtmTlCapaActionmst.getQcpaTempfield2() == null )
		qtmTlCapaActionmst.setQcpaTempfield2("-");
	if( qtmTlCapaActionmst.getQcpaTempfield3() == null )
		qtmTlCapaActionmst.setQcpaTempfield3("-");
	
	
    if( qtmTlCapaActionmst.getQcpaTempfield4() == null )
		qtmTlCapaActionmst.setQcpaTempfield4("-");
	if( qtmTlCapaActionmst.getQcpaTempfield5() == null )
		qtmTlCapaActionmst.setQcpaTempfield5("-");
	if( qtmTlCapaActionmst.getQcpaTempfield6() == null )
		qtmTlCapaActionmst.setQcpaTempfield6("-");
	
	
    if( qtmTlCapaActionmst.getQcpaTempfield7() == null )
		qtmTlCapaActionmst.setQcpaTempfield7("-");
	if( qtmTlCapaActionmst.getQcpaTempfield8() == null )
		qtmTlCapaActionmst.setQcpaTempfield8("-");
	if( qtmTlCapaActionmst.getQcpaTempfield9() == null )
		qtmTlCapaActionmst.setQcpaTempfield9("-");
	
if( qtmTlCapaActionmst.getQcpaTempfield10()  == null )
		qtmTlCapaActionmst.setQcpaTempfield10("-");
	return qtmTlCapaActionmst;
		
}
@Override
public List<String[]> getCapaDetails(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return this.newCapaDao.getCapaDetails(commonFilter);
}
@Override
public QtmTlCapaActionmst getCapaActionPlanUpdate(QtmTlCapaActionmst qtmTlCapaActionmst,
		QtmTlCapaActionmst existQtmTlCapaActionmst, String keyid, String preventiveAction, String rootCause,
		String targetDate, String status, String mType, String defect, String createdBy, String correctiveAction,
		String responsibility,String CAPA) throws Exception {
	// TODO Auto-generated method stub
	return this.newCapaDao.getCapaActionPlanUpdate(qtmTlCapaActionmst,existQtmTlCapaActionmst,keyid,preventiveAction,rootCause,targetDate,status,mType,defect,correctiveAction,responsibility,CAPA);
}

//elumalai new remove button
@Override
public QtmTlCapaActionmst deleteCapaActionPlanDetail(QtmTlCapaActionmst qtmTlCapaActionmst) throws Exception {
//  return newCapaDao.deleteCapaActionPlanDetail(qtmTlCapaActionmst);
  return newcapaServiceApi.deleteCapaActionPlanDetail(qtmTlCapaActionmst);
}
@Override
public String getTargetFlid(String targetJH) throws Exception {
	// TODO Auto-generated method stub
//	return this.newCapaDao.getTargetFlid(targetJH);
	return this.newcapaServiceApi.getFnlnKeyIdByOriginalId(targetJH);
}
@Override
public List<ComboBox> getDefectComboList(ComboFilter combofilter, String Sect) throws Exception {
	// TODO Auto-generated method stub
	System.out.println("In Type Service Impl");
	
	  String FlidId=dbActionTemplate.getSingleValue("GEN_TL_FUNCTIONALLOCN","FNLN_KEYID","FNLN_ORIGINALID",Sect);
	   System.out.println("FlidId"+FlidId);
	
	   
    String Tablename="GEN_TL_QUALITYDEFECTMST";
    System.out.println("Table"+Tablename);
	combofilter.setIdField("GQDM_KEYID");
	combofilter.setNameField("GQDM_DEFECT");
	if(FlidId!=null){
	combofilter.setCondSql(" AND GQDM_FLID='"+FlidId+"' ");
	}
	combofilter.setTableName(Tablename);
	
	return commonFilterDao.fillComboValues(combofilter);
}
@Override
public List<String[]> getCAPAEmpMailIds(String complaintNo) throws Exception {
	// TODO Auto-generated method stub
	return newCapaDao.getCAPAEmpMailIds(complaintNo);
}
@Override
public QtmTlCapaWorkflow UpdateWorkflow(QtmTlCapaWorkflow qtmTlCapaWorkflow, String capaId,String targetDmtLeader, String status) throws Exception {
	// TODO Auto-generated method stub
	return newCapaDao.UpdateWorkflow(qtmTlCapaWorkflow,capaId,targetDmtLeader,status);
	
}	
	

}
