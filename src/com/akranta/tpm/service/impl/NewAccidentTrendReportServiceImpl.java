package com.akranta.tpm.service.impl;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.HSEAccTrendRptDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.HSEAccTrendRptDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.NewAccidentTrendReportService;

import net.sf.json.JSONObject;

public class NewAccidentTrendReportServiceImpl implements NewAccidentTrendReportService{
	private HSEAccTrendRptDao hSEAccTrendRptDao;
	DBActionTemplate  dbActionTemplate;
	public NewAccidentTrendReportServiceImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		hSEAccTrendRptDao =  new HSEAccTrendRptDaoImpl(dbActionTemplate);
	}
	
	public void NewAccidentTrendReportServiceImplJwt(String JwtToken){
    	try{
    		hSEAccTrendRptDao.HSEAccTrendRptDaoImplJwt(JwtToken);
    		//serviceApi = new BestKaizenServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	public List<String []> getNewAccidentRpt(CommonFilter commonFilter)  throws Exception{
		return hSEAccTrendRptDao.getNewAccidentRpt(commonFilter);
	}
	public Workbook NewAccidentTrendExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		return hSEAccTrendRptDao.NewAccidentTrendExportExcel(commonFilter, colmodel,rptFormat);
	}
	public List<String[]> getNewHseAccTrendRptgraph(CommonFilter chrtCommonFilter, String keyId)throws Exception{
		return hSEAccTrendRptDao.getNewHseAccTrendRptgraph(chrtCommonFilter,keyId);
	}
	public List<String []> getNewBodyPartRpt(CommonFilter commonFilter)  throws Exception{
		return hSEAccTrendRptDao.getNewBodyPartRpt(commonFilter);
	}
	public List<String[]> getNewBodyPartRptGraph(CommonFilter chrtCommonFilter, String keyId)throws Exception{
		return hSEAccTrendRptDao.getNewBodyPartRptGraph(chrtCommonFilter,keyId);
	}
	public Workbook NewBodyPortExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		return hSEAccTrendRptDao.NewBodyPortExportExcel(commonFilter, colmodel, rptFormat);
	}
	public List<String[]> getModeWiseReport(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
      return this.hSEAccTrendRptDao.getModeWiseReport(commonFilter);
      }
	public List<String[]> getModeWiseRptReportgraph(CommonFilter commonFilter,String rowId)	throws Exception {			
		// TODO Auto-generated method stub			
		return this.hSEAccTrendRptDao.getModeWiseRptReportgraph(commonFilter,rowId);	
		}
	public Workbook ModeWiseReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		return this.hSEAccTrendRptDao.ModeWiseReportExportExcel(commonFilter,colmodel,rptFormat);	
	}
	public List<String[]> getEmployeeIncident(CommonFilter commonFilter) throws Exception{
		 return this.hSEAccTrendRptDao.getEmployeeIncident(commonFilter);
	}
	public Workbook EmpAccidentExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		 return this.hSEAccTrendRptDao.EmpAccidentExportExcel(commonFilter, colmodel, rptFormat);
	}
}
