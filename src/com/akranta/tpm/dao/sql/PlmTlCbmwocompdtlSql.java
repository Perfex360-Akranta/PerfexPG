package com.akranta.tpm.dao.sql;

public class PlmTlCbmwocompdtlSql {

	public static final String TBL_PLM_TL_CBMWOCOMPDTL = "PLM_TL_CBMWOCOMPDTL";  

	TableFieldType [] cmcdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, pmstandardid, pmcalendarid, wofeedbackid, pmentrytype
		, pmjobtype, activitydate, minimumreading, maximumreading, adjustedreading
		, currentreading, nextduedate, middlemax, tempfield1, tempfield2
		, tempfield3, tempfield4, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getCmcdDbFields() {
		return cmcdDbFields;
	}

	public PlmTlCbmwocompdtlSql()
	{
		cmcdDbFields = new TableFieldType[ 21 ];
		for(int i = 0;i < 21; i++)
		{	
			cmcdDbFields[ i ] = new TableFieldType();
		}
		cmcdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CMCD_KEYID";
		cmcdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		cmcdDbFields[ tableFldConstants.pmstandardid.ordinal() ].fieldName = "CMCD_PMSTANDARDID";
		cmcdDbFields[ tableFldConstants.pmstandardid.ordinal() ].fieldType = 'V';

		cmcdDbFields[ tableFldConstants.pmcalendarid.ordinal() ].fieldName = "CMCD_PMCALENDARID";
		cmcdDbFields[ tableFldConstants.pmcalendarid.ordinal() ].fieldType = 'V';

		cmcdDbFields[ tableFldConstants.wofeedbackid.ordinal() ].fieldName = "CMCD_WOFEEDBACKID";
		cmcdDbFields[ tableFldConstants.wofeedbackid.ordinal() ].fieldType = 'V';

		cmcdDbFields[ tableFldConstants.pmentrytype.ordinal() ].fieldName = "CMCD_PMENTRYTYPE";
		cmcdDbFields[ tableFldConstants.pmentrytype.ordinal() ].fieldType = 'C';

		cmcdDbFields[ tableFldConstants.pmjobtype.ordinal() ].fieldName = "CMCD_PMJOBTYPE";
		cmcdDbFields[ tableFldConstants.pmjobtype.ordinal() ].fieldType = 'C';

		cmcdDbFields[ tableFldConstants.activitydate.ordinal() ].fieldName = "CMCD_ACTIVITYDATE";
		cmcdDbFields[ tableFldConstants.activitydate.ordinal() ].fieldType = 'D';

		cmcdDbFields[ tableFldConstants.minimumreading.ordinal() ].fieldName = "CMCD_MINIMUMREADING";
		cmcdDbFields[ tableFldConstants.minimumreading.ordinal() ].fieldType = 'N';

		cmcdDbFields[ tableFldConstants.maximumreading.ordinal() ].fieldName = "CMCD_MAXIMUMREADING";
		cmcdDbFields[ tableFldConstants.maximumreading.ordinal() ].fieldType = 'N';

		cmcdDbFields[ tableFldConstants.adjustedreading.ordinal() ].fieldName = "CMCD_ADJUSTEDREADING";
		cmcdDbFields[ tableFldConstants.adjustedreading.ordinal() ].fieldType = 'N';

		cmcdDbFields[ tableFldConstants.currentreading.ordinal() ].fieldName = "CMCD_CURRENTREADING";
		cmcdDbFields[ tableFldConstants.currentreading.ordinal() ].fieldType = 'N';

		cmcdDbFields[ tableFldConstants.nextduedate.ordinal() ].fieldName = "CMCD_NEXTDUEDATE";
		cmcdDbFields[ tableFldConstants.nextduedate.ordinal() ].fieldType = 'D';

		cmcdDbFields[ tableFldConstants.middlemax.ordinal() ].fieldName = "CMCD_MIDDLEMAX";
		cmcdDbFields[ tableFldConstants.middlemax.ordinal() ].fieldType = 'V';

		cmcdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CMCD_TEMPFIELD1";
		cmcdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		cmcdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CMCD_TEMPFIELD2";
		cmcdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		cmcdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CMCD_TEMPFIELD3";
		cmcdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		cmcdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CMCD_TEMPFIELD4";
		cmcdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		cmcdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CMCD_ACTIVE";
		cmcdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		cmcdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CMCD_CREATEDBY";
		cmcdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		cmcdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CMCD_CREATEDON";
		cmcdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		cmcdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CMCD_MODIFIEDON";
		cmcdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_CBMWOCOMPDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_CBMWOCOMPDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_CBMWOCOMPDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

