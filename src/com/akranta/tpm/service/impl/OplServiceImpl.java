package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.ImprovementProjSheetKaiDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.ImprovementProjSheetKaiDao;
import com.akranta.tpm.dao.OPLDao;
import com.akranta.tpm.dao.impl.OplDaoImpl;
import com.akranta.tpm.exportreport.ImprovementProjectSheetTemplate;
import com.akranta.tpm.exportreport.OPLGeneralTemplate;
import com.akranta.tpm.exportreport.OPLITCExcelTemplate;
import com.akranta.tpm.exportreport.OplExcelTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.service.OplService;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class OplServiceImpl implements OplService {

	private OPLDao oplDao;
	private ImprovementProjSheetKaiDao improvementProjSheetKaiDao;
	DBActionTemplate  dbActionTemplate ; 
	
		public OplServiceImpl(DBActionTemplate dbActionTemplate)
		{
			this.dbActionTemplate  =dbActionTemplate ; 
			oplDao =  new OplDaoImpl(dbActionTemplate);
			improvementProjSheetKaiDao =new ImprovementProjSheetKaiDaoImpl(dbActionTemplate); 
		}
		public void OplServiceImplJwt(String JwtToken){
		    try{
		    	oplDao.OplDaoImplJwt(JwtToken);   // dao side
		        // (Optional) if you want service-level direct access
		       //  oplServiceApi = new OplTlMstServiceApi(JwtToken);
		    } catch(Exception e){
		        e.printStackTrace();
		    }
		}
		
		public List<String []> getAllOPl(CommonFilter commonFilter) throws Exception
		{
			return this.oplDao.getOplReport(commonFilter);
		}

		@Override
		public List<String[]> getAllexlOPl(CommonFilter commonFilter)
				throws Exception {
			// TODO Auto-generated method stub
			return this.oplDao.getOplexlReport(commonFilter);
		}

		@Override
		public List<String[]> getAlloplcountmodifyList(CommonFilter commonFilter)
				throws Exception {
			// TODO Auto-generated method stub
			return this.oplDao.getoplcountmodify(commonFilter);
		}

	
		@Override
		public List<GenTlAllmoduleimgfile>  getoplImage( String fileName, String filePath,String oplId)throws NoDataFoundException, Exception
		{
			// TODO Auto-generated method stub
			CommonMessage.debugMsg("Inside Service Impl EXL image");
			List<GenTlAllmoduleimgfile> oplImgList = new ArrayList<GenTlAllmoduleimgfile>();
			if(UIUtils.isValidKeyId(oplId))
			{
		
				GenTlAllmoduleimgfile oplBeforeImage = new GenTlAllmoduleimgfile();
					
				oplBeforeImage.setImflBlobimage(filePath);
				oplBeforeImage.setImflFilename(fileName);
				oplBeforeImage.setImflRefkeyid(oplId);
				oplBeforeImage.setImflRefdoctype("OPL");
				oplBeforeImage.setImflImagetype("PRE");
				oplImgList.add(oplBeforeImage);
				
				GenTlAllmoduleimgfile oplAfterImage = new GenTlAllmoduleimgfile();
				
				oplAfterImage.setImflBlobimage(filePath);
				oplAfterImage.setImflFilename(fileName);
				oplAfterImage.setImflRefkeyid(oplId);
				oplAfterImage.setImflRefdoctype("OPL");
				oplAfterImage.setImflImagetype("AFT");
				oplImgList.add(oplAfterImage);

			}
			
		//	oplTlMst.setAllmoduleimgfile(oplImgList);
		//	return oplTlMst;
			CommonMessage.debugMsg("List Size ="+oplImgList.size());
			CommonMessage.debugMsg("List Size ="+oplImgList.get(0).getImflFilename());
					
			return this.oplDao.getoplImage(oplImgList);
		}

		@Override
		public List<String[]> getAllStudent(String oplId) throws Exception {
			// TODO Auto-generated method stub
			return this.oplDao.getAllStudent(oplId);
		}

		@Override
public Workbook kaizenExportExcel(String oPLId, String format, String path,String flid,String type, String User,String imagePath, CommonFilter commonFilter) throws Exception {
			
			// List<String[]> kaizenData = oplDao.getOplExcelReport(oPLId,format,commonFilter);
			 Map<Integer, List<String[]>> oplData = oplDao.OplExcelReport(oPLId,format,commonFilter);
			//return this.improvementProjSheetKaiDao.getImprovementProjSheetkaiexlReport(kaizId,format);
			 
			List<String[]> ApprovalData = improvementProjSheetKaiDao.getImprovementProjSheetkaiexlReportApproval(User,flid,"OPLAPPROVE",oPLId);
			
			List<String[]> funclcnData = oplDao.getImprovementProjSheetkaiexlReportFunctllocn(flid);
			
			CommonMessage.debugMsg(" Inside Service Impl :: "+oplData+" Inside Service Impl :: "+oplData.get(1));
			Workbook wb=null;
			//Workbook wb = ( new OplExcelTemplate(dbActionTemplate  )).fillValues(oplData,format,path,oPLId,imagePath);
			//Workbook wb = ( new OplExcelTemplate(dbActionTemplate  )).fillValues(oplData,format,path,oPLId,imagePath);
			//Workbook wb=(new OPLITCExcelTemplate(dbActionTemplate)).fillValues(oplData, ApprovalData,funclcnData,format, path, oPLId, imagePath);
			CommonMessage.debugMsg("type inh serv impl"+type);

			//Workbook wb=(new OPLGeneralTemplate(dbActionTemplate)).fillValues(oplData, ApprovalData,funclcnData,format, path, oPLId, imagePath);
			//CommonMessage.debugMsg("Workbook::::::: service impl:"+wb.getSheetAt(0));
			if(type.equals("general")){
				wb=(new OPLGeneralTemplate(dbActionTemplate)).fillValues(oplData, ApprovalData,funclcnData,format, path, oPLId, imagePath);
			 CommonMessage.debugMsg("type"+type);
			}
			else if(type.equals("other")) {
				 CommonMessage.debugMsg("type"+type);
				 wb=(new OPLITCExcelTemplate(dbActionTemplate)).fillValues(oplData, ApprovalData,funclcnData,format, path, oPLId, imagePath);
				 CommonMessage.debugMsg("type"+type);
			}
			CommonMessage.debugMsg("Service implto be gae........."+wb);
			return wb;
		
		}


		@Override
		public Workbook getAllOPlxl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
			// TODO Auto-generated method stub
			return this.oplDao.getAlloplxl(commonFilter,colmodel,rptFormat);
		}

		@Override
		public List<String[]> getOPLSummaryGridData(CommonFilter commonFilter) throws Exception{
			return oplDao.getOPLSummaryGridData(commonFilter);
		}
		
		@Override
		public Workbook getOplSummaryGridDataExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception {
			
			return oplDao.getOplSummaryGridDataExportExcel(commonFilter,tableModel,format);
		}
		
		
		 

		
		
	}