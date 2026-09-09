package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.dao.sql.EntTlSkillmstSql.tableFldConstants;
import com.akranta.tpm.model.EntTlTrainingarea;

public class EntTlTrainingareaSql {

	TableFieldType [] trarDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, parentid, levelno, elementtype, refid, reftype, locationid
		, remarks, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, tempfield6, tempfield7, tempfield8, tempfield9, tempfield10
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getTrarDbFields() {
		return trarDbFields;
	}

	public EntTlTrainingareaSql()
	{
		trarDbFields = new TableFieldType[ 23 ];
		for(int i = 0;i < 23; i++)
		{	
			trarDbFields[ i ] = new TableFieldType();
		}
		trarDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TRAR_KEYID";
		trarDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.name.ordinal() ].fieldName = "TRAR_NAME";
		trarDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "TRAR_PARENTID";
		trarDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.levelno.ordinal() ].fieldName = "TRAR_LEVELNO";
		trarDbFields[ tableFldConstants.levelno.ordinal() ].fieldType = 'N';

		trarDbFields[ tableFldConstants.elementtype.ordinal() ].fieldName = "TRAR_ELEMENTTYPE";
		trarDbFields[ tableFldConstants.elementtype.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.refid.ordinal() ].fieldName = "TRAR_REFID";
		trarDbFields[ tableFldConstants.refid.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.reftype.ordinal() ].fieldName = "TRAR_REFTYPE";
		trarDbFields[ tableFldConstants.reftype.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.locationid.ordinal() ].fieldName = "TRAR_LOCATIONID";
		trarDbFields[ tableFldConstants.locationid.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "TRAR_REMARKS";
		trarDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "TRAR_TEMPFIELD1";
		trarDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "TRAR_TEMPFIELD2";
		trarDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "TRAR_TEMPFIELD3";
		trarDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "TRAR_TEMPFIELD4";
		trarDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "TRAR_TEMPFIELD5";
		trarDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "TRAR_TEMPFIELD6";
		trarDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "TRAR_TEMPFIELD7";
		trarDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "TRAR_TEMPFIELD8";
		trarDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "TRAR_TEMPFIELD9";
		trarDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "TRAR_TEMPFIELD10";
		trarDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TRAR_ACTIVE";
		trarDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		trarDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TRAR_CREATEDBY";
		trarDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		trarDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TRAR_CREATEDON";
		trarDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		trarDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TRAR_MODIFIEDON";
		trarDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TableNames.TBL_ENT_TL_TRAININGAREA, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TableNames.TBL_ENT_TL_TRAININGAREA, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TableNames.TBL_ENT_TL_TRAININGAREA ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getSelectSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "Select * from " + TableNames.TBL_ENT_TL_TRAININGAREA ;
		
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
	
	public static String getSearchNodeSql(String searchNode,String originalId) {		
		StringBuffer sb = new StringBuffer(); 
		//sb.append("select parentid from GEN_VW_FUNCLOCN where 1=1 ");
		sb.append("select CHILDPATH from ent_vw_trainingAreachildpath where 1=1 ");
		if(UIUtils.isValidKeyId(searchNode))
			sb.append(" and NAME = '"+searchNode+"'");
		if(UIUtils.isValidKeyId(originalId))
			sb.append(" and KEYID = '"+originalId+"'");
		return sb.toString();
	}
	public static String getChildSql(TableFieldType [] fieldTypeArr, Object [] dataArray,String start,String end) {
		// TODO Auto-generated method stub
		String keyFlag=null;
		keyFlag=(String)dataArray[ tableFldConstants.reftype.ordinal()];
		String sql = " select *  from  (";
		sql += "SELECT rownum AS slno, A.* FROM(";
		if(keyFlag.equals("SPK"))
			sql += "select SPOK_KEYID as keyId,'' as selectText,'' as chkFlg,SPOK_NAME as name,SPOK_CODE as code from ent_tl_spokemst ";
		if(keyFlag.equals("TOP"))
			sql += "select child.TOPI_KEYID as keyId,'' as selectText,'' as chkFlg,child.TOPI_NAME  as name" +
					",child.TOPI_CODE as code,child.TOPI_PARENTID as parentId ,parent.TOPI_NAME as parentName from ent_tl_topicmst child, " +
					"ent_tl_topicmst parent where parent.TOPI_KEYID=child.TOPI_PARENTID AND child.TOPI_ISCHILD='Y' ";
		
		if(keyFlag.equals("SPK")){
			sql+= " where SPOK_KEYID not in (select TRAR_REFID from " + TableNames.TBL_ENT_TL_TRAININGAREA;
			sql+=" where 1=1 ";
			if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.locationid.ordinal()]))
				sql+=" and TRAR_LOCATIONID='"+ (String)dataArray[ tableFldConstants.locationid.ordinal()] +"' ";
			if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.parentid.ordinal()]))
				sql+=" and TRAR_PARENTID='"+ (String)dataArray[ tableFldConstants.parentid.ordinal()] +"' ";
			sql+=" )";
		}
		
		if(keyFlag.equals("TOP"))
		{
			sql+= " and child.TOPI_KEYID not in (select TRAR_REFID from ENT_VW_TRAININGAREACHILDPATH ";
			sql+=" where 1=1 ";
			//if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.locationid.ordinal()]))
				//sql+=" and TRAR_LOCATIONID='"+ (String)dataArray[ tableFldConstants.locationid.ordinal()] +"' ";
			if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.parentid.ordinal()]))
				sql+=" and INSTR(CHILDPATH, '"+ (String)dataArray[ tableFldConstants.parentid.ordinal()] +"') > 0 ";
				//sql+=" and TRAR_PARENTID='"+ (String)dataArray[ tableFldConstants.parentid.ordinal()] +"' ";
			sql+=" )";
		}
		
		if(keyFlag.equals("SPK"))
			sql+= " order by SPOK_NAME";
		if(keyFlag.equals("TOP"))
			sql+= " order by child.TOPI_NAME";
		
		sql+= ")A ) where slno >= "+start+" and slno <= "+end;		
		
		sql+= " order by name";
		
		com.akranta.tpm.utils.CommonMessage.debugMsg(sql);
		return sql;		
	}
	
	public static String getAllChildSqlTotal(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String keyFlag=null;
		keyFlag=(String)dataArray[ tableFldConstants.reftype.ordinal()];
		String sql = " select count(*)  from  (";
		sql += "SELECT rownum AS slno, A.* FROM(";
		
		if(keyFlag.equals("SPK"))
			sql += "select SPOK_KEYID as keyId,'' as selectText,'' as chkFlg,SPOK_NAME as name,SPOK_CODE as code from ent_tl_spokemst ";
		if(keyFlag.equals("TOP"))
			sql += "select child.TOPI_KEYID as keyId,'' as selectText,'' as chkFlg,child.TOPI_NAME  as name" +
			",child.TOPI_CODE as code,child.TOPI_PARENTID as parentId ,parent.TOPI_NAME as parentName from ent_tl_topicmst child," +
			" ent_tl_topicmst parent where parent.TOPI_KEYID=child.TOPI_PARENTID AND child.TOPI_ISCHILD='Y' ";

		
		if(keyFlag.equals("SPK")){
			sql+= " where SPOK_KEYID not in (select TRAR_REFID from " + TableNames.TBL_ENT_TL_TRAININGAREA;
			sql+=" where 1=1 ";
			if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.locationid.ordinal()]))
				sql+=" and TRAR_LOCATIONID='"+ (String)dataArray[ tableFldConstants.locationid.ordinal()] +"' ";
			if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.parentid.ordinal()]))
				sql+=" and TRAR_PARENTID='"+ (String)dataArray[ tableFldConstants.parentid.ordinal()] +"' ";
			sql+=" )";
		}
		if(keyFlag.equals("TOP"))
		{
			sql+= " and child.TOPI_KEYID not in (select TRAR_REFID from ENT_VW_TRAININGAREACHILDPATH ";
			sql+=" where 1=1 ";
			//if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.locationid.ordinal()]))
				//sql+=" and TRAR_LOCATIONID='"+ (String)dataArray[ tableFldConstants.locationid.ordinal()] +"' ";
			if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.parentid.ordinal()]))
				sql+=" and INSTR(CHILDPATH, '"+ (String)dataArray[ tableFldConstants.parentid.ordinal()] +"') > 0 ";
				//sql+=" and TRAR_PARENTID='"+ (String)dataArray[ tableFldConstants.parentid.ordinal()] +"' ";
			sql+=" )";
		}
		
		if(keyFlag.equals("SPK"))
			sql+= " order by SPOK_NAME";
		if(keyFlag.equals("TOP"))
			sql+= " order by child.TOPI_NAME";		
		sql+= ")A )";		
		sql+= " order by name";
		com.akranta.tpm.utils.CommonMessage.debugMsg(sql);
		return sql;
	}
	
	public static String getTrAreaLevelSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuffer sql= new StringBuffer();		
		sql.append(" SELECT LEVEL ");		
		sql.append(" FROM " + TableNames.TBL_ENT_TL_TRAININGAREA  );
		sql.append(" WHERE " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'");
		sql.append(" START WITH TRAR_KEYID = TRAR_PARENTID   " );
		sql.append(" CONNECT BY nocycle prior TRAR_KEYID = TRAR_PARENTID  " );		
		return sql.toString();
	}
	
	public static String getTopicLevelSql(String keyId)
	{
		StringBuffer sql= new StringBuffer();		
		sql.append("  SELECT LEVEL ");		
		sql.append(" FROM " + TableNames.TBL_ENT_TL_TOPICMST  );
		sql.append(" WHERE TOPI_KEYID='"  +  keyId + "'");
		sql.append(" START WITH TOPI_KEYID = TOPI_PARENTID   " );
		sql.append(" CONNECT BY nocycle prior TOPI_KEYID = TOPI_PARENTID " );		
		return sql.toString();
	}
	
	public static String getSearchTopicLevelSql(String originalId) {		
		StringBuffer sb = new StringBuffer(); 
		sb.append("select  childpath,childpathname from ent_vw_topictreepath where 1=1 ");		
		if(UIUtils.isValidKeyId(originalId))
			sb.append(" and TOPI_KEYID = '"+originalId+"'");
		return sb.toString();
	}
	
	public static String getConfigTrAreaLevel()
	{
		StringBuffer sql= new StringBuffer();		
		sql.append(" SELECT CNFM_SETTINGVALUE ");	
		sql.append(" FROM " + TableNames.TBL_ADM_TL_CONFIGURATIONMST  );
		sql.append( " WHERE CNFM_CODE='ENTMAXSKILLLEVEL' AND SYSDATE BETWEEN CNFM_FROMDATE AND CNFM_TILLDATE " );		
		return sql.toString();
	}

	public static String getCount(EntTlTrainingarea entTlTrainingarea) {
		
		StringBuffer sql= new StringBuffer();		
		 
			
		sql.append(" SELECT COUNT(*) FROM ENT_VW_TRAININGTOPICREF WHERE TOPI_KEYID = '"+entTlTrainingarea.getTrarRefid()+"'");	
		sql.append(" AND INSTR('"+ entTlTrainingarea.getTrarParentid()+"',TRAR_KEYID)>0");
				
		return sql.toString();
	}
	
}

