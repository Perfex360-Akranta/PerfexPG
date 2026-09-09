package com.akranta.tpm.dao.sql;

public class GenTlImrcontrolchartSql {

	public static final String TBL_GEN_TL_IMRCONTROLCHART = "GEN_TL_IMRCONTROLCHART";  

	TableFieldType [] imrcDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, date, shiftid, orderno, samplereading, tempfield1
		, tempfield2, tempfield3, tempfield4, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getImrcDbFields() {
		return imrcDbFields;
	}

	public GenTlImrcontrolchartSql()
	{
		imrcDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			imrcDbFields[ i ] = new TableFieldType();
		}
		imrcDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "IMRC_KEYID";
		imrcDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		imrcDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "IMRC_FLID";
		imrcDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		imrcDbFields[ tableFldConstants.date.ordinal() ].fieldName = "IMRC_DATE";
		imrcDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		imrcDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "IMRC_SHIFTID";
		imrcDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		imrcDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "IMRC_ORDERNO";
		imrcDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'N';

		imrcDbFields[ tableFldConstants.samplereading.ordinal() ].fieldName = "IMRC_SAMPLEREADING";
		imrcDbFields[ tableFldConstants.samplereading.ordinal() ].fieldType = 'V';

		imrcDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "IMRC_TEMPFIELD1";
		imrcDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		imrcDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "IMRC_TEMPFIELD2";
		imrcDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		imrcDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "IMRC_TEMPFIELD3";
		imrcDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		imrcDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "IMRC_TEMPFIELD4";
		imrcDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		imrcDbFields[ tableFldConstants.active.ordinal() ].fieldName = "IMRC_ACTIVE";
		imrcDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		imrcDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "IMRC_CREATEDBY";
		imrcDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		imrcDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "IMRC_CREATEDON";
		imrcDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		imrcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "IMRC_MODIFIEDON";
		imrcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_IMRCONTROLCHART, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_IMRCONTROLCHART, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_IMRCONTROLCHART ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getSingledata() {
		
		String sql = "select * from GEN_TL_IMRCONTROLCHART WHERE IMRC_KEYID = ? " ;
		
		return sql;
	}

}

