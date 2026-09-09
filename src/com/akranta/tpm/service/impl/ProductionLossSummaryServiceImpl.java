package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

//import com.akranta.tpm.dao.DefectMatrixRptDao;
import com.akranta.tpm.dao.ProductionLossSummaryDao;
//import com.akranta.tpm.dao.impl.DefectMatrixDaoImpl;
import com.akranta.tpm.dao.impl.ProductionLossSummaryDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.ProductionLossSummaryService;

public class ProductionLossSummaryServiceImpl implements ProductionLossSummaryService{
	private ProductionLossSummaryDao productionLossSummaryDao;
	public ProductionLossSummaryServiceImpl(DBActionTemplate dbActionTemplate)
	{
		productionLossSummaryDao = new ProductionLossSummaryDaoImpl(dbActionTemplate);
	
	}
	
	public List<String[]> getProductionLossSummaryRpt(CommonFilter commonFilter)	throws Exception {
		return this.productionLossSummaryDao.getProductionLossSummaryRpt(commonFilter);
	}

	
	public Workbook getProdLossSmryExcel(CommonFilter commonFilter,JSONObject colModel, String format) throws Exception {
		return this.productionLossSummaryDao.getProdLossSmryExcel(commonFilter,colModel,format);
	}

}
