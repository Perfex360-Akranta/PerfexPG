package com.akranta.tpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_BDFormBean;
import com.akranta.tpm.controller.BAL_UIUtils;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.BAL_SAPInfoDao;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.BAL_SAPInfoDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BAL_SapTlMaintenanceOrderdtl;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_SapTlSparesreplaced;
//import com.akranta.tpm.model.SapTlMaintenanceOrderdtl;
import com.akranta.tpm.service.BAL_SAPInfoService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_SAPInfoServiceImpl implements BAL_SAPInfoService {
	private BAL_SAPInfoDao sapInfoDao;
	private Validations validations ;
	private BAL_CommonFilterDao commonFilterDao;
	public BAL_SAPInfoServiceImpl(DBActionTemplate dbActionTemplate) {
		
		sapInfoDao = new BAL_SAPInfoDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	@Override
	public List<String[]> getSAPInfoList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getSAPInfoList(commonFilter);
	}
	@Override
	public List<String[]> getSAPinfofillgriddata() throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getSAPinfofillgriddata();
	}
	@Override
	public List<String[]> getSAPStoragefillgriddata() throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getSAPStoragefillgriddata();
	}
	@Override
	public List<String[]> getSAPResultfillgriddata(String type) throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getSAPResultfillgriddata(type);
	}
	@Override
	public BAL_SapTlMaintenanceOrderdtl createSapSpareinfo( BAL_SapTlMaintenanceOrderdtl newSapTlMaintenanceOrderdtl,
			BAL_SapTlMaintenanceOrderdtl existSapTlMaintenanceOrderdtl, BAL_BDFormBean bdFormBean) throws Exception {
		// TODO Auto-generated method stub
		try {
			String validationsFor;
			validationsFor = "create";
		 	validations.validate(newSapTlMaintenanceOrderdtl,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		    fillValuesSapSpare(newSapTlMaintenanceOrderdtl,existSapTlMaintenanceOrderdtl,bdFormBean);
		  //  CommonFunctions.debugMsg("sercivece imple   "+newSapTlMaintenanceorder.getSparesSAPData().size());
		    CommonFunctions.debugMsg("After Filling Values ExterServices");
		    
		
	}catch (ValidationExceptions e){			
		CommonFunctions.debugMsg("e.getMessage():"+e.getMessage());
		throw new ValidationExceptions(e.getMessage());
	}	
	return sapInfoDao.createSapSpareInfo(newSapTlMaintenanceOrderdtl);
	}
	private BAL_SapTlMaintenanceOrderdtl fillValuesSapSpare(BAL_SapTlMaintenanceOrderdtl newSapTlMaintenanceOrderdtl,BAL_SapTlMaintenanceOrderdtl existSapTlMaintenanceOrderdtl,
			BAL_BDFormBean bdFormBean) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.dateTimeNow();
		System.out.println("Key ID : "+newSapTlMaintenanceOrderdtl.getExistDocNumber());
        newSapTlMaintenanceOrderdtl.setModtCreatedon(dateTime);
		newSapTlMaintenanceOrderdtl.setModtModifiedon(dateTime);
		newSapTlMaintenanceOrderdtl.setModtActive("Y");
	    if(!BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getModtDuration()))
	    	newSapTlMaintenanceOrderdtl.setModtDuration("0");
	    if(!BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getModtCckey()))
	    	newSapTlMaintenanceOrderdtl.setModtCckey("0");
	    if(!BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getModtActualcondn() ))
	    	newSapTlMaintenanceOrderdtl.setModtActualcondn("{}");
	    if(!BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getModtOpdesc()))
	    	newSapTlMaintenanceOrderdtl.setModtOpdesc("{}");
	     
	    if(!BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getModtFieldkey()))
	    	newSapTlMaintenanceOrderdtl.setModtFieldkey("{}");
	     if(!BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getModtWork()))
	    	newSapTlMaintenanceOrderdtl.setModtWork("{}");
	    if(!BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getModtTempfield1()))
	    	newSapTlMaintenanceOrderdtl.setModtTempfield1("-");
	    if(!BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getModtTempfield2()))
	    	newSapTlMaintenanceOrderdtl.setModtTempfield2("-");
	    if(!BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getModtTempfield3()))
	    	newSapTlMaintenanceOrderdtl.setModtTempfield3("-");
	    if(!BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getModtTempfield4()))
	    	newSapTlMaintenanceOrderdtl.setModtTempfield4("-");
	    if(!BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getModtTempfield5()))
	    	newSapTlMaintenanceOrderdtl.setModtTempfield5("-");
	    if(!BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getModtTempfield6()))
	    	newSapTlMaintenanceOrderdtl.setModtTempfield6("-");
	    
	    if(newSapTlMaintenanceOrderdtl.getSparesSAPData() ==null || newSapTlMaintenanceOrderdtl.getSparesSAPData().isEmpty() || newSapTlMaintenanceOrderdtl.getSparesSAPData().size()<=0)
	    {
	    	
	    }else{
	    	newSapTlMaintenanceOrderdtl.setSparesSAPData(fillSpareReplaced(newSapTlMaintenanceOrderdtl.getSparesSAPData()));
	    }
		return newSapTlMaintenanceOrderdtl;
	}
	private List<BAL_SapTlSparesreplaced> fillSpareReplaced(List<BAL_SapTlSparesreplaced> sparesSAPData) {
		
		List<BAL_SapTlSparesreplaced>  sparesReplaceList = new ArrayList<BAL_SapTlSparesreplaced>();
		String dateTime = CommonFunctions.dateTimeNow();
		//for( SapTlSparesreplaced newSapTlSparesreplaced : sparesSAPData)
		for( int i=0;i<sparesSAPData.size();i++)
		{	
			BAL_SapTlSparesreplaced sapTlSparesreplaced = new BAL_SapTlSparesreplaced();
			sapTlSparesreplaced=sparesSAPData.get(i);
			
			CommonFunctions.debugMsg("sapTlSparesreplaced.getRef"+sapTlSparesreplaced.getSspmDocnumber());
			CommonFunctions.debugMsg("sapTlSparesreplaced.getSspmSpareno"+sapTlSparesreplaced.getSspmSpareno());
			/*if(newSapTlSparesreplaced.getSspmDocnumber() == null )			
			{	*/
				
				sapTlSparesreplaced.setSspmCreatedon(dateTime);
				 
			/*}	
			else{
				sapTlSparesreplaced.setPtldCreatedon(oldsapTlSparesreplaced.getPtldCreatedon());
			}*/
			sapTlSparesreplaced.setSspmModifiedon(dateTime);
			sapTlSparesreplaced.setSspmActive("Y");
			CommonFunctions.debugMsg("key Id in service impl"+sapTlSparesreplaced.getSspmKeyId());
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getSspmKeyId())) 
				sapTlSparesreplaced.setSspmKeyId("-");
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getSspmDate()))
				sapTlSparesreplaced.setSspmDate(Constants.futureNullDate );
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getSspmDocnumber()))
				sapTlSparesreplaced.setSspmDocnumber("-");
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getSspmQuantity())) 
				sapTlSparesreplaced.setSspmQuantity("0");
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getSspmRate())) 
				sapTlSparesreplaced.setSspmRate("0");
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getSspmSpareno())) 
				sapTlSparesreplaced.setSspmSpareno("{}");
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getSspmStoragelocation())) 
				sapTlSparesreplaced.setSspmStoragelocation("{}");
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getSspmValue())) 
				sapTlSparesreplaced.setSspmValue("{}");
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getSspmSparename())) 
				sapTlSparesreplaced.setSspmSparename("-");
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getSspmStockAvailable())) 
				sapTlSparesreplaced.setSspmStockAvailable("-");			
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getSspmTempfield4())) 
				sapTlSparesreplaced.setSspmTempfield4("-");
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getSspmTempfield5())) 
				sapTlSparesreplaced.setSspmTempfield5("-");
			if(!BAL_UIUtils.isValidKeyId(sapTlSparesreplaced.getRefDocId())) 
				sapTlSparesreplaced.setRefDocId("-");
			
			sparesReplaceList.add(sapTlSparesreplaced);
		// TODO Auto-generated method stub
		
	}
		 
		return sparesReplaceList;
	
	}
	
	public String updateSparesQty(List<BAL_SapTlSparesreplaced> sparesReplaceList) throws Exception {
		return sapInfoDao.updateSparesQty(sparesReplaceList);
	}
	@Override
	public BAL_SapTlSparesreplaced createSapSpareReplaced( BAL_SapTlSparesreplaced newSapTlSparesreplaced,
			BAL_SapTlSparesreplaced existSapTlSparesreplaced)
			throws Exception {
		String validationsFor;
		validationsFor = "create";
		//validations.validate(newSapTlSparesreplaced,"bdcreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		newSapTlSparesreplaced.setSparesReplaced( fillSpareReplaced(newSapTlSparesreplaced.getSparesReplaced()));
		
	CommonFunctions.debugMsg("sercivece imple .....   "+newSapTlSparesreplaced.getSspmSparename());
    String SD=newSapTlSparesreplaced.getSspmStoragelocation();
    System.out.println(SD+" storagelocation");
		CommonFunctions.debugMsg("After Filling Values Services");	
	return sapInfoDao.createSapSpareReplaced(newSapTlSparesreplaced);
	}
	
	public List<String[]> getAllCrmmasterData() throws Exception{
		return sapInfoDao.getAllCrmmasterData();
	}
	
	public List<String[]> getCrmBillItemsData(String crmNo) throws Exception{
		return sapInfoDao.getCrmBillItemsData(crmNo);
	}
	public List<String[]> getCrmNotesData(String crmNo) throws Exception{
		return sapInfoDao.getCrmNotesData(crmNo);
	}
	public List<String[]> getDownTimeData(CommonFilter commonFilter) throws Exception{
		return sapInfoDao.getDownTimeData(commonFilter);
	}
	
	@Override
	public List<String[]> getFillGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getFillGrid(commonFilter);
	}
	
	public List<String[]> getNotificationData() throws Exception{
		return sapInfoDao.getNotificationData();
	}

	@Override
	public List<String[]> getFillDownTimeGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getFillDownTimeGrid(commonFilter);
	}
	@Override
	public List<String[]> getFillDownTimeReasonsGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getFillDownTimeReasonsGrid(commonFilter);
	}
	@Override
	public List<String[]> getHRMSFillGrid(CommonFilter commonFilter, String mode)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getHRMSFillGrid(commonFilter,mode);
	}
	@Override
	public List<String[]> getHRMSEmployeeFillGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getHRMSEmployeeFillGrid(commonFilter);
	}
	@Override
	public List<String[]> getHRMSShiftFillGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getHRMSShiftFillGrid(commonFilter);
	}
	@Override
	public List<String[]> getOperationGrid(String orderNo)
			throws Exception {
		return this.sapInfoDao.getOperationGrid(orderNo);
	}
	@Override
	public List<String[]> getMaterialGrid(String orderNo)
			throws Exception {
		return this.sapInfoDao.getMaterialGrid(orderNo);
	}

	@Override
	public Workbook getMaintExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return sapInfoDao.getMaintExcel( colmodel,  format, commonFilter);
	}
	public int getDownTimeCount(CommonFilter commonFilter) throws Exception{
		return sapInfoDao.getDownTimeCount(commonFilter);
	}
	@Override
	public Workbook getHrmsExportExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter, String mode) throws Exception {
		// TODO Auto-generated method stub
		return sapInfoDao.getHrmsExportExcel(colmodel,format,commonFilter,mode);
	}
	@Override
	public Workbook getShiftExportExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter, String mode) throws Exception {
		// TODO Auto-generated method stub
		return sapInfoDao.getShiftExportExcel(colmodel,format,commonFilter,mode);
	}
	@Override
	public Workbook getEmployeemstExportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return sapInfoDao.getEmployeemstExportExcel(colmodel,format,commonFilter);
	}
	@Override
	public List<String[]> getNPCFillGrid(CommonFilter commonFilter, String mode)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getNPCFillGrid(commonFilter,mode);
	}
	@Override
	public List<String[]> getREPULPFillGrid(CommonFilter commonFilter,
			String mode) throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getREPULPFillGrid(commonFilter,mode);
	}
	@Override
	public List<String[]> getTaskListFillGrid(CommonFilter commonFilter,
			String mode) throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getTaskListFillGrid(commonFilter,mode);
	}
	@Override
	public List<String[]> getProductionDetailsFillGrid(
			CommonFilter commonFilter, String mode) throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getProductionDetailsFillGrid(commonFilter,mode);
	}
	@Override
	public List<String[]> getMaterialMasterFillGrid(CommonFilter commonFilter
			) throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.getMaterialMasterFillGrid(commonFilter);
	}
	@Override
	public List<ComboBox> getOrdertype(String docType,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		String doctype=docType;
		CommonFunctions.debugMsg(doctype+";;;;;;;;;;;;"+docType);
		comboFilter.setCodeField("SMT_CODE");
		comboFilter.setNameField("SMT_DESCRIPTION");
		comboFilter.setIdField("SMT_KEYID");
		StringBuilder CondSql=new StringBuilder();
		if(doctype.equals("BDM")){
		CondSql.append("And SMT_CODE='PM01'");
		}
		else if(doctype.equals("PM")){
			CondSql.append("And SMT_CODE='PM02'");
		}
		else if(doctype.equals("GEN")){
		CondSql.append("And SMT_CODE<>'PM01'And SMT_CODE<>'PM02'And SMT_CODE<>'PM11'And SMT_CODE<>'PM14'");
		}
		comboFilter.setCondSql(CondSql.toString());
		comboFilter.setTableName(TableNames.TBL_BAL_SAP_TL_MAINTORDERTYPE);
		
		CommonFunctions.debugMsg(" get id Ordertype ::"+comboFilter.getIdField());
		return commonFilterDao.fillComboValues(comboFilter);		
	
	}
		@Override
	public String delteSparesDetail(String keyId) throws Exception {
		// TODO Auto-generated method stub
		return this.sapInfoDao.delteSparesDetail(keyId);
	}
	@Override
	public String delteSparesDetail(List<BAL_SapTlSparesreplaced> sparesReplaceList)
			throws Exception {
		return sapInfoDao.delteSparesDetail(sparesReplaceList);
	}
}
