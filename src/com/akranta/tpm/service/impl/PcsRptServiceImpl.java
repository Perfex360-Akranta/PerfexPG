
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.PcsRptDao;

import com.akranta.tpm.dao.impl.PcsRptDaoImpl;
import com.akranta.tpm.exportreport.ImprovementProjectSheetTemplate;
import com.akranta.tpm.exportreport.PCSDeatailExl;

import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.service.PcsRptService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;



public class PcsRptServiceImpl implements PcsRptService {

private PcsRptDao pcsRptDao;
DBActionTemplate  dbActionTemplate  ;
	public PcsRptServiceImpl(DBActionTemplate dbActionTemplate)
	{
		dbActionTemplate  =dbActionTemplate  ;
		pcsRptDao =  new PcsRptDaoImpl(dbActionTemplate);
	}
	
	public List<String []> getAllPcsRpt(CommonFilter commonFilter) throws Exception
	{
		return this.pcsRptDao.getpcsRpt(commonFilter);
	}
	public List<String []> getCellRpt(CommonFilter commonFilter) throws Exception
	{
		return this.pcsRptDao.getcellRpt(commonFilter);
	}
	

 
	public List<String[]> getAllPcsRptExl(String rowId,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.pcsRptDao.getAllPcsRptExl( rowId ,commonFilter);
	}
	
	public List<String[]> getPlanVsActual(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.pcsRptDao.getPlanVsActual(commonFilter);
	}

	
/*	public List<String[]> getAllPcsRptExl1(String rowId, String date,CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.pcsRptDao.getAllPcsRptExl1(rowId,date, commonFilter);
	}*/

	
	public List<String[]> getAllPcsRptExl1(String rowId, String date,
			String sectId,String prlmid,String shift, CommonFilter commonFilter, String locn) throws Exception {
		// TODO Auto-generated method stub
		return this.pcsRptDao.getAllPcsRptExl1(rowId,date,sectId,prlmid,shift,commonFilter,locn);
	}
	@Override
	public Workbook CellEfficiencyExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.pcsRptDao.getCellEfficiencyExcel(commonFilter,colmodel,rptFormat);
	}

	
	@Override
	public Workbook pcsExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.pcsRptDao.getPcsRptExl(commonFilter,colmodel,rptFormat);
	}

	@Override
	public List<String[]> getAllPcsRptdetail(String rowId, String date,	String sectId, String prlmid, String shiftId,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.pcsRptDao.getAllPcsRptdtl(rowId,date,sectId,prlmid,shiftId, commonFilter);
	}

	@Override
	public Workbook getAllPcsRptdetailExl(String rowId, String date,String sectId, String prlmid,String shiftId, String format, String path,String imagePath,String shift) throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, List<String[]>> pcsData = pcsRptDao.getAllPcsRptdtlExl(rowId,date,sectId,prlmid,shiftId,format,path,imagePath);	
		//return this.improvementProjSheetKaiDao.getImprovementProjSheetkaiexlReport(kaizId,format);
		Workbook wb = ( new PCSDeatailExl(dbActionTemplate  )).fillValues(pcsData,rowId,date,sectId,prlmid,shiftId,format,path,imagePath,shift);
		
		CommonMessage.debugMsg("Service implto be gae........."+wb);
		return wb;
	}

	@Override
	public List<String[]> getlossBreakup(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.pcsRptDao.getlossBreakup(commonFilter);
	}

	@Override
	public Workbook getlossBreakupExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return pcsRptDao.getlossBreakupExcel(commonFilter,tblJSONObj,format);
	}

	
	public List<String[]> getSectAndMechWiseDefect(CommonFilter commonFilter)	throws Exception {
		return this.pcsRptDao.getSectAndMechWiseDefect(commonFilter);
	}

	@Override
	public Workbook getSectAndMechWiseDefectExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		return pcsRptDao.getSectAndMechWiseDefectExcel(commonFilter,tblJSONObj,format);
	}

	@Override
	public List<String[]> getLossTimeBreakupSmry(CommonFilter commonFilter)	throws Exception {
		return this.pcsRptDao.getLossTimeBreakupSmry(commonFilter);
	}

	@Override
	public Workbook getLossTimeBreakupSmryExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		return pcsRptDao.getLossTimeBreakupSmryExcel(commonFilter,tblJSONObj,format);
	}
	
	public Workbook pcsPlanVsActualExport(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		return pcsRptDao.pcsPlanVsActualExport(commonFilter,tblJSONObj,format);
	}


	public List<String[]> getPlanVsRejData(CommonFilter commonFilter)
			throws Exception {
		return this.pcsRptDao.getPlanVsRejData(commonFilter);
	}

	public Workbook PlanVsRejExportExcel(CommonFilter commonFilter,	JSONObject colmodel, String rptFormat) throws Exception {
		return this.pcsRptDao.PlanVsRejExportExcel(commonFilter,colmodel,rptFormat);
	}

	
}