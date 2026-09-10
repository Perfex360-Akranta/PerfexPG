package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.sql.EntTlTrainingareaSql.tableFldConstants;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
import com.akranta.tpm.dao.sql.KznTlMstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;


public class BAL_BdmTlWhywhymstSql {

	public static final String TBL_BDM_TL_WHYWHYMST = "BDM_TL_WHYWHYMST";
	public static final String TBL_KZN_TL_MST = "KZN_TL_MST";

	TableFieldType [] wwmsDbFields = null;

	public enum   tableFldConstants
	{
		keyid, date, factoryid, sectionid, lossid, cellid, subcellid
		, machineid, assemblyid, targetpillarid, refdoctype, refdocno
		, phenomenaid, finalaction, sparesreplaced, checksmade, symptombefore
		, youdidnot, countermeasureid, countermeasure, rootcauseid, rootcause
		, isjh, ispm, iskk, isopl, preventivemeasureid, preventivemeasure
		, maintinchargeid, status, ishdpossible, prevdate, preveffectiveness
		, ispy, isojt, ojtdesc, issop, sopdesc, iskzn, ispokayoke, formtype
		, pokayoke, accidentdesc, accidentphen, prevno, prevperson, iseffective
		, flid, area, problem, timespent, problemattendby, whywhydoneby
		, reportdatetime, othercheckpoints,sparesid,pillarid,productid, immediateaction
		, tempfield1,tempfield2,tempfield3,active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getWwmsDbFields() {
		return wwmsDbFields;
	}

	public BAL_BdmTlWhywhymstSql()
	{
		wwmsDbFields = new TableFieldType[ 66 ];
		for(int i = 0;i < 66; i++)
		{	
			wwmsDbFields[ i ] = new TableFieldType();
		}
		wwmsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WWMS_KEYID";
		wwmsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.date.ordinal() ].fieldName = "WWMS_DATE";
		wwmsDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		wwmsDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "WWMS_FACTORYID";
		wwmsDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "WWMS_SECTIONID";
		wwmsDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.lossid.ordinal() ].fieldName = "WWMS_LOSSID";
		wwmsDbFields[ tableFldConstants.lossid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "WWMS_CELLID";
		wwmsDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.subcellid.ordinal() ].fieldName = "WWMS_SUBCELLID";
		wwmsDbFields[ tableFldConstants.subcellid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "WWMS_MACHINEID";
		wwmsDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "WWMS_ASSEMBLYID";
		wwmsDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.targetpillarid.ordinal() ].fieldName = "WWMS_TARGETPILLARID";
		wwmsDbFields[ tableFldConstants.targetpillarid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "WWMS_REFDOCTYPE";
		wwmsDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.refdocno.ordinal() ].fieldName = "WWMS_REFDOCNO";
		wwmsDbFields[ tableFldConstants.refdocno.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldName = "WWMS_PHENOMENAID";
		wwmsDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.finalaction.ordinal() ].fieldName = "WWMS_FINALACTION";
		wwmsDbFields[ tableFldConstants.finalaction.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.sparesreplaced.ordinal() ].fieldName = "WWMS_SPARESREPLACED";
		wwmsDbFields[ tableFldConstants.sparesreplaced.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.checksmade.ordinal() ].fieldName = "WWMS_CHECKSMADE";
		wwmsDbFields[ tableFldConstants.checksmade.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.symptombefore.ordinal() ].fieldName = "WWMS_SYMPTOMBEFORE";
		wwmsDbFields[ tableFldConstants.symptombefore.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.youdidnot.ordinal() ].fieldName = "WWMS_YOUDIDNOT";
		wwmsDbFields[ tableFldConstants.youdidnot.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.countermeasureid.ordinal() ].fieldName = "WWMS_COUNTERMEASUREID";
		wwmsDbFields[ tableFldConstants.countermeasureid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "WWMS_COUNTERMEASURE";
		wwmsDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.rootcauseid.ordinal() ].fieldName = "WWMS_ROOTCAUSEID";
		wwmsDbFields[ tableFldConstants.rootcauseid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "WWMS_ROOTCAUSE";
		wwmsDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.isjh.ordinal() ].fieldName = "WWMS_ISJH";
		wwmsDbFields[ tableFldConstants.isjh.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.ispm.ordinal() ].fieldName = "WWMS_ISPM";
		wwmsDbFields[ tableFldConstants.ispm.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.iskk.ordinal() ].fieldName = "WWMS_ISKK";
		wwmsDbFields[ tableFldConstants.iskk.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.isopl.ordinal() ].fieldName = "WWMS_ISOPL";
		wwmsDbFields[ tableFldConstants.isopl.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.preventivemeasureid.ordinal() ].fieldName = "WWMS_PREVENTIVEMEASUREID";
		wwmsDbFields[ tableFldConstants.preventivemeasureid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.preventivemeasure.ordinal() ].fieldName = "WWMS_PREVENTIVEMEASURE";
		wwmsDbFields[ tableFldConstants.preventivemeasure.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.maintinchargeid.ordinal() ].fieldName = "WWMS_MAINTINCHARGEID";
		wwmsDbFields[ tableFldConstants.maintinchargeid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.status.ordinal() ].fieldName = "WWMS_STATUS";
		wwmsDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.ishdpossible.ordinal() ].fieldName = "WWMS_ISHDPOSSIBLE";
		wwmsDbFields[ tableFldConstants.ishdpossible.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.prevdate.ordinal() ].fieldName = "WWMS_PREVDATE";
		wwmsDbFields[ tableFldConstants.prevdate.ordinal() ].fieldType = 'D';

		wwmsDbFields[ tableFldConstants.preveffectiveness.ordinal() ].fieldName = "WWMS_PREVEFFECTIVENESS";
		wwmsDbFields[ tableFldConstants.preveffectiveness.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.ispy.ordinal() ].fieldName = "WWMS_ISPY";
		wwmsDbFields[ tableFldConstants.ispy.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.isojt.ordinal() ].fieldName = "WWMS_ISOJT";
		wwmsDbFields[ tableFldConstants.isojt.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.ojtdesc.ordinal() ].fieldName = "WWMS_OJTDESC";
		wwmsDbFields[ tableFldConstants.ojtdesc.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.issop.ordinal() ].fieldName = "WWMS_ISSOP";
		wwmsDbFields[ tableFldConstants.issop.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.sopdesc.ordinal() ].fieldName = "WWMS_SOPDESC";
		wwmsDbFields[ tableFldConstants.sopdesc.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.iskzn.ordinal() ].fieldName = "WWMS_ISKZN";
		wwmsDbFields[ tableFldConstants.iskzn.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.ispokayoke.ordinal() ].fieldName = "WWMS_ISPOKAYOKE";
		wwmsDbFields[ tableFldConstants.ispokayoke.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.formtype.ordinal() ].fieldName = "WWMS_FORMTYPE";
		wwmsDbFields[ tableFldConstants.formtype.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.pokayoke.ordinal() ].fieldName = "WWMS_POKAYOKE";
		wwmsDbFields[ tableFldConstants.pokayoke.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.accidentdesc.ordinal() ].fieldName = "WWMS_ACCIDENTDESC";
		wwmsDbFields[ tableFldConstants.accidentdesc.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.accidentphen.ordinal() ].fieldName = "WWMS_ACCIDENTPHEN";
		wwmsDbFields[ tableFldConstants.accidentphen.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.prevno.ordinal() ].fieldName = "WWMS_PREVNO";
		wwmsDbFields[ tableFldConstants.prevno.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.prevperson.ordinal() ].fieldName = "WWMS_PREVPERSON";
		wwmsDbFields[ tableFldConstants.prevperson.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.iseffective.ordinal() ].fieldName = "WWMS_ISEFFECTIVE";
		wwmsDbFields[ tableFldConstants.iseffective.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "WWMS_FLID";
		wwmsDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.area.ordinal() ].fieldName = "WWMS_AREA";
		wwmsDbFields[ tableFldConstants.area.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.problem.ordinal() ].fieldName = "WWMS_PROBLEM";
		wwmsDbFields[ tableFldConstants.problem.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.timespent.ordinal() ].fieldName = "WWMS_TIMESPENT";
		wwmsDbFields[ tableFldConstants.timespent.ordinal() ].fieldType = 'N';

		wwmsDbFields[ tableFldConstants.problemattendby.ordinal() ].fieldName = "WWMS_PROBLEMATTENDBY";
		wwmsDbFields[ tableFldConstants.problemattendby.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.whywhydoneby.ordinal() ].fieldName = "WWMS_WHYWHYDONEBY";
		wwmsDbFields[ tableFldConstants.whywhydoneby.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.reportdatetime.ordinal() ].fieldName = "WWMS_REPORTDATETIME";
		wwmsDbFields[ tableFldConstants.reportdatetime.ordinal() ].fieldType = 'D';

		wwmsDbFields[ tableFldConstants.othercheckpoints.ordinal() ].fieldName = "WWMS_OTHERCHECKPOINTS";
		wwmsDbFields[ tableFldConstants.othercheckpoints.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.sparesid.ordinal() ].fieldName = "WWMS_SPARESID";
		wwmsDbFields[ tableFldConstants.sparesid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.pillarid.ordinal() ].fieldName = "WWMS_PILLARID";
		wwmsDbFields[ tableFldConstants.pillarid.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.productid.ordinal() ].fieldName = "WWMS_PRODUCTID";
		wwmsDbFields[ tableFldConstants.productid.ordinal() ].fieldType = 'V';
		
		
		wwmsDbFields[ tableFldConstants.immediateaction.ordinal() ].fieldName = "WWMS_IMMEDIATEACTION";
		wwmsDbFields[ tableFldConstants.immediateaction.ordinal() ].fieldType = 'V';
		
		wwmsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "WWMS_TEMPFIELD1";
		wwmsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';
		
		wwmsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "WWMS_TEMPFIELD2";
		wwmsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';
		
		wwmsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "WWMS_TEMPFIELD3";
		wwmsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';
		
		wwmsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "WWMS_ACTIVE";
		wwmsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		wwmsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WWMS_CREATEDBY";
		wwmsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wwmsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WWMS_CREATEDON";
		wwmsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wwmsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WWMS_MODIFIEDON";
		wwmsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_WHYWHYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_WHYWHYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_WHYWHYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}




	

	public static String getSelectSql(String wwmsKeyid)
	{
		return "select *  from " + TBL_BDM_TL_WHYWHYMST  + " where wwms_keyid = '" + wwmsKeyid + "'";
	}
	public static String getSelectYYSql(String refDocId)
	{
		return "select *  from " + TBL_BDM_TL_WHYWHYMST  + " where wwms_refdocno = '" + refDocId + "'";
	}
	
	public static String getRootCauseSql(String openMode)
	{
		//return "SELECT WRCM_KEYID,'',WRCM_NAME,'','','','' FROM "+TableNames.TBL_BDM_TL_ROOTCAUSEMST;
		String sql = "SELECT WRCM_KEYID,'',WRCM_NAME FROM "+TableNames.TBL_BDM_TL_ROOTCAUSEMST;
		if(CommonFunctions.isValidKeyId(openMode))
			sql += " WHERE WRCM_TYPE='"+openMode+"'";
		else
			sql += " WHERE WRCM_TYPE='BDM'";
		return sql;
	}
	public static String getPillarSql(String yyId,String pillarFlag)
	{
		StringBuffer sb = new StringBuffer();
		/*sb.append("SELECT   doctype,DECODE (TRIM (doctype),TRIM (yycm_refdoctype), yycm_countermsrid) AS countermeasrid,");
		sb.append("'' AS SAVE FROM (select * from  ( select * from ( SELECT 'OPL' AS doctype FROM DUAL UNION ");
		sb.append("SELECT 'KZN' AS doctype FROM DUAL UNION ");
		sb.append("SELECT 'PMC' AS doctype FROM DUAL UNION ");
		sb.append("SELECT 'JHN' AS doctype FROM DUAL ) ) a,");		
		sb.append("(SELECT MAX(yycm_refdoctype) yycm_refdoctype, MAX(yycm_countermsrid) yycm_countermsrid");
		sb.append(" FROM (SELECT yycm_refdoctype, yycm_countermsrid");
		sb.append(" FROM bdm_tl_yycountermeasurelink WHERE yycm_yyid = '"+yyId+"'");
		sb.append(" union select '' , '' from dual )) b)");
        sb.append(" ORDER BY doctype");*/
        sb.append("SELECT doctype,yycm_countermsrid, '' as save ");
        if(CommonFunctions.isValidKeyId(pillarFlag))
        {
        	if(pillarFlag.equals("SHE") || pillarFlag.equals("DOCK"))
        	{
	        	sb.append(" FROM (SELECT *  FROM (SELECT 'OPL' AS doctype  FROM DUAL UNION ");
	 	        sb.append("SELECT 'KZN' AS doctype  FROM DUAL UNION "); 
	 	        sb.append("SELECT 'OJT' AS doctype  FROM DUAL UNION "); 
	 	        if(pillarFlag.equals("SHE"))
	 	        	sb.append("SELECT 'POK' AS doctype  FROM DUAL ");
	 	        else
	 	        	sb.append("SELECT 'SOP' AS doctype  FROM DUAL ");
	 	       sb.append("UNION SELECT 'TRN' AS doctype  FROM DUAL ");
        	}
        	else if(pillarFlag.equals("CC") || pillarFlag.equals("IMT"))
        	{
        		sb.append(" FROM (SELECT *  FROM (SELECT 'OPL' AS doctype  FROM DUAL UNION ");
      	        sb.append("SELECT 'KZN' AS doctype  FROM DUAL UNION "); 
      	        sb.append("SELECT 'PMC' AS doctype  FROM DUAL UNION "); 
      	        sb.append("SELECT 'JHN' AS doctype  FROM DUAL ");
      	        sb.append("UNION SELECT '4MT' AS doctype  FROM DUAL ");
      	        sb.append("UNION SELECT 'TRN' AS doctype  FROM DUAL ");
        	}
        }
        else
        {
	        sb.append(" FROM (SELECT *  FROM (SELECT 'OPL' AS doctype  FROM DUAL UNION ");
	        sb.append("SELECT 'KZN' AS doctype  FROM DUAL UNION "); 
	        sb.append("SELECT 'PMC' AS doctype  FROM DUAL UNION "); 
	        sb.append("SELECT 'JHN' AS doctype  FROM DUAL ");//)) a,"); 	
	        sb.append("UNION SELECT 'TRN' AS doctype  FROM DUAL ");
        }
       // if(CommonFunctions.isValidKeyId(pillarFlag) && pillarFlag.equals("CC"))
        	
        sb.append(")) a,"); 
        sb.append("(SELECT yycm_countermsrid yycm_countermsrid,yycm_refdoctype yycm_refdoctype");
        sb.append(" FROM (SELECT yycm_refdoctype, yycm_countermsrid FROM bdm_tl_yycountermeasurelink "); 
        sb.append(" WHERE yycm_yyid = '"+yyId+"') ) b where doctype =  yycm_refdoctype(+) order by doctype");
                      
                      
                       
                   
                       

		/*sb.append("SELECT DOCTYPE, DECODE(TRIM(DOCTYPE), TRIM(YYCM_REFDOCTYPE), YYCM_COUNTERMSRID) AS COUNTERMEASRID,");
		sb.append("'' AS SAVE FROM (SELECT 'OPL' AS DOCTYPE FROM DUAL UNION ");
		sb.append("SELECT 'KZN' AS DOCTYPE FROM DUAL UNION ");
		sb.append("SELECT 'PMC' AS DOCTYPE FROM DUAL UNION ");
		sb.append("SELECT 'JHN' AS DOCTYPE FROM DUAL),(");
		sb.append("SELECT YYCM_REFDOCTYPE, YYCM_COUNTERMSRID FROM BDM_TL_YYCOUNTERMEASURELINK ");
		sb.append("WHERE YYCM_YYID = '"+yyId+"') ORDER BY DOCTYPE");*/
		
		//return "SELECT WRCM_KEYID,'',WRCM_NAME,'','','','' FROM "+TableNames.TBL_BDM_TL_ROOTCAUSEMST;
		CommonFunctions.debugMsg( sb.toString());
		return sb.toString();
	}
	
	public static String getSelectedRootCauseSql(String yyNo)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT WWMS_ROOTCAUSEID,WWMS_ROOTCAUSE,WWMS_ISJH,WWMS_ISPM,");
		sb.append("WWMS_ISKK,WWMS_ISOPL,WWMS_FINALACTION,WWMS_CHECKSMADE,WWMS_YOUDIDNOT,");
		sb.append("WWMS_COUNTERMEASURE FROM "+TBL_BDM_TL_WHYWHYMST+" WHERE WWMS_KEYID = '"+yyNo+"'");
		
		return sb.toString();
	}
	
	public static String getClassification(String rootCause,String tmpCode)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT BCLM_KEYID FROM BDM_VW_CLASSIFICATION");
		if(CommonFunctions.isValidKeyId(rootCause))
			sb.append(" where BCLM_NAME = '"+ rootCause+"'");
		if(CommonFunctions.isValidKeyId(tmpCode))
			sb.append(" and TPMP_CODE = '"+ tmpCode+"'");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static String selectWhyWhy()
	{
		String sql = "select * from "+ TBL_BDM_TL_WHYWHYMST+" where wwms_refdocno=?";
		return sql;
	}
	public static String checkMstExistSql(String refDocNo)
	{
		String sql = "select count(*) from "+ TBL_BDM_TL_WHYWHYMST+" where wwms_refdocno='"+refDocNo+"'";
		return sql;
	}
	
	public static String checkCounterMsrSql()
	{
		String sql = "SELECT CNFM_SETTINGVALUE FROM "+TableNames.TBL_ADM_TL_CONFIGURATIONMST + " WHERE CNFM_CODE ='ENABLEJHCOUNTERMSR'";
		return sql;
	}
	public static String checkCounterMsrSqlExist()
	{
		String sql = "SELECT COUNT(*) FROM "+TableNames.TBL_ADM_TL_CONFIGURATIONMST + " WHERE CNFM_CODE ='ENABLEJHCOUNTERMSR'";
		return sql;
	}
	public static String getProgramSql(String start,String end) {
		// TODO Auto-generated method stub
		String keyFlag=null;
		
		String sql = " select *  from  (";
		sql += "SELECT rownum AS slno, A.* FROM(";
		
			sql += "select prog_keyid as keyid,'' as chk,prog_name as prgname from ent_tl_programmst";
		
			sql+= " order by PROG_NAME";
		
		sql+= ")A ) "; //where slno >= "+start+" and slno <= "+end;		
		
		sql+= " order by prgname";
		
		com.akranta.tpm.utils.CommonFunctions.debugMsg(sql);
		return sql;		
	}

	public static String selectmaskeyid() {
		
	String sql=" select * from BDM_TL_WHYWHYMST where  WWMS_KEYID= ?";
	return sql;
	}

	public static String getMainGrid() {
		String sql=" SELECT WWMS_KEYID,WWMS_AREA,WWMS_PROBLEM,to_char(WWMS_DATE,'DD-MM-YYYY'),WWMS_WHYWHYDONEBY,WWMS_PROBLEMATTENDBY from BDM_TL_WHYWHYMST";
		return sql;
		
	}

	public static String getanalysis() {
		String sql=" SELECT WWDT_KEYID, WWDT_WHY, WWDT_ANSWER FROM BDM_TL_WHYWHYDTL WHERE WWDT_WWMS_KEYID= ?";
		return sql;
	}
	
	public  String counterMeasure(CommonFilter commonFilter)
	{
		StringBuffer sql= new StringBuffer();
		sql.append(" select 'Proposed preventive counter measures given below' AS TYPE,'Proposed preventive counter measures given below' AS KEYID, ");
		sql.append(" 'Proposed preventive counter measures given below' AS BTN,'Responsibility' AS RESPON,'Date' AS DTE,'Status' AS STATUS,'Theme' AS THEME,0 as dataorder  from dual ");
		sql.append(" union  ");
		sql.append(" SELECT DISTINCT CNAME,max(KEYID),max(AA),max(EMPNAME),max(CDATE),max(BB),MAX(THEME),dataorder as dataorder FROM( ");
		sql.append(countermeasureInnerQuery(commonFilter.getYyNo())+" group by cname,dataorder order by dataorder asc");
		System.out.println("sql.....1234"+sql);
		return sql.toString();
		
	}
	public static String countermeasureInnerQuery(String yyno)
	{
		StringBuffer sql= new StringBuffer();
		
		/*
		sql.append("  SELECT 'Change in Workpractice / Training / OPL' AS CNAME,OPLM_KEYID AS KEYID,'' AS AA,EMPM_NAME AS EMPNAME,TO_CHAR(OPLM_PREPAREDDATE) AS CDATE,decode(oplm_status,'P','A','P') AS BB,1 as dataorder ");
		sql.append(" FROM OPL_TL_MST,GEN_TL_EMPLOYEEMST WHERE OPLM_PREPAREDID = EMPM_KEYID (+) AND OPLM_REFDOCTYPE = 'YY'  ");
		//if(UIUtils.isValidKeyId(yyno))
			sql.append("AND OPLM_REFDOCNO = '"+yyno+"' ");
		sql.append(" UNION   SELECT 'Change in Workpractice / Training / OPL' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,1 as dataorder  FROM DUAL ");
		sql.append(" UNION   SELECT 'Routine Activity (CLTI)' AS CNAME,CLIS_KEYID AS KEYID,'' AS AA,EMPM_NAME AS EMPNAME,TO_CHAR(CLIS_EFFECTIVEDATE) AS CDATE,'' AS BB,3 as dataorder "); 
		sql.append(" FROM CLI_TL_STANDARDS,GEN_TL_EMPLOYEEMST WHERE CLIS_RESPONSIBILITYID = EMPM_KEYID (+) AND CLIS_REFDOCTYPE = 'YY'  ");
//		if(UIUtils.isValidKeyId(yyno))
			sql.append("AND CLIS_REFDOCNO = '"+yyno+"' ");
		sql.append(" UNION   SELECT 'Routine Activity (CLTI)' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,3 as dataorder  FROM DUAL ");
		sql.append(" UNION   SELECT 'Condition Monitoring' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,4 as dataorder  FROM DUAL ");
		sql.append(" UNION   SELECT 'Condition Monitoring' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,4 as dataorder  FROM DUAL  ");
		sql.append(" UNION   SELECT 'Preventive Maintenance',PMSD_KEYID,'',EMPM_NAME,TO_CHAR(PMSD_EFFECTIVEDATE),'', 5 as dataorder ");
		sql.append(" FROM PLM_TL_STANDARDS,GEN_TL_EMPLOYEEMST WHERE PMSD_PREPAREDBYID = EMPM_KEYID (+) AND PMSD_REFDOCTYPE = 'YY' ");
		//if(UIUtils.isValidKeyId(yyno))
			sql.append("AND PMSD_REFDOCNO = '"+yyno+"' ");
		sql.append(" UNION   SELECT 'Preventive Maintenance' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,5 as dataorder  FROM DUAL ");
		sql.append(" UNION   SELECT 'Preventive Maintenance' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,5 as dataorder  FROM DUAL ");
		sql.append(" UNION   SELECT 'Modification (Kaizen)' AS CNAME,KZBN_KEYID AS KEYID,'' AS AA,EMPM_NAME AS EMPNAME,TO_CHAR(KZBN_DATE) AS CDATE,'' AS BB,6 as dataorder "); 
		sql.append(" FROM KZN_TL_KAIZENBANKMST,GEN_TL_EMPLOYEEMST WHERE KZBN_SUGGESTEDBY = EMPM_KEYID (+) AND KZBN_REFDOCTYPE = 'YY' ");
		//if(UIUtils.isValidKeyId(yyno))
			sql.append("AND KZBN_REFDOCNO = '"+yyno+"' ");
		sql.append(" UNION   SELECT 'Modification (Kaizen)' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,6 as dataorder  FROM DUAL ");
		//sql.append(" UNION   SELECT 'SOP','','','','','',8 as dataorder  FROM DUAL ) ");
		sql.append(" ) ");
		*/
		
			sql.append(" SELECT 'Change in Workpractice / Training / OPL' AS CNAME,OPLM_KEYID AS KEYID,'' AS AA,EMPM_NAME AS EMPNAME,TO_CHAR(OPLM_PREPAREDDATE) AS CDATE, ");
			sql.append(" yyed_effectiveid AS BB,OPLM_THEME AS THEME,1 as dataorder  ");
			sql.append(" FROM OPL_TL_MST,GEN_TL_EMPLOYEEMST ,BDM_TL_YYEFFECTIVEMST,BDM_TL_YYEFFECTIVEDTL ");
			sql.append(" WHERE OPLM_PREPAREDID = EMPM_KEYID  AND OPLM_REFDOCTYPE = 'YY'  AND OPLM_REFDOCNO = '"+yyno+"'");  
			sql.append(" and yyef_keyid(+) = yyed_yyef_keyid and OPLM_keyid = yyed_countermesid(+) ");
			sql.append(" UNION   ");
			sql.append(" SELECT 'Change in Workpractice / Training / OPL' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,'' AS THEME,1 as dataorder  FROM DUAL ");  
			sql.append(" UNION   ");
			sql.append(" SELECT 'Routine Activity (CLTI)' AS CNAME,CLIS_KEYID AS KEYID,'' AS AA,EMPM_NAME AS EMPNAME,TO_CHAR(CLIS_EFFECTIVEDATE) AS CDATE,yyed_effectiveid AS BB,'' AS THEME, ");
			sql.append(" 3 as dataorder  FROM CLI_TL_STANDARDS,GEN_TL_EMPLOYEEMST ,(select yyef_keyid,yyed_yyef_keyid,yyed_countermesid,yyed_effectiveid  from ");
			sql.append(" bdm_tl_yyeffectivemst,bdm_tl_yyeffectivedtl where  yyef_keyid = yyed_yyef_keyid) ");
			sql.append(" WHERE CLIS_RESPONSIBILITYID = EMPM_KEYID(+)  AND CLIS_REFDOCTYPE = 'YY'  and CLIS_KEYID = yyed_countermesid(+) ");
			sql.append(" AND CLIS_REFDOCNO = '"+yyno+"'  ");
			sql.append(" UNION   ");
			sql.append(" SELECT 'Routine Activity (CLTI)' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,'' AS THEME,3 as dataorder  FROM DUAL ");  
			sql.append(" UNION   ");
			sql.append(" SELECT 'Condition Monitoring' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,'' AS THEME,4 as dataorder  FROM DUAL ");  
			sql.append(" UNION   ");
			sql.append(" SELECT 'Condition Monitoring' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,'' AS THEME,4 as dataorder  FROM DUAL ");   
			sql.append(" UNION   ");
			sql.append(" SELECT 'Preventive Maintenance',PMSD_KEYID,'',EMPM_NAME,TO_CHAR(PMSD_EFFECTIVEDATE),yyed_effectiveid,'' AS THEME, 5 as dataorder ");  
			sql.append(" FROM PLM_TL_STANDARDS,GEN_TL_EMPLOYEEMST ,(select yyef_keyid,yyed_yyef_keyid,yyed_countermesid,yyed_effectiveid  from ");
			sql.append(" bdm_tl_yyeffectivemst,bdm_tl_yyeffectivedtl where  yyef_keyid = yyed_yyef_keyid) ");
			sql.append(" WHERE PMSD_PREPAREDBYID = EMPM_KEYID(+)  AND PMSD_REFDOCTYPE = 'YY' and PMSD_KEYID = yyed_countermesid(+) ");
			sql.append(" AND PMSD_REFDOCNO = '"+yyno+"'  ");
			sql.append(" UNION   SELECT 'Preventive Maintenance' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,'' AS THEME,5 as dataorder  FROM DUAL ");  
			sql.append(" UNION   SELECT 'Preventive Maintenance' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,'' AS THEME,5 as dataorder  FROM DUAL   ");
			sql.append(" UNION   ");
			sql.append(" SELECT 'Modification (Kaizen)' AS CNAME,KZBN_KEYID AS KEYID,'' AS AA,EMPM_NAME AS EMPNAME,TO_CHAR(KZBN_DATE) AS CDATE,yyed_effectiveid AS BB,KZBN_KAIZEN AS THEME,6 as dataorder ");  
			sql.append(" FROM KZN_TL_KAIZENBANKMST,GEN_TL_EMPLOYEEMST ,KZN_TL_MST,(select yyef_keyid,yyed_yyef_keyid,yyed_countermesid,yyed_effectiveid  from ");
			sql.append(" bdm_tl_yyeffectivemst,bdm_tl_yyeffectivedtl where  yyef_keyid = yyed_yyef_keyid and yyef_wwms_keyid= '"+yyno+"') ");
			sql.append("WHERE KZBN_SUGGESTEDBY = EMPM_KEYID(+)  AND KZBN_REFDOCTYPE = 'YY' AND KZNM_KEYID= yyed_countermesid (+) ");  //and KZBN_KEYID = yyed_countermesid(+)
			sql.append(" AND KZBN_REFDOCNO = '"+yyno+"'   ");
			sql.append(" UNION   SELECT 'Modification (Kaizen)' AS CNAME,'' AS KEYID,'' AS AA,'' AS EMPNAME,'' AS CDATE,'' AS BB,'' AS THEME,6 as dataorder  FROM DUAL ");
			sql.append(" ) ");
		System.out.println("sql....."+sql);
	
		return sql.toString();
	}

	public static String countermeasureCount(BAL_BdmTlWhywhymst bdmTlWhywhymst) {
		
		StringBuffer sql= new StringBuffer();
		CommonFunctions.debugMsg("count....");
		
		sql.append(" select count(KEYID) from(  "+countermeasureInnerQuery(bdmTlWhywhymst.getWwmsKeyid()));
		//sql.append(")");
		return sql.toString();
	}

	public static String selectData(String cellId, String keyid, String formName) {
		// TODO Auto-generated method stub
	
		String sql=null;
		if(UIUtils.isValidKeyId(formName)&& "Opl".equals(formName))
		    sql= " SELECT OPLM_STATUS FROM OPL_TL_MST WHERE OPLM_REFDOCNO='"+keyid+"'";
		else if(UIUtils.isValidKeyId(formName)&& "Kzn".equals(formName))
		    sql= " select KZBN_STATUS FROM KZN_TL_KAIZENBANKMST WHERE KZBN_REFDOCNO='"+keyid+"' ";
		
		else
			sql= " select FNLN_DESCRIPTION FROM gen_mv_flidhierarchy where FNLN_ORIGINALID='"+cellId+"'";
		
		return sql;
	}

	public static String getUpdateKznSql(BAL_BdmTlWhywhymst bdmTlWhywhymst) {
		// TODO Auto-generated method stub
		StringBuffer sql= new StringBuffer();
		CommonFunctions.debugMsg("ref doc no"+bdmTlWhywhymst.getWwmsRefdocno()+"wwmskeyid"+bdmTlWhywhymst.getWwmsKeyid());
		sql.append("UPDATE KZN_TL_MST SET KZNM_WWMS_KEYID ='-' WHERE KZNM_KEYID='"+bdmTlWhywhymst.getWwmsRefdocno()+"' AND KZNM_WWMS_KEYID='"+bdmTlWhywhymst.getWwmsKeyid()+"'");
		return sql.toString();
	}
	/* updated By TEAM*/
	public static String getSelectWhyWhyMstSql()
	{
		return "select *  from BDM_TL_WHYWHYMST  where wwms_keyid =  ? ";
	}

	public static String getYYrefKyid(String refDocId) {
		// TODO Auto-generated method stub
		StringBuilder str=new StringBuilder();
		str.append("select WWMS_KEYID from BDM_TL_WHYWHYMST where WWMS_REFDOCNO ='");
		str.append(refDocId);
		str.append("'");
		return str.toString();
	}

}

