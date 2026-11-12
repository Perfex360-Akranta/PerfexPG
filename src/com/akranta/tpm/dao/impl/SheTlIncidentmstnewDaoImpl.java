package com.akranta.tpm.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.SheTlIncidentmstnewDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlAllmoduleimgfileSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
//import com.akranta.tpm.dao.sql.SheTlIncidentEmployeeSql;
import com.akranta.tpm.dao.sql.SheTlIncidentEmployeenewSql;
import com.akranta.tpm.dao.sql.SheTlIncidentmstSql;
import com.akranta.tpm.dao.sql.SheTlIncidentmstnewSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.SheTlIncidentEmployee;
import com.akranta.tpm.model.SheTlIncidentEmployeenew;

import com.akranta.tpm.model.SheTlIncidentmstnew;
import com.akranta.tpm.service.api.EmployeeGroupServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.SheTlIncidentmstnewServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.google.gson.internal.bind.SqlDateTypeAdapter;

import net.sf.json.JSONObject;

/* dao implementation */
public class SheTlIncidentmstnewDaoImpl implements SheTlIncidentmstnewDao {


	private DBActionTemplate dbActionTemplate; 
	SheTlIncidentmstnewSql sheTlIncidentmstnewSql;
	private SheTlIncidentmstnewServiceApi shetlincidentmstnewserviceapi;
	FunctionCallApi fnCallApi;
	
	public SheTlIncidentmstnewDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		sheTlIncidentmstnewSql = new SheTlIncidentmstnewSql();
	}
	
	public void SheTlIncidentmstnewDaoImplJwt(String JwtToken) 
	{
		try{
			shetlincidentmstnewserviceapi = new SheTlIncidentmstnewServiceApi(JwtToken);
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

	public SheTlIncidentmstnew create(SheTlIncidentmstnew sheTlIncidentmstnew) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SheTlIncidentmstnewSql sheTlIncidentmstnewSql = new SheTlIncidentmstnewSql(); // contains dbtable,field names, Field types and related sqls  of master table
		SheTlIncidentEmployeenewSql sheTlIncidentEmployeeSql = new SheTlIncidentEmployeenewSql();
		try{
			String elementId = sheTlIncidentmstnew.getSinmElementid();
		 	String location = null;
		 	String seqIdentf = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,SheTlIncidentmstnewSql.TBL_SHE_TL_INCIDENTMSTNEW);
			System.out.println("sequnce no : inc "+seqIdentf);
			sheTlIncidentmstnew.setSinmKeyid(dbActionTemplate.getSequenceNumber(seqIdentf,11,"INN","YY","Y")); // set the sequnce number 
			sqls.add(SheTlIncidentmstnewSql.getInsertSql(sheTlIncidentmstnewSql.getSinmDbFields(), sheTlIncidentmstnew.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return sheTlIncidentmstnew;
	}

	
	public SheTlIncidentmstnew update(SheTlIncidentmstnew sheTlIncidentmstnew)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		SheTlIncidentmstnewSql sheTlIncidentmstnewSql = new SheTlIncidentmstnewSql();
		try {

			sqls.add(SheTlIncidentmstnewSql.getUpdateSql(sheTlIncidentmstnewSql.getSinmDbFields(), sheTlIncidentmstnew.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return sheTlIncidentmstnew;
	}
	
	public SheTlIncidentmstnew delete(SheTlIncidentmstnew sheTlIncidentmstnew)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		SheTlIncidentmstnewSql sheTlIncidentmstnewSql = new SheTlIncidentmstnewSql();
		SheTlIncidentEmployeenewSql sheTlIncidentEmployeenewsql=new SheTlIncidentEmployeenewSql();
		try {
			
			sqls.add(SheTlIncidentmstnewSql.getDeleteSql(sheTlIncidentmstnewSql.getSinmDbFields(), sheTlIncidentmstnew.getSaveArray()));
			String sql=sheTlIncidentEmployeenewsql.getDeleteEmployee(sheTlIncidentmstnew.getSinmKeyid());
			sqls.add(sql);
			dbActionTemplate.executeStatements(sqls);
		String aplmkeyid=dbActionTemplate.getSingleValue("GEN_TL_ACTIONPLANMST", "APLM_KEYID", "APLM_MASTERREFID", sheTlIncidentmstnew.getSinmKeyid());
		
		if(aplmkeyid!=null)
		{
		deleteActionPlan(sheTlIncidentmstnew);
		}	
		
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return sheTlIncidentmstnew;
	}
	
	private String deleteActionPlan(SheTlIncidentmstnew sheTlIncidentmstnew) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		SheTlIncidentmstnewSql sheTlIncidentmstnewSql = new SheTlIncidentmstnewSql();
		String aplmkeyid=dbActionTemplate.getSingleValue("GEN_TL_ACTIONPLANMST", "APLM_KEYID", "APLM_MASTERREFID", sheTlIncidentmstnew.getSinmKeyid());
		String aplmdkeyid=dbActionTemplate.getSingleValue("GEN_TL_ACTIONPLANDTL", "APLD_KEYID", "APLD_APLM_KEYID", aplmkeyid);
		
		List<String> sqls = new ArrayList<String>();
		String detsql="DELETE FROM GEN_TL_ACTIONPLANDTL WHERE APLD_KEYID='"+aplmdkeyid+"'";
		String mstsql="DELETE FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"+aplmkeyid+"'";
		sqls.add(detsql);
		sqls.add(mstsql);
		dbActionTemplate.executeStatements(sqls);
		return mstsql;
		
		
	}

	public List<String[]> getAllIncidents(CommonFilter commonFilter) throws Exception
	{		
List<String> paramValues = new ArrayList<String>();
		
//		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
//		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
//	
//		paramValues.add(condParms);
//		paramValues.add(commonParams);
//		System.out.println("paramValues:::"+paramValues);
//		
//		
//		List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("GEN_TROUBLESHOOTING.ACCIDENTVIEW", paramValues);
//	
//    	System.out.println("DataList"+dataList);
//    	
//		if (commonFilter.getViewClick() == 'Y') {
//			String totalCnt = paramValues.get(0);
//			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
//			if (isInteger) {
//				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
//			}
//		
//		}
		
		
		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		if(CommonFunctions.isValidKeyId(commonFilter.getSafetyMode()))
		{
			if(commonFilter.getSafetyMode().equals("Create"))
				condParms += "SFTSTATUS=P"+";";
		}
		condParms += "FORMTYPE="+commonFilter.getDocType()+";";
		condParms+="FLID="+commonFilter.getActionKeyId()+";";	
		condParms += "EMPID="+commonFilter.getEmpch()+";";//	commonFilter.setAcceptenceRequired(rolename);
		condParms += "ROLE="+commonFilter.getAcceptenceRequired()+";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		System.out.println("condParms:::"+condParms);
		//List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("HSE_FN_ACCIDENTREPORT", paramValues);
		
		List<String[]> dataList =  fnCallApi.callFunction("HSE_FN_ACCIDENTREPORT_SB", paramValues,3,true);
		System.out.println("After " +commonFilter.getViewClick());
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonFunctions.debugMsg("totalCnt....."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
		return dataList;
	}
	@Override
	public SheTlIncidentEmployeenew recallEmployee(String keyId) throws Exception {
		
		SheTlIncidentEmployeenew sheTlIncidentEmployee = new SheTlIncidentEmployeenew();
		
		System.out.println("Before fetch the data");
		String sql = SheTlIncidentEmployeenewSql.getIncidentEmployee();
		
		System.out.println(sql);
		Object args [] = new Object [] {keyId};
		sheTlIncidentEmployee.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		System.out.println("Dao Impl"+sheTlIncidentEmployee);
			
		return sheTlIncidentEmployee;
		
	}
	@Override
	public List<GenTlAllmoduleimgfile> saveOplImg(SheTlIncidentmstnew sheTlIncidentmst) throws Exception,BusinessApplicationExceptions {
		
			List<String> sqls = new ArrayList<String>();
			List<Object[]> valueList  = new ArrayList<Object[]>();
			List<int[]> dataTypes  = new ArrayList<int[]>();
			List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = sheTlIncidentmst.getAllmoduleimgfile();
			System.out.println("oplTlMst.getAllmoduleimgfile()"+sheTlIncidentmst.getAllmoduleimgfile());
			
			for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:newGenTlAllmoduleimgfile)
			{
				System.out.println("FILE NAME:"+genTlAllmoduleimgfile.getImflFilename());
				System.out.println("BLOB LENGTH:"+genTlAllmoduleimgfile.getImflBloblength());
			
				if(!(UIUtils.isValidKeyId(genTlAllmoduleimgfile.getImflFilename())))
					continue;
			
				sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql());
				sqls.add(GenTlAllmoduleimgfileSql.getInsertSql());
				
				Object [] delValue	= { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype() };
				int [] delTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				java.sql.Timestamp  timeStamp = com.akranta.tpm.dao.impl.CommonFunctions.convertoSqlTimeStamp(genTlAllmoduleimgfile.getImflModifiedon()); 
				Object [] insValues = { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype(),
						genTlAllmoduleimgfile.getImflBlobimage(),genTlAllmoduleimgfile.getImflBloblength(),
						genTlAllmoduleimgfile.getImflFilename(),genTlAllmoduleimgfile.getImflTempfield1(),genTlAllmoduleimgfile.getImflTempfield2(),timeStamp};
				int [] insDataType = { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR, Types.BLOB,Types.INTEGER ,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP};
			
				valueList.add(delValue);
				valueList.add(insValues);
		
				dataTypes.add(delTypes);
				dataTypes.add(insDataType);
				}
			
			System.out.println("sqls ="+sqls);
			System.out.println("valueList ="+valueList);
			System.out.println("dataTypes ="+dataTypes);
			dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
		
		
		
		return null;
		
	}
	@Override
	public List<String[]> getIncidentEmployee(SheTlIncidentEmployeenew sheTlIncidentEmployee)throws Exception
	{
		try
		{
			String sql = SheTlIncidentEmployeenewSql.getIncidentEmployeesql(sheTlIncidentEmployee);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	//Changed By Swetha 27 Dec
	@Override
	public SheTlIncidentmstnew select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		SheTlIncidentmstnew sheTlIncidentmst = new SheTlIncidentmstnew();
		String sql = SheTlIncidentmstnewSql.selectIncident();		
		Object args [] = new Object [] { keyid };
		sheTlIncidentmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		//String getyy = "SELECT LISTAGG(WWMS_KEYID,',') within group(order by WWMS_KEYID) WWMS_KEYID FROM  BDM_TL_WHYWHYMST WHERE WWMS_REFDOCNO = '"+keyid+"'";
		String getyy =
				  "SELECT STRING_AGG(WWMS_KEYID, ',' ORDER BY WWMS_KEYID) " +
				  "FROM BDM_TL_WHYWHYMST WHERE WWMS_REFDOCNO = '"+keyid+"'";
		String yyno = dbActionTemplate.getSingleValue(getyy);
		sheTlIncidentmst.setSinmWhywhyno(yyno);
		return sheTlIncidentmst;
	}
	@Override
	public List<GenTlAllmoduleimgfile> getImage(List<GenTlAllmoduleimgfile> insImgList) throws Exception {
		
		System.out.println("Inside Dao impl Imgh");
		//List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
		List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
		for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:insImgList)
		{
			try{
			String condSql = " IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";
			String condSql1 = " AND IMFL_REFKEYID = '" + genTlAllmoduleimgfile.getImflRefkeyid() + "' AND " + condSql ;
			CommonFunctions.debugMsg("condSql...."+condSql);
			CommonFunctions.debugMsg("condSql1...."+condSql1);
			String Sql = "SELECT IMFL_FILENAME FROM GEN_TL_ALLMODULEIMGFILE WHERE IMFL_REFKEYID = '" + genTlAllmoduleimgfile.getImflRefkeyid() + "' AND  IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";
			CommonFunctions.debugMsg("SQL : "+Sql);
			String fileName = dbActionTemplate.getSingleValue(Sql);
			CommonFunctions.debugMsg("fileName...");
			//String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_FILENAME", "IMFL_REFKEYID", genTlAllmoduleimgfile.getImflRefkeyid(),condSql);
			CommonFunctions.debugMsg("fileName..."+fileName);
			
			if( fileName != null )
			{
				if( fileName.lastIndexOf("/") > -1 )
				fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
			
				String fileNamePath = genTlAllmoduleimgfile.getImflBlobimage()+  fileName; 
			
				String imgFileName = genTlAllmoduleimgfile.getImflFilename()+fileName;
				genTlAllmoduleimgfile.setImflFilename(imgFileName);
			
				System.out.println(" fileName " + fileNamePath);
				
				
				dbActionTemplate.restoreFile(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql1, fileNamePath);
				
				genTlAllmoduleimgList.add(genTlAllmoduleimgfile);
			}
			}catch(Exception e)
			{
				System.out.println("Inside exc");
				e.printStackTrace();
			}
		}
		
		return genTlAllmoduleimgList;
	}

	@Override
	public String AssignResponse(String keyid, String responsevalue, String targetdate,SheTlIncidentmstnew sheTlIncidentmstnew)throws Exception{
		try
		{
			List<String> sqls = new ArrayList<String>(); 
			StringBuffer sql = new StringBuffer(); 
			 if(sheTlIncidentmstnew.getSinmResponsibilityRemarks()==null||sheTlIncidentmstnew.getSinmResponsibilityRemarks().length()==0)
		       {
				 sheTlIncidentmstnew.setSinmResponsibilityRemarks("-");
		       }
			sql.append(" UPDATE SHE_TL_INCIDENTMSTNEW SET SINM_RESPONSIBILITY='"+responsevalue+"', SINM_RESPONSERMRKS='"+sheTlIncidentmstnew.getSinmResponsibilityRemarks()+"',SINM_TARGETDATE='"+targetdate+"' WHERE SINM_KEYID='"+ keyid+"'");
			//String sql = SheTlIncidentEmployeenewSql.getAssignResponse(keyid,responsevalue,targetdate,sheTlIncidentmstnew);
			//String commit =SheTlIncidentEmployeenewSql.getCommit();
			//sqls = dbActionTemplate.getDataList(sql);
			sqls.add(sql.toString()); 
			dbActionTemplate.executeStatements(sqls);
			return "Success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	//change by swetha - dec 31
	@Override
	public String AssignActionClosure(String keyid, String responsevalue, String targetdate, String reviseddate,
			String status, String correctiveaction, SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception {
		try
		{
			List<String> sqls = new ArrayList<String>(); 
			StringBuffer sql = new StringBuffer(); 
			 if(sheTlIncidentmstnew.getSinmCompletedRemarks()==null||sheTlIncidentmstnew.getSinmCompletedRemarks().length()==0)
		       {
				 sheTlIncidentmstnew.setSinmCompletedRemarks("-");
		       }
//			sql.append(" UPDATE SHE_TL_INCIDENTMSTNEW SET SINM_COMPLETEDBY='"+responsevalue+"'");
//			sql.append(",SINM_DATECOMPLETED='"+targetdate+"',SINM_REVISEDDATE='"+reviseddate+"',SINM_STATUS='"+status+"',SINM_ACTIONTAKEN='"+correctiveaction+"',SINM_COMPLETERMRKS='"+sheTlIncidentmstnew.getSinmCompletedRemarks()+"'"
//					+ " ,SINM_VERIFIEDSTATUS='"+"N"+"' WHERE SINM_KEYID='"+ keyid+"'");
			 
			 String revisedDateSql;
			 if (reviseddate == null || reviseddate.trim().isEmpty()) {
			     revisedDateSql = "NULL";
			 } else {
			     revisedDateSql = "'" + reviseddate + "'";
			 }
			 sql.append(" UPDATE SHE_TL_INCIDENTMSTNEW SET ");
			 sql.append(" SINM_COMPLETEDBY='").append(responsevalue).append("'");

			 sql.append(", SINM_DATECOMPLETED='").append(targetdate).append("'");

			 sql.append(", SINM_REVISEDDATE=").append(revisedDateSql);

			 sql.append(", SINM_STATUS='").append(status).append("'");

			 sql.append(", SINM_ACTIONTAKEN='").append(correctiveaction).append("'");

			 sql.append(", SINM_COMPLETERMRKS='")
			    .append(sheTlIncidentmstnew.getSinmCompletedRemarks()).append("'");

			 sql.append(", SINM_VERIFIEDSTATUS='N'");

			 sql.append(" WHERE SINM_KEYID='").append(keyid).append("'");
			
			sqls.add(sql.toString());  //sqls2.add(sql1.toString());
			dbActionTemplate.executeStatements(sqls);
			//sql = SheTlIncidentEmployeenewSql.getAssignActionClosure(keyid,responsevalue,targetdate,reviseddate,status,correctiveaction,sheTlIncidentmstnew);
			//String commit =SheTlIncidentEmployeenewSql.getCommit();
			 return "Success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String ActionVerification(String keyid, String responsevalue, String targetdate, String verifystatus,
			SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception {
		try
		{
			List<String> sqls = new ArrayList<String>(); 
			StringBuffer sql = new StringBuffer(); 
			 if(sheTlIncidentmstnew.getSinmVerifiedRemarks()==null||sheTlIncidentmstnew.getSinmVerifiedRemarks().length()==0)
		       {
				 sheTlIncidentmstnew.setSinmVerifiedRemarks("-");
		       }
			sql.append(" UPDATE SHE_TL_INCIDENTMSTNEW SET SINM_VERIFIEDBY='"+responsevalue+"'");
			sql.append(",SINM_DATEVERIFIED='"+targetdate+"',SINM_VERIFIEDSTATUS='"+verifystatus+"',SINM_VERIFYRMRKS='"+sheTlIncidentmstnew.getSinmVerifiedRemarks()+"',SINM_APPROVALSTATUS='"+"N"+"' WHERE SINM_KEYID='"+ keyid+"'");
			
			sqls.add(sql.toString());  //sqls2.add(sql1.toString());
			dbActionTemplate.executeStatements(sqls);
			//sql = SheTlIncidentEmployeenewSql.getAssignActionClosure(keyid,responsevalue,targetdate,reviseddate,status,correctiveaction,sheTlIncidentmstnew);
			//String commit =SheTlIncidentEmployeenewSql.getCommit();
			 return "Success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String saveClosure(String keyid, String responsevalue, String targetdate, String verifystatus,
			SheTlIncidentmstnew sheTlIncidentmstnew) {
		try
		{
			List<String> sqls = new ArrayList<String>(); 
			StringBuffer sql = new StringBuffer(); 
			 if(sheTlIncidentmstnew.getSinmApprovalRemarks()==null||sheTlIncidentmstnew.getSinmApprovalRemarks().length()==0)
		       {
				 sheTlIncidentmstnew.setSinmApprovalRemarks("-");
		       }
			sql.append(" UPDATE SHE_TL_INCIDENTMSTNEW SET SINM_APPROVALBY='"+responsevalue+"'");
			sql.append(",SINM_APPROVALDATE='"+targetdate+"',SINM_APPROVALSTATUS='"+verifystatus+"',SINM_APPROVALRMRKS='"+sheTlIncidentmstnew.getSinmApprovalRemarks()+"' WHERE SINM_KEYID='"+ keyid+"'");
			
			sqls.add(sql.toString());  //sqls2.add(sql1.toString());
			dbActionTemplate.executeStatements(sqls);
			//sql = SheTlIncidentEmployeenewSql.getAssignActionClosure(keyid,responsevalue,targetdate,reviseddate,status,correctiveaction,sheTlIncidentmstnew);
			//String commit =SheTlIncidentEmployeenewSql.getCommit();
			 return "Success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Workbook AccidentReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,
			String mode) throws Exception {
		ResultSet rs = null;
		
		 try{
				
			 rs =   getMOCModificationResultSet(commonFilter);
			 
			    ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				return excelUtils.writeToExcel(rs,format,2,0,0 );
				
			   }finally{
				DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		
	} 

	private ResultSet getMOCModificationResultSet(CommonFilter commonFilter) throws Exception {
	       List<String> paramValues = getFilterParamValues1(commonFilter);
	       return dbActionTemplate.NewdbFunctionCall2("HSE_FN_ACCIDENTREPORT", paramValues);
		//return null;
}
	private List<String> getFilterParamValues1(CommonFilter commonFilter) {
	       List <String>  paramvalues = new ArrayList<String>();
	       String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	       String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
	        paramvalues.add(condParms);
	       paramvalues.add(commonParams);
	       return paramvalues;
}

	

	private ResultSet getAccidentReportResultSet(CommonFilter commonFilter, String mode) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		List<String[]> relatedMst = null;
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		condParms += "FORMTYPE="+mode+";";
		condParms+="FLID="+commonFilter.getActionKeyId()+";";	
		condParms += "EMPID="+commonFilter.getEmpch()+";";//	commonFilter.setAcceptenceRequired(rolename);
		condParms += "ROLE="+commonFilter.getAcceptenceRequired()+";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		System.out.println("condParms:::"+condParms);
		return  dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.HSE_FN_ACCIDENTREPORT", paramValues);
	}
	//swetha query change-26Dec
	@Override
	public List<String[]> getNearMissEmpMailIds(String glbType, String flid, String Location) throws NoDataFoundException, Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT empm_name, empm_email FROM gen_tl_employeemst, gen_tl_fnlnroleteam, ehs_tl_mailconfiguration ");
		sql.append(" WHERE frt_role_keyid = ehsm_roleid ");
		sql.append(" AND ehsm_category = '" + glbType + "'");
		sql.append(" AND frt_fnln_keyid = '" + flid + "'"); // Removed Oracle outer join syntax
		sql.append(" AND empm_location = '" + Location + "'");
		sql.append(" AND frt_empm_keyid = empm_keyid ");
		sql.append(" AND empm_keyid IN (SELECT empm_keyid FROM gen_tl_employeemst WHERE empm_enableemail = 'Y') ");
		System.out.println("MAIL QUERY" + sql.toString());
		CommonFunctions.debugMsg("getMomAttendanceEmpMailIdSql==" + sql.toString());	
	
		
		//Object [] args = {flid,momKeyId};
		Object [] args = {};
		List<String []> data= dbActionTemplate.getDataList(sql.toString(),args);
		return data;
	}

	@Override
	public List<String[]> getIncidentReleatedFileManager(String momKeyId) throws Exception {
		// TODO Auto-generated method stub
		//String sql = GenTlMommstSql.selectgetMomReleatedFileManager(momKeyId);
		String sql = SheTlIncidentmstnewSql.selectgetReleatedFileManager(momKeyId);
		
		System.out.println("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}
	@Override
	public List<String[]> getfillaccidentEmpData(String keyId) throws Exception{    
	    StringBuilder sql=new StringBuilder();
		System.out.println("Inside the getfillaccidentEmpData");
//		sql.append(" SELECT DISTINCT SINE_KEYID,EMPM_KEYID,EMPM_CODE,EMPM_NAME,INJM_NAME,IBOP_NAME");
//		sql.append(" FROM SHE_TL_INCIDENT_EMPLOYEENEW,GEN_TL_EMPLOYEEMST,SHE_TL_INJURYMODEMST,SHE_TL_INCIDENTMST,SHE_TL_INCIDENTBODYPARTMST");
//		sql.append(" WHERE SINE_EMPM_KEYID =EMPM_KEYID AND SINE_INJURY_MODEID=INJM_KEYID AND SINE_BODYPART=IBOP_KEYID(+)");
//		sql.append(" AND SINE_SINC_KEYID= '" + keyId+ "' ");
		
		sql.append(" SELECT DISTINCT SINE_KEYID, EMPM_KEYID, EMPM_CODE, EMPM_NAME, INJM_NAME, IBOP_NAME");
		sql.append(" FROM SHE_TL_INCIDENT_EMPLOYEENEW");
		sql.append(" INNER JOIN GEN_TL_EMPLOYEEMST ON SINE_EMPM_KEYID = EMPM_KEYID");
		sql.append(" INNER JOIN SHE_TL_INJURYMODEMST ON SINE_INJURY_MODEID = INJM_KEYID");
		sql.append(" CROSS JOIN SHE_TL_INCIDENTMSTNEW");
		sql.append(" LEFT JOIN SHE_TL_INCIDENTBODYPARTMST ON SINE_BODYPART = IBOP_KEYID");
		sql.append(" WHERE SINE_SINC_KEYID = '" + keyId + "' ");
		List<String[]> datalist=dbActionTemplate.getDataList(sql.toString());
		System.out.println("The DataList"+sql.toString());
		return datalist;
	}
	
/*	@Override
	public List<String[]> getfillaccidentdata(String keyId,String flid) throws Exception{    	
	    StringBuilder sqldata=new StringBuilder();
	    System.out.println("Inside the getfillaccidentdata");
		
	sqldata.append(" SELECT SINM_KEYID AS sinm_keyid,A.ACTY_NAME AS sinm_accident_type,  sect_name AS dmt,cell_code AS jh,");
    sqldata.append(" to_char(SINM_OCCURRED_DATE,'DD-MON-YYY HH:MM') AS sinm_occurred_date,");
    sqldata.append(" SINM_AREA AS sinm_area,to_char(SINM_REPORTED_DATE,'DD-MON-YYY HH:MM') AS sinm_reported_date,A.EMPM_NAME AS sinm_reported_by, ");
    sqldata.append(" REPLACE (REPLACE (REPLACE (SINM_ACCIDENT_DESC,'{}'), '<*'),'*>') AS sinm_accident_desc,");
    //sqldata.append(" REPLACE (REPLACE (REPLACE (SINM_IMMEDIATE_ACTION, '{}'), '<*'),'*>') AS sinm_immediate_action,");
    sqldata.append("  SINM_CARECOMMENDED AS sinm_carecommended,SINM_RPTRECOMMENDATIONS AS sinm_rptrecommendations,");                    
   // sqldata.append("  to_char(SINM_INVRPTID) AS sinm_invrptid,SINM_CARECOMMENDED AS sinm_carecommended,");                    
    sqldata.append(" to_char(SINM_INVRPTDATE,'DD-MON-YYY') AS sinm_invrptdate,");                                      
    //sqldata.append(" REPLACE (REPLACE (REPLACE (SINM_EXACT_DESC, '{}'), '<*'),'*>') AS sinm_exact_desc,");
    //sqldata.append(" B.ACTY_NAME AS sinm_exact_type,B.EMPM_NAME AS sinm_analysed_by,SINM_INVESTIGATION AS sinm_investigation,");
    //sqldata.append(" B.EMPM_NAME AS sinm_analysed_by,SINM_INVESTIGATION AS sinm_investigation,");     
    sqldata.append(" SINM_INVESTIGATION AS sinm_investigation,");
   // sqldata.append(" to_char(SINM_ANALYSED_DATE,'DD-MON-YYY') AS sinm_analysed_date,SINM_ACTIONPLANNO AS sinm_actionplanno,");
    sqldata.append(" C.EMPM_NAME AS sinm_responsibility,to_char(SINM_TARGETDATE,'DD-MON-YYY') AS sinm_targetdate, ");
    //sqldata.append(" to_char(SINM_REVISEDDATE,'DD-MON-YYY') sinm_reviseddate, D.EMPM_NAME AS sinm_completedby,");
    //sqldata.append(" to_char(SINM_DATECOMPLETED,'DD-MON-YYY') sinm_datecompleted,SINM_ACTIONTAKEN AS sinm_actiontaken,");
    sqldata.append(" SINM_ACTIONTAKEN AS sinm_actiontaken,");
    sqldata.append(" D.EMPM_NAME AS sinm_completedby,TO_CHAR(SINM_DATECOMPLETED,'DD-MON-YYY') sinm_datecompleted,");
    sqldata.append(" E.EMPM_NAME AS sinm_verifiedby,to_char(SINM_DATEVERIFIED,'DD-MON-YYY') AS sinm_dateverified ");	
	//sqldata.append(" F.EMPM_NAME AS sinm_approvalby,to_char(SINM_APPROVALDATE,'DD-MON-YYY') AS sinm_approvaldate ");
	sqldata.append(" FROM SHE_TL_INCIDENTMSTNEW,GEN_VW_FNLN,GEN_TL_EMPLOYEEMST A,GEN_TL_EMPLOYEEMST B,GEN_TL_EMPLOYEEMST C,");
	sqldata.append(" GEN_TL_EMPLOYEEMST D,GEN_TL_EMPLOYEEMST E,GEN_TL_EMPLOYEEMST F,SHE_TL_ACCIDENT_TYPEMST A,SHE_TL_ACCIDENT_TYPEMST B,");
	sqldata.append(" GEN_TL_SHIFTMST A,GEN_TL_SHIFTMST B,GEN_TL_SHIFTMST C ");
	sqldata.append(" WHERE SINM_OCCURRED_SHIFT=A.SFTM_KEYID(+) AND SINM_REPORTED_SHIFT=B.SFTM_KEYID(+) AND SINM_ANALYSED_SHIFT=C.SFTM_KEYID(+)");
	sqldata.append(" AND SINM_REPORTED_BY=A.EMPM_KEYID(+) AND SINM_ANALYSED_BY=B.EMPM_KEYID(+) ");
	sqldata.append(" AND SINM_RESPONSIBILITY=C.EMPM_KEYID(+) AND SINM_COMPLETEDBY=D.EMPM_KEYID(+) ");
	sqldata.append(" AND fnln_keyid=SINM_FLID AND SINM_VERIFIEDBY=E.EMPM_KEYID(+) AND SINM_APPROVALBY=F.EMPM_KEYID(+) ");
	sqldata.append(" AND SINM_ACCIDENT_TYPE=A.ACTY_KEYID(+) AND SINM_EXACT_TYPE=B.ACTY_KEYID(+) ");	
	sqldata.append("  AND sinm_keyid='" +keyId + "' ");	
	
	List<String[]> contentdata=dbActionTemplate.getDataList(sqldata.toString());
	System.out.println("The Final ContentData "+sqldata.toString());
	return contentdata;
	}*/
	
	@Override
	public List<String[]> getfillaccidentdata(String keyId,String flid) throws Exception{    	
	StringBuilder sqldata=new StringBuilder();		
//	sqldata.append(" SELECT SINM_KEYID AS sinm_keyid,A.ACTY_NAME AS sinm_accident_type,  sect_name AS dmt,cell_code AS jh,");
//    sqldata.append(" to_char(SINM_OCCURRED_DATE,'DD-MON-YYYY HH:MM') AS sinm_occurred_date,");
//    sqldata.append(" SINM_AREA AS sinm_area,to_char(SINM_REPORTED_DATE,'DD-MON-YYYY HH:MM') AS sinm_reported_date,A.EMPM_NAME AS sinm_reported_by, ");
//    sqldata.append(" REPLACE (REPLACE (REPLACE (SINM_ACCIDENT_DESC,'{}'), '<*'),'*>') AS sinm_accident_desc,");
//   // sqldata.append("  SINM_CARECOMMENDED AS sinm_carecommended,SINM_RPTRECOMMENDATIONS AS sinm_rptrecommendations,");                    
//   // sqldata.append(" SINM_RPTRECOMMENDATIONS AS sinm_rptrecommendations, SINM_ANALYSED_BY AS SINM_ANALYSED_BY, ");
//    sqldata.append(" SINM_CARECOMMENDED AS CARECOMME, SINM_ANALYSED_BY AS SINM_ANALYSED_BY, ");
//   // sqldata.append(" SINM_ANALYSED_BY AS SINM_ANALYSED_BY,");
// //   sqldata.append(" to_char(SINM_INVRPTDATE,'DD-MON-YYYY HH:MM') AS sinm_invrptdate,");
//    sqldata.append(" decode(TO_CHAR(SINM_INVRPTDATE, 'DD-MON-YYYY'),'01-JAN-1801','',to_char(SINM_INVRPTDATE, 'DD-MON-YYYY ')) AS sinm_invrptdate,");
//    sqldata.append(" SINM_INVESTIGATION AS sinm_investigation, ");
//    //sqldata.append(" SINM_INVESTIGATION AS sinm_investigation, SINM_ANALYSED_BY AS SINM_ANALYSED_BY, ");
//    sqldata.append(" SINM_WITNESS1 AS witness1,SINM_WITNESS2 AS witness ");
//    sqldata.append(" FROM SHE_TL_INCIDENTMSTNEW, GEN_VW_FNLN,GEN_TL_EMPLOYEEMST A,GEN_TL_EMPLOYEEMST B, ");
//    sqldata.append(" GEN_TL_EMPLOYEEMST E,GEN_TL_EMPLOYEEMST F,SHE_TL_ACCIDENT_TYPEMST A,SHE_TL_ACCIDENT_TYPEMST B,");
//    sqldata.append(" GEN_TL_SHIFTMST A,GEN_TL_SHIFTMST B,GEN_TL_SHIFTMST C ");
//    sqldata.append(" WHERE SINM_OCCURRED_SHIFT=A.SFTM_KEYID(+) AND SINM_REPORTED_SHIFT  =B.SFTM_KEYID(+) AND SINM_ANALYSED_SHIFT  =C.SFTM_KEYID(+) ");
//	sqldata.append(" AND SINM_REPORTED_BY=A.EMPM_KEYID(+) AND SINM_ANALYSED_BY=B.EMPM_KEYID(+) ");
//	sqldata.append(" AND fnln_keyid=SINM_FLID AND SINM_VERIFIEDBY=E.EMPM_KEYID(+) AND SINM_APPROVALBY=F.EMPM_KEYID(+)AND SINM_ACCIDENT_TYPE=A.ACTY_KEYID(+) ");
//    sqldata.append(" AND SINM_EXACT_TYPE=B.ACTY_KEYID(+) AND sinm_keyid= '" +keyId+ "' ");
	sqldata.append(" SELECT SINM.SINM_KEYID AS sinm_keyid, ");
	sqldata.append(" ACT.ACTY_NAME AS sinm_accident_type, ");
	sqldata.append(" FNLN.sect_name AS dmt, FNLN.cell_code AS jh, ");

	sqldata.append(" to_char(SINM.SINM_OCCURRED_DATE,'DD-MON-YYYY HH24:MI') AS sinm_occurred_date, ");
	sqldata.append(" SINM.SINM_AREA AS sinm_area, ");
	sqldata.append(" to_char(SINM.SINM_REPORTED_DATE,'DD-MON-YYYY HH24:MI') AS sinm_reported_date, ");
	sqldata.append(" REP.EMPM_NAME AS sinm_reported_by, ");

	sqldata.append(" REPLACE( ");
	sqldata.append("   REPLACE( ");
	sqldata.append("     REPLACE(SINM.SINM_ACCIDENT_DESC,'{}',''), ");
	sqldata.append("     '<*',''), ");
	sqldata.append("   '*>','') AS sinm_accident_desc, ");

	sqldata.append(" SINM.SINM_CARECOMMENDED AS carecomme, ");
	sqldata.append(" SINM.SINM_ANALYSED_BY AS sinm_analysed_by, ");

	sqldata.append(" CASE ");
	sqldata.append("   WHEN to_char(SINM.SINM_INVRPTDATE,'DD-MON-YYYY') = '01-JAN-1801' ");
	sqldata.append("   THEN '' ");
	sqldata.append("   ELSE to_char(SINM.SINM_INVRPTDATE,'DD-MON-YYYY') ");
	sqldata.append(" END AS sinm_invrptdate, ");

	sqldata.append(" SINM.SINM_INVESTIGATION AS sinm_investigation, ");
	sqldata.append(" SINM.SINM_WITNESS1 AS witness1, ");
	sqldata.append(" SINM.SINM_WITNESS2 AS witness ");

	sqldata.append(" FROM SHE_TL_INCIDENTMSTNEW SINM ");

	sqldata.append(" LEFT JOIN GEN_VW_FNLN FNLN ");
	sqldata.append("   ON FNLN.fnln_keyid = SINM.SINM_FLID ");

	sqldata.append(" LEFT JOIN GEN_TL_EMPLOYEEMST REP ");
	sqldata.append("   ON SINM.SINM_REPORTED_BY = REP.EMPM_KEYID ");

	sqldata.append(" LEFT JOIN GEN_TL_EMPLOYEEMST ANA ");
	sqldata.append("   ON SINM.SINM_ANALYSED_BY = ANA.EMPM_KEYID ");

	sqldata.append(" LEFT JOIN GEN_TL_EMPLOYEEMST VER ");
	sqldata.append("   ON SINM.SINM_VERIFIEDBY = VER.EMPM_KEYID ");

	sqldata.append(" LEFT JOIN GEN_TL_EMPLOYEEMST APR ");
	sqldata.append("   ON SINM.SINM_APPROVALBY = APR.EMPM_KEYID ");

	sqldata.append(" LEFT JOIN SHE_TL_ACCIDENT_TYPEMST ACT ");
	sqldata.append("   ON SINM.SINM_ACCIDENT_TYPE = ACT.ACTY_KEYID ");

	sqldata.append(" LEFT JOIN SHE_TL_ACCIDENT_TYPEMST EXA ");
	sqldata.append("   ON SINM.SINM_EXACT_TYPE = EXA.ACTY_KEYID ");

	sqldata.append(" LEFT JOIN GEN_TL_SHIFTMST OCC ");
	sqldata.append("   ON SINM.SINM_OCCURRED_SHIFT = OCC.SFTM_KEYID ");

	sqldata.append(" LEFT JOIN GEN_TL_SHIFTMST REP_SFT ");
	sqldata.append("   ON SINM.SINM_REPORTED_SHIFT = REP_SFT.SFTM_KEYID ");

	sqldata.append(" LEFT JOIN GEN_TL_SHIFTMST ANA_SFT ");
	sqldata.append("   ON SINM.SINM_ANALYSED_SHIFT = ANA_SFT.SFTM_KEYID ");

	sqldata.append(" WHERE SINM.SINM_KEYID = '" + keyId + "' ");

	List<String[]> contentdata=dbActionTemplate.getDataList(sqldata.toString());
	System.out.println("The Final ContentData "+sqldata.toString());
	return contentdata;
	}
	
	@Override
	public List<String[]> getfillaccidentResData(String keyid)throws Exception{
		
		StringBuilder sqlData1=new StringBuilder();
//		sqlData1.append(" SELECT SINM_RPTRECOMMENDATIONS AS sinm_rptrecommendations,C.EMPM_NAME AS sinm_responsibility, ");
//		//sqlData1.append(" SELECT SINM_CARECOMMENDED AS CARECOMME,C.EMPM_NAME AS sinm_responsibility, ");
//		sqlData1.append(" decode(TO_CHAR(SINM_TARGETDATE, 'DD-MON-YYYY'),'01-JAN-1801','',to_char(SINM_TARGETDATE, 'DD-MON-YYYY ')) AS sinm_targetdate, ");
//		sqlData1.append(" SINM_ACTIONTAKEN  AS sinm_actiontaken,D.EMPM_NAME AS sinm_completedby, "); 
//		//sqlData1.append(" TO_CHAR(SINM_TARGETDATE,'DD-MON-YYY') AS sinm_targetdate,SINM_ACTIONTAKEN  AS sinm_actiontaken,D.EMPM_NAME AS sinm_completedby, "); 
//		sqlData1.append(" decode(TO_CHAR(SINM_DATECOMPLETED, 'DD-MON-YYYY'),'01-JAN-1801','',to_char(SINM_DATECOMPLETED, 'DD-MON-YYYY ')) AS sinm_datecompleted, ");
//		sqlData1.append(" E.EMPM_NAME  AS sinm_verifiedby, ");
//		sqlData1.append(" decode(TO_CHAR(SINM_DATEVERIFIED, 'DD-MON-YYYY'),'01-JAN-1801','',to_char(SINM_DATEVERIFIED, 'DD-MON-YYYY ')) AS sinm_dateverified ");
//		//sqlData1.append(" TO_CHAR(SINM_DATECOMPLETED,'DD-MON-YYY') sinm_datecompleted,E.EMPM_NAME  AS sinm_verifiedby,TO_CHAR(SINM_DATEVERIFIED,'DD-MON-YYY') AS sinm_dateverified ");
//		sqlData1.append(" FROM SHE_TL_INCIDENTMSTNEW,GEN_VW_FNLN,GEN_TL_EMPLOYEEMST C,GEN_TL_EMPLOYEEMST D,GEN_TL_EMPLOYEEMST E ");
//		sqlData1.append(" WHERE SINM_RESPONSIBILITY=C.EMPM_KEYID(+) AND SINM_COMPLETEDBY=D.EMPM_KEYID(+) AND fnln_keyid=SINM_FLID ");
//		sqlData1.append(" AND SINM_VERIFIEDBY=E.EMPM_KEYID(+) AND sinm_keyid= '"+keyid+"'");
		
		sqlData1.append(" SELECT SINM_RPTRECOMMENDATIONS AS sinm_rptrecommendations, C.EMPM_NAME AS sinm_responsibility, ");
		sqlData1.append(" CASE WHEN TO_CHAR(SINM_TARGETDATE, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE to_char(SINM_TARGETDATE, 'DD-MON-YYYY ') END AS sinm_targetdate, ");
		sqlData1.append(" SINM_ACTIONTAKEN AS sinm_actiontaken, D.EMPM_NAME AS sinm_completedby, ");
		sqlData1.append(" CASE WHEN TO_CHAR(SINM_DATECOMPLETED, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE to_char(SINM_DATECOMPLETED, 'DD-MON-YYYY ') END AS sinm_datecompleted, ");
		sqlData1.append(" E.EMPM_NAME AS sinm_verifiedby, ");
		sqlData1.append(" CASE WHEN TO_CHAR(SINM_DATEVERIFIED, 'DD-MON-YYYY') = '01-JAN-1801' THEN '' ELSE to_char(SINM_DATEVERIFIED, 'DD-MON-YYYY ') END AS sinm_dateverified ");
		sqlData1.append(" FROM SHE_TL_INCIDENTMSTNEW ");
		sqlData1.append(" INNER JOIN GEN_VW_FNLN ON fnln_keyid = SINM_FLID ");
		sqlData1.append(" LEFT JOIN GEN_TL_EMPLOYEEMST C ON SINM_RESPONSIBILITY = C.EMPM_KEYID ");
		sqlData1.append(" LEFT JOIN GEN_TL_EMPLOYEEMST D ON SINM_COMPLETEDBY = D.EMPM_KEYID ");
		sqlData1.append(" LEFT JOIN GEN_TL_EMPLOYEEMST E ON SINM_VERIFIEDBY = E.EMPM_KEYID ");
		sqlData1.append(" WHERE sinm_keyid = '" + keyid + "'");
		System.out.println("The Final sqlData1 Content::: "+sqlData1.toString());
		List<String[]> getFillAccidentCon=dbActionTemplate.getDataList(sqlData1.toString());
		return getFillAccidentCon;
	}	
	public List<GenTlAllmoduleimgfile> getIncidentImages(List<GenTlAllmoduleimgfile> IncidentImgList) throws NoDataFoundException, Exception{
		
		System.out.println("Inside Dao impl EXL Image ");
		List<GenTlAllmoduleimgfile> genAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
		for(GenTlAllmoduleimgfile gentlallModuleimage:IncidentImgList)
		{
			//String condSql = " IMFL_REFDOCTYPE = '" + gentlallModuleimage.getImflRefdoctype()+ "' ";
			String condSql = " IMFL_REFDOCTYPE = '" + gentlallModuleimage.getImflRefdoctype()+ "'AND IMFL_IMAGETYPE = '" + gentlallModuleimage.getImflImagetype()+"'";
 ;
			System.out.println("The condSql::::"+condSql);
			String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE,"IMFL_FILENAME","IMFL_REFKEYID", gentlallModuleimage.getImflRefkeyid(),condSql); 	
			System.out.println("The File Name::::"+fileName);
			if( fileName != null )
			{
				if( fileName.lastIndexOf("/") > -1 )
				fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
				String fileNamePath = gentlallModuleimage.getImflBlobimage()+  fileName;
				System.out.println(" The fileNamePath:::" + fileNamePath);
				String imgFileName = gentlallModuleimage.getImflFilename()+fileName;
				System.out.println(" IMFI FILENAME:::: " + imgFileName);
				gentlallModuleimage.setImflFilename(fileNamePath);
				System.out.println(" INFI set fileName " + fileNamePath);
				genAllmoduleimgList.add(gentlallModuleimage);
			}
		}
		return genAllmoduleimgList;
	}
	
	public List<String[]> getAllImageDetail(String keyId,String filePath,CommonFilter commonFilter)
			throws Exception {
		
		System.out.println("Inside Into getAllImageDetail");
		StringBuffer sql = new StringBuffer();
		List<String> params = null;
		sql.append(SheTlIncidentmstnewSql.getAllImageList(keyId));
		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
		
		
		List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(sql.toString(), params);
		String refdoctype="INC";
		//String imagetype="PRE";
		System.out.println("");
		String condSql = " IMFL_IMAGETYPE  like'%" +"IN"+ "%' AND IMFL_REFDOCTYPE = '" + refdoctype+"'";
			int rIndex=0;
			
		for (String[] row : gridData) {
			if( rIndex  >1){
				
				String fileName =  filePath+row[1];
				if(row[1] != null && ! row[1].isEmpty()){
					String condSql1 = " AND IMFL_REFKEYID = '" + row[0] + "' AND " + condSql ;
                 try{
                	 System.out.println("CountSql::"+fileName);
					dbActionTemplate.restoreFile(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql1, fileName);
					String CountSql = "select count(*) from GEN_TL_ALLMODULEIMGFILE where 1=1 " + condSql1 ;
					
					String Filecount = dbActionTemplate.getSingleValue(CountSql);
					System.out.println("CountSql" + Filecount);
					if (Integer.parseInt(Filecount) > 0 )
					{
						gridData.get(rIndex)[3] = fileName;
					}
					else
					{
						gridData.get(rIndex)[3] = "no-image";
					}
                 }
                 catch(Exception e){
                	 if("NO-DATA".equals( e.getMessage())){
                		  
                		 gridData.get(rIndex)[2] =  "no-image";
                	 } 
                 }
				}
			}
			rIndex++;
		}
		return gridData;
	}	
}
	
	
	
	

