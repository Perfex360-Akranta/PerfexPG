package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.FieldAuditSheetDao;
import com.akranta.tpm.dao.sql.FieldAuditSheetdtlSql;
import com.akranta.tpm.dao.sql.FieldAuditSheetmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FieldAuditSheetdtl;
import com.akranta.tpm.model.FieldAuditSheetmst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.WhywhyServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;



import net.sf.json.JSONObject;

public class FieldAuditSheetDaoImpl implements FieldAuditSheetDao{
	private DBActionTemplate dbActionTemplate;
	private CommonFilterDao commonFilterdao;
	private FieldAuditSheetServiceApi fieldauditsheetApi;
	FunctionCallApi fnCallApi;
	
	public FieldAuditSheetDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;	
	}
  public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
  //mano
  public void FieldAuditSheetDaoImplJwt(String JwtToken) 
	{
		try{
			fieldauditsheetApi = new FieldAuditSheetServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
  
	@Override
	public List<String[]> FieldAuditSheetDetail(CommonFilter commonFilter) throws Exception {		
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = "";  
			CommonMessage.debugMsg("Key id "+commonFilter.getKey());
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms="FASM_KEYID="+commonFilter.getKey()+";";
			}			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			//dataList =  dbActionTemplate.processFunctionCalls("JHA_TL_FIELDAUDITSHEETDTL", paramValues);
			dataList =  fnCallApi.callFunction("JHA_TL_FIELDAUDITSHEETDTL_SB", paramValues,2,false);
			
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				CommonMessage.debugMsg("tOTAL COUNT "+totalCnt);
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
	
	
	public FieldAuditSheetmst select(String keyid) throws Exception{
		FieldAuditSheetmst newFieldAuditSheetmst=new FieldAuditSheetmst();
		String sql = FieldAuditSheetmstSql.getSelectSql();
		CommonMessage.debugMsg(keyid+" sql   "+sql);   
		Object args [] = new Object [] {keyid};
		newFieldAuditSheetmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return newFieldAuditSheetmst;
	}

  
	public List<String[]> getFieldAuditSheetList(CommonFilter commonFilter)
			throws Exception {		
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			//dataList =  dbActionTemplate.processFunctionCalls("JHA_TL_FIELDAUDITSHEETMSTLIST", paramValues);
			
			dataList = fnCallApi.callFunction("JHA_TL_FIELDAUDITSHEETMSTLIST_SB", paramValues,2,true);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
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
  
	public List<String[]> getflid(String originalid) throws Exception {
	String sqlval = "SELECT locn_keyid,sect_keyid,fnln_keyid,cell_keyid FROM GEN_VW_FNLN WHERE FNLN_ORIGINALID= '"+originalid+"'";
	CommonMessage.debugMsg("The SqlVal"+sqlval);
	List<String[]> keyids=dbActionTemplate.getDataList(sqlval);
	return keyids;
}
	          
	@Override
	public Workbook getFieldAuditModificationGridDataExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,
			String format) throws Exception {
		// TODO Auto-generated method stub
		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("JHA_TL_FIELDAUDITSHEETMSTLIST", paramValues);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format,2,0,0 );
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());		     
		 }
	}
	public FieldAuditSheetmst create(FieldAuditSheetmst fieldAuditSheetmst) 	
		throws ValidationExceptions,BusinessApplicationExceptions,Exception { 

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		FieldAuditSheetmstSql  fieldAuditSheetmstSql=new FieldAuditSheetmstSql();
		FieldAuditSheetdtlSql  fieldAuditSheetdtlSql=new FieldAuditSheetdtlSql();
		FieldAuditSheetdtl fieldAuditSheetdtl=new FieldAuditSheetdtl();
		
		try{
						
			fieldAuditSheetmst.setFasmKeyid(dbActionTemplate.getSequenceNumber(FieldAuditSheetmstSql.TBL_JHA_TL_FIELDAUDITSHEETMST,12,"FASM", "", "")); // set the sequnce number 
			sqls.add(FieldAuditSheetmstSql.getInsertSql(fieldAuditSheetmstSql.getfasmDbFields(), fieldAuditSheetmst.getSaveArray()));
			if (fieldAuditSheetmst.getAuditDtl()!=null){		
				List<FieldAuditSheetdtl> fieldAuditSheetdtlList= fieldAuditSheetmst.getAuditDtl();
				for(int i=0 ;i<=fieldAuditSheetdtlList.size()-1;i++){				
					fieldAuditSheetdtl=fieldAuditSheetdtlList.get(i);
					if (!CommonFunctions.isValidKeyId(fieldAuditSheetdtl.getFasdKeyid())){
						fieldAuditSheetdtl.setFasdmasterid(fieldAuditSheetmst.getFasmKeyid());
						fieldAuditSheetdtl.setFasdKeyid(dbActionTemplate.getSequenceNumber(FieldAuditSheetdtlSql.TBL_JHA_TL_FIELDAUDITSHEETDTL,12,"FASD","",""));
						sqls.add(fieldAuditSheetdtlSql.getInsertSql(fieldAuditSheetdtlSql.getfasdDbFields(), fieldAuditSheetdtl.getSaveArray()));
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
		return fieldAuditSheetmst;
	}
	
	/*
	 * public FieldAuditSheetmst update(FieldAuditSheetmst fieldAuditSheetmst)
	 * throws ValidationExceptions,BusinessApplicationExceptions,Exception{
	 * 
	 * List<String> sqls = new ArrayList<String>();
	 * 
	 * FieldAuditSheetmstSql fieldAuditSheetmstSql=new FieldAuditSheetmstSql();
	 * FieldAuditSheetdtlSql fieldAuditSheetdtlSql=new FieldAuditSheetdtlSql();
	 * FieldAuditSheetdtl fieldAuditSheetdtl=new FieldAuditSheetdtl();
	 * 
	 * try { sqls.add(FieldAuditSheetmstSql.getUpdateSql(fieldAuditSheetmstSql.
	 * getfasmDbFields(), fieldAuditSheetmst.getSaveArray())); if
	 * (fieldAuditSheetmst.getAuditDtl()!=null){ List<FieldAuditSheetdtl>
	 * fieldAuditSheetdtlList=fieldAuditSheetmst.getAuditDtl(); for(int i=0
	 * ;i<=fieldAuditSheetdtlList.size()-1;i++){
	 * fieldAuditSheetdtl=fieldAuditSheetdtlList.get(i);
	 * CommonMessage.debugMsg("Keyid:::::"+fieldAuditSheetmst.getFasmKeyid()); if
	 * (!CommonFunctions.isValidKeyId(fieldAuditSheetdtl.getFasdKeyid())){
	 * fieldAuditSheetdtl.setFasdmasterid(fieldAuditSheetmst.getFasmKeyid());
	 * fieldAuditSheetdtl.setFasdKeyid(dbActionTemplate.getSequenceNumber(
	 * FieldAuditSheetdtlSql.TBL_JHA_TL_FIELDAUDITSHEETDTL,12,"FASD","",""));
	 * sqls.add(fieldAuditSheetdtlSql.getInsertSql(fieldAuditSheetdtlSql.
	 * getfasdDbFields(), fieldAuditSheetdtl.getSaveArray())); } else{
	 * sqls.add(fieldAuditSheetdtlSql.getUpdateSql(fieldAuditSheetdtlSql.
	 * getfasdDbFields(),fieldAuditSheetmst.getSaveArray())); } } }
	 * dbActionTemplate.executeStatements(sqls); }catch (ValidationExceptions e){
	 * throw new ValidationExceptions(e.getMessage()); } catch
	 * (BusinessApplicationExceptions e){ throw new
	 * BusinessApplicationExceptions(e.getMessage()); }catch( Exception e){ throw
	 * new Exception(e.getMessage()); } return fieldAuditSheetmst;
	 * 
	 * }
	 */
	//mano
	public FieldAuditSheetmst update(FieldAuditSheetmst fieldAuditSheetmst) 
	        throws ValidationExceptions, BusinessApplicationExceptions, Exception {
	    
	    List<String> sqls = new ArrayList<String>();
	    
	    FieldAuditSheetmstSql fieldAuditSheetmstSql = new FieldAuditSheetmstSql();
	    FieldAuditSheetdtlSql fieldAuditSheetdtlSql = new FieldAuditSheetdtlSql();
	    
	    try {
	        sqls.add(FieldAuditSheetmstSql.getUpdateSql(
	            fieldAuditSheetmstSql.getfasmDbFields(), 
	            fieldAuditSheetmst.getSaveArray()));
	        
	        if (fieldAuditSheetmst.getAuditDtl() != null) {
	            List<FieldAuditSheetdtl> fieldAuditSheetdtlList = fieldAuditSheetmst.getAuditDtl();
	            
	            for (int i = 0; i <= fieldAuditSheetdtlList.size() - 1; i++) {
	                FieldAuditSheetdtl fieldAuditSheetdtl = fieldAuditSheetdtlList.get(i);
	                CommonMessage.debugMsg("Keyid:::::" + fieldAuditSheetmst.getFasmKeyid());
	                
	                if (!CommonFunctions.isValidKeyId(fieldAuditSheetdtl.getFasdKeyid())) {
	                    fieldAuditSheetdtl.setFasdKeyid(
	                        dbActionTemplate.getSequenceNumber(FieldAuditSheetdtlSql.TBL_JHA_TL_FIELDAUDITSHEETDTL,12, "FASD", "", ""));
	                    sqls.add(fieldAuditSheetdtlSql.getInsertSql(fieldAuditSheetdtlSql.getfasdDbFields(),fieldAuditSheetdtl.getSaveArray()));
	                } else {
	                    // ✅ Use fieldAuditSheetdtl, not fieldAuditSheetmst
	                	//mano
	                    sqls.add(fieldAuditSheetdtlSql.getUpdateSql(fieldAuditSheetdtlSql.getfasdDbFields(),fieldAuditSheetdtl.getSaveArray()));
	                }
	            }
	        }
	        dbActionTemplate.executeStatements(sqls);
	        
	    } catch (ValidationExceptions e) {
	        throw new ValidationExceptions(e.getMessage());
	    } catch (BusinessApplicationExceptions e) {
	        throw new BusinessApplicationExceptions(e.getMessage());
	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }
	    
	    return fieldAuditSheetmst;
	}
    public List<String[]> getFieldAuditSheetReport(CommonFilter commonFilter)throws Exception{
    	try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			//dataList =  dbActionTemplate.processFunctionCalls("JHA_TL_FIELDAUDITSHEETREPORT", paramValues);
			dataList =  fnCallApi.callFunction("JHA_TL_FIELDAUDITSHEETREPORT_SB", paramValues,3,true);
			
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
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
	public Workbook getFieldAuditSheetReportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,
			String format) throws Exception {
		// TODO Auto-generated method stub
		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("JHA_TL_FIELDAUDITSHEETREPORT_SB", paramValues);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format,3,0,0 );
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());		     
		 }
	}
}
