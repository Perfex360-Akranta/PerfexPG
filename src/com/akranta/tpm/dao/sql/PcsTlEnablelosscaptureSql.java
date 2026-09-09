package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class PcsTlEnablelosscaptureSql {

	public static final String TBL_PCS_TL_ENABLELOSSCAPTURE = "PCS_TL_ENABLELOSSCAPTURE";  

	TableFieldType [] pelcDbFields = null;

	public enum   tableFldConstants
	{
		keyid, factoryid, cellid, ispcsenabled, effectivefrom, effectivetill
		, qtyortimebased, isgroupbased, ishtlog, type, machineid, option
		, lineid, flid, elementid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPelcDbFields() {
		return pelcDbFields;
	}

	public PcsTlEnablelosscaptureSql()
	{
		pelcDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			pelcDbFields[ i ] = new TableFieldType();
		}
		pelcDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PELC_KEYID";
		pelcDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pelcDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "PELC_FACTORYID";
		pelcDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		pelcDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "PELC_CELLID";
		pelcDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		pelcDbFields[ tableFldConstants.ispcsenabled.ordinal() ].fieldName = "PELC_ISPCSENABLED";
		pelcDbFields[ tableFldConstants.ispcsenabled.ordinal() ].fieldType = 'C';

		pelcDbFields[ tableFldConstants.effectivefrom.ordinal() ].fieldName = "PELC_EFFECTIVEFROM";
		pelcDbFields[ tableFldConstants.effectivefrom.ordinal() ].fieldType = 'D';

		pelcDbFields[ tableFldConstants.effectivetill.ordinal() ].fieldName = "PELC_EFFECTIVETILL";
		pelcDbFields[ tableFldConstants.effectivetill.ordinal() ].fieldType = 'D';

		pelcDbFields[ tableFldConstants.qtyortimebased.ordinal() ].fieldName = "PELC_QTYORTIMEBASED";
		pelcDbFields[ tableFldConstants.qtyortimebased.ordinal() ].fieldType = 'C';

		pelcDbFields[ tableFldConstants.isgroupbased.ordinal() ].fieldName = "PELC_ISGROUPBASED";
		pelcDbFields[ tableFldConstants.isgroupbased.ordinal() ].fieldType = 'C';

		pelcDbFields[ tableFldConstants.ishtlog.ordinal() ].fieldName = "PELC_ISHTLOG";
		pelcDbFields[ tableFldConstants.ishtlog.ordinal() ].fieldType = 'C';

		pelcDbFields[ tableFldConstants.type.ordinal() ].fieldName = "PELC_TYPE";
		pelcDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		pelcDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "PELC_MACHINEID";
		pelcDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		pelcDbFields[ tableFldConstants.option.ordinal() ].fieldName = "PELC_OPTION";
		pelcDbFields[ tableFldConstants.option.ordinal() ].fieldType = 'C';

		pelcDbFields[ tableFldConstants.lineid.ordinal() ].fieldName = "PELC_LINEID";
		pelcDbFields[ tableFldConstants.lineid.ordinal() ].fieldType = 'V';


		pelcDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "PELC_FLID";
		pelcDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		pelcDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "PELC_ELEMENTID";
		pelcDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		pelcDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PELC_ACTIVE";
		pelcDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pelcDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PELC_CREATEDBY";
		pelcDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pelcDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PELC_CREATEDON";
		pelcDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pelcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PELC_MODIFIEDON";
		pelcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_ENABLELOSSCAPTURE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_ENABLELOSSCAPTURE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_ENABLELOSSCAPTURE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	

	public static String getPcsEbDbMCHSql(String flid)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  MCHM_KEYID ,PELC_KEYID,  REPLACE(MCHM_MACHINENAME,'&','-') ,MCHM_MACHINENO, CSTM_CODE,  PELC_ISPCSENABLED,");
		sql.append(" PELC_OPTION ,PELC_QTYORTIMEBASED,PELC_ISHTLOG,'','','','',PELC_ISPCSENABLED ");
		sql.append("FROM ");
		sql.append("GEN_TL_MACHINEMST, GEN_TL_COSTCENTREMST,");
		sql.append("(SELECT CELL_KEYID , CSTM_CODE as CSTCODE ,PELC_ISPCSENABLED,PELC_KEYID,");
		sql.append("DECODE(PELC_QTYORTIMEBASED,'Q', 'QUANTITY','T', 'TIME') AS PELC_QTYORTIMEBASED,PELC_ISGROUPBASED,");
		sql.append("PELC_ISHTLOG ,DECODE(PELC_OPTION,'H','HOUR','S','SHIFT','D','DAY','W','WEEK') ");
		sql.append("AS PELC_OPTION,PELC_MACHINEID,PELC_LINEID  From " ); 
		sql.append("PCS_TL_ENABLELOSSCAPTURE,GEN_TL_COSTCENTREMST , GEN_TL_CELLMST ");
		//sql.append("Where  CELL_FACTORYID = ? AND CELL_SECTIONID= ? AND CELL_KEYID= ? ");
		sql.append(" Where  PELC_FLID = '" + flid + "'  ");
		sql.append(" AND PELC_CELLID = CELL_KEYID  (+) AND CELL_COSTCENTREID = CSTM_KEYID(+) ");	
		//sql.append(" AND PELC_CELLID = ? ");
		sql.append(" AND ( PELC_TYPE = 'M' OR PELC_TYPE = 'C') AND PELC_ACTIVE='Y'  ) ");
		sql.append(" WHERE MCHM_ACTIVE = 'Y' AND PELC_MACHINEID (+) = MCHM_KEYID ");
		//sql.append(" AND MCHM_CELLID = ? ");
		sql.append(" AND MCHM_FLID = '" + flid + "' ");
		sql.append(" AND MCHM_COSTCENTREID(+)=CSTM_KEYID ORDER BY MCHM_MACHINENO ");
			 
			
		return sql.toString();
	}
	
	public static String getPcsEbDbSECTSql()
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  SECT_KEYID, PELC_KEYID,SECT_NAME || '-' || SECT_CODE,'', PELC_ISPCSENABLED,");
		sql.append(" DECODE(PELC_OPTION,'H','HOUR','S','SHIFT','D','DAY','W','WEEK') AS QTYTIME ,PELC_ISGROUPBASED,");
		sql.append(" DECODE(PELC_QTYORTIMEBASED, 'Q', 'QUANTITY', 'T', 'TIME') AS PELC_QTYORTIMEBASED ,"); 
		sql.append(" PELC_ISHTLOG ");
		sql.append(" From ");
		sql.append(" GEN_TL_SECTIONMST , PCS_TL_ENABLELOSSCAPTURE ");
		sql.append(" Where ");
		sql.append(" SECT_FACTORYID = ? ");
		sql.append(" AND SECT_KEYID = ? ");		
		sql.append(" AND PELC_LINEID(+) = SECT_KEYID ");
		sql.append(" AND SECT_ACTIVE = 'Y' ");
		sql.append(" AND PELC_TYPE (+) = 'S'");
		sql.append(" ORDER BY SECT_KEYID ");
  	
		return sql.toString();
		
	}
	
	public static String getPcsEbDbCELLSql()
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  CELL_KEYID , CELL_NAME || '-' || CELL_CODE,CSTM_CODE,PELC_ISPCSENABLED, PELC_KEYID,");
		sql.append(" DECODE(PELC_QTYORTIMEBASED, 'Q', 'QUANTITY', 'T', 'TIME') AS PELC_QTYORTIMEBASED , PELC_ISGROUPBASED , PELC_ISHTLOG, ");
		sql.append(" DECODE(PELC_OPTION,'H','HOUR','S','SHIFT','D','DAY','W','WEEK')");
		sql.append(" FROM ");
		sql.append(" GEN_TL_CELLMST,PCS_TL_ENABLELOSSCAPTURE,GEN_TL_COSTCENTREMST");
		sql.append(" CELL_FACTORYID = ?  AND CELL_SECTIONID= ?  AND PELC_CELLID(+)=CELL_KEYID ");
		sql.append(" AND CELL_COSTCENTREID = CSTM_KEYID(+) AND CELL_ACTIVE ='Y' ");
		sql.append(" ORDER BY CELL_KEYID ");
		
		return sql.toString();
		
	}
	
	
	public static String getCELLCnt()
	{
		String sql=" SELECT DISTINCT PELC_CELLID FROM " +TBL_PCS_TL_ENABLELOSSCAPTURE 
                  +" WHERE PELC_TYPE IN ('C,M')  AND PELC_CELLID = ?";
		
		return sql;
	}
	
	public static String getMCHCnt()
	{
		String sql=" SELECT DISTINCT PELC_CELLID FROM " +TBL_PCS_TL_ENABLELOSSCAPTURE 
                  +" WHERE PELC_TYPE IN ('C')  AND PELC_CELLID = ?";
		
		return sql;
	}
	
	public static String getSectCnt()
	{
		String sql="  SELECT DISTINCT PELC_LINEID FROM " +TBL_PCS_TL_ENABLELOSSCAPTURE 
                  +" WHERE PELC_TYPE IN ('S') AND PELC_LINEID = ? ";
		
		return sql;
	}


	public static String getJHSql(String lossId) 
	{
		// TODO Auto-generated method stub
		String sql="";
		StringBuffer sql1 = new StringBuffer();
		
		sql1.append(" SELECT DISTINCT cell_keyid,cell_code,cell_name" );
		sql1.append(" FROM PCS_TL_LOSSCELLLINK, GEN_TL_CELLMST" );
		sql1.append(" WHERE PLFL_CELLID (+)= CELL_KEYID " );
		
		sql1.append(" AND PLFL_PARAMETERID(+)='" + lossId + "'   ");
		
		sql = sql1.toString();
		
		CommonMessage.debugMsg("SQL.........."+sql);
		return sql;
		
	}

 // --------------- Vignesh 12NOv 2025 ---- Making pg compatible -----------------------------//
//	public static String getFactorySql(String lossId, String phenID) 
//	{
//		// TODO Auto-generated method stub
//		String sql="";
//		if(UIUtils.isValidKeyId(lossId))
//		{
//			sql="SELECT DECODE (ppfl_factoryid, cell_keyid, 1, 0) AS selected, cell_keyid,cell_code,cell_name FROM gen_tl_cellmst,(SELECT DISTINCT ppfl_factoryid FROM pcs_tl_lossphenfactorylink, pcs_tl_lossphenomenamst WHERE plpm_keyid = ppfl_plpm_keyid AND plpm_name <> '-' AND plpm_mainloss = '"+lossId+"')"+
//			"WHERE cell_keyid = ppfl_factoryid(+)";
//		}
//		else if(UIUtils.isValidKeyId(phenID))
//		{	
//			sql="SELECT DECODE (ppfl_factoryid, cell_keyid, 1, 0) AS selected, cell_keyid,cell_code,cell_name FROM gen_tl_cellmst,(SELECT DISTINCT ppfl_factoryid FROM pcs_tl_lossphenfactorylink, pcs_tl_lossphenomenamst WHERE plpm_keyid = ppfl_plpm_keyid AND plpm_name <> '-' AND plpm_keyid = '"+phenID+"')"+
//			"WHERE cell_keyid = ppfl_factoryid(+)";
//		}
//		else
//		{
//			sql="select decode(FACTORY,PHENFACTID,1,0) as \"SELECTED\", FACTORY,fact_code,fact_name from( select distinct fact_keyid as \"FACTORY\",PPFL_FACTORYID as \"PHENFACTID\",fact_code,fact_name from gen_tl_factorymst,PCS_TL_LOSSPHENFACTORYLINK where fact_keyid=PPFL_FACTORYID(+) )";
//		}
//		CommonMessage.debugMsg("SQL.........."+sql);
//		return sql;
//	}
	
	

//	public static String gePhenomenaSql(String phenId, String lossId) {
//		// TODO Auto-generated method stub
//		String sql= "select distinct PLPM_KEYID AS PHENID,PLPM_NAME AS PHENNAME from PCS_TL_LOSSPHENFACTORYLINK,PCS_TL_LOSSPHENOMENAMST where  PLPM_KEYID=PPFL_PLPM_KEYID(+) and PLPM_NAME <> '-' and PLPM_ACTIVE='Y' ";
//		if(UIUtils.isValidKeyId(phenId))
//			sql+=" and PLPM_KEYID='"+phenId+"'";
//		if(UIUtils.isValidKeyId(lossId))
//			sql+=" and PLPM_MAINLOSS='"+lossId+"'";
//		
//		sql+=" ORDER BY PHENNAME ASC ";
//		
//		return sql;
//	}
	
	public static String getFactorySql(String lossId, String phenID) 
	{
	    String sql = "";

	    if (UIUtils.isValidKeyId(lossId)) {
	        

	        sql =
	        "SELECT " +
	        "  CASE WHEN pf.ppfl_factoryid = c.cell_keyid THEN 1 ELSE 0 END AS selected, " +
	        "  c.cell_keyid, c.cell_code, c.cell_name " +
	        "FROM gen_tl_cellmst c " +
	        "LEFT JOIN ( " +
	        "  SELECT DISTINCT l.ppfl_factoryid " +
	        "  FROM pcs_tl_lossphenfactorylink l " +
	        "  JOIN pcs_tl_lossphenomenamst p ON p.plpm_keyid = l.ppfl_plpm_keyid " +
	        "  WHERE p.plpm_name <> '-' " +
	        "    AND p.plpm_mainloss = '" + lossId + "' " +
	        ") pf ON c.cell_keyid = pf.ppfl_factoryid";

	    } else if (UIUtils.isValidKeyId(phenID)) {
	        

	        sql =
	        "SELECT " +
	        "  CASE WHEN pf.ppfl_factoryid = c.cell_keyid THEN 1 ELSE 0 END AS selected, " +
	        "  c.cell_keyid, c.cell_code, c.cell_name " +
	        "FROM gen_tl_cellmst c " +
	        "LEFT JOIN ( " +
	        "  SELECT DISTINCT l.ppfl_factoryid " +
	        "  FROM pcs_tl_lossphenfactorylink l " +
	        "  JOIN pcs_tl_lossphenomenamst p ON p.plpm_keyid = l.ppfl_plpm_keyid " +
	        "  WHERE p.plpm_name <> '-' " +
	        "    AND p.plpm_keyid = '" + phenID + "' " +
	        ") pf ON c.cell_keyid = pf.ppfl_factoryid";

	    } else {
	        

	        sql =
	        "SELECT " +
	        "  CASE WHEN t.\"FACTORY\" = t.\"PHENFACTID\" THEN 1 ELSE 0 END AS \"SELECTED\", " +
	        "  t.\"FACTORY\", t.fact_code, t.fact_name " +
	        "FROM ( " +
	        "  SELECT DISTINCT f.fact_keyid AS \"FACTORY\", l.ppfl_factoryid AS \"PHENFACTID\", " +
	        "         f.fact_code, f.fact_name " +
	        "  FROM gen_tl_factorymst f " +
	        "  LEFT JOIN pcs_tl_lossphenfactorylink l ON f.fact_keyid = l.ppfl_factoryid " +
	        ") t";
	    }

	    CommonMessage.debugMsg("SQL.........." + sql);
	    return sql;
	}
	
	
	public static String gePhenomenaSql(String phenId, String lossId) {
	    StringBuilder sql = new StringBuilder();

	    sql.append("SELECT DISTINCT ")
	       .append("  p.PLPM_KEYID AS PHENID, ")
	       .append("  p.PLPM_NAME AS PHENNAME ")
	       .append("FROM PCS_TL_LOSSPHENOMENAMST p ")
	       // Oracle: PLPM_KEYID = PPFL_PLPM_KEYID(+)
	       // Postgres equivalent:
	       .append("LEFT JOIN PCS_TL_LOSSPHENFACTORYLINK l ")
	       .append("  ON p.PLPM_KEYID = l.PPFL_PLPM_KEYID ")
	       .append("WHERE p.PLPM_NAME <> '-' ")
	       .append("  AND p.PLPM_ACTIVE = 'Y' ");

	    if (UIUtils.isValidKeyId(phenId)) {
	        sql.append(" AND p.PLPM_KEYID = '").append(phenId).append("' ");
	    }
	    if (UIUtils.isValidKeyId(lossId)) {
	        sql.append(" AND p.PLPM_MAINLOSS = '").append(lossId).append("' ");
	    }

	    sql.append("ORDER BY PHENNAME ASC ");

	    return sql.toString();
	}

	
	 // --------------- Vignesh 12NOv 2025 ---- Making pg compatible -----------------------------//



	public static String geLossSql(String jhId) {
		// TODO Auto-generated method stub
		StringBuffer sql1 = new StringBuffer();
		sql1.append(" SELECT DECODE (PLFL_PARAMETERID, PLCM_KEYID , 1, 0) AS selected, PLFL_KEYID, PLCM_KEYID, PLCM_LOSSNO, PLCM_PARAMETERNAME ");
		sql1.append(" FROM PCS_TL_LOGCONFIGURATION , PCS_TL_LOSSCELLLINK  " );
		sql1.append(" WHERE PLCM_ACTIVE ='Y' AND PLCM_ISHIDDEN='N' AND PLCM_SHOWLOSSNO='Y' " );
		sql1.append(" AND PLFL_PARAMETERID (+) = PLCM_KEYID " );
		
		sql1.append(" and PLFL_CELLID (+)='" + jhId + "' ");
		sql1.append(" AND PLFL_ACTIVE (+)='Y' "); 
		
		sql1.append(" ORDER BY PLCM_ORDER " );
		
		return sql1.toString();
	}
	
}

