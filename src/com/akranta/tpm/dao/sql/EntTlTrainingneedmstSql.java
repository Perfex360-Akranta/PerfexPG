package com.akranta.tpm.dao.sql;

public class EntTlTrainingneedmstSql {

	public static final String TBL_ENT_TL_TRAININGNEEDMST = "ENT_TL_TRAININGNEEDMST";  

	TableFieldType [] tnimDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, type, typeid, topicid, date, remarks, elementid
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getTnimDbFields() {
		return tnimDbFields;
	}

	public EntTlTrainingneedmstSql()
	{
		tnimDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			tnimDbFields[ i ] = new TableFieldType();
		}
		tnimDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TNIM_KEYID";
		tnimDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		tnimDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "TNIM_FLID";
		tnimDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		tnimDbFields[ tableFldConstants.type.ordinal() ].fieldName = "TNIM_TYPE";
		tnimDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		tnimDbFields[ tableFldConstants.typeid.ordinal() ].fieldName = "TNIM_TYPEID";
		tnimDbFields[ tableFldConstants.typeid.ordinal() ].fieldType = 'V';

		tnimDbFields[ tableFldConstants.topicid.ordinal() ].fieldName = "TNIM_TOPICID";
		tnimDbFields[ tableFldConstants.topicid.ordinal() ].fieldType = 'V';

		tnimDbFields[ tableFldConstants.date.ordinal() ].fieldName = "TNIM_DATE";
		tnimDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		tnimDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "TNIM_REMARKS";
		tnimDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		tnimDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "TNIM_ELEMENTID";
		tnimDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		tnimDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TNIM_TEMPFIELD2";
		tnimDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		tnimDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TNIM_TEMPFIELD3";
		tnimDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		tnimDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "TNIM_TEMPFIELD4";
		tnimDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		tnimDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "TNIM_TEMPFIELD5";
		tnimDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		tnimDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TNIM_ACTIVE";
		tnimDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		tnimDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TNIM_CREATEDBY";
		tnimDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		tnimDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TNIM_CREATEDON";
		tnimDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		tnimDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TNIM_MODIFIEDON";
		tnimDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TRAININGNEEDMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TRAININGNEEDMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TRAININGNEEDMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

