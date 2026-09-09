package com.akranta.tpm.dao.impl;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.bind.ValidationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GridFilter;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GridBasedTrainigCalendarDao;
import com.akranta.tpm.dao.sql.AbnTlAbnormalitySql;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.EntTlTragcalmstSql;
import com.akranta.tpm.dao.sql.EntTlTragcalquadSql;
import com.akranta.tpm.dao.sql.EntTlTrgCalEmpSql;
import com.akranta.tpm.dao.sql.EntTlTrgCalSessionSql;
import com.akranta.tpm.dao.sql.EntTlTrgCalUnqpSql;
import com.akranta.tpm.dao.sql.EntTlTrgFacultySql;
import com.akranta.tpm.dao.sql.EntTlTtgCalEmpatScoreSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMomKpiLinkSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalmst;
import com.akranta.tpm.model.EntTlTragcalquad;
import com.akranta.tpm.model.EntTlTrgCalSession;
import com.akranta.tpm.model.EntTlTrgCalUnqp;
import com.akranta.tpm.model.EntTlTrgFaculty;
import com.akranta.tpm.model.EntTlTtgCalEmpatScore;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class GridBasedTrainigCalendarDaoImpl implements GridBasedTrainigCalendarDao{
	private DBActionTemplate dbActionTemplate;
	private CommonFilterDao commonFilterdao;
	private EntTlTragcalmstSql entTlTragcalmstSql = null;
	private EntTlTrgCalSessionSql entTlTrgCalSessionSql=null;
	private EntTlTrgFacultySql entTlTrgFacultySql=null;
	private EntTlTrgCalEmpSql entTlTrgCalEmpSql=null;
	private EntTlTrgCalUnqpSql entTlTrgCalUnqpSql=null;
	
	
	
	public GridBasedTrainigCalendarDaoImpl(DBActionTemplate dbActionTemplate) {
		// TODO Auto-generated constructor stub
		this.dbActionTemplate=dbActionTemplate;
		entTlTragcalmstSql=new EntTlTragcalmstSql();
		entTlTrgCalSessionSql=new EntTlTrgCalSessionSql();
		entTlTrgFacultySql=new EntTlTrgFacultySql();
		entTlTrgCalUnqpSql=new EntTlTrgCalUnqpSql();
		entTlTrgCalEmpSql=new EntTlTrgCalEmpSql();
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	@Override
	public List<String[]> getElementId(String loginflid, String loginlevel,
			String loginElementid, String empId) throws Exception {
		StringBuffer sql =new StringBuffer();
		sql.append("SELECT FNLN_ELEMENTID,FNLN_KEYID,ROLE_LEVEL,ROLE_NAME,ROLE_KEYID FROM  GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST  ");
			sql.append(" WHERE FNLN_KEYID = FRT_FNLN_KEYID  and FRT_ROLE_KEYID = ROLE_KEYID  ");
		if(UIUtils.isValidKeyId(loginflid))
				sql.append(" AND FRT_FNLN_KEYID  = '"+ loginflid +"' ");
			
			sql.append(" AND FRT_EMPM_KEYID = '"+ empId +"'  AND ROLE_LEVEL= '"+ loginlevel +"'");
			CommonMessage.debugMsg("Sql iis"+sql);
			Object [] args = {} ;
			List<String []> userDatas = dbActionTemplate.getDataList(sql.toString(),args);
			return userDatas;
	}
	
	// --------- Vignesh ---------------------------------- 01DEC2025 -------------------------------------------------------------------//
	
//	@Override
//	public List<String[]> getListTrgCalendar(CommonFilter commonFilter,
//			GridParams gridParams) throws Exception {
//		try
//		{    
//		CommonMessage.debugMsg("getehsAuditParamterGrid sql..");
//		
//
//		List <String>  paramvalues = new ArrayList<String>();
//		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
//		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
//		String mode=commonFilter.getType();
//	    CommonMessage.debugMsg("value of mode"+commonFilter.getType());
//
//		
//	    if(UIUtils.isValidKeyId(mode)){
//			condParms +="Mode="+mode+";";
//		}
//	    CommonMessage.debugMsg("condParms"+condParms);
//	    CommonMessage.debugMsg("commonParams"+commonParams);
//		paramvalues.add(condParms);
//		paramvalues.add(commonParams);
//		String condSql="  ";
//		
//		if(UIUtils.isValidKeyId(commonFilter.getKey())){
//			condSql+=" AND ETCM_KEYID='"+commonFilter.getKey()+"'";
//		} 
//		else{
//		if(UIUtils.isValidKeyId(commonFilter.getSectionId())){
//		 condSql+= "  AND ETCM_DMT='"+commonFilter.getSectionId()+"'";
//		}
//		
//		if(UIUtils.isValidKeyId(commonFilter.getCellId())){
//			condSql+= "  AND ETCM_JH='"+commonFilter.getCellId()+"'";
//			}
//		if(UIUtils.isValidKeyId(commonFilter.getTrarId())){
//			condSql+= "  AND ETCM_TRAININGFUNCTION='"+commonFilter.getTrarId()+"'";
//			}
//		if(UIUtils.isValidDate(commonFilter.getFromDate())&&UIUtils.isValidDate(commonFilter.getToDate())){
//			 condSql+=" AND ETCM_CALDATE BETWEEN '"+commonFilter.getFromDate()+ "' AND '"+commonFilter.getToDate()+"'";
//		}
//		if(UIUtils.isValidKeyId(commonFilter.getUniquePos())){
//			condSql+=" AND ETCM_KEYID IN ( SELECT  ETCU_ETCM_KEYID FROM ENT_TL_TRGCALUNQP WHERE ETCU_ROLE_KEYID='"+commonFilter.getUniquePos()+"') ";
//		}
//		}
//		
//		CommonMessage.debugMsg("commonFilter.getUniquePos()commonFilter.getUniquePos()"+commonFilter.getUniquePos());
//		
//		List<String[]> getDataList=null;
//		StringBuilder sb=new StringBuilder();
//		
//		sb.append("   SELECT ETCM_KEYID,ETCM_DMT,ETCM_JH,ETCM_FLID,ETCM_LOCATION,ETCM_CREATEDATETIME,ETCM_ANCHOREDBY,ETCM_ANCHOREDBY ANCHOREDBYid,TOPI_NAME, ");
//		sb.append("   ETCM_TOPICID, TCAT_NAME, ETCM_TOPICCATEGORY,DECODE(ETCM_FUNCTION,'NB','Need Basic','SD','Section D','SI','Skill Index','KU','Knowledge Upgradation','KSA','KSA (GAP Based)','RT','Refresher Training','EHS','EHS' )AS IDENFIEDTHG,");
//		sb.append("   ETCM_FUNCTION,TRDM_NAME,ETCM_TRAININGFUNCTION,VENU_NAME ,ETCM_VENUE,DECODE(ETCM_GENERAL,'Y','General',DECODE(ETCM_UNIQUEPOS,'Y','Unique Position',DECODE(ETCM_MSD,'Y','MSD',''))) AS UNIQPOSE,");
//		sb.append("   DECODE(ETCM_GENERAL,'Y','GN',DECODE(ETCM_UNIQUEPOS,'Y','UQ',DECODE(ETCM_MSD,'Y','MS','')))  AS UNIQPOSEID,");
//		//sb.append("   (SELECT LISTAGG (ROLE_NAME, ',')  WITHIN GROUP (ORDER BY ROLE_NAME) FROM GEN_TL_ROLEMST,ENT_TL_TRGCALUNQP WHERE  ETCU_ROLE_KEYID=ROLE_KEYID(+)  AND ETCU_ETCM_KEYID=ETCM_KEYID ) ROLE_NAME  ,");
//
//		sb.append("   ETCM_CALDATE,ETCM_PERMITTEDSTRENGTH,ETCM_MAX_DURATION,DECODE(ETCM_ASSESSMENTREQUIRED,'Y','YES','NO'),");
//		sb.append("   ETCM_ASSESSMENTREQUIRED, DECODE(ETCM_MATERIALREADY,'Y','YES','NO'),ETCM_MATERIALREADY, DECODE(ETCM_MARKBASED,'Y','YES','NO'), ETCM_MARKBASED, (SELECT LISTAGG (ROLE_NAME, ',')  WITHIN GROUP (ORDER BY ROLE_NAME) FROM GEN_TL_ROLEMST,ENT_TL_TRGCALUNQP WHERE  ETCU_ROLE_KEYID=ROLE_KEYID(+)  AND ETCU_ETCM_KEYID=ETCM_KEYID ) ROLE_NAME  , '' AS UNIQPOS, '' AS EMPLOYEEADD,'' AS EMPATTEDNCE,");
//		sb.append("   (SELECT LISTAGG (EMPM_NAME, ',')  WITHIN GROUP (ORDER BY EMPM_NAME) FROM GEN_TL_EMPLOYEEMST,ENT_TL_TRGCALEMP WHERE  ");
//		sb.append("   ETCE_EMPM_KEYID=EMPM_KEYID AND ETCE_ETCM_KEYID=ETCM_KEYID ) PLANNEDEMPM_NAME, ");
//		sb.append("	  DECODE(ETCM_TEMPFIELD6,'Y','YES','NO')     AS ASSEMNTCOMPL, ");   
//		sb.append("   ETCM_TEMPFIELD6, DECODE(ETCM_CHKCOMPLETED,'Y','YES','NO')  AS TRNCOMPL,ETCM_CHKCOMPLETED AS TRNCOMPLID,DECODE(ETCM_CHKCOMPLETED,'Y',ETCM_COMPLETEDDATE,'') AS COMPLTDATE, EMPM_NAME ||'-' ||EMPM_CODE  AS COMPLETEDBY , ");
//		sb.append("   ETCM_COMPLETEDBY,ETCM_RATING,ETCM_RATING as RatingId,ETCM_COMMENTS,'' AS FILEMGR, PLANED PLANEDEMP, ATTEND ATTNDEMPL, ROUND(ATTEND/DECODE(PLANED,0,1,PLANED)*100,2) AS ADHERENCE,NVL(ATTEND*ETCM_MAX_DURATION,0) AS MANHOURSE");//ROUND(COUNT(ETCA_ETCM_KEYID)/DECODE(COUNT(ETCE_ETCM_KEYID),0,1)*100) ADHERENCE ");
//		sb.append("   FROM ENT_TL_TRGCALMST, ENT_TL_TOPICMST,ENT_TL_VENUEMST,ENT_TL_TOPICCATEGORYMST, GEN_TL_TRADEMST,ENT_TL_TRGCALEMP,ENT_TL_TRGCALEMPATSCORE, ( select COUNT(ETCE_ETCM_KEYID) AS PLANED from ENT_TL_TRGCALEMP where ETCE_ETCM_KEYID='"+commonFilter.getKey()+"'), (SELECT COUNT(ETCA_ETCM_KEYID) ATTEND FROM  ENT_TL_TRGCALEMPATSCORE WHERE ETCA_ETCM_KEYID='"+commonFilter.getKey()+"' AND ETCA_PRSENTABSENT='P')");
//		sb.append("   ,GEN_TL_EMPLOYEEMST WHERE TOPI_KEYID(+)  =ETCM_TOPICID AND TCAT_KEYID (+)=ETCM_TOPICCATEGORY ");
//		sb.append("   AND TRDM_KEYID(+)=ETCM_TRAININGFUNCTION ");
//		sb.append("   AND EMPM_KEYID(+)=ETCM_COMPLETEDBY ");
//		sb.append("   AND VENU_KEYID(+)=ETCM_VENUE ");
//		sb.append("   AND ETCE_ETCM_KEYID(+) = ETCM_KEYID ");
//		sb.append("   AND ETCA_ETCM_KEYID(+)= ETCM_KEYID " );
//		sb.append("   AND ETCM_KEYID= '"+commonFilter.getKey()+"'" );
//		sb.append("   GROUP BY ETCA_ETCM_KEYID,ETCE_ETCM_KEYID,ETCM_KEYID,  ETCM_DMT,  ETCM_JH,  ETCM_FLID,  ETCM_CREATEDATETIME,  ETCM_ANCHOREDBY, ");
//		sb.append("   ETCM_ANCHOREDBY ,  TOPI_NAME,  ETCM_TOPICID,  TCAT_NAME,  ETCM_TOPICCATEGORY,  ETCM_FUNCTION,  TRDM_NAME,  ETCM_TRAININGFUNCTION, ");
//		sb.append("   VENU_NAME ,  ETCM_VENUE,  ETCM_GENERAL,    ETCM_CALDATE,  ETCM_PERMITTEDSTRENGTH,  ETCM_MAX_DURATION,  ");
//		sb.append("   ETCM_ASSESSMENTREQUIRED, ETCM_MATERIALREADY,  ETCM_MARKBASED, ETCM_TEMPFIELD6, ETCM_CHKCOMPLETED , ETCM_CHKCOMPLETED, ");
//		sb.append("   ETCM_COMPLETEDDATE, EMPM_NAME ,EMPM_CODE, ETCM_COMPLETEDBY,ETCM_RATING,ETCM_COMMENTS,ETCE_ETCM_KEYID, ETCA_ETCM_KEYID,ETCM_UNIQUEPOS,ETCM_MSD,ETCM_LOCATION,PLANED,ATTEND ORDER BY ETCM_KEYID DESC");
//
//		/* Commented on 23May2023
//		 * sb.append("   SELECT ETCM_KEYID,ETCM_DMT,ETCM_JH,ETCM_FLID,ETCM_LOCATION,ETCM_CREATEDATETIME,ETCM_ANCHOREDBY,ETCM_ANCHOREDBY ANCHOREDBYid,TOPI_NAME, ");
//		sb.append("   ETCM_TOPICID, TCAT_NAME, ETCM_TOPICCATEGORY,DECODE(ETCM_FUNCTION,'NB','Need Basic','SD','Section D','SI','Skill Index','KU','Knowledge Upgradation','KSA','KSA (GAP Based)','RT','Refresher Training','EHS','EHS' )AS IDENFIEDTHG,");
//		sb.append("   ETCM_FUNCTION,TRDM_NAME,ETCM_TRAININGFUNCTION,VENU_NAME ,ETCM_VENUE,DECODE(ETCM_GENERAL,'Y','General',DECODE(ETCM_UNIQUEPOS,'Y','Unique Position',DECODE(ETCM_MSD,'Y','MSD',''))) AS UNIQPOSE,");
//		sb.append("   DECODE(ETCM_GENERAL,'Y','GN',DECODE(ETCM_UNIQUEPOS,'Y','UQ',DECODE(ETCM_MSD,'Y','MS','')))  AS UNIQPOSEID,");
//		sb.append("   ETCM_CALDATE,ETCM_PERMITTEDSTRENGTH,ETCM_MAX_DURATION,'' AS UNIQUEPOS,'' AS UNIQiD,'' AS MULIPLEUNIQPOS,'' SESSIONNAME,'' SESSIONID,'' FACULTY,'' AS FACULTYID,'' MULTIPLEFACULTY,DECODE(ETCM_ASSESSMENTREQUIRED,'Y','YES','NO'),");
//		sb.append("   ETCM_ASSESSMENTREQUIRED, DECODE(ETCM_MATERIALREADY,'Y','YES','NO'),ETCM_MATERIALREADY, DECODE(ETCM_MARKBASED,'Y','YES','NO'), ETCM_MARKBASED,  '' AS UNIQPOS, '' AS EMPLOYEEADD,'' AS EMPATTEDNCE, DECODE(ETCM_TEMPFIELD6,'Y','YES','NO')     AS ASSEMNTCOMPL, ");   
//		sb.append("   ETCM_TEMPFIELD6, DECODE(ETCM_CHKCOMPLETED,'Y','YES','NO')  AS TRNCOMPL,ETCM_CHKCOMPLETED AS TRNCOMPLID,DECODE(ETCM_CHKCOMPLETED,'Y',ETCM_COMPLETEDDATE,'') AS COMPLTDATE, EMPM_NAME ||'-' ||EMPM_CODE  AS COMPLETEDBY , ");
//		sb.append("   ETCM_COMPLETEDBY,ETCM_RATING,ETCM_RATING as RatingId,ETCM_COMMENTS,'' AS FILEMGR, PLANED PLANEDEMP, ATTEND ATTNDEMPL, ROUND(ATTEND/DECODE(PLANED,0,1,PLANED)*100,2) AS ADHERENCE");//ROUND(COUNT(ETCA_ETCM_KEYID)/DECODE(COUNT(ETCE_ETCM_KEYID),0,1)*100) ADHERENCE ");
//		sb.append("   FROM ENT_TL_TRGCALMST, ENT_TL_TOPICMST,ENT_TL_VENUEMST,ENT_TL_TOPICCATEGORYMST, GEN_TL_TRADEMST,ENT_TL_TRGCALEMP,ENT_TL_TRGCALEMPATSCORE, ( select COUNT(ETCE_ETCM_KEYID) AS PLANED from ENT_TL_TRGCALEMP where ETCE_ETCM_KEYID='"+commonFilter.getKey()+"'), (SELECT COUNT(ETCA_ETCM_KEYID) ATTEND FROM  ENT_TL_TRGCALEMPATSCORE WHERE ETCA_ETCM_KEYID='"+commonFilter.getKey()+"')");
//		sb.append("   ,GEN_TL_EMPLOYEEMST WHERE TOPI_KEYID(+)  =ETCM_TOPICID AND TCAT_KEYID (+)=ETCM_TOPICCATEGORY ");
//		sb.append("   AND TRDM_KEYID(+)=ETCM_TRAININGFUNCTION ");
//		sb.append("   AND EMPM_KEYID(+)=ETCM_COMPLETEDBY ");
//		sb.append("   AND VENU_KEYID(+)=ETCM_VENUE ");
//		sb.append("   AND ETCE_ETCM_KEYID(+) = ETCM_KEYID ");
//		sb.append("   AND ETCA_ETCM_KEYID(+)= ETCM_KEYID " );
//		sb.append("   AND ETCM_KEYID= '"+commonFilter.getKey()+"'" );
//		sb.append("   GROUP BY ETCA_ETCM_KEYID,ETCE_ETCM_KEYID,ETCM_KEYID,  ETCM_DMT,  ETCM_JH,  ETCM_FLID,  ETCM_CREATEDATETIME,  ETCM_ANCHOREDBY, ");
//		sb.append("   ETCM_ANCHOREDBY ,  TOPI_NAME,  ETCM_TOPICID,  TCAT_NAME,  ETCM_TOPICCATEGORY,  ETCM_FUNCTION,  TRDM_NAME,  ETCM_TRAININGFUNCTION, ");
//		sb.append("   VENU_NAME ,  ETCM_VENUE,  ETCM_GENERAL,    ETCM_CALDATE,  ETCM_PERMITTEDSTRENGTH,  ETCM_MAX_DURATION,  ");
//		sb.append("   ETCM_ASSESSMENTREQUIRED, ETCM_MATERIALREADY,  ETCM_MARKBASED, ETCM_TEMPFIELD6, ETCM_CHKCOMPLETED , ETCM_CHKCOMPLETED, ");
//		sb.append("   ETCM_COMPLETEDDATE, EMPM_NAME ,EMPM_CODE, ETCM_COMPLETEDBY,ETCM_RATING,ETCM_COMMENTS,ETCE_ETCM_KEYID, ETCA_ETCM_KEYID,ETCM_UNIQUEPOS,ETCM_MSD,ETCM_LOCATION,PLANED,ATTEND ORDER BY ETCM_KEYID DESC");
//*/
//		
//		
//		/*sb.append(" SELECT ETCM_KEYID, ETCM_DMT, ETCM_JH, ETCM_FLID, ETCM_CREATEDATETIME, ETCM_ANCHOREDBY, ETCM_ANCHOREDBY ANCHOREDBYid,TOPI_NAME, ETCM_TOPICID, TCAT_NAME, ETCM_TOPICCATEGORY, ");
//		sb.append(" DECODE(ETCM_FUNCTION,'NB','Need Basic','SD','Section D','SI','Skill Index','KU','Knowledge Upgradation','KSA','KSA (GAP Based)','RT','Refresher Training','EHS','EHS' )AS IDENFIEDTHG, ");
//		sb.append(" ETCM_FUNCTION, TRDM_NAME,ETCM_TRAININGFUNCTION, VENU_NAME  ,ETCM_VENUE, DECODE(ETCM_GENERAL,'Y','General',DECODE(ETCM_UNIQUEPOS,'Y','Unique Position',DECODE(ETCM_MSD,'Y','MSD',''))) AS UNIQPOSE, ");
//		sb.append(" DECODE(ETCM_GENERAL,'Y','GN',DECODE(ETCM_UNIQUEPOS,'Y','UQ',DECODE(ETCM_MSD,'Y','MS',''))) AS UNIQPOSEID, ETCM_CALDATE, ETCM_PERMITTEDSTRENGTH, ETCM_MAX_DURATION, DECODE(ETCM_ASSESSMENTREQUIRED,'Y','YES','NO'), ");
//		sb.append("	ETCM_ASSESSMENTREQUIRED, DECODE(ETCM_MATERIALREADY,'Y','YES','NO'), ETCM_MATERIALREADY, DECODE(ETCM_MARKBASED,'Y','YES','NO'),  ETCM_MARKBASED, '' AS UNIQPOS,  ''  AS EMPLOYEEADD, ETCM_CHKCOMPLETED  AS TRNCOMPL, ");
//		sb.append("	DECODE(ETCM_CHKCOMPLETED,'Y',ETCM_COMPLETEDDATE,'') AS COMPLTDATE, EMPM_NAME ||'-' ||EMPM_CODE, ETCM_COMPLETEDBY, '' AS EMPATTEDNCE, ETCM_RATING, ETCM_COMMENTS, '' AS FILEMGR, ");
//		sb.append("	'' PLANEDEMP, '' ATTNDEMPL, '' ADHERENCE  ");
//		sb.append("	 FROM ENT_TL_TRGCALMST,  ENT_TL_TOPICMST,  ENT_TL_VENUEMST,  ENT_TL_TOPICCATEGORYMST, GEN_TL_TRADEMST, GEN_TL_EMPLOYEEMST ");
//		sb.append(" WHERE TOPI_KEYID(+) =ETCM_TOPICID AND TCAT_KEYID (+)  =ETCM_TOPICCATEGORY AND TRDM_KEYID(+)=ETCM_TRAININGFUNCTION ");
//		sb.append(" AND EMPM_KEYID(+)=ETCM_COMPLETEDBY AND VENU_KEYID(+)=ETCM_VENUE "+condSql);
//		
//		*/
//		/*
//		sb.append(" SELECT ETCM_KEYID,ETCM_DMT,ETCM_JH,ETCM_FLID,ETCM_CREATEDATETIME,ETCM_ANCHOREDBY,'' ANCHID,TOPI_NAME,ETCM_TOPICID,TCAT_NAME,ETCM_TOPICCATEGORY, ");
//		sb.append(" DECODE(ETCM_FUNCTION,'NB','Need Basic','SD','Section D','SI','Skill Index','KU','Knowledge Upgradation','KSA','KSA (GAP Based)','RT','Refresher Training','EHS','EHS' )AS IDENFIEDTHG, ");
//		
//		sb.append(" TRDM_NAME,ETCM_TRAININGFUNCTION,	ETCM_VENUE,DECODE(ETCM_GENERAL,'Y','GN',DECODE(ETCM_UNIQUEPOS,'Y','UQ',DECODE(ETCM_MSD,'Y','MS',''))) AS UNIQPOSE, ");
//		sb.append(" ETCM_CALDATE,ETCM_PERMITTEDSTRENGTH,ETCM_MAX_DURATION,ETCM_ASSESSMENTREQUIRED,ETCM_MATERIALREADY,ETCM_MARKBASED,'' AS UNIQPOS,'' AS EMPLOYEEADD, ");
//		sb.append(" ETCM_CHKCOMPLETED AS TRNCOMPL,DECODE(ETCM_CHKCOMPLETED,'Y',ETCM_COMPLETEDDATE,'') AS COMPLTDATE,EMPM_NAME||'-'||EMPM_CODE,ETCM_COMPLETEDBY, ");
//		
//		sb.append(" '' AS EMPATTEDNCE, ETCM_RATING,ETCM_COMMENTS,'' AS FILEMGR,'' PLANEDEMP,'' ATTNDEMPL,'' ADHERENCE ");
//        sb.append(" FROM ENT_TL_TRGCALMST,ENT_TL_TOPICMST,ENT_TL_TOPICCATEGORYMST,GEN_TL_TRADEMST,GEN_TL_EMPLOYEEMST ");
//		sb.append(" WHERE TOPI_KEYID=ETCM_TOPICID ");
//		sb.append(" AND TCAT_KEYID=ETCM_TOPICCATEGORY ");
//		sb.append(" AND TRDM_KEYID(+)=ETCM_TRAININGFUNCTION ");
//		sb.append(" AND EMPM_KEYID(+)=ETCM_COMPLETEDBY ");
//		sb.append(condSql);*/
//		
//		CommonMessage.debugMsg(sb+" query in sid the DAO Impl");
//		
//		List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sb.toString(),null);
//		
////		 getDataList= dbActionTemplate.processFunctionCalls("ENT_NEW_REPORTS.ENT_TL_NEWTRAININGCALENTRYGRD", paramvalues); 
//		// getDataList= dbActionTemplate.processFunctionCalls("ENT_NEW_REPORTS.ENT_TL_NEWTRAININGCALMSTGRD", paramvalues); 
//
//		if( commonFilter.getViewClick() == 'Y'){
//		     String totalCnt = paramvalues.get(0);
//		     //CommonMessage.debugMsg("totalCnt...."+totalCnt);
//		     boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
//		    CommonMessage.debugMsg("IS isInteger"+isInteger);
//		     if(  isInteger ){
//		     	
//		     	commonFilter.setTotalRecordCnt((int) Long.parseLong(totalCnt));
//		      }
//		 }
//		return  dataList ;
//	
//	
//}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//			
//		}
	@Override
	public List<String[]> getListTrgCalendar(CommonFilter commonFilter,
	                                               GridParams gridParams) throws Exception {
	    try {
	        CommonMessage.debugMsg("getehsAuditParamterGrid sql..");

	        List<String> paramvalues = new ArrayList<String>();
	        String condParms    = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	        String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	        String mode         = commonFilter.getType();

	        CommonMessage.debugMsg("value of mode" + commonFilter.getType());

	        if (UIUtils.isValidKeyId(mode)) {
	            condParms += "Mode=" + mode + ";";
	        }
	        CommonMessage.debugMsg("condParms" + condParms);
	        CommonMessage.debugMsg("commonParams" + commonParams);
	        paramvalues.add(condParms);
	        paramvalues.add(commonParams);

	        StringBuilder sb = new StringBuilder();

	        sb.append(" SELECT ");
	        sb.append("   ETCM_KEYID,");
	        sb.append("   ETCM_DMT,");
	        sb.append("   ETCM_JH,");
	        sb.append("   ETCM_FLID,");
	        sb.append("   ETCM_LOCATION,");
	        sb.append("   ETCM_CREATEDATETIME,");
	        sb.append("   ETCM_ANCHOREDBY,");
	        sb.append("   ETCM_ANCHOREDBY ANCHOREDBYid,");
	        sb.append("   TOPI_NAME,");
	        sb.append("   ETCM_TOPICID,");
	        sb.append("   TCAT_NAME,");
	        sb.append("   ETCM_TOPICCATEGORY,");

	        // DECODE(ETCM_FUNCTION,...) → CASE
	        sb.append("   CASE ETCM_FUNCTION");
	        sb.append("     WHEN 'NB'  THEN 'Need Basic'");
	        sb.append("     WHEN 'SD'  THEN 'Section D'");
	        sb.append("     WHEN 'SI'  THEN 'Skill Index'");
	        sb.append("     WHEN 'KU'  THEN 'Knowledge Upgradation'");
	        sb.append("     WHEN 'KSA' THEN 'KSA (GAP Based)'");
	        sb.append("     WHEN 'RT'  THEN 'Refresher Training'");
	        sb.append("     WHEN 'EHS' THEN 'EHS'");
	        sb.append("     ELSE NULL");
	        sb.append("   END AS IDENFIEDTHG,");

	        sb.append("   ETCM_FUNCTION,");
	        sb.append("   TRDM_NAME,");
	        sb.append("   ETCM_TRAININGFUNCTION,");
	        sb.append("   VENU_NAME,");
	        sb.append("   ETCM_VENUE,");

	        // DECODE(ETCM_GENERAL,'Y','General',DECODE(...)) → CASE
	        sb.append("   CASE");
	        sb.append("     WHEN ETCM_GENERAL   = 'Y' THEN 'General'");
	        sb.append("     WHEN ETCM_UNIQUEPOS = 'Y' THEN 'Unique Position'");
	        sb.append("     WHEN ETCM_MSD       = 'Y' THEN 'MSD'");
	        sb.append("     ELSE ''");
	        sb.append("   END AS UNIQPOSE,");

	        // DECODE(ETCM_GENERAL,'Y','GN',DECODE(...)) → CASE
	        sb.append("   CASE");
	        sb.append("     WHEN ETCM_GENERAL   = 'Y' THEN 'GN'");
	        sb.append("     WHEN ETCM_UNIQUEPOS = 'Y' THEN 'UQ'");
	        sb.append("     WHEN ETCM_MSD       = 'Y' THEN 'MS'");
	        sb.append("     ELSE ''");
	        sb.append("   END AS UNIQPOSEID,");

	        sb.append("   ETCM_CALDATE,");
	        sb.append("   ETCM_PERMITTEDSTRENGTH,");
	        sb.append("   ETCM_MAX_DURATION,");

	        // DECODE(ETCM_ASSESSMENTREQUIRED,'Y','YES','NO')
	        sb.append("   CASE WHEN ETCM_ASSESSMENTREQUIRED = 'Y' THEN 'YES' ELSE 'NO' END,");
	        sb.append("   ETCM_ASSESSMENTREQUIRED,");

	        // DECODE(ETCM_MATERIALREADY,'Y','YES','NO')
	        sb.append("   CASE WHEN ETCM_MATERIALREADY = 'Y' THEN 'YES' ELSE 'NO' END,");
	        sb.append("   ETCM_MATERIALREADY,");

	        // DECODE(ETCM_MARKBASED,'Y','YES','NO')
	        sb.append("   CASE WHEN ETCM_MARKBASED = 'Y' THEN 'YES' ELSE 'NO' END,");
	        sb.append("   ETCM_MARKBASED,");

	        // LISTAGG → string_agg for ROLE_NAME
	        sb.append("   (");
	        sb.append("     SELECT string_agg(r.ROLE_NAME, ',' ORDER BY r.ROLE_NAME)");
	        sb.append("     FROM ENT_TL_TRGCALUNQP u");
	        sb.append("     LEFT JOIN GEN_TL_ROLEMST r ON u.ETCU_ROLE_KEYID = r.ROLE_KEYID");
	        sb.append("     WHERE u.ETCU_ETCM_KEYID = ETCM_KEYID");
	        sb.append("   ) AS ROLE_NAME,");

	        sb.append("   '' AS UNIQPOS,");
	        sb.append("   '' AS EMPLOYEEADD,");
	        sb.append("   '' AS EMPATTEDNCE,");

	        // LISTAGG → string_agg for PLANNEDEMPM_NAME
	        sb.append("   (");
	        sb.append("     SELECT string_agg(e2.EMPM_NAME, ',' ORDER BY e2.EMPM_NAME)");
	        sb.append("     FROM GEN_TL_EMPLOYEEMST e2");
	        sb.append("     JOIN ENT_TL_TRGCALEMP et2 ON et2.ETCE_EMPM_KEYID = e2.EMPM_KEYID");
	        sb.append("     WHERE et2.ETCE_ETCM_KEYID = ETCM_KEYID");
	        sb.append("   ) AS PLANNEDEMPM_NAME,");

	        // DECODE(ETCM_TEMPFIELD6,'Y','YES','NO')
	        sb.append("   CASE WHEN ETCM_TEMPFIELD6 = 'Y' THEN 'YES' ELSE 'NO' END AS ASSEMNTCOMPL,");
	        sb.append("   ETCM_TEMPFIELD6,");

	        // DECODE(ETCM_CHKCOMPLETED,'Y','YES','NO')
	        sb.append("   CASE WHEN ETCM_CHKCOMPLETED = 'Y' THEN 'YES' ELSE 'NO' END AS TRNCOMPL,");
	        sb.append("   ETCM_CHKCOMPLETED AS TRNCOMPLID,");

	        // DECODE(ETCM_CHKCOMPLETED,'Y',ETCM_COMPLETEDDATE,'')
	        // -> keep as DATE, default NULL
	        sb.append("   CASE WHEN ETCM_CHKCOMPLETED = 'Y' THEN ETCM_COMPLETEDDATE ELSE NULL END AS COMPLTDATE,");

	        sb.append("   EMPM_NAME || '-' || EMPM_CODE AS COMPLETEDBY,");
	        sb.append("   ETCM_COMPLETEDBY,");
	        sb.append("   ETCM_RATING,");
	        sb.append("   ETCM_RATING AS RatingId,");
	        sb.append("   ETCM_COMMENTS,");
	        sb.append("   '' AS FILEMGR,");

	        sb.append("   PLANED AS PLANEDEMP,");
	        sb.append("   ATTEND AS ATTNDEMPL,");

	        // ROUND(ATTEND/DECODE(PLANED,0,1,PLANED)*100,2)
	        sb.append("   ROUND(ATTEND::numeric / (CASE WHEN PLANED = 0 THEN 1 ELSE PLANED END) * 100, 2) AS ADHERENCE,");

	        // NVL(ATTEND*ETCM_MAX_DURATION,0)
	        sb.append("   COALESCE(ATTEND * ETCM_MAX_DURATION, 0) AS MANHOURSE");

	        sb.append(" FROM ENT_TL_TRGCALMST");
	        sb.append(" LEFT JOIN ENT_TL_TOPICMST");
	        sb.append("        ON TOPI_KEYID = ETCM_TOPICID");
	        sb.append(" LEFT JOIN ENT_TL_VENUEMST");
	        sb.append("        ON VENU_KEYID = ETCM_VENUE");
	        sb.append(" LEFT JOIN ENT_TL_TOPICCATEGORYMST");
	        sb.append("        ON TCAT_KEYID = ETCM_TOPICCATEGORY");
	        sb.append(" LEFT JOIN GEN_TL_TRADEMST");
	        sb.append("        ON TRDM_KEYID = ETCM_TRAININGFUNCTION");
	        sb.append(" LEFT JOIN ENT_TL_TRGCALEMP");
	        sb.append("        ON ETCE_ETCM_KEYID = ETCM_KEYID");
	        sb.append(" LEFT JOIN ENT_TL_TRGCALEMPATSCORE");
	        sb.append("        ON ETCA_ETCM_KEYID = ETCM_KEYID");
	        sb.append(" LEFT JOIN GEN_TL_EMPLOYEEMST");
	        sb.append("        ON EMPM_KEYID = ETCM_COMPLETEDBY");

	        // Inline subquery for PLANED – give alias (required in Postgres)
	        sb.append(" CROSS JOIN (");
	        sb.append("   SELECT COUNT(ETCE_ETCM_KEYID) AS PLANED");
	        sb.append("   FROM ENT_TL_TRGCALEMP");
	        sb.append("   WHERE ETCE_ETCM_KEYID = '").append(commonFilter.getKey()).append("'");
	        sb.append(" ) plan_tbl");

	        // Inline subquery for ATTEND – give alias
	        sb.append(" CROSS JOIN (");
	        sb.append("   SELECT COUNT(ETCA_ETCM_KEYID) AS ATTEND");
	        sb.append("   FROM ENT_TL_TRGCALEMPATSCORE");
	        sb.append("   WHERE ETCA_ETCM_KEYID = '").append(commonFilter.getKey()).append("'");
	        sb.append("     AND ETCA_PRSENTABSENT = 'P'");
	        sb.append(" ) attend_tbl");

	        sb.append(" WHERE ETCM_KEYID = '").append(commonFilter.getKey()).append("'");

	        sb.append(" GROUP BY ");
	        sb.append("   ETCA_ETCM_KEYID,");
	        sb.append("   ETCE_ETCM_KEYID,");
	        sb.append("   ETCM_KEYID,");
	        sb.append("   ETCM_DMT,");
	        sb.append("   ETCM_JH,");
	        sb.append("   ETCM_FLID,");
	        sb.append("   ETCM_CREATEDATETIME,");
	        sb.append("   ETCM_ANCHOREDBY,");
	        sb.append("   ETCM_ANCHOREDBY,");
	        sb.append("   TOPI_NAME,");
	        sb.append("   ETCM_TOPICID,");
	        sb.append("   TCAT_NAME,");
	        sb.append("   ETCM_TOPICCATEGORY,");
	        sb.append("   ETCM_FUNCTION,");
	        sb.append("   TRDM_NAME,");
	        sb.append("   ETCM_TRAININGFUNCTION,");
	        sb.append("   VENU_NAME,");
	        sb.append("   ETCM_VENUE,");
	        sb.append("   ETCM_GENERAL,");
	        sb.append("   ETCM_CALDATE,");
	        sb.append("   ETCM_PERMITTEDSTRENGTH,");
	        sb.append("   ETCM_MAX_DURATION,");
	        sb.append("   ETCM_ASSESSMENTREQUIRED,");
	        sb.append("   ETCM_MATERIALREADY,");
	        sb.append("   ETCM_MARKBASED,");
	        sb.append("   ETCM_TEMPFIELD6,");
	        sb.append("   ETCM_CHKCOMPLETED,");
	        sb.append("   ETCM_COMPLETEDDATE,");
	        sb.append("   EMPM_NAME,");
	        sb.append("   EMPM_CODE,");
	        sb.append("   ETCM_COMPLETEDBY,");
	        sb.append("   ETCM_RATING,");
	        sb.append("   ETCM_COMMENTS,");
	        sb.append("   ETCM_UNIQUEPOS,");
	        sb.append("   ETCM_MSD,");
	        sb.append("   ETCM_LOCATION,");
	        sb.append("   PLANED,");
	        sb.append("   ATTEND");

	        sb.append(" ORDER BY ETCM_KEYID DESC");

	        CommonMessage.debugMsg(sb + " query in sid the DAO Impl");

	        List<String[]> dataList =
	                dbActionTemplate.getDataListWithColHeader(sb.toString(), null);

	        // Keeping your existing (Oracle-era) viewClick logic as-is
	        if (commonFilter.getViewClick() == 'Y') {
	            String totalCnt = paramvalues.get(0);
	            CommonMessage.debugMsg("totalCnt...." + totalCnt);
	            boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	            CommonMessage.debugMsg("IS isInteger" + isInteger);
	            if (isInteger) {
	                commonFilter.setTotalRecordCnt((int) Long.parseLong(totalCnt));
	            }
	        }

	        return dataList;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	// --------- Vignesh ---------------------------------- 02DEC2025 -------------------------------------------------------------------//
	
	@Override
	/*public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		String functionName = "RPT_PC_CARD.RPT_FN_ETTRNCALENDAR";//commonFilter.getFunctionName();
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			
			String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			condParms+=";MODE="+commonFilter.getType()+";";
			CommonMessage.debugMsg( " FromModeDaoImpl  "+commonFilter.getType());
			paramValues.add(condParms);
			paramValues.add(commonParams);
						
			CommonMessage.debugMsg("ParamValues:"+paramValues);	
			List<String[]> dataList =   dbActionTemplate.processFunctionCalls(functionName, paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg(dataList);
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	
	}*/
	
	public List<String[]> getsession(String trgcalKeyid) throws Exception {
		// TODO Auto-generated method stub
		//public List<String[]> getsession(String TrainingKeyid) throws Exception {
			// TODO Auto-generated method stub
		CommonMessage.debugMsg(trgcalKeyid);
			StringBuilder sql=new StringBuilder();
			sql.append(" select ETCS_KEYID,ETCS_NAME, TO_CHAR(ETCS_SESSIONDATE,'DD-Mon-YYYY') as   \"Session\",");
	        sql.append(" TO_CHAR(ETCS_FROMDATE,'HH24:MI') as   \"From Time\", ");
	        sql.append(" TO_CHAR(ETCS_TILLDATE,'HH24:MI') as   \"To Time\", ");
	        sql.append(" '' as \"Delete\" ");
	        sql.append(" FROM ENT_TL_TRGCALSESSION, ENT_TL_TRGCALMST WHERE 1=1 AND ETCS_ETCM_KEYID='"+trgcalKeyid+"' ");
	        sql.append("  AND ETCS_ETCM_KEYID = ETCM_KEYID  ORDER BY ETCS_KEYID,ETCS_NAME ");
			CommonMessage.debugMsg("The Session Data::: inside grid based"+sql);
			List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql.toString(),null);
			return dataList ;
		}
	// -------------------------Vignesh 26Nov2025 ---------------------------------------------//
	
	@Override
	public List<String[]> getNewUniqPosData(String trainingKeyid) throws Exception {
	    StringBuffer sql = new StringBuffer();

	    sql.append(" select ");
	    sql.append("   Q.ETCU_KEYID,");
	    sql.append("   Q.ETCU_ROLE_KEYID,");
	    sql.append("   R.ROLE_NAME || ' - ' || R.FNLN_DISPLAYCODE as \"Unique Position\",");
	    sql.append("   S.SECT_NAME as \"DMT\",");
	    sql.append("   C.CELL_NAME as \"JH\",");
	    sql.append("   '' as \"Delete\" ");

	    sql.append(" from ENT_TL_TRGCALUNQP Q ");
	    sql.append(" join ENT_VW_ROLEMST R ");
	    sql.append("      on Q.ETCU_ROLE_KEYID = R.ROLE_KEYID ");

	    // Oracle: AND ETCU_ROLEDMT = SECT_KEYID(+)  ->  Q LEFT JOIN SECTION
	    sql.append(" left join GEN_TL_SECTIONMST S ");
	    sql.append("      on Q.ETCU_ROLEDMT = S.SECT_KEYID ");

	    // Oracle: AND ETCU_ROLEJH = CELL_KEYID(+)  ->  Q LEFT JOIN CELL
	    sql.append(" left join GEN_TL_CELLMST C ");
	    sql.append("      on Q.ETCU_ROLEJH = C.CELL_KEYID ");

	    sql.append(" where Q.ETCU_ETCM_KEYID = '").append(trainingKeyid).append("' ");

	    CommonMessage.debugMsg("The UniquePosition Data" + sql.toString());
	    List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
	    return dataList;
	}

	
//	@Override
//	public List<String[]> getNewUniqPosData(String trainingKeyid)
//			throws Exception {
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select ETCU_KEYID,ETCU_ROLE_KEYID,ROLE_NAME || ' - ' || FNLN_DISPLAYCODE ");
//		sql.append(" as \"Unique Position\",SECT_NAME as \"DMT\", ");
//		sql.append(" CELL_NAME as \"JH\" ,'' as \"Delete\" ");
//		sql.append(" from ENT_TL_TRGCALUNQP, ENT_VW_ROLEMST, ");
//		sql.append(" GEN_TL_SECTIONMST,GEN_TL_CELLMST ");
//		sql.append(" where ETCU_ETCM_KEYID ='"+trainingKeyid+"'  ");
//		sql.append(" and ETCU_ROLE_KEYID = ROLE_KEYID ");
//		sql.append(" AND ETCU_ROLEDMT=SECT_KEYID(+) ");
//		sql.append(" AND ETCU_ROLEJH=CELL_KEYID(+) ");
//		CommonMessage.debugMsg("The UniquePosition Data"+sql.toString());
//		List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql.toString(),null);
//		return dataList ;
//	}
	
	// -------------------------Vignesh 26Nov2025 ---------------------------------------------//
	public List<String[]> gwtJHRoleUniquePos(CommonFilter commonFilter) throws Exception{
	    String Calendarflid=commonFilter.getFlid();
	    CommonMessage.debugMsg("The Calendarflid"+Calendarflid);
	    String CalendarId=commonFilter.getKey();
	    /*List <String[]> grid = 
	    		
	    		commonFilter.getGridFilter() ;*/
	    String CondSql=" AND ";
	    if (UIUtils.isValidKeyId(commonFilter.getCellId())){
	    	CondSql+= " INSTR( PARENTFLIDS||FLID ,(SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID='"+commonFilter.getCellId()+"'))>0 ";
	    }
	    else if(UIUtils.isValidKeyId(commonFilter.getSectionId())){
	    	CondSql+= " INSTR( PARENTFLIDS||FLID ,(SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID='"+commonFilter.getSectionId()+"'))>0 ";
	    
	    }
	    else{
	    	CondSql+= " INSTR( PARENTFLIDS||FLID ,'"+Calendarflid+"')>0 ";
		    
	    }
	    GridFilter gridFilter = new GridFilter();
	    //String codSql=FilterCOncommonFilter.getGridFilter();//gridFilter.getData();// getData();
	    
		String sql=null;
		try{		
			// --------------------- Vignesh 27Nov2025 -----------------------------------------------//
		//	sql="SELECT '',ETCU_KEYID,ROLE_KEYID,ROLE_NAME||'-' || FNLN_DISPLAYCODE AS ROLE_NAME ,sect_keyid,cell_keyid,SECT_NAME AS SECTIONNAME,CELL_NAME As CELLNAME ";
		//	sql+=" FROM GEN_TL_ROLEMST,GEN_MV_FLIDHIERARCHY,ENT_TL_TRGCALUNQP,GEN_VW_FNLN WHERE ROLE_FLID=FLID AND ROLE_KEYID=ETCU_ROLE_KEYID(+) and  fnln_keyid=flid ";
		//	sql+=" AND ETCU_ETCM_KEYID(+)='"+CalendarId+"'"+CondSql+"";
			sql  = "SELECT '',"
				     + "       ETCU_KEYID,"
				     + "       ROLE_KEYID,"
				     + "       ROLE_NAME || '-' || FNLN_DISPLAYCODE uniqposition,"
				     + "       sect_keyid,"
				     + "       cell_keyid ";
				sql += "  FROM GEN_TL_ROLEMST "
				     + "  JOIN GEN_MV_FLIDHIERARCHY "
				     + "    ON ROLE_FLID = FLID "
				     + "  JOIN GEN_VW_FNLN "
				     + "    ON fnln_keyid = flid "
				     + "  LEFT JOIN ENT_TL_TRGCALUNQP "
				     + "    ON ROLE_KEYID = ETCU_ROLE_KEYID "
				     + "   AND ETCU_ETCM_KEYID = '" + CalendarId + "' ";
				sql += " WHERE POSITION('" + Calendarflid + "' IN (PARENTFLIDS || FLID)) > 0 ";

				// --------------------- Vignesh 27Nov2025 -----------------------------------------------//
			String codSql=FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter());
			CommonMessage.debugMsg("sqls of codSql"+codSql);
			if(codSql!=null||codSql!=" "){
				sql = "SELECT * FROM ("+sql+") WHERE 1=1 "+codSql+"";
				CommonMessage.debugMsg(" Query inside the DAOimpl intrg CAlendar "+sql);
			}
			sql+=" ORDER BY ETCU_KEYID";
			CommonMessage.debugMsg("sqls of doc"+sql);
			
		}catch(Exception e){
				e.printStackTrace();
			}
		
			return dbActionTemplate.getDataList(sql);	
	}

	
	@Override
	public List<EntTlTtgCalEmpatScore> employeeAttendance(
			List<EntTlTtgCalEmpatScore> fillValuesEmployeeAttendance,
			CommonFilter commonfilter) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public EntTlTragcalmst create(EntTlTragcalmst newentTlTragcalmst)throws Exception,BusinessApplicationExceptions, ValidationException{
			/*throws Exception {
		public EntTlTragcalmst create(EntTlTragcalmst newentTlTragcalmst) 	throws Exception,BusinessApplicationExceptions, ValidationException {
		*/	List<String> sqls = new ArrayList<String>();		
			EntTlTragcalmstSql entTlTragcalmstSql = new EntTlTragcalmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
			EntTlTrgCalSessionSql entTlTrgCalSessionSql=new EntTlTrgCalSessionSql(); 
			EntTlTrgCalUnqpSql entTlTrgCalUnqpSql=new EntTlTrgCalUnqpSql();            
		//	EntTlTrgCalUnqpSql entTlTrgCalUnqpSql=new EntTlTrgCalUnqpSql();            


	          //if(newentTlTragcalmst.getFaculty() != null)
		   //   if(newentTlTragcalmst.getsessionMaster()!= null)
			
			    newentTlTragcalmst.setEtcmKeyid(dbActionTemplate.getSequenceNumber(EntTlTragcalmstSql.TBL_ENT_TL_TRGCALMST,15,"ETC","",""));
				sqls.add(EntTlTragcalmstSql.getInsertSql(entTlTragcalmstSql.getFtymDbFields(),newentTlTragcalmst.getSaveArray())); // add insert sql for master table
				//dbActionTemplate.executeStatements(sqls); // execute the block of sqls

			/*&	!UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getBachKeyid())
				 && !Constants.futureNullDate.equals(newEntTlProgrammst.getBatchmaster().getBachTilldate())
				 && UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getBachTilldate()) 
				 EtcsTillDate*/
				       if(newentTlTragcalmst.getsessionMaster() != null && 
						 !UIUtils.isValidKeyId(newentTlTragcalmst.getsessionMaster().getEtcsKeyid())
						 && !Constants.futureNullDate.equals(newentTlTragcalmst.getsessionMaster().getEtcsTillDate())
						 && UIUtils.isValidKeyId(newentTlTragcalmst.getsessionMaster().getEtcsTillDate()) 
						 ){
				  if(newentTlTragcalmst.getsessionMaster() != null ){
					//  String count=dbActionTemplate.getSingleValue("ENT_TL_TRGCALSESSION", "COUNT(*)", "ETCS_ETCM_KEYID", newentTlTragcalmst.getsessionMaster().getEtcsEtcmKeyid());
					//  int cnt=Integer.parseInt(count)+1;
					  String sessionname="Session1";
					  //String sessionName = getSessionName(newentTlTragcalmst.getsessionMaster());
					  newentTlTragcalmst.getsessionMaster().setEtcsName(sessionname);	  
				      newentTlTragcalmst.getsessionMaster().setEtcsKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgCalSessionSql.TBL_ENT_TL_TRGCALSESSION,15,"ETS","","" ));	 
				      newentTlTragcalmst.getsessionMaster().setEtcsEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());	 
				      newentTlTragcalmst.getsessionMaster().setEtcsEtcmFlid(newentTlTragcalmst.getEtcmFlid());
				      sqls.add(EntTlTrgCalSessionSql.getInsertSql(entTlTrgCalSessionSql.getFtymDbFields(),newentTlTragcalmst.getsessionMaster().getSaveArray()));
				 }
				       }
					if(newentTlTragcalmst.getFaculty()!= null){
						 CommonMessage.debugMsg("Inside the Faculty");
						 newentTlTragcalmst.getFaculty().setEtcfKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgFacultySql.TBL_ENT_TL_TRGFACULTY,15,"ETF","",""));
						 newentTlTragcalmst.getFaculty().setEtcfEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());
						 sqls.add(EntTlTrgFacultySql.getInsertSql(entTlTrgFacultySql.getFtymDbFields(),newentTlTragcalmst.getFaculty().getSaveArray()));
					}

				   /*  if(newentTlTragcalmst.getRoleLink() != null){
			   		  if(!UIUtils.isValidKeyId(newentTlTragcalmst.getRoleLink().getEtcuKeyid())){
			   			         newentTlTragcalmst.getRoleLink().setEtcuKeyid(dbActionTemplate.getSequenceNumber(entTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP,15,"ETU","",""));
			   			         newentTlTragcalmst.getRoleLink().setEtcuKeyid(newentTlTragcalmst.getEtcmKeyid());
			   			         sqls.add(entTlTrgCalUnqpSql.getInsertSql(entTlTrgCalUnqpSql.getFtymDbFields(),newentTlTragcalmst.getRoleLink().getSaveArray()));
			   				}
			   	      }*/
					/**********Added Multiple unique position**************/
			         if(newentTlTragcalmst.getRoleLink() != null){
					//String allUnique = newentTlTrgCalUnqp.getAllUniquePosition();
		           	String allUnique = newentTlTragcalmst.getAllUniquePosition();
		           	CommonMessage.debugMsg("The All Unique In DAOIMPL"+allUnique);
					 if (UIUtils.isValidKeyId(allUnique) && allUnique.equals("Y")) {		 
						 CommonMessage.debugMsg("Inside if allUnique"+allUnique);
						 StringBuffer sf = new StringBuffer();
						 String flidlist=null;
						 String RoleJh= newentTlTragcalmst.getRoleLink().getEtcuRoleJh();
						 CommonMessage.debugMsg("The RoleJH"+RoleJh);
						 String RoleDmt=newentTlTragcalmst.getRoleLink().getEtcuRoleDmt();
						 CommonMessage.debugMsg("The RoleDMT"+RoleDmt);
						 if(RoleJh==null){
							 CommonMessage.debugMsg("Inside RoleJH");
							 flidlist="SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='"+RoleJh+"'";
							 CommonMessage.debugMsg("Inside the flidlist::JH:"+flidlist);
						 }
						 else{
							 CommonMessage.debugMsg("Inside RoleDMT");
							 flidlist="SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='"+RoleDmt+"'";
							 CommonMessage.debugMsg("Inside the flidlist::DMT:"+flidlist);
						 }
						 String flidData=dbActionTemplate.getSingleValue(flidlist);
						 sf.append(" SELECT DISTINCT ROLE_KEYID from  Ent_Vw_Rolemst ");
					     sf.append(" where INSTR( PARENTFLIDS||FLID ,'" + flidData + "')>0 ");
						 CommonMessage.debugMsg("The Sf Data Query:::"+sf.toString());
						 List<String[]> roleIds = dbActionTemplate.getDataList(sf.toString());
						 CommonMessage.debugMsg(""+"roleIds.size()==="+roleIds.size());
						 for ( int i=0;i<roleIds.size();i++) {
							 newentTlTragcalmst.getRoleLink().setEtcuRoleKeyid(roleIds.get(i)[0]);
							 CommonMessage.debugMsg("roleIds.get(i)[0]==="+roleIds.get(i)[0]);
		  			         newentTlTragcalmst.getRoleLink().setEtcuKeyid(dbActionTemplate.getSequenceNumber(entTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP,15,"ETU","",""));
		  			         newentTlTragcalmst.getRoleLink().setEtcuEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());
		  			         sqls.add(entTlTrgCalUnqpSql.getInsertSql(entTlTrgCalUnqpSql.getFtymDbFields(),newentTlTragcalmst.getRoleLink().getSaveArray()));
						 } 
					 } else {
					         newentTlTragcalmst.getRoleLink().setEtcuEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());    
						   if(!UIUtils.isValidKeyId(newentTlTragcalmst.getRoleLink().getEtcuKeyid())){
			   			         newentTlTragcalmst.getRoleLink().setEtcuKeyid(dbActionTemplate.getSequenceNumber(entTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP,15,"ETU","",""));
		  			             sqls.add(entTlTrgCalUnqpSql.getInsertSql(entTlTrgCalUnqpSql.getFtymDbFields(),newentTlTragcalmst.getRoleLink().getSaveArray()));
						 }else{
		  					 sqls.add(entTlTrgCalUnqpSql.getUpdateSql(entTlTrgCalUnqpSql.getFtymDbFields(),newentTlTragcalmst.getRoleLink().getSaveArray()));	
						 }
					}
			 }
				       CommonMessage.debugMsg(" In sid the Create method");
		    dbActionTemplate.executeStatements(sqls); 
			return newentTlTragcalmst;
		}
	@Override
	public List<EntTlTragcalmst> GrdBsdTrgCalcreate(List<EntTlTragcalmst> trngCalList)
			throws Exception {
		EntTlTragcalmstSql entTlTragcalmstSql = new EntTlTragcalmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		try
		{
			
			List<EntTlTragcalmst> trgCalList=trngCalList;
			String seqIdentfr=""; 
			for(EntTlTragcalmst getElementId :trgCalList){
				//String elementId = getElementId.getEtcm();
				String location = null;
			 	seqIdentfr = EntTlTragcalmstSql.TBL_ENT_TL_TRGCALMST;

			 	/*if( elementId != null && elementId.length() > 10  ){
			 		location = elementId.substring(11, 21);  
			 		seqIdentfr += location;
			 	}*/
			 //	trgCalList.get
			}
			     List<String> sqls = new ArrayList<String>();
			     List <EntTlTragcalmst> methodslist = trngCalList;
			     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),seqIdentfr,15,"ETC","","");
				 for(EntTlTragcalmst abnormalityLink:methodslist)
				 {
						String seqNo=sequenceNumber.getSequnceNumber();
						CommonMessage.debugMsg("The seqNo::::"+seqNo);
						abnormalityLink.setEtcmKeyid(seqNo);
                        sqls.add(EntTlTragcalmstSql.getInsertSql(entTlTragcalmstSql.getFtymDbFields(), abnormalityLink.getSaveArray()));
                      //  MultipleactionPlanEntry(list,"I");
                        System.out.print(" In side thet Dao impl  faculty"+abnormalityLink.getFaculty());
                      /* Commented on 05May2023 by Kiran
                        if(abnormalityLink.getFaculty()!= null){
       					 CommonMessage.debugMsg("Inside the Faculty");
       					abnormalityLink.getFaculty().setEtcfKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgFacultySql.TBL_ENT_TL_TRGFACULTY,15,"ETF","",""));
       					abnormalityLink.getFaculty().setEtcfEtcmKeyid(abnormalityLink.getEtcmKeyid());
       					 sqls.add(EntTlTrgFacultySql.getInsertSql(entTlTrgFacultySql.getFtymDbFields(),abnormalityLink.getFaculty().getSaveArray()));
       					CommonMessage.debugMsg("faculty   sqls Query:"+sqls);
                        }
                        
                        if(abnormalityLink.getRoleLink()!= null){
          					 CommonMessage.debugMsg("Inside the Faculty");
          					abnormalityLink.getRoleLink().setEtcuKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP,15,"ETU","",""));
          					abnormalityLink.getRoleLink().setEtcuEtcmKeyid(abnormalityLink.getEtcmKeyid());
          					abnormalityLink.getRoleLink().setEtcuRoleDmt(abnormalityLink.getEtcmDmt());
          					abnormalityLink.getRoleLink().setEtcuRoleJh(abnormalityLink.getEtcmJh());
          					 sqls.add(EntTlTrgCalUnqpSql.getInsertSql(entTlTrgCalUnqpSql.getFtymDbFields(),abnormalityLink.getRoleLink().getSaveArray()));
          					CommonMessage.debugMsg("Uniqueposition   sqls Query:"+sqls);
                        }
                        
                        if(abnormalityLink.getsessionMaster()!= null){
          					 CommonMessage.debugMsg("Inside the Faculty");
          					abnormalityLink.getsessionMaster().setEtcsKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgCalSessionSql.TBL_ENT_TL_TRGCALSESSION,15,"ETS","",""));
          					abnormalityLink.getsessionMaster().setEtcsEtcmKeyid(abnormalityLink.getEtcmKeyid());
          					 sqls.add(EntTlTrgCalSessionSql.getInsertSql(entTlTrgCalSessionSql.getFtymDbFields(),abnormalityLink.getsessionMaster().getSaveArray()));
          					 CommonMessage.debugMsg("Session  sqls Query:"+sqls);
                        }
                        */
                        CommonMessage.debugMsg("The sqls Query:"+sqls);
				 }
				  /*if(abnormalityLink!= null){
					 CommonMessage.debugMsg("Inside the Faculty");
					 abnormalityLink.getFaculty().setEtcfKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgFacultySql.TBL_ENT_TL_TRGFACULTY,15,"ETF","",""));
					 abnormalityLink.getFaculty().setEtcfEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());
					 sqls.add(EntTlTrgFacultySql.getInsertSql(entTlTrgFacultySql.getFtymDbFields(),newentTlTragcalmst.getFaculty().getSaveArray()));
				}*/
				 dbActionTemplate.executeStatements(sqls);
		}
		catch(BusinessApplicationExceptions e)
		{
			CommonMessage.debugMsg("Business Application   :"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage()); 
		}
		return trngCalList;
	}
	@Override
	public EntTlTragcalmst create(List<EntTlTragcalmst> trngCalfillValues)
			throws Exception {
		// TODO Auto-generated method stub
		EntTlTragcalmstSql entTlTragcalmstSql = new EntTlTragcalmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		try
		{
			
			List<EntTlTragcalmst> trgCalList=trngCalfillValues;
			String seqIdentfr=""; 
			for(EntTlTragcalmst getElementId :trgCalList){
				//String elementId = getElementId.getEtcm();
				String location = null;
			 	seqIdentfr = EntTlTragcalmstSql.TBL_ENT_TL_TRGCALMST;

			 	
				
			}
			     List<String> sqls = new ArrayList<String>();
			     List <EntTlTragcalmst> methodslist = trngCalfillValues;
			     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),seqIdentfr,15,"ETC","","");
				 for(EntTlTragcalmst abnormalityLink:methodslist)
				 {
						String seqNo=sequenceNumber.getSequnceNumber();
						CommonMessage.debugMsg("The seqNo::::"+seqNo);
						abnormalityLink.setEtcmKeyid(seqNo);
                        sqls.add(EntTlTragcalmstSql.getInsertSql(entTlTragcalmstSql.getFtymDbFields(), abnormalityLink.getSaveArray()));
                      //  MultipleactionPlanEntry(list,"I");
                       
                        System.out.print(" In side thet Dao impl  faculty"+abnormalityLink.getFaculty());
                        if(abnormalityLink.getFaculty()!= null){
       					 CommonMessage.debugMsg("Inside the Faculty");
       					abnormalityLink.getFaculty().setEtcfKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgFacultySql.TBL_ENT_TL_TRGFACULTY,15,"ETF","",""));
       					abnormalityLink.getFaculty().setEtcfEtcmKeyid(abnormalityLink.getEtcmKeyid());
       					 sqls.add(EntTlTrgFacultySql.getInsertSql(entTlTrgFacultySql.getFtymDbFields(),abnormalityLink.getFaculty().getSaveArray()));
       				}
				 }
				 /* if(EntTlTragcalmst!= null){
					 CommonMessage.debugMsg("Inside the Faculty");
					 newentTlTragcalmst.getFaculty().setEtcfKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgFacultySql.TBL_ENT_TL_TRGFACULTY,15,"ETF","",""));
					 newentTlTragcalmst.getFaculty().setEtcfEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());
					 sqls.add(EntTlTrgFacultySql.getInsertSql(entTlTrgFacultySql.getFtymDbFields(),newentTlTragcalmst.getFaculty().getSaveArray()));
				}*/
				 
				
				 dbActionTemplate.executeStatements(sqls);
		}
		catch(BusinessApplicationExceptions e)
		{
			CommonMessage.debugMsg("Business Application   :"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage()); 
		}
		return null;
	}
	@Override
	public EntTlTrgCalSession createSession(
			EntTlTrgCalSession newentTlTrgCalSession) throws Exception {
		// TODO Auto-generated method stub
	try{
				//  String count=dbActionTemplate.getSingleValue("ENT_TL_TRGCALSESSION", "COUNT(*)", "ETCS_ETCM_KEYID", newentTlTragcalmst.getsessionMaster().getEtcsEtcmKeyid());
				//  int cnt=Integer.parseInt(count)+1;
	     List<String> sqls = new ArrayList<String>();

				// String sessionname ="Session"+; 
				  
				   String cnt = getSessionName(newentTlTrgCalSession.getEtcsEtcmKeyid());
				   int Sescnt=Integer.parseInt(cnt)+1;
				   String sessionname ="Session "+Sescnt; 
				  newentTlTrgCalSession.setEtcsName(sessionname);	  
				  newentTlTrgCalSession.setEtcsKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgCalSessionSql.TBL_ENT_TL_TRGCALSESSION,15,"ETS","","" ));	 
				 
			      sqls.add(EntTlTrgCalSessionSql.getInsertSql(entTlTrgCalSessionSql.getFtymDbFields(),newentTlTrgCalSession.getSaveArray()));
			      dbActionTemplate.executeStatements(sqls);
	}catch(Exception e){
		e.printStackTrace();
		
	}
	return newentTlTrgCalSession;
	}
	

private String getSessionName(String etcsEtcmKeyid) {
	 String sql="select count(*) from ENT_TL_TRGCALSESSION " +
				"WHERE ETCS_ETCM_KEYID='"+etcsEtcmKeyid+"'";
		String sessioncnt = null;
		try {
			sessioncnt = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessioncnt;
	}
private String getSessionName(EntTlTrgCalSession newentTlTrgCalSession) {
		// TODO Auto-generated method stub
		return null;
	}
public String chkSessionDate(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	String fromtme=commonFilter.getDteAllotedfrm();
	CommonMessage.debugMsg("The fromtme"+fromtme);
	String totme=commonFilter.getDteAllotedto();
	CommonMessage.debugMsg("The totme"+totme);
	String frmhr=fromtme.substring(0,2);
	CommonMessage.debugMsg("The frmhr"+frmhr);
	String frommin=fromtme.substring(3, 5);
	String tohr=totme.substring(0,2);
	String tomin=totme.substring(3, 5);
	String fromdate=commonFilter.getDteOccuredto()+" "+frmhr+":"+frommin;
	CommonMessage.debugMsg("The fromtme"+fromdate);
	String todate=commonFilter.getDteOccuredto()+" "+tohr+":"+tomin;
	CommonMessage.debugMsg("The todate"+todate);
	String sql="select count(*) from ENT_TL_TRGCALSESSION " +
			"WHERE ETCS_ETCM_KEYID='"+commonFilter.getKey()+"' AND TO_CHAR (ETCS_SESSIONDATE, 'DD-Mon-YYYY')='"+commonFilter.getDteOccuredto()+"'" +
			"AND TO_CHAR (ETCS_FROMDATE, 'DD-Mon-YYYY HH24:MI')='"+fromdate+"' AND TO_CHAR (ETCS_TILLDATE, 'DD-Mon-YYYY HH24:MI')='"+todate+"'";
    CommonMessage.debugMsg("sql::"+sql);
	String sessioncnt=dbActionTemplate.getSingleValue(sql);
	return sessioncnt;
}
@Override
public String chkUniqueposition(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	String uniqcnt = null;
	//if(commonFilter.getCellch()=="true" || commonFilter.getCellch()=="false" )
	//{
		String sql="select count(*) from ENT_TL_TRGCALUNQP " +
			"WHERE ETCU_ETCM_KEYID='"+commonFilter.getKey()+"' AND ETCU_ROLE_KEYID='"+commonFilter.getUtil()+"'"; 
		CommonMessage.debugMsg("sql in unique position"+sql);
		uniqcnt=dbActionTemplate.getSingleValue(sql);
		return uniqcnt;
}
@Override
public EntTlTrgCalUnqp createUniquePostion(EntTlTrgCalUnqp newentTlTrgCalUnqp)
		throws Exception {

    List<String> sqls = new ArrayList<String>();	  
	  newentTlTrgCalUnqp.setEtcuKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP,15,"ETU","","" ));	 
	 
      sqls.add(EntTlTrgCalUnqpSql.getInsertSql(entTlTrgCalUnqpSql.getFtymDbFields(),newentTlTrgCalUnqp.getSaveArray()));
    CommonMessage.debugMsg(" System SQL"+sqls);
      dbActionTemplate.executeStatements(sqls);
	return newentTlTrgCalUnqp;

}
public String FacultyCheck(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	String result=null;
	
	String facultycnt=dbActionTemplate.getSingleValue("select count(*) from ENT_TL_TRGFACULTY WHERE ETCF_ETCM_KEYID='"+commonFilter.getKey()+"' AND ETCF_FACULTYID='"+commonFilter.getEmpch()+"'");
    
	return facultycnt;
	
}
@Override
public List<String[]> getFaculty(String progKeyid) throws Exception {
	// TODO Auto-generated method stub
	    StringBuilder sql = new StringBuilder( "select '',FTYM_EMPM_KEYID,ETCF_KEYID,FTYM_NAME  as \"Faculty\",'' as \"Delete\"  ");
		sql.append(" from ENT_TL_TRGFACULTY,ENT_TL_FACULTYMST,ENT_TL_TRGCALMST " );
		sql.append(" where ETCM_KEYID ='").append(progKeyid).append("'  " );
		sql.append(" AND ETCF_ETCM_KEYID=ETCM_KEYID AND ETCF_FACULTYID=FTYM_KEYID ");
		CommonMessage.debugMsg("Faculty Data"+sql.toString());
		List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql.toString(),null);
		CommonMessage.debugMsg("The DataList::"+dataList);
		return dataList;
		}
@Override
public EntTlTrgFaculty createFaculty(EntTlTrgFaculty newEntTlTrgFaculty)
		throws Exception {
	// TODO Auto-generated method stub
	
	entTlTrgFacultySql=new EntTlTrgFacultySql();
	
	 List<String> sqls = new ArrayList<String>();	  
	 newEntTlTrgFaculty.setEtcfKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgFacultySql.TBL_ENT_TL_TRGFACULTY,15,"ETF","","" ));	 
	 
     sqls.add(EntTlTrgFacultySql.getInsertSql(entTlTrgFacultySql.getFtymDbFields(),newEntTlTrgFaculty.getSaveArray()));
   CommonMessage.debugMsg(" System SQL"+sqls);
     dbActionTemplate.executeStatements(sqls);
	return newEntTlTrgFaculty;
}
// ------------------ Vignesh 28nov2025 -------------------------------------------------------------------------------------//

//
//public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter)
//        throws Exception {
//    // TODO Auto-generated method stub
//	CommonMessage.debugMsg(" In side the DAoimpl.............."+ commonFilter.getKey());
//            StringBuffer sqls = new StringBuffer();
//            CommonMessage.debugMsg("commonFilter"+commonFilter.getKey());
//            CommonMessage.debugMsg("commonFilter.length"+commonFilter.getRefdocid());
//           
//            if(commonFilter.getRefdocid()==null)
//            {  
//            	CommonMessage.debugMsg(commonFilter.getCellId()+"   "+commonFilter.getSectionId()+  "In side the DAoimpl....23.........."+ commonFilter.getKey());
//            	
//            sqls.append(" SELECT * FROM ( ");
//            sqls.append(" select  Distinct '' as selctVal,Empm_keyid as Empm_keyid,EMPM_CODE AS EMPM_CODE,EMPM_NAME as EMPM_NAME,DECODE(EMPM_EMPLOYEETYPE,'R','Employee','M','Manager','C','Contract','A','Asosciate','B','Badli'),DECODE(EMPM_GENDER,'M','Male','F','Female'),DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) as Ses, ");
//            sqls.append(" ROLE_NAME AS ROLE_NAME,ETCQ_CURRENTLEVEL as ETCQ_CURRENTLEVEL,");
//            sqls.append(" MAX(ETCQ_CURRENTLEVELDATE) AS ETCQ_CURRENTLEVELDATE,C.SECT_KEYID AS SECTIONID, C.SECT_NAME AS SECT_NAME,C.CELL_KEYID AS CELLID,C.CELL_NAME AS CELL_NAME, EMPM_ROLEID as roleid, DECODE(ETCE_KEYID,null,' ',ETCM_KEYID),ETCE_KEYID AS ETCEKEYID ");
//            sqls.append("  from GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM,GEN_TL_ROLEMST,ENT_TL_TRGCALQUAD,GEN_VW_FNLN C ");
//            if(commonFilter.getKey()!=null)
//            {
//                  sqls.append(" ,ENT_TL_TRGCALMST,ENT_TL_TRGCALEMP,ENT_TL_TRGCALSESSION ,GEN_TL_SECTIONMST A,GEN_TL_CELLMST B ");
//            }
//            if(UIUtils.isValidKeyId(commonFilter.getTrarId())){
//            	sqls.append(" ,GEN_TL_TEAMTRADELINK ");
//            }
//           if(commonFilter.getSectionId()!=null)
//            {
//                 sqls.append(" ,GEN_MV_FLIDHIERARCHY ");
//            }
//            sqls.append(" WHERE EMPM_KEYID = FRT_EMPM_KEYID AND EMPM_ROLEID = ROLE_KEYID(+) AND FRT_FNLN_KEYID=FNLN_KEYID ");
//            sqls.append(" AND ETCQ_EMPM_KEYID(+)=FRT_EMPM_KEYID AND EMPM_ACTIVE='Y' ");
//          
//            if(UIUtils.isValidKeyId(commonFilter.getCellId())){
//        		sqls.append(" AND FNLN_KEYID = (SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID='"+commonFilter.getCellId()+"')");
//        	}
//            if(commonFilter.getSectionId()!=null)
//            {
//            	if(!UIUtils.isValidKeyId(commonFilter.getCellId())){
//                //String flnid=dbActionTemplate.getSingleValue("GEN_MV_FLIDHIERARCHY", "FLID", "FNLN_ORIGINALID", commonFilter.getFactoryId());
//                sqls.append("  AND FNLN_KEYID=FLID  and INSTR(PARENTFLIDS||'/'||FLID, (SELECT FNLN_KEYID FROM  gen_tl_functionallocn   WHERE FNLN_ORIGINALID='"+commonFilter.getSectionId()+"'))>0 ");
//            }
//            	else{
//            		sqls.append(" AND FNLN_KEYID = (SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID='"+commonFilter.getCellId()+"')");
//
//            	}
//            }
//            else{
//            if(commonFilter.getUniquePos().equals("false")||commonFilter.getUniquePos()==null||commonFilter.getUniquePos()==""){
//            	if(commonFilter.getCellId()!=null){
//            		sqls.append(" AND FNLN_KEYID = (SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID='"+commonFilter.getCellId()+"')");
//            	}
//            	else
//            {
//            sqls.append(" AND FRT_FNLN_KEYID = '"+commonFilter.getFlid()+"'");
//            }
//            }else{
//                 sqls.append("AND FNLN_KEYID = '"+commonFilter.getFlid()+"' AND EMPM_ROLEID IN (SELECT ETCU_ROLE_KEYID FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID='"+commonFilter.getKey()+"')");
//            }
//            }
//            CommonMessage.debugMsg(commonFilter.getTrarId() +" trade id "+commonFilter.getType());
//            
//          
//           if(UIUtils.isValidKeyId(commonFilter.getTrarId())){
//            	 sqls.append(" AND FRP_FRT_KEYID=FRT_KEYID AND FRP_TRADEID='"+commonFilter.getTrarId()+"'");
//            }
//           if(commonFilter.getType()!=null){
//            if(commonFilter.getType().equals("UQ")){
//                sqls.append(" AND EMPM_ROLEID IN (SELECT ETCU_ROLE_KEYID FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID='"+commonFilter.getKey()+"')");//"AND FNLN_KEYID = '"+commonFilter.getFlid()+"'
//
//            }
//           /* else{
//            	sqls.append(" AND FNLN_KEYID ='"+commonFilter.getFlid()+"'");
//            }
//           }
//            else{
//            	sqls.append(" AND FNLN_KEYID ='"+commonFilter.getFlid()+"'");
//            */
//            }
//            /*else{
//            	sqls.append(" AND FRP_FRT_KEYID=FRT_KEYID ");
//
//            	//sqls.append(" AND FRP_FRT_KEYID=FRT_KEYID AND FRP_TRADEID='"+commonFilter.getTrarId()+"'");
//            }*/
//            
//           
//          
//            if(commonFilter.getEmpwiseType()!=null &&UIUtils.isValidKeyId(commonFilter.getEmpwiseType()) )
//            {
//            	CommonMessage.debugMsg("Employee Type"+commonFilter.getEmpwiseType());
//              sqls.append("  AND EMPM_EMPLOYEETYPE = '"+commonFilter.getEmpwiseType()+"' ");  
//            }
//            if(commonFilter.getEmpch()!=null)
//            {
//              sqls.append("  AND EMPM_GENDER = '"+commonFilter.getEmpch()+"' ");  
//            }
//           /* if(commonFilter.getRoleLevel()!=null)
//            {
//              sqls.append("  AND FRT_ROLE_KEYID = '"+commonFilter.getRoleLevel()+"' ");  
//            }*/
//            if(commonFilter.getKey()!=null)
//            {
//                  sqls.append(" AND ETCM_KEYID = ETCE_ETCM_KEYID(+) AND EMPM_KEYID = ETCE_EMPM_KEYID(+) AND ETCM_KEYID(+)='"+commonFilter.getKey()+"' AND ETCQ_TOPICID(+)=ETCM_TOPICID AND ETCE_ETCS_KEYID=ETCS_KEYID(+)");
//                  sqls.append(" AND C.CELL_KEYID(+) =B.CELL_KEYID AND C.SECT_KEYID(+) =A.SECT_KEYID AND B.CELL_SECTIONID(+)=A.SECT_KEYID ");
//            }
//            sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
//            sqls.append( "GROUP BY EMPM_NAME,EMPM_CODE,Empm_keyid,EMPM_EMPLOYEETYPE,EMPM_GENDER,ROLE_NAME,C.SECT_NAME,C.SECT_KEYID,C.CELL_KEYID,C.CELL_NAME,EMPM_ROLEID,ETCQ_CURRENTLEVEL");
//          
//            if(commonFilter.getKey()!=null)
//            {
//                  sqls.append(" ,DECODE(ETCE_KEYID,null,' ',ETCM_KEYID),ETCE_KEYID,DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) ORDER BY ETCE_KEYID ");
//            }
//            else{
//                sqls.append("ORDER BY EMPM_KEYID");
//            }
//            //sqls.append(" AND FNLN_KEYID = '"+commonFilter.getFlid()+"' ");
//          
//          
//            sqls.append(")");
//           CommonMessage.debugMsg("inside the get data"+sqls);
//            }
//          
//            else{
//                CommonMessage.debugMsg("Inside the Else");
//                sqls.append(" SELECT * FROM ( ");
//                sqls.append("  select  Distinct '' as selctVal,Empm_keyid as keyid,EMPM_CODE AS empcode,EMPM_NAME as name,DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) AS ses, ");
//                sqls.append("ROLE_NAME AS ROLENAME,ETCQ_CURRENTLEVEL as currLevel,");
//                sqls.append(" MAX(ETCQ_CURRENTLEVELDATE) AS LastUpdate,SECT_KEYID AS SECTIONID, SECT_NAME AS DMT,CELL_KEYID AS CELLID,CELL_NAME AS JH, EMPM_ROLEID as roleid, DECODE (ETCE_KEYID, NULL, ' ', ETCM_KEYID),ETCE_KEYID AS ETCEKEYID ");
//                sqls.append(" from GEN_TL_EMPLOYEEMST,GEN_TL_MOM_GROUPMST,GEN_TL_MOM_GROUPDTL,GEN_TL_ROLEMST, ");
//                sqls.append("  GEN_TL_FNLNROLETEAM,GEN_VW_FNLN,ENT_TL_TRGCALQUAD,ENT_TL_TRGCALMST,ENT_TL_TRGCALEMP,ENT_TL_TRGCALSESSION  WHERE MGRD_MGRM_KEYID=MGRM_KEYID  AND EMPM_KEYID = MGRD_EMPM_KEYID AND EMPM_ROLEID = ROLE_KEYID(+) ");
//                sqls.append(" AND FRT_FNLN_KEYID=FNLN_KEYID AND FRT_EMPM_KEYID=MGRD_EMPM_KEYID AND MGRD_EMPM_KEYID=ETCQ_EMPM_KEYID(+) AND MGRD_MGRM_KEYID='"+commonFilter.getRefdocid()+"' AND ETCE_ETCS_KEYID=ETCS_KEYID(+)");
//                sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
//                sqls.append(" AND ETCM_KEYID = ETCE_ETCM_KEYID(+) AND EMPM_KEYID = ETCE_EMPM_KEYID(+) AND ETCQ_TOPICID(+)=ETCM_TOPICID AND ETCM_KEYID(+)='"+commonFilter.getKey()+"'");
//                sqls.append( "GROUP BY EMPM_NAME,EMPM_CODE,Empm_keyid,ROLE_NAME,SECT_NAME,SECT_KEYID,CELL_KEYID,CELL_NAME,EMPM_ROLEID,DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME),ETCQ_CURRENTLEVEL,DECODE (ETCE_KEYID, NULL, ' ', ETCM_KEYID),ETCE_KEYID ORDER BY ETCE_KEYID");
//
//                sqls.append(")");
//                CommonMessage.debugMsg("The Else Data::"+sqls);
//              
//            }
//          //  sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
//                String countsql = CommonFilterSqls.countSql(sqls.toString(), commonFilter.getGridFilter());
//                CommonMessage.debugMsg("countsql " + countsql);
//                String cntStr = dbActionTemplate.getSingleValue(countsql);
//                CommonMessage.debugMsg("cntStr " + cntStr);
//                int count = Integer.parseInt(cntStr);
//                CommonMessage.debugMsg("count " + count);
//                commonFilter.setTotalRecordCnt(count);
//        
//              GridParams gridParams = new GridParams();
//              gridParams.setFromRow(commonFilter.getFromRow());
//              gridParams.setToRow(commonFilter.getToRow());
//              CommonMessage.debugMsg("From Row  :" +commonFilter.getFromRow());
//              CommonMessage.debugMsg("To Row  :" +commonFilter.getToRow());
//             
//              String oSql =  CommonFilterSqls.addPaginationParams(sqls.toString(), gridParams);
//              CommonMessage.debugMsg("osql " + oSql);
//           CommonMessage.debugMsg("sql get selected employee"+sqls.toString());
//           List<String[]> dataList =  dbActionTemplate.getDataList(oSql.toString());
//           CommonMessage.debugMsg("getEmpList :" +dataList.size());
//       //    CommonMessage.debugMsg("osql  :" +oSql);
//          
//           if( commonFilter.getViewClick() == 'Y'){
//               String totalCnt = cntStr;//paramValues.get(0);
//             CommonMessage.debugMsg("totalCnt...."+totalCnt);
//               boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
//               if(  isInteger ){
//                   commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
//               }
//           }
//          
//           return dataList;
//}


//public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter)
//        throws Exception {
//    // TODO Auto-generated method stub
//    CommonMessage.debugMsg(" In side the DAoimpl.............." + commonFilter.getKey());
//    StringBuffer sqls = new StringBuffer();
//    CommonMessage.debugMsg("commonFilter" + commonFilter.getKey());
//    CommonMessage.debugMsg("commonFilter.length" + commonFilter.getRefdocid());
//
//    if (commonFilter.getRefdocid() == null) {
//        CommonMessage.debugMsg(commonFilter.getCellId() + "   " + commonFilter.getSectionId()
//                + "In side the DAoimpl....213.........." + commonFilter.getKey());
//
//        sqls.append(" SELECT * FROM ( ");
//        sqls.append(" select  Distinct '' as selctVal,Empm_keyid as Empm_keyid,EMPM_CODE AS EMPM_CODE,EMPM_NAME as EMPM_NAME,");
//        // DECODE(EMPM_EMPLOYEETYPE,'R','Employee','M','Manager','C','Contract','A','Asosciate','B','Badli')
//        sqls.append(" CASE EMPM_EMPLOYEETYPE ");
//        sqls.append(" WHEN 'R' THEN 'Employee' ");
//        sqls.append(" WHEN 'M' THEN 'Manager' ");
//        sqls.append(" WHEN 'C' THEN 'Contract' ");
//        sqls.append(" WHEN 'A' THEN 'Asosciate' ");
//        sqls.append(" WHEN 'B' THEN 'Badli' ");
//        sqls.append(" END,");
//        // DECODE(EMPM_GENDER,'M','Male','F','Female')
//        sqls.append(" CASE EMPM_GENDER ");
//        sqls.append(" WHEN 'M' THEN 'Male' ");
//        sqls.append(" WHEN 'F' THEN 'Female' ");
//        sqls.append(" END,");
//        // DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) as Ses
//        sqls.append(" CASE WHEN ETCE_ETCS_KEYID = '{}' THEN ' ' ELSE ETCS_NAME END as Ses, ");
//        sqls.append(" ROLE_NAME AS ROLE_NAME,ETCQ_CURRENTLEVEL as ETCQ_CURRENTLEVEL,");
//        sqls.append(" MAX(ETCQ_CURRENTLEVELDATE) AS ETCQ_CURRENTLEVELDATE,");
//        sqls.append(" C.SECT_KEYID AS SECTIONID, C.SECT_NAME AS SECT_NAME,");
//        sqls.append(" C.CELL_KEYID AS CELLID,C.CELL_NAME AS CELL_NAME, ");
//        sqls.append(" EMPM_ROLEID as roleid, ");
//        // DECODE(ETCE_KEYID,null,' ',ETCM_KEYID)
//        sqls.append(" CASE WHEN ETCE_KEYID IS NULL THEN ' ' ELSE ETCM_KEYID END,");
//        sqls.append(" ETCE_KEYID AS ETCEKEYID ");
//        sqls.append("  from GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM,GEN_TL_ROLEMST,ENT_TL_TRGCALQUAD,GEN_VW_FNLN C ");
//        if (commonFilter.getKey() != null) {
//            sqls.append(" ,ENT_TL_TRGCALMST,ENT_TL_TRGCALEMP,ENT_TL_TRGCALSESSION ,GEN_TL_SECTIONMST A,GEN_TL_CELLMST B ");
//        }
//        if (UIUtils.isValidKeyId(commonFilter.getTrarId())) {
//            sqls.append(" ,GEN_TL_TEAMTRADELINK ");
//        }
//        if (commonFilter.getSectionId() != null) {
//            sqls.append(" ,GEN_MV_FLIDHIERARCHY ");
//        }
//
//        // Removed all (+) for Postgres
//        sqls.append(" WHERE EMPM_KEYID = FRT_EMPM_KEYID AND EMPM_ROLEID = ROLE_KEYID AND FRT_FNLN_KEYID=FNLN_KEYID ");
//        sqls.append(" AND ETCQ_EMPM_KEYID=FRT_EMPM_KEYID AND EMPM_ACTIVE='Y' ");
//
//        if (UIUtils.isValidKeyId(commonFilter.getCellId())) {
//            sqls.append(
//                    " AND FNLN_KEYID = (SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID='"
//                            + commonFilter.getCellId() + "')");
//        }
//        if (commonFilter.getSectionId() != null) {
//            if (!UIUtils.isValidKeyId(commonFilter.getCellId())) {
//                // INSTR -> STRPOS in Postgres
//                sqls.append(
//                        "  AND FNLN_KEYID=FLID  and STRPOS(PARENTFLIDS||'/'||FLID, (SELECT FNLN_KEYID FROM  gen_tl_functionallocn   WHERE FNLN_ORIGINALID='"
//                                + commonFilter.getSectionId() + "'))>0 ");
//            } else {
//                sqls.append(
//                        " AND FNLN_KEYID = (SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID='"
//                                + commonFilter.getCellId() + "')");
//            }
//        } else {
//            if (commonFilter.getUniquePos().equals("false") || commonFilter.getUniquePos() == null
//                    || commonFilter.getUniquePos() == "") {
//                if (commonFilter.getCellId() != null) {
//                    sqls.append(
//                            " AND FNLN_KEYID = (SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID='"
//                                    + commonFilter.getCellId() + "')");
//                } else {
//                    sqls.append(" AND FRT_FNLN_KEYID = '" + commonFilter.getFlid() + "'");
//                }
//            } else {
//                sqls.append(
//                        "AND FNLN_KEYID = '" + commonFilter.getFlid()
//                                + "' AND EMPM_ROLEID IN (SELECT ETCU_ROLE_KEYID FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID='"
//                                + commonFilter.getKey() + "')");
//            }
//        }
//        CommonMessage.debugMsg(commonFilter.getTrarId() + " trade id " + commonFilter.getType());
//
//        if (UIUtils.isValidKeyId(commonFilter.getTrarId())) {
//            sqls.append(" AND FRP_FRT_KEYID=FRT_KEYID AND FRP_TRADEID='" + commonFilter.getTrarId() + "'");
//        }
//        if (commonFilter.getType() != null) {
//            if (commonFilter.getType().equals("UQ")) {
//                sqls.append(
//                        " AND EMPM_ROLEID IN (SELECT ETCU_ROLE_KEYID FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID='"
//                                + commonFilter.getKey() + "')");
//            }
//        }
//
//        if (commonFilter.getEmpwiseType() != null && UIUtils.isValidKeyId(commonFilter.getEmpwiseType())) {
//            CommonMessage.debugMsg("Employee Type" + commonFilter.getEmpwiseType());
//            sqls.append("  AND EMPM_EMPLOYEETYPE = '" + commonFilter.getEmpwiseType() + "' ");
//        }
//        if (commonFilter.getEmpch() != null) {
//            sqls.append("  AND EMPM_GENDER = '" + commonFilter.getEmpch() + "' ");
//        }
//
//        if (commonFilter.getKey() != null) {
//            sqls.append(
//                    " AND ETCM_KEYID = ETCE_ETCM_KEYID AND EMPM_KEYID = ETCE_EMPM_KEYID AND ETCM_KEYID='"
//                            + commonFilter.getKey()
//                            + "' AND ETCQ_TOPICID=ETCM_TOPICID AND ETCE_ETCS_KEYID=ETCS_KEYID");
//            sqls.append(
//                    " AND C.CELL_KEYID =B.CELL_KEYID AND C.SECT_KEYID =A.SECT_KEYID AND B.CELL_SECTIONID=A.SECT_KEYID ");
//        }
//
//        sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
//        sqls.append(
//                "GROUP BY EMPM_NAME,EMPM_CODE,Empm_keyid,EMPM_EMPLOYEETYPE,EMPM_GENDER,ROLE_NAME,C.SECT_NAME,C.SECT_KEYID,C.CELL_KEYID,C.CELL_NAME,EMPM_ROLEID,ETCQ_CURRENTLEVEL");
//
//        if (commonFilter.getKey() != null) {
//            // group by CASE equivalents instead of DECODE
//            sqls.append(
//                    " ,CASE WHEN ETCE_KEYID IS NULL THEN ' ' ELSE ETCM_KEYID END,ETCE_KEYID,CASE WHEN ETCE_ETCS_KEYID = '{}' THEN ' ' ELSE ETCS_NAME END ORDER BY ETCE_KEYID ");
//        } else {
//            sqls.append(" ORDER BY EMPM_KEYID");
//        }
//
//        sqls.append(") a"); // alias required in Postgres
//        CommonMessage.debugMsg("inside the get data" + sqls);
//    }
//
//    else {
//        CommonMessage.debugMsg("Inside the Else");
//        sqls.append(" SELECT * FROM ( ");
//        sqls.append(
//                "  select  Distinct '' as selctVal,Empm_keyid as keyid,EMPM_CODE AS empcode,EMPM_NAME as name,");
//        // DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) AS ses
//        sqls.append(" CASE WHEN ETCE_ETCS_KEYID = '{}' THEN ' ' ELSE ETCS_NAME END AS ses, ");
//        sqls.append("ROLE_NAME AS ROLENAME,ETCQ_CURRENTLEVEL as currLevel,");
//        sqls.append(
//                " MAX(ETCQ_CURRENTLEVELDATE) AS LastUpdate,SECT_KEYID AS SECTIONID, SECT_NAME AS DMT,CELL_KEYID AS CELLID,CELL_NAME AS JH, EMPM_ROLEID as roleid, ");
//        // DECODE (ETCE_KEYID, NULL, ' ', ETCM_KEYID)
//        sqls.append(" CASE WHEN ETCE_KEYID IS NULL THEN ' ' ELSE ETCM_KEYID END,");
//        sqls.append("ETCE_KEYID AS ETCEKEYID ");
//        sqls.append(
//                " from GEN_TL_EMPLOYEEMST,GEN_TL_MOM_GROUPMST,GEN_TL_MOM_GROUPDTL,GEN_TL_ROLEMST, ");
//        sqls.append(
//                "  GEN_TL_FNLNROLETEAM,GEN_VW_FNLN,ENT_TL_TRGCALQUAD,ENT_TL_TRGCALMST,ENT_TL_TRGCALEMP,ENT_TL_TRGCALSESSION  WHERE MGRD_MGRM_KEYID=MGRM_KEYID  AND EMPM_KEYID = MGRD_EMPM_KEYID AND EMPM_ROLEID = ROLE_KEYID ");
//        sqls.append(
//                " AND FRT_FNLN_KEYID=FNLN_KEYID AND FRT_EMPM_KEYID=MGRD_EMPM_KEYID AND MGRD_EMPM_KEYID=ETCQ_EMPM_KEYID AND MGRD_MGRM_KEYID='"
//                        + commonFilter.getRefdocid() + "' AND ETCE_ETCS_KEYID=ETCS_KEYID");
//        sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
//        sqls.append(
//                " AND ETCM_KEYID = ETCE_ETCM_KEYID AND EMPM_KEYID = ETCE_EMPM_KEYID AND ETCQ_TOPICID=ETCM_TOPICID AND ETCM_KEYID='"
//                        + commonFilter.getKey() + "'");
//        // group by with CASE instead of DECODE
//        sqls.append(
//                " GROUP BY EMPM_NAME,EMPM_CODE,Empm_keyid,ROLE_NAME,SECT_NAME,SECT_KEYID,CELL_KEYID,CELL_NAME,EMPM_ROLEID,");
//        sqls.append(" CASE WHEN ETCE_ETCS_KEYID = '{}' THEN ' ' ELSE ETCS_NAME END,");
//        sqls.append(
//                "ETCQ_CURRENTLEVEL,CASE WHEN ETCE_KEYID IS NULL THEN ' ' ELSE ETCM_KEYID END,ETCE_KEYID ORDER BY ETCE_KEYID");
//
//        sqls.append(") a");
//        CommonMessage.debugMsg("The Else Data::" + sqls);
//
//    }
//
//    String countsql = CommonFilterSqls.countSql(sqls.toString(), commonFilter.getGridFilter());
//    CommonMessage.debugMsg("countsql " + countsql);
//    String cntStr = dbActionTemplate.getSingleValue(countsql);
//    CommonMessage.debugMsg("cntStr " + cntStr);
//    int count = Integer.parseInt(cntStr);
//    CommonMessage.debugMsg("count " + count);
//    commonFilter.setTotalRecordCnt(count);
//
//    GridParams gridParams = new GridParams();
//    gridParams.setFromRow(commonFilter.getFromRow());
//    gridParams.setToRow(commonFilter.getToRow());
//    CommonMessage.debugMsg("From Row  :" + commonFilter.getFromRow());
//    CommonMessage.debugMsg("To Row  :" + commonFilter.getToRow());
//
//    String oSql = CommonFilterSqls.addPaginationParams(sqls.toString(), gridParams);
//    CommonMessage.debugMsg("osql " + oSql);
//    CommonMessage.debugMsg("sql get selected employee" + sqls.toString());
//
//    List<String[]> dataList = dbActionTemplate.getDataList(oSql.toString());
//    CommonMessage.debugMsg("getEmpList :" + dataList.size());
//
//    if (commonFilter.getViewClick() == 'Y') {
//        String totalCnt = cntStr;
//        CommonMessage.debugMsg("totalCnt...." + totalCnt);
//        boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
//        if (isInteger) {
//            commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
//        }
//    }
//
//    return dataList;
//}

    // -- altered vignesh 

@Override
public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter)
        throws Exception {

    CommonMessage.debugMsg(" In side the DAoimpl.............." + commonFilter.getKey());
    StringBuffer sqls = new StringBuffer();
    CommonMessage.debugMsg("commonFilter" + commonFilter.getKey());
    CommonMessage.debugMsg("commonFilter.length" + commonFilter.getRefdocid());

    // 1) WHEN refdocid IS NULL  ---------------------------------------------
    if (commonFilter.getRefdocid() == null) {
        CommonMessage.debugMsg(
                commonFilter.getCellId() + "   " + commonFilter.getSectionId()
                        + "In side the DAoimpl....23.........." + commonFilter.getKey());

        sqls.append(" SELECT * FROM ( ");
        sqls.append("  SELECT DISTINCT ");
        sqls.append("    '' AS selctVal,");
        sqls.append("    e.EMPM_KEYID AS Empm_keyid,");
        sqls.append("    e.EMPM_CODE  AS EMPM_CODE,");
        sqls.append("    e.EMPM_NAME  AS EMPM_NAME,");

        // DECODE(EMPM_EMPLOYEETYPE,...) → CASE
        sqls.append("    CASE e.EMPM_EMPLOYEETYPE ");
        sqls.append("         WHEN 'R' THEN 'Employee' ");
        sqls.append("         WHEN 'M' THEN 'Manager' ");
        sqls.append("         WHEN 'C' THEN 'Contract' ");
        sqls.append("         WHEN 'A' THEN 'Asosciate' ");
        sqls.append("         WHEN 'B' THEN 'Badli' ");
        sqls.append("    END AS EMPM_EMPLOYEETYPE,");

        // DECODE(EMPM_GENDER,...) → CASE
        sqls.append("    CASE e.EMPM_GENDER ");
        sqls.append("         WHEN 'M' THEN 'Male' ");
        sqls.append("         WHEN 'F' THEN 'Female' ");
        sqls.append("    END AS EMPM_GENDER,");

        // DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) as Ses
        sqls.append("    CASE WHEN ce.ETCE_ETCS_KEYID = '{}' THEN ' ' ELSE s.ETCS_NAME END AS Ses,");

        sqls.append("    r.ROLE_NAME       AS ROLE_NAME,");
        sqls.append("    q.ETCQ_CURRENTLEVEL          AS ETCQ_CURRENTLEVEL,");
        sqls.append("    MAX(q.ETCQ_CURRENTLEVELDATE) AS ETCQ_CURRENTLEVELDATE,");
        sqls.append("    c.SECT_KEYID     AS SECTIONID,");
        sqls.append("    c.SECT_NAME      AS SECT_NAME,");
        sqls.append("    c.CELL_KEYID     AS CELLID,");
        sqls.append("    c.CELL_NAME      AS CELL_NAME,");
        sqls.append("    e.EMPM_ROLEID    AS roleid,");

        // DECODE(ETCE_KEYID,null,' ',ETCM_KEYID)
        sqls.append("    CASE WHEN ce.ETCE_KEYID IS NULL THEN ' ' ELSE m.ETCM_KEYID END,");
        sqls.append("    ce.ETCE_KEYID    AS ETCEKEYID ");

        sqls.append("  FROM GEN_TL_EMPLOYEEMST e ");
        sqls.append("  JOIN GEN_TL_FNLNROLETEAM frt ");
        sqls.append("    ON e.EMPM_KEYID = frt.FRT_EMPM_KEYID ");
        sqls.append("  JOIN GEN_VW_FNLN c ");
        sqls.append("    ON frt.FRT_FNLN_KEYID = c.FNLN_KEYID ");
        sqls.append("  LEFT JOIN GEN_TL_ROLEMST r ");
        sqls.append("    ON e.EMPM_ROLEID = r.ROLE_KEYID ");

        // Training master (ETCM), emp-link (ETCE), session (ETCS), quad (ETCQ)
        sqls.append("  LEFT JOIN ENT_TL_TRGCALMST m ");
        sqls.append("    ON m.ETCM_KEYID = '").append(commonFilter.getKey()).append("' ");
        sqls.append("  LEFT JOIN ENT_TL_TRGCALEMP ce ");
        sqls.append("    ON ce.ETCE_ETCM_KEYID = m.ETCM_KEYID ");
        sqls.append("   AND ce.ETCE_EMPM_KEYID = e.EMPM_KEYID ");
        sqls.append("  LEFT JOIN ENT_TL_TRGCALSESSION s ");
        sqls.append("    ON s.ETCS_KEYID = ce.ETCE_ETCS_KEYID ");
        sqls.append("  LEFT JOIN ENT_TL_TRGCALQUAD q ");
        sqls.append("    ON q.ETCQ_EMPM_KEYID = frt.FRT_EMPM_KEYID ");
        sqls.append("   AND q.ETCQ_TOPICID    = m.ETCM_TOPICID ");

        // Section and cell
        sqls.append("  LEFT JOIN GEN_TL_SECTIONMST a ");
        sqls.append("    ON a.SECT_KEYID = c.SECT_KEYID ");
        sqls.append("  LEFT JOIN GEN_TL_CELLMST b ");
        sqls.append("    ON b.CELL_KEYID    = c.CELL_KEYID ");
        sqls.append("   AND b.CELL_SECTIONID = a.SECT_KEYID ");

        // Trade link (was GEN_TL_TEAMTRADELINK)
        if (UIUtils.isValidKeyId(commonFilter.getTrarId())) {
            sqls.append("  JOIN GEN_TL_TEAMTRADELINK ttl ");
            sqls.append("    ON ttl.FRP_FRT_KEYID = frt.FRT_KEYID ");
            sqls.append("   AND ttl.FRP_TRADEID   = '").append(commonFilter.getTrarId()).append("' ");
        }

        // Hierarchy (GEN_MV_FLIDHIERARCHY) only when sectionId present
        if (commonFilter.getSectionId() != null) {
            sqls.append("  JOIN GEN_MV_FLIDHIERARCHY h ");
            sqls.append("    ON c.FNLN_KEYID = h.FLID ");
        }

        sqls.append("  WHERE e.EMPM_ACTIVE = 'Y' ");

        // Cell-based restriction (equivalent to old FNLN_KEYID = (SELECT ... FROM GEN_TL_FUNCTIONALLOCN))
        if (UIUtils.isValidKeyId(commonFilter.getCellId())) {
            sqls.append("    AND c.FNLN_KEYID = (SELECT FNLN_KEYID ");
            sqls.append("                       FROM GEN_TL_FUNCTIONALLOCN ");
            sqls.append("                      WHERE FNLN_ORIGINALID = '").append(commonFilter.getCellId()).append("') ");
        }

        // Section restriction using GEN_MV_FLIDHIERARCHY + POSITION (INSTR equivalent)
        if (commonFilter.getSectionId() != null) {
            if (!UIUtils.isValidKeyId(commonFilter.getCellId())) {
                sqls.append("    AND c.FNLN_KEYID = h.FLID ");
                sqls.append("    AND POSITION( (SELECT FNLN_KEYID ");
                sqls.append("                   FROM gen_tl_functionallocn ");
                sqls.append("                  WHERE FNLN_ORIGINALID = '")
                    .append(commonFilter.getSectionId()).append("') ");
                sqls.append("                IN (h.PARENTFLIDS || '/' || h.FLID) ) > 0 ");
            } else {
                sqls.append("    AND c.FNLN_KEYID = (SELECT FNLN_KEYID ");
                sqls.append("                       FROM GEN_TL_FUNCTIONALLOCN ");
                sqls.append("                      WHERE FNLN_ORIGINALID = '").append(commonFilter.getCellId()).append("') ");
            }
        } else {
            // Original else logic when sectionId is null
            if ("false".equals(commonFilter.getUniquePos())
                    || commonFilter.getUniquePos() == null
                    || "".equals(commonFilter.getUniquePos())) {

                if (commonFilter.getCellId() != null) {
                    sqls.append("    AND c.FNLN_KEYID = (SELECT FNLN_KEYID ");
                    sqls.append("                       FROM GEN_TL_FUNCTIONALLOCN ");
                    sqls.append("                      WHERE FNLN_ORIGINALID = '").append(commonFilter.getCellId()).append("') ");
                } else {
                    sqls.append("    AND frt.FRT_FNLN_KEYID = '").append(commonFilter.getFlid()).append("' ");
                }
            } else {
                sqls.append("    AND c.FNLN_KEYID = '").append(commonFilter.getFlid()).append("' ");
                sqls.append("    AND e.EMPM_ROLEID IN (");
                sqls.append("          SELECT ETCU_ROLE_KEYID ");
                sqls.append("            FROM ENT_TL_TRGCALUNQP ");
                sqls.append("           WHERE ETCU_ETCM_KEYID = '").append(commonFilter.getKey()).append("')");
            }
        }

        CommonMessage.debugMsg(commonFilter.getTrarId() + " trade id " + commonFilter.getType());

        // Employee type and gender filters
        if (commonFilter.getEmpwiseType() != null
                && UIUtils.isValidKeyId(commonFilter.getEmpwiseType())) {
            CommonMessage.debugMsg("Employee Type" + commonFilter.getEmpwiseType());
            sqls.append("    AND e.EMPM_EMPLOYEETYPE = '")
                .append(commonFilter.getEmpwiseType()).append("' ");
        }
        if (commonFilter.getEmpch() != null) {
            sqls.append("    AND e.EMPM_GENDER = '")
                .append(commonFilter.getEmpch()).append("' ");
        }

        // Extra grid filter conditions
        sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));

        // GROUP BY for all non-aggregated columns (matching Oracle)
        sqls.append("  GROUP BY ");
        sqls.append("    e.EMPM_NAME,");
        sqls.append("    e.EMPM_CODE,");
        sqls.append("    e.EMPM_KEYID,");
        sqls.append("    e.EMPM_EMPLOYEETYPE,");
        sqls.append("    e.EMPM_GENDER,");
        sqls.append("    r.ROLE_NAME,");
        sqls.append("    c.SECT_NAME,");
        sqls.append("    c.SECT_KEYID,");
        sqls.append("    c.CELL_KEYID,");
        sqls.append("    c.CELL_NAME,");
        sqls.append("    e.EMPM_ROLEID,");
        sqls.append("    q.ETCQ_CURRENTLEVEL,");
        sqls.append("    ce.ETCE_KEYID,");
        sqls.append("    m.ETCM_KEYID,");
        sqls.append("    ce.ETCE_ETCS_KEYID,");
        sqls.append("    s.ETCS_NAME ");

        // ORDER BY as in original
        if (commonFilter.getKey() != null) {
            sqls.append("  ORDER BY ce.ETCE_KEYID ");
        } else {
            sqls.append("  ORDER BY e.EMPM_KEYID ");
        }

        sqls.append(" ) AS x ");
        CommonMessage.debugMsg("inside the get data" + sqls);
    }

    // 2) WHEN refdocid IS NOT NULL  -----------------------------------------
    else {
        CommonMessage.debugMsg("Inside the Else");
        sqls.append(" SELECT * FROM ( ");
        sqls.append("  SELECT DISTINCT ");
        sqls.append("    '' AS selctVal,");
        sqls.append("    e.EMPM_KEYID AS keyid,");
        sqls.append("    e.EMPM_CODE  AS empcode,");
        sqls.append("    e.EMPM_NAME  AS name,");

        // DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) AS ses
        sqls.append("    CASE WHEN ce.ETCE_ETCS_KEYID = '{}' THEN ' ' ELSE s.ETCS_NAME END AS ses,");

        sqls.append("    r.ROLE_NAME AS ROLENAME,");
        sqls.append("    q.ETCQ_CURRENTLEVEL          AS currLevel,");
        sqls.append("    MAX(q.ETCQ_CURRENTLEVELDATE) AS LastUpdate,");
        sqls.append("    c.SECT_KEYID AS SECTIONID,");
        sqls.append("    c.SECT_NAME  AS DMT,");
        sqls.append("    c.CELL_KEYID AS CELLID,");
        sqls.append("    c.CELL_NAME  AS JH,");
        sqls.append("    e.EMPM_ROLEID AS roleid,");

        // DECODE (ETCE_KEYID, NULL, ' ', ETCM_KEYID)
        sqls.append("    CASE WHEN ce.ETCE_KEYID IS NULL THEN ' ' ELSE m.ETCM_KEYID END,");
        sqls.append("    ce.ETCE_KEYID AS ETCEKEYID ");

        sqls.append("  FROM GEN_TL_EMPLOYEEMST e ");
        sqls.append("  JOIN GEN_TL_MOM_GROUPDTL mgrd ");
        sqls.append("    ON e.EMPM_KEYID = mgrd.MGRD_EMPM_KEYID ");
        sqls.append("  JOIN GEN_TL_MOM_GROUPMST mgrm ");
        sqls.append("    ON mgrd.MGRD_MGRM_KEYID = mgrm.MGRM_KEYID ");
        sqls.append("  JOIN GEN_TL_FNLNROLETEAM frt ");
        sqls.append("    ON frt.FRT_EMPM_KEYID = mgrd.MGRD_EMPM_KEYID ");
        sqls.append("  JOIN GEN_VW_FNLN c ");
        sqls.append("    ON frt.FRT_FNLN_KEYID = c.FNLN_KEYID ");
        sqls.append("  LEFT JOIN GEN_TL_ROLEMST r ");
        sqls.append("    ON e.EMPM_ROLEID = r.ROLE_KEYID ");

        sqls.append("  LEFT JOIN ENT_TL_TRGCALMST m ");
        sqls.append("    ON m.ETCM_KEYID = '").append(commonFilter.getKey()).append("' ");
        sqls.append("  LEFT JOIN ENT_TL_TRGCALEMP ce ");
        sqls.append("    ON ce.ETCE_ETCM_KEYID = m.ETCM_KEYID ");
        sqls.append("   AND ce.ETCE_EMPM_KEYID = e.EMPM_KEYID ");
        sqls.append("  LEFT JOIN ENT_TL_TRGCALSESSION s ");
        sqls.append("    ON s.ETCS_KEYID = ce.ETCE_ETCS_KEYID ");

        // Quad with employee & topic
        sqls.append("  LEFT JOIN ENT_TL_TRGCALQUAD q ");
        sqls.append("    ON q.ETCQ_EMPM_KEYID = mgrd.MGRD_EMPM_KEYID ");
        sqls.append("   AND q.ETCQ_TOPICID    = m.ETCM_TOPICID ");

        sqls.append("  WHERE mgrd.MGRD_MGRM_KEYID = '")
            .append(commonFilter.getRefdocid()).append("' ");
        sqls.append("    AND e.EMPM_ACTIVE = 'Y' ");

        // Extra grid conditions
        sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));

        // GROUP BY like original (using base columns)
        sqls.append("  GROUP BY ");
        sqls.append("    e.EMPM_NAME,");
        sqls.append("    e.EMPM_CODE,");
        sqls.append("    e.EMPM_KEYID,");
        sqls.append("    r.ROLE_NAME,");
        sqls.append("    c.SECT_NAME,");
        sqls.append("    c.SECT_KEYID,");
        sqls.append("    c.CELL_KEYID,");
        sqls.append("    c.CELL_NAME,");
        sqls.append("    e.EMPM_ROLEID,");
        sqls.append("    ce.ETCE_ETCS_KEYID,");
        sqls.append("    s.ETCS_NAME,");
        sqls.append("    q.ETCQ_CURRENTLEVEL,");
        sqls.append("    ce.ETCE_KEYID,");
        sqls.append("    m.ETCM_KEYID ");

        sqls.append("  ORDER BY ce.ETCE_KEYID ");

        sqls.append(" ) AS x ");
        CommonMessage.debugMsg("The Else Data::" + sqls);
    }

    // 3) COUNT + PAGINATION + DATA FETCH ------------------------------------
    String countsql = CommonFilterSqls.countSql(sqls.toString(), commonFilter.getGridFilter());
    CommonMessage.debugMsg("countsql " + countsql);
    String cntStr = dbActionTemplate.getSingleValue(countsql);
    CommonMessage.debugMsg("cntStr " + cntStr);
    int count = Integer.parseInt(cntStr);
    CommonMessage.debugMsg("count " + count);
    commonFilter.setTotalRecordCnt(count);

    GridParams gridParams = new GridParams();
    gridParams.setFromRow(commonFilter.getFromRow());
    gridParams.setToRow(commonFilter.getToRow());
    CommonMessage.debugMsg("From Row  :" + commonFilter.getFromRow());
    CommonMessage.debugMsg("To Row  :" + commonFilter.getToRow());

    String oSql = CommonFilterSqls.addPaginationParams(sqls.toString(), gridParams);
    CommonMessage.debugMsg("osql " + oSql);
    CommonMessage.debugMsg("sql get selected employee" + sqls.toString());

    List<String[]> dataList = dbActionTemplate.getDataList(oSql.toString());
    CommonMessage.debugMsg("getEmpList :" + dataList.size());

    if (commonFilter.getViewClick() == 'Y') {
        String totalCnt = cntStr;
        CommonMessage.debugMsg("totalCnt...." + totalCnt);
        boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
        if (isInteger) {
            commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
        }
    }

    return dataList;
}

// ------------------ Vignesh 28nov2025 -------------------------------------------------------------------------------------//
@Override

	public List<String[]> getAllEmployee(CommonFilter commonFilter,GridParams gridParams) throws Exception {
		String Trainingkeyid = commonFilter.getKey();
		CommonMessage.debugMsg("The Trainingkeyid"+Trainingkeyid);
		String date = "";// commonFilter.getdate();
		StringBuffer sql= new StringBuffer();
		
		sql.append(EntTlTragcalmstSql.getTrainingAttedenceEmployeeData(Trainingkeyid));
		List<String> paramValues = new ArrayList<String>();
			
		String conditionalparam=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	    String commonparam=FilterCondSql.getGridCommonParams(commonFilter);
	    paramValues.add(conditionalparam);
	    paramValues.add(commonparam);
	    String countSql=CommonFilterSqls.countSql(sql.toString(),gridParams.getGridFilters());
		String viewinfo=dbActionTemplate.getSingleValue(countSql);
	    long counts=Long.parseLong(viewinfo);
	    if( counts >0){
	    	String sb = CommonFilterSqls.addPaginationParams(sql.toString(),gridParams);
	    	gridParams.setTotalRecordCnt(counts);
	    	CommonMessage.debugMsg("The sql Data"+sql);
		    List<String[]> datacon=dbActionTemplate.getDataList(sb);
	    	return datacon;
	    }
	    throw new NoDataFoundException("No Data Found");


		
}
	

	public List<EntTlTtgCalEmpatScore> createEmployeeAttendance(List<EntTlTtgCalEmpatScore> entTlEmployeeAttendanceLink, CommonFilter commonfilter) throws Exception {

		EntTlTtgCalEmpatScoreSql entTlTtgCalEmpatScoreSql = new EntTlTtgCalEmpatScoreSql();
		EntTlTragcalquadSql entTlTtgCalquadSql =new EntTlTragcalquadSql();
		// contains dbtable,field names, Field types and related sqls  of master table
		try
		{
			     List<String> sqls = new ArrayList<String>();
			     String dateTime=CommonFunctions.dateTimeNow();
			     List <EntTlTtgCalEmpatScore> Emplist = entTlEmployeeAttendanceLink;
			     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),EntTlTtgCalEmpatScoreSql.TBL_ENT_TL_TRGCALEMPATSCORE,15,"ETA", null,null);			 
			     EntTlTragcalquad existTlTragcalquad =new EntTlTragcalquad();
				 for(EntTlTtgCalEmpatScore empEmployeeLink:Emplist)
				 {     
					 CommonMessage.debugMsg("inside the daoImpl"+empEmployeeLink.getEtcaKeyid());
					 
					
					 if(empEmployeeLink.getEtcaKeyid()!=null)
					 {
						 String result=empEmployeeLink.getEtcaResult();
							String Fail="F";
						 sqls.add(EntTlTtgCalEmpatScoreSql.getUpdateSql(entTlTtgCalEmpatScoreSql.getFtymDbFields(),empEmployeeLink.getSaveArray()));
						 
						 String sqlqery="select ETCQ_KEYID from ENT_TL_TRGCALQUAD WHERE ETCQ_EMPM_KEYID='"+empEmployeeLink.getEtcaEtceEmpmKeyid()+"' AND ETCQ_TOPICID='"+commonfilter.getTopicid()+"' AND ETCQ_L1_TRGCALID='"+commonfilter.getKey()+"'";
						// CommonMessage.debugMsg("sql"+sqlqery);
						 String quadkeyid=dbActionTemplate.getSingleValue(sqlqery);
						 EntTlTragcalquad entTlTragcalquad=QuadrantFillvalues(existTlTragcalquad);
						 entTlTragcalquad.setEtcql2Pass(result);
						 if(entTlTragcalquad.getEtcql2Pass().equals("P"))
						 {
						 entTlTragcalquad.setEtcqCurrLevel("2");
						 }
						 else{
							 entTlTragcalquad.setEtcqCurrLevel("1"); 
						 }
						 entTlTragcalquad.setEtcqCurrlevelDate(empEmployeeLink.getEtcaAddDate());
						 entTlTragcalquad.setEtcql1Date(empEmployeeLink.getEtcaAddDate());
						 entTlTragcalquad.setEtcql2Date(empEmployeeLink.getEtcaAddDate());
						 entTlTragcalquad.setEtcql3Date(empEmployeeLink.getEtcaAddDate());
						 //entTlTragcalquad.setEtcql4Date(empEmployeeLink.getEtcaAddDate());
						 entTlTragcalquad.setEtcql4Date(dateTime);
						 entTlTragcalquad.setEtcql1Pass(result);
						 
						 entTlTragcalquad.setEtcql3Pass(Fail);
						 entTlTragcalquad.setEtcql3Pass(Fail);
						 
				// }
						 entTlTragcalquad.setEtcql1Remarks("{}");
						 entTlTragcalquad.setEtcql2Remarks("{}");
						// entTlTragcalquad.setEtcqEmpmRoleid(emprole);
						 entTlTragcalquad.setEtcqEmpmKeyid(empEmployeeLink.getEtcaEtceEmpmKeyid());
						 entTlTragcalquad.setEtcql2TrgCalId(commonfilter.getKey());
						 entTlTragcalquad.setEtcql1TrgCalId(commonfilter.getKey());
						 entTlTragcalquad.setEtcqFlid(commonfilter.getFlid());
						 entTlTragcalquad.setEtcqTopicid(commonfilter.getTopicid());
						 entTlTragcalquad.setEtcqCreatedby(commonfilter.getChkExternal());
						 entTlTragcalquad.setEtcqCreatedby(commonfilter.getChkExternal());
						 entTlTragcalquad.setEtcqLocation(commonfilter.getLossId());
						 entTlTragcalquad.setEtcqKeyid(quadkeyid);
						// entTlTragcalquad.setEtcqCreatedby(commonfilter.getChkExternal());
						 //entTlTragcalquad.setEtcqLocation(commonfilter.getLossId());
						 sqls.add(EntTlTragcalquadSql.getUpdateSql(entTlTtgCalquadSql.getFtymDbFields(),entTlTragcalquad.getSaveArray()));
							
					 }
					 
					 if(empEmployeeLink.getEtcaKeyid()==null)
					 {
						String seqNo=sequenceNumber.getSequnceNumber();
						empEmployeeLink.setEtcaKeyid(seqNo);
						empEmployeeLink.setEtcaEtcmKeyid(empEmployeeLink.getEtcaEtcmKeyid());
						String result=empEmployeeLink.getEtcaResult();
						String Fail="F";
						//if(empEmployeeLink.getEtcaKeyid()==null)
						//{	
						sqls.add(EntTlTtgCalEmpatScoreSql.getInsertSql(entTlTtgCalEmpatScoreSql.getFtymDbFields(),empEmployeeLink.getSaveArray()));
					//	}
						/*else{
							sqls.add(EntTlTtgCalEmpatScoreSql.getUpdateSql(entTlTtgCalEmpatScoreSql.getFtymDbFields(),empEmployeeLink.getSaveArray()));
								
						}*/
						 EntTlTragcalquad entTlTragcalquad=QuadrantFillvalues(existTlTragcalquad);
						 String query="select EMPM_ROLEID from GEN_TL_EMPLOYEEMST WHERE EMPM_KEYID='"+empEmployeeLink.getEtcaEtceEmpmKeyid()+"'";
						 String emprole=dbActionTemplate.getSingleValue(query);
						//String topicid=" SELECT ETCM_TOPICID FROM ENT_TL_TRGCALMST WHERE ETCM_KEYID='"++"' ";
						 
						 
						 entTlTragcalquad.setEtcql2Pass(result);
						// entTlTragcalquad.setEtcqCurrLevel("2");
						 
						 if(entTlTragcalquad.getEtcql2Pass().equals("P"))
						 {
						 entTlTragcalquad.setEtcqCurrLevel("2");
						 }
						 else{
							 entTlTragcalquad.setEtcqCurrLevel("1"); 
						 }
						 entTlTragcalquad.setEtcqCurrlevelDate(empEmployeeLink.getEtcaAddDate());
						 entTlTragcalquad.setEtcql1Date(empEmployeeLink.getEtcaAddDate());
						 entTlTragcalquad.setEtcql2Date(empEmployeeLink.getEtcaAddDate());
						 entTlTragcalquad.setEtcql3Date(empEmployeeLink.getEtcaAddDate());
						 //entTlTragcalquad.setEtcql4Date(empEmployeeLink.getEtcaAddDate());
						 entTlTragcalquad.setEtcql4Date(dateTime);
						 entTlTragcalquad.setEtcql1Pass(result);
						 
						 entTlTragcalquad.setEtcql3Pass(Fail);
						 entTlTragcalquad.setEtcql3Pass(Fail);
						 
				// }
						 entTlTragcalquad.setEtcql1Remarks("{}");
						 entTlTragcalquad.setEtcql2Remarks("{}");
						 entTlTragcalquad.setEtcqEmpmRoleid(emprole);
						 entTlTragcalquad.setEtcqEmpmKeyid(empEmployeeLink.getEtcaEtceEmpmKeyid());
						 entTlTragcalquad.setEtcql2TrgCalId(commonfilter.getKey());
						 entTlTragcalquad.setEtcql1TrgCalId(commonfilter.getKey());
						 entTlTragcalquad.setEtcqFlid(commonfilter.getFlid());
						 entTlTragcalquad.setEtcqTopicid(commonfilter.getTopicid());
						 entTlTragcalquad.setEtcqCreatedby(commonfilter.getChkExternal());
						 entTlTragcalquad.setEtcqCreatedby(commonfilter.getChkExternal());
						 entTlTragcalquad.setEtcqLocation(commonfilter.getLossId());
						 GenSequenceNumber sequenceNumber1 = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),EntTlTragcalquadSql.TBL_ENT_TL_TRGCALQUAD,15,"ETQ", null,null);			 
						 String seqNo1=sequenceNumber1.getSequnceNumber();
						 entTlTragcalquad.setEtcqKeyid(seqNo1);
						 sqls.add(EntTlTragcalquadSql.getInsertSql(entTlTtgCalquadSql.getFtymDbFields(),entTlTragcalquad.getSaveArray()));
							
						
						
					 }
					 
					
						}
				 dbActionTemplate.executeStatements(sqls);
		}
		catch(BusinessApplicationExceptions e)
		{
			CommonMessage.debugMsg("Business Application   :"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage()); 
		}
		return null;
	}	
	private EntTlTragcalquad QuadrantFillvalues(EntTlTragcalquad entTlTragcalquad) {
		// TODO Auto-generated method stub
			String dateTime=CommonFunctions.dateTimeNow();
			//entTlTragcalmst.setEtcmCreatedDateTime(dateTime);
			entTlTragcalquad.setEtcfActive("Y");
			entTlTragcalquad.setEtcqCreatedon(dateTime);
			entTlTragcalquad.setEtcqModifiedon(dateTime);
			
			if(entTlTragcalquad.getEtcqCurrLevel()==null)
			{ 
				entTlTragcalquad.setEtcqCurrLevel("0");
			}
			
			if(entTlTragcalquad.getEtcqCurrlevelDate()==null)
			{ 
				entTlTragcalquad.setEtcqCurrlevelDate(dateTime);
			}
			
			if(entTlTragcalquad.getEtcqEmpmKeyid()==null)
			{ 
				entTlTragcalquad.setEtcqEmpmKeyid("{}");
			}
			
			if(entTlTragcalquad.getEtcqDmt()==null)
			{ 
				entTlTragcalquad.setEtcqDmt("{}");
			}
			
			if(entTlTragcalquad.getEtcqEmpmRoleid()==null)
			{ 
				entTlTragcalquad.setEtcqEmpmRoleid("{}");
			}
			
			if(entTlTragcalquad.getEtcqTopicid()==null)
			{ 
				entTlTragcalquad.setEtcqTopicid("{}");
			}
			
			if(entTlTragcalquad.getEtcqFlid()==null)
			{ 
				entTlTragcalquad.setEtcqFlid("{}");
			}
			
			if(entTlTragcalquad.getEtcqJh()==null)
			{ 
				entTlTragcalquad.setEtcqJh("{}");
			}
			
			if(entTlTragcalquad.getEtcql1Date()==null)
			{ 
				entTlTragcalquad.setEtcql1Date(dateTime);
			}
			
			if(entTlTragcalquad.getEtcql2Date()==null)
			{ 
				entTlTragcalquad.setEtcql2Date(dateTime);
			}
			if(entTlTragcalquad.getEtcql3Date()==null)
			{ 
				entTlTragcalquad.setEtcql3Date(dateTime);
			}
			
			if(entTlTragcalquad.getEtcql4Date()==null)
			{ 
				entTlTragcalquad.setEtcql4Date(dateTime);
			}
			
			if(entTlTragcalquad.getEtcql1Pass()==null)
			{ 
				entTlTragcalquad.setEtcql1Pass("F");
			}
			
			if(entTlTragcalquad.getEtcql2Pass()==null)
			{ 
				entTlTragcalquad.setEtcql2Pass("F");
			}
			
			if(entTlTragcalquad.getEtcql3Pass()==null)
			{ 
				entTlTragcalquad.setEtcql3Pass("F");
			}
			
			if(entTlTragcalquad.getEtcql4Pass()==null)
			{ 
				entTlTragcalquad.setEtcql4Pass("F");
			}
			
			if(entTlTragcalquad.getEtcql1Remarks()==null)
			{ 
				entTlTragcalquad.setEtcql1Remarks("{}");
			}
			
			if(entTlTragcalquad.getEtcql2Remarks()==null)
			{ 
				entTlTragcalquad.setEtcql2Remarks("{}");
			}
			
			if(entTlTragcalquad.getEtcql3Remarks()==null)
			{ 
				entTlTragcalquad.setEtcql3Remarks("{}");
			}
			
			if(entTlTragcalquad.getEtcql4Remarks()==null)
			{ 
				entTlTragcalquad.setEtcql4Remarks("{}");
			}
			
			if(entTlTragcalquad.getEtcql1TrgCalId()==null)
			{ 
				entTlTragcalquad.setEtcql1TrgCalId("{}");
			}
			
			if(entTlTragcalquad.getEtcql2TrgCalId()==null)
			{ 
				entTlTragcalquad.setEtcql2TrgCalId("{}");
			}
			
			if(entTlTragcalquad.getEtcql3Udy()==null)
			{ 
				entTlTragcalquad.setEtcql3Udy("{}");
			}
			
			if(entTlTragcalquad.getEtcql4Udy()==null)
			{ 
				entTlTragcalquad.setEtcql4Udy("{}");
			}
			
			if(entTlTragcalquad.getEtcqLocation()==null)
			{ 
				entTlTragcalquad.setEtcqLocation("{}");
			}
			
			if(entTlTragcalquad.getEtcqTempfield1()==null)
			{ 
				entTlTragcalquad.setEtcqTempfield1("-");
			}
			
			if(entTlTragcalquad.getEtcqTempfield2()==null)
			{ 
				entTlTragcalquad.setEtcqTempfield2("-");
			}
			
			if(entTlTragcalquad.getEtcqTempfield3()==null)
			{ 
				entTlTragcalquad.setEtcqTempfield3("-");
			}
			
			if(entTlTragcalquad.getEtcqTempfield4()==null)
			{ 
				entTlTragcalquad.setEtcqTempfield4("-");
			}
			
			if(entTlTragcalquad.getEtcqTempfield5()==null)
			{ 
				entTlTragcalquad.setEtcqTempfield5("-");
			}
			
		return entTlTragcalquad;
	}
	@Override
	public List<EntTlTragcalmst> GrdBsdTrgCalUpdate(
			List<EntTlTragcalmst> trngCalfillValues) throws Exception {
		// TODO Auto-generated method stub
					// TODO Auto-generated method stub
			EntTlTragcalmstSql entTlTragcalmstSql = new EntTlTragcalmstSql(); 
			try
			{
				
				List<EntTlTragcalmst> trgCalList=trngCalfillValues;
				String seqIdentfr=""; 
				for(EntTlTragcalmst getElementId :trgCalList){
					String location = null;
				 	seqIdentfr = EntTlTragcalmstSql.TBL_ENT_TL_TRGCALMST;

				 	
					
				}
				     List<String> sqls = new ArrayList<String>();
				     List <EntTlTragcalmst> methodslist = trngCalfillValues;
					 for(EntTlTragcalmst abnormalityLink:methodslist)
					 {
							
	                        sqls.add(EntTlTragcalmstSql.getUpdateSql(entTlTragcalmstSql.getFtymDbFields(), abnormalityLink.getSaveArray()));//  getInsertSql(entTlTragcalmstSql.getFtymDbFields(), abnormalityLink.getSaveArray()));
	                      //  MultipleactionPlanEntry(list,"I");
	                       
	                        
					 }
					 
					 
					
					 dbActionTemplate.executeStatements(sqls);
					 return trngCalfillValues;
			}
			catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("Business Application   :"+e.getMessage());
				throw new BusinessApplicationExceptions(e.getMessage()); 
			}
			//return trngCalfillValues;

	}
	@Override
	public void DeleteCal(String keyid) throws Exception {
		// TODO Auto-generated method stub
		List<String > sqls = new ArrayList<String>();
		String sqlFacu=" DELETE FROM ENT_TL_TRGFACULTY WHERE ETCF_ETCM_KEYID='"+keyid+"' ";
		String sqlUniq=" DELETE FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID='"+keyid+"' ";
		String session=" DELETE FROM ENT_TL_TRGCALSESSION WHERE ETCS_ETCM_KEYID='"+keyid+"'";
		String EmplPln=" DELETE FROM ENT_TL_TRGCALEMP WHERE ETCE_ETCM_KEYID='"+keyid+"'";
		String sqlempAtt=" DELETE FROM ENT_TL_TRGCALEMPATSCORE WHERE ETCA_ETCM_KEYID='"+keyid+"'";
		String sqlempQuad=" DELETE FROM ENT_TL_TRGCALQUAD WHERE ETCQ_L1_TRGCALID='"+keyid+"'";		
		String sqlCalMst=" DELETE FROM ENT_TL_TRGCALMST WHERE ETCM_KEYID='"+keyid+"'";

		sqls.add(sqlempAtt);
		sqls.add(sqlempQuad);
		sqls.add(EmplPln);
		sqls.add(session);
		sqls.add(sqlUniq);
		sqls.add(sqlFacu);
		sqls.add(sqlCalMst);
		dbActionTemplate.executeStatements(sqls);
	}
	
	// ------------ vignesh 24NOV2025 -----------------------------//
	
//	public List<String[]> getListTrgCalendarModify(CommonFilter commonFilter,
//			GridParams gridParams) throws Exception {
//		try
//		{    
//		CommonMessage.debugMsg("getehsAuditParamterGrid sql..");
//		
//
//		List <String>  paramvalues = new ArrayList<String>();
//		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
//		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
//		String mode=commonFilter.getType();
//	    CommonMessage.debugMsg("value of mode"+commonFilter.getType());
//
//		
//	    if(UIUtils.isValidKeyId(mode)){
//			condParms +="Mode="+mode+";";
//		}
//	    CommonMessage.debugMsg("condParms"+condParms);
//	    CommonMessage.debugMsg("commonParams"+commonParams);
//		paramvalues.add(condParms);
//		paramvalues.add(commonParams);
//		String condSql="  ";
//		
//		if(UIUtils.isValidKeyId(commonFilter.getKey())){
//			condSql+=" AND ETCM_KEYID='"+commonFilter.getKey()+"'";
//		}		
//		
//		else{
//		if(UIUtils.isValidKeyId(commonFilter.getSectionId())){
//		 condSql+= "  AND ETCM_DMT='"+commonFilter.getSectionId()+"'";
//		}
//		
//		if(UIUtils.isValidKeyId(commonFilter.getCellId())){
//			condSql+= "  AND ETCM_JH='"+commonFilter.getCellId()+"'";
//			}
//		if(UIUtils.isValidKeyId(commonFilter.getTrarId())){
//			condSql+= "  AND ETCM_TRAININGFUNCTION='"+commonFilter.getTrarId()+"'";
//			}
//		if(UIUtils.isValidDate(commonFilter.getFromDate())&&UIUtils.isValidDate(commonFilter.getToDate())){
//			 condSql+=" AND ETCM_CALDATE BETWEEN '"+commonFilter.getFromDate()+ "' AND '"+commonFilter.getToDate()+"'";
//		}
//		if(UIUtils.isValidKeyId(commonFilter.getUniquePos())){
//			condSql+=" AND ETCM_KEYID IN ( SELECT  ETCU_ETCM_KEYID FROM ENT_TL_TRGCALUNQP WHERE ETCU_ROLE_KEYID='"+commonFilter.getUniquePos()+"') ";
//		}
//		}
//		if(UIUtils.isValidKeyId(commonFilter.getFlid())){
//			condSql+=" AND ETCM_FLID =FLID "+
//             " AND INSTR(PARENTFLIDS "+
//          " ||'/' "+
//          " ||FLID,'"+commonFilter.getFlid()+"')>0";
//		}
//		
//		CommonMessage.debugMsg("commonFilter.getUniquePos()commonFilter.getUniquePos()"+commonFilter.getUniquePos());
//		
//		List<String[]> getDataList=null;
//		StringBuilder sb=new StringBuilder();		
//		
//		 sb.append( " SELECT DISTINCT ETCM_KEYID,  ETCM_DMT,  ETCM_JH,  ETCM_FLID,  ETCM_LOCATION,  ETCM_CREATEDATETIME,  ETCM_ANCHOREDBY,  ETCM_ANCHOREDBY ANCHOREDBYid, ");
//		 sb.append( "  TOPI_NAME,  ETCM_TOPICID,  TCAT_NAME,  ETCM_TOPICCATEGORY,  DECODE(ETCM_FUNCTION,'NB','Need Basic','SD','Section D','SI','Skill Index','KU','Knowledge Upgradation','KSA','KSA (GAP Based)','RT','Refresher Training','EHS','EHS' )AS IDENFIEDTHG, ");
//		 sb.append( "  ETCM_FUNCTION,  TRDM_NAME,  ETCM_TRAININGFUNCTION,  VENU_NAME ,  ETCM_VENUE,  DECODE(ETCM_GENERAL,'Y','General',DECODE(ETCM_UNIQUEPOS,'Y','Unique Position',DECODE(ETCM_MSD,'Y','MSD',''))) AS UNIQPOSE,  DECODE(ETCM_GENERAL,'Y','GN',DECODE(ETCM_UNIQUEPOS,'Y','UQ',DECODE(ETCM_MSD,'Y','MS','')))    AS UNIQPOSEID,  ");
//		// sb.append( " (SELECT LISTAGG (ROLE_NAME, ',')  WITHIN GROUP (ORDER BY ROLE_NAME) FROM GEN_TL_ROLEMST,ENT_TL_TRGCALUNQP WHERE  ETCU_ROLE_KEYID=ROLE_KEYID(+)  AND ETCU_ETCM_KEYID=ETCM_KEYID ) ROLE_NAME  ,");
//		 sb.append( "  ETCM_CALDATE, ETCM_PERMITTEDSTRENGTH,  ETCM_MAX_DURATION, DECODE(ETCM_ASSESSMENTREQUIRED,'Y','YES','NO'), ETCM_ASSESSMENTREQUIRED, DECODE(ETCM_MATERIALREADY,'Y','YES','NO'),  ETCM_MATERIALREADY, DECODE(ETCM_MARKBASED,'Y','YES','NO'), ETCM_MARKBASED, (SELECT LISTAGG (ROLE_NAME, ',')  WITHIN GROUP (ORDER BY ROLE_NAME) FROM GEN_TL_ROLEMST,ENT_TL_TRGCALUNQP WHERE  ETCU_ROLE_KEYID=ROLE_KEYID(+)  AND ETCU_ETCM_KEYID=ETCM_KEYID ) ROLE_NAME  , '' AS UNIQPOS,  '' AS EMPLOYEEADD, ''  AS EMPATTEDNCE, ");
//		
//		 sb.append("   (SELECT LISTAGG (EMPM_NAME, ',')  WITHIN GROUP (ORDER BY EMPM_NAME) FROM GEN_TL_EMPLOYEEMST,ENT_TL_TRGCALEMP WHERE  ");
//		 sb.append("   ETCE_EMPM_KEYID=EMPM_KEYID AND ETCE_ETCM_KEYID=ETCM_KEYID ) PLANNEDEMPM_NAME, ");
//			
//		 sb.append( "  DECODE(ETCM_TEMPFIELD6,'Y','YES','NO') AS ASSEMNTCOMPL, ETCM_TEMPFIELD6, DECODE(ETCM_CHKCOMPLETED,'Y','YES','NO')AS TRNCOMPL, ");
//		 sb.append( " ETCM_CHKCOMPLETED   AS TRNCOMPLID,  DECODE(ETCM_CHKCOMPLETED,'Y',ETCM_COMPLETEDDATE,'') AS COMPLTDATE,  EMPM_NAME  ||'-'  ||EMPM_CODE AS COMPLETEDBY ,  ETCM_COMPLETEDBY, ");
//		 sb.append( " ETCM_RATING,  ETCM_RATING AS RatingId,  ETCM_COMMENTS,  '' AS FILEMGR,NVL(PLANED,0),  NVL(ATTEND,0),  NVL(ROUND((ATTEND/DECODE(PLANED,0,1,PLANED)*100),2),0) ADHERENCE,  NVL(ATTEND*ETCM_MAX_DURATION,0) as MANHOURSE ");
//		 sb.append( " FROM ENT_TL_TRGCALMST,  ENT_TL_TOPICMST,  ENT_TL_VENUEMST,  ENT_TL_TOPICCATEGORYMST,  GEN_TL_TRADEMST,  GEN_TL_EMPLOYEEMST,  GEN_MV_FLIDHIERARCHY,  (SELECT COUNT(ETCE_ETCM_KEYID) AS PLANED,  ETCE_ETCM_KEYID  FROM ENT_TL_TRGCALEMP,  ENT_TL_TRGCALMST WHERE ETCE_ETCM_KEYID=ETCM_KEYID  GROUP BY ETCE_ETCM_KEYID ), ");
//		 sb.append( "  (SELECT COUNT(ETCA_ETCM_KEYID) ATTEND,  ETCA_ETCM_KEYID  FROM ENT_TL_TRGCALEMPATSCORE,   ENT_TL_TRGCALMST  WHERE ETCA_ETCM_KEYID=ETCM_KEYID AND ETCA_PRSENTABSENT='P' GROUP BY ETCA_ETCM_KEYID ) ");
//		 sb.append( " WHERE TOPI_KEYID(+)    =ETCM_TOPICID AND TCAT_KEYID (+)     =ETCM_TOPICCATEGORY AND TRDM_KEYID(+)      =ETCM_TRAININGFUNCTION AND EMPM_KEYID(+)      =ETCM_COMPLETEDBY ");
//		 sb.append( " AND VENU_KEYID(+)      =ETCM_VENUE AND ETCE_ETCM_KEYID(+) = ETCM_KEYID AND ETCA_ETCM_KEYID(+) = ETCM_KEYID ");
//		 sb.append( " AND ETCM_CHKCOMPLETED  ='N' "+condSql);
//		 sb.append( " GROUP BY ETCM_KEYID, ETCM_DMT,  ETCM_JH,  ETCM_FLID,  ETCM_CREATEDATETIME,  ETCM_ANCHOREDBY,  ETCM_ANCHOREDBY ,  TOPI_NAME,  ETCM_TOPICID,  TCAT_NAME,  ETCM_TOPICCATEGORY,  ETCM_FUNCTION,  TRDM_NAME,  ETCM_TRAININGFUNCTION,  VENU_NAME ,  ETCM_VENUE,  ETCM_GENERAL,  ETCM_CALDATE,  ETCM_PERMITTEDSTRENGTH,  ETCM_MAX_DURATION, ");
//		 sb.append( " ETCM_ASSESSMENTREQUIRED,  ETCM_MATERIALREADY,  ETCM_MARKBASED,  ETCM_TEMPFIELD6,  ETCM_CHKCOMPLETED ,  ETCM_CHKCOMPLETED, ");
//		 sb.append( " PLANED,  ATTEND,  ETCA_ETCM_KEYID,  ETCE_ETCM_KEYID,  ETCM_COMPLETEDDATE,  EMPM_NAME ,  EMPM_CODE ,  ETCM_COMPLETEDBY, ");
//		 sb.append( " ETCM_RATING,  ETCM_COMMENTS,  ETCM_UNIQUEPOS,  ETCM_MSD,  ETCM_LOCATION ORDER BY ETCM_KEYID DESC ");
//		
//		CommonMessage.debugMsg(sb+" query in sid the DAO Impl");
//		
//		List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sb.toString(),null);
//		
////		 getDataList= dbActionTemplate.processFunctionCalls("ENT_NEW_REPORTS.ENT_TL_NEWTRAININGCALENTRYGRD", paramvalues); 
//		// getDataList= dbActionTemplate.processFunctionCalls("ENT_NEW_REPORTS.ENT_TL_NEWTRAININGCALMSTGRD", paramvalues); 
//
//		if( commonFilter.getViewClick() == 'Y'){
//		     String totalCnt = paramvalues.get(0);
//		     //CommonMessage.debugMsg("totalCnt...."+totalCnt);
//		     boolean isInteger = Pattern.matches("^\\d*$", totalCnt);		     
//		    CommonMessage.debugMsg("IS isInteger"+isInteger);
//		     if(  isInteger ){
//		     	
//		     	commonFilter.setTotalRecordCnt((int) Long.parseLong(totalCnt));
//		      }
//		 } 
//		return  dataList ;
//	
//	
//}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//			
//		}
	
	
	 // -------vignesh chnaging modify for pagination -------//
	@Override
	public List<String[]> getListTrgCalendarModify(CommonFilter commonFilter,
	                                               GridParams gridParams) throws Exception {
	  try {
	    CommonMessage.debugMsg("getehsAuditParamterGrid sql..");

	    List<String> paramvalues = new ArrayList<String>();
	    String condParms    = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	    String mode         = commonFilter.getType();
	    CommonMessage.debugMsg("value of mode 123 " + commonFilter.getType());
	    CommonMessage.debugMsg("check fromtorow parameter" + commonParams);

	    if (UIUtils.isValidKeyId(mode)) {
	      condParms += "Mode=" + mode + ";";
	    }
	    CommonMessage.debugMsg("condParms" + condParms);
	    CommonMessage.debugMsg("commonParams" + commonParams);

	    paramvalues.add(condParms);
	    paramvalues.add(commonParams);

	    // -----------------------
	    // Build WHERE conditions
	    // -----------------------
	    String condSql = "  ";

	    if (UIUtils.isValidKeyId(commonFilter.getKey())) {
	      condSql += " AND ETCM_KEYID = '" + commonFilter.getKey() + "'";
	    } else {
	      if (UIUtils.isValidKeyId(commonFilter.getSectionId())) {
	        condSql += " AND ETCM_DMT = '" + commonFilter.getSectionId() + "'";
	      }
	      if (UIUtils.isValidKeyId(commonFilter.getCellId())) {
	        condSql += " AND ETCM_JH = '" + commonFilter.getCellId() + "'";
	      }
	      if (UIUtils.isValidKeyId(commonFilter.getTrarId())) {
	        condSql += " AND ETCM_TRAININGFUNCTION = '" + commonFilter.getTrarId() + "'";
	      }
	      if (UIUtils.isValidDate(commonFilter.getFromDate())
	          && UIUtils.isValidDate(commonFilter.getToDate())) {
	        // Keep original literal style to avoid changing behavior
	        condSql += " AND ETCM_CALDATE BETWEEN '" + commonFilter.getFromDate()
	                + "' AND '" + commonFilter.getToDate() + "'";
	      }
	      if (UIUtils.isValidKeyId(commonFilter.getUniquePos())) {
	        condSql += " AND ETCM_KEYID IN ( "
	                + "  SELECT ETCU_ETCM_KEYID FROM ENT_TL_TRGCALUNQP "
	                + "   WHERE ETCU_ROLE_KEYID = '" + commonFilter.getUniquePos() + "')";
	      }
	    }

	    // FLID condition – Oracle INSTR -> PostgreSQL position()
	    if (UIUtils.isValidKeyId(commonFilter.getFlid())) {
	      condSql += " AND ETCM_FLID = FLID"
	              + " AND position('" + commonFilter.getFlid()
	              + "' in (PARENTFLIDS || '/' || FLID)) > 0";
	    }

	    CommonMessage.debugMsg("commonFilter.getUniquePos()commonFilter.getUniquePos()" + commonFilter.getUniquePos());

	    // -----------------------
	    // SELECT column list
	    // -----------------------
	    StringBuilder cols = new StringBuilder();
	    cols.append("  etcm.etcm_keyid          AS ETCM_KEYID, ")
	        .append("  etcm.etcm_dmt            AS ETCM_DMT, ")
	        .append("  etcm.etcm_jh             AS ETCM_JH, ")
	        .append("  etcm.etcm_flid           AS ETCM_FLID, ")
	        .append("  etcm.etcm_location       AS ETCM_LOCATION, ")
	        .append("  etcm.etcm_createdatetime AS ETCM_CREATEDATETIME, ")
	        .append("  etcm.etcm_anchoredby     AS ETCM_ANCHOREDBY, ")
	        .append("  etcm.etcm_anchoredby     AS ANCHOREDBYid, ")
	        .append("  topi.topi_name           AS TOPI_NAME, ")
	        .append("  etcm.etcm_topicid        AS ETCM_TOPICID, ")
	        .append("  tcat.tcat_name           AS TCAT_NAME, ")
	        .append("  etcm.etcm_topiccategory  AS ETCM_TOPICCATEGORY, ")

	        // DECODE(ETCM_FUNCTION,...) -> CASE
	        .append("  CASE etcm.etcm_function ")
	        .append("    WHEN 'NB'  THEN 'Need Basic' ")
	        .append("    WHEN 'SD'  THEN 'Section D' ")
	        .append("    WHEN 'SI'  THEN 'Skill Index' ")
	        .append("    WHEN 'KU'  THEN 'Knowledge Upgradation' ")
	        .append("    WHEN 'KSA' THEN 'KSA (GAP Based)' ")
	        .append("    WHEN 'RT'  THEN 'Refresher Training' ")
	        .append("    WHEN 'EHS' THEN 'EHS' ")
	        .append("    ELSE NULL ")
	        .append("  END AS IDENFIEDTHG, ")

	        .append("  etcm.etcm_function         AS ETCM_FUNCTION, ")
	        .append("  trdm.trdm_name             AS TRDM_NAME, ")
	        .append("  etcm.etcm_trainingfunction AS ETCM_TRAININGFUNCTION, ")
	        .append("  venu.venu_name             AS VENU_NAME, ")
	        .append("  etcm.etcm_venue            AS ETCM_VENUE, ")

	        // UNIQPOSE
	        .append("  CASE ")
	        .append("    WHEN etcm.etcm_general = 'Y'    THEN 'General' ")
	        .append("    WHEN etcm.etcm_uniquepos = 'Y'  THEN 'Unique Position' ")
	        .append("    WHEN etcm.etcm_msd = 'Y'        THEN 'MSD' ")
	        .append("    ELSE '' ")
	        .append("  END AS UNIQPOSE, ")

	        // UNIQPOSEID
	        .append("  CASE ")
	        .append("    WHEN etcm.etcm_general = 'Y'    THEN 'GN' ")
	        .append("    WHEN etcm.etcm_uniquepos = 'Y'  THEN 'UQ' ")
	        .append("    WHEN etcm.etcm_msd = 'Y'        THEN 'MS' ")
	        .append("    ELSE '' ")
	        .append("  END AS UNIQPOSEID, ")

	        .append("  etcm.etcm_caldate           AS ETCM_CALDATE, ")
	        .append("  etcm.etcm_permittedstrength AS ETCM_PERMITTEDSTRENGTH, ")
	        .append("  etcm.etcm_max_duration      AS ETCM_MAX_DURATION, ")

	        // DECODE(ETCM_ASSESSMENTREQUIRED,'Y','YES','NO')
	        .append("  CASE etcm.etcm_assessmentrequired ")
	        .append("    WHEN 'Y' THEN 'YES' ")
	        .append("    ELSE 'NO' ")
	        .append("  END AS ASSESSMENTREQUIREDTXT, ")
	        .append("  etcm.etcm_assessmentrequired AS ETCM_ASSESSMENTREQUIRED, ")

	        // DECODE(ETCM_MATERIALREADY,'Y','YES','NO')
	        .append("  CASE etcm.etcm_materialready ")
	        .append("    WHEN 'Y' THEN 'YES' ")
	        .append("    ELSE 'NO' ")
	        .append("  END AS MATERIALREADYTXT, ")
	        .append("  etcm.etcm_materialready AS ETCM_MATERIALREADY, ")

	        // DECODE(ETCM_MARKBASED,'Y','YES','NO')
	        .append("  CASE etcm.etcm_markbased ")
	        .append("    WHEN 'Y' THEN 'YES' ")
	        .append("    ELSE 'NO' ")
	        .append("  END AS MARKBASEDTXT, ")
	        .append("  etcm.etcm_markbased AS ETCM_MARKBASED, ")

	        // LISTAGG(ROLE_NAME,...) -> string_agg(...)
	        .append("  (SELECT string_agg(r.role_name, ',' ORDER BY r.role_name) ")
	        .append("     FROM ent_tl_trgcalunqp u ")
	        .append("     LEFT JOIN gen_tl_rolemst r ")
	        .append("       ON u.etcu_role_keyid = r.role_keyid ")
	        .append("    WHERE u.etcu_etcm_keyid = etcm.etcm_keyid ")
	        .append("  ) AS ROLE_NAME, ")

	        .append("  '' AS UNIQPOS, ")
	        .append("  '' AS EMPLOYEEADD, ")
	        .append("  '' AS EMPATTEDNCE, ")

	        // LISTAGG(EMPM_NAME,...) -> string_agg(...)
	        .append("  (SELECT string_agg(e2.empm_name, ',' ORDER BY e2.empm_name) ")
	        .append("     FROM gen_tl_employeemst e2 ")
	        .append("     JOIN ent_tl_trgcalemp ce ")
	        .append("       ON ce.etce_empm_keyid = e2.empm_keyid ")
	        .append("    WHERE ce.etce_etcm_keyid = etcm.etcm_keyid ")
	        .append("  ) AS PLANNEDEMPM_NAME, ")

	        // DECODE(ETCM_TEMPFIELD6,'Y','YES','NO')
	        .append("  CASE etcm.etcm_tempfield6 ")
	        .append("    WHEN 'Y' THEN 'YES' ")
	        .append("    ELSE 'NO' ")
	        .append("  END AS ASSEMNTCOMPL, ")
	        .append("  etcm.etcm_tempfield6 AS ETCM_TEMPFIELD6, ")

	        // DECODE(ETCM_CHKCOMPLETED,'Y','YES','NO')
	        .append("  CASE etcm.etcm_chkcompleted ")
	        .append("    WHEN 'Y' THEN 'YES' ")
	        .append("    ELSE 'NO' ")
	        .append("  END AS TRNCOMPL, ")

	        .append("  etcm.etcm_chkcompleted AS TRNCOMPLID, ")

	        // DECODE(ETCM_CHKCOMPLETED,'Y',ETCM_COMPLETEDDATE,'') -> keep as date/null
	        .append("  CASE ")
	        .append("    WHEN etcm.etcm_chkcompleted = 'Y' THEN etcm.etcm_completeddate ")
	        .append("    ELSE NULL ")
	        .append("  END AS COMPLTDATE, ")

	        .append("  (emp.empm_name || '-' || emp.empm_code) AS COMPLETEDBY, ")
	        .append("  etcm.etcm_completedby AS ETCM_COMPLETEDBY, ")

	        .append("  etcm.etcm_rating AS ETCM_RATING, ")
	        .append("  etcm.etcm_rating AS RatingId, ")
	        .append("  etcm.etcm_comments AS ETCM_COMMENTS, ")

	        .append("  '' AS FILEMGR, ")

	        // NVL(PLANED,0), NVL(ATTEND,0)
	        .append("  COALESCE(plan.planed, 0) AS PLANED, ")
	        .append("  COALESCE(att.attend, 0) AS ATTEND, ")

	        // NVL(ROUND((ATTEND/DECODE(PLANED,0,1,PLANED)*100),2),0)
	        .append("  COALESCE(ROUND((COALESCE(att.attend,0)::numeric / ")
	        .append("    (CASE WHEN COALESCE(plan.planed,0) = 0 ")
	        .append("          THEN 1 ")
	        .append("          ELSE COALESCE(plan.planed,0) ")
	        .append("     END)::numeric) * 100, 2), 0) AS ADHERENCE, ")

	        // NVL(ATTEND*ETCM_MAX_DURATION,0)
	        .append("  COALESCE(att.attend * etcm.etcm_max_duration, 0) AS MANHOURSE ");

	    // -----------------------
	    // FROM / JOINs
	    // -----------------------
	    StringBuilder fromJoins = new StringBuilder();
	    fromJoins.append(" FROM ent_tl_trgcalmst etcm ")
	             .append(" LEFT JOIN ent_tl_topicmst topi ")
	             .append("        ON topi.topi_keyid = etcm.etcm_topicid ")
	             .append(" LEFT JOIN ent_tl_topiccategorymst tcat ")
	             .append("        ON tcat.tcat_keyid = etcm.etcm_topiccategory ")
	             .append(" LEFT JOIN gen_tl_trademst trdm ")
	             .append("        ON trdm.trdm_keyid = etcm.etcm_trainingfunction ")
	             .append(" LEFT JOIN gen_tl_employeemst emp ")
	             .append("        ON emp.empm_keyid = etcm.etcm_completedby ")
	             .append(" LEFT JOIN ent_tl_venuemst venu ")
	             .append("        ON venu.venu_keyid = etcm.etcm_venue ")
	             // FLID hierarchy as in your original (no alias change)
	             .append(" JOIN gen_mv_flidhierarchy ")
	             .append("        ON flid = etcm.etcm_flid ")

	             // PLANED counts
	             .append(" LEFT JOIN ( ")
	             .append("    SELECT etce_etcm_keyid, COUNT(*) AS planed ")
	             .append("    FROM ent_tl_trgcalemp ")
	             .append("    GROUP BY etce_etcm_keyid ")
	             .append(" ) plan ")
	             .append("   ON plan.etce_etcm_keyid = etcm.etcm_keyid ")

	             // ATTEND counts
	             .append(" LEFT JOIN ( ")
	             .append("    SELECT etca_etcm_keyid, COUNT(*) AS attend ")
	             .append("    FROM ent_tl_trgcalempatscore ")
	             .append("    WHERE etca_prsentabsent = 'P' ")
	             .append("    GROUP BY etca_etcm_keyid ")
	             .append(" ) att ")
	             .append("   ON att.etca_etcm_keyid = etcm.etcm_keyid ");

	    // -----------------------
	    // WHERE (keep your filters)
	    // -----------------------
	    StringBuilder whereClause = new StringBuilder();
	    whereClause.append(" WHERE etcm.etcm_chkcompleted = 'N' ")
	               .append(condSql);

	    // -----------------------
	    // Core SQL (no ORDER BY)
	    // -----------------------
	    String coreSql = "SELECT DISTINCT " + cols.toString() + fromJoins.toString() + whereClause.toString();

	    // -----------------------
	    // Total count for jqGrid
	    // -----------------------
	    int totalCnt = 0;
	    try {
	      String countSql = "SELECT COUNT(*) FROM (" + coreSql + ") x";
	      String cntStr = dbActionTemplate.getSingleValue(countSql);
	      if (cntStr != null && cntStr.trim().length() > 0) {
	        totalCnt = Integer.parseInt(cntStr.trim());
	      }
	    } catch (Exception ex) {
	      CommonMessage.debugMsg("WARN: COUNT failed: " + ex.getMessage());
	    }

	    // -----------------------
	    // Paging from FROMTOROW
	    // -----------------------
	    int rowsPerPage = 100;  // fixed page size (matches your UI default)
	    int startRN     = 1;    // 1-based

	    if (commonParams != null) {
	      int idx = commonParams.indexOf("FROMTOROW=");
	      if (idx >= 0) {
	        try {
	          String tail = commonParams.substring(idx + "FROMTOROW=".length());
	          String val  = tail.split(";")[0];           // e.g., "201 AND 1000"
	          String[] ab = val.split("AND");
	          int a = Integer.parseInt(ab[0].trim());     // start
	          int b = Integer.parseInt(ab[1].trim());     // end or size (ignored for LIMIT, but ok to parse)
	          if (a > 0) startRN = a;
	          // We intentionally keep LIMIT at 100 to avoid fetching huge ranges (same as View method behavior)
	        } catch (Exception ignore) {
	          // keep defaults
	        }
	      }
	    }

	    long offset = Math.max(0, startRN - 1);
	    int  limit  = rowsPerPage;

	    CommonMessage.debugMsg("Paging -> startRN=" + startRN + " limit(size)=" + limit + " offset=" + offset);

	    // -----------------------
	    // Final SQL with ORDER + LIMIT/OFFSET
	    // -----------------------
	    String ordered  = coreSql + " ORDER BY etcm.etcm_keyid DESC";
	    String pagedSql = ordered + " LIMIT " + limit + " OFFSET " + offset;

	    CommonMessage.debugMsg(pagedSql + " query in sid the DAO Impl");

	    // -----------------------
	    // Fetch one page only
	    // -----------------------
	    List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(pagedSql, null);

	    // Set total count for jqGrid
	    if (commonFilter.getViewClick() == 'Y') {
	      commonFilter.setTotalRecordCnt(totalCnt);
	      CommonMessage.debugMsg(commonFilter.getTotalRecordCnt() + " tc - get total count");
	      CommonMessage.debugMsg(totalCnt + " tc total count");
	    }

	    return dataList;

	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	  return null;
	}

	
//	public List<String[]> getListTrgCalendarModify(CommonFilter commonFilter,
//            GridParams gridParams) throws Exception {
//try {
//CommonMessage.debugMsg("getehsAuditParamterGrid sql..");
//
//List<String> paramvalues = new ArrayList<String>();
//String condParms    = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
//String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
//String mode         = commonFilter.getType();
//CommonMessage.debugMsg("value of mode 123 " + commonFilter.getType());
//
//if (UIUtils.isValidKeyId(mode)) {
//condParms += "Mode=" + mode + ";";
//}
//CommonMessage.debugMsg("condParms" + condParms);
//CommonMessage.debugMsg("commonParams" + commonParams);
//
//paramvalues.add(condParms);
//paramvalues.add(commonParams);
//
//String condSql = "  ";
//
//if (UIUtils.isValidKeyId(commonFilter.getKey())) {
//condSql += " AND ETCM_KEYID = '" + commonFilter.getKey() + "'";
//} else {
//if (UIUtils.isValidKeyId(commonFilter.getSectionId())) {
//condSql += " AND ETCM_DMT = '" + commonFilter.getSectionId() + "'";
//}
//
//if (UIUtils.isValidKeyId(commonFilter.getCellId())) {
//condSql += " AND ETCM_JH = '" + commonFilter.getCellId() + "'";
//}
//
//if (UIUtils.isValidKeyId(commonFilter.getTrarId())) {
//condSql += " AND ETCM_TRAININGFUNCTION = '" + commonFilter.getTrarId() + "'";
//}
//
//if (UIUtils.isValidDate(commonFilter.getFromDate())
//&& UIUtils.isValidDate(commonFilter.getToDate())) {
//condSql += " AND ETCM_CALDATE BETWEEN '" + commonFilter.getFromDate() +
//"' AND '" + commonFilter.getToDate() + "'";
//}
//
//if (UIUtils.isValidKeyId(commonFilter.getUniquePos())) {
//condSql += " AND ETCM_KEYID IN ( " +
//"  SELECT ETCU_ETCM_KEYID FROM ENT_TL_TRGCALUNQP " +
//"   WHERE ETCU_ROLE_KEYID = '" + commonFilter.getUniquePos() + "')" ;
//}
//}
//
//// FLID condition – Oracle INSTR -> PostgreSQL position()
//if (UIUtils.isValidKeyId(commonFilter.getFlid())) {
//condSql += " AND ETCM_FLID = FLID" +
//" AND position('" + commonFilter.getFlid() +
//"' in (PARENTFLIDS || '/' || FLID)) > 0";
//}
//
//CommonMessage.debugMsg("commonFilter.getUniquePos()commonFilter.getUniquePos()"
//+ commonFilter.getUniquePos());
//
//StringBuilder sb = new StringBuilder();
//
//sb.append(" SELECT DISTINCT ");
//sb.append("  etcm.etcm_keyid          AS ETCM_KEYID, ");
//sb.append("  etcm.etcm_dmt            AS ETCM_DMT, ");
//sb.append("  etcm.etcm_jh             AS ETCM_JH, ");
//sb.append("  etcm.etcm_flid           AS ETCM_FLID, ");
//sb.append("  etcm.etcm_location       AS ETCM_LOCATION, ");
//sb.append("  etcm.etcm_createdatetime AS ETCM_CREATEDATETIME, ");
//sb.append("  etcm.etcm_anchoredby     AS ETCM_ANCHOREDBY, ");
//sb.append("  etcm.etcm_anchoredby     AS ANCHOREDBYid, ");
//sb.append("  topi.topi_name           AS TOPI_NAME, ");
//sb.append("  etcm.etcm_topicid        AS ETCM_TOPICID, ");
//sb.append("  tcat.tcat_name           AS TCAT_NAME, ");
//sb.append("  etcm.etcm_topiccategory  AS ETCM_TOPICCATEGORY, ");
//
//// DECODE(ETCM_FUNCTION,...) -> CASE
//sb.append("  CASE etcm.etcm_function ");
//sb.append("    WHEN 'NB'  THEN 'Need Basic' ");
//sb.append("    WHEN 'SD'  THEN 'Section D' ");
//sb.append("    WHEN 'SI'  THEN 'Skill Index' ");
//sb.append("    WHEN 'KU'  THEN 'Knowledge Upgradation' ");
//sb.append("    WHEN 'KSA' THEN 'KSA (GAP Based)' ");
//sb.append("    WHEN 'RT'  THEN 'Refresher Training' ");
//sb.append("    WHEN 'EHS' THEN 'EHS' ");
//sb.append("    ELSE NULL ");
//sb.append("  END AS IDENFIEDTHG, ");
//
//sb.append("  etcm.etcm_function        AS ETCM_FUNCTION, ");
//sb.append("  trdm.trdm_name            AS TRDM_NAME, ");
//sb.append("  etcm.etcm_trainingfunction AS ETCM_TRAININGFUNCTION, ");
//sb.append("  venu.venu_name            AS VENU_NAME, ");
//sb.append("  etcm.etcm_venue           AS ETCM_VENUE, ");
//
//// UNIQPOSE
//sb.append("  CASE ");
//sb.append("    WHEN etcm.etcm_general = 'Y'    THEN 'General' ");
//sb.append("    WHEN etcm.etcm_uniquepos = 'Y'  THEN 'Unique Position' ");
//sb.append("    WHEN etcm.etcm_msd = 'Y'        THEN 'MSD' ");
//sb.append("    ELSE '' ");
//sb.append("  END AS UNIQPOSE, ");
//
//// UNIQPOSEID
//sb.append("  CASE ");
//sb.append("    WHEN etcm.etcm_general = 'Y'    THEN 'GN' ");
//sb.append("    WHEN etcm.etcm_uniquepos = 'Y'  THEN 'UQ' ");
//sb.append("    WHEN etcm.etcm_msd = 'Y'        THEN 'MS' ");
//sb.append("    ELSE '' ");
//sb.append("  END AS UNIQPOSEID, ");
//
//sb.append("  etcm.etcm_caldate           AS ETCM_CALDATE, ");
//sb.append("  etcm.etcm_permittedstrength AS ETCM_PERMITTEDSTRENGTH, ");
//sb.append("  etcm.etcm_max_duration      AS ETCM_MAX_DURATION, ");
//
//// DECODE(ETCM_ASSESSMENTREQUIRED,'Y','YES','NO')
//sb.append("  CASE etcm.etcm_assessmentrequired ");
//sb.append("    WHEN 'Y' THEN 'YES' ");
//sb.append("    ELSE 'NO' ");
//sb.append("  END AS ASSESSMENTREQUIREDTXT, ");
//sb.append("  etcm.etcm_assessmentrequired AS ETCM_ASSESSMENTREQUIRED, ");
//
//// DECODE(ETCM_MATERIALREADY,'Y','YES','NO')
//sb.append("  CASE etcm.etcm_materialready ");
//sb.append("    WHEN 'Y' THEN 'YES' ");
//sb.append("    ELSE 'NO' ");
//sb.append("  END AS MATERIALREADYTXT, ");
//sb.append("  etcm.etcm_materialready AS ETCM_MATERIALREADY, ");
//
//// DECODE(ETCM_MARKBASED,'Y','YES','NO')
//sb.append("  CASE etcm.etcm_markbased ");
//sb.append("    WHEN 'Y' THEN 'YES' ");
//sb.append("    ELSE 'NO' ");
//sb.append("  END AS MARKBASEDTXT, ");
//sb.append("  etcm.etcm_markbased AS ETCM_MARKBASED, ");
//
//// LISTAGG(ROLE_NAME,...) -> string_agg(...)
//sb.append("  (SELECT string_agg(r.role_name, ',' ORDER BY r.role_name) ");
//sb.append("     FROM ent_tl_trgcalunqp u ");
//sb.append("     LEFT JOIN gen_tl_rolemst r ");
//sb.append("       ON u.etcu_role_keyid = r.role_keyid ");
//sb.append("    WHERE u.etcu_etcm_keyid = etcm.etcm_keyid ");
//sb.append("  ) AS ROLE_NAME, ");
//
//sb.append("  '' AS UNIQPOS, ");
//sb.append("  '' AS EMPLOYEEADD, ");
//sb.append("  '' AS EMPATTEDNCE, ");
//
//// LISTAGG(EMPM_NAME,...) -> string_agg(...)
//sb.append("  (SELECT string_agg(e2.empm_name, ',' ORDER BY e2.empm_name) ");
//sb.append("     FROM gen_tl_employeemst e2 ");
//sb.append("     JOIN ent_tl_trgcalemp ce ");
//sb.append("       ON ce.etce_empm_keyid = e2.empm_keyid ");
//sb.append("    WHERE ce.etce_etcm_keyid = etcm.etcm_keyid ");
//sb.append("  ) AS PLANNEDEMPM_NAME, ");
//
//// DECODE(ETCM_TEMPFIELD6,'Y','YES','NO')
//sb.append("  CASE etcm.etcm_tempfield6 ");
//sb.append("    WHEN 'Y' THEN 'YES' ");
//sb.append("    ELSE 'NO' ");
//sb.append("  END AS ASSEMNTCOMPL, ");
//sb.append("  etcm.etcm_tempfield6 AS ETCM_TEMPFIELD6, ");
//
//// DECODE(ETCM_CHKCOMPLETED,'Y','YES','NO')
//sb.append("  CASE etcm.etcm_chkcompleted ");
//sb.append("    WHEN 'Y' THEN 'YES' ");
//sb.append("    ELSE 'NO' ");
//sb.append("  END AS TRNCOMPL, ");
//
//sb.append("  etcm.etcm_chkcompleted AS TRNCOMPLID, ");
//
//// DECODE(ETCM_CHKCOMPLETED,'Y',ETCM_COMPLETEDDATE,'')
//// -> keep as date/null
//sb.append("  CASE ");
//sb.append("    WHEN etcm.etcm_chkcompleted = 'Y' THEN etcm.etcm_completeddate ");
//sb.append("    ELSE NULL ");
//sb.append("  END AS COMPLTDATE, ");
//
//sb.append("  (emp.empm_name || '-' || emp.empm_code) AS COMPLETEDBY, ");
//sb.append("  etcm.etcm_completedby AS ETCM_COMPLETEDBY, ");
//
//sb.append("  etcm.etcm_rating AS ETCM_RATING, ");
//sb.append("  etcm.etcm_rating AS RatingId, ");
//sb.append("  etcm.etcm_comments AS ETCM_COMMENTS, ");
//
//sb.append("  '' AS FILEMGR, ");
//
//// NVL(PLANED,0), NVL(ATTEND,0)
//sb.append("  COALESCE(plan.planed, 0) AS PLANED, ");
//sb.append("  COALESCE(att.attend, 0) AS ATTEND, ");
//
//// NVL(ROUND((ATTEND/DECODE(PLANED,0,1,PLANED)*100),2),0)
//sb.append("  COALESCE(ROUND((COALESCE(att.attend,0)::numeric / ");
//sb.append("    (CASE WHEN COALESCE(plan.planed,0) = 0 ");
//sb.append("          THEN 1 ");
//sb.append("          ELSE COALESCE(plan.planed,0) ");
//sb.append("     END)::numeric) * 100, 2), 0) AS ADHERENCE, ");
//
//// NVL(ATTEND*ETCM_MAX_DURATION,0)
//sb.append("  COALESCE(att.attend * etcm.etcm_max_duration, 0) AS MANHOURSE ");
//
//sb.append(" FROM ent_tl_trgcalmst etcm ");
//sb.append(" LEFT JOIN ent_tl_topicmst topi ");
//sb.append("        ON topi.topi_keyid = etcm.etcm_topicid ");
//sb.append(" LEFT JOIN ent_tl_topiccategorymst tcat ");
//sb.append("        ON tcat.tcat_keyid = etcm.etcm_topiccategory ");
//sb.append(" LEFT JOIN gen_tl_trademst trdm ");
//sb.append("        ON trdm.trdm_keyid = etcm.etcm_trainingfunction ");
//sb.append(" LEFT JOIN gen_tl_employeemst emp ");
//sb.append("        ON emp.empm_keyid = etcm.etcm_completedby ");
//sb.append(" LEFT JOIN ent_tl_venuemst venu ");
//sb.append("        ON venu.venu_keyid = etcm.etcm_venue ");
//sb.append(" JOIN gen_mv_flidhierarchy ");
//sb.append("        ON flid = etcm.etcm_flid ");
//
//// PLANED counts
//sb.append(" LEFT JOIN ( ");
//sb.append("    SELECT etce_etcm_keyid, COUNT(*) AS planed ");
//sb.append("    FROM ent_tl_trgcalemp ");
//sb.append("    GROUP BY etce_etcm_keyid ");
//sb.append(" ) plan ");
//sb.append("   ON plan.etce_etcm_keyid = etcm.etcm_keyid ");
//
//// ATTEND counts
//sb.append(" LEFT JOIN ( ");
//sb.append("    SELECT etca_etcm_keyid, COUNT(*) AS attend ");
//sb.append("    FROM ent_tl_trgcalempatscore ");
//sb.append("    WHERE etca_prsentabsent = 'P' ");
//sb.append("    GROUP BY etca_etcm_keyid ");
//sb.append(" ) att ");
//sb.append("   ON att.etca_etcm_keyid = etcm.etcm_keyid ");
//
//sb.append(" WHERE etcm.etcm_chkcompleted = 'N' ");
//sb.append(condSql);
//sb.append(" ORDER BY etcm.etcm_keyid DESC ");
//
//CommonMessage.debugMsg(sb + " query in sid the DAO Impl");
//
//List<String[]> dataList =
//dbActionTemplate.getDataListWithColHeader(sb.toString(), null);
//
//// NOTE: this block is still your original logic; it assumes
//// paramvalues.get(0) holds totalCnt from a function call.
//if (commonFilter.getViewClick() == 'Y') {
//String totalCnt = paramvalues.get(0);
//boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
//CommonMessage.debugMsg("IS isInteger" + isInteger);
//if (isInteger) {
//commonFilter.setTotalRecordCnt((int) Long.parseLong(totalCnt));
//}
//}
//
//return dataList;
//
//} catch (Exception e) {
//e.printStackTrace();
//}
//return null;
//}

	// ------------ vignesh 24NOV2025 -----------------------------//
	@Override
	public List<String[]> getListTrgCalendarView(CommonFilter commonFilter,
	                                             GridParams gridParams) throws Exception {
	  try {
	    CommonMessage.debugMsg("getehsAuditParamterGrid sql..");

	    List<String> paramvalues = new ArrayList<String>();
	    String condParms    = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	    String mode         = commonFilter.getType();
	    CommonMessage.debugMsg("value of mode" + commonFilter.getType());
	    CommonMessage.debugMsg("check fromtorow parameter" + commonParams);

	    if (UIUtils.isValidKeyId(mode)) {
	      condParms += "Mode=" + mode + ";";
	    }
	    CommonMessage.debugMsg("condParms" + condParms);
	    CommonMessage.debugMsg("commonParams" + commonParams);
	    paramvalues.add(condParms);
	    paramvalues.add(commonParams);

	    // --- Build WHERE pieces (Postgres-safe; use table alias m.* everywhere)
	    StringBuilder condSql = new StringBuilder();

	    if (UIUtils.isValidKeyId(commonFilter.getKey())) {
	      condSql.append(" AND m.ETCM_KEYID='").append(commonFilter.getKey()).append("'");
	    } else {
	      if (UIUtils.isValidKeyId(commonFilter.getSectionId())) {
	        condSql.append(" AND m.ETCM_DMT='").append(commonFilter.getSectionId()).append("'");
	      }
	      if (UIUtils.isValidKeyId(commonFilter.getCellId())) {
	        condSql.append(" AND m.ETCM_JH='").append(commonFilter.getCellId()).append("'");
	      }
	      if (UIUtils.isValidKeyId(commonFilter.getTrarId())) {
	        condSql.append(" AND m.ETCM_TRAININGFUNCTION='").append(commonFilter.getTrarId()).append("'");
	      }
	      if (UIUtils.isValidDate(commonFilter.getFromDate()) && UIUtils.isValidDate(commonFilter.getToDate())) {
	        // Oracle '01-Oct-2025' style -> Postgres to_date(...)
	        condSql.append(" AND m.ETCM_CALDATE BETWEEN to_date('")
	               .append(commonFilter.getFromDate()).append("','DD-Mon-YYYY')")
	               .append(" AND to_date('")
	               .append(commonFilter.getToDate()).append("','DD-Mon-YYYY')");
	      }
	      if (UIUtils.isValidKeyId(commonFilter.getUniquePos())) {
	        condSql.append(" AND m.ETCM_KEYID IN (")
	               .append(" SELECT ETCU_ETCM_KEYID FROM ENT_TL_TRGCALUNQP")
	               .append(" WHERE ETCU_ROLE_KEYID='").append(commonFilter.getUniquePos()).append("')");
	      }
	    }

	    // Optional FLID hierarchy filter (avoid cross join unless FLID present)
	    boolean hasFlid = UIUtils.isValidKeyId(commonFilter.getFlid());
	    String   flJoin = "";
	    String   flCond = "";
	    if (hasFlid) {
	      flJoin = " JOIN GEN_MV_FLIDHIERARCHY h ON m.ETCM_FLID = h.FLID ";
	      flCond = " AND POSITION('" + commonFilter.getFlid()
	             + "' IN (COALESCE(h.PARENTFLIDS,'') || '/' || h.FLID)) > 0";
	    }

	    CommonMessage.debugMsg("commonFilter.getUniquePos()commonFilter.getUniquePos()" + commonFilter.getUniquePos());

	    // =========================
	    // SELECT columns (unchanged)
	    // =========================
	    StringBuilder cols = new StringBuilder()
	      .append(" m.ETCM_KEYID,")
	      .append(" m.ETCM_DMT,")
	      .append(" m.ETCM_JH,")
	      .append(" m.ETCM_FLID,")
	      .append(" m.ETCM_LOCATION,")
	      .append(" m.ETCM_CREATEDATETIME,")
	      .append(" m.ETCM_ANCHOREDBY,")
	      .append(" m.ETCM_ANCHOREDBY AS ANCHOREDBYid,")
	      .append(" t.TOPI_NAME,")
	      .append(" m.ETCM_TOPICID,")
	      .append(" c.TCAT_NAME,")
	      .append(" m.ETCM_TOPICCATEGORY,")
	      .append(" CASE m.ETCM_FUNCTION")
	      .append("   WHEN 'NB'  THEN 'Need Basic'")
	      .append("   WHEN 'SD'  THEN 'Section D'")
	      .append("   WHEN 'SI'  THEN 'Skill Index'")
	      .append("   WHEN 'KU'  THEN 'Knowledge Upgradation'")
	      .append("   WHEN 'KSA' THEN 'KSA (GAP Based)'")
	      .append("   WHEN 'RT'  THEN 'Refresher Training'")
	      .append("   WHEN 'EHS' THEN 'EHS'")
	      .append("   ELSE NULL END AS IDENFIEDTHG,")
	      .append(" m.ETCM_FUNCTION,")
	      .append(" tr.TRDM_NAME,")
	      .append(" m.ETCM_TRAININGFUNCTION,")
	      .append(" v.VENU_NAME,")
	      .append(" m.ETCM_VENUE,")
	      .append(" CASE")
	      .append("   WHEN m.ETCM_GENERAL='Y'    THEN 'General'")
	      .append("   WHEN m.ETCM_UNIQUEPOS='Y'  THEN 'Unique Position'")
	      .append("   WHEN m.ETCM_MSD='Y'        THEN 'MSD'")
	      .append("   ELSE ''")
	      .append(" END AS UNIQPOSE,")
	      .append(" CASE")
	      .append("   WHEN m.ETCM_GENERAL='Y'    THEN 'GN'")
	      .append("   WHEN m.ETCM_UNIQUEPOS='Y'  THEN 'UQ'")
	      .append("   WHEN m.ETCM_MSD='Y'        THEN 'MS'")
	      .append("   ELSE ''")
	      .append(" END AS UNIQPOSEID,")
	      .append(" (SELECT STRING_AGG(r.ROLE_NAME, ',' ORDER BY r.ROLE_NAME)")
	      .append("    FROM ENT_TL_TRGCALUNQP uq")
	      .append("    LEFT JOIN GEN_TL_ROLEMST r ON r.ROLE_KEYID = uq.ETCU_ROLE_KEYID")
	      .append("   WHERE uq.ETCU_ETCM_KEYID = m.ETCM_KEYID) AS ROLE_NAME,")
	      .append(" m.ETCM_CALDATE,")
	      .append(" (SELECT STRING_AGG(s.ETCS_NAME, ',' ORDER BY s.ETCS_NAME)")
	      .append("    FROM ENT_TL_TRGCALSESSION s")
	      .append("   WHERE s.ETCS_ETCM_KEYID = m.ETCM_KEYID) AS SESSIONS,")
	      .append(" m.ETCM_PERMITTEDSTRENGTH,")
	      .append(" m.ETCM_MAX_DURATION,")
	      .append(" (SELECT STRING_AGG(f.FTYM_NAME, ',' ORDER BY f.FTYM_NAME)")
	      .append("    FROM ENT_TL_TRGFACULTY tf")
	      .append("    JOIN ENT_TL_FACULTYMST f ON f.FTYM_KEYID = tf.ETCF_FACULTYID")
	      .append("   WHERE tf.ETCF_ETCM_KEYID = m.ETCM_KEYID) AS FTYM_NAME,")
	      .append(" CASE WHEN m.ETCM_ASSESSMENTREQUIRED='Y' THEN 'YES' ELSE 'NO' END,")
	      .append(" m.ETCM_ASSESSMENTREQUIRED,")
	      .append(" CASE WHEN m.ETCM_MATERIALREADY='Y'      THEN 'YES' ELSE 'NO' END,")
	      .append(" m.ETCM_MATERIALREADY,")
	      .append(" CASE WHEN m.ETCM_MARKBASED='Y'           THEN 'YES' ELSE 'NO' END,")
	      .append(" m.ETCM_MARKBASED,")
	      .append(" '' AS UNIQPOS,")
	      .append(" '' AS EMPLOYEEADD,")
	      .append(" '' AS EMPATTEDNCE,")
	      .append(" CASE WHEN m.ETCM_TEMPFIELD6='Y' THEN 'YES' ELSE 'NO' END AS ASSEMNTCOMPL,")
	      .append(" m.ETCM_TEMPFIELD6,")
	      .append(" CASE WHEN m.ETCM_CHKCOMPLETED='Y' THEN 'YES' ELSE 'NO' END AS TRNCOMPL,")
	      .append(" m.ETCM_CHKCOMPLETED AS TRNCOMPLID,")
	      .append(" CASE WHEN m.ETCM_CHKCOMPLETED='Y'")
	      .append("      THEN COALESCE(TO_CHAR(m.ETCM_COMPLETEDDATE,'YYYY-MM-DD'),'')")
	      .append("      ELSE ''")
	      .append(" END AS COMPLTDATE,")
	      .append(" (e.EMPM_NAME || '-' || e.EMPM_CODE) AS COMPLETEDBY,")
	      .append(" m.ETCM_COMPLETEDBY,")
	      .append(" (SELECT STRING_AGG(e2.EMPM_NAME, ',' ORDER BY e2.EMPM_NAME)")
	      .append("    FROM ENT_TL_TRGCALEMP ce")
	      .append("    JOIN GEN_TL_EMPLOYEEMST e2 ON e2.EMPM_KEYID = ce.ETCE_EMPM_KEYID")
	      .append("   WHERE ce.ETCE_ETCM_KEYID = m.ETCM_KEYID) AS PLANNEDEMPM_NAME,")
	      .append(" (SELECT STRING_AGG(e3.EMPM_NAME, ',' ORDER BY e3.EMPM_NAME)")
	      .append("    FROM ENT_TL_TRGCALEMPATSCORE ca")
	      .append("    JOIN GEN_TL_EMPLOYEEMST e3 ON e3.EMPM_KEYID = ca.ETCA_ETCE_EMPM_KEYID")
	      .append("   WHERE ca.ETCA_ETCM_KEYID = m.ETCM_KEYID AND ca.ETCA_PRSENTABSENT='P') AS PRESENTEMPM_NAME,")
	      .append(" COALESCE(pl.PLANED,0),")
	      .append(" COALESCE(at.ATTEND,0),")
	      .append(" COALESCE(ROUND((COALESCE(at.ATTEND,0)::numeric / NULLIF(COALESCE(pl.PLANED,0)::numeric,0) * 100),2),0) AS ADHERENCE,")
	      .append(" COALESCE(at.ATTEND,0) * m.ETCM_MAX_DURATION AS MANHOURSE,")
	      .append(" COALESCE((COALESCE(pl.PLANED,0) - COALESCE(at.ATTEND,0)),0) AS ABSENT,")
	      .append(" m.ETCM_RATING,")
	      .append(" m.ETCM_RATING AS RatingId,")
	      .append(" m.ETCM_COMMENTS,")
	      .append(" '' AS FILEMGR");

	    // =========================
	    // FROM / JOINs (unchanged)
	    // =========================
	    StringBuilder fromJoins = new StringBuilder()
	      .append(" FROM ENT_TL_TRGCALMST m")
	      .append(" LEFT JOIN ENT_TL_TOPICMST        t  ON t.TOPI_KEYID  = m.ETCM_TOPICID")
	      .append(" LEFT JOIN ENT_TL_TOPICCATEGORYMST c ON c.TCAT_KEYID  = m.ETCM_TOPICCATEGORY")
	      .append(" LEFT JOIN GEN_TL_TRADEMST        tr ON tr.TRDM_KEYID = m.ETCM_TRAININGFUNCTION")
	      .append(" LEFT JOIN ENT_TL_VENUEMST        v  ON v.VENU_KEYID  = m.ETCM_VENUE")
	      .append(" LEFT JOIN GEN_TL_EMPLOYEEMST     e  ON e.EMPM_KEYID  = m.ETCM_COMPLETEDBY")
	      .append(" LEFT JOIN (")
	      .append("   SELECT ETCE_ETCM_KEYID, COUNT(*) AS PLANED")
	      .append("     FROM ENT_TL_TRGCALEMP")
	      .append("    GROUP BY ETCE_ETCM_KEYID")
	      .append(" ) pl ON pl.ETCE_ETCM_KEYID = m.ETCM_KEYID")
	      .append(" LEFT JOIN (")
	      .append("   SELECT ETCA_ETCM_KEYID, COUNT(*) AS ATTEND")
	      .append("     FROM ENT_TL_TRGCALEMPATSCORE")
	      .append("    WHERE ETCA_PRSENTABSENT='P'")
	      .append("    GROUP BY ETCA_ETCM_KEYID")
	      .append(" ) at ON at.ETCA_ETCM_KEYID = m.ETCM_KEYID");

	    if (hasFlid) {
	      fromJoins.append(flJoin);
	    }

	    // =========================
	    // WHERE + GROUP BY
	    // =========================
	    StringBuilder whereAndGroup = new StringBuilder()
	      .append(" WHERE 1=1 ")
	      .append(condSql);
	    if (hasFlid) whereAndGroup.append(flCond);

	    whereAndGroup.append(" GROUP BY ")
	      .append(" m.ETCM_KEYID, m.ETCM_DMT, m.ETCM_JH, m.ETCM_FLID, m.ETCM_LOCATION, m.ETCM_CREATEDATETIME, m.ETCM_ANCHOREDBY,")
	      .append(" t.TOPI_NAME, m.ETCM_TOPICID, c.TCAT_NAME, m.ETCM_TOPICCATEGORY, m.ETCM_FUNCTION, tr.TRDM_NAME, m.ETCM_TRAININGFUNCTION,")
	      .append(" v.VENU_NAME, m.ETCM_VENUE, m.ETCM_GENERAL, m.ETCM_UNIQUEPOS, m.ETCM_MSD, m.ETCM_CALDATE, m.ETCM_PERMITTEDSTRENGTH,")
	      .append(" m.ETCM_MAX_DURATION, m.ETCM_ASSESSMENTREQUIRED, m.ETCM_MATERIALREADY, m.ETCM_MARKBASED, m.ETCM_TEMPFIELD6,")
	      .append(" m.ETCM_CHKCOMPLETED, m.ETCM_COMPLETEDDATE, e.EMPM_NAME, e.EMPM_CODE, m.ETCM_COMPLETEDBY,")
	      .append(" pl.PLANED, at.ATTEND, m.ETCM_RATING, m.ETCM_COMMENTS ");

	    // Core (no ORDER BY) — used for COUNT(*) and paging base
	    String coreSql = "SELECT DISTINCT " + cols + fromJoins + whereAndGroup;

	    // =========================
	    // Total count (for jqGrid)
	    // =========================
	    String countSql = "SELECT COUNT(*) FROM (" + coreSql + ") x";
	    int totalCnt = 0;
	    try {
	      String cntStr = dbActionTemplate.getSingleValue(countSql);
	      if (cntStr != null && cntStr.trim().length() > 0) {
	        totalCnt = Integer.parseInt(cntStr.trim());
	      }
	    } catch (Exception ex) {
	      // fallback to 0 if anything goes wrong
	      CommonMessage.debugMsg("WARN: COUNT failed: " + ex.getMessage());
	    }

	    // =========================
	    // Resolve paging: FROMTOROW + gridParams
	    // =========================
	    int rowsPerPage =  100;
	    int pageNo      =  1;

	    // Defaults
	    int startRN = 1; // 1-based
	    int sizeRN  = rowsPerPage;

	    // If FROMTOROW is present, prefer its start; keep size from gridParams (safer)
	    if (commonParams != null) {
	      int idx = commonParams.indexOf("FROMTOROW=");
	      if (idx >= 0) {
	        try {
	          String tail = commonParams.substring(idx + "FROMTOROW=".length());
	          String val  = tail.split(";")[0];                   // "201 AND 1000"
	          String[] ab = val.split("AND");
	          int a = Integer.parseInt(ab[0].trim());             // start
	          int b = Integer.parseInt(ab[1].trim());             // end or size
	          if (a > 0) startRN = a;
	          // respect "end" format if provided smaller than start, treat as SIZE
	          int computedSize = (b < a) ? b : (b - a + 1);
	          if (computedSize > 0 && computedSize < 100000) {
	            // still prefer UI 'rows', but align upper bound if UI sends bigger
	        	  rowsPerPage = computedSize;
	          }
	        } catch (Exception ignore) {
	          // keep defaults
	        }
	      }
	    }

	    long offset = Math.max(0, startRN - 1);
	    int  limit  = sizeRN;

	    CommonMessage.debugMsg("Paging -> startRN=" + startRN + " limit(size)=" + limit + " offset=" + offset
	        + " (rowsPerPage=" + rowsPerPage + ", page=" + pageNo + ")");

	    // =========================
	    // Final ordered + paged SQL
	    // =========================
	    String ordered = coreSql + " ORDER BY m.ETCM_KEYID DESC";
	    String pagedSql = ordered + " LIMIT " + limit + " OFFSET " + offset;

	    CommonMessage.debugMsg(pagedSql + "  query in sid the DAO Impl");

	    // =========================
	    // Fetch only ONE page
	    // =========================
	    List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(pagedSql, null);

	    // Set total count for jqGrid (so your convertToJqGridTableObject uses it)
	    if (commonFilter.getViewClick() == 'Y') {
	      commonFilter.setTotalRecordCnt(totalCnt);
	      CommonMessage.debugMsg(commonFilter.getTotalRecordCnt() + " tc - get total count");
	      CommonMessage.debugMsg(totalCnt + " tc total count");
	    }

	    return dataList;

	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	  return null;
	}

//	@Override
//	public List<String[]> getListTrgCalendarView(CommonFilter commonFilter,
//	                                             GridParams gridParams) throws Exception {
//	  try {
//	    CommonMessage.debugMsg("getehsAuditParamterGrid sql..");
//
//	    List<String> paramvalues = new ArrayList<String>();
//	    String condParms    = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
//	    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
//	    String mode         = commonFilter.getType();
//	    CommonMessage.debugMsg("value of mode" + commonFilter.getType());
//	    CommonMessage.debugMsg("check fromtorow parameter" + commonParams);
//
//	    if (UIUtils.isValidKeyId(mode)) {
//	      condParms += "Mode=" + mode + ";";
//	    }
//	    CommonMessage.debugMsg("condParms" + condParms);
//	    CommonMessage.debugMsg("commonParams" + commonParams);
//	    paramvalues.add(condParms);
//	    paramvalues.add(commonParams);
//
//	    // --- Build WHERE pieces (Postgres-safe; use table alias m.* everywhere)
//	    StringBuilder condSql = new StringBuilder();
//
//	    if (UIUtils.isValidKeyId(commonFilter.getKey())) {
//	      condSql.append(" AND m.ETCM_KEYID='").append(commonFilter.getKey()).append("'");
//	    } else {
//	      if (UIUtils.isValidKeyId(commonFilter.getSectionId())) {
//	        condSql.append(" AND m.ETCM_DMT='").append(commonFilter.getSectionId()).append("'");
//	      }
//	      if (UIUtils.isValidKeyId(commonFilter.getCellId())) {
//	        condSql.append(" AND m.ETCM_JH='").append(commonFilter.getCellId()).append("'");
//	      }
//	      if (UIUtils.isValidKeyId(commonFilter.getTrarId())) {
//	        condSql.append(" AND m.ETCM_TRAININGFUNCTION='").append(commonFilter.getTrarId()).append("'");
//	      }
//	      if (UIUtils.isValidDate(commonFilter.getFromDate()) && UIUtils.isValidDate(commonFilter.getToDate())) {
//	        // Oracle '01-Oct-2025' style -> Postgres to_date(...)
//	        condSql.append(" AND m.ETCM_CALDATE BETWEEN to_date('")
//	               .append(commonFilter.getFromDate()).append("','DD-Mon-YYYY')")
//	               .append(" AND to_date('")
//	               .append(commonFilter.getToDate()).append("','DD-Mon-YYYY')");
//	      }
//	      if (UIUtils.isValidKeyId(commonFilter.getUniquePos())) {
//	        condSql.append(" AND m.ETCM_KEYID IN (")
//	               .append(" SELECT ETCU_ETCM_KEYID FROM ENT_TL_TRGCALUNQP")
//	               .append(" WHERE ETCU_ROLE_KEYID='").append(commonFilter.getUniquePos()).append("')");
//	      }
//	    }
//
//	    // Optional FLID hierarchy filter (avoid cross join unless FLID present)
//	    boolean hasFlid = UIUtils.isValidKeyId(commonFilter.getFlid());
//	    String   flJoin = "";
//	    String   flCond = "";
//	    if (hasFlid) {
//	      flJoin = " JOIN GEN_MV_FLIDHIERARCHY h ON m.ETCM_FLID = h.FLID ";
//	      flCond = " AND POSITION('" + commonFilter.getFlid()
//	             + "' IN (COALESCE(h.PARENTFLIDS,'') || '/' || h.FLID)) > 0";
//	    }
//
//	    CommonMessage.debugMsg("commonFilter.getUniquePos()commonFilter.getUniquePos()" + commonFilter.getUniquePos());
//
//	    StringBuilder sb = new StringBuilder();
//
//	    // ==== SELECT (Postgres rewrite) ====
//	    sb.append(" SELECT DISTINCT ")
//	      .append(" m.ETCM_KEYID,")
//	      .append(" m.ETCM_DMT,")
//	      .append(" m.ETCM_JH,")
//	      .append(" m.ETCM_FLID,")
//	      .append(" m.ETCM_LOCATION,")
//	      .append(" m.ETCM_CREATEDATETIME,")
//	      .append(" m.ETCM_ANCHOREDBY,")
//	      .append(" m.ETCM_ANCHOREDBY AS ANCHOREDBYid,")
//	      .append(" t.TOPI_NAME,")
//	      .append(" m.ETCM_TOPICID,")
//	      .append(" c.TCAT_NAME,")
//	      .append(" m.ETCM_TOPICCATEGORY,")
//	      // DECODE -> CASE
//	      .append(" CASE m.ETCM_FUNCTION")
//	      .append("   WHEN 'NB'  THEN 'Need Basic'")
//	      .append("   WHEN 'SD'  THEN 'Section D'")
//	      .append("   WHEN 'SI'  THEN 'Skill Index'")
//	      .append("   WHEN 'KU'  THEN 'Knowledge Upgradation'")
//	      .append("   WHEN 'KSA' THEN 'KSA (GAP Based)'")
//	      .append("   WHEN 'RT'  THEN 'Refresher Training'")
//	      .append("   WHEN 'EHS' THEN 'EHS'")
//	      .append("   ELSE NULL END AS IDENFIEDTHG,")
//	      .append(" m.ETCM_FUNCTION,")
//	      .append(" tr.TRDM_NAME,")
//	      .append(" m.ETCM_TRAININGFUNCTION,")
//	      .append(" v.VENU_NAME,")
//	      .append(" m.ETCM_VENUE,")
//	      // UNIQPOSE
//	      .append(" CASE")
//	      .append("   WHEN m.ETCM_GENERAL='Y'    THEN 'General'")
//	      .append("   WHEN m.ETCM_UNIQUEPOS='Y'  THEN 'Unique Position'")
//	      .append("   WHEN m.ETCM_MSD='Y'        THEN 'MSD'")
//	      .append("   ELSE ''")
//	      .append(" END AS UNIQPOSE,")
//	      // UNIQPOSEID
//	      .append(" CASE")
//	      .append("   WHEN m.ETCM_GENERAL='Y'    THEN 'GN'")
//	      .append("   WHEN m.ETCM_UNIQUEPOS='Y'  THEN 'UQ'")
//	      .append("   WHEN m.ETCM_MSD='Y'        THEN 'MS'")
//	      .append("   ELSE ''")
//	      .append(" END AS UNIQPOSEID,")
//
//	      // ROLE_NAME list (LISTAGG -> STRING_AGG with ORDER BY)
//	      .append(" (SELECT STRING_AGG(r.ROLE_NAME, ',' ORDER BY r.ROLE_NAME)")
//	      .append("    FROM ENT_TL_TRGCALUNQP uq")
//	      .append("    LEFT JOIN GEN_TL_ROLEMST r ON r.ROLE_KEYID = uq.ETCU_ROLE_KEYID")
//	      .append("   WHERE uq.ETCU_ETCM_KEYID = m.ETCM_KEYID) AS ROLE_NAME,")
//
//	      .append(" m.ETCM_CALDATE,")
//
//	      // SESSIONS list
//	      .append(" (SELECT STRING_AGG(s.ETCS_NAME, ',' ORDER BY s.ETCS_NAME)")
//	      .append("    FROM ENT_TL_TRGCALSESSION s")
//	      .append("   WHERE s.ETCS_ETCM_KEYID = m.ETCM_KEYID) AS SESSIONS,")
//
//	      .append(" m.ETCM_PERMITTEDSTRENGTH,")
//	      .append(" m.ETCM_MAX_DURATION,")
//
//	      // FTYM_NAME list (faculty)
//	      .append(" (SELECT STRING_AGG(f.FTYM_NAME, ',' ORDER BY f.FTYM_NAME)")
//	      .append("    FROM ENT_TL_TRGFACULTY tf")
//	      .append("    JOIN ENT_TL_FACULTYMST f ON f.FTYM_KEYID = tf.ETCF_FACULTYID")
//	      .append("   WHERE tf.ETCF_ETCM_KEYID = m.ETCM_KEYID) AS FTYM_NAME,")
//
//	      // YES/NO flags
//	      .append(" CASE WHEN m.ETCM_ASSESSMENTREQUIRED='Y' THEN 'YES' ELSE 'NO' END,")
//	      .append(" m.ETCM_ASSESSMENTREQUIRED,")
//	      .append(" CASE WHEN m.ETCM_MATERIALREADY='Y'      THEN 'YES' ELSE 'NO' END,")
//	      .append(" m.ETCM_MATERIALREADY,")
//	      .append(" CASE WHEN m.ETCM_MARKBASED='Y'           THEN 'YES' ELSE 'NO' END,")
//	      .append(" m.ETCM_MARKBASED,")
//
//	      .append(" '' AS UNIQPOS,")
//	      .append(" '' AS EMPLOYEEADD,")
//	      .append(" '' AS EMPATTEDNCE,")
//
//	      .append(" CASE WHEN m.ETCM_TEMPFIELD6='Y' THEN 'YES' ELSE 'NO' END AS ASSEMNTCOMPL,")
//	      .append(" m.ETCM_TEMPFIELD6,")
//	      .append(" CASE WHEN m.ETCM_CHKCOMPLETED='Y' THEN 'YES' ELSE 'NO' END AS TRNCOMPL,")
//	      .append(" m.ETCM_CHKCOMPLETED AS TRNCOMPLID,")
//
//	      // DECODE(CHKCOMPLETED,'Y',ETCM_COMPLETEDDATE,'') -> text
//	      .append(" CASE WHEN m.ETCM_CHKCOMPLETED='Y'")
//	      .append("      THEN COALESCE(TO_CHAR(m.ETCM_COMPLETEDDATE,'YYYY-MM-DD'),'')")
//	      .append("      ELSE ''")
//	      .append(" END AS COMPLTDATE,")
//
//	      .append(" (e.EMPM_NAME || '-' || e.EMPM_CODE) AS COMPLETEDBY,")
//	      .append(" m.ETCM_COMPLETEDBY,")
//
//	      // Planned employees list
//	      .append(" (SELECT STRING_AGG(e2.EMPM_NAME, ',' ORDER BY e2.EMPM_NAME)")
//	      .append("    FROM ENT_TL_TRGCALEMP ce")
//	      .append("    JOIN GEN_TL_EMPLOYEEMST e2 ON e2.EMPM_KEYID = ce.ETCE_EMPM_KEYID")
//	      .append("   WHERE ce.ETCE_ETCM_KEYID = m.ETCM_KEYID) AS PLANNEDEMPM_NAME,")
//
//	      // Present employees list
//	      .append(" (SELECT STRING_AGG(e3.EMPM_NAME, ',' ORDER BY e3.EMPM_NAME)")
//	      .append("    FROM ENT_TL_TRGCALEMPATSCORE ca")
//	      .append("    JOIN GEN_TL_EMPLOYEEMST e3 ON e3.EMPM_KEYID = ca.ETCA_ETCE_EMPM_KEYID")
//	      .append("   WHERE ca.ETCA_ETCM_KEYID = m.ETCM_KEYID AND ca.ETCA_PRSENTABSENT='P') AS PRESENTEMPM_NAME,")
//
//	      // Planed / Attend via pre-aggregated LEFT JOINs
//	      .append(" COALESCE(pl.PLANED,0),")
//	      .append(" COALESCE(at.ATTEND,0),")
//	      .append(" COALESCE(ROUND((COALESCE(at.ATTEND,0)::numeric / NULLIF(COALESCE(pl.PLANED,0)::numeric,0) * 100),2),0) AS ADHERENCE,")
//	      .append(" COALESCE(at.ATTEND,0) * m.ETCM_MAX_DURATION AS MANHOURSE,")
//	      .append(" COALESCE((COALESCE(pl.PLANED,0) - COALESCE(at.ATTEND,0)),0) AS ABSENT,")
//
//	      .append(" m.ETCM_RATING,")
//	      .append(" m.ETCM_RATING AS RatingId,")
//	      .append(" m.ETCM_COMMENTS,")
//	      .append(" '' AS FILEMGR ")
//
//	      // ==== FROM / JOINs ====
//	      .append(" FROM ENT_TL_TRGCALMST m")
//	      .append(" LEFT JOIN ENT_TL_TOPICMST        t  ON t.TOPI_KEYID  = m.ETCM_TOPICID")
//	      .append(" LEFT JOIN ENT_TL_TOPICCATEGORYMST c ON c.TCAT_KEYID  = m.ETCM_TOPICCATEGORY")
//	      .append(" LEFT JOIN GEN_TL_TRADEMST        tr ON tr.TRDM_KEYID = m.ETCM_TRAININGFUNCTION")
//	      .append(" LEFT JOIN ENT_TL_VENUEMST        v  ON v.VENU_KEYID  = m.ETCM_VENUE")
//	      .append(" LEFT JOIN GEN_TL_EMPLOYEEMST     e  ON e.EMPM_KEYID  = m.ETCM_COMPLETEDBY")
//	      // aggregated counts
//	      .append(" LEFT JOIN (")
//	      .append("   SELECT ETCE_ETCM_KEYID, COUNT(*) AS PLANED")
//	      .append("     FROM ENT_TL_TRGCALEMP")
//	      .append("    GROUP BY ETCE_ETCM_KEYID")
//	      .append(" ) pl ON pl.ETCE_ETCM_KEYID = m.ETCM_KEYID")
//	      .append(" LEFT JOIN (")
//	      .append("   SELECT ETCA_ETCM_KEYID, COUNT(*) AS ATTEND")
//	      .append("     FROM ENT_TL_TRGCALEMPATSCORE")
//	      .append("    WHERE ETCA_PRSENTABSENT='P'")
//	      .append("    GROUP BY ETCA_ETCM_KEYID")
//	      .append(" ) at ON at.ETCA_ETCM_KEYID = m.ETCM_KEYID");
//
//	    // optional FLID hierarchy join & filter
//	    if (hasFlid) {
//	      sb.append(flJoin);
//	    }
//
//	    // ==== WHERE ====
//	    sb.append(" WHERE 1=1 ");
//	    sb.append(condSql);
//	    if (hasFlid) {
//	      sb.append(flCond);
//	    }
//
//	    // ==== GROUP BY (mirror your Oracle list to avoid dup rows) ====
//	    sb.append(" GROUP BY ")
//	      .append(" m.ETCM_KEYID, m.ETCM_DMT, m.ETCM_JH, m.ETCM_FLID, m.ETCM_LOCATION, m.ETCM_CREATEDATETIME, m.ETCM_ANCHOREDBY,")
//	      .append(" t.TOPI_NAME, m.ETCM_TOPICID, c.TCAT_NAME, m.ETCM_TOPICCATEGORY, m.ETCM_FUNCTION, tr.TRDM_NAME, m.ETCM_TRAININGFUNCTION,")
//	      .append(" v.VENU_NAME, m.ETCM_VENUE, m.ETCM_GENERAL, m.ETCM_UNIQUEPOS, m.ETCM_MSD, m.ETCM_CALDATE, m.ETCM_PERMITTEDSTRENGTH,")
//	      .append(" m.ETCM_MAX_DURATION, m.ETCM_ASSESSMENTREQUIRED, m.ETCM_MATERIALREADY, m.ETCM_MARKBASED, m.ETCM_TEMPFIELD6,")
//	      .append(" m.ETCM_CHKCOMPLETED, m.ETCM_COMPLETEDDATE, e.EMPM_NAME, e.EMPM_CODE, m.ETCM_COMPLETEDBY,")
//	      .append(" pl.PLANED, at.ATTEND, m.ETCM_RATING, m.ETCM_COMMENTS ")
//
//	      // ==== ORDER ====
//	      .append(" ORDER BY m.ETCM_KEYID DESC ");
//
//	    CommonMessage.debugMsg(sb + " query in sid the DAO Impl");
//
//	    List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sb.toString(), null);
//
//	    if (commonFilter.getViewClick() == 'Y') {
//	     // String totalCnt = paramvalues.get(0);
//	      int totalCnt = dataList.size();
//	      //boolean isInteger = java.util.regex.Pattern.matches("^\\d*$", totalCnt);
//	      //CommonMessage.debugMsg("IS isInteger" + isInteger);
//	      //if (isInteger) {
//	        commonFilter.setTotalRecordCnt(totalCnt);
//	        CommonMessage.debugMsg(commonFilter.getTotalRecordCnt() + " tc - get total count") ;
//		    CommonMessage.debugMsg(totalCnt + " tc total count");
//	      //}
//	    }
//	    return dataList;
//
//	  } catch (Exception e) {
//	    e.printStackTrace();
//	  }
//	  return null;
//	}

	
	
	
	
	
	
	
//	public List<String[]> getListTrgCalendarView(CommonFilter commonFilter,
//			GridParams gridParams) throws Exception {
//		try
//		{    
//		CommonMessage.debugMsg("getehsAuditParamterGrid sql..");
//		
//
//		List <String>  paramvalues = new ArrayList<String>();
//		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
//		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
//		String mode=commonFilter.getType();
//	    CommonMessage.debugMsg("value of mode"+commonFilter.getType());
//
//		
//	    if(UIUtils.isValidKeyId(mode)){
//			condParms +="Mode="+mode+";";
//		}
//	    CommonMessage.debugMsg("condParms"+condParms);
//	    CommonMessage.debugMsg("commonParams"+commonParams);
//		paramvalues.add(condParms);
//		paramvalues.add(commonParams);
//		String condSql="  ";
//		
//		if(UIUtils.isValidKeyId(commonFilter.getKey())){
//			condSql+=" AND ETCM_KEYID='"+commonFilter.getKey()+"'";
//		} 
//		else{
//		if(UIUtils.isValidKeyId(commonFilter.getSectionId())){
//		 condSql+= "  AND ETCM_DMT='"+commonFilter.getSectionId()+"'";
//		}
//		
//		if(UIUtils.isValidKeyId(commonFilter.getCellId())){
//			condSql+= "  AND ETCM_JH='"+commonFilter.getCellId()+"'";
//			}
//		if(UIUtils.isValidKeyId(commonFilter.getTrarId())){
//			condSql+= "  AND ETCM_TRAININGFUNCTION='"+commonFilter.getTrarId()+"'";
//			}
//		if(UIUtils.isValidDate(commonFilter.getFromDate())&&UIUtils.isValidDate(commonFilter.getToDate())){
//			 condSql+=" AND ETCM_CALDATE BETWEEN '"+commonFilter.getFromDate()+ "' AND '"+commonFilter.getToDate()+"'";
//		}
//		if(UIUtils.isValidKeyId(commonFilter.getUniquePos())){
//			condSql+=" AND ETCM_KEYID IN ( SELECT  ETCU_ETCM_KEYID FROM ENT_TL_TRGCALUNQP WHERE ETCU_ROLE_KEYID='"+commonFilter.getUniquePos()+"') ";
//		}
//		if(UIUtils.isValidKeyId(commonFilter.getFlid())){
//			if(UIUtils.isValidKeyId(commonFilter.getFlid())){
//				condSql+=" AND ETCM_FLID =FLID "+
//	             " AND INSTR(PARENTFLIDS "+
//	          " ||'/' "+
//	          " ||FLID,'"+commonFilter.getFlid()+"')>0";
//			}
//			
//		}
//		}
//		
//		CommonMessage.debugMsg("commonFilter.getUniquePos()commonFilter.getUniquePos()"+commonFilter.getUniquePos());
//		
//		List<String[]> getDataList=null;
//		StringBuilder sb=new StringBuilder();
//		  sb.append(" SELECT DISTINCT ETCM_KEYID,  ETCM_DMT,  ETCM_JH,  ETCM_FLID,  ETCM_LOCATION,  ETCM_CREATEDATETIME,  ETCM_ANCHOREDBY,  ETCM_ANCHOREDBY ANCHOREDBYid, ");
//		  sb.append(" TOPI_NAME,  ETCM_TOPICID,  TCAT_NAME,  ETCM_TOPICCATEGORY,  DECODE(ETCM_FUNCTION,'NB','Need Basic','SD','Section D','SI','Skill Index','KU','Knowledge Upgradation','KSA','KSA (GAP Based)','RT','Refresher Training','EHS','EHS' )AS IDENFIEDTHG, ");
//		  sb.append(" ETCM_FUNCTION,  TRDM_NAME,  ETCM_TRAININGFUNCTION,  VENU_NAME ,  ETCM_VENUE,  DECODE(ETCM_GENERAL,'Y','General',DECODE(ETCM_UNIQUEPOS,'Y','Unique Position',DECODE(ETCM_MSD,'Y','MSD',''))) AS UNIQPOSE, ");
//		  sb.append(" DECODE(ETCM_GENERAL,'Y','GN',DECODE(ETCM_UNIQUEPOS,'Y','UQ',DECODE(ETCM_MSD,'Y','MS',''))) AS UNIQPOSEID, ");
//		  sb.append(" (SELECT LISTAGG (ROLE_NAME, ',')  WITHIN GROUP (ORDER BY ROLE_NAME) FROM GEN_TL_ROLEMST,ENT_TL_TRGCALUNQP WHERE  ETCU_ROLE_KEYID=ROLE_KEYID(+)  AND ETCU_ETCM_KEYID=ETCM_KEYID ) ROLE_NAME  ,  ");
//		  sb.append(" ETCM_CALDATE,(SELECT LISTAGG(ETCS_NAME,',') WITHIN GROUP ( ORDER BY ETCS_NAME) FROM ENT_TL_TRGCALSESSION ");
//		  sb.append("  WHERE   ETCS_ETCM_KEYID=ETCM_KEYID) AS SESSIONS    ,   ETCM_PERMITTEDSTRENGTH, ");
//		  sb.append(" ETCM_MAX_DURATION, (SELECT LISTAGG(FTYM_NAME,',') WITHIN GROUP ( ORDER BY FTYM_NAME) FROM ENT_TL_FACULTYMST,  ENT_TL_TRGFACULTY ");
//		  sb.append("  WHERE  ETCF_FACULTYID=FTYM_KEYID  AND ETCF_ETCM_KEYID=ETCM_KEYID) FTYM_NAME, DECODE(ETCM_ASSESSMENTREQUIRED,'Y','YES','NO'), ");
//		 sb.append("  ETCM_ASSESSMENTREQUIRED,  DECODE(ETCM_MATERIALREADY,'Y','YES','NO'),  ETCM_MATERIALREADY,  DECODE(ETCM_MARKBASED,'Y','YES','NO'), ");
//		 sb.append("  ETCM_MARKBASED,  ''   AS UNIQPOS,  '' AS EMPLOYEEADD,  ''  AS EMPATTEDNCE,  DECODE(ETCM_TEMPFIELD6,'Y','YES','NO') AS ASSEMNTCOMPL, ");
//		 sb.append("  ETCM_TEMPFIELD6,  DECODE(ETCM_CHKCOMPLETED,'Y','YES','NO') AS TRNCOMPL,  ETCM_CHKCOMPLETED  AS TRNCOMPLID, ");
//		 sb.append("  DECODE(ETCM_CHKCOMPLETED,'Y',ETCM_COMPLETEDDATE,'') AS COMPLTDATE, EMPM_NAME ||'-' ||EMPM_CODE AS COMPLETEDBY , ETCM_COMPLETEDBY, ");
//		  sb.append(" (SELECT LISTAGG (EMPM_NAME, ',')  WITHIN GROUP (ORDER BY EMPM_NAME) FROM GEN_TL_EMPLOYEEMST,ENT_TL_TRGCALEMP WHERE  ");
//		 sb.append("   ETCE_EMPM_KEYID=EMPM_KEYID AND ETCE_ETCM_KEYID=ETCM_KEYID ) PLANNEDEMPM_NAME, ");
//		  sb.append("  (SELECT LISTAGG (EMPM_NAME, ',') WITHIN GROUP (ORDER BY EMPM_NAME) FROM GEN_TL_EMPLOYEEMST,ENT_TL_TRGCALEMPATSCORE ");
//		 sb.append("  WHERE  ETCA_ETCE_EMPM_KEYID =EMPM_KEYID AND ETCA_ETCM_KEYID = ETCM_KEYID AND ETCA_PRSENTABSENT='P') PRESENTEMPM_NAME, ");
//		 sb.append("  NVL(PLANED,0),  NVL(ATTEND,0), NVL(ROUND((ATTEND/DECODE(PLANED,0,1,PLANED)*100),2),0) ADHERENCE,NVL(ATTEND*ETCM_MAX_DURATION,0) AS MANHOURSE,  NVL((NVL(PLANED,0)-NVL(ATTEND,0)),0) ABSENT, "); 
//		 sb.append("  ETCM_RATING, ETCM_RATING AS RatingId, ETCM_COMMENTS, '' AS FILEMGR  ");
//		 sb.append("  FROM ENT_TL_TRGCALMST, ENT_TL_TOPICMST, ENT_TL_VENUEMST, ENT_TL_TOPICCATEGORYMST,GEN_TL_TRADEMST, GEN_TL_EMPLOYEEMST, GEN_MV_FLIDHIERARCHY, ");
//		 sb.append("  (SELECT COUNT(ETCE_ETCM_KEYID) AS PLANED,ETCE_ETCM_KEYID  FROM ENT_TL_TRGCALEMP,ENT_TL_TRGCALMST WHERE ETCE_ETCM_KEYID=ETCM_KEYID  GROUP BY ETCE_ETCM_KEYID), ");
//		 sb.append("  (SELECT COUNT(ETCA_ETCM_KEYID) ATTEND, ETCA_ETCM_KEYID FROM ENT_TL_TRGCALEMPATSCORE,ENT_TL_TRGCALMST WHERE ETCA_ETCM_KEYID=ETCM_KEYID AND ETCA_PRSENTABSENT='P' GROUP BY ETCA_ETCM_KEYID ) ");
//		 sb.append("  WHERE TOPI_KEYID(+)    =ETCM_TOPICID AND TCAT_KEYID (+) =ETCM_TOPICCATEGORY AND TRDM_KEYID(+)=ETCM_TRAININGFUNCTION ");
//		 sb.append(" AND EMPM_KEYID(+)      =ETCM_COMPLETEDBY AND VENU_KEYID(+) =ETCM_VENUE AND ETCE_ETCM_KEYID(+) = ETCM_KEYID AND ETCA_ETCM_KEYID(+) = ETCM_KEYID  "+condSql);
//
//		  sb.append("  GROUP BY ETCM_KEYID,  ETCM_DMT,  ETCM_JH,  ETCM_FLID,  ETCM_CREATEDATETIME,  ETCM_ANCHOREDBY,  ETCM_ANCHOREDBY , ");
//		  sb.append("   TOPI_NAME,  ETCM_TOPICID,  TCAT_NAME,  ETCM_TOPICCATEGORY,  ETCM_FUNCTION, TRDM_NAME,  ETCM_TRAININGFUNCTION, ");
//		  sb.append("   VENU_NAME , ETCM_VENUE, ETCM_GENERAL, ETCM_CALDATE,  ETCM_PERMITTEDSTRENGTH,  ETCM_MAX_DURATION, ");
//		  sb.append("   ETCM_ASSESSMENTREQUIRED,  ETCM_MATERIALREADY,  ETCM_MARKBASED,  ETCM_TEMPFIELD6,  ETCM_CHKCOMPLETED , ");
//		  sb.append("  ETCM_CHKCOMPLETED,   PLANED,  ATTEND,  ETCA_ETCM_KEYID,  ETCE_ETCM_KEYID,  ETCM_COMPLETEDDATE, ");
//		  sb.append("  EMPM_NAME ,  EMPM_CODE ,  ETCM_COMPLETEDBY,  ETCM_RATING, ");
//		  sb.append("  ETCM_COMMENTS,  ETCM_UNIQUEPOS, ETCM_MSD, ETCM_LOCATION ORDER BY ETCM_KEYID DESC ");
//		/*
//		sb.append("   SELECT DISTINCT ETCM_KEYID,ETCM_DMT,ETCM_JH,ETCM_FLID,ETCM_LOCATION,ETCM_CREATEDATETIME,ETCM_ANCHOREDBY,ETCM_ANCHOREDBY ANCHOREDBYid,TOPI_NAME, ");
//		sb.append("   ETCM_TOPICID, TCAT_NAME, ETCM_TOPICCATEGORY,DECODE(ETCM_FUNCTION,'NB','Need Basic','SD','Section D','SI','Skill Index','KU','Knowledge Upgradation','KSA','KSA (GAP Based)','RT','Refresher Training','EHS','EHS' )AS IDENFIEDTHG,");
//		sb.append("   ETCM_FUNCTION,TRDM_NAME,ETCM_TRAININGFUNCTION,VENU_NAME ,ETCM_VENUE,DECODE(ETCM_GENERAL,'Y','General',DECODE(ETCM_UNIQUEPOS,'Y','Unique Position',DECODE(ETCM_MSD,'Y','MSD',''))) AS UNIQPOSE,");
//		sb.append("   DECODE(ETCM_GENERAL,'Y','GN',DECODE(ETCM_UNIQUEPOS,'Y','UQ',DECODE(ETCM_MSD,'Y','MS','')))  AS UNIQPOSEID,");
//		sb.append("   ETCM_CALDATE,ETCM_PERMITTEDSTRENGTH,ETCM_MAX_DURATION,DECODE(ETCM_ASSESSMENTREQUIRED,'Y','YES','NO'),");
//		sb.append("   ETCM_ASSESSMENTREQUIRED, DECODE(ETCM_MATERIALREADY,'Y','YES','NO'),ETCM_MATERIALREADY, DECODE(ETCM_MARKBASED,'Y','YES','NO'), ETCM_MARKBASED,  '' AS UNIQPOS, '' AS EMPLOYEEADD,'' AS EMPATTEDNCE, DECODE(ETCM_TEMPFIELD6,'Y','YES','NO')     AS ASSEMNTCOMPL, ");   
//		sb.append("   ETCM_TEMPFIELD6, DECODE(ETCM_CHKCOMPLETED,'Y','YES','NO')  AS TRNCOMPL,ETCM_CHKCOMPLETED AS TRNCOMPLID,DECODE(ETCM_CHKCOMPLETED,'Y',ETCM_COMPLETEDDATE,'') AS COMPLTDATE, EMPM_NAME ||'-' ||EMPM_CODE  AS COMPLETEDBY , ");
//		sb.append("   ETCM_COMPLETEDBY,ETCM_RATING,ETCM_RATING as RatingId,ETCM_COMMENTS,'' AS FILEMGR,    PLANEDEMP,   ATTNDEMPL,  ROUND((ATTNDEMPL/PLANEDEMP*100),2)  ADHERENCE ");
//		sb.append("   FROM ENT_TL_TRGCALMST, ENT_TL_TOPICMST,ENT_TL_VENUEMST,ENT_TL_TOPICCATEGORYMST, GEN_TL_TRADEMST,ENT_TL_TRGCALEMP,ENT_TL_TRGCALEMPATSCORE, ");
//		sb.append("   GEN_TL_EMPLOYEEMST,GEN_MV_FLIDHIERARCHY,(SELECT ETCE_ETCM_KEYID KEYID, COUNT(ETCM_KEYID) PLANEDEMP,  ");
//		sb.append(" COUNT(ETCA_KEYID) ATTNDEMPL ");
//		sb.append(" FROM   ENT_TL_TRGCALEMP,  ");
//		sb.append(" ENT_TL_TRGCALEMPATSCORE,  ");
//		sb.append(" ENT_TL_TRGCALMST,GEN_MV_FLIDHIERARCHY  ");
//		sb.append(" WHERE ETCA_ETCE_KEYID(+) =ETCE_KEYID  ");
//		sb.append(" AND ETCA_ETCM_KEYID(+)=ETCM_KEYID  ");
//		sb.append(" AND ETCE_ETCM_KEYID=ETCM_KEYID(+)   "+condSql+" "); 
//		sb.append(" GROUP BY ETCE_ETCM_KEYID ,ETCM_KEYID) WHERE TOPI_KEYID(+)  =ETCM_TOPICID AND TCAT_KEYID (+)=ETCM_TOPICCATEGORY ");
//		sb.append("   AND TRDM_KEYID(+)=ETCM_TRAININGFUNCTION ");
//		sb.append("   AND EMPM_KEYID(+)=ETCM_COMPLETEDBY ");
//		sb.append("   AND VENU_KEYID(+)=ETCM_VENUE ");
//		sb.append("   AND KEYID(+) = ETCM_KEYID " );
//		sb.append("     "+condSql );
//		sb.append("   GROUP BY ETCM_KEYID,  ETCM_DMT,  ETCM_JH,  ETCM_FLID,  ETCM_CREATEDATETIME,  ETCM_ANCHOREDBY, ");
//		sb.append("   ETCM_ANCHOREDBY ,  TOPI_NAME,  ETCM_TOPICID,  TCAT_NAME,  ETCM_TOPICCATEGORY,  ETCM_FUNCTION,  TRDM_NAME,  ETCM_TRAININGFUNCTION, ");
//		sb.append("   VENU_NAME ,  ETCM_VENUE,  ETCM_GENERAL,    ETCM_CALDATE,  ETCM_PERMITTEDSTRENGTH,  ETCM_MAX_DURATION,  ");
//		sb.append("   ETCM_ASSESSMENTREQUIRED, ETCM_MATERIALREADY,  ETCM_MARKBASED, ETCM_TEMPFIELD6, ETCM_CHKCOMPLETED , ETCM_CHKCOMPLETED, PLANEDEMP,  ATTNDEMPL, ");
//		sb.append("   ETCM_COMPLETEDDATE, EMPM_NAME ,EMPM_CODE , ETCM_COMPLETEDBY,ETCM_RATING,ETCM_COMMENTS,ETCM_UNIQUEPOS,ETCM_MSD,ETCM_LOCATION ORDER BY ETCM_KEYID DESC");
//*/
//		
//		CommonMessage.debugMsg(sb+" query in sid the DAO Impl");
//		
//		List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sb.toString(),null);
//		
////		 getDataList= dbActionTemplate.processFunctionCalls("ENT_NEW_REPORTS.ENT_TL_NEWTRAININGCALENTRYGRD", paramvalues); 
//		// getDataList= dbActionTemplate.processFunctionCalls("ENT_NEW_REPORTS.ENT_TL_NEWTRAININGCALMSTGRD", paramvalues); 
//
//		if( commonFilter.getViewClick() == 'Y'){
//		     String totalCnt = paramvalues.get(0);
//		     //CommonMessage.debugMsg("totalCnt...."+totalCnt);
//		     boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
//		    CommonMessage.debugMsg("IS isInteger"+isInteger);
//		     if(  isInteger ){
//		     	
//		     	commonFilter.setTotalRecordCnt((int) Long.parseLong(totalCnt));
//		      }
//		 }
//		return  dataList ;
//	
//	
//}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//			
//		}
	 
	
	// ------------ vignesh 24NOV2025 -----------------------------//
	public String deleteDetailRecord(String keyId, String gridId, String topicId) throws Exception,BusinessApplicationExceptions{
		// TODO Auto-generated method stub
		try{
			String sqlsDelete ="";			
		if("facultyGrid".equals(gridId))
		{
			sqlsDelete = "delete from "+TableNames.TBL_ENT_TL_TRGFACULTY+" where ETCF_KEYID = '"+keyId+"'";
				
		}else if("calUniquePosGrid".equals(gridId)){
			sqlsDelete= "delete from "+TableNames.TBL_ENT_TL_TRGCALUNQP+" where ETCU_KEYID='"+keyId+"' ";
		  }
		else{
			sqlsDelete = "delete from "+TableNames.TBL_ENT_TL_TRGCALSESSION+" where ETCS_KEYID = '"+keyId+"'" ;
		}
		  CommonMessage.debugMsg("The Sqls Delete:::"+sqlsDelete);
		  dbActionTemplate.executeStatement(sqlsDelete);
		  return "Data Deleted Successfully";
		}
		catch(BusinessApplicationExceptions e){
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
			return "Data Not Deleted";	
		}
	}
	
	public List<EntTlTrgCalUnqp> CreateMultipleUnique(List<EntTlTrgCalUnqp> entTlTrgCalUnqpLink) throws Exception{
		EntTlTrgCalUnqpSql entTlTrgCalUnqpSql=new EntTlTrgCalUnqpSql();
		
		try
		{
			     List<String> sqls = new ArrayList<String>();
			     List <EntTlTrgCalUnqp> methodslist = entTlTrgCalUnqpLink;
			     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),EntTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP,15,"ETU", null,null);
				 CommonMessage.debugMsg("sequenceNumber in IF"+sequenceNumber);
				 for(EntTlTrgCalUnqp trgcalUnqPositionLink:methodslist){
						String seqNo=sequenceNumber.getSequnceNumber();
						CommonMessage.debugMsg("The seqNo::::"+seqNo);
						trgcalUnqPositionLink.setEtcuKeyid(seqNo);
						trgcalUnqPositionLink.setEtcuEtcmKeyid(trgcalUnqPositionLink.getEtcuEtcmKeyid());
					//	trgcalUnqPositionLink.setEtcuRoleDmt(trgcalUnqPositionLink.getEtcuRoleDmt());
						sqls.add(EntTlTrgCalUnqpSql.getInsertSql(entTlTrgCalUnqpSql.getFtymDbFields(),trgcalUnqPositionLink.getSaveArray()));
				        CommonMessage.debugMsg("The sqls Query:"+sqls);
				 }
				 dbActionTemplate.executeStatements(sqls);
		}
		catch(BusinessApplicationExceptions e)
		{
			CommonMessage.debugMsg("Business Application   :"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage()); 
		}
		return null;
	}
	public String getempattn(String calid) throws Exception {
		// TODO Auto-generated method stub
		String sql="select COUNT(*) FROM ENT_TL_TRgcalempatscore WHERE ETCA_ETCM_KEYID='"+calid+"'";
		CommonMessage.debugMsg("sql"+sql);
		return dbActionTemplate.getSingleValue(sql);
	}
	@Override
	public String getCutoff(String calid) throws Exception {
		// TODO Auto-generated method stub
		String sql="select ETCA_CUTOFF FROM ENT_TL_TRgcalempatscore WHERE ETCA_KEYID = (SELECT MIN(ETCA_KEYID) from  ENT_TL_TRgcalempatscore where ETCA_ETCM_KEYID='"+calid+"')";
		CommonMessage.debugMsg("sql"+sql);
		return dbActionTemplate.getSingleValue(sql);
	}
	@Override
	public String getMaxmarks(String calid) throws Exception {
		// TODO Auto-generated method stub
		String sql="select ETCA_MAXMARKS FROM ENT_TL_TRgcalempatscore WHERE ETCA_KEYID = (SELECT MIN(ETCA_KEYID) from  ENT_TL_TRgcalempatscore where  ETCA_ETCM_KEYID='"+calid+"')";
		CommonMessage.debugMsg("sql"+sql);
		return dbActionTemplate.getSingleValue(sql);
	}
	@Override
	public String chkAssesmentComplted(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		if(commonFilter.getMaintMode().equals("modify"))
		{
			String sql1="select COUNT(*) FROM ENT_TL_TRgcalempatscore where ETCA_ASSESSMENTCOM='Y' and ETCA_ETCM_KEYID='"+commonFilter.getKey()+"'";
			String quad=dbActionTemplate.getSingleValue(sql1);
			//CommonMessage.debugMsg("sql"+sql1);
			//CommonMessage.debugMsg("quad"+quad);
			String sql2="SELECT COUNT(*) FROM ENT_TL_TRGCALEMP where ETCE_ETCM_KEYID='"+commonFilter.getKey()+"'";
			String emp=dbActionTemplate.getSingleValue(sql2);
			//CommonMessage.debugMsg("sql"+sql2);
			//CommonMessage.debugMsg("emp"+emp);
			if(quad.equals(emp))
			{
				//String sql3="UPDATE ENT_TL_TRgcalempatscore  SET ETCA_ASSESSMENTCOM='Y' where  ETCA_ETCM_KEYID='"+commonFilter.getKey()+"'";
			   // dbActionTemplate.getSingleValue(sql3);
				return "1";
			}
			else{
				return "0";
			}
		
		}
		else{
			
			String sql1="select COUNT(*) FROM ENT_TL_TRgcalempatscore where  ETCA_ETCM_KEYID='"+commonFilter.getKey()+"'";
			String quad=dbActionTemplate.getSingleValue(sql1);
			//CommonMessage.debugMsg("sql"+sql1);
			//CommonMessage.debugMsg("quad"+quad);
			String sql2="SELECT COUNT(*) FROM ENT_TL_TRGCALEMP where ETCE_ETCM_KEYID='"+commonFilter.getKey()+"'";
			String emp=dbActionTemplate.getSingleValue(sql2);
			//CommonMessage.debugMsg("sql"+sql2);
			//CommonMessage.debugMsg("emp"+emp);
			if(quad.equals(emp))
			{
				String sql3="UPDATE ENT_TL_TRgcalempatscore  SET ETCA_ASSESSMENTCOM='Y' where  ETCA_ETCM_KEYID='"+commonFilter.getKey()+"'";
			    dbActionTemplate.getSingleValue(sql3);
				return "1";
			}
			else{
				return "0";
			}
		}
	}
	@Override
	public EntTlTragcalmst getselectdata(String calid) throws Exception {
		EntTlTragcalmst entTlTragcalmst=new EntTlTragcalmst();
		String sql = EntTlTragcalmstSql.selectDataMst();
		
		Object args[] = new Object[] {calid};
		
		entTlTragcalmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		
		return entTlTragcalmst;
	}
	@Override
	public String getempdata(String calid) throws Exception {
		// TODO Auto-generated method stub
		
		String emplinkcnt=dbActionTemplate.getSingleValue("select count(*) from ENT_TL_TRGCALEMPATSCORE WHERE ETCA_ETCM_KEYID='"+calid+"'");
	    return emplinkcnt;
	   
	}
	@Override
	public String getTrgDateData(String keyId) throws Exception {
		// TODO Auto-generated method stub
		
		String sql="SELECT TO_CHAR(ETCM_CALDATE,'DD-MON-YYYY') FROM ENT_TL_TRGCALMST WHERE ETCM_KEYID='"+keyId+"'";
		CommonMessage.debugMsg("sql"+sql);
		return dbActionTemplate.getSingleValue(sql);

	}
	public String IsTrainingCompleted(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		String result=null;
	    String emplinkcnt=dbActionTemplate.getSingleValue("select count(*) from ENT_TL_TRGCALEMP WHERE ETCE_ETCM_KEYID='"+commonFilter.getKey()+"'");
		String atvalue=dbActionTemplate.getSingleValue("select count(*) from ENT_TL_TRGCALEMPATSCORE WHERE ETCA_ETCM_KEYID='"+commonFilter.getKey()+"'");
		if(!emplinkcnt.equals("0"))
		{
		if(emplinkcnt.equals(atvalue))
		{
			result="Y";
		}
		else{
			result="N";
		}
		return result;
		}
		else{
			return "N";
		}
	}
	
	@Override
	public Workbook getTrainingCalendarListExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		 try{
				
			 rs =   getTrainingCalenderResultSet(commonFilter);
			 rs = getTrainingCalenderResultSetModify(commonFilter);
			 
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
			 // --- CHANGING 2 TO 1 VIGNESH
				return excelUtils.writeToExcel(rs,format,1,0,0 );
				
			   }catch(Exception e){
				   e.printStackTrace();
				   
			   }
		 /*finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }*/
		return null;
	}
	
	// -- altering for excel download Vignesh -05dec2025--//
//	private ResultSet getTrainingCalenderResultSet(CommonFilter commonFilter) throws Exception {
//		List<String> paramValues = getFilterParamValues(commonFilter);
//		CommonMessage.debugMsg(paramValues +" paramValuesparamValues in side daoimpl");
//		return dbActionTemplate.NewdbFunctionCall2("ENT_TL_GRIDTRGCALMODIFYEXCEL", paramValues);
//	}
	private ResultSet getTrainingCalenderResultSetModify(CommonFilter commonFilter) throws Exception {
	    String condParms    = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	    commonFilter.setFromRow(null); 
	    commonFilter.setToRow(null); 
	    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

	    String mode = commonFilter.getType();
	    if (UIUtils.isValidKeyId(mode)) condParms += "Mode=" + mode + ";";
	    if (UIUtils.isValidKeyId(commonFilter.getKey()))       condParms += "KEY=" + commonFilter.getKey() + ";";
	    if (UIUtils.isValidKeyId(commonFilter.getSectionId())) condParms += "SECTIONID=" + commonFilter.getSectionId() + ";";
	    if (UIUtils.isValidKeyId(commonFilter.getCellId()))    condParms += "CELLID=" + commonFilter.getCellId() + ";";
	    if (UIUtils.isValidKeyId(commonFilter.getTrarId()))    condParms += "TRARID=" + commonFilter.getTrarId() + ";";
	    if (UIUtils.isValidKeyId(commonFilter.getUniquePos())) condParms += "UNIQUEPOS=" + commonFilter.getUniquePos() + ";";

	    CommonMessage.debugMsg("Excel(MOD) condParms = " + condParms);
	    CommonMessage.debugMsg("Excel(MOD) commonParams = " + commonParams);

	    List<String> paramValues = new ArrayList<>();
	    paramValues.add(condParms);
	    paramValues.add(commonParams);
      // changing -- modify excel new to view 
	    return dbActionTemplate.NewdbFunctionCall2("ENT_TL_GRIDTRGCALVIEWEXCELNEW", paramValues);
	}

	
	
	
	private ResultSet getTrainingCalenderResultSet(CommonFilter commonFilter) throws Exception {
	    // 1) Base (same as grid)
	    String condParms    = FilterCondSql.getAbnRelatedConditionStr(commonFilter); // FLID, FROM/TO dates, months, ISMONTHWISE, etc.
	    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);       // FILTERCOND, ISTOTALCNT, FROMTOROW, GRIDFILTER

	    // 2) EXACTLY like getListTrgCalendarView (add the rest)
	    String mode = commonFilter.getType();
	    if (UIUtils.isValidKeyId(mode)) {
	        condParms += "Mode=" + mode + ";";
	    }
	    if (UIUtils.isValidKeyId(commonFilter.getSectionId())) {
	        condParms += "SECTIONID=" + commonFilter.getSectionId() + ";"; // m.ETCM_DMT
	    }
	    if (UIUtils.isValidKeyId(commonFilter.getCellId())) {
	        condParms += "CELLID=" + commonFilter.getCellId() + ";";       // m.ETCM_JH
	    }
	    if (UIUtils.isValidKeyId(commonFilter.getTrarId())) {
	        condParms += "TRARID=" + commonFilter.getTrarId() + ";";       // m.ETCM_TRAININGFUNCTION
	    }
	    if (UIUtils.isValidKeyId(commonFilter.getUniquePos())) {
	        condParms += "UNIQUEPOS=" + commonFilter.getUniquePos() + ";"; // role filter
	    }

	    // 3) Debug so you can SEE what goes to PG
	    CommonMessage.debugMsg("Excel condParms = " + condParms);
	    CommonMessage.debugMsg("Excel commonParams = " + commonParams);

	    // 4) Call function with the two params IN THIS ORDER
	    List<String> paramValues = new ArrayList<>();
	    paramValues.add(condParms);
	    paramValues.add(commonParams);

	    return dbActionTemplate.NewdbFunctionCall2("ENT_TL_GRIDTRGCALMODIFYEXCEL", paramValues);
	}
	
	

	// -- altering for excel download Vignesh -05dec2025--//
	
	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List <String>  paramvalues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		paramvalues.add(condParms);
		paramvalues.add(commonParams);
		return paramvalues;
	}
	
	
}

	

	
	

