package com.akranta.tpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.JhaTlAuditreportmstDao;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import com.akranta.tpm.dao.impl.JhaTlAuditreportmstDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlAuditreportdtl;
import com.akranta.tpm.model.JhaTlAuditreportmst;
import com.akranta.tpm.model.KznTlEvaluationmst;
import com.akranta.tpm.service.jhAuditElementReportService;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class jhAuditElementReportServiceImpl implements jhAuditElementReportService{
	private Validations validations ;
	private JhaTlAuditreportmstDao jhaTlAuditreportmstDao ;
	public jhAuditElementReportServiceImpl(DBActionTemplate dbActionTemplate)
	{
		jhaTlAuditreportmstDao =  new JhaTlAuditreportmstDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	@Override
	public String populateTempTable(String excelFileName,
			JhaTlAuditreportmst jhaTlAuditreportmst) throws Exception {
		try{
			validations.validate(jhaTlAuditreportmst, "AuditreporValidations", "upload");
			fillValuesmst(jhaTlAuditreportmst);
		}catch(ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}
		return jhaTlAuditreportmstDao.populateTempTable(excelFileName,jhaTlAuditreportmst);
	}
	private JhaTlAuditreportmst fillValuesmst(JhaTlAuditreportmst jhaTlAuditreportmst) {
		String dateTime=CommonFunctions.dateTimeNow();
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmActive()))
			jhaTlAuditreportmst.setAurmActive("Y");
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmCreatedon()))
			jhaTlAuditreportmst.setAurmCreatedon(dateTime);
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmDate()))
			jhaTlAuditreportmst.setAurmDate(Constants.passNullDate);
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmElementid()))
			jhaTlAuditreportmst.setAurmElementid("{}");
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmFlid()))
			jhaTlAuditreportmst.setAurmFlid("{}");
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmPreparedby()))
			jhaTlAuditreportmst.setAurmPreparedby("{}");
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmUploadedby()))
			jhaTlAuditreportmst.setAurmUploadedby("{}");
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmUploadedfile()))
			jhaTlAuditreportmst.setAurmUploadedfile("{}");
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmModifiedon()))
			jhaTlAuditreportmst.setAurmModifiedon(dateTime);
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmTempfield1()))
			jhaTlAuditreportmst.setAurmTempfield1("-");
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmTempfield2()))
			jhaTlAuditreportmst.setAurmTempfield2("-");
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmTempfield3()))
			jhaTlAuditreportmst.setAurmTempfield3("-");
		if(jhaTlAuditreportmst.getJhaTlAuditreportdtl()!=null){
			if(jhaTlAuditreportmst.getJhaTlAuditreportdtl().size()>0)
				jhaTlAuditreportmst.setJhaTlAuditreportdtl(fillValuesDtl(jhaTlAuditreportmst));
		}
		return jhaTlAuditreportmst;
		
	}
	private List<JhaTlAuditreportdtl> fillValuesDtl(JhaTlAuditreportmst jhaTlAuditreportmst) {
		String dateTime=CommonFunctions.dateTimeNow();
		List<JhaTlAuditreportdtl> listJhaTlAuditreportdtl = jhaTlAuditreportmst.getJhaTlAuditreportdtl();
		List<JhaTlAuditreportdtl> jhaTlAuditreportdtlList = new ArrayList<JhaTlAuditreportdtl>();
		for (JhaTlAuditreportdtl jhaTlAuditreportdtl:listJhaTlAuditreportdtl){
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdActive()))
				jhaTlAuditreportdtl.setAurdActive("Y");
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdAuditelement()))
				jhaTlAuditreportdtl.setAurdAuditelement("{}");
			else
				jhaTlAuditreportdtl.setAurdAuditelement(getValueRepalced(jhaTlAuditreportdtl.getAurdAuditelement()));
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdCreatedby()))
				jhaTlAuditreportdtl.setAurdCreatedby(jhaTlAuditreportmst.getAurmCreatedby());
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdMa()))
				jhaTlAuditreportdtl.setAurdMa("0");
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdMm()))
				jhaTlAuditreportdtl.setAurdMm("0");
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdModifiedon()))
				jhaTlAuditreportdtl.setAurdModifiedon(dateTime);
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdCreatedon()))
				jhaTlAuditreportdtl.setAurdCreatedon(dateTime);
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdObservations()))
				jhaTlAuditreportdtl.setAurdObservations("{}");
			else
				jhaTlAuditreportdtl.setAurdObservations(getValueRepalced(jhaTlAuditreportdtl.getAurdObservations()));
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdRemarks()))
				jhaTlAuditreportdtl.setAurdRemarks("{}");
			else
				jhaTlAuditreportdtl.setAurdRemarks(getValueRepalced(jhaTlAuditreportdtl.getAurdRemarks()));
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdOrderno()))
				jhaTlAuditreportdtl.setAurdOrderno("0");
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdSheetname()))
				jhaTlAuditreportdtl.setAurdSheetname("{}");
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdSheetno()))
				jhaTlAuditreportdtl.setAurdSheetno("0");
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdSlno()))
				jhaTlAuditreportdtl.setAurdSlno("{}");
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdTempfield1()))
				jhaTlAuditreportdtl.setAurdTempfield1("-");
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdTempfield2()))
				jhaTlAuditreportdtl.setAurdTempfield2("-");
			if(!UIUtils.isValidKeyId(jhaTlAuditreportdtl.getAurdTempfield3()))
				jhaTlAuditreportdtl.setAurdTempfield3("-");
			jhaTlAuditreportdtlList.add(jhaTlAuditreportdtl);
		}
		return jhaTlAuditreportdtlList;
	}
	private String getValueRepalced(String value) {
		value=value.replaceAll("%-%", "-");
		value=value.replaceAll("%_%", "_");
		value=value.replaceAll("-", " ");
		value=value.replaceAll("%%", "'");
		value=value.replaceAll("_", "/");
		value=value.replaceAll("#", "(");
		value=value.replaceAll("%#%", ")");
		value=value.replaceAll("%20%","\"");
		return value;
	}
	@Override
	public List<String[]> getGriddata(CommonFilter commonFilter) throws Exception {
		return jhaTlAuditreportmstDao.getGriddata(commonFilter);
	}
	@Override
	public List<String[]> getMainGrid(CommonFilter commonFilter) throws Exception {
		return jhaTlAuditreportmstDao.getMainGrid(commonFilter);
	}
	@Override
	public JhaTlAuditreportmst selectData(String keyid) throws Exception {
		return jhaTlAuditreportmstDao.selectData(keyid);
	}
	@Override
	public JhaTlAuditreportmst delete(JhaTlAuditreportmst jhaTlAuditreportmst) throws Exception {
		return jhaTlAuditreportmstDao.delete(jhaTlAuditreportmst);
	}
	@Override
	public JhaTlAuditreportmst update(JhaTlAuditreportmst jhaTlAuditreportmst) throws Exception {
		validations.validate(jhaTlAuditreportmst, "AuditreporValidations", "update");
		fillValuesmst(jhaTlAuditreportmst);
		return jhaTlAuditreportmstDao.update(jhaTlAuditreportmst);
	}
	@Override
	public List<JhaTlAuditreportdtl> delete(List<JhaTlAuditreportdtl> listJhaTlAuditreportdtl) throws Exception {
		return jhaTlAuditreportmstDao.delete(listJhaTlAuditreportdtl);
	}
	@Override
	public Workbook getExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return jhaTlAuditreportmstDao.getExcel(colmodel,format,commonFilter);
	}
}
