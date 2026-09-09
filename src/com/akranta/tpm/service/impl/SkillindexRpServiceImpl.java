package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlSkillReviewpointmstDao;
import com.akranta.tpm.dao.EntTlSkillindexassessmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.EntTlSkillReviewpointmstDaoImpl;
import com.akranta.tpm.dao.impl.EntTlSkillindexassessmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillReviewpointdet;
import com.akranta.tpm.model.EntTlSkillReviewpointmst;
import com.akranta.tpm.model.EntTlSkillindexassessdtl;
import com.akranta.tpm.model.EntTlSkillindexassessmst;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.PcsTlEnablelosscapture;
import com.akranta.tpm.service.SkillindexRpService;
import com.akranta.tpm.service.api.SkillIndexServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class SkillindexRpServiceImpl implements SkillindexRpService
{

	EntTlSkillReviewpointmstDao entTlSkillReviewpointmstDao;
	EntTlSkillindexassessmstDao entTlSkillindexassessmstDao;
	private CommonFilterDao commonFilterDao;
	
	private SkillIndexServiceApi serviceApi; 
	private Validations validations;
	public SkillindexRpServiceImpl(DBActionTemplate dbActionTemplate) {
		entTlSkillReviewpointmstDao = new EntTlSkillReviewpointmstDaoImpl(dbActionTemplate);
		entTlSkillindexassessmstDao = new EntTlSkillindexassessmstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		// genTlMomatendanceDao = new
		// GenTlMomattendanceDaoImpl(dbActionTemplate);
		validations = new Validations();

	}
	
	public void SkillindexRpServiceImplJwt(String JwtToken){
    	try{
    		entTlSkillReviewpointmstDao.EntTlSkillReviewpointmstDaoImplJwt(JwtToken);
    		entTlSkillindexassessmstDao.entTlSkillindexassessmstDaoImplJwt(JwtToken);
    		serviceApi = new SkillIndexServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}}
	
	
	

	public List<String[]> getRpDetail(CommonFilter commonFilter,String mstkeyid) {
		return this.entTlSkillReviewpointmstDao.getRpDetail(commonFilter,mstkeyid);
		
	}

	@Override
	public EntTlSkillReviewpointmst create(EntTlSkillReviewpointmst newentTlSkillReviewpointmst,EntTlSkillReviewpointmst existEntTlSkillReviewpointmst,EntTlSkillReviewpointdet newEntTlSkillReviewpointdet) throws Exception, IllegalArgumentException,  IOException {
		       CommonMessage.debugMsg("Create Block:::");
			try {
				CommonMessage.debugMsg("Inside Create");
				String validationsFor = "create";
				String xml = "SkillIndexRp";
				CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
				CommonMessage.debugMsg("After Fill Values.............."+ newentTlSkillReviewpointmst.getSirmFlid());
				validations.validate(newentTlSkillReviewpointmst, xml, validationsFor);
				fillValuesMaster(newentTlSkillReviewpointmst, existEntTlSkillReviewpointmst);
				newEntTlSkillReviewpointdet=fillValuesEntTldtl (newentTlSkillReviewpointmst, existEntTlSkillReviewpointmst, newEntTlSkillReviewpointdet);	 
				CommonMessage.debugMsg("fillvalue fill after");
				CommonMessage.debugMsg("After Fill Values");
				return entTlSkillReviewpointmstDao.create(newentTlSkillReviewpointmst,newEntTlSkillReviewpointdet);
			} catch (ValidationExceptions e) {
				throw new ValidationExceptions(e.getMessage());
			}

	}



	private EntTlSkillReviewpointmst fillValuesMaster(EntTlSkillReviewpointmst newentTlSkillReviewpointmst,EntTlSkillReviewpointmst existEntTlSkillReviewpointmst) {
		CommonMessage.debugMsg("In Side fill values::::::;");
		newentTlSkillReviewpointmst.setSirmActive("Y");

		String dateTime = CommonFunctions.dateTimeNow();

		newentTlSkillReviewpointmst.setSirmCreatedon(dateTime);

		newentTlSkillReviewpointmst.setSirmModifiedon(dateTime);
		if (newentTlSkillReviewpointmst.getSirmRoleKeyid() == null)
			newentTlSkillReviewpointmst.setSirmRoleKeyid("{}");

		if (newentTlSkillReviewpointmst.getSirmTempid() == null)
			newentTlSkillReviewpointmst.setSirmTempid("{}");

		if (newentTlSkillReviewpointmst.getSirmFlid() == null)
			newentTlSkillReviewpointmst.setSirmFlid("{}");
	
		if (newentTlSkillReviewpointmst.getSirmTempfield1() == null)
			newentTlSkillReviewpointmst.setSirmTempfield1("-");
		if (newentTlSkillReviewpointmst.getSirmTempfield2() == null)
			newentTlSkillReviewpointmst.setSirmTempfield2("-");
		if (newentTlSkillReviewpointmst.getSirmTempfield3() == null)
			newentTlSkillReviewpointmst.setSirmTempfield3("-");
		if (newentTlSkillReviewpointmst.getSirmTempfield4() == null)
			newentTlSkillReviewpointmst.setSirmTempfield4("-");
		if (newentTlSkillReviewpointmst.getSirmTempfield5() == null)
			newentTlSkillReviewpointmst.setSirmTempfield5("-");
		if (newentTlSkillReviewpointmst.getSirmTempfield6() == null)
			newentTlSkillReviewpointmst.setSirmTempfield6("-");
		if (newentTlSkillReviewpointmst.getSirmTempfield7() == null)
			newentTlSkillReviewpointmst.setSirmTempfield7("-");
	
		//newentTlSkillReviewpointmst.setReviewPointDetails(fillValuesEntTldtl(newentTlSkillReviewpointmst,existEntTlSkillReviewpointmst));	
		return newentTlSkillReviewpointmst;
	}

	

	private EntTlSkillReviewpointdet fillValuesEntTldtl(EntTlSkillReviewpointmst newentTlSkillReviewpointmst,EntTlSkillReviewpointmst existEntTlSkillReviewpointmst,EntTlSkillReviewpointdet entTlReviewPpointdet) {
			CommonMessage.debugMsg("Detail 1");
			String dateTime = CommonFunctions.dateTimeNow();
			//EntTlSkillReviewpointdet entTlSkillReviewpointdet = newentTlSkillReviewpointmst.getReviewPointDetails();
	
		
		entTlReviewPpointdet.setSirdCreatedon(dateTime);
		entTlReviewPpointdet.setSirdModifiedon(dateTime);
			CommonMessage.debugMsg(" for loop Deatils fill values::::::  3");
			entTlReviewPpointdet.setSirdActive("Y");
			entTlReviewPpointdet.setSirdCreatedby(newentTlSkillReviewpointmst.getSirmCreatedby());
			CommonMessage.debugMsg(" for loop Deatils fill values::::::  4");

			CommonMessage.debugMsg(" for loop Deatils fill values::::::  5");

			if (entTlReviewPpointdet.getSirdSirmKeyid() == null)
				entTlReviewPpointdet.setSirdSirmKeyid("{}");

			if (entTlReviewPpointdet.getSirdReviewno() == null)
				entTlReviewPpointdet.setSirdReviewno("{}");

			if (entTlReviewPpointdet.getSirdReviewpoint() == null)
				entTlReviewPpointdet.setSirdReviewpoint("{}");
			
			if (entTlReviewPpointdet.getSirdReviewtype() == null)
				entTlReviewPpointdet.setSirdReviewtype("M");
			if (entTlReviewPpointdet.getSirdSpokKeyid() == null)
				entTlReviewPpointdet.setSirdSpokKeyid("-");
			
			if (entTlReviewPpointdet.getSirdSubreviewno() == null)
				entTlReviewPpointdet.setSirdSubreviewno("-");
			if (entTlReviewPpointdet.getSirdTempfield4() == null)
				entTlReviewPpointdet.setSirdTempfield4("-");
			if (entTlReviewPpointdet.getSirdTempfield5() == null)
				entTlReviewPpointdet.setSirdTempfield5("-");
			if (entTlReviewPpointdet.getSirdTempfield6() == null)
				entTlReviewPpointdet.setSirdTempfield6("-");
			if (entTlReviewPpointdet.getSirdTempfield7() == null)
				entTlReviewPpointdet.setSirdTempfield7("-");

			
		
		CommonMessage.debugMsg("End Of  fillValues genTlMomdtl ");

		return entTlReviewPpointdet;
	}

	@Override
	public EntTlSkillReviewpointmst update(EntTlSkillReviewpointmst newentTlSkillReviewpointmst,EntTlSkillReviewpointmst existEntTlSkillReviewpointmst,EntTlSkillReviewpointdet newEntTlSkillReviewpointdet) throws Exception {
		try {
			CommonMessage.debugMsg("Inside Create");
			String validationsFor = "create";
			String xml = "SkillIndexRp";
			CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
			CommonMessage.debugMsg("After Fill Values.............."+ newentTlSkillReviewpointmst.getSirmFlid());
			validations.validate(newentTlSkillReviewpointmst, xml, validationsFor);
			fillValuesMaster(newentTlSkillReviewpointmst, existEntTlSkillReviewpointmst);
			newEntTlSkillReviewpointdet=fillValuesEntTldtl (newentTlSkillReviewpointmst, existEntTlSkillReviewpointmst, newEntTlSkillReviewpointdet);	 
			CommonMessage.debugMsg("fillvalue fill after");
			CommonMessage.debugMsg("After Fill Values");
			return entTlSkillReviewpointmstDao.update(newentTlSkillReviewpointmst,newEntTlSkillReviewpointdet);
		} catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}

	}

	@Override
	public EntTlSkillReviewpointmst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		return this.entTlSkillReviewpointmstDao.select(mstkeyid);
	}

	@Override
	public void DeleteRplist(String keyid) throws BusinessApplicationExceptions, Exception {
		this.entTlSkillReviewpointmstDao.DeleteRplist(keyid);
		
	}

	@Override
	public EntTlSkillReviewpointmst delete(EntTlSkillReviewpointmst newEntTlSkillReviewpointmst) throws Exception
	{
		return this.entTlSkillReviewpointmstDao.delete(newEntTlSkillReviewpointmst);
	}

	@Override
	public List<String[]> getSIMainGrid(CommonFilter commonFilter, String reportName) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlSkillReviewpointmstDao.getSIMainGrid(commonFilter, reportName);
	}

	@Override
	public EntTlSkillReviewpointmst createmst(EntTlSkillReviewpointmst newentTlSkillReviewpointmst,EntTlSkillReviewpointmst existEntTlSkillReviewpointmst) throws Exception {
		CommonMessage.debugMsg("Inside Create");
		String validationsFor = "create";
		String xml = "SkillIndexRp";
		CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
		CommonMessage.debugMsg("After Fill Values.............."+ newentTlSkillReviewpointmst.getSirmFlid());
		validations.validate(newentTlSkillReviewpointmst, xml, validationsFor);
		fillValuesMaster(newentTlSkillReviewpointmst, existEntTlSkillReviewpointmst);
		return entTlSkillReviewpointmstDao.createmst(newentTlSkillReviewpointmst);
	

	}

	@Override
	public EntTlSkillReviewpointmst updatemst(EntTlSkillReviewpointmst newentTlSkillReviewpointmst,EntTlSkillReviewpointmst existEntTlSkillReviewpointmst) throws Exception {
		// TODO Auto-generated method stub
			CommonMessage.debugMsg("Inside Create");
			String validationsFor = "create";
			String xml = "SkillIndexRp";
			CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
			CommonMessage.debugMsg("After Fill Values.............."+ newentTlSkillReviewpointmst.getSirmFlid());
			validations.validate(newentTlSkillReviewpointmst, xml, validationsFor);
			fillValuesMaster(newentTlSkillReviewpointmst, existEntTlSkillReviewpointmst);
			return entTlSkillReviewpointmstDao.updatemst(newentTlSkillReviewpointmst);
	}


	@Override
	public List<String[]> getSkillAssessmentReport(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return entTlSkillReviewpointmstDao.getSkillAssessmentReport(commonFilter);
	}

	@Override
	public Workbook SkillIndexExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return this.entTlSkillReviewpointmstDao.SkillIndexExcel(commonFilter, colmodel,format);
	}
	
	public String getRecallReivewPoints(String flid, String roleId) throws Exception  {
		return this.entTlSkillReviewpointmstDao.getRecallReivewPoints(flid, roleId);
	}
	
	public List<ComboBox> getEmpTypeCombo(CommonFilter commonFilter) throws Exception { 
	
		//ComboFilter comboFilter = new ComboFilter();
		ComboFilter  comboFilter = commonFilter.getEmp();
		comboFilter.setCodeField("ETPM_CODE");
		comboFilter.setIdField("ETPM_KEYID");
		comboFilter.setNameField("ETPM_NAME");
		
		comboFilter.setTableName("GEN_TL_EMPTYPE_MST");	
		return commonFilterDao.fillComboValues(comboFilter);		
	
	}

	public List<String[]> getEmpList(CommonFilter commonfilter) throws Exception 
	{
		String flid = commonfilter.getFlid();
		String uniqPosid = commonfilter.getEmmLinkKeyId();
		String reviewDate = commonfilter.getStartDate();
		
		CommonMessage.debugMsg("flid "+flid+" uniqPosid "+uniqPosid+" reviewDate "+reviewDate);
		//return entTlSkillindexassessmstDao.getEmpList(commonfilter);
		
		return serviceApi.getEmpList(flid, uniqPosid, reviewDate);
		
	}
	/*
	 * public EntTlSkillindexassessmst
	 * createSkillAssement(List<EntTlSkillindexassessmst>
	 * lstEntTlSkillindexassessmst, List<EntTlSkillindexassessdtl>
	 * lstEntTlSkillindexassessdtl, String userId ) throws Exception,
	 * ValidationExceptions {
	 * 
	 * 
	 * String validationsFor = "create"; String xml = "SkillIndexRp";
	 * CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
	 * validations.validate(lstEntTlSkillindexassessmst.get(0), xml,
	 * validationsFor);
	 * 
	 * lstEntTlSkillindexassessmst = fillValuesMaster(lstEntTlSkillindexassessmst,
	 * userId); lstEntTlSkillindexassessdtl =
	 * fillValuesDetails(lstEntTlSkillindexassessdtl, userId);
	 * 
	 * EntTlSkillindexassessmst master = lstEntTlSkillindexassessmst.get(0);
	 * 
	 * //Create / update return serviceApi.saveSkillAssessment(master,
	 * lstEntTlSkillindexassessdtl); //return
	 * entTlSkillindexassessmstDao.createAssement(lstEntTlSkillindexassessmst,
	 * lstEntTlSkillindexassessdtl); }
	 */
	
	public EntTlSkillindexassessmst createSkillAssement(List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmst,
			List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl, String userId) throws Exception, ValidationExceptions {
	

		String validationsFor = "create";
		String xml = "SkillIndexRp";
		System.out.println("Validation XML Name:::::::::" + xml);
		validations.validate(lstEntTlSkillindexassessmst.get(0), xml, validationsFor);
		
		lstEntTlSkillindexassessmst = fillValuesMaster(lstEntTlSkillindexassessmst, userId);//Removed SIAM_KEYID
		lstEntTlSkillindexassessdtl = fillValuesDetails(lstEntTlSkillindexassessdtl, userId,lstEntTlSkillindexassessmst.get(0).getSiamReviewdate());
		
		EntTlSkillindexassessmst master = lstEntTlSkillindexassessmst.get(0);
		
		//Create / update
		return serviceApi.saveSkillAssessment(master, lstEntTlSkillindexassessdtl);
		//return 
			}
	
	public List<EntTlSkillindexassessmst> fillValuesMaster(List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmst, String userId) { 

		System.out.println("Inside fillValuesMaster");
		
		List<EntTlSkillindexassessmst> newLstEntTlSkillindexassessmst = new ArrayList<EntTlSkillindexassessmst>(); 
				
		for( EntTlSkillindexassessmst entTlSklassetMstData : lstEntTlSkillindexassessmst)
		{	
			
//			if(UIUtils.isValidKeyId(newSiamKeyId)) 
//			{
//				entTlSklassetMstData.setSiamKeyid(newSiamKeyId);//-COMMENTED
//			}
			

			String dateTime = CommonFunctions.pg_dateTimeNow();
			
			String reviewDate = entTlSklassetMstData.getSiamReviewdate();
			entTlSklassetMstData.setSiamReviewdate(CommonFunctions.pg_getDateTimeFromDate(reviewDate));
			
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
	
			/*
			 * public List<EntTlSkillindexassessmst>
			 * fillValuesMaster(List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmst,
			 * String userId) {
			 * 
			 * CommonMessage.debugMsg("Inside fillValuesMaster");
			 * 
			 * List<EntTlSkillindexassessmst> newLstEntTlSkillindexassessmst = new
			 * ArrayList<EntTlSkillindexassessmst>();
			 * 
			 * for( EntTlSkillindexassessmst entTlSklassetMstData :
			 * lstEntTlSkillindexassessmst) {
			 * 
			 * String dateTime = CommonFunctions.pg_dateTimeNow();
			 * 
			 * String reviewDate = entTlSklassetMstData.getSiamReviewdate();
			 * entTlSklassetMstData.setSiamReviewdate(CommonFunctions.pg_getDateTimeFromDate
			 * (reviewDate));
			 * 
			 * entTlSklassetMstData.setSiamCreatedby(userId);
			 * entTlSklassetMstData.setSiamCreatedon(dateTime);
			 * entTlSklassetMstData.setSiamModifiedon(dateTime);
			 * 
			 * entTlSklassetMstData.setSiamActive("Y");
			 * entTlSklassetMstData.setSiamTempfiled1("-");
			 * entTlSklassetMstData.setSiamTempfiled2("-");
			 * entTlSklassetMstData.setSiamTempfiled3("-");
			 * entTlSklassetMstData.setSiamTempfiled4("-");
			 * entTlSklassetMstData.setSiamTempfiled5("-");
			 * 
			 * newLstEntTlSkillindexassessmst.add(entTlSklassetMstData);
			 * 
			 * } return newLstEntTlSkillindexassessmst; }
			 * 
			 * 
			 * public List<EntTlSkillindexassessdtl>
			 * fillValuesDetails(List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl,
			 * String userId) {
			 * 
			 * CommonMessage.debugMsg("Inside fillValuesMaster");
			 * 
			 * List<EntTlSkillindexassessdtl> newLstEntTlSkillindexassessdtl = new
			 * ArrayList<EntTlSkillindexassessdtl>();
			 * 
			 * for( EntTlSkillindexassessdtl entTlSklassetDtlData :
			 * lstEntTlSkillindexassessdtl) {
			 * 
			 * String dateTime = CommonFunctions.pg_dateTimeNow();
			 * 
			 * entTlSklassetDtlData.setSiadCreatedby(userId);
			 * entTlSklassetDtlData.setSiadCreatedon(dateTime);
			 * entTlSklassetDtlData.setSiadModifiedon(dateTime);
			 * 
			 * if (! UIUtils.isValidKeyId( entTlSklassetDtlData.getSiadScore()))
			 * entTlSklassetDtlData.setSiadScore("0");
			 * 
			 * entTlSklassetDtlData.setSiadActive("Y");
			 * //entTlSklassetDtlData.setSiadTempfiled1("-");
			 * entTlSklassetDtlData.setSiadTotal("0");
			 * entTlSklassetDtlData.setSiadTempfiled3("-");
			 * entTlSklassetDtlData.setSiadTempfiled4("-");
			 * entTlSklassetDtlData.setSiadTempfiled5("-");
			 * 
			 * newLstEntTlSkillindexassessdtl.add(entTlSklassetDtlData);
			 * 
			 * } return newLstEntTlSkillindexassessdtl; }
			 */
	
	public List<EntTlSkillindexassessmst> fillValuesMaster(List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmst, String userId,String newSiamKeyId) { 

		System.out.println("Inside fillValuesMaster");
		
		List<EntTlSkillindexassessmst> newLstEntTlSkillindexassessmst = new ArrayList<EntTlSkillindexassessmst>(); 
				
		for( EntTlSkillindexassessmst entTlSklassetMstData : lstEntTlSkillindexassessmst)
		{	
			
			if(UIUtils.isValidKeyId(newSiamKeyId)) 
			{
				entTlSklassetMstData.setSiamKeyid(newSiamKeyId);
			}
			

			String dateTime = CommonFunctions.pg_dateTimeNow();
			
			String reviewDate = entTlSklassetMstData.getSiamReviewdate();
			entTlSklassetMstData.setSiamReviewdate(CommonFunctions.pg_getDateTimeFromDate(reviewDate));
			
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
	
public List<EntTlSkillindexassessdtl> fillValuesDetails(List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl, String userId,String siadReviewDate) { 
		
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
			//entTlSklassetDtlData.setSiadTempfiled3("-");
			//entTlSklassetDtlData.setSiadTempfiled4("-");
			
			entTlSklassetDtlData.setSiadReviewdate(siadReviewDate);;
			entTlSklassetDtlData.setSiadTempfiled5("-");
			entTlSklassetDtlData.setSiadReviewhalf(getHalfYear(siadReviewDate));

			newLstEntTlSkillindexassessdtl.add(entTlSklassetDtlData);
			
		}
		return newLstEntTlSkillindexassessdtl;
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

	

	@Override
	public Workbook getskillIndexExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return this.entTlSkillReviewpointmstDao.getskillIndexExcel(commonFilter, colmodel,format);
	}
	
	@Override
	public List<String[]> getEmpListFunction(CommonFilter commonfilter, GridParams gridParams) throws Exception {
		return entTlSkillindexassessmstDao.getEmpListFunction(commonfilter, gridParams);
	}



}
