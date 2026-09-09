package com.akranta.tpm.dao.sql;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonMessage;

public class EntTlTargetgroupmstSql {

	public static final String TBL_ENT_TL_TARGETGROUPMST = "ENT_TL_TARGETGROUPMST";  

	TableFieldType [] tgtmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, title, flid, tempfield2, tempfield3, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getTgtmDbFields() {
		return tgtmDbFields;
	}

	public EntTlTargetgroupmstSql()
	{
		tgtmDbFields = new TableFieldType[ 9 ];
		for(int i = 0;i < 9; i++)
		{	
			tgtmDbFields[ i ] = new TableFieldType();
		}
		tgtmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TGTM_KEYID";
		tgtmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		tgtmDbFields[ tableFldConstants.title.ordinal() ].fieldName = "TGTM_TITLE";
		tgtmDbFields[ tableFldConstants.title.ordinal() ].fieldType = 'V';

		tgtmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "TGTM_FLID";
		tgtmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'C';

		tgtmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TGTM_TEMPFIELD2";
		tgtmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		tgtmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TGTM_TEMPFIELD3";
		tgtmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		tgtmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TGTM_ACTIVE";
		tgtmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		tgtmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TGTM_CREATEDBY";
		tgtmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		tgtmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TGTM_CREATEDON";
		tgtmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		tgtmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TGTM_MODIFIEDON";
		tgtmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TARGETGROUPMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TARGETGROUPMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TARGETGROUPMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getselectsql() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ENT_TL_TARGETGROUPMST WHERE TGTM_KEYID = ?";
		return sql;
	}

	public static String gettarget(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		
		sql.append("  SELECT tgtm_keyid AS keyid, tgtm_title, TO_CHAR (cnt) FROM ent_tl_targetgroupmst,");
		sql.append("(SELECT   COUNT (*) cnt, tgtm_keyid AS mstkeyid FROM ent_tl_targetgroupmst, ent_tl_targetgroupdtl WHERE tgtm_keyid(+) = tgtd_tgtm_keyid GROUP BY tgtm_keyid) WHERE tgtm_keyid = mstkeyid");
		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		CommonMessage.debugMsg(" filter "+(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()))+" sql "+sql);
		CommonMessage.debugMsg("sql..." + sql);
		return sql.toString();
	}

}

