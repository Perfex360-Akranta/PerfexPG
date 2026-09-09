/*  Author ManiKandan*/
package com.akranta.tpm.service.impl;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.KznTlHdmstDaoImpl;
import com.akranta.tpm.dao.impl.KznTlMstDaoImpl;
import com.akranta.tpm.dao.impl.WorkFlowmstDaoImpl;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.ImprovementProjSheetKaiDao;
import com.akranta.tpm.dao.KznTlHdmstDao;
import com.akranta.tpm.dao.KznTlMstDao;
import com.akranta.tpm.dao.WorkFlowmstDao;
import com.akranta.tpm.dao.impl.ImprovementProjSheetKaiDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.ImprovementProjSheetKaiService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.exportreport.ImprovementProjectSheetTemplate;
import com.akranta.tpm.exportreport.KaizenExcelTemplate;

public class ImprovementProjSheetKaiServiceImpl implements ImprovementProjSheetKaiService {

	private ImprovementProjSheetKaiDao improvementProjSheetKaiDao;
	private KznTlMstDao kznTlMstDao;
	private WorkFlowmstDao workflowmstdao;
		DBActionTemplate  dbActionTemplate;
		
		
		public ImprovementProjSheetKaiServiceImpl(DBActionTemplate dbActionTemplate)
		{
			this.dbActionTemplate = dbActionTemplate;
			improvementProjSheetKaiDao =  new ImprovementProjSheetKaiDaoImpl(dbActionTemplate);
			kznTlMstDao = new KznTlMstDaoImpl(dbActionTemplate);
			workflowmstdao =new WorkFlowmstDaoImpl(dbActionTemplate);
		}
		
		public List<String []> getAllImprProjSht(CommonFilter commonFilter) throws Exception
		{
			return this.improvementProjSheetKaiDao.getImprovementProjSheetkaiReport(commonFilter);
		}
		
		/*public List<String []> getAllImprProjShtexl(String kaizId) throws Exception
		{
			return this.improvementProjSheetKaiDao.getImprovementProjSheetkaiexlReport(kaizId);
		}*/	
		/*public List<String[]> getAllImprProjShtexl(String kaizId)	throws Exception {
			return null;
		}*/

		public Workbook kaizenExportExcel(String kaizId, String flid,String benTypeVal, String User,String format,String path,String imagePath,String workFlow) throws Exception {
			CommonFilter commonFilter = new CommonFilter();
			Map<Integer, List<String[]>> kaizenData = improvementProjSheetKaiDao.getImprovementProjSheetkaiexlReport(kaizId,flid,benTypeVal,User,format);	
			
			List<String[]> ApprovalData = improvementProjSheetKaiDao.getImprovementProjSheetkaiexlReportApproval(User,flid,benTypeVal,kaizId);
						
			List<String[]>  getkznHDScanTbl = kznTlMstDao.getkznHDScanTbl(kaizId, commonFilter);
			
			List<String[]> teamdata =improvementProjSheetKaiDao.getteamdata(flid,kaizId);
			
			List<String[]> activitydata =improvementProjSheetKaiDao.getactivitypillar(kaizId);
			
			Workbook wb = ( new KaizenExcelTemplate(dbActionTemplate  )).fillValues(kaizenData,ApprovalData,getkznHDScanTbl,format,path,kaizId,imagePath,workFlow,teamdata,activitydata);
			
			CommonMessage.debugMsg("Service implto be gae........."+wb);
			return wb;
			
			   
		}

		@Override
		public Workbook improvementSmryReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat) throws Exception {
			return this.improvementProjSheetKaiDao.improvementSmryReportExportExcel(commonFilter,colModel,rptFormat);
		}		
}
