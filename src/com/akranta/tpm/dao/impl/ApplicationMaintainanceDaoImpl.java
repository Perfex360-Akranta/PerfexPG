package com.akranta.tpm.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.ApplicationMaintainanceDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class ApplicationMaintainanceDaoImpl implements ApplicationMaintainanceDao{
	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	public ApplicationMaintainanceDaoImpl(DBActionTemplate dbActionTemplate) throws Exception
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void ApplicationMaintainanceDaoImplJwt(String JwtToken) 
	{
		try{
	
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public DBActionTemplate getDbActionTemplate() {
		return this.dbActionTemplate;
	}
	public List<String[]> getEmployeeLocation(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		String EmpKey=commonFilter.getKey();
		String Location=commonFilter.getType();
		//CommonMessage.debugMsg("EmpKeyd"+EmpKey);
		StringBuffer sql = new StringBuffer();
		 if(UIUtils.isValidKeyId(EmpKey)){
		sql.append(" SELECT "); 
		sql.append("EMPM_KEYID AS \"EMPM_KEYID\",EMPM_CODE AS \"EMPM_CODE\",EMPM_NAME AS \"EMPM_NAME\",EMPM_EMPLOYEETYPE AS \"EMPM_EMPLOYEETYPE\",EMPM_LOCATION,LOCN_NAME,'','','' ");  
		sql.append(" FROM ");
		sql.append(" GEN_TL_EMPLOYEEMST,GEN_TL_LOCATIONMST"); 
		sql.append(" WHERE "); 
		sql.append(" 1=1   and  EMPM_LOCATION = LOCN_KEYID and EMPM_ACTIVE='Y'");
		
		if(UIUtils.isValidKeyId(EmpKey)){
			   sql.append(" AND EMPM_KEYID ='"+EmpKey+"' ");
			  }
		
		if(UIUtils.isValidKeyId(Location)){
			   sql.append(" AND EMPM_LOCATION ='"+Location+"' ");
			  }
	    sql.append(" ORDER BY ");
	    sql.append(" EMPM_NAME ASC ");
	   // CommonMessage.debugMsg("SQL is::::"+sql);
		 }
		 else{
			 sql.append(" SELECT "); 
				sql.append("EMPM_KEYID AS \"EMPM_KEYID\",EMPM_CODE AS \"EMPM_CODE\",EMPM_NAME AS \"EMPM_NAME\",EMPM_EMPLOYEETYPE AS \"EMPM_EMPLOYEETYPE\",EMPM_LOCATION,LOCN_NAME,'','','' ");  
				sql.append(" FROM ");
				sql.append(" GEN_TL_EMPLOYEEMST,GEN_TL_LOCATIONMST"); 
				sql.append(" WHERE "); 
				

				sql.append(" 1=1   and  EMPM_LOCATION = LOCN_KEYID and EMPM_ACTIVE='Y' ");

				if(UIUtils.isValidKeyId(Location)){
					   sql.append(" AND EMPM_LOCATION ='"+Location+"' ");
					  }
				

			    sql.append(" ORDER BY ");
			    sql.append(" EMPM_NAME ASC ");
			  //  CommonMessage.debugMsg("SQL is::::"+sql);
			 
		 }
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
	}
	
	public List<String[]> getEmployeeList(CommonFilter commonFilter) throws Exception{
		String EmpKey=commonFilter.getKey();
		//CommonMessage.debugMsg("EmpKey"+EmpKey);
		StringBuffer sql = new StringBuffer();
		 if(UIUtils.isValidKeyId(EmpKey)){
		sql.append(" SELECT "); 
		sql.append("EMPM_KEYID AS \"EMPM_KEYID\",EMPM_CODE AS \"EMPM_CODE\",EMPM_NAME AS \"EMPM_NAME\",EMPM_EMPLOYEETYPE AS \"EMPM_EMPLOYEETYPE\",LOCN_NAME,to_char(USRM_VALIDTILL,'DD-MM-YYYY'),USRM_REMARKS,'' ");  
		sql.append(" FROM ");
		sql.append(" GEN_TL_EMPLOYEEMST,GEN_TL_LOCATIONMST,ADM_TL_USERMST"); 
		sql.append(" WHERE "); 
		sql.append(" 1=1   and  EMPM_LOCATION = LOCN_KEYID and EMPM_ACTIVE='Y' AND EMPM_KEYID=USRM_CCNO");
		if(UIUtils.isValidKeyId(EmpKey)){
			   sql.append(" AND EMPM_KEYID ='"+EmpKey+"' ");
			  }
	    sql.append(" ORDER BY ");
	    sql.append(" EMPM_NAME ASC ");
	   // CommonMessage.debugMsg("SQL is::::"+sql);
		 }
		 else{
			 sql.append(" SELECT "); 
				sql.append("EMPM_KEYID AS \"EMPM_KEYID\",EMPM_CODE AS \"EMPM_CODE\",EMPM_NAME AS \"EMPM_NAME\",EMPM_EMPLOYEETYPE AS \"EMPM_EMPLOYEETYPE\",LOCN_NAME,to_char(USRM_VALIDTILL,'DD-MM-YYYY'),USRM_REMARKS,''");  
				sql.append(" FROM ");
				sql.append(" GEN_TL_EMPLOYEEMST,GEN_TL_LOCATIONMST,ADM_TL_USERMST"); 
				sql.append(" WHERE "); 
				sql.append(" 1=1   and  EMPM_LOCATION = LOCN_KEYID and EMPM_ACTIVE='Y'  AND EMPM_KEYID=USRM_CCNO");
			    sql.append(" ORDER BY ");
			    sql.append(" EMPM_NAME ASC ");
			   // CommonMessage.debugMsg("SQL is::::"+sql);
		 }
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
	}

	public Object LocationTransfer(String EmpKeyid,String Location) throws Exception {
		StringBuffer sql =new StringBuffer();
		//CommonMessage.debugMsg("Employee EmpKeyid:::"+EmpKeyid);
			//CommonMessage.debugMsg("Else");
			//sql.append("UPDATE ADM_TL_USERMST SET USRM_ISACTIVE ='Y',USRM_REMARKS='"+remarks+"' WHERE USRM_CCNO='"+EmpKeyid+"' ");
			sql.append("DELETE  FROM GEN_TL_TEAMTRADELINK WHERE FRP_FRT_KEYID IN (SELECT FRT_KEYID FROM GEN_TL_FNLNROLETEAM WHERE  FRT_EMPM_KEYID='"+EmpKeyid+"' )");
	    	//CommonMessage.debugMsg("Dao sql:" +sql);
		try{
			dbActionTemplate.executeStatement(sql.toString());
			DeleteRoles(EmpKeyid,Location);
		}catch(Exception e){
		}
		return sql;	
	}
	private void DeleteRoles(String empKeyid,String Location ) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		StringBuffer sql =new StringBuffer();
		sql.append("DELETE  FROM GEN_TL_FNLNROLETEAM WHERE FRT_EMPM_KEYID='"+empKeyid+"' ");
    	//CommonMessage.debugMsg("Dao sql:" +sql);
    	ChangeLocation(empKeyid,Location);
		dbActionTemplate.executeStatement(sql.toString());
	}
	
	private void ChangeLocation(String empKeyid,String Location ) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		StringBuffer sql =new StringBuffer();
		sql.append("UPDATE GEN_TL_EMPLOYEEMST SET EMPM_LOCATION='"+Location+"' WHERE EMPM_KEYID='"+empKeyid+"' ");
    	CommonMessage.debugMsg("Dao sql:" +sql);
	
		dbActionTemplate.executeStatement(sql.toString());
	}

	//Swetha - changed here for Application Maintenance
	public List<String[]> getEmpActiveData(GridParams gridParams,String location) throws Exception{
		
		
		/*
		 * String sql = getLocUserMasterGrid(location); String
		 * outerSql="select * from("+sql.toString()+")where 1=1 ";
		 * 
		 * if(gridParams.getGridFilters()!=null)
		 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
		 * String count="select count(*) from("+outerSql+")";
		 * ///CommonMessage.debugMsg("count in daoimpl"+count); String
		 * rowCount=dbActionTemplate.getSingleValue(count);
		 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
		 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
		 * List<String> params= new ArrayList<String>();
		 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
		 * 
		 * CommonMessage.debugMsg("Outer sql"+outerSql); String finalSql = "SELECT * FROM ("
		 * + "   SELECT ROW_NUMBER() OVER () AS slno, a.* " + "   FROM ( " + outerSql +
		 * " ) a " + ") t " +
		 * "WHERE slno >= CAST(? AS bigint) AND slno <= CAST(? AS bigint)";
		 * CommonMessage.debugMsg("finalSql in Dao"+finalSql);
		 * CommonMessage.debugMsg("grid "+gridParams.getFromRow());
		 * 
		 * List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
		 return result;*/
		
		List<String> paramValues = new ArrayList<String>();		
		String condParams = "";
		String commonParams = "";
		
		
		if(gridParams.getGridFilters()!=null){
			//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
			commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
		}
		
		commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
		
		
		
		paramValues.add(condParams);
		paramValues.add(commonParams);
		
		//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
		List<String[]> result =  fnCallApi.callFunction("app_fn_inactiveemployeegrid_sb", paramValues,2,true);
		
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			
			if(  isInteger )
			{
				long counts=Long.parseLong(totalCnt);
				gridParams.setTotalRecordCnt(counts);
			}
		
			
			
			return result;
}

//public String getLocUserMasterGrid(String location){
//		StringBuffer sql =new StringBuffer();
//		sql.append("SELECT EMPM_KEYID,LOCN_NAME AS EMPLOCATION,EMPM_NAME AS EMPLOYEENAME,EMPM_CODE AS EMPLOYEENUMBER,");
//		sql.append("DECODE(EMPM_EMPLOYEETYPE,'R','Regular','M','Manager','C','Contract','A','Associate','B','Badli','T','Trainee','E','Executive','O','Others') AS EMPTYPE,");
//		sql.append("USRM_LOGINID AS LOGINID,TO_CHAR(USRM_VALIDTILL,'DD-MON-YYYY') AS dteUsrm_validtill,USRM_REMARKS AS txtUsrm_remarks,'' ");
//		sql.append("FROM GEN_TL_EMPLOYEEMST,ADM_TL_USERMST,GEN_TL_LOCATIONMST WHERE EMPM_ACTIVE='N' ");
//		sql.append(" AND EMPM_KEYID=USRM_CCNO(+) AND LOCN_KEYID=EMPM_LOCATION ");
//		if(UIUtils.isValidKeyId(location))
//		    sql.append(" AND EMPM_LOCATION='"+location+"'");
//		sql.append(" order by USRM_USERNAME");
//		//CommonMessage.debugMsg("sql in loc user"+sql.toString());
//		return sql.toString();
//	}
	//Swetha changes - 24 Nov 2025-Application Maintenance
	public String getLocUserMasterGrid(String location) {
	    StringBuilder sql = new StringBuilder();
	    
	    sql.append("SELECT EMPM_KEYID, LOCN_NAME AS EMPLOCATION, EMPM_NAME AS EMPLOYEENAME, EMPM_CODE AS EMPLOYEENUMBER, ");
	    sql.append("CASE EMPM_EMPLOYEETYPE ");
	    sql.append("    WHEN 'R' THEN 'Regular' ");
	    sql.append("    WHEN 'M' THEN 'Manager' ");
	    sql.append("    WHEN 'C' THEN 'Contract' ");
	    sql.append("    WHEN 'A' THEN 'Associate' ");
	    sql.append("    WHEN 'B' THEN 'Badli' ");
	    sql.append("    WHEN 'T' THEN 'Trainee' ");
	    sql.append("    WHEN 'E' THEN 'Executive' ");
	    sql.append("    WHEN 'O' THEN 'Others' ");
	    sql.append("END AS EMPTYPE, ");
	    sql.append("USRM_LOGINID AS LOGINID, TO_CHAR(USRM_VALIDTILL, 'DD-MON-YYYY') AS dteUsrm_validtill, USRM_REMARKS AS txtUsrm_remarks, '' ");
	    sql.append("FROM GEN_TL_EMPLOYEEMST ");
	    sql.append("LEFT JOIN ADM_TL_USERMST ON EMPM_KEYID = USRM_CCNO ");
	    sql.append("INNER JOIN GEN_TL_LOCATIONMST ON LOCN_KEYID = EMPM_LOCATION ");
	    sql.append("WHERE EMPM_ACTIVE = 'N' ");
	    
	    if(UIUtils.isValidKeyId(location)) {
	        // Cast EMPM_LOCATION to text for comparison with character varying
	        sql.append(" AND EMPM_LOCATION::text = '").append(location).append("'");
	    }
	    
	    sql.append(" ORDER BY USRM_USERNAME");
	    
	    return sql.toString();
	}
	//Swetha changes - 24 Nov 2025-Application Maintenance
	
@Override
public void UpdateActive(String EmpKeyid,String ValidTill,String remarks,String EmpType) throws Exception {
	StringBuffer sql =new StringBuffer();
	//CommonMessage.debugMsg("Employee Type:::"+EmpType);
//
//	if(EmpType.equals("Contract") || EmpType.equals("Associate") || EmpType.equals("Badli")){
		//CommonMessage.debugMsg("Contract");
		sql.append("UPDATE ADM_TL_USERMST SET USRM_ISACTIVE ='Y',USRM_ISVALIDITYREQ='Y',USRM_REMARKS='"+remarks+"',USRM_VALIDTILL='"+ValidTill+"' WHERE USRM_CCNO='"+EmpKeyid+"' ");
//	}
//	else{
//		//CommonMessage.debugMsg("Else");
//		sql.append("UPDATE ADM_TL_USERMST SET USRM_ISACTIVE ='Y',USRM_REMARKS='"+remarks+"' WHERE USRM_CCNO='"+EmpKeyid+"' ");
//	}
    	//CommonMessage.debugMsg("Dao sql:" +sql);
	try{
		dbActionTemplate.executeStatement(sql.toString());
		UpdateEmployee(EmpKeyid);
	}catch(Exception e){
	}	
}

public void UpdateEmployeeInactive(String EmpKeyid,String remarks) throws Exception{
	StringBuilder sql=new StringBuilder();
	sql.append("UPDATE GEN_TL_EMPLOYEEMST SET EMPM_ACTIVE='N',empm_remarks='"+remarks+"' WHERE EMPM_KEYID='"+EmpKeyid+"' ");
	dbActionTemplate.executeStatement(sql.toString());
}

public void UpdateEmployee(String EmpKeyid) throws Exception{
	StringBuilder sql=new StringBuilder();
	sql.append("UPDATE GEN_TL_EMPLOYEEMST SET EMPM_ACTIVE='Y' WHERE EMPM_KEYID='"+EmpKeyid+"' ");
	dbActionTemplate.executeStatement(sql.toString());
}

public List<String[]> getKaizenData(GridParams gridParams,CommonFilter commonFilter) throws Exception{
	
	

	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	
	if(UIUtils.isValidKeyId(commonFilter.getFromDate()  )){
		condParams +="FROMDATE="+commonFilter.getFromDate()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getToDate())){
		condParams +="TODATE="+commonFilter.getToDate()+";";
	}
	
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParams +="DMT="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAwise())){
		condParams +="JH="+commonFilter.getAwise()+";";
	}
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
			List<String[]> result =  fnCallApi.callFunction("app_fn_kaizendate_sb", paramValues,2,true);
			
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				
				if(  isInteger )
				{
					long counts=Long.parseLong(totalCnt);
					gridParams.setTotalRecordCnt(counts);
				}
			
				
				
				return result;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
				/*
				 * 
				 * String sql = getKazienGrid(commonFilter); String
				 * outerSql="select * from("+sql.toString()+")where 1=1 ";
				 * if(gridParams.getGridFilters()!=null)
				 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				 * String count="select count(*) from("+outerSql+")";
				 * //CommonMessage.debugMsg("count in daoimpl"+count); String
				 * rowCount=dbActionTemplate.getSingleValue(count);
				 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
				 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
				 * List<String> params= new ArrayList<String>();
				 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
				 * //String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
				 * +outerSql+") a ) where slno >= ? and slno <= ?"; String finalSql =
				 * "SELECT * FROM (" + "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" +
				 * outerSql + ") a" +
				 * ") t WHERE slno >= CAST(? AS BIGINT) AND slno <= CAST(? AS BIGINT)";
				 * 
				 * //CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
				 * result=dbActionTemplate.getDataList(finalSql, params); return result;
				 */
}
//Swetha by November 25
public String getKazienGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();

//	if(fromDate !=null && toDate!=null){
//	  //  CommonMessage.debugMsg("If");
//		sql.append("SELECT KZNM_KEYID AS KznmKeyid,DMT AS txtDMT,JH AS txtJH,TO_CHAR (TO_DATE(KZNM_STARTDATE), 'DD-MON-YYYY') AS KznmStartdate,");
//		sql.append("TO_CHAR (TO_DATE(KZNM_ENDDATE), 'DD-MON-YYYY') AS KznmEnddate,TO_CHAR (TO_DATE(KZNM_DATE), 'DD-MON-YYYY') AS KznmDate,'' AS SUBMIT ");
//		sql.append("FROM KZN_TL_MST,gen_mv_flidhierarchy WHERE KZNM_DATE BETWEEN '"+fromDate+"' AND '"+toDate+"' AND KZNM_FLID=FLID(+) ");
//		
//		if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
//			sql.append("AND FLID='"+DMT+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(JH)){
//			//CommonMessage.debugMsg("Else If:"+sql.toString());
//			sql.append("AND FLID='"+JH+"' ");
//		}
//		
//		sql.append(" ORDER BY KZNM_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	}
//	else{
//	sql.append("SELECT KZNM_KEYID AS KznmKeyid,DMT AS txtDMT,JH AS txtJH,TO_CHAR (TO_DATE(KZNM_STARTDATE), 'DD-MON-YYYY') AS KznmStartdate,");
//	sql.append("TO_CHAR (TO_DATE(KZNM_ENDDATE), 'DD-MON-YYYY') AS KznmEnddate,TO_CHAR (TO_DATE(KZNM_DATE), 'DD-MON-YYYY') AS KznmDate,'' AS SUBMIT ");
//	sql.append("FROM KZN_TL_MST,gen_mv_flidhierarchy WHERE KZNM_DATE BETWEEN '01-Jan-1801' AND '31-DEC-2100' AND KZNM_FLID=FLID(+) ");
//	sql.append(" ORDER BY KZNM_KEYID DESC ");
//	//CommonMessage.debugMsg("sql Kaizen Data"+sql.toString());
//	
//	}
	
	if(fromDate != null && toDate != null){
	    sql.append("SELECT KZNM_KEYID AS KznmKeyid, DMT AS txtDMT, JH AS txtJH, TO_CHAR(KZNM_STARTDATE::DATE, 'DD-Mon-YYYY') AS KznmStartdate, ");
	    sql.append("TO_CHAR(KZNM_ENDDATE::DATE, 'DD-Mon-YYYY') AS KznmEnddate, TO_CHAR(KZNM_DATE::DATE, 'DD-Mon-YYYY') AS KznmDate, '' AS SUBMIT ");
	    sql.append("FROM KZN_TL_MST ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON KZNM_FLID=FLID ");
	    sql.append("WHERE KZNM_DATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    sql.append(" ORDER BY KZNM_KEYID DESC ");
	}
	else{
	    sql.append("SELECT KZNM_KEYID AS KznmKeyid, DMT AS txtDMT, JH AS txtJH, TO_CHAR(KZNM_STARTDATE::DATE, 'DD-Mon-YYYY') AS KznmStartdate, ");
	    sql.append("TO_CHAR(KZNM_ENDDATE::DATE, 'DD-Mon-YYYY') AS KznmEnddate, TO_CHAR(KZNM_DATE::DATE, 'DD-Mon-YYYY') AS KznmDate, '' AS SUBMIT ");
	    sql.append("FROM KZN_TL_MST ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON KZNM_FLID=FLID ");
	    sql.append("WHERE KZNM_DATE BETWEEN '1801-01-01' AND '2100-12-31' ");
	    sql.append(" ORDER BY KZNM_KEYID DESC ");
	}
	return sql.toString();
	}

//Swetha by November 25

public void UpdateKaizenDate(String KaizenKeyid,String KaizenDate) throws Exception{
	StringBuilder sql=new StringBuilder();
	//sql.append("UPDATE KZN_TL_MST SET KZNM_DATE=TO_DATE('"+KaizenDate+"') WHERE KZNM_KEYID='"+KaizenKeyid+"'");
	sql.append("UPDATE KZN_TL_MST SET KZNM_DATE='"+KaizenDate+"'::DATE WHERE KZNM_KEYID='"+KaizenKeyid+"'");
	//CommonMessage.debugMsg("Change Date"+sql);
	dbActionTemplate.executeStatement(sql.toString());
	UpdateSuggestionDate(KaizenKeyid,KaizenDate);
}

public void UpdateSuggestionDate(String KaizenKeyid,String KaizenDate) throws Exception{
    StringBuilder sql=new StringBuilder();
	String SuggestionId=dbActionTemplate.getSingleValue("SELECT KZNM_KZBNKEYID FROM KZN_TL_MST WHERE KZNM_KEYID='"+KaizenKeyid+"' ");
	//CommonMessage.debugMsg("SuggestionId"+SuggestionId);
	sql.append("UPDATE KZN_TL_KAIZENBANKMST SET KZBN_DATE='"+KaizenDate+"'::DATE WHERE KZBN_KEYID='"+SuggestionId+"'  ");
	//CommonMessage.debugMsg("Update Sugg Date:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
	
}

//SWETHA CHANGE - APPLICATION MAINTENANCE

public List<String[]> getSusaData(GridParams gridParams,CommonFilter commonFilter) throws Exception{
	String sql = getSusaDelGrid(commonFilter);
	String outerSql="select * from("+sql.toString()+")where 1=1 ";	
	if(gridParams.getGridFilters()!=null)
		outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
	String count="select count(*) from("+outerSql+")";
	//CommonMessage.debugMsg("count in daoimpl"+count);
	String rowCount=dbActionTemplate.getSingleValue(count);
	//CommonMessage.debugMsg("rowCount in daoimpl"+rowCount);
	long counts=Long.parseLong(rowCount);
	gridParams.setTotalRecordCnt(counts);
	List<String> params= new ArrayList<String>();
	params.add(gridParams.getFromRow());
	params.add(gridParams.getToRow());
	//String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("+outerSql+") a ) where slno >= ? and slno <= ?";
	String finalSql =
		    "SELECT * FROM (" +
		        "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" + outerSql + ") a" +
		    ") t WHERE slno >= CAST(? AS bigint) AND slno <= CAST(? AS bigint)";

	//CommonMessage.debugMsg("finalSql in Dao"+finalSql);
	List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
	return result;
	
}
//SWETHA CHANGE - APPLICATION MAINTENANCE
////SWETHA CHANGE - APPLICATION MAINTENANCE -25 Nov
public String getSusaDelGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();

//	 	if(fromDate !=null && toDate!=null){
//	   // CommonMessage.debugMsg("If");
//		sql.append("SELECT SUSN_KEYID AS SusnKeyid,TO_CHAR (SUSN_DATE, 'DD-MON-YYYY') AS SusnDate,DMT AS txtDMT,JH AS txtJH, ");
//		sql.append("SUSN_DISCUSSIONSUMM AS SusnDiscussionSumm,'' AS SUBMIT ");
//		sql.append("FROM GEN_TL_SUSAMSTNEW,gen_mv_flidhierarchy WHERE SUSN_DATE BETWEEN '"+fromDate+"' AND '"+toDate+"' AND SUSN_FLID=FLID(+) ");
//		
//		if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
//			sql.append("AND FLID='"+DMT+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(JH)){
//			//CommonMessage.debugMsg("Else If:"+sql.toString());
//			sql.append("AND FLID='"+JH+"' ");
//		}
//
//		
//		sql.append(" ORDER BY SUSN_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	}
//	else{
//	sql.append("SELECT SUSN_KEYID AS SusnKeyid,TO_CHAR (SUSN_DATE, 'DD-MON-YYYY') AS SusnDate,DMT AS txtDMT,JH AS txtJH, ");
//	sql.append("SUSN_DISCUSSIONSUMM AS SusnDiscussionSumm,'' AS SUBMIT ");
//	sql.append("FROM GEN_TL_SUSAMSTNEW,gen_mv_flidhierarchy WHERE SUSN_DATE BETWEEN '01-Jan-1801' AND '31-DEC-2100' AND SUSN_FLID=FLID(+) ");
//	sql.append(" ORDER BY SUSN_KEYID DESC ");
//	//CommonMessage.debugMsg("sql in Susa Delete Data"+sql.toString());
//	
//	}
	
	if(fromDate != null && toDate != null){
	    sql.append("SELECT SUSN_KEYID AS SusnKeyid, TO_CHAR(SUSN_DATE, 'DD-Mon-YYYY') AS SusnDate, DMT AS txtDMT, JH AS txtJH, ");
	    sql.append("SUSN_DISCUSSIONSUMM AS SusnDiscussionSumm, '' AS SUBMIT ");
	    sql.append("FROM GEN_TL_SUSAMSTNEW ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON SUSN_FLID=FLID ");
	    sql.append("WHERE SUSN_DATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    sql.append(" ORDER BY SUSN_KEYID DESC ");
	}
	else{
	    sql.append("SELECT SUSN_KEYID AS SusnKeyid, TO_CHAR(SUSN_DATE, 'DD-Mon-YYYY') AS SusnDate, DMT AS txtDMT, JH AS txtJH, ");
	    sql.append("SUSN_DISCUSSIONSUMM AS SusnDiscussionSumm, '' AS SUBMIT ");
	    sql.append("FROM GEN_TL_SUSAMSTNEW ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON SUSN_FLID=FLID ");
	    sql.append("WHERE SUSN_DATE BETWEEN '1801-01-01' AND '2100-12-31' ");
	    sql.append(" ORDER BY SUSN_KEYID DESC ");
	}
	 	CommonMessage.debugMsg("sql in Susa Delete Data"+sql.toString());
	return sql.toString();
	}
public void DeleteSusa(String SusaKeyid) throws Exception{
	  List<String> sql=new ArrayList<String>();
	  
	   String APlanMstId=dbActionTemplate.getSingleValue("SELECT APLM_KEYID FROM GEN_TL_ACTIONPLANMST WHERE APLM_MASTERREFID='"+SusaKeyid+"' ");
	   //CommonMessage.debugMsg("APlanMstId"+APlanMstId);
	   String APlanDtlId=dbActionTemplate.getSingleValue("SELECT APLD_KEYID FROM GEN_TL_ACTIONPLANDTL WHERE APLD_APLM_KEYID='"+APlanMstId+"' ");
	  // CommonMessage.debugMsg("APlanDtlId"+APlanDtlId);
	   String DocMgrId=dbActionTemplate.getSingleValue("SELECT DMDM_KEYID FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_REFDOCNO='"+SusaKeyid+"' ");
	  // CommonMessage.debugMsg("DocMgrId"+DocMgrId);
	   String DocLayoutId=dbActionTemplate.getSingleValue("SELECT DMLY_KEYID FROM DCM_TL_DOCUMENTLAYOUT WHERE DMLY_PARENTID='"+DocMgrId+"' ");
	   //CommonMessage.debugMsg("DocLayoutId"+DocLayoutId);
	   sql.add("DELETE FROM GEN_TL_SUSAMSTNEW WHERE SUSN_KEYID='"+SusaKeyid+"'");
	   sql.add("DELETE FROM GEN_TL_SUSAPARTICIPANTSMST WHERE SUST_SUSN_KEYID='"+SusaKeyid+"'");
	   sql.add("DELETE FROM GEN_TL_SUSAADDBEHAVIOURMST WHERE SUAB_SUSN_KEYID='"+SusaKeyid+"'");
	   sql.add("DELETE FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_KEYID='"+DocMgrId+"' ");
	   sql.add("DELETE FROM DCM_TL_DOCUMENTLAYOUT WHERE DMLY_KEYID='"+DocLayoutId+"' ");
	   sql.add("DELETE FROM GEN_TL_ACTIONPLANDTL WHERE APLD_KEYID='"+APlanDtlId+"' ");
	   sql.add("DELETE FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"+APlanMstId+"' ");

	 
	 // CommonMessage.debugMsg("Susa Delete"+sql);
	  dbActionTemplate.executeStatements(sql);
}

public List<String[]> getTrainingData(GridParams gridParams,CommonFilter commonFilter) throws Exception{
	
	

	
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	
	if(UIUtils.isValidKeyId(commonFilter.getFromDate()  )){
		condParams +="FROMDATE="+commonFilter.getFromDate()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getToDate())){
		condParams +="TODATE="+commonFilter.getToDate()+";";
	}
	
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParams +="DMT="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAwise())){
		condParams +="JH="+commonFilter.getAwise()+";";
	}
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
			List<String[]> result =  fnCallApi.callFunction("app_fn_trainingdeletegrid_sb", paramValues,2,true);
			
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				
				if(  isInteger )
				{
					long counts=Long.parseLong(totalCnt);
					gridParams.setTotalRecordCnt(counts);
				}
			
				
				
				return result;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
				/*
				 * 
				 * String sql = getTrainingDelGrid(commonFilter); String
				 * outerSql="select * from("+sql.toString()+")where 1=1 ";
				 * if(gridParams.getGridFilters()!=null)
				 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				 * String count="select count(*) from("+outerSql+")";
				 * //CommonMessage.debugMsg("count in daoimpl"+count); String
				 * rowCount=dbActionTemplate.getSingleValue(count);
				 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
				 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
				 * List<String> params= new ArrayList<String>();
				 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
				 * //String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
				 * +outerSql+") a ) where slno >= ? and slno <= ?"; String finalSql =
				 * "SELECT * FROM (" + "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" +
				 * outerSql + ") a" +
				 * ") t WHERE slno >= CAST(? AS BIGINT) AND slno <= CAST(? AS BIGINT)";
				 * 
				 * //CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
				 * result=dbActionTemplate.getDataList(finalSql, params); return result;
				 */
}
////Swetha change November 25
public String getTrainingDelGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	
//	 if(fromDate !=null && toDate!=null){
//	  //  CommonMessage.debugMsg("If");
//		sql.append("SELECT ETCM_KEYID AS EtcmKeyid,TO_CHAR(ETCM_CALDATE,'DD-MON-YYYY') AS EtcmCalendarDate,DMT AS txtDMT,JH AS txtJH,");
//		sql.append("TOPI_NAME AS EtcmTopicid,ETCM_MAX_DURATION AS EtcmMaxDuration,'' AS SUBMIT ");
//		sql.append("FROM ENT_TL_TRGCALMST,ent_tl_topicmst,gen_mv_flidhierarchy WHERE ETCM_TOPICID=TOPI_KEYID AND ETCM_CHKCOMPLETED='N' AND ETCM_CALDATE BETWEEN '"+fromDate+"' AND '"+toDate+"' AND ETCM_FLID=FLID(+) ");
//		
//		if(UIUtils.isValidKeyId(DMT)){
//			sql.append("AND FLID='"+DMT+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(JH)){
//			//CommonMessage.debugMsg("Else If:"+sql.toString());
//			sql.append("AND FLID='"+JH+"' ");
//		}
//
//		sql.append(" ORDER BY ETCM_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	}
//	else{
//    sql.append("SELECT ETCM_KEYID AS EtcmKeyid,TO_CHAR(ETCM_CALDATE,'DD-MON-YYYY') AS EtcmCalendarDate,DMT AS txtDMT,JH AS txtJH,");
//    sql.append("TOPI_NAME AS EtcmTopicid,ETCM_MAX_DURATION AS EtcmMaxDuration,'' AS SUBMIT ");
//	sql.append("FROM ENT_TL_TRGCALMST,ent_tl_topicmst,gen_mv_flidhierarchy WHERE ETCM_TOPICID=TOPI_KEYID AND ETCM_CHKCOMPLETED='N' AND ETCM_CALDATE BETWEEN '01-Jan-1801' AND '31-DEC-2100' AND ETCM_FLID=FLID(+) ");
//	sql.append(" ORDER BY ETCM_KEYID DESC ");
//	
//	
//	}
	if(fromDate != null && toDate != null){
	    sql.append("SELECT ETCM_KEYID AS EtcmKeyid, TO_CHAR(ETCM_CALDATE,'DD-Mon-YYYY') AS EtcmCalendarDate, DMT AS txtDMT, JH AS txtJH, ");
	    sql.append("TOPI_NAME AS EtcmTopicid, ETCM_MAX_DURATION AS EtcmMaxDuration, '' AS SUBMIT ");
	    sql.append("FROM ENT_TL_TRGCALMST ");
	    sql.append("INNER JOIN ent_tl_topicmst ON ETCM_TOPICID=TOPI_KEYID ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON ETCM_FLID=FLID ");
	    sql.append("WHERE ETCM_CHKCOMPLETED='N' AND ETCM_CALDATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(DMT)){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    sql.append(" ORDER BY ETCM_KEYID DESC ");
	}
	else{
	    sql.append("SELECT ETCM_KEYID AS EtcmKeyid, TO_CHAR(ETCM_CALDATE,'DD-Mon-YYYY') AS EtcmCalendarDate, DMT AS txtDMT, JH AS txtJH, ");
	    sql.append("TOPI_NAME AS EtcmTopicid, ETCM_MAX_DURATION AS EtcmMaxDuration, '' AS SUBMIT ");
	    sql.append("FROM ENT_TL_TRGCALMST ");
	    sql.append("INNER JOIN ent_tl_topicmst ON ETCM_TOPICID=TOPI_KEYID ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON ETCM_FLID=FLID ");
	    sql.append("WHERE ETCM_CHKCOMPLETED='N' AND ETCM_CALDATE BETWEEN '1801-01-01' AND '2100-12-31' ");
	    sql.append(" ORDER BY ETCM_KEYID DESC ");
	}
	 CommonMessage.debugMsg("sql in Training Delete Data"+sql.toString());
	return sql.toString();
	}
///Swetha change November 25

public void DeleteTraining(String TrainingKeyid) throws Exception{
	  List<String> sql=new ArrayList<String>();
	   String DocMgrId=dbActionTemplate.getSingleValue("SELECT DMDM_KEYID FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_REFDOCNO='"+TrainingKeyid+"' ");
	   //CommonMessage.debugMsg("DocMgrId"+DocMgrId);
	   String DocLayoutId=dbActionTemplate.getSingleValue("SELECT DMLY_KEYID FROM DCM_TL_DOCUMENTLAYOUT WHERE DMLY_PARENTID='"+DocMgrId+"' ");
	  // CommonMessage.debugMsg("DocLayoutId"+DocLayoutId);
	  sql.add("DELETE FROM ENT_TL_TRGCALMST WHERE ETCM_KEYID='"+TrainingKeyid+"'");
      sql.add("DELETE FROM ENT_TL_TRGCALSESSION WHERE ETCS_ETCM_KEYID='"+TrainingKeyid+"'");
      sql.add("DELETE FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID='"+TrainingKeyid+"'");
      sql.add("DELETE FROM ENT_TL_TRGCALEMP WHERE ETCE_ETCM_KEYID='"+TrainingKeyid+"'");
      sql.add("DELETE FROM ENT_TL_TRGCALEMPATSCORE WHERE ETCA_ETCM_KEYID='"+TrainingKeyid+"'");
      sql.add("DELETE FROM ENT_TL_TRGCALQUAD WHERE ETCQ_L1_TRGCALID='"+TrainingKeyid+"'");
      sql.add("DELETE FROM ENT_TL_TRGFACULTY WHERE ETCF_ETCM_KEYID='"+TrainingKeyid+"'");
      sql.add("DELETE FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_KEYID='"+DocMgrId+"' ");
	  sql.add("DELETE FROM DCM_TL_DOCUMENTLAYOUT WHERE DMLY_KEYID='"+DocLayoutId+"' ");

	 // CommonMessage.debugMsg("Training Delete"+sql);
	  dbActionTemplate.executeStatements(sql);
}

public List<String[]> getSuggestionData(GridParams gridParams,CommonFilter commonFilter) throws Exception
{
	
	
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	
	if(UIUtils.isValidKeyId(commonFilter.getFromDate()  )){
		condParams +="FROMDATE="+commonFilter.getFromDate()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getToDate())){
		condParams +="TODATE="+commonFilter.getToDate()+";";
	}
	
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParams +="DMT="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAwise())){
		condParams +="JH="+commonFilter.getAwise()+";";
	}
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
			List<String[]> result =  fnCallApi.callFunction("app_fn_suggesstionbank_sb", paramValues,2,true);
			
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				
				if(  isInteger )
				{
					long counts=Long.parseLong(totalCnt);
					gridParams.setTotalRecordCnt(counts);
				}
			
				
				
				return result;
	
	
	
	
	
	
	
	
	
	
	
	
	
				/*
				 * String sql = getSuggestionDelGrid(commonFilter); String
				 * outerSql="select * from("+sql.toString()+")where 1=1 ";
				 * if(gridParams.getGridFilters()!=null)
				 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				 * String count="select count(*) from("+outerSql+")";
				 * //CommonMessage.debugMsg("count in daoimpl"+count); String
				 * rowCount=dbActionTemplate.getSingleValue(count);
				 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
				 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
				 * List<String> params= new ArrayList<String>();
				 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
				 * String finalSql = "SELECT * FROM (" +
				 * "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" + outerSql + ") a" +
				 * ") t WHERE slno >= CAST(? AS bigint) AND slno <= CAST(? AS bigint)";
				 * 
				 * //String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
				 * +outerSql+") a ) where slno >= ? and slno <= ?";
				 * //CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
				 * result=dbActionTemplate.getDataList(finalSql, params); return result;
				 */
}
//Swetha conversion 25 Nov
public String getSuggestionDelGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	////CommonMessage.debugMsg("toDate"+toDate);
	//CommonMessage.debugMsg("dmt"+DMT);
	//CommonMessage.debugMsg("JH"+JH);
//	 if(fromDate !=null && toDate!=null){
//	  //  CommonMessage.debugMsg("If");
//		sql.append("SELECT KZBN_KEYID AS KzbnKeyid,TO_CHAR(KZBN_DATE,'DD-MON-YYYY') AS KzbnDate,DMT AS txtDMT,JH AS txtJH,KZBN_KAIZEN AS KzbnKaizen, ");
//		sql.append("'' AS SUBMIT FROM KZN_TL_KAIZENBANKMST,gen_mv_flidhierarchy WHERE KZBN_DATE BETWEEN '"+fromDate+"' AND '"+toDate+"' AND KZBN_FLID=FLID(+) ");
//		
//		if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
//			sql.append("AND FLID='"+DMT+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(JH)){
//			//CommonMessage.debugMsg("Else If:"+sql.toString());
//			sql.append("AND FLID='"+JH+"' ");
//		}
//		
//		sql.append(" ORDER BY KZBN_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	}
//	else{
//		sql.append("SELECT KZBN_KEYID AS KzbnKeyid,TO_CHAR(KZBN_DATE,'DD-MON-YYYY') AS KzbnDate,DMT AS txtDMT,JH AS txtJH,KZBN_KAIZEN AS KzbnKaizen, ");
//		sql.append("'' AS SUBMIT FROM KZN_TL_KAIZENBANKMST,gen_mv_flidhierarchy WHERE KZBN_DATE BETWEEN  '01-Jan-1801' AND '31-DEC-2100' AND KZBN_FLID=FLID(+) ");
//		sql.append(" ORDER BY KZBN_KEYID DESC ");
//	    //CommonMessage.debugMsg("sql in Suggestion Delete Data"+sql.toString());
//	
//	}
	
	if(fromDate != null && toDate != null){
	    sql.append("SELECT KZBN_KEYID AS KzbnKeyid, TO_CHAR(KZBN_DATE,'DD-Mon-YYYY') AS KzbnDate, DMT AS txtDMT, JH AS txtJH, KZBN_KAIZEN AS KzbnKaizen, ");
	    sql.append("'' AS SUBMIT FROM KZN_TL_KAIZENBANKMST ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON KZBN_FLID=FLID ");
	    sql.append("WHERE KZBN_DATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    sql.append(" ORDER BY KZBN_KEYID DESC ");
	}
	else{
	    sql.append("SELECT KZBN_KEYID AS KzbnKeyid, TO_CHAR(KZBN_DATE,'DD-Mon-YYYY') AS KzbnDate, DMT AS txtDMT, JH AS txtJH, KZBN_KAIZEN AS KzbnKaizen, ");
	    sql.append("'' AS SUBMIT FROM KZN_TL_KAIZENBANKMST ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON KZBN_FLID=FLID ");
	    sql.append("WHERE KZBN_DATE BETWEEN '1801-01-01' AND '2100-12-31' ");
	    sql.append(" ORDER BY KZBN_KEYID DESC ");
	}
	CommonMessage.debugMsg("sql in Suggestion Delete Data"+sql.toString());
	return sql.toString();
	}

public void DeleteSuggestion(String SuggKeyid) throws Exception{
       StringBuilder sql=new StringBuilder();
       sql.append("DELETE FROM KZN_TL_KAIZENBANKMST WHERE KZBN_KEYID='"+SuggKeyid+"' ");
	   //CommonMessage.debugMsg("Suggestion Delete"+sql);
	   dbActionTemplate.executeStatement(sql.toString());
}

public String getDmtOriginalId(String Flid)throws Exception{
	    String sql="SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='"+Flid+"' ";
		//CommonMessage.debugMsg("Sql:"+sql);
		return dbActionTemplate.getSingleValue(sql);
}
public String getJHOriginalId(String Flid)throws Exception{
	       String sql="SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='"+Flid+"' ";
			//CommonMessage.debugMsg("Sql:"+sql);
			return dbActionTemplate.getSingleValue(sql);
}

public List<String[]> getKaizenDeleteData(GridParams gridParams,CommonFilter commonFilter) throws Exception{
	

	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	
	if(UIUtils.isValidKeyId(commonFilter.getFromDate()  )){
		condParams +="FROMDATE="+commonFilter.getFromDate()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getToDate())){
		condParams +="TODATE="+commonFilter.getToDate()+";";
	}
	
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParams +="DMT="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAwise())){
		condParams +="JH="+commonFilter.getAwise()+";";
	}
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
			List<String[]> result =  fnCallApi.callFunction("app_fn_kaizendeletegrid_sb", paramValues,2,true);
			
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				
				if(  isInteger )
				{
					long counts=Long.parseLong(totalCnt);
					gridParams.setTotalRecordCnt(counts);
				}
			
				
				
				return result;
//	
//	
//	String sql = getKaizenDelGrid(commonFilter);
//	String outerSql="select * from("+sql.toString()+")where 1=1 ";	
//	if(gridParams.getGridFilters()!=null)
//		outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
//	String count="select count(*) from("+outerSql+")";
//	//CommonMessage.debugMsg("count in daoimpl"+count);
//	String rowCount=dbActionTemplate.getSingleValue(count);
//	//CommonMessage.debugMsg("rowCount in daoimpl"+rowCount);
//	long counts=Long.parseLong(rowCount);
//	gridParams.setTotalRecordCnt(counts);
//	List<String> params= new ArrayList<String>();
//	params.add(gridParams.getFromRow());
//	params.add(gridParams.getToRow());
//	String finalSql =
//		    "SELECT * FROM (" +
//		        "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" + outerSql + ") a" +
//		    ") t WHERE slno >= CAST(? AS bigint) AND slno <= CAST(? AS bigint)";
//
//	//CommonMessage.debugMsg("finalSql in Dao"+finalSql);
//	List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
	//return result;
}
public String getKaizenDelGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();

	
//	 if(fromDate !=null && toDate!=null){
//	    //CommonMessage.debugMsg("If");
//		sql.append("SELECT KZNM_KEYID AS KznmKeyid,TO_CHAR(KZNM_DATE,'DD-MON-YYYY') AS KznmDate,DMT AS txtDMT,JH AS txtJH,KZNM_IDEA AS KznmIdea,'' AS SUBMIT ");
//		sql.append("FROM KZN_TL_MST,gen_mv_flidhierarchy WHERE KZNM_DATE BETWEEN '"+fromDate+"' AND '"+toDate+"' AND KZNM_FLID=FLID(+) ");
//		
//		if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
//			sql.append("AND FLID='"+DMT+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(JH)){
//			//CommonMessage.debugMsg("Else If:"+sql.toString());
//			sql.append("AND FLID='"+JH+"' ");
//		}
//
//		sql.append(" ORDER BY KZNM_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	}
//	else{
//		sql.append("SELECT KZNM_KEYID AS KznmKeyid,TO_CHAR(KZNM_DATE,'DD-MON-YYYY') AS KznmDate,DMT AS txtDMT,JH AS txtJH,KZNM_IDEA AS KznmIdea,'' AS SUBMIT ");
//		sql.append("FROM KZN_TL_MST,gen_mv_flidhierarchy WHERE KZNM_DATE BETWEEN  '01-Jan-1801' AND '31-DEC-2100' AND KZNM_FLID=FLID(+) ");
//		sql.append(" ORDER BY KZNM_KEYID DESC ");
//	    //CommonMessage.debugMsg("sql in Kaizen Delete Data"+sql.toString());
//	
//	}
	
	if(fromDate != null && toDate != null){
	    sql.append("SELECT KZNM_KEYID AS KznmKeyid, TO_CHAR(KZNM_DATE,'DD-Mon-YYYY') AS KznmDate, DMT AS txtDMT, JH AS txtJH, KZNM_IDEA AS KznmIdea, '' AS SUBMIT ");
	    sql.append("FROM KZN_TL_MST ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON KZNM_FLID=FLID ");
	    sql.append("WHERE KZNM_DATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    sql.append(" ORDER BY KZNM_KEYID DESC ");
	}
	else{
	    sql.append("SELECT KZNM_KEYID AS KznmKeyid, TO_CHAR(KZNM_DATE,'DD-Mon-YYYY') AS KznmDate, DMT AS txtDMT, JH AS txtJH, KZNM_IDEA AS KznmIdea, '' AS SUBMIT ");
	    sql.append("FROM KZN_TL_MST ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON KZNM_FLID=FLID ");
	    sql.append("WHERE KZNM_DATE BETWEEN '1801-01-01' AND '2100-12-31' ");
	    sql.append(" ORDER BY KZNM_KEYID DESC ");
	}
	 CommonMessage.debugMsg("sql in Kaizen Delete Data"+sql.toString());
	return sql.toString();
	}

public void DeleteKaizen(String KaizenKeyid) throws Exception{
	//StringBuilder sql=new StringBuilder();

	List<String> sqls=new ArrayList<String>();
	
	String APlanMstId=dbActionTemplate.getSingleValue("SELECT APLM_KEYID FROM GEN_TL_ACTIONPLANMST WHERE APLM_DETAILREFID='"+KaizenKeyid+"' ");
	//CommonMessage.debugMsg("APlanMstId"+APlanMstId);
    String APlanDtlId=dbActionTemplate.getSingleValue("SELECT APLD_KEYID FROM GEN_TL_ACTIONPLANDTL WHERE APLD_APLM_KEYID='"+APlanMstId+"' ");
	//CommonMessage.debugMsg("APlanDtlId"+APlanDtlId);
	String DocMgrId=dbActionTemplate.getSingleValue("SELECT DMDM_KEYID FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_REFDOCNO='"+KaizenKeyid+"' ");
	//CommonMessage.debugMsg("DocMgrId"+DocMgrId);
	String DocLayoutId=dbActionTemplate.getSingleValue("SELECT DMLY_KEYID FROM DCM_TL_DOCUMENTLAYOUT WHERE DMLY_PARENTID='"+DocMgrId+"' ");
	//CommonMessage.debugMsg("DocLayoutId"+DocLayoutId);
    String WorkFlowId=dbActionTemplate.getSingleValue("SELECT WRIN_KEYID FROM GEN_TL_WORKFLOW_INFO WHERE WRIN_REF_ID='"+KaizenKeyid+"' ");
	//CommonMessage.debugMsg("WorkFlowId"+WorkFlowId);
	
    sqls.add("DELETE FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_KEYID='"+DocMgrId+"' ");
    sqls.add("DELETE FROM DCM_TL_DOCUMENTLAYOUT WHERE DMLY_KEYID='"+DocLayoutId+"' ");
    sqls.add("DELETE FROM GEN_TL_ACTIONPLANDTL WHERE APLD_KEYID='"+APlanDtlId+"' ");
    sqls.add("DELETE FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"+APlanMstId+"' ");
    sqls.add("DELETE FROM GEN_TL_WORKFLOW_INFO WHERE WRIN_KEYID='"+WorkFlowId+"' ");
    sqls.add("DELETE FROM KZN_TL_HDMST WHERE KHDM_KAIZENID='"+KaizenKeyid+"' ");
	sqls.add("DELETE FROM KZN_TL_GRAPHDATA WHERE KZGD_KAIZENID='"+KaizenKeyid+"' ");
	sqls.add("DELETE FROM KZN_TL_MST WHERE KZNM_KEYID='"+KaizenKeyid+"' ");
	

	//CommonMessage.debugMsg("Kaizen Delete "+sqls);
	
	dbActionTemplate.executeStatements(sqls);
}
public List<String[]> getWhyWhyDeleteData(GridParams gridParams,CommonFilter commonFilter) throws Exception
{
	

	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	
	
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	
	if(UIUtils.isValidKeyId(commonFilter.getFromDate()  )){
		condParams +="FROMDATE="+commonFilter.getFromDate()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getToDate())){
		condParams +="TODATE="+commonFilter.getToDate()+";";
	}
	

	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParams +="DMT="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAwise())){
		condParams +="JH="+commonFilter.getAwise()+";";
	}

	
	
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
			List<String[]> result =  fnCallApi.callFunction("app_fn_whywhydeletegrid_sb", paramValues,2,true);
			
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				
				if(  isInteger )
				{
					long counts=Long.parseLong(totalCnt);
					gridParams.setTotalRecordCnt(counts);
				}
				return result;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
				/*
				 * 
				 * String sql = getWhyWhyDelGrid(commonFilter); String
				 * outerSql="select * from("+sql.toString()+")where 1=1 ";
				 * if(gridParams.getGridFilters()!=null)
				 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				 * String count="select count(*) from("+outerSql+")";
				 * //CommonMessage.debugMsg("count in daoimpl"+count); String
				 * rowCount=dbActionTemplate.getSingleValue(count);
				 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
				 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
				 * List<String> params= new ArrayList<String>();
				 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
				 * //String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
				 * +outerSql+") a ) where slno >= ? and slno <= ?"; String finalSql =
				 * "SELECT * FROM (" + "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" +
				 * outerSql + ") a" +
				 * ") t WHERE slno >= CAST(? AS bigint) AND slno <= CAST(? AS bigint)";
				 * 
				 * //CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
				 * result=dbActionTemplate.getDataList(finalSql, params); return result;
				 */
	
}
//Swetha change November 25
public String getWhyWhyDelGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();

//	 if(fromDate !=null && toDate!=null){
//	   // CommonMessage.debugMsg("If");
//		sql.append("SELECT WWMS_KEYID AS WwmsKeyid,TO_CHAR(WWMS_DATE,'DD-MON-YYYY') AS WwmsDate,DMT AS txtDMT,JH AS txtJH,WWMS_AREA AS WwmsArea,");
//		sql.append("WWMS_PROBLEM AS WwmsProblem,'' AS SUBMIT  FROM BDM_TL_WHYWHYMST,GEN_MV_FLIDHIERARCHY WHERE WWMS_DATE BETWEEN '"+fromDate+"' AND '"+toDate+"' AND WWMS_FLID=FLID(+)");
//	
//       if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
//			sql.append("AND FLID='"+DMT+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(JH)){
//			//CommonMessage.debugMsg("Else If:"+sql.toString());
//			sql.append("AND FLID='"+JH+"' ");
//		}
//
//		
//		sql.append(" ORDER BY WWMS_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	}
//	else{
//		sql.append("SELECT WWMS_KEYID AS WwmsKeyid,TO_CHAR(WWMS_DATE,'DD-MON-YYYY') AS WwmsDate,DMT AS txtDMT,JH AS txtJH,WWMS_AREA AS WwmsArea,");
//		sql.append("WWMS_PROBLEM AS WwmsProblem,'' AS SUBMIT  FROM BDM_TL_WHYWHYMST,GEN_MV_FLIDHIERARCHY WHERE WWMS_DATE BETWEEN  '01-Jan-1801' AND '31-DEC-2100' AND WWMS_FLID=FLID(+) ");
//		sql.append(" ORDER BY WWMS_KEYID DESC ");
//	    //CommonMessage.debugMsg("sql in Why Why Delete Data"+sql.toString());
//	
//	}
	if(fromDate != null && toDate != null){
	    sql.append("SELECT WWMS_KEYID AS WwmsKeyid, TO_CHAR(WWMS_DATE,'DD-Mon-YYYY') AS WwmsDate, DMT AS txtDMT, JH AS txtJH, WWMS_AREA AS WwmsArea, ");
	    sql.append("WWMS_PROBLEM AS WwmsProblem, '' AS SUBMIT FROM BDM_TL_WHYWHYMST ");
	    sql.append("LEFT JOIN GEN_MV_FLIDHIERARCHY ON WWMS_FLID=FLID ");
	    sql.append("WHERE WWMS_DATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    sql.append(" ORDER BY WWMS_KEYID DESC ");
	}
	else{
	    sql.append("SELECT WWMS_KEYID AS WwmsKeyid, TO_CHAR(WWMS_DATE,'DD-Mon-YYYY') AS WwmsDate, DMT AS txtDMT, JH AS txtJH, WWMS_AREA AS WwmsArea, ");
	    sql.append("WWMS_PROBLEM AS WwmsProblem, '' AS SUBMIT FROM BDM_TL_WHYWHYMST ");
	    sql.append("LEFT JOIN GEN_MV_FLIDHIERARCHY ON WWMS_FLID=FLID ");
	    sql.append("WHERE WWMS_DATE BETWEEN '1801-01-01' AND '2100-12-31' ");
	    sql.append(" ORDER BY WWMS_KEYID DESC ");
	}
	 CommonMessage.debugMsg("sql in Why Why Delete Data"+sql.toString());
	return sql.toString();
	}
//Swetha change November 25
public void DeleteWhyWhy(String WhyWhyKeyid) throws Exception{
	   List<String> sql=new ArrayList<String>();
	   String APlanMstId=dbActionTemplate.getSingleValue("SELECT APLM_KEYID FROM GEN_TL_ACTIONPLANMST WHERE APLM_MASTERREFID='"+WhyWhyKeyid+"' ");
	   //CommonMessage.debugMsg("APlanMstId"+APlanMstId);
	   String APlanDtlId=dbActionTemplate.getSingleValue("SELECT APLD_KEYID FROM GEN_TL_ACTIONPLANDTL WHERE APLD_APLM_KEYID='"+APlanMstId+"' ");
	  // CommonMessage.debugMsg("APlanDtlId"+APlanDtlId);
	   String DocMgrId=dbActionTemplate.getSingleValue("SELECT DMDM_KEYID FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_REFDOCNO='"+WhyWhyKeyid+"' ");
	   //CommonMessage.debugMsg("DocMgrId"+DocMgrId);
	   String DocLayoutId=dbActionTemplate.getSingleValue("SELECT DMLY_KEYID FROM DCM_TL_DOCUMENTLAYOUT WHERE DMLY_PARENTID='"+DocMgrId+"' ");
	   //CommonMessage.debugMsg("DocLayoutId"+DocLayoutId);
	   String oplKeyid=dbActionTemplate.getSingleValue("SELECT OPLM_KEYID FROM OPL_TL_MST WHERE OPLM_REFDOCNO='"+WhyWhyKeyid+"' ");
	  // CommonMessage.debugMsg("oplKeyid"+oplKeyid);
	   String suggKeyid=dbActionTemplate.getSingleValue("SELECT KZBN_KEYID FROM KZN_TL_KAIZENBANKMST WHERE KZBN_REFDOCNO='"+WhyWhyKeyid+"' ");
	  // CommonMessage.debugMsg("suggKeyid"+suggKeyid);
	sql.add("DELETE FROM BDM_TL_WHYWHYDTL WHERE WWDT_WWMS_KEYID='"+WhyWhyKeyid+"'");
	sql.add("DELETE FROM BDM_TL_YYPROBLEMATTBYMST WHERE WWPA_WWMS_KEYID='"+WhyWhyKeyid+"'");
	sql.add("DELETE FROM BDM_TL_YYDONEBYMST WHERE WWDB_WWMS_KEYID='"+WhyWhyKeyid+"'");
	sql.add("DELETE FROM BDM_TL_WHYWHYMST WHERE WWMS_KEYID='"+WhyWhyKeyid+"'");
	sql.add("DELETE FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_KEYID='"+DocMgrId+"' ");
    sql.add("DELETE FROM DCM_TL_DOCUMENTLAYOUT WHERE DMLY_KEYID='"+DocLayoutId+"' ");
    sql.add("DELETE FROM GEN_TL_ACTIONPLANDTL WHERE APLD_KEYID='"+APlanDtlId+"' ");
    sql.add("DELETE FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"+APlanMstId+"' ");
    sql.add("DELETE FROM OPL_TL_MST WHERE OPLM_KEYID='"+oplKeyid+"' ");
    sql.add("DELETE FROM KZN_TL_KAIZENBANKMST WHERE KZBN_KEYID='"+suggKeyid+"' ");
    //CommonMessage.debugMsg("Why Why Delete"+sql);
	dbActionTemplate.executeStatements(sql);
}
public List<String[]> getLossDeleteData(GridParams gridParams,CommonFilter commonFilter) throws Exception{
	
	
	

	
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	
	String ActionPlanType=commonFilter.getType();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	
	if(UIUtils.isValidKeyId(commonFilter.getFromDate()  )){
		condParams +="FROMDATE="+commonFilter.getFromDate()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getToDate())){
		condParams +="TODATE="+commonFilter.getToDate()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getType())){
		condParams +="ACTIONPLANTYPE="+commonFilter.getType()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParams +="DMT="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAwise())){
		condParams +="JH="+commonFilter.getAwise()+";";
	}

	
	
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
			List<String[]> result =  fnCallApi.callFunction("app_fn_lossdeletegrid_sb", paramValues,2,true);
			
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				
				if(  isInteger )
				{
					long counts=Long.parseLong(totalCnt);
					gridParams.setTotalRecordCnt(counts);
				}
				return result;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
				/*
				 * String sql = getLossDelGrid(commonFilter); String
				 * outerSql="select * from("+sql.toString()+")where 1=1 ";
				 * if(gridParams.getGridFilters()!=null)
				 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				 * String count="select count(*) from("+outerSql+")";
				 * //CommonMessage.debugMsg("count in daoimpl"+count); String
				 * rowCount=dbActionTemplate.getSingleValue(count);
				 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
				 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
				 * List<String> params= new ArrayList<String>();
				 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
				 * //String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
				 * +outerSql+") a ) where slno >= ? and slno <= ?"; String finalSql =
				 * "SELECT * FROM (" + "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" +
				 * outerSql + ") a" +
				 * ") t WHERE slno >= CAST(? AS BIGINT) AND slno <= CAST(? AS BIGINT)";
				 * 
				 * //CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
				 * result=dbActionTemplate.getDataList(finalSql, params); return result;
				 */
}
//Swetha change November 25
public String getLossDelGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();

//	 if(fromDate !=null && toDate!=null){
//	   // CommonMessage.debugMsg("If");
//		sql.append("SELECT PLOS_KEYID AS PlosKeyid,TO_CHAR(PLOS_DATE,'DD-MON-YYYY') AS PlosDate,DMT AS txtDMT,JH AS txtJH,PLOS_LOSSDESCRIPTION AS PlosLossdescription,'' AS SUBMIT ");
//		sql.append("FROM PCS_TL_LOSSCAPTURE,GEN_MV_FLIDHIERARCHY WHERE PLOS_DATE BETWEEN '"+fromDate+"' AND '"+toDate+"' AND PLOS_FLID=FLID(+)");
//		
//		if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
//			sql.append("AND FLID='"+DMT+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(JH)){
//			//CommonMessage.debugMsg("Else If:"+sql.toString());
//			sql.append("AND FLID='"+JH+"' ");
//		}
//
//		
//		sql.append(" ORDER BY PLOS_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	}
//	else{
//		sql.append("SELECT PLOS_KEYID AS PlosKeyid,TO_CHAR(PLOS_DATE,'DD-MON-YYYY') AS PlosDate,DMT AS txtDMT,JH AS txtJH,PLOS_LOSSDESCRIPTION AS PlosLossdescription,'' AS SUBMIT ");
//		sql.append("FROM PCS_TL_LOSSCAPTURE,GEN_MV_FLIDHIERARCHY WHERE PLOS_DATE BETWEEN  '01-Jan-1801' AND '31-DEC-2100' AND PLOS_FLID=FLID(+)");
//		sql.append(" ORDER BY PLOS_KEYID DESC ");
//	   // CommonMessage.debugMsg("sql in Loss Delete Data"+sql.toString());
//	
//	}
	if(fromDate != null && toDate != null){
	    sql.append("SELECT PLOS_KEYID AS PlosKeyid, TO_CHAR(PLOS_DATE,'DD-Mon-YYYY') AS PlosDate, DMT AS txtDMT, JH AS txtJH, PLOS_LOSSDESCRIPTION AS PlosLossdescription, '' AS SUBMIT ");
	    sql.append("FROM PCS_TL_LOSSCAPTURE ");
	    sql.append("LEFT JOIN GEN_MV_FLIDHIERARCHY ON PLOS_FLID=FLID ");
	    sql.append("WHERE PLOS_DATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    sql.append(" ORDER BY PLOS_KEYID DESC ");
	}
	else{
	    sql.append("SELECT PLOS_KEYID AS PlosKeyid, TO_CHAR(PLOS_DATE,'DD-Mon-YYYY') AS PlosDate, DMT AS txtDMT, JH AS txtJH, PLOS_LOSSDESCRIPTION AS PlosLossdescription, '' AS SUBMIT ");
	    sql.append("FROM PCS_TL_LOSSCAPTURE ");
	    sql.append("LEFT JOIN GEN_MV_FLIDHIERARCHY ON PLOS_FLID=FLID ");
	    sql.append("WHERE PLOS_DATE BETWEEN '1801-01-01' AND '2100-12-31' ");
	    sql.append(" ORDER BY PLOS_KEYID DESC ");
	}
	 CommonMessage.debugMsg("sql in Loss Delete Data"+sql.toString());
	return sql.toString();
	}
//Swetha change November 25
public void DeleteLoss(String LossKeyid) throws Exception{
	StringBuilder sql=new StringBuilder();
	   List<String> sqls=new ArrayList<String>();

	String APlanMstId=dbActionTemplate.getSingleValue("SELECT APLM_KEYID FROM GEN_TL_ACTIONPLANMST WHERE APLM_MASTERREFID='"+LossKeyid+"' ");
	CommonMessage.debugMsg("APlanMstId"+APlanMstId);
    String APlanDtlId=dbActionTemplate.getSingleValue("SELECT APLD_KEYID FROM GEN_TL_ACTIONPLANDTL WHERE APLD_APLM_KEYID='"+APlanMstId+"' ");
	CommonMessage.debugMsg("APlanDtlId"+APlanDtlId);
	if(UIUtils.isValidKeyId(APlanDtlId)){
		sqls.add("DELETE FROM GEN_TL_ACTIONPLANDTL WHERE APLD_APLM_KEYID IN (SELECT APLM_KEYID FROM GEN_TL_ACTIONPLANMST WHERE APLM_MASTERREFID='"+LossKeyid+"' );");
		sqls.add("DELETE FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"+APlanMstId+"' ");
	
	}
	sql.append("DELETE FROM PCS_TL_LOSSCAPTURE WHERE PLOS_KEYID='"+LossKeyid+"' ");
	sqls.add(sql.toString());
	CommonMessage.debugMsg("Loss Delete"+sql);
	dbActionTemplate.executeStatements(sqls);
	
}
@Override
public List<String[]> getActionPlanDeleteData(GridParams gridParams, CommonFilter commonFilter) throws Exception 
{
	
	
	
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	
	String ActionPlanType=commonFilter.getType();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	
	if(UIUtils.isValidKeyId(commonFilter.getFromDate()  )){
		condParams +="FROMDATE="+commonFilter.getFromDate()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getToDate())){
		condParams +="TODATE="+commonFilter.getToDate()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getType())){
		condParams +="ACTIONPLANTYPE="+commonFilter.getType()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParams +="DMT="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAwise())){
		condParams +="JH="+commonFilter.getAwise()+";";
	}

	
	
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
			List<String[]> result =  fnCallApi.callFunction("app_fn_actionplandeletegrid_sb", paramValues,2,true);
			
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				
				if(  isInteger )
				{
					long counts=Long.parseLong(totalCnt);
					gridParams.setTotalRecordCnt(counts);
				}
				return result;
			
				
				/*
				 * return result; // TODO Auto-generated method stub String sql =
				 * getActionPlanDeleteGrid(commonFilter); String
				 * outerSql="select * from("+sql.toString()+")where 1=1 ";
				 * if(gridParams.getGridFilters()!=null)
				 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				 * String count="select count(*) from("+outerSql+")";
				 * //CommonMessage.debugMsg("count in daoimpl"+count); String
				 * rowCount=dbActionTemplate.getSingleValue(count);
				 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
				 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
				 * List<String> params= new ArrayList<String>();
				 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
				 * String finalSql = "SELECT * FROM (" +
				 * "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" + outerSql + ") a" +
				 * ") t WHERE slno >= CAST(? AS bigint) AND slno <= CAST(? AS bigint)";
				 * 
				 * // String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
				 * +outerSql+") a ) where slno >= ? and slno <= ?";
				 * //CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
				 * result=dbActionTemplate.getDataList(finalSql, params); return result;
				 */
}

public String getActionPlanDeleteGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String ActionPlanType=commonFilter.getType();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	//CommonMessage.debugMsg("DMT"+DMT);
	//CommonMessage.debugMsg("Jh"+JH);

	
//	 if(UIUtils.isValidKeyId(fromDate) && UIUtils.isValidKeyId(toDate)){
//	   // CommonMessage.debugMsg("If");
//		sql.append("SELECT APLM_KEYID AS AplmKeyid,TO_CHAR(APLM_PLANDATE,'DD-MON-YYYY') AS AplmPlandate,DMT AS txtDMT,JH AS txtJH,");
//		sql.append("APLM_REFDOCTYPE AS AplmRefdoctype,APLD_ACTIONPLAN AS ApldActionplan,EMPM_NAME AS Responsibility,'' AS SUBMIT ");
//		sql.append("FROM GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL,GEN_TL_EMPLOYEEMST,gen_mv_flidhierarchy WHERE ");
//		sql.append("APLM_PLANDATE BETWEEN '"+fromDate+"' AND '"+toDate+"' AND APLM_KEYID=APLD_APLM_KEYID(+) AND APLM_FLID=FLID(+) AND APLD_RESPONSIBILITY=EMPM_KEYID ");
//		
//		if(UIUtils.isValidKeyId(ActionPlanType)){
//			sql.append(" and APLM_REFDOCTYPE='"+ActionPlanType+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
//			sql.append("AND FLID='"+DMT+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(JH)){
//			//CommonMessage.debugMsg("Else If:"+sql.toString());
//			sql.append("AND FLID='"+JH+"' ");
//		}
//
//		
//		//sql.append(" ORDER BY APLM_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	}
//	else{
//		sql.append("SELECT APLM_KEYID AS AplmKeyid,TO_CHAR(APLM_PLANDATE,'DD-MON-YYYY') AS AplmPlandate,DMT AS txtDMT,JH AS txtJH, ");
//		sql.append("APLM_REFDOCTYPE AS AplmRefdoctype,APLD_ACTIONPLAN AS ApldActionplan,EMPM_NAME AS Responsibility,'' AS SUBMIT ");
//		sql.append("FROM GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL,GEN_TL_EMPLOYEEMST,gen_mv_flidhierarchy WHERE ");
//		sql.append("APLM_KEYID=APLD_APLM_KEYID(+) AND APLM_FLID=FLID(+) AND APLD_RESPONSIBILITY=EMPM_KEYID");
//	if(UIUtils.isValidKeyId(ActionPlanType)){
//		sql.append(" and APLM_REFDOCTYPE='"+ActionPlanType+"' ");
//	}
//		sql.append(" ORDER BY APLM_KEYID DESC ");
//	    //CommonMessage.debugMsg("sql in aCTIONpLAN Delete Data"+sql.toString());
//	}
	if(UIUtils.isValidKeyId(fromDate) && UIUtils.isValidKeyId(toDate)){
	    sql.append("SELECT APLM_KEYID AS AplmKeyid, TO_CHAR(APLM_PLANDATE,'DD-Mon-YYYY') AS AplmPlandate, DMT AS txtDMT, JH AS txtJH, ");
	    sql.append("APLM_REFDOCTYPE AS AplmRefdoctype, APLD_ACTIONPLAN AS ApldActionplan, EMPM_NAME AS Responsibility, '' AS SUBMIT ");
	    sql.append("FROM GEN_TL_ACTIONPLANMST ");
	    sql.append("LEFT JOIN GEN_TL_ACTIONPLANDTL ON APLM_KEYID=APLD_APLM_KEYID ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON APLM_FLID=FLID ");
	    sql.append("INNER JOIN GEN_TL_EMPLOYEEMST ON APLD_RESPONSIBILITY=EMPM_KEYID ");
	    sql.append("WHERE APLM_PLANDATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(ActionPlanType)){
	        sql.append(" AND APLM_REFDOCTYPE='" + ActionPlanType + "' ");
	    }
	    else if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    // sql.append(" ORDER BY APLM_KEYID DESC ");
	}
	else{
	    sql.append("SELECT APLM_KEYID AS AplmKeyid, TO_CHAR(APLM_PLANDATE,'DD-Mon-YYYY') AS AplmPlandate, DMT AS txtDMT, JH AS txtJH, ");
	    sql.append("APLM_REFDOCTYPE AS AplmRefdoctype, APLD_ACTIONPLAN AS ApldActionplan, EMPM_NAME AS Responsibility, '' AS SUBMIT ");
	    sql.append("FROM GEN_TL_ACTIONPLANMST ");
	    sql.append("LEFT JOIN GEN_TL_ACTIONPLANDTL ON APLM_KEYID=APLD_APLM_KEYID ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON APLM_FLID=FLID ");
	    sql.append("INNER JOIN GEN_TL_EMPLOYEEMST ON APLD_RESPONSIBILITY=EMPM_KEYID ");
	    sql.append("WHERE 1=1 ");
	    
	    if(UIUtils.isValidKeyId(ActionPlanType)){
	        sql.append(" AND APLM_REFDOCTYPE='" + ActionPlanType + "' ");
	    }
	    
	    sql.append(" ORDER BY APLM_KEYID DESC ");
	}
	 CommonMessage.debugMsg("sql in aCTIONpLAN Delete Data"+sql.toString());
	return sql.toString();
}

public void DeleteActionPlan(String ActionPlanKeyid) throws Exception{
	
	List<String> sql=new ArrayList<String>();
	sql.add("DELETE FROM GEN_TL_ACTIONPLANDTL WHERE APLD_APLM_KEYID='"+ActionPlanKeyid+"' ");
	sql.add("DELETE FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"+ActionPlanKeyid+"' ");
	//CommonMessage.debugMsg("ActionPlan Delete"+sql);
	dbActionTemplate.executeStatements(sql);
}
////Swetha change November 25
@Override
public List<String[]> getNearMissDeleteData(GridParams gridParams, CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	String sql = getNearMissDeleteGrid(commonFilter);
	String outerSql="select * from("+sql.toString()+")where 1=1 ";	
	if(gridParams.getGridFilters()!=null)
		outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
	String count="select count(*) from("+outerSql+")";
	//CommonMessage.debugMsg("count in daoimpl"+count);
	String rowCount=dbActionTemplate.getSingleValue(count);
	//CommonMessage.debugMsg("rowCount in daoimpl"+rowCount);
	long counts=Long.parseLong(rowCount);
	gridParams.setTotalRecordCnt(counts);
	List<String> params= new ArrayList<String>();
	params.add(gridParams.getFromRow());
	params.add(gridParams.getToRow());
	//String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("+outerSql+") a ) where slno >= ? and slno <= ?";
	String finalSql =
		    "SELECT * FROM (" +
		        "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" + outerSql + ") a" +
		    ") t WHERE slno >= CAST(? AS BIGINT) AND slno <= CAST(? AS BIGINT)";

	//CommonMessage.debugMsg("finalSql in Dao"+finalSql);
	List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
	return result;
}

////Swetha change November 25
public String getNearMissDeleteGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String NearMissType=commonFilter.getType();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	//CommonMessage.debugMsg("DMT"+DMT);
	//CommonMessage.debugMsg("Jh"+JH);

	
//	 if(UIUtils.isValidKeyId(fromDate) && UIUtils.isValidKeyId(toDate)){
//	    //CommonMessage.debugMsg("If");
//		sql.append("SELECT NMRN_KEYID AS NmrnKeyid,TO_CHAR(NMRN_OCCURRENCEDATETIME,'DD-MON-YYYY') AS NmrnOccurrencedatetime,DMT AS txtDMT,JH AS txtJH, ");
//		sql.append("NMSP_NAME AS NmrnSeveritypotentialid,NMRN_DESCNEARMISS AS NmrnDescnearmiss,EMPM_NAME AS Responsibility,'' AS SUBMIT ");
//		sql.append("FROM GEN_TL_NEARMISSREPORTMSTNEW,SHE_TL_NEARMISSSEVERITY,GEN_TL_EMPLOYEEMST,gen_mv_flidhierarchy WHERE NMRN_OCCURRENCEDATETIME ");
//		sql.append("BETWEEN '"+fromDate+"' AND '"+toDate+"' AND NMRN_SEVERITYPOTENTIALID=NMSP_KEYID(+) AND NMRN_FLNID=FLID(+) AND NMRN_EMPLOYEEID=EMPM_KEYID ");
//		if(UIUtils.isValidKeyId(NearMissType)){
//			sql.append(" and NMRN_SEVERITYPOTENTIALID='"+NearMissType+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
//			sql.append("AND FLID='"+DMT+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(JH)){
//			//CommonMessage.debugMsg("Else If:"+sql.toString());
//			sql.append("AND FLID='"+JH+"' ");
//		}
//		
//		sql.append(" ORDER BY NMRN_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	}
//	else{
//		sql.append("SELECT NMRN_KEYID AS NmrnKeyid,TO_CHAR(NMRN_OCCURRENCEDATETIME,'DD-MON-YYYY') AS NmrnOccurrencedatetime,DMT AS txtDMT,JH AS txtJH,");
//		sql.append("NMSP_NAME AS NmrnSeveritypotentialid,NMRN_DESCNEARMISS AS NmrnDescnearmiss,EMPM_NAME AS Responsibility,'' AS SUBMIT ");
//		sql.append("FROM GEN_TL_NEARMISSREPORTMSTNEW,SHE_TL_NEARMISSSEVERITY,GEN_TL_EMPLOYEEMST,gen_mv_flidhierarchy ");
//		sql.append(" WHERE NMRN_SEVERITYPOTENTIALID=NMSP_KEYID(+) AND NMRN_FLNID=FLID(+) AND NMRN_EMPLOYEEID=EMPM_KEYID ");
//		if(UIUtils.isValidKeyId(NearMissType)){
//			sql.append(" AND NMRN_SEVERITYPOTENTIALID='"+NearMissType+"' ");
//		}
//		sql.append(" ORDER BY NMRN_KEYID DESC ");
	
	if(UIUtils.isValidKeyId(fromDate) && UIUtils.isValidKeyId(toDate)){
	    sql.append("SELECT NMRN_KEYID AS NmrnKeyid, TO_CHAR(NMRN_OCCURRENCEDATETIME,'DD-Mon-YYYY') AS NmrnOccurrencedatetime, DMT AS txtDMT, JH AS txtJH, ");
	    sql.append("NMSP_NAME AS NmrnSeveritypotentialid, NMRN_DESCNEARMISS AS NmrnDescnearmiss, EMPM_NAME AS Responsibility, '' AS SUBMIT ");
	    sql.append("FROM GEN_TL_NEARMISSREPORTMSTNEW ");
	    sql.append("LEFT JOIN SHE_TL_NEARMISSSEVERITY ON NMRN_SEVERITYPOTENTIALID=NMSP_KEYID ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON NMRN_FLNID=FLID ");
	    sql.append("INNER JOIN GEN_TL_EMPLOYEEMST ON NMRN_EMPLOYEEID=EMPM_KEYID ");
	    sql.append("WHERE NMRN_OCCURRENCEDATETIME BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(NearMissType)){
	        sql.append(" AND NMRN_SEVERITYPOTENTIALID='" + NearMissType + "' ");
	    }
	    else if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    sql.append(" ORDER BY NMRN_KEYID DESC ");
	}
	else{
	    sql.append("SELECT NMRN_KEYID AS NmrnKeyid, TO_CHAR(NMRN_OCCURRENCEDATETIME,'DD-Mon-YYYY') AS NmrnOccurrencedatetime, DMT AS txtDMT, JH AS txtJH, ");
	    sql.append("NMSP_NAME AS NmrnSeveritypotentialid, NMRN_DESCNEARMISS AS NmrnDescnearmiss, EMPM_NAME AS Responsibility, '' AS SUBMIT ");
	    sql.append("FROM GEN_TL_NEARMISSREPORTMSTNEW ");
	    sql.append("LEFT JOIN SHE_TL_NEARMISSSEVERITY ON NMRN_SEVERITYPOTENTIALID=NMSP_KEYID ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON NMRN_FLNID=FLID ");
	    sql.append("INNER JOIN GEN_TL_EMPLOYEEMST ON NMRN_EMPLOYEEID=EMPM_KEYID ");
	    sql.append("WHERE 1=1 ");
	    
	    if(UIUtils.isValidKeyId(NearMissType)){
	        sql.append(" AND NMRN_SEVERITYPOTENTIALID='" + NearMissType + "' ");
	    }
	    
	    sql.append(" ORDER BY NMRN_KEYID DESC ");
	}
		//CommonMessage.debugMsg("If"+sql.toString());
	    CommonMessage.debugMsg("sql in NearMiss Delete Data"+sql.toString());
	
	
	return sql.toString();
}

public void DeleteNearMiss(String NearMissKeyid) throws Exception{
		List<String> sqls=new ArrayList<String>();
       String DocMgrId=dbActionTemplate.getSingleValue("SELECT DMDM_KEYID FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_REFDOCNO='"+NearMissKeyid+"' ");
	  // CommonMessage.debugMsg("DocMgrId"+DocMgrId);
	   String DocLayoutId=dbActionTemplate.getSingleValue("SELECT DMLY_KEYID FROM DCM_TL_DOCUMENTLAYOUT WHERE DMLY_PARENTID='"+DocMgrId+"' ");
	   //CommonMessage.debugMsg("DocLayoutId"+DocLayoutId);
	   String APlanMstId=dbActionTemplate.getSingleValue("SELECT APLM_KEYID FROM GEN_TL_ACTIONPLANMST WHERE APLM_MASTERREFID='"+NearMissKeyid+"' ");
	   //CommonMessage.debugMsg("APlanMstId"+APlanMstId);
	   String APlanDtlId=dbActionTemplate.getSingleValue("SELECT APLD_KEYID FROM GEN_TL_ACTIONPLANDTL WHERE APLD_APLM_KEYID='"+APlanMstId+"' ");
	  // CommonMessage.debugMsg("APlanDtlId"+APlanDtlId);
	   sqls.add("DELETE FROM GEN_TL_NEARMISSREPORTMSTNEW WHERE NMRN_KEYID='"+NearMissKeyid+"' ");
	   sqls.add("DELETE FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_KEYID='"+DocMgrId+"' "); 
	   sqls.add("DELETE FROM DCM_TL_DOCUMENTLAYOUT WHERE DMLY_KEYID='"+DocLayoutId+"' ");
	   sqls.add("DELETE FROM GEN_TL_ACTIONPLANDTL WHERE APLD_KEYID='"+APlanDtlId+"' ");
	   sqls.add("DELETE FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"+APlanMstId+"' ");
	  // CommonMessage.debugMsg("NearMiss Delete"+sqls);
	   dbActionTemplate.executeStatements(sqls);
}

public List<String[]> getAbnClosureData(GridParams gridParams, CommonFilter commonFilter) throws Exception{
	
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String TagClassId=commonFilter.getAbnCause();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	
	if(UIUtils.isValidKeyId(commonFilter.getFromDate()  )){
		condParams +="FROMDATE="+commonFilter.getFromDate()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getToDate())){
		condParams +="TODATE="+commonFilter.getToDate()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAbnCause())){
		condParams +="TAGCLASSID="+commonFilter.getAbnCause()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParams +="DMT="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAwise())){
		condParams +="JH="+commonFilter.getAwise()+";";
	}

	
	
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
			List<String[]> result =  fnCallApi.callFunction("app_fn_abnormalityclosure_sb", paramValues,2,true);
			
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				
				if(  isInteger )
				{
					long counts=Long.parseLong(totalCnt);
					gridParams.setTotalRecordCnt(counts);
				}
			
				
				
				return result;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
				/*
				 * 
				 * String sql = getAbnClosureGrid(commonFilter); String
				 * outerSql="select * from("+sql.toString()+")where 1=1 ";
				 * if(gridParams.getGridFilters()!=null)
				 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				 * String count="select count(*) from("+outerSql+")";
				 * //CommonMessage.debugMsg("count in daoimpl"+count); String
				 * rowCount=dbActionTemplate.getSingleValue(count);
				 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
				 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
				 * List<String> params= new ArrayList<String>();
				 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
				 * //String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
				 * +outerSql+") a ) where slno >= ? and slno <= ?"; String finalSql =
				 * "SELECT * FROM (" + "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" +
				 * outerSql + ") a" +
				 * ") t WHERE slno >= CAST(? AS BIGINT) AND slno <= CAST(? AS BIGINT)";
				 * 
				 * //CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
				 * result=dbActionTemplate.getDataList(finalSql, params); return result;
				 */
}


public String getAbnClosureGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String TagClassId=commonFilter.getAbnCause();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	//CommonMessage.debugMsg("DMT"+DMT);
	//CommonMessage.debugMsg("Jh"+JH);
	
//	 if(UIUtils.isValidKeyId(fromDate) && UIUtils.isValidKeyId(toDate)){
//	   // CommonMessage.debugMsg("If");
//		sql.append("SELECT ABNM_KEYID AS AbnmKeyid,TO_CHAR(ABNM_DATE,'DD-MON-YYYY') AS AbnmDate,DMT AS txtDMT,JH AS txtJH,TAGM_NAME AS AbnmTagclassid, ");
//		sql.append("ABNM_DESCRIPTION AS AbnmDescription,DECODE(ABNM_STATUS,'P','Pending','C','Completed') AS cmbAbnmStatus,");
//		sql.append("ABNM_COUNTERMEASURE AS AbnmCountermeasure,REPLACE(TO_CHAR(ABNM_WOENDTIME,'DD-Mon-YYYY'),'01-Jan-1801','') AS AbnmWoendtime, ");
//		sql.append("EMPM_NAME AS cmbAbnmCompletedby, ABNM_COMPLETEDBY AS Completedby,'' AS SUBMIT ");
//		sql.append("FROM ABN_TL_ABNORMALITY,GEN_TL_EMPLOYEEMST,ABN_TL_TAGMST,gen_mv_flidhierarchy WHERE ABNM_STATUS='P' ");
//		sql.append("AND ABNM_COMPLETEDBY=EMPM_KEYID(+) AND ABNM_TAGCLASSID=TAGM_KEYID(+) AND ABNM_FLID=FLID(+) AND ABNM_DATE ");
//		sql.append("BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
//		if(UIUtils.isValidKeyId(TagClassId)){
//			sql.append(" AND ABNM_TAGCLASSID='"+TagClassId+"' ");
//		}
//		else if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
//			sql.append("AND FLID='"+DMT+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(JH)){
//			//CommonMessage.debugMsg("Else If:"+sql.toString());
//			sql.append("AND FLID='"+JH+"' ");
//		}
//
//		sql.append(" ORDER BY ABNM_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	}
//	else{
//		sql.append("SELECT ABNM_KEYID AS AbnmKeyid,TO_CHAR(ABNM_DATE,'DD-MON-YYYY') AS AbnmDate,DMT AS txtDMT,JH AS txtJH,TAGM_NAME AS AbnmTagclassid, ");
//		sql.append("ABNM_DESCRIPTION AS AbnmDescription,DECODE(ABNM_STATUS,'P','Pending','C','Completed') AS cmbAbnmStatus,");
//		sql.append("ABNM_COUNTERMEASURE AS AbnmCountermeasure,REPLACE(TO_CHAR(ABNM_WOENDTIME,'DD-Mon-YYYY'),'01-Jan-1801','') AS AbnmWoendtime, ");
//		sql.append("EMPM_NAME AS cmbAbnmCompletedby, ABNM_COMPLETEDBY AS Completedby,'' AS SUBMIT ");
//		sql.append("FROM ABN_TL_ABNORMALITY,GEN_TL_EMPLOYEEMST,ABN_TL_TAGMST,gen_mv_flidhierarchy WHERE ABNM_STATUS='P' ");
//		sql.append("AND ABNM_COMPLETEDBY=EMPM_KEYID(+) AND ABNM_TAGCLASSID=TAGM_KEYID(+) AND ABNM_FLID=FLID(+) AND ABNM_DATE ");
//		sql.append("BETWEEN '01-Jan-1801' AND '31-DEC-2100' ");
//		if(UIUtils.isValidKeyId(TagClassId)){
//			sql.append(" AND ABNM_TAGCLASSID='"+TagClassId+"' ");
//		}
	if(UIUtils.isValidKeyId(fromDate) && UIUtils.isValidKeyId(toDate)){
	    sql.append("SELECT ABNM_KEYID AS AbnmKeyid, TO_CHAR(ABNM_DATE,'DD-Mon-YYYY') AS AbnmDate, DMT AS txtDMT, JH AS txtJH, TAGM_NAME AS AbnmTagclassid, ");
	    sql.append("ABNM_DESCRIPTION AS AbnmDescription, CASE WHEN ABNM_STATUS='P' THEN 'Pending' WHEN ABNM_STATUS='C' THEN 'Completed' END AS cmbAbnmStatus, ");
	    sql.append("ABNM_COUNTERMEASURE AS AbnmCountermeasure, REPLACE(TO_CHAR(ABNM_WOENDTIME,'DD-Mon-YYYY'),'01-Jan-1801','') AS AbnmWoendtime, ");
	    sql.append("EMPM_NAME AS cmbAbnmCompletedby, ABNM_COMPLETEDBY AS Completedby, '' AS SUBMIT ");
	    sql.append("FROM ABN_TL_ABNORMALITY ");
	    sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST ON ABNM_COMPLETEDBY=EMPM_KEYID ");
	    sql.append("LEFT JOIN ABN_TL_TAGMST ON ABNM_TAGCLASSID=TAGM_KEYID ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON ABNM_FLID=FLID ");
	    sql.append("WHERE ABNM_STATUS='P' ");
	    sql.append("AND ABNM_DATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(TagClassId)){
	        sql.append(" AND ABNM_TAGCLASSID='" + TagClassId + "' ");
	    }
	    else if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    sql.append(" ORDER BY ABNM_KEYID DESC ");
	}
	else{
	    sql.append("SELECT ABNM_KEYID AS AbnmKeyid, TO_CHAR(ABNM_DATE,'DD-Mon-YYYY') AS AbnmDate, DMT AS txtDMT, JH AS txtJH, TAGM_NAME AS AbnmTagclassid, ");
	    sql.append("ABNM_DESCRIPTION AS AbnmDescription, CASE WHEN ABNM_STATUS='P' THEN 'Pending' WHEN ABNM_STATUS='C' THEN 'Completed' END AS cmbAbnmStatus, ");
	    sql.append("ABNM_COUNTERMEASURE AS AbnmCountermeasure, REPLACE(TO_CHAR(ABNM_WOENDTIME,'DD-Mon-YYYY'),'01-Jan-1801','') AS AbnmWoendtime, ");
	    sql.append("EMPM_NAME AS cmbAbnmCompletedby, ABNM_COMPLETEDBY AS Completedby, '' AS SUBMIT ");
	    sql.append("FROM ABN_TL_ABNORMALITY ");
	    sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST ON ABNM_COMPLETEDBY=EMPM_KEYID ");
	    sql.append("LEFT JOIN ABN_TL_TAGMST ON ABNM_TAGCLASSID=TAGM_KEYID ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON ABNM_FLID=FLID ");
	    sql.append("WHERE ABNM_STATUS='P' ");
	    sql.append("AND ABNM_DATE BETWEEN '1801-01-01' AND '2100-12-31' ");
	    
	    if(UIUtils.isValidKeyId(TagClassId)){
	        sql.append(" AND ABNM_TAGCLASSID='" + TagClassId + "' ");
	    }
	    
	    sql.append(" ORDER BY ABNM_KEYID DESC ");
	}
		
	 CommonMessage.debugMsg("sql in Abnormality Closure Data"+sql.toString());
	return sql.toString();
}
///***************



///*********

public void AbnClosure(String AbnmKeyid,String Status,String CounterMeasure,String CompletedDate,String CompletedBy) throws Exception{
	
	
	List<String> sql= new ArrayList<String>();
	
	  try{
		  sql.add(" UPDATE GEN_TL_ACTIONPLANDTL SET  APLD_STATUS='"+Status+"',APLD_COMPLETEDBY=APLD_RESPONSIBILITY,APLD_COMPLEATEDON=APLD_TARGETDATE, APLD_COUNTERMEASURE='"+CounterMeasure+"' WHERE APLD_APLM_KEYID =(SELECT APLM_KEYID FROM GEN_TL_ACTIONPLANMST WHERE APLM_DETAILREFID='"+AbnmKeyid+"')");
		  sql.add(" UPDATE GEN_TL_ACTIONPLANMST SET APLM_STATUS='"+Status+"' WHERE APLM_DETAILREFID='"+AbnmKeyid+"'");
		  sql.add(" UPDATE ABN_TL_ABNORMALITY SET ABNM_STATUS ='"+Status+"', ABNM_COUNTERMEASURE='"+CounterMeasure+"', ABNM_WOENDTIME ='"+CompletedDate+"', ABNM_COMPLETEDBY ='"+CompletedBy+"'  WHERE ABNM_KEYID ='"+AbnmKeyid+"'");
	
  //  CommonMessage.debugMsg("Update ABNClosure :"+sql);
   

    dbActionTemplate.executeStatements(sql);
    
    
    }
    catch(Exception e){
    	
    	e.printStackTrace();
	}
    	
    
    
	
}
public List<String[]> getActionPlanClosureData(GridParams gridParams, CommonFilter commonFilter) throws Exception{
	
	
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	
	String ActionPlanType=commonFilter.getType();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	
	if(UIUtils.isValidKeyId(commonFilter.getFromDate()  )){
		condParams +="FROMDATE="+commonFilter.getFromDate()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getToDate())){
		condParams +="TODATE="+commonFilter.getToDate()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getType())){
		condParams +="ACTIONPLANTYPE="+commonFilter.getType()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParams +="DMT="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAwise())){
		condParams +="JH="+commonFilter.getAwise()+";";
	}

	
	
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
			List<String[]> result =  fnCallApi.callFunction("app_fn_actionplanclosure_sb", paramValues,2,true);
			
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				
				if(  isInteger )
				{
					long counts=Long.parseLong(totalCnt);
					gridParams.setTotalRecordCnt(counts);
				}
				return result;
	
	
	
	
	
				/*
				 * 
				 * 
				 * String sql = getActionPlanClosureGrid(commonFilter); String
				 * outerSql="select * from("+sql.toString()+")where 1=1 ";
				 * if(gridParams.getGridFilters()!=null)
				 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				 * String count="select count(*) from("+outerSql+")";
				 * //CommonMessage.debugMsg("count in daoimpl"+count); String
				 * rowCount=dbActionTemplate.getSingleValue(count);
				 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
				 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
				 * List<String> params= new ArrayList<String>();
				 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
				 * //String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
				 * +outerSql+") a ) where slno >= ? and slno <= ?"; String finalSql =
				 * "SELECT * FROM (" + "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" +
				 * outerSql + ") a" +
				 * ") t WHERE slno >= CAST(? AS BIGINT) AND slno <= CAST(? AS BIGINT)";
				 * 
				 * //CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
				 * result=dbActionTemplate.getDataList(finalSql, params); return result;
				 */
	
}

public String getActionPlanClosureGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String Type=commonFilter.getActionKeyId();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();

	
	if(UIUtils.isValidKeyId(fromDate) && UIUtils.isValidKeyId(toDate)){
	    sql.append("SELECT APLM_KEYID AS AplmKeyid, APLD_KEYID AS ApldKeyid, TO_CHAR(APLM_PLANDATE, 'DD-Mon-YYYY') AS AplmPlandate, ");
	    sql.append("APLM_REFDOCTYPE AS AplmRefdoctype, DMT AS txtDMT, JH AS txtJH, APLD_ACTIONPLAN AS ApldActionplan, EMPM_NAME AS Responsibility, ");
	    sql.append("APLD_TARGETDATE AS ApldTargetdate, APLD_STATUS AS ApldStatus, REPLACE(TO_CHAR(APLD_COMPLEATEDON,'DD-Mon-YYYY'),'31-Dec-2100','') AS dteApldCompleatedon, ");
	    sql.append("APLD_COMPLETEDBY AS ApldCompletedby, '' AS SUBMIT ");
	    sql.append("FROM GEN_TL_ACTIONPLANMST ");
	    sql.append("LEFT JOIN GEN_TL_ACTIONPLANDTL ON APLM_KEYID = APLD_APLM_KEYID ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON APLM_FLID=FLID ");
	    sql.append("INNER JOIN GEN_TL_EMPLOYEEMST ON APLD_RESPONSIBILITY=EMPM_KEYID ");
	    sql.append("WHERE APLD_STATUS='P' ");
	    sql.append("AND APLM_PLANDATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(Type)){
	        sql.append(" AND APLM_REFDOCTYPE='" + Type + "' ");
	    }
	    else if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    sql.append(" ORDER BY APLM_KEYID DESC ");
	}
	else{
	    sql.append("SELECT APLM_KEYID AS AplmKeyid, APLD_KEYID AS ApldKeyid, TO_CHAR(APLM_PLANDATE, 'DD-Mon-YYYY') AS AplmPlandate, ");
	    sql.append("APLM_REFDOCTYPE AS AplmRefdoctype, DMT AS txtDMT, JH AS txtJH, APLD_ACTIONPLAN AS ApldActionplan, EMPM_NAME AS Responsibility, ");
	    sql.append("APLD_TARGETDATE AS ApldTargetdate, APLD_STATUS AS ApldStatus, REPLACE(TO_CHAR(APLD_COMPLEATEDON,'DD-Mon-YYYY'),'31-Dec-2100','') AS dteApldCompleatedon, ");
	    sql.append("APLD_COMPLETEDBY AS ApldCompletedby, '' AS SUBMIT ");
	    sql.append("FROM GEN_TL_ACTIONPLANMST ");
	    sql.append("LEFT JOIN GEN_TL_ACTIONPLANDTL ON APLM_KEYID = APLD_APLM_KEYID ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON APLM_FLID=FLID ");
	    sql.append("INNER JOIN GEN_TL_EMPLOYEEMST ON APLD_RESPONSIBILITY=EMPM_KEYID ");
	    sql.append("WHERE APLD_STATUS='P' ");
	    sql.append("AND APLM_PLANDATE BETWEEN '1801-01-01' AND '2100-12-31' ");
	    
	    if(UIUtils.isValidKeyId(Type)){
	        sql.append(" AND APLM_REFDOCTYPE='" + Type + "' ");
	    }
	    
	    sql.append(" ORDER BY APLM_KEYID DESC ");
	}
	return sql.toString();
}
public void ActionPlanClosure(String ActionPlanId,String DetailId,String Status,String CompletedOn,String CompletedBy,String CounterMeasure) throws Exception{
	List<String> sql=new ArrayList<String>();	
	
	sql.add("UPDATE GEN_TL_ACTIONPLANDTL SET APLD_STATUS='"+Status+"',APLD_COMPLEATEDON='"+CompletedOn+"',APLD_COMPLETEDBY='"+CompletedBy+"',APLD_COUNTERMEASURE='"+CounterMeasure+"' WHERE APLD_KEYID='"+DetailId+"' ");
	sql.add(
		    "UPDATE GEN_TL_ACTIONPLANMST " +
		    "SET APLM_STATUS = COALESCE(" +
		    "       (SELECT DISTINCT APLD_STATUS " +
		    "        FROM GEN_TL_ACTIONPLANDTL " +
		    "        WHERE APLD_APLM_KEYID = '" + ActionPlanId + "' " +
		    "          AND APLD_STATUS = 'P'), " +
		    "       'C'" +
		    "   ) " +
		    "WHERE APLM_KEYID = '" + ActionPlanId + "'"
		);

	CommonMessage.debugMsg("ActionPlan Update"+sql);
	dbActionTemplate.executeStatements(sql);	
}
public List<String[]> getFIProjectData(GridParams gridParams, CommonFilter commonFilter) throws Exception{
	
	
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	
	String ActionPlanType=commonFilter.getType();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	
	if(UIUtils.isValidKeyId(commonFilter.getFromDate()  )){
		condParams +="FROMDATE="+commonFilter.getFromDate()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getToDate())){
		condParams +="TODATE="+commonFilter.getToDate()+";";
	}
	
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParams +="DMT="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAwise())){
		condParams +="JH="+commonFilter.getAwise()+";";
	}

	
	
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
			List<String[]> result =  fnCallApi.callFunction("app_fn_fipproject_sb", paramValues,2,true);
			
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				
				if(  isInteger )
				{
					long counts=Long.parseLong(totalCnt);
					gridParams.setTotalRecordCnt(counts);
				}
				return result;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
				/*
				 * 
				 * 
				 * String sql = getFIProjectGrid(commonFilter); String
				 * outerSql="select * from("+sql.toString()+")where 1=1 ";
				 * if(gridParams.getGridFilters()!=null)
				 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				 * String count="select count(*) from("+outerSql+")";
				 * //CommonMessage.debugMsg("count in daoimpl"+count); String
				 * rowCount=dbActionTemplate.getSingleValue(count);
				 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
				 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
				 * List<String> params= new ArrayList<String>();
				 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
				 * //String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
				 * +outerSql+") a ) where slno >= ? and slno <= ?"; String finalSql =
				 * "SELECT * FROM (" + "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" +
				 * outerSql + ") a" +
				 * ") t WHERE slno >= CAST(? AS BIGINT) AND slno <= CAST(? AS BIGINT)";
				 * 
				 * //CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
				 * result=dbActionTemplate.getDataList(finalSql, params); return result;
				 */
}
//Swetha change 25 November
public String getFIProjectGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
//
//	if(fromDate !=null && toDate!=null){
//		sql.append("SELECT KZPM_KEYID AS KzpmKeyid,DMT AS txtDMT,TO_CHAR (TO_DATE(KZPM_STARTDATE), 'DD-MON-YYYY') AS KzpmStartdate,");
//		sql.append("TO_CHAR (TO_DATE(KZPM_ENDDATE), 'DD-MON-YYYY') AS KzpmEnddate,'' AS SUBMIT ");
//		sql.append("FROM KZN_TL_PROJECTCREATIONMST,gen_mv_flidhierarchy WHERE KZPM_STARTDATE BETWEEN '"+fromDate+"' AND '"+toDate+"' AND KZPM_FLID=FLID(+) ");
//		
//		if(UIUtils.isValidKeyId(DMT)){
//			sql.append("AND FLID='"+DMT+"' ");
//		}
//		sql.append(" ORDER BY KZpm_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	}
//	else{
//	sql.append("SELECT KZPM_KEYID AS KznmKeyid,DMT AS txtDMT,TO_CHAR (TO_DATE(KZPM_STARTDATE), 'DD-MON-YYYY') AS KzpmStartdate,");
//	sql.append("TO_CHAR (TO_DATE(KZPM_ENDDATE), 'DD-MON-YYYY') AS KzpmEnddate,'' AS SUBMIT ");
//	sql.append("FROM KZN_TL_PROJECTCREATIONMST,gen_mv_flidhierarchy WHERE KZPM_STARTDATE BETWEEN '01-Jan-1801' AND '31-DEC-2100' AND KZPM_FLID=FLID(+) ");
//	sql.append(" ORDER BY KZPM_KEYID DESC ");
//	//CommonMessage.debugMsg("sql FIProject Data"+sql.toString());
//	}
	if(fromDate != null && toDate != null){
	    sql.append("SELECT KZPM_KEYID AS KzpmKeyid, DMT AS txtDMT, TO_CHAR(KZPM_STARTDATE::DATE, 'DD-Mon-YYYY') AS KzpmStartdate, ");
	    sql.append("TO_CHAR(KZPM_ENDDATE::DATE, 'DD-Mon-YYYY') AS KzpmEnddate, '' AS SUBMIT ");
	    sql.append("FROM KZN_TL_PROJECTCREATIONMST ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON KZPM_FLID=FLID ");
	    sql.append("WHERE KZPM_STARTDATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(DMT)){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    
	    sql.append(" ORDER BY KZPM_KEYID DESC ");
	}
	else{
	    sql.append("SELECT KZPM_KEYID AS KznmKeyid, DMT AS txtDMT, TO_CHAR(KZPM_STARTDATE::DATE, 'DD-Mon-YYYY') AS KzpmStartdate, ");
	    sql.append("TO_CHAR(KZPM_ENDDATE::DATE, 'DD-Mon-YYYY') AS KzpmEnddate, '' AS SUBMIT ");
	    sql.append("FROM KZN_TL_PROJECTCREATIONMST ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON KZPM_FLID=FLID ");
	    sql.append("WHERE KZPM_STARTDATE BETWEEN '1801-01-01' AND '2100-12-31' ");
	    sql.append(" ORDER BY KZPM_KEYID DESC ");
	}
	return sql.toString();
	}

public void UpdateFIProjectDate(String FIProjectId,String EndDate) throws Exception{
	StringBuilder sql=new StringBuilder();
	sql.append("UPDATE KZN_TL_PROJECTCREATIONMST SET KZPM_ENDDATE=TO_DATE('"+EndDate+"') WHERE KZPM_KEYID='"+FIProjectId+"'");
	//CommonMessage.debugMsg("Change Date"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}


@Override
public List<String[]> getAppMaintGrid(GridParams gridParams, CommonFilter commonFilter) throws Exception {
	
	
	
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	String Type=commonFilter.getTaskid();
	if(UIUtils.isValidKeyId(commonFilter.getTaskid())){
		condParams +="TYPE="+commonFilter.getTaskid()+";";
	}
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
	List<String[]> result =  fnCallApi.callFunction("app_fn_appmaingrid_sb", paramValues,2,true);
	
		String totalCnt = paramValues.get(0); 
		CommonMessage.debugMsg("totalCnt..."+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		
		if(  isInteger )
		{
			long counts=Long.parseLong(totalCnt);
			gridParams.setTotalRecordCnt(counts);
		}
	
		
		
		return result;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		/*
		 * // TODO Auto-generated method stub String Type=commonFilter.getTaskid();
		 * //CommonMessage.debugMsg("Type::::"+Type); String sql =
		 * getAppGrid(commonFilter,Type); String
		 * outerSql="select * from("+sql.toString()+")where 1=1 ";
		 * if(gridParams.getGridFilters()!=null)
		 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
		 * String count="select count(*) from("+outerSql+")";
		 * //CommonMessage.debugMsg("count in daoimpl"+count); String
		 * rowCount=dbActionTemplate.getSingleValue(count);
		 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
		 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
		 * List<String> params= new ArrayList<String>();
		 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
		 * String finalSql = "SELECT * FROM (" +
		 * "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" + outerSql + ") a" +
		 * ") t WHERE slno >= CAST(? AS bigint) AND slno <= CAST(? AS bigint)";
		 * CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
		 * result=dbActionTemplate.getDataList(finalSql, params); return result;
		 */
}
public String getAppGrid(CommonFilter commonFilter,String Type){
	StringBuffer sql =new StringBuffer();
	
	if(UIUtils.isValidKeyId(Type)){	
		sql.append("SELECT ADMM_KEYID AS txtAdmmkeyid,ADMM_CODE AS txtAdmmCode,ADMM_NAME AS txtAdmmname FROM ADM_TL_ADMINMENUMST WHERE ADMM_KEYID='"+Type+"' ");
		sql.append("ORDER BY ADMM_KEYID");
		
	}
	else{
	sql.append("SELECT ADMM_KEYID AS txtAdmmkeyid,ADMM_CODE AS txtAdmmCode,ADMM_NAME AS txtAdmmname FROM ADM_TL_ADMINMENUMST ");
	sql.append("ORDER BY ADMM_KEYID");
	}
	
	return sql.toString();
	
}	
public List<String[]> getEmployeeRole(GridParams gridParams, CommonFilter commonFilter) throws Exception{
	// TODO Auto-generated method stub
	//	String EmpId=commonFilter.getEmpch();
		//CommonMessage.debugMsg("EmpId::::"+EmpId);
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	String EmpId=commonFilter.getEmpch();
	if(UIUtils.isValidKeyId(commonFilter.getEmpch())){
		condParams +="EMPCH="+commonFilter.getEmpch()+";";
	}
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
	List<String[]> result =  fnCallApi.callFunction("app_fn_employeerole_sb", paramValues,2,true);
	
		String totalCnt = paramValues.get(0); 
		CommonMessage.debugMsg("totalCnt..."+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		
		if(  isInteger )
		{
			long counts=Long.parseLong(totalCnt);
			gridParams.setTotalRecordCnt(counts);
		}
	
		
		
		return result;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		/*
		 * String sql = getEmpRoleGrid(commonFilter); String
		 * outerSql="select * from("+sql+")where 1=1 ";
		 * 
		 * CommonMessage.debugMsg("OUTER SQL "+outerSql);
		 * if(gridParams.getGridFilters()!=null)
		 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
		 * String count="select count(*) from("+outerSql+")";
		 * 
		 * CommonMessage.debugMsg("count in daoimpl"+count); String
		 * rowCount=dbActionTemplate.getSingleValue(count);
		 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
		 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
		 * List<String> params= new ArrayList<String>();
		 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
		 * //String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
		 * +outerSql+") a ) where slno >= ? and slno <= ?"; String finalSql =
		 * "SELECT * FROM (" + "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" +
		 * outerSql + ") a" +
		 * ") t WHERE slno >= CAST(? AS BIGINT) AND slno <= CAST(? AS BIGINT)";
		 * 
		 * //CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
		 * result=dbActionTemplate.getDataList(finalSql, params); return result;
		 */
	
}
public String getEmpRoleGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	
//	if(UIUtils.isValidKeyId(commonFilter.getEmpch())){	
//		sql.append("SELECT ROLE_NAME AS ROLENAME,EMPM_NAME AS EMPMNAME,EMPM_CODE AS EMPMCODE, NVL (TRDM_NAME, '-') AS TRADENAME, DMT AS DMT, JH AS JH ");
//		sql.append("FROM GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST,GEN_TL_TEAMTRADELINK,GEN_TL_TRADEMST,GEN_MV_FLIDHIERARCHY,ADM_TL_USERMST ");
//		sql.append("WHERE FRT_ROLE_KEYID=ROLE_KEYID AND FRT_EMPM_KEYID=EMPM_KEYID AND FRT_KEYID=FRP_FRT_KEYID(+) AND FRP_TRADEID=TRDM_KEYID(+) ");
//		sql.append("AND EMPM_KEYID=USRM_CCNO(+) AND FRT_FNLN_KEYID=FLID(+) AND EMPM_KEYID='"+commonFilter.getEmpch()+"' ");
//		sql.append("ORDER BY ROLE_NAME,EMPM_NAME");
//	}
	
	if(UIUtils.isValidKeyId(commonFilter.getEmpch())){
	    sql.append("SELECT ROLE_NAME AS ROLENAME, EMPM_NAME AS EMPMNAME, EMPM_CODE AS EMPMCODE, COALESCE(TRDM_NAME, '-') AS TRADENAME, DMT AS DMT, JH AS JH ");
	    sql.append("FROM GEN_TL_FNLNROLETEAM ");
	    sql.append("INNER JOIN ADM_TL_ROLEMST ON FRT_ROLE_KEYID=ROLE_KEYID ");
	    sql.append("INNER JOIN GEN_TL_EMPLOYEEMST ON FRT_EMPM_KEYID=EMPM_KEYID ");
	    sql.append("LEFT JOIN GEN_TL_TEAMTRADELINK ON FRT_KEYID=FRP_FRT_KEYID ");
	    sql.append("LEFT JOIN GEN_TL_TRADEMST ON FRP_TRADEID=TRDM_KEYID ");
	    sql.append("LEFT JOIN ADM_TL_USERMST ON EMPM_KEYID=USRM_CCNO ");
	    sql.append("LEFT JOIN GEN_MV_FLIDHIERARCHY ON FRT_FNLN_KEYID=FLID ");
	    sql.append("WHERE EMPM_KEYID='" + commonFilter.getEmpch() + "' ");
	    sql.append("ORDER BY ROLE_NAME, EMPM_NAME");
	}
	CommonMessage.debugMsg("SQL"+sql.toString());
	return sql.toString();
	
}	

public List<String[]> getRoleList(GridParams gridParams, CommonFilter commonFilter) throws Exception
{
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	String EmpId=commonFilter.getKey();
	
	
	if(UIUtils.isValidKeyId(commonFilter.getKey())){
		condParams +="KEY="+commonFilter.getKey()+";";
	}
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
	List<String[]> result =  fnCallApi.callFunction("app_fn_rolelist_sb", paramValues,2,true);
	
		String totalCnt = paramValues.get(0); 
		CommonMessage.debugMsg("totalCnt..."+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		
		if(  isInteger )
		{
			long counts=Long.parseLong(totalCnt);
			gridParams.setTotalRecordCnt(counts);
		}
	
		
		
		return result;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		/*
		 * 
		 * 
		 * 
		 * String sql = getRoleListGrid(commonFilter); String
		 * outerSql="select * from("+sql.toString()+")where 1=1 ";
		 * if(gridParams.getGridFilters()!=null)
		 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
		 * String count="select count(*) from("+outerSql+")";
		 * //CommonMessage.debugMsg("count in daoimpl"+count); String
		 * rowCount=dbActionTemplate.getSingleValue(count);
		 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
		 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
		 * List<String> params= new ArrayList<String>();
		 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
		 * //String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
		 * +outerSql+") a ) where slno >= ? and slno <= ?"; String finalSql =
		 * "SELECT * FROM (" + "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" +
		 * outerSql + ") a" +
		 * ") t WHERE slno >= CAST(? AS BIGINT) AND slno <= CAST(? AS BIGINT)";
		 * 
		 * //CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
		 * result=dbActionTemplate.getDataList(finalSql, params); return result;
		 */
}

public String getRoleListGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	//CommonMessage.debugMsg("Level::"+commonFilter.getKey());
	if(UIUtils.isValidKeyId(commonFilter.getKey())){	
		sql.append("SELECT FRL_KEYID keyid,ROLE_KEYID,ROLE_NAME,ROLE_CODE");
		sql.append(" FROM adm_tl_rolemst, gen_tl_fnlnrolemap ");
		sql.append(" WHERE ROLE_ACTIVE = 'Y'  AND FRL_ROLE_KEYID = ROLE_KEYID ");
		sql.append(" AND FRL_LEVEL <= '"+commonFilter.getKey()+"'  AND FRL_LEVEL >'"+commonFilter.getKey()+"' -1 ");
		sql.append("ORDER BY ROLE_NAME");
	}
	//CommonMessage.debugMsg("SQL"+sql);
	return sql.toString();
}	


public List<String[]>  getlevelrole(String flId) throws Exception {

	//CommonMessage.debugMsg("+ Character.toChars(39) +"+ Character.toChars(39));
	
	String flidval=dbActionTemplate.getSingleValue("SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='"+flId+"' ");
	//CommonMessage.debugMsg("Flid Value"+flidval);
	
    String Sql=" select FLID,LEVEL,LOCN_KEYID From GEN_MV_FLIDHIERARCHY,GEN_VW_FNLN WHERE 1=1 AND FNLN_KEYID=FLID ";
    if (CommonFunctions.isValidKeyId(flidval))
    {
    	Sql = Sql + " AND FLID = '" + flidval +"' ";
    }
    
   // CommonMessage.debugMsg("reloadAllGrids sql" + Sql);
	return dbActionTemplate.getDataList(Sql);
}

@Override
public void UserInActive(String empKeyid, String validTill, String remarks) throws Exception {
	// TODO Auto-generated method stub
	StringBuffer userSql =new StringBuffer();
	StringBuffer empSql =new StringBuffer();
	

	
		CommonMessage.debugMsg("Else");
		
		//sql.append("UPDATE ADM_TL_USERMST SET usrm_isvalidityreq='Y',USRM_ACTIVE='N',USRM_REMARKS='"+remarks+"',USRM_VALIDTILL='' WHERE USRM_CCNO='"+empKeyid+"' ");
		userSql.append(    "UPDATE ADM_TL_USERMST " +
			    "SET usrm_isvalidityreq = 'Y', " +
			    "    USRM_ISACTIVE = 'N', " +
			    "    USRM_REMARKS = '" + remarks + "', " +
			    "    USRM_VALIDTILL = CURRENT_DATE " +        // ← Set to today's date
			    "WHERE USRM_CCNO = '" + empKeyid + "'"
			);
		CommonMessage.debugMsg("User inactive query "+userSql);
		
		empSql.append("UPDATE GEN_TL_EMPLOYEEMST SET EMPM_ACTIVE = 'N' WHERE EMPM_KEYID = '" +empKeyid+"'");
		CommonMessage.debugMsg("Employee inactive query "+empSql);
		
		List<String> sqls = new ArrayList<>();
		sqls.add(userSql.toString());
		sqls.add(empSql.toString());
		//dbActionTemplate.executeStatement(userSql.toString());
		dbActionTemplate.executeStatements(sqls);
		//UpdateEmployeeInactive(empKeyid,remarks);
    	//CommonMessage.debugMsg("Dao sql:" +sql);

	}
//Swetha modified 25 November Application Maintenance
@Override
public List<String[]> getAbnDeleteData(GridParams gridParams, CommonFilter commonFilter) throws Exception {
	
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String TagClassId=commonFilter.getAbnCause();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	
	List<String> paramValues = new ArrayList<String>();		
	String condParams = "";
	String commonParams = "";
	
	if(UIUtils.isValidKeyId(commonFilter.getFromDate()  )){
		condParams +="FROMDATE="+commonFilter.getFromDate()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getToDate())){
		condParams +="TODATE="+commonFilter.getToDate()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAbnCause())){
		condParams +="TAGCLASSID="+commonFilter.getAbnCause()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParams +="DMT="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getAwise())){
		condParams +="JH="+commonFilter.getAwise()+";";
	}

	
	
	if(gridParams.getGridFilters()!=null){
		//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
		commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
	}
	
	commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
	
	paramValues.add(condParams);
	paramValues.add(commonParams);
	
	//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
			List<String[]> result =  fnCallApi.callFunction("app_fn_abnormalitydeletegrid_sb", paramValues,2,true);
			
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				
				if(  isInteger )
				{
					long counts=Long.parseLong(totalCnt);
					gridParams.setTotalRecordCnt(counts);
				}
			
				
				
				return result;
				/*
				 * paramValues.add(condParams); paramValues.add(commonParams);
				 * 
				 * 
				 * String sql = getAbnDeleteGrid(commonFilter);
				 * 
				 * String outerSql="select * from("+sql.toString()+")where 1=1 ";
				 * if(gridParams.getGridFilters()!=null)
				 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				 * 
				 * String count="select count(*) from("+outerSql+")";
				 * //CommonMessage.debugMsg("count in daoimpl"+count);
				 * 
				 * String rowCount=dbActionTemplate.getSingleValue(count);
				 * //CommonMessage.debugMsg("rowCount in daoimpl"+rowCount);
				 * 
				 * long counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
				 * List<String> params= new ArrayList<String>();
				 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
				 * 
				 * String finalSql = "SELECT * FROM (" +
				 * "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" + outerSql + ") a" +
				 * ") t WHERE slno >= CAST(? AS bigint) AND slno <= CAST(? AS bigint)";
				 * 
				 * //String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
				 * +outerSql+") a ) where slno >= ? and slno <= ?";
				 * CommonMessage.debugMsg("finalSql in Dao Abnormality"+finalSql); List<String[]>
				 * result=dbActionTemplate.getDataList(finalSql, params); return result;
				 */
}	
public String getAbnDeleteGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String TagClassId=commonFilter.getAbnCause();
	//CommonMessage.debugMsg("fromDate"+fromDate);
	//CommonMessage.debugMsg("toDate"+toDate);
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	//CommonMessage.debugMsg("DMT"+DMT);
	//CommonMessage.debugMsg("Jh"+JH);
	
//	 if(UIUtils.isValidKeyId(fromDate) && UIUtils.isValidKeyId(toDate)){
//	    //CommonMessage.debugMsg("If");
//		sql.append("SELECT ABNM_KEYID AS AbnmKeyid,TO_CHAR(ABNM_DATE,'DD-MON-YYYY') AS AbnmDate,DMT AS txtDMT,JH AS txtJH,TAGM_NAME AS AbnmTagclassid, ");
//		sql.append("ABNM_DESCRIPTION AS AbnmDescription,DECODE(ABNM_STATUS,'P','Pending','C','Completed') AS cmbAbnmStatus,");
//		sql.append(" '' AS SUBMIT ");
//		sql.append("FROM ABN_TL_ABNORMALITY,GEN_TL_EMPLOYEEMST,ABN_TL_TAGMST,gen_mv_flidhierarchy WHERE ABNM_STATUS='P' ");
//		sql.append("AND ABNM_COMPLETEDBY=EMPM_KEYID(+) AND ABNM_TAGCLASSID=TAGM_KEYID(+) AND ABNM_FLID=FLID(+) AND ABNM_DATE ");
//		sql.append("BETWEEN '"+fromDate+"' AND '"+toDate+"' ");
//		if(UIUtils.isValidKeyId(TagClassId)){
//			sql.append(" AND ABNM_TAGCLASSID='"+TagClassId+"' ");
//		}
//		else if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
//			sql.append("AND FLID='"+DMT+"' ");
//		}
//		
//		else if(UIUtils.isValidKeyId(JH)){
//			//CommonMessage.debugMsg("Else If:"+sql.toString());
//			sql.append("AND FLID='"+JH+"' ");
//		}
//
//		sql.append(" ORDER BY ABNM_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	}
//	else{
//		sql.append("SELECT ABNM_KEYID AS AbnmKeyid,TO_CHAR(ABNM_DATE,'DD-MON-YYYY') AS AbnmDate,DMT AS txtDMT,JH AS txtJH,TAGM_NAME AS AbnmTagclassid, ");
//		sql.append("ABNM_DESCRIPTION AS AbnmDescription,DECODE(ABNM_STATUS,'P','Pending','C','Completed') AS cmbAbnmStatus,");
//		sql.append(" '' AS SUBMIT ");
//		sql.append("FROM ABN_TL_ABNORMALITY,GEN_TL_EMPLOYEEMST,ABN_TL_TAGMST,gen_mv_flidhierarchy WHERE ABNM_STATUS='P' ");
//		sql.append("AND ABNM_COMPLETEDBY=EMPM_KEYID(+) AND ABNM_TAGCLASSID=TAGM_KEYID(+) AND ABNM_FLID=FLID(+) AND ABNM_DATE ");
//		sql.append("BETWEEN '01-Jan-1801' AND '31-DEC-2100' ");
//		if(UIUtils.isValidKeyId(TagClassId)){
//			sql.append(" AND ABNM_TAGCLASSID='"+TagClassId+"' ");
//		}
//		sql.append(" ORDER BY ABNM_KEYID DESC ");
//		//CommonMessage.debugMsg("If"+sql.toString());
//	    //CommonMessage.debugMsg("sql in Abnormality Closure Data"+sql.toString());
//	
//	}
	
	if(UIUtils.isValidKeyId(fromDate) && UIUtils.isValidKeyId(toDate)){
	    sql.append("SELECT ABNM_KEYID AS AbnmKeyid, TO_CHAR(ABNM_DATE,'DD-Mon-YYYY') AS AbnmDate, DMT AS txtDMT, JH AS txtJH, TAGM_NAME AS AbnmTagclassid, ");
	    sql.append("ABNM_DESCRIPTION AS AbnmDescription, CASE WHEN ABNM_STATUS='P' THEN 'Pending' WHEN ABNM_STATUS='C' THEN 'Completed' END AS cmbAbnmStatus, ");
	    sql.append("'' AS SUBMIT ");
	    sql.append("FROM ABN_TL_ABNORMALITY ");
	    sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST ON ABNM_COMPLETEDBY=EMPM_KEYID ");
	    sql.append("LEFT JOIN ABN_TL_TAGMST ON ABNM_TAGCLASSID=TAGM_KEYID ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON ABNM_FLID=FLID ");
	    sql.append("WHERE ABNM_STATUS='P' ");
	    sql.append("AND ABNM_DATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(TagClassId)){
	        sql.append(" AND ABNM_TAGCLASSID='" + TagClassId + "' ");
	    }
	    else if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    sql.append(" ORDER BY ABNM_KEYID DESC ");
	}
	else{
	    sql.append("SELECT ABNM_KEYID AS AbnmKeyid, TO_CHAR(ABNM_DATE,'DD-Mon-YYYY') AS AbnmDate, DMT AS txtDMT, JH AS txtJH, TAGM_NAME AS AbnmTagclassid, ");
	    sql.append("ABNM_DESCRIPTION AS AbnmDescription, CASE WHEN ABNM_STATUS='P' THEN 'Pending' WHEN ABNM_STATUS='C' THEN 'Completed' END AS cmbAbnmStatus, ");
	    sql.append("'' AS SUBMIT ");
	    sql.append("FROM ABN_TL_ABNORMALITY ");
	    sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST ON ABNM_COMPLETEDBY=EMPM_KEYID ");
	    sql.append("LEFT JOIN ABN_TL_TAGMST ON ABNM_TAGCLASSID=TAGM_KEYID ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON ABNM_FLID=FLID ");
	    sql.append("WHERE ABNM_STATUS='P' ");
	    sql.append("AND ABNM_DATE BETWEEN '1801-01-01' AND '2100-12-31' ");
	    
	    if(UIUtils.isValidKeyId(TagClassId)){
	        sql.append(" AND ABNM_TAGCLASSID='" + TagClassId + "' ");
	    }
	    
	    sql.append(" ORDER BY ABNM_KEYID DESC ");
	}
	 CommonMessage.debugMsg(" Abnormality Closure "+sql.toString());
	return sql.toString();
}

@Override
public void deleteAbnormality(String abnKeyid) throws Exception {
	// TODO Auto-generated method stub
		List<String> sqls=new ArrayList<String>();
		sqls.add(" DELETE FROM GEN_TL_ACTIONPLANDTL WHERE APLD_APLM_KEYID=(SELECT APLM_KEYID FROM GEN_TL_ACTIONPLANMST WHERE APLM_DETAILREFID=(SELECT ABNM_KEYID FROM ABN_TL_ABNORMALITY WHERE ABNM_KEYID='"+abnKeyid+"'))");
		sqls.add(" DELETE FROM GEN_TL_ACTIONPLANMST WHERE APLM_DETAILREFID=(SELECT ABNM_KEYID FROM ABN_TL_ABNORMALITY WHERE ABNM_KEYID='"+abnKeyid+"')");
		sqls.add(" DELETE FROM BDM_TL_WHYWHYDTL WHERE WWDT_WWMS_KEYID=(SELECT WWMS_KEYID FROM BDM_TL_WHYWHYMST WHERE WWMS_REFDOCNO=(SELECT ABNM_KEYID FROM ABN_TL_ABNORMALITY WHERE ABNM_KEYID='"+abnKeyid+"'))");
		sqls.add(" DELETE FROM BDM_TL_YYDONEBYMST WHERE WWDB_WWMS_KEYID=(SELECT WWMS_KEYID FROM BDM_TL_WHYWHYMST WHERE WWMS_REFDOCNO=(SELECT ABNM_KEYID FROM ABN_TL_ABNORMALITY WHERE ABNM_KEYID='"+abnKeyid+"'))");
		sqls.add(" DELETE FROM BDM_TL_YYPROBLEMATTBYMST WHERE WWPA_WWMS_KEYID=(SELECT WWMS_KEYID FROM BDM_TL_WHYWHYMST WHERE WWMS_REFDOCNO=(SELECT ABNM_KEYID FROM ABN_TL_ABNORMALITY WHERE ABNM_KEYID='"+abnKeyid+"'))");
		sqls.add(" DELETE FROM BDM_TL_YYEFFECTIVEMST WHERE YYEF_WWMS_KEYID=(SELECT WWMS_KEYID FROM BDM_TL_WHYWHYMST WHERE WWMS_REFDOCNO=(SELECT ABNM_KEYID FROM ABN_TL_ABNORMALITY WHERE ABNM_KEYID='"+abnKeyid+"'))");
		sqls.add(" DELETE FROM BDM_TL_YYEFFECTIVEDTL WHERE YYED_YYEF_KEYID=(SELECT YYEF_KEYID FROM BDM_TL_YYEFFECTIVEMST WHERE YYEF_WWMS_KEYID=(SELECT WWMS_KEYID FROM BDM_TL_WHYWHYMST WHERE WWMS_REFDOCNO=(SELECT ABNM_KEYID FROM ABN_TL_ABNORMALITY WHERE ABNM_KEYID='"+abnKeyid+"')))");
		sqls.add(" DELETE FROM BDM_TL_WHYWHYMST WHERE WWMS_REFDOCNO=(SELECT ABNM_KEYID FROM ABN_TL_ABNORMALITY WHERE ABNM_KEYID='"+abnKeyid+"')");
		sqls.add(" DELETE FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_REFDOCNO=(SELECT ABNM_KEYID FROM ABN_TL_ABNORMALITY WHERE ABNM_KEYID='"+abnKeyid+"')");
		sqls.add(" DELETE from ABN_TL_DTL WHERE ABND_ABNORMALITYID=(SELECT ABNM_KEYID FROM ABN_TL_ABNORMALITY WHERE ABNM_KEYID='"+abnKeyid+"')" );
		sqls.add(" DELETE FROM ABN_TL_ABNORMALITY WHERE ABNM_KEYID='"+abnKeyid+"'");
		//CommonMessage.debugMsg(sqls.toString());
		dbActionTemplate.executeStatements(sqls);
}

@Override
public List<String[]> getUSerList(CommonFilter commonFilter) throws Exception {
	String EmpKey=commonFilter.getKey();
	//CommonMessage.debugMsg("EmpKey"+EmpKey);
	StringBuffer sql = new StringBuffer();
	 if(UIUtils.isValidKeyId(EmpKey)){
	sql.append(" SELECT "); 
	sql.append("EMPM_KEYID AS \"EMPM_KEYID\",EMPM_CODE AS \"EMPM_CODE\",EMPM_NAME AS \"EMPM_NAME\",EMPM_EMPLOYEETYPE AS \"EMPM_EMPLOYEETYPE\",LOCN_NAME,USRM_REMARKS,'' ");  
	sql.append(" FROM ");
	sql.append(" GEN_TL_EMPLOYEEMST,GEN_TL_LOCATIONMST,ADM_TL_USERMST"); 
	sql.append(" WHERE "); 
	sql.append(" 1=1   and  EMPM_LOCATION = LOCN_KEYID and EMPM_ACTIVE='Y' AND EMPM_KEYID=USRM_CCNO");
	if(UIUtils.isValidKeyId(EmpKey)){
		   sql.append(" AND EMPM_KEYID ='"+EmpKey+"' ");
		  }
    sql.append(" ORDER BY ");
    sql.append(" EMPM_NAME ASC ");
    //CommonMessage.debugMsg("SQL is::::"+sql);
	 }
	 else{
		 sql.append(" SELECT "); 
			sql.append("EMPM_KEYID AS \"EMPM_KEYID\",EMPM_CODE AS \"EMPM_CODE\",EMPM_NAME AS \"EMPM_NAME\",EMPM_EMPLOYEETYPE AS \"EMPM_EMPLOYEETYPE\",LOCN_NAME,USRM_REMARKS,''");  
			sql.append(" FROM ");
			sql.append(" GEN_TL_EMPLOYEEMST,GEN_TL_LOCATIONMST,ADM_TL_USERMST"); 
			sql.append(" WHERE "); 
			sql.append(" 1=1   and  EMPM_LOCATION = LOCN_KEYID and EMPM_ACTIVE='Y'  AND EMPM_KEYID=USRM_CCNO");
		    sql.append(" ORDER BY ");
		    sql.append(" EMPM_NAME ASC ");
		   // CommonMessage.debugMsg("SQL is::::"+sql);
	 }
	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
	return gridData;
}

@Override
public void AupUSerInActive(String empIds) throws Exception {
	ArrayList<String> sqls=new ArrayList<String>();
    StringBuffer sql =new StringBuffer();
    String DateTime=CommonFunctions.dateTimeNow();
    String Date=CommonFunctions.getDate();
    String ConDate=Date.substring(1,11);
    //CommonMessage.debugMsg("CurrentDate"+Date+"ConDate"+ConDate);
    //CommonMessage.debugMsg("DateTime"+DateTime);
    //CommonMessage.debugMsg("ValidTillDate:::"+ValidTillDate);
    
	
        		String Active="N";
    			sql.append("UPDATE adm_tl_userMST SET usrm_isACTIVE = '" + Active + "'  WHERE usrm_ccno='"+empIds+"'");
    			//CommonMessage.debugMsg("sql :" +sql);
    			sqls.add(sql.toString());
    			//getUserInactiveValidDateUpdate(empIds);
      
		
		
	
	dbActionTemplate.executeStatements(sqls);		
}


@Override
public List<ComboBox> getKaizenNoCombo(ComboFilter comboFilter) throws Exception {
	StringBuilder sql = new StringBuilder();
	sql.append(" select * from ( ");
	sql.append(" SELECT DISTINCT KZNM_KEYID id, KZNM_KEYID text ");
	sql.append(" FROM KZN_TL_MST ");
	sql.append(" WHERE 1 = 1 ");

	String filterText = null;
	if (comboFilter.getName() != null && comboFilter.getName().trim().length() > 0) {
		filterText = comboFilter.getName().trim();
	} else if (comboFilter.getId() != null && comboFilter.getId().trim().length() > 0) {
		filterText = comboFilter.getId().trim();
	} else if (comboFilter.getCode() != null && comboFilter.getCode().trim().length() > 0) {
		filterText = comboFilter.getCode().trim();
	}

	if (filterText != null) {
		sql.append(" AND UPPER(KZNM_KEYID) LIKE UPPER('%" + filterText + "%') ");
	}

	sql.append(" order by text ) ");

	CommonMessage.debugMsg("exectueSql    ::  " + sql.toString());

	List<String[]> rows = dbActionTemplate.getDataList(sql.toString());
	List<ComboBox> comboList = new ArrayList<ComboBox>();
	if (rows != null) {
		for (String[] row : rows) {
			ComboBox cb = new ComboBox();
			cb.setId(row[0]);
			cb.setText(row[1]);
			comboList.add(cb);
		}
	}
	return comboList;
}


@Override
public String[] getKaizenBenefitTypeAndFlid(String kznKeyid) throws Exception {
	String sql = "SELECT KZNM_BENEFITTYPE, KZNM_FLID FROM KZN_TL_MST WHERE KZNM_KEYID = '" + kznKeyid + "' ";
	
	CommonMessage.debugMsg("sql in getKaizenBenefitTypeAndFlid :: " + sql);
	
	List<String[]> rows = dbActionTemplate.getDataList(sql);
	if (rows != null && rows.size() > 0) {
		return rows.get(0);
	}
	return new String[]{"", ""};
}

@Override
public List<String[]> getKaizenApprovalDeleteData(GridParams gridParams, CommonFilter commonFilter) throws Exception {
	String sql = getKaizenApprovalDeleteGrid(commonFilter);
	String outerSql="select * from("+sql.toString()+")where 1=1 ";	
	if(gridParams.getGridFilters()!=null)
		outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
	String count="select count(*) from("+outerSql+")";
	String rowCount=dbActionTemplate.getSingleValue(count);
	long counts=Long.parseLong(rowCount);
	gridParams.setTotalRecordCnt(counts);
	List<String> params= new ArrayList<String>();
	params.add(gridParams.getFromRow());
	params.add(gridParams.getToRow());
	String finalSql =
		    "SELECT * FROM (" +
		        "SELECT ROW_NUMBER() OVER () AS slno, a.* FROM (" + outerSql + ") a" +
		    ") t WHERE slno >= CAST(? AS BIGINT) AND slno <= CAST(? AS BIGINT)";

	List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
	return result;
}

public String getKaizenApprovalDeleteGrid(CommonFilter commonFilter){
	StringBuffer sql =new StringBuffer();
	String fromDate=commonFilter.getFromDate();
	String toDate=commonFilter.getToDate();
	String DMT=commonFilter.getFlid();
	String JH=commonFilter.getAwise();
	String KznKeyid=commonFilter.getKey();

	if(UIUtils.isValidKeyId(KznKeyid)){
	    sql.append("SELECT KZNM_KEYID AS KaizenApprovalKeyid, KZNM_KEYID AS KznKeyid, TO_CHAR(KZNM_DATE,'DD-Mon-YYYY') AS KznmDate, DMT AS txtDMT, JH AS txtJH, ");
	    sql.append("KZNM_THEME AS KznmTheme, KZNM_STATUS AS KznmStatus, '' AS SUBMIT ");
	    sql.append("FROM KZN_TL_MST ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON KZNM_FLID=FLID ");
	    sql.append("WHERE KZNM_KEYID='" + KznKeyid + "' ");
	    sql.append(" ORDER BY KZNM_KEYID DESC ");
	}
	else if(UIUtils.isValidKeyId(fromDate) && UIUtils.isValidKeyId(toDate)){
	    sql.append("SELECT KZNM_KEYID AS KaizenApprovalKeyid, KZNM_KEYID AS KznKeyid, TO_CHAR(KZNM_DATE,'DD-Mon-YYYY') AS KznmDate, DMT AS txtDMT, JH AS txtJH, ");
	    sql.append("KZNM_THEME AS KznmTheme, KZNM_STATUS AS KznmStatus, '' AS SUBMIT ");
	    sql.append("FROM KZN_TL_MST ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON KZNM_FLID=FLID ");
	    sql.append("WHERE KZNM_DATE BETWEEN '" + fromDate + "' AND '" + toDate + "' ");
	    
	    if(UIUtils.isValidKeyId(DMT) && JH.length()==0){
	        sql.append("AND FLID='" + DMT + "' ");
	    }
	    else if(UIUtils.isValidKeyId(JH)){
	        sql.append("AND FLID='" + JH + "' ");
	    }
	    
	    sql.append(" ORDER BY KZNM_KEYID DESC ");
	}
	else{
	    sql.append("SELECT KZNM_KEYID AS KaizenApprovalKeyid, KZNM_KEYID AS KznKeyid, TO_CHAR(KZNM_DATE,'DD-Mon-YYYY') AS KznmDate, DMT AS txtDMT, JH AS txtJH, ");
	    sql.append("KZNM_THEME AS KznmTheme, KZNM_STATUS AS KznmStatus, '' AS SUBMIT ");
	    sql.append("FROM KZN_TL_MST ");
	    sql.append("LEFT JOIN gen_mv_flidhierarchy ON KZNM_FLID=FLID ");
	    sql.append("WHERE 1=1 ");
	    sql.append(" ORDER BY KZNM_KEYID DESC ");
	}

	CommonMessage.debugMsg("sql in KaizenApproval Delete Data"+sql.toString());
	return sql.toString();
}

@Override
public String getEmpRoleIds(String empId) throws Exception {
	String sql = " SELECT STRING_AGG(ROLE_KEYID, ',' ORDER BY ROLE_KEYID) FROM gen_tl_fnlnroleteam ";
	sql = sql + " INNER JOIN ADM_TL_ROLEMST ON ROLE_KEYID = FRT_ROLE_KEYID WHERE frt_empm_keyid = '"+empId+"' ";
	String empRoleids = dbActionTemplate.getSingleValue(sql);
	return empRoleids;
}






@Override
public List<String[]> getWorkFlowTransData(
		GenTlWorkflowInfo genTlWorkflowInfo,String transCode,String flId,String roleId) throws Exception {
	
	CommonMessage.debugMsg("transCode::"+transCode);
	CommonMessage.debugMsg("genTlWorkflowInfo.getWrinRefId()::"+genTlWorkflowInfo.getWrinRefId());
	CommonMessage.debugMsg("genTlWorkflowInfo.getWrinRefType()::"+genTlWorkflowInfo.getWrinRefType());
	CommonMessage.debugMsg("flId::"+flId);
	CommonMessage.debugMsg("roleId::"+roleId);
	CommonMessage.debugMsg("genTlWorkflowInfo.getWrinEmployeeId()::"+genTlWorkflowInfo.getWrinEmployeeId());
	
	List<String> paramValues = new ArrayList<String>();
	String condParms = "";
	
	condParms="FLID="+flId+";REFID="+genTlWorkflowInfo.getWrinRefId()+";REFTYPE="+genTlWorkflowInfo.getWrinRefType()+";EMPLOYEEID="+genTlWorkflowInfo.getWrinEmployeeId()+";TRANSCODE="+transCode+";ENABLE="+genTlWorkflowInfo.getEnable()+";ROLEID="+roleId+";";
	String commonParams = ""; // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	paramValues.add(condParms);
	paramValues.add(commonParams);
	return fnCallApi.callFunction("Gen_TL_WORKFLOWAPPROVAL_SB", paramValues, 1, false);
}


@Override
public String getWorkflowRefRoleId(String empId,String flId,String transCode) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append(" SELECT FRT_ROLE_KEYID ");
    sql.append(" FROM GEN_TL_FNLNROLETEAM ");
    sql.append(" WHERE FRT_EMPM_KEYID = '");
    sql.append(empId.replace("'", "''"));
    sql.append("' ");

    sql.append(" AND FRT_FLID = '");
    sql.append(flId.replace("'", "''"));
    sql.append("' ");

    sql.append(" AND FRT_ROLE_KEYID IN ( ");
    sql.append("     SELECT WRKD_ROLE_KEYID ");
    sql.append("     FROM GEN_TL_WORKFLOWDTL ");
    sql.append("     INNER JOIN GEN_TL_WORKFLOW_MENU_LINK ");
    sql.append("       ON WRML_WRKM_KEYID = WRKD_WRKM_KEYID ");
    sql.append("     WHERE TRIM(WRML_TRANS_CODE) = '");
    sql.append(transCode.replace("'", "''"));
    sql.append("' ");
    sql.append(" ) ");

    sql.append(" ORDER BY FRT_ROLE_KEYID ");
    sql.append(" LIMIT 1 ");

    CommonMessage.debugMsg("Workflow ref role SQL: " + sql);

    return dbActionTemplate.getSingleValue(sql.toString());
}


@Override
public void revokeKaizenApprovalWorkflow(String refId, List<String> wrinKeyIds) throws Exception {

    if (refId == null || refId.trim().isEmpty()) {
        throw new Exception("Kaizen reference ID is empty");
    }
    if (wrinKeyIds == null || wrinKeyIds.isEmpty()) {
        throw new Exception("No workflow rows selected");
    }

    String safeRefId = refId.trim().replace("'", "''");
    List<String> sqlList = new ArrayList<String>();

    // Hierarchy order - lower number = earlier approval stage.
    // NOTE: verify these role IDs against ADM_TL_ROLEMST - only
    // AROL0006 (JH LEADER), AROL0003 (DMT LEADER) and AROL0002 (PBU HEAD)
    // are confirmed by your logs; double-check SBU/UNIT/FINANCE ids.
    Map<String, Integer> roleLevel = new HashMap<String, Integer>();
    roleLevel.put("AROL0006", 1); // JH LEADER
    roleLevel.put("AROL0003", 2); // DMT LEADER
    roleLevel.put("AROL0002", 3); // PBU HEAD
    roleLevel.put("AROL0001", 4); // SBU HEAD
    roleLevel.put("AROL0103", 5); // UNIT HEAD
    roleLevel.put("AROL0061", 6); // FINANCE

    String earliestRoleId = null;
    String earliestRoleName = "";
    int earliestLevel = Integer.MAX_VALUE;

    for (String wrinKeyId : wrinKeyIds) {

        if (wrinKeyId == null || wrinKeyId.trim().isEmpty()) {
            continue;
        }

        String safeWrinKeyId = wrinKeyId.trim().replace("'", "''");

        String roleSql =
            "SELECT WI.WRIN_ROLE_ID, RM.ROLE_NAME " +
            "FROM GEN_TL_WORKFLOW_INFO WI " +
            "INNER JOIN ADM_TL_ROLEMST RM " +
            "ON RM.ROLE_KEYID = WI.WRIN_ROLE_ID " +
            "WHERE WI.WRIN_KEYID = '" + safeWrinKeyId + "' " +
            "AND WI.WRIN_REF_ID = '" + safeRefId + "' " +
            "AND WI.WRIN_REF_TYPE = 'KZNBTS'";

        List<String[]> roleData = dbActionTemplate.getDataList(roleSql);

        if (roleData == null || roleData.isEmpty()) {
            throw new Exception("Workflow role not found for key: " + safeWrinKeyId);
        }

        String roleId = roleData.get(0)[0] == null ? "" : roleData.get(0)[0].trim();
        String roleName = roleData.get(0)[1] == null ? "" : roleData.get(0)[1].trim();

        CommonMessage.debugMsg("Revoked role ID: " + roleId + ", role name: " + roleName);

        // Keep only the EARLIEST (lowest-level) role among everything revoked
        Integer level = roleLevel.get(roleId);
        if (level != null && level < earliestLevel) {
            earliestLevel = level;
            earliestRoleId = roleId;
            earliestRoleName = roleName;
        }

        String deleteSql =
            "DELETE FROM GEN_TL_WORKFLOW_INFO " +
            "WHERE WRIN_KEYID = '" + safeWrinKeyId + "' " +
            "AND WRIN_REF_ID = '" + safeRefId + "' " +
            "AND WRIN_REF_TYPE = 'KZNBTS'";

        sqlList.add(deleteSql);
    }

    if (sqlList.isEmpty()) {
        throw new Exception("No valid workflow rows selected");
    }
    if (earliestRoleId == null) {
        throw new Exception("Unable to determine revoked role level");
    }

    boolean isJhLeader = "AROL0006".equals(earliestRoleId);
    String status = isJhLeader ? "-" : "A";
    String approvalLevel = isJhLeader ? "JH LEADER" : earliestRoleName.replace("'", "''");

    String updateKaizenSql =
        "UPDATE KZN_TL_MST " +
        "SET KZNM_STATUS='" + status + "', " +
        "KZNM_APROV_LEVEL='" + approvalLevel + "' " +
        "WHERE KZNM_KEYID='" + safeRefId + "'";

    sqlList.add(updateKaizenSql);

    CommonMessage.debugMsg("Workflow revoke SQL list: " + sqlList);

    dbActionTemplate.executeStatements(sqlList);
}

	
}



