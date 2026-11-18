
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsRptDao;
import com.akranta.tpm.dao.BAL_ProductionLossDao;

import com.akranta.tpm.dao.impl.PcsRptDaoImpl;
import com.akranta.tpm.dao.impl.BAL_ProductionLossDaoImpl;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;


import com.akranta.tpm.service.PcsRptService;
import com.akranta.tpm.service.BAL_ProductionLossService;
import com.akranta.tpm.utils.CommonFunctions;



public class BAL_ProductionLossServiceImpl implements BAL_ProductionLossService {

private BAL_ProductionLossDao productionLossDao;
FunctionCallApi fnCallApi;

	
	public BAL_ProductionLossServiceImpl(DBActionTemplate dbActionTemplate)
	{
		productionLossDao =  new BAL_ProductionLossDaoImpl(dbActionTemplate);
		 //dbActionTemplate.getDataSource().getConnectionCacheName();
	}
	public void BAL_ProductionLossServiceImplJwt(String JwtToken){
	    try{
	    	productionLossDao.BAL_ProductionLossDaoImplJwt(JwtToken);   // dao side
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	public List<String []> getAllproductionLoss(CommonFilter commonFilter) throws Exception
	{
		List<String []> graphData = productionLossDao.getAllproductionLoss(commonFilter);
		 return graphData;
		//return this.productionLossDao.getAllproductionLoss(commonFilter);
	}


	
	
	public List<String[]> getAllproductionLossSubGrid(CommonFilter commonFilter)	throws Exception {
		return this.productionLossDao.getAllproductionLossSubGrid(commonFilter);
	}

	@Override
	public Workbook lossExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.productionLossDao.getproductionLossExl(commonFilter,colmodel,rptFormat);
	}

	@Override
	public List<String[]> getAllproductionLossGraph(CommonFilter chrtCommonFilter) throws Exception {
		List<String []> graphData = productionLossDao.getAllproductionLoss(chrtCommonFilter);
		CommonFunctions.debugMsg( " size " + graphData.size() );
		CommonFunctions.debugMsg( " ");
		 UIUtils.sortColDataDesc(graphData, 6,2, 15);
		 return graphData;
	}

	@Override
	public List<String[]> getAllproductionLossforparatograph(
			CommonFilter chrtCommonFilter, String selectmonth) throws Exception {
		List<String []> graphData = productionLossDao.getAllproductionLossforparatograph(chrtCommonFilter,selectmonth);
		 return graphData;
	}

	@Override
	public List<String[]> getdescorderlosses(String descsql) throws Exception {
		// TODO Auto-generated method stub
		return productionLossDao.getdescorderlosses(descsql);
	}

	@Override
	public List<String[]> getAllproductionLossNewChart(CommonFilter chrtCommonFilter,String selmonth) throws Exception {
		// TODO Auto-generated method stub
		return productionLossDao.getAllproductionLossNewChart(chrtCommonFilter,selmonth);
	}

	@Override
	public List<String[]> getAllproductionLossDrill(CommonFilter commonFilter)
			throws Exception {
		return productionLossDao.getAllproductionLossDrill(commonFilter);
	}

	@Override
	public List<String[]> getLossByFunctionalLocation(CommonFilter commonFilter)
			throws Exception {
		
		return productionLossDao.getLossByFunctionalLocation(commonFilter);
	}

	@Override
	public Workbook getLossExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		
		return productionLossDao.getLossExportExcel(commonFilter,tblJSONObj,format);
	}

	@Override
	public Workbook getLossAnalysisExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		
		return productionLossDao.getLossAnalysisExportExcel(commonFilter, tblJSONObj, format);
	}
	
}