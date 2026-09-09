package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.IntRejEntryBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlInternalrejectionhourly;
import com.akranta.tpm.model.QtmTlIntrejectiondtl;
import com.akranta.tpm.model.QtmTlIntrejectionmst;
import com.akranta.tpm.model.QtmTlTestscrapbrkup;

public interface IntRejEntryService {

	public List<ComboBox> getPhenomena(String processId, ComboFilter comboFilter) throws Exception ;
	public List<ComboBox> getProcessCombo(ComboFilter comboFilter) throws Exception;
	
	public List<String[]> getInternalRejectionViewGrid(String condParam, String detailTableName) throws Exception ;
	public List<String[]> getInternalRejectionEntryGrid(String condParam) throws Exception ;
	public List<String[]> getInternalRejectionEntryTestGrid(String condParam,CommonFilter commonFilter) throws Exception ;
	public Workbook intRejExportExcel( String condParam,JSONObject colmodel, String format) throws Exception;
	public List<String[]> getEntryExistsDates(String detailTable, String entryDate, String cellId, String mchId) throws Exception ;
	public List<String[]> getScrapBreakup(String rejectionId,String referenceId) throws Exception ;
	public List<String[]> getPendingColor(String entryDate,String cellId,String mchId,String detailTable) throws Exception;
	public String getIsHourlyEntry() throws Exception;

	public List<String[]> getIntRejHourBreakGrid(String qirmKeyid) throws Exception ;
	
	public String checkQirm(String QirmPldetailsid,String QirmProductid) throws Exception ;
	
	
	public QtmTlIntrejectiondtl create(QtmTlIntrejectionmst qtmTlIntrejectionmst, 
			QtmTlIntrejectiondtl qtmTlIntrejectiondtl, IntRejEntryBean intRejEntryBean) throws Exception;
	public QtmTlIntrejectiondtl createYY(QtmTlIntrejectionmst qtmTlIntrejectionmst, 
			QtmTlIntrejectiondtl qtmTlIntrejectiondtl, IntRejEntryBean intRejEntryBean) throws Exception, ValidationExceptions;
	public QtmTlInternalrejectionhourly createHourly(QtmTlInternalrejectionhourly qtmTlInternalrejectionhourly, 
			QtmTlIntrejectionmst qtmTlIntrejectionmst,IntRejEntryBean intRejEntryBean) throws Exception;
	
	
	public QtmTlTestscrapbrkup createBreakup(QtmTlTestscrapbrkup newQtmTlTestscrapbrkup, 
			QtmTlTestscrapbrkup oldQtmTlTestscrapbrkup,String insQty,String qaHold) throws Exception;
	public QtmTlIntrejectionmst create(
			QtmTlIntrejectionmst newQtmTlIntrejectionmst,
			List<QtmTlIntrejectiondtl> newQtmTlIntrejectiondtl) throws Exception, ValidationExceptions;
	public QtmTlIntrejectionmst update(
			QtmTlIntrejectionmst newQtmTlIntrejectionmst,
			List<QtmTlIntrejectiondtl> newQtmTlIntrejectiondtl) throws Exception, ValidationExceptions;
	public List<String[]> getInternalRejectionMstGrid(CommonFilter commonFilter) throws Exception;
	public QtmTlIntrejectionmst getInternalRejectionMstdata(String masterid) throws Exception;
	public List<ComboBox> getproductCombo(ComboFilter comboFilter, String factId, String machId,
			String prdModelId, String entryDate, String string) throws Exception;
	public Workbook getMstGridExcelData(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception ;
	public void IntRejEntryImplJwt(String JwtToken);

}
