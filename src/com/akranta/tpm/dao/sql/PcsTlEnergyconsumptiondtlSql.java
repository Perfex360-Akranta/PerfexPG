package com.akranta.tpm.dao.sql;

public class PcsTlEnergyconsumptiondtlSql {

	public static final String TBL_PCS_TL_ENERGYCONSUMPTIONDTL = "PCS_TL_ENERGYCONSUMPTIONDTL";  

	TableFieldType [] encdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, encm_keyid, machineid, productid, standardwt, exppowercons
		, actpowercons, expproduction, noofpiecesrejected, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getEncdDbFields() {
		return encdDbFields;
	}

	public PcsTlEnergyconsumptiondtlSql()
	{
		encdDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			encdDbFields[ i ] = new TableFieldType();
		}
		encdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ENCD_KEYID";
		encdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		encdDbFields[ tableFldConstants.encm_keyid.ordinal() ].fieldName = "ENCD_ENCM_KEYID";
		encdDbFields[ tableFldConstants.encm_keyid.ordinal() ].fieldType = 'V';

		encdDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "ENCD_MACHINEID";
		encdDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		encdDbFields[ tableFldConstants.productid.ordinal() ].fieldName = "ENCD_PRODUCTID";
		encdDbFields[ tableFldConstants.productid.ordinal() ].fieldType = 'V';

		encdDbFields[ tableFldConstants.standardwt.ordinal() ].fieldName = "ENCD_STANDARDWT";
		encdDbFields[ tableFldConstants.standardwt.ordinal() ].fieldType = 'N';

		encdDbFields[ tableFldConstants.exppowercons.ordinal() ].fieldName = "ENCD_EXPPOWERCONS";
		encdDbFields[ tableFldConstants.exppowercons.ordinal() ].fieldType = 'N';

		encdDbFields[ tableFldConstants.actpowercons.ordinal() ].fieldName = "ENCD_ACTPOWERCONS";
		encdDbFields[ tableFldConstants.actpowercons.ordinal() ].fieldType = 'N';

		encdDbFields[ tableFldConstants.expproduction.ordinal() ].fieldName = "ENCD_EXPPRODUCTION";
		encdDbFields[ tableFldConstants.expproduction.ordinal() ].fieldType = 'N';

		encdDbFields[ tableFldConstants.noofpiecesrejected.ordinal() ].fieldName = "ENCD_NOOFPIECESREJECTED";
		encdDbFields[ tableFldConstants.noofpiecesrejected.ordinal() ].fieldType = 'N';

		encdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ENCD_ACTIVE";
		encdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		encdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ENCD_CREATEDBY";
		encdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		encdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ENCD_CREATEDON";
		encdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		encdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ENCD_MODIFIEDON";
		encdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_ENERGYCONSUMPTIONDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_ENERGYCONSUMPTIONDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_ENERGYCONSUMPTIONDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

