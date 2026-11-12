package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.impl.SheTlIncidentEmployeeDaoImpl;
import com.akranta.tpm.model.EntTlBatchFacultyLink;
import com.akranta.tpm.model.SheTlIncidentEmployee;
import com.akranta.tpm.utils.CommonFunctions;

public class SheTlIncidentEmployeeSql {

	public static final String TBL_SHE_TL_INCIDENT_EMPLOYEE = "SHE_TL_INCIDENT_EMPLOYEE";  

	TableFieldType [] siemDbFields = null;

	public enum   tableFldConstants
	{
		keyid, sinc_keyid, empm_keyid, injury_modeid, bodypart, remarks
		, injury_typeid, mode, type, location, tempfield5
	}

	public TableFieldType[] getSiemDbFields() {
		return siemDbFields;
	}

	public SheTlIncidentEmployeeSql()
	{
		siemDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			siemDbFields[ i ] = new TableFieldType();
		}
		siemDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SIEM_KEYID";
		siemDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		siemDbFields[ tableFldConstants.sinc_keyid.ordinal() ].fieldName = "SIEM_SINC_KEYID";
		siemDbFields[ tableFldConstants.sinc_keyid.ordinal() ].fieldType = 'V';

		siemDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "SIEM_EMPM_KEYID";
		siemDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		siemDbFields[ tableFldConstants.injury_modeid.ordinal() ].fieldName = "SIEM_INJURY_MODEID";
		siemDbFields[ tableFldConstants.injury_modeid.ordinal() ].fieldType = 'V';

		siemDbFields[ tableFldConstants.bodypart.ordinal() ].fieldName = "SIEM_BODYPART";
		siemDbFields[ tableFldConstants.bodypart.ordinal() ].fieldType = 'V';

		siemDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "SIEM_REMARKS";
		siemDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		siemDbFields[ tableFldConstants.injury_typeid.ordinal() ].fieldName = "SIEM_INJURY_TYPEID";
		siemDbFields[ tableFldConstants.injury_typeid.ordinal() ].fieldType = 'V';

		siemDbFields[ tableFldConstants.mode.ordinal() ].fieldName = "SIEM_MODE";
		siemDbFields[ tableFldConstants.mode.ordinal() ].fieldType = 'V';

		siemDbFields[ tableFldConstants.type.ordinal() ].fieldName = "SIEM_TYPE";
		siemDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		siemDbFields[ tableFldConstants.location.ordinal() ].fieldName = "SIEM_LOCATION";
		siemDbFields[ tableFldConstants.location.ordinal() ].fieldType = 'V';

		siemDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SIEM_TEMPFIELD5";
		siemDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_INCIDENT_EMPLOYEE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SHE_TL_INCIDENT_EMPLOYEE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_INCIDENT_EMPLOYEE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String deleteBasedSincId(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_INCIDENT_EMPLOYEE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.sinc_keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.sinc_keyid.ordinal()] + "'";
		return sql;
	}
	public static String getIncidentEmployeesql(SheTlIncidentEmployee sheTlIncidentEmployee)
	{
			/*String sql = " SELECT SIEM_KEYID,EMPM_KEYID,EMPM_CODE,EMPM_NAME,INJM_CODE,IBOP_CODE";
			sql += " FROM " + TBL_SHE_TL_INCIDENT_EMPLOYEE + "," + TableNames.TBL_GEN_TL_EMPLOYEEMST + "," + TableNames.TBL_SHE_TL_INJURYMODEMST  ;
			sql += ", "+TableNames.TBL_SHE_TL_INCIDENTBODYPARTMST +"," + TableNames.TBL_SHE_TL_INCIDENTMST;
			sql += " WHERE SIEM_EMPM_KEYID=EMPM_KEYID and SIEM_INJURY_MODEID=INJM_KEYID and SIEM_BODYPART=IBOP_KEYID ";
			CommonFunctions.debugMsg("sql22222"+sql);*/
		String sql = " SELECT DISTINCT SIEM_KEYID,EMPM_KEYID,EMPM_CODE,EMPM_NAME,INJM_NAME,IBOP_NAME";
		sql += " FROM " + TBL_SHE_TL_INCIDENT_EMPLOYEE + "," + TableNames.TBL_GEN_TL_EMPLOYEEMST + "," + TableNames.TBL_SHE_TL_INJURYMODEMST  ;
		sql += ","+ TableNames.TBL_SHE_TL_INCIDENTMST+","+TableNames.TBL_SHE_TL_INCIDENTBODYPARTMST;
		sql += " WHERE SIEM_EMPM_KEYID=EMPM_KEYID and SIEM_INJURY_MODEID=INJM_KEYID AND SIEM_BODYPART = IBOP_KEYID(+)";
		if(CommonFunctions.isValidKeyId(sheTlIncidentEmployee.getSiemSincKeyid()))
			sql += " AND SIEM_SINC_KEYID = '"+sheTlIncidentEmployee.getSiemSincKeyid()+"'";
		if(CommonFunctions.isValidKeyId(sheTlIncidentEmployee.getSiemEmpmKeyid()))
			sql += " AND SIEM_EMPM_KEYID = '"+sheTlIncidentEmployee.getSiemEmpmKeyid()+"'";
		if(CommonFunctions.isValidKeyId(sheTlIncidentEmployee.getSiemInjuryModeid()))
			sql += " AND SIEM_INJURY_MODEID = '"+sheTlIncidentEmployee.getSiemInjuryModeid()+"'";
		
		CommonFunctions.debugMsg("sql22222"+sql);
		return sql;
	}

	public static String getIncidentEmployee() {
		return "select * from SHE_TL_INCIDENT_EMPLOYEE where SIEM_KEYID = ? ";
	}
	public static String getInjuryCountSql(String incId) {
		return "select COUNT(*) from SHE_TL_INCIDENT_EMPLOYEE where SIEM_SINC_KEYID = '"+incId+"'";
	}
	public static String updateNoofEmpSql(String empCnt,String incId) {
		return "UPDATE "+TableNames.TBL_SHE_TL_INCIDENTMST+" set SINC_NOOFEMPLOYEES="+empCnt+" where SINC_KEYID = '"+incId+"'";
	}
	
	public static String checkEmpRelatedField(String tableName,String colName,String val)
	{
		return "SELECT COUNT(*) FROM "+tableName+" WHERE "+colName+" = '"+val+"'";
	}
	
	public static String InsertDept(String createdOn,String createdDate)
	{
		String sql = "INSERT INTO "+TableNames.TBL_GEN_TL_DEPARTMENTMST;
			   sql += " VALUES('"+SheTlIncidentEmployeeDaoImpl.DEPT_ID+"','-','-','{}','N',";
			   sql += "'-','-','-','-','-','Y','"+createdOn+"',SYSDATE,SYSDATE)";
			   
	    return sql;
	}
	public static String InsertDesignation(String createdOn,String createdDate)
	{
		String sql = "INSERT INTO "+TableNames.TBL_GEN_TL_DESIGNATIONMST;
			   sql += " VALUES('"+SheTlIncidentEmployeeDaoImpl.DSG_ID+"','-','-',0,'N',";
			   sql += "'{}','Y','"+createdOn+"',SYSDATE,SYSDATE)";
			   
	    return sql;
	}
}

