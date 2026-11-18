
/*Created By : BABU.D*/
package com.akranta.tpm.service.impl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_PcsEntryBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.BAL_PcsEntryDao;
import com.akranta.tpm.dao.BAL_PcsTlLosscaptureDao;
import com.akranta.tpm.dao.BAL_PcsTlProductiondataDao;
import com.akranta.tpm.dao.BAL_PcsTlRejectionbreakupdataDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.BAL_PcsEntryDaoImpl;
import com.akranta.tpm.dao.impl.BAL_PcsTlLosscaptureDaoImpl;
import com.akranta.tpm.dao.impl.BAL_PcsTlProductiondataDaoImpl;
import com.akranta.tpm.dao.impl.BAL_PcsTlRejectionbreakupdataDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlAncilliarytime;
import com.akranta.tpm.model.BAL_PcsTlDtl;
import com.akranta.tpm.model.BAL_PcsTlHtDetails;
import com.akranta.tpm.model.BAL_PcsTlHtProductionHistory;
import com.akranta.tpm.model.BAL_PcsTlJhlossDetail;
import com.akranta.tpm.model.BAL_PcsTlLosscapture;
import com.akranta.tpm.model.BAL_PcsTlLossreasonlink;
import com.akranta.tpm.model.PcsTlLosscapture;
import com.akranta.tpm.model.BAL_PcsTlMst;
import com.akranta.tpm.model.PcsTlOperatordtl;
import com.akranta.tpm.model.PcsTlOtherlossentry;
import com.akranta.tpm.model.BAL_PcsTlProductiondata;
import com.akranta.tpm.model.BAL_PcsTlRejectionbreakupdata;
import com.akranta.tpm.model.PcsTlWorkorderlink;
//import com.akranta.tpm.model.pcsTlJhlossDetail;
import com.akranta.tpm.service.BAL_PcsEntryService;
import com.akranta.tpm.service.api.BAL_PcsEntryServiceApi;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_PcsEntryServiceImpl implements BAL_PcsEntryService {
	
	private BAL_PcsTlProductiondataDao pcsTlproductiondatadao;
	private BAL_PcsEntryDao pcsEntryDao ;
	private BAL_PcsTlLosscaptureDao pcsTlLosscaptureDao ;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	private BAL_PcsTlRejectionbreakupdataDao pcsTlRejectionbreakupdatadao;
	private BAL_PcsEntryBean pcsEntryBean; 
	private BAL_PcsEntryServiceApi pcsenrtyserviceApi;
	
	public BAL_PcsEntryServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pcsTlRejectionbreakupdatadao=new BAL_PcsTlRejectionbreakupdataDaoImpl(dbActionTemplate);
		pcsTlproductiondatadao=new BAL_PcsTlProductiondataDaoImpl(dbActionTemplate);
		pcsEntryDao = new BAL_PcsEntryDaoImpl(dbActionTemplate);
		pcsTlLosscaptureDao = new BAL_PcsTlLosscaptureDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
		pcsEntryBean= new BAL_PcsEntryBean();
		
 	}
	
	@Override 
	 public void BAL_PcsEntryServiceImplJwt(String jwtToken) { 
	        try { 
	        	pcsenrtyserviceApi = new BAL_PcsEntryServiceApi(jwtToken); 
	        } catch (Exception e) { 
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

	public List<String[]> getPcsProductionCaptureGrid(String cellid, String date,String Todate , String shiftid) throws Exception {
		return this.pcsEntryDao.getPcsProductionCaptureGrid(cellid, date, Todate , shiftid);
	}
	public List<String[]> getPcsRejectionCaptureGrid(String mstKeyid) throws Exception {
		return this.pcsEntryDao.getPcsRejectionCaptureGrid(mstKeyid);
	}
	
	public List<String[]> getPcsLossCaptureGrid(String flid, String date,String Todate , String shiftid) throws Exception {
		return this.pcsEntryDao.getPcsLossCaptureGrid(flid, date, Todate , shiftid);
	}
	
	public List<String[]> getPcsResultGrid(String entryDate,String shift,String sectId,String cellId, String mchId)throws Exception {
		return this.pcsEntryDao.getPcsResultGrid(entryDate,shift,sectId,cellId, mchId);
	}
	
	public String getMasterKeyid(String entryDate, String shift, String cellId) throws Exception {
		return this.pcsEntryDao.getMasterKeyid(entryDate, shift, cellId);
	}
	
	public List<String[]>  getLastShiftProduct(String factId, String cellId, String flId, String entryDate, String shift) throws Exception {
		//return this.pcsEntryDao.getLastShiftProduct(factId, cellId, flId, entryDate, shift);	
		return this.pcsenrtyserviceApi.getLastShiftProduct(factId, cellId, flId, entryDate, shift);	
	}

	
	public String getCurrentShift(String factId) throws Exception {
		return this.pcsEntryDao.getCurrentShift(factId);
	}
	
	public String getShiftKeyid(String factId, String shiftCode) throws Exception {
		return this.pcsEntryDao.getShiftKeyid(factId, shiftCode);
	}
	
	public List<String[]> getShiftStarEndTime( String shiftId) throws Exception {
		//return this.pcsEntryDao.getShiftStarEndTime(shiftId);
		return this.pcsenrtyserviceApi.getShiftStarEndTime(shiftId);
	}
	public List<String[]> getAllTimeforHour(String cellId,String shiftId,String  entryDate,String hourId,String shiftWise) throws Exception {
		//return this.pcsEntryDao.getAllTimeforHour(cellId, shiftId, entryDate, hourId,shiftWise);
		return this.pcsenrtyserviceApi.getAllTimeforHour(cellId, shiftId, entryDate, hourId, shiftWise);
	}
	public List<String[]>  getReasonLossNo( String reasonId) throws Exception {
		return this.pcsEntryDao.getReasonLossNo(reasonId);
	}
	public List<String[]>  getSubMainLossNo( String subId) throws Exception {
		return this.pcsEntryDao.getSubMainLossNo(subId);
	}
	
	public String getCalendarTime(String machineId, String plmasterId, String sectId, String entryDate,String shiftWise) throws Exception {
		return this.pcsEntryDao.getCalendarTime(machineId, plmasterId, sectId, entryDate,shiftWise);
	}	
	public List<String[]> getAllTimes(String cellId, String plmasterId, String sectId, String entryDate) throws Exception {
		return this.pcsEntryDao.getAllTimes(cellId, plmasterId, sectId, entryDate);
	}
	
	public String getDetailTableName(String cellId, String entryDate) throws Exception {
		return this.pcsEntryDao.getDetailTableName(cellId, entryDate);
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
		//return pcsEntryDao.getHolidayDates(factId, entryDate);
		return pcsenrtyserviceApi.getHolidayDates(factId, entryDate);
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
		CommonFunctions.debugMsg("service implssss");
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setNameField("EMPM_NAME");
		CommonFunctions.debugMsg("service implssss1111");
		
		String condSql ="";
		if (UIUtils.isValidKeyId(sectId))			
			condSql = " AND EMPM_CELLID IN ('"+sectId+"','{}') ";		
		condSql += " AND EMPM_ACTIVE = 'Y' ";
		condSql += " AND EMPM_ISOPERATOR = 'Y' ";
		comboFilter.setCondSql(condSql);
		
		CommonFunctions.debugMsg(condSql);
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	
	public List<ComboBox> getproductModelCombo(ComboFilter currentFilter , String factId, String cellId, String entryDate) throws Exception
	{
		CommonFunctions.debugMsg("service implssss");
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		//comboFilter.setCodeField("PRMM_CODE");
		comboFilter.setIdField("PRMM_KEYID");
		comboFilter.setNameField("PRMM_NAME");
		CommonFunctions.debugMsg("service implssss1111");
		
		String condSql ="";
		//if (UIUtils.isValidKeyId(factId))			
		//	condSql = " AND PRMM_FACTORYID IN ('{}','"+factId+"') ";
		
		//if (UIUtils.isValidKeyId(mchId) && UIUtils.isValidKeyId(factId)) { 
		if (UIUtils.isValidKeyId(cellId) ) {
			condSql += " AND PRMM_KEYID IN ( SELECT PRDM_MODEL  from PCS_TL_PRODUCTMST where 1 = 1 ";  
			condSql += " AND PRDM_KEYID IN ( SELECT CYTM_PRODUCTID  FROM BAL_PCS_TL_CYCLETIMEMST  WHERE CYTM_CELLID='" + cellId + "' " ;
			//condSql += " AND CYTM_FACTORYID ='" + factId + "' " ;
		//	condSql += " AND '"+ entryDate + "' BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE  ) ) ";  
			condSql += ")) AND PRMM_ACTIVE = 'Y' ";
		}
		if (UIUtils.isValidKeyId(factId) )  
		condSql += " AND PRMM_FACTORYID='"+ factId + "'";	
		condSql += " AND PRMM_ACTIVE = 'Y' ";
		comboFilter.setCondSql(condSql);
		
		CommonFunctions.debugMsg(condSql);
		comboFilter.setTableName("PCS_TL_PRODUCTMODELMST");	
		return commonFilterDao.fillComboValues(comboFilter);		
	}

	public List<ComboBox> getShift(ComboFilter currentFilter , String factId) throws Exception
	{
		CommonFunctions.debugMsg("service implssss");
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
		
		CommonFunctions.debugMsg(condSql);
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SHIFTMST);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}

	public List<ComboBox> getproductCombo(ComboFilter currentFilter , String factId, String cellId,  String prdModelId, 
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
		
		/*
		 * if (UIUtils.isValidKeyId(cellId)) { condSql +=
		 * " AND PRDM_KEYID IN ( SELECT CYTM_PRODUCTID  FROM PCS_TL_CYCLETIMEMST " +
		 * " WHERE CYTM_CELLID='" + cellId + "'  " ; condSql += " AND CYTM_ACTIVE='Y' ";
		 * } //if (UIUtils.isValidKeyId(factId) ) // condSql +=
		 * " AND CYTM_FACTORYID ='"+factId+"'  ";
		 * CommonFunctions.debugMsg("entryDate : "+entryDate); if
		 * (UIUtils.isValidKeyId(entryDate) ) condSql += " AND '"+ entryDate +
		 * "' BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE ";//>= CYTM_FROMDATE ";
		 * 
		 * if( UIUtils.isValidKeyId(cellId) ) condSql += " ) ";
		 */
		
		if (UIUtils.isValidKeyId(cellId) || UIUtils.isValidKeyId(entryDate)) {
		    condSql += " AND PRDM_KEYID IN ( SELECT CYTM_PRODUCTID FROM BAL_PCS_TL_CYCLETIMEMST WHERE 1=1 ";

		    if (UIUtils.isValidKeyId(cellId)) {
		        condSql += " AND CYTM_CELLID='" + cellId + "' ";
		        condSql += " AND CYTM_ACTIVE='Y' ";
		    }

		    if (UIUtils.isValidKeyId(entryDate))
		        condSql += " AND TO_DATE('" + entryDate + "', 'DD-Mon-YYYY') BETWEEN CYTM_FROMDATE AND CYTM_TILLDATE ";

		    condSql += " ) ";
		}

		if (UIUtils.isValidKeyId(factId) )  
			condSql += " AND PRDM_FACTORY='"+ factId + "'";

		if (UIUtils.isValidKeyId(rawMaterial) )  
			condSql += " AND PRDM_RAWMATTYPE='"+ rawMaterial + "'";
		
		//condSql += " AND PRDM_ACTIVE = 'Y' ";
		CommonFunctions.debugMsg("Conditionsssql "+condSql);
		comboFilter.setCondSql(condSql);
		
		comboFilter.setTableName(TableNames.TBL_PCS_TL_PRODUCTMST);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	
	public List<ComboBox> getproductCombo(ComboFilter currentFilter ,  String machineId,  String prdModelId, 
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
	
	if (UIUtils.isValidKeyId(machineId)) { 
		condSql += " AND PRDM_KEYID IN ( SELECT CYTM_PRODUCTID  FROM BAL_PCS_TL_CYCLETIMEMST " +
				" WHERE CYTM_MACHINEID='" + machineId + "'  " ;
			condSql += " AND CYTM_ACTIVE='Y' "; 
	}
	//if (UIUtils.isValidKeyId(factId) )  
	//		condSql += " AND CYTM_FACTORYID ='"+factId+"'  ";
	CommonFunctions.debugMsg("entryDate : "+entryDate);
	if (UIUtils.isValidKeyId(entryDate) )  
		condSql += " AND '"+ entryDate + "' BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE ";//>= CYTM_FROMDATE ";

	if( UIUtils.isValidKeyId(machineId) )
			condSql += " ) ";

//	if (UIUtils.isValidKeyId(factId) )  
	//	condSql += " AND PRDM_FACTORY='"+ factId + "'";

	if (UIUtils.isValidKeyId(rawMaterial) )  
		condSql += " AND PRDM_RAWMATTYPE='"+ rawMaterial + "'";
	
	//condSql += " AND PRDM_ACTIVE = 'Y' ";
	CommonFunctions.debugMsg("Conditionsssql "+condSql);
	comboFilter.setCondSql(condSql);
	
	comboFilter.setTableName(TableNames.TBL_PCS_TL_PRODUCTMST);	
	return commonFilterDao.fillComboValues(comboFilter);		
}
	
	public List<ComboBox> getManpowerCombo(ComboFilter currentFilter, String cellId, String machineId, String productId,
			String entryDate,String sectId) throws Exception
		{
		ComboFilter comboFilter = currentFilter ;
	//	comboFilter.setCodeField("CYTM_MANPOWER");
		comboFilter.setIdField("CYTM_KEYID");
		comboFilter.setNameField("CYTM_MANPOWER");
		
		String condSql ="";	
		CommonFunctions.debugMsg(cellId+ "  "+machineId+" "+productId+" "+sectId+"  "+entryDate );
	    if (UIUtils.isValidKeyId(cellId))  
			condSql += " AND CYTM_CELLID ='"+cellId+"'";
		if(UIUtils.isValidKeyId(productId))
			condSql += " AND CYTM_PRODUCTID ='"+productId+"'";
		if(entryDate != null)
			condSql +=" AND  '" + entryDate +"' BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE ";
		
		CommonFunctions.debugMsg("CondSql"+condSql);
		comboFilter.setCondSql(condSql);
		comboFilter.setTableName(TableNames.TBL_BAL_PCS_TL_CYCLETIMEMST);	
		
		return commonFilterDao.fillComboValues(comboFilter);	
		//return pcsEntryDao.getManpowerCombo(cellId, machineId, productId, entryDate,sectId);	
		
		}
	
	public List<ComboBox> getLossCombo(ComboFilter currentFilter , String isQtyLoss, String sectId) throws Exception
	{
		
    /*  comboFilter.setCodeField("PRDM_DRAWINGNO");
		
		comboFilter.setIdField("PRDM_KEYID");
		comboFilter.setNameField("PRDM_NAME");
		*/
		
		ComboFilter comboFilter = currentFilter ;
		comboFilter.setCodeField("PLCM_PARAMETERNAME");
		comboFilter.setNameField("PLCM_LOSSNO");
	//	comboFilter.setName("PLCM_PARAMETERNAME");
		comboFilter.setIdField("PLCM_KEYID");
		String condSql="";		
		condSql = " AND PLCM_ISPARENT ='Y' AND PLCM_ISLOSS='M'" ;
		comboFilter.setCondSql(condSql);
		comboFilter.setTableName(TableNames.TBL_PCS_TL_LOGCONFIGURATION);
		return commonFilterDao.fillComboValues(comboFilter);	
		
		/*
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
		
		CommonFunctions.debugMsg("mouldSectionCode==="+mouldSectionCode);
		//CommonFunctions.debugMsg("sectCode==="+sectCode);
		//CommonFunctions.debugMsg("mouldSectionCode.indexOf(sectCode)==="+mouldSectionCode.indexOf(sectCode));
		
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
		
		/*
		comboFilter.setCondSql(condSql);
		comboFilter.setTableName(TableNames.TBL_PCS_VW_LOSSNAMESFORENTRY);	
		return commonFilterDao.fillComboValues(comboFilter);
		*/
	}	
	
	public List<ComboBox> getSubLossCombo(ComboFilter comboFilter,String mainlossId) throws Exception {
		
		comboFilter.setCodeField("PLCM_PARAMETERNAME");
		comboFilter.setNameField("PLCM_LOSSNO");
	//	comboFilter.setName("PLCM_PARAMETERNAME");
		comboFilter.setIdField("PLCM_KEYID");
		String condSql="";
		CommonFunctions.debugMsg("mainlossid"+mainlossId);
	//	if( mainlossId.startsWith("PLC"))  
	/*
	 * if(UIUtils.isValidKeyId(mainlossId)) { condSql +=
	 * " AND PLCM_PARENTID ='"+mainlossId+"'"; } condSql +=
	 * " AND  ( PLCM_ISLOSS = 'S' OR PLCM_ISLOSS ='M' )"; condSql +=
	 * " AND INSTR(PLCM_PARAMETERNAME,'(+)') = '0' ";
	 * //if(mainlossId.equals("PLC/000010")) condSql =condSql +
	 * " AND PLCM_PARAMETERCODE  NOT IN ('REWORKQTY' ,'REJECTEDQTY')";
	 */
		
		if (UIUtils.isValidKeyId(mainlossId)) {
		    condSql += " AND PLCM_PARENTID = '" + mainlossId + "'";
		}
		condSql += " AND (PLCM_ISLOSS = 'S' OR PLCM_ISLOSS = 'M')";
		condSql += " AND POSITION('(+)' IN PLCM_PARAMETERNAME) = 0";
		condSql += " AND PLCM_PARAMETERCODE NOT IN ('REWORKQTY', 'REJECTEDQTY')";
		//else 
		//	condSql = condSql + " AND PLCM_PARAMETERCODE  NOT IN ('REWORKQTY' ,'REJECTEDQTY','EFJ') " ;//,'DEFECTTIME'
		//condSql = condSql + " AND PLCM_PARAMETERCODE  NOT IN "+condSql;
		CommonFunctions.debugMsg("condSql"+condSql);
		comboFilter.setCondSql(condSql);
		comboFilter.setTableName(TableNames.TBL_PCS_TL_LOGCONFIGURATION);	
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	public List<ComboBox> getLossNoCombo(ComboFilter comboFilter , String isQtyLoss, String sectId) throws Exception {
		
		comboFilter.setCodeField("PLCM_PARAMETERCODE");
		comboFilter.setName("PLCM_PARAMETERNAME");
		comboFilter.setIdField("PLCM_KEYID");
		
		comboFilter.setCondSql(" AND PLCM_ISPARENT ='Y'" );
		
		comboFilter.setTableName(TableNames.TBL_PCS_TL_LOGCONFIGURATION);
		return commonFilterDao.fillComboValues(comboFilter);		
		
	}
	
	/*public List<ComboBox> getLossNoCombo(ComboFilter currentFilter , String isQtyLoss, String sectId) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		comboFilter.setIdField("KEYID");
		comboFilter.setNameField("PLCM_LOSSNO");
		comboFilter.setOrderByField("ORDERNO");
		String condSql="";
		
		String mouldSectionCode = pcsEntryDao.getMouldSectionCode(); 
		String sectCode = pcsEntryDao.getSectionCode(sectId); 
		
		CommonFunctions.debugMsg("mouldSectionCode==="+mouldSectionCode);
		
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
	*/
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
public List<ComboBox> getRejectionPhenomenaCombo(ComboFilter currentFilter ) throws Exception {
		
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter comboFilter = currentFilter ;
		comboFilter.setCodeField("QPHM_CODE");
		comboFilter.setIdField("QPHM_KEYID");
		comboFilter.setNameField("QPHM_NAME");
		comboFilter.setOrderByField("QPHM_NAME");
		
		comboFilter.setTableName(TableNames.TBL_QTM_TL_PHENOMENAMST);
		return commonFilterDao.fillComboValues(comboFilter);			
	}
	
	
	public List<ComboBox> getPhenomenaCombo(ComboFilter comboFilter, String lossId, String cellId) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("PLPM_LOSSNO");
		comboFilter.setIdField("PLPM_KEYID");
		comboFilter.setNameField("PLPM_NAME");

		String condSql ="";
		
		if (UIUtils.isValidKeyId(lossId))  
			condSql += " AND PLPM_MAINLOSS ='"+lossId+"'";
		
		if (UIUtils.isValidKeyId(cellId)) {   
			condSql += " AND  ( PLPM_MAINLOSS IN ( SELECT PLFL_PARAMETERID  FROM PCS_TL_LOSSCELLLINK WHERE PLFL_CELLID ='"+cellId+"' ) ";
			condSql += " OR PLPM_MAINLOSS IN ( SELECT PLCM_KEYID FROM PCS_TL_LOGCONFIGURATION WHERE PLCM_KEYID NOT IN (";
			condSql += "       SELECT PLFL_PARAMETERID  FROM PCS_TL_LOSSCELLLINK ) ) ) ";
		}
		
		condSql += " AND PLPM_ACTIVE ='Y' ";

		if (condSql.length() >1)
			comboFilter.setCondSql(condSql);
		
		comboFilter.setTableName(TableNames.TBL_PCS_TL_LOSSPHENOMENAMST);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}	

	/*
	 * public List<ComboBox> getLossHourNo(ComboFilter comboFilter,String cellId,
	 * String shiftId,String entryDate, String hourId) throws Exception{
	 * comboFilter.setIdField("PPRD_HOUR"); comboFilter.setCodeField("PPRD_HOUR");
	 * comboFilter.setCondSql(" and PPRD_CELLID = '"+cellId+"' and PPRD_DATE='"
	 * +entryDate+"' and PPRD_SHIFTID='"+shiftId+"'" );
	 * comboFilter.setTableName(TableNames.TBL_BAL_PCS_TL_PRODUCTIONDATA);
	 * CommonFunctions.debugMsg("comboFilter.getCondSql"+comboFilter.getCondSql());
	 * return commonFilterDao.fillComboValues(comboFilter); }
	 */
	
	public List<ComboBox> getLossHourNo(ComboFilter comboFilter, String cellId, String shiftId, String entryDate, String hourId) throws Exception {

	    if (!UIUtils.isValidKeyId(cellId) || !UIUtils.isValidKeyId(shiftId) || !UIUtils.isValidKeyId(entryDate)) {
	        CommonFunctions.debugMsg("getLossHourNo: missing cellId/shiftId/entryDate - skipping query. "
	                + "cellId=" + cellId + ", shiftId=" + shiftId + ", entryDate=" + entryDate);
	        return new ArrayList<ComboBox>(); // empty combo instead of throwing
	    }

	    comboFilter.setIdField("PPRD_HOUR");
	    comboFilter.setCodeField("PPRD_HOUR");
	    comboFilter.setCondSql(" and PPRD_CELLID = '" + cellId + "' and PPRD_DATE = '" + entryDate + "' and PPRD_SHIFTID = '" + shiftId + "'");
	    comboFilter.setTableName(TableNames.TBL_BAL_PCS_TL_PRODUCTIONDATA);
	    CommonFunctions.debugMsg("comboFilter.getCondSql" + comboFilter.getCondSql());
	    return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<String[]> getLossHourTime(String shiftId, String hourId)throws Exception{
		
		return pcsEntryDao.getLossHourTime(shiftId, hourId);
	}
	
	public List<String []> getCycletime(ComboFilter comboFilter, String cellId, String machineId, String productId,
			String entryDate,String sectId) throws Exception
			{
		//return pcsEntryDao.getCycletime(cellId, machineId, productId, entryDate,sectId);		
		return pcsenrtyserviceApi.getCycletime(cellId, machineId, productId, entryDate, sectId);
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
	//elumalai
	public BAL_PcsTlMst create(BAL_PcsTlMst newPcsTlMst,BAL_PcsTlDtl newPcsTlDtl,  PcsTlWorkorderlink newPcsTlWorkorderlink,BAL_PcsEntryBean pcsEntryBean) throws ValidationExceptions,Exception {
		try {
			CommonFunctions.debugMsg("creaate service impl");
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
			CommonFunctions.debugMsg("inside for lpp");
			
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
			CommonFunctions.debugMsg("inside for getPtwokeyid");			
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
			CommonFunctions.debugMsg("After Filling Values");
			//CommonFunctions.debugMsg("Serv Impl BDMDETAIL Size : "+newPcsTlMst.getPcsDetail().size());			
			return pcsEntryDao.create(newPcsTlMst,newPcsTlDtl,newPcsTlWorkorderlink, pcsEntryBean);
			
			
		}catch (ValidationExceptions e){			
			CommonFunctions.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}
	public BAL_PcsTlMst createNew(BAL_PcsTlMst newPcsTlMst,BAL_PcsTlDtl newPcsTlDtl, PcsTlWorkorderlink newPcsTlWorkorderlink, BAL_PcsEntryBean pcsEntryBean ) throws ValidationExceptions, Exception
	{
		try {
			CommonFunctions.debugMsg("create New service impl");
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
			CommonFunctions.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		
	}
	public BAL_PcsTlMst update(BAL_PcsTlMst newPcsTlMst, BAL_PcsTlMst oldPcsTlMst,
			BAL_PcsEntryBean pcsEntryBean) throws ValidationExceptions, Exception {
		try {
			CommonFunctions.debugMsg("creaate service impl11122222");
			String validationsFor;			
			validationsFor = "create";		
			validations.validate(newPcsTlMst,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations

			List<BAL_PcsTlDtl> bddtls = newPcsTlMst.getPcsDetail();
			validationsFor = "update";
			
			//CommonFunctions.debugMsg("bddtls.size()"+bddtls.size());
			for(BAL_PcsTlDtl pcsTlDtl : bddtls )
			{				
				CommonFunctions.debugMsg("inside for lpp");
				validations.validate(pcsTlDtl,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
			}
			fillValues(newPcsTlMst,bddtls.get(0) ,pcsEntryBean);	
			
			CommonFunctions.debugMsg("After Filling Values");
			//CommonFunctions.debugMsg("Serv Impl BDMDETAIL Size : "+newPcsTlMst.getPcsDetail().size());
			
			return pcsEntryDao.update(newPcsTlMst);		
		}catch (ValidationExceptions e){			
			CommonFunctions.debugMsg("e.getMessage():"+e.getMessage());
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
	public BAL_PcsTlLossreasonlink createLoss(BAL_PcsTlLossreasonlink newPcsTlLossreasonlink, BAL_PcsTlLossreasonlink oldPcsTlLossreasonlink, BAL_PcsEntryBean pcsEntryBean) 	throws Exception {
		String validationsFor;		
		validationsFor = "create";				
		validations.validate(newPcsTlLossreasonlink,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
		losslinkFillValues(newPcsTlLossreasonlink);
		return pcsEntryDao.createLoss(newPcsTlLossreasonlink, pcsEntryBean.getPldetailsid(), pcsEntryBean.getLossId(), pcsEntryBean.getLossValue());
	}

	public BAL_PcsTlLossreasonlink updateLoss(BAL_PcsTlLossreasonlink newPcsTlLossreasonlink,  BAL_PcsTlLossreasonlink oldPcsTlLossreasonlink, BAL_PcsEntryBean pcsEntryBean) 	throws Exception {
		String validationsFor;		
		validationsFor = "update";				
		validations.validate(newPcsTlLossreasonlink,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations		
		losslinkFillValues(newPcsTlLossreasonlink);
		
		newPcsTlLossreasonlink.setPlrkKeyid(pcsEntryBean.getPlrkKeyid());
		return pcsEntryDao.updateLoss(newPcsTlLossreasonlink, pcsEntryBean.getPldetailsid(), pcsEntryBean.getLossId(), pcsEntryBean.getLossValue());
	}

	public BAL_PcsTlLosscapture createLossCapture(BAL_PcsTlLosscapture newPcsTlLosscapture,  BAL_PcsEntryBean pcsEntryBean) 	throws BusinessApplicationExceptions, Exception {
		String validationsFor;		
		validationsFor = "create";				
		validations.validate(newPcsTlLosscapture,"BAL_pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
		pcsLossCaptureFillValues(newPcsTlLosscapture);
		
		//fill in pcs 
		BAL_PcsTlMst pcsTlMst = new BAL_PcsTlMst();
		BAL_PcsTlDtl pcsTlDtl = new BAL_PcsTlDtl();
		BAL_PcsTlLossreasonlink pcsTlLossreasonlink = new BAL_PcsTlLossreasonlink();
		
		//CommonFunctions.debugMsg("newPcstlLossCapt"+newPcsTlLosscapture.getPlosKeyid());
		//CommonFunctions.debugMsg("newPcstlLoss.getflid==="+newPcsTlLosscapture.getPlosFlid());
		
		fnFillPcsMstDtl(pcsTlMst, pcsTlDtl, newPcsTlLosscapture, pcsTlLossreasonlink, pcsEntryBean);
		
		/*if (pcsEntryBean.getFormMode().equals("update"))
			return pcsTlLosscaptureDao.update(newPcsTlLosscapture);		
		else*/
			//return pcsEntryDao.createLossCapture(newPcsTlLosscapture, pcsTlMst, pcsTlDtl, pcsTlLossreasonlink,pcsEntryBean);
		return pcsenrtyserviceApi.insertLossRecord(newPcsTlLosscapture, pcsTlMst, pcsTlDtl, pcsTlLossreasonlink,pcsEntryBean);
	}
	
	public String fnFillPcsMstDtl(BAL_PcsTlMst pcsTlMst, BAL_PcsTlDtl pcsTlDtl, BAL_PcsTlLosscapture newPcsTlLosscapture, 
			BAL_PcsTlLossreasonlink pcsTlLossreasonlink,  BAL_PcsEntryBean pcsEntryBean ) throws Exception {
		String machineId=null;
		String cellId  = pcsEntryDao.getFlidOriginalid(newPcsTlLosscapture.getPlosFlid());
		if(cellId.substring(0, 3).equals("MCH")){
			CommonFunctions.debugMsg(" in side the get cellid "+cellId);
			machineId=cellId;
			String cellid  = pcsEntryDao.getCellid(cellId);
			cellId=cellid;
			CommonFunctions.debugMsg(cellId.substring(0, 3)+ "  in side the get cellid "+cellId);
		}
		String sectId  = pcsEntryDao.getSectionForCell(cellId); 
		//String machineId  = pcsEntryDao.getSectionForCell(cellId); 

		String machid  = pcsTlMst.getPrlmMachineid();//  getMachinId(machineId); 
		//CommonFunctions.debugMsg("newPcsTlLosscapture.getPlosDate()="+newPcsTlLosscapture.getPlosDate());
		//CommonFunctions.debugMsg("newPcsTlLosscapture.getPlosFlid()="+newPcsTlLosscapture.getPlosFlid());
		CommonFunctions.debugMsg("newPcsTlLosscapture.getPlosFlid()="+newPcsTlLosscapture.getPlosFlid());
		CommonFunctions.debugMsg("pcsTlMst()="+pcsTlMst.getPrlmFactoryid());
		CommonFunctions.debugMsg("FactoryId="+pcsTlLossreasonlink.getPlrkFactoryid());
		pcsTlDtl.setPldetailsid(pcsEntryBean.getPldetailsid());
		
		pcsTlMst.setPrlmEntrydate(newPcsTlLosscapture.getPlosDate());
		pcsTlMst.setPrlmShiftid(newPcsTlLosscapture.getPlosShiftid());
		pcsTlMst.setPrlmFlid(newPcsTlLosscapture.getPlosFlid());
		
		pcsTlMst.setPrlmSectionid(sectId);
		pcsTlMst.setPrlmCellid(cellId);
		pcsTlMst.setPrlmMachineid(machineId);
		
		pcsTlMst.setPrlmEntryby(newPcsTlLosscapture.getPlosCreatedby());
		pcsTlMst.setPrlmUpdatedby(newPcsTlLosscapture.getPlosCreatedby());
		pcsTlMst.setPrlmCreatedby(newPcsTlLosscapture.getPlosCreatedby());
		
		pcsTlDtl.setCellid(cellId);
		//pcsTlDtl.setEquipId(machineId);
		
		pcsTlDtl.setPlannedqty("0");
		pcsEntryBean.setIsOpenMsr("N");
		
		List<String[]> mstDtlids = pcsEntryDao.getPcsMstDtlids(newPcsTlLosscapture,pcsTlMst, pcsTlDtl );
				
		if (mstDtlids.size()>0 ) {
			
			CommonFunctions.debugMsg("mstDtlids.get(0)[0]==="+mstDtlids.get(0)[0]);
			CommonFunctions.debugMsg("mstDtlids.get(0)[1]==="+mstDtlids.get(0)[1]);
			pcsTlMst.setPrlmKeyid(mstDtlids.get(0)[0]);
			pcsTlDtl.setPldetailsid(mstDtlids.get(0)[1]);
		}
		
		//elumalai
		String isoDate = CommonFunctions.pg_getDateTimeFromDate(newPcsTlLosscapture.getPlosDate());

		newPcsTlLosscapture.setPlosDate(isoDate);
		newPcsTlLosscapture.setPlosFromtime(
		        CommonFunctions.pg_getPGDateTimeFromDateTime(newPcsTlLosscapture.getPlosFromtime()));
		newPcsTlLosscapture.setPlosTotime(
		        CommonFunctions.pg_getPGDateTimeFromDateTime(newPcsTlLosscapture.getPlosTotime()));
		pcsTlMst.setPrlmEntrydate(isoDate);
		
		pcsTlMst = fillValues(pcsTlMst, pcsTlDtl, pcsEntryBean);
		pcsTlDtl = detailFillValues(pcsTlDtl, pcsEntryBean);
			
		//CommonFunctions.debugMsg("pcsTlMst.getPrlmDate="+pcsTlMst.getPrlmDate());
		
		//loss Reason Link
		pcsTlLossreasonlink.setPlrkFlid(newPcsTlLosscapture.getPlosFlid());
		pcsTlLossreasonlink.setPlrkSectionid(sectId);
		
		pcsTlLossreasonlink.setPlrkLossid(newPcsTlLosscapture.getPlosLossid());
	
		pcsTlLossreasonlink.setPlrkReasonid(newPcsTlLosscapture.getPlosLossreason());
		if(UIUtils.isValidKeyId(pcsEntryBean.getPlrkKeyid()))
			pcsTlLossreasonlink.setPlrkKeyid(pcsEntryBean.getPlrkKeyid());
		if(pcsTlLossreasonlink.getPlrkReasonid()==null)
			pcsTlLossreasonlink.setPlrkReasonid("-");
		pcsTlLossreasonlink.setPlrkCauseid("-");
		pcsTlLossreasonlink.setPlrkRootcauseid("-");
		pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());
		pcsTlLossreasonlink.setPlrkMachineid(machineId);
		pcsTlLossreasonlink.setPlrkWno("-");
		CommonFunctions.debugMsg("newPcsTlLosscapture.getPlosLosstime==="+newPcsTlLosscapture.getPlosLosstime());
		pcsTlLossreasonlink.setPlrkMinutes(newPcsTlLosscapture.getPlosLosstime());
		pcsTlLossreasonlink.setPlrkInstance("1");
		pcsTlLossreasonlink.setPlrkDate(newPcsTlLosscapture.getPlosDate());
		pcsTlLossreasonlink.setPlrkShiftid(newPcsTlLosscapture.getPlosShiftid());
		pcsTlLossreasonlink.setPlrkCreatedby(newPcsTlLosscapture.getPlosCreatedby());
		
		pcsTlLossreasonlink = losslinkFillValues(pcsTlLossreasonlink);
		
		return "";
	}

	public PcsTlOperatordtl createPcsEmployee(PcsTlOperatordtl newPcsTlOperatordtl,  BAL_PcsEntryBean pcsEntryBean) 	throws Exception {
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
	public String deletePcsLossEntry(String PlrkKeyid, String Pldetailsid, String sectId, String lossId, String lossVal) throws ValidationExceptions, Exception {
		return pcsEntryDao.deletePcsLossEntry(PlrkKeyid, Pldetailsid, sectId, lossId, lossVal);
	}
	public String deletePcsLossEntryBajaj(String PlosKeyid,String PlrkKeyid, String Pldetailsid, String cellId, String lossId, String lossVal,String entryDate) throws ValidationExceptions, Exception {
		return pcsEntryDao.deletePcsLossEntryBajaj(PlosKeyid,PlrkKeyid, Pldetailsid, cellId, lossId, lossVal,entryDate);
	}
	
	public String deletePcsEmployee(String Pldetailsid, String empId) throws ValidationExceptions, Exception {
		return pcsEntryDao.deletePcsEmployee(Pldetailsid, empId);
	}
	
	private BAL_PcsTlMst fillValues(BAL_PcsTlMst newPcsTlMst, BAL_PcsTlDtl newPcsTlDtl , BAL_PcsEntryBean pcsEntryBean) throws Exception {
		
		newPcsTlMst.setPrlmActive("Y");		
		//String dateTime = CommonFunctions.dateTimeNow();
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		CommonFunctions.debugMsg("Key ID : "+newPcsTlMst.getPrlmElementid());
		CommonFunctions.debugMsg("Key ID : "+newPcsTlMst.getPrlmFlid());
	
		if(newPcsTlMst.getPrlmKeyid() == null )			
		{	
			newPcsTlMst.setPrlmCreatedon(dateTime);			
		}
		if(pcsEntryBean.getShiftWise().equals("Y"))
			newPcsTlMst.setPrlmLogtype("S");
			else
				newPcsTlMst.setPrlmLogtype("H");
		if(newPcsTlMst.getPrlmLogtype()=="S"){
			newPcsTlDtl.setMachineid("0");
		}
		
		String elementId = commonFilterDao.getElementID(newPcsTlMst.getPrlmFlid());
		CommonFunctions.debugMsg("elementId==="+elementId);
		
		
		newPcsTlMst.setPrlmElementid(elementId);
		String elmnId[]=elementId.split("-");
		newPcsTlMst.setPrlmFactoryid(elmnId[2]);
		newPcsTlMst.setPrlmSectionid(elmnId[4]);
		newPcsTlMst.setPrlmCellid(elmnId[5]);
		CommonFunctions.debugMsg("elementId==="+newPcsTlMst.getPrlmFactoryid());
		CommonFunctions.debugMsg("elementId==="+newPcsTlMst.getPrlmElementid());
		if( newPcsTlMst.getPrlmElementid() == null )
			newPcsTlMst.setPrlmElementid("{}");
		CommonFunctions.debugMsg("Flid ==="+newPcsTlMst.getPrlmFlid());
		if( newPcsTlMst.getPrlmFlid() == null || newPcsTlMst.getPrlmFlid().equals("NULL"))
			newPcsTlMst.setPrlmFlid("{}");
		
		if(newPcsTlMst.getPrlmDate()==null)
		newPcsTlMst.setPrlmDate(CommonFunctions.pg_dateTimeNow());
	
		if(UIUtils.isValidKeyId(newPcsTlMst.getPrlmMachineid()))
			newPcsTlMst.setPrlmMachineid(newPcsTlMst.getPrlmMachineid());
		else
			newPcsTlMst.setPrlmMachineid("{}");
		//newPcsTlMst.setPrlmEntrydate(CommonFunctions.getDate());
		newPcsTlMst.setPrlmUpdateddate(dateTime);
		
		newPcsTlMst.setPrlmApporvedflag("Y");  //y,n,e,c
		newPcsTlMst.setPrlmApprovedby("{}");
		newPcsTlMst.setPrlmApprovedate(dateTime);
		newPcsTlMst.setPrlmShiftincharge("{}");
		newPcsTlMst.setPrlmSubgroupid("{}");
		
		newPcsTlMst.setPrlmIsnoplan("N");  
		

		newPcsTlMst.setPrlmTimeorqty("T");
		newPcsTlMst.setPrlmCompletedflag("{}");
		newPcsTlMst.setPrlmTempfield2("{}");
		newPcsTlMst.setPrlmActive("Y");
		
		newPcsTlMst.setPrlmCreatedon(dateTime);
		newPcsTlMst.setPrlmModifiedon(dateTime);
	
		//newPcsTlMst.setPcsDetail(detailFillValues(newPcsTlMst,oldPcsTlMst,pcsEntryBean));
		//newPcsTlMst.setPcslosslink(losslinkFillValues(newPcsTlMst,oldPcsTlMst,pcsEntryBean));
		
			//CommonFunctions.debugMsg("getBdmDetail "+	newPcsTlMst.getBdmDetail().get(0).toString());
		return newPcsTlMst;
		
	}
	
private BAL_PcsTlHtProductionHistory fillValues(BAL_PcsTlHtProductionHistory newPcsTlHtProductionHistory, BAL_PcsTlHtDetails newPcsTlHtDetails , BAL_PcsEntryBean pcsEntryBean){
		
	newPcsTlHtProductionHistory.setActive("Y");		
		//String dateTime = CommonFunctions.dateTimeNow();
	     String dateTime = CommonFunctions.pg_dateTimeNow();
		
	
		if(newPcsTlHtProductionHistory.getKeyid() == null )			
		{	
			newPcsTlHtProductionHistory.setCreatedon(dateTime);			
		}
			CommonFunctions.debugMsg(" get Cell Id in service impl"+newPcsTlHtProductionHistory.getCellid());	
		if(newPcsTlHtProductionHistory.getDate()==null)
			newPcsTlHtProductionHistory.setDate(CommonFunctions.getDate());
		
		if(newPcsTlHtProductionHistory.getArea()==null)
			newPcsTlHtProductionHistory.setArea("{}");
		if(newPcsTlHtProductionHistory.getBatchQuantity()==null)
			newPcsTlHtProductionHistory.setBatchQuantity("{}");
		if(newPcsTlHtProductionHistory.getFromDate()==null)
			newPcsTlHtProductionHistory.setFromDate(CommonFunctions.getDate());
		if(newPcsTlHtProductionHistory.getToDate()==null)
			newPcsTlHtProductionHistory.setToDate(CommonFunctions.getDate());		
		if(newPcsTlHtProductionHistory.getProductid()==null)
			newPcsTlHtProductionHistory.setProductid("{}");
		if(newPcsTlHtProductionHistory.getShiftid()==null)
			newPcsTlHtProductionHistory.setShiftid("{}");
		if(newPcsTlHtProductionHistory.getRemarks()==null)
			newPcsTlHtProductionHistory.setRemarks("{}");
		if(newPcsTlHtProductionHistory.getTempField1()==null)
			newPcsTlHtProductionHistory.setTempField1("-");
		if(newPcsTlHtProductionHistory.getTempField2()==null)
			newPcsTlHtProductionHistory.setTempField2("-");
		if(newPcsTlHtProductionHistory.getTempField3()==null)
			newPcsTlHtProductionHistory.setTempField3("-");
		
	/*
		if(!UIUtils.isValidKeyId(newPcsTlHtProductionHistory.get()))
			newPcsTlMst.setPrlmMachineid("{}");
		//newPcsTlMst.setPrlmEntrydate(CommonFunctions.getDate());
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
		newPcsTlMst.setPrlmModifiedon(dateTime); */
	
		//newPcsTlMst.setPcsDetail(detailFillValues(newPcsTlMst,oldPcsTlMst,pcsEntryBean));
		//newPcsTlMst.setPcslosslink(losslinkFillValues(newPcsTlMst,oldPcsTlMst,pcsEntryBean));
		
			//CommonFunctions.debugMsg("getBdmDetail "+	newPcsTlMst.getBdmDetail().get(0).toString());
		return newPcsTlHtProductionHistory;
		
	}

	private PcsTlWorkorderlink workOrderFillValues(PcsTlWorkorderlink newPcsTlWorkorderlink,BAL_PcsEntryBean pcsEntryBean) 
	{
		CommonFunctions.debugMsg("work order 1");
		String dateTime = CommonFunctions.dateTimeNow();
		
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
		CommonFunctions.debugMsg("workingTime"+workingTime);
		CommonFunctions.debugMsg("CALENDAR :"+Float.parseFloat(newPcsTlWorkorderlink.getPtwoCalendartime()));
		newPcsTlWorkorderlink.setPtwoProductiontime(workingTime.toString());
		
		Float unaccTime = Float.parseFloat(newPcsTlWorkorderlink.getPtwoCalendartime()) - workingTime;
		unaccTime = Float.parseFloat(String.format("%.2f", unaccTime));
		CommonFunctions.debugMsg("unaccTime"+unaccTime);
		newPcsTlWorkorderlink.setPtwoUnaccountedtime(unaccTime.toString());
		
		if (newPcsTlWorkorderlink.getPtwoTemp1() == null)
			newPcsTlWorkorderlink.setPtwoTemp1("{}");			
		if (newPcsTlWorkorderlink.getPtwoTemp2() == null)
			newPcsTlWorkorderlink.setPtwoTemp2("{}");
		if (newPcsTlWorkorderlink.getPtwoTemp3() == null)
			newPcsTlWorkorderlink.setPtwoTemp3("{}");
		
		return newPcsTlWorkorderlink;		
	}
	
	private BAL_PcsTlDtl detailFillValues(BAL_PcsTlDtl newPcsTlDtl,BAL_PcsEntryBean pcsEntryBean) 
	{
		CommonFunctions.debugMsg("Detail 1 detailFillValues");
		//CommonFunctions.debugMsg("Chk Box : " +bdFormBean.getIssparesY());		
		//String dateTime = CommonFunctions.dateTimeNow();
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		CommonFunctions.debugMsg("Detail 2  $$$$$$ " +dateTime);
		
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
		
		CommonFunctions.debugMsg("Before Convert calendarTime"+newPcsTlDtl.getCalendartime());
		CommonFunctions.debugMsg("Before Convert calendarTime"+pcsEntryBean.getAvailtime());
		
		if (!UIUtils.isValidKeyId(newPcsTlDtl.getCalendartime()))
			newPcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
		
/*		Float calendarTime = (float) 0;	
		calendarTime = Float.parseFloat(newPcsTlDtl.getCalendartime().toString());				
		newPcsTlDtl.setCalendartime(df.format(calendarTime).toString());
	*/	
		if (newPcsTlDtl.getCalendartime() == null)
			newPcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
		
		CommonFunctions.debugMsg("Before Convert calendarTime"+newPcsTlDtl.getCalendartime() );

		if (newPcsTlDtl.getMachineid() == null)
			newPcsTlDtl.setMachineid("0");
		
		if (newPcsTlDtl.getNoplaninmins() == null)
			newPcsTlDtl.setNoplaninmins("0");
		
		if (newPcsTlDtl.getNoofproducts() == null)
			newPcsTlDtl.setNoofproducts("1");

		if (newPcsTlDtl.getRawmaterialtype() == null)
			newPcsTlDtl.setRawmaterialtype("{}");

		if (newPcsTlDtl.getWeight() == null)
			newPcsTlDtl.setWeight("0");

		if (newPcsTlDtl.getWno() == null)
			newPcsTlDtl.setWno("0");
		
		CommonFunctions.debugMsg("newPcsTlDtl.getweight()"+newPcsTlDtl.getWeight());

		if (newPcsTlDtl.getOperationno()== null)
			newPcsTlDtl.setOperationno("-");

		newPcsTlDtl.setOperationdescription("{}");

		if (newPcsTlDtl.getBatchno() == null)
			newPcsTlDtl.setBatchno("-");

		if (newPcsTlDtl.getCavityavailable() == null)
			newPcsTlDtl.setCavityavailable("1");

	// COMMENTED BY KIRAN	
		if (newPcsTlDtl.getCavityused() == null)
			newPcsTlDtl.setCavityused("1");

		if (newPcsTlDtl.getMandrelavailable() == null)
			newPcsTlDtl.setMandrelavailable("0");

		if (newPcsTlDtl.getMandrelused() == null)
			newPcsTlDtl.setMandrelused("0");
	
		if (newPcsTlDtl.getProducedqty()== null || newPcsTlDtl.getProducedqty()== " ")
			newPcsTlDtl.setProducedqty("0");

		if (newPcsTlDtl.getPlannedqty()== null || newPcsTlDtl.getPlannedqty()== "" || newPcsTlDtl.getPlannedqty()== " ")
			newPcsTlDtl.setPlannedqty("0");
		else
		{

    		Float planQty = (float) 0;	
			planQty = Float.parseFloat(newPcsTlDtl.getPlannedqty().toString());
			CommonFunctions.debugMsg("planQty"+planQty);
			//planQty = Float.parseFloat(String.format("%.2g%n", planQty));
			String planQty1 = df.format(planQty).toString().replace(",", "");
			CommonFunctions.debugMsg("planQty1"+planQty1);
			newPcsTlDtl.setPlannedqty(planQty1);		
		}
		
		/*
		 * if(!UIUtils.isValidKeyId(newPcsTlDtl.getModelchangepart()))
		 * newPcsTlDtl.setModelchangepart("{}");
		 */
		newPcsTlDtl.setDefectsandreworklossMl("0");
		//newPcsTlDtl.setRejectedqty(null);
		newPcsTlDtl.setCavityavailable("1");
		newPcsTlDtl.setDefecttimeSl("0");
		newPcsTlDtl.setProductionlosses("0");
		newPcsTlDtl.setEquipmentfailureMl("0");
		newPcsTlDtl.setSetupandadjustmentMl("0");
		newPcsTlDtl.setToolchangelossMl("0");
		newPcsTlDtl.setMinorstoppagelossMl("0");
		newPcsTlDtl.setSpeedlossMl("0");
		newPcsTlDtl.setShutdownlossMl("0");
		newPcsTlDtl.setManagementlossMl("0");
		newPcsTlDtl.setCommonutilitylossSl("0");
		newPcsTlDtl.setOperatingmotionlossMl("0");
		newPcsTlDtl.setLineorganisationlossMl("0");
		newPcsTlDtl.setLogisticslossMl("0");
		newPcsTlDtl.setMeasuringandadjlossMl("0");
		newPcsTlDtl.setDietoolandjiglossMl("0");
		newPcsTlDtl.setEnergylossMl("0");
		newPcsTlDtl.setYieldlossMl("0");
		newPcsTlDtl.setUnaccountedtime("0");
		newPcsTlDtl.setLoadingtime("0");
		newPcsTlDtl.setEffectiveprodmins("0");
		newPcsTlDtl.setMchavailabletime("0");
		newPcsTlDtl.setProductionavltime("0");
		newPcsTlDtl.setProductiontime("0");
		newPcsTlDtl.setRoa("0");
		newPcsTlDtl.setRop("0");
		newPcsTlDtl.setRoq("0");
		newPcsTlDtl.setOee("0");
		
		newPcsTlDtl.setNoplaninmins("0");
		if(pcsEntryBean.getUnreptimehourly() != null)
			newPcsTlDtl.setUnaccountedtime(pcsEntryBean.getUnreptimehourly());
		if(pcsEntryBean.getProdtimehourly() != null)
			newPcsTlDtl.setProductiontime(pcsEntryBean.getProdtimehourly());
		if(pcsEntryBean.getAvailtimehourly() != null)
		{
			newPcsTlDtl.setProductionavltime(pcsEntryBean.getAvailtimehourly());
			newPcsTlDtl.setMchavailabletime(pcsEntryBean.getAvailtimehourly());
		//	newPcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
		}
		
		
		CommonFunctions.debugMsg("pcsEntryBean.getIsOpenMsr()"+pcsEntryBean.getIsOpenMsr());
		
		if (pcsEntryBean.getIsOpenMsr()==null)		
			newPcsTlDtl.setCompletedflag("C");
		else
			newPcsTlDtl.setCompletedflag("C");
	/*	if (pcsEntryBean.getIsOpenMsr().equals("Y"))		
			newPcsTlDtl.setCompletedflag("P");
		else
			newPcsTlDtl.setCompletedflag("C");
		*/
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getRemarks()))
			newPcsTlDtl.setRemarks("<**>");
		
		CommonFunctions.debugMsg(newPcsTlDtl.getProducedqty());
		
	/*	Float workingTime = Float.parseFloat(newPcsTlDtl.getActualcycletime()) * Float.parseFloat(newPcsTlDtl.getProducedqty());
		//workingTime =  (float) Math.ceil(workingTime);
		//workingTime =  (float) Math.round(workingTime);
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		workingTime= Float.parseFloat(String.format("%.2g%n", workingTime));
		CommonFunctions.debugMsg("workingTime"+workingTime);
		CommonFunctions.debugMsg("CALENDAR :"+Float.parseFloat(newPcsTlDtl.getCalendartime()));
		newPcsTlDtl.setProductiontime(workingTime.toString());
		Float unaccTime = Float.parseFloat(newPcsTlDtl.getCalendartime()) - workingTime;
		//unaccTime = Float.parseFloat(String.format("%.2g%n", unaccTime));
		unaccTime = Float.parseFloat(String.format("%.2f", unaccTime));
		CommonFunctions.debugMsg("unaccTime"+unaccTime);
		newPcsTlDtl.setUnaccountedtime(unaccTime.toString());
		
		
		Float cycleTimeVar = Float.parseFloat(newPcsTlDtl.getActualcycletime()) - Float.parseFloat(newPcsTlDtl.getTheoriticalcycletime());
		CommonFunctions.debugMsg("Cycle Time Var : "+cycleTimeVar);
		Float cycTime = Float.parseFloat(newPcsTlDtl.getProducedqty())/Float.parseFloat(newPcsTlDtl.getCavityused());
		CommonFunctions.debugMsg("cycTime : "+cycTime);
			  cycleTimeVar = cycleTimeVar * cycTime;
		      cycleTimeVar = cycleTimeVar/Float.parseFloat(newPcsTlDtl.getTheoriticalcycletime());
		      cycleTimeVar = cycleTimeVar * Float.parseFloat(newPcsTlDtl.getCavityused());
		CommonFunctions.debugMsg("Cycle Time Var : "+cycleTimeVar);
		newPcsTlDtl.setLoss26(cycleTimeVar.toString());
		
		Float apcu =  Float.parseFloat(newPcsTlDtl.getProductiontime())/Float.parseFloat(newPcsTlDtl.getCavityused());
		CommonFunctions.debugMsg("apcu : "+apcu);
		Float cacu =  Float.parseFloat(newPcsTlDtl.getCavityavailable())-Float.parseFloat(newPcsTlDtl.getCavityused());
		CommonFunctions.debugMsg("cacu : "+cacu);
			  apcu = apcu * cacu;
		CommonFunctions.debugMsg("apcu : "+apcu);
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
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss01()))
			newPcsTlDtl.setLoss01("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss02()))
			newPcsTlDtl.setLoss02("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss03()))
			newPcsTlDtl.setLoss03("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss04()))
			newPcsTlDtl.setLoss04("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss05()))
			newPcsTlDtl.setLoss05("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss06()))
			newPcsTlDtl.setLoss06("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss07()))
			newPcsTlDtl.setLoss07("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss08()))
			newPcsTlDtl.setLoss08("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss09()))
			newPcsTlDtl.setLoss09("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss10()))
			newPcsTlDtl.setLoss10("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss11()))
			newPcsTlDtl.setLoss11("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss12()))
			newPcsTlDtl.setLoss12("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss13()))
			newPcsTlDtl.setLoss13("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss14()))
			newPcsTlDtl.setLoss14("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss15()))
				newPcsTlDtl.setLoss15("0");
			if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss16()))
				newPcsTlDtl.setLoss16("0");
			
			if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss17()))
				newPcsTlDtl.setLoss17("0");
			if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss18()))
				newPcsTlDtl.setLoss18("0");
			if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss19()))
					newPcsTlDtl.setLoss19("0");	
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss20()))
			newPcsTlDtl.setLoss20("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss21()))
			newPcsTlDtl.setLoss21("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss22()))
			newPcsTlDtl.setLoss22("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss23()))
			newPcsTlDtl.setLoss23("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss24()))
			newPcsTlDtl.setLoss24("0");
		
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss25()))
			newPcsTlDtl.setLoss25("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss26()))
			newPcsTlDtl.setLoss26("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss27()))
			newPcsTlDtl.setLoss27("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss28()))
			newPcsTlDtl.setLoss28("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss29()))
			newPcsTlDtl.setLoss29("0");
				
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss30()))
			newPcsTlDtl.setLoss30("0");		
	
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss31()))
			newPcsTlDtl.setLoss31("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss32()))
			newPcsTlDtl.setLoss32("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss33()))
			newPcsTlDtl.setLoss33("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss34()))
			newPcsTlDtl.setLoss34("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss35()))
			newPcsTlDtl.setLoss35("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss36()))
			newPcsTlDtl.setLoss36("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss37()))
			newPcsTlDtl.setLoss37("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss38()))
			newPcsTlDtl.setLoss38("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss39()))
			newPcsTlDtl.setLoss39("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss40()))
			newPcsTlDtl.setLoss40("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss41()))
			newPcsTlDtl.setLoss41("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss42()))
			newPcsTlDtl.setLoss42("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss43()))
			newPcsTlDtl.setLoss43("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss44()))
			newPcsTlDtl.setLoss44("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss45()))
			newPcsTlDtl.setLoss45("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss46()))
			newPcsTlDtl.setLoss46("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss47())||newPcsTlDtl.getLoss47()==null)
			newPcsTlDtl.setLoss47("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss48()))
			newPcsTlDtl.setLoss48("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss49())||newPcsTlDtl.getLoss49()==null)
			newPcsTlDtl.setLoss49("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss50()))
			newPcsTlDtl.setLoss50("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss51()))
			newPcsTlDtl.setLoss51("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss52()))
			newPcsTlDtl.setLoss52("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss53()))
			newPcsTlDtl.setLoss53("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss54()))
			newPcsTlDtl.setLoss54("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss55()))
			newPcsTlDtl.setLoss55("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss56()))
			newPcsTlDtl.setLoss56("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss57()))
			newPcsTlDtl.setLoss57("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss58()))
			newPcsTlDtl.setLoss58("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss59()))
			newPcsTlDtl.setLoss59("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss60()))
			newPcsTlDtl.setLoss60("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss61()))
			newPcsTlDtl.setLoss61("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss62()))
			newPcsTlDtl.setLoss62("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss63()))
			newPcsTlDtl.setLoss63("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss64()))
			newPcsTlDtl.setLoss64("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss65()))
			newPcsTlDtl.setLoss65("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss66()))
			newPcsTlDtl.setLoss66("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss67()))
			newPcsTlDtl.setLoss67("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss68()))
			newPcsTlDtl.setLoss68("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss69()))
			newPcsTlDtl.setLoss69("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss70()))
			newPcsTlDtl.setLoss70("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss71()))
			newPcsTlDtl.setLoss71("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss72()))
			newPcsTlDtl.setLoss72("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss73()))
			newPcsTlDtl.setLoss73("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss74()))
			newPcsTlDtl.setLoss74("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss75()))
			newPcsTlDtl.setLoss75("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss76()))
			newPcsTlDtl.setLoss76("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss77()))
			newPcsTlDtl.setLoss77("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss78()))
			newPcsTlDtl.setLoss78("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss79()))
			newPcsTlDtl.setLoss79("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss80()))
			newPcsTlDtl.setLoss80("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss81()))
			newPcsTlDtl.setLoss81("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss82()))
			newPcsTlDtl.setLoss82("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss83()))
			newPcsTlDtl.setLoss83("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss84()))
			newPcsTlDtl.setLoss84("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss85()))
			newPcsTlDtl.setLoss85("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss86()))
			newPcsTlDtl.setLoss86("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss87()))
			newPcsTlDtl.setLoss87("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss88()))
			newPcsTlDtl.setLoss88("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss89()))
			newPcsTlDtl.setLoss89("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss90()))
			newPcsTlDtl.setLoss90("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss91()))
			newPcsTlDtl.setLoss91("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss92()))
			newPcsTlDtl.setLoss92("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss93()))
			newPcsTlDtl.setLoss93("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss94()))
			newPcsTlDtl.setLoss94("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss95()))
			newPcsTlDtl.setLoss95("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss96()))
			newPcsTlDtl.setLoss96("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss97()))
			newPcsTlDtl.setLoss97("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss98()))
			newPcsTlDtl.setLoss98("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss99()))
			newPcsTlDtl.setLoss99("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss100()))
			newPcsTlDtl.setLoss100("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss101()))
			newPcsTlDtl.setLoss101("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss102()))
			newPcsTlDtl.setLoss102("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss103()))
			newPcsTlDtl.setLoss103("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss104()))
			newPcsTlDtl.setLoss104("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss105()))
			newPcsTlDtl.setLoss105("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss106()))
			newPcsTlDtl.setLoss106("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss107()))
			newPcsTlDtl.setLoss107("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss108()))
			newPcsTlDtl.setLoss108("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss109())||newPcsTlDtl.getLoss109()==null)
			newPcsTlDtl.setLoss109("0");
		
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss110())||newPcsTlDtl.getLoss110()==null)
			newPcsTlDtl.setLoss110("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss111())||newPcsTlDtl.getLoss111()==null)
			newPcsTlDtl.setLoss111("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss112())||newPcsTlDtl.getLoss112()==null)
			newPcsTlDtl.setLoss112("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss114()))
			newPcsTlDtl.setLoss114("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss115()))
			newPcsTlDtl.setLoss115("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss116()))
			newPcsTlDtl.setLoss116("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss117()))
			newPcsTlDtl.setLoss117("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss118()))
			newPcsTlDtl.setLoss118("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss117()))
			newPcsTlDtl.setLoss117("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss118()))
			newPcsTlDtl.setLoss118("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss119()))
			newPcsTlDtl.setLoss119("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss120()))
			newPcsTlDtl.setLoss120("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss121()) || newPcsTlDtl.getLoss121()== null)
			newPcsTlDtl.setLoss121("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLoss122()) || newPcsTlDtl.getLoss122()==null)
			newPcsTlDtl.setLoss122("0");
		
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
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getModelchangepart()))
			newPcsTlDtl.setModelchangepart("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getReprocessing()))
			newPcsTlDtl.setReprocessing("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getINSPECTEDQTY()))
			newPcsTlDtl.setINSPECTEDQTY("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getQAACCEPTEDQTY()))
			newPcsTlDtl.setQAACCEPTEDQTY("0");
		
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getTrimmingqty()))
			newPcsTlDtl.setTrimmingqty("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getExpansionqty()))
			newPcsTlDtl.setExpansionqty("0");
		
		if(newPcsTlDtl.getLoss122()==null)
			newPcsTlDtl.setLoss122("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getPokaYokeMl()))
			newPcsTlDtl.setPokaYokeMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getJigfixtureMl()))
		    newPcsTlDtl.setJigfixtureMl("0");
		if(!UIUtils.isValidKeyId(newPcsTlDtl.getLineorganisationlossMl()))
			newPcsTlDtl.setLineorganisationlossMl("0");    
		
	/*	Float lossCalcn = Float.parseFloat(newPcsTlDtl.getSetupandadjustmentMl()) + Float.parseFloat(newPcsTlDtl.getStartuplossMl());
		CommonFunctions.debugMsg("lossCalcn1 : "+lossCalcn);
			  lossCalcn = lossCalcn + Float.parseFloat(newPcsTlDtl.getMeasuringandadjlossMl()) + Float.parseFloat(newPcsTlDtl.getShutdownlossMl());
		CommonFunctions.debugMsg("lossCalcn 2: "+lossCalcn);
			  lossCalcn = lossCalcn +  Float.parseFloat(newPcsTlDtl.getLineorganisationlossMl())+ Float.parseFloat(newPcsTlDtl.getToolchangelossMl());
		CommonFunctions.debugMsg("lossCalcn3 : "+lossCalcn);
			  lossCalcn = lossCalcn + Float.parseFloat(newPcsTlDtl.getOperatingmotionlossMl()) + Float.parseFloat(newPcsTlDtl.getLogisticslossMl());
		CommonFunctions.debugMsg("lossCalcn4 : "+lossCalcn);
			  lossCalcn = lossCalcn + Float.parseFloat(newPcsTlDtl.getEquipmentfailureMl());
		CommonFunctions.debugMsg("lossCalcn : "+lossCalcn);
		Float downTime =  lossCalcn + Float.parseFloat(newPcsTlDtl.getManagementlossMl()) + Float.parseFloat(newPcsTlDtl.getMinorstoppagelossMl());
		CommonFunctions.debugMsg("downTime : "+downTime);
		
		Float loadingTime = Float.parseFloat(newPcsTlDtl.getMchavailabletime()) - Float.parseFloat(newPcsTlDtl.getManagementlossMl());
		CommonFunctions.debugMsg("loadingTime : "+loadingTime);
	    newPcsTlDtl.setLoadingtime(loadingTime.toString());
		
	  
	    Float avlLoss =  Float.parseFloat(newPcsTlDtl.getMchavailabletime()) - Float.parseFloat(newPcsTlDtl.getProductionavltime());
	    CommonFunctions.debugMsg("avlLoss : "+avlLoss);
	    
	    Float prodAvltime =  Float.parseFloat(newPcsTlDtl.getLoss41()) + Float.parseFloat(newPcsTlDtl.getLoss30()) + Float.parseFloat(newPcsTlDtl.getLoss42()) ;
	    	  prodAvltime =   prodAvltime + Float.parseFloat(newPcsTlDtl.getLoss40()) + Float.parseFloat(newPcsTlDtl.getLoss43()) + Float.parseFloat(newPcsTlDtl.getLoss20()) ;
	    	  prodAvltime =   prodAvltime + Float.parseFloat(newPcsTlDtl.getNoplaninmins());
	    	  prodAvltime = Float.parseFloat(newPcsTlDtl.getMchavailabletime()) - prodAvltime;
	    CommonFunctions.debugMsg("prodAvltime : "+prodAvltime);
	    newPcsTlDtl.setProductionavltime(prodAvltime.toString());
	    newPcsTlDtl.setProductiontime(prodAvltime.toString());
	    */
	    /*Float utilTime =  Float.parseFloat(newPcsTlDtl.getLoadingtime()) - lossCalcn;
	    CommonFunctions.debugMsg("utilTime : "+utilTime);
	    
	    Float actProdn = Float.parseFloat(newPcsTlDtl.getTheoriticalcycletime())/Float.parseFloat(newPcsTlDtl.getCavityused());
	    	  actProdn = Float.parseFloat(newPcsTlDtl.getProducedqty()) * actProdn; 
	    CommonFunctions.debugMsg("actProdn : "+actProdn);*/
		
	    //newPcsTlDtl.setProductiontime(actProdn.toString());
	  
	  /*  Float roq =   Float.parseFloat(newPcsTlDtl.getRejectedqty()) +  Float.parseFloat(newPcsTlDtl.getReworkqty());
	    	  roq =   Float.parseFloat(newPcsTlDtl.getProducedqty()) - roq; 
	    	  roq =   roq/Float.parseFloat(newPcsTlDtl.getProducedqty()); 
	    CommonFunctions.debugMsg("roq : "+roq);
	    newPcsTlDtl.setRoq(roq.toString());
	    
	    Float oee = Float.parseFloat(newPcsTlDtl.getRoa()) *  Float.parseFloat(newPcsTlDtl.getRop()) *  Float.parseFloat(newPcsTlDtl.getRoq());
	    newPcsTlDtl.setOee(oee.toString());*/
	   // Float avlRate = newPcsTlDtl.get
	    //newPcsTlDtl.setp
		//newPcsTlDtl.setModelchangepart("{}");		
		
		//genTlEmployeedtl.setEmpdModifiedon(dateTime); detailFillValues(newPcsTlHtDetails, pcsEntryBean);

	return newPcsTlDtl;
	}
	
	private BAL_PcsTlHtDetails detailFillValues(BAL_PcsTlHtDetails newPcsTlHtDetails, BAL_PcsEntryBean pcsEntryBean) 
	{
		CommonFunctions.debugMsg("Detail 1 detailFillValues");
		//CommonFunctions.debugMsg("Chk Box : " +bdFormBean.getIssparesY());		
		//String dateTime = CommonFunctions.dateTimeNow();
		String dateTime = CommonFunctions.pg_dateTimeNow();
		BAL_PcsTlHtProductionHistory newPcsTlHtProductionHistory = new BAL_PcsTlHtProductionHistory();
		CommonFunctions.debugMsg("Detail 2");
		
		NumberFormat df = DecimalFormat.getInstance();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		df.setRoundingMode(RoundingMode.UP);
		
		
		newPcsTlHtDetails.setCreatedon(dateTime);
		newPcsTlHtDetails.setModifiedon(dateTime);
		newPcsTlHtDetails.setActive("Y");
		//newPcsTlDtl.setCreatedby(newPcsTlMst.getPrlmCreatedby());						
		//newPcsTlDtl.setPlmasterid(newPcsTlMst.getPrlmKeyid());
		//newPcsTlDtl.setCellid(newPcsTlMst.getPrlmCellid());
		newPcsTlHtDetails.setTempField1("-");
		newPcsTlHtDetails.setTempField2("-");
		newPcsTlHtDetails.setTempField3("-");

		
		//CommonFunctions.debugMsg("Before Convert calendarTime"+newPcsTlDtl.getCalendartime());
		CommonFunctions.debugMsg("Before Convert calendarTime"+pcsEntryBean.getAvailtime());
		
		if (!UIUtils.isValidKeyId(newPcsTlHtDetails.getCalendarTime()))
			newPcsTlHtDetails.setCalendarTime(pcsEntryBean.getAvailtimehourly());
		
/*		Float calendarTime = (float) 0;	
		calendarTime = Float.parseFloat(newPcsTlDtl.getCalendartime().toString());				
		newPcsTlDtl.setCalendartime(df.format(calendarTime).toString());
	*/	
		if (newPcsTlHtDetails.getCalendarTime() == null)
			newPcsTlHtDetails.setCalendarTime(pcsEntryBean.getAvailtimehourly());
		
		CommonFunctions.debugMsg("Before Convert calendarTime"+newPcsTlHtDetails.getCalendarTime() );

		if (newPcsTlHtDetails.getMasterid() == null)
			newPcsTlHtDetails.setMasterid(newPcsTlHtProductionHistory.getKeyid());
		if (newPcsTlHtDetails.getCalculatedCharges()==null)
			newPcsTlHtDetails.setCalculatedCharges("0");

		newPcsTlHtDetails.setBreaks("{}");

		if (newPcsTlHtDetails.getCycleTimeVariation() == "")
			newPcsTlHtDetails.setCycleTimeVariation("0");

		if (newPcsTlHtDetails.getCommonUtilities() == "")
			newPcsTlHtDetails.setCommonUtilities("0");

		if (newPcsTlHtDetails.getDefectStdLoss() == null)
			newPcsTlHtDetails.setDefectStdLoss("0");

		if (newPcsTlHtDetails.getEffProdmins() == null)
			newPcsTlHtDetails.setEffProdmins("0");

		if (newPcsTlHtDetails.getEqElectrical() == "")
			newPcsTlHtDetails.setEqElectrical(null);
		if (newPcsTlHtDetails.getEqElectronics() == "")
			newPcsTlHtDetails.setEqElectronics(null);
		if (newPcsTlHtDetails.getEqMechanical() == "")
			newPcsTlHtDetails.setEqMechanical(null);
		if (newPcsTlHtDetails.getEqJigfixture() == "")
			newPcsTlHtDetails.setEqJigfixture(null);
		if (newPcsTlHtDetails.getNoplanMints() == "")
			newPcsTlHtDetails.setNoplanMints(null);
		

		if (newPcsTlHtDetails.getPlanedQty()== null || newPcsTlHtDetails.getPlanedQty()== "" || newPcsTlHtDetails.getPlanedQty()== " ")
			newPcsTlHtDetails.setPlanedQty("0");
		else
		{

    		Float planQty = (float) 0;	
			planQty = Float.parseFloat(newPcsTlHtDetails.getPlanedQty().toString());
			CommonFunctions.debugMsg("planQty"+planQty);
			//planQty = Float.parseFloat(String.format("%.2g%n", planQty));
			String planQty1 = df.format(planQty).toString().replace(",", "");
			CommonFunctions.debugMsg("planQty1"+planQty1);
			newPcsTlHtDetails.setPlanedQty(planQty1);		
		}
		
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getLineChangOvr()))
			newPcsTlHtDetails.setLineChangOvr("{}");
		newPcsTlHtDetails.setEquipmentFailuare(null);
		//newPcsTlHtDetails.setRejectedqty(null);
		newPcsTlHtDetails.setJhTime(null);
		newPcsTlHtDetails.setLoadingTime(null);
		newPcsTlHtDetails.setLogisticLoss(null);
		newPcsTlHtDetails.setManagementLoss(null);
		newPcsTlHtDetails.setMeating(null);
		newPcsTlHtDetails.setMinorstopage(null);
		newPcsTlHtDetails.setTrainig(null);
		newPcsTlHtDetails.setTheoroticalCycletime(null);
		newPcsTlHtDetails.setShutDownLoss(null);
		newPcsTlHtDetails.setStartupLoss(null);
		newPcsTlHtDetails.setSpeedLoss(null);
		newPcsTlHtDetails.setNewProdDev(null);
		newPcsTlHtDetails.setNoAir(null);
		newPcsTlHtDetails.setNoGas(null);
		newPcsTlHtDetails.setNoPlanFrnng(null);
		newPcsTlHtDetails.setNoPower(null);
		newPcsTlHtDetails.setNoCoolent(null);
		newPcsTlHtDetails.setLogisticLoss(null);
		newPcsTlHtDetails.setNoManPower(null);
		newPcsTlHtDetails.setNoMaterialPpc(null);
		newPcsTlHtDetails.setNoofProduct(null);
		newPcsTlHtDetails.setNoTool(null);
		newPcsTlHtDetails.setPmTime(null);
		newPcsTlHtDetails.setProcessStopage(null);
		newPcsTlHtDetails.setProductionLoss(null);
		newPcsTlHtDetails.setProductionTimeLoss(null);
		newPcsTlHtDetails.setRoa(null);
		newPcsTlHtDetails.setRop(null);
		newPcsTlHtDetails.setRoq(null);
		newPcsTlHtDetails.setOee(null);
		
		newPcsTlHtDetails.setNoplanMints("0");
			
		CommonFunctions.debugMsg("pcsEntryBean.getIsOpenMsr()"+pcsEntryBean.getIsOpenMsr());
		
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getRemarks()))
			newPcsTlHtDetails.setRemarks("<**>");
		
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getShutDownLoss()) ||newPcsTlHtDetails.getShutDownLoss()==null)
			newPcsTlHtDetails.setShutDownLoss("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getStartupLoss())||newPcsTlHtDetails.getStartupLoss()==null)
			newPcsTlHtDetails.setStartupLoss("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getSpeedLoss())||newPcsTlHtDetails.getSpeedLoss()==null)
			newPcsTlHtDetails.setSpeedLoss("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getManagementLoss())||newPcsTlHtDetails.getManagementLoss()==null)
			newPcsTlHtDetails.setManagementLoss("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getMinorstopage()))
			newPcsTlHtDetails.setMinorstopage("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getDefectStdLoss()))
			newPcsTlHtDetails.setDefectStdLoss("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getLogisticLoss()))
			newPcsTlHtDetails.setLogisticLoss("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getProductionLoss()))
			newPcsTlHtDetails.setProductionLoss("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getProductionTimeLoss()))
			newPcsTlHtDetails.setProductionTimeLoss("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getEqElectrical()))
			newPcsTlHtDetails.setEqElectrical("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getEqElectronics()))
			newPcsTlHtDetails.setEqElectronics("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getEqMechanical()))
			newPcsTlHtDetails.setEqMechanical("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getEqJigfixture()))
			newPcsTlHtDetails.setEqJigfixture("0");
				
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getProductAvailabetime()))
			newPcsTlHtDetails.setProductAvailabetime("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getNoplanMints()))
			newPcsTlHtDetails.setNoplanMints("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getProduceQty()))
			newPcsTlHtDetails.setProduceQty("0");
		//if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getRejectedqty()))
		//	newPcsTlHtDetails.setRejectedqty("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getRejectedQty()))
			newPcsTlHtDetails.setRejectedQty("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getReworkQty()))
			newPcsTlHtDetails.setReworkQty("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getRoa()))
			newPcsTlHtDetails.setRoa("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getRop()))
			newPcsTlHtDetails.setRop("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getRoq()))
			newPcsTlHtDetails.setRoq("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getReprocessQty()))
			newPcsTlHtDetails.setRoq("0");
		if(!UIUtils.isValidKeyId(newPcsTlHtDetails.getRnt()))
			newPcsTlHtDetails.setRnt("0");
	
	return newPcsTlHtDetails;
	}


	private BAL_PcsTlLossreasonlink losslinkFillValues(BAL_PcsTlLossreasonlink newPcsTlLossreasonlink) 
	{
			CommonFunctions.debugMsg(" losslinkFillValues Detail 1");
		//CommonFunctions.debugMsg("Chk Box : " +bdFormBean.getIssparesY());		
			//String dateTime = CommonFunctions.dateTimeNow();
			String dateTime = CommonFunctions.pg_dateTimeNow();
			
			if (newPcsTlLossreasonlink.getPlrkInstance() == null || newPcsTlLossreasonlink.getPlrkInstance().equals("0"))
				newPcsTlLossreasonlink.setPlrkInstance("1");
			
			
			String[] reasonId=null;
		//	CommonFunctions.debugMsg(newPcsTlLossreasonlink.getPlrkReasonid().split("-"));			
			reasonId = newPcsTlLossreasonlink.getPlrkReasonid().split("-");
			CommonFunctions.debugMsg("reasonId.length"+reasonId.length);
			/*CommonFunctions.debugMsg("reasonId(0)"+reasonId[0]);
			
			if (reasonId.length>1) { 
				CommonFunctions.debugMsg("In if statement");
				String plrkProcessid = reasonId[0].toString();
				newPcsTlLossreasonlink.setPlrkProcessid(plrkProcessid);
				newPcsTlLossreasonlink.setPlrkReasonid(reasonId[1].toString());
			}
			else*/
				CommonFunctions.debugMsg("In else statement");
				newPcsTlLossreasonlink.setPlrkProcessid("{}");
				newPcsTlLossreasonlink.setPlrkReasonid("{}");
			
			//newPcsTlLossreasonlink.setPlrkDate(dateTime);
			//newPcsTlLossreasonlink.setPlrkShiftid(newPcsTlMst.getPrlmShiftid());
			newPcsTlLossreasonlink.setPlrkHourno("1");
			
		/*	newPcsTlLossreasonlink.setPlrkFactoryid(newPcsTlMst.getPrlmFactoryid());
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
			
			CommonFunctions.debugMsg(" losslinkFillValues before Ancilliary time ");
			
			if(newPcsTlLossreasonlink.getPcsTlAncilliarytimeList()!= null && newPcsTlLossreasonlink.getPcsTlAncilliarytimeList().size()>=0) 
				newPcsTlLossreasonlink.setPcsTlAncilliarytimeList(ancilliaryFillValues(newPcsTlLossreasonlink));
			
			//newPcsTlLossreasonlink.setPlrkCreatedby();
		//	PcsTlDtl.setBdanBdms_keyid(newPcsTlMst.getBdmsKeyid());
	
			//genTlEmployeedtl.setEmpdModifiedon(dateTime);
		
		return newPcsTlLossreasonlink;
	}
	
	

	private BAL_PcsTlLosscapture pcsLossCaptureFillValues(BAL_PcsTlLosscapture newPcsTlLosscapture) 
	{
			CommonFunctions.debugMsg("Detail 1 pcsLossCaptureFillValues ");
			//CommonFunctions.debugMsg("Chk Box : " +bdFormBean.getIssparesY());		
			//String dateTime = CommonFunctions.dateTimeNow();
			String dateTime = CommonFunctions.pg_dateTimeNow();
			String losshrno=newPcsTlLosscapture.getPloslosshrno();
			CommonFunctions.debugMsg("hrno.."+losshrno);
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
			if(newPcsTlLosscapture.getPlosLossreason()==null)
				newPcsTlLosscapture.setPlosLossreason("-");
			if(newPcsTlLosscapture.getPlosTradeid()==null)
			newPcsTlLosscapture.setPlosTradeid("-");
			System.out.println(" Loss hours NO:  "+newPcsTlLosscapture.getPloslosshrno());
			if (newPcsTlLosscapture.getPloslosshrno()==null)
				newPcsTlLosscapture.setPloslosshrno("-");
			else
				newPcsTlLosscapture.setPloslosshrno(losshrno);
			CommonFunctions.debugMsg("remarks in loss "+ newPcsTlLosscapture.getPlosRemarks());
			if(newPcsTlLosscapture.getPlosRemarks()==null)
				newPcsTlLosscapture.setPlosRemarks("-");
			else
				newPcsTlLosscapture.setPlosRemarks(newPcsTlLosscapture.getPlosRemarks());
			if(newPcsTlLosscapture.getPlosMachineId()==null)
			        newPcsTlLosscapture.setPlosMachineId("-");
		//	if(newPcsTlLosscapture.get()==null)
			        if(newPcsTlLosscapture.getPlosSource()==null)
		        	newPcsTlLosscapture.setPlosSource("-");
			        
			        System.out.println("  ToolId Source......"+newPcsTlLosscapture.getPlosToolId());
		        if(newPcsTlLosscapture.getPlosSourcename()==null)		        	
		        	newPcsTlLosscapture.setPlosSourcename("-");		        	
		        if(newPcsTlLosscapture.getPlosCleantime()==null)		        	
		        	newPcsTlLosscapture.setPlosCleanTime("0");		        	
		        if(newPcsTlLosscapture.getPlosTightTime()==null)		        	
		        	newPcsTlLosscapture.setPlosTightTime("0");		        	
		        if(newPcsTlLosscapture.getPlosLubtime()==null)		        	
		        	newPcsTlLosscapture.setPlosLubTime("0");		        	
		        if(newPcsTlLosscapture.getPlosInspectTime()==null)		        	
		        	newPcsTlLosscapture.setPlosInspectTime("0");
		        if(newPcsTlLosscapture.getPlosToolId()==null)
		        	newPcsTlLosscapture.setPlosToolId("-");
		        	newPcsTlLosscapture.setPlosTempfield2("-");
		        	newPcsTlLosscapture.setPlosTempfield3("-");
		        	newPcsTlLosscapture.setPlosCreatedon(dateTime);
		        	newPcsTlLosscapture.setPlosModifiedon(dateTime);
		        	newPcsTlLosscapture.setPlosActive("Y"); 
		        	return newPcsTlLosscapture;
	}
	
	private PcsTlOperatordtl pcsEmployeeFillValues(PcsTlOperatordtl newPcsTlOperatordtl) 
	{
			CommonFunctions.debugMsg("Detail 1 pcsEmployeeFillValues");
			//CommonFunctions.debugMsg("Chk Box : " +bdFormBean.getIssparesY());		
			String dateTime = CommonFunctions.dateTimeNow();
			
			newPcsTlOperatordtl.setPopdCreatedon(dateTime);
			newPcsTlOperatordtl.setPopdModifiedon(dateTime);
			newPcsTlOperatordtl.setPopdActive("Y");
		
		return newPcsTlOperatordtl;
	}
	
	private List<PcsTlAncilliarytime>  ancilliaryFillValues(BAL_PcsTlLossreasonlink newPcsTlLossreasonlink) 
	{
		
		CommonFunctions.debugMsg(" In Fillvalues Ancilliary time ");
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		List<PcsTlAncilliarytime> newPcsTlAncilliarytimes = newPcsTlLossreasonlink.getPcsTlAncilliarytimeList();
		
		System.out.println(newPcsTlLossreasonlink.getPcsTlAncilliarytimeList().size() + " : Size");
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

	@Override
	public String populateTempTable(String excelFileName,
			PcsTlOtherlossentry pcsTlOtherlossentry) throws UploadException, ValidationExceptions, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException {
		try{
			validations.validate(pcsTlOtherlossentry,"pcsOtherLoss","create");
		}catch(ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}
		fillValues(pcsTlOtherlossentry);
		return pcsTlLosscaptureDao.populateTempTable( excelFileName,pcsTlOtherlossentry);
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
		return pcsTlLosscaptureDao.updateLossVal(otherLossList);
	}

	@Override
	public List<String[]> getShiftEndTime() throws Exception {
		// TODO Auto-generated method stub
		return pcsEntryDao.getShiftEndTime();
	}
	
	
	
	/*
	public PcsTlProductiondata createProductiondataCapture(PcsTlProductiondata newPscTlProductiondata,PcsTlProductiondata existPcsTlProductiondata) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		
		String validationsFor;		
		validationsFor = "create";				
	try {
//		String fullyNoPlan=newPscTlProductiondata.getPprdShiftnoplan();
	
		CommonFunctions.debugMsg("NoPlan value"+newPscTlProductiondata.getPprdShiftnoplan());
		CommonFunctions.debugMsg("Hourly NoPlan value"+newPscTlProductiondata.getPprdHourlynoplan());
	
		if(newPscTlProductiondata.getPprdShiftnoplan() == null && newPscTlProductiondata.getPprdHourlynoplan() == null)
		{
			validations.validate(newPscTlProductiondata,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
		}
		System.out.println("fillvalue fill after");
		PcsTlMst newpcsTlmst = new PcsTlMst();
		PcsTlDtl newpcsTlDtl = new PcsTlDtl();
		pcsLossProductiondataCaptureFillValues(newPscTlProductiondata,existPcsTlProductiondata,newpcsTlmst,newpcsTlDtl);
		CommonFunctions.debugMsg("After Fill Values");
	//	return pcsTlproductiondatadao.create(newPscTlProductiondata);
	
		}catch (ValidationExceptions e){			
			CommonFunctions.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return existPcsTlProductiondata;
}
	*/
	
   //elumalai
	public BAL_PcsTlProductiondata createProductiondataCapture(BAL_PcsTlProductiondata newPcsTlProductiondata,BAL_PcsTlProductiondata existPcsTlProductionData,
			BAL_PcsTlMst newPcsTlMst, BAL_PcsTlDtl newPcsTlDtl,BAL_PcsEntryBean pcsEntryBean) throws ValidationExceptions,Exception {
		
		String validationsFor;		
		validationsFor = "create";				
	try {
//		String fullyNoPlan=newPscTlProductiondata.getPprdShiftnoplan();
	
		CommonFunctions.debugMsg("NoPlan value"+newPcsTlProductiondata.getPprdShiftnoplan());
//		CommonFunctions.debugMsg("Hourly NoPlan value"+newPcsTlMst.getPrlmEntrydate());
//		newPcsTlMst.setPrlmEntrydate(CommonFunctions.pg_dateTimeNow());  
//		newPcsTlProductiondata.setPprdCellid(newPcsTlMst.getPrlmCellid());
//		newPcsTlProductiondata.setPprdDate(newPcsTlMst.getPrlmEntrydate());
//		newPcsTlProductiondata.setPprdShiftid(newPcsTlMst.getPrlmShiftid());

		
		CommonFunctions.debugMsg("Entry date " + newPcsTlMst.getPrlmEntrydate());

		String isoDate = CommonFunctions.pg_getDateTimeFromDate(newPcsTlMst.getPrlmEntrydate());
		newPcsTlMst.setPrlmEntrydate(isoDate);   
		newPcsTlMst.setPrlmDate(isoDate);        

		newPcsTlProductiondata.setPprdCellid(newPcsTlMst.getPrlmCellid());
		newPcsTlProductiondata.setPprdDate(isoDate);
		newPcsTlProductiondata.setPprdShiftid(newPcsTlMst.getPrlmShiftid());
		
		if(newPcsTlProductiondata.getPprdShiftnoplan() == null && newPcsTlProductiondata.getPprdHourlynoplan() == null && pcsEntryBean.getAddrowtoGrid() ==  null )
		{
			
			validations.validate(newPcsTlProductiondata,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
		}
		fillValues(newPcsTlMst, newPcsTlDtl , pcsEntryBean);
		detailFillValues(newPcsTlDtl, pcsEntryBean);
						
		newPcsTlDtl.setCellid(newPcsTlMst.getPrlmCellid());
		pcsLossProductiondataCaptureFillValues(newPcsTlProductiondata,existPcsTlProductionData,newPcsTlMst,newPcsTlDtl);
	
			//return pcsTlproductiondatadao.createNew(newPcsTlProductiondata,newPcsTlMst,newPcsTlDtl, pcsEntryBean);
		return pcsenrtyserviceApi.insertRecord(newPcsTlProductiondata,newPcsTlMst,newPcsTlDtl, pcsEntryBean);
		}catch (ValidationExceptions e){			
			CommonFunctions.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;//existPcsTlProductionData;
	
	}
	
	@Override
	public BAL_PcsTlRejectionbreakupdata createRejectionbreakpupdataRecord(BAL_PcsTlRejectionbreakupdata newPcsTlRejectionbreakupdata,BAL_PcsTlRejectionbreakupdata existPcsTlRejectionbreakupdata,List<BAL_PcsTlRejectionbreakupdata> rejectionGridList) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		
			String validationsFor;		
			validationsFor = "create";				
		try {
			//validations.validate(newPcsTlRejectionbreakupdata,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
			System.out.println("fillvalue fill after");
				
			fillRejectionbreakupDatadetail(newPcsTlRejectionbreakupdata,existPcsTlRejectionbreakupdata,rejectionGridList);
			CommonFunctions.debugMsg("After Fill Values");
		
			//return pcsTlRejectionbreakupdatadao.create(newPcsTlRejectionbreakupdata,rejectionGridList);
			return pcsenrtyserviceApi.create(newPcsTlRejectionbreakupdata, rejectionGridList);
			
			}catch (ValidationExceptions e){			
				CommonFunctions.debugMsg("e.getMessage():"+e.getMessage());
				throw new ValidationExceptions(e.getMessage());
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return existPcsTlRejectionbreakupdata;
	}
	
	private List<BAL_PcsTlRejectionbreakupdata> fillRejectionbreakupDatadetail(BAL_PcsTlRejectionbreakupdata newPcsTlRejectionbreakupdata,
			BAL_PcsTlRejectionbreakupdata existPcsTlRejectionbreakupdata,List<BAL_PcsTlRejectionbreakupdata> rejectionGridList) {
		// TODO Auto-generated method stub
		
		System.out.println("Detail 1");
		//String dateTime = CommonFunctions.dateTimeNow();
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<BAL_PcsTlRejectionbreakupdata> newpcsTlBreakupdataList = new ArrayList<BAL_PcsTlRejectionbreakupdata>();
	
			for(BAL_PcsTlRejectionbreakupdata breakupDatafill : rejectionGridList){
				
				breakupDatafill.setPrbdActive("Y");
				breakupDatafill.setPrbdCreatedby(newPcsTlRejectionbreakupdata.getPrbdCreatedby());
				breakupDatafill.setPrbdCreatedon(dateTime);
				breakupDatafill.setPrbdModifiedon(dateTime);
				breakupDatafill.setPrbdPprdKeyid(newPcsTlRejectionbreakupdata.getPrbdPprdKeyid());
				if(breakupDatafill.getPrbdDefect() == null)
					breakupDatafill.setPrbdDefect("{}");
			
				if(breakupDatafill.getPrbdEquipment()==null)
					breakupDatafill.setPrbdEquipment("{}");
				
				if(breakupDatafill.getPrbdPhenomena()== null)
					breakupDatafill.setPrbdPhenomena("{}");
			
				if(breakupDatafill.getPrbdRejectionreason() == null)
					breakupDatafill.setPrbdRejectionreason("{}");
				
				if (breakupDatafill.getPrbdTempfield1() == null)
					breakupDatafill.setPrbdTempfield1("-");
				
				if (breakupDatafill.getPrbdTempfield2() == null)
					breakupDatafill.setPrbdTempfield2("-");
				
				if (breakupDatafill.getPrbdTempfield3() == null)
					breakupDatafill.setPrbdTempfield3("-");
				
				if (breakupDatafill.getPrbdTempfield4() == null)
					breakupDatafill.setPrbdTempfield4("-");
			
				CommonFunctions.debugMsg("End "+breakupDatafill.getPrbdKeyid());
				newpcsTlBreakupdataList.add(breakupDatafill);
			}
		
		CommonFunctions.debugMsg("End Of Breakup  fill Values");
		return newpcsTlBreakupdataList;
	}

		private BAL_PcsTlProductiondata pcsLossProductiondataCaptureFillValues(BAL_PcsTlProductiondata newPcsTlProductiondata,BAL_PcsTlProductiondata existPcsTlProductiondatan,BAL_PcsTlHtProductionHistory newPcsTlHtProductionHistory,BAL_PcsTlHtDetails newPcsTlHtDetails) throws Exception{
			
		   
			//String dateTime = CommonFunctions.dateTimeNow();
			String dateTime = CommonFunctions.pg_dateTimeNow();
			newPcsTlProductiondata.setPprdCreatedon(dateTime);
			newPcsTlProductiondata.setPprdModifiedon(dateTime);
			
			CommonFunctions.debugMsg("Noplanduration"+newPcsTlProductiondata.getPprdManpower());
			
			newPcsTlProductiondata.setPprdActive("Y");
		/*	newPcsTlHtProductionHistory.setMachineid(newPcsTlProductiondata.getPprdHour());
			newPcsTlHtProductionHistory.setProductid(newPcsTlProductiondata.getPprdProductid());
			newPcsTlHtProductionHistory.setTheoriticalcycletime(newPcsTlProductiondata.getPprdCycletime());
			newPcsTlHtProductionHistory.setActualcycletime(newPcsTlProductiondata.getPprdCycletime());
			newPcsTlHtProductionHistory.setCavityused("1");
		//	newPcsTlDtl.setOperators(newPcsTlProductiondata.getPprdManpower());
			CommonFunctions.debugMsg("Rework Quantity"+newPcsTlProductiondata.getPprdReworkqty());
			newPcsTlProductiondata.setPprdFlid(newPcsTlMst.getPrlmFlid());
			newPcsTlProductiondata.setPprdCellid(newPcsTlMst.getPrlmCellid());
			newPcsTlProductiondata.setPprdDate(newPcsTlMst.getPrlmEntrydate());
			newPcsTlProductiondata.setPprdShiftid(newPcsTlMst.getPrlmShiftid());
			newPcsTlProductiondata.setPprdCreatedby(newPcsTlMst.getPrlmCreatedby());
			CommonFunctions.debugMsg("In Fill Values"+newPcsTlDtl.getReworkqty()); */
			CommonFunctions.debugMsg("In Fill Values");
			if(newPcsTlProductiondata.getPprdCycletime()== null)
				newPcsTlProductiondata.setPprdCycletime("0");
		
			if(newPcsTlProductiondata.getPprdManpower() == null)
				newPcsTlProductiondata.setPprdManpower("-");

			if(newPcsTlProductiondata.getPprdProductid() == null)
				newPcsTlProductiondata.setPprdProductid("-");
			
			if(newPcsTlProductiondata.getPprdProductmodel()==null)
				newPcsTlProductiondata.setPprdProductmodel("-");
			
			if(newPcsTlProductiondata.getPprdProducedqty()== null)
				newPcsTlProductiondata.setPprdProducedqty("0");
			
			if(newPcsTlProductiondata.getPprdRejectedqty()== null)
				newPcsTlProductiondata.setPprdRejectedqty("0");
			
			if(newPcsTlProductiondata.getPprdReworkqty()==null)
				newPcsTlProductiondata.setPprdReworkqty("0");
			
			if(newPcsTlProductiondata.getPprdRemarks()==null)
				newPcsTlProductiondata.setPprdRemarks("-");
			
			if(newPcsTlProductiondata.getPprdHourlynoplan()==null)
				newPcsTlProductiondata.setPprdHourlynoplan("N");
			
			if(newPcsTlProductiondata.getPprdNoplanduration()==null)
				newPcsTlProductiondata.setPprdNoplanduration("0");
		
			if(newPcsTlProductiondata.getPprdShiftnoplan()== null)
				newPcsTlProductiondata.setPprdShiftnoplan("N");
			
		/*	if(newPcsTlProductiondata.getPprdBatchQty()== null)
				newPcsTlProductiondata.setPprdBatchQty("0");
			
			if(newPcsTlProductiondata.getPprdWeightpiece()==null)
				newPcsTlProductiondata.setPprdWeightpiece("0");
			
			if(newPcsTlProductiondata.getPprdArea()==null)
				newPcsTlProductiondata.setPprdArea("-");*/
			
			if(newPcsTlProductiondata.getPprdTempfield1()==null)
				newPcsTlProductiondata.setPprdTempfield1("-");
			
			if(newPcsTlProductiondata.getPprdTempfield2()==null)
				newPcsTlProductiondata.setPprdTempfield2("-");
			
			if(newPcsTlProductiondata.getPprdTempfield3()==null)
				newPcsTlProductiondata.setPprdTempfield3("-");
			/*
			newPcsTlDtl.setProducedqty(newPcsTlProductiondata.getPprdProducedqty());
			newPcsTlDtl.setRejectedqty(newPcsTlProductiondata.getPprdRejectedqty());
			newPcsTlDtl.setNoplaninmins(newPcsTlProductiondata.getPprdNoplanduration());
			newPcsTlDtl.setReworkqty(newPcsTlProductiondata.getPprdReworkqty());
		*/
		return newPcsTlProductiondata;	
		}

		@Override
		public BAL_PcsTlRejectionbreakupdata deleteRejectiondata(String keyid) throws BusinessApplicationExceptions, Exception {
			
			BAL_PcsTlRejectionbreakupdata pcsTlRejectionbreakupdata=new BAL_PcsTlRejectionbreakupdata();
			//this.pcsTlRejectionbreakupdatadao.deleteRejectionbreakupdata(keyid);
			this.pcsenrtyserviceApi.deleteRejectionbreakupdata(keyid);
	
			return pcsTlRejectionbreakupdata;
		}
		public BAL_PcsTlProductiondata removeShiftNoplan(String mstkeyId, String dtlkeyId,String cellId, String entrydate)throws BusinessApplicationExceptions, Exception {
			
			BAL_PcsTlProductiondata pcsTlProductiondata=new BAL_PcsTlProductiondata();
			this.pcsTlproductiondatadao.removeShiftNoplan(mstkeyId,dtlkeyId, cellId, entrydate);
	
			return pcsTlProductiondata;
		}
		
		public BAL_PcsTlRejectionbreakupdata deleteProductiondata(String keyid) throws BusinessApplicationExceptions, Exception {
			
			BAL_PcsTlRejectionbreakupdata pcsTlRejectionbreakupdata=new BAL_PcsTlRejectionbreakupdata();
			this.pcsTlproductiondatadao.deleteProductiondata(keyid);
	
			return pcsTlRejectionbreakupdata;
		}
		
		@Override
		public BAL_PcsTlProductiondata updateProductiondataCapture(BAL_PcsTlProductiondata newPscTlProductiondata,BAL_PcsTlProductiondata existPcsTlProductionData,
BAL_PcsTlMst newPcsTlMst, BAL_PcsTlDtl newPcsTlDtl,BAL_PcsEntryBean pcsEntryBean) throws ValidationExceptions,Exception {
	
			
			return null;
		}
		public int getShiftTotaltime(String startdate, String enddate,String shiftId) throws Exception{
			//return this.pcsEntryDao.getShiftTotaltime(startdate, enddate,shiftId);
			return this.pcsenrtyserviceApi.getShiftTotaltime(startdate, enddate, shiftId);
		}

		@Override
		public List<String[]> getJhEquipment(CommonFilter commonFilter)
				throws Exception {
			// TODO Auto-generated method stub
			return this.pcsEntryDao.getJhEquipmnt(commonFilter);
		}

		@Override
		public List<String[]> getJhEquipmentSpeed(CommonFilter commonFilter)
				throws Exception {
			// TODO Auto-generated method stub
			return this.pcsEntryDao.getJhEquipmntSpeed(commonFilter);
		}
		public BAL_PcsTlJhlossDetail createJhLossDetails( BAL_PcsTlJhlossDetail newPcsTlJhlossDetail,
				BAL_PcsTlJhlossDetail existPcsTlJhlossDetail)throws Exception {
			CommonFunctions.debugMsg("sercivece imple   666   "+newPcsTlJhlossDetail.getJhDetails());
			newPcsTlJhlossDetail.setjhDetails( fillJhDetails(newPcsTlJhlossDetail.getJhDetails()));
			
		
	 
			CommonFunctions.debugMsg("After Filling Values Services  ki  " +newPcsTlJhlossDetail);	
		return pcsEntryDao.createJhDetails(newPcsTlJhlossDetail);
		}
		private List<BAL_PcsTlJhlossDetail> fillJhDetails(List<BAL_PcsTlJhlossDetail> jhData) {
			CommonFunctions.debugMsg("After Filling Values Services  kir  " +jhData);	
			
			List<BAL_PcsTlJhlossDetail>  pcsTlJhlossDetaillist = new ArrayList<BAL_PcsTlJhlossDetail>();
			String dateTime = CommonFunctions.dateTimeNow();
			
			for( int i=0;i<jhData.size();i++)
			{	
				BAL_PcsTlJhlossDetail pcsTlJhlossDetail = new BAL_PcsTlJhlossDetail();
				pcsTlJhlossDetail=jhData.get(i);
				pcsTlJhlossDetail.setJhlossCreatedOn(dateTime);
				CommonFunctions.debugMsg(pcsTlJhlossDetail.getJhlossCreatedOn()+" in side fill values created by ");
				pcsTlJhlossDetail.setJhlossModifiedOn(dateTime);
		
			
				if(!UIUtils.isValidKeyId(pcsTlJhlossDetail.getJhlosKeyid())) 
					pcsTlJhlossDetail.setJhlossKeyid("-");			
				if(!UIUtils.isValidKeyId(pcsTlJhlossDetail.getJhlossId()))
					pcsTlJhlossDetail.setJhlossId("-");
				if(!UIUtils.isValidKeyId(pcsTlJhlossDetail.getJhlossLubtime())) 
					pcsTlJhlossDetail.setJhlossLubTime("0");
				if(!UIUtils.isValidKeyId(pcsTlJhlossDetail.getJhlossMachine())) 
					pcsTlJhlossDetail.setJhlossMachine("{}");
				if(!UIUtils.isValidKeyId(pcsTlJhlossDetail.getJhlossInspectTime())) 
					pcsTlJhlossDetail.setJhlossInspectTime("0");
				if(!UIUtils.isValidKeyId(pcsTlJhlossDetail.getJhlossCleanTime())) 
					pcsTlJhlossDetail.setJhlossCleanTime("0");
				if(!UIUtils.isValidKeyId(pcsTlJhlossDetail.getJhlossInspectTime())) 
					pcsTlJhlossDetail.setJhlossInspectTime("0");
				
				if(!UIUtils.isValidKeyId(pcsTlJhlossDetail.getJhlossTempfield2())) 
					pcsTlJhlossDetail.setJhlossTempfield1("-");
				if(!UIUtils.isValidKeyId(pcsTlJhlossDetail.getJhlossTempfield3())) 
					pcsTlJhlossDetail.setJhlossTempfield2("-");
				
				pcsTlJhlossDetaillist.add(pcsTlJhlossDetail);
			// TODO Auto-generated method stub
			
		}
			 
			return pcsTlJhlossDetaillist;
		
		}
		public List<ComboBox> getToolCombo(ComboFilter currentFilter,String machinId)throws Exception{
			ComboFilter comboFilter = currentFilter ;
			
			CommonFunctions.debugMsg("service INSIDE TOOLCHANGE LOSS");                                                                                                                                                 
		//	PTLM_CODE                     
			//comboFilter.setCodeField("PRMM_CODE");
			comboFilter.setIdField("PTLM_KEYID");
			comboFilter.setNameField("PTLM_NAME");
			comboFilter.setCodeField("PTLM_CODE");
			comboFilter.setTableName(TableNames.TBL_PCS_VW_TOOLCHANGE);
			
			CommonFunctions.debugMsg("service INSIDE TOOLCHANGE LOSS");
			StringBuilder condSql=new StringBuilder();	
			if (UIUtils.isValidKeyId(machinId))
				condSql.append("AND TCOM_MACHINEID='"+machinId+"'");
				condSql.append("AND PTLM_ACTIVE='Y'");
			comboFilter.setCondSql(condSql.toString());
			CommonFunctions.debugMsg(" get id TOOLS pcs_tl_toolcomst::"+comboFilter.getNameField());
			return commonFilterDao.fillComboValues(comboFilter);		
		
		}

		@Override
		public List<ComboBox> getAreaCombo(ComboFilter currentFilter) throws Exception {
			// TODO Auto-generated method stub
				
			
				ComboFilter comboFilter = currentFilter ;
			//	comboFilter.setCodeField("CYTM_MANPOWER");
				comboFilter.setIdField("HTPA_KEYID");
				comboFilter.setNameField("HTPA_AREA");
				
			    comboFilter.setTableName("PCS_TL_HTPRODUCTAREA");	
			    CommonFunctions.debugMsg("In Side the SERVICE prd area");
				return commonFilterDao.fillComboValues(comboFilter);
			
		}
		private BAL_PcsTlProductiondata pcsLossProductiondataCaptureFillValues(BAL_PcsTlProductiondata newPcsTlProductiondata,BAL_PcsTlProductiondata existPcsTlProductionData,BAL_PcsTlMst newPcsTlMst,BAL_PcsTlDtl newPcsTlDtl)throws Exception{

	//	pcsLossProductiondataCaptureFillValues(PcsTlProductiondata newPcsTlProductiondata,PcsTlProductiondata existPcsTlProductiondatan,PcsTlMst newPcsTlMst,PcsTlDtl newPcsTlDtl) throws Exception{
			
			
			//String dateTime = CommonFunctions.dateTimeNow();
			String dateTime = CommonFunctions.pg_dateTimeNow();
			newPcsTlProductiondata.setPprdCreatedon(dateTime);
			newPcsTlProductiondata.setPprdModifiedon(dateTime);
			
			CommonFunctions.debugMsg("Noplanduration"+newPcsTlProductiondata.getPprdManpower());
			
			newPcsTlProductiondata.setPprdActive("Y");
			if(newPcsTlMst.getPrlmLogtype().equals("S")){
				newPcsTlDtl.setMachineid("0");

			}
			else{
			newPcsTlDtl.setMachineid(newPcsTlProductiondata.getPprdHour());
			}
			newPcsTlDtl.setProductid(newPcsTlProductiondata.getPprdProductid());
			newPcsTlDtl.setTheoriticalcycletime(newPcsTlProductiondata.getPprdCycletime());
			newPcsTlDtl.setActualcycletime(newPcsTlProductiondata.getPprdCycletime());
			newPcsTlDtl.setCavityused("1");
		//	newPcsTlDtl.setOperators(newPcsTlProductiondata.getPprdManpower());
			CommonFunctions.debugMsg("Rework Quantity"+newPcsTlProductiondata.getPprdReworkqty());
			newPcsTlProductiondata.setPprdFlid(newPcsTlMst.getPrlmFlid());
			newPcsTlProductiondata.setPprdCellid(newPcsTlMst.getPrlmCellid());
			newPcsTlProductiondata.setPprdDate(newPcsTlMst.getPrlmEntrydate());
			newPcsTlProductiondata.setPprdShiftid(newPcsTlMst.getPrlmShiftid());
			newPcsTlProductiondata.setPprdCreatedby(newPcsTlMst.getPrlmCreatedby());
			CommonFunctions.debugMsg("In Fill Values"+newPcsTlDtl.getReworkqty());
			CommonFunctions.debugMsg("In Fill Values");
			if(newPcsTlProductiondata.getPprdCycletime()== null)
				newPcsTlProductiondata.setPprdCycletime("0");
		
			if(newPcsTlProductiondata.getPprdManpower() == null)
				newPcsTlProductiondata.setPprdManpower("-");

			if(newPcsTlProductiondata.getPprdProductid() == null)
				newPcsTlProductiondata.setPprdProductid("-");
			
			if(newPcsTlProductiondata.getPprdProductmodel()==null)
				newPcsTlProductiondata.setPprdProductmodel("-");
			
			if(newPcsTlProductiondata.getPprdProducedqty()== null)
				newPcsTlProductiondata.setPprdProducedqty("0");
			
			if(newPcsTlProductiondata.getPprdRejectedqty()== null)
				newPcsTlProductiondata.setPprdRejectedqty("0");
			
			if(newPcsTlProductiondata.getPprdReworkqty()==null)
				newPcsTlProductiondata.setPprdReworkqty("0");
			
			if(newPcsTlProductiondata.getPprdRemarks()==null)
				newPcsTlProductiondata.setPprdRemarks("-");
			
			if(newPcsTlProductiondata.getPprdHourlynoplan()==null)
				newPcsTlProductiondata.setPprdHourlynoplan("N");
			
			if(newPcsTlProductiondata.getPprdNoplanduration()==null)
				newPcsTlProductiondata.setPprdNoplanduration("0");
		
			if(newPcsTlProductiondata.getPprdShiftnoplan()== null)
				newPcsTlProductiondata.setPprdShiftnoplan("N");
			
		/*	if(newPcsTlProductiondata.getPprdBatchQty()== null)
				newPcsTlProductiondata.setPprdBatchQty("0");
			
			if(newPcsTlProductiondata.getPprdWeightpiece()==null)
				newPcsTlProductiondata.setPprdWeightpiece("0");
			
			if(newPcsTlProductiondata.getPprdArea()==null)
				newPcsTlProductiondata.setPprdArea("-");*/
			
			if(newPcsTlProductiondata.getPprdTempfield1()==null)
				newPcsTlProductiondata.setPprdTempfield1("-");
			
			if(newPcsTlProductiondata.getPprdTempfield2()==null)
				newPcsTlProductiondata.setPprdTempfield2("-");
			
			if(newPcsTlProductiondata.getPprdTempfield3()==null)
				newPcsTlProductiondata.setPprdTempfield3("-");
			
			newPcsTlDtl.setProducedqty(newPcsTlProductiondata.getPprdProducedqty());
			newPcsTlDtl.setRejectedqty(newPcsTlProductiondata.getPprdRejectedqty());
			newPcsTlDtl.setNoplaninmins(newPcsTlProductiondata.getPprdNoplanduration());
			newPcsTlDtl.setReworkqty(newPcsTlProductiondata.getPprdReworkqty());
		
		return newPcsTlProductiondata;	
		}

		//elumalai
		public BAL_PcsTlHtDetails createProductiondataCapture(BAL_PcsTlHtProductionHistory newPcsTlHtProductionHistory,
				BAL_PcsTlHtDetails newPcsTlHtDetails, BAL_PcsTlHtDetails existPcsTlHtDetails) throws Exception {
			String validationsFor;		
			//validationsFor = "create";				
		try {
//			String fullyNoPlan=newPscTlProductiondata.getPprdShiftnoplan();
		
			CommonFunctions.debugMsg("NoPlan value"+newPcsTlHtDetails.getNoplanMints());
		//	CommonFunctions.debugMsg("Hourly NoPlan value"+newPcsTlMst.getPrlmEntrydate());
			newPcsTlHtDetails.setCellmaning(newPcsTlHtProductionHistory.getCellid());
			newPcsTlHtDetails.setTempField2(newPcsTlHtProductionHistory.getDate());
			newPcsTlHtDetails.setTempField3(newPcsTlHtProductionHistory.getShiftid());
			
			if(newPcsTlHtDetails.getNoplanMints() == null && newPcsTlHtDetails.getNoplanMints() == null )
			{
			//	validations.validate(newPscTlProductiondata,"pcsEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
			}
			fillValues(newPcsTlHtProductionHistory, newPcsTlHtDetails , pcsEntryBean);
			detailFillValues(newPcsTlHtDetails, pcsEntryBean);
							
			newPcsTlHtDetails.setCellmaning(newPcsTlHtProductionHistory.getCellid());
			pcsLossProductiondataCaptureFillValues(newPcsTlHtProductionHistory,newPcsTlHtDetails,existPcsTlHtDetails);
		
				return pcsTlproductiondatadao.createNew(newPcsTlHtProductionHistory,newPcsTlHtDetails,existPcsTlHtDetails);
			//return serviceApi.insertRecord(newPcsTlHtProductionHistory,newPcsTlHtDetails,existPcsTlHtDetails);
			}catch (ValidationExceptions e){			
				CommonFunctions.debugMsg("e.getMessage():"+e.getMessage());
				throw new ValidationExceptions(e.getMessage());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return existPcsTlHtDetails;
		}
//
		
		private BAL_PcsTlHtDetails pcsLossProductiondataCaptureFillValues(BAL_PcsTlHtProductionHistory newPcsTlHtProductionHistory,BAL_PcsTlHtDetails newPcsTlHtDetails,BAL_PcsTlHtDetails existPcsTlHtDetails) throws Exception{
			
			
			String dateTime = CommonFunctions.dateTimeNow();
			newPcsTlHtDetails.setCreatedon(dateTime);
			newPcsTlHtDetails.setModifiedon(dateTime);
			
			//CommonFunctions.debugMsg("Noplanduration"+newPcsTlProductiondata.getPprdManpower());
			
			newPcsTlHtDetails.setActive("Y");
		/*	newPcsTlHtProductionHistory.setMachineid(newPcsTlProductiondata.getPprdHour());
			newPcsTlHtProductionHistory.setProductid(newPcsTlProductiondata.getPprdProductid());
			newPcsTlHtProductionHistory.setTheoriticalcycletime(newPcsTlProductiondata.getPprdCycletime());
			newPcsTlHtProductionHistory.setActualcycletime(newPcsTlProductiondata.getPprdCycletime());
			newPcsTlHtProductionHistory.setCavityused("1");
		//	newPcsTlDtl.setOperators(newPcsTlProductiondata.getPprdManpower());
			CommonFunctions.debugMsg("Rework Quantity"+newPcsTlProductiondata.getPprdReworkqty());
			newPcsTlProductiondata.setPprdFlid(newPcsTlMst.getPrlmFlid());
			newPcsTlProductiondata.setPprdCellid(newPcsTlMst.getPrlmCellid());
			newPcsTlProductiondata.setPprdDate(newPcsTlMst.getPrlmEntrydate());
			newPcsTlProductiondata.setPprdShiftid(newPcsTlMst.getPrlmShiftid());
			newPcsTlProductiondata.setPprdCreatedby(newPcsTlMst.getPrlmCreatedby());
			CommonFunctions.debugMsg("In Fill Values"+newPcsTlDtl.getReworkqty()); */
			CommonFunctions.debugMsg("In Fill Values");
			if(newPcsTlHtDetails.getCycleTimeVariation()== null)
				newPcsTlHtDetails.setCycleTimeVariation("0");
		
			if(newPcsTlHtDetails.getManpower() == null)
				newPcsTlHtDetails.setManpower("-");

			if(newPcsTlHtDetails.getProductid() == null)
				newPcsTlHtDetails.setProductid("-");
			
			if(newPcsTlHtDetails.getProduceQty()==null)
				newPcsTlHtDetails.setProduceQty("0");
			
			if(newPcsTlHtDetails.getActualCharges()== null)
				newPcsTlHtDetails.setActualCharges("0");
			
			if(newPcsTlHtDetails.getActualCycleTime()== null)
				newPcsTlHtDetails.setActualCycleTime("0");
			
			if(newPcsTlHtDetails.getRejectedQty()==null)
				newPcsTlHtDetails.setRejectedQty("0");
			
			if(newPcsTlHtDetails.getReworkQty()==null)
				newPcsTlHtDetails.setReworkQty("-");
			
			if(newPcsTlHtDetails.getArea()==null)
				newPcsTlHtDetails.setArea("-");
			
			if(newPcsTlHtDetails.getTheoroticalCycletime()==null)
				newPcsTlHtDetails.setTheoroticalCycletime("0");
		
			if(newPcsTlHtDetails.getReprocessQty()== null)
				newPcsTlHtDetails.setReprocessQty("N");
			
		/*	if(newPcsTlHtDetails.get()== null)
				newPcsTlHtDetails.setPprdBatchQty("0");
			
			if(newPcsTlHtDetails.getPprdWeightpiece()==null)
				newPcsTlHtDetails.setPprdWeightpiece("0");
			
			if(newPcsTlHtDetails.getPprdArea()==null)
				newPcsTlHtDetails.setPprdArea("-");
			*/
			if(newPcsTlHtDetails.getTempField1()==null)
				newPcsTlHtDetails.setTempField1("-");
			
			if(newPcsTlHtDetails.getTempField2()==null)
				newPcsTlHtDetails.setTempField2("-");
			
			if(newPcsTlHtDetails.getTempField3()==null)
				newPcsTlHtDetails.setTempField3("-");
			/*
			newPcsTlDtl.setProducedqty(newPcsTlHtDetails.getPprdProducedqty());
			newPcsTlDtl.setRejectedqty(newPcsTlHtDetails.getPprdRejectedqty());
			newPcsTlDtl.setNoplaninmins(newPcsTlHtDetails.getPprdNoplanduration());
			newPcsTlDtl.setReworkqty(newPcsTlHtDetails.getPprdReworkqty());
		*/
		return newPcsTlHtDetails;	
		}	
///		

		@Override
		public List<ComboBox> getproductModelCombo(ComboFilter currentFilter,
				String factId, String cellId, String machineId, String entryDate)
				throws Exception {
			CommonFunctions.debugMsg("service implssss");
			//ComboFilter comboFilter = new ComboFilter();
			ComboFilter comboFilter = currentFilter ;
			//comboFilter.setCodeField("PRMM_CODE");
			comboFilter.setIdField("PRMM_KEYID");
			comboFilter.setNameField("PRMM_NAME");
			CommonFunctions.debugMsg("service implssss1111");
			
			String condSql ="";
			//if (UIUtils.isValidKeyId(factId))			
			//	condSql = " AND PRMM_FACTORYID IN ('{}','"+factId+"') ";
			
			//if (UIUtils.isValidKeyId(mchId) && UIUtils.isValidKeyId(factId)) { 
			if (UIUtils.isValidKeyId(cellId) ) {
				condSql += " AND PRMM_KEYID IN ( SELECT PRDM_MODEL  from PCS_TL_PRODUCTMST where 1 = 1 ";  
				condSql += " AND PRDM_KEYID IN ( SELECT CYTM_PRODUCTID  FROM BAL_PCS_TL_CYCLETIMEMST  WHERE CYTM_CELLID='" + cellId + "' " ;
				//condSql += " AND CYTM_FACTORYID ='" + factId + "' " ;
			//	condSql += " AND '"+ entryDate + "' BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE  ) ) ";  
				condSql += ")) AND PRMM_ACTIVE = 'Y' ";
			}
			
			if (UIUtils.isValidKeyId(machineId) ) {
				condSql += " AND PRMM_KEYID IN ( SELECT PRDM_MODEL  from PCS_TL_PRODUCTMST where 1 = 1 ";  
				condSql += " AND PRDM_KEYID IN ( SELECT CYTM_PRODUCTID  FROM BAL_PCS_TL_CYCLETIMEMST  WHERE CYTM_MACHINEID='" + machineId + "' " ;
				//condSql += " AND CYTM_FACTORYID ='" + factId + "' " ;
			//	condSql += " AND '"+ entryDate + "' BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE  ) ) ";  
				condSql += ")) AND PRMM_ACTIVE = 'Y' ";
			}
			if (UIUtils.isValidKeyId(factId) )  
			condSql += " AND PRMM_FACTORYID='"+ factId + "'";	
			condSql += " AND PRMM_ACTIVE = 'Y' ";
			comboFilter.setCondSql(condSql);
			
			CommonFunctions.debugMsg(condSql);
			comboFilter.setTableName(TableNames.TBL_PCS_TL_PRODUCTMODELMST);	
			return commonFilterDao.fillComboValues(comboFilter);
		}

		

		/*
		 * @Override public BAL_PcsTlHtDetails
		 * createProductiondataCapture(BAL_PcsTlHtProductionHistory
		 * newPcsTlHtProductionHistory, BAL_PcsTlHtDetails newPcsTlHtDetails,
		 * BAL_PcsTlHtDetails existPcsTlHtDetails) throws Exception { // TODO
		 * Auto-generated method stub return null; }
		 */


	
		
		
}	


