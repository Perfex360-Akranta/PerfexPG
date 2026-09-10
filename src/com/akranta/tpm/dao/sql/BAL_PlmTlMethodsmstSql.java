package com.akranta.tpm.dao.sql;

public class BAL_PlmTlMethodsmstSql {

	public static final String TBL_PLM_TL_METHODSMST = "PLM_TL_METHODSMST";  

	TableFieldType [] pmmsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, pmkeyid, activity, instructions, duration, standards, workpermitrequired
		, permittype, safetyinstruction, protectiveequipements, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getPmmsDbFields() {
		return pmmsDbFields;
	}

	public BAL_PlmTlMethodsmstSql()
	{
		pmmsDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			pmmsDbFields[ i ] = new TableFieldType();
		}
		pmmsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PMMS_KEYID";
		pmmsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pmmsDbFields[ tableFldConstants.pmkeyid.ordinal() ].fieldName = "PMMS_PMKEYID";
		pmmsDbFields[ tableFldConstants.pmkeyid.ordinal() ].fieldType = 'V';

		pmmsDbFields[ tableFldConstants.activity.ordinal() ].fieldName = "PMMS_ACTIVITY";
		pmmsDbFields[ tableFldConstants.activity.ordinal() ].fieldType = 'V';

		pmmsDbFields[ tableFldConstants.instructions.ordinal() ].fieldName = "PMMS_INSTRUCTIONS";
		pmmsDbFields[ tableFldConstants.instructions.ordinal() ].fieldType = 'V';

		pmmsDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "PMMS_DURATION";
		pmmsDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'N';

		pmmsDbFields[ tableFldConstants.standards.ordinal() ].fieldName = "PMMS_STANDARDS";
		pmmsDbFields[ tableFldConstants.standards.ordinal() ].fieldType = 'V';

		pmmsDbFields[ tableFldConstants.workpermitrequired.ordinal() ].fieldName = "PMMS_WORKPERMITREQUIRED";
		pmmsDbFields[ tableFldConstants.workpermitrequired.ordinal() ].fieldType = 'C';

		pmmsDbFields[ tableFldConstants.permittype.ordinal() ].fieldName = "PMMS_PERMITTYPE";
		pmmsDbFields[ tableFldConstants.permittype.ordinal() ].fieldType = 'V';

		pmmsDbFields[ tableFldConstants.safetyinstruction.ordinal() ].fieldName = "PMMS_SAFETYINSTRUCTION";
		pmmsDbFields[ tableFldConstants.safetyinstruction.ordinal() ].fieldType = 'V';

		pmmsDbFields[ tableFldConstants.protectiveequipements.ordinal() ].fieldName = "PMMS_PROTECTIVEEQUIPEMENTS";
		pmmsDbFields[ tableFldConstants.protectiveequipements.ordinal() ].fieldType = 'V';

		pmmsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PMMS_TEMPFIELD1";
		pmmsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		pmmsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PMMS_TEMPFIELD2";
		pmmsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		pmmsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PMMS_TEMPFIELD3";
		pmmsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		pmmsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PMMS_TEMPFIELD4";
		pmmsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		pmmsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "PMMS_TEMPFIELD5";
		pmmsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		pmmsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PMMS_ACTIVE";
		pmmsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pmmsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PMMS_CREATEDBY";
		pmmsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pmmsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PMMS_CREATEDON";
		pmmsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pmmsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PMMS_MODIFIEDON";
		pmmsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_METHODSMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_METHODSMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_METHODSMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

