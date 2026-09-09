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
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlUniqpostopicLinkmstDao;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.EntTlSkillReviewpointmstSql;
import com.akranta.tpm.dao.sql.EntTlTopicmstSql;
import com.akranta.tpm.dao.sql.EntTlUniqpostopicLinkdtlSql;
import com.akranta.tpm.dao.sql.EntTlUniqpostopicLinkmstSql;
import com.akranta.tpm.dao.sql.EntTlTaskMappingksadtlSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMomattendanceSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillReviewpointmst;
import com.akranta.tpm.model.EntTlUniqpostopicLinkdtl;
import com.akranta.tpm.model.EntTlUniqpostopicLinkmst;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class EntTlUniqpostopicLinkmstDaoImpl implements EntTlUniqpostopicLinkmstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlUniqpostopicLinkmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlUniqpostopicLinkmst create(EntTlUniqpostopicLinkmst entTlTaskMappingTopicmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlUniqpostopicLinkmstSql entTlTaskMappingTopicmstSql = new EntTlUniqpostopicLinkmstSql();
		EntTlUniqpostopicLinkdtlSql entTlTaskMappingTopicdtlSql =new EntTlUniqpostopicLinkdtlSql();// contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlTaskMappingTopicmst.setTmtmKeyid(dbActionTemplate.getSequenceNumber(EntTlUniqpostopicLinkmstSql.TBL_ENT_TL_UNIQPOSTOPIC_LINKMST,10,"TMTM","MMYY","Y")); // set the sequnce number 
			sqls.add(EntTlUniqpostopicLinkmstSql.getInsertSql(entTlTaskMappingTopicmstSql.getTmtmDbFields(), entTlTaskMappingTopicmst.getSaveArray())); // add insert sql for master table
			List<EntTlUniqpostopicLinkdtl> entTlTaskMappingTopicdtls= entTlTaskMappingTopicmst.getTaskTopicDetails();
		if(entTlTaskMappingTopicdtls !=null && entTlTaskMappingTopicdtls.size()> 0)	
		{
			for(EntTlUniqpostopicLinkdtl entTaskMappingdtl: entTlTaskMappingTopicdtls)
			{
				CommonMessage.debugMsg("  newGenTlMommst10 " +  entTlTaskMappingTopicmst.getTaskTopicDetails());// get detail info from list in Mommeeting object
				//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
				entTaskMappingdtl.setTmtdKeyid(dbActionTemplate.getSequenceNumber(EntTlUniqpostopicLinkdtlSql.TBL_ENT_TL_UNIQPOSTOPIC_LINKDTL, 10, "TMTD", "", ""));
				CommonMessage.debugMsg("momd Key ID  ::::"+entTaskMappingdtl.getTmtdKeyid());
				entTaskMappingdtl.setTmtdTmtmKeyid(entTlTaskMappingTopicmst.getTmtmKeyid());
					CommonMessage.debugMsg("entTaskMappingdtl");
					sqls.add(EntTlUniqpostopicLinkdtlSql.getInsertSql(entTlTaskMappingTopicdtlSql.getTmtdDbFields(), entTaskMappingdtl.getSaveArray()));// add insert sql for detail table
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
	
	public EntTlUniqpostopicLinkmst update(EntTlUniqpostopicLinkmst entTlTaskMappingTopicmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlUniqpostopicLinkmstSql entTlTaskMappingTopicmstSql = new EntTlUniqpostopicLinkmstSql();
		EntTlUniqpostopicLinkdtlSql entTlTaskMappingTopicdtlSql =new EntTlUniqpostopicLinkdtlSql();
		try {
            CommonMessage.debugMsg("Details Tabel 1 "+entTlTaskMappingTopicmst.getTmtmRoleKeyid());
			sqls.add(EntTlUniqpostopicLinkmstSql.getUpdateSql(entTlTaskMappingTopicmstSql.getTmtmDbFields(), entTlTaskMappingTopicmst.getSaveArray()));
			List<EntTlUniqpostopicLinkdtl> entTlTaskMappingTopicdtls= entTlTaskMappingTopicmst.getTaskTopicDetails();
		    if(CommonFunctions.isValidKeyId(entTlTaskMappingTopicmst.getTmtmKeyid())){
			
		    	//sqls.add(EntTlUniqpostopicLinkdtlSql.getDeleteDtlSql(entTlTaskMappingTopicmst.getTmtmKeyid()));// add insert sql for detail table
		    	
			  if( entTlTaskMappingTopicdtls != null && entTlTaskMappingTopicdtls.size()> 0)	
			     {
			       for(EntTlUniqpostopicLinkdtl entTaskMappingdtl: entTlTaskMappingTopicdtls)
				      {
					if(entTaskMappingdtl.getTmtdKeyid().length()<5 )
					{CommonMessage.debugMsg("details "+entTaskMappingdtl.getTmtdTmkmKeyid());
						CommonMessage.debugMsg("  newGenTlMommst10 " +  entTlTaskMappingTopicmst.getTaskTopicDetails());// get detail info from list in Mommeeting object
						//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
						entTaskMappingdtl.setTmtdKeyid(dbActionTemplate.getSequenceNumber(EntTlUniqpostopicLinkdtlSql.TBL_ENT_TL_UNIQPOSTOPIC_LINKDTL, 10, "TMTD", "", ""));
						CommonMessage.debugMsg("momd Key ID  ::::"+entTaskMappingdtl.getTmtdKeyid());
						entTaskMappingdtl.setTmtdTmtmKeyid(entTlTaskMappingTopicmst.getTmtmKeyid());
					    sqls.add(EntTlUniqpostopicLinkdtlSql.getInsertSql(entTlTaskMappingTopicdtlSql.getTmtdDbFields(), entTaskMappingdtl.getSaveArray()));// add insert sql for detail table
		                CommonMessage.debugMsg("Task Keyid ::::"+entTaskMappingdtl.getTmtdTmkmKeyid());
					    CommonMessage.debugMsg("sql  " +sqls);	
					}	 
					else
					{	
						   //CommonMessage.debugMsg("  newGenTlMommst4 " +  entTaskMappingdtl.getTmtdKeyid());
							CommonMessage.debugMsg("getTmtdKeyid Keyid ::::"+entTaskMappingdtl.getTmtdKeyid());
							CommonMessage.debugMsg("keyid of detail");
							sqls.add(EntTlUniqpostopicLinkdtlSql.DeleteTasklist(entTaskMappingdtl.getTmtdKeyid()));
							//sqls.add(entTlTaskMappingTopicdtlSql.getUpdateSql(entTlTaskMappingTopicdtlSql.getTmtdDbFields(), entTaskMappingdtl.getSaveArray()));		
				}
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
	
	public EntTlUniqpostopicLinkmst delete(EntTlUniqpostopicLinkmst entTlTaskMappingTopicmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlUniqpostopicLinkmstSql entTlTaskMappingTopicmstSql = new EntTlUniqpostopicLinkmstSql();
		EntTlUniqpostopicLinkdtlSql entTlTaskMappingTopicdtlSql = new EntTlUniqpostopicLinkdtlSql();
		int count=0;
		String sql="";
		String cnt;
		sql+=" select Count(*)  From ENT_TL_TRAININGNEEDMST where 1=1 AND TNIM_FLID='"+entTlTaskMappingTopicmst.getTmtmFlid()+"'";
		cnt=dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg("delete ::: "+cnt);		
		try {
			if(UIUtils.isValidKeyId(cnt))
			{
				if(Integer.parseInt(cnt)>0)
				{
					count++;
				    throw new BusinessApplicationExceptions("TopicReference,");
				}
				else
				{
					sqls.add("Delete from " +entTlTaskMappingTopicdtlSql.TBL_ENT_TL_UNIQPOSTOPIC_LINKDTL+" where TMTD_TMTM_KEYID='"+entTlTaskMappingTopicmst.getTmtmKeyid()+"'");
					sqls.add(entTlTaskMappingTopicmstSql.getDeleteSql(entTlTaskMappingTopicmstSql.getTmtmDbFields(), entTlTaskMappingTopicmst.getSaveArray()));
					dbActionTemplate.executeStatements(sqls);	
				}
			}		
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
	public EntTlUniqpostopicLinkmst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception {
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:" +mstkeyid);
		EntTlUniqpostopicLinkmst newEntTlTaskMappingTopicmst = new EntTlUniqpostopicLinkmst();		
		String sql = EntTlUniqpostopicLinkmstSql.getTaskTpSelectSql();				
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
			String sql =EntTlUniqpostopicLinkmstSql.RpDetailGrid(commonFilter,flid,uniquePostion,mstkeyid,createmode,topic);
			//
			CommonMessage.debugMsg("sql Count==="+sql);
			String countsql = CommonFilterSqls.countSql(sql.toString(), commonFilter.getGridFilter());
			String cntStr = dbActionTemplate.getSingleValue(countsql);
			int count = Integer.parseInt(cntStr);
			commonFilter.setTotalRecordCnt(count);
			if( count > 0  ){
				GridParams gridParams = new GridParams();
				gridParams.setFromRow(commonFilter.getFromRow());
				gridParams.setToRow(commonFilter.getToRow());
				String oSql =  CommonFilterSqls.addPaginationParams(sql.toString(), gridParams);
				CommonMessage.debugMsg("sql " + oSql);
				List<String[]> operator = dbActionTemplate.getDataList(oSql, params);
				return operator;

			}
			//
			/*
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			CommonMessage.debugMsg("Exception:"+sql);
		    return operator;
			*/
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
			return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_TASK_MAPPINGTOPIC", paramValues);
	}

	@Override
	public void DeleteTasklist(String keyid) throws Exception {
		try
		{
			List<String > sqls = new ArrayList<String>();
			CommonMessage.debugMsg("Keyid 22222:::::"+keyid);
			sqls.add(EntTlUniqpostopicLinkdtlSql.DeleteTasklist(keyid));
	  
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

