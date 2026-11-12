package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.SheTlIncidentEmployeenewBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.SheTlIncidentEmployeenewDao;
import com.akranta.tpm.dao.sql.SheTlIncidentEmployeenewSql;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.SheTlIncidentEmployeenew;
//import com.akranta.tpm.service.api.;

/* dao implementation */
public class SheTlIncidentEmployeenewDaoImpl implements SheTlIncidentEmployeenewDao {


	private DBActionTemplate dbActionTemplate; 

	public SheTlIncidentEmployeenewDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public SheTlIncidentEmployeenew create(SheTlIncidentEmployeenew sheTlIncidentEmployee,SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean) 	throws BusinessApplicationExceptions,Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SheTlIncidentEmployeenewSql sheTlIncidentEmployeeSql = new SheTlIncidentEmployeenewSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		if(CommonFunctions.isValidKeyId(sheTlIncidentEmployeetBean.getContractEmp()))
		{
			GenTlEmployeemst genTlEmployeemst = new GenTlEmployeemst();
			//createEmployee(genTlEmployeemst,sheTlIncidentEmployee,sheTlIncidentEmployeetBean,sqls);
			sheTlIncidentEmployee.setSineEmpmKeyid(sheTlIncidentEmployeetBean.getContractEmp());
		}
			sheTlIncidentEmployee.setSineKeyid(dbActionTemplate.getSequenceNumber(SheTlIncidentEmployeenewSql.TBL_SHE_TL_INCIDENT_EMPLOYEENEW, 10, "SIE", null, "N")); // set the sequnce number 
			sqls.add(SheTlIncidentEmployeenewSql.getInsertSql(sheTlIncidentEmployeeSql.getSineDbFields(), sheTlIncidentEmployee.getSaveArray())); // add insert sql for master table
			String injuryCount = dbActionTemplate.getSingleValue(SheTlIncidentEmployeenewSql.getInjuryCountSql(sheTlIncidentEmployee.getSineSincKeyid()));
			if(UIUtils.isValidKeyId(injuryCount))
			{
				int injCnt = Integer.parseInt(injuryCount)+1;
				sqls.add(SheTlIncidentEmployeenewSql.updateNoofEmpSql(Integer.toString(injCnt),sheTlIncidentEmployee.getSineSincKeyid()));
			}
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		
		return sheTlIncidentEmployee;
	}
	
public SheTlIncidentEmployeenew update(SheTlIncidentEmployeenew sheTlIncidentEmployee,SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		SheTlIncidentEmployeenewSql sheTlIncidentEmployeeSql = new SheTlIncidentEmployeenewSql();
		try {
			if(CommonFunctions.isValidKeyId(sheTlIncidentEmployeetBean.getContractEmp()))
			{
				GenTlEmployeemst genTlEmployeemst = new GenTlEmployeemst();
				//createEmployee(genTlEmployeemst,sheTlIncidentEmployee,sheTlIncidentEmployeetBean,sqls);
				sheTlIncidentEmployee.setSineEmpmKeyid(sheTlIncidentEmployeetBean.getContractEmp());
			}
			sqls.add(SheTlIncidentEmployeenewSql.getUpdateSql(sheTlIncidentEmployeeSql.getSineDbFields(), sheTlIncidentEmployee.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return sheTlIncidentEmployee;
	}

@Override
public SheTlIncidentEmployeenew delete(SheTlIncidentEmployeenew sheTlIncidentEmployeenew) throws Exception {

		List<String> sqls = new ArrayList<String>();
		SheTlIncidentEmployeenewSql sheTlIncidentEmployeeSql = new SheTlIncidentEmployeenewSql();
		try {
			
			sqls.add(SheTlIncidentEmployeenewSql.getDeleteSql(sheTlIncidentEmployeeSql.getSineDbFields(), sheTlIncidentEmployeenew.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return sheTlIncidentEmployeenew;
	}
	
	/*public SheTlIncidentEmployeenew delete(SheTlIncidentEmployeenew sheTlIncidentEmployeenew)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		SheTlIncidentEmployeenewSql sheTlIncidentEmployeenewSql = new SheTlIncidentEmployeenewSql();
		try {
			
			sqls.add(SheTlIncidentEmployeenew.getDeleteSql(sheTlIncidentEmployeenewSql.getSineDbFields(), sheTlIncidentEmployeenew.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return sheTlIncidentEmployeenew;
	}*/
	
}

