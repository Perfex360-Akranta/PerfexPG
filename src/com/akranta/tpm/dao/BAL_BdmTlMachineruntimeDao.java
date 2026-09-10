package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.BAL_BdmTlMachineruntime;

public interface BAL_BdmTlMachineruntimeDao {

	public abstract List<BAL_BdmTlMachineruntime> create(List<BAL_BdmTlMachineruntime> mchnHrsList) throws Exception;
	public abstract BAL_BdmTlMachineruntime update(BAL_BdmTlMachineruntime bdmTlMachineruntime) throws Exception;
	public abstract BAL_BdmTlMachineruntime delete(BAL_BdmTlMachineruntime bdmTlMachineruntime) throws Exception;
	public abstract List<String[]> getMachinerRunHrs(String cellId, GridParams gridParams)throws Exception;
	public abstract String getCellOrMchWiseConfig()throws Exception;
	public abstract Workbook MchnRnHrsExportExcel(JSONObject colmodel,String format, GridParams gridParams, String keyId)throws Exception;

}

