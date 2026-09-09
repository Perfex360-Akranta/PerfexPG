package com.akranta.tpm.dao.sql;
import com.akranta.tpm.utils.CommonMessage;
public class EntTlTragcalmstSql{

	public static final String TBL_ENT_TL_TRGCALMST = "ENT_TL_TRGCALMST";  

	TableFieldType [] ftymDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, location, dmt, jh, topicid, createddatetime, remarks
		, calendardate, general, uniquepstn,msd,chkcompleted,completeddate,completedby, 
		maxduration,function, venue,permittedstnt,materialready,
	    assessmentrequ,marksbased,filemanagerid,anchoredby,trainingfunction,rating,
		comments,topiccategory,tempfield6,tempfield7,tempfield8,tempfield9,tempfield10
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFtymDbFields() {
		return ftymDbFields;
	}

	public EntTlTragcalmstSql()
	{
		ftymDbFields = new TableFieldType[ 37 ];
		for(int i = 0;i < 37; i++)
		{	
			ftymDbFields[ i ] = new TableFieldType();
		}
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ETCM_KEYID";
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "ETCM_FLID";
		ftymDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.location.ordinal() ].fieldName = "ETCM_LOCATION";
		ftymDbFields[ tableFldConstants.location.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.dmt.ordinal() ].fieldName = "ETCM_DMT";
		ftymDbFields[ tableFldConstants.dmt.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.jh.ordinal() ].fieldName = "ETCM_JH";
		ftymDbFields[ tableFldConstants.jh.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.topicid.ordinal() ].fieldName = "ETCM_TOPICID";
		ftymDbFields[ tableFldConstants.topicid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.createddatetime.ordinal() ].fieldName = "ETCM_CREATEDATETIME";
		ftymDbFields[ tableFldConstants.createddatetime.ordinal() ].fieldType = 'T';

		ftymDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "ETCM_REMARKS";
		ftymDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.calendardate.ordinal() ].fieldName = "ETCM_CALDATE";
		ftymDbFields[ tableFldConstants.calendardate.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.general.ordinal() ].fieldName = "ETCM_GENERAL";
		ftymDbFields[ tableFldConstants.general.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.uniquepstn.ordinal() ].fieldName = "ETCM_UNIQUEPOS";
		ftymDbFields[ tableFldConstants.uniquepstn.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.msd.ordinal() ].fieldName = "ETCM_MSD";
		ftymDbFields[ tableFldConstants.msd.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.chkcompleted.ordinal() ].fieldName = "ETCM_CHKCOMPLETED";
		ftymDbFields[ tableFldConstants.chkcompleted.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.completeddate.ordinal() ].fieldName = "ETCM_COMPLETEDDATE";
		ftymDbFields[ tableFldConstants.completeddate.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "ETCM_COMPLETEDBY";
		ftymDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.maxduration.ordinal() ].fieldName = "ETCM_MAX_DURATION";
		ftymDbFields[ tableFldConstants.maxduration.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.function.ordinal() ].fieldName = "ETCM_FUNCTION";
		ftymDbFields[ tableFldConstants.function.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.venue.ordinal() ].fieldName = "ETCM_VENUE";
		ftymDbFields[ tableFldConstants.venue.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.permittedstnt.ordinal() ].fieldName = "ETCM_PERMITTEDSTRENGTH";
		ftymDbFields[ tableFldConstants.permittedstnt.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.materialready.ordinal() ].fieldName = "ETCM_MATERIALREADY";
		ftymDbFields[ tableFldConstants.materialready.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.assessmentrequ.ordinal() ].fieldName = "ETCM_ASSESSMENTREQUIRED";
		ftymDbFields[ tableFldConstants.assessmentrequ.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.marksbased.ordinal() ].fieldName = "ETCM_MARKBASED";
		ftymDbFields[ tableFldConstants.marksbased.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.filemanagerid.ordinal() ].fieldName = "ETCM_FILEMGNID";
		ftymDbFields[ tableFldConstants.filemanagerid.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.anchoredby.ordinal() ].fieldName = "ETCM_ANCHOREDBY";
		ftymDbFields[ tableFldConstants.anchoredby.ordinal() ].fieldType = 'V';
		
		ftymDbFields[ tableFldConstants.trainingfunction.ordinal() ].fieldName = "ETCM_TRAININGFUNCTION";
		ftymDbFields[ tableFldConstants.trainingfunction.ordinal() ].fieldType = 'V';
		
		ftymDbFields[ tableFldConstants.rating.ordinal() ].fieldName = "ETCM_RATING";
		ftymDbFields[ tableFldConstants.rating.ordinal() ].fieldType = 'N';
		
		ftymDbFields[ tableFldConstants.comments.ordinal() ].fieldName = "ETCM_COMMENTS";
		ftymDbFields[ tableFldConstants.comments.ordinal() ].fieldType = 'V';
		
		ftymDbFields[ tableFldConstants.topiccategory.ordinal() ].fieldName = "ETCM_TOPICCATEGORY";
		ftymDbFields[ tableFldConstants.topiccategory.ordinal() ].fieldType = 'V';
		
		ftymDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "ETCM_TEMPFIELD6";
		ftymDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "ETCM_TEMPFIELD7";
		ftymDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "ETCM_TEMPFIELD8";
		ftymDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "ETCM_TEMPFIELD9";
		ftymDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "ETCM_TEMPFIELD10";
		ftymDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'C';
        
		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ETCM_ACTIVE";
		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ETCM_CREATEDBY";
		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ETCM_CREATEDON";
		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ETCM_MODIFIEDON";
		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TRGCALMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TRGCALMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TRGCALMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String selectDataMst(){
		 String sql= "select * from ENT_TL_TRGCALMST WHERE ETCM_KEYID =?";
			// select * from " + TBL_WOM_TL_REVISIONMST + " where WRVM_KEYID= ?
		CommonMessage.debugMsg("created Query"+sql.toString());	
		 return sql;
	}
	/*public static String getTrainingEmailIds(){
	StringBuilder sql=new StringBuilder();
	String enableEmail="Y";
	sql.append("SELECT EMPM_NAME,EMPM_EMAIL FROM GEN_TL_EMPLOYEEMST,ENT_TL_TRGFACULTY, ");
	sql.append("ENT_TL_FACULTYMST,ENT_TL_TRGCALMST WHERE FTYM_EMPM_KEYID (+) = EMPM_KEYID ");
	sql.append("AND ETCF_ETCM_KEYID=ETCM_KEYID(+) AND FTYM_EMPM_KEYID(+)=EMPM_KEYID ");
	sql.append("AND FTYM_KEYID=ETCF_FACULTYID AND ETCF_ETCM_KEYID = ? AND EMPM_KEYID ");
	sql.append("IN (SELECT EMPM_KEYID FROM GEN_TL_EMPLOYEEMST WHERE EMPM_ENABLEEMAIL='"+enableEmail+"') ");
	CommonMessage.debugMsg("The EmilIdList"+sql.toString());
	return sql.toString();
	}
   public static String selectTrainingRelatedFileManager(String keyId){
	   StringBuilder sql=new StringBuilder();
	   sql.append("SELECT DMDM_FILENAME,DMDM_PATH FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_REFDOCNO='"+keyId+"' ");	   
	   return sql.toString();
   }*/

	// ---------- VIGNESH   25Nov2025 -------------------------------------------------//
   
	
	public static String getTrainingAttedenceData(String Trainingkeyid) {
	    CommonMessage.debugMsg("ENTERING 1 DATA: ATTENDANACE");
	    StringBuilder sb = new StringBuilder();

	    sb.append(" SELECT DISTINCT ");
	    sb.append("   ETCM.ETCM_KEYID      AS hdnEtcaEtcmKeyid,");
	    sb.append("   ETCA.ETCA_KEYID      AS hdnEtcaKeyid,");
	    sb.append("   ETCE.ETCE_KEYID      AS hdnEtcaEtceKeyid,");
	    sb.append("   ETCE.ETCE_EMPM_KEYID AS hdnEtcaEtceEmpmKeyid,");
	    sb.append("   ETCM.ETCM_TOPICID    AS hdnTopicId,");
	    sb.append("   EMP.EMPM_NAME        AS empname,");

	    // DECODE(EMPM_EMPLOYEETYPE,'M','Manager','R','Employee','B','Badli','C','Contract','A','Asosciate','O','Others')
	    sb.append("   CASE EMP.EMPM_EMPLOYEETYPE ");
	    sb.append("        WHEN 'M' THEN 'Manager' ");
	    sb.append("        WHEN 'R' THEN 'Employee' ");
	    sb.append("        WHEN 'B' THEN 'Badli' ");
	    sb.append("        WHEN 'C' THEN 'Contract' ");
	    sb.append("        WHEN 'A' THEN 'Asosciate' ");
	    sb.append("        WHEN 'O' THEN 'Others' ");
	    sb.append("   END AS EMPM_EMPLOYEETYPE,");

	    // decode(EMPM_GENDER,'M','Male','F','FEMALE')
	    sb.append("   CASE EMP.EMPM_GENDER ");
	    sb.append("        WHEN 'M' THEN 'Male' ");
	    sb.append("        WHEN 'F' THEN 'FEMALE' ");
	    sb.append("   END AS EMPM_GENDER,");

	    // DECODE (ETCA_PRSENTABSENT,'P', 'PRESENT','A', 'ABSENT')
	    sb.append("   CASE ETCA.ETCA_PRSENTABSENT ");
	    sb.append("        WHEN 'P' THEN 'PRESENT' ");
	    sb.append("        WHEN 'A' THEN 'ABSENT' ");
	    sb.append("   END AS cmbEtcaPresentAbsent,");

	    sb.append("   TO_CHAR(ETCA.ETCA_ATTDATE,'DD-MON-YYYY') AS dteEtcaAddDate,");

	    // Oracle: TO_CHAR(ETCA_SCORE)  ->  Postgres: cast numeric to text
	    sb.append("   ETCA.ETCA_SCORE::text AS txtEtcaScore,");

	    // DECODE(ETCA_RESULT,  'P', 'Pass',  'F', 'Fail')
	    sb.append("   CASE ETCA.ETCA_RESULT ");
	    sb.append("        WHEN 'P' THEN 'Pass' ");
	    sb.append("        WHEN 'F' THEN 'Fail' ");
	    sb.append("   END AS txtEtcaResult,");

	    sb.append("   ETCA.ETCA_REMARKS AS txtEtcaRemarks,");
	    sb.append("   '' AS btnFilManage ");

	    sb.append(" FROM ENT_TL_TRGCALMST              ETCM ");
	    sb.append(" JOIN ENT_TL_TRGCALEMP              ETCE ");
	    sb.append("      ON ETCM.ETCM_KEYID = ETCE.ETCE_ETCM_KEYID ");
	    sb.append(" JOIN GEN_TL_EMPLOYEEMST            EMP ");
	    sb.append("      ON ETCE.ETCE_EMPM_KEYID = EMP.EMPM_KEYID ");
	    sb.append(" LEFT JOIN ENT_TL_TRGCALEMPATSCORE  ETCA ");
	    sb.append("      ON ETCA.ETCA_ETCE_KEYID = ETCE.ETCE_KEYID ");

	    sb.append(" WHERE ETCM.ETCM_KEYID = '").append(Trainingkeyid).append("' ");

	    sb.append(" ORDER BY hdnEtcaKeyid ");

	    CommonMessage.debugMsg("Final Query::" + sb.toString());
	    return sb.toString();
	}


//   public static String getTrainingAttedenceData(String Trainingkeyid){
//	   CommonMessage.debugMsg("ENTERING 1 DATA: ATTENDANACE");  
//	   StringBuilder sb=new StringBuilder();
//	   sb.append(" SELECT DISTINCT ETCM_KEYID AS hdnEtcaEtcmKeyid,ETCA_KEYID AS hdnEtcaKeyid,ETCE_KEYID AS hdnEtcaEtceKeyid,ETCE_EMPM_KEYID AS hdnEtcaEtceEmpmKeyid, ");
//	   sb.append(" ETCM_TOPICID AS hdnTopicId,EMPM_NAME AS empname,DECODE(EMPM_EMPLOYEETYPE,'M','Manager','R','Employee','B','Badli','C','Contract','A','Asosciate','O','Others') AS EMPM_EMPLOYEETYPE,decode(EMPM_GENDER,'M','Male','F','FEMALE') AS EMPM_GENDER,DECODE (ETCA_PRSENTABSENT,'P', 'PRESENT','A', 'ABSENT') AS cmbEtcaPresentAbsent,");
//	   sb.append("TO_CHAR (ETCA_ATTDATE,'DD-MON-YYYY') AS dteEtcaAddDate,TO_CHAR(ETCA_SCORE) AS txtEtcaScore, DECODE(ETCA_RESULT,  'P', 'Pass',  'F', 'Fail') AS txtEtcaResult,");
//	   sb.append("ETCA_REMARKS AS txtEtcaRemarks,'' AS btnFilManage FROM GEN_TL_EMPLOYEEMST,ENT_TL_TRGCALEMPATSCORE, ENT_TL_TRGCALEMP,ENT_TL_TRGCALMST");
//	   sb.append(" WHERE 1=1 AND ETCM_KEYID = ETCE_ETCM_KEYID AND ETCE_EMPM_KEYID = EMPM_KEYID AND ETCA_ETCE_KEYID(+) = ETCE_KEYID");
//       sb.append(" AND ETCM_KEYID='"+Trainingkeyid+"' ORDER BY hdnEtcaKeyid ");
//       CommonMessage.debugMsg("Final Query::"+sb);
//	   return sb.toString();
//   }  
	


//   public static String getTrainingAttedenceEmployeeData(String Trainingkeyid){
//	   CommonMessage.debugMsg("ENTERING 1 DATA: EMPLOYEE");  
//	   StringBuilder sb=new StringBuilder();
//	   sb.append(" SELECT DISTINCT ETCM_KEYID AS hdnEtcaEtcmKeyid,ETCA_KEYID AS hdnEtcaKeyid,ETCE_KEYID AS hdnEtcaEtceKeyid,ETCE_EMPM_KEYID AS hdnEtcaEtceEmpmKeyid, ");
//	   sb.append(" ETCM_TOPICID AS hdnTopicId,EMPM_NAME AS empname,DECODE(EMPM_EMPLOYEETYPE,'M','Manager','R','Employee','B','Badli','C','Contract','A','Asosciate','O','Others') AS EMPM_EMPLOYEETYPE,decode(EMPM_GENDER,'M','Male','F','FEMALE') AS EMPM_GENDER,");
//	  sb.append (" TO_CHAR (ETCS_SESSIONDATE,'DD-MON-YYYY')||'-'|| ETCS_NAME  AS SESSIONNAME,");
//	  sb.append(" DECODE (ETCA_PRSENTABSENT,'P', 'PRESENT','A', 'ABSENT') AS cmbEtcaPresentAbsent,");
//	   sb.append("TO_CHAR (ETCA_ATTDATE,'DD-MON-YYYY') AS dteEtcaAddDate,TO_CHAR(ETCA_SCORE) AS txtEtcaScore, DECODE(ETCA_RESULT,  'P', 'Pass',  'F', 'Fail') AS txtEtcaResult,");
//	   sb.append(" ETCA_REMARKS AS txtEtcaRemarks,'' AS btnFilManage FROM GEN_TL_EMPLOYEEMST,ENT_TL_TRGCALEMPATSCORE, ENT_TL_TRGCALEMP,ENT_TL_TRGCALMST,ENT_TL_TRGCALSESSION");
//	   sb.append(" WHERE 1=1 AND ETCM_KEYID = ETCE_ETCM_KEYID AND ETCE_EMPM_KEYID = EMPM_KEYID AND ETCA_ETCE_KEYID(+) = ETCE_KEYID AND ETCE_ETCS_KEYID = ETCS_KEYID");
//       sb.append(" AND ETCM_KEYID='"+Trainingkeyid+"'   ORDER BY SESSIONNAME, hdnEtcaKeyid ");
//       CommonMessage.debugMsg("Final Query::"+sb);  
//	   return sb.toString();
//   } 
	public static String getTrainingAttedenceEmployeeData(String Trainingkeyid) {
	    CommonMessage.debugMsg("ENTERING 1 DATA: EMPLOYEE");
	    StringBuilder sb = new StringBuilder();

	    sb.append(" SELECT DISTINCT ");
	    sb.append("   ETCM.ETCM_KEYID      AS hdnEtcaEtcmKeyid,");
	    sb.append("   ETCA.ETCA_KEYID      AS hdnEtcaKeyid,");
	    sb.append("   ETCE.ETCE_KEYID      AS hdnEtcaEtceKeyid,");
	    sb.append("   ETCE.ETCE_EMPM_KEYID AS hdnEtcaEtceEmpmKeyid,");
	    sb.append("   ETCM.ETCM_TOPICID    AS hdnTopicId,");
	    sb.append("   EMPM.EMPM_NAME       AS empname,");

	    // DECODE(EMPM_EMPLOYEETYPE, ...) -> CASE
	    sb.append("   CASE EMPM.EMPM_EMPLOYEETYPE ");
	    sb.append("        WHEN 'M' THEN 'Manager' ");
	    sb.append("        WHEN 'R' THEN 'Employee' ");
	    sb.append("        WHEN 'B' THEN 'Badli' ");
	    sb.append("        WHEN 'C' THEN 'Contract' ");
	    sb.append("        WHEN 'A' THEN 'Asosciate' ");
	    sb.append("        WHEN 'O' THEN 'Others' ");
	    sb.append("   END AS EMPM_EMPLOYEETYPE,");

	    // decode(EMPM_GENDER, ...) -> CASE
	    sb.append("   CASE EMPM.EMPM_GENDER ");
	    sb.append("        WHEN 'M' THEN 'Male' ");
	    sb.append("        WHEN 'F' THEN 'FEMALE' ");
	    sb.append("   END AS EMPM_GENDER,");

	    // SESSIONNAME
	    sb.append("   TO_CHAR(ETCS.ETCS_SESSIONDATE,'DD-MON-YYYY') || '-' || ETCS.ETCS_NAME AS SESSIONNAME,");

	    // DECODE (ETCA_PRSENTABSENT, ...) -> CASE
	    sb.append("   CASE ETCA.ETCA_PRSENTABSENT ");
	    sb.append("        WHEN 'P' THEN 'PRESENT' ");
	    sb.append("        WHEN 'A' THEN 'ABSENT' ");
	    sb.append("   END AS cmbEtcaPresentAbsent,");

	    sb.append("   TO_CHAR(ETCA.ETCA_ATTDATE,'DD-MON-YYYY') AS dteEtcaAddDate,");

	    // Oracle: TO_CHAR(ETCA_SCORE) -> Postgres: cast numeric to text
	    sb.append("   ETCA.ETCA_SCORE::text AS txtEtcaScore,");

	    // DECODE(ETCA_RESULT, ...) -> CASE
	    sb.append("   CASE ETCA.ETCA_RESULT ");
	    sb.append("        WHEN 'P' THEN 'Pass' ");
	    sb.append("        WHEN 'F' THEN 'Fail' ");
	    sb.append("   END AS txtEtcaResult,");

	    sb.append("   ETCA.ETCA_REMARKS AS txtEtcaRemarks,");
	    sb.append("   '' AS btnFilManage ");

	    sb.append(" FROM GEN_TL_EMPLOYEEMST          EMPM ");
	    sb.append(" JOIN ENT_TL_TRGCALEMP            ETCE ");
	    sb.append("   ON ETCE.ETCE_EMPM_KEYID = EMPM.EMPM_KEYID ");
	    sb.append(" JOIN ENT_TL_TRGCALMST            ETCM ");
	    sb.append("   ON ETCM.ETCM_KEYID = ETCE.ETCE_ETCM_KEYID ");
	    sb.append(" JOIN ENT_TL_TRGCALSESSION        ETCS ");
	    sb.append("   ON ETCS.ETCS_KEYID = ETCE.ETCE_ETCS_KEYID ");

	    // ETCA_ETCE_KEYID(+) = ETCE_KEYID  -> LEFT JOIN in Postgres
	    sb.append(" LEFT JOIN ENT_TL_TRGCALEMPATSCORE ETCA ");
	    sb.append("   ON ETCA.ETCA_ETCE_KEYID = ETCE.ETCE_KEYID ");

	    sb.append(" WHERE ETCM.ETCM_KEYID = '").append(Trainingkeyid).append("' ");

	    // same ORDER BY (uses alias SESSIONNAME and hdnEtcaKeyid)
	    sb.append(" ORDER BY SESSIONNAME, hdnEtcaKeyid ");

	    CommonMessage.debugMsg("Final Query::" + sb);
	    return sb.toString();
	}

//   public static String getTrainingAttedenceEmployeeData(String Trainingkeyid) {
//	    CommonMessage.debugMsg("ENTERING 1 DATA: EMPLOYEE");
//	    StringBuilder sb = new StringBuilder();
//
//	    sb.append(" SELECT DISTINCT ");
//	    sb.append("   ETCM.ETCM_KEYID      AS hdnEtcaEtcmKeyid,");
//	    sb.append("   ETCA.ETCA_KEYID      AS hdnEtcaKeyid,");
//	    sb.append("   ETCE.ETCE_KEYID      AS hdnEtcaEtceKeyid,");
//	    sb.append("   ETCE.ETCE_EMPM_KEYID AS hdnEtcaEtceEmpmKeyid,");
//	    sb.append("   ETCM.ETCM_TOPICID    AS hdnTopicId,");
//	    sb.append("   EMPM.EMPM_NAME       AS empname,");
//
//	    // DECODE(EMPM_EMPLOYEETYPE, ...) -> CASE
//	    sb.append("   CASE EMPM.EMPM_EMPLOYEETYPE ");
//	    sb.append("        WHEN 'M' THEN 'Manager' ");
//	    sb.append("        WHEN 'R' THEN 'Employee' ");
//	    sb.append("        WHEN 'B' THEN 'Badli' ");
//	    sb.append("        WHEN 'C' THEN 'Contract' ");
//	    sb.append("        WHEN 'A' THEN 'Asosciate' ");
//	    sb.append("        WHEN 'O' THEN 'Others' ");
//	    sb.append("   END AS EMPM_EMPLOYEETYPE,");
//
//	    // decode(EMPM_GENDER, ...) -> CASE
//	    sb.append("   CASE EMPM.EMPM_GENDER ");
//	    sb.append("        WHEN 'M' THEN 'Male' ");
//	    sb.append("        WHEN 'F' THEN 'FEMALE' ");
//	    sb.append("   END AS EMPM_GENDER,");
//
//	    // SESSIONNAME
//	    sb.append("   TO_CHAR(ETCS.ETCS_SESSIONDATE,'DD-MON-YYYY') || '-' || ETCS.ETCS_NAME AS SESSIONNAME,");
//
//	    // DECODE (ETCA_PRSENTABSENT, ...) -> CASE
//	    sb.append("   CASE ETCA.ETCA_PRSENTABSENT ");
//	    sb.append("        WHEN 'P' THEN 'PRESENT' ");
//	    sb.append("        WHEN 'A' THEN 'ABSENT' ");
//	    sb.append("   END AS cmbEtcaPresentAbsent,");
//
//	    sb.append("   TO_CHAR(ETCA.ETCA_ATTDATE,'DD-MON-YYYY') AS dteEtcaAddDate,");
//	    sb.append("   TO_CHAR(ETCA.ETCA_SCORE) AS txtEtcaScore,");
//
//	    // DECODE(ETCA_RESULT, ...) -> CASE
//	    sb.append("   CASE ETCA.ETCA_RESULT ");
//	    sb.append("        WHEN 'P' THEN 'Pass' ");
//	    sb.append("        WHEN 'F' THEN 'Fail' ");
//	    sb.append("   END AS txtEtcaResult,");
//
//	    sb.append("   ETCA.ETCA_REMARKS AS txtEtcaRemarks,");
//	    sb.append("   '' AS btnFilManage ");
//
//	    sb.append(" FROM GEN_TL_EMPLOYEEMST          EMPM ");
//	    sb.append(" JOIN ENT_TL_TRGCALEMP            ETCE ");
//	    sb.append("   ON ETCE.ETCE_EMPM_KEYID = EMPM.EMPM_KEYID ");
//	    sb.append(" JOIN ENT_TL_TRGCALMST            ETCM ");
//	    sb.append("   ON ETCM.ETCM_KEYID = ETCE.ETCE_ETCM_KEYID ");
//	    sb.append(" JOIN ENT_TL_TRGCALSESSION        ETCS ");
//	    sb.append("   ON ETCS.ETCS_KEYID = ETCE.ETCE_ETCS_KEYID ");
//
//	    // ETCA_ETCE_KEYID(+) = ETCE_KEYID  -> LEFT JOIN
//	    sb.append(" LEFT JOIN ENT_TL_TRGCALEMPATSCORE ETCA ");
//	    sb.append("   ON ETCA.ETCA_ETCE_KEYID = ETCE.ETCE_KEYID ");
//
//	    sb.append(" WHERE ETCM.ETCM_KEYID = '").append(Trainingkeyid).append("' ");
//
//	    // same ORDER BY (uses alias SESSIONNAME and hdnEtcaKeyid)
//	    sb.append(" ORDER BY SESSIONNAME, hdnEtcaKeyid ");
//
//	    CommonMessage.debugMsg("Final Query::" + sb);
//	    return sb.toString();
//	}

   
	
//   public static String getTrainingAttedenceEmployeeData(String Trainingkeyid) {
//	    CommonMessage.debugMsg("ENTERING 1 DATA: EMPLOYEE");
//	    StringBuilder sb = new StringBuilder();
//
//	    sb.append(" SELECT DISTINCT ");
//	    sb.append(" ETCM.ETCM_KEYID      AS hdnEtcaEtcmKeyid,");
//	    sb.append(" ETCA.ETCA_KEYID      AS hdnEtcaKeyid,");
//	    sb.append(" ETCE.ETCE_KEYID      AS hdnEtcaEtceKeyid,");
//	    sb.append(" ETCE.ETCE_EMPM_KEYID AS hdnEtcaEtceEmpmKeyid,");
//	    sb.append(" ETCM.ETCM_TOPICID    AS hdnTopicId,");
//	    sb.append(" EMP.EMPM_NAME        AS empname,");
//
//	    // DECODE(EMPM_EMPLOYEETYPE,'M','Manager','R','Employee','B','Badli','C','Contract','A','Asosciate','O','Others')
//	    sb.append(" CASE EMP.EMPM_EMPLOYEETYPE ");
//	    sb.append("    WHEN 'M' THEN 'Manager' ");
//	    sb.append("    WHEN 'R' THEN 'Employee' ");
//	    sb.append("    WHEN 'B' THEN 'Badli' ");
//	    sb.append("    WHEN 'C' THEN 'Contract' ");
//	    sb.append("    WHEN 'A' THEN 'Asosciate' ");
//	    sb.append("    WHEN 'O' THEN 'Others' ");
//	    sb.append(" END AS EMPM_EMPLOYEETYPE,");
//
//	    // decode(EMPM_GENDER,'M','Male','F','FEMALE')
//	    sb.append(" CASE EMP.EMPM_GENDER ");
//	    sb.append("    WHEN 'M' THEN 'Male' ");
//	    sb.append("    WHEN 'F' THEN 'FEMALE' ");
//	    sb.append(" END AS EMPM_GENDER,");
//
//	    // TO_CHAR (ETCS_SESSIONDATE,'DD-MON-YYYY')||'-'|| ETCS_NAME  AS SESSIONNAME
//	    sb.append(" TO_CHAR(ETCS.ETCS_SESSIONDATE,'DD-MON-YYYY') || '-' || ETCS.ETCS_NAME AS SESSIONNAME,");
//
//	    // DECODE (ETCA_PRSENTABSENT,'P', 'PRESENT','A', 'ABSENT') AS cmbEtcaPresentAbsent
//	    sb.append(" CASE ETCA.ETCA_PRSENTABSENT ");
//	    sb.append("    WHEN 'P' THEN 'PRESENT' ");
//	    sb.append("    WHEN 'A' THEN 'ABSENT' ");
//	    sb.append(" END AS cmbEtcaPresentAbsent,");
//
//	    sb.append(" TO_CHAR(ETCA.ETCA_ATTDATE,'DD-MON-YYYY') AS dteEtcaAddDate,");
//	    sb.append(" TO_CHAR(ETCA.ETCA_SCORE) AS txtEtcaScore,");
//
//	    // DECODE(ETCA_RESULT,  'P', 'Pass',  'F', 'Fail') AS txtEtcaResult
//	    sb.append(" CASE ETCA.ETCA_RESULT ");
//	    sb.append("    WHEN 'P' THEN 'Pass' ");
//	    sb.append("    WHEN 'F' THEN 'Fail' ");
//	    sb.append(" END AS txtEtcaResult,");
//
//	    sb.append(" ETCA.ETCA_REMARKS AS txtEtcaRemarks,");
//	    sb.append(" '' AS btnFilManage ");
//
//	    sb.append(" FROM ENT_TL_TRGCALMST       ETCM ");
//	    sb.append(" JOIN ENT_TL_TRGCALEMP       ETCE ON ETCM.ETCM_KEYID      = ETCE.ETCE_ETCM_KEYID ");
//	    sb.append(" JOIN GEN_TL_EMPLOYEEMST     EMP  ON ETCE.ETCE_EMPM_KEYID = EMP.EMPM_KEYID ");
//	    sb.append(" LEFT JOIN ENT_TL_TRGCALEMPATSCORE ETCA ON ETCA.ETCA_ETCE_KEYID = ETCE.ETCE_KEYID ");
//	    sb.append(" JOIN ENT_TL_TRGCALSESSION   ETCS ON ETCE.ETCE_ETCS_KEYID = ETCS.ETCS_KEYID ");
//
//	    sb.append(" WHERE ETCM.ETCM_KEYID = '").append(Trainingkeyid).append("' ");
//	    sb.append(" ORDER BY SESSIONNAME, hdnEtcaKeyid ");
//
//	    CommonMessage.debugMsg("Final Query::" + sb);
//	    return sb.toString();
//	}

   
   
   
	// ---------- VIGNESH   25Nov2025 -------------------------------------------------//
   
   public static String getTrainingAttedenceEmployeeDataa(String Trainingkeyid){
	   CommonMessage.debugMsg("ENTERING 2 DATAA");  
	   StringBuilder sb=new StringBuilder();
	   sb.append(" SELECT DISTINCT ETCM_KEYID AS hdnEtcaEtcmKeyid,ETCA_KEYID AS hdnEtcaKeyid,ETCE_KEYID AS hdnEtcaEtceKeyid,ETCE_EMPM_KEYID AS hdnEtcaEtceEmpmKeyid, ");
	   sb.append(" ETCM_TOPICID AS hdnTopicId,EMPM_NAME AS empname,DECODE(EMPM_EMPLOYEETYPE,'M','Manager','R','Employee','B','Badli','C','Contract','A','Asosciate','O','Others') AS EMPM_EMPLOYEETYPE,decode(EMPM_GENDER,'M','Male','F','FEMALE') AS EMPM_GENDER,");
	  sb.append (" TO_CHAR (ETCS_SESSIONDATE,'DD-MON-YYYY')||'-'|| ETCS_NAME  AS SESSIONNAME,");
	  sb.append(" DECODE (ETCA_PRSENTABSENT,'P', 'PRESENT','A', 'ABSENT') AS cmbEtcaPresentAbsent,");
	   sb.append("TO_CHAR (ETCA_ATTDATE,'DD-MON-YYYY') AS dteEtcaAddDate,TO_CHAR(ETCA_SCORE) AS txtEtcaScore, DECODE(ETCA_RESULT,  'P', 'Pass',  'F', 'Fail') AS txtEtcaResult,");
	   sb.append(" ETCA_REMARKS AS txtEtcaRemarks,'' AS btnFilManage FROM GEN_TL_EMPLOYEEMST,ENT_TL_TRGCALEMPATSCORE, ENT_TL_TRGCALEMP,ENT_TL_TRGCALMST,ENT_TL_TRGCALSESSION");
	   sb.append(" WHERE 1=1 AND ETCM_KEYID = ETCE_ETCM_KEYID AND ETCE_EMPM_KEYID = EMPM_KEYID AND ETCA_ETCE_KEYID(+) = ETCE_KEYID AND ETCE_ETCS_KEYID = ETCS_KEYID");
       sb.append(" AND ETCM_KEYID='"+Trainingkeyid+"'   ORDER BY SESSIONNAME, hdnEtcaKeyid ");
       CommonMessage.debugMsg("Final Query::"+sb);  
	   return sb.toString();
   } 
}

