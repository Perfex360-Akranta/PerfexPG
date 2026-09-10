package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_BdmTlNewphncausereqDao;
import com.akranta.tpm.dao.BAL_BdmTlPhenomenamstDao;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.BAL_CostInfoRptDao;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.BAL_CostInfoRptDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_CostInfoService;



public class BAL_CostInfoServiceImpl implements BAL_CostInfoService{

	private BAL_CostInfoRptDao costinfoDao;
	
	private BAL_CommonFilterDao commonFilterDao;	
	
	public BAL_CostInfoServiceImpl(DBActionTemplate dbActionTemplate)
	{
		costinfoDao = new BAL_CostInfoRptDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
	
	}
	
	public List<String[]> getAllCostInfo(CommonFilter commonFilter ) throws Exception
	{
		return this.costinfoDao.getAllCostInfo( commonFilter);
	}


	public List<ComboBox> getBD(String condSql) throws Exception
	{
		System.out.println("service impl");
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("BDMS_KEYID");
		comboFilter.setIdField("BDMS_KEYID");	
		comboFilter.setTableName(TableNames.TBL_BDM_TL_MST);
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	
	public List<ComboBox> getPhenomena(String condSql,ComboFilter comboFilter) throws Exception
	{
		System.out.println("service impl");
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("BPHM_PHENOMENANAME");
		comboFilter.setIdField("BPHM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_BDM_TL_PHENOMENAMST);
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getCause(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("BCSM_NAME");
		comboFilter.setIdField("BCSM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_BAL_BDM_TL_CAUSEMST);
		return commonFilterDao.fillComboValues(comboFilter);		
	}

	public List<ComboBox> getEmpgrade(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("GRDM_NAME");
		comboFilter.setIdField("GRDM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPGRADEMST);

		 condSql =  "  AND GRDM_ACTIVE='Y' ";
		comboFilter.setCondSql(condSql);

		return commonFilterDao.fillComboValues(comboFilter);		
	}
	
	public List<ComboBox> getVendor(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("AMVM_NAME");
		comboFilter.setIdField("AMVM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_AMCVENDORMST);

		condSql =  "  AND AMVM_ACTIVE ='Y' ";
		comboFilter.setCondSql(condSql);
		
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getContractor(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("UNGM_NAME");
		comboFilter.setIdField("UNGM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_PLM_TL_UNSKILLEDGRADEMST_I);
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	
	
	
	  public List<ComboBox> getSpares(String condSql,ComboFilter comboFilter) throws Exception {
	 // ComboFilter comboFilter = new ComboFilter();
	  comboFilter.setNameField("SPRM_PARTNAME");
	  comboFilter.setCodeField("SPRM_PARTNO");
	  comboFilter.setIdField("SPRM_KEYID"); if (! condSql.equals(null))
	  comboFilter.setCondSql(condSql);
	  
	  comboFilter.setTableName(TableNames.TBL_GEN_TL_SPARESMST); return
	  commonFilterDao.fillComboValues(comboFilter); }
	 
	
	
	/*
	 * public List<ComboBox> getSpares(String condSql, String searchText) throws
	 * Exception { ComboFilter comboFilter = new ComboFilter();
	 * comboFilter.setNameField("SPRM_PARTNAME");
	 * comboFilter.setCodeField("SPRM_PARTNO");
	 * comboFilter.setIdField("SPRM_KEYID"); if (condSql != null && condSql.length()
	 * > 0) comboFilter.setCondSql(condSql);
	 * 
	 * if (searchText != null && searchText.trim().length() > 0) {
	 * comboFilter.setName(searchText.trim());
	 * comboFilter.setCode(searchText.trim()); }
	 * 
	 * comboFilter.setTableName(TableNames.TBL_GEN_TL_SPARESMST); return
	 * commonFilterDao.fillComboValues(comboFilter); }
	 */
	
	public List<ComboBox> getService(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("AMVM_NAME");
		comboFilter.setIdField("AMVM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_AMCVENDORMST);
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getUtilities(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("TOLM_NAME");
		comboFilter.setIdField("TOLM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_GEN_TL_TOOLSMST);
		
		condSql =  " AND TOLM_TYPE ='U' AND TOLM_ACTIVE ='Y' ";
		comboFilter.setCondSql(condSql);		
		return commonFilterDao.fillComboValues(comboFilter);		
	}
	public List<ComboBox> getExpense(String condSql,ComboFilter comboFilter) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("OTCM_COSTNAME");
		comboFilter.setIdField("OTCM_KEYID");	
		comboFilter.setTableName(TableNames.TBL_WOM_TL_OTHERCOSTMST);

		//comboFilter.setCondSql(condSql);
		
		return commonFilterDao.fillComboValues(comboFilter);		
	}

	
		
}

