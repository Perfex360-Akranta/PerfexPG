package com.akranta.tpm.service.impl;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.ImprovementVsCompletedDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.ImprovementVsCompletedDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.ImprovementVsCompletedService;
import com.akranta.tpm.service.api.ApplicationMaintananceServiceApi;




public class ImprovementVsCompletedServiceImpl implements ImprovementVsCompletedService {

	private ImprovementVsCompletedDao improvementVsCompletedDao;


	public ImprovementVsCompletedServiceImpl(DBActionTemplate dbActionTemplate)
	{
		improvementVsCompletedDao = new ImprovementVsCompletedDaoImpl(dbActionTemplate);
	}	
	public void ImprovementVsCompletedServiceImplJwt(String JwtToken){
    	try{
    		improvementVsCompletedDao.ImprovementVsCompletedDaoImplJwt(JwtToken);
    //		serviceApi = new ApplicationMaintananceServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}}
	
	public List<String[]> getAllImpVsComp(CommonFilter commonFilter)throws Exception {		
		return this.improvementVsCompletedDao.getAllImpVsComp(commonFilter);
		
	}
	public Workbook ImprovementVsCompleteExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
		return this.improvementVsCompletedDao.ImprovementVsCompleteExportExcel(commonFilter,colModel,rptFormat);
	}

	@Override
	public List<String[]> getAllIncedentImpVsComp(CommonFilter commonFilter)throws Exception {
		return this.improvementVsCompletedDao.getAllIncedentImpVsComp(commonFilter);
	}

	@Override
	public Workbook IncedentVsCompleteExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.improvementVsCompletedDao.IncedentVsCompleteExportExcel(commonFilter,colModel,rptFormat);
	}
	
	@Override
	public List<String[]> getsuggestdimple(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.improvementVsCompletedDao.getsuggestdimple(commonFilter);
	}

	@Override
	public List<String[]> getchartforimpl(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.improvementVsCompletedDao.getchartforimpl(commonFilter);
	}

	@Override
	public Workbook SuggestnVsImpl(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception {
		// TODO Auto-generated method stub
		return this.improvementVsCompletedDao.SuggestnVsImpl(commonFilter,tableModel,format);
	}
	public List<String[]> getKznImplCount(CommonFilter commonFilter)throws Exception {		
		return this.improvementVsCompletedDao.getKznImplCount(commonFilter);
	}

		public Workbook KznImplCountExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
		return this.improvementVsCompletedDao.KznImplCountExportExcel(commonFilter,colModel,rptFormat);
			}
		public List<String[]> getKznSgnCount(CommonFilter commonFilter)throws Exception {		
			return this.improvementVsCompletedDao.getKznSgnCount(commonFilter);
			}

			public Workbook KznSgnCountExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
			return this.improvementVsCompletedDao.KznSgnCountExportExcel(commonFilter,colModel,rptFormat);
				}
			public List<String[]> getAllGraphicalSumm(CommonFilter commonFilter)throws Exception {		
				return this.improvementVsCompletedDao.getAllGraphicalSumm(commonFilter);
				
			}
			public Workbook KaizenGraphicalSummExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
				return this.improvementVsCompletedDao.KaizenGraphicalSummExportExcel(commonFilter,colModel,rptFormat);
			}
			public List<String[]> getsuggchartforimpl(CommonFilter commonFilter) throws Exception {
				// TODO Auto-generated method stub
				return this.improvementVsCompletedDao.getsuggchartforimpl(commonFilter);
			}
			public List<String[]> getKaizenCululative(CommonFilter commonFilter)throws Exception{
				 
					return this.improvementVsCompletedDao.getKaizenCululative(commonFilter); 
			 }
			public Workbook KaizenCumulativeExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat) throws Exception {
			    return this.improvementVsCompletedDao.KaizenCumulativeExportExcel(commonFilter, colModel, rptFormat);
			}
			public List<String[]> getEmpDmtKaizen(CommonFilter commonFilter)throws Exception {		
				return this.improvementVsCompletedDao.getEmpDmtKaizen(commonFilter);
				
			}
			public Workbook EmpDmtWiseKaizenExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
				return this.improvementVsCompletedDao.EmpDmtWiseKaizenExportExcel(commonFilter,colModel,rptFormat);
			}		
}

