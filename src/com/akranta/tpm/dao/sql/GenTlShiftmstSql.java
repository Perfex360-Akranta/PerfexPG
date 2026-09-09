package com.akranta.tpm.dao.sql;

public class GenTlShiftmstSql {

	public static final String TBL_GEN_TL_SHIFTMST = "GEN_TL_SHIFTMST";  

	TableFieldType [] sftmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, code, duration, breaktime, shiftorder, starttime
		, endtime, description, effectivedate, inactivedate, factoryid
		, sectionid, cellid, elementid,flid,active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getSftmDbFields() {
		return sftmDbFields;
	}

	public GenTlShiftmstSql()
	{
		sftmDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			sftmDbFields[ i ] = new TableFieldType();
		}
		sftmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SFTM_KEYID";
		sftmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		sftmDbFields[ tableFldConstants.name.ordinal() ].fieldName = "SFTM_NAME";
		sftmDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		sftmDbFields[ tableFldConstants.code.ordinal() ].fieldName = "SFTM_CODE";
		sftmDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		sftmDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "SFTM_DURATION";
		sftmDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'D';

		sftmDbFields[ tableFldConstants.breaktime.ordinal() ].fieldName = "SFTM_BREAKTIME";
		sftmDbFields[ tableFldConstants.breaktime.ordinal() ].fieldType = 'N';

		sftmDbFields[ tableFldConstants.shiftorder.ordinal() ].fieldName = "SFTM_SHIFTORDER";
		sftmDbFields[ tableFldConstants.shiftorder.ordinal() ].fieldType = 'N';

		sftmDbFields[ tableFldConstants.starttime.ordinal() ].fieldName = "SFTM_STARTTIME";
		sftmDbFields[ tableFldConstants.starttime.ordinal() ].fieldType = 'D';

		sftmDbFields[ tableFldConstants.endtime.ordinal() ].fieldName = "SFTM_ENDTIME";
		sftmDbFields[ tableFldConstants.endtime.ordinal() ].fieldType = 'D';

		sftmDbFields[ tableFldConstants.description.ordinal() ].fieldName = "SFTM_DESCRIPTION";
		sftmDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		sftmDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldName = "SFTM_EFFECTIVEDATE";
		sftmDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldType = 'D';

		sftmDbFields[ tableFldConstants.inactivedate.ordinal() ].fieldName = "SFTM_INACTIVEDATE";
		sftmDbFields[ tableFldConstants.inactivedate.ordinal() ].fieldType = 'D';

		sftmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "SFTM_FACTORYID";
		sftmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		sftmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "SFTM_SECTIONID";
		sftmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		sftmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "SFTM_CELLID";
		sftmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';
		
		sftmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "SFTM_ELEMENTID";
		sftmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';
		
		sftmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SFTM_FLID";
		sftmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		sftmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SFTM_ACTIVE";
		sftmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		sftmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SFTM_CREATEDBY";
		sftmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		sftmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SFTM_CREATEDON";
		sftmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		sftmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SFTM_MODIFIEDON";
		sftmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_SHIFTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_SHIFTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_SHIFTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getGenTlShiftmstSql() {
		return  " SELECT * from " + TBL_GEN_TL_SHIFTMST + " where SFTM_KEYID = ?  ";
	}

}

