package com.akranta.tpm.dao.sql;

public class KznTlDmcfipcreationmstSql {

	public static final String TBL_KZN_TL_DMCFIPCREATIONMST = "KZN_TL_DMCFIPCREATIONMST";  

	TableFieldType [] dmcmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, startdate, enddate, projectname, area, projectchamp
		, projectno, benefits, savings, projectmetrics, problemstatement
		, businesscase, goalobj, scopeconst, definestage, measurestage
		, analysestage, controlstage, improvestage, closurestage, imprcategory
		, istangible, isintangible, verifiedamnt, amtverifyremarks, wave
		, oldresponsibility, belt, tempfield4, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getDmcmDbFields() {
		return dmcmDbFields;
	}

	public KznTlDmcfipcreationmstSql()
	{
		dmcmDbFields = new TableFieldType[ 34 ];
		for(int i = 0;i < 34; i++)
		{	
			dmcmDbFields[ i ] = new TableFieldType();
		}
		dmcmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DMCM_KEYID";
		dmcmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "DMCM_FLID";
		dmcmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.startdate.ordinal() ].fieldName = "DMCM_STARTDATE";
		dmcmDbFields[ tableFldConstants.startdate.ordinal() ].fieldType = 'D';

		dmcmDbFields[ tableFldConstants.enddate.ordinal() ].fieldName = "DMCM_ENDDATE";
		dmcmDbFields[ tableFldConstants.enddate.ordinal() ].fieldType = 'D';

		dmcmDbFields[ tableFldConstants.projectname.ordinal() ].fieldName = "DMCM_PROJECTNAME";
		dmcmDbFields[ tableFldConstants.projectname.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.area.ordinal() ].fieldName = "DMCM_AREA";
		dmcmDbFields[ tableFldConstants.area.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.projectchamp.ordinal() ].fieldName = "DMCM_PROJECTCHAMP";
		dmcmDbFields[ tableFldConstants.projectchamp.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.projectno.ordinal() ].fieldName = "DMCM_PROJECTNO";
		dmcmDbFields[ tableFldConstants.projectno.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.benefits.ordinal() ].fieldName = "DMCM_BENEFITS";
		dmcmDbFields[ tableFldConstants.benefits.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.savings.ordinal() ].fieldName = "DMCM_SAVINGS";
		dmcmDbFields[ tableFldConstants.savings.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.projectmetrics.ordinal() ].fieldName = "DMCM_PROJECTMETRICS";
		dmcmDbFields[ tableFldConstants.projectmetrics.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.problemstatement.ordinal() ].fieldName = "DMCM_PROBLEMSTATEMENT";
		dmcmDbFields[ tableFldConstants.problemstatement.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.businesscase.ordinal() ].fieldName = "DMCM_BUSINESSCASE";
		dmcmDbFields[ tableFldConstants.businesscase.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.goalobj.ordinal() ].fieldName = "DMCM_GOALOBJ";
		dmcmDbFields[ tableFldConstants.goalobj.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.scopeconst.ordinal() ].fieldName = "DMCM_SCOPECONST";
		dmcmDbFields[ tableFldConstants.scopeconst.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.definestage.ordinal() ].fieldName = "DMCM_DEFINESTAGE";
		dmcmDbFields[ tableFldConstants.definestage.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.measurestage.ordinal() ].fieldName = "DMCM_MEASURESTAGE";
		dmcmDbFields[ tableFldConstants.measurestage.ordinal() ].fieldType = 'C';

		dmcmDbFields[ tableFldConstants.analysestage.ordinal() ].fieldName = "DMCM_ANALYSESTAGE";
		dmcmDbFields[ tableFldConstants.analysestage.ordinal() ].fieldType = 'C';

		dmcmDbFields[ tableFldConstants.controlstage.ordinal() ].fieldName = "DMCM_CONTROLSTAGE";
		dmcmDbFields[ tableFldConstants.controlstage.ordinal() ].fieldType = 'C';

		dmcmDbFields[ tableFldConstants.improvestage.ordinal() ].fieldName = "DMCM_IMPROVESTAGE";
		dmcmDbFields[ tableFldConstants.improvestage.ordinal() ].fieldType = 'C';

		dmcmDbFields[ tableFldConstants.closurestage.ordinal() ].fieldName = "DMCM_CLOSURESTAGE";
		dmcmDbFields[ tableFldConstants.closurestage.ordinal() ].fieldType = 'C';

		dmcmDbFields[ tableFldConstants.imprcategory.ordinal() ].fieldName = "DMCM_IMPRCATEGORY";
		dmcmDbFields[ tableFldConstants.imprcategory.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.istangible.ordinal() ].fieldName = "DMCM_ISTANGIBLE";
		dmcmDbFields[ tableFldConstants.istangible.ordinal() ].fieldType = 'C';

		dmcmDbFields[ tableFldConstants.isintangible.ordinal() ].fieldName = "DMCM_ISINTANGIBLE";
		dmcmDbFields[ tableFldConstants.isintangible.ordinal() ].fieldType = 'C';

		dmcmDbFields[ tableFldConstants.verifiedamnt.ordinal() ].fieldName = "DMCM_VERIFIEDAMNT";
		dmcmDbFields[ tableFldConstants.verifiedamnt.ordinal() ].fieldType = 'N';

		dmcmDbFields[ tableFldConstants.amtverifyremarks.ordinal() ].fieldName = "DMCM_AMTVERIFYREMARKS";
		dmcmDbFields[ tableFldConstants.amtverifyremarks.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.wave.ordinal() ].fieldName = "DMCM_WAVE";
		dmcmDbFields[ tableFldConstants.wave.ordinal() ].fieldType = 'N';

		dmcmDbFields[ tableFldConstants.oldresponsibility.ordinal() ].fieldName = "DMCM_OLDRESPONSIBILITY";
		dmcmDbFields[ tableFldConstants.oldresponsibility.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.belt.ordinal() ].fieldName = "DMCM_BELT";
		dmcmDbFields[ tableFldConstants.belt.ordinal() ].fieldType = 'V';
		
		dmcmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "DMCM_TEMPFIELD4";
		dmcmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		dmcmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DMCM_ACTIVE";
		dmcmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		dmcmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DMCM_CREATEDBY";
		dmcmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dmcmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DMCM_CREATEDON";
		dmcmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dmcmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DMCM_MODIFIEDON";
		dmcmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_DMCFIPCREATIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_DMCFIPCREATIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_DMCFIPCREATIONMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getdmcRecall() {
		StringBuilder sql = new StringBuilder(" SELECT  DMCM_KEYID, DMCM_FLID,to_char(DMCM_STARTDATE,'dd-Mon-YYYY'),to_char(DMCM_ENDDATE,'dd-Mon-YYYY'), DMCM_PROJECTNAME, DMCM_AREA,DMCM_PROJECTCHAMP, DMCM_PROJECTNO, DMCM_BENEFITS,");
		sql.append(" DMCM_SAVINGS, DMCM_PROJECTMETRICS, DMCM_PROBLEMSTATEMENT,DMCM_BUSINESSCASE, DMCM_GOALOBJ, DMCM_SCOPECONST, DMCM_DEFINESTAGE, DMCM_MEASURESTAGE," );
		sql.append(" DMCM_ANALYSESTAGE,DMCM_CONTROLSTAGE,DMCM_IMPROVESTAGE, DMCM_CLOSURESTAGE," ); 
		sql.append(" DMCM_IMPRCATEGORY, DMCM_ISTANGIBLE, DMCM_ISINTANGIBLE, DMCM_VERIFIEDAMNT,DMCM_AMTVERIFYREMARKS,DMCM_WAVE,DMCM_OLDRESPONSIBILITY,");
		sql.append(" DMCM_BELT, DMCM_TEMPFIELD4,DMCM_ACTIVE, DMCM_CREATEDBY, DMCM_CREATEDON, DMCM_MODIFIEDON FROM KZN_TL_DMCFIPCREATIONMST K WHERE DMCM_KEYID = ? ");
		return sql.toString();
	}
	
	public static String getdmcDMTLeader(String dmcmFlid) {
		// TODO Auto-generated method stub 
		StringBuilder sql = new StringBuilder("select FRT_EMPM_KEYID from gen_Tl_fnlnroleteam where ");
		sql.append(" FRT_ROLE_KEYID in (select ROLE_KEYID from adm_tl_rolemst where upper(COALESCE(ROLE_NAME,' '))='DMT LEADER') "); 
		sql.append(" and FRT_FNLN_KEYID='");
		sql.append(dmcmFlid );
		sql.append("' ");
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
	
}

