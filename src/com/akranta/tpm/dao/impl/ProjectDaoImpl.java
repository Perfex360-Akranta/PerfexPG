package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.ProjectDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlDmcfipworkflowSql;
import com.akranta.tpm.dao.sql.GenTlMomKpiLinkSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.dao.sql.GenTlWorkflowInfoSql;
import com.akranta.tpm.dao.sql.KznTlDmcfipcreationmstSql;
import com.akranta.tpm.dao.sql.KznTlProjectKaizenLinkSql;
import com.akranta.tpm.dao.sql.KznTlProjectKpiLinkSql;
import com.akranta.tpm.dao.sql.KznTlProjectResourceLinkSql;
import com.akranta.tpm.dao.sql.KznTlProjectcreationmstSql;
import com.akranta.tpm.dao.sql.KznTlProjectdmaicstatusSql;
import com.akranta.tpm.dao.sql.KznTlProjectmaicMileDtlSql;
import com.akranta.tpm.dao.sql.KznTlProjectmaicMileMstSql;
import com.akranta.tpm.dao.sql.SheTlRiskassessmentmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlDmcfipworkflow;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.KznTlDmcfipcreationmst;
import com.akranta.tpm.model.KznTlKkprojectprioritydtl;
import com.akranta.tpm.model.KznTlProjectKaizenLink;
import com.akranta.tpm.model.KznTlProjectKpiLink;
import com.akranta.tpm.model.KznTlProjectResourceLink;
import com.akranta.tpm.model.KznTlProjectcreationmst;
import com.akranta.tpm.model.KznTlProjectdmaicstatus;
import com.akranta.tpm.model.KznTlProjectmaicMileDtl;
import com.akranta.tpm.model.KznTlProjectmaicMileMst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.exportreport.ProjectExcelTemplete;

public class ProjectDaoImpl implements ProjectDao {
	
	private DBActionTemplate dbActionTemplate;	
	private KznTlProjectcreationmstSql kznTlProjectcreationmstSql;
	FunctionCallApi fnCallApi;
	
	public ProjectDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void ProjectDaoImplJwt(String JwtToken) 
	{
		try{
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public List<String[]> getResources(CommonFilter commonFilter) throws Exception{		
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = "";
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms=condParms+"KZPMKEYID="+commonFilter.getKey()+";";
			}	
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;

			//dataList =  dbActionTemplate.processFunctionCalls("KZN_FN_PROJECTRESOURCELIST", paramValues);
			dataList = fnCallApi.callFunction("KZN_FN_PROJECTRESOURCELIST_SB", paramValues, 3, true); 
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public List<String[]> getMilesStone(String keyId) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 'Keyid' as keyid,'Stages','Milestone','Description','Responsiblity','Status','Completed by','Completed Date'  ");
		if(UIUtils.isValidKeyId(keyId)){
			sql.append(" union all select kmmm_keyid as keyid,CASE KMMM_STAGES "
					+ "    WHEN 'D' THEN 'Define' "
					+ "    WHEN 'M' THEN 'Measure' "
					+ "    WHEN 'A' THEN 'Analyse' "
					+ "    WHEN 'I' THEN 'Improve' "
					+ "	WHEN 'C' THEN 'CONTROL' "
					+ "    ELSE NULL  "
					+ "END,kmmd_milestone,kmmd_description,b.empm_name,CASE KMMM_STATUS "
					+ "    WHEN 'P' THEN 'Pending' "
					+ "    WHEN 'W' THEN 'Work In Progress' "
					+ "    WHEN 'C' THEN 'Completed' "
					+ "    WHEN 'S' THEN 'Short Close' "
					+ "    ELSE NULL "
					+ "END,a.empm_name,to_char(KMMM_TODATE,'dd-Mon-YYYY')");
			sql.append(" from KZN_TL_PROJ_MILESTONE_DTL JOIN KZN_TL_PROJ_MILESTONE_MST ON KMMM_KEYID = KMMD_KMMM_KEYID  "
					+ " JOIN gen_tl_employeemst a ON a.empm_keyid= kmmm_empm_keyid "
					+ " JOIN gen_tl_employeemst b ON b.empm_keyid = kmmd_empm_keyid where ");
			sql.append("  kmmm_kzpm_keyid ='");
			sql.append(keyId);
			sql.append('\'');
		}
		CommonMessage.debugMsg("sql:::Milestone"+sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
	}
	@Override
	public List<String[]> getKaizen(String masterId, String flid) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT 'Kaizen No.','Date','Done By','Link' FROM DUAL UNION ALL");
//		sql.append(" select KZNM_KEYID,to_char(KZNM_DATE,'dd-Mon-YYYY'),EMPM_NAME,'' FROM KZN_TL_MST, gen_tl_employeemst,KZN_TL_PROJECT_KAIZEN_LINK WHERE kznm_keyid = kplk_kznm_keyid  AND empm_keyid = kznm_createdby   AND kznm_flid = '");
		sql.append("SELECT 'Kaizen No.','Date','Done By','Link'  UNION ALL");
		sql.append(" select KZNM_KEYID,to_char(KZNM_DATE,'dd-Mon-YYYY'),EMPM_NAME,'' FROM KZN_TL_MST JOIN gen_tl_employeemst ON empm_keyid = kznm_createdby  JOIN KZN_TL_PROJECT_KAIZEN_LINK ON kznm_keyid = kplk_kznm_keyid WHERE    kznm_flid = '");
		sql.append(flid+"'");
		sql.append("and kplk_kzpm_keyid ='"+masterId+"'");
		CommonMessage.debugMsg("sql:::Kaizen...."+sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
	}
	
	@Override
	public List<String[]> getDmaic(String projectId) throws Exception {
		// TODO Auto-generated method stub
		/*StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT * FROM (");
		sql.append(" 				SELECT 'Stage','Description','Responsibility','Status','Remarks','Completed By','Completed Date','Verified By','Verified Date',");
		sql.append(" 'Doc.Ref',0 AS DATAORDER FROM DUAL");
		sql.append(" UNION  SELECT 'Define','Description Define','Employee 6','Completed','Remarks 1','Employee 1','21-Nov-2013','Employee 2','22-Nov-2013','1',1 AS DATAORDER FROM DUAL");
		sql.append(" UNION  SELECT 'Measure','Description Measure','Employee 6','Completed','Remarks 2','Employee 2','22-Nov-2013','Employee 3','25-Nov-2013','1',2 AS DATAORDER FROM DUAL");
		sql.append(" UNION  SELECT 'Analyse','Description Analyse','Employee 6','Completed','Remarks','Employee 3','21-Nov-2013','Employee 4','26-Nov-2013','1',3 AS DATAORDER FROM DUAL");
		sql.append(" UNION  SELECT 'Improve','Description Improve','Employee 8','Work in Progress','Remarks 4','Employee 4','','','','1',4 AS DATAORDER FROM DUAL");
		sql.append(" UNION  SELECT 'Control','Description Control','Employee 9','Pending','Remarks for Pending','','','','','1',5 AS DATAORDER FROM DUAL");
		sql.append(" ) ORDER BY DATAORDER");
		//KZN_PC_KAIZEN.KZN_FN_DMAICSTAUSLIST
		CommonMessage.debugMsg("sql:::"+sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
		*/
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = "";
			if(CommonFunctions.isValidKeyId(projectId)){
				condParms=condParms+"KZPMKEYID="+projectId+";";
			}	
			String commonParams = "";// FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			dataList =  dbActionTemplate.processFunctionCalls("KZN_PC_KAIZEN.KZN_FN_DMAICSTAUSLIST", paramValues);
			/*if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}*/
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}

		
	}

	
	public List<String[]> getProject(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub		
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			CommonMessage.debugMsg(commonFilter.getType() + " commonFilter.getType() ");
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms+="ISCLOSURE="+commonFilter.getKey()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getType())){
				condParms+="STAGE="+commonFilter.getType()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getAtype())){
				condParms+="APPROVAL="+commonFilter.getAtype()+";";
			}
			
		   if(commonFilter.getEmpch()!=null)
		   {
				condParms+="EMPNO="+commonFilter.getEmpch() +";";
		   }
		   String rolename=null;
		   
		   if(commonFilter.getEmpWise()!=null)
		   {
			   rolename=commonFilter.getEmpWise();
		   }
		   //MAIC STAGE APPROVALS FOR MENU LEVEL
		   if(commonFilter.getType().equals("MAIC"))
		   {
			   String sql="Select Count(*) from GEN_TL_FNLNROLETEAM WHERE FRT_ROLE_KEYID='AROL0060' AND FRT_EMPM_KEYID='"+ commonFilter.getEmpch()+"'";
			     String count=dbActionTemplate.getSingleValue(sql);
			     int countval=Integer.parseInt(count);
			     if(countval!=0)
			     {
			    	 condParms+="ROLENAME="+"KK CHAMPION"+";";
			     }
			     else{
			    	 condParms+="ROLENAME="+null+";";
			     }
			     
			   
		   }
		   
		   if(commonFilter.getType().equals("DEFINE")||commonFilter.getType().equals("closure"))
		   {
			   //String sql="Select ROLE_NAME from GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST WHERE ROLE_KEYID=FRT_ROLE_KEYID  AND FRT_EMPM_KEYID='"+ commonFilter.getEmpch()+"'";
			   		//"AND FRT_FNLN_KEYID='"+commonFilter.getFlid() +"'" +
			   				//For Getting the role name list
			    /// String Rolename=dbActionTemplate.getSingleValue(sql);
			   //CommonMessage.debugMsg("sql"+sql);
			  // List<String[]> rolelist=dbActionTemplate.getDataList(sql);
			   int i;
			  
			  // for (i=0;i<rolelist.size();i++)// for loop for separate and check below roles are there are not
//			   {
				   //rolename=rolelist.get(i)[0];
				  // rolename=commonFilter.getEmpWise();
				  // if(rolename.equals("FINANCE")||rolename.equals("KK CHAMPION")||rolename.equals("PBU HEAD")||rolename.equals("PROJECT LEAD"))
				//   {
					   //rolename=rolename
					  // condParms+="ROLENAME="+rolename+";";//if there means it will set to to condition params and exit the for loop
					 //  break;
				//   }
				 //  else{
					  // rolename=null;
				 //  }
					   
				  
				   
				   
//			   }
			   if(commonFilter.getType().equals("DEFINE"))
				   
			   { 
				   
				   /*if(rolename!=null)
				   {
					   
					   if(rolename.equals("DMT LEADER"))
					     {
						   String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0003' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
		                                       //   FNLNID='"+commonFilter.getFlid()+"' )";
							CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
						    dbActionTemplate.executeStatement(InsertQuery1);
						   
						 }
					   
				   if(rolename.equals("PBU HEAD"))
			     {
				   String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0002' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
                                       //   FNLNID='"+commonFilter.getFlid()+"' )";
					CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
				    dbActionTemplate.executeStatement(InsertQuery1);
				   
				    String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='W'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0003' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery2);
			    dbActionTemplate.executeStatement(InsertQuery2);
				 }
				   
				   if(rolename.equals("SBU HEAD"))
				     {
					   String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0001' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                                       //   FNLNID='"+commonFilter.getFlid()+"' )";
						CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery1);
					   
					    String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='W'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL IN ('AROL0003','AROL0002') AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery2);
				    dbActionTemplate.executeStatement(InsertQuery2);
					 }
				   
				   if(rolename.equals("UNIT HEAD"))
				     {
					   String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0103' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                                       //   FNLNID='"+commonFilter.getFlid()+"' )";
						CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery1);
					   
					    String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='W'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL IN('AROL0001','AROL0002','AROL0003') AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery2);
				    dbActionTemplate.executeStatement(InsertQuery2);
					 }
				   
			    if(rolename.equals("FINANCE"))
			   {
			    	String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0061' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
				    dbActionTemplate.executeStatement(InsertQuery1);
				   
			
			    	String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='W'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL IN ('AROL0001','AROL0002','AROL0003','AROL0103' )AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery2);
				    dbActionTemplate.executeStatement(InsertQuery2);
			    }
			    
			    
			    if(rolename.equals("KK CHAMPION"))
				   {
			    	String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0060' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery1);
					   
				  
				    	String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='W'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL IN ('AROL0001','AROL0002','AROL0003','AROL0103','AROL0061') AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
		                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery2);
				    }*/
				   
				   if(rolename!=null)
				   {
				   if(rolename.equals("PBU HEAD"))
			     {
				   String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0002' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
                                       //   FNLNID='"+commonFilter.getFlid()+"' )";
					CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
				    dbActionTemplate.executeStatement(InsertQuery1);
				   
				 }
			    if(rolename.equals("FINANCE"))
			   {
					   //madhan
					 
						
						/*
						 * String
						 * InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0061' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"
						 * +commonFilter.getFlid()+"') >0)";
						 * CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
						 * dbActionTemplate.executeStatement(InsertQuery1);
						 * 
						 */
					  
					  String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='W'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0002' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND STAGE='FIPRODEF'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
					  CommonMessage.debugMsg("InsertQuery"+InsertQuery2);
					  dbActionTemplate.executeStatement(InsertQuery2);
					 
			    }
			    
			    
			    if(rolename.equals("KK CHAMPION"))
				   {
						
						/*
						 * String
						 * InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0060' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"
						 * +commonFilter.getFlid()+"') >0)";
						 * CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
						 * dbActionTemplate.executeStatement(InsertQuery1);
						 * 
						 * 
						 * String
						 * InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='W'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL IN ('AROL0002','AROL0061') AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"
						 * +commonFilter.getFlid()+"') >0)";
						 * CommonMessage.debugMsg("InsertQuery"+InsertQuery2);
						 * dbActionTemplate.executeStatement(InsertQuery2);
						 * 
						 * String
						 * InsertQuery3="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"
						 * +commonFilter.getFlid()+"') >0)";
						 * CommonMessage.debugMsg("InsertQuery3InsertQuery3 "+InsertQuery3);
						 * dbActionTemplate.executeStatement(InsertQuery3);
						 */
						 
				    }
			    
			  /*  if(rolename.equals("PROJECT LEAD"))
			    {
			    	String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  KZPM_KEYID from KZN_TL_PROJECTCREATIONMST,GEN_MV_FLIDHIERARCHY where  FLID=KZPM_FLID AND KZPM_DEFINESTAGE IN (''-'')    AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
				    dbActionTemplate.executeStatement(InsertQuery1);
			    	
			    }*/
			    
				   }
				   
			   } 
			   
			   if(commonFilter.getType().equals("closure")){
				   
			   }
				   
				   if(commonFilter.getType().equals("closure"))
					   
				   { 
					   
					   if(rolename!=null)
					   {
						   
						   if(rolename.equals("DMT LEADER"))
						     {
							   String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0003' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
			                                       //   FNLNID='"+commonFilter.getFlid()+"' )";
								CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
							    dbActionTemplate.executeStatement(InsertQuery1);
							   
							 }
						   
					   if(rolename.equals("PBU HEAD"))
				     {
					   String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0002' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
	                                       //   FNLNID='"+commonFilter.getFlid()+"' )";
						CommonMessage.debugMsg("InsertQuery PBU HEAD "+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery1);
					   
					    String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='W'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0003' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND STAGE='FIPRODEF'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
	                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery2);
				    dbActionTemplate.executeStatement(InsertQuery2);
					 }
					   
					   
					   if(rolename.equals("PBU HEAD"))
				     {
					   String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0002' AND STAGE='FIPRODEF' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
	                                       //   FNLNID='"+commonFilter.getFlid()+"' )";
						CommonMessage.debugMsg("InsertQuery pbu head"+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery1);
					   
					 }
					   
					   if(rolename.equals("SBU HEAD"))
					     {
						   String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0001' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
		                                       //   FNLNID='"+commonFilter.getFlid()+"' )";
							CommonMessage.debugMsg("InsertQuery "+InsertQuery1);
						    dbActionTemplate.executeStatement(InsertQuery1);
						   
						    String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='W'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL IN ('AROL0003','AROL0002') AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND STAGE='FIPRODEF'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
		                  	CommonMessage.debugMsg("InsertQuery sbu head"+InsertQuery2);
					       dbActionTemplate.executeStatement(InsertQuery2);
						 }
					   
					   if(rolename.equals("UNIT HEAD"))
					     {
						   String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0103' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
		                                       //   FNLNID='"+commonFilter.getFlid()+"' )";
							CommonMessage.debugMsg("InsertQuery UNIT "+InsertQuery1);
						    dbActionTemplate.executeStatement(InsertQuery1);
						   
						    String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='W'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL IN('AROL0001','AROL0002','AROL0003') AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND STAGE='FIPRODEF'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
		                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery2);
					    dbActionTemplate.executeStatement(InsertQuery2);
						 }
					   
				    if(rolename.equals("FINANCE"))
				   {
				    	//String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0061'  AND STAGE='FIPROCLO' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";

				    	
				    	String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0061'  AND STAGE='FIPRODEF' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
		                  	CommonMessage.debugMsg("InsertQuery FINANACE "+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery1);
					   
				
				    	String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='W'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL IN ('AROL0001','AROL0002','AROL0003','AROL0103') AND STAGE='FIPROCLO' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
		                  	CommonMessage.debugMsg("InsertQuery "+InsertQuery2);
					    dbActionTemplate.executeStatement(InsertQuery2);
				    }
				    
				    
				    if(rolename.equals("KK CHAMPION"))
					   {
				    	String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0060' AND STAGE='FIPRODEF' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
		                  	CommonMessage.debugMsg("InsertQuery KK"+InsertQuery1);
						    dbActionTemplate.executeStatement(InsertQuery1);
						   
					  
					    	String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='W'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL IN ('AROL0001','AROL0002','AROL0003','AROL0103','AROL0061') AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPROCLO'  AND POSITION ('"+commonFilter.getFlid()+"' IN parentflids || '-' || flid) >0)";
			                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
						    dbActionTemplate.executeStatement(InsertQuery2);
					    }
				    
				   /* if(rolename.equals("PROJECT LEAD"))
				    {
				    	String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  KZPM_KEYID from KZN_TL_PROJECTCREATIONMST,GEN_MV_FLIDHIERARCHY where  FLID=KZPM_FLID AND KZPM_DEFINESTAGE IN (''-'')   AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery1);
				    	
				    }*/
				    
					   }
				   }
			   
			   if(rolename!=null)
			   {
				   condParms+="ROLENAME="+rolename+";";
			   }
		   else{
				   condParms+="ROLENAME="+null+";";
			   }
			   
			 /*    CommonMessage.debugMsg("Rolename"+Rolename);
			  //   int countval=Integer.parseInt(count);
			     if(Rolename!=null)
			     {
			    	 condParms+="ROLENAME="+Rolename+";";
			     }
			     else{
			    	 condParms+="ROLENAME="+null+";";
			     }
			 */    
			   
		   }
		   
		   
		   //DEFINE STAG EAPPROVALS FOR MENU LEVEL
			/*if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
				condParms=condParms+"FLID="+commonFilter.getFlid()+";";
			}*/	
		
		   
			 List<String[]> dataList = null;
			String fiptype=commonFilter.getActwise();
			CommonMessage.debugMsg("fiptype="+commonFilter.getActwise());
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 CommonMessage.debugMsg("condParms"+condParms);
			 CommonMessage.debugMsg("commonParams"+commonParams);
			 if(fiptype.equals("DMC"))
			 {
			 dataList =  dbActionTemplate.processFunctionCalls("JHN_FN_DMCPROJECTCREATIONMST", paramValues);
			 }
			 else
			 {
			dataList = fnCallApi.callFunction("JHN_FN_PROJECTCREATIONMST_SB", paramValues, 3, false);
			 //dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_PROJECTCREATIONMST", paramValues);

		/*  String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_DEFINESTAGE='W'";
          dbActionTemplate.executeStatement(InsertQuery2);
          String InsertQuery3="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_CLOSURESTAGE='W'";
          dbActionTemplate.executeStatement(InsertQuery3);*/
			 }
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	
	
	public List<String[]> getProjectview(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub		
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			/*String role=null;
			CommonMessage.debugMsg(commonFilter.getType() + " commonFilter.getType() ");
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms+="ISCLOSURE="+commonFilter.getKey()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getType())){
				condParms+="STAGE="+commonFilter.getType()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getAtype())){
				condParms+="APPROVAL="+commonFilter.getAtype()+";";
			}
			
		   if(commonFilter.getEmpch()!=null)
		   {
				condParms+="EMPNO="+commonFilter.getEmpch() +";";
		   }
		   
		   if(commonFilter.getEmpWise()!=null)
		   {
			   role=commonFilter.getEmpWise();
		   }
		   //MAIC STAGE APPROVALS FOR MENU LEVEL
		   if(commonFilter.getType().equals("MAIC"))
		   {
			   //String sql="Select Count(*) from GEN_TL_FNLNROLETEAM WHERE FRT_ROLE_KEYID='AROL0060' AND FRT_EMPM_KEYID='"+ commonFilter.getEmpch()+"'";
			   //  String count=dbActionTemplate.getSingleValue(sql);
			  //   int countval=Integer.parseInt(count);
			   //  if(countval!=0)
			    // {
			    	 if(role.equals("KK CHAMPION"))
			    	 { 
			    	 condParms+="ROLENAME="+"KK CHAMPION"+";";
			    	 }
			   //  }
			    // else{
			    	 //condParms+="ROLENAME="+null+";";
			    // }
			     
			   
		   }
		   
		   if(commonFilter.getType().equals("DEFINE")||commonFilter.getType().equals("closure"))
		   {
			   String sql="Select ROLE_NAME from GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST WHERE ROLE_KEYID=FRT_ROLE_KEYID  AND FRT_EMPM_KEYID='"+ commonFilter.getEmpch()+"'";
			   		//"AND FRT_FNLN_KEYID='"+commonFilter.getFlid() +"'" +
			   				//For Getting the role name list
			    /// String Rolename=dbActionTemplate.getSingleValue(sql);
			   CommonMessage.debugMsg("sql"+sql);
			   List<String[]> rolelist=dbActionTemplate.getDataList(sql);
			   int i;
			   String rolename=null;
			   for (i=0;i<rolelist.size();i++)// for loop for separate and check below roles are there are not
			   {
				   rolename=rolelist.get(i)[0];
				   if(rolename.equals("FINANCE")||rolename.equals("KK CHAMPION")||rolename.equals("PBU HEAD")||rolename.equals("PROJECT LEAD"))
				   {
					   if(role.equals(rolename))
						   {   
					   //rolename=rolename
					  // condParms+="ROLENAME="+rolename+";";//if there means it will set to to condition params and exit the for loop
					         break;
						   }
				   }
				   else{
					   rolename=null;
				   }
					   
				  
				   
				   
			   }
			   if(commonFilter.getType().equals("DEFINE"))
				   
			   { 
				   
				   if(rolename!=null)
				   {
				   if(rolename.equals("PBU HEAD"))
			     {
				   String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0002' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
                                       //   FNLNID='"+commonFilter.getFlid()+"' )";
					CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
				    dbActionTemplate.executeStatement(InsertQuery1);
				   
				 }
			    if(rolename.equals("FINANCE"))
			   {
			    	String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0061' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
				    dbActionTemplate.executeStatement(InsertQuery1);
				   
			
			    	String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='-'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0002' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery2);
				    dbActionTemplate.executeStatement(InsertQuery2);
			    }
			    
			    
			    if(rolename.equals("KK CHAMPION"))
				   {
			    	String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0060' AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery1);
					   
				  
				    	String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='-'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL IN ('AROL0002','AROL0061') AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPRODEF'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
		                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery2);
				    }
			    
			    if(rolename.equals("PROJECT LEAD"))
			    {
			    	String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_DEFINESTAGE='P'  where KZPM_KEYID IN(select  KZPM_KEYID from KZN_TL_PROJECTCREATIONMST,GEN_MV_FLIDHIERARCHY where  FLID=KZPM_FLID AND KZPM_DEFINESTAGE IN (''-'')    AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
				    dbActionTemplate.executeStatement(InsertQuery1);
			    	
			    }
			    
				   }
				   
			   }   
				   
				   if(commonFilter.getType().equals("closure"))
					   
				   { 
					   
					   if(rolename!=null)
					   {
					   if(rolename.equals("PBU HEAD"))
				     {
					   String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0002' AND STAGE='FIPROCLO' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                                       //   FNLNID='"+commonFilter.getFlid()+"' )";
						CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery1);
					   
					 }
				    if(rolename.equals("FINANCE"))
				   {
				    	String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0061'  AND STAGE='FIPROCLO' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
		                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery1);
					   
				
				    	String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='-'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0002' AND STAGE='FIPROCLO' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
		                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery2);
					    dbActionTemplate.executeStatement(InsertQuery2);
				    }
				    
				    
				    if(rolename.equals("KK CHAMPION"))
					   {
				    	String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL='AROL0060' AND STAGE='FIPROCLO' AND FLID=FNLNID AND MENULEVELSTATUS='P'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
		                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
						    dbActionTemplate.executeStatement(InsertQuery1);
						   
					  
					    	String InsertQuery2="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='-'  where KZPM_KEYID IN(select  PROJECTNO from KK_TL_FIPAPPROVALSLIST,GEN_MV_FLIDHIERARCHY where NEXTAPPROVAL IN ('AROL0002','AROL0061') AND FLID=FNLNID AND MENULEVELSTATUS='P' AND STAGE='FIPROCLO'  AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
			                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
						    dbActionTemplate.executeStatement(InsertQuery2);
					    }
				    
				    if(rolename.equals("PROJECT LEAD"))
				    {
				    	String InsertQuery1="UPDATE KZN_TL_PROJECTCREATIONMST SET   KZPM_CLOSURESTAGE='P'  where KZPM_KEYID IN(select  KZPM_KEYID from KZN_TL_PROJECTCREATIONMST,GEN_MV_FLIDHIERARCHY where  FLID=KZPM_FLID AND KZPM_DEFINESTAGE IN (''-'')   AND INSTR (parentflids || '-' || flid,'"+commonFilter.getFlid()+"') >0)";
	                  	CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
					    dbActionTemplate.executeStatement(InsertQuery1);
				    	
				    }
				    
					   }
				   }
			   
			   if(rolename!=null)
			   {
				   condParms+="ROLENAME="+rolename+";";
			   }
		   else{
				   condParms+="ROLENAME="+null+";";
			   }
			   
			     CommonMessage.debugMsg("Rolename"+Rolename);
			  //   int countval=Integer.parseInt(count);
			     if(Rolename!=null)
			     {
			    	 condParms+="ROLENAME="+Rolename+";";
			     }
			     else{
			    	 condParms+="ROLENAME="+null+";";
			     }
			     
			   
		   }
		   
		   
		   //DEFINE STAG EAPPROVALS FOR MENU LEVEL
			if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
				condParms=condParms+"FLID="+commonFilter.getFlid()+";";
			}	
		
		  */ 
			 List<String[]> dataList = null;
			//String fiptype=commonFilter.getActwise();
			//CommonMessage.debugMsg("fiptype="+commonFilter.getActwise());
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 CommonMessage.debugMsg("condParms"+condParms);
			 CommonMessage.debugMsg("commonParams"+commonParams);
			
			 dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_DMCPROJECTCREATIONVIEW", paramValues);
			
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	/**/
	public List<String[]> getChangeprolead(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub		
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			CommonMessage.debugMsg(commonFilter.getType() + " commonFilter.getType() ");
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms+="ISCLOSURE="+commonFilter.getKey()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getType())){
				condParms+="STAGE="+commonFilter.getType()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getAtype())){
				condParms+="APPROVAL="+commonFilter.getAtype()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
				condParms=condParms+"FLID="+commonFilter.getFlid()+";";
			}	
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 String fiptype=commonFilter.getMainkeyid();
			 List<String[]> dataList = null;
			 if(fiptype.equals("dmcfip")){
			 dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZEN.KZN_FN_DMCFIPCHANGEPROLEAD",paramValues);
			 }
			 else{
			 dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZEN.JHN_FN_CHANGEPROLEAD",paramValues); 
			 }
			 //CommonMessage.debugMsg("commingggggggggggg");
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	/*************/
	public Workbook getChangeproleadExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		 ResultSet rs = null;
		 CommonMessage.debugMsg("excel");
			// TODO Auto-generated method stub
			 try{
					
				 rs =   getChangeproleadResultSet(commonFilter);
				 
				 ExcelUtils excelUtils = new ExcelUtils(colmodel);
					return excelUtils.writeToExcel(rs,format, 2,0,0 );
					
				   }finally{
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				   }
	}
	
	


	private ResultSet getChangeproleadResultSet(CommonFilter commonFilter) throws Exception{
		List<String> paramValues = new ArrayList<String>();
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String condParms =  FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		/*if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms=condParms+"ISCLOSURE="+commonFilter.getKey()+";";
		}*/
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			
			condParms=condParms+"FLID="+commonFilter.getFlid()+";";
		}	
		CommonMessage.debugMsg("condParms");
		 paramValues.add(condParms);
		 paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("KZN_PC_KAIZEN.JHN_FN_CHANGEPROLEAD", paramValues);
	}
	/***/


	
	public List<String[]> getgetprojectchampD(String Keyid) throws Exception
	{
		try
		{
		 	String sql ="";
		 	StringBuffer buffer = new StringBuffer();
		 	buffer.append (" select kzpm_projectchamp from kzn_tl_projectcreationmst where kzpm_keyid='"+Keyid+"'");

		 	sql = buffer.toString();
		 	CommonMessage.debugMsg("sqllll"+sql);
			return dbActionTemplate.getDataList(sql);			 
		}
		catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
	}
	
	
	/**/
	@Override
	public List<String[]> getAuthorization(String type) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql= new StringBuffer();
		sql.append("select 'Stages',' ','Name','Approval Status','Remarks' from dual");
				 if("team".equals(type)){
					 sql.append(" union all");
					 sql.append(" select ' ','Team Leader','T SRINU-104585','Approved','' from dual");
				 }
				 else if("champion".equals(type)){
					 sql.append(" union all");
					 sql.append(" select '','Team Leader','T SRINU-104585','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select '','Champion','SK SALEEM-1152','Approved','' from dual");
				 }
				 else if("dmt".equals(type)){
					 sql.append(" union all");
					 sql.append(" select '', 'Team Leader','T SRINU-104585','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select '','Champion','SK SALEEM-1152','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select '','DMT Lead','K.NAGARAJU-18313','Approved','' from dual");
				 }else if("finance".equals(type)){
					 sql.append(" union all");
					 sql.append(" select '','Team Leader','T SRINU-104585','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select '','Champion','SK SALEEM-1152','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select '','DMT Lead','K.NAGARAJU-18313','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select '','Finance','J.SHIVA KUMAR-2580','Rejected','Remarks 1' from dual");
				 }else if("sbu".equals(type)){
					 sql.append(" union all");
					 sql.append(" select '','Team Leader','T SRINU-104585','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select '','Champion','SK SALEEM-1152','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select '','DMT Lead','K.NAGARAJU-18313','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select '','Finance','J.SHIVA KUMAR-2580','Approved','Remarks 1' from dual");
					 sql.append(" union all");
					 sql.append(" select '','SBU Head','S.S.SATPATHY-97660','Rejected','Remarks 2' from dual");
				 }	else{
					 sql.append(" union all");
					 sql.append(" select 'Measure','KK Champion','','','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Measure','Project Lead','','','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Analyse','KK Champion','','','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Analyse','Project Lead','','','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Improve','KK Champion','','','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Improve','Project Lead','','','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Control','KK Champion','','','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Control','Project Lead','','','' from dual");
				 }
			
				 CommonMessage.debugMsg("sql....."+sql);
			List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
			return gridData;

	}
	@Override
	public List<String[]> getApproval(CommonFilter commonFilter)throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 'Stage','Status','Approved Date','Approved by','Comment'");
		sql.append("  FROM DUAL UNION ALL");
		sql.append(" SELECT 'Project Lead','Pending','15-Apr-2013','Employee1','Comment1' from dual union all");
		sql.append(" SELECT 'Project Champion','Completed','02-Aug-2013','ATPL','Comment2' from dual union all");
		sql.append(" SELECT 'Finance Head','Completed','10-Dec-2013','Employee1','Comment3' from dual union all");
		sql.append(" SELECT 'KK Pillar Champion','Pending','15-Oct-2013','ATPL','Comment4' from dual ");
		CommonMessage.debugMsg("sql:::"+sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
	}
	@Override
	public List<String[]> getKpi(String keyid) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT '1' as keyid,'Key Performance Indicator','Base Value','Target Value'  ");
		
		if(UIUtils.isValidKeyId(keyid)){
			sql.append(" union all select kpkl_keyid as keyid,kink_indicatorname,KPKL_BASEVAL :: TEXT,KPKL_TARGETVAL :: TEXT from kpi_tl_indicator JOIN gen_tl_tpmpillarmst ON KINK_PILLARID = TPMP_KEYID LEFT JOIN kzn_tl_project_kpi_link ON  kpkl_kink_keyid = kink_keyid WHERE  KINK_ISCHILD = 'Y'");
			sql.append("  and kpkl_kzpm_keyid='");
			sql.append(keyid);
			sql.append("'");
			sql.append(" order by keyid");
		}
		
		CommonMessage.debugMsg("sql:::"+sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
	}
	
	@Override
	public KznTlProjectcreationmst create(KznTlProjectcreationmst newKznTlProjectcreationmst)throws ValidationExceptions,BusinessApplicationExceptions,Exception  {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		KznTlProjectcreationmstSql kznTlProjectcreationmstSql = new KznTlProjectcreationmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		KznTlProjectResourceLinkSql kznTlProjectResourceLinkSql=new KznTlProjectResourceLinkSql();
		GenTlWorkflowInfoSql genTlWorkflowInfoSql=new GenTlWorkflowInfoSql();
	
		String elementId = newKznTlProjectcreationmst.getElementid();
	 	String location = null;
	 	CommonMessage.debugMsg("elementId :"+elementId);
	 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,KznTlProjectcreationmstSql.TBL_KZN_TL_PROJECTCREATIONMST);
	 	CommonMessage.debugMsg("seqIdentfi :"+seqIdentfi);
		CommonMessage.debugMsg("IN side the Create method Daoimpl");
		newKznTlProjectcreationmst.setKzpmKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,15,"FI","YYMMDD","Y")); // set the sequnce number
		//newKznTlProjectcreationmst.setKzpmKeyid(dbActionTemplate.getSequenceNumber(KznTlProjectcreationmstSql.TBL_KZN_TL_PROJECTCREATIONMST,15,"FI","YYMMDD","Y")); // set the sequnce number
		String no = newKznTlProjectcreationmst.getKzpmKeyid().substring(9);
		newKznTlProjectcreationmst.setKzpmProjectno(no);
		sqls.add(KznTlProjectcreationmstSql.getInsertSql(kznTlProjectcreationmstSql.getKzpmDbFields(), newKznTlProjectcreationmst.getSaveArray())); // add insert sql for master table
		if (newKznTlProjectcreationmst.getProjectResourceList()!=null){
			
			GenSequenceNumber resSeq = new GenSequenceNumber( dbActionTemplate.getDataSource().getConnection(), KznTlProjectResourceLinkSql.TBL_KZN_TL_PROJECT_RESOURCE_LINK,15,"KPR","YYMMDD","Y") ;
			for(int i=0;i<newKznTlProjectcreationmst.getProjectResourceList().size();i++){
				
				KznTlProjectResourceLink kznTlProjectResourceLink=newKznTlProjectcreationmst.getProjectResourceList().get(i);
		//		CommonMessage.debugMsg("getKprlEmpmKeyid:"+ i+ kznTlProjectResourceLink.getKprlEmpmKeyid());
				kznTlProjectResourceLink.setKprlKzpmKeyid(newKznTlProjectcreationmst.getKzpmKeyid());
				kznTlProjectResourceLink.setKprlKeyid(resSeq.getSequnceNumber()); // set the sequnce number
			    sqls.add(KznTlProjectResourceLinkSql.getInsertSql(kznTlProjectResourceLinkSql.getKprlDbFields(), kznTlProjectResourceLink.getSaveArray()));
			}
			resSeq.closeConnection();
			resSeq =null;
		}
		if (newKznTlProjectcreationmst.getWorkFlowApp()!=null){
			GenTlWorkflowInfo genTlWorkflowInfo =newKznTlProjectcreationmst.getWorkFlowApp();
			genTlWorkflowInfo.setWrinRefId(newKznTlProjectcreationmst.getKzpmKeyid());
			try{
				String projectLeadId = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_ROLEMST, "ROLE_KEYID", "UPPER(COALESCE(ROLE_NAME, ' '))", "PROJECTLEAD");
				genTlWorkflowInfo.setWrinRoleId(projectLeadId);
			}catch(Exception e){
				genTlWorkflowInfo.setWrinRoleId("AROL0059");
			}
			genTlWorkflowInfo.setWrinKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_GEN_TL_WORKFLOW_INFO, 10, "WF", null,null));
			sqls.add(genTlWorkflowInfoSql.getInsertSql(genTlWorkflowInfoSql.getWrinDbFields(), genTlWorkflowInfo.getSaveArray()));
		}
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		return newKznTlProjectcreationmst;
			
	}
	
	@Override
	public KznTlProjectcreationmst update(KznTlProjectcreationmst kznTlProjectcreationmst)throws ValidationExceptions,BusinessApplicationExceptions,Exception  {
		List<String> sqls = new ArrayList<String>();
		KznTlProjectcreationmstSql kznTlProjectcreationmstSql = new KznTlProjectcreationmstSql();
		
		
		sqls.add(KznTlProjectcreationmstSql.getUpdateSql(kznTlProjectcreationmstSql.getKzpmDbFields(), kznTlProjectcreationmst.getSaveArray()));
		if("define".equalsIgnoreCase(kznTlProjectcreationmst.getMode()) )
			CommonMessage.debugMsg(" In side the Workflow Update method");
			sqls.add(GenTlWorkflowInfoSql.getStatusUpdateSql(kznTlProjectcreationmst.getKzpmKeyid(),kznTlProjectcreationmst.getKzpmCreatedby() ));
		dbActionTemplate.executeStatements(sqls);			
		return kznTlProjectcreationmst;
		
	}
	
	@Override
	public KznTlProjectcreationmst updateMAICStatus(KznTlProjectcreationmst kznTlProjectcreationmst, String stage)throws ValidationExceptions,BusinessApplicationExceptions,Exception  {
		List<String> sqls = new ArrayList<String>();
		KznTlProjectcreationmstSql kznTlProjectcreationmstSql = new KznTlProjectcreationmstSql();
	
		sqls.add(KznTlProjectcreationmstSql.getUpdateMAICStatusSql(kznTlProjectcreationmst,stage));
		dbActionTemplate.executeStatements(sqls);			
		return kznTlProjectcreationmst;
		
	}
	@Override
	public KznTlDmcfipcreationmst createdmc(KznTlDmcfipcreationmst newKznTlDmcfipcreationmst,GenTlDmcfipworkflow newGenTlDmcfipworkflow)throws ValidationExceptions,BusinessApplicationExceptions,Exception  {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		KznTlDmcfipcreationmstSql kznTlDmcfipcreationmstSql = new KznTlDmcfipcreationmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		KznTlProjectResourceLinkSql kznTlProjectResourceLinkSql=new KznTlProjectResourceLinkSql();
		GenTlWorkflowInfoSql genTlWorkflowInfoSql=new GenTlWorkflowInfoSql();
		GenTlDmcfipworkflowSql genTlDmcfipworkflowSql=new GenTlDmcfipworkflowSql();
	
		String elementId = newKznTlDmcfipcreationmst.getElementid();
	 	String location = null;
	 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,KznTlDmcfipcreationmstSql.TBL_KZN_TL_DMCFIPCREATIONMST);
		
	 	newKznTlDmcfipcreationmst.setDmcmKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,15,"FI","YYMMDD","Y")); // set the sequnce number
		//newKznTlProjectcreationmst.setKzpmKeyid(dbActionTemplate.getSequenceNumber(KznTlProjectcreationmstSql.TBL_KZN_TL_PROJECTCREATIONMST,15,"FI","YYMMDD","Y")); // set the sequnce number
		String no = newKznTlDmcfipcreationmst.getDmcmKeyid().substring(9);
		newKznTlDmcfipcreationmst.setDmcmProjectno(no);
		sqls.add(KznTlDmcfipcreationmstSql.getInsertSql(kznTlDmcfipcreationmstSql.getDmcmDbFields(), newKznTlDmcfipcreationmst.getSaveArray())); // add insert sql for master table
		
		newGenTlDmcfipworkflow.setDfiwKeyid(dbActionTemplate.getSequenceNumber(genTlDmcfipworkflowSql.TBL_GEN_TL_DMCFIPWORKFLOW, 10, "WD","YY","Y"));
		newGenTlDmcfipworkflow.setDfiwFipno(newKznTlDmcfipcreationmst.getDmcmKeyid());
		sqls.add(GenTlDmcfipworkflowSql.getInsertSql(genTlDmcfipworkflowSql.getDfiwDbFields(), newGenTlDmcfipworkflow.getSaveArray())); // add insert sql for wokflow table
		if (newKznTlDmcfipcreationmst.getProjectResourceList()!=null){
			
			GenSequenceNumber resSeq = new GenSequenceNumber( dbActionTemplate.getDataSource().getConnection(), KznTlProjectResourceLinkSql.TBL_KZN_TL_PROJECT_RESOURCE_LINK,15,"KPR","YYMMDD","Y") ;
			for(int i=0;i<newKznTlDmcfipcreationmst.getProjectResourceList().size();i++){
				
				KznTlProjectResourceLink kznTlProjectResourceLink=newKznTlDmcfipcreationmst.getProjectResourceList().get(i);
		//		CommonMessage.debugMsg("getKprlEmpmKeyid:"+ i+ kznTlProjectResourceLink.getKprlEmpmKeyid());
				kznTlProjectResourceLink.setKprlKzpmKeyid(newKznTlDmcfipcreationmst.getDmcmKeyid());
				kznTlProjectResourceLink.setKprlKeyid(resSeq.getSequnceNumber()); // set the sequnce number
			    sqls.add(KznTlProjectResourceLinkSql.getInsertSql(kznTlProjectResourceLinkSql.getKprlDbFields(), kznTlProjectResourceLink.getSaveArray()));
			}
			resSeq.closeConnection();
			resSeq =null;
		}
		if (newKznTlDmcfipcreationmst.getWorkFlowApp()!=null){
			GenTlWorkflowInfo genTlWorkflowInfo =newKznTlDmcfipcreationmst.getWorkFlowApp();
			genTlWorkflowInfo.setWrinRefId(newKznTlDmcfipcreationmst.getDmcmKeyid());
			genTlWorkflowInfo.setWrinWrmlKeyid(newGenTlDmcfipworkflow.getDfiwKeyid());
			//genTlWorkflowInfo.setWrinRefType("DMCFIP");
			genTlWorkflowInfo.setWrinWrkdKeyid("DMCPL");
			
			try{
				String projectLeadId = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_ROLEMST, "ROLE_KEYID", "UPPER(COALESCE(ROLE_NAME, ' '))", "PROJECTLEAD");
				genTlWorkflowInfo.setWrinRoleId(projectLeadId);
			}catch(Exception e){
				genTlWorkflowInfo.setWrinRoleId("AROL0059");
			}
			genTlWorkflowInfo.setWrinKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_GEN_TL_WORKFLOW_INFO, 10, "WF", null,null));
			sqls.add(genTlWorkflowInfoSql.getInsertSql(genTlWorkflowInfoSql.getWrinDbFields(), genTlWorkflowInfo.getSaveArray()));
		}
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		return newKznTlDmcfipcreationmst;
			
	}
	@Override
	public KznTlDmcfipcreationmst updatedmc(KznTlDmcfipcreationmst newKznTlDmcfipcreationmst,GenTlDmcfipworkflow newGenTlDmcfipworkflow)throws ValidationExceptions,BusinessApplicationExceptions,Exception  {
		List<String> sqls = new ArrayList<String>();
		KznTlDmcfipcreationmstSql kznTlDmcfipcreationmstSql = new KznTlDmcfipcreationmstSql();
		GenTlDmcfipworkflowSql genTlDmcfipworkflowSql=new GenTlDmcfipworkflowSql();
		
		
		sqls.add(KznTlDmcfipcreationmstSql.getUpdateSql(kznTlDmcfipcreationmstSql.getDmcmDbFields(), newKznTlDmcfipcreationmst.getSaveArray()));
		sqls.add(GenTlDmcfipworkflowSql.getUpdateSql(genTlDmcfipworkflowSql.getDfiwDbFields(), newGenTlDmcfipworkflow.getSaveArray())); // add insert sql for wokflow table
		if("define".equalsIgnoreCase(newKznTlDmcfipcreationmst.getMode()) )
			sqls.add(GenTlWorkflowInfoSql.getdmcStatusUpdateSql(newKznTlDmcfipcreationmst.getDmcmKeyid(),newKznTlDmcfipcreationmst.getDmcmCreatedby() ));
		dbActionTemplate.executeStatements(sqls);			
		return newKznTlDmcfipcreationmst;
		
	}
	@Override
	public KznTlProjectcreationmst delete(KznTlProjectcreationmst kznTlProjectcreationmst)throws Exception {
		List<String> sqls = new ArrayList<String>();
		String newKznTlKkprojectprioritymst=null;
		String Fipkzndtldelete=dbActionTemplate.getSingleValue("KZN_TL_KKPROJECTPRIORITYMST", "KPPM_KEYID", "KPPM_KZPM_KEYID", kznTlProjectcreationmst.getKzpmKeyid());
		
	 //String Fipmomdelete=dbActionTemplate.getSingleValue("KZN_TL_PROJ_MILESTONE_MST", "KMMM_KEYID","KMMM_KZPM_KEYID", kznTlProjectcreationmst.getKzpmKeyid());
	  // String Fipmomdelete1=dbActionTemplate.getSingleValue("KZN_TL_PROJ_MILESTONE_DTL", "KMM_KEYID", "KMMD_KMMM_KEYID",Fipmomdelete);
		
		
	/*	List<String[]> dataContent=new ArrayList<String[]>();
		String Sqldata="";
		Sqldata+="SELECT KMMM_KEYID FROM KZN_TL_PROJ_MILESTONE_MST WHERE KMMM_KZPM_KEYID ='"+kznTlProjectcreationmst.getKzpmKeyid()+"'";
		ResultSet rs;
		rs=dbActionTemplate.getData(Sqldata.toString());
		//CommonMessage.debugMsg("SQL Data"+rs.getString(0));
		CommonMessage.debugMsg("The Result set"+rs.getString("KMMM_KEYID"));
		//dataContent=dbActionTemplate.getDataList(Sqldata);
		CommonMessage.debugMsg("List Of Key1"+dataContent);
		CommonMessage.debugMsg("List Of Key2"+dataContent.toString());   */
		
		
		
		
		//StringBuilder sb=new StringBuilder();
		//String sqlsda="";
		//sb.append("SELECT KMMM_KEYID FROM KZN_TL_PROJ_MILESTONE_MST WHERE KMMM_KZPM_KEYID ='"+kznTlProjectcreationmst.getKzpmKeyid()+"' ");
		//sqlsda=sb.toString();
		//CommonMessage.debugMsg("SQLDATA"+sqlsda);
		
	//	CommonMessage.debugMsg("The Total Keyid"+dataContent);
	//	List<String> listmom=new ArrayList<String>();
	//	CommonMessage.debugMsg("listy---"+dataContent.get(1));
		
		//listmom.add(kznTlProjectcreationmstSql.getallMom(kznTlProjectcreationmst.getKzpmKeyid()));		
		//CommonMessage.debugMsg("The List Mom"+listmom);
		//listmom.addAll(dataContent);
		//String momkeyid=dataContent.toString();
		//String val=dbActionTemplate.executeStatements(listmom);
		
	/*	for(int i=0;i<dataContent.size();i++)
		{
			String key=dataContent.toString();
			CommonMessage.debugMsg("kmmdkeyid::"+dataContent.get(i).toString());
			sqls.add(kznTlProjectcreationmstSql.getDeleteMilestoneDetail(dataContent.get(1).toString()));	
		} */

		String Fipmomdelete;
        Fipmomdelete=dbActionTemplate.getSingleValue("KZN_TL_PROJ_MILESTONE_MST","KMMM_KEYID","KMMM_KZPM_KEYID", kznTlProjectcreationmst.getKzpmKeyid());
		String Fipactionplandelete=dbActionTemplate.getSingleValue("GEN_TL_ACTIONPLANMST","APLM_KEYID", "APLM_DETAILREFID",kznTlProjectcreationmst.getKzpmKeyid());
		String Fipactionplandelete1=dbActionTemplate.getSingleValue("GEN_TL_ACTIONPLANDTL","APLD_KEYID", "APLD_APLM_KEYID",Fipactionplandelete);
		KznTlProjectcreationmstSql kznTlProjectcreationmstSql = new KznTlProjectcreationmstSql(); 
		KznTlKkprojectprioritydtl kznTlKkprojectprioritydtl =new KznTlKkprojectprioritydtl();

		sqls.add(kznTlProjectcreationmstSql.getDeleteKpi(kznTlProjectcreationmst.getKzpmKeyid()));
		//sqls.add(kznTlProjectcreationmstSql.getDeleteActionPlandtl(kznTlProjectcreationmst.getKzpmKeyid()));
		sqls.add(kznTlProjectcreationmstSql.getDeleteActionPlandtl(Fipactionplandelete1));
		sqls.add(kznTlProjectcreationmstSql.getDeleteActionPlan(Fipactionplandelete));
		//sqls.add(kznTlProjectcreationmstSql.getDeleteMilestoneDetail(kznTlProjectcreationmst.getKzpmKeyid()));
		sqls.add(kznTlProjectcreationmstSql.getDeleteMilestoneDetail(Fipmomdelete));
		//sqls.add(kznTlProjectcreationmstSql.getDeleteMilestoneDetail(kznTlProjectcreationmst.getKzpmKeyid()));
		sqls.add(kznTlProjectcreationmstSql.getDeleteMilestoneMaster(kznTlProjectcreationmst.getKzpmKeyid()));
		sqls.add(kznTlProjectcreationmstSql.getDeleteResource(kznTlProjectcreationmst.getKzpmKeyid()));
		sqls.add(kznTlProjectcreationmstSql.getDeleteKaizen(kznTlProjectcreationmst.getKzpmKeyid()));
		sqls.add(kznTlProjectcreationmstSql.getDeleteKaizendtl(Fipkzndtldelete));
		sqls.add(kznTlProjectcreationmstSql.getDeleteKK(kznTlProjectcreationmst.getKzpmKeyid()));
		sqls.add(kznTlProjectcreationmstSql.getchecklistlink(kznTlProjectcreationmst.getKzpmKeyid()));
		sqls.add(kznTlProjectcreationmstSql.getDeleteSql(kznTlProjectcreationmstSql.getKzpmDbFields(), kznTlProjectcreationmst.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		return kznTlProjectcreationmst;
	} 
	@Override
	public KznTlDmcfipcreationmst dmcdelete(KznTlDmcfipcreationmst newKznTlDmcfipcreationmst,GenTlDmcfipworkflow newGenTlDmcfipworkflow)throws Exception {
		List<String> sqls = new ArrayList<String>();
		String newKznTlKkprojectprioritymst=null;
		String Fipkzndtldelete=dbActionTemplate.getSingleValue("KZN_TL_KKPROJECTPRIORITYMST", "KPPM_KEYID", "KPPM_KZPM_KEYID", newKznTlDmcfipcreationmst.getDmcmKeyid());
		
	 //String Fipmomdelete=dbActionTemplate.getSingleValue("KZN_TL_PROJ_MILESTONE_MST", "KMMM_KEYID","KMMM_KZPM_KEYID", kznTlProjectcreationmst.getKzpmKeyid());
	  // String Fipmomdelete1=dbActionTemplate.getSingleValue("KZN_TL_PROJ_MILESTONE_DTL", "KMM_KEYID", "KMMD_KMMM_KEYID",Fipmomdelete);
		
		
	/*	List<String[]> dataContent=new ArrayList<String[]>();
		String Sqldata="";
		Sqldata+="SELECT KMMM_KEYID FROM KZN_TL_PROJ_MILESTONE_MST WHERE KMMM_KZPM_KEYID ='"+kznTlProjectcreationmst.getKzpmKeyid()+"'";
		ResultSet rs;
		rs=dbActionTemplate.getData(Sqldata.toString());
		//CommonMessage.debugMsg("SQL Data"+rs.getString(0));
		CommonMessage.debugMsg("The Result set"+rs.getString("KMMM_KEYID"));
		//dataContent=dbActionTemplate.getDataList(Sqldata);
		CommonMessage.debugMsg("List Of Key1"+dataContent);
		CommonMessage.debugMsg("List Of Key2"+dataContent.toString());   */
		
		
		
		
		//StringBuilder sb=new StringBuilder();
		//String sqlsda="";
		//sb.append("SELECT KMMM_KEYID FROM KZN_TL_PROJ_MILESTONE_MST WHERE KMMM_KZPM_KEYID ='"+kznTlProjectcreationmst.getKzpmKeyid()+"' ");
		//sqlsda=sb.toString();
		//CommonMessage.debugMsg("SQLDATA"+sqlsda);
		
	//	CommonMessage.debugMsg("The Total Keyid"+dataContent);
	//	List<String> listmom=new ArrayList<String>();
	//	CommonMessage.debugMsg("listy---"+dataContent.get(1));
		
		//listmom.add(kznTlProjectcreationmstSql.getallMom(kznTlProjectcreationmst.getKzpmKeyid()));		
		//CommonMessage.debugMsg("The List Mom"+listmom);
		//listmom.addAll(dataContent);
		//String momkeyid=dataContent.toString();
		//String val=dbActionTemplate.executeStatements(listmom);
		
	/*	for(int i=0;i<dataContent.size();i++)
		{
			String key=dataContent.toString();
			CommonMessage.debugMsg("kmmdkeyid::"+dataContent.get(i).toString());
			sqls.add(kznTlProjectcreationmstSql.getDeleteMilestoneDetail(dataContent.get(1).toString()));	
		} */

		String Fipmomdelete;
        Fipmomdelete=dbActionTemplate.getSingleValue("KZN_TL_PROJ_MILESTONE_MST","KMMM_KEYID","KMMM_KZPM_KEYID", newKznTlDmcfipcreationmst.getDmcmKeyid());
		String Fipactionplandelete=dbActionTemplate.getSingleValue("GEN_TL_ACTIONPLANMST","APLM_KEYID", "APLM_DETAILREFID",newKznTlDmcfipcreationmst.getDmcmKeyid());
		String Fipactionplandelete1=dbActionTemplate.getSingleValue("GEN_TL_ACTIONPLANDTL","APLD_KEYID", "APLD_APLM_KEYID",Fipactionplandelete);
		KznTlDmcfipcreationmstSql kznTlDmcfipcreationmstSql = new KznTlDmcfipcreationmstSql(); 
		KznTlKkprojectprioritydtl kznTlKkprojectprioritydtl =new KznTlKkprojectprioritydtl();
		GenTlDmcfipworkflowSql genTlDmcfipworkflowSql =new GenTlDmcfipworkflowSql();
		
		sqls.add(genTlDmcfipworkflowSql.getDeleteSql(genTlDmcfipworkflowSql.getDfiwDbFields(), newGenTlDmcfipworkflow.getSaveArray()));
		
		sqls.add(kznTlDmcfipcreationmstSql.getDeleteKpi(newKznTlDmcfipcreationmst.getDmcmKeyid()));
		//sqls.add(kznTlDmcfipcreationmstSql.getDeleteActionPlandtl(kznTlProjectcreationmst.getDmcmKeyid()));
		sqls.add(kznTlDmcfipcreationmstSql.getDeleteActionPlandtl(Fipactionplandelete1));
		sqls.add(kznTlDmcfipcreationmstSql.getDeleteActionPlan(Fipactionplandelete));
		//sqls.add(kznTlDmcfipcreationmstSql.getDeleteMilestoneDetail(kznTlProjectcreationmst.getDmcmKeyid()));
		sqls.add(kznTlDmcfipcreationmstSql.getDeleteMilestoneDetail(Fipmomdelete));
		//sqls.add(kznTlDmcfipcreationmstSql.getDeleteMilestoneDetail(kznTlProjectcreationmst.getDmcmKeyid()));
		sqls.add(kznTlDmcfipcreationmstSql.getDeleteMilestoneMaster(newKznTlDmcfipcreationmst.getDmcmKeyid()));
		sqls.add(kznTlDmcfipcreationmstSql.getDeleteResource(newKznTlDmcfipcreationmst.getDmcmKeyid()));
		sqls.add(kznTlDmcfipcreationmstSql.getDeleteKaizen(newKznTlDmcfipcreationmst.getDmcmKeyid()));
		sqls.add(kznTlDmcfipcreationmstSql.getDeleteKaizendtl(Fipkzndtldelete));
		sqls.add(kznTlDmcfipcreationmstSql.getDeleteKK(newKznTlDmcfipcreationmst.getDmcmKeyid()));
		sqls.add(kznTlDmcfipcreationmstSql.getchecklistlink(newKznTlDmcfipcreationmst.getDmcmKeyid()));
		sqls.add(kznTlDmcfipcreationmstSql.getDeleteSql(kznTlDmcfipcreationmstSql.getDmcmDbFields(), newKznTlDmcfipcreationmst.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		return newKznTlDmcfipcreationmst;
	} 
	@Override
	/**/
	public String singleResponsiblty(String Projectkeyid, String responsevalue,String oldemployeeid) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		List<String> sqls2 = new ArrayList<String>();
		StringBuffer sql1 = new StringBuffer();

		
		if(Projectkeyid!=null&&Projectkeyid.length()!=0)
		{
			sql1.append(" UPDATE KZN_TL_PROJECTCREATIONMST SET KZPM_oldresponsibility ='"+oldemployeeid+"'");
			sql1.append(",KZPM_PROJECTCHAMP='"+responsevalue+"'"+"WHERE KZPM_KEYID='"+Projectkeyid+"'");
	
			sqls2.add(sql1.toString());
			
			 CommonMessage.debugMsg("SQLS::"+sqls2);
			dbActionTemplate.executeStatements(sqls2);
			
		}
		return "SUCCESS";
	}
	/**/
/**/
	
	public List<String[]> getChangeproleader(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub		
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			CommonMessage.debugMsg(commonFilter.getType() + " commonFilter.getType() ");
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms+="ISCLOSURE="+commonFilter.getKey()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getType())){
				condParms+="STAGE="+commonFilter.getType()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getAtype())){
				condParms+="APPROVAL="+commonFilter.getAtype()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
				condParms=condParms+"FLID="+commonFilter.getFlid()+";";
			}	
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZEN.JHN_FN_CHANGEPROLEAD",paramValues);
			 //CommonMessage.debugMsg("commingggggggggggg");
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	public List<String[]> getgetprojectleadD(String Keyid) throws Exception
	{
		try
		{
		 	String sql ="";
		 	StringBuffer buffer = new StringBuffer();
		 	buffer.append (" select kzpm_projectchamp from kzn_tl_projectcreationmst where kzpm_keyid='"+Keyid+"'");

		 	sql = buffer.toString();
		 	CommonMessage.debugMsg("sqllll"+sql);
			return dbActionTemplate.getDataList(sql);			 
		}
		catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
	}
	public String singleleadResponsiblty(String Projectkeyid, String responsevalue,String oldemployeeid) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		List<String> sqls2 = new ArrayList<String>();
		StringBuffer sql1 = new StringBuffer();

		
		if(Projectkeyid!=null&&Projectkeyid.length()!=0)
		{
			sql1.append(" UPDATE KZN_TL_PROJECTCREATIONMST SET KZPM_oldresponsibility ='"+oldemployeeid+"'");
			sql1.append(",KZPM_CREATEDBY='"+responsevalue+"'"+"WHERE KZPM_KEYID='"+Projectkeyid+"'");
	
			sqls2.add(sql1.toString());
			
			 CommonMessage.debugMsg("SQLS::"+sqls2);
			dbActionTemplate.executeStatements(sqls2);
			
		}
		return "SUCCESS";
	}
	public Workbook getChangeproleaderExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		 ResultSet rs = null;
		 CommonMessage.debugMsg("excel");
			// TODO Auto-generated method stub
			 try{
					
				 rs =   getChangeproleadResultSet(commonFilter);
				 
				 ExcelUtils excelUtils = new ExcelUtils(colmodel);
					return excelUtils.writeToExcel(rs,format, 2,0,0 );
					
				   }finally{
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				   }
	}
	
	


	private ResultSet getChangeproleaderResultSet(CommonFilter commonFilter) throws Exception{
		List<String> paramValues = new ArrayList<String>();
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String condParms =  FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		/*if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms=condParms+"ISCLOSURE="+commonFilter.getKey()+";";
		}*/
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			
			condParms=condParms+"FLID="+commonFilter.getFlid()+";";
		}	
		CommonMessage.debugMsg("condParms");
		 paramValues.add(condParms);
		 paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("KZN_PC_KAIZEN.JHN_FN_CHANGEPROLEAD", paramValues);
	}
	/***/
	public KznTlProjectcreationmst getRecall(String keyid) throws Exception {
		try
		{
			KznTlProjectcreationmstSql newKznTlProjectcreationmstSql = new KznTlProjectcreationmstSql();
			KznTlProjectcreationmst newKznTlProjectcreationmst = new KznTlProjectcreationmst();
			String sql = newKznTlProjectcreationmstSql.getRecall();
			CommonMessage.debugMsg("DAO SQL : "+sql);
			Object [] args =  new Object [] { keyid };
			CommonMessage.debugMsg("keyid:::::"+keyid);
			newKznTlProjectcreationmst.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
			CommonMessage.debugMsg(sql);
			CommonMessage.debugMsg(keyid);
			return  newKznTlProjectcreationmst;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
	}
	
	public KznTlDmcfipcreationmst getdmcRecall(String keyid) throws Exception {
		try
		{
			KznTlDmcfipcreationmstSql newKznTlDmcfipcreationmstSql = new KznTlDmcfipcreationmstSql();
			KznTlDmcfipcreationmst newKznTlDmcfipcreationmst = new KznTlDmcfipcreationmst();
			String sql = newKznTlDmcfipcreationmstSql.getdmcRecall();
			CommonMessage.debugMsg("DAO SQL : "+sql);
			Object [] args =  new Object [] { keyid };
			CommonMessage.debugMsg("keyid:::::"+keyid);
			newKznTlDmcfipcreationmst.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
			CommonMessage.debugMsg(sql);
			CommonMessage.debugMsg(keyid);
			return  newKznTlDmcfipcreationmst;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
	}
	public GenTlDmcfipworkflow getwrkflpRecall(String DfiwkeyId) throws Exception {
		try
		{
			GenTlDmcfipworkflowSql newGenTlDmcfipworkflowSql=new GenTlDmcfipworkflowSql();
			GenTlDmcfipworkflow newGenTlDmcfipworkflow = new GenTlDmcfipworkflow();
			String sql = newGenTlDmcfipworkflowSql.getwrkflpRecall();
			CommonMessage.debugMsg("DAO SQL : "+sql);
			Object [] args =  new Object [] { DfiwkeyId };
			CommonMessage.debugMsg("keyid:::::"+DfiwkeyId);
			newGenTlDmcfipworkflow.setSaveArray( dbActionTemplate.getDataArr(sql,args ));
			CommonMessage.debugMsg("savearray="+newGenTlDmcfipworkflow.getSaveArray());
			CommonMessage.debugMsg(sql);
			CommonMessage.debugMsg(DfiwkeyId);
			return  newGenTlDmcfipworkflow;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
	}
	public GenTlDmcfipworkflow getdmcwrkflpRecall(String keyId) throws Exception {
		try
		{
			GenTlDmcfipworkflowSql newGenTlDmcfipworkflowSql=new GenTlDmcfipworkflowSql();
			GenTlDmcfipworkflow newGenTlDmcfipworkflow = new GenTlDmcfipworkflow();
			String sql = newGenTlDmcfipworkflowSql.getdmcwrkflpRecall();
			CommonMessage.debugMsg("DAO SQL : "+sql);
			Object [] args =  new Object [] { keyId };
			CommonMessage.debugMsg("keyid:::::"+keyId);
			newGenTlDmcfipworkflow.setSaveArray( dbActionTemplate.getDataArr(sql,args ));
			CommonMessage.debugMsg("savearray="+newGenTlDmcfipworkflow.getSaveArray());
			CommonMessage.debugMsg(sql);
			CommonMessage.debugMsg(keyId);
			return  newGenTlDmcfipworkflow;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
	}
	@Override
	public List<String[]> getListOfIndicators(CommonFilter commonFilter) throws Exception {
		/*List<String > paramValues = new ArrayList<String>();
		paramValues.add(commonFilter.getKey());	
		paramValues.add(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
		CommonMessage.debugMsg("ParamValues:"+paramValues);	
		List<String[]> indicatorList;	
		indicatorList = dbActionTemplate.processFunctionCalls("KZN_PC_KAIZEN.KPI_FN_BMINDICATORPOPUP", paramValues);
		CommonMessage.debugMsg(" indicatorList size " + indicatorList.size());
		return indicatorList;*/
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms=condParms+"KZPMKEYID="+commonFilter.getKey()+";";
		}	
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		//CommonMessage.debugMsg("KZN_PC_KAIZEN.KZN_FN_PROJECTKPILIST....");
		//dataList =  dbActionTemplate.processFunctionCalls("KZN_FN_PROJECTKPILIST", paramValues);
		dataList =  fnCallApi.callFunction("KZN_FN_PROJECTKPILIST_SB", paramValues, 3, true);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			//CommonMessage.debugMsg("totalCnt....."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}
	@Override
	public KznTlProjectKpiLink createKpi(List<KznTlProjectKpiLink> kznTlProjectKpiLinkList)throws Exception{
		List<String> sqls = new ArrayList<String>();
		KznTlProjectKpiLink	newkznTlProjectKpiLink= new KznTlProjectKpiLink();
		KznTlProjectKpiLinkSql newKznTlProjectKpiLinkSql = new KznTlProjectKpiLinkSql();
		
			
		if(kznTlProjectKpiLinkList!=null){
			//sqls.add(newKznTlProjectKpiLinkSql.getDeleteAllSql( newKznTlProjectKpiLink.getProjectKpi().get(0).getKpklKzpmKeyid()));
			GenSequenceNumber qSeq = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(),KznTlProjectKpiLinkSql.TBL_KZN_TL_PROJECT_KPI_LINK,15,"KPK","DDMMYY","Y");
			for(int i=0;i<kznTlProjectKpiLinkList.size();i++){
					
			    KznTlProjectKpiLink	kznTlProjectKpiLink= kznTlProjectKpiLinkList.get(i);
			    if(kznTlProjectKpiLink.getKpklKeyid() ==null ){
					kznTlProjectKpiLink.setKpklKeyid(qSeq.getSequnceNumber()); // set the sequnce number
					sqls.add(KznTlProjectKpiLinkSql.getInsertSql(newKznTlProjectKpiLinkSql.getKpklDbFields(), kznTlProjectKpiLink.getSaveArray()));
				}
				else{
					if (CommonFunctions.isValidKeyId(kznTlProjectKpiLink.getIsDelete())){		
						if (kznTlProjectKpiLink.getIsDelete().equals("Y")){								
							sqls.add(KznTlProjectKpiLinkSql.getDeleteSql(newKznTlProjectKpiLinkSql.getKpklDbFields(), kznTlProjectKpiLink.getSaveArray()));								
						}
						else{
							sqls.add(KznTlProjectKpiLinkSql.getUpdateSql(newKznTlProjectKpiLinkSql.getKpklDbFields(), kznTlProjectKpiLink.getSaveArray()));
						}
					}
				}					
			}
			qSeq.closeConnection();
			qSeq =null;
		}
		CommonMessage.debugMsg("before Execute");
		dbActionTemplate.executeStatements(sqls);
		CommonMessage.debugMsg("after Execute");
		return newkznTlProjectKpiLink;
	
	}
	
	@Override
	public KznTlProjectResourceLink create(KznTlProjectResourceLink newKznTlProjectResourceLink)throws Exception {
		List<String> sqls = new ArrayList<String>();
		try{
			KznTlProjectResourceLinkSql newKznTlProjectResourceLinkSql = new KznTlProjectResourceLinkSql();
			newKznTlProjectResourceLink.setKprlKeyid(dbActionTemplate.getSequenceNumber(KznTlProjectResourceLinkSql.TBL_KZN_TL_PROJECT_RESOURCE_LINK,15,"KPR","DDMMYY","Y")); // set the sequnce number
		    sqls.add(KznTlProjectResourceLinkSql.getInsertSql(newKznTlProjectResourceLinkSql.getKprlDbFields(), newKznTlProjectResourceLink.getSaveArray()));						
			CommonMessage.debugMsg("before Execute");
			dbActionTemplate.executeStatements(sqls);
			CommonMessage.debugMsg("after Execute");
		}catch(Exception e){
			CommonMessage.debugMsg(" error  "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public KznTlProjectResourceLink delete(KznTlProjectResourceLink newKznTlProjectResourceLink)throws Exception {
		List<String> sqls = new ArrayList<String>();
		KznTlProjectResourceLinkSql newKznTlProjectResourceLinkSql = new KznTlProjectResourceLinkSql();
		sqls.add(KznTlProjectResourceLinkSql.getDeleteSql(newKznTlProjectResourceLinkSql.getKprlDbFields(), newKznTlProjectResourceLink.getSaveArray()));			
		dbActionTemplate.executeStatements(sqls);

		return newKznTlProjectResourceLink;
	}
	@Override
	public List<String[]> getAllMilestones(CommonFilter commonFilter)throws Exception {
		
		String sql = KznTlProjectmaicMileMstSql.selectMilestonesSql(commonFilter);
		CommonMessage.debugMsg("mile stone grid "+sql);				
		return dbActionTemplate.getDataList(sql);			
		
		
	}
	@Override
	public KznTlProjectmaicMileMst create(KznTlProjectmaicMileMst newKznTlProjectmaicMileMst) throws Exception {
		List<String> sqls = new ArrayList<String>();
		try{
			
			KznTlProjectmaicMileMstSql newKznTlProjectmaicMileMstSql = new KznTlProjectmaicMileMstSql();
			KznTlProjectmaicMileDtlSql newKznTlProjectmaicMileDtlSql = new KznTlProjectmaicMileDtlSql();
			KznTlProjectmaicMileDtl newKznTlProjectmaicMileDtl = new KznTlProjectmaicMileDtl();
			if(newKznTlProjectmaicMileMst.getKmmmKeyid()==null){
				newKznTlProjectmaicMileMst.setKmmmKeyid(dbActionTemplate.getSequenceNumber(KznTlProjectmaicMileMstSql.TBL_KZN_TL_PROJECTMAIC_MILE_MST,15,"KMM","DDMMYY","Y")); // set the sequnce number
				sqls.add(KznTlProjectmaicMileMstSql.getInsertSql(newKznTlProjectmaicMileMstSql.getKmmmDbFields(), newKznTlProjectmaicMileMst.getSaveArray()));
			}else{
				sqls.add(KznTlProjectmaicMileMstSql.getUpdateSql(newKznTlProjectmaicMileMstSql.getKmmmDbFields(), newKznTlProjectmaicMileMst.getSaveArray()));
			}
			if(newKznTlProjectmaicMileMst.getMilestonedetail()!=null){
				for(int i=0;i<newKznTlProjectmaicMileMst.getMilestonedetail().size();i++){
					newKznTlProjectmaicMileDtl = newKznTlProjectmaicMileMst.getMilestonedetail().get(i);
					fillvalues(newKznTlProjectmaicMileDtl,newKznTlProjectmaicMileMst);
					if(newKznTlProjectmaicMileDtl.getKmmdKeyid()==null){
						newKznTlProjectmaicMileDtl.setKmmdKeyid(dbActionTemplate.getSequenceNumber(KznTlProjectmaicMileDtlSql.TBL_KZN_TL_PROJECTMAIC_MILE_DTL,15,"KMD","DDMMYY","Y")); // set the sequnce number
						sqls.add(KznTlProjectmaicMileDtlSql.getInsertSql(newKznTlProjectmaicMileDtlSql.getKmmdDbFields(), newKznTlProjectmaicMileDtl.getSaveArray()));
					}else{
						
						//CHECK CHANGES IN MAJOR FILEDS
						String existingdate = "Select to_char(KMMD_TARGETDATE,'dd-MON-YYYY') from KZN_TL_PROJ_MILESTONE_DTL where kmmd_keyid ='"+newKznTlProjectmaicMileDtl.getKmmdKeyid()+"'";
						String existDate = dbActionTemplate.getSingleValue(existingdate);
						CommonMessage.debugMsg("valuesddddd grid "+existDate+"   "+newKznTlProjectmaicMileDtl.getKmmdTargetdate());
						if(!newKznTlProjectmaicMileDtl.getKmmdTargetdate().equals(existDate)) {
							StringBuilder st1=new StringBuilder(); 
							st1.append( " INSERT INTO Kzn_Tl_PROJ_MILESTONE_DTL_HIS ");
							st1.append( " SELECT  ");
							st1.append( " KMMD_KEYID,KMMD_KMMM_KEYID,KMMD_MILESTONE,KMMD_DESCRIPTION,KMMD_TARGETDATE,");
							st1.append( " KMMD_EMPM_KEYID,KMMD_STATUS,KMMD_REMARKS,KMMD_TEMPFIELD1,KMMD_TEMPFIELD2,");
							st1.append( " KMMD_TEMPFIELD3,KMMD_TEMPFIELD4,KMMD_TEMPFIELD5,KMMD_CREATEDBY,KMMD_ACTIVE,KMMD_CREATEDON,KMMD_MODIFIEDON FROM Kzn_Tl_PROJ_MILESTONE_DTL ");
							st1.append( " WHERE KMMD_KEYID ='"+newKznTlProjectmaicMileDtl.getKmmdKeyid()+"'");
							sqls.add(st1.toString());
						}
						sqls.add(KznTlProjectmaicMileDtlSql.getUpdateSql(newKznTlProjectmaicMileDtlSql.getKmmdDbFields(), newKznTlProjectmaicMileDtl.getSaveArray()));
						sqls.add(KznTlProjectmaicMileDtlSql.getUpdateMaster(newKznTlProjectmaicMileMst.getKmmmKeyid()));
						
					}
				}
			
			}
			dbActionTemplate.executeStatements(sqls);
			UpdateMaicStatus(newKznTlProjectmaicMileMst.getKmmmKzpmKeyid(),newKznTlProjectmaicMileMst.getKmmmStages(),"MileStone");
			return newKznTlProjectmaicMileMst;
		}catch(Exception e){
//			e.printStackTrace();
			throw  new Exception("Data Not Saved"); 
		}
		
	}
	
	private void UpdateMaicStatus(String kznKeyId,String maicStage,String processType) throws Exception {
		List<String> sqls = new ArrayList<String>();
		String mileStoneStatusSql = "";
		String verifiedSql="";
		String maicSql="";
		String maicStages = "";
		maicSql=KznTlProjectmaicMileMstSql.getMaicStage(kznKeyId, maicStage);
		mileStoneStatusSql=KznTlProjectmaicMileMstSql.getMileStoneStatus(kznKeyId, maicStage);
		verifiedSql=KznTlProjectdmaicstatusSql.getMaicStageSql(kznKeyId, maicStage);
		CommonMessage.debugMsg("getStatusSql: 1    "+mileStoneStatusSql);
		CommonMessage.debugMsg("getStatusSql: 2   "+verifiedSql);
		CommonMessage.debugMsg("maicSql:  3  "+maicSql);
		
		String status = dbActionTemplate.getSingleValue(maicSql);
		String mileStatus = dbActionTemplate.getSingleValue(mileStoneStatusSql);
		String verifiedStatus = dbActionTemplate.getSingleValue(verifiedSql);		
		
		if (processType.equals("MileStone")){
			maicStages=mileStatus;				
			if (status=="W" || status=="C"  ){
				maicStages=status;
			}
		}
		else if (processType.equals("Verification")){
			maicStages=verifiedStatus;
			if (status=="C"  ){
				maicStages=status;
			}
		}
		else if (processType.equals("Approval")){
			//maicStages=maicStage+"C";
			maicStages="C";
		}
		
		CommonMessage.debugMsg("maicStages:"+maicStages);
		CommonMessage.debugMsg("status:"+status);
		
		sqls.add(KznTlProjectmaicMileMstSql.getUpdateMaicStage(kznKeyId,maicStages,maicStage));
		
		dbActionTemplate.executeStatements(sqls);
	
	}
	
	private void UpdatedmcMaicStatus(String kznKeyId,String maicStage,String processType) throws Exception {
		List<String> sqls = new ArrayList<String>();
		String mileStoneStatusSql = "";
		String verifiedSql="";
		String maicSql="";
		String maicStages = "";
		maicSql=KznTlProjectmaicMileMstSql.getdmcMaicStage(kznKeyId, maicStage);
		mileStoneStatusSql=KznTlProjectmaicMileMstSql.getMileStoneStatus(kznKeyId, maicStage);
		verifiedSql=KznTlProjectdmaicstatusSql.getMaicStageSql(kznKeyId, maicStage);
		CommonMessage.debugMsg("getStatusSql:"+mileStoneStatusSql);
		CommonMessage.debugMsg("getStatusSql:"+verifiedSql);
		CommonMessage.debugMsg("maicSql:"+maicSql);
		String status = dbActionTemplate.getSingleValue(maicSql);
		String mileStatus = dbActionTemplate.getSingleValue(mileStoneStatusSql);
		String verifiedStatus = dbActionTemplate.getSingleValue(verifiedSql);		
		
		if (processType.equals("MileStone")){
			maicStages=mileStatus;				
			if (status=="W" || status=="C"  ){
				maicStages=status;
			}
		}
		else if (processType.equals("Verification")){
			maicStages=verifiedStatus;
			if (status=="C"  ){
				maicStages=status;
			}
		}
		else if (processType.equals("Approval")){
			//maicStages=maicStage+"C";
			maicStages="C";
		}
		
		CommonMessage.debugMsg("maicStages:"+maicStages);
		CommonMessage.debugMsg("status:"+status);
		
		sqls.add(KznTlProjectmaicMileMstSql.getdmcUpdateMaicStage(kznKeyId,maicStages,maicStage));
		
		dbActionTemplate.executeStatements(sqls);
	
	}
	
	@Override
	public String getMileStoneStages(String kznKeyId) {
		List<String> sqls = new ArrayList<String>();
		String stages="";
		try{		
			String sql = KznTlProjectmaicMileMstSql.getMileStoneStatus(kznKeyId);
			CommonMessage.debugMsg("sql "+sql);
			stages = dbActionTemplate.getSingleValue(sql);
			CommonMessage.debugMsg("values grid "+stages);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return stages;
	}
	
	private void fillvalues(KznTlProjectmaicMileDtl newKznTlProjectmaicMileDtl,KznTlProjectmaicMileMst newKznTlProjectmaicMileMst) {
		if(newKznTlProjectmaicMileDtl.getKmmdTempfield1()==null){
			newKznTlProjectmaicMileDtl.setKmmdTempfield1("-");
		}
		if(newKznTlProjectmaicMileDtl.getKmmdTempfield2()==null){
			newKznTlProjectmaicMileDtl.setKmmdTempfield2("-");
		}
		if(newKznTlProjectmaicMileDtl.getKmmdTempfield3()==null){
			newKznTlProjectmaicMileDtl.setKmmdTempfield3("-");
		}
		if(newKznTlProjectmaicMileDtl.getKmmdTempfield4()==null){
			newKznTlProjectmaicMileDtl.setKmmdTempfield4("-");
		}
		if(newKznTlProjectmaicMileDtl.getKmmdTempfield5()==null){
			newKznTlProjectmaicMileDtl.setKmmdTempfield5("-");
		}
		if(newKznTlProjectmaicMileDtl.getKmmdEmpmKeyid()==null){
			newKznTlProjectmaicMileDtl.setKmmdEmpmKeyid("-");
		}
		if(newKznTlProjectmaicMileDtl.getKmmdDescription()==null){
			newKznTlProjectmaicMileDtl.setKmmdDescription("-");
		}
		if(newKznTlProjectmaicMileDtl.getKmmdMilestone()==null){
			newKznTlProjectmaicMileDtl.setKmmdMilestone("-");
		}
		if(newKznTlProjectmaicMileDtl.getKmmdStatus()==null){
			newKznTlProjectmaicMileDtl.setKmmdStatus("-");
		}
		if(newKznTlProjectmaicMileDtl.getKmmdRemarks()==null){
			newKznTlProjectmaicMileDtl.setKmmdRemarks("-");
		}
		newKznTlProjectmaicMileDtl.setKmmdActive(newKznTlProjectmaicMileMst.getKmmmActive());
		newKznTlProjectmaicMileDtl.setKmmdModifiedon(newKznTlProjectmaicMileMst.getKmmmModifiedon());
		newKznTlProjectmaicMileDtl.setKmmdCreatedby(newKznTlProjectmaicMileMst.getKmmmCreatedby());
		newKznTlProjectmaicMileDtl.setKmmdCreatedon(newKznTlProjectmaicMileMst.getKmmmCreatedon());
		newKznTlProjectmaicMileDtl.setKmmdKmmmKeyid(newKznTlProjectmaicMileMst.getKmmmKeyid());		
	}
	@Override
	public KznTlProjectmaicMileMst getRecallMile(String keyid) throws Exception {
		try
		{
			KznTlProjectmaicMileMstSql newKznTlProjectmaicMileMstSql = new KznTlProjectmaicMileMstSql();
			KznTlProjectmaicMileMst newKznTlProjectmaicMileMst = new KznTlProjectmaicMileMst();
			String sql = newKznTlProjectmaicMileMstSql.getRecall();
			CommonMessage.debugMsg("DAO SQL : "+sql);
			Object [] args =  new Object [] { keyid };
			CommonMessage.debugMsg("keyid:::::"+keyid);
			newKznTlProjectmaicMileMst.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
			CommonMessage.debugMsg(sql);
			CommonMessage.debugMsg(keyid);
			return  newKznTlProjectmaicMileMst;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
	}
	@Override
	public KznTlProjectmaicMileMst delete(KznTlProjectmaicMileMst newKznTlProjectmaicMileMst)throws Exception {
		List<String> sqls = new ArrayList<String>();
		KznTlProjectmaicMileMstSql newKznTlProjectmaicMileMstSql = new KznTlProjectmaicMileMstSql();
		try {
			sqls.add(newKznTlProjectmaicMileMstSql.getDeleteDetail(newKznTlProjectmaicMileMst.getKmmmKeyid()));
			sqls.add(newKznTlProjectmaicMileMstSql.getDeleteSql(newKznTlProjectmaicMileMstSql.getKmmmDbFields(), newKznTlProjectmaicMileMst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return newKznTlProjectmaicMileMst;
	}
	@Override
	public void deleteMilestone(String keyid) throws Exception {
		try{
			String sql = KznTlProjectmaicMileDtlSql.deleteMilestone(keyid);
			this.dbActionTemplate.executeStatement(sql);
			}
			catch(BusinessApplicationExceptions e){
				CommonMessage.debugMsg(" Message:"+e.getMessage());
				throw new BusinessApplicationExceptions(e.getMessage());
			}
			catch( Exception e){
				throw new Exception(e.getMessage());
			}
		
	}
//	@Override
//	public List<String[]> getListOfKaizen(CommonFilter commonFilter)
//			throws Exception {
//		StringBuffer sql = new StringBuffer();
//		List<String> params = null;
////		sql.append(" Select * From(select 'hdnChkSel' hdnChkSel,1 as dataorder,'keyid' as keyid,'Is Delete' isDelete,'Functional Location' as Flid,'Kaizen No' as KaizenNo,'Kaizen Date' as kaizendate,'Done by' as doneby from dual union all ");
////		sql.append("select ' ' hdnChkSel,2 as dataorder,kplk_keyid as keyid,'' isDelete,FUNCTIONALLOC as Flid,KZNM_KEYID as KaizenNo,to_char(KZNM_DATE,'dd-Mon-YYYY') as kaizendate,EMPM_NAME as doneby FROM KZN_TL_MST,GEN_VW_FNLN, gen_tl_employeemst,KZN_TL_PROJECT_KAIZEN_LINK WHERE kznm_flid = fnln_keyid and kznm_keyid = kplk_kznm_keyid(+)  AND empm_keyid = kznm_createdby   AND kznm_flid = '");
////		sql.append(commonFilter.getFlid()+"'");
////		sql.append("and kplk_kzpm_keyid(+) ='"+commonFilter.getKey()+"' order by dataorder,keyid) ");
////		sql.append(" where 1=1");
////		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
//		sql.append(" Select * From(select 'hdnChkSel' hdnChkSel,1 as dataorder,'keyid' as keyid,'Is Delete' isDelete,'Functional Location' as Flid,'Kaizen No' as KaizenNo,'Kaizen Date' as kaizendate,'Done by' as doneby  union all ");
//		sql.append("select ' ' hdnChkSel,2 as dataorder,kplk_keyid as keyid,'' isDelete,FUNCTIONALLOC as Flid,KZNM_KEYID as KaizenNo,to_char(KZNM_DATE,'dd-Mon-YYYY') as kaizendate,EMPM_NAME as doneby FROM KZN_TL_MST JOIN GEN_VW_FNLN ON kznm_flid = fnln_keyid JOIN  gen_tl_employeemst ON empm_keyid = kznm_createdby  LEFT JOIN KZN_TL_PROJECT_KAIZEN_LINK ON kznm_keyid = kplk_kznm_keyid and kplk_kzpm_keyid ='"+commonFilter.getKey()+"' WHERE  kznm_flid = '");
//		sql.append(commonFilter.getFlid()+"'");
//		sql.append(" order by dataorder,keyid) ");
//		sql.append(" where 1=1");
//		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
//		CommonMessage.debugMsg("sql:::Kaizen...."+sql);
//		return dbActionTemplate.getDataListWithColHeader(sql.toString(),params);
//	}
	
	@Override
	public List<String[]> getListOfKaizen(CommonFilter commonFilter)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>()  ;
		
		String conditionparam ="FLID="+commonFilter.getFlid()+";PROJECTID="+commonFilter.getKey()+";";
		String commonparam = "GRIDFILTER="+FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";";
		params.add(conditionparam);
		params.add(commonparam);
		
		List<String[]> dataList = fnCallApi.callFunction("KZN_FN_PROJECTKAIZENLIST_sb", params, 1, false);
		
		String totalCnt = params.get(0); 
		//CommonMessage.debugMsg("totalCnt....."+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		if(  isInteger ){
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
		}

//		sql.append(" Select * From(select 'hdnChkSel' hdnChkSel,1 as dataorder,'keyid' as keyid,'Is Delete' isDelete,'Functional Location' as Flid,'Kaizen No' as KaizenNo,'Kaizen Date' as kaizendate,'Done by' as doneby  union all ");
//		sql.append("select ' ' hdnChkSel,2 as dataorder,kplk_keyid as keyid,'' isDelete,FUNCTIONALLOC as Flid,KZNM_KEYID as KaizenNo,to_char(KZNM_DATE,'dd-Mon-YYYY') as kaizendate,EMPM_NAME as doneby FROM KZN_TL_MST JOIN GEN_VW_FNLN ON kznm_flid = fnln_keyid JOIN  gen_tl_employeemst ON empm_keyid = kznm_createdby  LEFT JOIN KZN_TL_PROJECT_KAIZEN_LINK ON kznm_keyid = kplk_kznm_keyid and kplk_kzpm_keyid ='"+commonFilter.getKey()+"' WHERE  kznm_flid = '");
//		sql.append(commonFilter.getFlid()+"'");
//		sql.append(" order by dataorder,keyid) ");
//		sql.append(" where 1=1");
//		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
//		CommonMessage.debugMsg("sql:::Kaizen...."+sql);
//		return dbActionTemplate.getDataListWithColHeader(sql.toString(),params);
		return dataList;
	}
	@Override
	public KznTlProjectKaizenLink create(KznTlProjectKaizenLink newKznTlProjectKaizenLink) throws Exception {
		List<String> sqls = new ArrayList<String>();
		KznTlProjectKaizenLinkSql newKznTlProjectKaizenLinkSql = new KznTlProjectKaizenLinkSql();
		try{
			
			if(newKznTlProjectKaizenLink.getProjectKaizen()!=null){
				//sqls.add(KznTlProjectKaizenLinkSql.getDeleteAllSql( newKznTlProjectKaizenLink.getProjectKaizen().get(0).getKplkKzpmKeyid()));
				for(int i=0;i<newKznTlProjectKaizenLink.getProjectKaizen().size();i++){
					CommonMessage.debugMsg("before Execute           ("+ i  + ")" +newKznTlProjectKaizenLink.getProjectKaizen().size());
					
					KznTlProjectKaizenLink	kznTlProjectKaizenLink= newKznTlProjectKaizenLink.getProjectKaizen().get(i);
					kznTlProjectKaizenLink.setKplkCreatedby(newKznTlProjectKaizenLink.getKplkCreatedby());
				    fillValues(kznTlProjectKaizenLink);
					if(kznTlProjectKaizenLink.getKplkKeyid() ==null ){
						kznTlProjectKaizenLink.setKplkKeyid(dbActionTemplate.getSequenceNumber(KznTlProjectKaizenLinkSql.TBL_KZN_TL_PROJECT_KAIZEN_LINK,15,"KKL","DDMMYY","Y")); // set the sequnce number
						sqls.add(KznTlProjectKaizenLinkSql.getInsertSql(newKznTlProjectKaizenLinkSql.getKplkDbFields(), kznTlProjectKaizenLink.getSaveArray()));
					}
					else{
						if (CommonFunctions.isValidKeyId(kznTlProjectKaizenLink.getIsDelete())){		
							if (kznTlProjectKaizenLink.getIsDelete().equals("Y")){								
								sqls.add(KznTlProjectKaizenLinkSql.getDeleteSql(newKznTlProjectKaizenLinkSql.getKplkDbFields(), kznTlProjectKaizenLink.getSaveArray()));								
							}
							else{
								sqls.add(KznTlProjectKaizenLinkSql.getUpdateSql(newKznTlProjectKaizenLinkSql.getKplkDbFields(), kznTlProjectKaizenLink.getSaveArray()));
							}
						}
					}
				}
			}
			CommonMessage.debugMsg("before Execute");
			dbActionTemplate.executeStatements(sqls);
			CommonMessage.debugMsg("after Execute");
		}catch(Exception e){
			CommonMessage.debugMsg(" error  "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	private void fillValues(KznTlProjectKaizenLink newKznTlProjectKaizenLink) {
		String dateTime = CommonFunctions.dateTimeNow();
		if(newKznTlProjectKaizenLink.getKplkActive()==null){
			newKznTlProjectKaizenLink.setKplkActive("Y");
		}
		if(newKznTlProjectKaizenLink.getKplkCreatedon()==null){
			newKznTlProjectKaizenLink.setKplkCreatedon(dateTime);
		}
		if(newKznTlProjectKaizenLink.getKplkModifiedon()==null){
			newKznTlProjectKaizenLink.setKplkModifiedon(dateTime);
		}
		if(newKznTlProjectKaizenLink.getKplkTempfield1()==null){
			newKznTlProjectKaizenLink.setKplkTempfield1("-");
		}
		if(newKznTlProjectKaizenLink.getKplkTempfield2()==null){
			newKznTlProjectKaizenLink.setKplkTempfield2("-");
		}
		if(newKznTlProjectKaizenLink.getKplkTempfield3()==null){
			newKznTlProjectKaizenLink.setKplkTempfield3("-");
		}
		if(newKznTlProjectKaizenLink.getKplkTempfield4()==null){
			newKznTlProjectKaizenLink.setKplkTempfield4("-");
		}
		if(newKznTlProjectKaizenLink.getKplkTempfield5()==null){
			newKznTlProjectKaizenLink.setKplkTempfield5("-");
		}
		
	}
	@Override
	public KznTlProjectResourceLink update(KznTlProjectResourceLink newKznTlProjectResourceLink)throws Exception {
		List<String> sqls = new ArrayList<String>();
		try{
					KznTlProjectResourceLinkSql newKznTlProjectResourceLinkSql = new KznTlProjectResourceLinkSql();
				    sqls.add(KznTlProjectResourceLinkSql.getUpdateSql(newKznTlProjectResourceLinkSql.getKprlDbFields(), newKznTlProjectResourceLink.getSaveArray()));
						
			CommonMessage.debugMsg("before Execute");
			dbActionTemplate.executeStatements(sqls);
			CommonMessage.debugMsg("after Execute");
		}catch(Exception e){
			CommonMessage.debugMsg(" error  "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public KznTlProjectResourceLink getRecallResource(String keyid)throws Exception {
		KznTlProjectResourceLinkSql newKznTlProjectResourceLinkSql = new KznTlProjectResourceLinkSql();
		KznTlProjectResourceLink newKznTlProjectResourceLink = new KznTlProjectResourceLink();
		String sql = newKznTlProjectResourceLinkSql.getRecall();
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { keyid };
		CommonMessage.debugMsg("keyid:::::"+keyid);
		newKznTlProjectResourceLink.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
		CommonMessage.debugMsg(sql);
		CommonMessage.debugMsg(keyid);
		return  newKznTlProjectResourceLink;
	
	}
	@Override
	public Workbook getProjectcreationExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		 ResultSet rs = null;
		 String fiptype=commonFilter.getAbnCatch();
		 int rowStart = 3;
		 if("DMC".equals(fiptype)) {
			 rowStart = 2;
		 }
			// TODO Auto-generated method stub
			 try{
					
				 rs =   getProjectResultSet(commonFilter);
				 //CommonMessage.debugMsg("rs::::::"+rs);
				 
				 ProjectExcelTemplete excelUtils = new ProjectExcelTemplete(colmodel);
					return excelUtils.writeToExcel(rs,format, rowStart,0,0 );
					
				   }finally{
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				   }
	}
	private ResultSet getProjectResultSet(CommonFilter commonFilter) throws Exception{
		List<String> paramValues = new ArrayList<String>();
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String type=commonFilter.getTC();
		CommonMessage.debugMsg("type:::"+type);
		String fiptype=commonFilter.getAbnCatch();
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms=condParms+"ISCLOSURE="+commonFilter.getKey()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms=condParms+"FLID="+commonFilter.getFlid()+";";
		}
	
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms+="ISCLOSURE="+commonFilter.getKey()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms+="STAGE="+commonFilter.getType()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getAtype())){
			condParms+="APPROVAL="+commonFilter.getAtype()+";";
		}
		
	   if(commonFilter.getEmpch()!=null)
	   {
			condParms+="EMPNO="+commonFilter.getEmpch() +";";
	   }
		 paramValues.add(condParms);
		 paramValues.add(commonParams);
		// CommonMessage.debugMsg("type::"+type);
		/* if(type.equals("view"))
		 {
		      return dbActionTemplate.dbFunctionCall("KZN_PC_KAIZEN.JHN_FN_PROJECTCREATIONMST", paramValues);
		 }
		 else{*/
		if("DMC".equals(fiptype))
		 {
			 
			 return dbActionTemplate.NewdbFunctionCall2("JHN_FN_DMCPROJECTCREATIONMST", paramValues); 
			 
		 }
		 else if ("view".equals(type)){
			 return dbActionTemplate.NewdbFunctionCall2("JHN_FN_PROJECTVIEWMST_SB", paramValues); 
		 }else{
			 return dbActionTemplate.NewdbFunctionCall2("JHN_FN_PROJECTCREATIONMST_SB", paramValues); 
		 }
			
		 //}
	}
	
	public Workbook getdmcProjectviewExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		 ResultSet rs = null;
			// TODO Auto-generated method stub
			 try{
					
				 rs =   getdmcProjectResultSet(commonFilter);
				 CommonMessage.debugMsg("rs::::::"+rs);
				 
				 ExcelUtils excelUtils = new ExcelUtils(colmodel);
					return excelUtils.writeToExcel(rs,format, 2,0,0 );
					
				   }finally{
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				   }
	}
	private ResultSet getdmcProjectResultSet(CommonFilter commonFilter) throws Exception{
		List<String> paramValues = new ArrayList<String>();
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String type=commonFilter.getTC();
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms=condParms+"ISCLOSURE="+commonFilter.getKey()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms=condParms+"FLID="+commonFilter.getFlid()+";";
		}
		
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms+="ISCLOSURE="+commonFilter.getKey()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms+="STAGE="+commonFilter.getType()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getAtype())){
			condParms+="APPROVAL="+commonFilter.getAtype()+";";
		}
		
	 paramValues.add(condParms);
		 paramValues.add(commonParams);
		// CommonMessage.debugMsg("type::"+type);
		/* if(type.equals("view"))
		 {
		      return dbActionTemplate.dbFunctionCall("KZN_PC_KAIZEN.JHN_FN_PROJECTCREATIONMST", paramValues);JHN_FN_DMCPROJECTCREATIONVIEW
		 }
		 else{*/
		 CommonMessage.debugMsg("paramValues"+paramValues);
			 return dbActionTemplate.NewdbFunctionCall2("JHN_FN_DMCPROJECTCREATIONVIEW", paramValues);
		 //}
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
}
	public List<String[]> getAllHistory(String dtlId) throws Exception
	{
		 String sql = KznTlProjectmaicMileDtlSql.selectHistorySql(dtlId);
		 return dbActionTemplate.getDataList(sql);
	}
	@Override
	public List<KznTlProjectdmaicstatus> create(List<KznTlProjectdmaicstatus> kznTlProjectdmaicstatusList,List<KznTlProjectdmaicstatus> existKznTlProjectdmaicstatusLst)
			throws Exception {
		// TODO Auto-generated method stub
		//List<String> sqls = new ArrayList<String>();
		KznTlProjectdmaicstatusSql kznTlProjectdmaicstatusSql = new KznTlProjectdmaicstatusSql();
		try{
			
			if(kznTlProjectdmaicstatusList.size()>0){
				for(int i=0;i<kznTlProjectdmaicstatusList.size();i++){
					CommonMessage.debugMsg("before Execute           ("+ i  + ")" +kznTlProjectdmaicstatusList.size());
					List<String> sqls = new ArrayList<String>();
					KznTlProjectdmaicstatus	kznTlProjectdmaicstatus= kznTlProjectdmaicstatusList.get(i);
				    if(!UIUtils.isValidKeyId(kznTlProjectdmaicstatus.getKpdsKeyid())){
				    	kznTlProjectdmaicstatus.setKpdsKeyid(dbActionTemplate.getSequenceNumber(kznTlProjectdmaicstatusSql.TBL_KZN_TL_PROJECTDMAICSTATUS,15,"KDS","DDMMYY","Y")); // set the sequnce number
						sqls.add(kznTlProjectdmaicstatusSql.getInsertSql(kznTlProjectdmaicstatusSql.getKpdsDbFields(), kznTlProjectdmaicstatus.getSaveArray()));
					}else if(UIUtils.isValidKeyId(kznTlProjectdmaicstatus.getKpdsKeyid())){
						if (CommonFunctions.isValidKeyId(kznTlProjectdmaicstatus.getIsDelete())){		
							if (kznTlProjectdmaicstatus.getIsDelete().equals("Y")){
								sqls.add(kznTlProjectdmaicstatusSql.getDeleteSql(kznTlProjectdmaicstatusSql.getKpdsDbFields(), kznTlProjectdmaicstatus.getSaveArray()));
							}
							else{
								sqls.add(kznTlProjectdmaicstatusSql.getUpdateSql(kznTlProjectdmaicstatusSql.getKpdsDbFields(), kznTlProjectdmaicstatus.getSaveArray()));
							}
						}
						else{
							sqls.add(kznTlProjectdmaicstatusSql.getUpdateSql(kznTlProjectdmaicstatusSql.getKpdsDbFields(), kznTlProjectdmaicstatus.getSaveArray()));
						}
					}
				    dbActionTemplate.executeStatements(sqls);
				    UpdateMaicStatus(kznTlProjectdmaicstatus.getKpdsKzpmKeyid(),kznTlProjectdmaicstatus.getKpdsStage(),"Verification");
				}
			}
			CommonMessage.debugMsg("before Execute");
			//dbActionTemplate.executeStatements(sqls);
			CommonMessage.debugMsg("after Execute");
		}catch(Exception e){
			CommonMessage.debugMsg(" error  "+e.getMessage());
			//e.printStackTrace();
		}
		return null;

	}
	@Override
	public KznTlProjectdmaicstatus delete(
			KznTlProjectdmaicstatus newKznTlProjectdmaicstatus)
			throws ValidationExceptions, BusinessApplicationExceptions,
			Exception {
		// TODO Auto-generated method stub		
		
		List<String> sqls = new ArrayList<String>();
		KznTlProjectdmaicstatusSql kznTlProjectdmaicstatusSql = new KznTlProjectdmaicstatusSql();
		try{
			sqls.add(KznTlProjectResourceLinkSql.getDeleteSql(kznTlProjectdmaicstatusSql.getKpdsDbFields(), newKznTlProjectdmaicstatus.getSaveArray()));			
			dbActionTemplate.executeStatements(sqls);
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		}
		return null;		
	}
	
	@Override
	public String getDefineStage(String kzpmKeyid) throws Exception {
		// TODO Auto-generated method stub
		String defineStageSql=KznTlProjectdmaicstatusSql.getDefineStageSql(kzpmKeyid);
		CommonMessage.debugMsg("Sql"+defineStageSql);
		String definestages="-";
		CommonMessage.debugMsg("defineStageSql"+defineStageSql);
		definestages=	dbActionTemplate.getSingleValue(defineStageSql);
		return definestages;
	}
	
	@Override
	public String getStage(String kzpmKeyid) throws Exception {
		// TODO Auto-generated method stub
		String stageSql=KznTlProjectdmaicstatusSql.getMaicStageSql(kzpmKeyid);
		CommonMessage.debugMsg("Sql"+stageSql);
		String maicStages = "-";
		maicStages=	dbActionTemplate.getSingleValue(stageSql);
		return maicStages;
	}
	
	@Override
	public String getWorkFlowStaus(String kznKeyId, String refType,
			String transCode,boolean isUpdate,String wfStatus) throws Exception {
		// TODOD Auto-generated method stub
		String sql="";
		String statusSql=KznTlProjectdmaicstatusSql.getWorkFlowStatus(kznKeyId,refType,transCode);
		CommonMessage.debugMsg("statusSql "+statusSql);
		String status = "-";
		String wfnewstatus = "P";
		String dmaicStage="";
		CommonMessage.debugMsg("defineStageSq   l"+statusSql);
		status=	dbActionTemplate.getSingleValue(statusSql);
		CommonMessage.debugMsg(transCode+"  status:    "+status);
		CommonMessage.debugMsg(transCode+"  transCode:   "+transCode);
		CommonMessage.debugMsg("   before wfStatus:"+wfStatus);
		
		if(wfStatus.equals("R")){
			wfnewstatus="R";
		}
		else if(wfStatus.equals("E")){
			wfnewstatus="E";
		}
		CommonMessage.debugMsg(transCode+"   transCode  "+isUpdate +" After wfnewstatus:"+wfnewstatus);
		if(isUpdate){
			if (status.equals("1")){
				if (transCode.equals("FIPRODEF") ||transCode.equals("FIPRODEFGE1C")||transCode.equals("FIPRODEFGE5L") ){			
					sql= KznTlProjectmaicMileMstSql.getUpdateDefineStage(kznKeyId,"C");
				
					UpdateMaicStatus(kznKeyId,"A","Approval");
					UpdateMaicStatus(kznKeyId,"M","Approval");
					UpdateMaicStatus(kznKeyId,"I","Approval");
					UpdateMaicStatus(kznKeyId,"C","Approval");
					CommonMessage.debugMsg("UPDATE SQL :"+sql);
					dbActionTemplate.executeStatement(sql);
				}
				else if(transCode.equals("FIPROMEA") ){
					//CommonMessage.debugMsg("UPDATE SQL :"+sql);
					UpdateMaicStatus(kznKeyId,"M","Approval");
				}
				else if(transCode.equals("FIPROANA") ){
					//CommonMessage.debugMsg("UPDATE SQL :"+sql);
					UpdateMaicStatus(kznKeyId,"A","Approval");
				}
				else if(transCode.equals("FIPROIMP") ){
					//CommonMessage.debugMsg("UPDATE SQL :"+sql);
					UpdateMaicStatus(kznKeyId,"I","Approval");
				}
				else if(transCode.equals("FIPROCON") ){
					//CommonMessage.debugMsg("UPDATE SQL :"+sql);
					UpdateMaicStatus(kznKeyId,"C","Approval");
				}
				else if(transCode.equals("FIPROCLO")||transCode.equals("FIPROCLOGE5L")||transCode.equals("FIPROCLOGE1C") ){
					//CommonMessage.debugMsg("UPDATE SQL :"+sql);
					sql= KznTlProjectmaicMileMstSql.getUpdateMaicStage(kznKeyId,"C","X");
					CommonMessage.debugMsg("UPDATE SQL :"+sql);
					dbActionTemplate.executeStatement(sql);
				}
			}
			else if (status.equals("0") && ! "P".equals(wfnewstatus) ){				
				if (transCode.equals("FIPRODEF") ||transCode.equals("FIPRODEFGE1C")||transCode.equals("FIPRODEFGE5L") ){	
					sql= KznTlProjectmaicMileMstSql.getUpdateDefineStage(kznKeyId,wfnewstatus);
					CommonMessage.debugMsg("UPDATE  FIPRODEF SQL :"+sql);
					dbActionTemplate.executeStatement(sql);
				}
				else {
					if(wfStatus.equals("A")){
						wfnewstatus="L";
					}
					if(transCode.equals("FIPROMEA") ){
						dmaicStage="M";
					}
					else if(transCode.equals("FIPROANA") ){
						dmaicStage="A";
					}
					else if(transCode.equals("FIPROIMP") ){
						dmaicStage="I";
					}
					else if(transCode.equals("FIPROCON") ){
						dmaicStage="C";
					}
					else if (transCode.equals("FIPROCLO")||transCode.equals("FIPROCLOGE5L")||transCode.equals("FIPROCLOGE1C") ){
						if(wfStatus.equals("A")){
							wfnewstatus="P";
						}
						dmaicStage="X";
					}
				}
				if (UIUtils.isValidKeyId(dmaicStage)){				
					sql= KznTlProjectmaicMileMstSql.getUpdateMaicStage(kznKeyId,wfnewstatus,dmaicStage);
					CommonMessage.debugMsg("UPDATE other SQL :"+sql);
					dbActionTemplate.executeStatement(sql);
				}
			}
		}
		return status;
	}
	
	@Override
	public String getdmcWorkFlowStaus(String kznKeyId, String refType,
			String transCode,boolean isUpdate,String wfStatus) throws Exception {
		// TODOD Auto-generated method stub
		String sql="";
		String statusSql=KznTlProjectdmaicstatusSql.getdmcWorkFlowStatus(kznKeyId,refType,transCode);
		CommonMessage.debugMsg("statusSql "+statusSql);
		String status = "-";
		String wfnewstatus = "P";
		String dmaicStage="";
		status=	dbActionTemplate.getSingleValue(statusSql);
		//CommonMessage.debugMsg(transCode+" status:"+status);
		//CommonMessage.debugMsg(transCode+" transCode:"+transCode);
		//CommonMessage.debugMsg("before wfStatus:"+wfStatus);
		
		if(wfStatus.equals("R")){
			wfnewstatus="R";
		}
		else if(wfStatus.equals("E")){
			wfnewstatus="E";
		}
		CommonMessage.debugMsg(" after wfnewstatus:"+wfnewstatus);
		if(isUpdate){
			if (status.equals("1")){
				if (transCode.equals("FIPRODEF") ){			
					sql= KznTlProjectmaicMileMstSql.getdmcUpdateDefineStage(kznKeyId,"C");
					CommonMessage.debugMsg("UPDATE SQL :"+sql);
					dbActionTemplate.executeStatement(sql);
				}
				else if(transCode.equals("FIPROMEA") ){
					//CommonMessage.debugMsg("UPDATE SQL :"+sql);
					UpdatedmcMaicStatus(kznKeyId,"M","Approval");
				}
				else if(transCode.equals("FIPROANA") ){
					//CommonMessage.debugMsg("UPDATE SQL :"+sql);
					UpdatedmcMaicStatus(kznKeyId,"A","Approval");
				}
				else if(transCode.equals("FIPROIMP") ){
					//CommonMessage.debugMsg("UPDATE SQL :"+sql);
					UpdatedmcMaicStatus(kznKeyId,"I","Approval");
				}
				else if(transCode.equals("FIPROCON") ){
					//CommonMessage.debugMsg("UPDATE SQL :"+sql);
					UpdatedmcMaicStatus(kznKeyId,"C","Approval");
				}
				else if(transCode.equals("FIPROCLO") ){
					//CommonMessage.debugMsg("UPDATE SQL :"+sql);
					sql= KznTlProjectmaicMileMstSql.getdmcUpdateMaicStage(kznKeyId,"C","X");
					CommonMessage.debugMsg("UPDATE SQL :"+sql);
					dbActionTemplate.executeStatement(sql);
				}
			}
			else if (status.equals("0") && ! "P".equals(wfnewstatus) ){				
				if (transCode.equals("FIPRODEF")  ){	
					sql= KznTlProjectmaicMileMstSql.getdmcUpdateDefineStage(kznKeyId,wfnewstatus);
					CommonMessage.debugMsg("UPDATE  FIPRODEF SQL :"+sql);
					dbActionTemplate.executeStatement(sql);
				}
				else {
					if(wfStatus.equals("A")){
						wfnewstatus="L";
					}
					if(transCode.equals("FIPROMEA") ){
						dmaicStage="M";
					}
					else if(transCode.equals("FIPROANA") ){
						dmaicStage="A";
					}
					else if(transCode.equals("FIPROIMP") ){
						dmaicStage="I";
					}
					else if(transCode.equals("FIPROCON") ){
						dmaicStage="C";
					}
					else if (transCode.equals("FIPROCLO") ){
						if(wfStatus.equals("A")){
							wfnewstatus="P";
						}
						dmaicStage="X";
					}
				}
				if (UIUtils.isValidKeyId(dmaicStage)){				
					sql= KznTlProjectmaicMileMstSql.getdmcUpdateMaicStage(kznKeyId,wfnewstatus,dmaicStage);
					CommonMessage.debugMsg("UPDATE other SQL :"+sql);
					dbActionTemplate.executeStatement(sql);
				}
			}
		}
		return status;
	}
	
	@Override
	public String[] getdmcAllStageStatus(String kznKeyId) throws Exception{
		String sql =  KznTlProjectcreationmstSql.getdmcAllStageStatusSql();
		Object [] args = { kznKeyId };
		List<String[]> data =   dbActionTemplate.getDataList(sql, args);
		return data.get(0);
	}
	
	
	public String[] getAllStageStatus(String kznKeyId) throws Exception{
		String sql =  KznTlProjectcreationmstSql.getAllStageStatusSql();
		Object [] args = { kznKeyId };
		List<String[]> data =   dbActionTemplate.getDataList(sql, args);
		return data.get(0);
	}
	
	
	@Override
	public String getMaicStage(String kznKeyId,	String stage) throws Exception {
		// TODO Auto-generated method stub
		String colName="";
		if(stage.equals("D")){
			colName="KZPM_DEFINESTAGE";
		}	
		else if(stage.equals("M")){
			colName="KZPM_MEASURESTAGE";
		}			
		else if(stage.equals("A")){
			colName="KZPM_ANALYSESTAGE";
		}
		else if(stage.equals("I")){
			colName="KZPM_IMPROVESTAGE";
		}	
		else if(stage.equals("C")){
			colName="KZPM_CONTROLSTAGE";
		}
		else if(stage.equals("X")){
			colName="KZPM_CLOSURESTAGE";
		}
		String getStatusSql="SELECT "+colName+" FROM KZN_TL_PROJECTCREATIONMST WHERE KZPM_KEYID='"+kznKeyId+"'";		
		CommonMessage.debugMsg("getStatusSql:"+getStatusSql);		
		String status = dbActionTemplate.getSingleValue(getStatusSql);
		CommonMessage.debugMsg("status:"+status);
		return status;
	}
	@Override
	public KznTlProjectResourceLink create(
			List<KznTlProjectResourceLink> kznTlProjectResourceLinkList)
			throws ValidationExceptions, BusinessApplicationExceptions,
			Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		KznTlProjectResourceLinkSql kznTlProjectResourceLinkSql = new KznTlProjectResourceLinkSql();
		//KznTlProjectResourceLink kznTlProjectResourceLink=new KznTlProjectResourceLink();
		try{			
			if(kznTlProjectResourceLinkList!=null){
				//sqls.add(KznTlProjectKaizenLinkSql.getDeleteAllSql( newKznTlProjectKaizenLink.getProjectKaizen().get(0).getKplkKzpmKeyid()));
				for(int i=0;i<kznTlProjectResourceLinkList.size();i++){
					CommonMessage.debugMsg("before Execute           ("+ i  + ")" +kznTlProjectResourceLinkList.size());					
					KznTlProjectResourceLink	kznTlProjectResourceLink= kznTlProjectResourceLinkList.get(i);					
					if(kznTlProjectResourceLink.getKprlKeyid() ==null ){
						kznTlProjectResourceLink.setKprlKeyid(dbActionTemplate.getSequenceNumber(kznTlProjectResourceLinkSql.TBL_KZN_TL_PROJECT_RESOURCE_LINK,15,"KPR","DDMMYY","Y")); // set the sequnce number
						sqls.add(kznTlProjectResourceLinkSql.getInsertSql(kznTlProjectResourceLinkSql.getKprlDbFields(), kznTlProjectResourceLink.getSaveArray()));
					}
					else{
						if (CommonFunctions.isValidKeyId(kznTlProjectResourceLink.getIsDelete())){		
							if (kznTlProjectResourceLink.getIsDelete().equals("Y")){								
								sqls.add(kznTlProjectResourceLinkSql.getDeleteSql(kznTlProjectResourceLinkSql.getKprlDbFields(), kznTlProjectResourceLink.getSaveArray()));								
							}
							else{
								sqls.add(kznTlProjectResourceLinkSql.getUpdateSql(kznTlProjectResourceLinkSql.getKprlDbFields(), kznTlProjectResourceLink.getSaveArray()));
							}
						}
						else if(kznTlProjectResourceLink.getKprlKeyid() !=null ){
							sqls.add(kznTlProjectResourceLinkSql.getUpdateSql(kznTlProjectResourceLinkSql.getKprlDbFields(), kznTlProjectResourceLink.getSaveArray()));
						}
					}
				}
			}
			CommonMessage.debugMsg("before Execute");
			dbActionTemplate.executeStatements(sqls);
			CommonMessage.debugMsg("after Execute");
			return null;
		}catch(Exception e){
			CommonMessage.debugMsg(" error  "+e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}
	@Override
	public KznTlProjectKpiLink create(KznTlProjectKpiLink newKznTlProjectKpiLink)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String[]> getListNewResources(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		/*String sql = " select distinct '' as selVal,KPRL_KEYID as txtKprlKeyid ,empm_keyid txtKprlEmpmKeyid,empm_name||'-'||empm_code empName,KPRL_KZPM_KEYID txtKprlKzpmKeyid from ";
			sql += " gen_tl_employeemst,kzn_tl_project_resource_link,gen_tl_fnlnroleteam";
			sql += " where FRT_FNLN_KEYID='"+commonFilter.getFlid()+"' and FRT_EMPM_KEYID= empm_keyid AND KPRL_KZPM_KEYID(+) = '"+commonFilter.getKey()+"'";
		   	sql += " and KPRL_EMPM_KEYID(+)=FRT_EMPM_KEYID order by EMPM_KEYID asc";
		   CommonMessage.debugMsg("empSql  "+sql);
		List<String[]> getEmpList = dbActionTemplate.getDataList(sql);
		return getEmpList;*/
	
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = "";
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms=condParms+"KZPMKEYID="+commonFilter.getKey()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
				condParms=condParms+"FLID="+commonFilter.getFlid()+";";
			}
			
			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			CommonMessage.debugMsg("KZN_FN_PROJECTRESOURCEEMPLIST....");

            dataList = fnCallApi.callFunction("KZN_FN_PROJECTRESOURCEEMPLIST_SB", paramValues, 3, true); //dbActionTemplate.processFunctionCalls("KZN_FN_PROJECTRESOURCEEMPLIST", paramValues);
           
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}

	}
	
	public String getDMTLeader(KznTlProjectcreationmst newKznTlProjectcreationmst)throws Exception{
		String empId="";
		String sql =KznTlProjectcreationmstSql.getDMTLeader(newKznTlProjectcreationmst.getKzpmFlid());
		empId=dbActionTemplate.getSingleValue(sql);
		return empId;
	}
	
	public String getdmcDMTLeader(KznTlDmcfipcreationmst newKznTlDmcfipcreationmst)throws Exception{
		String empId="";
		String sql =KznTlDmcfipcreationmstSql.getdmcDMTLeader(newKznTlDmcfipcreationmst.getDmcmFlid());
		empId=dbActionTemplate.getSingleValue(sql);
		return empId;
	}
	
	@Override
	public List<String[]> getProjectCreationList(CommonFilter commonFilter)
			throws Exception {
		
	
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter); 
			
			/*if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
				condParms=condParms+"FLID="+commonFilter.getFlid()+";";
			}*/
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			CommonMessage.debugMsg("KZN_FN_PROJECTCREATIONLIST....");
			//dataList =  dbActionTemplate.processFunctionCalls("KZN_FN_PROJECTCREATIONLIST", paramValues);
			dataList = fnCallApi.callFunction("KZN_FN_PROJECTCREATIONLIST_SB", paramValues,3,false);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}

	}
	@Override
	public List<String[]> getdmcProjectCreationList(CommonFilter commonFilter)
			throws Exception {
		
	
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter); 
			condParms = condParms+ "ET=" +commonFilter.getET();
			/*if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
				condParms=condParms+"FLID="+commonFilter.getFlid()+";";
			}*/
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			CommonMessage.debugMsg("In dao impl="+commonFilter.getET());
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			CommonMessage.debugMsg("KZN_PC_KAIZEN.KZN_FN_DMCPROJECTCREATIONLIST....");
			dataList =  dbActionTemplate.processFunctionCalls("KZN_FN_DMCPROJECTCREATIONLIST", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}

	}
	@Override
	public Workbook getprojectlistExcel(JSONObject colmodel, String format,	CommonFilter commonFilter) throws Exception {
		
		// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				ResultSet rs = null;
				// TODO Auto-generated method stub
				 try{
						
					 rs =   getprojectrelatnResultSet(commonFilter);
					 
					 ExcelUtils excelUtils = new ExcelUtils(colmodel);
						return excelUtils.writeToExcel(rs,format, 2,0,0 );
						
					   }finally{
						   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
					   }
	}
	@Override
	public Workbook getdmcprojectlistExcel(JSONObject colmodel, String format,	CommonFilter commonFilter) throws Exception {
		
		// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				ResultSet rs = null;
				// TODO Auto-generated method stub
				 try{
						
					 rs =   getdmcprojectrelatnResultSet(commonFilter);
					 
					 ExcelUtils excelUtils = new ExcelUtils(colmodel);
						return excelUtils.writeToExcel(rs,format, 2,0,0 );
						
					   }finally{
						   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
					   }
	}
	private ResultSet getprojectrelatnResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg("result");
		return dbActionTemplate.NewdbFunctionCall2("KZN_FN_PROJECTCREATIONLIST", paramValues);
	}
	
	private ResultSet getdmcprojectrelatnResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg("result");
		return dbActionTemplate.NewdbFunctionCall2("KZN_FN_DMCPROJECTCREATIONLIST", paramValues);
	}
	public List<String[]> getProjectCheckList(String stage,String projectId) throws Exception {
		String sql = KznTlProjectcreationmstSql.getCheckLists();
		Object [] args = {projectId,stage};
		CommonMessage.debugMsg(args + " " + sql );
		return dbActionTemplate.getDataList(sql, args  );
	}
	
	@Override
	public List<String[]> getDmaiccunt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String WAVES = commonFilter.getColVal();
		//if(WAVES != null)
		//{	
		condParms += "WAVES="+WAVES;
		CommonMessage.debugMsg("waves="+WAVES);
		//}
		
		 String sql1="UPDATE KZN_TL_PROJECTCREATIONMST SET KZPM_DEFINESTAGE='P' WHERE KZPM_KEYID IN (select KZPM_KEYID from KZN_TL_PROJECTCREATIONMST where KZPM_DEFINESTAGE='W') ";
		   String sql2="UPDATE KZN_TL_PROJECTCREATIONMST SET KZPM_CLOSURESTAGE='P' WHERE KZPM_KEYID IN (select KZPM_KEYID from KZN_TL_PROJECTCREATIONMST where KZPM_CLOSURESTAGE='W') ";
		  CommonMessage.debugMsg("sql1"+sql1);
		   dbActionTemplate.executeStatement(sql1);
		   dbActionTemplate.executeStatement(sql2);
		   
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		dataList = fnCallApi.callFunction("KZN_FN_DMAICCOUNT_SB", paramValues, 3, false);
//		dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_DMAICCOUNT", paramValues);

		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 

	}
	@Override
	public List<String[]> getpiechart(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String WAVES = commonFilter.getColVal();
		//if(WAVES != null)
		//{	
		condParms += "WAVES="+WAVES;
		CommonMessage.debugMsg("waves="+WAVES);
		//}
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_DMAICCOUNT", paramValues);
        
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt)+1);
			}
		}
		return dataList; 
	
	}
	@Override
	public Workbook getdmaicExportToExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		   ResultSet rs = null;
		    try{
				
				rs =   getdmaicResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				
				return excelUtils.writeToExcel(rs,format,2 ,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}
	private ResultSet getdmaicResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String WAVES = commonFilter.getColVal();
		//if(WAVES != null)
		//{	
		condParms += "WAVES="+WAVES;
		CommonMessage.debugMsg("waves="+WAVES);
		//}
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		return dbActionTemplate.NewdbFunctionCall2("KZN_FN_DMAICCOUNT", paramValues);
	}
	@Override
	public List<String[]> getdmcDmaiccunt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String WAVES = commonFilter.getColVal();
		//if(WAVES != null)
		//{	
		condParms += "WAVES="+WAVES;
		CommonMessage.debugMsg("waves="+WAVES);
		//}
		String fiptype=commonFilter.getActwise();
		CommonMessage.debugMsg("fiptype in Dao impl="+fiptype);
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_DMCDMAICCOUNT", paramValues);

		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 

	}
	@Override
	public List<String[]> getdmcpiechart(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String WAVES = commonFilter.getColVal();
		//if(WAVES != null)
		//{	
		condParms += "WAVES="+WAVES;
		CommonMessage.debugMsg("waves="+WAVES);
		//}
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_DMCDMAICCOUNT", paramValues);
        
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt)+1);
			}
		}
		return dataList; 
	
	}
	@Override
	public Workbook getdmcdmaicExportToExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		   ResultSet rs = null;
		    try{
				
				rs = getdmcdmaicResultSet(commonFilter); //   getdmaicResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				
				return excelUtils.writeToExcel(rs,format,2 ,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}
	private ResultSet getdmcdmaicResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String WAVES = commonFilter.getColVal();
		//if(WAVES != null)
		//{	
		condParms += "WAVES="+WAVES;
		CommonMessage.debugMsg("waves="+WAVES);
		//}
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		return dbActionTemplate.NewdbFunctionCall2("KZN_FN_DMCDMAICCOUNT", paramValues);
	}
	@Override
	public List<String[]> getJhmemberaddresources(CommonFilter commonFilter1,
			String flid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = "";
			
			if(CommonFunctions.isValidKeyId(commonFilter1.getJH())){
				
				condParms=condParms+"FLID="+commonFilter1.getFlid()+";";
				condParms=condParms+"KZPMKEYID="+commonFilter1.getKey()+";";
				
			}else{
				if(CommonFunctions.isValidKeyId(commonFilter1.getFlid())){
					condParms=condParms+"FLID="+commonFilter1.getFlid()+";";
				}
				if(CommonFunctions.isValidKeyId(commonFilter1.getRoleLevel())){
					condParms=condParms+"ROLE="+commonFilter1.getRoleLevel()+";";
				}
				if(CommonFunctions.isValidKeyId(commonFilter1.getKK()))
					condParms=condParms+"KZPMKEYID="+commonFilter1.getKK()+";";
			}
				
						
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			CommonMessage.debugMsg("KZN_PC_KAIZEN.KZN_FN_ADDJHMEMBERSRESOURCE....");
			if(CommonFunctions.isValidKeyId(commonFilter1.getJH()))
			   dataList = fnCallApi.callFunction("KZN_FN_PROJECTRESOURCEEMPLIST_SB", paramValues, 3, true);// dbActionTemplate.processFunctionCalls("KZN_FN_PROJECTRESOURCEEMPLIST", paramValues);
			else
			   dataList = fnCallApi.callFunction("KZN_FN_ADDJHMEMBERSRESOURCE_SB", paramValues, 3, true);//  dbActionTemplate.processFunctionCalls("KZN_FN_ADDJHMEMBERSRESOURCE", paramValues);
                
			if( commonFilter1.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter1.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public List<String[]> getdmcJhmemberaddresources(CommonFilter commonFilter1,
			String flid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = "";
			
			if(CommonFunctions.isValidKeyId(commonFilter1.getJH())){
				
				condParms=condParms+"FLID="+commonFilter1.getFlid()+";";
				condParms=condParms+"KZPMKEYID="+commonFilter1.getKey()+";";
				
			}else{
				if(CommonFunctions.isValidKeyId(commonFilter1.getFlid())){
					condParms=condParms+"FLID="+commonFilter1.getFlid()+";";
				}
				if(CommonFunctions.isValidKeyId(commonFilter1.getRoleLevel())){
					condParms=condParms+"ROLE="+commonFilter1.getRoleLevel()+";";
				}
				if(CommonFunctions.isValidKeyId(commonFilter1.getKK()))
					condParms=condParms+"KZPMKEYID="+commonFilter1.getKK()+";";
			}
				
					
			String AllEmployees = commonFilter1.getAreatype();	
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			CommonMessage.debugMsg("KZN_PC_KAIZEN.KZN_FN_ADDJHMEMBERSRESOURCE....");
            if(AllEmployees.equals("Yes")){
    			dataList =  dbActionTemplate.processFunctionCalls("KZN_PC_KAIZEN.KZN_FN_PROJECTALLEMPLIST", paramValues);
                }
                else{
			if(CommonFunctions.isValidKeyId(commonFilter1.getJH()))
			   dataList =  dbActionTemplate.processFunctionCalls("KZN_FN_PROJECTRESOURCEEMPLIST", paramValues);
			else
			   dataList =  dbActionTemplate.processFunctionCalls("KZN_FN_ADDJHMEMBERSRESOURCE", paramValues);
                }
			if( commonFilter1.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter1.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public void DeleteJhMemberRecord(String keyid) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		sqls.add( KznTlProjectcreationmstSql.Removejhmember(keyid));
        dbActionTemplate.executeStatements(sqls);
        
	}
	@Override
	public List<String[]> setInsertQuery(String rolename, String refId,
			String nxtrole, String trnscode, String lstlvl, String flId,
			String roleid,String workstatus) throws Exception {
		
		// TODO Auto-generated method stub
		List<String[]> result=null;
		CommonMessage.debugMsg("In side the Set insert Query Daoimpl RefId "+refId);
		CommonMessage.debugMsg("In side the Set insert Query Daoimpl flid "+flId);
		CommonMessage.debugMsg("In side the Set insert Query Daoimpl trnscode "+ trnscode);
		CommonMessage.debugMsg("In side the Set insert Query Daoimpl Next Role  "+ nxtrole);
		CommonMessage.debugMsg("In side the Set insert Query Daoimpl RoleId  "+ roleid);
		String kzpmstatus=null;
		String status=null;
		if(nxtrole!=null)
		{
			
		if(nxtrole.equals("AROL0059"))
		{
				kzpmstatus="B";//Pbu Head
				CommonMessage.debugMsg("countsql:::countsql:::AROL0059:::"+trnscode);

		}	
		if(nxtrole.equals("AROL0002"))
		{
			CommonMessage.debugMsg("countsql:::countsql:::AROL0002:::"+trnscode);

			kzpmstatus="S";//sbu Head
		}
		/////////////
		else if(nxtrole.equals("AROL0001"))
		{
			kzpmstatus="F";//Finance
			CommonMessage.debugMsg("countsql:::countsql:::AROL0061:::"+trnscode);

			
		}
		////////////
		else if(nxtrole.equals("AROL0061"))
		{
			kzpmstatus="K";//kk
			CommonMessage.debugMsg("countsql:::countsql:::AROL0061:::"+trnscode);

			
		}
		else if(nxtrole.equals("AROL0060"))
		{
			CommonMessage.debugMsg("countsql:::countsql:::AROL0060:::"+trnscode);

			kzpmstatus="C";//Finance
		}
		}
			
		if(refId!=null&&refId.length()!=0)
		{
			CommonMessage.debugMsg("countsql:::countsql:::countsql:::   "+trnscode);

			if(workstatus.equals("A"))
			{
			if(trnscode.equals("FIPRODEF")||trnscode.equals("FIPRODEFGE1C")||trnscode.equals("FIPRODEFGE5L")||trnscode.equals("FIPROCLO")||trnscode.equals("FIPROCLOGE5L")||trnscode.equals("FIPROCLOGE1C"))
			{
								
			
			String countsql="select count(*) from KK_TL_FIPAPPROVALSLIST where PROJECTNO='"+refId+"' AND STAGE='"+trnscode+"'";
		//	String rolesql="select count(*) from KK_TL_FIPAPPROVALSLIST where PROJECTNO='"+refId+"' AND STAGE='"+trnscode+"'";
			CommonMessage.debugMsg("countsql:::  "+countsql);
			String count=dbActionTemplate.getSingleValue(countsql);
			//String rolecount=dbActionTemplate.getSingleValue(rolesql);
			int countvalues=Integer.valueOf(count);
			//

			CommonMessage.debugMsg("Get Count "+count);
			if(countvalues>=1)
			{
				CommonMessage.debugMsg(" In side the Count value > 1");
				
				String updatesql="Update KK_TL_FIPAPPROVALSLIST set CURRENTSTATUS='C' where PROJECTNO='"+refId+"' AND STAGE='"+trnscode+"' AND CURRENTSTATUS='P' ";
				
				//String updatesql="Update KK_TL_FIPAPPROVALSLIST set CURRENTSTATUS='C' where PROJECTNO='"+refId+"'";
				//AND STAGE='"+trnscode+"' AND CURRENTSTATUS='N' ";
				dbActionTemplate.executeStatement(updatesql);
				//CommonMessage.debugMsg("updatesql"+updatesql);
			   
				
			}
			
			if(lstlvl.equals("Y"))
			{
				CommonMessage.debugMsg(" In side the Las level Y   "+1);
				status="C";
			}
			else{
				CommonMessage.debugMsg(" In side the Las level N"+1);
				status="P";
			}
			
			
			String updatesql="Update KK_TL_FIPAPPROVALSLIST set MENULEVELSTATUS='C' where PROJECTNO='"+refId+"' AND STAGE='"+trnscode+"' AND MENULEVELSTATUS='P' ";
			//String updatesql="Update KK_TL_FIPAPPROVALSLIST set CURRENTSTATUS='C' where PROJECTNO='"+refId+"'";
			//AND STAGE='"+trnscode+"' AND CURRENTSTATUS='N' ";
			dbActionTemplate.executeStatement(updatesql);
			
			String InsertQuery="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+refId+"','FIP',"+"'"+trnscode+"','"+nxtrole+"','"+lstlvl+"','"+roleid+"',"+"CURRENT_TIMESTAMP"+",'"+flId+"','"+status+"','"+status+"')";
			CommonMessage.debugMsg("InsertQuery    "+InsertQuery);
			dbActionTemplate.executeStatement(InsertQuery);
			
			
			
			if(lstlvl.equals("N"))
			{
				if(trnscode.equals("FIPRODEF")||trnscode.equals("FIPRODEFGE1C")||trnscode.equals("FIPRODEFGE5L"))
						{
					String updatesql1="Update KZN_TL_PROJECTCREATIONMST set KZPM_DEFINESTAGE='P' where KZPM_KEYID='"+refId+"'";
			
					dbActionTemplate.executeStatement(updatesql1);
						}
				else{
					//MADHAN		
			//String updatesql1="Update KZN_TL_PROJECTCREATIONMST set KZPM_CLOSURESTAGE='P' where KZPM_KEYID='"+refId+"'";

			
					/*updatesql1= updatesql1+" Update KZN_TL_PROJECTCREATIONMST set KZPM_ANALYSESTAGE='P' where KZPM_KEYID='"+refId+"'"+
			   updatesql= updatesql1 +" Update KZN_TL_PROJECTCREATIONMST set KZPM_MEASURESTAGE='P' where KZPM_KEYID='"+refId+"'" +
			   updatesql=updatesql1 +" Update KZN_TL_PROJECTCREATIONMST set KZPM_IMPROVESTAGE='P' where KZPM_KEYID='"+refId+"'" +
			    updatesql=updatesql1 +" Update KZN_TL_PROJECTCREATIONMST set KZPM_CONTROLSTAGE='P' where KZPM_KEYID='"+refId+"'" +*/
			//dbActionTemplate.executeStatement(updatesql2);
			//dbActionTemplate.executeStatement(updatesql1);
		    }
			//String updatesql="Update KK_TL_FIPAPPROVALSLIST set CURRENTSTATUS='C' where PROJECTNO='"+refId+"'";
			//AND STAGE='"+trnscode+"' AND CURRENTSTATUS='N' ";
			
			}
			else{
				if(trnscode.equals("FIPRODEF")||trnscode.equals("FIPRODEFGE1C")||trnscode.equals("FIPRODEFGE5L")){
				//String updatesql1="Update KZN_TL_PROJECTCREATIONMST set KZPM_DEFINESTAGE='C' where KZPM_KEYID='"+refId+"'";
				CommonMessage.debugMsg(" IN side the Else Statement");
				// MADHAN
				//String updatesql1=" Update KZN_TL_PROJECTCREATIONMST set KZPM_DEFINESTAGE='C',KZPM_ANALYSESTAGE='C',KZPM_MEASURESTAGE='C',KZPM_IMPROVESTAGE='C',KZPM_CONTROLSTAGE='C' where KZPM_KEYID='"+refId+"'";
				String updatesql1=" Update KZN_TL_PROJECTCREATIONMST set KZPM_DEFINESTAGE='C',KZPM_DEFINECOMPLETEDDATE = CURRENT_DATE where KZPM_KEYID='"+refId+"'";

				dbActionTemplate.executeStatement(updatesql1);
				
				//dbActionTemplate.executeStatement(InsertQuery);
				// MADHAN
				//String updatesql2="Update KZN_TL_PROJECTCREATIONMST set KZPM_CLOSURESTAGE='P' where KZPM_KEYID='"+refId+"'";
		
				String updatesql2="Update KZN_TL_PROJECTCREATIONMST set KZPM_MEASURESTAGE='P' where KZPM_KEYID='"+refId+"'";
				dbActionTemplate.executeStatement(updatesql2);
				
				}
				else if(trnscode.equals("FIPROCLO")||trnscode.equals("FIPROCLOGE1C")||trnscode.equals("FIPROCLOGE5L")){
					
					String updatesql2="Update KZN_TL_PROJECTCREATIONMST set KZPM_CLOSURESTAGE='C' , KZPM_CLOSURECOMPLETEDDATE = CURRENT_DATE where KZPM_KEYID='"+refId+"'";
					
					dbActionTemplate.executeStatement(updatesql2);
				}
				
				
				
					}
				
			}
			/*if(trnscode.equals("FIPRODEF"))//For Define stage update based on role
					{	
			  String updatesql="Update KZN_TL_PROJECTCREATIONMST set KZPM_DEFINESTAGE='"+kzpmstatus+"' where KZPM_KEYID='"+refId+"'";
			//String updatesql="Update KK_TL_FIPAPPROKZN_TL_PROJECTCREATIONMST;
			  dbActionTemplate.executeStatement(updatesql);
			//  CommonMessage.debugMsg("updatesql"+updatesql);
					}else{////For Measure stage update based on role
						
						 String updatesql="Update KZN_TL_PROJECTCREATIONMST set KZPM_CLOSURESTAGE='"+kzpmstatus+"' where KZPM_KEYID='"+refId+"'";
							//String updatesql="Update KK_TL_FIPAPPROKZN_TL_PROJECTCREATIONMST;
							  dbActionTemplate.executeStatement(updatesql);
							//  CommonMessage.debugMsg("updatesql"+updatesql);
					}*/
			
			}
			else{
				
				CommonMessage.debugMsg(" In sid ethe Else Part **** 2");
				String rolesql="select count(*) from KK_TL_FIPAPPROVALSLIST where PROJECTNO='"+refId+"' AND STAGE='"+trnscode+"'";
				String rolecount=dbActionTemplate.getSingleValue(rolesql);
				int rolecountvalue=Integer.valueOf(rolecount);
				
				if(rolecountvalue==0)
				{
				if(trnscode.equals("FIPROMEA"))
				{ 
				  String updatesql="Update KZN_TL_PROJECTCREATIONMST set KZPM_MEASURESTAGE='P' where KZPM_KEYID='"+refId+"'";
				//String updatesql="Update KK_TL_FIPAPPROKZN_TL_PROJECTCREATIONMST;
				  dbActionTemplate.executeStatement(updatesql);
				//  CommonMessage.debugMsg("updatesql"+updatesql);
				  String InsertQuery="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+refId+"','FIP',"+"'"+trnscode+"','"+nxtrole+"','"+lstlvl+"','"+roleid+"',"+"CURRENT_TIMESTAMP"+",'"+flId+"','C','C')";
					//CommonMessage.debugMsg("InsertQuery"+InsertQuery);
					dbActionTemplate.executeStatement(InsertQuery);
				}
				else if(trnscode.equals("FIPROANA"))
				{ 
					  String updatesql="Update KZN_TL_PROJECTCREATIONMST set KZPM_ANALYSESTAGE='P' where KZPM_KEYID='"+refId+"'";
					//String updatesql="Update KK_TL_FIPAPPROKZN_TL_PROJECTCREATIONMST;
					  dbActionTemplate.executeStatement(updatesql);
					//  CommonMessage.debugMsg("updatesql"+updatesql);
					  String InsertQuery="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+refId+"','FIP',"+"'"+trnscode+"','"+nxtrole+"','"+lstlvl+"','"+roleid+"',"+"CURRENT_TIMESTAMP"+",'"+flId+"','C','C')";
				//		CommonMessage.debugMsg("InsertQuery"+InsertQuery);
						dbActionTemplate.executeStatement(InsertQuery);
				}
				else if(trnscode.equals("FIPROIMP"))
				{ 
					  String updatesql="Update KZN_TL_PROJECTCREATIONMST set KZPM_IMPROVESTAGE='P' where KZPM_KEYID='"+refId+"'";
					//String updatesql="Update KK_TL_FIPAPPROKZN_TL_PROJECTCREATIONMST;
					  dbActionTemplate.executeStatement(updatesql);
					//  CommonMessage.debugMsg("updatesql"+updatesql);
					  String InsertQuery="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+refId+"','FIP',"+"'"+trnscode+"','"+nxtrole+"','"+lstlvl+"','"+roleid+"',"+"CURRENT_TIMESTAMP"+",'"+flId+"','C','C')";
					//	CommonMessage.debugMsg("InsertQuery"+InsertQuery);
						dbActionTemplate.executeStatement(InsertQuery);
				}
				else if(trnscode.equals("FIPROCON"))
				{ 
					  String updatesql="Update KZN_TL_PROJECTCREATIONMST set KZPM_CONTROLSTAGE='P' where KZPM_KEYID='"+refId+"'";
					//String updatesql="Update KK_TL_FIPAPPROKZN_TL_PROJECTCREATIONMST;
					  dbActionTemplate.executeStatement(updatesql);
					//  CommonMessage.debugMsg("updatesql"+updatesql);
					  String InsertQuery="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+refId+"','FIP',"+"'"+trnscode+"','"+nxtrole+"','"+lstlvl+"','"+roleid+"',"+"CURRENT_TIMESTAMP"+",'"+flId+"','C','C')";
					//	CommonMessage.debugMsg("InsertQuery"+InsertQuery);
						dbActionTemplate.executeStatement(InsertQuery);
				}
				
				
				
				
				}
				
				
				
			}
			}
			else{
				
				
					//CommonMessage.debugMsg("updatesql"+2);
				String InsertQueryold="delete from KK_TL_FIPAPPROVALSLIST where PROJECTNO='"+refId+"'";
				dbActionTemplate.executeStatement(InsertQueryold);
				if(trnscode.equals("FIPRODEF")||trnscode.equals("FIPRODEFGE1C")||trnscode.equals("FIPRODEFGE5L")||trnscode.equals("FIPROCLO")||trnscode.equals("FIPROCLOGE1C")||trnscode.equals("FIPROCLOGE5L"))
				{
					status="P";
			    String InsertQuery1="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+refId+"','FIP',"+"'"+trnscode+"','"+"AROL0059"+"','"+lstlvl+"','"+roleid+"',"+"CURRENT_TIMESTAMP"+",'"+flId+"','P','P')";

				String InsertQuery2="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+refId+"','FIP',"+"'FIPROMEA','"+nxtrole+"','"+lstlvl+"','"+roleid+"',"+"CURRENT_TIMESTAMP"+",'"+flId+"','C','C')";
				String InsertQuery3="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+refId+"','FIP',"+"'FIPROANA','"+nxtrole+"','"+lstlvl+"','"+roleid+"',"+"CURRENT_TIMESTAMP"+",'"+flId+"','C','C')";
				String InsertQuery4="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+refId+"','FIP',"+"'FIPROIMP','"+nxtrole+"','"+lstlvl+"','"+roleid+"',"+"CURRENT_TIMESTAMP"+",'"+flId+"','C','C')";
				String InsertQuery5="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+refId+"','FIP',"+"'FIPROCON','"+nxtrole+"','"+lstlvl+"','"+roleid+"',"+"CURRENT_TIMESTAMP"+",'"+flId+"','C','C')";

			    CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
						dbActionTemplate.executeStatement(InsertQuery1);
						dbActionTemplate.executeStatement(InsertQuery2);
						dbActionTemplate.executeStatement(InsertQuery3);
						dbActionTemplate.executeStatement(InsertQuery4);
						dbActionTemplate.executeStatement(InsertQuery5);


				}
				
				
				
			}
			//work status
		return result;
		}
	
	
	
	@Override
	public String InsertPbuHead(String kzpmKeyid,String mode, String flid) throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg(" In side the insertPbuHead Method " +mode);
		String  status=null;
		try{
			String benval = getAmount(kzpmKeyid);
			int amount=Integer.parseInt(CommonFunctions.isValidKeyId(benval)? benval : "0" );
			String trnsCode=null;
			CommonMessage.debugMsg(" IN sid ethe INsert PBU Head ***" +amount);
			if(amount>=500000 && amount<10000000){
				trnsCode="FIPRODEGE5L";
			}
			else if(amount>=10000000 ){
				trnsCode="FIPRODEFGE1C";
			}
			else{
				trnsCode="FIPRODEF";
			}
			
			CommonMessage.debugMsg(" Aftere The Get Amount  ***   " +trnsCode);

		if(mode.equals("define"))
		{

		//String InsertQuery="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+kzpmKeyid+"','FIP',"+"'FIPRODEF','AROL0002','N','AROL0059',"+"sysdate"+",'"+flid+"','P','P')";
		
			String InsertQuery="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+kzpmKeyid+"','FIP',"+"'"+trnsCode+"','AROL0002','N','AROL0059',"+"CURRENT_TIMESTAMP"+",'"+flid+"','P','P')";

			CommonMessage.debugMsg("InsertQuery"+InsertQuery);
		dbActionTemplate.executeStatement(InsertQuery);
		
		 String updatesql="Update KZN_TL_PROJECTCREATIONMST set KZPM_DEFINESTAGE='P' where KZPM_KEYID='"+kzpmKeyid+"'";
			//String updatesql="Update KK_TL_FIPAPPROKZN_TL_PROJECTCREATIONMST;
			CommonMessage.debugMsg("updatesql in side the define  "+updatesql);
	
		 dbActionTemplate.executeStatement(updatesql);

		status="success";
		}
		else if(mode.equals("finance"))
		{
			String updatesql="Update KK_TL_FIPAPPROVALSLIST set CURRENTSTATUS='C' where PROJECTNO='"+kzpmKeyid+"' AND STAGE='"+trnsCode+"' AND CURRENTSTATUS='N' ";
		
			dbActionTemplate.executeStatement(updatesql);
			
			CommonMessage.debugMsg("updatesql in side the finance  "+updatesql);
			
			String updatesql1="Update KK_TL_FIPAPPROVALSLIST set MENULEVELSTATUS='C' where PROJECTNO='"+kzpmKeyid+"'  AND MENULEVELSTATUS='P' ";
			//String updatesql="Update KK_TL_FIPAPPROVALSLIST set CURRENTSTATUS='C' where PROJECTNO='"+refId+"'";
			//AND STAGE='"+trnscode+"' AND CURRENTSTATUS='N' ";
			
			dbActionTemplate.executeStatement(updatesql1);
			
			String InsertQuery1="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+kzpmKeyid+"','FIP',"+"'"+trnsCode+"','AROL0060','N','AROL0061',"+"CURRENT_TIMESTAMP"+",'"+flid+"','P','P')";
				CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
			dbActionTemplate.executeStatement(InsertQuery1);
			
			
		}
		}
		catch(Exception e)
		{
			status="error";
		}
		return status;
	}
	
	@Override
	public String InsertPbuHeadClosure(String kzpmKeyid,String mode, String flid) throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg(" In side the insertPbuHead Method " +mode);
		String  status=null;
		try{
			
			String benval = getAmount(kzpmKeyid);
			int amount=Integer.parseInt(CommonFunctions.isValidKeyId(benval)? benval : "0" );
			String trnsCode=null;
			CommonMessage.debugMsg(" IN sid ethe INsert PBU Head ***" +amount);
			if(amount>=500000 && amount<10000000){
				trnsCode="FIPROCLOE5L";
			}
			else if(amount>=10000000 ){
				trnsCode="FIPROCLOGE1C";
			}
			else{
				trnsCode="FIPROCLO";
			}
			
			CommonMessage.debugMsg(" Aftere The Get Amount  ***   " +trnsCode);

		if(mode.equals("closure"))
		{

		//String InsertQuery="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+kzpmKeyid+"','FIP',"+"'FIPRODEF','AROL0002','N','AROL0059',"+"sysdate"+",'"+flid+"','P','P')";
		
			String InsertQuery="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+kzpmKeyid+"','FIP',"+"'"+trnsCode+"','AROL0002','N','AROL0059',"+"CURRENT_TIMESTAMP"+",'"+flid+"','P','P')";

			CommonMessage.debugMsg("InsertQuery"+InsertQuery);
		dbActionTemplate.executeStatement(InsertQuery);
		
		 String updatesql="Update KZN_TL_PROJECTCREATIONMST set KZPM_CLOSURESTAGE='P' where KZPM_KEYID='"+kzpmKeyid+"'";
			//String updatesql="Update KK_TL_FIPAPPROKZN_TL_PROJECTCREATIONMST;
			CommonMessage.debugMsg("updatesql in side the define  "+updatesql);
	
		 dbActionTemplate.executeStatement(updatesql);

		status="success";
		}
		else if(mode.equals("finance"))
		{
			String updatesql="Update KK_TL_FIPAPPROVALSLIST set CURRENTSTATUS='C' where PROJECTNO='"+kzpmKeyid+"' AND STAGE='"+trnsCode+"' AND CURRENTSTATUS='N' ";
		
			dbActionTemplate.executeStatement(updatesql);
			
			CommonMessage.debugMsg("updatesql in side the finance  "+updatesql);
			
			String updatesql1="Update KK_TL_FIPAPPROVALSLIST set MENULEVELSTATUS='C' where PROJECTNO='"+kzpmKeyid+"'  AND MENULEVELSTATUS='P' ";
			//String updatesql="Update KK_TL_FIPAPPROVALSLIST set CURRENTSTATUS='C' where PROJECTNO='"+refId+"'";
			//AND STAGE='"+trnscode+"' AND CURRENTSTATUS='N' ";
			
			dbActionTemplate.executeStatement(updatesql1);
			
			String InsertQuery1="INSERT INTO KK_TL_FIPAPPROVALSLIST VALUES ('"+kzpmKeyid+"','FIP',"+"'"+trnsCode+"','AROL0060','N','AROL0061',"+"CURRENT_TIMESTAMP"+",'"+flid+"','P','P')";
				CommonMessage.debugMsg("InsertQuery"+InsertQuery1);
			dbActionTemplate.executeStatement(InsertQuery1);
			
			
		}
		}
		catch(Exception e)
		{
			status="error";
		}
		return status;
	}
	private String getAmount(String kzpmKeyid) {
		// TODO Auto-generated method stub
		
		String sql="SELECT KZPM_BENEFITS FROM  KZN_TL_PROJECTCREATIONMST WHERE KZPM_KEYID='"+kzpmKeyid+"'";
		try {
			return dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<String[]> getProjectnewview(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			CommonMessage.debugMsg(commonFilter.getType() + " commonFilter.getType() ");
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms+="ISCLOSURE="+commonFilter.getKey()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getType())){
				condParms+="STAGE="+commonFilter.getType()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getAtype())){
				condParms+="APPROVAL="+commonFilter.getAtype()+";";
			}
			
		   if(commonFilter.getEmpch()!=null)
		   {
				condParms+="EMPNO="+commonFilter.getEmpch() +";";
		   }
		   String rolename=null;
		   
		   if(commonFilter.getEmpWise()!=null)
		   {
			   rolename=commonFilter.getEmpWise();
		   }
		   //MAIC STAGE APPROVALS FOR MENU LEVEL
		   
		   String sql1="UPDATE KZN_TL_PROJECTCREATIONMST SET KZPM_DEFINESTAGE='P' WHERE KZPM_KEYID IN (select KZPM_KEYID from KZN_TL_PROJECTCREATIONMST where KZPM_DEFINESTAGE='W') ";
		   String sql2="UPDATE KZN_TL_PROJECTCREATIONMST SET KZPM_CLOSURESTAGE='P' WHERE KZPM_KEYID IN (select KZPM_KEYID from KZN_TL_PROJECTCREATIONMST where KZPM_CLOSURESTAGE='W') ";
		   dbActionTemplate.executeStatement(sql1);
		   dbActionTemplate.executeStatement(sql2);
		   
		   List<String[]> dataList = null;
			String fiptype=commonFilter.getActwise();
			CommonMessage.debugMsg("fiptype="+commonFilter.getActwise());
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 CommonMessage.debugMsg("condParms"+condParms);
			 CommonMessage.debugMsg("commonParams"+commonParams);
			 if(fiptype.equals("DMC"))
			 {
			 dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_DMCPROJECTCREATIONMST", paramValues);
			 }
			 else
			 {
			dataList = fnCallApi.callFunction("JHN_FN_PROJECTVIEWMST_SB", paramValues, 3, false);
			 //dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_PROJECTVIEWMST", paramValues);	 
			 }
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

	@Override
	public List<String[]> getFIpCount(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		String FunctionName="TEST_PC_TEST3.FIP_FN_FIPSTAGETREND";
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		dataList =  dbActionTemplate.processFunctionCallsWithColHeaders(FunctionName, paramValues);	

		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 

	}
	@Override
	public Workbook getFIpWaveCountExportToExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter1) throws Exception{
		   ResultSet rs=null;
		   try{
				
				rs =   getFipCountesultSet(commonFilter1);
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				
				return excelUtils.writeToExcel(rs,format,2 ,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}
	private ResultSet getFipCountesultSet(CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		  String FunctionName="TEST_PC_TEST3.FIP_FN_FIPSTAGETREND";
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter1); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); 

		paramValues.add(condParms);			
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall(FunctionName, paramValues);
	}
	@Override
	public List<String[]> getFIpcountGraph(CommonFilter commonFilter,String rowId) throws Exception {
		// TODO Auto-generated method stub
		String FunctionName="TEST_PC_TEST3.FIP_FN_FIPSTAGETREND";
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		dataList =  dbActionTemplate.processFunctionCallsWithColHeaders(FunctionName, paramValues);	

		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}  
   
	@Override
	public List<String[]> getFIpWaveBenefitCount(CommonFilter commonFilter)throws Exception{
		String FunctionName="TEST_PC_TEST3.FIP_FN_FIPBENEFITTREND";
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		dataList =  dbActionTemplate.processFunctionCallsWithColHeaders(FunctionName, paramValues);	

		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}
	
	@Override
	public Workbook getFIpWaveCountExportToExcel(CommonFilter commonFilter1,JSONObject colmodel, String format) throws Exception{
		   ResultSet rs=null;
		   try{
				
				rs =   getFipBenefitesultSet(commonFilter1);
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				
				return excelUtils.writeToExcel(rs,format,2 ,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}
	private ResultSet getFipBenefitesultSet(CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		String FunctionName="TEST_PC_TEST3.FIP_FN_FIPBENEFITTREND";
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter1); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); 
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall(FunctionName, paramValues);
	}
	@Override
	public GenTlWorkflowInfo autoapproveDMAIC(GenTlWorkflowInfo genTlWorkflowInfo,
			GenTlWorkflowInfo existGenTlWorkflowInfo, String Projectleader, String KKChampion, String FIPNO)
					throws Exception {
		// TODO Auto-generated method stub
		  CommonMessage.debugMsg("hello");
		  String WrinId;
		  String WorkFlowId;
		  String KKPillarChampion;
		  WrinId=dbActionTemplate.getSingleValue("SELECT DFIW_KEYID FROM GEN_TL_DMCFIPWORKFLOW WHERE DFIW_FIPNO='"+FIPNO+"' ");
		  CommonMessage.debugMsg("WrinId"+WrinId);
		  WorkFlowId=dbActionTemplate.getSequenceNumber(TableNames.TBL_GEN_TL_WORKFLOW_INFO, 10, "WF", null,null);
	      CommonMessage.debugMsg("WrinId Keiyd"+WorkFlowId);
		  String CurrentDate=CommonFunctions.getDate();
		  CommonMessage.debugMsg("Current Date"+CurrentDate);
		  StringBuffer sql = new StringBuffer();
          sql.append(" insert into GEN_TL_WORKFLOW_INFO  (WRIN_KEYID, WRIN_WRML_KEYID, WRIN_REF_ID, WRIN_REF_TYPE, WRIN_ROLE_ID, WRIN_STATUS, WRIN_EMPLOYEE_ID, WRIN_DATE, WRIN_REMARKS, WRIN_WRKD_KEYID, WRIN_TEMPFIELD2, WRIN_TEMPFIELD3, WRIN_TEMPFIELD4, WRIN_TEMPFIELD5, WRIN_CREATEDBY, WRIN_CREATEDON, WRIN_MODIFIEDON) ");
          sql.append("Values ( '"+WorkFlowId+"','"+WrinId+"','"+FIPNO+"','PROME','AROL0059', 'A','"+Projectleader+"','"+CurrentDate+"','-', 'DMCPL',  '-', '-', '-', '-', '"+Projectleader+"','"+CurrentDate+"','"+CurrentDate+"'   )");
		  CommonMessage.debugMsg("SQL  detail:::"+sql);
		  dbActionTemplate.executeStatement(sql.toString());
		  CommonMessage.debugMsg("SQL  detail:::"+sql);
		  popupSqlForKKApproval(Projectleader,KKChampion,FIPNO,WrinId,CurrentDate);
		  return genTlWorkflowInfo;
	}
	private void popupSqlForKKApproval(String projectleader, String KKChampion, String FIPNO, String WrinId,
			String CurrentDate) throws Exception {
		// TODO Auto-generated method stub
		  String WorkFlowId;
		  StringBuffer sql = new StringBuffer();
		  CommonMessage.debugMsg("SQL  KK:::");
		  List<String> sqls = new ArrayList<String>();
		  WorkFlowId=dbActionTemplate.getSequenceNumber(TableNames.TBL_GEN_TL_WORKFLOW_INFO, 10, "WF", null,null);
		  CommonMessage.debugMsg("WrinId Keiyd"+WorkFlowId);
		  sql.append(" insert into GEN_TL_WORKFLOW_INFO  (WRIN_KEYID, WRIN_WRML_KEYID, WRIN_REF_ID, WRIN_REF_TYPE, WRIN_ROLE_ID, WRIN_STATUS, WRIN_EMPLOYEE_ID, WRIN_DATE, WRIN_REMARKS, WRIN_WRKD_KEYID, WRIN_TEMPFIELD2, WRIN_TEMPFIELD3, WRIN_TEMPFIELD4, WRIN_TEMPFIELD5, WRIN_CREATEDBY, WRIN_CREATEDON, WRIN_MODIFIEDON) ");
		  sql.append("Values ( '"+WorkFlowId+"','"+WrinId+"','"+FIPNO+"','PROME','AROL0060', 'A','"+KKChampion+"','"+CurrentDate+"','-', 'DMCFKK',  '-', '-', '-', '-', '"+KKChampion+"','"+CurrentDate+"','"+CurrentDate+"')");
		  CommonMessage.debugMsg("SQL  detail:::"+sql);
 		//  sql.append("UPDATE KZN_TL_DMCFIPCREATIONMST SET DMCM_MEASURESTAGE='C' WHERE DMCM_KEYID='"+FIPNO+"' ");	
          dbActionTemplate.executeStatement(sql.toString());	
          UpdateMeasureStage(FIPNO);		
	}
	public void UpdateMeasureStage(String FIPNO) throws BusinessApplicationExceptions, Exception{
		CommonMessage.debugMsg("UpdateMeasure"+FIPNO);
		String sql="UPDATE KZN_TL_DMCFIPCREATIONMST SET DMCM_MEASURESTAGE='C' WHERE DMCM_KEYID='"+FIPNO+"' ";	
		CommonMessage.debugMsg("UpdateMeasure"+sql);
		 dbActionTemplate.executeStatement(sql);
	}
}
