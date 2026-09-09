package com.akranta.tpm.dao.sql;

import com.akranta.tpm.bean.ActionPlanParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.utils.CommonMessage;

public class GenTlActionplanmstSql {

	public static final String TBL_GEN_TL_ACTIONPLANMST = "GEN_TL_ACTIONPLANMST";
   
	TableFieldType[] aplmDbFields = null;

	public enum tableFldConstants {
		keyid, masterrefid, detailrefid, refdoctype, flid, elementid, maintask, pillarid, status, remarks, plandate, tempfiled2, tempfiled3, tempfiled4, tempfiled5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getAplmDbFields() {
		return aplmDbFields;
	}

	public GenTlActionplanmstSql() {
		aplmDbFields = new TableFieldType[19];
		for (int i = 0; i < 19; i++) {
			aplmDbFields[i] = new TableFieldType();
		}
		aplmDbFields[tableFldConstants.keyid.ordinal()].fieldName = "APLM_KEYID";
		aplmDbFields[tableFldConstants.keyid.ordinal()].fieldType = 'V';

		aplmDbFields[tableFldConstants.masterrefid.ordinal()].fieldName = "APLM_MASTERREFID";
		aplmDbFields[tableFldConstants.masterrefid.ordinal()].fieldType = 'V';

		aplmDbFields[tableFldConstants.detailrefid.ordinal()].fieldName = "APLM_DETAILREFID";
		aplmDbFields[tableFldConstants.detailrefid.ordinal()].fieldType = 'V';

		aplmDbFields[tableFldConstants.refdoctype.ordinal()].fieldName = "APLM_REFDOCTYPE";
		aplmDbFields[tableFldConstants.refdoctype.ordinal()].fieldType = 'V';

		aplmDbFields[tableFldConstants.flid.ordinal()].fieldName = "APLM_FLID";
		aplmDbFields[tableFldConstants.flid.ordinal()].fieldType = 'V';

		aplmDbFields[tableFldConstants.elementid.ordinal()].fieldName = "APLM_ELEMENTID";
		aplmDbFields[tableFldConstants.elementid.ordinal()].fieldType = 'V';

		aplmDbFields[tableFldConstants.maintask.ordinal()].fieldName = "APLM_MAINTASK";
		aplmDbFields[tableFldConstants.maintask.ordinal()].fieldType = 'V';

		aplmDbFields[tableFldConstants.pillarid.ordinal()].fieldName = "APLM_PILLARID";
		aplmDbFields[tableFldConstants.pillarid.ordinal()].fieldType = 'V';

		aplmDbFields[tableFldConstants.status.ordinal()].fieldName = "APLM_STATUS";
		aplmDbFields[tableFldConstants.status.ordinal()].fieldType = 'C';

		aplmDbFields[tableFldConstants.remarks.ordinal()].fieldName = "APLM_REMARKS";
		aplmDbFields[tableFldConstants.remarks.ordinal()].fieldType = 'V';

		aplmDbFields[tableFldConstants.plandate.ordinal()].fieldName = "APLM_PLANDATE";
		aplmDbFields[tableFldConstants.plandate.ordinal()].fieldType = 'D';

		aplmDbFields[tableFldConstants.tempfiled2.ordinal()].fieldName = "APLM_TEMPFILED2";
		aplmDbFields[tableFldConstants.tempfiled2.ordinal()].fieldType = 'C';

		aplmDbFields[tableFldConstants.tempfiled3.ordinal()].fieldName = "APLM_TEMPFILED3";
		aplmDbFields[tableFldConstants.tempfiled3.ordinal()].fieldType = 'C';

		aplmDbFields[tableFldConstants.tempfiled4.ordinal()].fieldName = "APLM_TEMPFILED4";
		aplmDbFields[tableFldConstants.tempfiled4.ordinal()].fieldType = 'C';

		aplmDbFields[tableFldConstants.tempfiled5.ordinal()].fieldName = "APLM_TEMPFILED5";
		aplmDbFields[tableFldConstants.tempfiled5.ordinal()].fieldType = 'C';

		aplmDbFields[tableFldConstants.active.ordinal()].fieldName = "APLM_ACTIVE";
		aplmDbFields[tableFldConstants.active.ordinal()].fieldType = 'C';

		aplmDbFields[tableFldConstants.createdby.ordinal()].fieldName = "APLM_CREATEDBY";
		aplmDbFields[tableFldConstants.createdby.ordinal()].fieldType = 'V';

		aplmDbFields[tableFldConstants.createdon.ordinal()].fieldName = "APLM_CREATEDON";
		aplmDbFields[tableFldConstants.createdon.ordinal()].fieldType = 'D';

		aplmDbFields[tableFldConstants.modifiedon.ordinal()].fieldName = "APLM_MODIFIEDON";
		aplmDbFields[tableFldConstants.modifiedon.ordinal()].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType[] fieldTypeArr,
			Object[] dataArray) {
		return SqlUtils.getInsertSql(TBL_GEN_TL_ACTIONPLANMST, fieldTypeArr,
				dataArray);
	}

	public static String getUpdateSql(TableFieldType[] fieldTypeArr,
			Object[] dataArray) {
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_ACTIONPLANMST,
				fieldTypeArr, dataArray);

		sql += " where "
				+ fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName
				+ " = '"
				+ (String) dataArray[tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType[] fieldTypeArr,
			Object[] dataArray) {
		String sql = "DELETE from " + TBL_GEN_TL_ACTIONPLANMST;

		sql += " where "
				+ fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName
				+ " = '"
				+ (String) dataArray[tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static StringBuffer getActionPlanReportSql() {
		StringBuffer sql = new StringBuffer();
		sql.append("select 'APLM_KEYID' as APLM_KEYID,'Functional Location' as Fnloc,'Main Task' as maintask,'Status' as status,'Remarks' as remarks,'flid' as flid");
		sql.append(" from dual union all");
		sql.append(" SELECT aplm_keyid, FUNCTIONALLOC AS fnloc, aplm_maintask AS maintask, aplm_status AS status, aplm_remarks AS remarks,aplm_flid as flid");
		sql.append(" FROM gen_tl_actionplanmst,gen_vw_fnln  where APLM_FLID=FNLN_KEYID");
		return sql;
	}

	public static StringBuffer getActionPlanDetailReportSql(String keyId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select 'APLD_KEYID' as APLd_KEYID,'APLD_APLM_KEYID' as APLD_APLM_KEYID ,'Action Plan' as actionplan,'Trade' as trade,'How to Do' as howtodo,");
		sql.append(" 'Responsibility' as  Responsibility,'Target Date' as targetdate,'Status' as status,'Completed Date' as CompletedDate,'Completed By' as completedby,");
		sql.append(" 'Counter Measure' as countermeasure,'Remarks' as remarks,'TRADEID' AS TRADEID,'responsibility' as APLD_RESPONSIBILITY,'APLD_COMPLETEDBY' as APLD_COMPLETEDBY,'status' as  APLD_STATUS");
		sql.append(" from dual union all");
		sql.append(" SELECT apld_keyid, apld_aplm_keyid, apld_actionplan AS actionplan,TRDM_NAME AS trade, apld_howtodo AS howtodo, a.EMPM_NAME AS responsibility, TO_CHAR (apld_targetdate, 'DD-MON-YYYY') AS targetdate,");
		sql.append(" decode(apld_status,'C','COMPLETED','P','PENDING') AS status,TO_CHAR (apld_compleatedon, 'DD-MON-YYYY') AS completeddate,b.EMPM_NAME AS completedby, apld_countermeasure AS countermeasure,apld_remarks AS remarks,APLD_TRADEID,APLD_RESPONSIBILITY,APLD_COMPLETEDBY,APLD_STATUS");
		sql.append(" FROM gen_tl_actionplandtl,gen_tl_employeemst a,gen_tl_employeemst b,gen_tl_trademst");
		sql.append(" where apld_aplm_keyid ='" + keyId + "'");
		sql.append("and TRDM_KEYID=apld_tradeid(+)   AND apld_responsibility  = a.empm_keyid(+)   AND apld_completedby = b.empm_keyid(+)");
		return sql;
	}

	public static StringBuffer getActionPlanFillControl(
			GenTlActionplanmst genTlActionplanmst) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from gen_tl_actionplanmst where 1=1");
		/*
		 * if (CommonFunctions.isValidKeyId(genTlActionplanmst.getAplmKeyid())){
		 * sql.append(" and APLM_KEYID=?"); }
		 */
		sql.append(" AND APLM_DETAILREFID= ?  ");

		return sql;
	}

	public static String getRefDocUpdateSql(
			GenTlActionplanmst genTlActionplanmst) {

		StringBuffer sql = new StringBuffer();
		String docType = genTlActionplanmst.getAplmRefdoctype();
		if (docType.equals("JHA")) {
			sql.append("update JHA_TL_AUDITDTL set ");
			sql.append(" JHAD_NCREMARKS='"
					+ genTlActionplanmst.getAplmRemarks() + "'");
			sql.append(" ,JHAD_NCACTIONPLAN='"
					+ genTlActionplanmst.getAplmKeyid() + "'");
			sql.append(" ,JHAD_NCSTATUS=(SELECT APLM_STATUS FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"
					+ genTlActionplanmst.getAplmKeyid() + "') ");// '"+(String)dataArray[
																	// tableFldConstants.status.ordinal()]
																	// +"'");
			sql.append(" ,JHAD_NCCLOSED=(SELECT APLM_STATUS FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"
					+ genTlActionplanmst.getAplmKeyid() + "') ");// '"+(String)dataArray[
																	// tableFldConstants.status.ordinal()]
																	// +"'");
			sql.append(" where JHAD_KEYID='"
					+ genTlActionplanmst.getAplmDetailrefid() + "'");
		} 
		if (docType.equals("PSRR")) {
			sql.append("update PSSR_TL_RECCOMENDATIONS set ");
			
			sql.append(" PSRR_STATUS=(SELECT APLM_STATUS FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"
					+ genTlActionplanmst.getAplmKeyid() + "') ");// '"+(String)dataArray[
																	// tableFldConstants.status.ordinal()]
																	// +"'");
			sql.append(" where PSRR_KEYID='"
					+ genTlActionplanmst.getAplmDetailrefid() + "'");
		}
		
		else if (docType.equals("MOCR")) {
			sql.append("update MOC_TL_RECCOMENDATIONS set ");
			sql.append(" MOCR_STATUS=(SELECT APLM_STATUS FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"
					+ genTlActionplanmst.getAplmKeyid() + "') ");// '"+(String)dataArray[
																	// tableFldConstants.status.ordinal()]
																	// +"'");
			sql.append(" where MOCR_WH_KEYID='"
					+ genTlActionplanmst.getAplmDetailrefid() + "'");
		}
		
		if (docType.equals("CAPA")) {
			sql.append("update QTM_TL_CAPActionmst set ");
			sql.append(" QCPA_STATUS=(SELECT APLM_STATUS FROM GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"
					+ genTlActionplanmst.getAplmKeyid() + "') ");
			sql.append(" where QCPA_QCAP_KEYID='"
					+ genTlActionplanmst.getAplmDetailrefid() + "'");
		}
		
		else if (docType.equals("RISK")) {
			sql.append("update SHE_TL_RISKASSESSMENTDTL  set ");
			sql.append(" RASD_ACTPLAN='" + genTlActionplanmst.getAplmKeyid()
					+ "'");
			sql.append(" where RASD_KEYID='"
					+ genTlActionplanmst.getAplmDetailrefid() + "'");
		} else {

		}
		return sql.toString();
	}

	public static String getUpdateStausSql(TableFieldType[] fieldTypeArr,
			Object[] dataArray) {
		StringBuffer sql = new StringBuffer();
		// TODO Auto-generated method stub
		sql.append("UPDATE " + TBL_GEN_TL_ACTIONPLANMST + " SET ");
		sql.append(" APLM_STATUS=COALESCE((SELECT DISTINCT APLD_STATUS FROM GEN_TL_ACTIONPLANDTL ");
		sql.append(" WHERE APLD_APLM_KEYID='"
				+ (String) dataArray[tableFldConstants.keyid.ordinal()]
				+ "' and APLD_STATUS='P'),'C') ");
		sql.append(" WHERE APLM_KEYID='"
				+ (String) dataArray[tableFldConstants.keyid.ordinal()] + "' ");
		return sql.toString();
	}  


	public static String getEmployeeActionPlan() {
        StringBuilder sql = new StringBuilder();

        sql.append(" select APLD_KEYID,DECODE(APLD_STATUS,'P','Pending','W','Work in Progress','C','Completed') Status,APLM_MAINTASK AS txtApldMaintask,APLD_ACTIONPLAN AS txtApldActionplan,");
	    sql.append(" a.EMPM_NAME as cmbApldResponsibility,b.EMPM_NAME ||''-''|| b.EMPM_CODE as Responsibilty,");
		sql.append(" replace(to_char(APLD_TARGETDATE,'DD-Mon-YYYY'),'31-Dec-2100','') AS dteApldTargetdate, ");
		sql.append(" replace(to_char(APLD_COMPLEATEDON,'DD-Mon-YYYY'),'31-Dec-2100','') AS dteApldCompleatedon,APLD_COUNTERMEASURE AS txtApldCountermeasure,APLD_REMARKS AS txtApldRmarks, ");
		sql.append(" a.EMPM_NAME,b.EMPM_NAME as txtApldallotedby from  GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL, gen_TL_employeemst a ,gen_TL_employeemst b,GEN_MV_FLIDHIERARCHY,ADM_TL_USERMST ");
	    sql.append(" where APLD_APLM_KEYID=APLM_KEYID  AND a.EMPM_KEYID = ? ");
        sql.append("  AND APLM_FLID=FLID AND A.EMPM_KEYID=APLD_RESPONSIBILITY ");
        sql.append(" and aplm_createdby=usrm_keyid(+) and usrm_ccno=b.empm_keyid(+)  "); //AND INSTR(PARENTFLIDS||FLID,?) > 0
        sql.append(" AND APLD_STATUS <> 'C' order by APLD_KEYID desc ");
   //     CommonMessage.debugMsg("Sql Data"+sql.toString());
        return sql.toString();

    }
	

	public static String getEmployeeActionPlan(ActionPlanParams actionPlanParams) {
		StringBuilder sql = new StringBuilder();
		//CommonMessage.debugMsg("TeamCheck DaoImpl:"+actionPlanParams.getKeyid());
		//CommonMessage.debugMsg("Flid DaoImpl:"+actionPlanParams.getFlid()); 
		//if(actionPlanParams.getEmployeeId().equals("Y")){ 
		   if(actionPlanParams.getKeyid()==null){ 
				sql.append(" select APLM_KEYID, APLD_KEYID,CASE APLD_STATUS");
				sql.append(" WHEN 'P' THEN 'Pending' ");
				sql.append( " WHEN 'W' THEN 'Work in Progress'");
				sql.append( " WHEN 'C' THEN 'Completed'");
				sql.append( " ELSE APLD_STATUS");
				sql.append( " END AS Status,a.EMPM_NAME as cmbApldResponsibility,a.EMPM_KEYID as Responsibilty, ");
				sql.append(" replace(to_char(APLD_TARGETDATE,'DD-Mon-YYYY'),'31-Dec-2100','') AS dteApldTargetdate, ");
				sql.append(" replace(to_char(APLD_COMPLEATEDON,'DD-Mon-YYYY'),'31-Dec-2100','') AS dteApldCompleatedon,APLD_COUNTERMEASURE AS txtApldCountermeasure,APLD_REMARKS AS txtApldRmarks,APLM_MASTERREFID AS txtApldRefDocNo,APLM_REFDOCTYPE AS txtApldRefDocType, ");
			    sql.append("APLM_MAINTASK AS txtApldMaintask,APLD_ACTIONPLAN AS txtApldActionplan, ");
				sql.append(" b.EMPM_NAME as txtApldallotedby "
						+ "from  GEN_TL_ACTIONPLANMST  "
						+ " JOIN GEN_TL_ACTIONPLANDTL  ON  APLD_APLM_KEYID = APLM_KEYID "
						+ " JOIN gen_TL_employeemst a  ON a.EMPM_KEYID=APLD_RESPONSIBILITY "
						+ " JOIN gen_TL_employeemst b  ON b.EMPM_KEYID=APLM_CREATEDBY "
						+ " JOIN GEN_MV_FLIDHIERARCHY  ON APLM_FLID=FLID " );
//						+ " JOIN ADM_TL_USERMST ");
				sql.append(" where  a.EMPM_KEYID = ? ");
				CommonMessage.debugMsg("sql2 : "+sql);
				CommonMessage.debugMsg("sql2 : "+sql.toString());
				if (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromDate())
						&& CommonFunctions.isValidKeyId(actionPlanParams.getDtToDate())) {

					sql.append(" AND APLD_TARGETDATE::date BETWEEN ? AND ? ");
				}
				if (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromMonth())
						&& CommonFunctions.isValidKeyId(actionPlanParams.getDtToMonth())) {
						sql.append(" AND APLD_TARGETDATE BETWEEN TO_DATE(? , 'YYYY-MM-DD') AND TO_DATE(? , 'YYYY-MM-DD') ");
				}
				sql.append("");	
//				sql.append("  AND APLM_FLID=FLID AND A.EMPM_KEYID=APLD_RESPONSIBILITY ");
//				sql.append(" and aplm_createdby=usrm_keyid(+) and usrm_ccno=b.empm_keyid(+)  "); //AND INSTR(PARENTFLIDS||FLID,?) > 0
				sql.append(" AND APLD_STATUS <> 'C' order by APLD_KEYID desc ");
			 
		 } 
		 else{
		                sql.append(" select APLD_KEYID,DECODE(APLD_STATUS,'P','Pending','W','Work in Progress','C','Completed') Status,APLM_MAINTASK AS txtApldMaintask,APLD_ACTIONPLAN AS txtApldActionplan,");
						sql.append("  a.EMPM_NAME as cmbApldResponsibility,b.EMPM_NAME||''-''||b.EMPM_CODE as Responsibilty,");
		                sql.append(" replace(to_char(APLD_TARGETDATE,'DD-Mon-YYYY'),'31-Dec-2100','') AS dteApldTargetdate, ");
						sql.append(" replace(to_char(APLD_COMPLEATEDON,'DD-Mon-YYYY'),'31-Dec-2100','') AS dteApldCompleatedon ,APLD_COUNTERMEASURE AS txtApldCountermeasure,APLD_REMARKS AS txtApldRmarks, ");
						sql.append(" a.EMPM_NAME ,b.EMPM_NAME as txtApldallotedby from  GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL, gen_TL_employeemst a ,gen_TL_employeemst b,GEN_MV_FLIDHIERARCHY,ADM_TL_USERMST ");
						sql.append(" where APLD_APLM_KEYID=APLM_KEYID ");
						CommonMessage.debugMsg("sql2 : "+sql);
						CommonMessage.debugMsg("sql2 : "+sql.toString());
						if (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromDate())
								&& CommonFunctions.isValidKeyId(actionPlanParams.getDtToDate())) {

							sql.append(" AND trunc(APLD_TARGETDATE) BETWEEN ? AND ? ");
						}
						if (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromMonth())
								&& CommonFunctions.isValidKeyId(actionPlanParams.getDtToMonth())) {
								sql.append(" AND trunc(APLD_TARGETDATE) BETWEEN ? AND LAST_DAY(?)");
						}
						sql.append("");	
						sql.append("  AND APLM_FLID=FLID AND A.EMPM_KEYID=APLD_RESPONSIBILITY ");
					    sql.append(" AND INSTR(PARENTFLIDS||FLID,'"+actionPlanParams.getFlid()+"') > 0");
						sql.append(" and aplm_createdby=usrm_keyid(+) and usrm_ccno=b.empm_keyid(+)  "); //AND INSTR(PARENTFLIDS||FLID,?) > 0
						sql.append(" AND APLD_STATUS <> 'C' order by APLD_KEYID desc ");
		 }
		 CommonMessage.debugMsg("Sql Content::"+sql.toString());
		 return sql.toString();
	}
	public static String getAllEmployeeActionPlan(
            ActionPlanParams actionPlanParams) {
        StringBuilder sql = new StringBuilder();
//        sql.append(" SELECT APLM_KEYID,APLD_KEYID,TO_CHAR(APLM_PLANDATE,'DD-Mon-YYYY') ACTIONPLANDATE,REPLACE(APLM_REFDOCTYPE,'{}') REDOCTYPE,REPLACE(APLM_MASTERREFID,'{}') REFID,");
//        sql.append(" PARENTS FLOCATION,REPLACE(APLM_MAINTASK,'{}') MAINTASK,APLD_ACTIONPLAN ACTIONPLAN,A.EMPM_NAME RESPONSIBLITY, ");
//        sql.append(" REPLACE(TO_CHAR(APLD_TARGETDATE,'DD-Mon-YYYY'),'31-Dec-2100','') TARGETDATE,DECODE(APLD_STATUS,'P','Pending','W','Work in Progress','C','Completed') STATUS, ");
//        sql.append(" REPLACE(APLD_COUNTERMEASURE,'{}') COUNTERMEASURE,B.EMPM_NAME COMPLETEDBY, REPLACE(TO_CHAR(APLD_COMPLEATEDON,'DD-Mon-YYYY'),'31-Dec-2100','') COMPLETEDON , ");
//        sql.append(" REPLACE(APLD_REMARKS,'{}') REMARKS,c.empm_name as createdby,DECODE(c.EMPM_EMPLOYEETYPE,'R','Regular','A','Associate','C','Contract','M','Manager','B','Badli','T','Trainee','E','Executive','O','Others') AS EMPLOYEETYPE ");
//        sql.append(" FROM GEN_TL_ACTIONPLANDTL,GEN_TL_ACTIONPLANMST,GEN_MV_FLIDHIERARCHY, gen_TL_employeemst A, gen_TL_employeemst B");      
//        sql.append(" ,gen_TL_employeemst c WHERE APLM_KEYID = APLD_APLM_KEYID AND FLID = APLM_FLID AND APLD_RESPONSIBILITY = A.EMPM_KEYID(+) AND APLD_COMPLETEDBY = B.EMPM_KEYID(+) ");
//        sql.append(" and aplm_createdby=c.empm_keyid AND INSTR(PARENTFLIDS||FLID,?) > 0");
        sql.append("SELECT \r\n"
        		+ "        APLM_KEYID,\r\n"
        		+ "        APLD_KEYID,\r\n"
        		+ "        TO_CHAR(APLM_PLANDATE, 'DD-Mon-YYYY') AS ACTIONPLANDATE,\r\n"
        		+ "        REPLACE(APLM_REFDOCTYPE, '{}', '') AS REDOCTYPE,\r\n"
        		+ "        REPLACE(APLM_MASTERREFID, '{}', '') AS REFID,\r\n"
        		+ "        PARENTS AS FLOCATION,\r\n"
        		+ "        REPLACE(APLM_MAINTASK, '{}', '') AS MAINTASK,\r\n"
        		+ "        APLD_ACTIONPLAN AS ACTIONPLAN,\r\n"
        		+ "        A.EMPM_NAME AS RESPONSIBLITY,\r\n"
        		+ "        NULLIF(TO_CHAR(APLD_TARGETDATE, 'DD-Mon-YYYY'), '31-Dec-2100') AS TARGETDATE,\r\n"
        		+ "        CASE APLD_STATUS\r\n"
        		+ "            WHEN 'P' THEN 'Pending'\r\n"
        		+ "            WHEN 'W' THEN 'Work in Progress'\r\n"
        		+ "            WHEN 'C' THEN 'Completed'\r\n"
        		+ "        END AS STATUS,\r\n"
        		+ "        REPLACE(APLD_COUNTERMEASURE, '{}', '') AS COUNTERMEASURE,\r\n"
        		+ "        B.EMPM_NAME AS COMPLETEDBY,\r\n"
        		+ "        NULLIF(TO_CHAR(APLD_COMPLEATEDON, 'DD-Mon-YYYY'), '31-Dec-2100') AS COMPLETEDON,\r\n"
        		+ "        REPLACE(APLD_REMARKS, '{}', '') AS REMARKS,\r\n"
        		+ "        C.EMPM_NAME AS CREATEDBY,\r\n"
        		+ "        CASE C.EMPM_EMPLOYEETYPE\r\n"
        		+ "            WHEN 'R' THEN 'Regular'\r\n"
        		+ "            WHEN 'A' THEN 'Associate'\r\n"
        		+ "            WHEN 'C' THEN 'Contract'\r\n"
        		+ "            WHEN 'M' THEN 'Manager'\r\n"
        		+ "            WHEN 'B' THEN 'Badli'\r\n"
        		+ "            WHEN 'T' THEN 'Trainee'\r\n"
        		+ "            WHEN 'E' THEN 'Executive'\r\n"
        		+ "            WHEN 'O' THEN 'Others'\r\n"
        		+ "        END AS EMPLOYEETYPE\r\n"
        		+ "    FROM GEN_TL_ACTIONPLANDTL d\r\n"
        		+ "    JOIN GEN_TL_ACTIONPLANMST m ON m.APLM_KEYID = d.APLD_APLM_KEYID\r\n"
        		+ "    JOIN GEN_MV_FLIDHIERARCHY h ON h.FLID = m.APLM_FLID\r\n"
        		+ "    LEFT JOIN GEN_TL_EMPLOYEEMST A ON d.APLD_RESPONSIBILITY = A.EMPM_KEYID\r\n"
        		+ "    LEFT JOIN GEN_TL_EMPLOYEEMST B ON d.APLD_COMPLETEDBY = B.EMPM_KEYID\r\n"
        		+ "    JOIN GEN_TL_EMPLOYEEMST C ON m.APLM_CREATEDBY = C.EMPM_KEYID\r\n"
        		+ "    WHERE POSITION(? IN (h.PARENTFLIDS || h.FLID)) > 0 ");

        CommonMessage.debugMsg("sql1 : "+sql);
        CommonMessage.debugMsg("sql1 : "+sql.toString());
           String frmMonth=actionPlanParams.getDtFromMonth();
        String toMonth =actionPlanParams.getDtToMonth();
        CommonMessage.debugMsg(actionPlanParams.getDtToMonth() +" actionPlanParams.getDtToMonth() sql1 : "+actionPlanParams.getDtFromMonth());
        if (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromDate())
                && CommonFunctions.isValidKeyId(actionPlanParams.getDtToDate())) {
        	
        	
        //CommonMessage.debugMsg();
        	//sql.append(" AND APLM_PLANDATE BETWEEN '"+frmdate+"' AND '"+todate+"'");
            sql.append(" AND APLM_PLANDATE BETWEEN TO_DATE(? , 'YYYY-MM-DD') AND TO_DATE(? , 'YYYY-MM-DD') ");
        }
        if (CommonFunctions.isValidKeyId(actionPlanParams.getDtFromMonth())
                && CommonFunctions.isValidKeyId(actionPlanParams.getDtToMonth())) {
        	  // sql.append(" AND APLM_PLANDATE BETWEEN '"+frmMonth+"' AND LAST_DAY('"+toMonth+"')");
               sql.append(" AND APLM_PLANDATE BETWEEN TO_DATE(? , 'DD-Mon-YYYY')  AND TO_DATE(? , 'DD-Mon-YYYY') ");
        }
        sql.append(" ORDER BY APLM_PLANDATE DESC,APLM_REFDOCTYPE  DESC ");

      //  sql.append(" ORDER BY APLM_PLANDATE DESC,APLM_REFDOCTYPE ,APLM_MASTERREFID DESC ");
        CommonMessage.debugMsg("the Sql Data:::::"+sql);
        return sql.toString();
      
    }

	public static String addPaginationParams(String sql, GridParams gridParams) {
		String condSql="";
		if(gridParams.getGridFilters()!=null)   
			condSql+= FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
        CommonMessage.debugMsg("The condSql:::"+condSql);
		StringBuilder fsql = new StringBuilder(
				"SELECT  * from ( select row_number() over () as slno,a.* from ( select  * from ( ")
				.append(sql).append(" )  where 1 = 1 ").append(condSql)
				.append(" ) a ) ");
		if (Integer.parseInt(gridParams.getToRow()) > 0) {
			fsql.append(" where slno >=").append(gridParams.getFromRow())
					.append(" and slno <= ").append(gridParams.getToRow());
		}
		CommonMessage.debugMsg("fsql:::::"+fsql.toString());
		return fsql.toString();
	}

	public static String countSql(String innerSql, GridParams gridParams) {
		String condSql = FilterCondSql.makeGridFilterCond(gridParams
				.getGridFilters());

		return new StringBuilder(" SELECT COUNT(*) FROM ( SELECT * from ( ")
				.append(innerSql).append(" ) WHERE 1 =  1 ").append(condSql)
				.append(')').toString();
	}

	public static String updateActionPlanCompletionSql() {
		return " UPDATE GEN_TL_ACTIONPLANDTL SET APLD_TARGETDATE= TO_DATE(? , 'DD-Mon-YYYY'),APLD_STATUS=?,APLD_COMPLEATEDON= TO_DATE(? , 'DD-Mon-YYYY'),APLD_COMPLETEDBY=?,APLD_COUNTERMEASURE=?,APLD_REMARKS=?,APLD_RESPONSIBILITY=? WHERE APLD_KEYID= ? ";
	}
	
	public static String updateActionPlanMstCompletionSql() {
		return " UPDATE GEN_TL_ACTIONPLANMST SET  APLM_STATUS=COALESCE((SELECT DISTINCT APLD_STATUS FROM GEN_TL_ACTIONPLANDTL  WHERE APLD_APLM_KEYID=? and APLD_STATUS='P'),'C')  WHERE APLM_KEYID= ? ";
	}
	
	public static String updateJHAuditNcSatusSql() {
		return " UPDATE  JHA_TL_AUDITDTL Set JHAD_NCSTATUS = 'C' , JHAD_NCCLOSED = 'C' Where JHAD_KEYID IN ( Select APLM_DETAILREFID FROM  GEN_TL_ACTIONPLANMST  WHERE APLM_STATUS = 'C'  AND APLM_KEYID = ? ) ";
	}

	public static String getRemainderDataSql() {
		// TODO Auto-generated method stub
		String sql;
		sql="SELECT * from " + TBL_GEN_TL_ACTIONPLANMST + " where APLM_KEYID= ?";
		return sql;
	}
}
