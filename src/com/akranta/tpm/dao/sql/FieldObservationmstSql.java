package com.akranta.tpm.dao.sql;

import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class FieldObservationmstSql {

	public static final String TBL_UNSF_TL_FIELDOBSRVMST= "UNSF_TL_FIELDOBSRVMST";  
	public static final String ABN_TYPE_VAL = "ABT0000";
	public static final String UNSF_TL_FIELDOBSRVDTL ="UNSF_TL_FIELDOBSRVDTL";

	TableFieldType [] fobmDbFields = null;

	public enum   tableFldConstants
	{		
	keyid, flid, dmtid, jhid, detectiondate, shiftid,
		
		tempfield1,tempfield2,tempfield3,tempfield4,tempfield5,tempfield6,tempfield7
	,active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getfobmDbFields() {
		return fobmDbFields;
	}

	public FieldObservationmstSql()
	{
		fobmDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			fobmDbFields[ i ] = new TableFieldType();
		}
		fobmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FOBM_KEYID";
		fobmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fobmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "FOBM_FLID";
		fobmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		

		fobmDbFields[ tableFldConstants.dmtid.ordinal() ].fieldName = "FOBM_DMTID";
		fobmDbFields[ tableFldConstants.dmtid.ordinal() ].fieldType = 'V';

		fobmDbFields[ tableFldConstants.jhid.ordinal() ].fieldName = "FOBM_JHID";
		fobmDbFields[ tableFldConstants.jhid.ordinal() ].fieldType = 'V';

		fobmDbFields[ tableFldConstants.detectiondate.ordinal() ].fieldName = "FOBM_LOGDATE";
		fobmDbFields[ tableFldConstants.detectiondate.ordinal() ].fieldType = 'D';

		fobmDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "FOBM_SHIFTID";
		fobmDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';
		
	/*	fobmDbFields[ tableFldConstants.image.ordinal() ].fieldName = "FOBM_PHOTO";
		fobmDbFields[ tableFldConstants.image.ordinal() ].fieldType = 'V';*/
		
		fobmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FOBM_TEMPFIELD1";
		fobmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';
		
		fobmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FOBM_TEMPFIELD2";
		fobmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';
		
		fobmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FOBM_TEMPFIELD3";
		fobmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';
		
		fobmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FOBM_TEMPFIELD4";
		fobmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';
		
		fobmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FOBM_TEMPFIELD5";
		fobmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';
		
		fobmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "FOBM_TEMPFIELD6";
		fobmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';
		
		fobmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "FOBM_TEMPFIELD7";
		fobmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'C';
	
		
		fobmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FOBM_ACTIVE";
		fobmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fobmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FOBM_CREATEDBY";
		fobmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fobmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FOBM_CREATEDON";
		fobmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fobmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FOBM_MODIFIEDON";
		fobmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';


	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//CommonMessage.debugMsg(SqlUtils.getInsertSql(TBL_UNSF_TL_FIELDOBSRVMST, fieldTypeArr, dataArray));
		CommonMessage.debugMsg("to be cont...");
		return SqlUtils.getInsertSql(TBL_UNSF_TL_FIELDOBSRVMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_UNSF_TL_FIELDOBSRVMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_UNSF_TL_FIELDOBSRVMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getselectsql() {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from " + TBL_UNSF_TL_FIELDOBSRVMST + " where FOBM_keyid= ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}
	
	public static String getabnSubtypeSql()
	{
		String sql="Select AHSM_KEYID id ,AHSM_CODE||'-'||AHSM_NAME text from " + TableNames.TBL_ABN_TL_HTASOCMST + " where AHSM_ABNORMALITYTYPE =? ";
		return sql;
		
	}
	public static String inactiveAbnSql(String keyId) {
		// TODO Auto-generated method stub
		return " UPDATE " + TBL_UNSF_TL_FIELDOBSRVMST + " SET ABNM_ACTIVE = 'N' where ABNM_KEYID = '"+keyId+"'";
	}
	public static String chkTypeExistSql() {
		// TODO Auto-generated method stub
		
		return "select count(*) from "+TableNames.TBL_ABN_TL_TYPEMST +" where abtm_keyid= '" + ABN_TYPE_VAL + "'";
	}

	public static String insertABNTypeSql() {
		
		StringBuffer sb = new StringBuffer();
		String dateTime = CommonFunctions.dateTimeNow();
		dateTime = dateTime.split(" ").length>0?dateTime.split(" ")[0]:dateTime;
		sb.append("INSERT INTO "+TableNames.TBL_ABN_TL_TYPEMST+" VALUES('"+ABN_TYPE_VAL+"',");
		sb.append("'-','-','X','{}','{}','Y','{}','"+dateTime+"','"+dateTime+"')");
		CommonMessage.debugMsg(sb.toString());
		return sb.toString();
		
	}

	public static String getHseModifyQuery() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT ABNM_KEYID AS ABNM_DOCNO,ABND_KEYID,  ");
		sb.append(" UPPER(TO_CHAR(ABNM_DETECTIONDATE,'DD-MON-YYYY')) AS DETECTIONDATE, ");
		sb.append(" A.EMPM_NAME AS ABNM_DETECTEDBY,SECT_NAME||' - ['||SECT_CODE||']' AS SECT, ");
		sb.append(" CELL_NAME||' - ['||CELL_CODE||']' AS CELL,MCHM_MACHINENAME||' - ['||MCHM_MACHINENO||']' AS MACHINE, ");
		sb.append(" ASSM_NAME AS STATION,ABNM_BLOCKDIAGRAMREF, ABNM_DESCRIPTION AS DESCRIPTION,AHSM_NAME AS SUBTYPES ,ABNM_WHYABNHAPPENED,ABNM_WHATCAUSE,");
		sb.append(" TAGM_NAME AS ABNM_TAGCLASS, ABCM_NAME,ABIM_NAME,ABNM_COUNTERMEASURE,ABNM_TARGETREMARKS,B.EMPM_NAME AS RESPONSIBLE ,");
		sb.append(" ABND_IMMEDIATEACTION,ABND_EFFECTLEADSTO,ABND_AVOIDRECURRENCE,UPPER(TO_CHAR(ABNM_TARGETDATE,'DD-MON-YYYY')) AS TARGETDATE, ");
		sb.append(" DECODE(ABNM_STATUS,'P','PENDING','W','WORK ORDER','C','COMPLETED','D','CANCELLED') AS STATUS");
		sb.append("  FROM");
		sb.append(" UNSF_TL_QCLOGMST,ABN_TL_DTL, GEN_TL_EMPLOYEEMST A,GEN_TL_MACHINEMST, GEN_TL_SECTIONMST, GEN_TL_CELLMST, GEN_TL_ASSEMBLYMST,GEN_TL_EMPLOYEEMST B,");
		sb.append(" ABN_TL_CATEGORYMST,ABN_TL_IMPACTMST,ABN_TL_HTASOCMST,ABN_TL_TAGMST ");
		sb.append(" WHERE ABNM_DETECTEDBY = A.EMPM_KEYID(+)  AND ABNM_KEYID = ABND_ABNORMALITYID  AND TAGM_KEYID = ABNM_TAGCLASSID");
		sb.append(" AND ABNM_EQUIPMENTID = MCHM_KEYID(+)  AND ABNM_SECTIONID = SECT_KEYID(+) AND ABNM_CELLID = CELL_KEYID(+)");
		sb.append("  AND ABNM_ASSEMBLYID = ASSM_KEYID(+)  AND ABNM_CATEGORYID = ABCM_KEYID(+)");
		sb.append(" AND ABNM_IMPACTID = ABIM_KEYID(+) AND ABNM_SUBTYPE = AHSM_KEYID(+) AND ABND_RESPONSIBLITY =B.EMPM_KEYID(+)");
		sb.append("  AND ABNM_PILLAR='SHE'");
	
	return sb.toString();
		
	}
/*	
	public static String getHseSelectQuery() {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from "  + TBL_ABN_TL_DTL + " where ABND_KEYID = ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}
	public static String getHseSelectQueryMST() {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from "  + TBL_ABN_TL_DTL + " where ABND_ABNORMALITYID = ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}
	public static String getCountHseSelectQuery(String keyId) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT count(*) from "  + TBL_ABN_TL_DTL + " where ABND_KEYID = '"+keyId+"'";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}
	public static String getCountHseSelectQueryMST(String keyId) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT count(*) from "  + TBL_ABN_TL_DTL + " where ABND_ABNORMALITYID = '"+keyId+"'";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}*/
	public static String checkTagSql() {
		String sql = "SELECT cnfm_settingvalue FROM "+TableNames.TBL_ADM_TL_CONFIGURATIONMST;
		sql += " Where Cnfm_Code = 'ABNTAGFROMMSR'";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}
	public static String checkAbnQuery() {
		String sql = "Select BITAND(CNFM_SETTINGVALUE,8) FROM "+TableNames.TBL_ADM_TL_CONFIGURATIONMST;
		sql += " Where Cnfm_Code='MSRACTIVITY'";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}
	public static String showAbnQuery() {
		String sql = "Select CNFM_SETTINGVALUE FROM "+TableNames.TBL_ADM_TL_CONFIGURATIONMST;
		sql += " Where Cnfm_Code='ABNSHOWCOMPDATE'";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}
	public static String inactiveWOSql(String keyId)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+ TableNames.TBL_WOM_TL_MST+" SET WOMS_ACTIVE = 'N'");
		sb.append(" WHERE woms_keyid = '"+keyId+"'");
		
		return sb.toString();
	}

	public static String updateBulkTagRemoval(AbnTlAbnormality abnTlAbnormality) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE "+ TBL_UNSF_TL_FIELDOBSRVMST +" SET ABNM_KEYID = '"+abnTlAbnormality.getAbnmKeyid()+"'");
		sql.append(" ,ABNM_TRADEID = '"+abnTlAbnormality.getAbnmTradeid()+"', " );
		sql.append(" ABNM_WHYABNHAPPENED = '"+abnTlAbnormality.getAbnmWhyabnhappened()+"'");
		sql.append(" ,ABNM_WHATCAUSE = '"+abnTlAbnormality.getAbnmWhatcause()+"', ");
		sql.append(" ABNM_COUNTERMEASURE = '"+abnTlAbnormality.getAbnmCountermeasure()+"',");
		sql.append(" ABNM_STATUS = 'C',ABNM_WOENDTIME=TO_DATE( '"+abnTlAbnormality.getAbnmWoendtime()+"','dd-Mon-yyyy hh24:mi:ss')");
		sql.append(" ,ABNM_REMARKS = '"+abnTlAbnormality.getAbnmTargetremarks()+"'," );
		sql.append(" ABNM_COMPLETEDBY = '"+abnTlAbnormality.getAbnmCompletedby()+"' WHERE ABNM_KEYID = '"+abnTlAbnormality.getAbnmKeyid()+"'");
		return sql.toString();
	}

	public static String updateWorkOrderMst(AbnTlAbnormality abnTlAbnormality) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE "+TableNames.TBL_WOM_TL_MST+" SET WOMS_ACTIVE = 'N' WHERE woms_keyid = '"+abnTlAbnormality.getAbnmRefdocid()+"'");
		return sql.toString();
	}

	public static String getLocnFLID(String flid) {

		return " SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID =(SELECT LOCN_KEYID FROM GEN_VW_FNLN WHERE FNLN_KEYID = '"+flid+"') ";
	}

	public static String updateAbnAllocation(AbnTlAbnormality abnTlAbnormality) {
		StringBuffer sql = new StringBuffer();
		String effectDate = "";
		sql.append(" UPDATE "+ TBL_UNSF_TL_FIELDOBSRVMST +" SET ABNM_RESPONSIBLEID='"+abnTlAbnormality.getAbnmResponsibleid()+"' , ABNM_KEYID = '"+abnTlAbnormality.getAbnmKeyid()+"'");
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmTradeid()))
			sql.append(" ,ABNM_TRADEID = '"+abnTlAbnormality.getAbnmTradeid()+"' " );
		
		CommonMessage.debugMsg("....abnTlAbnormality.getAbnmEffectivedate()..."+abnTlAbnormality.getAbnmEffectivedate());
		
		if(!UIUtils.isValidKeyId(abnTlAbnormality.getAbnmEffectivedate()))
			effectDate = Constants.passNullDate;		
		else
			effectDate = abnTlAbnormality.getAbnmEffectivedate();
		
		sql.append(" ,ABNM_EFFECTIVEDATE = '"+effectDate+"'");
		sql.append(" WHERE ABNM_KEYID = '"+abnTlAbnormality.getAbnmKeyid()+"'");
		return sql.toString();
	}

	/*public static String updateAbnAllocationdtl(AbnTlAbnormality abnTlAbnormality) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE "+ TBL_ABN_TL_DTL);
		if(abnTlAbnormality.getAbnTlDtl().getAbndResponsiblity().trim().length()>0)
		//if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnTlDtl().getAbndResponsiblity()))
			sql.append(" SET ABND_RESPONSIBLITY = '"+abnTlAbnormality.getAbnTlDtl().getAbndResponsiblity()+"' " );
		sql.append(" WHERE ABND_ABNORMALITYID = '"+abnTlAbnormality.getAbnmKeyid()+"'");
		return sql.toString();
	}*/

	public String getActionPlanCount(AbnTlAbnormality abnTlAbnormality) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT COUNT(*) FROM GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL WHERE APLM_KEYID=APLD_APLM_KEYID AND APLM_DETAILREFID = '"+abnTlAbnormality.getAbnmKeyid()+"' AND APLM_STATUS = 'P' ");
		CommonMessage.debugMsg("action plan count....."+sql.toString());
		return sql.toString();
	}

	public static String selectabnpopData(String keyid) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" select abnm_remarks,abnm_status,ABNM_COMPLETEDBY,ABNM_COUNTERMEASURE,TO_CHAR (ABNM_WOENDTIME,'dd-Mon-yyyy') from UNSF_TL_QCLOGMST ");
		sql.append(" where abnm_keyid='"+keyid+"' ");
		
		return sql.toString();
	}

	public static String selectAbnActnplnstats(String keyid) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" select aplm_status from GEN_TL_ACTIONPLANMST ");
		sql.append(" where aplm_detailrefid='"+keyid+"' ");
		
		return sql.toString();
	}
	public String getSubType(String typeId)throws Exception{
       String sql="";
       sql="SELECT DISTINCT AHSM_KEYID id, AHSM_NAME || '-' || AHSM_CODE text FROM ABN_TL_HTASOCMST WHERE AHSM_ABNORMALITYTYPE='"+typeId+"' ";
       CommonMessage.debugMsg("SQL"+sql);
	   return sql;
	}

	public static String getMasterData(String keyId) {
		
		return "SELECT * FROM UNSF_TL_FIELDOBSRVMST WHERE FOBM_KEYID=? ";
	}
	
}

