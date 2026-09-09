package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DashboardDispbean;
import com.akranta.tpm.dao.AdmTlDashboadUserrightsDao;
import com.akranta.tpm.dao.DashboardDao;
import com.akranta.tpm.dao.impl.AdmTlDashboadUserrightsDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.DashboardDaoImpl;
import com.akranta.tpm.exportreport.NewDashboardExcelTemplate;
import com.akranta.tpm.model.AdmTlDashboadUserrights;
import com.akranta.tpm.model.AdmTlScrollmsgmst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMessageboard;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class DashboardServiceImpl implements DashboardService{
	private DashboardDao dashboardDao =null;
	private AdmTlDashboadUserrightsDao admtlDashboadUserrightsDao=null;
	private Validations validations;
	DBActionTemplate dbActionTemplate;
	public DashboardServiceImpl(DBActionTemplate dbActionTemplate) throws Exception{
		dashboardDao = new DashboardDaoImpl(dbActionTemplate); 
		admtlDashboadUserrightsDao=new AdmTlDashboadUserrightsDaoImpl(dbActionTemplate);
		validations = new Validations();
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<String[]> getDhashboardPillars()throws Exception {
		return dashboardDao.getDashboardPillars();
	}
	
	public List<DashboardDispbean> getDashboardRptDetails(String pillarCode,String userid) throws Exception{
		return dashboardDao.getDashboardRptDetails(pillarCode,userid);
	}
	@Override
	public List<String[]> getDBUserRights() throws Exception {
		
		return admtlDashboadUserrightsDao.getDBUserRights();
	}
	@Override
	public List<AdmTlDashboadUserrights> createDBUserRights(List<AdmTlDashboadUserrights> newDBUserRightsList,List<AdmTlDashboadUserrights> existAdmTlDashboadUserrights,String usrm_ccno) throws Exception {
	
		FillValues(newDBUserRightsList,existAdmTlDashboadUserrights,usrm_ccno);	
		CommonMessage.debugMsg("Inside Service impl create");
		return admtlDashboadUserrightsDao.create(newDBUserRightsList);
	}
	private List<AdmTlDashboadUserrights> FillValues(List<AdmTlDashboadUserrights> newDBUserRightsList,List<AdmTlDashboadUserrights> existAdmTlDashboadUserrights,String usrm_ccno) {
		AdmTlDashboadUserrights userrights=new AdmTlDashboadUserrights();
		String dateTime = CommonFunctions.dateTimeNow();
		
		
		if(newDBUserRightsList!=null && newDBUserRightsList.size()>0){
			for(int i=0;i<newDBUserRightsList.size();i++){
				CommonMessage.debugMsg("inside fillvalues for"+ " size"+ newDBUserRightsList.size());
				CommonMessage.debugMsg("inside fillvalues for"+ " keyid"+ newDBUserRightsList.get(i).getDburKeyid());
				userrights.setDburKeyid(newDBUserRightsList.get(i).getDburKeyid());
				
				newDBUserRightsList.get(i).setDburCreatedby(usrm_ccno);
				
				//if(!CommonFunctions.isValidKeyId(newDBUserRightsList.get(i).getDburKeyid())){
					//CommonMessage.debugMsg("dburkeyid"+newDBUserRightsList.get(i).getDburKeyid());
					//newDBUserRightsList.get(i).setDburCreatedon(dateTime);
				//}
				if(newDBUserRightsList.get(i).getDburActive()==null){
					
					newDBUserRightsList.get(i).setDburActive("Y");
					CommonMessage.debugMsg("inside db active"+newDBUserRightsList.get(i).getDburActive());
				}
				if(newDBUserRightsList.get(i).getDburCreatedon()==null)
					newDBUserRightsList.get(i).setDburCreatedon(dateTime);
				if(newDBUserRightsList.get(i).getDburModifiedon()==null){
					newDBUserRightsList.get(i).setDburModifiedon(dateTime);
					CommonMessage.debugMsg("modifiedon..."+newDBUserRightsList.get(i).getDburModifiedon());
				}
				if(newDBUserRightsList.get(i).getDburMenuid()==null){
					newDBUserRightsList.get(i).setDburMenuid("{}");
					CommonMessage.debugMsg("menuid..."+newDBUserRightsList.get(i).getDburMenuid());
				}
				if(newDBUserRightsList.get(i).getDburRoleid()==null){
					newDBUserRightsList.get(i).setDburRoleid("{}");
					CommonMessage.debugMsg("roleid..."+newDBUserRightsList.get(i).getDburRoleid());
				}
				if(newDBUserRightsList.get(i).getDburTempfield()==null)
					newDBUserRightsList.get(i).setDburTempfield("-");
				if(newDBUserRightsList.get(i).getDburTempfiled1()==null)
					newDBUserRightsList.get(i).setDburTempfiled1("-");
				if(newDBUserRightsList.get(i).getDburTempfield2()==null)
					newDBUserRightsList.get(i).setDburTempfield2("-");
				if(newDBUserRightsList.get(i).getDburTempfield3()==null){
					newDBUserRightsList.get(i).setDburTempfield3("-");
					
				}
		}
		
	}
		CommonMessage.debugMsg("inside  size"+ newDBUserRightsList.size());
		return newDBUserRightsList;
	}
	@Override
	public Workbook getDBUserRightsExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat)throws Exception {
try {
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return this.admtlDashboadUserrightsDao.getDBUserRightsExcel(commonFilter,colModel,rptFormat);
	}
	@Override
	public List<String[]> getMessage(String flid, String roleId)
			throws Exception {
		// TODO Auto-generated method stub
		return dashboardDao.getMessage(flid, roleId);
	}
	@Override
	public List<String[]> getjhAct(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return dashboardDao.getjhAct(commonFilter);
	}
	@Override
	public GenTlMessageboard createMessage(GenTlMessageboard newGenTlMessageboard,GenTlMessageboard existGenTlMessageboard) throws Exception {
		// TODO Auto-generated method stub
		
		 CommonMessage.debugMsg("Create Block:::");
			try {
				CommonMessage.debugMsg("Inside detail Create");
				String validationsFor = "create";
				String xml = "MessageBoard";
				CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
				//CommonMessage.debugMsg("After Fill Values detail.............."+ newGenTlMessageboard.getTmkmFlid());
				validations.validate(newGenTlMessageboard, xml, validationsFor);
				fillValuesMaster(newGenTlMessageboard, existGenTlMessageboard);
				CommonMessage.debugMsg("fillvalue fill after");
				CommonMessage.debugMsg("After Fill Values");
				return dashboardDao.createMessage(newGenTlMessageboard,existGenTlMessageboard);
			} catch (ValidationExceptions e) {
				throw new ValidationExceptions(e.getMessage());
			}
	}
	private GenTlMessageboard fillValuesMaster(GenTlMessageboard newGenTlMessageboard,GenTlMessageboard existGenTlMessageboard) {

		
		String dateTime = CommonFunctions.dateTimeNow();
		newGenTlMessageboard.setMsgbCreatedon(dateTime);
		newGenTlMessageboard.setMsgbModifiedon(dateTime);
		CommonMessage.debugMsg(" for loop Deatils fill values::::::  3");

		if (newGenTlMessageboard.getMsgbKeyid() == null)
			newGenTlMessageboard.setMsgbKeyid("{}");

		if (newGenTlMessageboard.getMsgbTitle() == null)
			newGenTlMessageboard.setMsgbTitle("{}");

		if (newGenTlMessageboard.getMsgbContent() == null)
			newGenTlMessageboard.setMsgbContent("{}");
		
		if (newGenTlMessageboard.getMsgbEffectivefrom() == null)
			newGenTlMessageboard.setMsgbEffectivefrom("{}");
		
		if (newGenTlMessageboard.getMsgbEffectiveto() == null)
			newGenTlMessageboard.setMsgbEffectiveto("{}");
		
		if (newGenTlMessageboard.getMsgbShownfordays() == null)
			newGenTlMessageboard.setMsgbShownfordays("{}");
		
		if (newGenTlMessageboard.getMsgbFlid() == null)
			newGenTlMessageboard.setMsgbFlid("{}");
		
		if (newGenTlMessageboard.getMsgbActive() == null)
			newGenTlMessageboard.setMsgbActive("{}");
		
		if (newGenTlMessageboard.getMsgbType() == null)
			newGenTlMessageboard.setMsgbType("{}");
		
		if (newGenTlMessageboard.getMsgbEmployeeid() == null)
			newGenTlMessageboard.setMsgbEmployeeid("{}");
		if (newGenTlMessageboard.getMsgbRoleid() == null)
			newGenTlMessageboard.setMsgbRoleid("{}");
		if (newGenTlMessageboard.getMsgbShowngrouptype() == null)
			newGenTlMessageboard.setMsgbShowngrouptype("{}");

		if (newGenTlMessageboard.getMsgbTempfield1() == null)
			newGenTlMessageboard.setMsgbTempfield1("-");
		if (newGenTlMessageboard.getMsgbTempfield2() == null)
			newGenTlMessageboard.setMsgbTempfield2("-");
		if (newGenTlMessageboard.getMsgbTempfield3() == null)
			newGenTlMessageboard.setMsgbTempfield3("-");
		if (newGenTlMessageboard.getMsgbTempfield4() == null)
			newGenTlMessageboard.setMsgbTempfield4("-");
		if (newGenTlMessageboard.getMsgbTempfield5() == null)
			newGenTlMessageboard.setMsgbTempfield5("-");
	
	CommonMessage.debugMsg("End Of  fillValues genTlMomdtl ");

	return newGenTlMessageboard;
		
	}
	@Override
	public GenTlMessageboard updateMessage(GenTlMessageboard newGenTlMessageboard,GenTlMessageboard existGenTlMessageboard) throws Exception {
		// TODO Auto-generated method stub
		try {
			CommonMessage.debugMsg("Inside detail Create");
			String validationsFor = "create";
			String xml = "MessageBoard";
			CommonMessage.debugMsg("Validation XML Name:::::::::" + xml);
			//CommonMessage.debugMsg("After Fill Values detail.............."+ newGenTlMessageboard.getTmkmFlid());
			validations.validate(newGenTlMessageboard, xml, validationsFor);
			fillValuesMaster(newGenTlMessageboard, existGenTlMessageboard);
			CommonMessage.debugMsg("fillvalue fill after");
			CommonMessage.debugMsg("After Fill Values");
			return dashboardDao.updateMessage(newGenTlMessageboard,existGenTlMessageboard);
		} catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}
	}
	@Override
	public GenTlMessageboard selectMessage(String mesgkeyid) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		return dashboardDao.selectMessage(mesgkeyid);
	}
	@Override
	public GenTlMessageboard deleteMessageBoard(GenTlMessageboard newGenTlMessageboard) throws Exception {
		// TODO Auto-generated method stub
		return dashboardDao.deleteMessageBoard(newGenTlMessageboard);
	}
	@Override
	public List<String[]> getFillMsggrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return dashboardDao.getFillMsggrid(commonFilter);
	}
	@Override
	public AdmTlScrollmsgmst createMessageBoard( AdmTlScrollmsgmst newadmTlScrollmsgmst,AdmTlScrollmsgmst existadmTlScrollmsgmst) throws Exception {
		// TODO Auto-generated method stub
		fillValuesMessageBoard(newadmTlScrollmsgmst,existadmTlScrollmsgmst);
		return dashboardDao.createMessageBoard(newadmTlScrollmsgmst,existadmTlScrollmsgmst);
	}
	
	@Override
	public AdmTlScrollmsgmst updateMessageBoard(AdmTlScrollmsgmst newadmTlScrollmsgmst,AdmTlScrollmsgmst existadmTlScrollmsgmst) throws Exception {
		// TODO Auto-generated method stub
		fillValuesMessageBoard(newadmTlScrollmsgmst,existadmTlScrollmsgmst);
		return dashboardDao.updateMessageBoard(newadmTlScrollmsgmst,existadmTlScrollmsgmst);
	}
	
	@Override
	public AdmTlScrollmsgmst deleteMessageBoardNew(
			AdmTlScrollmsgmst newadmTlScrollmsgmst) throws Exception {
		// TODO Auto-generated method stub
		return dashboardDao.deleteMessageBoardNew(newadmTlScrollmsgmst);
	}
	
	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return dashboardDao.FillControlData(keyid);
	}
	
	private void fillValuesMessageBoard(AdmTlScrollmsgmst newadmTlScrollmsgmst,AdmTlScrollmsgmst existadmTlScrollmsgmst)throws Exception {
		// TODO Auto-generated method stub
		
		newadmTlScrollmsgmst.setSmsgActive("Y");			
		String dateTime = CommonFunctions.dateTimeNow();
		
		if(newadmTlScrollmsgmst.getSmsgKeyid() == null )
		{	
			newadmTlScrollmsgmst.setSmsgCreatedon(dateTime);
		}
		else
		{				
			newadmTlScrollmsgmst.setSmsgCreatedon(dateTime);
		}
		
		newadmTlScrollmsgmst.setSmsgModifiedon(dateTime);
		
		if(newadmTlScrollmsgmst.getSmsgFromdate()==null)
			newadmTlScrollmsgmst.setSmsgFromdate(dateTime);
		
		if(newadmTlScrollmsgmst.getSmsgTodate()==null)
			newadmTlScrollmsgmst.setSmsgTodate(dateTime);
		
		if( newadmTlScrollmsgmst.getSmsgFlid() == null )
			newadmTlScrollmsgmst.setSmsgFlid("{}");
	
		if( newadmTlScrollmsgmst.getSmsgRoleid() == null )
			newadmTlScrollmsgmst.setSmsgRoleid("{}");
		
		if( newadmTlScrollmsgmst.getSmsgCellid() == null )
			newadmTlScrollmsgmst.setSmsgCellid("{}");
		
		if( newadmTlScrollmsgmst.getSmsgShiftid() == null )
			newadmTlScrollmsgmst.setSmsgShiftid("{}");
		
		if( newadmTlScrollmsgmst.getSmsgMessage() == null )
			newadmTlScrollmsgmst.setSmsgMessage("{}");
		
		if( newadmTlScrollmsgmst.getSmsgStatus() == null )
			newadmTlScrollmsgmst.setSmsgStatus("Y");
		
		if( newadmTlScrollmsgmst.getSmsgPriority() == null )
			newadmTlScrollmsgmst.setSmsgPriority("Y");
		
		if( newadmTlScrollmsgmst.getSmsgNoexpiry() == null )
			newadmTlScrollmsgmst.setSmsgNoexpiry("N");
		
		if( newadmTlScrollmsgmst.getSmsgNoofocc() == null )
			newadmTlScrollmsgmst.setSmsgNoofocc("1");
		
		if( newadmTlScrollmsgmst.getSmsgDwmy() == null )
			newadmTlScrollmsgmst.setSmsgDwmy("N");
		
		if( newadmTlScrollmsgmst.getSmsgOptionno() == null )
			newadmTlScrollmsgmst.setSmsgOptionno("1");
		
		if( newadmTlScrollmsgmst.getSmsgRecurrencefreq() == null )
			newadmTlScrollmsgmst.setSmsgRecurrencefreq("1");
		
		if( newadmTlScrollmsgmst.getSmsgWeekday() == null )
			newadmTlScrollmsgmst.setSmsgWeekday("1");
		
		if( newadmTlScrollmsgmst.getSmsgWeekno() == null )
			newadmTlScrollmsgmst.setSmsgWeekno("1");
		
		if( newadmTlScrollmsgmst.getSmsgDayonmonth() == null )
			newadmTlScrollmsgmst.setSmsgDayonmonth("1");
		
		if( newadmTlScrollmsgmst.getSmsgMonthonyear() == null )
			newadmTlScrollmsgmst.setSmsgMonthonyear("4");
		
		if( newadmTlScrollmsgmst.getSmsgRegenno() == null )
			newadmTlScrollmsgmst.setSmsgRegenno("1");  
			
		if( newadmTlScrollmsgmst.getSmsgAdvanced() == null )
			newadmTlScrollmsgmst.setSmsgAdvanced("1");
		
		if( newadmTlScrollmsgmst.getSmsgRange() == null )
			newadmTlScrollmsgmst.setSmsgRange("1");
		
		if( newadmTlScrollmsgmst.getSmsgIstobedisplayed() == null )
			newadmTlScrollmsgmst.setSmsgIstobedisplayed("Y");
		
		if( newadmTlScrollmsgmst.getSmsgDisAlllevel() == null )
			newadmTlScrollmsgmst.setSmsgDisAlllevel("N");
		
		if( newadmTlScrollmsgmst.getSmsgTempfield2() == null )
			newadmTlScrollmsgmst.setSmsgTempfield2("-");
		
		if( newadmTlScrollmsgmst.getSmsgTempfield3() == null )
			newadmTlScrollmsgmst.setSmsgTempfield3("-");
		
		if( newadmTlScrollmsgmst.getSmsgTempfield4() == null )
			newadmTlScrollmsgmst.setSmsgTempfield4("-");		
	}
	
	@Override
	public String Functionallocn(String Flid) throws Exception {
		// TODO Auto-generated method stub
		return dashboardDao.Functionallocn(Flid);
	}
	public String FunctionallocnDMTID(String DMTId)throws Exception{
		 return dashboardDao.FunctionallocnDMTID(DMTId);
	}

	public String FunctionallocnJHID(String Flid)throws Exception{
		return dashboardDao.FunctionallocnJHID(Flid);
	}
	public String LocationName(String Location)throws Exception{
		return dashboardDao.LocationName(Location);
	}
	public String getDmtFlid(String Flid)throws Exception{
	    return this.dashboardDao.getDmtFlid(Flid);	
	}

public Workbook NewDashboardExcelView(String flid,String fromMonth,String toMonth,String FirstMonth,
		String FYearStart,String FYearEnd,String QuarterFirst1,String QuarterSecMonth,
		String QuarterEnd,String Location,String Dept,String DMTname,String DmtOriginalId,String date,String format, String path,String JHkeyid,String FromDate,String ToDate,String StartMonth,String EndMonth,String Finance) throws IOException, SQLException, Exception{
		List<String[]> EHSMAtrixData=dashboardDao.EHSMAtrixDetail(flid,FYearStart,FYearEnd,FromDate,ToDate,Finance);
		List<String[]> AETAdhrenceData=dashboardDao.AetAdherence(DmtOriginalId,FYearStart,FYearEnd,FromDate,ToDate,Finance);
		List<String[]> PACTAdhrenceData=dashboardDao.PactAdherence(flid,FYearStart,FYearEnd,FromDate,ToDate,Finance);
		List<String[]> MOMListData=dashboardDao.MOMReviewDetail(flid,QuarterFirst1,QuarterEnd,FromDate,ToDate,Finance);
		List<String[]> KaizenData=dashboardDao.KaizenStatusDetail(flid,FYearStart,FYearEnd,FromDate,ToDate,Finance);
		List<String[]> AbnormalityData=dashboardDao.AbnormalityStatusDetail(flid,QuarterFirst1,QuarterSecMonth,QuarterEnd,FYearStart,FYearEnd,date,FromDate,ToDate,Finance);
		List<String[]> TransactionData=dashboardDao.TransactionDetail(flid,FYearStart,FYearEnd,FromDate,ToDate,Finance);
		List<String[]> AbnormalityPendingData=dashboardDao.AbnormalityPendingDetail(flid,FYearStart,FYearEnd,JHkeyid,FromDate,ToDate,Finance);
		List<String[]> AetPercentage=dashboardDao.AetAdherencePercentage(DmtOriginalId,FYearStart,FYearEnd,FromDate,ToDate,Finance);  
		List<String[]> PActPercentage=dashboardDao.PActAdherencePercentage(flid,FYearStart,FYearEnd,FromDate,ToDate,Finance);
		List<String[]> funclcnData = dashboardDao.getReportFunctllocn(flid);	
	    Workbook wb=(new NewDashboardExcelTemplate(dbActionTemplate)).fillValues(EHSMAtrixData,AETAdhrenceData,PACTAdhrenceData,MOMListData,KaizenData,
	    		AbnormalityData,TransactionData,AbnormalityPendingData,AetPercentage,PActPercentage,funclcnData,path,fromMonth,toMonth,FirstMonth,FYearStart,FYearEnd,QuarterFirst1,
	    		QuarterEnd,Location,Dept,DMTname,JHkeyid,FromDate,ToDate,Finance);
	    return wb;
	}	
}