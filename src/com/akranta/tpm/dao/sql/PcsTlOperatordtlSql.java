package com.akranta.tpm.dao.sql;

public class PcsTlOperatordtlSql {

	public static final String TBL_PCS_TL_OPERATORDTL = "PCS_TL_OPERATORDTL";  

	TableFieldType [] popdDbFields = null;

	public enum   tableFldConstants
	{
		plmasterid, pldetailsid, plemployeeid, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getPopdDbFields() {
		return popdDbFields;
	}

	public PcsTlOperatordtlSql()
	{
		popdDbFields = new TableFieldType[ 7 ];
		for(int i = 0;i < 7; i++)
		{	
			popdDbFields[ i ] = new TableFieldType();
		}
		popdDbFields[ tableFldConstants.plmasterid.ordinal() ].fieldName = "POPD_PLMASTERID";
		popdDbFields[ tableFldConstants.plmasterid.ordinal() ].fieldType = 'V';

		popdDbFields[ tableFldConstants.pldetailsid.ordinal() ].fieldName = "POPD_PLDETAILSID";
		popdDbFields[ tableFldConstants.pldetailsid.ordinal() ].fieldType = 'V';

		popdDbFields[ tableFldConstants.plemployeeid.ordinal() ].fieldName = "POPD_PLEMPLOYEEID";
		popdDbFields[ tableFldConstants.plemployeeid.ordinal() ].fieldType = 'V';

		popdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "POPD_ACTIVE";
		popdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		popdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "POPD_CREATEDBY";
		popdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		popdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "POPD_CREATEDON";
		popdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		popdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "POPD_MODIFIEDON";
		popdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_OPERATORDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_OPERATORDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.pldetailsid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.pldetailsid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_OPERATORDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.pldetailsid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.pldetailsid.ordinal()] + "'";
		return sql;
	}

}

