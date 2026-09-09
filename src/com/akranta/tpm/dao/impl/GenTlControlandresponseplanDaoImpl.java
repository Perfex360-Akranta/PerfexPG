package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.GenTlControlandresponseplanDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlControlandresponseplanSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlControlandresponseplan;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class GenTlControlandresponseplanDaoImpl implements GenTlControlandresponseplanDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlControlandresponseplanDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlControlandresponseplan create(GenTlControlandresponseplan genTlControlandresponseplan) 	throws Exception {

		CommonMessage.debugMsg(" Inside Dao Impl ");
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlControlandresponseplanSql genTlControlandresponseplanSql = new GenTlControlandresponseplanSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			CommonMessage.debugMsg(" Inside Dao Impl:: try block ");
			genTlControlandresponseplan.setCarpKeyid(dbActionTemplate.getSequenceNumber("GEN_TL_CONTROLANDRESPONSEPLAN", 10, "CARP", "", "Y")); // set the sequnce number 
			sqls.add(GenTlControlandresponseplanSql.getInsertSql(genTlControlandresponseplanSql.getCarpDbFields(), genTlControlandresponseplan.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlControlandresponseplan;
	}
	
	public GenTlControlandresponseplan update(GenTlControlandresponseplan genTlControlandresponseplan)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlControlandresponseplanSql genTlControlandresponseplanSql = new GenTlControlandresponseplanSql();
		try {

			sqls.add(GenTlControlandresponseplanSql.getUpdateSql(genTlControlandresponseplanSql.getCarpDbFields(), genTlControlandresponseplan.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlControlandresponseplan;
	}
	
	public GenTlControlandresponseplan delete(GenTlControlandresponseplan genTlControlandresponseplan)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlControlandresponseplanSql genTlControlandresponseplanSql = new GenTlControlandresponseplanSql();
		try {
			
			sqls.add(genTlControlandresponseplanSql.getDeleteSql(genTlControlandresponseplanSql.getCarpDbFields(), genTlControlandresponseplan.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlControlandresponseplan;
	}

	@Override
	public GenTlControlandresponseplan getAllFillControl(String controlId)
			throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(" Inside DaoImpl :: Keyid "+controlId);
		GenTlControlandresponseplan genTlUnsafeworkpractice = new GenTlControlandresponseplan();
		String sql = GenTlControlandresponseplanSql.getunsafedata();
		Object args[] = new Object[] {controlId};
		genTlUnsafeworkpractice.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		return genTlUnsafeworkpractice;
	}

	@Override
	public Workbook getConresplnExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		       // TODO Auto-generated method stub
		       ResultSet rs = null;
		       CommonMessage.debugMsg(" Inside Dao Impl :: Outside ");
		 try{
			    CommonMessage.debugMsg(" Inside Dao Impl :: Inside ");
				rs =   getControlresplanResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				
				return excelUtils.writeToExcel(rs,format, 2,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		}

		private ResultSet getControlresplanResultSet(CommonFilter commonFilter) throws Exception {
			// TODO Auto-generated method stub
			
			CommonMessage.debugMsg(" Inside Dao Impl :: Inside Result Set ");
			
			//List<String> paramValues = getFilterParamValues();
			/*StringBuffer sql= new StringBuffer();
			  sql.append("SELECT Carp_Keyid AS \"KeyID\",");
				sql.append("Carp_Processstep AS \"Process Step By\",");
				sql.append("Carp_Kpov AS \"KPOV\" ,Carp_Uom AS \"UOM\",");
				sql.append("Carp_Speclimits As \"Spec Limits\",");
				sql.append("Carp_Controllimits  AS \"Control Limits\","); 
				sql.append("Carp_Measurementmethod AS \"Measurement\",");
				sql.append("Carp_Samplesize AS \"Sample Size\",");
				sql.append("Carp_Frequency AS \"Frequency\",");
				sql.append("Carp_Whomeasures AS \"Who Measures\",");
				sql.append("Carp_Whererecorded AS \"Where It Is\",");
				
				sql.append("Carp_Decisionrule AS \"Decision Rule To\",");
				sql.append("Carp_Informto AS \"Inform To\",");
				sql.append("Carp_Correctiveaction AS \"Corrective\"");
				sql.append(" FROM "); 
				sql.append("GEN_TL_CONTROLANDRESPONSEPLAN" );
	        sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		    CommonMessage.debugMsg("sql..."+sql);
	       
			return dbActionTemplate.getData(sql.toString());
		//	return null;
			//return null;*/
			
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			paramValues.add(condParms);
			paramValues.add(commonParams);
	        return dbActionTemplate.NewdbFunctionCall2("QTM_FN_CONDITIONANADRESPONSE", paramValues);
			
		}
	
}

