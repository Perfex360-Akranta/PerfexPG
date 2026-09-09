package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class StdTlStdworksheetdtlSql {

	public static final String TBL_STD_TL_STDWORKSHEETDTL = "STD_TL_STDWORKSHEETDTL";  

	TableFieldType [] stwdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, stws_keyid, majorsteps, typeofmanpower, mantime, processtime
		, waittime, traveltime, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, createdby, active, createdon, modifiedon
	}

	public TableFieldType[] getStwdDbFields() {
		return stwdDbFields;
	}

	public StdTlStdworksheetdtlSql()
	{
		stwdDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			stwdDbFields[ i ] = new TableFieldType();
		}
		stwdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "STWD_KEYID";
		stwdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.stws_keyid.ordinal() ].fieldName = "STWD_STWS_KEYID";
		stwdDbFields[ tableFldConstants.stws_keyid.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.majorsteps.ordinal() ].fieldName = "STWD_MAJORSTEPS";
		stwdDbFields[ tableFldConstants.majorsteps.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.typeofmanpower.ordinal() ].fieldName = "STWD_TYPEOFMANPOWER";
		stwdDbFields[ tableFldConstants.typeofmanpower.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.mantime.ordinal() ].fieldName = "STWD_MANTIME";
		stwdDbFields[ tableFldConstants.mantime.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.processtime.ordinal() ].fieldName = "STWD_PROCESSTIME";
		stwdDbFields[ tableFldConstants.processtime.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.waittime.ordinal() ].fieldName = "STWD_WAITTIME";
		stwdDbFields[ tableFldConstants.waittime.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.traveltime.ordinal() ].fieldName = "STWD_TRAVELTIME";
		stwdDbFields[ tableFldConstants.traveltime.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "STWD_TEMPFIELD1";
		stwdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "STWD_TEMPFIELD2";
		stwdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "STWD_TEMPFIELD3";
		stwdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "STWD_TEMPFIELD4";
		stwdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "STWD_TEMPFIELD5";
		stwdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "STWD_CREATEDBY";
		stwdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		stwdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "STWD_ACTIVE";
		stwdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		stwdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "STWD_CREATEDON";
		stwdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		stwdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "STWD_MODIFIEDON";
		stwdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_STD_TL_STDWORKSHEETDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_STD_TL_STDWORKSHEETDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_STD_TL_STDWORKSHEETDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteForMasterSql(TableFieldType [] fieldTypeArr, String masterID)
	{
		String sql = "DELETE from " + TBL_STD_TL_STDWORKSHEETDTL ;
		sql += " where " + fieldTypeArr[ tableFldConstants.stws_keyid.ordinal() ].fieldName  +
			  " = '" +  masterID + "'";
		return sql;
	}

	public String DeleteStdRow(String keyid) {
		{
			// TODO Auto-generated method stub
			String sql=" DELETE FROM " + TBL_STD_TL_STDWORKSHEETDTL + " WHERE STWD_KEYID ='"+keyid+"'";
			CommonMessage.debugMsg("Delete Row Sql: "+sql);
			return sql;
		}

	}


}

