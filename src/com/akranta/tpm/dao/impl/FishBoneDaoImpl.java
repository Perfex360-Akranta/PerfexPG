package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.FishBoneBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.FishBoneDao;
import com.akranta.tpm.dao.sql.BdmTlMstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlFishbonedtlSql;
import com.akranta.tpm.dao.sql.GenTlFishbonemstSql;
import com.akranta.tpm.dao.sql.GenTlMomKpiLinkSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.dao.sql.JhaTlFiveSAuditareamstSql;
import com.akranta.tpm.dao.sql.KpiTlIndicatorDeptLinkSql;
//import com.akranta.tpm.dao.sql.MspTlIndicatorsDtlSql;
//import com.akranta.tpm.dao.sql.MspTlIndicatorsMstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlFishbonedtl;
import com.akranta.tpm.model.GenTlFishbonemst;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.JhaTlFiveSAuditareamst;
import com.akranta.tpm.service.api.FishBoneServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.dao.sql.FishboneSql;
import com.akranta.tpm.model.Fishbone;


public class FishBoneDaoImpl implements FishBoneDao {

	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;
	FishBoneServiceApi fishBoneServiceApi;
	public FishBoneDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void FishBoneDaoImplJwt(String JwtToken) {
	    try {
	    	fishBoneServiceApi = new FishBoneServiceApi(JwtToken);
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;		
	}
	public Fishbone create(Fishbone fishbone) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		FishboneSql fishboneSql = new FishboneSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			fishbone.setFiboNo(dbActionTemplate.getSequenceNumber(FishboneSql.TBL_FISHBONE)); // set the sequnce number 
			sqls.add(FishboneSql.getInsertSql(fishboneSql.getFiboDbFields(), fishbone.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return fishbone;
	}
	
	public Fishbone update(Fishbone fishbone)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		FishboneSql fishboneSql = new FishboneSql();
		try {

			sqls.add(FishboneSql.getUpdateSql(fishboneSql.getFiboDbFields(), fishbone.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return fishbone;
	}
	
	public Fishbone delete(Fishbone fishbone)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		FishboneSql fishboneSql = new FishboneSql();
		try {
			
			sqls.add(fishboneSql.getDeleteSql(fishboneSql.getFiboDbFields(), fishbone.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return fishbone;
	}
	public List<Fishbone> getAllFishBone(Fishbone fishbone) throws Exception
	{		
		StringBuffer sql= new StringBuffer();
		//FIBO_NO,FIBO_NAME,FIBO_PARENTID,FIBO_LEVELNO
		//String sql = "select  fnln_originalid,fnln_elementid,fnln_parentid,fnln_elementtype,displaycode";
		sql.append(" SELECT  FIBO_NO,FIBO_NAME,FB.FIBO_PARENTID,FIBO_LEVELNO");
		sql.append(" ,CHILDPATH " );		
		sql.append(" FROM FISHBONE  FB,vw_fishbone");
		sql.append(" WHERE 1=1 AND FISHBONENO=FIBO_NO");
		sql.append(" AND FIBO_ACTIVE='Y' ");	
		sql.append(" AND FIBO_NO<>FB.FIBO_PARENTID ");
		
		if (CommonFunctions.isValidKeyId(fishbone.getFiboParentid())){
			sql.append(" AND FB.FIBO_PARENTID='" + fishbone.getFiboParentid() + "'");
		}
		sql.append(" ORDER BY FIBO_NO " );
		
		CommonMessage.debugMsg(" sql " + sql.toString());
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return fillFishBone(resultList);	
	}
	
	public List<Fishbone> getFishBoneValues(Fishbone fishbone) throws Exception
	{				
		StringBuffer sql= new StringBuffer();
		sql.append(" SELECT  FIBO_NO,FIBO_NAME,FB.FIBO_PARENTID,FIBO_LEVELNO");
		sql.append(" ,CHILDPATH " );		
		sql.append(" FROM FISHBONE  FB,vw_fishbone");
		sql.append(" WHERE 1=1 AND FISHBONENO=FIBO_NO");
		sql.append(" AND FIBO_ACTIVE='Y' ");	
		sql.append(" AND FIBO_NO=FB.FIBO_PARENTID ");
		
		if (CommonFunctions.isValidKeyId(fishbone.getFiboParentid())){
			sql.append(" AND FB.FIBO_PARENTID='" + fishbone.getFiboParentid() + "'");
		}
		sql.append(" ORDER BY FIBO_NO " );
		
		CommonMessage.debugMsg(" sql " + sql.toString());
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return fillFishBone(resultList);		
	}
	/*@Override
	public List<GenTlFishbonemst> getFishBoneValues(
			GenTlFishbonemst genTlFishbonemst) throws Exception {
		// TODO Auto-generated method stub
		try
		{	String sql = "";
		
		   //CommonMessage.debugMsg("functionalLocn.getElementtype()"+ genTlFishbonemst.getFisdOrderno());
		   //CommonMessage.debugMsg("functionalLocn.getParentNumber()"+ genTlFishbonemst.getFisdLevelno());
		   //CommonMessage.debugMsg("functionalLocn.getParentId()"+ genTlFishbonemst.getFisdParentid());
			
			sql = "SELECT  fisd_cause, fisd_parentid, fisd_orderno, fisd_levelno,FISD_FISM_KEYID,FISD_KEYID ";
		    sql += " FROM gen_tl_fishbonedtl WHERE 1=1 ";//fisd_parentid = '{}' ";
		    
	    	sql += " and FISM_KEYID='"+genTlFishbonemst.getFismKeyid()+"' ";
		    if (UIUtils.isValidKeyId(genTlFishbonemst.getFismKeyid()))
		    	 sql += " and FISM_KEYID='"+genTlFishbonemst.getFismKeyid()+"' ";
		    else
		    	sql += " and fisd_parentid='{}' ";
		    
			CommonMessage.debugMsg(" sql " + sql);
			List resultList = dbActionTemplate.getDataList(sql);
			CommonMessage.debugMsg(" resultList size " + resultList.size());
			return genTlFishbonemst(resultList);			
		}
		catch (Exception e)
		{			
			throw new Exception(e.getMessage()); 			
		}
	}*/
	private List<GenTlFishbonemst> genTlFishbonemst(List<String []> resultList) throws SQLException
	{
	
		   List<GenTlFishbonemst> menus = new ArrayList<GenTlFishbonemst>();
		   for( String [] row : resultList )
		   {
			   GenTlFishbonemst fl = new GenTlFishbonemst();
			   //fl.setFisdOrderno(row[3]);
			   //fl.setFisdLevelno(row[2]);
			   //fl.setFisdParentid(row[1]);
			   fl.setFismKeyid(row[0]);
			   //fl.setFisdFismKeyid(row[4]);
			   //fl.setFisdKeyid(row[5]);
			  /* CommonMessage.debugMsg(" Inside Anthor :: 1 "+fl.getFisdOrderno());
			   CommonMessage.debugMsg(" Inside Anthor :: 2 "+fl.getFisdLevelno());
			   CommonMessage.debugMsg(" Inside Anthor :: 3 "+fl.getFisdParentid());
			   CommonMessage.debugMsg(" Inside Anthor :: 4 "+fl.getFisdCause());
			   CommonMessage.debugMsg(" Inside Anthor :: 5 "+fl.getFisdFismKeyid());
			   CommonMessage.debugMsg(" Inside Anthor :: 6 "+fl.getFisdKeyid());*/
			   //fl.setDisplayCode(row[4]);	
			   //fl.setTempParentId(row[5]);	
			   menus.add(fl);			   
			   
		   }
  		  return menus;
	 }
	
	// -------------Vignesh 15Dec2025 --------------------------------------------------------------------------//
	
	@Override
	public List<GenTlFishbonedtl> getFishBoneValues(GenTlFishbonedtl genTlFishbonedtl, GenTlFishbonemst genTlFishbonemst, String id, String masterId)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{	
			//GenTlFishbonedtlSql
			
			//String sql1 = "";
			
		   CommonMessage.debugMsg(" Inside Dao Impl For Loadval ::");
			
		   StringBuffer sql = new StringBuffer();
		   String sql1 = "";
		   
		   CommonMessage.debugMsg(" ID ::  Dao Impl "+ id+" masterId :: Dao Impl "+masterId);
		   CommonMessage.debugMsg("functionalLocn.getElementtype()"+ genTlFishbonedtl.getFisdOrderno());
		   CommonMessage.debugMsg("functionalLocn.getParentNumber()"+ genTlFishbonedtl.getFisdLevelno());
		   CommonMessage.debugMsg("functionalLocn.getParentId()"+ genTlFishbonedtl.getFisdParentid());
		   CommonMessage.debugMsg(" Master Id In Dao Impl :: "+ genTlFishbonemst.getFismKeyid());
		 
		   
		   
		   /* if(id.equals("1"))
	    	{
			   sql.append("select FISM_PROBLEM from gen_tl_fishbonemst where FISM_KEYID='"+masterId+"' ");
			}  
		   else{*/		   
			   
		   CommonMessage.debugMsg(" Inside 1 ");			   
		   
		   sql.append("SELECT  d.fisd_cause, d.fisd_parentid, d.fisd_orderno, d.fisd_levelno,d.FISD_FISM_KEYID,d.FISD_KEYID ");
		   sql.append(" FROM gen_tl_fishbonemst m ");//fisd_parentid = '{}' ";\
		   sql.append(" JOIN gen_tl_fishbonedtl d");
		   sql.append(" ON m.FISM_KEYID = d.FISD_FISM_KEYID ");
		   
		   CommonMessage.debugMsg(" Inside 2 "+genTlFishbonedtl.getFisdParentid()+" Inside 3 "+genTlFishbonemst.getFismKeyid());
		   
			if (CommonFunctions.isValidKeyId(genTlFishbonemst.getFismKeyid())||!CommonFunctions.isValidKeyId(genTlFishbonemst.getFismKeyid()))
				sql.append(" where m.FISM_KEYID = '"+genTlFishbonemst.getFismKeyid()+"'");
	
			if (CommonFunctions.isValidKeyId(genTlFishbonedtl.getFisdParentid()))
				sql.append(" and d.fisd_parentid = '"+genTlFishbonedtl.getFisdParentid()+"'");
			
			//AND fisd_parentid='FSD0314016';
			
			if (CommonFunctions.isValidKeyId(genTlFishbonedtl.getFisdKeyid())){
							
				sql.append(" and d.FISD_KEYID<>d.FISD_PARENTID ");
				
					/*if(genTlFishbonedtl.getFisdParentid().trim().equals(MspTlIndicatorsDtlSql.MasterPlanId))
					{
						sql.append(" AND FISD_PARENTID=FISD_KEYID");					
					}
					else
					{
						
						sql.append(" AND FISD_PARENTID='" + genTlFishbonedtl.getFisdParentid() + "'");
					//}*/
			}
			
			CommonMessage.debugMsg(" sql " + sql);
		  
           List<String []> resultList = dbActionTemplate.getDataList(sql.toString());
		   CommonMessage.debugMsg("   resultList size " + resultList.size());
		   return genTlFishbonedtl(resultList);	
			
		}
		catch (Exception e)
		{			
			throw new Exception(e.getMessage()); 			
		}
	}
	
	
//	@Override
//	public List<GenTlFishbonedtl> getFishBoneValues(GenTlFishbonedtl genTlFishbonedtl, GenTlFishbonemst genTlFishbonemst, String id, String masterId)
//			throws Exception {
//		// TODO Auto-generated method stub
//		try
//		{	
//			//GenTlFishbonedtlSql
//			
//			//String sql1 = "";
//			
//		   CommonMessage.debugMsg(" Inside Dao Impl For Loadval ::");
//			
//		   StringBuffer sql = new StringBuffer();
//		   String sql1 = "";
//		   
//		   CommonMessage.debugMsg(" ID ::  Dao Impl "+ id+" masterId :: Dao Impl "+masterId);
//		   CommonMessage.debugMsg("functionalLocn.getElementtype()"+ genTlFishbonedtl.getFisdOrderno());
//		   CommonMessage.debugMsg("functionalLocn.getParentNumber()"+ genTlFishbonedtl.getFisdLevelno());
//		   CommonMessage.debugMsg("functionalLocn.getParentId()"+ genTlFishbonedtl.getFisdParentid());
//		   CommonMessage.debugMsg(" Master Id In Dao Impl :: "+ genTlFishbonemst.getFismKeyid());
//		 
//		   
//		   
//		   /* if(id.equals("1"))
//	    	{
//			   sql.append("select FISM_PROBLEM from gen_tl_fishbonemst where FISM_KEYID='"+masterId+"' ");
//			}  
//		   else{*/		   
//			   
//		   CommonMessage.debugMsg(" Inside 1 ");			   
//		   
//		   sql.append("SELECT  fisd_cause, fisd_parentid, fisd_orderno, fisd_levelno,FISD_FISM_KEYID,FISD_KEYID ");
//		   sql.append(" FROM gen_tl_fishbonedtl,gen_tl_fishbonemst ");//fisd_parentid = '{}' ";\
//		   sql.append(" where 1=1 and FISM_KEYID=FISD_FISM_KEYID(+) ");
//		   
//		   CommonMessage.debugMsg(" Inside 2 "+genTlFishbonedtl.getFisdParentid()+" Inside 3 "+genTlFishbonemst.getFismKeyid());
//		   
//			if (CommonFunctions.isValidKeyId(genTlFishbonemst.getFismKeyid())||!CommonFunctions.isValidKeyId(genTlFishbonemst.getFismKeyid()))
//				sql.append(" and FISM_KEYID = '"+genTlFishbonemst.getFismKeyid()+"'");
//	
//			if (CommonFunctions.isValidKeyId(genTlFishbonedtl.getFisdParentid()))
//				sql.append(" and fisd_parentid = '"+genTlFishbonedtl.getFisdParentid()+"'");
//			
//			//AND fisd_parentid='FSD0314016';
//			
//			if (CommonFunctions.isValidKeyId(genTlFishbonedtl.getFisdKeyid())){
//							
//				sql.append(" and FISD_KEYID<>FISD_PARENTID ");
//				
//					/*if(genTlFishbonedtl.getFisdParentid().trim().equals(MspTlIndicatorsDtlSql.MasterPlanId))
//					{
//						sql.append(" AND FISD_PARENTID=FISD_KEYID");					
//					}
//					else
//					{
//						
//						sql.append(" AND FISD_PARENTID='" + genTlFishbonedtl.getFisdParentid() + "'");
//					//}*/
//			}
//			
//			CommonMessage.debugMsg(" sql " + sql);
//		  
//           List<String []> resultList = dbActionTemplate.getDataList(sql.toString());
//		   CommonMessage.debugMsg("   resultList size " + resultList.size());
//		   return genTlFishbonedtl(resultList);	
//			
//		}
//		catch (Exception e)
//		{			
//			throw new Exception(e.getMessage()); 			
//		}
//	}
	
	// -------------Vignesh 15Dec2025 --------------------------------------------------------------------------//
	private List<GenTlFishbonedtl> genTlFishbonedtl(List<String []> resultList) throws SQLException
	{
		   CommonMessage.debugMsg(" Inside Dao Impl For GenTlFishbonedtl :: ");
		   List<GenTlFishbonedtl> menus = new ArrayList<GenTlFishbonedtl>();
		   if(resultList.size()>0){  CommonMessage.debugMsg(" Inside Dao Impl For GenTlFishbonedtl 11 :: ");
			   for( String [] row : resultList )
			   {
				   GenTlFishbonedtl fl = new GenTlFishbonedtl();
				   //fl.setFisdCause(row[0]);
				   fl.setFisdOrderno(row[3]);
				   fl.setFisdLevelno(row[2]);
				   fl.setFisdParentid(row[1]);
				   fl.setFisdCause(row[0]);
				   fl.setFisdFismKeyid(row[4]);
				   fl.setFisdKeyid(row[5]);
				   
				   menus.add(fl);			   
				   
			   }
		   }
  		  return menus;
	 }
	@Override
	public  List<String[]> getSearchNode(String searchNode,String originalId) throws Exception
	{
		try
		{	
			FishboneSql fishboneSql = new FishboneSql();			
			String sql = fishboneSql.getSearchNodeSql(searchNode,originalId);
			CommonMessage.debugMsg("Search SQL : "+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	private List<Fishbone> fillFishBone(List<String []> resultList) throws SQLException
	{
	   List<Fishbone> menus = new ArrayList<Fishbone>();
	   for( String [] row : resultList )
	   {			  
		   Fishbone fishbone = new Fishbone();
		   fishbone.setFiboNo(row[0]);
		   fishbone.setFiboName(row[1]);	
		   fishbone.setFiboParentid(row[2]);
		   fishbone.setFiboLevelno(row[3]);
		   fishbone.setFiboCreatedon(row[4]);
		   menus.add(fishbone);			   
	   }
	   return menus;
	 }

	@Override
	public List<String[]> getAllFishGrid(CommonFilter commonFilter) throws Exception {
       
		/* StringBuffer sql = new StringBuffer();
		
		sql.append("Select 'Title' as TITLE,'Effect'  AS PROBLEM");
		sql.append(" From Dual UNION ");
		sql.append("Select 'Title1' as TITLE,'Problem Statement - 20% scrap on Paper Mill due to poor registration'  AS PROBLEM ");
		sql.append(" From Dual UNION ");
		sql.append("Select 'Title2' as TITLE,'Problem' AS PROBLEM ");
		sql.append(" From Dual ");
		
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;*/
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			CommonMessage.debugMsg("test to............");
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
		//	List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_FISHBONEMAINGRID", paramValues);
			List<String[]> dataList =  fnCallApi.callFunction("GEN_FN_FISHBONEMAINGRID_SB", paramValues,3,true);
			
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
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}
		
	} 
	
	// ----------- Vignesh 15Dec2025 ---------------------------------------------------------------------------//
	
	@Override
	public GenTlFishbonemst create(GenTlFishbonemst newGenTlFishbonemst,
	                              GenTlFishbonemst existGenTlFishbonemst,
	                              FishBoneBean fishBoneBean) throws Exception {

	    List<String> sqls = new ArrayList<String>(); /* sqls for execution */
	    GenTlFishbonemstSql genTlFishbonemstSql = new GenTlFishbonemstSql(); // master fields/sql
	    GenTlFishbonedtlSql genTlFishbonedtlSql = new GenTlFishbonedtlSql(); // detail fields/sql

	    try {
	        // 1) Generate MASTER KeyId and insert master
	        newGenTlFishbonemst.setFismKeyid(
	            dbActionTemplate.getSequenceNumber(
	                GenTlFishbonemstSql.TBL_GEN_TL_FISHBONEMST, 10, "FSH", "MMYY", "Y"
	            )
	        );

	        sqls.add(
	            GenTlFishbonemstSql.getInsertSql(
	                genTlFishbonemstSql.getFismDbFields(),
	                newGenTlFishbonemst.getSaveArray()
	            )
	        );

	        CommonMessage.debugMsg("refdctype fish bone:" + newGenTlFishbonemst.getFismRefdoctype());

	        // 2) Existing WM update logic (unchanged)
	        if ("WM".equals(newGenTlFishbonemst.getFismRefdoctype())) {
	            String womsKey = newGenTlFishbonemst.getFismRefdocid();
	            String FBKeyid = newGenTlFishbonemst.getFismKeyid();
	            CommonMessage.debugMsg("womsKey iside fishbone going to update:" + womsKey);
	            sqls.add(GenTlFishbonemstSql.getUpdateWomstSql(FBKeyid, womsKey));
	        }

	        // ------------------------------------------------------------
	        // 3) NEW: Auto insert 4 default children under FB001
	        // ------------------------------------------------------------
	        String masterKeyId = newGenTlFishbonemst.getFismKeyid();

	        // reuse master audit fields
	        String createdBy = newGenTlFishbonemst.getFismCreatedby();
	        Object createdOn = newGenTlFishbonemst.getFismCreatedon();
	        Object modifiedOn = newGenTlFishbonemst.getFismModifiedon();

	        // safety fallback (optional)
	        if (createdOn == null) createdOn = new java.util.Date();
	        if (modifiedOn == null) modifiedOn = createdOn;

	        String parentId = GenTlFishbonedtlSql.FishBoneId; // "FB001"
	        int orderNo = 1;
	        int levelNo = 1;

	        String[] defaultCauses = { "MAN", "MACHINE", "MATERIAL", "METHOD" };

	        for (String causeName : defaultCauses) {

	            GenTlFishbonedtl autoDtl = new GenTlFishbonedtl();

	            autoDtl.setFisdKeyid(dbActionTemplate.getSequenceNumber(
	                GenTlFishbonedtlSql.TBL_GEN_TL_FISHBONEDTL, 10, "FSD", "MMYY", "Y"
	            ));

	            autoDtl.setFisdFismKeyid(masterKeyId);

	            // ✅ THIS is what actually inserts into FISD_CAUSE
	            autoDtl.setFisdCause(causeName);

	            // optional (won't affect insert, but ok to keep)
	            autoDtl.setFismParent(causeName);

	            autoDtl.setFisdParentid("FB001");
	            autoDtl.setFisdOrderno("1");
	            autoDtl.setFisdLevelno("1");

	            autoDtl.setFisdTempfield1("-");
	            autoDtl.setFisdTempfield2("-");
	            autoDtl.setFisdTempfield3("-");
	            autoDtl.setFisdTempfield4("-");
	            autoDtl.setFisdTempfield5("-");

	            autoDtl.setFisdActive("Y");
	            autoDtl.setFisdCreatedby(createdBy);
	           	            
	        	            String createdOnStr  = newGenTlFishbonemst.getFismCreatedon();   // if this returns String in your model
	        	            String modifiedOnStr = newGenTlFishbonemst.getFismModifiedon();  // if this returns String in your model

	        	            String nowStr = new java.text.SimpleDateFormat("dd-MMM-yyyy HH:mm:ss",Locale.ENGLISH)
	        	                    .format(new java.util.Date());

	        	if (createdOnStr == null || createdOnStr.trim().isEmpty()) createdOnStr = nowStr;
	        	if (modifiedOnStr == null || modifiedOnStr.trim().isEmpty()) modifiedOnStr = nowStr;

	        	autoDtl.setFisdCreatedon(createdOnStr);
	        	autoDtl.setFisdModifiedon(modifiedOnStr);



	            sqls.add(GenTlFishbonedtlSql.getInsertSql(
	                genTlFishbonedtlSql.getFisdDbFields(),
	                autoDtl.getSaveArray()
	            ));
	        }


	        // 4) Execute everything together (master + 4 children + optional WM update)
	        dbActionTemplate.executeStatements(sqls);

	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }

	    return newGenTlFishbonemst;
	}

 
//	@Override
//	public GenTlFishbonemst create(GenTlFishbonemst newGenTlFishbonemst,GenTlFishbonemst existGenTlFishbonemst,
//			FishBoneBean fishBoneBean)throws Exception {
//		// TODO Auto-generated method stub
//		
//		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
//		GenTlFishbonemstSql genTlFishbonemstSql = new GenTlFishbonemstSql();// contains dbtable,field names, Field types and related sqls  of master table
//		GenTlFishbonedtlSql genTlFishbonedtlSql = new GenTlFishbonedtlSql();
//
//		try{
//			
//		newGenTlFishbonemst.setFismKeyid(dbActionTemplate.getSequenceNumber(GenTlFishbonemstSql.TBL_GEN_TL_FISHBONEMST, 10, "FSH", "MMYY", "Y"));  
//		sqls.add(GenTlFishbonemstSql.getInsertSql(genTlFishbonemstSql.getFismDbFields(), newGenTlFishbonemst.getSaveArray())); // add insert sql for master table
//		CommonMessage.debugMsg("refdctype fish bone:"+newGenTlFishbonemst.getFismRefdoctype());
//		if(newGenTlFishbonemst.getFismRefdoctype().equals("WM")){
//			String womsKey=newGenTlFishbonemst.getFismRefdocid();
//			String FBKeyid=newGenTlFishbonemst.getFismKeyid();
//			CommonMessage.debugMsg("womsKey iside fishbone going to update:"+womsKey);
//			sqls.add(GenTlFishbonemstSql.getUpdateWomstSql(FBKeyid, womsKey));	
//		}
//		
//		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
//		
//	}catch(Exception e)
//	{
//		throw new Exception(e.getMessage());
//	}
//	return newGenTlFishbonemst;
//	}

	// ----------- Vignesh 15Dec2025 ---------------------------------------------------------------------------//
	
	@Override
	public GenTlFishbonemst update(GenTlFishbonemst newGenTlFishbonemst,GenTlFishbonemst existGenTlFishbonemst, 
			FishBoneBean fishBoneBean)throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		GenTlFishbonemstSql genTlFishbonemstSql = new GenTlFishbonemstSql();// contains dbtable,field names, Field types and related sqls  of master table
		GenTlFishbonedtlSql genTlFishbonedtlSql = new GenTlFishbonedtlSql();

		try{
			/*
			 * sqls.add(MspTlIndicatorsMstSql.getUpdateSql(mspTlIndicatorsMstSql.getMspiDbFields(), mspTlIndicatorsMst.getSaveArray()));
			mspTlIndicatorsDtl.setMsidMspiKeyid(mspTlIndicatorsMst.getMspiKeyid());
			sqls.add(MspTlIndicatorsDtlSql.getUpdateSql(mspTlIndicatorsDtlSql.getMsidDbFields(), mspTlIndicatorsDtl.getSaveArray()));
			 * 
			 * 
			 */
		//newGenTlFishbonemst.setFismKeyid(dbActionTemplate.getSequenceNumber(GenTlFishbonemstSql.TBL_GEN_TL_FISHBONEMST, 10, "FSH", "MMYY", "Y"));  
		sqls.add(GenTlFishbonemstSql.getUpdateSql(genTlFishbonemstSql.getFismDbFields(), newGenTlFishbonemst.getSaveArray())); // add insert sql for master table
		//mspTlIndicatorsDtl.setMsidMspiKeyid(mspTlIndicatorsMst.getMspiKeyid());
		//sqls.add(MspTlIndicatorsDtlSql.getUpdateSql(mspTlIndicatorsDtlSql.getMsidDbFields(), mspTlIndicatorsDtl.getSaveArray()));
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
	}catch(Exception e)
	{
		throw new Exception(e.getMessage());
	}
	return newGenTlFishbonemst;
	
	}

	@Override
	public GenTlFishbonedtl createChildEntry(GenTlFishbonedtl newGenTlFishbonedtl,GenTlFishbonedtl existGenTlFishbonedtl,
			FishBoneBean fishBoneBean,String editval)throws Exception {

		    // TODO Auto-generated method stub
		
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			GenTlFishbonedtlSql genTlFishbonedtlSql = new GenTlFishbonedtlSql();// contains dbtable,field names, Field types and related sqls  of master table

		try{
			
			CommonMessage.debugMsg(" Inside Dao Impl Before Insert Statement :: "+newGenTlFishbonedtl.getFisdParentid());
			CommonMessage.debugMsg(" Inside Dao Impl Before Insert Statement :: "+newGenTlFishbonedtl.getFismParent());
			CommonMessage.debugMsg(" Inside Dao Impl Before Insert Statement :: "+editval);
			
			if("Editval".equals(editval)){
				CommonMessage.debugMsg(" Editval :: IF ");
				sqls.add("UPDATE gen_tl_fishbonedtl SET FISD_CAUSE='"+newGenTlFishbonedtl.getFismParent()+"' WHERE FISD_KEYID='"+newGenTlFishbonedtl.getFisdParentid()+"'");
			
			}else{
				CommonMessage.debugMsg(" Editval :: ELSE ");
				
				String remarks = newGenTlFishbonedtl.getFisdRemarks();
				if(remarks == null || remarks.trim().length() == 0) {
				    newGenTlFishbonedtl.setFisdRemarks("-");
				}

				newGenTlFishbonedtl.setFisdKeyid(dbActionTemplate.getSequenceNumber(GenTlFishbonedtlSql.TBL_GEN_TL_FISHBONEDTL, 10, "FSD", "MMYY", "Y"));  
				sqls.add(GenTlFishbonedtlSql.getInsertSql(genTlFishbonedtlSql.getFisdDbFields(), newGenTlFishbonedtl.getSaveArray())); // add insert sql for master table
			
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newGenTlFishbonedtl;
			
	}

	@Override
	public GenTlFishbonedtl updateChildEntry(GenTlFishbonedtl newGenTlFishbonedtl,GenTlFishbonedtl existGenTlFishbonedtl,
			FishBoneBean fishBoneBean)throws Exception {

		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		GenTlFishbonedtlSql genTlFishbonedtlSql = new GenTlFishbonedtlSql();// contains dbtable,field names, Field types and related sqls  of master table
		//GenTlFishbonedtlSql
		
		try{
			
			//newGenTlFishbonemst.setFismKeyid(dbActionTemplate.getSequenceNumber(GenTlFishbonemstSql.TBL_GEN_TL_FISHBONEMST, 10, "FSH", "MMYY", "Y"));  
			sqls.add(GenTlFishbonedtlSql.getUpdateSql(genTlFishbonedtlSql.getFisdDbFields(), newGenTlFishbonedtl.getSaveArray())); // add insert sql for master table
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newGenTlFishbonedtl;
		
	}

	@Override
	public GenTlFishbonedtl deleteFishBoneChildEntry(
			GenTlFishbonedtl newGenTlFishbonedtl) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			List<String> sqls = new ArrayList<String>();
			GenTlFishbonemstSql genTlFishbonemstSql = new GenTlFishbonemstSql();// contains dbtable,field names, Field types and related sqls  of master table
			GenTlFishbonedtlSql genTlFishbonedtlSql = new GenTlFishbonedtlSql();
		
			sqls.add("Delete from " +genTlFishbonedtlSql.TBL_GEN_TL_FISHBONEDTL+" where FISD_KEYID='"+newGenTlFishbonedtl.getFisdKeyid()+"'");
			sqls.add("Delete from " +genTlFishbonedtlSql.TBL_GEN_TL_FISHBONEDTL +" where  FISD_PARENTID ='"+ newGenTlFishbonedtl.getFisdKeyid()+"'");
		    
		   
			
		    dbActionTemplate.executeStatements(sqls);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newGenTlFishbonedtl;
	}
	@Override
	public GenTlFishbonemst deleteFishBoneMst(GenTlFishbonemst newGenTlFishbonemst) throws Exception {
		// TODO Auto-generated method stub
		
		try {
		
			List<String> sqls = new ArrayList<String>();
			GenTlFishbonemstSql genTlFishbonemstSql = new GenTlFishbonemstSql();// contains dbtable,field names, Field types and related sqls  of master table
			GenTlFishbonedtlSql genTlFishbonedtlSql = new GenTlFishbonedtlSql();
		
			
			sqls.add("Delete from " +genTlFishbonedtlSql.TBL_GEN_TL_FISHBONEDTL +" where  FISD_FISM_KEYID ='"+ newGenTlFishbonemst.getFismKeyid()+"'");
		    sqls.add("Delete from " +genTlFishbonemstSql.TBL_GEN_TL_FISHBONEMST+" where FISM_KEYID='"+newGenTlFishbonemst.getFismKeyid()+"'");
		   
			
		    dbActionTemplate.executeStatements(sqls);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newGenTlFishbonemst;
		
		
	}
	@Override
	public Workbook getFishBoneExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
	     ResultSet rs = null;
		   try{
			
			rs =   getFishBoneResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format, 2,1,0 );//elumalai
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
		   
	}

	private ResultSet getFishBoneResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		paramValues.add(condParms);
		paramValues.add(commonParams);
        return dbActionTemplate.NewdbFunctionCall2("GEN_FN_FISHBONEMAINGRID", paramValues);
	
	}

	@Override
	public GenTlFishbonemst getFillControl(String fishboneKeyId)
			throws Exception {
		// TODO Auto-generated method stub
		GenTlFishbonemst newGenTlFishbonemst= new GenTlFishbonemst();
		String sql = GenTlFishbonemstSql.getFishdata();
		Object args[] = new Object[] {fishboneKeyId};
		newGenTlFishbonemst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		return newGenTlFishbonemst;
	}

	
	// ---------- VIGNESH 16Dec2025 ---------------------------------------------------------------------------//
	@Override
	public List<String[]> getFBDetail(String keyid) throws Exception {

	    StringBuilder sql = new StringBuilder();

	    // keep your same behavior (apply key filter only if valid)
	    String condKeyD = "";
	    String condKeyC = "";
	    if (CommonFunctions.isValidKeyId(keyid)) {
	        condKeyD = " AND d.fisd_fism_keyid = '" + keyid + "' ";
	        condKeyC = " AND c.fisd_fism_keyid = '" + keyid + "' ";
	    }

	    sql.append(" WITH RECURSIVE tree (fisd_keyid, fisd_parentid, fisd_cause, lvl, path_ids) AS ( ");
	    sql.append("   SELECT ");
	    sql.append("     d.fisd_keyid, ");
	    sql.append("     d.fisd_parentid, ");
	    sql.append("     d.fisd_cause, ");
	    sql.append("     1 AS lvl, ");
	    sql.append("     ARRAY[d.fisd_keyid::text]::text[] AS path_ids ");
	    sql.append("   FROM gen_tl_fishbonedtl d ");
	    sql.append("   WHERE d.fisd_parentid = 'FB001' ");
	    sql.append(condKeyD);
	    sql.append("   UNION ALL ");
	    sql.append("   SELECT ");
	    sql.append("     c.fisd_keyid, ");
	    sql.append("     c.fisd_parentid, ");
	    sql.append("     c.fisd_cause, ");
	    sql.append("     t.lvl + 1 AS lvl, ");
	    sql.append("     (t.path_ids || c.fisd_keyid::text)::text[] AS path_ids ");
	    sql.append("   FROM gen_tl_fishbonedtl c ");
	    sql.append("   JOIN tree t ON c.fisd_parentid = t.fisd_keyid ");
	    sql.append("   WHERE t.lvl < 30 ");
	    sql.append(condKeyC);
	    sql.append("     AND NOT (c.fisd_keyid::text = ANY(t.path_ids::text[])) ");
	    sql.append(" ) ");
	    sql.append(" SELECT ");
	    sql.append("   CASE WHEN lvl =  1 THEN fisd_cause END AS level_1, ");
	    sql.append("   CASE WHEN lvl =  2 THEN fisd_cause END AS level_2, ");
	    sql.append("   CASE WHEN lvl =  3 THEN fisd_cause END AS level_3, ");
	    sql.append("   CASE WHEN lvl =  4 THEN fisd_cause END AS level_4, ");
	    sql.append("   CASE WHEN lvl =  5 THEN fisd_cause END AS level_5, ");
	    sql.append("   CASE WHEN lvl =  6 THEN fisd_cause END AS level_6, ");
	    sql.append("   CASE WHEN lvl =  7 THEN fisd_cause END AS level_7, ");
	    sql.append("   CASE WHEN lvl =  8 THEN fisd_cause END AS level_8, ");
	    sql.append("   CASE WHEN lvl =  9 THEN fisd_cause END AS level_9, ");
	    sql.append("   CASE WHEN lvl = 10 THEN fisd_cause END AS level_10, ");
	    sql.append("   CASE WHEN lvl = 11 THEN fisd_cause END AS level_11, ");
	    sql.append("   CASE WHEN lvl = 12 THEN fisd_cause END AS level_12, ");
	    sql.append("   CASE WHEN lvl = 13 THEN fisd_cause END AS level_13, ");
	    sql.append("   CASE WHEN lvl = 14 THEN fisd_cause END AS level_14, ");
	    sql.append("   CASE WHEN lvl = 15 THEN fisd_cause END AS level_15, ");
	    sql.append("   CASE WHEN lvl = 16 THEN fisd_cause END AS level_16, ");
	    sql.append("   CASE WHEN lvl = 17 THEN fisd_cause END AS level_17, ");
	    sql.append("   CASE WHEN lvl = 18 THEN fisd_cause END AS level_18, ");
	    sql.append("   CASE WHEN lvl = 19 THEN fisd_cause END AS level_19, ");
	    sql.append("   CASE WHEN lvl = 20 THEN fisd_cause END AS level_20, ");
	    sql.append("   CASE WHEN lvl = 21 THEN fisd_cause END AS level_21, ");
	    sql.append("   CASE WHEN lvl = 22 THEN fisd_cause END AS level_22, ");
	    sql.append("   CASE WHEN lvl = 23 THEN fisd_cause END AS level_23, ");
	    sql.append("   CASE WHEN lvl = 24 THEN fisd_cause END AS level_24, ");
	    sql.append("   CASE WHEN lvl = 25 THEN fisd_cause END AS level_25, ");
	    sql.append("   CASE WHEN lvl = 26 THEN fisd_cause END AS level_26, ");
	    sql.append("   CASE WHEN lvl = 27 THEN fisd_cause END AS level_27, ");
	    sql.append("   CASE WHEN lvl = 28 THEN fisd_cause END AS level_28, ");
	    sql.append("   CASE WHEN lvl = 29 THEN fisd_cause END AS level_29, ");
	    sql.append("   CASE WHEN lvl = 30 THEN fisd_cause END AS level_30 ");
	    sql.append(" FROM tree ");
	    sql.append(" ORDER BY path_ids ");

	    List<String[]> resultList = dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
	    CommonMessage.debugMsg(" resultList size " + resultList.size());
	    return resultList;
	}

//	@Override
//	public List<String[]> getFBDetail(String keyid) throws Exception {
//		
//		
//		
//
//		StringBuilder sql = new StringBuilder();
//		
//		sql.append(" SELECT ");
//		sql.append("  DECODE(level, 1, FISD_CAUSE) level_1 , ");
//		sql.append( " DECODE(level, 2, FISD_CAUSE) level_2 , ");
//		sql.append( " DECODE(level, 3, FISD_CAUSE) level_3 , ");
//		sql.append( " DECODE(level, 4, FISD_CAUSE) level_4 , ");
//		sql.append( " DECODE(level, 5, FISD_CAUSE) level_5 , ");
//		sql.append( " DECODE(level, 6, FISD_CAUSE) level_6 , ");
//		sql.append( " DECODE(level, 7, FISD_CAUSE) level_7 , ");
//		sql.append( " DECODE(level, 8, FISD_CAUSE) level_8 , ");
//		sql.append("  DECODE(level, 9, FISD_CAUSE) level_9 , ");
//		sql.append( " DECODE(level, 10, FISD_CAUSE) level_10, ");
//		
//		sql.append("  DECODE(level, 11, FISD_CAUSE) level_11 , ");
//		sql.append( " DECODE(level, 12, FISD_CAUSE) level_12 , ");
//		sql.append( " DECODE(level, 13, FISD_CAUSE) level_13 , ");
//		sql.append( " DECODE(level, 14, FISD_CAUSE) level_14 , ");
//		sql.append( " DECODE(level, 15, FISD_CAUSE) level_15 , ");
//		sql.append( " DECODE(level, 16, FISD_CAUSE) level_16 , ");
//		sql.append( " DECODE(level, 17, FISD_CAUSE) level_17 , ");
//		sql.append( " DECODE(level, 18, FISD_CAUSE) level_18 , ");
//		sql.append("  DECODE(level, 19, FISD_CAUSE) level_19 , ");
//		sql.append( " DECODE(level, 20, FISD_CAUSE) level_20, ");
//		
//		sql.append("  DECODE(level, 21, FISD_CAUSE) level_21 , ");
//		sql.append( " DECODE(level, 22, FISD_CAUSE) level_22 , ");
//		sql.append( " DECODE(level, 23, FISD_CAUSE) level_23 , ");
//		sql.append( " DECODE(level, 24, FISD_CAUSE) level_24 , ");
//		sql.append( " DECODE(level, 25, FISD_CAUSE) level_25 , ");
//		sql.append( " DECODE(level, 26, FISD_CAUSE) level_26 , ");
//		sql.append( " DECODE(level, 27, FISD_CAUSE) level_27 , ");
//		sql.append( " DECODE(level, 28, FISD_CAUSE) level_28 , ");
//		sql.append("  DECODE(level, 29, FISD_CAUSE) level_29 , ");
//		sql.append( " DECODE(level, 30, FISD_CAUSE) level_30  ");
//		
//		sql.append( " FROM gen_tl_fishbonedtl " );
//		
//		if (CommonFunctions.isValidKeyId(keyid))
//		{
//			sql.append( " where FISD_FISM_KEYID ='" + keyid +"'" );
//		}
//		sql.append( " START  WITH FISD_PARENTID='FB001'	" ) ;
//		sql.append( " CONNECT  BY prior FISD_KEYID = FISD_PARENTID " ) ;
//		
//		
//		
//		//CommonMessage.debugMsg(" sql " + sql.toString());
//		List<String []> resultList = dbActionTemplate.getDataListWithColHeader(sql.toString(),null);	
//		CommonMessage.debugMsg(" resultList size " + resultList.size());
//		return resultList;	
//	}
	
	
	// ---------- VIGNESH 16Dec2025 ---------------------------------------------------------------------------//
	
	private ResultSet getFBValueResultSet(String keyid) throws Exception {

	    StringBuilder sql = new StringBuilder();

	    String condKeyD = "";
	    String condKeyC = "";
	    if (CommonFunctions.isValidKeyId(keyid)) {
	        condKeyD = " AND d.fisd_fism_keyid = '" + keyid + "' ";
	        condKeyC = " AND c.fisd_fism_keyid = '" + keyid + "' ";
	    }

	    sql.append(" WITH RECURSIVE tree (fisd_keyid, fisd_parentid, fisd_cause, lvl, path_ids) AS ( ");
	    sql.append("   SELECT ");
	    sql.append("     d.fisd_keyid, ");
	    sql.append("     d.fisd_parentid, ");
	    sql.append("     d.fisd_cause, ");
	    sql.append("     1 AS lvl, ");
	    sql.append("     ARRAY[d.fisd_keyid::text]::text[] AS path_ids ");
	    sql.append("   FROM gen_tl_fishbonedtl d ");
	    sql.append("   WHERE d.fisd_parentid = 'FB001' ");
	    sql.append(condKeyD);
	    sql.append("   UNION ALL ");
	    sql.append("   SELECT ");
	    sql.append("     c.fisd_keyid, ");
	    sql.append("     c.fisd_parentid, ");
	    sql.append("     c.fisd_cause, ");
	    sql.append("     t.lvl + 1 AS lvl, ");
	    sql.append("     (t.path_ids || c.fisd_keyid::text)::text[] AS path_ids ");
	    sql.append("   FROM gen_tl_fishbonedtl c ");
	    sql.append("   JOIN tree t ON c.fisd_parentid = t.fisd_keyid ");
	    sql.append("   WHERE t.lvl < 30 ");
	    sql.append(condKeyC);
	    sql.append("     AND NOT (c.fisd_keyid::text = ANY(t.path_ids::text[])) ");
	    sql.append(" ) ");
	    sql.append(" SELECT ");
	    sql.append("   CASE WHEN lvl =  1 THEN fisd_cause END AS level_1, ");
	    sql.append("   CASE WHEN lvl =  2 THEN fisd_cause END AS level_2, ");
	    sql.append("   CASE WHEN lvl =  3 THEN fisd_cause END AS level_3, ");
	    sql.append("   CASE WHEN lvl =  4 THEN fisd_cause END AS level_4, ");
	    sql.append("   CASE WHEN lvl =  5 THEN fisd_cause END AS level_5, ");
	    sql.append("   CASE WHEN lvl =  6 THEN fisd_cause END AS level_6, ");
	    sql.append("   CASE WHEN lvl =  7 THEN fisd_cause END AS level_7, ");
	    sql.append("   CASE WHEN lvl =  8 THEN fisd_cause END AS level_8, ");
	    sql.append("   CASE WHEN lvl =  9 THEN fisd_cause END AS level_9, ");
	    sql.append("   CASE WHEN lvl = 10 THEN fisd_cause END AS level_10, ");
	    sql.append("   CASE WHEN lvl = 11 THEN fisd_cause END AS level_11, ");
	    sql.append("   CASE WHEN lvl = 12 THEN fisd_cause END AS level_12, ");
	    sql.append("   CASE WHEN lvl = 13 THEN fisd_cause END AS level_13, ");
	    sql.append("   CASE WHEN lvl = 14 THEN fisd_cause END AS level_14, ");
	    sql.append("   CASE WHEN lvl = 15 THEN fisd_cause END AS level_15, ");
	    sql.append("   CASE WHEN lvl = 16 THEN fisd_cause END AS level_16, ");
	    sql.append("   CASE WHEN lvl = 17 THEN fisd_cause END AS level_17, ");
	    sql.append("   CASE WHEN lvl = 18 THEN fisd_cause END AS level_18, ");
	    sql.append("   CASE WHEN lvl = 19 THEN fisd_cause END AS level_19, ");
	    sql.append("   CASE WHEN lvl = 20 THEN fisd_cause END AS level_20, ");
	    sql.append("   CASE WHEN lvl = 21 THEN fisd_cause END AS level_21, ");
	    sql.append("   CASE WHEN lvl = 22 THEN fisd_cause END AS level_22, ");
	    sql.append("   CASE WHEN lvl = 23 THEN fisd_cause END AS level_23, ");
	    sql.append("   CASE WHEN lvl = 24 THEN fisd_cause END AS level_24, ");
	    sql.append("   CASE WHEN lvl = 25 THEN fisd_cause END AS level_25, ");
	    sql.append("   CASE WHEN lvl = 26 THEN fisd_cause END AS level_26, ");
	    sql.append("   CASE WHEN lvl = 27 THEN fisd_cause END AS level_27, ");
	    sql.append("   CASE WHEN lvl = 28 THEN fisd_cause END AS level_28, ");
	    sql.append("   CASE WHEN lvl = 29 THEN fisd_cause END AS level_29, ");
	    sql.append("   CASE WHEN lvl = 30 THEN fisd_cause END AS level_30 ");
	    sql.append(" FROM tree ");
	    sql.append(" ORDER BY path_ids ");

	    return dbActionTemplate.getData(sql.toString());
	}

//	private ResultSet getFBValueResultSet(String keyid) throws Exception {
//		// TODO Auto-generated method stub
//StringBuilder sql = new StringBuilder();
//		
//		sql.append(" SELECT ");
//		sql.append("  DECODE(level, 1, FISD_CAUSE) level_1 , ");
//		sql.append( " DECODE(level, 2, FISD_CAUSE) level_2 , ");
//		sql.append( " DECODE(level, 3, FISD_CAUSE) level_3 , ");
//		sql.append( " DECODE(level, 4, FISD_CAUSE) level_4 , ");
//		sql.append( " DECODE(level, 5, FISD_CAUSE) level_5 , ");
//		sql.append( " DECODE(level, 6, FISD_CAUSE) level_6 , ");
//		sql.append( " DECODE(level, 7, FISD_CAUSE) level_7 , ");
//		sql.append( " DECODE(level, 8, FISD_CAUSE) level_8 , ");
//		sql.append("  DECODE(level, 9, FISD_CAUSE) level_9 , ");
//		sql.append( " DECODE(level, 10, FISD_CAUSE) level_10, ");
//		
//		sql.append("  DECODE(level, 11, FISD_CAUSE) level_11 , ");
//		sql.append( " DECODE(level, 12, FISD_CAUSE) level_12 , ");
//		sql.append( " DECODE(level, 13, FISD_CAUSE) level_13 , ");
//		sql.append( " DECODE(level, 14, FISD_CAUSE) level_14 , ");
//		sql.append( " DECODE(level, 15, FISD_CAUSE) level_15 , ");
//		sql.append( " DECODE(level, 16, FISD_CAUSE) level_16 , ");
//		sql.append( " DECODE(level, 17, FISD_CAUSE) level_17 , ");
//		sql.append( " DECODE(level, 18, FISD_CAUSE) level_18 , ");
//		sql.append("  DECODE(level, 19, FISD_CAUSE) level_19 , ");
//		sql.append( " DECODE(level, 20, FISD_CAUSE) level_20, ");
//		
//		sql.append("  DECODE(level, 21, FISD_CAUSE) level_21 , ");
//		sql.append( " DECODE(level, 22, FISD_CAUSE) level_22 , ");
//		sql.append( " DECODE(level, 23, FISD_CAUSE) level_23 , ");
//		sql.append( " DECODE(level, 24, FISD_CAUSE) level_24 , ");
//		sql.append( " DECODE(level, 25, FISD_CAUSE) level_25 , ");
//		sql.append( " DECODE(level, 26, FISD_CAUSE) level_26 , ");
//		sql.append( " DECODE(level, 27, FISD_CAUSE) level_27 , ");
//		sql.append( " DECODE(level, 28, FISD_CAUSE) level_28 , ");
//		sql.append("  DECODE(level, 29, FISD_CAUSE) level_29 , ");
//		sql.append( " DECODE(level, 30, FISD_CAUSE) level_30  ");
//		
//		sql.append( " FROM gen_tl_fishbonedtl " );
//		
//		if (CommonFunctions.isValidKeyId(keyid))
//		{
//			sql.append( "  where FISD_FISM_KEYID ='" + keyid +"'");
//		}
//		sql.append( " START  WITH FISD_PARENTID='FB001'	");
//		sql.append( "  CONNECT  BY prior FISD_KEYID = FISD_PARENTID ") ;
//		
//        return dbActionTemplate.getData(sql.toString());
//	}
	
	// ---------- VIGNESH 16Dec2025 ---------------------------------------------------------------------------//
	
	@Override
	public Workbook getFBDetailForExcel(String keyid,JSONObject tblJSONObj,String format) throws Exception {
		ResultSet rs = null;		
		rs =   getFBValueResultSet(keyid);
		
		ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
		CommonMessage.debugMsg(rs);
		return excelUtils.writeToExcel(rs,format,0 ,0,0 );
	}
	
	public List<String[]> getKznSgnCount(CommonFilter commonFilter)
			throws Exception {
		try
		{
			List<String > paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
		//	List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_FISHBONECOUNT", paramValues);

			List<String[]> impVsCompList = fnCallApi.callFunction("GEN_FN_FISHBONECOUNT_SB", paramValues,2,true);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
			return impVsCompList;			
		}
			
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
		}	
	
	//------------------------------------ 31Oct2025 ---- Vignesh ------------------------------------------------------//
	
		@Override
		public Workbook getExcelreport(CommonFilter commonFilter, JSONObject colModel, String format) throws Exception {
			// TODO Auto-generated method stub
			ResultSet rs = null;
			   try{
				   
				//  CommonMessage.debugMsg("formats=="+formats);
				rs =getExcelResultSet(commonFilter);
				//CommonMessage.debugMsg("rs.........");
				ExcelUtils excelUtils = new ExcelUtils(colModel);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				
				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(254,0,0)); //red font
				condFormat.setFontName("Wingdings");
				condFormat.setFontHeightPoint((short)14);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setFromCol(13);
				condFormat.setToCol(-1);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue( (char)252+""); //Tick
				condFormat.setIdentfier("tick");
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
				return excelUtils.writeToExcel(rs,format,1,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
			
		}
		
		
		public ResultSet getExcelResultSet(CommonFilter commonFilter) throws Exception
		{
			ResultSet rs = null;
			try
			{
				List<String > paramValues = new ArrayList<String>();
				String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter);			
				paramValues.add(condParms);
				paramValues.add(commonParams);			
				rs = dbActionTemplate.NewdbFunctionCall2("GEN_FN_FISHBONECOUNT", paramValues);
				
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
				return rs;
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}

		}
		
//		@Override
//		public Workbook getFishBoneExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
//			// TODO Auto-generated method stub
//		     ResultSet rs = null;
//			   try{
//				
//				rs =   getFishBoneResultSet(commonFilter);
//				ExcelUtils excelUtils = new ExcelUtils(colmodel);
//				
//				return excelUtils.writeToExcel(rs,format, 2,0,0 );
//				
//			   }finally{
//				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
//			   }
//			   
//		}
//
//		private ResultSet getFishBoneResultSet(CommonFilter commonFilter) throws Exception {
//			// TODO Auto-generated method stub
//			
//			List<String> paramValues = new ArrayList<String>();
//			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
//			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
//			paramValues.add(condParms);
//			paramValues.add(commonParams);
//	        return dbActionTemplate.NewdbFunctionCall2("GEN_FN_FISHBONEMAINGRID", paramValues);
//		
//		}
	
	
}




