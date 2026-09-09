package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

//import com.akranta.tpm.model.BdmTlSetupadjsplit;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PlmTlGenmaintenance;
import com.akranta.tpm.model.WomTlWomst;

public interface PlmTlGenmaintenanceDao {

	public abstract PlmTlGenmaintenance create(PlmTlGenmaintenance plmTlGenmaintenance) throws Exception;
	public abstract PlmTlGenmaintenance update(PlmTlGenmaintenance plmTlGenmaintenance,WomTlWomst womTlWomst) throws Exception;
	public abstract PlmTlGenmaintenance delete(PlmTlGenmaintenance plmTlGenmaintenance) throws Exception;
//	public abstract BdmTlSetupadjsplit deleteSubLoss(BdmTlSetupadjsplit bdmTlSetupadjsplit) throws Exception;
	public abstract List<String[]> getdataGenMain(CommonFilter commonFilter, String relto, String setupAdj);
	public abstract List<String[]> getSetupAdjSubLoss(String refDocId);
	public abstract PlmTlGenmaintenance getFillValue(String docno) throws Exception;
	public abstract String getShift(List<String> paramValues);
	public List<ComboBox> fillComboValues(ComboFilter comboFilter) throws Exception;
	public Workbook getGenMaintainanaceReport(CommonFilter commonFilter, JSONObject colmodel,String rptFormat,String relto)throws Exception;
}

