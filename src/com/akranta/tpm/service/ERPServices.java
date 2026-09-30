package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.SapTlPerfexMappingMst;
import com.akranta.tpm.utils.SAPMappingMst;

public interface ERPServices {
	public List<ComboBox> getTableFields(String tableName) throws Exception;
	public List<ComboBox> getAllTabTables(ComboFilter comboFilter) throws Exception;
	
	public List<String[]> getTableDetails(String tableName) throws Exception;
	public List<String[]> getAllProcess()throws Exception;
	public void save(SapTlPerfexMappingMst sapTlPerfexMappingMst)throws Exception;
	public List<ComboBox> getProcessName(ComboFilter comboFilter)throws Exception;
	public List<String[]> getDetailsData(String masterId, String forRequestData,
			String forResponseData) throws Exception;
	
	public SapTlPerfexMappingMst getSapPerfexMappingMst(String keyid) throws Exception;
	public String getPrimaryKeyField(String tableName) throws Exception;
	public void deleteSapPerfexMappingDtl(String dtlIds) throws Exception;
	public List<SAPMappingMst> getSapPerfexMappingConfig() throws Exception;
}
