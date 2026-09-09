package com.akranta.tpm.dao.sql;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.FactoryLayout;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlFunctionallocnSql {

	public static final String TBL_GEN_TL_FUNCTIONALLOCN = "GEN_TL_FUNCTIONALLOCN";  
 //   public static final String TBL_WOM_TL_WORKORDER_MST= "WOM_TL_WORKORDER_MST";
	TableFieldType [] fnlnDbFields = null;

	public enum   tableFldConstants
	{
		originalid, elementid, parentid, displaycode, description, elementtype
		, active,keyid
	}

	public TableFieldType[] getFnlnDbFields() {
		return fnlnDbFields;
	}

	public GenTlFunctionallocnSql()
	{
		fnlnDbFields = new TableFieldType[ 8 ];
		for(int i = 0;i < 8; i++)
		{	
			fnlnDbFields[ i ] = new TableFieldType();
		}
		fnlnDbFields[ tableFldConstants.originalid.ordinal() ].fieldName = "FNLN_ORIGINALID";
		fnlnDbFields[ tableFldConstants.originalid.ordinal() ].fieldType = 'V';

		fnlnDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "FNLN_ELEMENTID";
		fnlnDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		fnlnDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "FNLN_PARENTID";
		fnlnDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		fnlnDbFields[ tableFldConstants.displaycode.ordinal() ].fieldName = "FNLN_DISPLAYCODE";
		fnlnDbFields[ tableFldConstants.displaycode.ordinal() ].fieldType = 'V';

		fnlnDbFields[ tableFldConstants.description.ordinal() ].fieldName = "FNLN_DESCRIPTION";
		fnlnDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		fnlnDbFields[ tableFldConstants.elementtype.ordinal() ].fieldName = "FNLN_ELEMENTTYPE";
		fnlnDbFields[ tableFldConstants.elementtype.ordinal() ].fieldType = 'V';

		fnlnDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FNLN_ACTIVE";
		fnlnDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fnlnDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FNLN_KEYID";
		fnlnDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_FUNCTIONALLOCN, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
	/*String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_FUNCTIONALLOCN, fieldTypeArr, dataArray);*/
		
//		sql += " where " + fieldTypeArr[tableFldConstants.originalid.ordinal()].fieldName  +
//			  " = '" +  (String)dataArray[ tableFldConstants.originalid.ordinal() ] + "'";
//		return sql;
		String sql= "update GEN_TL_FUNCTIONALLOCN set  FNLN_ELEMENTID  ='"  +  (String)dataArray[ tableFldConstants.elementid.ordinal() ] + "' , FNLN_PARENTID = '"  +  (String)dataArray[ tableFldConstants.parentid.ordinal() ] + "'	"
				+ ", FNLN_DISPLAYCODE ='"  +  (String)dataArray[ tableFldConstants.displaycode.ordinal() ] + "'"
					+",	FNLN_DESCRIPTION  ='"  +  (String)dataArray[ tableFldConstants.description.ordinal() ] + "'"
							+",	FNLN_ELEMENTTYPE ='"  +  (String)dataArray[ tableFldConstants.elementtype.ordinal() ] + "'"
								+",FNLN_ACTIVE ='"  +  (String)dataArray[ tableFldConstants.active.ordinal() ] + "'";
					sql += " where " + fieldTypeArr[tableFldConstants.originalid.ordinal()].fieldName  +
					  " = '" +  (String)dataArray[ tableFldConstants.originalid.ordinal() ] + "'";
		return sql;

	}

	
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_FUNCTIONALLOCN ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.originalid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.originalid.ordinal()] + "'";
		return sql;
	}
	
	public static String getChildRecordCountSql(){
		
		return  " SELECT  COUNT(*)  FROM   ?  WHERE   INSTR( ? , ? ) > 0 ";  
	}
	public static String getParentElemSql(String elemId) {
		
		return "select distinct fnln_originalid from gen_tl_functionallocn where fnln_parentid ='" +elemId+"' and FNLN_ORIGINALID !='"+elemId+"'";
	}
	public static String getAddedElemSql(String elemId) {
		
		return "select distinct fnln_originalid from gen_tl_functionallocn where fnln_originalid like '%"+elemId+"%'";
		
	}
	public static String deleteNodeSql(String elemId) {
		return "DELETE FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ELEMENTID = '"+elemId+"'";
	}
	public static String delNodeSql(String orgId) {
		return "DELETE FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_originalID = '"+orgId+"'";
	}
	public static String getOriginalidFromParent(String parentId) {
		return "select fnln_originalid from gen_tl_functionallocn where fnln_parentid='"+parentId+"'";
	}
	public static String getOriginalidFromElemId(String elemId) {
		return "select fnln_originalid from gen_tl_functionallocn where fnln_elementid='"+elemId+"'";
	}
	public static String getSearchNodeSql(String searchNode,String originalId) {		
		StringBuffer sb = new StringBuffer(); 
		sb.append("select parentid from GEN_VW_FUNCLOCN where displaycode ='"+searchNode+"'");//like '%"+searchNode+"%'");
		if(UIUtils.isValidKeyId(originalId))
			sb.append(" and originalid = '"+originalId+"'");
		return sb.toString();
	}
	public static String getBdForEqp(String nodeId) {
		return "SELECT   BDMS_KEYID  FROM  BDM_TL_MST WHERE  BDMS_MACHINEID = '"+nodeId+"' AND  BDMS_STATUS != 'C' AND BDMS_DOWNTIME > '0'";
	}
	public static String CheckPmStnds(String nodeId) {
		return "SELECT   PMSD_KEYID  FROM  PLM_TL_STANDARDS WHERE  PMSD_MACHINEID ='"+nodeId+"'";
	}
	public static String getBdSql(String nodeId) {
		StringBuffer sb= new StringBuffer();
		sb.append("SELECT * FROM (  SELECT 'NO. OF STATIONS' AS DESCR,COUNT(*) AS COUNT,");
		sb.append("1 AS SORTORDER FROM BDM_VW_PHNCASLAYOUT WHERE INSTR(PCT_ELEMENTID,'"+nodeId+"') > 0 AND");
		sb.append(" PCT_ELEMENTTYPE = 'A' UNION  SELECT 'NO. OF MINOR STOPAGES' AS DESCR,");
		sb.append("COUNT(*) AS COUNT,2 AS SORTORDER FROM BDM_VW_PHNCASLAYOUT WHERE INSTR(PCT_ELEMENTID,'"+nodeId+"')> 0 ");
		sb.append("AND PCT_ELEMENTTYPE = 'MS' UNION  SELECT 'NO. OF PHYSICAL PHENOMENA' AS DESCR,");
		sb.append("COUNT(*) AS COUNT,3 AS SORTORDER FROM BDM_VW_PHNCASLAYOUT WHERE 	INSTR(PCT_ELEMENTID,'"+nodeId+"') > 0 ");
		sb.append("AND PCT_ELEMENTTYPE = 'PHN' UNION  SELECT 'ABN_TL_ABNORMALITY - RED TAGS' AS DESCR,");
		sb.append("COUNT(*) AS COUNT,4 AS SORTORDER FROM ABN_TL_ABNORMALITY WHERE ABNM_EQUIPMENTID = '"+nodeId+"'");
		sb.append(" AND ABNM_TAGCLASSID = 'R' UNION  SELECT 'ABN_TL_ABNORMALITY - WHITE TAGS' AS DESCR,COUNT(*) AS COUNT,");
		sb.append("5 AS SORTORDER FROM ABN_TL_ABNORMALITY WHERE ABNM_EQUIPMENTID = '"+nodeId+"'");
		sb.append(" AND ABNM_TAGCLASSID = 'W' UNION  SELECT 'BREAKDOWN' AS DESCR,COUNT(*) AS COUNT,");
		sb.append("9 AS SORTORDER FROM BDM_TL_MST WHERE BDMS_MACHINEID= 'MCH/00009' ) ORDER BY SORTORDER");
		
		CommonMessage.debugMsg("BD SQL : "+sb.toString());
		return sb.toString();
	}
	public static String updateMchSql() {
		String sql =  "UPDATE GEN_TL_FUNCTIONALLOCN  SET FNLN_PARENTID = ?,FNLN_ELEMENTID = ?";
			   sql +=" WHERE FNLN_ELEMENTID = ?";
		return sql;
	}
	public static String insertMchHistorySql() {
		String sql =  "INSERT INTO GEN_TL_MACHINEHISTORY VALUES(?,?,?,?,?,?,?,?,?,?,?,?)" ;
		return sql;
	}
	public static String updateMchChildSql() {
		StringBuffer sb= new StringBuffer();
		sb.append("UPDATE GEN_TL_FUNCTIONALLOCN SET FNLN_PARENTID = REPLACE(FNLN_PARENTID,?,?),");
		sb.append(" FNLN_ELEMENTID = REPLACE(FNLN_ELEMENTID,?,?) WHERE instr(FNLN_PARENTID,?) > 0");
		CommonMessage.debugMsg("MCH CHILD SQL : "+sb.toString());
		return sb.toString();
	}
	public static String updatePhenCauseLinkSql() {
		StringBuffer sb= new StringBuffer();
		sb.append("UPDATE "+TableNames.TBL_BDM_TL_PHENCAUSELINK+" SET BPCL_PARENTID  = REPLACE(BPCL_PARENTID ,?,?),");
		sb.append(" BPCL_ELEMENTID = REPLACE(BPCL_ELEMENTID,?,?) WHERE instr(BPCL_ELEMENTID,?) > 0");
		CommonMessage.debugMsg("Phen Cause SQL : "+sb.toString());
		return sb.toString();
	}
	public static String updateCellInMchSql() {
		
		CommonMessage.debugMsg("UPDATE "+TableNames.TBL_GEN_TL_MACHINEMST+" SET MCHM_CELLID = ? WHERE MCHM_KEYID = ?)");
		return "UPDATE "+TableNames.TBL_GEN_TL_MACHINEMST+" SET MCHM_CELLID = ? WHERE MCHM_KEYID = ?";
	}
	public static String updateABNSql() {
		
		String sql =  "UPDATE "+TableNames.TBL_ABN_TL_ABNORMALITY+" SET ABNM_SECTIONID = ?,ABNM_CELLID  = ?";
		   sql +=" WHERE ABNM_EQUIPMENTID  = ?";
		   CommonMessage.debugMsg("ABN : "+sql);
		   return sql;
	}
	public static String updatePLMSql() {
		
		String sql =  "UPDATE "+TableNames.TBL_PLM_TL_STANDARDS+" SET PMSD_FACTORYID = ?,PMSD_SECTIONID   = ?";
			   sql +=",PMSD_CELLID = ? WHERE PMSD_MACHINEID   = ?";
			   CommonMessage.debugMsg("PLM : "+sql);
		   return sql;
	}
	public static String updatePLCalSql() {
		
		String sql =  "UPDATE "+TableNames.TBL_PLM_TL_CALENDAR+" SET PMCL_FACTORYID = ?,PMCL_SECTIONID = ?";
			   sql +=",PMCL_CELLID = ? WHERE PMCL_MACHINEID   = ?";
			   CommonMessage.debugMsg("PLCAL : "+sql);
		   return sql;
	}
	public static String updateKaizenSql() {
		
		String sql =  "UPDATE "+TableNames.TBL_KZN_TL_MST+" SET KZNM_FACTORYID = ?,KZNM_SECTIONID = ?";
			   sql +=",KZNM_CELLID = ? WHERE KZNM_MACHINEID   = ?";
			   CommonMessage.debugMsg("KAIZEN : "+sql);
		   return sql;
	}
	public static String updateKaizenHdSql() {
		
		String sql =  "UPDATE "+TableNames.TBL_KZN_TL_HDMST+" SET KHDM_FACTORYID = ?,KHDM_SECTIONID = ?";
			   sql +=",KHDM_CELLID = ? WHERE KHDM_MACHINEID   = ?";
			   CommonMessage.debugMsg("KAIZEN-HD : "+sql);
		   return sql;
	}
	public static String updateOplSql() {
		
		String sql =  "UPDATE "+TableNames.TBL_OPL_TL_MST+" SET OPLM_FACTORYID = ?,OPLM_SECTIONID = ?";
			   sql +=",OPLM_CELLID = ? WHERE OPLM_MACHINEID   = ?";
			   CommonMessage.debugMsg("OPL : "+sql);
		   return sql;
	}
	public static String updateBDStatusSql() {
		CommonMessage.debugMsg("UPDATE "+TableNames.TBL_BDM_TL_MST+" SET BDMS_ACTIVE = 'N' WHERE BDMS_MACHINEID = ?");
		return "UPDATE "+TableNames.TBL_BDM_TL_MST+" SET BDMS_ACTIVE = 'N' WHERE BDMS_MACHINEID = ?";
	}
	public static String updateCycletimeMstSql() {
		String sql =  "UPDATE "+TableNames.TBL_PCS_TL_CYCLETIMEMST+" SET CYTM_FACTORYID = ?,CYTM_SECTIONID = ?";
		   sql +=",CYTM_CELLID = ? WHERE CYTM_MACHINEID   = ?";
		   CommonMessage.debugMsg("OPL : "+sql);
	   return sql;
	}
	
 public static String updateWorkordersql(){
//	String sql = "UPDATE "+TBL_WOM_TL_WORKORDER_MST+" SET WOMS_CELLID = ?  WHERE WOMS_MACHINEID = ?"; 
	
	String sql =  "UPDATE WOM_TL_WORKORDER_MST  SET WOMS_CELLID = ?";
	   sql +=" WHERE WOMS_MACHINEID = ?";
	return sql;
}	

 public static String getChildSql(int size,String keyFlag,String start,String end,String key,GridParams gridParams) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("getChildSql  :"+keyFlag +"key  "+key);
		String sql = " select *  from  (";
		sql += "SELECT rownum AS slno, A.* FROM(";
		if(keyFlag.equals("LCN"))
			sql += " select locn_keyid,'' as temp1,'',locn_name AS TXTDISPLAYCODE,locn_code AS TXTDESCRIPTION from gen_tl_locationmst where locn_active='Y' and locn_keyid ";
		else if(keyFlag.equals("SBU")){
			sql += "select sbut_keyid,'' as temp1,'',sbut_name AS TXTDISPLAYCODE,sbut_code AS TXTDESCRIPTION from gen_tl_sbumst where sbut_active='Y' and sbut_keyid ";
			sql += " not in( select FNLN_ORIGINALID from GEN_TL_FUNCTIONALLOCN where FNLN_ELEMENTTYPE = 'SBU' ) and "; 
			if(CommonFunctions.isValidKeyId(key))
				sql += " sbut_keyid='"+key+"' AND ";
			sql += " sbut_keyid ";
		}	
		else if(keyFlag.equals("PBU")){
			sql += "select pbut_keyid,'' as temp1,'',pbut_name AS TXTDISPLAYCODE,pbut_code AS TXTDESCRIPTION from gen_tl_pbumst where pbut_active ='Y' and pbut_keyid ";
			sql += " not in( select FNLN_ORIGINALID from GEN_TL_FUNCTIONALLOCN where FNLN_ELEMENTTYPE = 'PBU' ) and  ";
			if(CommonFunctions.isValidKeyId(key))
				sql += " pbut_keyid='"+key+"' AND ";
			sql += " pbut_keyid";
		}
		else if(keyFlag.equals("FCT"))
			sql += "select fact_keyid,'' as temp1,'',fact_name AS TXTDISPLAYCODE,fact_code AS TXTDESCRIPTION from gen_tl_factorymst where fact_keyid";
		else if(keyFlag.equals("LIN"))
		{
			sql += "select sect_keyid,'' as temp1,'',sect_name AS TXTDISPLAYCODE,sect_code AS TXTDESCRIPTION from gen_tl_sectionmst where sect_active='Y' and sect_keyid " ;
			sql += " not in( select FNLN_ORIGINALID from GEN_TL_FUNCTIONALLOCN where FNLN_ELEMENTTYPE = 'L' ) and ";
			if(CommonFunctions.isValidKeyId(key))
				sql += " sect_keyid='"+key+"' AND";
			sql += " sect_keyid";
		}
		else if(keyFlag.equals("CEL"))
		{
			sql += "select cell_keyid,'' as temp1,'',cell_name AS TXTDISPLAYCODE,cell_code AS TXTDESCRIPTION from gen_tl_cellmst where ";
			if(CommonFunctions.isValidKeyId(key))
				sql += " cell_keyid ='"+key+"'";
			sql += " cell_keyid";
		}
		else if(keyFlag.equals("TEM"))
		{
			sql += "select team_keyid,'' as temp1,'',team_name AS TXTDISPLAYCODE,team_code AS TXTDESCRIPTION from gen_tl_teammst where";
			if(CommonFunctions.isValidKeyId(key))
				sql += "  team_keyid='"+key+"'";
			sql += "  team_keyid";
		}
		else if(keyFlag.equals("MCH"))
		{
			sql += "select MCHM_KEYID,'' as temp1,'',MCHM_MACHINENAME AS TXTDISPLAYCODE,MCHM_MACHINENO AS TXTDESCRIPTION from gen_tl_MACHINEmst where MCHM_ACTIVE='Y'";
			if(CommonFunctions.isValidKeyId(key))
				sql += " AND MCHM_keyid ='"+key+"'";
			sql += " AND MCHM_keyid";
		}
		else if(keyFlag.equals("ASM"))
		{
			sql += "select ASSM_KEYID,'' as temp1,'',ASSM_NAME AS TXTDISPLAYCODE,ASSM_CODE AS TXTDESCRIPTION from gen_tl_ASSEMBLYmst where";
			if(CommonFunctions.isValidKeyId(key))
				sql += " ASSM_keyid='"+key+"' AND";
			
			sql += " ASSM_KEYID";
		}
		else if(keyFlag.equals("SPR"))
		{
			sql += "select SPRM_KEYID,'' as temp1,'',SPRM_PARTNAME AS TXTDISPLAYCODE,SPRM_PARTNO AS TXTDESCRIPTION from gen_tl_SPARESmst where";
			if(CommonFunctions.isValidKeyId(key))
			sql += " SPRM_KEYID='"+key+"' AND";
		
			sql += " SPRM_KEYID";
		}
		else if(keyFlag.equals("SBA"))
			sql += "select SBAM_KEYID,'' as temp1,'',SBAM_NAME AS TXTDISPLAYCODE,SBAM_CODE AS TXTDESCRIPTION from gen_tl_SUBASSEMBLYmst where SBAM_keyid";
		
		/*if(keyFlag.equals("CEL"))
			sql += "select cell_keyid,'','',cell_name,cell_code from gen_tl_cellmst where cell_keyid";*/
		sql+= " not in (";
		for(int i=0;i<size;i++)
			sql+= "?,";
		
		
		sql = sql.substring(0, sql.length()-1)+")";
		
		if(keyFlag.equals("SPR"))
			sql+= " order by sprm_partname";
		else if(keyFlag.equals("ASM"))
			sql+= " order by ASSM_name";
		
		sql+= ")A";
		String condSql ="";
		if( gridParams != null)
		{
			condSql = FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
			sql += " WHERE 1=1 "+condSql;
		}
		sql += ") where slno >= "+start+" and slno <= "+end;
		
		if(keyFlag.equals("SPR"))
			sql+= " order by TXTDISPLAYCODE";
		if(keyFlag.equals("ASM"))
			sql+= " order by TXTDISPLAYCODE";
		
		return sql;
	}
	public static String getAllChildSql(String keyFlag,String start,String end,String key,GridParams gridParams)
	{
		String sql = " select *  from  (";
		sql += "SELECT rownum AS slno, A.* FROM(";
		/*if(keyFlag.substring(0,3).equals("LCN"))
			sql += "select locn_keyid,'','',locn_name,locn_code from gen_tl_locationmst";
		if(keyFlag.substring(0,3).equals("FCT"))*/
		keyFlag = keyFlag.length()==3?keyFlag:keyFlag.substring(0, 3);
		CommonMessage.debugMsg("getAllChildSql  :"+keyFlag);
		if(keyFlag.equals("LCN")){
			sql += "select sbut_keyid,'' as temp1,'',sbut_name AS TXTDISPLAYCODE,sbut_code AS TXTDESCRIPTION from gen_tl_sbumst ";
			sql += " where sbut_active='Y' and sbut_keyid not in( select FNLN_ORIGINALID from GEN_TL_FUNCTIONALLOCN where FNLN_ELEMENTTYPE = 'SBU' )  ";
			if(CommonFunctions.isValidKeyId(key))
				sql += " and  sbut_keyid='"+key+"'";
			//sql += "select fact_keyid,'' as temp1,'',fact_name AS TXTDISPLAYCODE,fact_code AS TXTDESCRIPTION from gen_tl_factorymst";
		}
		else if(keyFlag.equals("SBU")){
			sql += "select pbut_keyid,'' as temp1,'',pbut_name AS TXTDISPLAYCODE,pbut_code AS TXTDESCRIPTION from gen_tl_pbumst ";
			sql += " where pbut_active='Y' and pbut_keyid not in( select FNLN_ORIGINALID from GEN_TL_FUNCTIONALLOCN where FNLN_ELEMENTTYPE = 'PBU' )  ";
			if(CommonFunctions.isValidKeyId(key))
				sql += " and pbut_keyid='"+key+"'";
		}
		else if(keyFlag.equals("PBU"))
		{
			sql += "select sect_keyid,'' as temp1,'',sect_name AS TXTDISPLAYCODE,sect_code AS TXTDESCRIPTION from gen_tl_sectionmst ";
			sql += " where sect_active='Y' and sect_keyid not in( select FNLN_ORIGINALID from GEN_TL_FUNCTIONALLOCN where FNLN_ELEMENTTYPE = 'L' )  ";
			if(CommonFunctions.isValidKeyId(key))
				sql += " and sect_keyid='"+key+"'";
		}
		else if(keyFlag.equals("LIN"))
		{
			sql += "select cell_keyid,'' as temp1,'',cell_name AS TXTDISPLAYCODE,cell_code AS TXTDESCRIPTION from gen_tl_cellmst";
			if(CommonFunctions.isValidKeyId(key))
				sql += " where cell_keyid='"+key+"'";
		}
		else if(keyFlag.equals("TEA"))
		{
			sql += "select team_keyid,'' as temp1,'',team_name AS TXTDISPLAYCODE,team_code AS TXTDESCRIPTION from gen_tl_teammst";
			if(CommonFunctions.isValidKeyId(key))
				sql += " where team_keyid='"+key+"'";
		}
		else if(keyFlag.equals("MCH"))
		{
			sql += "select MCHM_KEYID,'' as temp1,'',MCHM_MACHINENAME AS TXTDISPLAYCODE,MCHM_MACHINENO AS TXTDESCRIPTION from gen_tl_MACHINEmst where MCHM_ACTIVE='Y' ";
			sql += " and mchm_keyid not in( select FNLN_ORIGINALID from GEN_TL_FUNCTIONALLOCN where FNLN_ELEMENTTYPE = 'M' )  ";
			if(CommonFunctions.isValidKeyId(key))
				sql += " AND MCHM_KEYID='"+key+"'";
		}
		else if(keyFlag.equals("ASM"))
		{
			sql += "select ASSM_KEYID,'' as temp1,'',ASSM_NAME AS TXTDISPLAYCODE,ASSM_CODE AS TXTDESCRIPTION from gen_tl_ASSEMBLYmst";
			if(CommonFunctions.isValidKeyId(key))
				sql += " where ASSM_KEYID='"+key+"'";
		}
		else if(keyFlag.equals("SPR"))
		{
			sql += "select SPRM_KEYID,'' as temp1,'',SPRM_PARTNAME AS TXTDISPLAYCODE,SPRM_PARTNO AS TXTDESCRIPTION from gen_tl_SPARESmst";
			if(CommonFunctions.isValidKeyId(key))
				sql += " where SPRM_KEYID='"+key+"'";
		}
		else if(keyFlag.equals("SBA"))
			sql += "select SBAM_KEYID,'' as temp1,'',SBAM_NAME AS TXTDISPLAYCODE,SBAM_CODE AS TXTDESCRIPTION from gen_tl_SUBASSEMBLYmst";
		
		if(keyFlag.equals("SPR"))
			sql+= " order by sprm_partname";
		else if(keyFlag.equals("ASM"))
			sql+= " order by ASSM_name";
		
		sql+= ")A";
		
		String condSql ="";
		if( gridParams != null)
		{
			condSql = FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
			sql += " WHERE 1=1 "+condSql;
		}
		sql+= ") where slno >= "+start+" and slno <= "+end;
		
		if(keyFlag.equals("SPR"))
			sql+= " order by TXTDISPLAYCODE";
		else if(keyFlag.equals("ASM"))
			sql+= " order by TXTDISPLAYCODE";
		
		return sql;
	}
	
	public static String getTotalChildSql(int size,String keyFlag) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("keyFlag  :"+keyFlag +" size  :"+size);
		String sql = " select count(*)  from  (";
		sql += "SELECT rownum AS slno, A.* FROM(";
		if(keyFlag.equals("LCN"))
			sql += "select locn_keyid,'' as temp1,'',locn_name,locn_code from gen_tl_locationmst where locn_active= 'Y' and locn_keyid  ";
		else if(keyFlag.equals("SBU"))
			sql += "select sbut_keyid,'' as temp1,'',sbut_name,sbut_code from gen_tl_sbumst where sbut_active='Y' and sbut_keyid ";
		else if(keyFlag.equals("PBU"))
			sql += "select pbut_keyid,'' as temp1,'',pbut_name,pbut_code from gen_tl_pbumst where pbut_active ='Y' and pbut_keyid ";
		else if(keyFlag.equals("FCT"))
			sql += "select fact_keyid,'' as temp1,'',fact_name,fact_code from gen_tl_factorymst where fact_keyid";
		else if(keyFlag.equals("LIN"))
			sql += "select sect_keyid,'' as temp1,'',sect_name,sect_code from gen_tl_sectionmst where sect_keyid";
		else if(keyFlag.equals("CEL"))
			sql += "select cell_keyid,'' as temp1,'',cell_name,cell_code from gen_tl_cellmst where cell_keyid";
		else if(keyFlag.equals("TEM"))
			sql += "select team_keyid,'' as temp1,'',team_name,team_code from gen_tl_teammst where team_keyid";
		else if(keyFlag.equals("MCH"))
			sql += "select MCHM_KEYID,'' as temp1,'',MCHM_MACHINENAME,MCHM_MACHINENO from gen_tl_MACHINEmst where MCHM_ACTIVE='Y' AND MCHM_keyid";
		else if(keyFlag.equals("ASM"))
			sql += "select ASSM_KEYID,'' as temp1,'',ASSM_NAME,ASSM_CODE from gen_tl_ASSEMBLYmst where ASSM_keyid";
		else if(keyFlag.equals("SPR"))
			sql += "select SPRM_KEYID,'' as temp1,'',SPRM_PARTNAME,SPRM_PARTNO from gen_tl_SPARESmst where SPRM_keyid";
		else if(keyFlag.equals("SBA"))
			sql += "select SBAM_KEYID,'' as temp1,'',SBAM_NAME,SBAM_CODE from gen_tl_SUBASSEMBLYmst where SBAM_keyid";
		
		/*if(keyFlag.equals("CEL"))
			sql += "select cell_keyid,'','',cell_name,cell_code from gen_tl_cellmst where cell_keyid";*/
		sql+= " not in (";
		for(int i=0;i<size;i++)
			sql+= "?,";
		
		
		sql = sql.substring(0, sql.length()-1)+")";
		if(keyFlag.equals("SPR"))
			sql+= " order by sprm_partname";
		else if(keyFlag.equals("ASM"))
			sql+= " order by ASSM_name";
		
		sql+= ")A )";
		
		if(keyFlag.equals("SPR"))
			sql+= " order by sprm_partname";
		else if(keyFlag.equals("ASM"))
			sql+= " order by ASSM_name";
		
		return sql;
	}
	
	public static String getAllChildSqlTotal(String keyFlag)
	{
		CommonMessage.debugMsg("keyFlag  ::::"+keyFlag);
		String sql = " select count(*)  from  (";
		sql += "SELECT rownum AS slno, A.* FROM(";
		/*if(keyFlag.substring(0,3).equals("LCN"))
			sql += "select locn_keyid,'','',locn_name,locn_code from gen_tl_locationmst";
		if(keyFlag.substring(0,3).equals("FCT"))*/
		keyFlag = keyFlag.length()==3?keyFlag:keyFlag.substring(0, 3);
		CommonMessage.debugMsg("keyFlag  >>>:"+keyFlag);
		if(keyFlag.equals("LCN")){
			sql += "select SBUT_KEYID,'' as temp1,'',SBUT_name,SBUT_code from gen_tl_sbumst";
			//sql += "select fact_keyid,'' as temp1,'',fact_name,fact_code from gen_tl_factorymst";
		}	
		else if(keyFlag.equals("SBU"))
			sql += "select PBUT_keyid,'' as temp1,'',PBUT_name,PBUT_code from gen_tl_pbumst";
		else if(keyFlag.equals("PBU"))
			sql += "select sect_keyid,'' as temp1,'',sect_name,sect_code from gen_tl_sectionmst";		
		else if(keyFlag.equals("LIN"))
			sql += "select cell_keyid,'' as temp1,'',cell_name,cell_code from gen_tl_cellmst";
		else if(keyFlag.equals("TEA"))
			sql += "select team_keyid,'' as temp1,'',team_name,team_code from gen_tl_teammst";
		else if(keyFlag.equals("MCH"))
			sql += "select MCHM_KEYID,'' as temp1,'',MCHM_MACHINENAME,MCHM_MACHINENO from gen_tl_MACHINEmst where MCHM_ACTIVE='Y'";
		else if(keyFlag.equals("ASM"))
			sql += "select ASSM_KEYID,'' as temp1,'',ASSM_NAME,ASSM_CODE from gen_tl_ASSEMBLYmst";
		else if(keyFlag.equals("SPR"))
			sql += "select SPRM_KEYID,'' as temp1,'',SPRM_PARTNAME,SPRM_PARTNO from gen_tl_SPARESmst";
		else if(keyFlag.equals("SBA"))
			sql += "select SBAM_KEYID,'' as temp1,'',SBAM_NAME,SBAM_CODE from gen_tl_SUBASSEMBLYmst";
		
		if(keyFlag.equals("SPR"))
			sql+= " order by sprm_partname";
		else if(keyFlag.equals("ASM"))
			sql+= " order by ASSM_name";
		
		sql+= ")A )";
		
		if(keyFlag.equals("SPR"))
			sql+= " order by sprm_partname";
		else if(keyFlag.equals("ASM"))
			sql+= " order by ASSM_name";
		
		return sql;
	}
	
	public static String getDeleteFunLocSql(String delMode,TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		 CommonMessage.debugMsg("inside getDeleteFunLocSql");
		 String sql ="";
		
		if (delMode.equals("I")) {  
			sql = "UPDATE " + TBL_GEN_TL_FUNCTIONALLOCN ;		
			sql += " SET " + fieldTypeArr[tableFldConstants.active.ordinal()].fieldName  +" = 'N'";
			sql += " where " + fieldTypeArr[tableFldConstants.originalid.ordinal()].fieldName  +
				  " = '" +  (String)dataArray[ tableFldConstants.originalid.ordinal()] + "'";		
			
		}
		else  {			
			
			sql = "DELETE from " + TBL_GEN_TL_FUNCTIONALLOCN ;		
			sql += " where " + fieldTypeArr[tableFldConstants.originalid.ordinal()].fieldName  +
				  " = '" +  (String)dataArray[ tableFldConstants.originalid.ordinal()] + "'";
			
		}			
		return sql;
	}
	
	//Added by Madhan
	public static String getReturnFieldByElementType(String elementType){
		if("CMP".equals(elementType)) {
			return "comp_active";
		}else if("LCN".equals(elementType)) {
			return "locn_active";
		}else if("SBU".equals(elementType)) {
			return "sbut_active";
		}else if("PBU".equals(elementType)) {
			return "pbut_active";
		}else if("L".equals(elementType)) {
			return "sect_active";
		}else if("C".equals(elementType)) {
			return "cell_active";
		}else if("M".equals(elementType)) {
			return "mchm_active";
		}
		
		return  "";
	}
	
	public static String getCheckFieldByElementType(String elementType){
		if("CMP".equals(elementType)) {
			return "comp_keyid";
		}else if("LCN".equals(elementType)) {
			return "locn_keyid";
		}else if("SBU".equals(elementType)) {
			return "sbut_keyid";
		}else if("PBU".equals(elementType)) {
			return "pbut_keyid";
		}else if("L".equals(elementType)) {
			return "sect_keyid";
		}else if("C".equals(elementType)) {
			return "cell_keyid";
		}else if("M".equals(elementType)) {
			return "mchm_keyid";
		}
		
		return  "";
	}
	
	public static String getTableNamesByElementTypeSql(String elementType){
		if("CMP".equals(elementType)) {
			return TableNames.TBL_GEN_TL_COMPANYMST;
		}else if("LCN".equals(elementType)) {
			return TableNames.TBL_GEN_TL_LOCATIONMST;
		}else if("SBU".equals(elementType)) {
			return TableNames.TBL_GEN_TL_SBUMST;
		}else if("PBU".equals(elementType)) {
			return TableNames.TBL_GEN_TL_PBUMST;
		}else if("L".equals(elementType)) {
			return TableNames.TBL_GEN_TL_SECTIONMST;
		}else if("C".equals(elementType)) {
			return TableNames.TBL_GEN_TL_CELLMST;
		}else if("M".equals(elementType)) {
			return TableNames.TBL_GEN_TL_MACHINEMST;
		}
		
		return  "";
	}  // Added by madhan
	
	public static String getFactoryLayoutElementsSql(FactoryLayout factoryLayout ){
		StringBuffer sql = new StringBuffer();
		StringBuffer joinCondSql = new StringBuffer();
		
		
		String machkeyid = factoryLayout.getMachine() != null ?  factoryLayout.getMachine().getKeyid(): null;
		boolean isValidMachId = CommonFunctions.isValidKeyId(machkeyid);
		String cellId = factoryLayout.getCell() != null ?  factoryLayout.getCell().getKeyid(): null;
		boolean isValidCell = CommonFunctions.isValidKeyId(cellId);
		String sectId = factoryLayout.getSection() != null ?  factoryLayout.getSection().getKeyid(): null;
		boolean isValidSectId = CommonFunctions.isValidKeyId(sectId);			
		String pbuId = factoryLayout.getPbu() != null ?  factoryLayout.getPbu().getKeyid(): null;
		boolean isValidfPbuId = CommonFunctions.isValidKeyId(pbuId);			
		String sbuId = factoryLayout.getSbu() != null ?  factoryLayout.getSbu().getKeyid(): null;
		boolean isValidfSbuId = CommonFunctions.isValidKeyId(sbuId);			
		//fact// String factId = factoryLayout.getFactory() != null ?  factoryLayout.getFactory().getKeyid(): null;
		//fact// boolean isValidfFactId = CommonFunctions.isValidKeyId(factId);
		String locnId = factoryLayout.getLocation() != null ?  factoryLayout.getLocation().getKeyid(): null;
		boolean isValidLocnIdId = CommonFunctions.isValidKeyId(locnId);
		String compId = factoryLayout.getCompany() != null ?  factoryLayout.getCompany().getKeyid(): null;
		boolean isValidCompId = CommonFunctions.isValidKeyId(compId);
		String flId = factoryLayout.getFlid() != null ?  factoryLayout.getFlid().getKeyid(): null;
		boolean isValidFlId = CommonFunctions.isValidKeyId(flId);
		//fact// if( ! isValidMachId && ! isValidCell && ! isValidSectId && ! isValidfFactId && ! isValidLocnIdId  && ! isValidCompId && !isValidFlId )
		if( ! isValidMachId && ! isValidCell && !isValidfPbuId && ! isValidfSbuId && ! isValidSectId && ! isValidLocnIdId  && ! isValidCompId && !isValidFlId )
			return "";
		
		//sql.append(" SELECT DISTINCT COMP_KEYID compId , COMP_CODE, COMP_NAME, ");
		sql.append(" SELECT  COMP_KEYID compId , COMP_CODE, COMP_NAME, ");
		//joinCondSql.append(" COMP_KEYID");
		
		/*if( ! isValidMachId && ! isValidCell && ! isValidSectId && ! isValidfFactId && ! isValidLocnIdId )
			sql.append(" '' locaId , '' LOCN_CODE, '' LOCN_NAME, ");
		else{*/
			sql.append(" LOCN_KEYID locaId , LOCN_CODE, LOCN_NAME, ");
		//	joinCondSql.append("||'-'||LOCN_KEYID");
		//}	
		
		
		/*if( ! isValidMachId && ! isValidCell && ! isValidSectId && ! isValidfFactId )
			sql.append(" '' factId , '' FACT_CODE, '' FACT_NAME, ");				
		else{*/
		//	sql.append(" FACT_KEYID factId , FACT_CODE, FACT_NAME, ");
		//	joinCondSql.append("||'-'||FACT_KEYID");
		//}	
		
			sql.append(" SBUT_KEYID sbuId , SBUT_CODE, SBUT_NAME, ");
			//joinCondSql.append("||'-'||SBUT_KEYID");
			
			sql.append(" PBUT_KEYID pbuId , PBUT_CODE, PBUT_NAME, ");
			//joinCondSql.append("||'-'||PBUT_KEYID");
		/*if( ! isValidMachId && ! isValidCell && ! isValidSectId )
			sql.append(" '' sectId ,''  SECT_CODE, '' SECT_NAME, ");				
		else{*/
			sql.append(" SECT_KEYID sectId , SECT_CODE, SECT_NAME, ");
			//joinCondSql.append("||'-'||SECT_KEYID");
	//	}	
		
		/*if( ! isValidMachId && ! isValidCell)
			sql.append(" '' cellId , '' CELL_CODE, '' CELL_NAME, ");				
		else{*/
			sql.append(" CELL_KEYID cellId , CELL_CODE, CELL_NAME, ");
			//joinCondSql.append("||'-'||CELL_KEYID");
		//}	
		/*if( isValidMachId ){*/
			sql.append(" MCHM_KEYID machId , MCHM_MACHINENO, MCHM_MACHINENAME ");
			//joinCondSql.append("||'-'||MCHM_KEYID");
			
		/*}	
		else
			sql.append(" '' machId , '' MCHM_MACHINENO,'' MCHM_MACHINENAME ");
		*/
			sql.append(" ,FNLN_KEYID flId,FNLN_ELEMENTID,functionalloc,DISPLAYCODE ") ;	
			
		sql.append(" FROM ");
		//sql.append( TableNames.TBL_GEN_VW_FACTORYLAYOUT   );
		sql.append( TableNames.TBL_GEN_VW_FNLN );
		if( isValidFlId){
			//sql.append(","+TableNames.TBL_GEN_TL_FUNCTIONALLOCN );
			//sql.append(" WHERE FNLN_ELEMENTID = ");
			//sql.append(joinCondSql);
			
			sql.append(" WHERE FNLN_KEYID ='"+flId+"'");
		}
		else{
			sql.append(" WHERE 1 = 1 ");
		
	/*	if(CommonFunctions.isValidKeyId( factoryLayout.getElementId() ))
			sql.append(" AND FNLN_ELEMENTID = '" + factoryLayout.getElementId() +"'" );
		*/	if(CommonFunctions.isValidKeyId( factoryLayout.getOriginalId() ))
				sql.append(" AND FNLN_ORIGINALID = '" + factoryLayout.getOriginalId() +"'" );
		}

		/*String keyid = factoryLayout.getCompany() != null ?  factoryLayout.getCompany().getKeyid(): null;
		
		if( CommonFunctions.isValidKeyId(keyid) )
		{
			//sql.append(" AND COMP_KEYID = '" + keyid + "'");
		}
		
		keyid = factoryLayout.getLocation() != null ?  factoryLayout.getLocation().getKeyid(): null;
		if( CommonFunctions.isValidKeyId(keyid) )
			sql.append(" AND LOCN_KEYID = '" + keyid + "'");
		//fact//
/*		keyid = factoryLayout.getFactory() != null ?  factoryLayout.getFactory().getKeyid(): null;
		if( CommonFunctions.isValidKeyId(keyid) )
			sql.append(" AND FACT_KEYID = '" + keyid + "'");
*/		
/*		keyid = factoryLayout.getSbu() != null ?  factoryLayout.getSbu().getKeyid(): null;
		if( CommonFunctions.isValidKeyId(keyid) )
			sql.append(" AND SBUT_KEYID = '" + keyid + "'");
		
		keyid = factoryLayout.getPbu() != null ?  factoryLayout.getPbu().getKeyid(): null;
		if( CommonFunctions.isValidKeyId(keyid) )
			sql.append(" AND PBUT_KEYID = '" + keyid + "'");
		
		keyid = factoryLayout.getSection() != null ?  factoryLayout.getSection().getKeyid(): null;
		if( CommonFunctions.isValidKeyId(keyid) )
			sql.append(" AND SECT_KEYID = '" + keyid + "'");
		
		keyid = factoryLayout.getCell() != null ?  factoryLayout.getCell().getKeyid(): null;
		if( CommonFunctions.isValidKeyId(keyid) )
			sql.append(" AND CELL_KEYID = '" + keyid + "'");
		
		
		if( isValidMachId )
			sql.append(" AND MCHM_KEYID = '" + machkeyid + "'");
		*/
		CommonMessage.debugMsg( " functional location query " + sql);
		return sql.toString();
	}

		public static String getMachineToSearchSql(String mchId){
			StringBuffer sql = new StringBuffer();
		
			sql.append("select mchm_machinename || ' (' || mchm_machineno || ')'   from gen_tl_machinemst where mchm_keyid='" +mchId+"'" );
			return sql.toString();
		}
		public static String getSpareToSearchSql(String sprId){
			StringBuffer sql = new StringBuffer();
		
			sql.append("select sprm_partname || ' (' || sprm_partno || ')'   from gen_tl_sparesmst where sprm_keyid='" +sprId+"'" );
			return sql.toString();
		}

		public String getFuncLocnId(String elementId) {
			String sql = "SELECT FNLN_KEYID FROM "+TBL_GEN_TL_FUNCTIONALLOCN+ " WHERE FNLN_ELEMENTID = '"+elementId+"'";
			return sql;
		}
}

