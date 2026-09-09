package com.akranta.tpm.dao.impl;




import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlSelfnominationmstDao;
import com.akranta.tpm.dao.sql.EntTlSelfnominationmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSelfnominationmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class EntTlSelfnominationmstDaoImpl implements EntTlSelfnominationmstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlSelfnominationmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public List<EntTlSelfnominationmst> create(List<EntTlSelfnominationmst> NominationList1,String nominatonDelete) 	throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlSelfnominationmstSql entTlSelfnominationmstSql = new EntTlSelfnominationmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		//EntTlSelfnominationmst entTlSelfnominationmst=new EntTlSelfnominationmst();
		try{	   
			if(UIUtils.isValidKeyId(nominatonDelete)){	
				StringBuffer sql1 = new StringBuffer();
				nominatonDelete=nominatonDelete.replace("[\"", "'").replace("\"]","'");
				nominatonDelete=nominatonDelete.replaceAll("\",\"", "','");
				
				sql1.append(" DELETE FROM ENT_TL_SELFNOMINATIONMST ");
				sql1.append(" WHERE SNOM_KEYID in (" + nominatonDelete + ") ");	
				sqls.add(sql1.toString());
			}	
			if(NominationList1!=null){
				for(EntTlSelfnominationmst entTSelfnominationmst:NominationList1){				
					if(entTSelfnominationmst.getSnomKeyid()== null){
					   entTSelfnominationmst.setSnomKeyid(dbActionTemplate.getSequenceNumber(EntTlSelfnominationmstSql.TBL_ENT_TL_SELFNOMINATIONMST, 10, "SNOM", "", "")); // set the sequnce number 
					   CommonMessage.debugMsg("save entry:: "+entTSelfnominationmst.getSnomKeyid());
					   sqls.add(EntTlSelfnominationmstSql.getInsertSql(entTlSelfnominationmstSql.getSnomDbFields(), entTSelfnominationmst.getSaveArray())); // add insert sql for master table
					}
				   else{
				      sqls.add(EntTlSelfnominationmstSql.getUpdateSql(entTlSelfnominationmstSql.getSnomDbFields(), entTSelfnominationmst.getSaveArray()));
	      			}
				}	
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return NominationList1;
	}
	
	public  List<EntTlSelfnominationmst> update(List<EntTlSelfnominationmst> NominationList1,List<EntTlSelfnominationmst> nominationDelete)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlSelfnominationmstSql entTlSelfnominationmstSql = new EntTlSelfnominationmstSql();
		try {
			
			   CommonMessage.debugMsg("nominatonDelete "+nominationDelete);
				for(EntTlSelfnominationmst entTSelfnominationmst:nominationDelete)
				{
					if(entTSelfnominationmst.getSnomKeyid()!= null){
					   sqls.add(entTlSelfnominationmstSql.getDeleteSql(entTlSelfnominationmstSql.getSnomDbFields(), entTSelfnominationmst.getSaveArray()));
				    }
				}
				for(EntTlSelfnominationmst entTSelfnominationmst:NominationList1){				
					if(entTSelfnominationmst.getSnomKeyid()== null){
					   entTSelfnominationmst.setSnomKeyid(dbActionTemplate.getSequenceNumber(EntTlSelfnominationmstSql.TBL_ENT_TL_SELFNOMINATIONMST, 10, "SNOM", "", "")); // set the sequnce number 
					   CommonMessage.debugMsg("save entry:: "+entTSelfnominationmst.getSnomKeyid());
					   sqls.add(EntTlSelfnominationmstSql.getInsertSql(entTlSelfnominationmstSql.getSnomDbFields(), entTSelfnominationmst.getSaveArray())); // add insert sql for master table
					}
				    else{
				      sqls.add(EntTlSelfnominationmstSql.getUpdateSql(entTlSelfnominationmstSql.getSnomDbFields(), entTSelfnominationmst.getSaveArray()));
	      			}
				}			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return NominationList1;
	}
	
	public List<EntTlSelfnominationmst> delete(List<EntTlSelfnominationmst> nominationList1)throws Exception {
		List<String> sqls = new ArrayList<String>();
		EntTlSelfnominationmstSql entTlSelfnominationmstSql = new EntTlSelfnominationmstSql();
		try {
			for(EntTlSelfnominationmst entTSelfnominationmst:nominationList1)
			{
			   sqls.add(entTlSelfnominationmstSql.getDeleteSql(entTlSelfnominationmstSql.getSnomDbFields(), entTSelfnominationmst.getSaveArray()));
			}
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return nominationList1;
	}

	@Override
	public Workbook SelfNominationExcel(CommonFilter commonFilter,JSONObject colmodel, String format,String empid) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{			
			rs =   getSelfNominationReport(commonFilter,empid);
			CommonMessage.debugMsg("   rs value::::::::");
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,2,0,0 );	
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}

	private ResultSet getSelfNominationReport(CommonFilter commonFilter,String empid) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();	
		String condParms =FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		
		paramValues.add(condParms+"EMPID="+empid);
		paramValues.add(commonParams); 
		
		return dbActionTemplate.dbFunctionCall("test_pc_testSKILLINDX.ENT_FN_SELFNOMINATIONMAINGRID", paramValues);
	}
	
}

