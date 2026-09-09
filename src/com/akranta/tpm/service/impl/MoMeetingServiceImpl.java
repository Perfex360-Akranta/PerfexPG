package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.MOMeetingBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlMommstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlMommstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.MOMITCExcelProductionTemplate;
import com.akranta.tpm.exportreport.MOMITCExcelTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;
import com.akranta.tpm.service.MoMeetingService;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.service.api.MomServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class MoMeetingServiceImpl implements MoMeetingService 
{
	private GenTlMommstDao genTlMommstDao;
	// private GenTlMomattendanceDao genTlMomatendanceDao;
	private Validations validations;
	private  MomServiceApi momServiceApi;
	private CommonFilterDao commonFilterDao ;
	DBActionTemplate  dbActionTemplate ;
	public MoMeetingServiceImpl(DBActionTemplate dbActionTemplate) {
		genTlMommstDao = new GenTlMommstDaoImpl(dbActionTemplate);
		// genTlMomatendanceDao = new
		// GenTlMomattendanceDaoImpl(dbActionTemplate);
		validations = new Validations();
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);	
		this.dbActionTemplate  =dbActionTemplate ;
		
		
	}
	
	public void MoMeetingServiceImplJwt(String JwtToken){
    	try{
    		genTlMommstDao.GenTlMommstDaoImplJwt(JwtToken);
    		
    		momServiceApi = new MomServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	@Override
	public String RoleBasedFlid(String originalId) {
		return momServiceApi.getFlid(originalId);
	}

	public GenTlMommst select(String keyid) throws Exception {
		//return this.genTlMommstDao.select(keyid);
		return momServiceApi.getMomById(keyid);
	}
	@Override
	public GenTlMommst selectRecall(String shift, String mstDate, String flid, String type)
			throws Exception {
		// TODO Auto-generated method stub
		return this.genTlMommstDao.selectRecall(shift,mstDate,flid,type);
	}


	@Override
	public List<String[]> selectRecalling(String shift, String mstDate,
			String flid, String type, String pillarid) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Shift Id "+shift+" mstDate "+mstDate+" flid "+flid+" type "+type+" pillarid "+pillarid);
		//return this.genTlMommstDao.selectRecalling(shift, mstDate,flid,type,pillarid);
		String date = CommonFunctions.pg_getDate(mstDate);
		return momServiceApi.selectRecalling(shift, date, flid, type, pillarid);
	}

	
	@Override
	public List<String[]> getMomGrid(CommonFilter commonFilter, String KeyId, String momdate, String shift, String pillarid)
			throws Exception {
		String flid=commonFilter.getFlid();
		String type=commonFilter.getType();
		CommonMessage.debugMsg("Mom date "+momdate);
		//return this.genTlMommstDao.getMomGrid(commonFilter, KeyId,momdate,shift,pillarid);
		return momServiceApi.getMomGrid(KeyId, shift, momdate, flid, type, pillarid);

	}

	public List<String[]> getMomeeting(CommonFilter commonFilter)
			throws Exception {
		return genTlMommstDao.getMomeeting(commonFilter);
	}

	public List<String[]> getMomeetingRvw(CommonFilter commonFilter)
	throws Exception {
        return genTlMommstDao.getMomeetingRvw(commonFilter);
    }
	
	@Override
	public GenTlMommst create(GenTlMommst genTlMommst,GenTlMommst existGenTlMommst, MOMeetingBean momeetingBean)throws BusinessApplicationExceptions,Exception {
	
      CommonMessage.debugMsg("Create Block:::");
		try {
			
			//CommonMessage.debugMsg("After Fill Values"+momeetingBean.getMomCellId()+" Checking Lenght :: "+((momeetingBean.getMomCellId()).length()));
			String validationsFor;
			if(momeetingBean.getMomCellId()==null || momeetingBean.getMomCellId()==" " || momeetingBean.getMomCellId().length()== 0){
				CommonMessage.debugMsg("Inside Create");
				validationsFor = "create";
				//validations.validate(newOplTlMst,"oplcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
				validations.validate(momeetingBean,"Momeeting",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			}
			
			validationsFor = "create";
			String xml = "Momeeting";
			validations.validate(genTlMommst, xml, validationsFor);
			fillValuesgenTlMommst(genTlMommst, existGenTlMommst);
			CommonMessage.debugMsg("fillvalue fill after");
			
			CommonMessage.debugMsg("After Fill Values"+momeetingBean.getMomCellId());
			
			//return genTlMommstDao.create(genTlMommst);
			return momServiceApi.saveMOM(genTlMommst);
		} catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}

	}

	@Override
	public GenTlMommst update(GenTlMommst genTlMommst,GenTlMommst existGenTlMommst, MOMeetingBean momeetingBean)throws Exception
	{
		// TODO Auto-generated method stub

		CommonMessage.debugMsg("In Side Update ::::::;"+ genTlMommst.getMomsCreatedby());
		CommonMessage.debugMsg("exitGenTlMommst:::" + existGenTlMommst);
		String validationsFor = "create";
		String xml = "Momeeting";
		CommonMessage.debugMsg("Inside the ServiceImpl u");
		validations.validate(genTlMommst, xml, validationsFor);
		// com.akranta.validations.tpm.validations.kaizencreation.xml - defined
			// rules for server side validations
		List<GenTlMomdtl> momdtls = genTlMommst.getMomeetingDetail();
		for (GenTlMomdtl genTlMomdtl : momdtls) {

			// validations.validate(genTlMomdtl,"employee",validationsFor);//com.akranta.validations.tpm.validations.employee.xml
			// - defined rules for server side validations
		}
		CommonMessage.debugMsg("  5555555555 After");
		fillValuesgenTlMommst(genTlMommst, existGenTlMommst);
        CommonMessage.debugMsg("update:" + genTlMommst);
		//return genTlMommstDao.update(genTlMommst);
        return momServiceApi.saveMOM(genTlMommst);

	}

	public GenTlMommst delete(GenTlMommst newGenTlMommst) throws Exception {
		CommonMessage.debugMsg("Delete Services IMPL");
		//return genTlMommstDao.delete(newGenTlMommst);
		return momServiceApi.DeleteFullMom(newGenTlMommst);
	}

	private void fillValuesgenTlMommst(GenTlMommst genTlMommst,
			GenTlMommst exitGenTlMommst) throws Exception 
	{
		CommonMessage.debugMsg("In Side fill values::::::;");
		genTlMommst.setMomsActive("Y");
		 
		if(isValidDateFormat(genTlMommst.getMomsDate())) {
		String date = CommonFunctions.pg_getDateTimeFromDate(genTlMommst.getMomsDate());
		genTlMommst.setMomsDate(date);
		}
		//String dateTime = CommonFunctions.dateTimeNow();
		String dateTime = CommonFunctions.pg_dateTimeNow();
		CommonMessage.debugMsg("date time now "+dateTime);

		genTlMommst.setMomsCreatedon(dateTime);

		genTlMommst.setMomsModifiedon(dateTime);
		String momDate = genTlMommst.getMomsDate();
		if (genTlMommst.getMomsMeetingno() == null)
			genTlMommst.setMomsMeetingno("{}");
		
		if (genTlMommst.getMomsMeetingtitle() == null){
			
			if (genTlMommst.getMomsMeetingtype().equals("D")){
				genTlMommst.setMomsMeetingtitle("DMT"+" "+momDate.substring(0,8)+dateTime.substring(11,19));//dateTime
			}
			else if (genTlMommst.getMomsMeetingtype().equals("J"))
			{
					genTlMommst.setMomsMeetingtitle("JH"+" "+momDate.substring(0,8)+dateTime.substring(11,19));
			}
			else if (genTlMommst.getMomsMeetingtype().equals("O"))
			{
					genTlMommst.setMomsMeetingtitle("OTHERS"+" "+momDate.substring(0,8)+dateTime.substring(11,19));
			}
			else if (genTlMommst.getMomsMeetingtype().equals("PD"))
			{
					genTlMommst.setMomsMeetingtitle("PRODUCTION MEETING"+" "+momDate.substring(0,8)+dateTime.substring(11,19));
			}else if (genTlMommst.getMomsMeetingtype().equals("P"))
			{
				genTlMommst.setMomsMeetingtitle("PILLAR"+" "+momDate.substring(0,8)+dateTime.substring(11,19));
		    }
			else if (genTlMommst.getMomsMeetingtype().equals("CEC"))//Added this line Mom - change-08-May
			{
				genTlMommst.setMomsMeetingtitle("CEC"+" "+momDate.substring(0,8)+dateTime.substring(11,19));
		    }
			else if (genTlMommst.getMomsMeetingtype().equals("DEC"))//Added this line Mom - change-08-May
			{
				genTlMommst.setMomsMeetingtitle("DEC"+" "+momDate.substring(0,8)+dateTime.substring(11,19));
		    }
			else
				genTlMommst.setMomsMeetingtitle("{}");
			
		}
			//CommonMessage.debugMsg(" Checking :: Meeting Type :: "+genTlMommst.getMomsMeetingtype());
			
			//action.equals("functionalLoc.mom")
			
			/*if(genTlMommst.getMomsMeetingtitle().equals(" ")){
				CommonMessage.debugMsg(" Inside Service Impl :: ");
				genTlMommst.setMomsMeetingtitle("{}");
			}*/
			
			
			/*if (genTlMommst.getMomsMeetingtype().equals("D")){
				CommonMessage.debugMsg(" Checking :: Meeting Type :: D "+genTlMommst.getMomsMeetingtype());
			    genTlMommst.setMomsMeetingtitle("DMT"+" "+dateTime);
			}
			else if (genTlMommst.getMomsMeetingtype().equals("J"))
			{
					CommonMessage.debugMsg(" Checking :: Meeting Type :: J "+genTlMommst.getMomsMeetingtype());
				    genTlMommst.setMomsMeetingtitle("JH"+" "+dateTime);
			}*/
			//}
		
			if (genTlMommst.getMomsMeetingtype() == null)
			genTlMommst.setMomsMeetingtype("{}");

		if (genTlMommst.getMomsDate() == null)
			genTlMommst.setMomsDate(genTlMommst.getMomsDate());
		if (genTlMommst.getMomsFlid() == null)

			genTlMommst.setMomsFlid("{}");
		if (genTlMommst.getMomsIsmeetinghappen() == null)
			genTlMommst.setMomsIsmeetinghappen("N");

		if (genTlMommst.getMomsRemarks() == null)
			genTlMommst.setMomsRemarks("{}");

		if (genTlMommst.getMomsSafetytalk() == null)
			genTlMommst.setMomsSafetytalk("{}");
		if (genTlMommst.getMomsShiftid() == null)
			genTlMommst.setMomsShiftid("{}");
		
		if(genTlMommst.getMomsRefdocid()==null)
		    genTlMommst.setMomsRefdocid("{}");
		
		if(genTlMommst.getMomsRefdoctype()==null)
		    genTlMommst.setMomsRefdoctype("{}");
		
		if(genTlMommst.getMomsIsMailTrig()==null)
		    genTlMommst.setMomsIsMailTrig("N");
		
		if(genTlMommst.getMomsTempField1()==null)
		    genTlMommst.setMomsTempField1("-");
		
		if(genTlMommst.getMomsTempField2()==null)
		    genTlMommst.setMomsTempField2("-");
		
		if(genTlMommst.getMomsTempField3()==null)
		    genTlMommst.setMomsTempField3("-");
		
		

		if (genTlMommst.getMomsPillarid() == null)
			genTlMommst.setMomsPillarid("{}");
		if (genTlMommst.getMomsIsmessageboard() == null)
			genTlMommst.setMomsIsmessageboard("N");
		if (genTlMommst.getMomsAgenda() == null)
			genTlMommst.setMomsAgenda("{}");
		if (genTlMommst.getMomsOthers() == null)
			genTlMommst.setMomsOthers("N");
		if (genTlMommst.getMomsPillargroup() == null)
			genTlMommst.setMomsPillargroup("{}");
		
		List<GenTlMomKpiLink> newgenTlMomKpiLinks = genTlMommst.getMomeetingKPI();
		genTlMommst.setMomeetingDetail(fillValuesgenTlMomdtl(genTlMommst,exitGenTlMommst));
		CommonMessage.debugMsg("Entry in fill values moms keyid "+genTlMommst.getMomsKeyid());
		genTlMommst.setMomeettingAttence(fillValuesgenTlMommstatt(genTlMommst,exitGenTlMommst));
		if(newgenTlMomKpiLinks!=null)
		{
			CommonMessage.debugMsg("inside kpi fillvalues");
			genTlMommst.setMomeetingKPI(fillValuesgenTlMommKpi(genTlMommst,	exitGenTlMommst));
		}
		CommonMessage.debugMsg("End Of  fillValues genTlMommst");
	}

	private List<GenTlMomKpiLink> fillValuesgenTlMommKpi(GenTlMommst genTlMommst, GenTlMommst exitGenTlMommst) {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("Kpi 1");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<GenTlMomKpiLink> newgenTlMomKpiLinks = genTlMommst.getMomeetingKPI();
		List<GenTlMomKpiLink> exitGenTlMomKpiLinks = null;
		GenTlMomKpiLink exitGenTlMomKpiLink = null;
		CommonMessage.debugMsg("Kpi 2");
		if (exitGenTlMommst != null) {
			exitGenTlMomKpiLinks = exitGenTlMommst.getMomeetingKPI();
			if (exitGenTlMomKpiLinks != null && exitGenTlMomKpiLinks.size() > 0)
				exitGenTlMomKpiLink = exitGenTlMomKpiLinks.get(0);
		}
		CommonMessage.debugMsg("KPI 3");
		List<GenTlMomKpiLink> newGenTlMomdLinksList = new ArrayList<GenTlMomKpiLink>();
		CommonMessage.debugMsg("KPI ::::: "+newgenTlMomKpiLinks.size()); 
		
		for (GenTlMomKpiLink genTlMomKpiLink : newgenTlMomKpiLinks)
		{
			CommonMessage.debugMsg(" for loop Kpi fill values::::::");
			CommonMessage.debugMsg("Detail");
			genTlMomKpiLink.setMokpCreatedon(dateTime);
			CommonMessage.debugMsg(" for loop Kpi fill values::::::  3");
			genTlMomKpiLink.setMokpModifiedon(dateTime);

			CommonMessage.debugMsg(" for loop Kpi fill values::::::  3");
			genTlMomKpiLink.setMokpActive("Y");
			genTlMomKpiLink.setMokpCreatedby(genTlMommst.getMomsCreatedby());

			CommonMessage.debugMsg(" for loop kpi fill values::::::  4");
			
			CommonMessage.debugMsg(" for loop Kpi fill values::::::  5");

			if (genTlMomKpiLink.getMokpKeyid() == null)
				genTlMomKpiLink.setMokpKeyid("{}");

			if (genTlMomKpiLink.getMokpKinkKeyid() == null)
				genTlMomKpiLink.setMokpKinkKeyid("{}");

			if (genTlMomKpiLink.getMokpMomdKeyid() == null)
				genTlMomKpiLink.setMokpMomdKeyid("{}");

			if (genTlMomKpiLink.getMokpMomsKeyid() == null)
				genTlMomKpiLink.setMokpMomsKeyid("{}");
			
			if (genTlMomKpiLink.getMokpTempfield1() == null)
				genTlMomKpiLink.setMokpTempfield1("-");

			if (genTlMomKpiLink.getMokpTempfield2() == null)
				genTlMomKpiLink.setMokpTempfield2("-");

			if (genTlMomKpiLink.getMokpTempfield3() == null)
				genTlMomKpiLink.setMokpTempfield3("-");

			if (genTlMomKpiLink.getMokpTempfield4() == null)
				genTlMomKpiLink.setMokpTempfield4("-");

			if (genTlMomKpiLink.getMokpTempfield5() == null)
				genTlMomKpiLink.setMokpTempfield5("-");

			newGenTlMomdLinksList.add(genTlMomKpiLink);
		}
		CommonMessage.debugMsg("End Of  fillValues genTlMomKpi");

		return newGenTlMomdLinksList;
	}

	private List<GenTlMomdtl> fillValuesgenTlMomdtl(GenTlMommst genTlMommst,GenTlMommst exitGenTlMommst) throws Exception 
	{
		CommonMessage.debugMsg("Detail 1");
		//String dateTime = CommonFunctions.dateTimeNow();
		
		String mstKeyId = genTlMommst.getMomsKeyid();
		//String exmstKeyId = exitGenTlMommst.getMomsKeyid();
		
		CommonMessage.debugMsg("mst "+mstKeyId+" ");
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<GenTlMomdtl> newgenTlMomdtls = genTlMommst.getMomeetingDetail();
		List<GenTlMomdtl> exitGenTlMomdtls = null;
		GenTlMomdtl exitGenTlMomdtl = null;
		CommonMessage.debugMsg("Detail 2 "+newgenTlMomdtls.size());
		if (exitGenTlMommst != null) {
			exitGenTlMomdtls = exitGenTlMommst.getMomeetingDetail();
			if (exitGenTlMomdtls != null && exitGenTlMomdtls.size() > 0)
				exitGenTlMomdtl = exitGenTlMomdtls.get(0);
		}

		List<GenTlMomdtl> newGenTlMomdtlsList = new ArrayList<GenTlMomdtl>();
		
		if(newgenTlMomdtls!=null&& newgenTlMomdtls.size()>0)
		{
			for (GenTlMomdtl genTlMomdtl : newgenTlMomdtls)
			{
				CommonMessage.debugMsg(" for loop Deatils fill values::::::");
				CommonMessage.debugMsg("Detail");
				genTlMomdtl.setMomdCreatedon(dateTime);
				CommonMessage.debugMsg(" for loop Deatils fill values::::::  3");
				genTlMomdtl.setMomdModifiedon(dateTime);
	
				CommonMessage.debugMsg(" for loop Deatils fill values::::::  3");
				genTlMomdtl.setMomdActive("Y");
				genTlMomdtl.setMomdCreatedby(genTlMommst.getMomsCreatedby());
	
				CommonMessage.debugMsg(" for loop Deatils fill values::::::  4");
	
				/*
				 * if(employeeBean.getEmpdBirthdate()==null)
				 * genTlEmployeedtl.setEmpdBirthdate(Constants.passNullDate);
				 */
				CommonMessage.debugMsg(" for loop Deatils fill values::::::  5");
				
				if(mstKeyId != null && !mstKeyId.isEmpty()) 
				{
					genTlMomdtl.setMomdMomsKeyid(mstKeyId);
					
				}
	
				if (genTlMomdtl.getMomdActionplanId() == null)
					genTlMomdtl.setMomdActionplanId("{}");
	
				if (genTlMomdtl.getMomdDiscussionDetails() == null)
					genTlMomdtl.setMomdDiscussionDetails("{}");
	
				if (genTlMomdtl.getMomdDiscussionType() == null)
					genTlMomdtl.setMomdDiscussionType("{}");
	
				if (genTlMomdtl.getMomdRemarks() == null)
					genTlMomdtl.setMomdRemarks("{}");
				
				if(genTlMomdtl.getMomdPillar()==null)
					genTlMomdtl.setMomdPillar("{}");
	
				if (genTlMomdtl.getMomdTempfield1() == null)
					genTlMomdtl.setMomdTempfield1("-");
	
				if (genTlMomdtl.getMomdTempfield2() == null)
					genTlMomdtl.setMomdTempfield2("-");
	
				if (genTlMomdtl.getMomdTempfield3() == null)
					genTlMomdtl.setMomdTempfield3("-");
	
				if (genTlMomdtl.getMomdTempfield4() == null)
					genTlMomdtl.setMomdTempfield4("-");
	
				if (genTlMomdtl.getMomdTempfield5() == null)
					genTlMomdtl.setMomdTempfield5("-");
	
				newGenTlMomdtlsList.add(genTlMomdtl);
			}
		}
		CommonMessage.debugMsg("End Of  fillValues genTlMomdtl ");

		return newGenTlMomdtlsList;

	}

	@Override
	public GenTlMomdtl selectdtl(String keyid) throws Exception {
		return this.genTlMommstDao.selectdtl(keyid);
	}

	private List<GenTlMomattendance> fillValuesgenTlMommstatt(GenTlMommst genTlMommst, GenTlMommst exitGenTlMommst)

	{
		//String dateTime = CommonFunctions.dateTimeNow();
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<GenTlMomattendance> newgenTlMomattendances = genTlMommst.getMomeetinMomattendances();
		List<GenTlMomattendance> exitGenTlMomattendances = null;
		GenTlMomattendance exitGenTlMomattendance = null;
		CommonMessage.debugMsg("In Side fill values Attt::::::;");

		if (exitGenTlMommst != null) {
			exitGenTlMomattendances = exitGenTlMommst.getMomeetinMomattendances();
			if (exitGenTlMomattendance != null
					&& exitGenTlMomattendances.size() > 0)
				exitGenTlMomattendance = exitGenTlMomattendances.get(0);
		}
		String newMstKeyId = genTlMommst.getMomsKeyid();
		//Validations validate = new Validations();
		List<GenTlMomattendance> newGenTlMomdattList = new ArrayList<GenTlMomattendance>();
		for (GenTlMomattendance genTlMomattendance : newgenTlMomattendances)

		{
			// String dateTime = CommonFunctions.dateTimeNow();
				String attKeyId = genTlMomattendance.getMomaKeyid();
				CommonMessage.debugMsg("printing att Key Id "+attKeyId);
				
		
				

			   genTlMomattendance.setMomaCreatedon(dateTime);
			
			   genTlMomattendance.setMomaCreatedby("USR0001");
			
               genTlMomattendance.setMomaModifiedon(dateTime);

			if (genTlMomattendance.getMomaDate() == null)
				genTlMomattendance.setMomaDate(genTlMommst.getMomsDate());
			if (genTlMomattendance.getMomaFlid() == null)
				genTlMomattendance.setMomaFlid("{}");
			
			if (genTlMomattendance.getMomaAttandance() == null)
				genTlMomattendance.setMomaAttandance("P");

			if (genTlMomattendance.getMomaActive() == null)
				genTlMomattendance.setMomaActive("Y");

			if (genTlMomattendance.getMomaEmployeeid() == null)
				genTlMomattendance.setMomaEmployeeid("{}");

			if (genTlMomattendance.getMomaKeyid() == null)
				genTlMomattendance.setMomaKeyid(null);
			
				if(newMstKeyId != null && !newMstKeyId.isEmpty()) 
				{
					genTlMomattendance.setMomaMomsKeyid(newMstKeyId);
					
				}
			if (genTlMomattendance.getMomaMomsKeyid() == null)
				genTlMomattendance.setMomaMomsKeyid("{}");
			
			if (genTlMomattendance.getMomaTempfield1() == null)
				genTlMomattendance.setMomaTempfield1("-");

			if (genTlMomattendance.getMomaTempfield2() == null)
				genTlMomattendance.setMomaTempfield2("-");
			if (genTlMomattendance.getMomaTempfield3() == null)
				genTlMomattendance.setMomaTempfield3("-");
			if (genTlMomattendance.getMomaTempfield4() == null)
				genTlMomattendance.setMomaTempfield4("-");
			if (genTlMomattendance.getMomaTempfield5() == null)
				genTlMomattendance.setMomaTempfield5("-");

			newGenTlMomdattList.add(genTlMomattendance);

		}
		CommonMessage.debugMsg("End Of  fillValues Attt:::::::: ");
		return newGenTlMomdattList;

	}

	public List<String[]> getMomeetingAtt(CommonFilter commonFilter,String KeyId,String location,String flid,String Momdate, String shift, String recall) throws Exception {
		return this.genTlMommstDao.getMomeetingAtt(commonFilter, KeyId,location,flid,Momdate,shift,recall);
	}

	@Override
	public GenTlMomattendance selectatt(String keyid) throws Exception {
		return this.genTlMommstDao.selectatt(keyid);
	}

	@Override
	public Workbook MomeetingExportExcel(CommonFilter commonFilter,	JSONObject colmodel, String format) throws Exception{
		return this.genTlMommstDao.MomeetingExportExcel(commonFilter, colmodel,format);
	}

	@Override
	public Workbook MomeetingRvwExportExcel(CommonFilter commonFilter,	JSONObject colmodel, String format) throws Exception{
		return this.genTlMommstDao.MomeetingRvwExportExcel(commonFilter, colmodel,format);
	}
	
	@Override
	public Workbook MomeetingMonthwiseExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlMommstDao.MomeetingMonthwiseExportExcel(commonFilter, colmodel,format);
	}

	
	@Override
	public GenTlMommst createatt(GenTlMommst newGenTlMommst,GenTlMommst existGenTlMommst) throws Exception 
	{
		try {
			CommonMessage.debugMsg("Inside Create");
			String validationsFor = "create";
			String xml = "Momeeting";
			CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
			CommonMessage.debugMsg("After Fill Values.............."+ newGenTlMommst.getMomsFlid());
			validations.validate(newGenTlMommst, xml, validationsFor);
			fillValuesgenTlMommst(newGenTlMommst, existGenTlMommst);
			CommonMessage.debugMsg("fillvalue fill after");
			CommonMessage.debugMsg("After Fill Values");
			return genTlMommstDao.createatt(newGenTlMommst);
		}  catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}
	}

	@Override
	public GenTlMommst updateatt(GenTlMommst newGenTlMommst,GenTlMommst existGenTlMommst) throws Exception 
	{

		CommonMessage.debugMsg("In Side Update ::::::;"+ newGenTlMommst.getMomsCreatedby());
    	CommonMessage.debugMsg("exitGenTlMommst:::" + existGenTlMommst);
		String validationsFor = "create";
		String xml = "Momeeting";
		CommonMessage.debugMsg("Inside the ServiceImpl u");
		validations.validate(newGenTlMommst, xml, validationsFor);
		List<GenTlMomattendance> momattendances = newGenTlMommst.getMomeetinMomattendances();
		for (GenTlMomattendance genTlMomattendance : momattendances) {

			// validations.validate(genTlMomdtl,"employee",validationsFor);//com.akranta.validations.tpm.validations.employee.xml
			// - defined rules for server side validations
		}
		CommonMessage.debugMsg("  5555555555 After");
		CommonMessage.debugMsg("Keyid in attendance update "+existGenTlMommst.getMomsKeyid());
		String newKeyId = existGenTlMommst.getMomsKeyid();
		if(newKeyId != null && !newKeyId.isEmpty()) 
		{
			newGenTlMommst.setMomsKeyid(newKeyId);
		}
		fillValuesgenTlMommst(newGenTlMommst, existGenTlMommst);
		CommonMessage.debugMsg("update:" + newGenTlMommst);
		//return genTlMommstDao.updateatt(newGenTlMommst);
		return momServiceApi.saveMOM(newGenTlMommst);

	}

	@Override
	public void DeleteMomRow(String keyid) throws Exception 
	{
		//this.genTlMommstDao.DeleteMomRow(keyid);
		momServiceApi.DeleteMomRow(keyid);

	}

	@Override
	public void DeleteATTVisitorRow(String keyid) throws Exception 
	{
		//this.genTlMommstDao.DeleteATTVisitorRow(keyid);
		momServiceApi.DeleteVisitor(keyid);

	}

	@Override
	public Workbook MomeetingAttExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws IOException,SQLException, Exception 
		{

		return this.genTlMommstDao.MomeetingAttExportExcel(commonFilter,colmodel, format);
	}

	public Workbook getmomAttReportExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlMommstDao.getmomAttReportExcel(commonFilter,colmodel, format);
	}
	

	@Override
	public List<String[]> getAttendance(CommonFilter commonFilter,String flid) throws Exception {
		// TODO Auto-generated method stub
		return genTlMommstDao.getAttendance(commonFilter,flid); 
	}
	@Override
	public List<String[]> getAttendanceDHQ(CommonFilter commonFilter,String flid) throws Exception {
		// TODO Auto-generated method stub
		return genTlMommstDao.getAttendanceDHQ(commonFilter,flid); 
	}
	@Override
	public List<String[]> getAttendancemonthwise(CommonFilter commonFilter,
			String flid) throws Exception {
		// TODO Auto-generated method stub
		return genTlMommstDao.getAttendancemonthwise(commonFilter,flid);
	}

	@Override
	public List<String[]> getAttVistor(CommonFilter commonFilter,String MasterKeyid, String shift, String date, String flid, String type,String pillarid,String recall) throws Exception
	{
		//return this.genTlMommstDao.getAttVistor(commonFilter,MasterKeyid,shift,date,flid,type,pillarid,recall);
		return momServiceApi.momGridVisitor(MasterKeyid, shift, date, flid, type, pillarid, recall);
	}

	@Override
	public GenTlVisitors createVisitor(GenTlVisitors newGenTlVisitors,GenTlVisitors existGenTlVisitors) throws Exception {
	
		try {
			CommonMessage.debugMsg("Inside Create");
			String validationsFor = "createKpi";
			String xml = "AddVisitors";
			validations.validate(newGenTlVisitors, xml, validationsFor);
			fillValuesgenTlMoMAttVisitor(newGenTlVisitors, existGenTlVisitors);
			CommonMessage.debugMsg("fillvalue fill after Att Visitor ");
			CommonMessage.debugMsg("After Fill Values Visitor");
			//return genTlMommstDao.createVisitor(newGenTlVisitors);
			return momServiceApi.saveVisitor(newGenTlVisitors);
		}  catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}
	}

	private GenTlVisitors fillValuesgenTlMoMAttVisitor(GenTlVisitors newGenTlVisitors,GenTlVisitors existGenTlVisitors)
	{
		CommonMessage.debugMsg("Inside fill Values");
		newGenTlVisitors.setVisiActive("Y");
		String dateTime = CommonFunctions.pg_dateTimeNow();
			
		newGenTlVisitors.setVisiCreatedon(dateTime);	
		
		if( newGenTlVisitors.getVisiMomsKeyid() == null )
			newGenTlVisitors.setVisiMomsKeyid("{}");
		if( newGenTlVisitors.getVisiVisitorname() == null )
			newGenTlVisitors.setVisiVisitorname("{}");
			
		if( newGenTlVisitors.getVisiPurpose() == null )
			newGenTlVisitors.setVisiPurpose("{}");		
		
		if( newGenTlVisitors.getVisiTempfield1() == null )
			newGenTlVisitors.setVisiTempfield1("-");
		
		if( newGenTlVisitors.getVisiTempfield2() == null )
			newGenTlVisitors.setVisiTempfield2("-");
		
		if( newGenTlVisitors.getVisiTempfield3() == null )
			newGenTlVisitors.setVisiTempfield3("-");
		
		if( newGenTlVisitors.getVisiTempfield4() == null )
			newGenTlVisitors.setVisiTempfield4("-");

		if( newGenTlVisitors.getVisiTempfield5() == null )
			newGenTlVisitors.setVisiTempfield5("-");
		
		if( newGenTlVisitors.getVisiCreatedby() == null )
			newGenTlVisitors.setVisiCreatedby("{}");
		
			
		if( newGenTlVisitors.getVisiModifiedon() == null )
			newGenTlVisitors.setVisiModifiedon(dateTime);
		
		return newGenTlVisitors; 
		
	}

	@Override
	public GenTlVisitors updateVisitor(GenTlVisitors newGenTlVisitors,GenTlVisitors existGenTlVisitors) throws Exception {
		try {
			CommonMessage.debugMsg("Inside Create");
			String validationsFor = "create";
			String xml = "Momeeting";
			validations.validate(newGenTlVisitors, xml, validationsFor);
			fillValuesgenTlMoMAttVisitor(newGenTlVisitors, existGenTlVisitors);
			CommonMessage.debugMsg("fillvalue fill after Att Visitor ");
			CommonMessage.debugMsg("After Fill Values Visitor");
			//return genTlMommstDao.updateVisitor(newGenTlVisitors);
			return momServiceApi.saveVisitor(newGenTlVisitors);
		}  catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}
	}

	@Override
	public GenTlVisitors selectVisitor(String visitorkey) throws NoDataFoundException, SQLException, Exception {
		
		return this.genTlMommstDao.selectVisitor(visitorkey);
	}

	@Override
	public GenTlMommst updateApl(String dtlKeyid, String aplKeyid)
			throws Exception {
		// TODO Auto-generated method stub
		return genTlMommstDao.updateApl(dtlKeyid, aplKeyid);
	}

	@Override
	public List<ComboBox> getRoleComboComboList(
			ComboFilter roleComboComboFilter, String flid, String string)
			throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(" flid :: "+flid);
		roleComboComboFilter.setIdField("ROLE_KEYID");	
		roleComboComboFilter.setCodeField("ROLE_CODE");	
		roleComboComboFilter.setNameField("ROLE_NAME");		
		roleComboComboFilter.setTableName("ADM_TL_ROLEMST");
		
		return commonFilterDao.fillComboValues(roleComboComboFilter);
	}
	
	@Override
	public List<ComboBox> getrolebasedemployee(ComboFilter empComboFilter,
			String flid, String string) throws Exception {
		// TODO Auto-generated method stub
		
		
		empComboFilter.setIdField("EMPM_KEYID");	
		empComboFilter.setCodeField("EMPM_CODE");
		empComboFilter.setNameField("EMPM_NAME");
			
		empComboFilter.setTableName("GEN_TL_EMPLOYEEMST");
		
		return commonFilterDao.fillComboValues(empComboFilter);
		
	}

	@Override
	public List<String[]> FillMomAttGridData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return genTlMommstDao.FillMomAttGridData(keyid);
	}


	@Override
	public List<String[]> FillRoleDatainGrid(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return genTlMommstDao.FillRoleDatainGrid(keyid);
	}

	/*
	 * @Override public List<String[]> fillagendadata(String flid, String momdate)
	 * throws Exception { // TODO Auto-generated method stub //return
	 * genTlMommstDao.fillagendadata(flid,momdate); return
	 * momServiceApi.fillagendadata(momdate, flid); }
	 */
	
	@Override
	public List<String[]> fillagendadata(String flid, String momdate)
			throws Exception {
		// TODO Auto-generated method stub
		//return genTlMommstDao.fillagendadata(flid,momdate);
		return momServiceApi.fillagendadata(flid,momdate);//order change
	}
	@Override
	public List<String[]> getMomReleatedFileManager(String momKeyId)
			throws Exception {
		// TODO Auto-generated method stub
		return genTlMommstDao.getMomReleatedFileManager(momKeyId);
	}
	
	
	@Override
	public Workbook momExcelView(String meetingType,String momKeyId, String flid, String path) throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg(" momKeyId "+momKeyId+" flid "+flid);

		CommonMessage.debugMsg(" momKeyId  1 ");
		CommonMessage.debugMsg("mom excel view");
		
		List<String[]> mommstData = genTlMommstDao.getfillmstdata(momKeyId,flid);
		List<String[]> momdtlData = genTlMommstDao.getfilldetaildata(momKeyId,flid);
		List<String[]> atnplnData = genTlMommstDao.getfillactnplndata(momKeyId,flid);
		List<String[]> attdanceData = genTlMommstDao.getfillattdanceData(momKeyId,flid);
		List<String[]> externalData = genTlMommstDao.getfillexternalData(momKeyId,flid);
		
		CommonMessage.debugMsg("attdanceData"+attdanceData.size());
		String glbType= meetingType ;// commonFilter.getGENERAL();
		
		CommonMessage.debugMsg(" momKeyId  2 :: After ");
		
		Workbook wb=null;
		
		if(UIUtils.isValidKeyId(glbType)){
			CommonMessage.debugMsg(" momKeyId  22 :: Inisde :: if ");
			CommonMessage.debugMsg("inside the if in glbtype");
			wb=(new MOMITCExcelProductionTemplate(dbActionTemplate)).fillValues(mommstData, momdtlData,atnplnData,path,attdanceData,glbType,externalData);
		 
		}
		else{
			CommonMessage.debugMsg(" momKeyId  22 :: Inisde :: else ");
			wb=(new MOMITCExcelTemplate(dbActionTemplate)).fillValues(mommstData, momdtlData,atnplnData,path,attdanceData,externalData);
		}
		
		return wb;
	}

	@Override
	public List<String[]> getMomAttendanceEmpMailIds(String momKeyId,
			String flid) throws Exception {
		// TODO Auto-generated method stub
		return genTlMommstDao.getMomAttendanceEmpMailIds(momKeyId, flid);
	}

	@Override
	public List<ComboBox> getPillarGroupcombo(String condSql,ComboFilter comboFilter)
			throws Exception {
		// TODO Auto-generated method stub
		
		comboFilter.setNameField("MGRM_NAME");
		comboFilter.setIdField("MGRM_KEYID");
		comboFilter.setCondSql(condSql);
		comboFilter.setTableName(TableNames.GEN_TL_MOM_GROUPMST+" EM JOIN GEN_MV_FLIDHIERARCHY MV ON MV.FLID = EM.MGRM_FLID ");
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<String[]> getAttendancemonthwiseCount(CommonFilter commonFilter, String flid) throws Exception {
		// TODO Auto-generated method stub
		return genTlMommstDao.getAttendancemonthwiseCount(commonFilter,flid);
	}

	public Workbook getMomCountExcel(CommonFilter commonFilter,JSONObject  colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("ins ser imp");
		return genTlMommstDao.getMomCountExcel(commonFilter,colmodel,format);
	}

	public List<String[]> getMomAttendanceCountData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return genTlMommstDao.getMomAttendanceCountData(commonFilter);
	}
	
	@Override
	public List<String[]> getNewAttendance(CommonFilter commonFilter,String flid) throws Exception {
		// TODO Auto-generated method stub
		return genTlMommstDao.getNewAttendance(commonFilter,flid);
	}


	public Workbook getnewmomAttReportExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlMommstDao.getnewmomAttReportExcel(colmodel, format,commonFilter);
	}

	@Override
	public String updateIsmailid(String momid,String val) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlMommstDao.updateIsmail(momid,val);
	}

	@Override
	public String getmailidTrigger(String mailid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlMommstDao.getmailidTrigger(mailid);

	}

	@Override
	public String PillarSelectedData(CommonFilter commonfilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.genTlMommstDao.PillarSelectedData(commonfilter);
	}
	@Override
	public List<String[]> getAttendancemonthwiseDHQ(CommonFilter commonFilter, String flid) throws Exception {
		// TODO Auto-generated method stub
		return genTlMommstDao.getAttendancemonthwiseDHQ(commonFilter,flid);
	}

	@Override
	public Workbook MomeetingMonthwiseExportExcelDHQ(CommonFilter commonFilter, JSONObject colmodel, String format)
			throws Exception {
		// TODO Auto-generated method stub
		return this.genTlMommstDao.MomeetingMonthwiseExportExcelDHQ(commonFilter, colmodel,format);
	}

	@Override
	public Workbook getmomAttReportExcelDHQ(JSONObject colmodel, String format, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlMommstDao.getmomAttReportExcelDHQ(commonFilter,colmodel, format);
	}
	
	public static boolean isValidDateFormat(String dateStr) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("d-MMM-yyyy", Locale.ENGLISH);

        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


}
