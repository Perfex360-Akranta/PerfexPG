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
import com.akranta.tpm.dao.EntTlTaskMappingTopicmstDao;
import com.akranta.tpm.dao.sql.EntTlSkillReviewpointmstSql;
import com.akranta.tpm.dao.sql.EntTlTaskMappingTopicdtlSql;
import com.akranta.tpm.dao.sql.EntTlTaskMappingTopicmstSql;
import com.akranta.tpm.dao.sql.EntTlTaskMappingksadtlSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMomattendanceSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillReviewpointmst;
import com.akranta.tpm.model.EntTlTaskMappingTopicdtl;
import com.akranta.tpm.model.EntTlTaskMappingTopicmst;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class EntTlTaskMappingTopicmstDaoImpl implements EntTlTaskMappingTopicmstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlTaskMappingTopicmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlTaskMappingTopicmst create(EntTlTaskMappingTopicmst entTlTaskMappingTopicmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTaskMappingTopicmstSql entTlTaskMappingTopicmstSql = new EntTlTaskMappingTopicmstSql();
		EntTlTaskMappingTopicdtlSql entTlTaskMappingTopicdtlSql =new EntTlTaskMappingTopicdtlSql();// contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlTaskMappingTopicmst.setTmtmKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskMappingTopicmstSql.TBL_ENT_TL_TASK_MAPPING_TOPICMST)); // set the sequnce number 
			sqls.add(EntTlTaskMappingTopicmstSql.getInsertSql(entTlTaskMappingTopicmstSql.getTmtmDbFields(), entTlTaskMappingTopicmst.getSaveArray())); // add insert sql for master table
			List<EntTlTaskMappingTopicdtl> entTlTaskMappingTopicdtls= entTlTaskMappingTopicmst.getTaskTopicDetails();
		if(entTlTaskMappingTopicdtls !=null && entTlTaskMappingTopicdtls.size()> 0)	
		{
			for(EntTlTaskMappingTopicdtl entTaskMappingdtl: entTlTaskMappingTopicdtls)
			{
				CommonMessage.debugMsg("  newGenTlMommst10 " +  entTlTaskMappingTopicmst.getTaskTopicDetails());// get detail info from list in Mommeeting object
				//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
				entTaskMappingdtl.setTmtdKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskMappingTopicdtlSql.TBL_ENT_TL_TASK_MAPPING_TOPICDTL, 10, "TMTD", "MMYY", "Y"));
				CommonMessage.debugMsg("momd Key ID  ::::"+entTaskMappingdtl.getTmtdKeyid());
				entTaskMappingdtl.setTmtdTmtmKeyid(entTlTaskMappingTopicmst.getTmtmKeyid());
					CommonMessage.debugMsg("entTaskMappingdtl");
					sqls.add(EntTlTaskMappingTopicdtlSql.getInsertSql(entTlTaskMappingTopicdtlSql.getTmtdDbFields(), entTaskMappingdtl.getSaveArray()));// add insert sql for detail table
		            CommonMessage.debugMsg("sql  " +sqls);	
				  
			}
		}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlTaskMappingTopicmst;
	}
	
	public EntTlTaskMappingTopicmst update(EntTlTaskMappingTopicmst entTlTaskMappingTopicmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlTaskMappingTopicmstSql entTlTaskMappingTopicmstSql = new EntTlTaskMappingTopicmstSql();
		EntTlTaskMappingTopicdtlSql entTlTaskMappingTopicdtlSql =new EntTlTaskMappingTopicdtlSql();
		try {
            CommonMessage.debugMsg("Details Tabel 1 ");
			sqls.add(EntTlTaskMappingTopicmstSql.getUpdateSql(entTlTaskMappingTopicmstSql.getTmtmDbFields(), entTlTaskMappingTopicmst.getSaveArray()));
			List<EntTlTaskMappingTopicdtl> entTlTaskMappingTopicdtls= entTlTaskMappingTopicmst.getTaskTopicDetails();
			CommonMessage.debugMsg("Details Tabel 2 "+entTlTaskMappingTopicdtls.size());
		  if(CommonFunctions.isValidKeyId(entTlTaskMappingTopicmst.getTmtmKeyid()))
          {
			sqls.add(EntTlTaskMappingTopicdtlSql.getDeleteDtlSql(entTlTaskMappingTopicmst.getTmtmKeyid()));// add insert sql for detail table
			 if( entTlTaskMappingTopicdtls != null && entTlTaskMappingTopicdtls.size()> 0)	
			   {	
				 for(EntTlTaskMappingTopicdtl entTaskMappingdtl: entTlTaskMappingTopicdtls)
				  {
					/*if(!UIUtils.isValidKeyId(entTaskMappingdtl.getTmtdKeyid()))
					{*/
						CommonMessage.debugMsg("  newGenTlMommst10 " +  entTlTaskMappingTopicmst.getTaskTopicDetails());// get detail info from list in Mommeeting object
						//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
						entTaskMappingdtl.setTmtdKeyid(dbActionTemplate.getSequenceNumber(EntTlTaskMappingTopicdtlSql.TBL_ENT_TL_TASK_MAPPING_TOPICDTL, 10, "TMTD", "MMYY", "Y"));
						CommonMessage.debugMsg("momd Key ID  ::::"+entTaskMappingdtl.getTmtdKeyid());
						entTaskMappingdtl.setTmtdTmtmKeyid(entTlTaskMappingTopicmst.getTmtmKeyid());
					    sqls.add(EntTlTaskMappingTopicdtlSql.getInsertSql(entTlTaskMappingTopicdtlSql.getTmtdDbFields(), entTaskMappingdtl.getSaveArray()));// add insert sql for detail table
		                CommonMessage.debugMsg("Task Keyid ::::"+entTaskMappingdtl.getTmtdTmkmKeyid());
					    CommonMessage.debugMsg("sql  " +sqls);	
					  
					/*} 
					else
					{	
						   //CommonMessage.debugMsg("  newGenTlMommst4 " +  entTaskMappingdtl.getTmtdKeyid());
							CommonMessage.debugMsg("Task Keyid ::::"+entTaskMappingdtl.getTmtdTmkmKeyid());
							CommonMessage.debugMsg("keyid of detail");
							sqls.add(entTlTaskMappingTopicdtlSql.getUpdateSql(entTlTaskMappingTopicdtlSql.getTmtdDbFields(), entTaskMappingdtl.getSaveArray()));		
				}*/
			}
          }
	}
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlTaskMappingTopicmst;
	}
	
	public EntTlTaskMappingTopicmst delete(EntTlTaskMappingTopicmst entTlTaskMappingTopicmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlTaskMappingTopicmstSql entTlTaskMappingTopicmstSql = new EntTlTaskMappingTopicmstSql();
		EntTlTaskMappingTopicdtlSql entTlTaskMappingTopicdtlSql = new EntTlTaskMappingTopicdtlSql();
		try {	
			sqls.add("Delete from " +entTlTaskMappingTopicdtlSql.TBL_ENT_TL_TASK_MAPPING_TOPICDTL+" where TMTD_TMTM_KEYID='"+entTlTaskMappingTopicmst.getTmtmKeyid()+"'");
			sqls.add(entTlTaskMappingTopicmstSql.getDeleteSql(entTlTaskMappingTopicmstSql.getTmtmDbFields(), entTlTaskMappingTopicmst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlTaskMappingTopicmst;
	}

	@Override
	public List<String[]> getTaskMainGrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		CommonMessage.debugMsg("test to............");
		
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_TASK_MAPPINGTOPIC", paramValues);
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
	public EntTlTaskMappingTopicmst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception {
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:" +mstkeyid);
		EntTlTaskMappingTopicmst newEntTlTaskMappingTopicmst = new EntTlTaskMappingTopicmst();		
		String sql = EntTlTaskMappingTopicmstSql.getTaskTpSelectSql();				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { mstkeyid };
		newEntTlTaskMappingTopicmst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+newEntTlTaskMappingTopicmst.getTmtmKeyid());
		return newEntTlTaskMappingTopicmst;
	}

	@Override
	public List<String[]> getTopicDetail(CommonFilter commonFilter,String flid, String uniquePostion,String mstkeyid,String createmode,String topic) {
		// TODO Auto-generated method stub
		try
		{
			List<String> params = new ArrayList<String>();
			String sql =EntTlTaskMappingTopicmstSql.RpDetailGrid(commonFilter,flid,uniquePostion,mstkeyid,createmode,topic);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			CommonMessage.debugMsg("Exception:"+sql);
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public Workbook TaskTopicExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getTaskTopicReport(commonFilter);
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
	private ResultSet getTaskTopicReport(CommonFilter commonFilter) throws Exception
	{
		
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			return dbActionTemplate.dbFunctionCall("TEST_PC_TEST.ENT_FN_TASK_MAPPINGTOPIC", paramValues);
	}

	@Override
	public void DeleteTasklist(String keyid) throws Exception {
		try
		{
			List<String > sqls = new ArrayList<String>();
			CommonMessage.debugMsg("Keyid 22222:::::"+keyid);
			sqls.add(EntTlTaskMappingTopicdtlSql.DeleteTasklist(keyid));
	  
			dbActionTemplate.executeStatements(sqls);
		}
		catch(BusinessApplicationExceptions e){
			CommonMessage.debugMsg(" Message:"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch( Exception e){
			throw new Exception(e.getMessage());
		}
		
	}

	
	
}

