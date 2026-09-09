package com.akranta.tpm.dao.sql;

public class GenTlEmployeecostSql {

	public static final String TBL_GEN_TL_EMPLOYEECOST = "GEN_TL_EMPLOYEECOST";  

	TableFieldType [] empcDbFields = null;

	public enum   tableFldConstants
	{
		keyid, employeeid, effectivefromdate, effectivetilldate, costperhour
		, otcostperhour, calloutcostperhour, type, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getEmpcDbFields() {
		return empcDbFields;
	}

	public GenTlEmployeecostSql()
	{
		empcDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			empcDbFields[ i ] = new TableFieldType();
		}
		empcDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EMPC_KEYID";
		empcDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		empcDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "EMPC_EMPLOYEEID";
		empcDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		empcDbFields[ tableFldConstants.effectivefromdate.ordinal() ].fieldName = "EMPC_EFFECTIVEFROMDATE";
		empcDbFields[ tableFldConstants.effectivefromdate.ordinal() ].fieldType = 'D';

		empcDbFields[ tableFldConstants.effectivetilldate.ordinal() ].fieldName = "EMPC_EFFECTIVETILLDATE";
		empcDbFields[ tableFldConstants.effectivetilldate.ordinal() ].fieldType = 'D';

		empcDbFields[ tableFldConstants.costperhour.ordinal() ].fieldName = "EMPC_COSTPERHOUR";
		empcDbFields[ tableFldConstants.costperhour.ordinal() ].fieldType = 'N';

		empcDbFields[ tableFldConstants.otcostperhour.ordinal() ].fieldName = "EMPC_OTCOSTPERHOUR";
		empcDbFields[ tableFldConstants.otcostperhour.ordinal() ].fieldType = 'N';

		empcDbFields[ tableFldConstants.calloutcostperhour.ordinal() ].fieldName = "EMPC_CALLOUTCOSTPERHOUR";
		empcDbFields[ tableFldConstants.calloutcostperhour.ordinal() ].fieldType = 'N';

		empcDbFields[ tableFldConstants.type.ordinal() ].fieldName = "EMPC_TYPE";
		empcDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		empcDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "EMPC_TEMPFIELD2";
		empcDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		empcDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "EMPC_TEMPFIELD3";
		empcDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		empcDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "EMPC_TEMPFIELD4";
		empcDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		empcDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "EMPC_TEMPFIELD5";
		empcDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		empcDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EMPC_ACTIVE";
		empcDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		empcDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EMPC_CREATEDBY";
		empcDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		empcDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EMPC_CREATEDON";
		empcDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		empcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EMPC_MODIFIEDON";
		empcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_EMPLOYEECOST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_EMPLOYEECOST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_EMPLOYEECOST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

