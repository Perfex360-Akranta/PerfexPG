package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.FilterValues;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
//import com.sun.org.apache.bcel.internal.generic.RETURN;

public class EntTlAssessmentmstSql {
	public static final String TBL_ENT_TL_ENTTLASSESMENTMST = "ENT_TL_ENTTLASSESMENTMST";  

	TableFieldType [] asmmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, evaluation_desc, evaluation_date, evaluation_no,evaluation_type, is_locked
		, trar_keyid, role_keyid, empm_keyid, faculty, remarks
		, fact_id, assessedby, elementid, tempfield6, tempfield7
		, tempfield8, tempfield9, tempfield10, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getAsmmDbFields() {
		return asmmDbFields;
	}

	public EntTlAssessmentmstSql()
	{
		asmmDbFields = new TableFieldType[ 23 ];
		for(int i = 0;i < 23; i++)
		{	
			asmmDbFields[ i ] = new TableFieldType();
		}
		asmmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ASMM_KEYID";
		asmmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		asmmDbFields[ tableFldConstants.evaluation_desc.ordinal() ].fieldName = "ASMM_EVALUATION_DESC";
		asmmDbFields[ tableFldConstants.evaluation_desc.ordinal() ].fieldType = 'V';

		asmmDbFields[ tableFldConstants.evaluation_date.ordinal() ].fieldName = "ASMM_EVALUATION_DATE";
		asmmDbFields[ tableFldConstants.evaluation_date.ordinal() ].fieldType = 'D';

		asmmDbFields[ tableFldConstants.evaluation_no.ordinal() ].fieldName = "ASMM_EVALUATION_NO";
		asmmDbFields[ tableFldConstants.evaluation_no.ordinal() ].fieldType = 'N';
		
		asmmDbFields[ tableFldConstants.evaluation_type.ordinal() ].fieldName = "ASMM_EVALUATION_TYPE";
		asmmDbFields[ tableFldConstants.evaluation_type.ordinal() ].fieldType = 'N';

		asmmDbFields[ tableFldConstants.is_locked.ordinal() ].fieldName = "ASMM_IS_LOCKED";
		asmmDbFields[ tableFldConstants.is_locked.ordinal() ].fieldType = 'C';

		asmmDbFields[ tableFldConstants.trar_keyid.ordinal() ].fieldName = "ASMM_TRAR_KEYID";
		asmmDbFields[ tableFldConstants.trar_keyid.ordinal() ].fieldType = 'V';

		asmmDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "ASMM_ROLE_KEYID";
		asmmDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';

		asmmDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "ASMM_EMPM_KEYID";
		asmmDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		asmmDbFields[ tableFldConstants.faculty.ordinal() ].fieldName = "ASMM_FACULTY";
		asmmDbFields[ tableFldConstants.faculty.ordinal() ].fieldType = 'V';

		asmmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "ASMM_REMARKS";
		asmmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		asmmDbFields[ tableFldConstants.fact_id.ordinal() ].fieldName = "ASMM_FACT_ID";
		asmmDbFields[ tableFldConstants.fact_id.ordinal() ].fieldType = 'V';

		asmmDbFields[ tableFldConstants.assessedby.ordinal() ].fieldName = "ASMM_ASSESSEDBY";
		asmmDbFields[ tableFldConstants.assessedby.ordinal() ].fieldType = 'V';

		asmmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "ASMM_ELEMENTID";
		asmmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		asmmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "ASMM_TEMPFIELD6";
		asmmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		asmmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "ASMM_TEMPFIELD7";
		asmmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'C';

		asmmDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "ASMM_TEMPFIELD8";
		asmmDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'C';

		asmmDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "ASMM_TEMPFIELD9";
		asmmDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'C';

		asmmDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "ASMM_TEMPFIELD10";
		asmmDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'C';

		asmmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ASMM_ACTIVE";
		asmmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		asmmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ASMM_CREATEDBY";
		asmmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		asmmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ASMM_CREATEDON";
		asmmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		asmmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ASMM_MODIFIEDON";
		asmmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray){
		return SqlUtils.getInsertSql(TableNames.TBL_ENT_TL_ASSESSMENTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray){
		String sql = SqlUtils.getUpdateSql(TableNames.TBL_ENT_TL_ASSESSMENTMST, fieldTypeArr, dataArray);		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray){
		String sql = "DELETE from " + TableNames.TBL_ENT_TL_ASSESSMENTMST ;		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getSeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray) {
		String sql = "Select * from " + TableNames.TBL_ENT_TL_ASSESSMENTMST ;		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getEvaluationNo(TableFieldType [] fieldTypeArr, Object [] dataArray) {
		String sql = "Select NVL(max(ASMM_EVALUATION_NO),0)+1 AS EVALUATION_NO  from " + TableNames.TBL_ENT_TL_ASSESSMENTMST ;
		
		sql += " where 1=1 ";
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.trar_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.trar_keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.trar_keyid.ordinal()] + "'";
		}
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.role_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.role_keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'";
		}
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.empm_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.empm_keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'";
		}
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.evaluation_type.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.evaluation_type.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.evaluation_type.ordinal()] + "'";
		}
		return sql;
	}
	
	public static String getUpdateExists(TableFieldType [] fieldTypeArr, Object [] dataArray,String evaluationNo,String status) {
		String sql=null; 
		
		sql="Update " + TableNames.TBL_ENT_TL_ASSESSMENTMST + " set " +  fieldTypeArr[tableFldConstants.is_locked.ordinal()].fieldName  +
		" = '" + status + "'";
		sql += " where 1=1 ";
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.trar_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.trar_keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.trar_keyid.ordinal()] + "'";
		}
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.role_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.role_keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'";
		}
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.empm_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.empm_keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'";
		}
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.evaluation_type.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.evaluation_type.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.evaluation_type.ordinal()] + "'";
		}
		
		sql += " and " + fieldTypeArr[tableFldConstants.evaluation_no.ordinal()].fieldName  +
		" = '" +  evaluationNo + "'";
		
		return sql;
	}

	public static String getEvaluationLatest(TableFieldType [] fieldTypeArr, Object [] dataArray) {
		String sql=null;
		sql="select * from " + TableNames.TBL_ENT_TL_ASSESSMENTMST ;
		sql += " where 1=1 and ASMM_EVALUATION_NO In (";
		sql +=  "Select NVL(max(ASMM_EVALUATION_NO),0) AS EVALUATION_NO  from " + TableNames.TBL_ENT_TL_ASSESSMENTMST ;
		
		sql += " where 1=1 ";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.trar_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.trar_keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.trar_keyid.ordinal()] + "'";
		}
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.role_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.role_keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'";
		}
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.empm_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.empm_keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'";
		}
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.evaluation_type.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.evaluation_type.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.evaluation_type.ordinal()] + "'";
		}
		
		sql += " ) ";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.trar_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.trar_keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.trar_keyid.ordinal()] + "'";
		}
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.role_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.role_keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'";
		}
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.empm_keyid.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.empm_keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'";
		}
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.evaluation_type.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.evaluation_type.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.evaluation_type.ordinal()] + "'";
		}
		
		CommonMessage.debugMsg(" evaluation latest Sql:"+sql);      	
		return sql;
	}
	
	public static String getEvaluationGridData(TableFieldType [] fieldTypeArr, Object [] dataArray) {
		String sql = "Select NVL(max(ASMM_EVALUATION_NO),0)+1 AS EVALUATION_NO  from " + TableNames.TBL_ENT_TL_ASSESSMENTMST ;
		
		sql = " SELECT ASMD_KEYID, SPOK_NAME, EVALUATIONTYPE, TOPIC, RTRL_CUTOFF,  ";
		sql = sql + "ASMD_SCORE, ASMD_RESULT, PROG_NAME, BACH_NAME FROM  ";
		sql = sql + "ENT_TL_ASSESSMENTDTL, ENT_TL_PROGRAMMST, ENT_TL_BATCHMST, ";
		sql = sql + "(SELECT SPOK_NAME, EVAL_NAME AS EVALUATIONTYPE, TOPI_NAME AS TOPIC, RTRL_CUTOFF, RTLK_SPOK_ID, RTLK_TOPI_KEYID ";
		sql = sql + "FROM ENT_TL_ROLE_TOPIC_LINK, ENT_TL_ROLE_TOPIC_RATING,  ";
		sql = sql + "ENT_TL_SPOKEMST, ENT_TL_TOPICMST, ENT_TL_EVALUATIONTYPEMST ";
		sql = sql + "WHERE RTLK_KEYID = RTRL_RTLK_KEYID ";
		sql = sql + "AND RTLK_SPOK_ID = SPOK_KEYID ";
		sql = sql + "AND RTLK_TOPI_KEYID = TOPI_KEYID ";
		sql = sql + "AND TOPI_EVALUATIONTYPEID = EVAL_KEYID) ";
		sql = sql + "WHERE ASMD_SPOK_KEYID(+) = RTLK_SPOK_ID ";
		sql = sql + "AND ASMD_TOPI_KEYID(+) = RTLK_TOPI_KEYID ";
		sql = sql + "AND ASMD_PROG_KEYID = PROG_KEYID(+) ";
		sql = sql + "AND ASMD_BACH_KEYID = BACH_KEYID(+) ";
		
		sql += " and " + fieldTypeArr[tableFldConstants.trar_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.trar_keyid.ordinal()] + "'";
		
		sql += " and " + fieldTypeArr[tableFldConstants.role_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'";
		
		sql += " and " + fieldTypeArr[tableFldConstants.empm_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.evaluation_type.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.evaluation_type.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.evaluation_type.ordinal()] + "'";
		}
		
		return sql;
	}
	
	public static String getAll(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();	
		sql.append (" select *  from  (");
		sql.append(" SELECT A.* FROM(");
		sql.append(" SELECT BACH_KEYID,PROG_KEYID,BACH_NAME AS BATCH,PROG_NAME AS PROGRAM,");
		sql.append(" TO_CHAR(BACH_FROMDATE,'DD-MON-YYYY') AS BATCHSTARTDATE,");
		sql.append(" TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') AS BATCHENDDATE ,  MAX(EASM_EVALUATION_NO) AS EVALUATIONNO"); 
		sql.append(" FROM " + TableNames.TBL_ENT_TL_BATCHMST + "," + TableNames.TBL_ENT_TL_PROGRAMMST+ "," + TableNames.TBL_ENT_TL_ASSESSMENTMST );
		sql.append(" WHERE BACH_KEYID=EASM_BACH_KEYID (+)");
		sql.append(" AND PROG_KEYID=BACH_PROG_KEYID");
		sql.append(" AND BACH_PROG_KEYID=EASM_PROG_KEYID(+) AND BACH_STATUS='C'");
		sql.append(" GROUP BY PROG_KEYID,BACH_KEYID,PROG_NAME,BACH_NAME,");
		sql.append(" TO_CHAR(BACH_FROMDATE,'DD-MON-YYYY'), ");
		sql.append(" TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') ");
		sql.append(" ORDER BY  PROG_NAME,BACH_NAME");		
  		sql.append(")A where  1 = 1 ) " );     
  		CommonMessage.debugMsg("Sql:"+sql);
  		
  		return sql.toString();
	}
	
	public static String masterGrid(CommonFilter commonFilter) {
		StringBuffer sql = new StringBuffer();
		sql.append (" select *  from  (");
		sql.append(" SELECT A.* FROM(");
		sql.append(" SELECT BACH_KEYID,PROG_KEYID,BACH_NAME AS BATCH,PROG_NAME AS PROGRAM,");
		sql.append(" TO_CHAR(BACH_FROMDATE,'DD-MON-YYYY') AS BATCHSTARTDATE,");
		sql.append(" TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') AS BATCHENDDATE ,  MAX(EASM_EVALUATION_NO) AS EVALUATIONNO"); 
		sql.append(" FROM " + TableNames.TBL_ENT_TL_BATCHMST + "," + TableNames.TBL_ENT_TL_PROGRAMMST+ "," + TableNames.TBL_ENT_TL_ASSESSMENTMST );
		sql.append(" WHERE BACH_KEYID=EASM_BACH_KEYID (+)");
		sql.append(" AND PROG_KEYID=BACH_PROG_KEYID");
		sql.append(" AND BACH_PROG_KEYID=EASM_PROG_KEYID(+)  AND BACH_STATUS='C'");
		sql.append(" GROUP BY PROG_KEYID,BACH_KEYID,PROG_NAME,BACH_NAME,");
		sql.append(" TO_CHAR(BACH_FROMDATE,'DD-MON-YYYY'), ");
		sql.append(" TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') ");
		sql.append(" ORDER BY  PROG_NAME,BACH_NAME");		
  		sql.append(")A where  1 = 1 ) " );     
      	CommonMessage.debugMsg("Sql:"+sql);      	
      	return sql.toString(); 
	}
	
	public static String getCountAll(CommonFilter commonFilter) {		
		StringBuffer sql = new StringBuffer();
		sql.append (" select count(*)  from  (");
		sql.append(" SELECT A.* FROM(");
		sql.append(" SELECT BACH_KEYID,PROG_KEYID,BACH_NAME AS BATCH,PROG_NAME AS PROGRAM,");
		sql.append(" TO_CHAR(BACH_FROMDATE,'DD-MON-YYYY') AS BATCHSTARTDATE,");
		sql.append(" TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') AS BATCHENDDATE ,  MAX(EASM_EVALUATION_NO) AS EVALUATIONNO"); 
		sql.append(" FROM " + TableNames.TBL_ENT_TL_BATCHMST + "," + TableNames.TBL_ENT_TL_PROGRAMMST+ "," + TableNames.TBL_ENT_TL_ASSESSMENTMST );
		sql.append(" WHERE BACH_KEYID=EASM_BACH_KEYID (+)");
		sql.append(" AND PROG_KEYID=BACH_PROG_KEYID");
		sql.append(" AND BACH_PROG_KEYID=EASM_PROG_KEYID(+) AND BACH_STATUS='C'");
		sql.append(" GROUP BY PROG_KEYID,BACH_KEYID,PROG_NAME,BACH_NAME,");
		sql.append(" TO_CHAR(BACH_FROMDATE,'DD-MON-YYYY'), ");
		sql.append(" TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') ");
		sql.append(" ORDER BY  PROG_NAME,BACH_NAME");		
  		sql.append(" )A where  1 = 1 ) " );  
  		CommonMessage.debugMsg("Sql:"+sql);
        return sql.toString();                            
	}
	
	public static String getAssessmentCount(CommonFilter commonFilter,String progId,String batchId) {
		StringBuffer sql = new StringBuffer();
		sql.append (" select count(*)  from  (");
		sql.append(" SELECT A.* FROM(");
		sql.append(" select ");
		sql.append(" EASM_KEYID,EASM_BACH_KEYID,BACH_CODE||'-'||BACH_NAME AS BATCH,BACH_FROMDATE,BACH_TILLDATE,EASM_EVALUATION_DESC,EASM_EVALUATION_DATE,EASM_EVALUATION_NO ");
		sql.append(" from " );
		sql.append( TableNames.TBL_ENT_TL_ASSESSMENTMST + "," + TableNames.TBL_ENT_TL_BATCHMST);
		sql.append(" where BACH_KEYID=EASM_BACH_KEYID ");		
		sql.append(" and BACH_STATUS='C' ");
		//sql.append(" and EASM_EVALUATION_DATE between BACH_FROMDATE and BACH_TILLDATE ");
		if(CommonFunctions.isValidKeyId(progId))
			sql.append(" and EASM_PROG_KEYID='"+ progId +"' ");
		if(CommonFunctions.isValidKeyId(batchId))
			sql.append(" and EASM_BACH_KEYID='"+ batchId +"' ");
		sql.append(" order by BACH_CODE,BACH_NAME ");
  		sql.append(")A where  1 = 1 ) " );     
      	CommonMessage.debugMsg("Sql:"+sql);      	
      	return sql.toString(); 
	}
	
	public static String assessmentGrid(CommonFilter commonFilter,String progId,String batchId) {
		StringBuffer sql = new StringBuffer();
		sql.append (" select *  from  (");
		sql.append(" SELECT A.* FROM(");
		sql.append(" select ");
		sql.append(" EASM_KEYID,EASM_BACH_KEYID,BACH_CODE||'-'||BACH_NAME AS BATCH,BACH_FROMDATE,BACH_TILLDATE,EASM_EVALUATION_DESC,EASM_EVALUATION_DATE,EASM_EVALUATION_NO ");
		sql.append(" from " );
		sql.append( TableNames.TBL_ENT_TL_ASSESSMENTMST + "," + TableNames.TBL_ENT_TL_BATCHMST);
		sql.append(" where BACH_KEYID=EASM_BACH_KEYID ");
		sql.append(" and BACH_STATUS='C' ");
		//sql.append(" and EASM_EVALUATION_DATE between BACH_FROMDATE and BACH_TILLDATE ");
		if(CommonFunctions.isValidKeyId(progId))
			sql.append(" and EASM_PROG_KEYID='"+ progId +"' ");
		if(CommonFunctions.isValidKeyId(batchId))
			sql.append(" and EASM_BACH_KEYID='"+ batchId +"' ");
		sql.append(" order by BACH_CODE,BACH_NAME ");
  		sql.append(")A where  1 = 1 ) " );     
      	CommonMessage.debugMsg("Sql:"+sql);      	
      	return sql.toString(); 
	}
	
	public static String getCheckList(TableFieldType [] fieldTypeArr, Object [] dataArray,String topId,String asmdKeyId, String curRattting) {
		StringBuffer sql = new StringBuffer();
		
		/*sql.append (" select '0' as chk,ASCL_KEYID as keyid ,CHKD_KEYID,CHKD_NAME,CHKD_REMARKS,CHKM_KEYID FROM ent_tl_checklistmst,ent_tl_checklistdtl ,ENT_TL_ASSESSMENT_CHECKLIST ");
		sql.append (" WHERE 1=1 ");
		sql.append (" AND CHKM_KEYID=CHKD_CHKM_KEYID AND ASCL_CHEK_KEYID(+) =CHKD_KEYID");
		sql.append (" and CHKM_TOPI_KEYID='"+topId+"' ");
		*/
		
		sql.append (" select '0' as chk,ASCL_KEYID as keyid ,CHKD_KEYID,CHKD_NAME,CHKD_REMARKS,CHKM_KEYID FROM ");
		sql.append (" (select CHKD_KEYID,CHKD_NAME,CHKD_REMARKS,CHKM_KEYID,CHKD_ORDERNO FROM ent_tl_checklistmst,ent_tl_checklistdtl ");
		sql.append (" WHERE 1=1  AND CHKM_KEYID=CHKD_CHKM_KEYID  and CHKM_TOPI_KEYID='"+topId+"' AND CHKM_SKRM_KEYID = '"+curRattting+"' ), ");
		sql.append ("(SELECT ASCL_KEYID,ASCL_CHEK_KEYID FROM ");
		sql.append (" ENT_TL_ASSESSMENTDTL,ENT_TL_ASSESSMENTMST,ENT_TL_ASSESSMENT_CHECKLIST ");
		sql.append (" WHERE ASMM_KEYID=ASMD_ASMM_KEYID ");
		sql.append (" AND ASMD_KEYID=ASCL_ASMD_KEYID(+) ");
		
		if(CommonFunctions.isValidKeyId(asmdKeyId))
			sql.append (" AND  ASCL_ASMD_KEYID ='"+asmdKeyId+"' ");
		
		sql.append (" AND ASMM_EVALUATION_NO" +
				" = '" +  (String)dataArray[ tableFldConstants.evaluation_no.ordinal()] + "'");
		
		sql.append (" AND ASMM_TRAR_KEYID" +
		" = '" +  (String)dataArray[ tableFldConstants.trar_keyid.ordinal()] + "'");
		
		sql.append ( " AND ASMM_ROLE_KEYID"  +
		" = '" +  (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'");
		
		sql.append (" AND ASMM_EMPM_KEYID"  +
		" = '" +  (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'");

		sql.append (" AND ASMM_EVALUATION_TYPE"  +
				" = '" +  (String)dataArray[ tableFldConstants.evaluation_type.ordinal()] + "'");
		sql.append (") WHERE ASCL_CHEK_KEYID(+) =CHKD_KEYID ORDER BY CHKD_ORDERNO");
		CommonMessage.debugMsg("Sql:"+sql);  
      	return sql.toString(); 
	}
	
	public static String getTrainingAreaPath(TableFieldType [] fieldTypeArr, Object [] dataArray) {
		String sql = "Select PARENTNAMES  from ENT_VW_TRAININGAREACHILDPATH " ;
		
		sql += " where 1=1 ";
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.trar_keyid.ordinal()])){
			sql += " and KEYID='" + (String)dataArray[ tableFldConstants.trar_keyid.ordinal()] + "'";
		}
		return sql;
	}
	public static String getEmployeeName(TableFieldType [] fieldTypeArr, Object [] dataArray) {
		String sql = "Select EMPM_NAME  from " + TableNames.TBL_GEN_TL_EMPLOYEEMST ;
		
		sql += " where 1=1 ";
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.empm_keyid.ordinal()])){
			sql += " and EMPM_KEYID='" + (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'";
		}
		return sql;
	}
	public static String getRoleName(TableFieldType [] fieldTypeArr, Object [] dataArray) {
		String sql = "Select ROLE_NAME  from " + TableNames.TBL_GEN_TL_ROLEMST ;
		
		sql += " where 1=1 ";
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.role_keyid.ordinal()])){
			sql += " and ROLE_KEYID='" + (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'";
		}
		return sql;
	}
		
	public static String getSkillRating(TableFieldType [] fieldTypeArr, Object [] dataArray,String TopicId,String score,String result) {
		String sql ="";
		//if(result.equals("P")){	
			sql = "select ASMD_CURRENT_RATE  from ent_tl_assessmentdtl,ent_tl_assessmentmst where asmm_keyid = asmd_asmm_keyid AND ";
		    if(CommonFunctions.isValidKeyId(TopicId)){
            	sql += " asmd_topi_keyid = '" +  TopicId + "'";
            }
			sql += " AND asmm_empm_keyid = '" +  (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'";
			sql += " AND ASMD_RESULT = '" +  result + "'  AND asmm_evaluation_type in( 'PRE','CUR') ";
			sql += " AND asmm_evaluation_date = ";
			sql += " (SELECT MAX (asmm_evaluation_date)";
			sql += " FROM ent_tl_assessmentdtl, ent_tl_assessmentmst ";
			sql += " WHERE asmm_keyid = asmd_asmm_keyid ";
			sql += " AND asmm_empm_keyid = '" +  (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'";
			sql += " AND asmm_evaluation_type in( 'PRE','CUR') ";
            if(CommonFunctions.isValidKeyId(TopicId)){
            	sql += "  AND asmd_topi_keyid = '" +  TopicId + "'";
            }
    	    sql += " AND ASMD_RESULT = 'P')"; 
			/*sql =  " SELECT ";
			sql = sql + " RTRL_SKRM_KEYID, RTRL_CUTOFF ";
			sql = sql + " FROM ";
			sql = sql + " ENT_TL_ROLE_TRAININGAREA_LINK, ";
			sql = sql + " ENT_TL_ROLE_TOPIC_LINK, ";
			sql = sql + " ENT_TL_ROLE_TOPIC_RATING   "; 		
			sql = sql + " WHERE 1=1 AND RTAL_KEYID=RTLK_RTAL_KEYID ";		
			sql = sql + " AND RTLK_KEYID=RTRL_RTLK_KEYID ";
		
			sql += " and RTAL_TRAR_KEYID" +
			" = '" +  (String)dataArray[ tableFldConstants.trar_keyid.ordinal()] + "'";
			
			sql += " and RTAL_ROLE_KEYID"  +
			" = '" +  (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'";
		
			if(CommonFunctions.isValidKeyId(TopicId)){
				sql += " and RTLK_TOPI_KEYID = '" +  TopicId + "'";
			}
			
			sql += " and RTRL_CUTOFF<=" +  score ;
			sql += " ORDER BY RTRL_CUTOFF DESC " ;
		}
		else if(result.equals("F")){			
			sql =" SELECT SKRM_KEYID,SKRM_MINPOINTS FROM ";
			sql = sql + " ENT_TL_SKILL_RATINGMST ";
			sql = sql + " WHERE SKRM_MINPOINTS='0' ";			    
		}*/
		return sql;
	}
	public static String getPreviousRating(TableFieldType [] fieldTypeArr, Object [] dataArray,String TopicId) {
		String sql ="";	
		String evaluationNo=(String)dataArray[ tableFldConstants.evaluation_no.ordinal()];
		if (evaluationNo.equals("1")){		
			sql =" SELECT SKRM_KEYID FROM ";
			sql = sql + " ENT_TL_SKILL_RATINGMST ";
			sql = sql + " WHERE SKRM_MINPOINTS='0' ";
		}
		else{
			sql = "SELECT ASMD_CURRENT_RATE FROM ";
			sql = sql + " ENT_TL_ASSESSMENTMST,ENT_TL_ASSESSMENTDTL ";
			sql = sql + " WHERE  ASMD_ASMM_KEYID=ASMM_KEYID AND ASMD_CURRENT_RATE<>'{}' ";
			
			sql += " and ASMM_TRAR_KEYID" +
			" = '" +  (String)dataArray[ tableFldConstants.trar_keyid.ordinal()] + "'";
			
			sql += " and ASMM_ROLE_KEYID"  +
			" = '" +  (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'";
			
			sql += " and ASMM_EMPM_KEYID"  +
			" = '" +  (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'";
			
			sql += " and ASMM_EVALUATION_NO<"  +
			  (String)dataArray[ tableFldConstants.evaluation_no.ordinal()] ;
			
			sql += " and ASMM_EVALUATION_TYPE"  +
			" = '" +  (String)dataArray[ tableFldConstants.evaluation_type.ordinal()] + "'";
		
			if(CommonFunctions.isValidKeyId(TopicId)){
				sql += " and ASMD_TOPI_KEYID = '" +  TopicId + "'";
			}
				
			sql += " ORDER BY ASMM_EVALUATION_NO DESC " ;
		}
		return sql;
	}
	
	public static String getMaxEvlDate(TableFieldType [] fieldTypeArr, Object [] dataArray) {
		String sql = "Select to_char(max(ASMM_EVALUATION_DATE),'DD-Mon-YYYY')  from " + TableNames.TBL_ENT_TL_ASSESSMENTMST ;
		
		sql += " where 1=1 ";
				
		sql += " and " + fieldTypeArr[tableFldConstants.trar_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.trar_keyid.ordinal()] + "'";
		
		sql += " and " + fieldTypeArr[tableFldConstants.role_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'";
		
		sql += " and " + fieldTypeArr[tableFldConstants.empm_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.evaluation_type.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.evaluation_type.ordinal()].fieldName  + " = 'PRE'";
		}

		return sql;
	}
	
	public static String getMinEvlDate(TableFieldType [] fieldTypeArr, Object [] dataArray) {
		String sql = "Select to_char(min(ASMM_EVALUATION_DATE),'DD-Mon-YYYY')  from " + TableNames.TBL_ENT_TL_ASSESSMENTMST ;
		
		sql += " where 1=1 ";
				
		sql += " and " + fieldTypeArr[tableFldConstants.trar_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.trar_keyid.ordinal()] + "'";
		
		sql += " and " + fieldTypeArr[tableFldConstants.role_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'";
		
		sql += " and " + fieldTypeArr[tableFldConstants.empm_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.evaluation_type.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.evaluation_type.ordinal()].fieldName  + " = 'POS'";
		}

		return sql;
	}
	
	public static String preEvlExists(TableFieldType [] fieldTypeArr, Object [] dataArray) {
		String sql = "Select count(*) from " + TableNames.TBL_ENT_TL_ASSESSMENTMST ;
		
		sql += " where 1=1 ";
				
		sql += " and " + fieldTypeArr[tableFldConstants.trar_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.trar_keyid.ordinal()] + "'";
		
		sql += " and " + fieldTypeArr[tableFldConstants.role_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.role_keyid.ordinal()] + "'";
		
		sql += " and " + fieldTypeArr[tableFldConstants.empm_keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.empm_keyid.ordinal()] + "'";
		
		if(CommonFunctions.isValidKeyId((String)dataArray[ tableFldConstants.evaluation_type.ordinal()])){
			sql += " and " + fieldTypeArr[tableFldConstants.evaluation_type.ordinal()].fieldName + " = 'PRE'";
		}

		return sql;
	}

	public String getMinCutOff(String assEmpKeyId, String topicId,
			String assRoleKeyId, String evalType, String mode, String trarkey, String result, String asmdKeyId) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(asmdKeyId +"~~~~~"+ result);
		String sql = "select min(RTRL_CUTOFF) RTRL_CUTOFF,rtrl_skrm_keyid from ( ";
			   sql +=" select MAX(nvl(nextrate,'1')) nextrate,MAX(ord)  from ( ";
			   sql +=" select nvl(b.SKRM_keyid,'1') nextrate,b.SKRM_ORDERNO ord from ENT_TL_ASSESSMENTDTL ,ENT_TL_ASSESSMENTMST,ent_tl_role_trainingarea_link, ";
			   sql +=" ENT_TL_SKILL_RATINGMST a, (select SKRM_keyid,SKRM_ORDERNO from ENT_TL_SKILL_RATINGMST  ) b ";
			   sql +=" where ASMM_KEYID = ASMD_ASMM_KEYID AND ASMM_EMPM_KEYID = '"+assEmpKeyId+"' and RTAL_ROLE_KEYID = ASMM_ROLE_KEYID and  a.SKRM_KEYID =ASMD_CURRENT_RATE and ";
			   if(UIUtils.isValidKeyId(asmdKeyId) &&  result.equals("PASS"))
				   sql +=" a.SKRM_ORDERNO  = b.SKRM_ORDERNO and "; 
			   else
				   sql +=" a.SKRM_ORDERNO + 1 = b.SKRM_ORDERNO and ";
			   sql +=" RTAL_ROLE_KEYID  = '"+assRoleKeyId+"' ";
			   sql +=" and ASMM_EVALUATION_DATE  = (select max(ASMM_EVALUATION_DATE ) from  ENT_TL_ASSESSMENTDTL ,ENT_TL_ASSESSMENTMST "; 
			   sql +=" where ASMM_KEYID = ASMD_ASMM_KEYID AND ASMM_EMPM_KEYID = '"+assEmpKeyId+"' and ASMM_EVALUATION_TYPE = '"+evalType+"'  AND  ASMM_TRAR_KEYID = '"+trarkey+"'"; 
			   if(UIUtils.isValidKeyId(asmdKeyId))
				  sql +=" and ASMD_KEYID <> '"+asmdKeyId+"'"; 
			   sql +=" and ASMD_TOPI_KEYID = '"+topicId+"' ) "; 
			   sql +=" and ASMD_TOPI_KEYID = '"+topicId+"' and ASMM_EVALUATION_TYPE = '"+evalType+"' AND  ASMM_TRAR_KEYID = '"+trarkey+"'";
			   sql +=" union select '',0 from dual)) a,( "; 
			   sql +=" select RTRL_SKRM_KEYID,RTRL_CUTOFF from ent_tl_role_topic_rating,ent_tl_role_topic_link,ent_tl_role_trainingarea_link ";
			   sql +=" where RTRL_RTLK_KEYID =  RTLK_KEYID and RTLK_TOPI_KEYID ='"+topicId+"'   AND  RTAL_TRAR_KEYID = '"+trarkey+"'";
			   sql +=" and RTAL_KEYID = RTLK_RTAL_KEYID and RTAL_ROLE_KEYID = '"+assRoleKeyId+"') where decode(nextrate,'1',RTRL_SKRM_KEYID,nextrate)= RTRL_SKRM_KEYID ";
			   sql +=" and RTRL_CUTOFF > 0 group by rtrl_skrm_keyid order by RTRL_CUTOFF ";
			   
		CommonMessage.debugMsg("sql  :"+sql);	   
		return sql;
	}

	
	public static String getSelectAttence(String filter, CommonFilter commonFilter) {

		/*String sql = "SELECT ";
			sql +=" DISTINCT ASMD_PROG_KEYID,ASMM_ASSESSEDBY,ASMM_EVALUATION_DESC,PROG_NAME,TO_CHAR(ASMM_EVALUATION_DATE,'DD-MON-YYYY') ASMM_EVALUATION_DATE,";
			sql +=" PROG_MIN_DURATION,ASMM_FACULTY,sum(cnt)as Counts,LISTAGG(ASMMKEYIDS,',') within group(order by ASMMKEYIDS)";
			sql +=" FROM ";
			sql +=" ENT_TL_ASSESSMENTMST,GEN_TL_EMPLOYEEMST,ENT_TL_ASSESSMENTDTL,ENT_TL_PROGRAMMST,GEN_MV_FLIDHIERARCHY,";
			sql +=" (SELECT COUNT(ASMM_KEYID) AS cnt , ASMM_KEYID  AS ASMMKEYIDS, ASMD_PROG_KEYID AS prgid, ASMM_FACULTY  AS facid";
			sql +=" FROM ENT_TL_ASSESSMENTMST, ENT_TL_ASSESSMENTDTL  WHERE 1=1  AND ASMD_ASMM_KEYID = ASMM_KEYID GROUP BY ASMM_KEYID, ASMD_PROG_KEYID,ASMM_ASSESSEDBY,ASMM_EVALUATION_DESC,";
			sql +=" ASMM_FACULTY )a "; 
			sql +=" WHERE ";
			sql +=" ASMD_PROG_KEYID = PROG_KEYID AND ASMM_EMPM_KEYID = EMPM_KEYID ";
			sql +=" AND ASMD_ASMM_KEYID = ASMM_KEYID AND a.prgid  = PROG_KEYID AND a.facid  = ASMM_FACULTY";
			sql +=" AND INSTR(ASMM_ELEMENTID, '"+commonFilter.getLocation().getId()+"')>0 ";
			sql +=" AND ASMM_TRAR_KEYID = FLID AND INSTR(PARENTFLIDS||FLID,'"+commonFilter.getFlid()+"')>0    AND ASMD_CURRENT_RATE <3 ";
			if(UIUtils.isValidKeyId(filter))
				sql +=	filter;
			sql +=" GROUP BY ";
			sql +=" ASMM_KEYID,ASMM_ASSESSEDBY,ASMM_EVALUATION_DESC,ASMD_PROG_KEYID, ASMM_FACULTY, PROG_NAME, TO_CHAR(ASMM_EVALUATION_DATE,'DD-MON-YYYY'),";
			sql +=" PROG_MIN_DURATION, EMPM_NAME, ASMM_EMPM_KEYID, cnt ";
		*/
			/*String sql=" SELECT DISTINCT ASMD_PROG_KEYID,PROG_NAME,TO_CHAR(ASMM_EVALUATION_DATE,'DD-MON-YYYY') ASMM_EVALUATION_DATE,PROG_MIN_DURATION,ASMM_FACULTY,cnt,ASMMKEYIDS"; 
			sql +=" FROM ENT_TL_ASSESSMENTMST,GEN_TL_EMPLOYEEMST,ENT_TL_ASSESSMENTDTL,ENT_TL_PROGRAMMST,";
			sql +=" (SELECT COUNT(ASMM_KEYID) AS cnt , wm_concat(ASMM_KEYID)   AS ASMMKEYIDS, ASMD_PROG_KEYID AS prgid, ASMM_FACULTY  AS facid"; 
			sql +=" FROM ENT_TL_ASSESSMENTMST, ENT_TL_ASSESSMENTDTL  WHERE 1=1  AND ASMD_ASMM_KEYID = ASMM_KEYID"; 
			sql +=" GROUP BY ASMM_KEYID, ASMD_PROG_KEYID, ASMM_FACULTY";
			sql +=" )a"; 
			sql +=" WHERE ASMD_PROG_KEYID = PROG_KEYID AND ASMM_EMPM_KEYID = EMPM_KEYID AND ASMD_ASMM_KEYID = ASMM_KEYID"; 
			sql +=" AND a.prgid  = PROG_KEYID AND a.facid  = ASMM_FACULTY"; 
			sql +=" GROUP BY ASMM_KEYID,ASMD_PROG_KEYID, ASMM_FACULTY,"; 
			sql +=" PROG_NAME, TO_CHAR(ASMM_EVALUATION_DATE,'DD-MON-YYYY'), PROG_MIN_DURATION, EMPM_NAME, ASMM_EMPM_KEYID, cnt, ASMMKEYIDS";*/ 
			/*" SELECT DISTINCT ASMD_PROG_KEYID,PROG_NAME,TO_CHAR(ASMM_EVALUATION_DATE,'DD-MON-YYYY') ASMM_EVALUATION_DATE,PROG_MIN_DURATION,ASMM_FACULTY,cnt,ASMMKEYIDS"+
            " FROM ENT_TL_ASSESSMENTMST,GEN_TL_EMPLOYEEMST,ENT_TL_ASSESSMENTDTL,ENT_TL_PROGRAMMST,"+
            " (SELECT COUNT(ASMM_KEYID) AS cnt ,"+
             " wm_concat(ASMM_KEYID)   AS ASMMKEYIDS,"+
           " ASMD_PROG_KEYID AS prgid,"+
           " ASMM_FACULTY  AS facid"+
           " FROM ENT_TL_ASSESSMENTMST,"+
           " ENT_TL_ASSESSMENTDTL "+
          " WHERE 1=1 "+
       " AND ASMD_ASMM_KEYID = ASMM_KEYID"+
         " GROUP BY ASMM_FACULTY,"+
           " ASMD_PROG_KEYID "+
           " )a"+
        " WHERE ASMD_PROG_KEYID = PROG_KEYID AND ASMM_EMPM_KEYID = EMPM_KEYID AND ASMD_ASMM_KEYID = ASMM_KEYID AND a.prgid  = PROG_KEYID AND a.facid  = ASMM_FACULTY GROUP BY ASMM_KEYID,ASMD_PROG_KEYID, ASMM_FACULTY, PROG_NAME, TO_CHAR(ASMM_EVALUATION_DATE,'DD-MON-YYYY'), PROG_MIN_DURATION, EMPM_NAME, ASMM_EMPM_KEYID, cnt, ASMMKEYIDS ";*/
			

		
			StringBuilder sql = new StringBuilder( " SELECT * FROM ( ");

				sql .append(" SELECT DISTINCT ASMD_PROG_KEYID,ASMM_TRAR_KEYID, ASMD_BACH_KEYID, ASMM_ASSESSEDBY,FNLN_DESCRIPTION, ASMM_EVALUATION_DESC,");
				sql .append(" PROG_NAME, BACH_NAME, TO_CHAR(ASMM_EVALUATION_DATE,'DD-MON-YYYY') ASMM_EVALUATION_DATE,");
				//sql .append(" PROG_MAX_DURATION,FTYM_NAME,sum(AssessdCnt) as Counts," );
				sql .append(" PROG_MAX_DURATION,MAX(ASMM_FACULTY) AS ASMM_FACULTY,count(ASMM_KEYID) as Counts," );
				
				// sql .append(" SUM(TOTAL)-sum(AssessdCnt) AS Balance, " );
				
				/*sql.append("   (  Select Count(Distinct Bstd_Empm_Keyid)  From " ); 
				sql.append("  Ent_Tl_Programmst,Gen_Tl_Employeemst,Gen_Tl_Departmentmst,Ent_Tl_Assessmentmst,Ent_Tl_Assessmentdtl, ENT_TL_BATCHMST, Ent_Tl_Batch_Employee_Link " ); 
				sql.append(" 	WHERE 1=1 And Bach_Prog_Keyid = Prog_Keyid And Bach_Keyid = Bstd_Bach_Keyid AND BSTD_EMPM_KEYID = EMPM_KEYID " );
				sql.append(" 	AND DEPT_KEYID (+) = EMPM_DEPARTMENTID ");
				//sql.append(" AND ASMM_KEYID (+) = ASMD_ASMM_KEYID " );
				sql.append(" AND ASMM_KEYID  = ASMD_ASMM_KEYID " );
				sql.append("   And Asmm_Empm_Keyid(+) = Empm_Keyid  And Prog_Keyid = ASMD_PROG_KEYID (+) " );
				sql.append("   And Prog_Keyid = ASSDTL.ASMD_PROG_KEYID  " );
				sql.append("   And ASMD_BACH_KEYID = ASSDTL.ASMD_BACH_KEYID " );
				sql .append(" And Empm_Roleid In(Select Rtlk_Rtal_Keyid From Ent_Tl_Role_Topic_Link Where Rtlk_Topi_Keyid = ASMD_PROG_KEYID ) " ); 
				sql .append(" )-Sum(Assessdcnt) As BalanceCount, " );
				 */
				
				sql .append(" ( Select Count(Distinct EBEA_Empm_Keyid) ");
				sql .append(" From   Ent_Tl_Programmst, ENT_TL_BATCH_SCHEDULE, ENT_TL_BATCH_EMP_ATTENDANCE, Gen_Tl_Employeemst ");
				sql .append(" WHERE PROG_KEYID = BSDL_PROG_KEYID ");
				sql .append(" AND BSDL_KEYID = ebea_bsdl_keyid AND EBEA_ATTENDANCE=1 ");
				sql.append("   And Prog_Keyid = ASSDTL.ASMD_PROG_KEYID  " );
				sql.append("   And EBEA_BACH_KEYID = ASSDTL.ASMD_BACH_KEYID " );
				sql.append("   And EBEA_Empm_Keyid  = Empm_Keyid  " );
				sql .append(" And Empm_Roleid In(Select Rtlk_Rtal_Keyid From Ent_Tl_Role_Topic_Link Where Rtlk_Topi_Keyid = Prog_Keyid ) " );
				sql .append(" )-count(ASMM_KEYID) As BalanceCount, " );
				
				sql .append(" LISTAGG(ASMMKEYIDS,',') within group(order by ASMMKEYIDS) ");
				
				sql .append(" FROM ");
				sql .append(" ENT_TL_ASSESSMENTMST,GEN_TL_EMPLOYEEMST,ENT_TL_ASSESSMENTDTL ASSDTL,ENT_TL_PROGRAMMST,ENT_TL_BATCHMST, GEN_MV_FLIDHIERARCHY,");
				sql .append(" (SELECT COUNT(ASMM_KEYID) AS AssessdCnt , COUNT(*) AS TOTAL, ");
				//sql.append(" ASMM_KEYID  AS ASMMKEYIDS, ");
				//sql.append(" LISTAGG(ASMM_KEYID,',') within group(order by ASMM_KEYID) ASMMKEYIDS , ");
				sql.append(" LISTAGG( SUBSTR(ASMM_KEYID,LENGTH(ASMM_KEYID)-3,LENGTH(ASMM_KEYID)) ,',') within GROUP(ORDER BY ASMM_KEYID) ASMMKEYIDS , ");
				
				sql.append(" ASMD_PROG_KEYID AS prgid,ASMD_BACH_KEYID as batchid, FTYM_NAME , ASMM_FACULTY  AS facid");
				sql .append(" FROM ENT_TL_ASSESSMENTMST, ENT_TL_ASSESSMENTDTL,ENT_TL_FACULTYMST   WHERE 1=1  AND ASMD_ASMM_KEYID = ASMM_KEYID ");
				sql .append(" and ASMM_FACULTY = FTYM_KEYID (+) ");
				//sql .append(" GROUP BY ASMM_KEYID, ASMD_PROG_KEYID,ASMM_ASSESSEDBY,ASMM_EVALUATION_DESC,");
				sql .append(" GROUP BY ASMD_PROG_KEYID,ASMD_BACH_KEYID, ASMM_ASSESSEDBY,ASMM_EVALUATION_DESC,");
				
				sql .append(" FTYM_NAME ,ASMM_FACULTY )a "); 
				sql .append(" WHERE ");
				sql .append(" ASMD_PROG_KEYID = PROG_KEYID AND ASMM_EMPM_KEYID = EMPM_KEYID ");
				sql .append(" AND ASMD_ASMM_KEYID = ASMM_KEYID ");
				sql .append(" AND a.prgid  = PROG_KEYID AND a.batchid  = ASMD_BACH_KEYID AND a.facid (+) = ASMM_FACULTY");
				sql .append(" AND  ASMD_BACH_KEYID =  BACH_KEYID (+) " );  
				sql .append(" AND INSTR(ASMM_ELEMENTID, '").append(commonFilter.getLocation().getId()).append("')>0 ");
				sql .append(" AND ASMM_TRAR_KEYID = FLID AND INSTR(PARENTFLIDS||FLID,'").append(commonFilter.getFlid()).append("')>0    AND ASMD_CURRENT_RATE <3 ");

				CommonMessage.debugMsg("commonFilter.getMonwise()"+commonFilter.getMonwise());
				if (UIUtils.isValidKeyId(commonFilter.getMonwise()) && commonFilter.getMonwise().equals("Y")) {
					sql .append(" AND TRUNC(ASMM_EVALUATION_DATE) BETWEEN '01-").append(commonFilter.getFromMonth()).append("' ");
					sql .append("  and LAST_DAY('01-").append(commonFilter.getToMonth()).append("')");
				}
				else if (UIUtils.isValidDate( commonFilter.getFromDate())) {
					sql .append(" AND TRUNC(ASMM_EVALUATION_DATE) between '").append(commonFilter.getFromDate()).append("'");
					sql .append("  and '").append(commonFilter.getToDate()).append("'");
				}
				
				if(UIUtils.isValidKeyId(filter))
					sql .append(filter);
				/*sql .append(" GROUP BY ASMM_KEYID,ASMM_ASSESSEDBY, FNLN_DESCRIPTION, ASMM_EVALUATION_DESC,");
				sql .append(" ASMM_TRAR_KEYID, ASMD_PROG_KEYID, ASMD_BACH_KEYID,FTYM_NAME, PROG_NAME, BACH_NAME, TO_CHAR(ASMM_EVALUATION_DATE,'DD-MON-YYYY'),");
				sql .append(" PROG_MAX_DURATION, EMPM_NAME, ASMM_EMPM_KEYID ");
				*/
				
				sql .append(" GROUP BY  ASMD_PROG_KEYID,ASMM_TRAR_KEYID, ASMD_BACH_KEYID, ASMM_ASSESSEDBY,FNLN_DESCRIPTION, ASMM_EVALUATION_DESC,");
				sql .append(" PROG_NAME, BACH_NAME, TO_CHAR(ASMM_EVALUATION_DATE,'DD-MON-YYYY') , PROG_MAX_DURATION ");				
				
				sql .append( " ) WHERE 1 = 1 AND ASMD_BACH_KEYID <> '{}' ");
				sql .append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())) ;

				
				sql .append(" ORDER BY TO_DATE(ASMM_EVALUATION_DATE) DESC ");
				
			CommonMessage.debugMsg("sql" + sql);
			return sql.toString();
			
	}
	
	

	public String getselectsql() {
					String sql=" select PROG_NAME,TO_CHAR(ASMM_EVALUATION_DATE,'DD-MON-YYYY'),PROG_MIN_DURATION,EMPM_NAME FROM ENT_TL_ASSESSMENTMST WHERE  ASMM_KEYID = ?";
					return sql;
					}

	public static String getdatalist(String keyId) {
		String sql=" select ASMD_PROG_KEYID  ,ASMM_FACULTY, DECODE(ASMM_FACULTY,'-','',asmm_keyid ),EMPM_KEYID,EMPM_NAME||'-'|| EMPM_CODE,DEPT_NAME,ASMD_SCORE as score,ASMD_TYPE as type,"+
   " ASMM_EVALUATION_DATE,PROG_MIN_DURATION   from gen_tl_employeemst,gen_tl_departmentmst,ENT_TL_ASSESSMENTDTL,ENT_TL_ASSESSMENTMST,ENT_TL_PROGRAMMST"+  
    " where EMPM_DEPARTMENTID = DEPT_KEYID AND  ASMM_KEYID = ASMD_ASMM_KEYID  and ASMM_EMPM_KEYID  = EMPM_KEYID   and ASMM_KEYID IN ("+keyId+")"+
    "  ORDER BY asmm_keyid DESC";
		return sql;
	}

}
