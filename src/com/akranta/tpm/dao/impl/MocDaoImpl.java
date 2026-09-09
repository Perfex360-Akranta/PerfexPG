package com.akranta.tpm.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.bind.ValidationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.MocDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlActionplandtlSql;
import com.akranta.tpm.dao.sql.GenTlActionplanmstSql;
import com.akranta.tpm.dao.sql.HazopDtlSql;
import com.akranta.tpm.dao.sql.HazopMstSql;
import com.akranta.tpm.dao.sql.MOCPssrReccommendSql;
import com.akranta.tpm.dao.sql.MOCReccommendationSql;
import com.akranta.tpm.dao.sql.MocClosureSql;
import com.akranta.tpm.dao.sql.MocPssrdtlSql;
import com.akranta.tpm.dao.sql.MocPssrmstSql;
import com.akranta.tpm.dao.sql.MocRfcBasismstSql;
import com.akranta.tpm.dao.sql.MocRfcQuestionsSql;
import com.akranta.tpm.dao.sql.MocRfcmstSql;
import com.akranta.tpm.dao.sql.MocTeamConfigSql;
import com.akranta.tpm.dao.sql.SheTlRiskassessmentmstSql;
import com.akranta.tpm.dao.sql.WhatifDtlSql;
import com.akranta.tpm.dao.sql.WhatifMstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.HazopDtl;
import com.akranta.tpm.model.HazopMst;
import com.akranta.tpm.model.MOCPssrReccommend;
import com.akranta.tpm.model.MOCReccommendation;
import com.akranta.tpm.model.MocClosure;
import com.akranta.tpm.model.MocPssrdtl;
import com.akranta.tpm.model.MocPssrmst;
import com.akranta.tpm.model.MocRfQuestions;
import com.akranta.tpm.model.MocRfcBasismst;
import com.akranta.tpm.model.MocRfcmst;
import com.akranta.tpm.model.MocTeamConfigmst;
import com.akranta.tpm.model.WhatifDtl;
import com.akranta.tpm.model.WhatifMst;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
public class MocDaoImpl implements MocDao{
	private DBActionTemplate dbActionTemplate;
	private CommonFilterDao commonFilterdao;
	private MocTeamConfigSql mocTeamConfigSql;
	public MocDaoImpl(DBActionTemplate dbActionTemplate) {
	
		this.dbActionTemplate = dbActionTemplate;	
		mocTeamConfigSql=new MocTeamConfigSql();
	}
  public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
  
// @Override
//public List<String[]> getQuestionaire(CommonFilter commonFilter) throws Exception {
//
//	String MocKey=commonFilter.getKey();
//	System.out.println("MOC Keyid"+MocKey);
//	StringBuffer sql = new StringBuffer();
//	 if(UIUtils.isValidKeyId(MocKey)){
//	sql.append(" SELECT "); 
//	sql.append("MOC_RFQ_KEYID AS \"MOC_RFQ_KEYID\",MOC_QRFC_KEYID AS \"MOC_QRFC_KEYID\",MOC_RQM_KEYID AS \"MOC_RQM_KEYID\",MOC_RFQ_RESPONSE AS \"Response\",DECODE(MOC_RFQ_RESPONSE,'Y','Y','N','N'),MOC_RQM_QUESTIONS AS \"Description\" ");  
//	sql.append(" FROM ");
//	sql.append(" MOC_TL_RFCQST,MOC_RL_RFCQSTMST"); 
//	sql.append(" WHERE "); 
//	
//
//	sql.append(" 1=1   and  MOC_QRQM_KEYID(+) = MOC_RQM_KEYID ");
//	
//	if(UIUtils.isValidKeyId(MocKey)){
//		   sql.append(" AND MOC_QRFC_KEYID(+) ='"+MocKey+"' ");
//		  }
//
//    sql.append(" ORDER BY ");
//    sql.append(" MOC_RQM_KEYID ASC ");
//    System.out.println("SQL is::::"+sql);
//	 }
//	 else{
//		 sql.append(" SELECT "); 
//			sql.append("'','',MOC_RQM_KEYID AS \"MOC_RQM_KEYID\",'','',MOC_RQM_QUESTIONS AS \"Description\" ");  
//			sql.append(" FROM ");
//			sql.append(" MOC_RL_RFCQSTMST"); 
//			sql.append(" WHERE ");
//			
//
//			sql.append(" 1=1  ");
//			
//
//		    sql.append(" ORDER BY ");
//		    sql.append(" MOC_RQM_KEYID ASC ");
//		    System.out.println("SQL is::::"+sql);
//		 
//	 }
//	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
//	return gridData;
//}
  
  @Override
  public List<String[]> getQuestionaire(CommonFilter commonFilter) throws Exception {

      String MocKey=commonFilter.getKey();
      System.out.println("MOC Keyid"+MocKey);
      StringBuffer sql = new StringBuffer();
      if(UIUtils.isValidKeyId(MocKey)){
          sql.append(" SELECT ");
          sql.append("MOC_RFQ_KEYID AS \"MOC_RFQ_KEYID\",MOC_QRFC_KEYID AS \"MOC_QRFC_KEYID\",MOC_RQM_KEYID AS \"MOC_RQM_KEYID\",MOC_RFQ_RESPONSE AS \"Response\",CASE WHEN MOC_RFQ_RESPONSE='Y' THEN 'Y' WHEN MOC_RFQ_RESPONSE='N' THEN 'N' END,MOC_RQM_QUESTIONS AS \"Description\" ");
          sql.append(" FROM ");
          sql.append(" MOC_RL_RFCQSTMST ");
          sql.append(" LEFT OUTER JOIN MOC_TL_RFCQST ON MOC_QRQM_KEYID = MOC_RQM_KEYID ");
          if(UIUtils.isValidKeyId(MocKey)){
              sql.append(" AND MOC_QRFC_KEYID ='"+MocKey+"' ");
          }
          sql.append(" WHERE 1=1 ");
          sql.append(" ORDER BY ");
          sql.append(" MOC_RQM_KEYID ASC ");
          System.out.println("SQL is::::"+sql);
      }
      else{
          sql.append(" SELECT ");
          sql.append("'','',MOC_RQM_KEYID AS \"MOC_RQM_KEYID\",'','',MOC_RQM_QUESTIONS AS \"Description\" ");
          sql.append(" FROM ");
          sql.append(" MOC_RL_RFCQSTMST");
          sql.append(" WHERE ");
          sql.append(" 1=1  ");
          sql.append(" ORDER BY ");
          sql.append(" MOC_RQM_KEYID ASC ");
          System.out.println("SQL is::::"+sql);
      }
      List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
      return gridData;
  }
  
  @Override
  public List<String[]> getPSSR(String mocKeyId) throws Exception {
      // TODO Auto-generated method stub
      System.out.println("In Dao PSSR" + mocKeyId);
      StringBuffer sql = new StringBuffer();

      if (UIUtils.isValidKeyId(mocKeyId)) {
          System.out.println("In IF");
          sql.append(" SELECT "); 
          sql.append("PSRD_KEYID AS \"PSRD_KEYID\", ");
          sql.append("PSRD_PSRM_KEYID AS \"PSRD_PSRM_KEYID\", ");
          sql.append("MOC_PCM_KEYID AS \"MOC_PCM_KEYID\", ");
          sql.append("PSRD_ROWNUM AS \"PSRD_ROWNUM\", ");
          sql.append("MOC_PCM_KEYID, ");
          sql.append("MOC_PCM_AREA AS \"Area\", ");
          sql.append("MOC_PCM_CHECKPOINT AS \"MOC_PCM_CHECKPOINT\", ");
          sql.append("PSRD_OBSERVATION AS \"PSRD_OBSERVATION\" ");
          sql.append(" FROM MOC_TL_PSSRCHKLISTMST mst ");
          sql.append(" LEFT JOIN MOC_TL_PSSRCHECKLISTdtl dtl ");
          sql.append(" ON mst.MOC_PCM_KEYID = dtl.PSRD_MOC_PCM_KEYID ");
          
          if (UIUtils.isValidKeyId(mocKeyId)) {
              sql.append(" AND dtl.PSRD_RFCM_KEYID = '" + mocKeyId + "' ");
          }

          sql.append(" ORDER BY dtl.PSRD_KEYID ");
          System.out.println("SQL is::::" + sql);
      } else {
          sql.append(" SELECT "); 
          sql.append("'' ,'','','',MOC_PCM_KEYID, MOC_PCM_AREA AS \"Area\", MOC_PCM_CHECKPOINT AS \"Checkpoints\", '' AS \"Observations\" ");  
          sql.append(" FROM MOC_TL_PSSRCHKLISTMST "); 
          sql.append(" WHERE 1=1 ");
          sql.append(" ORDER BY MOC_PCM_KEYID ASC ");
          System.out.println("SQL is::::" + sql);
      }

      List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
      return gridData;
  }
  
  
//@Override
//public List<String[]> getPSSR(String mocKeyId) throws Exception {
//	// TODO Auto-generated method stub
//	System.out.println("In Dao PSSR"+mocKeyId);
//	StringBuffer sql = new StringBuffer();
// if(UIUtils.isValidKeyId(mocKeyId)){
//		 System.out.println("In IF");
//	sql.append(" SELECT "); 
//	sql.append("PSRD_KEYID AS \"PSRD_KEYID\",PSRD_PSRM_KEYID AS \"PSRD_PSRM_KEYID\",MOC_PCM_KEYID AS \"MOC_PCM_KEYID\",PSRD_ROWNUM AS \"PSRD_ROWNUM\",MOC_PCM_KEYID,MOC_PCM_AREA AS \"Area\",MOC_PCM_CHECKPOINT AS \"MOC_PCM_CHECKPOINT\",PSRD_OBSERVATION AS \"PSRD_OBSERVATION\" ");  
//	sql.append(" FROM ");
//	sql.append(" MOC_TL_PSSRCHKLISTMST,MOC_TL_PSSRCHECKLISTdtl"); 
//	sql.append(" WHERE ");
//	sql.append(" 1=1 AND MOC_PCM_KEYID=PSRD_MOC_PCM_KEYID(+) ");
//  	
//	   if(UIUtils.isValidKeyId(mocKeyId)){
//	    //sql.append(" AND QCDC_QCLDKEYID ='"+unsafeact+"' ");
//		sql.append(" AND PSRD_RFCM_KEYID(+) ='"+mocKeyId+"' ");
//	   }
//    sql.append(" ORDER BY ");
//    sql.append(" PSRD_KEYID  ");
//    System.out.println("SQL is::::"+sql);
//	 }
//	
//	 else{
//	sql.append(" SELECT "); 
//	sql.append("'' ,'','','',MOC_PCM_KEYID,MOC_PCM_AREA AS \"Area\",MOC_PCM_CHECKPOINT AS \"Checkpoints\" ,'' AS \"Observations\" ");  
//	sql.append(" FROM ");
//	sql.append("  MOC_TL_PSSRCHKLISTMST"); 
//	sql.append(" WHERE ");
//	sql.append("  1=1 ");
//	 sql.append("order by MOC_PCM_KEYID asc ");
//    System.out.println("SQL is::::"+sql);
//	 }
//	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
//	return gridData;
//}
@Override
public List<String[]> getCRBasis(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	System.out.println("In Dao Implo");
	List<String> paramValues = new ArrayList<String>();
	List<String[]> getSUSA = null;
	try {
		
		String condParams = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	
		paramValues.add(condParams);
		paramValues.add(commonParams);
		getSUSA =dbActionTemplate.processFunctionCallsWithColHeaders("SOP_PC_SOP.GEN_FN_CRBASICS",paramValues);

		
	}catch( Exception e){
	}
	return getSUSA;
}

@Override
public String getProbablityVal(String prob) throws Exception {
	// TODO Auto-generated method stub
	String sql=null; 
	String probVal="";
	SheTlRiskassessmentmstSql sheTlRiskassessmentmstSql = new SheTlRiskassessmentmstSql();
	try{		
		sql=sheTlRiskassessmentmstSql.getProbablityValSql(prob);
		System.out.println("sql....."+sql);
		probVal=dbActionTemplate.getSingleValue(sql); 
	}catch(Exception e)
	{
		throw new Exception(e.getMessage());
	}
	return probVal;
}

@Override
public String getSeviorityVal(String sev) throws Exception {
	// TODO Auto-generated method stub
	String sql=null; 
	String sevVal="";
	SheTlRiskassessmentmstSql sheTlRiskassessmentmstSql = new SheTlRiskassessmentmstSql();
	try{		
		sql=sheTlRiskassessmentmstSql.getSeviorityValSql(sev);
		sevVal=dbActionTemplate.getSingleValue(sql); 
		CommonFunctions.debugMsg("sql....."+sql);
	}catch(Exception e)
	{
		throw new Exception(e.getMessage());
	}
	return sevVal;
}
@Override
public String getRiskLevel(String riskVal)
		throws Exception {
	// TODO Auto-generated method stub
	String sql=null; 
	String RiskLevel="";
	SheTlRiskassessmentmstSql sheTlRiskassessmentmstSql = new SheTlRiskassessmentmstSql();
	try{		
		sql=sheTlRiskassessmentmstSql.getRiskLevelSql(riskVal);
		RiskLevel=dbActionTemplate.getSingleValue(sql); 
	}catch(Exception e)
	{
		throw new Exception(e.getMessage());
	}
	return RiskLevel;
}

//--PGSQL--------------------------------------------------------------------------
//public List<String[]> getBasisofChange(String MocKeyid) throws Exception {
//    // TODO Auto-generated method stub
//    StringBuffer sql = new StringBuffer();
//    if(UIUtils.isValidKeyId(MocKeyid)){
//        sql.append(" SELECT ");
//        sql.append("MOC_BAM_KEYID AS \"MOC_BAM_KEYID\",MOC_BAM_RFCKEYID AS \"MOC_BAM_RFCKEYID\",MOCB_KEYID AS \"DescriptionID\",MOCB_NAME AS \"Description\" ");
//        sql.append(" FROM ");
//        sql.append(" MOC_TL_RFCBASIS ");
//        sql.append(" LEFT OUTER JOIN MOC_TL_MOCBASIS ON MOC_BAM_BASISID = MOCB_KEYID ");
//        if(UIUtils.isValidKeyId(MocKeyid)){
//            //sql.append(" AND QCDC_QCLDKEYID ='"+__unsafeact__+"' ");
//            sql.append(" AND MOC_BAM_RFCKEYID ='"+MocKeyid+"' ");
//        }
//        sql.append(" WHERE 1=1 ");
//        sql.append(" ORDER BY ");
//        sql.append(" MOCB_KEYID ");
//        System.out.println("SQL is::::"+sql);
//    }
//    else{
//        System.out.println("In Basis Else ::::");
//        sql.append(" SELECT ");
//        sql.append("'','',MOCB_KEYID AS \"DescriptionID\",MOCB_NAME AS \"Description\" ");
//        sql.append(" FROM ");
//        sql.append(" MOC_TL_MOCBASIS");
//        sql.append(" WHERE ");
//        sql.append(" 1=1  ");
//
//        sql.append(" ORDER BY ");
//        sql.append(" MOCB_KEYID ");
//        System.out.println("SQL is::::"+sql);
//    }
//
//    List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
//    return gridData;
//}

////ORACLE------------------------------
//@Override
//public List<String[]> getBasisofChange(String MocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//	StringBuffer sql = new StringBuffer();
//	   if(UIUtils.isValidKeyId(MocKeyid)){
//	sql.append(" SELECT "); 
//	sql.append("MOC_BAM_KEYID AS \"MOC_BAM_KEYID\",MOC_BAM_RFCKEYID AS \"MOC_BAM_RFCKEYID\",MOCB_KEYID AS \"DescriptionID\",MOCB_NAME AS \"Description\" ");  
//	sql.append(" FROM ");
//	sql.append(" MOC_TL_MOCBASIS,MOC_TL_RFCBASIS"); 
//	sql.append(" WHERE ");
//	sql.append(" 1=1   and   MOC_BAM_BASISID(+)=MOCB_KEYID");
//	 if(UIUtils.isValidKeyId(MocKeyid)){
//		    //sql.append(" AND QCDC_QCLDKEYID ='"+unsafeact+"' ");
//			sql.append(" AND MOC_BAM_RFCKEYID(+) ='"+MocKeyid+"' ");
//		   }
//
//    sql.append(" ORDER BY ");
//    sql.append(" MOCB_KEYID ");
//    System.out.println("SQL is::::"+sql);
//	   }
//	   else{
//		   System.out.println("In Basis Else ::::");
//			sql.append(" SELECT "); 
//			sql.append("'','',MOCB_KEYID AS \"DescriptionID\",MOCB_NAME AS \"Description\" ");  
//			sql.append(" FROM ");
//			sql.append(" MOC_TL_MOCBASIS"); 
//			sql.append(" WHERE ");
//			sql.append(" 1=1  ");
//			
//		    sql.append(" ORDER BY ");
//		    sql.append(" MOCB_KEYID ");
//		    System.out.println("SQL is::::"+sql);   
//	   }
//   
//	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
//	return gridData;
//}

//new---------------------------------------------
@Override
public List<String[]> getBasisofChange(String MocKeyid) throws Exception {
	// TODO Auto-generated method stub
	StringBuffer sql = new StringBuffer();
	   if(UIUtils.isValidKeyId(MocKeyid)){
		   System.out.println("In BasisOF Change IF ::::");
	sql.append(" SELECT "); 
	sql.append("rfcb.MOC_BAM_KEYID AS \"MOC_BAM_KEYID\",rfcb.MOC_BAM_RFCKEYID AS \"MOC_BAM_RFCKEYID\",mocb.MOCB_KEYID AS \"DescriptionID\",mocb.MOCB_NAME AS \"Description\" ");  
	sql.append(" FROM ");
	sql.append(" MOC_TL_MOCBASIS mocb"); 
	
	sql.append(" left join MOC_TL_RFCBASIS rfcb  on  rfcb.MOC_BAM_BASISID = mocb.MOCB_KEYID");
	
	 if(UIUtils.isValidKeyId(MocKeyid)){
		    //sql.append(" AND QCDC_QCLDKEYID ='"+unsafeact+"' ");
			sql.append(" AND rfcb.MOC_BAM_RFCKEYID ='"+MocKeyid+"' ");
		   }
	 sql.append(" WHERE 1=1 ");
    sql.append(" ORDER BY ");
    sql.append(" mocb.MOCB_KEYID ");
    System.out.println("SQL is::::"+sql);
	   }
	   else{
		   System.out.println("In Basis Else ::::");
			sql.append(" SELECT "); 
			sql.append("'','',MOCB_KEYID AS \"DescriptionID\",MOCB_NAME AS \"Description\" ");  
			sql.append(" FROM ");
			sql.append(" MOC_TL_MOCBASIS"); 
			sql.append(" WHERE ");
			sql.append(" 1=1  ");
			
		    sql.append(" ORDER BY ");
		    sql.append(" MOCB_KEYID ");
		    System.out.println("SQL is::::"+sql);   
	   }
   
	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
	return gridData;
}
@Override
public List<String[]> getMOCTeam(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	StringBuffer sql = new StringBuffer();
	sql.append(" SELECT "); 
	sql.append("MOC_RCM_KEYID AS \"MOC_RCM_KEYID\",MOC_RCM_RFCKEYID AS \"MOC_RCM_RFCKEYID\",ROLM_KEYID AS \"ROLM_KEYID\",ROLM_NAME AS \"ROLM_NAME\",MOC_RCM_INITIALAPPROVAL AS \"MOC_RCM_INITIALAPPROVAL\",MOC_RCM_HAZOPAPPROVAL AS \"MOC_RCM_HAZOPAPPROVAL\",MOC_RCM_FINALAPPROVAL AS \"MOC_RCM_FINALAPPROVAL\",EMPM_NAME AS \"EMPM_NAME\",MOC_RCM_EMPID AS \"MOC_RCM_EMPID\" ");  
	sql.append(" FROM ");
	sql.append(" MOC_TL_ROLEMST,MOC_TL_ROLECONFIGMST,GEN_TL_EMPLOYEEMST"); 
	sql.append(" WHERE ");
	sql.append(" 1=1 AND ROLM_KEYID=MOC_RCM_ROLEID(+) AND EMPM_KEYID(+)=MOC_RCM_EMPID and ROLM_KEYID='Y'");

    sql.append(" ORDER BY ");
    sql.append(" ROLM_KEYID ");
    System.out.println("SQL is::::"+sql);
   
	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
	return gridData;
}

@Override
public List<String[]> getMOCClosure(CommonFilter commonFilter) throws Exception {

    String MocKey = commonFilter.getKey();
    System.out.println("MOC Keyid" + MocKey);
    getPssrCount(MocKey);
    StringBuffer sql = new StringBuffer();

    if (UIUtils.isValidKeyId(MocKey)) {

        sql.append(" SELECT ");
        sql.append(" mocl.MOCL_KEYID AS \"MOCL_KEYID\", ");
        sql.append(" mocl.MOCL_RFC_KEYID AS \"MOCL_RFC_KEYID\", ");
        sql.append(" clq.MOC_CLQ_KEYID AS \"MOC_CLQ_KEYID\", ");
        sql.append(" clq.MOC_CLQ_QUESTIONS AS \"Description\", ");
        sql.append(" CASE WHEN mocl.MOCL_RESPONSEY = 'Y' THEN 'Y' ELSE '-' END, ");
        sql.append(" CASE WHEN mocl.MOCL_RESPONSEN = 'Y' THEN 'N' ELSE '-' END ");

        sql.append(" FROM ");
        sql.append(" MOC_TL_CLOSUREQSTMST clq ");
        sql.append(" LEFT JOIN MOC_TL_CLOSUREMASTER mocl ");
        sql.append(" ON mocl.MOCL_CLQ_KEYID = clq.MOC_CLQ_KEYID ");
        sql.append(" AND mocl.MOCL_RFC_KEYID = '" + MocKey + "' ");

        sql.append(" ORDER BY ");
        sql.append(" clq.MOC_CLQ_KEYID ASC ");

        System.out.println("SQL is::::" + sql);

    } else {

        sql.append(" SELECT ");
        sql.append(" '' AS \"MOCL_KEYID\", ");
        sql.append(" '' AS \"MOCL_RFC_KEYID\", ");
        sql.append(" clq.MOC_CLQ_KEYID AS \"MOC_CLQ_KEYID\", ");
        sql.append(" clq.MOC_CLQ_QUESTIONS AS \"Description\", ");
        sql.append(" '' AS \"MOCL_RESPONSEY\", ");
        sql.append(" '' AS \"MOCL_RESPONSEN\" ");

        sql.append(" FROM ");
        sql.append(" MOC_TL_CLOSUREQSTMST clq ");

        sql.append(" ORDER BY ");
        sql.append(" clq.MOC_CLQ_KEYID ASC ");

        System.out.println("SQL is::::" + sql);
    }

    List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
    return gridData;
}


//@Override
//public List<String[]> getMOCClosure(CommonFilter commonFilter) throws Exception {
//
//	String MocKey=commonFilter.getKey();
//	System.out.println("MOC Keyid"+MocKey);
//	getPssrCount(MocKey); 
//	StringBuffer sql = new StringBuffer();
//	 if(UIUtils.isValidKeyId(MocKey)){
//	sql.append(" SELECT "); 
//	sql.append("MOCL_KEYID AS \"MOCL_KEYID\",MOCL_RFC_KEYID AS \"MOCL_RFC_KEYID\",MOC_CLQ_KEYID AS \"MOC_CLQ_KEYID\",MOC_CLQ_QUESTIONS AS \"Description\" ,DECODE(MOCL_RESPONSEY,'Y','Y','N','-'),DECODE(MOCL_RESPONSEN,'Y','N','N','-')");  
//	sql.append(" FROM ");
//	sql.append(" MOC_TL_CLOSUREMASTER,MOC_TL_CLOSUREQSTMST"); 
//	sql.append(" WHERE ");
//	
//
//	sql.append(" 1=1   and  MOC_CLQ_KEYID = MOCL_CLQ_KEYID(+)");
//	
//	if(UIUtils.isValidKeyId(MocKey)){
//		   sql.append(" AND MOCL_RFC_KEYID(+) ='"+MocKey+"' ");
//		  }
//
//    sql.append(" ORDER BY ");
//    sql.append(" MOC_CLQ_KEYID ASC ");
//    System.out.println("SQL is::::"+sql);
//	 }
//	 else{
//		 sql.append(" SELECT "); 
//			sql.append("'','',MOC_CLQ_KEYID AS \"MOC_CLQ_KEYID\",MOC_CLQ_QUESTIONS AS \"Description\" ,'',''");  
//			sql.append(" FROM ");
//			sql.append(" MOC_TL_CLOSUREQSTMST"); 
//			sql.append(" WHERE ");
//			
//
//			sql.append(" 1=1  ");
//			
//
//		    sql.append(" ORDER BY ");
//		    sql.append(" MOC_CLQ_KEYID ASC ");
//		    System.out.println("SQL is::::"+sql);
//		 
//	 }
//	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
//	return gridData;
//}
@Override
public List<String[]> getMocRelatedData(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	try {
		System.out.println("Inside Dao");
		String ProType="";
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		String teamLead=commonFilter.getTeamId();
		System.out.println("teamLead"+teamLead);
		String startDate=commonFilter.getAftFromDt();
		System.out.println("startDate"+startDate);
		String endDate=commonFilter.getAftToDt();
		System.out.println("endDate"+endDate);
		String type=commonFilter.getType();
		System.out.println("The Type is:::"+type);
		String Status=commonFilter.getStatus();
		
		
		if(teamLead!=null){
			condParms+="TEAMLEAD="+teamLead+";";
		}
		
		if(type!=null){
			condParms+="PROTYPE="+type+";";
		}
		
		if(Status!=null){
			condParms+="PROSTATUS="+Status+";";
		}
		if(startDate!=null && endDate!=null){
			
			condParms+="STARTDATE="+startDate+";";
			condParms+="ENDDATE="+endDate+";";
		}
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("MOC_FN_MOCVIEW",paramValues);
	System.out.println("DataList"+dataList);
		if (commonFilter.getViewClick() == 'Y') {
			String totalCnt = paramValues.get(0);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if (isInteger) {
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;
	} catch (Exception e) {
		throw new Exception(e.getMessage());
	}
}


public Workbook getMOCModificationExcel(JSONObject colmodel, String rptformat,
		CommonFilter commonFilter) throws Exception {
	  ResultSet rs = null;
	// TODO Auto-generated method stub
	 try{
			
		 rs =   getMOCModificationResultSet(commonFilter);
		 
		 ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptformat,2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
}

private ResultSet getMOCModificationResultSet(CommonFilter commonFilter) throws Exception {
	List<String> paramValues = getFilterParamValues1(commonFilter);
	//return dbActionTemplate.dbFunctionCall("MOC_FN_MOCVIEW", paramValues);
	return dbActionTemplate.NewdbFunctionCall2("MOC_FN_MOCVIEW", paramValues);
}

private List<String> getFilterParamValues1(CommonFilter commonFilter) {
	List <String>  paramvalues = new ArrayList<String>();
	String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
	paramvalues.add(condParms);
	paramvalues.add(commonParams);
	return paramvalues;
}


@Override
public List<MocRfcBasismst> createBasis(List<MocRfcBasismst> fillValuesMOCQstns, String createdBy, MocRfcmst mocRfcmst)
		throws Exception {
	System.out.println("In Dao Implof Desc");
	MocRfcBasismstSql mocRfcBasismstSql=new MocRfcBasismstSql();
	try
	{
		     List<String> sqls = new ArrayList<String>();
		     List <MocRfcBasismst> methodslist = fillValuesMOCQstns;
		     

		     String detlKeyid="";
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),MocRfcBasismstSql.TBL_MOC_TL_RFCBASIS,14,"RFCB","","");
			 System.out.println("sequenceNumber in IF"+sequenceNumber);
			 for(MocRfcBasismst CreatedescLink:methodslist)
			 {
				    System.out.println("Inside the for");
					String seqNo=sequenceNumber.getSequnceNumber();
					System.out.println("The seqNo::::"+seqNo);
					CreatedescLink.setRfcbKeyid(seqNo);
                   CreatedescLink.setRfcbCreatedby(createdBy);
                    detlKeyid= mocRfcmst.getRfcmKeyid();
                //   fodKeyid=CreatedescLink.
                   System.out.println("The detlKeyid"+detlKeyid);
                   CreatedescLink.setRfcbrfcid(detlKeyid);
					sqls.add(MocRfcBasismstSql.getInsertSql(mocRfcBasismstSql.getrfcb_DbFields(),CreatedescLink.getSaveArray()));
			        System.out.println("The sqls Query:"+sqls);
			 }
			 dbActionTemplate.executeStatements(sqls);
			// popSqlsForDescData(detlKeyid); 
	}
	catch(BusinessApplicationExceptions e)
	{
		CommonFunctions.debugMsg("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return fillValuesMOCQstns;
}
@Override
public MocRfcmst createMOC(MocRfcmst mocRfcmst,MocRfcBasismst mocRfcBasismst,MocTeamConfigmst mocTeamConfigmst) throws Exception {
	// TODO Auto-generated method stub
	System.out.println("In Dao Impl");
	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	MocRfcmstSql mocRfcmstSql = new MocRfcmstSql(); // contains dbtable,field names, Field types and related sqls  of master table

try{

	mocRfcmst.setRfcmKeyid(dbActionTemplate.getSequenceNumber(MocRfcmstSql.TBL_MOC_TL_RFCMST,14,"MOC","","")); // set the sequnce number 
sqls.add(MocRfcmstSql.getInsertSql(mocRfcmstSql.getrfcm_DbFields(), mocRfcmst.getSaveArray())); // add insert sql for master table

//   
System.out.println("after dtl "+sqls.toString());

dbActionTemplate.executeStatements(sqls); // execute the block of sqls

}		
catch(Exception e)
			{
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
	return mocRfcmst;
}

@Override
public List<MocTeamConfigmst> createTeam(List<MocTeamConfigmst> team, String createdBy,
		MocRfcmst mocRfcmst) throws Exception {
	// TODO Auto-generated method stub
	
	MocTeamConfigSql mocTeamConfigSql=new MocTeamConfigSql(); // contains dbtable,field names, Field types and related sqls  of master table
	try
	{
		
	//	List<MocTeamConfigmst> TeamList=team;
	/*	String seqIdentfr=""; 
		for(MocTeamConfigmst getElementId :TeamList){
			
		 	seqIdentfr = MocTeamConfigSql.TBL_MOC_TL_ROLECONFIGMST;

			
		}*/
		     List<String> sqls = new ArrayList<String>();
		     List <MocTeamConfigmst> methodslist = team;
		     System.out.println("before for loop"+methodslist);
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),MocTeamConfigSql.TBL_MOC_TL_ROLECONFIGMST,14,"MOCT", null,null);
		     String detlKeyid="";
		   //  GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource(),"",14,"MOCT", "YYMM","Y");
			System.out.println("the sequenceNumber"+sequenceNumber);
		     for(MocTeamConfigmst abnormalityLink : methodslist)
			 {
					String seqNo=sequenceNumber.getSequnceNumber();
					System.out.println("The seqNo::::"+seqNo);
					abnormalityLink.setMctcKeyid(seqNo);
					abnormalityLink.setMctcCreatedby(createdBy);
					detlKeyid= mocRfcmst.getRfcmKeyid();
					System.out.println("detlKeyid::::"+detlKeyid);
					abnormalityLink.setMctcmasterid(detlKeyid);
					//  popSqlsForTeamDeleteData(detlKeyid);
					popSqlsForMOCTeamDeleteData(detlKeyid);

                    sqls.add(MocTeamConfigSql.getInsertSql(mocTeamConfigSql.getmctc_DbFields(), abnormalityLink.getSaveArray()));
                  //  MultipleactionPlanEntry(list,"I");
                    System.out.println("The sql tEAM NOs Query:"+sqls);
			 }
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		CommonFunctions.debugMsg("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return team;
}
private void popSqlsForMOCTeamDeleteData(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_ROLECONFIGMST", "MOC_RCM_KEYID", "MOC_RCM_RFCKEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" delete from MOC_TL_ROLECONFIGMST  WHERE MOC_RCM_RFCKEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}

/*private String popSqlsForTeamDeleteData(String detlKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String Momdetailid=dbActionTemplate.getSingleValue("MOC_TL_ROLECONFIGMST", "MOC_RCM_KEYID", "MOC_RCM_RFCKEYID",detlKeyid);
	System.out.println("KKKKKKK:::"+Momdetailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" delete from MOC_TL_ROLECONFIGMST  WHERE MOC_RCM_RFCKEYID ='"+detlKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
	return sql;	
}*/
@Override
public MocRfcmst update(MocRfcmst mocRfcmst,String nodeId,String Nature ,String Desc,String Detail, String Dmt,String Jh,String Initiator,String MOCTitle,String MocType) throws Exception {
	List<String> sqls = new ArrayList<String>();
	try
	{
		System.out.println("IN Update ");
		  StringBuilder sql=new StringBuilder();
		  
	 sql.append("UPDATE MOC_TL_RFCMST SET MOC_RFC_JHID='"+Jh+"', MOC_RFC_DMTID='"+Dmt+"' ,MOC_RFC_TITLE='"+MOCTitle+"' ,MOC_RFC_EMPMKEYID='"+Initiator+"', MOC_RFC_NATURE='"+Nature+"',MOC_RFC_TYPE='"+MocType+"',MOC_RFC_DETAIL='"+Detail+"',MOC_RFC_DESCRIPTION='"+Desc+"' WHERE MOC_RFC_KEYID='"+nodeId+"' ");
System.out.println("Data Updated "+sql);
	 dbActionTemplate.executeStatement(sql.toString());	
	}
	catch(Exception e)
	{
		e.printStackTrace();
		throw new Exception(e.getMessage());
	}
	/*MocRfcmstSql mocRfcmstSql = new MocRfcmstSql();
	try {

		sqls.add(MocRfcmstSql.getUpdateSql(mocRfcmstSql.getrfcm_DbFields(), mocRfcmst.getSaveArray()));
		
		dbActionTemplate.executeStatements(sqls);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new Exception(e.getMessage());
	}*/
	
	return mocRfcmst;
}
@Override
public List<MocRfQuestions> createQuestionsNew(List<MocRfQuestions> fillValuesSessionEmployee,String MocKeyid) throws Exception {
	// TODO Auto-generated method stub
	MocRfcQuestionsSql mocRfcQuestionsSql = new MocRfcQuestionsSql(); // contains dbtable,field names, Field types and related sqls  of master table
	try
	{
		     List<String> sqls = new ArrayList<String>();
		     List <MocRfQuestions> methodslist = fillValuesSessionEmployee;
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),MocRfcQuestionsSql.TBL_MOC_TL_RFCQST,14,"MOQ", null,null);
			 System.out.println("sequenceNumber in IF"+sequenceNumber);
			 for(MocRfQuestions empSessionLink:methodslist)
			 {
				    System.out.println("The Role Keyid");
					String seqNo=sequenceNumber.getSequnceNumber();
					System.out.println("The seqNo::::"+seqNo);
					empSessionLink.setRfcqKeyid(seqNo);
				
				String Response=	empSessionLink.getRfcqresponse();
				System.out.println("Response::::"+Response);
					popSqlsForQuestionnaireStatusUpdate(MocKeyid);
					sqls.add(MocRfcQuestionsSql.getInsertSql(mocRfcQuestionsSql.getrfcq_DbFields(), empSessionLink.getSaveArray()));
			        System.out.println("The sqls Query:"+sqls);
			 }
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		System.out.println("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return fillValuesSessionEmployee;
}


//@Override
//public List<MocRfQuestions> createQuestionsNew(List<MocRfQuestions> fillValuesSessionEmployee, String MocKeyid) throws Exception {
//    MocRfcQuestionsSql mocRfcQuestionsSql = new MocRfcQuestionsSql();
//    try {
//        List<String> sqls = new ArrayList<String>();
//        List<MocRfQuestions> methodslist = fillValuesSessionEmployee;
//
//        // ✅ FIX 1: Move outside loop — only needs to run once
//        popSqlsForQuestionnaireStatusUpdate(MocKeyid);
//
//        for (MocRfQuestions empSessionLink : methodslist) {
//            System.out.println("The Role Keyid");
//
//            // ✅ FIX 2: Move inside loop — new sequence needed per record
//            GenSequenceNumber sequenceNumber = new GenSequenceNumber(
//                this.dbActionTemplate.getDataSource().getConnection(),
//                MocRfcQuestionsSql.TBL_MOC_TL_RFCQST, 14, "MOQ", null, null
//            );
//
//            String seqNo = sequenceNumber.getSequnceNumber();
//            System.out.println("The seqNo::::" + seqNo);
//            empSessionLink.setRfcqKeyid(seqNo);
//
//            String Response = empSessionLink.getRfcqresponse();
//            System.out.println("Response::::" + Response);
//
//            sqls.add(MocRfcQuestionsSql.getInsertSql(mocRfcQuestionsSql.getrfcq_DbFields(), empSessionLink.getSaveArray()));
//            System.out.println("The sqls Query:" + sqls);
//        }
//
//        dbActionTemplate.executeStatements(sqls);
//    } catch (BusinessApplicationExceptions e) {
//        System.out.println("Business Application   :" + e.getMessage());
//        throw new BusinessApplicationExceptions(e.getMessage());
//    }
//    return fillValuesSessionEmployee;
//}
@Override
public List<MocRfcBasismst> createBasis(List<MocRfcBasismst> fillValuesMOCBasis, String mocKeyid) throws Exception {
	// TODO Auto-generated method stub
	System.out.println("In Dao Implof Desc");
	MocRfcBasismstSql mocRfcBasismstSql=new MocRfcBasismstSql();
	try
	{
		     List<String> sqls = new ArrayList<String>();
		     List <MocRfcBasismst> methodslist = fillValuesMOCBasis;
		     

		     String detlKeyid="";
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),MocRfcBasismstSql.TBL_MOC_TL_RFCBASIS,14,"RFCB","","");
			 System.out.println("sequenceNumber in IF"+sequenceNumber);
			 for(MocRfcBasismst CreatedescLink:methodslist)
			 {
				    System.out.println("Inside the for");
					String seqNo=sequenceNumber.getSequnceNumber();
					System.out.println("The seqNo::::"+seqNo);
					CreatedescLink.setRfcbKeyid(seqNo);
                  /* CreatedescLink.setRfcbCreatedby(createdBy);
                    detlKeyid= mocRfcmst.getRfcmKeyid();*/
                //   fodKeyid=CreatedescLink.
                   System.out.println("The mocKeyid"+mocKeyid);
                   CreatedescLink.setRfcbrfcid(mocKeyid);
                   popSqlsForBasisDeleteData(mocKeyid);
                   popSqlsForRFCStatusUpdate(mocKeyid);
					sqls.add(MocRfcBasismstSql.getInsertSql(mocRfcBasismstSql.getrfcb_DbFields(),CreatedescLink.getSaveArray()));
			        System.out.println("The sqls Query:"+sqls);
			 }
			 dbActionTemplate.executeStatements(sqls);
			// popSqlsForDescData(detlKeyid); 
	}
	catch(BusinessApplicationExceptions e)
	{
		CommonFunctions.debugMsg("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return fillValuesMOCBasis;
}
private void popSqlsForBasisDeleteData(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCBASIS", "MOC_BAM_KEYID", "MOC_BAM_RFCKEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" delete from MOC_TL_RFCBASIS  WHERE MOC_BAM_RFCKEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}
private void popSqlsForPssrChecklistDeleteData(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_PSSRCHECKLISTMST", "PSRM_KEYID", "PSRM_RFCKEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" delete from MOC_TL_PSSRCHECKLISTMST  WHERE PSRM_RFCKEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}
private void popSqlsForRFCStatusUpdate(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCMST", "MOC_RFC_KEYID", "MOC_RFC_KEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" Update MOC_TL_RFCMST set MOC_RFC_STATUS='CRFC' WHERE MOC_RFC_KEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}
private void popSqlsForQuestionnaireStatusUpdate(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCMST", "MOC_RFC_KEYID", "MOC_RFC_KEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" Update MOC_TL_RFCMST set MOC_RFC_STATUS='RFCQ' WHERE MOC_RFC_KEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}


private void popSqlsForInitialApprovalStatusUpdate(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCMST", "MOC_RFC_KEYID", "MOC_RFC_KEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" Update MOC_TL_RFCMST set MOC_RFC_STATUS='RFIA' WHERE MOC_RFC_KEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}
private void popSqlsForWhatIfStatusUpdate(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCMST", "MOC_RFC_KEYID", "MOC_RFC_KEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" Update MOC_TL_RFCMST set MOC_RFC_STATUS='WTIF' WHERE MOC_RFC_KEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}
private void popSqlsForHazopStatusUpdate(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCMST", "MOC_RFC_KEYID", "MOC_RFC_KEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" Update MOC_TL_RFCMST set MOC_RFC_STATUS='HZOP' WHERE MOC_RFC_KEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}
private void popSqlsForHazopApprovalStatusUpdate(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCMST", "MOC_RFC_KEYID", "MOC_RFC_KEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" Update MOC_TL_RFCMST set MOC_RFC_STATUS='HZAP' WHERE MOC_RFC_KEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}
private void popSqlsForPssrChkStatusUpdate(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCMST", "MOC_RFC_KEYID", "MOC_RFC_KEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" Update MOC_TL_RFCMST set MOC_RFC_STATUS='PSCK' WHERE MOC_RFC_KEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}
private void popSqlsForPssrRcmndStatusUpdate(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCMST", "MOC_RFC_KEYID", "MOC_RFC_KEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" Update MOC_TL_RFCMST set MOC_RFC_STATUS='PSRC' WHERE MOC_RFC_KEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}
private void popSqlsForClosureStatusUpdate(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCMST", "MOC_RFC_KEYID", "MOC_RFC_KEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" Update MOC_TL_RFCMST set MOC_RFC_STATUS='MOCL' WHERE MOC_RFC_KEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}
private void popSqlsForFinalApprovalStatusUpdate(String mocKeyid) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCMST", "MOC_RFC_KEYID", "MOC_RFC_KEYID",mocKeyid);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" Update MOC_TL_RFCMST set MOC_RFC_STATUS='MOFA' WHERE MOC_RFC_KEYID ='"+mocKeyid+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
}

@Override
public List<String[]> getMOCTeamSuccess(CommonFilter commonFilter, String mocKeyId) throws Exception {
	// TODO Auto-generated method stub
	StringBuffer sql = new StringBuffer();
	 if(UIUtils.isValidKeyId(mocKeyId)){
		 System.out.println("In IF");
	sql.append(" SELECT "); 
	sql.append(" mrcm.MOC_RCM_KEYID AS \"MOC_RCM_KEYID\", mrcm.MOC_RCM_RFCKEYID AS \"MOC_RCM_RFCKEYID\",rolm.ROLM_KEYID AS \"ROLM_KEYID\",rolm.ROLM_NAME AS \"ROLM_NAME\",rolm.ROLM_GRPAPPROVAL AS \"ROLM_GRPAPPROVAL\",mrcm.MOC_RCM_INITIALAPPROVAL AS \"MOC_RCM_INITIALAPPROVAL\",mrcm.MOC_RCM_HAZOPAPPROVAL AS \"MOC_RCM_HAZOPAPPROVAL\",mrcm.MOC_RCM_FINALAPPROVAL AS \"MOC_RCM_FINALAPPROVAL\",empm.EMPM_NAME AS \"EMPM_NAME\",mrcm.MOC_RCM_EMPID AS \"MOC_RCM_EMPID\" ");  
	sql.append(" FROM ");
	sql.append(" MOC_TL_ROLEMST rolm"); 
	sql.append(" LEFT JOIN MOC_TL_ROLECONFIGMST mrcm ON rolm.ROLM_KEYID = mrcm.MOC_RCM_ROLEID"); 
	sql.append(" AND ");
//	sql.append(" LEFT JOIN MOC_TL_ROLECONFIGMST mrcm ON rolm.ROLM_KEYID = mrcm.MOC_RCM_ROLEID"); 
//	sql.append(" 1=1 AND ROLM_KEYID=MOC_RCM_ROLEID(+) AND EMPM_KEYID(+)=MOC_RCM_EMPID");
  	
	   if(UIUtils.isValidKeyId(mocKeyId)){
	    //sql.append(" AND QCDC_QCLDKEYID ='"+unsafeact+"' ");
		sql.append(" mrcm.MOC_RCM_RFCKEYID ='"+mocKeyId+"' ");
		sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST empm ON empm.EMPM_KEYID = mrcm.MOC_RCM_EMPID");
	   }
	   sql.append(" WHERE ");
	   sql.append(" rolm.ROLM_ACTIVE = 'Y' ");
	   
    sql.append(" ORDER BY ");
    sql.append(" rolm.ROLM_KEYID ");
    System.out.println("SQL is::::"+sql);
	 }
	 else{
		 System.out.println("In else");
		 sql.append(" SELECT "); 
			sql.append("'' AS \"MOC_RCM_KEYID\",'' AS \"MOC_RCM_RFCKEYID\",ROLM_KEYID AS \"ROLM_KEYID\",ROLM_NAME AS \"ROLM_NAME\",ROLM_GRPAPPROVAL AS \"ROLM_GRPAPPROVAL\",'','', '','','' ");  
			sql.append(" FROM ");
			sql.append(" MOC_TL_ROLEMST"); 
			sql.append(" WHERE ");
			sql.append(" 1=1 AND  ROLM_ACTIVE='Y' ");
			sql.append(" ORDER BY ");
		    sql.append(" ROLM_KEYID ");
		    System.out.println("SQL is::::"+sql);
		  	 
	 }
	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
	return gridData;
}

@Override
public MocTeamConfigmst getInitialApproval(MocTeamConfigmst mocTeamConfigmst, MocTeamConfigmst existMocTeamConfigmst,
		String keyid, String empId, String status, String date, String remarks,String MocKeyid) throws Exception {
	// TODO Auto-generated method stub
StringBuilder sql=new StringBuilder();
popSqlsForInitialApprovalStatusUpdate(MocKeyid);
if(status.equals("E")){
	
	
sql.append(" UPDATE MOC_TL_ROLECONFIGMST SET MOC_RCM_IABY='"+empId+"', MOC_RCM_IAREMARKS='"+remarks+"',MOC_RCM_IASTATUS='"+status+"',MOC_RCM_IADATE='"+date+"',MOC_RCM_IAYN='Y',MOC_RCM_IARWKFLAG='Y',MOC_RCM_IARWKDATE='"+date+"',MOC_RCM_IARWKREMARKS='"+remarks+"' WHERE MOC_RCM_KEYID='"+keyid+"' ");
   System.out.println("RoleConfig"+sql);
   
}

else{
	sql.append(" UPDATE MOC_TL_ROLECONFIGMST SET MOC_RCM_IABY='"+empId+"', MOC_RCM_IAREMARKS='"+remarks+"',MOC_RCM_IASTATUS='"+status+"',MOC_RCM_IADATE='"+date+"',MOC_RCM_IAYN='Y' WHERE MOC_RCM_KEYID='"+keyid+"' ");
	   System.out.println("RoleCofig C:::"+sql);	
}
dbActionTemplate.executeStatement(sql.toString());
	return mocTeamConfigmst;
	
}
//Oracle
//@Override
//public List<String[]> getApprovalList(String mocKeyid) throws Exception {
//	StringBuffer menuSql  = new StringBuffer();
//	//menuSql.append(" SELECT * FROM ( " ); 
////	menuSql.append(" SELECT '' AS MOC_RCM_KEYID, 'MOC_RCM_KEYI', 'Empid','Name' ,'BadgeId','Badge','UnsafeActId','Unsafeact', 'Description', 'Remarks','Image' " );
////	menuSql.append(" , 0 AS DATAORDER FROM DUAL " );
// //   menuSql.append(" 		UNION " );
//	 if(UIUtils.isValidKeyId(mocKeyid)){
//	menuSql.append(" SELECT MOC_RCM_KEYID, MOC_RCM_RFCKEYID,ROLM_KEYID,ROLM_NAME,MOC_RCM_GROUPNUM,EMPM_NAME,EMPM_KEYID,MOC_RCM_IASTATUS,TO_CHAR(MOC_RCM_IADATE,'DD-MON-YYYY'),MOC_RCM_IAREMARKS,''");
//	 menuSql.append(" FROM MOC_TL_ROLECONFIGMST ,GEN_TL_EMPLOYEEMST, MOC_TL_ROLEMST" );
//	menuSql.append(" WHERE MOC_RCM_EMPID=EMPM_KEYID(+) AND MOC_RCM_ROLEID(+)=ROLM_KEYID AND MOC_RCM_INITIALAPPROVAL='Y'" );		
//
//	if(UIUtils.isValidKeyId(mocKeyid)){
//		   menuSql.append(" AND MOC_RCM_RFCKEYID ='"+mocKeyid+"' ");
//		  }
//	 }
//	else{
//		System.out.println("In Else Approval");
//		
//		menuSql.append(" SELECT '', '',ROLM_KEYID,ROLM_NAME,'','','','','','',''");
//		 menuSql.append(" FROM MOC_TL_ROLEMST" );
//		menuSql.append(" WHERE 1=1" );		
//
//	 }
//
//		System.out.println("LossSql  :"+menuSql.toString());
//		List<String[]> getPillarRelMenu = dbActionTemplate.getDataList(menuSql.toString());
//		return getPillarRelMenu;
//}

@Override
public List<String[]> getApprovalList(String mocKeyid) throws Exception {
    StringBuffer menuSql  = new StringBuffer();

    if(UIUtils.isValidKeyId(mocKeyid)){
        menuSql.append(" SELECT mrcm.MOC_RCM_KEYID, mrcm.MOC_RCM_RFCKEYID, rm.ROLM_KEYID, rm.ROLM_NAME, ");
        menuSql.append(" mrcm.MOC_RCM_GROUPNUM, emp.EMPM_NAME, emp.EMPM_KEYID, mrcm.MOC_RCM_IASTATUS, ");
        menuSql.append(" TO_CHAR(mrcm.MOC_RCM_IADATE,'DD-MON-YYYY'), mrcm.MOC_RCM_IAREMARKS, '' ");

        menuSql.append(" FROM MOC_TL_ROLECONFIGMST mrcm ");
        menuSql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST emp ");
        menuSql.append(" ON mrcm.MOC_RCM_EMPID = emp.EMPM_KEYID ");

        menuSql.append(" LEFT JOIN MOC_TL_ROLEMST rm ");
        menuSql.append(" ON mrcm.MOC_RCM_ROLEID = rm.ROLM_KEYID ");

        menuSql.append(" WHERE mrcm.MOC_RCM_INITIALAPPROVAL = 'Y' ");

        if(UIUtils.isValidKeyId(mocKeyid)){
            menuSql.append(" AND mrcm.MOC_RCM_RFCKEYID = '"+mocKeyid+"' ");
        }
    }
    else{
        System.out.println("In Else Approval");

        menuSql.append(" SELECT '', '', ROLM_KEYID, ROLM_NAME, '', '', '', '', '', '', '' ");
        menuSql.append(" FROM MOC_TL_ROLEMST ");
        menuSql.append(" WHERE 1=1 ");
    }

    System.out.println("LossSql  :"+menuSql.toString());
    List<String[]> getPillarRelMenu = dbActionTemplate.getDataList(menuSql.toString());
    return getPillarRelMenu;
}

@Override
public List<String[]> getHazopApprovalList(String mocKeyid) throws Exception {

    StringBuffer menuSql  = new StringBuffer();

    if(UIUtils.isValidKeyId(mocKeyid)){
        menuSql.append(" SELECT M.MOC_RCM_KEYID, M.MOC_RCM_RFCKEYID, R.ROLM_KEYID, R.ROLM_NAME, ");
        menuSql.append(" M.MOC_RCM_GROUPNUM, E.EMPM_NAME, E.EMPM_KEYID, M.MOC_RCM_HZSTATUS, ");
        menuSql.append(" TO_CHAR(M.MOC_RCM_HZDATE,'DD-Mon-YYYY'), M.MOC_RCM_HZREMARKS, '' ");
        menuSql.append(" FROM MOC_TL_ROLECONFIGMST M ");
        menuSql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST E ON M.MOC_RCM_EMPID = E.EMPM_KEYID ");
        menuSql.append(" LEFT JOIN MOC_TL_ROLEMST R ON M.MOC_RCM_ROLEID = R.ROLM_KEYID ");
        menuSql.append(" WHERE M.MOC_RCM_HAZOPAPPROVAL = 'Y' ");

        if(UIUtils.isValidKeyId(mocKeyid)){
            menuSql.append(" AND M.MOC_RCM_RFCKEYID = '"+mocKeyid+"' ");
        }
    }
    else{
        System.out.println("In Else Approval");
        
        menuSql.append(" SELECT '', '', R.ROLM_KEYID, R.ROLM_NAME, '', '', '', '', '', '', '' ");
        menuSql.append(" FROM MOC_TL_ROLEMST R ");
        menuSql.append(" WHERE 1=1 ");        
    }

    System.out.println("LossSql  :"+menuSql.toString());
    List<String[]> getPillarRelMenu = dbActionTemplate.getDataList(menuSql.toString());
    return getPillarRelMenu;    
}


//oracle
//@Override
//public List<String[]> getHazopApprovalList(String mocKeyid) throws Exception {
//
//	StringBuffer menuSql  = new StringBuffer();
//
//	 if(UIUtils.isValidKeyId(mocKeyid)){
//	menuSql.append(" SELECT MOC_RCM_KEYID, MOC_RCM_RFCKEYID,ROLM_KEYID,ROLM_NAME,MOC_RCM_GROUPNUM,EMPM_NAME,EMPM_KEYID,MOC_RCM_HZSTATUS,TO_CHAR(MOC_RCM_HZDATE,'DD-MON-YYYY'),MOC_RCM_HZREMARKS,''");
//	 menuSql.append(" FROM MOC_TL_ROLECONFIGMST ,GEN_TL_EMPLOYEEMST, MOC_TL_ROLEMST" );
//	menuSql.append(" WHERE MOC_RCM_EMPID=EMPM_KEYID(+) AND MOC_RCM_ROLEID(+)=ROLM_KEYID AND MOC_RCM_HAZOPAPPROVAL='Y'" );		
//
//	if(UIUtils.isValidKeyId(mocKeyid)){
//		   menuSql.append(" AND MOC_RCM_RFCKEYID ='"+mocKeyid+"' ");
//		  }
//	 }
//	else{
//		System.out.println("In Else Approval");
//		
//		menuSql.append(" SELECT '', '',ROLM_KEYID,ROLM_NAME,'','','','','','',''");
//		 menuSql.append(" FROM MOC_TL_ROLEMST" );
//		menuSql.append(" WHERE 1=1" );		
//
//	 }
//
//		System.out.println("LossSql  :"+menuSql.toString());
//		List<String[]> getPillarRelMenu = dbActionTemplate.getDataList(menuSql.toString());
//		return getPillarRelMenu;	
//}


//postgrese query

@Override
public List<String[]> getFinalApprovalList(String mocKeyid) throws Exception {

  StringBuffer menuSql = new StringBuffer();

  if (UIUtils.isValidKeyId(mocKeyid)) {

      menuSql.append(" SELECT ");
      menuSql.append(" rcfm.MOC_RCM_KEYID, ");
      menuSql.append(" rcfm.MOC_RCM_RFCKEYID, ");
      menuSql.append(" mcro.ROLM_KEYID, ");
      menuSql.append(" mcro.ROLM_NAME, ");
      menuSql.append(" rcfm.MOC_RCM_GROUPNUM, ");
      menuSql.append(" empm.EMPM_NAME, ");
      menuSql.append(" empm.EMPM_KEYID, ");
      menuSql.append(" rcfm.MOC_RCM_FASTATUS, ");
      menuSql.append(" TO_CHAR(rcfm.MOC_RCM_FADATE, 'DD-MON-YYYY'), ");
      menuSql.append(" rcfm.MOC_RCM_FAREMARKS, ");
      menuSql.append(" '' AS \"BLANK\" ");

      menuSql.append(" FROM ");
      menuSql.append(" MOC_TL_ROLEMST mcro ");
      menuSql.append(" JOIN MOC_TL_ROLECONFIGMST rcfm ");
      menuSql.append(" ON rcfm.MOC_RCM_ROLEID = mcro.ROLM_KEYID ");
      menuSql.append(" AND rcfm.MOC_RCM_FINALAPPROVAL = 'Y' ");
      menuSql.append(" AND rcfm.MOC_RCM_RFCKEYID = '" + mocKeyid + "' ");
      menuSql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST empm ");
      menuSql.append(" ON empm.EMPM_KEYID = rcfm.MOC_RCM_EMPID ");

  } else {

      menuSql.append(" SELECT ");
      menuSql.append(" '' AS \"MOC_RCM_KEYID\", ");
      menuSql.append(" '' AS \"MOC_RCM_RFCKEYID\", ");
      menuSql.append(" mcro.ROLM_KEYID, ");
      menuSql.append(" mcro.ROLM_NAME, ");
      menuSql.append(" '' AS \"MOC_RCM_GROUPNUM\", ");
      menuSql.append(" '' AS \"EMPM_NAME\", ");
      menuSql.append(" '' AS \"EMPM_KEYID\", ");
      menuSql.append(" '' AS \"MOC_RCM_FASTATUS\", ");
      menuSql.append(" '' AS \"MOC_RCM_FADATE\", ");
      menuSql.append(" '' AS \"MOC_RCM_FAREMARKS\", ");
      menuSql.append(" '' AS \"BLANK\" ");

      menuSql.append(" FROM ");
      menuSql.append(" MOC_TL_ROLEMST mcro ");

  }

  System.out.println("LossSql  :" + menuSql.toString());
  List<String[]> getPillarRelMenu = dbActionTemplate.getDataList(menuSql.toString());
  return getPillarRelMenu;
}
//@Override
//public List<String[]> getFinalApprovalList(String mocKeyid) throws Exception {
//
//
//	StringBuffer menuSql  = new StringBuffer();
//
//	 if(UIUtils.isValidKeyId(mocKeyid)){
//	menuSql.append(" SELECT MOC_RCM_KEYID, MOC_RCM_RFCKEYID,ROLM_KEYID,ROLM_NAME,MOC_RCM_GROUPNUM,EMPM_NAME,EMPM_KEYID,MOC_RCM_FASTATUS,TO_CHAR(MOC_RCM_FADATE,'DD-MON-YYYY'),MOC_RCM_FAREMARKS,''");
//	 menuSql.append(" FROM MOC_TL_ROLECONFIGMST ,GEN_TL_EMPLOYEEMST, MOC_TL_ROLEMST" );
//	menuSql.append(" WHERE MOC_RCM_EMPID=EMPM_KEYID(+) AND MOC_RCM_ROLEID(+)=ROLM_KEYID AND MOC_RCM_FINALAPPROVAL='Y'" );		
//
//	if(UIUtils.isValidKeyId(mocKeyid)){
//		   menuSql.append(" AND MOC_RCM_RFCKEYID ='"+mocKeyid+"' ");
//		  }
//	 }
//	else{
//		System.out.println("In Else Approval");
//		
//		menuSql.append(" SELECT '', '',ROLM_KEYID,ROLM_NAME,'','','','','','',''");
//		 menuSql.append(" FROM MOC_TL_ROLEMST" );
//		menuSql.append(" WHERE 1=1" );		
//
//	 }
//
//		System.out.println("LossSql  :"+menuSql.toString());
//		List<String[]> getPillarRelMenu = dbActionTemplate.getDataList(menuSql.toString());
//		return getPillarRelMenu;
//}
@Override
public MocTeamConfigmst getFinalApproval(MocTeamConfigmst mocTeamConfigmst, MocTeamConfigmst existMocTeamConfigmst,
		String keyid, String empId, String status, String date, String remarks,String MocKeyId) throws Exception {
//	popSqlsForHazopApprovalStatusUpdate(keyid);
	popSqlsForFinalApprovalStatusUpdate(MocKeyId);
	StringBuilder sql=new StringBuilder();
	if(status.equals("E")){
		
		
	sql.append(" UPDATE MOC_TL_ROLECONFIGMST SET MOC_RCM_FABY='"+empId+"', MOC_RCM_FAREMARKS='"+remarks+"',MOC_RCM_FASTATUS='"+status+"',MOC_RCM_FADATE='"+date+"',MOC_RCM_FAYN='Y',MOC_RCM_FARWKFLAG='Y',MOC_RCM_FARWKDATE='"+date+"',MOC_RCM_IARWKREMARKS='"+remarks+"' WHERE MOC_RCM_KEYID='"+keyid+"' ");
	   System.out.println("Role"+sql);
	   
	}

	else{
		sql.append(" UPDATE MOC_TL_ROLECONFIGMST SET MOC_RCM_FABY='"+empId+"', MOC_RCM_FAREMARKS='"+remarks+"',MOC_RCM_FASTATUS='"+status+"',MOC_RCM_FADATE='"+date+"',MOC_RCM_FAYN='Y' WHERE MOC_RCM_KEYID='"+keyid+"' ");
		   System.out.println(" Role:::"+sql);	
	}
	dbActionTemplate.executeStatement(sql.toString());
		return mocTeamConfigmst;
}
@Override
public MocTeamConfigmst getHazopApproval(MocTeamConfigmst mocTeamConfigmst, MocTeamConfigmst existMocTeamConfigmst,
		String keyid, String empId, String status, String date, String remarks,String MocKeyId) throws Exception {
	// TODO Auto-generated method stub
	//popSqlsForHazopStatusUpdate(keyid);
	popSqlsForHazopApprovalStatusUpdate(MocKeyId);
	StringBuilder sql=new StringBuilder();
	if(status.equals("E")){
		
		
	sql.append(" UPDATE MOC_TL_ROLECONFIGMST SET MOC_RCM_HZBY='"+empId+"', MOC_RCM_HZREMARKS='"+remarks+"',MOC_RCM_HZSTATUS='"+status+"',MOC_RCM_HZDATE='"+date+"',MOC_RCM_HZYN='Y',MOC_RCM_HZRWKFLAG='Y',MOC_RCM_HZRWKDATE='"+date+"',MOC_RCM_HZRWKREMARKS='"+remarks+"' WHERE MOC_RCM_KEYID='"+keyid+"' ");
	   System.out.println("role Hz"+sql);
	   
	}

	else{
		sql.append(" UPDATE MOC_TL_ROLECONFIGMST SET MOC_RCM_HZBY='"+empId+"', MOC_RCM_HZREMARKS='"+remarks+"',MOC_RCM_HZSTATUS='"+status+"',MOC_RCM_HZDATE='"+date+"',MOC_RCM_HZYN='Y' WHERE MOC_RCM_KEYID='"+keyid+"' ");
		   System.out.println("Role:::"+sql);	
	}
	dbActionTemplate.executeStatement(sql.toString());
		return mocTeamConfigmst;
}
@Override
public List<String[]> getWhatIfList(CommonFilter commonFilter) throws Exception {		
	try
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms="MOCKEYID="+commonFilter.getKey()+";";
		}			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		dataList =  dbActionTemplate.processFunctionCalls("MOC_TL_WHATIFLIST", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonFunctions.debugMsg("totalCnt....."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
	}
}
public List<String[]> getHazopList(CommonFilter commonFilter) throws Exception{
	try
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms="MOCMKEYID="+commonFilter.getKey()+";";
		}			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		dataList =  dbActionTemplate.processFunctionCalls("MOC_TL_HAZOPLIST", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonFunctions.debugMsg("totalCnt....."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
	}
}
public HazopMst createHazop(HazopMst newHazopMst,String Keyid) throws BusinessApplicationExceptions,ValidationException,Exception{
List<String> sql=new ArrayList<String>();
HazopMstSql hazopMstSql=new HazopMstSql();
HazopDtlSql  hazopDtlSql=new HazopDtlSql();
HazopDtl hazopDtl=new HazopDtl();
try{
   newHazopMst.setHzomKeyid(dbActionTemplate.getSequenceNumber(HazopMstSql.TBL_MOC_TL_HAZOPMST,12, "HZOM","",""));
   sql.add(HazopMstSql.getInsertSql(hazopMstSql.gethzomDbFields(),newHazopMst.getSaveArray()));
   if(newHazopMst.getHazopDetails() !=null){		
		List<HazopDtl> hazopdtlList=newHazopMst.getHazopDetails();
		for(int i=0 ;i<=hazopdtlList.size()-1;i++){				
			hazopDtl=hazopdtlList.get(i);
			System.out.println("inside the for loops");
			if (!CommonFunctions.isValidKeyId(hazopDtl.getMohdKeyid())){
				hazopDtl.setMohdMohmKeyid(newHazopMst.getHzomKeyid());
				hazopDtl.setMohdWithSafeGuards("-");
				hazopDtl.setMohdKeyid(dbActionTemplate.getSequenceNumber(HazopDtlSql.TBL_MOC_TL_HAZOPDTL,12,"MOHD","",""));
				sql.add(hazopDtlSql.getInsertSql(hazopDtlSql.getmohdDbFields(), hazopDtl.getSaveArray()));
				}				
		}
	}	
	dbActionTemplate.executeStatements(sql); 
}catch (ValidationExceptions e){
	throw new ValidationExceptions(e.getMessage());
}	
catch (BusinessApplicationExceptions e){			
	throw new BusinessApplicationExceptions(e.getMessage());
}catch( Exception e){
	throw new Exception(e.getMessage());
}	
return newHazopMst;
}

public HazopMst UpdateHazop(HazopMst newHazopMst,String HazopKeyId) throws Exception{
	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	HazopMstSql hazopMstSql=new HazopMstSql();
	HazopDtlSql  hazopDtlSql=new HazopDtlSql();
	HazopDtl hazopDtl=new HazopDtl();
	System.out.println("The HazopKey "+HazopKeyId);
	try {
		newHazopMst.setHzomKeyid(HazopKeyId);
		sqls.add(HazopMstSql.getUpdateSql(hazopMstSql.gethzomDbFields(),newHazopMst.getSaveArray()));					
		if(newHazopMst.getHazopDetails() !=null){
			List<HazopDtl> hazopdtlList=newHazopMst.getHazopDetails();			
			for(int i=0 ;i<=hazopdtlList.size()-1;i++){				
				hazopDtl=hazopdtlList.get(i);
				hazopDtl.setMohdMohmKeyid(HazopKeyId);
				System.out.println("Master Id"+HazopKeyId);
				hazopDtl.setMohdWithSafeGuards("-");				
				if (!CommonFunctions.isValidKeyId(hazopDtl.getMohdKeyid())){
					System.out.println("Inside Insert"+hazopDtl.getMohdKeyid());
					hazopDtl.setMohdKeyid(dbActionTemplate.getSequenceNumber(HazopDtlSql.TBL_MOC_TL_HAZOPDTL,12,"MOHD","",""));
					sqls.add(hazopDtlSql.getInsertSql(hazopDtlSql.getmohdDbFields(),hazopDtl.getSaveArray()));
				}
				else{
					System.out.println("Inside Update");
					sqls.add(hazopDtlSql.getUpdateSql(hazopDtlSql.getmohdDbFields(),hazopDtl.getSaveArray()));
				}	
			}
		}
		dbActionTemplate.executeStatements(sqls);	
	}catch (ValidationExceptions e){
		throw new ValidationExceptions(e.getMessage());
	}	
	catch (BusinessApplicationExceptions e){			
		throw new BusinessApplicationExceptions(e.getMessage());
	}catch( Exception e){
		throw new Exception(e.getMessage());
	}	
	return newHazopMst;
}
public void DeleteHazopRow(String keyid) throws Exception 
{
	List<String > sqls = new ArrayList<String>();
	sqls.add( HazopDtlSql.DeleteHazopRow(keyid));
	//sqls.add( HazopDtlSql.getDeleteSql(keyid));
	dbActionTemplate.executeStatements(sqls);
}

public WhatifMst createWhatif(WhatifMst newWhatifMst,String Keyid) 	
		throws ValidationExceptions,BusinessApplicationExceptions,Exception{ 
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		WhatifMstSql whatifMstSql=new WhatifMstSql();
		WhatifDtlSql whatifDtlSql=new WhatifDtlSql();
		WhatifDtl whatifDtl=new WhatifDtl();
		try{
			
			newWhatifMst.setWifmKeyid(dbActionTemplate.getSequenceNumber(WhatifMstSql.TBL_MOC_TL_WHATIFMST,12,"WIFM","",""));
			sqls.add(WhatifMstSql.getInsertSql(whatifMstSql.getwifmDbFields(),newWhatifMst.getSaveArray()));			
			if(newWhatifMst.getWhatifDetails() !=null){
				List<WhatifDtl> whatifdtlList=newWhatifMst.getWhatifDetails();
				for(int i=0 ;i<=whatifdtlList.size()-1;i++){				
					whatifDtl=whatifdtlList.get(i);
					if (!CommonFunctions.isValidKeyId(whatifDtl.getWifdKeyid())){
						whatifDtl.setWifdWifmKeyid(newWhatifMst.getWifmKeyid());
						whatifDtl.setWifdWithSafeGuards("-");
						whatifDtl.setWifdKeyid(dbActionTemplate.getSequenceNumber(WhatifDtlSql.TBL_MOC_TL_WHATIFDTL,12,"WIFD","",""));
						sqls.add(WhatifDtlSql.getInsertSql(whatifDtlSql.getwifdDbFields(),whatifDtl.getSaveArray()));
						}				
				}
			}
			dbActionTemplate.executeStatements(sqls); 
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newWhatifMst;
	}

public WhatifMst UpdateWhatif(WhatifMst newWhatifMst,String Keyid,String WhatifKeyId) throws BusinessApplicationExceptions,ValidationException,Exception{
	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	WhatifMstSql whatifMstSql=new WhatifMstSql();
	WhatifDtlSql whatifDtlSql=new WhatifDtlSql();
	WhatifDtl whatifDtl=new WhatifDtl();
	System.out.println("The Whatif "+WhatifKeyId);
	try {
		newWhatifMst.setWifmKeyid(WhatifKeyId);
		sqls.add(WhatifMstSql.getUpdateSql(whatifMstSql.getwifmDbFields(),newWhatifMst.getSaveArray()));					
		if(newWhatifMst.getWhatifDetails() !=null){
			List<WhatifDtl> whatifdtlList=newWhatifMst.getWhatifDetails();			
			for(int i=0 ;i<=whatifdtlList.size()-1;i++){				
				whatifDtl=whatifdtlList.get(i);
				
				whatifDtl.setWifdWifmKeyid(WhatifKeyId);
				
				System.out.println("Master Id"+WhatifKeyId);
				
				whatifDtl.setWifdWithSafeGuards("-");				
				if (!CommonFunctions.isValidKeyId(whatifDtl.getWifdKeyid())){
					System.out.println("Inside Insert"+whatifDtl.getWifdKeyid());
					whatifDtl.setWifdKeyid(dbActionTemplate.getSequenceNumber(WhatifDtlSql.TBL_MOC_TL_WHATIFDTL,12,"WIFD","",""));
					sqls.add(whatifDtlSql.getInsertSql(whatifDtlSql.getwifdDbFields(),whatifDtl.getSaveArray()));
				}
				else{
					System.out.println("Inside Update");
					sqls.add(whatifDtlSql.getUpdateSql(whatifDtlSql.getwifdDbFields(),whatifDtl.getSaveArray()));
				}	
			}
		}
		dbActionTemplate.executeStatements(sqls);	
	}catch (ValidationExceptions e){
		throw new ValidationExceptions(e.getMessage());
	}	
	catch (BusinessApplicationExceptions e){			
		throw new BusinessApplicationExceptions(e.getMessage());
	}catch( Exception e){
		throw new Exception(e.getMessage());
	}	
	return newWhatifMst;
}

public void DeleteWhatifRow(String keyid) throws Exception 
{
	List<String > sqls = new ArrayList<String>();
	sqls.add(WhatifDtlSql.DeleteWhatifRow(keyid));
	dbActionTemplate.executeStatements(sqls);
}

@Override 
public MocPssrmst createPssrCheckListC(MocPssrmst newwMocPssrmst, MocPssrmst existMocPssrmst,String Keyid) throws Exception {
	// TODO Auto-generated method stub
	System.out.println("In Dao Impl");
	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	MocPssrmstSql mocPssrmstSql = new MocPssrmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
	
try{
	newwMocPssrmst.setPsrmrfcid(Keyid);

	newwMocPssrmst.setPsrmKeyid(dbActionTemplate.getSequenceNumber(MocPssrmstSql.TBL_MOC_TL_PSSRCHECKLISTMST,14,"PSR","","")); // set the sequnce number 
	popSqlsForPssrChkStatusUpdate(Keyid);
	popSqlsForPssrChecklistDeleteData(Keyid);
	sqls.add(MocPssrmstSql.getInsertSql(mocPssrmstSql.getpsrm_DbFields(), newwMocPssrmst.getSaveArray())); // add insert sql for master table

//   
System.out.println("after dtl "+sqls.toString());

dbActionTemplate.executeStatements(sqls); // execute the block of sqls
	
}		
catch(Exception e)
			{
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
	return newwMocPssrmst;
}
@Override
public List<MocClosure> createClosureQuestionsNew(List<MocClosure> fillValuesClosure,String mocKeyidClosure) throws Exception {
	// TODO Auto-generated method stub
	MocClosureSql mocClosureSql = new MocClosureSql(); // contains dbtable,field names, Field types and related sqls  of master table
	try
	{
		     List<String> sqls = new ArrayList<String>();
		     List <MocClosure> methodslist = fillValuesClosure;
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),MocClosureSql.TBL_MOC_TL_CLOSUREMASTER,15,"MOCC", null,null);
			 System.out.println("sequenceNumber in IF"+sequenceNumber);
			 for(MocClosure empSessionLink:methodslist)
			 {
				    System.out.println("The Role Keyid");
					String seqNo=sequenceNumber.getSequnceNumber();
					System.out.println("The seqNo::::"+seqNo);
					empSessionLink.setRfccKeyid(seqNo);
					popSqlsForClosureStatusUpdate(mocKeyidClosure);
					sqls.add(MocClosureSql.getInsertSql(mocClosureSql.getrfcc_DbFields(), empSessionLink.getSaveArray()));
			        System.out.println("The sqls Query:"+sqls);
			 }
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		System.out.println("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return fillValuesClosure;
}
@Override
public List<MocPssrdtl> createPssrCheckListdtl(List<MocPssrdtl> team, String createdBy,
		MocPssrmst newwMocPssrmst) throws Exception {

	MocPssrdtlSql mocPssrdtlSql=new MocPssrdtlSql();
	MocPssrdtl mocPssrdtl=new MocPssrdtl(); // contains dbtable,field names, Field types and related sqls  of master table
	try
	{

		     List<String> sqls = new ArrayList<String>();
		     List <MocPssrdtl> methodslist = team;
		     System.out.println("before for loop"+methodslist);
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),MocPssrdtlSql.TBL_MOC_TL_PSSRCHECKLISTDTL,14,"PSD", null,null);
		     String detlKeyid="";
		     String MocMasterId="";
		   //  GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource(),"",14,"MOCT", "YYMM","Y");
			System.out.println("the sequenceNumber"+sequenceNumber);
		     for(MocPssrdtl abnormalityLink : methodslist)
			 {
					String seqNo=sequenceNumber.getSequnceNumber();
					System.out.println("The seqNo::::"+seqNo);
					abnormalityLink.setPsrdKeyid(seqNo);
					abnormalityLink.setPsrdCreatedby(createdBy);
					detlKeyid= newwMocPssrmst.getPsrmKeyid();
					MocMasterId=newwMocPssrmst.getPsrmrfcid();
					System.out.println("MocMasterId::::"+MocMasterId);
					System.out.println("detlKeyid::::"+detlKeyid);
					abnormalityLink.setPsrdmasterid(detlKeyid);
					abnormalityLink.setPsrdMocid(MocMasterId);
					//  popSqlsForTeamDeleteData(detlKeyid);
                    sqls.add(MocPssrdtlSql.getInsertSql(mocPssrdtlSql.getpsrd_DbFields(), abnormalityLink.getSaveArray()));
                  //  MultipleactionPlanEntry(list,"I");
                    System.out.println("The sql tEAM NOs Query:"+sqls);
			 }
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		CommonFunctions.debugMsg("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return team;
}



/*@Override
public List<MOCPssrReccommend> createMocReccommendation(List<MOCPssrReccommend> fillValuesReccommend,String Keyid) throws Exception {
	// TODO Auto-generated method stub
	MOCPssrReccommendSql mOCPssrReccommendSql = new MOCPssrReccommendSql(); // contains dbtable,field names, Field types and related sqls  of master table
	try
	{
		     List<String> sqls = new ArrayList<String>();
		     List <MOCPssrReccommend> methodslist = fillValuesReccommend;
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource(),MOCPssrReccommendSql.TBL_PSSR_TL_RECCOMENDATIONS,15,"PSRR", null,null);
			 System.out.println("sequenceNumber in IF"+sequenceNumber);
			 for(MOCPssrReccommend empSessionLink:methodslist)
			 {
				    System.out.println("The Role Keyid");
					String seqNo=sequenceNumber.getSequnceNumber();
					System.out.println("The seqNo::::"+seqNo);
					empSessionLink.setPsrrKeyid(seqNo);
					popSqlsForPssrRcmndStatusUpdate(Keyid);
					sqls.add(MOCPssrReccommendSql.getInsertSql(mOCPssrReccommendSql.getpsrrDbFields(), empSessionLink.getSaveArray()));
			        System.out.println("The sqls Query:"+sqls);
			 }
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		System.out.println("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return fillValuesReccommend;
}

@Override
public List<MOCPssrReccommend> UpdateMocReccommendation(List<MOCPssrReccommend> fillValuesReccommend,String Keyid,String PsrrKeyId) throws Exception {
	// TODO Auto-generated method stub
	MOCPssrReccommendSql mOCPssrReccommendSql = new MOCPssrReccommendSql(); // contains dbtable,field names, Field types and related sqls  of master table
	try
	{
		     List<String> sqls = new ArrayList<String>();
		     List <MOCPssrReccommend> methodslist = fillValuesReccommend;
			 for(MOCPssrReccommend empSessionLink:methodslist)
			 {
				    System.out.println("The Keyid");
				    empSessionLink.setPsrrKeyid(PsrrKeyId);
					sqls.add(MOCPssrReccommendSql.getUpdateSql(mOCPssrReccommendSql.getpsrrDbFields(), empSessionLink.getSaveArray()));
			        System.out.println("The sqls Query:"+sqls);
			 }
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		System.out.println("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return fillValuesReccommend;
}
*/public String getActionDKeyid(String Keyid) throws Exception{
	   String ActionDetailId=dbActionTemplate.getSingleValue("GEN_TL_ACTIONPLANDTL","APLD_KEYID","APLD_APLM_KEYID",Keyid);
	   System.out.println("ActionDetailId"+ActionDetailId);
	   return Keyid;
}

public String getPssrKeyid(String mocKeyid) throws Exception{
	   String Keyid=dbActionTemplate.getSingleValue("PSSR_TL_RECCOMENDATIONS","MAX(PSRR_KEYID)","PSRR_MOC_KEYID",mocKeyid);
	   System.out.println("PSSR KeyId"+Keyid);
	   return Keyid;	
	}

public GenTlActionplanmst createActionPlan(GenTlActionplanmst genTlActionplanmst,
		GenTlActionplandtl genTlActionplandtl,String MocKeyidRec) throws BusinessApplicationExceptions, Exception {
	List<String> sqls = new ArrayList<String>();
	GenTlActionplanmstSql genTlActionplanmstSql = new GenTlActionplanmstSql(); 
	GenTlActionplandtlSql genTlActionplandtlSql = new GenTlActionplandtlSql();
	String elementId = genTlActionplanmst.getAplmElementid();	
 	String seqIdentf = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlActionplanmstSql.TBL_GEN_TL_ACTIONPLANMST);
	try {
		genTlActionplanmst.setAplmKeyid(dbActionTemplate.getSequenceNumber(seqIdentf, 10, "AP","", ""));
		genTlActionplandtl.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
		genTlActionplanmst.setAplmMasterrefid(MocKeyidRec);
	//	String Momdetailid=dbActionTemplate.getSingleValue("GEN_TL_MOMDTL", "MOMD_KEYID", "MOMD_MOMS_KEYID",newGenTlMommst.getMomsKeyid());
	    genTlActionplanmst.setAplmDetailrefid(MocKeyidRec);
		sqls.add(GenTlActionplanmstSql.getInsertSql(genTlActionplanmstSql.getAplmDbFields(),genTlActionplanmst.getSaveArray()));
	    List<GenTlActionplandtl> genTlActionplandtls= genTlActionplandtl.getActionplanlist();
	    if( genTlActionplandtls !=null && genTlActionplandtls.size()>0){
	            for(GenTlActionplandtl genTlActionplandtlss:genTlActionplandtls)
				{
	            	System.out.println("Inside the for Details:::");
	             	genTlActionplandtlss.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,10,"APLD", "", ""));
	            	genTlActionplandtlss.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
	            	genTlActionplandtlss.setApldHowtodo(genTlActionplandtl.getApldHowtodo());
	              	genTlActionplandtlss.setApldActionplan(genTlActionplandtl.getApldActionplan());
	            	genTlActionplandtlss.setApldTradeid(genTlActionplandtl.getApldTradeid());
	            	genTlActionplandtlss.setApldStatus(genTlActionplandtl.getApldStatus());
	            	genTlActionplandtlss.setApldTargetdate(genTlActionplandtl.getApldTargetdate());
	            	genTlActionplandtlss.setApldCompleatedon(genTlActionplandtl.getApldCompleatedon());
	            	genTlActionplandtlss.setApldCompletedby(genTlActionplandtl.getApldCompletedby());
	            	genTlActionplandtlss.setApldCountermeasure(genTlActionplandtl.getApldCountermeasure());
	            	genTlActionplandtlss.setApldCreatedby(genTlActionplandtl.getApldCreatedby());
	            	genTlActionplandtlss.setApldCreatedon(genTlActionplandtl.getApldCreatedon());
	            	genTlActionplandtlss.setApldRemarks(genTlActionplandtl.getApldRemarks());
	            	genTlActionplandtlss.setApldOthers(genTlActionplandtl.getApldOthers());
	            	genTlActionplandtlss.setApldTempfiled2(genTlActionplandtl.getApldTempfiled2());
	            	genTlActionplandtlss.setApldTempfiled3(genTlActionplandtl.getApldTempfiled3());
	            	genTlActionplandtlss.setApldTempfiled4(genTlActionplandtl.getApldTempfiled4());
	            	genTlActionplandtlss.setApldTempfiled5(genTlActionplandtl.getApldTempfiled5());
	            	genTlActionplandtlss.setApldActive(genTlActionplandtl.getApldActive());
	            	genTlActionplandtlss.setApldModifiedon(genTlActionplandtl.getApldModifiedon());
	            	genTlActionplandtlss.setApldResponsibility(genTlActionplandtlss.getApldResponsibility());
					sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtlss.getSaveArray()));// add insert sql for detail table
				} 
	    }
		 else{
		   if (!CommonFunctions.isValidKeyId(genTlActionplandtl.getApldKeyid())){
			   System.out.println("Inside the else if Details:::");
			genTlActionplandtl.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,10, "APLD", "", "")); 
			sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtl.getSaveArray()));
		}}
		sqls.add(GenTlActionplanmstSql.getUpdateStausSql(genTlActionplanmstSql.getAplmDbFields(),genTlActionplanmst.getSaveArray())); 		
		/*String sql = GenTlActionplanmstSql.getRefDocUpdateSql(genTlActionplanmst);
		if (CommonFunctions.isValidKeyId(sql))
			sqls.add(sql);*/
		dbActionTemplate.executeStatements(sqls); 													
	} catch (Exception e) {
		throw new Exception(e.getMessage());
	}
	return genTlActionplanmst;
}

//POSTGRESE QUERY
@Override
public List<String[]> getPssrReccommendation(CommonFilter commonFilter) throws Exception {
 // TODO Auto-generated method stub
 StringBuffer sql = new StringBuffer();
 String mocKeyId = commonFilter.getKey();
 System.out.println("IN Dao recc" + mocKeyId);
 
 if (UIUtils.isValidKeyId(mocKeyId)) {
     System.out.println("In IF");
     
     sql.append(" SELECT DISTINCT ");
     sql.append(" psrr.PSRR_KEYID AS \"PSRR_KEYID\", ");
     sql.append(" psrr.PSRR_MOC_KEYID AS \"PSRR_MOC_KEYID\", ");
     sql.append(" psrr.PSRR_RECOMMEND AS \"PSRR_RECOMMEND\", ");
     sql.append(" psrr.PSRR_CATEGORY AS \"PSRR_CATEGORY\", ");
     sql.append(" empm.EMPM_NAME AS \"EMPM_NAME\", ");
     sql.append(" psrr.PSRR_RESPONSIBLEID AS \"PSRR_RESPONSIBLEID\", ");
     sql.append(" TO_CHAR(psrr.PSRR_TARGETDATE, 'DD-MON-YYYY') AS \"PSRR_TARGETDATE\", ");
     sql.append(" psrr.PSRR_STATUS AS \"PSRR_STATUS\", ");
     sql.append(" '' AS \"BLANK\", ");
     sql.append(" TO_CHAR(psrr.PSRR_COMPLETEDDATE, 'DD-MON-YYYY') AS \"PSRR_COMPLETEDDATE\" ");
     
     sql.append(" FROM ");
     sql.append(" PSSR_TL_RECCOMENDATIONS psrr ");
     sql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST empm ON empm.EMPM_KEYID = psrr.PSRR_RESPONSIBLEID ");
     
     sql.append(" WHERE ");
     sql.append(" psrr.PSRR_MOC_KEYID = '" + mocKeyId + "' ");
     
     sql.append(" ORDER BY ");
     sql.append(" psrr.PSRR_KEYID ASC ");
     
     System.out.println("SQL is::::" + sql);
 }

 List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
 return gridData;
}



//@Override
//public List<String[]> getPssrReccommendation(CommonFilter commonFilter) throws Exception {
//	// TODO Auto-generated method stub
//	StringBuffer sql = new StringBuffer();
//	String mocKeyId=commonFilter.getKey();
//	System.out.println("IN Dao recc"+mocKeyId);
//	 if(UIUtils.isValidKeyId(mocKeyId)){
//		 System.out.println("In IF");
//	sql.append(" SELECT distinct "); 
//	sql.append("PSRR_KEYID AS \"PSRR_KEYID\",PSRR_MOC_KEYID AS \"PSRR_MOC_KEYID\",PSRR_RECOMMEND AS \"PSRR_RECOMMEND\",PSRR_CATEGORY AS \"PSRR_CATEGORY\",EMPM_NAME AS \"EMPM_NAME\",PSRR_RESPONSIBLEID AS \"PSRR_RESPONSIBLEID\",TO_CHAR(PSRR_TARGETDATE,'DD-MON-YYYY') AS \"PSRR_TARGETDATE\",PSRR_STATUS AS \"PSRR_STATUS\",'',TO_CHAR(PSRR_COMPLETEDDATE,'DD-MON-YYYY') AS \"PSRR_COMPLETEDDATE\"");  
//	sql.append(" FROM ");
//	sql.append(" PSSR_TL_RECCOMENDATIONS,GEN_TL_EMPLOYEEMST"); 
//	sql.append(" WHERE ");
//	sql.append(" 1=1 AND EMPM_KEYID(+)=PSRR_RESPONSIBLEID" );
// 	
//	   if(UIUtils.isValidKeyId(mocKeyId)){
//	    //sql.append(" AND QCDC_QCLDKEYID ='"+unsafeact+"' ");
//		sql.append(" AND PSRR_MOC_KEYID ='"+mocKeyId+"' ");
//	   }
//   sql.append(" ORDER BY ");
//   sql.append(" PSRR_KEYID asc ");
//   System.out.println("SQL is::::"+sql);
//	 }
//	 
//	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
//	return gridData;
//}

public String getPssrCount(String mocKeyid) throws Exception{
	String pssrCount=dbActionTemplate.getSingleValue("SELECT COUNT(*) FROM PSSR_TL_RECCOMENDATIONS WHERE PSRR_MOC_KEYID='"+mocKeyid+"'  AND PSRR_STATUS='P' ");
    System.out.println("Initial pssrCount"+pssrCount);	
	return pssrCount;
}
@Override
public String getApprovalCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String TotalApproval = null;

	String Maxgroup=dbActionTemplate.getSingleValue("SELECT MAX(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_INITIALAPPROVAL='Y' AND MOC_RCM_IASTATUS='A' ");
  if(Maxgroup==null){
	
  

    	 TotalApproval=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_ROLECONFIGMST WHERE 	MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_INITIALAPPROVAL='Y' AND MOC_RCM_groupnum='0' "); 	
    	  System.out.println("Initial Total Approval"+TotalApproval);	
    }
  else
  {
		
 TotalApproval=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_ROLECONFIGMST WHERE 	MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_INITIALAPPROVAL='Y' AND MOC_RCM_groupnum='"+Maxgroup+"' "); 	
 	  System.out.println("Initial Total Approval"+TotalApproval);	
 }
 
    
	return TotalApproval;
}
@Override
public String getCompletedApprovalCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String CompletedApproval = null;
	String Maxgroup=dbActionTemplate.getSingleValue("SELECT MAX(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_INITIALAPPROVAL='Y' AND MOC_RCM_IASTATUS='A' ");
	 if(Maxgroup==null){
			 

	    CompletedApproval=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_ROLECONFIGMST WHERE 	MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_INITIALAPPROVAL='Y' AND MOC_RCM_groupnum='0' AND MOC_RCM_IASTATUS='A' "); 	
	    System.out.println("Initial ComApproval"+CompletedApproval);	    
	    }
	 else{

		    CompletedApproval=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_ROLECONFIGMST WHERE 	MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_INITIALAPPROVAL='Y' AND MOC_RCM_groupnum='"+Maxgroup+"' AND MOC_RCM_IASTATUS='A' "); 	
		    System.out.println("Initial ComApproval"+CompletedApproval);		 
	 }
	 return CompletedApproval;
}
@Override
public String getMaxGroupCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	
	String MaxGroupNo=dbActionTemplate.getSingleValue("SELECT MAX(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_INITIALAPPROVAL='Y' AND MOC_RCM_IASTATUS='A' ");
    System.out.println("Inital MaxGroup"+MaxGroupNo);
    return MaxGroupNo;
}
@Override
public String getNextGroupCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String NextGroupNo=dbActionTemplate.getSingleValue("SELECT MIN(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_INITIALAPPROVAL='Y' AND MOC_RCM_IASTATUS='-' ");
    System.out.println("Initial NextGroup"+NextGroupNo);
    return NextGroupNo;
}
@Override
public String getHazopApprovalCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String HazopTotalApproval = null;

	String Maxgroup=dbActionTemplate.getSingleValue("SELECT MAX(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_HAZOPAPPROVAL='Y' AND MOC_RCM_HZSTATUS='A' ");
    System.out.println("Hazop MaxGroup"+Maxgroup);	
    if(Maxgroup==null){

    	HazopTotalApproval=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_ROLECONFIGMST WHERE 	MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_HAZOPAPPROVAL='Y' AND MOC_RCM_groupnum='0' "); 	
    }
    else{
       	HazopTotalApproval=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_ROLECONFIGMST WHERE 	MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_HAZOPAPPROVAL='Y' AND MOC_RCM_groupnum='"+Maxgroup+"' "); 	
         	
    }
 
    
	return HazopTotalApproval;
}
@Override
public String getHazopCompletedApprovalCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String HazopCompletedApproval = null;
	String Maxgroup=dbActionTemplate.getSingleValue("SELECT MAX(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_HAZOPAPPROVAL='Y' AND MOC_RCM_HZSTATUS='A' ");
    System.out.println("MaxGroup"+Maxgroup);	
    if(Maxgroup==null){
		 HazopCompletedApproval=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_ROLECONFIGMST WHERE 	MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_HAZOPAPPROVAL='Y' AND MOC_RCM_groupnum='0' AND MOC_RCM_HZSTATUS='A' "); 	
	     
	    }
    else{
   	 HazopCompletedApproval=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_ROLECONFIGMST WHERE 	MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_HAZOPAPPROVAL='Y' AND MOC_RCM_groupnum='"+Maxgroup+"' AND MOC_RCM_HZSTATUS='A' "); 	
	    	
    }
	 return HazopCompletedApproval;
}
@Override
public String getHazopMaxGroupCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String HazopMaxGroupNo=dbActionTemplate.getSingleValue("SELECT MAX(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_HAZOPAPPROVAL='Y' AND MOC_RCM_HZSTATUS='A' ");
    System.out.println("HazopMaxGroupNo"+HazopMaxGroupNo);
    return HazopMaxGroupNo;
}
@Override
public String getHazopNextGroupCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String HazopNextGroupNo=dbActionTemplate.getSingleValue("SELECT MIN(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_HAZOPAPPROVAL='Y' AND MOC_RCM_HZSTATUS='-' ");
    System.out.println("HazopNextGroupNo"+HazopNextGroupNo);
    return HazopNextGroupNo;
}
@Override
public String getFinalApprovalCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String FinalTotalApproval = null;

	String Maxgroup=dbActionTemplate.getSingleValue("SELECT MAX(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_FINALAPPROVAL='Y' AND MOC_RCM_FASTATUS='A' ");
    System.out.println("Final"+Maxgroup);	
    if(Maxgroup==null){

    	FinalTotalApproval=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_ROLECONFIGMST WHERE 	MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_FINALAPPROVAL='Y' AND MOC_RCM_groupnum='0' "); 	
    }
    else{

    	FinalTotalApproval=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_ROLECONFIGMST WHERE 	MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_FINALAPPROVAL='Y' AND MOC_RCM_groupnum='"+Maxgroup+"' "); 	
  
    }
    
	return FinalTotalApproval;
}
@Override
public String getFinalCompletedApprovalCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String FinalCompletedApproval = null;
	String Maxgroup=dbActionTemplate.getSingleValue("SELECT MAX(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_FINALAPPROVAL='Y' AND MOC_RCM_FASTATUS='A' ");
    System.out.println("MaxGroup"+Maxgroup);	
    if(Maxgroup==null){
    	
		 FinalCompletedApproval=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_ROLECONFIGMST WHERE 	MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_FINALAPPROVAL='Y' AND MOC_RCM_groupnum='0' AND MOC_RCM_FASTATUS='A' "); 	
	     
	    }
    else{
   	 FinalCompletedApproval=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_ROLECONFIGMST WHERE 	MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_FINALAPPROVAL='Y' AND MOC_RCM_groupnum='"+Maxgroup+"' AND MOC_RCM_FASTATUS='A' "); 	
     
 	 	
    }
	 return FinalCompletedApproval;
}
@Override
public String getFinalMaxGroupCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String FinalMaxGroupNo=dbActionTemplate.getSingleValue("SELECT MAX(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_FINALAPPROVAL='Y' AND MOC_RCM_FASTATUS='A' ");
    System.out.println("FinalMaxGroupNo"+FinalMaxGroupNo);
    return FinalMaxGroupNo;
}
@Override
public String getFinalNextGroupCount(String mockeyid) throws Exception {

	String FinalNextGroupNo=dbActionTemplate.getSingleValue("SELECT MIN(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_FINALAPPROVAL='Y' AND MOC_RCM_FASTATUS='-' ");
    System.out.println("FinalNextGroupNo"+FinalNextGroupNo);
    return FinalNextGroupNo;
}

@Override
public List<String[]> getHazopReccommendation(CommonFilter commonFilter) throws Exception {
    // TODO Auto-generated method stub
    StringBuffer sql = new StringBuffer();
    String mocKeyId = commonFilter.getKey();
    System.out.println("IN Dao recc" + mocKeyId);
    
    if (UIUtils.isValidKeyId(mocKeyId)) {
        System.out.println("In IF");
        // WhatIf query
        sql.append(" SELECT DISTINCT ");
        sql.append(" m.MOCR_KEYID AS \"MOCR_KEYID\", wd.WIFD_KEYID AS \"WIFD_KEYID\", w.WIFM_MOCM_KEYID AS \"WIFM_MOCM_KEYID\", 'WhatIf' AS TYPE, wd.WIFD_RECOMMENDATIONS AS \"WIFD_WHATIF\", e.EMPM_NAME AS \"EMPM_NAME\", m.MOCR_RESPONSIBLEID AS \"_RESPONSIBLEID\", TO_CHAR(m.MOCR_TARGETDATE,'DD-MON-YYYY') AS \"MOCR_TARGETDATE\", m.MOCR_STATUS AS \"MOCR_STATUS\", '', TO_CHAR(a.APLD_COMPLEATEDON,'DD-MON-YYYY') AS \"APLD_COMPLEATEDON\", apm.APLM_KEYID AS \"APLM_KEYID\" ");
        sql.append(" FROM MOC_TL_WHATIFMST w ");
        sql.append(" JOIN MOC_TL_WHATIFDTL wd ON w.WIFM_KEYID = wd.WIFD_WIFM_KEYID ");
        sql.append(" LEFT JOIN MOC_TL_RECCOMENDATIONS m ON m.MOCR_WH_KEYID = wd.WIFD_KEYID AND m.MOCR_MOC_KEYID = w.WIFM_MOCM_KEYID ");
        sql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST e ON m.MOCR_RESPONSIBLEID = e.EMPM_KEYID ");
        sql.append(" LEFT JOIN GEN_TL_ACTIONPLANMST apm ON apm.APLM_DETAILREFID = wd.WIFD_KEYID ");
        sql.append(" LEFT JOIN GEN_TL_ACTIONPLANDTL a ON a.APLD_APLM_KEYID = apm.APLM_KEYID AND e.EMPM_KEYID = a.APLD_RESPONSIBILITY ");
        sql.append(" WHERE wd.WIFD_RECOMMENDATIONS NOT IN ('-') ");
        
        if (UIUtils.isValidKeyId(mocKeyId)) {
            sql.append(" AND w.WIFM_MOCM_KEYID = '" + mocKeyId + "' ");
        }
    }
    
    sql.append(" UNION ");
    
    // Hazop query
    sql.append(" SELECT DISTINCT ");
    sql.append(" m.MOCR_KEYID AS \"MOCR_KEYID\", h.MOHD_KEYID AS \"MOHD_KEYID\", hz.HZOM_MOCM_KEYID AS \"HZOM_MOCM_KEYID\", 'Hazop' AS TYPE, h.MOHD_RECOMMENDATIONS AS \"MOHD_RECOMMENDATIONS\", e.EMPM_NAME AS \"EMPM_NAME\", m.MOCR_RESPONSIBLEID AS \"_RESPONSIBLEID\", TO_CHAR(m.MOCR_TARGETDATE,'DD-MON-YYYY') AS \"MOCR_TARGETDATE\", m.MOCR_STATUS AS \"MOCR_STATUS\", '', TO_CHAR(a.APLD_COMPLEATEDON,'DD-MON-YYYY') AS \"APLD_COMPLEATEDON\", apm.APLM_KEYID AS \"APLM_KEYID\" ");
    sql.append(" FROM moc_tl_hazopdtl h ");
    sql.append(" JOIN moc_tl_hazopmst hz ON hz.HZOM_KEYID = h.MOHD_MOHM_KEYID ");
    sql.append(" LEFT JOIN MOC_TL_RECCOMENDATIONS m ON m.MOCR_WH_KEYID = h.MOHD_KEYID AND m.MOCR_MOC_KEYID = hz.HZOM_MOCM_KEYID ");
    sql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST e ON m.MOCR_RESPONSIBLEID = e.EMPM_KEYID ");
    sql.append(" LEFT JOIN GEN_TL_ACTIONPLANMST apm ON apm.APLM_DETAILREFID = h.MOHD_KEYID ");
    sql.append(" LEFT JOIN GEN_TL_ACTIONPLANDTL a ON a.APLD_APLM_KEYID = apm.APLM_KEYID AND e.EMPM_KEYID = a.APLD_RESPONSIBILITY ");
    sql.append(" WHERE h.MOHD_RECOMMENDATIONS NOT IN ('-') ");
    
    if (UIUtils.isValidKeyId(mocKeyId)) {
        sql.append(" AND hz.HZOM_MOCM_KEYID = '" + mocKeyId + "' ");
    }

    System.out.println("SQL is::::" + sql);
    List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
    return gridData;
}


//@Override
//public List<String[]> getHazopReccommendation(CommonFilter commonFilter) throws Exception {
//	// TODO Auto-generated method stub
//	StringBuffer sql = new StringBuffer();
//	String mocKeyId=commonFilter.getKey();
//	System.out.println("IN Dao recc"+mocKeyId);
//	 if(UIUtils.isValidKeyId(mocKeyId)){
//		 System.out.println("In IF");
//	sql.append(" SELECT DISTINCT "); 
//	sql.append("MOCR_KEYID AS \"MOCR_KEYID\",WIFD_KEYID AS \"WIFD_KEYID\",WIFM_MOCM_KEYID AS \"WIFM_MOCM_KEYID\",'WhatIf' AS TYPE,WIFD_RECOMMENDATIONS AS \"WIFD_WHATIF\",EMPM_NAME AS \"EMPM_NAME\",MOCR_RESPONSIBLEID AS \"_RESPONSIBLEID\",TO_CHAR(MOCR_TARGETDATE,'DD-MON-YYYY') AS \"MOCR_TARGETDATE\",MOCR_STATUS AS \"MOCR_STATUS\",'',TO_CHAR(MOCR_COMPLETEDDATE,'DD-MON-YYYY') AS \"APLD_COMPLEATEDON\", APLM_KEYID AS \"APLM_KEYID\"");  
//	sql.append(" FROM ");
//	sql.append(" MOC_TL_WHATIFMST,MOC_TL_WHATIFDTL,GEN_TL_EMPLOYEEMST,GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL,MOC_TL_RECCOMENDATIONS"); 
//	sql.append(" WHERE ");
//	sql.append(" 1=1 AND EMPM_KEYID=APLD_RESPONSIBILITY(+) AND APLM_DETAILREFID(+)= WIFD_KEYID AND APLD_APLM_KEYID(+)=APLM_KEYID AND WIFM_KEYID=WIFD_WIFM_KEYID and MOCR_WH_KEYID(+)=WIFD_KEYID and MOCR_MOC_KEYID(+)=WIFM_MOCM_KEYID  AND MOCR_RESPONSIBLEID=EMPM_KEYID(+) AND WIFD_RECOMMENDATIONS NOT IN('-') ");
// 	
//	   if(UIUtils.isValidKeyId(mocKeyId)){
//	    //sql.append(" AND QCDC_QCLDKEYID ='"+unsafeact+"' ");
//		sql.append(" AND WIFM_MOCM_KEYID ='"+mocKeyId+"' ");
//	   }
//
//  
// 
//	 }
//	 
//	  sql.append("union");
//	   sql.append(" SELECT DISTINCT "); 
//		sql.append("MOCR_KEYID AS \"MOCR_KEYID\",MOHD_KEYID AS \"MOHD_KEYID\",HZOM_MOCM_KEYID AS \"HZOM_MOCM_KEYID\",'Hazop' AS TYPE,MOHD_RECOMMENDATIONS AS \"MOHD_RECOMMENDATIONS\",EMPM_NAME AS \"EMPM_NAME\",MOCR_RESPONSIBLEID AS \"_RESPONSIBLEID\",TO_CHAR(MOCR_TARGETDATE,'DD-MON-YYYY') AS \"MOCR_TARGETDATE\",MOCR_STATUS AS \"MOCR_STATUS\",'',TO_CHAR(APLD_COMPLEATEDON,'DD-MON-YYYY') AS \"APLD_COMPLEATEDON\", APLM_KEYID AS \"APLM_KEYID\"");  
//		sql.append(" FROM ");
//		sql.append(" moc_tl_hazopdtl,moc_tl_hazopmst,GEN_TL_EMPLOYEEMST,GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL,MOC_TL_RECCOMENDATIONS"); 
//		sql.append(" WHERE ");
//		sql.append(" 1=1 AND EMPM_KEYID=APLD_RESPONSIBILITY(+)  AND APLM_DETAILREFID(+) = MOHD_KEYID AND APLD_APLM_KEYID(+)=APLM_KEYID and MOCR_WH_KEYID(+)=MOHD_KEYID and MOCR_MOC_KEYID(+)=HZOM_MOCM_KEYID AND HZOM_KEYID=MOHD_MOHM_KEYID(+) AND MOCR_RESPONSIBLEID=EMPM_KEYID(+) AND MOHD_RECOMMENDATIONS NOT IN('-') ");
//		
//		   if(UIUtils.isValidKeyId(mocKeyId)){
//		    //sql.append(" AND QCDC_QCLDKEYID ='"+unsafeact+"' ");
//			sql.append(" AND HZOM_MOCM_KEYID ='"+mocKeyId+"' ");
//		   }
//	
//	  System.out.println("SQL is::::"+sql);
//	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
//	return gridData;
//}
@Override
public String getMOCStatusCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stubMocStatus
	
	String MocStatus=dbActionTemplate.getSingleValue("SELECT count(*) FROM moc_tl_rfcmst WHERE MOC_RFC_KEYID='"+mockeyid+"' AND MOC_RFC_STATUS in ('PSCK','PSRC')  ");
    System.out.println("MocStatus"+MocStatus);
    return MocStatus;
}
@Override
public List<MOCReccommendation> createReccommendation(List<MOCReccommendation> fillValuesReccommendation,
		String mocKeyidRec,String detailid) throws Exception {
	MOCReccommendationSql mOCReccommendationSql = new MOCReccommendationSql(); // contains dbtable,field names, Field types and related sqls  of master table
	try
	{
		     List<String> sqls = new ArrayList<String>();
		     List <MOCReccommendation> methodslist = fillValuesReccommendation;
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),MOCReccommendationSql.TBL_MOC_TL_RECCOMENDATIONS,15,"MOCR", null,null);
			 System.out.println("sequenceNumber in IF"+sequenceNumber);
			 for(MOCReccommendation empSessionLink:methodslist)
			 {
				    System.out.println("The Role Keyid");
					String seqNo=sequenceNumber.getSequnceNumber();
					System.out.println("The seqNo::::"+seqNo);
					empSessionLink.setMocrKeyid(seqNo);
					
					sqls.add(MOCReccommendationSql.getInsertSql(mOCReccommendationSql.getmocrDbFields(), empSessionLink.getSaveArray()));
			        System.out.println("The sqls Query:"+sqls);
			 }
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		System.out.println("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return fillValuesReccommendation;
}
@Override
public GenTlActionplanmst createActionPlanRec(GenTlActionplanmst newActionplanmst, GenTlActionplandtl newActionplandtl,
		String mocKeyidRec, String PrrrKeyid) throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlActionplanmstSql genTlActionplanmstSql = new GenTlActionplanmstSql(); 
		GenTlActionplandtlSql genTlActionplandtlSql = new GenTlActionplandtlSql();
		String elementId = newActionplanmst.getAplmElementid();	
	 	String seqIdentf = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlActionplanmstSql.TBL_GEN_TL_ACTIONPLANMST);
		try {
			newActionplanmst.setAplmKeyid(dbActionTemplate.getSequenceNumber(seqIdentf, 10, "AP","", ""));
			newActionplandtl.setApldAplmKeyid(newActionplanmst.getAplmKeyid());
			newActionplanmst.setAplmMasterrefid(mocKeyidRec);
		//	String Momdetailid=dbActionTemplate.getSingleValue("GEN_TL_MOMDTL", "MOMD_KEYID", "MOMD_MOMS_KEYID",newGenTlMommst.getMomsKeyid());
			newActionplanmst.setAplmDetailrefid(PrrrKeyid);
			sqls.add(GenTlActionplanmstSql.getInsertSql(genTlActionplanmstSql.getAplmDbFields(),newActionplanmst.getSaveArray()));
		    List<GenTlActionplandtl> genTlActionplandtls= newActionplandtl.getActionplanlist();
		    if( genTlActionplandtls !=null && genTlActionplandtls.size()>0){
		            for(GenTlActionplandtl genTlActionplandtlss:genTlActionplandtls)
					{
		            	System.out.println("Inside the for Details:::");
		             	genTlActionplandtlss.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,10,"APLD", "", ""));
		            	genTlActionplandtlss.setApldAplmKeyid(newActionplanmst.getAplmKeyid());
		            	genTlActionplandtlss.setApldHowtodo(newActionplandtl.getApldHowtodo());
		              	genTlActionplandtlss.setApldActionplan(newActionplandtl.getApldActionplan());
		            	genTlActionplandtlss.setApldTradeid(newActionplandtl.getApldTradeid());
		            	genTlActionplandtlss.setApldStatus(newActionplandtl.getApldStatus());
		            	genTlActionplandtlss.setApldTargetdate(newActionplandtl.getApldTargetdate());
		            	genTlActionplandtlss.setApldCompleatedon(newActionplandtl.getApldCompleatedon());
		            	genTlActionplandtlss.setApldCompletedby(newActionplandtl.getApldCompletedby());
		            	genTlActionplandtlss.setApldCountermeasure(newActionplandtl.getApldCountermeasure());
		            	genTlActionplandtlss.setApldCreatedby(newActionplandtl.getApldCreatedby());
		            	genTlActionplandtlss.setApldCreatedon(newActionplandtl.getApldCreatedon());
		            	genTlActionplandtlss.setApldRemarks(newActionplandtl.getApldRemarks());
		            	genTlActionplandtlss.setApldOthers(newActionplandtl.getApldOthers());
		            	genTlActionplandtlss.setApldTempfiled2(newActionplandtl.getApldTempfiled2());
		            	genTlActionplandtlss.setApldTempfiled3(newActionplandtl.getApldTempfiled3());
		            	genTlActionplandtlss.setApldTempfiled4(newActionplandtl.getApldTempfiled4());
		            	genTlActionplandtlss.setApldTempfiled5(newActionplandtl.getApldTempfiled5());
		            	genTlActionplandtlss.setApldActive(newActionplandtl.getApldActive());
		            	genTlActionplandtlss.setApldModifiedon(newActionplandtl.getApldModifiedon());
		            	genTlActionplandtlss.setApldResponsibility(genTlActionplandtlss.getApldResponsibility());
						sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtlss.getSaveArray()));// add insert sql for detail table
					} 
		    }
			 else{
			   if (!CommonFunctions.isValidKeyId(newActionplandtl.getApldKeyid())){
				   System.out.println("Inside the else if Details:::");
				   newActionplandtl.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,10, "APLD", "", "")); 
				sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),newActionplandtl.getSaveArray()));
			}}
			sqls.add(GenTlActionplanmstSql.getUpdateStausSql(genTlActionplanmstSql.getAplmDbFields(),newActionplanmst.getSaveArray())); 		
			/*String sql = GenTlActionplanmstSql.getRefDocUpdateSql(genTlActionplanmst);
			if (CommonFunctions.isValidKeyId(sql))
				sqls.add(sql);*/
			dbActionTemplate.executeStatements(sqls); 													
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return newActionplanmst;
}
/*@Override
public MOCReccommendation getUpdateReccommendation(MOCReccommendation newMOCReccommendation,
		MOCReccommendation existMOCReccommendation, String recKeyid, String responsiblity, String actionPlanStatus,
		String targetDate, String mocKeyidRec) throws Exception {
	// TODO Auto-generated method stub
	StringBuilder sql=new StringBuilder();

		sql.append(" UPDATE MOC_TL_RECCOMENDATIONS SET MOCR_RESPONSIBLEID='"+responsiblity+"',MOCR_STATUS='"+actionPlanStatus+"',MOCR_COMPLETEDDATE='"+targetDate+"' WHERE MOCR_KEYID='"+recKeyid+"' ");
		
	
	dbActionTemplate.executeStatement(sql.toString());
		return newMOCReccommendation;
}
*/
@Override
public String getMocCompletionUpdate(String keyId) throws Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCMST", "MOC_RFC_KEYID", "MOC_RFC_KEYID",keyId);
	System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" Update MOC_TL_RFCMST set MOC_RFC_STATUS='MOCA' WHERE MOC_RFC_KEYID ='"+keyId+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
	return sql;
}
@Override
public String getFinalMaxGroupCountStatus(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String FinalMaxRoleNo=dbActionTemplate.getSingleValue("SELECT MAX(MOC_RCM_ROLEID) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_FINALAPPROVAL='Y'  ");
    System.out.println("FinalMaxRoleNo"+FinalMaxRoleNo);
	String FinalMaxGroupStatus=dbActionTemplate.getSingleValue("SELECT MOC_RCM_FASTATUS FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mockeyid+"' AND MOC_RCM_FINALAPPROVAL='Y' AND MOC_RCM_ROLEID='"+FinalMaxRoleNo+"' ");
    System.out.println("FinalMaxGroupStatus:::::::::::::"+FinalMaxGroupStatus);
    return FinalMaxGroupStatus;
}


public String getnextApprovalKeyid(String mocKeyid) throws Exception{
	String NextGroupNo=dbActionTemplate.getSingleValue("SELECT MIN(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mocKeyid+"' AND MOC_RCM_INITIALAPPROVAL='Y' AND MOC_RCM_IASTATUS='-' ");
    System.out.println("NextGroupNo"+NextGroupNo);
    
    String Roleid=dbActionTemplate.getSingleValue("SELECT MIN(MOC_RCM_ROLEID) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_GROUPNUM='"+NextGroupNo+"' AND MOC_RCM_RFCKEYID='"+mocKeyid+"' AND MOC_RCM_IASTATUS NOT IN('A') ");
    System.out.println("Roleid"+Roleid);
    
    String Employeeid=dbActionTemplate.getSingleValue("SELECT MOC_RCM_EMPID FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_GROUPNUM='"+NextGroupNo+"' AND MOC_RCM_RFCKEYID='"+mocKeyid+"' AND MOC_RCM_ROLEID='"+Roleid+"' ");
    System.out.println("Employeeid"+Employeeid);
    
    String EmailId=dbActionTemplate.getSingleValue("SELECT EMPM_EMAIL FROM GEN_TL_EMPLOYEEMST WHERE EMPM_KEYID='"+Employeeid+"' ");
    System.out.println("Initial Approval Email id"+EmailId);
    
    return EmailId;
}


public String getHazopnextApprovalKeyid(String mocKeyid) throws Exception{
	String NextGroupNo=dbActionTemplate.getSingleValue("SELECT MIN(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mocKeyid+"' AND MOC_RCM_HAZOPAPPROVAL='Y' AND MOC_RCM_HZSTATUS='-' ");
    System.out.println("NextGroupNo"+NextGroupNo);
   
    String Roleid=dbActionTemplate.getSingleValue("SELECT MIN(MOC_RCM_ROLEID) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_GROUPNUM='"+NextGroupNo+"' AND MOC_RCM_RFCKEYID='"+mocKeyid+"' AND MOC_RCM_HZSTATUS NOT IN('A')  ");
    System.out.println("Roleid"+Roleid);
    
    String Employeeid=dbActionTemplate.getSingleValue("SELECT MOC_RCM_EMPID FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_GROUPNUM='"+NextGroupNo+"' AND MOC_RCM_RFCKEYID='"+mocKeyid+"' AND MOC_RCM_ROLEID='"+Roleid+"'  ");
    System.out.println("Employeeid"+Employeeid);
    
    String EmailId=dbActionTemplate.getSingleValue("SELECT EMPM_EMAIL FROM GEN_TL_EMPLOYEEMST WHERE EMPM_KEYID='"+Employeeid+"' ");
    System.out.println("Initial Approval Email id"+EmailId);
    
    return EmailId;
}

public String getFinalApprovalKeyid(String mocKeyid) throws Exception{
	String NextGroupNo=dbActionTemplate.getSingleValue("SELECT MIN(MOC_RCM_GROUPNUM) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_RFCKEYID='"+mocKeyid+"' AND MOC_RCM_FINALAPPROVAL='Y' AND MOC_RCM_FASTATUS='-' ");
    System.out.println("NextGroupNo"+NextGroupNo);
   
    String Roleid=dbActionTemplate.getSingleValue("SELECT MIN(MOC_RCM_ROLEID) FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_GROUPNUM='"+NextGroupNo+"' AND MOC_RCM_RFCKEYID='"+mocKeyid+"' AND MOC_RCM_FASTATUS NOT IN('A') ");
    System.out.println("Roleid"+Roleid);
    
    String Employeeid=dbActionTemplate.getSingleValue("SELECT MOC_RCM_EMPID FROM MOC_TL_ROLECONFIGMST WHERE MOC_RCM_GROUPNUM='"+NextGroupNo+"' AND MOC_RCM_RFCKEYID='"+mocKeyid+"' AND MOC_RCM_ROLEID='"+Roleid+"'  ");
    System.out.println("Employeeid"+Employeeid);
    
    String EmailId=dbActionTemplate.getSingleValue("SELECT EMPM_EMAIL FROM GEN_TL_EMPLOYEEMST WHERE EMPM_KEYID='"+Employeeid+"' ");
    System.out.println("Initial Approval Email id"+EmailId);
    
    return EmailId;
}
@Override
public String getWhatifHazopCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String WHCount=dbActionTemplate.getSingleValue("SELECT COUNT(*) FROM moc_tl_reccomendations WHERE MOCR_MOC_KEYID='"+mockeyid+"'  AND MOCR_STATUS='P' ");
    System.out.println("Initial WHCount"+WHCount);	
	return WHCount;
}
@Override
public MOCReccommendation UpdateReccommendation(String mocKeyidRec,
		String detailid, String targetDate, String responsiblity,String actionPlanStatus)
		throws Exception {
	
	System.out.println("StatusNew");
	
	StringBuilder sql=new StringBuilder();
	
	
	System.out.println("StatusNew"+actionPlanStatus);
	
	if(actionPlanStatus.equals("P")){
		 System.out.println("Inside if:::");	
	sql.append(" UPDATE MOC_TL_RECCOMENDATIONS SET MOCR_RESPONSIBLEID='"+responsiblity+"',MOCR_STATUS='"+actionPlanStatus+"',MOCR_TARGETDATE='"+targetDate+"' WHERE MOCR_WH_KEYID='"+detailid+"' ");
	   System.out.println("Print Initial:::"+sql);	
	   popSqlsForHazopActionplanStatusUpdatePending(mocKeyidRec,detailid,targetDate,responsiblity,actionPlanStatus) ;
	}
	else {
		System.out.println("Update:::");	
		sql.append(" UPDATE MOC_TL_RECCOMENDATIONS SET MOCR_RESPONSIBLEID='"+responsiblity+"',MOCR_STATUS='"+actionPlanStatus+"',MOCR_COMPLETEDDATE='"+targetDate+"' WHERE MOCR_WH_KEYID='"+detailid+"' ");
		System.out.println("Update:::"+sql);	
	    popSqlsForHazopActionplanStatusUpdate(mocKeyidRec,detailid,responsiblity,targetDate);	
	}
	
dbActionTemplate.executeStatement(sql.toString());
	return null;
}

private void popSqlsForHazopActionplanStatusUpdatePending(String mocKeyidRec,
		String detailid,String targetDate,String responsiblity,String actionPlanStatus) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String sql="";
	String ActionPlanMSt=dbActionTemplate.getSingleValue("SELECT APLM_KEYID FROM GEN_TL_ACTIONPLANMST WHERE APLM_DETAILREFID='"+detailid+"' ");
	   
	System.out.println(" ActionPlanMSt"+ActionPlanMSt);
	
	 sql=" Update GEN_TL_ACTIONPLANMST set APLM_STATUS='"+actionPlanStatus+"' WHERE APLM_MASTERREFID ='"+mocKeyidRec+"' and APLM_DETAILREFID='"+detailid+"' ";
	 System.out.println("SQL qc detail:::"+sql);
	 sql="update gen_tl_actionplandtl set apld_status='"+actionPlanStatus+"',APLD_RESPONSIBILITY='"+responsiblity+"',APLD_TARGETDATE='"+targetDate+"' where apld_aplm_keyid='"+ActionPlanMSt+"' ";
	 System.out.println("Update:::"+sql);
	 dbActionTemplate.executeStatement(sql.toString());
}

private void popSqlsForHazopActionplanStatusUpdate(String mocKeyidRec,
		String detailid,String targetDate,String responsiblity) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String sql="";
	String ActionPlanMSt=dbActionTemplate.getSingleValue("SELECT APLM_KEYID FROM GEN_TL_ACTIONPLANMST WHERE APLM_DETAILREFID='"+detailid+"' ");
	   
	System.out.println(" ActionPlanMSt"+ActionPlanMSt);
	
	 sql=" Update GEN_TL_ACTIONPLANMST set APLM_STATUS='C' WHERE APLM_MASTERREFID ='"+mocKeyidRec+"' and APLM_DETAILREFID='"+detailid+"' ";
	 System.out.println("SQL qc detail:::"+sql);
	 sql="update gen_tl_actionplandtl set apld_status='C',APLD_COMPLETEDBY='"+targetDate+"',APLD_COMPLEATEDON='"+responsiblity+"' where apld_aplm_keyid='"+ActionPlanMSt+"' ";
	 System.out.println("Update:::"+sql);
	 dbActionTemplate.executeStatement(sql.toString());
}
@Override
public MOCPssrReccommend UpdatePssrReccommendation(String mocKeyidRec,
		String psrrKeyId, String targetDate, String responsiblity,
		String actionPlanStatus) throws Exception {
	// TODO Auto-generated method stub
	StringBuilder sql=new StringBuilder();

	sql.append(" UPDATE PSSR_TL_RECCOMENDATIONS SET PSRR_RESPONSIBLEID='"+responsiblity+"',PSRR_STATUS='"+actionPlanStatus+"',PSRR_COMPLETEDDATE='"+targetDate+"' WHERE PSRR_KEYID='"+psrrKeyId+"' ");
	   System.out.println("Update PSSRInitial:::"+sql);	
	   popSqlsForActionplanStatusUpdate(mocKeyidRec) ;
		
dbActionTemplate.executeStatement(sql.toString());
	return null;
}
private void popSqlsForActionplanStatusUpdate(String mocKeyidRec) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	//String detailid=dbActionTemplate.getSingleValue("MOC_TL_RFCMST", "MOC_RFC_KEYID", "MOC_RFC_KEYID",mocKeyid);
	//System.out.println("KKKKKKK:::"+detailid);
	System.out.println(" delete Dtl.toString()== "+ sf.toString());
	String sql=" Update GEN_TL_ACTIONPLANMST set APLM_STATUS='C' WHERE APLM_MASTERREFID ='"+mocKeyidRec+"' ";
	System.out.println("SQL qc detail:::"+sql);
	dbActionTemplate.executeStatement(sql.toString());
	
}
@Override
public List<MOCPssrReccommend> createPSSRCheckReccommendation(
		List<MOCPssrReccommend> fillValuesPSSRCHECKReccommendation, String mocKeyidRec, String detailid)
				throws Exception {
	MOCPssrReccommendSql PssrReccommendationSql = new MOCPssrReccommendSql(); // contains dbtable,field names, Field types and related sqls  of master table
	try
	{
		     List<String> sqls = new ArrayList<String>();
		     List <MOCPssrReccommend> methodslist = fillValuesPSSRCHECKReccommendation;
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),MOCPssrReccommendSql.TBL_PSSR_TL_RECCOMENDATIONS,15,"PSRR", null,null);
			 System.out.println("sequenceNumber in IF"+sequenceNumber);
			 for(MOCPssrReccommend empSessionLink:methodslist)
			 {
				    System.out.println("The Role Keyid");
					String seqNo=sequenceNumber.getSequnceNumber();
					System.out.println("The seqNo::::"+seqNo);
					empSessionLink.setPsrrKeyid(seqNo);
					
					sqls.add(MOCPssrReccommendSql.getInsertSql(PssrReccommendationSql.getpsrrDbFields(), empSessionLink.getSaveArray()));
			        System.out.println("The sqls Query:"+sqls);
			 }
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		System.out.println("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return fillValuesPSSRCHECKReccommendation;
}
@Override
public MOCPssrReccommend UpdatePSSRReccommendation(String mocKeyidRec, String detailid, String targetDate,
		String responsiblity, String actionPlanStatus,String reccommendation) throws Exception {
	
	System.out.println("StatusNew");
	
	StringBuilder sql=new StringBuilder();
	
	
	System.out.println("actionPlanStatus"+actionPlanStatus);
	System.out.println("detailid"+detailid);
	System.out.println("mocKeyidRec"+mocKeyidRec);
	System.out.println("responsiblity"+responsiblity);
	System.out.println("targetDate"+targetDate);
	System.out.println("mocKeyidRec"+reccommendation);
	
	
	if(actionPlanStatus.equals("P")){
		 System.out.println("Inside if:::");	
	sql.append(" UPDATE PSSR_TL_RECCOMENDATIONS  SET PSRR_RESPONSIBLEID='"+responsiblity+"',PSRR_STATUS='"+actionPlanStatus+"',PSRR_RECOMMEND='"+reccommendation+"',PSRR_TARGETDATE='"+targetDate+"' WHERE PSRR_KEYID='"+detailid+"' ");
	   System.out.println("Print Initial:::"+sql);	
	   popSqlsForPSRRRecActionplanStatusUpdatePending(mocKeyidRec,detailid,targetDate,responsiblity,actionPlanStatus, reccommendation) ;
	}
	else {
		System.out.println("Update:::");	
		sql.append(" UPDATE PSSR_TL_RECCOMENDATIONS SET PSRR_RESPONSIBLEID='"+responsiblity+"',PSRR_RECOMMEND='"+reccommendation+"',PSRR_STATUS='"+actionPlanStatus+"',PSRR_COMPLETEDDATE='"+targetDate+"' WHERE PSRR_KEYID='"+detailid+"' ");
		System.out.println("Update:::"+sql);	
		popSqlsForPSSRRecActionplanStatusUpdate(mocKeyidRec,detailid,responsiblity,targetDate,reccommendation);	
	}
	
dbActionTemplate.executeStatement(sql.toString());
	return null;
}

private void popSqlsForPSRRRecActionplanStatusUpdatePending(String mocKeyidRec,
		String detailid,String targetDate,String responsiblity,String actionPlanStatus,String reccommendation) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	System.out.println("APactionPlanStatus"+actionPlanStatus);
	System.out.println("APdetailid"+detailid);
	System.out.println("APmocKeyidRec"+mocKeyidRec);
	System.out.println("APresponsiblity"+responsiblity);
	System.out.println("APtargetDate"+targetDate);
	System.out.println("APmocKeyidRec"+reccommendation);
	String sql="";
	String ActionPlanMSt=dbActionTemplate.getSingleValue("SELECT APLM_KEYID FROM GEN_TL_ACTIONPLANMST WHERE APLM_DETAILREFID='"+detailid+"' ");
	   
	System.out.println(" ActionPlanMSt"+ActionPlanMSt);
	
	 sql=" Update GEN_TL_ACTIONPLANMST set APLM_STATUS='"+actionPlanStatus+"', APLM_MAINTASK='"+reccommendation+"' WHERE APLM_MASTERREFID ='"+mocKeyidRec+"'  and APLM_DETAILREFID='"+detailid+"' ";
	 System.out.println("SQL qc detail:::"+sql);
	 sql="update gen_tl_actionplandtl set apld_status='"+actionPlanStatus+"',APLD_RESPONSIBILITY='"+responsiblity+"', APLD_ACTIONPLAN='"+reccommendation+"',APLD_TARGETDATE='"+targetDate+"' where apld_aplm_keyid='"+ActionPlanMSt+"' ";
	 System.out.println("Update:::"+sql);
	 dbActionTemplate.executeStatement(sql.toString());
}

private void popSqlsForPSSRRecActionplanStatusUpdate(String mocKeyidRec,
		String detailid,String targetDate,String responsiblity,String reccommendation) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	StringBuffer sf = new StringBuffer();
	String sql="";
	String ActionPlanMSt=dbActionTemplate.getSingleValue("SELECT APLM_KEYID FROM GEN_TL_ACTIONPLANMST WHERE APLM_DETAILREFID='"+detailid+"' ");
	   
	System.out.println(" ActionPlanMSt"+ActionPlanMSt);
	
	 sql=" Update GEN_TL_ACTIONPLANMST set APLM_STATUS='C' , APLM_MAINTASK='"+reccommendation+"' WHERE APLM_MASTERREFID ='"+mocKeyidRec+"'and APLM_DETAILREFID='"+detailid+"' ";
	 System.out.println("SQL qc detail:::"+sql);
	 sql="update gen_tl_actionplandtl set apld_status='C',APLD_COMPLETEDBY='"+targetDate+"', APLD_ACTIONPLAN='"+reccommendation+"',APLD_COMPLEATEDON='"+responsiblity+"' where apld_aplm_keyid='"+ActionPlanMSt+"' ";
	 System.out.println("Update:::"+sql);
	 dbActionTemplate.executeStatement(sql.toString());
}
/*@Override
public MOCPssrReccommend UpdatePSSRReccommendation(String mocKeyidRec, String detailid, String targetDate,
		String responsiblity, String actionPlanStatus) throws Exception {
	// TODO Auto-generated method stub
	return null;
}*/
@Override
public String getClosureCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String  ClosureCount= null;
   System.out.println("In Closure Count"+mockeyid);
	 ClosureCount=dbActionTemplate.getSingleValue("SELECT count(*) FROM MOC_TL_CLOSUREMASTER WHERE MOCL_RFC_KEYID='"+mockeyid+"' ");
    System.out.println("Closure Count"+ClosureCount);	
   
    
	return ClosureCount;
}
//@Override
//public List<String[]> MOCWorkflow(String flid, String mocKeyid, String mOCDate, String jH, String dMT, String format,
//		String path) throws Exception {
//	// TODO Auto-generated method stub
//
//		 StringBuilder sql=new StringBuilder();
//		
//		 sql.append("select ROLM_NAME , MOC_RCM_INITIALAPPROVAL ,MOC_RCM_HAZOPAPPROVAL ,MOC_RCM_FINALAPPROVAL ,EMPM_NAME ");
//		 sql.append("  FROM MOC_TL_ROLEMST, MOC_TL_ROLECONFIGMST, GEN_TL_EMPLOYEEMST WHERE     1 = 1");
//		 sql.append(" AND ROLM_KEYID = MOC_RCM_ROLEID(+) AND EMPM_KEYID(+) = MOC_RCM_EMPID AND moc_rcm_rfckeyid(+) = '"+mocKeyid+"' ");
//		 sql.append("ORDER BY ROLM_KEYID"); 
//		 System.out.println("The MOC WorkFlow :::::"+sql.toString());
//		  List<String[]> MOCWorkFlow=dbActionTemplate.getDataList(sql.toString());
//		  return MOCWorkFlow;
//	}
//
//public List<String[]> MOCWorkflow(String mocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//
//		 StringBuilder sql=new StringBuilder();
//		
//		 sql.append("select ROLM_NAME , MOC_RCM_INITIALAPPROVAL ,MOC_RCM_HAZOPAPPROVAL ,MOC_RCM_FINALAPPROVAL ,EMPM_NAME ");
//		 sql.append("  FROM MOC_TL_ROLEMST, MOC_TL_ROLECONFIGMST, GEN_TL_EMPLOYEEMST WHERE     1 = 1");
//		 sql.append(" AND ROLM_KEYID = MOC_RCM_ROLEID(+) AND EMPM_KEYID(+) = MOC_RCM_EMPID AND moc_rcm_rfckeyid(+) = '"+mocKeyid+"' ");
//		 sql.append("ORDER BY ROLM_KEYID"); 
//		 System.out.println("The MOC WorkFlow :::::"+sql.toString());
//		  List<String[]> MOCWorkFlow=dbActionTemplate.getDataList(sql.toString());
//		  return MOCWorkFlow;
//	}
@Override
public List<String[]> MOCWorkflow(String flid, String mocKeyid, String mOCDate, String jH, String dMT, String format,
        String path) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT ROLM_NAME, MOC_RCM_INITIALAPPROVAL, MOC_RCM_HAZOPAPPROVAL, MOC_RCM_FINALAPPROVAL, EMPM_NAME ");
    sql.append("FROM MOC_TL_ROLEMST ");
    sql.append("LEFT JOIN MOC_TL_ROLECONFIGMST ON ROLM_KEYID = MOC_RCM_ROLEID ");
    sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST ON EMPM_KEYID = MOC_RCM_EMPID ");
    sql.append("WHERE MOC_RCM_RFCKEYID = '" + mocKeyid + "' ");
    sql.append("ORDER BY ROLM_KEYID");

    System.out.println("The MOC WorkFlow :::::" + sql.toString());
    List<String[]> MOCWorkFlow = dbActionTemplate.getDataList(sql.toString());
    return MOCWorkFlow;
}


public List<String[]> MOCWorkflow(String mocKeyid) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT ROLM_NAME, MOC_RCM_INITIALAPPROVAL, MOC_RCM_HAZOPAPPROVAL, MOC_RCM_FINALAPPROVAL, EMPM_NAME ");
    sql.append("FROM MOC_TL_ROLEMST ");
    sql.append("LEFT JOIN MOC_TL_ROLECONFIGMST ON ROLM_KEYID = MOC_RCM_ROLEID ");
    sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST ON EMPM_KEYID = MOC_RCM_EMPID ");
    sql.append("WHERE MOC_RCM_RFCKEYID = '" + mocKeyid + "' ");
    sql.append("ORDER BY ROLM_KEYID");

    System.out.println("The MOC WorkFlow :::::" + sql.toString());
    List<String[]> MOCWorkFlow = dbActionTemplate.getDataList(sql.toString());
    return MOCWorkFlow;
}

@Override
public String getDmtName(String dMTId) throws Exception {
	// TODO Auto-generated method stub
	   String sql="SELECT SECT_NAME FROM GEN_TL_SECTIONMST WHERE SECT_KEYID='"+dMTId+"' ";
			System.out.println("Sql:"+sql);
			return dbActionTemplate.getSingleValue(sql);
}
@Override
public String getJHName(String jHId) throws Exception {
	// TODO Auto-generated method stub
	   String sql="SELECT CELL_NAME FROM GEN_TL_CELLMST WHERE CELL_KEYID='"+jHId+"' ";
			System.out.println("Sql:"+sql);
			return dbActionTemplate.getSingleValue(sql);
}
//@Override
//public List<String[]> RFC(String mocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//	 StringBuilder sql=new StringBuilder();
//	
//	 sql.append("select MOCB_NAME");
//	 sql.append(" from MOC_TL_MOCBASIS,MOC_TL_RFCBASIS");
//	 sql.append("  where 1=1 and   MOC_BAM_BASISID(+)=MOCB_KEYID AND MOC_BAM_RFCKEYID ='"+mocKeyid+"' ");
//	 sql.append("ORDER BY  MOCB_KEYID"); 
//	 System.out.println("The Basis :::::"+sql.toString());
//	  List<String[]> RFC=dbActionTemplate.getDataList(sql.toString());
//	  return RFC;
//}

@Override
public List<String[]> RFC(String mocKeyid) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT MOCB_NAME ");
    sql.append("FROM MOC_TL_MOCBASIS ");
    sql.append("LEFT JOIN MOC_TL_RFCBASIS ON MOC_BAM_BASISID = MOCB_KEYID ");
    sql.append("WHERE MOC_BAM_RFCKEYID = '" + mocKeyid + "' ");
    sql.append("ORDER BY MOCB_KEYID");

    System.out.println("The Basis :::::" + sql.toString());
    List<String[]> RFC = dbActionTemplate.getDataList(sql.toString());
    return RFC;
}

//@Override
//public List<String[]> Question(String mocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//	 StringBuilder sql=new StringBuilder();
//		
//	 sql.append("select DECODE(MOC_RFQ_RESPONSE,'Y','Yes','N','No','','-'),MOC_RQM_QUESTIONS ");
//	 sql.append(" FROM  MOC_TL_RFCQST,MOC_RL_RFCQSTMST WHERE  1=1 ");
//	 sql.append("   and  MOC_QRQM_KEYID(+) = MOC_RQM_KEYID  AND MOC_QRFC_KEYID(+) ='"+mocKeyid+"'  ");
//	 sql.append("ORDER BY  MOC_RQM_KEYID ASC "); 
//	 System.out.println("The  questionaire :::::"+sql.toString());
//	  List<String[]> Question=dbActionTemplate.getDataList(sql.toString());
//	  return Question;
//}

@Override
public List<String[]> Question(String mocKeyid) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT CASE MOC_RFQ_RESPONSE WHEN 'Y' THEN 'Yes' WHEN 'N' THEN 'No' WHEN '' THEN '-' ELSE '-' END, MOC_RQM_QUESTIONS ");
    sql.append("FROM MOC_RL_RFCQSTMST ");
    sql.append("LEFT JOIN MOC_TL_RFCQST ON MOC_QRQM_KEYID = MOC_RQM_KEYID AND MOC_QRFC_KEYID = '" + mocKeyid + "' ");
    sql.append("ORDER BY MOC_RQM_KEYID ASC");

    System.out.println("The questionaire :::::" + sql.toString());
    List<String[]> Question = dbActionTemplate.getDataList(sql.toString());
    return Question;
}

//@Override
//public List<String[]> WhatIf(String mocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//	 StringBuilder sql=new StringBuilder();
//		
//	 sql.append(" SELECT WIFD_WHATIF ,WIFD_CAUSES ,WIFD_CONSEQUENCES ,WIFD_WITHOUTSAFEGUARDS ,PRBM_CODE ,SIVM_CODE ,TO_CHAR (WIFD_RISK1) ,WIFD_RECOMMENDATIONS ,PRBM_CODE ,SIVM_CODE ,TO_CHAR (WIFD_RISK2) ,WIFD_REMARKS");
//	 sql.append(" FROM MOC_TL_WHATIFMST,MOC_TL_WHATIFDTL, SHE_TL_PROBABLITYMST,SHE_TL_SEVIORITYMST");
//	 sql.append("   WHERE     1 = 1 AND WIFD_LIKEHOOD1 = PRBM_KEYID AND WIFD_SEVERITY1 = SIVM_KEYID AND WIFM_KEYID = WIFD_WIFM_KEYID AND WIFM_MOCM_KEYID = '"+mocKeyid+"' ");
//	 sql.append("ORDER BY   wifd_keyid  ASC "); 
//	 System.out.println("The  WhatIF :::::"+sql.toString());
//	  List<String[]> WhatIf=dbActionTemplate.getDataList(sql.toString());
//	  return WhatIf;
//}

@Override
public List<String[]> WhatIf(String mocKeyid) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT WIFD_WHATIF, WIFD_CAUSES, WIFD_CONSEQUENCES, WIFD_WITHOUTSAFEGUARDS, ");
    sql.append("PRBM_CODE, SIVM_CODE, CAST(WIFD_RISK1 AS TEXT), WIFD_RECOMMENDATIONS, ");
    sql.append("PRBM_CODE, SIVM_CODE, CAST(WIFD_RISK2 AS TEXT), WIFD_REMARKS ");
    sql.append("FROM MOC_TL_WHATIFMST ");
    sql.append("JOIN MOC_TL_WHATIFDTL ON WIFM_KEYID = WIFD_WIFM_KEYID ");
    sql.append("JOIN SHE_TL_PROBABLITYMST ON WIFD_LIKEHOOD1 = PRBM_KEYID ");
    sql.append("JOIN SHE_TL_SEVIORITYMST ON WIFD_SEVERITY1 = SIVM_KEYID ");
    sql.append("WHERE WIFM_MOCM_KEYID = '" + mocKeyid + "' ");
    sql.append("ORDER BY WIFD_KEYID ASC");

    System.out.println("The WhatIF :::::" + sql.toString());
    List<String[]> WhatIf = dbActionTemplate.getDataList(sql.toString());
    return WhatIf;
}


//@Override
//public List<String[]> Hazop(String mocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//	 StringBuilder sql=new StringBuilder();
//		
//	 sql.append("SELECT MGWM_NAME ,MOHD_PARAMETER ,MOHD_DEVIATION ,MOHD_CAUSES ,MOHD_CONSEQUENCES ,MOHD_WITHOUTSAFEGUARDS , PRBM_CODE cmbMohdLikeHood1,SIVM_CODE ,TO_CHAR(MOHD_RISK1) ,MOHD_RECOMMENDATIONS ,PRBM_CODE ,SIVM_CODE ,TO_CHAR(MOHD_RISK2), MOHD_REMARKS");
//	 sql.append(" FROM MOC_TL_HAZOPMST,MOC_TL_HAZOPDTL,SHE_TL_PROBABLITYMST,SHE_TL_SEVIORITYMST,MOC_TL_GUIDEWORDMST  WHERE 1=1");
//	 sql.append("     AND MOHD_LIKEHOOD1=PRBM_KEYID AND MOHD_SEVERITY1= SIVM_KEYID AND HZOM_KEYID=MOHD_MOHM_KEYID AND MOHD_GUIDEWORD=MGWM_KEYID(+)AND HZOM_MOCM_KEYID='"+mocKeyid+"' ");
//	 sql.append("ORDER BY HZOM_KEYID  ASC "); 
//	 System.out.println("The  Hazop :::::"+sql.toString());
//	  List<String[]> Hazop=dbActionTemplate.getDataList(sql.toString());
//	  return Hazop;
//}

@Override
public List<String[]> Hazop(String mocKeyid) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT MGWM_NAME, MOHD_PARAMETER, MOHD_DEVIATION, MOHD_CAUSES, MOHD_CONSEQUENCES, MOHD_WITHOUTSAFEGUARDS, ");
    sql.append("PRBM_CODE AS cmbMohdLikeHood1, SIVM_CODE, CAST(MOHD_RISK1 AS TEXT), MOHD_RECOMMENDATIONS, ");
    sql.append("PRBM_CODE, SIVM_CODE, CAST(MOHD_RISK2 AS TEXT), MOHD_REMARKS ");
    sql.append("FROM MOC_TL_HAZOPMST ");
    sql.append("JOIN MOC_TL_HAZOPDTL ON HZOM_KEYID = MOHD_MOHM_KEYID ");
    sql.append("JOIN SHE_TL_PROBABLITYMST ON MOHD_LIKEHOOD1 = PRBM_KEYID ");
    sql.append("JOIN SHE_TL_SEVIORITYMST ON MOHD_SEVERITY1 = SIVM_KEYID ");
    sql.append("LEFT JOIN MOC_TL_GUIDEWORDMST ON MOHD_GUIDEWORD = MGWM_KEYID ");
    sql.append("WHERE HZOM_MOCM_KEYID = '" + mocKeyid + "' ");
    sql.append("ORDER BY HZOM_KEYID ASC");

    System.out.println("The Hazop :::::" + sql.toString());
    List<String[]> Hazop = dbActionTemplate.getDataList(sql.toString());
    return Hazop;
}
//public List<String[]> Hazop(String mocKeyid) throws Exception {
//    StringBuilder sql = new StringBuilder();
//
//    sql.append("SELECT MGWM_NAME, MOHD_PARAMETER, MOHD_DEVIATION, MOHD_CAUSES, MOHD_CONSEQUENCES, ");
//    sql.append("       MOHD_WITHOUTSAFEGUARDS, ");
//    sql.append("       P1.PRBM_CODE AS cmbMohdLikeHood1, ");   // Likelihood 1
//    sql.append("       S1.SIVM_CODE AS cmbMohdSeverity1, ");    // Severity 1
//    sql.append("       TO_CHAR(MOHD_RISK1), ");
//    sql.append("       MOHD_RECOMMENDATIONS, ");
//    sql.append("       P2.PRBM_CODE AS cmbMohdLikeHood2, ");   // Likelihood 2
//    sql.append("       S2.SIVM_CODE AS cmbMohdSeverity2, ");    // Severity 2
//    sql.append("       TO_CHAR(MOHD_RISK2), ");
//    sql.append("       MOHD_REMARKS ");
//    sql.append("FROM MOC_TL_HAZOPMST ");
//    sql.append("JOIN MOC_TL_HAZOPDTL      ON HZOM_KEYID = MOHD_MOHM_KEYID ");
//    sql.append("JOIN SHE_TL_PROBABLITYMST P1 ON MOHD_LIKEHOOD1 = P1.PRBM_KEYID ");  // Risk1 Likelihood
//    sql.append("JOIN SHE_TL_SEVIORITYMST  S1 ON MOHD_SEVERITY1 = S1.SIVM_KEYID ");  // Risk1 Severity
//    sql.append("JOIN SHE_TL_PROBABLITYMST P2 ON MOHD_LIKEHOOD2 = P2.PRBM_KEYID ");  // Risk2 Likelihood
//    sql.append("JOIN SHE_TL_SEVIORITYMST  S2 ON MOHD_SEVERITY2 = S2.SIVM_KEYID ");  // Risk2 Severity
//    sql.append("LEFT JOIN MOC_TL_GUIDEWORDMST ON MOHD_GUIDEWORD = MGWM_KEYID ");
//    sql.append("WHERE HZOM_MOCM_KEYID = '" + mocKeyid + "' ");
//    sql.append("ORDER BY HZOM_KEYID ASC ");
//
//    System.out.println("The Hazop SQL: " + sql.toString());
//
//    List<String[]> hazop = dbActionTemplate.getDataList(sql.toString());
//    return hazop;
//}

//@Override
//public List<String[]> WHReccommend(String mocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//	 StringBuilder sql=new StringBuilder();
//		
//	 sql.append("SELECT DISTINCT 'WhatIf' ,WIFD_WHATIF ,EMPM_NAME ,TO_CHAR(MOCR_TARGETDATE,'DD-MON-YYYY') ,MOCR_STATUS ,TO_CHAR(MOCR_COMPLETEDDATE,'DD-MON-YYYY') ,aplm_keyid");
//	 sql.append(" FROM  MOC_TL_WHATIFMST,MOC_TL_WHATIFDTL,GEN_TL_EMPLOYEEMST,GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL,MOC_TL_RECCOMENDATIONS ");
//	 sql.append("  WHERE  1=1 AND EMPM_KEYID(+)=APLD_RESPONSIBILITY AND APLM_DETAILREFID(+)= WIFD_KEYID AND APLD_APLM_KEYID(+)=APLM_KEYID AND WIFM_KEYID=WIFD_WIFM_KEYID and MOCR_WH_KEYID(+)=WIFD_KEYID and MOCR_MOC_KEYID(+)=WIFM_MOCM_KEYID  AND MOCR_RESPONSIBLEID=EMPM_KEYID(+) AND WIFD_RECOMMENDATIONS NOT IN('-')  AND WIFM_MOCM_KEYID ='"+mocKeyid+"'");
//	 sql.append("UNION "); 
//	 sql.append("SELECT DISTINCT 'Hazop',MOHD_RECOMMENDATIONS ,EMPM_NAME ,TO_CHAR(MOCR_TARGETDATE,'DD-MON-YYYY') ,MOCR_STATUS,TO_CHAR(APLD_COMPLEATEDON,'DD-MON-YYYY'),aplm_keyid");
//	 sql.append(" FROM  moc_tl_hazopdtl,moc_tl_hazopmst,GEN_TL_EMPLOYEEMST,GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL,MOC_TL_RECCOMENDATIONS ");
//	 sql.append("  WHERE  1=1 AND EMPM_KEYID(+)=APLD_RESPONSIBILITY  AND APLM_DETAILREFID(+) = MOHD_KEYID AND APLD_APLM_KEYID(+)=APLM_KEYID and MOCR_WH_KEYID(+)=MOHD_KEYID and MOCR_MOC_KEYID(+)=HZOM_MOCM_KEYID AND HZOM_KEYID=MOHD_MOHM_KEYID(+) AND MOCR_RESPONSIBLEID=EMPM_KEYID(+) AND MOHD_RECOMMENDATIONS NOT IN('-') AND HZOM_MOCM_KEYID ='"+mocKeyid+"'");
//	
//	 System.out.println("The  WhatIF Reccommend :::::"+sql.toString());
//	  List<String[]> HzReccommend=dbActionTemplate.getDataList(sql.toString());
//	  return HzReccommend;
//}
@Override
public List<String[]> WHReccommend(String mocKeyid) throws Exception {

    StringBuilder sql = new StringBuilder();

    // WhatIf part
    sql.append("SELECT DISTINCT 'WhatIf', WIFD_WHATIF, EMPM_NAME, ");
    sql.append("TO_CHAR(MOCR_TARGETDATE, 'DD-Mon-YYYY'), MOCR_STATUS, ");
    sql.append("TO_CHAR(MOCR_COMPLETEDDATE, 'DD-Mon-YYYY'), APLM_KEYID ");
    sql.append("FROM MOC_TL_WHATIFMST ");
    sql.append("JOIN MOC_TL_WHATIFDTL ON WIFM_KEYID = WIFD_WIFM_KEYID ");
    sql.append("LEFT JOIN GEN_TL_ACTIONPLANMST ON APLM_DETAILREFID = WIFD_KEYID ");
    sql.append("LEFT JOIN GEN_TL_ACTIONPLANDTL ON APLD_APLM_KEYID = APLM_KEYID ");
    sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST ON EMPM_KEYID = APLD_RESPONSIBILITY ");
    sql.append("LEFT JOIN MOC_TL_RECCOMENDATIONS ON MOCR_WH_KEYID = WIFD_KEYID ");
    sql.append("AND MOCR_MOC_KEYID = WIFM_MOCM_KEYID ");
    sql.append("AND MOCR_RESPONSIBLEID = EMPM_KEYID ");
    sql.append("WHERE WIFD_RECOMMENDATIONS NOT IN ('-') ");
    sql.append("AND WIFM_MOCM_KEYID = '" + mocKeyid + "' ");

    sql.append("UNION ");

    // Hazop part
    sql.append("SELECT DISTINCT 'Hazop', MOHD_RECOMMENDATIONS, EMPM_NAME, ");
    sql.append("TO_CHAR(MOCR_TARGETDATE, 'DD-Mon-YYYY'), MOCR_STATUS, ");
    sql.append("TO_CHAR(APLD_COMPLEATEDON, 'DD-Mon-YYYY'), APLM_KEYID ");
    sql.append("FROM MOC_TL_HAZOPMST ");
    sql.append("LEFT JOIN MOC_TL_HAZOPDTL ON HZOM_KEYID = MOHD_MOHM_KEYID ");
    sql.append("LEFT JOIN GEN_TL_ACTIONPLANMST ON APLM_DETAILREFID = MOHD_KEYID ");
    sql.append("LEFT JOIN GEN_TL_ACTIONPLANDTL ON APLD_APLM_KEYID = APLM_KEYID ");
    sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST ON EMPM_KEYID = APLD_RESPONSIBILITY ");
    sql.append("LEFT JOIN MOC_TL_RECCOMENDATIONS ON MOCR_WH_KEYID = MOHD_KEYID ");
    sql.append("AND MOCR_MOC_KEYID = HZOM_MOCM_KEYID ");
    sql.append("AND MOCR_RESPONSIBLEID = EMPM_KEYID ");
    sql.append("WHERE MOHD_RECOMMENDATIONS NOT IN ('-') ");
    sql.append("AND HZOM_MOCM_KEYID = '" + mocKeyid + "'");

    System.out.println("The WhatIF Reccommend :::::" + sql.toString());
    List<String[]> HzReccommend = dbActionTemplate.getDataList(sql.toString());
    return HzReccommend;
}

//@Override
//public List<String[]> PssrCheck(String mocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//	 StringBuilder sql=new StringBuilder();
//		
//	 sql.append("select MOC_PCM_AREA,MOC_PCM_CHECKPOINT ,PSRD_OBSERVATION ");
//	 sql.append("   FROM  MOC_TL_PSSRCHKLISTMST,MOC_TL_PSSRCHECKLISTdtl WHERE  1=1");
//	 sql.append("    AND MOC_PCM_KEYID=PSRD_MOC_PCM_KEYID(+) AND PSRD_RFCM_KEYID(+) ='"+mocKeyid+"' ");
//	 sql.append("ORDER BY PSRD_KEYID   ASC "); 
//	 System.out.println("The  CheckList :::::"+sql.toString());
//	  List<String[]> CheckList=dbActionTemplate.getDataList(sql.toString());
//	  return CheckList;
//}
@Override
public List<String[]> PssrCheck(String mocKeyid) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT MOC_PCM_AREA, MOC_PCM_CHECKPOINT, PSRD_OBSERVATION ");
    sql.append("FROM MOC_TL_PSSRCHKLISTMST ");
    sql.append("LEFT JOIN MOC_TL_PSSRCHECKLISTDTL ON MOC_PCM_KEYID = PSRD_MOC_PCM_KEYID ");
    sql.append("AND PSRD_RFCM_KEYID = '" + mocKeyid + "' ");
    sql.append("ORDER BY PSRD_KEYID ASC");

    System.out.println("The CheckList :::::" + sql.toString());
    List<String[]> CheckList = dbActionTemplate.getDataList(sql.toString());
    return CheckList;
}


//@Override
//public List<String[]> PssrReccommend(String mocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//	 StringBuilder sql=new StringBuilder();
//		
//	 sql.append("SELECT distinct PSRR_RECOMMEND,PSRR_CATEGORY ,EMPM_NAME ,TO_CHAR(PSRR_TARGETDATE,'DD-MON-YYYY') ,PSRR_STATUS,TO_CHAR(PSRR_COMPLETEDDATE,'DD-MON-YYYY') ,aplm_keyid ");
//	 sql.append(" FROM  PSSR_TL_RECCOMENDATIONS,GEN_TL_EMPLOYEEMST ,gen_tl_actionplanmst ");
//	 sql.append("WHERE  1=1 AND EMPM_KEYID(+)=PSRR_RESPONSIBLEID and aplm_detailrefid= PSRR_KEYID AND PSRR_MOC_KEYID ='"+mocKeyid+"' ");
//	 System.out.println("The  PssrReccommend :::::"+sql.toString());
//	  List<String[]> PssrReccommend=dbActionTemplate.getDataList(sql.toString());
//	  return PssrReccommend;
//
//}
@Override
public List<String[]> PssrReccommend(String mocKeyid) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT DISTINCT PSRR_RECOMMEND, PSRR_CATEGORY, EMPM_NAME, ");
    sql.append("TO_CHAR(PSRR_TARGETDATE, 'DD-Mon-YYYY'), PSRR_STATUS, ");
    sql.append("TO_CHAR(PSRR_COMPLETEDDATE, 'DD-Mon-YYYY'), APLM_KEYID ");
    sql.append("FROM PSSR_TL_RECCOMENDATIONS ");
    sql.append("JOIN GEN_TL_ACTIONPLANMST ON APLM_DETAILREFID = PSRR_KEYID ");
    sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST ON EMPM_KEYID = PSRR_RESPONSIBLEID ");
    sql.append("WHERE PSRR_MOC_KEYID = '" + mocKeyid + "'");

    System.out.println("The PssrReccommend :::::" + sql.toString());
    List<String[]> PssrReccommend = dbActionTemplate.getDataList(sql.toString());
    return PssrReccommend;
}

//@Override
//public List<String[]> MOCClosure(String mocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//	 StringBuilder sql=new StringBuilder();
//	 sql.append("select DECODE(MOCL_RESPONSEY,'Y','Y','N','-'),MOC_CLQ_QUESTIONS ");
//	
//	 sql.append("   FROM  MOC_TL_CLOSUREMASTER,MOC_TL_CLOSUREQSTMST WHERE  1=1   and  MOC_CLQ_KEYID = MOCL_CLQ_KEYID(+) AND MOCL_RFC_KEYID(+)='"+mocKeyid+"'  ");
//	 sql.append("ORDER BY   MOC_CLQ_KEYID ASC "); 
//	 System.out.println("The  questionaire :::::"+sql.toString());
//	  List<String[]> ClosureQuestion=dbActionTemplate.getDataList(sql.toString());
//	  return ClosureQuestion;
//}

@Override
public List<String[]> MOCClosure(String mocKeyid) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT CASE MOCL_RESPONSEY WHEN 'Y' THEN 'Y' WHEN 'N' THEN '-' ELSE '-' END, MOC_CLQ_QUESTIONS ");
    sql.append("FROM MOC_TL_CLOSUREQSTMST ");
    sql.append("LEFT JOIN MOC_TL_CLOSUREMASTER ON MOC_CLQ_KEYID = MOCL_CLQ_KEYID ");
    sql.append("AND MOCL_RFC_KEYID = '" + mocKeyid + "' ");
    sql.append("ORDER BY MOC_CLQ_KEYID ASC");

    System.out.println("The questionaire :::::" + sql.toString());
    List<String[]> ClosureQuestion = dbActionTemplate.getDataList(sql.toString());
    return ClosureQuestion;
}

//@Override
//public List<String[]> InitialApprovals(String mocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//	 StringBuilder sql=new StringBuilder();
//		
//	 sql.append("SELECT ROLM_NAME,EMPM_NAME,MOC_RCM_IASTATUS,TO_CHAR(MOC_RCM_IADATE,'DD-MON-YYYY'),MOC_RCM_IAREMARKS ,ROLM_KEYID ");
//	 sql.append(" FROM MOC_TL_ROLECONFIGMST ,GEN_TL_EMPLOYEEMST, MOC_TL_ROLEMST  ");
//	 sql.append("WHERE MOC_RCM_EMPID=EMPM_KEYID(+) AND MOC_RCM_ROLEID(+)=ROLM_KEYID AND MOC_RCM_INITIALAPPROVAL(+)='Y' AND MOC_RCM_RFCKEYID(+) ='"+mocKeyid+"' order by rolm_keyid ");
//	 System.out.println("The Initial Approvals :::::"+sql.toString());
//	  List<String[]> InitalApprovals=dbActionTemplate.getDataList(sql.toString());
//	  return InitalApprovals;
//}
@Override
public List<String[]> InitialApprovals(String mocKeyid) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT ROLM_NAME, EMPM_NAME, MOC_RCM_IASTATUS, ");
    sql.append("TO_CHAR(MOC_RCM_IADATE, 'DD-Mon-YYYY'), MOC_RCM_IAREMARKS, ROLM_KEYID ");
    sql.append("FROM MOC_TL_ROLEMST ");
    sql.append("LEFT JOIN MOC_TL_ROLECONFIGMST ON MOC_RCM_ROLEID = ROLM_KEYID ");
    sql.append("AND MOC_RCM_INITIALAPPROVAL = 'Y' ");
    sql.append("AND MOC_RCM_RFCKEYID = '" + mocKeyid + "' ");
    sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST ON EMPM_KEYID = MOC_RCM_EMPID ");
    sql.append("ORDER BY ROLM_KEYID");

    System.out.println("The Initial Approvals :::::" + sql.toString());
    List<String[]> InitalApprovals = dbActionTemplate.getDataList(sql.toString());
    return InitalApprovals;
}

//@Override
//public List<String[]> HazopApprovals(String mocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//	 StringBuilder sql=new StringBuilder();
//		
//	 sql.append("SELECT ROLM_NAME,EMPM_NAME,MOC_RCM_HZSTATUS,TO_CHAR(MOC_RCM_HZDATE,'DD-MON-YYYY'),MOC_RCM_HZREMARKS ,ROLM_KEYID ");
//	 sql.append(" FROM MOC_TL_ROLECONFIGMST ,GEN_TL_EMPLOYEEMST, MOC_TL_ROLEMST  ");
//	 sql.append("WHERE MOC_RCM_EMPID=EMPM_KEYID(+) AND MOC_RCM_ROLEID(+)=ROLM_KEYID AND MOC_RCM_HAZOPAPPROVAL(+)='Y' AND MOC_RCM_RFCKEYID(+) ='"+mocKeyid+"' order by rolm_keyid ");
//	 System.out.println("The Hazop Approvals :::::"+sql.toString());
//	  List<String[]> HazopApprovals=dbActionTemplate.getDataList(sql.toString());
//	  return HazopApprovals;
//}

@Override
public List<String[]> HazopApprovals(String mocKeyid) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT ROLM_NAME, EMPM_NAME, MOC_RCM_HZSTATUS, ");
    sql.append("TO_CHAR(MOC_RCM_HZDATE, 'DD-Mon-YYYY'), MOC_RCM_HZREMARKS, ROLM_KEYID ");
    sql.append("FROM MOC_TL_ROLEMST ");
    sql.append("LEFT JOIN MOC_TL_ROLECONFIGMST ON MOC_RCM_ROLEID = ROLM_KEYID ");
    sql.append("AND MOC_RCM_HAZOPAPPROVAL = 'Y' ");
    sql.append("AND MOC_RCM_RFCKEYID = '" + mocKeyid + "' ");
    sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST ON EMPM_KEYID = MOC_RCM_EMPID ");
    sql.append("ORDER BY ROLM_KEYID");

    System.out.println("The Hazop Approvals :::::" + sql.toString());
    List<String[]> HazopApprovals = dbActionTemplate.getDataList(sql.toString());
    return HazopApprovals;
}

//@Override
//public List<String[]> FinalApprovals(String mocKeyid) throws Exception {
//	// TODO Auto-generated method stub
//	 StringBuilder sql=new StringBuilder();
//		
//	 sql.append("SELECT ROLM_NAME,EMPM_NAME,MOC_RCM_FASTATUS,TO_CHAR(MOC_RCM_HZDATE,'DD-MON-YYYY'),MOC_RCM_FAREMARKS ,ROLM_KEYID ");
//	 sql.append(" FROM MOC_TL_ROLECONFIGMST ,GEN_TL_EMPLOYEEMST, MOC_TL_ROLEMST  ");
//	 sql.append("WHERE MOC_RCM_EMPID=EMPM_KEYID(+) AND MOC_RCM_ROLEID(+)=ROLM_KEYID AND MOC_RCM_FINALAPPROVAL(+)='Y' AND MOC_RCM_RFCKEYID(+) ='"+mocKeyid+"' order by rolm_keyid ");
//	 System.out.println("The Final Approvals :::::"+sql.toString());
//	  List<String[]> FinalApprovals=dbActionTemplate.getDataList(sql.toString());
//	  return FinalApprovals;
//}

@Override
public List<String[]> FinalApprovals(String mocKeyid) throws Exception {

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT ROLM_NAME, EMPM_NAME, MOC_RCM_FASTATUS, ");
    sql.append("TO_CHAR(MOC_RCM_HZDATE, 'DD-Mon-YYYY'), MOC_RCM_FAREMARKS, ROLM_KEYID ");
    sql.append("FROM MOC_TL_ROLEMST ");
    sql.append("LEFT JOIN MOC_TL_ROLECONFIGMST ON MOC_RCM_ROLEID = ROLM_KEYID ");
    sql.append("AND MOC_RCM_FINALAPPROVAL = 'Y' ");
    sql.append("AND MOC_RCM_RFCKEYID = '" + mocKeyid + "' ");
    sql.append("LEFT JOIN GEN_TL_EMPLOYEEMST ON EMPM_KEYID = MOC_RCM_EMPID ");
    sql.append("ORDER BY ROLM_KEYID");

    System.out.println("The Final Approvals :::::" + sql.toString());
    List<String[]> FinalApprovals = dbActionTemplate.getDataList(sql.toString());
    return FinalApprovals;
}

@Override
public String getPssrReccommendationCompleted(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String pssrCount=dbActionTemplate.getSingleValue("SELECT COUNT(*) FROM PSSR_TL_RECCOMENDATIONS WHERE PSRR_MOC_KEYID='"+mockeyid+"' and PSRR_STATUS='C' ");
    System.out.println("COMPLETED pssrCount"+pssrCount);	
	return pssrCount;
}
@Override
public String getPssrReccommendationCount(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	String pssrCount=dbActionTemplate.getSingleValue("SELECT COUNT(*) FROM PSSR_TL_RECCOMENDATIONS WHERE PSRR_MOC_KEYID='"+mockeyid+"' ");
    System.out.println("TOTAL pssrCount"+pssrCount);	
	return pssrCount;
}
@Override
public List<String[]> getPSITeamMailIds(String mockeyid) throws Exception {
	// TODO Auto-generated method stub
	StringBuilder sql=new StringBuilder();
	String enableEmail="Y";

		 sql.append("select empm_name,empm_email FROM GEN_TL_EMPLOYEEMST  WHERE EMPM_CODE in ('SYSADMIN','109827','113680','115711','84450','84270','105006')");
		
	   System.out.println("sql"+sql);
	
	 List<String[]> MailList=dbActionTemplate.getDataList(sql.toString());	
		return MailList;


}
@Override
public String getJHLeader(String sugflid) throws Exception {
	// TODO Auto-generated method stub
 //String JHLeader="SELECT  EMPM_KEYID FROM GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST,GEN_MV_FLIDHIERARCHY  WHERE     FRT_ROLE_KEYID = ROLE_KEYID AND FRT_EMPM_KEYID = EMPM_KEYID AND EMPM_ACTIVE = 'Y' AND FRT_FNLN_KEYID = FLID(+) AND ROLE_KEYID = 'AROL0006' and FLID='"+sugflid+"' ";
	
 String JHLeader="SELECT E.EMPM_KEYID FROM GEN_TL_FNLNROLETEAM FRT JOIN ADM_TL_ROLEMST R ON FRT.FRT_ROLE_KEYID = R.ROLE_KEYID JOIN GEN_TL_EMPLOYEEMST E ON FRT.FRT_EMPM_KEYID = E.EMPM_KEYID JOIN GEN_MV_FLIDHIERARCHY H ON FRT.FRT_FNLN_KEYID = H.FLID WHERE E.EMPM_ACTIVE = 'Y' AND R.ROLE_KEYID = 'AROL0006' AND H.FLID =" + "'"+sugflid+"' ";
	
 	  
	  System.out.println(" JHLeader:"+ JHLeader);
			return dbActionTemplate.getSingleValue(JHLeader);
}
@Override
public String getDMTLeader(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	
	String CellId=dbActionTemplate.getSingleValue("GEN_TL_FUNCTIONALLOCN", "FNLN_ORIGINALID", "FNLN_KEYID",sugflid);
	
	String SectionId=dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_SECTIONID", "CELL_KEYID",CellId);
	
	
//	String DMTLeader="SELECT  EMPM_KEYID FROM GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST,GEN_MV_FLIDHIERARCHY  WHERE  FRT_ROLE_KEYID = ROLE_KEYID AND FRT_EMPM_KEYID = EMPM_KEYID AND EMPM_ACTIVE = 'Y' AND FRT_FNLN_KEYID = FLID(+) AND ROLE_KEYID = 'AROL0003' and FNLN_ORIGINALID='"+SectionId+"' ";
	String DMTLeader="SELECT E.EMPM_KEYID FROM GEN_TL_FNLNROLETEAM frt join ADM_TL_ROLEMST r on frt.FRT_ROLE_KEYID = r.ROLE_KEYID join GEN_TL_EMPLOYEEMST e on frt.FRT_EMPM_KEYID = e.EMPM_KEYID left join GEN_MV_FLIDHIERARCHY m on frt.FRT_FNLN_KEYID = m.FLID where e.EMPM_ACTIVE = 'Y' and r.ROLE_KEYID = 'AROL0003' and m.FNLN_ORIGINALID ='"+SectionId+"' ";
	
	
	

	System.out.println(" DMTLeader:"+ DMTLeader);
	return dbActionTemplate.getSingleValue(DMTLeader);
}
@Override
public String getProcess(String sugflid) throws Exception {

	String CellId=dbActionTemplate.getSingleValue("GEN_TL_FUNCTIONALLOCN", "FNLN_ORIGINALID", "FNLN_KEYID",sugflid);
	
	String SectionId=dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_SECTIONID", "CELL_KEYID",CellId);

	
//	String PAI="SELECT  EMPM_KEYID FROM GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST,GEN_MV_FLIDHIERARCHY  WHERE     FRT_ROLE_KEYID = ROLE_KEYID AND FRT_EMPM_KEYID = EMPM_KEYID AND EMPM_ACTIVE = 'Y' AND FRT_FNLN_KEYID = FLID(+) AND ROLE_KEYID = 'AROL0128' and FNLN_ORIGINALID='"+SectionId+"' ";
	String PAI="SELECT EMPM_KEYID FROM GEN_TL_FNLNROLETEAM frt join ADM_TL_ROLEMST r on frt.FRT_ROLE_KEYID = r.ROLE_KEYID join GEN_TL_EMPLOYEEMST e on frt.FRT_EMPM_KEYID = e.EMPM_KEYID left join GEN_MV_FLIDHIERARCHY m on frt.FRT_FNLN_KEYID = m.FLID where e.EMPM_ACTIVE = 'Y' and r.ROLE_KEYID = 'AROL0131' and m.FNLN_ORIGINALID ='"+SectionId+"' ";
	System.out.println(" PAI:"+ PAI);
	return dbActionTemplate.getSingleValue(PAI);
}
@Override
public String getMech(String sugflid) throws Exception {
	// TODO Auto-generated method stub
    String CellId=dbActionTemplate.getSingleValue("GEN_TL_FUNCTIONALLOCN", "FNLN_ORIGINALID", "FNLN_KEYID",sugflid);
	
	String SectionId=dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_SECTIONID", "CELL_KEYID",CellId);
	
	//String MAI="SELECT  EMPM_KEYID FROM GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST,GEN_MV_FLIDHIERARCHY  WHERE     FRT_ROLE_KEYID = ROLE_KEYID AND FRT_EMPM_KEYID = EMPM_KEYID AND EMPM_ACTIVE = 'Y' AND FRT_FNLN_KEYID = FLID(+) AND ROLE_KEYID = 'AROL0129' and FNLN_ORIGINALID='"+SectionId+"' ";
	String MAI="SELECT EMPM_KEYID FROM GEN_TL_FNLNROLETEAM frt join ADM_TL_ROLEMST r on frt.FRT_ROLE_KEYID = r.ROLE_KEYID join GEN_TL_EMPLOYEEMST e on frt.FRT_EMPM_KEYID = e.EMPM_KEYID left join GEN_MV_FLIDHIERARCHY m on frt.FRT_FNLN_KEYID = m.FLID where e.EMPM_ACTIVE = 'Y' and r.ROLE_KEYID = 'AROL0132' and m.FNLN_ORIGINALID ='"+SectionId+"' ";
	System.out.println(" MAI:"+ MAI);
	return dbActionTemplate.getSingleValue(MAI);
}
@Override
public String getInstrument(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	
    String CellId=dbActionTemplate.getSingleValue("GEN_TL_FUNCTIONALLOCN", "FNLN_ORIGINALID", "FNLN_KEYID",sugflid);
	
	String SectionId=dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_SECTIONID", "CELL_KEYID",CellId);
	
	
//	String IAI="SELECT  EMPM_KEYID FROM GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST,GEN_MV_FLIDHIERARCHY  WHERE     FRT_ROLE_KEYID = ROLE_KEYID AND FRT_EMPM_KEYID = EMPM_KEYID AND EMPM_ACTIVE = 'Y' AND FRT_FNLN_KEYID = FLID(+) AND ROLE_KEYID = 'AROL0130' and FNLN_ORIGINALID='"+SectionId+"' ";
	String IAI="SELECT EMPM_KEYID FROM GEN_TL_FNLNROLETEAM frt join ADM_TL_ROLEMST r on frt.FRT_ROLE_KEYID = r.ROLE_KEYID join GEN_TL_EMPLOYEEMST e on frt.FRT_EMPM_KEYID = e.EMPM_KEYID left join GEN_MV_FLIDHIERARCHY m on frt.FRT_FNLN_KEYID = m.FLID where e.EMPM_ACTIVE = 'Y' and r.ROLE_KEYID = 'AROL0133' and m.FNLN_ORIGINALID ='"+SectionId+"' ";
	
	System.out.println(" IAI:"+ IAI);
	return dbActionTemplate.getSingleValue(IAI);
}
@Override
public String getCivil(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	
    String CellId=dbActionTemplate.getSingleValue("GEN_TL_FUNCTIONALLOCN", "FNLN_ORIGINALID", "FNLN_KEYID",sugflid);
	
	String SectionId=dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_SECTIONID", "CELL_KEYID",CellId);
	
	
//	String CAI="SELECT  EMPM_KEYID FROM GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST,GEN_MV_FLIDHIERARCHY  WHERE     FRT_ROLE_KEYID = ROLE_KEYID AND FRT_EMPM_KEYID = EMPM_KEYID AND EMPM_ACTIVE = 'Y' AND FRT_FNLN_KEYID = FLID(+) AND ROLE_KEYID = 'AROL0132' and FNLN_ORIGINALID='"+SectionId+"' ";
	String CAI="SELECT EMPM_KEYID FROM GEN_TL_FNLNROLETEAM frt join ADM_TL_ROLEMST r on frt.FRT_ROLE_KEYID = r.ROLE_KEYID join GEN_TL_EMPLOYEEMST e on frt.FRT_EMPM_KEYID = e.EMPM_KEYID left join GEN_MV_FLIDHIERARCHY m on frt.FRT_FNLN_KEYID = m.FLID where e.EMPM_ACTIVE = 'Y' and r.ROLE_KEYID = 'AROL0135' and m.FNLN_ORIGINALID ='"+SectionId+"' ";
	System.out.println(" CAI:"+ CAI);
	return dbActionTemplate.getSingleValue(CAI);
}
@Override
public String getElect(String sugflid) throws Exception {
	// TODO Auto-generated method stub
    String CellId=dbActionTemplate.getSingleValue("GEN_TL_FUNCTIONALLOCN", "FNLN_ORIGINALID", "FNLN_KEYID",sugflid);
	
	String SectionId=dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_SECTIONID", "CELL_KEYID",CellId);
	//String EAI="SELECT  EMPM_KEYID FROM GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST,GEN_MV_FLIDHIERARCHY  WHERE     FRT_ROLE_KEYID = ROLE_KEYID AND FRT_EMPM_KEYID = EMPM_KEYID AND EMPM_ACTIVE = 'Y' AND FRT_FNLN_KEYID = FLID(+) AND ROLE_KEYID = 'AROL0131' and FNLN_ORIGINALID='"+SectionId+"' ";
	String EAI="SELECT EMPM_KEYID FROM GEN_TL_FNLNROLETEAM frt join ADM_TL_ROLEMST r on frt.FRT_ROLE_KEYID = r.ROLE_KEYID join GEN_TL_EMPLOYEEMST e on frt.FRT_EMPM_KEYID = e.EMPM_KEYID left join GEN_MV_FLIDHIERARCHY m on frt.FRT_FNLN_KEYID = m.FLID where e.EMPM_ACTIVE = 'Y' and r.ROLE_KEYID = 'AROL0134' and m.FNLN_ORIGINALID ='"+SectionId+"' ";
	System.out.println(" EAI:"+ EAI);
	return dbActionTemplate.getSingleValue(EAI);
}
@Override
public String getEHSHead(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	return null;
}
@Override
public String getEHSManager(String sugflid) throws Exception {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getPbuHead(String sugflid) throws Exception {
	// TODO Auto-generated method stub

	String CellId=dbActionTemplate.getSingleValue("GEN_TL_FUNCTIONALLOCN", "FNLN_ORIGINALID", "FNLN_KEYID",sugflid);
	
	String SectionId=dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_SECTIONID", "CELL_KEYID",CellId);
	String PbuId =dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_FACTORYID", "SECT_KEYID",SectionId);
	
//	String EAI="SELECT  EMPM_KEYID FROM GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST,GEN_MV_FLIDHIERARCHY  WHERE     FRT_ROLE_KEYID = ROLE_KEYID AND FRT_EMPM_KEYID = EMPM_KEYID AND EMPM_ACTIVE = 'Y' AND FRT_FNLN_KEYID = FLID(+) AND ROLE_KEYID = 'AROL0002' and FNLN_ORIGINALID='"+PbuId+"' ";
	String PH="SELECT E.EMPM_KEYID FROM GEN_TL_FNLNROLETEAM frt join ADM_TL_ROLEMST r on frt.FRT_ROLE_KEYID = r.ROLE_KEYID join GEN_TL_EMPLOYEEMST e on frt.FRT_EMPM_KEYID = e.EMPM_KEYID left join GEN_MV_FLIDHIERARCHY m on frt.FRT_FNLN_KEYID = m.FLID where e.EMPM_ACTIVE = 'Y' and r.ROLE_KEYID = 'AROL0002' and m.FNLN_ORIGINALID ='"+PbuId+"' ";
	System.out.println("PH : "+PH);
	return dbActionTemplate.getSingleValue(PH);
}


//@Override
//public String getPbuHead(String sugflid) throws Exception {
//	// TODO Auto-generated method stub
////	String CellId =dbActionTemplate.getSingleValue("GEN_TL_FUNCTIONALLOCN", "FNLN_ORIGINALID", "FNLN_KEYID",sugflid);
////	
////	String SectionId=dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_SECTIONID", "CELL_KEYID",CellId);
//	
//	String PbuId=dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_FACTORYID", "CELL_FLID",sugflid);
////	String EAI="SELECT  EMPM_KEYID FROM GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST,GEN_TL_EMPLOYEEMST,GEN_MV_FLIDHIERARCHY  WHERE     FRT_ROLE_KEYID = ROLE_KEYID AND FRT_EMPM_KEYID = EMPM_KEYID AND EMPM_ACTIVE = 'Y' AND FRT_FNLN_KEYID = FLID(+) AND ROLE_KEYID = 'AROL0002' and FNLN_ORIGINALID='"+PbuId+"' ";
//	String EAI="SELECT EMPM_KEYID FROM GEN_TL_FNLNROLETEAM frt join ADM_TL_ROLEMST r on frt.FRT_ROLE_KEYID = r.ROLE_KEYID join GEN_TL_EMPLOYEEMST e on frt.FRT_EMPM_KEYID = e.EMPM_KEYID left join GEN_MV_FLIDHIERARCHY m on frt.FRT_FNLN_KEYID = m.FLID where e.EMPM_ACTIVE = 'Y' and r.ROLE_KEYID = 'AROL0136' and m.FNLN_ORIGINALID ='"+PbuId+"' ";
//	System.out.println(" EAI:"+ EAI);
//	return dbActionTemplate.getSingleValue(EAI);
//}
//@Override
public List<String[]> getRecipientId(String mocKeyid) throws Exception {
	// TODO Auto-generated method stub

	StringBuilder stbuild = new  StringBuilder();
	stbuild.append(" select EMPM_KEYID, EMPM_EMAIL FROM GEN_TL_EMPLOYEEMST,MOC_TL_ROLECONFIGMST");
	stbuild.append(" WHERE EMPM_KEYID=MOC_RCM_EMPID AND EMPM_EMAIL LIKE '%@%' ");
			stbuild.append(" AND EMPM_ENABLEEMAIL='Y' AND MOC_RCM_RFCKEYID='"+mocKeyid+"'");
			
			System.out.println(stbuild.toString()+"SQl in DAoIMpl");
			List<String[]> gridData = dbActionTemplate.getDataList(stbuild.toString());

			return gridData;
}
}