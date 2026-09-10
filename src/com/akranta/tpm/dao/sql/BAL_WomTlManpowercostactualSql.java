package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_WomTlManpowercostplanSql.tableFldConstants;

public class BAL_WomTlManpowercostactualSql {

	public static final String TBL_BAL_WOM_TL_MANPOWERCOSTACTUAL = "BAL_WOM_TL_MANPOWERCOSTACTUAL";  

	TableFieldType [] mpcsDbFields = null;

	public enum   tableFldConstants
	{
		maintwoid, doctype, manpowerid, normalwt, holidaywt, otherwt
		, normalrate, holidayrate, otherrate, totalvalue, noofhelpers
		, skillflag, skillid, date, activity, remarks, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMpcsDbFields() {
		return mpcsDbFields;
	}

	public BAL_WomTlManpowercostactualSql()
	{
		mpcsDbFields = new TableFieldType[ 24 ];
		for(int i = 0;i < 24; i++)
		{	
			mpcsDbFields[ i ] = new TableFieldType();
		}
		mpcsDbFields[ tableFldConstants.maintwoid.ordinal() ].fieldName = "MPCS_MAINTWOID";
		mpcsDbFields[ tableFldConstants.maintwoid.ordinal() ].fieldType = 'V';

		mpcsDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "MPCS_DOCTYPE";
		mpcsDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		mpcsDbFields[ tableFldConstants.manpowerid.ordinal() ].fieldName = "MPCS_MANPOWERID";
		mpcsDbFields[ tableFldConstants.manpowerid.ordinal() ].fieldType = 'V';

		mpcsDbFields[ tableFldConstants.normalwt.ordinal() ].fieldName = "MPCS_NORMALWT";
		mpcsDbFields[ tableFldConstants.normalwt.ordinal() ].fieldType = 'N';

		mpcsDbFields[ tableFldConstants.holidaywt.ordinal() ].fieldName = "MPCS_HOLIDAYWT";
		mpcsDbFields[ tableFldConstants.holidaywt.ordinal() ].fieldType = 'N';

		mpcsDbFields[ tableFldConstants.otherwt.ordinal() ].fieldName = "MPCS_OTHERWT";
		mpcsDbFields[ tableFldConstants.otherwt.ordinal() ].fieldType = 'N';

		mpcsDbFields[ tableFldConstants.normalrate.ordinal() ].fieldName = "MPCS_NORMALRATE";
		mpcsDbFields[ tableFldConstants.normalrate.ordinal() ].fieldType = 'N';

		mpcsDbFields[ tableFldConstants.holidayrate.ordinal() ].fieldName = "MPCS_HOLIDAYRATE";
		mpcsDbFields[ tableFldConstants.holidayrate.ordinal() ].fieldType = 'N';

		mpcsDbFields[ tableFldConstants.otherrate.ordinal() ].fieldName = "MPCS_OTHERRATE";
		mpcsDbFields[ tableFldConstants.otherrate.ordinal() ].fieldType = 'N';

		mpcsDbFields[ tableFldConstants.totalvalue.ordinal() ].fieldName = "MPCS_TOTALVALUE";
		mpcsDbFields[ tableFldConstants.totalvalue.ordinal() ].fieldType = 'N';

		mpcsDbFields[ tableFldConstants.noofhelpers.ordinal() ].fieldName = "MPCS_NOOFHELPERS";
		mpcsDbFields[ tableFldConstants.noofhelpers.ordinal() ].fieldType = 'N';

		mpcsDbFields[ tableFldConstants.skillflag.ordinal() ].fieldName = "MPCS_SKILLFLAG";
		mpcsDbFields[ tableFldConstants.skillflag.ordinal() ].fieldType = 'C';

		mpcsDbFields[ tableFldConstants.skillid.ordinal() ].fieldName = "MPCS_SKILLID";
		mpcsDbFields[ tableFldConstants.skillid.ordinal() ].fieldType = 'V';

		mpcsDbFields[ tableFldConstants.date.ordinal() ].fieldName = "MPCS_DATE";
		mpcsDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		mpcsDbFields[ tableFldConstants.activity.ordinal() ].fieldName = "MPCS_ACTIVITY";
		mpcsDbFields[ tableFldConstants.activity.ordinal() ].fieldType = 'V';

		mpcsDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MPCS_REMARKS";
		mpcsDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		mpcsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MPCS_TEMPFIELD1";
		mpcsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mpcsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MPCS_TEMPFIELD2";
		mpcsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		mpcsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MPCS_TEMPFIELD3";
		mpcsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		mpcsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MPCS_TEMPFIELD4";
		mpcsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		mpcsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MPCS_TEMPFIELD5";
		mpcsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		mpcsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MPCS_CREATEDBY";
		mpcsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mpcsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MPCS_CREATEDON";
		mpcsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mpcsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MPCS_MODIFIEDON";
		mpcsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BAL_WOM_TL_MANPOWERCOSTACTUAL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BAL_WOM_TL_MANPOWERCOSTACTUAL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.maintwoid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.maintwoid.ordinal() ] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.manpowerid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.manpowerid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.skillid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.skillid.ordinal()] + "'";		
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BAL_WOM_TL_MANPOWERCOSTACTUAL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.maintwoid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.maintwoid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.manpowerid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.manpowerid.ordinal()] + "'";
		sql += " and " + fieldTypeArr[tableFldConstants.skillid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.skillid.ordinal()] + "'";		
		return sql;
	}

}

