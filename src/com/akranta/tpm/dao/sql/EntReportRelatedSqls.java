package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntReportRelatedSqls {
	
	public static String getEmpAssessmentTopicRatingSql(String fromSpoke)
	{
		StringBuffer sql= new StringBuffer();		
		if(fromSpoke.equals("false"))
		{
			sql.append(" SELECT  TOPICS, TARGETRATING,CURRENTRATING,TO_CHAR(TARGETRATING - CURRENTRATING), " );
			sql.append(" MAX(TO_CHAR(BACH_FROMDATE, 'DD-MON-YYYY')) AS BACHFROMDT,MAX(TO_CHAR(BACH_TILLDATE, 'DD-MON-YYYY')) AS BACHTODT");
			sql.append(" FROM ENT_VW_SKILLGAPANALYSIS, ENT_TL_BATCHMST, ENT_TL_ASSESSMENTDTL WHERE ASMM_KEYID = ? " );
			sql.append(" AND BACH_KEYID(+) = ASMD_BACH_KEYID AND ASMM_KEYID = ASMD_ASMM_KEYID");
			sql.append(" GROUP BY TOPICS, TARGETRATING, CURRENTRATING,TO_CHAR (TARGETRATING - CURRENTRATING)");
			sql.append(" ORDER BY TOPICS");
			return sql.toString();
		}
		else if(fromSpoke.equals("true"))
			return "SELECT   spoke, AVG (targetrating), AVG (currentrating),TO_CHAR (AVG (currentrating) - AVG (targetrating)),TO_CHAR (bach_fromdate, 'dd-mon-yyyy'),TO_CHAR (bach_tilldate, 'dd-mon-yyyy')"+
					"FROM ent_vw_skillgapanalysis, ent_tl_batchmst, ent_tl_assessmentdtl WHERE asmm_keyid =? AND bach_keyid = asmd_bach_keyid AND asmm_keyid = asmd_asmm_keyid "+
					" GROUP BY spoke,bach_fromdate,bach_tilldate";
		else 
			return null;
	}
	public static String getEmpAssessmentBefAftRatingSql(String empId,String date,String fromSpoke){
		String Sql="";
		String query = "";
		
		if(fromSpoke.equals("false")){
			query += "replace(TOPICS,',','')";
		}	 
		else if(fromSpoke.equals("true")){
			query += "spoke";
		}
		Sql+= "  select "+ query + ", avg(targetRating)," ;
		Sql +=" avg(currentRating) from  ent_vw_skillgapanalysis where EREL_EMPM_KEYID = '"+empId+ "'" ;
		if(CommonFunctions.isValidKeyId(date)){			
			Sql +=	"and To_char(EVALDATE,'DD-MON-YYYY') ='"+date+"'" ;
		}	
		Sql += " group by "+ query + " ";	
		CommonMessage.debugMsg(Sql);
		return Sql;					
	}

}
