package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlFnlnrolemap;
import com.akranta.tpm.model.GenTlFnlnroleteam;

public interface GenTlFnlnrolemapService {

	public abstract GenTlFnlnrolemap create(GenTlFnlnrolemap genTlFnlnrolemap) throws Exception;
	public abstract GenTlFnlnrolemap update(GenTlFnlnrolemap genTlFnlnrolemap) throws Exception;
	public abstract GenTlFnlnrolemap delete(GenTlFnlnrolemap genTlFnlnrolemap) throws Exception;
	public abstract List<String[]> getRoleMappingGrid(CommonFilter commonFilter)throws Exception;
	public abstract List<FunctionalLocn> getAllfnLocation(FunctionalLocn functionalLocn)throws Exception;
	public abstract List<GenTlFnlnrolemap> create(List<GenTlFnlnrolemap> genTlFnlnrolemapList)throws Exception;
	public abstract List<String[]> getRoleTeamCntrlGrid(CommonFilter commonFilter)throws Exception;
	public abstract List<GenTlFnlnrolemap> delete(List<GenTlFnlnrolemap> genTlFnlnrolemapList)throws Exception;
	public abstract List<ComboBox> getRoleComboList(ComboFilter roleFilterComboFilter) throws Exception;
	public abstract GenTlFnlnroleteam deleteTeam(GenTlFnlnroleteam genTlFnlnroleteam) throws Exception;
	public abstract List<String[]> getRoleTeamGrid(CommonFilter commonFilter)throws Exception;	
	public abstract List<GenTlFnlnroleteam> createTeam(List<GenTlFnlnroleteam> genTlFnlnroleteamList)throws Exception;
	public abstract List<GenTlFnlnroleteam> deleteTeam(List<GenTlFnlnroleteam> genTlFnlnroleteamList)throws Exception;
	public abstract List<String[]> getRoleTeamEmpGrid(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getRoleMappingMainGrid(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getRoleTeamMainGrid(CommonFilter commonFilter)throws Exception;
	public abstract String SelectOriginalId(String flId)throws Exception;
	public abstract String SelectFnlnName(String flId)throws Exception;
	public abstract List<ComboBox> getTradeComboList(ComboFilter filterComboFilter)throws Exception;
	public abstract List<ComboBox> getProcessComboList(ComboFilter filterComboFilter)throws Exception;
	public abstract List<ComboBox> getSubProcessComboList(ComboFilter roleFilterComboFilter)throws Exception;
	public abstract List<ComboBox> getSubSubProcComboList(ComboFilter filterComboFilter) throws Exception;
	public abstract String getTradeType(String trade)throws Exception;
	public abstract String getSubProcessId(String subSubProcessId)throws Exception;
	public abstract String getProcessId(String subProcessId)throws Exception;
	public abstract List<String[]> getEmpAllList(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getEmpTeamList(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getRoleAllList(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getRoleTeamList(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getRoleTeamAllMainGrid(CommonFilter commonFilter)throws Exception;
	public abstract Workbook getRoleTeamAllExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public abstract String SelectLocation(String flId)throws Exception;
	public abstract List<String[]> getlevelrole(String flid) throws Exception;
	
	/************************new*********************/
	public Workbook getEmployeeRoleLocationExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public Workbook getTransactionSummaryGridDataExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> getEmployeeGridData(CommonFilter commonFilter) throws Exception;
	public List<String[]> getTransactionSummaryGridData(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getlocation(String flid)throws Exception;
	
	public List<ComboBox> getTradeComboListforrole(ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getRoleComboListfortrade(ComboFilter comboFilter) throws Exception;
	public List<String[]> getTradeRoleLinkGrid(CommonFilter commonFilter) throws Exception;
    void saveTradeRoleLink(String tradeId, String roleId, String createdBy) throws Exception;
	void deleteTradeRoleLink(String tradeId, String roleId) throws Exception;


public void GenTlFnlnrolemapServiceImplJwt(String JwtToken);
	
	// added by priyanka
	public List<ComboBox> getPillarComboListforrole(ComboFilter comboFilter) throws Exception;
	
	List<String[]> getPillarRoleLinkGridData(CommonFilter commonFilter, String pillarId, String roleId) throws Exception;
	
	void savePillarRoleLink(String pillarId, String roleId, String createdBy) throws Exception;
	
	void deletePillarRoleLink(String pillarId, String roleId) throws Exception;
	
}

