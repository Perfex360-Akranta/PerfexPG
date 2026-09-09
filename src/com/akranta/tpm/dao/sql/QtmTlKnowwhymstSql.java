package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;

public class QtmTlKnowwhymstSql {

	public static final String TBL_QTM_TL_KNOWWHYMST = "QTM_TL_KNOWWHYMST";  

	TableFieldType [] knwmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, pillarid, type, developedby, approvedby
		, quality, description, image, phenomena, prepareddate, versionno
		, versiondate, tempfield5, createdby, active, createdon, modifiedon
	}

	public TableFieldType[] getKnwmDbFields() {
		return knwmDbFields;
	}

	public QtmTlKnowwhymstSql()
	{
		knwmDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			knwmDbFields[ i ] = new TableFieldType();
		}
		knwmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KNWM_KEYID";
		knwmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		knwmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "KNWM_FLID";
		knwmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		knwmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "KNWM_ELEMENTID";
		knwmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		knwmDbFields[ tableFldConstants.pillarid.ordinal() ].fieldName = "KNWM_PILLARID";
		knwmDbFields[ tableFldConstants.pillarid.ordinal() ].fieldType = 'V';

		knwmDbFields[ tableFldConstants.type.ordinal() ].fieldName = "KNWM_TYPE";
		knwmDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		knwmDbFields[ tableFldConstants.developedby.ordinal() ].fieldName = "KNWM_DEVELOPEDBY";
		knwmDbFields[ tableFldConstants.developedby.ordinal() ].fieldType = 'V';

		knwmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "KNWM_APPROVEDBY";
		knwmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		knwmDbFields[ tableFldConstants.quality.ordinal() ].fieldName = "KNWM_QUALITY";
		knwmDbFields[ tableFldConstants.quality.ordinal() ].fieldType = 'V';

		knwmDbFields[ tableFldConstants.description.ordinal() ].fieldName = "KNWM_DESCRIPTION";
		knwmDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		knwmDbFields[ tableFldConstants.image.ordinal() ].fieldName = "KNWM_IMAGE";
		knwmDbFields[ tableFldConstants.image.ordinal() ].fieldType = 'V';

		knwmDbFields[ tableFldConstants.phenomena.ordinal() ].fieldName = "KNWM_PHENOMENA";
		knwmDbFields[ tableFldConstants.phenomena.ordinal() ].fieldType = 'C';

		knwmDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldName = "KNWM_PREPAREDDATE";
		knwmDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldType = 'C';

		knwmDbFields[ tableFldConstants.versionno.ordinal() ].fieldName = "KNWM_VERSIONNO";
		knwmDbFields[ tableFldConstants.versionno.ordinal() ].fieldType = 'N';

		knwmDbFields[ tableFldConstants.versiondate.ordinal() ].fieldName = "KNWM_VERSIONDATE";
		knwmDbFields[ tableFldConstants.versiondate.ordinal() ].fieldType = 'D';

		knwmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KNWM_TEMPFIELD5";
		knwmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		knwmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KNWM_CREATEDBY";
		knwmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		knwmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KNWM_ACTIVE";
		knwmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		knwmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KNWM_CREATEDON";
		knwmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		knwmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KNWM_MODIFIEDON";
		knwmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_KNOWWHYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_KNOWWHYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_KNOWWHYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getselectsql() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM QTM_TL_KNOWWHYMST WHERE KNWM_KEYID = ?";
		return sql;
	}

	

}

