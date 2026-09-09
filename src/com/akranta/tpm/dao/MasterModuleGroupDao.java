package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AuditmasterModel;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MasterModuleGroup;;

public interface MasterModuleGroupDao {
	
	public List<MasterModuleGroup> getAllMasterModuleGroup(String userId,String pillar) throws Exception;
//	public List<String []> getRelatedMst(String menuCaption,String menuName, List<String > paramValues) throws Exception;
	public List<String[]> getRelatedMst(String menuCaption,String menuName, String activeRecordFlag, GridParams gridParams) throws Exception;
//	public String getMstDatasCount(String menuName,List<String > paramValues) throws Exception;
	public String getMstDatasCount(String menuName,String activeRecordFlag,GridParams gridParams) throws Exception;
	//public Workbook generalMstFormExportExcel(String menuCaption,String menuName, List<String> paramValues,JSONObject colModel,String format) throws Exception;
	public void getMakeactive(String menuCaption, String menuName,List<String> paramValues) throws Exception;
	public Workbook generalMstFormExportExcel(String menuCaption, String menuName,String activeRecordFlag, JSONObject colModel, String format,GridParams gridParam) throws Exception;
	public List<AuditmasterModel> getauditmastermodule(String userid,String module)throws Exception;
	public List<AuditmasterModel> save(List<AuditmasterModel> auditsave)throws Exception;
	public List<String[]> getauditdata(CommonFilter commonFilter,GridParams gridparam)throws Exception;
	public Workbook getauditdataexcel(CommonFilter commonFilter,JSONObject jsonobj,String format)throws Exception;
	public List<String[]> auditreportdata(CommonFilter commonFilter)throws Exception;
 }
