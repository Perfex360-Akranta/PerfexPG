package com.akranta.tpm.dao.sql;

public class SheTlRiskassessmentmstSql {

	public static final String TBL_SHE_TL_RISKASSESSMENTMST = "SHE_TL_RISKASSESSMENTMST";  

	TableFieldType [] rasmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, area, title, date, preparedby, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getRasmDbFields() {
		return rasmDbFields;
	}

	public SheTlRiskassessmentmstSql()
	{
		rasmDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			rasmDbFields[ i ] = new TableFieldType();
		}
		rasmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "RASM_KEYID";
		rasmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		rasmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "RASM_FLID";
		rasmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		rasmDbFields[ tableFldConstants.area.ordinal() ].fieldName = "RASM_AREA";
		rasmDbFields[ tableFldConstants.area.ordinal() ].fieldType = 'V';

		rasmDbFields[ tableFldConstants.title.ordinal() ].fieldName = "RASM_TITLE";
		rasmDbFields[ tableFldConstants.title.ordinal() ].fieldType = 'V';

		rasmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "RASM_DATE";
		rasmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		rasmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "RASM_PREPAREDBY";
		rasmDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		rasmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "RASM_TEMPFIELD1";
		rasmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		rasmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "RASM_TEMPFIELD2";
		rasmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		rasmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "RASM_TEMPFIELD3";
		rasmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		rasmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "RASM_TEMPFIELD4";
		rasmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		rasmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "RASM_TEMPFIELD5";
		rasmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		rasmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "RASM_ACTIVE";
		rasmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		rasmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "RASM_CREATEDBY";
		rasmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		rasmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "RASM_CREATEDON";
		rasmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		rasmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "RASM_MODIFIEDON";
		rasmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_RISKASSESSMENTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SHE_TL_RISKASSESSMENTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_RISKASSESSMENTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getSelectSql()
	{
		String sql = "Select * from " + TBL_SHE_TL_RISKASSESSMENTMST +" where RASM_KEYID=?";
		return sql;
	}

	public String getRiskLevelSql(String riskVal) {
		// TODO Auto-generated method stub
		String sql = "Select RILM_KEYID from SHE_TL_RISKLEVELMST where "+riskVal+" between RILM_MINPOINT and RILM_MAXPPOINT";
		return sql;
	}

	public String getDeleteDtlsSql(TableFieldType [] fieldTypeArr, Object [] dataArray){
		// TODO Auto-generated method stub
		String sql = "DELETE from SHE_TL_RISKASSESSMENTDTL ";
	
		sql += " where RASD_RASM_KEYID" +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getProbablityValSql(String prob) {
		// TODO Auto-generated method stub
		String sql = "SELECT PRBM_CODE FROM SHE_TL_PROBABLITYMST where PRBM_KEYID='"+prob+"'";
		return sql;
	}
	public String getSeviorityValSql(String sev) {
		// TODO Auto-generated method stub
		String sql = "SELECT SIVM_CODE FROM SHE_TL_SEVIORITYMST where SIVM_KEYID='"+sev+"'";
		return sql;
	}


}

