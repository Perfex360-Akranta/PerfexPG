package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class KznTlMstSql {

	public static final String TBL_KZN_TL_MST = "KZN_TL_MST";  

	TableFieldType [] kznmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, date, factoryid, tpmpillarid, sectionid, cellid, machineid
		, lossid, resultarea, teammembers, theme, themecategoryid, benchmark
		, target, startdate, enddate, presentproblem, presentimage, wwms_keyid
		, rootcause, idea, countermeasure, afterimage, resultdescription
		, resultimage, benefits, benefitsimage, isprovidingchanging, reversibleirreversible
		, kaizenlink, kaizenlinktype, assemblyid, phenomenaid, causeid
		, materialcost, labourcost, howtosustain, additionaldetails, additionalimage
		, ishdpossible, noofhds, preparedid, prepareddate, approvedid
		, approveddate, isworequired, refdoctype, refdocno, status, woid
		, wofeedbackid, completeddate, completedid, remarks, operations
		, whattosustain, sustainfreq, totalcost, circleid, departmentid
		, costcentreid, istpmkzn, materialno, isworthformp, creationflag
		, relatedto, mouldid,elementid, flid,  utiliseforfuture,resultareasec,ideagroupindividual,kzbnkeyid, benefittype, benefitvalue, costperhour
		, costperequipment, verifyamount, aprovLevel, analysis, isWhywhy
		, fipRequired, fipNumber, kaizenupload, kpiid, activitypillarid,active, createdby, createdon
		, modifiedon,industry4,industrycategory,espNames,csmValue,icoe,pcoe,fip
	}

	public TableFieldType[] getKznmDbFields() {
		return kznmDbFields;
	}

	public KznTlMstSql()
	{
		kznmDbFields = new TableFieldType[ 97 ];
		for(int i = 0;i < 97; i++)
		{	
			kznmDbFields[ i ] = new TableFieldType();
		}
		kznmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KZNM_KEYID";
		kznmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "KZNM_DATE";
		kznmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		kznmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "KZNM_FACTORYID";
		kznmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldName = "KZNM_TPMPILLARID";
		kznmDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "KZNM_SECTIONID";
		kznmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "KZNM_CELLID";
		kznmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "KZNM_MACHINEID";
		kznmDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.lossid.ordinal() ].fieldName = "KZNM_LOSSID";
		kznmDbFields[ tableFldConstants.lossid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.resultarea.ordinal() ].fieldName = "KZNM_RESULTAREA";
		kznmDbFields[ tableFldConstants.resultarea.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.teammembers.ordinal() ].fieldName = "KZNM_TEAMMEMBERS";
		kznmDbFields[ tableFldConstants.teammembers.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.theme.ordinal() ].fieldName = "KZNM_THEME";
		kznmDbFields[ tableFldConstants.theme.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.themecategoryid.ordinal() ].fieldName = "KZNM_THEMECATEGORYID";
		kznmDbFields[ tableFldConstants.themecategoryid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.benchmark.ordinal() ].fieldName = "KZNM_BENCHMARK";
		kznmDbFields[ tableFldConstants.benchmark.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.target.ordinal() ].fieldName = "KZNM_TARGET";
		kznmDbFields[ tableFldConstants.target.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.startdate.ordinal() ].fieldName = "KZNM_STARTDATE";
		kznmDbFields[ tableFldConstants.startdate.ordinal() ].fieldType = 'D';

		kznmDbFields[ tableFldConstants.enddate.ordinal() ].fieldName = "KZNM_ENDDATE";
		kznmDbFields[ tableFldConstants.enddate.ordinal() ].fieldType = 'D';

		kznmDbFields[ tableFldConstants.presentproblem.ordinal() ].fieldName = "KZNM_PRESENTPROBLEM";
		kznmDbFields[ tableFldConstants.presentproblem.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.presentimage.ordinal() ].fieldName = "KZNM_PRESENTIMAGE";
		kznmDbFields[ tableFldConstants.presentimage.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.wwms_keyid.ordinal() ].fieldName = "KZNM_WWMS_KEYID";
		kznmDbFields[ tableFldConstants.wwms_keyid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "KZNM_ROOTCAUSE";
		kznmDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.idea.ordinal() ].fieldName = "KZNM_IDEA";
		kznmDbFields[ tableFldConstants.idea.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "KZNM_COUNTERMEASURE";
		kznmDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.afterimage.ordinal() ].fieldName = "KZNM_AFTERIMAGE";
		kznmDbFields[ tableFldConstants.afterimage.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.resultdescription.ordinal() ].fieldName = "KZNM_RESULTDESCRIPTION";
		kznmDbFields[ tableFldConstants.resultdescription.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.resultimage.ordinal() ].fieldName = "KZNM_RESULTIMAGE";
		kznmDbFields[ tableFldConstants.resultimage.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.benefits.ordinal() ].fieldName = "KZNM_BENEFITS";
		kznmDbFields[ tableFldConstants.benefits.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.benefitsimage.ordinal() ].fieldName = "KZNM_BENEFITSIMAGE";
		kznmDbFields[ tableFldConstants.benefitsimage.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.isprovidingchanging.ordinal() ].fieldName = "KZNM_ISPROVIDINGCHANGING";
		kznmDbFields[ tableFldConstants.isprovidingchanging.ordinal() ].fieldType = 'C';

		kznmDbFields[ tableFldConstants.reversibleirreversible.ordinal() ].fieldName = "KZNM_REVERSIBLEIRREVERSIBLE";
		kznmDbFields[ tableFldConstants.reversibleirreversible.ordinal() ].fieldType = 'C';

		kznmDbFields[ tableFldConstants.kaizenlink.ordinal() ].fieldName = "KZNM_KAIZENLINK";
		kznmDbFields[ tableFldConstants.kaizenlink.ordinal() ].fieldType = 'C';

		kznmDbFields[ tableFldConstants.kaizenlinktype.ordinal() ].fieldName = "KZNM_KAIZENLINKTYPE";
		kznmDbFields[ tableFldConstants.kaizenlinktype.ordinal() ].fieldType = 'C';

		kznmDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "KZNM_ASSEMBLYID";
		kznmDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldName = "KZNM_PHENOMENAID";
		kznmDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.causeid.ordinal() ].fieldName = "KZNM_CAUSEID";
		kznmDbFields[ tableFldConstants.causeid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.materialcost.ordinal() ].fieldName = "KZNM_MATERIALCOST";
		kznmDbFields[ tableFldConstants.materialcost.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.labourcost.ordinal() ].fieldName = "KZNM_LABOURCOST";
		kznmDbFields[ tableFldConstants.labourcost.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.howtosustain.ordinal() ].fieldName = "KZNM_HOWTOSUSTAIN";
		kznmDbFields[ tableFldConstants.howtosustain.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.additionaldetails.ordinal() ].fieldName = "KZNM_ADDITIONALDETAILS";
		kznmDbFields[ tableFldConstants.additionaldetails.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.additionalimage.ordinal() ].fieldName = "KZNM_ADDITIONALIMAGE";
		kznmDbFields[ tableFldConstants.additionalimage.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.ishdpossible.ordinal() ].fieldName = "KZNM_ISHDPOSSIBLE";
		kznmDbFields[ tableFldConstants.ishdpossible.ordinal() ].fieldType = 'C';

		kznmDbFields[ tableFldConstants.noofhds.ordinal() ].fieldName = "KZNM_NOOFHDS";
		kznmDbFields[ tableFldConstants.noofhds.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.preparedid.ordinal() ].fieldName = "KZNM_PREPAREDID";
		kznmDbFields[ tableFldConstants.preparedid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldName = "KZNM_PREPAREDDATE";
		kznmDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldType = 'D';

		kznmDbFields[ tableFldConstants.approvedid.ordinal() ].fieldName = "KZNM_APPROVEDID";
		kznmDbFields[ tableFldConstants.approvedid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.approveddate.ordinal() ].fieldName = "KZNM_APPROVEDDATE";
		kznmDbFields[ tableFldConstants.approveddate.ordinal() ].fieldType = 'D';

		kznmDbFields[ tableFldConstants.isworequired.ordinal() ].fieldName = "KZNM_ISWOREQUIRED";
		kznmDbFields[ tableFldConstants.isworequired.ordinal() ].fieldType = 'C';

		kznmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "KZNM_REFDOCTYPE";
		kznmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.refdocno.ordinal() ].fieldName = "KZNM_REFDOCNO";
		kznmDbFields[ tableFldConstants.refdocno.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.status.ordinal() ].fieldName = "KZNM_STATUS";
		kznmDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		kznmDbFields[ tableFldConstants.woid.ordinal() ].fieldName = "KZNM_WOID";
		kznmDbFields[ tableFldConstants.woid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.wofeedbackid.ordinal() ].fieldName = "KZNM_WOFEEDBACKID";
		kznmDbFields[ tableFldConstants.wofeedbackid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.completeddate.ordinal() ].fieldName = "KZNM_COMPLETEDDATE";
		kznmDbFields[ tableFldConstants.completeddate.ordinal() ].fieldType = 'D';

		kznmDbFields[ tableFldConstants.completedid.ordinal() ].fieldName = "KZNM_COMPLETEDID";
		kznmDbFields[ tableFldConstants.completedid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "KZNM_REMARKS";
		kznmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.operations.ordinal() ].fieldName = "KZNM_OPERATIONS";
		kznmDbFields[ tableFldConstants.operations.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.whattosustain.ordinal() ].fieldName = "KZNM_WHATTOSUSTAIN";
		kznmDbFields[ tableFldConstants.whattosustain.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.sustainfreq.ordinal() ].fieldName = "KZNM_SUSTAINFREQ";
		kznmDbFields[ tableFldConstants.sustainfreq.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.totalcost.ordinal() ].fieldName = "KZNM_TOTALCOST";
		kznmDbFields[ tableFldConstants.totalcost.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.circleid.ordinal() ].fieldName = "KZNM_CIRCLEID";
		kznmDbFields[ tableFldConstants.circleid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.departmentid.ordinal() ].fieldName = "KZNM_DEPARTMENTID";
		kznmDbFields[ tableFldConstants.departmentid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.costcentreid.ordinal() ].fieldName = "KZNM_COSTCENTREID";
		kznmDbFields[ tableFldConstants.costcentreid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.istpmkzn.ordinal() ].fieldName = "KZNM_ISTPMKZN";
		kznmDbFields[ tableFldConstants.istpmkzn.ordinal() ].fieldType = 'C';

		kznmDbFields[ tableFldConstants.materialno.ordinal() ].fieldName = "KZNM_MATERIALNO";
		kznmDbFields[ tableFldConstants.materialno.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.isworthformp.ordinal() ].fieldName = "KZNM_ISWORTHFORMP";
		kznmDbFields[ tableFldConstants.isworthformp.ordinal() ].fieldType = 'C';

		kznmDbFields[ tableFldConstants.creationflag.ordinal() ].fieldName = "KZNM_CREATIONFLAG";
		kznmDbFields[ tableFldConstants.creationflag.ordinal() ].fieldType = 'C';

		kznmDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "KZNM_RELATEDTO";
		kznmDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.mouldid.ordinal() ].fieldName = "KZNM_MOULDID";
		kznmDbFields[ tableFldConstants.mouldid.ordinal() ].fieldType = 'V';
		
		kznmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "KZNM_ELEMENTID";
		kznmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "KZNM_FLID";
		kznmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		kznmDbFields[ tableFldConstants.utiliseforfuture.ordinal() ].fieldName = "KZNM_UTILISEFORFUTURE";
		kznmDbFields[ tableFldConstants.utiliseforfuture.ordinal() ].fieldType = 'C';
		
		kznmDbFields[ tableFldConstants.resultareasec.ordinal() ].fieldName = "KZNM_RESULTAREASEC";
		kznmDbFields[ tableFldConstants.resultareasec.ordinal() ].fieldType = 'V';
		
		kznmDbFields[ tableFldConstants.ideagroupindividual.ordinal() ].fieldName = "KZNM_IDEAGROUPINDIVIDUAL";
		kznmDbFields[ tableFldConstants.ideagroupindividual.ordinal() ].fieldType = 'C';
		
		kznmDbFields[ tableFldConstants.kzbnkeyid.ordinal() ].fieldName = "KZNM_KZBNKEYID";
		kznmDbFields[ tableFldConstants.kzbnkeyid.ordinal() ].fieldType = 'V';
		
		kznmDbFields[ tableFldConstants.benefittype.ordinal() ].fieldName = "KZNM_BENEFITTYPE";
		kznmDbFields[ tableFldConstants.benefittype.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.benefitvalue.ordinal() ].fieldName = "KZNM_BENEFITVALUE";
		kznmDbFields[ tableFldConstants.benefitvalue.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.costperhour.ordinal() ].fieldName = "KZNM_COSTPERHOUR";
		kznmDbFields[ tableFldConstants.costperhour.ordinal() ].fieldType = 'N';

		kznmDbFields[ tableFldConstants.costperequipment.ordinal() ].fieldName = "KZNM_COSTPEREQUIPMENT";
		kznmDbFields[ tableFldConstants.costperequipment.ordinal() ].fieldType = 'N';

		kznmDbFields[ tableFldConstants.verifyamount.ordinal() ].fieldName = "KZNM_VERIFYAMOUNT";
		kznmDbFields[ tableFldConstants.verifyamount.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.aprovLevel.ordinal() ].fieldName = "KZNM_APROV_LEVEL";
		kznmDbFields[ tableFldConstants.aprovLevel.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.analysis.ordinal() ].fieldName = "KZNM_ANALYSIS";
		kznmDbFields[ tableFldConstants.analysis.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.isWhywhy.ordinal() ].fieldName = "KZNM_ISWHYWHY";
		kznmDbFields[ tableFldConstants.isWhywhy.ordinal() ].fieldType = 'C';

		kznmDbFields[ tableFldConstants.fipRequired.ordinal() ].fieldName = "KZNM_FIPREQUIRED";
		kznmDbFields[ tableFldConstants.fipRequired.ordinal() ].fieldType = 'V';
		
		kznmDbFields[ tableFldConstants.fipNumber.ordinal() ].fieldName = "KZNM_FIPNUMBER";
		kznmDbFields[ tableFldConstants.fipNumber.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.kaizenupload.ordinal() ].fieldName = "KZNM_KAIZENUPLOAD";
		kznmDbFields[ tableFldConstants.kaizenupload.ordinal() ].fieldType = 'V';
		
		kznmDbFields[ tableFldConstants.kpiid.ordinal() ].fieldName = "KZNM_KPIID";
		kznmDbFields[ tableFldConstants.kpiid.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.activitypillarid.ordinal() ].fieldName = "KZNM_ACTIVITYPILLARID";
		kznmDbFields[ tableFldConstants.activitypillarid.ordinal() ].fieldType = 'V';

		
		kznmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KZNM_ACTIVE";
		kznmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kznmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KZNM_CREATEDBY";
		kznmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KZNM_CREATEDON";
		kznmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kznmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KZNM_MODIFIEDON";
		kznmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
		kznmDbFields[ tableFldConstants.industry4.ordinal() ].fieldName = "KZNM_INDUSTRY4";
		kznmDbFields[ tableFldConstants.industry4.ordinal() ].fieldType = 'V';
		
		kznmDbFields[ tableFldConstants.industrycategory.ordinal() ].fieldName = "KZNM_INDUSTRYCATEGORY";
		kznmDbFields[ tableFldConstants.industrycategory.ordinal() ].fieldType = 'V';

		kznmDbFields[ tableFldConstants.espNames.ordinal() ].fieldName = "KZNM_SUGGBYESPNAME";
		kznmDbFields[ tableFldConstants.espNames.ordinal() ].fieldType = 'V';
		
		kznmDbFields[ tableFldConstants.csmValue.ordinal() ].fieldName = "KZNM_CSMVALUE";
		kznmDbFields[ tableFldConstants.csmValue.ordinal() ].fieldType = 'V';
		
		kznmDbFields[ tableFldConstants.icoe.ordinal() ].fieldName = "KZNM_ICOE";
		kznmDbFields[ tableFldConstants.icoe.ordinal() ].fieldType = 'V';
		
		kznmDbFields[ tableFldConstants.pcoe.ordinal() ].fieldName = "KZNM_PCOE";
		kznmDbFields[ tableFldConstants.pcoe.ordinal() ].fieldType = 'V';
		
		kznmDbFields[ tableFldConstants.fip.ordinal() ].fieldName = "KZNM_FIP";
		kznmDbFields[ tableFldConstants.fip.ordinal() ].fieldType = 'V';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_MST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_MST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getAllKaizenSql()
	{
		return "select * from " + TBL_KZN_TL_MST  + " where kznm_active = 'Y' ";
	}

	public static String selectSql() {
		return "Select * from " + TBL_KZN_TL_MST + " where KZNM_KEYID = ? ";
	}
	
	public static String getKznImprvCategorySql()
	{
		return  "select 'False' as \"select\", KCTM_KEYID,KCTM_TPMPILLARID,KCTM_NAME,KCTM_CODE from  " + TableNames.TBL_KZN_TL_CATEGORYMST + " where KCTM_TPMPILLARID = ? ";
		
	}
	
	public static String getKznLossDtlSql()
	{
		return  "select 'False' as \"select\",  LOSM_KEYID,LOSM_LOSSNAME,LOSM_LOSSNO from  " + TableNames.TBL_GEN_TL_LOSSMST + " ORDER BY LOSM_LOSSNAME ASC ";
		
	}
	
	public static String getKznPillarLossRecallSql(String count)
	{
		StringBuffer sql = new StringBuffer();
	
		/*
		 * sql.
		 * append(" SELECT TPMP_KEYID, '',TPMP_CODE AS PILLARNAME, PILLARTICK PILLARTICK, PILLARFLG PILLARFLG,"
		 * ); sql.
		 * append(" KZPL_KAIZENID KZPL_KAIZENID,TPMP_KEYID, KZNCATEGORYID KZNCATEGORYID, "
		 * ); sql.append("  KZNCATEGORY KZNCATEGORY, LOSSTICK LOSSTICK,");
		 * sql.append(" LOSSNOS, LOSSID, LOSFLG, KZLL_KAIZENID KZLL_KAIZENID, ''");
		 * sql.append(" FROM ("); sql.append(" SELECT DISTINCT TPMP_KEYID, TPMP_CODE,");
		 * sql.append(" DECODE(TPMP_KEYID, PILLARS, PILLARCHK, 0) AS PILLARTICK,");
		 * sql.append(" DECODE(TPMP_KEYID, PILLARS, PILLARFLG, 'INSERT') AS PILLARFLG,"
		 * ); sql.
		 * append(" DECODE(TPMP_KEYID, PILLARS, KZPL_KZNCATEGORYID) AS KZNCATEGORYID,");
		 * sql.append(" DECODE(TPMP_KEYID, PILLARS, KCTM_NAME)          AS KZNCATEGORY,"
		 * );
		 * sql.append(" DECODE(TPMP_KEYID, PILLARS, LOSCHK, 0)          AS LOSSTICK,");
		 * sql.
		 * append(" DECODE(TPMP_KEYID, PILLARS, LOSFLG, 'INSERT')          AS LOSFLG,");
		 * sql.append(" DECODE(TPMP_KEYID, PILLARS, LOSM_LOSSNO)        AS LOSSNOS,");
		 * sql.append(" DECODE(TPMP_KEYID, PILLARS, LOSM_KEYID)         AS LOSSID, ");
		 * sql.append(" DECODE(TPMP_KEYID, PILLARS, KZPL_KAIZENID) AS KZPL_KAIZENID,");
		 * sql.append(" DECODE(TPMP_KEYID, PILLARS, KZLL_KAIZENID) AS KZLL_KAIZENID ");
		 * sql.append(" 		from GEN_TL_TPMPILLARMST,");
		 * sql.append(" ( SELECT DISTINCT KZPL_TPMPILLARID AS PILLARS,");
		 * sql.append(" DECODE(KZPL_KAIZENID, null, '0', '1') as PILLARCHK,");
		 * sql.append(" DECODE(KZPL_KAIZENID, null, 'INSERT', 'VIEW') as PILLARFLG,");
		 * sql.append("   KZPL_KZNCATEGORYID, KCTM_NAME,");
		 * sql.append("   DECODE(KZLL_KAIZENID, null, '0', '1') as LOSCHK,");
		 * sql.append("   DECODE(KZLL_KAIZENID, null, 'INSERT', 'VIEW') as LOSFLG,");
		 * sql.append("   LOSM_LOSSNO, LOSM_KEYID, kzpl_kaizenid, kzll_kaizenid");
		 * sql.append(" FROM KZN_TL_LOSSLINK,"); sql.append("   GEN_TL_LOSSMST,");
		 * sql.append("   KZN_TL_PILLARLINK,"); sql.append("   KZN_TL_CATEGORYMST");
		 * sql.append(" WHERE KZPL_TPMPILLARID = KZLL_TPMPILLARID(+)");
		 * sql.append(" and KZLL_LOSSID = LOSM_KEYID(+)");
		 * sql.append(" and KZPL_KZNCATEGORYID = KCTM_KEYID(+)");
		 * sql.append(" and KZPL_KAIZENID(+)   = ? ");
		 * sql.append(" AND KZLL_KAIZENID(+)      = ? ");
		 * if(CommonFunctions.isValidKeyId(count)) { if(count.equals("0")) {
		 * sql.append(" UNION");
		 * sql.append(" SELECT '', '', '', '', '', '', '', '', '', '', '' FROM DUAL"); }
		 * } else { sql.append(" UNION");
		 * sql.append(" SELECT '', '', '', '', '', '', '', '', '', '', '' FROM DUAL"); }
		 * sql.
		 * append("  ) WHERE TPMP_KEYID = PILLARS(+) )   ORDER BY PILLARNAME, LOSFLG DESC "
		 * );
		 */
		
		
	    sql.append(" SELECT TP.TPMP_KEYID, '' AS dummy_col, TP.TPMP_CODE AS PILLARNAME, TP.PILLARTICK, TP.PILLARFLG, TP.KZPL_KAIZENID, TP.TPMP_KEYID, TP.KZNCATEGORYID, TP.KZNCATEGORY, ");
	    sql.append(" TP.LOSSTICK, TP.LOSSNOS, TP.LOSSID, TP.LOSFLG, TP.KZLL_KAIZENID, '' AS dummy2 ");
	    sql.append(" FROM ( SELECT DISTINCT     P.TPMP_KEYID,     P.TPMP_CODE, ");
	    sql.append(" CASE WHEN P.TPMP_KEYID = L.PILLARS THEN L.PILLARCHK ELSE '0' END AS PILLARTICK,    ");
		sql.append(" CASE WHEN P.TPMP_KEYID = L.PILLARS THEN L.PILLARFLG ELSE 'INSERT' END AS PILLARFLG, ");
	    sql.append(" CASE WHEN P.TPMP_KEYID = L.PILLARS THEN L.KZPL_KZNCATEGORYID END AS KZNCATEGORYID,  ");
		sql.append(" CASE WHEN P.TPMP_KEYID = L.PILLARS THEN L.KCTM_NAME END AS KZNCATEGORY, ");
	    sql.append(" CASE WHEN P.TPMP_KEYID = L.PILLARS THEN L.LOSCHK ELSE '0' END AS LOSSTICK, ");    
		sql.append(" CASE WHEN P.TPMP_KEYID = L.PILLARS THEN L.LOSFLG ELSE 'INSERT' END AS LOSFLG, ");
	    sql.append(" CASE WHEN P.TPMP_KEYID = L.PILLARS THEN L.LOSM_LOSSNO END AS LOSSNOS,    ");
		sql.append(" CASE WHEN P.TPMP_KEYID = L.PILLARS THEN L.LOSM_KEYID END AS LOSSID, ");
	    sql.append(" CASE WHEN P.TPMP_KEYID = L.PILLARS THEN L.KZPL_KAIZENID END AS KZPL_KAIZENID, ");   
		sql.append(" CASE WHEN P.TPMP_KEYID = L.PILLARS THEN L.KZLL_KAIZENID END AS KZLL_KAIZENID  ");
		sql.append(" FROM GEN_TL_TPMPILLARMST P ");
		sql.append(" LEFT JOIN ( SELECT DISTINCT KZPL_TPMPILLARID AS PILLARS, CASE WHEN KZPL_KAIZENID IS NULL THEN '0' ELSE '1' END AS PILLARCHK, ");
	    sql.append(" CASE WHEN KZPL_KAIZENID IS NULL THEN 'INSERT' ELSE 'VIEW' END AS PILLARFLG, KZPL_KZNCATEGORYID, KCTM_NAME,  ");
	    sql.append(" CASE WHEN KZLL_KAIZENID IS NULL THEN '0' ELSE '1' END AS LOSCHK, CASE WHEN KZLL_KAIZENID IS NULL THEN 'INSERT' ELSE 'VIEW' END AS LOSFLG, ");        
	    sql.append(" LOSM_LOSSNO, LOSM_KEYID,KZPL_KAIZENID, KZLL_KAIZENID  FROM KZN_TL_LOSSLINK LNK LEFT JOIN GEN_TL_LOSSMST LM ON LNK.KZLL_LOSSID = LM.LOSM_KEYID "); 
	    sql.append(" LEFT JOIN KZN_TL_PILLARLINK PL ON PL.KZPL_TPMPILLARID = LNK.KZLL_TPMPILLARID     LEFT JOIN KZN_TL_CATEGORYMST CM ON PL.KZPL_KZNCATEGORYID = CM.KCTM_KEYID  ");   
	    sql.append(" WHERE PL.KZPL_KAIZENID = 'KZB2500069'     AND LNK.KZLL_KAIZENID = 'KZB2500069' ");

	    if(CommonFunctions.isValidKeyId(count)) { if(count.equals("0")) {
			  sql.append(" UNION" );
			  sql.append(" SELECT " );
			  sql.append( " ''::text, ''::text, ''::text, ''::text, ''::text, " );
			  sql.append( " ''::text, ''::text, ''::text, ''::text, ''::text, " );
			  sql.append("  ''::text, ''::text, ''::text, ''::text " );
			  sql.append("    FROM (VALUES(1)) AS v(dummy)" ); 
			  }
			  } else { sql.append(" UNION");
			  sql.append(" SELECT " );
			  sql.append( " ''::text, ''::text, ''::text, ''::text, ''::text, " );
			  sql.append( " ''::text, ''::text, ''::text, ''::text, ''::text, " );
			  sql.append("  ''::text, ''::text, ''::text, ''::text " );
			  sql.append("    FROM (VALUES(1)) AS v(dummy)" ); 
			  }
			  sql. append(" ) L ON P.TPMP_KEYID = L.PILLARS) TP ORDER BY PILLARNAME, LOSFLG DESC  " );
	 
	/*	sql.append(" SELECT TPMP_KEYID, '', MAX(PILLARTICK) PILLARTICK, MAX(PILLARFLG) PILLARFLG, ");
		 sql.append(" MAX(KZPL_KAIZENID) KZPL_KAIZENID, MAX(KZNCATEGORYID) KZNCATEGORYID, ");
		 sql.append(" TPMP_CODE AS PILLARNAME, MAX(KZNCATEGORY) KZNCATEGORY, MAX(LOSSTICK) LOSSTICK,");
		 sql.append(" MAX(LOSSNOS), MAX(LOSSID), MAX(LOSFLG), '', ''");
		 sql.append(" FROM (");
		 sql.append(" SELECT DISTINCT TPMP_KEYID, TPMP_CODE,");
		 sql.append("   DECODE(TPMP_KEYID, PILLARS, PILLARCHK, 0) AS PILLARTICK, ");
		 sql.append("   DECODE(TPMP_KEYID, PILLARS, PILLARFLG, 'INSERT') AS PILLARFLG,");
		 sql.append("   DECODE(TPMP_KEYID, PILLARS, KZPL_KZNCATEGORYID) AS KZNCATEGORYID,");
		 sql.append("   DECODE(TPMP_KEYID, PILLARS, KCTM_NAME)          AS KZNCATEGORY,");
		 sql.append("   DECODE(TPMP_KEYID, PILLARS, DECODE(NVL(TO_CHAR(LOSM_LOSSNO),'0'),'0','0','1'), 0) AS LOSSTICK,");
		 sql.append("   DECODE(TPMP_KEYID, PILLARS, DECODE(NVL(TO_CHAR(LOSM_LOSSNO),'INSERT'),'INSERT','INSERT','VIEW'), 'INSERT') AS LOSFLG,");
		 sql.append("   DECODE(TPMP_KEYID, PILLARS, LOSM_LOSSNO)        AS LOSSNOS,");
		 sql.append("   DECODE(TPMP_KEYID, PILLARS, LOSM_KEYID)         AS LOSSID, ");
		 sql.append("   DECODE(TPMP_KEYID, PILLARS, KZPL_KAIZENID) AS KZPL_KAIZENID");
		 sql.append(" from GEN_TL_TPMPILLARMST,");
		 sql.append("   ( SELECT DISTINCT KZPL_TPMPILLARID AS PILLARS,");
		 sql.append("   DECODE(KZPL_KAIZENID, null, '0', '1') as PILLARCHK,");
		 sql.append("   DECODE(KZPL_KAIZENID, null, 'INSERT', 'VIEW') as PILLARFLG,");
		 sql.append("     KZPL_KZNCATEGORYID, KCTM_NAME,'' as LOSCHK, '' as LOSFLG,");
		 sql.append("     KZN_PC_KAIZEN.KZN_FN_GETLOSSNOSFORPILLAR(KZPL_KAIZENID, KZPL_TPMPILLARID) LOSM_LOSSNO, ");
		 sql.append("     KZN_PC_KAIZEN.KZN_FN_GETLOSSIDSFORPILLAR(KZPL_KAIZENID, KZPL_TPMPILLARID) LOSM_KEYID, ");
		 sql.append("     kzpl_kaizenid");
		 sql.append("   FROM ");
		 sql.append("     KZN_TL_PILLARLINK,");
		 sql.append("     KZN_TL_CATEGORYMST");
		 sql.append("   WHERE KZPL_KZNCATEGORYID = KCTM_KEYID(+)");
		 sql.append("   and KZPL_KAIZENID(+)   = ? ");
		 sql.append("   UNION");
		 sql.append("   SELECT '', '', '', '', '', '', '', '', '', '' FROM DUAL");
		 sql.append("    )) GROUP BY TPMP_KEYID,TPMP_CODE ");
	*/	
		 CommonMessage.debugMsg("Pillar "+sql.toString());
		return sql.toString();
	}
	
	
	public static String getKznHDScanDtlsSql(String kaizenId)
	{
		StringBuffer sql = new StringBuffer();
		/*
		 * sql.append(" SELECT KHDM_KAIZENID, SECT_NAME AS SHOP , ");
		 * sql.append(" CELL_NAME  as   CELL  , "); sql.
		 * append(" DECODE(MCHM_KEYID,KHDM_MACHINEID,MCHM_MACHINENAME || ' - [' || MCHM_MACHINENO || ']','') "
		 * ); sql.
		 * append("  AS  EQUIPMENT ,  TO_CHAR(KHDM_TARGETDATE,'DD-MON-YYYY') AS TARGETDATE, "
		 * ); sql.append("  EMPM_CODE AS RESPONSIBILITY, "); sql.
		 * append(" DECODE (KHDM_STATUS, 'P','PENDING', 'C', 'COMPLETE', 'A', 'PENDING') AS STATUS "
		 * ); sql.append(" FROM "); sql.
		 * append(" GEN_TL_SECTIONMST , GEN_TL_CELLMST , GEN_TL_MACHINEMST , KZN_TL_HDMST ,GEN_TL_EMPLOYEEMST "
		 * );
		 * sql.append(" WHERE CELL_SECTIONID = SECT_KEYID AND KHDM_CELLID = CELL_KEYID "
		 * ); sql.
		 * append(" AND KHDM_MACHINEID = MCHM_KEYID(+) AND KHDM_RESPONSIBILITYID = EMPM_KEYID AND KHDM_REFDOCTYPE='KZN' "
		 * ); sql.append(" AND KHDM_KAIZENID = " + "'" + kaizenId +"'" + " ");
		 */
		
		sql.append(" SELECT  khdm.khdm_kaizenid, sect.sect_name AS shop,cell.cell_name AS cell, ");
		sql.append("    CASE ");
		sql.append("        WHEN mchm.mchm_keyid = khdm.khdm_machineid ");
		sql.append("        THEN mchm.MCHM_MACHINENAME || ' - [' || mchm.MCHM_MACHINENO || ']'  ");
		sql.append("        ELSE '' ");
		sql.append("    END AS equipment, ");
		sql.append("    TO_CHAR(khdm.khdm_targetdate, 'DD-MON-YYYY') AS targetdate, ");
		sql.append("    empm.empm_code AS responsibility, ");
		 sql.append("   CASE  ");
		 sql.append("       WHEN khdm.khdm_status = 'P' THEN 'PENDING' ");
		 sql.append("       WHEN khdm.khdm_status = 'C' THEN 'COMPLETE' ");
		 sql.append("       WHEN khdm.khdm_status = 'A' THEN 'PENDING' ");
		 sql.append("       ELSE khdm.khdm_status ");
		 sql.append("   END AS status ");
		sql.append(" FROM kzn_tl_hdmst khdm ");
		sql.append(" JOIN gen_tl_cellmst cell ");
		sql.append(" ON khdm.khdm_cellid = cell.cell_keyid ");
		sql.append(" JOIN gen_tl_sectionmst sect ");
		sql.append("  ON cell.cell_sectionid = sect.sect_keyid ");
		sql.append(" LEFT JOIN gen_tl_machinemst mchm ");
		sql.append(" ON khdm.khdm_machineid = mchm.mchm_keyid ");
		sql.append(" JOIN gen_tl_employeemst empm ");
		sql.append(" ON khdm.khdm_responsibilityid = empm.empm_keyid ");
		sql.append(" WHERE khdm.khdm_refdoctype = 'KZN' ");
		sql.append(" AND khdm.khdm_kaizenid = " +"'" + kaizenId +"'" + " ");
		
		CommonMessage.debugMsg(" Inside :: HD Scan "+sql);
		return sql.toString();
	}
	

	
	public static String getKznYYDtlsSql(String wwmsKeyid)
	{
	
       StringBuffer stbuf = new  StringBuffer();
       stbuf.append( " select DISTINCT WWDT_keyid,WWMS_KEYID, WWDT_ANSWER from BDM_TL_WHYWHYMST, BDM_TL_WHYWHYDTL" );
       stbuf.append( " where  WWDT_WWMS_KEYID = WWMS_KEYID" );
       stbuf.append( " and WWMS_REFDOCNO= '"+wwmsKeyid+"' and WWMS_REFDOCTYPE='KZN' ORDER BY WWDT_keyid " );
 //      CommonMessage.debugMsg("stbufstbuf:: "+stbuf.toString());
       CommonMessage.debugMsg(" stbufstbuf:: "+stbuf.toString());
       return stbuf.toString();
	

	}
	
	public static String getUpdateCompleteDtlsSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'" +
			  ""
			  ;
		return sql;
	}
	public static String getKznHDCellMchSql(String kznKeyid)
	{
		String sql="Select KHDM_CELLID,KHDM_MACHINEID from KZN_TL_HDMST where KHDM_KEYID ='"+ kznKeyid+"' ";
		
		return sql;
	}
	
	public static String updateCompleteDetailsSql(String kznmKeyid, String khdmKeyid,String remarks, String completedBy, String completedDate,String modifiedDate)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE KZN_TL_MST SET KZNM_STATUS =  'C', ");
		sql.append(" KZNM_COMPLETEDID = '"+completedBy+"', ");
		sql.append(" KZNM_COMPLETEDDATE = TO_DATE('"+completedDate+"','DD-MON-YYYY hh24:mi:ss') ,");
		sql.append(" KZNM_REMARKS = '" + remarks + "' ,");
		sql.append(" KZNM_MODIFIEDON =  TO_DATE('"+modifiedDate+"','DD-MON-YYYY hh24:mi:ss')");
		sql.append(" WHERE KZNM_KEYID ='"+ kznmKeyid +"'");
		
		return sql.toString();
	}
	
	public static String getUpdateCompletionSql(TableFieldType [] fieldTypeArr, Object [] dataArray,String modifiedOn)
	{
		String sql ="UPDATE KZN_TL_MST SET KZNM_STATUS =  'C', "+
					fieldTypeArr[tableFldConstants.completedid.ordinal()].fieldName  +
			 " = '" +  (String)dataArray[ tableFldConstants.completedid.ordinal() ] + "' , " + 
			 fieldTypeArr[tableFldConstants.completeddate.ordinal()].fieldName  +
		 " = to_date( '" +  (String)dataArray[ tableFldConstants.completeddate.ordinal() ] + "' , 'DD-MON-YYYY hh24:mi:ss') , "+ 
		 fieldTypeArr[tableFldConstants.remarks.ordinal()].fieldName  +  
		 " = '" +  (String)dataArray[ tableFldConstants.remarks.ordinal() ] + "' , "+ 
		 fieldTypeArr[tableFldConstants.modifiedon.ordinal()].fieldName  +
		 " = to_date( '" +  modifiedOn + "', 'DD-MON-YYYY hh24:mi:ss')"; 
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
	
	
	
	public static String resultDataSql(String kznmKeyid)
	{
		StringBuffer sql = new StringBuffer();
		/*
		 * sql.
		 * append(" SELECT REPLACE(REPLACE(REPLACE(KZNM_RESULTDESCRIPTION,'{}',''), '<*',''),'*>',''), "
		 * ); sql.
		 * append(" IMFL_BLOBLENGTH,IMFL_FILENAME FROM KZN_TL_MST,GEN_TL_ALLMODULEIMGFILE "
		 * ); sql.
		 * append(" WHERE KZNM_KEYID = IMFL_REFKEYID(+) AND IMFL_IMAGETYPE(+)='RES' ");
		 * sql.append(" AND IMFL_REFDOCTYPE(+)='KZN' AND KZNM_KEYID='"+kznmKeyid +"'");
		 */
		
		sql.append(" SELECT ");
	    sql.append(" REPLACE(REPLACE(REPLACE(KZNM_RESULTDESCRIPTION,'{}',''), '<*',''),'*>','') AS result_description, ");
	    sql.append(" IMFL_BLOBLENGTH, IMFL_FILENAME FROM KZN_TL_MST K LEFT JOIN GEN_TL_ALLMODULEIMGFILE F ");
	    sql.append(" ON F.IMFL_REFKEYID = K.KZNM_KEYID  AND F.IMFL_IMAGETYPE = 'RES' ");
	    sql.append(" AND F.IMFL_REFDOCTYPE = 'KZN' WHERE K.KZNM_KEYID = '"+kznmKeyid +"'");
	    
		return sql.toString();
	}
	
	public static String resultDescriptionSql(String kznmKeyid)
	{
		String sql="select KZNM_RESULTDESCRIPTION from KZN_TL_MST WHERE KZNM_KEYID ='"+ kznmKeyid +"'";
		CommonMessage.debugMsg("HDDDDD0"+sql);
		return sql;
	}
	
	
	public static String chkForDuplicatesSql(String kznmKeyid,String factId,String cellId,String mchId,String theme,String  bnchMrk,String target)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT KZNM_KEYID FROM KZN_TL_MST WHERE KZNM_FACTORYID = '"+ factId +"'");
		sql.append(" AND KZNM_MACHINEID  = '"+ mchId +"'");
		sql.append(" AND KZNM_CELLID = '"+ cellId +"'");
		sql.append(" AND KZNM_THEME  = '"+theme +"'");
		sql.append(" AND KZNM_BENCHMARK  = '"+bnchMrk+"'");
		sql.append(" AND KZNM_TARGET  = '"+target +"'");
		
		if(UIUtils.isValidKeyId(kznmKeyid))
			sql.append(" AND KZNM_KEYID  <> '"+kznmKeyid+"'");
		
		CommonMessage.debugMsg("chkForDuplicatesSql="+sql);
		return sql.toString();
	}

	public static String getUpdateKznResultImage() 
	{
		String sql ="UPDATE KZN_TL_MST SET KZNM_RESULTIMAGE =  ? where KZNM_KEYID =?"; 
		return sql;
	}

	public static String getkznCompleteSql(String kznKeyid) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT KZNM_KEYID,KZNM_DATE,KZNM_SECTIONID,KZNM_CELLID, ");
		sql.append(" KZNM_MACHINEID,KZNM_THEME,KZNM_COMPLETEDID, " );
		sql.append(" KZNM_COMPLETEDDATE,KZNM_REMARKS ");
		sql.append(" from KZN_TL_MST where KZNM_KEYID = '"+kznKeyid+"'");
		
		return sql.toString();
	
	}
	
	public static String getkznDocUpdatesSql(String kznKeyid ,String docId)
	{
		String sql="UPDATE GEN_TL_DOCUPDATES SET DCUP_DETAILID = '"+ kznKeyid +"',DCUP_UPDATEDOCTYPE='KZN' where DCUP_KEYID= '"+ docId +"'";
		return sql;
	}

	public static String getKaizenCompleteFunction() {
		
		return "KZN_FN_KZNCOMPLETE";
	}

	public static String getKaizenHDCompleteFunction() {
		
		return "KZN_FN_KZNHDSUBQUERY";
	}

	public static String getKaizenViewFunction() {
		return "KZN_FN_KAIZENVIEW_1";
	}

	public static String getkznGraphMnthsSql(String kznKeyid)
	{
		
		return " select * from KZN_TL_GRAPHDATA where 	KZGD_KAIZENID ='"+kznKeyid+"' order by KZGD_DATEMONTHYEAR ";
	}

	public static String insertYYCounterMeasureSql(String yycmKeyid,String yyId, KznTlMst kznTlMst) 
	{
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO BDM_TL_YYCOUNTERMEASURELINK VALUES('"+yycmKeyid+"','"+yyId+"','KZN','"+kznTlMst.getKznmKeyid()+"','{}','-','-','-','-','-','Y',");
		sql.append("'"+kznTlMst.getKznmCreatedby()+"',to_date('"+kznTlMst.getKznmCreatedon()+"','dd-MON-yyyy hh24:mi:ss'),to_date('"+kznTlMst.getKznmModifiedon()+"', 'dd-MON-yyyy hh24:mi:ss'))");
		
		
		return sql.toString();
		
	}

	public static String selectKzbnSql(String kznbKeyid) {
		return "Select * from " + TBL_KZN_TL_MST + " where KZNM_KZBNKEYID =  '"+kznbKeyid+"'";
	}

	public static String selectKznThemeSql(String kznbKeyid) {
		return "Select KZBN_KAIZEN from KZN_TL_KAIZENBANKMST where KZBN_KEYID =  '"+kznbKeyid+"'";
	}

	public static String selectData(String keyid) {
		// TODO Auto-generated method stub
		//KINK_KEYID,
		String sql= " select KINK_PILLARID,KINK_IMPACTAREA,KINK_DESCRIPTION ";
		sql+= " from KPI_TL_INDICATOR " ;
		sql+= " where KINK_KEYID='"+keyid+"'";
	    return sql;
	    
	    
	    //  select KINK_KEYID,KINK_PILLARID,KINK_IMPACTAREA,KINK_DESCRIPTION from KPI_TL_INDICATOR;
	     
	
	}
	
	public static String selectCategory(String keyid) {
		// TODO Auto-generated method stub
		//KINK_KEYID,
		String sql= " select KZCT_KEYID,KZCT_CODE,KZCT_NAME ";
		sql+= " from KZN_TL_CATEGORYTHMMST " ;
		sql+= " where KZCT_KEYID='"+keyid+"'";
	    return sql;
	
	}
	
	 //where empm_keyid='EMP00001';

	public static String selectTeamData(String keyid) {
		// TODO Auto-generated method stub
		String sql= " select empm_name from gen_tl_employeemst ";
		sql+= " where empm_keyid='"+keyid+"'";
		CommonMessage.debugMsg(" Inside :: 1234567890 "+sql);
	    return sql;
		
		
	}

	public static String updateKaizenStatus(String status, String keyid, String nextLevel, String type, String value, String approvallevel, String mpvalue) {
		// TODO Auto-generated method stub
		
		String sql=null;
		if(UIUtils.isValidKeyId(type))
		     sql= " UPDATE  KZN_TL_MST  SET KZNM_UTILISEFORFUTURE ='"+value+"' WHERE KZNM_KEYID ='"+keyid+"' ";
		//else if(UIUtils.isValidKeyId(approvallevel))
			//sql= " UPDATE  KZN_TL_MST  SET KZNM_ISWORTHFORMP ='"+value+"' WHERE KZNM_KEYID ='"+keyid+"' ";
		else
		     sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='"+status+"',KZNM_ISWORTHFORMP ='"+mpvalue+"',KZNM_APROV_LEVEL='"+nextLevel+"' WHERE KZNM_KEYID='"+keyid+"' ";
		
		return sql;
	}

	public static String updateHd(String keyid) {
		String sql= "UPDATE KZN_TL_HDMST SET KHDM_STATUS='C' WHERE KHDM_KEYID=(SELECT KZBN_REFDOCNO FROM KZN_TL_KAIZENBANKMST WHERE KZBN_REFDOCTYPE='HD' AND KZBN_KEYID='"+keyid+"')";
		//sql+= "";
		CommonMessage.debugMsg(" Inside :: 1234567890 "+sql);
		return sql;
	}

	public static String getKznHDScanStatusSql(String hdKeyid) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT KHDM_KAIZENID, SECT_NAME AS SHOP , ");
		sql.append(" CELL_NAME  as   CELL  , ");
		sql.append(" DECODE(MCHM_KEYID,KHDM_MACHINEID,MCHM_MACHINENAME || ' - [' || MCHM_MACHINENO || ']','') ");
		sql.append("  AS  EQUIPMENT ,  TO_CHAR(KHDM_TARGETDATE,'DD-MON-YYYY') AS TARGETDATE, ");
		sql.append("  EMPM_CODE AS RESPONSIBILITY, ");
		sql.append(" DECODE (KHDM_STATUS, 'P','PENDING', 'C', 'COMPLETE', 'A', 'PENDING') AS STATUS ");
		sql.append(" FROM ");
		sql.append(" GEN_TL_SECTIONMST , GEN_TL_CELLMST , GEN_TL_MACHINEMST , KZN_TL_HDMST ,GEN_TL_EMPLOYEEMST ");
		sql.append(" WHERE KHDM_KEYID='"+hdKeyid+"'");
		sql.append(" AND CELL_SECTIONID = SECT_KEYID AND KHDM_CELLID = CELL_KEYID ");
		sql.append(" AND KHDM_MACHINEID = MCHM_KEYID(+) AND KHDM_RESPONSIBILITYID = EMPM_KEYID AND KHDM_REFDOCTYPE='KZN' ");
	//	sql.append(" AND KHDM_KAIZENID = " + "'" + kaizenId +"'" + " ");
		//sql.append(" AND KHDM_KAIZENID = " + "'" + kaizenId +"'" + " ");
		CommonMessage.debugMsg(" Inside :: HD Scan "+sql);
		return sql.toString();
	}

	public static String getHdKey(String kaizenId) {
		String sql="SELECT KZBN_REFDOCNO FROM KZN_TL_KAIZENBANKMST WHERE KZBN_KEYID='"+kaizenId+"' AND KZBN_REFDOCTYPE='HD'";
		return sql;
	}

	public static String selectKzbnBenefitSql(String kznbKeyid) {
		// TODO Auto-generated method stub
		String sql="SELECT KZBN_BENEFIT FROM KZN_TL_KAIZENBANKMST WHERE KZBN_KEYID='"+kznbKeyid+"' ";
		return sql;
	}

	public static String selectKzbnpcdqsmeSql(String kznbKeyid) {
		// TODO Auto-generated method stub
		String sql="SELECT KZBN_PQCDSME FROM KZN_TL_KAIZENBANKMST WHERE KZBN_KEYID='"+kznbKeyid+"' ";
		return sql;
	}

	public static String selectKzbnThemenameSql(String benefit) {
		String sql= " select KZCT_NAME ";
		sql+= " from KZN_TL_CATEGORYTHMMST " ;
		sql+= " where KZCT_KEYID='"+benefit+"'";
	    return sql;
	}
}

