package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsEntryBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlDtl;
import com.akranta.tpm.model.PcsTlLosscapture;
import com.akranta.tpm.model.PcsTlLossreasonlink;
import com.akranta.tpm.model.PcsTlMst;
import com.akranta.tpm.model.PcsTlOperatordtl;
import com.akranta.tpm.model.PcsTlWorkorderlink;

public interface PcsEntryDao {
	
	public List<ComboBox> getCycletimeCombo(String cellId, String machineId, String productId, String entryDate,String sectId) throws Exception;
	
	public String getActTimePct() throws Exception;
	public String getLossNo(String plcmKeyid) throws Exception ;
	
	public List<String[]> getCalendar(String factId, String date,String userId)throws Exception;
	public List<String[]> getPCSCalendar(String factId, String date, String userId,String sectionId,String cellId)throws Exception;
	public List<String[]> getParameters(String sectId)throws Exception;
	
	public List<String[]> getProdLoss(String shiftId,String entryDate,String sectId,String cellId, String mchId,String factid) throws Exception;
	public List<String[]> getProductDetail(String shiftId,String entryDate,String cellId,String sectionId, String mchId,String dtlId) throws Exception;
	public List<String[]> getEntryGrid(String date,String shift,String sectId, String mchId) throws Exception;
	public List<String[]> getSubLossGrid(String pldetailsId) throws Exception ;
	public List<String[]> getSubLossGridNew(String pldetailsId) throws Exception;
	public List<String[]> getPcsResultGrid(String entryDate,String shift,String sectId,String cellId, String mchId)throws Exception ;
	
	public List<String[]> getPcsLossCaptureGrid(String flid, String date, String Todate , String shiftid) throws Exception ;
	
	public List<String[]> getEmployeeGrid(String pldetailsId) throws Exception ;
	
	public List<String[]> getEntryExistsDates(String detailTable, String entryDate, String cellId) throws Exception ;	
	public List<String[]> getHolidayDates(String factId, String entryDate) throws Exception ;
	public List<String[]> getDateOeeValue(String detailTable,String sectId,String cellId, String mchId, String entryDate) throws Exception ;
	
	public List<String[]> getClosedMsr(String prodMchId,String  condParam) throws Exception  ;	
	public List<String[]> getMsrsDownTime(String msrNos) throws Exception  ;
	
	public List<String[]> checkIsOpenMsr(String mchId,String date,String shift) throws Exception  ;
		
	public String getMasterKeyid(String entryDate, String shift, String cellId) throws Exception ;
	public List<String[]>  getLastShiftProduct(String sectId, String cellId, String mchId, String entryDate, String shift) throws Exception ;
	public String getCurrentShift(String factId) throws Exception ;
	public String getShiftKeyid(String factId, String shiftCode) throws Exception ;
	public List<String[]> getShiftStarEndTime(String shiftId) throws Exception;
	public List<String[]>  getReasonLossNo( String reasonId) throws Exception ;
	public String getCalendarTime(String machineId, String plmasterId, String sectId, String entryDate) throws Exception ;
	public List<String[]> getAllTimes(String machineId, String plmasterId, String sectId, String entryDate) throws Exception ;
	
	public String getDetailTableName(String sectId, String entryDate) throws Exception ;
	public List<String[]> getIsPcsEnabled(String condParam) throws Exception ;
	public List<String[]> checkEquipmentFailure(String lossId) throws Exception ;
	public List<String[]> checkIsQtyLoss(String lossId) throws Exception ;
	
	public String getPldRemarks(String detailTable,String pldetailsId ) throws Exception ;
	public List<String[]> getEntryAllowDates() throws Exception ;
	
	public List<String[]> getPendingTime(String detailTable,String pldetailsId) throws Exception ;
	public String getSelectModel(String prdId) throws Exception  ;
	
	public String updatePldRemarks(String detailTable, String plDetailsid, String remarks) throws Exception ;
	
	
	public String getIsHourlyEntry() throws Exception ;
	public String getFieldsForPcs() throws Exception ;
	public String CheckConf() throws Exception ;
	
	public String getMouldSectionCode() throws Exception ;
	public String getSectionCode(String sectId) throws Exception ;
	
	public abstract PcsTlMst create(PcsTlMst pcsTlMst, PcsTlDtl pcsTlDtl,PcsTlWorkorderlink pcsTlWorkorderlink,   PcsEntryBean pcsEntryBean) throws Exception;
	public abstract PcsTlMst createNew(PcsTlMst pcsTlMst, PcsTlDtl pcsTlDtl,PcsTlWorkorderlink pcsTlWorkorderlink,   PcsEntryBean pcsEntryBean) throws Exception;
	public abstract PcsTlMst update(PcsTlMst PcsTlMst) throws Exception;	

	public PcsTlLossreasonlink createLoss(PcsTlLossreasonlink pcsTlLossreasonlink,String Pldetailsid, String lossId, String lossValue) 	throws Exception ;
	public PcsTlLossreasonlink updateLoss(PcsTlLossreasonlink pcsTlLossreasonlink,String Pldetailsid, String lossId, String lossValue) 	throws Exception ;
	
	
	
	public abstract List<String> createLosssql (PcsTlLossreasonlink pcsTlLossreasonlink,String Pldetailsid, String lossId, String lossValue) 	throws Exception ;
	public abstract List<String> updateLosssql (PcsTlLossreasonlink pcsTlLossreasonlink,String Pldetailsid, String lossId, String lossValue) 	throws Exception ;

	public String insertNoPlan(String mchId, String date, String shift, String userId, String type, String duration, String frmTime, String toTime) throws Exception;
	
	public abstract PcsTlOperatordtl createPcsEmployee(PcsTlOperatordtl pcsTlOperatordtl,String Plmasterid, String Pldetailsid ) 	throws Exception ;
	
	public List<String[]> getPcsMstDtlids(PcsTlLosscapture newPcsTlLosscapture, PcsTlMst pcsTlMst, PcsTlDtl pcsTlDtl) throws Exception ;
	
	public PcsTlLosscapture createLossCapture(PcsTlLosscapture newPcsTlLosscapture, PcsTlMst pcsTlMst, PcsTlDtl pcsTlDtl, PcsTlLossreasonlink pcsTlLossreasonlink) throws Exception ;
	   
	public String getLineRejectionLoss() throws Exception;
	public String deletePcsEntry(String sectId, String plmasterId, String pldetailsId, String workOrderNo, String machKeyId, String entryDate) throws Exception;
	public String deletePcsLossEntryITC(String PlosKeyid, String PlrkKeyid, String Pldetailsid, String sectId, String lossId, String lossVal)  throws Exception;
	public String deletePcsLossEntry(String PlrkKeyid, String Pldetailsid, String sectId, String lossId, String lossVal)  throws Exception;
	public String deletePcsEmployee(String Pldetailsid, String empId) throws ValidationExceptions, Exception ;
	
	public String isExpansionQtyMandatory() throws Exception;
	public String[] isExpansionQtyNotMandatory(String sectId) throws Exception;
	
	public List<String[]> selectLossRelatedValues(String lossId,String plDetailId) throws Exception;
	public List<String[]> selectDetailIdValues(String productId,String mchId,String sectId,String entryDate,String mstId,String plDetailId) throws Exception;

	public List<String[]> getAllfillgriddata(CommonFilter commonFilter)throws Exception;

	public String getSectionForCell(String cellid)  throws Exception ;
	public String getFlidOriginalid(String flid)  throws Exception ;

	public List<String[]> getShiftEndTime() throws Exception;

	public List<String[]> getLossEntryData(GridParams gridParams, CommonFilter commonFilter, String flid) throws Exception;

	public String getTotalLossEntryData(String flid) throws Exception;

	public Workbook getExcelreport(CommonFilter commonFilter,
			JSONObject tblJSONObj, String formats,GridParams gridParams) throws Exception;

	public PcsTlMst deletePcsLossEntryITCNew(String plrkKeyid,String pldetailsid, String sectId)throws Exception;
	
	public abstract void PcsEntryDaoImplJwt(String jwtToken);
	
}

