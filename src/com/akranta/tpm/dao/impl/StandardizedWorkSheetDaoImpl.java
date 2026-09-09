package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.StandardizedWorkSheetDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMomKpiLinkSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.dao.sql.KznTlMstSql;
//import com.akranta.tpm.dao.sql.QtmTlKnowwhymstSql;
//import com.akranta.tpm.dao.sql.QtmTlSopdtlSql;
//import com.akranta.tpm.dao.sql.QtmTlSopmstSql;
//import com.akranta.tpm.dao.sql.SopTlVisualchecklistdtlSql;
//import com.akranta.tpm.dao.sql.SopTlVisualchecklistmstSql;
import com.akranta.tpm.dao.sql.StdTlStdworksheetdtlSql;
import com.akranta.tpm.dao.sql.StdTlStdworksheetmstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlEvaluationdtl;
//import com.akranta.tpm.model.SopTlVisualchecklistmst;
import com.akranta.tpm.model.StdTlStdworksheetdtl;
import com.akranta.tpm.model.StdTlStdworksheetmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.FunctionCallApi;
//import com.akranta.tpm.service.api.WhywhyServiceApi;
import com.akranta.tpm.service.api.WorksheetServiceApi;


public class StandardizedWorkSheetDaoImpl implements StandardizedWorkSheetDao{
	
		private DBActionTemplate dbActionTemplate; 
		private WorksheetServiceApi worksheetserviceapi;
		
		FunctionCallApi fnCallApi;
		

		public StandardizedWorkSheetDaoImpl(DBActionTemplate dbActionTemplate) 
		{
			this.dbActionTemplate = dbActionTemplate;
		}
		public void StandardizedWorkSheetDaoImplJwt(String JwtToken) 
		{
			try{
				worksheetserviceapi = new WorksheetServiceApi(JwtToken);
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
		/*public List<String[]> getStdWoShMainGrid() throws Exception  {
			String sql=" select * from (SELECT 'Coating'as Process , 'Manual Work' as TypeOfManpower ,  ";
			sql+=" 'Production\\Moulding\\MHI-B2-1 - Moulding Horizontal Injection - B2-1' as FunctionalLocation,  ";
			sql+=" 'Step 1' as MajorSteps, '07:00:00 AM' as DateTime, 'ATPL' as PreparedBy , ";
			sql+=" 'ATPL' as ApprovedBy FROM dual union SELECT 'Trimming'as Process , 'Travel' as TypeOfManpower ,";
			sql+=" 'Production\\Moulding\\MHI-B2-3 - Moulding Horizontal Injection - B2-3' as FunctionalLocation, ";
			sql+=" 'Step 2' as MajorSteps, '08:00:00 AM' as DateTime, 'ATPL' as PreparedBy , 'ATPL' as ApprovedBy FROM dual ) d   ";
			CommonMessage.debugMsg("sql "+sql);
			List<String[]> dataList =  dbActionTemplate.getDataList(sql);
			return dataList;	
			
		}*/
		public List<String[]> getStdWoShMainGrid() throws Exception  {
			String sql="select '','','','','','','','' FROM DUAL";
			       sql+=" UNION ALL select '','','','','','','','' FROM DUAL";
			       sql+=" UNION ALL select '','','','','','','','' FROM DUAL";
			       sql+=" UNION ALL select '','','','','','','','' FROM DUAL";
			       sql+=" UNION ALL select '','','','','','','','' FROM DUAL";
			       sql+=" UNION ALL select '','','','','','','','' FROM DUAL";
			       sql+=" UNION ALL select '','','','','','','','' FROM DUAL";
			       sql+=" UNION ALL select '','','','','','','','' FROM DUAL";
			       sql+=" UNION ALL select '','','','','','','','' FROM DUAL";
			       sql+=" UNION ALL select '','','','','','','','' FROM DUAL";
			       sql+=" UNION ALL select '','','','','','','','' FROM DUAL";
			CommonMessage.debugMsg("sql "+sql);
			List<String[]> dataList =  dbActionTemplate.getDataList(sql);
			return dataList;	
			
		}

		@Override
		public List<String[]> getAllworkshtdtl(CommonFilter commonFilter)
				throws Exception {
			
			try
			{
				String KEYID = commonFilter.getKey() ;
				List<String> paramValues = new ArrayList<String>();		
				String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				CommonMessage.debugMsg("test to............");
				condParms=condParms+";KEYID="+KEYID+";";
				paramValues.add(condParms);
				paramValues.add(commonParams);
				
				List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST.STD_FN_STDWORKSHEETMST", paramValues);
				
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

		@Override
		public List<String[]> getAllMaster(CommonFilter commonFilter)    //master
				throws Exception {

			try
			{
				List<String> paramValues = new ArrayList<String>();		
				String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				CommonMessage.debugMsg("test to............");
				
				if(UIUtils.isValidKeyId(commonFilter.getFlid())){
					condParms +="FLID="+commonFilter.getFlid()+";";
				}
				
				CommonMessage.debugMsg(" CommonFunctions :: Dao Impl :: "+commonFilter.getFlid());
				
				paramValues.add(condParms);
				paramValues.add(commonParams);
				
				//List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("STD_FN_STDWORKSHEETGRID", paramValues);
				
				List<String[]> dataList =  fnCallApi.callFunction("STD_FN_STDWORKSHEETGRID_SB", paramValues,3,true);
				
				
				
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

		@Override
		public StdTlStdworksheetmst create(
				StdTlStdworksheetmst newStdTlStdworksheetmst) throws Exception {
			
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
			
			StdTlStdworksheetmstSql stdTlStdworksheetmstSql = new StdTlStdworksheetmstSql(); 
			StdTlStdworksheetdtlSql stdTlStdworksheetdtlSql=new StdTlStdworksheetdtlSql();
			
			try{
			    newStdTlStdworksheetmst.setStwsKeyid(dbActionTemplate.getSequenceNumber(StdTlStdworksheetmstSql.TBL_STD_TL_STDWORKSHEETMST, 10, "STWS", "", "")); // set the sequnce number 
				sqls.add(StdTlStdworksheetmstSql.getInsertSql(stdTlStdworksheetmstSql.getStwsDbFields(), newStdTlStdworksheetmst.getSaveArray())); // add insert sql for master table
				CommonMessage.debugMsg("daoimpl");
				CommonMessage.debugMsg("78686867867");
				List<StdTlStdworksheetdtl> newStdTlStdworksheetdtl =newStdTlStdworksheetmst.getstdTlStdworksheetdtl();
				
				//List<StdTlStdworksheetdtl> worksheetdtl= newStdTlStdworksheetmst.getstdTlStdworksheetdtl();
				if( newStdTlStdworksheetdtl != null )
				{	
					CommonMessage.debugMsg("daoimpl23");
					for( StdTlStdworksheetdtl stdTlStdworksheetdtl : newStdTlStdworksheetdtl)
					{
						CommonMessage.debugMsg("daoimpl44");
					
						stdTlStdworksheetdtl.setStwdKeyid(dbActionTemplate.getSequenceNumber(StdTlStdworksheetdtlSql.TBL_STD_TL_STDWORKSHEETDTL,10, "STWD", "", "")); // set the sequnce number
						stdTlStdworksheetdtl.setStwdStwsKeyid(newStdTlStdworksheetmst.getStwsKeyid());
					   sqls.add(StdTlStdworksheetdtlSql.getInsertSql(stdTlStdworksheetdtlSql.getStwdDbFields(), stdTlStdworksheetdtl.getSaveArray())); // add insert sql for master table
				}
				}
				dbActionTemplate.executeStatements(sqls); // execute the block of sqls
				
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return newStdTlStdworksheetmst;
		}

		@Override
		public StdTlStdworksheetmst selectmaster(String keyid) throws Exception {
			
			try{
				StdTlStdworksheetmstSql stdTlStdworksheetmstSql = new StdTlStdworksheetmstSql(); 
				StdTlStdworksheetmst newStdTlStdworksheetmst=new StdTlStdworksheetmst();//model
				String sql = stdTlStdworksheetmstSql.selectmaster();
				Object [] args =  new Object [] {keyid };
				CommonMessage.debugMsg("sql:::::"+sql);
				CommonMessage.debugMsg("keyid:::::"+keyid);
				newStdTlStdworksheetmst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		    	//CommonMessage.debugMsg(sql);
				//CommonMessage.debugMsg(keyId);
				return newStdTlStdworksheetmst;
	          }catch(Exception e){
				
			}
			return null;
		}

		@Override
		public StdTlStdworksheetmst update(
				StdTlStdworksheetmst newStdTlStdworksheetmst) throws Exception {
			
			List<String> sqls = new ArrayList<String>();
			StdTlStdworksheetmstSql stdTlStdworksheetmstSql = new StdTlStdworksheetmstSql(); 
			StdTlStdworksheetdtlSql stdTlStdworksheetdtlSql=new StdTlStdworksheetdtlSql();
			List<StdTlStdworksheetdtl> newStdTlStdworksheetdtl =newStdTlStdworksheetmst.getstdTlStdworksheetdtl();
			try {

				sqls.add(StdTlStdworksheetmstSql.getUpdateSql(stdTlStdworksheetmstSql.getStwsDbFields(), newStdTlStdworksheetmst.getSaveArray()));
				//popSqlsForQtmTlKnowwhydtl(sqls, newStdTlStdworksheetmst.getqtmTlKnowwhydtl(), qtmTlKnowwhymst.getKnwmKeyid());
				if( newStdTlStdworksheetdtl != null && newStdTlStdworksheetdtl.size()> 0 )
				{	
					CommonMessage.debugMsg("daoimpl23");
					for( StdTlStdworksheetdtl stdTlStdworksheetdtl : newStdTlStdworksheetdtl)
					{
						if(UIUtils.isValidKeyId(stdTlStdworksheetdtl.getStwdStwsKeyid()))
						{
							stdTlStdworksheetdtl.setStwdStwsKeyid(newStdTlStdworksheetmst.getStwsKeyid());
							sqls.add(StdTlStdworksheetdtlSql.getUpdateSql(stdTlStdworksheetdtlSql.getStwdDbFields(), stdTlStdworksheetdtl.getSaveArray()));	
						}
						else
						{   stdTlStdworksheetdtl.setStwdStwsKeyid(newStdTlStdworksheetmst.getStwsKeyid());
						    stdTlStdworksheetdtl.setStwdKeyid(dbActionTemplate.getSequenceNumber(StdTlStdworksheetdtlSql.TBL_STD_TL_STDWORKSHEETDTL, 10, "STWD", "", "")); // set the sequnce number
							sqls.add(StdTlStdworksheetdtlSql.getInsertSql(stdTlStdworksheetdtlSql.getStwdDbFields(), stdTlStdworksheetdtl.getSaveArray()));
							
						}
				
				}
				}
				dbActionTemplate.executeStatements(sqls);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(e.getMessage());
			}
			
			return newStdTlStdworksheetmst;
		}

		@Override
		public StdTlStdworksheetmst delete(
				StdTlStdworksheetmst newStdTlStdworksheetmst) throws Exception {
			List<String> sqls = new ArrayList<String>();
			StdTlStdworksheetmstSql stdTlStdworksheetmstSql = new StdTlStdworksheetmstSql(); 
			StdTlStdworksheetdtlSql stdTlStdworksheetdtlSql=new StdTlStdworksheetdtlSql();
			try {
				sqls.add("DELETE FROM "+stdTlStdworksheetdtlSql.TBL_STD_TL_STDWORKSHEETDTL+" WHERE STWD_STWS_KEYID ='"+newStdTlStdworksheetmst.getStwsKeyid()+"'");
				sqls.add(StdTlStdworksheetmstSql.getDeleteSql(stdTlStdworksheetmstSql.getStwsDbFields(), newStdTlStdworksheetmst.getSaveArray()));

				dbActionTemplate.executeStatements(sqls);
				
			}catch( Exception e){

			}
			return newStdTlStdworksheetmst;
		}

		@Override
		public Workbook getStdWorkExcel(JSONObject colmodel, String format,
				CommonFilter commonFilter) throws Exception {
			
			ResultSet rs = null;
			 try
			 {
				 
					rs =   getWorkSheetResultSet(commonFilter);
					CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
					
					CommonMessage.debugMsg("daoimpl");
					ExcelUtils excelUtils = new ExcelUtils(colmodel);
					List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
					
					XLConditionalFormats condFormat = new XLConditionalFormats();
					condFormat.setFontColor(new RGB(254,0,0)); //red font
					condFormat.setFontName("Wingdings");
					condFormat.setFontHeightPoint((short)104);
					condFormat.setFontBoldWeight((short)20);
					condFormat.setFromCol(103);
					condFormat.setToCol(-10);
					condFormat.setOperator(ComparisonOperator.EQUAL);
					condFormat.setCondValue( (char)252+""); //Tick
					condFormat.setIdentfier("tick");
					condFormats.add(condFormat);
					excelUtils.setCondFormats(condFormats);
					CommonMessage.debugMsg("daoimpl22222");
					return excelUtils.writeToExcel(rs,format,3,0,0 );
					
					
				   }finally{
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				   }  
			}

		private ResultSet getWorkSheetResultSet(CommonFilter commonFilter) throws Exception {
			// TODO Auto-generated method stub
			
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			if(UIUtils.isValidKeyId(commonFilter.getFlid())){
				condParms +="FLID="+commonFilter.getFlid()+";";
			}
			paramValues.add(condParms);
			paramValues.add(commonParams);
			return dbActionTemplate.NewdbFunctionCall2("STD_FN_STDWORKSHEETGRID_sb", paramValues);
		   
		}

		private List<String> getFilterParamValues(CommonFilter commonFilter) {
			
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			return paramValues;
	}

		@Override
		public void DeleteStdRow(String keyid) throws Exception {
			try
			{
				StdTlStdworksheetdtlSql stdTlStdworksheetdtlSql=new StdTlStdworksheetdtlSql();
				List<String > sqls = new ArrayList<String>();
				//sqls.add(GenTlMomKpiLinkSql.DeleteMomRow(keyid));
				sqls.add( stdTlStdworksheetdtlSql.DeleteStdRow(keyid));
		  
				dbActionTemplate.executeStatements(sqls);
			}
			catch( Exception e){
				throw new Exception(e.getMessage());
			}
		}

		@Override
		public void DeleteStdWorkRow(
				List<StdTlStdworksheetdtl> lstStdTlStdworksheetdtl)
				throws Exception {
			
			try
			{
				List<String > sqls = new ArrayList<String>();
				StdTlStdworksheetdtlSql stdTlStdworksheetdtlSql=new StdTlStdworksheetdtlSql();
				for( StdTlStdworksheetdtl stdTlStdworksheetdtl : lstStdTlStdworksheetdtl)
				{
					sqls.add( stdTlStdworksheetdtlSql.DeleteStdRow(stdTlStdworksheetdtl.getStwdKeyid()));	
				}
				dbActionTemplate.executeStatements(sqls);
			}
				
			catch( Exception e){
				throw new Exception(e.getMessage());
			}
	

		}

		@Override
		public List<String[]> FillControlData(String keyid) throws Exception {
			// TODO Auto-generated method stub
			
			String sql = StdTlStdworksheetmstSql.selectData(keyid);
			CommonMessage.debugMsg("sql  "+sql);
			List<String []> gridData = dbActionTemplate.getDataList(sql);
			return gridData;
		}
}

