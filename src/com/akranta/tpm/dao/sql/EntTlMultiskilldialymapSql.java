package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlMultiskilldialymapSql {

	public static final String TBL_ENT_TL_MULTISKILLDIALYMAP = "ENT_TL_MULTISKILLDIALYMAP";  

	TableFieldType [] mudmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, date, employeeid, unipositionid, flid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, createdby, active, createdon
		, modifiedon
	}

	public TableFieldType[] getMudmDbFields() {
		return mudmDbFields;
	}

	public EntTlMultiskilldialymapSql()
	{
		mudmDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			mudmDbFields[ i ] = new TableFieldType();
		}
		mudmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MUDM_KEYID";
		mudmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mudmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "MUDM_DATE";
		mudmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		mudmDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "MUDM_EMPLOYEEID";
		mudmDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		mudmDbFields[ tableFldConstants.unipositionid.ordinal() ].fieldName = "MUDM_UNIPOSITIONID";
		mudmDbFields[ tableFldConstants.unipositionid.ordinal() ].fieldType = 'V';

		mudmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "MUDM_FLID";
		mudmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		mudmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MUDM_TEMPFIELD1";
		mudmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		mudmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MUDM_TEMPFIELD2";
		mudmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		mudmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MUDM_TEMPFIELD3";
		mudmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		mudmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MUDM_TEMPFIELD4";
		mudmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		mudmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MUDM_TEMPFIELD5";
		mudmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		mudmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MUDM_CREATEDBY";
		mudmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mudmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MUDM_ACTIVE";
		mudmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mudmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MUDM_CREATEDON";
		mudmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mudmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MUDM_MODIFIEDON";
		mudmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_MULTISKILLDIALYMAP, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_MULTISKILLDIALYMAP, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_MULTISKILLDIALYMAP ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String selectFlid() {
		String sql = "	SELECT   MUDM_KEYID,To_char(MUDM_DATE),MUDM_EMPLOYEEID,MUDM_UNIPOSITIONID, MUDM_FLID   " +
				     "  From  ENT_TL_MULTISKILLDIALYMAP where  MUDM_FLID = ?  and  MUDM_DATE =  ? "  ; 
		CommonMessage.debugMsg("ffffffflllllll   "+sql);
		        return sql;
	}

}

