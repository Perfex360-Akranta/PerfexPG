package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.BAL_BDFormBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_SapTlMaintenanceOrderdtl;
// com.akranta.tpm.model.SapTlSparesreplaced;
import com.akranta.tpm.model.BAL_SapTlSparesreplaced;

public interface BAL_SAPInfoService {
	public List<String[]> getSAPInfoList(CommonFilter commonFilter) throws Exception;

	public List<String[]> getSAPinfofillgriddata() throws Exception;

	public List<String[]> getSAPStoragefillgriddata()throws Exception;

	public List<String[]> getSAPResultfillgriddata(String type)throws Exception;

	public BAL_SapTlMaintenanceOrderdtl createSapSpareinfo( BAL_SapTlMaintenanceOrderdtl newSapTlMaintenanceOrderdtl,
			BAL_SapTlMaintenanceOrderdtl existSapTlMaintenanceOrderdtl, BAL_BDFormBean bdFormBean)throws Exception;

	public String updateSparesQty(List<BAL_SapTlSparesreplaced> sparesReplaceList) throws Exception;
	public BAL_SapTlSparesreplaced createSapSpareReplaced( BAL_SapTlSparesreplaced newSapTlSparesreplaced,
			BAL_SapTlSparesreplaced existSapTlSparesreplaced)throws Exception;
	public List <ComboBox> getOrdertype(String docType, ComboFilter comboFilter) throws Exception;
	
	public List<String[]>  getAllCrmmasterData() throws Exception;
	public List<String[]> getCrmBillItemsData(String crmNo) throws Exception;
	public List<String[]> getCrmNotesData(String crmNo) throws Exception;
	public List<String[]> getDownTimeData(CommonFilter commonFilter) throws Exception;

	public List<String[]> getFillGrid(CommonFilter commonFilter)throws Exception;
	public List<String[]> getNotificationData() throws Exception;

	public List<String[]> getFillDownTimeGrid(CommonFilter commonFilter)throws Exception;
	public List<String[]> getFillDownTimeReasonsGrid(CommonFilter commonFilter)throws Exception;

	public List<String[]> getHRMSFillGrid(CommonFilter commonFilter, String mode)throws Exception;

	public List<String[]> getHRMSEmployeeFillGrid(CommonFilter commonFilter)throws Exception;

	public List<String[]> getHRMSShiftFillGrid(CommonFilter commonFilter)throws Exception;
	public List<String[]> getOperationGrid(String orderNo) throws Exception;
	
	public List<String[]> getMaterialGrid(String orderNo) throws Exception;
	
	
	public Workbook getMaintExcel(JSONObject colmodel, String format, CommonFilter commonFilter) throws Exception;
	public int getDownTimeCount(CommonFilter commonFilter) throws Exception;
	
	public Workbook getHrmsExportExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter, String mode)throws Exception;

	public Workbook getShiftExportExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter, String mode)throws Exception;

	public Workbook getEmployeemstExportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter)throws Exception;

	public List<String[]> getNPCFillGrid(CommonFilter commonFilter, String mode)throws Exception;

	public List<String[]> getREPULPFillGrid(CommonFilter commonFilter,String mode)throws Exception;

	public List<String[]> getTaskListFillGrid(CommonFilter commonFilter,
			String mode)throws Exception;

	public List<String[]> getProductionDetailsFillGrid(
			CommonFilter commonFilter, String mode)throws Exception;

	public List<String[]> getMaterialMasterFillGrid(CommonFilter commonFilter
			)throws Exception;

	
	public String delteSparesDetail(List<BAL_SapTlSparesreplaced> sparesReplaceList) throws Exception;

	public String delteSparesDetail(String keyId) throws Exception;

	//public String delteSparesDetail(String keyId)throws Exception;
}

