package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.QparameterBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlQpoint;
import com.akranta.tpm.model.QtmTlQpointdtl;
import com.akranta.tpm.model.QtmTlQpointdtls;
import com.akranta.tpm.model.QtmTlQpointmst;

public interface QtmTlQpointmstDao {
	
	public abstract QtmTlQpointmst create(QtmTlQpointmst newqtmtiqpointmst) throws Exception;
	public abstract QtmTlQpointmst update(QtmTlQpointmst newqtmtiqpointmst)   throws Exception;
	public abstract QtmTlQpointmst select(String keyid) throws Exception;
	public abstract List<String[]> getmaster(String masterKeyid)throws Exception  ;
	public abstract List<String[]> getAllSop() throws Exception ;
	public abstract QtmTlQpointmst delete(QtmTlQpointmst newqtmtiqpointmst) throws Exception;
	public abstract QtmTlQpointdtl getalldetail(String detailKeyid) throws Exception ;
	public abstract List<String[]> getPoint(String type) throws Exception;
	public abstract List<String[]> getQPointNewGrid(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getQPointFormNewGrid(CommonFilter commonFilter, String keyId, String area, String kpov, String preparedby)throws Exception;
	public abstract QtmTlQpoint createQpoint(QtmTlQpoint newQtmTlQpoint)throws Exception;
	public abstract QtmTlQpoint updateQpoint(QtmTlQpoint newQtmTlQpoint)throws Exception;
	public abstract List<String[]> FillControlData(String keyid)throws Exception;
	public abstract QtmTlQpoint deleteQPoint(QtmTlQpoint newQtmTlQpoint)throws Exception;
	public abstract Workbook getQPointExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception;
	public abstract QtmTlQpoint getFilControlData(String keyid);
	public List<String[]> getQptscnt(CommonFilter commonFilter) throws Exception;
	public Workbook qPpointCumulativeExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	
	public void QtmTlQpointmstDaoImplJwt(String JwtToken);
}
