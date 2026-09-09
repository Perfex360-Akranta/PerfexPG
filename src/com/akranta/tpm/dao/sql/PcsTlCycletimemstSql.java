package com.akranta.tpm.dao.sql;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;

public class PcsTlCycletimemstSql {

	public static final String TBL_PCS_TL_CYCLETIMEMST = "PCS_TL_CYCLETIMEMST";  

	TableFieldType [] cytmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, cellid, productid, cycletime, manpower, fromdate, tilldate
		, prodgroupid, machineid, sectionid, factoryid, cavity, mandrels
		, tempfield1, tempfield2, tempfield3, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getCytmDbFields() {
		return cytmDbFields;
	}

	public PcsTlCycletimemstSql()
	{
		cytmDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			cytmDbFields[ i ] = new TableFieldType();
		}
		cytmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CYTM_KEYID";
		cytmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		cytmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "CYTM_CELLID";
		cytmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		cytmDbFields[ tableFldConstants.productid.ordinal() ].fieldName = "CYTM_PRODUCTID";
		cytmDbFields[ tableFldConstants.productid.ordinal() ].fieldType = 'V';

		cytmDbFields[ tableFldConstants.cycletime.ordinal() ].fieldName = "CYTM_CYCLETIME";
		cytmDbFields[ tableFldConstants.cycletime.ordinal() ].fieldType = 'N';

		cytmDbFields[ tableFldConstants.manpower.ordinal() ].fieldName = "CYTM_MANPOWER";
		cytmDbFields[ tableFldConstants.manpower.ordinal() ].fieldType = 'N';

		cytmDbFields[ tableFldConstants.fromdate.ordinal() ].fieldName = "CYTM_FROMDATE";
		cytmDbFields[ tableFldConstants.fromdate.ordinal() ].fieldType = 'D';

		cytmDbFields[ tableFldConstants.tilldate.ordinal() ].fieldName = "CYTM_TILLDATE";
		cytmDbFields[ tableFldConstants.tilldate.ordinal() ].fieldType = 'D';

		cytmDbFields[ tableFldConstants.prodgroupid.ordinal() ].fieldName = "CYTM_PRODGROUPID";
		cytmDbFields[ tableFldConstants.prodgroupid.ordinal() ].fieldType = 'V';

		cytmDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "CYTM_MACHINEID";
		cytmDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		cytmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "CYTM_SECTIONID";
		cytmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		cytmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "CYTM_FACTORYID";
		cytmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		cytmDbFields[ tableFldConstants.cavity.ordinal() ].fieldName = "CYTM_CAVITY";
		cytmDbFields[ tableFldConstants.cavity.ordinal() ].fieldType = 'N';

		cytmDbFields[ tableFldConstants.mandrels.ordinal() ].fieldName = "CYTM_MANDRELS";
		cytmDbFields[ tableFldConstants.mandrels.ordinal() ].fieldType = 'N';

		cytmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CYTM_TEMPFIELD1";
		cytmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		cytmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CYTM_TEMPFIELD2";
		cytmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		cytmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CYTM_TEMPFIELD3";
		cytmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		cytmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CYTM_ACTIVE";
		cytmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		cytmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CYTM_CREATEDBY";
		cytmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		cytmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CYTM_CREATEDON";
		cytmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		cytmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CYTM_MODIFIEDON";
		cytmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
	}


	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_CYCLETIMEMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_CYCLETIMEMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_CYCLETIMEMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String isValidSql()
	{
		StringBuffer sql= new StringBuffer();
		sql.append(" SELECT DISTINCT PELC_TYPE FROM PCS_TL_ENABLELOSSCAPTURE");
		sql.append(" WHERE PELC_LINEID = 'LIN/02'  ");
		sql.append(" AND PELC_CELLID = 'CEL003' ");
		sql.append(" AND PELC_ISGROUPBASED IN('Y','N') ");
		sql.append(" AND PELC_ACTIVE = 'Y' ");
	
		return sql.toString();
	}
	
	
	public static String getPcsCycletimeSql()
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CELLID,  PRODID ,  MCHM_MACHINENO ,  PRODUCTCODE, ");
		sql.append(" PRODUCTNAME,  SUM(SPEED) AS SPEED, SUM(MCHHRRATE) AS MCHHRRATE,FROMDATE AS FROMDATE, ");
		sql.append(" MODEL ");
		sql.append(" FROM (SELECT NVL(PRODID,'-')  AS PRODID,  MCHM_KEYID  AS CELLID,");
		sql.append(" NVL(MCHM_MACHINENO,'-') AS MCHM_MACHINENO , ");
		sql.append(" NVL(PRODUCTCODE,'-') AS PRODUCTCODE ,NVL(PRODUCTNAME,'-')  AS PRODUCTNAME,SPEED AS SPEED, ");
		sql.append(" MCHHRRATE AS MCHHRRATE,  DECODE(FROMDATE,'','-' ,FROMDATE) AS FROMDATE,NVL(MODEL,'-')  AS MODEL ");
		sql.append(" FROM ( SELECT DISTINCT CELL_KEYID , PRDM_KEYID AS PRODID, PRODUCTCODE, PRODUCTNAME, ");
		sql.append(" DECODE(CYTM_MACHINEID,MCHM_KEYID,CYTM_CYCLETIME,0)  AS SPEED, ");
		sql.append(" DECODE(CYTM_MACHINEID,MCHM_KEYID,CYTM_MANPOWER,0)   AS MCHHRRATE , ");
		sql.append(" DECODE(CYTM_MACHINEID,MCHM_KEYID,CYTM_FROMDATE, '') AS FROMDATE , ");
		sql.append(" MCHM_KEYID, MCHM_MACHINENO AS MCHM_MACHINENO, PRMM_NAME  AS MODEL ");
		sql.append(" FROM PCS_TL_ENABLELOSSCAPTURE,GEN_TL_MACHINEMST,( SELECT DISTINCT PRDM_KEYID, ");
		sql.append(" CELL_KEYID , CYTM_CYCLETIME,CYTM_MANPOWER,CYTM_FROMDATE,MCHM_KEYID AS CYTM_MACHINEID, ");
		sql.append(" MCHM_MACHINENAME ,PRDM_CODE AS PRODUCTCODE ,PRDM_NAME AS PRODUCTNAME ,PRMM_NAME");
		sql.append(" FROM PCS_TL_CYCLETIMEMST,GEN_TL_CELLMST,PCS_TL_ENABLELOSSCAPTURE,");
		sql.append(" GEN_TL_MACHINEMST,PCS_TL_PRODUCTMST,PCS_TL_PRODUCTMODELMST");
		sql.append(" WHERE 1 = 1 AND PRDM_ACTIVE = 'Y' AND CYTM_CELLID = CELL_KEYID");
		sql.append(" AND CYTM_PRODUCTID     = PRDM_KEYID(+) AND PRDM_MODEL= PRMM_KEYID (+) AND CYTM_ACTIVE = 'Y'");
		sql.append(" AND PELC_CELLID = CELL_KEYID AND PELC_CELLID = MCHM_CELLID AND CYTM_MACHINEID = MCHM_KEYID");
		sql.append(" AND PELC_ISPCSENABLED  = 'Y' AND PELC_ISGROUPBASED  = 'N' AND CELL_FACTORYID (+) ='FCT/01' ");
    	sql.append(" AND CELL_SECTIONID (+) ='LIN/01' ");
		sql.append(" AND CELL_KEYID  = 'CEL001' AND CELL_COSTCENTREID  = 'CST/0001' )WHERE 1 = 1 ");
		sql.append(" AND PELC_MACHINEID = MCHM_KEYID AND PELC_ISPCSENABLED = 'Y' AND PELC_ISGROUPBASED = 'N' AND MCHM_CELLID = 'CEL001'");
		sql.append("  ) )GROUP BY cellid, prodid,  MCHM_MACHINENO,productcode,productname ,FROMDATE,");		
		sql.append(" model ORDER BY MCHM_MACHINENO,PRODUCTCODE,PRODUCTNAME,MCHHRRATE ,MODEL");		
		return sql.toString() ;
	}
	public static String getPcsCycletimeEntrySql()
	{
		return "Select * from dual";
		
	}
	
	public static String RecallCompSqlForSection(String subGroupId,String productId)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CYTM_KEYID AS KEYID, SECT_CODE ||'-'||SECT_NAME AS LINCODE , PRDM_CODE || '-'|| PRDM_NAME ");
		sql.append(" AS PRODUCT,PRMM_NAME AS MODEL,CYTM_CYCLETIME   AS CYCLETIME,");
		sql.append(" CYTM_MANPOWER  AS MANPOWER,TO_CHAR(CYTM_FROMDATE,'DD-Mon-YYYY') AS EFFDATE ,");
		sql.append(" SECT_KEYID AS CELLID , CYTM_PRODUCTID AS PRODUCTID ,");
		sql.append(" PSGM_NAME || '-'|| PSGM_CODE AS SUBGROUP, ");
		sql.append(" CYTM_SUBGROUPID , CYTM_MACHINEID  ");
		sql.append(" FROM PCS_TL_CYCLETIMEMST , GEN_TL_SECTIONMST ,PCS_TL_SUBGROUPMST ,PCS_TL_PRODUCTMST , PCS_TL_PRODUCTMODELMST");
		sql.append(" AND CYTM_SECTIONID = 'LIN/02'");
		sql.append(" AND CYTM_PRODUCTID = PRDM_KEYID  AND CYTM_SECTIONID = SECT_KEYID");
		sql.append(" AND CYTM_SUBGROUPID = PSGM_KEYID (+) AND CYTM_ACTIVE = 'Y'");
		sql.append(" AND PRDM_ACTIVE  = 'Y' ");
		if(UIUtils.isValidKeyId(subGroupId))
			sql.append(" AND CYTM_SUBGROUPID = '"+subGroupId+"' ");
		if(UIUtils.isValidKeyId(productId))
			sql.append(" AND CYTM_PRODUCTID = '"+productId+"' ");
		
		sql.append(" ORDER BY  SECT_CODE,PRDM_CODE,PRDM_NAME,PRMM_NAME, CYTM_MANPOWER," );
		sql.append(" TO_CHAR(CYTM_FROMDATE,'DD-Mon-YYYY') ");
		
		return sql.toString();
	}
	
	public static String GetAvailMachinesinPCS()
	{
		StringBuffer sql =new StringBuffer();
		sql.append(" ");
		return sql.toString();
	}
	
	public static String RecallCompForCellSubgroup()
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT   CYTM_KEYID AS KEYID,  CELL_CODE||'-'||CELL_NAME AS CellCode ,");
		sql.append(" PRDM_CODE || '-' || PRDM_NAME AS PRODUCT,  ");
		sql.append(" PRMM_NAME AS MODEL, CYTM_CYCLETIME AS CYCLETIME, CYTM_MANPOWER AS MANPOWER, ");
		sql.append(" TO_CHAR(CYTM_FROMDATE,'DD-Mon-YYYY') AS EFFDATE , CELL_KEYID AS CELLID , ");
		sql.append(" CYTM_PRODUCTID AS PRODUCTID ,PSGM_CODE AS SUBGROUP, CYTM_SUBGROUPID,CYTM_MACHINEID ");
		sql.append(" FROM ");
		sql.append(" PCS_TL_CYCLETIMEMST ,GEN_TL_CELLMST ,PCS_TL_SUBGROUPMST,PCS_TL_PRODUCTMST ,PCS_TL_PRODUCTMODELMST ,GEN_TL_MACHINEMST  ");
		sql.append(" WHERE ");
		sql.append(" PRDM_MODEL = PRMM_KEYID AND CYTM_CELLID = '' ");
		sql.append(" AND CYTM_PRODUCTID = PRDM_KEYID  AND CYTM_CELLID = CELL_KEYID  ");
		sql.append(" AND CYTM_SUBGROUPID = PSGM_KEYID (+) AND CYTM_MACHINEID=MCHM_KEYID(+) ");
		sql.append("  AND CYTM_ACTIVE = 'Y' AND PRDM_ACTIVE = 'Y' ");
		if(UIUtils.isValidKeyId(""))
			sql.append(" AND CYTM_SUBGROUPID = ''");
		if(UIUtils.isValidKeyId(" "))
			sql.append(" AND CYTM_MACHINEID = '' ");
		if(UIUtils.isValidKeyId(" "))
			sql.append(" AND CYTM_PRODUCTID = '' ");
		sql.append(" ORDER BY   CELL_CODE,PRDM_CODE,PRDM_NAME,PRMM_NAME, ");
		sql.append("  CYTM_MANPOWER,TO_CHAR(CYTM_FROMDATE,'DD-Mon-YYYY') ");
		
		return sql.toString();
	}
	
	public static String Recall()
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CYTM_KEYID AS KEYID, CELL_CODE ||'-'||CELL_NAME AS CELLCODE ,");
		sql.append("  PRDM_CODE || '-'|| PRDM_NAME  AS PRODUCT,");
		sql.append(" PRMM_NAME AS MODEL,");
		sql.append(" CYTM_CYCLETIME AS CYCLETIME,CYTM_MANPOWER AS MANPOWER,");
		sql.append(" TO_CHAR(CYTM_FROMDATE,'DD-Mon-YYYY') AS EFFDATE , CELL_KEYID  AS CELLID ,");
		sql.append("  PSGM_CODE  AS SUBGROUP,CYTM_PRODGROUPID ,CYTM_MACHINEID");
		sql.append(" FROM PCS_TL_CYCLETIMEMST,");
		sql.append("  GEN_TL_CELLMST,PCS_VW_PRODUCTMST, PCS_TL_PRODUCTMST,PCS_TL_PRODUCTMODELMST");
		sql.append(" WHERE PRDM_MODEL    = PRMM_KEYID AND CYTM_CELLID = 'CEL003' AND CYTM_PRODUCTID  = PRDM_KEYID");
		sql.append(" AND CYTM_CELLID     = CELL_KEYID AND CYTM_PRODGROUPID = PRODUCTID (+) AND CYTM_ACTIVE = 'Y'");
		sql.append(" AND PRDM_ACTIVE = 'Y' AND CYTM_PRODUCTID  = 'PRD/000002' ORDER BY CELL_CODE, PRDM_CODE,PRDM_NAME, PRMM_NAME,");
		sql.append(" CYTM_MANPOWER, TO_CHAR(CYTM_FROMDATE,'DD-Mon-YYYY')");
	
		return sql.toString();
	}
	
	
	public static String RecallCompSqlForMachine(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams)
	{
		
		StringBuffer sql = new StringBuffer();
		StringBuffer outerSql = new StringBuffer();
		sql.append(" SELECT CYTM_KEYID AS KEYID,  MCHM_MACHINENO AS mchname ,'', ");
		sql.append(" PRDM_CODE || '-' || PRDM_NAME AS Product,    ");
		sql.append("  PRMM_NAME AS Model, CYTM_CYCLETIME AS txtcytmCycletime,'' AS NEWCYCLETIME, CYTM_MANPOWER AS txtcytmManpower, ");
		sql.append(" CYTM_CAVITY AS txtcytmCavity, CYTM_MANDRELS AS txtcytmMandrels,");
		sql.append(" TO_CHAR(CYTM_FROMDATE,'DD-Mon-YYYY') AS dtecytmFromdate ,'' AS NEWEFFECTIVEDATE,CYTM_FACTORYID,CYTM_SECTIONID, CELL_KEYID AS CELLID , ");
		sql.append(" CYTM_PRODUCTID AS PRODUCTID, CYTM_MACHINEID ");
		sql.append(" FROM ");
		sql.append("  PCS_TL_CYCLETIMEMST ,  GEN_TL_CELLMST, ");
		sql.append(" PCS_TL_PRODUCTMST ,PCS_TL_PRODUCTMODELMST,GEN_TL_MACHINEMST ");
		sql.append("  WHERE PRDM_MODEL = PRMM_KEYID  AND CYTM_PRODUCTID = PRDM_KEYID  ");
		sql.append("  AND CYTM_CELLID = CELL_KEYID  AND CYTM_MACHINEID = MCHM_KEYID  AND CYTM_ACTIVE = 'Y' AND PRDM_ACTIVE = 'Y' ");
		if(UIUtils.isValidKeyId(cellId))
			sql.append(" AND CYTM_CELLID = '"+cellId+"'");
		if(UIUtils.isValidKeyId(prodGroupId))
			sql.append(" AND CYTM_PRODGROUPID = '"+prodGroupId+"'");
		if(UIUtils.isValidKeyId(machineId))
			sql.append(" AND CYTM_MACHINEID = '"+machineId+"' ");
		if(UIUtils.isValidKeyId(productId))
			sql.append(" AND CYTM_PRODUCTID = '"+productId+"' ");
		sql.append(" UNION SELECT '' AS KEYID, MCHM_MACHINENO AS mchname, '', ");
       sql.append(" ''  AS PRODUCT,'' AS MODEL, 0 AS TXTCYTMCYCLETIME,'' AS NEWCYCLETIME,0 AS TXTCYTMMANPOWER, ");
	   sql.append(" 0 AS TXTCYTMCAVITY,0 AS TXTCYTMMANDRELS,TO_CHAR (PELC_EFFECTIVEFROM,'DD-Mon-YYYY') AS DTECYTMFROMDATE,");
       sql.append(" '' AS NEWEFFECTIVEDATE, PELC_FACTORYID,PELC_LINEID, CELL_KEYID AS CELLID,'"+productId+"' AS PRODUCTID,PELC_MACHINEID ");
	   sql.append(" FROM PCS_TL_ENABLELOSSCAPTURE,GEN_TL_CELLMST,GEN_TL_MACHINEMST WHERE ");		   
	   sql.append(" PELC_CELLID = CELL_KEYID  AND PELC_MACHINEID(+) = MCHM_KEYID  AND PELC_ACTIVE(+) = 'Y' ");
       sql.append(" AND PELC_ISPCSENABLED = 'Y' AND PELC_TYPE = 'M' ");
       sql.append(" AND PELC_MACHINEID NOT IN(SELECT CYTM_MACHINEID FROM  PCS_TL_CYCLETIMEMST WHERE CYTM_PRODUCTID = '"+productId+"') ");

		outerSql.append(" select * from (  "  +sql + " )  where 1 = 1 ");
		
		
		outerSql.append(FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())); 
		return outerSql.toString();
		
		/*StringBuffer sql = new StringBuffer();
		StringBuffer outerSql = new StringBuffer();
		sql.append(" SELECT    CYTM_KEYID AS KEYID,  MCHM_MACHINENO||'-'||MCHM_MACHINENAME AS MACHINENO ,'', ");
		sql.append(" PRDM_CODE || '-' || PRDM_NAME AS Product,    ");
		sql.append("  PRMM_NAME AS Model, CYTM_CYCLETIME AS txtcytmCycletime, CYTM_MANPOWER AS txtcytmManpower, ");
		sql.append(" CYTM_CAVITY AS txtcytmCavity, CYTM_MANDRELS AS txtcytmMandrels,");
		sql.append(" TO_CHAR(CYTM_FROMDATE,'DD-Mon-YYYY') AS dtecytmFromdate ,CYTM_FACTORYID,CYTM_SECTIONID, CELL_KEYID AS CELLID , ");
		sql.append(" CYTM_PRODUCTID AS PRODUCTID, CYTM_MACHINEID ");
		sql.append(" FROM ");
		sql.append("  PCS_TL_CYCLETIMEMST ,  GEN_TL_CELLMST, ");
		sql.append(" PCS_TL_PRODUCTMST ,PCS_TL_PRODUCTMODELMST,GEN_TL_MACHINEMST ");
		sql.append("  WHERE PRDM_MODEL = PRMM_KEYID  AND CYTM_PRODUCTID = PRDM_KEYID  ");
		sql.append("  AND CYTM_CELLID = CELL_KEYID  AND CYTM_MACHINEID = MCHM_KEYID  AND CYTM_ACTIVE = 'Y' AND PRDM_ACTIVE = 'Y' ");
		if(UIUtils.isValidKeyId(cellId))
			sql.append(" AND CYTM_CELLID = '"+cellId+"'");
		if(UIUtils.isValidKeyId(prodGroupId))
			sql.append(" AND CYTM_PRODGROUPID = '"+prodGroupId+"'");
		if(UIUtils.isValidKeyId(machineId))
			sql.append(" AND CYTM_MACHINEID = '"+machineId+"' ");
		if(UIUtils.isValidKeyId(productId))
			sql.append(" AND CYTM_PRODUCTID = '"+productId+"' ");
		sql.append(" ORDER BY CELL_CODE,PRDM_CODE,PRDM_NAME,PRMM_NAME,  ");
		sql.append("  CYTM_MANPOWER,TO_CHAR(CYTM_FROMDATE,'DD-Mon-YYYY') ");
		outerSql.append(" select * from (  "  +sql + " )  where 1 = 1 ");
		
		
		outerSql.append(FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())); 
		return outerSql.toString();*/
	}
	
	public static String selectPcsCycleTimeMstSql(){
		return "select * from " + TBL_PCS_TL_CYCLETIMEMST + " where CYTM_KEYID = ? " ;
	}
	public static String selectPcsCycleTimeMstMCHProdCavitySql(){
		return "select * from " + TBL_PCS_TL_CYCLETIMEMST + " where CYTM_MACHINEID = ? AND CYTM_PRODUCTID = ? AND CYTM_CAVITY = ? " ;
	}

	public static String getcycleTimeUpdate(String product, String cycleTime) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE  " + TBL_PCS_TL_CYCLETIMEMST );
		sql.append(" SET CYTM_CYCLETIME = '"+cycleTime+"' WHERE CYTM_PRODUCTID = '"+product+"'");
		return sql.toString();
	}

	public static Object RecallCompSqlForProd(String cellId, String machineId,	String productId, String prodGroupId, GridParams gridParams) {
				
			
			StringBuffer sql = new StringBuffer();
			StringBuffer outerSql = new StringBuffer();
			sql.append(" SELECT    '' AS TICKVAL,'' AS TICK,CYTM_KEYID AS KEYID,  MCHM_MACHINENO AS mchname ,'', ");
			sql.append(" PRDM_CODE || '-' || PRDM_NAME AS Product,    ");
			sql.append("  PRMM_NAME AS Model, CYTM_CYCLETIME AS txtcytmCycletime,'' AS NEWCYCLETIME, CYTM_MANPOWER AS txtcytmManpower, ");
			sql.append(" CYTM_CAVITY AS txtcytmCavity, CYTM_MANDRELS AS txtcytmMandrels,");
			sql.append(" TO_CHAR(CYTM_FROMDATE,'DD-Mon-YYYY') AS dtecytmFromdate ,'' AS NEWEFFECTIVEDATE,CYTM_FACTORYID,CYTM_SECTIONID, CELL_KEYID AS CELLID , ");
			sql.append(" CYTM_PRODUCTID AS PRODUCTID, CYTM_MACHINEID ");
			sql.append(" FROM ");
			sql.append("  PCS_TL_CYCLETIMEMST ,  GEN_TL_CELLMST, ");
			sql.append(" PCS_TL_PRODUCTMST ,PCS_TL_PRODUCTMODELMST,GEN_TL_MACHINEMST ");
			sql.append("  WHERE PRDM_MODEL = PRMM_KEYID  AND CYTM_PRODUCTID = PRDM_KEYID  ");
			sql.append("  AND CYTM_CELLID = CELL_KEYID  AND CYTM_MACHINEID = MCHM_KEYID  AND CYTM_ACTIVE = 'Y' AND PRDM_ACTIVE = 'Y' ");
			if(UIUtils.isValidKeyId(cellId))
				sql.append(" AND CYTM_CELLID = '"+cellId+"'");
			if(UIUtils.isValidKeyId(prodGroupId))
				sql.append(" AND CYTM_PRODGROUPID = '"+prodGroupId+"'");
			if(UIUtils.isValidKeyId(machineId))
				sql.append(" AND CYTM_MACHINEID = '"+machineId+"' ");
			if(UIUtils.isValidKeyId(productId))
				sql.append(" AND CYTM_PRODUCTID = '"+productId+"' ");
			sql.append(" UNION SELECT   '' AS TICKVAL, '' AS TICK,  '' AS KEYID, MCHM_MACHINENO AS mchname, '', ");
	       sql.append(" ''  AS PRODUCT,'' AS MODEL, 0 AS TXTCYTMCYCLETIME,'' AS NEWCYCLETIME,0 AS TXTCYTMMANPOWER, ");
		   sql.append(" 0 AS TXTCYTMCAVITY,0 AS TXTCYTMMANDRELS,TO_CHAR (PELC_EFFECTIVEFROM,'DD-Mon-YYYY') AS DTECYTMFROMDATE,");
	       sql.append(" '' AS NEWEFFECTIVEDATE, PELC_FACTORYID,PELC_LINEID, CELL_KEYID AS CELLID,'"+productId+"' AS PRODUCTID,PELC_MACHINEID ");
		   sql.append(" FROM PCS_TL_ENABLELOSSCAPTURE,GEN_TL_CELLMST,GEN_TL_MACHINEMST WHERE ");		   
		   sql.append(" PELC_CELLID = CELL_KEYID  AND PELC_MACHINEID(+) = MCHM_KEYID  AND PELC_ACTIVE(+) = 'Y' ");
	       sql.append(" AND PELC_ISPCSENABLED = 'Y' AND PELC_TYPE = 'M' ");
	       sql.append(" AND PELC_MACHINEID NOT IN(SELECT CYTM_MACHINEID FROM  PCS_TL_CYCLETIMEMST WHERE CYTM_PRODUCTID = '"+productId+"') ORDER BY mchname");

			outerSql.append(" select * from (  "  +sql + " )  where 1 = 1 ");
			
			
			outerSql.append(FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())); 
			return outerSql.toString();
			
		
		/*StringBuffer sql = new StringBuffer();
		StringBuffer outerSql = new StringBuffer();
		sql.append(" SELECT    '' AS TICKVAL,'' AS TICK,CYTM_KEYID AS KEYID,  MCHM_MACHINENO AS MACHINENO ,'', ");
		sql.append(" PRDM_CODE || '-' || PRDM_NAME AS Product,    ");
		sql.append("  PRMM_NAME AS Model, CYTM_CYCLETIME AS txtcytmCycletime,'' AS NEWCYCLETIME, CYTM_MANPOWER AS txtcytmManpower, ");
		sql.append(" CYTM_CAVITY AS txtcytmCavity, CYTM_MANDRELS AS txtcytmMandrels,");
		sql.append(" TO_CHAR(CYTM_FROMDATE,'DD-Mon-YYYY') AS dtecytmFromdate ,'' AS NEWEFFECTIVEDATE,CYTM_FACTORYID,CYTM_SECTIONID, CELL_KEYID AS CELLID , ");
		sql.append(" CYTM_PRODUCTID AS PRODUCTID, CYTM_MACHINEID ");
		sql.append(" FROM ");
		sql.append("  PCS_TL_CYCLETIMEMST ,  GEN_TL_CELLMST, ");
		sql.append(" PCS_TL_PRODUCTMST ,PCS_TL_PRODUCTMODELMST,GEN_TL_MACHINEMST ");
		sql.append("  WHERE PRDM_MODEL = PRMM_KEYID  AND CYTM_PRODUCTID = PRDM_KEYID  ");
		sql.append("  AND CYTM_CELLID = CELL_KEYID  AND CYTM_MACHINEID = MCHM_KEYID  AND CYTM_ACTIVE = 'Y' AND PRDM_ACTIVE = 'Y' ");
		if(UIUtils.isValidKeyId(cellId))
			sql.append(" AND CYTM_CELLID = '"+cellId+"'");
		if(UIUtils.isValidKeyId(prodGroupId))
			sql.append(" AND CYTM_PRODGROUPID = '"+prodGroupId+"'");
		if(UIUtils.isValidKeyId(machineId))
			sql.append(" AND CYTM_MACHINEID = '"+machineId+"' ");
		if(UIUtils.isValidKeyId(productId))
			sql.append(" AND CYTM_PRODUCTID = '"+productId+"' ");
		sql.append(" ORDER BY CELL_CODE,PRDM_CODE,PRDM_NAME,PRMM_NAME,  ");
		sql.append("  CYTM_MANPOWER,TO_CHAR(CYTM_FROMDATE,'DD-Mon-YYYY') ");
		outerSql.append(" select * from (  "  +sql + " )  where 1 = 1 ");	
		
		outerSql.append(FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())); 
		return outerSql.toString();*/
	}

}

