package com.akranta.tpm.dao.sql;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlEmployeemstUplSql {

	public static final String TBL_GEN_TL_EMPLOYEEMSTUPL = "GEN_TL_EMPLOYEEMSTUPL";  

	TableFieldType [] empuDbFields = null;

	public enum   tableFldConstants
	{
		keyid,excelrowno,name,code,employeetype, employeenumber,companyid,companycode,locationid,locationcode,cellid,
		sectionid,loginid,defaultpassword,roleid1,roleid2,roleid3,roleid4,roleid5,roleid6,
		roleid7,roleid8,roleid9,roleid10,joineddate,departmentid,departmentcode
		,designationid,designationcode,factoryid, isshiftincharge, iscellmanager
		,tradeid, extensionphone,gender, mobile, email, personalinfo
		,remarks, issectionmanager, skillcategory, gradeid, isoperator
		,sbuid, embmenablemail, active, createdby, createdon, modifiedon,errorflag,errormsg
	}

	public TableFieldType[] getempuDbFields() {
		return empuDbFields;
	}

	public GenTlEmployeemstUplSql()
	{
		empuDbFields = new TableFieldType[ 51 ];
		for(int i = 0;i <51; i++)
		{	
			empuDbFields[ i ] = new TableFieldType();
		}
		empuDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EMPU_KEYID";
		empuDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.excelrowno.ordinal() ].fieldName = "EMPU_EXCELROWNO";
		empuDbFields[ tableFldConstants.excelrowno.ordinal() ].fieldType = 'N';

		empuDbFields[ tableFldConstants.name.ordinal() ].fieldName = "EMPU_NAME";
		empuDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.code.ordinal() ].fieldName = "EMPU_CODE";
		empuDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.employeetype.ordinal() ].fieldName = "EMPU_EMPLOYEETYPE";
		empuDbFields[ tableFldConstants.employeetype.ordinal() ].fieldType = 'C';

		empuDbFields[ tableFldConstants.employeenumber.ordinal() ].fieldName = "EMPU_EMPLOYEENUMBER";
		empuDbFields[ tableFldConstants.employeenumber.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.companyid.ordinal() ].fieldName = "EMPU_COMPANYID";
		empuDbFields[ tableFldConstants.companyid.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.companycode.ordinal() ].fieldName = "EMPU_COMPANYCODE";
		empuDbFields[ tableFldConstants.companycode.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.locationid.ordinal() ].fieldName = "EMPU_LOCATIONID";
		empuDbFields[ tableFldConstants.locationid.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.locationcode.ordinal() ].fieldName = "EMPU_LOCATIONCODE";
		empuDbFields[ tableFldConstants.locationcode.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "EMPU_CELLID";
		empuDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "EMPU_SECTIONID";
		empuDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';
		
		
		empuDbFields[ tableFldConstants.loginid.ordinal() ].fieldName = "EMPU_LOGINID";
		empuDbFields[ tableFldConstants.loginid.ordinal() ].fieldType = 'V';
		
		
		empuDbFields[ tableFldConstants.defaultpassword.ordinal() ].fieldName = "EMPU_DEFAULTPASSWORD";
		empuDbFields[ tableFldConstants.defaultpassword.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.defaultpassword.ordinal() ].fieldName = "EMPU_DEFAULTPASSWORD";
		empuDbFields[ tableFldConstants.defaultpassword.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.roleid1.ordinal() ].fieldName = "EMPU_ROLEID1";
		empuDbFields[ tableFldConstants.roleid1.ordinal() ].fieldType = 'V';
		empuDbFields[ tableFldConstants.roleid2.ordinal() ].fieldName = "EMPU_ROLEID2";
		empuDbFields[ tableFldConstants.roleid2.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.roleid3.ordinal() ].fieldName = "EMPU_ROLEID3";
		empuDbFields[ tableFldConstants.roleid3.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.roleid4.ordinal() ].fieldName = "EMPU_ROLEID4";
		empuDbFields[ tableFldConstants.roleid4.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.roleid5.ordinal() ].fieldName = "EMPU_ROLEID5";
		empuDbFields[ tableFldConstants.roleid5.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.roleid6.ordinal() ].fieldName = "EMPU_ROLEID6";
		empuDbFields[ tableFldConstants.roleid6.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.roleid7.ordinal() ].fieldName = "EMPU_ROLEID7";
		empuDbFields[ tableFldConstants.roleid7.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.roleid8.ordinal() ].fieldName = "EMPU_ROLEID8";
		empuDbFields[ tableFldConstants.roleid8.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.roleid9.ordinal() ].fieldName = "EMPU_ROLEID9";
		empuDbFields[ tableFldConstants.roleid9.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.roleid10.ordinal() ].fieldName = "EMPU_ROLEID10";
		empuDbFields[ tableFldConstants.roleid10.ordinal() ].fieldType = 'V';
		

		empuDbFields[ tableFldConstants.joineddate.ordinal() ].fieldName = "EMPU_JOINEDDATE";
		empuDbFields[ tableFldConstants.joineddate.ordinal() ].fieldType = 'D';

		empuDbFields[ tableFldConstants.departmentid.ordinal() ].fieldName = "EMPU_DEPARTMENTID";
		empuDbFields[ tableFldConstants.departmentid.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.departmentcode.ordinal() ].fieldName = "EMPU_DEPARTMENTCODE";
		empuDbFields[ tableFldConstants.departmentcode.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.designationid.ordinal() ].fieldName = "EMPU_DESIGNATIONID";
		empuDbFields[ tableFldConstants.designationid.ordinal() ].fieldType = 'V';
		
		empuDbFields[ tableFldConstants.designationcode.ordinal() ].fieldName = "EMPU_DESIGNATIONCODE";
		empuDbFields[ tableFldConstants.designationcode.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "EMPU_FACTORYID";
		empuDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.isshiftincharge.ordinal() ].fieldName = "EMPU_ISSHIFTINCHARGE";
		empuDbFields[ tableFldConstants.isshiftincharge.ordinal() ].fieldType = 'C';

		empuDbFields[ tableFldConstants.iscellmanager.ordinal() ].fieldName = "EMPU_ISCELLMANAGER";
		empuDbFields[ tableFldConstants.iscellmanager.ordinal() ].fieldType = 'C';

		empuDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "EMPU_TRADEID";
		empuDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.extensionphone.ordinal() ].fieldName = "EMPU_EXTENSIONPHONE";
		empuDbFields[ tableFldConstants.extensionphone.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.gender.ordinal() ].fieldName = "EMPU_GENDER";
		empuDbFields[ tableFldConstants.gender.ordinal() ].fieldType = 'C';

		empuDbFields[ tableFldConstants.mobile.ordinal() ].fieldName = "EMPU_MOBILE";
		empuDbFields[ tableFldConstants.mobile.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.email.ordinal() ].fieldName = "EMPU_EMAIL";
		empuDbFields[ tableFldConstants.email.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.personalinfo.ordinal() ].fieldName = "EMPU_PERSONALINFO";
		empuDbFields[ tableFldConstants.personalinfo.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "EMPU_REMARKS";
		empuDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.issectionmanager.ordinal() ].fieldName = "EMPU_ISSECTIONMANAGER";
		empuDbFields[ tableFldConstants.issectionmanager.ordinal() ].fieldType = 'C';

		empuDbFields[ tableFldConstants.skillcategory.ordinal() ].fieldName = "EMPU_SKILLCATEGORY";
		empuDbFields[ tableFldConstants.skillcategory.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.gradeid.ordinal() ].fieldName = "EMPU_GRADEID";
		empuDbFields[ tableFldConstants.gradeid.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.isoperator.ordinal() ].fieldName = "EMPU_ISOPERATOR";
		empuDbFields[ tableFldConstants.isoperator.ordinal() ].fieldType = 'C';


		empuDbFields[ tableFldConstants.sbuid.ordinal() ].fieldName = "EMPU_SBUID";
		empuDbFields[ tableFldConstants.sbuid.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.embmenablemail.ordinal() ].fieldName = "EMPU_ENABLEEMAIL";
		empuDbFields[ tableFldConstants.embmenablemail.ordinal() ].fieldType = 'C';

		
		empuDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EMPU_ACTIVE";
		empuDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		empuDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EMPU_CREATEDBY";
		empuDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		empuDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EMPU_CREATEDON";
		empuDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		empuDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EMPU_MODIFIEDON";
		empuDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

		empuDbFields[ tableFldConstants.errorflag.ordinal() ].fieldName = "EMPU_ERRORFLAG";
		empuDbFields[ tableFldConstants.errorflag.ordinal() ].fieldType = 'C';
		
		empuDbFields[ tableFldConstants.errormsg.ordinal() ].fieldName = "EMPU_ERRORMSG";
		empuDbFields[ tableFldConstants.errormsg.ordinal() ].fieldType = 'V';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_EMPLOYEEMSTUPL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_EMPLOYEEMSTUPL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_EMPLOYEEMSTUPL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String selectSql()
	{
		//String sql = "SELECT * from " + TBL_GEN_TL_EMPLOYEEMST ;
		String sql="Select EMPU_KEYID, FACT_CODE ,EMPU_NAME,EMPU_CODE,EMPU_EMPLOYEENUMBER,DESG_NAME AS DESIGNATION,DEPT_NAME AS DEPARTMENT,REPLACE(EMPU_EXTENSIONPHONE,'{}','')AS EXTNPHONE,REPLACE(EMPU_FAX,'{}','') AS EMPU_FAX,REPLACE(EMPU_MOBILE,'{}','') AS EMPU_MOBILE ,REPLACE(EMPU_EMAIL,'{}','')AS EMPU_EMAIL,REPLACE(EMPU_PERSONALINFO,'{}','') AS   PERSONAL_INFO,REPLACE(EMPU_REMARKS,'{}') AS EMPU_REMARKS, GRDM_NAME AS GRADE from     GEN_TL_EMPLOYEEMST    ,    GEN_TL_DEPARTMENTMST    ,    GEN_TL_EMPLOYEEDTL    , GEN_TL_DESIGNATIONMST    ,    GEN_TL_FACTORYMST    ,    GEN_TL_EMPGRADEMST WHERE EMPU_DESIGNATIONID = DESG_KEYID(+) AND EMPU_DEPARTMENTID = DEPT_KEYID(+) AND EMPU_KEYID=EMPD_KEYID(+) AND EMPU_FACTORYID=FACT_KEYID (+) AND EMPU_GRADEID = GRDM_KEYID(+) AND EMPU_ACTIVE = 'Y'";
		//String sql="select * from "+ TBL_GEN_TL_EMPLOYEEMST +" , "+TBL_GEN_TL_EMPLOYEEDTL +" where EMPU_KEYID=EMPD_KEYID(+) and  EMPU_KEYID= ? ";
		return sql;
	}
	
	public static String getEmployeemstSql() {
		// TODO Auto-generated method stub
		return " SELECT * from " + TBL_GEN_TL_EMPLOYEEMSTUPL + " where EMPU_KEYID= ?";
		//CommonMessage.debugMsg("before + ");
		//return "select * from "+ TBL_GEN_TL_EMPLOYEEMST+","+TBL_GEN_TL_EMPLOYEEDTL +" where EMPU_KEYID=EMPD_KEYID and  EMPU_KEYID= ?";
		
	}

}
