package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.OplSummaryDao;
import com.akranta.tpm.dao.impl.OplSummaryDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.OplSummaryService;

public class OplSummaryServiceImpl implements OplSummaryService
{

private OplSummaryDao oplSummary;

public OplSummaryServiceImpl(DBActionTemplate dbActionTemplate)
{
	oplSummary=new OplSummaryDaoImpl(dbActionTemplate);
}	
	public List<String[]> getAllOPLSummaryService(CommonFilter commonFilter)
			throws Exception {	
		
		return this.oplSummary.getOplSummaryDao(commonFilter);
		
	}
	
	public Workbook getOPLSummaryExcel(CommonFilter commonFilter,JSONObject colModel,String format) throws Exception 
	{
		return this.oplSummary.getOPLSummaryExcel(commonFilter,colModel,format);
	}

}