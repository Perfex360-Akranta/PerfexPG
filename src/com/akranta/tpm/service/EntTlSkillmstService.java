package com.akranta.tpm.service;

import java.io.FileInputStream;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillmst;
import com.akranta.tpm.model.FunctionalLocn;

public interface EntTlSkillmstService {
	public List<EntTlSkillmst> getEntTlSkillmstValues(String factId,String DeptId) throws Exception;
	public  List<FunctionalLocn> getAllLocation(FunctionalLocn functionalLocn) throws Exception;
	public List<EntTlSkillmst> getAllSkill(EntTlSkillmst entTlSkillmst) throws Exception;
	public EntTlSkillmst create(EntTlSkillmst newEntTlSkillmst,EntTlSkillmst oldEntTlSkillmst)throws ValidationExceptions,BusinessApplicationExceptions, Exception;	
	public EntTlSkillmst update(EntTlSkillmst newEntTlSkillmst,EntTlSkillmst oldEntTlSkillmst)throws ValidationExceptions,BusinessApplicationExceptions, Exception;		
	public List<String []> getParentElem(String elemId) throws Exception;
	public List<String []> getChildElem(List<String> childElem,String formfield,String start,String end) throws Exception;
	public String getTotalCount(List<String> childElem,String formfield) throws Exception;	
	public EntTlSkillmst delete(EntTlSkillmst entTlSkillmst)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public EntTlSkillmst select(EntTlSkillmst entTlSkillmst)throws Exception;	
	public List<EntTlSkillmst> selectList(EntTlSkillmst entTlSkillmst)throws Exception;	
	public List<String[]> getSearchNode(String searchNode,String originalId) throws Exception;
	public List<String[]> getSearchSkillLevel(String searchNode) throws Exception;
	public String validateSkillLevel(EntTlSkillmst entTlSkillmst)throws Exception;
	public String validateDelSkillLevel(EntTlSkillmst entTlSkillmst)throws Exception;	
	public List<ComboBox> getSkilParentComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getSkilComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getEvaluationTypeComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getSkillTypeComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getDepartmentCombo() throws Exception ;
	
	public List<String[]> getSkillLevelInvent(CommonFilter commonFilter)throws Exception ;
	public Workbook skillLevelInventoryExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception ;
	
	public void getAllSkillLevel(List<String[]> skillHeader)throws Exception ;
	public List<String[]> getAllresource() throws Exception;
	
	
}
