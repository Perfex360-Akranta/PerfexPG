package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class MspTlDtlSql {

	public static final String TBL_MSP_TL_DTL = "MSP_TL_DTL";  

	TableFieldType [] mspdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, indicatorid, mpms_keyid, factoryid, sectionid, cellid
		, milestone, targetdate, completedate, responsibility, completedby
		, remarks, status, assignedto, flid, tempfield8, tempfield7
		, tempfield6, tempfield5, tempfield4, tempfield3, tempfield2
		, tempfield1, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMspdDbFields() {
		return mspdDbFields;
	}

	public MspTlDtlSql()
	{
		mspdDbFields = new TableFieldType[ 27 ];
		for(int i = 0;i < 27; i++)
		{	
			mspdDbFields[ i ] = new TableFieldType();
		}
		mspdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MSPD_KEYID";
		mspdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.indicatorid.ordinal() ].fieldName = "MSPD_INDICATORID";
		mspdDbFields[ tableFldConstants.indicatorid.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.mpms_keyid.ordinal() ].fieldName = "MSPD_MPMS_KEYID";
		mspdDbFields[ tableFldConstants.mpms_keyid.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "MSPD_FACTORYID";
		mspdDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "MSPD_SECTIONID";
		mspdDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "MSPD_CELLID";
		mspdDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.milestone.ordinal() ].fieldName = "MSPD_MILESTONE";
		mspdDbFields[ tableFldConstants.milestone.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "MSPD_TARGETDATE";
		mspdDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		mspdDbFields[ tableFldConstants.completedate.ordinal() ].fieldName = "MSPD_COMPLETEDATE";
		mspdDbFields[ tableFldConstants.completedate.ordinal() ].fieldType = 'D';

		mspdDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "MSPD_RESPONSIBILITY";
		mspdDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "MSPD_COMPLETEDBY";
		mspdDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MSPD_REMARKS";
		mspdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.status.ordinal() ].fieldName = "MSPD_STATUS";
		mspdDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		mspdDbFields[ tableFldConstants.assignedto.ordinal() ].fieldName = "MSPD_ASSIGNEDTO";
		mspdDbFields[ tableFldConstants.assignedto.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "MSPD_FLID";
		mspdDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "MSPD_TEMPFIELD8";
		mspdDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "MSPD_TEMPFIELD7";
		mspdDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "MSPD_TEMPFIELD6";
		mspdDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MSPD_TEMPFIELD5";
		mspdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MSPD_TEMPFIELD4";
		mspdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MSPD_TEMPFIELD3";
		mspdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MSPD_TEMPFIELD2";
		mspdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MSPD_TEMPFIELD1";
		mspdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MSPD_ACTIVE";
		mspdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mspdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MSPD_CREATEDBY";
		mspdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mspdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MSPD_CREATEDON";
		mspdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mspdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MSPD_MODIFIEDON";
		mspdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		CommonMessage.debugMsg("getInsertSql : "+SqlUtils.getInsertSql(TBL_MSP_TL_DTL, fieldTypeArr, dataArray));
		return SqlUtils.getInsertSql(TBL_MSP_TL_DTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MSP_TL_DTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		CommonMessage.debugMsg("getUpdateSql : "+sql);
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MSP_TL_DTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String selectSql(String keyId)
	{
		String sql = "SELECT * from " + TBL_MSP_TL_DTL ;		
		sql += " where MSPD_KEYID = '" + keyId +"'";
		return sql;
	}
	public static String getCountSql(String mstId,String keyId)
	{
		String sql = "SELECT count(*) from " + TBL_MSP_TL_DTL ;		
		sql += " where MSPD_MPMS_KEYID = '" + mstId +"'";
		sql += " and MSPD_KEYID <> '" + keyId +"'";
		CommonMessage.debugMsg("sql "+sql);
		return sql;
	}

	public static String getUpdateSql1(String mspdMpmsKeyid) {
		String sql=" update MSP_TL_MST SET MPMS_STATUS=(SELECT DECODE(COUNT(*),SUM(DECODE(MSPD_STATUS,'C',1,0)),'C',DECODE(COUNT(*)"+
		" ,SUM(DECODE(MSPD_STATUS,'P',1,0)),'P','W')) AS STATUS FROM MSP_TL_DTL WHERE MSPD_MPMS_KEYID ='"+mspdMpmsKeyid+"')"+
          " WHERE MPMS_KEYID ='"+mspdMpmsKeyid+"' ";
return sql;
	}

}

