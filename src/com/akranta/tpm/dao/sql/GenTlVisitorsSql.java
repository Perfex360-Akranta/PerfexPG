package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlVisitorsSql {

	public static final String TBL_GEN_TL_VISITORS = "GEN_TL_VISITORS";  

	TableFieldType [] visiDbFields = null;

	public enum   tableFldConstants
	{
		keyid, moms_keyid, visitorname, purpose, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getVisiDbFields() {
		return visiDbFields;
	}

	public GenTlVisitorsSql()
	{
		visiDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			visiDbFields[ i ] = new TableFieldType();
		}
		visiDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "VISI_KEYID";
		visiDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		visiDbFields[ tableFldConstants.moms_keyid.ordinal() ].fieldName = "VISI_MOMS_KEYID";
		visiDbFields[ tableFldConstants.moms_keyid.ordinal() ].fieldType = 'V';

		visiDbFields[ tableFldConstants.visitorname.ordinal() ].fieldName = "VISI_VISITORNAME";
		visiDbFields[ tableFldConstants.visitorname.ordinal() ].fieldType = 'V';

		visiDbFields[ tableFldConstants.purpose.ordinal() ].fieldName = "VISI_PURPOSE";
		visiDbFields[ tableFldConstants.purpose.ordinal() ].fieldType = 'V';

		visiDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "VISI_TEMPFIELD1";
		visiDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		visiDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "VISI_TEMPFIELD2";
		visiDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		visiDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "VISI_TEMPFIELD3";
		visiDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		visiDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "VISI_TEMPFIELD4";
		visiDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		visiDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "VISI_TEMPFIELD5";
		visiDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		visiDbFields[ tableFldConstants.active.ordinal() ].fieldName = "VISI_ACTIVE";
		visiDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		visiDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "VISI_CREATEDBY";
		visiDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		visiDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "VISI_CREATEDON";
		visiDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		visiDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "VISI_MODIFIEDON";
		visiDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_VISITORS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_VISITORS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_VISITORS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String DeleteATTRow(String keyid) 
	{
        String sql=" DELETE FROM " +TBL_GEN_TL_VISITORS + " WHERE VISI_KEYID ='"+keyid+"'";
		
		CommonMessage.debugMsg("sql:: "+sql);
		return sql;
	
	}

	public static String getMomsFrmDataSql1() {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from "+TBL_GEN_TL_VISITORS +" where VISI_KEYID= ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}

}

