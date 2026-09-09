package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.SkillIndexGridEntryDao;
import com.akranta.tpm.dao.sql.EntTlSkillindexassessdtlSql;
import com.akranta.tpm.dao.sql.EntTlSkillindexassessmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillindexassessdtl;
import com.akranta.tpm.model.EntTlSkillindexassessmst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;

public class SkillIndexGridEntryDaoImpl implements SkillIndexGridEntryDao {
private DBActionTemplate dbActionTemplate;
 FunctionCallApi fnCallApi; 
	
	public SkillIndexGridEntryDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void SkillIndexGridEntryDaoImplJwt(String JwtToken) 
	{
		try{
	
			fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	@Override
	public List<String[]> getTopicTask(CommonFilter commonFilter) throws Exception {
		try {
			try
			{
				boolean isInteger = false;
				//List<String> paramValues = getFilterParamValues(commonFilter);
			//	CommonMessage.debugMsg(" Common Params ...... "+paramValues);
				
				List<String> paramValues = new ArrayList<String>();
				String condParms=null;
				String condParms1=null;
				String condParms2=null;
				String condParms3=null;
				
				CommonMessage.debugMsg("commonFilter.getStartDate()"+commonFilter.getStartDate());
				if(UIUtils.isValidKeyId(commonFilter.getFlid()))
					condParms =commonFilter.getFlid();
				if (UIUtils.isValidKeyId(commonFilter.getUniquePos()))
					condParms1 =commonFilter.getUniquePos();	
				if (UIUtils.isValidKeyId(commonFilter.getFromDate()))
					condParms2 =commonFilter.getFromDate();	
				if (UIUtils.isValidKeyId(commonFilter.getEmpch()))
					condParms3 =commonFilter.getEmpch();
				
				
				String emp = commonFilter.getEmpch();
			    // Clean bad quotes
			    emp = emp.replace("'", "");  

			    // Now emp looks like: BCM01029,BCM00036,BCM00707...

			    // Wrap each value in quotes
			    emp = Arrays.stream(emp.split(","))
			                 .map(s -> "'" + s.trim() + "'")
			                 .collect(Collectors.joining(","));

			    condParms3 = emp;
				
				paramValues.add(condParms); 
				paramValues.add(condParms1);
				paramValues.add(condParms2);
				paramValues.add(condParms3);
				CommonMessage.debugMsg( "condParms condParms  "+condParms);
				CommonMessage.debugMsg( "condParms condParms1 "+condParms1);
				CommonMessage.debugMsg( "condParms condParms2 "+condParms2);
				CommonMessage.debugMsg( "condParms condParms3 "+condParms3);
				//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_SKILLINDEXREVIEWW", paramValues);
				List<String[]> dataList =  fnCallApi.callMultiParamFunction("GEN_FN_SKILLINDEXREVIEWW_SB", paramValues,4,true);
				
				for(String[] arr:dataList) 
				{
					CommonMessage.debugMsg(Arrays.toString(arr));
				}
				CommonMessage.debugMsg("dataList53453      "+dataList.size());
				//ENT_PC_EDUANDTRAINING.ENT_FN_SKILLINDEXREPORT
				//ENT_FN_SKILLINDEXCHART
				CommonMessage.debugMsg("commonFilter.getViewClick()=="+commonFilter.getViewClick());
				
				
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
				return dataList; 
			}
			catch (Exception e)
			{
				e.printStackTrace(); 
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//Query change By Swetha - SKill assessment November 12
	public List<String[]> getEmpList(CommonFilter commonfilter) throws Exception { 
		StringBuffer sql = new StringBuffer();
		
		String flid = commonfilter.getFlid();
		String uniqPosid = commonfilter.getEmmLinkKeyId();
		String reviewDate = commonfilter.getStartDate();
		//Strin empId = commonfilter.setEmpch(empId);
		
		//sql.append("  select Empm_keyid as keyid,''as checks,empm_code as code,empm_name as name,TOT_SCORE, EMPM_ROLEID as roleid "); 
		sql.append("  SELECT DISTINCT Empm_keyid AS keyid, '' AS checks, empm_code AS code, empm_name AS name, LASTDATE, TOT_SCORE ");
		sql.append("  FROM  ( ");
		
		/*sql.append("  select Empm_keyid ,empm_code ,empm_name ,EMPM_ROLEID ");
		sql.append("  from GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM where 1=1 ");
		sql.append("  AND EMPM_KEYID = FRT_EMPM_KEYID ");
		sql.append("  AND FRT_FNLN_KEYID = '"+commonfilter.getFlid()+"' ");
		*/
		
		CommonMessage.debugMsg("reviewDate=== "+reviewDate);
		
	    StringBuffer lstSql = new  StringBuffer();
	    lstSql.append("SELECT TO_CHAR(MAX(siam_reviewdate), 'DD-MON-YYYY') ");
	    lstSql.append("FROM ent_tl_skillindexassessmst m ");
	    lstSql.append("JOIN ent_tl_skillindexassessdtl d ON d.siad_siam_keyid = m.siam_keyid ");
	    lstSql.append("WHERE m.siam_flid = '" + flid + "' ");
	    lstSql.append("AND m.siam_uniqueposid = '" + uniqPosid + "' ");
	    
	    String lastDoneDate = dbActionTemplate.getSingleValue(lstSql.toString());
	    
	    CommonMessage.debugMsg("lastDoneDate=== "+lastDoneDate);
	    
	    if (! UIUtils.isValidKeyId( reviewDate))
	    	reviewDate = lastDoneDate;
	    
	    sql.append(" SELECT DISTINCT Empm_keyid, empm_code, empm_name, EMPM_ROLEID ");
	    sql.append(" FROM GEN_TL_EMPLOYEEMST ");
	    sql.append(" INNER JOIN GEN_TL_FNLNROLETEAM ON EMPM_KEYID = FRT_EMPM_KEYID ");
	    sql.append(" INNER JOIN GEN_TL_TEAMTRADELINK ON FRP_FRT_KEYID = FRT_KEYID ");
	    sql.append(" INNER JOIN GEN_TL_TRADEMST ON TRDM_KEYID = FRP_TRADEID ");
	    sql.append(" INNER JOIN GEN_TL_EMPTYPE_MST ON TRDM_CLASSIFICATION = ETPM_CODE ");
	    sql.append(" WHERE FRT_FNLN_KEYID = '" + commonfilter.getFlid() + "' ");
	    sql.append(" AND ETPM_KEYID = '" + uniqPosid + "' ");


		//ADDED FOR AVOID MANAGERS - 21-JUL-2014
	    sql.append(" AND EMPM_ACTIVE = 'Y' AND EMPM_EMPLOYEETYPE NOT IN ('M') ");
		
		sql.append(FilterCondSql.makeGridFilterCond(commonfilter.getGridFilter()));
		sql.append(" ) AS emp_data ");
		sql.append(" LEFT JOIN ( ");
		sql.append(" SELECT SIAD_EMPM_KEYID, ROUND(SUM(SIAD_SCORE)::numeric / COUNT(DISTINCT SIAD_CRITERIAID), 2) AS TOT_SCORE, ");
		sql.append(" TO_CHAR(MAX(SIAM_REVIEWDATE), 'DD-MON-YYYY') AS LASTDATE ");
		sql.append(" FROM ENT_TL_SKILLINDEXASSESSMST ");
		sql.append(" INNER JOIN ENT_TL_SKILLINDEXASSESSDTL ON SIAD_SIAM_KEYID = SIAM_KEYID ");
		sql.append(" INNER JOIN ( ");
		sql.append("   SELECT SIAD_EMPM_KEYID AS EMP, TO_CHAR(MAX(siam_reviewdate), 'DD-MON-YYYY') AS MAXDATE ");
		sql.append("   FROM ent_tl_skillindexassessmst ");
		sql.append("   INNER JOIN ent_tl_skillindexassessdtl ON siad_siam_keyid = siam_keyid ");
		sql.append("   WHERE siam_flid = '" + flid + "' ");
		sql.append("   AND siam_uniqueposid = '" + uniqPosid + "' ");
		sql.append("   GROUP BY SIAD_EMPM_KEYID ");
		sql.append(" ) AS max_dates ON EMP = SIAD_EMPM_KEYID AND TO_CHAR(siam_reviewdate, 'DD-MON-YYYY') = MAXDATE ");
		sql.append(" WHERE SIAM_FLID = '" + flid + "' ");
		sql.append(" AND SIAM_UNIQUEPOSID = '" + uniqPosid + "' ");
		
	//	sql.append( " AND TRUNC(SIAM_REVIEWDATE)='" + reviewDate + "' ");
		
		sql.append(" GROUP BY SIAD_EMPM_KEYID ");
		sql.append(" ) AS score_data ON SIAD_EMPM_KEYID = Empm_keyid ");
		sql.append(" ORDER BY TOT_SCORE DESC NULLS LAST ");
		
		
		CommonMessage.debugMsg("sql ........."+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql.toString());
		return dataList;
	}
	//Query change By Swetha - SKill assessment November 12
	
	public EntTlSkillindexassessmst createAssement( List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmst, 
			List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl) throws Exception {
	
		List<String> sqls = new ArrayList<String>();
		EntTlSkillindexassessmstSql entTlSkillindexassessmstSql = new EntTlSkillindexassessmstSql();
		EntTlSkillindexassessdtlSql entTlSkillindexassessdtlSql = new EntTlSkillindexassessdtlSql();
		
		try {
			
			//MASTER
			for( EntTlSkillindexassessmst entTlSklassetMstData : lstEntTlSkillindexassessmst)
			{	
				if (!UIUtils.isValidKeyId( entTlSklassetMstData.getSiamKeyid())) {
					entTlSklassetMstData.setSiamKeyid(dbActionTemplate.getSequenceNumber(EntTlSkillindexassessmstSql.TBL_ENT_TL_SKILLINDEXASSESSMST, 10, "SIAM",  null,null)); // set the sequnce number 
					sqls.add(EntTlSkillindexassessmstSql.getInsertSql(entTlSkillindexassessmstSql.getSiamDbFields(), entTlSklassetMstData.getSaveArray())); // add insert sql for master table
				}
				else {
					sqls.add(EntTlSkillindexassessmstSql.getUpdateSql(entTlSkillindexassessmstSql.getSiamDbFields(), entTlSklassetMstData.getSaveArray()));
				}
			}
			
			//DETAILS
			int i=0;
			int mastNo=0;
			String mastKeyid="0";
			
			if (lstEntTlSkillindexassessmst.size()>=0)
				mastKeyid = lstEntTlSkillindexassessmst.get(0).getSiamKeyid();
			
			for( EntTlSkillindexassessdtl entTlSklassetDtlData : lstEntTlSkillindexassessdtl)
			{	
				
				//CommonMessage.debugMsg("entTlSklassetDtlData.getSiadScore()="+entTlSklassetDtlData.getSiadScore());
					//CommonMessage.debugMsg("i % lstEntTlSkillindexassessmst.size()="+ i % lstEntTlSkillindexassessmst.size());
					if ( ( i % (lstEntTlSkillindexassessdtl.size() /  lstEntTlSkillindexassessmst.size()) ) == 0 ) {					
						mastKeyid = lstEntTlSkillindexassessmst.get(mastNo).getSiamKeyid();
						//CommonMessage.debugMsg("mastKeyid=mastNo="+mastKeyid+"="+mastNo);
						mastNo = mastNo+1;
					}
					
					entTlSklassetDtlData.setSiadSiamKeyid(mastKeyid);
					
					i = i+1;
				
					//CommonMessage.debugMsg("UIUtils.isValidKeyId( entTlSklassetDtlData.getSiadScore())"+UIUtils.isValidKeyId( entTlSklassetDtlData.getSiadScore()));

					String sql ="";
					sql = " SELECT SIAD_KEYID FROM ENT_TL_SKILLINDEXASSESSDTL WHERE SIAD_SIAM_KEYID = '" + entTlSklassetDtlData.getSiadSiamKeyid() + "' "; 
					sql = sql + " AND SIAD_EMPM_KEYID = '" + entTlSklassetDtlData.getSiadEmpmKeyid() + "'  ";
					sql = sql + " AND SIAD_REVIEWID = '" + entTlSklassetDtlData.getSiadReviewid() + "' ";
					
					
					CommonMessage.debugMsg("sql==="+sql);
					String dtlKeyid = dbActionTemplate.getSingleValue( sql);
					
					//CommonMessage.debugMsg("dtlKeyid="+dtlKeyid);
					
					//if ( UIUtils.isValidKeyId( entTlSklassetDtlData.getSiadScore())) {
							
						if (!UIUtils.isValidKeyId( dtlKeyid )) {
							entTlSklassetDtlData.setSiadKeyid(dbActionTemplate.getSequenceNumber(EntTlSkillindexassessdtlSql.TBL_ENT_TL_SKILLINDEXASSESSDTL, 10, "SIAD", null,null)); // set the sequnce number 
							sqls.add(EntTlSkillindexassessdtlSql.getInsertSql(entTlSkillindexassessdtlSql.getSiadDbFields(), entTlSklassetDtlData.getSaveArray())); // add insert sql for master table
						}
						else {
							entTlSklassetDtlData.setSiadKeyid(dtlKeyid);
							sqls.add(EntTlSkillindexassessdtlSql.getUpdateSql(entTlSkillindexassessdtlSql.getSiadDbFields(), entTlSklassetDtlData.getSaveArray()));
						}

					//}
			}
			
			// UPDATE TOTAL
			StringBuffer sql =  new StringBuffer();

			sql.append(" UPDATE ENT_TL_SKILLINDEXASSESSDTL A SET");
			sql.append(" SIAD_TOTAL = (SELECT SUM(SIAD_SCORE) FROM ENT_TL_SKILLINDEXASSESSDTL B");
			sql.append(" WHERE B.SIAD_SIAM_KEYID = A.SIAD_SIAM_KEYID AND B.SIAD_CRITERIAID = A.SIAD_CRITERIAID ");
			sql.append(" AND B.SIAD_EMPM_KEYID = A.SIAD_EMPM_KEYID) " );
			sql.append(" WHERE SIAD_SIAM_KEYID = '" + lstEntTlSkillindexassessmst.get(0).getSiamKeyid() + "' ");
			
			StringBuffer sqld =  new StringBuffer();
			List<String> sqlsd = new ArrayList<String>();

			sqld.append(" DELETE FROM ENT_TL_SKILLINDEXASSESSDTL WHERE SIAD_EMPM_KEYID IN ( ");
			sqld.append(" SELECT EMP FROM( ");
			sqld.append(" SELECT SIAD_EMPM_KEYID EMP,SUM(SIAD_SCORE) SCORE FROM ENT_TL_SKILLINDEXASSESSDTL,ENT_TL_SKILLINDEXASSESSMST ");
			sqld.append(" WHERE SIAM_FLID='"+lstEntTlSkillindexassessmst.get(0).getSiamFlid()+"'		");
			sqld.append(" AND SIAM_UNIQUEPOSID='"+lstEntTlSkillindexassessmst.get(0).getSiamUniqueposid()+"' 	");	
			sqld.append(" AND SIAM_REVIEWDATE='"+lstEntTlSkillindexassessmst.get(0).getSiamReviewdate()+"'  		");
			sqld.append(" AND SIAM_KEYID=SIAD_SIAM_KEYID 	");	
			sqld.append(" GROUP  BY SIAD_EMPM_KEYID) WHERE SCORE<=0)");
			
			sqls.add(sql.toString());
			sqlsd.add(sqld.toString());
			dbActionTemplate.executeStatements(sqls);
			dbActionTemplate.executeStatements(sqlsd);

			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return lstEntTlSkillindexassessmst.get(0);
	}
	
private List<String> getFilterParamValues(CommonFilter commonFilter) {
		
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		List<String> paramValues  = new ArrayList<String>();
		
		condParms += "FLID="+commonFilter.getFlid()+";UNIQPOSID="+commonFilter.getUniquePos()+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

@Override
public List<String[]> getTopicTaskModify(CommonFilter commonFilter)
		throws Exception {
	
		try
		{
			boolean isInteger = false;
			//List<String> paramValues = getFilterParamValues(commonFilter);
		//	CommonMessage.debugMsg(" Common Params ...... "+paramValues);
			
			List<String> paramValues = new ArrayList<String>();
			String condParms=null;
			String condParms1=null;
			String condParms2=null;
			String condParms3=null;
			
			CommonMessage.debugMsg("commonFilter.getStartDate()"+commonFilter.getStartDate());
			if(UIUtils.isValidKeyId(commonFilter.getFlid()))
				condParms =commonFilter.getFlid();
			if (UIUtils.isValidKeyId(commonFilter.getUniquePos()))
				condParms1 =commonFilter.getUniquePos();	
			if (UIUtils.isValidKeyId(commonFilter.getFromDate()))
				condParms2 =commonFilter.getFromDate();	
			if (UIUtils.isValidKeyId(commonFilter.getEmpch()))
				condParms3 =commonFilter.getEmpch();
			
			paramValues.add(condParms); 
			paramValues.add(condParms1);
			paramValues.add(condParms2);
			paramValues.add(condParms3);
			CommonMessage.debugMsg( "condParms condParms  "+condParms);
			CommonMessage.debugMsg( "condParms condParms1 "+condParms1);
			CommonMessage.debugMsg( "condParms condParms2 "+condParms2);
			CommonMessage.debugMsg( "condParms condParms3 "+condParms3);
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_SKILLINDEXREVIEWMODIFY", paramValues);
			
			List<String[]> dataList =  fnCallApi.callMultiParamFunction("GEN_FN_SKILLINDEXREVIEWMODIFY_SB", paramValues,4,true);
			CommonMessage.debugMsg("dataList53453      "+dataList.size());
			//ENT_PC_EDUANDTRAINING.ENT_FN_SKILLINDEXREPORT
			//ENT_FN_SKILLINDEXCHART
			CommonMessage.debugMsg("commonFilter.getViewClick()=="+commonFilter.getViewClick());
			
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			e.printStackTrace(); 
			
		}
	
	return null;
}

@Override
public List<String[]> getSIMainGrid(CommonFilter commonFilter, String reportName) {
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("SERVICE IMPL");
	List<String> paramValues = new ArrayList<String>();	
	CommonMessage.debugMsg("CommonFilter.getFlid()==="+commonFilter.getFlid());
	String condParms =FilterCondSql.getETRelatedStr(commonFilter);
	CommonMessage.debugMsg(condParms);
	if (UIUtils.isValidKeyId( commonFilter.getHrschkbox()))
	
			condParms+= "FORHEADER=Y;";
			

	//CommonMessage.debugMsg("SERVICE IMPL"+commonParams);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
	CommonMessage.debugMsg("test to............");
	CommonMessage.debugMsg("SERVICE IMPL"+commonParams);
	paramValues.add(condParms);
	paramValues.add(commonParams); 
	
	
	List<String[]> dataList =null;
	//dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("GEN_TROUBLESHOOTING.ENT_FN_SKILL_ASSEMENTMAINVIEW", paramValues);

/*	if (!reportName.equals("REVIEW") )
		
		dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_SKILL_ASSEMENTMAINVIEW", paramValues);
		
	else*/
		try {
			//dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_FN_SKILL_ASSEMENTMAINVIEW", paramValues);
			dataList =  fnCallApi.callFunction("ENT_FN_SKILL_ASSEMENTMAINVIEW_SB", paramValues,3,false );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	
	if( commonFilter.getViewClick() == 'Y')
	{
		String totalCnt = paramValues.get(0); 
		CommonMessage.debugMsg("totalCnt..."+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		if(  isInteger )
		{
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
		}
	}
	CommonMessage.debugMsg("out ");
	return dataList; 
}

@Override
public EntTlSkillindexassessmst updateSkillAssement(
		List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmstmst,
		List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl)
		throws Exception {
	List<String> sqls = new ArrayList<String>();
	EntTlSkillindexassessmstSql entTlSkillindexassessmstSql = new EntTlSkillindexassessmstSql();
	EntTlSkillindexassessdtlSql entTlSkillindexassessdtlSql = new EntTlSkillindexassessdtlSql();
	
	try {
		

		
		
		for( EntTlSkillindexassessmst entTlSklassetMstData : lstEntTlSkillindexassessmstmst)
		{	
			String sqlm ="";
			sqlm = " SELECT SIAM_KEYID FROM ENT_TL_SKILLINDEXASSESSMST WHERE SIAM_REVIEWDATE = '" + entTlSklassetMstData.getSiamReviewdate() + "' "; 
			sqlm = sqlm + " AND SIAM_UNIQUEPOSID = '" + entTlSklassetMstData.getSiamUniqueposid() + "'  ";
			sqlm = sqlm + " AND SIAM_FLID = '" + entTlSklassetMstData.getSiamFlid() + "'   ";
			
			
			CommonMessage.debugMsg("sql==="+sqlm);
			String mstKeyid = dbActionTemplate.getSingleValue( sqlm);
			
			entTlSklassetMstData.setSiamKeyid(mstKeyid);
			CommonMessage.debugMsg(entTlSklassetMstData.getSiamKeyid() +"Siam Keyid ");
			if (!UIUtils.isValidKeyId( entTlSklassetMstData.getSiamKeyid())) {
				entTlSklassetMstData.setSiamKeyid(dbActionTemplate.getSequenceNumber(EntTlSkillindexassessmstSql.TBL_ENT_TL_SKILLINDEXASSESSMST, 10, "SIAM",  null,null)); // set the sequnce number 
				sqls.add(EntTlSkillindexassessmstSql.getInsertSql(entTlSkillindexassessmstSql.getSiamDbFields(), entTlSklassetMstData.getSaveArray())); // add insert sql for master table
			}
			else {
				sqls.add(EntTlSkillindexassessmstSql.getUpdateSql(entTlSkillindexassessmstSql.getSiamDbFields(), entTlSklassetMstData.getSaveArray()));
			}
		}
		
		//DETAILS
		int i=0;
		int mastNo=0;
		String mastKeyid="0";
		
		if (lstEntTlSkillindexassessmstmst.size()>=0)
			mastKeyid = lstEntTlSkillindexassessmstmst.get(0).getSiamKeyid();
		
		for( EntTlSkillindexassessdtl entTlSklassetDtlData : lstEntTlSkillindexassessdtl)
		{	
			
			//CommonMessage.debugMsg("entTlSklassetDtlData.getSiadScore()="+entTlSklassetDtlData.getSiadScore());
				//CommonMessage.debugMsg("i % lstEntTlSkillindexassessmst.size()="+ i % lstEntTlSkillindexassessmst.size());
				if ( ( i % (lstEntTlSkillindexassessdtl.size() /  lstEntTlSkillindexassessmstmst.size()) ) == 0 ) {					
					mastKeyid = lstEntTlSkillindexassessmstmst.get(mastNo).getSiamKeyid();
					//CommonMessage.debugMsg("mastKeyid=mastNo="+mastKeyid+"="+mastNo);
					mastNo = mastNo+1;
				}
				
				entTlSklassetDtlData.setSiadSiamKeyid(mastKeyid);
				
				i = i+1;
			
				//CommonMessage.debugMsg("UIUtils.isValidKeyId( entTlSklassetDtlData.getSiadScore())"+UIUtils.isValidKeyId( entTlSklassetDtlData.getSiadScore()));

				String sql ="";
				sql = " SELECT SIAD_KEYID FROM ENT_TL_SKILLINDEXASSESSDTL WHERE SIAD_SIAM_KEYID = '" + entTlSklassetDtlData.getSiadSiamKeyid() + "' "; 
				sql = sql + " AND SIAD_EMPM_KEYID = '" + entTlSklassetDtlData.getSiadEmpmKeyid() + "'  ";
				sql = sql + " AND SIAD_REVIEWID = '" + entTlSklassetDtlData.getSiadReviewid() + "' ";
				
				
				CommonMessage.debugMsg("sql==="+sql);
				String dtlKeyid = dbActionTemplate.getSingleValue( sql);
				
				//CommonMessage.debugMsg("dtlKeyid="+dtlKeyid);
				
				//if ( UIUtils.isValidKeyId( entTlSklassetDtlData.getSiadScore())) {
						
					if (!UIUtils.isValidKeyId( dtlKeyid )) {
						entTlSklassetDtlData.setSiadKeyid(dbActionTemplate.getSequenceNumber(EntTlSkillindexassessdtlSql.TBL_ENT_TL_SKILLINDEXASSESSDTL, 10, "SIAD", null,null)); // set the sequnce number 
						sqls.add(EntTlSkillindexassessdtlSql.getInsertSql(entTlSkillindexassessdtlSql.getSiadDbFields(), entTlSklassetDtlData.getSaveArray())); // add insert sql for master table
					}
					else {
						entTlSklassetDtlData.setSiadKeyid(dtlKeyid);
						sqls.add(EntTlSkillindexassessdtlSql.getUpdateSql(entTlSkillindexassessdtlSql.getSiadDbFields(), entTlSklassetDtlData.getSaveArray()));
					}

				//}
		}
		
		// UPDATE TOTAL
		StringBuffer sql =  new StringBuffer();

		sql.append(" UPDATE ENT_TL_SKILLINDEXASSESSDTL A SET");
		sql.append(" SIAD_TOTAL = (SELECT SUM(SIAD_SCORE) FROM ENT_TL_SKILLINDEXASSESSDTL B");
		sql.append(" WHERE B.SIAD_SIAM_KEYID = A.SIAD_SIAM_KEYID AND B.SIAD_CRITERIAID = A.SIAD_CRITERIAID ");
		sql.append(" AND B.SIAD_EMPM_KEYID = A.SIAD_EMPM_KEYID) " );
		sql.append(" WHERE SIAD_SIAM_KEYID = '" + lstEntTlSkillindexassessmstmst.get(0).getSiamKeyid() + "' ");
		
		StringBuffer sqld =  new StringBuffer();
		List<String> sqlsd = new ArrayList<String>();

		sqld.append(" DELETE FROM ENT_TL_SKILLINDEXASSESSDTL WHERE SIAD_EMPM_KEYID IN ( ");
		sqld.append(" SELECT EMP FROM( ");
		sqld.append(" SELECT SIAD_EMPM_KEYID EMP,SUM(SIAD_SCORE) SCORE FROM ENT_TL_SKILLINDEXASSESSDTL,ENT_TL_SKILLINDEXASSESSMST ");
		sqld.append(" WHERE SIAM_FLID='"+lstEntTlSkillindexassessmstmst.get(0).getSiamFlid()+"'		");
		sqld.append(" AND SIAM_UNIQUEPOSID='"+lstEntTlSkillindexassessmstmst.get(0).getSiamUniqueposid()+"' 	");	
		sqld.append(" AND SIAM_REVIEWDATE='"+lstEntTlSkillindexassessmstmst.get(0).getSiamReviewdate()+"'  		");
		sqld.append(" AND SIAM_KEYID=SIAD_SIAM_KEYID 	");	
		sqld.append(" GROUP  BY SIAD_EMPM_KEYID) WHERE SCORE<=0)");
		
		sqls.add(sql.toString());
		sqlsd.add(sqld.toString());
		dbActionTemplate.executeStatements(sqls);
		dbActionTemplate.executeStatements(sqlsd);

		
	}catch( Exception e){
		throw new Exception(e.getMessage());
	}
	return lstEntTlSkillindexassessmstmst.get(0);
}

@Override
public Workbook getExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception {
		
			  ResultSet rs = null;
			   try
			   {
				   	rs =   getAbnormalityAllocationResultSet(commonFilter);
					ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
					List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
					
					XLConditionalFormats condFormat = new XLConditionalFormats();
					condFormat.setFontColor(new RGB(254,0,0)); //red font
					condFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
					condFormat.setFontHeightPoint((short)8);
					condFormat.setFontBoldWeight((short)20);
					condFormat.setDbChkColIndx(3);
					condFormat.setFromCol(2);
					condFormat.setToCol(2);
					condFormat.setOperator(ComparisonOperator.EQUAL);
					condFormat.setCondValue("RED"); //Tick
					condFormat.setIdentfier("RED");
					//condFormat.setBgColor(new RGB(213,255,195));
					condFormats.add(condFormat);
					excelUtils.setCondFormats(condFormats);
					return excelUtils.writeToExcel(rs,format, 3,0,0 );
			   }
			   
			   finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());	   
			   }
		}
	
		//return relatedMst;
	


//private ResultSet getAbnormalityAllocationResultSet(CommonFilter commonFilter) throws Exception
//{
//	//List<String> paramValues = getFilterParamValues(commonFilter);
//	
//	List<String> paramValues1 = new ArrayList<String>();
//	String condParms=null;
//	String condParms1=null;
//	String condParms2=null;
//	String condParms3=null;
//	
//	//CommonMessage.debugMsg("commonFilter.getStartDate()"+commonFilter.getStartDate());
//	if(UIUtils.isValidKeyId(commonFilter.getFlid()))
//		condParms =commonFilter.getFlid();
//	if (UIUtils.isValidKeyId(commonFilter.getUniquePos()))
//		condParms1 =commonFilter.getUniquePos();	
//	if (UIUtils.isValidKeyId(commonFilter.getFromDate()))
//		condParms2 =commonFilter.getFromDate();	
//	if (UIUtils.isValidKeyId(commonFilter.getEmpch()))
//		condParms3 =commonFilter.getEmpch();
//	
//	CommonMessage.debugMsg(commonFilter.getFlid());
//	CommonMessage.debugMsg(commonFilter.getUniquePos());
//	CommonMessage.debugMsg(commonFilter.getFromDate());
//	//CommonMessage.debugMsg(commonFilter.getEmpch());
//	
//	String emp = commonFilter.getEmpch();
//    // Clean bad quotes
//    emp = emp.replace("'", "");  
//
//    // Now emp looks like: BCM01029,BCM00036,BCM00707...
//
//    // Wrap each value in quotes
//    emp = Arrays.stream(emp.split(","))
//                 .map(s -> "'" + s.trim() + "'")
//                 .collect(Collectors.joining(","));
//
//    condParms3 = emp;
//	
//	paramValues1.add(condParms); 
//	paramValues1.add(condParms1);
//	paramValues1.add(condParms2);
//	//paramValues1.add(condParms3);
//	CommonMessage.debugMsg( "condParms condParms  "+condParms);
//	CommonMessage.debugMsg( "condParms condParms1 "+condParms1);
//	CommonMessage.debugMsg( "condParms condParms2 "+condParms2);
//	//CommonMessage.debugMsg( "condParms condParms3 "+condParms3);
//	
//	CommonMessage.debugMsg("Inside the rs.........."+paramValues1);
//	
//	CommonMessage.debugMsg("commonFilter.getAbnIsHSE()...."+commonFilter.getAbnIsHSE());
//	
//	return  dbActionTemplate.NewdbFunctionCall2("GEN_FN_SKILLINDEXREVIEWMODIFY", paramValues1);
//	
//}
private ResultSet getAbnormalityAllocationResultSet(CommonFilter commonFilter) throws Exception {
    
    CommonMessage.debugMsg("\n========== START getAbnormalityAllocationResultSet ==========");
    
    List<String> paramValues1 = new ArrayList<String>();
    String condParms = null;
    String condParms1 = null;
    String condParms2 = null;
    String condParms3 = null;
    
    // Debug parameter extraction
    CommonMessage.debugMsg(">>> Extracting parameters from CommonFilter...");
    
    if(UIUtils.isValidKeyId(commonFilter.getFlid())) {
        condParms = commonFilter.getFlid();
        CommonMessage.debugMsg("  condParms (flid): " + condParms);
    } else {
        CommonMessage.debugMsg("  condParms (flid): INVALID/NULL");
    }
    
    if (UIUtils.isValidKeyId(commonFilter.getUniquePos())) {
        condParms1 = commonFilter.getUniquePos();
        CommonMessage.debugMsg("  condParms1 (uniquePos): " + condParms1);
    } else {
        CommonMessage.debugMsg("  condParms1 (uniquePos): INVALID/NULL");
    }
    
    if (UIUtils.isValidKeyId(commonFilter.getFromDate())) {
        condParms2 = commonFilter.getFromDate();
        CommonMessage.debugMsg("  condParms2 (fromDate): " + condParms2);
    } else {
        CommonMessage.debugMsg("  condParms2 (fromDate): INVALID/NULL");
    }
    
    if (UIUtils.isValidKeyId(commonFilter.getEmpch())) {
        condParms3 = commonFilter.getEmpch();
        CommonMessage.debugMsg("  condParms3 (empch) BEFORE processing: " + condParms3);
    } else {
        CommonMessage.debugMsg("  condParms3 (empch): INVALID/NULL");
    }

    

    // Build parameter list
    CommonMessage.debugMsg(">>> Building parameter list...");
    paramValues1.add(condParms); 
    paramValues1.add(condParms1);
    paramValues1.add(condParms2);
    paramValues1.add(condParms3);
    
    
    

    ResultSet result = null;
    try {
        result = dbActionTemplate.NewdbFunctionCall2("GEN_FN_SKILLINDEXREVIEWMODIFY", paramValues1);
        
        CommonMessage.debugMsg(">>> Database function call completed");
        CommonMessage.debugMsg("  Result: " + (result == null ? "NULL!!!" : "Valid ResultSet"));
        
        if (result != null) {
            CommonMessage.debugMsg("  ResultSet type: " + result.getClass().getName());
            try {
                CommonMessage.debugMsg("  ResultSet has Statement: " + (result.getStatement() != null));
            } catch (Exception e) {
                System.err.println("  ERROR: Cannot get Statement from ResultSet: " + e.getMessage());
            }
        }
        
    } catch (Exception e) {
        System.err.println("!!! EXCEPTION during database function call !!!");
        System.err.println("  Exception type: " + e.getClass().getName());
        System.err.println("  Exception message: " + e.getMessage());
        e.printStackTrace();
        throw e;
    }
    
    CommonMessage.debugMsg("========== END getAbnormalityAllocationResultSet ==========\n");
    return result;
}

@Override
public Workbook multipleSkillIndexReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)
		throws Exception {
	 ResultSet rs = null;
	   try{
		
		rs =  getMultipleSkillIndexReportExportExcel(commonFilter);
		CommonMessage.debugMsg(rs);
		ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
		List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
		
		XLConditionalFormats condFormat = new XLConditionalFormats();
		condFormat.setFontColor(new RGB(254,0,0)); //red font
		condFormat.setFontName("Wingdings");
		condFormat.setFontHeightPoint((short)14);
		condFormat.setFontBoldWeight((short)20);
		/*condFormat.setFromCol(13);
		condFormat.setToCol(-1);*/
		/*condFormat.setOperator(ComparisonOperator.EQUAL);
		condFormat.setCondValue( (char)252+""); //Tick
		condFormat.setIdentfier("tick");
		condFormats.add(condFormat);*/
		excelUtils.setCondFormats(condFormats);
		return excelUtils.writeToExcel(rs,format, 3,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
}

private ResultSet getMultipleSkillIndexReportExportExcel(CommonFilter commonFilter) throws Exception {
	//List<String> paramValues = getFilterParamValues(commonFilter);	
	
	List<String> paramValues1 = new ArrayList<String>();
	String condParms=null;
	String condParms1=null;
	String condParms2=null;
	String condParms3=null;
	
	CommonMessage.debugMsg("commonFilter.getStartDate()"+commonFilter.getStartDate());
	if(UIUtils.isValidKeyId(commonFilter.getFlid()))
		condParms =commonFilter.getFlid();
	if (UIUtils.isValidKeyId(commonFilter.getUniquePos()))
		condParms1 =commonFilter.getUniquePos();	
	if (UIUtils.isValidKeyId(commonFilter.getFromDate()))
		condParms2 =commonFilter.getFromDate();	
	if (UIUtils.isValidKeyId(commonFilter.getEmpch()))
		condParms3 =commonFilter.getEmpch();
	
	
	String emp = commonFilter.getEmpch();
    // Clean bad quotes
    emp = emp.replace("'", "");  

    // Now emp looks like: BCM01029,BCM00036,BCM00707...

    // Wrap each value in quotes
    emp = Arrays.stream(emp.split(","))
                 .map(s -> "'" + s.trim() + "'")
                 .collect(Collectors.joining(","));

    condParms3 = emp;
	
	paramValues1.add(condParms); 
	paramValues1.add(condParms1);
	paramValues1.add(condParms2);
	paramValues1.add(condParms3);
	CommonMessage.debugMsg( "condParms condParms  "+condParms);
	CommonMessage.debugMsg( "condParms condParms1 "+condParms1);
	CommonMessage.debugMsg( "condParms condParms2 "+condParms2);
	CommonMessage.debugMsg( "condParms condParms3 "+condParms3);
	
	CommonMessage.debugMsg("Inside the rs.........."+paramValues1);
	ResultSet rs = null;
	CommonMessage.debugMsg("commonFilter.getAbnIsHSE()...."+commonFilter.getAbnIsHSE());

	rs = dbActionTemplate.NewdbFunctionCall2("GEN_FN_SKILLINDEXREVIEWW", paramValues1);
	
	CommonMessage.debugMsg("rs value.....");
	return rs;
}

@Override
public List<String[]> getEmpListFunction(CommonFilter commonfilter, GridParams gridParams) throws Exception {
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	
	
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	String flid = commonfilter.getFlid();
	String uniqPosid = commonfilter.getEmmLinkKeyId();
	String reviewDate = commonfilter.getStartDate();
	String reviewHalf = commonfilter.getYear();
	
	if(UIUtils.isValidKeyId(flid )){
		condParams +="FLID="+flid+";";
	}
	if(UIUtils.isValidKeyId(uniqPosid)){
		condParams +="UNIQPOSID="+uniqPosid+";";
	}
	
	
	if(UIUtils.isValidKeyId(reviewDate)){
		condParams +="REVIEWDATE="+reviewDate+";";
	}
	
	if(UIUtils.isValidKeyId(reviewHalf)){
		condParams +="REVIEWHALF="+reviewHalf+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
	List<String[]> result =  fnCallApi.callFunction("ENT_FN_UNIQUEPOSITIONEMPLOYEES_MULTIPLE_SB", paramValues,2,true);
	
		String totalCnt = paramValues.get(0); 
		CommonMessage.debugMsg("totalCnt..."+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		
		if(  isInteger )
		{
			long counts=Long.parseLong(totalCnt);
			gridParams.setTotalRecordCnt(counts);
		}
	
		
		
		return result;
}
}



