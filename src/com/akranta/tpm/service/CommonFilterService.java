package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;

public interface CommonFilterService {

	public List<ComboBox> getCompanyComboList(CommonFilter commonFilter,String keyid) throws Exception;
	public List<ComboBox> getFactoryComboList(CommonFilter commonFilter,String keyid, String lcnid, String type) throws Exception;
	public List<ComboBox> getSectionComboList(CommonFilter commonFilter,String keyid, String factoryId) throws Exception;
	public List<ComboBox> getCellComboList(CommonFilter commonFilter, String keyid, String sectionid) throws Exception;
	public List<ComboBox> getSubCellComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getMachineComboList(CommonFilter commonFilter, String keyid, String cellid) throws Exception;
	public List<ComboBox> getCostCentreComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getLocationComboList(CommonFilter commonFilter,String keyid) throws Exception;
	public List<ComboBox> getEmployeeComboList(ComboFilter currentFilter, String rtal) throws Exception;
	public List<ComboBox> getUomComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getAssemblyComboList(CommonFilter commonFilter,String relatedTo)	throws Exception ;
	public List<String[]> getMachineHierarchy(String eqpID);
	public List<ComboBox> getMould(String eqpID,ComboFilter mouldComboFilter);
	public List<ComboBox> getBreakdown();
	public List<ComboBox> getMSR(ComboFilter currentFilter, String activityType);
	public List<String[]> getCostCenterRelCell(String costCenterID);	
	public List<String[]> getCellHierarchy(String cellId);
	public List<String[]> getSectionHierarchy(String SectionId);	
	public List<String[]> getCityHierarchy(String cityId);//created karthick.t
	public List<String[]> getSubUnitHierarchy(String subUnitId);
	public List<ComboBox> getEqpGroupComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getCircleComboList(CommonFilter commonFilter)throws Exception;
	//public List<ComboBox> getCircleComboList(CommonFilter commonFilter,ComboFilter circle)	throws Exception ;
	public List<ComboBox> getTradeComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getMachineRankComboList(CommonFilter commonFilter)throws Exception;
	public List<GenTlAllmoduleimgfile> saveImg(List<GenTlAllmoduleimgfile> allmoduleimgfiles,String refKeyId,String refDocType) throws Exception;
	public List<ComboBox> getJhAuditLevelIdComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getJhAuditComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getProdcngroupComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getOplNoComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getSubassemblyComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getSpareComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getGrpByCellComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getImprovmntNoComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getPillarComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getShiftComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getFailTypeComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getCauseComboList(CommonFilter commonFilter,String phenomena)throws Exception;
	public List<ComboBox> getPhenomenaComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getShiftInchargeComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getYyComboList(CommonFilter commonFilter)throws Exception;
	public List<String[]> getfactoryHierarchy(String fctId);
	public List<String[]> getlocationHierarchy(String lcnId);
	public List<ComboBox> getBdRootCauseComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getProductComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getMachineAreaComboList(CommonFilter commonFilter ) throws Exception;//ADDED BY MANI-14.03.12
	public List<ComboBox> getLossComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getComplaintnoComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getCustIDComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getPartnoComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getproductComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getDefactparamComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getRecordedbyComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getinspectionComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getDefactPhenamenComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getProcessComboList(CommonFilter commonFilter, String originalid)throws Exception;;
	public List<ComboBox> getQtmCauseComboList(CommonFilter commonFilter)throws Exception;
	public String getDesigId(String empId) throws Exception;
	public List<ComboBox> getRawMatrialComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getsupplierComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getproductModelComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getWorkOrderComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getDesignationComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getProgramComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getPgmBenefitComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getBatchComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getPgmnoComboList(CommonFilter commonFilter)throws Exception;
	public String getspokekeyId(String progkeyId) throws Exception;
	public List<ComboBox> getSkillComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getSkillRatingComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getRoleComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getskillTypeComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getTopicComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getAccidentNoComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getInjurymodeComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getBodypartComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getJhAuditNameComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getJhAuditLevelComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getSpokeComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getTopicsComboList(CommonFilter commonFilter,
			String spokeid, String trarkeyid) throws Exception;
	public List<ComboBox> getKaizenNoComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getThemeCombo() throws Exception;
	public List<ComboBox> getTopicCombo(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getJHKaizenCategoryComboList(CommonFilter commonFilter)throws Exception;

	public List<ComboBox> getJhStepComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getManagerComboList(CommonFilter commonFilter)throws Exception;

	//team
	public List<ComboBox> getTeamComboList(String funLocCndSql, String empCndSql) throws Exception; //, ComboFilter teamComboFilter
	public List<ComboBox> getEqpSubGroupComboList(CommonFilter commonFilter)throws Exception;/* Added ByDhanalakshmi.R*/
	public List<ComboBox> getSeverityCombo() throws Exception;
	public List<ComboBox> getProbableCombo() throws Exception;

	public List<ComboBox> getGradeSpecComboList(CommonFilter commonFilter, ComboFilter comboFilter) throws Exception;
	
	public List<ComboBox> getCustomerComboList(CommonFilter commonFilter) throws Exception;
	public List<String[]> getSbuHierarchy(String sbuId);
	public String getFuncLocnHierarchy(String sbuId)throws Exception;
	public List<ComboBox> getSbuComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getPbuComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
 	//Department
	List<ComboBox> getDepartmentCombo(CommonFilter commonFilter) throws Exception;
	public List<String[]> getEmpData(String userKeyid)throws Exception;
	public List<String[]> getempEqpData(String userID)throws Exception;
	public List<String[]> getMenu(String pillarid, String usrm_keyid)throws Exception;
	public List<String[]> getPcsLoss(String string)throws Exception;
	public List<String[]> getPilarWiseEmployee(String pillar)throws Exception;
//	public List<ComboBox> getTeamComboList(String funLocCndSql,
//			String empCndSql, ComboFilter teamComboFilter);
	//public List<ComboBox> getPillarComboList(CommonFilter commonFilter,ComboFilter pillarComboFilter);
	public List<ComboBox> getTargetGrpComboList(ComboFilter currentFilter,
			String string)throws Exception;
	
	public List<ComboBox> getEffectiveeComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getReason(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getproductSLAComboList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getFacultyComboList(CommonFilter commonFilter, ComboFilter currentFilter)throws Exception;
	public List<ComboBox> getSubProcessComboList(ComboFilter currentFilter, String processId) throws Exception;
	public List<ComboBox> getSourceOFKPI(CommonFilter commonFilter,	ComboFilter currentFilter) throws Exception;
	public List<ComboBox> getCheckType(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getWorkCentreComboList(CommonFilter commonFilter, ComboFilter currentFilter)throws Exception;
	public List<ComboBox> getPlannerGroupComboList(CommonFilter commonFilter, ComboFilter currentFilter)throws Exception;
	public List<ComboBox> getFlidComboList(CommonFilter commonFilter)throws Exception;
	
	/*
	 * To Get All the Role
	 */
	
	public List<ComboBox> getRoleComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getCellComboList(CommonFilter commonFilter,
			String keyid, String sectionid, String flid) throws Exception;	
	public List<ComboBox> getKaizenThemeCategory(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getETTradeComboList(CommonFilter commonFilter) throws Exception;
	public String getEmployeeLocation(String userKeyid)throws Exception;
	
	public List<ComboBox> getMocItemComboList(CommonFilter commonFilter)throws Exception;
}
