package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class OplTlMstSql {

	public static final String TBL_OPL_TL_MST = "OPL_TL_MST";  

	TableFieldType [] oplmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, date, factoryid, tpmpillarid, sectionid, cellid, machineid
		, theme, themecategoryid, classification, classdescription, benefit
		, type, tradeid, presentcondition, presentimage, aftercondition
		, afterimage, lesson, preparedid, prepareddate, approvedid, approveddate
		, status, refdoctype, refdocno, remarks, relatedto, departmentmanager
		, sectionmanager, groupleader, requestflag, related, mouldid
		, isok, ispresent, elementid, flid, process, isupload,utiliseforfuture
		, mpworthy, aprovLevel, general, oplupload, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getOplmDbFields() {
		return oplmDbFields;
	}

	public OplTlMstSql()
	{
		oplmDbFields = new TableFieldType[ 51 ];
		for(int i = 0;i < 51; i++)
		{	
			oplmDbFields[ i ] = new TableFieldType();
		}
		oplmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "OPLM_KEYID";
		oplmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "OPLM_DATE";
		oplmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		oplmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "OPLM_FACTORYID";
		oplmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldName = "OPLM_TPMPILLARID";
		oplmDbFields[ tableFldConstants.tpmpillarid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "OPLM_SECTIONID";
		oplmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "OPLM_CELLID";
		oplmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "OPLM_MACHINEID";
		oplmDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.theme.ordinal() ].fieldName = "OPLM_THEME";
		oplmDbFields[ tableFldConstants.theme.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.themecategoryid.ordinal() ].fieldName = "OPLM_THEMECATEGORYID";
		oplmDbFields[ tableFldConstants.themecategoryid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.classification.ordinal() ].fieldName = "OPLM_CLASSIFICATION";
		oplmDbFields[ tableFldConstants.classification.ordinal() ].fieldType = 'C';

		oplmDbFields[ tableFldConstants.classdescription.ordinal() ].fieldName = "OPLM_CLASSDESCRIPTION";
		oplmDbFields[ tableFldConstants.classdescription.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.benefit.ordinal() ].fieldName = "OPLM_BENEFIT";
		oplmDbFields[ tableFldConstants.benefit.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.type.ordinal() ].fieldName = "OPLM_TYPE";
		oplmDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		oplmDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "OPLM_TRADEID";
		oplmDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.presentcondition.ordinal() ].fieldName = "OPLM_PRESENTCONDITION";
		oplmDbFields[ tableFldConstants.presentcondition.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.presentimage.ordinal() ].fieldName = "OPLM_PRESENTIMAGE";
		oplmDbFields[ tableFldConstants.presentimage.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.aftercondition.ordinal() ].fieldName = "OPLM_AFTERCONDITION";
		oplmDbFields[ tableFldConstants.aftercondition.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.afterimage.ordinal() ].fieldName = "OPLM_AFTERIMAGE";
		oplmDbFields[ tableFldConstants.afterimage.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.lesson.ordinal() ].fieldName = "OPLM_LESSON";
		oplmDbFields[ tableFldConstants.lesson.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.preparedid.ordinal() ].fieldName = "OPLM_PREPAREDID";
		oplmDbFields[ tableFldConstants.preparedid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldName = "OPLM_PREPAREDDATE";
		oplmDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldType = 'D';

		oplmDbFields[ tableFldConstants.approvedid.ordinal() ].fieldName = "OPLM_APPROVEDID";
		oplmDbFields[ tableFldConstants.approvedid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.approveddate.ordinal() ].fieldName = "OPLM_APPROVEDDATE";
		oplmDbFields[ tableFldConstants.approveddate.ordinal() ].fieldType = 'D';

		oplmDbFields[ tableFldConstants.status.ordinal() ].fieldName = "OPLM_STATUS";
		oplmDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		oplmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "OPLM_REFDOCTYPE";
		oplmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.refdocno.ordinal() ].fieldName = "OPLM_REFDOCNO";
		oplmDbFields[ tableFldConstants.refdocno.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "OPLM_REMARKS";
		oplmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "OPLM_RELATEDTO";
		oplmDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.departmentmanager.ordinal() ].fieldName = "OPLM_DEPARTMENTMANAGER";
		oplmDbFields[ tableFldConstants.departmentmanager.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.sectionmanager.ordinal() ].fieldName = "OPLM_SECTIONMANAGER";
		oplmDbFields[ tableFldConstants.sectionmanager.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.groupleader.ordinal() ].fieldName = "OPLM_GROUPLEADER";
		oplmDbFields[ tableFldConstants.groupleader.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.requestflag.ordinal() ].fieldName = "OPLM_REQUESTFLAG";
		oplmDbFields[ tableFldConstants.requestflag.ordinal() ].fieldType = 'C';

		oplmDbFields[ tableFldConstants.related.ordinal() ].fieldName = "OPLM_RELATED";
		oplmDbFields[ tableFldConstants.related.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.mouldid.ordinal() ].fieldName = "OPLM_MOULDID";
		oplmDbFields[ tableFldConstants.mouldid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.isok.ordinal() ].fieldName = "OPLM_ISOK";
		oplmDbFields[ tableFldConstants.isok.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.ispresent.ordinal() ].fieldName = "OPLM_ISPRESENT";
		oplmDbFields[ tableFldConstants.ispresent.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "OPLM_ELEMENTID";
		oplmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "OPLM_FLID";
		oplmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.process.ordinal() ].fieldName = "OPLM_PROCESS";
		oplmDbFields[ tableFldConstants.process.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.utiliseforfuture.ordinal() ].fieldName = "OPLM_UTILISEFORFUTURE";
		oplmDbFields[ tableFldConstants.utiliseforfuture.ordinal() ].fieldType = 'C';
		
		oplmDbFields[ tableFldConstants.isupload.ordinal() ].fieldName = "OPLM_ISUPLOAD";
		oplmDbFields[ tableFldConstants.isupload.ordinal() ].fieldType = 'C';

		oplmDbFields[ tableFldConstants.mpworthy.ordinal() ].fieldName = "OPLM_MPWORTHY";
		oplmDbFields[ tableFldConstants.mpworthy.ordinal() ].fieldType = 'C';

		oplmDbFields[ tableFldConstants.aprovLevel.ordinal() ].fieldName = "OPLM_APROV_LEVEL";
		oplmDbFields[ tableFldConstants.aprovLevel.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.general.ordinal() ].fieldName = "OPLM_ISGENERAL";
		oplmDbFields[ tableFldConstants.general.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.oplupload.ordinal() ].fieldName = "OPLM_OPLUPLOAD";
		oplmDbFields[ tableFldConstants.oplupload.ordinal() ].fieldType = 'C';

		oplmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "OPLM_TEMPFIELD4";
		oplmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		oplmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "OPLM_TEMPFIELD5";
		oplmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		oplmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "OPLM_ACTIVE";
		oplmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		oplmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OPLM_CREATEDBY";
		oplmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		oplmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OPLM_CREATEDON";
		oplmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		oplmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "OPLM_MODIFIEDON";
		oplmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_OPL_TL_MST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_OPL_TL_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_OPL_TL_MST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String selectSql(){
		return  "select * from " + TBL_OPL_TL_MST  + " where OPLM_KEYID = ? ";
	}
	
	public static String getOplStudentSql(){
		return  "select OPLL_OPLID,OPLL_DATE,'' as \"teacher\",EMPM_NAME  from " + TableNames.TBL_OPL_TL_LESSON + "," + TableNames.TBL_GEN_TL_EMPLOYEEMST 
				+ " where OPLL_EMPLOYEEID = EMPM_KEYID and " + " OPLL_OPLID = ? order by OPLL_DATE DESC";
	}
	public static String getOplImprvCategorySql()
	{
		return  "select 'False' as \"select\",OPLC_KEYID,OPLC_TPMPILLARID,OPLC_NAME,OPLC_CODE from " + TableNames.TBL_OPL_TL_CAT +" where OPLC_TPMPILLARID = ? ";
		
	}
	public static String getOplStudentInfoSql()
	{
		StringBuffer sql= new StringBuffer();
		sql.append(" select EMPM_NAME  from OPL_TL_LESSON,GEN_TL_EMPLOYEEMST" );
		sql.append(" where OPLL_EMPLOYEEID = EMPM_KEYID and  OPLL_OPLID = ? ");
		sql.append(" and OPLL_EMPLOYEEID = ? ");
		return sql.toString();
	}
    public static String getoplDocUpdatesSql(String oplmKeyid, String docId) {
		
		return "UPDATE GEN_TL_DOCUPDATES SET DCUP_DETAILID = '"+ oplmKeyid +"',DCUP_UPDATEDOCTYPE='OPL' where DCUP_KEYID= '"+ docId +"'";
	}
    public static String insertYYCounterMeasureSql(String yycmKeyId,String yyId, OplTlMst oplTlMst) 
	{
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO BDM_TL_YYCOUNTERMEASURELINK VALUES('"+yycmKeyId+"','"+yyId+"','OPL','"+oplTlMst.getOplmKeyid()+"','{}','-','-','-','-','-','Y',");
		sql.append("'"+oplTlMst.getOplmCreatedby()+"',to_date('"+oplTlMst.getOplmCreatedon()+"','dd-MON-yyyy hh24:mi:ss') ,to_date('"+oplTlMst.getOplmModifiedon()+"','dd-MON-yyyy hh24:mi:ss'))");
		
		
		return sql.toString();
		
	}

	public static String Deleteimageclear(String keyid, String imagetype) {
		// TODO Auto-generated method stub
		String sql=" DELETE FROM GEN_TL_ALLMODULEIMGFILE where imfl_refkeyid='"+keyid+"' and imfl_imagetype='"+imagetype+"'";
		CommonMessage.debugMsg("Delete Row Sql: "+sql);
		return sql;
	}

	public static String fillEmployeeDatainGrid(String keyid) {
		// TODO Auto-generated method stub
		String sql=" select TO_CHAR(SYSDATE,'DD-Mon-YYYY') as curdate,empm_keyid,empm_name,empm_code from gen_tl_employeemst where empm_keyid='"+keyid+"'";
		CommonMessage.debugMsg(" fillEmployeeDatainGrid : "+sql);
		return sql;
	}
	
	public static String getUpdatedRowOpl(String keyId) {
        StringBuffer sql = new StringBuffer();
        sql.append(
            "SELECT " +
            "  m.oplm_keyid AS txtoplno, " +
            "  to_char(m.oplm_date,'DD-MON-YYYY') AS opldate, " +
            "  replace(replace(m.oplm_theme,'<*',''),'*>','') AS theme, " +
            "  CASE trim(m.oplm_classification) " +
            "    WHEN 'BIT' THEN 'BASIC KNOWLEDGE,IMPROVEMENT CASES,TROUBLE CASES' " +
            "    WHEN 'BI'  THEN 'BASIC KNOWLEDGE,IMPROVEMENT CASES' " +
            "    WHEN 'BT'  THEN 'BASIC KNOWLEDGE,TROUBLE CASES' " +
            "    WHEN 'IT'  THEN 'IMPROVEMENT CASES,TROUBLE CASES' " +
            "    WHEN 'B'   THEN 'BASIC KNOWLEDGE' " +
            "    WHEN 'I'   THEN 'IMPROVEMENT CASES' " +
            "    WHEN 'T'   THEN 'TROUBLE CASES' " +
            "  END AS classification, " +
            "  CASE m.oplm_type WHEN 'R' THEN 'REGULAR' ELSE m.oplm_type END AS opltype, " +
            "  e.empm_name AS preparedby, " +
            "  CASE m.oplm_status " +
            "    WHEN '-' THEN 'JH LEADER APPROVAL PENDING' " +
            "    WHEN 'P' THEN 'JH LEADER APPROVAL PENDING' " +
            "    WHEN 'E' THEN 'REWORK' " +
            "    WHEN 'A' THEN coalesce(m.oplm_aprov_level::text,'') || ' APPROVAL PENDING' " +
            "    WHEN 'C' THEN 'COMPLETED' " +
            "    WHEN 'R' THEN 'REJECTED' " +
            "    ELSE m.oplm_status " +
            "  END AS status, " +
            "  f.functionalloc AS functionallocation, " +
            "  CASE e.empm_employeetype " +
            "    WHEN 'R' THEN 'Manager'  WHEN 'A' THEN 'Associate' " +
            "    WHEN 'C' THEN 'Contract' WHEN 'M' THEN 'Manager' " +
            "    WHEN 'B' THEN 'Badli'    WHEN 'T' THEN 'Trainee' " +
            "    WHEN 'E' THEN 'Executive' WHEN 'O' THEN 'Others' " +
            "  END AS employeetype, " +
            "  m.oplm_flid AS flid, " +
            "  to_char(m.oplm_date,'YYYYMMDD') AS orderdate " +
            "FROM opl_tl_mst m " +
            "JOIN gen_vw_fnln f ON m.oplm_flid = f.fnln_keyid " +
            "LEFT JOIN gen_tl_employeemst e ON m.oplm_preparedid = e.empm_keyid " +
            "WHERE m.oplm_active = 'Y' AND m.oplm_keyid = '" + keyId + "'"
        );
        CommonMessage.debugMsg("OplUpdatedRow SQL: " + sql.toString());
        return sql.toString();
    }

}

