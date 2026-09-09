package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlJhactivitychartdtlDao;
import com.akranta.tpm.dao.GenTlJhactivitychartmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlJhactivitychartdtlDaoImpl;
import com.akranta.tpm.dao.impl.GenTlJhactivitychartmstDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlJhactivitychartdtl;
import com.akranta.tpm.model.GenTlJhactivitychartmst;
import com.akranta.tpm.service.JhactivitychartService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class JhactivitychartServiceImpl implements JhactivitychartService{
	private GenTlJhactivitychartmstDao genTlJhactivitychartmstDao ;
	private GenTlJhactivitychartdtlDao genTlJhactivitychartdtlDao ;	
	 private CommonFilterDao commonFilterDao;
	 private Validations validation;
	 
	 public JhactivitychartServiceImpl(DBActionTemplate dbActionTemplate) {
		 genTlJhactivitychartmstDao=new GenTlJhactivitychartmstDaoImpl(dbActionTemplate);
		 genTlJhactivitychartdtlDao=new GenTlJhactivitychartdtlDaoImpl(dbActionTemplate);
		 commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
		 validation = new Validations();
	 }
	@Override
	public List<String[]> getjhActDtl(CommonFilter commonFilter) throws Exception {
		return genTlJhactivitychartmstDao.getjhActDtl(commonFilter);
	}
	@Override
	public GenTlJhactivitychartmst delete(
			GenTlJhactivitychartmst genTlJhactivitychartmst) throws Exception {
		return genTlJhactivitychartmstDao.delete(genTlJhactivitychartmst);
	}
	@Override
	public GenTlJhactivitychartmst create(
			GenTlJhactivitychartmst newGenTlJhactivitychartmst,
			GenTlJhactivitychartmst existGenTlJhactivitychartmst) throws Exception {
		validation.validate(newGenTlJhactivitychartmst, "jhEntryValidation","create");
		filValues(newGenTlJhactivitychartmst,existGenTlJhactivitychartmst);
		return genTlJhactivitychartmstDao.create(newGenTlJhactivitychartmst);
	}
	private GenTlJhactivitychartmst filValues(GenTlJhactivitychartmst newGenTlJhactivitychartmst,
			GenTlJhactivitychartmst existGenTlJhactivitychartmst) {
		String dateTime = CommonFunctions.dateTimeNow();
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmActive()))
			newGenTlJhactivitychartmst.setAchmActive("Y");
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmActivity()))
			newGenTlJhactivitychartmst.setAchmActivity("{}");
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmCreatedby()))
			newGenTlJhactivitychartmst.setAchmCreatedby("{}");
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmCreatedon()))
			newGenTlJhactivitychartmst.setAchmCreatedon(dateTime);
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmEffectivedate()))
			newGenTlJhactivitychartmst.setAchmEffectivedate(Constants.passNullDate);
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmFlid()))
			newGenTlJhactivitychartmst.setAchmFlid("{}");
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmFrequency()))
			newGenTlJhactivitychartmst.setAchmFrequency("{}");
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmInactivedate()))
			newGenTlJhactivitychartmst.setAchmInactivedate(Constants.passNullDate);
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmModifiedon()))
			newGenTlJhactivitychartmst.setAchmModifiedon(dateTime);
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmRoleid()))
			newGenTlJhactivitychartmst.setAchmRoleid("{}");
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmTempfield1()))
			newGenTlJhactivitychartmst.setAchmTempfield1("-");
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmTempfield2()))
			newGenTlJhactivitychartmst.setAchmTempfield2("-");
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmTempfield3()))
			newGenTlJhactivitychartmst.setAchmTempfield3("-");
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmTempfield4()))
			newGenTlJhactivitychartmst.setAchmTempfield4("-");
		if(!UIUtils.isValidKeyId(newGenTlJhactivitychartmst.getAchmTempfield5()))
			newGenTlJhactivitychartmst.setAchmTempfield5("-");
		if(newGenTlJhactivitychartmst.getGenTlJhactivitychartdtl()!=null)
			newGenTlJhactivitychartmst.setGenTlJhactivitychartdtl(fillValuesDtl(newGenTlJhactivitychartmst,existGenTlJhactivitychartmst));
		return newGenTlJhactivitychartmst;
		
	}
	private List<GenTlJhactivitychartdtl> fillValuesDtl(
			GenTlJhactivitychartmst newGenTlJhactivitychartmst,
			GenTlJhactivitychartmst existGenTlJhactivitychartmst) {
		List<GenTlJhactivitychartdtl> genTlJhactivitychartdtlList= newGenTlJhactivitychartmst.getGenTlJhactivitychartdtl();
		String dateTime = CommonFunctions.dateTimeNow();
		List<GenTlJhactivitychartdtl> newGenTlJhactivitychartdtl=new ArrayList<GenTlJhactivitychartdtl>();
		for(GenTlJhactivitychartdtl genTlJhactivitychartdtl:genTlJhactivitychartdtlList){
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdAchmKeyid()))
				genTlJhactivitychartdtl.setJacdAchmKeyid("{}");
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdActive()))
				genTlJhactivitychartdtl.setJacdActive("Y");
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdCreatedby()))
				genTlJhactivitychartdtl.setJacdCreatedby("{}");
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdCreatedon()))
				genTlJhactivitychartdtl.setJacdCreatedon(dateTime);
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdDescription()))
				genTlJhactivitychartdtl.setJacdDescription("{}");
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdTime()))
				genTlJhactivitychartdtl.setJacdTime("{}");
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdModifiedon()))
				genTlJhactivitychartdtl.setJacdModifiedon(dateTime);
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdRemarks()))
				genTlJhactivitychartdtl.setJacdRemarks("{}");
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdShiftid()))
				genTlJhactivitychartdtl.setJacdShiftid("{}");
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdTempfield1()))
				genTlJhactivitychartdtl.setJacdTempfield1("-");
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdTempfield2()))
				genTlJhactivitychartdtl.setJacdTempfield2("-");
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdTempfield3()))
				genTlJhactivitychartdtl.setJacdTempfield3("-");
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdTempfield5()))
				genTlJhactivitychartdtl.setJacdTempfield5("-");
			if(!UIUtils.isValidKeyId(genTlJhactivitychartdtl.getJacdtempfield4()))
				genTlJhactivitychartdtl.setJacdtempfield4("-");
			newGenTlJhactivitychartdtl.add(genTlJhactivitychartdtl);
		}
		return newGenTlJhactivitychartdtl;
	}
	@Override
	public GenTlJhactivitychartmst update(
			GenTlJhactivitychartmst newGenTlJhactivitychartmst,
			GenTlJhactivitychartmst existGenTlJhactivitychartmst) throws Exception {
		validation.validate(newGenTlJhactivitychartmst, "jhEntryValidation","update");
		filValues(newGenTlJhactivitychartmst,existGenTlJhactivitychartmst);
		return genTlJhactivitychartmstDao.update(newGenTlJhactivitychartmst);
	}
	@Override
	public GenTlJhactivitychartmst selectMstData(String mstKeyid) throws Exception {
		return genTlJhactivitychartmstDao.selectMstData(mstKeyid);
	}
	@Override
	public List<String[]> getjhAct(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return genTlJhactivitychartmstDao.getjhAct(commonFilter);
	}
	@Override
	public GenTlJhactivitychartdtl delete(
			List<GenTlJhactivitychartdtl> jhActDtlList) throws Exception {
		// TODO Auto-generated method stub
		return genTlJhactivitychartdtlDao.delete(jhActDtlList);
	}
	@Override
	public Workbook getjhActGridExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws IOException, SQLException, Exception {
		// TODO Auto-generated method stub
		return genTlJhactivitychartmstDao.getjhActGridExcel(commonFilter,tblJSONObj,format);
	}
}
