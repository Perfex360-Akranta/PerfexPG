package com.akranta.tpm.dao.sql;


public class BdmTlWhywhydtlSql {

	public static final String TBL_BDM_TL_WHYWHYDTL = "BDM_TL_WHYWHYDTL";  

	TableFieldType [] wwdtDbFields = null;

	public enum   tableFldConstants
	{
		keyid, wwms_keyid, slno, why, answer, action, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getWwdtDbFields() {
		return wwdtDbFields;
	}

	public BdmTlWhywhydtlSql()
	{
		wwdtDbFields = new TableFieldType[ 9 ];
		for(int i = 0;i < 9; i++)
		{	
			wwdtDbFields[ i ] = new TableFieldType();
		}
		wwdtDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WWDT_KEYID";
		wwdtDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		wwdtDbFields[ tableFldConstants.wwms_keyid.ordinal() ].fieldName = "WWDT_WWMS_KEYID";
		wwdtDbFields[ tableFldConstants.wwms_keyid.ordinal() ].fieldType = 'V';

		wwdtDbFields[ tableFldConstants.slno.ordinal() ].fieldName = "WWDT_SLNO";
		wwdtDbFields[ tableFldConstants.slno.ordinal() ].fieldType = 'N';

		wwdtDbFields[ tableFldConstants.why.ordinal() ].fieldName = "WWDT_WHY";
		wwdtDbFields[ tableFldConstants.why.ordinal() ].fieldType = 'V';

		wwdtDbFields[ tableFldConstants.answer.ordinal() ].fieldName = "WWDT_ANSWER";
		wwdtDbFields[ tableFldConstants.answer.ordinal() ].fieldType = 'V';

		wwdtDbFields[ tableFldConstants.action.ordinal() ].fieldName = "WWDT_ACTION";
		wwdtDbFields[ tableFldConstants.action.ordinal() ].fieldType = 'V';

		wwdtDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WWDT_CREATEDBY";
		wwdtDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wwdtDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WWDT_CREATEDON";
		wwdtDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wwdtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WWDT_MODIFIEDON";
		wwdtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_WHYWHYDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_WHYWHYDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_WHYWHYDTL;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getDeleteAllKZNDtl(TableFieldType [] fieldTypeArr, String masterID)
	{
		String sql = "DELETE from " + TBL_BDM_TL_WHYWHYDTL;
		
		sql += " where " + fieldTypeArr[tableFldConstants.wwms_keyid.ordinal()].fieldName  +
			  " = '" +  masterID + "'";
		return sql;
	}
	
	public static String getSelectWWDT(String masterID)
	{
		 return "select *  from " + TBL_BDM_TL_WHYWHYDTL + " where wwdt_wwms_keyid = '" + masterID + "'" ;
	} 
	public static String delYYDtlSql(String detailID)
	{
		String sql = "DELETE from " + TBL_BDM_TL_WHYWHYDTL +" where WWDT_KEYID = '"+detailID+"'";
		return sql;
	}
	
}

