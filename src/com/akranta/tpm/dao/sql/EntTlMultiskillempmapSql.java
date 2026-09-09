package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlMultiskillempmapSql {

	public static final String TBL_ENT_TL_MULTISKILLEMPMAP = "ENT_TL_MULTISKILLEMPMAP";  

	TableFieldType [] museDbFields = null;

	public enum   tableFldConstants
	{
		keyid, employeeid, unipositionid, flid, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getMuseDbFields() {
		return museDbFields;
	}

	public EntTlMultiskillempmapSql()
	{
		museDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			museDbFields[ i ] = new TableFieldType();
		}
		museDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MUSE_KEYID";
		museDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		museDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "MUSE_EMPLOYEEID";
		museDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		museDbFields[ tableFldConstants.unipositionid.ordinal() ].fieldName = "MUSE_UNIPOSITIONID";
		museDbFields[ tableFldConstants.unipositionid.ordinal() ].fieldType = 'V';

		museDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "MUSE_FLID";
		museDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		museDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MUSE_TEMPFIELD1";
		museDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		museDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MUSE_TEMPFIELD2";
		museDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		museDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MUSE_TEMPFIELD3";
		museDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		museDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MUSE_TEMPFIELD4";
		museDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		museDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MUSE_TEMPFIELD5";
		museDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		museDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MUSE_ACTIVE";
		museDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		museDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MUSE_CREATEDBY";
		museDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		museDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MUSE_CREATEDON";
		museDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		museDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MUSE_MODIFIEDON";
		museDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_MULTISKILLEMPMAP, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_MULTISKILLEMPMAP, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_MULTISKILLEMPMAP ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String selectmaster() {
		String sql = "	SELECT  * From ENT_TL_MULTISKILLEMPMAP where  MUSE_FLID = ? " ; 
		CommonMessage.debugMsg("ffffffflllllll   "+sql);
		            // "  FROM std_tl_stdworksheetmst WHERE MUSE_KEYID = ? ";
            return sql;

		
	}

}

