package com.akranta.tpm.dao.sql;

public class KpiTlKpiremarksSql {

	public static final String TBL_KPI_TL_KPIREMARKS = "KPI_TL_KPIREMARKS";  

	TableFieldType [] kprmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, indicatorid, flid, date, remarks, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getKprmDbFields() {
		return kprmDbFields;
	}

	public KpiTlKpiremarksSql()
	{
		kprmDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			kprmDbFields[ i ] = new TableFieldType();
		}
		kprmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KPRM_KEYID";
		kprmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kprmDbFields[ tableFldConstants.indicatorid.ordinal() ].fieldName = "KPRM_INDICATORID";
		kprmDbFields[ tableFldConstants.indicatorid.ordinal() ].fieldType = 'V';

		kprmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "KPRM_FLID";
		kprmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		kprmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "KPRM_DATE";
		kprmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		kprmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "KPRM_REMARKS";
		kprmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		kprmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KPRM_TEMPFIELD1";
		kprmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		kprmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KPRM_TEMPFIELD2";
		kprmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		kprmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KPRM_TEMPFIELD3";
		kprmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		kprmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KPRM_TEMPFIELD4";
		kprmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		kprmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KPRM_TEMPFIELD5";
		kprmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		kprmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KPRM_ACTIVE";
		kprmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kprmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KPRM_CREATEDBY";
		kprmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kprmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KPRM_CREATEDON";
		kprmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kprmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KPRM_MODIFIEDON";
		kprmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KPI_TL_KPIREMARKS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KPI_TL_KPIREMARKS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KPI_TL_KPIREMARKS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getSelectSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "SELECT * from " + TBL_KPI_TL_KPIREMARKS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
}

