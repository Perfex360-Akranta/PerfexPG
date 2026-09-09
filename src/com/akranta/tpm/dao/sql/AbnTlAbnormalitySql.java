package com.akranta.tpm.dao.sql;

import java.util.List;
import java.util.stream.Collectors;

import com.akranta.tpm.bean.ActionPlanParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class AbnTlAbnormalitySql {

	public static final String TBL_ABN_TL_ABNORMALITY = "ABN_TL_ABNORMALITY";  
	public static final String ABN_TYPE_VAL = "ABT0000";
	public static final String TBL_ABN_TL_DTL ="ABN_TL_DTL";

	TableFieldType [] abnmDbFields = null;

	public enum   tableFldConstants
	{		
		keyid, date, refdoctype, refdocid, detectiondate, detectedby
		, equipmentid, sectionid, cellid, assemblyid, shiftid, tradeid
		, woreceiveddate, responsetime, worktime, wostarttime, woendtime
		, downtime, description, typeid, whyabnhappened, whatcause, tagclassid
		, categoryid, impactid, countermeasure, preventivemeasure, status
		, targetdate, targetremarks, completedby, womasterid, wodetailid
		, feedbackid, feedbackdate, remarks, blockdiagramref, revisionno
		, priority, detailedesc, subtype, contaminant, mode, factoryid
		, pillar, safetypatrol, relatedto, mould, flid, elementid, pillarid
		, repeatedabn, afeemid, effectivedate, notifysap, shutdownmaint
		, tentativedate, shutdownid, accecpatncerequired,accecptdate,accecpted,others
		,repotheres,responsibleid,multipleabn,tempfield4,tempfield5,tempfield6,tempfield7
		,tempfield8,tempfield9,tempfield10,
		active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getAbnmDbFields() {
		return abnmDbFields;
	}

	public AbnTlAbnormalitySql()
	{
		abnmDbFields = new TableFieldType[ 76 ];
		for(int i = 0;i < 76; i++)
		{	
			abnmDbFields[ i ] = new TableFieldType();
		}
		abnmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ABNM_KEYID";
		abnmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "ABNM_DATE";
		abnmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		abnmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "ABNM_REFDOCTYPE";
		abnmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "ABNM_REFDOCID";
		abnmDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.detectiondate.ordinal() ].fieldName = "ABNM_DETECTIONDATE";
		abnmDbFields[ tableFldConstants.detectiondate.ordinal() ].fieldType = 'D';

		abnmDbFields[ tableFldConstants.detectedby.ordinal() ].fieldName = "ABNM_DETECTEDBY";
		abnmDbFields[ tableFldConstants.detectedby.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.equipmentid.ordinal() ].fieldName = "ABNM_EQUIPMENTID";
		abnmDbFields[ tableFldConstants.equipmentid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "ABNM_SECTIONID";
		abnmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "ABNM_CELLID";
		abnmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "ABNM_ASSEMBLYID";
		abnmDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "ABNM_SHIFTID";
		abnmDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "ABNM_TRADEID";
		abnmDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.woreceiveddate.ordinal() ].fieldName = "ABNM_WORECEIVEDDATE";
		abnmDbFields[ tableFldConstants.woreceiveddate.ordinal() ].fieldType = 'D';

		abnmDbFields[ tableFldConstants.responsetime.ordinal() ].fieldName = "ABNM_RESPONSETIME";
		abnmDbFields[ tableFldConstants.responsetime.ordinal() ].fieldType = 'N';

		abnmDbFields[ tableFldConstants.worktime.ordinal() ].fieldName = "ABNM_WORKTIME";
		abnmDbFields[ tableFldConstants.worktime.ordinal() ].fieldType = 'N';

		abnmDbFields[ tableFldConstants.wostarttime.ordinal() ].fieldName = "ABNM_WOSTARTTIME";
		abnmDbFields[ tableFldConstants.wostarttime.ordinal() ].fieldType = 'D';

		abnmDbFields[ tableFldConstants.woendtime.ordinal() ].fieldName = "ABNM_WOENDTIME";
		abnmDbFields[ tableFldConstants.woendtime.ordinal() ].fieldType = 'D';

		abnmDbFields[ tableFldConstants.downtime.ordinal() ].fieldName = "ABNM_DOWNTIME";
		abnmDbFields[ tableFldConstants.downtime.ordinal() ].fieldType = 'N';

		abnmDbFields[ tableFldConstants.description.ordinal() ].fieldName = "ABNM_DESCRIPTION";
		abnmDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.typeid.ordinal() ].fieldName = "ABNM_TYPEID";
		abnmDbFields[ tableFldConstants.typeid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.whyabnhappened.ordinal() ].fieldName = "ABNM_WHYABNHAPPENED";
		abnmDbFields[ tableFldConstants.whyabnhappened.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.whatcause.ordinal() ].fieldName = "ABNM_WHATCAUSE";
		abnmDbFields[ tableFldConstants.whatcause.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.tagclassid.ordinal() ].fieldName = "ABNM_TAGCLASSID";
		abnmDbFields[ tableFldConstants.tagclassid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.categoryid.ordinal() ].fieldName = "ABNM_CATEGORYID";
		abnmDbFields[ tableFldConstants.categoryid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.impactid.ordinal() ].fieldName = "ABNM_IMPACTID";
		abnmDbFields[ tableFldConstants.impactid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "ABNM_COUNTERMEASURE";
		abnmDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.preventivemeasure.ordinal() ].fieldName = "ABNM_PREVENTIVEMEASURE";
		abnmDbFields[ tableFldConstants.preventivemeasure.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.status.ordinal() ].fieldName = "ABNM_STATUS";
		abnmDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		abnmDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "ABNM_TARGETDATE";
		abnmDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		abnmDbFields[ tableFldConstants.targetremarks.ordinal() ].fieldName = "ABNM_TARGETREMARKS";
		abnmDbFields[ tableFldConstants.targetremarks.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "ABNM_COMPLETEDBY";
		abnmDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.womasterid.ordinal() ].fieldName = "ABNM_WOMASTERID";
		abnmDbFields[ tableFldConstants.womasterid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.wodetailid.ordinal() ].fieldName = "ABNM_WODETAILID";
		abnmDbFields[ tableFldConstants.wodetailid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.feedbackid.ordinal() ].fieldName = "ABNM_FEEDBACKID";
		abnmDbFields[ tableFldConstants.feedbackid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.feedbackdate.ordinal() ].fieldName = "ABNM_FEEDBACKDATE";
		abnmDbFields[ tableFldConstants.feedbackdate.ordinal() ].fieldType = 'D';

		abnmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "ABNM_REMARKS";
		abnmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.blockdiagramref.ordinal() ].fieldName = "ABNM_BLOCKDIAGRAMREF";
		abnmDbFields[ tableFldConstants.blockdiagramref.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.revisionno.ordinal() ].fieldName = "ABNM_REVISIONNO";
		abnmDbFields[ tableFldConstants.revisionno.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.priority.ordinal() ].fieldName = "ABNM_PRIORITY";
		abnmDbFields[ tableFldConstants.priority.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.detailedesc.ordinal() ].fieldName = "ABNM_DETAILEDESC";
		abnmDbFields[ tableFldConstants.detailedesc.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.subtype.ordinal() ].fieldName = "ABNM_SUBTYPE";
		abnmDbFields[ tableFldConstants.subtype.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.contaminant.ordinal() ].fieldName = "ABNM_CONTAMINANT";
		abnmDbFields[ tableFldConstants.contaminant.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.mode.ordinal() ].fieldName = "ABNM_MODE";
		abnmDbFields[ tableFldConstants.mode.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "ABNM_FACTORYID";
		abnmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.pillar.ordinal() ].fieldName = "ABNM_PILLAR";
		abnmDbFields[ tableFldConstants.pillar.ordinal() ].fieldType = 'C';

		abnmDbFields[ tableFldConstants.safetypatrol.ordinal() ].fieldName = "ABNM_SAFETYPATROL";
		abnmDbFields[ tableFldConstants.safetypatrol.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "ABNM_RELATEDTO";
		abnmDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.mould.ordinal() ].fieldName = "ABNM_MOULD";
		abnmDbFields[ tableFldConstants.mould.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "ABNM_FLID";
		abnmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "ABNM_ELEMENTID";
		abnmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.pillarid.ordinal() ].fieldName = "ABNM_PILLARID";
		abnmDbFields[ tableFldConstants.pillarid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.repeatedabn.ordinal() ].fieldName = "ABNM_REPEATEDABN";
		abnmDbFields[ tableFldConstants.repeatedabn.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.afeemid.ordinal() ].fieldName = "ABNM_AFEEMID";
		abnmDbFields[ tableFldConstants.afeemid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldName = "ABNM_EFFECTIVEDATE";
		abnmDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldType = 'D';

		abnmDbFields[ tableFldConstants.notifysap.ordinal() ].fieldName = "ABNM_NOTIFYSAP";
		abnmDbFields[ tableFldConstants.notifysap.ordinal() ].fieldType = 'C';

		abnmDbFields[ tableFldConstants.shutdownmaint.ordinal() ].fieldName = "ABNM_SHUTDOWNMAINT";
		abnmDbFields[ tableFldConstants.shutdownmaint.ordinal() ].fieldType = 'C';

		abnmDbFields[ tableFldConstants.tentativedate.ordinal() ].fieldName = "ABNM_TENTATIVEDATE";
		abnmDbFields[ tableFldConstants.tentativedate.ordinal() ].fieldType = 'D';

		abnmDbFields[ tableFldConstants.shutdownid.ordinal() ].fieldName = "ABNM_SHUTDOWNID";
		abnmDbFields[ tableFldConstants.shutdownid.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.accecpatncerequired.ordinal() ].fieldName = "ABNM_ACCECPTREQUIRED";
		abnmDbFields[ tableFldConstants.accecpatncerequired.ordinal() ].fieldType = 'C';

		abnmDbFields[ tableFldConstants.accecptdate.ordinal() ].fieldName = "ABNM_ACCECPTDATE";
		abnmDbFields[ tableFldConstants.accecptdate.ordinal() ].fieldType = 'D';

		abnmDbFields[ tableFldConstants.accecpted.ordinal() ].fieldName = "ABNM_ACCECPTED";
		abnmDbFields[ tableFldConstants.accecpted.ordinal() ].fieldType = 'C';

		abnmDbFields[ tableFldConstants.others.ordinal() ].fieldName = "ABNM_OTHERS";
		abnmDbFields[ tableFldConstants.others.ordinal() ].fieldType = 'C';
		
		abnmDbFields[ tableFldConstants.repotheres.ordinal() ].fieldName = "ABNM_REPOTHERES";
		abnmDbFields[ tableFldConstants.repotheres.ordinal() ].fieldType = 'C';
		
		abnmDbFields[ tableFldConstants.responsibleid.ordinal() ].fieldName = "ABNM_RESPONSIBLEID";
		abnmDbFields[ tableFldConstants.responsibleid.ordinal() ].fieldType = 'V';
		
		abnmDbFields[ tableFldConstants.multipleabn.ordinal() ].fieldName = "ABNM_MULTIPLEABN";
		abnmDbFields[ tableFldConstants.multipleabn.ordinal() ].fieldType = 'C';
		
		abnmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ABNM_TEMPFIELD4";
		abnmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';
		
		abnmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ABNM_TEMPFIELD5";
		abnmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';
		
		abnmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "ABNM_TEMPFIELD6";
		abnmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';
		
		abnmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "ABNM_TEMPFIELD7";
		abnmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'C';
		
		abnmDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "ABNM_TEMPFIELD8";
		abnmDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'C';
		
		abnmDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "ABNM_TEMPFIELD9";
		abnmDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'C';
		
		abnmDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "ABNM_TEMPFIELD10";
		abnmDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'C';
		
		abnmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ABNM_ACTIVE";
		abnmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		abnmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ABNM_CREATEDBY";
		abnmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		abnmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ABNM_CREATEDON";
		abnmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		abnmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ABNM_MODIFIEDON";
		abnmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';


	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//CommonMessage.debugMsg(SqlUtils.getInsertSql(TBL_ABN_TL_ABNORMALITY, fieldTypeArr, dataArray));
		CommonMessage.debugMsg("to be cont...");
		return SqlUtils.getInsertSql(TBL_ABN_TL_ABNORMALITY, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ABN_TL_ABNORMALITY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ABN_TL_ABNORMALITY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getabnfrmdatasql() {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from " + TBL_ABN_TL_ABNORMALITY + " where abnm_keyid= ?";
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
		return " UPDATE " + TBL_ABN_TL_ABNORMALITY + " SET ABNM_ACTIVE = 'N' where ABNM_KEYID = '"+keyId+"'";
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
	
	public static String getMultipleAbnormality(List<String> keyids) {
		StringBuilder sql = new StringBuilder();

				sql.append(" SELECT ABNM_KEYID AS hdnAbnmdKeyid, "
						+ "TO_CHAR(ABNM_DATE, 'DD-Mon-YYYY') AS dteAbnmDetectiondate, "
						+ "CASE WHEN ABNM_DETECTEDBY = A.EMPM_KEYID THEN A.EMPM_NAME || '-' || A.EMPM_CODE ELSE '-' END AS cmbAbnmDetectedby, "
						+ "ABNM_DETECTEDBY AS Detectedby, "
						+ "ABNM_DESCRIPTION AS txtAbnmDescription, "
						+ "ABNM_EQUIPMENTID AS cmbAbnmEquipmentid, "
						+ "ABNM_EQUIPMENTID AS Equipment, "
						+ "ABTM_NAME AS cmbAbnmTypeid, "
						+ "ABNM_TYPEID AS AbnormalityType, "
						+ "ABNM_SUBTYPE AS cmbAbnmSubtype, "
						+ "ABNM_SUBTYPE AS SubType, "
						+ "ABCM_NAME AS cmbAbnmCategoryid, "
						+ "ABNM_CATEGORYID AS AbnormalityCategory, "
						+ "TAGM_NAME AS cmbAbnmTagclassid, "
						+ "ABNM_TAGCLASSID AS TagClass, "
						+ "ABIM_NAME AS cmbAbnmImpactid, "
						+ "ABNM_IMPACTID AS AbnormalityImpact, "
						+ "TRDM_NAME AS cmbAbnmTradeid, "
						+ "ABNM_TRADEID AS MaintainanceSection, "
						+ "CASE WHEN ABNM_RESPONSIBLEID = B.EMPM_KEYID THEN B.EMPM_NAME || '-' || B.EMPM_CODE ELSE '-' END AS cmbAbnmResponsibleid, "
						+ "ABNM_RESPONSIBLEID AS Responsibiltyby, "
						+ "CASE ABNM_STATUS WHEN 'P' THEN 'Pending' WHEN 'C' THEN 'Completed' ELSE ABNM_STATUS END AS cmbAbnmStatus, "
//						+ "ABNM_STATUS AS StatusDetail, "
						+ "TO_CHAR(ABNM_TARGETDATE, 'DD-MON-YYYY') AS dteAbnmTargetdate, "
						+ "ABNM_COUNTERMEASURE AS txtAbnmCountermeasure, "
						+ "CASE WHEN ABNM_COMPLETEDBY = C.EMPM_KEYID THEN C.EMPM_NAME || '-' || C.EMPM_CODE ELSE '-' END AS cmbAbnmCompletedby, "
						+ "ABNM_COMPLETEDBY AS completedby, "
						+ "TO_CHAR(ABNM_WOENDTIME, 'DD-MON-YYYY') AS dteAbnmWoendtime, "
						+ "'' AS ISM, "
						+ "ABNM_REFDOCID AS RefDocID, "
						+ "ABNM_REMARKS AS txtAbnmRemarks, '' AS btnActionPlan, '' AS btnFilManage"
						+ " FROM ABN_TL_ABNORMALITY "
						+ "LEFT JOIN GEN_TL_EMPLOYEEMST A ON ABNM_DETECTEDBY = A.EMPM_KEYID "
						+ "LEFT JOIN GEN_TL_EMPLOYEEMST B ON ABNM_RESPONSIBLEID = B.EMPM_KEYID "
						+ "LEFT JOIN GEN_TL_EMPLOYEEMST C  ON ABNM_COMPLETEDBY = C.EMPM_KEYID "
						+ "LEFT JOIN ABN_TL_CATEGORYMST  ON ABNM_CATEGORYID = ABCM_KEYID "
						+ "LEFT JOIN ABN_TL_IMPACTMST  ON ABNM_IMPACTID = ABIM_KEYID "
						+ "LEFT JOIN ABN_TL_TYPEMST  ON ABNM_TYPEID = ABTM_KEYID "
						+ "LEFT JOIN ABN_TL_TAGMST  ON ABNM_TAGCLASSID = TAGM_KEYID "
						+ "LEFT JOIN GEN_TL_TRADEMST  ON ABNM_TRADEID = TRDM_KEYID "
						+ "WHERE 1=1 " );

				if (keyids != null && !keyids.isEmpty()) {
				    // Convert List<String> → comma-separated quoted values
				    String joinedIds = keyids.stream()
				        .map(id -> "'" + id + "'")
				        .collect(Collectors.joining(","));
				    
				    sql.append(" AND ABNM_KEYID IN (" + joinedIds + ") ORDER BY ABNM_KEYID ");
				}else {
					sql.append( "AND ABNM_KEYID  = '' ORDER BY ABNM_KEYID ");
				}
				
			
//				CommonMessage.debugMsg("sql2 : "+sql);
				CommonMessage.debugMsg("sql2 : "+sql.toString());
			  
		 CommonMessage.debugMsg("Sql Content::"+sql.toString());
		 return sql.toString();
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
		sb.append(" ABN_TL_ABNORMALITY,ABN_TL_DTL, GEN_TL_EMPLOYEEMST A,GEN_TL_MACHINEMST, GEN_TL_SECTIONMST, GEN_TL_CELLMST, GEN_TL_ASSEMBLYMST,GEN_TL_EMPLOYEEMST B,");
		sb.append(" ABN_TL_CATEGORYMST,ABN_TL_IMPACTMST,ABN_TL_HTASOCMST,ABN_TL_TAGMST ");
		sb.append(" WHERE ABNM_DETECTEDBY = A.EMPM_KEYID(+)  AND ABNM_KEYID = ABND_ABNORMALITYID  AND TAGM_KEYID = ABNM_TAGCLASSID");
		sb.append(" AND ABNM_EQUIPMENTID = MCHM_KEYID(+)  AND ABNM_SECTIONID = SECT_KEYID(+) AND ABNM_CELLID = CELL_KEYID(+)");
		sb.append("  AND ABNM_ASSEMBLYID = ASSM_KEYID(+)  AND ABNM_CATEGORYID = ABCM_KEYID(+)");
		sb.append(" AND ABNM_IMPACTID = ABIM_KEYID(+) AND ABNM_SUBTYPE = AHSM_KEYID(+) AND ABND_RESPONSIBLITY =B.EMPM_KEYID(+)");
		sb.append("  AND ABNM_PILLAR='SHE'");
	
	return sb.toString();
		
	}
	
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
	}
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
		sql.append(" UPDATE "+ TBL_ABN_TL_ABNORMALITY +" SET ABNM_KEYID = '"+abnTlAbnormality.getAbnmKeyid()+"'");
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
		sql.append(" UPDATE "+ TBL_ABN_TL_ABNORMALITY +" SET ABNM_RESPONSIBLEID='"+abnTlAbnormality.getAbnmResponsibleid()+"' , ABNM_KEYID = '"+abnTlAbnormality.getAbnmKeyid()+"'");
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
	
	public static String getUpdatedRowAbn(String keyId) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT\r\n"
				+ "	 ''::text AS selectv,  \r\n"
				+ "        CASE WHEN dmdm_keyid IS NULL OR dmdm_keyid = '' THEN '' ELSE '+' END AS attachment,\r\n"
				+ "        abnm_keyid AS tagno,\r\n"
				+ "        upper(to_char(abnm_detectiondate, 'DD-MON-YYYY HH24:MI')) AS detecteddate,\r\n"
				+ "        abnm_description AS item,\r\n"
				+ "        mchm_machinename AS machinemname,\r\n"
				+ "        a.empm_name AS detectedby,\r\n"
				+ "        c.empm_name AS responsiblityby,\r\n"
				+ "        CASE ABNM_REFDOCID WHEN '{}' THEN '-' ELSE ABNM_REFDOCID END AS mwno,\r\n"
				+ "        tagm_name AS tagclass,\r\n"
				+ "        functionalloc AS funloc,\r\n"
				+ "        assm_name AS assembly,\r\n"
				+ "        TO_CHAR(ABNM_DETECTIONDATE, 'DD-MON-YYYY') AS occureddate,\r\n"
				+ "        abtm_name AS abnormalitytype,\r\n"
				+ "        abnm_whyabnhappened AS  whyabnormality,\r\n"
				+ "        abnm_whatcause AS whatcause,\r\n"
				+ "        abcm_name AS abnormalitycategory,\r\n"
				+ "        abim_name AS abnormalityimpact,\r\n"
				+ "        abnm_countermeasure AS countermeasure,\r\n"
				+ "        abnm_remarks AS remarks,\r\n"
				+ "        to_char(abnm_targetdate, 'DD-MON-YYYY') AS targetdate,\r\n"
				+ "        CASE abnm_status\r\n"
				+ "            WHEN 'P' THEN 'PENDING'\r\n"
				+ "            WHEN 'W' THEN 'WORK ORDER'\r\n"
				+ "            WHEN 'C' THEN 'COMPLETED'\r\n"
				+ "            WHEN 'D' THEN 'CANCELLED'\r\n"
				+ "            ELSE abnm_status\r\n"
				+ "        END AS status,\r\n"
				+ "        REPLACE(TO_CHAR(ABNM_WOSTARTTIME, 'DD-MON-YYYY HH24:MI'), '01-JAN-1801 00:00', '') AS wostart,\r\n"
				+ "        CASE \r\n"
				+ "          WHEN TAGM_ISTHROUGHWO = 'N' THEN \r\n"
				+ "             REPLACE(REPLACE(TO_CHAR(ABNM_WOENDTIME, 'DD-MON-YYYY HH24:MI'),'31-DEC-2100 00:00',''),'01-JAN-1801 00:00', '')\r\n"
				+ "          ELSE \r\n"
				+ "            REPLACE(TO_CHAR(ABNM_WOENDTIME, 'DD-MON-YYYY HH24:MI'),'31-DEC-2100 00:00','') \r\n"
				+ "        END AS woend,\r\n"
				+ "        replace(replace(to_char(abnm_woendtime, 'DD-MON-YYYY'),'31-DEC-2100',''),'01-JAN-1801','') AS completeddate,\r\n"
				+ "        CASE WHEN abnm_status = 'P' THEN '' ELSE b.empm_name END AS workdoneby,\r\n"
				+ "        to_char(\r\n"
				+ "          CASE\r\n"
				+ "            WHEN abnm_status = 'P' THEN\r\n"
				+ "              CASE WHEN current_date - abnm_detectiondate::date < 0 THEN 0 \r\n"
				+ "                   ELSE (current_date - abnm_detectiondate::date) END\r\n"
				+ "            WHEN abnm_status = 'C' THEN\r\n"
				+ "              CASE WHEN abnm_woendtime::date - abnm_detectiondate::date < 0 THEN 0 \r\n"
				+ "                   ELSE (abnm_woendtime::date - abnm_detectiondate::date) END\r\n"
				+ "            ELSE NULL\r\n"
				+ "          END, \r\n"
				+ "        'FM9999999') AS days,\r\n"
				+ "        apld.apld_status,\r\n"
				+ "        abnm_refdoctype,\r\n"
				+ "        replace(abnm_refdocid,'{}','') AS refdoc,\r\n"
				+ "        CASE\r\n"
				+ "          WHEN TAGM_ISTHROUGHWO = 'N' THEN TO_CHAR(ABNM_WORECEIVEDDATE, 'DD-MON-YYYY')\r\n"
				+ "          ELSE TO_CHAR(ABNM_WORECEIVEDDATE, 'DD-MON-YYYY HH24:MI')\r\n"
				+ "        END AS abnm_receiveddate,\r\n"
				+ "        abnm_downtime::text AS downtime,\r\n"
				+ "        c.empm_name AS manpower,\r\n"
				+ "        to_char(abnm_detectiondate, 'YYYYMMDD') AS orderdate\r\n"
				+ "    FROM\r\n"
				+ "        abn_tl_abnormality abn\r\n"
				+ "        LEFT JOIN gen_tl_employeemst a ON abn.abnm_detectedby = a.empm_keyid\r\n"
				+ "        LEFT JOIN gen_vw_fnln fnln ON abn.abnm_flid = fnln.fnln_keyid\r\n"
				+ "        LEFT JOIN abn_tl_typemst abtm ON abn.abnm_typeid = abtm.abtm_keyid\r\n"
				+ "        LEFT JOIN abn_tl_categorymst abcm ON abn.abnm_categoryid = abcm.abcm_keyid\r\n"
				+ "        LEFT JOIN abn_tl_impactmst abim ON abn.abnm_impactid = abim.abim_keyid\r\n"
				+ "        LEFT JOIN gen_tl_employeemst b ON abn.abnm_completedby = b.empm_keyid\r\n"
				+ "        LEFT JOIN gen_tl_employeemst c ON abn.abnm_responsibleid = c.empm_keyid\r\n"
				+ "        LEFT JOIN dcm_tl_documentmanager dmdm ON dmdm.dmdm_refdocno = abn.abnm_keyid\r\n"
				+ "        LEFT JOIN abn_tl_tagmst tagm ON tagm.tagm_keyid = abn.abnm_tagclassid\r\n"
				+ "		LEFT JOIN gen_tl_actionplanmst aplm ON aplm.aplm_detailrefid = abn.abnm_keyid\r\n"
				+ "		LEFT JOIN gen_tl_actionplandtl apld ON aplm.aplm_keyid = apld_aplm_keyid \r\n"
				+ "		LEFT JOIN gen_tl_assemblymst  ON abnm_assemblyid = assm_keyid\r\n"
				+ "    WHERE\r\n"
				+ "        abn.abnm_active = 'Y' AND abn.abnm_keyid = '"+keyId+"'");
//		sql.append(" SELECT COUNT(*) FROM GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL WHERE APLM_KEYID=APLD_APLM_KEYID AND APLM_DETAILREFID = '""' AND APLM_STATUS = 'P' ");
		CommonMessage.debugMsg("UpdatedGridRow....."+sql.toString());
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
		sql.append(" select abnm_remarks,abnm_status,ABNM_COMPLETEDBY,ABNM_COUNTERMEASURE,TO_CHAR (ABNM_WOENDTIME,'dd-Mon-yyyy'),TO_CHAR (abnm_detectiondate,'dd-Mon-yyyy') from ABN_TL_ABNORMALITY ");
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
	
}

