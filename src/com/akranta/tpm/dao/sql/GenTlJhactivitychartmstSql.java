package com.akranta.tpm.dao.sql;

public class GenTlJhactivitychartmstSql {

	public static final String TBL_GEN_TL_JHACTIVITYCHARTMST = "GEN_TL_JHACTIVITYCHARTMST";  

	TableFieldType [] achmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, activity, frequency, effectivedate, inactivedate, flid
		, roleid, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getAchmDbFields() {
		return achmDbFields;
	}

	public GenTlJhactivitychartmstSql()
	{
		achmDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			achmDbFields[ i ] = new TableFieldType();
		}
		achmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ACHM_KEYID";
		achmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		achmDbFields[ tableFldConstants.activity.ordinal() ].fieldName = "ACHM_ACTIVITY";
		achmDbFields[ tableFldConstants.activity.ordinal() ].fieldType = 'V';

		achmDbFields[ tableFldConstants.frequency.ordinal() ].fieldName = "ACHM_FREQUENCY";
		achmDbFields[ tableFldConstants.frequency.ordinal() ].fieldType = 'V';

		achmDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldName = "ACHM_EFFECTIVEDATE";
		achmDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldType = 'D';

		achmDbFields[ tableFldConstants.inactivedate.ordinal() ].fieldName = "ACHM_INACTIVEDATE";
		achmDbFields[ tableFldConstants.inactivedate.ordinal() ].fieldType = 'D';

		achmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "ACHM_FLID";
		achmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		achmDbFields[ tableFldConstants.roleid.ordinal() ].fieldName = "ACHM_ROLEID";
		achmDbFields[ tableFldConstants.roleid.ordinal() ].fieldType = 'V';

		achmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ACHM_TEMPFIELD1";
		achmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		achmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ACHM_TEMPFIELD2";
		achmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		achmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ACHM_TEMPFIELD3";
		achmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		achmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ACHM_TEMPFIELD4";
		achmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		achmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ACHM_TEMPFIELD5";
		achmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		achmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ACHM_ACTIVE";
		achmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		achmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ACHM_CREATEDBY";
		achmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		achmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ACHM_CREATEDON";
		achmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		achmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ACHM_MODIFIEDON";
		achmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_JHACTIVITYCHARTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_JHACTIVITYCHARTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_JHACTIVITYCHARTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String selectmst() {
		String sql = "SELECT * from " + TBL_GEN_TL_JHACTIVITYCHARTMST +" WHERE ACHM_KEYID=?";
		return sql;
	}

}

