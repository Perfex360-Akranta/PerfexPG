package com.akranta.tpm.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.SkillIndexGridEntryDao;
import com.akranta.tpm.dao.SkillIndexReportDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.SkillIndexGridEntryDaoImpl;
import com.akranta.tpm.dao.impl.SkillIndexReportDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillindexassessdtl;
import com.akranta.tpm.model.EntTlSkillindexassessmst;
import com.akranta.tpm.service.SkillIndexGridEntryService;
import com.akranta.tpm.service.api.SkillIndexServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

import net.sf.json.JSONObject;

public class SkillIndexGridEntryServiceImpl implements SkillIndexGridEntryService {
private SkillIndexGridEntryDao skillIndexGridEntryDao;
 SkillIndexServiceApi serviceApi;
	private Validations validations ;
	public SkillIndexGridEntryServiceImpl(DBActionTemplate dbActionTemplate)
	{
		skillIndexGridEntryDao =  new SkillIndexGridEntryDaoImpl(dbActionTemplate);
		validations=new Validations();
	}	
	
	public void SkillIndexGridEntryServiceImplJwt(String JwtToken){
    	try{
    		skillIndexGridEntryDao.SkillIndexGridEntryDaoImplJwt(JwtToken);
    		serviceApi = new SkillIndexServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}}
	
	@Override
	public List<String[]> getTopicTask(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return skillIndexGridEntryDao.getTopicTask(commonFilter);
	}
	
	public EntTlSkillindexassessmst createSkillAssement(List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmst,
			List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl, String userId ) throws Exception, ValidationExceptions {
	

		String validationsFor = "create";
		String xml = "";//"SkillIndexRp";
		CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
		validations.validate(lstEntTlSkillindexassessmst.get(0), xml, validationsFor);
		
		lstEntTlSkillindexassessmst = fillValuesMaster(lstEntTlSkillindexassessmst, userId);
		lstEntTlSkillindexassessdtl = fillValuesDetails(lstEntTlSkillindexassessdtl, userId);
		
		//Create / update 
		return skillIndexGridEntryDao.createAssement(lstEntTlSkillindexassessmst, lstEntTlSkillindexassessdtl);
	}
	
	
	 	 
	  public List<EntTlSkillindexassessmst> fillValuesMaster(List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmst, String userId) { 

			System.out.println("Inside fillValuesMaster");
			
			List<EntTlSkillindexassessmst> newLstEntTlSkillindexassessmst = new ArrayList<EntTlSkillindexassessmst>(); 
					
			for( EntTlSkillindexassessmst entTlSklassetMstData : lstEntTlSkillindexassessmst)
			{	
				System.out.println("Key Id "+entTlSklassetMstData.getSiamKeyid()+" print");
				
//				if(UIUtils.isValidKeyId(newSiamKeyId)) 
//				{
//					entTlSklassetMstData.setSiamKeyid(newSiamKeyId);
//				}
//				
				String reviewDate = entTlSklassetMstData.getSiamReviewdate();
				entTlSklassetMstData.setSiamReviewdate(CommonFunctions.pg_getDateTimeFromDate(reviewDate));

				String dateTime = CommonFunctions.pg_dateTimeNow();
				
				entTlSklassetMstData.setSiamCreatedby(userId);
				entTlSklassetMstData.setSiamCreatedon(dateTime);
				entTlSklassetMstData.setSiamModifiedon(dateTime);
				
				entTlSklassetMstData.setSiamActive("Y");
				entTlSklassetMstData.setSiamTempfiled1("-");
				entTlSklassetMstData.setSiamTempfiled2("-");
				entTlSklassetMstData.setSiamTempfiled3("-");
				entTlSklassetMstData.setSiamTempfiled4("-");
				entTlSklassetMstData.setSiamTempfiled5("-");
				
				newLstEntTlSkillindexassessmst.add(entTlSklassetMstData);
				
			}
			return newLstEntTlSkillindexassessmst;
		}
	
public List<EntTlSkillindexassessdtl> fillValuesDetails(List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl, String userId,String siadReviewdate) { 
		
		System.out.println("Inside fillValuesMaster");
		
		List<EntTlSkillindexassessdtl> newLstEntTlSkillindexassessdtl = new ArrayList<EntTlSkillindexassessdtl>(); 
				
		for( EntTlSkillindexassessdtl entTlSklassetDtlData : lstEntTlSkillindexassessdtl)
		{	

			String dateTime = CommonFunctions.pg_dateTimeNow();
			
			entTlSklassetDtlData.setSiadCreatedby(userId);
			entTlSklassetDtlData.setSiadCreatedon(dateTime);
			entTlSklassetDtlData.setSiadModifiedon(dateTime);
			
			if (! UIUtils.isValidKeyId( entTlSklassetDtlData.getSiadScore()))
					entTlSklassetDtlData.setSiadScore("0");
			
			entTlSklassetDtlData.setSiadActive("Y");
			//entTlSklassetDtlData.setSiadTempfiled1("-");
			entTlSklassetDtlData.setSiadTotal("0");
			entTlSklassetDtlData.setSiadReviewdate(siadReviewdate);
			entTlSklassetDtlData.setSiadReviewhalf(getHalfYear(siadReviewdate));
			//entTlSklassetDtlData.setSiadTempfiled3("-");
			//entTlSklassetDtlData.setSiadTempfiled4("-");
			entTlSklassetDtlData.setSiadTempfiled5("-");

			newLstEntTlSkillindexassessdtl.add(entTlSklassetDtlData);
			
		}
		return newLstEntTlSkillindexassessdtl;
	}

public static String getHalfYear(String inputDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    LocalDateTime date = LocalDateTime.parse(inputDate, formatter);

    int month = date.getMonthValue();
    int year = date.getYear();

    // April to September => H1 (same year)
    if (month >= 4 && month <= 9) {
        return year + "-H1";
    }
    // October to December => H2 (same year)
    else if (month >= 10) {
        return year + "-H2";
    }
    // January to March => H2 (previous year)
    else {
        return (year - 1) + "-H2";
    }
}

/*
 * public static String getHalfYear(String inputDate) { // Input format:
 * 2026-05-01T00:00:00 DateTimeFormatter formatter =
 * DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
 * 
 * LocalDateTime date = LocalDateTime.parse(inputDate, formatter);
 * 
 * int month = date.getMonthValue(); int year = date.getYear();
 * 
 * // April to September => H1 // October to March => H2 if (month >= 4 && month
 * <= 9) { return year + "-H1"; } else { return year + "-H2"; } }
 */

	public List<String[]> getEmpList(CommonFilter commonfilter) throws Exception 
	{
		String flid = commonfilter.getFlid();
		String uniqPosid = commonfilter.getEmmLinkKeyId();
		String reviewDate = commonfilter.getStartDate();
		
		CommonMessage.debugMsg("flid "+flid+" uniqPosid "+uniqPosid+" reviewDate "+reviewDate);
		//return skillIndexGridEntryDao.getEmpList(commonfilter);
		return serviceApi.getEmpListMultiple(flid, uniqPosid, reviewDate);
	}

	
	  public List<EntTlSkillindexassessdtl>
	  fillValuesDetails(List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl,
	  String userId) {
	  
	  CommonMessage.debugMsg("Inside fillValuesMaster");
	  
	  List<EntTlSkillindexassessdtl> newLstEntTlSkillindexassessdtl = new
	  ArrayList<EntTlSkillindexassessdtl>();
	  
	  for( EntTlSkillindexassessdtl entTlSklassetDtlData :
	  lstEntTlSkillindexassessdtl) {
	  
	  String dateTime = CommonFunctions.pg_dateTimeNow();
	  
	  entTlSklassetDtlData.setSiadCreatedby(userId);
	  entTlSklassetDtlData.setSiadCreatedon(dateTime);
	  entTlSklassetDtlData.setSiadModifiedon(dateTime);
	  
	  if (! UIUtils.isValidKeyId( entTlSklassetDtlData.getSiadScore()))
	  entTlSklassetDtlData.setSiadScore("0");
	  
	  entTlSklassetDtlData.setSiadActive("Y");
	  //entTlSklassetDtlData.setSiadTempfiled1("-");
	  entTlSklassetDtlData.setSiadTotal("0");
	  entTlSklassetDtlData.setSiadReviewdate("-");// check_Skill
	  entTlSklassetDtlData.setSiadReviewhalf("-");
	  entTlSklassetDtlData.setSiadTempfiled5("-");
	  
	  newLstEntTlSkillindexassessdtl.add(entTlSklassetDtlData);
	  
	  } return newLstEntTlSkillindexassessdtl; }
	 
	@Override
	public List<String[]> getTopicTaskModify(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return skillIndexGridEntryDao.getTopicTaskModify(commonFilter);
	}
	@Override
	public List<String[]> getSIMainGrid(CommonFilter commonFilter,
			String reportName) throws Exception {
		// TODO Auto-generated method stub
		return skillIndexGridEntryDao.getSIMainGrid(commonFilter,reportName) ;
	}

	/*
	 * @Override public EntTlSkillindexassessmst updateSkillAssement(
	 * List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmstmst,
	 * List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessmstdtl, String
	 * usrm_keyid) throws Exception { String validationsFor = "create"; String xml =
	 * "SkillIndexRp"; CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
	 * validations.validate(lstEntTlSkillindexassessmstmst.get(0), xml,
	 * validationsFor);
	 * 
	 * lstEntTlSkillindexassessmstmst =
	 * fillValuesMaster(lstEntTlSkillindexassessmstmst, usrm_keyid);
	 * lstEntTlSkillindexassessmstdtl =
	 * fillValuesDetails(lstEntTlSkillindexassessmstdtl, usrm_keyid);
	 * 
	 * //Create / update //return
	 * skillIndexGridEntryDao.updateSkillAssement(lstEntTlSkillindexassessmstmst,
	 * lstEntTlSkillindexassessmstdtl); return
	 * serviceApi.saveMultipleSkillAssessment(lstEntTlSkillindexassessmstmst,
	 * lstEntTlSkillindexassessmstdtl); }
	 */
	
	@Override
	public EntTlSkillindexassessmst updateSkillAssement(
			List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmstmst,
			List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessmstdtl,
			String usrm_keyid) throws Exception {
		String validationsFor = "create";
		String xml = "SkillIndexRp";
		System.out.println("Validation XML Name:::::::::" + xml);
		validations.validate(lstEntTlSkillindexassessmstmst.get(0), xml, validationsFor);
		
		lstEntTlSkillindexassessmstmst = fillValuesMaster(lstEntTlSkillindexassessmstmst, usrm_keyid);
		lstEntTlSkillindexassessmstdtl = fillValuesDetails(lstEntTlSkillindexassessmstdtl, usrm_keyid,lstEntTlSkillindexassessmstmst.get(0).getSiamReviewdate());
		
		//Create / update 
		//return skillIndexGridEntryDao.updateSkillAssement(lstEntTlSkillindexassessmstmst, lstEntTlSkillindexassessmstdtl);
		return serviceApi.saveMultipleSkillAssessment(lstEntTlSkillindexassessmstmst, lstEntTlSkillindexassessmstdtl);
	}
	
	@Override
	public Workbook getExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return skillIndexGridEntryDao.getExportExcel(commonFilter,tblJSONObj,format);
	}
	@Override
	public Workbook multipleSkillIndexReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		return this.skillIndexGridEntryDao.multipleSkillIndexReportExportExcel( commonFilter, tblJSONObj, format);
	}
	
	@Override
	public List<String[]> getEmpListFunction(CommonFilter commonfilter, GridParams gridParams) throws Exception {
		return skillIndexGridEntryDao.getEmpListFunction(commonfilter, gridParams);
	}

}
