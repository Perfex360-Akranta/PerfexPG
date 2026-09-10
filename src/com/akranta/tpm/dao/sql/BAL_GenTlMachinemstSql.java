package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.model.CommonFilter;

public class BAL_GenTlMachinemstSql {

	public static final String TBL_GEN_TL_MACHINEMST = "GEN_TL_MACHINEMST";  
	private static final String TBL_GEN_TL_EMPLOYEEMST = "GEN_TL_EMPLOYEEMST";

	private static final String TBL_GEN_TL_MACHINESKILLMST = "GEN_TL_MACHINESKILLMST";
	private static final String TBL_GEN_TL_MAINTENANCETEAMMST = "GEN_TL_MAINTENANCETEAMMST";
	private static final String TBL_GEN_TL_MCHMAINTTEAMLINK = "GEN_TL_MCHMAINTTEAMLINK";

	private static final String TBL_GEN_TL_MCHEMPLINK = "GEN_TL_MCHEMPLINK";

	private static final String TBL_GEN_TL_MCHPARAMETERLINK = "GEN_TL_MCHPARAMETERLINK ";

	private static final String TBL_GEN_TL_MCHSUBMCHLINK = "GEN_TL_MCHSUBMCHLINK";

	private static final String TBL_GEN_TL_FUNCTIONALLOCN = null;
	

	TableFieldType [] mchmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, machineno, machinename, cellid, subcellid, equipmentgroup
		, controltype, purpose, category, subcategory, machinerank, jhstep
		, jhstepdate, phase, wires, ipvolt, ipvoltmin, ipvoltmax, ipfreq
		, ipfreqmin, ipfreqmax, powersupply, connectedload, dbno, sbno
		, specification, remarks, manufacturerid, manufactureddate, make
		, model, mfrslno, mfrremarks, supplierid, pono, podate, purchasedate
		, purchaseprice, installeddate, isunderwarranty, warrantydate
		, supplierremarks, isunderamc, amcdate, amcvendor, amcrenewaldate
		, amcremarks, machineorder, effectivedate, inactivateddate, includeforproduction
		, givesfinaloutput, costcentreid, circleid, iscavityormandrel
		, maxmeterreading, currencyid, workcenter, technicalid, type
		, tradeid, oldmachineno, tempfield4, tempfield5, elementid, flid
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMchmDbFields() {
		return mchmDbFields;
	}

	public BAL_GenTlMachinemstSql()
	{
		mchmDbFields = new TableFieldType[ 70 ];
		for(int i = 0;i < 70; i++)
		{	
			mchmDbFields[ i ] = new TableFieldType();
		}
		mchmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MCHM_KEYID";
		mchmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.machineno.ordinal() ].fieldName = "MCHM_MACHINENO";
		mchmDbFields[ tableFldConstants.machineno.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.machinename.ordinal() ].fieldName = "MCHM_MACHINENAME";
		mchmDbFields[ tableFldConstants.machinename.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "MCHM_CELLID";
		mchmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.subcellid.ordinal() ].fieldName = "MCHM_SUBCELLID";
		mchmDbFields[ tableFldConstants.subcellid.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.equipmentgroup.ordinal() ].fieldName = "MCHM_EQUIPMENTGROUP";
		mchmDbFields[ tableFldConstants.equipmentgroup.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.controltype.ordinal() ].fieldName = "MCHM_CONTROLTYPE";
		mchmDbFields[ tableFldConstants.controltype.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.purpose.ordinal() ].fieldName = "MCHM_PURPOSE";
		mchmDbFields[ tableFldConstants.purpose.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.category.ordinal() ].fieldName = "MCHM_CATEGORY";
		mchmDbFields[ tableFldConstants.category.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.subcategory.ordinal() ].fieldName = "MCHM_SUBCATEGORY";
		mchmDbFields[ tableFldConstants.subcategory.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.machinerank.ordinal() ].fieldName = "MCHM_MACHINERANK";
		mchmDbFields[ tableFldConstants.machinerank.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.jhstep.ordinal() ].fieldName = "MCHM_JHSTEP";
		mchmDbFields[ tableFldConstants.jhstep.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.jhstepdate.ordinal() ].fieldName = "MCHM_JHSTEPDATE";
		mchmDbFields[ tableFldConstants.jhstepdate.ordinal() ].fieldType = 'D';

		mchmDbFields[ tableFldConstants.phase.ordinal() ].fieldName = "MCHM_PHASE";
		mchmDbFields[ tableFldConstants.phase.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.wires.ordinal() ].fieldName = "MCHM_WIRES";
		mchmDbFields[ tableFldConstants.wires.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.ipvolt.ordinal() ].fieldName = "MCHM_IPVOLT";
		mchmDbFields[ tableFldConstants.ipvolt.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.ipvoltmin.ordinal() ].fieldName = "MCHM_IPVOLTMIN";
		mchmDbFields[ tableFldConstants.ipvoltmin.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.ipvoltmax.ordinal() ].fieldName = "MCHM_IPVOLTMAX";
		mchmDbFields[ tableFldConstants.ipvoltmax.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.ipfreq.ordinal() ].fieldName = "MCHM_IPFREQ";
		mchmDbFields[ tableFldConstants.ipfreq.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.ipfreqmin.ordinal() ].fieldName = "MCHM_IPFREQMIN";
		mchmDbFields[ tableFldConstants.ipfreqmin.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.ipfreqmax.ordinal() ].fieldName = "MCHM_IPFREQMAX";
		mchmDbFields[ tableFldConstants.ipfreqmax.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.powersupply.ordinal() ].fieldName = "MCHM_POWERSUPPLY";
		mchmDbFields[ tableFldConstants.powersupply.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.connectedload.ordinal() ].fieldName = "MCHM_CONNECTEDLOAD";
		mchmDbFields[ tableFldConstants.connectedload.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.dbno.ordinal() ].fieldName = "MCHM_DBNO";
		mchmDbFields[ tableFldConstants.dbno.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.sbno.ordinal() ].fieldName = "MCHM_SBNO";
		mchmDbFields[ tableFldConstants.sbno.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.specification.ordinal() ].fieldName = "MCHM_SPECIFICATION";
		mchmDbFields[ tableFldConstants.specification.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MCHM_REMARKS";
		mchmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.manufacturerid.ordinal() ].fieldName = "MCHM_MANUFACTURERID";
		mchmDbFields[ tableFldConstants.manufacturerid.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.manufactureddate.ordinal() ].fieldName = "MCHM_MANUFACTUREDDATE";
		mchmDbFields[ tableFldConstants.manufactureddate.ordinal() ].fieldType = 'D';

		mchmDbFields[ tableFldConstants.make.ordinal() ].fieldName = "MCHM_MAKE";
		mchmDbFields[ tableFldConstants.make.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.model.ordinal() ].fieldName = "MCHM_MODEL";
		mchmDbFields[ tableFldConstants.model.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.mfrslno.ordinal() ].fieldName = "MCHM_MFRSLNO";
		mchmDbFields[ tableFldConstants.mfrslno.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.mfrremarks.ordinal() ].fieldName = "MCHM_MFRREMARKS";
		mchmDbFields[ tableFldConstants.mfrremarks.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.supplierid.ordinal() ].fieldName = "MCHM_SUPPLIERID";
		mchmDbFields[ tableFldConstants.supplierid.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.pono.ordinal() ].fieldName = "MCHM_PONO";
		mchmDbFields[ tableFldConstants.pono.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.podate.ordinal() ].fieldName = "MCHM_PODATE";
		mchmDbFields[ tableFldConstants.podate.ordinal() ].fieldType = 'D';

		mchmDbFields[ tableFldConstants.purchasedate.ordinal() ].fieldName = "MCHM_PURCHASEDATE";
		mchmDbFields[ tableFldConstants.purchasedate.ordinal() ].fieldType = 'D';

		mchmDbFields[ tableFldConstants.purchaseprice.ordinal() ].fieldName = "MCHM_PURCHASEPRICE";
		mchmDbFields[ tableFldConstants.purchaseprice.ordinal() ].fieldType = 'N';

		mchmDbFields[ tableFldConstants.installeddate.ordinal() ].fieldName = "MCHM_INSTALLEDDATE";
		mchmDbFields[ tableFldConstants.installeddate.ordinal() ].fieldType = 'D';

		mchmDbFields[ tableFldConstants.isunderwarranty.ordinal() ].fieldName = "MCHM_ISUNDERWARRANTY";
		mchmDbFields[ tableFldConstants.isunderwarranty.ordinal() ].fieldType = 'C';

		mchmDbFields[ tableFldConstants.warrantydate.ordinal() ].fieldName = "MCHM_WARRANTYDATE";
		mchmDbFields[ tableFldConstants.warrantydate.ordinal() ].fieldType = 'D';

		mchmDbFields[ tableFldConstants.supplierremarks.ordinal() ].fieldName = "MCHM_SUPPLIERREMARKS";
		mchmDbFields[ tableFldConstants.supplierremarks.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.isunderamc.ordinal() ].fieldName = "MCHM_ISUNDERAMC";
		mchmDbFields[ tableFldConstants.isunderamc.ordinal() ].fieldType = 'C';

		mchmDbFields[ tableFldConstants.amcdate.ordinal() ].fieldName = "MCHM_AMCDATE";
		mchmDbFields[ tableFldConstants.amcdate.ordinal() ].fieldType = 'D';

		mchmDbFields[ tableFldConstants.amcvendor.ordinal() ].fieldName = "MCHM_AMCVENDOR";
		mchmDbFields[ tableFldConstants.amcvendor.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.amcrenewaldate.ordinal() ].fieldName = "MCHM_AMCRENEWALDATE";
		mchmDbFields[ tableFldConstants.amcrenewaldate.ordinal() ].fieldType = 'D';

		mchmDbFields[ tableFldConstants.amcremarks.ordinal() ].fieldName = "MCHM_AMCREMARKS";
		mchmDbFields[ tableFldConstants.amcremarks.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.machineorder.ordinal() ].fieldName = "MCHM_MACHINEORDER";
		mchmDbFields[ tableFldConstants.machineorder.ordinal() ].fieldType = 'N';

		mchmDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldName = "MCHM_EFFECTIVEDATE";
		mchmDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldType = 'D';

		mchmDbFields[ tableFldConstants.inactivateddate.ordinal() ].fieldName = "MCHM_INACTIVATEDDATE";
		mchmDbFields[ tableFldConstants.inactivateddate.ordinal() ].fieldType = 'D';

		mchmDbFields[ tableFldConstants.includeforproduction.ordinal() ].fieldName = "MCHM_INCLUDEFORPRODUCTION";
		mchmDbFields[ tableFldConstants.includeforproduction.ordinal() ].fieldType = 'C';

		mchmDbFields[ tableFldConstants.givesfinaloutput.ordinal() ].fieldName = "MCHM_GIVESFINALOUTPUT";
		mchmDbFields[ tableFldConstants.givesfinaloutput.ordinal() ].fieldType = 'C';

		mchmDbFields[ tableFldConstants.costcentreid.ordinal() ].fieldName = "MCHM_COSTCENTREID";
		mchmDbFields[ tableFldConstants.costcentreid.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.circleid.ordinal() ].fieldName = "MCHM_CIRCLEID";
		mchmDbFields[ tableFldConstants.circleid.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.iscavityormandrel.ordinal() ].fieldName = "MCHM_ISCAVITYORMANDREL";
		mchmDbFields[ tableFldConstants.iscavityormandrel.ordinal() ].fieldType = 'C';

		mchmDbFields[ tableFldConstants.maxmeterreading.ordinal() ].fieldName = "MCHM_MAXMETERREADING";
		mchmDbFields[ tableFldConstants.maxmeterreading.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.currencyid.ordinal() ].fieldName = "MCHM_CURRENCYID";
		mchmDbFields[ tableFldConstants.currencyid.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.workcenter.ordinal() ].fieldName = "MCHM_WORKCENTER";
		mchmDbFields[ tableFldConstants.workcenter.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.technicalid.ordinal() ].fieldName = "MCHM_TECHNICALID";
		mchmDbFields[ tableFldConstants.technicalid.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.type.ordinal() ].fieldName = "MCHM_TYPE";
		mchmDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "MCHM_TRADEID";
		mchmDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.oldmachineno.ordinal() ].fieldName = "MCHM_OLDMACHINENO";
		mchmDbFields[ tableFldConstants.oldmachineno.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MCHM_TEMPFIELD4";
		mchmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MCHM_TEMPFIELD5";
		mchmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "MCHM_ELEMENTID";
		mchmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "MCHM_FLID";
		mchmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MCHM_ACTIVE";
		mchmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mchmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MCHM_CREATEDBY";
		mchmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mchmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MCHM_CREATEDON";
		mchmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mchmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MCHM_MODIFIEDON";
		mchmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MACHINEMST, fieldTypeArr, dataArray);
	}

	
	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MACHINEMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		System.out.println(sql);
		return sql;
	}

	/*public static String getDeleteSql(String delMode, TableFieldType [] fieldTypeArr, Object [] dataArray )
	{
		String sql ="";
		
		if (delMode.equals("I")) {  
			sql = "UPDATE " + TBL_GEN_TL_MACHINEMST ;		
			sql += " SET " + fieldTypeArr[tableFldConstants.active.ordinal()].fieldName  +" = 'N'";
			sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
			
			
		}
		else  {			
			
			sql = "DELETE from " + TBL_GEN_TL_MACHINEMST ;		
			sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
			
		}			
		return sql;
	}
	*/
	
	public static String getDeleteSql(String originalId,String flid,String cellId,String delMode )
	{
		StringBuilder sql=new StringBuilder();
		
		if (delMode.equals("I")) {  
		
			sql.append("UPDATE ");
			sql.append(TBL_GEN_TL_MACHINEMST) ;		
			sql.append(" SET MCHM_ACTIVE = 'N', MCHM_FLID = '");
			sql.append(flid);
			sql.append("', MCHM_CELLID='");
			sql.append(cellId);
			sql.append("' where MCHM_KEYID = '");
			sql.append(originalId);
			sql.append("'");
			
			
		}
		else  {			
			sql.append("DELETE from ");
			sql.append(TBL_GEN_TL_MACHINEMST);		
			sql.append(" where MCHM_KEYID = '");
			sql.append(originalId);
			sql.append("'");
			
		}			
		return sql.toString();
	}
	
	public static String getInsertUdSql(String originalId,String cellId,String sectionId,String factId,String elementId,String cretedby,String delMode) 
	{
		//String sql=null;
		StringBuilder sqlb=new StringBuilder();
		
	//	sql=" INSERT INTO GEN_TL_MACHINEUPDATEDELETE VALUES ('"+originalId+"'","'"+cellId+"'","'"+sectionId+"'","'"+factId+"'","'"+elementId+"'","'"+delMode+"'","'"+cretedby+"',SYSDATE)";	
				
		sqlb.append(" INSERT INTO GEN_TL_MACHINEUPDATEDELETE VALUES ('") ;
		sqlb.append(originalId);
		sqlb.append("','");
		sqlb.append(cellId);
		sqlb.append("','");
		sqlb.append(sectionId);
		sqlb.append("','");
		sqlb.append(factId);
		sqlb.append("','");
		sqlb.append(elementId);
		sqlb.append("','");
		sqlb.append(delMode);
		sqlb.append("','");
		sqlb.append(cretedby);
		sqlb.append("',");
		sqlb.append("SYSDATE )");


		
		return sqlb.toString();
	}
	
	
	public static String getEquipmentMstSql() {
		
		String sql=" SELECT * from " + TBL_GEN_TL_MACHINEMST + " where MCHM_KEYID= ? ";
		System.out.println("Sql Fetch:"+sql);
		return sql;
		
	}

	public static String getOperatorData(String factId) {
	
		StringBuilder sql = new StringBuilder();
		
		sql.append( "select '',EMPM_KEYID,EMPM_CODE,EMPM_NAME from "+TBL_GEN_TL_EMPLOYEEMST  + " WHERE 1 = 1 "); //+ " where empm_designationid='DSG003'";
		
		if( factId != null )
			sql.append(" AND EMPM_FACTORYID = '" + factId + "'" );
		
		return sql.toString();
	}

	public static String OperatorSkillData() {
		
		return "select '',rownum, MSKM_SKILLDESCRIPTION from (SELECT  DISTINCT  MSKM_SKILLDESCRIPTION from "+TBL_GEN_TL_MACHINESKILLMST +
		" WHERE MSKM_SKILLFORDEPARTMENT = 'O')";
	}

	public static String MachineData() {
		return " SELECT DISTINCT DECODE (MTMM_KEYID, MCMT_MAINTENANCETEAMID, 1,0) AS TICK, MTMM_CODE," +
				" MTMM_NAME,MTMM_KEYID  From "+ TBL_GEN_TL_MAINTENANCETEAMMST +" ,"+ TBL_GEN_TL_MCHMAINTTEAMLINK +
				" Where MTMM_KEYID = MCMT_MAINTENANCETEAMID (+) ORDER BY TICK DESC, MTMM_CODE";
	}

	public static String equipmentData() {
		String sql= "  SELECT MCPM_KEYID ,MCPM_PARAMETER,'',MPLK_DESCRIPTION ,DECODE(MCPM_ENTRYOPTION,'T', 'ListText', "+
        " 'N', 'ListNumber' ,'S',  'ListSelection' ,'D', 'ListDate' , 'W', 'ListDropdown')  ,"+  
        " REPLACE(MCPM_LISTTEXT , '{}','')   , REPLACE(MCPM_LISTVALUE,'X',''), "+
         "DECODE(MCPM_ISMANDATORY, 'Y','YES','N','NO'), TO_CHAR(MPLK_DATE,'DD-MON-YYYY')"+
         "FROM GEN_TL_MCHPARAMETERCONFIG  , GEN_TL_MCHPARAMETERLINK WHERE MCPM_ACTIVE = 'Y' AND MPLK_ACTIVE (+) = 'Y'"+ 
         "AND MPLK_PARAMETERID (+) = MCPM_KEYID AND MPLK_KEYID (+) = ? "+
         "ORDER BY MCPM_SLNO";
		System.out.println(sql);
		return sql;
	}
	
	public static String subEquipmentData(String sectId,String eqpId){
		StringBuilder sb = new StringBuilder();
		System.out.println("EQPID in sql :"+eqpId);
		sb.append("SELECT distinct ");
		if(com.akranta.tpm.utils.CommonFunctions.isValidKeyId(eqpId))
			sb.append("DECODE(MCHM_KEYID,SCML_CHILDMCHID,1,0) AS TICK,");
		sb.append("MCHM_KEYID,'',MCHM_MACHINENO,MCHM_MACHINENAME ");
		sb.append("FROM GEN_TL_MACHINEMST,GEN_TL_MCHSUBMCHLINK,GEN_TL_CELLMST ");
		sb.append("WHERE MCHM_ACTIVE ='Y' AND  MCHM_CELLID=CELL_KEYID AND ");
		sb.append("MCHM_KEYID=SCML_CHILDMCHID(+)  AND MCHM_MACHINENO <> '-'  ");
		if( com.akranta.tpm.utils.CommonFunctions.isValidKeyId(sectId))
			sb.append("AND CELL_SECTIONID= '"+sectId+"'");
			
		if(com.akranta.tpm.utils.CommonFunctions.isValidKeyId(eqpId))
			sb.append(" AND MCHM_KEYID <> '"+eqpId+"' AND SCML_PARENTMCHID(+)= '"+eqpId+"'");		
		sb.append(" ORDER BY MCHM_MACHINENO");
	
		return sb.toString();
	}

	public static String MaintSkillData() {
		return "select '',rownum, MSKM_SKILLDESCRIPTION from (SELECT  DISTINCT  MSKM_SKILLDESCRIPTION from "+TBL_GEN_TL_MACHINESKILLMST +
		" WHERE MSKM_SKILLFORDEPARTMENT = 'M')" ;
	}

	public static String getgridData() {
		
		return " SELECT * from " + TBL_GEN_TL_MCHMAINTTEAMLINK + " where MCMT_MACHINEID= ?";
	}

	public static String RecallOperatorData() {
	
		return "select EMPM_KEYID,EMPM_NAME, EMPM_CODE from GEN_TL_MCHEMPLINK,"+TBL_GEN_TL_EMPLOYEEMST +" where MCEM_EMPLOYEEID=EMPM_KEYID(+) AND MCEM_MACHINEID = ?";
	}

	public static String RecallOperatorSkillData() {
		
		return "select MSKM_MACHINEID, MSKM_SKILLDESCRIPTION, MSKM_SKILLFORDEPARTMENT from GEN_TL_MACHINESKILLMST  where MSKM_MACHINEID =? AND MSKM_SKILLFORDEPARTMENT ='O'";
	}

	public static String RecallMaintainceData() {
		StringBuilder sql = new StringBuilder();
		
		sql.append( " SELECT DISTINCT MTMM_KEYID, ");
		sql.append( " MTMM_CODE, MTMM_NAME,DECODE (MTMM_KEYID, MCMT_MAINTENANCETEAMID, 1,0) AS TICK From GEN_TL_MAINTENANCETEAMMST,GEN_TL_MCHMAINTTEAMLINK ");
		sql.append( " Where MTMM_KEYID = MCMT_MAINTENANCETEAMID  and mcmt_machineid = ? ");
		sql.append( " ORDER BY TICK DESC, MTMM_CODE") ;
		 
		 return sql.toString();
	}

	public static String RecallMaintainceSkillData() {
		return "select MSKM_MACHINEID, MSKM_SKILLDESCRIPTION, MSKM_SKILLFORDEPARTMENT from GEN_TL_MACHINESKILLMST  where MSKM_MACHINEID =? AND MSKM_SKILLFORDEPARTMENT ='M'";
	}

	public static String masterGrid(CommonFilter commonFilter) {
	
		StringBuilder sql = new StringBuilder();
		 sql.append (" select *  from  (");
		sql.append("SELECT rownum AS slno, A.* FROM(");
		sql.append( " SELECT DISTINCT " );
	   sql.append("  MCHM_KEYID AS KEYID,'' AS CHKVAL, '' AS TICK,FACT_NAME || ' - ' || FACT_CODE Factory, MCHM_MACHINENO AS EQUIPMENTNO, MCHM_MACHINENAME AS MACHINENAME,");
	   sql.append(" replace( SECT_CODE,'{}','') AS SECTION ,");
	   sql.append(" SECT_NAME AS SECTIONNAME ,");
	   sql.append(" replace(CELL_CODE,'{}','') as LINE,");
	   sql.append(" CELL_NAME as CELLNAME,");  
	   sql.append(" replace(CSTM_CODE ,'{}','') AS COST_CENTRE ,  "); 
	   sql.append(" replace(MAINGRPNAME ,'{}','') AS MAIN_GROUP,"); 
	   sql.append(" replace(SUBGRPNAME ,'{}','') AS SUB_GROUP,"); 
	   sql.append(" PRPM_CODE AS PURPOSE, replace(CATM_CODE,'{}','') AS CATEGORY,"); 
	   sql.append(" SBCM_CODE AS SUB_CATEGORY, MCHM_MACHINERANK as   Machine_RANK,"); 
	   sql.append(" JHSM_CODE AS JHSTEP, MCHM_PHASE AS PHASE, MCHM_WIRES AS WIRES,MCHM_IPVOLT As IPVOLT,");  
	   sql.append(" MCHM_IPVOLTMIN AS IPVOLTMIN, MCHM_IPVOLTMAX AS IPVOLTMAX, MCHM_IPFREQ AS IPFREQ,"); 
	   sql.append(" MCHM_IPFREQMIN AS IPFREQMIN, MCHM_IPFREQMAX AS IPFREQMAX,"); 
	   sql.append(" replace(MCHM_POWERSUPPLY,'{}','')  AS POWERSUPPLY,"); 
	   sql.append(" MCHM_SPECIFICATION AS SPECIFICATION, MCHM_REMARKS AS REMARKS,"); 
	   sql.append(" DECODE(TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY')) AS MANUFACTUREDDATE,");          
	   sql.append(" MCHM_MFRSLNO AS MFRSLNO, MCHM_MFRREMARKS AS MFRREMARKS, MCHM_PONO As PONO,"); 
	   sql.append(" DECODE(TO_CHAR(MCHM_PODATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_PODATE,'DD-MON-YYYY')) AS PODATE, ");
	   sql.append(" DECODE(TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY')) AS PURCHASEDATE,"); 
	   sql.append(" NVL(MCHM_PURCHASEPRICE,0) AS PURCHASEPRICE,"); 
	   sql.append(" DECODE(TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY')) AS INSTALLEDDATE,");
	   sql.append(" decode(MCHM_ISUNDERWARRANTY,'Y','Yes','N','No') AS ISUNDERWARRANTY ,DECODE(TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY')) AS WARRANTYDATE,");
	   sql.append(" MCHM_SUPPLIERREMARKS AS SUPPLIERREMARKS, decode(MCHM_ISUNDERAMC,'Y','Yes','N','No') AS ISUNDERAMC, DECODE(TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY')) AS AMCDATE,"); 
	   sql.append(" DECODE(TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY')) AS AMCRENEWALDATE,"); 
	   sql.append(" REPLACE(MCHM_AMCREMARKS,'{}') AS AMCREMARKS FROM"); 
        sql.append(" GEN_TL_MACHINEMST  ,GEN_MV_FLIDHIERARCHY,  GEN_TL_CELLMST  ,  GEN_TL_SECTIONMST"); 
        sql.append(" ,  GEN_VW_EQPGROUPMST  ,  GEN_TL_PURPOSEMST  ,  GEN_TL_CATEGORYMST"); 
        sql.append(" ,  GEN_TL_SUBCATEGORYMST  ,  GEN_TL_JHSTEPMST  ,  GEN_TL_COSTCENTREMST,GEN_TL_FACTORYMST "); 
        sql.append(" where FACT_KEYID = SECT_FACTORYID AND MCHM_ACTIVE = '").append( commonFilter.getActive()).append("' AND FNLN_ORIGINALID = MCHM_KEYID And ");
        
        if (commonFilter.getLocation() != null && UIUtils.isValidKeyId(commonFilter.getLocation().getId()) )
        {
        	//sql.append(" INSTR(FNLN_ELEMENTID,'").append( commonFilter.getLocation().getId() ).append("') > 0 AND ");
        	sql.append(" FACT_LOCATIONID = '").append( commonFilter.getLocation().getId() ).append("' AND " )  ;
        }
        if (UIUtils.isValidKeyId(commonFilter.getFlid() ) )
        {
        	sql.append(" INSTR( PARENTFLIDS || FLID , '").append( commonFilter.getFlid() ).append("') > 0 AND " )  ;
        }
        
        sql.append(" CELL_KEYID =MCHM_CELLID AND SECT_KEYID=CELL_SECTIONID AND"); 
        sql.append(" MCHM_COSTCENTREID=CSTM_KEYID(+) AND"); 
        sql.append(" MCHM_EQUIPMENTGROUP=EQGM_KEYID(+) AND"); 
        sql.append(" MCHM_PURPOSE=PRPM_KEYID(+) AND"); 
        sql.append(" MCHM_CATEGORY = CATM_KEYID(+) AND"); 
        sql.append(" MCHM_SUBCATEGORY=SBCM_KEYID(+) AND"); 
        sql.append(" MCHM_JHSTEP=JHSM_KEYID(+) AND CELL_ACTIVE = 'Y' AND MCHM_TYPE = 'MCH' ");// ORDER BY MCHM_KEYID DESC ");
        if(CommonFunctions.isValidKeyId(commonFilter.getGridSortColumn() ) ){
	        sql.append(" order by  " ) ;
	        sql.append( ( Integer.parseInt(commonFilter.getGridSortColumn()) -1 ) );
	        sql.append( " " + commonFilter.getGridSortOrder());
        }
        else{
        	sql.append(" order by  5 " ) ;
        }
        sql.append(")A where  1 = 1 " );
        sql.append( FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
        sql.append( " ) where slno >= "+commonFilter.getFromRow()+" and slno <= "+commonFilter.getToRow());
        return sql.toString();
                            
	}

	public static String RecallEquipmentParameterData() {
		String sql= "  SELECT MCPM_KEYID ,MCPM_PARAMETER,MPLK_DESCRIPTION ,'',DECODE(MCPM_ENTRYOPTION,'T', 'ListText', "+
         " 'N', 'ListNumber' ,'S',  'ListSelection' ,'D', 'ListDate' , 'W', 'ListDropdown')  ,"+  
         " REPLACE(MCPM_LISTTEXT , '{}','')   , REPLACE(MCPM_LISTVALUE,'X',''), "+
         " DECODE(MCPM_ISMANDATORY, 'Y','YES','N','NO'), TO_CHAR(MPLK_DATE,'DD-MON-YYYY')"+
         " FROM GEN_TL_MCHPARAMETERCONFIG  , GEN_TL_MCHPARAMETERLINK WHERE MCPM_ACTIVE = 'Y' AND MPLK_ACTIVE (+) = 'Y'"+ 
         " AND MPLK_PARAMETERID (+) = MCPM_KEYID AND MPLK_KEYID (+) = ? "+
         " ORDER BY MCPM_SLNO";
		return sql;
	}

	public static String recallSubEquipmentData() {
		String sql;
		
		/*sql = "SELECT DISTINCT DECODE (mchm_keyid, scml_childmchid, 1, 0) AS tick, "+
				" mchm_keyid, mchm_machineno, mchm_machinename "+
				" FROM gen_tl_machinemst, gen_tl_mchsubmchlink, gen_tl_cellmst "+
				" WHERE mchm_active = 'Y' AND mchm_cellid = cell_keyid "+
				" AND mchm_keyid = scml_childmchid(+) AND mchm_machineno <> '-' "+
				" AND cell_sectionid = 'LIN/01' AND mchm_keyid <> 'MCH/00483' "+
				" AND scml_parentmchid(+) = 'MCH/00483' ORDER BY mchm_machineno ";*/
		sql = "Select * from gen_tl_mchsubmchlink";
		return sql;
	}
	
	
	public static String getDeleteFunLocSql(String delMode, TableFieldType [] fieldTypeArr, Object [] dataArray )
	{
		String sql ="";
		
		if (delMode.equals("I")) {  
			sql = "UPDATE " + TBL_GEN_TL_FUNCTIONALLOCN ;		
			sql += " SET " + fieldTypeArr[tableFldConstants.active.ordinal()].fieldName  +" = 'N'";
			sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				  " = '" +  (String)dataArray[tableFldConstants.keyid.ordinal()] + "'";		
		}
		else  {			
			sql = "DELETE from " + TBL_GEN_TL_FUNCTIONALLOCN ;		
			sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		}			
		return sql;
	}
	
	public static String getDeleteOperatorSkillSql(){
		return " DELETE FROM " + TBL_GEN_TL_MACHINESKILLMST + " WHERE MSKM_MACHINEID = ? AND MSKM_SKILLDESCRIPTION =? AND MSKM_SKILLFORDEPARTMENT = 'O' ";
	}
	public static String getDeleteOperatorMachineLinkSql(){
		return " DELETE FROM " +  TBL_GEN_TL_MCHEMPLINK  + " WHERE MCEM_MACHINEID = ? AND MCEM_EMPLOYEEID = ? ";
	}	
	
	public static String getDeleteMaintTeamMachineLinkSql(){
		return " DELETE FROM " + TBL_GEN_TL_MCHMAINTTEAMLINK + "  WHERE MCMT_MACHINEID = ? AND MCMT_MAINTENANCETEAMID = ? "; 
	}

	public static String getDeleteMaintTeamSkillSql(){
		return " DELETE FROM " + TBL_GEN_TL_MACHINESKILLMST + " WHERE MSKM_MACHINEID = ? AND MSKM_SKILLDESCRIPTION =? AND MSKM_SKILLFORDEPARTMENT = 'M' ";  
	}

	public static String getEquipmentMstSqlCount(CommonFilter commonFilter) {
		return "SELECT COUNT(*) FROM "+TBL_GEN_TL_MACHINEMST +" WHERE MCHM_ACTIVE = '"+commonFilter.getActive()+"'";
	}

	public static String getActive(String keyIds) {
		
		String sql = "";
		
	          sql = "UPDATE "+TBL_GEN_TL_MACHINEMST+" SET MCHM_ACTIVE = 'Y' WHERE MCHM_KEYID = '"+ keyIds+"'";
	          //UPDATE GEN_TL_MACHINEMST SET MCHM_ACTIVE='Y' WHERE MCHM_KEYID = '';
	      
		return sql;
	}

	public static String getAll(CommonFilter commonFilter) {
		
		
		StringBuilder sql = new StringBuilder();
		 sql.append (" select *  from  (");
		sql.append("SELECT rownum AS slno, A.* FROM(");
	 	sql.append( " SELECT DISTINCT " );
       sql.append("  MCHM_KEYID AS KEYID,'' AS CHKVAL, '' AS TICK, FACT_NAME || ' - ' || FACT_CODE Factory, MCHM_MACHINENO AS EQUIPMENTNO, MCHM_MACHINENAME AS MACHINENAME,");
       sql.append(" replace(CELL_CODE,'{}','') as JH,");  
       sql.append(" replace(CSTM_CODE ,'{}','') AS COST_CENTRE ,  replace( SECT_CODE,'{}','') AS DMT ,"); 
       sql.append(" replace(MAINGRPNAME ,'{}','') AS MAIN_GROUP,"); 
       sql.append(" replace(SUBGRPNAME ,'{}','') AS SUB_GROUP,"); 
       sql.append(" PRPM_CODE AS PURPOSE, replace(CATM_CODE,'{}','') AS CATEGORY,"); 
       sql.append(" SBCM_CODE AS SUB_CATEGORY, MCHM_MACHINERANK as   Machine_RANK,"); 
       sql.append(" JHSM_CODE AS JHSTEP, MCHM_PHASE AS PHASE, MCHM_WIRES AS WIRES,MCHM_IPVOLT As IPVOLT,");  
       sql.append(" MCHM_IPVOLTMIN AS IPVOLTMIN, MCHM_IPVOLTMAX AS IPVOLTMAX, MCHM_IPFREQ AS IPFREQ,"); 
       sql.append(" MCHM_IPFREQMIN AS IPFREQMIN, MCHM_IPFREQMAX AS IPFREQMAX,"); 
       sql.append(" replace(MCHM_POWERSUPPLY,'{}','')  AS POWERSUPPLY,"); 
       sql.append(" MCHM_SPECIFICATION AS SPECIFICATION, MCHM_REMARKS AS REMARKS,"); 
       sql.append(" DECODE(TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY')) AS MANUFACTUREDDATE,");          
       sql.append(" MCHM_MFRSLNO AS MFRSLNO, MCHM_MFRREMARKS AS MFRREMARKS, MCHM_PONO As PONO,"); 
       sql.append(" DECODE(TO_CHAR(MCHM_PODATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_PODATE,'DD-MON-YYYY')) AS PODATE, ");
       sql.append(" DECODE(TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY')) AS PURCHASEDATE,"); 
       sql.append(" NVL(MCHM_PURCHASEPRICE,0) AS PURCHASEPRICE,"); 
       sql.append(" DECODE(TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY')) AS INSTALLEDDATE,");
       sql.append(" decode(MCHM_ISUNDERWARRANTY,'Y','Yes','N','No') AS ISUNDERWARRANTY ,DECODE(TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY')) AS WARRANTYDATE,");
       sql.append(" MCHM_SUPPLIERREMARKS AS SUPPLIERREMARKS, decode(MCHM_ISUNDERAMC,'Y','Yes','N','No') AS ISUNDERAMC, DECODE(TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY')) AS AMCDATE,"); 
       sql.append(" DECODE(TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY')) AS AMCRENEWALDATE,"); 
       sql.append(" REPLACE(MCHM_AMCREMARKS,'{}') AS AMCREMARKS FROM"); 
       sql.append(" GEN_TL_MACHINEMST  , GEN_MV_FLIDHIERARCHY, GEN_TL_CELLMST  ,  GEN_TL_SECTIONMST "); 
       sql.append(" ,  GEN_VW_EQPGROUPMST  ,  GEN_TL_PURPOSEMST  ,  GEN_TL_CATEGORYMST"); 
       sql.append(" ,  GEN_TL_SUBCATEGORYMST  ,  GEN_TL_JHSTEPMST  ,  GEN_TL_COSTCENTREMST,GEN_TL_FACTORYMST "); 
       sql.append(" where FACT_KEYID = SECT_FACTORYID AND "); 
       sql.append(" CELL_KEYID =MCHM_CELLID AND SECT_KEYID=CELL_SECTIONID AND FNLN_ORIGINALID = MCHM_KEYID And " );
       if (commonFilter.getLocation() != null && UIUtils.isValidKeyId(commonFilter.getLocation().getId()) )
       {
       	//sql.append(" INSTR(FNLN_ELEMENTID,'").append( commonFilter.getLocation().getId() ).append("') > 0 AND ");
       		sql.append(" FACT_LOCATIONID = '").append( commonFilter.getLocation().getId() ).append("' AND " )  ;
       }
       if (UIUtils.isValidKeyId(commonFilter.getFlid() ) )
       {
       	sql.append(" INSTR( PARENTFLIDS || FLID , '").append( commonFilter.getFlid() ).append("') > 0 AND " )  ;
       }
       sql.append(" MCHM_COSTCENTREID=CSTM_KEYID(+) AND"); 
       sql.append(" MCHM_EQUIPMENTGROUP=EQGM_KEYID(+) AND"); 
       sql.append(" MCHM_PURPOSE=PRPM_KEYID(+) AND"); 
       sql.append(" MCHM_CATEGORY = CATM_KEYID(+) AND"); 
       sql.append(" MCHM_SUBCATEGORY=SBCM_KEYID(+) AND"); 
       sql.append(" MCHM_JHSTEP=JHSM_KEYID(+) AND CELL_ACTIVE = 'Y' AND MCHM_TYPE = 'MCH' ");// ORDER BY MCHM_KEYID DESC ");
       sql.append(" order by  " ) ;
       sql.append( commonFilter.getGridSortColumn());
       sql.append( " " + commonFilter.getGridSortOrder());
       sql.append(")A where  1 = 1 " );
       sql.append( FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
       
       sql.append( " ) where slno >= "+commonFilter.getFromRow()+" and slno <= "+commonFilter.getToRow());
       
       return sql.toString();
       
	}

	public static String getFuncLocActive(String data) {
		return "UPDATE GEN_TL_FUNCTIONALLOCN  SET FNLN_ACTIVE = 'Y' where FNLN_ORIGINALID =  '"+data+"'";
	}
	
	
	public static String getCountAll(CommonFilter commonFilter) {
		
		StringBuilder sql = new StringBuilder();
	   sql.append(" select count(*)  from  (");
	   
	   sql.append(" SELECT rownum AS slno, A.* FROM(");
	   sql.append(" SELECT DISTINCT " );
	   sql.append(" MCHM_KEYID AS KEYID,'' AS CHKVAL, '' AS TICK,FACT_NAME || ' - ' || FACT_CODE Factory, MCHM_MACHINENO AS EQUIPMENTNO, MCHM_MACHINENAME AS MACHINENAME,");
	   sql.append(" replace( SECT_CODE,'{}','') AS SECTION ,");
	   sql.append(" SECT_NAME AS SECTIONNAME ,");
	   sql.append(" replace(CELL_CODE,'{}','') as LINE,");
	   sql.append(" CELL_NAME as CELLNAME,");  
	   sql.append(" replace(CSTM_CODE ,'{}','') AS COST_CENTRE ,"); 
	   sql.append(" replace(MAINGRPNAME ,'{}','') AS MAIN_GROUP,"); 
	   sql.append(" replace(SUBGRPNAME ,'{}','') AS SUB_GROUP,"); 
	   sql.append(" PRPM_CODE AS PURPOSE, replace(CATM_CODE,'{}','') AS CATEGORY,"); 
	   sql.append(" SBCM_CODE AS SUB_CATEGORY, MCHM_MACHINERANK as   Machine_RANK,"); 
	   sql.append(" JHSM_CODE AS JHSTEP, MCHM_PHASE AS PHASE, MCHM_WIRES AS WIRES,MCHM_IPVOLT As IPVOLT,");  
	   sql.append(" MCHM_IPVOLTMIN AS IPVOLTMIN, MCHM_IPVOLTMAX AS IPVOLTMAX, MCHM_IPFREQ AS IPFREQ,"); 
	   sql.append(" MCHM_IPFREQMIN AS IPFREQMIN, MCHM_IPFREQMAX AS IPFREQMAX,"); 
	   sql.append(" replace(MCHM_POWERSUPPLY,'{}','')  AS POWERSUPPLY,"); 
	   sql.append(" MCHM_SPECIFICATION AS SPECIFICATION, MCHM_REMARKS AS REMARKS,"); 
	   sql.append(" DECODE(TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_MANUFACTUREDDATE,'DD-MON-YYYY')) AS MANUFACTUREDDATE,");          
	   sql.append(" MCHM_MFRSLNO AS MFRSLNO, MCHM_MFRREMARKS AS MFRREMARKS, MCHM_PONO As PONO,"); 
	   sql.append(" DECODE(TO_CHAR(MCHM_PODATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_PODATE,'DD-MON-YYYY')) AS PODATE, ");
	   sql.append(" DECODE(TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_PURCHASEDATE,'DD-MON-YYYY')) AS PURCHASEDATE,"); 
	   sql.append(" NVL(MCHM_PURCHASEPRICE,0) AS PURCHASEPRICE,"); 
	   sql.append(" DECODE(TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_INSTALLEDDATE,'DD-MON-YYYY')) AS INSTALLEDDATE,");
	   sql.append(" decode(MCHM_ISUNDERWARRANTY,'Y','Yes','N','No') AS ISUNDERWARRANTY ,DECODE(TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_WARRANTYDATE,'DD-MON-YYYY')) AS WARRANTYDATE,");
	   sql.append(" MCHM_SUPPLIERREMARKS AS SUPPLIERREMARKS, decode(MCHM_ISUNDERAMC,'Y','Yes','N','No') AS ISUNDERAMC, DECODE(TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_AMCDATE,'DD-MON-YYYY')) AS AMCDATE,"); 
	   sql.append(" DECODE(TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY'),'01-JAN-1801','',TO_CHAR(MCHM_AMCRENEWALDATE,'DD-MON-YYYY')) AS AMCRENEWALDATE,"); 
	   sql.append(" REPLACE(MCHM_AMCREMARKS,'{}') AS AMCREMARKS FROM"); 
       sql.append(" GEN_TL_MACHINEMST  ,GEN_MV_FLIDHIERARCHY,  GEN_TL_CELLMST  ,  GEN_TL_SECTIONMST"); 
       sql.append(" ,  GEN_VW_EQPGROUPMST  ,  GEN_TL_PURPOSEMST  ,  GEN_TL_CATEGORYMST"); 
       sql.append(" ,  GEN_TL_SUBCATEGORYMST  ,  GEN_TL_JHSTEPMST  ,  GEN_TL_COSTCENTREMST,GEN_TL_FACTORYMST "); 
       sql.append(" where FACT_KEYID = SECT_FACTORYID AND MCHM_ACTIVE = '").append( commonFilter.getActive()).append("' AND FNLN_ORIGINALID = MCHM_KEYID And ");
        
        if (commonFilter.getLocation() != null && UIUtils.isValidKeyId(commonFilter.getLocation().getId()) )
        {
        	//sql.append(" INSTR(FNLN_ELEMENTID,'").append( commonFilter.getLocation().getId() ).append("') > 0 AND ");
        	sql.append(" FACT_LOCATIONID = '").append( commonFilter.getLocation().getId() ).append("' AND " )  ;
        }
        if (UIUtils.isValidKeyId(commonFilter.getFlid() ) )
        {
        	sql.append(" INSTR( PARENTFLIDS || FLID , '").append( commonFilter.getFlid() ).append("') > 0 AND " )  ;
        }
        
        sql.append(" CELL_KEYID =MCHM_CELLID AND SECT_KEYID=CELL_SECTIONID AND"); 
        sql.append(" MCHM_COSTCENTREID=CSTM_KEYID(+) AND"); 
        sql.append(" MCHM_EQUIPMENTGROUP=EQGM_KEYID(+) AND"); 
        sql.append(" MCHM_PURPOSE=PRPM_KEYID(+) AND"); 
        sql.append(" MCHM_CATEGORY = CATM_KEYID(+) AND"); 
        sql.append(" MCHM_SUBCATEGORY=SBCM_KEYID(+) AND"); 
        sql.append(" MCHM_JHSTEP=JHSM_KEYID(+) AND CELL_ACTIVE = 'Y' AND MCHM_TYPE = 'MCH' ORDER BY MCHM_KEYID DESC ");
        sql.append(")A where  1 = 1 " );

       sql.append( FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
       sql.append(" ) ");
       // sql.append( " ) where slno >= "+commonFilter.getFromRow()+" and slno <= "+commonFilter.getToRow());
        return sql.toString();
                            
	}

	public static String getAllCircle(String mchId) {
		//return "SELECT DISTINCT '' AS VAL,'' AS SELECTVal, MCLK_CIRCLEID AS KEYID,CRCM_NAME AS CIRCLE FROM GEN_TL_MCHCIRCLELINK,GEN_TL_CIRCLEMST WHERE CRCM_KEYID = MCLK_CIRCLEID" ;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT MAX(DECODE(MCLK_MACHINEID,'"+mchId+"',1,0)) AS VAL,MAX(DECODE(MCLK_MACHINEID,'"+mchId+"',1,0)) AS SELECTVal, ");
		sql.append(" CRCM_KEYID AS KEYID,CRCM_NAME AS CIRCLE FROM GEN_TL_MCHCIRCLELINK,GEN_TL_CIRCLEMST "); 
		sql.append(" WHERE CRCM_KEYID = MCLK_CIRCLEID(+) AND CRCM_ACTIVE='Y'");
		sql.append(" GROUP BY CRCM_KEYID,CRCM_NAME");
		sql.append(" ORDER BY VAL DESC,CIRCLE");
		 return sql.toString();
	}

	public static String getFormCircle(String mchId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT DISTINCT MCLK_CIRCLEID AS KEYID,CRCM_NAME AS CIRCLE from GEN_TL_MCHCIRCLELINK,GEN_TL_CIRCLEMST ");
		sql.append(" WHERE 1=1 AND CRCM_KEYID = MCLK_CIRCLEID AND CRCM_ACTIVE='Y' AND MCLK_MACHINEID = '"+mchId+"'");
		return sql.toString();
		
	}

	


}

