package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.WorkFlowmstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlWorkflowInfoSql;
import com.akranta.tpm.dao.sql.GenTlWorkflowdtlSql;
import com.akranta.tpm.dao.sql.KznTlMstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.dao.sql.WorkFlowmstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.GenTlWorkflowdtl;
import com.akranta.tpm.model.WorkFlowmst;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class WorkFlowmstDaoImpl implements WorkFlowmstDao {
	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	public WorkFlowmstDaoImpl(DBActionTemplate dbActionTemplate) {
		// TODO Auto-generated constructor stub
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void WorkFlowmstDaoImplJwt(String JwtToken) 
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
	public List<String[]> getMasterGrid(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//return dbActionTemplate.processFunctionCalls("adm_fn_worlfowlist", paramValues);
		return fnCallApi.callFunction("adm_fn_worlfowlist_sb", paramValues, 3, true);
	}
	
	@Override
	public List<String[]> getDetail(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		String mstkeyid = commonFilter.getKey();
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms="MasterId="+commonFilter.getKey()+";";
		}
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//return dbActionTemplate.processFunctionCalls("ADM_FN_WORKFLOWDTLS", paramValues);
		return fnCallApi.callFunction("ADM_FN_WORKFLOWDTLS_SB", paramValues, 3, true); 
	}

	@Override
	public WorkFlowmst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		WorkFlowmst workflowmst=new WorkFlowmst();//model
		String sql = WorkFlowmstSql.getWorkFlowmstSql();
		//CommonMessage.debugMsg(keyid+" sql "+sql);
		Object args [] = new Object [] {keyid};
		workflowmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		//CommonMessage.debugMsg("workflow::::::"+workflowmst.getWrkmNoofstage());
		return  workflowmst;
	}

	@Override
	public WorkFlowmst delete(WorkFlowmst workflowmst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		WorkFlowmstSql workflowmstql = new WorkFlowmstSql();
		GenTlWorkflowdtlSql genTlWorkflowdtlsql = new GenTlWorkflowdtlSql();
		try {		
			sqls.add(genTlWorkflowdtlsql.getDeleteAllSql( workflowmst.getWrkmKeyid()));
			sqls.add(WorkFlowmstSql .getDeleteSql(workflowmstql.getWrkmDbFields(), workflowmst.getSaveArray()));			
			dbActionTemplate.executeStatements(sqls);			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return workflowmst;	
	}

	@Override
	public WorkFlowmst create(WorkFlowmst genT1Workflowmst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		WorkFlowmstSql workflowmstql = new WorkFlowmstSql();// contains dbtable,field names, Field types and related sqls  of master table
		GenTlWorkflowdtl newGenTlWorkflowdtl=null;
		GenTlWorkflowdtlSql genTlWorkflowdtlsql = new GenTlWorkflowdtlSql();
		
		genT1Workflowmst.setWrkmKeyid(dbActionTemplate.getSequenceNumber(WorkFlowmstSql.TBL_GEN_TL_WORKFLOWMST, 10, "QTM", "", "")); // set the sequnce number
		sqls.add(WorkFlowmstSql.getInsertSql(workflowmstql.getWrkmDbFields(), genT1Workflowmst.getSaveArray())); // add insert sql for master table
		
		List<GenTlWorkflowdtl> genTlWorkflowdtlList=genT1Workflowmst.getWorkFlowDtls();
		for(int i=0 ;i<=genTlWorkflowdtlList.size()-1;i++){				
			newGenTlWorkflowdtl=genTlWorkflowdtlList.get(i);
			newGenTlWorkflowdtl.setWrkdWrkmKeyid(genT1Workflowmst.getWrkmKeyid());
			if (!CommonFunctions.isValidKeyId(newGenTlWorkflowdtl.getWrkdKeyid())){
				newGenTlWorkflowdtl.setWrkdKeyid(dbActionTemplate.getSequenceNumber(GenTlWorkflowdtlSql.TBL_GEN_TL_WORKFLOWDTL,10,"WFD","","")); // set the sequnce number
				//CommonMessage.debugMsg(newGenTlWorkflowdtl.getWrkdWrkmKeyid()+"  detail keyid  "+newGenTlWorkflowdtl.getWrkdKeyid());
				sqls.add(GenTlWorkflowdtlSql.getInsertSql(genTlWorkflowdtlsql.getWrkdDbFields(), newGenTlWorkflowdtl.getSaveArray()));	
			}
			else{					
				sqls.add(GenTlWorkflowdtlSql.getUpdateSql(genTlWorkflowdtlsql.getWrkdDbFields(), newGenTlWorkflowdtl.getSaveArray())); // add insert sql for master table
			}			
		}	
		
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls			
		return genT1Workflowmst;
	}

	@Override
	public WorkFlowmst update(WorkFlowmst genT1Workflowmst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		WorkFlowmstSql workflowmstql = new WorkFlowmstSql();// contains dbtable,field names, Field types and related sqls  of master table
		GenTlWorkflowdtl newGenTlWorkflowdtl=null;
		GenTlWorkflowdtlSql genTlWorkflowdtlsql = new GenTlWorkflowdtlSql();
		try {
			sqls.add(workflowmstql.getUpdateSql(workflowmstql.getWrkmDbFields(), genT1Workflowmst.getSaveArray()));
			List<GenTlWorkflowdtl> genTlWorkflowdtlList=genT1Workflowmst.getWorkFlowDtls();
			for(int i=0 ;i<=genTlWorkflowdtlList.size()-1;i++){				
				newGenTlWorkflowdtl=genTlWorkflowdtlList.get(i);
				newGenTlWorkflowdtl.setWrkdWrkmKeyid(genT1Workflowmst.getWrkmKeyid());
				if (!CommonFunctions.isValidKeyId(newGenTlWorkflowdtl.getWrkdKeyid())){
					newGenTlWorkflowdtl.setWrkdKeyid(dbActionTemplate.getSequenceNumber(GenTlWorkflowdtlSql.TBL_GEN_TL_WORKFLOWDTL)); // set the sequnce number
					//CommonMessage.debugMsg(newGenTlWorkflowdtl.getWrkdWrkmKeyid()+"  detail keyid  "+newGenTlWorkflowdtl.getWrkdKeyid());
					sqls.add(GenTlWorkflowdtlSql.getInsertSql(genTlWorkflowdtlsql.getWrkdDbFields(), newGenTlWorkflowdtl.getSaveArray()));	
				}
				else{					
					sqls.add(GenTlWorkflowdtlSql.getUpdateSql(genTlWorkflowdtlsql.getWrkdDbFields(), newGenTlWorkflowdtl.getSaveArray())); // add insert sql for master table
				}			
			}	
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}		
		return genT1Workflowmst;
	}

	@Override
	public void Deletelist(String keyid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String > sqls = new ArrayList<String>();
			//sqls.add(GenTlMomKpiLinkSql.DeleteMomRow(keyid));
			sqls.add( GenTlWorkflowdtlSql.Delete(keyid));	  
			dbActionTemplate.executeStatements(sqls);
		}
		catch(BusinessApplicationExceptions e){
			CommonMessage.debugMsg(" Message:"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch( Exception e){
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public WorkFlowmst delete(String keyid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String > sqls = new ArrayList<String>();
			//sqls.add(GenTlMomKpiLinkSql.DeleteMomRow(keyid));
			sqls.add( GenTlWorkflowdtlSql.Delete(keyid));	  
			dbActionTemplate.executeStatements(sqls);
		}
		catch(BusinessApplicationExceptions e){
			CommonMessage.debugMsg(" Message:"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return null;	
	}
	
	@Override
	public List<String[]> getAllResource(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<String[]> getWorkFlowTransData(
//			GenTlWorkflowInfo genTlWorkflowInfo,String transCode,String flId,String roleId) throws Exception {
//		
//		//String sql = GenTlWorkflowInfoSql.getWorkFlowTransSql(transCode,genTlWorkflowInfo.getWrinRefId(), genTlWorkflowInfo.getWrinRefType(),genTlWorkflowInfo.getWrinEmployeeId(),flId);
//		Character enable = genTlWorkflowInfo.getEnable();
//		String status=null;
//		String cnt=null;
//		String elementId=null;
//		String sql=null;
//		 elementId = getElementID(flId);
//		 CommonMessage.debugMsg( elementId+" elementIdelementIdelementId "+flId);
//		if(transCode.equals("BTSNOSAVIN"))
//				{
//			      status=dbActionTemplate.getSingleValue("KZN_TL_MST", "KZNM_STATUS", "KZNM_KEYID", genTlWorkflowInfo.getWrinRefId());
//				   if(status.equals("C"))
//				   {
//					   cnt=dbActionTemplate.getSingleValue("GEN_TL_WORKFLOW_INFO", "COUNT(WRIN_KEYID)", "WRIN_REF_ID", genTlWorkflowInfo.getWrinRefId());
//					   CommonMessage.debugMsg("cnt>>>"+cnt);  
//					   
//				   }
//				
//				}
//		 String singsql = " SELECT STRING_AGG(ROLE_KEYID, ',' ORDER BY ROLE_KEYID) FROM gen_tl_fnlnroleteam ";
//		singsql = singsql + " INNER JOIN ADM_TL_ROLEMST ON ROLE_KEYID = FRT_ROLE_KEYID WHERE frt_empm_keyid = '"+genTlWorkflowInfo.getWrinEmployeeId()+"' ";
//
//      String empRoleids = dbActionTemplate.getSingleValue(singsql);
//		// Added By Kiran on 06sep2023 for DHQ Location
//		//elementId=dbActionTemplate.getSingleValue("KZN_TL_MST", "KZNM_ELEMENTID", "KZNM_KEYID", genTlWorkflowInfo.getWrinRefId());
//
//		if((elementId.substring(11, 21)).equals("LCN0000005")){
//			CommonMessage.debugMsg(" In side the DHQ Location " +elementId.substring(11, 21));
//			 sql = GenTlWorkflowInfoSql.getWorkFlowTransSqlDHQ(transCode,genTlWorkflowInfo.getWrinRefId(),genTlWorkflowInfo.getWrinRefType(),genTlWorkflowInfo.getWrinEmployeeId(),enable,cnt);
//
//		} 
//		else{
//			
//			 sql = GenTlWorkflowInfoSql.getWorkFlowTransSqlPG(roleId,transCode,genTlWorkflowInfo.getWrinEmployeeId(),genTlWorkflowInfo.getWrinRefId(), genTlWorkflowInfo.getWrinRefType(),flId,enable,cnt);
//				 
//		}
//			//}
//		
//	
//		CommonMessage.debugMsg(empRoleids +" empRoleids  empRoleids");
//	
//		
//		//Object [] params = {roleId,genTlWorkflowInfo.getWrinEmployeeId(),transCode,genTlWorkflowInfo.getWrinRefId(), genTlWorkflowInfo.getWrinRefType(),genTlWorkflowInfo.getWrinEmployeeId(),roleId,flId};
//		//Object [] params = {empRoleids,genTlWorkflowInfo.getWrinEmployeeId(),transCode,genTlWorkflowInfo.getWrinRefId(), genTlWorkflowInfo.getWrinRefType(),genTlWorkflowInfo.getWrinEmployeeId(),empRoleids,flId};
//		//Object [] params1 = {transCode,genTlWorkflowInfo.getWrinRefId(), genTlWorkflowInfo.getWrinRefType(),genTlWorkflowInfo.getWrinEmployeeId(),roleId,flId};
//		
//		CommonMessage.debugMsg("transCode::"+transCode);
//		CommonMessage.debugMsg("genTlWorkflowInfo.getWrinRefId()::"+genTlWorkflowInfo.getWrinRefId());
//		CommonMessage.debugMsg("genTlWorkflowInfo.getWrinRefType()::"+genTlWorkflowInfo.getWrinRefType());
//		CommonMessage.debugMsg("flId::"+flId);
//		CommonMessage.debugMsg("roleId::"+roleId);
//		CommonMessage.debugMsg("genTlWorkflowInfo.getWrinEmployeeId()::"+genTlWorkflowInfo.getWrinEmployeeId());
//		CommonMessage.debugMsg("sql in workflow ### "+sql);
//		return dbActionTemplate.getDataList(sql);
//	}
	
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
		//return dbActionTemplate.processFunctionCalls("Gen_TL_WORKFLOWAPPROVAL", paramValues);
		//String sql = GenTlWorkflowInfoSql.getWorkFlowTransSql(transCode,genTlWorkflowInfo.getWrinRefId(), genTlWorkflowInfo.getWrinRefType(),genTlWorkflowInfo.getWrinEmployeeId(),flId);
//		Character enable = genTlWorkflowInfo.getEnable();
//		String status=null;
//		String cnt=null;
//		String elementId=null;
//		String sql=null;
//		 elementId = getElementID(flId);
//		 CommonMessage.debugMsg( elementId+" elementIdelementIdelementId "+flId);
//		if(transCode.equals("BTSNOSAVIN"))
//				{
//			      status=dbActionTemplate.getSingleValue("KZN_TL_MST", "KZNM_STATUS", "KZNM_KEYID", genTlWorkflowInfo.getWrinRefId());
//				   if(status.equals("C"))
//				   {
//					   cnt=dbActionTemplate.getSingleValue("GEN_TL_WORKFLOW_INFO", "COUNT(WRIN_KEYID)", "WRIN_REF_ID", genTlWorkflowInfo.getWrinRefId());
//					   CommonMessage.debugMsg("cnt>>>"+cnt);  
//					   
//				   }
//				
//				}
//		 String singsql = " SELECT STRING_AGG(ROLE_KEYID, ',' ORDER BY ROLE_KEYID) FROM gen_tl_fnlnroleteam ";
//		singsql = singsql + " INNER JOIN ADM_TL_ROLEMST ON ROLE_KEYID = FRT_ROLE_KEYID WHERE frt_empm_keyid = '"+genTlWorkflowInfo.getWrinEmployeeId()+"' ";
//
//      String empRoleids = dbActionTemplate.getSingleValue(singsql);
//		// Added By Kiran on 06sep2023 for DHQ Location
//		//elementId=dbActionTemplate.getSingleValue("KZN_TL_MST", "KZNM_ELEMENTID", "KZNM_KEYID", genTlWorkflowInfo.getWrinRefId());
//
//		if((elementId.substring(11, 21)).equals("LCN0000005")){
//			CommonMessage.debugMsg(" In side the DHQ Location " +elementId.substring(11, 21));
//			 sql = GenTlWorkflowInfoSql.getWorkFlowTransSqlDHQ(transCode,genTlWorkflowInfo.getWrinRefId(),genTlWorkflowInfo.getWrinRefType(),genTlWorkflowInfo.getWrinEmployeeId(),enable,cnt);
//
//		} 
//		else{
//			
//			 sql = GenTlWorkflowInfoSql.getWorkFlowTransSqlPG(roleId,transCode,genTlWorkflowInfo.getWrinEmployeeId(),genTlWorkflowInfo.getWrinRefId(), genTlWorkflowInfo.getWrinRefType(),flId,enable,cnt);
//				 
//		}
//			//}
//		
//	
//		CommonMessage.debugMsg(empRoleids +" empRoleids  empRoleids");
//	
//		
//		//Object [] params = {roleId,genTlWorkflowInfo.getWrinEmployeeId(),transCode,genTlWorkflowInfo.getWrinRefId(), genTlWorkflowInfo.getWrinRefType(),genTlWorkflowInfo.getWrinEmployeeId(),roleId,flId};
//		//Object [] params = {empRoleids,genTlWorkflowInfo.getWrinEmployeeId(),transCode,genTlWorkflowInfo.getWrinRefId(), genTlWorkflowInfo.getWrinRefType(),genTlWorkflowInfo.getWrinEmployeeId(),empRoleids,flId};
//		//Object [] params1 = {transCode,genTlWorkflowInfo.getWrinRefId(), genTlWorkflowInfo.getWrinRefType(),genTlWorkflowInfo.getWrinEmployeeId(),roleId,flId};
//		
		
//		return dbActionTemplate.getDataList(sql);
	}

	
	@Override
	public List<String[]> getdmcWorkFlowTransData(
			GenTlWorkflowInfo genTlWorkflowInfo,String transCode,String flId,String roleId) throws Exception {
		
		//String sql = GenTlWorkflowInfoSql.getWorkFlowTransSql(transCode,genTlWorkflowInfo.getWrinRefId(), genTlWorkflowInfo.getWrinRefType(),genTlWorkflowInfo.getWrinEmployeeId(),flId);
		Character enable = genTlWorkflowInfo.getEnable();
		String sql = GenTlWorkflowInfoSql.getdmcWorkFlowTransSql(genTlWorkflowInfo.getWrinRefId(),genTlWorkflowInfo.getWrinRefType(),genTlWorkflowInfo.getWrinEmployeeId(),enable);
	

		
		return dbActionTemplate.getDataList(sql);
	}
	public String getElementID(String flid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT FNLN_ELEMENTID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_KEYID= '"+flid+"'";
		CommonMessage.debugMsg(sql);
		String getElementID = dbActionTemplate.getSingleValue(sql);
	
		return getElementID;
	}
	public void saveGenTlWorkFlowInfoMst(GenTlWorkflowInfo genTlWorkflowInfo, String lastLevel, String nextRoleName, String nextRoleId ) throws Exception{
		
		List<String> sqls = new ArrayList<String>();
		GenTlWorkflowInfoSql genTlWorkflowInfoSql = new GenTlWorkflowInfoSql();    
		String refType1=genTlWorkflowInfo.getWrinRefType();
		
		CommonMessage.debugMsg(genTlWorkflowInfo.getWrinRoleId() +" IN side the Work Flow   "+lastLevel +" ..... "+ nextRoleName+"_________"+genTlWorkflowInfo.getWrinRefType());
		
		if( ! CommonFunctions.isValidKeyId(genTlWorkflowInfo.getWrinKeyid())){
			//FIPRODEF"||transCode=="FIPRODEFGE1C"||transCode=="FIPRODEFGE5L
			String keyId =null;
			String sql=null;
			
			if("A".equals(genTlWorkflowInfo.getWrinStatus())&& lastLevel.equals("Y") ){
				CommonMessage.debugMsg(" In side the last level Y");
				if (refType1.equals("PRODE")){//||refType1.equals("PRODEGE1C")||refType1.equals("PRODEGE5L")
				CommonMessage.debugMsg(" In side the If "+refType1);
//				for(int i=0;i<=4;i++){
					CommonMessage.debugMsg(" In side the for "+refType1);
					 keyId = dbActionTemplate.getSequenceNumber(TableNames.TBL_GEN_TL_WORKFLOW_INFO, 10, "WF", null,null);
					genTlWorkflowInfo.setWrinKeyid(keyId);
					CommonMessage.debugMsg(" In side the for "+refType1);
					
//					 if(i==0){
						 CommonMessage.debugMsg(" In side the If "+refType1);
						 genTlWorkflowInfo.setWrinRefType("PRODE");
						 CommonMessage.debugMsg(" In side the If "+refType1);
						 String keyid=getMenuLinkKeyid("FIPRODEF");
						 String dtlKeyid=getDetailKeyid("FIPRODEF",genTlWorkflowInfo.getWrinRoleId());
						 
					 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
						 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);

//					 }
//					 else if(i==1){
//						 CommonMessage.debugMsg(i +" In side the If "+refType1);
//						 genTlWorkflowInfo.setWrinRefType("PROME");
//						 
//						 String keyid=getMenuLinkKeyid("FIPROMEA");
//						 String dtlKeyid=getDetailKeyid("FIPROMEA",genTlWorkflowInfo.getWrinRoleId());
//					 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//						 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//
//					 }
//					 	else if(i==2){
//					 		CommonMessage.debugMsg(i +" In side the If "+refType1);
//
//							 genTlWorkflowInfo.setWrinRefType("PROAN");
//							 String keyid=getMenuLinkKeyid("FIPROANA");
//							 String dtlKeyid=getDetailKeyid("FIPROANA",genTlWorkflowInfo.getWrinRoleId());
//						 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//							 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//					 	
//					 }
//					 	else if(i==3){
//					 		CommonMessage.debugMsg(i +" In side the If "+refType1);
//					 		genTlWorkflowInfo.setWrinRefType("PROIM");
//					 		 String keyid=getMenuLinkKeyid("FIPROIMP");
//					 		 String dtlKeyid=getDetailKeyid("FIPROIMP",genTlWorkflowInfo.getWrinRoleId());
//						 		genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//							 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//					 	}
//					 	else if(i==4){
//					 		CommonMessage.debugMsg(i +" In side the If "+refType1);
//					 	
//					 		genTlWorkflowInfo.setWrinRefType("PROCO");
//					 		 String keyid=getMenuLinkKeyid("FIPROCON");
//					 		 String dtlKeyid=getDetailKeyid("FIPROCON",genTlWorkflowInfo.getWrinRoleId());
//					 		genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//							 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//							 
//					 	}
					 	
					 sql = GenTlWorkflowInfoSql.getInsertSql(genTlWorkflowInfoSql.getWrinDbFields(), genTlWorkflowInfo.getSaveArray());

					 sqls.add(sql);
					
//				}
			
			}
				else if(refType1.equals("PRODEGE1C")){ //||refType1.equals("PRODEGE5L"
					CommonMessage.debugMsg(" In side the If "+refType1);
//					for(int i=0;i<5;i++){
						CommonMessage.debugMsg(" In side the for "+refType1);
						 keyId = dbActionTemplate.getSequenceNumber(TableNames.TBL_GEN_TL_WORKFLOW_INFO, 10, "WF", null,null);
						genTlWorkflowInfo.setWrinKeyid(keyId);
						CommonMessage.debugMsg(" In side the for "+refType1);
						
//						 if(i==0){
							 CommonMessage.debugMsg(" In side the If "+refType1);
							 genTlWorkflowInfo.setWrinRefType("PRODEGE1C"); 
							 CommonMessage.debugMsg(" In side the If     "+genTlWorkflowInfo.getWrinRoleId());
							 String dtlKeyid=getDetailKeyid("FIPRODEFGE1C",genTlWorkflowInfo.getWrinRoleId());
						 		genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
							
							 String keyid=getMenuLinkKeyid("FIPRODEFGE1C");
							 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//						 }
//						 else if(i==1){
//							 CommonMessage.debugMsg(i +" In side the If "+refType1);
//							 genTlWorkflowInfo.setWrinRefType("PROME");
//							 String keyid=getMenuLinkKeyid("FIPROMEA");
//							 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//							 String dtlKeyid=getDetailKeyid("FIPROMEA",genTlWorkflowInfo.getWrinRoleId());
//						 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//
//						 }
//						 	else if(i==2){
//						 		CommonMessage.debugMsg(i +" In side the If "+refType1);
//
//								 genTlWorkflowInfo.setWrinRefType("PROAN");
//								 String keyid=getMenuLinkKeyid("FIPROANA");
//								 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//								 
//								 String dtlKeyid=getDetailKeyid("FIPROANA",genTlWorkflowInfo.getWrinRoleId());
//							 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//
//						 	
//						 }
//						 	else if(i==3){
//						 		CommonMessage.debugMsg(i +" In side the If "+refType1);
//						 		genTlWorkflowInfo.setWrinRefType("PROIM");
//						 		 String keyid=getMenuLinkKeyid("FIPROIMP");
//						 		 
//						 		 String dtlKeyid=getDetailKeyid("FIPROIMP",genTlWorkflowInfo.getWrinRoleId());
//							 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//								 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//						 	}
//						 	else if(i==4){
//						 		CommonMessage.debugMsg(i +" In side the If "+refType1);
//						 	
//						 		genTlWorkflowInfo.setWrinRefType("PROCO");
//						 		 String keyid=getMenuLinkKeyid("FIPROCON");
//								 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//								 
//								 String dtlKeyid=getDetailKeyid("FIPROCON",genTlWorkflowInfo.getWrinRoleId());
//							 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//						 	}
						 	
						 sql = GenTlWorkflowInfoSql.getInsertSql(genTlWorkflowInfoSql.getWrinDbFields(), genTlWorkflowInfo.getSaveArray());

						 CommonMessage.debugMsg("INsert Sql  :PRODEGE1C ---"+sql);
						 sqls.add(sql);
						
//					}
				
				
				}
				else if(refType1.equals("PRODEGE5L")){ //||refType1.equals("PRODEGE5L"
					CommonMessage.debugMsg(" In side the If "+refType1);
//					for(int i=0;i<=4;i++){
						CommonMessage.debugMsg(" In side the for "+refType1);
						 keyId = dbActionTemplate.getSequenceNumber(TableNames.TBL_GEN_TL_WORKFLOW_INFO, 10, "WF", null,null);
						genTlWorkflowInfo.setWrinKeyid(keyId);
						CommonMessage.debugMsg(" In side the for "+refType1);
						
//						 if(i==0){
							 CommonMessage.debugMsg(" In side the If "+refType1);
							 genTlWorkflowInfo.setWrinRefType("PRODEGE5L");
							 CommonMessage.debugMsg(" In side the If "+refType1);
							 String keyid=getMenuLinkKeyid("FIPRODEFGE5L");
							 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
							 String dtlKeyid=getDetailKeyid("FIPRODEFGE5L" ,genTlWorkflowInfo.getWrinRoleId());
						 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//						 }
//						 else if(i==1){
//							 CommonMessage.debugMsg(i +" In side the If "+refType1);
//							 genTlWorkflowInfo.setWrinRefType("PROME");
//							 String keyid=getMenuLinkKeyid("FIPROMEA");
//							 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//							 String dtlKeyid=getDetailKeyid("FIPROMEA",genTlWorkflowInfo.getWrinRoleId());
//						 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//
//						 }
//						 	else if(i==2){
//						 		CommonMessage.debugMsg(i +" In side the If "+refType1);
//
//								 genTlWorkflowInfo.setWrinRefType("PROAN");
//								 String keyid=getMenuLinkKeyid("FIPROANA");
//								 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//								 
//								 String dtlKeyid=getDetailKeyid("FIPROANA",genTlWorkflowInfo.getWrinRoleId());
//							 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//						 	
//						 }
//						 	else if(i==3){
//						 		CommonMessage.debugMsg(i +" In side the If "+refType1);
//						 		genTlWorkflowInfo.setWrinRefType("PROIM");
//						 		 String keyid=getMenuLinkKeyid("FIPROIMP");
//								 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//								 String dtlKeyid=getDetailKeyid("FIPROIMP",genTlWorkflowInfo.getWrinRoleId());
//							 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//						 	}
//						 	else if(i==4){
//						 		CommonMessage.debugMsg(i +" In side the If "+refType1);
//						 	
//						 		genTlWorkflowInfo.setWrinRefType("PROCO");
//						 		 String keyid=getMenuLinkKeyid("FIPROCON");
//								 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
//								 String dtlKeyid=getDetailKeyid("FIPROCON",genTlWorkflowInfo.getWrinRoleId());
//							 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
//						 	}
						 	
						 sql = GenTlWorkflowInfoSql.getInsertSql(genTlWorkflowInfoSql.getWrinDbFields(), genTlWorkflowInfo.getSaveArray());

						 CommonMessage.debugMsg("INsert Sql  PRODEGE5L  :---"+sql);
						 sqls.add(sql);
						
//					}
				
				
				}
				else if(refType1.equals("PROCL")||refType1.equals("PROCLGE5L")||refType1.equals("PROCLGE1C")){
					
				if(refType1.equals("PROCL")){
					genTlWorkflowInfo.setWrinRefType("PROCL");
					
					 String keyid=getMenuLinkKeyid("FIPROCLO");
					 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
					 String dtlKeyid=getDetailKeyid("FIPROCLO",genTlWorkflowInfo.getWrinRoleId());
				 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
				}
				else if(refType1.equals("PROCLGE5L")){
					genTlWorkflowInfo.setWrinRefType("PROCLGE5L");
					String keyid=getMenuLinkKeyid("FIPROCLOGE5L");
					 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
					 String dtlKeyid=getDetailKeyid("FIPROCLOGE5L",genTlWorkflowInfo.getWrinRoleId());
				 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
				}
				else {
					genTlWorkflowInfo.setWrinRefType("PROCLGE1C");
					String keyid=getMenuLinkKeyid("FIPROCLOGE1C");
					 String dtlKeyid=getDetailKeyid("FIPROCLOGE1C",genTlWorkflowInfo.getWrinRoleId());
				 	 genTlWorkflowInfo.setWrinWrkdKeyid(dtlKeyid);
					 genTlWorkflowInfo.setWrinWrmlKeyid(keyid);
				}
							 keyId = dbActionTemplate.getSequenceNumber(TableNames.TBL_GEN_TL_WORKFLOW_INFO, 10, "WF", null,null);
							genTlWorkflowInfo.setWrinKeyid(keyId);
							CommonMessage.debugMsg(" In side the for "+refType1);
				
							
								 sql = GenTlWorkflowInfoSql.getInsertSql(genTlWorkflowInfoSql.getWrinDbFields(), genTlWorkflowInfo.getSaveArray());

								 CommonMessage.debugMsg("INsert Sql  PROCL  :---"+sql);
								 sqls.add(sql);
				}
				// --------------Vignesh for DMT APPOVAL 18Oct2025 ----------- //
				else {
		            // ✅ DEFAULT for refTypes not explicitly expanded (e.g., OPL, KZN)
		            keyId = dbActionTemplate.getSequenceNumber(
		                    TableNames.TBL_GEN_TL_WORKFLOW_INFO, 10, "WF", null, null);
		            genTlWorkflowInfo.setWrinKeyid(keyId);

		            // IMPORTANT: assign to the OUTER 'sql' (don’t re-declare a new local)
		            sql = GenTlWorkflowInfoSql.getInsertSql(
		                    genTlWorkflowInfoSql.getWrinDbFields(),
		                    genTlWorkflowInfo.getSaveArray());

		            sqls.add(sql);
		            CommonMessage.debugMsg("Inserted last-level workflow row for refType=" + refType1 + " key=" + keyId);
		        }
				// --------------Vignesh for DMT APPOVAL 18Oct2025 ----------- //
			}
			else{
				
			CommonMessage.debugMsg(" IN side the Else part of Workflow save  "+genTlWorkflowInfo.getWrinStatus());
			 keyId = dbActionTemplate.getSequenceNumber(TableNames.TBL_GEN_TL_WORKFLOW_INFO, 10, "WF", null,null);
			genTlWorkflowInfo.setWrinKeyid(keyId);
			 sql = GenTlWorkflowInfoSql.getInsertSql(genTlWorkflowInfoSql.getWrinDbFields(), genTlWorkflowInfo.getSaveArray());
			 sqls.add(sql);
			}
			CommonMessage.debugMsg(sql);
			
			if("E".equals(genTlWorkflowInfo.getWrinStatus())){ // IF Rework Update all bellow level to rework;
				sqls.add(GenTlWorkflowInfoSql.getReworkSatusUpdateSql(genTlWorkflowInfo.getWrinRefId(),genTlWorkflowInfo.getWrinWrmlKeyid(), genTlWorkflowInfo.getWrinRefType()));
				String uSql  = " DELETE FROM  ADM_APPROVALS_LIST WHERE DOCUMENTNO = '" + genTlWorkflowInfo.getWrinRefId() + "'";
				sqls.add(uSql);
			}
			/*************** For Inbox Approval Items to Refresh ******************/
			else {
				if( CommonFunctions.isValidKeyId(genTlWorkflowInfo.getNextEmpId())){
					String uSql = " UPDATE ADM_APPROVALS_LIST SET APPROVALEMPID = '"+genTlWorkflowInfo.getNextEmpId() + "' WHERE DOCUMENTNO = '" + genTlWorkflowInfo.getWrinRefId() + "'";
					sqls.add(uSql);
				}
				else  //if( genTlWorkflowInfo.)
				{
					String uSql  = " DELETE FROM  ADM_APPROVALS_LIST WHERE DOCUMENTNO = '" + genTlWorkflowInfo.getWrinRefId() + "'";
					sqls.add(uSql);
				}	
			}
			/*************** For Inbox Approval Items to Refresh ******************/
			
			dbActionTemplate.executeStatements(sqls);
		}
		
		else {
			String sql = GenTlWorkflowInfoSql.getUpdateSql(genTlWorkflowInfoSql.getWrinDbFields(), genTlWorkflowInfo.getSaveArray());
			CommonMessage.debugMsg(sql );
			sqls.add(sql);
			if("E".equals(genTlWorkflowInfo.getWrinStatus())){ // IF Rework Update all bellow level to rework;
				sqls.add(GenTlWorkflowInfoSql.getReworkSatusUpdateSql(genTlWorkflowInfo.getWrinRefId(),genTlWorkflowInfo.getWrinWrmlKeyid(),genTlWorkflowInfo.getWrinRefType()));
				String uSql  = " DELETE FROM  ADM_APPROVALS_LIST WHERE DOCUMENTNO = '" + genTlWorkflowInfo.getWrinRefId() + "'";
				sqls.add(uSql);
			}
			/*************** For Inbox Approval Items to Refresh ******************/
			else {
				if( CommonFunctions.isValidKeyId(genTlWorkflowInfo.getNextEmpId())){
					String uSql = " UPDATE ADM_APPROVALS_LIST SET APPROVALEMPID = '"+genTlWorkflowInfo.getNextEmpId() + "' WHERE DOCUMENTNO = '" + genTlWorkflowInfo.getWrinRefId() + "'";
					sqls.add(uSql);
				}
				else  //if( genTlWorkflowInfo.)
				{
					String uSql  = " DELETE FROM  ADM_APPROVALS_LIST WHERE DOCUMENTNO = '" + genTlWorkflowInfo.getWrinRefId() + "'";
					sqls.add(uSql);
				}	
			}
			/*************** For Inbox Approval Items to Refresh ******************/
			dbActionTemplate.executeStatements(sqls);
		}
		
		if (UIUtils.isValidKeyId(genTlWorkflowInfo.getWrinRefType()) 
				&& genTlWorkflowInfo.getWrinRefType().length()>2) {
			String refType = genTlWorkflowInfo.getWrinRefType().substring(0,3);
			//CommonMessage.debugMsg("refType="+refType);
			if(refType.equals("KZN") || refType.equals("OPL"))
				updateOPLKaizen(genTlWorkflowInfo, lastLevel, nextRoleName, nextRoleId, refType );
		}
		
		genTlWorkflowInfoSql = null;
		
	}
	
	private String getDetailKeyid(String transCode,String roleId) {
		// TODO Auto-generated method stub
		
		String sql="SELECT WRKD_KEYID from GEN_TL_WORKFLOWDTL  WHERE WRKD_WRKM_KEYID =(SELECT WRML_WRKM_KEYID FROM GEN_TL_WORKFLOW_MENU_LINK WHERE WRML_TRANS_CODE='"+transCode+"') AND WRKD_STAGE='"+roleId+"' ";

		CommonMessage.debugMsg(" In side the Details Keyid  FIPRODEFGE1C");
		try {
			return dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String getMenuLinkKeyid(String transCode) throws Exception {
		// TODO Auto-generated method stub
		String sql="SELECT WRML_KEYID FROM GEN_TL_WORKFLOW_MENU_LINK WHERE WRML_TRANS_CODE ='"+transCode+"' ";
		return dbActionTemplate.getSingleValue(sql);
	}

	public void savedmcGenTlWorkFlowInfoMst(GenTlWorkflowInfo genTlWorkflowInfo
			, String lastLevel, String nextRoleName, String nextRoleId ) throws Exception{
		
		List<String> sqls = new ArrayList<String>();
		GenTlWorkflowInfoSql genTlWorkflowInfoSql = new GenTlWorkflowInfoSql();    
		if( ! CommonFunctions.isValidKeyId(genTlWorkflowInfo.getWrinKeyid())){
			String keyId = dbActionTemplate.getSequenceNumber(TableNames.TBL_GEN_TL_WORKFLOW_INFO, 10, "WF", null,null);
			genTlWorkflowInfo.setWrinKeyid(keyId);
			String sql = GenTlWorkflowInfoSql.getInsertSql(genTlWorkflowInfoSql.getWrinDbFields(), genTlWorkflowInfo.getSaveArray());
			CommonMessage.debugMsg(sql );
			sqls.add(sql);
			if("E".equals(genTlWorkflowInfo.getWrinStatus())){ // IF Rework Update all bellow level to rework;
				sqls.add(GenTlWorkflowInfoSql.getReworkSatusUpdateSql(genTlWorkflowInfo.getWrinRefId(),genTlWorkflowInfo.getWrinWrmlKeyid(), genTlWorkflowInfo.getWrinRefType()));
				String uSql  = " DELETE FROM  ADM_APPROVALS_LIST WHERE DOCUMENTNO = '" + genTlWorkflowInfo.getWrinRefId() + "'";
				sqls.add(uSql);
			}
			/*************** For Inbox Approval Items to Refresh ******************/
			else {
				if( CommonFunctions.isValidKeyId(genTlWorkflowInfo.getNextEmpId())){
					String uSql = " UPDATE ADM_APPROVALS_LIST SET APPROVALEMPID = '"+genTlWorkflowInfo.getNextEmpId() + "' WHERE DOCUMENTNO = '" + genTlWorkflowInfo.getWrinRefId() + "'";
					sqls.add(uSql);
				}
				else  //if( genTlWorkflowInfo.)
				{
					String uSql  = " DELETE FROM  ADM_APPROVALS_LIST WHERE DOCUMENTNO = '" + genTlWorkflowInfo.getWrinRefId() + "'";
					sqls.add(uSql);
				}	
			}
			/*************** For Inbox Approval Items to Refresh ******************/
			
			dbActionTemplate.executeStatements(sqls);
		}
		else {
			String sql = GenTlWorkflowInfoSql.getUpdateSql(genTlWorkflowInfoSql.getWrinDbFields(), genTlWorkflowInfo.getSaveArray());
			CommonMessage.debugMsg(sql );
			sqls.add(sql);
			if("E".equals(genTlWorkflowInfo.getWrinStatus())){ // IF Rework Update all bellow level to rework;
				sqls.add(GenTlWorkflowInfoSql.getReworkSatusUpdateSql(genTlWorkflowInfo.getWrinRefId(),genTlWorkflowInfo.getWrinWrmlKeyid(),genTlWorkflowInfo.getWrinRefType()));
				String uSql  = " DELETE FROM  ADM_APPROVALS_LIST WHERE DOCUMENTNO = '" + genTlWorkflowInfo.getWrinRefId() + "'";
				sqls.add(uSql);
			}
			/*************** For Inbox Approval Items to Refresh ******************/
			else {
				if( CommonFunctions.isValidKeyId(genTlWorkflowInfo.getNextEmpId())){
					String uSql = " UPDATE ADM_APPROVALS_LIST SET APPROVALEMPID = '"+genTlWorkflowInfo.getNextEmpId() + "' WHERE DOCUMENTNO = '" + genTlWorkflowInfo.getWrinRefId() + "'";
					sqls.add(uSql);
				}
				else  //if( genTlWorkflowInfo.)
				{
					String uSql  = " DELETE FROM  ADM_APPROVALS_LIST WHERE DOCUMENTNO = '" + genTlWorkflowInfo.getWrinRefId() + "'";
					sqls.add(uSql);
				}	
			}
			/*************** For Inbox Approval Items to Refresh ******************/
			dbActionTemplate.executeStatements(sqls);
		}
		
		if (UIUtils.isValidKeyId(genTlWorkflowInfo.getWrinRefType()) 
				&& genTlWorkflowInfo.getWrinRefType().length()>2) {
			String refType = genTlWorkflowInfo.getWrinRefType().substring(0,3);
			//CommonMessage.debugMsg("refType="+refType);
			if(refType.equals("KZN") || refType.equals("OPL"))
				updateOPLKaizen(genTlWorkflowInfo, lastLevel, nextRoleName, nextRoleId, refType );
		}
		
		genTlWorkflowInfoSql = null;
	}
	
	public void updateOPLKaizen(GenTlWorkflowInfo genTlWorkflowInfo
			, String lastLevel, String nextRoleName, String nextRoleId , String refType) throws Exception{
		
			CommonMessage.debugMsg("refType===="+refType);
			
			String wfStatus=genTlWorkflowInfo.getWrinStatus();
			CommonMessage.debugMsg("status=="+wfStatus);
			String status = "";
		
			if(("A".equals(wfStatus) ||"E".equals(wfStatus)||"R".equals(wfStatus)) )
			{//&& lastLevel == "Y"){
			
			     if("A".equals(wfStatus) &&  "Y".equals(lastLevel))
			    	 status="C";
			     else if("A".equals(wfStatus))
			    	 status="A";
			     else if("E".equals(wfStatus)) {
			    	 status="E";
			    	 nextRoleName="REWORK";
			     }else if("R".equals(wfStatus)) {
			    	 status="R";
		     }
			     
		     if(!UIUtils.isValidKeyId(nextRoleName))
		    	 nextRoleName="-";
	
		     CommonMessage.debugMsg("status=="+status);
		     String keyid=genTlWorkflowInfo.getWrinRefId();
		     //mand - status, approvallevel, keyid
		     String sql="";
		     if(refType.equals("KZN") ) 
		    	 sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='"+status+"',KZNM_APROV_LEVEL='"+nextRoleName+"' WHERE KZNM_KEYID='"+keyid+"' ";
		     else if (refType.equals("OPL"))
		     	sql= " UPDATE  OPL_TL_MST  SET OPLM_STATUS ='"+status+"', OPLM_APROV_LEVEL='"+nextRoleName+"' WHERE OPLM_KEYID ='"+keyid+"' ";
		    	 
		     CommonMessage.debugMsg("Kaizenypdate==="+sql);
		     
		     if(refType.equals("KZN") || refType.equals("OPL"))
		    	 dbActionTemplate.executeStatement(sql);
		}
	}			
}	

	
	
	
	
	
	