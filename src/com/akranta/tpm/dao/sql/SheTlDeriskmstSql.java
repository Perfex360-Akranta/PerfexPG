package com.akranta.tpm.dao.sql;

public class SheTlDeriskmstSql {

	public static final String TBL_SHE_TL_DERISKMST = "SHE_TL_DERISKMST";  

	TableFieldType [] dramDbFields = null;

	public enum   tableFldConstants
	{
		keyid, rasm_keyid, date, preparedby, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getDramDbFields() {
		return dramDbFields;
	}

	public SheTlDeriskmstSql()
	{
		dramDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			dramDbFields[ i ] = new TableFieldType();
		}
		dramDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DRAM_KEYID";
		dramDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dramDbFields[ tableFldConstants.rasm_keyid.ordinal() ].fieldName = "DRAM_RASM_KEYID";
		dramDbFields[ tableFldConstants.rasm_keyid.ordinal() ].fieldType = 'V';

		dramDbFields[ tableFldConstants.date.ordinal() ].fieldName = "DRAM_DATE";
		dramDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		dramDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "DRAM_PREPAREDBY";
		dramDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		dramDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "DRAM_TEMPFIELD1";
		dramDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		dramDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "DRAM_TEMPFIELD2";
		dramDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		dramDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "DRAM_TEMPFIELD3";
		dramDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		dramDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "DRAM_TEMPFIELD4";
		dramDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		dramDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "DRAM_TEMPFIELD5";
		dramDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		dramDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DRAM_ACTIVE";
		dramDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		dramDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DRAM_CREATEDBY";
		dramDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dramDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DRAM_CREATEDON";
		dramDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dramDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DRAM_MODIFIEDON";
		dramDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_DERISKMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SHE_TL_DERISKMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_DERISKMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getDeleteDtlsSql(TableFieldType [] fieldTypeArr, Object [] dataArray){
		// TODO Auto-generated method stub
		String sql = "DELETE from SHE_TL_DERISKDTL " ;
		
		sql += " where  DRAD_DRAM_KEYID= '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getSelectSql()
	{
		String sql = "Select * from " + TBL_SHE_TL_DERISKMST +" where DRAM_KEYID=?";
		return sql;
	}

}

