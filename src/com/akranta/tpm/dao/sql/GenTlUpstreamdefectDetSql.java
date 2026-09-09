package com.akranta.tpm.dao.sql;

public class GenTlUpstreamdefectDetSql {

	public static final String TBL_GEN_TL_UPSTREAMDEFECT_DET = "GEN_TL_UPSTREAMDEFECT_DET";  

	TableFieldType [] upsdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, upsm_keyid, informto, rawmaterial, defect, correctionaction
		, preventiveaction, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getUpsdDbFields() {
		return upsdDbFields;
	}

	public GenTlUpstreamdefectDetSql()
	{
		upsdDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			upsdDbFields[ i ] = new TableFieldType();
		}
		upsdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "UPSD_KEYID";
		upsdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.upsm_keyid.ordinal() ].fieldName = "UPSD_UPSM_KEYID";
		upsdDbFields[ tableFldConstants.upsm_keyid.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.informto.ordinal() ].fieldName = "UPSD_INFORMTO";
		upsdDbFields[ tableFldConstants.informto.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.rawmaterial.ordinal() ].fieldName = "UPSD_RAWMATERIAL";
		upsdDbFields[ tableFldConstants.rawmaterial.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.defect.ordinal() ].fieldName = "UPSD_DEFECT";
		upsdDbFields[ tableFldConstants.defect.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.correctionaction.ordinal() ].fieldName = "UPSD_CORRECTIONACTION";
		upsdDbFields[ tableFldConstants.correctionaction.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.preventiveaction.ordinal() ].fieldName = "UPSD_PREVENTIVEACTION";
		upsdDbFields[ tableFldConstants.preventiveaction.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "UPSD_TEMPFIELD1";
		upsdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		upsdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "UPSD_TEMPFIELD2";
		upsdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		upsdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "UPSD_TEMPFIELD3";
		upsdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		upsdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "UPSD_TEMPFIELD4";
		upsdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		upsdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "UPSD_TEMPFIELD5";
		upsdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		upsdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "UPSD_ACTIVE";
		upsdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		upsdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "UPSD_CREATEDBY";
		upsdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "UPSD_CREATEDON";
		upsdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		upsdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "UPSD_MODIFIEDON";
		upsdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_UPSTREAMDEFECT_DET, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_UPSTREAMDEFECT_DET, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_UPSTREAMDEFECT_DET ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

