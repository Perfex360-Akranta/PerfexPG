package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlEnergyconsumptionmst;

public interface PcsTlEnergyconsumptionmstDao {

	public abstract PcsTlEnergyconsumptionmst create(PcsTlEnergyconsumptionmst pcsTlEnergyconsumptionmst) throws Exception;
	public abstract PcsTlEnergyconsumptionmst update(PcsTlEnergyconsumptionmst pcsTlEnergyconsumptionmst) throws Exception;
	public abstract PcsTlEnergyconsumptionmst delete(PcsTlEnergyconsumptionmst pcsTlEnergyconsumptionmst) throws Exception;
	
	List<String[]> getEnergyConsumptionDetails(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getEnergyConsumptionExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)throws Exception;
	public abstract List<String[]> getEnergyConsumptionEntryRpt(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getEnergyConsumptionEntryRptExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)throws Exception;

}

