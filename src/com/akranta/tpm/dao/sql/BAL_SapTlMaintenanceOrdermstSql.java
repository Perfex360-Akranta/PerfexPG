package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.SapTlMaintenanceorderSql.tableFldConstants;

public class BAL_SapTlMaintenanceOrdermstSql {

	public static final String TBL_SAP_TL_MAINTENANCE_ORDERMST = "SAP_TL_MAINTENANCE_ORDERMST";  

	TableFieldType [] momsDbFields = null;

	public enum   tableFldConstants
	{
		keyid,refdocid, refdoctype, activitytype, priority, machineid, fnlocation, plannergrp
		, mntworkcenter, orderdesc, startdt, finishdt, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getMomsDbFields() {
		return momsDbFields;
	}

	public BAL_SapTlMaintenanceOrdermstSql()
	{
		momsDbFields = new TableFieldType[ 22 ];
		for(int i = 0;i < 22; i++)
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
		return SqlUtils.getInsertSql(TBL_SAP_TL_MAINTENANCE_ORDERMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SAP_TL_MAINTENANCE_ORDERMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SAP_TL_MAINTENANCE_ORDERMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

