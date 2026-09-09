package com.akranta.tpm.dao.sql;

//import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
public class PcsTlMstSql {

	public static final String TBL_PCS_TL_MST = "PCS_TL_MST";  

	TableFieldType [] prlmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, date, factoryid, sectionid, cellid, machineid, shiftid
		, entrydate, entryby, updatedby, updateddate, approvedby, approvedate
		, apporvedflag, shiftincharge, subgroupid, isnoplan, logtype
		, timeorqty, completedflag, tempfield2, flid, elementid,  active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getPrlmDbFields() {
		return prlmDbFields;
	}

	public PcsTlMstSql()
	{
		prlmDbFields = new TableFieldType[ 27 ];
		for(int i = 0;i < 27; i++)
		{	
			prlmDbFields[ i ] = new TableFieldType();
		}
		prlmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PRLM_KEYID";
		prlmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "PRLM_DATE";
		prlmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		prlmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "PRLM_FACTORYID";
		prlmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "PRLM_SECTIONID";
		prlmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "PRLM_CELLID";
		prlmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "PRLM_MACHINEID";
		prlmDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "PRLM_SHIFTID";
		prlmDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.entrydate.ordinal() ].fieldName = "PRLM_ENTRYDATE";
		prlmDbFields[ tableFldConstants.entrydate.ordinal() ].fieldType = 'D';

		prlmDbFields[ tableFldConstants.entryby.ordinal() ].fieldName = "PRLM_ENTRYBY";
		prlmDbFields[ tableFldConstants.entryby.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.updatedby.ordinal() ].fieldName = "PRLM_UPDATEDBY";
		prlmDbFields[ tableFldConstants.updatedby.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.updateddate.ordinal() ].fieldName = "PRLM_UPDATEDDATE";
		prlmDbFields[ tableFldConstants.updateddate.ordinal() ].fieldType = 'D';

		prlmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "PRLM_APPROVEDBY";
		prlmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.approvedate.ordinal() ].fieldName = "PRLM_APPROVEDATE";
		prlmDbFields[ tableFldConstants.approvedate.ordinal() ].fieldType = 'D';

		prlmDbFields[ tableFldConstants.apporvedflag.ordinal() ].fieldName = "PRLM_APPORVEDFLAG";
		prlmDbFields[ tableFldConstants.apporvedflag.ordinal() ].fieldType = 'C';

		prlmDbFields[ tableFldConstants.shiftincharge.ordinal() ].fieldName = "PRLM_SHIFTINCHARGE";
		prlmDbFields[ tableFldConstants.shiftincharge.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.subgroupid.ordinal() ].fieldName = "PRLM_SUBGROUPID";
		prlmDbFields[ tableFldConstants.subgroupid.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.isnoplan.ordinal() ].fieldName = "PRLM_ISNOPLAN";
		prlmDbFields[ tableFldConstants.isnoplan.ordinal() ].fieldType = 'C';

		prlmDbFields[ tableFldConstants.logtype.ordinal() ].fieldName = "PRLM_LOGTYPE";
		prlmDbFields[ tableFldConstants.logtype.ordinal() ].fieldType = 'C';

		prlmDbFields[ tableFldConstants.timeorqty.ordinal() ].fieldName = "PRLM_TIMEORQTY";
		prlmDbFields[ tableFldConstants.timeorqty.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.completedflag.ordinal() ].fieldName = "PRLM_COMPLETEDFLAG";
		prlmDbFields[ tableFldConstants.completedflag.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PRLM_TEMPFIELD2";
		prlmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "PRLM_FLID";
		prlmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "PRLM_ELEMENTID";
		prlmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PRLM_ACTIVE";
		prlmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		prlmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PRLM_CREATEDBY";
		prlmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		prlmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PRLM_CREATEDON";
		prlmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		prlmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PRLM_MODIFIEDON";
		prlmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_MST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_MST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getProdLossSql(String shiftId,String entryDate,String sectId, String cellId, String mchId, String detailTableName)
	{
		CommonMessage.debugMsg("sectId=="+sectId);
		StringBuffer sb = new StringBuffer();
		
		//sb.append("SELECT pldetailsid, plmasterid, cellid, MCHM_MACHINENAME as MACHINENO, MCHM_KEYID as machineid, operators,");
		sb.append("SELECT pldetailsid, plmasterid, cellid, CELL_NAME as MACHINENO, CELL_KEYID as machineid, operators,");
		sb.append("calendartime AS calendartime, noplaninmins AS noplaninmins,");
		sb.append("noofproducts AS noofproducts, prmm_name, prdm_keyid, prdm_code, PRDM_NAME, plannedqty AS plannedqty, rawmaterialtype AS rawmaterialtype,");
		sb.append("weight AS weight, wno,");
		//sb.append(" PCS_PC_PRODLOG.PCS_FN_GETMERGEFILED('WNO','" + detailTableName + "', '" + entryDate + "','" + shiftId + "',PRDM_KEYID,'" + cellId + "') as wno,  ");
		sb.append(" operationno,operationdescription, ");
		sb.append("  cavityavailable, cavityused,  mandrelavailable, mandrelused, " ) ;
		sb.append("theoriticalcycletime AS theoriticalcycletime,actualcycletime AS actualcycletime,INSPECTEDQTY, producedqty AS producedqty,");
		sb.append("defectsandreworkloss_ml AS defectsandreworkloss_ml,rejectedqty AS rejectedqty, reworkqty AS reworkqty,QAACCEPTEDQTY,");
		sb.append("defecttime_sl AS defecttime_sl, productionlosses AS productionlosses,equipmentfailure_ml AS equipmentfailure_ml,");
		sb.append("setupandadjustment_ml AS setupandadjustment_ml, toolchangeloss_ml AS toolchangeloss_ml,");
		sb.append("startuploss_ml AS startuploss_ml,minorstoppageloss_ml AS minorstoppageloss_ml,speedloss_ml AS speedloss_ml,");
		sb.append("shutdownloss_ml AS shutdownloss_ml,managementloss_ml AS managementloss_ml,commonutilityloss_sl AS commonutilityloss_sl,");
		sb.append("operatingmotionloss_ml AS operatingmotionloss_ml,lineorganisationloss_ml AS lineorganisationloss_ml,");
		sb.append("logisticsloss_ml AS logisticsloss_ml,measuringandadjloss_ml AS measuringandadjloss_ml,dietoolandjigloss_ml AS dietoolandjigloss_ml,");
		sb.append("energyloss_ml AS energyloss_ml, yieldloss_ml AS yieldloss_ml,unaccountedtime AS unaccountedtime,loadingtime AS loadingtime,");
		sb.append("effectiveprodmins AS effectiveprodmins, mchavailabletime AS mchavailabletime,productionavltime AS productionavltime,");
		sb.append("productiontime AS productiontime, " );
		sb.append("roa AS roa, rop AS rop, roq AS roq, oee AS oee, completedflag, remarks,");
		sb.append("loss01 AS loss01, loss02 AS loss02, loss03 AS loss03,loss04 AS loss04, loss05 AS loss05, loss06 AS loss06,");
		sb.append("loss07 AS loss07, loss08 AS loss08, loss09 AS loss09,loss10 AS loss10, loss11 AS loss11, loss12 AS loss12,");
		sb.append("loss13 AS loss13, loss14 AS loss14, loss15 AS loss15,loss16 AS loss16, loss17 AS loss17, loss18 AS loss18,");
		sb.append("loss19 AS loss19, loss20 AS loss20, loss21 AS loss21,loss22 AS loss22, loss23 AS loss23, loss24 AS loss24,");
		sb.append("loss25 AS loss25, loss26 AS loss26, loss27 AS loss27,loss28 AS loss28, loss29 AS loss29, loss30 AS loss30,");
		sb.append("loss31 AS loss31, loss32 AS loss32, loss33 AS loss33,loss34 AS loss34, loss35 AS loss35, loss36 AS loss36,");
		sb.append("loss37 AS loss37, loss38 AS loss38, loss39 AS loss39,loss40 AS loss40, loss41 AS loss41, loss42 AS loss42,");
	    sb.append("loss43 AS loss43, loss44 AS loss44, loss45 AS loss45,loss46 AS loss46, loss47 AS loss47, loss48 AS loss48,");
	    sb.append("loss49 AS loss49, loss50 AS loss50, loss51 AS loss51,loss52 AS loss52, loss53 AS loss53, loss54 AS loss54,");
	    
	    sb.append("loss55 AS loss55, loss56 AS loss56, loss57 AS loss57,loss58 AS loss58, loss59 AS loss59, loss60 AS loss60,");
	    sb.append("loss61 AS loss61, loss62 AS loss62, loss63 AS loss63,loss64 AS loss64, loss65 AS loss65, ");
	    sb.append("loss66 AS loss66, loss67 AS loss67, loss68 AS loss68,loss69 AS loss69, loss65 AS loss70, ");

	    sb.append("loss71 AS loss71, loss72 AS loss72, loss73 AS loss73,loss74 AS loss74, loss75 AS loss75, ");
	    sb.append("loss76 AS loss76, loss77 AS loss77, loss78 AS loss78,loss79 AS loss79, loss75 AS loss70, ");
	    sb.append("loss81 AS loss81, loss82 AS loss82, loss83 AS loss83,loss84 AS loss84, loss85 AS loss85, ");
	    sb.append("loss86 AS loss86, loss87 AS loss87, loss88 AS loss88,loss89 AS loss89, loss85 AS loss80, ");
	    sb.append("loss91 AS loss91, loss92 AS loss92, loss93 AS loss93,loss94 AS loss94, loss95 AS loss95, ");
	    sb.append("loss96 AS loss96, loss97 AS loss97, trimmingqty AS trimmingqty,expansionqty AS expansionqty, ");

	    sb.append("modelchangepart AS modelchangepart,reprocessing AS reprocessing");
	    
	    sb.append(" FROM (  ");
	    
	    /*sb.append(" SELECT MCHM_KEYID, MCHM_CELLID,  MCHM_MACHINENO, MCHM_MACHINENAME FROM GEN_TL_MACHINEMST  ");
	    sb.append(" WHERE MCHM_CELLID = '" + cellId + "' AND MCHM_ACTIVE = 'Y'  ");
	    */
	    
	    sb.append(" SELECT CELL_KEYID, CELL_SECTIONID,  CELL_CODE, CELL_NAME FROM GEN_TL_CELLMST  ");
	    sb.append(" WHERE CELL_SECTIONID = '" + sectId + "' AND CELL_ACTIVE = 'Y'  ");
	    
	    sb.append("  ),(  ");
	    sb.append("  SELECT *  FROM " + detailTableName + ",pcs_tl_mst,pcs_tl_productmst,pcs_tl_productmodelmst "); 
	    sb.append(" WHERE  PLMASTERID = PRLM_KEYID  and productid = PRDM_KEYID (+) and prdm_model = prmm_keyid (+) ");
	    
	    if(UIUtils.isValidKeyId(shiftId))
	    	 sb.append(" and prlm_shiftid  = ?");
	    if(UIUtils.isValidKeyId(entryDate))
	    	 sb.append(" and prlm_entrydate  = ?");
	    /*if(UIUtils.isValidKeyId(cellId))
	    	sb.append(" and PRLM_CELLID = ?");*/
	    if(UIUtils.isValidKeyId(sectId))
	    	sb.append(" and PRLM_SECTIONID = ?");
	    //sb.append(" ) WHERE  MCHM_KEYID = MACHINEID   (+)  " );
	    sb.append(" ) WHERE  CELL_KEYID = CELLID   (+)  " );
	    
	    sb.append(" AND ( ");
	    
	    /*sb.append(" MCHM_KEYID IN (SELECT PELC_MACHINEID FROM  PCS_TL_ENABLELOSSCAPTURE ");
	    sb.append(" WHERE  PELC_ISPCSENABLED = 'Y' AND PELC_ACTIVE ='Y' ) " );
	    sb.append(" OR MCHM_CELLID IN (SELECT PELC_CELLID FROM   PCS_TL_ENABLELOSSCAPTURE ");
	    sb.append(" WHERE    PELC_ISPCSENABLED = 'Y' AND PELC_ACTIVE ='Y' AND PELC_MACHINEID='{}' ) " );
	    */
	    
	    sb.append(" CELL_KEYID IN (SELECT DISTINCT PELC_CELLID FROM  PCS_TL_ENABLELOSSCAPTURE ");
	    sb.append(" WHERE  PELC_ISPCSENABLED = 'Y' AND PELC_ACTIVE ='Y' ) " );
	    
	    sb.append(" ) ");
	    
	    if (UIUtils.isValidKeyId(sectId))
	    	sb.append(" AND CELL_SECTIONID ='" + sectId + "'");
    
	    
	    /*if (UIUtils.isValidKeyId(mchId))
	    	sb.append(" AND MCHM_KEYID ='" + mchId + "'");
	    sb.append(" ORDER BY MCHM_MACHINENO ||'-' || MCHM_MACHINENAME, pldetailsid " );
	    */
	    
	    sb.append(" ORDER BY CELL_CODE ||'-' || CELL_NAME, pldetailsid " );    
	     
	    CommonMessage.debugMsg("PROD SQL=="+sb.toString());
		
		//sb.append("SELECT DISTINCT PCS_PC_PRODLOG.PCS_FN_GETPCSDETAILID('" + detailTableName + "', '" + entryDate + "','" + shiftId + "',PRDM_KEYID,'" + cellId + "'), plmasterid, cellid, mchm_machineno as MACHINENO, MCHM_KEYID as machineid, operators,");
		
/*		sb.append(" SELECT DISTINCT PCS_PC_PRODLOG.PCS_FN_GETMERGEFILED('PLDETAILSID','" + detailTableName + "', '" + entryDate + "','" + shiftId + "',PRDM_KEYID,'" + cellId + "') AS PLDETAILSID, plmasterid, ");
		sb.append(" cellid, mchm_machineno as MACHINENO, MCHM_KEYID as machineid, sum(operators) as operators,sum(calendartime) AS calendartime, sum(noplaninmins) AS noplaninmins,");
		sb.append(" sum(noofproducts) AS noofproducts, prmm_name, prdm_keyid, prdm_code, PRDM_NAME, sum(plannedqty) AS plannedqty, sum(rnt) AS rnt,cellmanning AS cellmanning, ");
		sb.append(" PCS_PC_PRODLOG.PCS_FN_GETMERGEFILED('WNO','" + detailTableName + "', '" + entryDate + "','" + shiftId + "',PRDM_KEYID,'" + cellId + "') as wno,  ");
		sb.append(" operationno,operationdescription,");
		sb.append(" SUM(CAVITYAVAILABLE) CAVITYAVAILABLE, SUM(CAVITYUSED) AS CAVITYUSED,  SUM(MANDRELAVAILABLE) AS MANDRELAVAILABLE, SUM(MANDRELUSED) AS MANDRELUSED, " ) ;
		sb.append(" sum(theoriticalcycletime) AS theoriticalcycletime,sum(actualcycletime) AS actualcycletime,");		
		sb.append(" sum(producedqty) AS producedqty, ");
		sb.append(" sum(defectsandreworkloss_ml) AS defectsandreworkloss_ml,sum(rejectedqty) AS rejectedqty, sum(reworkqty) AS reworkqty,sum(defecttime_sl) AS defecttime_sl, ");
		sb.append(" sum(productionlosses) AS productionlosses,sum(equipmentfailure_ml) AS equipmentfailure_ml,sum(setupandadjustment_ml) AS setupandadjustment_ml, ");
		sb.append(" sum(toolchangeloss_ml) AS toolchangeloss_ml,sum(startuploss_ml) AS startuploss_ml,sum(minorstoppageloss_ml) AS minorstoppageloss_ml,");
		sb.append(" sum(speedloss_ml) AS speedloss_ml,sum(shutdownloss_ml) AS shutdownloss_ml,sum(managementloss_ml) AS managementloss_ml,sum(commonutilityloss_sl) AS commonutilityloss_sl,");
		sb.append(" sum(operatingmotionloss_ml) AS operatingmotionloss_ml,sum(lineorganisationloss_ml) AS lineorganisationloss_ml,sum(logisticsloss_ml) AS logisticsloss_ml,");
		sb.append(" sum(measuringandadjloss_ml) AS measuringandadjloss_ml,sum(dietoolandjigloss_ml) AS dietoolandjigloss_ml,sum(energyloss_ml) AS energyloss_ml,");
		sb.append(" sum(yieldloss_ml) AS yieldloss_ml,sum(unaccountedtime) AS unaccountedtime,sum(loadingtime) AS loadingtime,sum(effectiveprodmins) AS effectiveprodmins, ");
		sb.append(" sum(mchavailabletime) AS mchavailabletime,sum(productionavltime) AS productionavltime,sum(productiontime) AS productiontime, ");
		sb.append(" sum(roa) AS roa, sum(rop) AS rop, sum(roq) AS roq, sum(oee) AS oee, ");
		sb.append(" PCS_PC_PRODLOG.PCS_FN_GETMERGEFILED('completedflag','PCS_TL_MLD', '02-May-2012','SFT001',PRDM_KEYID,'CEL004') AS completedflag,  " );
		sb.append(" PCS_PC_PRODLOG.PCS_FN_GETMERGEFILED('remarks','PCS_TL_MLD', '02-May-2012','SFT001',PRDM_KEYID,'CEL004') AS remarks, ");
		sb.append(" sum(loss01) AS loss01, sum(loss02) AS loss02, sum(loss03) AS loss03,sum(loss04) AS loss04, sum(loss05) AS loss05, sum(loss06) AS loss06,sum(loss07) AS loss07, sum(loss08) AS loss08, sum(loss09) AS loss09,sum(loss10) AS loss10, ");
		sb.append(" sum(loss11) AS loss11, sum(loss12) AS loss12,sum(loss13) AS loss13, sum(loss14) AS loss14, sum(loss15) AS loss15,sum(loss16) AS loss16, sum(loss17) AS loss17, sum(loss18) AS loss18,sum(loss19) AS loss19, sum(loss20) AS loss20, ");
		sb.append(" sum(loss21) AS loss21,sum(loss22) AS loss22, sum(loss23) AS loss23, sum(loss24) AS loss24,sum(loss25) AS loss25, sum(loss26) AS loss26, sum(loss27) AS loss27,sum(loss28) AS loss28, sum(loss29) AS loss29, sum(loss30) AS loss30,");
		sb.append(" sum(loss31) AS loss31, sum(loss32) AS loss32, sum(loss33) AS loss33,sum(loss34) AS loss34, sum(loss35) AS loss35, sum(loss36) AS loss36,sum(loss37) AS loss37, sum(loss38) AS loss38, sum(loss39) AS loss39,sum(loss40) AS loss40, ");
		sb.append(" sum(loss41) AS loss41, sum(loss42) AS loss42,sum(loss43) AS loss43, sum(loss44) AS loss44, sum(loss45) AS loss45,sum(loss46) AS loss46, sum(loss47) AS loss47, sum(loss48) AS loss48,sum(loss49) AS loss49, sum(loss50) AS loss50, ");
		sb.append(" sum(loss51) AS loss51,sum(loss52) AS loss52, sum(loss53) AS loss53, sum(loss54) AS loss54,sum(loss55) AS loss55, ");
		sb.append(" PCS_PC_PRODLOG.PCS_FN_GETMERGEFILED('modelchangepart','" + detailTableName + "', '" + entryDate + "','" + shiftId + "',PRDM_KEYID,'" + cellId + "') AS modelchangepart,");
	    sb.append(" PCS_PC_PRODLOG.PCS_FN_GETMERGEFILED('reprocessing','" + detailTableName + "', '" + entryDate + "','" + shiftId + "',PRDM_KEYID,'" + cellId + "') AS reprocessing ");
	    sb.append(" FROM (  ");
	    sb.append(" SELECT MCHM_KEYID, MCHM_MACHINENO, MCHM_MACHINENAME FROM GEN_TL_MACHINEMST  ");
	    sb.append(" WHERE MCHM_CELLID = '" + cellId + "' AND MCHM_ACTIVE = 'Y'  ");
	    sb.append("  ),(  ");
	    sb.append("  SELECT *  FROM " + detailTableName + ",pcs_tl_mst,pcs_tl_productmst,pcs_tl_productmodelmst "); 
	    sb.append(" WHERE  PLMASTERID = PRLM_KEYID  and productid = PRDM_KEYID  and prdm_model = prmm_keyid ");
	    
	    if(UIUtils.isValidKeyId(shiftId))
	    	 sb.append(" and prlm_shiftid  = ?");
	    if(UIUtils.isValidKeyId(entryDate))
	    	 sb.append(" and prlm_entrydate  = ?");
	    if(UIUtils.isValidKeyId(cellId))
	    	sb.append(" and PRLM_CELLID = ?");
	    
	    sb.append(" ) WHERE  MCHM_KEYID = MACHINEID   (+)  " );
	    sb.append(" AND MCHM_KEYID IN (SELECT PELC_MACHINEID FROM    PCS_TL_ENABLELOSSCAPTURE  WHERE    PELC_ISPCSENABLED = 'Y' ) " );
	    
	    sb.append(" GROUP BY plmasterid, cellid, mchm_machineno , MCHM_KEYID ,  prmm_name, prdm_keyid, prdm_code, PRDM_NAME, cellmanning, "); 
	    sb.append(" operationno,operationdescription,completedflag, remarks,modelchangepart ,reprocessing  ");
	    sb.append(" ORDER BY MCHM_KEYID " );    
	*/	
	    
	    return sb.toString();
	}
	public static String getProductDetailSql(String shiftId,String entryDate,String cellId, String mchId, String detailTableName,String dtlId)
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT pldetailsid, plmasterid, cellid, mchm_machineno as MACHINENO, MCHM_KEYID as machineid, operators,");
		sb.append("calendartime AS calendartime, noplaninmins AS noplaninmins,");
		sb.append("noofproducts AS noofproducts, prmm_name, prdm_keyid, prdm_code, PRDM_NAME, plannedqty AS plannedqty, rawmaterialtype AS rawmaterialtype,");
		sb.append("weight AS weight, wno,");		
		sb.append(" operationno,operationdescription, ");
		sb.append("  cavityavailable, cavityused,  mandrelavailable, mandrelused, " ) ;
		sb.append("theoriticalcycletime AS theoriticalcycletime,actualcycletime AS actualcycletime,INSPECTEDQTY, producedqty AS producedqty,");
		sb.append("defectsandreworkloss_ml AS defectsandreworkloss_ml,rejectedqty AS rejectedqty, reworkqty AS reworkqty,QAACCEPTEDQTY,");
		sb.append("defecttime_sl AS defecttime_sl, productionlosses AS productionlosses,equipmentfailure_ml AS equipmentfailure_ml,");
		sb.append("setupandadjustment_ml AS setupandadjustment_ml, toolchangeloss_ml AS toolchangeloss_ml,");
		sb.append("startuploss_ml AS startuploss_ml,minorstoppageloss_ml AS minorstoppageloss_ml,speedloss_ml AS speedloss_ml,");
		sb.append("shutdownloss_ml AS shutdownloss_ml,managementloss_ml AS managementloss_ml,commonutilityloss_sl AS commonutilityloss_sl,");
		sb.append("operatingmotionloss_ml AS operatingmotionloss_ml,lineorganisationloss_ml AS lineorganisationloss_ml,");
		sb.append("logisticsloss_ml AS logisticsloss_ml,measuringandadjloss_ml AS measuringandadjloss_ml,dietoolandjigloss_ml AS dietoolandjigloss_ml,");
		sb.append("energyloss_ml AS energyloss_ml, yieldloss_ml AS yieldloss_ml,unaccountedtime AS unaccountedtime,loadingtime AS loadingtime,");
		sb.append("effectiveprodmins AS effectiveprodmins, mchavailabletime AS mchavailabletime,productionavltime AS productionavltime,");
		sb.append("productiontime AS productiontime, " );
		sb.append("roa AS roa, rop AS rop, roq AS roq, oee AS oee, completedflag, remarks,");
		sb.append("loss01 AS loss01, loss02 AS loss02, loss03 AS loss03,loss04 AS loss04, loss05 AS loss05, loss06 AS loss06,");
		sb.append("loss07 AS loss07, loss08 AS loss08, loss09 AS loss09,loss10 AS loss10, loss11 AS loss11, loss12 AS loss12,");
		sb.append("loss13 AS loss13, loss14 AS loss14, loss15 AS loss15,loss16 AS loss16, loss17 AS loss17, loss18 AS loss18,");
		sb.append("loss19 AS loss19, loss20 AS loss20, loss21 AS loss21,loss22 AS loss22, loss23 AS loss23, loss24 AS loss24,");
		sb.append("loss25 AS loss25, loss26 AS loss26, loss27 AS loss27,loss28 AS loss28, loss29 AS loss29, loss30 AS loss30,");
		sb.append("loss31 AS loss31, loss32 AS loss32, loss33 AS loss33,loss34 AS loss34, loss35 AS loss35, loss36 AS loss36,");
		sb.append("loss37 AS loss37, loss38 AS loss38, loss39 AS loss39,loss40 AS loss40, loss41 AS loss41, loss42 AS loss42,");
	    sb.append("loss43 AS loss43, loss44 AS loss44, loss45 AS loss45,loss46 AS loss46, loss47 AS loss47, loss48 AS loss48,");
	    sb.append("loss49 AS loss49, loss50 AS loss50, loss51 AS loss51,loss52 AS loss52, loss53 AS loss53, loss54 AS loss54,");
	    
	    sb.append("loss55 AS loss55, loss56 AS loss56, loss57 AS loss57,loss58 AS loss58, loss59 AS loss59, loss60 AS loss60,");
	    sb.append("loss61 AS loss61, loss62 AS loss62, loss63 AS loss63,loss64 AS loss64, loss65 AS loss65, ");
	    sb.append("loss66 AS loss66, loss67 AS loss67, loss68 AS loss68,loss69 AS loss69, loss65 AS loss70, ");

	    sb.append("loss71 AS loss71, loss72 AS loss72, loss73 AS loss73,loss74 AS loss74, loss75 AS loss75, ");
	    sb.append("loss76 AS loss76, loss77 AS loss77, loss78 AS loss78,loss79 AS loss79, loss75 AS loss70, ");
	    sb.append("loss81 AS loss81, loss82 AS loss82, loss83 AS loss83,loss84 AS loss84, loss85 AS loss85, ");
	    sb.append("loss86 AS loss86, loss87 AS loss87, loss88 AS loss88,loss89 AS loss89, loss85 AS loss80, ");
	    sb.append("loss91 AS loss91, loss92 AS loss92, loss93 AS loss93,loss94 AS loss94, loss95 AS loss95, ");
	    sb.append("loss96 AS loss96, loss97 AS loss97, trimmingqty AS trimmingqty,expansionqty AS expansionqty, ");

	    sb.append("modelchangepart AS modelchangepart,reprocessing AS reprocessing");
	    
	    sb.append(" FROM (  ");
	    sb.append(" SELECT MCHM_KEYID, MCHM_CELLID, MCHM_MACHINENO, MCHM_MACHINENAME FROM GEN_TL_MACHINEMST  ");
	    sb.append(" WHERE MCHM_CELLID = '" + cellId + "' AND MCHM_ACTIVE = 'Y'  ");
	    sb.append("  ),(  ");
	    sb.append("  SELECT *  FROM " + detailTableName + ",pcs_tl_mst,pcs_tl_productmst,pcs_tl_productmodelmst "); 
	    sb.append(" WHERE  PLMASTERID = PRLM_KEYID  and productid = PRDM_KEYID (+) and prdm_model = prmm_keyid (+) ");
	    
	    if(UIUtils.isValidKeyId(shiftId))
	    	 sb.append(" and prlm_shiftid  = ?");
	    if(UIUtils.isValidKeyId(entryDate))
	    	 sb.append(" and prlm_entrydate  = ?");
	    if(UIUtils.isValidKeyId(cellId))
	    	sb.append(" and PRLM_CELLID = ?");
	    if(UIUtils.isValidKeyId(dtlId))
	    	sb.append(" and pldetailsid = ?");
	    
	    sb.append(" ) WHERE  MCHM_KEYID = MACHINEID   (+)  " );
	    
	    //sb.append(" AND MCHM_KEYID IN (SELECT PELC_MACHINEID FROM    PCS_TL_ENABLELOSSCAPTURE  WHERE    PELC_ISPCSENABLED = 'Y' ) " );	    
	    
	    sb.append(" AND ( ");
	    sb.append(" MCHM_KEYID IN (SELECT PELC_MACHINEID FROM  PCS_TL_ENABLELOSSCAPTURE ");
	    sb.append(" WHERE  PELC_ISPCSENABLED = 'Y' AND PELC_ACTIVE = ''Y' ) " );
	    sb.append(" OR MCHM_CELLID IN (SELECT PELC_CELLID FROM   PCS_TL_ENABLELOSSCAPTURE ");
	    sb.append(" WHERE    PELC_ISPCSENABLED = 'Y' AND PELC_ACTIVE = ''Y' AND PELC_MACHINEID='{}') " );
	    sb.append(" ) ");
	    
	    if (UIUtils.isValidKeyId(mchId))
	    	sb.append(" AND MCHM_KEYID ='" + mchId + "'");
	    
	    sb.append(" ORDER BY MCHM_MACHINENO ||'-' || MCHM_MACHINENAME, pldetailsid " );    
	   CommonMessage.debugMsg("Sql : "+sb.toString());
	    return sb.toString();
	}
	public static StringBuffer getNoPlanTimeDuration(String mchId, String date, String shift)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("  SELECT TO_CHAR(BDSS_NOPLANSTARTDT, 'DD-Mon-YYYY HH24:MI') AS \"NOPLAN START TIME\", ");
		sql.append(" TO_CHAR(BDSS_NOPLANENDDT,'DD-Mon-YYYY HH24:MI') AS \"NOPLAN END TIME\", BDSS_NOPLANTIME AS NOPLANDURATION ");
		sql.append(" FROM BDM_TL_SHIFTWISESPLIT WHERE BDSS_MACHINEID ='"+mchId+"' AND BDSS_BDSPLITDATE ='"+date+"' AND BDSS_SHIFTID ='"+shift+"'");
		sql.append(" AND TRUNC(BDSS_NOPLANSTARTDT) <> '01-JAN-1801' AND TRUNC(BDSS_NOPLANENDDT) <> '31-DEC-2100' AND BDSS_NOPLANTIME > 0");
		sql.append(" UNION SELECT TO_CHAR(TO_DATE('"+date+"', 'DD-MON-YYYY'),'DD-Mon-YYYY') || TO_CHAR(SFTM_STARTTIME,' HH24:MI') AS \"NOPLAN START TIME\","); 
		sql.append(" DECODE(FLOOR(TO_DATE(TO_CHAR(SFTM_ENDTIME, ' HH24:MI'),' HH24:MI')-TO_DATE(TO_CHAR(SFTM_STARTTIME,' HH24:MI'),' HH24:MI')),-1,"); 
		sql.append(" TO_CHAR(TO_DATE('"+date+"','DD-MON-YYYY')+1,'DD-Mon-YYYY'),TO_CHAR(TO_DATE('"+date+"','DD-MON-YYYY'),'DD-Mon-YYYY')) || TO_CHAR(SFTM_ENDTIME, ' HH24:MI') AS \"NOPLAN END TIME\","); 
		sql.append(" ROUND((SFTM_ENDTIME - SFTM_STARTTIME) * 1440) AS NOPLANDURATION FROM GEN_TL_SHIFTMST WHERE SFTM_KEYID ='"+shift+"'"); 
		sql.append(" AND SFTM_FACTORYID IN (SELECT CELL_FACTORYID FROM GEN_TL_MACHINEMST, GEN_TL_CELLMST");
		sql.append(" WHERE MCHM_CELLID = CELL_KEYID AND MCHM_KEYID = '"+mchId+"') ORDER BY NOPLANDURATION DESC");
		return sql;
	}
	public String getCount(String pldetailsId) {
		// TODO Auto-generated method stub
		return " SELECT COUNT(*) FROM PCS_TL_LOSSREASONLINK  WHERE PLRK_PLDETAILID = '" + pldetailsId + "' ";
	}

	public String delWOLink(String workOrderNo, String pldetailsId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM PCS_TL_WORKORDERLINK WHERE PTWO_WNO ='" + workOrderNo + "' ";
			   sql += " AND PTWO_PLDETAILID ='" + pldetailsId + "' ";
		return sql;
	}

	public String delDetail(String detailTableName, String pldetailsId) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(); 
		sql.append(" DELETE FROM " + detailTableName + " ");
		sql.append(" WHERE PLDETAILSID = '" + pldetailsId + "' ");	
		return sql.toString();
	}

	public String delpcsMaster(String plmasterId) {
		// TODO Auto-generated method stub
		return " DELETE FROM PCS_TL_MST WHERE PRLM_KEYID = '" + plmasterId + "' ";	
	}

	public String delPcsTLDAY(String entryDate, String machKeyId) {
		// TODO Auto-generated method stub
		return " DELETE FROM PCS_TL_DAY WHERE ENTRYDATE = '"+entryDate+"' AND MACHINEID = '"+machKeyId+"'";
	}

	//------------------------------------ 31Oct2025 ---- Vignesh ------------------------------------------------------//
	
	public Object getLossEntrySql(String flid, String fromDate, String toDate) {
	    StringBuilder sql = new StringBuilder();

	    sql.append("SELECT FLID, PLOSSDATE, DMT, JH, SHIFTID, COUNT(*) ");
	    sql.append("FROM ( ");
	    sql.append("  SELECT ");
	    sql.append("    h.flid AS FLID, ");
	    sql.append("    l.plos_date, ");
	    sql.append("    to_char(l.plos_date::date, 'DD-MON-YYYY') AS PLOSSDATE, ");
	    sql.append("    split_part(h.parents, '/', 4) AS DMT, ");
	    sql.append("    coalesce(array_to_string((string_to_array(h.parents,'/'))[5:], '/'), '') AS JH, ");
	    sql.append("    l.plos_shiftid AS SHIFTID ");
	    sql.append("  FROM pcs_tl_losscapture l ");
	    sql.append("  JOIN gen_mv_flidhierarchy h ON l.plos_flid = h.flid ");
	    sql.append("  WHERE position('").append(flid).append("' in (h.parentflids || '/' || h.flid)) > 0 ");
	    sql.append("    AND l.plos_date::date >= to_date('").append(fromDate).append("','DD-MON-YYYY') ");
	    sql.append("    AND l.plos_date::date <= to_date('").append(toDate).append("','DD-MON-YYYY') ");
	    sql.append(") x ");
	    sql.append("GROUP BY FLID, PLOSSDATE, PLOS_DATE, DMT, JH, SHIFTID ");
	    sql.append("ORDER BY PLOS_DATE DESC");

	    return sql;
	}

//	public Object getLossEntrySql(String flid, String fromDate, String toDate) {
//		// TODO Auto-generated method stub
//		StringBuilder sql =new StringBuilder();
//		sql.append("select FLID,PLOSSDATE, DMT,JH,SHIFTID, COUNT(*) FROM ( ");
//		sql.append("SELECT FLID,PLOS_DATE,TO_CHAR((PLOS_DATE),'DD-MON-YYYY') AS PLOSSDATE, ");
//		sql.append("SUBSTR(PARENTS, INSTR(PARENTS,'/',1,3)+1,LENGTH(SUBSTR(PARENTS,INSTR(PARENTS,'/',1,3)+1,INSTR(PARENTS,'/',1,4)-INSTR(PARENTS,'/',1,3)-1))) DMT, ");
//		sql.append("SUBSTR(PARENTS, INSTR(PARENTS,'/',1,4)+1, LENGTH(SUBSTR(PARENTS,INSTR(PARENTS,'/',1,4)+1))) JH,PLOS_SHIFTID SHIFTID ");
//		sql.append(" FROM PCS_TL_LOSSCAPTURE,GEN_MV_FLIDHIERARCHY ");
//		sql.append("WHERE  PLOS_FLID=FLID AND INSTR(PARENTFLIDS||'/'||FLID,'");
//		sql.append(flid);
//		sql.append("')>0 AND TO_DATE(PLOS_DATE) >= TO_DATE('");
//		sql.append(fromDate);
//		sql.append("' ) AND TO_DATE(PLOS_DATE) <= TO_DATE('");
//		sql.append(toDate);
//		sql.append("')) ");
//		sql.append("GROUP BY FLID,PLOSSDATE,PLOS_DATE,DMT,JH,SHIFTID order by PLOS_DATE DESC");
//		return sql;
//	}

	
	
	//------------------------------------ 31Oct2025 ---- Vignesh ------------------------------------------------------//

}

