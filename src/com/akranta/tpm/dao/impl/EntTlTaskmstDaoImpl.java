package com.akranta.tpm.dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlTaskmstDao;
import com.akranta.tpm.dao.sql.EntTlUniqpostopicLinkdtlSql;
import com.akranta.tpm.dao.sql.EntTlTaskMappingksadtlSql;
import com.akranta.tpm.dao.sql.EntTlTaskMappingksamstSql;
import com.akranta.tpm.dao.sql.EntTlTaskdtlSql;
import com.akranta.tpm.dao.sql.EntTlTaskmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlSparesmstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTaskMappingksamst;
import com.akranta.tpm.model.EntTlTaskdtl;
import com.akranta.tpm.model.EntTlTaskmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class EntTlTaskmstDaoImpl implements EntTlTaskmstDao {


	private DBActionTemplate dbActionTemplate; 
	EntTlTaskmstSql entTlTaskMappingksamstSql =new EntTlTaskmstSql();
	EntTlTaskdtlSql entTlTaskMappingksadtlSql = new EntTlTaskdtlSql();
	public EntTlTaskmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlTaskmst create(EntTlTaskmst newentTlTaskmst, EntTlTaskdtl newEntTlTaskdtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTaskmstSql entTlTaskMappingksamstSql = new EntTlTaskmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlTaskdtlSql entTlTaskMappingksadtlSql = new EntTlTaskdtlSql();
		try{
		
			newentTlTaskmst.setTmkmKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskmstSql.TBL_ENT_TL_TASKMST,10, "TMKM", "MMYY", "Y")); // set the sequnce number 
			sqls.add(EntTlTaskmstSql.getInsertSql(entTlTaskMappingksamstSql.getTmkmDbFields(), newentTlTaskmst.getSaveArray())); // add insert sql for master table
			
			
			if(!CommonFunctions.isValidKeyId(newEntTlTaskdtl.getTmkdKeyid()))	
			{
					CommonMessage.debugMsg("  newGenTlMommst10 " +  newEntTlTaskdtl.getTmkdKsa());// get detail info from list in Mommeeting object
					//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
					newEntTlTaskdtl.setTmkdKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskdtlSql.TBL_ENT_TL_TASKDTL, 10, "TMKD", "", "Y"));
					CommonMessage.debugMsg("momd Key ID  ::::"+newEntTlTaskdtl.getTmkdKeyid());
					newEntTlTaskdtl.setTmkdTmkmKeyid(newentTlTaskmst.getTmkmKeyid());
					sqls.add(EntTlTaskdtlSql.getInsertSql(entTlTaskMappingksadtlSql.getTmkdDbFields(), newEntTlTaskdtl.getSaveArray()));// add insert sql for detail table
		            CommonMessage.debugMsg("sql  " +sqls);	           			            
			}
			
	
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
	}	catch(Exception e)
	{
		e.printStackTrace();
		throw new Exception(e.getMessage());
	}
	return newentTlTaskmst;
	}
	
	public EntTlTaskmst update(EntTlTaskmst newentTlTaskmst, EntTlTaskdtl newEntTlTaskdtl)	throws Exception { 

		List<String> sqls = new ArrayList<String>();
		EntTlTaskmstSql entTlTaskMappingksamstSql = new EntTlTaskmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlTaskdtlSql entTlTaskMappingksadtlSql = new EntTlTaskdtlSql();
		try {

			sqls.add(EntTlTaskmstSql.getUpdateSql(entTlTaskMappingksamstSql.getTmkmDbFields(), newentTlTaskmst.getSaveArray()));
			if(!CommonFunctions.isValidKeyId(newEntTlTaskdtl.getTmkdKeyid()))
			{
				CommonMessage.debugMsg("  newGenTlMommst10 " +  newEntTlTaskdtl.getTmkdTask());// get detail info from list in Mommeeting object
				//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
				newEntTlTaskdtl.setTmkdKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskdtlSql.TBL_ENT_TL_TASKDTL, 10, "TMKD","", "Y"));
				CommonMessage.debugMsg("momd Key ID  ::::"+newEntTlTaskdtl.getTmkdKeyid());
				newEntTlTaskdtl.setTmkdTmkmKeyid(newentTlTaskmst.getTmkmKeyid());
				sqls.add(EntTlTaskdtlSql.getInsertSql(entTlTaskMappingksadtlSql.getTmkdDbFields(), newEntTlTaskdtl.getSaveArray()));// add insert sql for detail table
	            CommonMessage.debugMsg("sql  " +sqls);	
			}
			else if(CommonFunctions.isValidKeyId(newEntTlTaskdtl.getTmkdKeyid()))
			{
				
				newEntTlTaskdtl.setTmkdTmkmKeyid(newentTlTaskmst.getTmkmKeyid());
				sqls.add(EntTlTaskdtlSql.getUpdateSql(entTlTaskMappingksadtlSql.getTmkdDbFields(), newEntTlTaskdtl.getSaveArray()));// add insert sql for detail table
		        CommonMessage.debugMsg("sql  " +sqls);
		        
			}

			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return newentTlTaskmst;
	}
	
	public EntTlTaskmst delete(EntTlTaskmst entTlTaskmst, String mastkeyid, String details) throws Exception {

		List<String> sqls = new ArrayList<String>();
		List<String[]> detailList = new ArrayList<String[]>();
		try {
			if(CommonFunctions.isValidKeyId(details))
			{
			String sqldetailList=" Select TMKD_TMKM_KEYID,TMKD_KEYID From ENT_TL_TASKMST,ENT_TL_TASKDTL where 1=1 AND TMKD_TMKM_KEYID=TMKM_KEYID AND TMKD_TMKM_KEYID = '"+entTlTaskmst.getTmkmKeyid()+"'";
			detailList=dbActionTemplate.getDataList(sqldetailList);
			for(int i=0;i<detailList.size();i++)
			{
				//sqls.add( EntTlTaskMappingTopicdtlSql.DeleteTopiclist(detailList.get(i).toString()));
				//sqls.add( EntTlTaskMappingksadtlSql.DeleteRplist(detailList.get(i).toString()));
				for(int j=0;j<detailList.size();j++)
				{
					CommonMessage.debugMsg("detailList  "+detailList.size()+"  1  "+detailList.get(i)[0]);
					CommonMessage.debugMsg("detailList detail  "+detailList.size()+"  1  "+detailList.get(i)[1]);
					sqls.add( EntTlUniqpostopicLinkdtlSql.DeleteTopiclist(detailList.get(i)[1].toString()));
					//sqls.add( EntTlTaskdtlSql.DeleteRplistMst(detailList.get(i)[0].toString(),detailList.get(i)[1].toString()));
				 }
			   }
			
			}
			//CommonMessage.debugMsg("sqls Delete "+sqls.toString());
			//sqls.add("Delete from " +entTlTaskMappingksadtlSql.TBL_ENT_TL_TASK_MAPPINGKSADTL+" where TMKD_TMKM_KEYID='"+entTlTaskMappingksamst.getTmkmKeyid()+"'");
			sqls.add( EntTlTaskdtlSql.DeleteRplistMst(entTlTaskmst.getTmkmKeyid()));
			sqls.add(EntTlTaskmstSql.getDeleteSql(entTlTaskMappingksamstSql.getTmkmDbFields(), entTlTaskmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlTaskmst;
	}

	@Override
	public EntTlTaskmst createmst(EntTlTaskmst newEntTlTaskmst,EntTlTaskdtl newEntTlTaskdtl) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTaskmstSql entTlTaskMappingksamstSql = new EntTlTaskmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlTaskdtlSql entTlTaskMappingksadtlSql = new EntTlTaskdtlSql();
		try{
		
			newEntTlTaskmst.setTmkmKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskmstSql.TBL_ENT_TL_TASKMST,10, "TMKM", "MMYY", "Y")); // set the sequnce number 
			sqls.add(EntTlTaskmstSql.getInsertSql(entTlTaskMappingksamstSql.getTmkmDbFields(), newEntTlTaskmst.getSaveArray())); // add insert sql for master table
			
			
			if(!CommonFunctions.isValidKeyId(newEntTlTaskdtl.getTmkdKeyid()))	
			{
					CommonMessage.debugMsg("  newGenTlMommst10 " +  newEntTlTaskdtl.getTmkdKsa());// get detail info from list in Mommeeting object
					//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
					newEntTlTaskdtl.setTmkdKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskdtlSql.TBL_ENT_TL_TASKDTL, 10, "TMKD", "", "Y"));
					CommonMessage.debugMsg("momd Key ID  ::::"+newEntTlTaskdtl.getTmkdKeyid());
					newEntTlTaskdtl.setTmkdTmkmKeyid(newEntTlTaskmst.getTmkmKeyid());
					CommonMessage.debugMsg("Details Table Not saved "+newEntTlTaskdtl.getTmkdTask());
					CommonMessage.debugMsg("Details Table Not saved 1111"+newEntTlTaskdtl.getTmkdTask().trim()!="{}");
					
					CommonMessage.debugMsg("Details Table Not saved 12"+  (!CommonFunctions.isValidKeyId(newEntTlTaskdtl.getTmkdTask())&& newEntTlTaskdtl.getTmkdTask().trim()!="{}"));
					if(!CommonFunctions.isValidKeyId(newEntTlTaskdtl.getTmkdTask())&& newEntTlTaskdtl.getTmkdTask().trim()!="{}")
					{
					sqls.add(EntTlTaskdtlSql.getInsertSql(entTlTaskMappingksadtlSql.getTmkdDbFields(), newEntTlTaskdtl.getSaveArray()));// add insert sql for detail table
		            CommonMessage.debugMsg("sql  " +sqls);	
					}
	          }

		   dbActionTemplate.executeStatements(sqls); // execute the block of sqls
	     }	catch(Exception e)
	        {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
				return newEntTlTaskmst;
			}

	@Override
	public EntTlTaskmst updatemst(EntTlTaskmst newEntTlTaskmst,EntTlTaskdtl newEntTlTaskdtl) throws Exception {
		List<String> sqls = new ArrayList<String>();
		EntTlTaskmstSql entTlTaskMappingksamstSql = new EntTlTaskmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlTaskdtlSql entTlTaskMappingksadtlSql = new EntTlTaskdtlSql();
		try {
			sqls.add(EntTlTaskmstSql.getUpdateSql(entTlTaskMappingksamstSql.getTmkmDbFields(), newEntTlTaskmst.getSaveArray()));
			if(!CommonFunctions.isValidKeyId(newEntTlTaskdtl.getTmkdKeyid()))
			{
				CommonMessage.debugMsg("  newGenTlMommst10 " +  newEntTlTaskdtl.getTmkdTask());// get detail info from list in Mommeeting object
				//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
				newEntTlTaskdtl.setTmkdKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskdtlSql.TBL_ENT_TL_TASKDTL, 10, "TMKD", "", ""));
				CommonMessage.debugMsg("momd Key ID  ::::"+newEntTlTaskdtl.getTmkdKeyid());
				newEntTlTaskdtl.setTmkdTmkmKeyid(newEntTlTaskmst.getTmkmKeyid());
				CommonMessage.debugMsg("Details Task Not INsert "+newEntTlTaskdtl.getTmkdTask());
				if(!CommonFunctions.isValidKeyId(newEntTlTaskdtl.getTmkdTask())&& newEntTlTaskdtl.getTmkdTask().trim()!="{}")
				{
				sqls.add(EntTlTaskdtlSql.getInsertSql(entTlTaskMappingksadtlSql.getTmkdDbFields(), newEntTlTaskdtl.getSaveArray()));// add insert sql for detail table
	            CommonMessage.debugMsg("sql  " +sqls);	
				}
			}
			if(CommonFunctions.isValidKeyId(newEntTlTaskdtl.getTmkdKeyid()))
			{
				if(!CommonFunctions.isValidKeyId(newEntTlTaskdtl.getTmkdTask())&& newEntTlTaskdtl.getTmkdTask().trim()!="{}")
				{
					newEntTlTaskdtl.setTmkdTmkmKeyid(newEntTlTaskmst.getTmkmKeyid());
					sqls.add(EntTlTaskdtlSql.getUpdateSql(entTlTaskMappingksadtlSql.getTmkdDbFields(), newEntTlTaskdtl.getSaveArray()));// add insert sql for detail table
			        CommonMessage.debugMsg("sql  " +sqls);
				}
			}

			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return newEntTlTaskmst;
	   }

	@Override
	public List<String[]> getKsaMainGrid(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		
		String flid = commonFilter.getFlid();
		
		CommonMessage.debugMsg(commonParams +"cctest to............"+condParms);
		
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		paramValues.add(flid);
		
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_TASKMASTERMAINGRID", paramValues);
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

	@Override
	public List<String[]> getKsaDetail(CommonFilter commonFilter,String mstkeyid,String flid,String unique) {
		try
		{
			List<String> params = new ArrayList<String>();
			if( commonFilter.getViewClick() == 'Y'){
				 String sql2 = entTlTaskMappingksamstSql.getTotalDetailGrid(commonFilter,mstkeyid,flid,unique);
					String totalCnt = dbActionTemplate.getSingleValue(sql2); 
					CommonMessage.debugMsg("totalCnt"+totalCnt);
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
			}
			String sql =entTlTaskMappingksamstSql.RpDetailGrid(commonFilter,mstkeyid,flid,unique);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public Workbook TaskKsaExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getTaskKsaReport(commonFilter);
			CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
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
			return excelUtils.writeToExcel(rs,format,2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}
	private ResultSet getTaskKsaReport(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		String flid = commonFilter.getFlid();
		CommonMessage.debugMsg("test to............");		
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		paramValues.add(flid);
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_TASKMASTERMAINGRID", paramValues);
	}

	@Override
	public EntTlTaskmst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:" +mstkeyid);
		EntTlTaskmst newEntTlmst = new EntTlTaskmst();		
		String sql = entTlTaskMappingksamstSql.getTaksKsaSelectSql();				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { mstkeyid };
		newEntTlmst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+newEntTlmst.getTmkmKeyid());
		return newEntTlmst;
	}

	@Override
	public void DeleteRplist(String keyid, String deletechid, String[] detials) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String > sqls = new ArrayList<String>();
			//sqls.add(GenTlMomKpiLinkSql.DeleteMomRow(keyid));
			CommonMessage.debugMsg("Keyid 22222:::::"+keyid);
			for(int i=0;i<detials.length;i++)
			{
			if(UIUtils.isValidKeyId(deletechid)){
				if("Y".equals(deletechid.trim())){
					CommonMessage.debugMsg("deletechid 22222:::::"+deletechid);
					sqls.add( EntTlUniqpostopicLinkdtlSql.DeleteTopiclist(detials[i]));
				}
			}
			    sqls.add( entTlTaskMappingksadtlSql.DeleteRplist(detials[i]));
	  
			dbActionTemplate.executeStatements(sqls);
		}
		}
		/*catch(BusinessApplicationExceptions e){
			CommonMessage.debugMsg(" Message:"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}*/
		catch( Exception e){
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<String[]> getnewSelect(String flid, String unique) {
		// TODO Auto-generated method stub
		try
		{
			List<String> params = new ArrayList<String>();
			String sql =entTlTaskMappingksamstSql.newSelectlist(flid,unique);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}
	
	
	}
  

