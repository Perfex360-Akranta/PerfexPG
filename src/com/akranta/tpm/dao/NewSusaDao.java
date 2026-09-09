package com.akranta.tpm.dao;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlSusaAddBehaviormst;
import com.akranta.tpm.model.GenTlSusaParticipantmst;
import com.akranta.tpm.model.GenTlSusamstNew;

import net.sf.json.JSONObject;

public interface NewSusaDao {
public List<String[]> getEmployeeList(String susaKeyid) throws Exception;
public String DeleteParticipants(String keyid) throws Exception;  
public GenTlSusamstNew  getSelectedData(String keyid) throws Exception;
public List<String[]> getNewSusaGridData(CommonFilter commonFilter) throws Exception;
public List<String[]> getBehaviorList(CommonFilter commonFilter) throws Exception;
public GenTlSusaAddBehaviormst updateActionPlanId(String susaid,String AplKeyid) throws Exception;
public List<String[]> getElementId(String loginflid, String loginlevel,
		String loginElementid, String empId) throws Exception;
public GenTlSusamstNew create(GenTlSusamstNew genTlSusamstNew) throws Exception;
public GenTlSusamstNew update(GenTlSusamstNew genTlSusamstNew) throws Exception;
public GenTlSusamstNew deleteSusa(GenTlSusamstNew genTlSusamstNew) throws Exception;
public List<String[]> BehaviourDetail(CommonFilter commonFilter) throws Exception;
public Workbook getNewSusaModificationGridDataExportExcel(CommonFilter commonFilter,
		JSONObject colmodel, String format) throws  Exception; 
public GenTlSusaParticipantmst ParticipantsCreate(GenTlSusaParticipantmst newGenTlSusaParticipantmst) throws Exception;
public  GenTlSusaAddBehaviormst BehaviourCreate(GenTlSusaAddBehaviormst newGenTlSusaAddBehaviormst) throws Exception;
public  GenTlSusaAddBehaviormst BehaviourUpdate(GenTlSusaAddBehaviormst newGenTlSusaAddBehaviormst) throws Exception; 
public List<String[]> getBehaviorCountData(CommonFilter commonFilter) throws Exception;
public List<String[]> getBehaviorCategoryCountData(CommonFilter commonFilter) throws Exception;
public Workbook getNewSusaSafeUnsafeExportExcel(CommonFilter commonFilter,
		JSONObject colmodel, String format) throws  Exception;
public Workbook getNewSusaSUBehaviourExportExcel(CommonFilter commonFilter,
		  JSONObject colmodel, String format) throws  Exception;
public List<String[]> getNewSusaReportData(CommonFilter commonFilter) throws Exception;
public Workbook getNewSusaDiscussionExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;
List<String[]> SusaDetailsData(String keyid,String flid) throws Exception; 
List<String[]> susaBehaviourData(String keyid) throws Exception;
public String getImageCount(String keyid) throws Exception;
//public List<GenTlAllmoduleimgfile> getSusaAllImages(String fileName,String path, String keyId) throws Exception;
public abstract List<GenTlAllmoduleimgfile> getSusaImages(List<GenTlAllmoduleimgfile> incidentImgList) throws NoDataFoundException, Exception;
public GenTlSusaAddBehaviormst behaviourDelete(GenTlSusaAddBehaviormst newenTlSusaAddBehaviormst) throws Exception;
public List<String[]> getSusaSafeUnsafeReport(CommonFilter commonFilter) throws Exception;
public void DeleteSusaBehaviour(String keyid) throws Exception;
}
