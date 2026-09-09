package com.akranta.tpm.dao.sql;

public class JhaTlVisualsopdtlSql {

	public static final String TBL_JHA_TL_VISUALSOPDTL = "JHA_TL_VISUALSOPDTL";  

	TableFieldType [] vsodDbFields = null;

	public enum   tableFldConstants
	{
		keyid, vsom_keyid, instruction, keypoint, importanceofkeypoint
		, toolused, imgtoolused, imgppe, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getVsodDbFields() {
		return vsodDbFields;
	}

	public JhaTlVisualsopdtlSql()
	{
		vsodDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			vsodDbFields[ i ] = new TableFieldType();
		}
		vsodDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "VSOD_KEYID";
		vsodDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		vsodDbFields[ tableFldConstants.vsom_keyid.ordinal() ].fieldName = "VSOD_VSOM_KEYID";
		vsodDbFields[ tableFldConstants.vsom_keyid.ordinal() ].fieldType = 'V';

		vsodDbFields[ tableFldConstants.instruction.ordinal() ].fieldName = "VSOD_INSTRUCTION";
		vsodDbFields[ tableFldConstants.instruction.ordinal() ].fieldType = 'V';

		vsodDbFields[ tableFldConstants.keypoint.ordinal() ].fieldName = "VSOD_KEYPOINT";
		vsodDbFields[ tableFldConstants.keypoint.ordinal() ].fieldType = 'V';

		vsodDbFields[ tableFldConstants.importanceofkeypoint.ordinal() ].fieldName = "VSOD_IMPORTANCEOFKEYPOINT";
		vsodDbFields[ tableFldConstants.importanceofkeypoint.ordinal() ].fieldType = 'V';

		vsodDbFields[ tableFldConstants.toolused.ordinal() ].fieldName = "VSOD_TOOLUSED";
		vsodDbFields[ tableFldConstants.toolused.ordinal() ].fieldType = 'V';

		vsodDbFields[ tableFldConstants.imgtoolused.ordinal() ].fieldName = "VSOD_IMGTOOLUSED";
		vsodDbFields[ tableFldConstants.imgtoolused.ordinal() ].fieldType = 'V';

		vsodDbFields[ tableFldConstants.imgppe.ordinal() ].fieldName = "VSOD_IMGPPE";
		vsodDbFields[ tableFldConstants.imgppe.ordinal() ].fieldType = 'V';

		vsodDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "VSOD_TEMPFIELD1";
		vsodDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		vsodDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "VSOD_TEMPFIELD2";
		vsodDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		vsodDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "VSOD_TEMPFIELD3";
		vsodDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		vsodDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "VSOD_TEMPFIELD4";
		vsodDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		vsodDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "VSOD_TEMPFIELD5";
		vsodDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		vsodDbFields[ tableFldConstants.active.ordinal() ].fieldName = "VSOD_ACTIVE";
		vsodDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		vsodDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "VSOD_CREATEDBY";
		vsodDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		vsodDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "VSOD_CREATEDON";
		vsodDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		vsodDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "VSOD_MODIFIEDON";
		vsodDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_VISUALSOPDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_VISUALSOPDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_VISUALSOPDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getSingledata() {
		String sql = "select * from JHA_TL_VISUALSOPDTL WHERE VSOD_KEYID = ? " ;
		return sql;
	}
	public static String getDeleteImage(String KeyId) {
		String sql = "delete from gen_Tl_Allmoduleimgfile WHERE IMFL_REFKEYID = '"+KeyId+"'" ;
		return sql;
	}
}

