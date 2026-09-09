package com.akranta.tpm.dao.sql;


import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class KznTlProjectmaicMileMstSql {

	public static final String TBL_KZN_TL_PROJECTMAIC_MILE_MST = "KZN_TL_PROJ_MILESTONE_MST";  

	TableFieldType [] kmmmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, kzpm_keyid, flid, stages, fromdate, todate, empm_keyid
		, status, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, createdby, active, createdon, modifiedon
	}

	public TableFieldType[] getKmmmDbFields() {
		return kmmmDbFields;
	}

	public KznTlProjectmaicMileMstSql()
	{
		kmmmDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			kmmmDbFields[ i ] = new TableFieldType();
		}
		kmmmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KMMM_KEYID";
		kmmmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kmmmDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldName = "KMMM_KZPM_KEYID";
		kmmmDbFields[ tableFldConstants.kzpm_keyid.ordinal() ].fieldType = 'V';

		kmmmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "KMMM_FLID";
		kmmmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		kmmmDbFields[ tableFldConstants.stages.ordinal() ].fieldName = "KMMM_STAGES";
		kmmmDbFields[ tableFldConstants.stages.ordinal() ].fieldType = 'V';

		kmmmDbFields[ tableFldConstants.fromdate.ordinal() ].fieldName = "KMMM_FROMDATE";
		kmmmDbFields[ tableFldConstants.fromdate.ordinal() ].fieldType = 'D';

		kmmmDbFields[ tableFldConstants.todate.ordinal() ].fieldName = "KMMM_TODATE";
		kmmmDbFields[ tableFldConstants.todate.ordinal() ].fieldType = 'D';

		kmmmDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "KMMM_EMPM_KEYID";
		kmmmDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		kmmmDbFields[ tableFldConstants.status.ordinal() ].fieldName = "KMMM_STATUS";
		kmmmDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'V';

		kmmmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KMMM_TEMPFIELD1";
		kmmmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		kmmmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KMMM_TEMPFIELD2";
		kmmmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		kmmmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KMMM_TEMPFIELD3";
		kmmmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		kmmmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KMMM_TEMPFIELD4";
		kmmmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		kmmmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KMMM_TEMPFIELD5";
		kmmmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		kmmmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KMMM_CREATEDBY";
		kmmmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kmmmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KMMM_ACTIVE";
		kmmmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kmmmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KMMM_CREATEDON";
		kmmmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kmmmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KMMM_MODIFIEDON";
		kmmmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_PROJECTMAIC_MILE_MST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_PROJECTMAIC_MILE_MST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_PROJECTMAIC_MILE_MST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String selectMilestonesSql(CommonFilter commonFilter) {
//		StringBuilder sql = new StringBuilder("select 'keyid' as keyid,'Assigend','selectval','Select' ,'Milestones','Description','Target Date',' '" );
//		sql.append(",'Revised date','Assigned To','Status','Remarks','Delete','History','status','milestone mst id' KMMD_TEMPFIELD1,1 dataorder  from dual ");
//		sql.append(" union  ");
//		sql.append(" select kmmd_keyid as keyid,empm_name,'','',nvl(kmmd_milestone,MILM_NAME) kmmd_milestone,nvl(kmmd_description,MILM_DESC) kmmd_description ");
//		sql.append(" ,to_char(kmmd_targetdate,'dd-Mon-YYYY'), ");
//		sql.append(" '','',kmmd_empm_keyid,kmmd_status,kmmd_remarks,'','' ");
//		sql.append(" ,Decode(status,'P','Pending','W','Work In Progress','C','Completed','S','Short Close') ,MILM_KEYID KMMD_TEMPFIELD1,2 dataorder ");
//		sql.append(" from ( ");
//		sql.append(" select kmmd_keyid ,empm_name,kmmd_milestone,kmmd_description,kmmd_targetdate, ");
//		sql.append(" kmmd_empm_keyid,kmmd_status,kmmd_remarks,kmmd_status status,KMMD_TEMPFIELD1 ");
//		sql.append(" from KZN_TL_PROJ_MILESTONE_DTL,KZN_TL_PROJ_MILESTONE_MST,gen_tl_employeemst ");
//		sql.append(" where kmmd_empm_keyid = empm_keyid AND KMMM_KEYID=KMMD_KMMM_KEYID  ");
//		sql.append(" AND KMMM_STAGES='"+commonFilter.getStatus()+"'  ");
//		sql.append(" and kmmd_kmmm_keyid ='"+commonFilter.getKey()+"'),KZN_TL_MILESTONEMST  ");
//		sql.append(" where  MILM_TYPE='"+commonFilter.getStatus()+"' ");
//		sql.append(" and MILM_KEYID=KMMD_TEMPFIELD1(+) ");
		
		StringBuilder sql = new StringBuilder("");
		sql.append("SELECT "
				+ "    'keyid' AS KEYID, "
				+ "    'Assigend', "
				+ "    'selectval', "
				+ "    'Select', "
				+ "    'Milestones', "
				+ "    'Description', "
				+ "    'Target Date', "
				+ "    ' ', "
				+ "    'Revised date', "
				+ "    'Assigned To', "
				+ "    'Status', "
				+ "    'Remarks', "
				+ "    'Delete', "
				+ "    'History', "
				+ "    'status', "
				+ "    'milestone mst id' AS KMMD_TEMPFIELD1, "
				+ "    1 AS DATAORDER "
				+ "UNION "
				+ "SELECT "
				+ "    KMMD.KMMD_KEYID AS KEYID, "
				+ "    KMMD.EMPM_NAME, "
				+ "    '', "
				+ "    '', "
				+ "    COALESCE(KMMD.KMMD_MILESTONE, M.MILM_NAME) AS KMMD_MILESTONE, "
				+ "    COALESCE(KMMD.KMMD_DESCRIPTION, M.MILM_DESC) AS KMMD_DESCRIPTION, "
				+ "    TO_CHAR(KMMD.KMMD_TARGETDATE, 'DD-Mon-YYYY'), "
				+ "    '', "
				+ "    '', "
				+ "    KMMD.KMMD_EMPM_KEYID, "
				+ "    KMMD.KMMD_STATUS, "
				+ "    KMMD.KMMD_REMARKS, "
				+ "    '', "
				+ "    '', "
				+ "    CASE KMMD.STATUS "
				+ "        WHEN 'P' THEN 'Pending' "
				+ "        WHEN 'W' THEN 'Work In Progress' "
				+ "        WHEN 'C' THEN 'Completed' "
				+ "        WHEN 'S' THEN 'Short Close' "
				+ "    END AS STATUS, "
				+ "    M.MILM_KEYID AS KMMD_TEMPFIELD1, "
				+ "    2 AS DATAORDER "
				+ "FROM "
				+ "( "
				+ "    SELECT "
				+ "        D.KMMD_KEYID, "
				+ "        E.EMPM_NAME, "
				+ "        D.KMMD_MILESTONE, "
				+ "        D.KMMD_DESCRIPTION, "
				+ "        D.KMMD_TARGETDATE, "
				+ "        D.KMMD_EMPM_KEYID, "
				+ "        D.KMMD_STATUS, "
				+ "        D.KMMD_REMARKS, "
				+ "        D.KMMD_STATUS AS STATUS, "
				+ "        D.KMMD_TEMPFIELD1 "
				+ "    FROM "
				+ "        KZN_TL_PROJ_MILESTONE_DTL AS D "
				+ "    JOIN GEN_TL_EMPLOYEEMST AS E "
				+ "        ON D.KMMD_EMPM_KEYID = E.EMPM_KEYID "
				+ "    JOIN KZN_TL_PROJ_MILESTONE_MST AS MST "
				+ "        ON MST.KMMM_KEYID = D.KMMD_KMMM_KEYID "
				+ "    WHERE "
				+ "        MST.KMMM_STAGES = 'M' "
				+ "        AND D.KMMD_KMMM_KEYID = ''  "
				+ ") AS KMMD "
				+ "LEFT JOIN KZN_TL_MILESTONEMST AS M "
				+ "    ON M.MILM_KEYID = KMMD.KMMD_TEMPFIELD1 "
				+ "WHERE "
				+ "    M.MILM_TYPE = 'M'  ");
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			/*SQL +=" union all select kmmd_keyid as keyid,empm_name,'','',kmmd_milestone,kmmd_description,to_char(kmmd_targetdate,'dd-Mon-YYYY'),'','',kmmd_empm_keyid,kmmd_status,kmmd_remarks,'','',Decode(kmmd_status,'P','Pending','W','Work In Progress','C','Completed','S','Short Close') from KZN_TL_PROJ_MILESTONE_DTL,gen_tl_employeemst");
			sql.append( " where kmmd_empm_keyid = empm_keyid and kmmd_kmmm_keyid ='";
			SQL += commonFilter.getKey()+"'");*/	
			
			sql.append("Union SELECT kmmd_keyid AS keyid, empm_name, '', '', ");
			sql.append("        kmmd_milestone, kmmd_description, ");
			sql.append("        TO_CHAR(kmmd_targetdate, 'DD-Mon-YYYY') AS kmmd_targetdate, ");
			sql.append("        '', '', kmmd_empm_keyid, kmmd_status, kmmd_remarks, '', '', ");
			sql.append("        CASE kmmd_status ");
			sql.append("            WHEN 'P' THEN 'Pending' ");
			sql.append("            WHEN 'W' THEN 'Work In Progress' ");
			sql.append("            WHEN 'C' THEN 'Completed' ");
			sql.append("            WHEN 'S' THEN 'Short Close' ");
			sql.append("        END AS status, ");
			sql.append("        KMMD_TEMPFIELD1, ");
			sql.append("        3 AS dataorder ");
			sql.append(" FROM KZN_TL_PROJ_MILESTONE_DTL D ");
			sql.append(" JOIN GEN_TL_EMPLOYEEMST E ON D.kmmd_empm_keyid = E.empm_keyid ");
			sql.append(" JOIN KZN_TL_PROJ_MILESTONE_MST M ON M.kmmm_keyid = D.kmmd_kmmm_keyid ");
			sql.append(" WHERE M.kmmm_stages = '" + commonFilter.getStatus() + "' ");
			sql.append("   AND D.KMMD_TEMPFIELD1 = '-' ");
			sql.append("   AND D.kmmd_kmmm_keyid = '" + commonFilter.getKey() + "' ");

			sql.append(" ORDER BY dataorder, KMMD_TEMPFIELD1 ");
			
//			sql.append(" union select kmmd_keyid as keyid,empm_name,'','',kmmd_milestone,kmmd_description,to_char(kmmd_targetdate,'dd-Mon-YYYY'), ");
//			sql.append(" '','',kmmd_empm_keyid,kmmd_status,kmmd_remarks,'','' ");
//			sql.append(" ,Decode(kmmd_status,'P','Pending','W','Work In Progress','C','Completed','S','Short Close') ,KMMD_TEMPFIELD1,3 dataorder ");
//			sql.append(" from KZN_TL_PROJ_MILESTONE_DTL,KZN_TL_PROJ_MILESTONE_MST,gen_tl_employeemst  ");
//			sql.append(" where kmmd_empm_keyid = empm_keyid AND KMMM_KEYID=KMMD_KMMM_KEYID  ");
//			sql.append(" AND KMMM_STAGES='"+commonFilter.getStatus()+"' AND KMMD_TEMPFIELD1='-' ");
//			sql.append(" and kmmd_kmmm_keyid ='"+commonFilter.getKey()+"' ");
//			sql.append("  order by dataorder, KMMD_TEMPFIELD1 ");
		}
		
		
		return sql.toString();
	}

	public String getRecall() {
		StringBuilder sql= new StringBuilder("SELECT  K.KMMM_KEYID, K.KMMM_KZPM_KEYID, K.KMMM_FLID,K.KMMM_STAGES, to_char(K.KMMM_FROMDATE,'DD-MM-YYYY'), to_char(K.KMMM_TODATE,'DD-MM-YYYY'),"); 
			sql.append(" K.KMMM_EMPM_KEYID, K.KMMM_STATUS, K.KMMM_TEMPFIELD1, K.KMMM_TEMPFIELD2, K.KMMM_TEMPFIELD3, K.KMMM_TEMPFIELD4, K.KMMM_TEMPFIELD5, K.KMMM_CREATEDBY, K.KMMM_ACTIVE,");
			sql.append("K.KMMM_CREATEDON, K.KMMM_MODIFIEDON FROM KZN_TL_PROJ_MILESTONE_MST K WHERE K.KMMM_KEYID =?");
		return sql.toString();
	}

	public String getDeleteDetail(String kmmmKeyid) {
		// TODO Auto-generated method stub
		return "Delete from KZN_TL_PROJ_MILESTONE_DTL WHERE KMMD_KMMM_KEYID ='"+kmmmKeyid+"'";
	}

	public static String getUpdateCreation(String kmmmKzpmKeyid,String Definestages,String maicStages) {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg(maicStages +" IN Side the Update Creation Method "+Definestages );
		return "Update KZN_TL_PROJECTCREATIONMST SET KZPM_DEFINESTAGE='"+Definestages+"',KZPM_MAICSTAGE='"+maicStages+"' WHERE KZPM_KEYID = '"+kmmmKzpmKeyid+"'";
	}
	public static String getMileStoneStatus(String kznKeyId){
		
		StringBuilder sql = new StringBuilder("SELECT LISTAGG(STAGE) WITHIN GROUP (ORDER BY STAGE)  FROM (");
		sql.append(" SELECT KMMM_KZPM_KEYID,REPLACE(KMMM_STAGES||MAX(KMMM_STATUS),'W','P') STAGE FROM Kzn_Tl_PROJ_MILESTONE_MST "); 
		sql.append(" WHERE  KMMM_STAGES='M' GROUP BY KMMM_KZPM_KEYID,KMMM_STAGES ");
		sql.append(" UNION ");
		sql.append(" SELECT KMMM_KZPM_KEYID,REPLACE(KMMM_STAGES||MAX(KMMM_STATUS),'W','P') STAGE FROM Kzn_Tl_PROJ_MILESTONE_MST");
		sql.append(" WHERE KMMM_STAGES='A' GROUP BY KMMM_KZPM_KEYID,KMMM_STAGES");
		sql.append(" UNION ");
		sql.append(" SELECT KMMM_KZPM_KEYID,REPLACE(KMMM_STAGES||MAX(KMMM_STATUS),'W','P') STAGE FROM Kzn_Tl_PROJ_MILESTONE_MST ");
		sql.append(" WHERE  KMMM_STAGES='I' GROUP BY KMMM_STAGES,KMMM_KZPM_KEYID");
		sql.append(" UNION ");
		sql.append(" SELECT KMMM_KZPM_KEYID,REPLACE(KMMM_STAGES||MAX(KMMM_STATUS),'W','P') STAGE FROM Kzn_Tl_PROJ_MILESTONE_MST ");
		sql.append(" WHERE KMMM_STAGES='C' GROUP BY KMMM_KZPM_KEYID,KMMM_STAGES");
		sql.append(" )WHERE KMMM_KZPM_KEYID='"+kznKeyId+"'");
		return sql.toString();
	}
	
	public static String getMileStoneStatus(String kznKeyId,String stage)
	{		 
		//String sql = " SELECT REPLACE(REPLACE(KMMM_STAGES||MAX(KMMM_STATUS),KMMM_STAGES||'W',KMMM_STAGES||'P'),KMMM_STAGES||'C',KMMM_STAGES||'I') STAGE");
		//StringBuilder sql = new StringBuilder(" SELECT DECODE(MAX(KMMM_STATUS),'P','P','W','P','C','I') STAGE");
		StringBuilder sql = new StringBuilder(
			    "SELECT CASE MAX(KMMM_STATUS) " +
			    "       WHEN 'P' THEN 'P' " +
			    "       WHEN 'W' THEN 'P' " +
			    "       WHEN 'C' THEN 'I' " +
			    "       ELSE NULL END AS STAGE "
			);
		sql.append( " FROM KZN_TL_PROJ_MILESTONE_MST  WHERE  KMMM_STAGES='"+stage+"'");
		sql.append( " AND KMMM_KZPM_KEYID='"+kznKeyId+"'");
		sql.append( " GROUP BY KMMM_STAGES ");
		return sql.toString();
	}
	
	public static String getUpdateDefineStage(String kznKeyId,String status) {
		return  new StringBuilder(" Update KZN_TL_PROJECTCREATIONMST SET KZPM_DEFINESTAGE = '").append(status).append("' , KZPM_DEFINECOMPLETEDDATE = CURRENT_DATE  WHERE KZPM_KEYID = '").append(kznKeyId).append('\'').toString();		
	}
	
	public static String getdmcUpdateDefineStage(String kznKeyId,String status) {
		return  new StringBuilder(" Update KZN_TL_DMCFIPCREATIONMST SET DMCM_DEFINESTAGE = '").append(status).append("' WHERE DMCM_KEYID = '").append(kznKeyId).append('\'').toString();		
	}
	
	public static String getUpdateMaicStage(String kznKeyId,String maicStage,String Stage) {
		String colName="";
		String colName2="";
		if(Stage.equals("M")){
			colName="KZPM_MEASURESTAGE";
			colName2="KZPM_MEASURECOMPLETEDDATE";
		}			
		else if(Stage.equals("A")){
			colName="KZPM_ANALYSESTAGE";
			colName2="KZPM_ANALYSECOMPLETEDDATE";
		}
		else if(Stage.equals("I")){
			colName="KZPM_IMPROVESTAGE";
			colName2="KZPM_IMPROVECOMPLETEDDATE";
		}	
		else if(Stage.equals("C")){
			colName="KZPM_CONTROLSTAGE";
			colName2="KZPM_CONTROLCOMPLETEDDATE";
		}
		else if(Stage.equals("X")){
			colName="KZPM_CLOSURESTAGE";
			colName2="KZPM_CLOSURECOMPLETEDDATE";
		}
		
		CommonMessage.debugMsg(" Insdie the maic state 12 "+new StringBuilder("Update KZN_TL_PROJECTCREATIONMST SET ").append(colName).append(" = '").append(maicStage).append("' , ").append(colName2).append(" = CURRENT_DATE WHERE KZPM_KEYID = '").append(kznKeyId).append('\'').toString());
		return  new StringBuilder("Update KZN_TL_PROJECTCREATIONMST SET ").append(colName).append(" = '").append(maicStage).append("' , ").append(colName2).append(" = CURRENT_DATE  WHERE KZPM_KEYID = '").append(kznKeyId).append('\'').toString();		
	}
	
	public static String getdmcUpdateMaicStage(String kznKeyId,String maicStage,String Stage) {
		String colName="";
		if(Stage.equals("M")){
			colName="DMCM_MEASURESTAGE";
		}			
		else if(Stage.equals("A")){
			colName="DMCM_ANALYSESTAGE";
		}
		else if(Stage.equals("I")){
			colName="DMCM_IMPROVESTAGE";
		}	
		else if(Stage.equals("C")){
			colName="DMCM_CONTROLSTAGE";
		}
		else if(Stage.equals("X")){
			colName="DMCM_CLOSURESTAGE";
		}
		return  new StringBuilder("Update KZN_TL_DMCFIPCREATIONMST SET ").append(colName).append(" = '").append(maicStage).append("' WHERE DMCM_KEYID = '").append(kznKeyId).append('\'').toString();		
	}
	public static String getMaicStage(String kznKeyId,String Stage) {
		String colName="";
		if(Stage.equals("M")){
			colName="KZPM_MEASURESTAGE";
		}			
		else if(Stage.equals("A")){
			colName="KZPM_ANALYSESTAGE";
		}
		else if(Stage.equals("I")){
			colName="KZPM_IMPROVESTAGE";
		}	
		else if(Stage.equals("C")){
			colName="KZPM_CONTROLSTAGE";
		}
		else if(Stage.equals("X")){
			colName="KZPM_CLOSURESTAGE";
		}
		CommonMessage.debugMsg(" Insdie the maic state 34 "+new StringBuilder("select ").append(colName).append(" from KZN_TL_PROJECTCREATIONMST WHERE KZPM_KEYID = '").append(kznKeyId).append('\'').toString());

		return  new StringBuilder("select ").append(colName).append(" from KZN_TL_PROJECTCREATIONMST WHERE KZPM_KEYID = '").append(kznKeyId).append('\'').toString();		
	}

	
	public static String getdmcMaicStage(String kznKeyId,String Stage) {
		String colName="";
		if(Stage.equals("M")){
			colName="DMCM_MEASURESTAGE";
		}			
		else if(Stage.equals("A")){
			colName="DMCM_ANALYSESTAGE";
		}
		else if(Stage.equals("I")){
			colName="DMCM_IMPROVESTAGE";
		}	
		else if(Stage.equals("C")){
			colName="DMCM_CONTROLSTAGE";
		}
		else if(Stage.equals("X")){
			colName="DMCM_CLOSURESTAGE";
		}
		return  new StringBuilder("select ").append(colName).append(" from KZN_TL_DMCFIPCREATIONMST WHERE DMCM_KEYID = '").append(kznKeyId).append('\'').toString();		
	}
}

