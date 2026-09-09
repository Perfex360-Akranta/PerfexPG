package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.dao.QtmTlSapCustComplaintsDao;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.ExcelUtils;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class QtmTlSapCustComplaintsDaoImpl implements QtmTlSapCustComplaintsDao  {
	private DBActionTemplate dbActionTemplate; 

	public QtmTlSapCustComplaintsDaoImpl(DBActionTemplate dbActionTemplate) throws Exception
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public DBActionTemplate getDbActionTemplate() {
		return this.dbActionTemplate;
	}
	//Change By Swetha - 9Dec
	public List<String[]> custComList() throws Exception {
	    StringBuilder sql = new StringBuilder();

	    sql.append(" SELECT QCCP_SAPTRANSID as \"Transaction Number.\", QCCP_SAPTRANSDATE as \"Complaint Date\", ");
	    sql.append(" QCCP_PLANT as \"Plant\", '' as \" Why-Why\", ");
	    sql.append(" ( SELECT STRING_AGG(WWMS_KEYID, ',' ORDER BY WWMS_KEYID) FROM BDM_TL_WHYWHYMST ");
	    sql.append(" WHERE WWMS_REFDOCNO NOT IN ('-', '{}') AND WWMS_REFDOCNO = QCCP_SAPTRANSID ");
	    sql.append(" ) ");
	    sql.append(" as \"Why-Why No\", ");
	    sql.append(" '' as \"Kaizen\", ");
	    sql.append(" ( SELECT STRING_AGG(KZBN_KEYID, ',' ORDER BY KZBN_KEYID) FROM KZN_TL_KAIZENBANKMST ");
	    sql.append(" WHERE KZBN_REFDOCNO <> '-' AND KZBN_REFDOCNO = QCCP_SAPTRANSID ");
	    sql.append(" ) ");
	    sql.append(" as \"Kaizen No\", ");
	    sql.append(" '' as \"ActionPlan\", ");
	    sql.append(" ( SELECT STRING_AGG(APLM_KEYID, ',' ORDER BY APLM_KEYID) FROM GEN_TL_ACTIONPLANMST ");
	    sql.append(" WHERE APLM_MASTERREFID NOT IN ('-', '{}') AND APLM_MASTERREFID = QCCP_SAPTRANSID ");
	    sql.append(" ) ");
	    sql.append(" as \"ActionPlan No\", ");
	    sql.append(" QCCP_MILL as \"Mill\", '' as \"filemanager \", QCCP_MACHINEID as \"Machine ID\", QCCP_BILLINGDOC as \"Billing Document\", ");
	    sql.append(" QCCP_MATERIAL as \"Material\", QCCP_GRADE as \"Grade\", QCCP_SALESOFFICE as \"Sales Office\", ");
	    sql.append(" QCCP_CATDESC as \"Category Description\", QCCP_SHIPTOPARTY as \"Ship To Party\", QCCP_SOLDTOPARTY as \"Sold To Party\", QCCP_EMPRESPONSIBLE as \"Employee Responsible\", ");
	    sql.append(" QCCP_POSTINGDATE as \"Posting (Complaint) Date\", QCCP_REFDOCDATE as \"Refer doc Date\", QCCP_QCANALYZEDATE as \"QC Analysis Completed Date\", ");
	    sql.append(" QCCP_MILLFEEDBACKDATE as \"Waiting mill feedbac\", QCCP_DATEJUSTIFIED as \"Justified date\", QCCP_DATEUNJUSTIFIED as \"Unjustified date\", QCCP_SENDTOQSC as \"Send to QSC\", ");
	    sql.append(" QCCP_SENDTOQCIC as \"Send to QCIC\", QCCP_BRANCHJUSTIFIED as \"Branch Justified\", QCCP_BRANCHUNJUSTIFIED as \"Branch Unjustified\", ");
	    sql.append(" QCCP_FINALSTATUS as \"Final Status\", QCCP_SAMPLESENTDATE as \"Sample SendDate\", QCCP_SAMPLERECVDATE as \"Sample Recivied  Date\", ");
	    sql.append(" QCCP_MFGDATE as \"Manufacturing Date\", QCCP_REMOTESHEETING as \"Remote Sheeting\", QCCP_SHEETERDATA as \"Sheeter Data\", QCCP_SHEETERNUM as \"Sheeter number\", ");
	    sql.append(" QCCP_PACKTYPE as \"packing type\", QCCP_CAUSEDESC as \"Cause Description\", QCCP_COMPNATURE as \"Nature of complaint\", QCCP_RUNID as \"Run ID\", ");
	    sql.append(" QCCP_NCP as \"NCP\", QCCP_WIS as \"WIS\", QCCP_FINALSTATUSDATE as \"Final Status Date\", QCCP_COMPLAINTQTY as \"Complaint QTY\", ");
	    sql.append(" QCCP_CLAIMVALUE as \"Claim value\", ");
	    sql.append(" QCCP_REMARKS as \"Remarks\", ");
	    sql.append(" QCCP_STATUS as \"Status\", ");
	    sql.append(" QCCP_COMPLETEDBY as \"Completed By\", ");
	    sql.append(" QCCP_COMPLETEDON as \"Completed On\" ");
	    sql.append(" FROM QTM_TL_SAPCUSTCOMPLAINTS ");
	    sql.append(" ORDER BY QCCP_SAPTRANSDATE DESC ");
	    
	    CommonMessage.debugMsg("The Final Data:::" + sql.toString());
	    return dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
	}
   
    public Workbook getCustomerComplaintExportToExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception
	{
		ResultSet rs = null;
		try
		{
			rs =   getCustomerComplaintResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,0, 0,0 );
			
		 }
		finally
		 {
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	
	}
	
	
	private ResultSet getCustomerComplaintResultSet(CommonFilter commonFilter) throws Exception
	{
		StringBuffer sql=getCustomerCompaintexcelSql(commonFilter);
		return dbActionTemplate.getData(sql.toString());
		
	}
	public StringBuffer getCustomerCompaintexcelSql(CommonFilter commonFilter) {
	    StringBuffer sql = new StringBuffer();

	    sql.append(" SELECT QCCP_SAPTRANSID as \"Transaction Number.\", QCCP_SAPTRANSDATE as \"Complaint Date\", ");
	    sql.append(" QCCP_PLANT as \"Plant\", '' as \" Why-Why\", ");
	    sql.append(" ( SELECT STRING_AGG(WWMS_KEYID, ',' ORDER BY WWMS_KEYID) FROM BDM_TL_WHYWHYMST ");
	    sql.append(" WHERE WWMS_REFDOCNO NOT IN ('-', '{}') AND WWMS_REFDOCNO = QCCP_SAPTRANSID ");
	    sql.append(" ) ");
	    sql.append(" as \"Why-Why No\", ");
	    sql.append(" '' as \"Kaizen\", ");
	    sql.append(" ( SELECT STRING_AGG(KZBN_KEYID, ',' ORDER BY KZBN_KEYID) FROM KZN_TL_KAIZENBANKMST ");
	    sql.append(" WHERE KZBN_REFDOCNO <> '-' AND KZBN_REFDOCNO = QCCP_SAPTRANSID ");
	    sql.append(" ) ");
	    sql.append(" as \"Kaizen No\", ");
	    sql.append(" '' as \"ActionPlan\", ");
	    sql.append(" ( SELECT STRING_AGG(APLM_KEYID, ',' ORDER BY APLM_KEYID) FROM GEN_TL_ACTIONPLANMST ");
	    sql.append(" WHERE APLM_MASTERREFID NOT IN ('-', '{}') AND APLM_MASTERREFID = QCCP_SAPTRANSID ");
	    sql.append(" ) ");
	    sql.append(" as \"ActionPlan No\", ");
	    sql.append(" QCCP_MILL as \"Mill\", QCCP_MACHINEID as \"Machine ID\", QCCP_BILLINGDOC as \"Billing Document\", ");
	    sql.append(" QCCP_MATERIAL as \"Material\", QCCP_GRADE as \"Grade\", QCCP_SALESOFFICE as \"Sales Office\", ");
	    sql.append(" QCCP_CATDESC as \"Category Description\", QCCP_SHIPTOPARTY as \"Ship To Party\", QCCP_SOLDTOPARTY as \"Sold To Party\", QCCP_EMPRESPONSIBLE as \"Employee Responsible\", ");
	    sql.append(" QCCP_POSTINGDATE as \"Posting (Complaint) Date\", QCCP_REFDOCDATE as \"Refer doc Date\", QCCP_QCANALYZEDATE as \"QC Analysis Completed Date\", ");
	    sql.append(" QCCP_MILLFEEDBACKDATE as \"Waiting mill feedbac\", QCCP_DATEJUSTIFIED as \"Justified date\", QCCP_DATEUNJUSTIFIED as \"Unjustified date\", QCCP_SENDTOQSC as \"Send to QSC\", ");
	    sql.append(" QCCP_SENDTOQCIC as \"Send to QCIC\", QCCP_BRANCHJUSTIFIED as \"Branch Justified\", QCCP_BRANCHUNJUSTIFIED as \"Branch Unjustified\", ");
	    sql.append(" QCCP_FINALSTATUS as \"Final Status\", QCCP_SAMPLESENTDATE as \"Sample SendDate\", QCCP_SAMPLERECVDATE as \"Sample Recivied  Date\", ");
	    sql.append(" QCCP_MFGDATE as \"Manufacturing Date\", QCCP_REMOTESHEETING as \"Remote Sheeting\", QCCP_SHEETERDATA as \"Sheeter Data\", QCCP_SHEETERNUM as \"Sheeter number\", ");
	    sql.append(" QCCP_PACKTYPE as \"packing type\", QCCP_CAUSEDESC as \"Cause Description\", QCCP_COMPNATURE as \"Nature of complaint\", QCCP_RUNID as \"Run ID\", ");
	    sql.append(" QCCP_NCP as \"NCP\", QCCP_WIS as \"WIS\", QCCP_FINALSTATUSDATE as \"Final Status Date\", QCCP_COMPLAINTQTY as \"Complaint QTY\", ");
	    sql.append(" QCCP_CLAIMVALUE as \"Claim value\", ");
	    sql.append(" QCCP_REMARKS as \"Remarks\", ");
	    sql.append(" QCCP_STATUS as \"Status\", ");
	    sql.append(" QCCP_COMPLETEDBY as \"Completed By\", ");
	    sql.append(" QCCP_COMPLETEDON as \"Completed On\" ");
	    sql.append(" FROM QTM_TL_SAPCUSTCOMPLAINTS ");
	    sql.append(" ORDER BY QCCP_SAPTRANSDATE DESC ");
	    
	    CommonMessage.debugMsg("The Excel Data " + sql.toString());

	    return sql;
	}
    
    public List<String[]> customerList(String keyid) throws Exception{
    	StringBuilder sql = new StringBuilder();

		sql.append(" SELECT QCCP_SAPTRANSID  as \"Complaint No.\",QCCP_SAPTRANSDATE as \"SapTranse Date\",");
		sql.append(" QCCP_PLANT as \"Plant\", ");
   		sql.append(" ( SELECT LISTAGG(WWMS_KEYID,',') WITHIN GROUP(ORDER BY WWMS_KEYID)  FROM BDM_TL_WHYWHYMST ");
		sql.append(" where WWMS_REFDOCNO NOT IN  ('-','{}') AND WWMS_REFDOCNO = QCCP_KEYID ");
		sql.append(" ) ");
		sql.append("  as \"Why-Why No\",");
		//sql.append(" '' as \"Kaizen\",");
		sql.append(" (SELECT LISTAGG(KZBN_KEYID,',') WITHIN GROUP(ORDER BY KZBN_KEYID) FROM KZN_TL_KAIZENBANKMST ");
		sql.append(" WHERE KZBN_REFDOCNO <> '-' AND KZBN_REFDOCNO= QCCP_KEYID ");
		sql.append(" ) ");
		sql.append(" as \"Kaizen No\",");
		//sql.append(" '' as \"ActionPlan\",'' as \"ActionPlan No\",");
		sql.append(" '' as \"ActionPlan No\",");
		sql.append(" QCCP_MILL  as \"Mill\",QCCP_MACHINEID as \"Machine Id\",QCCP_BILLINGDOC as \"Billing Doc\", ");
		sql.append(" QCCP_MATERIAL as \"Material\",QCCP_GRADE as \"Grade\",QCCP_SALESOFFICE as \"Sales Office\", ");
		sql.append(" QCCP_CATDESC as \"CateDesc\",QCCP_SHIPTOPARTY as \"ShiptoParty\",QCCP_SOLDTOPARTY as \"SoldoParty\",QCCP_EMPRESPONSIBLE  as \"Emp Responsible\",");
		sql.append(" QCCP_POSTINGDATE as \"Posting Date\",QCCP_REFDOCDATE as \"RefDoc Date\",QCCP_QCANALYZEDATE as \"Analyzed Date\",");
		sql.append(" QCCP_MILLFEEDBACKDATE as \"Mill FeedBackDate\",QCCP_DATEJUSTIFIED as \"DateJustified\",QCCP_DATEUNJUSTIFIED as \"DateUnJustified\",QCCP_SENDTOQSC as \"SendToQsc\",");
		sql.append(" QCCP_SENDTOQCIC as \"SendToQcic\",QCCP_BRANCHJUSTIFIED as \"BranchJustified\",QCCP_BRANCHUNJUSTIFIED as \"BranchUnJustified\",");
		sql.append(" QCCP_FINALSTATUS as \"Final Status\",QCCP_SAMPLESENTDATE as \"Sample Send Date\",QCCP_SAMPLERECVDATE  as \"Sample Receive Date\",");
		sql.append(" QCCP_MFGDATE as \"MFGDate\",QCCP_REMOTESHEETING as \"Remote Sheeting\",QCCP_SHEETERDATA as \"SheetedData\",QCCP_SHEETERNUM as \"SheeterNum\",");
		sql.append(" QCCP_PACKTYPE as \"PackType\",QCCP_CAUSEDESC as \"CausedDesc\",QCCP_COMPNATURE as \"Compnature\",QCCP_RUNID as \"RunId\",");
		sql.append(" QCCP_NCP as \"NCP\",QCCP_WIS as \"WIS\",QCCP_FINALSTATUSDATE as \"FinalStatusDate\",QCCP_COMPLAINTQTY as \"Company Qty\",");
		sql.append(" QCCP_CLAIMVALUE as \"ClaimValue\" ");

		//sql.append(" , '' as \" Why-Why\", ");

	//	sql.append(" ( SELECT LISTAGG(WWMS_KEYID,',') WITHIN GROUP(ORDER BY WWMS_KEYID)  FROM BDM_TL_WHYWHYMST ");
	//	sql.append(" where WWMS_REFDOCNO NOT IN  ('-','{}') AND WWMS_REFDOCNO = QCCP_KEYID ");
	//	sql.append(" ) ");

		//sql.append("  as \"Why-Why Ref No\",'' as \"Suggestion\", ");

	//	sql.append(" ( SELECT LISTAGG(KZBN_KEYID,',') WITHIN GROUP(ORDER BY KZBN_KEYID)  FROM KZN_TL_KAIZENBANKMST ");
	//	sql.append(" where KZBN_REFDOCNO <> '-' AND KZBN_REFDOCNO = QCCP_KEYID ");
	//	sql.append(" ) ");

	//	sql.append(" as \"Kaizen Ref No.\" ");
		sql.append(" from QTM_TL_SAPCUSTCOMPLAINTS WHERE QCCP_SAPTRANSID='"+keyid+"' ");
		sql.append(" ORDER BY QCCP_SAPTRANSDATE DESC ");
		//CommonMessage.debugMsg("CC SQL :" + sql.toString());
		CommonMessage.debugMsg("The Final Data:::"+sql.toString());
		return dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
    }
}
	