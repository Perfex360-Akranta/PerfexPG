package com.akranta.tpm.dao.sql;

public class BAL_WomTlManpowercostplanSql {

	public static final String TBL_WOM_TL_MANPOWERCOSTPLAN = "WOM_TL_MANPOWERCOSTPLAN";  

	TableFieldType [] mpcpDbFields = null;

	public enum   tableFldConstants
	{
		woid, doctype, manpowerid, normalmins, holidaymins, othermins
		, normalcost, holidaycost, othercost, totalvalue, noofhelpers
		, skillflag, skillid, date, activity, remarks, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getMpcpDbFields() {
		return mpcpDbFields;
	}

	public BAL_WomTlManpowercostplanSql()
	{
		mpcpDbFields = new TableFieldType[ 25 ];
		for(int i = 0;i < 25; i++)
		{	
			mpcpDbFields[ i ] = new TableFieldType();
		}
		mpcpDbFields[ tableFldConstants.woid.ordinal() ].fieldName = "MPCP_WOID";
		mpcpDbFields[ tableFldConstants.woid.ordinal() ].fieldType = 'V';

		mpcpDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "MPCP_DOCTYPE";
		mpcpDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		mpcpDbFields[ tableFldConstants.manpowerid.ordinal() ].fieldName = "MPCP_MANPOWERID";
		mpcpDbFields[ tableFldConstants.manpowerid.ordinal() ].fieldType = 'V';

		mpcpDbFields[ tableFldConstants.normalmins.ordinal() ].fieldName = "MPCP_NORMALMINS";
		mpcpDbFields[ tableFldConstants.normalmins.ordinal() ].fieldType = 'N';

		mpcpDbFields[ tableFldConstants.holidaymins.ordinal() ].fieldName = "MPCP_HOLIDAYMINS";
		mpcpDbFields[ tableFldConstants.holidaymins.ordinal() ].fieldType = 'N';

		mpcpDbFields[ tableFldConstants.othermins.ordinal() ].fieldName = "MPCP_OTHERMINS";
		mpcpDbFields[ tableFldConstants.othermins.ordinal() ].fieldType = 'N';

		mpcpDbFields[ tableFldConstants.normalcost.ordinal() ].fieldName = "MPCP_NORMALCOST";
		mpcpDbFields[ tableFldConstants.normalcost.ordinal() ].fieldType = 'N';

		mpcpDbFields[ tableFldConstants.holidaycost.ordinal() ].fieldName = "MPCP_HOLIDAYCOST";
		mpcpDbFields[ tableFldConstants.holidaycost.ordinal() ].fieldType = 'N';

		mpcpDbFields[ tableFldConstants.othercost.ordinal() ].fieldName = "MPCP_OTHERCOST";
		mpcpDbFields[ tableFldConstants.othercost.ordinal() ].fieldType = 'N';

		mpcpDbFields[ tableFldConstants.totalvalue.ordinal() ].fieldName = "MPCP_TOTALVALUE";
		mpcpDbFields[ tableFldConstants.totalvalue.ordinal() ].fieldType = 'N';

		mpcpDbFields[ tableFldConstants.noofhelpers.ordinal() ].fieldName = "MPCP_NOOFHELPERS";
		mpcpDbFields[ tableFldConstants.noofhelpers.ordinal() ].fieldType = 'N';

		mpcpDbFields[ tableFldConstants.skillflag.ordinal() ].fieldName = "MPCP_SKILLFLAG";
		mpcpDbFields[ tableFldConstants.skillflag.ordinal() ].fieldType = 'C';

		mpcpDbFields[ tableFldConstants.skillid.ordinal() ].fieldName = "MPCP_SKILLID";
		mpcpDbFields[ tableFldConstants.skillid.ordinal() ].fieldType = 'V';

		mpcpDbFields[ tableFldConstants.date.ordinal() ].fieldName = "MPCP_DATE";
		mpcpDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		mpcpDbFields[ tableFldConstants.activity.ordinal() ].fieldName = "MPCP_ACTIVITY";
		mpcpDbFields[ tableFldConstants.activity.ordinal() ].fieldType = 'V';

		mpcpDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MPCP_REMARKS";
		mpcpDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		mpcpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MPCP_TEMPFIELD1";
		mpcpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mpcpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MPCP_TEMPFIELD2";
		mpcpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mpcpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MPCP_TEMPFIELD3";
		mpcpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		mpcpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MPCP_TEMPFIELD4";
		mpcpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		mpcpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MPCP_TEMPFIELD5";
		mpcpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		mpcpDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MPCP_ACTIVE";
		mpcpDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mpcpDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MPCP_CREATEDBY";
		mpcpDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mpcpDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MPCP_CREATEDON";
		mpcpDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mpcpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MPCP_MODIFIEDON";
		mpcpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_WOM_TL_MANPOWERCOSTPLAN, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_WOM_TL_MANPOWERCOSTPLAN, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.woid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.woid.ordinal() ] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.manpowerid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.manpowerid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.skillid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.skillid.ordinal()] + "'";
		
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_WOM_TL_MANPOWERCOSTPLAN ;
		sql += " where " + fieldTypeArr[tableFldConstants.woid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.woid.ordinal()] + "'" ;
		sql += " and " + fieldTypeArr[tableFldConstants.manpowerid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.manpowerid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.skillid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.skillid.ordinal()] + "'";

		return sql;
	}

}

