package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlSkillindexassessmstDao;
import com.akranta.tpm.dao.sql.EntTlSkillindexassessdtlSql;
import com.akranta.tpm.dao.sql.EntTlSkillindexassessmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillindexassessdtl;
import com.akranta.tpm.model.EntTlSkillindexassessmst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
//import com.sun.org.apache.xpath.internal.operations.Mod;

/* dao implementation */
public class EntTlSkillindexassessmstDaoImpl implements EntTlSkillindexassessmstDao {


	private DBActionTemplate dbActionTemplate; 
	private FunctionCallApi fnCallApi;

	public EntTlSkillindexassessmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void entTlSkillindexassessmstDaoImplJwt(String JwtToken) 
	{
		try{
			
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public EntTlSkillindexassessmst create(EntTlSkillindexassessmst entTlSkillindexassessmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlSkillindexassessmstSql entTlSkillindexassessmstSql = new EntTlSkillindexassessmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlSkillindexassessmst.setSiamKeyid(dbActionTemplate.getSequenceNumber(EntTlSkillindexassessmstSql.TBL_ENT_TL_SKILLINDEXASSESSMST, 15, "SIAM", "MMYY", "Y")); // set the sequnce number 
			sqls.add(EntTlSkillindexassessmstSql.getInsertSql(entTlSkillindexassessmstSql.getSiamDbFields(), entTlSkillindexassessmst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlSkillindexassessmst;
	}
	
	public EntTlSkillindexassessmst update(EntTlSkillindexassessmst entTlSkillindexassessmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlSkillindexassessmstSql entTlSkillindexassessmstSql = new EntTlSkillindexassessmstSql();
		try {

			sqls.add(EntTlSkillindexassessmstSql.getUpdateSql(entTlSkillindexassessmstSql.getSiamDbFields(), entTlSkillindexassessmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlSkillindexassessmst;
	}
	
	public EntTlSkillindexassessmst delete(EntTlSkillindexassessmst entTlSkillindexassessmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlSkillindexassessmstSql entTlSkillindexassessmstSql = new EntTlSkillindexassessmstSql();
		try {
			
			sqls.add(entTlSkillindexassessmstSql.getDeleteSql(entTlSkillindexassessmstSql.getSiamDbFields(), entTlSkillindexassessmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlSkillindexassessmst;
	}

	
	public EntTlSkillindexassessmst createAssement( List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmst, 
			List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl) throws Exception {
	
		List<String> sqls = new ArrayList<String>();
		EntTlSkillindexassessmstSql entTlSkillindexassessmstSql = new EntTlSkillindexassessmstSql();
		EntTlSkillindexassessdtlSql entTlSkillindexassessdtlSql = new EntTlSkillindexassessdtlSql();
		
		try {
			
			//MASTER
			for( EntTlSkillindexassessmst entTlSklassetMstData : lstEntTlSkillindexassessmst)
			{	
				CommonMessage.debugMsg(entTlSklassetMstData.getSiamKeyid() +" entTlSklassetMstData.getSiamKeyid() ");
				
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
					
					CommonMessage.debugMsg(sql +" sqlsqlsqlsqlsqlsqlsql");
					
					//CommonMessage.debugMsg("sql==="+sql);
					String dtlKeyid = dbActionTemplate.getSingleValue( sql);
					
					//CommonMessage.debugMsg("dtlKeyid="+dtlKeyid);
					
					//if ( UIUtils.isValidKeyId( entTlSklassetDtlData.getSiadScore())) {
					
					if(UIUtils.isValidKeyId( dtlKeyid )){
						
						String sqlDel ="";
						sqlDel = " DELETE FROM ENT_TL_SKILLINDEXASSESSDTL WHERE SIAD_SIAM_KEYID = '" + entTlSklassetDtlData.getSiadSiamKeyid() + "' "; 
						sqlDel = sqlDel + " AND SIAD_EMPM_KEYID = '" + entTlSklassetDtlData.getSiadEmpmKeyid() + "'  ";
			     		sqlDel = sqlDel + " AND SIAD_REVIEWID = '" + entTlSklassetDtlData.getSiadReviewid() + "' ";
						
						CommonMessage.debugMsg(sqlDel +" sqlsqlsqlsqlsqlsqlsql");
						
						//CommonMessage.debugMsg("sql==="+sql);
						 dbActionTemplate.executeStatement(sqlDel);
					}
					
					String sqlDet ="";
					sqlDet = " SELECT SIAD_KEYID FROM ENT_TL_SKILLINDEXASSESSDTL WHERE SIAD_SIAM_KEYID = '" + entTlSklassetDtlData.getSiadSiamKeyid() + "' "; 
					sqlDet = sqlDet + " AND SIAD_EMPM_KEYID = '" + entTlSklassetDtlData.getSiadEmpmKeyid() + "'  ";
					sqlDet = sqlDet + " AND SIAD_REVIEWID = '" + entTlSklassetDtlData.getSiadReviewid() + "' ";
					
					CommonMessage.debugMsg(sqlDet +" sqlsqlsqlsqlsqlsqlsql");
					
					//CommonMessage.debugMsg("sql==="+sql);
					String dtlKeyId = dbActionTemplate.getSingleValue( sql);
							
						if (!UIUtils.isValidKeyId( dtlKeyId )) {
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
			
			sqls.add(sql.toString());
			
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return lstEntTlSkillindexassessmst.get(0);
	}
	//Query change By Swetha - SKill assessment October 30
	public List<String[]> getEmpList(CommonFilter commonfilter) throws Exception { 
		StringBuffer sql = new StringBuffer();
		
		String flid = commonfilter.getFlid();
		String uniqPosid = commonfilter.getEmmLinkKeyId();
		String reviewDate = commonfilter.getStartDate();
		//Strin empId = commonfilter.setEmpch(empId);
		
		//sql.append("  select Empm_keyid as keyid,''as checks,empm_code as code,empm_name as name,TOT_SCORE, EMPM_ROLEID as roleid "); 
		sql.append("  SELECT DISTINCT Empm_keyid as keyid, '' as checks, empm_code as code, empm_name as name, TOT_SCORE ");
		sql.append("  FROM  ( ");
		
		/*sql.append("  select Empm_keyid ,empm_code ,empm_name ,EMPM_ROLEID ");
		sql.append("  from GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM where 1=1 ");
		sql.append("  AND EMPM_KEYID = FRT_EMPM_KEYID ");
		sql.append("  AND FRT_FNLN_KEYID = '"+commonfilter.getFlid()+"' ");
		*/
		
		CommonMessage.debugMsg("reviewDate=== "+reviewDate);
		
		StringBuffer lstSql = new StringBuffer();
		lstSql.append("  SELECT TO_CHAR(MAX(SIAM_REVIEWDATE), 'YYYY-MM-DD')  ");
		lstSql.append("  FROM ENT_TL_SKILLINDEXASSESSMST, ENT_TL_SKILLINDEXASSESSDTL ");
		lstSql.append("  WHERE SIAD_SIAM_KEYID = SIAM_KEYID ");
		lstSql.append(" AND SIAM_FLID='" + flid + "' AND SIAM_UNIQUEPOSID='" + uniqPosid + "' ");
	    
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
	    	sql.append(" AND EMPM_ACTIVE='Y' AND EMPM_EMPLOYEETYPE NOT IN ('M') "); 
		
	    	sql.append(FilterCondSql.makeGridFilterCond(commonfilter.getGridFilter()));
	    	sql.append(" ) emp ");
	    	sql.append(" LEFT JOIN ( ");
	    	sql.append(" SELECT SIAD_EMPM_KEYID, ROUND(SUM(SIAD_SCORE)::numeric/COUNT(DISTINCT SIAD_CRITERIAID)::numeric, 2) AS TOT_SCORE ");
	    	sql.append("  FROM ENT_TL_SKILLINDEXASSESSMST ");
	    	sql.append(" INNER JOIN ENT_TL_SKILLINDEXASSESSDTL ON SIAD_SIAM_KEYID = SIAM_KEYID ");
	    	sql.append(" WHERE SIAM_FLID='" + flid + "' AND SIAM_UNIQUEPOSID='" + uniqPosid + "' ");
		
		
	    	sql.append(" AND DATE_TRUNC('day', SIAM_REVIEWDATE) = '" + reviewDate + "'::date ");
		
	    	sql.append(" GROUP BY SIAD_EMPM_KEYID ");
	    	sql.append(" ) scores ON scores.SIAD_EMPM_KEYID = emp.Empm_keyid ");
	    	sql.append(" ORDER BY TOT_SCORE DESC NULLS LAST ");
		
		CommonMessage.debugMsg("sql ........."+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql.toString());
		return dataList;
	}
	//Query change By Swetha - SKill assessment October 30
	
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
		List<String[]> result =  fnCallApi.callFunction("ENT_FN_UNIQUEPOSITIONEMPLOYEES_SB", paramValues,2,true);
		
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

