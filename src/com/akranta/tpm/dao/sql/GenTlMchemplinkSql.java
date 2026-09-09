package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonMessage;

public class GenTlMchemplinkSql {

	public static final String TBL_GEN_TL_MCHEMPLINK = "GEN_TL_MCHEMPLINK";  

	TableFieldType [] mcemDbFields = null;

	public enum   tableFldConstants
	{
		machineid, employeeid, tempfield1, tempfield2, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getMcemDbFields() {
		return mcemDbFields;
	}

	
	public GenTlMchemplinkSql()
	{
		mcemDbFields = new TableFieldType[ 8 ];
		for(int i = 0;i < 8; i++)
		{	
			mcemDbFields[ i ] = new TableFieldType();
		}
		mcemDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "MCEM_MACHINEID";
		mcemDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		mcemDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "MCEM_EMPLOYEEID";
		mcemDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		mcemDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MCEM_TEMPFIELD1";
		mcemDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mcemDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MCEM_TEMPFIELD2";
		mcemDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mcemDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MCEM_ACTIVE";
		mcemDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mcemDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MCEM_CREATEDBY";
		mcemDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mcemDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MCEM_CREATEDON";
		mcemDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mcemDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MCEM_MODIFIEDON";
		mcemDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql;
		sql = SqlUtils.getInsertSql(TBL_GEN_TL_MCHEMPLINK, fieldTypeArr, dataArray);
		CommonMessage.debugMsg(sql);
		return sql;
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MCHEMPLINK, fieldTypeArr, dataArray);
		
		sql += " where MCEM_MACHINEID=?" ;
		
		return sql;
	}

	/*public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MCHEMPLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	*/
	public static String getDeleteSql(String bdNo)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MCHEMPLINK ;
			   sql += " where MCEM_MACHINEID = '" +  bdNo + "'";
		return sql;
	}


	public static String getUpdateSql(Object mcemDbFields2, Object[] saveArray) {
		// TODO Auto-generated method stub
		return null;
	}

}

