package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_PlmTlStandardsFormBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PlmTlCbmstdcadtl;
import com.akranta.tpm.model.BAL_PlmTlMethodtasklist;
import com.akranta.tpm.model.BAL_PlmTlShutdowncal;
import com.akranta.tpm.model.BAL_PlmTlStandards;

public interface BAL_PlmTlStandardsService {
	
	BAL_PlmTlStandards create(BAL_PlmTlStandards newPlmTlStandards,BAL_PlmTlStandards existPlmTlStandards,BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws ValidationExceptions, Exception;

	BAL_PlmTlStandards update(BAL_PlmTlStandards newPlmTlStandards,BAL_PlmTlStandards existPlmTlStandards,BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws Exception;
	
	List<ComboBox> getPmsdTradeCombo(ComboFilter comboFilter) throws Exception;

	List<ComboBox> getpmsdpreparedbyCombo(String condSql, ComboFilter comboFilter) throws Exception;

	List<ComboBox> getpmsdassemblyIdCombo(String condSql, ComboFilter comboFilter) throws Exception;

	//List<ComboBox> getpmsdsubassemblyIdCombo(ComboFilter comboFilter,String assmId) throws Exception;
	List<ComboBox> getpmsdsubassemblyIdCombo(ComboFilter comboFilter, String assmId, String machineId) throws Exception;

	List<ComboBox> getpmsdjobtypeCombo(String condSql, ComboFilter comboFilter)throws Exception;

	List<String[]> getAllsubtype();

	List<String[]> getAllgridData(CommonFilter commonFilter) throws Exception;

	BAL_PlmTlStandards getFillValue(String pmstdKeyid) throws NoDataFoundException, SQLException, Exception;

	List<String[]> getAllsprGriddata(String pmsdkeyid);

	List<ComboBox> getpmsdphenomenaIdCombo(ComboFilter comboFilter) throws Exception;

	List<ComboBox> getpmsdcauseIdCombo(ComboFilter comboFilter) throws Exception;

	List<String[]> getSprPickup(String standardId);

	List<String[]> getactSubValue(String replActSub) throws Exception;

	List<String[]> getAlljhclittools(String toolclisid);

	List<String[]> getchkplnexists(String machorasswise, String machineId);

	List<String[]> getAllgridassmData(CommonFilter commonFilter) throws Exception;

	List<String[]> getassmgridData(CommonFilter commonFilter) throws Exception;

	String delSpares(String pmstdId)throws Exception;

	String delTool(String pmstdId)throws Exception;

	List<String[]> getCBM(String pmStandardId)throws Exception;

	List<ComboBox> getcbmInspectionIdCombo(ComboFilter comboFilter)throws Exception;

	BAL_PlmTlCbmstdcadtl createCBM(BAL_PlmTlCbmstdcadtl newPlmTlCbmstdcadtl,
			BAL_PlmTlCbmstdcadtl existPlmTlCbmstdcadtl,
			BAL_PlmTlStandardsFormBean plmTlStandardsFormBean)throws Exception;

	BAL_PlmTlCbmstdcadtl updateCBM(BAL_PlmTlCbmstdcadtl newPlmTlCbmstdcadtl,
			BAL_PlmTlCbmstdcadtl existPlmTlCbmstdcadtl,
			BAL_PlmTlStandardsFormBean plmTlStandardsFormBean)throws Exception;

	BAL_PlmTlCbmstdcadtl getFillValueCMB(String pmsdId)throws Exception;

	List<String[]> getPermitLinkData(String pmStandardId)throws Exception;

	List<String[]> getfillJobType()throws Exception;

	String sdmGenrateCal(String effectivedate, String factoryid,
			String sectionid, String cellid, String machineid, String string,
			String frequency, String locationid)throws Exception;

	BAL_PlmTlShutdowncal createSDM(BAL_PlmTlShutdowncal newPlmTlShutdowncal,
			BAL_PlmTlShutdowncal existPlmTlShutdowncal,
			BAL_PlmTlStandardsFormBean plmTlStandardsFormBean)throws Exception;

	List<String[]> getfillActivity()throws Exception;

	 

	List<String[]> getCbmGrid(CommonFilter commonFilter)throws Exception;
//PM Report
	public List<String[]> getPMReport(CommonFilter commonFilter)throws Exception;
	public List<String[]> getMethodTaskList(CommonFilter commonFilter)throws Exception;

	BAL_PlmTlMethodtasklist createMethodTask( BAL_PlmTlMethodtasklist newPlmTlMethodtasklist)throws Exception;
	
	// To Copy Standards from one machine to other by sugu
	List<String[]> getEquipmentList(CommonFilter commonFilter)throws Exception;

	String getEquipflid(CommonFilter commonFilter)throws Exception;

	List<String[]> copyStandards(List<String> eqlist, List<String> actlst, String elementid, String tradeid) throws Exception;

	Workbook stdExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,
			String format) throws Exception;

	// Delete the Standards by Kiran 
	String delteStandards(String detailKeyid,String cellId, String machineId)throws Exception;
	
	List<BAL_PlmTlStandards> createMultiple(
	        List<BAL_PlmTlStandards> newList,
	        BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws ValidationExceptions, Exception;

	List<BAL_PlmTlStandards> updateMultiple(
	        List<BAL_PlmTlStandards> newList,
	        List<BAL_PlmTlStandards> existList,   // same size/order as newList
	        BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws ValidationExceptions, Exception;
	  public void BAL_PlmTlStandardsServiceImplJwt(String JwtToken);
	  
		    List<String[]> getMultiplePmsdList(CommonFilter commonFilter) throws Exception;
		    BAL_PlmTlStandards delete(BAL_PlmTlStandards plmTlStandards) throws Exception;
	
}
