package com.akranta.tpm.dao.sql;

import java.sql.Types;

public class EmployeeSqls {
	
	public static final String TBL_GEN_TL_EMPLOYEEMST = "GEN_TL_EMPLOYEEMST";
	public static final String TBL_GEN_TL_EMPLOYEEDTL = "GEN_TL_EMPLOYEEDTL";
	
	public static final int [] employeeMst_types = new int [] {
					Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.CHAR   ,Types.VARCHAR,Types.DATE,
					Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.CHAR   ,Types.VARCHAR,Types.VARCHAR,
					Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
					Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.VARCHAR,Types.VARCHAR,Types.CHAR,
					Types.VARCHAR,Types.DATE,Types.DATE };
	
	public static final int [] employeeDtl_types = new int [] {
					Types.VARCHAR,Types.DATE,Types.VARCHAR,Types.VARCHAR   ,Types.VARCHAR,Types.VARCHAR,
					Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,
					Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.CHAR, Types.VARCHAR,Types.DATE,
					Types.DATE };
				
	public static String getInsertSqlForEMPM()
	{
		return " insert into " + TBL_GEN_TL_EMPLOYEEMST + "( empm_keyid ,empm_name ,empm_code," +
			   " empm_employeetype,empm_employeenumber ,empm_joineddate values, " +
			   " empm_departmentid , empm_designationid,empm_factoryid,empm_isshiftincharge ,empm_sectionid," +
			   " empm_iscellmanager ,empm_cellid,empm_tradeid,empm_extensionphone,  " +
			   " empm_fax,empm_mobile,empm_email,empm_personalinfo," +
			   " empm_remarks  ,empm_issectionmanager,empm_skillcategory ,empm_gradeid, " +
			   " empm_active,empm_createdby,empm_createdon ,empm_modifiedon values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	}
	
	public static String getUpdateSqlForEMPM()
	{
		return " update " + TBL_GEN_TL_EMPLOYEEMST + " set empm_keyid =?,empm_name =?,empm_code=?," +
			   " empm_employeetype=?,empm_employeenumber =?,empm_joineddate values=?, " +
			   " empm_departmentid =?, empm_designationid=?,empm_factoryid=?,empm_isshiftincharge =?,empm_sectionid=?," +
			   " empm_iscellmanager =?,empm_cellid=?,empm_tradeid=?,empm_extensionphone=?,  " +
			   " empm_fax=?,empm_mobile=?,empm_email=?,empm_personalinfo=?," +
			   " empm_remarks  =?,empm_issectionmanager=?,empm_skillcategory =?,empm_gradeid=?, " +
			   " empm_active=?,empm_createdby=?,empm_createdon =?,empm_modifiedon=?";
	}
	
	public static String getDeleteSqlForEMPM()
	{
		return " delete from " + TBL_GEN_TL_EMPLOYEEMST + " where empm_keyid = ?"; 
	}
	
	public static String getInsertSqlForEMPD()
	{
		return " insert into " + TBL_GEN_TL_EMPLOYEEDTL + "( empd_keyid ,empd_birthdate,empd_address,empd_cityid,empd_stateid," +
			   " empd_countryid,empd_phone,empd_image,empd_remarks, " + 
			   " empd_currentexperience,empd_otherexperience, " +
			   " empd_totalexperience,empd_qualification  ,empd_discipline,empd_active, " +
			   " empd_createdby,empd_createdon ,empd_modifiedon ) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
	}
	
	public static String getUpdateSqlForEMPD()
	{
		return " update " + TBL_GEN_TL_EMPLOYEEDTL + " set empd_keyid =?,empd_birthdate=?,empd_address=?,empd_cityid=?,empd_stateid=?," +
			   " empd_countryid=?,empd_phone=?,empd_image=?,empd_remarks=?, " + 
			   " empd_currentexperience=?,empd_otherexperience=?, " +
			   " empd_totalexperience=?,empd_qualification  =?,empd_discipline=?,empd_active=?, " +
			   " empd_createdby=?,empd_createdon =?,empd_modifiedon =?"; 
	}

	public static String getDeleteSqlForEMPD()
	{
		return " delete from " + TBL_GEN_TL_EMPLOYEEDTL + " where empd_keyid = ?"; 
	}

}
