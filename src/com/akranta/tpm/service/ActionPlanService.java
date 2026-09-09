package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplanmst;

public interface ActionPlanService {

	GenTlActionplanmst create(GenTlActionplanmst genTlActionplanmst,
			GenTlActionplanmst existGenTlActionplanmst, String from) throws Exception;

	GenTlActionplanmst update(GenTlActionplanmst genTlActionplanmst,
			GenTlActionplanmst existGenTlActionplanmst, String from)throws Exception;

	GenTlActionplanmst delete(GenTlActionplanmst genTlActionplanmst)throws Exception;

	List<String[]> getActionPlan(CommonFilter commonFilter, String from)throws Exception;

	GenTlActionplanmst select(String keyid)throws Exception;

	Workbook ActionPlanExportExcel(CommonFilter commonFilter, JSONObject colmodel, String format, String from)throws Exception;

	List<ComboBox> getPhenomenaComboList(String assemblyId)throws Exception;

	List<String[]> getlink() throws Exception;

	

}
