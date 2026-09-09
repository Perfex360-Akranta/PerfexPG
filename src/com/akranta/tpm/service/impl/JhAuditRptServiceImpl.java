package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.JhAuditRptDao;

import com.akranta.tpm.dao.impl.JhAuditRptDaoImpl;
import com.akranta.tpm.exportreport.JHAuditExcelReportTemplate;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.JhAuditRptService;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.MomServiceApi;

public class JhAuditRptServiceImpl implements JhAuditRptService {
	private JhAuditRptDao jhAuditRptDao;
	FunctionCallApi fnCallApi;
	
	DBActionTemplate  dbActionTemplate ; 
	public JhAuditRptServiceImpl(DBActionTemplate dbActionTemplate)
	{
		dbActionTemplate  =dbActionTemplate  ;
		jhAuditRptDao =  new JhAuditRptDaoImpl(dbActionTemplate);
	}
	

	public List<String[]> getJhAuditRpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.jhAuditRptDao.getJhAuditRpt(commonFilter);
	}
	
	public void JhAuditRptServiceImplJwt(String JwtToken){
    	try{
    		jhAuditRptDao.JhAuditRptDaoImplJwt(JwtToken);
    		//momServiceApi = new MomServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	
	
	public Workbook getAllJHAuditRptExl(String rowId, String format,String path, String imagePath) throws Exception {
		// List<String[]> whyData = whywhyReportDao.getwhywhyRptExl(rowId,format);	
		 Map<Integer, List<String[]>> whyData = jhAuditRptDao.getAllJHAuditRptExl(rowId,format);
		//return this.improvementProjSheetKaiDao.getImprovementProjSheetkaiexlReport(kaizId,format);
		Workbook wb = ( new JHAuditExcelReportTemplate(dbActionTemplate  )).fillValues(whyData,format,path,rowId,imagePath);
		
		return wb;
	}
	
	public Workbook JhAuditReportExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
		return this.jhAuditRptDao.getJhAuditReportExportExcel(commonFilter,colmodel,rptFormat);
	}
	
	
	
	
	@Override
	public List<String[]> getHseAccTrendRptgraph(CommonFilter commonFilter,String keyId)
	throws Exception {
	// TODO Auto-generated method stub
	return this.jhAuditRptDao.getHseAccTrendRptgraph(commonFilter,keyId);
}


	@Override
	public Workbook JhAuditActionReportExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return this.jhAuditRptDao.JhAuditActionReportExportExcel(commonFilter,tblJSONObj,format);
	}

	//self audit grpah
	
//kaizen per persons
	
	@Override
	public List<String[]> getAllAuditScoreGraph(CommonFilter commonFilter)throws Exception {
		return this.jhAuditRptDao.getAllAuditScoreGraph(commonFilter);
	}

	@Override
	public Workbook jhAuditGraphExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.jhAuditRptDao.jhAuditGraphExportExcel(commonFilter,colModel,rptFormat);
	}

}
