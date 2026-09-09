package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlMachinemstSql {

	public static final String TBL_GEN_TL_MACHINEMST = "GEN_TL_MACHINEMST";
	private static final String TBL_GEN_TL_EMPLOYEEMST = "GEN_TL_EMPLOYEEMST";

	private static final String TBL_GEN_TL_MACHINESKILLMST = "GEN_TL_MACHINESKILLMST";
	private static final String TBL_GEN_TL_MAINTENANCETEAMMST = "GEN_TL_MAINTENANCETEAMMST";
	private static final String TBL_GEN_TL_MCHMAINTTEAMLINK = "GEN_TL_MCHMAINTTEAMLINK";

	private static final String TBL_GEN_TL_MCHEMPLINK = "GEN_TL_MCHEMPLINK";

	private static final String TBL_GEN_TL_MCHPARAMETERLINK = "GEN_TL_MCHPARAMETERLINK ";

	private static final String TBL_GEN_TL_MCHSUBMCHLINK = "GEN_TL_MCHSUBMCHLINK";

	private static final String TBL_GEN_TL_FUNCTIONALLOCN = null;

	TableFieldType[] mchmDbFields = null;

	public enum tableFldConstants {
		keyid, machineno, machinename, cellid, subcellid, equipmentgroup, controltype, purpose, category, subcategory,
		machinerank, jhstep, jhstepdate, phase, wires, ipvolt, ipvoltmin, ipvoltmax, ipfreq, ipfreqmin, ipfreqmax,
		powersupply, connectedload, dbno, sbno, specification, remarks, manufacturerid, manufactureddate, make, model,
		mfrslno, mfrremarks, supplierid, pono, podate, purchasedate, purchaseprice, installeddate, isunderwarranty,
		warrantydate, supplierremarks, isunderamc, amcdate, amcvendor, amcrenewaldate, amcremarks, machineorder,
		effectivedate, inactivateddate, includeforproduction, givesfinaloutput, costcentreid, circleid,
		iscavityormandrel, maxmeterreading, currencyid, workcenter, technicalid, type, tradeid,tempfield3 ,tempfield4 ,tempfield5, elementid, flid, active, createdby, createdon,
		modifiedon
	}

	public TableFieldType[] getMchmDbFields() {
		return mchmDbFields;
	}

	public GenTlMachinemstSql() {
		mchmDbFields = new TableFieldType[70];
		for (int i = 0; i < 70; i++) {
			mchmDbFields[i] = new TableFieldType();
		}
		mchmDbFields[tableFldConstants.keyid.ordinal()].fieldName = "MCHM_KEYID";
		mchmDbFields[tableFldConstants.keyid.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.machineno.ordinal()].fieldName = "MCHM_MACHINENO";
		mchmDbFields[tableFldConstants.machineno.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.machinename.ordinal()].fieldName = "MCHM_MACHINENAME";
		mchmDbFields[tableFldConstants.machinename.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.cellid.ordinal()].fieldName = "MCHM_CELLID";
		mchmDbFields[tableFldConstants.cellid.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.subcellid.ordinal()].fieldName = "MCHM_SUBCELLID";
		mchmDbFields[tableFldConstants.subcellid.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.equipmentgroup.ordinal()].fieldName = "MCHM_EQUIPMENTGROUP";
		mchmDbFields[tableFldConstants.equipmentgroup.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.controltype.ordinal()].fieldName = "MCHM_CONTROLTYPE";
		mchmDbFields[tableFldConstants.controltype.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.purpose.ordinal()].fieldName = "MCHM_PURPOSE";
		mchmDbFields[tableFldConstants.purpose.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.category.ordinal()].fieldName = "MCHM_CATEGORY";
		mchmDbFields[tableFldConstants.category.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.subcategory.ordinal()].fieldName = "MCHM_SUBCATEGORY";
		mchmDbFields[tableFldConstants.subcategory.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.machinerank.ordinal()].fieldName = "MCHM_MACHINERANK";
		mchmDbFields[tableFldConstants.machinerank.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.jhstep.ordinal()].fieldName = "MCHM_JHSTEP";
		mchmDbFields[tableFldConstants.jhstep.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.jhstepdate.ordinal()].fieldName = "MCHM_JHSTEPDATE";
		mchmDbFields[tableFldConstants.jhstepdate.ordinal()].fieldType = 'D';

		mchmDbFields[tableFldConstants.phase.ordinal()].fieldName = "MCHM_PHASE";
		mchmDbFields[tableFldConstants.phase.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.wires.ordinal()].fieldName = "MCHM_WIRES";
		mchmDbFields[tableFldConstants.wires.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.ipvolt.ordinal()].fieldName = "MCHM_IPVOLT";
		mchmDbFields[tableFldConstants.ipvolt.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.ipvoltmin.ordinal()].fieldName = "MCHM_IPVOLTMIN";
		mchmDbFields[tableFldConstants.ipvoltmin.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.ipvoltmax.ordinal()].fieldName = "MCHM_IPVOLTMAX";
		mchmDbFields[tableFldConstants.ipvoltmax.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.ipfreq.ordinal()].fieldName = "MCHM_IPFREQ";
		mchmDbFields[tableFldConstants.ipfreq.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.ipfreqmin.ordinal()].fieldName = "MCHM_IPFREQMIN";
		mchmDbFields[tableFldConstants.ipfreqmin.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.ipfreqmax.ordinal()].fieldName = "MCHM_IPFREQMAX";
		mchmDbFields[tableFldConstants.ipfreqmax.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.powersupply.ordinal()].fieldName = "MCHM_POWERSUPPLY";
		mchmDbFields[tableFldConstants.powersupply.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.connectedload.ordinal()].fieldName = "MCHM_CONNECTEDLOAD";
		mchmDbFields[tableFldConstants.connectedload.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.dbno.ordinal()].fieldName = "MCHM_DBNO";
		mchmDbFields[tableFldConstants.dbno.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.sbno.ordinal()].fieldName = "MCHM_SBNO";
		mchmDbFields[tableFldConstants.sbno.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.specification.ordinal()].fieldName = "MCHM_SPECIFICATION";
		mchmDbFields[tableFldConstants.specification.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.remarks.ordinal()].fieldName = "MCHM_REMARKS";
		mchmDbFields[tableFldConstants.remarks.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.manufacturerid.ordinal()].fieldName = "MCHM_MANUFACTURERID";
		mchmDbFields[tableFldConstants.manufacturerid.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.manufactureddate.ordinal()].fieldName = "MCHM_MANUFACTUREDDATE";
		mchmDbFields[tableFldConstants.manufactureddate.ordinal()].fieldType = 'D';

		mchmDbFields[tableFldConstants.make.ordinal()].fieldName = "MCHM_MAKE";
		mchmDbFields[tableFldConstants.make.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.model.ordinal()].fieldName = "MCHM_MODEL";
		mchmDbFields[tableFldConstants.model.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.mfrslno.ordinal()].fieldName = "MCHM_MFRSLNO";
		mchmDbFields[tableFldConstants.mfrslno.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.mfrremarks.ordinal()].fieldName = "MCHM_MFRREMARKS";
		mchmDbFields[tableFldConstants.mfrremarks.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.supplierid.ordinal()].fieldName = "MCHM_SUPPLIERID";
		mchmDbFields[tableFldConstants.supplierid.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.pono.ordinal()].fieldName = "MCHM_PONO";
		mchmDbFields[tableFldConstants.pono.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.podate.ordinal()].fieldName = "MCHM_PODATE";
		mchmDbFields[tableFldConstants.podate.ordinal()].fieldType = 'D';

		mchmDbFields[tableFldConstants.purchasedate.ordinal()].fieldName = "MCHM_PURCHASEDATE";
		mchmDbFields[tableFldConstants.purchasedate.ordinal()].fieldType = 'D';

		mchmDbFields[tableFldConstants.purchaseprice.ordinal()].fieldName = "MCHM_PURCHASEPRICE";
		mchmDbFields[tableFldConstants.purchaseprice.ordinal()].fieldType = 'N';

		mchmDbFields[tableFldConstants.installeddate.ordinal()].fieldName = "MCHM_INSTALLEDDATE";
		mchmDbFields[tableFldConstants.installeddate.ordinal()].fieldType = 'D';

		mchmDbFields[tableFldConstants.isunderwarranty.ordinal()].fieldName = "MCHM_ISUNDERWARRANTY";
		mchmDbFields[tableFldConstants.isunderwarranty.ordinal()].fieldType = 'C';

		mchmDbFields[tableFldConstants.warrantydate.ordinal()].fieldName = "MCHM_WARRANTYDATE";
		mchmDbFields[tableFldConstants.warrantydate.ordinal()].fieldType = 'D';

		mchmDbFields[tableFldConstants.supplierremarks.ordinal()].fieldName = "MCHM_SUPPLIERREMARKS";
		mchmDbFields[tableFldConstants.supplierremarks.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.isunderamc.ordinal()].fieldName = "MCHM_ISUNDERAMC";
		mchmDbFields[tableFldConstants.isunderamc.ordinal()].fieldType = 'C';

		mchmDbFields[tableFldConstants.amcdate.ordinal()].fieldName = "MCHM_AMCDATE";
		mchmDbFields[tableFldConstants.amcdate.ordinal()].fieldType = 'D';

		mchmDbFields[tableFldConstants.amcvendor.ordinal()].fieldName = "MCHM_AMCVENDOR";
		mchmDbFields[tableFldConstants.amcvendor.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.amcrenewaldate.ordinal()].fieldName = "MCHM_AMCRENEWALDATE";
		mchmDbFields[tableFldConstants.amcrenewaldate.ordinal()].fieldType = 'D';

		mchmDbFields[tableFldConstants.amcremarks.ordinal()].fieldName = "MCHM_AMCREMARKS";
		mchmDbFields[tableFldConstants.amcremarks.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.machineorder.ordinal()].fieldName = "MCHM_MACHINEORDER";
		mchmDbFields[tableFldConstants.machineorder.ordinal()].fieldType = 'N';

		mchmDbFields[tableFldConstants.effectivedate.ordinal()].fieldName = "MCHM_EFFECTIVEDATE";
		mchmDbFields[tableFldConstants.effectivedate.ordinal()].fieldType = 'D';

		mchmDbFields[tableFldConstants.inactivateddate.ordinal()].fieldName = "MCHM_INACTIVATEDDATE";
		mchmDbFields[tableFldConstants.inactivateddate.ordinal()].fieldType = 'D';

		mchmDbFields[tableFldConstants.includeforproduction.ordinal()].fieldName = "MCHM_INCLUDEFORPRODUCTION";
		mchmDbFields[tableFldConstants.includeforproduction.ordinal()].fieldType = 'C';

		mchmDbFields[tableFldConstants.givesfinaloutput.ordinal()].fieldName = "MCHM_GIVESFINALOUTPUT";
		mchmDbFields[tableFldConstants.givesfinaloutput.ordinal()].fieldType = 'C';

		mchmDbFields[tableFldConstants.costcentreid.ordinal()].fieldName = "MCHM_COSTCENTREID";
		mchmDbFields[tableFldConstants.costcentreid.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.circleid.ordinal()].fieldName = "MCHM_CIRCLEID";
		mchmDbFields[tableFldConstants.circleid.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.iscavityormandrel.ordinal()].fieldName = "MCHM_ISCAVITYORMANDREL";
		mchmDbFields[tableFldConstants.iscavityormandrel.ordinal()].fieldType = 'C';

		mchmDbFields[tableFldConstants.maxmeterreading.ordinal()].fieldName = "MCHM_MAXMETERREADING";
		mchmDbFields[tableFldConstants.maxmeterreading.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.currencyid.ordinal()].fieldName = "MCHM_CURRENCYID";
		mchmDbFields[tableFldConstants.currencyid.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.workcenter.ordinal()].fieldName = "MCHM_WORKCENTER";
		mchmDbFields[tableFldConstants.workcenter.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.technicalid.ordinal()].fieldName = "MCHM_TECHNICALID";
		mchmDbFields[tableFldConstants.technicalid.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.type.ordinal()].fieldName = "MCHM_TYPE";
		mchmDbFields[tableFldConstants.type.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.tradeid.ordinal()].fieldName = "MCHM_TRADEID";
		mchmDbFields[tableFldConstants.tradeid.ordinal()].fieldType = 'V';

//		mchmDbFields[tableFldConstants.pmfunctionallocn.ordinal()].fieldName = "MCHM_PMFUNCTIONALLOCN";
//		mchmDbFields[tableFldConstants.pmfunctionallocn.ordinal()].fieldType = 'V';
//
//		mchmDbFields[tableFldConstants.plannergroup.ordinal()].fieldName = "MCHM_PLANNERGROUP";
//		mchmDbFields[tableFldConstants.plannergroup.ordinal()].fieldType = 'V';
//
//		mchmDbFields[tableFldConstants.wbselement.ordinal()].fieldName = "MCHM_WBSELEMENT";
//		mchmDbFields[tableFldConstants.wbselement.ordinal()].fieldType = 'V';
		
		mchmDbFields[tableFldConstants.tempfield3.ordinal()].fieldName = "MCHM_TEMPFIELD3";
		mchmDbFields[tableFldConstants.tempfield3.ordinal()].fieldType = 'V';
		
		mchmDbFields[tableFldConstants.tempfield4.ordinal()].fieldName = "MCHM_TEMPFIELD4";
		mchmDbFields[tableFldConstants.tempfield4.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.tempfield5.ordinal()].fieldName = "MCHM_TEMPFIELD5";
		mchmDbFields[tableFldConstants.tempfield5.ordinal()].fieldType = 'V';


		mchmDbFields[tableFldConstants.elementid.ordinal()].fieldName = "MCHM_ELEMENTID";
		mchmDbFields[tableFldConstants.elementid.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.flid.ordinal()].fieldName = "MCHM_FLID";
		mchmDbFields[tableFldConstants.flid.ordinal()].fieldType = 'V';

//		mchmDbFields[tableFldConstants.purchaseorg.ordinal()].fieldName = "MCHM_PURCHASE_ORG ";
//		mchmDbFields[tableFldConstants.purchaseorg.ordinal()].fieldType = 'V';
//
//		mchmDbFields[tableFldConstants.maintplant.ordinal()].fieldName = "MCHM_MAINT_PLANT";
//		mchmDbFields[tableFldConstants.maintplant.ordinal()].fieldType = 'V';
//
//		mchmDbFields[tableFldConstants.planningplant.ordinal()].fieldName = "MCHM_PLANNING_PLANT";
//		mchmDbFields[tableFldConstants.planningplant.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.active.ordinal()].fieldName = "MCHM_ACTIVE";
		mchmDbFields[tableFldConstants.active.ordinal()].fieldType = 'C';

		mchmDbFields[tableFldConstants.createdby.ordinal()].fieldName = "MCHM_CREATEDBY";
		mchmDbFields[tableFldConstants.createdby.ordinal()].fieldType = 'V';

		mchmDbFields[tableFldConstants.createdon.ordinal()].fieldName = "MCHM_CREATEDON";
		mchmDbFields[tableFldConstants.createdon.ordinal()].fieldType = 'D';

		mchmDbFields[tableFldConstants.modifiedon.ordinal()].fieldName = "MCHM_MODIFIEDON";
		mchmDbFields[tableFldConstants.modifiedon.ordinal()].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType[] fieldTypeArr, Object[] dataArray) {
		return SqlUtils.getInsertSql(TBL_GEN_TL_MACHINEMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType[] fieldTypeArr, Object[] dataArray) {
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MACHINEMST, fieldTypeArr, dataArray);

		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName + " = '"
				+ (String) dataArray[tableFldConstants.keyid.ordinal()] + "'";
		CommonMessage.debugMsg(sql);
		return sql;
	}

	public static String getDeleteSql(String delMode, TableFieldType[] fieldTypeArr, Object[] dataArray) {
		String sql = "";

		if (delMode.equals("I")) {
			sql = "UPDATE " + TBL_GEN_TL_MACHINEMST;
			sql += " SET " + fieldTypeArr[tableFldConstants.active.ordinal()].fieldName + " = 'N'";
			sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName + " = '"
					+ (String) dataArray[tableFldConstants.keyid.ordinal()] + "'";

		} else {

			sql = "DELETE from " + TBL_GEN_TL_MACHINEMST;
			sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName + " = '"
					+ (String) dataArray[tableFldConstants.keyid.ordinal()] + "'";

		}
		return sql;
	}

	public static String getEquipmentMstSql() {

		String sql = " SELECT * from " + TBL_GEN_TL_MACHINEMST + " where MCHM_KEYID= ? ";
		CommonMessage.debugMsg("Sql Fetch:" + sql);
		return sql;

	}

	public static String getOperatorData(String factId) {

		StringBuffer sql = new StringBuffer();

		sql.append("select '',EMPM_KEYID,EMPM_CODE,EMPM_NAME from " + TBL_GEN_TL_EMPLOYEEMST + " WHERE 1 = 1 "); // + "
																													// where
																													// empm_designationid='DSG003'";

		if (factId != null)
			sql.append(" AND EMPM_FACTORYID = '" + factId + "'");

		return sql.toString();
	}

	/*
	 * public static String OperatorSkillData() {
	 * 
	 * return
	 * "select '',rownum, MSKM_SKILLDESCRIPTION from (SELECT  DISTINCT  MSKM_SKILLDESCRIPTION from "
	 * + TBL_GEN_TL_MACHINESKILLMST + " WHERE MSKM_SKILLFORDEPARTMENT = 'O')"; }
	 */
	
	public static String OperatorSkillData() {
	    return "SELECT '', ROW_NUMBER() OVER (ORDER BY MSKM_SKILLDESCRIPTION) as rownum, MSKM_SKILLDESCRIPTION " +
	           "FROM (SELECT DISTINCT MSKM_SKILLDESCRIPTION FROM " + 
	           TBL_GEN_TL_MACHINESKILLMST + 
	           " WHERE MSKM_SKILLFORDEPARTMENT = 'O') subquery";
	}

	public static String MachineData() {
		/*
		 * return
		 * " SELECT DISTINCT DECODE (MTMM_KEYID, MCMT_MAINTENANCETEAMID, 1,0) AS TICK, MTMM_CODE,"
		 * + " MTMM_NAME,MTMM_KEYID  From " + TBL_GEN_TL_MAINTENANCETEAMMST + " ," +
		 * TBL_GEN_TL_MCHMAINTTEAMLINK +
		 * " Where MTMM_KEYID = MCMT_MAINTENANCETEAMID (+) ORDER BY TICK DESC, MTMM_CODE"
		 * ;
		 *///mano
		return " SELECT DISTINCT CASE WHEN mtmm_keyid = mcmt_maintenanceteamid THEN 1 ELSE 0 END AS tick, mtmm_code,"
        + " mtmm_name,mtmm_keyid  From " + TBL_GEN_TL_MAINTENANCETEAMMST + " LEFT JOIN " + TBL_GEN_TL_MCHMAINTTEAMLINK
        + " ON mtmm_keyid = mcmt_maintenanceteamid ORDER BY tick DESC, mtmm_code";
	}

	/*
	 * public static String equipmentData() { String sql=
	 * "  SELECT MCPM_KEYID ,MCPM_PARAMETER,'',MPLK_DESCRIPTION ,DECODE(MCPM_ENTRYOPTION,'T', 'ListText', "
	 * +
	 * " 'N', 'ListNumber' ,'S',  'ListSelection' ,'D', 'ListDate' , 'W', 'ListDropdown')  ,"
	 * + " REPLACE(MCPM_LISTTEXT , '{}','')   , REPLACE(MCPM_LISTVALUE,'X',''), "+
	 * "DECODE(MCPM_ISMANDATORY, 'Y','YES','N','NO'), TO_CHAR(MPLK_DATE,'DD-MON-YYYY')"
	 * +
	 * "FROM GEN_TL_MCHPARAMETERCONFIG  , GEN_TL_MCHPARAMETERLINK WHERE MCPM_ACTIVE = 'Y' AND MPLK_ACTIVE (+) = 'Y'"
	 * + "AND MPLK_PARAMETERID (+) = MCPM_KEYID AND MPLK_KEYID (+) = ? "+
	 * "ORDER BY MCPM_SLNO"; CommonMessage.debugMsg(sql); return sql; }
	 * 
	 */
	public static String equipmentData() {
		
		/*
		 * String sql =
		 * "SELECT SCCV_KEYID,SCCV_OBJECTKEY,SCCV_EQUIPMENTNUM,SCCV_CLASSTYPE,SCCV_CLASSNUM,SCCV_INTCLASSNUM,SCCV_INTCHARNUM,SCCV_CHARNAME,SCCV_CHARVALUE FROM SAP_CLASSCHAR_VAL WHERE SCCV_EQUIPMENTNUM=?"
		 * + "ORDER BY SCCV_INTCHARNUM";
		 * 
		 * return sql;
		 */
		 
		// mano
		
		  String sql =
		  "SELECT sccv_keyid, sccv_objectkey, sccv_equipmentnum, sccv_classtype, " +
		  "sccv_classnum, sccv_intclassnum, sccv_intcharnum, sccv_charname, sccv_charvalue "
		  + "FROM sap_classchar_val " + "WHERE sccv_equipmentnum = ? " +
		  "ORDER BY sccv_intcharnum";
		 

	return sql;
	}

	public static String subEquipmentData(String sectId, String eqpId) {
		/*
		 * StringBuffer sb = new StringBuffer(); CommonMessage.debugMsg("EQPID in sql :" +
		 * eqpId); sb.append("SELECT distinct "); if
		 * (com.akranta.tpm.utils.CommonFunctions.isValidKeyId(eqpId))
		 * sb.append("DECODE(MCHM_KEYID,SCML_CHILDMCHID,1,0) AS TICK,");
		 * sb.append("MCHM_KEYID,'',MCHM_MACHINENO,MCHM_MACHINENAME ");
		 * sb.append("FROM GEN_TL_MACHINEMST,GEN_TL_MCHSUBMCHLINK,GEN_TL_CELLMST ");
		 * sb.append("WHERE MCHM_ACTIVE ='Y' AND  MCHM_CELLID=CELL_KEYID AND ");
		 * sb.append("MCHM_KEYID=SCML_CHILDMCHID(+)  AND MCHM_MACHINENO <> '-'  "); if
		 * (com.akranta.tpm.utils.CommonFunctions.isValidKeyId(sectId))
		 * sb.append("AND CELL_SECTIONID= '" + sectId + "'");
		 * 
		 * if (com.akranta.tpm.utils.CommonFunctions.isValidKeyId(eqpId))
		 * sb.append(" AND MCHM_KEYID <> '" + eqpId + "' AND SCML_PARENTMCHID(+)= '" +
		 * eqpId + "'"); sb.append(" ORDER BY MCHM_MACHINENO");
		 * 
		 * return sb.toString();
		 */
		//mano 
		StringBuffer sb = new StringBuffer();
		CommonMessage.debugMsg("EQPID in sql :" + eqpId);
		sb.append("SELECT distinct ");
		if (com.akranta.tpm.utils.CommonFunctions.isValidKeyId(eqpId))
		    sb.append("CASE WHEN mchm_keyid = scml_childmchid THEN 1 ELSE 0 END AS tick,");
		sb.append("mchm_keyid,'',mchm_machineno,mchm_machinename ");
		sb.append("FROM gen_tl_machinemst ");
		sb.append("LEFT JOIN gen_tl_mchsubmchlink ON mchm_keyid = scml_childmchid ");
		sb.append("INNER JOIN gen_tl_cellmst ON mchm_cellid = cell_keyid ");
		sb.append("WHERE mchm_active = 'Y' AND mchm_machineno <> '-' ");
		if (com.akranta.tpm.utils.CommonFunctions.isValidKeyId(sectId))
		    sb.append("AND cell_sectionid = '" + sectId + "' ");

		if (com.akranta.tpm.utils.CommonFunctions.isValidKeyId(eqpId))
		    sb.append("AND mchm_keyid <> '" + eqpId + "' AND scml_parentmchid = '" + eqpId + "' ");
		sb.append("ORDER BY mchm_machineno");

		return sb.toString();
	}

	public static String MaintSkillData() {
		/*
		 * return
		 * "select '',rownum, MSKM_SKILLDESCRIPTION from (SELECT  DISTINCT  MSKM_SKILLDESCRIPTION from "
		 * + TBL_GEN_TL_MACHINESKILLMST + " WHERE MSKM_SKILLFORDEPARTMENT = 'M')";
		 */
		return "select '', row_number() over (order by MSKM_SKILLDESCRIPTION), MSKM_SKILLDESCRIPTION from (SELECT DISTINCT MSKM_SKILLDESCRIPTION from "
        + TBL_GEN_TL_MACHINESKILLMST + " WHERE MSKM_SKILLFORDEPARTMENT = 'M') as subquery";
	}

	public static String getgridData() {

		return " SELECT * from " + TBL_GEN_TL_MCHMAINTTEAMLINK + " where MCMT_MACHINEID= ?";
	}

	public static String RecallOperatorData() {

		/*
		 * return "select EMPM_KEYID,EMPM_NAME, EMPM_CODE from GEN_TL_MCHEMPLINK," +
		 * TBL_GEN_TL_EMPLOYEEMST +
		 * " where MCEM_EMPLOYEEID=EMPM_KEYID(+) AND MCEM_MACHINEID = ?";
		 */
		return "SELECT EMPM_KEYID, EMPM_NAME, EMPM_CODE FROM GEN_TL_MCHEMPLINK " +
	       "LEFT JOIN " + TBL_GEN_TL_EMPLOYEEMST + 
	       " ON MCEM_EMPLOYEEID = EMPM_KEYID " +
	       "WHERE MCEM_MACHINEID = ?";
	}

	/*
	 * public static String RecallOperatorSkillData() {
	 * 
	 * return
	 * "select MSKM_MACHINEID, MSKM_SKILLDESCRIPTION, MSKM_SKILLFORDEPARTMENT from GEN_TL_MACHINESKILLMST  where MSKM_MACHINEID =? AND MSKM_SKILLFORDEPARTMENT ='O'"
	 * ; }
	 */
	//mano
	public static String RecallOperatorSkillData() {
	    return "SELECT mskm_machineid, mskm_skilldescription, mskm_skillfordepartment " +
	           "FROM gen_tl_machineskillmst " +
	           "WHERE mskm_machineid = ? AND mskm_skillfordepartment = 'O'";
	}

	public static String RecallMaintainceData() {
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT DISTINCT MTMM_KEYID, ");
		sql.append(
				" MTMM_CODE, MTMM_NAME,DECODE (MTMM_KEYID, MCMT_MAINTENANCETEAMID, 1,0) AS TICK From GEN_TL_MAINTENANCETEAMMST,GEN_TL_MCHMAINTTEAMLINK ");
		sql.append(" Where MTMM_KEYID = MCMT_MAINTENANCETEAMID  and mcmt_machineid = ? ");
		sql.append(" ORDER BY TICK DESC, MTMM_CODE");

		return sql.toString();
	}

	public static String RecallMaintainceSkillData() {
		return "select MSKM_MACHINEID, MSKM_SKILLDESCRIPTION, MSKM_SKILLFORDEPARTMENT from GEN_TL_MACHINESKILLMST  where MSKM_MACHINEID =? AND MSKM_SKILLFORDEPARTMENT ='M'";
	}

	public static String masterGrid(CommonFilter commonFilter) {
		CommonMessage.debugMsg("INSIDE THE MASTER GRID");

		
		/*
		 * StringBuffer sql = new StringBuffer(); sql.append (" select *  from  (");
		 * sql.append("SELECT rownum AS slno, A.* FROM("); sql.append(
		 * " SELECT DISTINCT " ); sql.
		 * append("  MCHM_KEYID AS KEYID,'' AS CHKVAL, '' AS TICK,MCHM_MACHINENO AS EQUIPMENTNO, MCHM_MACHINENAME AS MACHINENAME,"
		 * ); sql.append(" replace(CELL_CODE,'{}','') as LINE,"); // sql.
		 * append(" replace(CSTM_CODE ,'{}','') AS COST_CENTRE , REPLACE (WKCM_NAME,'{}','') AS WORK_CENTRE, replace( SECT_CODE,'{}','') AS DMT ,"
		 * ); sql.
		 * append(" MCHM_COSTCENTREID AS COST_CENTRE ,MCHM_WORKCENTER AS WORK_CENTRE, replace( SECT_CODE,'{}','') AS DMT ,"
		 * ); sql.append(" replace(MAINGRPNAME ,'{}','') AS MAIN_GROUP,");
		 * sql.append(" replace(SUBGRPNAME ,'{}','') AS SUB_GROUP,");
		 * sql.append(" PRPM_CODE AS PURPOSE, replace(CATM_CODE,'{}','') AS CATEGORY,");
		 * sql.append(" SBCM_CODE AS SUB_CATEGORY, MCHM_MACHINERANK as   Machine_RANK,"
		 * ); sql.
		 * append(" JHSM_CODE AS JHSTEP, MCHM_PHASE AS PHASE, MCHM_WIRES AS WIRES,MCHM_IPVOLT As IPVOLT,"
		 * ); sql.
		 * append(" MCHM_IPVOLTMIN AS IPVOLTMIN, MCHM_IPVOLTMAX AS IPVOLTMAX, MCHM_IPFREQ AS IPFREQ,"
		 * ); sql.append(" MCHM_IPFREQMIN AS IPFREQMIN, MCHM_IPFREQMAX AS IPFREQMAX,");
		 * sql.append(" replace(MCHM_POWERSUPPLY,'{}','')  AS POWERSUPPLY,");
		 * sql.append(" MCHM_SPECIFICATION AS SPECIFICATION, MCHM_REMARKS AS REMARKS,");
		 * sql.
		 * append(" DECODE(TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY')) AS MANUFACTUREDDATE,"
		 * ); sql.
		 * append(" MCHM_MFRSLNO AS MFRSLNO, MCHM_MFRREMARKS AS MFRREMARKS, MCHM_PONO As PONO,"
		 * ); sql.
		 * append(" DECODE(TO_CHAR(MCHM_PODATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_PODATE,'DD-MON-YYYY')) AS PODATE, "
		 * ); sql.
		 * append(" DECODE(TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY')) AS PURCHASEDATE,"
		 * ); sql.append(" NVL(MCHM_PURCHASEPRICE,0) AS PURCHASEPRICE,"); sql.
		 * append(" DECODE(TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY')) AS INSTALLEDDATE,"
		 * ); sql.
		 * append(" decode(MCHM_ISUNDERWARRANTY,'Y','Yes','N','No') AS ISUNDERWARRANTY ,DECODE(TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY')) AS WARRANTYDATE,"
		 * ); sql.
		 * append(" MCHM_SUPPLIERREMARKS AS SUPPLIERREMARKS, decode(MCHM_ISUNDERAMC,'Y','Yes','N','No') AS ISUNDERAMC, DECODE(TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY')) AS AMCDATE,"
		 * ); sql.
		 * append(" DECODE(TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY')) AS AMCRENEWALDATE,"
		 * ); sql.append(" REPLACE(MCHM_AMCREMARKS,'{}') AS AMCREMARKS,");
		 * sql.append(" MCHM_PMFUNCTIONALLOCN AS PMFUNCTIONALLOCN,");
		 * sql.append(" MCHM_PLANNERGROUP AS PLANNERGROUP,");
		 * sql.append(" MCHM_WBSELEMENT AS  WBSELEMENT,");
		 * sql.append(" MCHM_PURCHASE_ORG AS PURCHASEORG,");
		 * sql.append(" MCHM_MAINT_PLANT AS MAINTPLANT,");
		 * sql.append(" MCHM_PLANNING_PLANT AS PLANNINGPLANT"); sql.append(" FROM ");
		 * sql.
		 * append(" GEN_TL_MACHINEMST  ,GEN_MV_FLIDHIERARCHY,  GEN_TL_CELLMST  ,  GEN_TL_SECTIONMST"
		 * ); sql.
		 * append(" ,  GEN_VW_EQPGROUPMST  ,  GEN_TL_PURPOSEMST  ,  GEN_TL_CATEGORYMST"
		 * ); //sql.
		 * append(" ,  GEN_TL_SUBCATEGORYMST  ,  GEN_TL_JHSTEPMST  ,  GEN_TL_COSTCENTREMST,GEN_TL_WORKCENTREMST "
		 * ); sql.append(" ,  GEN_TL_SUBCATEGORYMST  ,  GEN_TL_JHSTEPMST ");
		 * sql.append(" where MCHM_ACTIVE = '"+commonFilter.getActive()
		 * +"' AND FNLN_ORIGINALID = MCHM_KEYID And ");
		 * 
		 * if (commonFilter.getLocation() != null && commonFilter.getLocation().getId()
		 * != null ) {
		 * CommonMessage.debugMsg("inside INSTR:"+commonFilter.getLocation().getId());
		 * if(UIUtils.isValidKeyId(commonFilter.getLocation().getId()))
		 * sql.append(" INSTR( PARENTFLIDS || FLID , '").append( commonFilter.getFlid()
		 * ).append("') > 0 AND " ); } if (UIUtils.isValidKeyId(commonFilter.getFlid() )
		 * ) { sql.append(" INSTR( PARENTFLIDS || FLID , '").append(
		 * commonFilter.getFlid() ).append("') > 0 AND " ) ; }
		 * 
		 * sql.append(" CELL_KEYID =MCHM_CELLID AND SECT_KEYID=CELL_SECTIONID AND");
		 * //sql.append(" MCHM_COSTCENTREID=CSTM_KEYID(+) AND");
		 * //sql.append(" MCHM_WORKCENTER=wkcm_keyid(+) AND ");
		 * sql.append(" MCHM_EQUIPMENTGROUP=EQGM_KEYID(+) AND");
		 * sql.append(" MCHM_PURPOSE=PRPM_KEYID(+) AND");
		 * sql.append(" MCHM_CATEGORY = CATM_KEYID(+) AND");
		 * sql.append(" MCHM_SUBCATEGORY=SBCM_KEYID(+) AND"); sql.
		 * append(" MCHM_JHSTEP=JHSM_KEYID(+) AND CELL_ACTIVE = 'Y' AND MCHM_TYPE = 'MCH' ORDER BY MCHM_KEYID DESC "
		 * ); sql.append(")A where  1 = 1 " ); sql.append(
		 * FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) ); sql.append(
		 * " ) where slno >= "+commonFilter.getFromRow()+" and slno <= "+commonFilter.
		 * getToRow()); return sql.toString();
		 */
		 
		
		//mano
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM (");
		sql.append(" SELECT ROW_NUMBER() OVER (ORDER BY a.keyid DESC) AS slno, a.* FROM (");
		sql.append(" SELECT DISTINCT ");
		sql.append(" m.mchm_keyid AS keyid, '' AS chkval, '' AS tick, m.mchm_machineno AS equipmentno, m.mchm_machinename AS machinename, ");
		sql.append(" REPLACE(c.cell_code, '{}', '') AS line, ");
		sql.append(" m.mchm_costcentreid AS cost_centre, m.mchm_workcenter AS work_centre, REPLACE(s.sect_code, '{}', '') AS dmt, ");
		sql.append(" REPLACE(eg.maingrpname, '{}', '') AS main_group, ");
		sql.append(" REPLACE(eg.subgrpname, '{}', '') AS sub_group, ");
		sql.append(" pm.prpm_code AS purpose, REPLACE(cm.catm_code, '{}', '') AS category, ");
		sql.append(" scm.sbcm_code AS sub_category, m.mchm_machinerank AS machine_rank, ");
		sql.append(" jh.jhsm_code AS jhstep, m.mchm_phase AS phase, m.mchm_wires AS wires, m.mchm_ipvolt AS ipvolt, ");
		sql.append(" m.mchm_ipvoltmin AS ipvoltmin, m.mchm_ipvoltmax AS ipvoltmax, m.mchm_ipfreq AS ipfreq, ");
		sql.append(" m.mchm_ipfreqmin AS ipfreqmin, m.mchm_ipfreqmax AS ipfreqmax, ");
		sql.append(" REPLACE(m.mchm_powersupply, '{}', '') AS powersupply, ");
		sql.append(" m.mchm_specification AS specification, m.mchm_remarks AS remarks, ");
		sql.append(" CASE WHEN TO_CHAR(m.mchm_manufactureddate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_manufactureddate, 'DD-MON-YYYY') END AS manufactureddate, ");
		sql.append(" m.mchm_mfrslno AS mfrslno, m.mchm_mfrremarks AS mfrremarks, m.mchm_pono AS pono, ");
		sql.append(" CASE WHEN TO_CHAR(m.mchm_podate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_podate, 'DD-MON-YYYY') END AS podate, ");
		sql.append(" CASE WHEN TO_CHAR(m.mchm_purchasedate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_purchasedate, 'DD-MON-YYYY') END AS purchasedate, ");
		sql.append(" COALESCE(m.mchm_purchaseprice, 0) AS purchaseprice, ");
		sql.append(" CASE WHEN TO_CHAR(m.mchm_installeddate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_installeddate, 'DD-MON-YYYY') END AS installeddate, ");
		sql.append(" CASE m.mchm_isunderwarranty WHEN 'Y' THEN 'Yes' WHEN 'N' THEN 'No' ELSE '' END AS isunderwarranty, ");
		sql.append(" CASE WHEN TO_CHAR(m.mchm_warrantydate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_warrantydate, 'DD-MON-YYYY') END AS warrantydate, ");
		sql.append(" m.mchm_supplierremarks AS supplierremarks, ");
		sql.append(" CASE m.mchm_isunderamc WHEN 'Y' THEN 'Yes' WHEN 'N' THEN 'No' ELSE '' END AS isunderamc, ");
		sql.append(" CASE WHEN TO_CHAR(m.mchm_amcdate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_amcdate, 'DD-MON-YYYY') END AS amcdate, ");
		sql.append(" CASE WHEN TO_CHAR(m.mchm_amcrenewaldate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_amcrenewaldate, 'DD-MON-YYYY') END AS amcrenewaldate, ");
		sql.append(" REPLACE(m.mchm_amcremarks, '{}', '') AS amcremarks, ");
		sql.append(" '' AS pmfunctionallocn, ");
		sql.append(" '' AS plannergroup, ");
		sql.append(" '' AS wbselement, ");
		sql.append(" '' AS purchaseorg, ");
		sql.append(" '' AS maintplant, ");
		sql.append(" '' AS planningplant, ");
//		sql.append(" m.mchm_pmfunctionallocn AS pmfunctionallocn, ");
//		sql.append(" m.mchm_plannergroup AS plannergroup, ");
//		sql.append(" m.mchm_wbselement AS wbselement, ");
//		sql.append(" m.mchm_purchase_org AS purchaseorg, ");
//		sql.append(" m.mchm_maint_plant AS maintplant, ");
//		sql.append(" m.mchm_planning_plant AS planningplant, ");
		sql.append("  fl.fnln_elementid  AS elementid ");
		sql.append(" FROM gen_tl_machinemst m ");
		sql.append("  JOIN gen_mv_flidhierarchy fh ON fh.fnln_originalid = m.mchm_keyid ");
		sql.append(" INNER JOIN gen_tl_cellmst c ON c.cell_keyid = m.mchm_cellid ");
		sql.append(" INNER JOIN gen_tl_sectionmst s ON s.sect_keyid = c.cell_sectionid ");
		sql.append(" LEFT JOIN gen_vw_eqpgroupmst eg ON m.mchm_equipmentgroup = eg.eqgm_keyid ");
		sql.append(" LEFT JOIN gen_tl_purposemst pm ON m.mchm_purpose = pm.prpm_keyid ");
		sql.append(" LEFT JOIN gen_tl_categorymst cm ON m.mchm_category = cm.catm_keyid ");
		sql.append(" LEFT JOIN gen_tl_subcategorymst scm ON m.mchm_subcategory = scm.sbcm_keyid ");
		sql.append(" LEFT JOIN gen_tl_jhstepmst jh ON m.mchm_jhstep = jh.jhsm_keyid ");
		sql.append(" INNER JOIN GEN_TL_FUNCTIONALLOCN FL ON M.MCHM_KEYID = FL.FNLN_ORIGINALID");
		sql.append(" WHERE m.mchm_active = '").append(commonFilter.getActive()).append("' ");

		if (UIUtils.isValidKeyId(commonFilter.getFlid()))
		{
		    //sql.append(" AND POSITION('").append(commonFilter.getFlid()).append("' IN (fh.parentflids || fh.flid)) > 0 ");
			 sql.append(" AND (fh.fnln_originalid IS NULL OR POSITION('")
		       .append(commonFilter.getFlid())
		       .append("' IN  COALESCE(fh.parentflids || fh.flid,'')) > 0) ");
		}

		sql.append(" AND c.cell_active = 'Y' ");
		sql.append(" AND m.mchm_type = 'MCH' ");
		sql.append(" ) a WHERE 1 = 1 ");
		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
		sql.append(" ) data_query WHERE slno >= ").append(commonFilter.getFromRow()).append(" AND slno <= ").append(commonFilter.getToRow());

		return sql.toString();
	}

	public static String RecallEquipmentParameterData() {
		/*
		 * String sql =
		 * "  SELECT MCPM_KEYID ,MCPM_PARAMETER,MPLK_DESCRIPTION ,'',DECODE(MCPM_ENTRYOPTION,'T', 'ListText', "
		 * +
		 * " 'N', 'ListNumber' ,'S',  'ListSelection' ,'D', 'ListDate' , 'W', 'ListDropdown')  ,"
		 * + " REPLACE(MCPM_LISTTEXT , '{}','')   , REPLACE(MCPM_LISTVALUE,'X',''), " +
		 * " DECODE(MCPM_ISMANDATORY, 'Y','YES','N','NO'), TO_CHAR(MPLK_DATE,'DD-MON-YYYY')"
		 * +
		 * " FROM GEN_TL_MCHPARAMETERCONFIG  , GEN_TL_MCHPARAMETERLINK WHERE MCPM_ACTIVE = 'Y' AND MPLK_ACTIVE (+) = 'Y'"
		 * + " AND MPLK_PARAMETERID (+) = MCPM_KEYID AND MPLK_KEYID (+) = ? " +
		 * " ORDER BY MCPM_SLNO"; return sql;
		 */
		
		String sql = "SELECT MCPM_KEYID, MCPM_PARAMETER, MPLK_DESCRIPTION, '', "
			    + "CASE MCPM_ENTRYOPTION "
			    + "  WHEN 'T' THEN 'ListText' "
			    + "  WHEN 'N' THEN 'ListNumber' "
			    + "  WHEN 'S' THEN 'ListSelection' "
			    + "  WHEN 'D' THEN 'ListDate' "
			    + "  WHEN 'W' THEN 'ListDropdown' "
			    + "END, "
			    + "REPLACE(MCPM_LISTTEXT, '{}', ''), REPLACE(MCPM_LISTVALUE, 'X', ''), "
			    + "CASE MCPM_ISMANDATORY "
			    + "  WHEN 'Y' THEN 'YES' "
			    + "  WHEN 'N' THEN 'NO' "
			    + "END, "
			    + "TO_CHAR(MPLK_DATE, 'DD-MON-YYYY') "
			    + "FROM GEN_TL_MCHPARAMETERCONFIG "
			    + "LEFT JOIN GEN_TL_MCHPARAMETERLINK "
			    + "  ON MPLK_PARAMETERID = MCPM_KEYID "
			    + "  AND MPLK_KEYID = ? "
			    + "  AND MPLK_ACTIVE = 'Y' "
			    + "WHERE MCPM_ACTIVE = 'Y' "
			    + "ORDER BY MCPM_SLNO";
			return sql;
	}

	public static String recallSubEquipmentData() {
		String sql;

		/*
		 * sql = "SELECT DISTINCT DECODE (mchm_keyid, scml_childmchid, 1, 0) AS tick, "+
		 * " mchm_keyid, mchm_machineno, mchm_machinename "+
		 * " FROM gen_tl_machinemst, gen_tl_mchsubmchlink, gen_tl_cellmst "+
		 * " WHERE mchm_active = 'Y' AND mchm_cellid = cell_keyid "+
		 * " AND mchm_keyid = scml_childmchid(+) AND mchm_machineno <> '-' "+
		 * " AND cell_sectionid = 'LIN/01' AND mchm_keyid <> 'MCH/00483' "+
		 * " AND scml_parentmchid(+) = 'MCH/00483' ORDER BY mchm_machineno ";
		 */
		sql = "Select * from gen_tl_mchsubmchlink";
		return sql;
	}

	public static String getDeleteFunLocSql(String delMode, TableFieldType[] fieldTypeArr, Object[] dataArray) {
		String sql = "";

		if (delMode.equals("I")) {
			sql = "UPDATE " + TBL_GEN_TL_FUNCTIONALLOCN;
			sql += " SET " + fieldTypeArr[tableFldConstants.active.ordinal()].fieldName + " = 'N'";
			sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName + " = '"
					+ (String) dataArray[tableFldConstants.keyid.ordinal()] + "'";
		} else {
			sql = "DELETE from " + TBL_GEN_TL_FUNCTIONALLOCN;
			sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName + " = '"
					+ (String) dataArray[tableFldConstants.keyid.ordinal()] + "'";
		}
		return sql;
	}

	public static String getDeleteOperatorSkillSql() {
		return " DELETE FROM " + TBL_GEN_TL_MACHINESKILLMST
				+ " WHERE MSKM_MACHINEID = ? AND MSKM_SKILLDESCRIPTION =? AND MSKM_SKILLFORDEPARTMENT = 'O' ";
	}

	public static String getDeleteOperatorMachineLinkSql() {
		return " DELETE FROM " + TBL_GEN_TL_MCHEMPLINK + " WHERE MCEM_MACHINEID = ? AND MCEM_EMPLOYEEID = ? ";
	}

	public static String getDeleteMaintTeamMachineLinkSql() {
		return " DELETE FROM " + TBL_GEN_TL_MCHMAINTTEAMLINK
				+ "  WHERE MCMT_MACHINEID = ? AND MCMT_MAINTENANCETEAMID = ? ";
	}

	public static String getDeleteMaintTeamSkillSql() {
		return " DELETE FROM " + TBL_GEN_TL_MACHINESKILLMST
				+ " WHERE MSKM_MACHINEID = ? AND MSKM_SKILLDESCRIPTION =? AND MSKM_SKILLFORDEPARTMENT = 'M' ";
	}

	public static String getEquipmentMstSqlCount(CommonFilter commonFilter) {
		return "SELECT COUNT(*) FROM " + TBL_GEN_TL_MACHINEMST + " WHERE MCHM_ACTIVE = '" + commonFilter.getActive()
				+ "'";
	}

	public static String getActive(String keyIds) {

		String sql = "";

		sql = "UPDATE " + TBL_GEN_TL_MACHINEMST + " SET MCHM_ACTIVE = 'Y' WHERE MCHM_KEYID = '" + keyIds + "'";
		// UPDATE GEN_TL_MACHINEMST SET MCHM_ACTIVE='Y' WHERE MCHM_KEYID = '';

		return sql;
	}

	public static String getAll(CommonFilter commonFilter) {

		/*
		 * StringBuffer sql = new StringBuffer(); sql.append(" select *  from  (");
		 * sql.append("SELECT rownum AS slno, A.* FROM(");
		 * sql.append(" SELECT DISTINCT "); sql.append(
		 * "  MCHM_KEYID AS KEYID,'' AS CHKVAL, '' AS TICK,MCHM_MACHINENO AS EQUIPMENTNO, MCHM_MACHINENAME AS MACHINENAME,"
		 * ); sql.append(" replace(CELL_CODE,'{}','') as JH,"); sql.
		 * append(" MCHM_COSTCENTREID AS COST_CENTRE ,  replace( SECT_CODE,'{}','') AS DMT ,"
		 * ); sql.append(" replace(MAINGRPNAME ,'{}','') AS MAIN_GROUP,");
		 * sql.append(" replace(SUBGRPNAME ,'{}','') AS SUB_GROUP,");
		 * sql.append(" PRPM_CODE AS PURPOSE, replace(CATM_CODE,'{}','') AS CATEGORY,");
		 * sql.append(" SBCM_CODE AS SUB_CATEGORY, MCHM_MACHINERANK as   Machine_RANK,"
		 * ); sql.
		 * append(" JHSM_CODE AS JHSTEP, MCHM_PHASE AS PHASE, MCHM_WIRES AS WIRES,MCHM_IPVOLT As IPVOLT,"
		 * ); sql.
		 * append(" MCHM_IPVOLTMIN AS IPVOLTMIN, MCHM_IPVOLTMAX AS IPVOLTMAX, MCHM_IPFREQ AS IPFREQ,"
		 * ); sql.append(" MCHM_IPFREQMIN AS IPFREQMIN, MCHM_IPFREQMAX AS IPFREQMAX,");
		 * sql.append(" replace(MCHM_POWERSUPPLY,'{}','')  AS POWERSUPPLY,");
		 * sql.append(" MCHM_SPECIFICATION AS SPECIFICATION, MCHM_REMARKS AS REMARKS,");
		 * sql.append(
		 * " DECODE(TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY')) AS MANUFACTUREDDATE,"
		 * ); sql.
		 * append(" MCHM_MFRSLNO AS MFRSLNO, MCHM_MFRREMARKS AS MFRREMARKS, MCHM_PONO As PONO,"
		 * ); sql.append(
		 * " DECODE(TO_CHAR(MCHM_PODATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_PODATE,'DD-MON-YYYY')) AS PODATE, "
		 * ); sql.append(
		 * " DECODE(TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY')) AS PURCHASEDATE,"
		 * ); sql.append(" NVL(MCHM_PURCHASEPRICE,0) AS PURCHASEPRICE,"); sql.append(
		 * " DECODE(TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY')) AS INSTALLEDDATE,"
		 * ); sql.append(
		 * " decode(MCHM_ISUNDERWARRANTY,'Y','Yes','N','No') AS ISUNDERWARRANTY ,DECODE(TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY')) AS WARRANTYDATE,"
		 * ); sql.append(
		 * " MCHM_SUPPLIERREMARKS AS SUPPLIERREMARKS, decode(MCHM_ISUNDERAMC,'Y','Yes','N','No') AS ISUNDERAMC, DECODE(TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY')) AS AMCDATE,"
		 * ); sql.append(
		 * " DECODE(TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY')) AS AMCRENEWALDATE,"
		 * ); sql.append(" REPLACE(MCHM_AMCREMARKS,'{}') AS AMCREMARKS FROM"); sql.
		 * append(" GEN_TL_MACHINEMST  , GEN_TL_FUNCTIONALLOCN , GEN_TL_CELLMST  ,  GEN_TL_SECTIONMST"
		 * ); sql.
		 * append(" ,  GEN_VW_EQPGROUPMST  ,  GEN_TL_PURPOSEMST  ,  GEN_TL_CATEGORYMST"
		 * ); // sql.append(" , GEN_TL_SUBCATEGORYMST , GEN_TL_JHSTEPMST , //
		 * GEN_TL_COSTCENTREMST");
		 * sql.append(" ,  GEN_TL_SUBCATEGORYMST  ,  GEN_TL_JHSTEPMST ");
		 * sql.append(" where "); sql.
		 * append(" CELL_KEYID =MCHM_CELLID AND SECT_KEYID=CELL_SECTIONID AND FNLN_ORIGINALID = MCHM_KEYID And "
		 * ); if (commonFilter.getLocation() != null &&
		 * commonFilter.getLocation().getId() != null) {
		 * sql.append("  INSTR(FNLN_ELEMENTID,'" + commonFilter.getLocation().getId() +
		 * "') > 0 AND "); } // sql.append(" MCHM_COSTCENTREID=CSTM_KEYID(+) AND");
		 * sql.append(" MCHM_EQUIPMENTGROUP=EQGM_KEYID(+) AND");
		 * sql.append(" MCHM_PURPOSE=PRPM_KEYID(+) AND");
		 * sql.append(" MCHM_CATEGORY = CATM_KEYID(+) AND");
		 * sql.append(" MCHM_SUBCATEGORY=SBCM_KEYID(+) AND"); sql.
		 * append(" MCHM_JHSTEP=JHSM_KEYID(+) AND CELL_ACTIVE = 'Y' AND MCHM_TYPE = 'MCH' ORDER BY MCHM_KEYID DESC "
		 * ); sql.append(")A where  1 = 1 ");
		 * sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
		 * sql.append(" ) where slno >= " + commonFilter.getFromRow() + " and slno <= "
		 * + commonFilter.getToRow());
		 * 
		 * return sql.toString();
		 */
		// mano 
		StringBuffer sql = new StringBuffer();
	    sql.append(" select * from (");
	    sql.append("SELECT row_number() over() AS slno, A.* FROM(");
	    sql.append(" SELECT DISTINCT ");
	    sql.append(
	        " mchm_keyid AS keyid,'' AS chkval, '' AS tick,mchm_machineno AS equipmentno, mchm_machinename AS machinename,");
	    sql.append(" replace(cell_code,'{}','') as jh,");
	    sql.append(" mchm_costcentreid AS cost_centre , replace( sect_code,'{}','') AS dmt ,");
	    sql.append(" replace(maingrpname ,'{}','') AS main_group,");
	    sql.append(" replace(subgrpname ,'{}','') AS sub_group,");
	    sql.append(" prpm_code AS purpose, replace(catm_code,'{}','') AS category,");
	    sql.append(" sbcm_code AS sub_category, mchm_machinerank as machine_rank,");
	    sql.append(" jhsm_code AS jhstep, mchm_phase AS phase, mchm_wires AS wires,mchm_ipvolt As ipvolt,");
	    sql.append(" mchm_ipvoltmin AS ipvoltmin, mchm_ipvoltmax AS ipvoltmax, mchm_ipfreq AS ipfreq,");
	    sql.append(" mchm_ipfreqmin AS ipfreqmin, mchm_ipfreqmax AS ipfreqmax,");
	    sql.append(" replace(mchm_powersupply,'{}','') AS powersupply,");
	    sql.append(" mchm_specification AS specification, mchm_remarks AS remarks,");
	    sql.append(
	        " CASE WHEN TO_CHAR(mchm_manufactureddate,'DD-MON-YYYY')='01-JAN-1801' THEN '' ELSE TO_CHAR(mchm_manufactureddate,'DD-MON-YYYY') END AS manufactureddate,");
	    sql.append(" mchm_mfrslno AS mfrslno, mchm_mfrremarks AS mfrremarks, mchm_pono As pono,");
	    sql.append(
	        " CASE WHEN TO_CHAR(mchm_podate,'DD-MON-YYYY')='01-JAN-1801' THEN '' ELSE TO_CHAR(mchm_podate,'DD-MON-YYYY') END AS podate, ");
	    sql.append(
	        " CASE WHEN TO_CHAR(mchm_purchasedate,'DD-MON-YYYY')='01-JAN-1801' THEN '' ELSE TO_CHAR(mchm_purchasedate,'DD-MON-YYYY') END AS purchasedate,");
	    sql.append(" COALESCE(mchm_purchaseprice,0) AS purchaseprice,");
	    sql.append(
	        " CASE WHEN TO_CHAR(mchm_installeddate,'DD-MON-YYYY')='01-JAN-1801' THEN '' ELSE TO_CHAR(mchm_installeddate,'DD-MON-YYYY') END AS installeddate,");
	    sql.append(
	        " CASE WHEN mchm_isunderwarranty='Y' THEN 'Yes' WHEN mchm_isunderwarranty='N' THEN 'No' END AS isunderwarranty ,CASE WHEN TO_CHAR(mchm_warrantydate,'DD-MON-YYYY')='01-JAN-1801' THEN '' ELSE TO_CHAR(mchm_warrantydate,'DD-MON-YYYY') END AS warrantydate,");
	    sql.append(
	        " mchm_supplierremarks AS supplierremarks, CASE WHEN mchm_isunderamc='Y' THEN 'Yes' WHEN mchm_isunderamc='N' THEN 'No' END AS isunderamc, CASE WHEN TO_CHAR(mchm_amcdate,'DD-MON-YYYY')='01-JAN-1801' THEN '' ELSE TO_CHAR(mchm_amcdate,'DD-MON-YYYY') END AS amcdate,");
	    sql.append(
	        " CASE WHEN TO_CHAR(mchm_amcrenewaldate,'DD-MON-YYYY')='01-JAN-1801' THEN '' ELSE TO_CHAR(mchm_amcrenewaldate,'DD-MON-YYYY') END AS amcrenewaldate,");
	    sql.append(" REPLACE(mchm_amcremarks,'{}') AS amcremarks FROM");
	    sql.append(" gen_tl_machinemst , gen_tl_functionallocn , gen_tl_cellmst , gen_tl_sectionmst");
	    sql.append(" , gen_vw_eqpgroupmst , gen_tl_purposemst , gen_tl_categorymst");
	    sql.append(" , gen_tl_subcategorymst , gen_tl_jhstepmst ");
	    sql.append(" where ");
	    sql.append(" cell_keyid =mchm_cellid AND sect_keyid=cell_sectionid AND fnln_originalid = mchm_keyid And ");
	    if (commonFilter.getLocation() != null && commonFilter.getLocation().getId() != null) {
	        sql.append(" POSITION('" + commonFilter.getLocation().getId() + "' IN fnln_elementid) > 0 AND ");
	    }
	    sql.append(" mchm_equipmentgroup=eqgm_keyid AND");
	    sql.append(" mchm_purpose=prpm_keyid AND");
	    sql.append(" mchm_category = catm_keyid AND");
	    sql.append(" mchm_subcategory=sbcm_keyid AND");
	    sql.append(" mchm_jhstep=jhsm_keyid AND cell_active = 'Y' AND mchm_type = 'MCH' ORDER BY mchm_keyid DESC ");
	    sql.append(")A where 1 = 1 ");
	    sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
	    sql.append(" ) subquery where slno >= " + commonFilter.getFromRow() + " and slno <= " + commonFilter.getToRow());

	    return sql.toString();
	}

	public static String getFuncLocActive(String data) {
		return "UPDATE GEN_TL_FUNCTIONALLOCN  SET FNLN_ACTIVE = 'Y' where FNLN_ORIGINALID =  '" + data + "'";
	}

	public static String getCountAll(CommonFilter commonFilter) {

		/*
		 * StringBuilder sql = new StringBuilder();
		 * sql.append(" select count(*)  from  (");
		 * 
		 * sql.append(" SELECT rownum AS slno, A.* FROM(");
		 * sql.append(" SELECT DISTINCT " ); sql.
		 * append(" MCHM_KEYID AS KEYID,'' AS CHKVAL, '' AS TICK,MCHM_MACHINENO AS EQUIPMENTNO, MCHM_MACHINENAME AS MACHINENAME,"
		 * ); sql.append(" replace( SECT_CODE,'{}','') AS SECTION ,");
		 * sql.append(" SECT_NAME AS SECTIONNAME ,");
		 * sql.append(" replace(CELL_CODE,'{}','') as LINE,");
		 * sql.append(" CELL_NAME as CELLNAME,");
		 * sql.append(" replace(CSTM_CODE ,'{}','') AS COST_CENTRE ,");
		 * sql.append(" replace(MAINGRPNAME ,'{}','') AS MAIN_GROUP,");
		 * sql.append(" replace(SUBGRPNAME ,'{}','') AS SUB_GROUP,");
		 * sql.append(" PRPM_CODE AS PURPOSE, replace(CATM_CODE,'{}','') AS CATEGORY,");
		 * sql.append(" SBCM_CODE AS SUB_CATEGORY, MCHM_MACHINERANK as   Machine_RANK,"
		 * ); sql.
		 * append(" JHSM_CODE AS JHSTEP, MCHM_PHASE AS PHASE, MCHM_WIRES AS WIRES,MCHM_IPVOLT As IPVOLT,"
		 * ); sql.
		 * append(" MCHM_IPVOLTMIN AS IPVOLTMIN, MCHM_IPVOLTMAX AS IPVOLTMAX, MCHM_IPFREQ AS IPFREQ,"
		 * ); sql.append(" MCHM_IPFREQMIN AS IPFREQMIN, MCHM_IPFREQMAX AS IPFREQMAX,");
		 * sql.append(" replace(MCHM_POWERSUPPLY,'{}','')  AS POWERSUPPLY,");
		 * sql.append(" MCHM_SPECIFICATION AS SPECIFICATION, MCHM_REMARKS AS REMARKS,");
		 * sql.
		 * append(" DECODE(TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY')) AS MANUFACTUREDDATE,"
		 * ); sql.
		 * append(" MCHM_MFRSLNO AS MFRSLNO, MCHM_MFRREMARKS AS MFRREMARKS, MCHM_PONO As PONO,"
		 * ); sql.
		 * append(" DECODE(TO_CHAR(MCHM_PODATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_PODATE,'DD-MON-YYYY')) AS PODATE, "
		 * ); sql.
		 * append(" DECODE(TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY')) AS PURCHASEDATE,"
		 * ); sql.append(" NVL(MCHM_PURCHASEPRICE,0) AS PURCHASEPRICE,"); sql.
		 * append(" DECODE(TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY')) AS INSTALLEDDATE,"
		 * ); sql.
		 * append(" decode(MCHM_ISUNDERWARRANTY,'Y','Yes','N','No') AS ISUNDERWARRANTY ,DECODE(TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY')) AS WARRANTYDATE,"
		 * ); sql.
		 * append(" MCHM_SUPPLIERREMARKS AS SUPPLIERREMARKS, decode(MCHM_ISUNDERAMC,'Y','Yes','N','No') AS ISUNDERAMC, DECODE(TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY')) AS AMCDATE,"
		 * ); sql.
		 * append(" DECODE(TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY')) AS AMCRENEWALDATE,"
		 * ); sql.append(" REPLACE(MCHM_AMCREMARKS,'{}') AS AMCREMARKS FROM"); sql.
		 * append(" GEN_TL_MACHINEMST  ,GEN_MV_FLIDHIERARCHY,  GEN_TL_CELLMST  ,  GEN_TL_SECTIONMST"
		 * ); sql.
		 * append(" ,  GEN_VW_EQPGROUPMST  ,  GEN_TL_PURPOSEMST  ,  GEN_TL_CATEGORYMST"
		 * ); sql.
		 * append(" ,  GEN_TL_SUBCATEGORYMST  ,  GEN_TL_JHSTEPMST  ,  GEN_TL_COSTCENTREMST"
		 * ); sql.append(" where MCHM_ACTIVE = '").append(
		 * commonFilter.getActive()).append("' AND FNLN_ORIGINALID = MCHM_KEYID And ");
		 * 
		 * if (UIUtils.isValidKeyId(commonFilter.getFlid() ) ) {
		 * sql.append(" INSTR( PARENTFLIDS || FLID , '").append( commonFilter.getFlid()
		 * ).append("') > 0 AND " ) ; }
		 * 
		 * sql.append(" CELL_KEYID =MCHM_CELLID AND SECT_KEYID=CELL_SECTIONID AND");
		 * sql.append(" MCHM_COSTCENTREID=CSTM_KEYID(+) AND");
		 * sql.append(" MCHM_EQUIPMENTGROUP=EQGM_KEYID(+) AND");
		 * sql.append(" MCHM_PURPOSE=PRPM_KEYID(+) AND");
		 * sql.append(" MCHM_CATEGORY = CATM_KEYID(+) AND");
		 * sql.append(" MCHM_SUBCATEGORY=SBCM_KEYID(+) AND"); sql.
		 * append(" MCHM_JHSTEP=JHSM_KEYID(+) AND CELL_ACTIVE = 'Y' AND MCHM_TYPE = 'MCH' ORDER BY MCHM_KEYID DESC "
		 * ); sql.append(")A where  1 = 1 " );
		 * 
		 * sql.append( FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		 * sql.append(" ) "); // sql.append(
		 * " ) where slno >= "+commonFilter.getFromRow()+" and slno <= "+commonFilter.
		 * getToRow()); return sql.toString();
		 */
		// mano
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(*) FROM (");

		sql.append(" SELECT ROW_NUMBER() OVER (ORDER BY a.keyid DESC) AS slno, a.* FROM (");
		sql.append(" SELECT DISTINCT ");
		sql.append(
				" m.mchm_keyid AS keyid, '' AS chkval, '' AS tick, m.mchm_machineno AS equipmentno, m.mchm_machinename AS machinename, ");
		sql.append(" REPLACE(s.sect_code, '{}', '') AS section, ");
		sql.append(" s.sect_name AS sectionname, ");
		sql.append(" REPLACE(c.cell_code, '{}', '') AS line, ");
		sql.append(" c.cell_name AS cellname, ");
		sql.append(" REPLACE(cc.cstm_code, '{}', '') AS cost_centre, ");
		sql.append(" REPLACE(eg.maingrpname, '{}', '') AS main_group, ");
		sql.append(" REPLACE(eg.subgrpname, '{}', '') AS sub_group, ");
		sql.append(" pm.prpm_code AS purpose, REPLACE(cm.catm_code, '{}', '') AS category, ");
		sql.append(" scm.sbcm_code AS sub_category, m.mchm_machinerank AS machine_rank, ");
		sql.append(" jh.jhsm_code AS jhstep, m.mchm_phase AS phase, m.mchm_wires AS wires, m.mchm_ipvolt AS ipvolt, ");
		sql.append(" m.mchm_ipvoltmin AS ipvoltmin, m.mchm_ipvoltmax AS ipvoltmax, m.mchm_ipfreq AS ipfreq, ");
		sql.append(" m.mchm_ipfreqmin AS ipfreqmin, m.mchm_ipfreqmax AS ipfreqmax, ");
		sql.append(" REPLACE(m.mchm_powersupply, '{}', '') AS powersupply, ");
		sql.append(" m.mchm_specification AS specification, m.mchm_remarks AS remarks, ");
		sql.append(
				" CASE WHEN TO_CHAR(m.mchm_manufactureddate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_manufactureddate, 'DD-MON-YYYY') END AS manufactureddate, ");
		sql.append(" m.mchm_mfrslno AS mfrslno, m.mchm_mfrremarks AS mfrremarks, m.mchm_pono AS pono, ");
		sql.append(
				" CASE WHEN TO_CHAR(m.mchm_podate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_podate, 'DD-MON-YYYY') END AS podate, ");
		sql.append(
				" CASE WHEN TO_CHAR(m.mchm_purchasedate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_purchasedate, 'DD-MON-YYYY') END AS purchasedate, ");
		sql.append(" COALESCE(m.mchm_purchaseprice, 0) AS purchaseprice, ");
		sql.append(
				" CASE WHEN TO_CHAR(m.mchm_installeddate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_installeddate, 'DD-MON-YYYY') END AS installeddate, ");
		sql.append(
				" CASE m.mchm_isunderwarranty WHEN 'Y' THEN 'Yes' WHEN 'N' THEN 'No' ELSE '' END AS isunderwarranty, ");
		sql.append(
				" CASE WHEN TO_CHAR(m.mchm_warrantydate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_warrantydate, 'DD-MON-YYYY') END AS warrantydate, ");
		sql.append(" m.mchm_supplierremarks AS supplierremarks, ");
		sql.append(" CASE m.mchm_isunderamc WHEN 'Y' THEN 'Yes' WHEN 'N' THEN 'No' ELSE '' END AS isunderamc, ");
		sql.append(
				" CASE WHEN TO_CHAR(m.mchm_amcdate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_amcdate, 'DD-MON-YYYY') END AS amcdate, ");
		sql.append(
				" CASE WHEN TO_CHAR(m.mchm_amcrenewaldate, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE TO_CHAR(m.mchm_amcrenewaldate, 'DD-MON-YYYY') END AS amcrenewaldate, ");
		sql.append(" REPLACE(m.mchm_amcremarks, '{}', '') AS amcremarks ");
		sql.append(" FROM gen_tl_machinemst m ");
		sql.append(" JOIN gen_mv_flidhierarchy fh ON fh.fnln_originalid = m.mchm_keyid ");
		sql.append(" INNER JOIN gen_tl_cellmst c ON c.cell_keyid = m.mchm_cellid ");
		sql.append(" INNER JOIN gen_tl_sectionmst s ON s.sect_keyid = c.cell_sectionid ");
		sql.append(" LEFT JOIN gen_tl_costcentermst cc ON m.mchm_costcentreid = cc.cstm_keyid ");
		sql.append(" LEFT JOIN gen_vw_eqpgroupmst eg ON m.mchm_equipmentgroup = eg.eqgm_keyid ");
		sql.append(" LEFT JOIN gen_tl_purposemst pm ON m.mchm_purpose = pm.prpm_keyid ");
		sql.append(" LEFT JOIN gen_tl_categorymst cm ON m.mchm_category = cm.catm_keyid ");
		sql.append(" LEFT JOIN gen_tl_subcategorymst scm ON m.mchm_subcategory = scm.sbcm_keyid ");
		sql.append(" LEFT JOIN gen_tl_jhstepmst jh ON m.mchm_jhstep = jh.jhsm_keyid ");
		sql.append(" WHERE m.mchm_active = '").append(commonFilter.getActive()).append("' ");

		if (UIUtils.isValidKeyId(commonFilter.getFlid())) {
			//sql.append(" AND POSITION('").append(commonFilter.getFlid())
					//.append("' IN (fh.parentflids || fh.flid)) > 0 ");
			
			 sql.append(" AND (fh.fnln_originalid IS NULL OR POSITION('")
		       .append(commonFilter.getFlid())
		       .append("' IN (fh.parentflids || fh.flid)) > 0) ");
		}

		sql.append(" AND c.cell_active = 'Y' ");
		sql.append(" AND m.mchm_type = 'MCH' ");
		sql.append(" ) a WHERE 1 = 1 ");

		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
		sql.append(" ) count_query ");

		return sql.toString();
	}

	/*
	 * public static String getAllCircle(String mchId) { // return "SELECT DISTINCT
	 * '' AS VAL,'' AS SELECTVal, MCLK_CIRCLEID AS // KEYID,CRCM_NAME AS CIRCLE FROM
	 * GEN_TL_MCHCIRCLELINK,GEN_TL_CIRCLEMST WHERE // CRCM_KEYID = MCLK_CIRCLEID" ;
	 * StringBuffer sql = new StringBuffer();
	 * sql.append(" SELECT DISTINCT MAX(DECODE(MCLK_MACHINEID,'" + mchId +
	 * "',1,0)) AS VAL,MAX(DECODE(MCLK_MACHINEID,'" + mchId +
	 * "',1,0)) AS SELECTVal, "); sql.
	 * append(" CRCM_KEYID AS KEYID,CRCM_NAME AS CIRCLE FROM GEN_TL_MCHCIRCLELINK,GEN_TL_CIRCLEMST "
	 * ); sql.append(" WHERE CRCM_KEYID = MCLK_CIRCLEID(+) AND CRCM_ACTIVE='Y'");
	 * sql.append(" GROUP BY CRCM_KEYID,CRCM_NAME");
	 * sql.append(" ORDER BY VAL DESC,CIRCLE"); return sql.toString(); }
	 */
	// mano 
	public static String getAllCircle(String mchId) {
	    // return "SELECT DISTINCT '' AS VAL,'' AS SELECTVal, MCLK_CIRCLEID AS
	    // KEYID,CRCM_NAME AS CIRCLE FROM GEN_TL_MCHCIRCLELINK,GEN_TL_CIRCLEMST WHERE
	    // CRCM_KEYID = MCLK_CIRCLEID" ;
	    StringBuffer sql = new StringBuffer();
	    sql.append(" SELECT DISTINCT MAX(CASE WHEN MCLK_MACHINEID='" + mchId + "' THEN 1 ELSE 0 END) AS VAL,MAX(CASE WHEN MCLK_MACHINEID='"
	            + mchId + "' THEN 1 ELSE 0 END) AS SELECTVal, ");
	    sql.append(" CRCM_KEYID AS KEYID,CRCM_NAME AS CIRCLE FROM GEN_TL_CIRCLEMST ");
	    sql.append(" LEFT JOIN GEN_TL_MCHCIRCLELINK ON CRCM_KEYID = MCLK_CIRCLEID WHERE CRCM_ACTIVE='Y'");
	    sql.append(" GROUP BY CRCM_KEYID,CRCM_NAME");
	    sql.append(" ORDER BY VAL DESC,CIRCLE");
	    return sql.toString();
	}
	public static String getFormCircle(String mchId) {
		
		  StringBuffer sql = new StringBuffer(); sql.append(
		  "SELECT DISTINCT MCLK_CIRCLEID AS KEYID,CRCM_NAME AS CIRCLE from GEN_TL_MCHCIRCLELINK,GEN_TL_CIRCLEMST "
		  ); sql.append(
		  " WHERE 1=1 AND CRCM_KEYID = MCLK_CIRCLEID AND CRCM_ACTIVE='Y' AND MCLK_MACHINEID = '"
		  + mchId + "'"); return sql.toString();
		 
		// mano
		/*
		 * StringBuffer sql = new StringBuffer(); sql.append(
		 * "SELECT DISTINCT mclk_circleid AS keyid, crcm_name AS circle FROM gen_tl_mchcirclelink, gen_tl_circlemst "
		 * ); sql.append(
		 * " WHERE 1=1 AND crcm_keyid = mclk_circleid AND crcm_active='Y' AND mclk_machineid = '"
		 * + mchId + "'"); return sql.toString();
		 */
	}

	public static String equipmentOtherDetails() {
		
		/*
		 * StringBuffer sql = new StringBuffer(); sql.append("SELECT * FROM (");
		 * sql.append(
		 * "SELECT EQPM_NUMBER,EQPM_CATEGORY,EQPM_DESCRIPTION,EQPM_VALID_FROM,EQPM_TECHOBJECT_TYPE,EQPM_AUTH_GROUP,CAST(EQPM_WEIGHT AS VARCHAR2(10)) EQPM_WEIGHT,EQPM_WEIGHT_CHAR,EQPM_SIZE_DIMENSION,"
		 * +
		 * "EQPM_MANUFACTURER,EQPM_MFR_COUNTRY,EQPM_MODEL_NO,CAST(EQPM_CONS_YEAR AS VARCHAR2(10)) EQPM_CONS_YEAR,CAST(EQPM_CONS_MONTH AS VARCHAR2(10)) EQPM_CONS_MONTH,EQPM_MFR_SERIAL_NO,EQPM_INVENTORY_NO,EQPM_MAINT_PLANT,EQPM_LOCATION,"
		 * +
		 * "EQPM_PLANT_SECTION,EQPM_ABC_INDICATOR,EQPM_SORT_FIELD,EQPM_COMPANY_CODE,EQPM_BUSINESS_AREA,"
		 * +
		 * "EQPM_MAIN_ASSET_NO,EQPM_SUB_ASSET_NO,EQPM_COSTCENTER,EQPM_PLANNING_PLANT,EQPM_PLANNER_GROUP,EQPM_MAIN_WORKCENTER,EQPM_FUNCTIONAL_LOCN,EQPM_SUPERIOR_EQP,EQPM_POSITION,EQPM_WARRANTY_ST_DATE,EQPM_WARRANTY_ED_DATE,EQPM_MATERIAL_NO,EQPM_SERIAL_NO,EQPM_ACTIVE"
		 * + " FROM sap_equipment_mst WHERE LTRIM(EQPM_NUMBER,'0')=?)");
		 * sql.append("UNPIVOT(SAPValue FOR SAPName IN(" +
		 * "EQPM_NUMBER AS 'Equipment Number',EQPM_CATEGORY AS 'Category',EQPM_DESCRIPTION AS 'Description',EQPM_VALID_FROM AS 'Valid From',EQPM_TECHOBJECT_TYPE AS 'Tech.Object Type',EQPM_AUTH_GROUP AS 'Auth.Group',EQPM_WEIGHT AS 'Weight',EQPM_WEIGHT_CHAR AS 'Weight Char',EQPM_SIZE_DIMENSION AS 'Size Dimension',"
		 * +
		 * "EQPM_MANUFACTURER AS 'Manufacturer',EQPM_MFR_COUNTRY AS 'MFG.Country',EQPM_MODEL_NO AS 'Model Number',EQPM_CONS_YEAR AS 'Year',EQPM_CONS_MONTH AS 'Month',EQPM_MFR_SERIAL_NO AS 'MFG.SerialNo',EQPM_INVENTORY_NO AS 'Inventory No',EQPM_MAINT_PLANT AS 'Maint.Plant',EQPM_LOCATION AS 'Location',"
		 * +
		 * "EQPM_PLANT_SECTION AS 'Section',EQPM_ABC_INDICATOR AS 'ABC.Indicator',EQPM_SORT_FIELD AS 'Sort Field',EQPM_COMPANY_CODE AS 'Company Code',EQPM_BUSINESS_AREA AS 'Business Area',"
		 * +
		 * "EQPM_MAIN_ASSET_NO AS 'Main.AssetNO',EQPM_SUB_ASSET_NO AS 'SUB.AssetNo',EQPM_COSTCENTER AS 'Cost Center',EQPM_PLANNING_PLANT AS 'Planning Plant',EQPM_PLANNER_GROUP AS 'Planning Group',EQPM_MAIN_WORKCENTER AS 'Main.WorkCenter',EQPM_FUNCTIONAL_LOCN AS 'Functional Location',EQPM_SUPERIOR_EQP AS 'Superior Eqp',EQPM_POSITION AS 'Position',EQPM_WARRANTY_ST_DATE AS 'Warranty Date',EQPM_WARRANTY_ED_DATE AS 'Warranty EndDate',EQPM_MATERIAL_NO AS 'Material NO',EQPM_SERIAL_NO AS 'Serial No',EQPM_ACTIVE AS 'Active')) "
		 * ); return sql.toString();
		 */
		//mano
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (");
		sql.append(
		    "SELECT EQPM_NUMERIC,EQPM_CATEGORY,EQPM_DESCRIPTION,EQPM_VALID_FROM,EQPM_TECHOBJECT_TYPE,EQPM_AUTH_GROUP,CAST(EQPM_WEIGHT AS VARCHAR(10)) EQPM_WEIGHT,EQPM_WEIGHT_CHAR,EQPM_SIZE_DIMENSION,"
		    +
		    "EQPM_MANUFACTURER,EQPM_MFR_COUNTRY,EQPM_MODEL_NO,CAST(EQPM_CONS_YEAR AS VARCHAR(10)) EQPM_CONS_YEAR,CAST(EQPM_CONS_MONTH AS VARCHAR(10)) EQPM_CONS_MONTH,EQPM_MFR_SERIAL_NO,EQPM_INVENTORY_NO,EQPM_MAINT_PLANT,EQPM_LOCATION,"
		    +
		    "EQPM_PLANT_SECTION,EQPM_ABC_INDICATOR,EQPM_SORT_FIELD,EQPM_COMPANY_CODE,EQPM_BUSINESS_AREA,"
		    +
		    "EQPM_MAIN_ASSET_NO,EQPM_SUB_ASSET_NO,EQPM_COSTCENTER,EQPM_PLANNING_PLANT,EQPM_PLANNER_GROUP,EQPM_MAIN_WORKCENTER,EQPM_FUNCTIONAL_LOCN,EQPM_SUPERIOR_EQP,EQPM_POSITION,EQPM_WARRANTY_ST_DATE,EQPM_WARRANTY_ED_DATE,EQPM_MATERIAL_NO,EQPM_SERIAL_NO,EQPM_ACTIVE"
		    + " FROM sap_equipment_mst WHERE LTRIM(EQPM_NUMERIC,'0')=?) AS base_data ");
		sql.append("CROSS JOIN LATERAL (VALUES " +
		    "('Equipment Number', EQPM_NUMERIC),('Category', EQPM_CATEGORY),('Description', EQPM_DESCRIPTION),('Valid From', EQPM_VALID_FROM),('Tech.Object Type', EQPM_TECHOBJECT_TYPE),('Auth.Group', EQPM_AUTH_GROUP),('Weight', EQPM_WEIGHT),('Weight Char', EQPM_WEIGHT_CHAR),('Size Dimension', EQPM_SIZE_DIMENSION),"
		    +
		    "('Manufacturer', EQPM_MANUFACTURER),('MFG.Country', EQPM_MFR_COUNTRY),('Model Number', EQPM_MODEL_NO),('Year', EQPM_CONS_YEAR),('Month', EQPM_CONS_MONTH),('MFG.SerialNo', EQPM_MFR_SERIAL_NO),('Inventory No', EQPM_INVENTORY_NO),('Maint.Plant', EQPM_MAINT_PLANT),('Location', EQPM_LOCATION),"
		    +
		    "('Section', EQPM_PLANT_SECTION),('ABC.Indicator', EQPM_ABC_INDICATOR),('Sort Field', EQPM_SORT_FIELD),('Company Code', EQPM_COMPANY_CODE),('Business Area', EQPM_BUSINESS_AREA),"
		    +
		    "('Main.AssetNO', EQPM_MAIN_ASSET_NO),('SUB.AssetNo', EQPM_SUB_ASSET_NO),('Cost Center', EQPM_COSTCENTER),('Planning Plant', EQPM_PLANNING_PLANT),('Planning Group', EQPM_PLANNER_GROUP),('Main.WorkCenter', EQPM_MAIN_WORKCENTER),('Functional Location', EQPM_FUNCTIONAL_LOCN),('Superior Eqp', EQPM_SUPERIOR_EQP),('Position', EQPM_POSITION),('Warranty Date', EQPM_WARRANTY_ST_DATE),('Warranty EndDate', EQPM_WARRANTY_ED_DATE),('Material NO', EQPM_MATERIAL_NO),('Serial No', EQPM_SERIAL_NO),('Active', EQPM_ACTIVE)"
		    + ") AS unpivot_data(SAPName, SAPValue) "
		);
		return sql.toString();
	}

}
