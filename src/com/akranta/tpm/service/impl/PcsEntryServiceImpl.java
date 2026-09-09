
/*Created By : BABU.D*/
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsEntryBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.PcsEntryDao;
import com.akranta.tpm.dao.PcsTlLosscaptureDao;
import com.akranta.tpm.dao.PcsTlMstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.PcsEntryDaoImpl;
import com.akranta.tpm.dao.impl.PcsTlLosscaptureDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlAncilliarytime;
import com.akranta.tpm.model.PcsTlDtl;
import com.akranta.tpm.model.PcsTlLosscapture;
import com.akranta.tpm.model.PcsTlLossreasonlink;
import com.akranta.tpm.model.PcsTlMst;
import com.akranta.tpm.model.PcsTlOperatordtl;
import com.akranta.tpm.model.PcsTlOtherlossentry;
import com.akranta.tpm.model.PcsTlWorkorderlink;
import com.akranta.tpm.service.PcsEntryService;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.service.api.PcsEntryServiceApi;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class PcsEntryServiceImpl implements PcsEntryService {
	
	private PcsEntryDao pcsEntryDao ;
	private PcsTlLosscaptureDao pcsTlLosscaptureDao ;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	private PcsEntryServiceApi  pcsEntryServiceApi;
	
	
	public PcsEntryServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pcsEntryDao = new PcsEntryDaoImpl(dbActionTemplate);
		pcsTlLosscaptureDao = new PcsTlLosscaptureDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
 	}
	
	public void PcsEntryServiceImplJwt(String JwtToken){
	    try{
	    //	oplTlMstDao.OplTlMstDaoImplJwt(JwtToken);   // dao side
	        // (Optional) if you want service-level direct access
	    	pcsEntryServiceApi = new PcsEntryServiceApi(JwtToken);
	    	pcsEntryDao.PcsEntryDaoImplJwt(JwtToken);
	    	
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	public List<String[]> getCalendar(String factId, String date,String userId)throws Exception {
		return this.pcsEntryDao.getCalendar(factId, date,userId);
	}
	public List<String[]> getPCSCalendar(String factId, String date, String userId,String sectionId,String cellId)throws Exception
	{
		return this.pcsEntryDao.getPCSCalendar(factId, date, userId, sectionId, cellId);
	}
	@Override
	public List<String[]> getParameters(String sectId) throws Exception {
		// TODO Auto-generated method stub
		return this.pcsEntryDao.getParameters(sectId);
	}
	public List<String[]> getEntryGrid(String date,String shift,String sectId, String mchId) throws Exception {
		return this.pcsEntryDao.getEntryGrid(date,shift,sectId,mchId);
	}
	
	public List<String[]> getEmployeeGrid(String pldetailsId) throws Exception {
		return this.pcsEntryDao.getEmployeeGrid(pldetailsId);
	}
	
	public List<String[]> getProdLoss(String shiftId,String entryDate,String sectId,String cellId, String mchId,String factid) throws Exception {
		// TODO Auto-generated method stub
		return this.pcsEntryDao.getProdLoss(shiftId,entryDate,sectId, cellId, mchId,factid);
	}
	public List<String[]> getProductDetail(String shiftId,String entryDate,String cellId,String sectionId, String mchId,String dtlId) throws Exception
	{
		return this.pcsEntryDao.getProductDetail(shiftId, entryDate, cellId, sectionId, mchId, dtlId);
	}
	public List<String[]> getSubLossGrid(String pldetailsId) throws Exception {
		// TODO Auto-generated method stub
		return this.pcsEntryDao.getSubLossGrid(pldetailsId);
	}

	public List<String[]> getSubLossGridNew(String pldetailsId) throws Exception {
		// TODO Auto-generated method stub
		return this.pcsEntryDao.getSubLossGridNew(pldetailsId);
	}

	public List<String[]> getPcsLossCaptureGrid(String flid, String date,String Todate , String shiftid) throws Exception {
	
	//	return this.pcsEntryDao.getPcsLossCaptureGrid(flid, date, Todate , shiftid);
		return this.pcsEntryServiceApi.getPcsLossCaptureGrid(flid, date, Todate , shiftid);
	}
	
	public List<String[]> getPcsResultGrid(String entryDate,String shift,String sectId,String cellId, String mchId)throws Exception {
		return this.pcsEntryDao.getPcsResultGrid(entryDate,shift,sectId,cellId, mchId);
	}
	
	public String getMasterKeyid(String entryDate, String shift, String cellId) throws Exception {
		return this.pcsEntryDao.getMasterKeyid(entryDate, shift, cellId);
	}
	
	public List<String[]>  getLastShiftProduct(String sectId, String cellId, String mchId, String entryDate, String shift) throws Exception {
		return this.pcsEntryDao.getLastShiftProduct(sectId, cellId, mchId, entryDate, shift);		
	}

	
	public String getCurrentShift(String factId) throws Exception {
		return this.pcsEntryDao.getCurrentShift(factId);
	}
	
	public String getShiftKeyid(String factId, String shiftCode) throws Exception {
		return this.pcsEntryDao.getShiftKeyid(factId, shiftCode);
	}
	
	public List<String[]> getShiftStarEndTime( String shiftId) throws Exception {
		return this.pcsEntryDao.getShiftStarEndTime(shiftId);
	}
	
	public List<String[]>  getReasonLossNo( String reasonId) throws Exception {
		return this.pcsEntryDao.getReasonLossNo(reasonId);
	}
	
	public String getCalendarTime(String machineId, String plmasterId, String sectId, String entryDate) throws Exception {
		return this.pcsEntryDao.getCalendarTime(machineId, plmasterId, sectId, entryDate);
	}	
	public List<String[]> getAllTimes(String machineId, String plmasterId, String sectId, String entryDate) throws Exception {
		return this.pcsEntryDao.getAllTimes(machineId, plmasterId, sectId, entryDate);
	}
	
	public String getDetailTableName(String sectId, String entryDate) throws Exception {
		return this.pcsEntryDao.getDetailTableName(sectId, entryDate);
	}

	
	
	public List<String[]> getIsPcsEnabled(String condParam) throws Exception
	{		
		return pcsEntryDao.getIsPcsEnabled(condParam);		
	}	
	
	public List<String[]> checkEquipmentFailure(String lossId) throws Exception {
		return pcsEntryDao.checkEquipmentFailure(lossId);		
	}
	
	public List<String[]> checkIsQtyLoss(String lossId) throws Exception {
		return pcsEntryDao.checkIsQtyLoss(lossId);		
	}
		
	public String getFieldsForPcs() throws Exception {
		return this.pcsEntryDao.getFieldsForPcs();
	}
	
	public String getPldRemarks(String detailTable,String pldetailsId ) throws Exception {
		return pcsEntryDao.getPldRemarks(detailTable,pldetailsId );		
	}
	
	public List<String[]> getEntryAllowDates() throws Exception {
		return pcsEntryDao.getEntryAllowDates();		
	}

	public List<String[]> getEntryExistsDates(String detailTable, String entryDate, String cellId) throws Exception {
		return pcsEntryDao.getEntryExistsDates(detailTable, entryDate, cellId);
	}	
	
	public List<String[]> getHolidayDates(String factId, String entryDate) throws Exception {
		return pcsEntryDao.getHolidayDates(factId, entryDate);
	}
	
	public List<String[]> getDateOeeValue(String detailTable,String sectId,String cellId, String mchId,  String entryDate) throws Exception { 
		return pcsEntryDao.getDateOeeValue(detailTable,sectId, cellId, mchId, entryDate);
	}
	
	
	public List<String[]> getPendingTime(String detailTable,String pldetailsId) throws Exception { 
		return pcsEntryDao.getPendingTime(detailTable, pldetailsId);
	}
	
	public String getSelectModel(String prdId) throws Exception {
		return pcsEntryDao.getSelectModel(prdId);
	}
	
	public List<String[]> getClosedMsr(String prodMchId,String condParam ) throws Exception  {
		return pcsEntryDao.getClosedMsr(prodMchId,condParam);
	}
	
	
	public List<String[]> getAllfillgriddata(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return pcsEntryDao.getAllfillgriddata(commonFilter);
	}
	
	public List<String[]> getMsrsDownTime(String msrNos) throws Exception  {
		return pcsEntryDao.getMsrsDownTime(msrNos);
	}
	
	public List<String[]> checkIsOpenMsr(String mchId,String date,String shift) throws Exception  {
		return pcsEntryDao.checkIsOpenMsr(mchId, date, shift);
	}
	
	public String updatePldRemarks(String detailTable, String plDetailsid, String remarks) throws Exception {
		return pcsEntryDao.updatePldRemarks(detailTable, plDetailsid, remarks);
	}
	
	public List<String[]> selectLossRelatedValues(String lossId,String plDetailId) throws Exception
	{
		return pcsEntryDao.selectLossRelatedValues(lossId,plDetailId);
	}
	public List<String[]> selectDetailIdValues(String productId,String mchId,String sectId,String entryDate,String mstId,String plDetailId) throws Exception
	{
		return pcsEntryDao.selectDetailIdValues(productId, mchId, sectId, entryDate, mstId, plDetailId);
	}
	public List<ComboBox> getOperatorCombo(ComboFilter currentFilter , String sectId) throws Exception
	{
		CommonMessage.debugMsg("service implssss");
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setNameField("EMPM_NAME");
		CommonMessage.debugMsg("service implssss1111");
		
		String condSql ="";
		if (UIUtils.isValidKeyId(sectId))			
			condSql = " AND EMPM_CELLID IN ('"+sectId+"','{}') ";		
		condSql += " AND EMPM_ACTIVE = 'Y' ";
		condSql += " AND EMPM_ISOPERATOR = 'Y' ";
		comboFilter.setCondSql(condSql);
		
		CommonMessage.debugMsg(condSql);
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	
	public List<ComboBox> getproductModelCombo(ComboFilter currentFilter , String factId, String mchId, String entryDate) throws Exception
	{
		CommonMessage.debugMsg("service implssss");
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		//comboFilter.setCodeField("PRMM_CODE");
		comboFilter.setIdField("PRMM_KEYID");
		comboFilter.setNameField("PRMM_NAME");
		CommonMessage.debugMsg("service implssss1111");
		
		String condSql ="";
		//if (UIUtils.isValidKeyId(factId))			
		//	condSql = " AND PRMM_FACTORYID IN ('{}','"+factId+"') ";
		
		//if (UIUtils.isValidKeyId(mchId) && UIUtils.isValidKeyId(factId)) { 
		if (UIUtils.isValidKeyId(mchId) ) {
			condSql += " AND PRMM_KEYID IN ( SELECT PRDM_MODEL  from PCS_TL_PRODUCTMST where 1 = 1 ";  
			condSql += " AND PRDM_KEYID IN ( SELECT CYTM_PRODUCTID  FROM PCS_TL_CYCLETIMEMST  WHERE CYTM_MACHINEID='" + mchId + "' " ;
			//condSql += " AND CYTM_FACTORYID ='" + factId + "' " ;
			condSql += " AND '"+ entryDate + "' BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE  ) ) ";  
		}
			
		condSql += " AND PRMM_ACTIVE = 'Y' ";
		comboFilter.setCondSql(condSql);
		
		CommonMessage.debugMsg(condSql);
		comboFilter.setTableName(TableNames.TBL_PCS_TL_PRODUCTMODELMST);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}

	public List<ComboBox> getShift(ComboFilter currentFilter , String factId) throws Exception
	{
		CommonMessage.debugMsg("service implssss");
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		comboFilter.setCodeField("SFTM_NAME");
		comboFilter.setIdField("SFTM_KEYID");
		comboFilter.setNameField("SFTM_CODE");
		
		String condSql ="";
		//if (UIUtils.isValidKeyId(factId))			
		//	condSql = " AND SFTM_FACTORYID ='"+factId+"'";		
		condSql += " AND SFTM_ACTIVE = 'Y' AND SFTM_SHIFTORDER < 4";
		comboFilter.setCondSql(condSql);
		
		CommonMessage.debugMsg(condSql);
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SHIFTMST);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}

	public List<ComboBox> getproductCombo(ComboFilter currentFilter , String factId, String mchId,  String prdModelId, 
				String entryDate,String rawMaterial) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		
		String isHourly = pcsEntryDao.getIsHourlyEntry();	
		if ("Y".equals(isHourly))				
			comboFilter.setCodeField("PRDM_CODE");
		else
			comboFilter.setCodeField("PRDM_DRAWINGNO");
		
		comboFilter.setIdField("PRDM_KEYID");
		comboFilter.setNameField("PRDM_NAME");
		
		String condSql ="";		
		if (UIUtils.isValidKeyId(prdModelId))  
			condSql += " AND PRDM_MODEL ='"+prdModelId+"'";
		
		if (UIUtils.isValidKeyId(mchId)) { 
			condSql += " AND PRDM_KEYID IN ( SELECT CYTM_PRODUCTID  FROM PCS_TL_CYCLETIMEMST " +
					" WHERE CYTM_MACHINEID='" + mchId + "'  " ;
			//condSql += " AND CYTM_ACTIVE='Y' "; 
		}
		//if (UIUtils.isValidKeyId(factId) )  
		//		condSql += " AND CYTM_FACTORYID ='"+factId+"'  ";

		if (UIUtils.isValidKeyId(entryDate) )  
			condSql += " AND '"+ entryDate + "' BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE  ";

		if( UIUtils.isValidKeyId(factId) || UIUtils.isValidKeyId(mchId) )
				condSql += " ) ";

		if (UIUtils.isValidKeyId(rawMaterial) )  
			condSql += " AND PRDM_RAWMATTYPE='"+ rawMaterial + "'";
		//condSql += " AND PRDM_ACTIVE = 'Y' ";
		
		comboFilter.setCondSql(condSql);
		
		comboFilter.setTableName(TableNames.TBL_PCS_TL_PRODUCTMST);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	

		
	public List<ComboBox> getLossCombo(ComboFilter currentFilter , String isQtyLoss, String sectId) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		//comboFilter.setCodeField("PARENTLOSS");
		comboFilter.setIdField("KEYID");
		comboFilter.setNameField("CHILDLOSS");
		comboFilter.setOrderByField("ORDERNO");
		//condSql+= condSql + "AND PLCM_ISLOSS='S'";
		//condSql+= condSql + " AND PLCM_SHOWLOSSNO='Y'";
		String condSql="";
		
		String mouldSectionCode = pcsEntryDao.getMouldSectionCode(); 
		String sectCode = pcsEntryDao.getSectionCode(sectId); 
		
		CommonMessage.debugMsg("mouldSectionCode==="+mouldSectionCode);
		//CommonMessage.debugMsg("sectCode==="+sectCode);
		//CommonMessage.debugMsg("mouldSectionCode.indexOf(sectCode)==="+mouldSectionCode.indexOf(sectCode));
		
		if( UIUtils.isValidKeyId(sectCode) && UIUtils.isValidKeyId(sectCode)) {
			if ((mouldSectionCode.indexOf(sectCode)) >= 0)	
			condSql =" AND UPPER(CHILDLOSS) NOT LIKE '%NO PLAN%' ";
		}
		
		if ("Y".equals(isQtyLoss))
			condSql =" AND KEYID IN (SELECT PLCM_KEYID FROM PCS_TL_LOGCONFIGURATION " + 
									" where PLCM_UOM = (SELECT UOMM_KEYID FROM ADM_TL_UOMMST WHERE UOMM_CODE = 'NOS')) ";
		else if ("N".equals(isQtyLoss))
			condSql = " AND KEYID IN (SELECT PLCM_KEYID FROM PCS_TL_LOGCONFIGURATION " + 
			" where PLCM_UOM NOT IN (SELECT UOMM_KEYID FROM ADM_TL_UOMMST WHERE UOMM_CODE = 'NOS')) ";
		
	/*	condSql += " AND KEYID NOT IN (SELECT PLCM_KEYID FROM PCS_TL_LOGCONFIGURATION " + 
					" WHERE PLCM_PARENTID = (SELECT PLCM_KEYID FROM PCS_TL_LOGCONFIGURATION " + 
					" WHERE PLCM_PARAMETERCODE ='SPEEDLOSS')) ";
     */
		
		comboFilter.setCondSql(condSql);
		comboFilter.setTableName(TableNames.TBL_PCS_VW_LOSSNAMESFORENTRY);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}	
	
	public List<ComboBox> getLossNoCombo(ComboFilter currentFilter , String isQtyLoss, String sectId) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		comboFilter.setIdField("KEYID");
		comboFilter.setNameField("PLCM_LOSSNO");
		comboFilter.setOrderByField("ORDERNO");
		String condSql="";
		
		String mouldSectionCode = pcsEntryDao.getMouldSectionCode(); 
		String sectCode = pcsEntryDao.getSectionCode(sectId); 
		
		CommonMessage.debugMsg("mouldSectionCode==="+mouldSectionCode);
		
		if( UIUtils.isValidKeyId(sectCode) && UIUtils.isValidKeyId(sectCode)) {
			if ((mouldSectionCode.indexOf(sectCode)) >= 0)	
			condSql =" AND UPPER(CHILDLOSS) NOT LIKE '%NO PLAN%' ";
		}
		
		if ("Y".equals(isQtyLoss))
			condSql =" AND KEYID IN (SELECT PLCM_KEYID FROM PCS_TL_LOGCONFIGURATION " + 
									" where PLCM_UOM = (SELECT UOMM_KEYID FROM ADM_TL_UOMMST WHERE UOMM_CODE = 'NOS')) ";
		else if ("N".equals(isQtyLoss))
			condSql = " AND KEYID IN (SELECT PLCM_KEYID FROM PCS_TL_LOGCONFIGURATION " + 
			" where PLCM_UOM NOT IN (SELECT UOMM_KEYID FROM ADM_TL_UOMMST WHERE UOMM_CODE = 'NOS')) ";
		
		comboFilter.setCondSql(condSql);
		comboFilter.setTableName(TableNames.TBL_PCS_VW_LOSSNAMESFORENTRY);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}	
	
	public List<ComboBox> getWno(ComboFilter currentFilter , String sectId, String Pldetailsid , String entryDate) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		//comboFilter.setCodeField("PTWO_WNO");
		comboFilter.setIdField("WNO");
		comboFilter.setNameField("WNO");
		comboFilter.setOrderByField("WNO");
				
		comboFilter.setCondSql(" AND PLDETAILSID ='" + Pldetailsid + "'");
		
		//comboFilter.setTableName(TableNames.TBL_PCS_TL_WORKORDERLINK);
		
		String detailTableName = pcsEntryDao.getDetailTableName(sectId, entryDate); 
		
		comboFilter.setTableName(detailTableName);
		
		
		return commonFilterDao.fillComboValues(comboFilter);			
	}

	public List<ComboBox> getRawType(ComboFilter currentFilter , String productId) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		//comboFilter.setCodeField("PTWO_WNO");
		comboFilter.setIdField("PRDM_RAWMATTYPE");
		comboFilter.setNameField("PRDM_RAWMATTYPE");
		comboFilter.setOrderByField("PRDM_RAWMATTYPE");
		
		// Arun Gupta raised 21-Dec-2012
		comboFilter.setCondSql(" AND PRDM_RAWMATTYPE <> 'DA' " );
		
		if(UIUtils.isValidKeyId(productId))
			comboFilter.setCondSql(" AND PRDM_KEYID = '"+productId+"' " );
		
		comboFilter.setTableName(TableNames.TBL_PCS_TL_PRODUCTMST);
		
		return commonFilterDao.fillComboValues(comboFilter);			
	}
	

	
	public List<ComboBox> getQTYPhenomena(ComboFilter currentFilter ) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
/*		comboFilter.setCodeField("QPHM_CODE");
		comboFilter.setIdField("QPHM_KEYID");
		comboFilter.setNameField("QPHM_NAME");
		comboFilter.setOrderByField("QPHM_CODE");
		
		comboFilter.setTableName(TableNames.TBL_QTM_TL_PHENOMENAMST);*/
		
		//comboFilter.setCodeField("");
		comboFilter.setIdField("ELEMENTID");
		comboFilter.setNameField("DISPLAYCODE");
		comboFilter.setOrderByField("ELEMENTID");
		
		comboFilter.setTableName(TableNames.TBL_QTM_VW_PHENPROCESS);		
		return commonFilterDao.fillComboValues(comboFilter);			
	}
	
	public List<ComboBox> getCause(ComboFilter currentFilter , String isQtyLoss, String parentId) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		
	if ("Y".equals(isQtyLoss)) {
		//comboFilter.setCodeField("QCAM_CODE");		
		comboFilter.setIdField("QLYT_ORIGINALID");
		comboFilter.setNameField("QLYT_DESCRIPTION");
		comboFilter.setOrderByField("QLYT_DESCRIPTION");
		
		String isHourly = pcsEntryDao.getIsHourlyEntry();
		
		if(UIUtils.isValidKeyId(parentId)) {
			if ("Y".equals(isHourly))
				comboFilter.setCondSql(" AND QLYT_PARENTID = '" + parentId + "'" );
				//comboFilter.setCondSql(" AND SUBSTR(QLYT_PARENTID,12) = '" + parentId + "'" );
			else
				comboFilter.setCondSql(" AND QLYT_PARENTID = '" + parentId + "'" );
		}
		
		comboFilter.setTableName(TableNames.TBL_QTM_TL_LAYOUT);
	}
	else {
		comboFilter.setCodeField("QCAM_CODE");
		comboFilter.setIdField("QCAM_KEYID");
		comboFilter.setNameField("QCAM_NAME");
		comboFilter.setOrderByField("QCAM_CODE");	
		comboFilter.setTableName(TableNames.TBL_QTM_TL_CAUSEMST);		
	}
		
		return commonFilterDao.fillComboValues(comboFilter);			
	}	

	public List<ComboBox> getRootCause(ComboFilter currentFilter ) throws Exception {
		
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		comboFilter.setCodeField("QRCM_CODE");
		comboFilter.setIdField("QRCM_KEYID");
		comboFilter.setNameField("QRCM_NAME");
		comboFilter.setOrderByField("QRCM_CODE");
		
		comboFilter.setTableName(TableNames.TBL_QTM_TL_ROOTCAUSEMST);	
		return commonFilterDao.fillComboValues(comboFilter);			
	}	
	
	
	public List<ComboBox> getPhenomenaCombo(ComboFilter comboFilter, String lossId, String cellId,String flid) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("PLPM_LOSSNO");
		comboFilter.setIdField("PLPM_KEYID");
		comboFilter.setNameField("PLPM_NAME");

		String condSql ="";
		StringBuffer sb = new StringBuffer();
		
		/*
		 
		if (UIUtils.isValidKeyId(lossId))  
			condSql += " AND PLPM_MAINLOSS ='"+lossId+"'";
		
		if (UIUtils.isValidKeyId(cellId)) {   
			condSql += " AND  ( PLPM_MAINLOSS IN ( SELECT PLFL_PARAMETERID  FROM PCS_TL_LOSSCELLLINK WHERE PLFL_CELLID ='"+cellId+"' ) ";
			condSql += " OR PLPM_MAINLOSS IN ( SELECT PLCM_KEYID FROM PCS_TL_LOGCONFIGURATION WHERE PLCM_KEYID NOT IN (";
			condSql += "       SELECT PLFL_PARAMETERID  FROM PCS_TL_LOSSCELLLINK ) ) ) ";
		}
		condSql += " AND PLPM_ACTIVE ='Y' ";
		if (condSql.length() >1)
			comboFilter.setCondSql(condSql); */
		
		if (UIUtils.isValidKeyId(flid)) {
			sb.append(" AND PLPM_KEYID IN (SELECT PPFL_PLPM_KEYID FROM PCS_TL_LOSSPHENFACTORYLINK  " );
			sb.append(" WHERE PPFL_FACTORYID ='" + flid + "' )" );
			
			if (sb.toString().length() >1)
				comboFilter.setCondSql(sb.toString()); 
		}
		
		comboFilter.setTableName(TableNames.TBL_PCS_TL_LOSSPHENOMENAMST);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}	
	
	
	public List<ComboBox> getCycletimeCombo(ComboFilter comboFilter, String cellId, String machineId, String productId,
			String entryDate,String sectId) throws Exception
	{
	/*	ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("PLPM_LOSSNO");
		comboFilter.setIdField("CYTM_CAVITY");		
		comboFilter.setNameField("CYTM_CYCLETIME");
			
		StringBuffer condSql = new StringBuffer();
		condSql.append(" AND CYTM_ACTIVE = 'Y' " );
		condSql.append(" AND CYTM_CELLID = '" + cellId + "' AND CYTM_MACHINEID = '" + machineId + "' " );
		condSql.append(" AND CYTM_PRODUCTID = '" + productId + "' " );
		condSql.append(" AND '"+ entryDate + "' BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE " );				
		// condSql.append(" AND CYTM_FROMDATE <= '"+ date + "' AND CYTM_TILLDATE >= '"+ date + "' " );
		
		if (condSql.toString().length() >1)
			comboFilter.setCondSql(condSql.toString());
		
		comboFilter.setTableName(TableNames.TBL_PCS_TL_CYCLETIMEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	*/ //ADD HISTORY ALSO ON 22-09-2012		
		return pcsEntryDao.getCycletimeCombo(cellId, machineId, productId, entryDate,sectId);	
	}	

	public String getActTimePct() throws Exception {
		return pcsEntryDao.getActTimePct();
	}
	
	public String getLossNo(String plcmKeyid) throws Exception {
		return pcsEntryDao.getLossNo(plcmKeyid);
	}
	
	public String[] isExpansionQtyNotMandatory(String sectId) throws Exception
	{
		return pcsEntryDao.isExpansionQtyNotMandatory(sectId);
	}
	
	public PcsTlMst create(PcsTlMst newPcsTlMst,PcsTlDtl newPcsTlDtl,  PcsTlWorkorderlink newPcsTlWorkorderlink,PcsEntryBean pcsEntryBean) throws ValidationExceptions,Exception {
		try {
			CommonMessage.debugMsg("creaate service impl");
			String validationsFor;
			
			if (pcsEntryBean.getPlmasterid().equals(""))	{
				validationsFor = "create";		
			}
			else {
				validationsFor = "create";	
				
			}
			
			validations.validate(newPcsTlMst,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
				
			if(UIUtils.isValidKeyId(pcsEntryBean.getPldetailsid()))			
				validationsFor = "update";
			else
				validationsFor = "create";
			CommonMessage.debugMsg("inside for lpp");
			
			validations.validate(newPcsTlDtl,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
			//String flag = pcsEntryDao.isExpansionQtyMandatory();
			String[] flag = pcsEntryDao.isExpansionQtyNotMandatory(newPcsTlMst.getPrlmSectionid());
			boolean validateTrimQty = false;
			if(UIUtils.isValidKeyId(flag[0]))
			{
				if(flag[0].equalsIgnoreCase("TRUE")||flag[0].equalsIgnoreCase("Y"))
				{
					if(UIUtils.isValidKeyId(flag[1]))
					{
						validateTrimQty = flag[1].equals("Y") ? false:true;
					}
					else
						validateTrimQty = true;						
				}
			}
			if(validateTrimQty)
				validations.validate(newPcsTlDtl,"pcsEntryCreation","TrimExp");
			
			
/*			if(UIUtils.isValidKeyId(pcsEntryBean.getPtwokeyid()))			
				validationsFor = "update";
			else
				validationsFor = "create";
			CommonMessage.debugMsg("inside for getPtwokeyid");			
			validations.validate(newPcsTlWorkorderlink,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations			
*/
			fillValues(newPcsTlMst,newPcsTlDtl, pcsEntryBean);
			
/*			newPcsTlWorkorderlink.setPtwoTheoriticalcycletime(newPcsTlDtl.getTheoriticalcycletime());
			newPcsTlWorkorderlink.setPtwoActualcycletime(newPcsTlDtl.getActualcycletime());
			
			workOrderFillValues(newPcsTlWorkorderlink, pcsEntryBean);
			
			newPcsTlDtl.setPlannedqty(newPcsTlWorkorderlink.getPtwoPlannedqty());
			newPcsTlDtl.setProducedqty(newPcsTlWorkorderlink.getPtwoProducedqty());
			newPcsTlDtl.setNoplaninmins(newPcsTlWorkorderlink.getPtwoNoplaninmins());
			newPcsTlDtl.setCalendartime(newPcsTlWorkorderlink.getPtwoCalendartime());
*/			
			detailFillValues(newPcsTlDtl, pcsEntryBean);
						
			newPcsTlDtl.setCellid(newPcsTlMst.getPrlmCellid());
			
			//newPcsTlDtl.setMachineid(newPcsTlMst.getPrlmMachineid());
			CommonMessage.debugMsg("After Filling Values");
			//CommonMessage.debugMsg("Serv Impl BDMDETAIL Size : "+newPcsTlMst.getPcsDetail().size());			
			return pcsEntryDao.create(newPcsTlMst,newPcsTlDtl,newPcsTlWorkorderlink, pcsEntryBean);
			
			
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	public PcsTlMst createNew(PcsTlMst newPcsTlMst,PcsTlDtl newPcsTlDtl, PcsTlWorkorderlink newPcsTlWorkorderlink, PcsEntryBean pcsEntryBean ) throws ValidationExceptions, Exception
	{
		try {
			CommonMessage.debugMsg("create New service impl");
			String validationsFor = "create";				
			
			validations.validate(newPcsTlMst,"pcsEntryCreation",validationsFor);
				
			if(UIUtils.isValidKeyId(pcsEntryBean.getPldetailsid()))			
				validationsFor = "update";			
			
			validations.validate(newPcsTlDtl,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
			
			String[] flag = pcsEntryDao.isExpansionQtyNotMandatory(newPcsTlMst.getPrlmSectionid());
			boolean validateTrimQty = false;
			if(UIUtils.isValidKeyId(flag[0]))
			{
				if(flag[0].equalsIgnoreCase("TRUE")||flag[0].equalsIgnoreCase("Y"))
				{
					if(UIUtils.isValidKeyId(flag[1]))
					{
						validateTrimQty = flag[1].equals("Y") ? false:true;
					}
					else
						validateTrimQty = true;						
				}
			}
			if(validateTrimQty)
				validations.validate(newPcsTlDtl,"pcsEntryCreation","TrimExp");
			
			
			fillValues(newPcsTlMst, newPcsTlDtl , pcsEntryBean);
			detailFillValues(newPcsTlDtl, pcsEntryBean);
						
			newPcsTlDtl.setCellid(newPcsTlMst.getPrlmCellid());
			return pcsEntryDao.createNew(newPcsTlMst,newPcsTlDtl,newPcsTlWorkorderlink, pcsEntryBean);
			
			
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		
	}
	public PcsTlMst update(PcsTlMst newPcsTlMst, PcsTlMst oldPcsTlMst,
			PcsEntryBean pcsEntryBean) throws ValidationExceptions, Exception {
		try {
			CommonMessage.debugMsg("creaate service impl11122222");
			String validationsFor;			
			validationsFor = "create";		
			validations.validate(newPcsTlMst,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations

			List<PcsTlDtl> bddtls = newPcsTlMst.getPcsDetail();
			validationsFor = "update";
			
			//CommonMessage.debugMsg("bddtls.size()"+bddtls.size());
			for(PcsTlDtl pcsTlDtl : bddtls )
			{				
				CommonMessage.debugMsg("inside for lpp");
				validations.validate(pcsTlDtl,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
			}
			fillValues(newPcsTlMst,bddtls.get(0) ,pcsEntryBean);	
			
			CommonMessage.debugMsg("After Filling Values");
			//CommonMessage.debugMsg("Serv Impl BDMDETAIL Size : "+newPcsTlMst.getPcsDetail().size());
			
			return pcsEntryDao.update(newPcsTlMst);		
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
	}
	
	public String insertNoPlan(String mchId, String date, String shift, String userId, String type,String duration,String frmTime,String toTime) throws Exception {
		return pcsEntryDao.insertNoPlan(mchId, date, shift, userId, type,duration,frmTime,toTime) ;
	}
	public String getLineRejectionLoss() throws Exception
	{    
		return pcsEntryDao.getLineRejectionLoss();
	}
	public PcsTlLossreasonlink createLoss(PcsTlLossreasonlink newPcsTlLossreasonlink,  PcsTlLossreasonlink oldPcsTlLossreasonlink, PcsEntryBean pcsEntryBean) 	throws Exception {
		String validationsFor;		
		validationsFor = "create";				
		validations.validate(newPcsTlLossreasonlink,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
		losslinkFillValues(newPcsTlLossreasonlink);
		return pcsEntryDao.createLoss(newPcsTlLossreasonlink, pcsEntryBean.getPldetailsid(), pcsEntryBean.getLossId(), pcsEntryBean.getLossValue());
	}

	public PcsTlLossreasonlink updateLoss(PcsTlLossreasonlink newPcsTlLossreasonlink,  PcsTlLossreasonlink oldPcsTlLossreasonlink, PcsEntryBean pcsEntryBean) 	throws Exception {
		String validationsFor;		
		validationsFor = "update";				
		validations.validate(newPcsTlLossreasonlink,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations		
		losslinkFillValues(newPcsTlLossreasonlink);
		
		newPcsTlLossreasonlink.setPlrkKeyid(pcsEntryBean.getPlrkKeyid());
		return pcsEntryDao.updateLoss(newPcsTlLossreasonlink, pcsEntryBean.getPldetailsid(), pcsEntryBean.getLossId(), pcsEntryBean.getLossValue());
	}

	public PcsTlLosscapture createLossCapture(PcsTlLosscapture newPcsTlLosscapture,  PcsEntryBean pcsEntryBean) 	throws Exception {
		String validationsFor;		
		validationsFor = "create";	  	 		
		validations.validate(newPcsTlLosscapture,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
		pcsLossCaptureFillValues(newPcsTlLosscapture);
		
		//fill in pcs 
		PcsTlMst pcsTlMst = new PcsTlMst();
		PcsTlDtl pcsTlDtl = new PcsTlDtl();
		PcsTlLossreasonlink pcsTlLossreasonlink = new PcsTlLossreasonlink();
		
		//CommonMessage.debugMsg("newPcstlLossCapt"+newPcsTlLosscapture.getPlosKeyid());
		//CommonMessage.debugMsg("newPcstlLoss.getflid==="+newPcsTlLosscapture.getPlosFlid());
		
		fnFillPcsMstDtl(pcsTlMst, pcsTlDtl, newPcsTlLosscapture, pcsTlLossreasonlink, pcsEntryBean);
		
		/*if (pcsEntryBean.getFormMode().equals("update"))
			return pcsTlLosscaptureDao.update(newPcsTlLosscapture);		
		else*/
		//	return pcsEntryDao.createLossCapture(newPcsTlLosscapture, pcsTlMst, pcsTlDtl, pcsTlLossreasonlink);
			return pcsEntryServiceApi.createLossCapture(newPcsTlLosscapture, pcsTlMst, pcsTlDtl, pcsTlLossreasonlink);
	}
	
	public String fnFillPcsMstDtl(PcsTlMst pcsTlMst, PcsTlDtl pcsTlDtl, PcsTlLosscapture newPcsTlLosscapture, 
			PcsTlLossreasonlink pcsTlLossreasonlink,  PcsEntryBean pcsEntryBean ) throws Exception {
		
		String cellId  = pcsEntryDao.getFlidOriginalid(newPcsTlLosscapture.getPlosFlid());
		String sectId  = pcsEntryDao.getSectionForCell(cellId); 

		//CommonMessage.debugMsg("newPcsTlLosscapture.getPlosDate()="+newPcsTlLosscapture.getPlosDate());
		//CommonMessage.debugMsg("newPcsTlLosscapture.getPlosFlid()="+newPcsTlLosscapture.getPlosFlid());
		
		pcsTlDtl.setPldetailsid(pcsEntryBean.getPldetailsid());
		
		pcsTlMst.setPrlmEntrydate(newPcsTlLosscapture.getPlosDate());
		pcsTlMst.setPrlmShiftid(newPcsTlLosscapture.getPlosShiftid());
		pcsTlMst.setPrlmFlid(newPcsTlLosscapture.getPlosFlid());
		
		pcsTlMst.setPrlmSectionid(sectId);
		pcsTlMst.setPrlmCellid("-");
		pcsTlMst.setPrlmMachineid("-");
		
		pcsTlMst.setPrlmEntryby(newPcsTlLosscapture.getPlosTempfield3());
		pcsTlMst.setPrlmUpdatedby(newPcsTlLosscapture.getPlosCreatedby());
		pcsTlMst.setPrlmCreatedby(newPcsTlLosscapture.getPlosCreatedby());
		
		pcsTlDtl.setCellid(cellId);
		//newPcsTlLosscapture.setPlosTempfield3("-");
		
		pcsTlDtl.setPlannedqty("0");
		pcsEntryBean.setIsOpenMsr("N");
		

		List<String[]> mstDtlids = pcsEntryDao.getPcsMstDtlids(newPcsTlLosscapture,pcsTlMst, pcsTlDtl );
				
		if (mstDtlids.size()>0 ) {
			
			CommonMessage.debugMsg("mstDtlids.get(0)[0]==="+mstDtlids.get(0)[0]);
			pcsTlMst.setPrlmKeyid(mstDtlids.get(0)[0]);
			pcsTlDtl.setPldetailsid(mstDtlids.get(0)[1]);
		}
		
		pcsTlMst = fillValues(pcsTlMst, pcsTlDtl, pcsEntryBean);
		pcsTlDtl = detailFillValues(pcsTlDtl, pcsEntryBean);
			
		//CommonMessage.debugMsg("pcsTlMst.getPrlmDate="+pcsTlMst.getPrlmDate());
		
		//loss Reason Link
		pcsTlLossreasonlink.setPlrkFlid(newPcsTlLosscapture.getPlosFlid());
		pcsTlLossreasonlink.setPlrkSectionid(sectId);
		
		pcsTlLossreasonlink.setPlrkLossid(newPcsTlLosscapture.getPlosLossid());
		pcsTlLossreasonlink.setPlrkReasonid(newPcsTlLosscapture.getPlosLossreason());
		pcsTlLossreasonlink.setPlrkCauseid("-");
		pcsTlLossreasonlink.setPlrkRootcauseid("-");
		pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());
		pcsTlLossreasonlink.setPlrkWno("-");
		pcsTlLossreasonlink.setPlrkMinutes(newPcsTlLosscapture.getPlosLosstime());
		pcsTlLossreasonlink.setPlrkInstance("1");
		pcsTlLossreasonlink.setPlrkDate(newPcsTlLosscapture.getPlosDate());
		pcsTlLossreasonlink.setPlrkShiftid(newPcsTlLosscapture.getPlosShiftid());
		pcsTlLossreasonlink.setPlrkCreatedby(newPcsTlLosscapture.getPlosCreatedby());
		
		pcsTlLossreasonlink = losslinkFillValues(pcsTlLossreasonlink);
		
		return "";
	}

	public PcsTlOperatordtl createPcsEmployee(PcsTlOperatordtl newPcsTlOperatordtl,  PcsEntryBean pcsEntryBean) 	throws Exception {
		String validationsFor;		
		validationsFor = "create";				
		validations.validate(newPcsTlOperatordtl,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
		pcsEmployeeFillValues(newPcsTlOperatordtl);
		return pcsEntryDao.createPcsEmployee(newPcsTlOperatordtl, pcsEntryBean.getPlmasterid(), pcsEntryBean.getPldetailsid());		
	}
	
	public String deletePcsEntry(String sectId, String plmasterId, String pldetailsId, String workOrderNo, String machKeyId, String entryDate) throws ValidationExceptions, Exception {
		return pcsEntryDao.deletePcsEntry(sectId, plmasterId, pldetailsId,workOrderNo,  machKeyId,  entryDate);
	}
		
	public String deletePcsLossEntryITC(String PlosKeyid, String PlrkKeyid, String Pldetailsid, String sectId, String lossId, String lossVal)  throws Exception {
		return pcsEntryDao.deletePcsLossEntryITC(PlosKeyid, PlrkKeyid, Pldetailsid, sectId, lossId, lossVal);
	}
	@Override
	public PcsTlMst deletePcsLossEntryITCNew(String plrkKeyid,String pldetailsid, String sectId) throws Exception {
		// TODO Auto-generated method stub
	//	return pcsEntryDao.deletePcsLossEntryITCNew(plrkKeyid,pldetailsid,sectId);
		 // -vignesh api
		return pcsEntryServiceApi.deletePcsLossEntryITCNew(plrkKeyid,pldetailsid,sectId);
	}
	public String deletePcsLossEntry(String PlrkKeyid, String Pldetailsid, String sectId, String lossId, String lossVal) throws ValidationExceptions, Exception {
		return pcsEntryDao.deletePcsLossEntry(PlrkKeyid, Pldetailsid, sectId, lossId, lossVal);
	}
	
	public String deletePcsEmployee(String Pldetailsid, String empId) throws ValidationExceptions, Exception {
		return pcsEntryDao.deletePcsEmployee(Pldetailsid, empId);
	}
	
	private PcsTlMst fillValues(PcsTlMst newPcsTlMst, PcsTlDtl newPcsTlDtl , PcsEntryBean pcsEntryBean) throws Exception {
		
		newPcsTlMst.setPrlmActive("Y");		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		//CommonMessage.debugMsg("Time : "+pcsEntryBean.get());
		CommonMessage.debugMsg("Key ID : "+newPcsTlMst.getPrlmMachineid());
		//CommonMessage.debugMsg("Old CreatedON : "+oldPcsTlMst.getBdmsCreatedon());
	
		if(newPcsTlMst.getPrlmKeyid() == null )			
		{	
			newPcsTlMst.setPrlmCreatedon(dateTime);			
		}
		
		String elementId = commonFilterDao.getElementID(newPcsTlDtl.getMachineid());
		CommonMessage.debugMsg("elementId==="+elementId);
		
		
		newPcsTlMst.setPrlmElementid(elementId);
		
		if( newPcsTlMst.getPrlmElementid() == null )
			newPcsTlMst.setPrlmElementid("{}");
		
		if( newPcsTlMst.getPrlmFlid() == null || newPcsTlMst.getPrlmFlid().equals("NULL"))
			newPcsTlMst.setPrlmFlid("{}");
		
		
		newPcsTlMst.setPrlmDate(dateTime);
		if(!UIUtils.isValidKeyId(newPcsTlMst.getPrlmMachineid()))
			newPcsTlMst.setPrlmMachineid("{}");
		
		newPcsTlMst.setPrlmUpdateddate(dateTime);
		
		newPcsTlMst.setPrlmApporvedflag("Y");  //y,n,e,c
		newPcsTlMst.setPrlmApprovedby("{}");
		newPcsTlMst.setPrlmApprovedate(dateTime);
		newPcsTlMst.setPrlmShiftincharge("{}");
		newPcsTlMst.setPrlmSubgroupid("{}");
		
		newPcsTlMst.setPrlmIsnoplan("N");  
		newPcsTlMst.setPrlmLogtype("S");
		newPcsTlMst.setPrlmTimeorqty("T");
		newPcsTlMst.setPrlmCompletedflag("{}");
		newPcsTlMst.setPrlmTempfield2("{}");
		newPcsTlMst.setPrlmActive("Y");
		
		newPcsTlMst.setPrlmCreatedon(dateTime);
		newPcsTlMst.setPrlmModifiedon(dateTime);
	
		//newPcsTlMst.setPcsDetail(detailFillValues(newPcsTlMst,oldPcsTlMst,pcsEntryBean));
		//newPcsTlMst.setPcslosslink(losslinkFillValues(newPcsTlMst,oldPcsTlMst,pcsEntryBean));
		
			//CommonMessage.debugMsg("getBdmDetail "+	newPcsTlMst.getBdmDetail().get(0).toString());
		return newPcsTlMst;
		
	}

	private PcsTlWorkorderlink workOrderFillValues(PcsTlWorkorderlink newPcsTlWorkorderlink,PcsEntryBean pcsEntryBean) 
	{
		CommonMessage.debugMsg("work order 1");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		newPcsTlWorkorderlink.setPtwoCreatedon(dateTime);
		newPcsTlWorkorderlink.setPtwoModifiedon(dateTime);
		newPcsTlWorkorderlink.setPtwoActive("Y");

		
		if (newPcsTlWorkorderlink.getPtwoNoplaninmins() == null)
			newPcsTlWorkorderlink.setPtwoNoplaninmins("0"); 

		if (newPcsTlWorkorderlink.getPtwoRejectionqty() == null)
			newPcsTlWorkorderlink.setPtwoRejectionqty("0"); 
		
		if (newPcsTlWorkorderlink.getPtwoProductiontime() == null)
			newPcsTlWorkorderlink.setPtwoProductiontime("0"); 
		if (newPcsTlWorkorderlink.getPtwoUnaccountedtime() == null)
			newPcsTlWorkorderlink.setPtwoUnaccountedtime("0"); 
		
		Float workingTime = Float.parseFloat(newPcsTlWorkorderlink.getPtwoActualcycletime()) 
									* Float.parseFloat(newPcsTlWorkorderlink.getPtwoProducedqty());
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		workingTime= Float.parseFloat(String.format("%.2g%n", workingTime));
		CommonMessage.debugMsg("workingTime"+workingTime);
		CommonMessage.debugMsg("CALENDAR :"+Float.parseFloat(newPcsTlWorkorderlink.getPtwoCalendartime()));
		newPcsTlWorkorderlink.setPtwoProductiontime(workingTime.toString());
		
		Float unaccTime = Float.parseFloat(newPcsTlWorkorderlink.getPtwoCalendartime()) - workingTime;
		unaccTime = Float.parseFloat(String.format("%.2f", unaccTime));
		CommonMessage.debugMsg("unaccTime"+unaccTime);
		newPcsTlWorkorderlink.setPtwoUnaccountedtime(unaccTime.toString());
		
		if (newPcsTlWorkorderlink.getPtwoTemp1() == null)
			newPcsTlWorkorderlink.setPtwoTemp1("{}");			
		if (newPcsTlWorkorderlink.getPtwoTemp2() == null)
			newPcsTlWorkorderlink.setPtwoTemp2("{}");
		if (newPcsTlWorkorderlink.getPtwoTemp3() == null)
			newPcsTlWorkorderlink.setPtwoTemp3("{}");
		
		return newPcsTlWorkorderlink;		
	}
	
	private PcsTlDtl detailFillValues(PcsTlDtl newPcsTlDtl,PcsEntryBean pcsEntryBean) 
	{
		CommonMessage.debugMsg("Detail 1");
		//CommonMessage.debugMsg("Chk Box : " +bdFormBean.getIssparesY());		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		CommonMessage.debugMsg("Detail 2");
		
		NumberFormat df = DecimalFormat.getInstance();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		df.setRoundingMode(RoundingMode.UP);
		
		
		newPcsTlDtl.setCreatedon(dateTime);
		newPcsTlDtl.setModifiedon(dateTime);
		newPcsTlDtl.setActive("Y");
		//newPcsTlDtl.setCreatedby(newPcsTlMst.getPrlmCreatedby());						
		//newPcsTlDtl.setPlmasterid(newPcsTlMst.getPrlmKeyid());
		//newPcsTlDtl.setCellid(newPcsTlMst.getPrlmCellid());
		newPcsTlDtl.setOperators("0");
		
		CommonMessage.debugMsg("Before Convert calendarTime"+newPcsTlDtl.getCalendartime());
		
		if (!UIUtils.isValidKeyId(newPcsTlDtl.getCalendartime()))
			newPcsTlDtl.setCalendartime("480");
		
		Float calendarTime = (float) 0;	
		calendarTime = Float.parseFloat(newPcsTlDtl.getCalendartime().toString());				
		newPcsTlDtl.setCalendartime(df.format(calendarTime).toString());
		
		
	/*	Float calendarTime = Float.parseFloat(newPcsTlDtl.getCalendartime()) ;
		calendarTime = Float.parseFloat(String.format("%.2f", calendarTime));
		CommonMessage.debugMsg("calendarTime"+calendarTime);
		newPcsTlDtl.setCalendartime(calendarTime.toString());
		*/
		

		if (newPcsTlDtl.getMachineid() == null)
			newPcsTlDtl.setMachineid("-");
		
		if (newPcsTlDtl.getNoplaninmins() == null)
			newPcsTlDtl.setNoplaninmins("0");
		
		if (newPcsTlDtl.getNoofproducts() == null)
			newPcsTlDtl.setNoofproducts("1");

		//if (newPcsTlDtl.getRawmaterialtype() == null)
		//	newPcsTlDtl.setRawmaterialtype("{}");

		//if (newPcsTlDtl.getWeight() == "")
		//	newPcsTlDtl.setWeight(null);

		//if (newPcsTlDtl.getWno() == "")
		//	newPcsTlDtl.setWno(null);
		
		CommonMessage.debugMsg("newPcsTlDtl.getweight()"+newPcsTlDtl.getWeight());

		if (newPcsTlDtl.getOperationno()== "")
			newPcsTlDtl.setOperationno(null);

		newPcsTlDtl.setOperationdescription("{}");

		if (newPcsTlDtl.getBatchno() == "")
			newPcsTlDtl.setBatchno("-");

		if (newPcsTlDtl.getCavityavailable() == "")
			newPcsTlDtl.setCavityavailable(null);

		if (newPcsTlDtl.getCavityused() == "")
			newPcsTlDtl.setCavityused(null);

		if (newPcsTlDtl.getMandrelavailable() == "")
			newPcsTlDtl.setMandrelavailable(null);

		if (newPcsTlDtl.getMandrelused() == "")
			newPcsTlDtl.setMandrelused(null);
	
		if (newPcsTlDtl.getProducedqty()== "" || newPcsTlDtl.getProducedqty()== " ")
			newPcsTlDtl.setProducedqty(null);

		if (newPcsTlDtl.getPlannedqty()== null || newPcsTlDtl.getPlannedqty()== "" || newPcsTlDtl.getPlannedqty()== " ")
			newPcsTlDtl.setPlannedqty("0");
		else
		{

    		Float planQty = (float) 0;	
			planQty = Float.parseFloat(newPcsTlDtl.getPlannedqty().toString());
			CommonMessage.debugMsg("planQty"+planQty);
			//planQty = Float.parseFloat(String.format("%.2g%n", planQty));
			String planQty1 = df.format(planQty).toString().replace(",", "");
			CommonMessage.debugMsg("planQty1"+planQty1);
			newPcsTlDtl.setPlannedqty(planQty1);		
		}
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getModelchangepart()))
			newPcsTlDtl.setModelchangepart("{}");
		newPcsTlDtl.setDefectsandreworklossMl(null);
		//newPcsTlDtl.setRejectedqty(null);
		newPcsTlDtl.setReworkqty(null);
		newPcsTlDtl.setDefecttimeSl(null);
		newPcsTlDtl.setProductionlosses(null);
		newPcsTlDtl.setEquipmentfailureMl(null);
		newPcsTlDtl.setSetupandadjustmentMl(null);
		newPcsTlDtl.setToolchangelossMl(null);
		newPcsTlDtl.setMinorstoppagelossMl(null);
		newPcsTlDtl.setSpeedlossMl(null);
		newPcsTlDtl.setShutdownlossMl(null);
		newPcsTlDtl.setManagementlossMl(null);
		newPcsTlDtl.setCommonutilitylossSl(null);
		newPcsTlDtl.setOperatingmotionlossMl(null);
		newPcsTlDtl.setLineorganisationlossMl(null);
		newPcsTlDtl.setLogisticslossMl(null);
		newPcsTlDtl.setMeasuringandadjlossMl(null);
		newPcsTlDtl.setDietoolandjiglossMl(null);
		newPcsTlDtl.setEnergylossMl(null);
		newPcsTlDtl.setYieldlossMl(null);
		newPcsTlDtl.setUnaccountedtime(null);
		newPcsTlDtl.setLoadingtime(null);
		newPcsTlDtl.setEffectiveprodmins(null);
		newPcsTlDtl.setMchavailabletime(null);
		newPcsTlDtl.setProductionavltime(null);
		newPcsTlDtl.setProductiontime(null);
		newPcsTlDtl.setRoa(null);
		newPcsTlDtl.setRop(null);
		newPcsTlDtl.setRoq(null);
		newPcsTlDtl.setOee(null);
		
		CommonMessage.debugMsg("pcsEntryBean.getIsOpenMsr()"+pcsEntryBean.getIsOpenMsr());
		
		if (pcsEntryBean.getIsOpenMsr().equals("Y"))		
			newPcsTlDtl.setCompletedflag("P");
		else
			newPcsTlDtl.setCompletedflag("C");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getRemarks()))
			newPcsTlDtl.setRemarks("<**>");
		
		CommonMessage.debugMsg(newPcsTlDtl.getProducedqty());
		
	/*	Float workingTime = Float.parseFloat(newPcsTlDtl.getActualcycletime()) * Float.parseFloat(newPcsTlDtl.getProducedqty());
		//workingTime =  (float) Math.ceil(workingTime);
		//workingTime =  (float) Math.round(workingTime);
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		workingTime= Float.parseFloat(String.format("%.2g%n", workingTime));
		CommonMessage.debugMsg("workingTime"+workingTime);
		CommonMessage.debugMsg("CALENDAR :"+Float.parseFloat(newPcsTlDtl.getCalendartime()));
		newPcsTlDtl.setProductiontime(workingTime.toString());
		Float unaccTime = Float.parseFloat(newPcsTlDtl.getCalendartime()) - workingTime;
		//unaccTime = Float.parseFloat(String.format("%.2g%n", unaccTime));
		unaccTime = Float.parseFloat(String.format("%.2f", unaccTime));
		CommonMessage.debugMsg("unaccTime"+unaccTime);
		newPcsTlDtl.setUnaccountedtime(unaccTime.toString());
		
		
		Float cycleTimeVar = Float.parseFloat(newPcsTlDtl.getActualcycletime()) - Float.parseFloat(newPcsTlDtl.getTheoriticalcycletime());
		CommonMessage.debugMsg("Cycle Time Var : "+cycleTimeVar);
		Float cycTime = Float.parseFloat(newPcsTlDtl.getProducedqty())/Float.parseFloat(newPcsTlDtl.getCavityused());
		CommonMessage.debugMsg("cycTime : "+cycTime);
			  cycleTimeVar = cycleTimeVar * cycTime;
		      cycleTimeVar = cycleTimeVar/Float.parseFloat(newPcsTlDtl.getTheoriticalcycletime());
		      cycleTimeVar = cycleTimeVar * Float.parseFloat(newPcsTlDtl.getCavityused());
		CommonMessage.debugMsg("Cycle Time Var : "+cycleTimeVar);
		newPcsTlDtl.setLoss26(cycleTimeVar.toString());
		
		Float apcu =  Float.parseFloat(newPcsTlDtl.getProductiontime())/Float.parseFloat(newPcsTlDtl.getCavityused());
		CommonMessage.debugMsg("apcu : "+apcu);
		Float cacu =  Float.parseFloat(newPcsTlDtl.getCavityavailable())-Float.parseFloat(newPcsTlDtl.getCavityused());
		CommonMessage.debugMsg("cacu : "+cacu);
			  apcu = apcu * cacu;
		CommonMessage.debugMsg("apcu : "+apcu);
		newPcsTlDtl.setLoss27(apcu.toString());
		*/
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getSetupandadjustmentMl()))
			newPcsTlDtl.setSetupandadjustmentMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getStartuplossMl()))
			newPcsTlDtl.setStartuplossMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getMeasuringandadjlossMl()))
			newPcsTlDtl.setMeasuringandadjlossMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getShutdownlossMl()))
			newPcsTlDtl.setShutdownlossMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLineorganisationlossMl()))
			newPcsTlDtl.setLineorganisationlossMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getToolchangelossMl()))
			newPcsTlDtl.setToolchangelossMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getOperatingmotionlossMl()))
			newPcsTlDtl.setOperatingmotionlossMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLogisticslossMl()))
			newPcsTlDtl.setLogisticslossMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getEquipmentfailureMl()))
			newPcsTlDtl.setEquipmentfailureMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getManagementlossMl()))
			newPcsTlDtl.setManagementlossMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getMinorstoppagelossMl()))
			newPcsTlDtl.setMinorstoppagelossMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss20()))
			newPcsTlDtl.setLoss20("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss30()))
			newPcsTlDtl.setLoss30("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss40()))
			newPcsTlDtl.setLoss40("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss41()))
			newPcsTlDtl.setLoss41("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss42()))
			newPcsTlDtl.setLoss42("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss43()))
			newPcsTlDtl.setLoss43("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getMchavailabletime()))
			newPcsTlDtl.setMchavailabletime("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getProductionavltime()))
			newPcsTlDtl.setProductionavltime("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getNoplaninmins()))
			newPcsTlDtl.setNoplaninmins("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getProducedqty()))
			newPcsTlDtl.setProducedqty("0");
		//if(!UIUtils.isValidKeyId(newPcsTlDtl.getRejectedqty()))
		//	newPcsTlDtl.setRejectedqty("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getBacklogqty()))
			newPcsTlDtl.setBacklogqty("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getReworkqty()))
			newPcsTlDtl.setReworkqty("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getRoa()))
			newPcsTlDtl.setRoa("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getRop()))
			newPcsTlDtl.setRop("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getRoq()))
			newPcsTlDtl.setRoq("0");
		
	/*	Float lossCalcn = Float.parseFloat(newPcsTlDtl.getSetupandadjustmentMl()) + Float.parseFloat(newPcsTlDtl.getStartuplossMl());
		CommonMessage.debugMsg("lossCalcn1 : "+lossCalcn);
			  lossCalcn = lossCalcn + Float.parseFloat(newPcsTlDtl.getMeasuringandadjlossMl()) + Float.parseFloat(newPcsTlDtl.getShutdownlossMl());
		CommonMessage.debugMsg("lossCalcn 2: "+lossCalcn);
			  lossCalcn = lossCalcn +  Float.parseFloat(newPcsTlDtl.getLineorganisationlossMl())+ Float.parseFloat(newPcsTlDtl.getToolchangelossMl());
		CommonMessage.debugMsg("lossCalcn3 : "+lossCalcn);
			  lossCalcn = lossCalcn + Float.parseFloat(newPcsTlDtl.getOperatingmotionlossMl()) + Float.parseFloat(newPcsTlDtl.getLogisticslossMl());
		CommonMessage.debugMsg("lossCalcn4 : "+lossCalcn);
			  lossCalcn = lossCalcn + Float.parseFloat(newPcsTlDtl.getEquipmentfailureMl());
		CommonMessage.debugMsg("lossCalcn : "+lossCalcn);
		Float downTime =  lossCalcn + Float.parseFloat(newPcsTlDtl.getManagementlossMl()) + Float.parseFloat(newPcsTlDtl.getMinorstoppagelossMl());
		CommonMessage.debugMsg("downTime : "+downTime);
		
		Float loadingTime = Float.parseFloat(newPcsTlDtl.getMchavailabletime()) - Float.parseFloat(newPcsTlDtl.getManagementlossMl());
		CommonMessage.debugMsg("loadingTime : "+loadingTime);
	    newPcsTlDtl.setLoadingtime(loadingTime.toString());
		
	  
	    Float avlLoss =  Float.parseFloat(newPcsTlDtl.getMchavailabletime()) - Float.parseFloat(newPcsTlDtl.getProductionavltime());
	    CommonMessage.debugMsg("avlLoss : "+avlLoss);
	    
	    Float prodAvltime =  Float.parseFloat(newPcsTlDtl.getLoss41()) + Float.parseFloat(newPcsTlDtl.getLoss30()) + Float.parseFloat(newPcsTlDtl.getLoss42()) ;
	    	  prodAvltime =   prodAvltime + Float.parseFloat(newPcsTlDtl.getLoss40()) + Float.parseFloat(newPcsTlDtl.getLoss43()) + Float.parseFloat(newPcsTlDtl.getLoss20()) ;
	    	  prodAvltime =   prodAvltime + Float.parseFloat(newPcsTlDtl.getNoplaninmins());
	    	  prodAvltime = Float.parseFloat(newPcsTlDtl.getMchavailabletime()) - prodAvltime;
	    CommonMessage.debugMsg("prodAvltime : "+prodAvltime);
	    newPcsTlDtl.setProductionavltime(prodAvltime.toString());
	    newPcsTlDtl.setProductiontime(prodAvltime.toString());
	    */
	    /*Float utilTime =  Float.parseFloat(newPcsTlDtl.getLoadingtime()) - lossCalcn;
	    CommonMessage.debugMsg("utilTime : "+utilTime);
	    
	    Float actProdn = Float.parseFloat(newPcsTlDtl.getTheoriticalcycletime())/Float.parseFloat(newPcsTlDtl.getCavityused());
	    	  actProdn = Float.parseFloat(newPcsTlDtl.getProducedqty()) * actProdn; 
	    CommonMessage.debugMsg("actProdn : "+actProdn);*/
		
	    //newPcsTlDtl.setProductiontime(actProdn.toString());
	  
	  /*  Float roq =   Float.parseFloat(newPcsTlDtl.getRejectedqty()) +  Float.parseFloat(newPcsTlDtl.getReworkqty());
	    	  roq =   Float.parseFloat(newPcsTlDtl.getProducedqty()) - roq; 
	    	  roq =   roq/Float.parseFloat(newPcsTlDtl.getProducedqty()); 
	    CommonMessage.debugMsg("roq : "+roq);
	    newPcsTlDtl.setRoq(roq.toString());
	    
	    Float oee = Float.parseFloat(newPcsTlDtl.getRoa()) *  Float.parseFloat(newPcsTlDtl.getRop()) *  Float.parseFloat(newPcsTlDtl.getRoq());
	    newPcsTlDtl.setOee(oee.toString());*/
	   // Float avlRate = newPcsTlDtl.get
	    //newPcsTlDtl.setp
		//newPcsTlDtl.setModelchangepart("{}");		
		
		//genTlEmployeedtl.setEmpdModifiedon(dateTime);

	return newPcsTlDtl;
	}


	private PcsTlLossreasonlink losslinkFillValues(PcsTlLossreasonlink newPcsTlLossreasonlink) 
	{
			CommonMessage.debugMsg(" losslinkFillValues Detail 1");
		//CommonMessage.debugMsg("Chk Box : " +bdFormBean.getIssparesY());		
			String dateTime = CommonFunctions.pg_dateTimeNow();
			
			if (newPcsTlLossreasonlink.getPlrkInstance() == null || newPcsTlLossreasonlink.getPlrkInstance().equals("0"))
				newPcsTlLossreasonlink.setPlrkInstance("1");
			
			
			String[] reasonId=null;
			//CommonMessage.debugMsg(newPcsTlLossreasonlink.getPlrkReasonid().split("-"));			
			reasonId = newPcsTlLossreasonlink.getPlrkReasonid().split("-");
			CommonMessage.debugMsg("reasonId.length"+reasonId.length);
			CommonMessage.debugMsg("reasonId(0)"+reasonId[0].toString());
			
			if (reasonId.length>1) { 
				String plrkProcessid = reasonId[0].toString();
				newPcsTlLossreasonlink.setPlrkProcessid(plrkProcessid);
				newPcsTlLossreasonlink.setPlrkReasonid(reasonId[1].toString());
			}
			else
				newPcsTlLossreasonlink.setPlrkProcessid("{}");
			
			//newPcsTlLossreasonlink.setPlrkDate(dateTime);
			//newPcsTlLossreasonlink.setPlrkShiftid(newPcsTlMst.getPrlmShiftid());
			newPcsTlLossreasonlink.setPlrkHourno("1");
			/*
			newPcsTlLossreasonlink.setPlrkFactoryid(newPcsTlMst.getPrlmFactoryid());
			newPcsTlLossreasonlink.setPlrkSectionid(newPcsTlMst.getPrlmSectionid());
			newPcsTlLossreasonlink.setPlrkCellid(newPcsTlMst.getPrlmCellid());			
			newPcsTlLossreasonlink.setPlrkMachineid(newPcsTlMst.getPcsDetail().get(1).getMachineid());
			*/
			if (newPcsTlLossreasonlink.getPlrkSubgroupid() == null )
				newPcsTlLossreasonlink.setPlrkSubgroupid("{}");
			if (newPcsTlLossreasonlink.getPlrkRemarks() == null)
				newPcsTlLossreasonlink.setPlrkRemarks("{}");
			newPcsTlLossreasonlink.setPlrkMsrno("{}");
			
			if (newPcsTlLossreasonlink.getPlrkPldetailid() ==null)
				newPcsTlLossreasonlink.setPlrkPldetailid("{}");
			
			if (newPcsTlLossreasonlink.getPlrkWno() ==null)
				newPcsTlLossreasonlink.setPlrkWno("{}");
			
			if (newPcsTlLossreasonlink.getPlrkCauseid() ==null)
				newPcsTlLossreasonlink.setPlrkCauseid("{}");
			if (newPcsTlLossreasonlink.getPlrkRootcauseid() ==null)
				newPcsTlLossreasonlink.setPlrkRootcauseid("{}");
	
			
			if (newPcsTlLossreasonlink.getPlrkMsrno() ==null)
				newPcsTlLossreasonlink.setPlrkMsrno("{}");
			//if (newPcsTlLossreasonlink.getPlrkProcessid() ==null)
			//	newPcsTlLossreasonlink.setPlrkProcessid("{}");
			if (newPcsTlLossreasonlink.getPlrkElementid() ==null)
				newPcsTlLossreasonlink.setPlrkElementid("-");
			
			newPcsTlLossreasonlink.setPlrkCreatedon(dateTime);
			newPcsTlLossreasonlink.setPlrkModifiedon(dateTime);
			newPcsTlLossreasonlink.setPlrkActive("Y");
			
			CommonMessage.debugMsg(" losslinkFillValues before Ancilliary time ");
			
			if(newPcsTlLossreasonlink.getPcsTlAncilliarytimeList()!= null && newPcsTlLossreasonlink.getPcsTlAncilliarytimeList().size()>=0) 
				newPcsTlLossreasonlink.setPcsTlAncilliarytimeList(ancilliaryFillValues(newPcsTlLossreasonlink));
			
			//newPcsTlLossreasonlink.setPlrkCreatedby();
		//	PcsTlDtl.setBdanBdms_keyid(newPcsTlMst.getBdmsKeyid());
	
			//genTlEmployeedtl.setEmpdModifiedon(dateTime);
		
		return newPcsTlLossreasonlink;
	}
	
	

	private PcsTlLosscapture pcsLossCaptureFillValues(PcsTlLosscapture newPcsTlLosscapture) 
	{
			CommonMessage.debugMsg("Detail 1");
			//CommonMessage.debugMsg("Chk Box : " +bdFormBean.getIssparesY());		
			String dateTime = CommonFunctions.pg_dateTimeNow();
			           
			if (! UIUtils.isValidKeyId(newPcsTlLosscapture.getPlosProdImpQty()))
				newPcsTlLosscapture.setPlosProdImpQty("0");
			if (! UIUtils.isValidKeyId(newPcsTlLosscapture.getPlosProdImpact()))
				newPcsTlLosscapture.setPlosProdImpact("N");
			if (! UIUtils.isValidKeyId(newPcsTlLosscapture.getPlosLosstime()))
				newPcsTlLosscapture.setPlosLosstime("0");
			
			if (! UIUtils.isValidKeyId(newPcsTlLosscapture.getPLosLossdescription()))
				newPcsTlLosscapture.setPlosLossdescription("-");
			
			//newPcsTlLosscapture.setPlosFromtime( newPcsTlLosscapture.getPlosFromtime() + " " + newPcsTlLosscapture.getPlosFromtime() ); 
			//newPcsTlLosscapture.setPlosTotime( newPcsTlLosscapture.getPlosTotime() + " " + newPcsTlLosscapture.getPlosTotime() );
			
			//newPcsTlLosscapture.setPlosTempfield1("-");   
			
			if(!UIUtils.isValidKeyId(newPcsTlLosscapture.getPlosEquipment()))
			   newPcsTlLosscapture.setPlosEquipment("-");
			
			//newPcsTlLosscapture.setPlosTempfield3("-");
			newPcsTlLosscapture.setPlosTempfield4("-");
			newPcsTlLosscapture.setPlosTempfield5("-");
			
			newPcsTlLosscapture.setPlosCreatedon(dateTime);
			newPcsTlLosscapture.setPlosModifiedon(dateTime);
			newPcsTlLosscapture.setPlosActive("Y");
		
		return newPcsTlLosscapture;
	}
	
	private PcsTlOperatordtl pcsEmployeeFillValues(PcsTlOperatordtl newPcsTlOperatordtl) 
	{
			CommonMessage.debugMsg("Detail 1");
			//CommonMessage.debugMsg("Chk Box : " +bdFormBean.getIssparesY());		
			String dateTime = CommonFunctions.pg_dateTimeNow();
			
			newPcsTlOperatordtl.setPopdCreatedon(dateTime);
			newPcsTlOperatordtl.setPopdModifiedon(dateTime);
			newPcsTlOperatordtl.setPopdActive("Y");
		
		return newPcsTlOperatordtl;
	}
	
	private List<PcsTlAncilliarytime>  ancilliaryFillValues(PcsTlLossreasonlink newPcsTlLossreasonlink) 
	{
		
		CommonMessage.debugMsg(" In Fillvalues Ancilliary time ");
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		List<PcsTlAncilliarytime> newPcsTlAncilliarytimes = newPcsTlLossreasonlink.getPcsTlAncilliarytimeList();
		
		CommonMessage.debugMsg(newPcsTlLossreasonlink.getPcsTlAncilliarytimeList().size() + " : Size");
		List<PcsTlAncilliarytime> newPcsTlAncilliarytime = new ArrayList<PcsTlAncilliarytime>();
		for( PcsTlAncilliarytime pcsTlAncilliarytime :newPcsTlAncilliarytimes)
		{	
			
			PcsTlAncilliarytime pcsAncilliarytime2 = new PcsTlAncilliarytime();
			
			pcsAncilliarytime2.setPtatPldeatilsid(newPcsTlLossreasonlink.getPlrkPldetailid());
			pcsAncilliarytime2.setPtatLossid(newPcsTlLossreasonlink.getPlrkLossid());
			pcsAncilliarytime2.setPtatWomskeyid(pcsTlAncilliarytime.getPtatWomskeyid());
			pcsAncilliarytime2.setPtatOccureddate(pcsTlAncilliarytime.getPtatOccureddate());
			pcsAncilliarytime2.setPtatProductionstartdate(pcsTlAncilliarytime.getPtatProductionstartdate());
			pcsAncilliarytime2.setPtatLosstime(pcsTlAncilliarytime.getPtatLosstime());
			
			pcsAncilliarytime2.setPtatStatus("Y");
			pcsAncilliarytime2.setPtatRemarks("{}");			
			//pcsAncilliarytime2.setPtatMachineid(newPcsTlLossreasonlink.getPlrkMachineid());
			pcsAncilliarytime2.setPtatMachineid(pcsTlAncilliarytime.getPtatMachineid());
			pcsAncilliarytime2.setPtatTemp2("{}");
			pcsAncilliarytime2.setPtatTemp3("{}");			
			pcsAncilliarytime2.setPtatActive("Y");
			
			pcsAncilliarytime2.setPtatCreatedby(newPcsTlLossreasonlink.getPlrkCreatedby());			
			pcsAncilliarytime2.setPtatCreatedon(dateTime);
			pcsAncilliarytime2.setPtatModifiedon(dateTime);
				
			newPcsTlAncilliarytime.add(pcsAncilliarytime2);
		}
		return newPcsTlAncilliarytime;
	}

	@Override
	public List<ComboBox> getPcsLossCause(ComboFilter currentFilter , String isQtyLoss, String parentId)throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		
			//comboFilter.setCodeField("QCAM_CODE");		
			comboFilter.setIdField("PLCS_KEYID");
			comboFilter.setNameField("PLCS_DESCRIPTION");
			comboFilter.setOrderByField("PLCS_DESCRIPTION");
			
			String isHourly = pcsEntryDao.getIsHourlyEntry();
			
			if(UIUtils.isValidKeyId(parentId)) {
				if ("Y".equals(isHourly))
					comboFilter.setCondSql(" AND PLCS_PHENOMENAID = '" + parentId + "'" );
					//comboFilter.setCondSql(" AND SUBSTR(QLYT_PARENTID,12) = '" + parentId + "'" );
				else
					comboFilter.setCondSql(" AND PLCS_PHENOMENAID = '" + parentId + "'" );
			}
			
			comboFilter.setTableName(TableNames.TBL_PCS_TL_LOSSCAUSEMST);
	
			return commonFilterDao.fillComboValues(comboFilter);		
	}

//	@Override
//	public String populateTempTable(String excelFileName,
//			PcsTlOtherlossentry pcsTlOtherlossentry) throws UploadException, ValidationExceptions, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
//		try{
//			validations.validate(pcsTlOtherlossentry,"pcsOtherLoss","create");
//		}catch(ValidationExceptions e){
//			throw new ValidationExceptions(e.getMessage());
//		}
//		fillValues(pcsTlOtherlossentry);
//		
//		return pcsTlLosscaptureDao.populateTempTable( excelFileName,pcsTlOtherlossentry);
//		return pcsEntryServiceApi.populateTempTable( excelFileName,pcsTlOtherlossentry);
//	}
	

	@Override
	public String populateTempTable(String excelFileName,
			PcsTlOtherlossentry pcsTlOtherlossentry) throws UploadException, ValidationExceptions, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
		try{
			validations.validate(pcsTlOtherlossentry,"pcsOtherLoss","create");
			fillValues(pcsTlOtherlossentry);
			return pcsEntryServiceApi.populateTempTable( excelFileName,pcsTlOtherlossentry);
		}catch(Throwable e){
			throw new ValidationExceptions(e.getMessage());
		}
		
		
	//	return pcsTlLosscaptureDao.populateTempTable( excelFileName,pcsTlOtherlossentry);
		
	}

	private PcsTlOtherlossentry fillValues(PcsTlOtherlossentry pcsTlOtherlossentry) {
		String dateTime = CommonFunctions.dateTimeNow();
		
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseActive()))
			pcsTlOtherlossentry.setOlseActive("Y");
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseCreatedon()))
			pcsTlOtherlossentry.setOlseCreatedon(dateTime);
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseDate()))
			pcsTlOtherlossentry.setOlseDate(Constants.passNullDate);
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseElementid()))
			pcsTlOtherlossentry.setOlseElementid("{}");
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseFlid()))
			pcsTlOtherlossentry.setOlseFlid("{}");
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseLossid()))
			pcsTlOtherlossentry.setOlseLossid("{}");
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseModifiedon()))
			pcsTlOtherlossentry.setOlseModifiedon(dateTime);
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseTempfield1()))
			pcsTlOtherlossentry.setOlseTempfield1("-");
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseTempfield2()))
			pcsTlOtherlossentry.setOlseTempfield2("-");
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseTempfield3()))
			pcsTlOtherlossentry.setOlseTempfield3("-");
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseTempfield4()))
			pcsTlOtherlossentry.setOlseTempfield4("-");
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseTempfield5()))
			pcsTlOtherlossentry.setOlseTempfield5("-");
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseTempfield6()))
			pcsTlOtherlossentry.setOlseTempfield6("-");
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseTempfield7()))
			pcsTlOtherlossentry.setOlseTempfield7("-");
		if(!UIUtils.isValidKeyId(pcsTlOtherlossentry.getOlseTempfield8()))
			pcsTlOtherlossentry.setOlseTempfield8("-");
		return pcsTlOtherlossentry;
	}

	@Override
	public String updateLossVal(List<PcsTlOtherlossentry> otherLossList) throws BusinessApplicationExceptions, Exception {
	//	return pcsTlLosscaptureDao.updateLossVal(otherLossList);
		return pcsEntryServiceApi.updateLossVal(otherLossList);
	}

	@Override
	public List<String[]> getShiftEndTime() throws Exception {
		// TODO Auto-generated method stub
		return pcsEntryDao.getShiftEndTime();
	}

	@Override
	public List<String[]> getLossEntryData(GridParams gridParams,CommonFilter commonFilter,String flid) throws Exception {
		// TODO Auto-generated method stub
		
		return pcsEntryDao.getLossEntryData(gridParams,commonFilter,flid);
		 // --vignesh 
		
	//	return	pcsEntryServiceApi.getLossEntryData(gridParams, commonFilter, flid);
		
	}

	@Override
	public String getTotalLossEntryData(String flid) throws Exception {
		// TODO Auto-generated method stub
		return pcsEntryDao.getTotalLossEntryData(flid);
	}

	
	public   Workbook getExcelreport(CommonFilter commonFilter,
			JSONObject tblJSONObj, String formats) throws Exception  {
		// TODO Auto-generated method stub
		return  null;
	}
	
	@Override
	public List<ComboBox> getEquipmentName(ComboFilter combofilter) throws Exception{
		combofilter.setIdField("PLE_KEYID");
		combofilter.setNameField("PLE_NAME");
		combofilter.setTableName(TableNames.TBL_PCS_TL_EQUIPMENT);
		return commonFilterDao.fillComboValues(combofilter);
	}
}
