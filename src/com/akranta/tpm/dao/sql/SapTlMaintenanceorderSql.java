package com.akranta.tpm.dao.sql;

public class SapTlMaintenanceorderSql {

	public static final String TBL_SAP_TL_MAINTENANCE_ORDER = "SAP_TL_MAINTENANCE_ORDER";  

	TableFieldType [] momsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, refdocid, refdoctype, activitytype, priority, machineid
		, fnlocation, plannergrp, mntworkcenter, orderdesc, startdt, finishdt
		, opno, controlkey, opdesc, work, workunit, duration, durationunit
		, cckey, fieldkey, checkingtool, idealcondn, typeofcheck, actualcondn
		, wbselement, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, tempfield6, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMomsDbFields() {
		return momsDbFields;
	}

	public SapTlMaintenanceorderSql()
	{
		momsDbFields = new TableFieldType[ 36 ];
		for(int i = 0;i < 36; i++)
		{	
			momsDbFields[ i ] = new TableFieldType();
		}
		momsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MOMS_KEYID";
		momsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "MOMS_REFDOCID";
		momsDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "MOMS_REFDOCTYPE";
		momsDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.activitytype.ordinal() ].fieldName = "MOMS_ACTIVITYTYPE";
		momsDbFields[ tableFldConstants.activitytype.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.priority.ordinal() ].fieldName = "MOMS_PRIORITY";
		momsDbFields[ tableFldConstants.priority.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "MOMS_MACHINEID";
		momsDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.fnlocation.ordinal() ].fieldName = "MOMS_FNLOCATION";
		momsDbFields[ tableFldConstants.fnlocation.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.plannergrp.ordinal() ].fieldName = "MOMS_PLANNERGRP";
		momsDbFields[ tableFldConstants.plannergrp.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.mntworkcenter.ordinal() ].fieldName = "MOMS_MNTWORKCENTER";
		momsDbFields[ tableFldConstants.mntworkcenter.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.orderdesc.ordinal() ].fieldName = "MOMS_ORDERDESC";
		momsDbFields[ tableFldConstants.orderdesc.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.startdt.ordinal() ].fieldName = "MOMS_STARTDT";
		momsDbFields[ tableFldConstants.startdt.ordinal() ].fieldType = 'D';

		momsDbFields[ tableFldConstants.finishdt.ordinal() ].fieldName = "MOMS_FINISHDT";
		momsDbFields[ tableFldConstants.finishdt.ordinal() ].fieldType = 'D';

		momsDbFields[ tableFldConstants.opno.ordinal() ].fieldName = "MOMS_OPNO";
		momsDbFields[ tableFldConstants.opno.ordinal() ].fieldType = 'N';

		momsDbFields[ tableFldConstants.controlkey.ordinal() ].fieldName = "MOMS_CONTROLKEY";
		momsDbFields[ tableFldConstants.controlkey.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.opdesc.ordinal() ].fieldName = "MOMS_OPDESC";
		momsDbFields[ tableFldConstants.opdesc.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.work.ordinal() ].fieldName = "MOMS_WORK";
		momsDbFields[ tableFldConstants.work.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.workunit.ordinal() ].fieldName = "MOMS_WORKUNIT";
		momsDbFields[ tableFldConstants.workunit.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "MOMS_DURATION";
		momsDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.durationunit.ordinal() ].fieldName = "MOMS_DURATIONUNIT";
		momsDbFields[ tableFldConstants.durationunit.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.cckey.ordinal() ].fieldName = "MOMS_CCKEY";
		momsDbFields[ tableFldConstants.cckey.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.fieldkey.ordinal() ].fieldName = "MOMS_FIELDKEY";
		momsDbFields[ tableFldConstants.fieldkey.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.checkingtool.ordinal() ].fieldName = "MOMS_CHECKINGTOOL";
		momsDbFields[ tableFldConstants.checkingtool.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.idealcondn.ordinal() ].fieldName = "MOMS_IDEALCONDN";
		momsDbFields[ tableFldConstants.idealcondn.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.typeofcheck.ordinal() ].fieldName = "MOMS_TYPEOFCHECK";
		momsDbFields[ tableFldConstants.typeofcheck.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.actualcondn.ordinal() ].fieldName = "MOMS_ACTUALCONDN";
		momsDbFields[ tableFldConstants.actualcondn.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.wbselement.ordinal() ].fieldName = "MOMS_WBSELEMENT";
		momsDbFields[ tableFldConstants.wbselement.ordinal() ].fieldType = 'N';

		momsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MOMS_TEMPFIELD1";
		momsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MOMS_TEMPFIELD2";
		momsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MOMS_TEMPFIELD3";
		momsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MOMS_TEMPFIELD4";
		momsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MOMS_TEMPFIELD5";
		momsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "MOMS_TEMPFIELD6";
		momsDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MOMS_ACTIVE";
		momsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		momsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MOMS_CREATEDBY";
		momsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		momsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MOMS_CREATEDON";
		momsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		momsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MOMS_MODIFIEDON";
		momsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SAP_TL_MAINTENANCE_ORDER, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SAP_TL_MAINTENANCE_ORDER, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SAP_TL_MAINTENANCE_ORDER ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

