package com.akranta.tpm.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Author:N Arun
 * Created on:25.11.2011
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.businessvalidations.FunctionalLocValidations;
import com.akranta.tpm.dao.BAL_GenTlAssemblymstDao;
//import com.akranta.tpm.dao.sql.BAL_CliTlStandardsSql;
//import com.akranta.tpm.dao.sql.BAL_CompanySql;
import com.akranta.tpm.dao.sql.BAL_GenTlAssemblymstSql;
import com.akranta.tpm.dao.sql.BAL_GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.TableNames;
//import com.akranta.tpm.model.CliTlStandards;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONObject;

import com.akranta.tpm.businessvalidations.FunctionalLocValidations;
import com.akranta.tpm.controller.UIUtils;
/* dao implementation */
public class BAL_AssemblyDaoImpl implements BAL_GenTlAssemblymstDao {
	private DBActionTemplate dbActionTemplate; 
	private static final String TBL_GEN_TL_FUNCTIONALLOCN = "GEN_TL_FUNCTIONALLOCN";  
	private FunctionalLocValidations functionalLocValidations = null;
	public BAL_AssemblyDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		functionalLocValidations = new FunctionalLocValidations(dbActionTemplate);
		
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	//added here by priyanka on 17/06/2026
	
	private FunctionCallApi fnCallApi;

	public void BAL_GenTlAssemblymstDaoImplJwt(String jwtToken) {
	    try {
	        fnCallApi = new FunctionCallApi(jwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//end here

	public GenTlAssemblymst create(GenTlAssemblymst genTlAssemblymst,GenTlFunctionallocn genTlFunctionallocn) 	throws Exception {
		
		BAL_GenTlAssemblymstSql genTlAssemblymstSql = new BAL_GenTlAssemblymstSql();
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql = new BAL_GenTlFunctionallocnSql();
		List<String> sqls = new ArrayList<String>(); 
				
			genTlAssemblymst.setAssmKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlAssemblymstSql. TBL_BAL_GEN_TL_ASSEMBLYMST)); // set the sequnce number 
			genTlAssemblymst.setAssmCode(genTlAssemblymst.getAssmKeyid());
			if(genTlFunctionallocn != null)
			{
				String elementId = dbActionTemplate.getSingleValue(TableNames.TBL_BAL_GEN_TL_FUNCTIONALLOCN, "FNLN_ELEMENTID", "FNLN_ORIGINALID", genTlFunctionallocn.getFnlnOriginalid());
				System.out.println("Element Id : "+elementId);
				if(UIUtils.isValidKeyId(elementId))
				{
					genTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_BAL_GEN_TL_FUNCTIONALLOCN));
					genTlFunctionallocn.setFnlnOriginalid(genTlAssemblymst.getAssmKeyid());
					genTlFunctionallocn.setFnlnElementid(elementId + "-"+genTlAssemblymst.getAssmKeyid());
					genTlFunctionallocn.setFnlnParentid(elementId);
					//genTlFunctionallocn.setFnlnDisplaycode(genTlAssemblymst.getAssmCode());
					genTlFunctionallocn.setFnlnDisplaycode(genTlAssemblymst.getAssmName());
					sqls.add(BAL_GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray())); // add insert sql for master table
				}
			}
			System.out.println("Assembly Id : "+genTlAssemblymst.getAssmKeyid());
			sqls.add(BAL_GenTlAssemblymstSql.getInsertSql(genTlAssemblymstSql.getAssmDbFields(), genTlAssemblymst.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return genTlAssemblymst;
	}
	
	public GenTlAssemblymst update(GenTlAssemblymst genTlAssemblymst,GenTlFunctionallocn genTlFunctionallocn)	throws Exception { 
		List<String> sqls = new ArrayList<String>();
		BAL_GenTlAssemblymstSql genTlAssemblymstSql = new BAL_GenTlAssemblymstSql();
		try {
			
			genTlAssemblymst.setAssmCode(genTlAssemblymst.getAssmKeyid());
			if(genTlFunctionallocn != null)
			{
				String elementId = dbActionTemplate.getSingleValue(TableNames.TBL_BAL_GEN_TL_FUNCTIONALLOCN, "FNLN_ELEMENTID", "FNLN_ORIGINALID", genTlFunctionallocn.getFnlnOriginalid());
				System.out.println("Element Id : "+elementId);
				if(UIUtils.isValidKeyId(elementId))
				{
					GenTlFunctionallocnSql genTlFunctionallocnSql = new GenTlFunctionallocnSql();
					
					genTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_BAL_GEN_TL_FUNCTIONALLOCN));
					genTlFunctionallocn.setFnlnOriginalid(genTlAssemblymst.getAssmKeyid());
					genTlFunctionallocn.setFnlnElementid(elementId + "-"+genTlAssemblymst.getAssmKeyid());
					genTlFunctionallocn.setFnlnParentid(elementId);
					genTlFunctionallocn.setFnlnDescription(genTlAssemblymst.getAssmName());
					genTlFunctionallocn.setFnlnDisplaycode(genTlAssemblymst.getAssmName());
					//sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray())); // add insert sql for master table
					sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray())); // add insert sql for master table
				}
			}
			sqls.add(BAL_GenTlAssemblymstSql.getUpdateSql(genTlAssemblymstSql.getAssmDbFields(), genTlAssemblymst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlAssemblymst;
	}
	
	public GenTlAssemblymst delete(String delemode,GenTlAssemblymst genTlAssemblymst) throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_GenTlAssemblymstSql genTlAssemblymstSql = new BAL_GenTlAssemblymstSql();
		String originalId= genTlAssemblymst.getAssmKeyid(); 
		
		if (!delemode.equals("I"))
		functionalLocValidations.checkOriginalIdExistsinFunctionalLoc(originalId);
		try {
			
			sqls.add(BAL_GenTlAssemblymstSql.getDeleteSql(delemode,genTlAssemblymstSql.getAssmDbFields(), genTlAssemblymst.getSaveArray()));
			String inactFun = dbActionTemplate.getSingleValue(" SELECT count (*)  from " + TBL_GEN_TL_FUNCTIONALLOCN + " where FNLN_ORIGINALID =  '"+originalId+"' ");
			 
			if(inactFun.length()>0){
			  	sqls.add(  " " + " UPDATE " + TBL_GEN_TL_FUNCTIONALLOCN + " SET "+" FNLN_ACTIVE " +" = 'N'" +" where FNLN_ORIGINALID =  '"+originalId+"' ");	
				 
			}
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlAssemblymst;
	}

	

	
	
	public GenTlAssemblymst select(String keyId) throws Exception {
		// TODO Auto-generated method stub
		GenTlAssemblymst genTlAssemblymst = new GenTlAssemblymst();
		String sql = BAL_GenTlAssemblymstSql.getAssemblymstSql();
		
		Object args [] = new Object [] { keyId };
		genTlAssemblymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlAssemblymst;
	}

	//MANO REMOVE
	public List<String[]> getGenTlAssemblymst(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//added here by priyanka on 16/06/2026
	
	/*public GenTlAssemblymst assmformfill(String keyId)
	// TODO Auto-generated method stub
	{
		try
		{
			GenTlAssemblymst genTlAssemblymst = new GenTlAssemblymst();
			
			String sql = GenTlAssemblymstSql.getassmfrmdatasql();
					
			System.out.println("DAO SQL : "+sql);
			Object [] args =  new Object [] {keyId };
			genTlAssemblymst.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
			return  genTlAssemblymst;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}*/
	//	return null;


	//added here by priyanka on 17/06/2026
	


	// ── getAssemblyList ────────────────────────────────────────────────────
	
	
	@Override
	public List<String[]> getAssemblyList(CommonFilter commonFilter) throws Exception {
	    try {
	        if (fnCallApi == null) {
	            throw new Exception("FunctionCallApi not initialized.");
	        }

	        List<String> paramValues = new ArrayList<>();

	        // This already includes machineId= from condParam set in servlet
	        String condParms    = "";
	        if (CommonFunctions.isValidKeyId(commonFilter.getMachineId())) {
	            condParms = "MACHINEID=" + commonFilter.getMachineId() + ";";
	        }
	        String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

	        CommonMessage.debugMsg("getAssemblyList condParms=[" + condParms + "]");

	        paramValues.add(condParms);
	        paramValues.add(commonParams);

	        List<String[]> dataList = fnCallApi.callFunction(
	                "BAL_GEN_FN_ASSEMBLYMST", paramValues, 3, true);
	        
	        System.out.println("paramValues after function call = " + paramValues);

	        if (commonFilter.getViewClick() == 'Y') {
	            String totalCnt = paramValues.get(0);
	            boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	            if (isInteger) {
	                commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
	            }
	        }

	        return dataList;

	    } catch (Exception e) {
	        CommonMessage.debugMsg("getAssemblyList error: " + e.getMessage());
	        throw new Exception(e.getMessage());
	    }
	}


	// ── getAssemblyColModel ────────────────────────────────────────────────
	/*
	 * @Override public JSONObject getAssemblyColModel() throws Exception { try { if
	 * (fnCallApi == null) { throw new
	 * Exception("FunctionCallApi not initialized."); }
	 * 
	 * CommonFilter colFilter = new CommonFilter(); // ISGETCOL=Y tells the function
	 * to return column definitions only (no data rows) colFilter.setViewClick('Y');
	 * 
	 * List<String> paramValues = new ArrayList<>(); String condParms =
	 * FilterCondSql.getAbnRelatedConditionStr(colFilter); String commonParams =
	 * FilterCondSql.getGridCommonParams(colFilter);
	 * 
	 * paramValues.add(condParms); paramValues.add(commonParams);
	 * 
	 * List<String[]> gridList = fnCallApi.callFunction( "BAL_GEN_FN_ASSEMBLYMST",
	 * paramValues, 3, false);
	 * 
	 * JqGridTableModel jqGridTableModel = new JqGridTableModel(); GridColModel
	 * gridColModel = new GridColModel(); gridColModel.setHeaderNum(1);
	 * jqGridTableModel.setSortable(false); jqGridTableModel.setTableButton(true);
	 * jqGridTableModel.setEnableFilter(true); jqGridTableModel.setRowNumbers(true);
	 * 
	 * String[] colHeaderHead = gridList.get(0); String[] colHeader =
	 * gridList.get(1); List<String[]> headers = new ArrayList<>();
	 * headers.add(colHeader);
	 * 
	 * JSONObject colModel = UIUtils.getTableModel( headers, colHeaderHead,
	 * jqGridTableModel, gridColModel);
	 * 
	 * return colModel;
	 * 
	 * } catch (Exception e) { CommonMessage.debugMsg("getAssemblyColModel error: "
	 * + e.getMessage()); throw new Exception(e.getMessage()); } }
	 */
	
	/*
	 * @Override public JSONObject getAssemblyColModel() throws Exception { try { if
	 * (fnCallApi == null) { throw new
	 * Exception("FunctionCallApi not initialized."); }
	 * 
	 * CommonFilter colFilter = new CommonFilter(); colFilter.setViewClick('N'); //
	 * reset to N
	 * 
	 * List<String> paramValues = new ArrayList<>(); String condParms =
	 * FilterCondSql.getAbnRelatedConditionStr(colFilter);
	 * 
	 * // Hardcode ISGETCOL=Y directly — don't rely on setViewClick String
	 * commonParams = "FILTERCOND=;ISTOTALCNT=N;GRIDFILTER=;ISGETCOL=Y;";
	 * 
	 * paramValues.add(condParms); paramValues.add(commonParams);
	 * 
	 * List<String[]> gridList = fnCallApi.callFunction( "BAL_GEN_FN_ASSEMBLYMST",
	 * paramValues, 3, false); //debug for (int i = 0; i < gridList.size(); i++) {
	 * CommonMessage.debugMsg("gridList[" + i + "] = " +
	 * java.util.Arrays.toString(gridList.get(i))); }
	 * 
	 * JqGridTableModel jqGridTableModel = new JqGridTableModel(); GridColModel
	 * gridColModel = new GridColModel(); gridColModel.setHeaderNum(1);
	 * jqGridTableModel.setSortable(false); jqGridTableModel.setTableButton(true);
	 * jqGridTableModel.setEnableFilter(true); jqGridTableModel.setRowNumbers(true);
	 * 
	 * String[] colHeaderHead = gridList.get(0); String[] colHeader =
	 * gridList.get(1); List<String[]> headers = new ArrayList<>();
	 * headers.add(colHeader);
	 * 
	 * JSONObject colModel = UIUtils.getTableModel( headers, colHeaderHead,
	 * jqGridTableModel, gridColModel);
	 * 
	 * return colModel;
	 * 
	 * } catch (Exception e) { CommonMessage.debugMsg("getAssemblyColModel error: "
	 * + e.getMessage()); throw new Exception(e.getMessage()); } }
	 */


	// ── getAssemblyCount ───────────────────────────────────────────────────
	/*
	 * @Override public int getAssemblyCount(CommonFilter commonFilter) throws
	 * Exception { // The count is set inside commonFilter.totalRecordCnt by
	 * getAssemblyList() // This method is a convenience wrapper used by the servlet
	 * return (int) commonFilter.getTotalRecordCnt(); }
	 */

	/*
	 * @Override public List<String[]> getAssemblyGridData(CommonFilter
	 * commonFilter) throws Exception { // TODO Auto-generated method stub return
	 * null; }
	 */
	
	// end here
}

