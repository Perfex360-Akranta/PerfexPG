package com.akranta.tpm.dao.sql;

public class GenTlVisualcntchecklistdtlSql {

	public static final String TBL_GEN_TL_VISUALCNTCHECKLISTDTL = "GEN_TL_VISUALCNTCHECKLISTDTL";  

	TableFieldType [] vcdtDbFields = null;

	public enum   tableFldConstants
	{
		keyid, vccl_keyid, vccd_keyid, criteriaval, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getVcdtDbFields() {
		return vcdtDbFields;
	}

	public GenTlVisualcntchecklistdtlSql()
	{
		vcdtDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			vcdtDbFields[ i ] = new TableFieldType();
		}
		vcdtDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "VCDT_KEYID";
		vcdtDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		vcdtDbFields[ tableFldConstants.vccl_keyid.ordinal() ].fieldName = "VCDT_VCCL_KEYID";
		vcdtDbFields[ tableFldConstants.vccl_keyid.ordinal() ].fieldType = 'V';

		vcdtDbFields[ tableFldConstants.vccd_keyid.ordinal() ].fieldName = "VCDT_VCCD_KEYID";
		vcdtDbFields[ tableFldConstants.vccd_keyid.ordinal() ].fieldType = 'V';

		vcdtDbFields[ tableFldConstants.criteriaval.ordinal() ].fieldName = "VCDT_CRITERIAVAL";
		vcdtDbFields[ tableFldConstants.criteriaval.ordinal() ].fieldType = 'C';

		vcdtDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "VCDT_TEMPFIELD1";
		vcdtDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		vcdtDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "VCDT_TEMPFIELD2";
		vcdtDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		vcdtDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "VCDT_TEMPFIELD3";
		vcdtDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		vcdtDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "VCDT_TEMPFIELD4";
		vcdtDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		vcdtDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "VCDT_TEMPFIELD5";
		vcdtDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		vcdtDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "VCDT_TEMPFIELD6";
		vcdtDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		vcdtDbFields[ tableFldConstants.active.ordinal() ].fieldName = "VCDT_ACTIVE";
		vcdtDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		vcdtDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "VCDT_CREATEDBY";
		vcdtDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		vcdtDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "VCDT_CREATEDON";
		vcdtDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		vcdtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "VCDT_MODIFIEDON";
		vcdtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_VISUALCNTCHECKLISTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_VISUALCNTCHECKLISTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_VISUALCNTCHECKLISTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

