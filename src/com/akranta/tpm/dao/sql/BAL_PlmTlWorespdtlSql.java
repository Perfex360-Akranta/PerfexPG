package com.akranta.tpm.dao.sql;

public class BAL_PlmTlWorespdtlSql {

	public static final String TBL_BAL_PLM_TL_WORESPDTL = "BAL_PLM_TL_WORESPDTL";  

	TableFieldType [] pwrdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, masterid, tradeid, empid, effectfrom, effecttill, tempfield1
		, tempfield2, tempfield3, tempfield4, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getPwrdDbFields() {
		return pwrdDbFields;
	}

	public BAL_PlmTlWorespdtlSql()
	{
		pwrdDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			pwrdDbFields[ i ] = new TableFieldType();
		}
		pwrdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PWRD_KEYID";
		pwrdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pwrdDbFields[ tableFldConstants.masterid.ordinal() ].fieldName = "PWRD_MASTERID";
		pwrdDbFields[ tableFldConstants.masterid.ordinal() ].fieldType = 'V';

		pwrdDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "PWRD_TRADEID";
		pwrdDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		pwrdDbFields[ tableFldConstants.empid.ordinal() ].fieldName = "PWRD_EMPID";
		pwrdDbFields[ tableFldConstants.empid.ordinal() ].fieldType = 'V';

		pwrdDbFields[ tableFldConstants.effectfrom.ordinal() ].fieldName = "PWRD_EFFECTFROM";
		pwrdDbFields[ tableFldConstants.effectfrom.ordinal() ].fieldType = 'D';

		pwrdDbFields[ tableFldConstants.effecttill.ordinal() ].fieldName = "PWRD_EFFECTTILL";
		pwrdDbFields[ tableFldConstants.effecttill.ordinal() ].fieldType = 'D';

		pwrdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PWRD_TEMPFIELD1";
		pwrdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		pwrdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PWRD_TEMPFIELD2";
		pwrdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		pwrdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PWRD_TEMPFIELD3";
		pwrdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		pwrdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PWRD_TEMPFIELD4";
		pwrdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		pwrdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PWRD_ACTIVE";
		pwrdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pwrdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PWRD_CREATEDBY";
		pwrdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pwrdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PWRD_CREATEDON";
		pwrdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pwrdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PWRD_MODIFIEDON";
		pwrdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		System.out.println("inside insert sql");
		return SqlUtils.getInsertSql(TBL_BAL_PLM_TL_WORESPDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BAL_PLM_TL_WORESPDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BAL_PLM_TL_WORESPDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

