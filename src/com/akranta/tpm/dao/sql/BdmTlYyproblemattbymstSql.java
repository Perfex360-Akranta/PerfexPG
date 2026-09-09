package com.akranta.tpm.dao.sql;
import com.akranta.tpm.utils.CommonMessage;
public class BdmTlYyproblemattbymstSql {

	public static final String TBL_BDM_TL_YYPROBLEMATTBYMST = "BDM_TL_YYPROBLEMATTBYMST";  

	TableFieldType [] wwpaDbFields = null;

	public enum   tableFldConstants
	{
		keyid, wwms_keyid, empm_keyid, tempfield1, tempfield2, tempfield3
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getWwpaDbFields() {
		return wwpaDbFields;
	}

	public BdmTlYyproblemattbymstSql()
	{
		wwpaDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			wwpaDbFields[ i ] = new TableFieldType();
		}
		wwpaDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WWPA_KEYID";
		wwpaDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		wwpaDbFields[ tableFldConstants.wwms_keyid.ordinal() ].fieldName = "WWPA_WWMS_KEYID";
		wwpaDbFields[ tableFldConstants.wwms_keyid.ordinal() ].fieldType = 'V';

		wwpaDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "WWPA_EMPM_KEYID";
		wwpaDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		wwpaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "WWPA_TEMPFIELD1";
		wwpaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		wwpaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "WWPA_TEMPFIELD2";
		wwpaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		wwpaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "WWPA_TEMPFIELD3";
		wwpaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		wwpaDbFields[ tableFldConstants.active.ordinal() ].fieldName = "WWPA_ACTIVE";
		wwpaDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		wwpaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WWPA_CREATEDBY";
		wwpaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wwpaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WWPA_CREATEDON";
		wwpaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wwpaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WWPA_MODIFIEDON";
		wwpaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		CommonMessage.debugMsg("inside SQL");
		return SqlUtils.getInsertSql(TBL_BDM_TL_YYPROBLEMATTBYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_YYPROBLEMATTBYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_YYPROBLEMATTBYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

