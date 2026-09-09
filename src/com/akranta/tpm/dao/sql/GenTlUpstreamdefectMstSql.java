package com.akranta.tpm.dao.sql;

public class GenTlUpstreamdefectMstSql {

	public static final String TBL_GEN_TL_UPSTREAMDEFECT_MST = "GEN_TL_UPSTREAMDEFECT_MST";  

	TableFieldType [] upsmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, area, title, remakrs, inspectionlotno
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getUpsmDbFields() {
		return upsmDbFields;
	}

	public GenTlUpstreamdefectMstSql()
	{
		upsmDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			upsmDbFields[ i ] = new TableFieldType();
		}
		upsmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "UPSM_KEYID";
		upsmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		upsmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "UPSM_FLID";
		upsmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		upsmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "UPSM_ELEMENTID";
		upsmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		upsmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "UPSM_DATE";
		upsmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		upsmDbFields[ tableFldConstants.area.ordinal() ].fieldName = "UPSM_AREA";
		upsmDbFields[ tableFldConstants.area.ordinal() ].fieldType = 'V';

		upsmDbFields[ tableFldConstants.title.ordinal() ].fieldName = "UPSM_TITLE";
		upsmDbFields[ tableFldConstants.title.ordinal() ].fieldType = 'V';

		upsmDbFields[ tableFldConstants.remakrs.ordinal() ].fieldName = "UPSM_REMAKRS";
		upsmDbFields[ tableFldConstants.remakrs.ordinal() ].fieldType = 'V';

		upsmDbFields[ tableFldConstants.inspectionlotno.ordinal() ].fieldName = "UPSM_INSPECTIONLOTNO";
		upsmDbFields[ tableFldConstants.inspectionlotno.ordinal() ].fieldType = 'V';

		upsmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "UPSM_TEMPFIELD1";
		upsmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		upsmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "UPSM_TEMPFIELD2";
		upsmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		upsmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "UPSM_TEMPFIELD3";
		upsmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		upsmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "UPSM_TEMPFIELD4";
		upsmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		upsmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "UPSM_TEMPFIELD5";
		upsmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		upsmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "UPSM_ACTIVE";
		upsmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		upsmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "UPSM_CREATEDBY";
		upsmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		upsmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "UPSM_CREATEDON";
		upsmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		upsmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "UPSM_MODIFIEDON";
		upsmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_UPSTREAMDEFECT_MST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_UPSTREAMDEFECT_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_UPSTREAMDEFECT_MST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

