package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.EmployeeBean;
import com.akranta.tpm.bean.SheTlIncidentEmployeetBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.SheTlIncidentEmployeeDao;
import com.akranta.tpm.dao.sql.EntTlBatchFacultyLinkSql;
import com.akranta.tpm.dao.sql.GenTlEmployeemstSql;
import com.akranta.tpm.dao.sql.SheTlIncidentEmployeeSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.EntTlBatchFacultyLink;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.SheTlIncidentEmployee;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class SheTlIncidentEmployeeDaoImpl implements SheTlIncidentEmployeeDao {
	private static String SEQGEN_EMPLOYEECODE_NUM="SEQGEN_EMPLOYEECODE_NUM";
	public static String DEPT_ID = "DPT000";
	public static String DSG_ID = "DSG000";
	private DBActionTemplate dbActionTemplate; 

	public SheTlIncidentEmployeeDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<String> createEmployee(GenTlEmployeemst genTlEmployeemst,SheTlIncidentEmployee sheTlIncidentEmployee,SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean,List<String> sqls) 	throws Exception {
		GenTlEmployeemstSql genTlEmployeemstsql = new GenTlEmployeemstSql();
		//fillValues(genTlEmployeemst,sheTlIncidentEmployee,sheTlIncidentEmployeetBean);		
		//genTlEmployeemst.setEmpmCode(dbActionTemplate.getSequenceNumber(SEQGEN_EMPLOYEECODE_NUM,5,"C","",""));
		//genTlEmployeemst.setEmpmEmployeenumber(genTlEmployeemst.getEmpmCode());
		//genTlEmployeemst.setEmpmKeyid(dbActionTemplate.getSequenceNumber(GenTlEmployeemstSql.TBL_GEN_TL_EMPLOYEEMST,8,"EMP","","")); // set the sequnce number 
		//sheTlIncidentEmployee.setSiemEmpmKeyid(genTlEmployeemst.getEmpmKeyid());
		//sqls.add(GenTlEmployeemstSql.getInsertSql(genTlEmployeemstsql.getEmpmDbFields(), genTlEmployeemst.getSaveArray())); // add insert sql for master table
		
		return sqls;
	}
	public SheTlIncidentEmployee create(SheTlIncidentEmployee sheTlIncidentEmployee,SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean) 	throws BusinessApplicationExceptions,Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SheTlIncidentEmployeeSql sheTlIncidentEmployeeSql = new SheTlIncidentEmployeeSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		if(CommonFunctions.isValidKeyId(sheTlIncidentEmployeetBean.getContractEmp()))
		{
			GenTlEmployeemst genTlEmployeemst = new GenTlEmployeemst();
			//createEmployee(genTlEmployeemst,sheTlIncidentEmployee,sheTlIncidentEmployeetBean,sqls);
			sheTlIncidentEmployee.setSiemEmpmKeyid(sheTlIncidentEmployeetBean.getContractEmp());
		}
			sheTlIncidentEmployee.setSiemKeyid(dbActionTemplate.getSequenceNumber(SheTlIncidentEmployeeSql.TBL_SHE_TL_INCIDENT_EMPLOYEE, 10, "SIE", null, "N")); // set the sequnce number 
			sqls.add(SheTlIncidentEmployeeSql.getInsertSql(sheTlIncidentEmployeeSql.getSiemDbFields(), sheTlIncidentEmployee.getSaveArray())); // add insert sql for master table
			String injuryCount = dbActionTemplate.getSingleValue(SheTlIncidentEmployeeSql.getInjuryCountSql(sheTlIncidentEmployee.getSiemSincKeyid()));
			if(UIUtils.isValidKeyId(injuryCount))
			{
				int injCnt = Integer.parseInt(injuryCount)+1;
				sqls.add(SheTlIncidentEmployeeSql.updateNoofEmpSql(Integer.toString(injCnt),sheTlIncidentEmployee.getSiemSincKeyid()));
			}
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		
		return sheTlIncidentEmployee;
	}
	
	public SheTlIncidentEmployee update(SheTlIncidentEmployee sheTlIncidentEmployee,SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		SheTlIncidentEmployeeSql sheTlIncidentEmployeeSql = new SheTlIncidentEmployeeSql();
		try {
			if(CommonFunctions.isValidKeyId(sheTlIncidentEmployeetBean.getContractEmp()))
			{
				GenTlEmployeemst genTlEmployeemst = new GenTlEmployeemst();
				//createEmployee(genTlEmployeemst,sheTlIncidentEmployee,sheTlIncidentEmployeetBean,sqls);
				sheTlIncidentEmployee.setSiemEmpmKeyid(sheTlIncidentEmployeetBean.getContractEmp());
			}
			sqls.add(SheTlIncidentEmployeeSql.getUpdateSql(sheTlIncidentEmployeeSql.getSiemDbFields(), sheTlIncidentEmployee.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return sheTlIncidentEmployee;
	}
	
	public SheTlIncidentEmployee delete(SheTlIncidentEmployee sheTlIncidentEmployee)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		SheTlIncidentEmployeeSql sheTlIncidentEmployeeSql = new SheTlIncidentEmployeeSql();
		try {
			
			sqls.add(SheTlIncidentEmployeeSql.getDeleteSql(sheTlIncidentEmployeeSql.getSiemDbFields(), sheTlIncidentEmployee.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return sheTlIncidentEmployee;
	}
	
	public List<String[]> getIncidentEmployee(SheTlIncidentEmployee sheTlIncidentEmployee)throws Exception
	{
		try
		{
			String sql = SheTlIncidentEmployeeSql.getIncidentEmployeesql(sheTlIncidentEmployee);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	private GenTlEmployeemst fillValues(GenTlEmployeemst newGenTlEmployeemst,SheTlIncidentEmployee sheTlIncidentEmployee,SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean) throws Exception {
	
		String dateTime = CommonFunctions.dateTimeNow();
		System.out.println(dateTime);
		List<String> sqls = new ArrayList<String>(); 
		if(UIUtils.isValidKeyId(sheTlIncidentEmployeetBean.getContractEmp()))
			newGenTlEmployeemst.setEmpmName(sheTlIncidentEmployeetBean.getContractEmp());
		
		if(UIUtils.isValidKeyId(sheTlIncidentEmployeetBean.getContractComp()))
			newGenTlEmployeemst.setEmpmCompany(sheTlIncidentEmployeetBean.getContractComp());
		else
			newGenTlEmployeemst.setEmpmCompany("{}");
		newGenTlEmployeemst.setEmpmCreatedon(dateTime);	
		newGenTlEmployeemst.setEmpmModifiedon(dateTime);
		if( newGenTlEmployeemst.getEmpmCreatedby() == null )
			newGenTlEmployeemst.setEmpmCreatedby("{}");		
		System.out.println("fill values:");
		
		if( newGenTlEmployeemst.getEmpmFactoryid() == null )
			newGenTlEmployeemst.setEmpmFactoryid("{}");
	
		if( newGenTlEmployeemst.getEmpmSectionid() == null )
			newGenTlEmployeemst.setEmpmSectionid("{}");
		
		if( newGenTlEmployeemst.getEmpmCellid() == null )
			newGenTlEmployeemst.setEmpmCellid("{}");
		
		if( newGenTlEmployeemst.getEmpmGradeid() == null )
			newGenTlEmployeemst.setEmpmGradeid("{}");		
		
		if( newGenTlEmployeemst.getEmpmSkillcategory() == null )
			newGenTlEmployeemst.setEmpmSkillcategory("{}");		
		
		if( newGenTlEmployeemst.getEmpmGender() == null )
			newGenTlEmployeemst.setEmpmGender("{}");
		
		if( newGenTlEmployeemst.getEmpmEmail() == null )
			newGenTlEmployeemst.setEmpmEmail("{}");
		
		if( newGenTlEmployeemst.getEmpmMobile() == null )
			newGenTlEmployeemst.setEmpmMobile("{}");
		
		if( newGenTlEmployeemst.getEmpmTradeid() == null )
			newGenTlEmployeemst.setEmpmTradeid("{}");
		
		if( newGenTlEmployeemst.getEmpmActive() == null )
			newGenTlEmployeemst.setEmpmActive("Y");
		
		if( newGenTlEmployeemst.getEmpmCode() == null )
			newGenTlEmployeemst.setEmpmCode("{}");
		
		
		if( newGenTlEmployeemst.getEmpmDepartmentid() == null )
		{
			String deptId = dbActionTemplate.getSingleValue(SheTlIncidentEmployeeSql.checkEmpRelatedField(TableNames.TBL_GEN_TL_DEPARTMENTMST,"DEPT_KEYID",DEPT_ID));
			if(UIUtils.isValidKeyId(deptId))
			{
				if(Integer.parseInt(deptId) <= 0)
				{
					CommonFunctions.debugMsg("DEPT SQL : "+SheTlIncidentEmployeeSql.InsertDept( newGenTlEmployeemst.getEmpmCreatedby(), newGenTlEmployeemst.getEmpmCreatedon()));
					sqls.add(SheTlIncidentEmployeeSql.InsertDept( newGenTlEmployeemst.getEmpmCreatedby(), newGenTlEmployeemst.getEmpmCreatedon()));
				}
				newGenTlEmployeemst.setEmpmDepartmentid(DEPT_ID);
			}
		}
		
		if( newGenTlEmployeemst.getEmpmDesignationid() == null )
		{
			String dsgId = dbActionTemplate.getSingleValue(SheTlIncidentEmployeeSql.checkEmpRelatedField(TableNames.TBL_GEN_TL_DESIGNATIONMST,"DESG_KEYID",DSG_ID));
			CommonFunctions.debugMsg("dESIGNATION : "+dsgId);
			if(UIUtils.isValidKeyId(dsgId))
			{
				if(Integer.parseInt(dsgId) <= 0)
				{
					CommonFunctions.debugMsg("dESIGNATION SQL : "+SheTlIncidentEmployeeSql.InsertDesignation( newGenTlEmployeemst.getEmpmCreatedby(), newGenTlEmployeemst.getEmpmCreatedon()));
					sqls.add(SheTlIncidentEmployeeSql.InsertDesignation( newGenTlEmployeemst.getEmpmCreatedby(), newGenTlEmployeemst.getEmpmCreatedon()));
				}
			}
			newGenTlEmployeemst.setEmpmDesignationid(DSG_ID);
			CommonFunctions.debugMsg("dESIGNATION : "+dsgId);
		}
		
		if( newGenTlEmployeemst.getEmpmEmployeenumber() == null )
			newGenTlEmployeemst.setEmpmEmployeenumber("{}");
		
		System.out.println("type:"+newGenTlEmployeemst.getEmpmEmployeetype());
		if( newGenTlEmployeemst.getEmpmEmployeetype() == null )
			newGenTlEmployeemst.setEmpmEmployeetype("C");
		
		
		if( newGenTlEmployeemst.getEmpmExtensionphone() == null )
			newGenTlEmployeemst.setEmpmExtensionphone("{}");
		
		if( newGenTlEmployeemst.getEmpmIscellmanager() == null )
			newGenTlEmployeemst.setEmpmIscellmanager("N");
		
		
		if( newGenTlEmployeemst.getEmpmIssectionmanager() == null )
			newGenTlEmployeemst.setEmpmIssectionmanager("N");
		
		if( newGenTlEmployeemst.getEmpmIsoperator() == null )
			newGenTlEmployeemst.setEmpmIsoperator("N");
		
	
		
		if( !UIUtils.isValidKeyId(newGenTlEmployeemst.getEmpmRoleid()))
			newGenTlEmployeemst.setEmpmRoleid("-");
		
		if( newGenTlEmployeemst.getEmpmSbuId()== null )
			newGenTlEmployeemst.setEmpmSbuId("{}");
		
		if( newGenTlEmployeemst.getEmpmenablemail() == null )
			newGenTlEmployeemst.setEmpmenablemail("-");
		
		if( newGenTlEmployeemst.getEmpmLocation() == null )
			newGenTlEmployeemst.setEmpmLocation("{}");
		
		if( newGenTlEmployeemst.getEmpmIsshiftincharge() == null )
			newGenTlEmployeemst.setEmpmIsshiftincharge("N");
		
		
		
		if( newGenTlEmployeemst.getEmpmJoineddate() == null )
			newGenTlEmployeemst.setEmpmJoineddate(Constants.passNullDate);
		
		
		if( newGenTlEmployeemst.getEmpmPersonalinfo() == null )
			newGenTlEmployeemst.setEmpmPersonalinfo("{}");
		
		if( newGenTlEmployeemst.getEmpmRemarks() == null )
			newGenTlEmployeemst.setEmpmRemarks("{}");
		
		dbActionTemplate.executeStatements(sqls);
		
			System.out.println("fill values:"+newGenTlEmployeemst);		
			
		return newGenTlEmployeemst; 
		
	}
	

}



