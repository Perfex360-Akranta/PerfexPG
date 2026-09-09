package com.akranta.tpm.dao.sql;

public class GenTlTeamtradelinkSql {

	public static final String TBL_GEN_TL_TEAMTRADELINK = "GEN_TL_TEAMTRADELINK";  

	TableFieldType [] frpDbFields = null;

	public enum   tableFldConstants
	{
		keyid, frt_keyid, tradeid, processid, subprocessid, subsubprocessid
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFrpDbFields() {
		return frpDbFields;
	}

	public GenTlTeamtradelinkSql()
	{
		frpDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			frpDbFields[ i ] = new TableFieldType();
		}
		frpDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FRP_KEYID";
		frpDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		frpDbFields[ tableFldConstants.frt_keyid.ordinal() ].fieldName = "FRP_FRT_KEYID";
		frpDbFields[ tableFldConstants.frt_keyid.ordinal() ].fieldType = 'V';

		frpDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "FRP_TRADEID";
		frpDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		frpDbFields[ tableFldConstants.processid.ordinal() ].fieldName = "FRP_PROCESSID";
		frpDbFields[ tableFldConstants.processid.ordinal() ].fieldType = 'V';

		frpDbFields[ tableFldConstants.subprocessid.ordinal() ].fieldName = "FRP_SUBPROCESSID";
		frpDbFields[ tableFldConstants.subprocessid.ordinal() ].fieldType = 'V';

		frpDbFields[ tableFldConstants.subsubprocessid.ordinal() ].fieldName = "FRP_SUBSUBPROCESSID";
		frpDbFields[ tableFldConstants.subsubprocessid.ordinal() ].fieldType = 'V';

		frpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FRP_TEMPFIELD1";
		frpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		frpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FRP_TEMPFIELD2";
		frpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		frpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FRP_TEMPFIELD3";
		frpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		frpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FRP_TEMPFIELD4";
		frpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		frpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FRP_TEMPFIELD5";
		frpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		frpDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FRP_ACTIVE";
		frpDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		frpDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FRP_CREATEDBY";
		frpDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		frpDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FRP_CREATEDON";
		frpDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		frpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FRP_MODIFIEDON";
		frpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_TEAMTRADELINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_TEAMTRADELINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_TEAMTRADELINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getDeleteAllSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_TEAMTRADELINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.frt_keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.frt_keyid.ordinal()] + "'";
		return sql;
	}

}

