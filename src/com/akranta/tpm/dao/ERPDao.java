package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.SapTlPerfexMappingMst;
import com.akranta.tpm.utils.SAPMappingMst;

public interface ERPDao {
	public List<String[]> getTableDetails(String tableName) throws Exception;

	public List<String[]> getAllProcess()throws Exception;

	public void save(SapTlPerfexMappingMst sapTlPerfexMappingMst)throws Exception;

	public List<String[]> getDetailsData(String mappingId, String forRequest,
			String forResponse)throws Exception;
	
	public SapTlPerfexMappingMst getSapPerfexMappingMst(String keyid) throws Exception;
	public String getPrimaryKeyField(String tableName) throws Exception;
	public void deleteSapPerfexMappingDtl(String dtlIds) throws Exception;
	public List<SAPMappingMst> getSapPerfexMappingConfig() throws Exception;
}
