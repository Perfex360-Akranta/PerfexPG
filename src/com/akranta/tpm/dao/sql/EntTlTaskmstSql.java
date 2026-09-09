package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlTaskmstSql {

	public static final String TBL_ENT_TL_TASKMST = "ENT_TL_TASKMST";  

	TableFieldType [] tmkmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, role_keyid, elementid, tempfield2, tempfield3, tempfield4
		, tempfield5, tempfield6, tempfield7, active, createdby, createdon
		, modifiedon
	}
	
	public TableFieldType[] getTmkmDbFields() {
		return tmkmDbFields;
	}

	public EntTlTaskmstSql()
	{
		tmkmDbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			tmkmDbFields[ i ] = new TableFieldType();
		}
		tmkmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TMKM_KEYID";
		tmkmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "TMKM_FLID";
		tmkmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "TMKM_ROLE_KEYID";
		tmkmDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';
		
		tmkmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "TMKM_ELEMENTID";
		tmkmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TMKM_TEMPFIELD2";
		tmkmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TMKM_TEMPFIELD3";
		tmkmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "TMKM_TEMPFIELD4";
		tmkmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "TMKM_TEMPFIELD5";
		tmkmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "TMKM_TEMPFIELD6";
		tmkmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "TMKM_TEMPFIELD7";
		tmkmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TMKM_ACTIVE";
		tmkmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		tmkmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TMKM_CREATEDBY";
		tmkmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		tmkmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TMKM_CREATEDON";
		tmkmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		tmkmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TMKM_MODIFIEDON";
		tmkmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TASKMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TASKMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TASKMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getTaksKsaSelectSql() {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from " + TBL_ENT_TL_TASKMST + " where TMKM_KEYID = ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}

	public String getTotalDetailGrid(CommonFilter commonFilter, String mstkeyid, String flid, String unique) {
		StringBuffer strBuf = new StringBuffer();
		
		strBuf.append(" Select COUNT(*) ");
		
		strBuf.append(" from (Select ROWNUM RN, A.* from (" );

		strBuf.append ( " SELECT  TMKD_KEYID,'' AS BTN,TMKD_BASICEMNT,DECODE(TMKD_KSA,'S','SKILL','K','KNOWLEDGE','A','ATTITUDE') AS KSA, ");
		strBuf.append ( " TMKD_KSA,TMKD_KSADESC,TMKD_TASK,SPOK_NAME,SKRM_DESCRIPTION,TMKD_SKRM_KEYID, TMKD_SPOKE_KEYID ") ;
		
		if(UIUtils.isValidKeyId(flid) && UIUtils.isValidKeyId(unique))
		{
			strBuf.append ( " From ENT_TL_TASKMST,ENT_TL_TASKDTL,ENT_TL_SKILL_RATINGMST, ENT_TL_SPOKEMST where  TMKD_TMKM_KEYID=TMKM_KEYID AND SKRM_KEYID=TMKD_SKRM_KEYID AND SPOK_KEYID(+)=tmkd_spoke_keyid AND  tmkm_flid = '"+flid+"' AND  TMKM_ROLE_KEYID='"+unique+"' ");
		}
		else
		{	
			strBuf.append ( " From ENT_TL_TASKMST,ENT_TL_TASKDTL,ENT_TL_SKILL_RATINGMST, ENT_TL_SPOKEMST where  TMKD_TMKM_KEYID=TMKM_KEYID AND SKRM_KEYID=TMKD_SKRM_KEYID AND SPOK_KEYID(+)=tmkd_spoke_keyid AND TMKM_KEYID='"+mstkeyid+"' ");     
		}
		strBuf.append ( " ) A ) where 1=1 " );
		
		strBuf.append( FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );

		return strBuf.toString();
	}

	public String RpDetailGrid(CommonFilter commonFilter, String mstkeyid, String flid, String unique) {
		String sql="";
		StringBuffer strBuf = new StringBuffer();
		
		strBuf.append(" Select TMKD_KEYID,BTN,TMKD_BASICEMNT, KSA, ");
		strBuf.append ( " TMKD_KSA,TMKD_KSADESC,TMKD_TASK,SPOK_NAME,SKRM_DESCRIPTION,TMKD_SKRM_KEYID, TMKD_SPOKE_KEYID ") ;
		
		strBuf.append(" from (Select ROWNUM RN, A.* from (" );

		strBuf.append ( " SELECT  TMKD_KEYID,'' AS BTN,TMKD_BASICEMNT,DECODE(TMKD_KSA,'S','SKILL','K','KNOWLEDGE','A','ATTITUDE') AS KSA, ");
		strBuf.append ( " TMKD_KSA,TMKD_KSADESC,TMKD_TASK,SPOK_NAME,SKRM_DESCRIPTION,TMKD_SKRM_KEYID, TMKD_SPOKE_KEYID ") ;
		
		if(UIUtils.isValidKeyId(flid) && UIUtils.isValidKeyId(unique))
		{
			strBuf.append ( " From ENT_TL_TASKMST,ENT_TL_TASKDTL,ENT_TL_SKILL_RATINGMST, ENT_TL_SPOKEMST where  TMKD_TMKM_KEYID=TMKM_KEYID AND SKRM_KEYID=TMKD_SKRM_KEYID AND SPOK_KEYID(+)=tmkd_spoke_keyid AND  tmkm_flid = '"+flid+"' AND  TMKM_ROLE_KEYID='"+unique+"' ");
		}
		else
		{	
			strBuf.append ( " From ENT_TL_TASKMST,ENT_TL_TASKDTL,ENT_TL_SKILL_RATINGMST, ENT_TL_SPOKEMST where  TMKD_TMKM_KEYID=TMKM_KEYID AND SKRM_KEYID=TMKD_SKRM_KEYID AND SPOK_KEYID(+)=tmkd_spoke_keyid AND TMKM_KEYID='"+mstkeyid+"' ");     
		}
		strBuf.append ( " ) A ) where 1=1 " );
		
		if(CommonFunctions.isValidKeyId(commonFilter.getFromRow()) && CommonFunctions.isValidKeyId(commonFilter.getToRow())){
			strBuf.append( " AND  RN >= "+commonFilter.getFromRow() + "  and RN <= "+commonFilter.getToRow());
		}
		strBuf.append( FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );

		strBuf.append ( " ORDER BY TMKD_KEYID " ); 
		return strBuf.toString();
	}

	public String newSelectlist(String flid, String unique) 
	{
		// TODO Auto-generated method stub		
		String sql=" SELECT  TMKM_KEYID From ENT_TL_TASKMST where  tmkm_flid = '"+flid+"' AND  TMKM_ROLE_KEYID='"+unique+"'" ;
		return sql;
	}


}

