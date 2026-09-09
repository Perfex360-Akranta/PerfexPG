
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.ArrayList;
import java.util.List;

//import org.apache.catalina.connector.Request;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EmployeeCostDao;
import com.akranta.tpm.dao.GenTlEmployeecostDao;
import com.akranta.tpm.dao.WomTlContractormstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.EmployeeCostDaoImpl;
import com.akranta.tpm.dao.impl.GenTlEmployeecostDaoImpl;
import com.akranta.tpm.dao.impl.WomTlContractormstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.GenTlEmployeecost;
import com.akranta.tpm.model.WomTlContractormst;
import com.akranta.tpm.service.EmployeeCostService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class EmployeeCostServiceImpl implements EmployeeCostService {
	
	private CommonFilterDao commonFilterDao;	
	private EmployeeCostDao employeeCostDao;
	private GenTlEmployeecostDao genTlEmployeecostDao;
	private WomTlContractormstDao womTlContractormstDao;
		
	private Validations validations;
	
		public EmployeeCostServiceImpl(DBActionTemplate dbActionTemplate)
		{
			commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
			employeeCostDao = new EmployeeCostDaoImpl(dbActionTemplate);
			genTlEmployeecostDao = new GenTlEmployeecostDaoImpl(dbActionTemplate);
			womTlContractormstDao = new WomTlContractormstDaoImpl(dbActionTemplate);			
			
			validations = new Validations();
		}	

		public List<ComboBox> getEmployee(String condSql) throws Exception
		{
			ComboFilter comboFilter = new ComboFilter();
			comboFilter.setNameField("EMPM_NAME");
			comboFilter.setCodeField("EMPM_CODE");
			comboFilter.setIdField("EMPM_KEYID");
			comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
			if (condSql != null)
				comboFilter.setCondSql(condSql);			
			return commonFilterDao.fillComboValues(comboFilter);
		}

		public List<ComboBox> getDepartment(String condSql) throws Exception
		{
			ComboFilter comboFilter = new ComboFilter();
			comboFilter.setNameField("DEPT_NAME");
			comboFilter.setIdField("DEPT_KEYID");	
			comboFilter.setTableName(TableNames.TBL_GEN_TL_DEPARTMENTMST);
			condSql =  "  AND DEPT_ACTIVE ='Y' ";
			comboFilter.setCondSql(condSql);			
			return commonFilterDao.fillComboValues(comboFilter);
		}

		public List<ComboBox> getDesignation(String condSql) throws Exception
		{
			ComboFilter comboFilter = new ComboFilter();
			comboFilter.setNameField("DESG_NAME");
			comboFilter.setIdField("DESG_KEYID");	
			comboFilter.setTableName(TableNames.TBL_GEN_TL_DESIGNATIONMST);
			condSql =  "  AND DESG_ACTIVE ='Y' ";
			comboFilter.setCondSql(condSql);			
			return commonFilterDao.fillComboValues(comboFilter);
		}
		
		public List<ComboBox> getUtilities(String condSql) throws Exception
		{
			ComboFilter comboFilter = new ComboFilter();
			comboFilter.setNameField("TOLM_NAME");
			comboFilter.setIdField("TOLM_KEYID");	
			comboFilter.setTableName(TableNames.TBL_GEN_TL_TOOLSMST);			
			condSql =  " AND TOLM_TYPE ='U' AND TOLM_ACTIVE ='Y' ";
			comboFilter.setCondSql(condSql);		
			return commonFilterDao.fillComboValues(comboFilter);		
		}
		
		public List<ComboBox> getVendor(String condSql) throws Exception
		{
			ComboFilter comboFilter = new ComboFilter();
			comboFilter.setNameField("AMVM_NAME");
			comboFilter.setIdField("AMVM_KEYID");	
			comboFilter.setTableName(TableNames.TBL_GEN_TL_AMCVENDORMST);

			condSql =  "  AND AMVM_ACTIVE ='Y' ";
			comboFilter.setCondSql(condSql);
			
			return commonFilterDao.fillComboValues(comboFilter);		
		}
		
		public List<String []> getEmpDeptDes(String EmpId) throws Exception
		{
			CommonMessage.debugMsg("Inside empcost");
			return this.employeeCostDao.getEmpDeptDes(EmpId);
		}
		
		public List<String[]>  getEmployeeCostGrid(String deptId, String desId, String empId) throws Exception
		{
			return this.employeeCostDao.getEmployeeCostGridQry(deptId, desId, empId);
		}

		public List<String[]>  getUtilityCostGrid(String vendorId) throws Exception
		{
			return this.employeeCostDao.getUtilityCostGridQry(vendorId);
		}

		public List<String[]>  getContractorCostGrid(String utilityId) throws Exception
		{
			return this.employeeCostDao.getContractorCostGridQry(utilityId);
		}

		public GenTlEmployeecost create(GenTlEmployeecost newGenTlEmployeecost,GenTlEmployeecost oldGenTlEmployeecost ) throws ValidationExceptions, Exception
		{
			try {
				CommonMessage.debugMsg("Inside Impl11111");			
				//validations.validate(newGenTlEmployeecost,"employeeCostCreation","create");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations			
				CommonMessage.debugMsg("After Validate");
				fillValuesEmployeeCost(newGenTlEmployeecost,oldGenTlEmployeecost);
				return genTlEmployeecostDao.create(newGenTlEmployeecost);
			}catch (ValidationExceptions e){
				throw new ValidationExceptions(e.getMessage());
			}			
		}

		public GenTlEmployeecost update(GenTlEmployeecost newGenTlEmployeecost,GenTlEmployeecost oldGenTlEmployeecost)  throws Exception {
			try {
				CommonMessage.debugMsg("update " +oldGenTlEmployeecost.getEmpcKeyid());
				//validations.validate(newGenTlEmployeecost,"employeeCostCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations		
				fillValuesEmployeeCost(newGenTlEmployeecost,oldGenTlEmployeecost);		
				return genTlEmployeecostDao.update(newGenTlEmployeecost);
			}catch (ValidationExceptions e){
				throw new ValidationExceptions(e.getMessage());
			}
		}
		

		public WomTlContractormst createContractor(WomTlContractormst newWomTlContractormst,WomTlContractormst oldWomTlContractormst ) throws ValidationExceptions, Exception
		{
			try {
				CommonMessage.debugMsg("Inside Impl11111");			
				//validations.validate(newWomTlContractormst,"employeeCostCreation","create");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations			
				CommonMessage.debugMsg("After Validate");
				fillValuesContractorCost(newWomTlContractormst,oldWomTlContractormst);
				return womTlContractormstDao.create(newWomTlContractormst);
			}catch (ValidationExceptions e){
				throw new ValidationExceptions(e.getMessage());
			}			
		}

		public WomTlContractormst updateContractor(WomTlContractormst newWomTlContractormst,WomTlContractormst oldWomTlContractormst)  throws Exception {
			try {
				CommonMessage.debugMsg("update " +oldWomTlContractormst.getCncsKeyid());
				//validations.validate(newWomTlContractormst,"employeeCostCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations		
				fillValuesContractorCost(newWomTlContractormst,oldWomTlContractormst);		
				return womTlContractormstDao.update(newWomTlContractormst);
			}catch (ValidationExceptions e){
				throw new ValidationExceptions(e.getMessage());
			}
		}
				
		private GenTlEmployeecost fillValuesEmployeeCost(GenTlEmployeecost newGenTlEmployeecost,GenTlEmployeecost oldGenTlEmployeecost) {
			
			CommonMessage.debugMsg("fill values:");			
			String dateTime = CommonFunctions.dateTimeNow();			
			newGenTlEmployeecost.setEmpcCreatedon(dateTime);
			newGenTlEmployeecost.setEmpcModifiedon(dateTime);		
			newGenTlEmployeecost.setEmpcActive("Y");
			
			if( newGenTlEmployeecost.getEmpcOtcostperhour() == null )
				newGenTlEmployeecost.setEmpcOtcostperhour("0");
			if( newGenTlEmployeecost.getEmpcCalloutcostperhour() == null )
				newGenTlEmployeecost.setEmpcCalloutcostperhour("0");
			
			newGenTlEmployeecost.setEmpcEffectivetilldate(Constants.futureNullDate);
			
			newGenTlEmployeecost.setEmpcTempfield2("{}");		
			newGenTlEmployeecost.setEmpcTempfield3("{}");
			newGenTlEmployeecost.setEmpcTempfield4("{}");
			newGenTlEmployeecost.setEmpcTempfield5("{}");
			
			return newGenTlEmployeecost; 
		}

		private WomTlContractormst fillValuesContractorCost(WomTlContractormst newWomTlContractormst,WomTlContractormst oldWomTlContractormst) {
			
			CommonMessage.debugMsg("fill values:");			
			String dateTime = CommonFunctions.dateTimeNow();			
			newWomTlContractormst.setCncsCreatedon(dateTime);
			newWomTlContractormst.setCncsModifiedon(dateTime);		
			newWomTlContractormst.setCncsActive("Y");
			
			if( newWomTlContractormst.getCncsOtcost() == null )
				newWomTlContractormst.setCncsOtcost("0");
			if( newWomTlContractormst.getCncsHolidaycost() == null )
				newWomTlContractormst.setCncsHolidaycost("0");
			
			newWomTlContractormst.setCncsEffectivetodate(Constants.futureNullDate);
			
			newWomTlContractormst.setCncsTempfield1("{}");		
			newWomTlContractormst.setCncsTempfield2("{}");
			newWomTlContractormst.setCncsTempfield3("{}");
			newWomTlContractormst.setCncsTempfield4("{}");
			
			return newWomTlContractormst; 
		}
}
