package com.akranta.tpm.dao.sql;

public class SopTlVisualchecklistmstSql {

	public static final String TBL_SOP_TL_VISUALCHECKLISTMST = "SOP_TL_VISUALCHECKLISTMST";  

	TableFieldType [] vccmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, title, tempfield1, flid, tempfield2, tempfield3
		, tempfield4, tempfield5, createdby, active, createdon, modifiedon
	}

	public TableFieldType[] getVccmDbFields() {
		return vccmDbFields;
	}

	public SopTlVisualchecklistmstSql()
	{
		vccmDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			vccmDbFields[ i ] = new TableFieldType();
		}
		vccmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "VCCM_KEYID";
		vccmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		vccmDbFields[ tableFldConstants.title.ordinal() ].fieldName = "VCCM_TITLE";
		vccmDbFields[ tableFldConstants.title.ordinal() ].fieldType = 'V';

		vccmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "VCCM_TEMPFIELD1";
		vccmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		vccmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "VCCM_FLID";
		vccmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'C';

		vccmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "VCCM_TEMPFIELD2";
		vccmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		vccmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "VCCM_TEMPFIELD3";
		vccmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		vccmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "VCCM_TEMPFIELD4";
		vccmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		vccmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "VCCM_TEMPFIELD5";
		vccmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		vccmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "VCCM_CREATEDBY";
		vccmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		vccmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "VCCM_ACTIVE";
		vccmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		vccmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "VCCM_CREATEDON";
		vccmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		vccmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "VCCM_MODIFIEDON";
		vccmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SOP_TL_VISUALCHECKLISTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SOP_TL_VISUALCHECKLISTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SOP_TL_VISUALCHECKLISTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getselectsql() {
		String sql = "SELECT VCCM_KEYID,VCCM_TITLE,VCCM_TEMPFIELD1,VCCM_FLID,VCCM_TEMPFIELD2,VCCM_TEMPFIELD3,VCCM_TEMPFIELD4,";
		sql+= "VCCM_TEMPFIELD5,VCCM_CREATEDBY,VCCM_ACTIVE,VCCM_CREATEDON,VCCM_MODIFIEDON FROM SOP_TL_VISUALCHECKLISTMST WHERE VCCM_KEYID = ? ";
	 
		return sql;
	}

	

}

