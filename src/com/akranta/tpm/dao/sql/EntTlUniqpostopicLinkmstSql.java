package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlUniqpostopicLinkmstSql {

	public static final String TBL_ENT_TL_UNIQPOSTOPIC_LINKMST = "ENT_TL_UNIQPOSTOPIC_LINKMST";  

	TableFieldType [] tmtmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, role_keyid, topi_keyid, skrm_keyid, ksa, elementid
		, tempfield2, tempfield3, tempfield4, tempfield5, tempfield6
		, cutoffmark, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getTmtmDbFields() {
		return tmtmDbFields;
	}

	public EntTlUniqpostopicLinkmstSql()
	{
		tmtmDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			tmtmDbFields[ i ] = new TableFieldType();
		}
		tmtmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TMTM_KEYID";
		tmtmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "TMTM_FLID";
		tmtmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "TMTM_ROLE_KEYID";
		tmtmDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldName = "TMTM_TOPI_KEYID";
		tmtmDbFields[ tableFldConstants.topi_keyid.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldName = "TMTM_SKRM_KEYID";
		tmtmDbFields[ tableFldConstants.skrm_keyid.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.ksa.ordinal() ].fieldName = "TMTM_KSA";
		tmtmDbFields[ tableFldConstants.ksa.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "TMTM_ELEMENTID";
		tmtmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TMTM_TEMPFIELD2";
		tmtmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TMTM_TEMPFIELD3";
		tmtmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "TMTM_TEMPFIELD4";
		tmtmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "TMTM_TEMPFIELD5";
		tmtmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "TMTM_TEMPFIELD6";
		tmtmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.cutoffmark.ordinal() ].fieldName = "TMTM_CUTOFFMARK";
		tmtmDbFields[ tableFldConstants.cutoffmark.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TMTM_ACTIVE";
		tmtmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		tmtmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TMTM_CREATEDBY";
		tmtmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		tmtmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TMTM_CREATEDON";
		tmtmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		tmtmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TMTM_MODIFIEDON";
		tmtmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_UNIQPOSTOPIC_LINKMST , fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_UNIQPOSTOPIC_LINKMST , fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_UNIQPOSTOPIC_LINKMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getTaskTpSelectSql() {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from " + TBL_ENT_TL_UNIQPOSTOPIC_LINKMST  + " where TMTM_KEYID = ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}

	public static String RpDetailGrid(CommonFilter commonFilter, String flid, String uniquePostion, String mstkeyid, String createmode, String topic) {
		// TODO Auto-generated method stub
		
		String filter = FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter());
		
	/*	StringBuffer sf = new StringBuffer();
		sf.append(" SELECT * FROM ( ");
		sf.append(" select TMTD_KEYID,'',DECODE(TMTD_KEYID,'',0,1) as  TICKVALUE,TMTD_TMTM_KEYID tmkdkeyid,TMKD_TASK  AS TASK ,TMKD_KSA AS KSA from ent_tl_taskdtl T1,ent_tl_taskmst,");
		sf.append(" ENT_TL_UNIQPOSTOPIC_LINKMST,ENT_TL_UNIQPOSTOPIC_LINKDTl");
		sf.append(" where TMKD_TMKM_KEYID = TMKM_KEYID");
		sf.append(" and TMTD_TMKD_KEYID(+) = TMKD_KEYID AND TMTD_TMTM_KEYID = TMTM_KEYID(+)"); 
		sf.append(" AND TMKM_ROLE_KEYID = '"+uniquePostion+"' ");
		sf.append(" AND TMKM_FLID = '"+flid+"' ");
		sf.append(" AND TMTM_TOPI_KEYID(+) ='"+topic+"'");
		sf.append(" and NOT EXISTS ( SELECT TMTD_TMKD_KEYID FROM  ENT_TL_UNIQPOSTOPIC_LINKMST,ENT_TL_UNIQPOSTOPIC_LINKDTl T2"); 
		sf.append(" WHERE TMTD_TMTM_KEYID = TMTM_KEYID AND TMTM_TOPI_KEYID <> '"+topic+"' AND T1.TMKD_KEYID = T2.TMTD_TMKD_KEYID )"); */
		/**
		 * 
		 * SUGUMAR DONE ON 22Mar2016 for Duplicates on GRID
		 * 
		 */
		StringBuffer sf = new StringBuffer();
		sf.append(" SELECT * FROM ( ");
		sf.append(" select DISTINCT(TMKD_KEYID) AS TMKD_KEYID,'',DECODE(TMTD_KEYID,'',0,1) as  TICKVALUE,TMKD_KEYID AS tmkdid,TMKD_TASK  AS TASK ,TMKD_KSA AS KSA from ent_tl_taskdtl T1,ent_tl_taskmst,");
		sf.append(" ENT_TL_UNIQPOSTOPIC_LINKMST,ENT_TL_UNIQPOSTOPIC_LINKDTl");
		sf.append(" where TMKD_TMKM_KEYID = TMKM_KEYID");
		sf.append(" and TMTD_TMKD_KEYID(+) = TMKD_KEYID AND TMTD_TMTM_KEYID = TMTM_KEYID(+)"); 
		sf.append(" AND TMKM_ROLE_KEYID = '"+uniquePostion+"' ");
		sf.append(" AND TMKM_FLID = '"+flid+"' ");
		sf.append(" AND TMTM_ROLE_KEYID = '"+uniquePostion+"' ");
		sf.append(" AND TMTM_FLID = '"+flid+"' ");
		//sf.append(" AND TMTM_TOPI_KEYID(+) ='"+topic+"'");
		sf.append(" and NOT EXISTS ( SELECT TMTD_TMKD_KEYID FROM  ENT_TL_UNIQPOSTOPIC_LINKMST,ENT_TL_UNIQPOSTOPIC_LINKDTl T2"); 
		sf.append(" WHERE TMTD_TMTM_KEYID = TMTM_KEYID AND TMTM_TOPI_KEYID <> '"+topic+"' AND T1.TMKD_KEYID = T2.TMTD_TMKD_KEYID )");
		
		sf.append("  ) " );
		
		if (filter.length() > 1)
			sf.append( " where 1=1 " + filter );
		
		sf.append(" ORDER BY tmkdid ");
	 
		return sf.toString();
	}
	
}

