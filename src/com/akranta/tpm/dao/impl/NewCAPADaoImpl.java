package com.akranta.tpm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
//import com.akranta.tpm.dao.CompanyDao;
import com.akranta.tpm.dao.NewCAPADao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.QtmTlCapaActionmstSql;
import com.akranta.tpm.dao.sql.QtmTlCapaSql;
import com.akranta.tpm.dao.sql.QtmTlCapaWorkflowSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlCapa;
import com.akranta.tpm.model.QtmTlCapaActionmst;
import com.akranta.tpm.model.QtmTlCapaWorkflow;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.KaizenBankServiceAPI;
import com.akranta.tpm.service.api.NewcapaServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;



public class NewCAPADaoImpl implements NewCAPADao {
	
	
	
	private DBActionTemplate dbActionTemplate;
	private NewcapaServiceApi newcapaServiceApi;
	private FunctionCallApi fnCallApi;
	
	public NewCAPADaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
public void NewCAPADaoImplJwt(String jwtToken) {
		
		try{
			newcapaServiceApi = new NewcapaServiceApi(jwtToken);
			fnCallApi = new FunctionCallApi(jwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	@Override
	public QtmTlCapa create(QtmTlCapa newQtmTlCapa) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		QtmTlCapaSql qtmTlCapaSql = new QtmTlCapaSql(); // contains dbtable,field names, Field types and related sqls  of master table
		try{
			newQtmTlCapa.setQcapKeyid(dbActionTemplate.getSequenceNumber(QtmTlCapaSql.TBL_QTM_TL_CAPA,12,"CAPA",null,"N"));
			sqls.add(QtmTlCapaSql.getInsertSql(qtmTlCapaSql.getQcapDbFields(),newQtmTlCapa.getSaveArray()));
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newQtmTlCapa;
	}
//	@Override
//	public List<String[]> getDmtLeaderApproval(CommonFilter commonFilter, String capaId) throws Exception {
//		// TODO Auto-generated method stub
//		
//		StringBuffer sql = new StringBuffer();
//		 if(UIUtils.isValidKeyId(capaId)){ 
//		sql.append(" SELECT "); 
//		sql.append("QCWF_KEYID ,QCWF_CAPAREFID,ROLM_KEYID ,ROLM_NAME ,EMPM_NAME ,QCWF_APPROVEDBY ,QCWF_STATUS,TO_CHAR(QCWF_DATE,'DD-MON-YYYY'),QCWF_REMARKS   ");  
//		sql.append(" FROM ");
//		sql.append("MOC_TL_ROLEMST,QTM_TL_CAPAWORKFLOW,GEN_TL_EMPLOYEEMST"); 
//		sql.append(" WHERE ");
//		sql.append(" EMPM_KEYID=QCWF_APPROVEDBY  and rolm_keyid='ROLM0013'");
//	  	
//		   if(UIUtils.isValidKeyId(capaId)){
//		    //sql.append(" AND QCDC_QCLDKEYID ='"+unsafeact+"' ");
//			sql.append(" AND QCWF_CAPAREFID ='"+capaId+"' ");
//		   }
//	    sql.append(" ORDER BY ");
//	    sql.append(" ROLM_KEYID ");
//	    System.out.println("SQL is::::"+sql);
//		 }
//		 else {
//			 System.out.println("sys");
//		 }
//	    List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
//		return gridData;
//		
//	}
	
	@Override
	public List<String[]> getDmtLeaderApproval(CommonFilter commonFilter, String capaId) throws Exception {
		// TODO Auto-generated method stub
		
		StringBuffer sql = new StringBuffer();
		 if(UIUtils.isValidKeyId(capaId)){ 
		sql.append(" SELECT "); 
		sql.append("QCWF_KEYID ,QCWF_CAPAREFID,ROLE_KEYID ,ROLE_NAME ,EMPM_NAME ,QCWF_APPROVEDBY ,QCWF_STATUS,TO_CHAR(QCWF_DATE,'DD-MON-YYYY'),QCWF_REMARKS   ");  
		sql.append(" FROM ");
		sql.append("ADM_TL_ROLEMST,QTM_TL_CAPAWORKFLOW,GEN_TL_EMPLOYEEMST"); 
		sql.append(" WHERE ");
		sql.append(" EMPM_KEYID=QCWF_APPROVEDBY  and ROLE_KEYID='AROL0003'");
	  	
		   if(UIUtils.isValidKeyId(capaId)){
		    //sql.append(" AND QCDC_QCLDKEYID ='"+unsafeact+"' ");
			sql.append(" AND QCWF_CAPAREFID ='"+capaId+"' ");
		   }
	    sql.append(" ORDER BY ");
	    sql.append(" ROLE_KEYID ");
	    System.out.println("SQL is::::"+sql);
		 }
		 else {
			 System.out.println("sys");
		 }
	    List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
		
	}
	@Override
public QtmTlCapaWorkflow create(QtmTlCapaWorkflow qtmTlCapaWorkflow, String capaId) throws Exception {
		
		//List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		//QtmTlCapaWorkflowSql qtmTlCapaWorkflowSql = new QtmTlCapaWorkflowSql(); // contains dbtable,field names, Field types and related sqls  of master table
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		QtmTlCapaWorkflowSql qtmTlCapaWorkflowSql = new QtmTlCapaWorkflowSql(); // contains dbtable,field names, Field types and related sqls  of master table
		try{
			qtmTlCapaWorkflow.setQcwfKeyid(dbActionTemplate.getSequenceNumber(QtmTlCapaWorkflowSql.TBL_QTM_TL_CAPAWORKFLOW,12,"CAPW",null,"N"));
		System.out.println("inside save"+qtmTlCapaWorkflow.getQcwfKeyid());
			sqls.add(QtmTlCapaWorkflowSql.getInsertSql(qtmTlCapaWorkflowSql.getqcwfDbFields(),qtmTlCapaWorkflow.getSaveArray()));
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return qtmTlCapaWorkflow;
	}
	
	
	
	@Override
	public List<String[]> getCAPARelatedData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try {
			System.out.println("Inside Dao");
			String ProType="";
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		    
			String Title = commonFilter.gettitle();
			
			System.out.println("Inside Dao and the title is  " + Title);
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList;
			
			if("Report".equals(commonFilter.gettitle())) {
//				dataList = dbActionTemplate.processFunctionCallsWithColHeaders("QM_FN_CAPAREPORT",paramValues);	
				dataList = fnCallApi.callFunction("QM_FN_CAPAREPORT_SB", paramValues, 3, true);
			} 
			else {
			
//			dataList = dbActionTemplate.processFunctionCallsWithColHeaders("QM_FN_CAPA",paramValues);
				dataList = fnCallApi.callFunction("QM_FN_CAPA_SB", paramValues, 3, false);
			
			}
			
	    	System.out.println("DataList"+dataList);
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public QtmTlCapaWorkflow getComplaintApproval(QtmTlCapaWorkflow qtmTlCapaWorkflow,
			QtmTlCapaWorkflow existQtmTlCapaWorkflow, String keyid, String empId, String status, String date,
			String remarks, String capaId) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
	
		sql.append(" UPDATE QTM_TL_CAPAWORKFLOW SET QCWF_APPROVEDBY='"+empId+"', QCWF_REMARKS='"+remarks+"',QCWF_STATUS='"+status+"',QCWF_DATE='"+date+"'WHERE QCWF_CAPAREFID='"+capaId+"' ");
		   System.out.println("RoleConfig"+sql);
		
/*	
			sql.append(" UPDATE qtm_tl_capaworkflow SET QCWF_APPROVEDBY='"+empId+"', QCWF_REMARKS='"+remarks+"',QCWF_STATUS='"+status+"',QCWF_DATE='"+date+"' WHERE QCWF_CAPAREFID='"+capaId+"' ");
			   System.out.println("Updatesql C:::"+sql);	
		*/
		dbActionTemplate.executeStatement(sql.toString());
		System.out.println("aFTER DBACTION");
	   return qtmTlCapaWorkflow;
	}
	@Override
	public List<QtmTlCapaActionmst> createCAPA(List<QtmTlCapaActionmst> qtmTlCapaActionmst, String createdBy,
			String capaMasterKeyid) throws Exception {
		// TODO Auto-generated method stub
		try{
		 List<String> sqls = new ArrayList<String>();
	     List <QtmTlCapaActionmst> methodslist = qtmTlCapaActionmst;
	     System.out.println("before for loop"+methodslist);
	     QtmTlCapaActionmstSql qtmTlCapaActionmstSql=new QtmTlCapaActionmstSql();
	     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),QtmTlCapaActionmstSql.TBL_QTM_TL_CAPACTIONMST,12,"CAP", null,null);
	     String detlKeyid="";
	   //  GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource(),"",14,"MOCT", "YYMM","Y");
		System.out.println("the sequenceNumber"+sequenceNumber);
	     for(QtmTlCapaActionmst abnormalityLink : methodslist)
		 {
				String seqNo=sequenceNumber.getSequnceNumber();
				System.out.println("The seqNo::::"+seqNo);
				abnormalityLink.setQcpaKeyid(seqNo);
				abnormalityLink.setQcpaCreatedby(createdBy);
				detlKeyid= capaMasterKeyid;
				System.out.println("detlKeyid::::"+detlKeyid);
				abnormalityLink.setQcpaQcapkeyid(detlKeyid);;
				//  popSqlsForTeamDeleteData(detlKeyid);
				//popSqlsForMOCTeamDeleteData(detlKeyid);

                sqls.add(QtmTlCapaActionmstSql.getInsertSql(qtmTlCapaActionmstSql.getqcpaDbFields(), abnormalityLink.getSaveArray()));
              //  MultipleactionPlanEntry(list,"I");
                System.out.println("The sql CAPA NOs Query:"+sqls);
		 }
		 dbActionTemplate.executeStatements(sqls);
}
catch(BusinessApplicationExceptions e)
{
	System.out.println("Business Application   :"+e.getMessage());
	throw new BusinessApplicationExceptions(e.getMessage()); 
}
return qtmTlCapaActionmst;
	}
	@Override
	public QtmTlCapaActionmst createCAPA(QtmTlCapaActionmst fillValuesCAPActionPlan, String createdBy, String keyid,
			String preventiveAction, String rootCause, String targetDate, String status, String mType, String defect)
					throws Exception {
		// TODO Auto-generated method stub
		System.out.println("In Dao Impl");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		QtmTlCapaActionmstSql qtmTlCapaActionmstSql = new QtmTlCapaActionmstSql(); // contains dbtable,field names, Field types and related sqls  of master table

	try{

		fillValuesCAPActionPlan.setQcpaKeyid(dbActionTemplate.getSequenceNumber(QtmTlCapaActionmstSql.TBL_QTM_TL_CAPACTIONMST,12,"CAP","","")); // set the sequnce number 
	sqls.add(QtmTlCapaActionmstSql.getInsertSql(qtmTlCapaActionmstSql.getqcpaDbFields(), fillValuesCAPActionPlan.getSaveArray())); // add insert sql for master table

	//   
	System.out.println("after dtl "+sqls.toString());

	dbActionTemplate.executeStatements(sqls); // execute the block of sqls

	}		
	catch(Exception e)
				{
					e.printStackTrace();
					throw new Exception(e.getMessage());
				}
		return fillValuesCAPActionPlan;
	}
	// ---------- Vignesh 22Dec2025 -------------------------------------------------------//
//	@Override
//	public List<String[]> getCapaDetails(CommonFilter commonFilter) throws Exception {
//		// TODO Auto-generated method stub
//		StringBuffer sql = new StringBuffer();
//		String CapaId=commonFilter.getKey();
//		
//		
//		 if(UIUtils.isValidKeyId(CapaId)){ 
//		sql.append(" SELECT "); 
//		sql.append("QCPA_KEYID, QCPA_QCAP_KEYID,QCPA_CORRECTIVEACTION,QCPA_ROOTCAUSE,QCPA_PREVENTIVEACTION,QCPA_4MCONDITION,QCPA_DEFECTTYPE,EMPM_NAME,QCPA_RESPONSIBLEID,TO_CHAR(QCPA_TARGETDATE,'DD-mon-yyyy'),DECODE(QCPA_STATUS,'P','Pending','C','Completed','W','Work in Progress','-','Not Assigned'),'',APLM_KEYID,'',WWMS_KEYID,'',kzbn_keyid,'',''  ");  
//		sql.append(" FROM ");
//		sql.append(" QTM_TL_CAPActionmst, gen_tl_employeemst,gen_tl_actionplanmst,kzn_tl_kaizenbankmst ,bdm_tl_whywhymst"); 
//		sql.append(" WHERE ");
//		sql.append(" EMPM_KEYID=QCPA_RESPONSIBLEID AND aplm_masterrefid(+)=QCPA_QCAP_KEYID  and QCPA_QCAP_KEYID=KZBN_REFDOCNO(+)  and WWMS_REFDOCNO(+)=QCPA_QCAP_KEYID");
//	  	
//		   if(UIUtils.isValidKeyId(CapaId)){
//		    //sql.append(" AND QCDC_QCLDKEYID ='"+unsafeact+"' ");
//			sql.append(" AND QCPA_QCAP_KEYID ='"+CapaId+"' ");
//		   }
//	    sql.append(" ORDER BY ");
//	    sql.append(" QCPA_QCAP_KEYID ");
//	    System.out.println("SQL is::::"+sql);
//		 }
//		 else {
//			 System.out.println("sys");
//		 }
//	    List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
//		return gridData;
//	}
	
	@Override
	public List<String[]> getCapaDetails(CommonFilter commonFilter) throws Exception {

	    StringBuffer sql = new StringBuffer();
	    String CapaId = commonFilter.getKey();

	    if (UIUtils.isValidKeyId(CapaId)) {

	        sql.append(" SELECT ");
	        sql.append(" q.QCPA_KEYID, ");
	        sql.append(" q.QCPA_QCAP_KEYID, ");
	        sql.append(" q.QCPA_CORRECTIVEACTION, ");
	        sql.append(" q.QCPA_ROOTCAUSE, ");
	        sql.append(" q.QCPA_PREVENTIVEACTION, ");
	        sql.append(" q.QCPA_4MCONDITION, ");
	        sql.append(" q.QCPA_DEFECTTYPE, ");
	        sql.append(" e.EMPM_NAME, ");
	        sql.append(" q.QCPA_RESPONSIBLEID, ");
	        sql.append(" TO_CHAR(q.QCPA_TARGETDATE,'DD-mon-YYYY'), ");

	        // DECODE -> CASE (same mapping; if no match returns NULL like Oracle DECODE without default)
	        sql.append(" CASE q.QCPA_STATUS ");
	        sql.append("   WHEN 'P' THEN 'Pending' ");
	        sql.append("   WHEN 'C' THEN 'Completed' ");
	        sql.append("   WHEN 'W' THEN 'Work in Progress' ");
	        sql.append("   WHEN '-' THEN 'Not Assigned' ");
	        sql.append(" END, ");

	        // Oracle '' is NULL; in Postgres use NULL::text to mirror Oracle behaviour
	        sql.append(" NULL::text, ap.APLM_KEYID, NULL::text, ww.WWMS_KEYID, NULL::text, kz.KZBN_KEYID, NULL::text, NULL::text ");

	        sql.append(" FROM QTM_TL_CAPACTIONMST q ");
	        sql.append(" JOIN GEN_TL_EMPLOYEEMST e ON e.EMPM_KEYID = q.QCPA_RESPONSIBLEID ");

	        // aplm_masterrefid(+)=QCPA_QCAP_KEYID  -> LEFT JOIN actionplan
	        sql.append(" LEFT JOIN GEN_TL_ACTIONPLANMST ap ON ap.APLM_MASTERREFID = q.QCPA_QCAP_KEYID ");

	        // QCPA_QCAP_KEYID=KZBN_REFDOCNO(+) -> LEFT JOIN kaizen
	        sql.append(" LEFT JOIN KZN_TL_KAIZENBANKMST kz ON kz.KZBN_REFDOCNO = q.QCPA_QCAP_KEYID ");

	        // WWMS_REFDOCNO(+)=QCPA_QCAP_KEYID -> LEFT JOIN whywhy
	        sql.append(" LEFT JOIN BDM_TL_WHYWHYMST ww ON ww.WWMS_REFDOCNO = q.QCPA_QCAP_KEYID ");

	        sql.append(" WHERE 1=1 ");

	        if (UIUtils.isValidKeyId(CapaId)) {
	            sql.append(" AND q.QCPA_QCAP_KEYID = '" + CapaId + "' ");
	        }

	        sql.append(" ORDER BY q.QCPA_QCAP_KEYID ");

	        System.out.println("SQL is::::" + sql);
	    }
	    else {
	        System.out.println("sys");
	    }

	    List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
	    return gridData;
	}

	
	// ---------- Vignesh 22Dec2025 -------------------------------------------------------//
	
	@Override
	public QtmTlCapaActionmst getCapaActionPlanUpdate(QtmTlCapaActionmst qtmTlCapaActionmst,
			QtmTlCapaActionmst existQtmTlCapaActionmst, String keyid, String preventiveAction, String rootCause,
			String targetDate, String status, String mType, String defect, String correctiveAction,
			String responsibility,String CAPA) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		
		if(status.equals("A")){
			
			
		sql.append(" UPDATE QTM_TL_CAPACTIONMST SET QCPA_CORRECTIVEACTION='"+correctiveAction+"', QCPA_ROOTCAUSE='"+rootCause+"',QCPA_PREVENTIVEACTION='"+preventiveAction+"',QCPA_4MCONDITION='"+mType+"',QCPA_DEFECTTYPE='"+defect+"',QCPA_RESPONSIBLEID='"+responsibility+"',QCPA_COMPLETEDON='"+targetDate+"',QCPA_STATUS='"+status+"' WHERE QCPA_KEYID='"+CAPA+"' ");
		   System.out.println("RoleConfig"+sql);
		   
		}

		else{
			sql.append(" UPDATE QTM_TL_CAPACTIONMST SET QCPA_CORRECTIVEACTION='"+correctiveAction+"', QCPA_ROOTCAUSE='"+rootCause+"',QCPA_PREVENTIVEACTION='"+preventiveAction+"',QCPA_4MCONDITION='"+mType+"',QCPA_DEFECTTYPE='"+defect+"',QCPA_RESPONSIBLEID='"+responsibility+"',QCPA_TARGETDATE='"+targetDate+"',QCPA_STATUS='"+status+"' WHERE QCPA_KEYID='"+CAPA+"' ");
			System.out.println("RoleConfig"+sql);
		}
		dbActionTemplate.executeStatement(sql.toString());
			return qtmTlCapaActionmst;
	}
	@Override
	public String getTargetFlid(String targetJH) throws Exception {
		// TODO Auto-generated method stub
		  String TargetFlid=dbActionTemplate.getSingleValue("GEN_TL_FUNCTIONALLOCN","FNLN_KEYID","FNLN_ORIGINALID",targetJH);
		   System.out.println("ActionDetailId"+TargetFlid);
		   return TargetFlid;
	}
	
	@Override
	public List<String[]> getCAPAEmpMailIds(String complaintNo) throws Exception {
		// TODO Auto-generated method stub
	
		StringBuilder sql=new StringBuilder();
		String enableEmail="Y";
		
		
		 if(UIUtils.isValidKeyId(complaintNo)){ 
			 sql.append("select empm_name,empm_email FROM GEN_TL_EMPLOYEEMST  ,QTM_TL_CAPA WHERE EMPM_KEYID=QCAP_TODMTLEADER AND QCAP_KEYID='"+complaintNo+"'");
				sql.append(" UNION  ");
				sql.append(" select empm_name,empm_email FROM GEN_TL_EMPLOYEEMST  ,QTM_TL_CAPA WHERE EMPM_KEYID=QCAP_AREAINCHARGE AND QCAP_KEYID='"+complaintNo+"'");
				sql.append(" UNION  ");
				sql.append("select empm_name,empm_email FROM GEN_TL_EMPLOYEEMST  ,QTM_TL_CAPA WHERE EMPM_KEYID=QCAP_COMPLANTRAISEDBY AND QCAP_KEYID='"+complaintNo+"'");
				
				
				
	}
		  System.out.println("sql"+sql);
		
		 List<String[]> MailList=dbActionTemplate.getDataList(sql.toString());	
			return MailList;
	
	
	}
	
	
	//------------------------------------ 31Oct2025 ---- Vignesh ------------------------------------------------------//
	
	@Override
	public Workbook getExcelreport(CommonFilter commonFilter, JSONObject colModel, String format) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			   
			//  CommonFunctions.debugMsg("formats=="+formats);
			rs =getExcelResultSet(commonFilter);
			//CommonFunctions.debugMsg("rs.........");
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format,2,0,0);
			
		//	return excelUtils.writeToExcel(rs,format,2,0,1);
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
		
	}
	
	
	public ResultSet getExcelResultSet(CommonFilter commonFilter) throws Exception
	{
		ResultSet rs = null;
		try
		{
			List<String > paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);			
			paramValues.add(condParms);
			paramValues.add(commonParams);		
	         String Title = commonFilter.gettitle();
			
			System.out.println("Inside Dao IN EXCEL and the title is  " + Title);
			
			
			if("Report".equals(commonFilter.gettitle())) {
				rs = dbActionTemplate.NewdbFunctionCall2("QM_FN_CAPAREPORT", paramValues);
			} 
			else {
			
			rs = dbActionTemplate.NewdbFunctionCall2("QM_FN_CAPA", paramValues);
			}
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return rs;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}

	}
	
	@Override
	public QtmTlCapa update(QtmTlCapa newQtmTlCapa) throws Exception {
		// TODO Auto-generated method stub
List<String> sqls = new ArrayList<String>();
		
QtmTlCapaSql  qtmTlCapaSql=new QtmTlCapaSql();
		
		
		try {
			sqls.add(QtmTlCapaSql.getUpdateSql(qtmTlCapaSql.getQcapDbFields(), newQtmTlCapa.getSaveArray()));
			
			
			dbActionTemplate.executeStatements(sqls);	
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newQtmTlCapa;
	}
	@Override
	public QtmTlCapaWorkflow UpdateWorkflow(QtmTlCapaWorkflow qtmTlCapaWorkflow, String capaId,String targetDmtLeader, String status) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			System.out.println("IN Update ");
			  StringBuilder sql=new StringBuilder();
			  
		 sql.append("UPDATE qtm_tl_capaworkflow SET  QCWF_APPROVEDBY='"+targetDmtLeader+"' ,QCWF_STATUS='"+status+"' WHERE QCWF_CAPAREFID='"+capaId+"' ");
	System.out.println("Data Updated "+sql);
		 dbActionTemplate.executeStatement(sql.toString());	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return qtmTlCapaWorkflow;
	}
	
	//elumalai new remove data button
	@Override
	public QtmTlCapaActionmst deleteCapaActionPlanDetail(QtmTlCapaActionmst qtmTlCapaActionmst) throws Exception {

	    List<String> sqls = new ArrayList<String>();
	    QtmTlCapaActionmst existQtmTlCapaActionmst = new QtmTlCapaActionmst();

	    sqls.add("DELETE FROM QTM_TL_CAPACTIONMST WHERE QCPA_KEYID = '"
	            + qtmTlCapaActionmst.getQcpaKeyid() + "'");

	    dbActionTemplate.executeStatements(sqls);

	    return existQtmTlCapaActionmst;
	}

}
