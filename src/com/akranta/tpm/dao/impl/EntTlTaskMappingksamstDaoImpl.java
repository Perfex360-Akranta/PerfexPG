package com.akranta.tpm.dao.impl;




import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlTaskMappingksamstDao;
import com.akranta.tpm.dao.sql.EntTlSkillReviewpointdetSql;
import com.akranta.tpm.dao.sql.EntTlSkillReviewpointmstSql;
import com.akranta.tpm.dao.sql.EntTlTaskMappingTopicdtlSql;
import com.akranta.tpm.dao.sql.EntTlTaskMappingksadtlSql;
import com.akranta.tpm.dao.sql.EntTlTaskMappingksamstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillReviewpointmst;
import com.akranta.tpm.model.EntTlTaskMappingksadtl;
import com.akranta.tpm.model.EntTlTaskMappingksamst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class EntTlTaskMappingksamstDaoImpl implements EntTlTaskMappingksamstDao {


	private DBActionTemplate dbActionTemplate; 
	EntTlTaskMappingksadtlSql entTlTaskMappingksadtlSql =new EntTlTaskMappingksadtlSql();
	EntTlTaskMappingksamstSql entTlTaskMappingksamstSql = new EntTlTaskMappingksamstSql();
	public EntTlTaskMappingksamstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlTaskMappingksamst create(EntTlTaskMappingksamst entTlTaskMappingksamst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTaskMappingksamstSql entTlTaskMappingksamstSql = new EntTlTaskMappingksamstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlTaskMappingksamst.setTmkmKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskMappingksamstSql.TBL_ENT_TL_TASK_MAPPINGKSAMST)); // set the sequnce number 
			sqls.add(EntTlTaskMappingksamstSql.getInsertSql(entTlTaskMappingksamstSql.getTmkmDbFields(), entTlTaskMappingksamst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlTaskMappingksamst;
	}
	
	public EntTlTaskMappingksamst update(EntTlTaskMappingksamst entTlTaskMappingksamst, EntTlTaskMappingksadtl newentTlTaskMappingksadtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlTaskMappingksamstSql entTlTaskMappingksamstSql = new EntTlTaskMappingksamstSql();
		EntTlTaskMappingksadtlSql entTlTaskMappingksadtlSql = new EntTlTaskMappingksadtlSql();
		try {

			sqls.add(EntTlTaskMappingksamstSql.getUpdateSql(entTlTaskMappingksamstSql.getTmkmDbFields(), entTlTaskMappingksamst.getSaveArray()));
			if(!CommonFunctions.isValidKeyId(newentTlTaskMappingksadtl.getTmkdKeyid()))
			{
				CommonMessage.debugMsg("  newGenTlMommst10 " +  newentTlTaskMappingksadtl.getTmkdTask());// get detail info from list in Mommeeting object
				//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
				newentTlTaskMappingksadtl.setTmkdKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskMappingksadtlSql.TBL_ENT_TL_TASK_MAPPINGKSADTL, 10, "TMKD", "MMYY", "Y"));
				CommonMessage.debugMsg("momd Key ID  ::::"+newentTlTaskMappingksadtl.getTmkdKeyid());
				newentTlTaskMappingksadtl.setTmkdTmkmKeyid(entTlTaskMappingksamst.getTmkmKeyid());
				sqls.add(EntTlTaskMappingksadtlSql.getInsertSql(entTlTaskMappingksadtlSql.getTmkdDbFields(), newentTlTaskMappingksadtl.getSaveArray()));// add insert sql for detail table
	            CommonMessage.debugMsg("sql  " +sqls);	
			}
			else if(CommonFunctions.isValidKeyId(newentTlTaskMappingksadtl.getTmkdKeyid()))
			{
				
				newentTlTaskMappingksadtl.setTmkdTmkmKeyid(entTlTaskMappingksamst.getTmkmKeyid());
				sqls.add(EntTlTaskMappingksadtlSql.getUpdateSql(entTlTaskMappingksadtlSql.getTmkdDbFields(), newentTlTaskMappingksadtl.getSaveArray()));// add insert sql for detail table
		        CommonMessage.debugMsg("sql  " +sqls);
		        
			}

			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlTaskMappingksamst;
	}
	
	public EntTlTaskMappingksamst delete(EntTlTaskMappingksamst entTlTaskMappingksamst,String mastkeyid, String details)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		List<String[]> detailList = new ArrayList<String[]>();
		EntTlTaskMappingksamstSql entTlTaskMappingksamstSql = new EntTlTaskMappingksamstSql();
		EntTlTaskMappingksadtlSql entTlTaskMappingksadtlSql = new EntTlTaskMappingksadtlSql();
		try {
			if(CommonFunctions.isValidKeyId(details))
			{
			String sqldetailList=" Select TMKD_TMKM_KEYID,TMKD_KEYID From ENT_TL_TASK_MAPPINGKSAMST,ENT_TL_TASK_MAPPINGKSADTL where 1=1 AND TMKD_TMKM_KEYID=TMKM_KEYID AND TMKD_TMKM_KEYID = '"+entTlTaskMappingksamst.getTmkmKeyid()+"'";
			detailList=dbActionTemplate.getDataList(sqldetailList);
			for(int i=0;i<detailList.size();i++)
			{
				//sqls.add( EntTlTaskMappingTopicdtlSql.DeleteTopiclist(detailList.get(i).toString()));
				//sqls.add( EntTlTaskMappingksadtlSql.DeleteRplist(detailList.get(i).toString()));
				for(int j=0;j<detailList.size();j++)
				{
					//CommonMessage.debugMsg("detailList  "+detailList.size()+"  1  "+detailList.get(i)[0]);
					//CommonMessage.debugMsg("detailList detail  "+detailList.size()+"  1  "+detailList.get(i)[1]);
					sqls.add( EntTlTaskMappingTopicdtlSql.DeleteTopiclist(detailList.get(i)[1].toString()));
					sqls.add( EntTlTaskMappingksadtlSql.DeleteRplistMst(detailList.get(i)[0].toString(),detailList.get(i)[1].toString()));
				 }
			   }
			}
			//CommonMessage.debugMsg("sqls Delete "+sqls.toString());
			//sqls.add("Delete from " +entTlTaskMappingksadtlSql.TBL_ENT_TL_TASK_MAPPINGKSADTL+" where TMKD_TMKM_KEYID='"+entTlTaskMappingksamst.getTmkmKeyid()+"'");
			sqls.add(entTlTaskMappingksamstSql.getDeleteSql(entTlTaskMappingksamstSql.getTmkmDbFields(), entTlTaskMappingksamst.getSaveArray()));
            
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlTaskMappingksamst;
	}

	@Override
	public EntTlTaskMappingksamst create(EntTlTaskMappingksamst newentTlTaskMappingksamst,EntTlTaskMappingksadtl newentTlTaskMappingksadtl) throws Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTaskMappingksamstSql entTlTaskMappingksamstSql = new EntTlTaskMappingksamstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlTaskMappingksadtlSql entTlTaskMappingksadtlSql = new EntTlTaskMappingksadtlSql();
		try{
		
			newentTlTaskMappingksamst.setTmkmKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskMappingksamstSql.TBL_ENT_TL_TASK_MAPPINGKSAMST)); // set the sequnce number 
			sqls.add(EntTlTaskMappingksamstSql.getInsertSql(entTlTaskMappingksamstSql.getTmkmDbFields(), newentTlTaskMappingksamst.getSaveArray())); // add insert sql for master table
			
			
			if(!CommonFunctions.isValidKeyId(newentTlTaskMappingksadtl.getTmkdKeyid()))	
			{
					CommonMessage.debugMsg("  newGenTlMommst10 " +  newentTlTaskMappingksadtl.getTmkdKsa());// get detail info from list in Mommeeting object
					//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
					newentTlTaskMappingksadtl.setTmkdKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskMappingksadtlSql.TBL_ENT_TL_TASK_MAPPINGKSADTL, 10, "TMKD", "MMYY", "Y"));
					CommonMessage.debugMsg("momd Key ID  ::::"+newentTlTaskMappingksadtl.getTmkdKeyid());
					newentTlTaskMappingksadtl.setTmkdTmkmKeyid(newentTlTaskMappingksamst.getTmkmKeyid());
					sqls.add(EntTlTaskMappingksadtlSql.getInsertSql(entTlTaskMappingksadtlSql.getTmkdDbFields(), newentTlTaskMappingksadtl.getSaveArray()));// add insert sql for detail table
		            CommonMessage.debugMsg("sql  " +sqls);	           			            
			}
			
	
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
	}	catch(Exception e)
	{
		e.printStackTrace();
		throw new Exception(e.getMessage());
	}
	return newentTlTaskMappingksamst;
	}

	@Override
	public List<String[]> getKsaDetail(CommonFilter commonFilter,String mstkeyid) {
		// TODO Auto-generated method stub
		try
		{
			List<String> params = new ArrayList<String>();
			String sql =entTlTaskMappingksamstSql.RpDetailGrid(commonFilter,mstkeyid);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public EntTlTaskMappingksamst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:" +mstkeyid);
		EntTlTaskMappingksamst newEntTlTaskMappingksamst = new EntTlTaskMappingksamst();		
		String sql = entTlTaskMappingksamstSql.getTaksKsaSelectSql();				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { mstkeyid };
		newEntTlTaskMappingksamst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+newEntTlTaskMappingksamst.getTmkmKeyid());
		return newEntTlTaskMappingksamst;
	}

	@Override
	public List<String[]> getKsaMainGrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		CommonMessage.debugMsg("test to............");
		
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_TASK_MAPPINGKSA", paramValues);
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
	public void DeleteRplist(String keyid,String deletechid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String > sqls = new ArrayList<String>();
			//sqls.add(GenTlMomKpiLinkSql.DeleteMomRow(keyid));
			CommonMessage.debugMsg("Keyid 22222:::::"+keyid);
			if(UIUtils.isValidKeyId(deletechid)){
				if("Y".equals(deletechid.trim())){
					CommonMessage.debugMsg("deletechid 22222:::::"+deletechid);
					sqls.add( EntTlTaskMappingTopicdtlSql.DeleteTopiclist(keyid));
				}
			}
			    sqls.add( EntTlTaskMappingksadtlSql.DeleteRplist(keyid));
	  
			dbActionTemplate.executeStatements(sqls);
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
	public Workbook TaskKsaExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
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
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("TEST_PC_TEST.ENT_FN_TASK_MAPPINGKSA", paramValues);
	}

	@Override
	public EntTlTaskMappingksamst createmst(EntTlTaskMappingksamst newentTlTaskMappingksamst,EntTlTaskMappingksadtl entTlTaskMappingksadtl) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTaskMappingksamstSql entTlTaskMappingksamstSql = new EntTlTaskMappingksamstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlTaskMappingksadtlSql entTlTaskMappingksadtlSql = new EntTlTaskMappingksadtlSql();
		try{
		
			newentTlTaskMappingksamst.setTmkmKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskMappingksamstSql.TBL_ENT_TL_TASK_MAPPINGKSAMST)); // set the sequnce number 
			sqls.add(EntTlTaskMappingksamstSql.getInsertSql(entTlTaskMappingksamstSql.getTmkmDbFields(), newentTlTaskMappingksamst.getSaveArray())); // add insert sql for master table
			
			
			if(!CommonFunctions.isValidKeyId(entTlTaskMappingksadtl.getTmkdKeyid()))	
			{
					CommonMessage.debugMsg("  newGenTlMommst10 " +  entTlTaskMappingksadtl.getTmkdKsa());// get detail info from list in Mommeeting object
					//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
					entTlTaskMappingksadtl.setTmkdKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskMappingksadtlSql.TBL_ENT_TL_TASK_MAPPINGKSADTL, 10, "TMKD", "MMYY", "Y"));
					CommonMessage.debugMsg("momd Key ID  ::::"+entTlTaskMappingksadtl.getTmkdKeyid());
					entTlTaskMappingksadtl.setTmkdTmkmKeyid(newentTlTaskMappingksamst.getTmkmKeyid());
					CommonMessage.debugMsg("Details Table Not saved "+entTlTaskMappingksadtl.getTmkdTask());
					CommonMessage.debugMsg("Details Table Not saved 1111"+entTlTaskMappingksadtl.getTmkdTask().trim()!="{}");
					
					CommonMessage.debugMsg("Details Table Not saved 12"+  (!CommonFunctions.isValidKeyId(entTlTaskMappingksadtl.getTmkdTask())&& entTlTaskMappingksadtl.getTmkdTask().trim()!="{}"));
					if(!CommonFunctions.isValidKeyId(entTlTaskMappingksadtl.getTmkdTask())&& entTlTaskMappingksadtl.getTmkdTask().trim()!="{}")
					{
					sqls.add(EntTlTaskMappingksadtlSql.getInsertSql(entTlTaskMappingksadtlSql.getTmkdDbFields(), entTlTaskMappingksadtl.getSaveArray()));// add insert sql for detail table
		            CommonMessage.debugMsg("sql  " +sqls);	
					}
			}
			
	
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
	}	catch(Exception e)
	{
		e.printStackTrace();
		throw new Exception(e.getMessage());
	}
	return newentTlTaskMappingksamst;
	}

	@Override
	public EntTlTaskMappingksamst updatemst(EntTlTaskMappingksamst newentTlTaskMappingksamst,EntTlTaskMappingksadtl newentTlTaskMappingksadtl) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		EntTlTaskMappingksamstSql entTlTaskMappingksamstSql = new EntTlTaskMappingksamstSql();
		EntTlTaskMappingksadtlSql entTlTaskMappingksadtlSql = new EntTlTaskMappingksadtlSql();
		try {
			sqls.add(EntTlTaskMappingksamstSql.getUpdateSql(entTlTaskMappingksamstSql.getTmkmDbFields(), newentTlTaskMappingksamst.getSaveArray()));
			if(!CommonFunctions.isValidKeyId(newentTlTaskMappingksadtl.getTmkdKeyid()))
			{
				CommonMessage.debugMsg("  newGenTlMommst10 " +  newentTlTaskMappingksadtl.getTmkdTask());// get detail info from list in Mommeeting object
				//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
				newentTlTaskMappingksadtl.setTmkdKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskMappingksadtlSql.TBL_ENT_TL_TASK_MAPPINGKSADTL, 10, "TMKD", "MMYY", "Y"));
				CommonMessage.debugMsg("momd Key ID  ::::"+newentTlTaskMappingksadtl.getTmkdKeyid());
				newentTlTaskMappingksadtl.setTmkdTmkmKeyid(newentTlTaskMappingksamst.getTmkmKeyid());
				CommonMessage.debugMsg("Details Task Not INsert "+newentTlTaskMappingksadtl.getTmkdTask());
				if(!CommonFunctions.isValidKeyId(newentTlTaskMappingksadtl.getTmkdTask())&& newentTlTaskMappingksadtl.getTmkdTask().trim()!="{}")
				{
				sqls.add(EntTlTaskMappingksadtlSql.getInsertSql(entTlTaskMappingksadtlSql.getTmkdDbFields(), newentTlTaskMappingksadtl.getSaveArray()));// add insert sql for detail table
	            CommonMessage.debugMsg("sql  " +sqls);	
				}
			}
			if(CommonFunctions.isValidKeyId(newentTlTaskMappingksadtl.getTmkdKeyid()))
			{
				if(!CommonFunctions.isValidKeyId(newentTlTaskMappingksadtl.getTmkdTask())&& newentTlTaskMappingksadtl.getTmkdTask().trim()!="{}")
				{
				newentTlTaskMappingksadtl.setTmkdTmkmKeyid(newentTlTaskMappingksamst.getTmkmKeyid());
				sqls.add(EntTlTaskMappingksadtlSql.getUpdateSql(entTlTaskMappingksadtlSql.getTmkdDbFields(), newentTlTaskMappingksadtl.getSaveArray()));// add insert sql for detail table
		        CommonMessage.debugMsg("sql  " +sqls);
				}
			}

			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return newentTlTaskMappingksamst;
	}
	
}

