package com.akranta.tpm.dao.sql;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class AbnormalityReportSqls {
	
	public static String getAbnormalityReportSql(){
		return "ABN_PC_ABNORMALITY.ABN_FN_ABNREPORTGENERAL";
	}
	
public static String getAbnDetailsSql(String keyId,GridParams gridParams){
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT  ROWNUM as slno,ABNM_KEYID,REPLACE(REPLACE(ABNM_DESCRIPTION,'<*',''),'*>','') AS ABNITEM,");
		sb.append("REPLACE(REPLACE(REPLACE(ABNM_DETAILEDESC,'<*',''),'*>',''),'{}','') AS ABNDETAILDESC,DECODE(ABNM_STATUS ,'C','COMPLETED','PENDING' ) AS ABNSTATUS ");
		sb.append("FROM ABN_TL_ABNORMALITY,GEN_TL_SECTIONMST WHERE SECT_KEYID = ABNM_SECTIONID");
		
		if(UIUtils.isValidKeyId(keyId))
		{
			if(!keyId.substring(0, 3).equals("CMP"))
				sb.append(getCondnSql(keyId));
		}
		sb.append(" ORDER BY ABNSTATUS DESC ,ABNM_KEYID DESC");
		CommonMessage.debugMsg("test Sql....."+sb.toString());
		return sb.toString();
	}

public static String getCondnSql(String keyId){

	 if(keyId.substring(0, 3).equals("FCT"))
        return " AND SECT_FACTORYID = '" + keyId + "'";
	 else if(keyId.substring(0, 3).equals("LIN"))
        return " AND SECT_KEYID = '" + keyId + "'";
	 else if(keyId.substring(0, 3).equals("CEL"))
        return " AND ABNM_CELLID = '" + keyId + "'";
	 else if(keyId.substring(0, 3).equals("MCH"))
	    return " AND ABNM_EQUIPMENTID = '" + keyId + "'";
	 else if(keyId.substring(0, 3).equals("ASM"))
		    return " AND ABNM_ASSEMBLYID = '" + keyId + "'";
	 else
		return null;
}

public static String getAbnDetailsSqlCount(String keyId, GridParams gridParams) {
	StringBuffer sb = new StringBuffer();
	
	sb.append(" SELECT  ROWNUM as slno,ABNM_KEYID,REPLACE(REPLACE(ABNM_DESCRIPTION,'<*',''),'*>','') AS ABNITEM,");
	sb.append("REPLACE(REPLACE(REPLACE(ABNM_DETAILEDESC,'<*',''),'*>',''),'{}','') AS ABNDETAILDESC,DECODE(ABNM_STATUS ,'C','COMPLETED','PENDING' ) AS ABNSTATUS ");
	sb.append("FROM ABN_TL_ABNORMALITY,GEN_TL_SECTIONMST WHERE SECT_KEYID = ABNM_SECTIONID ");
	
	if(UIUtils.isValidKeyId(keyId))
	{
		if(!keyId.substring(0, 3).equals("CMP"))
			sb.append(getCondnSql(keyId));
	}
	sb.append(" ORDER BY ABNSTATUS DESC ,ABNM_KEYID DESC");
	CommonMessage.debugMsg("test Sql....."+sb.toString());
	return sb.toString();
}

public static String getRepeatedAbn(CommonFilter commonFilter) {
StringBuffer sb = new StringBuffer();
	String machine = FilterCondSql.getComboSelectionId(commonFilter.getMachine());
	String Assembly = FilterCondSql.getComboSelectionId(commonFilter.getAssembly());
	
	String flid = commonFilter.getFlid();

	String type = FilterCondSql.getComboSelectionId(commonFilter.getAbnType());

//	sb.append(" SELECT ROWNUM AS slno, 'ABN Id 'as KEYID, 'Description','Functional Location','Detected Date','Detected Name','JH','Equipment', 0 AS dataorder  FROM DUAL union  ");
//	sb.append(" SELECT ROWNUM AS slno, abnm_keyid,REPLACE (REPLACE (abnm_description, '<*', ''),'*>','') AS abnormalitydesc,FUNCTIONALLOC,to_char(ABNM_DETECTIONDATE),EMPM_NAME,CELL_NAME,MCHM_MACHINENAME, 1 AS dataorder ");
//	sb.append("FROM ABN_TL_ABNORMALITY,gen_vw_fnln,gen_tl_employeemst WHERE 1=1  ");
//	sb.append(" and ABNM_FLID=FNLN_KEYID and ABNM_DETECTEDBY=EMPM_KEYID(+) ");
	sb.append("SELECT row_number() over () AS slno, 'ABN Id 'as abnm_keyid, 'Description','Functional Location','Detected Date','Detected Name','JH','Equipment', 0 AS dataorder   \r\n"
			+ "union   \r\n"
			+ "SELECT row_number() over (ORDER BY ABNM_DETECTIONDATE desc ,abnm_keyid desc) AS slno, abnm_keyid,REPLACE (REPLACE (abnm_description, '<*', ''),'*>','') AS abnormalitydesc,FUNCTIONALLOC,to_char(ABNM_DETECTIONDATE,'DD-Mon-YYYY'),EMPM_NAME,CELL_NAME,MCHM_MACHINENAME, 1 AS dataorder \r\n"
			+ "FROM ABN_TL_ABNORMALITY \r\n"
			+ " JOIN gen_vw_fnln ON ABNM_FLID=FNLN_KEYID   \r\n"
			+ " Left JOIN gen_tl_employeemst ON ABNM_DETECTEDBY=EMPM_KEYID  WHERE 1=1 ");
	//if(UIUtils.isValidKeyId(machine))
		//sb.append("and  ABNM_EQUIPMENTID= '"+commonFilter.getMachine().getId()+"'");
	if(UIUtils.isValidKeyId(flid))
		sb.append("and  ABNM_FLID= '"+commonFilter.getFlid()+"'");
	if(UIUtils.isValidKeyId(type))
		sb.append("  and ABNM_TYPEID= '"+commonFilter.getAbnType().getId()+"'");
	
	CommonMessage.debugMsg("test Sql....."+sb.toString());
	return sb.toString();
}

public static String getAbnAllocation(CommonFilter commonFilter) {
	
	StringBuffer sb = new StringBuffer();
	sb.append(" select * from (");
	sb.append("SELECT  'Functional Location','Target Date', 'Trade', 'Responsibility','Abnormality No.','Expected Date', 'Description', 'Abnormality Type','Detected Date','Detected Name','JH','Equipment', 0 AS dataorder FROM DUAL UNION ");
	sb.append(" SELECT  '2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / ESDB-2','', '', '', 'AB1301122','28-Dec-2013', 'CONTAMINATION','CONTAMINATION SOURCES','06-APR-13','ATPL','FIBRELINE 1','NFL-1 UPS ROOM AHU', 1 AS dataorder FROM DUAL UNION");
	sb.append(" SELECT  '2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / NFL-1 UPS ROOM AHU','', '', '', 'AB1301120','28-Dec-2013', 'HARD TO ACCESS','HARD TO ACCESS','06-APR-13','ATPL','FIBRELINE 1','NFL-1 UPS ROOM AHU',  1 AS dataorder FROM DUAL UNION");
	sb.append(" SELECT '2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 2 / NWSW_PN2', '', '', '', 'ABN12070156','28-Dec-2013','HYDRUOLIC UNIT PIPE OIL LEAKGE', 'MINOR FLAWS', '06-APR-13','ATPL','FIBRELINE 1','NFL-1 UPS ROOM AHU',1 AS dataorder  FROM DUAL UNION ");
	sb.append(" SELECT '2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 2 / SERVO STABLIZER (NFL-2).', '', '', '', 'ABN12070095','26-Dec-2013', 'LOCK DAMAGE','HARD TO ACCESS', '06-APR-13','ATPL','FIBRELINE 1','NFL-1 UPS ROOM AHU',1 AS dataorder FROM DUAL UNION ");
	sb.append(" SELECT  '2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 2 / SERVO STABLIZER (NFL-2).','', '', '', 'ABN12070157','28-Dec-2013','UN WANTED HOLE M/C BACK SIDE', 'HARD TO ACCESS','06-APR-13','ATPL','FIBRELINE 1','NFL-1 UPS ROOM AHU',1 AS dataorder FROM DUAL UNION ");
	sb.append(" SELECT  '2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / NFL-1 UPS ROOM AHU','', '', '', 'ABN12070138','25-Dec-2013', 'CONVEYOR', 'MINOR FLAWS','06-APR-13','ATPL','FIBRELINE 1','NFL-1 UPS ROOM AHU',1 AS dataorder FROM DUAL UNION ");
	sb.append(" SELECT  '2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / ESDB-2','', '', '', 'ABN120701156','28-Dec-2013', 'OIL LEAKAGE','HARD TO ACCESS', '06-APR-13','ATPL','FIBRELINE 1','NFL-1 UPS ROOM AHU',1 AS dataorder FROM DUAL UNION ");
	sb.append(" SELECT  '2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / NFL-1 UPS ROOM AHU','', '', '', 'ABN12070168','27-Dec-2013','COVER SUPPLY BROKEN', 'HARD TO ACCESS','06-APR-13','ATPL','FIBRELINE 1','NFL-1 UPS ROOM AHU', 1 AS dataorder FROM DUAL UNION ");
	sb.append(" SELECT  '2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 2 / NWSW_PN2','', '', '', 'ABN12070115','28-Dec-2013','M/C BACK SIDE BOLT MISSING', 'HARD TO ACCESS', '06-APR-13','ATPL','FIBRELINE 1','NFL-1 UPS ROOM AHU',1 AS dataorder FROM DUAL UNION ");
	sb.append(" SELECT '2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / ESDB-2','', '', '', 'ABN1207020','31-Dec-2013', 'TABLE NOT FIXED','HARD TO ACCESS','06-APR-13','ATPL','FIBRELINE 1','NFL-1 UPS ROOM AHU', 1 AS dataorder FROM DUAL");
	sb.append(") order by DATAORDER");
	CommonMessage.debugMsg("test Sql....."+sb.toString());
	return sb.toString();
}

public static String getview(String from, String to) throws Exception{
	// TODO Auto-generated method stub
	StringBuffer sb = new StringBuffer();
	CommonMessage.debugMsg("IN side the get view");
	sb.append(" SELECT * FROM ABN_TL_ABNORMALITY WHERE DAY BETWEEN ");
	return sb.toString();
}

	

}
