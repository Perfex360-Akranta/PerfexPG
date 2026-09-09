package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.EquipmentBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMachinemst;
import com.akranta.tpm.model.GenTlMchcirclelink;

public interface GenTlMachinemstDao {

	public abstract GenTlMachinemst create(GenTlMachinemst genTlMachinemst) throws Exception;
	public abstract GenTlMachinemst update(GenTlMachinemst genTlMachinemst) throws Exception;
	public abstract GenTlMachinemst delete(String delMode, GenTlMachinemst genTlMachinemst) throws Exception;
	public abstract GenTlMachinemst getselect(String MchmKeyid) throws Exception;
	public abstract List<String[]> getOperator(String eqpId) throws Exception;
	public abstract List<String[]> getOperatorData(String factId) throws Exception;
	public abstract List<String[]> getSkillData()throws Exception;
	public abstract List<String[]> getMaintainceData()throws Exception;
	public abstract List<String[]> getMaintSkillData()throws Exception;
	public abstract List<String[]> getEquipParmData()throws Exception;
	public abstract List<String[]> getSubEquipData(String section)throws Exception;
	public abstract List<String[]> getEquipmentParm(String machineId)throws Exception;
	public abstract List<String[]> getOperatorRecall(String oprRecall)throws Exception;
	public abstract List<String[]> getOperatorSkillRecall(String recall) throws Exception;
	public abstract List<String[]> getMaintainceRecall(String recall)throws Exception;
	public abstract List<String[]> getMaintainceSkillRecall(String recall)throws Exception;
	public abstract List<String[]> getMainGrid(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getEquipmentParameterRecall(String recall) throws Exception;
	public abstract List<String[]> getSubEquipmentRecall(String recall) throws Exception;
	public abstract List<String[]> getAllSubEquipDataForEqp(String sectionId,String eqpId)throws Exception; 

	public void deleteOperatorSkill(String machineId, String skillName) throws Exception;
	public void deleteMainTeamSkill(String machineId, String skillName) throws Exception;
	public void deleteOperatorMachineLink(String machineId, String empId) throws Exception;
	public void deleteMaintTeamMachineLink(String machineId, String maintTeamId) throws Exception;
	public abstract Workbook EquipmentFormExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat) throws Exception;
	public  int selectCount(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getInactive(String keyIds) throws Exception;
	public abstract List<String[]> getAll(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getAllCircle(String mchId)throws Exception;
	public abstract List<String[]> getFormCircle(String mchId)throws Exception;
	public abstract String createCircle(List<GenTlMchcirclelink> newGenTlMchcirclelinksList)throws Exception;
	public abstract String deleteCircle(GenTlMchcirclelink newGenTlMchcirclelink)throws Exception;
	public abstract List<String[]> getAllPM() throws Exception;
	public abstract List<String[]> getAllPMgrid(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getEquipOtherDetails(String machineId)throws Exception;
	
	public abstract String getElementByMachineId(String machineId) throws Exception;
	
	public abstract void GenTlMachinemstDaoImplJwt(String jwtToken);
}

