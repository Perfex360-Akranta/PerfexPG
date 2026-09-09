package com.akranta.tpm.dao.sql;

import java.util.List;

import com.akranta.tpm.model.BdmTlPhncauselink;
import com.akranta.tpm.utils.CommonMessage;

public class BdmTlPhencauseSql {

	public static final String TBL_BDM_TL_PHENOMENAMST = "BDM_TL_PHENOMENAMST";  
	public static final String TBL_BDM_TL_CAUSEMST = "BDM_TL_CAUSEMST";  
	public static final String TBL_BDM_TL_PHNCAUSELINK = "BDM_TL_PHNCAUSELINK";  

	TableFieldType [] bphmDbFields = null;
	TableFieldType [] bcsmDbFields = null;
	TableFieldType [] bpclDbFields = null;

	public enum   tablePhenFldConstants
	{
		keyid, phenomenatype, phenomenaname, shortname, remarks, assemblyid
		, levelno, childflag, isphnnotdefined, causenotneeded, active
		, createdby, createdon, modifiedon
	}

	public enum   tableCauseFldConstants
	{
		keyid, name, code, phenomenaid, remarks, iscausedefined, active
		, createdby, createdon, modifiedon
	}

	public enum   tablePclFldConstants
	{
		originalid, elementid, parentid, displaycode, elementtype, active
	}

	public TableFieldType[] getBphmDbFields() {
		return bphmDbFields;
	}
	
	public TableFieldType[] getBcsmDbFields() {
		return bcsmDbFields;
	}	

	public TableFieldType[] getBpclDbFields() {
		return bpclDbFields;
	}
	
	public BdmTlPhencauseSql()
	{
		bphmDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			bphmDbFields[ i ] = new TableFieldType();
		}
		bphmDbFields[ tablePhenFldConstants.keyid.ordinal() ].fieldName = "BPHM_KEYID";
		bphmDbFields[ tablePhenFldConstants.keyid.ordinal() ].fieldType = 'V';

		bphmDbFields[ tablePhenFldConstants.phenomenatype.ordinal() ].fieldName = "BPHM_PHENOMENATYPE";
		bphmDbFields[ tablePhenFldConstants.phenomenatype.ordinal() ].fieldType = 'V';

		bphmDbFields[ tablePhenFldConstants.phenomenaname.ordinal() ].fieldName = "BPHM_PHENOMENANAME";
		bphmDbFields[ tablePhenFldConstants.phenomenaname.ordinal() ].fieldType = 'V';

		bphmDbFields[ tablePhenFldConstants.shortname.ordinal() ].fieldName = "BPHM_SHORTNAME";
		bphmDbFields[ tablePhenFldConstants.shortname.ordinal() ].fieldType = 'V';

		bphmDbFields[ tablePhenFldConstants.remarks.ordinal() ].fieldName = "BPHM_REMARKS";
		bphmDbFields[ tablePhenFldConstants.remarks.ordinal() ].fieldType = 'V';

		bphmDbFields[ tablePhenFldConstants.assemblyid.ordinal() ].fieldName = "BPHM_ASSEMBLYID";
		bphmDbFields[ tablePhenFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		bphmDbFields[ tablePhenFldConstants.levelno.ordinal() ].fieldName = "BPHM_LEVELNO";
		bphmDbFields[ tablePhenFldConstants.levelno.ordinal() ].fieldType = 'V';

		bphmDbFields[ tablePhenFldConstants.childflag.ordinal() ].fieldName = "BPHM_CHILDFLAG";
		bphmDbFields[ tablePhenFldConstants.childflag.ordinal() ].fieldType = 'C';

		bphmDbFields[ tablePhenFldConstants.isphnnotdefined.ordinal() ].fieldName = "BPHM_ISPHNNOTDEFINED";
		bphmDbFields[ tablePhenFldConstants.isphnnotdefined.ordinal() ].fieldType = 'C';

		bphmDbFields[ tablePhenFldConstants.causenotneeded.ordinal() ].fieldName = "BPHM_CAUSENOTNEEDED";
		bphmDbFields[ tablePhenFldConstants.causenotneeded.ordinal() ].fieldType = 'C';

		bphmDbFields[ tablePhenFldConstants.active.ordinal() ].fieldName = "BPHM_ACTIVE";
		bphmDbFields[ tablePhenFldConstants.active.ordinal() ].fieldType = 'C';

		bphmDbFields[ tablePhenFldConstants.createdby.ordinal() ].fieldName = "BPHM_CREATEDBY";
		bphmDbFields[ tablePhenFldConstants.createdby.ordinal() ].fieldType = 'V';

		bphmDbFields[ tablePhenFldConstants.createdon.ordinal() ].fieldName = "BPHM_CREATEDON";
		bphmDbFields[ tablePhenFldConstants.createdon.ordinal() ].fieldType = 'D';

		bphmDbFields[ tablePhenFldConstants.modifiedon.ordinal() ].fieldName = "BPHM_MODIFIEDON";
		bphmDbFields[ tablePhenFldConstants.modifiedon.ordinal() ].fieldType = 'D';

		
		bcsmDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			bcsmDbFields[ i ] = new TableFieldType();
		}
		bcsmDbFields[ tableCauseFldConstants.keyid.ordinal() ].fieldName = "BCSM_KEYID";
		bcsmDbFields[ tableCauseFldConstants.keyid.ordinal() ].fieldType = 'V';

		bcsmDbFields[ tableCauseFldConstants.name.ordinal() ].fieldName = "BCSM_NAME";
		bcsmDbFields[ tableCauseFldConstants.name.ordinal() ].fieldType = 'V';

		bcsmDbFields[ tableCauseFldConstants.code.ordinal() ].fieldName = "BCSM_CODE";
		bcsmDbFields[ tableCauseFldConstants.code.ordinal() ].fieldType = 'V';

		bcsmDbFields[ tableCauseFldConstants.phenomenaid.ordinal() ].fieldName = "BCSM_PHENOMENAID";
		bcsmDbFields[ tableCauseFldConstants.phenomenaid.ordinal() ].fieldType = 'V';

		bcsmDbFields[ tableCauseFldConstants.remarks.ordinal() ].fieldName = "BCSM_REMARKS";
		bcsmDbFields[ tableCauseFldConstants.remarks.ordinal() ].fieldType = 'V';

		bcsmDbFields[ tableCauseFldConstants.iscausedefined.ordinal() ].fieldName = "BCSM_ISCAUSEDEFINED";
		bcsmDbFields[ tableCauseFldConstants.iscausedefined.ordinal() ].fieldType = 'C';

		bcsmDbFields[ tableCauseFldConstants.active.ordinal() ].fieldName = "BCSM_ACTIVE";
		bcsmDbFields[ tableCauseFldConstants.active.ordinal() ].fieldType = 'C';

		bcsmDbFields[ tableCauseFldConstants.createdby.ordinal() ].fieldName = "BCSM_CREATEDBY";
		bcsmDbFields[ tableCauseFldConstants.createdby.ordinal() ].fieldType = 'V';

		bcsmDbFields[ tableCauseFldConstants.createdon.ordinal() ].fieldName = "BCSM_CREATEDON";
		bcsmDbFields[ tableCauseFldConstants.createdon.ordinal() ].fieldType = 'D';

		bcsmDbFields[ tableCauseFldConstants.modifiedon.ordinal() ].fieldName = "BCSM_MODIFIEDON";
		bcsmDbFields[ tableCauseFldConstants.modifiedon.ordinal() ].fieldType = 'D';		

		bpclDbFields = new TableFieldType[ 6 ];
		for(int i = 0;i < 6; i++)
		{	
			bpclDbFields[ i ] = new TableFieldType();
		}
		bpclDbFields[ tablePclFldConstants.originalid.ordinal() ].fieldName = "BPCL_ORIGINALID";
		bpclDbFields[ tablePclFldConstants.originalid.ordinal() ].fieldType = 'V';

		bpclDbFields[ tablePclFldConstants.elementid.ordinal() ].fieldName = "BPCL_ELEMENTID";
		bpclDbFields[ tablePclFldConstants.elementid.ordinal() ].fieldType = 'V';

		bpclDbFields[ tablePclFldConstants.parentid.ordinal() ].fieldName = "BPCL_PARENTID";
		bpclDbFields[ tablePclFldConstants.parentid.ordinal() ].fieldType = 'V';

		bpclDbFields[ tablePclFldConstants.displaycode.ordinal() ].fieldName = "BPCL_DISPLAYCODE";
		bpclDbFields[ tablePclFldConstants.displaycode.ordinal() ].fieldType = 'V';

		bpclDbFields[ tablePclFldConstants.elementtype.ordinal() ].fieldName = "BPCL_ELEMENTTYPE";
		bpclDbFields[ tablePclFldConstants.elementtype.ordinal() ].fieldType = 'V';

		bpclDbFields[ tablePclFldConstants.active.ordinal() ].fieldName = "BPCL_ACTIVE";
		bpclDbFields[ tablePclFldConstants.active.ordinal() ].fieldType = 'C';

	}

	public static String getPhenInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_PHENOMENAMST, fieldTypeArr, dataArray);
	}

	public static String getPhenUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_PHENOMENAMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tablePhenFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tablePhenFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getPhenDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_PHENOMENAMST ;
		
		sql += " where " + fieldTypeArr[tablePhenFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tablePhenFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getCauseInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_CAUSEMST, fieldTypeArr, dataArray);
	}

	public static String getCauseUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_CAUSEMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableCauseFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableCauseFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getCauseDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_CAUSEMST ;
		
		sql += " where " + fieldTypeArr[tableCauseFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableCauseFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getPclInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_PHNCAUSELINK, fieldTypeArr, dataArray);
	}

	public static String getPclUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_PHNCAUSELINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tablePclFldConstants.elementid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tablePclFldConstants.elementid.ordinal() ] + "'";
		CommonMessage.debugMsg("Update sql : "+sql);
		return sql;
	}

	public static String getPclDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_PHNCAUSELINK ;
		
		sql += " where " + fieldTypeArr[tablePclFldConstants.originalid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tablePclFldConstants.originalid.ordinal()] + "'";
		return sql;
	}
	
	

}

