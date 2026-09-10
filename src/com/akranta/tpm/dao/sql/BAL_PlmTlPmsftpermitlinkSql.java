package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;

public class BAL_PlmTlPmsftpermitlinkSql {

	public static final String TBL_PLM_TL_PMSFTPERMITLINK = "PLM_TL_PMSFTPERMITLINK";  

	TableFieldType [] psplDbFields = null;

	public enum   tableFldConstants
	{
		pmstandardid, sftpermitid, sftpermittype, tempfield1
	}

	public TableFieldType[] getPsplDbFields() {
		return psplDbFields;
	}

	public BAL_PlmTlPmsftpermitlinkSql()
	{
		psplDbFields = new TableFieldType[ 4 ];
		for(int i = 0;i < 4; i++)
		{	
			psplDbFields[ i ] = new TableFieldType();
		}
		psplDbFields[ tableFldConstants.pmstandardid.ordinal() ].fieldName = "PSPL_PMSTANDARDID";
		psplDbFields[ tableFldConstants.pmstandardid.ordinal() ].fieldType = 'V';

		psplDbFields[ tableFldConstants.sftpermitid.ordinal() ].fieldName = "PSPL_SFTPERMITID";
		psplDbFields[ tableFldConstants.sftpermitid.ordinal() ].fieldType = 'V';

		psplDbFields[ tableFldConstants.sftpermittype.ordinal() ].fieldName = "PSPL_SFTPERMITTYPE";
		psplDbFields[ tableFldConstants.sftpermittype.ordinal() ].fieldType = 'V';

		psplDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PSPL_TEMPFIELD1";
		psplDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		CommonFunctions.debugMsg("insert ppLink");
		return SqlUtils.getInsertSql(TBL_PLM_TL_PMSFTPERMITLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_PMSFTPERMITLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.pmstandardid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.pmstandardid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_PMSFTPERMITLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.pmstandardid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.pmstandardid.ordinal()] + "'";
		return sql;
	}

	public static String delPermLinkdatasql(String pmsdKey) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM " + TBL_PLM_TL_PMSFTPERMITLINK + " WHERE PSPL_PMSTANDARDID = '"+pmsdKey+"'";
		CommonFunctions.debugMsg(sql);
		return sql;
	}

	

}

