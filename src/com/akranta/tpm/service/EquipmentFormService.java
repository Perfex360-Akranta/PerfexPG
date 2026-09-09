package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EquipmentBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.GenTlMachinemst;
import com.akranta.tpm.model.GenTlMchcirclelink;

public interface EquipmentFormService {

	public List<ComboBox> getEquipmentGroup(String condSql) throws Exception;
	public List<ComboBox> getWorkCenter(ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getMachineRank(ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getCircle(String condSql) throws Exception;
	public List<ComboBox> getPurpose(ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getCategory(ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getSubCategory(String category,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getMachineName(String condSql) throws Exception;

	public List<ComboBox> getJHstep(ComboFilter comboFilter) throws Exception;

	public List<ComboBox> getMachineKeyId(String keyid) throws Exception;	
	public GenTlMachinemst select(String keyid) throws Exception;
	public GenTlMachinemst create(GenTlMachinemst newgenTlMachinemst,GenTlMachinemst existGenTlMachinemst, EquipmentBean equipmentBean)throws ValidationExceptions,Exception;
	public GenTlMachinemst update(GenTlMachinemst newgenTlMachinemst,GenTlMachinemst existGenTlMachinemst, EquipmentBean equipmentBean)throws ValidationExceptions,Exception;
	List<ComboBox> getPowerSupply(ComboFilter comboFilter) throws Exception;
	List<ComboBox> getConnectedLoad(ComboFilter comboFilter) throws Exception;
	List<ComboBox> getDBNo(ComboFilter comboFilter) throws Exception;
	List<ComboBox> getSBNo(ComboFilter comboFilter) throws Exception;
	List<ComboBox> getManufacture(ComboFilter comboFilter) throws Exception;
	List<ComboBox> getMake(ComboFilter comboFilter) throws Exception;
	List<ComboBox> getModel(ComboFilter comboFilter) throws Exception;
	List<ComboBox> getSupplier(ComboFilter comboFilter) throws Exception;
	List<ComboBox> getUnit(ComboFilter comboFilter) throws Exception;
	List<ComboBox> getProvider(ComboFilter comboFilter) throws Exception;
	public GenTlMachinemst delete(String delMode, GenTlMachinemst newGenTlMachinemst) throws ValidationExceptions,Exception;
	List<String[]> getAllOperator(String eqpId) throws Exception;
	List<String[]> getAllOperatorData(String factId) throws Exception;
	List<String[]> getAllSkillData()throws Exception;
	List<String[]> getAllMaintainceData()throws Exception;
	List<String[]> getAllMaintSkillData()throws Exception;
	List<String[]> getAllEquipParmData()throws Exception;
	List<String[]> getAllSubEquipData(String condSql)throws Exception;
	List<String[]> getAllEquipmentParm(String machineId)throws Exception;
	List<String[]> getOperatorRecallData(String oprRecall)throws Exception;
	List<String[]> getOperatorSkillRecallData(String recall)throws Exception;
	List<String[]> getMaintainceRecallData(String recall)throws Exception;
	List<String[]> getMaintainceSkillRecallData(String recall)throws Exception;
	List<String[]> getMasterGrid(CommonFilter commonFilter)throws Exception;
	List<String[]> getEquipmentParameterRecallData(String recall) throws Exception;
	List<ComboBox> getSubSection(ComboFilter comboFilter)throws Exception;
	List<String[]> getSubEquipmentRecallData(String recall) throws Exception;
	List<String[]> getAllSubEquipDataForEqp(String sectionId, String eqpId) throws Exception;
	List<ComboBox> getCostCentre(String costId) throws Exception;

	
	public void deleteOperatorSkill(String machineId, String skillName) throws Exception;
	public void deleteMainTeamSkill(String machineId, String skillName) throws Exception;
	public void deleteOperatorMachineLink(String machineId, String empId) throws Exception;
	public void deleteMaintTeamMachineLink(String machineId, String maintTeamId) throws Exception;
	public Workbook EquipmentFormExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	public int selectCount(CommonFilter commonFilter) throws Exception;
	public List<String[]> getInactive(String keyIds) throws Exception;
	public List<String[]> getAll(CommonFilter commonFilter) throws Exception;
	public List<String[]> getAllCircle(String mchId)throws Exception;
	public List<String[]> getFormCircle(String mchId)throws Exception;
	public String createCircle(GenTlMchcirclelink newGenTlMchcirclelink,GenTlMchcirclelink existGenTlMachinemst, EquipmentBean equipmentBean)throws Exception;
	public String deleteCircle(GenTlMchcirclelink newGenTlMchcirclelink,GenTlMchcirclelink existGenTlMchcirclelink,EquipmentBean equipmentBean)throws Exception;
	public List<String[]> getAllPM() throws Exception;
	public List<String[]> getAllPMgrid(CommonFilter commonFilter) throws Exception;
	public List<String[]> getEquipOtherDetails(String machineId)throws Exception;
	public void EquipmentFormServiceImplJwt(String JwtToken);
	

}
