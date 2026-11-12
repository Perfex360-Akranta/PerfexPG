package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.SheTlIncidentEmployee;
import com.akranta.tpm.model.SheTlIncidentEmployeenew;
import com.akranta.tpm.model.SheTlIncidentmstnew;
import com.akranta.tpm.utils.CommonFunctions;

public class SheTlIncidentEmployeenewSql {

	public static final String TBL_SHE_TL_INCIDENT_EMPLOYEENEW = "SHE_TL_INCIDENT_EMPLOYEENEW";  

	TableFieldType [] sineDbFields = null;

	public enum   tableFldConstants
	{
		keyid, sinc_keyid, empm_keyid, injury_modeid, bodypart, remarks
		, injury_typeid, mode, type, location, tempfield5
	}

	public TableFieldType[] getSineDbFields() {
		return sineDbFields;
	}

	public SheTlIncidentEmployeenewSql()
	{
		sineDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			sineDbFields[ i ] = new TableFieldType();
		}
		sineDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SINE_KEYID";
		sineDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		sineDbFields[ tableFldConstants.sinc_keyid.ordinal() ].fieldName = "SINE_SINC_KEYID"; 

		sineDbFields[ tableFldConstants.sinc_keyid.ordinal() ].fieldType = 'V';

		sineDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "SINE_EMPM_KEYID";
		sineDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		sineDbFields[ tableFldConstants.injury_modeid.ordinal() ].fieldName = "SINE_INJURY_MODEID";
		sineDbFields[ tableFldConstants.injury_modeid.ordinal() ].fieldType = 'V';

		sineDbFields[ tableFldConstants.bodypart.ordinal() ].fieldName = "SINE_BODYPART";
		sineDbFields[ tableFldConstants.bodypart.ordinal() ].fieldType = 'V';

		sineDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "SINE_REMARKS";
		sineDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		sineDbFields[ tableFldConstants.injury_typeid.ordinal() ].fieldName = "SINE_INJURY_TYPEID";
		sineDbFields[ tableFldConstants.injury_typeid.ordinal() ].fieldType = 'V';

		sineDbFields[ tableFldConstants.mode.ordinal() ].fieldName = "SINE_MODE";
		sineDbFields[ tableFldConstants.mode.ordinal() ].fieldType = 'V';

		sineDbFields[ tableFldConstants.type.ordinal() ].fieldName = "SINE_TYPE";
		sineDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		sineDbFields[ tableFldConstants.location.ordinal() ].fieldName = "SINE_LOCATION";
		sineDbFields[ tableFldConstants.location.ordinal() ].fieldType = 'V';

		sineDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SINE_TEMPFIELD5";
		sineDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_INCIDENT_EMPLOYEENEW, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_SHE_TL_INCIDENT_EMPLOYEENEW, fieldTypeArr, dataArray));
		
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ) ; 
		sql.append(" = '"  ) ; 
		sql.append( (String)dataArray[ tableFldConstants.keyid.ordinal() ] );
		sql.append("'");
		return sql.toString();
	}

	/*public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_SHE_TL_INCIDENT_EMPLOYEENEW );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}*/
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_INCIDENT_EMPLOYEENEW ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	//Query Change By Swetha = 26 Dec
	public static String getIncidentEmployeesql(SheTlIncidentEmployeenew sheTlIncidentEmployee)
	{

//		String sql = " SELECT DISTINCT SINE_KEYID,EMPM_KEYID,EMPM_CODE,EMPM_NAME,INJM_NAME,IBOP_NAME";
//		sql += " FROM " + TBL_SHE_TL_INCIDENT_EMPLOYEENEW + "," + TableNames.TBL_GEN_TL_EMPLOYEEMST + "," + TableNames.TBL_SHE_TL_INJURYMODEMST  ;
//		sql += ","+ TableNames.TBL_SHE_TL_INCIDENTMST+","+TableNames.TBL_SHE_TL_INCIDENTBODYPARTMST;
//		sql += " WHERE SINE_EMPM_KEYID=EMPM_KEYID and SINE_INJURY_MODEID=INJM_KEYID AND SINE_BODYPART = IBOP_KEYID(+)";
//		if(CommonFunctions.isValidKeyId(sheTlIncidentEmployee.getSineSincKeyid()))
//			sql += " AND SINE_SINC_KEYID = '"+sheTlIncidentEmployee.getSineSincKeyid()+"'";
//		if(CommonFunctions.isValidKeyId(sheTlIncidentEmployee.getSineEmpmKeyid()))
//			sql += " AND SINE_EMPM_KEYID = '"+sheTlIncidentEmployee.getSineEmpmKeyid()+"'";
//		if(CommonFunctions.isValidKeyId(sheTlIncidentEmployee.getSineInjuryModeid()))
//			sql += " AND SINE_INJURY_MODEID = '"+sheTlIncidentEmployee.getSineInjuryModeid()+"'";
//		
//		CommonFunctions.debugMsg("sql22222"+sql);
		
		String sql = " SELECT DISTINCT SINE_KEYID,EMPM_KEYID,EMPM_CODE,EMPM_NAME,INJM_NAME,IBOP_NAME";
		sql += " FROM " + TBL_SHE_TL_INCIDENT_EMPLOYEENEW + "," + TableNames.TBL_GEN_TL_EMPLOYEEMST + "," + TableNames.TBL_SHE_TL_INJURYMODEMST;
		sql += ","+ TableNames.TBL_SHE_TL_INCIDENTMSTNEW+","+TableNames.TBL_SHE_TL_INCIDENTBODYPARTMST;
		sql += " WHERE SINE_EMPM_KEYID=EMPM_KEYID AND SINE_INJURY_MODEID=INJM_KEYID AND SINE_SINC_KEYID=SINM_KEYID";
		sql += " AND SINE_BODYPART = IBOP_KEYID"; // Removed (+) outer join syntax

		if(CommonFunctions.isValidKeyId(sheTlIncidentEmployee.getSineSincKeyid()))
		    sql += " AND SINE_SINC_KEYID = '"+sheTlIncidentEmployee.getSineSincKeyid()+"'";
		if(CommonFunctions.isValidKeyId(sheTlIncidentEmployee.getSineEmpmKeyid()))
		    sql += " AND SINE_EMPM_KEYID = '"+sheTlIncidentEmployee.getSineEmpmKeyid()+"'";
		if(CommonFunctions.isValidKeyId(sheTlIncidentEmployee.getSineInjuryModeid()))
		    sql += " AND SINE_INJURY_MODEID = '"+sheTlIncidentEmployee.getSineInjuryModeid()+"'";

		CommonFunctions.debugMsg("sql22222"+sql);
		return sql;
	}
	public static String getInjuryCountSql(String incId) {
		return "select COUNT(*) from SHE_TL_INCIDENT_EMPLOYEENEW where SINE_SINC_KEYID = '"+incId+"'";
	}
	public static String updateNoofEmpSql(String empCnt,String incId) {
		return "UPDATE SHE_TL_INCIDENTMSTNEW  set SINM_NOOFEMPLOYEES="+empCnt+" where SINM_KEYID = '"+incId+"'";
	}
	public static String getIncidentEmployee() {
		return "select * from SHE_TL_INCIDENT_EMPLOYEENEW where SINE_KEYID = ? ";
	}
	
	public static String getDeleteEmployee(String keyid) {
		return "Delete from SHE_TL_INCIDENT_EMPLOYEENEW where SINE_SINC_KEYID ='"+keyid+"'";
	}

	public static StringBuffer getAssignResponse(String keyid, String responsevalue, String targetdate,SheTlIncidentmstnew sheTlIncidentmstnew) {
		StringBuffer sql = new StringBuffer(); 
		sql.append(" UPDATE SHE_TL_INCIDENTMSTNEW SET SINM_RESPONSIBILITY='"+responsevalue+"',SINM_TARGETDATE='"+targetdate+"' WHERE SINM_KEYID='"+ keyid+"'");
		System.out.println("sql in Response :"+sql.toString());
		return sql;
	}

	public static StringBuffer getAssignActionClosure(String keyid, String responsevalue, String targetdate,
	        String reviseddate, String status, String correctiveaction, SheTlIncidentmstnew sheTlIncidentmstnew) {
		StringBuffer sql = new StringBuffer(); 
		  sql.append(" UPDATE SHE_TL_INCIDENTMSTNEW SET SINM_COMPLETEDBY='"+responsevalue+"',SINM_DATECOMPLETED='"+targetdate+"',SINM_REVISEDDATE='"+reviseddate+"',SINM_STATUS='"+status+"',SINM_ACTIONTAKEN='"+correctiveaction+"' WHERE SINM_KEYID='"+ keyid+"'");
		  System.out.println("sql in Action :"+sql.toString());
		 return sql;
	}

	public static String getCommit() { 
		String sql  = " COMMIT ";
		return sql;
	}

	
	

}

