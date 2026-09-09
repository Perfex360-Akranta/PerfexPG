package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.IntRejEntryDao;
import com.akranta.tpm.dao.InternalRejectionRptDao;
import com.akranta.tpm.dao.impl.InternalRejectionRptDaoImpl;
import com.akranta.tpm.exportreport.InternalRejectionReport;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.InternalRejectionRptService;
import com.akranta.tpm.service.api.InternalRejectionSerivceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class InternalRejectionRptServiceImpl implements InternalRejectionRptService{

	private InternalRejectionRptDao internalRejectionRptDao;
	DBActionTemplate  dbActionTemplate;
	public InternalRejectionRptServiceImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		internalRejectionRptDao = new InternalRejectionRptDaoImpl(dbActionTemplate);
	
	}
	
	public void InternalRejectionRptServiceImplJwt(String JwtToken){
    	try{
    		internalRejectionRptDao.InternalRejectionRptDaoImplJwt(JwtToken);
    		//irServiceApi = new InternalRejectionSerivceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}}
	
	public List<String[]> getInternalRejectionRpt(CommonFilter commonFilter)  throws Exception {
		
		return this.internalRejectionRptDao.getInternalRejection(commonFilter);
	}

	public List<String[]> getInternalRejectionRptnew(CommonFilter commonFilter)  throws Exception {
		CommonMessage.debugMsg("Inside Service Impl");
		return this.internalRejectionRptDao.getInternalRejectionRpt(commonFilter);
	}
	
	
	public Map<Integer, List<String[]>> getAllData(CommonFilter commonFilter)	throws Exception {
		Map<Integer, List<String[]>> internalData = internalRejectionRptDao.getAllRejection(commonFilter);	
		
		return internalData;
		
	}

	@Override
	public Workbook getAllExcelData(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception{
		return this.internalRejectionRptDao.getAllExcelData(commonFilter,  colModel,rptFormat);
	}

	
	public Workbook getExportExcel(String format, JSONObject tblJSONObj,CommonFilter commonFilter) throws Exception {
		
		
		// Map<Integer, List<String[]>> internalRejData = internalRejectionRptDao.getExportExcel(format,tblJSONObj,commonFilter);
		 //Workbook wb = ( new InternalRejectionReport(dbActionTemplate)).fillInternalValues(internalRejData,format,tblJSONObj);
		Workbook wb = internalRejectionRptDao.getAllExcelDataNew(commonFilter, tblJSONObj, format);
		 return wb;
		
	}

}
