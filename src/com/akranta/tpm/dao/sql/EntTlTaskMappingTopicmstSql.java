package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntTlTaskMappingTopicmstSql {

	public static final String TBL_ENT_TL_TASK_MAPPING_TOPICMST = "ENT_TL_TASK_MAPPING_TOPICMST";  

	TableFieldType [] tmtmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, role_keyid, topi_keyid, skrm_keyid, ksa, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, tempfield6
		, cutoffmark, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getTmtmDbFields() {
		return tmtmDbFields;
	}

	public EntTlTaskMappingTopicmstSql()
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

		tmtmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "TMTM_TEMPFIELD1";
		tmtmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

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
		return SqlUtils.getInsertSql(TBL_ENT_TL_TASK_MAPPING_TOPICMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TASK_MAPPING_TOPICMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TASK_MAPPING_TOPICMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getTaskTpSelectSql() {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from " + TBL_ENT_TL_TASK_MAPPING_TOPICMST + " where TMTM_KEYID = ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}

	public static String RpDetailGrid(CommonFilter commonFilter, String flid, String uniquePostion, String mstkeyid, String createmode, String topic) {
		// TODO Auto-generated method stub
		/*CommonMessage.debugMsg("uniquePostion "+uniquePostion);
		String sql="SELECT TMTD_KEYID,'','',TMKD_KEYID,TMKD_TASK,'' FROM ENT_TL_TASK_MAPPINGKSADTL,ENT_TL_TASK_MAPPING_TOPICDTL where 1=1 AND TMTD_TMKM_KEYID(+)=TMKD_KEYID ";      
        return sql;*/
		String sql="";
		/*if(CommonFunctions.isValidKeyId(mstkeyid))
		{
			
			sql+=" SELECT tmtd_keyid, '', DECODE(tmtd_keyid,'',0,1), tmkd_keyid,tmkd_task";
	        sql+=" FROM ent_tl_task_mappingksadtl,ent_tl_task_mapping_topicdtl,ent_tl_task_mappingksamst,ent_tl_task_mapping_topicmst";
		    sql+=" WHERE 1 = 1 AND tmkm_flid = '"+flid+"' AND TMKD_TMKM_KEYID =  TMKM_KEYID AND TMTD_TMTM_KEYID =  TMTM_KEYID(+)";
		    sql+=" AND TMTD_TMKM_KEYID(+) =  TMKD_KEYID AND tmkm_role_keyid = '"+uniquePostion+"' ";
			CommonMessage.debugMsg("master key id ");
			createmode="";
			sql="SELECT TMTD_KEYID,'',decode(tmtd_keyid,tmtd_keyid,1,0), tmkd_keyid, tmkd_task FROM ent_tl_task_mappingksadtl,ent_tl_task_mapping_topicdtl,ent_tl_task_mapping_topicmst WHERE 1=1 AND TMTD_TMTM_KEYID=TMTM_KEYID   AND TMTD_TMKM_KEYID=TMKD_KEYID AND TMTD_TMTM_KEYID='"+mstkeyid+"'";
		    CommonMessage.debugMsg("SQL::: "+sql);
			
		}		
		else 
		if(CommonFunctions.isValidKeyId(uniquePostion) || CommonFunctions.isValidKeyId(flid))
		{	**/
		sql+=" SELECT tmtd_keyid, '' as TICK, DECODE(TMTD_KEYID,'',0,1) as TICKVALUE, tmkd_keyid tmkdkeyid,tmkd_task";
        sql+=" FROM ent_tl_task_mappingksadtl,ent_tl_task_mapping_topicdtl,ent_tl_task_mappingksamst,ent_tl_task_mapping_topicmst";
	    sql+=" WHERE 1 = 1 AND tmkm_flid = '"+flid+"' AND TMKD_TMKM_KEYID =  TMKM_KEYID AND TMTD_TMTM_KEYID =  TMTM_KEYID(+)";
	    sql+=" AND TMTD_TMKD_KEYID(+) =  TMKD_KEYID AND tmkm_role_keyid = '"+uniquePostion+"' ";
			
	    if(CommonFunctions.isValidKeyId(createmode))
		{
	    	sql="SELECT tmtd_keyid, TICK,  TICKVALUE , tmkdkeyid,tmkd_task  FROM  ("+sql+") GROUP BY tmtd_keyid, TICK,  TICKVALUE , tmkdkeyid,tmkd_task  HAVING TICKVALUE <1";
		}
	    else 
	    {
	    	sql+=" AND TMTM_TOPI_KEYID ='"+topic+"' and TMTM_KEYID(+)='"+mstkeyid+"' ";
	    }
		    //return sql;
		//}		
	    /*if(uniquePostion !=null)
		{
		String sql=" SELECT tmtd_keyid,'','',tmkd_keyid, tmkd_task,'' FROM ent_tl_task_mappingksadtl,ent_tl_task_mappingksamst,ent_tl_task_mapping_topicdtl,GEN_TL_ROLEMST WHERE 1 = 1 AND tmkm_role_keyid = ROLE_KEYID AND tmkm_role_keyid = '"+uniquePostion+"'  and TMTD_TMKM_KEYID(+) =TMKM_KEYID and TMKD_TMKM_KEYID = TMKM_KEYID";
	    CommonMessage.debugMsg("Sql:::: "+sql);
	    return sql;
		}*/
		/*if(flid !=null)
		{
		String sql=" SELECT tmtd_keyid,'','',tmkd_keyid, tmkd_task,'' FROM ent_tl_task_mappingksadtl,ent_tl_task_mappingksamst,ent_tl_task_mapping_topicdtl WHERE 1 = 1  AND TMKM_FLID= '"+flid+"'  and TMTD_TMKM_KEYID(+) =TMKM_KEYID and TMKD_TMKM_KEYID = TMKM_KEYID";
	    CommonMessage.debugMsg("Sql:::: "+sql);
	    return sql;
		}	*/
		return sql;
	}
	
}

