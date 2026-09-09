
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsRptDao;
import com.akranta.tpm.dao.ProductionLossDao;

import com.akranta.tpm.dao.impl.PcsRptDaoImpl;
import com.akranta.tpm.dao.impl.ProductionLossDaoImpl;

import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.service.PcsRptService;
import com.akranta.tpm.service.ProductionLossService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;



public class ProductionLossServiceImpl implements ProductionLossService {

private ProductionLossDao productionLossDao;
	
	public ProductionLossServiceImpl(DBActionTemplate dbActionTemplate)
	{
		productionLossDao =  new ProductionLossDaoImpl(dbActionTemplate);
		 //dbActionTemplate.getDataSource().getConnectionCacheName();
	}
	public void ProductionLossServiceImplJwt(String JwtToken){
	    try{
	    	productionLossDao.ProductionLossDaoImplJwt(JwtToken);   // dao side
	        // (Optional) if you want service-level direct access
	       //  oplServiceApi = new OplTlMstServiceApi(JwtToken); 
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
		CommonMessage.debugMsg( " size " + graphData.size() );
		CommonMessage.debugMsg( " ");
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
	@Override
	public List<String[]> getAllLossTimeDrill(CommonFilter commonFilter)
						throws Exception {
			return productionLossDao.getAllLossTimeDrill(commonFilter);
	}
	@Override
	public List<String[]> getAllLossTimeNewChart(CommonFilter chrtCommonFilter,String rowid)
						throws Exception {
			return productionLossDao.getAllLossTimeNewChart(chrtCommonFilter,rowid);
	}
}