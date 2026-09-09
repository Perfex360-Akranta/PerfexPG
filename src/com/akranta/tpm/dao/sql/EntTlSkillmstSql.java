package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;

public class EntTlSkillmstSql {

	TableFieldType [] skilDbFields = null;

	public enum   tableFldConstants
	{
		keyid, code, name, parentid, ischild, fact_keyid
		, dept_keyid,cell_function,cell_fun_keyid
		, evaluationtypeid,type
		, remarks, effective_date, inactive_date, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getSkilDbFields() {
		return skilDbFields;
	}

	public EntTlSkillmstSql()
	{
		skilDbFields = new TableFieldType[ 23 ];
		for(int i = 0;i < 23; i++)
		{	
			skilDbFields[ i ] = new TableFieldType();
		}
		skilDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SKIL_KEYID";
		skilDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		skilDbFields[ tableFldConstants.code.ordinal() ].fieldName = "SKIL_CODE";
		skilDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		skilDbFields[ tableFldConstants.name.ordinal() ].fieldName = "SKIL_NAME";
		skilDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		skilDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "SKIL_PARENTID";
		skilDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		skilDbFields[ tableFldConstants.ischild.ordinal() ].fieldName = "SKIL_ISCHILD";
		skilDbFields[ tableFldConstants.ischild.ordinal() ].fieldType = 'C';

		skilDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldName = "SKIL_FACT_KEYID";
		skilDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldType = 'V';		
		
		skilDbFields[ tableFldConstants.dept_keyid.ordinal() ].fieldName = "SKIL_DEPT_KEYID";
		skilDbFields[ tableFldConstants.dept_keyid.ordinal() ].fieldType = 'V';		
		
		skilDbFields[ tableFldConstants.cell_function.ordinal() ].fieldName = "SKIL_CELL_FUNCTION";
		skilDbFields[ tableFldConstants.cell_function.ordinal() ].fieldType = 'V';			
		
		skilDbFields[ tableFldConstants.cell_fun_keyid.ordinal() ].fieldName = "SKIL_CELL_FUN_KEYID";
		skilDbFields[ tableFldConstants.cell_fun_keyid.ordinal() ].fieldType = 'V';		

		skilDbFields[ tableFldConstants.evaluationtypeid.ordinal() ].fieldName = "SKIL_EVALUATIONTYPEID";
		skilDbFields[ tableFldConstants.evaluationtypeid.ordinal() ].fieldType = 'V';
		
		skilDbFields[ tableFldConstants.type.ordinal() ].fieldName = "SKIL_TYPE";
		skilDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		skilDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "SKIL_REMARKS";
		skilDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		skilDbFields[ tableFldConstants.effective_date.ordinal() ].fieldName = "SKIL_EFFECTIVE_DATE";
		skilDbFields[ tableFldConstants.effective_date.ordinal() ].fieldType = 'D';

		skilDbFields[ tableFldConstants.inactive_date.ordinal() ].fieldName = "SKIL_INACTIVE_DATE";
		skilDbFields[ tableFldConstants.inactive_date.ordinal() ].fieldType = 'D';

		skilDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SKIL_TEMPFIELD1";
		skilDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		skilDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SKIL_TEMPFIELD2";
		skilDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		skilDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SKIL_TEMPFIELD3";
		skilDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		skilDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SKIL_TEMPFIELD4";
		skilDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		skilDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SKIL_TEMPFIELD5";
		skilDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		skilDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SKIL_ACTIVE";
		skilDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		skilDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SKIL_CREATEDBY";
		skilDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		skilDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SKIL_CREATEDON";
		skilDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		skilDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SKIL_MODIFIEDON";
		skilDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TableNames.TBL_ENT_TL_SKILLMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TableNames.TBL_ENT_TL_SKILLMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TableNames.TBL_ENT_TL_SKILLMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getSelectSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "Select * from " + TableNames.TBL_ENT_TL_SKILLMST ;
		
		sql += " where 1=1 " ;
		if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.keyid.ordinal()]))
				sql += " and " +  fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				" = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		
		if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.name.ordinal()]))
				sql += " and " +  fieldTypeArr[tableFldConstants.name.ordinal()].fieldName  +
				" = '" +  (String)dataArray[ tableFldConstants.name.ordinal()] + "'";
		
		if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.parentid.ordinal()]))
			sql += " and " +  fieldTypeArr[tableFldConstants.parentid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.parentid.ordinal()] + "'";
		
		return sql;
	}
	
	public static String getSkillLevelSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuffer sql= new StringBuffer();		
		sql.append(" SELECT LEVEL ");		//, SKIL_NAME 
		sql.append(" FROM " + TableNames.TBL_ENT_TL_SKILLMST  );
		sql.append( " WHERE " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'");
		sql.append(" START WITH SKIL_KEYID = SKIL_PARENTID  " );
		sql.append(" CONNECT BY nocycle prior SKIL_KEYID = SKIL_PARENTID " );
		
		return sql.toString();
	}
	
	public static String getConfigSkillLevel()
	{
		StringBuffer sql= new StringBuffer();		
		sql.append(" SELECT CNFM_SETTINGVALUE ");	
		sql.append(" FROM " + TableNames.TBL_ADM_TL_CONFIGURATIONMST  );
		sql.append( " WHERE CNFM_CODE='ENTMAXSKILLLEVEL' AND SYSDATE BETWEEN CNFM_FROMDATE AND CNFM_TILLDATE " );
		
		return sql.toString();
	}
	
	public static String getSearchSkillLevelSql(String originalId) {		
		StringBuffer sb = new StringBuffer(); 
		sb.append("select  CHILDPATH,SKIL_KEYID,SKIL_NAME,L as SKILL_LEVEL from ent_vw_skilltreepath where 1=1 ");		
		if(UIUtils.isValidKeyId(originalId))
			sb.append(" and SKIL_NAME = '"+originalId+"'");
		return sb.toString();
	}
	
	public static String getSearchNodeSql(String searchNode,String originalId) {		
		StringBuffer sb = new StringBuffer(); 
		//sb.append("select parentid from GEN_VW_FUNCLOCN where 1=1 ");
		sb.append("select fnln_parentid from gen_vw_funclocndept where 1=1 ");
		if(UIUtils.isValidKeyId(searchNode))
			sb.append(" and newdisplaycode = '"+searchNode+"'");
		if(UIUtils.isValidKeyId(originalId))
			sb.append(" and fnln_originalid = '"+originalId+"'");
		return sb.toString();
	}
}

