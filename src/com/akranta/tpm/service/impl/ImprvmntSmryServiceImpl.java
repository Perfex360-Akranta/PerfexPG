package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.ImprvmntSmryDao;
import com.akranta.tpm.dao.impl.ImprvmntSmryDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.ImprvmntSmryService;

public class ImprvmntSmryServiceImpl implements ImprvmntSmryService {
	
	private ImprvmntSmryDao imprvmntSmryDao;
		
		public ImprvmntSmryServiceImpl(DBActionTemplate dbActionTemplate)
		{
			imprvmntSmryDao =  new ImprvmntSmryDaoImpl(dbActionTemplate);
		}
		public void ImprvmntSmryServiceImplJwt(String JwtToken){
	    	try{
	    		imprvmntSmryDao.ImprvmntSmryDaoImplJwt(JwtToken);
	    //		serviceApi = new ApplicationMaintananceServiceApi(JwtToken);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}}
		
		
		public List<String []> getAllImprovement(CommonFilter commonFilter) throws Exception
		{
			return this.imprvmntSmryDao.getAllImprovmntSmry(commonFilter);
		}

		@Override
		public List<String[]> getAllImprovementSubGrp(CommonFilter commonFilter)throws Exception {
			
			return this.imprvmntSmryDao.getAllImprovementSubGrp(commonFilter);
		}

		@Override
		public List<String[]> getAllImprovementSubGrpLoss(CommonFilter commonFilter) throws Exception {
			return this.imprvmntSmryDao.getAllImprovementSubGrpLoss(commonFilter);
		}

		@Override
		public List<String[]> getAllImprovementSubGrpPiller(CommonFilter commonFilter) throws Exception {
			return this.imprvmntSmryDao.getAllImprovementSubGrpPiller(commonFilter);
		}

		@Override
		public List<String[]> getAllImprovementSubGrpEqp(CommonFilter commonFilter) throws Exception {
			return this.imprvmntSmryDao.getAllImprovementSubGrpEqp(commonFilter);
		}

		@Override
		public Workbook improvementSmryReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat) throws Exception {
			return this.imprvmntSmryDao.improvementSmryReportExportExcel(commonFilter,colModel,rptFormat);
		}

		@Override
		public Workbook improvementSmrySubReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
				throws Exception {
			return this.imprvmntSmryDao.improvementSmrySubReportExportExcel(commonFilter,colModel,rptFormat);
		}

		@Override
		public Workbook improvementSmrySubLossReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
				throws Exception {
			return this.imprvmntSmryDao.improvementSmrySubLossReportExportExcel(commonFilter,colModel,rptFormat);
		}

		@Override
		public Workbook improvementSmrySubPillerReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
				throws Exception {
			return this.imprvmntSmryDao.improvementSmrySubPillerReportExportExcel(commonFilter,colModel,rptFormat);
		}

		@Override
		public Workbook improvementSmrySubEquipReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
				throws Exception {
			return this.imprvmntSmryDao.improvementSmrySubEquipReportExportExcel(commonFilter,colModel,rptFormat);
		}

		@Override
		public List<String[]> getpiechart(CommonFilter chrtCommonFilter) throws Exception {
			// TODO Auto-generated method stub
			return this.imprvmntSmryDao.getpiechart(chrtCommonFilter);
		}

}
