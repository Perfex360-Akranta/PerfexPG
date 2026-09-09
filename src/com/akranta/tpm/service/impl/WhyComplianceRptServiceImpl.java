
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.dao.WhyComplianceRptDao;
import com.akranta.tpm.dao.impl.WhyComplianceRptDaoImpl;
import com.akranta.tpm.service.WhyComplianceRptService;
import com.akranta.tpm.utils.CommonMessage;
public class WhyComplianceRptServiceImpl implements WhyComplianceRptService{

	private WhyComplianceRptDao whyComplianceRptDao;
	
	
	public WhyComplianceRptServiceImpl(DBActionTemplate dbActionTemplate)
	{
		whyComplianceRptDao = new WhyComplianceRptDaoImpl(dbActionTemplate);
	
	}
	
	@Override
	public List<String[]> getAllyydrill(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		return this.whyComplianceRptDao.getAllyydrill( commonFilter);
	}

	@Override
	public List<String[]> getAllyycelldrill(String colIndex, CommonFilter commonFilter  )	throws Exception {
		// TODO Auto-generated method stub
		String code = null;
		if( colIndex.equalsIgnoreCase("3") )
		{
			CommonMessage.debugMsg("colIndex.equalsIgnoreCase " + colIndex.equalsIgnoreCase("2"));
			code = "BDO";
		}
		else if( colIndex.equalsIgnoreCase("4") )
			code = "BDC";
		else if( colIndex.equalsIgnoreCase("5") )
			code = "BDI";
		else if( colIndex.equalsIgnoreCase("6") )
			code = "IAP";
		else if( colIndex.equalsIgnoreCase("7") )
			code = "YYD";
		else if( colIndex.equalsIgnoreCase("8") )
			code = "YYP";
		
		return this.whyComplianceRptDao.getAllyycelldrill( code,commonFilter);
	}

	@Override
	public List<String[]> getAllmonth(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		return this.whyComplianceRptDao.getAllmonth( commonFilter);
	}

	@Override
	public Workbook whycompExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.whyComplianceRptDao.getWhycompmonthExl( commonFilter,colmodel,rptFormat);
	}

	@Override
	public Workbook whydetailExportExcel(CommonFilter commonFilter,	JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.whyComplianceRptDao.getWhydetailExl( commonFilter,colmodel,rptFormat);
	}

	@Override
	public Workbook whymonthExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.whyComplianceRptDao.getWhymonthExl( commonFilter,colmodel,rptFormat);
	}
	
}
