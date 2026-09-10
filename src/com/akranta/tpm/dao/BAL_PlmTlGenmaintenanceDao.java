package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.BAL_BdmTlSetupadjsplit;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PlmTlGenmaintenance;
import com.akranta.tpm.model.WomTlWomst;

public interface BAL_PlmTlGenmaintenanceDao {

	public abstract BAL_PlmTlGenmaintenance create(BAL_PlmTlGenmaintenance plmTlGenmaintenance) throws Exception;
	public abstract BAL_PlmTlGenmaintenance update(BAL_PlmTlGenmaintenance plmTlGenmaintenance,WomTlWomst womTlWomst) throws Exception;
	public abstract BAL_PlmTlGenmaintenance delete(BAL_PlmTlGenmaintenance plmTlGenmaintenance) throws Exception;
	public abstract BAL_BdmTlSetupadjsplit deleteSubLoss(BAL_BdmTlSetupadjsplit bdmTlSetupadjsplit) throws Exception;
	public abstract List<String[]> getdataGenMain(CommonFilter commonFilter, String relto, String setupAdj);
	public abstract List<String[]> getSetupAdjSubLoss(String refDocId);
	public abstract BAL_PlmTlGenmaintenance getFillValue(String docno) throws Exception;
	public abstract String getShift(List<String> paramValues);
	public List<ComboBox> fillComboValues(ComboFilter comboFilter) throws Exception;
	public Workbook getGenMaintainanaceReport(CommonFilter commonFilter, JSONObject colmodel,String rptFormat,String relto)throws Exception;
    public void BAL_PlmTlGenmaintenanceDaoImplJwt(String JwtToken);
		
		
	
}

