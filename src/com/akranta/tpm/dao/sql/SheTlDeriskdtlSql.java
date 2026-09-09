package com.akranta.tpm.dao.sql;

public class SheTlDeriskdtlSql {

	public static final String TBL_SHE_TL_DERISKDTL = "SHE_TL_DERISKDTL";  

	TableFieldType [] dradDbFields = null;

	public enum   tableFldConstants
	{
		keyid, dram_keyid, rasd_keyid, date, preparedby, probablityid
		, seviorityid, riskval, risklevelid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getDradDbFields() {
		return dradDbFields;
	}

	public SheTlDeriskdtlSql()
	{
		dradDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			dradDbFields[ i ] = new TableFieldType();
		}
		dradDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DRAD_KEYID";
		dradDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.dram_keyid.ordinal() ].fieldName = "DRAD_DRAM_KEYID";
		dradDbFields[ tableFldConstants.dram_keyid.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.rasd_keyid.ordinal() ].fieldName = "DRAD_RASD_KEYID";
		dradDbFields[ tableFldConstants.rasd_keyid.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.date.ordinal() ].fieldName = "DRAD_DATE";
		dradDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		dradDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "DRAD_PREPAREDBY";
		dradDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.probablityid.ordinal() ].fieldName = "DRAD_PROBABLITYID";
		dradDbFields[ tableFldConstants.probablityid.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.seviorityid.ordinal() ].fieldName = "DRAD_SEVIORITYID";
		dradDbFields[ tableFldConstants.seviorityid.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.riskval.ordinal() ].fieldName = "DRAD_RISKVAL";
		dradDbFields[ tableFldConstants.riskval.ordinal() ].fieldType = 'N';

		dradDbFields[ tableFldConstants.risklevelid.ordinal() ].fieldName = "DRAD_RISKLEVELID";
		dradDbFields[ tableFldConstants.risklevelid.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "DRAD_TEMPFIELD1";
		dradDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "DRAD_TEMPFIELD2";
		dradDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "DRAD_TEMPFIELD3";
		dradDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "DRAD_TEMPFIELD4";
		dradDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "DRAD_TEMPFIELD5";
		dradDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DRAD_ACTIVE";
		dradDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		dradDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DRAD_CREATEDBY";
		dradDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dradDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DRAD_CREATEDON";
		dradDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dradDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DRAD_MODIFIEDON";
		dradDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_DERISKDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SHE_TL_DERISKDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_DERISKDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

