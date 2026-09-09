package com.akranta.tpm.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillmst;
import com.akranta.tpm.model.FunctionalLocn;

public interface EntTlSkillmstDao {

	public List<EntTlSkillmst> getEntTlSkillmstValues(String factId,String DeptId) throws Exception;
	public  List<FunctionalLocn> getAllLocation(FunctionalLocn functionalLocn) throws Exception;
	public List<EntTlSkillmst> getAllSkill(EntTlSkillmst entTlSkillmst) throws Exception;
	public EntTlSkillmst create(EntTlSkillmst newEntTlSkillmst)throws BusinessApplicationExceptions, Exception;	
	public EntTlSkillmst update(EntTlSkillmst newEntTlSkillmst)throws BusinessApplicationExceptions, Exception;		
	public List<String []> getParentElem(String elemId) throws Exception;
	public List<String []> getChildElem(List<String> childElem,String formfield,String start,String end) throws Exception;
	public String getTotalCount(List<String> childElem,String formfield) throws Exception;
	public int getSkillLevel(EntTlSkillmst entTlSkillmst)throws Exception;
	public int getConfigSkillLevel()throws Exception;
	public EntTlSkillmst delete(EntTlSkillmst entTlSkillmst)throws BusinessApplicationExceptions, Exception;
	public EntTlSkillmst select(EntTlSkillmst entTlSkillmst)throws Exception;
	public List<EntTlSkillmst> selectList(EntTlSkillmst entTlSkillmst)throws Exception;
	public  List<String[]>  getSearchNode(String searchNode,String originalId) throws Exception;
	public  List<String[]>  getSearchSkillLevel(String searchNode) throws Exception;
	public abstract List<String[]> getSkillLevelInvent(CommonFilter commonFilter)throws Exception;
	public abstract Workbook skillLevelInventoryExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	
	public void getAllSkillLevel(List<String[]> skillLevel) throws Exception;
	public List<String[]> getResource() throws Exception;
	
}

