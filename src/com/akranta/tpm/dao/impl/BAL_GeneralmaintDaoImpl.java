package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_GeneralmaintDao;
//import com.akranta.tpm.dao.sql.BAL_BreakDownSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.BAL_GeneralMaintenanceServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class BAL_GeneralmaintDaoImpl implements BAL_GeneralmaintDao {

	private DBActionTemplate dbActionTemplate; 
	private BAL_GeneralMaintenanceServiceApi GeneralMaintenanceServiceApi;
	FunctionCallApi fnCallApi;
	
	public BAL_GeneralmaintDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void BAL_GeneralmaintDaoImplJwt(String JwtToken) {
	    try {
			GeneralMaintenanceServiceApi = new BAL_GeneralMaintenanceServiceApi(JwtToken);
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public List<String[]> getAllgeneralmanit(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
					
			//List<String> paramValues = getFilterParamValues(commonFilter);
			List<String> paramValues  = new ArrayList<String>();
			String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			 /***for setup and adjustment report**/
			 if("S".equals(commonFilter.getWostatus()))
					condParms +="ACTIVITYTYPE=S";
			 String sbuId = commonFilter.getSect();
			 if(sbuId != null && !sbuId.trim().isEmpty() && !sbuId.equals("null")){
			     condParms += "FACTORYID=" + sbuId + ";";
			 }
			 paramValues.add(condParms);
			 paramValues.add(commonParams);

			
			 
			 //String sbuId = request.getParameter("cmbSbuid");

			//List<String[]> genmaintList = dbActionTemplate.processFunctionCallsWithColHeaders("PLM_PC_PLANNEDMAINT.PLM_FN_GENERALMAINT", paramValues);	
			List<String[]> genmaintList = dbActionTemplate.processFunctionCallsWithColHeaders("PLM_FN_GENERALMAINT", paramValues);
			//List<String[]> dataList = fnCallApi.callFunction("PLM_FN_GENERALMAINT",paramValues,3,true);

			System.out.println(" ::: List  " + genmaintList.size());
			return genmaintList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
}
	@Override
	public Workbook getGenMaintRpt(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		   ResultSet rs = null;
		   try{
			
			rs =   getGenMaintRptResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 3,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getGenMaintRptResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		//return dbActionTemplate.dbFunctionCall("PLM_PC_PLANNEDMAINT.PLM_FN_GENERALMAINT", paramValues);
		return dbActionTemplate.dbFunctionCall("PLM_FN_GENERALMAINT", paramValues);
		//List<String[]> dataList = fnCallApi.callFunction("PLM_FN_GENERALMAINT",paramValues,3,true);
		//return null;

		
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		 paramValues.add(condParms);
		 paramValues.add(commonParams);
				
		return paramValues;
	}	
	public List<String[]> getSapInfoList(CommonFilter commFilter) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("getSapInfoListdaoimp..."+commFilter );
		StringBuffer sql = new StringBuffer();
		String sql1="";
		String keyid = commFilter.getKey();
		System.out.println("keyid.."+keyid);
		sql.append(" select * from ( " );//'Model','Quantity'
		
		/////
		sql.append(" select * from ( " );
		sql.append(" select 'KeyId','Select','Part No','Spare Name','Spare Loc','Model','Quantity','Rate','Avl Stock','hdnCheckSel',0 as dataorder from dual ");
		sql.append(" UNION ALL SELECT SSPM_KEYID,'',TO_CHAR(SSPM_SPARENO),SSPM_SPARENAME,SSPM_STORAGELOCATION,SPRM_MODEL,TO_CHAR(SSPM_QUANTITY),TO_CHAR(SSPM_RATE),SSPM_AVAILABLESTOCK ");
		sql.append(" ,'',1 as dataorder FROM  SAP_TL_SPARESREPLACED,gen_tl_sparesmst ");//gen_tl_sparesmst
	    sql.append(" WHERE 1=1  AND TO_CHAR(SSPM_SPARENO) = SPRM_PARTNO (+) ");
	    sql.append(" AND SSPM_DOCNUMBER = '"+keyid+"' ");
	    sql.append(" ) order by dataorder) ");
	    
		
		
		/////
	    /*
		sql.append(" select 'KeyId','Select','Part No','Spare Name','Spare Loc','Planned Quantity','Actual Quantity','Rate','Avl Stock','hdnCheckSel',0 as dataorder from dual ");
		sql.append(" UNION ALL SELECT SSPM_KEYID,'',TO_CHAR(SSPM_SPARENO),SSPM_SPARENAME,SSPM_STORAGELOCATION,SPRM_MODEL,TO_CHAR(SSPM_QUANTITY),TO_CHAR(SSPM_RATE),SSPM_AVAILABLESTOCK ");
		sql.append(" ,'',1 as dataorder FROM  SAP_TL_SPARESREPLACED,gen_tl_sparesmst ");//gen_tl_sparesmst
	    sql.append(" WHERE 1=1  AND TO_CHAR(SSPM_SPARENO) = SPRM_PARTNO (+) ");
	    sql.append(" AND SSPM_DOCNUMBER = '"+keyid+"' ");
	    sql.append(" ) order by dataorder ");	*/    
		// TODO Auto-generated method stub
		System.out.println("sql... in  daoimpl" + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());

		CommonFunctions.debugMsg("GRIDDATA.SIZE"+gridData.size());
		//CommonFunctions.debugMsg("GRIDDATA.SIZE"+gridData);
		return gridData;
	}
	

}