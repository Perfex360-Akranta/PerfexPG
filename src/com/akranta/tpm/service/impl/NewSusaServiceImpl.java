package com.akranta.tpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.NewSusaDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.NewSusaDaoImpl;
import com.akranta.tpm.exportreport.SUSANEWITCExcelTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlSusaAddBehaviormst;
import com.akranta.tpm.model.GenTlSusaParticipantmst;
import com.akranta.tpm.model.GenTlSusamstNew;
import com.akranta.tpm.service.NewSusaService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

import net.sf.json.JSONObject;

public class NewSusaServiceImpl implements NewSusaService{
	private Validations validations ;
	DBActionTemplate  dbActionTemplate ;
	NewSusaDao newSusaDao;  
	CommonFilterDao commonFilterDao;
public NewSusaServiceImpl(DBActionTemplate dbActionTemplate){
		 
		validations = new Validations();
		this.dbActionTemplate  =dbActionTemplate;
		newSusaDao=new NewSusaDaoImpl(dbActionTemplate);
		commonFilterDao=new CommonFilterDaoImpl(dbActionTemplate);
}
public List<String[]> getEmployeeList(String susaKeyid) throws Exception{
	 return newSusaDao.getEmployeeList(susaKeyid);
}
public String DeleteParticipants(String keyid) throws Exception{
	return newSusaDao.DeleteParticipants(keyid);
}
public GenTlSusamstNew  getSelectedData(String keyid) throws Exception{
	return newSusaDao.getSelectedData(keyid);
}
public GenTlSusamstNew create(GenTlSusamstNew genTlSusamstNew,GenTlSusamstNew existGenTlSusamstNew) throws Exception{
            String XmlName="NewSusaValidations";
		    String validationsFor = "create";
		    validations.validate(genTlSusamstNew,XmlName,validationsFor);
			fillValuess(genTlSusamstNew,existGenTlSusamstNew);
	        return newSusaDao.create(genTlSusamstNew);
}
public GenTlSusamstNew update(GenTlSusamstNew genTlSusamstNew,GenTlSusamstNew existGenTlSusamstNew) throws Exception{
	    String XmlName="NewSusaValidations";
	    String validationsFor = "create";
	    validations.validate(genTlSusamstNew,XmlName,validationsFor);
		fillValuess(genTlSusamstNew,existGenTlSusamstNew);
        return newSusaDao.update(genTlSusamstNew);
}
public GenTlSusamstNew deleteSusa(GenTlSusamstNew genTlSusamstNew) throws Exception{
	return newSusaDao.deleteSusa(genTlSusamstNew);
}
public List<String[]> BehaviourDetail(CommonFilter commonFilter) throws Exception{
	 return newSusaDao.BehaviourDetail(commonFilter);
}

public List<String[]> getBehaviorList(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return newSusaDao.getBehaviorList(commonFilter);
}

public List<String[]> getNewSusaGridData(CommonFilter commonFilter) throws Exception{
  return newSusaDao.getNewSusaGridData(commonFilter);
}
private void fillValuess(GenTlSusamstNew genTlSusamstNew,GenTlSusamstNew existGenTlSusamstNew) throws Exception{
	String dateTime=CommonFunctions.dateTimeNow();
	genTlSusamstNew.setSusnActive("Y");
	genTlSusamstNew.setSusnCreatedon(dateTime);
	genTlSusamstNew.setSusnModifiedon(dateTime);
	
	if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnFlid())){
		genTlSusamstNew.setSusnFlid("{}");
	 }
	if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnDate())){
		genTlSusamstNew.setSusnDate(dateTime);
	}
   if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnDiscussionType())){
	   genTlSusamstNew.setSusnDiscussionType("{}");
   }
   if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnDiscussionNo())){
	   genTlSusamstNew.setSusnDiscussionNo("0");
   }
   if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnPreparedby())){
	   genTlSusamstNew.setSusnPreparedby("{}");
   }
   if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnPersonRole())){
	   genTlSusamstNew.setSusnPersonRole("{}");
   }
   if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnDiscussionSumm())){
	   genTlSusamstNew.setSusnDiscussionSumm("{}");
   }
   if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnOtherParticipants())){
	   genTlSusamstNew.setSusnOtherParticipants("{}");
   }
   if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnSusaDoneJh())){
	   genTlSusamstNew.setSusnSusaDoneJh("{}");
   }
   if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnSusaDoneby())){
    	genTlSusamstNew.setSusnSusaDoneby("{}");
    }
   if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnTempField3())){
	   genTlSusamstNew.setSusnTempField3("{}");
   }
   if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnTempField4())){
	   genTlSusamstNew.setSusnTempField4("{}");
   }
   if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnTempField5())){
	   genTlSusamstNew.setSusnTempField5("{}");
   }
   if(!UIUtils.isValidKeyId(genTlSusamstNew.getSusnCreatedby())){
	   genTlSusamstNew.setSusnCreatedby("{}");
   }
   
   genTlSusamstNew.setAddBehaviourMst(fillValuesBehaviourDetails(genTlSusamstNew));
   
}


private List<GenTlSusaAddBehaviormst> fillValuesBehaviourDetails(GenTlSusamstNew genTlSusamstNew) {
	String dateTime = CommonFunctions.dateTimeNow();
	
	List<GenTlSusaAddBehaviormst> AddBehaviourList=genTlSusamstNew.getAddBehaviourMst();

	if (genTlSusamstNew.getAddBehaviourMst()!=null){
		for(int i=0 ;i<=AddBehaviourList.size()-1;i++){	
			AddBehaviourList.get(i).setSuabCreatedon(dateTime);
			AddBehaviourList.get(i).setSuabModifiedon(dateTime);
			AddBehaviourList.get(i).setSuabCreatedby(genTlSusamstNew.getSusnCreatedby());
			AddBehaviourList.get(i).setSuabActive("Y");
            
			if(AddBehaviourList.get(i).getSuabKeyid()==null)
				AddBehaviourList.get(i).setSuabKeyid("{}");
			if(AddBehaviourList.get(i).getSuabFlid()==null)
				AddBehaviourList.get(i).setSuabFlid("{}");
			if(AddBehaviourList.get(i).getSuabImage()==null)
				AddBehaviourList.get(i).setSuabImage("{}");
			if(AddBehaviourList.get(i).getSuabAction()==null)
				AddBehaviourList.get(i).setSuabAction("{}");
			if(AddBehaviourList.get(i).getSuabActionPlanId()==null)
				AddBehaviourList.get(i).setSuabActionPlanId("{}");
			if(AddBehaviourList.get(i).getSuabBehavCategory()==null)
				AddBehaviourList.get(i).setSuabBehavCategory("{}");
			if(AddBehaviourList.get(i).getSuabBehavior()==null)
				AddBehaviourList.get(i).setSuabBehavior("{}");
			if(AddBehaviourList.get(i).getSuabCause()==null)
				AddBehaviourList.get(i).setSuabCause("{}");
			if(AddBehaviourList.get(i).getSuabConsequence()==null)
				AddBehaviourList.get(i).setSuabConsequence("{}");
			if(AddBehaviourList.get(i).getSuabProbability()==null)
				AddBehaviourList.get(i).setSuabProbability("{}");
			if(AddBehaviourList.get(i).getSuabRemarks()==null)
				AddBehaviourList.get(i).setSuabRemarks("{}");
			if(AddBehaviourList.get(i).getSuabSusnKeyid()==null)
				AddBehaviourList.get(i).setSuabSusnKeyid("{}");
			if(AddBehaviourList.get(i).getSuabType()==null)
				AddBehaviourList.get(i).setSuabType("{}");
			if(AddBehaviourList.get(i).getSuabTempfield1()==null)
				AddBehaviourList.get(i).setSuabTempfield1("-");
			if(AddBehaviourList.get(i).getSuabTempField2()==null)
				AddBehaviourList.get(i).setSuabTempField2("-");
			if(AddBehaviourList.get(i).getSuabTempfield3()==null)
				AddBehaviourList.get(i).setSuabTempfield3("-");
			if(AddBehaviourList.get(i).getSuabTempfield4()==null)
				AddBehaviourList.get(i).setSuabTempfield4("-");
			if(AddBehaviourList.get(i).getSuabTempfield5()==null)
				AddBehaviourList.get(i).setSuabTempfield5("-");
		}
	}
	return AddBehaviourList;
}


public GenTlSusaAddBehaviormst updateActionPlanId(String susaid,String AplKeyid) throws Exception{
	return newSusaDao.updateActionPlanId(susaid, AplKeyid);
}
public List<ComboBox> getDiscussionType(ComboFilter comboFilter) throws Exception {
	comboFilter.setNameField("SUDT_NAME");
	comboFilter.setIdField("SUDT_KEYID");
	comboFilter.setTableName("GEN_TL_SUSADISCUSSIONTYPEMST");
	return commonFilterDao.fillComboValues(comboFilter);
}
@Override
public List<String[]> getElementId(String loginflid, String loginlevel,
		String loginElementid, String empId) throws Exception {
	// TODO Auto-generated method stub
	return newSusaDao.getElementId(loginflid,loginlevel,loginElementid,empId);
}
public List<ComboBox> getCause(ComboFilter comboFilter) throws Exception{
	//ComboFilter comboFilter=new ComboFilter();
	comboFilter.setNameField("SUSC_NAME");
	comboFilter.setIdField("SUSC_KEYID");
	comboFilter.setTableName("GEN_TL_SUSACAUSEMST");
	return commonFilterDao.fillComboValues(comboFilter);
}
public List<ComboBox> getProbability(ComboFilter comboFilter) throws Exception{
	comboFilter.setNameField("SUSP_NAME");
	comboFilter.setIdField("SUSP_KEYID");
	comboFilter.setTableName("GEN_TL_SUSAPROBABILITYMST");
	return commonFilterDao.fillComboValues(comboFilter);
}
public List<ComboBox> getConsequence(ComboFilter comboFilter) throws Exception{
	comboFilter.setNameField("SUCM_NAME");
	comboFilter.setIdField("SUCM_KEYID");
	comboFilter.setTableName("GEN_TL_SUSACONSEQUENCEMST");
	return commonFilterDao.fillComboValues(comboFilter);
}
public List<ComboBox> getAction(ComboFilter comboFilter) throws Exception{
	comboFilter.setNameField("SUAA_NAME");
	comboFilter.setIdField("SUAA_KEYID");
	comboFilter.setTableName("GEN_TL_SUSAACTIONMST");
	return commonFilterDao.fillComboValues(comboFilter);
}
public List<ComboBox> getBehaviour(String BehaviourCategoryid,ComboFilter comboFilter) throws Exception{
	String TableName="GEN_TL_SUSABEHAVIOURMST,GEN_TL_SUSABEHAVIOURCATEGORY";

	comboFilter.setNameField("SUSB_NAME");
	comboFilter.setIdField("SUSB_KEYID");
	if(UIUtils.isValidKeyId(BehaviourCategoryid)){
	comboFilter.setCondSql(" AND SUSB_BEHAVCATGEGORYID='"+BehaviourCategoryid+"'");
	}
	comboFilter.setTableName(TableName);
	return commonFilterDao.fillComboValues(comboFilter);
}
public List<ComboBox> getBehaviourCategory(ComboFilter comboFilter) throws Exception{
	comboFilter.setNameField("SUBC_NAME");
	comboFilter.setIdField("SUBC_KEYID");
	comboFilter.setTableName("GEN_TL_SUSABEHAVIOURCATEGORY");
	return commonFilterDao.fillComboValues(comboFilter);
}
public Workbook getNewSusaModificationGridDataExportExcel(CommonFilter commonFilter,
		JSONObject colmodel, String format) throws  Exception {
	// TODO Auto-generated method stub
	 return newSusaDao.getNewSusaModificationGridDataExportExcel(commonFilter, colmodel, format);
}
public GenTlSusaParticipantmst ParticipantsCreate(GenTlSusaParticipantmst newGenTlSusaParticipantmst,
		GenTlSusaParticipantmst existGenTlSusaParticipantmst) throws Exception{
	    fillParticipantsValues(newGenTlSusaParticipantmst,existGenTlSusaParticipantmst);
	return newSusaDao.ParticipantsCreate(newGenTlSusaParticipantmst);
}
public  GenTlSusaAddBehaviormst BehaviourCreate(GenTlSusaAddBehaviormst newGenTlSusaAddBehaviormst,
		GenTlSusaAddBehaviormst existGenTlSusaAddBehaviormst) throws Exception{
	fillBehaviourValues(newGenTlSusaAddBehaviormst,existGenTlSusaAddBehaviormst);
    return newSusaDao.BehaviourCreate(newGenTlSusaAddBehaviormst);
}
public  GenTlSusaAddBehaviormst BehaviourUpdate(GenTlSusaAddBehaviormst newGenTlSusaAddBehaviormst,
		  GenTlSusaAddBehaviormst existGenTlSusaAddBehaviormst) throws Exception{
    return newSusaDao.BehaviourUpdate(newGenTlSusaAddBehaviormst);

}
public Workbook SusaExcelView(String keyid, String flid, String format,String path,String imagepath,CommonFilter commonFilter)throws Exception{
	CommonMessage.debugMsg("SusaExcelView::ServiceImpl::"+keyid);
	List<String[]>  SusaDetailsData=newSusaDao.SusaDetailsData(keyid,flid);
	List<String[]>  susaBehaviourData=newSusaDao.susaBehaviourData(keyid);
	CommonMessage.debugMsg("ServiceImpl Path::::"+path);
	Workbook wb=(new SUSANEWITCExcelTemplate(dbActionTemplate)).fillValues(SusaDetailsData,susaBehaviourData,path,keyid,imagepath);
    return wb;	

}
public String getImageCount(String keyid) throws Exception{
  return  newSusaDao.getImageCount(keyid);
}
public GenTlSusaAddBehaviormst behaviourDelete(GenTlSusaAddBehaviormst newenTlSusaAddBehaviormst) throws Exception{
	return newSusaDao.behaviourDelete(newenTlSusaAddBehaviormst); 
}

public List<GenTlAllmoduleimgfile> getSusaAllImages(String fileName,String path, String keyId) throws Exception{
	List<GenTlAllmoduleimgfile> SusaImgList = new ArrayList<GenTlAllmoduleimgfile>();
	if(UIUtils.isValidKeyId(keyId))
	{
		GenTlAllmoduleimgfile FirstSusaImage = new GenTlAllmoduleimgfile();
		
		FirstSusaImage.setImflBlobimage(path);
		FirstSusaImage.setImflFilename(fileName);
		FirstSusaImage.setImflRefkeyid(keyId);
		FirstSusaImage.setImflRefdoctype("SB");
		FirstSusaImage.setImflImagetype("SU1");
		SusaImgList.add(FirstSusaImage);
		
     GenTlAllmoduleimgfile SecondSusaImage = new GenTlAllmoduleimgfile();
     SecondSusaImage.setImflBlobimage(path);
     SecondSusaImage.setImflFilename(fileName);
     SecondSusaImage.setImflRefkeyid(keyId);
     SecondSusaImage.setImflRefdoctype("SB"); //SUN
     SecondSusaImage.setImflImagetype("SU2");
	 SusaImgList.add(SecondSusaImage);
		
	 GenTlAllmoduleimgfile ThirdSusaImage = new GenTlAllmoduleimgfile();
	 ThirdSusaImage.setImflBlobimage(path);
	 ThirdSusaImage.setImflFilename(fileName);
	 ThirdSusaImage.setImflRefkeyid(keyId);
	 ThirdSusaImage.setImflRefdoctype("SB");//SUN
	 ThirdSusaImage.setImflImagetype("SU3");
	 SusaImgList.add(ThirdSusaImage);
     
	 GenTlAllmoduleimgfile FourthSusaImage = new GenTlAllmoduleimgfile();
	 FourthSusaImage.setImflBlobimage(path);
	 FourthSusaImage.setImflFilename(fileName);
	 FourthSusaImage.setImflRefkeyid(keyId);
	 FourthSusaImage.setImflRefdoctype("SB"); //SUN
	 FourthSusaImage.setImflImagetype("SU4");
	 SusaImgList.add(FourthSusaImage);
	 
	}
	CommonMessage.debugMsg("List Size ="+SusaImgList.size());			
	return this.newSusaDao.getSusaImages(SusaImgList);
}
public   GenTlSusaParticipantmst fillParticipantsValues(GenTlSusaParticipantmst newGenTlSusaParticipantmst,
		 GenTlSusaParticipantmst existGenTlSusaParticipantmst) throws Exception{
        
	newGenTlSusaParticipantmst.setSustActive("Y");
	String DateTime=CommonFunctions.dateTimeNow();
	newGenTlSusaParticipantmst.setSustCreatedon(DateTime);
	newGenTlSusaParticipantmst.setSustModifiedon(DateTime);
	
	if(!UIUtils.isValidKeyId(newGenTlSusaParticipantmst.getSustEmpmKeyid()))
		newGenTlSusaParticipantmst.setSustEmpmKeyid("-");
	 if(!UIUtils.isValidKeyId(newGenTlSusaParticipantmst.getSustTempfield1()))
		 newGenTlSusaParticipantmst.setSustTempfield1("-");
	 if(!UIUtils.isValidKeyId(newGenTlSusaParticipantmst.getSustTempfield2()))
		 newGenTlSusaParticipantmst.setSustTempfield2("-");
	 if(!UIUtils.isValidKeyId(newGenTlSusaParticipantmst.getSustTempfield3()))
		 newGenTlSusaParticipantmst.setSustTempfield3("-");
	 
   return newGenTlSusaParticipantmst;
}


public GenTlSusaAddBehaviormst fillBehaviourValues(GenTlSusaAddBehaviormst newGenTlSusaAddBehaviormst,
		GenTlSusaAddBehaviormst existGenTlSusaAddBehaviormst) throws Exception{
	newGenTlSusaAddBehaviormst.setSuabActive("Y");
	String dateTime=CommonFunctions.dateTimeNow();
	newGenTlSusaAddBehaviormst.setSuabCreatedon(dateTime);
	newGenTlSusaAddBehaviormst.setSuabModifiedon(dateTime);
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabFlid()))
		newGenTlSusaAddBehaviormst.setSuabFlid("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabType()))
		newGenTlSusaAddBehaviormst.setSuabType("-");
	 if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabBehavCategory()))
		 newGenTlSusaAddBehaviormst.setSuabBehavCategory("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabAction()))
		newGenTlSusaAddBehaviormst.setSuabAction("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabBehavior()))
		newGenTlSusaAddBehaviormst.setSuabBehavior("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabCause()))
		newGenTlSusaAddBehaviormst.setSuabCause("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabConsequence()))
		newGenTlSusaAddBehaviormst.setSuabConsequence("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabProbability()))
		newGenTlSusaAddBehaviormst.setSuabProbability("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabImage()))
		newGenTlSusaAddBehaviormst.setSuabImage("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabActionPlanId()))
		newGenTlSusaAddBehaviormst.setSuabActionPlanId("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabRemarks()))
		newGenTlSusaAddBehaviormst.setSuabRemarks("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabTempfield1()))
		newGenTlSusaAddBehaviormst.setSuabTempfield1("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabTempField2()))
		newGenTlSusaAddBehaviormst.setSuabTempField2("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabTempfield3()))
		newGenTlSusaAddBehaviormst.setSuabTempfield3("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabTempfield4()))
		newGenTlSusaAddBehaviormst.setSuabTempfield4("-");
	if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabTempfield5()))
		newGenTlSusaAddBehaviormst.setSuabTempfield5("-");
	 if(!UIUtils.isValidKeyId(newGenTlSusaAddBehaviormst.getSuabCreatedby()))
		 newGenTlSusaAddBehaviormst.setSuabCreatedby("-");
	 return newGenTlSusaAddBehaviormst;
}
@Override
public void DeleteSusaBehaviour(String keyid) throws Exception 
{
	this.newSusaDao.DeleteSusaBehaviour(keyid);

}

}
