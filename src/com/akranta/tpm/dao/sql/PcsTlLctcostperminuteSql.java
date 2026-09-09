package com.akranta.tpm.dao.sql;

public class PcsTlLctcostperminuteSql {

	public static final String TBL_PCS_TL_LCTCOSTPERMINUTE = "PCS_TL_LCTCOSTPERMINUTE";  

	TableFieldType [] lcpmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, elementid, elementtype, fromdate, todate, costperminute
		, latestflag, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getLcpmDbFields() {
		return lcpmDbFields;
	}

	public PcsTlLctcostperminuteSql()
	{
		lcpmDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			lcpmDbFields[ i ] = new TableFieldType();
		}
		lcpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "LCPM_KEYID";
		lcpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		lcpmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "LCPM_ELEMENTID";
		lcpmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		lcpmDbFields[ tableFldConstants.elementtype.ordinal() ].fieldName = "LCPM_ELEMENTTYPE";
		lcpmDbFields[ tableFldConstants.elementtype.ordinal() ].fieldType = 'C';

		lcpmDbFields[ tableFldConstants.fromdate.ordinal() ].fieldName = "LCPM_FROMDATE";
		lcpmDbFields[ tableFldConstants.fromdate.ordinal() ].fieldType = 'D';

		lcpmDbFields[ tableFldConstants.todate.ordinal() ].fieldName = "LCPM_TODATE";
		lcpmDbFields[ tableFldConstants.todate.ordinal() ].fieldType = 'D';

		lcpmDbFields[ tableFldConstants.costperminute.ordinal() ].fieldName = "LCPM_COSTPERMINUTE";
		lcpmDbFields[ tableFldConstants.costperminute.ordinal() ].fieldType = 'N';

		lcpmDbFields[ tableFldConstants.latestflag.ordinal() ].fieldName = "LCPM_LATESTFLAG";
		lcpmDbFields[ tableFldConstants.latestflag.ordinal() ].fieldType = 'C';

		lcpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "LCPM_TEMPFIELD1";
		lcpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		lcpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "LCPM_TEMPFIELD2";
		lcpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		lcpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "LCPM_TEMPFIELD3";
		lcpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		lcpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "LCPM_TEMPFIELD4";
		lcpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		lcpmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "LCPM_TEMPFIELD5";
		lcpmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		lcpmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "LCPM_ACTIVE";
		lcpmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		lcpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "LCPM_CREATEDBY";
		lcpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		lcpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "LCPM_CREATEDON";
		lcpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		lcpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "LCPM_MODIFIEDON";
		lcpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_LCTCOSTPERMINUTE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_LCTCOSTPERMINUTE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_LCTCOSTPERMINUTE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getInsert()
	{
		return "INSERT INTO "+TBL_PCS_TL_LCTCOSTPERMINUTE+"(LCPM_KEYID,LCPM_ELEMENTID,LCPM_ELEMENTTYPE,LCPM_FROMDATE ,LCPM_TODATE,LCPM_COSTPERMINUTE,LCPM_LATESTFLAG,LCPM_TEMPFIELD1,"+
 "LCPM_TEMPFIELD2,LCPM_TEMPFIELD3,LCPM_TEMPFIELD4,LCPM_TEMPFIELD5,LCPM_ACTIVE ,LCPM_CREATEDBY ,LCPM_CREATEDON,LCPM_MODIFIEDON) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	}
	public static String getUpdateExistDateGreater()
	{
		return "UPDATE "+TBL_PCS_TL_LCTCOSTPERMINUTE+" SET LCPM_COSTPERMINUTE=?,LCPM_FROMDATE=?,LCPM_TODATE=?,LCPM_LATESTFLAG='N' WHERE LCPM_ELEMENTID=?  AND LCPM_KEYID=?";
	}
	public static String getUpdateExistDateEqual()
	{
		return "UPDATE "+TBL_PCS_TL_LCTCOSTPERMINUTE +" SET LCPM_COSTPERMINUTE=? WHERE LCPM_ELEMENTID=?  AND LCPM_KEYID=? ";
	}
	public static String getDelete()
	{
		return "DELETE FROM "+TBL_PCS_TL_LCTCOSTPERMINUTE +" WHERE LCPM_KEYID=? ";
	}
	
}

