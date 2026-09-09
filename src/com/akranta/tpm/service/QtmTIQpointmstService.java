package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.QPointBean;
import com.akranta.tpm.bean.QparameterBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlQpoint;
import com.akranta.tpm.model.QtmTlQpointdtl;
import com.akranta.tpm.model.QtmTlQpointdtls;
import com.akranta.tpm.model.QtmTlQpointmst;

public interface QtmTIQpointmstService {

	public QtmTlQpointmst create(QtmTlQpointmst newqtmtiqpointmst,QtmTlQpointmst existqtmtiqpointmst) throws Exception;
	public QtmTlQpointmst update(QtmTlQpointmst newqtmtiqpointmst,QtmTlQpointmst existqtmtiqpointmst, QparameterBean para) throws Exception;
	public QtmTlQpointmst select(String keyid) throws Exception  ;
	public List<String[]> getmaster(String masterKeyid) throws Exception;
	public List<String[]> getAllSop()  throws Exception ;
	public QtmTlQpointmst delete(QtmTlQpointmst newqtmtiqpointmst,QtmTlQpointmst existQtmTlSopmst, QparameterBean bean) throws Exception;
	public QtmTlQpointdtl getalldetail(String detailKeyid) throws Exception ;
	public QtmTlQpointdtl create(QtmTlQpointdtl newqtmTlQpointdtl,QtmTlQpointdtl existqtmTlQpointdtl) throws Exception;
	public QtmTlQpointdtl update(QtmTlQpointdtl newqtmTlQpointdtl,QtmTlQpointdtl existqtmTlQpointdtl) throws Exception;
	public QtmTlQpointdtl delete(QtmTlQpointdtl newQtmTlpointdtl,QtmTlQpointdtl existQtmTlpointdtl) throws Exception ;
	public List<String[]> getPoint(String type)  throws Exception ;
	public List<String[]> getQPointNewGrid(CommonFilter commonFilter)throws Exception ;
	public List<String[]> getQPointFormNewGrid(CommonFilter commonFilter, String keyId, String area, String kpov, String preparedby)throws Exception ;
	public QtmTlQpoint createQpoint(QtmTlQpoint newQtmTlQpoint,QtmTlQpoint existQtmTlQpoint, QPointBean qPointBean)throws Exception ;
	public QtmTlQpoint updateQpoint(QtmTlQpoint newQtmTlQpoint,QtmTlQpoint existQtmTlQpoint, QPointBean qPointBean)throws Exception ;
	public List<String[]> FillControlData(String keyid)throws Exception ;
	public QtmTlQpoint deleteQPoint(QtmTlQpoint newQtmTlQpoint)throws Exception ;
	public Workbook getQPointExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception ;
	public QtmTlQpoint getFilControlData(String keyid) throws Exception;
	public List<String[]> getQptscnt(CommonFilter commonFilter) throws Exception;
	public Workbook qPpointCumulativeExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	
	public void QtmTIQpointmstServiceImplJwt(String JwtToken);
}
