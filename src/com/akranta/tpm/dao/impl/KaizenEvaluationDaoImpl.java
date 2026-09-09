package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.KaizenEvaluationDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMomKpiLinkSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.dao.sql.KznTlEvaluationdtlSql;
import com.akranta.tpm.dao.sql.KznTlEvaluationmstSql;
import com.akranta.tpm.dao.sql.KznTlKaizenbankmstSql;
import com.akranta.tpm.model.AdmTlDashboadUserrights;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.KznTlEvaluationdtl;
import com.akranta.tpm.model.KznTlEvaluationdtl;
import com.akranta.tpm.model.KznTlEvaluationmst;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class KaizenEvaluationDaoImpl implements KaizenEvaluationDao {
	
private DBActionTemplate dbActionTemplate;
	
	public KaizenEvaluationDaoImpl(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<String[]> getKaizenData(CommonFilter commonFilter) throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			if(!UIUtils.isValidKeyId(commonFilter.getFlid()))
				commonFilter.setFlid("");
			if(!UIUtils.isValidKeyId(commonFilter.getRange()))
				commonFilter.setRange("");
			if(!UIUtils.isValidKeyId(commonFilter.getKAIZEN()))
				commonFilter.setKAIZEN("");
			
			CommonMessage.debugMsg("Rank servlet   "+commonFilter.getRange());
			paramValues.add(condParms+"RANK="+commonFilter.getRange()+";KZNID="+commonFilter.getKAIZEN()+";KZNMonth="+commonFilter.getDefaultDate());
			paramValues.add(commonParams);
						
			CommonMessage.debugMsg("ParamValues:"+paramValues);	
			List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZEN.GEN_FN_KAIZENEVALUATION", paramValues);
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
		}

	@Override
	public List<String[]> getKaizenName() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append( "SELECT NINE,SEVEN ,SIX,FRST, SECND, THIRD, FOUR, FIVE,EIGHT FROM (");
	 sql.append(" SELECT 1 AS ORDNO,'Keyid'as NINE,'Kaizen Title'AS SEVEN,'Initiator Name' AS SIX, MAX(CASE RNO WHEN 1 THEN jhcr_criteria ELSE NULL END) AS FRST,");
	 sql.append(" MAX(CASE RNO WHEN 2 THEN jhcr_criteria ELSE NULL END) AS SECND,");
	 sql.append(" MAX(CASE RNO WHEN 3 THEN jhcr_criteria ELSE NULL END) AS THIRD,");
	 sql.append(" MAX(CASE RNO WHEN 4 THEN jhcr_criteria ELSE NULL END) AS FOUR,");
	 sql.append(" MAX(CASE RNO WHEN 5 THEN jhcr_criteria ELSE NULL END) AS FIVE,'Total' AS EIGHT");
	 sql.append(" FROM (");
	 sql.append(" select ROWNUM AS RNO,jhcr_criteria,jhcr_weightage from JHK_TL_KZNCRITERIA");
	 sql.append(" ) GROUP BY 1");
	 sql.append(" UNION ALL" );
	 sql.append(" SELECT 2 AS ORDNO,'Keyid'as NINE,'Kaizen Title' AS SEVEN, 'Weightage' AS SIX, MAX(CASE RNO WHEN 1 THEN jhcr_weightage ELSE NULL END)||'' AS FRST,");
	 sql.append(" MAX(CASE RNO WHEN 2 THEN jhcr_weightage ELSE NULL END)||'' AS SECND,");
	 sql.append(" MAX(CASE RNO WHEN 3 THEN jhcr_weightage ELSE NULL END)||'' AS THIRD,");
	 sql.append(" MAX(CASE RNO WHEN 4 THEN jhcr_weightage ELSE NULL END)||'' AS FOUR,");
	 sql.append(" MAX(CASE RNO WHEN 5 THEN jhcr_weightage ELSE NULL END)||'' AS FIVE,'100%' AS EIGHT");
	 sql.append(" FROM (");
	 sql.append(" select ROWNUM AS RNO,jhcr_criteria,jhcr_weightage from JHK_TL_KZNCRITERIA");
	 sql.append(" ) GROUP BY 2");
	 sql.append(" ORDER BY ORDNO)");
	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
	return gridData;
	}
	@Override
	public List<String[]> getKaizenData() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select kznm_keyid,kznm_theme,empm_name "); 
		sql.append("from kzn_tl_mst,gen_tl_employeemst where KZNM_PREPAREDID = EMPM_KEYID");
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
	}
	
	@Override
	public List<String[]> FillJhLeader(String flid) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT FRT_EMPM_KEYID FROM GEN_TL_FNLNROLETEAM ,ADM_TL_ROLEMST "); 
		sql.append("WHERE FRT_FNLN_KEYID ='"+flid+"'AND ROLE_CODE ='JH LEADER' AND FRT_ROLE_KEYID = ROLE_KEYID");
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
		
	}
	public KznTlEvaluationmst create(KznTlEvaluationmst kznTlEvaluationmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		KznTlEvaluationmstSql kznTlEvaluationmstSql = new KznTlEvaluationmstSql();
		KznTlEvaluationdtlSql kznTlEvaluationdtlSql = new KznTlEvaluationdtlSql();
		
		try{
		
			kznTlEvaluationmst.setKevaKeyid(dbActionTemplate.getSequenceNumber(KznTlEvaluationmstSql.TBL_KZN_TL_EVALUATIONMST ,10, "KEVA", "", "")); 
			sqls.add(KznTlEvaluationmstSql.getInsertSql(kznTlEvaluationmstSql.getKevaDbFields(), kznTlEvaluationmst.getSaveArray())); 
			List<KznTlEvaluationdtl> kznTlEvdtl= kznTlEvaluationmst.getKznTlEvaluationdtl();
			CommonMessage.debugMsg(kznTlEvdtl.get(0).getKedlKzncretriaid()+"SIZE OF DTL "+kznTlEvdtl.size());
			if( kznTlEvdtl != null && kznTlEvdtl.size()> 0 )
			{	
				for( KznTlEvaluationdtl kznTlEvaluationdtl : kznTlEvdtl)
				{	
					kznTlEvaluationdtl.setKedlKeyid(dbActionTemplate.getSequenceNumber(KznTlEvaluationdtlSql.TBL_KZN_TL_EVALUATIONDTL, 10, "KEDL", "", ""));
					CommonMessage.debugMsg(" Key ID  ::::"+kznTlEvaluationdtl.getKedlKeyid());
					kznTlEvaluationdtl.setKedlKevaKeyid(kznTlEvaluationmst.getKevaKeyid());
					sqls.add(KznTlEvaluationdtlSql.getInsertSql(kznTlEvaluationdtlSql.getKedlDbFields(), kznTlEvaluationdtl.getSaveArray()));// add insert sql for detail table
					CommonMessage.debugMsg("sql  " +sqls);	
				}
		    }
			CommonMessage.debugMsg("SQLS   "+sqls.toString());
			//dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return kznTlEvaluationmst;
	}
	
	public KznTlEvaluationmst update(KznTlEvaluationmst kznTlEvaluationmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		KznTlEvaluationmstSql kznTlEvaluationmstSql = new KznTlEvaluationmstSql();
		try {

			sqls.add(KznTlEvaluationmstSql.getUpdateSql(kznTlEvaluationmstSql.getKevaDbFields(), kznTlEvaluationmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return kznTlEvaluationmst;
	}
	
	
	public String getElementID(String originalId) throws Exception {
		String sql = "select fnln_elementid from gen_tl_functionallocn where FNLN_KEYID = '"+originalId+"'";
		CommonMessage.debugMsg(sql);
		String getElementID = dbActionTemplate.getSingleValue(sql);
	
		return getElementID;
	}
	@Override
	public KznTlEvaluationmst selectmst(CommonFilter commonFilter) throws NoDataFoundException, SQLException, Exception {
		KznTlEvaluationmst kznTlEvaluationmst = new KznTlEvaluationmst();
		String sql = KznTlEvaluationmstSql.selectmst(commonFilter.getFlid());
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] { commonFilter.getFlid() };
		kznTlEvaluationmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		CommonMessage.debugMsg(kznTlEvaluationmst.getKevaDate()+" in daoimpl kznTlEvaluationmst   "+kznTlEvaluationmst.getKevaEmployeeid());
		return kznTlEvaluationmst;
	}
	@Override
	public List<KznTlEvaluationmst> create( List<KznTlEvaluationmst> kznTlEvmstList) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		KznTlEvaluationmstSql kznTlEvaluationmstSql = new KznTlEvaluationmstSql();
		KznTlEvaluationdtlSql kznTlEvaluationdtlSql = new KznTlEvaluationdtlSql();
		
		
		try{
		//for(int i=0;i<kznTlEvmstList.size();i++){
				for( KznTlEvaluationmst kznTlEvaluationmst :kznTlEvmstList){
				//KznTlEvaluationmst kznTlEvaluationmst = kznTlEvmstList.get(i);
				if(!UIUtils.isValidKeyId(kznTlEvaluationmst.getKevaKeyid())){
					kznTlEvaluationmst .setKevaKeyid(dbActionTemplate.getSequenceNumber(KznTlEvaluationmstSql.TBL_KZN_TL_EVALUATIONMST ,10, "KEVA", "", "")); 
					sqls.add(KznTlEvaluationmstSql.getInsertSql(kznTlEvaluationmstSql.getKevaDbFields(), kznTlEvaluationmst.getSaveArray())); 
					List<KznTlEvaluationdtl> kznTlEvdtl= kznTlEvaluationmst.getKznTlEvaluationdtl();
					if( kznTlEvdtl != null && kznTlEvdtl.size()> 0 )
					{	
						for( KznTlEvaluationdtl kznTlEvaluationdtl : kznTlEvdtl)
						{	
							kznTlEvaluationdtl.setKedlKeyid(dbActionTemplate.getSequenceNumber(KznTlEvaluationdtlSql.TBL_KZN_TL_EVALUATIONDTL, 10, "KEDL", "", ""));
							CommonMessage.debugMsg(" Key ID  ::::"+kznTlEvaluationdtl.getKedlKeyid());
							kznTlEvaluationdtl.setKedlKevaKeyid(kznTlEvaluationmst.getKevaKeyid());
							sqls.add(KznTlEvaluationdtlSql.getInsertSql(kznTlEvaluationdtlSql.getKedlDbFields(), kznTlEvaluationdtl.getSaveArray()));// add insert sql for detail table
						}
				    }
				}else 
				{ 
					sqls.add(KznTlEvaluationmstSql.getUpdateSql(kznTlEvaluationmstSql.getKevaDbFields(), kznTlEvaluationmst.getSaveArray())); 
					List<KznTlEvaluationdtl> kznTlEvdtl= kznTlEvaluationmst.getKznTlEvaluationdtl();
					CommonMessage.debugMsg(kznTlEvdtl.get(0).getKedlKzncretriaid()+"SIZE OF DTL "+kznTlEvdtl.size());
					if( kznTlEvdtl != null && kznTlEvdtl.size()> 0 )
					{	
						for( KznTlEvaluationdtl kznTlEvaluationdtl : kznTlEvdtl)
						{	
							CommonMessage.debugMsg(kznTlEvaluationmst.getKevaKeyid()+"   master   detail key id  "+kznTlEvaluationdtl.getKedlKeyid());
							if(!UIUtils.isValidKeyId(kznTlEvaluationdtl.getKedlKeyid())){
								kznTlEvaluationdtl.setKedlKeyid(dbActionTemplate.getSequenceNumber(KznTlEvaluationdtlSql.TBL_KZN_TL_EVALUATIONDTL, 10, "KEDL", "", ""));
								kznTlEvaluationdtl.setKedlKevaKeyid(kznTlEvaluationmst.getKevaKeyid());
								sqls.add(KznTlEvaluationdtlSql.getInsertSql(kznTlEvaluationdtlSql.getKedlDbFields(), kznTlEvaluationdtl.getSaveArray()));// add insert sql for detail table
							}else{
								kznTlEvaluationdtl.setKedlKevaKeyid(kznTlEvaluationmst.getKevaKeyid());
								sqls.add(KznTlEvaluationdtlSql.getUpdateSql(kznTlEvaluationdtlSql.getKedlDbFields(), kznTlEvaluationdtl.getSaveArray()));// add insert sql for detail table
							}
						}
				    }
				}
				CommonMessage.debugMsg("SQLS   "+sqls.toString());
			}
			dbActionTemplate.executeStatements(sqls);
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return kznTlEvmstList;
	}
	public  List<KznTlEvaluationmst> delete(List<KznTlEvaluationmst> kznTlEvaluationmstList)
			throws Exception {
		
		List<String> sqls = new ArrayList<String>();
		KznTlEvaluationmstSql kznTlEvaluationmstSql = new KznTlEvaluationmstSql();
		KznTlEvaluationdtlSql kznTlEvaluationdtlSql = new KznTlEvaluationdtlSql();
		try {
			for( KznTlEvaluationmst kznTlEvaluationmst :kznTlEvaluationmstList){
				List<KznTlEvaluationdtl> kznTlEvdtl= kznTlEvaluationmst.getKznTlEvaluationdtl();
				CommonMessage.debugMsg(kznTlEvdtl.get(0).getKedlKzncretriaid()+"SIZE OF DTL "+kznTlEvdtl.size());
				if( kznTlEvdtl != null && kznTlEvdtl.size()> 0 )
				{	
					for( KznTlEvaluationdtl kznTlEvaluationdtl : kznTlEvdtl)
					{	
							sqls.add(kznTlEvaluationdtlSql.getDeleteSql(kznTlEvaluationdtlSql.getKedlDbFields(), kznTlEvaluationdtl.getSaveArray()));	
					}
				}
				sqls.add(kznTlEvaluationmstSql.getDeleteSql(kznTlEvaluationmstSql.getKevaDbFields(), kznTlEvaluationmst.getSaveArray()));
			}
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return kznTlEvaluationmstList;
	}
	
}
