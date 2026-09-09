package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlSusaAddBehaviormst;
import com.akranta.tpm.model.GenTlSusaParticipantmst;
import com.akranta.tpm.model.GenTlSusamstNew;

import net.sf.json.JSONObject;

public interface NewSusaService {
  public List<String[]> getEmployeeList(String susaKeyid) throws Exception;
  public String DeleteParticipants(String keyid) throws Exception;  
  public GenTlSusamstNew  getSelectedData(String keyid) throws Exception;
  public GenTlSusamstNew create(GenTlSusamstNew genTlSusamstNew,GenTlSusamstNew existGenTlSusamstNew) throws Exception;
  public GenTlSusamstNew deleteSusa(GenTlSusamstNew genTlSusamstNew) throws Exception;
  public GenTlSusamstNew update(GenTlSusamstNew genTlSusamstNew,GenTlSusamstNew existGenTlSusamstNew) throws Exception;
  public List<String[]> BehaviourDetail(CommonFilter commonFilter) throws Exception;
  public List<String[]> getNewSusaGridData(CommonFilter commonFilter) throws Exception;
  public List<String[]> getBehaviorList(CommonFilter commonFilter) throws Exception;
  public List<ComboBox> getDiscussionType(ComboFilter comboFilter) throws Exception;
  public List<String[]> getElementId(String loginflid, String loginlevel,
String loginElementid, String empId) throws Exception;
  public GenTlSusaAddBehaviormst updateActionPlanId(String susaid,String AplKeyid) throws Exception;
  public List<ComboBox> getCause(ComboFilter comboFilter) throws Exception;
  public List<ComboBox> getProbability(ComboFilter comboFilter) throws Exception;
  public List<ComboBox> getConsequence(ComboFilter comboFilter) throws Exception;
  public List<ComboBox> getAction(ComboFilter comboFilter) throws Exception;
  public List<ComboBox> getBehaviour(String BehaviourCategoryid,ComboFilter comboFilter) throws Exception;
  public List<ComboBox> getBehaviourCategory(ComboFilter comboFilter) throws Exception;
  public Workbook getNewSusaModificationGridDataExportExcel(CommonFilter commonFilter,
		  JSONObject colmodel, String format) throws  Exception; 
  public  GenTlSusaParticipantmst ParticipantsCreate(GenTlSusaParticipantmst newGenTlSusaParticipantmst,
		  GenTlSusaParticipantmst existGenTlSusaParticipantmst) throws Exception;
  public  GenTlSusaAddBehaviormst BehaviourCreate(GenTlSusaAddBehaviormst newGenTlSusaAddBehaviormst,
		  GenTlSusaAddBehaviormst existGenTlSusaAddBehaviormst) throws Exception; 
  public  GenTlSusaAddBehaviormst BehaviourUpdate(GenTlSusaAddBehaviormst newGenTlSusaAddBehaviormst,
		  GenTlSusaAddBehaviormst existGenTlSusaAddBehaviormst) throws Exception;
  public Workbook SusaExcelView(String keyid, String flid, String format,String path,String imagepath,CommonFilter commonFilter)throws Exception;
  public String getImageCount(String keyid) throws Exception;
  public List<GenTlAllmoduleimgfile> getSusaAllImages(String fileName,String path, String keyId) throws Exception;
  public GenTlSusaAddBehaviormst behaviourDelete(GenTlSusaAddBehaviormst newenTlSusaAddBehaviormst) throws Exception;
  public void DeleteSusaBehaviour(String keyid)throws Exception;
}
