package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.bean.WOFormBean;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.BdmTlMst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MldTlMouldunloadmst;
import com.akranta.tpm.model.PlmTlGenmaintenance;
import com.akranta.tpm.model.WomTlWomst;

public interface WomTlWomstDao {

	public abstract WomTlWomst create(WomTlWomst womTlWomst) throws Exception;
	public abstract WomTlWomst update(WomTlWomst womTlWomst) throws Exception;
	public WomTlWomst updateApproval(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception;
	public WomTlWomst cancelWorkOrder(WomTlWomst womTlWomst)  throws Exception;
	public WomTlWomst updateCreation(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean,BdmTlMst bdmTlMst)  throws Exception;
	public WomTlWomst updateAcceptance(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean ,BdmTlMst bdmTlMst)  throws Exception;
	public WomTlWomst updateAllocation(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean,BdmTlMst bdmTlMst,AbnTlAbnormality abnTlAbnormality,PlmTlGenmaintenance plmTlGenmaintenance,MldTlMouldunloadmst mldTlMouldunloadmst )  throws Exception;
	public WomTlWomst updateCompletion(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean,BdmTlMst bdmTlMst )  throws Exception;
	public WomTlWomst updateProdAcceptance(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean ,List<String[]> overLapFlag)  throws Exception;
	public abstract WomTlWomst delete(WomTlWomst womTlWomst) throws Exception;
	
	public  List<String[]> getWorkOrderList(CommonFilter commonFilter) throws Exception;
	public List<String[]> getSapEquipmentDetail(String empNo) throws Exception ;
	
	public List<String[]> getWorkOrder(CommonFilter commonFilter) throws Exception;
	public WomTlWomst select(String keyid) throws Exception;
	public String getShift(ShiftBean shiftBean);
	public String getYYId(String refDocId);
	public String getRelatedFieldVal(String refDocId,String actType);
	public String checkActivityConfig();
	public Workbook workOrderRpt(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;
	public Workbook workOrderViewRpt(CommonFilter commonFilter, JSONObject colmodel,String rptFormat)throws Exception;
	public List<ComboBox> fillComboValues(ComboFilter comboFilter) throws Exception;
	public List<String[]> checkPCSInsertEnable() throws Exception;
	public String checkAssmMand() throws Exception;
	public List<String[]>  checkOverlap(String occuredDate,String prodStartDate,String woId,String actType)  throws Exception;
	public abstract List<String[]> getOrder() throws Exception;
	public abstract List<String[]> getStdWoShMainGrid() throws Exception;
	public abstract List<String[]> getOrderMainGrid() throws Exception;
	public abstract List<String[]> getOrderExernal() throws Exception;

	public abstract List<String[]> getSapqueue(CommonFilter commonFilter) throws Exception;

	public List<String[]> getTaskDetails(CommonFilter commonFilter,	String keyid) throws Exception ;
	public List<String[]> getSparesDetails(CommonFilter commonFilter,	String keyid) throws Exception ;
	public List<String[]> getResourceInfo(CommonFilter commonFilter,	String keyid) throws Exception ;
	public List<String[]> getOtherCost(CommonFilter commonFilter,	String keyid, String taskId) throws Exception ;
	public abstract void reSubmitSAP() throws Exception;
	public void updateControlKey(String womsKey, String cntrlKey) throws Exception;
	public abstract List<String[]> getSapReservationDetail(String keyId) throws Exception;
	public abstract List<String[]> getSapStatus(String womsId, String type) throws Exception;

}

