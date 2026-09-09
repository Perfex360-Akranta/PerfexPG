package com.akranta.tpm.dao.sql;

import java.util.List;
import com.akranta.tpm.utils.CommonMessage;
public class JhaTlVisualsopmstSql {

	public static final String TBL_JHA_TL_VISUALSOPMST = "JHA_TL_VISUALSOPMST";  

	TableFieldType [] vsomDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flnid, equipmentid, productid, operation, safetyinstruction
		, effectofnoncompliance, preparedby, approvedby, issuedby, status
		, nextlevel, maintsection, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getVsomDbFields() {
		return vsomDbFields;
	}

	public JhaTlVisualsopmstSql()
	{
		vsomDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			vsomDbFields[ i ] = new TableFieldType();
		}
		vsomDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "VSOM_KEYID";
		vsomDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.flnid.ordinal() ].fieldName = "VSOM_FLNID";
		vsomDbFields[ tableFldConstants.flnid.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.equipmentid.ordinal() ].fieldName = "VSOM_EQUIPMENTID";
		vsomDbFields[ tableFldConstants.equipmentid.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.productid.ordinal() ].fieldName = "VSOM_PRODUCTID";
		vsomDbFields[ tableFldConstants.productid.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.operation.ordinal() ].fieldName = "VSOM_OPERATION";
		vsomDbFields[ tableFldConstants.operation.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.safetyinstruction.ordinal() ].fieldName = "VSOM_SAFETYINSTRUCTION";
		vsomDbFields[ tableFldConstants.safetyinstruction.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.effectofnoncompliance.ordinal() ].fieldName = "VSOM_EFFECTOFNONCOMPLIANCE";
		vsomDbFields[ tableFldConstants.effectofnoncompliance.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "VSOM_PREPAREDBY";
		vsomDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "VSOM_APPROVEDBY";
		vsomDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.issuedby.ordinal() ].fieldName = "VSOM_ISSUEDBY";
		vsomDbFields[ tableFldConstants.issuedby.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.status.ordinal() ].fieldName = "VSOM_STATUS";
		vsomDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		vsomDbFields[ tableFldConstants.nextlevel.ordinal() ].fieldName = "VSOM_NEXTLEVEL";
		vsomDbFields[ tableFldConstants.nextlevel.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.maintsection.ordinal() ].fieldName = "VSOM_MAINTSECTION";
		vsomDbFields[ tableFldConstants.maintsection.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "VSOM_TEMPFIELD4";
		vsomDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		vsomDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "VSOM_TEMPFIELD5";
		vsomDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		vsomDbFields[ tableFldConstants.active.ordinal() ].fieldName = "VSOM_ACTIVE";
		vsomDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		vsomDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "VSOM_CREATEDBY";
		vsomDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		vsomDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "VSOM_CREATEDON";
		vsomDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		vsomDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "VSOM_MODIFIEDON";
		vsomDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHA_TL_VISUALSOPMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHA_TL_VISUALSOPMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHA_TL_VISUALSOPMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getSingledata() {
		String sql = "select * from JHA_TL_VISUALSOPMST WHERE VSOM_KEYID = ? " ;
		return sql;
	}
	public static String getDeleteAllRecord(String KeyId) {
		String sql = "DELETE from JHA_TL_VISUALSOPDTL WHERE  VSOD_VSOM_KEYID = '"+KeyId+"'" ;
		return sql;
	}
	//For Main Report
	public static StringBuffer getVisualSopReport() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 'vsom_keyid'as vsom_keyid, 'Function Location' AS functionlocation,'Equipment' AS equipment,'Effect Of Non Complaince' AS effectofnoncomplaince,'Safety Instruction' AS safetyinstruction,");
		sql.append(" 'Prepared By' AS preparedby, 'Approved By' AS approvedby,'IssuedBy' AS issuedby");
		sql.append(" FROM DUAL UNION all");
		sql.append(" SELECT * FROM (  SELECT vsom_keyid , functionalloc  AS functionlocation, mchm_machinename AS equipment,");
		sql.append(" vsom_effectofnoncompliance AS effectofnoncomplaince, vsom_safetyinstruction AS safetyinstruction,a.empm_name AS preparedby, b.empm_name AS approvedby, c.empm_name AS issuedby");
		sql.append(" FROM jha_tl_visualsopmst,gen_vw_fnln,gen_tl_employeemst a,gen_tl_employeemst b,gen_tl_employeemst c");
		sql.append(" WHERE vsom_flnid = fnln_keyid(+)  AND vsom_preparedby = a.empm_keyid(+)AND vsom_approvedby = b.empm_keyid(+)AND vsom_issuedby = c.empm_keyid(+))");
		sql.append(" where 1=1");
		
		CommonMessage.debugMsg("getVisualSopReport" + sql );
		return sql;
	}

	public static StringBuffer getAllDetailRecord(String keyId) {
		StringBuffer sql = new StringBuffer();
		/*sql.append("SELECT 'vsod_keyid' AS vsod_keyid, 'Instruction' AS Instruction,'Key Point' AS KeyPoint,'Importance Of Key Point' AS ImportanceOfKeyPoint,'Tool Used' AS ToolUsed, 'Image' AS Image, 'PPE' AS PPE");
		sql.append(" ,'PPE Count' as PPEcount ,'vsodid' as vsodid FROM DUAL UNION ALL");
		sql.append(" SELECT * FROM (");
		sql.append(" SELECT vsod_keyid, vsod_instruction AS Instruction,vsod_keypoint AS KeyPoint,vsod_importanceofkeypoint AS ImportanceOfKeyPoint,vsod_toolused AS ToolUsed, imfl_filename AS Image,");
		sql.append(" vsod_imgppe AS ppe FROM jha_tl_visualsopdtl, gen_tl_allmoduleimgfile WHERE vsod_vsom_keyid = '");
		sql.append(keyId);
		sql.append("'");
		sql.append(" and VSOD_KEYID=IMFL_REFKEYID(+)) , ");
		sql.append(" (select to_char(count(distinct ppeid)) ppecount,vsod_keyid vsodid from ( ");
		sql.append(" select regexp_substr(vsod_imgppe,'[^,]+', 1, level) ppeid,vsod_keyid from jha_tl_visualsopdtl where vsod_imgppe <> '{}' ");
		sql.append(" connect by regexp_substr(vsod_imgppe, '[^,]+', 1, level) is not null)");
		sql.append(" group by vsod_keyid)");
		sql.append(" where 1=1 and vsodid(+) = vsod_keyid  ");*/
		//sql.append(" order by vsod_keyid ");

		/*sql.append(" SELECT 'vsod_keyid' AS vsod_keyid,'Instruction' AS Instruction,'Key Point' AS KeyPoint,'Importance Of Key Point' AS ImportanceOfKeyPoint, ");
		sql.append(" 'Tool Used' AS ToolUsed, 'Image' AS Image, 'PPE' AS PPE ,'PPE ?' as PPEcount ,'vsodid' as vsodid "); 
		sql.append(" FROM DUAL UNION ALL SELECT * FROM ( SELECT vsod_keyid, vsod_instruction AS Instruction,vsod_keypoint AS KeyPoint, ");
		sql.append(" vsod_importanceofkeypoint AS ImportanceOfKeyPoint, ");
		sql.append(" vsod_toolused AS ToolUsed, imfl_filename AS Image, vsod_imgppe AS ppe, CASE vsod_imgppe WHEN '{}' then 'No' else 'Yes	' end as PPEyes ,'' ");
		sql.append(" from jha_tl_visualsopdtl, gen_tl_allmoduleimgfile "); 
		sql.append(" WHERE vsod_vsom_keyid = '"+keyId+"' and VSOD_KEYID=IMFL_REFKEYID(+) ");
		sql.append(" ORDER BY vsod_keyid )");*/
		
		//sriram 26-oct-2025 change postgress compatible 
		sql.append(" SELECT 'vsod_keyid' AS vsod_keyid, ");
		sql.append(" 'Instruction' AS Instruction, ");
		sql.append(" 'Key Point' AS KeyPoint, ");
		sql.append(" 'Importance Of Key Point' AS ImportanceOfKeyPoint, ");
		sql.append(" 'Tool Used' AS ToolUsed, ");
		sql.append(" 'Image' AS Image, ");
		sql.append(" 'PPE' AS PPE, ");
		sql.append(" 'PPE ?' AS PPEcount, ");
		sql.append(" 'vsodid' AS vsodid ");
		sql.append(" UNION ALL ");
		sql.append(" SELECT * FROM ( ");
		sql.append("   SELECT vsod_keyid, vsod_instruction AS Instruction, vsod_keypoint AS KeyPoint, ");
		sql.append("   vsod_importanceofkeypoint AS ImportanceOfKeyPoint, ");
		sql.append("   vsod_toolused AS ToolUsed, imfl_filename AS Image, vsod_imgppe AS PPE, ");
		sql.append("   CASE WHEN vsod_imgppe = '{}' THEN 'No' ELSE 'Yes' END AS PPEyes, '' ");
		sql.append("   FROM jha_tl_visualsopdtl ");
		sql.append("   LEFT JOIN gen_tl_allmoduleimgfile ON VSOD_KEYID = IMFL_REFKEYID ");
		sql.append("   WHERE vsod_vsom_keyid = '" + keyId + "' ");
		sql.append("   ORDER BY vsod_keyid ");
		sql.append(" ) AS subquery ");

		
		CommonMessage.debugMsg("getAllDetailRecord" + sql );
		return sql;
	}

	
	public static String getPPERecord()
	{
		StringBuffer sql = new StringBuffer();
		//sriram 26-oct-2025 just removed (FROM DUAL)
//		sql.append(" SELECT 'Check'  ,'CHECKVAL' as CHECKVAL , 'KEYID' as  KEYID , 'Tool Name', 'Tool Image' ");
//		sql.append(" UNION ALL ");
//		sql.append("select 'Select','0', TOLM_KEYID KEYID , tolm_NAME tool ,TOIM_FILENAME  toolimage ");
//		sql.append("from GEN_TL_TOOLSmst,GEN_TL_TOOLSIMG where toim_keyid = tolm_keyid and tolm_type='P' ");
		
		
		
//		sql.append(" SELECT 'Check' AS checkcol, 'CHECKVAL' AS CHECKVAL, 'KEYID' AS KEYID, ");
//		sql.append("        'Tool Name' AS tool, 'Tool Image' AS toolimage ");
//		sql.append(" UNION ALL ");
//		sql.append(" SELECT 'Select', '0', tolm.tolm_keyid AS KEYID, tolm.tolm_name AS tool, toim.toim_filename AS toolimage ");
//		sql.append(" FROM gen_tl_toolsmst tolm ");
//		sql.append(" JOIN gen_tl_toolsimg toim ON toim.toim_keyid = tolm.tolm_keyid ");
//		sql.append(" WHERE tolm.tolm_type = 'P' ");

		
		sql.append(" SELECT 'Check' AS checkcol, 'CHECKVAL' AS CHECKVAL, 'KEYID' AS KEYID, ");
		sql.append("        'Tool Name' AS tool, 'Tool Image' AS toolimage ");
		sql.append(" UNION ALL ");
		sql.append(" SELECT 'Select', '0', tolm.tolm_keyid AS KEYID, tolm.tolm_name AS tool, i.toim_filename AS toolimage ");
		sql.append(" FROM gen_tl_toolsmst tolm ");
		sql.append(" LEFT JOIN gen_tl_toolsimg i ON i.toim_keyid = tolm.tolm_keyid ");
		sql.append(" WHERE tolm.tolm_type = 'P' ");


		
		CommonMessage.debugMsg("getPPERecord sql 111" + sql);
		return sql.toString();
	}

	public static StringBuffer getAllDetailRecordExcelResultset(String keyId) {
		StringBuffer sql=new StringBuffer();
		sql.append("Select");
		sql.append(" VSOD_KEYID,VSOD_INSTRUCTION as \"Instruction\" ,VSOD_KEYPOINT as \"Key Point\",VSOD_IMPORTANCEOFKEYPOINT as \"Importance Of Key Point\", ");
		sql.append(" VSOD_TOOLUSED as \"Tool Used\",VSOD_IMGPPE as \"Image\",VSOD_IMGPPE as PPE");
		sql.append(" from jha_tl_visualsopdtl where VSOD_VSOM_KEYID='");
		sql.append(keyId);
		sql.append("'");
		return sql;
	}

}

