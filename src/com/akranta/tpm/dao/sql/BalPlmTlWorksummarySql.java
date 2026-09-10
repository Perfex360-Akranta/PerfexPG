package com.akranta.tpm.dao.sql;

import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.controller.BAL_UIUtils;
import com.akranta.tpm.utils.CommonFunctions;

public class BalPlmTlWorksummarySql {

	public static final String TBL_PLM_TL_WORKSUMMARY = "PLM_TL_WORKSUMMARY";  

	TableFieldType [] wksmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, wodetailid, wofeedbackid, spareflag, sparecost, manpowercost
		, contractorcost, othercost, accountedtime, unaccountedtime, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getWksmDbFields() {
		return wksmDbFields;
	}

	public BalPlmTlWorksummarySql()
	{
		wksmDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			wksmDbFields[ i ] = new TableFieldType();
		}
		wksmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WKSM_KEYID";
		wksmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		wksmDbFields[ tableFldConstants.wodetailid.ordinal() ].fieldName = "WKSM_WODETAILID";
		wksmDbFields[ tableFldConstants.wodetailid.ordinal() ].fieldType = 'V';

		wksmDbFields[ tableFldConstants.wofeedbackid.ordinal() ].fieldName = "WKSM_WOFEEDBACKID";
		wksmDbFields[ tableFldConstants.wofeedbackid.ordinal() ].fieldType = 'V';

		wksmDbFields[ tableFldConstants.spareflag.ordinal() ].fieldName = "WKSM_SPAREFLAG";
		wksmDbFields[ tableFldConstants.spareflag.ordinal() ].fieldType = 'C';

		wksmDbFields[ tableFldConstants.sparecost.ordinal() ].fieldName = "WKSM_SPARECOST";
		wksmDbFields[ tableFldConstants.sparecost.ordinal() ].fieldType = 'N';

		wksmDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldName = "WKSM_MANPOWERCOST";
		wksmDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldType = 'N';

		wksmDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldName = "WKSM_CONTRACTORCOST";
		wksmDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldType = 'N';

		wksmDbFields[ tableFldConstants.othercost.ordinal() ].fieldName = "WKSM_OTHERCOST";
		wksmDbFields[ tableFldConstants.othercost.ordinal() ].fieldType = 'N';

		wksmDbFields[ tableFldConstants.accountedtime.ordinal() ].fieldName = "WKSM_ACCOUNTEDTIME";
		wksmDbFields[ tableFldConstants.accountedtime.ordinal() ].fieldType = 'N';

		wksmDbFields[ tableFldConstants.unaccountedtime.ordinal() ].fieldName = "WKSM_UNACCOUNTEDTIME";
		wksmDbFields[ tableFldConstants.unaccountedtime.ordinal() ].fieldType = 'N';

		wksmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WKSM_CREATEDBY";
		wksmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wksmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WKSM_CREATEDON";
		wksmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wksmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WKSM_MODIFIEDON";
		wksmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_WORKSUMMARY, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_WORKSUMMARY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_WORKSUMMARY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getDelFeedBack(String wofbWodetailid) {
		// TODO Auto-generated method stub
		return  "DELETE FROM "+TableNames.TBL_PLM_TL_WOFEEDBACK +" WHERE WOFB_WODETAILID = '"+wofbWodetailid+"'" ;
		
	}

	public String getDelWoSumry(String wofbWodetailid) {
		// TODO Auto-generated method stub
		return  "DELETE FROM "+TableNames.TBL_PLM_TL_WORKSUMMARY +" WHERE WKSM_WODETAILID = '"+wofbWodetailid+"'" ;
	}

	public String updtCal(String wofbFeedbackid, String dateTime,
			String wofbCompletedby, String wksmKeyid, String fromDate, String wofbDuration,
			String pmCalendarId) {
		CommonFunctions.debugMsg(wofbFeedbackid+"--"+dateTime+"--"+wofbCompletedby+"--"+wksmKeyid+"--"+fromDate+"--"+wofbDuration+"--"+pmCalendarId);
		// TODO Auto-generated method stub
		return  "UPDATE PLM_TL_CALENDAR SET PMCL_FEEDBACKID = '"+wofbFeedbackid+"',"+
				"PMCL_STATUS = 'Y' ,PMCL_FEEDBACKDATE =  to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss'),"+
				"PMCL_COMPLETEDBY = '"+wofbCompletedby+"',  PMCL_WORKSUMMARYID = '"+wksmKeyid+"',"+
				"PMCL_EFFPLANCOMPDATE = TO_DATE('07-"+fromDate+"','DD-MON-YYYY') , PMCL_DOWNTIME ='"+wofbDuration+"'  WHERE  PMCL_KEYID = '"+pmCalendarId+"' ";
	}

	public String updtPlanConfig(String dateTime, String fromDate,
			String wofbMachineid, String assmbleyId) {
		// TODO Auto-generated method stub
		if(!BAL_UIUtils.isValidKeyId(assmbleyId))
			assmbleyId = "{}";
		return "UPDATE PLM_TL_PLANCONFIGURATION SET PPLC_MODIFIEDON = to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss') ,"+
			   "PPLC_MONTHLY ='01-"+fromDate+"' WHERE  ( PPLC_MACHINEID || PPLC_ASSEMBLYID ='"+wofbMachineid+"' || '"+
			   assmbleyId+"' or PPLC_MACHINEID || PPLC_ASSEMBLYID ='"+wofbMachineid+"' || '"+ assmbleyId +"'   ) ";
	}

	public String updtWOdtl(String wofbFeedbackid, String wofbCompletedby,
			String dateTime, String wofbWodetailid) {
		// TODO Auto-generated method stub
		return "UPDATE PLM_TL_WODTL SET PWDD_FEEDBACKID = '"+wofbFeedbackid+"',"+
				//"PWDD_COMPLETEDBY = '"+wofbCompletedby+"',"+
				//"PWDD_COMPLETEDDATE = to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss'),"+
				"PWDD_STATUS = 'C'  WHERE PWDD_WODETAILID = '"+wofbWodetailid+"'";
	}

	public String updtWOmst(String dateTime, String wOmstId) {
		// TODO Auto-generated method stub
		return "UPDATE PLM_TL_WOMST SET PWDM_MODIFIEDON=to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss') , PWDM_WOSTATUS=  (  SELECT MAX(PWDD_STATUS)  FROM PLM_TL_WODTL"+
			   " WHERE PWDD_WOMASTERID = PWDM_WORKORDERNO  )  WHERE PWDM_WORKORDERNO ='"+wOmstId+"'";
	}

	public String updtWOfeed(String dateTime, String wofbWodetailid) {
		// TODO Auto-generated method stub
		return "UPDATE PLM_TL_WOFEEDBACK SET  WOFB_MODIFIEDON  =  to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss') WHERE   WOFB_WODETAILID = '"+wofbWodetailid+"'";
	}

	public String updtWOsum(String dateTime, String wofbWodetailid) {
		// TODO Auto-generated method stub
		return " UPDATE PLM_TL_WORKSUMMARY SET WKSM_MODIFIEDON = to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss') WHERE   WKSM_WODETAILID = '"+wofbWodetailid+"'";
	}

	public String updtWOrelease(String wofbCompletedby, String dateTime,String wOmstId) {
		// TODO Auto-generated method stub
		return 	"UPDATE PLM_TL_WORKRELEASE SET  PMWR_COMPLETEDBY ='"+wofbCompletedby+"' , PMWR_COMPLETEDFLAG ='Y' ,  PMWR_COMPLETEDDATE=to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss')"+
			    ",PMWR_STATUS ='W'  WHERE PMWR_PMWORKORDERNO ='"+wOmstId+"'";
	}

//	public static String getMultipleRespSql(String pmwoKeyid,
//			CommonParams commonParams) {
//		// TODO Auto-generated method stub
//		StringBuffer str=new StringBuffer();
//		str.append("select EMPM_KEYID ,EMPM_NAME  EMPM_NAME,EMPM_CODE  EMPM_CODE ,PMRS_ALLOTED_EMPID,PMRS_COMPLETED_EMPID ");
//		str.append("from gen_tl_employeemst,PLM_TL_MULTIPLE_RESP where EMPM_ACTIVE='Y' ");
//		//str.append("and EMPM_KEYID=BDRS_RESP_EMPID(+) ");
//		if(BAL_UIUtils.isValidKeyId(pmwoKeyid)){
//			str.append("AND EMPM_KEYID=PMRS_COMPLETED_EMPID(+) ");
//			str.append("AND BDRS_REFID(+)='"+pmwoKeyid+"' ");
//		}
//		else
//			str.append("AND EMPM_KEYID=PMRS_ALLOTED_EMPID(+) ");
//		str.append("and EMPM_KEYID IN (SELECT distinct FRT_EMPM_KEYID  ");
//		str.append("FROM GEN_TL_FNLNROLETEAM, GEN_MV_FLIDHIERARCHY "); 
//		str.append("WHERE FRT_FNLN_KEYID = FLID AND INSTR(PARENTFLIDS||FLID,(SELECT CELL_FLID FROM GEN_TL_CELLMST ");
//		str.append("WHERE CELL_KEYID IN (SELECT MCHM_CELLID FROM GEN_TL_machinemst WHERE MCHM_KEYID='"+commonParams.getFlid()+"')))>0 ) ");
//		str.append("order by EMPM_NAME  ");
//		CommonFunctions.debugMsg("SQL RESP "+str);
//		return str.toString();
//	}
	//Swetha - Work Order Changes - 05 Sep
	public static String getMultipleRespSql(String pmwoKeyid,
	        CommonParams commonParams) {

	    StringBuffer str = new StringBuffer();

	    str.append("SELECT EMPM_KEYID, ");
	    str.append("       EMPM_NAME AS EMPM_NAME, ");
	    str.append("       EMPM_CODE AS EMPM_CODE, ");
	    str.append("       PMRS_ALLOTED_EMPID, ");
	    str.append("       PMRS_COMPLETED_EMPID ");

	    str.append("FROM GEN_TL_EMPLOYEEMST ");
	    str.append("LEFT JOIN BAL_PLM_TL_MULTIPLE_RESP ");
	    str.append("       ON EMPM_KEYID = PMRS_COMPLETED_EMPID ");
	    str.append("WHERE EMPM_ACTIVE = 'Y' ");

	    if (BAL_UIUtils.isValidKeyId(pmwoKeyid)) {

	        str.append("AND PMRS_REFID = '");
	        str.append(pmwoKeyid);
	        str.append("' ");

	    } else {

	        str.append("AND EMPM_KEYID = PMRS_ALLOTED_EMPID ");

	    }

	    str.append("AND EMPM_KEYID IN ( ");
	    str.append("    SELECT DISTINCT FRT_EMPM_KEYID ");
	    str.append("    FROM GEN_TL_FNLNROLETEAM, GEN_MV_FLIDHIERARCHY ");
	    str.append("    WHERE FRT_FNLN_KEYID = FLID ");
	    str.append("    AND (PARENTFLIDS || FLID::text) LIKE '%' || ");
	    str.append("        (SELECT CELL_FLID ");
	    str.append("         FROM GEN_TL_CELLMST ");
	    str.append("         WHERE CELL_KEYID IN ( ");
	    str.append("             SELECT MCHM_CELLID ");
	    str.append("             FROM GEN_TL_MACHINEMST ");
	    str.append("             WHERE MCHM_KEYID = '");
	    str.append(commonParams.getFlid());
	    str.append("')) || '%' ");
	    str.append(") ");

	    str.append("ORDER BY EMPM_NAME ");

	    CommonFunctions.debugMsg("SQL RESP " + str);

	    return str.toString();
	}

}

