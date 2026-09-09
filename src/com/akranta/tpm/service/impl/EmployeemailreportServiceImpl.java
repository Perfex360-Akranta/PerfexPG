package com.akranta.tpm.service.impl;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlEmployeemstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlEmployeemstDaoImpl;
import com.akranta.tpm.dao.sql.GenTlEmployeemstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EmpmailreportModel;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.service.EmployeemailreportService;
//import com.akranta.tpm.upload.Validations;
import com.google.gson.JsonObject;
//import com.sun.corba.se.spi.orbutil.threadpool.Work;
//import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
public class EmployeemailreportServiceImpl implements EmployeemailreportService{

	private GenTlEmployeemstDao genempmstdao;
    private  CommonFilterDao commonfilterdao;
    //private Validations valid;
public 	EmployeemailreportServiceImpl(DBActionTemplate dbactionTemplate){
   	
	genempmstdao=new GenTlEmployeemstDaoImpl(dbactionTemplate);
	commonfilterdao=new CommonFilterDaoImpl(dbactionTemplate);
	//valid=new Validations();
}

public List<String[]> empreport(CommonFilter commonFilter,GridParams gridparams)throws Exception{
	   
	return genempmstdao.empreport(commonFilter,gridparams);
}

public Workbook getExcelreport(CommonFilter commonFilter,JSONObject jsonobj,String format)throws Exception{
	   
	return genempmstdao.getExcelreport(commonFilter, jsonobj, format);
}

public List<EmpmailreportModel> updateEmail(List<EmpmailreportModel> empMailEnableList) throws Exception{
	
	String validationsFor="";
	validationsFor="update";
	fillValues(empMailEnableList);
	return genempmstdao.updateEmail(empMailEnableList);
	}

private List<EmpmailreportModel> fillValues(List<EmpmailreportModel> empMailEnableList) {
	
	for(int i=0;i<empMailEnableList.size();i++)
	{
		if(UIUtils.isValidEmail(empMailEnableList.get(i).getEmpenablemail())){ 
			empMailEnableList.get(i).setEmpmailid("-");
		}	
	}	
 	return empMailEnableList;	
}


}
