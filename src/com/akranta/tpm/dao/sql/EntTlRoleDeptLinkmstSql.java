package com.akranta.tpm.dao.sql;

public class EntTlRoleDeptLinkmstSql {

	public static final String TBL_ENT_TL_ROLE_DEPT_LINKMST = "ENT_TL_ROLE_DEPT_LINKMST";  

	TableFieldType [] erdlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, fact_keyid, dept_keyid, cell_function, cell_fun_keyid
		, role_keyid, elective_skills, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getErdlDbFields() {
		return erdlDbFields;
	}

	public EntTlRoleDeptLinkmstSql()
	{
		erdlDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			erdlDbFields[ i ] = new TableFieldType();
		}
		erdlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ERDL_KEYID";
		erdlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		erdlDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldName = "ERDL_FACT_KEYID";
		erdlDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldType = 'V';

		erdlDbFields[ tableFldConstants.dept_keyid.ordinal() ].fieldName = "ERDL_DEPT_KEYID";
		erdlDbFields[ tableFldConstants.dept_keyid.ordinal() ].fieldType = 'V';

		erdlDbFields[ tableFldConstants.cell_function.ordinal() ].fieldName = "ERDL_CELL_FUNCTION";
		erdlDbFields[ tableFldConstants.cell_function.ordinal() ].fieldType = 'C';

		erdlDbFields[ tableFldConstants.cell_fun_keyid.ordinal() ].fieldName = "ERDL_CELL_FUN_KEYID";
		erdlDbFields[ tableFldConstants.cell_fun_keyid.ordinal() ].fieldType = 'V';

		erdlDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "ERDL_ROLE_KEYID";
		erdlDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';

		erdlDbFields[ tableFldConstants.elective_skills.ordinal() ].fieldName = "ERDL_ELECTIVE_SKILLS";
		erdlDbFields[ tableFldConstants.elective_skills.ordinal() ].fieldType = 'N';

		erdlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ERDL_TEMPFIELD1";
		erdlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		erdlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ERDL_TEMPFIELD2";
		erdlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		erdlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ERDL_TEMPFIELD3";
		erdlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		erdlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ERDL_TEMPFIELD4";
		erdlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		erdlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ERDL_TEMPFIELD5";
		erdlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		erdlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ERDL_ACTIVE";
		erdlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		erdlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ERDL_CREATEDBY";
		erdlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		erdlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ERDL_CREATEDON";
		erdlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		erdlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ERDL_MODIFIEDON";
		erdlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_ROLE_DEPT_LINKMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_ROLE_DEPT_LINKMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_ROLE_DEPT_LINKMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

