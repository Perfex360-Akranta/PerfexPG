package com.akranta.tpm.dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlTrainingneedmstDao;
import com.akranta.tpm.dao.sql.EntTlTrainingneedmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTrainingneedmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class EntTlTrainingneedmstDaoImpl implements EntTlTrainingneedmstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlTrainingneedmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<EntTlTrainingneedmst> create(List<EntTlTrainingneedmst> trainingNeedGridList1,List<EntTlTrainingneedmst> trainingKeyid) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTrainingneedmstSql entTlTrainingNeedmstSql = new EntTlTrainingneedmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		CommonMessage.debugMsg("Dao Impl 33 ::: ");		
		try{   
			for(EntTlTrainingneedmst entTlkeyidneedmst:trainingKeyid){
				sqls.add(EntTlTrainingneedmstSql.getDeleteSql(entTlTrainingNeedmstSql.getTnimDbFields(), entTlkeyidneedmst.getSaveArray())); // add insert sql for master table
			}
			for(EntTlTrainingneedmst entTlTrainingneedmst:trainingNeedGridList1){   
				entTlTrainingneedmst.setTnimKeyid(dbActionTemplate.getSequenceNumber(EntTlTrainingneedmstSql.TBL_ENT_TL_TRAININGNEEDMST,10, "TNIM", "", "Y")); // set the sequnce number 
				sqls.add(EntTlTrainingneedmstSql.getInsertSql(entTlTrainingNeedmstSql.getTnimDbFields(), entTlTrainingneedmst.getSaveArray())); // add insert sql for master table
		    }
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls			
		}catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
			return trainingNeedGridList1;
	}
	
	public EntTlTrainingneedmst update(EntTlTrainingneedmst entTlTrainingNeedindenfymst)	throws Exception { 
		
			List<String> sqls = new ArrayList<String>();
			EntTlTrainingneedmstSql entTlTrainingNeedindenfymstSql = new EntTlTrainingneedmstSql();
			try {
				sqls.add(EntTlTrainingneedmstSql.getUpdateSql(entTlTrainingNeedindenfymstSql.getTnimDbFields(), entTlTrainingNeedindenfymst.getSaveArray()));			
				dbActionTemplate.executeStatements(sqls);			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(e.getMessage());
			}		
			return entTlTrainingNeedindenfymst;
	}
	
	public EntTlTrainingneedmst delete(EntTlTrainingneedmst entTlTrainingNeedindenfymst)throws Exception {
			List<String> sqls = new ArrayList<String>();
			EntTlTrainingneedmstSql entTlTrainingNeedindenfymstSql = new EntTlTrainingneedmstSql();
			try {			
				sqls.add(entTlTrainingNeedindenfymstSql.getDeleteSql(entTlTrainingNeedindenfymstSql.getTnimDbFields(), entTlTrainingNeedindenfymst.getSaveArray()));
				dbActionTemplate.executeStatements(sqls);			
			}catch( Exception e){
				throw new Exception(e.getMessage());
			}
			return entTlTrainingNeedindenfymst;
	}

	@Override
	public List<String[]> getTopic(String type, CommonFilter commonFilter,String flid, String date) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("TrainingGrid::::: ");
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			CommonMessage.debugMsg("test to getTrainingGrid.....");		
		    paramValues.add(type);
		    paramValues.add(flid);
			if(UIUtils.isValidKeyId(date)){
			    paramValues.add(date);
			}
			else{
			     paramValues.add(" "); 
			}
			List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGNEEDINENTIFY", paramValues);
			CommonMessage.debugMsg("getViewClick::: "+commonFilter.getViewClick() );
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			    if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}   
			return dataList; 
	}

	@Override
	public List<String[]> getTRneedMainGrid(CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
			CommonMessage.debugMsg("TrainingGrid::::: ");
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter1);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); 
			CommonMessage.debugMsg("test to getTrainingGrid....."+FilterCondSql.makeGridFilterCond(commonFilter1.getGridFilter()));		
		    paramValues.add(condParms);
		    paramValues.add(commonParams);
			List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_TRAINING_NEEDMAINGRID", paramValues);
			if( commonFilter1.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(isInteger ){
					commonFilter1.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	@Override
	public Workbook TrNeedExcel(CommonFilter commonFilter1,JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getTrNeedReport(commonFilter1);
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

	private ResultSet getTrNeedReport(CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter1);			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_TRAINING_NEEDMAINGRID", paramValues);
	}

	@Override
	public List<EntTlTrainingneedmst> deleteTrneed(List<EntTlTrainingneedmst> trainingKeyid) throws Exception {
		// TODO Auto-generated method stub

			List<String> sqls = new ArrayList<String>();
			EntTlTrainingneedmstSql entTlTrainingNeedindenfymstSql = new EntTlTrainingneedmstSql();
			try {	
				for(EntTlTrainingneedmst entTlkeyidneedmst:trainingKeyid){
					sqls.add(entTlTrainingNeedindenfymstSql.getDeleteSql(entTlTrainingNeedindenfymstSql.getTnimDbFields(), entTlkeyidneedmst.getSaveArray()));
				}
				dbActionTemplate.executeStatements(sqls);
				
			}catch( Exception e){
				throw new Exception(e.getMessage());
			}
			return trainingKeyid;
	}

	@Override
	public List<String[]> getTrneedReport(CommonFilter commonFilter1)throws Exception {
		// TODO Auto-generated method stub
			CommonMessage.debugMsg("TrainingGrid::::: ");
			List<String> paramValues = new ArrayList<String>();	
			String TypeID="U";
			String condParms =FilterCondSql.getETRelatedStr(commonFilter1);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); 
			CommonMessage.debugMsg("Flid Get Common Filtert "+commonFilter1.getFlid());
			CommonMessage.debugMsg("test to getTrainingGrid....."+FilterCondSql.makeGridFilterCond(commonFilter1.getGridFilter()));
			condParms +="Type="+TypeID;
		    paramValues.add(condParms);
		    paramValues.add(commonParams);
			List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGNEEDREPORT", paramValues);
			if( commonFilter1.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(isInteger ){
					commonFilter1.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}

	@Override
	public Workbook TrNeedReportExcel(CommonFilter commonFilter1, JSONObject colmodel, String format) throws Exception {
		ResultSet rs = null;
		   try{		
			rs =   getTrainingNeedReport(commonFilter1);
			CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();			
			XLConditionalFormats condFormat = new XLConditionalFormats();			
			condFormat.setFontColor(new RGB(255,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(2);
			condFormat.setToCol(colmodel.length());
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("1"); //Tick			
			condFormat.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
			condFormats.add(condFormat);	
			
			XLConditionalFormats condFormatEmpty = new XLConditionalFormats();
			condFormatEmpty.setFontColor(new RGB(254,0,0)); //red font
			condFormatEmpty.setFontName("Wingdings");
			condFormatEmpty.setFontHeightPoint((short)14);
			condFormatEmpty.setFontBoldWeight((short)20);
			condFormatEmpty.setFromCol(2);
			condFormatEmpty.setToCol(colmodel.length());
			condFormatEmpty.setOperator(ComparisonOperator.EQUAL);
			condFormatEmpty.setCondValue("0"); //NULL		
			condFormatEmpty.setSymbolStr("");
			condFormats.add(condFormatEmpty);			
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format,3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  

	}
	private ResultSet getTrainingNeedReport(CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
			List<String> paramValues = new ArrayList<String>();
			String TypeID="U";
			String condParms =FilterCondSql.getETRelatedStr(commonFilter1);			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			condParms +="Type="+TypeID;
			paramValues.add(condParms);
			paramValues.add(commonParams);
			return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGNEEDREPORT", paramValues);
	}
}

