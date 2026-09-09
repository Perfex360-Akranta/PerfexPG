package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.sql.KznTlMstSql.tableFldConstants;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class MpsTlMstSql {

	public static final String TBL_MPS_TL_MST = "MPS_TL_MST";  
	public static final String TBL_MPS_TL_DTL = "MPS_TL_DTL";

	TableFieldType [] mpsmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, createddate, improvementtheme, problem, registrationcode
		, controlno, cellid, machineid, workcentreid, costcentreid, cause
		, beforeimprovement, afterimprovement, iscosteffective, standardization
		, change, others, totalamount, effsavingsreported, responsiblity
		, status, completedby, completeddate, completedremarks, opinion
		, ishdrequired, tempfield1, tempfield2, tempfield3, tempfield4
		, modifiedby, elementid, flid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMpsmDbFields() {
		return mpsmDbFields;
	}

	public MpsTlMstSql()
	{
		mpsmDbFields = new TableFieldType[ 37 ];
		for(int i = 0;i < 37; i++)
		{	
			mpsmDbFields[ i ] = new TableFieldType();
		}
		mpsmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MPSM_KEYID";
		mpsmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.createddate.ordinal() ].fieldName = "MPSM_CREATEDDATE";
		mpsmDbFields[ tableFldConstants.createddate.ordinal() ].fieldType = 'D';

		mpsmDbFields[ tableFldConstants.improvementtheme.ordinal() ].fieldName = "MPSM_IMPROVEMENTTHEME";
		mpsmDbFields[ tableFldConstants.improvementtheme.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.problem.ordinal() ].fieldName = "MPSM_PROBLEM";
		mpsmDbFields[ tableFldConstants.problem.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.registrationcode.ordinal() ].fieldName = "MPSM_REGISTRATIONCODE";
		mpsmDbFields[ tableFldConstants.registrationcode.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.controlno.ordinal() ].fieldName = "MPSM_CONTROLNO";
		mpsmDbFields[ tableFldConstants.controlno.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "MPSM_CELLID";
		mpsmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "MPSM_MACHINEID";
		mpsmDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.workcentreid.ordinal() ].fieldName = "MPSM_WORKCENTREID";
		mpsmDbFields[ tableFldConstants.workcentreid.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.costcentreid.ordinal() ].fieldName = "MPSM_COSTCENTREID";
		mpsmDbFields[ tableFldConstants.costcentreid.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.cause.ordinal() ].fieldName = "MPSM_CAUSE";
		mpsmDbFields[ tableFldConstants.cause.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.beforeimprovement.ordinal() ].fieldName = "MPSM_BEFOREIMPROVEMENT";
		mpsmDbFields[ tableFldConstants.beforeimprovement.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.afterimprovement.ordinal() ].fieldName = "MPSM_AFTERIMPROVEMENT";
		mpsmDbFields[ tableFldConstants.afterimprovement.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.iscosteffective.ordinal() ].fieldName = "MPSM_ISCOSTEFFECTIVE";
		mpsmDbFields[ tableFldConstants.iscosteffective.ordinal() ].fieldType = 'C';

		mpsmDbFields[ tableFldConstants.standardization.ordinal() ].fieldName = "MPSM_STANDARDIZATION";
		mpsmDbFields[ tableFldConstants.standardization.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.change.ordinal() ].fieldName = "MPSM_CHANGE";
		mpsmDbFields[ tableFldConstants.change.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.others.ordinal() ].fieldName = "MPSM_OTHERS";
		mpsmDbFields[ tableFldConstants.others.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.totalamount.ordinal() ].fieldName = "MPSM_TOTALAMOUNT";
		mpsmDbFields[ tableFldConstants.totalamount.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.effsavingsreported.ordinal() ].fieldName = "MPSM_EFFSAVINGSREPORTED";
		mpsmDbFields[ tableFldConstants.effsavingsreported.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.responsiblity.ordinal() ].fieldName = "MPSM_RESPONSIBLITY";
		mpsmDbFields[ tableFldConstants.responsiblity.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.status.ordinal() ].fieldName = "MPSM_STATUS";
		mpsmDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		mpsmDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "MPSM_COMPLETEDBY";
		mpsmDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.completeddate.ordinal() ].fieldName = "MPSM_COMPLETEDDATE";
		mpsmDbFields[ tableFldConstants.completeddate.ordinal() ].fieldType = 'D';

		mpsmDbFields[ tableFldConstants.completedremarks.ordinal() ].fieldName = "MPSM_COMPLETEDREMARKS";
		mpsmDbFields[ tableFldConstants.completedremarks.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.opinion.ordinal() ].fieldName = "MPSM_OPINION";
		mpsmDbFields[ tableFldConstants.opinion.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.ishdrequired.ordinal() ].fieldName = "MPSM_ISHDREQUIRED";
		mpsmDbFields[ tableFldConstants.ishdrequired.ordinal() ].fieldType = 'C';

		mpsmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MPSM_TEMPFIELD1";
		mpsmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MPSM_TEMPFIELD2";
		mpsmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MPSM_TEMPFIELD3";
		mpsmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MPSM_TEMPFIELD4";
		mpsmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldName = "MPSM_MODIFIEDBY";
		mpsmDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "MPSM_ELEMENTID";
		mpsmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "MPSM_FLID";
		mpsmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MPSM_ACTIVE";
		mpsmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mpsmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MPSM_CREATEDBY";
		mpsmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mpsmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MPSM_CREATEDON";
		mpsmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mpsmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MPSM_MODIFIEDON";
		mpsmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_MPS_TL_MST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MPS_TL_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MPS_TL_MST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getMpsSelectQuery() {
		// TODO Auto-generated method stub
		return "Select * from "+TBL_MPS_TL_MST+ " where MPSM_KEYID = ? ";
	}

	public static String getTargetImpSql(String mpsKeyid) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT MPRM_KEYID, MPRM_NAME, MPRM_RESULTTYPE, MPID_ACTIVITYDESC, REPLACE(MPID_BEFORE,'{}',''),");
		sql.append("REPLACE(MPID_AFTER,'{}','') , MPID_KEYID ");
		sql.append(" FROM MPS_TL_RESULTMST,MPS_TL_IMPROVEMENTSDTL ");
		sql.append(" WHERE MPRM_RESULTTYPE = 'T' AND MPID_IMPROVEMENTID(+) = MPRM_KEYID AND MPRM_ACTIVE='Y'  ");
		//if(UIUtils.isValidKeyId(mpsKeyid))
		  if(mpsKeyid.trim().equals(""))
			  mpsKeyid=null;
			sql.append(" AND MPID_KEYID(+) ='"+mpsKeyid+"'");	 
		
		sql.append(" ORDER BY MPRM_NAME ");
		CommonMessage.debugMsg("sql for target......."+sql);
		return sql.toString();

	}
	
	public static String getResultsSql(String mpsKeyid)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("select '','',MPRM_KEYID,MPRM_NAME,MPRM_RESULTTYPE from MPS_TL_RESULTMST");
		sql.append(" where MPRM_RESULTTYPE='L' group by MPRM_KEYID, MPRM_NAME, MPRM_RESULTTYPE ");
		
		/*if(UIUtils.isValidKeyId(mpsKeyid))
			sql.append(" AND MPID_KEYID =?");	 */
			
		return sql.toString();

	}

	public static String getMpsHDScanDataSql(String mpsKeyid) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT KHDM_KAIZENID, SECT_NAME || ' - [' || SECT_CODE || ']' AS SHOP , ");
		sql.append(" CELL_NAME || ' - [' || CELL_CODE || ']' as   CELL  , ");
		sql.append(" DECODE(MCHM_KEYID,KHDM_MACHINEID,MCHM_MACHINENAME || ' - [' || MCHM_MACHINENO || ']','') ");
		sql.append("  AS  EQUIPMENT ,  TO_CHAR(KHDM_TARGETDATE,'DD-MON-YYYY') AS TARGETDATE, ");
		sql.append("  EMPM_CODE AS RESPONSIBILITY, ");
		sql.append(" DECODE (KHDM_STATUS, 'P','PENDING', 'C', 'COMPLETE', 'A', 'PENDING') AS STATUS ");
		sql.append(" FROM ");
		sql.append(" GEN_TL_SECTIONMST , GEN_TL_CELLMST , GEN_TL_MACHINEMST , KZN_TL_HDMST ,GEN_TL_EMPLOYEEMST ");
		sql.append(" WHERE CELL_SECTIONID = SECT_KEYID AND KHDM_CELLID = CELL_KEYID ");
		sql.append(" AND KHDM_MACHINEID = MCHM_KEYID(+) AND KHDM_RESPONSIBILITYID = EMPM_KEYID AND KHDM_REFDOCTYPE='MPS' ");
		sql.append(" AND KHDM_KAIZENID = " + "'" + mpsKeyid +"'" + " ");
		
		return sql.toString();
	}

	public static String getUpdateCompletionSql(TableFieldType[] fieldTypeArr,	Object[] dataArray) 
	{
		String sql ="UPDATE MPS_TL_MST SET MPSM_STATUS =  'C', "+
		fieldTypeArr[tableFldConstants.completedby.ordinal()].fieldName  +
		 " = '" +  (String)dataArray[ tableFldConstants.completedby.ordinal() ] + "' , " + 
		 fieldTypeArr[tableFldConstants.completeddate.ordinal()].fieldName  +
		" = to_date( '" +  (String)dataArray[ tableFldConstants.completeddate.ordinal() ] + "' , 'DD-MON-YYYY hh24:mi:ss') , "+ 
		fieldTypeArr[tableFldConstants.completedremarks.ordinal()].fieldName  +  
		" = '" +  (String)dataArray[ tableFldConstants.completedremarks.ordinal() ] + "' , "+ 
		fieldTypeArr[tableFldConstants.opinion.ordinal()].fieldName  +  
		" = '" +  (String)dataArray[ tableFldConstants.opinion.ordinal() ] + "' , "+ 
		fieldTypeArr[tableFldConstants.modifiedon.ordinal()].fieldName  +
		" = to_date( '" +  (String)dataArray[ tableFldConstants.modifiedon.ordinal()]+"', 'DD-MON-YYYY hh24:mi:ss')"; 
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteDtlSql(String mpsmKeyid) {
		String sql = "Delete from "+TBL_MPS_TL_DTL+" where MPSD_MPSID = '"+mpsmKeyid+"'";
		return sql;
	}

}

