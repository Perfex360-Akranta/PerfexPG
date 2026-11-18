package com.akranta.tpm.dao.sql;

public class BAL_PcsJhlossDetailSql {
	public static final String TBL_PCS_TL_JHLOSSDETAILS = "PCS_TL_JHLOSSDETAILS";

	TableFieldType [] plosDbFields = null;	

	public enum   tableFldConstants
	{
		keyid,  lossid, machineid,lubTime,cleanTime,tightime,inspectTime,tempfiled1,tempField2,tempField3,tempField4,  createdby
		, createdon, modifiedon
	}
	public TableFieldType[] getPlosDbFields() {
		return plosDbFields;
	}
	
	public BAL_PcsJhlossDetailSql()
	{
		plosDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			plosDbFields[ i ] = new TableFieldType();
			
		}
		plosDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PJH_KEYID";
		plosDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.lossid.ordinal() ].fieldName = "PJH_PLOSSID";
		plosDbFields[ tableFldConstants.lossid.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "PJH_MACHINEID";
		plosDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'D';

		plosDbFields[ tableFldConstants.cleanTime.ordinal() ].fieldName = "PJH_CLEANTIME";
		plosDbFields[ tableFldConstants.cleanTime.ordinal() ].fieldType = 'N';

		plosDbFields[ tableFldConstants.tightime.ordinal() ].fieldName = "PJH_TIGHTINGTIME";
		plosDbFields[ tableFldConstants.tightime.ordinal() ].fieldType = 'N';

		plosDbFields[ tableFldConstants.inspectTime.ordinal() ].fieldName = "PJH_INSPECTTIME";
		plosDbFields[ tableFldConstants.inspectTime.ordinal() ].fieldType = 'N';

		plosDbFields[ tableFldConstants.lubTime.ordinal() ].fieldName = "PJH_LUBTIME";
		plosDbFields[ tableFldConstants.lubTime.ordinal() ].fieldType = 'N';

		plosDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldName = "PJH_TEMPFIELD1";
		plosDbFields[ tableFldConstants.tempfiled1.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.tempField2.ordinal() ].fieldName = "PJH_TEMPFIELD2";
		plosDbFields[ tableFldConstants.tempField2.ordinal() ].fieldType = 'V';

		
		plosDbFields[ tableFldConstants.tempField3.ordinal() ].fieldName = "PJH_TEMPFIELD3";
		plosDbFields[ tableFldConstants.tempField3.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.tempField4.ordinal() ].fieldName = "PJH_TEMPFIELD4";
		plosDbFields[ tableFldConstants.tempField4.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PJH_CREATEDBY";
		plosDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		plosDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PJH_CREATEDON";
		plosDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		plosDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PJH_MODIFIEDON";
		plosDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_JHLOSSDETAILS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_JHLOSSDETAILS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_JHLOSSDETAILS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	/*public static String getPlossMinutes(TableFieldType [] fieldTypeArr, Object [] dataArray){
			String sql="SELECT PLOS_LOSSTIME FROM PCS_TL_LOSSCAPTURE";
			sql += "where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName +
			" = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}*/

}

