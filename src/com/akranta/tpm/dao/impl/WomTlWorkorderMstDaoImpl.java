package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.WomTlWorkorderMstDao;
import com.akranta.tpm.dao.sql.GenTlActionplandtlSql;
import com.akranta.tpm.dao.sql.GenTlActionplanmstSql;
import com.akranta.tpm.dao.sql.WomTlWorkorderMstSql;
import com.akranta.tpm.dao.sql.WomsTlTaskmstSql;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.WomTlWorkorderMst;
import com.akranta.tpm.model.WomsTlTaskmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class WomTlWorkorderMstDaoImpl implements WomTlWorkorderMstDao {


	private DBActionTemplate dbActionTemplate; 

	public WomTlWorkorderMstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public WomTlWorkorderMst create(WomTlWorkorderMst womTlWorkorderMst, WomsTlTaskmst womsTlTaskmst ) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		WomTlWorkorderMstSql womTlWorkorderMstSql = new WomTlWorkorderMstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		
			String flid = womTlWorkorderMst.getWomsFlid();
			
			String elementId = getElementID(flid);
						
			String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,WomTlWorkorderMstSql.TBL_WOM_TL_WORKORDER_MST);

			String locId ="";
			if( elementId != null && elementId.length() > 10  ){
				locId = elementId.substring(11, 21); /* location id starts from 11  */
		 	}
			String location = dbActionTemplate.getSingleValue( " select decode(substr(locn_name,1,2),'BO','L',substr(locn_name,1,1)) from gen_tl_locationmst where locn_keyid='"+locId+"' ");
			/// change ths line///a
			CommonMessage.debugMsg("seqIdentfi=="+seqIdentfi);
			womTlWorkorderMst.setWomsKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,12,"W"+location,"MMYY","Y")); 

			//womTlWorkorderMst.setWomsKeyid(dbActionTemplate.getSequenceNumber(WomTlWorkorderMstSql.TBL_WOM_TL_WORKORDER_MST, 12, "W", "MMYY", "Y"));  
			
			womTlWorkorderMst.setWomsOrderno(womTlWorkorderMst.getWomsKeyid());
			
			sqls.add(WomTlWorkorderMstSql.getInsertSql(womTlWorkorderMstSql.getWomsDbFields(), womTlWorkorderMst.getSaveArray())); // add insert sql for master table
			
			checkLoadTaskData(sqls,womTlWorkorderMst, womsTlTaskmst );
			CommonMessage.debugMsg("sql.size=="+sqls.size());
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			String womsId = womTlWorkorderMst.getWomsKeyid();
		//	submitMorderToSap(womsId,type);
			//dbActionTemplate.processPLSQLProcedures("SAP_NOTI_TRANSFER.insert_ORDER_TO_SAP", WomsKeyid, null);
		
		return womTlWorkorderMst;
	}
	
	
	public WomTlWorkorderMst update(WomTlWorkorderMst womTlWorkorderMst, WomsTlTaskmst womsTlTaskmst )	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		WomTlWorkorderMstSql womTlWorkorderMstSql = new WomTlWorkorderMstSql();
		
			
		womTlWorkorderMst.setWomsOrderno(womTlWorkorderMst.getWomsKeyid());
			sqls.add(WomTlWorkorderMstSql.getUpdateSql(womTlWorkorderMstSql.getWomsDbFields(), womTlWorkorderMst.getSaveArray()));
			
			//List<String> WomsKeyid=new ArrayList<String>();
			//WomsKeyid.add(womTlWorkorderMst.getWomsKeyid());
			//add task list if notification change to workorder 
			
			CommonMessage.debugMsg("checkLoadTaskData");
			checkLoadTaskData(sqls,womTlWorkorderMst, womsTlTaskmst );
			
			CommonMessage.debugMsg("sql.size=="+sqls.size());
			dbActionTemplate.executeStatements(sqls);
			String womsId = womTlWorkorderMst.getWomsKeyid();
			//submitMorderToSap(womsId);
			//dbActionTemplate.processPLSQLProcedures("SAP_NOTI_TRANSFER.insert_ORDER_TO_SAP", WomsKeyid, null);
		return womTlWorkorderMst;
	}
	
	private void checkLoadTaskData(List<String> sqls, WomTlWorkorderMst womTlWorkorderMst,
			WomsTlTaskmst womsTlTaskmst ) throws Exception {
		WomsTlTaskmstSql womsTlTaskmstSql = new WomsTlTaskmstSql();
		CommonMessage.debugMsg("taskcnt:inside checkLoad");
		//if (!womTlWorkorderMst.getWomsSaporderFlag().equals("Y"))			// line to decide whether task need to generate if notification is created
			//return ;
		
		String sql = new String();
		sql = "SELECT COUNT(*) FROM WOMS_TL_TASKMST WHERE WTMS_WOMS_KEYID='"+womTlWorkorderMst.getWomsKeyid()+"' ";
		String taskcnt = dbActionTemplate.getSingleValue(sql);

		CommonMessage.debugMsg("taskcnt"+taskcnt);
		
		if (Integer.parseInt(taskcnt)==0) { 

			womsTlTaskmst.setWtmsWomsKeyid(womTlWorkorderMst.getWomsKeyid());
			womsTlTaskmst.setWtmsTask(womTlWorkorderMst.getWomsProblem());
			womsTlTaskmst.setWtmsCreatedby(womTlWorkorderMst.getWomsCreatedby());
			
			//womsTlTaskmst.setWtmsActivityno("0010");
			
			String wtmsKeyid =dbActionTemplate.getSequenceNumber(WomsTlTaskmstSql.TBL_WOMS_TL_TASKMST,12,"WTM","MMYY","Y");
			womsTlTaskmst.setWtmsKeyid(wtmsKeyid);
		
			CommonMessage.debugMsg("womsTlTaskmst.getWtmsWomsKeyid()"+womsTlTaskmst.getWtmsWomsKeyid());
			String lineNo=getLineNo("WOMS_TL_TASKMST", womsTlTaskmst.getWtmsWomsKeyid() , "WTMS_WOMS_KEYID");
			if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsActivityno()))
				womsTlTaskmst.setWtmsActivityno(lineNo);
			else
				womsTlTaskmst.setWtmsActivityno("0010");
			
			CommonMessage.debugMsg("sql.cnt"+sqls.size());
			
			sqls.add(WomsTlTaskmstSql.getInsertSql(womsTlTaskmstSql.getWtmsDbFields(), womsTlTaskmst.getSaveArray()));
			
			return;
		}

	}
	
	public WomTlWorkorderMst delete(WomTlWorkorderMst womTlWorkorderMst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		WomTlWorkorderMstSql womTlWorkorderMstSql = new WomTlWorkorderMstSql();
		try {
			
			String allowDelete=allowUpdate(womTlWorkorderMst.getWomsKeyid(),"");
			if(allowDelete.equals("true")){
			sqls.add(womTlWorkorderMstSql.getDeleteSql(womTlWorkorderMstSql.getWomsDbFields(), womTlWorkorderMst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
			List<String> WomsKeyid=new ArrayList<String>();
			WomsKeyid.add(womTlWorkorderMst.getWomsKeyid());
			dbActionTemplate.processPLSQLProcedures("SAP_NOTI_TRANSFER.insert_ORDER_TO_SAP", WomsKeyid, null);
			
			String womsId = womTlWorkorderMst.getWomsKeyid();
		//	submitMorderToSap(womsId);
			}
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlWorkorderMst;
	}
	
	public String deleteWO(WomTlWorkorderMst womTlWorkorderMst) throws Exception {
					String msg="";
					List<String> sqls = new ArrayList<String>();
					WomTlWorkorderMstSql womTlWorkorderMstSql = new WomTlWorkorderMstSql();
					try {
						
						String allowDelete=allowUpdate(womTlWorkorderMst.getWomsKeyid(),"");
						if(allowDelete.equals("true")){
						sqls.add(womTlWorkorderMstSql.getDeleteSql(womTlWorkorderMstSql.getWomsDbFields(), womTlWorkorderMst.getSaveArray()));
					
						dbActionTemplate.executeStatements(sqls);
						
						List<String> WomsKeyid=new ArrayList<String>();
						WomsKeyid.add(womTlWorkorderMst.getWomsKeyid());
						dbActionTemplate.processPLSQLProcedures("SAP_NOTI_TRANSFER.insert_ORDER_TO_SAP", WomsKeyid, null);
						
						String womsId = womTlWorkorderMst.getWomsKeyid();
						 msg="Success";
					//	submitMorderToSap(womsId);
						}
						else {
							msg="Failed";
						}
						return msg;
					}catch( Exception e){
						throw new Exception(e.getMessage());
					}
					
}

	public WomsTlTaskmst createTaskList(List<WomsTlTaskmst> womsTlTaskmstList) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		
		CommonMessage.debugMsg("Dao Impl......");
		
		WomsTlTaskmst womsTlTaskmstt = new WomsTlTaskmst();
		
		CommonMessage.debugMsg(" kaizenFormBean.getKaizenBankList().size() " + womsTlTaskmstList.size());
		
		if(womsTlTaskmstList!= null && womsTlTaskmstList.size()>0) // check for detail table data
			{
			CommonMessage.debugMsg("inside");
			String lineNo="";
		    	for(int i =0;i<womsTlTaskmstList.size();i++)
				{	
		    		WomsTlTaskmst womsTlTaskmst = (WomsTlTaskmst)womsTlTaskmstList.get(i); // get detail info from list in empployee object
		    		
		    		CommonMessage.debugMsg("womsTlTaskmst.idd"+womsTlTaskmst.getWtmsKeyid());
		    		CommonMessage.debugMsg("womsTlTaskmst.womsidd"+womsTlTaskmst.getWtmsWomsKeyid());
		    		
		    		StringBuffer sqlUpdt = new StringBuffer();
		    		WomsTlTaskmstSql womsTlTaskmstSql = new WomsTlTaskmstSql();
		    		String actionPlanid = womsTlTaskmst.getWtmsActionplanid();
		    	   if(actionPlanid.length()>=10)	
		    		womsTlTaskmst.setWtmsActionplanid(actionPlanid.substring(0,12));
		    	   else
		    		   womsTlTaskmst.setWtmsActionplanid("-");
		    		String sql = "";
		    		if(!UIUtils.isValidKeyId( womsTlTaskmst.getWtmsKeyid()) ) {
		    			String wtmsKeyid =dbActionTemplate.getSequenceNumber(WomsTlTaskmstSql.TBL_WOMS_TL_TASKMST,12,"WTM","MMYY","Y");
		    			womsTlTaskmst.setWtmsKeyid(wtmsKeyid);
		    				lineNo=getLineNo("WOMS_TL_TASKMST", womsTlTaskmst.getWtmsWomsKeyid() , "WTMS_WOMS_KEYID");
		    				CommonMessage.debugMsg("Line no for task inside dao impl: "+lineNo);
		    				if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsActivityno()))
		    					womsTlTaskmst.setWtmsActivityno(lineNo);
		    				else
		    					womsTlTaskmst.setWtmsActivityno("0010");
		    			sql = WomsTlTaskmstSql.getInsertSql(womsTlTaskmstSql.getWtmsDbFields(), womsTlTaskmst.getSaveArray());
		    		}
		    		else {
		    			//lineNo=getLineNo("WOMS_TL_TASKMST", womsTlTaskmst.getWtmsWomsKeyid() , "WTMS_WOMS_KEYID");
    					//CommonMessage.debugMsg("Line no for task inside dao impl update: "+lineNo+": Activity no:"+womsTlTaskmst.getWtmsActivityno());
    					//	if(!UIUtils.isValidKeyId(womsTlTaskmst.getWtmsActivityno()) && !UIUtils.isValidKeyId( womsTlTaskmst.getWtmsKeyid()))
    					//			womsTlTaskmst.setWtmsActivityno(lineNo);
		    			sql = WomsTlTaskmstSql.getUpdateSql(womsTlTaskmstSql.getWtmsDbFields(), womsTlTaskmst.getSaveArray());
		    		}
		    		
		    		sqls.add(sql);
		    		
		    		womsTlTaskmst.setWtmsActionplanid(actionPlanid);
		    		
		    		CommonMessage.debugMsg("sqlypdt"+sqlUpdt.toString());
		    		
		    		//insert or update action plan details
		    		setActionplanDetails(sqls,womsTlTaskmst);
		    		//sqls.add(actSql);
		    		
		    		womsTlTaskmstt=womsTlTaskmst;
				}
		    	
		    	dbActionTemplate.executeStatements(sqls);
		    	
		    	String womsId = womsTlTaskmstt.getWtmsWomsKeyid();
		    	//submitMorderToSap(womsId);
			}
			
		return womsTlTaskmstt;
	}	

	public void setActionplanDetails(List<String> sqls, WomsTlTaskmst womsTlTaskmst) throws Exception {
		
		String sql = "";
		String dateTime = CommonFunctions.dateTimeNow();
		
		GenTlActionplanmst genTlActionplanmst = new GenTlActionplanmst();
		GenTlActionplandtl genTlActionplandtl = new GenTlActionplandtl();
		GenTlActionplanmstSql genTlActionplanmstSql = new GenTlActionplanmstSql();
		GenTlActionplandtlSql genTlActionplandtlSql = new GenTlActionplandtlSql();
		
		sql = " SELECT woms_flid from wom_tl_workorder_mst where woms_keyid='"+womsTlTaskmst.getWtmsWomsKeyid()+"' ";
		String womsFlid = dbActionTemplate.getSingleValue(sql);
		
		genTlActionplanmst.setAplmMasterrefid(womsTlTaskmst.getWtmsWomsKeyid());
		genTlActionplanmst.setAplmDetailrefid(womsTlTaskmst.getWtmsKeyid());
		genTlActionplanmst.setAplmCreatedby(womsTlTaskmst.getWtmsCreatedby());
		genTlActionplanmst.setAplmCreatedon(dateTime);
		genTlActionplanmst.setAplmElementid("-");
		genTlActionplanmst.setAplmFlid(womsFlid);
		genTlActionplanmst.setAplmMaintask( womsTlTaskmst.getWtmsTask() + " -- " + womsTlTaskmst.getWtmsObservation() );
		genTlActionplanmst.setAplmModifiedon(dateTime);
		genTlActionplanmst.setAplmPillarid("{}");
		genTlActionplanmst.setAplmPlandate(dateTime);
		genTlActionplanmst.setAplmRefdoctype("WTS");
		genTlActionplanmst.setAplmRemarks("-");
		genTlActionplanmst.setAplmStatus("P");
		
		genTlActionplanmst.setAplmActive("Y");
		
		genTlActionplanmst.setAplmTempfiled2("-");
		genTlActionplanmst.setAplmTempfiled3("-");
		genTlActionplanmst.setAplmTempfiled4("-");
		genTlActionplanmst.setAplmTempfiled5("-");
		
		String aplmKeyid = "";
		String apldKeyid = "";
		
		String actionId = womsTlTaskmst.getWtmsActionplanid();
		CommonMessage.debugMsg("actionId"+actionId);
		String[] actIds = actionId.split("-") ;
		if(actIds.length>1) {
			aplmKeyid = actIds[0];
			apldKeyid = actIds[1];
		}
		else {
			aplmKeyid = actionId;
		}
		
		if (UIUtils.isValidKeyId(aplmKeyid)) {
			genTlActionplanmst.setAplmKeyid(aplmKeyid);
			sql = GenTlActionplanmstSql.getUpdateSql(genTlActionplanmstSql.getAplmDbFields(),genTlActionplanmst.getSaveArray());
		}
		else {
			aplmKeyid = dbActionTemplate.getSequenceNumber("GEN_TL_ACTIONPLANMST");
			genTlActionplanmst.setAplmKeyid(aplmKeyid);
			sql = GenTlActionplanmstSql.getInsertSql(genTlActionplanmstSql.getAplmDbFields(),genTlActionplanmst.getSaveArray());
		}
		
		sqls.add(sql);
		
		genTlActionplandtl.setApldActionplan(womsTlTaskmst.getWtmsActionTaken());
		genTlActionplandtl.setApldActive("Y");
		
		genTlActionplandtl.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
		
		genTlActionplandtl.setApldCompleatedon(Constants.futureNullDate);
		genTlActionplandtl.setApldCompletedby("{}");
		genTlActionplandtl.setApldCountermeasure("{}");
		genTlActionplandtl.setApldCreatedby("{}");
		genTlActionplandtl.setApldCreatedon(dateTime);
		genTlActionplandtl.setApldHowtodo("{}");
		genTlActionplandtl.setApldModifiedon(dateTime);
		genTlActionplandtl.setApldOthers("-");
		genTlActionplandtl.setApldRemarks("{}");
		genTlActionplandtl.setApldResponsibility(womsTlTaskmst.getWtmsResponsibility());
		genTlActionplandtl.setApldStatus("P");
		genTlActionplandtl.setApldTargetdate(womsTlTaskmst.getWtmsTargetDate());
		genTlActionplandtl.setApldTempfiled2("-");
		genTlActionplandtl.setApldTempfiled3("-");
		genTlActionplandtl.setApldTempfiled4("-");
		genTlActionplandtl.setApldTempfiled5("-");
		genTlActionplandtl.setApldTradeid("{}");

		if (UIUtils.isValidKeyId( apldKeyid)) {
			genTlActionplandtl.setApldKeyid(apldKeyid);
			sql = GenTlActionplandtlSql.getUpdateSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtl.getSaveArray());
		}
		else {
			apldKeyid = dbActionTemplate.getSequenceNumber("GEN_TL_ACTIONPLANDTL");
			genTlActionplandtl.setApldKeyid(apldKeyid);
			sql = GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtl.getSaveArray());
		}

		sqls.add(sql);
		
		
		sqls.add(" UPDATE WOMS_TL_TASKMST SET WTMS_ACTIONPLANID ='"+genTlActionplanmst.getAplmKeyid()+"' WHERE WTMS_KEYID='"+womsTlTaskmst.getWtmsKeyid()+"' ");
		
		return ;
	}
	
	public WomTlWorkorderMst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		WomTlWorkorderMst womTlWorkorderMst = new WomTlWorkorderMst();
		String sql = WomTlWorkorderMstSql.selectWO();
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] { keyid };
		womTlWorkorderMst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return womTlWorkorderMst;
	}

	public String getElementID(String flid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT FNLN_ELEMENTID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_KEYID= '"+flid+"'";
		CommonMessage.debugMsg(sql);
		String getElementID = dbActionTemplate.getSingleValue(sql);
	
		return getElementID;
	}
	
	public String allowUpdate(String womsKeyid,String type) throws Exception {
		
		  String sql="select count(*)  from SAP_TL_NOTIFICATION where SNOT_KEYID ='"+womsKeyid+"'and TRIM(SNOT_SAPDOCNO)='-' AND TRIM(SNOT_SAPMESSAGE)='-' AND TRIM(SNOT_SAPSTATUS) in ('-','P')";
		  String updCnt=dbActionTemplate.getSingleValue(sql);
		  CommonMessage.debugMsg("updation count:"+updCnt);
		  if(updCnt.equals("0")){
			  return "true";
		  }
		  else{
			  return "false";
		  }
		
	}
	public String allowOrderUpdate(String womsKeyid) throws Exception {
		
		  String sql="select count(*)  from SAP_TL_NOTIFICATION where SNOT_KEYID ='"+womsKeyid+"'and TRIM(SNOT_SAPDOCNO)='-' AND TRIM(SNOT_SAPMESSAGE)='-' AND TRIM(SNOT_SAPSTATUS) in ('-','P')";
		  String updCnt=dbActionTemplate.getSingleValue(sql);
		  CommonMessage.debugMsg("updation count:"+updCnt);
		  if(updCnt.equals("0")){
			  return "true";
		  }
		  else{
			  return "false";
		  }
		
	}


	

	public String getLocationBasedMandfield(String loginFlid) throws Exception {
		//String loginLocn  = dbActionTemplate.getSingleValue( " SELECT  ");;
		try {
		CommonMessage.debugMsg("loginFlid=="+loginFlid);
		if(loginFlid.equals("FNL000000001"))
			loginFlid="FNL000000002";
		
		String sql = "select fnln_keyid from gen_vw_fnln where fnln_originalid in ( select LOCN_KEYID from gen_vw_fnln where fnln_keyid='"+loginFlid+"') ";
		String LocationId=dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg("LocationId=="+LocationId);
		 
		sql=" select MAX(wlfl_mandtory_level) from WOM_TL_LOCNFNLN_MST WHERE WLFL_LOCN_FLID='"+LocationId+"' ";
		  String locnMandField=dbActionTemplate.getSingleValue(sql);
		  CommonMessage.debugMsg("locnMandField:"+locnMandField);
		  return locnMandField;
		}
		catch( Exception e){
			return "MCHM";
		}
	}

	public String submitMorderToSap(String womsKeyid,String type) throws Exception {
			String res="";
			List<String> WomsKeyidList =new ArrayList<String>();
			String date=CommonFunctions.getDate();
			WomsKeyidList.add(womsKeyid);
			String sql="select count(*)  from SAP_TL_MAINTENANCEORDER_MST where SMOM_MASTERID ='"+womsKeyid+"'and TRIM(SMOM_SAPDOCNO)='-' AND TRIM(SMOM_SAPMESSAGE)='-' AND TRIM(SMOM_SAPSTATUS) IN ('-','P')";
			String sqlnoti="select count(*)  from SAP_TL_NOTIFICATION where SNOT_KEYID ='"+womsKeyid+"'and TRIM(SNOT_SAPDOCNO)='-' AND TRIM(SNOT_SAPMESSAGE)='-' AND TRIM(SNOT_SAPSTATUS) in ('-','P')";
			String updSapdate="update wom_tl_workorder_mst set WOMS_SAPORDER_DATE='"+date+"' where woms_keyid='"+womsKeyid+"'";
			///dbActionTemplate.executeStatement(updSapdate);
			String updCnt=dbActionTemplate.getSingleValue(sql);
			String updCntNoti=dbActionTemplate.getSingleValue(sqlnoti);
			if(type.equals("WO") ){
			  CommonMessage.debugMsg("ORDER updation count:"+updCnt);
			  if(updCnt.equals("0")){
				  CommonMessage.debugMsg("ORDER :"+womsKeyid);
				  CommonMessage.debugMsg("update date:"+updSapdate);
				   
					dbActionTemplate.processPLSQLProcedures("SAP_MORDER_TRANSFER.INS_SAP_TRANSACTION_MST", WomsKeyidList, null);
					
				   res= "order submitted";	
			  }
			  else{
				  res= "order failed";
			  }
			}
			else if(type.equals("NOTI")){
				
				  
				  CommonMessage.debugMsg("updation count:"+updCntNoti);
				  if(updCntNoti.equals("0")){
					  dbActionTemplate.processPLSQLProcedures("SAP_NOTI_TRANSFER.insert_ORDER_TO_SAP", WomsKeyidList, null);
					  res= "noti submitted";
				  }
				  else{
					  res= "noti failed";
				  }	
			}
			else if(type.equals("NOTI and WO")){
				
				  CommonMessage.debugMsg("ORDER updation count:"+updCnt);
				  
				  if(updCntNoti.equals("0") && updCnt.equals("0")){
					 // dbActionTemplate.executeStatement(updSapdate);
					  dbActionTemplate.processPLSQLProcedures("SAP_NOTI_TRANSFER.insert_ORDER_TO_SAP", WomsKeyidList, null);
						dbActionTemplate.processPLSQLProcedures("SAP_MORDER_TRANSFER.INS_SAP_TRANSACTION_MST", WomsKeyidList, null);
					   res= "on submitted";	
				  }
				  else{
					  res= "on failed";
				  }
			}
			 
		return res;
		
	}
	public  String getLineNo(String tableName, String condId, String condIdField) throws Exception{
		String lineNo="";
		
		StringBuilder sql=new StringBuilder();
		//sql.append("Select decode(length((count(*)+1)*10),2,'00'||(count(*)+1)*10,3,'0'||(count(*)+1)*10,(count(*)+1)*10) from  ");
		sql.append(" SELECT LPAD((COUNT(*)+1)*10,4,'00') FROM " );
		sql.append(tableName);
		sql.append(" where ");
		sql.append(condIdField);
		sql.append(" ='");
		sql.append(condId);
		sql.append("'");
		CommonMessage.debugMsg("Line No sql:"+sql.toString());
		lineNo=dbActionTemplate.getSingleValue(sql.toString());
		
		return lineNo;
	}

	@Override
	public List<String[]> getPrintHeaderData(String womsKey) throws Exception {
		// TODO Auto-generated method stub
		
		StringBuilder header=new StringBuilder();
		header.append("select fnln.locn_code as plant,fnln.comp_code || '-' ||fnln.locn_code || '-' || fnln.sbut_code ");
		header.append("|| '-' || fnln.pbut_code || '-' || fnln.sect_code || '-' || fnln.cell_code || '-' || fnln.mchm_machineno as functionallocn,");
		header.append("to_char(sysdate,'dd-mm-yyyy') as doc_date, fnln.mchm_machineno||'-'||fnln.mchm_machinename  as equipment,");
		header.append("woms_keyid as orderno,mst.woms_problem as order_text,");
		header.append("cstm.cstm_code as costcentre,'0.00' as Avl_budget,");
		header.append("wkcm.wkcm_code as workcentre,'0.00' as orginalbudget,");
		header.append("'0.00' as actualspent,'0.00' as remainingorderplan ");
		header.append(" from wom_tl_workorder_mst mst,gen_vw_fnln fnln,");
		header.append("gen_tl_costcentremst cstm,gen_tl_workcentremst wkcm where ");
		header.append(" mst.woms_keyid='"+womsKey+"'");
		header.append(" and mst.woms_flid = fnln.fnln_keyid ");
		header.append(" and ((mst.woms_costcenterid = cstm_keyid) or (trim(leading '0' from mst.woms_costcenterid) = cstm_code))  ");
		header.append(" and ((mst.woms_womscenterid=wkcm_keyid) or (mst.woms_womscenterid=wkcm_code)) ");
		CommonMessage.debugMsg("Print Header Sql:"+header.toString());
		List<String[]> headerData=dbActionTemplate.getDataList(header.toString());
		return headerData;
		
	}

	@Override
	public String getSubContractCheck(String womsKey, String taskId)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("select EXTM_EXT_SUB_CONTRACT from sap_external_service_mst where extm_orderno='");
		sql.append(womsKey+"'  and extm_taskid='"+taskId+"'");
		String subContractCheck=dbActionTemplate.getSingleValue(sql.toString());
		return subContractCheck;
	}

	@Override
	public int getMatProvIndCnt(String womsKey, String taskId)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("select count(wsct_matprovind) from wom_tl_sparecost where trim(wsct_matprovind)='S'  and wsct_woms_keyid='");
		sql.append(womsKey+"'  and wsct_taskid='"+taskId+"'");
		String MatProvInd =dbActionTemplate.getSingleValue(sql.toString());
		int MatProvIndCnt=Integer.parseInt(MatProvInd);
		return MatProvIndCnt;
	}

	@Override
	public List<String[]> getSapFnlnDetail(String flid,String fnlnTxt) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT  trim(leading '0' from gsfl_costcenter),gsfl_planner_group,gsfl_main_workcenter,gsfl_functional_locn,gsfm_pmdefault FROM gen_tl_sap_functional_locn,GEN_TL_SAPFNLNMAPPING "); 
		sql.append("where gsfl_keyid = gsfm_gsfl_keyid and gsfm_flid='"+flid+"' and "); 
		sql.append(" gsfl_functional_locn like '%"+fnlnTxt+"%'");
		CommonMessage.debugMsg("Sapfnln sql:"+sql.toString());
		List<String[]> SapFnlnDetail=dbActionTemplate.getDataList(sql.toString());
		return SapFnlnDetail;
	}

	@Override
	public List<String[]> getDefSapFnln(String flid, String fnlnTxt)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT  gsfl_functional_locn,gsfm_pmdefault FROM gen_tl_sap_functional_locn,GEN_TL_SAPFNLNMAPPING "); 
		sql.append("where gsfl_keyid = gsfm_gsfl_keyid and gsfm_flid='"+flid+"' and gsfm_pmdefault='Y' and "); 
		sql.append(" gsfl_functional_locn like '%"+fnlnTxt+"%'");
		CommonMessage.debugMsg("Sapfnln sql:"+sql.toString());
		List<String[]> SapFnlnDetail=dbActionTemplate.getDataList(sql.toString());
		return SapFnlnDetail;
	}
	
}

