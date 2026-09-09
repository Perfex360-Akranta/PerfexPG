package com.akranta.tpm.dao.impl;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.dao.SheTlRiskassessmentmstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlFnlnrolemapSql;
import com.akranta.tpm.dao.sql.SheTlDeriskdtlSql;
import com.akranta.tpm.dao.sql.SheTlDeriskmstSql;
import com.akranta.tpm.dao.sql.SheTlRiskassessmentdtlSql;
import com.akranta.tpm.dao.sql.SheTlRiskassessmentmstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.SheTlDeriskdtl;
import com.akranta.tpm.model.SheTlDeriskmst;
import com.akranta.tpm.model.SheTlRiskassessmentdtl;
import com.akranta.tpm.model.SheTlRiskassessmentmst;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class SheTlRiskassessmentmstDaoImpl implements SheTlRiskassessmentmstDao {
	private DBActionTemplate dbActionTemplate; 
	private SheTlRiskassessmentmstSql sheTlRiskassessmentmstSql;
	
	public SheTlRiskassessmentmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		 sheTlRiskassessmentmstSql=new SheTlRiskassessmentmstSql ();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public SheTlRiskassessmentmst create(SheTlRiskassessmentmst sheTlRiskassessmentmst) 	
		throws ValidationExceptions,BusinessApplicationExceptions,Exception { 

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SheTlRiskassessmentmstSql sheTlRiskassessmentmstSql = new SheTlRiskassessmentmstSql();
		SheTlRiskassessmentdtlSql sheTlRiskassessmentdtlSql=new SheTlRiskassessmentdtlSql();
		SheTlRiskassessmentdtl sheTlRiskassessmentdtl= new SheTlRiskassessmentdtl();
		try{
			
			String elementId = sheTlRiskassessmentmst.getElementid();
		 	String location = null;
		 	String seqIdentfi =com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,SheTlRiskassessmentmstSql.TBL_SHE_TL_RISKASSESSMENTMST);
			
			sheTlRiskassessmentmst.setRasmKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,10,"RA", "", "")); // set the sequnce number 
			//sheTlRiskassessmentmst.setRasmKeyid(dbActionTemplate.getSequenceNumber(SheTlRiskassessmentmstSql.TBL_SHE_TL_RISKASSESSMENTMST,10,"RAM", "", "")); // set the sequnce number 
			sqls.add(SheTlRiskassessmentmstSql.getInsertSql(sheTlRiskassessmentmstSql.getRasmDbFields(), sheTlRiskassessmentmst.getSaveArray())); // add insert sql for master table			
			if (sheTlRiskassessmentmst.getRiskDetails()!=null){		
				List<SheTlRiskassessmentdtl> sheTlRiskassessmentdtlList= sheTlRiskassessmentmst.getRiskDetails();
				for(int i=0 ;i<=sheTlRiskassessmentdtlList.size()-1;i++){				
					sheTlRiskassessmentdtl=sheTlRiskassessmentdtlList.get(i);
					if (!CommonFunctions.isValidKeyId(sheTlRiskassessmentdtl.getRasdKeyid())){
						sheTlRiskassessmentdtl.setRasdRasmKeyid(sheTlRiskassessmentmst.getRasmKeyid());
						sheTlRiskassessmentdtl.setRasdKeyid(dbActionTemplate.getSequenceNumber(SheTlRiskassessmentdtlSql.TBL_SHE_TL_RISKASSESSMENTDTL,10,"RAD", "", "")); // set the sequnce number
						sqls.add(sheTlRiskassessmentdtlSql.getInsertSql(sheTlRiskassessmentdtlSql.getRasdDbFields(), sheTlRiskassessmentdtl.getSaveArray())); // add insert sql for master table					
					}				
				}
			}
			dbActionTemplate.executeStatements(sqls); 
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return sheTlRiskassessmentmst;
	}
	
	public SheTlRiskassessmentmst update(SheTlRiskassessmentmst sheTlRiskassessmentmst)	
		throws ValidationExceptions,BusinessApplicationExceptions,Exception { 
		
		List<String> sqls = new ArrayList<String>();
		SheTlRiskassessmentmstSql sheTlRiskassessmentmstSql = new SheTlRiskassessmentmstSql();
		SheTlRiskassessmentdtlSql sheTlRiskassessmentdtlSql=new SheTlRiskassessmentdtlSql();
		SheTlRiskassessmentdtl sheTlRiskassessmentdtl= new SheTlRiskassessmentdtl();
		try {
			sqls.add(SheTlRiskassessmentmstSql.getUpdateSql(sheTlRiskassessmentmstSql.getRasmDbFields(), sheTlRiskassessmentmst.getSaveArray()));			
			if (sheTlRiskassessmentmst.getRiskDetails()!=null){
				List<SheTlRiskassessmentdtl> sheTlRiskassessmentdtlList= sheTlRiskassessmentmst.getRiskDetails();				
				for(int i=0 ;i<=sheTlRiskassessmentdtlList.size()-1;i++){				
					sheTlRiskassessmentdtl=sheTlRiskassessmentdtlList.get(i);
					sheTlRiskassessmentdtl.setRasdRasmKeyid(sheTlRiskassessmentmst.getRasmKeyid());
					if (!CommonFunctions.isValidKeyId(sheTlRiskassessmentdtl.getRasdKeyid())){					
						sheTlRiskassessmentdtl.setRasdKeyid(dbActionTemplate.getSequenceNumber(SheTlRiskassessmentdtlSql.TBL_SHE_TL_RISKASSESSMENTDTL,10,"RAD", "", "")); // set the sequnce number
						sqls.add(sheTlRiskassessmentdtlSql.getInsertSql(sheTlRiskassessmentdtlSql.getRasdDbFields(), sheTlRiskassessmentdtl.getSaveArray())); // add insert sql for master table					
					}
					else{
						sqls.add(sheTlRiskassessmentdtlSql.getUpdateSql(sheTlRiskassessmentdtlSql.getRasdDbFields(), sheTlRiskassessmentdtl.getSaveArray())); // add insert sql for master table					
					}	
				}
			}
			dbActionTemplate.executeStatements(sqls);	
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return sheTlRiskassessmentmst;
	}
	
	public SheTlRiskassessmentmst delete(SheTlRiskassessmentmst sheTlRiskassessmentmst)
		throws ValidationExceptions,BusinessApplicationExceptions,Exception {

		List<String> sqls = new ArrayList<String>();
		SheTlRiskassessmentmstSql sheTlRiskassessmentmstSql = new SheTlRiskassessmentmstSql();
		try {	
			sqls.add(sheTlRiskassessmentmstSql.getDeleteDtlsSql(sheTlRiskassessmentmstSql.getRasmDbFields(), sheTlRiskassessmentmst.getSaveArray()));
			sqls.add(sheTlRiskassessmentmstSql.getDeleteSql(sheTlRiskassessmentmstSql.getRasmDbFields(), sheTlRiskassessmentmst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		}	
		catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage()+",FK_DRAD_RASD_KEYID");
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return sheTlRiskassessmentmst;
	}

	@Override
	public SheTlRiskassessmentmst select(String keyid) throws Exception {
		SheTlRiskassessmentmst  newSheTlRiskassessmentmst =new 	SheTlRiskassessmentmst();
		String sql = SheTlRiskassessmentmstSql.getSelectSql();
		CommonFunctions.debugMsg(keyid+" sql   "+sql);
		Object args [] = new Object [] {keyid};
		newSheTlRiskassessmentmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return newSheTlRiskassessmentmst;
	}

	@Override
	public Workbook getRiskExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		
		ResultSet rs = null;
	   try{
		
		rs =   getRiskReportResultSet(commonFilter);
		ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
		/*List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
		
		(XLConditionalFormats condFormat = new XLConditionalFormats();
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
		excelUtils.setCondFormats(condFormats);*/
		return excelUtils.writeToExcel(rs,format, 2,0,0 );
		
	   }
	   finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
	}
	
	private ResultSet getRiskReportResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);		
		return dbActionTemplate.NewdbFunctionCall2("HSE_FN_RISKASSESSMENTMSTLIST", paramValues);
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
	public List<String[]> getRiskList(CommonFilter commonFilter)
			throws Exception {		
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			dataList =  dbActionTemplate.processFunctionCalls("HSE_FN_RISKASSESSMENTMSTLIST", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public List<String[]> getRiskDtlsList(CommonFilter commonFilter) throws Exception {		
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = "";
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms="RASM_KEYID="+commonFilter.getKey()+";";
			}	
			commonFilter.setFromRow("1");
			commonFilter.setToRow("100");
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			dataList =  dbActionTemplate.processFunctionCalls("HSE_FN_RISKASSESSMENTDTLSLIST", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public String getRiskLevel(String riskVal)
			throws Exception {
		// TODO Auto-generated method stub
		String sql=null; 
		String RiskLevel="";
		SheTlRiskassessmentmstSql sheTlRiskassessmentmstSql = new SheTlRiskassessmentmstSql();
		try{		
			sql=sheTlRiskassessmentmstSql.getRiskLevelSql(riskVal);
			RiskLevel=dbActionTemplate.getSingleValue(sql); 
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return RiskLevel;
	}

	@Override
	public List<String[]> getDeRiskList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_DERISKMSTLIST", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if( isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public List<String[]> getDeRiskDtlsList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = "";
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms="RASM_KEYID="+commonFilter.getKey()+";";
			}			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_DERISKDTLSLIST", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if( isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public SheTlDeriskmst create(SheTlDeriskmst newSheTlDeriskmst)
		throws ValidationExceptions,BusinessApplicationExceptions,Exception { 
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SheTlDeriskmstSql sheTlDeriskmstSql = new SheTlDeriskmstSql();
		SheTlDeriskdtlSql sheTlDeriskdtlSql=new SheTlDeriskdtlSql();
		SheTlDeriskdtl sheTlDeriskdtl= new SheTlDeriskdtl();
		try{		
			newSheTlDeriskmst.setDramKeyid(dbActionTemplate.getSequenceNumber(sheTlDeriskmstSql.TBL_SHE_TL_DERISKMST,10,"DAM", "", "")); // set the sequnce number 
			sqls.add(sheTlDeriskmstSql.getInsertSql(sheTlDeriskmstSql.getDramDbFields(), newSheTlDeriskmst.getSaveArray())); // add insert sql for master table			
					
			List<SheTlDeriskdtl> sheTlDeriskdtlList= newSheTlDeriskmst.getDeRiskDetails();
			for(int i=0 ;i<=sheTlDeriskdtlList.size()-1;i++){				
				sheTlDeriskdtl=sheTlDeriskdtlList.get(i);
				sheTlDeriskdtl.setDradDramKeyid(newSheTlDeriskmst.getDramKeyid());
				if (!CommonFunctions.isValidKeyId(sheTlDeriskdtl.getDradKeyid())){
					sheTlDeriskdtl.setDradKeyid(dbActionTemplate.getSequenceNumber(sheTlDeriskdtlSql.TBL_SHE_TL_DERISKDTL,10,"DAD", "", "")); // set the sequnce number
					sqls.add(sheTlDeriskdtlSql.getInsertSql(sheTlDeriskdtlSql.getDradDbFields(), sheTlDeriskdtl.getSaveArray())); // add insert sql for master table					
				}				
			}
			dbActionTemplate.executeStatements(sqls); 
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newSheTlDeriskmst;
	}

	@Override
	public SheTlDeriskmst update(SheTlDeriskmst newSheTlDeriskmst)
		throws ValidationExceptions,BusinessApplicationExceptions,Exception { 
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SheTlDeriskmstSql sheTlDeriskmstSql = new SheTlDeriskmstSql();
		SheTlDeriskdtlSql sheTlDeriskdtlSql=new SheTlDeriskdtlSql();
		SheTlDeriskdtl sheTlDeriskdtl= new SheTlDeriskdtl();
		try{		
			sqls.add(sheTlDeriskmstSql.getUpdateSql(sheTlDeriskmstSql.getDramDbFields(), newSheTlDeriskmst.getSaveArray())); // add insert sql for master table			
					
			List<SheTlDeriskdtl> sheTlDeriskdtlList= newSheTlDeriskmst.getDeRiskDetails();
			//CommonFunctions.debugMsg("sheTlDeriskdtlList.size()"+sheTlDeriskdtlList.size());
			for(int i=0 ;i<=sheTlDeriskdtlList.size()-1;i++){				
				sheTlDeriskdtl=sheTlDeriskdtlList.get(i);				
				sheTlDeriskdtl.setDradDramKeyid(newSheTlDeriskmst.getDramKeyid());
				//CommonFunctions.debugMsg("getDradDramKeyid()"+sheTlDeriskdtl.getDradDramKeyid());
				if (!CommonFunctions.isValidKeyId(sheTlDeriskdtl.getDradKeyid())){					
					sheTlDeriskdtl.setDradKeyid(dbActionTemplate.getSequenceNumber(sheTlDeriskdtlSql.TBL_SHE_TL_DERISKDTL,10,"DAD", "", "")); // set the sequnce number
					sqls.add(sheTlDeriskdtlSql.getInsertSql(sheTlDeriskdtlSql.getDradDbFields(), sheTlDeriskdtl.getSaveArray())); // add insert sql for master table					
				}
				else{
					sqls.add(sheTlDeriskdtlSql.getUpdateSql(sheTlDeriskdtlSql.getDradDbFields(), sheTlDeriskdtl.getSaveArray())); // add insert sql for master table					
				}	
			}
			dbActionTemplate.executeStatements(sqls); 
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newSheTlDeriskmst;
	}

	@Override
	public SheTlDeriskmst delete(SheTlDeriskmst newSheTlDeriskmst)
		throws ValidationExceptions,BusinessApplicationExceptions,Exception { 
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		SheTlDeriskmstSql sheTlDeriskmstSql = new SheTlDeriskmstSql();
		try {	
			sqls.add(sheTlDeriskmstSql.getDeleteDtlsSql(sheTlDeriskmstSql.getDramDbFields(), newSheTlDeriskmst.getSaveArray()));
			sqls.add(sheTlDeriskmstSql.getDeleteSql(sheTlDeriskmstSql.getDramDbFields(), newSheTlDeriskmst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newSheTlDeriskmst;
	}

	@Override
	public SheTlDeriskmst selectDeRisk(String dramkeyid) throws Exception {
		// TODO Auto-generated method stub
		SheTlDeriskmst  newSheTlDeriskmst =new 	SheTlDeriskmst();
		String sql = SheTlDeriskmstSql.getSelectSql();
		CommonFunctions.debugMsg(dramkeyid+" sql   "+sql);
		Object args [] = new Object [] {dramkeyid};
		newSheTlDeriskmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return newSheTlDeriskmst;
	}	
	
	@Override
	public List<String[]> getDeRiskDtlsGridList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = "";
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms="RASM_KEYID="+commonFilter.getKey()+";";
			}			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_DERISKDTLSVIEWLIST", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if( isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public Workbook DeRiskExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getDeRiskReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);			
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
		}
		
		private ResultSet getDeRiskReportResultSet(CommonFilter commonFilter) throws Exception {
			// TODO Auto-generated method stub
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);		
			return dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.HSE_FN_DERISKMSTLIST", paramValues);
		}

		@Override
		public String getProbablityVal(String prob) throws Exception {
			// TODO Auto-generated method stub
			String sql=null; 
			String probVal="";
			SheTlRiskassessmentmstSql sheTlRiskassessmentmstSql = new SheTlRiskassessmentmstSql();
			try{		
				sql=sheTlRiskassessmentmstSql.getProbablityValSql(prob);
				CommonFunctions.debugMsg("sql....."+sql);
				probVal=dbActionTemplate.getSingleValue(sql); 
			}catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
			return probVal;
		}

		@Override
		public String getSeviorityVal(String sev) throws Exception {
			// TODO Auto-generated method stub
			String sql=null; 
			String sevVal="";
			SheTlRiskassessmentmstSql sheTlRiskassessmentmstSql = new SheTlRiskassessmentmstSql();
			try{		
				sql=sheTlRiskassessmentmstSql.getSeviorityValSql(sev);
				sevVal=dbActionTemplate.getSingleValue(sql); 
				CommonFunctions.debugMsg("sql....."+sql);
			}catch(Exception e)
			{
				throw new Exception(e.getMessage());
			}
			return sevVal;
		}


}

