package com.akranta.tpm.dao.sql;

public class SapExternalRepairSql {

	public static final String TBL_SAP_EXTERNAL_REPAIR = "SAP_EXTERNAL_REPAIR";  

	TableFieldType [] extrDbFields = null;

	public enum   tableFldConstants
	{
		extm_keyid, keyid, component_no, requirement_qty, uom, item_category
		, storage_location, mat_rework_indi, partno,lineno,tempfield1,tempfield2, 
		tempfield3, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getExtrDbFields() {
		return extrDbFields;
	}

	public SapExternalRepairSql()
	{
		extrDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			extrDbFields[ i ] = new TableFieldType();
		}
		extrDbFields[ tableFldConstants.extm_keyid.ordinal() ].fieldName = "EXTR_EXTM_KEYID";
		extrDbFields[ tableFldConstants.extm_keyid.ordinal() ].fieldType = 'V';

		extrDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EXTR_KEYID";
		extrDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		extrDbFields[ tableFldConstants.component_no.ordinal() ].fieldName = "EXTR_COMPONENT_NO";
		extrDbFields[ tableFldConstants.component_no.ordinal() ].fieldType = 'V';

		extrDbFields[ tableFldConstants.requirement_qty.ordinal() ].fieldName = "EXTR_REQUIREMENT_QTY";
		extrDbFields[ tableFldConstants.requirement_qty.ordinal() ].fieldType = 'N';

		extrDbFields[ tableFldConstants.uom.ordinal() ].fieldName = "EXTR_UOM";
		extrDbFields[ tableFldConstants.uom.ordinal() ].fieldType = 'V';

		extrDbFields[ tableFldConstants.item_category.ordinal() ].fieldName = "EXTR_ITEM_CATEGORY";
		extrDbFields[ tableFldConstants.item_category.ordinal() ].fieldType = 'V';

		extrDbFields[ tableFldConstants.storage_location.ordinal() ].fieldName = "EXTR_STORAGE_LOCATION";
		extrDbFields[ tableFldConstants.storage_location.ordinal() ].fieldType = 'V';

		extrDbFields[ tableFldConstants.mat_rework_indi.ordinal() ].fieldName = "EXTR_MAT_REWORK_INDI";
		extrDbFields[ tableFldConstants.mat_rework_indi.ordinal() ].fieldType = 'V';

		extrDbFields[ tableFldConstants.partno.ordinal() ].fieldName = "EXTR_PARTNO";
		extrDbFields[ tableFldConstants.partno.ordinal() ].fieldType = 'V';

		extrDbFields[ tableFldConstants.lineno.ordinal() ].fieldName = "EXTR_LINENO";
		extrDbFields[ tableFldConstants.lineno.ordinal() ].fieldType = 'V';
		
		extrDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "EXTR_TEMPFIELD1";
		extrDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';
		
		extrDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "EXTR_TEMPFIELD2";
		extrDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		extrDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "EXTR_TEMPFIELD3";
		extrDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		extrDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EXTR_ACTIVE";
		extrDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		extrDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EXTR_CREATEDBY";
		extrDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		extrDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EXTR_CREATEDON";
		extrDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		extrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EXTR_MODIFIEDON";
		extrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SAP_EXTERNAL_REPAIR, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SAP_EXTERNAL_REPAIR, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SAP_EXTERNAL_REPAIR ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

