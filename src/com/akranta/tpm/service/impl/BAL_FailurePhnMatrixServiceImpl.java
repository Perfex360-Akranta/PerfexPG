package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_FailurePhnMatrixDao;
import com.akranta.tpm.dao.impl.BAL_FailurePhnMatrixDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_FailurePhnMatrixService;

import com.akranta.tpm.service.api.FunctionCallApi;

public class BAL_FailurePhnMatrixServiceImpl implements BAL_FailurePhnMatrixService {
private BAL_FailurePhnMatrixDao failurePhnMatrixDao;
FunctionCallApi fnCallApi;
	
	
	public BAL_FailurePhnMatrixServiceImpl(DBActionTemplate dbActionTemplate)
	{
		failurePhnMatrixDao = new BAL_FailurePhnMatrixDaoImpl(dbActionTemplate);
	
	}
	public void BAL_FailurePhnMatrixServiceImplJwt(String JwtToken){
		try{
			failurePhnMatrixDao.BAL_FailurePhnMatrixDaoImplJwt(JwtToken);
			//cltiapi = new CliTlStandardsServiceApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    // TODO Auto-generated constructor stub
	}
	public List<String[]> getAllFailurePhnMatrix(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		return this.failurePhnMatrixDao.getAllFailurePhnMatrix( commonFilter);
	}

	
	@Override
	public Workbook ppmatrixExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.failurePhnMatrixDao.getppmatrix(commonFilter,colmodel,rptFormat);
	}
}
