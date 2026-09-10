package com.akranta.tpm.dao.sql;

public class BAL_SapTlMaintenanceOrderdtlSql {

	public static final String TBL_SAP_TL_MAINTENANCE_ORDERDTL = "SAP_TL_MAINTENANCE_ORDERDTL";  

	TableFieldType [] modtDbFields = null;

	public enum   tableFldConstants
	{
		keyid, momskeyid,  opno, controlkey, opdesc, work, workunit
		, duration, durationunit, cckey, fieldkey, checkingtool, idealcondn
		, typeofcheck, actualcondn, wbselement, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getModtDbFields() {
		return modtDbFields;
	}

	public BAL_SapTlMaintenanceOrderdtlSql()
	{
		modtDbFields = new TableFieldType[ 26 ];
		for(int i = 0;i < 26; i++)
		{	
			modtDbFields[ i ] = new TableFieldType();
		}
		modtDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MODT_KEYID";
		modtDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.momskeyid.ordinal() ].fieldName = "MODT_MOMS_KEYID";
		modtDbFields[ tableFldConstants.momskeyid.ordinal() ].fieldType = 'V';

		 
		modtDbFields[ tableFldConstants.opno.ordinal() ].fieldName = "MODT_OPNO";
		modtDbFields[ tableFldConstants.opno.ordinal() ].fieldType = 'N';

		modtDbFields[ tableFldConstants.controlkey.ordinal() ].fieldName = "MODT_CONTROLKEY";
		modtDbFields[ tableFldConstants.controlkey.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.opdesc.ordinal() ].fieldName = "MODT_OPDESC";
		modtDbFields[ tableFldConstants.opdesc.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.work.ordinal() ].fieldName = "MODT_WORK";
		modtDbFields[ tableFldConstants.work.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.workunit.ordinal() ].fieldName = "MODT_WORKUNIT";
		modtDbFields[ tableFldConstants.workunit.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "MODT_DURATION";
		modtDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.durationunit.ordinal() ].fieldName = "MODT_DURATIONUNIT";
		modtDbFields[ tableFldConstants.durationunit.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.cckey.ordinal() ].fieldName = "MODT_CCKEY";
		modtDbFields[ tableFldConstants.cckey.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.fieldkey.ordinal() ].fieldName = "MODT_FIELDKEY";
		modtDbFields[ tableFldConstants.fieldkey.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.checkingtool.ordinal() ].fieldName = "MODT_CHECKINGTOOL";
		modtDbFields[ tableFldConstants.checkingtool.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.idealcondn.ordinal() ].fieldName = "MODT_IDEALCONDN";
		modtDbFields[ tableFldConstants.idealcondn.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.typeofcheck.ordinal() ].fieldName = "MODT_TYPEOFCHECK";
		modtDbFields[ tableFldConstants.typeofcheck.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.actualcondn.ordinal() ].fieldName = "MODT_ACTUALCONDN";
		modtDbFields[ tableFldConstants.actualcondn.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.wbselement.ordinal() ].fieldName = "MODT_WBSELEMENT";
		modtDbFields[ tableFldConstants.wbselement.ordinal() ].fieldType = 'N';

		modtDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MODT_TEMPFIELD1";
		modtDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		modtDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MODT_TEMPFIELD2";
		modtDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		modtDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MODT_TEMPFIELD3";
		modtDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		modtDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MODT_TEMPFIELD4";
		modtDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		modtDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MODT_TEMPFIELD5";
		modtDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		modtDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "MODT_TEMPFIELD6";
		modtDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		modtDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MODT_ACTIVE";
		modtDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		modtDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MODT_CREATEDBY";
		modtDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		modtDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MODT_CREATEDON";
		modtDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		modtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MODT_MODIFIEDON";
		modtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SAP_TL_MAINTENANCE_ORDERDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SAP_TL_MAINTENANCE_ORDERDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SAP_TL_MAINTENANCE_ORDERDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

