package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PlmTlConditionalappraisalDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlConditionalappraisalSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.GenTlSparesmstSql;
//import com.akranta.tpm.dao.sql.KznTlBestmstSql;
import com.akranta.tpm.dao.sql.PlmTlConditionalappraisalSql;
import com.akranta.tpm.dao.sql.PlmTlConditionalappraisalentrySql;
import com.akranta.tpm.dao.sql.PlmTlConditionalappraisalmstSql;
import com.akranta.tpm.dao.sql.PlmTlConditionalappraisalmstentrySql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlConditionalappraisal;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GenTlSparesmst;
import com.akranta.tpm.model.KznTlBestmst;
import com.akranta.tpm.model.PlmTlConditionalappraisal;
import com.akranta.tpm.model.PlmTlConditionalappraisalEntry;
import com.akranta.tpm.model.PlmTlConditionalappraisalmst;
import com.akranta.tpm.model.PlmTlConditionalappraisalmstentry;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.ConditionalAppraisalServiceApi;

/* dao implementation */
public class PlmTlConditionalappraisalDaoImpl implements PlmTlConditionalappraisalDao {


	private DBActionTemplate dbActionTemplate; 
	private ConditionalAppraisalServiceApi conditionalappraisalserviceapi;
	FunctionCallApi fnCallApi;


	public PlmTlConditionalappraisalDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void PlmTlConditionalappraisalDaoImplJwt(String JwtToken) 
	{
		try{
			conditionalappraisalserviceapi = new ConditionalAppraisalServiceApi(JwtToken);
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

	public PlmTlConditionalappraisal create(PlmTlConditionalappraisal plmTlConditionalappraisal) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlConditionalappraisalSql plmTlConditionalappraisalsql = new PlmTlConditionalappraisalSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			plmTlConditionalappraisal.setCdapKeyid(dbActionTemplate.getSequenceNumber(PlmTlConditionalappraisalSql.TBL_PLM_TL_CONDITIONALAPPRAISAL,15,"CDA","","")); // set the sequnce number 
			sqls.add(PlmTlConditionalappraisalSql.getInsertSql(plmTlConditionalappraisalsql.getCdapDbFields(), plmTlConditionalappraisal.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return plmTlConditionalappraisal;
	}
	
	public PlmTlConditionalappraisal update(PlmTlConditionalappraisal plmTlConditionalappraisal)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PlmTlConditionalappraisalSql plmTlConditionalappraisalSql = new PlmTlConditionalappraisalSql();
		try {

			sqls.add(PlmTlConditionalappraisalSql.getUpdateSql(plmTlConditionalappraisalSql.getCdapDbFields(), plmTlConditionalappraisal.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return plmTlConditionalappraisal;
	}
	@Override
	public String addnewcomponent(GenTlSparesmst genTlSparesmst, GenTlFunctionallocn genTlFunctionallocn,String machid) throws Exception {
		
		GenTlFunctionallocnSql genTlFunctionallocnSql = new GenTlFunctionallocnSql();
		GenTlSparesmstSql GenTlSparesmstSql = new GenTlSparesmstSql();
		List<String> sqls = new ArrayList<String>(); 
		
		String sprsql = "select max(SPRM_PARTNO)+1 FROM gen_tl_sparesmst";		
		String maxpartno = dbActionTemplate.getSingleValue(sprsql);
		
		genTlSparesmst.setSprmKeyid(dbActionTemplate.getSequenceNumber(GenTlSparesmstSql.TBL_GEN_TL_SPARESMST));
		genTlSparesmst.setSprmPartno(maxpartno);
		
		String sql = "select * from GEN_TL_FUNCTIONALLOCN where FNLN_ORIGINALID = '"+ machid +"'";		
		List<String[]> fldata = dbActionTemplate.getDataList(sql);
		
		genTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12, "FNLN", "MMYY", "Y"));
		if(fldata.size() > 0)
		{
			genTlFunctionallocn.setFnlnParentid(fldata.get(0)[2]+"-"+fldata.get(0)[0]);
			genTlFunctionallocn.setFnlnElementid(fldata.get(0)[1]+"-"+genTlSparesmst.getSprmKeyid());
			genTlFunctionallocn.setFnlnDisplaycode(maxpartno);
			genTlFunctionallocn.setFnlnOriginalid(genTlSparesmst.getSprmKeyid());
			
		}
		sqls.add(GenTlSparesmstSql.getInsertSql(GenTlSparesmstSql.getSprmDbFields(), genTlSparesmst.getSaveArray()));
		sqls.add(genTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray()));
		
		CommonMessage.debugMsg("newsprsql" + sqls.toString());
		
		dbActionTemplate.executeStatements(sqls);
		
		// TODO Auto-generated method stub
		return genTlSparesmst.getSprmKeyid();
	}
	
	public PlmTlConditionalappraisalmst create(PlmTlConditionalappraisalmst plmTlConditionalappraisalmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlConditionalappraisalmstSql plmTlConditionalappraisalmstsql = new PlmTlConditionalappraisalmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		PlmTlConditionalappraisalSql plmTlConditionalappraisalsql = new PlmTlConditionalappraisalSql(); // contains dbtable,field names, Field types and related sqls  of master table
		PlmTlConditionalappraisal plmTlConditionalappraisal= plmTlConditionalappraisalmst.getPlmTlConditionalappraisal();
		try{
		
			plmTlConditionalappraisalmst.setCdamKeyid(dbActionTemplate.getSequenceNumber(PlmTlConditionalappraisalmstSql.TBL_PLM_TL_CONDITIONALAPPRAISALMST,10,"CDM","","")); // set the sequnce number 
			sqls.add(PlmTlConditionalappraisalmstSql.getInsertSql(plmTlConditionalappraisalmstsql.getCdamDbFields(), plmTlConditionalappraisalmst.getSaveArray())); // add insert sql for master table
			if(plmTlConditionalappraisal!=null)
			{
				plmTlConditionalappraisal.setCdapKeyid(dbActionTemplate.getSequenceNumber(PlmTlConditionalappraisalSql.TBL_PLM_TL_CONDITIONALAPPRAISAL,15,"CDA","","")); // set the sequnce number)
				plmTlConditionalappraisal.setCdapCdamKeyid(plmTlConditionalappraisalmst.getCdamKeyid());
				sqls.add(PlmTlConditionalappraisalSql.getInsertSql(plmTlConditionalappraisalsql.getCdapDbFields(), plmTlConditionalappraisal.getSaveArray())); // add insert sql for master table
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return plmTlConditionalappraisalmst;
	}
	public PlmTlConditionalappraisalmst createMstEntry(PlmTlConditionalappraisalmst plmTlConditionalappraisalmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlConditionalappraisalmstSql plmTlConditionalappraisalmstsql = new PlmTlConditionalappraisalmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		PlmTlConditionalappraisalSql plmTlConditionalappraisalsql = new PlmTlConditionalappraisalSql(); // contains dbtable,field names, Field types and related sqls  of master table
		PlmTlConditionalappraisal plmTlConditionalappraisal= plmTlConditionalappraisalmst.getPlmTlConditionalappraisal();
		try{
		
			plmTlConditionalappraisalmst.setCdamKeyid(dbActionTemplate.getSequenceNumber(PlmTlConditionalappraisalmstSql.TBL_PLM_TL_CONDITIONALAPPRAISALMST,10,"CDM","","")); // set the sequnce number 
			sqls.add(PlmTlConditionalappraisalmstSql.getInsertSql(plmTlConditionalappraisalmstsql.getCdamDbFields(), plmTlConditionalappraisalmst.getSaveArray())); // add insert sql for master table
			if(plmTlConditionalappraisal!=null)
			{
				plmTlConditionalappraisal.setCdapKeyid(dbActionTemplate.getSequenceNumber(PlmTlConditionalappraisalSql.TBL_PLM_TL_CONDITIONALAPPRAISAL,15,"CDA","","")); // set the sequnce number)
				plmTlConditionalappraisal.setCdapCdamKeyid(plmTlConditionalappraisalmst.getCdamKeyid());
				sqls.add(PlmTlConditionalappraisalSql.getInsertSql(plmTlConditionalappraisalsql.getCdapDbFields(), plmTlConditionalappraisal.getSaveArray())); // add insert sql for master table
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return plmTlConditionalappraisalmst;
	}
	
	
	public PlmTlConditionalappraisalmst update(PlmTlConditionalappraisalmst plmTlConditionalappraisalmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PlmTlConditionalappraisalmstSql plmTlConditionalappraisalmstSql = new PlmTlConditionalappraisalmstSql();
		PlmTlConditionalappraisalSql plmTlConditionalappraisalsql = new PlmTlConditionalappraisalSql(); // contains dbtable,field names, Field types and related sqls  of master table
		PlmTlConditionalappraisal plmTlConditionalappraisal= plmTlConditionalappraisalmst.getPlmTlConditionalappraisal();
		try {

			sqls.add(PlmTlConditionalappraisalmstSql.getUpdateSql(plmTlConditionalappraisalmstSql.getCdamDbFields(), plmTlConditionalappraisalmst.getSaveArray()));
			if(plmTlConditionalappraisal!=null)
			{
				if(!UIUtils.isValidKeyId(plmTlConditionalappraisal.getCdapKeyid())){
					plmTlConditionalappraisal.setCdapKeyid(dbActionTemplate.getSequenceNumber(PlmTlConditionalappraisalSql.TBL_PLM_TL_CONDITIONALAPPRAISAL,15,"CDA","","")); // set the sequnce number)
					plmTlConditionalappraisal.setCdapCdamKeyid(plmTlConditionalappraisalmst.getCdamKeyid());
					sqls.add(PlmTlConditionalappraisalSql.getInsertSql(plmTlConditionalappraisalsql.getCdapDbFields(), plmTlConditionalappraisal.getSaveArray())); // add insert sql for master table
				}else{
					plmTlConditionalappraisal.setCdapCdamKeyid(plmTlConditionalappraisalmst.getCdamKeyid());
					sqls.add(PlmTlConditionalappraisalSql.getUpdateSql(plmTlConditionalappraisalsql.getCdapDbFields(), plmTlConditionalappraisal.getSaveArray())); // add insert sql for master table
				}
			}
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return plmTlConditionalappraisalmst;
	}
	
	public PlmTlConditionalappraisalmst delete(PlmTlConditionalappraisalmst plmTlConditionalappraisalmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		PlmTlConditionalappraisalmstSql plmTlConditionalappraisalmstSql = new PlmTlConditionalappraisalmstSql();
		try {
			sqls.add(plmTlConditionalappraisalmstSql.getDeleteDetailSql(plmTlConditionalappraisalmst.getCdamKeyid()));
			sqls.add(PlmTlConditionalappraisalmstSql.getDeleteSql(plmTlConditionalappraisalmstSql.getCdamDbFields(), plmTlConditionalappraisalmst.getSaveArray()));
            CommonMessage.debugMsg(" Master Details " + sqls.toString());
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return plmTlConditionalappraisalmst;
	}
	
	public PlmTlConditionalappraisal delete(PlmTlConditionalappraisal plmTlConditionalappraisal)
		throws Exception {
	
	List<String> sqls = new ArrayList<String>();
	PlmTlConditionalappraisalSql plmTlConditionalappraisalSql = new PlmTlConditionalappraisalSql();
	try {
		
		sqls.add(PlmTlConditionalappraisalSql.getDeleteSql(plmTlConditionalappraisalSql.getCdapDbFields(), plmTlConditionalappraisal.getSaveArray()));
	
		dbActionTemplate.executeStatements(sqls);
		
	}catch( Exception e){
		throw new Exception(e.getMessage());
	}
	return plmTlConditionalappraisal;
	}

	@Override
	public List<String[]> recallData(String keyid) throws Exception {
		String sql = PlmTlConditionalappraisalSql.selectData(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}

	@Override
	public List<String[]> recallEntryData(String keyid) throws Exception {
		String sql = PlmTlConditionalappraisalentrySql.selectData(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}
	
	@Override
	public PlmTlConditionalappraisalmst getAllFillControl(String keyId) throws NoDataFoundException, SQLException, Exception {
		PlmTlConditionalappraisalmst plmTlConditionalappraisalmst = new PlmTlConditionalappraisalmst();
			String sql = PlmTlConditionalappraisalSql.getSingledata();
			CommonMessage.debugMsg("key- in impl"+keyId);
			CommonMessage.debugMsg("sql in impl"+sql);
			Object args[] = new Object[] {keyId};
			plmTlConditionalappraisalmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
			return plmTlConditionalappraisalmst;
			
			
	}

	@Override
	public Workbook getCondAppExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		try{				
			rs =   getCondAppResultSet(commonFilter);			 
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format, 3,0,0 );				
		}finally{
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet getCondAppResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.NewdbFunctionCall2("PLM_FN_CONDITIONAPPRAISALMAIN_SB", paramValues);
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		String type=commonFilter.getAbnViewType();
		CommonMessage.debugMsg(" Checking for type "+type);
		if(UIUtils.isValidKeyId(type))
			condParms +="TYPE="+type+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}

	@Override
	public List<String[]> getConditionalAppraisalGrid(CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			String type=commonFilter.getAbnViewType();
			CommonMessage.debugMsg(" Checking for type "+type);
			if(UIUtils.isValidKeyId(type))
				condParms +="TYPE="+type+";";
			
			condParms+="DATE=";
			if(UIUtils.isValidKeyId(commonFilter.getDteend()))
				condParms+=commonFilter.getDteend();
			condParms+=";";
			condParms+="MSTKEYID=";
			if(UIUtils.isValidKeyId(commonFilter.getKey()))
				condParms+=commonFilter.getKey();
			condParms+=";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			//List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("PLM_FN_CONDITIONAPPRAISAL", paramValues);
			List<String[]> dataList =   fnCallApi.callFunction("PLM_FN_CONDITIONAPPRAISAL_SB", paramValues,3,true);
			
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
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
	public List<String[]> getConditionalAppMainGrid(CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			//List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("PLM_FN_CONDITIONAPPRAISALMAIN", paramValues);
			
			List<String[]> dataList =  fnCallApi.callFunction("PLM_FN_CONDITIONAPPRAISALMAIN_SB", paramValues,3,false);
			
			
			
			
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
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
	public List<String[]> getCondApReport(CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			String type=commonFilter.getAbnViewType();
			CommonMessage.debugMsg(" Checking for type "+type);
			if(UIUtils.isValidKeyId(type))
				condParms +="TYPE="+type+";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			//List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("PLM_FN_CONDITIONAPPRAISALMAIN", paramValues);
			
			List<String[]> dataList =  fnCallApi.callFunction("PLM_FN_CONDITIONAPPRAISALMAIN_SB", paramValues,3,true);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
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
	public List<String[]> getExcelColmodel(CommonFilter commonFilter) throws Exception {
		
		try
		{
			List<String> paramValues = getFilterParamValuesRep(commonFilter);
			//List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("PLM_FN_CONDITIONAPPREPORT", paramValues);
			
			
			List<String[]> dataList =   fnCallApi.callFunction("PLM_FN_CONDITIONAPPREPORT_SB", paramValues,3,false);
			
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
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
	public Workbook getViewExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		//CommonMessage.debugMsg("getViewExcel");
		ResultSet rs = null;
		try{		
			rs =   getCondAppRepResultSet(commonFilter);			 
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );				
		}finally{
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet getCondAppRepResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValuesRep(commonFilter);
		return dbActionTemplate.NewdbFunctionCall2("PLM_FN_CONDITIONAPPRAISALEXCEL", paramValues);
	}

	private List<String> getFilterParamValuesRep(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		condParms+="DATE=";
		if(UIUtils.isValidKeyId(commonFilter.getDteend()))
			condParms+=commonFilter.getDteend();
		condParms+=";";
		condParms+="MSTKEYID=";
		if(UIUtils.isValidKeyId(commonFilter.getKey()))
			condParms+=commonFilter.getKey();
		condParms+=";";
		if ("Y".equals(commonFilter.getFreq())){
			condParms+="ISFORMAT="+commonFilter.getFreq()+";";
		}
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}

	@Override
	public PlmTlConditionalappraisalmstentry createEntry(
			PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlConditionalappraisalmstentrySql plmTlConditionalappraisalmstsql = new PlmTlConditionalappraisalmstentrySql(); // contains dbtable,field names, Field types and related sqls  of master table
		PlmTlConditionalappraisalentrySql plmTlConditionalappraisalsql = new PlmTlConditionalappraisalentrySql(); // contains dbtable,field names, Field types and related sqls  of master table
		PlmTlConditionalappraisalEntry plmTlConditionalappraisal= plmTlConditionalappraisalmst.getPlmTlConditionalappraisal();
		try{
		
			plmTlConditionalappraisalmst.setCdamKeyid(dbActionTemplate.getSequenceNumber(PlmTlConditionalappraisalmstentrySql.TBL_PLM_TL_CONDITIONALAPPRAISALMSTENTRY,10,"CDME","","")); // set the sequnce number 
			sqls.add(PlmTlConditionalappraisalmstentrySql.getInsertSql(plmTlConditionalappraisalmstsql.getCdamDbFields(), plmTlConditionalappraisalmst.getSaveArray())); // add insert sql for master table
			if(plmTlConditionalappraisal!=null)
			{
				plmTlConditionalappraisal.setCdapKeyid(dbActionTemplate.getSequenceNumber(PlmTlConditionalappraisalentrySql.TBL_PLM_TL_CONDITIONALAPPRAISALENTRY,15,"CDAE","","")); // set the sequnce number)
				plmTlConditionalappraisal.setCdapCdamKeyid(plmTlConditionalappraisalmst.getCdamKeyid());
				sqls.add(PlmTlConditionalappraisalentrySql.getInsertSql(plmTlConditionalappraisalsql.getCdapDbFields(), plmTlConditionalappraisal.getSaveArray())); // add insert sql for master table
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return plmTlConditionalappraisalmst;
	}
    public String getmasterentryid(PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst) throws Exception
    {
    	String sql = "select CDAM_KEYID from PLM_TL_CONAPPRAISALMSTENTRY,PLM_TL_CONAPPRAISALENTRY where CDAP_CDAM_KEYID = CDAM_KEYID and CDAM_FLID = '" + plmTlConditionalappraisalmst.getCdamFlid() + "' and CDAM_DATE = '" + plmTlConditionalappraisalmst.getCdamDate() + "'" ;
				
		CommonMessage.debugMsg("checkupdate sql...."+sql);
		String keyid = dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg("checkupdate sql keyid ...."+keyid);
    	return keyid;
    	
    }
	public String checkupdate(PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst,String forGrid )
			throws Exception {
		CommonMessage.debugMsg("checkupdate sql....forGrid "+ forGrid);
		String sql = "";
		if ("N".equals(forGrid) )
		{   sql = "select count(CDAM_KEYID) from PLM_TL_CONAPPRAISALMSTENTRY,PLM_TL_CONAPPRAISALENTRY where CDAP_CDAM_KEYID = CDAM_KEYID and CDAM_FLID = '" + plmTlConditionalappraisalmst.getCdamFlid() + "' and CDAM_DATE = '" + plmTlConditionalappraisalmst.getCdamDate() + "'" ;
			sql = sql + " and  CDAP_CDAPKEYID = '"+ plmTlConditionalappraisalmst.getPlmTlConditionalappraisal().getCdapCdapkeyid() + "'";
		}
		else
		{
			sql = "select count(CDAM_KEYID) from PLM_TL_CONAPPRAISALMSTENTRY where CDAM_FLID = '" + plmTlConditionalappraisalmst.getCdamFlid() + "' and CDAM_DATE = '" + plmTlConditionalappraisalmst.getCdamDate() + "'" ;
		}
		CommonMessage.debugMsg("checkupdate sql...."+sql);
		String keyid = dbActionTemplate.getSingleValue(sql);
		return keyid ;
	}

	@Override
	public List<String[]> getConditionalAppraisalEntryGrid(
			CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			condParms+="DATE=";
			if(UIUtils.isValidKeyId(commonFilter.getDteend()))
				condParms+=commonFilter.getDteend();
			condParms+=";";
			condParms+="MSTKEYID=";
			if(UIUtils.isValidKeyId(commonFilter.getKey()))
				condParms+=commonFilter.getKey();
			condParms+=";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			//List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("PLM_FN_CONDITIONAPPRAISALENTRY", paramValues);
			
			List<String[]> dataList =   fnCallApi.callFunction("PLM_FN_CONDITIONAPPRAISALENTRY_SB", paramValues,3,false);
			
			
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
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
	public PlmTlConditionalappraisalmstentry updateEntry(
			PlmTlConditionalappraisalmstentry plmTlConditionalappraisalmst)
			throws Exception {
		List<String> sqls = new ArrayList<String>();
		PlmTlConditionalappraisalmstentrySql plmTlConditionalappraisalmstSql = new PlmTlConditionalappraisalmstentrySql();
		PlmTlConditionalappraisalentrySql plmTlConditionalappraisalsql = new PlmTlConditionalappraisalentrySql(); // contains dbtable,field names, Field types and related sqls  of master table
		PlmTlConditionalappraisalEntry plmTlConditionalappraisal= plmTlConditionalappraisalmst.getPlmTlConditionalappraisal();
		try {

			sqls.add(PlmTlConditionalappraisalmstentrySql.getUpdateSql(plmTlConditionalappraisalmstSql.getCdamDbFields(), plmTlConditionalappraisalmst.getSaveArray()));
			if(plmTlConditionalappraisal!=null)
			{     String count = checkupdate(plmTlConditionalappraisalmst,"N");
			
				if(Integer.parseInt(count) > 0){
					plmTlConditionalappraisal.setCdapCdamKeyid(plmTlConditionalappraisalmst.getCdamKeyid());
					plmTlConditionalappraisal.setCdapCdamKeyid(getmasterentryid(plmTlConditionalappraisalmst));
					sqls.add(PlmTlConditionalappraisalentrySql.getUpdateSql(plmTlConditionalappraisalsql.getCdapDbFields(), plmTlConditionalappraisal.getSaveArray())); // add insert sql for master table
				}else{					
					plmTlConditionalappraisal.setCdapKeyid(dbActionTemplate.getSequenceNumber(PlmTlConditionalappraisalentrySql.TBL_PLM_TL_CONDITIONALAPPRAISALENTRY,15,"CDAE","","")); // set the sequnce number)
					//plmTlConditionalappraisal.setCdapCdamKeyid(plmTlConditionalappraisalmst.getCdamKeyid());
					plmTlConditionalappraisal.setCdapCdamKeyid(getmasterentryid(plmTlConditionalappraisalmst));
					sqls.add(PlmTlConditionalappraisalentrySql.getInsertSql(plmTlConditionalappraisalsql.getCdapDbFields(), plmTlConditionalappraisal.getSaveArray())); // add insert sql for master table					
					
				}
			}
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return plmTlConditionalappraisalmst;
	}
	@Override
	public List<String[]> getUserRoleDetails(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception {
	    StringBuffer sql = new StringBuffer();
	    sql.append("SELECT FNLN_ELEMENTID, FNLN_KEYID, ROLE_LEVEL, ROLE_NAME, ROLE_KEYID ");
	    sql.append("FROM GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM, ADM_TL_ROLEMST ");
	    sql.append("WHERE FNLN_KEYID = FRT_FNLN_KEYID ");
	    sql.append("AND FRT_ROLE_KEYID = ROLE_KEYID ");
	    
	    if (UIUtils.isValidKeyId(loginflid)) {
	        sql.append("AND FRT_FNLN_KEYID = '" + loginflid + "' ");
	    }
	    
	    sql.append("AND FRT_EMPM_KEYID = '" + empId + "' ");
	    sql.append("AND ROLE_LEVEL = '" + loginlevel + "'");
	    
	    Object[] args = new Object[0];
	    CommonMessage.debugMsg("=== Role Query SQL ===");
	    CommonMessage.debugMsg(sql.toString());
	    
	    List<String[]> userDatas = this.dbActionTemplate.getDataList(sql.toString(), args);
	    
	    CommonMessage.debugMsg("Query returned " + (userDatas != null ? userDatas.size() : 0) + " rows");
	    
	    return userDatas;
	}
	
	
}

