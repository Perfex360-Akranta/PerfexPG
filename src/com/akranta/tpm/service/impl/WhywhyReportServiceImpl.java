package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.WhywhyReportDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.WhywhyReportDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ImprovementProjectSheetTemplate;
import com.akranta.tpm.exportreport.WhyWhyQtyReportTemplate;
import com.akranta.tpm.exportreport.whywhyReportTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.service.WhywhyReportService;
import com.akranta.tpm.service.api.WhywhyServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;


public  class WhywhyReportServiceImpl implements WhywhyReportService {

private WhywhyReportDao whywhyReportDao;
private CommonFilterDao commonFilterDao;
private DBActionTemplate dbActionTemplate;
private WhywhyServiceApi whywhyServiceApi;


	public WhywhyReportServiceImpl(DBActionTemplate dbActionTemplate)
	{
		CommonMessage.debugMsg("dbActionTemplate in ServiceImpl==="+dbActionTemplate);
		this.dbActionTemplate =dbActionTemplate;
		whywhyReportDao =  new WhywhyReportDaoImpl(dbActionTemplate);
		CommonMessage.debugMsg("whywhyReportDao in ServiceImpl==="+whywhyReportDao);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		CommonMessage.debugMsg("dthis.dbActionTemplateServiceImpl==="+this.dbActionTemplate);
	}
	
	public void WhywhyReportServiceImplJwt(String JwtToken){
    	try{
    		whywhyReportDao.WhywhyReportDaoImplJwt(JwtToken);
    		whywhyServiceApi = new WhywhyServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	public List<String[]> getAllwhywhyStd(CommonFilter commonFilter) throws Exception
	{
		
		return this.whywhyReportDao.getWhywhyStd(commonFilter);
		
	}
	public List<String[]> getAllwhywhyQtyStd(CommonFilter commonFilter) throws Exception
	{
		
		return this.whywhyReportDao.getWhywhyQtyStd(commonFilter);
		
	}
	public List<ComboBox> getYY(String condSql,ComboFilter comboFilter) throws Exception
	{
//		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("BCSM_CODE");
		comboFilter.setNameField("WWMS_KEYID");
		comboFilter.setIdField("WWMS_KEYID");	
		comboFilter.setTableName(TableNames.TBL_BDM_TL_WHYWHYMST);
		return commonFilterDao.fillComboValues(comboFilter);	
	}


	public List<String[]> getAllStudent(String rowId) throws Exception {
	
		return this.whywhyReportDao.getAllStudent(rowId);
	}

	
	public List<String[]> getAllexlyy(CommonFilter commonFilter) throws Exception {
	
		return this.whywhyReportDao.getWhywhy(commonFilter);
	}

	@Override
	public List<GenTlAllmoduleimgfile> getAllwhywhyexcel(String fileName,String filePath, String rowId) throws NoDataFoundException,Exception {
		CommonMessage.debugMsg("Inside Service Impl EXL image");
		List<GenTlAllmoduleimgfile> yyImgList = new ArrayList<GenTlAllmoduleimgfile>();
		if(UIUtils.isValidKeyId(rowId))
		{
	
			GenTlAllmoduleimgfile yyBeforeImage = new GenTlAllmoduleimgfile();
				
			yyBeforeImage.setImflBlobimage(filePath);
			yyBeforeImage.setImflFilename(fileName);
			yyBeforeImage.setImflRefkeyid(rowId);
			yyBeforeImage.setImflRefdoctype("YY");
			yyBeforeImage.setImflImagetype("PRE");
			yyImgList.add(yyBeforeImage);
			
			GenTlAllmoduleimgfile yyAfterImage = new GenTlAllmoduleimgfile();
			
			yyAfterImage.setImflBlobimage(filePath);
			yyAfterImage.setImflFilename(fileName);
			yyAfterImage.setImflRefkeyid(rowId);
			yyAfterImage.setImflRefdoctype("YY");
			yyAfterImage.setImflImagetype("AFT");
			yyImgList.add(yyAfterImage);

		}
		return this.whywhyReportDao.getyyImage(yyImgList);
		
	}

	@Override
	public List<String[]> getAllwhywhyStdExl(String rowId) throws Exception {
		// TODO Auto-generated method stub
		return this.whywhyReportDao.getAllwhywhyStdExl(rowId);
	}

	@Override
	public Workbook yyExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.whywhyReportDao.getwhywhyStdExl(commonFilter,colmodel,rptFormat);
	}
	
	
	public Workbook getAllwhywhyQtyExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.whywhyReportDao.getAllwhywhyQtyExcel(commonFilter,colmodel,rptFormat);
	}
	@Override
	public Workbook getAllwhywhyRptExl(String rowId, String format,String path, String imagePath) throws Exception {
		// List<String[]> whyData = whywhyReportDao.getwhywhyRptExl(rowId,format);	
		 Map<Integer, List<String[]>> whyData = whywhyReportDao.getwhywhyRptExl(rowId,format);
		//return this.improvementProjSheetKaiDao.getImprovementProjSheetkaiexlReport(kaizId,format);
		Workbook wb = ( new whywhyReportTemplate(dbActionTemplate)).fillValues(whyData,format,path,rowId,imagePath);
		
		return wb;
	}
	
	public Workbook getAllwhywhyQtyRptExl(String rowId, String format,String path, String imagePath) throws Exception {
		// List<String[]> whyData = whywhyReportDao.getwhywhyRptExl(rowId,format);	
		 Map<Integer, List<String[]>> whyData = whywhyReportDao.getwhywhyQtyRptExl(rowId,format);
		//return this.improvementProjSheetKaiDao.getImprovementProjSheetkaiexlReport(kaizId,format);
		Workbook wb = ( new WhyWhyQtyReportTemplate(dbActionTemplate)).fillValues(whyData,format,path,rowId,imagePath);
		
		return wb;
	}

	@Override
	public List<String[]> getanalysis() throws Exception {
		// TODO Auto-generated method stub
		return whywhyReportDao.getanalysis();
	}

	@Override
	public List<String[]> getProposed(CommonFilter commonFilter) throws Exception {
		CommonMessage.debugMsg(" In side the Service Impl  Proposed grid 12");

		return whywhyReportDao.getProposed(commonFilter);
	}
	@Override
	public List<String[]> getMainGrid() throws Exception {
		return whywhyReportDao.getMainGrid();
	}

	@Override
	public List<String[]> getYYEffectiveness(CommonFilter commonFilter) throws Exception {
		return whywhyReportDao.getYYEffectiveness(commonFilter);
	}

	@Override
	public List<String[]> getanalysis(String masdetkeyid) throws Exception {
		return whywhyReportDao.getanalysis(masdetkeyid);
		//return whywhyServiceApi.getAnalysis(masdetkeyid);
		
	}

	@Override
	public List<String[]> FillControlData(String cellId,String keyid, String formName) throws Exception {
		// TODO Auto-generated method stub
		return whywhyReportDao.FillControlData(cellId,keyid,formName);
	}
	
	public Workbook getwhywhyExlView(String rowId, String format,String path, String imagePath) throws Exception {
		// List<String[]> whyData = whywhyReportDao.getwhywhyRptExl(rowId,format);	
		 Map<Integer, List<String[]>> whyData = whywhyReportDao.getwhywhyExlView(rowId,format);
		 CommonMessage.debugMsg("whyData.size::::::"+whyData.size());
		//return this.improvementProjSheetKaiDao.getImprovementProjSheetkaiexlReport(kaizId,format);
		Workbook wb = ( new whywhyReportTemplate(dbActionTemplate)).fillValuesExlView(whyData,format,path,rowId,imagePath);
	
		/*String fileName =path + "/MOC_Details__" + UIUtils.now() +format;

		FileOutputStream out = new FileOutputStream( fileName );
		
		
	    wb.write(out); 
	    out.close();
	    out = null;
	    wb = null;*/
		return wb;
	}

	@Override
	public Workbook WhyWhyEffectivenessExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format) throws Exception{
		// TODO Auto-generated method stub
		return whywhyReportDao.WhyWhyEffectivenessExportExcel(commonFilter,colmodel,format);
	}
	
	@Override 
	public List<String[]> getyyDoneby(String masterkeyid) throws Exception {
		// TODO Auto-generated method stub
		//return whywhyReportDao.getyyDoneby(masterkeyid);
		return whywhyServiceApi.getYyDoneby(masterkeyid);
	}
	public List<String[]> getProbAttby(String masterkeyid) throws Exception {
		// TODO Auto-generated method stub
		
		//return whywhyReportDao.getProbAttby(masterkeyid);
		return whywhyServiceApi.getProbAttby(masterkeyid);
	}
	
	
}

