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
import com.akranta.tpm.dao.SheTlAccinvestigationmstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.PlmTlConditionalappraisalSql;
import com.akranta.tpm.dao.sql.SheTlAccinvestigationdtlSql;
import com.akranta.tpm.dao.sql.SheTlAccinvestigationmstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PlmTlConditionalappraisal;
import com.akranta.tpm.model.SheTlAccinvestigationdtl;
import com.akranta.tpm.model.SheTlAccinvestigationmst;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class SheTlAccinvestigationmstDaoImpl implements SheTlAccinvestigationmstDao {


	private DBActionTemplate dbActionTemplate; 

	public SheTlAccinvestigationmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public SheTlAccinvestigationmst create(SheTlAccinvestigationmst sheTlAccinvestigationmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SheTlAccinvestigationmstSql sheTlAccinvestigationmstSql = new SheTlAccinvestigationmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		SheTlAccinvestigationdtlSql sheTlAccinvestigationdtlSql = new SheTlAccinvestigationdtlSql();
		List<SheTlAccinvestigationdtl> listSheTlAccinvestigationdtl=sheTlAccinvestigationmst.getSheTlAccinvestigationdtl();
		
		try{
		
			sheTlAccinvestigationmst.setAinmKeyid(dbActionTemplate.getSequenceNumber(SheTlAccinvestigationmstSql.TBL_SHE_TL_ACCINVESTIGATIONMST, 10, "AINM", "", "") );
			sqls.add(SheTlAccinvestigationmstSql.getInsertSql(sheTlAccinvestigationmstSql.getAinmDbFields(), sheTlAccinvestigationmst.getSaveArray())); // add insert sql for master table
			if(listSheTlAccinvestigationdtl!=null){
				if(listSheTlAccinvestigationdtl.size()>0){
					for(SheTlAccinvestigationdtl sheTlAccinvestigationdtl:listSheTlAccinvestigationdtl){
						sheTlAccinvestigationdtl.setAindKeyid(dbActionTemplate.getSequenceNumber(SheTlAccinvestigationdtlSql.TBL_SHE_TL_ACCINVESTIGATIONDTL, 10, "AIND", "", "") ); // set the sequnce number
						sheTlAccinvestigationdtl.setAindAinmKeyid(sheTlAccinvestigationmst.getAinmKeyid());
						sqls.add(SheTlAccinvestigationdtlSql.getInsertSql(sheTlAccinvestigationdtlSql.getAindDbFields(),sheTlAccinvestigationdtl.getSaveArray())); // add insert sql for master table
					}
				}
			}
			CommonFunctions.debugMsg("sqls "+sqls.toString());
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return sheTlAccinvestigationmst;
	}
	
	public SheTlAccinvestigationmst update(SheTlAccinvestigationmst sheTlAccinvestigationmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		SheTlAccinvestigationmstSql sheTlAccinvestigationmstSql = new SheTlAccinvestigationmstSql();
		SheTlAccinvestigationdtlSql sheTlAccinvestigationdtlSql = new SheTlAccinvestigationdtlSql();
		List<SheTlAccinvestigationdtl> listSheTlAccinvestigationdtl=sheTlAccinvestigationmst.getSheTlAccinvestigationdtl();
		try {

			sqls.add(SheTlAccinvestigationmstSql.getUpdateSql(sheTlAccinvestigationmstSql.getAinmDbFields(), sheTlAccinvestigationmst.getSaveArray()));
			if(listSheTlAccinvestigationdtl!=null){
				if(listSheTlAccinvestigationdtl.size()>0){
					for(SheTlAccinvestigationdtl sheTlAccinvestigationdtl:listSheTlAccinvestigationdtl){
						sheTlAccinvestigationdtl.setAindAinmKeyid(sheTlAccinvestigationmst.getAinmKeyid());
						if(!UIUtils.isValidKeyId(sheTlAccinvestigationdtl.getAindKeyid())){
							sheTlAccinvestigationdtl.setAindKeyid(dbActionTemplate.getSequenceNumber(SheTlAccinvestigationdtlSql.TBL_SHE_TL_ACCINVESTIGATIONDTL, 10, "AIND", "", "") ); // set the sequnce number
							sqls.add(SheTlAccinvestigationdtlSql.getInsertSql(sheTlAccinvestigationdtlSql.getAindDbFields(),sheTlAccinvestigationdtl.getSaveArray())); // add insert sql for master table
						}
						else
							sqls.add(SheTlAccinvestigationdtlSql.getUpdateSql(sheTlAccinvestigationdtlSql.getAindDbFields(),sheTlAccinvestigationdtl.getSaveArray())); // add insert sql for master table
					}
				}
			}
			CommonFunctions.debugMsg("sqls "+sqls.toString());
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return sheTlAccinvestigationmst;
	}
	
	public SheTlAccinvestigationmst delete(SheTlAccinvestigationmst sheTlAccinvestigationmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		SheTlAccinvestigationmstSql sheTlAccinvestigationmstSql = new SheTlAccinvestigationmstSql();
		try {
			sqls.add(SheTlAccinvestigationdtlSql.getDeleteDtlSql(sheTlAccinvestigationmst.getAinmKeyid()));
			sqls.add(SheTlAccinvestigationmstSql.getDeleteSql(sheTlAccinvestigationmstSql.getAinmDbFields(), sheTlAccinvestigationmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return sheTlAccinvestigationmst;
	}

	@Override
	public List<String[]> getAccInvestDtl(CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			condParms+="MSTKEYID=";
			if(UIUtils.isValidKeyId(commonFilter.getKey()))
				condParms+=commonFilter.getKey();
			condParms+=";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("HSE_PC_SAFETY.SHE_FN_ACCINVESTDTL", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public List<String[]> getAccInvestMainGrid(CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("HSE_PC_SAFETY.SHE_FN_ACCINVESTMAINGRID", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}

	@Override
	public SheTlAccinvestigationmst getAllData(String keyid) throws NoDataFoundException, SQLException, Exception {
		SheTlAccinvestigationmst sheTlAccinvestigationmst = new SheTlAccinvestigationmst();
		String sql = SheTlAccinvestigationmstSql.getFillData();
		System.out.println("key- in impl"+keyid);
		System.out.println("sql in impl"+sql);
		Object args[] = new Object[] {keyid};
		
		sheTlAccinvestigationmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return sheTlAccinvestigationmst;
	}
	@Override
	public Workbook getExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		try{				
			rs =   getResultSet(commonFilter);			 
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );				
		}finally{
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet getResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.SHE_FN_ACCINVESTMAINGRID", paramValues);
	}

	@Override
	public String getElementID(String ainmFlid) throws Exception {
		String sql = SheTlAccinvestigationmstSql.getElementId(ainmFlid);
		String elementid=dbActionTemplate.getSingleValue(sql);
		return elementid;
	}
}

