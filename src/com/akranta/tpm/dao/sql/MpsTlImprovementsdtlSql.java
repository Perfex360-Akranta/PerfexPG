package com.akranta.tpm.dao.sql;

public class MpsTlImprovementsdtlSql {

	public static final String TBL_MPS_TL_IMPROVEMENTSDTL = "MPS_TL_IMPROVEMENTSDTL";  

	TableFieldType [] mpidDbFields = null;

	public enum   tableFldConstants
	{
		keyid, improvementid, activitydesc, before, after, tempfield1
		, tempfield2, tempfield3, modifiedby, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getMpidDbFields() {
		return mpidDbFields;
	}

	public MpsTlImprovementsdtlSql()
	{
		mpidDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			mpidDbFields[ i ] = new TableFieldType();
		}
		mpidDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MPID_KEYID";
		mpidDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mpidDbFields[ tableFldConstants.improvementid.ordinal() ].fieldName = "MPID_IMPROVEMENTID";
		mpidDbFields[ tableFldConstants.improvementid.ordinal() ].fieldType = 'V';

		mpidDbFields[ tableFldConstants.activitydesc.ordinal() ].fieldName = "MPID_ACTIVITYDESC";
		mpidDbFields[ tableFldConstants.activitydesc.ordinal() ].fieldType = 'V';

		mpidDbFields[ tableFldConstants.before.ordinal() ].fieldName = "MPID_BEFORE";
		mpidDbFields[ tableFldConstants.before.ordinal() ].fieldType = 'V';

		mpidDbFields[ tableFldConstants.after.ordinal() ].fieldName = "MPID_AFTER";
		mpidDbFields[ tableFldConstants.after.ordinal() ].fieldType = 'V';

		mpidDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MPID_TEMPFIELD1";
		mpidDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mpidDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MPID_TEMPFIELD2";
		mpidDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mpidDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MPID_TEMPFIELD3";
		mpidDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		mpidDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldName = "MPID_MODIFIEDBY";
		mpidDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldType = 'V';

		mpidDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MPID_ACTIVE";
		mpidDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mpidDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MPID_CREATEDBY";
		mpidDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mpidDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MPID_CREATEDON";
		mpidDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mpidDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MPID_MODIFIEDON";
		mpidDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_MPS_TL_IMPROVEMENTSDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MPS_TL_IMPROVEMENTSDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'"  +
			  " AND " + fieldTypeArr[tableFldConstants.improvementid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.improvementid.ordinal() ] + "'"  ;
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MPS_TL_IMPROVEMENTSDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'"  +
			  " AND " + fieldTypeArr[tableFldConstants.improvementid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.improvementid.ordinal() ] + "'"  ;
		return sql;
	}

}

