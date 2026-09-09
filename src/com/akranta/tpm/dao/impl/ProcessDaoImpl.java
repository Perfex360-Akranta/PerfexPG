package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.ProcessDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlCriticalprocessSql;
import com.akranta.tpm.dao.sql.QtmTlCriticalprocessdtlSql;
import com.akranta.tpm.dao.sql.QtmTlCriticalprocessmstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlCriticalprocessdtl;
import com.akranta.tpm.model.QtmTlCriticalprocessmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.CriticalProcessServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;

public class ProcessDaoImpl implements ProcessDao {

	private DBActionTemplate dbActionTemplate;
	private CriticalProcessServiceApi criticalprocessapi;
	FunctionCallApi fnCallApi;
	QtmTlCriticalprocessdtlSql qtmTlCriticalprocessdtlSql =null;
	QtmTlCriticalprocessmstSql qtmTlCriticalprocessmstSql = null;

	public ProcessDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		this.qtmTlCriticalprocessmstSql = new QtmTlCriticalprocessmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		this.qtmTlCriticalprocessdtlSql = new QtmTlCriticalprocessdtlSql();
	}
	
	public void ProcessDaoImplJwt(String JwtToken) 
	{
		try{
			criticalprocessapi = new CriticalProcessServiceApi(JwtToken);
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
		// TODO Auto-generated constructor stub


	@Override
	public List<String[]> getAllProcess(CommonFilter commonFilter)
			throws Exception {
		List<String[]> dataList=new ArrayList<String[]>();
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			String FNLNID=commonFilter.getFlid();
			String DATE=commonFilter.getDteend();
			String KEYID=commonFilter.getKey();
			String Parameter=commonFilter.getParamCode();
			
			//String UpstreamDate  = DATE.substring(0, 11);
			
			//CommonMessage.debugMsg(" KEYID :: "+KEYID+" UpstreamDate :: "+UpstreamDate);
			
			if(UIUtils.isValidKeyId(DATE))
			   condParms+="DATE="+DATE+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getFlid()))
			   condParms+="FLID="+FNLNID+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getParamCode()))
			   condParms+="PARAMETER="+Parameter+";";
			
			if(UIUtils.isValidKeyId(KEYID))
			   condParms+="KEYID="+KEYID+";";
				
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			//dataList =  dbActionTemplate.processFunctionCalls("QTM_PC_QUALITY.QTM_FN_CRITICALPROCESSFORMGRID", paramValues);
			dataList =  fnCallApi.callFunction("QTM_FN_CRITICALPROCESSFORMGRID_SB", paramValues,3,false);
			
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
				 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return dataList; 
	}

	
	@Override
	public List<String[]> getFillMainGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		List<String[]> dataList=new ArrayList<String[]>();
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			CommonMessage.debugMsg("test to............");
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			//dataList =  dbActionTemplate.processFunctionCalls("QTM_PC_QUALITY.QTM_FN_CRITICALPROCESSMAINGRID", paramValues);
			
			dataList =  fnCallApi.callFunction("QTM_FN_CRITICALPROCESSMAINGRID_SB", paramValues,3,false);
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
				 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return dataList; 
	}

	
	public QtmTlCriticalprocessmst create(QtmTlCriticalprocessmst qtmTlCriticalprocessmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		
				
		QtmTlCriticalprocessdtl qtmTlCriticalprocessdtl = qtmTlCriticalprocessmst.getQtmTlCriticalprocessdtl();
		
		qtmTlCriticalprocessmst.setCrppKeyid(dbActionTemplate.getSequenceNumber(QtmTlCriticalprocessmstSql.TBL_QTM_TL_CRITICALPROCESSMST,10,"CRPP",null,null )); // set the sequnce number 
		sqls.add(QtmTlCriticalprocessmstSql.getInsertSql(qtmTlCriticalprocessmstSql.getCrppDbFields(), qtmTlCriticalprocessmst.getSaveArray())); // add insert sql for master table
		if( qtmTlCriticalprocessdtl != null){
			qtmTlCriticalprocessdtl.setCrpdKeyid(dbActionTemplate.getSequenceNumber(QtmTlCriticalprocessdtlSql.TBL_QTM_TL_CRITICALPROCESSDTL,10,"CRPD",null,null )); // set the sequnce number
			qtmTlCriticalprocessdtl.setCrpdCrppKeyid(qtmTlCriticalprocessmst.getCrppKeyid());
			sqls.add(QtmTlCriticalprocessdtlSql.getInsertSql(qtmTlCriticalprocessdtlSql.getCrpdDbFields(), qtmTlCriticalprocessdtl.getSaveArray())); // add insert sql for master table
		}
		
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return qtmTlCriticalprocessmst;
	}
	
	public QtmTlCriticalprocessmst update(QtmTlCriticalprocessmst qtmTlCriticalprocessmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		
		QtmTlCriticalprocessdtl qtmTlCriticalprocessdtl = qtmTlCriticalprocessmst.getQtmTlCriticalprocessdtl();
		qtmTlCriticalprocessdtl.setCrpdCrppKeyid(qtmTlCriticalprocessmst.getCrppKeyid());
		sqls.add(QtmTlCriticalprocessmstSql.getUpdateSql(qtmTlCriticalprocessmstSql.getCrppDbFields(), qtmTlCriticalprocessmst.getSaveArray()));
		if( qtmTlCriticalprocessdtl != null){
			if( qtmTlCriticalprocessdtl.getCrpdKeyid() == null){
				qtmTlCriticalprocessdtl.setCrpdKeyid(dbActionTemplate.getSequenceNumber(QtmTlCriticalprocessdtlSql.TBL_QTM_TL_CRITICALPROCESSDTL,10,"CRPD",null,null )); // set the sequnce number 
				sqls.add(QtmTlCriticalprocessdtlSql.getInsertSql(qtmTlCriticalprocessdtlSql.getCrpdDbFields(), qtmTlCriticalprocessdtl.getSaveArray())); // add insert sql for master table
			}
			else{
				sqls.add(QtmTlCriticalprocessdtlSql.getUpdateSql(qtmTlCriticalprocessdtlSql.getCrpdDbFields(), qtmTlCriticalprocessdtl.getSaveArray())); // add insert sql for master table
			}
		}
		
		dbActionTemplate.executeStatements(sqls);
	
		
		return qtmTlCriticalprocessmst;
	}
	
	@Override
	public QtmTlCriticalprocessmst select(String crppKeyid) throws Exception {
		// TODO Auto-generated method stub
		 String sql = QtmTlCriticalprocessmstSql.getSelectSql();
		 CommonMessage.debugMsg(" sql "  + sql + " crppKeyid " + crppKeyid);
		 Object [] args = {crppKeyid}; 
		 QtmTlCriticalprocessmst qtmTlCriticalprocessmst = new QtmTlCriticalprocessmst();
		 
		 qtmTlCriticalprocessmst.setSaveArray(dbActionTemplate.getDataArr(sql, args)) ;
		 return qtmTlCriticalprocessmst;
	}
	
	public void delete(String masterId)
			throws Exception {

		List<String> sqls = new ArrayList<String>();

		sqls.add(QtmTlCriticalprocessmstSql.getDeleteAllDtlSql(masterId));
		sqls.add(QtmTlCriticalprocessmstSql.getDeleteSql(qtmTlCriticalprocessmstSql.getCrppDbFields(),masterId));
		
		
		dbActionTemplate.executeStatements(sqls);
			
		
	}
	
	public void deleteDtl(String detailId)
	throws Exception {

		List<String> sqls = new ArrayList<String>();
		
		
		sqls.add(QtmTlCriticalprocessmstSql.getDeleteDtlSql(detailId));
		
		dbActionTemplate.executeStatements(sqls);
			
		
	}

	
	@Override
	public Workbook getCriticalProcessExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
			
	       ResultSet rs = null;
		   try{
			
			rs =   getCriticalProcessResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet getCriticalProcessResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		paramValues.add(condParms);
		paramValues.add(commonParams);
        return dbActionTemplate.dbFunctionCall("QTM_FN_CRITICALPROCESSMAINGRID", paramValues);
	
	}

	
	

	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = GenTlCriticalprocessSql.selectData(keyid,"");
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}

	@Override
	public Workbook getprocessExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		 try
		 {
				rs =   getprocessExcel(commonFilter);
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
				return excelUtils.writeToExcel(rs,format,2,0,0 );
				
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }  
		}

	private ResultSet getprocessExcel(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg("result");
		return dbActionTemplate.NewdbFunctionCall2("QTM_FN_CRITICALPROCESSMAINGRID", paramValues);
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}



	
}


