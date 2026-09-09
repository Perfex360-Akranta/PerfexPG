package com.akranta.tpm.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.MOMeetingBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlNewMommstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlNewMommstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.MOMITCExcelProductionTemplate;
import com.akranta.tpm.exportreport.MOMITCExcelTemplate;
import com.akranta.tpm.exportreport.NEWMOMITCExcelProductionTemplate;
import com.akranta.tpm.exportreport.NEWMOMITCExcelTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;
import com.akranta.tpm.service.NewMoMeetingService;
import com.akranta.tpm.service.api.MomServiceApi;
import com.akranta.tpm.service.api.NewMomServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

import net.sf.json.JSONObject;

public class NewMoMeetingServiceImpl implements NewMoMeetingService{
	private GenTlNewMommstDao genTlNewMommstDao;
	private Validations validations;
	private CommonFilterDao commonFilterDao ;
	DBActionTemplate  dbActionTemplate ;
	NewMomServiceApi newMomServiceApi;
	public NewMoMeetingServiceImpl(DBActionTemplate dbActionTemplate) {
		genTlNewMommstDao = new GenTlNewMommstDaoImpl(dbActionTemplate);
		validations = new Validations();
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);	
		this.dbActionTemplate  =dbActionTemplate ;
	}
	
	public void NewmomeetingServiceImplJwt(String JwtToken){
    	try{
    		genTlNewMommstDao.GenTlNewMommstDaoImplJwt(JwtToken);
    		
    		newMomServiceApi = new NewMomServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	public GenTlMommst select(String keyid) throws Exception {
		//return this.genTlNewMommstDao.select(keyid);
		return newMomServiceApi.getMomById(keyid);
	}
	public List<String[]> selectRecalling(String shift, String mstDate,
			String flid, String type, String pillarid) throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("Shift Id "+shift+" mstDate "+mstDate+" flid "+flid+" type "+type+" pillarid "+pillarid);
		//return this.genTlMommstDao.selectRecalling(shift, mstDate,flid,type,pillarid);
		String date = CommonFunctions.pg_getDate(mstDate);
		return newMomServiceApi.selectRecalling(shift, date, flid, type, pillarid);
		//return this.genTlNewMommstDao.selectRecalling(shift, mstDate,flid,type,pillarid);
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

@Override
	public String RoleBasedFlid(String originalId) {
		return newMomServiceApi.getFlid(originalId);
	}
@Override
public List<String[]> getNewMomGrid(CommonFilter commonFilter, String KeyId, String momdate, String shift, String pillarid)
		throws Exception {
	
	String flid=commonFilter.getFlid();
	String type=commonFilter.getType();
	return newMomServiceApi.getNewMomGrid(KeyId, shift, momdate, flid, type,pillarid);
}
//	@Override
//	public List<String[]> getNewMomGrid(CommonFilter commonFilter, String KeyId, String momdate, String shift, String pillarid)
//			throws Exception {
//		
//		String flid=commonFilter.getFlid();
//		String type=commonFilter.getType();
//		return newMomServiceApi.getNewMomGrid(KeyId, shift, momdate, flid, type);
//		//return this.genTlNewMommstDao.getNewMomGrid(commonFilter, KeyId,momdate,shift,pillarid);
//
//	}
	public List<String[]> getNewMomeetingAtt(CommonFilter commonFilter,String KeyId,String location,String flid,String Momdate, String shift, String recall) throws Exception {
		return this.genTlNewMommstDao.getNewMomeetingAtt(commonFilter, KeyId,location,flid,Momdate,shift,recall);
	}
	@Override
	public List<String[]> getMomAttendanceEmpMailIds(String momKeyId,
			String flid) throws Exception {
		// TODO Auto-generated method stub
		return genTlNewMommstDao.getMomAttendanceEmpMailIds(momKeyId, flid);
	}
	@Override
	public List<String[]> getMomReleatedFileManager(String momKeyId)
			throws Exception {
		// TODO Auto-generated method stub
		return genTlNewMommstDao.getMomReleatedFileManager(momKeyId);
	}
	@Override
	public String updateIsmailid(String momid,String val) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlNewMommstDao.updateIsmail(momid,val);
	}
	public List<ComboBox> getrolebasedemployee(ComboFilter empComboFilter,
			String flid, String string) throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("RoleBasedEmployee");
		empComboFilter.setIdField("EMPM_KEYID");	
		empComboFilter.setCodeField("EMPM_CODE");
		empComboFilter.setNameField("EMPM_NAME");
		empComboFilter.setTableName("GEN_TL_EMPLOYEEMST");
		return commonFilterDao.fillComboValues(empComboFilter);
	}
	@Override
	public Workbook newmomExcelView(String meetingType,String momKeyId, String flid, String path) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> mommstData = genTlNewMommstDao.getfillmstdata(momKeyId,flid);
		List<String[]> momdtlData = genTlNewMommstDao.getfilldetaildata(momKeyId,flid);
		List<String[]> atnplnData = genTlNewMommstDao.getfillactnplndata(momKeyId,flid);
		List<String[]> attdanceData = genTlNewMommstDao.getfillattdanceData(momKeyId,flid);
		List<String[]> externalData = genTlNewMommstDao.getfillexternalData(momKeyId,flid);
		String glbType= meetingType ;// commonFilter.getGENERAL();	
		Workbook wb=null;
		if(UIUtils.isValidKeyId(glbType)){
			wb=(new NEWMOMITCExcelProductionTemplate(dbActionTemplate)).fillValues(mommstData, momdtlData,atnplnData,path,attdanceData,glbType,externalData);
		}
		else{
			wb=(new NEWMOMITCExcelTemplate(dbActionTemplate)).fillValues(mommstData, momdtlData,atnplnData,path,attdanceData,externalData);
		}
		return wb;
	}
	@Override
	public List<String[]> getAttVistor(CommonFilter commonFilter,String MasterKeyid, String shift, String date, String flid, String type,String pillarid,String recall) throws Exception
	{
		//return this.genTlNewMommstDao.getAttVistor(commonFilter,MasterKeyid,shift,date,flid,type,pillarid,recall);
		return newMomServiceApi.momGridVisitor(MasterKeyid, shift, date, flid, type, pillarid, recall);
	}
	@Override
	public GenTlVisitors createVisitor(GenTlVisitors newGenTlVisitors,GenTlVisitors existGenTlVisitors) throws Exception {
	
		try {
			String validationsFor = "createKpi";
			String xml = "AddVisitors";
			validations.validate(newGenTlVisitors, xml, validationsFor);
			fillValuesgenTlNewMoMAttVisitor(newGenTlVisitors, existGenTlVisitors);
			//return genTlNewMommstDao.createVisitor(newGenTlVisitors);
			return newMomServiceApi.saveVisitor(newGenTlVisitors);
		}  catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}
	}
	private GenTlVisitors fillValuesgenTlNewMoMAttVisitor(GenTlVisitors newGenTlVisitors,GenTlVisitors existGenTlVisitors)
	{
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
	
	private void fillValuesgenTlMommst(GenTlMommst newGenTlMommst,
			GenTlMommst exitGenTlMommst) throws Exception {
		newGenTlMommst.setMomsActive("Y");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		newGenTlMommst.setMomsCreatedon(dateTime);
		newGenTlMommst.setMomsModifiedon(dateTime);
		String momDate = newGenTlMommst.getMomsDate();
		newGenTlMommst.setMomsDate(CommonFunctions.pg_getDateTimeFromDate(momDate));
		String momDateVal = newGenTlMommst.getMomsDate();
		if (newGenTlMommst.getMomsMeetingno() == null)
			newGenTlMommst.setMomsMeetingno("{}");
		
		if (newGenTlMommst.getMomsMeetingtitle() == null){
			
			if (newGenTlMommst.getMomsMeetingtype().equals("D")){
				newGenTlMommst.setMomsMeetingtitle("DMT"+" "+momDateVal.substring(0,8)+dateTime.substring(11,19));//dateTime
			}
			else if (newGenTlMommst.getMomsMeetingtype().equals("J"))
			{
					newGenTlMommst.setMomsMeetingtitle("JH"+" "+momDateVal.substring(0,8)+dateTime.substring(11,19));
			}
			else if (newGenTlMommst.getMomsMeetingtype().equals("O"))
			{
					newGenTlMommst.setMomsMeetingtitle("OTHERS"+" "+momDateVal.substring(0,8)+dateTime.substring(11,19));
			}
			else if (newGenTlMommst.getMomsMeetingtype().equals("PD"))
			{
					newGenTlMommst.setMomsMeetingtitle("PRODUCTION MEETING"+" "+momDateVal.substring(0,8)+dateTime.substring(11,19));
			}else if (newGenTlMommst.getMomsMeetingtype().equals("P"))
			{
				newGenTlMommst.setMomsMeetingtitle("PILLAR"+" "+momDateVal.substring(0,8)+dateTime.substring(11,19));
		    }
			else
				newGenTlMommst.setMomsMeetingtitle("{}");
			
		}
		
	    if (newGenTlMommst.getMomsMeetingtype() == null)
			newGenTlMommst.setMomsMeetingtype("{}");

		if (newGenTlMommst.getMomsDate() == null)
			newGenTlMommst.setMomsDate(newGenTlMommst.getMomsDate());
		if (newGenTlMommst.getMomsFlid() == null)

			newGenTlMommst.setMomsFlid("{}");
		if (newGenTlMommst.getMomsIsmeetinghappen() == null)
			newGenTlMommst.setMomsIsmeetinghappen("N");

		if (newGenTlMommst.getMomsRemarks() == null)
			newGenTlMommst.setMomsRemarks("{}");

		if (newGenTlMommst.getMomsSafetytalk() == null)
			newGenTlMommst.setMomsSafetytalk("{}");
		if (newGenTlMommst.getMomsShiftid() == null)
			newGenTlMommst.setMomsShiftid("{}");
		
		if(newGenTlMommst.getMomsRefdocid()==null)
		    newGenTlMommst.setMomsRefdocid("{}");
		
		if(newGenTlMommst.getMomsRefdoctype()==null)
		    newGenTlMommst.setMomsRefdoctype("{}");
		
		if(newGenTlMommst.getMomsIsMailTrig()==null)
		    newGenTlMommst.setMomsIsMailTrig("N");
		
		if(newGenTlMommst.getMomsTempField1()==null)
		    newGenTlMommst.setMomsTempField1("-");
		
		if(newGenTlMommst.getMomsTempField2()==null)
		    newGenTlMommst.setMomsTempField2("-");
		
		if(newGenTlMommst.getMomsTempField3()==null)
		    newGenTlMommst.setMomsTempField3("-");
		
		

		if (newGenTlMommst.getMomsPillarid() == null)
			newGenTlMommst.setMomsPillarid("{}");
		if (newGenTlMommst.getMomsIsmessageboard() == null)
			newGenTlMommst.setMomsIsmessageboard("N");
		if (newGenTlMommst.getMomsAgenda() == null)
			newGenTlMommst.setMomsAgenda("{}");
		if (newGenTlMommst.getMomsOthers() == null)
			newGenTlMommst.setMomsOthers("N");
		if (newGenTlMommst.getMomsPillargroup() == null)
			newGenTlMommst.setMomsPillargroup("{}");
		
		List<GenTlMomKpiLink> newgenTlMomKpiLinks = newGenTlMommst.getMomeetingKPI();
		newGenTlMommst.setMomeetingDetail(fillValuesgenTlMomdtl(newGenTlMommst,exitGenTlMommst));
		newGenTlMommst.setMomeettingAttence(fillValuesnewGenTlMommstatt(newGenTlMommst,exitGenTlMommst));
		if(newgenTlMomKpiLinks!=null)
		{
			newGenTlMommst.setMomeetingKPI(fillValuesgenTlMommKpi(newGenTlMommst,	exitGenTlMommst));
		}
	}
	private List<GenTlMomattendance> fillValuesnewGenTlMommstatt(GenTlMommst newGenTlMommst,
			GenTlMommst exitGenTlMommst) {
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<GenTlMomattendance> newgenTlMomattendances = newGenTlMommst.getMomeetinMomattendances();
		List<GenTlMomattendance> exitGenTlMomattendances = null;
		GenTlMomattendance exitGenTlMomattendance = null;
		
		if (exitGenTlMommst != null) {
			exitGenTlMomattendances = exitGenTlMommst.getMomeetinMomattendances();
			if (exitGenTlMomattendance != null
					&& exitGenTlMomattendances.size() > 0)
				exitGenTlMomattendance = exitGenTlMomattendances.get(0);
		}
		List<GenTlMomattendance> newGenTlMomdattList = new ArrayList<GenTlMomattendance>();
		for (GenTlMomattendance genTlMomattendance : newgenTlMomattendances)

		{
			String momaDate = genTlMomattendance.getMomaDate();
			genTlMomattendance.setMomaDate(CommonFunctions.pg_getDateTimeFromDate(momaDate));

			   genTlMomattendance.setMomaCreatedon(dateTime);
			   genTlMomattendance.setMomaCreatedby("USR0001");
               genTlMomattendance.setMomaModifiedon(dateTime);

			if (genTlMomattendance.getMomaDate() == null)
				genTlMomattendance.setMomaDate(newGenTlMommst.getMomsDate());
			if (genTlMomattendance.getMomaFlid() == null)
				genTlMomattendance.setMomaFlid("{}");
			
			if (genTlMomattendance.getMomaAttandance() == null)
				genTlMomattendance.setMomaAttandance("P");

			if (genTlMomattendance.getMomaActive() == null)
				genTlMomattendance.setMomaActive("Y");

			if (genTlMomattendance.getMomaEmployeeid() == null)
				genTlMomattendance.setMomaEmployeeid("{}");

			if (genTlMomattendance.getMomaKeyid() == null)
				genTlMomattendance.setMomaKeyid("{}");
			
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
		return newGenTlMomdattList;		
	}
	private List<GenTlMomdtl> fillValuesgenTlMomdtl(GenTlMommst newGenTlMommst,GenTlMommst exitGenTlMommst) throws Exception 
	{
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<GenTlMomdtl> newgenTlMomdtls = newGenTlMommst.getMomeetingDetail();
		List<GenTlMomdtl> exitGenTlMomdtls = null;
		
		String mstKeyId = newGenTlMommst.getMomsKeyid();
		CommonMessage.debugMsg("KEY ID VALUE "+mstKeyId);
		
		GenTlMomdtl exitGenTlMomdtl = null;
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
				genTlMomdtl.setMomdCreatedon(dateTime);
				genTlMomdtl.setMomdModifiedon(dateTime);
				genTlMomdtl.setMomdActive("Y");
				genTlMomdtl.setMomdCreatedby(newGenTlMommst.getMomsCreatedby());
				
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
		return newGenTlMomdtlsList;
	}
	
	private List<GenTlMomKpiLink> fillValuesgenTlMommKpi(GenTlMommst newGenTlMommst, GenTlMommst exitGenTlMommst) {
		// TODO Auto-generated method stub
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<GenTlMomKpiLink> newgenTlMomKpiLinks = newGenTlMommst.getMomeetingKPI();
		List<GenTlMomKpiLink> exitGenTlMomKpiLinks = null;
		GenTlMomKpiLink exitGenTlMomKpiLink = null;
		if (exitGenTlMommst != null) {
			exitGenTlMomKpiLinks = exitGenTlMommst.getMomeetingKPI();
			if (exitGenTlMomKpiLinks != null && exitGenTlMomKpiLinks.size() > 0)
				exitGenTlMomKpiLink = exitGenTlMomKpiLinks.get(0);
		}
		List<GenTlMomKpiLink> newGenTlMomdLinksList = new ArrayList<GenTlMomKpiLink>();		
		for (GenTlMomKpiLink genTlMomKpiLink : newgenTlMomKpiLinks)
		{
			genTlMomKpiLink.setMokpCreatedon(dateTime);
			genTlMomKpiLink.setMokpModifiedon(dateTime);
			genTlMomKpiLink.setMokpActive("Y");
			genTlMomKpiLink.setMokpCreatedby(newGenTlMommst.getMomsCreatedby());			
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
		return newGenTlMomdLinksList;
	}

	@Override
	public GenTlVisitors updateVisitor(GenTlVisitors newGenTlVisitors,GenTlVisitors existGenTlVisitors) throws Exception {
		try {
			String validationsFor = "create";
			String xml = "NewMomeeting";
			validations.validate(newGenTlVisitors, xml, validationsFor);
			fillValuesgenTlNewMoMAttVisitor(newGenTlVisitors, existGenTlVisitors);
			return genTlNewMommstDao.updateVisitor(newGenTlVisitors);
		}  catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}
	}
	@Override
	public void DeleteATTVisitorRow(String keyid) throws Exception 
	{
		//this.genTlNewMommstDao.DeleteATTVisitorRow(keyid);
		newMomServiceApi.DeleteVisitor(keyid);
	}
	@Override
	public String getmailidTrigger(String mailid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlNewMommstDao.getmailidTrigger(mailid);
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
public GenTlVisitors selectVisitor(String visitorkey) throws NoDataFoundException, SQLException, Exception {
	
	return this.genTlNewMommstDao.selectVisitor(visitorkey);
}
	@Override
	public GenTlMommst create(GenTlMommst newGenTlMommst, GenTlMommst existGenTlMommst, MOMeetingBean momeetingBean)
			throws Exception {		
		try {
			
			String validationsFor;
			if(momeetingBean.getMomCellId()==null || momeetingBean.getMomCellId()==" " || momeetingBean.getMomCellId().length()== 0){
				CommonMessage.debugMsg("Inside Create");
				validationsFor = "create";
				validations.validate(momeetingBean,"NewMomeeting",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			}
			validationsFor = "create";
			String xml = "NewMomeeting";
			validations.validate(newGenTlMommst, xml, validationsFor);
			fillValuesgenTlMommst(newGenTlMommst, existGenTlMommst);			
			//return genTlNewMommstDao.create(newGenTlMommst);
			return newMomServiceApi.saveMOM(newGenTlMommst);
		} catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}
	}
	@Override
	public GenTlMommst updateatt(GenTlMommst newGenTlMommst, GenTlMommst existGenTlMommst) throws Exception {
		// TODO Auto-generated method stub
		     return genTlNewMommstDao.updateatt(newGenTlMommst);
	}
	@Override
	public GenTlMommst update(GenTlMommst newGenTlMommst, GenTlMommst existGenTlMommst, MOMeetingBean momeetingBean)
			throws Exception {		
		try {
			String validationsFor;
			if(momeetingBean.getMomCellId()==null || momeetingBean.getMomCellId()==" " || momeetingBean.getMomCellId().length()== 0){
				validationsFor = "create";
				validations.validate(momeetingBean,"NewMomeeting",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			}
			
			validationsFor = "create";
			String xml = "NewMomeeting";
			validations.validate(newGenTlMommst, xml, validationsFor);
			fillValuesgenTlMommst(newGenTlMommst, existGenTlMommst);			
			
			return newMomServiceApi.saveMOM(newGenTlMommst);
		} catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}
	}
	public GenTlActionplanmst createActionPlan(GenTlActionplanmst newActionplanmst, GenTlActionplandtl newGenTlActionplandtl,GenTlMommst newGenTlMommst)throws Exception{
		fillValues(newActionplanmst,newGenTlActionplandtl);
		//return genTlNewMommstDao.createActionPlan(newActionplanmst,newGenTlActionplandtl,newGenTlMommst);
		return newMomServiceApi.createActionPlan(newActionplanmst, newGenTlActionplandtl, newGenTlMommst);
	    	
	}
    public GenTlActionplanmst updateActionPlan(GenTlActionplanmst newActionplanmst, GenTlActionplandtl newGenTlActionplandtl,
    		GenTlMommst newGenTlMommst,String Rowid,String ActionPlanId,String ActionplanDetailId)throws Exception{
	fillValuesADtl(newActionplanmst,newGenTlActionplandtl);
	//return genTlNewMommstDao.updateActionPlan(newActionplanmst,newGenTlActionplandtl,newGenTlMommst,Rowid,ActionPlanId,ActionplanDetailId);
	return newMomServiceApi.updateActionPlan(newActionplanmst, newGenTlActionplandtl, newGenTlMommst, Rowid, ActionPlanId, ActionplanDetailId);
}
	private void fillValuesADtl(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl newGenTlActionplandtl)throws Exception 
	{
		String dateTime = CommonFunctions.pg_dateTimeNow();
		genTlActionplanmst.setAplmActive("Y");	
		genTlActionplanmst.setAplmCreatedon(dateTime);
		genTlActionplanmst.setAplmModifiedon(dateTime);
		if(genTlActionplanmst.getAplmPillarid()==null)
			genTlActionplanmst.setAplmPillarid("{}");		
		if(genTlActionplanmst.getAplmMasterrefid()==null)
			genTlActionplanmst.setAplmMasterrefid("{}");
		if(genTlActionplanmst.getAplmDetailrefid()==null)
			genTlActionplanmst.setAplmDetailrefid("{}");
		if(genTlActionplanmst.getAplmMaintask()==null)
			genTlActionplanmst.setAplmMaintask("{}");
		if(genTlActionplanmst.getAplmRefdoctype()==null)
			genTlActionplanmst.setAplmRefdoctype("{}");
		if(genTlActionplanmst.getAplmStatus()==null)
			genTlActionplanmst.setAplmStatus("P");
		if(genTlActionplanmst.getAplmRemarks()==null)
			genTlActionplanmst.setAplmRemarks("{}");

		if(!UIUtils.isValidDate(genTlActionplanmst.getAplmPlandate()))
			genTlActionplanmst.setAplmPlandate(dateTime);
		
		if(genTlActionplanmst.getAplmTempfiled2()==null)
			genTlActionplanmst.setAplmTempfiled2("-");
		if(genTlActionplanmst.getAplmTempfiled3()==null)
			genTlActionplanmst.setAplmTempfiled3("-");
		if(genTlActionplanmst.getAplmTempfiled4()==null)
			genTlActionplanmst.setAplmTempfiled4("-");
		if(genTlActionplanmst.getAplmTempfiled5()==null)
			genTlActionplanmst.setAplmTempfiled5("-");	
		if(genTlActionplanmst.getAplmCreatedby()==null)
			genTlActionplanmst.setAplmCreatedby("{}");
		if(genTlActionplanmst.getAplmCreatedby()==null)
			genTlActionplanmst.setAplmCreatedby("{}");
		if(genTlActionplanmst.getAplmElementid()==null)
			genTlActionplanmst.setAplmElementid("{}");
		if(genTlActionplanmst.getAplmFlid()==null)
			genTlActionplanmst.setAplmFlid("{}");
		if(genTlActionplanmst.getAplmElementid()==null)
			genTlActionplanmst.setAplmElementid("{}");
		
		fillActionplandtlValues(newGenTlActionplandtl,genTlActionplanmst);		
		genTlActionplanmst.setAplmRemarks(newGenTlActionplandtl.getApldRemarks());
	}

	private void fillValues(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl newGenTlActionplandtl)throws Exception 
	{
		String dateTime = CommonFunctions.pg_dateTimeNow();
		genTlActionplanmst.setAplmActive("Y");	
		genTlActionplanmst.setAplmCreatedon(dateTime);
		genTlActionplanmst.setAplmModifiedon(dateTime);
		if(genTlActionplanmst.getAplmPillarid()==null)
			genTlActionplanmst.setAplmPillarid("{}");		
		if(genTlActionplanmst.getAplmMasterrefid()==null)
			genTlActionplanmst.setAplmMasterrefid("{}");
		if(genTlActionplanmst.getAplmDetailrefid()==null)
			genTlActionplanmst.setAplmDetailrefid("{}");
		if(genTlActionplanmst.getAplmMaintask()==null)
			genTlActionplanmst.setAplmMaintask("{}");
		if(genTlActionplanmst.getAplmRefdoctype()==null)
			genTlActionplanmst.setAplmRefdoctype("{}");
		if(genTlActionplanmst.getAplmStatus()==null)
			genTlActionplanmst.setAplmStatus("P");
		if(genTlActionplanmst.getAplmRemarks()==null)
			genTlActionplanmst.setAplmRemarks("{}");
	
		if(!UIUtils.isValidDate(genTlActionplanmst.getAplmPlandate()))
			genTlActionplanmst.setAplmPlandate(dateTime);
		
		if(genTlActionplanmst.getAplmTempfiled2()==null)
			genTlActionplanmst.setAplmTempfiled2("-");
		if(genTlActionplanmst.getAplmTempfiled3()==null)
			genTlActionplanmst.setAplmTempfiled3("-");
		if(genTlActionplanmst.getAplmTempfiled4()==null)
			genTlActionplanmst.setAplmTempfiled4("-");
		if(genTlActionplanmst.getAplmTempfiled5()==null)
			genTlActionplanmst.setAplmTempfiled5("-");	
		if(genTlActionplanmst.getAplmCreatedby()==null)
			genTlActionplanmst.setAplmCreatedby("{}");
		if(genTlActionplanmst.getAplmCreatedby()==null)
			genTlActionplanmst.setAplmCreatedby("{}");
		if(genTlActionplanmst.getAplmElementid()==null)
			genTlActionplanmst.setAplmElementid("{}");
		if(genTlActionplanmst.getAplmFlid()==null)
			genTlActionplanmst.setAplmFlid("{}");
		if(genTlActionplanmst.getAplmElementid()==null)
			genTlActionplanmst.setAplmElementid("{}");
		
		fillActionplandtlValues(newGenTlActionplandtl,genTlActionplanmst);		
		genTlActionplanmst.setAplmRemarks(newGenTlActionplandtl.getApldRemarks());
	}

	private void fillActionplandtlValues(GenTlActionplandtl genTlActionplandtl,GenTlActionplanmst genTlActionplanmst) {
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		  String targetDate = genTlActionplandtl.getApldTargetdate();
		  genTlActionplandtl.setApldTargetdate(CommonFunctions.pg_getDateTimeFromDate(targetDate));
		 
		if(genTlActionplandtl.getApldTargetdate()==null)
			genTlActionplandtl.setApldTargetdate(dateTime);
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldActive()))
			genTlActionplandtl.setApldActive("Y");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldAplmKeyid()))
			genTlActionplandtl.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCreatedby()))
			genTlActionplandtl.setApldCreatedby(genTlActionplanmst.getAplmCreatedby());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldActive()))
			genTlActionplandtl.setApldActive("Y");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldActionplan()))
			genTlActionplandtl.setApldActionplan("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldHowtodo()))
			genTlActionplandtl.setApldHowtodo("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCountermeasure()))
			genTlActionplandtl.setApldCountermeasure("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldRemarks()))
			genTlActionplandtl.setApldRemarks("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldResponsibility()))
			genTlActionplandtl.setApldResponsibility("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldStatus()))
			genTlActionplandtl.setApldStatus("P");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTradeid()))
			genTlActionplandtl.setApldTradeid("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCompletedby()))
			genTlActionplandtl.setApldCompletedby("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCompleatedon()))
			genTlActionplandtl.setApldCompleatedon(Constants.pgFutureNullDateTime);
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldModifiedon()))
			genTlActionplandtl.setApldModifiedon(CommonFunctions.pg_dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCreatedon()))
			genTlActionplandtl.setApldCreatedon(CommonFunctions.pg_dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldOthers()))
			genTlActionplandtl.setApldOthers("-");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled2()))
			genTlActionplandtl.setApldTempfiled2("-");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled3()))
			genTlActionplandtl.setApldTempfiled3("-");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled4()))
			genTlActionplandtl.setApldTempfiled4("-");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled5()))
			genTlActionplandtl.setApldTempfiled5("-");
		}
	@Override
	public List<String[]> getNewMomeetingList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return genTlNewMommstDao.getNewMomeetingList(commonFilter);
	}

	@Override
public void DeleteNewMomRow(String keyid,String ActionPMasterId) throws Exception 
{
	//this.genTlNewMommstDao.DeleteNewMomRow(keyid,ActionPMasterId);
		newMomServiceApi.DeleteNewMomRow(keyid, ActionPMasterId);

}
	@Override
	public Workbook momExcelView(String meetingType,String momKeyId, String flid, String path) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> mommstData = genTlNewMommstDao.getfillmstdata(momKeyId,flid);
		List<String[]> momdtlData = genTlNewMommstDao.getfilldetaildata(momKeyId,flid);
		List<String[]> atnplnData = genTlNewMommstDao.getfillactnplndata(momKeyId,flid);
		List<String[]> attdanceData = genTlNewMommstDao.getfillattdanceData(momKeyId,flid);
		List<String[]> externalData = genTlNewMommstDao.getfillexternalData(momKeyId,flid);
		String glbType= meetingType ;
		Workbook wb=null;
		
		if(UIUtils.isValidKeyId(glbType)){
			wb=(new MOMITCExcelProductionTemplate(dbActionTemplate)).fillValues(mommstData, momdtlData,atnplnData,path,attdanceData,glbType,externalData);		 
		}
		else{
			wb=(new MOMITCExcelTemplate(dbActionTemplate)).fillValues(mommstData, momdtlData,atnplnData,path,attdanceData,externalData);
		}		
		return wb;
	}
	@Override
	public Workbook getMomeetingExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception{
		return this.genTlNewMommstDao.getMomeetingExportExcel(commonFilter, colmodel,format);
	}
}
