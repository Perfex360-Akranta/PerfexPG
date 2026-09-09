package com.akranta.tpm.dao.impl;




import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlNearmissreportmstnewDao;
import com.akranta.tpm.dao.sql.FilterCondNewSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlNearmissreportmstnewSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlNearmissreportmstnew;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;

/* dao implementation */
public class GenTlNearmissreportmstnewDaoImpl implements GenTlNearmissreportmstnewDao {


	private DBActionTemplate dbActionTemplate; 

	
	public GenTlNearmissreportmstnewDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlNearmissreportmstnew create(GenTlNearmissreportmstnew genTlNearmissreportmstnew) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		//CommonMessage.debugMsg("inside the create123");
		GenTlNearmissreportmstnewSql genTlNearmissreportmstnewSql = new GenTlNearmissreportmstnewSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		   
			String elementId = genTlNearmissreportmstnew.getElementid();
			CommonMessage.debugMsg("elementId"+elementId);
		 	String location = null;
		 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlNearmissreportmstnewSql.TBL_GEN_TL_NEARMISSREPORTMSTNEW);
		 	CommonMessage.debugMsg("seqIdentfi"+seqIdentfi);
		 	//genTlNearmissreportmstnew.setNmrnKeyid(dbActionTemplate.getSequenceNumber(GenTlNearmissreportmstnewSql.TBL_GEN_TL_NEARMISSREPORTMSTNEW)); // set the sequnce number 
			
		 	genTlNearmissreportmstnew.setNmrnKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,12, "NNM" ,"YY" ,null)); // set the sequnce number
			//CommonMessage.debugMsg(genTlNearmissreportmstnew.getNmrnKeyid());
		 	sqls.add(GenTlNearmissreportmstnewSql.getInsertSql(genTlNearmissreportmstnewSql.getNmrnDbFields(), genTlNearmissreportmstnew.getSaveArray())); // add insert sql for master table

			//CommonMessage.debugMsg("before execute the block of sqls");
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			//CommonMessage.debugMsg("after execute the block of sqls");
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlNearmissreportmstnew;
	}
	
	public GenTlNearmissreportmstnew update(GenTlNearmissreportmstnew genTlNearmissreportmstnew)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlNearmissreportmstnewSql genTlNearmissreportmstnewSql = new GenTlNearmissreportmstnewSql();
		try {

			sqls.add(GenTlNearmissreportmstnewSql.getUpdateSql(genTlNearmissreportmstnewSql.getNmrnDbFields(), genTlNearmissreportmstnew.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlNearmissreportmstnew;
	}
	
	public GenTlNearmissreportmstnew delete(GenTlNearmissreportmstnew genTlNearmissreportmstnew)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlNearmissreportmstnewSql genTlNearmissreportmstnewSql = new GenTlNearmissreportmstnewSql();
		try {
			
			sqls.add(genTlNearmissreportmstnewSql.getDeleteSql(genTlNearmissreportmstnewSql.getNmrnDbFields(), genTlNearmissreportmstnew.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlNearmissreportmstnew;
	}

	@Override
	public List<String[]> getNearDetailsList(CommonFilter commonFilter,String formType) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondNewSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondNewSql.getGridCommonParams(commonFilter); 
		String userno=commonFilter.getActionKeyId();
		String chkType=commonFilter.getChkActType();
		
		
		condParms+="REPORTTYPE="+"create"+";";
		condParms+="FORMMODE="+formType+";";
		condParms+="EMPID="+userno+";";
		condParms+="CHKTYPE="+chkType+";";
		condParms+="ROLE="+commonFilter.getTaskid()+";";
		String mode =commonFilter.getAbnAllch();
		
		//CommonMessage.debugMsg(" DaoImpl :: mode :: "+mode+" Checking for type :: "+"create");
		
		
			
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg("TEST_PC_TEST2. paramvalues::: in side daoimpl "+paramValues);
		List<String[]> dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST2.EHS_FN_NEARMISSMAINGRIDNEW", paramValues);
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;
		
	}

	@Override
	public List<String[]> getNearDetailsListView(CommonFilter commonFilter,String formType) throws Exception {
		try {
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("JHN_PC_AUDIT.GEN_FN_NEARMISSNEWGRID",paramValues);
			//if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) { 
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				//}
			}
			return dataList;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	@Override
	public String UpdateCloseDocument(String detailkeyid, String responsevalue,
			String targetdate, String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		StringBuffer sql2 = new StringBuffer();
		 if(remarks==null)
		    {
		    	remarks="-";
		    }
		if(verifystatus.equals("Y"))
		{
			sql2.append("UPDATE GEN_TL_NEARMISSREPORTMSTNEW SET NMRN_CLOSED='"+verifystatus+"'");
			sql2.append(",NMRN_CLOSEDDATE='"+targetdate+"'"+",NMRN_CLOSEDBY='"+responsevalue+"'"+",NMRN_CLOSEDBYREMARKS='"+remarks+"'"+"WHERE NMRN_KEYID='"+detailkeyid+"'"); 
			sqls.add(sql2.toString());
			 //CommonMessage.debugMsg("SQLS::"+sqls);
			dbActionTemplate.executeStatements(sqls);
		}
		return "SUCCESS";
	}

	@Override
	public String UpdateVerify(String detailkeyid, String responsevalue,
			String targetdate, String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		StringBuffer sql2 = new StringBuffer();
		 if(remarks==null||remarks.length()==0)
		    {
		    	remarks="-";
		    }
		 
		if(verifystatus.equals("Y"))
		{
			sql2.append("UPDATE GEN_TL_NEARMISSREPORTMSTNEW SET NMRN_VERIFIEDSTATUS='"+verifystatus+"'");
			sql2.append(",NMRN_DATEVERIFIED='"+targetdate+"'"+",NMRN_VERIFIEDBY='"+responsevalue+"'"+",NMRN_VERIFYREMARKS='"+remarks+"'"+"WHERE NMRN_KEYID='"+detailkeyid+"'"); 
			sqls.add(sql2.toString());
			 //CommonMessage.debugMsg("SQLS::"+sqls);
			dbActionTemplate.executeStatements(sqls);
		}
		return "SUCCESS";
	}

	@Override
	public String UpdateResponse(String detailkeyid, String responsevalue,
			String targetdate, String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		StringBuffer sql2 = new StringBuffer();
    if(remarks==null||remarks.length()==0)
    {
    	remarks="-";
    }

			sql2.append(" UPDATE GEN_TL_NEARMISSREPORTMSTNEW SET NMRN_RESPONSIBILITY='"+responsevalue+"'");
			sql2.append(",NMRN_TARGETDATE='"+targetdate+"'"+",NMRN_RESPONREMARKS='"+remarks+"'"+"WHERE NMRN_KEYID='"+detailkeyid+"'"); 
			sqls.add(sql2.toString());
			 //CommonMessage.debugMsg("SQLS::"+sqls);
			dbActionTemplate.executeStatements(sqls);
		
		return "SUCCESS";
	}

	@Override
	public String updateActionCLosure(String detailkeyid, String responsevalue,
			String targetdate, String reviseddate, String status,
			String correctiveaction,String remarks) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		StringBuffer sql1 = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		 if(remarks==null||remarks.length()==0)
		    {
		    	remarks="-";
		    }
		if(status.equals("C"))
		{
		
			sql1.append(" UPDATE GEN_TL_NEARMISSREPORTMSTNEW SET NMRN_COMPLETEDBY='"+responsevalue+"'");
			sql1.append(",NMRN_DATECOMPLETED='"+targetdate+"'"+",NMRN_RESPONREMARKS='"+remarks+"'"+",NMRN_STATUS='"+status+"'"+",NMRN_ACTIONTAKEN='"+correctiveaction+"'"+",NMRN_ACTNCLSREMARKS='"+remarks+"'"+"WHERE NMRN_KEYID='"+detailkeyid+"'"); 
			sqls.add(sql1.toString());
			 //CommonMessage.debugMsg("SQLS::"+sqls);
			 
			/* sql1.append(" UPDATE GEN_TL_ACTIONPLANDTL SET SDAD_COMPLETEDBY='"+responsevalue+"'");
				sql1.append(",SDAD_COMPLETEDDATE='"+targetdate+"'"+",SDAD_STATUS='"+status+"'"+",SDAD_CORRECACTION='"+correctiveaction+"'"+"WHERE SDAD_KEYID='"+detailkeyid+"'"); 
				sqls.add(sql1.toString());
				 //CommonMessage.debugMsg("SQLS::"+sqls);
				dbActionTemplate.executeStatements(sqls);*/
			 
			dbActionTemplate.executeStatements(sqls);
			updateActionPlan(detailkeyid,targetdate,status,responsevalue);
			
			
		
		}
		else
		{  
			if(reviseddate!=null&&reviseddate.length()!=0)
			{
			sql1.append(" UPDATE GEN_TL_NEARMISSREPORTMSTNEW SET NMRN_STATUS='"+status+"'");
			sql1.append(",NMRN_REVISEDTARGET='"+reviseddate+"'"+",NMRN_RESPONREMARKS='"+remarks+ "WHERE NMRN_KEYID='"+detailkeyid+"'"); 
			sqls.add(sql1.toString());
			 //CommonMessage.debugMsg("SQLS::"+sqls);
			dbActionTemplate.executeStatements(sqls);
			}
		}
		return "SUCCESS";
	}

	private String updateActionPlan(String detailkeyid, String targetdate, String status, String responsevalue) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		//String aplmkeyid=dbActionTemplate.getSingleValue("", returnField, checkField, checkValue)
		String aplmkeyid=dbActionTemplate.getSingleValue("GEN_TL_ACTIONPLANMST", "APLM_KEYID", "APLM_MASTERREFID", detailkeyid);
		String aplmdkeyid=dbActionTemplate.getSingleValue("GEN_TL_ACTIONPLANDTL", "APLD_KEYID", "APLD_APLM_KEYID", aplmkeyid);
		
		List<String> sqls = new ArrayList<String>();
		String detsql="UPDATE GEN_TL_ACTIONPLANDTL SET APLD_STATUS='"+"C"+"'"+","+"APLD_COMPLETEDBY='"+responsevalue+"'"+","+
		"APLD_COMPLEATEDON='"+targetdate+"' " +"WHERE APLD_KEYID='"+aplmdkeyid+"'";
		String mstsql="UPDATE  GEN_TL_ACTIONPLANMST SET APLM_STATUS='"+"C"+"'"+ "WHERE APLM_KEYID='"+aplmkeyid+"'";
		sqls.add(detsql);
		sqls.add(mstsql);
		dbActionTemplate.executeStatements(sqls);
		return mstsql;
	}

	@Override
	public List<String[]> getElementId(String loginflid, String loginlevel,String loginElementid, String empId) throws NoDataFoundException, Exception {
		// TODO Auto-generated method stub
    StringBuffer sql =new StringBuffer();
		
		
		sql.append("SELECT FNLN_ELEMENTID,FNLN_KEYID,ROLE_LEVEL,ROLE_NAME,ROLE_KEYID FROM  GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST  ");
		sql.append(" WHERE FNLN_KEYID = FRT_FNLN_KEYID  and FRT_ROLE_KEYID = ROLE_KEYID  ");
		
		if(UIUtils.isValidKeyId(loginflid))
			sql.append(" AND FRT_FNLN_KEYID  = '"+ loginflid +"' ");
		
		sql.append(" AND FRT_EMPM_KEYID = '"+ empId +"'  AND ROLE_LEVEL= '"+ loginlevel +"'");
		
		Object [] args = {} ;
		
		//CommonMessage.debugMsg("sql:::"+sql.toString());
		
		List<String []> userDatas = dbActionTemplate.getDataList(sql.toString(),args);
		
		//CommonMessage.debugMsg("userDatas:::"+userDatas);
		
		return userDatas;
	}

	@Override
	public List<String[]> getSwitchUserDetail(String empId) throws NoDataFoundException, Exception {
		// TODO Auto-generated method stub
		 StringBuffer sql =new StringBuffer();
			
			
			sql.append("SELECT ROLE_KEYID,ROLE_NAME FROM  GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST  ");
			sql.append(" WHERE   FRT_ROLE_KEYID = ROLE_KEYID  ");
			sql.append(" AND FRT_EMPM_KEYID = '"+ empId +"'");
			
			Object [] args = {} ;
			
			//CommonMessage.debugMsg("sql:::"+sql.toString());
			
			List<String []> userDatas = dbActionTemplate.getDataList(sql.toString(),args);
			
			//CommonMessage.debugMsg("userDatas:::"+userDatas);
			
			return userDatas;
	}

	@Override
	/*public List<String[]> getNearMissEmpMailIds(String rolekeyid, String flid) throws NoDataFoundException, Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" select EMPM_NAME, EMPM_EMAIL FROM gen_tl_employeemst ,GEN_TL_FNLNROLETEAM ");
		sql.append(" WHERE FRT_FNLN_KEYID= '"+ flid +"'");
		sql.append("  AND FRT_ROLE_KEYID = '"+ rolekeyid +"'");
		sql.append(" AND FRT_EMPM_KEYID=EMPM_KEYID ");
		// Modified on 10-Feb-15 for mail to be send based on configuration
		sql.append("  and empm_keyid in ( select empm_keyid from gen_tl_employeemst where empm_enableemail='Y') ");
		
		CommonMessage.debugMsg("getMomAttendanceEmpMailIdSql=="+sql.toString());
		
	
		
		//Object [] args = {flid,momKeyId};
		Object [] args = {};
		List<String []> data= dbActionTemplate.getDataList(sql.toString(),args);
		return data;
	}*/
	

	public List<String[]> getNearMissEmpMailIds(String type, String flid,String Location) throws NoDataFoundException, Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" select EMPM_NAME, EMPM_EMAIL FROM gen_tl_employeemst ,GEN_TL_FNLNROLETEAM,EHS_TL_MAILCONFIGURATION ");
		sql.append(" WHERE  FRT_ROLE_KEYID=EHSM_ROLEID ");
		sql.append("  AND EHSM_CATEGORY = '"+ type +"'");
		if(type.equals("MINOR")||type.equals("MAJOR"))
		{
		  sql.append("  AND FRT_FNLN_KEYID(+) = '"+ flid +"'");
		}
		//.append(" AND EMPM_LOCATION='"+Location+"'" );
		sql.append(" AND FRT_EMPM_KEYID=EMPM_KEYID ");
		// Modified on 10-Feb-15 for mail to be send based on configuration
		sql.append("  and empm_keyid in ( select empm_keyid from gen_tl_employeemst where empm_enableemail='Y') ");
		//CommonMessage.debugMsg("MAIL QUERY"+sql.toString());
		CommonMessage.debugMsg("get Nearmissmail id=="+sql.toString());
		
	
		
		//Object [] args = {flid,momKeyId};
		Object [] args = {};
		List<String []> data= dbActionTemplate.getDataList(sql.toString(),args);
		return data;
	}



	@Override
	public List<String[]> getNearMiss(String masterKeyid) throws Exception{
		//
StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT "); 
		sql.append(" NMUA_KEYID,NEAR_KEYID AS \"keyid\",NEAR_NAME AS \"Unsafe Act\",'' ");  
		sql.append(" FROM ");
		sql.append(" SHE_TL_NEARMISS , GEN_TL_NEARMISSREPORTDTLNEW,GEN_TL_NEARMISSREPORTMSTNEW "); 
		sql.append(" WHERE ");
		sql.append(" NEAR_KEYID=NMUA_NEARKEYID(+) ");
	    sql.append(" AND NMRN_KEYID(+) =  NMUA_NEARKEYID ");
	    sql.append(" AND NMUA_NMRTKEYID(+) ='"+masterKeyid+"' ");
	    sql.append(" AND NEAR_ACTIVE='Y' ");
	    sql.append(" AND NEAR_CODE = 'UC' ");
	    sql.append(" ORDER BY ");
	    sql.append(" NEAR_KEYID ");
	    
	
				
		CommonMessage.debugMsg("sql..." + sql.toString());
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;

		
	}

	@Override
	public List<String[]> getNearAct(String masterKeyid) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT "); 
		sql.append(" NMUA_KEYID,NEAR_KEYID AS \"keyid\",NEAR_NAME AS \"Unsafe Act\",'' ");  
		sql.append(" FROM ");
		sql.append(" SHE_TL_NEARMISS , GEN_TL_NEARMISSREPORTDTLNEW,GEN_TL_NEARMISSREPORTMSTNEW "); 
		sql.append(" WHERE ");
		sql.append(" NEAR_KEYID=NMUA_NEARKEYID(+) ");
	    sql.append(" AND NMRN_KEYID(+) =  NMUA_NEARKEYID ");
	    //if(UIUtils.isValidKeyId(masterKeyid)){
	    	sql.append(" AND NMUA_NMRTKEYID(+) ='"+masterKeyid+"' ");
	    //}
	    sql.append(" AND NEAR_ACTIVE='Y'");
	    sql.append(" AND NEAR_CODE = 'UA' ");
	    sql.append(" ORDER BY ");
	    sql.append(" NEAR_KEYID ");
	    
	    
		/*sql.append("select  NMUA_KEYID,near_keyid as \"keyid\",near_name as \"Unsafe Act\",''  from she_tl_nearmiss , Gen_Tl_Nearmissreportdtl");
		sql.append(" where ");
		sql.append("NEAR_KEYID=NMUA_NEARKEYID(+) ");
		if(UIUtils.isValidKeyId(masterKeyid)){
			sql.append("and NMUA_NMRTKEYID(+) ='");//NMUA_KEYID
			sql.append(masterKeyid);
			sql.append("'");
		}
		sql.append("and near_code = 'UA' ORDER BY near_keyid");*/
		//CommonMessage.debugMsg("sql..." + sql.toString());
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
	}
	
	@Override
	public Workbook getNearMissExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,
			String formtype) throws Exception {
		// TODO Auto-generated method stub
		/* ResultSet rs = null;
		   try
		   {
			   	rs =   getNearMissReportResultSet(commonFilter,formtype);
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();	
				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(254,0,0)); //red font
				condFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
				condFormat.setFontHeightPoint((short)8);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setDbChkColIndx(3);
				condFormat.setFromCol(2);
				condFormat.setToCol(2);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue("RED"); //Tick
				condFormat.setIdentfier("RED");
				//condFormat.setBgColor(new RGB(213,255,195));
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
				return excelUtils.writeToExcel(rs,format,2, 0,1);
	}
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());	   
		   }*/
		    List<String> paramValues = new ArrayList<String>();	
			String userno=commonFilter.getActionKeyId();
			String chkType=commonFilter.getChkActType();
			CommonMessage.debugMsg("The userno"+userno);
		    String condParms = FilterCondNewSql.getEmployeeRoleRelatedCondStr(commonFilter);
		    String commonParams = FilterCondNewSql.getGridCommonParams(commonFilter);
		    condParms+="FORMMODE="+formtype+";";
			condParms+="EMPID="+userno+";";  
			condParms+="CHKTYPE="+chkType+";";
			condParms+="ROLE="+commonFilter.getTaskid()+";";
		    paramValues.add(condParms);	 
		    paramValues.add(commonParams);
		 	ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.dbFunctionCall("TEST_PC_TEST2.EHS_FN_NEARMISSMAINGRIDNEW", paramValues);
		 	ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format,2,0,0);	  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());    
		 }
	}

	@Override
	public Workbook getNearMissViewExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,
			String formtype) throws Exception {
		// TODO Auto-generated method stub
		/* ResultSet rs = null;
		   try
		   {
			   	rs =   getNearMissReportResultSet(commonFilter,formtype);
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();	
				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(254,0,0)); //red font
				condFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
				condFormat.setFontHeightPoint((short)8);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setDbChkColIndx(3);
				condFormat.setFromCol(2);
				condFormat.setToCol(2);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue("RED"); //Tick
				condFormat.setIdentfier("RED");
				//condFormat.setBgColor(new RGB(213,255,195));
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
				return excelUtils.writeToExcel(rs,format,2, 0,1);
	}
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());	   
		   }*/
		    List<String> paramValues = new ArrayList<String>();	
			String userno=commonFilter.getActionKeyId();
			String chkType=commonFilter.getChkActType();
			CommonMessage.debugMsg("The userno"+userno);
		    String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		 /*   condParms+="FORMMODE="+formtype+";";
			condParms+="EMPID="+userno+";";
			condParms+="CHKTYPE="+chkType+";";
			condParms+="ROLE="+commonFilter.getTaskid()+";";*/
		    paramValues.add(condParms);	 
		    paramValues.add(commonParams);
		 	ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.dbFunctionCall("JHN_PC_AUDIT.GEN_FN_NEARMISSNEWGRID", paramValues);
		 	ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format,2,0,0);	  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());    
		 }
	}
	/*private ResultSet getNearMissReportResultSet(CommonFilter commonFilter, String formtype) throws Exception {
		// TODO Auto-generated method stub
		/*List<String> paramValues = new ArrayList<String>();
		List<String[]> relatedMst = null;
		
		//String userno=commonFilter.getEmpch();
		String userno=commonFilter.getActionKeyId();
		String chkType=commonFilter.getChkActType();
		CommonMessage.debugMsg("The userno"+userno);
		String  condParms= FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		condParms+="FORMMODE="+formtype+";";
		condParms+="EMPID="+userno+";";
		condParms+="CHKTYPE"+chkType+";";
		condParms+="ROLE="+commonFilter.getTaskid()+";";
	
		String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
		CommonMessage.debugMsg("The userno"+userno);
		CommonMessage.debugMsg("The condParms"+condParms);
		paramValues.add(condParms);
		paramValues.add(commonParam);

     return  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST2.EHS_FN_NEARMISSMAINGRIDNEW", paramValues);
		return  dbActionTemplate.dbFunctionCall("TEST_PC_TEST2.EHS_FN_NEARMISSMAINGRIDNEW", paramValues);
		
	}*/

	@Override
	public String getUpdateInvest(String repsonse, String targetdate, String probable, String invest, String recomd,
			String keyid, String chko, String chks, String oa, String os,String remarks,String empid)
					throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		//CommonMessage.debugMsg("oa:::"+oa.length());
		//CommonMessage.debugMsg("oa:::"+os.length());
		 StringBuffer sql =new StringBuffer();
		 String chkov;
		 String chksv;
		 if(chko==null)
		 {
			 chko="-";
		 }
			
		 
		 
		 if(chks==null)
		 {
			 chks="-";
		 }
		 //CommonMessage.debugMsg("remarks::"+remarks);
			List<String> sqls = new ArrayList<String>();
			StringBuffer sql2 = new StringBuffer();
			
			
			if(oa.length()==0)
			{
				
				oa="-";
				//CommonMessage.debugMsg("sql2"+sql2);
			}
			
			if(os.length()==0)
			{   
				os="-";
			
			}
			
			
	    		 //CommonMessage.debugMsg(chko+chks);
	    	  if(chko.equals("N"))
	    	  {
	    		  
	    		  chko="Y";
	    	  }
	    	  else{
	    		  chko="N";
	    	  }
	    	  if(chks.equals("N"))
	    	  {
	    		  chks="Y";
	    	  }else{
	    		  chks="N";
	    	  }
	    	// }
			
			if(targetdate!=null&&targetdate.length()!=0&&repsonse!=null&&repsonse.length()!=0)
			{
				 //CommonMessage.debugMsg("Insdie the if");
				if(probable==null||probable.length()==0)
				{
					probable="-";
				//	//CommonMessage.debugMsg("INSIDE THE ELSE IN PROB");
				}
				
				if(recomd==null||recomd.length()==0)
				{
					recomd="-";
					////CommonMessage.debugMsg("INSIDE THE ELSE IN RECOMDS");
				}
                
				if(remarks==null||remarks.length()==0)
				{
					remarks="-";
				}
				
			
				
				
				if(invest==null||invest.length()==0)
				{
					invest="-";
					////CommonMessage.debugMsg("INSIDE THE ELSE IN RECOMDS");
				}
				
			
				sql2.append(" UPDATE GEN_TL_NEARMISSREPORTMSTNEW SET NMRN_INVESTIGATION='"+invest+"'");
				sql2.append(",NMRN_PROBABLERECRATE='"+probable+"'"+",NMRN_ACTIONRECOMMENDED='"+recomd+"'"); 
				sql2.append(",NMRN_TARGETDATE='"+targetdate+"'"+", NMRN_RESPONSIBILITY='"+repsonse+"'"); 
				sql2.append(",NMRN_CHKOTHERSUSA='"+chko+"'"+", NMRN_CHKOTHERSUSC='"+chks+"'");
				sql2.append(",NMRN_OTHERSUSC='"+os+"'");
				sql2.append(",NMRN_OTHERSUSA='"+oa+"'");
				sql2.append(",NMRN_INVRECREMARKS='"+remarks+"'");
				sql2.append(",NMRN_INVESTIGATIONBY='"+empid+"'");
				sql2.append(",NMRN_INVESTIGATIONDATE=SYSDATE ");
				
				sql2.append("WHERE NMRN_KEYID='"+keyid+"'"); 
				sqls.add(sql2.toString());
				 CommonMessage.debugMsg("SQLS::"+sqls);
				dbActionTemplate.executeStatements(sqls);
				
			}
			else{
				//CommonMessage.debugMsg("INSIDE THE ELSE");
				if(probable==null||probable.length()==0)
				{
					probable="-";
					//CommonMessage.debugMsg("INSIDE THE ELSE IN PROB");
				}
				if(repsonse==null||repsonse.length()==0)
				{
					 repsonse="{}";
						//CommonMessage.debugMsg("INSIDE THE ELSE IN RESP");
					
				}
				/* if(chko.equals("0")&&chks.equals("0"))
		    	 { 
					 chko="N";
					 chks="N";
					 //CommonMessage.debugMsg(chko+chks);
		    		 
		    	 }*/
		    	//
		    		 //CommonMessage.debugMsg(chko+chks);
		    	  if(chko.equals("N"))
		    	  {
		    		  
		    		  chko="Y";
		    	  }
		    	  else{
		    		  chko="N";
		    	  }
		    	  if(chks.equals("N"))
		    	  {
		    		  chks="Y";
		    	  }else{
		    		  chks="N";
		    	  }
		    	// }
				if(targetdate==null||targetdate.length()==0)
				{
					targetdate="01-Jan-1801";
					//CommonMessage.debugMsg("INSIDE THE ELSE IN TARGE");
				}
				if(recomd==null||recomd.length()==0)
				{
					recomd="-";
					//CommonMessage.debugMsg("INSIDE THE ELSE IN RECOMDS");
				}
				
				if(invest==null||invest.length()==0)
				{
					invest="-";
					//CommonMessage.debugMsg("INSIDE THE ELSE IN RECOMDS");
				}
				
				if(remarks==null)
				{
					remarks="-";
				}
				
			
				sql2.append(" UPDATE GEN_TL_NEARMISSREPORTMSTNEW SET NMRN_INVESTIGATION='"+invest+"'");
				sql2.append(",NMRN_PROBABLERECRATE='"+probable+"'"+",NMRN_ACTIONRECOMMENDED='"+recomd+"'"); 
				sql2.append(",NMRN_TARGETDATE='"+targetdate+"'"+", NMRN_RESPONSIBILITY='"+repsonse+"'"); 
				sql2.append(",NMRN_CHKOTHERSUSA='"+chko+"'"+", NMRN_CHKOTHERSUSC='"+chks+"'");
				sql2.append(",NMRN_OTHERSUSA='"+oa+"'");
				sql2.append(",NMRN_OTHERSUSC='"+os+"'");
				sql2.append(",NMRN_INVRECREMARKS='"+remarks+"'");
				sql2.append(",NMRN_INVESTIGATIONBY='"+empid+"'");
				sql2.append(",NMRN_INVESTIGATIONDATE=SYSDATE ");
			
				sql2.append("WHERE NMRN_KEYID='"+keyid+"'"); 
				sqls.add(sql2.toString());
				 CommonMessage.debugMsg("SQLS::"+sqls);
				dbActionTemplate.executeStatements(sqls);
			
			}
			
			return  "SUCCESS";
			
	}

	public List<String[]> getfillnewnearmissdata(String keyid, String flid)
			throws Exception {
		// TODO Auto-generated method stub
		
		StringBuilder sql = new StringBuilder();
		/*sql.append(" select a.empm_name as employeename,to_char(NMRN_PREPAREDDATETIME,'DD-MON-YYYY HH:MM'),sect_name as dmt,cell_code as jh,to_char(NMRN_OCCURRENCEDATETIME,'DD-MON-YYYY HH:MM'),NMRN_DESCNEARMISS, ");
		sql.append(" nmsp_name,prrr_name,nmrn_actionrecommended,b.empm_name,to_char(nmrn_targetdate,'DD-MON-YYYY'),DECODE(nmrn_status,'P','Pending','C','Completed') ");
		sql.append(" from GEN_TL_NEARMISSREPORTMSTNEW,SHE_TL_PROBABLERECURANCERATE,gen_tl_employeemst a,gen_tl_employeemst b, ");
		sql.append(" SHE_TL_NEARMISSSEVERITY,GEN_VW_FNLN ");
		sql.append(" where NMRN_EMPLOYEEID  =a.empm_keyid(+) AND NMRN_responsibility =b.empm_keyid (+) ");
		sql.append(" AND nmrn_probablerecrate = prrr_keyid AND NMSP_KEYID=NMRN_SEVERITYPOTENTIALID AND fnln_keyid=NMRN_flnid AND NMRN_keyid='"+keyid+"' ");
		*/
		
		sql.append(" select nmrn_keyid,a.empm_name as employeename,to_char(NMRN_PREPAREDDATETIME,'DD-MON-YYYY HH:MM'),sect_name as dmt,cell_code as jh,to_char(NMRN_OCCURRENCEDATETIME,'DD-MON-YYYY HH:MM'),NMRN_DESCNEARMISS, ");
		sql.append(" nmsp_name,e.empm_name,prrr_name,NMRN_INVESTIGATION,NMRN_ACTIONTAKEN,g.empm_name,nmrn_actionrecommended, DECODE(NMRN_RESPONSIBILITY,'{}',' ',b.empm_name),DECODE(NMRN_RESPONSIBILITY,'{}',' ',TO_CHAR(NMRN_TARGETDATE,'DD-Mon-YYYY')),DECODE(NMRN_RESPONSIBILITY,'{}',' ',TO_CHAR(NMRN_TARGETDATE,'DD-Mon-YYYY')),NMRN_INVESTIGATIONBY,to_char(NMRN_INVESTIGATIONDATE,'DD-MON-YYYY') ,");
		sql.append(" NMRN_ACTIONTAKEN, DECODE(NMRN_COMPLETEDBY,'{}',' ',c.empm_name) AS completedby,DECODE(NMRN_COMPLETEDBY,'{}',' ',TO_CHAR(NMRN_DATECOMPLETED,'DD-Mon-YYYY')),DECODE(NMRN_VERIFIEDBY,'{}','-',d.empm_name),DECODE(TO_CHAR(NMRN_DATEVERIFIED,'DD-Mon-YYYY'),'01-Jan-1801',' ',TO_CHAR(NMRN_DATEVERIFIED,'DD-Mon-YYYY')),DECODE(NMRN_CLOSEDBY,'{}','-',h.EMPM_NAME),DECODE(TO_CHAR(NMRN_CLOSEDDATE,'DD-Mon-YYYY'),'01-Jan-1801',' ',TO_CHAR(NMRN_CLOSEDDATE,'DD-Mon-YYYY'))  ");
		
		sql.append(" from GEN_TL_NEARMISSREPORTMSTNEW,SHE_TL_PROBABLERECURANCERATE,gen_tl_employeemst a,gen_tl_employeemst b,gen_tl_employeemst c, gen_tl_employeemst d,  gen_tl_employeemst e, gen_tl_employeemst g,");
		sql.append(" SHE_TL_NEARMISSSEVERITY,GEN_TL_EMPLOYEEMST h,GEN_VW_FNLN ");
		sql.append(" where NMRN_EMPLOYEEID  =a.empm_keyid(+) AND NMRN_responsibility =b.empm_keyid (+) AND NMRN_COMPLETEDBY=c.empm_keyid(+) AND NMRN_VERIFIEDBY=d.empm_keyid(+)  AND NMRN_CLOSEDBY=h.empm_keyid(+)");
		sql.append(" AND nmrn_probablerecrate = prrr_keyid(+) AND NMSP_KEYID=NMRN_SEVERITYPOTENTIALID AND NMRN_COMPLETEDBY=g.empm_keyid(+) AND NMRN_IDENTIFIEDBY=e.empm_keyid(+) AND NMRN_COMPLETEDBY=g.empm_keyid(+) AND fnln_keyid=NMRN_flnid AND NMRN_keyid='"+keyid+"' ");
	
		CommonMessage.debugMsg(" Checking mst data :: "+sql.toString());
		//CommonMessage.debugMsg("Sql::"+sql.toString());
		
		List<String[]>  mstrdata  = dbActionTemplate.getDataList(sql.toString());
	    return mstrdata;
	}
	@Override
	public List<String[]> getfillnewunsafeactdata(String keyid, String flid)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT  NMUA_KEYID,NEAR_NAME AS UnsafeAct ");
		sql.append(" FROM SHE_TL_NEARMISS,GEN_TL_NEARMISSREPORTDTLNEW,GEN_TL_NEARMISSREPORTMSTNEW ");
		sql.append(" WHERE  NEAR_KEYID=NMUA_NEARKEYID AND NMRN_KEYID(+) =  NMUA_NMRTKEYID ");
		sql.append(" AND NMUA_NMRTKEYID(+) ='"+keyid+"' AND NEAR_CODE = 'UC'  ORDER BY  NEAR_KEYID ");
		
		CommonMessage.debugMsg(" Checking mst data :: "+sql.toString());
		//CommonMessage.debugMsg("Sql::"+sql.toString());
		
		List<String[]>  unsafeconditiondata  = dbActionTemplate.getDataList(sql.toString());
	    return unsafeconditiondata;
	}
	@Override
	public List<String[]> getfillnewunsafeconditiondata(String keyid, String flid)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT  NMUA_KEYID,NEAR_NAME AS UnsafeAct ");
		sql.append(" FROM SHE_TL_NEARMISS,GEN_TL_NEARMISSREPORTDTLNEW,GEN_TL_NEARMISSREPORTMSTNEW ");
		sql.append(" WHERE  NEAR_KEYID=NMUA_NEARKEYID AND NMRN_KEYID(+) =  NMUA_NMRTKEYID ");
		sql.append(" AND NMUA_NMRTKEYID(+) ='"+keyid+"' AND NEAR_CODE = 'UA'  ORDER BY  NEAR_KEYID ");
		
		CommonMessage.debugMsg(" Checking mst data :: "+sql.toString());
		//CommonMessage.debugMsg("Sql::"+sql.toString());
		
		
		List<String[]>  unsafeactdata  = dbActionTemplate.getDataList(sql.toString());
	    return unsafeactdata;
	}

	@Override
	public List<String[]> getNearMissReleatedFileManager(String momKeyId) throws Exception {
		// TODO Auto-generated method stub
		GenTlNearmissreportmstnewSql genTlNearmissreportmstnewSql = new GenTlNearmissreportmstnewSql(); 
	String sql = genTlNearmissreportmstnewSql.selectgetReleatedFileManager(momKeyId);
		
		//CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}


}
	


