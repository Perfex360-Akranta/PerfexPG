package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

	import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_PmstandardDao;
import com.akranta.tpm.dao.impl.BAL_PmstandardDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_PmStandardServices;
import com.akranta.tpm.service.api.FunctionCallApi;


		
		public class BAL_PmStandardServiceImpl implements BAL_PmStandardServices {

			private BAL_PmstandardDao pmstandardDao;
			FunctionCallApi fnCallApi;

				
				public BAL_PmStandardServiceImpl(DBActionTemplate dbActionTemplate)
				{
					pmstandardDao =  new BAL_PmstandardDaoImpl(dbActionTemplate);
				}
				
				public void BAL_PmStandardServiceImplJwt(String JwtToken){
				    try{
				    	pmstandardDao.BAL_PmstandardDaoImplJwt(JwtToken);   // dao side
				    } catch(Exception e){
				        e.printStackTrace();
				    }
				}

			
				@Override
				public List<String[]> getAllPmStandard(CommonFilter commonFilter) throws Exception {
					// TODO Auto-generated method stub
					return this.pmstandardDao.getPmstandard(commonFilter);
				}


				@Override
				public Workbook PMStandardReportExportExcel(CommonFilter commonFilter, JSONObject colModel,String rptFormat) throws Exception {
					return this.pmstandardDao.PMStandardReportExportExcel(commonFilter,colModel,rptFormat);
				}
				public Workbook PMStandardReportExportToExcel(CommonFilter commonFilter, JSONObject colModel,String rptFormat) throws Exception {
					return this.pmstandardDao.PMStandardReportExportToExcel(commonFilter,colModel,rptFormat);
				}

				@Override
				public List<String[]> getPmStandardRptTemp(
						CommonFilter commonFilter) throws Exception {
					// TODO Auto-generated method stub
					return this.pmstandardDao.getPmstandardRptTemp(commonFilter);
				}


				@Override
				public List<String[]> getPmStandardAssemblyRptTemp(
						CommonFilter commonFilter) throws Exception {
					// TODO Auto-generated method stub
					return this.pmstandardDao.getPmstandardAssemblyRptTemp(commonFilter);
				}


	}


