package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
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
import com.akranta.tpm.bean.EntTlProgCalendarBean;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlProgCalendarDao;
import com.akranta.tpm.dao.EntTlSelfnominationmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.EntTlProgCalendarDaoImpl;
import com.akranta.tpm.dao.impl.EntTlSelfnominationmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlProgCalendar;
import com.akranta.tpm.model.EntTlProgCalendar;
import com.akranta.tpm.model.EntTlSelfnominationmst;
import com.akranta.tpm.service.EntTlProgCalendarService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

/**
 * Servlet implementation class QtmTlCustcomplaintmstServiceImpl
 */
public class EntTlProgCalendarServiceImpl  implements EntTlProgCalendarService {
	private static final long serialVersionUID = 1L;       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private CommonFilterDao commonFilterDao;
	private EntTlProgCalendarDao EntTlProgCalendarDao;
	private EntTlSelfnominationmstDao entTlSelfnominationmstDao;
	private Validations validations ;
    public EntTlProgCalendarServiceImpl(DBActionTemplate dbActionTemplate){    	
        commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
        EntTlProgCalendarDao = new EntTlProgCalendarDaoImpl(dbActionTemplate);
        entTlSelfnominationmstDao =new EntTlSelfnominationmstDaoImpl(dbActionTemplate);
        validations = new Validations();
        // TODO Auto-generated constructor stub
    }

	
	public List<EntTlProgCalendar>  create(List<EntTlProgCalendar>  newEntTlProgCalendar,List<EntTlProgCalendar>  oldEntTlProgCalendar, 
			EntTlProgCalendarBean EntTlProgCalendarBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception {
		try 
		{
			/*String validationsFor;		
			validationsFor="create";
			validations.validate(newEntTlProgCalendar,"EntTlProgCalendar",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		*/
			FillValues(newEntTlProgCalendar,oldEntTlProgCalendar,EntTlProgCalendarBean);			
			return EntTlProgCalendarDao.create(newEntTlProgCalendar);
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

	public List<EntTlProgCalendar>  update(List<EntTlProgCalendar>  newEntTlProgCalendar,List<EntTlProgCalendar>  oldEntTlProgCalendar, 
			EntTlProgCalendarBean EntTlProgCalendarBean) throws ValidationExceptions,BusinessApplicationExceptions, Exception {
		//CommonMessage.debugMsg("Inside the ServiceImpl update");
		String validationsFor="update";
		validations.validate(newEntTlProgCalendar,"DockAuditCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		FillValues(newEntTlProgCalendar,oldEntTlProgCalendar,EntTlProgCalendarBean);		
		//CommonMessage.debugMsg("After Fill Values");
		return EntTlProgCalendarDao.update(newEntTlProgCalendar);	
	}	


	@Override
	public List<EntTlProgCalendar>  delete(List<EntTlProgCalendar>  newEntTlProgCalendar)throws Exception {
		// TODO Auto-generated method stub
		return this.EntTlProgCalendarDao.delete(newEntTlProgCalendar);
	}
	
	@Override
	public EntTlProgCalendar select(EntTlProgCalendar EntTlProgCalendar) throws Exception {	
		return this.EntTlProgCalendarDao.select(EntTlProgCalendar);
	}
	
	@Override
	public List<EntTlProgCalendar>  selectList(EntTlProgCalendar newEntTlProgCalendar)throws Exception {
		// TODO Auto-generated method stub
		return this.EntTlProgCalendarDao.selectList(newEntTlProgCalendar);
	}
	
	@Override
	public List<ComboBox> getProgramComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter program = commonFilter.getProgm();
		String spoke = commonFilter.getService();
		program.setIdField("PROG_KEYID");		
		program.setNameField("PROG_NAME");	
//		program.setCodeField("PROG_CODE");
		CommonMessage.debugMsg("spoke" + spoke);
		if(CommonFunctions.isValidKeyId(spoke))
			program.setCondSql(" and PROG_SPOKE_KEYID='"+ spoke + "' ");
		program.setTableName(TableNames.TBL_ENT_TL_PROGRAMMST);		
		return commonFilterDao.fillComboValues(program);
	}
	@Override
	public List<ComboBox> getSpokeComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		ComboFilter program = commonFilter.getProgm();		
		program.setIdField("SPOK_KEYID");		
		program.setNameField("SPOK_NAME");	
		program.setCodeField("SPOK_CODE");		
		program.setTableName(TableNames.TBL_ENT_TL_SPOKEMST);		
		return commonFilterDao.fillComboValues(program);
	}
	
	@Override
	public List<String[]> getDatesList(String year,int noOfMonths,String ProgId)throws Exception {
		// TODO Auto-generated method stub
		String month=null;		
		List<String[]> dateList=new ArrayList<String[]>();
		month=this.EntTlProgCalendarDao.getStartMonth();
		
		if (CommonFunctions.isValidKeyId(month))
		{
			dateList=this.EntTlProgCalendarDao.getDatesList(month+"-"+year,noOfMonths,ProgId);			
		}
		return dateList;
	}
	
	public List<String[]> selectSchedule(EntTlProgCalendar entTlProgCalendar)throws Exception{
		return this.EntTlProgCalendarDao.selectSchedule(entTlProgCalendar);
	}
	
	@Override
	public List<String[]> getProgCalendergridData(String year,int noOfMonths,String ProgId,String frmType)throws Exception {
		// TODO Auto-generated method stub
		String month=null;		
		List<String[]> dateList=new ArrayList<String[]>();
		month=this.EntTlProgCalendarDao.getStartMonth();		
		if (CommonFunctions.isValidKeyId(month))	
			dateList=this.EntTlProgCalendarDao.getProgCalendergridData(month+"-"+year,noOfMonths,ProgId,frmType);	
		return dateList;
	}
	
	private List<EntTlProgCalendar> FillValues(List<EntTlProgCalendar> newEntTlProgCalendar,
			List<EntTlProgCalendar> oldEntTlProgCalendar,EntTlProgCalendarBean EntTlProgCalendarBean) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ValidationExceptions {
		// TODO Auto-generated method stub
		String validationsFor;	
		 EntTlProgCalendar entTlProgCalendar=new EntTlProgCalendar();
		String dateTime = CommonFunctions.dateTimeNow();		
		if (newEntTlProgCalendar!= null && newEntTlProgCalendar.size() > 0) 
		{
			CommonMessage.debugMsg("iNSIDE fOR" +newEntTlProgCalendar.size());
			for(int i=0;i<=newEntTlProgCalendar.size()-1;i++)
			{				CommonMessage.debugMsg(i+"  iNSIDE fOR  " +newEntTlProgCalendar.size());

				entTlProgCalendar.setEcalProgId(newEntTlProgCalendar.get(i).getEcalProgId());
				entTlProgCalendar.setEcalPlanMonth(newEntTlProgCalendar.get(i).getEcalPlanMonth());
				validationsFor="create";
				validations.validate(entTlProgCalendar,"EntTlProgCalendar",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations			
				CommonMessage.debugMsg("getEcalKeyid:" + newEntTlProgCalendar.get(i).getEcalKeyid());
				if(!CommonFunctions.isValidKeyId(newEntTlProgCalendar.get(i).getEcalKeyid())){
					newEntTlProgCalendar.get(i).setEcalCreatedon(dateTime);
				}
				
				newEntTlProgCalendar.get(i).setEcalModifiedon(dateTime);
				//newEntTlProgCalendar.get(i).setEcalActive("Y");		
				if(newEntTlProgCalendar.get(i).getEcalActive() == null)
					newEntTlProgCalendar.get(i).setEcalActive("Y");
							
				if(newEntTlProgCalendar.get(i).getEcalPlanWeek() == null)
					newEntTlProgCalendar.get(i).setEcalPlanWeek("0");
				
				if(newEntTlProgCalendar.get(i).getEcalPlanAudiencecount() == null)
					newEntTlProgCalendar.get(i).setEcalPlanAudiencecount("0");
				
				if(newEntTlProgCalendar.get(i).getEcalRequestby() == null)
					newEntTlProgCalendar.get(i).setEcalRequestby("{}");
				
				if(newEntTlProgCalendar.get(i).getEcalRequestdate() == null)
					newEntTlProgCalendar.get(i).setEcalRequestdate(dateTime);
				
				if(newEntTlProgCalendar.get(i).getEcalRequestremarks() == null)
					newEntTlProgCalendar.get(i).setEcalRequestremarks("{}");
				
				if(newEntTlProgCalendar.get(i).getEcalApprovedflag() == null)
					newEntTlProgCalendar.get(i).setEcalApprovedflag("N");
				
				if(newEntTlProgCalendar.get(i).getEcalApprovedby() == null)
					newEntTlProgCalendar.get(i).setEcalApprovedby("{}");
				
				if(newEntTlProgCalendar.get(i).getEcalApproveddate() == null)
					newEntTlProgCalendar.get(i).setEcalApproveddate(dateTime);
					
				if(newEntTlProgCalendar.get(i).getEcalApprovedremarks() == null)
					newEntTlProgCalendar.get(i).setEcalApprovedremarks("{}");
				
				if(newEntTlProgCalendar.get(i).getEcalActualFromdate() == null)
					newEntTlProgCalendar.get(i).setEcalActualFromdate(dateTime);
				
				if(newEntTlProgCalendar.get(i).getEcalActualTilldate() == null)
					newEntTlProgCalendar.get(i).setEcalActualTilldate(dateTime);
				
				if(newEntTlProgCalendar.get(i).getEcalActualAudiencecount() == null)
					newEntTlProgCalendar.get(i).setEcalActualAudiencecount("0");
				
				if(newEntTlProgCalendar.get(i).getEcalStatus() == null)
					newEntTlProgCalendar.get(i).setEcalStatus("P");
				
				if(newEntTlProgCalendar.get(i).getEcalMonthwise() == null)
					newEntTlProgCalendar.get(i).setEcalMonthwise("Y");
				
				if(newEntTlProgCalendar.get(i).getEcalBudget() == null)
					newEntTlProgCalendar.get(i).setEcalBudget("0");
				
				if(newEntTlProgCalendar.get(i).getEcalTempfield2() == null)
					newEntTlProgCalendar.get(i).setEcalTempfield2("-");
				
				if(newEntTlProgCalendar.get(i).getEcalTempfield3() == null)
					newEntTlProgCalendar.get(i).setEcalTempfield3("-");
				
				if(newEntTlProgCalendar.get(i).getEcalTempfield4() == null)
					newEntTlProgCalendar.get(i).setEcalTempfield4("-");
				
				if(newEntTlProgCalendar.get(i).getEcalTempfield5() == null)
					newEntTlProgCalendar.get(i).setEcalTempfield5("-");
			}
		}
		return newEntTlProgCalendar;
	
	}


	@Override
	public List<String[]> getSelfNominationReport(CommonFilter commonFilter,String EmpId, String ProgId, String BatchId,String userid,String Status)throws Exception {
		// TODO Auto-generated method stub
		return EntTlProgCalendarDao.getSelfNominationReport(commonFilter,EmpId,ProgId,BatchId,userid,Status);
	}


	@Override
	public List<String[]> getSelfNominationPopUp(CommonFilter commonFilter,String empid)
			throws Exception {
		// TODO Auto-generated method stub
		return EntTlProgCalendarDao.getSelfNominationPopUp(commonFilter,empid);
	}


	@Override
	public List<EntTlSelfnominationmst> createNomination(List<EntTlSelfnominationmst> NominationList1,String nominatonDelete) throws Exception {
		CommonMessage.debugMsg("Create Block:::");
		try {
			//CommonMessage.debugMsg("Inside Create  "+NominationList1.get(0).getSnomBatchid());
			String validationsFor = "create";
			String xml = "TrainingNeed";
//			validations.validate(NominationList1.get(0),xml,validationsFor);	
			fillValuesMaster(NominationList1);	 
			//CommonMessage.debugMsg("lengt of List ::: "+NominationList1.size());
			CommonMessage.debugMsg("After Fill Values");
			return entTlSelfnominationmstDao.create(NominationList1,nominatonDelete);
		 } catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}
	}


	private List<EntTlSelfnominationmst>  fillValuesMaster(List<EntTlSelfnominationmst> nominationList1) {
	if(nominationList1!=null){
		for (EntTlSelfnominationmst entTlSelfnominationmst : nominationList1)
		{
			CommonMessage.debugMsg(" for loop Deatils fill values::::::");
			String dateTime = CommonFunctions.dateTimeNow();
			entTlSelfnominationmst.setSnomCreatedon(dateTime);			
			entTlSelfnominationmst.setSnomModifiedon(dateTime);			
			entTlSelfnominationmst.setSnomNominationdate(dateTime);
		   if(entTlSelfnominationmst.getSnomKeyid()== null) {			   
			   entTlSelfnominationmst.setSnomApproveddate(Constants.passNullDate);				
		   }
		   else{
			   entTlSelfnominationmst.setSnomApproveddate(dateTime);	
		   }
			entTlSelfnominationmst.setSnomActive("Y");			

			if (entTlSelfnominationmst.getSnomBatchid() == null)
				entTlSelfnominationmst.setSnomBatchid("{}");
			CommonMessage.debugMsg("Remarks :::: "+entTlSelfnominationmst.getSnomRemarks());
			if (entTlSelfnominationmst.getSnomRemarks() == null)
				entTlSelfnominationmst.setSnomRemarks("{}");
			
			if (entTlSelfnominationmst.getSnomProgid() == null)
				entTlSelfnominationmst.setSnomProgid("{}");

			if (entTlSelfnominationmst.getSnomFlid() == null)
				entTlSelfnominationmst.setSnomFlid("{}");												
						
			
			if (entTlSelfnominationmst.getSnomStatus() == null)
				entTlSelfnominationmst.setSnomStatus("N");
			
			
			if (entTlSelfnominationmst.getSnomApprovedremarks() == null)
				entTlSelfnominationmst.setSnomApprovedremarks("{}");
			
			if (entTlSelfnominationmst.getSnomTempfield1() == null)
				entTlSelfnominationmst.setSnomTempfield1("-");

			if (entTlSelfnominationmst.getSnomTempfield2() == null)
				entTlSelfnominationmst.setSnomTempfield2("-");

			if (entTlSelfnominationmst.getSnomTempfield3() == null)
				entTlSelfnominationmst.setSnomTempfield3("-");

			if (entTlSelfnominationmst.getSnomTempfield4() == null)
				entTlSelfnominationmst.setSnomTempfield4("-");
			
		}
	}
		CommonMessage.debugMsg("End Of  fillValues genTlMomdtl ");

		return nominationList1;	
	}


	@Override
	public List<EntTlSelfnominationmst> updateNomination(List<EntTlSelfnominationmst> NominationList1,List<EntTlSelfnominationmst> NominationDelete) throws Exception {
		// TODO Auto-generated method stub
		try {
			CommonMessage.debugMsg("Inside Create  "+NominationList1.get(0).getSnomBatchid());
			String validationsFor = "create";
			String xml = "TrainingNeed";
			validations.validate(NominationList1.get(0),xml,validationsFor);	
			fillValuesMaster(NominationList1);	 
			CommonMessage.debugMsg("lengt of List ::: "+NominationList1.size());
			CommonMessage.debugMsg("After Fill Values");
			return entTlSelfnominationmstDao.update(NominationList1,NominationDelete);
		 } catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}
	}


	@Override
	public List<String[]> getSelfNominationMainGrid(CommonFilter commonFilter,String empid) throws Exception {
		// TODO Auto-generated method stub
		return EntTlProgCalendarDao.getSelfNominationMainGrid(commonFilter,empid);
	}


	@Override
	public Workbook SelfNominationExcel(CommonFilter commonFilter,JSONObject colmodel, String format, String empid) throws IOException, SQLException, Exception {
		// TODO Auto-generated method stub
		return this.entTlSelfnominationmstDao.SelfNominationExcel(commonFilter,colmodel,format,empid);
	}


	@Override
	public List<EntTlSelfnominationmst> deleteSelf(List<EntTlSelfnominationmst> nominationList1) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlSelfnominationmstDao.delete(nominationList1);
	}

}
