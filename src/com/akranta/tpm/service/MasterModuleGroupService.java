package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AuditmasterModel;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MasterModuleGroup;

public interface MasterModuleGroupService {

	public List<MasterModuleGroup> getAllMasterModuleGroup(String userId,String pillar) throws Exception;
	public List<String []> getRelatedMst(String menuCaption,String menuName, String activeRecordFlag, GridParams gridParams) throws Exception;
	public String getMstDatasCount(String menuName, String activeRecordFlag, GridParams gridParams) throws Exception;
	
	public Workbook generalMstFormExportExcel(String menuCaption,String menuName, String activeRecordFlag,JSONObject colModel,String format,GridParams gridParams) throws Exception;
	public void getMakeactive(String menuCaption, String menuName,List<String> paramValues) throws Exception;
	public List<AuditmasterModel> getauditmastermodule(String userid,String module) throws Exception;
	public List<AuditmasterModel> save(List<AuditmasterModel> saveeaudit)throws Exception;
	public List<String[]> getauditdata(CommonFilter commonFilter,GridParams gridparam)throws Exception;
	public List<ComboBox> getauditcombo(ComboFilter comboFilter)throws Exception;
	 public Workbook getauditdataexcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
    public List<String[]> auditreportdata(CommonFilter commonFilter)throws Exception;
    public List<ComboBox> getTableCombo(CommonFilter commonfilter)throws Exception;
}
