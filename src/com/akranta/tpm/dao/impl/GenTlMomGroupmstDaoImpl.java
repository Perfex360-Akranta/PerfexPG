package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlMomGroupmstDao;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMomGroupdtlSql;
import com.akranta.tpm.dao.sql.GenTlMomGroupmstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMomGroupdtl;
import com.akranta.tpm.model.GenTlMomGroupmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.EmployeeGroupServiceApi;

/* dao implementation */
public class GenTlMomGroupmstDaoImpl implements GenTlMomGroupmstDao {

	private DBActionTemplate dbActionTemplate;
	private EmployeeGroupServiceApi employeegroupserviceapi;
	FunctionCallApi fnCallApi;
	public GenTlMomGroupmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void GenTlMomGroupmstDaoImplJwt(String JwtToken) 
	{
		try{
			employeegroupserviceapi = new EmployeeGroupServiceApi(JwtToken);
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

	public GenTlMomGroupmst create(GenTlMomGroupmst genTlMomGroupmst) 	throws BusinessApplicationExceptions, Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlMomGroupmstSql genTlMomGroupmstSql = new GenTlMomGroupmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlMomGroupdtlSql genTlMomGroupdtlSql = new GenTlMomGroupdtlSql();
		try{
			genTlMomGroupmst.setMgrmKeyid(dbActionTemplate.getSequenceNumber(GenTlMomGroupmstSql.TBL_GEN_TL_MOM_GROUPMST)); // set the sequnce number 
			genTlMomGroupmst.setMgrmName(genTlMomGroupmst.getMgrmName());
			sqls.add(GenTlMomGroupmstSql.getInsertSql(genTlMomGroupmstSql.getMgrmDbFields(), genTlMomGroupmst.getSaveArray())); // add insert sql for master table
			List<GenTlMomGroupdtl> gentlMomGroupdtl=genTlMomGroupmst.getGroupMemberDetail();
			if(gentlMomGroupdtl != null && gentlMomGroupdtl.size()>0)
			{
				for(GenTlMomGroupdtl genTlMomGroupdtl : gentlMomGroupdtl)
				{	
					genTlMomGroupdtl.setMgrdKeyid(dbActionTemplate.getSequenceNumber(GenTlMomGroupdtlSql.TBL_GEN_TL_MOM_GROUPDTL));
					genTlMomGroupdtl.setMgrdMgrmKeyid(genTlMomGroupmst.getMgrmKeyid());
					sqls.add(GenTlMomGroupdtlSql.getInsertSql(genTlMomGroupdtlSql.getMgrdDbFields(),genTlMomGroupdtl.getSaveArray()));
				}
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of
		} catch (BusinessApplicationExceptions b) {

			throw new BusinessApplicationExceptions(b.getMessage());

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return genTlMomGroupmst;
	}	
	
	public GenTlMomGroupmst update(GenTlMomGroupmst genTlMomGroupmst)	throws Exception { 
		List<String> sqls = new ArrayList<String>();
		GenTlMomGroupmstSql genTlMomGroupmstSql = new GenTlMomGroupmstSql();
		GenTlMomGroupdtlSql genTlMomGroupdtlSql= new GenTlMomGroupdtlSql();
		
	  try {
			sqls.add(GenTlMomGroupmstSql.getUpdateSql(genTlMomGroupmstSql.getMgrmDbFields(), genTlMomGroupmst.getSaveArray()));
			List<GenTlMomGroupdtl> genTlMomGroupdtl=genTlMomGroupmst.getGroupMemberDetail();
			if(genTlMomGroupdtl != null && genTlMomGroupdtl.size()>0 )
			{
				for(GenTlMomGroupdtl gentlMomGroupdtl : genTlMomGroupdtl)
				{	
					gentlMomGroupdtl.setMgrdKeyid(dbActionTemplate.getSequenceNumber(GenTlMomGroupdtlSql.TBL_GEN_TL_MOM_GROUPDTL));
					gentlMomGroupdtl.setMgrdMgrmKeyid(genTlMomGroupmst.getMgrmKeyid());
					sqls.add(GenTlMomGroupdtlSql.getInsertSql(genTlMomGroupdtlSql.getMgrdDbFields(),gentlMomGroupdtl.getSaveArray()));
				}
			}
			sqls.add(GenTlMomGroupmstSql.getUpdateSql(genTlMomGroupmstSql.getMgrmDbFields(), genTlMomGroupmst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return genTlMomGroupmst;
	}
	
	public GenTlMomGroupmst delete(GenTlMomGroupmst genTlMomGroupmst)throws Exception {
		List<String> sqls = new ArrayList<String>();
		GenTlMomGroupmstSql genTlMomGroupmstSql = new GenTlMomGroupmstSql();
		try {
			sqls.add("Delete from " +GenTlMomGroupdtlSql.TBL_GEN_TL_MOM_GROUPDTL +" where  MGRD_MGRM_KEYID ='"+ genTlMomGroupmst.getMgrmKeyid()+"'");
			sqls.add(GenTlMomGroupmstSql.getDeleteSql(genTlMomGroupmstSql.getMgrmDbFields(), genTlMomGroupmst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlMomGroupmst;
	}

		
	@Override
	public List<String[]> getEmpgroupCreationmstGridData(CommonFilter commonFilter,CommonParams commonparams)throws Exception {
		/*
		 * String functional=commonparams.getFlid(); String
		 * mstkeyid=commonparams.getKeyid(); String innerSql =
		 * GenTlMomGroupmstSql.getempGridData(functional,mstkeyid);
		 * CommonMessage.debugMsg("TEMP SQL :"+innerSql); String countSql =
		 * CommonFilterSqls.countSql(innerSql,commonparams.getGridFilters()); String
		 * viewinfo = dbActionTemplate.getSingleValue(countSql); long counts =
		 * Long.parseLong(viewinfo); if (counts > 0) { String sql =
		 * CommonFilterSqls.addPaginationParams(innerSql,commonparams);
		 * commonparams.setTotalRecordCnt(counts); CommonMessage.debugMsg("Print "+sql);
		 * List<String[]> addCommonLo = dbActionTemplate.getDataList(sql); return
		 * addCommonLo; } throw new NoDataFoundException("No Data Found");
		 */

		
		String functional=commonparams.getFlid();
		String mstkeyid=commonparams.getKeyid();
		
		List<String> paramValues = new ArrayList<String>();		
		String condParams = "";
		String commonParams = "";
		
		if(UIUtils.isValidKeyId(functional)){
			condParams +="FNLN_KEYID="+functional+";";
		}
		if(UIUtils.isValidKeyId(mstkeyid)){
			condParams +="MGRM_KEYID="+mstkeyid+";";
		}
		
		
		
		if(commonparams.getGridFilters()!=null){
			//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
			commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(commonparams.getGridFilters())+";";
		}
		
		commonParams +="FROMTOROW="+commonparams.getFromRow()+" AND "+commonparams.getToRow()+";";
		
		paramValues.add(condParams);
		paramValues.add(commonParams);
		
		//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
				List<String[]> result =  fnCallApi.callFunction("gen_fn_empgrouprolegrid_sb", paramValues,2,false);
				
					String totalCnt = paramValues.get(0); 
					CommonMessage.debugMsg("totalCnt..."+totalCnt);
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					
					if(  isInteger )
					{
						long counts=Long.parseLong(totalCnt);
						commonparams.setTotalRecordCnt(counts);
					}
				
					
					
					return result;
		
		
		
		
		
		
		
		
	}
	   
	@Override
	public List<String[]> getGroupGridData(CommonFilter commonFilter,CommonParams commonparams)throws Exception {
		
		/*
		 * String mstkeyid=commonparams.getKeyid(); String
		 * innerSql=GenTlMomGroupmstSql.getGroupGridDataSql(mstkeyid);
		 * CommonMessage.debugMsg("TEMP SQL :"+innerSql); String countSql =
		 * CommonFilterSqls.countSql(innerSql,commonparams.getGridFilters());
		 * CommonMessage.debugMsg("mcount"+countSql); String viewinfo =
		 * dbActionTemplate.getSingleValue(countSql); long counts =
		 * Long.parseLong(viewinfo); if (counts > 0) { String sql =
		 * CommonFilterSqls.addPaginationParams(innerSql,commonparams);
		 * CommonMessage.debugMsg ("msqlm"+sql); commonparams.setTotalRecordCnt(counts);
		 * List<String[]> addCommonLo = dbActionTemplate.getDataList(sql); return
		 * addCommonLo; } else { throw new NoDataFoundException("No Data Found"); }
		 */

		String mstkeyid=commonparams.getKeyid();
		
		List<String> paramValues = new ArrayList<String>();		
		String condParams = "";
		String commonParams = "";
		
		if(UIUtils.isValidKeyId(mstkeyid)){
			condParams +="MGRM_KEYID="+mstkeyid+";";
		}
		
		if(commonparams.getGridFilters()!=null){
			//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
			commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(commonparams.getGridFilters())+";";
		}
		
		commonParams +="FROMTOROW="+commonparams.getFromRow()+" AND "+commonparams.getToRow()+";";
		
		paramValues.add(condParams);
		paramValues.add(commonParams);
		
		//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
				List<String[]> result =  fnCallApi.callFunction("gen_fn_empgroupdetailgrid_sb", paramValues,2,false);
				
					String totalCnt = paramValues.get(0); 
					CommonMessage.debugMsg("totalCnt..."+totalCnt);
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					
					if(  isInteger )
					{
						long counts=Long.parseLong(totalCnt);
						commonparams.setTotalRecordCnt(counts);
					}
				
					
					
					return result;
		
		
		
		
		
		
		
		
		
	}

	@Override
	public List<String[]> getEmpgroupViewGridData(GridParams gridparams)throws Exception {
		/*
		 * String innerSql = GenTlMomGroupmstSql.getEmpgroupViewGridData(); String
		 * countSql = CommonFilterSqls.countSql(innerSql,gridparams.getGridFilters());
		 * String viewinfo = dbActionTemplate.getSingleValue(countSql); long counts =
		 * Long.parseLong(viewinfo); if (counts > 0) {
		 * 
		 * String sql = CommonFilterSqls.addPaginationParams(innerSql,gridparams);
		 * List<String[]> addCommonLo = dbActionTemplate.getDataList(sql);
		 * CommonMessage.debugMsg("Native Query "+sql); return addCommonLo; } throw new
		 * NoDataFoundException("No Data Found");
		 */

		List<String> paramValues = new ArrayList<String>();		
		String condParams = "";
		String commonParams = "";
		
		
		if(gridparams.getGridFilters()!=null){
			//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
			commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridparams.getGridFilters())+";";
		}
		
		commonParams +="FROMTOROW="+gridparams.getFromRow()+" AND "+gridparams.getToRow()+";";
		
		
		
		paramValues.add(condParams);
		paramValues.add(commonParams);
		
		//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_usermaingrid_sb", paramValues);
		List<String[]> result =  fnCallApi.callFunction("gen_fn_empgroupmaingrid_sb", paramValues,2,false);
		
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			
			if(  isInteger )
			{
				long counts=Long.parseLong(totalCnt);
				gridparams.setTotalRecordCnt(counts);
			}
		
			
			
			return result;
		 
	                   
	}

	@Override
	public GenTlMomGroupmst getGridValues(String keyid) throws Exception {
		GenTlMomGroupmst genTlMomGroupmst = new GenTlMomGroupmst();
		String sql = GenTlMomGroupmstSql.getGroupmstSelectSql();
		Object[] args = new Object[] { keyid };
		genTlMomGroupmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlMomGroupmst;
	}

	@Override
	public GenTlMomGroupmst RemoveGroupMember(String keyid) throws BusinessApplicationExceptions,Exception {
		GenTlMomGroupmst genTlMomGroupmst = new GenTlMomGroupmst();
		List<String> sqls = new ArrayList<String>();
		sqls.add(GenTlMomGroupdtlSql.DeleteGroupMember(keyid));
		dbActionTemplate.executeStatements(sqls);
	    return genTlMomGroupmst;
	}
	
	
	public Workbook getempGroupViewExcel(GridParams gridparams,
			JSONObject colModel, String format) throws Exception {
	     	ResultSet rs = null;
		   try{	
			    rs = getGroupDetailResultSet(gridparams);
				ExcelUtils excelUtils = new ExcelUtils(colModel);
				return excelUtils.writeToExcel(rs,format, 0,1,0 );
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}

	@Override
	public Workbook getempGroupDetailExcel(GridParams gridparams,
			JSONObject colModel, String format,String keyid) throws Exception {
	     	ResultSet rs = null;
		   try{	
			    rs = getGroupMemberResultSet(gridparams,keyid);
				ExcelUtils excelUtils = new ExcelUtils(colModel);
				return excelUtils.writeToExcel(rs,format, 0,2,0 );
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}

	private ResultSet getGroupMemberResultSet(GridParams gridparams,String keyid) throws Exception {
		String mstkeyid=keyid;
		String innerSql = GenTlMomGroupmstSql.getGroupDetailGridData(mstkeyid);
		String sql = CommonFilterSqls.addPaginationParams(innerSql,gridparams);
		ResultSet addCommonLo = dbActionTemplate.getData(sql);
		return addCommonLo;
	}

	private ResultSet getGroupDetailResultSet(GridParams gridparams) throws Exception {
		String innerSql =GenTlMomGroupmstSql.getEmpgroupViewGridData();
		String sql = CommonFilterSqls.addPaginationParams(innerSql, gridparams); 
		ResultSet addCommonLo=dbActionTemplate.getData(sql);
	    return addCommonLo;
	}
			
}

