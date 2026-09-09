package com.akranta.tpm.dao.sql;

public class BdmTlSetupadjsplitSql {

	public static final String TBL_BDM_TL_SETUPADJSPLIT = "BDM_TL_SETUPADJSPLIT";  

	TableFieldType [] supsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, machineid, refdocid, splitdate, splitshift, duration, lossid
		, active, createdby, createdon, modificeon
	}

	public TableFieldType[] getSupsDbFields() {
		return supsDbFields;
	}

	public BdmTlSetupadjsplitSql()
	{
		supsDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			supsDbFields[ i ] = new TableFieldType();
		}
		supsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SUPS_KEYID";
		supsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		supsDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "SUPS_MACHINEID";
		supsDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		supsDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "SUPS_REFDOCID";
		supsDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		supsDbFields[ tableFldConstants.splitdate.ordinal() ].fieldName = "SUPS_SPLITDATE";
		supsDbFields[ tableFldConstants.splitdate.ordinal() ].fieldType = 'D';

		supsDbFields[ tableFldConstants.splitshift.ordinal() ].fieldName = "SUPS_SPLITSHIFT";
		supsDbFields[ tableFldConstants.splitshift.ordinal() ].fieldType = 'V';

		supsDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "SUPS_DURATION";
		supsDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'N';

		supsDbFields[ tableFldConstants.lossid.ordinal() ].fieldName = "SUPS_LOSSID";
		supsDbFields[ tableFldConstants.lossid.ordinal() ].fieldType = 'V';

		supsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SUPS_ACTIVE";
		supsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		supsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SUPS_CREATEDBY";
		supsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		supsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SUPS_CREATEDON";
		supsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		supsDbFields[ tableFldConstants.modificeon.ordinal() ].fieldName = "SUPS_MODIFICEON";
		supsDbFields[ tableFldConstants.modificeon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_SETUPADJSPLIT, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_SETUPADJSPLIT, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_SETUPADJSPLIT ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String deleteExist(String refDocId)
	{
		String sql = "DELETE "+TBL_BDM_TL_SETUPADJSPLIT ;
		
		sql += " WHERE SUPS_REFDOCID ='"+refDocId+"'";
		
		return sql;
	}

}

