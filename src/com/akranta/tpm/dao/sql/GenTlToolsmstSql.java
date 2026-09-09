package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlToolsmstSql {

	public static final String TBL_GEN_TL_TOOLSMST = "GEN_TL_TOOLSMST";  

	TableFieldType [] tolmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, code, category, uses, remarks, isimageavl, type
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getTolmDbFields() {
		return tolmDbFields;
	}

	public GenTlToolsmstSql()
	{
		tolmDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			tolmDbFields[ i ] = new TableFieldType();
		}
		tolmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TOLM_KEYID";
		tolmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		tolmDbFields[ tableFldConstants.name.ordinal() ].fieldName = "TOLM_NAME";
		tolmDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		tolmDbFields[ tableFldConstants.code.ordinal() ].fieldName = "TOLM_CODE";
		tolmDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		tolmDbFields[ tableFldConstants.category.ordinal() ].fieldName = "TOLM_CATEGORY";
		tolmDbFields[ tableFldConstants.category.ordinal() ].fieldType = 'V';

		tolmDbFields[ tableFldConstants.uses.ordinal() ].fieldName = "TOLM_USES";
		tolmDbFields[ tableFldConstants.uses.ordinal() ].fieldType = 'V';

		tolmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "TOLM_REMARKS";
		tolmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		tolmDbFields[ tableFldConstants.isimageavl.ordinal() ].fieldName = "TOLM_ISIMAGEAVL";
		tolmDbFields[ tableFldConstants.isimageavl.ordinal() ].fieldType = 'V';

		tolmDbFields[ tableFldConstants.type.ordinal() ].fieldName = "TOLM_TYPE";
		tolmDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		tolmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TOLM_ACTIVE";
		tolmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		tolmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TOLM_CREATEDBY";
		tolmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		tolmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TOLM_CREATEDON";
		tolmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		tolmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TOLM_MODIFIEDON";
		tolmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_TOOLSMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		CommonMessage.debugMsg("main in toolmst");
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_TOOLSMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		CommonMessage.debugMsg("sqlssssss in tollsmst"+sql);
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_TOOLSMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String selectSql()
	{
		return "SELECT * from " + TBL_GEN_TL_TOOLSMST + " where TOLM_KEYID= ?";
	}	

}

