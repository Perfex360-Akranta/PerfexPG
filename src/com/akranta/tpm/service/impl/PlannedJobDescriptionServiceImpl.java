package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PlannedJobDescBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.PlannedJobDescriptionDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.PlannedJobDescriptionDaoImpl;
import com.akranta.tpm.exportreport.PlannedJobObserExcelTemplate;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.SheTlPlannedjobobservation;
import com.akranta.tpm.model.SheTlPlnjobemplink;
import com.akranta.tpm.service.PlannedJobDescriptionService;
import com.akranta.tpm.service.api.PlannedJobObservationServiceApi;
//import com.akranta.tpm.service.api.WhywhyServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class PlannedJobDescriptionServiceImpl  implements PlannedJobDescriptionService{
	 private PlannedJobDescriptionDao plannedJobDescriptionDao;
	 private CommonFilterDao commonFilterDao;
	 DBActionTemplate  dbActionTemplate ; 
	 private Validations validation;
	 private PlannedJobObservationServiceApi plannedjobobservationserviceapi;
	 
	 
	 public void PlannedJobDescriptionServiceImplJwt(String JwtToken){
	    	try{
	    		plannedJobDescriptionDao.PlannedJobDescriptionDaoImplJwt(JwtToken);
	    		plannedjobobservationserviceapi = new PlannedJobObservationServiceApi(JwtToken);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	        // TODO Auto-generated constructor stub
	    }
	 
	 public PlannedJobDescriptionServiceImpl(DBActionTemplate dbActionTemplate) {
		 plannedJobDescriptionDao=new PlannedJobDescriptionDaoImpl(dbActionTemplate);
		 commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
		 this.dbActionTemplate  =dbActionTemplate ; 
		 validation = new Validations();
	 }

	@Override
	public SheTlPlannedjobobservation create(
			SheTlPlannedjobobservation newSheTlPlannedjobobservation,
			SheTlPlannedjobobservation existSheTlPlannedjobobservation, PlannedJobDescBean plannedJobDescBean)
			throws Exception,IOException,ValidationExceptions,BusinessApplicationExceptions {
		validation.validate(newSheTlPlannedjobobservation, "Plannedjobobservation","create");
		fillValues(newSheTlPlannedjobobservation,existSheTlPlannedjobobservation,plannedJobDescBean);
		// plannedJobDescriptionDao.create(newSheTlPlannedjobobservation);
		return plannedjobobservationserviceapi.savePlnjobemplink(newSheTlPlannedjobobservation);
		
	}
	@Override
	public SheTlPlannedjobobservation update(
			SheTlPlannedjobobservation newSheTlPlannedjobobservation,
			SheTlPlannedjobobservation existSheTlPlannedjobobservation,PlannedJobDescBean plannedJobDescBean)
			throws Exception,IOException,ValidationExceptions,BusinessApplicationExceptions {
		validation.validate(newSheTlPlannedjobobservation, "Plannedjobobservation","update");
		fillValues(newSheTlPlannedjobobservation,existSheTlPlannedjobobservation,plannedJobDescBean);
		//return plannedJobDescriptionDao.update(newSheTlPlannedjobobservation);
		return plannedjobobservationserviceapi.savePlnjobemplink(newSheTlPlannedjobobservation);
	}
	@Override
	public SheTlPlannedjobobservation delete(
			SheTlPlannedjobobservation sheTlPlannedjobobservation) throws Exception {
		//return plannedJobDescriptionDao.delete(sheTlPlannedjobobservation);
		return plannedjobobservationserviceapi.deletePlannedJobObservation(sheTlPlannedjobobservation);
	}

	/*
	 * private SheTlPlannedjobobservation fillvalues( SheTlPlannedjobobservation
	 * newSheTlPlannedjobobservation, SheTlPlannedjobobservation
	 * existSheTlPlannedjobobservation, PlannedJobDescBean plannedJobDescBean)
	 * throws Exception {
	 * 
	 * CommonFunctions.debugMsg("inside fillvalues "); String dateTime =
	 * CommonFunctions.pg_dateTimeNow();
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobActive()))
	 * newSheTlPlannedjobobservation.setPjobActive("Y");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobCorrections()))
	 * newSheTlPlannedjobobservation.setPjobCorrections("{}");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobCreatedby()))
	 * newSheTlPlannedjobobservation.setPjobCreatedby("{}");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobCreatedon()))
	 * newSheTlPlannedjobobservation.setPjobCreatedon(dateTime); String date2 =
	 * newSheTlPlannedjobobservation.getPjobFollowUpDate();
	 * newSheTlPlannedjobobservation.setPjobFollowUpDate(CommonFunctions.
	 * pg_getDateTimeFromDate(date2));
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobDate()))
	 * newSheTlPlannedjobobservation.setPjobDate(Constants.pgPassNullDateTime);
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobDeviations()))
	 * newSheTlPlannedjobobservation.setPjobDeviations("{}");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobEmployeeid()))
	 * newSheTlPlannedjobobservation.setPjobEmployeeid("{}");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobFlid()))
	 * newSheTlPlannedjobobservation.setPjobFlid("{}");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobActionplanId())
	 * ) newSheTlPlannedjobobservation.setPjobActionplanId("{}");
	 * if(UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobFlid())){ String
	 * elementId = plannedJobDescriptionDao.getElementID
	 * (newSheTlPlannedjobobservation.getPjobFlid());
	 * newSheTlPlannedjobobservation.setPjobElementid(elementId); }else
	 * newSheTlPlannedjobobservation.setPjobElementid("{}");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobFollowUpAction(
	 * ))) newSheTlPlannedjobobservation.setPjobFollowUpAction("{}");
	 * 
	 * String date = newSheTlPlannedjobobservation.getPjobDate();
	 * newSheTlPlannedjobobservation.setPjobDate(CommonFunctions.
	 * pg_getDateTimeFromDate(date));
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobFollowUpDate())
	 * ) newSheTlPlannedjobobservation.setPjobFollowUpDate(Constants.
	 * pgFutureNullDateTime);
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobFollowUpDone())
	 * ) newSheTlPlannedjobobservation.setPjobFollowUpDone("{}");
	 * 
	 * CommonFunctions.debugMsg(plannedJobDescBean.getPjobIsPropertyDamageY()
	 * +"  FOR plannedJobDescBean Y  "+plannedJobDescBean.getPjobIsFollowUpY()+"  "
	 * +plannedJobDescBean.getPjobIsEfficientY()+"   "+plannedJobDescBean.
	 * getPjobIsComplementedY()+" in fillvalues "+plannedJobDescBean.
	 * getPjobIsComplyY() + "   "+plannedJobDescBean.getPjobIsDetrimentalY());
	 * CommonFunctions.debugMsg(plannedJobDescBean.getPjobIsPropertyDamageN()
	 * +"  FOR plannedJobDescBean N  "+plannedJobDescBean.getPjobIsFollowUpN()+"  "
	 * +plannedJobDescBean.getPjobIsEfficientN()+"   "+plannedJobDescBean.
	 * getPjobIsComplementedN()+" in fillvalues "+plannedJobDescBean.
	 * getPjobIsComplyN() + "   "+plannedJobDescBean.getPjobIsDetrimentalN());
	 * 
	 * if(UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsComplementedN()))
	 * newSheTlPlannedjobobservation.setPjobIsComplemented(plannedJobDescBean.
	 * getPjobIsComplementedN()); else
	 * if(UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsComplementedY()))
	 * newSheTlPlannedjobobservation.setPjobIsComplemented(plannedJobDescBean.
	 * getPjobIsComplementedY());
	 * 
	 * if(UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsComplyN()))
	 * newSheTlPlannedjobobservation.setPjobIsComply(plannedJobDescBean.
	 * getPjobIsComplyN()); else
	 * if(UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsComplyY()))
	 * newSheTlPlannedjobobservation.setPjobIsComply(plannedJobDescBean.
	 * getPjobIsComplyY());
	 * 
	 * if(UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsDetrimentalN()))
	 * newSheTlPlannedjobobservation.setPjobIsDetrimental(plannedJobDescBean.
	 * getPjobIsDetrimentalN()); else
	 * if(UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsDetrimentalY()))
	 * newSheTlPlannedjobobservation.setPjobIsDetrimental(plannedJobDescBean.
	 * getPjobIsDetrimentalY());
	 * 
	 * if(UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsEfficientN()))
	 * newSheTlPlannedjobobservation.setPjobIsEfficient(plannedJobDescBean.
	 * getPjobIsEfficientN()); else
	 * if(UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsEfficientY()))
	 * newSheTlPlannedjobobservation.setPjobIsEfficient(plannedJobDescBean.
	 * getPjobIsEfficientY());
	 * 
	 * if(UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsFollowUpN()))
	 * newSheTlPlannedjobobservation.setPjobIsFollowUp(plannedJobDescBean.
	 * getPjobIsFollowUpN()); else
	 * if(UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsFollowUpY()))
	 * newSheTlPlannedjobobservation.setPjobIsFollowUp(plannedJobDescBean.
	 * getPjobIsFollowUpY());
	 * 
	 * if(UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsPropertyDamageN()))
	 * newSheTlPlannedjobobservation.setPjobIsPropertyDamage(plannedJobDescBean.
	 * getPjobIsPropertyDamageN()); else
	 * if(UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsPropertyDamageY()))
	 * newSheTlPlannedjobobservation.setPjobIsPropertyDamage(plannedJobDescBean.
	 * getPjobIsPropertyDamageY());
	 * 
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobIsComplemented(
	 * ))) newSheTlPlannedjobobservation.setPjobIsComplemented("X");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobIsComply()))
	 * newSheTlPlannedjobobservation.setPjobIsComply("X");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobIsDetrimental()
	 * )) newSheTlPlannedjobobservation.setPjobIsDetrimental("X");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobIsEfficient()))
	 * newSheTlPlannedjobobservation.setPjobIsEfficient("X");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobIsFollowUp()))
	 * newSheTlPlannedjobobservation.setPjobIsFollowUp("X");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.
	 * getPjobIsPropertyDamage()))
	 * newSheTlPlannedjobobservation.setPjobIsPropertyDamage("X");
	 * 
	 * CommonFunctions.debugMsg(newSheTlPlannedjobobservation.
	 * getPjobIsPropertyDamage()+"   "+newSheTlPlannedjobobservation.
	 * getPjobIsFollowUp()+"  "+newSheTlPlannedjobobservation.getPjobIsEfficient()
	 * +"   "+newSheTlPlannedjobobservation.getPjobIsComplemented()
	 * +" in fillvalues "+newSheTlPlannedjobobservation.getPjobIsComply() +
	 * "   "+newSheTlPlannedjobobservation.getPjobIsDetrimental());
	 * 
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.
	 * getPjobLocationDescription()))
	 * newSheTlPlannedjobobservation.setPjobLocationDescription("{}");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobManager()))
	 * newSheTlPlannedjobobservation.setPjobManager("{}");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobModifiedon()))
	 * newSheTlPlannedjobobservation.setPjobModifiedon(dateTime);
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobNotification())
	 * ) newSheTlPlannedjobobservation.setPjobNotification("-");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobReasonid()))
	 * newSheTlPlannedjobobservation.setPjobReasonid("{}");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.
	 * getPjobStandardProcedure()))
	 * newSheTlPlannedjobobservation.setPjobStandardProcedure("{}");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobTaskObserved())
	 * ) newSheTlPlannedjobobservation.setPjobTaskObserved("{}"); String
	 * practicecond="";
	 * if(UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobAppPPE()))
	 * practicecond+=newSheTlPlannedjobobservation.getPjobAppPPE()+",";
	 * if(UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobChecklist()))
	 * practicecond+=newSheTlPlannedjobobservation.getPjobChecklist()+",";
	 * if(UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobCheckWorker()))
	 * practicecond+=newSheTlPlannedjobobservation.getPjobCheckWorker()+",";
	 * if(UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobPositioning()))
	 * practicecond+=newSheTlPlannedjobobservation.getPjobPositioning()+",";
	 * if(practicecond.length()>0) practicecond=practicecond.substring(0,
	 * practicecond.length()-1); if(UIUtils.isValidKeyId(practicecond))
	 * newSheTlPlannedjobobservation.setPjobPracticecond(practicecond); else
	 * newSheTlPlannedjobobservation.setPjobPracticecond("{}");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobFollowuppjono()
	 * )) newSheTlPlannedjobobservation.setPjobFollowuppjono("-");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.
	 * getPjobFollowuptaskobserved()))
	 * newSheTlPlannedjobobservation.setPjobFollowuptaskobserved("-");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobSopNo()))
	 * newSheTlPlannedjobobservation.setPjobSopNo("-");
	 * if(!UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobTempfield5()))
	 * newSheTlPlannedjobobservation.setPjobTempfield5((
	 * newSheTlPlannedjobobservation.getPjobTempfield5()));
	 * 
	 * return newSheTlPlannedjobobservation; }
	 */
	private SheTlPlannedjobobservation fillValues( SheTlPlannedjobobservation newSheTlPlannedjobobservation,SheTlPlannedjobobservation existSheTlPlannedjobobservation,
	        PlannedJobDescBean plannedJobDescBean) {

	    CommonFunctions.debugMsg("fillValues SheTlPlannedjobobservation");
	    String dateTime = CommonFunctions.pg_dateTimeNow();

	    // Date conversions
	    String date = newSheTlPlannedjobobservation.getPjobDate();
	    newSheTlPlannedjobobservation.setPjobDate(CommonFunctions.pg_getDateTimeFromDate(date));

	    String followUpDate = newSheTlPlannedjobobservation.getPjobFollowUpDate();
	    newSheTlPlannedjobobservation.setPjobFollowUpDate(CommonFunctions.pg_getDateTimeFromDate(followUpDate));

	    // Always set
	    newSheTlPlannedjobobservation.setPjobActive("Y");
	    newSheTlPlannedjobobservation.setPjobCreatedon(dateTime);
	    newSheTlPlannedjobobservation.setPjobModifiedon(dateTime);

	    // Null checks
	    if (newSheTlPlannedjobobservation.getPjobKeyid() == null)
	        newSheTlPlannedjobobservation.setPjobKeyid(null);
	    if (newSheTlPlannedjobobservation.getPjobFlid() == null)
	        newSheTlPlannedjobobservation.setPjobFlid("{}");
	    if (newSheTlPlannedjobobservation.getPjobElementid() == null)
	        newSheTlPlannedjobobservation.setPjobElementid("{}");
	    if (newSheTlPlannedjobobservation.getPjobEmployeeid() == null)
	        newSheTlPlannedjobobservation.setPjobEmployeeid("{}");
	    if (newSheTlPlannedjobobservation.getPjobDate() == null)
	        newSheTlPlannedjobobservation.setPjobDate(dateTime);
	    if (newSheTlPlannedjobobservation.getPjobLocationDescription() == null)
	        newSheTlPlannedjobobservation.setPjobLocationDescription("{}");
	    if (newSheTlPlannedjobobservation.getPjobTaskObserved() == null)
	        newSheTlPlannedjobobservation.setPjobTaskObserved("{}");
	    if (newSheTlPlannedjobobservation.getPjobType() == null)
	        newSheTlPlannedjobobservation.setPjobType("{}");
	    if (newSheTlPlannedjobobservation.getPjobNotification() == null)
	        newSheTlPlannedjobobservation.setPjobNotification("-");
	    if (newSheTlPlannedjobobservation.getPjobReasonid() == null)
	        newSheTlPlannedjobobservation.setPjobReasonid("{}");

	    // ===== Y/N flag fields from PlannedJobDescBean =====
	    CommonFunctions.debugMsg(plannedJobDescBean.getPjobIsPropertyDamageY() + "  FOR plannedJobDescBean Y  "
	            + plannedJobDescBean.getPjobIsFollowUpY() + "  " + plannedJobDescBean.getPjobIsEfficientY()
	            + "   " + plannedJobDescBean.getPjobIsComplementedY() + " in fillvalues "
	            + plannedJobDescBean.getPjobIsComplyY() + "   " + plannedJobDescBean.getPjobIsDetrimentalY());
	    CommonFunctions.debugMsg(plannedJobDescBean.getPjobIsPropertyDamageN() + "  FOR plannedJobDescBean N  "
	            + plannedJobDescBean.getPjobIsFollowUpN() + "  " + plannedJobDescBean.getPjobIsEfficientN()
	            + "   " + plannedJobDescBean.getPjobIsComplementedN() + " in fillvalues "
	            + plannedJobDescBean.getPjobIsComplyN() + "   " + plannedJobDescBean.getPjobIsDetrimentalN());

	    if (UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsComplementedN()))
	        newSheTlPlannedjobobservation.setPjobIsComplemented(plannedJobDescBean.getPjobIsComplementedN());
	    else if (UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsComplementedY()))
	        newSheTlPlannedjobobservation.setPjobIsComplemented(plannedJobDescBean.getPjobIsComplementedY());

	    if (UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsComplyN()))
	        newSheTlPlannedjobobservation.setPjobIsComply(plannedJobDescBean.getPjobIsComplyN());
	    else if (UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsComplyY()))
	        newSheTlPlannedjobobservation.setPjobIsComply(plannedJobDescBean.getPjobIsComplyY());

	    if (UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsDetrimentalN()))
	        newSheTlPlannedjobobservation.setPjobIsDetrimental(plannedJobDescBean.getPjobIsDetrimentalN());
	    else if (UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsDetrimentalY()))
	        newSheTlPlannedjobobservation.setPjobIsDetrimental(plannedJobDescBean.getPjobIsDetrimentalY());

	    if (UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsEfficientN()))
	        newSheTlPlannedjobobservation.setPjobIsEfficient(plannedJobDescBean.getPjobIsEfficientN());
	    else if (UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsEfficientY()))
	        newSheTlPlannedjobobservation.setPjobIsEfficient(plannedJobDescBean.getPjobIsEfficientY());

	    if (UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsFollowUpN()))
	        newSheTlPlannedjobobservation.setPjobIsFollowUp(plannedJobDescBean.getPjobIsFollowUpN());
	    else if (UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsFollowUpY()))
	        newSheTlPlannedjobobservation.setPjobIsFollowUp(plannedJobDescBean.getPjobIsFollowUpY());

	    if (UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsPropertyDamageN()))
	        newSheTlPlannedjobobservation.setPjobIsPropertyDamage(plannedJobDescBean.getPjobIsPropertyDamageN());
	    else if (UIUtils.isValidKeyId(plannedJobDescBean.getPjobIsPropertyDamageY()))
	        newSheTlPlannedjobobservation.setPjobIsPropertyDamage(plannedJobDescBean.getPjobIsPropertyDamageY());

	    // Fallback defaults for Y/N flags if still not set
	    if (newSheTlPlannedjobobservation.getPjobIsComplemented() == null)
	        newSheTlPlannedjobobservation.setPjobIsComplemented("X");
	    if (newSheTlPlannedjobobservation.getPjobIsComply() == null)
	        newSheTlPlannedjobobservation.setPjobIsComply("X");
	    if (newSheTlPlannedjobobservation.getPjobIsDetrimental() == null)
	        newSheTlPlannedjobobservation.setPjobIsDetrimental("X");
	    if (newSheTlPlannedjobobservation.getPjobIsEfficient() == null)
	        newSheTlPlannedjobobservation.setPjobIsEfficient("X");
	    if (newSheTlPlannedjobobservation.getPjobIsFollowUp() == null)
	        newSheTlPlannedjobobservation.setPjobIsFollowUp("X");
	    if (newSheTlPlannedjobobservation.getPjobIsPropertyDamage() == null)
	        newSheTlPlannedjobobservation.setPjobIsPropertyDamage("X");

	    CommonFunctions.debugMsg(newSheTlPlannedjobobservation.getPjobIsPropertyDamage() + "   "
	            + newSheTlPlannedjobobservation.getPjobIsFollowUp() + "  "
	            + newSheTlPlannedjobobservation.getPjobIsEfficient() + "   "
	            + newSheTlPlannedjobobservation.getPjobIsComplemented() + " in fillvalues "
	            + newSheTlPlannedjobobservation.getPjobIsComply() + "   "
	            + newSheTlPlannedjobobservation.getPjobIsDetrimental());

	    // Remaining null checks
	    if (newSheTlPlannedjobobservation.getPjobCorrections() == null)
	        newSheTlPlannedjobobservation.setPjobCorrections("{}");
	    if (newSheTlPlannedjobobservation.getPjobDeviations() == null)
	        newSheTlPlannedjobobservation.setPjobDeviations("{}");
	    if (newSheTlPlannedjobobservation.getPjobStandardProcedure() == null)
	        newSheTlPlannedjobobservation.setPjobStandardProcedure("{}");
	    if (newSheTlPlannedjobobservation.getPjobManager() == null)
	        newSheTlPlannedjobobservation.setPjobManager("{}");
	    if (newSheTlPlannedjobobservation.getPjobFollowUpAction() == null)
	        newSheTlPlannedjobobservation.setPjobFollowUpAction("{}");
	    if (newSheTlPlannedjobobservation.getPjobFollowUpDate() == null)
	        newSheTlPlannedjobobservation.setPjobFollowUpDate(dateTime);
	    if (newSheTlPlannedjobobservation.getPjobFollowUpDone() == null)
	        newSheTlPlannedjobobservation.setPjobFollowUpDone("{}");

	    // practicecond — built from individual checkbox fields
	    String practicecond = "";
	    if (UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobAppPPE()))
	        practicecond += newSheTlPlannedjobobservation.getPjobAppPPE() + ",";
	    if (UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobChecklist()))
	        practicecond += newSheTlPlannedjobobservation.getPjobChecklist() + ",";
	    if (UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobCheckWorker()))
	        practicecond += newSheTlPlannedjobobservation.getPjobCheckWorker() + ",";
	    if (UIUtils.isValidKeyId(newSheTlPlannedjobobservation.getPjobPositioning()))
	        practicecond += newSheTlPlannedjobobservation.getPjobPositioning() + ",";
	    if (practicecond.length() > 0)
	        practicecond = practicecond.substring(0, practicecond.length() - 1);
	    if (UIUtils.isValidKeyId(practicecond))
	        newSheTlPlannedjobobservation.setPjobPracticecond(practicecond);
	    else
	        newSheTlPlannedjobobservation.setPjobPracticecond("{}");

	    if (newSheTlPlannedjobobservation.getPjobFollowuppjono() == null)
	        newSheTlPlannedjobobservation.setPjobFollowuppjono("-");
	    if (newSheTlPlannedjobobservation.getPjobFollowuptaskobserved() == null)
	        newSheTlPlannedjobobservation.setPjobFollowuptaskobserved("-");
	    if (newSheTlPlannedjobobservation.getPjobSopNo() == null)
	        newSheTlPlannedjobobservation.setPjobSopNo("-");
	    if (newSheTlPlannedjobobservation.getPjobTempfield5() == null)
	        newSheTlPlannedjobobservation.setPjobTempfield5("{}");
	    if (newSheTlPlannedjobobservation.getPjobCreatedby() == null)
	        newSheTlPlannedjobobservation.setPjobCreatedby("{}");
	    if (newSheTlPlannedjobobservation.getPjobActionplanId() == null)
	        newSheTlPlannedjobobservation.setPjobActionplanId("{}");

	    // Fill details
	    newSheTlPlannedjobobservation.setSheTlPlnjobemplink(
	            fillValuesPlnjobemplink(newSheTlPlannedjobobservation));

	    return newSheTlPlannedjobobservation;
	}

	private List<SheTlPlnjobemplink> fillValuesPlnjobemplink(
	        SheTlPlannedjobobservation newSheTlPlannedjobobservation) {

	    String dateTime = CommonFunctions.pg_dateTimeNow();
	    List<SheTlPlnjobemplink> detailList = newSheTlPlannedjobobservation.getSheTlPlnjobemplink();

	    if (detailList != null) {
	        for (int i = 0; i <= detailList.size() - 1; i++) {

	            // Always set
	            detailList.get(i).setPjelCreatedon(dateTime);
	            detailList.get(i).setPjelModifiedon(dateTime);
	            detailList.get(i).setPjelCreatedby(newSheTlPlannedjobobservation.getPjobCreatedby());
	            detailList.get(i).setPjelActive("Y");

	            // Always set master ID
	            detailList.get(i).setPjelPjobKeyid(newSheTlPlannedjobobservation.getPjobKeyid());

	            // Null checks
	            if (detailList.get(i).getPjelPjobEmployeeid() == null)
	                detailList.get(i).setPjelPjobEmployeeid("{}");
	            if (detailList.get(i).getPjelTempfield1() == null)
	                detailList.get(i).setPjelTempfield1("{}");
	            if (detailList.get(i).getPjelTempfield2() == null)
	                detailList.get(i).setPjelTempfield2("{}");
	            if (detailList.get(i).getPjelTempfield3() == null)
	                detailList.get(i).setPjelTempfield3("{}");
	            if (detailList.get(i).getPjelTempfield4() == null)
	                detailList.get(i).setPjelTempfield4("{}");
	        }
	    }

	    System.out.println("End Of fillValuesPlnjobemplink");
	    return detailList;
	}
	@Override
	public List<String[]> getmaingrid(CommonFilter commonFilter) throws Exception {
		return plannedJobDescriptionDao.getmaingrid(commonFilter);
	}

	@Override
	public Workbook getPlJobMainExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return  plannedJobDescriptionDao.getPlJobMainExcel(colmodel,format,commonFilter);
	}
	@Override
	public SheTlPlannedjobobservation getSelectedData(String Keyid) throws Exception {
		//return plannedJobDescriptionDao.getSelectedData(Keyid);
		return plannedjobobservationserviceapi.getPlannedJobObservationByKeyid(Keyid);
	}

	@Override
	public List<String[]> selectData(CommonFilter commonFilter) throws NoDataFoundException, SQLException, Exception {
		return plannedJobDescriptionDao.selectData(commonFilter);
	}

	@Override
	public Workbook plannedJobExcel(String keyid, String format, String path,
			String imagePath, CommonFilter commonFilter) throws Exception {
		Map<Integer, List<String[]>> plnData = plannedJobDescriptionDao.PlanExcel(keyid,format,commonFilter);
		CommonFunctions.debugMsg(" Inside Service Impl :: "+plnData+" Inside Service Impl :: "+plnData.get(1));
		Workbook wb=(new PlannedJobObserExcelTemplate(dbActionTemplate)).fillValues(plnData, format, path, keyid, imagePath);
		return wb;
	}
	
	@Override
	public List<String[]> getPojEmployee(String pjobKeyId) throws Exception {
		//return plannedJobDescriptionDao.getPojEmployee(pjobKeyId);
		return plannedjobobservationserviceapi.getPojEmployee(pjobKeyId);

	}
	
	@Override
	public String deleteDetail(String keyId, String gridId) throws Exception,BusinessApplicationExceptions{
		// TODO Auto-generated method stub
		//return plannedJobDescriptionDao.deleteDetail(keyId,gridId);
		return plannedjobobservationserviceapi.deleteDetail(keyId,gridId);
	}
	
	
	
	
    public SheTlPlannedjobobservation UpdateActionPlanId(String pojkeyid,String aplmkeyid)throws Exception{
    	return plannedJobDescriptionDao.UpdateActionPlanId(pojkeyid, aplmkeyid);
    	//return plannedjobobservationserviceapi.updateActionPlan(pojkeyid, aplmkeyid);
    }
    
    
    
	public List<String[]> getPJOEmployeeData(GridParams gridParams, CommonFilter commonFilter) throws Exception{
		return plannedJobDescriptionDao.getPJOEmployeeData(gridParams, commonFilter);
		
	}


}
