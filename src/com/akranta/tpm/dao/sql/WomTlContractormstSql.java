package com.akranta.tpm.dao.sql;

public class WomTlContractormstSql {

	public static final String TBL_WOM_TL_CONTRACTORMST = "WOM_TL_CONTRACTORMST";  

	TableFieldType [] cncsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, vendorid, unskilledgradeid, effectivefromdate, effectivetodate
		, costperhour, otcost, holidaycost, type, tempfield1, tempfield2
		, tempfield3, tempfield4, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getCncsDbFields() {
		return cncsDbFields;
	}

	public WomTlContractormstSql()
	{
		cncsDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			cncsDbFields[ i ] = new TableFieldType();
		}
		cncsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CNCS_KEYID";
		cncsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		cncsDbFields[ tableFldConstants.vendorid.ordinal() ].fieldName = "CNCS_VENDORID";
		cncsDbFields[ tableFldConstants.vendorid.ordinal() ].fieldType = 'V';

		cncsDbFields[ tableFldConstants.unskilledgradeid.ordinal() ].fieldName = "CNCS_UNSKILLEDGRADEID";
		cncsDbFields[ tableFldConstants.unskilledgradeid.ordinal() ].fieldType = 'V';

		cncsDbFields[ tableFldConstants.effectivefromdate.ordinal() ].fieldName = "CNCS_EFFECTIVEFROMDATE";
		cncsDbFields[ tableFldConstants.effectivefromdate.ordinal() ].fieldType = 'D';

		cncsDbFields[ tableFldConstants.effectivetodate.ordinal() ].fieldName = "CNCS_EFFECTIVETODATE";
		cncsDbFields[ tableFldConstants.effectivetodate.ordinal() ].fieldType = 'D';

		cncsDbFields[ tableFldConstants.costperhour.ordinal() ].fieldName = "CNCS_COSTPERHOUR";
		cncsDbFields[ tableFldConstants.costperhour.ordinal() ].fieldType = 'N';

		cncsDbFields[ tableFldConstants.otcost.ordinal() ].fieldName = "CNCS_OTCOST";
		cncsDbFields[ tableFldConstants.otcost.ordinal() ].fieldType = 'N';

		cncsDbFields[ tableFldConstants.holidaycost.ordinal() ].fieldName = "CNCS_HOLIDAYCOST";
		cncsDbFields[ tableFldConstants.holidaycost.ordinal() ].fieldType = 'N';

		cncsDbFields[ tableFldConstants.type.ordinal() ].fieldName = "CNCS_TYPE";
		cncsDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		cncsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CNCS_TEMPFIELD1";
		cncsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		cncsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CNCS_TEMPFIELD2";
		cncsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		cncsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CNCS_TEMPFIELD3";
		cncsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		cncsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CNCS_TEMPFIELD4";
		cncsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		cncsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CNCS_ACTIVE";
		cncsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		cncsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CNCS_CREATEDBY";
		cncsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		cncsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CNCS_CREATEDON";
		cncsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		cncsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CNCS_MODIFIEDON";
		cncsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOM_TL_CONTRACTORMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOM_TL_CONTRACTORMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_WOM_TL_CONTRACTORMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

