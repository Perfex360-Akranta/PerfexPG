package com.akranta.tpm.dao.sql;

public class BAL_PlmTlCbmstdcadtlSql {

	public static final String TBL_PLM_TL_CBMSTDCADTL = "PLM_TL_CBMSTDCADTL";  

	TableFieldType [] cmdtDbFields = null;

	public enum   tableFldConstants
	{
		keyid, inspectionid, zoneid, zonecolor, desirablereading, upperlimit
		, lowerlimit, correctiveaction, cbmcondition, pmstandardid, uomid
		, measuringmethod, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getCmdtDbFields() {
		return cmdtDbFields;
	}

	public BAL_PlmTlCbmstdcadtlSql()
	{
		cmdtDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			cmdtDbFields[ i ] = new TableFieldType();
		}
		cmdtDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CMDT_KEYID";
		cmdtDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		cmdtDbFields[ tableFldConstants.inspectionid.ordinal() ].fieldName = "CMDT_INSPECTIONID";
		cmdtDbFields[ tableFldConstants.inspectionid.ordinal() ].fieldType = 'V';

		cmdtDbFields[ tableFldConstants.zoneid.ordinal() ].fieldName = "CMDT_ZONEID";
		cmdtDbFields[ tableFldConstants.zoneid.ordinal() ].fieldType = 'V';

		cmdtDbFields[ tableFldConstants.zonecolor.ordinal() ].fieldName = "CMDT_ZONECOLOR";
		cmdtDbFields[ tableFldConstants.zonecolor.ordinal() ].fieldType = 'V';

		cmdtDbFields[ tableFldConstants.desirablereading.ordinal() ].fieldName = "CMDT_DESIRABLEREADING";
		cmdtDbFields[ tableFldConstants.desirablereading.ordinal() ].fieldType = 'N';

		cmdtDbFields[ tableFldConstants.upperlimit.ordinal() ].fieldName = "CMDT_UPPERLIMIT";
		cmdtDbFields[ tableFldConstants.upperlimit.ordinal() ].fieldType = 'N';

		cmdtDbFields[ tableFldConstants.lowerlimit.ordinal() ].fieldName = "CMDT_LOWERLIMIT";
		cmdtDbFields[ tableFldConstants.lowerlimit.ordinal() ].fieldType = 'N';

		cmdtDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldName = "CMDT_CORRECTIVEACTION";
		cmdtDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldType = 'V';

		cmdtDbFields[ tableFldConstants.cbmcondition.ordinal() ].fieldName = "CMDT_CBMCONDITION";
		cmdtDbFields[ tableFldConstants.cbmcondition.ordinal() ].fieldType = 'C';

		cmdtDbFields[ tableFldConstants.pmstandardid.ordinal() ].fieldName = "CMDT_PMSTANDARDID";
		cmdtDbFields[ tableFldConstants.pmstandardid.ordinal() ].fieldType = 'V';

		cmdtDbFields[ tableFldConstants.uomid.ordinal() ].fieldName = "CMDT_UOMID";
		cmdtDbFields[ tableFldConstants.uomid.ordinal() ].fieldType = 'V';

		cmdtDbFields[ tableFldConstants.measuringmethod.ordinal() ].fieldName = "CMDT_MEASURINGMETHOD";
		cmdtDbFields[ tableFldConstants.measuringmethod.ordinal() ].fieldType = 'V';

		cmdtDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CMDT_ACTIVE";
		cmdtDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		cmdtDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CMDT_CREATEDBY";
		cmdtDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		cmdtDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CMDT_CREATEDON";
		cmdtDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		cmdtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CMDT_MODIFIEDON";
		cmdtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_CBMSTDCADTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_CBMSTDCADTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_CBMSTDCADTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getCMBListSql(String pmsdId) {
		// TODO Auto-generated method stub
		return "select * from " +TBL_PLM_TL_CBMSTDCADTL+" where CMDT_PMSTANDARDID = ?";
	}

	
}

