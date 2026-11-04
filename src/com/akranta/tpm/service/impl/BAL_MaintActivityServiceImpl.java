package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;


import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.dao.BAL_MaintActivityDao;
import com.akranta.tpm.dao.impl.BAL_MaintActivityDaoImpl;
import com.akranta.tpm.exportreport.EmployeeWiseActivityExcelTemplate;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.BAL_MaintActivity;

import com.akranta.tpm.service.BAL_MaintActivityService;


public class BAL_MaintActivityServiceImpl  implements BAL_MaintActivityService {
	
private BAL_MaintActivityDao maintActivityDao;
private DBActionTemplate dbActionTemplate;
	
	public BAL_MaintActivityServiceImpl(DBActionTemplate dbActionTemplate)
	{
		maintActivityDao = new BAL_MaintActivityDaoImpl(dbActionTemplate);
		this.dbActionTemplate =dbActionTemplate;
	}
	
	public void BAL_PmStandardServiceImplJwt(String JwtToken){
	    try{
	    	maintActivityDao.BAL_MaintActivityDaoImplJwt(JwtToken);   // dao side
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	public List<String[]> getAllMaintActivity(CommonFilter commonFilter) throws Exception
	{
		return this.maintActivityDao.getAllMaintActivity(commonFilter);
	}

	@Override
	public Workbook activityWiseRptExportExcel(CommonFilter commonFilter ,JSONObject tableModel ,String format) throws Exception {
		// TODO Auto-generated method stub
		return this.maintActivityDao.activityWiseRptExportExcel(commonFilter, tableModel,format);
	}

	//@Override
	/*public Workbook ActivityWiseExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.maintActivityDao.ActivityWiseExportExcel(commonFilter,colModel,rptFormat);
	}*/
	@Override
	public Workbook EmployeeWiseActivityRptExportExcel(CommonFilter commonFilter,JSONObject tableModel ,String format) throws Exception{
		return this.maintActivityDao. EmployeeWiseActivityRptExportExcel(commonFilter, tableModel,format);
	}
	@Override
	public List<String[]> getAllEmployeeActivity(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.maintActivityDao.getAllEmployeeActivity(commonFilter);
	}

	@Override
	public Workbook getEmployeeWiseActivityExlView(String empKeyId, String format,
			String path, String imagePath) throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, List<String[]>> empWActivityData = maintActivityDao.getEmployeeWiseActivityExlView(empKeyId,format);
		Workbook wb = ( new EmployeeWiseActivityExcelTemplate(dbActionTemplate)).fillValues(empWActivityData,format,path,empKeyId);
		
		return wb;
	}

	@Override
	public List<String[]> getActivityWiserpt(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.maintActivityDao.getActivityWiserpt(commonFilter);
	}
	
	

}
