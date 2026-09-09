package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.ControlResponsePlanBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlControlandresponseplan;


public interface ControlResponseService {
	public List<String[]> controlResponseReport(CommonFilter commonFilter) throws Exception;

	public GenTlControlandresponseplan create(GenTlControlandresponseplan newGenTlControlandresponseplan,GenTlControlandresponseplan existGenTlControlandresponseplan,ControlResponsePlanBean controlresponseplanBean)throws Exception;

	public GenTlControlandresponseplan update(GenTlControlandresponseplan newGenTlControlandresponseplan,GenTlControlandresponseplan existGenTlControlandresponseplan,ControlResponsePlanBean controlresponseplanBean)throws Exception;

	public GenTlControlandresponseplan getAllFillControl(String controlId)throws Exception;

	public GenTlControlandresponseplan delete(GenTlControlandresponseplan newGenTlControlandresponseplan)throws Exception;

	public Workbook getConresplnExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;
	
	public void ControlResponseServiceImplJwt(String JwtToken);


}
