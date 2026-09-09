package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.bean.WOFormBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.model.WomTlWorkorderMst;
import com.akranta.tpm.model.WomsTlTaskmst;

public interface WorkOrderService {
	
	public  WomTlWorkorderMst createNew(WomTlWorkorderMst newWomTlWorkorderMst, WomTlWorkorderMst existWomTlWorkorderMst, WOFormBean woFormBean)throws ValidationExceptions, Exception;
	public  WomTlWorkorderMst updateNew(WomTlWorkorderMst newWomTlWorkorderMst, WomTlWorkorderMst existWomTlWorkorderMst, WOFormBean woFormBean)throws ValidationExceptions, Exception;
	public  WomTlWorkorderMst deleteNew(WomTlWorkorderMst newWomTlWorkorderMst) throws Exception;
	
	public WomTlWomst create(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean ) throws ValidationExceptions, Exception;
	public WomTlWomst update(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception;
	public WomTlWomst updateApproval(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception;
	public WomTlWomst cancelWorkOrder(WomTlWomst womTlWomst)  throws Exception;
	public WomTlWomst updateCreation(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception;
	public WomTlWomst updateAcceptance(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception;
	public WomTlWomst updateAllocation(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception;
	public WomTlWomst updateCompletion(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception;
	public WomTlWomst updateProdAcceptance(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean, List<String[]> overLapFlag )  throws Exception;
	public WomTlWomst delete(WomTlWomst womTlWomst) throws Exception;
	
	public WomsTlTaskmst createTaskList(List<WomsTlTaskmst>womsTlTaskmstList) throws Exception;
	
	public List<String[]> getWorkOrderList(CommonFilter commonFilter) throws Exception;
	
	public List<String[]> getSapEquipmentDetail(String empNo) throws Exception;
	
	public List<String[]> getWorkOrder(CommonFilter commonFilter) throws Exception;
	
	public List<ComboBox> getSapNotificationType(ComboFilter comboFilter) throws Exception ;
	public List<ComboBox> getSapOrderType(ComboFilter comboFilter, String cntrlKey) throws Exception ;
	
	public WomTlWomst select(String keyid) throws Exception;
	public WomTlWorkorderMst selectNew(String keyid) throws Exception;
	public List<ComboBox> getAllSpares(ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getMould(String condSql,String active, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getAlarm(String condSql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getFailureType(String condSql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getPhenomena(String condSql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getCause(String condSql,String phenId,String assmId, ComboFilter comboFilter) throws Exception;
 
	public List<ComboBox> getActivityCombo(String condSql, ComboFilter comboFilter) throws Exception;
 
	
	public List<ComboBox> getSpare(String condSql,String assmId, ComboFilter comboFilter) throws Exception;
	public String getShift(ShiftBean shiftBean);
	public String getYYId(String refDocId);
	public String getRelatedFieldVal(String refDocId,String actType);
	public String checkActivityConfig();
	public WomTlWomst deleteWorkOrder(WomTlWomst womTlWomst) throws Exception;
	public Workbook workOrderExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String format) throws Exception;
	public Workbook workOrderViewExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String format) throws Exception;
	public List<String[]> checkPCSInsertEnable() throws Exception;
	public String checkAssmMand() throws Exception;
	public List<String[]>  checkOverlap(String occuredDate,String prodStartDate,String woId,String actType)  throws Exception;
	public List<String[]> getOrder() throws Exception;
	public List<String[]> getStdWoShMainGrid() throws Exception;
	
	public List<String[]> getOrderExernal() throws Exception;
	public List<String[]> getOrderMainGrid() throws Exception;
	
	public List<String[]> getSapqueue(CommonFilter commonFilter) throws Exception;
	
	public List<String[]> getTaskDetails(CommonFilter commonFilter,	String keyid) throws Exception;
	public List<String[]> getResourceInfo(CommonFilter commonFilter,	String keyid) throws Exception;
	public List<String[]> getOtherCost(CommonFilter commonFilter,	String womsId,String taskId) throws Exception;
	public List<ComboBox> getOtherCostType(ComboFilter comboFilter) throws Exception;
	
	public List<String[]> getSparesDetails(CommonFilter commonFilter,	String keyid) throws Exception;
	public List<ComboBox> getResources(ComboFilter comboFilter, String type, String flid, String others) throws Exception;
	public void reSubmitSAP() throws Exception;
	public String allowUpdate(String womsKeyid, String type) throws Exception;
	public String getLocationBasedMandfield(String loginFlid) throws Exception;
	public String submitMOrder(String woKey, String type) throws Exception;
	public String deleteWO(WomTlWorkorderMst newWomTlWorkorderMst) throws Exception;
	public List<ComboBox> getMaterialGroup(ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getVendor(ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getPurchaseGroup(ComboFilter comboFilter) throws Exception;
	public String getLineNo(String tableName, String condId,String condIdField) throws Exception;
	public void updateControlKey(String womsKey, String cntrlKey) throws Exception;
	public List<ComboBox> getPurchaseOrg(ComboFilter comboFilter) throws Exception;
	public List<String[]> getSapReservationDetail(String keyId) throws Exception;
	public List<String[]> getSapStatus(String womsId, String type) throws Exception;
	public List<ComboBox> getSapRequistions(ComboFilter comboFilter, String flid) throws Exception;
	public List<ComboBox> getEquipmentBOM(String mchId, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getCostcenter(ComboFilter comboFilter, String cellid) throws Exception;
	public List<ComboBox> getWbsElementId(ComboFilter comboFilter,
			String orderType, String workCenter, String controlKey) throws Exception;
	public List<String[]> getPrintHeaderData(String womsKey) throws Exception;
	public List<String[]> getPrintMaterialData(String womsKey, String taskId) throws Exception;
	public String getSubContractCheck(String womsKey, String taskId) throws Exception;
	public int getMatProvIndCnt(String womsKey, String taskId) throws Exception;
	public List<ComboBox> getSapFunctionallocn(ComboFilter comboFilter) throws Exception;
	public List<String[]> getSapFnlnDetail(String flid, String fnlnTxt) throws Exception;
	public List<String[]> getDefSapFnln(String flid, String fnlnTxt) throws Exception;
	
	
}
