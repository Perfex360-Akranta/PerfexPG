package com.akranta.tpm.dao;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlControlandresponseplan;

public interface GenTlControlandresponseplanDao {

	public abstract GenTlControlandresponseplan create(GenTlControlandresponseplan genTlControlandresponseplan) throws Exception;
	public abstract GenTlControlandresponseplan update(GenTlControlandresponseplan genTlControlandresponseplan) throws Exception;
	public abstract GenTlControlandresponseplan delete(GenTlControlandresponseplan genTlControlandresponseplan) throws Exception;
	public abstract GenTlControlandresponseplan getAllFillControl(String controlId)throws Exception;
	public abstract Workbook getConresplnExcel(JSONObject colmodel,String format, CommonFilter commonFilter)throws Exception;

}

