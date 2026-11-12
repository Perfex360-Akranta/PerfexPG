package com.akranta.tpm.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.SheTlAccinvestigationdtlDao;
import com.akranta.tpm.dao.SheTlAccinvestigationmstDao;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.SheTlAccinvestigationdtlDaoImpl;
import com.akranta.tpm.dao.impl.SheTlAccinvestigationmstDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.SheTlAccinvestigationdtl;
import com.akranta.tpm.model.SheTlAccinvestigationmst;
import com.akranta.tpm.service.AccidentInvestigationService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class AccidentInvestigationServiceImpl implements AccidentInvestigationService {
	private SheTlAccinvestigationmstDao sheTlAccinvestigationmstDao;
	private SheTlAccinvestigationdtlDao sheTlAccinvestigationdtlDao;
	private DBActionTemplate dbActionTemplate;
	private Validations validations;
	public AccidentInvestigationServiceImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		sheTlAccinvestigationmstDao=new SheTlAccinvestigationmstDaoImpl(dbActionTemplate);
		sheTlAccinvestigationdtlDao=new SheTlAccinvestigationdtlDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	@Override
	public List<String[]> getAccInvestDtl(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		return sheTlAccinvestigationmstDao.getAccInvestDtl(commonFilter);
	}
	@Override
	public SheTlAccinvestigationmst create(
			SheTlAccinvestigationmst sheTlAccinvestigationmst,
			SheTlAccinvestigationmst existSheTlAccinvestigationmst)
			throws Exception {
		validations.validate(sheTlAccinvestigationmst, "AccInvestigationValidation", "create");
		fillValues(sheTlAccinvestigationmst);
		return sheTlAccinvestigationmstDao.create(sheTlAccinvestigationmst);
	}
	private SheTlAccinvestigationmst fillValues(SheTlAccinvestigationmst sheTlAccinvestigationmst) throws Exception {
		String dateTime=CommonFunctions.dateTimeNow();
		
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmAccdate()))
			sheTlAccinvestigationmst.setAinmAccdate(Constants.passNullDate);
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmAccdescription()))
			sheTlAccinvestigationmst.setAinmAccdescription("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmAcclocation()))
			sheTlAccinvestigationmst.setAinmAcclocation("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmAcctime()))
			sheTlAccinvestigationmst.setAinmAcctime("00:00");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmBodypartaffected()))
			sheTlAccinvestigationmst.setAinmBodypartaffected("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmCreatedon()))
			sheTlAccinvestigationmst.setAinmCreatedon(dateTime);
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmActive()))
			sheTlAccinvestigationmst.setAinmActive("Y");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmDepartment()))
			sheTlAccinvestigationmst.setAinmDepartment("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmEmployee()))
			sheTlAccinvestigationmst.setAinmEmployee("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmFindings()))
			sheTlAccinvestigationmst.setAinmFindings("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmImmediatecause()))
			sheTlAccinvestigationmst.setAinmImmediatecause("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmModifiedon()))
			sheTlAccinvestigationmst.setAinmModifiedon(dateTime);
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmNatureofinjury()))
			sheTlAccinvestigationmst.setAinmNatureofinjury("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmOperators()))
			sheTlAccinvestigationmst.setAinmOperators("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmPnocontractor()))
			sheTlAccinvestigationmst.setAinmPnocontractor("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmResponsibility()))
			sheTlAccinvestigationmst.setAinmResponsibility("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmRootcause()))
			sheTlAccinvestigationmst.setAinmRootcause("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmShiftincharge()))
			sheTlAccinvestigationmst.setAinmShiftincharge("{}");
		if(UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmFlid()))
			sheTlAccinvestigationmst.setAinmElementid(sheTlAccinvestigationmstDao.getElementID(sheTlAccinvestigationmst.getAinmFlid()));
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmFlid()))
			sheTlAccinvestigationmst.setAinmFlid("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmElementid()))
			sheTlAccinvestigationmst.setAinmElementid("{}");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmTempfield3()))
			sheTlAccinvestigationmst.setAinmTempfield3("-");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmTempfield4()))
			sheTlAccinvestigationmst.setAinmTempfield4("-");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmTempfield5()))
			sheTlAccinvestigationmst.setAinmTempfield5("-");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmTempfield6()))
			sheTlAccinvestigationmst.setAinmTempfield6("-");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmTempfield7()))
			sheTlAccinvestigationmst.setAinmTempfield7("-");
		if(!UIUtils.isValidKeyId(sheTlAccinvestigationmst.getAinmTreatment()))
			sheTlAccinvestigationmst.setAinmTreatment("{}");
		if(sheTlAccinvestigationmst.getSheTlAccinvestigationdtl()!=null)
			sheTlAccinvestigationmst.setSheTlAccinvestigationdtl(fillvaluesDtl(sheTlAccinvestigationmst));
		return sheTlAccinvestigationmst;
	}
	private List<SheTlAccinvestigationdtl> fillvaluesDtl(
			SheTlAccinvestigationmst sheTlAccinvestigationmst) {
		String dateTime=CommonFunctions.dateTimeNow();
		List<SheTlAccinvestigationdtl> listSheTlAccinvestigationdtl=sheTlAccinvestigationmst.getSheTlAccinvestigationdtl();
		List<SheTlAccinvestigationdtl> sheTlAccinvestigationdtlList=new ArrayList<SheTlAccinvestigationdtl>();
		for(SheTlAccinvestigationdtl sheTlAccinvestigationdtl:listSheTlAccinvestigationdtl){
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindAgency()))
				sheTlAccinvestigationdtl.setAindAgency("{}");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindAgency()))
				sheTlAccinvestigationdtl.setAindAgency("{}");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindCreatedby()))
				sheTlAccinvestigationdtl.setAindCreatedby(sheTlAccinvestigationmst.getAinmCreatedby());
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindCreatedon()))
				sheTlAccinvestigationdtl.setAindCreatedon(dateTime);
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindActive()))
				sheTlAccinvestigationdtl.setAindActive("Y");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindModifiedon()))
				sheTlAccinvestigationdtl.setAindModifiedon(dateTime);
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindReclocation()))
				sheTlAccinvestigationdtl.setAindReclocation("{}");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindRecommendation()))
				sheTlAccinvestigationdtl.setAindRecommendation("{}");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindRemarks()))
				sheTlAccinvestigationdtl.setAindRemarks("{}");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindTarget()))
				sheTlAccinvestigationdtl.setAindTarget("{}");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindTempfield1()))
				sheTlAccinvestigationdtl.setAindTempfield1("-");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindTempfield2()))
				sheTlAccinvestigationdtl.setAindTempfield2("-");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindTempfield3()))
				sheTlAccinvestigationdtl.setAindTempfield3("-");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindTempfield4()))
				sheTlAccinvestigationdtl.setAindTempfield4("-");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindTempfield5()))
				sheTlAccinvestigationdtl.setAindTempfield5("-");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindTempfield6()))
				sheTlAccinvestigationdtl.setAindTempfield6("-");
			if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindTempfield7()))
				sheTlAccinvestigationdtl.setAindTempfield7("-");
			
			sheTlAccinvestigationdtlList.add(sheTlAccinvestigationdtl);
		}
		return sheTlAccinvestigationdtlList;
	}
	@Override
	public SheTlAccinvestigationmst update(
			SheTlAccinvestigationmst sheTlAccinvestigationmst,
			SheTlAccinvestigationmst existSheTlAccinvestigationmst)
			throws Exception {
		validations.validate(sheTlAccinvestigationmst, "AccInvestigationValidation", "update");
		fillValues(sheTlAccinvestigationmst);
		return sheTlAccinvestigationmstDao.update(sheTlAccinvestigationmst);
	}
	@Override
	public List<String[]> getAccInvestMainGrid(CommonFilter commonFilter)throws Exception {
		return sheTlAccinvestigationmstDao.getAccInvestMainGrid(commonFilter);
	}
	@Override
	public SheTlAccinvestigationmst getAllData(String keyid) throws NoDataFoundException, SQLException, Exception {
		return sheTlAccinvestigationmstDao.getAllData(keyid);
	}
	@Override
	public SheTlAccinvestigationmst delete(
			SheTlAccinvestigationmst sheTlAccinvestigationmst)
			throws Exception {
		return sheTlAccinvestigationmstDao.delete(sheTlAccinvestigationmst);
	}
	@Override
	public List<SheTlAccinvestigationdtl> deleteDtl(
			List<SheTlAccinvestigationdtl> listSheTlAccinvestigationdtl)
			throws Exception {
		return sheTlAccinvestigationdtlDao.delete(listSheTlAccinvestigationdtl);
	}
	@Override
	public Workbook getExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return sheTlAccinvestigationmstDao.getExcel(colmodel, format,commonFilter);
	}
}
