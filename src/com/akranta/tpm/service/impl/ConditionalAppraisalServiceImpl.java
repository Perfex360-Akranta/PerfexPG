package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GenTlConditionalappraisalBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlConditionalappraisalDao;
import com.akranta.tpm.dao.PlmTlConditionalappraisalDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlConditionalappraisalDaoImpl;
import com.akranta.tpm.dao.impl.PlmTlConditionalappraisalDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlConditionalappraisal;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GenTlSparesmst;
import com.akranta.tpm.model.PlmTlConditionalappraisal;
import com.akranta.tpm.model.PlmTlConditionalappraisalEntry;
import com.akranta.tpm.model.PlmTlConditionalappraisalmst;
import com.akranta.tpm.model.PlmTlConditionalappraisalmstentry;
import com.akranta.tpm.service.ConditionalAppraisalService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.ConditionalAppraisalServiceApi;
import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;

public class ConditionalAppraisalServiceImpl implements ConditionalAppraisalService{
	
	private GenTlConditionalappraisalDao conditionalappraisalDao;
	private PlmTlConditionalappraisalDao plmTlConditionalappraisalDao;
	private CommonFilterDao commonFilterDao ;
	private ConditionalAppraisalServiceApi conditionalappraisalserviceapi;
	
	private Validations validations;
	
	public ConditionalAppraisalServiceImpl(DBActionTemplate dbActionTemplate) {
		
		conditionalappraisalDao = new GenTlConditionalappraisalDaoImpl(dbActionTemplate);
		plmTlConditionalappraisalDao = new PlmTlConditionalappraisalDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public void ConditionalAppraisalServiceImplJwt(String JwtToken){
		try{
			plmTlConditionalappraisalDao.PlmTlConditionalappraisalDaoImplJwt(JwtToken);
			conditionalappraisalserviceapi = new ConditionalAppraisalServiceApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    // TODO Auto-generated constructor stub
	}
	@Override
	public GenTlConditionalappraisal create(GenTlConditionalappraisal genTlConditionalappraisal,GenTlConditionalappraisal existGenTlConditionalappraisal,GenTlConditionalappraisalBean genTlConditionalappraisalBean)throws Exception {
		String validationsFor;
		validationsFor = "create";		
		String xml="ConditionalAppraisal";
		CommonMessage.debugMsg("Validation XML Name:::::::::"+xml);
		validations.validate(genTlConditionalappraisal,"ConditionalAppraisal","create");

		fillvalues(genTlConditionalappraisal,existGenTlConditionalappraisal,genTlConditionalappraisalBean);
		return conditionalappraisalDao.create(genTlConditionalappraisal);
	}
	@Override
	public GenTlConditionalappraisal update(
			GenTlConditionalappraisal genTlConditionalappraisal,
			GenTlConditionalappraisal existGenTlConditionalappraisal,
			GenTlConditionalappraisalBean genTlConditionalappraisalBean)
			throws Exception {
		String validationsFor;
		validationsFor = "update";		
		CommonMessage.debugMsg(genTlConditionalappraisal.getCdapCompletedby());
		String xml="ConditionalAppraisal";
		CommonMessage.debugMsg("Validation XML Name:::::::::"+xml);
		validations.validate(genTlConditionalappraisal,xml,validationsFor);
		fillvalues(genTlConditionalappraisal,existGenTlConditionalappraisal,genTlConditionalappraisalBean);
		return conditionalappraisalDao.update(genTlConditionalappraisal);
	}
	@Override
	public GenTlConditionalappraisal delete(
			GenTlConditionalappraisal genTlConditionalappraisal)
			throws Exception {
		return conditionalappraisalDao.delete(genTlConditionalappraisal);
	}
	
	private void fillvalues(GenTlConditionalappraisal genTlConditionalappraisal,
			GenTlConditionalappraisal existGenTlConditionalappraisal,
			GenTlConditionalappraisalBean genTlConditionalappraisalBean)throws Exception  {
		String dateTime = CommonFunctions.pg_dateTimeNow();
			genTlConditionalappraisal.setCdapCreatedon(dateTime);
		genTlConditionalappraisal.setCdapModifiedon(dateTime);
			if(genTlConditionalappraisal.getCdapActionrequired()==null)
			genTlConditionalappraisal.setCdapActionrequired("{}");
		if(genTlConditionalappraisal.getCdapActualconditin()==null)
			genTlConditionalappraisal.setCdapActualconditin("{}");
		if(genTlConditionalappraisal.getCdapActive()==null)
			genTlConditionalappraisal.setCdapActive("N");
		if(genTlConditionalappraisal.getCdapCheckingtool()==null)
			genTlConditionalappraisal.setCdapCheckingtool("{}");
		if(genTlConditionalappraisal.getCdapComponentid()==null)
			genTlConditionalappraisal.setCdapComponentid("{}");
		if(genTlConditionalappraisal.getCdapCreatedby()==null)
			genTlConditionalappraisal.setCdapCreatedby("{}");
		if(genTlConditionalappraisal.getCdapDimension()==null)
			genTlConditionalappraisal.setCdapDimension("{}");
		if(genTlConditionalappraisal.getCdapFlnid()==null)
			genTlConditionalappraisal.setCdapFlnid("{}");
		if(genTlConditionalappraisal.getCdapIdealcondition()==null)
			genTlConditionalappraisal.setCdapIdealcondition("{}");
		if(genTlConditionalappraisal.getCdapRemarks()==null)
			genTlConditionalappraisal.setCdapRemarks("{}");
		if(genTlConditionalappraisal.getCdapResponsibility()==null)
			genTlConditionalappraisal.setCdapResponsibility("{}");
		if(genTlConditionalappraisal.getCdapStatus()==null)
			genTlConditionalappraisal.setCdapStatus("Y");
		if(genTlConditionalappraisal.getCdapTargetdate()==null)
			genTlConditionalappraisal.setCdapTargetdate(dateTime);
		if(genTlConditionalappraisal.getCdapTempfield1()==null)
			genTlConditionalappraisal.setCdapTempfield1("N");
		if(genTlConditionalappraisal.getCdapTempfield2()==null)
			genTlConditionalappraisal.setCdapTempfield2("N");
		if(genTlConditionalappraisal.getCdapTempfield3()==null)
			genTlConditionalappraisal.setCdapTempfield3("N");
		if(genTlConditionalappraisal.getCdapTempfield4()==null)
			genTlConditionalappraisal.setCdapTempfield4("N");
		if(genTlConditionalappraisal.getCdapTypeofcheck()==null)
			genTlConditionalappraisal.setCdapTypeofcheck("{}");
		if(genTlConditionalappraisal.getCdapCompleteddate()==null)
			genTlConditionalappraisal.setCdapCompleteddate(dateTime);
		
		if(genTlConditionalappraisal.getCdapCompletedby()==null)
			genTlConditionalappraisal.setCdapCompletedby("{}");
	}
	@Override
	public PlmTlConditionalappraisalmst getAllFillControl(String keyId) throws Exception {	
		//return plmTlConditionalappraisalDao.getAllFillControl(keyId);
		return conditionalappraisalserviceapi.getCompleteConditionalAppraisalData(keyId);
		
	}
	@Override
	public List<String[]> getfillgriddata() throws Exception {
		// TODO Auto-generated method stub
		return conditionalappraisalDao.getfillgriddata();
	}
	@Override
	public List<String[]> getConditionalAppraisalGrid(CommonFilter commonFilter) throws Exception {
		
		return this.plmTlConditionalappraisalDao.getConditionalAppraisalGrid(commonFilter);
	}
	@Override
	public PlmTlConditionalappraisal create(
			PlmTlConditionalappraisal plmTlConditionalappraisal,
			PlmTlConditionalappraisal existPlmTlConditionalappraisal) throws Exception {
		String validationsFor="create";
		validations.validate(plmTlConditionalappraisal, "plmTlConditionalappValidation",validationsFor);
		fillValues(plmTlConditionalappraisal,existPlmTlConditionalappraisal);
		return plmTlConditionalappraisalDao.create(plmTlConditionalappraisal);
	}
	
	private PlmTlConditionalappraisal fillValues(
			PlmTlConditionalappraisal plmTlConditionalappraisal,
			PlmTlConditionalappraisal existPlmTlConditionalappraisal) {
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapComponentType()))
			plmTlConditionalappraisal.setCdapComponentType("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapComponentid()))
			plmTlConditionalappraisal.setCdapComponentid("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapNewcomponent()))
			plmTlConditionalappraisal.setCdapNewcomponent("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapDimension()))
			plmTlConditionalappraisal.setCdapDimension("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapCheckingtool()))
			plmTlConditionalappraisal.setCdapCheckingtool("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTypeofcheck()))
			plmTlConditionalappraisal.setCdapTypeofcheck("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapIdealtype()))
			plmTlConditionalappraisal.setCdapIdealtype("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapIdealminimum()))
			plmTlConditionalappraisal.setCdapIdealminimum("0");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapIdealmaximum()))
			plmTlConditionalappraisal.setCdapIdealmaximum("0");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapUom()))
			plmTlConditionalappraisal.setCdapUom("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapIdealcondition()))
			plmTlConditionalappraisal.setCdapIdealcondition("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapActualcondition()))
			plmTlConditionalappraisal.setCdapActualcondition("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapActualvalue()))
			plmTlConditionalappraisal.setCdapActualvalue("0");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapOknotok()))
			plmTlConditionalappraisal.setCdapOknotok("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapStatus()))
			plmTlConditionalappraisal.setCdapStatus("X");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapActionrequired()))
			plmTlConditionalappraisal.setCdapActionrequired("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapRefurbishmentStatus()))
			plmTlConditionalappraisal.setCdapRefurbishmentStatus("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield1()))
			plmTlConditionalappraisal.setCdapTempfield1("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield2()))
			plmTlConditionalappraisal.setCdapTempfield2("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield3()))
			plmTlConditionalappraisal.setCdapTempfield3("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield4()))
			plmTlConditionalappraisal.setCdapTempfield4("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield5()))
			plmTlConditionalappraisal.setCdapTempfield5("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield6()))
			plmTlConditionalappraisal.setCdapTempfield6("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield7()))
			plmTlConditionalappraisal.setCdapTempfield7("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapActive()))
			plmTlConditionalappraisal.setCdapActive("Y");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapModifiedon()))
			plmTlConditionalappraisal.setCdapModifiedon(dateTime);
		String createdOn = plmTlConditionalappraisal.getCdapCreatedon();
		plmTlConditionalappraisal.setCdapCreatedon(CommonFunctions.pg_getDateTimeFromDate(createdOn));
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapCreatedon()))
			plmTlConditionalappraisal.setCdapCreatedon(dateTime);
		return plmTlConditionalappraisal;
	}
	private PlmTlConditionalappraisalEntry fillValuesEntry(
			PlmTlConditionalappraisalEntry plmTlConditionalappraisal,
			PlmTlConditionalappraisalEntry existPlmTlConditionalappraisal) {
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapComponentType()))
			plmTlConditionalappraisal.setCdapComponentType("-");
		String condition=plmTlConditionalappraisal.getCdapComponentid();
		plmTlConditionalappraisal.setCdapComponentid(condition);
		
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapComponentid()))
			plmTlConditionalappraisal.setCdapComponentid("-");
		
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapNewcomponent()))
			plmTlConditionalappraisal.setCdapNewcomponent("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapDimension()))
			plmTlConditionalappraisal.setCdapDimension("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapCheckingtool()))
			plmTlConditionalappraisal.setCdapCheckingtool("-");
		String type=plmTlConditionalappraisal.getCdapTypeofcheck();
		plmTlConditionalappraisal.setCdapTypeofcheck(type);
		
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTypeofcheck()))
			plmTlConditionalappraisal.setCdapTypeofcheck("-");
		String ideal=plmTlConditionalappraisal.getCdapIdealtype();
		plmTlConditionalappraisal.setCdapIdealtype(ideal);
		
		
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapIdealtype()))
			plmTlConditionalappraisal.setCdapIdealtype("-");
		String min =plmTlConditionalappraisal.getCdapIdealminimum();
		plmTlConditionalappraisal.setCdapIdealminimum(min);
		
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapIdealminimum()))
			plmTlConditionalappraisal.setCdapIdealminimum("0");
		
		String max=plmTlConditionalappraisal.getCdapIdealmaximum();
		plmTlConditionalappraisal.setCdapIdealmaximum(max);
		
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapIdealmaximum()))
			plmTlConditionalappraisal.setCdapIdealmaximum("0");
		
		String capp=plmTlConditionalappraisal.getCdapUom();
		plmTlConditionalappraisal.setCdapUom(capp);
		
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapUom()))
			plmTlConditionalappraisal.setCdapUom("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapIdealcondition()))
			plmTlConditionalappraisal.setCdapIdealcondition("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapActualcondition()))
			plmTlConditionalappraisal.setCdapActualcondition("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapActualvalue()))
			plmTlConditionalappraisal.setCdapActualvalue("0");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapOknotok()))
			plmTlConditionalappraisal.setCdapOknotok("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapStatus()))
			plmTlConditionalappraisal.setCdapStatus("X");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapActionrequired()))
			plmTlConditionalappraisal.setCdapActionrequired("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapRefurbishmentStatus()))
			plmTlConditionalappraisal.setCdapRefurbishmentStatus("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapCdapkeyid()))
			plmTlConditionalappraisal.setCdapCdapkeyid("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield2()))
			plmTlConditionalappraisal.setCdapTempfield2("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield3()))
			plmTlConditionalappraisal.setCdapTempfield3("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield4()))
			plmTlConditionalappraisal.setCdapTempfield4("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield5()))
			plmTlConditionalappraisal.setCdapTempfield5("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield6()))
			plmTlConditionalappraisal.setCdapTempfield6("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapTempfield7()))
			plmTlConditionalappraisal.setCdapTempfield7("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapActive()))
			plmTlConditionalappraisal.setCdapActive("Y");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapModifiedon()))
			plmTlConditionalappraisal.setCdapModifiedon(dateTime);
		String date = plmTlConditionalappraisal.getCdapCreatedon();
		plmTlConditionalappraisal.setCdapCreatedon(CommonFunctions.pg_getDateTimeFromDate(date));
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapCreatedon()))
			plmTlConditionalappraisal.setCdapCreatedon(dateTime);
		return plmTlConditionalappraisal;
	}
	@Override
	public PlmTlConditionalappraisal update(
			PlmTlConditionalappraisal plmTlConditionalappraisal,
			PlmTlConditionalappraisal existPlmTlConditionalappraisal) throws Exception {
		String validationsFor="update";
		validations.validate(plmTlConditionalappraisal, "plmTlConditionalappValidation",validationsFor);
		fillValues(plmTlConditionalappraisal,existPlmTlConditionalappraisal);
		return plmTlConditionalappraisalDao.update(plmTlConditionalappraisal);
	}
	
	@Override
	public PlmTlConditionalappraisalmst create(
			PlmTlConditionalappraisalmst plmTlConditionalappraisalmst,
			PlmTlConditionalappraisalmst existPlmTlConditionalappraisalmst) throws Exception {
		String validationsFor="create";		
		validations.validate(plmTlConditionalappraisalmst, "plmTlConditionalappValidation",validationsFor);
		validations.validate(plmTlConditionalappraisalmst.getPlmTlConditionalappraisal(), "plmTlConditionalappValidation",validationsFor);
		fillValues(plmTlConditionalappraisalmst,existPlmTlConditionalappraisalmst);
		//return plmTlConditionalappraisalDao.create(plmTlConditionalappraisalmst);
		return conditionalappraisalserviceapi.saveConditionalAppraisal(plmTlConditionalappraisalmst);
		
		
	}
	public PlmTlConditionalappraisalmstentry createMstEntry(
			PlmTlConditionalappraisalmstentry  plmTlConditionalappraisalmst,
			PlmTlConditionalappraisalmstentry existPlmTlConditionalappraisalmst) throws Exception {
		String validationsFor="create";
		validations.validate(plmTlConditionalappraisalmst, "plmTlConditionalappValidation",validationsFor);
		validations.validate(plmTlConditionalappraisalmst.getPlmTlConditionalappraisal(), "plmTlConditionalappValidation",validationsFor);
		
		fillValuesMstEntry(plmTlConditionalappraisalmst,existPlmTlConditionalappraisalmst);
		//return plmTlConditionalappraisalDao.createEntry(plmTlConditionalappraisalmst);
		return conditionalappraisalserviceapi.saveConditionalAppraisalEntry(plmTlConditionalappraisalmst);
		
		
	}
	
	/*public PlmTlConditionalappraisalEntry createEntry(
			PlmTlConditionalappraisalEntry plmTlConditionalappraisalmst,
			PlmTlConditionalappraisalEntry  existPlmTlConditionalappraisalmst) throws Exception {
		String validationsFor="create";
		validations.validate(plmTlConditionalappraisalmst, "plmTlConditionalappValidation",validationsFor);
		validations.validate(plmTlConditionalappraisalmst.getPlmTlConditionalappraisal(), "plmTlConditionalappValidation",validationsFor);
		EntryfillValues(plmTlConditionalappraisalmst,existPlmTlConditionalappraisalmst);
		return plmTlConditionalappraisalDao.createEntry(plmTlConditionalappraisalmst);
	}*/
	private PlmTlConditionalappraisalmst fillValues(
			PlmTlConditionalappraisalmst plmTlConditionalappraisalmst,
			PlmTlConditionalappraisalmst existPlmTlConditionalappraisalmst) {
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamActive()))
			plmTlConditionalappraisalmst.setCdamActive("Y");
		
		String date = plmTlConditionalappraisalmst.getCdamDate();
		plmTlConditionalappraisalmst.setCdamDate(CommonFunctions.pg_getDateTimeFromDate(date));
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamDate()))
			plmTlConditionalappraisalmst.setCdamDate(Constants.pgFutureNullDateTime);
		
		String createdOn = plmTlConditionalappraisalmst.getCdamCreatedon();
		plmTlConditionalappraisalmst.setCdamCreatedon(CommonFunctions.pg_getDateTimeFromDate(createdOn));
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamCreatedon()))
			plmTlConditionalappraisalmst.setCdamCreatedon(dateTime);
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamModifiedon()))
			plmTlConditionalappraisalmst.setCdamModifiedon(dateTime);
		
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamFlid()))
			plmTlConditionalappraisalmst.setCdamFlid("{}");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamElementid()))
			plmTlConditionalappraisalmst.setCdamElementid("{}");
		
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield1()))
			plmTlConditionalappraisalmst.setCdamTempfield1("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield2()))
			plmTlConditionalappraisalmst.setCdamTempfield2("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield3()))
			plmTlConditionalappraisalmst.setCdamTempfield3("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield4()))
			plmTlConditionalappraisalmst.setCdamTempfield4("-");
		if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield5()))
			plmTlConditionalappraisalmst.setCdamTempfield5("-");
		if(plmTlConditionalappraisalmst.getPlmTlConditionalappraisal()!=null)
			plmTlConditionalappraisalmst.setPlmTlConditionalappraisal(fillValues(
					plmTlConditionalappraisalmst.getPlmTlConditionalappraisal(),
					plmTlConditionalappraisalmst.getPlmTlConditionalappraisal()));
		
		return plmTlConditionalappraisalmst;
	}

	/*
	 * private PlmTlConditionalappraisalmstentry fillValuesMstEntry(
	 * PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst,
	 * PlmTlConditionalappraisalmstentry existPlmTlConditionalappraisalmst) { String
	 * dateTime = CommonFunctions.pg_dateTimeNow();
	 * 
	 * if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamActive()))
	 * plmTlConditionalappraisalmst.setCdamActive("Y");
	 * 
	 * //String date = plmTlConditionalappraisalmst.getCdamDate();
	 * //plmTlConditionalappraisalmst.setCdamDate(CommonFunctions.
	 * pg_getDateFromTimeStamp(date));
	 * 
	 * if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamDate()))
	 * plmTlConditionalappraisalmst.setCdamDate(Constants.pgFutureNullDateTime);
	 * 
	 * String date2 = plmTlConditionalappraisalmst.getCdamCreatedon();
	 * plmTlConditionalappraisalmst.setCdamCreatedon(CommonFunctions.
	 * pg_getDateTimeFromDate(date2));
	 * 
	 * if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamCreatedon()))
	 * plmTlConditionalappraisalmst.setCdamCreatedon(dateTime);
	 * if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamModifiedon()))
	 * plmTlConditionalappraisalmst.setCdamModifiedon(dateTime);
	 * 
	 * if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamFlid()))
	 * plmTlConditionalappraisalmst.setCdamFlid("{}");
	 * if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamElementid()))
	 * plmTlConditionalappraisalmst.setCdamElementid("{}");
	 * 
	 * if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield1()))
	 * plmTlConditionalappraisalmst.setCdamTempfield1("-");
	 * if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield2()))
	 * plmTlConditionalappraisalmst.setCdamTempfield2("-");
	 * if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield3()))
	 * plmTlConditionalappraisalmst.setCdamTempfield3("-");
	 * if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield4()))
	 * plmTlConditionalappraisalmst.setCdamTempfield4("-");
	 * if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield5()))
	 * plmTlConditionalappraisalmst.setCdamTempfield5("-");
	 * if(plmTlConditionalappraisalmst.getPlmTlConditionalappraisal()!=null)
	 * 
	 * plmTlConditionalappraisalmst.setPlmTlConditionalappraisal(fillValuesEntry(
	 * plmTlConditionalappraisalmst.getPlmTlConditionalappraisal(),
	 * plmTlConditionalappraisalmst.getPlmTlConditionalappraisal()));
	 * 
	 * return plmTlConditionalappraisalmst; }
	 */
	
	private PlmTlConditionalappraisalmstentry fillValuesMstEntry(
	        PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst,
	        PlmTlConditionalappraisalmstentry existPlmTlConditionalappraisalmst) {
	    String dateTime = CommonFunctions.pg_dateTimeNow();
	    
	    if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamActive()))
	        plmTlConditionalappraisalmst.setCdamActive("Y");
	    
	    if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamDate()))
	        plmTlConditionalappraisalmst.setCdamDate(Constants.pgFutureNullDateTime);
	    
	    String date2 = plmTlConditionalappraisalmst.getCdamCreatedon();
	    plmTlConditionalappraisalmst.setCdamCreatedon(CommonFunctions.pg_getDateTimeFromDate(date2));
	    
	    if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamCreatedon()))
	        plmTlConditionalappraisalmst.setCdamCreatedon(dateTime);
	    if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamModifiedon()))
	        plmTlConditionalappraisalmst.setCdamModifiedon(dateTime);
	    
	    if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamFlid()))
	        plmTlConditionalappraisalmst.setCdamFlid("{}");
	    if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamElementid()))
	        plmTlConditionalappraisalmst.setCdamElementid("{}");
	    
	    if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield1()))
	        plmTlConditionalappraisalmst.setCdamTempfield1("-");
	    if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield2()))
	        plmTlConditionalappraisalmst.setCdamTempfield2("-");
	    if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield3()))
	        plmTlConditionalappraisalmst.setCdamTempfield3("-");
	    if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield4()))
	        plmTlConditionalappraisalmst.setCdamTempfield4("-");
	    if(!UIUtils.isValidKeyId(plmTlConditionalappraisalmst.getCdamTempfield5()))
	        plmTlConditionalappraisalmst.setCdamTempfield5("-");
	    
	    if(plmTlConditionalappraisalmst.getPlmTlConditionalappraisal() != null) {
	        // Get the single entry (not a list)
	        PlmTlConditionalappraisalEntry entry = plmTlConditionalappraisalmst.getPlmTlConditionalappraisal();
	        
	        CommonMessage.debugMsg("========== BEFORE fillValuesEntry ==========");
	        CommonMessage.debugMsg("KeyId: " + entry.getCdapKeyid());
	        CommonMessage.debugMsg("Component Type: " + entry.getCdapComponentType());
	        CommonMessage.debugMsg("Component ID: " + entry.getCdapComponentid());
	        CommonMessage.debugMsg("Dimension: " + entry.getCdapDimension());
	        CommonMessage.debugMsg("Checking Tool: " + entry.getCdapCheckingtool());
	        CommonMessage.debugMsg("Type of Check: " + entry.getCdapTypeofcheck());
	        CommonMessage.debugMsg("Ideal Type: " + entry.getCdapIdealtype());
	        CommonMessage.debugMsg("Ideal Min: " + entry.getCdapIdealminimum());
	        CommonMessage.debugMsg("Ideal Max: " + entry.getCdapIdealmaximum());
	        CommonMessage.debugMsg("Actual Condition: " + entry.getCdapActualcondition());
	        CommonMessage.debugMsg("Actual Value: " + entry.getCdapActualvalue());
	        CommonMessage.debugMsg("Status: " + entry.getCdapStatus());
	        CommonMessage.debugMsg("OK/Not OK: " + entry.getCdapOknotok());
	        
	        plmTlConditionalappraisalmst.setPlmTlConditionalappraisal(fillValuesEntry(
	                plmTlConditionalappraisalmst.getPlmTlConditionalappraisal(),
	                plmTlConditionalappraisalmst.getPlmTlConditionalappraisal()));
	        
	        CommonMessage.debugMsg("========== AFTER fillValuesEntry ==========");
	        entry = plmTlConditionalappraisalmst.getPlmTlConditionalappraisal();
	        CommonMessage.debugMsg("KeyId: " + entry.getCdapKeyid());
	        CommonMessage.debugMsg("Component Type: " + entry.getCdapComponentType());
	        CommonMessage.debugMsg("Component ID: " + entry.getCdapComponentid());
	        CommonMessage.debugMsg("Dimension: " + entry.getCdapDimension());
	        CommonMessage.debugMsg("Checking Tool: " + entry.getCdapCheckingtool());
	        CommonMessage.debugMsg("Type of Check: " + entry.getCdapTypeofcheck());
	        CommonMessage.debugMsg("Ideal Type: " + entry.getCdapIdealtype());
	        CommonMessage.debugMsg("Ideal Min: " + entry.getCdapIdealminimum());
	        CommonMessage.debugMsg("Ideal Max: " + entry.getCdapIdealmaximum());
	        CommonMessage.debugMsg("Actual Condition: " + entry.getCdapActualcondition());
	        CommonMessage.debugMsg("Actual Value: " + entry.getCdapActualvalue());
	        CommonMessage.debugMsg("Status: " + entry.getCdapStatus());
	        CommonMessage.debugMsg("OK/Not OK: " + entry.getCdapOknotok());
	    } // ✅ Closing brace for the if statement
	    
	    return plmTlConditionalappraisalmst; // ✅ Return statement is OUTSIDE the if block
	}
	@Override
	public PlmTlConditionalappraisalmst update(
			PlmTlConditionalappraisalmst plmTlConditionalappraisalmst,
			PlmTlConditionalappraisalmst existPlmTlConditionalappraisalmst) throws Exception {
		String validationsFor="update";
		validations.validate(plmTlConditionalappraisalmst, "plmTlConditionalappValidation",validationsFor);
		validations.validate(plmTlConditionalappraisalmst.getPlmTlConditionalappraisal(), "plmTlConditionalappValidation",validationsFor);
		fillValues(plmTlConditionalappraisalmst,existPlmTlConditionalappraisalmst);
		//return plmTlConditionalappraisalDao.update(plmTlConditionalappraisalmst);
		
		return conditionalappraisalserviceapi.saveConditionalAppraisal(plmTlConditionalappraisalmst);
	}
	
	@Override
	public PlmTlConditionalappraisal delete(
			PlmTlConditionalappraisal plmTlConditionalappraisal) throws Exception {
		//return plmTlConditionalappraisalDao.delete(plmTlConditionalappraisal);
		return conditionalappraisalserviceapi.deleteConditionalAppraisalDetail(plmTlConditionalappraisal);
	}
	@Override
	public PlmTlConditionalappraisalmst delete(
			PlmTlConditionalappraisalmst plmTlConditionalappraisalmst) throws Exception {
		//return plmTlConditionalappraisalDao.delete(plmTlConditionalappraisalmst);
		return conditionalappraisalserviceapi.deleteConditionalAppraisalMaster(plmTlConditionalappraisalmst);
	}

	@Override
	public List<String[]> recallData(String keyid) throws Exception {
		//return plmTlConditionalappraisalDao.recallData(keyid);
		return conditionalappraisalserviceapi.recallConditionalAppraisalDetail(keyid);
	}
	@Override
	public List<String[]> recallentryData(String keyid) throws Exception {
		return plmTlConditionalappraisalDao.recallEntryData(keyid);
	}

	@Override
	public Workbook getCondAppExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return plmTlConditionalappraisalDao.getCondAppExcel(colmodel,format,commonFilter);
	}
	@Override
	public List<String[]> getConditionalAppMainGrid(CommonFilter commonFilter) throws Exception {
		return this.plmTlConditionalappraisalDao.getConditionalAppMainGrid(commonFilter);
	}
	@Override
	public List<String[]> getCondApReport(CommonFilter commonFilter) throws Exception {
		return this.plmTlConditionalappraisalDao.getCondApReport(commonFilter);
	}
	@Override
	public List<String[]> getExcelColmodel(CommonFilter commonFilter) throws Exception {
		return this.plmTlConditionalappraisalDao.getExcelColmodel(commonFilter);
	}
	@Override
	public Workbook getViewExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return plmTlConditionalappraisalDao.getViewExcel(colmodel,format,commonFilter);
	}
	@Override
	public List<ComboBox> getcheckingtool(CommonFilter commonFilter,ComboFilter comboFilter)
			throws Exception {
		//ComboFilter spare = commonFilter.getSpare();
		comboFilter.setIdField("CHKT_KEYID");
		comboFilter.setCodeField("CHKT_CODE");
		comboFilter.setNameField("CHKT_NAME");
		comboFilter.setTableName("PLM_TL_CHECKINGTOOL");		
		return commonFilterDao.fillComboValues(comboFilter);
	}	
	public List<ComboBox> getSpareComboList(CommonFilter commonFilter)	throws Exception {

		ComboFilter spare = commonFilter.getSpare();
		spare.setIdField("SPRM_KEYID");
		spare.setCodeField("SPRM_PARTNO");
		spare.setNameField("SPRM_PARTNAME");
		spare.setTableName(TableNames.TBL_GEN_TL_SPARESMST);
		StringBuffer condSql = new StringBuffer();
		String assmId=commonFilter.getAssembly()!=null?commonFilter.getAssembly().getId():null;
		String machineId = commonFilter.getMachine() != null ? commonFilter.getMachine().getId() : null;
	
		if(UIUtils.isValidKeyId(assmId))
		{
			condSql.append(" AND  SPRM_KEYID IN(SELECT  FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN WHERE  INSTR(FNLN_ELEMENTID,'"+assmId+"',1,1)>0 AND FNLN_ELEMENTTYPE='SPR')");
		}
		if(UIUtils.isValidKeyId(machineId))
		{
			condSql.append(" AND  SPRM_KEYID IN(SELECT  FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN WHERE  INSTR(FNLN_ELEMENTID,'"+machineId+"',1,1)>0 AND FNLN_ELEMENTTYPE='SPR')");
		}
		if(condSql.length()>0)
			spare.setCondSql(condSql.toString());
		return commonFilterDao.fillComboValues(spare);
	}
	@Override
	public String addnewcomponent(String compname, String mechid) throws Exception {
		// TODO Auto-generated method stub
		GenTlSparesmst genTlSparesmst  = new GenTlSparesmst();
		GenTlFunctionallocn genTlFunctionallocn = new GenTlFunctionallocn();
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		
		
		genTlFunctionallocn.setFnlnOriginalid("");	
		genTlFunctionallocn.setFnlnElementid("");		
		genTlFunctionallocn.setFnlnElementtype("SPR");		
		genTlFunctionallocn.setFnlnParentid("");		
		genTlFunctionallocn.setFnlnDescription(compname);
		genTlFunctionallocn.setFnlnDisplaycode("");
		genTlFunctionallocn.setFnlnActive("Y");
		genTlFunctionallocn.setFnlnKeyid("");

		
		genTlSparesmst.setSprmKeyid("");                                                                                                                                                                                                   
		genTlSparesmst.setSprmPartno("") ;                                                                                                                                                                                                    
		genTlSparesmst.setSprmPartname(compname);                                                                                                                                                                                                  
		genTlSparesmst.setSprmFactoryid("-");                                                                                                                                                                                                 
		genTlSparesmst.setSprmSource("-");                                                                                                                                                                                                    
		genTlSparesmst.setSprmType("D");                                                                                                                                                                                                       
		genTlSparesmst.setSprmIschangepart("-") ;                                                                                                                                                                                              
		genTlSparesmst.setSprmIsmachinespecific("-");                                                                                                                                                                                        
		genTlSparesmst.setSprmStoreslocationref("-");                                                                                                                                                                                        
		genTlSparesmst.setSprmCriticalityid("-");                                                                                                                                                                                             
		genTlSparesmst.setSprmClassificationid("-");                                                                                                                                                                                          
		genTlSparesmst.setSprmCategoryid("-");                                                                                                                                                                                               
		genTlSparesmst.setSprmSubcategoryid("-");                                                                                                                                                                                              
		genTlSparesmst.setSprmAbcclass("C");                                                                                                                                                                                                  
		genTlSparesmst.setSprmSupplierpartno("-");                                                                                                                                                                                             
		genTlSparesmst.setSprmUomid("-");                                                                                                                                                                                                     
		genTlSparesmst.setSprmMake("-");                                                                                                                                                                                                       
		genTlSparesmst.setSprmModel("-");                                                                                                                                                                                                      
		genTlSparesmst.setSprmSpecification("-");                                                                                                                                                                                              
		genTlSparesmst.setSprmShelflifeitem("-");                                                                                                                                                                                             
		genTlSparesmst.setSprmShelflifemonths("0");                                                                                                                                                                                          
		genTlSparesmst.setSprmLeadtimeinternal("0");                                                                                                                                                                                        
		genTlSparesmst.setSprmLeadtimeexternal("0");                                                                                                                                                                                          
		genTlSparesmst.setSprmMaxinventorylevel("0");                                                                                                                                                                                          
		genTlSparesmst.setSprmReorderlevel("0");                                                                                                                                                                                              
		genTlSparesmst.setSprmReorderqty("0");                                                                                                                                                                                                 
		genTlSparesmst.setSprmPrefsupplier1("-");                                                                                                                                                                                             
		genTlSparesmst.setSprmPrefsupplier2("-");                                                                                                                                                                                              
		genTlSparesmst.setSprmPrefsupplier3("-");                                                                                                                                                                                              
		genTlSparesmst.setSprmEquipmentgroup("-");                                                                                                                                                                                          
		genTlSparesmst.setSprmStandardrate("0");                                                                                                                                                                                              
		genTlSparesmst.setSprmIsdirectentry("-");                                                                                                                                                                                              
		genTlSparesmst.setSprmDrawingno("-");                                                                                                                                                                                                
		genTlSparesmst.setSprmErpname("-");                                                                                                                                                                                                   
		genTlSparesmst.setSprmErpcode("-");                                                                                                                                                                                                   
		genTlSparesmst.setSprmShelflifeunit("-");                                                                                                                                                                                              
		genTlSparesmst.setSprmTempfield2("-");                                                                                                                                                                                                
		genTlSparesmst.setSprmTempfield3("-");                                                                                                                                                                                                 
		genTlSparesmst.setSprmActive("Y");                                                                                                                                                                                                    
		genTlSparesmst.setSprmCreatedby("EMP00001");                                                                                                                                                                                                  
		genTlSparesmst.setSprmCreatedon(dateTime) ;                                                                                                                                                                                                
		genTlSparesmst.setSprmModifiedon(dateTime);   
		
		return plmTlConditionalappraisalDao.addnewcomponent(genTlSparesmst,genTlFunctionallocn,mechid);
	}
	@Override
	public PlmTlConditionalappraisalmstentry updateEntry(
			PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst,
			PlmTlConditionalappraisalmstentry existPlmTlConditionalappraisalmst)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PlmTlConditionalappraisalmstentry updateMstEntry(
			PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst,
			PlmTlConditionalappraisalmstentry existPlmTlConditionalappraisalmst)
			throws Exception {
		String validationsFor="update";
		validations.validate(plmTlConditionalappraisalmst, "plmTlConditionalappValidation",validationsFor);
		validations.validate(plmTlConditionalappraisalmst.getPlmTlConditionalappraisal(), "plmTlConditionalappValidation",validationsFor);
		fillValuesMstEntry(plmTlConditionalappraisalmst,existPlmTlConditionalappraisalmst);
		//return plmTlConditionalappraisalDao.updateEntry(plmTlConditionalappraisalmst);
		return conditionalappraisalserviceapi.saveConditionalAppraisalEntry(plmTlConditionalappraisalmst);
	}
	@Override
	public String checkdata(
			PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst,String ForGrid)
			throws Exception {
		String cdapKeyid = "";

		if (plmTlConditionalappraisalmst.getPlmTlConditionalappraisal() != null) {
		    String temp = plmTlConditionalappraisalmst
		            .getPlmTlConditionalappraisal()
		            .getCdapCdapkeyid();
		    cdapKeyid = (temp != null) ? temp : "";
		}
		String date = plmTlConditionalappraisalmst.getCdamDate();
		CommonMessage.debugMsg("checkupdate"+date);
		//plmTlConditionalappraisalmst.setCdamDate(CommonFunctions.pg_getDateTimeFromDate(date));
		
		//String formatDate = CommonFunctions.pg_getDateTimeFromDate(date);
		
		
		String flid = plmTlConditionalappraisalmst.getCdamFlid();
		
		
	//return plmTlConditionalappraisalDao.checkupdate(plmTlConditionalappraisalmst,ForGrid);
	
		
		//return conditionalappraisalserviceapi.checkUpdate(plmTlConditionalappraisalmst,ForGrid);
		return conditionalappraisalserviceapi.checkUpdate(flid, date, cdapKeyid, ForGrid);
		
	}
	@Override
	public List<String[]> getConditionalAppraisalEntryGrid(
			CommonFilter commonFilter) throws Exception {
		return this.plmTlConditionalappraisalDao.getConditionalAppraisalEntryGrid(commonFilter);
	}
	@Override
	public List<String[]> getUserRoleDetails(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception {
	    return plmTlConditionalappraisalDao.getUserRoleDetails(loginflid, loginlevel, loginElementid, empId);
	}

}
