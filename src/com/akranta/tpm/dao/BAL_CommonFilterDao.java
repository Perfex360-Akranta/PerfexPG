package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;

public interface BAL_CommonFilterDao {
	public List<ComboBox> getCompanyComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getFactoryComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getFlidComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getSectionComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getCellComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getMachineComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> fillComboValues(ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getEmployeeComboList(ComboFilter employee) throws Exception;
	public List<String[]> getMachineHierarchy(String eqpID);//added by mani
	public List<String[]> getCostCenterRelCell(String costCenterID);	
	public List<String[]> getCellHierarchy(String cellID);
	public List<String[]> getSectionHierarchy(String SectionId);
	public List<String[]> getSubUnitHierarchy(String subUnitId);
	public List<String[]> getfactoryHierarchy(String fctId);
	public List<String[]> getlocationHierarchy(String lcnId);
	public List<String[]> getCityHierarchy(String cityId);//added by Karthick.T
	public String getDesigId(String empId) throws Exception;
	public String getspokeid(String progkeyId) throws Exception;
	public DBActionTemplate  getDBActionTemplate();
	public List<GenTlAllmoduleimgfile> saveImg(List<GenTlAllmoduleimgfile> allmoduleimgfiles) throws Exception ;
	public List<ComboBox> getTeamComboList(String funLocCndSql, String empCndSql) throws Exception ;	
	public String getElementID(String originalId) throws Exception ;
	String getFuncLocnHierarchy(String keyid) throws Exception;
	public List<String[]> getEmpData(String userKeyid) throws Exception;
	public List<String[]> getempEqpData(String userID)throws Exception;
	public List<String[]> getMenu(String pillarid, String userId)throws Exception;
	public List<String[]> getPcsLoss(String string)throws Exception;
	public List<String[]> getPilarWiseEmployee(String pillar) throws Exception;
	public List<ComboBox> getOrdertype(ComboFilter comboFilter) throws Exception;
}
