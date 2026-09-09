package com.akranta.tpm.dao.sql;

import java.util.List;

import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
public class MasterTableConfigSql {
	
	public static String getMasterTableConfigSql(String menuId){
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT * from ");
		sql.append( TableNames.TBL_GEN_TL_MMCMST);
		sql.append( ","); 
		sql.append( TableNames.TBL_GEN_TL_MMCDTL);
		sql.append( " WHERE MMCN_KEYID = MSCN_MASTERID AND MMCN_MENUID = '");
		sql.append( menuId );
		sql.append( "'");
		CommonMessage.debugMsg("The getMasterTableCongigSql Method"+sql);
		return sql.toString(); 
	}
	
	public static String getMasterTableMetaDataSql(String tableName){
		return " select * from " + tableName + " where 1 = 2 ";
	}
  public static String getTableNameinfo(String tableName){
	  
  return "SELECT MSCN_COLUMNNAME FROM GEN_TL_MMCDTL WHERE MSCN_TABLENAME='"+tableName+"'";
}
 
 /*  public static String validateKeipanSql(String validatekaipen){
	 StringBuilder validationSql=new StringBuilder();
	 
	 
//	 validationSql.append("SELECT * FROM (SELECT CASE");
//	 validationSql.append(" WHEN LENGTH (KEYID) > 100");
//	 validationSql.append(" THEN slno || '##2'");
	 
	 
	 validationSql.append("INSTR(gen_tl_functionallocn )");
	 validationSql.append(" LENGTH(TRIM(JH))<2");
	 validationSql.append("THEN");
	 
	 validationSql.append("SELECT  flid FROM ");
	 validationSql.append(" gen_mv_flidhierarchy WHERE");
	 validationSql.append("fnln_displaycode");  
	 
	 validationSql.append("INSTR(gen_tl_functionallocn )");
	 validationSql.append(" LENGTH(TRIM(DMT))<2");
	 validationSql.append("THEN");
	 validationSql.append("SELECT * FROM ");
	 validationSql.append(" gen_tl_functionallocn)");
	 validationSql.append(" FNLN_ELEMENTTYPE= 'DMT' ");
	 
	 validationSql.append("INSTR(gen_tl_functionallocn)");
	 validationSql.append(" LENGTH(TRIM(PBU))<2"); 
	 validationSql.append("THEN");
	 validationSql.append("SELECT * FROM");
	 validationSql.append(" gen_tl_functionallocn)");
	 validationSql.append(" FNLN_ELEMENTTYPE= 'PBU' ");
	 
	 
	 validationSql.append(" ELSE ''");
	 validationSql.append(" END KEYID,");
	 validationSql.append("CASE");
	 validationSql.append(" WHEN LENGTH TRIM(CODE)> 100");
	 validationSql.append(" THEN slno || '##3'");
	 validationSql.append(" ELSE ''");
	 validationSql.append(" END CODE,");
	 validationSql.append(" CASE");
	 validationSql.append(" WHEN LENGTH TRIM(NAME) > 20");
	 validationSql.append(" THEN slno || '##4'");
	 validationSql.append(" ELSE ''");
	 validationSql.append(" END NAME,");
	 validationSql.append(" CASE");
	 validationSql.append(" WHEN LENGTH TRIM(FLID) > 100");
	 validationSql.append(" THEN slno || '##5'");
	 validationSql.append(" ELSE ''");
	 validationSql.append(" END FLID"); 
	 validationSql.append(" FROM ");
	 validationSql.append(validatekaipen);
	 validationSql.append(") WHERE 1 = 2");
	 validationSql.append(" OR KEYID LIKE '%##%'");
	 validationSql.append(" OR CODE LIKE '%##%'");
	 validationSql.append(" OR NAME LIKE '%##%'");
	 validationSql.append(" OR FLID LIKE '%##%'");
	 com.akranta.tpm.utils.CommonMessage.debugMsg("SQL For Validatelength: "+validationSql.toString());
	 return validationSql.toString();
	}
	
/*	public static String codeDuplicateSql(String code){
		
		 StringBuilder sql=new StringBuilder();
		 sql.append("SELECT slno || '##2' ");
		 sql.append("FROM (SELECT fnln_displaycode ");
		 sql.append("FROM gen_tl_functionallocn ");
		 sql.append(" WHERE fnln_parentid IN (SELECT fnln_elementid ");
		 sql.append("FROM gen_tl_functionallocn, ");
		 sql.append(code);
		 sql.append(" WHERE fnln_displaycode = equipment) ");
		 sql.append("AND fnln_elementtype = 'A' ");
		 sql.append("INTERSECT ");
		 sql.append("SELECT assembly FROM ");
		 sql.append(code);
		 sql.append(", gen_tl_functionallocn ");
		 sql.append("WHERE equipment = fnln_displaycode), ");
		 sql.append(code);
		 sql.append(" where assembly=fnln_displaycode");
		 sql.append(" order by slno "); 
		 com.akranta.tpm.utils.CommonMessage.debugMsg("SQL For CodeDuplicateSql:"+sql.toString());	
         return sql.toString();
}


	
	
public static String nameDuplicateSql(String name){
	
	 StringBuilder sql=new StringBuilder();
	 sql.append("SELECT slno || '##3' ");
	 sql.append("FROM (SELECT fnln_displaycode ");
	 sql.append("FROM gen_tl_functionallocn ");
	 sql.append(" WHERE fnln_parentid IN (SELECT fnln_elementid ");
	 sql.append("FROM gen_tl_functionallocn, ");
	 sql.append(name);
	 sql.append(" WHERE fnln_displaycode = equipment) ");
	 sql.append("AND fnln_elementtype = 'SAM' ");
	 sql.append("INTERSECT ");
	 sql.append("SELECT subassembly FROM ");
	 sql.append(name);
	 sql.append(", gen_tl_functionallocn ");
	 sql.append("WHERE equipment = fnln_displaycode), ");
	 sql.append(name);
	 sql.append(" where subassembly=fnln_displaycode ");
	 sql.append(" order by slno "); 
	 com.akranta.tpm.utils.CommonMessage.debugMsg("Sql For NameDuplicateSql:"+sql.toString());
	 return sql.toString();	
}

public static String flocationDuplicateSql(String flocation){
	 StringBuilder sql=new StringBuilder();
	 sql.append("SELECT MIN (slno) || '##4' ");
	 sql.append("FROM (SELECT fnln_description ");
	 sql.append("FROM gen_tl_functionallocn ");
	 sql.append(" WHERE fnln_parentid IN (SELECT fnln_elementid ");
	 sql.append("FROM gen_tl_functionallocn, ");
	 sql.append(flocation);
	 sql.append(" WHERE fnln_description = partname) ");
	 sql.append("AND fnln_elementtype = 'SPR' ");
	 sql.append("INTERSECT ");
	 sql.append("SELECT flid FROM ");
	 sql.append(flocation);
	 sql.append(", gen_tl_functionallocn ");
	 sql.append("WHERE partname = fnln_description), ");
	 sql.append(flocation);
	 sql.append(" order by MIN(slno) ");
	 com.akranta.tpm.utils.CommonMessage.debugMsg("SQL For flocationDuplicateSql:"+sql.toString());
	 return sql.toString();
}*/
}
