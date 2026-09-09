package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.KpiTlActualKkBean;
import com.akranta.tpm.bean.KpiTlKpiremarksBean;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.KpiTlActualKkDao;
import com.akranta.tpm.dao.KpiTlKpiremarksDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.KpiTlActualKkDaoImpl;
import com.akranta.tpm.dao.impl.KpiTlKpiremarksDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.KPIDeviationITCExcelTemplate;
import com.akranta.tpm.exportreport.TrainingCalendarNEWITCExcelTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KpiTlActualKk;
import com.akranta.tpm.model.KpiTlKpiremarks;
import com.akranta.tpm.service.KpiTlActualKkService;

import com.akranta.tpm.service.api.KpiTlActualandIndicatorServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

/**
 * Servlet implementation class QtmTlCustcomplaintmstServiceImpl
 */
public class KpiTlActualKkServiceImpl  implements KpiTlActualKkService {
	private static final long serialVersionUID = 1L;       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private KpiTlActualandIndicatorServiceApi kpiTlActualandIndicatorServiceApi;
	
	private CommonFilterDao commonFilterDao;
	private KpiTlActualKkDao KpiTlActualKkDao;
	private KpiTlKpiremarksDao KpiTlKpiremarksDao;
	private Validations validations;
	private DBActionTemplate dbActiontemplate;
    public KpiTlActualKkServiceImpl(DBActionTemplate dbActionTemplate){
    	this.dbActiontemplate=dbActiontemplate;
        commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
        KpiTlActualKkDao = new KpiTlActualKkDaoImpl(dbActionTemplate);
        KpiTlKpiremarksDao = new KpiTlKpiremarksDaoImpl(dbActionTemplate);
        validations = new Validations();
      //  dbActiontemplate=new DBActionTemplate();
        // TODO Auto-generated constructor stub
    }

	public void KpiTlActualKkServiceImplJwt(String JwtToken){
    	try{
    		KpiTlActualKkDao.KpiTlActualKkDaoImplJwt(JwtToken);
    		KpiTlKpiremarksDao.KpiTlKpiremarksDaoImplJwt(JwtToken);
    		kpiTlActualandIndicatorServiceApi = new KpiTlActualandIndicatorServiceApi(JwtToken);
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
    
   
	
	public List<KpiTlActualKk>  create(List<KpiTlActualKk>  newKpiTlActualKk,List<KpiTlActualKk>  oldKpiTlActualKk, 
			KpiTlActualKkBean kpiTlActualKkBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception {
		try 
		{
			/*String validationsFor;		
			validationsFor="create";
			validations.validate(newKpiTlActualKk,"KpiTlActualKk",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		*/
			FillValues(newKpiTlActualKk,oldKpiTlActualKk,kpiTlActualKkBean);			
			//return KpiTlActualKkDao.create(newKpiTlActualKk);
			return kpiTlActualandIndicatorServiceApi.createordelete(newKpiTlActualKk);
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("VALID e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
	}

	public List<KpiTlKpiremarks> createRemarks(List<KpiTlKpiremarks> newKpiTlKpiremarks,List<KpiTlKpiremarks> existKpiTlKpiremarks,
			KpiTlKpiremarksBean kpiTlKpiremarksBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception {
		
		FillValuesRemarks(newKpiTlKpiremarks,existKpiTlKpiremarks,kpiTlKpiremarksBean);			
		//return KpiTlKpiremarksDao.create(newKpiTlKpiremarks);
		return kpiTlActualandIndicatorServiceApi.createordeleteremarks(newKpiTlKpiremarks);
	}

	public List<KpiTlActualKk>  update(List<KpiTlActualKk>  newKpiTlActualKk,List<KpiTlActualKk>  oldKpiTlActualKk, 
			KpiTlActualKkBean KpiTlActualKkBean) throws ValidationExceptions,BusinessApplicationExceptions, Exception {
		//CommonMessage.debugMsg("Inside the ServiceImpl update");
		//String validationsFor="update";
		//validations.validate(newKpiTlActualKk,"DockAuditCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		FillValues(newKpiTlActualKk,oldKpiTlActualKk,KpiTlActualKkBean);		
		//CommonMessage.debugMsg("After Fill Values");
		return KpiTlActualKkDao.update(newKpiTlActualKk);	
	}	


	@Override
	public List<KpiTlActualKk>  delete(List<KpiTlActualKk>  newKpiTlActualKk)throws Exception {
		// TODO Auto-generated method stub
		return this.KpiTlActualKkDao.delete(newKpiTlActualKk);
	}
	
	@Override
	public KpiTlActualKk select(KpiTlActualKk KpiTlActualKk) throws Exception {	
		return this.KpiTlActualKkDao.select(KpiTlActualKk);
		//return this.kpiTlActualandIndicatorServiceApi.select(KpiTlActualKk);
	}
	
	public KpiTlKpiremarks selectRemarks(KpiTlKpiremarks kpiTlKpiremarks)throws Exception {
		return this.KpiTlKpiremarksDao.select(kpiTlKpiremarks);
		//return this.kpiTlActualandIndicatorServiceApi.selectReamrk(kpiTlKpiremarks);
	}
	
	@Override
	public List<KpiTlActualKk>  selectList(KpiTlActualKk newKpiTlActualKk)throws Exception {
		// TODO Auto-generated method stub
		return this.KpiTlActualKkDao.selectList(newKpiTlActualKk);
	}
	
	@Override
	public String getPillarKeyId(String pillarCode)throws Exception {
		// TODO Auto-generated method stub
		//return this.KpiTlActualKkDao.getPillarKeyId(pillarCode);
		return this.kpiTlActualandIndicatorServiceApi.getPillarKeyId(pillarCode);
	}
	
	@Override
	public List<ComboBox> getCostAreaComboList()throws Exception {
		ComboFilter keyInd = new ComboFilter();		
		keyInd.setIdField("COAR_KEYID");		
		keyInd.setNameField("COAR_NAME");
		keyInd.setTableName(TableNames.TBL_KPI_TL_COSTAREAMST);		
		return commonFilterDao.fillComboValues(keyInd);
	}
	
	@Override
	public List<ComboBox> getParentComboList()throws Exception {
		ComboFilter keyInd = new ComboFilter();		
		keyInd.setIdField("KINK_KEYID");		
		keyInd.setNameField("KINK_INDICATORNAME");
		keyInd.setTableName(TableNames.TBL_KPI_TL_INDICATOR);		
		return commonFilterDao.fillComboValues(keyInd);
	}
	
	public List<String[]> selectSchedule(KpiTlActualKk KpiTlActualKk)throws Exception{
		return this.KpiTlActualKkDao.selectSchedule(KpiTlActualKk);
	}
	
	@Override
	public List<String[]> getCostOfQualitygridData(KpiTlActualKk newKpiTlActualKk, GridParams gridParams,CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		String month=null;		
		List<String[]> dateList=new ArrayList<String[]>();
		//month=this.KpiTlActualKkDao.getStartMonth();	
		month=this.kpiTlActualandIndicatorServiceApi.getEntProgStartMonth();
		if (CommonFunctions.isValidKeyId(month) && !CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukMonthyear())) 
			newKpiTlActualKk.setKaukMonthyear(month+"-" + newKpiTlActualKk.getKaukCalendaryear());	
		CommonMessage.debugMsg(" 2 getKaukMonthyear  :"+newKpiTlActualKk.getKaukMonthyear());
			dateList=this.KpiTlActualKkDao.getCostOfQualitygridData(newKpiTlActualKk,gridParams,commonFilter);	
		return dateList;
	}
	
	@Override
	public List<String[]> getDatesList(KpiTlActualKk newKpiTlActualKk)throws Exception{
		// TODO Auto-generated method stub
		String month=null;		
		List<String[]> dateList=new ArrayList<String[]>();		
			dateList=this.KpiTlActualKkDao.getDatesList(newKpiTlActualKk);	
		return dateList;
	}
	
	private List<KpiTlActualKk> FillValues(List<KpiTlActualKk> newKpiTlActualKk,
			List<KpiTlActualKk> oldKpiTlActualKk,KpiTlActualKkBean KpiTlActualKkBean) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ValidationExceptions {
		// TODO Auto-generated method stub
		String validationsFor;	
		 KpiTlActualKk kpiTlActualKk=new KpiTlActualKk();
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		if (newKpiTlActualKk!= null && newKpiTlActualKk.size() > 0) 
		{
			CommonMessage.debugMsg("iNSIDE fOR");
			for(int i=0;i<=newKpiTlActualKk.size()-1;i++)
			{	
				//kpiTlActualKk.setKaukKinkKeyid(newKpiTlActualKk.get(i).getKaukKinkKeyid());
				//kpiTlActualKk.setKaukMonth(newKpiTlActualKk.get(i).getKaukMonth());
				//validationsFor="create";
				//validations.validate(kpiTlActualKk,"KpiTlActualKk",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
				
				CommonMessage.debugMsg("getKaukKeyid:" + newKpiTlActualKk.get(i).getKaukKeyid());
				CommonMessage.debugMsg("getmonthyear:" + newKpiTlActualKk.get(i).getKaukMonthyear());
				
				if(!CommonFunctions.isValidKeyId(newKpiTlActualKk.get(i).getKaukKeyid())){
					newKpiTlActualKk.get(i).setKaukCreatedon(dateTime);
				}				
				newKpiTlActualKk.get(i).setKaukModifiedon(dateTime);
				
				if(newKpiTlActualKk.get(i).getKaukActive() == null)
					newKpiTlActualKk.get(i).setKaukActive("Y");
						
				if(newKpiTlActualKk.get(i).getKaukIndicatorid() == null)
					newKpiTlActualKk.get(i).setKaukIndicatorid("{}");
				
				if(newKpiTlActualKk.get(i).getKaukDeptid() == null)
					newKpiTlActualKk.get(i).setKaukDeptid("{}");
				if(newKpiTlActualKk.get(i).getKaukDepttype() == null)
					newKpiTlActualKk.get(i).setKaukDepttype("{}");
				
			/*	if(newKpiTlActualKk.get(i).getKaukFactoryid() == null)
					newKpiTlActualKk.get(i).setKaukFactoryid("{}");
				
				if(newKpiTlActualKk.get(i).getKaukSectionid() == null)
					newKpiTlActualKk.get(i).setKaukSectionid("{}");
				
				if(newKpiTlActualKk.get(i).getKaukCellid()== null)
					newKpiTlActualKk.get(i).setKaukCellid("{}");*/
				
				if(newKpiTlActualKk.get(i).getKaukPillarid() == null)
					newKpiTlActualKk.get(i).setKaukPillarid("{}");
				
				if(newKpiTlActualKk.get(i).getKaukCalendaryear() == null)
					newKpiTlActualKk.get(i).setKaukCalendaryear("0");
				
//				String monthyear = newKpiTlActualKk.get(i).getKaukMonthyear();
//				newKpiTlActualKk.get(i).setKaukMonthyear(CommonFunctions.pg_dateTimeNow());
				if(newKpiTlActualKk.get(i).getKaukMonthyear() == null) {
					newKpiTlActualKk.get(i).setKaukMonthyear(dateTime);
				}else {
			        newKpiTlActualKk.get(i).setKaukMonthyear(CommonFunctions.pg_getDateTimeFromDate(newKpiTlActualKk.get(i).getKaukMonthyear()));
			    }
				
//				if (dateTime != null && dateTime.length() > 3) {
//			        newKpiTlActualKk.get(i).setKaukMonthyear(dateTime);
//			    } else {
//			        newKpiTlActualKk.get(i).setKaukMonthyear(dateTime);
//			    }
				
				if(newKpiTlActualKk.get(i).getKaukExcellencevalue() == null)
					newKpiTlActualKk.get(i).setKaukExcellencevalue("0");
				
				if(newKpiTlActualKk.get(i).getKaukBenchmarkvalue() == null)
					newKpiTlActualKk.get(i).setKaukBenchmarkvalue("0");
				
				if(newKpiTlActualKk.get(i).getKaukValue() == null)
					newKpiTlActualKk.get(i).setKaukValue("0");
				
				if(newKpiTlActualKk.get(i).getKaukIsactual() == null)
					newKpiTlActualKk.get(i).setKaukIsactual("Y");
								
				if(newKpiTlActualKk.get(i).getKaukFreqtype() == null)
					newKpiTlActualKk.get(i).setKaukFreqtype("M");
				
				if(newKpiTlActualKk.get(i).getKaukStatus() == null)
					newKpiTlActualKk.get(i).setKaukStatus("P");
				
				if(newKpiTlActualKk.get(i).getKaukTempfield1() == null)
					newKpiTlActualKk.get(i).setKaukTempfield1("-");
				
				if(newKpiTlActualKk.get(i).getKaukTempfield2() == null)
					newKpiTlActualKk.get(i).setKaukTempfield2("-");
				
				if(newKpiTlActualKk.get(i).getKaukTempfield3() == null)
					newKpiTlActualKk.get(i).setKaukTempfield3("-");
				
				if(newKpiTlActualKk.get(i).getKaukCreatedon() == null)
					newKpiTlActualKk.get(i).setKaukCreatedon(dateTime);
				
				if(newKpiTlActualKk.get(i).getKaukCreatedby() == null)
					newKpiTlActualKk.get(i).setKaukCreatedby("{}");
			}
		}
		return newKpiTlActualKk;
	
	}

///Remarks
	
	private List<KpiTlKpiremarks> FillValuesRemarks(List<KpiTlKpiremarks> newKpiTlKpiremarks,
			List<KpiTlKpiremarks> oldKpiTlKpiremarks,KpiTlKpiremarksBean kpiTlKpiremarksBean) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ValidationExceptions {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.pg_dateTimeNow();		
		if (newKpiTlKpiremarks!= null && newKpiTlKpiremarks.size() > 0) 
		{
			CommonMessage.debugMsg("iNSIDE fOR");
			for(int i=0;i<=newKpiTlKpiremarks.size()-1;i++)
			{	
				CommonMessage.debugMsg("getKprmKeyid:" + newKpiTlKpiremarks.get(i).getKprmKeyid());
				if(!CommonFunctions.isValidKeyId(newKpiTlKpiremarks.get(i).getKprmKeyid())){
					newKpiTlKpiremarks.get(i).setKprmCreatedon(dateTime);
				}				
				newKpiTlKpiremarks.get(i).setKprmModifiedon(dateTime);
				
				if(newKpiTlKpiremarks.get(i).getKprmActive() == null)
					newKpiTlKpiremarks.get(i).setKprmActive("Y");
				
				String date = newKpiTlKpiremarks.get(i).getKprmDate();
				newKpiTlKpiremarks.get(i).setKprmDate(CommonFunctions.pg_getDateTimeFromDate(date));
				if(newKpiTlKpiremarks.get(i).getKprmDate() == null)
					newKpiTlKpiremarks.get(i).setKprmDate(dateTime);
				
				if(newKpiTlKpiremarks.get(i).getKprmTempfield1() == null)
					newKpiTlKpiremarks.get(i).setKprmTempfield1("-");
				if(newKpiTlKpiremarks.get(i).getKprmTempfield2() == null)
					newKpiTlKpiremarks.get(i).setKprmTempfield2("-");
				if(newKpiTlKpiremarks.get(i).getKprmTempfield3() == null)
					newKpiTlKpiremarks.get(i).setKprmTempfield3("-");
				if(newKpiTlKpiremarks.get(i).getKprmTempfield4() == null)
					newKpiTlKpiremarks.get(i).setKprmTempfield4("-");
				if(newKpiTlKpiremarks.get(i).getKprmTempfield5() == null)
					newKpiTlKpiremarks.get(i).setKprmTempfield5("-");
				
			}
		}
		return newKpiTlKpiremarks;
	
	}


	@Override
	public List<String[]> getListOfIndicators(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return KpiTlActualKkDao.getListOfIndicators( commonFilter);
	}


	@Override
	public List<String[]> getWeeksList(KpiTlActualKk newKpiTlActualKk)
			throws Exception {
		// TODO Auto-generated method stub
		return KpiTlActualKkDao.getWeeksList(newKpiTlActualKk);
	}

	@Override
	public List<String[]> getKPIRemarks(CommonFilter comonFilter) throws Exception {

		return KpiTlActualKkDao.getKPIRemarks(comonFilter);
	}


	@Override
	public Workbook getActualKKListExl(KpiTlActualKk newKpiTlActualKk,
			GridParams gridParams, CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		String month=this.KpiTlActualKkDao.getStartMonth();		
		if (CommonFunctions.isValidKeyId(month) && !CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukMonthyear())) 
			newKpiTlActualKk.setKaukMonthyear(month+"-" + newKpiTlActualKk.getKaukCalendaryear());	
		return KpiTlActualKkDao.getActualKKListExl(newKpiTlActualKk,
				gridParams, commonFilter,tblJSONObj,  format);
	}
	@Override
	public String getFnlnDescription(String flid) throws Exception {
		return KpiTlActualKkDao.getFnlnDescription(flid);
	}



	@Override
	public List<String[]> getCostOfQualitygridDataNewDM(KpiTlActualKk newKpiTlActualKk, GridParams gridParams,
			CommonFilter commonFilter) throws Exception {
		//String month=null;		
		List<String[]> dateList=new ArrayList<String[]>();
	//	month=this.KpiTlActualKkDao.getStartMonth();		
		if (!CommonFunctions.isValidKeyId(newKpiTlActualKk.getKaukMonthyear())) 
			newKpiTlActualKk.setKaukMonthyear( newKpiTlActualKk.getKaukCalendaryear());	
		CommonMessage.debugMsg(" 2 getKaukMonthyear  :"+newKpiTlActualKk.getKaukMonthyear());
			dateList=this.KpiTlActualKkDao.getCostOfQualitygridDataNewDM(newKpiTlActualKk,gridParams,commonFilter);	
		return dateList;
	}



	@Override
	public List<String[]> getIndicatorRemarks(String indicatorid)
			throws Exception {
		// TODO Auto-generated method stub
		return KpiTlActualKkDao.getIndicatorRemarks(indicatorid);
		
	}
	public List<String[]> getKPIRemarksReport(CommonFilter comonFilter) throws Exception {

		return KpiTlActualKkDao.getKPIRemarksReport(comonFilter);
	}


	@Override
	public List<String[]> getKPIkeyid(String kinkid, String flid, String year, String freq)
			throws Exception {
		// TODO Auto-generated method stub
		return KpiTlActualKkDao.getKPIkeyid(kinkid,flid,year,freq);
	}


	@Override
	public String getKPICalendaryr(String year) throws Exception {
		// TODO Auto-generated method stub
		return KpiTlActualKkDao.getKPIyear(year);
	}
    public Workbook KPIDeviationExcelView(String flid,String year,String CurrDate,String CurrMonthYear,String frequency,String format,String path)throws Exception{
    	List<String[]>  KPIDeviationData=KpiTlActualKkDao.KPIDeviationData(flid,year,CurrDate,CurrMonthYear,frequency);
    	Workbook wb=(new KPIDeviationITCExcelTemplate(dbActiontemplate)).fillValues(KPIDeviationData,path,flid);
        return wb;	
    }
    public List<String[]> getKPIEmpMailIds(String flid,String location,String rolename) throws Exception{
    	return KpiTlActualKkDao.getKPIEmpMailIds(flid, location,rolename);
    }
    public String getKPIDeviationCount(String flid,String year,String CurrDate,String CurrMonthYear ,String frequency) throws Exception{
    	//return KpiTlActualKkDao.getKPIDeviationCount(flid,year,CurrDate,CurrMonthYear,frequency);
    	return kpiTlActualandIndicatorServiceApi.getKPIDeviationCount(flid, year, CurrDate, CurrMonthYear, frequency);
    }
    @Override
    public List<String[]> getElementId(String loginflid, String loginlevel,
    		String loginElementid, String empId) throws Exception {
    	// TODO Auto-generated method stub
    	//return KpiTlActualKkDao.getElementId(loginflid,loginlevel,loginElementid,empId);
    	return kpiTlActualandIndicatorServiceApi.getElementId(loginflid, loginlevel, loginElementid, empId);
    	
    }

    @Override
	public List<String[]> getrpakpidata(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return KpiTlActualKkDao.gerpakpidata(commonFilter);
	}


	

}
