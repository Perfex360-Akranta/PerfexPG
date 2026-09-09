package com.akranta.tpm.dao.sql;

public class GenTlJhactivitychartdtlSql {

	public static final String TBL_GEN_TL_JHACTIVITYCHARTDTL = "GEN_TL_JHACTIVITYCHARTDTL";  

	TableFieldType [] jacdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, achm_keyid, shiftid, description, time, remarks, tempfield1
		, tempfield2, createdby, createdon, modifiedon, tempfield3, jacdtempfield4
		, tempfield5, active
	}

	public TableFieldType[] getJacdDbFields() {
		return jacdDbFields;
	}

	public GenTlJhactivitychartdtlSql()
	{
		jacdDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			jacdDbFields[ i ] = new TableFieldType();
		}
		jacdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "JACD_KEYID";
		jacdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		jacdDbFields[ tableFldConstants.achm_keyid.ordinal() ].fieldName = "JACD_ACHM_KEYID";
		jacdDbFields[ tableFldConstants.achm_keyid.ordinal() ].fieldType = 'V';

		jacdDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "JACD_SHIFTID";
		jacdDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		jacdDbFields[ tableFldConstants.description.ordinal() ].fieldName = "JACD_DESCRIPTION";
		jacdDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		jacdDbFields[ tableFldConstants.time.ordinal() ].fieldName = "JACD_TIME";
		jacdDbFields[ tableFldConstants.time.ordinal() ].fieldType = 'V';

		jacdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "JACD_REMARKS";
		jacdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		jacdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "JACD_TEMPFIELD1";
		jacdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		jacdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "JACD_TEMPFIELD2";
		jacdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		jacdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "JACD_CREATEDBY";
		jacdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		jacdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "JACD_CREATEDON";
		jacdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		jacdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "JACD_MODIFIEDON";
		jacdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

		jacdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "JACD_TEMPFIELD3";
		jacdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		jacdDbFields[ tableFldConstants.jacdtempfield4.ordinal() ].fieldName = "JACDTEMPFIELD4";
		jacdDbFields[ tableFldConstants.jacdtempfield4.ordinal() ].fieldType = 'C';

		jacdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "JACD_TEMPFIELD5";
		jacdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		jacdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "JACD_ACTIVE";
		jacdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_JHACTIVITYCHARTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_JHACTIVITYCHARTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_JHACTIVITYCHARTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getDeleteMstSql(String achmKeyid) {
		String sql = "DELETE from " + TBL_GEN_TL_JHACTIVITYCHARTDTL ;
		sql += " where JACD_ACHM_KEYID='"+achmKeyid+"'";
		return sql;
	}

}

