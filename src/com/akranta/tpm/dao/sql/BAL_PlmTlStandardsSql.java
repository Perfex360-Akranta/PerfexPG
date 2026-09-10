package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonFunctions;

public class BAL_PlmTlStandardsSql {

	public static final String TBL_BAL_PLM_TL_STANDARDS = "BAL_PLM_TL_STANDARDS";  

	private static final String TBL_PLM_TL_ACTIVITYSUBTYPEMST = "PLM_TL_ACTIVITYSUBTYPEMST";

	private static final String TBL_GEN_TL_SPARESMST = "GEN_TL_SPARESMST ";
	
	private static final String TBL_PLM_TL_SPAREDTL = "GEN_TL_SPARESDTL ";

	private static final String TBL_PLM_TL_TOOLSDTL = "PLM_TL_TOOLSDTL";

	private static final String TBL_GEN_TL_TOOLSMST = "GEN_TL_TOOLSMST";

	private static final String TBL_GEN_TL_DOCUPDATES = "GEN_TL_DOCUPDATES";

	TableFieldType [] pmsdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, date, factoryid, sectionid, cellid, machineid, assemblyid
		, subassemblyid, eqpgroupid, source, supplierid, tradeid, frequency
		, frequencyunit, uomid, howmethod, duration, activitytype, activitysubtype
		, bomid, location, activity, standard, machinecondition, planconfigstatus
		, issparesreq, istoolsreq, refdoctype, refdocno, preparedbyid
		, formatno, effectivedate, wogenflag, phenomenaid, causeid, routenumber
		, includeinshutdownmaint, groupno, resultifnotdone, correctiveaction
		, issftpermitreq, safetyinstruction, inactivateddate, monthweekno
		, relatedTo, mouldid, locationid, flid, elementid,  maxvalue
		, minvalue, target, tempfield8, tempfield9, tempfield10
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPmsdDbFields() {
		return pmsdDbFields;
	}

	public BAL_PlmTlStandardsSql()
	{
		pmsdDbFields = new TableFieldType[ 59 ];
		for(int i = 0;i < 59; i++)
		{	
			pmsdDbFields[ i ] = new TableFieldType();
		}
		pmsdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PMSD_KEYID";
		pmsdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.date.ordinal() ].fieldName = "PMSD_DATE";
		pmsdDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		pmsdDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "PMSD_FACTORYID";
		pmsdDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "PMSD_SECTIONID";
		pmsdDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "PMSD_CELLID";
		pmsdDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "PMSD_MACHINEID";
		pmsdDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "PMSD_ASSEMBLYID";
		pmsdDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.subassemblyid.ordinal() ].fieldName = "PMSD_SUBASSEMBLYID";
		pmsdDbFields[ tableFldConstants.subassemblyid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.eqpgroupid.ordinal() ].fieldName = "PMSD_EQPGROUPID";
		pmsdDbFields[ tableFldConstants.eqpgroupid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.source.ordinal() ].fieldName = "PMSD_SOURCE";
		pmsdDbFields[ tableFldConstants.source.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.supplierid.ordinal() ].fieldName = "PMSD_SUPPLIERID";
		pmsdDbFields[ tableFldConstants.supplierid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "PMSD_TRADEID";
		pmsdDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.frequency.ordinal() ].fieldName = "PMSD_FREQUENCY";
		pmsdDbFields[ tableFldConstants.frequency.ordinal() ].fieldType = 'N';

		pmsdDbFields[ tableFldConstants.frequencyunit.ordinal() ].fieldName = "PMSD_FREQUENCYUNIT";
		pmsdDbFields[ tableFldConstants.frequencyunit.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.uomid.ordinal() ].fieldName = "PMSD_UOMID";
		pmsdDbFields[ tableFldConstants.uomid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.howmethod.ordinal() ].fieldName = "PMSD_HOWMETHOD";
		pmsdDbFields[ tableFldConstants.howmethod.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.duration.ordinal() ].fieldName = "PMSD_DURATION";
		pmsdDbFields[ tableFldConstants.duration.ordinal() ].fieldType = 'N';

		pmsdDbFields[ tableFldConstants.activitytype.ordinal() ].fieldName = "PMSD_ACTIVITYTYPE";
		pmsdDbFields[ tableFldConstants.activitytype.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.activitysubtype.ordinal() ].fieldName = "PMSD_ACTIVITYSUBTYPE";
		pmsdDbFields[ tableFldConstants.activitysubtype.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.bomid.ordinal() ].fieldName = "PMSD_BOMID";
		pmsdDbFields[ tableFldConstants.bomid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.location.ordinal() ].fieldName = "PMSD_LOCATION";
		pmsdDbFields[ tableFldConstants.location.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.activity.ordinal() ].fieldName = "PMSD_ACTIVITY";
		pmsdDbFields[ tableFldConstants.activity.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.standard.ordinal() ].fieldName = "PMSD_STANDARD";
		pmsdDbFields[ tableFldConstants.standard.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.machinecondition.ordinal() ].fieldName = "PMSD_MACHINECONDITION";
		pmsdDbFields[ tableFldConstants.machinecondition.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.planconfigstatus.ordinal() ].fieldName = "PMSD_PLANCONFIGSTATUS";
		pmsdDbFields[ tableFldConstants.planconfigstatus.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.issparesreq.ordinal() ].fieldName = "PMSD_ISSPARESREQ";
		pmsdDbFields[ tableFldConstants.issparesreq.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.istoolsreq.ordinal() ].fieldName = "PMSD_ISTOOLSREQ";
		pmsdDbFields[ tableFldConstants.istoolsreq.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "PMSD_REFDOCTYPE";
		pmsdDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.refdocno.ordinal() ].fieldName = "PMSD_REFDOCNO";
		pmsdDbFields[ tableFldConstants.refdocno.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.preparedbyid.ordinal() ].fieldName = "PMSD_PREPAREDBYID";
		pmsdDbFields[ tableFldConstants.preparedbyid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.formatno.ordinal() ].fieldName = "PMSD_FORMATNO";
		pmsdDbFields[ tableFldConstants.formatno.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldName = "PMSD_EFFECTIVEDATE";
		pmsdDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldType = 'D';

		pmsdDbFields[ tableFldConstants.wogenflag.ordinal() ].fieldName = "PMSD_WOGENFLAG";
		pmsdDbFields[ tableFldConstants.wogenflag.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldName = "PMSD_PHENOMENAID";
		pmsdDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.causeid.ordinal() ].fieldName = "PMSD_CAUSEID";
		pmsdDbFields[ tableFldConstants.causeid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.routenumber.ordinal() ].fieldName = "PMSD_ROUTENUMBER";
		pmsdDbFields[ tableFldConstants.routenumber.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.includeinshutdownmaint.ordinal() ].fieldName = "PMSD_INCLUDEINSHUTDOWNMAINT";
		pmsdDbFields[ tableFldConstants.includeinshutdownmaint.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.groupno.ordinal() ].fieldName = "PMSD_GROUPNO";
		pmsdDbFields[ tableFldConstants.groupno.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.resultifnotdone.ordinal() ].fieldName = "PMSD_RESULTIFNOTDONE";
		pmsdDbFields[ tableFldConstants.resultifnotdone.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldName = "PMSD_CORRECTIVEACTION";
		pmsdDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.issftpermitreq.ordinal() ].fieldName = "PMSD_ISSFTPERMITREQ";
		pmsdDbFields[ tableFldConstants.issftpermitreq.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.safetyinstruction.ordinal() ].fieldName = "PMSD_SAFETYINSTRUCTION";
		pmsdDbFields[ tableFldConstants.safetyinstruction.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.inactivateddate.ordinal() ].fieldName = "PMSD_INACTIVATEDDATE";
		pmsdDbFields[ tableFldConstants.inactivateddate.ordinal() ].fieldType = 'D';

		pmsdDbFields[ tableFldConstants.monthweekno.ordinal() ].fieldName = "PMSD_MONTHWEEKNO";
		pmsdDbFields[ tableFldConstants.monthweekno.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.relatedTo.ordinal() ].fieldName = "PMSD_RELATEDTO";
		pmsdDbFields[ tableFldConstants.relatedTo.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.mouldid.ordinal() ].fieldName = "PMSD_MOULDID";
		pmsdDbFields[ tableFldConstants.mouldid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.locationid.ordinal() ].fieldName = "PMSD_LOCATIONID";
		pmsdDbFields[ tableFldConstants.locationid.ordinal() ].fieldType = 'V';
		
		pmsdDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "PMSD_FLID";
		pmsdDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		pmsdDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "PMSD_ELEMENTID";
		pmsdDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.maxvalue.ordinal() ].fieldName = "PMSD_MAXVALUE";
		pmsdDbFields[ tableFldConstants.maxvalue.ordinal() ].fieldType = 'N';

		pmsdDbFields[ tableFldConstants.minvalue.ordinal() ].fieldName = "PMSD_MINVALUE";
		pmsdDbFields[ tableFldConstants.minvalue.ordinal() ].fieldType = 'N';

		pmsdDbFields[ tableFldConstants.target.ordinal() ].fieldName = "PMSD_TARGET";
		pmsdDbFields[ tableFldConstants.target.ordinal() ].fieldType = 'N';

		pmsdDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "PMSD_TEMPFIELD8";
		pmsdDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "PMSD_TEMPFIELD9";
		pmsdDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "PMSD_TEMPFIELD10";
		pmsdDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PMSD_ACTIVE";
		pmsdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pmsdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PMSD_CREATEDBY";
		pmsdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pmsdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PMSD_CREATEDON";
		pmsdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pmsdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PMSD_MODIFIEDON";
		pmsdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BAL_PLM_TL_STANDARDS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BAL_PLM_TL_STANDARDS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BAL_PLM_TL_STANDARDS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getSubtype() {
		// TODO Auto-generated method stub
		return "  Select  Distinct PASM_KEYID,DECODE(PASM_KEYID,'',1,0) AS TICK,PASM_CODE,PASM_NAME From "+ 
			    TBL_PLM_TL_ACTIVITYSUBTYPEMST +" Where  PASM_ACTIVE in ( 'Y' ) ORDER BY TICK  DESC ";
	}

	public static String getpmstdListSql(String pmstdKeyid) {
		// TODO Auto-generated method stub
		return "select * from " +TBL_BAL_PLM_TL_STANDARDS+" where PMSD_KEYID = ?";
	}

	public static String getsprData(String pmsdkeyid) {
		// TODO Auto-generated method stub
		String sql = "SELECT  SPRM_PARTNO,SPRM_PARTNAME,SPRM_MAKE,SPRM_MODEL,PSPD_QUANTITY FROM "  
			+TBL_PLM_TL_SPAREDTL+","+TBL_GEN_TL_SPARESMST+","+TBL_BAL_PLM_TL_STANDARDS +
			"  WHERE  PSPD_SPAREID=SPRM_KEYID AND pmsd_keyID ='"+pmsdkeyid + "'";
		      CommonFunctions.debugMsg(sql);
		return sql;
	}

	public static String getSparesPickupTbl() {
		// TODO Auto-generated method stub
		String sql = "Select PSPD_SPAREID, SPRM_PARTNO, SPRM_PARTNAME ,SPRM_MAKE,SPRM_MODEL, " +
		" PSPD_QUANTITY, PSPD_KEYID  from " + TableNames.TBL_GEN_TL_SPARESMST +"," + TableNames.TBL_PLM_TL_SPAREDTL +
		 " where SPRM_KEYID = PSPD_SPAREID and PSPD_STANDARDID = ? ";
		CommonFunctions.debugMsg("SQL  :"+sql);
		return sql;
	}

	public static String actSubdatasql(String replActSub) {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg("SQL replActSub :");
		String sql = "SELECT PASM_NAME  FROM " + TBL_PLM_TL_ACTIVITYSUBTYPEMST + " WHERE PASM_KEYID IN ('" +replActSub+"')";
		CommonFunctions.debugMsg("SQL  :"+sql);
		return sql;
	}

	/*
	 * public static String gettooldata(String toolpmsdid) { // TODO Auto-generated
	 * method stub
	 * 
	 * String sql = "select PTLD_TOOLID,TOLM_NAME from "+TBL_PLM_TL_TOOLSDTL+","+
	 * TBL_GEN_TL_TOOLSMST
	 * +" where PTLD_TOOLID = TOLM_KEYID AND ptld_standardid = '"+toolpmsdid+"'";
	 * CommonFunctions.debugMsg("sql tools    :"+sql); return sql;
	 * 
	 * }
	 */
	//mano 
	public static String gettooldata(String toolpmsdid) {

	    String sql = "SELECT PTLD_TOOLID, TOLM_NAME " +
	                 "FROM " + TBL_PLM_TL_TOOLSDTL + " t " +
	                 "JOIN " + TBL_GEN_TL_TOOLSMST + " m " +
	                 "ON t.PTLD_TOOLID = m.TOLM_KEYID " +
	                 "WHERE t.PTLD_STANDARDID = '" + toolpmsdid + "'";

	    CommonFunctions.debugMsg("sql tools: " + sql);

	    return sql;
	}

	public static String getchkplan(String machorasswise, String machineId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String updateDocUpdatesdatasql(String dKeyId, String pmsdKeyId) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+ TBL_GEN_TL_DOCUPDATES +" SET DCUP_DETAILID = '"+pmsdKeyId+"' Where DCUP_KEYID= '"+dKeyId+"'" ;
		CommonFunctions.debugMsg("UPDATE DOCUPDATES   :"+sql);
		return sql;
	}

	public static String getCBMTbl(String pmStandardId) {
		// TODO Auto-generated method stub
		//CMDT_CBMCONDITION  replaced with ZONM_CORRECTIVECONDITION 
		String cbmSql = null;
		//if(UIUtils.isValidKeyId(pmStandardId)){
			cbmSql = "SELECT   ZONM_KEYID , CMDT_INSPECTIONID, REPLACE(REPLACE(ZONM_NAME,'<*',''),'*>',''), CMDT_ZONECOLOR, CMDT_LOWERLIMIT,  CMDT_UPPERLIMIT, CMDT_DESIRABLEREADING,  "+
					"  REPLACE(REPLACE(CMDT_CORRECTIVEACTION,'<*',''),'*>',''), ZONM_CORRECTIVECONDITION,'', REPLACE(REPLACE(REPLACE(CMDT_MEASURINGMETHOD,'<*',''),'*>',''),'{}','') , CMDT_PMSTANDARDID,CMDT_UOMID,CMDT_KEYID "+  
					" FROM "+TableNames.TBL_PLM_TL_CBMSTDCADTL+","+TableNames.TBL_PLM_TL_ZONEMST+" WHERE  CMDT_PMSTANDARDID(+) = ? AND CMDT_ZONEID(+) = ZONM_KEYID  order by ZONM_CODE " ;// DECODE(ZONM_CODE,'R',3,'Y',2,'G',1)";
		/*}
		else{
			cbmSql = "SELECT  ZONM_KEYID, ZONM_COLOR, ZONM_NAME, ZONM_CODE,'','','',  ZONM_CORRECTIVEACTION, ZONM_CORRECTIVECONDITION"+ 
			" FROM "+TableNames.TBL_PLM_TL_ZONEMST+" order by DECODE(ZONM_CODE,'R',3,'Y',2,'G',1)";
		}*/
		return cbmSql;
	}

	public static String getMethodTaskList(String machineId){
		return "Select MTSK_OPERATION,MTSK_CHECKINGTOOL,MTSK_IDEALCONDITION,MTSK_TYPEOFCHECK,MTSK_ACTUALCONDITION from plm_tl_methodtasklist where MTSK_MACHINEID = '"+machineId +"'"; 
	}

}



