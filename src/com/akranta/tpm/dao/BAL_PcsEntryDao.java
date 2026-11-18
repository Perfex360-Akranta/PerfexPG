package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_PcsEntryBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PcsTlDtl;
import com.akranta.tpm.model.BAL_PcsTlJhlossDetail;
import com.akranta.tpm.model.BAL_PcsTlLosscapture;
import com.akranta.tpm.model.BAL_PcsTlLossreasonlink;
import com.akranta.tpm.model.BAL_PcsTlMst;
import com.akranta.tpm.model.PcsTlOperatordtl;
import com.akranta.tpm.model.PcsTlWorkorderlink;

public interface BAL_PcsEntryDao {
	
	public List<ComboBox> getCycletimeCombo(String cellId, String machineId, String productId, String entryDate,String sectId) throws Exception;
	public List<String[]> getCycletime(String cellId, String machineId, String productId, String entryDate,String sectId) throws Exception;
	public List<ComboBox> getManpowerCombo(String cellId, String machineId, String productId, String entryDate,String sectId) throws Exception;	
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
	public List<String[]> getPcsRejectionCaptureGrid(String mstKeyid) throws Exception;;	
	public List<String[]> getPcsLossCaptureGrid(String flid, String date, String Todate , String shiftid) throws Exception ;
	public List<String[]> getPcsProductionCaptureGrid(String cellid, String date, String Todate , String shiftid) throws Exception ;
	public List<String[]> getEmployeeGrid(String pldetailsId) throws Exception ;
	
	public List<String[]> getEntryExistsDates(String detailTable, String entryDate, String cellId) throws Exception ;	
	public List<String[]> getHolidayDates(String factId, String entryDate) throws Exception ;
	public List<String[]> getDateOeeValue(String detailTable,String sectId,String cellId, String mchId, String entryDate) throws Exception ;
	
	public List<String[]> getClosedMsr(String prodMchId,String  condParam) throws Exception  ;	
	public List<String[]> getMsrsDownTime(String msrNos) throws Exception  ;
	
	public List<String[]> checkIsOpenMsr(String mchId,String date,String shift) throws Exception  ;
		
	public String getMasterKeyid(String entryDate, String shift, String cellId) throws Exception ;
	public List<String[]>  getLastShiftProduct(String factId, String cellId, String flId, String entryDate, String shift) throws Exception ;
	public String getCurrentShift(String factId) throws Exception ;
	public String getShiftKeyid(String factId, String shiftCode) throws Exception ;
	public List<String[]> getShiftStarEndTime(String shiftId) throws Exception;
	public List<String[]>  getReasonLossNo( String reasonId) throws Exception ;
	public List<String[]>  getSubMainLossNo( String subId) throws Exception ;
	
	public String getCalendarTime(String cellId, String plmasterId, String sectId, String entryDate,String shiftWise) throws Exception ;
	public List<String[]> getAllTimes(String cellId, String plmasterId, String sectId, String entryDate) throws Exception ;
	
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
	
	public abstract BAL_PcsTlMst create(BAL_PcsTlMst pcsTlMst, BAL_PcsTlDtl pcsTlDtl,PcsTlWorkorderlink pcsTlWorkorderlink,   BAL_PcsEntryBean pcsEntryBean) throws Exception;
	public abstract BAL_PcsTlMst createNew(BAL_PcsTlMst pcsTlMst, BAL_PcsTlDtl pcsTlDtl,PcsTlWorkorderlink pcsTlWorkorderlink,   BAL_PcsEntryBean pcsEntryBean) throws Exception;
	public abstract BAL_PcsTlMst update(BAL_PcsTlMst PcsTlMst) throws Exception;	

	public BAL_PcsTlLossreasonlink createLoss(BAL_PcsTlLossreasonlink pcsTlLossreasonlink,String Pldetailsid, String lossId, String lossValue) 	throws Exception ;
	public BAL_PcsTlLossreasonlink updateLoss(BAL_PcsTlLossreasonlink pcsTlLossreasonlink,String Pldetailsid, String lossId, String lossValue) 	throws Exception ;
	
	
	
	public abstract List<String> createLosssql (BAL_PcsTlLossreasonlink pcsTlLossreasonlink,String Pldetailsid, String lossId, String lossValue) 	throws Exception ;
	public abstract List<String> updateLosssql (BAL_PcsTlLossreasonlink pcsTlLossreasonlink,String Pldetailsid, String lossId, String lossValue) 	throws Exception ;

	public String insertNoPlan(String mchId, String date, String shift, String userId, String type, String duration, String frmTime, String toTime) throws Exception;
	
	public abstract PcsTlOperatordtl createPcsEmployee(PcsTlOperatordtl pcsTlOperatordtl,String Plmasterid, String Pldetailsid ) 	throws Exception ;
	
	public List<String[]> getPcsMstDtlids(BAL_PcsTlLosscapture newPcsTlLosscapture, BAL_PcsTlMst pcsTlMst, BAL_PcsTlDtl pcsTlDtl) throws Exception ;
	
	public BAL_PcsTlLosscapture createLossCapture(BAL_PcsTlLosscapture newPcsTlLosscapture, BAL_PcsTlMst pcsTlMst, BAL_PcsTlDtl pcsTlDtl, BAL_PcsTlLossreasonlink pcsTlLossreasonlink, BAL_PcsEntryBean pcsEntryBean) throws BusinessApplicationExceptions , Exception ;
	
	public String getLineRejectionLoss() throws Exception;
	public String deletePcsEntry(String sectId, String plmasterId, String pldetailsId, String workOrderNo, String machKeyId, String entryDate) throws Exception;
	public String deletePcsLossEntryITC(String PlosKeyid, String PlrkKeyid, String Pldetailsid, String sectId, String lossId, String lossVal)  throws Exception;
	public String deletePcsLossEntry(String PlrkKeyid, String Pldetailsid, String cellId, String lossId, String lossVal)  throws Exception;
	public String deletePcsLossEntryBajaj(String PlosKeyid,String PlrkKeyid, String pldetailsid,String cellId, String lossId, String lossVal, String entryDate) throws Exception;
	
	public String deletePcsEmployee(String Pldetailsid, String empId) throws ValidationExceptions, Exception ;
	
	public String isExpansionQtyMandatory() throws Exception;
	public String[] isExpansionQtyNotMandatory(String sectId) throws Exception;
	
	public List<String[]> selectLossRelatedValues(String lossId,String plDetailId) throws Exception;
	public List<String[]> selectDetailIdValues(String productId,String mchId,String sectId,String entryDate,String mstId,String plDetailId) throws Exception;

	public List<String[]> getAllfillgriddata(CommonFilter commonFilter)throws Exception;

	public String getSectionForCell(String cellid)  throws Exception ;
	public String getFlidOriginalid(String flid)  throws Exception ;
	//public String getMachinId(String machineId)  throws Exception ;

	public List<String[]> getShiftEndTime() throws Exception;
	public List<String[]> getAllTimeforHour(String cellId, String shiftId,
			String entryDate, String hourId, String shiftWise) throws Exception;
	public List<String[]> getLossHourTime(String shiftId, String hourId)throws Exception;
	public int getShiftTotaltime(String startdate, String enddate,String shiftId) throws Exception;
	public List<String[]> getJhEquipmnt(CommonFilter commonFilter) throws Exception;
	public List<String[]> getJhEquipmntSpeed(CommonFilter commonFilter)throws Exception;
	public BAL_PcsTlJhlossDetail createJhDetails(
			BAL_PcsTlJhlossDetail newPcsTlJhlossDetail)throws Exception;
	public String getCellid(String cellId)throws Exception;


}

