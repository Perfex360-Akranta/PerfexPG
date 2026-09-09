package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.KznTlProjectcreationmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class KznTlProjectcreationmstSql {

	public static final String TBL_KZN_TL_PROJECTCREATIONMST = "KZN_TL_PROJECTCREATIONMST";  

	TableFieldType [] kzpmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, startdate, enddate, projectname, area, projectchamp
		, projectno, benefits, savings, projectmetrics, problemstatement
		, businesscase, goalobj, scopeconst, definestage, measurestage
		, analysestage, controlstage, improvestage, closurestage,definetargetdate, measuretargetdate
		, analysetargetdate, improvetargetdate, controltargetdate, closuretargetdate,definecompleteddate, measurecompleteddate
		, analysecompleteddate, improvecompleteddate, controlcompleteddate, closurecompleteddate, imprcategory
		, istangible, isintangible, verifiedamnt,finalamnt,amtverifyremark,wave
		,oldresponsibility  ,belt  ,tempfield4 , active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getKzpmDbFields() {
		return kzpmDbFields;
	}

	public KznTlProjectcreationmstSql()
	{
		kzpmDbFields = new TableFieldType[ 47 ];
		for(int i = 0;i < 47; i++)
		{	
			kzpmDbFields[ i ] = new TableFieldType();
		}
		kzpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KZPM_KEYID";
		kzpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "KZPM_FLID";
		kzpmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.startdate.ordinal() ].fieldName = "KZPM_STARTDATE";
		kzpmDbFields[ tableFldConstants.startdate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.enddate.ordinal() ].fieldName = "KZPM_ENDDATE";
		kzpmDbFields[ tableFldConstants.enddate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.projectname.ordinal() ].fieldName = "KZPM_PROJECTNAME";
		kzpmDbFields[ tableFldConstants.projectname.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.area.ordinal() ].fieldName = "KZPM_AREA";
		kzpmDbFields[ tableFldConstants.area.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.projectchamp.ordinal() ].fieldName = "KZPM_PROJECTCHAMP";
		kzpmDbFields[ tableFldConstants.projectchamp.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.projectno.ordinal() ].fieldName = "KZPM_PROJECTNO";
		kzpmDbFields[ tableFldConstants.projectno.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.benefits.ordinal() ].fieldName = "KZPM_BENEFITS";
		kzpmDbFields[ tableFldConstants.benefits.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.savings.ordinal() ].fieldName = "KZPM_SAVINGS";
		kzpmDbFields[ tableFldConstants.savings.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.projectmetrics.ordinal() ].fieldName = "KZPM_PROJECTMETRICS";
		kzpmDbFields[ tableFldConstants.projectmetrics.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.problemstatement.ordinal() ].fieldName = "KZPM_PROBLEMSTATEMENT";
		kzpmDbFields[ tableFldConstants.problemstatement.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.businesscase.ordinal() ].fieldName = "KZPM_BUSINESSCASE";
		kzpmDbFields[ tableFldConstants.businesscase.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.goalobj.ordinal() ].fieldName = "KZPM_GOALOBJ";
		kzpmDbFields[ tableFldConstants.goalobj.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.scopeconst.ordinal() ].fieldName = "KZPM_SCOPECONST";
		kzpmDbFields[ tableFldConstants.scopeconst.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.definestage.ordinal() ].fieldName = "KZPM_DEFINESTAGE";
		kzpmDbFields[ tableFldConstants.definestage.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.measurestage.ordinal() ].fieldName = "KZPM_MEASURESTAGE";
		kzpmDbFields[ tableFldConstants.measurestage.ordinal() ].fieldType = 'C';

		kzpmDbFields[ tableFldConstants.analysestage.ordinal() ].fieldName = "KZPM_ANALYSESTAGE";
		kzpmDbFields[ tableFldConstants.analysestage.ordinal() ].fieldType = 'C';

		kzpmDbFields[ tableFldConstants.controlstage.ordinal() ].fieldName = "KZPM_CONTROLSTAGE";
		kzpmDbFields[ tableFldConstants.controlstage.ordinal() ].fieldType = 'C';

		kzpmDbFields[ tableFldConstants.improvestage.ordinal() ].fieldName = "KZPM_IMPROVESTAGE";
		kzpmDbFields[ tableFldConstants.improvestage.ordinal() ].fieldType = 'C';

		kzpmDbFields[ tableFldConstants.closurestage.ordinal() ].fieldName = "KZPM_CLOSURESTAGE";
		kzpmDbFields[ tableFldConstants.closurestage.ordinal() ].fieldType = 'C';
		
		kzpmDbFields[ tableFldConstants.definetargetdate.ordinal() ].fieldName = "KZPM_DEFINETARGETDATE";
		kzpmDbFields[ tableFldConstants.definetargetdate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.measuretargetdate.ordinal() ].fieldName = "KZPM_MEASURETARGETDATE";
		kzpmDbFields[ tableFldConstants.measuretargetdate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.analysetargetdate.ordinal() ].fieldName = "KZPM_ANALYSETARGETDATE";
		kzpmDbFields[ tableFldConstants.analysetargetdate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.controltargetdate.ordinal() ].fieldName = "KZPM_CONTROLTARGETDATE";
		kzpmDbFields[ tableFldConstants.controltargetdate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.improvetargetdate.ordinal() ].fieldName = "KZPM_IMPROVETARGETDATE";
		kzpmDbFields[ tableFldConstants.improvetargetdate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.closuretargetdate.ordinal() ].fieldName = "KZPM_CLOSURETARGETDATE";
		kzpmDbFields[ tableFldConstants.closuretargetdate.ordinal() ].fieldType = 'D';
		
		kzpmDbFields[ tableFldConstants.definecompleteddate.ordinal() ].fieldName = "KZPM_DEFINECOMPLETEDDATE";
		kzpmDbFields[ tableFldConstants.definecompleteddate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.measurecompleteddate.ordinal() ].fieldName = "KZPM_MEASURECOMPLETEDDATE";
		kzpmDbFields[ tableFldConstants.measurecompleteddate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.analysecompleteddate.ordinal() ].fieldName = "KZPM_ANALYSECOMPLETEDDATE";
		kzpmDbFields[ tableFldConstants.analysecompleteddate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.controlcompleteddate.ordinal() ].fieldName = "KZPM_CONTROLCOMPLETEDDATE";
		kzpmDbFields[ tableFldConstants.controlcompleteddate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.improvecompleteddate.ordinal() ].fieldName = "KZPM_IMPROVECOMPLETEDDATE";
		kzpmDbFields[ tableFldConstants.improvecompleteddate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.closurecompleteddate.ordinal() ].fieldName = "KZPM_CLOSURECOMPLETEDDATE";
		kzpmDbFields[ tableFldConstants.closurecompleteddate.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.imprcategory.ordinal() ].fieldName = "KZPM_IMPRCATEGORY";
		kzpmDbFields[ tableFldConstants.imprcategory.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.istangible.ordinal() ].fieldName = "KZPM_ISTANGIBLE";
		kzpmDbFields[ tableFldConstants.istangible.ordinal() ].fieldType = 'C';

		kzpmDbFields[ tableFldConstants.isintangible.ordinal() ].fieldName = "KZPM_ISINTANGIBLE";
		kzpmDbFields[ tableFldConstants.isintangible.ordinal() ].fieldType = 'C';
		
		kzpmDbFields[ tableFldConstants.verifiedamnt.ordinal() ].fieldName = "KZPM_VERIFIEDAMNT";
		kzpmDbFields[ tableFldConstants.verifiedamnt.ordinal() ].fieldType = 'N';
		
		kzpmDbFields[ tableFldConstants.finalamnt.ordinal() ].fieldName = "KZPM_FINALAMNT";
		kzpmDbFields[ tableFldConstants.finalamnt.ordinal() ].fieldType = 'N';
		
		kzpmDbFields[ tableFldConstants.amtverifyremark.ordinal() ].fieldName = "KZPM_AMTVERIFYREMARKS";
		kzpmDbFields[ tableFldConstants.amtverifyremark.ordinal() ].fieldType = 'V';
		
		kzpmDbFields[ tableFldConstants.wave.ordinal() ].fieldName = "KZPM_WAVE";
		kzpmDbFields[ tableFldConstants.wave.ordinal() ].fieldType = 'N';
		
		kzpmDbFields[ tableFldConstants.oldresponsibility.ordinal() ].fieldName = "KZPM_OLDRESPONSIBILITY";
		kzpmDbFields[ tableFldConstants.oldresponsibility.ordinal() ].fieldType = 'V';
		
		kzpmDbFields[ tableFldConstants.belt.ordinal() ].fieldName = "KZPM_BELT";
		kzpmDbFields[ tableFldConstants.belt.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KZPM_TEMPFIELD4";
		kzpmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		kzpmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KZPM_ACTIVE";
		kzpmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kzpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KZPM_CREATEDBY";
		kzpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kzpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KZPM_CREATEDON";
		kzpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kzpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KZPM_MODIFIEDON";
		kzpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_PROJECTCREATIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_PROJECTCREATIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
	
	public static String getUpdateMAICStatusSql(KznTlProjectcreationmst kznTlProjectcreationmst , String stage )
	{
		String sql = " Update "+ TBL_KZN_TL_PROJECTCREATIONMST +" SET ";
		if ("M".equals(stage)) {
			sql += " KZPM_MEASURESTAGE = 'C' , KZPM_MEASURECOMPLETEDDATE = CURRENT_DATE  "; 
		}else if ("A".equals(stage)) {
			sql += " KZPM_ANALYSESTAGE = 'C' , KZPM_ANALYSECOMPLETEDDATE = CURRENT_DATE  "; 
		}else if ("I".equals(stage)) {
			sql += " KZPM_IMPROVESTAGE = 'C' , KZPM_IMPROVECOMPLETEDDATE = CURRENT_DATE  "; 
		}else if ("C".equals(stage)) {
			sql += " KZPM_CONTROLSTAGE = 'C' , KZPM_CONTROLCOMPLETEDDATE = CURRENT_DATE  "; 
		}
		
		sql += " where  KZPM_KEYID  = '" + kznTlProjectcreationmst.getKzpmKeyid() + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_PROJECTCREATIONMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getRecall() {
		StringBuilder sql = new StringBuilder(" SELECT  K.KZPM_KEYID, K.KZPM_FLID,to_char(K.KZPM_STARTDATE,'dd-Mon-YYYY'),to_char(K.KZPM_ENDDATE,'dd-Mon-YYYY'), K.KZPM_PROJECTNAME, K.KZPM_AREA,K.KZPM_PROJECTCHAMP, K.KZPM_PROJECTNO, K.KZPM_BENEFITS,");
		sql.append(" K.KZPM_SAVINGS, K.KZPM_PROJECTMETRICS, K.KZPM_PROBLEMSTATEMENT,K.KZPM_BUSINESSCASE, K.KZPM_GOALOBJ, K.KZPM_SCOPECONST, K.KZPM_DEFINESTAGE, K.KZPM_MEASURESTAGE," );
		sql.append(" K.KZPM_ANALYSESTAGE,K.KZPM_CONTROLSTAGE,K.KZPM_IMPROVESTAGE, K.KZPM_CLOSURESTAGE," ); 
		sql.append(" to_char(K.KZPM_DEFINETARGETDATE,'dd-Mon-YYYY'),to_char(K.KZPM_MEASURETARGETDATE,'dd-Mon-YYYY'),to_char(K.KZPM_ANALYSETARGETDATE,'dd-Mon-YYYY'),to_char(K.KZPM_IMPROVETARGETDATE,'dd-Mon-YYYY'),to_char(K.KZPM_CONTROLTARGETDATE,'dd-Mon-YYYY'),to_char(K.KZPM_CLOSURETARGETDATE,'dd-Mon-YYYY')," );
		sql.append(" to_char(K.KZPM_DEFINECOMPLETEDDATE,'dd-Mon-YYYY'),to_char(K.KZPM_MEASURECOMPLETEDDATE,'dd-Mon-YYYY'),to_char(K.KZPM_ANALYSECOMPLETEDDATE,'dd-Mon-YYYY'),to_char(K.KZPM_IMPROVECOMPLETEDDATE,'dd-Mon-YYYY'),to_char(K.KZPM_CONTROLCOMPLETEDDATE,'dd-Mon-YYYY'),to_char(K.KZPM_CLOSURECOMPLETEDDATE,'dd-Mon-YYYY')," );
		sql.append(" K.KZPM_IMPRCATEGORY, K.KZPM_ISTANGIBLE, K.KZPM_ISINTANGIBLE, KZPM_VERIFIEDAMNT,KZPM_FINALAMNT,KZPM_AMTVERIFYREMARKS,KZPM_WAVE,KZPM_OLDRESPONSIBILITY,");
		sql.append(" KZPM_BELT, K.KZPM_TEMPFIELD4,K.KZPM_ACTIVE, K.KZPM_CREATEDBY, K.KZPM_CREATEDON, K.KZPM_MODIFIEDON FROM KZN_TL_PROJECTCREATIONMST K WHERE KZPM_KEYID = ? ");
		return sql.toString();
	}
	

	public static String getDeleteKpi(String masterkeyid){
		return "DELETE FROM kzn_tl_project_kpi_link WHERE KPKL_KZPM_KEYID = '"+masterkeyid+"'";
	}
	public static String getDeleteResource(String masterkeyid){
		return "DELETE FROM kzn_tl_project_resource_link WHERE KPRL_KZPM_KEYID = '"+masterkeyid+"'";
	}
	public static String getDeleteMilestoneDetail(String masterkeyiddt){
		return "DELETE FROM KZN_TL_PROJ_MILESTONE_DTL WHERE KMMD_KMMM_KEYID = '"+masterkeyiddt+"'";
	}
	public static String getDeleteMilestoneMaster(String masterkeyid){
		return "DELETE FROM KZN_TL_PROJ_MILESTONE_MST WHERE KMMM_KZPM_KEYID = '"+masterkeyid+"'";
	}
	public static String getDeleteKaizen(String masterkeyid){
		return "DELETE FROM KZN_TL_PROJECT_KAIZEN_LINK WHERE KPLK_KZPM_KEYID = '"+masterkeyid+"'";
	}
	
	public static String getDeleteKaizendtl(String masterkeyiddtl){ 
        return "DELETE FROM KZN_TL_KKPROJECTPRIORITYDTL WHERE KPPD_KPPM_KEYID = '"+masterkeyiddtl+"'";	
	}
	
	public static String getDeleteKK(String masterkeyid){
		return "DELETE FROM KZN_TL_KKPROJECTPRIORITYMST WHERE KPPM_KZPM_KEYID = '"+masterkeyid+"'";
	}  	
	public static String getchecklistlink(String masterkeyid){
		return "DELETE FROM KZN_TL_PROJECT_CHECKLIST_LINK WHERE PCLL_PROJECTID= '"+masterkeyid+"'";
	}

	public static String getDeleteActionPlandtl(String masterskeyiddtl1){
		return "DELETE FROM  GEN_TL_ACTIONPLANDTL WHERE APLD_APLM_KEYID = '"+masterskeyiddtl1+"'";
	}
	
	public static String getDeleteActionPlan(String masterkeyid){
		return "DELETE FROM Gen_Tl_Actionplanmst WHERE APLM_DETAILREFID = '"+masterkeyid+"'";
	}
   
	public static String getDMTLeader(String kzpmFlid) {
		// TODO Auto-generated method stub 
		StringBuilder sql = new StringBuilder("select FRT_EMPM_KEYID from gen_Tl_fnlnroleteam where ");
		sql.append(" FRT_ROLE_KEYID in (select ROLE_KEYID from adm_tl_rolemst where upper(COALESCE(ROLE_NAME,' '))='DMTLEADER') "); 
		sql.append(" and FRT_FNLN_KEYID='");
		sql.append(kzpmFlid );
		sql.append("' ");
		return sql.toString();
	}

	public static String getCheckLists() {
		// TODO Auto-generated method stub 
//		StringBuilder sql = new StringBuilder(" select PCLL_KEYID,PCLL_PROJECTID,PCLM_KEYID,PCLM_CHECKLIST,");
//		sql.append(" PCLL_INCLUDE,DECODE (pcll_include, 'N','Y','Y', 'N',pcll_include) as NotInclude," );
//		sql.append(" DECODE (pcll_include, 'X','Y','Y','N',pcll_include) as NotAppl,PCLL_VERIFIEDSTATUS, " );
//		sql.append(" DECODE (pcll_verifiedstatus, 'N', 'Y','Y','N', pcll_verifiedstatus) as NotOk, ");
//		sql.append(" count(DMDM_REFDOCNO) filemanager,pcll_include||PCLL_VERIFIEDSTATUS oldval " ); 
//		sql.append(" from KZN_TL_PROJECT_CHECKLIST_LINK,KZN_TL_PROJECT_CHECKLISTMST ,dcm_tl_documentmanager "); 
//		sql.append(" where PCLL_CHECKLISTID(+) = PCLM_KEYID and DMDM_REFDOCNO(+) = pcll_keyid and PCLM_TYPE = ? AND PCLL_PROJECTID(+) = ? ");
//		sql.append(" group by  pclm_slno,pcll_keyid, pcll_projectid, pclm_keyid, pclm_checklist,pcll_verifiedstatus, pcll_include ,pcll_include || pcll_verifiedstatus ");
//		sql.append(" ORDER BY PCLM_SLNO ");
		StringBuilder sql = new StringBuilder("SELECT "
				+ "    PCLL.PCLL_KEYID,"
				+ "    PCLL.PCLL_PROJECTID,"
				+ "    PCLM.PCLM_KEYID,"
				+ "    PCLM.PCLM_CHECKLIST,"
				+ "    PCLL.PCLL_INCLUDE,"
				+ "    CASE "
				+ "        WHEN PCLL.PCLL_INCLUDE = 'N' THEN 'Y'"
				+ "        WHEN PCLL.PCLL_INCLUDE = 'Y' THEN 'N'"
				+ "        ELSE PCLL.PCLL_INCLUDE"
				+ "    END AS NotInclude,"

				+ "    CASE "
				+ "        WHEN PCLL.PCLL_INCLUDE = 'X' THEN 'Y' "
				+ "        WHEN PCLL.PCLL_INCLUDE = 'Y' THEN 'N' "
				+ "        ELSE PCLL.PCLL_INCLUDE"
				+ "    END AS NotAppl,"

				+ "    PCLL.PCLL_VERIFIEDSTATUS,"

				+ "    CASE "
				+ "        WHEN PCLL.PCLL_VERIFIEDSTATUS = 'N' THEN 'Y' "
				+ "        WHEN PCLL.PCLL_VERIFIEDSTATUS = 'Y' THEN 'N' "
				+ "        ELSE PCLL.PCLL_VERIFIEDSTATUS"
				+ "    END AS NotOk,"
				+ "    COUNT(DMDM.DMDM_REFDOCNO) AS filemanager,"
				+ "    (PCLL.PCLL_INCLUDE || PCLL.PCLL_VERIFIEDSTATUS) AS oldval"
				+ " FROM "
				+ "    KZN_TL_PROJECT_CHECKLISTMST PCLM"
				+ "    LEFT JOIN KZN_TL_PROJECT_CHECKLIST_LINK PCLL "
				+ "        ON PCLL.PCLL_CHECKLISTID = PCLM.PCLM_KEYID"
				+ "        AND PCLL.PCLL_PROJECTID  = ?   "
				+ "    LEFT JOIN DCM_TL_DOCUMENTMANAGER DMDM "
				+ "        ON DMDM.DMDM_REFDOCNO = PCLL.PCLL_KEYID "
				+ " WHERE"
				+ "    PCLM.PCLM_TYPE = ?  "
				+ "GROUP BY"
				+ "    PCLM.PCLM_SLNO,"
				+ "    PCLL.PCLL_KEYID,"
				+ "    PCLL.PCLL_PROJECTID,"
				+ "    PCLM.PCLM_KEYID,"
				+ "    PCLM.PCLM_CHECKLIST,"
				+ "    PCLL.PCLL_VERIFIEDSTATUS,"
				+ "    PCLL.PCLL_INCLUDE,"
				+ "    (PCLL.PCLL_INCLUDE || PCLL.PCLL_VERIFIEDSTATUS)"
				+ "    ORDER BY"
				+ "    PCLM.PCLM_SLNO;");
		return sql.toString();
	}
	
	public static String getAllStageStatusSql(){
		return " SELECT KZPM_DEFINESTAGE,KZPM_MEASURESTAGE,KZPM_ANALYSESTAGE,KZPM_IMPROVESTAGE,KZPM_CONTROLSTAGE,KZPM_CLOSURESTAGE FROM KZN_TL_PROJECTCREATIONMST WHERE KZPM_KEYID = ? ";
	}
	
	public static String getdmcAllStageStatusSql(){
		return " SELECT DMCM_DEFINESTAGE,DMCM_MEASURESTAGE,DMCM_ANALYSESTAGE,DMCM_IMPROVESTAGE,DMCM_CONTROLSTAGE,DMCM_CLOSURESTAGE FROM KZN_TL_DMCFIPCREATIONMST WHERE DMCM_KEYID = ? ";
	}


	public static String Removejhmember(String keyid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql=" DELETE FROM kzn_tl_project_resource_link WHERE kprl_keyid ='"+keyid+"'";
		CommonMessage.debugMsg("Delete Row Sql: "+sql);
		return sql;
	}
}

