package com.akranta.tpm.dao.impl;




import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_WomTlOthercostplanDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.BAL_WomTlOthercostplanSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_WomTlOthercostplan;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class BAL_WomTlOthercostplanDaoImpl implements BAL_WomTlOthercostplanDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_WomTlOthercostplanDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_WomTlOthercostplan create(BAL_WomTlOthercostplan womTlOthercostplan) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_WomTlOthercostplanSql womTlOthercostplanSql = new BAL_WomTlOthercostplanSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		sqls.add(BAL_WomTlOthercostplanSql.getInsertSql(womTlOthercostplanSql.getOtcpDbFields(), womTlOthercostplan.getSaveArray())); // add insert sql for master table
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return womTlOthercostplan;
	}
	
	public BAL_WomTlOthercostplan update(BAL_WomTlOthercostplan womTlOthercostplan)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_WomTlOthercostplanSql womTlOthercostplanSql = new BAL_WomTlOthercostplanSql();
		
		sqls.add(BAL_WomTlOthercostplanSql.getUpdateSql(womTlOthercostplanSql.getOtcpDbFields(), womTlOthercostplan.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
			
		return womTlOthercostplan;
	}
	
	public BAL_WomTlOthercostplan delete(BAL_WomTlOthercostplan womTlOthercostplan)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_WomTlOthercostplanSql womTlOthercostplanSql = new BAL_WomTlOthercostplanSql();
		try {
			
			sqls.add(BAL_WomTlOthercostplanSql.getDeleteSql(womTlOthercostplanSql.getOtcpDbFields(), womTlOthercostplan.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlOthercostplan;
	}

	public List<String []> getOtherCost(String otherId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Inside empcost daoimpl"+otherId);
		
		String sql ="";		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append ("	SELECT OTCM_SHORTNAME, OTCM_COSTNAME  ");
		buffer.append ( " FROM WOM_TL_OTHERCOSTMST "); 
		buffer.append ( " WHERE  OTCM_ACTIVE ='Y' AND OTCM_KEYID ='"+otherId+"' ");
		
		sql = buffer.toString();
		//String empCost =dbActionTemplate.getSingleValue(sql);
		List<String[]> otherDetails = dbActionTemplate.getDataList(sql);
		System.out.println("Inside empcost value"+otherDetails.size());
		
		return otherDetails;
	}

	public Workbook getAllCostInfoExcel(CommonFilter commonFilter,JSONObject colmodel, String rptformat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getCostInfoReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptformat, 0,0,0 );
			
		   }finally{
			   if(rs != null)
			      DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getCostInfoReportResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);		
		//dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_COSTINFORMATION", paramValues);
		return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_COSTINFORMATION", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		
List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}	


}

