package com.akranta.tpm.service.impl;

import java.util.List;

import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.ERPDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.ERPDaoImpl;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.SapTlPerfexMappingMst;
import com.akranta.tpm.service.ERPServices;
import com.akranta.tpm.utils.SAPMappingMst;
import com.akranta.tpm.utils.Validations;

public class ERPServiceImpl implements ERPServices {
	
	//private DBActionTemplate dbActionTemplatete 
	private CommonFilterDao commonFilterDao;
	private ERPDao erpDao; 
	public ERPServiceImpl(DBActionTemplate  dbActionTemplate){
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		erpDao = new ERPDaoImpl(dbActionTemplate);
	}
	public List<ComboBox> getAllTabTables(ComboFilter comboFilter) throws Exception{
		
		comboFilter.setCodeField("tname");
		
		comboFilter.setIdField("tname");
		comboFilter.setTableName("tab");
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	public List<String[]> getTableDetails(String tableName) throws Exception{
		return erpDao.getTableDetails(tableName);
	}
	public List<String[]> getAllProcess() throws Exception{
		return erpDao.getAllProcess();
	}
/*	public String getPrimaryKeyField(String tableName) throws Exception{
		return erpDao.getPrimaryKeyField(tableName);
	}
*/	
	public List<ComboBox> getTableFields(String tableName) throws Exception{
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("column_name");
		
		comboFilter.setIdField("column_name");
		comboFilter.setTableName("user_tab_columns");
		comboFilter.setCondSql(" AND TABLE_NAME = upper('"+tableName+"')");
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public void save(SapTlPerfexMappingMst sapTlPerfexMappingMst)
			throws Exception {
		 Validations validations = new Validations();
		 validations.validate(sapTlPerfexMappingMst, "ERPMappingValidation", "create");
		 erpDao.save(sapTlPerfexMappingMst);
	}
	@Override
	public List<ComboBox> getProcessName(ComboFilter comboFilter)
			throws Exception {
		
		comboFilter.setCodeField("SPMM_PROCESSNAME");
		
		comboFilter.setIdField("SPMM_KEYID");
		comboFilter.setTableName("SAP_TL_PERFEXMAPPINGMST");
		return commonFilterDao.fillComboValues(comboFilter);

		// TODO Auto-generated method stub
		//return null;
	}
	@Override
	public List<String[]> getDetailsData(String mappingId, String forRequest,
			String forResponse) throws Exception {
		// TODO Auto-generated method stub
		return erpDao.getDetailsData(mappingId,forRequest,forResponse);
	}
	
	public SapTlPerfexMappingMst getSapPerfexMappingMst(String keyid) throws Exception{
		return erpDao.getSapPerfexMappingMst(keyid);
	}

	public String getPrimaryKeyField(String tableName) throws Exception{
		return erpDao.getPrimaryKeyField(tableName);
	}
	
	/*public List<ComboBox> getTableFields(String tableName) throws Exception{
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("column_name");
		
		comboFilter.setIdField("column_name");
		comboFilter.setTableName("user_tab_columns");
		comboFilter.setCondSql(" AND TABLE_NAME = upper('"+tableName+"')");
		return commonFilterDao.fillComboValues(comboFilter);
	}*/
	
	public void deleteSapPerfexMappingDtl(String dtlIds) throws Exception{
		erpDao.deleteSapPerfexMappingDtl(dtlIds);
	}
	
	public List<SAPMappingMst> getSapPerfexMappingConfig() throws Exception{
		return erpDao.getSapPerfexMappingConfig();
	}
}
