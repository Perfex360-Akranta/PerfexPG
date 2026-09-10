package com.akranta.tpm.dao.impl;	

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.BAL_UIUtils;
import com.akranta.tpm.dao.BAL_SAPInfoDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.SapExternalServiceDtlSql;
import com.akranta.tpm.dao.sql.BAL_SapTlMaintenanceOrderdtlSql;
import com.akranta.tpm.dao.sql.BAL_SapTlMaintenanceOrdermstSql;
import com.akranta.tpm.dao.sql.BAL_SapTlSparesreplacedSql;
import com.akranta.tpm.dao.sql.WomTlWomstSql;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_SapTlMaintenanceOrderdtl;
//import com.akranta.tpm.model.SapTlSparesreplaced;
import com.akranta.tpm.model.BAL_SapTlSparesreplaced;
import com.akranta.tpm.utils.CommonFunctions;
//import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.ExcelUtils;


public class BAL_SAPInfoDaoImpl implements BAL_SAPInfoDao{
	
	private DBActionTemplate dbActionTemplate;
	//private CommonFilterDao commonFilterdao;
	private BAL_SapTlMaintenanceOrderdtlSql sapTlMaintenanceOrderdtlSql;
	private BAL_SapTlSparesreplacedSql sapTlSparesreplacedSql;
	public BAL_SAPInfoDaoImpl(DBActionTemplate dbActionTemplate) {
	//commonFilterdao = new CommonFilterDaoImpl(dbActionTemplate);
		this.dbActionTemplate = dbActionTemplate;
		sapTlMaintenanceOrderdtlSql = new BAL_SapTlMaintenanceOrderdtlSql();
		sapTlSparesreplacedSql   = new BAL_SapTlSparesreplacedSql();
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	private List<String[]> getSAPInfoList(String type) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		String sql1="";
		sql.append("Select * from GEN_TL_PILLARREPORTMETA");
		sql.append(" WHERE PRPT_PILLAR = '"+type+"' ORDER BY PRPT_SORTORDER");
		
		sql1 = sql.toString();
		// TODO Auto-generated method stub
		System.out.println("sql..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql1);
		
		return gridData;
	}
	private String getFunctionName(String type) throws Exception {
		// TODO Auto-generated method stub
		
		String FunctionName="";
		if(type.equals("equip")){
			//FunctionName="SAPINFOSAPLIST";
			FunctionName="RPT_PC_CARD.SAP_FN_EQUIPMENTMASTER";				
		}
		else if(type.equals("fn")){
			//FunctionName="SAPINFOSAPLIST";
			FunctionName="RPT_PC_CARD.SAP_FN_FUNCTIONALLOCATION";				
		}
		else if(type.equals("equipbom")){
			//FunctionName="SAPINFOSAPLIST";
			FunctionName="RPT_PC_CARD.SAP_FN_EQUIPMENTBOM";				
		}
		else if(type.equals("workcenter")){
			//FunctionName="SAPINFOSAPLIST";
			FunctionName="RPT_PC_CARD.SAP_FN_WORKCENTER";				
		}
		else if(type.equals("wbselement")){
			//FunctionName="SAPINFOSAPLIST";
			FunctionName="RPT_PC_CARD.SAP_FN_WBSELEMENT";				
		}
		else if(type.equals("tasklist")){
			//FunctionName="SAPINFOSAPLIST";
			FunctionName="RPT_PC_CARD.SAP_FN_TASKLIST";				
		}
		else if(type.equals("costcenter")){
			//FunctionName="SAPINFOSAPLIST";
			FunctionName="RPT_PC_CARD.SAP_FN_COSTCENTER";				
		}
		return FunctionName;
	}
	@Override
	public List<String[]> getSAPInfoList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		String type = commonFilter.getType();
		String functionName="";
		try
		{
			System.out.println("Inside daoimpl");
			System.out.println("type:"+type);				
			functionName=getFunctionName(type);			
			List<String> paramValues = new ArrayList<String>();				
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
						
			System.out.println("ParamValues:"+paramValues);	
			System.out.println("functionName:"+functionName);	
			List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders(functionName, paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			System.out.println(dataList);
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public List<String[]> getSAPinfofillgriddata() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		String sql1 ="";//select '1','Area 1' from dual union all  select '1','Area 1' from dual;
		sql.append(" select ' ','' from dual ");
		
	    
		sql1 = sql.toString();
		System.out.println("sql..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql1);
		return gridData;
	}
	@Override
	public List<String[]> getSAPStoragefillgriddata() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		String sql1 ="";//select '1','Area 1' from dual union all  select '1','Area 1' from dual;
		sql.append(" select ' ','',' ',' ' from dual ");
		
	    
		sql1 = sql.toString();
		System.out.println("sql..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql1);
		return gridData;
	}
	@Override
	public List<String[]> getSAPResultfillgriddata(String type) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		String sql1 ="";
		if("sap".equals(type)){
	 sql.append("select ' ', ' '  ,'1','Plant 1','MC 1','Material Description 1','Storage1','5','50','250','Bin 1'" +
	 		" from dual union all" +
	 		" select ' ', ' '  ,'1','Plant 2','MC 2','Material Description 2','Storage2','10','100','1000','Bin 2'" +
	 		" from dual union all"+
	 		" select ' ', ' '  ,'1','Plant 3','MC 3','Material Description 3','Storage3','4','80','320','Bin 3'" +
	 		" from dual union all"+
	 		
	 		" select ' ', ' '  ,'1','Plant 4','MC 4','Material Description 4','Storage4','8','60','480','Bin 4'" +
	 		" from dual union all"+
	 		" select ' ', ' '  ,'1','Plant 5','MC 5','Material Description 5','Storage5','2','40','80','Bin 5'" +
	 		" from dual ");
	// sql.append("");
		}
	sql1 = sql.toString();
	//sql1 = "Select * from dual";
	System.out.println("sql..." + sql);
	List<String[]> gridData = dbActionTemplate.getDataList(sql1);
	System.out.println("Grid value" + gridData.get(1));
	return gridData;
	}
	@Override
	public BAL_SapTlMaintenanceOrderdtl createSapSpareInfo(BAL_SapTlMaintenanceOrderdtl newSapTlMaintenanceOrderdtl) throws Exception {
		// TODO Auto-generated method stub
		try{

			List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			StringBuffer sapMasterData = new StringBuffer();
			
			CommonFunctions.debugMsg("ceatedBYDaoimpl  "+newSapTlMaintenanceOrderdtl.getModtCreatedby());
			String sapMasterKey = dbActionTemplate.getSequenceNumber("SAP_TL_MAINTENANCE_ORDERMST",10, "SMO", "MMYY", "Y");
			sapMasterData.append(" INSERT INTO SAP_TL_MAINTENANCE_ORDERMST (MOMS_KEYID,MOMS_REFDOCID,MOMS_REFDOCTYPE,MOMS_ACTIVITYTYPE,  ");
			sapMasterData.append(" MOMS_PRIORITY,MOMS_MACHINEID,MOMS_FNLOCATION,MOMS_PLANNERGRP,MOMS_MNTWORKCENTER,MOMS_ORDERDESC," +
					"MOMS_STARTDT,MOMS_FINISHDT, ");
			sapMasterData.append(" MOMS_TEMPFIELD1,MOMS_TEMPFIELD2,MOMS_TEMPFIELD3,MOMS_TEMPFIELD4,MOMS_TEMPFIELD5,MOMS_TEMPFIELD6, ");
			sapMasterData.append(" MOMS_ACTIVE,MOMS_CREATEDBY,MOMS_CREATEDON,MOMS_MODIFIEDON)   ");
			sapMasterData.append(" SELECT '"+sapMasterKey+"','"+newSapTlMaintenanceOrderdtl.getRefdocid()+"' ,'BDM',WOMS_ACTIVITYTYPE  ");
			sapMasterData.append(" ,WOMS_PRIORITY,WOMS_MACHINEID,WOMS_FLID,WOMS_PLANNERGROUP,WOMS_WORKCENTERID,'-',WOMS_WORKSTARTDATE," +
					"WOMS_WORKENDDATE, ");
			sapMasterData.append(" '-','-','-','-','-','-', 'Y','"+newSapTlMaintenanceOrderdtl.getModtCreatedby()+"',SYSDATE,SYSDATE FROM WOM_TL_WOMST WHERE WOMS_KEYID='"+newSapTlMaintenanceOrderdtl.getRefdocid()+"' ");
			CommonFunctions.debugMsg("sapMaster Insert  :"+sapMasterData.toString());
			String condSql = " AND MOMS_REFDOCID = '"+newSapTlMaintenanceOrderdtl.getRefdocid()+"'"; 
			boolean chkDuplicate = dbActionTemplate.checkDuplicateValue("SAP_TL_MAINTENANCE_ORDERMST" , "MOMS_REFDOCID", newSapTlMaintenanceOrderdtl.getRefdocid(), condSql);
			CommonFunctions.debugMsg("chkDuplicate  "+chkDuplicate);
			String masterKeyid = "";
			if(!chkDuplicate){
			
				sqls.add(sapMasterData.toString());
			}
			else{
				sapMasterKey = dbActionTemplate.getSingleValue("SAP_TL_MAINTENANCE_ORDERMST" , "MOMS_KEYID", "MOMS_REFDOCID", newSapTlMaintenanceOrderdtl.getRefdocid());
			} 	 
				newSapTlMaintenanceOrderdtl.setModtMomsKeyid(sapMasterKey );
			 
			if(! BAL_UIUtils.isValidKeyId(newSapTlMaintenanceOrderdtl.getExistDocNumber())){
				newSapTlMaintenanceOrderdtl.setModtKeyid(dbActionTemplate.getSequenceNumber(BAL_SapTlMaintenanceOrderdtlSql.TBL_SAP_TL_MAINTENANCE_ORDERDTL,10, "SMD", "MMYY", "Y"));
				sqls.add(BAL_SapTlMaintenanceOrderdtlSql.getInsertSql(sapTlMaintenanceOrderdtlSql.getModtDbFields(),newSapTlMaintenanceOrderdtl.getSaveArray()));
			}
			else{
				CommonFunctions.debugMsg(" exist doc number true ");
				sqls.add(BAL_SapTlMaintenanceOrderdtlSql.getUpdateSql(sapTlMaintenanceOrderdtlSql.getModtDbFields(),newSapTlMaintenanceOrderdtl.getSaveArray()));
			}
			//CommonFunctions.debugMsg("asdadf   "+newSapTlMaintenanceOrderdtl.getSparesSAPData().size());
			if(newSapTlMaintenanceOrderdtl.getSparesSAPData() ==null || newSapTlMaintenanceOrderdtl.getSparesSAPData().isEmpty() || newSapTlMaintenanceOrderdtl.getSparesSAPData().size()<=0){
				
			}/*else{
				for( SapTlSparesreplaced newSapTlSparesreplaced : newSapTlMaintenanceOrderdtl.getSparesSAPData())
				{ 
					sqls.add("Delete from "+SapTlSparesreplacedSql.TBL_SAP_TL_SPARESREPLACED+" where SSPM_DOCNUMBER = '"+newSapTlMaintenanceOrderdtl.getExistDocNumber()+"'");
					 
					newSapTlSparesreplaced.setSspmDocnumber(newSapTlMaintenanceOrderdtl.getModtRefdocid());
					newSapTlSparesreplaced.setSspmCreatedby(newSapTlMaintenanceOrderdtl.getModtCreatedby()); 
					sqls.add(SapTlSparesreplacedSql.getInsertSql(sapTlSparesreplacedSql.getSspmDbFields() , newSapTlSparesreplaced.getSaveArray()));
				}
				
			}*/
			dbActionTemplate.executeStatements(sqls);
			
		}catch(Exception e){
			e.printStackTrace();
			if(BAL_UIUtils.isValidKeyId(e.getMessage())){
				CommonFunctions.debugMsg("MKSAPSPAREINFO  "+e.getMessage());
				throw new Exception(e.getMessage());
			}
		}
		return newSapTlMaintenanceOrderdtl;
	}
	public String updateSparesQty(List<BAL_SapTlSparesreplaced> sparesReplaceList) throws Exception {
	
		try {
			CommonFunctions.debugMsg("KEYID===="+sparesReplaceList.toString());
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		
		for( BAL_SapTlSparesreplaced newSapTlSparesreplaced : sparesReplaceList)
		{ 
			CommonFunctions.debugMsg("KEYID===="+newSapTlSparesreplaced.getSspmKeyId());
			CommonFunctions.debugMsg("qtyyy===="+newSapTlSparesreplaced.getSspmQuantity());
		//	sapTlSparesreplaced.setSspmKeyId(dbActionTemplate.getSequenceNumber(SapTlSparesreplacedSql.TBL_SAP_TL_SPARESREPLACED, 12, "SSPM", "MMYY", "Y"));//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE "+BAL_SapTlSparesreplacedSql.TBL_SAP_TL_SPARESREPLACED+" ");
			sql.append(" SET SSPM_Quantity = " + newSapTlSparesreplaced.getSspmQuantity() +" ");
			sql.append(" where SSPM_KEYID = '" + newSapTlSparesreplaced.getSspmKeyId() +"' "); 
			System.out.println(sql);
			sqls.add(sql.toString());
		}
		
		dbActionTemplate.executeStatements(sqls);
		return "sucess";
		
		}catch(Exception e){
			e.printStackTrace();
			if(BAL_UIUtils.isValidKeyId(e.getMessage())){
				CommonFunctions.debugMsg("MKSAPSPAREINFO  "+e.getMessage());
				throw new Exception(e.getMessage());
			}
		  }
		return "success";
		
	}
	@Override
	public BAL_SapTlSparesreplaced createSapSpareReplaced(BAL_SapTlSparesreplaced sapTlSparesreplaced) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		StringBuffer sapMasterData = new StringBuffer();
		BAL_SapTlSparesreplaced SapTlSparesreplaced = new BAL_SapTlSparesreplaced();
	try{
		CommonFunctions.debugMsg("DAOIMPLLLLLL   "+sapTlSparesreplaced.getRefDocId());
		CommonFunctions.debugMsg("DAOIMPLLLLLLgetSspmCreatedby   "+sapTlSparesreplaced.getSspmCreatedby());
		
		//sapMasterData.append("INSERT INTO SAP_TL_SPARESREPLACED(SSPM_SPARENO,SSPM_DOCNUMBER,SSPM_QUANTITY,SSPM_DATE,SSPM_STORAGELOCATION,SSPM_RATE,SSPM_VALUE,SSPM_TEMPFIELD1,SSPM_TEMPFIELD2.SSPM_TEMPFIELD3.SSPM_TEMPFIELD4,SSPM_TEMPFIELD5,SSPM_ACTIVE,SSPM_CREATEDBY,SSPM_CREATEDON,SSPM_MODIFIEDON)");
		   
		/*String sapMasterKey = dbActionTemplate.getSequenceNumber("SAP_TL_MAINTENANCE_ORDERMST",10, "SMO", "MMYY", "Y");
		sapMasterData.append(" INSERT INTO SAP_TL_MAINTENANCE_ORDERMST (MOMS_KEYID,MOMS_REFDOCID,MOMS_REFDOCTYPE,MOMS_ACTIVITYTYPE,  ");
		sapMasterData.append(" MOMS_PRIORITY,MOMS_MACHINEID,MOMS_FNLOCATION,MOMS_PLANNERGRP,MOMS_MNTWORKCENTER,MOMS_ORDERDESC," +
				"MOMS_STARTDT,MOMS_FINISHDT, ");
		sapMasterData.append(" MOMS_TEMPFIELD1,MOMS_TEMPFIELD2,MOMS_TEMPFIELD3,MOMS_TEMPFIELD4,MOMS_TEMPFIELD5,MOMS_TEMPFIELD6, ");
		sapMasterData.append(" MOMS_ACTIVE,MOMS_CREATEDBY,MOMS_CREATEDON,MOMS_MODIFIEDON)   ");
		sapMasterData.append(" SELECT '"+sapMasterKey+"','"+sapTlSparesreplaced.getRefDocId()+"' ,'BDM',WOMS_ACTIVITYTYPE  ");
		sapMasterData.append(" ,WOMS_PRIORITY,WOMS_MACHINEID,WOMS_FLID,WOMS_PLANNERGROUP,WOMS_WORKCENTERID,'-',WOMS_WORKSTARTDATE," +
				"WOMS_WORKENDDATE, ");
		sapMasterData.append(" '-','-','-','-','-','-', 'Y','"+sapTlSparesreplaced.getSspmCreatedby()+"',SYSDATE,SYSDATE FROM WOM_TL_WOMST WHERE WOMS_KEYID='"+sapTlSparesreplaced.getRefDocId()+"' ");
		CommonFunctions.debugMsg("sapMaster Insert  :"+sapMasterData.toString());
		String condSql = " AND MOMS_REFDOCID = '"+sapTlSparesreplaced.getRefDocId()+"'"; 
		boolean chkDuplicate = dbActionTemplate.checkDuplicateValue("SAP_TL_MAINTENANCE_ORDERMST" , "MOMS_REFDOCID", sapTlSparesreplaced.getRefDocId(), condSql);
		CommonFunctions.debugMsg("chkDuplicate  "+chkDuplicate);
		if(!chkDuplicate)
			sqls.add(sapMasterData.toString());*/
	//	sqls.add(SapTlSparesreplacedSql.getInsertSql(sapTlSparesreplacedSql.getSspmDbFields() , SapTlSparesreplaced.getSaveArray()));
		//sapTlSparesreplaced.setSspmKeyId(sspmKeyid);//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number
		
		// sqls.add("Delete from "+SapTlSparesreplacedSql.TBL_SAP_TL_SPARESREPLACED+" where SSPM_DOCNUMBER = '"+sapTlSparesreplaced.getSparesReplaced().get(0).getSspmDocnumber()+"'");
		
		for( BAL_SapTlSparesreplaced newSapTlSparesreplaced : sapTlSparesreplaced.getSparesReplaced())
		{ 
		//	sapTlSparesreplaced.setSspmKeyId(dbActionTemplate.getSequenceNumber(SapTlSparesreplacedSql.TBL_SAP_TL_SPARESREPLACED, 12, "SSPM", "MMYY", "Y"));//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number
			
			String sspmKeyid =  dbActionTemplate.getSequenceNumber(BAL_SapTlSparesreplacedSql.TBL_SAP_TL_SPARESREPLACED, 12, "SSPM", "MMYY", "Y");
			CommonFunctions.debugMsg("sspmKeyid==="+sspmKeyid);				
			newSapTlSparesreplaced.setSspmKeyId(sspmKeyid);
			CommonFunctions.debugMsg("newSapTlSparesreplaced.getSspmDocnumber()=="+newSapTlSparesreplaced.getSspmDocnumber());
			//newSapTlSparesreplaced.setSspmDocnumber(sapTlSparesreplaced.getRefDocId());
			newSapTlSparesreplaced.setSspmCreatedby(sapTlSparesreplaced.getSspmCreatedby()); 
			sqls.add(BAL_SapTlSparesreplacedSql.getInsertSql(sapTlSparesreplacedSql.getSspmDbFields() , newSapTlSparesreplaced.getSaveArray()));
			System.out.println(sqls);
		}
		
		dbActionTemplate.executeStatements(sqls);
		return sapTlSparesreplaced; 
		}catch(Exception e){
			e.printStackTrace();
			if(BAL_UIUtils.isValidKeyId(e.getMessage())){
				CommonFunctions.debugMsg("MKSAPSPAREINFO  "+e.getMessage());
				throw new Exception(e.getMessage());
			}
		  }
		return sapTlSparesreplaced;
	
	}
	
	public List<String[]> getAllCrmmasterData() throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT CRMM_COMPLAINTNO  as \"Complaint No.\",CRMM_DATE_STR as \"Complaint Date\"," );
		sql.append(" 'B' as \"Bill Items\",'N' as Notes, "); 
		sql.append(" CRMM_SHIP_TO_PARTY as \"Ship to Party\", ");
		sql.append(" CRMM_SOLD_TO_PARTY  as \"Sold to Party\",CRMM_TRANS_TYPE as \"Transaction Type\",CRMM_TRANS_DESC as \"Description of Transaction\", "); 
		sql.append(" CRMM_EMP_RESPONSIBLE as \"Employee Responsible\",CRMM_EXT_REFERENCE as \"External Reference\",CRMM_EXT_REFDATE_STR as \"Reference Date\", ");
		sql.append(" CRMM_SALES_OFFICE as \"Sales Office\",CRMM_PLANT as \"Plant\",CRMM_EQUIPMENTNO as \"Machine ID\",CRMM_MANUFACT_DATE_STR  as \"Manufacturing Date\",");
		sql.append(" CRMM_RUNID as \"Run ID\",CRMM_PROCESS_CAT_DESC as \"Process. Category Description\",CRMM_PROCESS_STATUS as \"Processing Status\",");
		sql.append(" CRMM_DEFECT_CAT as \"Defect Category\",CRMM_CAUSE as \"Cause\",CRMM_REMOTE_SHEETER as \"Remote Sheeter\",CRMM_Invoice_Qty as \"Invoice Qty\",");
		sql.append(" CRMM_Invoice_Price as \"Invoice Price\",CRMM_Complaint_Qty as \"Complaint Qty\",CRMM_Complaint_Price as \"Complaint Price\",");
		sql.append(" CRMM_Provision_Value as \"Provision Value\",CRMM_SAMPL_RECD_Date_STR  as \"Sample received Date\",CRMM_SAMPL_SENT_Date_STR  as \"Sample sent Date\",");
		sql.append(" CRMM_Sales_Org as \"Sales Organization\",CRMM_Distributior_Chan as \"Distributior Channel\",CRMM_Division as \"Division\",CRMM_Sales_Group as \"Sales Group\",");
		sql.append(" CRMM_Packing_equipment as \"Packing machine\",CRMM_Sheeter as \"Sheeter\",CRMM_Sheeter_NO as \"Sheeter Number\",CRMM_Packing_Type as \"Packing Type\"");
		sql.append(" from SAP_TL_CRMMST " );
	
		return dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
	}

	public List<String[]> getCrmBillItemsData(String crmNo) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select CRMB_Billing_Doc as \" Billing Document \", CRMB_Batch_No as \" Batch No.\",CRMB_Material as \"Material\" from SAP_TL_CRM_BILLING_ITEM where CRMB_COMPLAINTNO =  '" + crmNo +"'" );
		
		return dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
	}
	public List<String[]> getCrmNotesData(String crmNo) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select CRMN_TEXT_TYPE as \"Text Type\",CRMN_CREATEDBY as \"Created By\",CRMN_NOTE_DESC as \"Note Description \" from SAP_TL_CRM_NOTES where CRMN_COMPLAINTNO ='"+ crmNo+"'" );
		return dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
		
	}
	
	public List<String[]> getDownTimeData(CommonFilter commonFilter) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT 0 AS SLNO,1 AS DATAORDER, 'Date' SDATE,'Shift' SHIFT,'Unit Reference' UNITREFERENCE,'Functional Location Reference' FUNCTIONALLOCATIONREFERENCE, ");
		sql.append(" 'Work Center Reference' WORKCENTERREFERENCE,'Equipment' EQUIPMENT,'Production Order No.' PRODUCTIONORDERNO,'Product' PRODUCT,'Grade' GRADE, ");
		sql.append(" 'Grade Spec' GRADESPEC,'Downtime Start Time' DOWNTIMESTARTTIME,'Downtime End Time' DOWNTIMEENDTIME,'Total Downtime' TOTALDOWNTIME , ");
		sql.append(" 'Downtime Criticality' DOWNTIMECRITICALITY,'Downtime Area ID' DOWNTIMEAREAID,'Downtime Reason ID' DOWNTIMEREASONID, ");
		sql.append(" 'Downtime Reason Description' DOWNTIMEREASONDESCRIPTION,'Remarks' REMARKS FROM DUAL UNION ALL ");
		sql.append(" SELECT * FROM (");
		sql.append(" SELECT ROWNUM AS SLNO, A.* FROM( ");
		sql.append(" SELECT 2 AS DATAORDER,SAP_DNTM_DATE_STR AS SDATE, SAP_DNTM_SHIFT AS SHIFT,SAP_DNTM_UNIT AS UNITREFERENCE, ");
		sql.append(" SAP_DNTM_FNLOCATION AS FUNCTIONALLOCATIONREFERENCE,SAP_DNTM_WORKCENTERID AS WORKCENTERREFERENCE, ");
		sql.append(" SAP_DNTM_EQUIPMENT AS EQUIPMENT,SAP_DNTM_PRODUCTIONORDERNO AS PRODUCTIONORDERNO, ");
		sql.append(" SAP_DNTM_PRODUCT AS PRODUCT,SAP_DNTM_GRADE AS GRADE,SAP_DNTM_GRADESPEC AS GRADESPEC, ");
		sql.append(" SAP_DNTM_FROMTIME_STR AS DOWNTIMESTARTTIME,SAP_DNTM_TOTIME_STR AS DOWNTIMEENDTIME, ");
		sql.append(" TO_CHAR(SAP_DNTM_DOWNTIME) AS TOTALDOWNTIME,SAP_DNTM_DTCRITICALITY AS DOWNTIMECRITICALITY, ");
		sql.append(" SAP_DNTM_DTAREAID AS DOWNTIMEAREAID,SAP_DNTM_DTREASONID AS DOWNTIMEREASONID, ");
		sql.append(" SAP_DNTM_REASONTEXT AS DOWNTIMEREASONDESCRIPTION,SAP_DNTM_REMARKS AS REMARKS FROM SAP_TL_DOWNTIME ");
		sql.append(" ) A WHERE 1=1 "+ FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		sql.append(" ) WHERE SLNO >= "+commonFilter.getFromRow()+" AND SLNO <= "+commonFilter.getToRow());
		sql.append(" ORDER BY DATAORDER ");
		CommonFunctions.debugMsg("sql: "+sql.toString());
		return dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
	}

	@Override
	public List<String[]> getFillGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST2.QTM_FN_SAPMAINTENENCEORDER", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}
	}
	
	public List<String[]> getNotificationData() throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select WOMS_NOTIFICATION_NO as \"Notification.No.\",WOMS_ORDER_NO as \"Order No\",WOMS_NOTIFICATIONTYPE as \"Notification Type\",WOMS_SHORTTEXT as \"Short Text\",");
		sql.append(" WOMS_EQUIPMENTNO	as \"Equipment No.\",WOMS_PLANNERGROUP as \"Planner Group\",WOMS_WORKCENTERID as \"WorkCenter\",");
		sql.append(" WOMS_PARTNER as \"Partner\",WOMS_PERSONRESPONSIBLE as \"Responsible\",WOMS_REPORTEDBY as \"Reported By\",");
		sql.append(" WOMS_OCCURREDDATE_STR	as \"Occurred Date\",WOMS_REQUIREDSTART_STR as \"Required Start\",WOMS_REQUIREDEND_STR \"Required End\", ");
		sql.append(" WOMS_BREAKDOWNINDICATOR as \"Breakdown Indicator\",WOMS_WORKSTARTDATE_STR as \"Work Start Date\",WOMS_WORKENDDATE_STR as \"Work End Date\", "); 
		sql.append(" WOMS_PRIORITY \"Priority\" FROM SAP_TL_WOMST ");
		
		return dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
		
	}				
            	
	@Override
	public List<String[]> getFillDownTimeGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.SAP_FN_SAPDOWNTIMEAREA", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}
	}
	@Override
	public List<String[]> getFillDownTimeReasonsGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.SAP_FN_SAPDOWNTIMEREASONS", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}
	}
	
	@Override
	public List<String[]> getHRMSFillGrid(CommonFilter commonFilter, String mode)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			CommonFunctions.debugMsg(" Inside DaoImpl :: mode :: "+mode);
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
			if(BAL_UIUtils.isValidKeyId(mode))
			condParms+="Mode="+mode+";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.SAP_FN_HRMSDATA", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}

	}
@Override
	public List<String[]> getHRMSEmployeeFillGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{

			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.SAP_FN_HRMSEMPLOYEEDATA", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}

	}
	@Override
	public List<String[]> getHRMSShiftFillGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
 
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.SAP_FN_HRMSSHIFTDATA", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}

	}
	
	@Override
	public List<String[]> getOperationGrid(String orderNo)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select MODT_OPNO as \" Operation Number \",MODT_CONTROLKEY as \" Control Key \",MODT_OPDESC as \" Operation Description \",MODT_WORK as \" Work \",MODT_WORKUNIT as \" Work Unit \", MODT_DURATION as \" Duration\", MODT_CCKEY as \" CC Key\", MODT_FIELDKEY as \" Field Key\", MODT_CHECKINGTOOL as \" Checking Tool\", MODT_IDEALCOND as \" Ideal Condition\", MODT_TYPEOFCHECK as \" Type Of Check\", MODT_ACTUALCOND as \" Actual Condition\", MODT_WBSELEMENT as \" WBS Element\" from SAP_MAINTENANCE_ORDER_OPN where MODT_ORDER_NO =  '" + orderNo +"'" );
		return dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
	}
	@Override
	public List<String[]> getMaterialGrid(String orderNo)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select MOSP_ITEMNUMBER as \" Item Number \", MOSP_MATERIALNUMBER as \" Material Number \", MOSP_QUANTITY as \" Material Quantity \", MOSP_UOM as \" UOM \" from SAP_MAINTENANCE_ORDER_MATERIAL where MOSP_ORDER_NO =  '" + orderNo +"'" );
		return dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
	}

	public int getDownTimeCount(CommonFilter commonFilter) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(*) FROM ( ");
		sql.append("SELECT ROWNUM AS SLNO, A.* FROM( ");
		sql.append(" SELECT 2 AS DATAORDER,SAP_DNTM_DATE_STR AS SDATE, SAP_DNTM_SHIFT AS SHIFT,SAP_DNTM_UNIT AS UNITREFERENCE, ");
		sql.append(" SAP_DNTM_FNLOCATION AS FUNCTIONALLOCATIONREFERENCE,SAP_DNTM_WORKCENTERID AS WORKCENTERREFERENCE, ");
		sql.append(" SAP_DNTM_EQUIPMENT AS EQUIPMENT,SAP_DNTM_PRODUCTIONORDERNO AS PRODUCTIONORDERNO, ");
		sql.append(" SAP_DNTM_PRODUCT AS PRODUCT,SAP_DNTM_GRADE AS GRADE,SAP_DNTM_GRADESPEC AS GRADESPEC, ");
		sql.append(" SAP_DNTM_FROMTIME_STR AS DOWNTIMESTARTTIME,SAP_DNTM_TOTIME_STR AS DOWNTIMEENDTIME, ");
		sql.append(" TO_CHAR(SAP_DNTM_DOWNTIME) AS TOTALDOWNTIME,SAP_DNTM_DTCRITICALITY AS DOWNTIMECRITICALITY, ");
		sql.append(" SAP_DNTM_DTAREAID AS DOWNTIMEAREAID,SAP_DNTM_DTREASONID AS DOWNTIMEREASONID, ");
		sql.append(" SAP_DNTM_REASONTEXT AS DOWNTIMEREASONDESCRIPTION,SAP_DNTM_REMARKS AS REMARKS FROM SAP_TL_DOWNTIME ");
		sql.append(" ) A WHERE 1=1 "+ FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		sql.append(" ) ");
		CommonFunctions.debugMsg(" count sql: "+sql.toString());
		String returnData = dbActionTemplate.getSingleValue(sql.toString());
		int count = Integer.parseInt(returnData);
		return count;
	}

	@Override
	public Workbook getMaintExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		try{				
			rs =   getResultSet(commonFilter);			 
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );				
		}finally{
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}
	private ResultSet getResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter); // OPLRelatedCondSql(commonFilter)			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("TEST_PC_TEST2.QTM_FN_SAPMAINTENENCEORDER", paramValues);
	}
	
	@Override
	public Workbook getHrmsExportExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter, String mode) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try{				
			CommonFunctions.debugMsg(" Inside CommonFunctions :: Dao Impl :: "+mode);
			rs =   getHrmsResultSet(commonFilter, mode);			 
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,2,0,0 );
			
		}finally{
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}
	
	private ResultSet getHrmsResultSet(CommonFilter commonFilter, String mode)throws Exception {
		// TODO Auto-generated method stub
		
		CommonFunctions.debugMsg(" Inside CommonFunctions :: Result Set :: "+mode);
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		if(BAL_UIUtils.isValidKeyId(mode))
			condParms+="Mode="+mode+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return dbActionTemplate.dbFunctionCall("SAP_PC_REPORTS.SAP_FN_HRMSDATA", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}
	@Override
	public Workbook getShiftExportExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter, String mode) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try{				
			CommonFunctions.debugMsg(" Inside CommonFunctions :: Dao Impl :: "+mode);
			rs =   getShiftResultSet(commonFilter, mode);			 
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,2,0,0 );
			
		}finally{
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}
	private ResultSet getShiftResultSet(CommonFilter commonFilter, String mode)throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("SAP_PC_REPORTS.SAP_FN_HRMSSHIFTDATA", paramValues);
	}
	@Override
	public Workbook getEmployeemstExportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try{				
			
			rs =   getEmployeeResultSet(commonFilter);			 
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,2,0,0 );
			
		}finally{
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}
	private ResultSet getEmployeeResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("SAP_PC_REPORTS.SAP_FN_HRMSEMPLOYEEDATA", paramValues);
	}
	@Override
	public List<String[]> getNPCFillGrid(CommonFilter commonFilter, String mode)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			CommonFunctions.debugMsg(" Inside DaoImpl :: mode :: "+mode);
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
			if(BAL_UIUtils.isValidKeyId(mode))
			condParms+="Mode="+mode+";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.SAP_FN_NPC", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}

	}
	@Override
	public List<String[]> getREPULPFillGrid(CommonFilter commonFilter,
			String mode) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			CommonFunctions.debugMsg(" Inside DaoImpl :: mode :: "+mode);
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
			if(BAL_UIUtils.isValidKeyId(mode))
			condParms+="Mode="+mode+";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.SAP_FN_REPULP", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}

	}
	@Override
	public List<String[]> getTaskListFillGrid(CommonFilter commonFilter,
			String mode) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			CommonFunctions.debugMsg(" Inside DaoImpl :: mode :: "+mode);
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
			if(BAL_UIUtils.isValidKeyId(mode))
			condParms+="Mode="+mode+";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.SAP_FN_TASKLIST", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}

	}
	@Override
	public List<String[]> getProductionDetailsFillGrid(
			CommonFilter commonFilter, String mode) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			CommonFunctions.debugMsg(" Inside DaoImpl :: mode :: "+mode);
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
			if(BAL_UIUtils.isValidKeyId(mode))
			condParms+="Mode="+mode+";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.SAP_FN_PRODUCTIONDETAILS", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(isInteger)
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}
	}
	@Override
	public List<String[]> getMaterialMasterFillGrid(CommonFilter commonFilter) throws Exception {
		try
		{
			
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);  //SAP_FN_MATERIALMASTER
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.SAP_FN_MATERIALMASTER", paramValues);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
			return dataList;
	}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			//e.printStackTrace();
			
		}
		 
		
	}
	public List <ComboBox> getOrdertype(ComboFilter comboFilter) throws Exception{
		
		
		return null;
		
	}
	public String delteSparesDetail(List<BAL_SapTlSparesreplaced> sparesReplaceList) throws Exception {
			
			try {
				CommonFunctions.debugMsg("KEYID====");
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			
			for( BAL_SapTlSparesreplaced newSapTlSparesreplaced : sparesReplaceList)
			{ 
				CommonFunctions.debugMsg("KEYID===="+newSapTlSparesreplaced.getSspmKeyId());				
			//	sapTlSparesreplaced.setSspmKeyId(dbActionTemplate.getSequenceNumber(SapTlSparesreplacedSql.TBL_SAP_TL_SPARESREPLACED, 12, "SSPM", "MMYY", "Y"));//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number
				StringBuffer sql = new StringBuffer();
				sql.append(" DELETE FROM  "+BAL_SapTlSparesreplacedSql.TBL_SAP_TL_SPARESREPLACED+" ");
				sql.append(" where SSPM_KEYID = '" + newSapTlSparesreplaced.getSspmKeyId() +"' "); 
				
				System.out.println(" sql in delete daoimpl   "+sql);
				
				sqls.add(sql.toString());
				
			}
			
			dbActionTemplate.executeStatements(sqls);
			return "sucess";
			
			}catch(Exception e){
				e.printStackTrace();
				if(BAL_UIUtils.isValidKeyId(e.getMessage())){
					CommonFunctions.debugMsg("MKSAPSPAREINFO  "+e.getMessage());
					throw new Exception(e.getMessage());
				}
			  }
			return "success";
			
		}
	public String delteSparesDetail(String keyId) throws  Exception{
		
		String sql="";
		sql="DELETE FROM SAP_TL_SPARESREPLACED WHERE SSPM_KEYID='"+keyId+"'";
		
		System.out.println("sql queri in dlt"+sql);
		dbActionTemplate.executeStatement(sql);
		return "Data Deleted Successfully.";
	}
	
		

	
}
