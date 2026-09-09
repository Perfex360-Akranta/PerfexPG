/*Created By : Siddharth.A*/
package com.akranta.tpm.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsEntryBean;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlDtl;
import com.akranta.tpm.model.PcsTlLosscapture;
import com.akranta.tpm.model.PcsTlLossreasonlink;
import com.akranta.tpm.model.PcsTlMst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.PcsTlOperatordtl;
import com.akranta.tpm.model.PcsTlOtherlossentry;
import com.akranta.tpm.model.PcsTlWorkorderlink;
import com.akranta.tpm.upload.UploadException;

public interface PcsEntryService {

	
	public List<String[]> getCalendar(String factId, String date,String userId)throws Exception;
	public List<String[]> getPCSCalendar(String factId, String date, String userId,String sectionId,String cellId)throws Exception;
	public List<String[]> getParameters(String sectId)throws Exception;
	public List<String[]> getEntryGrid(String date,String shift,String sectId, String mchId) throws Exception;
	public List<String[]> getSubLossGrid(String pldetailsId)throws Exception;
	public List<String[]> getSubLossGridNew(String pldetailsId) throws Exception ;

	public List<String[]> getPcsLossCaptureGrid(String flid, String date,String todate ,String shiftid) throws Exception ;
	public List<String[]> getPcsResultGrid(String entryDate,String shift,String sectId,String cellId, String mchId)throws Exception;
	public List<String[]> getEmployeeGrid(String pldetailsid) throws Exception ;

	public List<String[]> getProdLoss(String shiftId,String entryDate,String sectId,String cellId, String mchId,String factId)throws Exception;
	public List<String[]> getProductDetail(String shiftId,String entryDate,String cellId,String sectionId, String mchId,String dtlId) throws Exception;
	public List<ComboBox> getOperatorCombo(ComboFilter currentFilter , String sectId) throws Exception;
	public List<ComboBox> getproductModelCombo(ComboFilter currentFilter , String factId, String mchId, String entryDate) throws Exception;
	public List<ComboBox> getShift(ComboFilter currentFilter , String factId) throws Exception;
	public List<String[]> getIsPcsEnabled(String condParam) throws Exception;
	public List<String[]> checkEquipmentFailure(String lossId) throws Exception;
	public List<String[]> checkIsQtyLoss(String lossId) throws Exception;
	public String getPldRemarks(String detailTable,String pldetailsId ) throws Exception;
	public List<String[]> getEntryAllowDates() throws Exception;
	public String getFieldsForPcs() throws Exception;
	
	public List<ComboBox> getproductCombo(ComboFilter currentFilter , String factId, String mchId,  String prdModelId,String entryDate,
				String rawMaterial ) throws Exception;
	
	public List<String[]> getEntryExistsDates(String detailTable, String entryDate, String cellId) throws Exception;
	public List<String[]> getHolidayDates(String factId, String entryDate) throws Exception;
	public List<String[]> getDateOeeValue(String detailTable,String sectId,String cellId, String mchId,  String entryDate) throws Exception ;
	public List<String[]> getPendingTime(String detailTable,String pldetailsId) throws Exception; 
	
	public String getSelectModel(String prdId) throws Exception;
	
	public List<String[]> getClosedMsr(String prodMchId,String condParam) throws Exception  ;
	public List<String[]> getMsrsDownTime(String msrNos) throws Exception ;
	
	public List<String[]> checkIsOpenMsr(String mchId,String date,String shift) throws Exception ;
	public String getMasterKeyid(String entryDate, String shift, String cellId) throws Exception;
	public List<String[]>  getLastShiftProduct(String sectId, String cellId, String mchId, String entryDate, String shift) throws Exception;
	
	public String getCurrentShift(String factId) throws Exception ;
	public String getShiftKeyid(String factId, String shiftCode) throws Exception;
	public List<String[]> getShiftStarEndTime( String shiftId) throws Exception;
	public List<String[]>  getReasonLossNo( String reasonId) throws Exception ;
	public String getCalendarTime(String machineId, String plmasterId, String sectId, String entryDate) throws Exception;
	public List<String[]> getAllTimes(String machineId, String plmasterId, String sectId, String entryDate) throws Exception ;
	public String getDetailTableName(String sectId, String entryDate) throws Exception;
	
	public List<ComboBox> getLossCombo(ComboFilter currentFilter , String isQtyLoss, String sectId) throws Exception;
	public List<ComboBox> getLossNoCombo(ComboFilter currentFilter , String isQtyLoss, String sectId) throws Exception;
	public List<ComboBox> getWno(ComboFilter currentFilter , String sectId,String Pldetailsid, String entryDate) throws Exception;

	public List<ComboBox> getRawType(ComboFilter currentFilter , String productId) throws Exception ;
	public List<ComboBox> getQTYPhenomena(ComboFilter currentFilter) throws Exception;
	public List<ComboBox> getCause(ComboFilter currentFilter , String isQtyLoss, String parentId) throws Exception;
	public List<ComboBox> getRootCause(ComboFilter currentFilter ) throws Exception;
	public List<ComboBox> getPhenomenaCombo(ComboFilter currentFilter,String lossId,String cellid,String flid) throws Exception;
	
	public List<ComboBox> getCycletimeCombo(ComboFilter currentFilter , String cellId, String machineId, String productId, 
			String entryDate, String sectId) throws Exception;
	
	public String getActTimePct() throws Exception;
	public String getLossNo(String plcmKeyid) throws Exception ;
	
	public String updatePldRemarks(String detailTable, String plDetailsid, String remarks) throws Exception;
	
	
	public PcsTlMst create(PcsTlMst newPcsTlMst,PcsTlDtl newPcsTlDtl, PcsTlWorkorderlink newPcsTlWorkorderlink, PcsEntryBean pcsEntryBean ) throws ValidationExceptions, Exception;
	public PcsTlMst createNew(PcsTlMst newPcsTlMst,PcsTlDtl newPcsTlDtl, PcsTlWorkorderlink newPcsTlWorkorderlink, PcsEntryBean pcsEntryBean ) throws ValidationExceptions, Exception;
	public PcsTlMst update(PcsTlMst newPcsTlMst,PcsTlMst oldPcsTlMst,  PcsEntryBean pcsEntryBean ) throws ValidationExceptions, Exception;

	public String insertNoPlan(String mchId, String date, String shift, String userId, String type, String duration, String fromTme, String toTime) throws Exception ;	
	
	public PcsTlLossreasonlink createLoss(PcsTlLossreasonlink newPcsTlLossreasonlink,  PcsTlLossreasonlink oldPcsTlLossreasonlink, PcsEntryBean pcsEntryBean) 	throws Exception ;
	public PcsTlLossreasonlink updateLoss(PcsTlLossreasonlink newPcsTlLossreasonlink,  PcsTlLossreasonlink oldPcsTlLossreasonlink, PcsEntryBean pcsEntryBean) 	throws Exception ;
	
	public PcsTlOperatordtl createPcsEmployee(PcsTlOperatordtl newPcsTlOperatordtl,  PcsEntryBean pcsEntryBean) 	throws Exception ;
	public PcsTlLosscapture createLossCapture(PcsTlLosscapture newPcsTlLosscapture,  PcsEntryBean pcsEntryBean) 	throws Exception ;
	public String getLineRejectionLoss() throws Exception;   
	public String deletePcsEntry(String sectId, String plmasterId, String pldetailsId,String workOrderNo, String machKeyId, String entryDate) throws ValidationExceptions, Exception;
	public String deletePcsLossEntryITC(String PlosKeyid, String PlrkKeyid, String Pldetailsid, String sectId, String lossId, String lossVal)  throws Exception ;
	public String deletePcsLossEntry(String PlrkKeyid, String Pldetailsid, String sectId, String lossId, String lossVal) throws ValidationExceptions, Exception;
	public String deletePcsEmployee(String Pldetailsid, String empId) throws ValidationExceptions, Exception;
	
	public String[] isExpansionQtyNotMandatory(String sectId) throws Exception;
	
	public List<String[]> selectLossRelatedValues(String lossId,String plDetailId) throws Exception;
	public List<String[]> selectDetailIdValues(String productId,String mchId,String sectId,String entryDate,String mstId,String plDetailId) throws Exception;
	
	public List<ComboBox> getPcsLossCause(ComboFilter currentFilter , String isQtyLoss, String parentId) throws Exception;
	public List<String[]> getAllfillgriddata(CommonFilter commonFilter) throws Exception;
	public String populateTempTable(String excelFileName,
			PcsTlOtherlossentry pcsTlOtherlossentry) throws UploadException, ValidationExceptions, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException;
	public String updateLossVal(List<PcsTlOtherlossentry> otherLossList) throws BusinessApplicationExceptions, Exception;
	public List<String[]> getShiftEndTime()throws Exception;
	public List<String[]> getLossEntryData(GridParams gridParams, CommonFilter commonFilter, String flid2) throws Exception;
	public String getTotalLossEntryData(String flid) throws Exception;
	public Workbook getExcelreport(CommonFilter commonFilter,
			JSONObject jsonobj, String formats) throws Exception;
	public PcsTlMst deletePcsLossEntryITCNew(String plrkKeyid,String pldetailsid, String sectId)throws Exception;
	public List<ComboBox> getEquipmentName(ComboFilter combofilter) throws Exception;
	
	public void PcsEntryServiceImplJwt(String string);

}
