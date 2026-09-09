package com.akranta.tpm.dao.sql;

public class PcsTlEnergyconsumptionmstSql {

	public static final String TBL_PCS_TL_ENERGYCONSUMPTIONMST = "PCS_TL_ENERGYCONSUMPTIONMST";  

	TableFieldType [] encmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, date, totalmaterialcon, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, tempfield6, tempfield7, tempfield8
		, tempfield9, tempfield10, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getEncmDbFields() {
		return encmDbFields;
	}

	public PcsTlEnergyconsumptionmstSql()
	{
		encmDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			encmDbFields[ i ] = new TableFieldType();
		}
		encmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ENCM_KEYID";
		encmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		encmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "ENCM_DATE";
		encmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		encmDbFields[ tableFldConstants.totalmaterialcon.ordinal() ].fieldName = "ENCM_TOTALMATERIALCON";
		encmDbFields[ tableFldConstants.totalmaterialcon.ordinal() ].fieldType = 'N';

		encmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ENCM_TEMPFIELD1";
		encmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		encmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ENCM_TEMPFIELD2";
		encmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		encmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ENCM_TEMPFIELD3";
		encmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		encmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ENCM_TEMPFIELD4";
		encmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		encmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ENCM_TEMPFIELD5";
		encmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		encmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "ENCM_TEMPFIELD6";
		encmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		encmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "ENCM_TEMPFIELD7";
		encmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'C';

		encmDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "ENCM_TEMPFIELD8";
		encmDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'C';

		encmDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "ENCM_TEMPFIELD9";
		encmDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'C';

		encmDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "ENCM_TEMPFIELD10";
		encmDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'C';

		encmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ENCM_ACTIVE";
		encmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		encmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ENCM_CREATEDBY";
		encmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		encmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ENCM_CREATEDON";
		encmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		encmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ENCM_MODIFIEDON";
		encmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_ENERGYCONSUMPTIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_ENERGYCONSUMPTIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_ENERGYCONSUMPTIONMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String checkDate(String date)
	{
		String sql = "SELECT ENCM_KEYID FROM "+ TBL_PCS_TL_ENERGYCONSUMPTIONMST + " WHERE ENCM_DATE='"+date+"'";
		return sql;
	}

}

