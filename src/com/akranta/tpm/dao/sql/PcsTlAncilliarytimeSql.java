package com.akranta.tpm.dao.sql;

public class PcsTlAncilliarytimeSql {

	public static final String TBL_PCS_TL_ANCILLIARYTIME = "PCS_TL_ANCILLIARYTIME";  

	TableFieldType [] ptatDbFields = null;

	public enum   tableFldConstants
	{
		keyid, pldeatilsid, lossid, womskeyid, occureddate, productionstartdate
		, losstime, status, remarks, machineid, temp2, temp3, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getPtatDbFields() {
		return ptatDbFields;
	}

	public PcsTlAncilliarytimeSql()
	{
		ptatDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			ptatDbFields[ i ] = new TableFieldType();
		}
		ptatDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PTAT_KEYID";
		ptatDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ptatDbFields[ tableFldConstants.pldeatilsid.ordinal() ].fieldName = "PTAT_PLDEATILSID";
		ptatDbFields[ tableFldConstants.pldeatilsid.ordinal() ].fieldType = 'V';

		ptatDbFields[ tableFldConstants.lossid.ordinal() ].fieldName = "PTAT_LOSSID";
		ptatDbFields[ tableFldConstants.lossid.ordinal() ].fieldType = 'V';

		ptatDbFields[ tableFldConstants.womskeyid.ordinal() ].fieldName = "PTAT_WOMSKEYID";
		ptatDbFields[ tableFldConstants.womskeyid.ordinal() ].fieldType = 'V';

		ptatDbFields[ tableFldConstants.occureddate.ordinal() ].fieldName = "PTAT_OCCUREDDATE";
		ptatDbFields[ tableFldConstants.occureddate.ordinal() ].fieldType = 'D';

		ptatDbFields[ tableFldConstants.productionstartdate.ordinal() ].fieldName = "PTAT_PRODUCTIONSTARTDATE";
		ptatDbFields[ tableFldConstants.productionstartdate.ordinal() ].fieldType = 'D';

		ptatDbFields[ tableFldConstants.losstime.ordinal() ].fieldName = "PTAT_LOSSTIME";
		ptatDbFields[ tableFldConstants.losstime.ordinal() ].fieldType = 'N';

		ptatDbFields[ tableFldConstants.status.ordinal() ].fieldName = "PTAT_STATUS";
		ptatDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		ptatDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "PTAT_REMARKS";
		ptatDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		ptatDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "PTAT_MACHINEID";
		ptatDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		ptatDbFields[ tableFldConstants.temp2.ordinal() ].fieldName = "PTAT_TEMP2";
		ptatDbFields[ tableFldConstants.temp2.ordinal() ].fieldType = 'V';

		ptatDbFields[ tableFldConstants.temp3.ordinal() ].fieldName = "PTAT_TEMP3";
		ptatDbFields[ tableFldConstants.temp3.ordinal() ].fieldType = 'V';

		ptatDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PTAT_ACTIVE";
		ptatDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ptatDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PTAT_CREATEDBY";
		ptatDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ptatDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PTAT_CREATEDON";
		ptatDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ptatDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PTAT_MODIFIEDON";
		ptatDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_ANCILLIARYTIME, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_ANCILLIARYTIME, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_ANCILLIARYTIME ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getDeleteAncilliarySql(String pldetailsId,String lossId)
	{
		String sql = "DELETE from " + TBL_PCS_TL_ANCILLIARYTIME ;
			   sql += " where PTAT_PLDEATILSID = '" +  pldetailsId + "'";
			   sql += " and PTAT_LOSSID = '" +  lossId + "'";
		return sql;
	}

}

