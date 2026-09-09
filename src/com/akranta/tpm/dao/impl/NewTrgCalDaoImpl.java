package com.akranta.tpm.dao.impl;

import com.akranta.tpm.dao.NewTrgCalDao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.bind.ValidationException;

import net.sf.json.JSONObject;
import oracle.net.aso.d;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GridFilter;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.EntTlTragcalmstSql;
import com.akranta.tpm.dao.sql.EntTlTragcalquadSql;
import com.akranta.tpm.dao.sql.EntTlTrgCalEmpSql;
import com.akranta.tpm.dao.sql.EntTlTrgCalSessionSql;
import com.akranta.tpm.dao.sql.EntTlTrgCalUnqpSql;
import com.akranta.tpm.dao.sql.EntTlTrgFacultySql;
import com.akranta.tpm.dao.sql.EntTlTtgCalEmpatScoreSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalmst;
import com.akranta.tpm.model.EntTlTragcalquad;
import com.akranta.tpm.model.EntTlTrgCalEmp;
import com.akranta.tpm.model.EntTlTrgCalSession;
import com.akranta.tpm.model.EntTlTrgCalUnqp;
import com.akranta.tpm.model.EntTlTtgCalEmpatScore;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
public class NewTrgCalDaoImpl implements NewTrgCalDao{
	private DBActionTemplate dbActionTemplate;
	private CommonFilterDao commonFilterdao;
	private EntTlTragcalmstSql entTlTragcalmstSql = null;
	private EntTlTrgCalSessionSql entTlTrgCalSessionSql=null;
	private EntTlTrgFacultySql entTlTrgFacultySql=null;
	private EntTlTrgCalEmpSql entTlTrgCalEmpSql=null;
	  FunctionCallApi fnCallApi;
	public NewTrgCalDaoImpl(DBActionTemplate dbActionTemplate) {
		entTlTragcalmstSql=new EntTlTragcalmstSql();
		entTlTrgCalSessionSql=new EntTlTrgCalSessionSql();
		entTlTrgFacultySql=new EntTlTrgFacultySql();
		entTlTrgCalEmpSql=new EntTlTrgCalEmpSql();
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	//-- added by vignesh -- //
	public void NewTrgCalDaoImplJwt(String JwtToken) {
	    try {
	   //     oplServiceApi = new OplTlMstServiceApi(JwtToken);
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//-- added by vignesh -- //
	 //--------------------- Vignesh 26Nov2025 -------------------------------------------------------------------------//
	@Override
	public List<String[]> getsession(String TrainingKeyid) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append(" select ETCS_KEYID,ETCS_NAME, TO_CHAR(ETCS_SESSIONDATE,'DD-Mon-YYYY') as   \"Session\",");
        sql.append(" TO_CHAR(ETCS_FROMDATE,'HH24:MI') as   \"From Time\", ");
        sql.append(" TO_CHAR(ETCS_TILLDATE,'HH24:MI') as   \"To Time\", ");
        sql.append(" '' as \"Delete\" ");
        sql.append(" FROM ENT_TL_TRGCALSESSION, ENT_TL_TRGCALMST WHERE ETCS_ETCM_KEYID='"+TrainingKeyid+"' ");
        sql.append("  AND ETCS_ETCM_KEYID = ETCM_KEYID  ORDER BY ETCS_KEYID,ETCS_NAME ");
		CommonMessage.debugMsg("The Session Data:::inside newtrgcal "+sql);
		List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql.toString(),null);
		return dataList ;
	}
	
	//--------------------- Vignesh 26Nov2025 -------------------------------------------------------------------------//
	@Override
	public List<String[]> getFaculty(String TrainingId) throws Exception {
    StringBuilder sql = new StringBuilder( "select '',FTYM_EMPM_KEYID,ETCF_KEYID,FTYM_NAME  as \"Faculty\",'' as \"Delete\"  ");
	sql.append(" from ENT_TL_TRGFACULTY,ENT_TL_FACULTYMST,ENT_TL_TRGCALMST " );
	sql.append(" where ETCM_KEYID ='").append(TrainingId).append("'  " );
	sql.append(" AND ETCF_ETCM_KEYID=ETCM_KEYID AND ETCF_FACULTYID=FTYM_KEYID ");
	CommonMessage.debugMsg("Faculty Data"+sql.toString());
	List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql.toString(),null);
	CommonMessage.debugMsg("The DataList::"+dataList);
	return dataList;
	}
	
	public List<String[]> getAllEmployee(CommonFilter commonFilter,GridParams gridParams) throws Exception {
		String Trainingkeyid = commonFilter.getKey();
		CommonMessage.debugMsg("The Trainingkeyid"+Trainingkeyid);
		String date = "";// commonFilter.getdate();
		StringBuffer sql= new StringBuffer();
		sql.append(EntTlTragcalmstSql.getTrainingAttedenceData(Trainingkeyid));
		List<String> paramValues = new ArrayList<String>();
			
		String conditionalparam=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	    String commonparam=FilterCondSql.getGridCommonParams(commonFilter);
	    paramValues.add(conditionalparam);
	    paramValues.add(commonparam);
	    String countSql=CommonFilterSqls.countSql(sql.toString(),gridParams.getGridFilters());
		String viewinfo=dbActionTemplate.getSingleValue(countSql);
	    long counts=Long.parseLong(viewinfo);
	    if( counts >0){
	    	String sb = CommonFilterSqls.addPaginationParams(sql.toString(),gridParams);
	    	gridParams.setTotalRecordCnt(counts);
	    	CommonMessage.debugMsg("The sql Data"+sql);
		    List<String[]> datacon=dbActionTemplate.getDataList(sb);
	    	return datacon;
	    }
	    throw new NoDataFoundException("No Data Found");


		
		
		
		
	/*	//condParam += "CALENID="+commonFilter.getProgram()+";";
		condParam += "TRAININGID="+commonFilter.getKey()+";";
	//	condParam += "PROGTYPE="+value+";";
		CommonMessage.debugMsg("The condParam::::"+condParam);

		paramValues.add(condParam);
		paramValues.add(commonParam);
		
		List<String[]> gridData = dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_TRNATTENDENCEENEW", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt...."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return gridData;*/
	}
	
	// --------------------------25NOV2025-----------------------VIGNESH -----------------------------------------//

	
	//	@Override
//	public List<String[]> getAllUniqueEmployee(CommonFilter commonFilter, GridParams gridParams) throws Exception{
//        StringBuffer sqls = new StringBuffer();
//        List<String> paramValues=new ArrayList<String>();		  
//         sqls.append(" SELECT * FROM ( ");
//         sqls.append(" SELECT DISTINCT  ETCE_KEYID AS etcekeyid,ETCA_KEYID AS Attnkeyid,Empm_keyid AS keyid,EMPM_CODE AS empcode,EMPM_NAME AS name,DECODE(EMPM_EMPLOYEETYPE,'R','Employee','M','Manager','C','Contract','A','Asosciate','B','Badli'),DECODE(EMPM_GENDER,'M','Male','F','Female'),DECODE(ETCE_ETCS_KEYID,'{}',' ',ETCS_NAME) AS ses,ROLE_NAME AS ROLENAME, ");
//         sqls.append(" ETCQ_CURRENTLEVEL AS currLevel,MAX(ETCQ_CURRENTLEVELDATE) AS LastUpdate,SECT_NAME AS DMT, ");
//         sqls.append("  CELL_NAME AS JH,ROLE_NAME AS roleid FROM GEN_TL_EMPLOYEEMST,GEN_TL_ROLEMST,ENT_TL_TRGCALEMP,ENT_TL_TRGCALSESSION, ");
//         sqls.append(" ENT_TL_TOPICMST,ENT_TL_TRGCALMST,ENT_TL_TRGCALQUAD,GEN_TL_CELLMST,GEN_TL_SECTIONMST,ENT_TL_TRgcalempatscore WHERE EMPM_KEYID = ETCE_EMPM_KEYID ");
//         sqls.append(" AND ETCE_ROLE_KEYID = ROLE_KEYID(+) AND ETCE_ETCM_KEYID=ETCM_KEYID  AND TOPI_KEYID=ETCQ_TOPICID(+) AND TOPI_KEYID=ETCM_TOPICID  AND ETCE_ETCS_KEYID = ETCS_KEYID");
//         sqls.append(" AND ETCE_EMPM_KEYID=ETCQ_EMPM_KEYID(+) AND ETCE_ETCM_KEYID='"+commonFilter.getKey()+"' AND ETCE_ROLEDMT=SECT_KEYID(+) AND ETCE_ROLEJH=CELL_KEYID(+) AND ETCA_ETCE_KEYID(+)=ETCE_KEYID ");
//        // sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
//          sqls.append(" GROUP BY ETCE_KEYID,ETCA_KEYID,Empm_keyid,EMPM_CODE,EMPM_NAME,EMPM_EMPLOYEETYPE,EMPM_GENDER,ROLE_NAME,ETCQ_CURRENTLEVEL,SECT_NAME,CELL_NAME,DECODE(ETCE_ETCS_KEYID,'{}',' ',ETCS_NAME) ,ROLE_NAME ORDER BY ETCE_KEYID");   
//    
//        sqls.append(")");
//        CommonMessage.debugMsg("inside the get data"+sqls);
//        
//        
//       // sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
//        String conditionalparam=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
//	    String commonparam=FilterCondSql.getGridCommonParams(commonFilter);
//	    paramValues.add(conditionalparam);
//	    paramValues.add(commonparam);
//	    String countSql=CommonFilterSqls.countSql(sqls.toString(),gridParams.getGridFilters());
//		String viewinfo=dbActionTemplate.getSingleValue(countSql);
//	    long counts=Long.parseLong(viewinfo);
//	    if( counts >0){
//	    	String sql = CommonFilterSqls.addPaginationParams(sqls.toString(),gridParams);
//	    	gridParams.setTotalRecordCnt(counts);
//	    	CommonMessage.debugMsg("The sql Data"+sql);
//		    List<String[]> datacon=dbActionTemplate.getDataList(sql.toString());
//	    	return datacon;
//	    }
//        
///*        CommonMessage.debugMsg("sql selected employee"+sqls.toString());
//        List<String[]> dataList =  dbActionTemplate.getDataList(sqls.toString());
//        return dataList;
//        */
//	    throw new NoDataFoundException("No Data Found"); 
// }
//	
	
	
	
	@Override
	public List<String[]> getAllUniqueEmployee(CommonFilter commonFilter, GridParams gridParams) throws Exception {
	    StringBuffer sqls = new StringBuffer();
	    List<String> paramValues = new ArrayList<String>();

	    sqls.append(" SELECT * FROM ( ");
	    sqls.append("   SELECT DISTINCT ");
	    sqls.append("     ETCE.ETCE_KEYID AS etcekeyid, ");
	    sqls.append("     ETCA.ETCA_KEYID AS Attnkeyid, ");
	    sqls.append("     EMPM.EMPM_KEYID AS keyid, ");
	    sqls.append("     EMPM.EMPM_CODE  AS empcode, ");
	    sqls.append("     EMPM.EMPM_NAME  AS name, ");

	    // DECODE(EMPM_EMPLOYEETYPE,'R','Employee','M','Manager','C','Contract','A','Asosciate','B','Badli')
	    sqls.append("     CASE EMPM.EMPM_EMPLOYEETYPE ");
	    sqls.append("       WHEN 'R' THEN 'Employee' ");
	    sqls.append("       WHEN 'M' THEN 'Manager' ");
	    sqls.append("       WHEN 'C' THEN 'Contract' ");
	    sqls.append("       WHEN 'A' THEN 'Asosciate' ");
	    sqls.append("       WHEN 'B' THEN 'Badli' ");
	    sqls.append("       ELSE NULL ");
	    sqls.append("     END, ");

	    // DECODE(EMPM_GENDER,'M','Male','F','Female')
	    sqls.append("     CASE EMPM.EMPM_GENDER ");
	    sqls.append("       WHEN 'M' THEN 'Male' ");
	    sqls.append("       WHEN 'F' THEN 'Female' ");
	    sqls.append("       ELSE NULL ");
	    sqls.append("     END, ");

	    // DECODE(ETCE_ETCS_KEYID,'{}',' ',ETCS_NAME) AS ses
	    sqls.append("     CASE ");
	    sqls.append("       WHEN ETCE.ETCE_ETCS_KEYID = '{}' THEN ' ' ");
	    sqls.append("       ELSE ETCS.ETCS_NAME ");
	    sqls.append("     END AS ses, ");

	    sqls.append("     ROL.ROLE_NAME AS ROLENAME, ");
	    sqls.append("     ETCQ.ETCQ_CURRENTLEVEL AS currLevel, ");
	    sqls.append("     MAX(ETCQ.ETCQ_CURRENTLEVELDATE) AS LastUpdate, ");
	    sqls.append("     SECT.SECT_NAME AS DMT, ");
	    sqls.append("     CELL.CELL_NAME AS JH, ");
	    sqls.append("     ROL.ROLE_NAME AS roleid ");

	    sqls.append("   FROM GEN_TL_EMPLOYEEMST EMPM ");
	    sqls.append("   JOIN ENT_TL_TRGCALEMP ETCE ");
	    sqls.append("        ON EMPM.EMPM_KEYID = ETCE.ETCE_EMPM_KEYID ");

	    // ETCE_ROLE_KEYID = ROLE_KEYID(+)
	    sqls.append("   LEFT JOIN GEN_TL_ROLEMST ROL ");
	    sqls.append("        ON ETCE.ETCE_ROLE_KEYID = ROL.ROLE_KEYID ");

	    // ETCE_ETCM_KEYID = ETCM_KEYID
	    sqls.append("   JOIN ENT_TL_TRGCALMST ETCM ");
	    sqls.append("        ON ETCE.ETCE_ETCM_KEYID = ETCM.ETCM_KEYID ");

	    // ETCE_ETCS_KEYID = ETCS_KEYID
	    sqls.append("   JOIN ENT_TL_TRGCALSESSION ETCS ");
	    sqls.append("        ON ETCE.ETCE_ETCS_KEYID = ETCS.ETCS_KEYID ");

	    // TOPI_KEYID = ETCM_TOPICID
	    sqls.append("   JOIN ENT_TL_TOPICMST TOPI ");
	    sqls.append("        ON TOPI.TOPI_KEYID = ETCM.ETCM_TOPICID ");

	    // TOPI_KEYID = ETCQ_TOPICID(+) AND ETCE_EMPM_KEYID = ETCQ_EMPM_KEYID(+)
	    sqls.append("   LEFT JOIN ENT_TL_TRGCALQUAD ETCQ ");
	    sqls.append("        ON ETCQ.ETCQ_TOPICID    = TOPI.TOPI_KEYID ");
	    sqls.append("       AND ETCQ.ETCQ_EMPM_KEYID = ETCE.ETCE_EMPM_KEYID ");

	    // ETCE_ROLEDMT = SECT_KEYID(+)
	    sqls.append("   LEFT JOIN GEN_TL_SECTIONMST SECT ");
	    sqls.append("        ON ETCE.ETCE_ROLEDMT = SECT.SECT_KEYID ");

	    // ETCE_ROLEJH = CELL_KEYID(+)
	    sqls.append("   LEFT JOIN GEN_TL_CELLMST CELL ");
	    sqls.append("        ON ETCE.ETCE_ROLEJH = CELL.CELL_KEYID ");

	    // ETCA_ETCE_KEYID(+) = ETCE_KEYID
	    sqls.append("   LEFT JOIN ENT_TL_TRGCALEMPATSCORE ETCA ");
	    sqls.append("        ON ETCA.ETCA_ETCE_KEYID = ETCE.ETCE_KEYID ");

	    // WHERE clause with training key
	    sqls.append("   WHERE ETCE.ETCE_ETCM_KEYID = '")
	        .append(commonFilter.getKey())
	        .append("' ");

	    // GROUP BY (expression for SES must match SELECT)
	    sqls.append("   GROUP BY ");
	    sqls.append("     ETCE.ETCE_KEYID, ");
	    sqls.append("     ETCA.ETCA_KEYID, ");
	    sqls.append("     EMPM.EMPM_KEYID, ");
	    sqls.append("     EMPM.EMPM_CODE, ");
	    sqls.append("     EMPM.EMPM_NAME, ");
	    sqls.append("     EMPM.EMPM_EMPLOYEETYPE, ");
	    sqls.append("     EMPM.EMPM_GENDER, ");
	    sqls.append("     ROL.ROLE_NAME, ");
	    sqls.append("     ETCQ.ETCQ_CURRENTLEVEL, ");
	    sqls.append("     SECT.SECT_NAME, ");
	    sqls.append("     CELL.CELL_NAME, ");
	    sqls.append("     CASE ");
	    sqls.append("       WHEN ETCE.ETCE_ETCS_KEYID = '{}' THEN ' ' ");
	    sqls.append("       ELSE ETCS.ETCS_NAME ");
	    sqls.append("     END, ");
	    sqls.append("     ROL.ROLE_NAME ");

	    sqls.append("   ORDER BY ETCE.ETCE_KEYID ");
	    sqls.append(" ) ");

	    CommonMessage.debugMsg("inside the get data " + sqls.toString());

	    // Existing filter / pagination logic (unchanged)
	    String conditionalparam = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	    String commonparam      = FilterCondSql.getGridCommonParams(commonFilter);
	    paramValues.add(conditionalparam);
	    paramValues.add(commonparam);

	    String countSql = CommonFilterSqls.countSql(sqls.toString(), gridParams.getGridFilters());
	    String viewinfo = dbActionTemplate.getSingleValue(countSql);
	    long counts     = Long.parseLong(viewinfo);

	    if (counts > 0) {
	        String sql = CommonFilterSqls.addPaginationParams(sqls.toString(), gridParams);
	        gridParams.setTotalRecordCnt(counts);
	        CommonMessage.debugMsg("The sql Data " + sql);
	        List<String[]> datacon = dbActionTemplate.getDataList(sql);
	        return datacon;
	    }

	    throw new NoDataFoundException("No Data Found");
	}

	
	// --------------------------25NOV2025-----------------------VIGNESH -----------------------------------------//
	@Override
	public List<String[]> getNewUniqPosData(String TrgKeyid) throws Exception {
	    // TODO Auto-generated method stub
	    StringBuffer sql = new StringBuffer();

	    sql.append(" SELECT ");
	    sql.append("   ETCU.ETCU_KEYID,");
	    sql.append("   ETCU.ETCU_ROLE_KEYID,");
	    sql.append("   ROLE.ROLE_NAME || ' - ' || ROLE.FNLN_DISPLAYCODE AS \"Unique Position\",");
	    sql.append("   SECT.SECT_NAME AS \"DMT\",");
	    sql.append("   CELL.CELL_NAME AS \"JH\",");
	    sql.append("   '' AS \"Delete\" ");
	    sql.append(" FROM ENT_TL_TRGCALUNQP ETCU ");
	    sql.append(" JOIN ENT_VW_ROLEMST ROLE ");
	    sql.append("       ON ETCU.ETCU_ROLE_KEYID = ROLE.ROLE_KEYID ");
	    sql.append(" LEFT JOIN GEN_TL_SECTIONMST SECT ");
	    sql.append("       ON ETCU.ETCU_ROLEDMT = SECT.SECT_KEYID ");
	    sql.append(" LEFT JOIN GEN_TL_CELLMST CELL ");
	    sql.append("       ON ETCU.ETCU_ROLEJH = CELL.CELL_KEYID ");
	    sql.append(" WHERE ETCU.ETCU_ETCM_KEYID = '").append(TrgKeyid).append("' ");

	    CommonMessage.debugMsg("The UniquePosition Data" + sql.toString());
	    List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql.toString(), null);
	    return dataList;
	}

//	@Override
//	public List<String[]> getNewUniqPosData(String TrgKeyid) throws Exception {
//		// TODO Auto-generated method stub		
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select ETCU_KEYID,ETCU_ROLE_KEYID,ROLE_NAME || ' - ' || FNLN_DISPLAYCODE ");
//		sql.append(" as \"Unique Position\",SECT_NAME as \"DMT\", ");
//		sql.append(" CELL_NAME as \"JH\" ,'' as \"Delete\" ");
//		sql.append(" from ENT_TL_TRGCALUNQP, ENT_VW_ROLEMST, ");
//		sql.append(" GEN_TL_SECTIONMST,GEN_TL_CELLMST ");
//		sql.append(" where ETCU_ETCM_KEYID ='"+TrgKeyid+"'  ");
//		sql.append(" and ETCU_ROLE_KEYID = ROLE_KEYID ");
//		sql.append(" AND ETCU_ROLEDMT=SECT_KEYID(+) ");
//		sql.append(" AND ETCU_ROLEJH=CELL_KEYID(+) ");
//		CommonMessage.debugMsg("The UniquePosition Data"+sql.toString());
//		List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql.toString(),null);
//		return dataList ;
//	}
	// --------------------------25NOV2025-----------------------VIGNESH -----------------------------------------//
	/* @Override
	   public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter)
            throws Exception {
        // TODO Auto-generated method stub
                StringBuffer sqls = new StringBuffer();
                CommonMessage.debugMsg("commonFilter"+commonFilter.getKey());
                CommonMessage.debugMsg("commonFilter.length"+commonFilter.getRefdocid());
               
               
                if(commonFilter.getRefdocid()==null)
                {  
                sqls.append(" SELECT * FROM ( ");
                sqls.append(" select  Distinct '' as selctVal,Empm_keyid as Empm_keyid,EMPM_CODE AS EMPM_CODE,EMPM_NAME as EMPM_NAME,DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) as Ses, ");
                sqls.append(" ROLE_NAME AS ROLE_NAME,ETCQ_CURRENTLEVEL as ETCQ_CURRENTLEVEL,");
                sqls.append(" MAX(ETCQ_CURRENTLEVELDATE) AS ETCQ_CURRENTLEVELDATE,SECT_KEYID AS SECTIONID, SECT_NAME AS SECT_NAME,CELL_KEYID AS CELLID,CELL_NAME AS CELL_NAME, EMPM_ROLEID as roleid, DECODE(ETCE_KEYID,null,' ',ETCM_KEYID),ETCE_KEYID AS ETCEKEYID ");
                sqls.append("  from GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM,GEN_TL_ROLEMST,ENT_TL_TRGCALQUAD,GEN_VW_FNLN  ");
                if(commonFilter.getKey()!=null)
                {
                      sqls.append(" ,ENT_TL_TRGCALMST,ENT_TL_TRGCALEMP,ENT_TL_TRGCALSESSION");
                }
                if(commonFilter.getFactoryId()!=null)
                {
                     sqls.append(" ,GEN_MV_FLIDHIERARCHY ");
                }
                sqls.append(" WHERE EMPM_KEYID = FRT_EMPM_KEYID AND EMPM_ROLEID = ROLE_KEYID(+) AND FRT_FNLN_KEYID=FNLN_KEYID ");
                sqls.append(" AND ETCQ_EMPM_KEYID(+)=FRT_EMPM_KEYID ");
                if(commonFilter.getFactoryId()!=null)
                {
                    //String flnid=dbActionTemplate.getSingleValue("GEN_MV_FLIDHIERARCHY", "FLID", "FNLN_ORIGINALID", commonFilter.getFactoryId());
                    sqls.append("  AND FNLN_KEYID=FLID  and INSTR(PARENTFLIDS||'/'||FLID, (SELECT FNLN_KEYID FROM  gen_tl_functionallocn   WHERE FNLN_ORIGINALID='"+commonFilter.getFactoryId()+"'))>0 ");
                }else
                {
                if(commonFilter.getUniquePos().equals("false"))
                {
                sqls.append(" AND FNLN_KEYID = '"+commonFilter.getFlid()+"'");
                }else{
                     sqls.append(" AND EMPM_ROLEID IN (SELECT ETCU_ROLE_KEYID FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID='"+commonFilter.getKey()+"')");
                }
                }
               
                if(commonFilter.getRoleLevel()!=null)
                {
                  sqls.append("  AND FRT_ROLE_KEYID = '"+commonFilter.getRoleLevel()+"' ");  
                }
                if(commonFilter.getKey()!=null)
                {
                      sqls.append(" AND ETCM_KEYID = ETCE_ETCM_KEYID(+) AND EMPM_KEYID = ETCE_EMPM_KEYID(+) AND ETCM_KEYID(+)='"+commonFilter.getKey()+"' AND ETCQ_TOPICID(+)=ETCM_TOPICID AND ETCE_ETCS_KEYID=ETCS_KEYID(+)");
                }
                sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
                sqls.append( "GROUP BY EMPM_NAME,EMPM_CODE,Empm_keyid,ROLE_NAME,SECT_NAME,SECT_KEYID,CELL_KEYID,CELL_NAME,EMPM_ROLEID,ETCQ_CURRENTLEVEL");
              
                if(commonFilter.getKey()!=null)
                {
                      sqls.append(" ,DECODE(ETCE_KEYID,null,' ',ETCM_KEYID),ETCE_KEYID,DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) ORDER BY ETCE_KEYID ");
                }
                else{
                    sqls.append("ORDER BY EMPM_KEYID");
                }
                //sqls.append(" AND FNLN_KEYID = '"+commonFilter.getFlid()+"' ");
              
              
                sqls.append(")");
               CommonMessage.debugMsg("inside the get data"+sqls);
                }
              
                else{
                  
                    sqls.append(" SELECT * FROM ( ");
                    sqls.append("  select  Distinct '' as selctVal,Empm_keyid as keyid,EMPM_CODE AS empcode,EMPM_NAME as name,DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) AS ses, ");
                    sqls.append("ROLE_NAME AS ROLENAME,ETCQ_CURRENTLEVEL as currLevel,");
                    sqls.append(" MAX(ETCQ_CURRENTLEVELDATE) AS LastUpdate,SECT_KEYID AS SECTIONID, SECT_NAME AS DMT,CELL_KEYID AS CELLID,CELL_NAME AS JH, EMPM_ROLEID as roleid, DECODE (ETCE_KEYID, NULL, ' ', ETCM_KEYID),ETCE_KEYID AS ETCEKEYID ");
                    sqls.append(" from GEN_TL_EMPLOYEEMST,GEN_TL_MOM_GROUPMST,GEN_TL_MOM_GROUPDTL,GEN_TL_ROLEMST, ");
                    sqls.append("  GEN_TL_FNLNROLETEAM,GEN_VW_FNLN,ENT_TL_TRGCALQUAD,ENT_TL_TRGCALMST,ENT_TL_TRGCALEMP,ENT_TL_TRGCALSESSION  WHERE MGRD_MGRM_KEYID=MGRM_KEYID  AND EMPM_KEYID = MGRD_EMPM_KEYID AND EMPM_ROLEID = ROLE_KEYID(+) ");
                    sqls.append(" AND FRT_FNLN_KEYID=FNLN_KEYID AND FRT_EMPM_KEYID=MGRD_EMPM_KEYID AND MGRD_EMPM_KEYID=ETCQ_EMPM_KEYID(+) AND MGRD_MGRM_KEYID='"+commonFilter.getRefdocid()+"' AND ETCE_ETCS_KEYID=ETCS_KEYID(+)");
                    sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
                    sqls.append(" AND ETCM_KEYID = ETCE_ETCM_KEYID(+) AND EMPM_KEYID = ETCE_EMPM_KEYID(+) AND ETCQ_TOPICID(+)=ETCM_TOPICID AND ETCM_KEYID(+)='"+commonFilter.getKey()+"'");
                    sqls.append( "GROUP BY EMPM_NAME,EMPM_CODE,Empm_keyid,ROLE_NAME,SECT_NAME,SECT_KEYID,CELL_KEYID,CELL_NAME,EMPM_ROLEID,DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME),ETCQ_CURRENTLEVEL,DECODE (ETCE_KEYID, NULL, ' ', ETCM_KEYID),ETCE_KEYID ORDER BY ETCE_KEYID");
    
                    sqls.append(")");
                  
                  
                }
              //  sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
                    String countsql = CommonFilterSqls.countSql(sqls.toString(), commonFilter.getGridFilter());
                    CommonMessage.debugMsg("countsql " + countsql);
                    String cntStr = dbActionTemplate.getSingleValue(countsql);
                    CommonMessage.debugMsg("cntStr " + cntStr);
                    int count = Integer.parseInt(cntStr);
                    CommonMessage.debugMsg("count " + count);
                    commonFilter.setTotalRecordCnt(count);
            
                  GridParams gridParams = new GridParams();
                  gridParams.setFromRow(commonFilter.getFromRow());
                  gridParams.setToRow(commonFilter.getToRow());
                  CommonMessage.debugMsg("From Row  :" +commonFilter.getFromRow());
                  CommonMessage.debugMsg("To Row  :" +commonFilter.getToRow());
                 
                  String oSql =  CommonFilterSqls.addPaginationParams(sqls.toString(), gridParams);
                  CommonMessage.debugMsg("osql " + oSql);
               CommonMessage.debugMsg("sql get selected employee"+sqls.toString());
               List<String[]> dataList =  dbActionTemplate.getDataList(oSql.toString());
               CommonMessage.debugMsg("getEmpList :" +dataList.size());
           //    CommonMessage.debugMsg("osql  :" +oSql);
              
               if( commonFilter.getViewClick() == 'Y'){
                   String totalCnt = cntStr;//paramValues.get(0);
                 CommonMessage.debugMsg("totalCnt...."+totalCnt);
                   boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
                   if(  isInteger ){
                       commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
                   }
               }
              
               return dataList;
    }*/

	//------------------ VIGNESH 25NOV2025 --------------------------------------------------------------//
	
	
//	public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter)
//            throws Exception {
//        // TODO Auto-generated method stub
//                StringBuffer sqls = new StringBuffer();
//                CommonMessage.debugMsg("commonFilter"+commonFilter.getKey());
//                CommonMessage.debugMsg("commonFilter.length"+commonFilter.getRefdocid());
//                CommonMessage.debugMsg("UNIQUE POSITION...... "+commonFilter.getUniquePos());
//               
//                if(commonFilter.getRefdocid()==null)
//                {  
//                sqls.append(" SELECT * FROM ( ");
//                sqls.append(" select  Distinct '' as selctVal,Empm_keyid as Empm_keyid,EMPM_CODE AS EMPM_CODE,EMPM_NAME as EMPM_NAME,DECODE(EMPM_EMPLOYEETYPE,'R','Employee','M','Manager','C','Contract','A','Asosciate','B','Badli'),DECODE(EMPM_GENDER,'M','Male','F','Female'),DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) as Ses, ");
//                sqls.append(" ROLE_NAME AS ROLE_NAME,ETCQ_CURRENTLEVEL as ETCQ_CURRENTLEVEL,");
//                sqls.append(" MAX(ETCQ_CURRENTLEVELDATE) AS ETCQ_CURRENTLEVELDATE,C.SECT_KEYID AS SECTIONID, C.SECT_NAME AS SECT_NAME,C.CELL_KEYID AS CELLID,C.CELL_NAME AS CELL_NAME, EMPM_ROLEID as roleid, DECODE(ETCE_KEYID,null,' ',ETCM_KEYID),ETCE_KEYID AS ETCEKEYID ");
//                sqls.append("  from GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM,GEN_TL_ROLEMST,ENT_TL_TRGCALQUAD,GEN_VW_FNLN C ");
//                if(commonFilter.getKey()!=null)
//                {
//                      sqls.append(" ,ENT_TL_TRGCALMST,ENT_TL_TRGCALEMP,ENT_TL_TRGCALSESSION ,GEN_TL_SECTIONMST A,GEN_TL_CELLMST B ");
//                }
//                if(commonFilter.getFactoryId()!=null)
//                {
//                     sqls.append(" ,GEN_MV_FLIDHIERARCHY ");
//                }
//                sqls.append(" WHERE EMPM_KEYID = FRT_EMPM_KEYID AND EMPM_ROLEID = ROLE_KEYID(+) AND FRT_FNLN_KEYID=FNLN_KEYID ");
//                sqls.append(" AND ETCQ_EMPM_KEYID(+)=FRT_EMPM_KEYID AND EMPM_ACTIVE='Y' ");
//                if(commonFilter.getFactoryId()!=null)
//                {
//                    //String flnid=dbActionTemplate.getSingleValue("GEN_MV_FLIDHIERARCHY", "FLID", "FNLN_ORIGINALID", commonFilter.getFactoryId());
//                    sqls.append("  AND FNLN_KEYID=FLID  and INSTR(PARENTFLIDS||'/'||FLID, (SELECT FNLN_KEYID FROM  gen_tl_functionallocn   WHERE FNLN_ORIGINALID='"+commonFilter.getFactoryId()+"'))>0 ");
//                }else
//                {
//                if(commonFilter.getUniquePos().equals("false"))
//                {
//                sqls.append(" AND FNLN_KEYID = '"+commonFilter.getFlid()+"'");
//                }else{
//                   // COMMENTED BY KIRAN ON 13SEP2023  sqls.append("AND FNLN_KEYID = '"+commonFilter.getFlid()+"' AND EMPM_ROLEID IN (SELECT ETCU_ROLE_KEYID FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID='"+commonFilter.getKey()+"')");
//                     sqls.append(" AND EMPM_ROLEID IN (SELECT ETCU_ROLE_KEYID FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID='"+commonFilter.getKey()+"')");
//
//                }
//                }
//              
//                if(commonFilter.getEmpwiseType()!=null &&UIUtils.isValidKeyId(commonFilter.getEmpwiseType()) )
//                {
//                	CommonMessage.debugMsg("Employee Type"+commonFilter.getEmpwiseType());
//                  sqls.append("  AND EMPM_EMPLOYEETYPE = '"+commonFilter.getEmpwiseType()+"' ");  
//                }
//                if(commonFilter.getEmpch()!=null)
//                {
//                  sqls.append("  AND EMPM_GENDER = '"+commonFilter.getEmpch()+"' ");  
//                }
//                if(commonFilter.getRoleLevel()!=null)
//                {
//                  sqls.append("  AND FRT_ROLE_KEYID = '"+commonFilter.getRoleLevel()+"' ");  
//                }
//                if(commonFilter.getKey()!=null)
//                {
//                      sqls.append(" AND ETCM_KEYID = ETCE_ETCM_KEYID(+) AND EMPM_KEYID = ETCE_EMPM_KEYID(+) AND ETCM_KEYID(+)='"+commonFilter.getKey()+"' AND ETCQ_TOPICID(+)=ETCM_TOPICID AND ETCE_ETCS_KEYID=ETCS_KEYID(+)");
//                      sqls.append(" AND C.CELL_KEYID(+) =B.CELL_KEYID AND C.SECT_KEYID(+) =A.SECT_KEYID AND B.CELL_SECTIONID(+)=A.SECT_KEYID ");
//                }
//                sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
//                sqls.append( "GROUP BY EMPM_NAME,EMPM_CODE,Empm_keyid,EMPM_EMPLOYEETYPE,EMPM_GENDER,ROLE_NAME,C.SECT_NAME,C.SECT_KEYID,C.CELL_KEYID,C.CELL_NAME,EMPM_ROLEID,ETCQ_CURRENTLEVEL");
//              
//                if(commonFilter.getKey()!=null)
//                {
//                      sqls.append(" ,DECODE(ETCE_KEYID,null,' ',ETCM_KEYID),ETCE_KEYID,DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) ORDER BY ETCE_KEYID ");
//                }
//                else{
//                    sqls.append("ORDER BY EMPM_KEYID");
//                }
//                //sqls.append(" AND FNLN_KEYID = '"+commonFilter.getFlid()+"' ");
//              
//              
//                sqls.append(")");
//               CommonMessage.debugMsg("inside the get data"+sqls);
//                }
//              
//                else{
//                    CommonMessage.debugMsg("Inside the Else");
//                    sqls.append(" SELECT * FROM ( ");
//                    sqls.append("  select  Distinct '' as selctVal,Empm_keyid as keyid,EMPM_CODE AS empcode,EMPM_NAME as name,DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) AS ses, ");
//                    sqls.append("ROLE_NAME AS ROLENAME,ETCQ_CURRENTLEVEL as currLevel,");
//                    sqls.append(" MAX(ETCQ_CURRENTLEVELDATE) AS LastUpdate,SECT_KEYID AS SECTIONID, SECT_NAME AS DMT,CELL_KEYID AS CELLID,CELL_NAME AS JH, EMPM_ROLEID as roleid, DECODE (ETCE_KEYID, NULL, ' ', ETCM_KEYID),ETCE_KEYID AS ETCEKEYID ");
//                    sqls.append(" from GEN_TL_EMPLOYEEMST,GEN_TL_MOM_GROUPMST,GEN_TL_MOM_GROUPDTL,GEN_TL_ROLEMST, ");
//                    sqls.append("  GEN_TL_FNLNROLETEAM,GEN_VW_FNLN,ENT_TL_TRGCALQUAD,ENT_TL_TRGCALMST,ENT_TL_TRGCALEMP,ENT_TL_TRGCALSESSION  WHERE MGRD_MGRM_KEYID=MGRM_KEYID  AND EMPM_KEYID = MGRD_EMPM_KEYID AND EMPM_ROLEID = ROLE_KEYID(+) ");
//                    sqls.append(" AND FRT_FNLN_KEYID=FNLN_KEYID AND FRT_EMPM_KEYID=MGRD_EMPM_KEYID AND MGRD_EMPM_KEYID=ETCQ_EMPM_KEYID(+) AND MGRD_MGRM_KEYID='"+commonFilter.getRefdocid()+"' AND ETCE_ETCS_KEYID=ETCS_KEYID(+)");
//                    sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
//                    sqls.append(" AND ETCM_KEYID = ETCE_ETCM_KEYID(+) AND EMPM_KEYID = ETCE_EMPM_KEYID(+) AND ETCQ_TOPICID(+)=ETCM_TOPICID AND ETCM_KEYID(+)='"+commonFilter.getKey()+"'");
//                    sqls.append( "GROUP BY EMPM_NAME,EMPM_CODE,Empm_keyid,ROLE_NAME,SECT_NAME,SECT_KEYID,CELL_KEYID,CELL_NAME,EMPM_ROLEID,DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME),ETCQ_CURRENTLEVEL,DECODE (ETCE_KEYID, NULL, ' ', ETCM_KEYID),ETCE_KEYID ORDER BY ETCE_KEYID");
//    
//                    sqls.append(")");
//                    CommonMessage.debugMsg("The Else Data::"+sqls);
//                  
//                }
//              //  sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
//                    String countsql = CommonFilterSqls.countSql(sqls.toString(), commonFilter.getGridFilter());
//                    CommonMessage.debugMsg("countsql " + countsql);
//                    String cntStr = dbActionTemplate.getSingleValue(countsql);
//                    CommonMessage.debugMsg("cntStr " + cntStr);
//                    int count = Integer.parseInt(cntStr);
//                    CommonMessage.debugMsg("count " + count);
//                    commonFilter.setTotalRecordCnt(count);
//            
//                  GridParams gridParams = new GridParams();
//                  gridParams.setFromRow(commonFilter.getFromRow());
//                  gridParams.setToRow(commonFilter.getToRow());
//                  CommonMessage.debugMsg("From Row  :" +commonFilter.getFromRow());
//                  CommonMessage.debugMsg("To Row  :" +commonFilter.getToRow());
//                 
//                  String oSql =  CommonFilterSqls.addPaginationParams(sqls.toString(), gridParams);
//                  CommonMessage.debugMsg("osql " + oSql);
//               CommonMessage.debugMsg("sql get selected employee"+sqls.toString());
//               List<String[]> dataList =  dbActionTemplate.getDataList(oSql.toString());
//               CommonMessage.debugMsg("getEmpList :" +dataList.size());
//           //    CommonMessage.debugMsg("osql  :" +oSql);
//              
//               if( commonFilter.getViewClick() == 'Y'){
//                   String totalCnt = cntStr;//paramValues.get(0);
//                 CommonMessage.debugMsg("totalCnt...."+totalCnt);
//                   boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
//                   if(  isInteger ){
//                       commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
//                   }
//               }
//              
//               return dataList;
//    }
	
//	public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter) throws Exception {
//	    StringBuffer sqls = new StringBuffer();
//	    CommonMessage.debugMsg("commonFilter" + commonFilter.getKey());
//	    CommonMessage.debugMsg("commonFilter.length" + commonFilter.getRefdocid());
//	    CommonMessage.debugMsg("UNIQUE POSITION...... " + commonFilter.getUniquePos());
//
//	    // =========================
//	    // 1) MAIN FLOW (REFDOCID IS NULL)
//	    // =========================
//	    if (commonFilter.getRefdocid() == null) {
//
//	        sqls.append(" SELECT * FROM ( ");
//	        sqls.append("  SELECT DISTINCT ");
//	        sqls.append("    '' AS selctVal,");
//	        sqls.append("    e.empm_keyid AS Empm_keyid,");
//	        sqls.append("    e.empm_code AS EMPM_CODE,");
//	        sqls.append("    e.empm_name AS EMPM_NAME,");
//
//	        // DECODE(EMPM_EMPLOYEETYPE,'R','Employee','M','Manager','C','Contract','A','Asosciate','B','Badli')
//	        sqls.append("    CASE e.empm_employeetype ");
//	        sqls.append("      WHEN 'R' THEN 'Employee' ");
//	        sqls.append("      WHEN 'M' THEN 'Manager' ");
//	        sqls.append("      WHEN 'C' THEN 'Contract' ");
//	        sqls.append("      WHEN 'A' THEN 'Asosciate' ");
//	        sqls.append("      WHEN 'B' THEN 'Badli' ");
//	        sqls.append("      ELSE '' ");
//	        sqls.append("    END AS EMPM_EMPLOYEETYPE_DESC,");
//
//	        // DECODE(EMPM_GENDER,'M','Male','F','Female')
//	        sqls.append("    CASE e.empm_gender ");
//	        sqls.append("      WHEN 'M' THEN 'Male' ");
//	        sqls.append("      WHEN 'F' THEN 'Female' ");
//	        sqls.append("      ELSE '' ");
//	        sqls.append("    END AS EMPM_GENDER_DESC,");
//
//	        // DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) as Ses
//	        sqls.append("    CASE ");
//	        sqls.append("      WHEN etce.etce_etcs_keyid = '{}' THEN ' ' ");
//	        sqls.append("      ELSE etcs.etcs_name ");
//	        sqls.append("    END AS Ses,");
//
//	        sqls.append("    r.role_name AS ROLE_NAME,");
//	        sqls.append("    etcq.etcq_currentlevel AS ETCQ_CURRENTLEVEL,");
//	        sqls.append("    MAX(etcq.etcq_currentleveldate) AS ETCQ_CURRENTLEVELDATE,");
//	        sqls.append("    c.sect_keyid AS SECTIONID,");
//	        sqls.append("    c.sect_name AS SECT_NAME,");
//	        sqls.append("    c.cell_keyid AS CELLID,");
//	        sqls.append("    c.cell_name AS CELL_NAME,");
//	        sqls.append("    e.empm_roleid AS roleid,");
//
//	        // DECODE(ETCE_KEYID,null,' ',ETCM_KEYID)
//	        sqls.append("    CASE ");
//	        sqls.append("      WHEN etce.etce_keyid IS NULL THEN ' ' ");
//	        sqls.append("      ELSE etcm.etcm_keyid ");
//	        sqls.append("    END AS ETCM_KEYID_DISPLAY,");
//
//	        sqls.append("    etce.etce_keyid AS ETCEKEYID ");
//
//	        sqls.append("  FROM gen_tl_employeemst       e ");
//	        sqls.append("  JOIN gen_tl_fnlnroleteam      frt  ON e.empm_keyid = frt.frt_empm_keyid ");
//	        sqls.append("  JOIN gen_vw_fnln              c    ON frt.frt_fnln_keyid = c.fnln_keyid ");
//
//	        // EMPM_ROLEID = ROLE_KEYID(+)
//	        sqls.append("  LEFT JOIN gen_tl_rolemst      r    ON e.empm_roleid = r.role_keyid ");
//
//	        // ETCQ_EMPM_KEYID(+)=FRT_EMPM_KEYID AND ETCQ_TOPICID(+)=ETCM_TOPICID
//	        sqls.append("  LEFT JOIN ent_tl_trgcalquad   etcq ON etcq.etcq_empm_keyid = frt.frt_empm_keyid ");
//
//	        // Training calendar master / emp / session
//	        // ETCM_KEYID = ETCE_ETCM_KEYID(+), EMPM_KEYID = ETCE_EMPM_KEYID(+), ETCM_KEYID(+)='key'
//	        if (commonFilter.getKey() != null && !commonFilter.getKey().trim().isEmpty()) {
//	            sqls.append("  LEFT JOIN ent_tl_trgcalmst    etcm ON etcm.etcm_keyid = '")
//	                .append(commonFilter.getKey()).append("' ");
//	            sqls.append("  LEFT JOIN ent_tl_trgcalemp    etce ON etce.etce_empm_keyid = e.empm_keyid ");
//	            sqls.append("                                   AND etce.etce_etcm_keyid = etcm.etcm_keyid ");
//	            sqls.append("  LEFT JOIN ent_tl_trgcalsession etcs ON etcs.etcs_keyid = etce.etce_etcs_keyid ");
//	            // tie quad to calendar topic
//	            sqls.append("                                   AND etcq.etcq_topicid = etcm.etcm_topicid ");
//	        } else {
//	            // Safety: if key is null, still join ETCE/ETCS loosely (to avoid invalid columns)
//	            sqls.append("  LEFT JOIN ent_tl_trgcalemp    etce ON etce.etce_empm_keyid = e.empm_keyid ");
//	            sqls.append("  LEFT JOIN ent_tl_trgcalsession etcs ON etcs.etcs_keyid = etce.etce_etcs_keyid ");
//	            sqls.append("  LEFT JOIN ent_tl_trgcalmst    etcm ON 1 = 0 "); // no rows, but keeps name
//	        }
//
//	        // GEN_MV_FLIDHIERARCHY only matters when factoryId is used. 
//	        // In Oracle it was added only conditionally in FROM. We keep the same.
//	        if (commonFilter.getFactoryId() != null && !commonFilter.getFactoryId().trim().isEmpty()) {
//	            sqls.append("  JOIN gen_mv_flidhierarchy     h    ON c.fnln_keyid = h.flid ");
//	        }
//
//	        sqls.append("  WHERE e.empm_active = 'Y' ");
//
//	        // Factory hierarchy / FNLN / UniquePos logic
//	        if (commonFilter.getFactoryId() != null && !commonFilter.getFactoryId().trim().isEmpty()) {
//	            // AND FNLN_KEYID=FLID and INSTR(PARENTFLIDS||'/'||FLID, (SELECT FNLN_KEYID ...))>0
//	            sqls.append("    AND position( (SELECT fnln_keyid ");
//	            sqls.append("                     FROM gen_tl_functionallocn ");
//	            sqls.append("                    WHERE fnln_originalid = '").append(commonFilter.getFactoryId()).append("') ");
//	            sqls.append("                 in (h.parentflids || '/' || h.flid) ) > 0 ");
//	        } else {
//	            if ("false".equalsIgnoreCase(commonFilter.getUniquePos())) {
//	                // AND FNLN_KEYID = 'flid'
//	                sqls.append("    AND c.fnln_keyid = '").append(commonFilter.getFlid()).append("' ");
//	            } else {
//	                // AND EMPM_ROLEID IN (SELECT ETCU_ROLE_KEYID FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID='key')
//	                sqls.append("    AND e.empm_roleid IN ( ");
//	                sqls.append("          SELECT etcu_role_keyid ");
//	                sqls.append("            FROM ent_tl_trgcalunqp ");
//	                sqls.append("           WHERE etcu_etcm_keyid = '").append(commonFilter.getKey()).append("'");
//	                sqls.append("        ) ");
//	            }
//	        }
//
//	        // Employee type
//	        if (commonFilter.getEmpwiseType() != null && UIUtils.isValidKeyId(commonFilter.getEmpwiseType())) {
//	            sqls.append("    AND e.empm_employeetype = '").append(commonFilter.getEmpwiseType()).append("' ");
//	        }
//
//	        // Gender
//	        if (commonFilter.getEmpch() != null && !commonFilter.getEmpch().trim().isEmpty()) {
//	            sqls.append("    AND e.empm_gender = '").append(commonFilter.getEmpch()).append("' ");
//	        }
//
//	        // Role Level
//	        if (commonFilter.getRoleLevel() != null && !commonFilter.getRoleLevel().trim().isEmpty()) {
//	            sqls.append("    AND frt.frt_role_keyid = '").append(commonFilter.getRoleLevel()).append("' ");
//	        }
//
//	        // Grid filter (already Postgres compatible in your helper)
//	        sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
//
//	        sqls.append("  GROUP BY ");
//	        sqls.append("    e.empm_name,");
//	        sqls.append("    e.empm_code,");
//	        sqls.append("    e.empm_keyid,");
//	        sqls.append("    EMPM_EMPLOYEETYPE_DESC,");
//	        sqls.append("    EMPM_GENDER_DESC,");
//	        sqls.append("    Ses,");
//	        sqls.append("    r.role_name,");
//	        sqls.append("    etcq.etcq_currentlevel,");
//	        sqls.append("    c.sect_name,");
//	        sqls.append("    c.sect_keyid,");
//	        sqls.append("    c.cell_keyid,");
//	        sqls.append("    c.cell_name,");
//	        sqls.append("    e.empm_roleid,");
//	        sqls.append("    ETCM_KEYID_DISPLAY,");
//	        sqls.append("    etce.etce_keyid ");
//
//	        if (commonFilter.getKey() != null && !commonFilter.getKey().trim().isEmpty()) {
//	            sqls.append("  ORDER BY etce.etce_keyid ");
//	        } else {
//	            sqls.append("  ORDER BY e.empm_keyid ");
//	        }
//
//	        sqls.append(" ) ");
//	        CommonMessage.debugMsg("inside the get data" + sqls.toString());
//
//	    // =========================
//	    // 2) ELSE FLOW (REFDOCID IS NOT NULL)
//	    // =========================
//	    } else {
//
//	        CommonMessage.debugMsg("Inside the Else");
//	        sqls.append(" SELECT * FROM ( ");
//	        sqls.append("  SELECT DISTINCT ");
//	        sqls.append("    '' AS selctVal,");
//	        sqls.append("    e.empm_keyid AS keyid,");
//	        sqls.append("    e.empm_code AS empcode,");
//	        sqls.append("    e.empm_name AS name,");
//
//	        // DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) AS ses
//	        sqls.append("    CASE ");
//	        sqls.append("      WHEN etce.etce_etcs_keyid = '{}' THEN ' ' ");
//	        sqls.append("      ELSE etcs.etcs_name ");
//	        sqls.append("    END AS ses,");
//
//	        sqls.append("    r.role_name AS ROLENAME,");
//	        sqls.append("    etcq.etcq_currentlevel AS currLevel,");
//	        sqls.append("    MAX(etcq.etcq_currentleveldate) AS LastUpdate,");
//	        sqls.append("    c.sect_keyid AS SECTIONID,");
//	        sqls.append("    c.sect_name AS DMT,");
//	        sqls.append("    c.cell_keyid AS CELLID,");
//	        sqls.append("    c.cell_name AS JH,");
//	        sqls.append("    e.empm_roleid AS roleid,");
//
//	        // DECODE (ETCE_KEYID, NULL, ' ', ETCM_KEYID)
//	        sqls.append("    CASE ");
//	        sqls.append("      WHEN etce.etce_keyid IS NULL THEN ' ' ");
//	        sqls.append("      ELSE etcm.etcm_keyid ");
//	        sqls.append("    END AS ETCM_KEYID_DISPLAY,");
//
//	        sqls.append("    etce.etce_keyid AS ETCEKEYID ");
//
//	        sqls.append("  FROM gen_tl_mom_groupmst    mgrm ");
//	        sqls.append("  JOIN gen_tl_mom_groupdtl    mgrd ON mgrd.mgrd_mgrm_keyid = mgrm.mgrm_keyid ");
//	        sqls.append("  JOIN gen_tl_employeemst     e    ON e.empm_keyid = mgrd.mgrd_empm_keyid ");
//
//	        // EMPM_ROLEID = ROLE_KEYID(+)
//	        sqls.append("  LEFT JOIN gen_tl_rolemst    r    ON e.empm_roleid = r.role_keyid ");
//
//	        // FRT_FNLN_KEYID=FNLN_KEYID AND FRT_EMPM_KEYID=MGRD_EMPM_KEYID
//	        sqls.append("  JOIN gen_tl_fnlnroleteam    frt  ON frt.frt_empm_keyid = mgrd.mgrd_empm_keyid ");
//	        sqls.append("  JOIN gen_vw_fnlN            c    ON c.fnln_keyid = frt.frt_fnln_keyid ");
//
//	        // Training calendar and quad/session
//	        sqls.append("  LEFT JOIN ent_tl_trgcalmst  etcm ON etcm.etcm_keyid = '")
//	            .append(commonFilter.getKey()).append("' ");
//	        sqls.append("  LEFT JOIN ent_tl_trgcalemp  etce ON etce.etce_empm_keyid = e.empm_keyid ");
//	        sqls.append("                                  AND etce.etce_etcm_keyid = etcm.etcm_keyid ");
//	        sqls.append("  LEFT JOIN ent_tl_trgcalquad etcq ON etcq.etcq_empm_keyid = mgrd.mgrd_empm_keyid ");
//	        sqls.append("                                  AND etcq.etcq_topicid = etcm.etcm_topicid ");
//	        sqls.append("  LEFT JOIN ent_tl_trgcalsession etcs ON etcs.etcs_keyid = etce.etce_etcs_keyid ");
//
//	        // MGRD_MGRM_KEYID='refdocid'
//	        sqls.append("  WHERE mgrd.mgrd_mgrm_keyid = '").append(commonFilter.getRefdocid()).append("' ");
//
//	        // Grid filter
//	        sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
//
//	        sqls.append("  GROUP BY ");
//	        sqls.append("    e.empm_name,");
//	        sqls.append("    e.empm_code,");
//	        sqls.append("    e.empm_keyid,");
//	        sqls.append("    r.role_name,");
//	        sqls.append("    c.sect_name,");
//	        sqls.append("    c.sect_keyid,");
//	        sqls.append("    c.cell_keyid,");
//	        sqls.append("    c.cell_name,");
//	        sqls.append("    e.empm_roleid,");
//	        sqls.append("    ses,");
//	        sqls.append("    etcq.etcq_currentlevel,");
//	        sqls.append("    ETCM_KEYID_DISPLAY,");
//	        sqls.append("    etce.etce_keyid ");
//
//	        sqls.append("  ORDER BY etce.etce_keyid ");
//
//	        sqls.append(" ) ");
//	        CommonMessage.debugMsg("The Else Data::" + sqls.toString());
//	    }
//
//	    // ====== COUNT + PAGINATION (unchanged) ======
//	    String countsql = CommonFilterSqls.countSql(sqls.toString(), commonFilter.getGridFilter());
//	    CommonMessage.debugMsg("countsql " + countsql);
//	    String cntStr = dbActionTemplate.getSingleValue(countsql);
//	    CommonMessage.debugMsg("cntStr " + cntStr);
//	    int count = Integer.parseInt(cntStr);
//	    CommonMessage.debugMsg("count " + count);
//	    commonFilter.setTotalRecordCnt(count);
//
//	    GridParams gridParams = new GridParams();
//	    gridParams.setFromRow(commonFilter.getFromRow());
//	    gridParams.setToRow(commonFilter.getToRow());
//	    CommonMessage.debugMsg("From Row  :" + commonFilter.getFromRow());
//	    CommonMessage.debugMsg("To Row  :" + commonFilter.getToRow());
//
//	    String oSql = CommonFilterSqls.addPaginationParams(sqls.toString(), gridParams);
//	    CommonMessage.debugMsg("osql " + oSql);
//	    CommonMessage.debugMsg("sql get selected employee" + sqls.toString());
//
//	    List<String[]> dataList = dbActionTemplate.getDataList(oSql.toString());
//	    CommonMessage.debugMsg("getEmpList :" + dataList.size());
//
//	    if (commonFilter.getViewClick() == 'Y') {
//	        String totalCnt = cntStr;
//	        CommonMessage.debugMsg("totalCnt...." + totalCnt);
//	        boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
//	        if (isInteger) {
//	            commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
//	        }
//	    }
//
//	    return dataList;
//	}

	// --- Vignesh Fixing duplicate records --- //	 -- fixed method for duplicate records ---26Dec2025 ---//
	
		public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter) throws Exception {
		    StringBuffer sqls = new StringBuffer();
		    CommonMessage.debugMsg("commonFilter" + commonFilter.getKey());
		    CommonMessage.debugMsg("commonFilter.length" + commonFilter.getRefdocid());
		    CommonMessage.debugMsg("UNIQUE POSITION...... " + commonFilter.getUniquePos());

		    // =========================
		    // 1) MAIN FLOW (REFDOCID IS NULL)
		    //    - Here we de-duplicate per Empm_keyid + ETCM_KEYID_DISPLAY
		    //      keeping the highest level / latest date.
		    // =========================
		    if (commonFilter.getRefdocid() == null) {

		        sqls.append(" SELECT * FROM ( ");
		        sqls.append("  SELECT ");
		        sqls.append("    selctVal,");
		        sqls.append("    Empm_keyid,");
		        sqls.append("    EMPM_CODE,");
		        sqls.append("    EMPM_NAME,");
		        sqls.append("    EMPM_EMPLOYEETYPE_DESC,");
		        sqls.append("    EMPM_GENDER_DESC,");
		        sqls.append("    Ses,");
		        sqls.append("    ROLE_NAME,");
		        sqls.append("    ETCQ_CURRENTLEVEL,");
		        sqls.append("    ETCQ_CURRENTLEVELDATE,");
		        sqls.append("    SECTIONID,");
		        sqls.append("    SECT_NAME,");
		        sqls.append("    CELLID,");
		        sqls.append("    CELL_NAME,");
		        sqls.append("    roleid,");
		        sqls.append("    ETCM_KEYID_DISPLAY,");
		        sqls.append("    ETCEKEYID ");
		        sqls.append("  FROM ( ");
		        sqls.append("    SELECT base.*, ");
		        sqls.append("           ROW_NUMBER() OVER ( ");
		        sqls.append("             PARTITION BY base.Empm_keyid, base.ETCM_KEYID_DISPLAY ");
		        sqls.append("             ORDER BY base.ETCQ_CURRENTLEVEL DESC, ");
		        sqls.append("                      base.ETCQ_CURRENTLEVELDATE DESC, ");
		        sqls.append("                      base.ETCEKEYID DESC ");
		        sqls.append("           ) AS LVL_RN ");
		        sqls.append("    FROM ( ");

		        // ===== ORIGINAL INNER QUERY STARTS HERE (unchanged) =====
		        sqls.append("      SELECT DISTINCT ");
		        sqls.append("        '' AS selctVal,");
		        sqls.append("        e.empm_keyid AS Empm_keyid,");
		        sqls.append("        e.empm_code AS EMPM_CODE,");
		        sqls.append("        e.empm_name AS EMPM_NAME,");

		        // DECODE(EMPM_EMPLOYEETYPE,'R','Employee','M','Manager','C','Contract','A','Asosciate','B','Badli')
		        sqls.append("        CASE e.empm_employeetype ");
		        sqls.append("          WHEN 'R' THEN 'Employee' ");
		        sqls.append("          WHEN 'M' THEN 'Manager' ");
		        sqls.append("          WHEN 'C' THEN 'Contract' ");
		        sqls.append("          WHEN 'A' THEN 'Asosciate' ");
		        sqls.append("          WHEN 'B' THEN 'Badli' ");
		        sqls.append("          ELSE '' ");
		        sqls.append("        END AS EMPM_EMPLOYEETYPE_DESC,");

		        // DECODE(EMPM_GENDER,'M','Male','F','Female')
		        sqls.append("        CASE e.empm_gender ");
		        sqls.append("          WHEN 'M' THEN 'Male' ");
		        sqls.append("          WHEN 'F' THEN 'Female' ");
		        sqls.append("          ELSE '' ");
		        sqls.append("        END AS EMPM_GENDER_DESC,");

		        // DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) as Ses
		        sqls.append("        CASE ");
		        sqls.append("          WHEN etce.etce_etcs_keyid = '{}' THEN ' ' ");
		        sqls.append("          ELSE etcs.etcs_name ");
		        sqls.append("        END AS Ses,");

		        sqls.append("        r.role_name AS ROLE_NAME,");
		        sqls.append("        etcq.etcq_currentlevel AS ETCQ_CURRENTLEVEL,");
		        sqls.append("        MAX(etcq.etcq_currentleveldate) AS ETCQ_CURRENTLEVELDATE,");
		        sqls.append("        c.sect_keyid AS SECTIONID,");
		        sqls.append("        c.sect_name AS SECT_NAME,");
		        sqls.append("        c.cell_keyid AS CELLID,");
		        sqls.append("        c.cell_name AS CELL_NAME,");
		        sqls.append("        e.empm_roleid AS roleid,");

		        // DECODE(ETCE_KEYID,null,' ',ETCM_KEYID)
		        sqls.append("        CASE ");
		        sqls.append("          WHEN etce.etce_keyid IS NULL THEN ' ' ");
		        sqls.append("          ELSE etcm.etcm_keyid ");
		        sqls.append("        END AS ETCM_KEYID_DISPLAY,");

		        sqls.append("        etce.etce_keyid AS ETCEKEYID ");

		        sqls.append("      FROM gen_tl_employeemst       e ");
		        sqls.append("      JOIN gen_tl_fnlnroleteam      frt  ON e.empm_keyid = frt.frt_empm_keyid ");
		        sqls.append("      JOIN gen_vw_fnln              c    ON frt.frt_fnln_keyid = c.fnln_keyid ");

		        // EMPM_ROLEID = ROLE_KEYID(+)
		        sqls.append("      LEFT JOIN gen_tl_rolemst      r    ON e.empm_roleid = r.role_keyid ");

		        // ETCQ_EMPM_KEYID(+)=FRT_EMPM_KEYID AND ETCQ_TOPICID(+)=ETCM_TOPICID
		        sqls.append("      LEFT JOIN ent_tl_trgcalquad   etcq ON etcq.etcq_empm_keyid = frt.frt_empm_keyid ");

		        // Training calendar master / emp / session
		        // ETCM_KEYID = ETCE_ETCM_KEYID(+), EMPM_KEYID = ETCE_EMPM_KEYID(+), ETCM_KEYID(+)='key'
		        if (commonFilter.getKey() != null && !commonFilter.getKey().trim().isEmpty()) {
		            sqls.append("      LEFT JOIN ent_tl_trgcalmst    etcm ON etcm.etcm_keyid = '")
		                .append(commonFilter.getKey()).append("' ");
		            sqls.append("      LEFT JOIN ent_tl_trgcalemp    etce ON etce.etce_empm_keyid = e.empm_keyid ");
		            sqls.append("                                       AND etce.etce_etcm_keyid = etcm.etcm_keyid ");
		            sqls.append("      LEFT JOIN ent_tl_trgcalsession etcs ON etcs.etcs_keyid = etce.etce_etcs_keyid ");
		            // tie quad to calendar topic
		            sqls.append("                                       AND etcq.etcq_topicid = etcm.etcm_topicid ");
		        } else {
		            // Safety: if key is null, still join ETCE/ETCS loosely (to avoid invalid columns)
		            sqls.append("      LEFT JOIN ent_tl_trgcalemp    etce ON etce.etce_empm_keyid = e.empm_keyid ");
		            sqls.append("      LEFT JOIN ent_tl_trgcalsession etcs ON etcs.etcs_keyid = etce.etce_etcs_keyid ");
		            sqls.append("      LEFT JOIN ent_tl_trgcalmst    etcm ON 1 = 0 "); // no rows, but keeps name
		        }

		        // GEN_MV_FLIDHIERARCHY only matters when factoryId is used. 
		        // In Oracle it was added only conditionally in FROM. We keep the same.
		        if (commonFilter.getFactoryId() != null && !commonFilter.getFactoryId().trim().isEmpty()) {
		            sqls.append("      JOIN gen_mv_flidhierarchy     h    ON c.fnln_keyid = h.flid ");
		        }

		        sqls.append("      WHERE e.empm_active = 'Y' ");

		        // Factory hierarchy / FNLN / UniquePos logic
		        if (commonFilter.getFactoryId() != null && !commonFilter.getFactoryId().trim().isEmpty()) {
		            // AND FNLN_KEYID=FLID and INSTR(PARENTFLIDS||'/'||FLID, (SELECT FNLN_KEYID ...))>0
		            sqls.append("        AND position( (SELECT fnln_keyid ");
		            sqls.append("                         FROM gen_tl_functionallocn ");
		            sqls.append("                        WHERE fnln_originalid = '").append(commonFilter.getFactoryId()).append("') ");
		            sqls.append("                     in (h.parentflids || '/' || h.flid) ) > 0 ");
		        } else {
		            if ("false".equalsIgnoreCase(commonFilter.getUniquePos())) {
		                // AND FNLN_KEYID = 'flid'
		                sqls.append("        AND c.fnln_keyid = '").append(commonFilter.getFlid()).append("' ");
		            } else {
		                // AND EMPM_ROLEID IN (SELECT ETCU_ROLE_KEYID FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID='key')
		                sqls.append("        AND e.empm_roleid IN ( ");
		                sqls.append("              SELECT etcu_role_keyid ");
		                sqls.append("                FROM ent_tl_trgcalunqp ");
		                sqls.append("               WHERE etcu_etcm_keyid = '").append(commonFilter.getKey()).append("'");
		                sqls.append("            ) ");
		            }
		        }

		        // Employee type
		        if (commonFilter.getEmpwiseType() != null && UIUtils.isValidKeyId(commonFilter.getEmpwiseType())) {
		            sqls.append("        AND e.empm_employeetype = '").append(commonFilter.getEmpwiseType()).append("' ");
		        }

		        // Gender
		        if (commonFilter.getEmpch() != null && !commonFilter.getEmpch().trim().isEmpty()) {
		            sqls.append("        AND e.empm_gender = '").append(commonFilter.getEmpch()).append("' ");
		        }

		        // Role Level
		        if (commonFilter.getRoleLevel() != null && !commonFilter.getRoleLevel().trim().isEmpty()) {
		            sqls.append("        AND frt.frt_role_keyid = '").append(commonFilter.getRoleLevel()).append("' ");
		        }

		        // Grid filter (already Postgres compatible in your helper)
		        sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));

		        sqls.append("      GROUP BY ");
		        sqls.append("        e.empm_name,");
		        sqls.append("        e.empm_code,");
		        sqls.append("        e.empm_keyid,");
		        sqls.append("        EMPM_EMPLOYEETYPE_DESC,");
		        sqls.append("        EMPM_GENDER_DESC,");
		        sqls.append("        Ses,");
		        sqls.append("        r.role_name,");
		        sqls.append("        etcq.etcq_currentlevel,");
		        sqls.append("        c.sect_name,");
		        sqls.append("        c.sect_keyid,");
		        sqls.append("        c.cell_keyid,");
		        sqls.append("        c.cell_name,");
		        sqls.append("        e.empm_roleid,");
		        sqls.append("        ETCM_KEYID_DISPLAY,");
		        sqls.append("        etce.etce_keyid ");

		        if (commonFilter.getKey() != null && !commonFilter.getKey().trim().isEmpty()) {
		            sqls.append("      ORDER BY etce.etce_keyid ");
		        } else {
		            sqls.append("      ORDER BY e.empm_keyid ");
		        }

		        sqls.append("    ) base ");
		        sqls.append("  ) lvl ");
		        sqls.append("  WHERE lvl.LVL_RN = 1 ");
		        sqls.append(" ) ");

		        CommonMessage.debugMsg("inside the get data" + sqls.toString());

		    // =========================
		    // 2) ELSE FLOW (REFDOCID IS NOT NULL)
		    //    - LEFT COMPLETELY UNCHANGED
		    // =========================
		    } else {

		        CommonMessage.debugMsg("Inside the Else");
		        sqls.append(" SELECT * FROM ( ");
		        sqls.append("  SELECT DISTINCT ");
		        sqls.append("    '' AS selctVal,");
		        sqls.append("    e.empm_keyid AS keyid,");
		        sqls.append("    e.empm_code AS empcode,");
		        sqls.append("    e.empm_name AS name,");

		        // DECODE (ETCE_ETCS_KEYID, '{}', ' ', ETCS_NAME) AS ses
		        sqls.append("    CASE ");
		        sqls.append("      WHEN etce.etce_etcs_keyid = '{}' THEN ' ' ");
		        sqls.append("      ELSE etcs.etcs_name ");
		        sqls.append("    END AS ses,");

		        sqls.append("    r.role_name AS ROLENAME,");
		        sqls.append("    etcq.etcq_currentlevel AS currLevel,");
		        sqls.append("    MAX(etcq.etcq_currentleveldate) AS LastUpdate,");
		        sqls.append("    c.sect_keyid AS SECTIONID,");
		        sqls.append("    c.sect_name AS DMT,");
		        sqls.append("    c.cell_keyid AS CELLID,");
		        sqls.append("    c.cell_name AS JH,");
		        sqls.append("    e.empm_roleid AS roleid,");

		        // DECODE (ETCE_KEYID, NULL, ' ', ETCM_KEYID)
		        sqls.append("    CASE ");
		        sqls.append("      WHEN etce.etce_keyid IS NULL THEN ' ' ");
		        sqls.append("      ELSE etcm.etcm_keyid ");
		        sqls.append("    END AS ETCM_KEYID_DISPLAY,");

		        sqls.append("    etce.etce_keyid AS ETCEKEYID ");

		        sqls.append("  FROM gen_tl_mom_groupmst    mgrm ");
		        sqls.append("  JOIN gen_tl_mom_groupdtl    mgrd ON mgrd.mgrd_mgrm_keyid = mgrm.mgrm_keyid ");
		        sqls.append("  JOIN gen_tl_employeemst     e    ON e.empm_keyid = mgrd.mgrd_empm_keyid ");

		        // EMPM_ROLEID = ROLE_KEYID(+)
		        sqls.append("  LEFT JOIN gen_tl_rolemst    r    ON e.empm_roleid = r.role_keyid ");

		        // FRT_FNLN_KEYID=FNLN_KEYID AND FRT_EMPM_KEYID=MGRD_EMPM_KEYID
		        sqls.append("  JOIN gen_tl_fnlnroleteam    frt  ON frt.frt_empm_keyid = mgrd.mgrd_empm_keyid ");
		        sqls.append("  JOIN gen_vw_fnlN            c    ON c.fnln_keyid = frt.frt_fnln_keyid ");

		        // Training calendar and quad/session
		        sqls.append("  LEFT JOIN ent_tl_trgcalmst  etcm ON etcm.etcm_keyid = '")
		            .append(commonFilter.getKey()).append("' ");
		        sqls.append("  LEFT JOIN ent_tl_trgcalemp  etce ON etce.etce_empm_keyid = e.empm_keyid ");
		        sqls.append("                                  AND etce.etce_etcm_keyid = etcm.etcm_keyid ");
		        sqls.append("  LEFT JOIN ent_tl_trgcalquad etcq ON etcq.etcq_empm_keyid = mgrd.mgrd_empm_keyid ");
		        sqls.append("                                  AND etcq.etcq_topicid = etcm.etcm_topicid ");
		        sqls.append("  LEFT JOIN ent_tl_trgcalsession etcs ON etcs.etcs_keyid = etce.etce_etcs_keyid ");

		        // MGRD_MGRM_KEYID='refdocid'
		        sqls.append("  WHERE mgrd.mgrd_mgrm_keyid = '").append(commonFilter.getRefdocid()).append("' ");

		        // Grid filter
		        sqls.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));

		        sqls.append("  GROUP BY ");
		        sqls.append("    e.empm_name,");
		        sqls.append("    e.empm_code,");
		        sqls.append("    e.empm_keyid,");
		        sqls.append("    r.role_name,");
		        sqls.append("    c.sect_name,");
		        sqls.append("    c.sect_keyid,");
		        sqls.append("    c.cell_keyid,");
		        sqls.append("    c.cell_name,");
		        sqls.append("    e.empm_roleid,");
		        sqls.append("    ses,");
		        sqls.append("    etcq.etcq_currentlevel,");
		        sqls.append("    ETCM_KEYID_DISPLAY,");
		        sqls.append("    etce.etce_keyid ");

		        sqls.append("  ORDER BY etce.etce_keyid ");

		        sqls.append(" ) ");
		        CommonMessage.debugMsg("The Else Data::" + sqls.toString());
		    }

		    // ====== COUNT + PAGINATION (unchanged) ======
		    String countsql = CommonFilterSqls.countSql(sqls.toString(), commonFilter.getGridFilter());
		    CommonMessage.debugMsg("countsql " + countsql);
		    String cntStr = dbActionTemplate.getSingleValue(countsql);
		    CommonMessage.debugMsg("cntStr " + cntStr);
		    int count = Integer.parseInt(cntStr);
		    CommonMessage.debugMsg("count " + count);
		    commonFilter.setTotalRecordCnt(count);

		    GridParams gridParams = new GridParams();
		    gridParams.setFromRow(commonFilter.getFromRow());
		    gridParams.setToRow(commonFilter.getToRow());
		    CommonMessage.debugMsg("From Row  :" + commonFilter.getFromRow());
		    CommonMessage.debugMsg("To Row  :" + commonFilter.getToRow());

		    String oSql = CommonFilterSqls.addPaginationParams(sqls.toString(), gridParams);
		    CommonMessage.debugMsg("osql " + oSql);
		    CommonMessage.debugMsg("sql get selected employee" + sqls.toString());

		    List<String[]> dataList = dbActionTemplate.getDataList(oSql.toString());
		    CommonMessage.debugMsg("getEmpList :" + dataList.size());

		    if (commonFilter.getViewClick() == 'Y') {
		        String totalCnt = cntStr;
		        CommonMessage.debugMsg("totalCnt...." + totalCnt);
		        boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		        if (isInteger) {
		            commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
		        }
		    }

		    return dataList;
		}


//	 -- fixed method for duplicate records ---26Dec2025 ---//
	
	//------------------ VIGNESH 25NOV2025 --------------------------------------------------------------//
public EntTlTragcalmst create(EntTlTragcalmst newentTlTragcalmst) 	throws Exception,BusinessApplicationExceptions, ValidationException {
		List<String> sqls = new ArrayList<String>();		
		EntTlTragcalmstSql entTlTragcalmstSql = new EntTlTragcalmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlTrgCalSessionSql entTlTrgCalSessionSql=new EntTlTrgCalSessionSql(); 
		EntTlTrgCalUnqpSql entTlTrgCalUnqpSql=new EntTlTrgCalUnqpSql();            
	

          //if(newentTlTragcalmst.getFaculty() != null)
	   //   if(newentTlTragcalmst.getsessionMaster()!= null)
		
		    newentTlTragcalmst.setEtcmKeyid(dbActionTemplate.getSequenceNumber(EntTlTragcalmstSql.TBL_ENT_TL_TRGCALMST,15,"ETC","",""));
			sqls.add(EntTlTragcalmstSql.getInsertSql(entTlTragcalmstSql.getFtymDbFields(),newentTlTragcalmst.getSaveArray())); // add insert sql for master table
			//dbActionTemplate.executeStatements(sqls); // execute the block of sqls

		/*&	!UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getBachKeyid())
			 && !Constants.futureNullDate.equals(newEntTlProgrammst.getBatchmaster().getBachTilldate())
			 && UIUtils.isValidKeyId(newEntTlProgrammst.getBatchmaster().getBachTilldate()) 
			 EtcsTillDate*/
			       if(newentTlTragcalmst.getsessionMaster() != null && 
					 !UIUtils.isValidKeyId(newentTlTragcalmst.getsessionMaster().getEtcsKeyid())
					 && !Constants.futureNullDate.equals(newentTlTragcalmst.getsessionMaster().getEtcsTillDate())
					 && UIUtils.isValidKeyId(newentTlTragcalmst.getsessionMaster().getEtcsTillDate()) 
					 ){
			  if(newentTlTragcalmst.getsessionMaster() != null ){
				//  String count=dbActionTemplate.getSingleValue("ENT_TL_TRGCALSESSION", "COUNT(*)", "ETCS_ETCM_KEYID", newentTlTragcalmst.getsessionMaster().getEtcsEtcmKeyid());
				//  int cnt=Integer.parseInt(count)+1;
				  String sessionname="Session1";
				  //String sessionName = getSessionName(newentTlTragcalmst.getsessionMaster());
				  newentTlTragcalmst.getsessionMaster().setEtcsName(sessionname);	  
			      newentTlTragcalmst.getsessionMaster().setEtcsKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgCalSessionSql.TBL_ENT_TL_TRGCALSESSION,15,"ETS","","" ));	 
			      newentTlTragcalmst.getsessionMaster().setEtcsEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());	 
			      newentTlTragcalmst.getsessionMaster().setEtcsEtcmFlid(newentTlTragcalmst.getEtcmFlid());
			      sqls.add(EntTlTrgCalSessionSql.getInsertSql(entTlTrgCalSessionSql.getFtymDbFields(),newentTlTragcalmst.getsessionMaster().getSaveArray()));
			 }
			       }
				if(newentTlTragcalmst.getFaculty()!= null){
					 CommonMessage.debugMsg("Inside the Faculty");
					 newentTlTragcalmst.getFaculty().setEtcfKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgFacultySql.TBL_ENT_TL_TRGFACULTY,15,"ETF","",""));
					 newentTlTragcalmst.getFaculty().setEtcfEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());
					 sqls.add(EntTlTrgFacultySql.getInsertSql(entTlTrgFacultySql.getFtymDbFields(),newentTlTragcalmst.getFaculty().getSaveArray()));
				}

			   /*  if(newentTlTragcalmst.getRoleLink() != null){
		   		  if(!UIUtils.isValidKeyId(newentTlTragcalmst.getRoleLink().getEtcuKeyid())){
		   			         newentTlTragcalmst.getRoleLink().setEtcuKeyid(dbActionTemplate.getSequenceNumber(entTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP,15,"ETU","",""));
		   			         newentTlTragcalmst.getRoleLink().setEtcuKeyid(newentTlTragcalmst.getEtcmKeyid());
		   			         sqls.add(entTlTrgCalUnqpSql.getInsertSql(entTlTrgCalUnqpSql.getFtymDbFields(),newentTlTragcalmst.getRoleLink().getSaveArray()));
		   				}
		   	      }*/
				/**********Added Multiple unique position**************/
		         if(newentTlTragcalmst.getRoleLink() != null){
				//String allUnique = newentTlTrgCalUnqp.getAllUniquePosition();
	           	String allUnique = newentTlTragcalmst.getAllUniquePosition();
	           	CommonMessage.debugMsg("The All Unique In DAOIMPL"+allUnique);
				 if (UIUtils.isValidKeyId(allUnique) && allUnique.equals("Y")) {		 
					 CommonMessage.debugMsg("Inside if allUnique"+allUnique);
					 StringBuffer sf = new StringBuffer();
					 String flidlist=null;
					 String RoleJh= newentTlTragcalmst.getRoleLink().getEtcuRoleJh();
					 CommonMessage.debugMsg("The RoleJH"+RoleJh);
					 String RoleDmt=newentTlTragcalmst.getRoleLink().getEtcuRoleDmt();
					 CommonMessage.debugMsg("The RoleDMT"+RoleDmt);
					 if(RoleJh==null){
						 CommonMessage.debugMsg("Inside RoleJH");
						 flidlist="SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='"+RoleJh+"'";
						 CommonMessage.debugMsg("Inside the flidlist::JH:"+flidlist);
					 }
					 else{
						 CommonMessage.debugMsg("Inside RoleDMT");
						 flidlist="SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='"+RoleDmt+"'";
						 CommonMessage.debugMsg("Inside the flidlist::DMT:"+flidlist);
					 }
					 String flidData=dbActionTemplate.getSingleValue(flidlist);
					 sf.append(" SELECT DISTINCT ROLE_KEYID from  Ent_Vw_Rolemst ");
				     sf.append(" where INSTR( PARENTFLIDS||FLID ,'" + flidData + "')>0 ");
					 CommonMessage.debugMsg("The Sf Data Query:::"+sf.toString());
					 List<String[]> roleIds = dbActionTemplate.getDataList(sf.toString());
					 CommonMessage.debugMsg(""+"roleIds.size()==="+roleIds.size());
					 for ( int i=0;i<roleIds.size();i++) {
						 newentTlTragcalmst.getRoleLink().setEtcuRoleKeyid(roleIds.get(i)[0]);
						 CommonMessage.debugMsg("roleIds.get(i)[0]==="+roleIds.get(i)[0]);
	  			         newentTlTragcalmst.getRoleLink().setEtcuKeyid(dbActionTemplate.getSequenceNumber(entTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP,15,"ETU","",""));
	  			         newentTlTragcalmst.getRoleLink().setEtcuEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());
	  			         sqls.add(entTlTrgCalUnqpSql.getInsertSql(entTlTrgCalUnqpSql.getFtymDbFields(),newentTlTragcalmst.getRoleLink().getSaveArray()));
					 } 
				 } else {
				         newentTlTragcalmst.getRoleLink().setEtcuEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());    
					   if(!UIUtils.isValidKeyId(newentTlTragcalmst.getRoleLink().getEtcuKeyid())){
		   			         newentTlTragcalmst.getRoleLink().setEtcuKeyid(dbActionTemplate.getSequenceNumber(entTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP,15,"ETU","",""));
	  			             sqls.add(entTlTrgCalUnqpSql.getInsertSql(entTlTrgCalUnqpSql.getFtymDbFields(),newentTlTragcalmst.getRoleLink().getSaveArray()));
					 }else{
	  					 sqls.add(entTlTrgCalUnqpSql.getUpdateSql(entTlTrgCalUnqpSql.getFtymDbFields(),newentTlTragcalmst.getRoleLink().getSaveArray()));	
					 }
				}
		 }
			       
	    dbActionTemplate.executeStatements(sqls); 
		return newentTlTragcalmst;
	}
// --------- vignesh 09dec2025 ------------------------------------------------------//
public EntTlTragcalmst update(EntTlTragcalmst newentTlTragcalmst) throws Exception {
    List<String> sqls = new ArrayList<String>();		
    EntTlTragcalmstSql entTlTragcalmstSql = new EntTlTragcalmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
    EntTlTrgCalUnqpSql entTlTrgCalUnqpSql = new EntTlTrgCalUnqpSql();
    EntTlTrgCalSessionSql entTlTrgCalSessionSql = new EntTlTrgCalSessionSql(); 

    // **TraingCalUpdate****//
    StringBuilder sqlapp = new StringBuilder();
    sqlapp.append("SELECT ETCM_KEYID FROM  ENT_TL_TRGCALMST WHERE ETCM_KEYID ='")
          .append(newentTlTragcalmst.getEtcmKeyid())
          .append("' ");
    // sqlapp.append(" AND TO_CHAR(ETCM_CALDATE,'D-Mon-YYYY')= '"+newentTlTragcalmst.getEtcmCalendarDate()+"' ");
    // sqlapp.append(" AND ETCM_FLID='"+newentTlTragcalmst.getEtcmFlid()+"' ");
    CommonMessage.debugMsg("sqlapp:::" + sqlapp.toString());
    String isExists = dbActionTemplate.getSingleValue(sqlapp.toString());
    CommonMessage.debugMsg("isExists:::" + isExists);	   	 

    if (!UIUtils.isValidKeyId(isExists)) { 
        newentTlTragcalmst.setEtcmKeyid(
            dbActionTemplate.getSequenceNumber(
                EntTlTragcalmstSql.TBL_ENT_TL_TRGCALMST, 15, "ETC", "", ""
            )
        );
        sqls.add(
            EntTlTragcalmstSql.getInsertSql(
                entTlTragcalmstSql.getFtymDbFields(),
                newentTlTragcalmst.getSaveArray()
            )
        ); // add insert sql for master table
    } else {
        newentTlTragcalmst.setEtcmKeyid(isExists);
        sqls.add(
            EntTlTragcalmstSql.getUpdateSql(
                entTlTragcalmstSql.getFtymDbFields(),
                newentTlTragcalmst.getSaveArray()
            )
        ); // add update sql for master table 
    }    

    // =========================
    // SESSION MASTER (NEW / EDIT)
    // =========================
    EntTlTrgCalSession session = newentTlTragcalmst.getsessionMaster();
    if (session != null) {
        boolean hasSessionKey = UIUtils.isValidKeyId(session.getEtcsKeyid());
        String sessionName    = session.getEtcsName();

        // 1) UPDATE EXISTING SESSION
        //    (edit from/to time, date, etc. for already existing ETCS_KEYID)
        if (hasSessionKey && "Session".equals(sessionName)) {
            String dbSessionName = dbActionTemplate.getSingleValue(
                "ENT_TL_TRGCALSESSION", "ETCS_NAME", "ETCS_KEYID",
                session.getEtcsKeyid()
            );

            // Keep original Session1/Session2 name from DB
            session.setEtcsName(dbSessionName);
            // Preserve createdOn as per your old logic
            session.setEtcsCreatedon(session.getEtcsModifiedon());

            sqls.add(
                EntTlTrgCalSessionSql.getUpdateSql(
                    entTlTrgCalSessionSql.getFtymDbFields(),
                    session.getSaveArray()
                )
            );
            CommonMessage.debugMsg("DAO update – sessionKey=" + session.getEtcsKeyid()
            + ", sessionName=" + sessionName
            + ", hasSessionKey=" + hasSessionKey);

        }

        // 2) INSERT NEW SESSION (NO KEY YET)
        if (!hasSessionKey && "Session".equals(sessionName)) {
            // Ignore "Faculty" here; this block only runs for "Session"
            String count = dbActionTemplate.getSingleValue(
                "ENT_TL_TRGCALSESSION", "COUNT(*)", "ETCS_ETCM_KEYID",
                session.getEtcsEtcmKeyid()
            );
            int cnt = Integer.parseInt(count) + 1;
            String newSessionName = "Session" + cnt;

            session.setEtcsName(newSessionName);
            session.setEtcsEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());

            // Generate new ETCS key for this new session
            session.setEtcsKeyid(
                dbActionTemplate.getSequenceNumber(
                    EntTlTrgCalSessionSql.TBL_ENT_TL_TRGCALSESSION,
                    15, "ETCS", "", ""
                )
            );

            sqls.add(
                EntTlTrgCalSessionSql.getInsertSql(
                    entTlTrgCalSessionSql.getFtymDbFields(),
                    session.getSaveArray()
                )
            );
        }
    }
 

    // =========================
    // FACULTY
    // =========================
    if (newentTlTragcalmst.getFaculty() != null) {
        newentTlTragcalmst.getFaculty().setEtcfEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());
        if (!UIUtils.isValidKeyId(newentTlTragcalmst.getFaculty().getEtcfKeyid())) {
            newentTlTragcalmst.getFaculty().setEtcfKeyid(
                dbActionTemplate.getSequenceNumber(
                    EntTlTrgFacultySql.TBL_ENT_TL_TRGFACULTY, 15, "ETF", "", ""
                )
            );
            newentTlTragcalmst.getFaculty().setEtcfEtcmFlid(newentTlTragcalmst.getEtcmFlid());
            sqls.add(
                EntTlTrgFacultySql.getInsertSql(
                    entTlTrgFacultySql.getFtymDbFields(),
                    newentTlTragcalmst.getFaculty().getSaveArray()
                )
            );
        } else {
            sqls.add(
                EntTlTrgFacultySql.getUpdateSql(
                    entTlTrgFacultySql.getFtymDbFields(),
                    newentTlTragcalmst.getFaculty().getSaveArray()
                )
            );
        }
    }

    /**********Added Multiple unique position**************/
    if (newentTlTragcalmst.getRoleLink() != null) {
        //String allUnique = newentTlTrgCalUnqp.getAllUniquePosition();
        String allUnique = newentTlTragcalmst.getAllUniquePosition();
        CommonMessage.debugMsg("The All Unique In DAOIMPL" + allUnique);
        if (UIUtils.isValidKeyId(allUnique) && allUnique.equals("Y")) {		 
            CommonMessage.debugMsg("Inside if allUnique" + allUnique);
            StringBuffer sf = new StringBuffer();
            String flidlist = null;
            String RoleJh   = newentTlTragcalmst.getRoleLink().getEtcuRoleJh();
            CommonMessage.debugMsg("The RoleJH" + RoleJh);
            String RoleDmt  = newentTlTragcalmst.getRoleLink().getEtcuRoleDmt();
            CommonMessage.debugMsg("The RoleDMT" + RoleDmt);
            // if(RoleJh!=null){
            if (RoleJh == null) {
                CommonMessage.debugMsg("Inside RoleJH");
                flidlist = "SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='" + RoleJh + "'";
                CommonMessage.debugMsg("Inside the flidlist::JH:" + flidlist);
            } else {
                CommonMessage.debugMsg("Inside RoleDMT");
                flidlist = "SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='" + RoleDmt + "'";
                CommonMessage.debugMsg("Inside the flidlist::DMT:" + flidlist);
            }
            String flidData = dbActionTemplate.getSingleValue(flidlist);
            sf.append(" SELECT DISTINCT ROLE_KEYID from  Ent_Vw_Rolemst ");
            sf.append(" where INSTR( PARENTFLIDS||FLID ,'" + flidData + "')>0 ");
            CommonMessage.debugMsg("The Sf Data Query:::" + sf.toString());
            List<String[]> roleIds = dbActionTemplate.getDataList(sf.toString());
            CommonMessage.debugMsg("" + "roleIds.size()===" + roleIds.size());
            for (int i = 0; i < roleIds.size(); i++) {
                newentTlTragcalmst.getRoleLink().setEtcuRoleKeyid(roleIds.get(i)[0]);
                CommonMessage.debugMsg("roleIds.get(i)[0]===" + roleIds.get(i)[0]);
                newentTlTragcalmst.getRoleLink().setEtcuKeyid(
                    dbActionTemplate.getSequenceNumber(
                        entTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP, 15, "ETU", "", ""
                    )
                );
                newentTlTragcalmst.getRoleLink().setEtcuEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());
                sqls.add(
                    entTlTrgCalUnqpSql.getInsertSql(
                        entTlTrgCalUnqpSql.getFtymDbFields(),
                        newentTlTragcalmst.getRoleLink().getSaveArray()
                    )
                );
            } 
        } else {
            newentTlTragcalmst.getRoleLink().setEtcuEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());    
            if (!UIUtils.isValidKeyId(newentTlTragcalmst.getRoleLink().getEtcuKeyid())) {
                newentTlTragcalmst.getRoleLink().setEtcuKeyid(
                    dbActionTemplate.getSequenceNumber(
                        entTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP, 15, "ETU", "", ""
                    )
                );
                sqls.add(
                    entTlTrgCalUnqpSql.getInsertSql(
                        entTlTrgCalUnqpSql.getFtymDbFields(),
                        newentTlTragcalmst.getRoleLink().getSaveArray()
                    )
                );
            } else {
                sqls.add(
                    entTlTrgCalUnqpSql.getUpdateSql(
                        entTlTrgCalUnqpSql.getFtymDbFields(),
                        newentTlTragcalmst.getRoleLink().getSaveArray()
                    )
                );	
            }
        }
    }    

    dbActionTemplate.executeStatements(sqls); // execute the block of sqls
    return newentTlTragcalmst;
}


//public EntTlTragcalmst update(EntTlTragcalmst newentTlTragcalmst) 	throws Exception {
//	List<String> sqls = new ArrayList<String>();		
//	EntTlTragcalmstSql entTlTragcalmstSql = new EntTlTragcalmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
//	EntTlTrgCalUnqpSql entTlTrgCalUnqpSql=new EntTlTrgCalUnqpSql();
//	EntTlTrgCalSessionSql entTlTrgCalSessionSql=new EntTlTrgCalSessionSql(); 
//
//	           //**TraingCalUpdate****//
//	           StringBuilder sqlapp=new StringBuilder();
//	           
//	           sqlapp.append("SELECT ETCM_KEYID FROM  ENT_TL_TRGCALMST WHERE ETCM_KEYID ='"+newentTlTragcalmst.getEtcmKeyid()+"' ");
//	          // sqlapp.append(" AND TO_CHAR(ETCM_CALDATE,'D-Mon-YYYY')= '"+newentTlTragcalmst.getEtcmCalendarDate()+"' ");
//	        //   sqlapp.append(" AND ETCM_FLID='"+newentTlTragcalmst.getEtcmFlid()+"' ");
//	           CommonMessage.debugMsg("sqlapp:::"+sqlapp.toString());
//	   		   String isExists = dbActionTemplate.getSingleValue(sqlapp.toString());
//	   	       CommonMessage.debugMsg("isExists:::"+isExists);	   	 
//
//	   	    if (!UIUtils.isValidKeyId(isExists)) { 
//	   	    	newentTlTragcalmst.setEtcmKeyid(dbActionTemplate.getSequenceNumber(EntTlTragcalmstSql.TBL_ENT_TL_TRGCALMST,15,"ETC","",""));
//	   	    	sqls.add(EntTlTragcalmstSql.getInsertSql(entTlTragcalmstSql.getFtymDbFields(),newentTlTragcalmst.getSaveArray())); // add insert sql for master table
//			}
//			else {
//				newentTlTragcalmst.setEtcmKeyid(isExists);
//				sqls.add(EntTlTragcalmstSql.getUpdateSql(entTlTragcalmstSql.getFtymDbFields(),newentTlTragcalmst.getSaveArray())); // add insert sql for master table 
//			}    
//		
//	   	 if(newentTlTragcalmst.getsessionMaster()!=null) 
//         {
//              if((newentTlTragcalmst.getsessionMaster().getEtcsKeyid())!=null)
//              {
//         if((newentTlTragcalmst.getsessionMaster().getEtcsKeyid()).length()!=0)
//       {
//      
//             if((newentTlTragcalmst.getsessionMaster().getEtcsName()).equals("Session"))
//          {
//              String session=dbActionTemplate.getSingleValue("ENT_TL_TRGCALSESSION", "ETCS_NAME", "ETCS_KEYID",newentTlTragcalmst.getsessionMaster().getEtcsKeyid());
//                 
//           newentTlTragcalmst.getsessionMaster().setEtcsKeyid((newentTlTragcalmst.getsessionMaster().getEtcsKeyid()));
//           newentTlTragcalmst.getsessionMaster().setEtcsName(session);
//           newentTlTragcalmst.getsessionMaster().setEtcsCreatedon(newentTlTragcalmst.getsessionMaster().getEtcsModifiedon());
//           sqls.add(EntTlTrgCalSessionSql.getUpdateSql(entTlTrgCalSessionSql.getFtymDbFields(),newentTlTragcalmst.getsessionMaster().getSaveArray()));               
//          }     
//       }
//              }
//         }
//	   	    
//	   	 if(newentTlTragcalmst.getsessionMaster() !=null && 
//    			 !UIUtils.isValidKeyId(newentTlTragcalmst.getsessionMaster().getEtcsKeyid())){
//    	 { 
//               if(!(newentTlTragcalmst.getsessionMaster().getEtcsName()).equals("Faculty"))
//            		   {
//    		 
//            	   if((newentTlTragcalmst.getsessionMaster().getEtcsName()).equals("Session"))
//        		   {
//            	   
//            	   String count=dbActionTemplate.getSingleValue("ENT_TL_TRGCALSESSION", "COUNT(*)", "ETCS_ETCM_KEYID", newentTlTragcalmst.getsessionMaster().getEtcsEtcmKeyid());
//				  int cnt=Integer.parseInt(count)+1;
//				  String sessionname="Session"+cnt;
//			  newentTlTragcalmst.getsessionMaster().setEtcsName(sessionname);
//			  newentTlTragcalmst.getsessionMaster().setEtcsEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());
//			  CommonMessage.debugMsg("newentTlTragcalmst.getsessionMaster().getEtcsKeyid()"+newentTlTragcalmst.getsessionMaster().getEtcsKeyid());
//			  if((newentTlTragcalmst.getsessionMaster().getEtcsKeyid()).length()==0)
//			  {
//		      newentTlTragcalmst.getsessionMaster().setEtcsKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgCalSessionSql.TBL_ENT_TL_TRGCALSESSION,15,"ETCS","","" ));	 
//		      sqls.add(EntTlTrgCalSessionSql.getInsertSql(entTlTrgCalSessionSql.getFtymDbFields(),newentTlTragcalmst.getsessionMaster().getSaveArray()));				
//			  }
//			  if((newentTlTragcalmst.getsessionMaster().getEtcsKeyid()).length()!=0)
//			  {
//				  newentTlTragcalmst.getsessionMaster().setEtcsKeyid((newentTlTragcalmst.getsessionMaster().getEtcsKeyid()));
//				  sqls.add(EntTlTrgCalSessionSql.getUpdateSql(entTlTrgCalSessionSql.getFtymDbFields(),newentTlTragcalmst.getsessionMaster().getSaveArray()));				
//					 
//			  }      		   
//        		   }
//            	}
//    	 
//    	 }
//	   	 }
//	       if(newentTlTragcalmst.getFaculty()!= null){
//			  newentTlTragcalmst.getFaculty().setEtcfEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());
//			if( !UIUtils.isValidKeyId(newentTlTragcalmst.getFaculty().getEtcfKeyid())){
//			     newentTlTragcalmst.getFaculty().setEtcfKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgFacultySql.TBL_ENT_TL_TRGFACULTY,15,"ETF","",""));
//			     newentTlTragcalmst.getFaculty().setEtcfEtcmFlid(newentTlTragcalmst.getEtcmFlid());
//			     sqls.add(EntTlTrgFacultySql.getInsertSql(entTlTrgFacultySql.getFtymDbFields(),newentTlTragcalmst.getFaculty().getSaveArray()));
//			}
//			else{
//			    sqls.add(EntTlTrgFacultySql.getUpdateSql(entTlTrgFacultySql.getFtymDbFields(),newentTlTragcalmst.getFaculty().getSaveArray()));
//			}
//			}
//	      
//	       /**********Added Multiple unique position**************/
//	         if(newentTlTragcalmst.getRoleLink() != null){
//			//String allUnique = newentTlTrgCalUnqp.getAllUniquePosition();
//           	String allUnique = newentTlTragcalmst.getAllUniquePosition();
//           	CommonMessage.debugMsg("The All Unique In DAOIMPL"+allUnique);
//			 if (UIUtils.isValidKeyId(allUnique) && allUnique.equals("Y")) {		 
//				 CommonMessage.debugMsg("Inside if allUnique"+allUnique);
//				 StringBuffer sf = new StringBuffer();
//				 String flidlist=null;
//				 String RoleJh= newentTlTragcalmst.getRoleLink().getEtcuRoleJh();
//				 CommonMessage.debugMsg("The RoleJH"+RoleJh);
//				 String RoleDmt=newentTlTragcalmst.getRoleLink().getEtcuRoleDmt();
//				 CommonMessage.debugMsg("The RoleDMT"+RoleDmt);
//				// if(RoleJh!=null){
//				 if(RoleJh==null){
//					 CommonMessage.debugMsg("Inside RoleJH");
//					 flidlist="SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='"+RoleJh+"'";
//					 CommonMessage.debugMsg("Inside the flidlist::JH:"+flidlist);
//				 }
//				 else{
//					 CommonMessage.debugMsg("Inside RoleDMT");
//					 flidlist="SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='"+RoleDmt+"'";
//					 CommonMessage.debugMsg("Inside the flidlist::DMT:"+flidlist);
//				 }
//				 String flidData=dbActionTemplate.getSingleValue(flidlist);
//				 sf.append(" SELECT DISTINCT ROLE_KEYID from  Ent_Vw_Rolemst ");
//			     sf.append(" where INSTR( PARENTFLIDS||FLID ,'" + flidData + "')>0 ");
//				 CommonMessage.debugMsg("The Sf Data Query:::"+sf.toString());
//				 List<String[]> roleIds = dbActionTemplate.getDataList(sf.toString());
//				 CommonMessage.debugMsg(""+"roleIds.size()==="+roleIds.size());
//				 for ( int i=0;i<roleIds.size();i++) {
//					 newentTlTragcalmst.getRoleLink().setEtcuRoleKeyid(roleIds.get(i)[0]);
//					 CommonMessage.debugMsg("roleIds.get(i)[0]==="+roleIds.get(i)[0]);
//  			         newentTlTragcalmst.getRoleLink().setEtcuKeyid(dbActionTemplate.getSequenceNumber(entTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP,15,"ETU","",""));
//  			         newentTlTragcalmst.getRoleLink().setEtcuEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());
//  			         sqls.add(entTlTrgCalUnqpSql.getInsertSql(entTlTrgCalUnqpSql.getFtymDbFields(),newentTlTragcalmst.getRoleLink().getSaveArray()));
//				 } 
//			 } else {
//			         newentTlTragcalmst.getRoleLink().setEtcuEtcmKeyid(newentTlTragcalmst.getEtcmKeyid());    
//				   if(!UIUtils.isValidKeyId(newentTlTragcalmst.getRoleLink().getEtcuKeyid())){
//	   			         newentTlTragcalmst.getRoleLink().setEtcuKeyid(dbActionTemplate.getSequenceNumber(entTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP,15,"ETU","",""));
//  			             sqls.add(entTlTrgCalUnqpSql.getInsertSql(entTlTrgCalUnqpSql.getFtymDbFields(),newentTlTragcalmst.getRoleLink().getSaveArray()));
//				 }else{
//  					 sqls.add(entTlTrgCalUnqpSql.getUpdateSql(entTlTrgCalUnqpSql.getFtymDbFields(),newentTlTragcalmst.getRoleLink().getSaveArray()));	
//				 }
//			}
//	 }    
//		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
//	    return newentTlTragcalmst;
//}
public EntTlTragcalmst deleteTrainingCal(EntTlTragcalmst newEntTlTragcalmst) throws Exception{
    try
    {
    	List<String> sql=new ArrayList<String>();
    	sql.add("DELETE FROM ENT_TL_TRGCALMST WHERE ETCM_KEYID='"+newEntTlTragcalmst.getEtcmKeyid()+"'");
        sql.add("DELETE FROM ENT_TL_TRGCALSESSION WHERE ETCS_ETCM_KEYID='"+newEntTlTragcalmst.getEtcmKeyid()+"'");
        sql.add("DELETE FROM ENT_TL_TRGCALUNQP WHERE ETCU_ETCM_KEYID='"+newEntTlTragcalmst.getEtcmKeyid()+"'");
        sql.add("DELETE FROM ENT_TL_TRGCALEMP WHERE ETCE_ETCM_KEYID='"+newEntTlTragcalmst.getEtcmKeyid()+"'");
        sql.add("DELETE FROM ENT_TL_TRGCALEMPATSCORE WHERE ETCA_ETCM_KEYID='"+newEntTlTragcalmst.getEtcmKeyid()+"'");
        sql.add("DELETE FROM ENT_TL_TRGCALQUAD WHERE ETCQ_L1_TRGCALID='"+newEntTlTragcalmst.getEtcmKeyid()+"'");
        sql.add("DELETE FROM ENT_TL_TRGFACULTY WHERE ETCF_ETCM_KEYID='"+newEntTlTragcalmst.getEtcmKeyid()+"'");	
    	//CommonMessage.debugMsg("Delete sql"+sql.toString());
    	dbActionTemplate.executeStatements(sql);
    }
catch(Exception e){
	e.printStackTrace();
	throw new Exception(e.getMessage());
}
  return newEntTlTragcalmst;	
}

public String getSessionName(EntTlTrgCalSession entTlsessionmaster) throws Exception {
	// TODO Auto-generated method stub
	 String sessionMonth=entTlsessionmaster.getEtcsTillDate();
	if (sessionMonth.length()>0) { 
		sessionMonth = sessionMonth.substring(sessionMonth.indexOf("-"),sessionMonth.length());
		sessionMonth = sessionMonth.substring(1);
	}
	 // VIGNESH CHECKING IF MON-YYYY WILL WORK OR NEED TIMESTAMP 
	 String countSql= "select Count(*) from ENT_TL_TRGCALSESSION where 1=1 " +
	 		"AND  to_char(ETCS_TILLDATE,'MON-YYYY')  = trim(upper('"+sessionMonth+"')) AND  ETCS_ETCM_KEYID = trim('"+entTlsessionmaster.getEtcsEtcmKeyid()+"')";
	 CommonMessage.debugMsg("countSql  "+countSql );
	 String Count = dbActionTemplate.getSingleValue(countSql);
	 CommonMessage.debugMsg("The Session Count"+Count);
	 int newCnt = Integer.parseInt(Count);
	 newCnt = newCnt+1;
	 String sessionName = "Session"+Integer.toString(newCnt);
	 CommonMessage.debugMsg("The bachName::::"+sessionName);
	return sessionName;
}


public List<EntTlTrgCalEmp> createSessionEmployee(List<EntTlTrgCalEmp> entTlSessionEmployeeLink) throws Exception {

	EntTlTrgCalEmpSql entTlTrgCalEmpSql = new EntTlTrgCalEmpSql(); // contains dbtable,field names, Field types and related sqls  of master table
	try
	{
		     List<String> sqls = new ArrayList<String>();
		     List <EntTlTrgCalEmp> methodslist = entTlSessionEmployeeLink;
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),EntTlTrgCalEmpSql.TBL_ENT_TL_TRGCALEMP,15,"ETE", null,null);
			 CommonMessage.debugMsg("sequenceNumber in IF"+sequenceNumber);
			 for(EntTlTrgCalEmp empSessionLink:methodslist)
			 {
				    CommonMessage.debugMsg("The Role Keyid"+empSessionLink.getEtceRoleKeyid());
					String seqNo=sequenceNumber.getSequnceNumber();
					CommonMessage.debugMsg("The seqNo::::"+seqNo);
					empSessionLink.setEtceKeyid(seqNo);
					empSessionLink.setEtceRoleKeyid(empSessionLink.getEtceRoleKeyid());
					empSessionLink.setEtceEtcsKeyid(empSessionLink.getEtceEtcsKeyid());  
					empSessionLink.setEtceRoleDmt(empSessionLink.getEtceRoleDmt());
					CommonMessage.debugMsg("The RoleDMT"+empSessionLink.getEtceRoleDmt());
					empSessionLink.setEtceRoleJh(empSessionLink.getEtceRoleJh());
					CommonMessage.debugMsg("The RoleJH"+empSessionLink.getEtceRoleJh());
					sqls.add(EntTlTrgCalEmpSql.getInsertSql(entTlTrgCalEmpSql.getFtymDbFields(), empSessionLink.getSaveArray()));
			        CommonMessage.debugMsg("The sqls Query:"+sqls);
			 }
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		CommonMessage.debugMsg("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return null;
}

public List<EntTlTrgCalEmp> DeleteSessionEmployee(List<EntTlTrgCalEmp> entTlSessionEmployeeLink) throws Exception{
	 
	EntTlTrgCalEmpSql entTlTrgCalEmpSql = new EntTlTrgCalEmpSql(); // contains dbtable,field names, Field types and related sqls  of master table
	try
	{
		     List<String> sqls = new ArrayList<String>();
		     List <EntTlTrgCalEmp> methodslist = entTlSessionEmployeeLink;
			 for(EntTlTrgCalEmp empSessionLink:methodslist)
			 {
				  sqls.add(EntTlTrgCalEmpSql.getDeleteSql(entTlTrgCalEmpSql.getFtymDbFields(),empSessionLink.getSaveArray()));
			}
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		CommonMessage.debugMsg("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return null;
}


public List<EntTlTrgCalEmp>  createSessionEmployeeUpdate(List<EntTlTrgCalEmp> empMailEnableList)throws Exception{
	
	 List<Object[]> valueList = new ArrayList<Object[]>();	 
	 String sql = "UPDATE ENT_TL_TRGCALEMP SET ETCE_KEYID =? WHERE ETCE_KEYID =?";
	 CommonMessage.debugMsg("Update the sql"+sql);
	 for(EntTlTrgCalEmp EntEmpModel : empMailEnableList)
	 {  
 Object[] value={EntEmpModel.getEtceKeyid()};    
 valueList.add(value);
   }
 int [] dataTypes = { Types.VARCHAR};
 dbActionTemplate.executeBatch(sql,valueList,dataTypes);
 return empMailEnableList;
}

public List<String[]> getflid(String originalid) throws Exception {
	//String sqlval = "select fnln_keyid from gen_tl_functionallocn where fnln_originalid = '"+originalid+"'";
	    //String flidval = dbActionTemplate.getSingleValue(sqlval);
	String sqlval = "SELECT locn_keyid,sect_keyid,fnln_keyid,cell_keyid FROM GEN_VW_FNLN WHERE FNLN_ORIGINALID= '"+originalid+"'";
	CommonMessage.debugMsg("The SqlVal"+sqlval);
	List<String[]> keyids=dbActionTemplate.getDataList(sqlval);
	return keyids;
}
public List<String[]> getTrainingCalendarList(GridParams gridparams,CommonFilter commonFilter) throws Exception {
	//String functionName = "ENT_NEW_REPORTS.ENT_TL_NEWTRAININGCALMSTGRD";//commonFilter.getFunctionName();
	try
	{    
	CommonMessage.debugMsg("getehsAuditParamterGrid sql..");
	

	List <String>  paramvalues = new ArrayList<String>();
	String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
	String mode=commonFilter.getType();
    CommonMessage.debugMsg("value of mode"+commonFilter.getType());

	
    if(UIUtils.isValidKeyId(mode)){
		condParms +="Mode="+mode+";";
	}
    CommonMessage.debugMsg("condParms"+condParms);
    CommonMessage.debugMsg("commonParams"+commonParams);
	paramvalues.add(condParms);
	paramvalues.add(commonParams);
	

//	List<String[]> getDataList= dbActionTemplate.processFunctionCalls("ENT_TL_NEWTRAININGCALMSTGRD", paramvalues);
	List<String[]> getDataList= fnCallApi.callFunction("ENT_TL_NEWTRAININGCALMSTGRD_SB", paramvalues,3,true);
	 if( commonFilter.getViewClick() == 'Y'){
	     String totalCnt = paramvalues.get(0);
	     //CommonMessage.debugMsg("totalCnt...."+totalCnt);
	     boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	    CommonMessage.debugMsg("IS isInteger"+isInteger);
	     if(isInteger ){ // Vignesh
	    	 CommonMessage.debugMsg("ENTERING IF BLOCK IN IS INTEGER printing total count " + totalCnt);
	     	commonFilter.setTotalRecordCnt((int) Long.parseLong(totalCnt));
	     	 CommonMessage.debugMsg(" PRINTING THE COMMON FILTER FROM IF BLOCK "   );
	      }
	 }
	 CommonMessage.debugMsg(" PRINTING THE datalist after IF BLOCK " + getDataList  );
	 
	return getDataList;

	}
	catch(Exception e)
	{
	//e.printStackTrace();
	throw new Exception(e.getMessage());
	}
}

// -------------- vIGNESH 05DEC2025 FOR FILTER --------------------------------//


//public List<String[]> getEmpWiseTrainingReport(CommonFilter commonFilter) throws Exception {
//	//String functionName = "ENT_NEW_REPORTS.ENT_TL_NEWTRAININGCALMSTGRD";//commonFilter.getFunctionName();
//	try
//	{    
//	
//	List <String>  paramvalues = new ArrayList<String>();
//	String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
//	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
//	String mode=commonFilter.getType();
//    CommonMessage.debugMsg("value of mode"+commonFilter.getType());
//
//	
//    if(UIUtils.isValidKeyId(mode)){
//		condParms +="Mode="+mode+";";
//	}
//    CommonMessage.debugMsg("condParms"+condParms);
//    CommonMessage.debugMsg("commonParams"+commonParams);
//	paramvalues.add(condParms);
//	paramvalues.add(commonParams);
//	
//    CommonMessage.debugMsg("dAOiMPL");
//	List<String[]> getDataList= dbActionTemplate.processFunctionCalls("ENT_TL_EMPLOYEEWISEGRD", paramvalues);
//	 if( commonFilter.getViewClick() == 'Y'){
//	     String totalCnt = paramvalues.get(0);
//	     //CommonMessage.debugMsg("totalCnt...."+totalCnt);
//	     boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
//	    CommonMessage.debugMsg("IS isInteger"+isInteger);
//	     if(  isInteger ){
//	     	
//	     	commonFilter.setTotalRecordCnt((int) Long.parseLong(totalCnt));
//	      }
//	 }
//	return getDataList;
//
//	}
//	catch(Exception e)
//	{
//	//e.printStackTrace();
//	throw new Exception(e.getMessage());
//	}
//}

public List<String[]> getEmpWiseTrainingReport(CommonFilter commonFilter) throws Exception {
    try {    

        List<String>  paramvalues = new ArrayList<String>();
        String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
        String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
        String mode = commonFilter.getType();
        CommonMessage.debugMsg("value of mode " + commonFilter.getType());

        if (UIUtils.isValidKeyId(mode)) {
            condParms += "Mode=" + mode + ";";
        }
        CommonMessage.debugMsg("condParms " + condParms);
        CommonMessage.debugMsg("commonParams " + commonParams);

        paramvalues.add(condParms);
        paramvalues.add(commonParams);

        CommonMessage.debugMsg("dAOiMPL");
        
//               List<String[]> getDataList = dbActionTemplate.processFunctionCalls(
//                "ENT_TL_EMPLOYEEWISEGRD", paramvalues);

        List<String[]> getDataList = fnCallApi.callFunction(
                "ENT_TL_EMPLOYEEWISEGRD_SB", paramvalues,3,true);
        
        // 🔴 OLD: only when viewClick == 'Y'
        // if (commonFilter.getViewClick() == 'Y') {
        //     String totalCnt = paramvalues.get(0);
        //     boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
        //     CommonMessage.debugMsg("IS isInteger" + isInteger);
        //     if (isInteger) {
        //         commonFilter.setTotalRecordCnt((int) Long.parseLong(totalCnt));
        //     }
        // }

        // 🟢 NEW: always update totalRecordCnt from the OUT parameter
        if (!paramvalues.isEmpty()) {
            String totalCnt = paramvalues.get(0);
            CommonMessage.debugMsg("totalCnt...." + totalCnt);

            boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
            CommonMessage.debugMsg("IS isInteger " + isInteger);

            if (isInteger && totalCnt.length() > 0) {
                commonFilter.setTotalRecordCnt((int) Long.parseLong(totalCnt));
            }
        }

        return getDataList;

    } catch(Exception e) {
        throw new Exception(e.getMessage());
    }
}

//-------------- vIGNESH 05DEC2025 FOR FILTER --------------------------------//
@Override
public EntTlTragcalmst getselectdata(String calid) throws Exception {
	EntTlTragcalmst entTlTragcalmst=new EntTlTragcalmst();
	String sql = EntTlTragcalmstSql.selectDataMst();
	
	Object args[] = new Object[] {calid};
	
	entTlTragcalmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	
	CommonMessage.debugMsg("returning select dao  impl  ========== " + entTlTragcalmst);
	
	return entTlTragcalmst;
}
//------------------------Vignesh 28Nov2025 -------------------------------------------------------------//

@Override
public String IsTrainingCompleted(CommonFilter commonFilter) throws Exception {
    
	 CommonMessage.debugMsg(" direct key from commonfilter  " + commonFilter.getKey());
    String key = commonFilter.getKey();
    
    if (key == null || key.trim().isEmpty()) {
        return "N";
    }
    CommonMessage.debugMsg(" key " + key);
    
    // 1) Count employees linked to this training
    String empCntStr = dbActionTemplate.getSingleValue(
        "SELECT COUNT(*) " +
        "FROM ENT_TL_TRGCALEMP " +
        "WHERE ETCE_ETCM_KEYID = '" + key + "'"
    );
    CommonMessage.debugMsg(" empcount " + empCntStr);
    
    if (empCntStr == null || empCntStr.trim().isEmpty()) {
        empCntStr = "0";
    }

    // If no employees linked, training cannot be "completed"
    if ("0".equals(empCntStr)) {
        return "N";
    }

    // 2) Detect if this training has NO assessment configured
    String maxMarksStr = dbActionTemplate.getSingleValue(
        "SELECT COALESCE(MAX(ETCA_MAXMARKS), 0) " +
        "FROM ENT_TL_TRGCALEMPATSCORE " +
        "WHERE ETCA_ETCM_KEYID = '" + key + "'"
    );
    CommonMessage.debugMsg(" maxMarksStr " + maxMarksStr);
    String cutOffStr = dbActionTemplate.getSingleValue(
        "SELECT COALESCE(MAX(ETCA_CUTOFF), 0) " +
        "FROM ENT_TL_TRGCALEMPATSCORE " +
        "WHERE ETCA_ETCM_KEYID = '" + key + "'"
    );
    CommonMessage.debugMsg(" cutOffStr " + cutOffStr);

    if (maxMarksStr == null || maxMarksStr.trim().isEmpty()) {
        maxMarksStr = "0";
    }
    if (cutOffStr == null || cutOffStr.trim().isEmpty()) {
        cutOffStr = "0";
    }

    boolean noAssessment =
        "0".equals(maxMarksStr) &&
        "0".equals(cutOffStr);
    CommonMessage.debugMsg(" noAssessment " + noAssessment);
    // 3) If NO assessment: only attendance matters
    if (noAssessment) {
        // Training is "completed" if every employee has at least one attendance row
        String missingAttendStr = dbActionTemplate.getSingleValue(
            "SELECT COUNT(*) " +
            "FROM ENT_TL_TRGCALEMP e " +
            "WHERE e.ETCE_ETCM_KEYID = '" + key + "' " +
            "AND NOT EXISTS ( " +
            "    SELECT 1 " +
            "    FROM ENT_TL_TRGCALEMPATSCORE a " +
            "    WHERE a.ETCA_ETCE_KEYID = e.ETCE_KEYID " +
            ")"
        );
        CommonMessage.debugMsg(" missingAttendStr " + missingAttendStr);
        
        if (missingAttendStr == null || missingAttendStr.trim().isEmpty()) {
            missingAttendStr = "0";
        }

        // If no employees are missing attendance, training is completed
        if ("0".equals(missingAttendStr)) {
            return "Y";
        } else {
            return "N";
        }
    }

    // 4) If assessment IS required: ensure all employees' assessments are completed
    //    (ETCA_ASSESSMENTCOM = 'Y' for each linked employee)
    String completedAssessCntStr = dbActionTemplate.getSingleValue(
        "SELECT COUNT(*) " +
        "FROM ENT_TL_TRGCALEMP e " +
        "WHERE e.ETCE_ETCM_KEYID = '" + key + "' " +
        "AND EXISTS ( " +
        "    SELECT 1 " +
        "    FROM ENT_TL_TRGCALEMPATSCORE a " +
        "    WHERE a.ETCA_ETCE_KEYID = e.ETCE_KEYID " +
        "      AND a.ETCA_ASSESSMENTCOM = 'Y' " +
        ")"
    );
    CommonMessage.debugMsg(" completedAssessCntStr " + completedAssessCntStr);
    
    if (completedAssessCntStr == null || completedAssessCntStr.trim().isEmpty()) {
        completedAssessCntStr = "0";
    }

    // If all employees have completed assessments, training is completed
    if (empCntStr.equals(completedAssessCntStr)) {
        return "Y";
    } else {
        return "N";
    }
}

//@Override
//public String IsTrainingCompleted(CommonFilter commonFilter)
//		throws Exception {
//	// TODO Auto-generated method stub
//	String result=null;
//    String emplinkcnt=dbActionTemplate.getSingleValue("select count(*) from ENT_TL_TRGCALEMP WHERE ETCE_ETCM_KEYID='"+commonFilter.getKey()+"'");
//	String atvalue=dbActionTemplate.getSingleValue("select count(*) from ENT_TL_TRGCALEMPATSCORE WHERE ETCA_ETCM_KEYID='"+commonFilter.getKey()+"'");
//	if(!emplinkcnt.equals("0"))
//	{
//	if(emplinkcnt.equals(atvalue))
//	{
//		result="Y";
//	}
//	else{
//		result="N";
//	}
//	return result;
//	}
//	else{
//		return "N";
//	}
//}

//------------------------Vignesh 28Nov2025 -------------------------------------------------------------//
@Override
public List<String[]> getElementId(String loginflid, String loginlevel,
		String loginElementid, String empId) throws Exception {
	// TODO Auto-generated method stub
    StringBuffer sql =new StringBuffer();
	sql.append("SELECT FNLN_ELEMENTID,FNLN_KEYID,ROLE_LEVEL,ROLE_NAME,ROLE_KEYID FROM  GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST  ");
		sql.append(" WHERE FNLN_KEYID = FRT_FNLN_KEYID  and FRT_ROLE_KEYID = ROLE_KEYID  ");
	if(UIUtils.isValidKeyId(loginflid))
			sql.append(" AND FRT_FNLN_KEYID  = '"+ loginflid +"' ");
		
		sql.append(" AND FRT_EMPM_KEYID = '"+ empId +"'  AND ROLE_LEVEL= '"+ loginlevel +"'");
		CommonMessage.debugMsg("Sql iis"+sql);
		Object [] args = {} ;
		List<String []> userDatas = dbActionTemplate.getDataList(sql.toString(),args);
		return userDatas;
}
@Override
public String getempdata(String calid) throws Exception {
	// TODO Auto-generated method stub
	
	String emplinkcnt=dbActionTemplate.getSingleValue("select count(*) from ENT_TL_TRGCALEMPATSCORE WHERE ETCA_ETCM_KEYID='"+calid+"'");
    return emplinkcnt;
   
}
@Override
public String FacultyCheck(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	String result=null;
	
	String facultycnt=dbActionTemplate.getSingleValue("select count(*) from ENT_TL_TRGFACULTY WHERE ETCF_ETCM_KEYID='"+commonFilter.getKey()+"' AND ETCF_FACULTYID='"+commonFilter.getEmpch()+"'");
    
	return facultycnt;
	
}

public String chkSessionDate(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	String fromtme=commonFilter.getDteAllotedfrm();
	CommonMessage.debugMsg("The fromtme"+fromtme);
	String totme=commonFilter.getDteAllotedto();
	CommonMessage.debugMsg("The totme"+totme);
	String frmhr=fromtme.substring(0,2);
	CommonMessage.debugMsg("The frmhr"+frmhr);
	String frommin=fromtme.substring(3, 5);
	String tohr=totme.substring(0,2);
	String tomin=totme.substring(3, 5);
	String fromdate=commonFilter.getDteOccuredto()+" "+frmhr+":"+frommin;
	CommonMessage.debugMsg("The fromtme"+fromdate);
	String todate=commonFilter.getDteOccuredto()+" "+tohr+":"+tomin;
	CommonMessage.debugMsg("The todate"+todate);
	String sql="select count(*) from ENT_TL_TRGCALSESSION " +
			"WHERE ETCS_ETCM_KEYID='"+commonFilter.getKey()+"' AND TO_CHAR (ETCS_SESSIONDATE, 'DD-Mon-YYYY')='"+commonFilter.getDteOccuredto()+"'" +
			"AND TO_CHAR (ETCS_FROMDATE, 'DD-Mon-YYYY HH24:MI')='"+fromdate+"' AND TO_CHAR (ETCS_TILLDATE, 'DD-Mon-YYYY HH24:MI')='"+todate+"'";
    CommonMessage.debugMsg("sql::"+sql);
	String sessioncnt=dbActionTemplate.getSingleValue(sql);
	return sessioncnt;
}
@Override
public List<EntTlTtgCalEmpatScore> createEmployeeAttendance(List<EntTlTtgCalEmpatScore> entTlEmployeeAttendanceLink, CommonFilter commonfilter) throws Exception {

	EntTlTtgCalEmpatScoreSql entTlTtgCalEmpatScoreSql = new EntTlTtgCalEmpatScoreSql();
	EntTlTragcalquadSql entTlTtgCalquadSql =new EntTlTragcalquadSql();
	// contains dbtable,field names, Field types and related sqls  of master table
	try
	{
		     List<String> sqls = new ArrayList<String>();
		     String dateTime=CommonFunctions.dateTimeNow();
		     List <EntTlTtgCalEmpatScore> Emplist = entTlEmployeeAttendanceLink;
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),EntTlTtgCalEmpatScoreSql.TBL_ENT_TL_TRGCALEMPATSCORE,15,"ETA", null,null);			 
		     EntTlTragcalquad existTlTragcalquad =new EntTlTragcalquad();
			 for(EntTlTtgCalEmpatScore empEmployeeLink:Emplist)
			 {     
				 CommonMessage.debugMsg("inside the daoImpl"+empEmployeeLink.getEtcaKeyid());
				 
				
				 if(empEmployeeLink.getEtcaKeyid()!=null)
				 {
					 String result=empEmployeeLink.getEtcaResult();
						String Fail="F";
					 sqls.add(EntTlTtgCalEmpatScoreSql.getUpdateSql(entTlTtgCalEmpatScoreSql.getFtymDbFields(),empEmployeeLink.getSaveArray()));
					 
					 String sqlqery="select ETCQ_KEYID from ENT_TL_TRGCALQUAD WHERE ETCQ_EMPM_KEYID='"+empEmployeeLink.getEtcaEtceEmpmKeyid()+"' AND ETCQ_TOPICID='"+commonfilter.getTopicid()+"' AND ETCQ_L1_TRGCALID='"+commonfilter.getKey()+"'";
					// CommonMessage.debugMsg("sql"+sqlqery);
					 String quadkeyid=dbActionTemplate.getSingleValue(sqlqery);
					 EntTlTragcalquad entTlTragcalquad=QuadrantFillvalues(existTlTragcalquad);
					 entTlTragcalquad.setEtcql2Pass(result);
					 if(entTlTragcalquad.getEtcql2Pass().equals("P"))
					 {
					 entTlTragcalquad.setEtcqCurrLevel("2");
					 }
					 else{
						 entTlTragcalquad.setEtcqCurrLevel("1"); 
					 }
					 entTlTragcalquad.setEtcqCurrlevelDate(empEmployeeLink.getEtcaAddDate());
					 entTlTragcalquad.setEtcql1Date(empEmployeeLink.getEtcaAddDate());
					 entTlTragcalquad.setEtcql2Date(empEmployeeLink.getEtcaAddDate());
					 entTlTragcalquad.setEtcql3Date(empEmployeeLink.getEtcaAddDate());
					 //entTlTragcalquad.setEtcql4Date(empEmployeeLink.getEtcaAddDate());
					 entTlTragcalquad.setEtcql4Date(dateTime);
					 entTlTragcalquad.setEtcql1Pass(result);
					 
					 entTlTragcalquad.setEtcql3Pass(Fail);
					 entTlTragcalquad.setEtcql3Pass(Fail);
					 
			// }
					 entTlTragcalquad.setEtcql1Remarks("{}");
					 entTlTragcalquad.setEtcql2Remarks("{}");
					// entTlTragcalquad.setEtcqEmpmRoleid(emprole);
					 entTlTragcalquad.setEtcqEmpmKeyid(empEmployeeLink.getEtcaEtceEmpmKeyid());
					 entTlTragcalquad.setEtcql2TrgCalId(commonfilter.getKey());
					 entTlTragcalquad.setEtcql1TrgCalId(commonfilter.getKey());
					 entTlTragcalquad.setEtcqFlid(commonfilter.getFlid());
					 entTlTragcalquad.setEtcqTopicid(commonfilter.getTopicid());
					 entTlTragcalquad.setEtcqCreatedby(commonfilter.getChkExternal());
					 entTlTragcalquad.setEtcqCreatedby(commonfilter.getChkExternal());
					 entTlTragcalquad.setEtcqLocation(commonfilter.getLossId());
					 entTlTragcalquad.setEtcqKeyid(quadkeyid);
					// entTlTragcalquad.setEtcqCreatedby(commonfilter.getChkExternal());
					 //entTlTragcalquad.setEtcqLocation(commonfilter.getLossId());
					 sqls.add(EntTlTragcalquadSql.getUpdateSql(entTlTtgCalquadSql.getFtymDbFields(),entTlTragcalquad.getSaveArray()));
						
				 }
				 
				 if(empEmployeeLink.getEtcaKeyid()==null)
				 {
					String seqNo=sequenceNumber.getSequnceNumber();
					empEmployeeLink.setEtcaKeyid(seqNo);
					empEmployeeLink.setEtcaEtcmKeyid(empEmployeeLink.getEtcaEtcmKeyid());
					String result=empEmployeeLink.getEtcaResult();
					String Fail="F";
					//if(empEmployeeLink.getEtcaKeyid()==null)
					//{	
					sqls.add(EntTlTtgCalEmpatScoreSql.getInsertSql(entTlTtgCalEmpatScoreSql.getFtymDbFields(),empEmployeeLink.getSaveArray()));
				//	}
					/*else{
						sqls.add(EntTlTtgCalEmpatScoreSql.getUpdateSql(entTlTtgCalEmpatScoreSql.getFtymDbFields(),empEmployeeLink.getSaveArray()));
							
					}*/
					 EntTlTragcalquad entTlTragcalquad=QuadrantFillvalues(existTlTragcalquad);
					 String query="select EMPM_ROLEID from GEN_TL_EMPLOYEEMST WHERE EMPM_KEYID='"+empEmployeeLink.getEtcaEtceEmpmKeyid()+"'";
					 String emprole=dbActionTemplate.getSingleValue(query);
					 entTlTragcalquad.setEtcql2Pass(result);
					// entTlTragcalquad.setEtcqCurrLevel("2");
					 
					 if(entTlTragcalquad.getEtcql2Pass().equals("P"))
					 {
					 entTlTragcalquad.setEtcqCurrLevel("2");
					 }
					 else{
						 entTlTragcalquad.setEtcqCurrLevel("1"); 
					 }
					 entTlTragcalquad.setEtcqCurrlevelDate(empEmployeeLink.getEtcaAddDate());
					 entTlTragcalquad.setEtcql1Date(empEmployeeLink.getEtcaAddDate());
					 entTlTragcalquad.setEtcql2Date(empEmployeeLink.getEtcaAddDate());
					 entTlTragcalquad.setEtcql3Date(empEmployeeLink.getEtcaAddDate());
					 //entTlTragcalquad.setEtcql4Date(empEmployeeLink.getEtcaAddDate());
					 entTlTragcalquad.setEtcql4Date(dateTime);
					 entTlTragcalquad.setEtcql1Pass(result);
					 
					 entTlTragcalquad.setEtcql3Pass(Fail);
					 entTlTragcalquad.setEtcql3Pass(Fail);
					 
			// }
					 entTlTragcalquad.setEtcql1Remarks("{}");
					 entTlTragcalquad.setEtcql2Remarks("{}");
					 entTlTragcalquad.setEtcqEmpmRoleid(emprole);
					 entTlTragcalquad.setEtcqEmpmKeyid(empEmployeeLink.getEtcaEtceEmpmKeyid());
					 entTlTragcalquad.setEtcql2TrgCalId(commonfilter.getKey());
					 entTlTragcalquad.setEtcql1TrgCalId(commonfilter.getKey());
					 entTlTragcalquad.setEtcqFlid(commonfilter.getFlid());
					 entTlTragcalquad.setEtcqTopicid(commonfilter.getTopicid());
					 entTlTragcalquad.setEtcqCreatedby(commonfilter.getChkExternal());
					 entTlTragcalquad.setEtcqCreatedby(commonfilter.getChkExternal());
					 entTlTragcalquad.setEtcqLocation(commonfilter.getLossId());
					 GenSequenceNumber sequenceNumber1 = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),EntTlTragcalquadSql.TBL_ENT_TL_TRGCALQUAD,15,"ETQ", null,null);			 
					 String seqNo1=sequenceNumber1.getSequnceNumber();
					 entTlTragcalquad.setEtcqKeyid(seqNo1);
					 sqls.add(EntTlTragcalquadSql.getInsertSql(entTlTtgCalquadSql.getFtymDbFields(),entTlTragcalquad.getSaveArray()));
						
					
					
				 }
				 
				
					}
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		CommonMessage.debugMsg("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return null;
}

	private EntTlTragcalquad QuadrantFillvalues(EntTlTragcalquad entTlTragcalquad) {
	// TODO Auto-generated method stub
		String dateTime=CommonFunctions.dateTimeNow();
		//entTlTragcalmst.setEtcmCreatedDateTime(dateTime);
		entTlTragcalquad.setEtcfActive("Y");
		entTlTragcalquad.setEtcqCreatedon(dateTime);
		entTlTragcalquad.setEtcqModifiedon(dateTime);
		
		if(entTlTragcalquad.getEtcqCurrLevel()==null)
		{ 
			entTlTragcalquad.setEtcqCurrLevel("0");
		}
		
		if(entTlTragcalquad.getEtcqCurrlevelDate()==null)
		{ 
			entTlTragcalquad.setEtcqCurrlevelDate(dateTime);
		}
		
		if(entTlTragcalquad.getEtcqEmpmKeyid()==null)
		{ 
			entTlTragcalquad.setEtcqEmpmKeyid("{}");
		}
		
		if(entTlTragcalquad.getEtcqDmt()==null)
		{ 
			entTlTragcalquad.setEtcqDmt("{}");
		}
		
		if(entTlTragcalquad.getEtcqEmpmRoleid()==null)
		{ 
			entTlTragcalquad.setEtcqEmpmRoleid("{}");
		}
		
		if(entTlTragcalquad.getEtcqTopicid()==null)
		{ 
			entTlTragcalquad.setEtcqTopicid("{}");
		}
		
		if(entTlTragcalquad.getEtcqFlid()==null)
		{ 
			entTlTragcalquad.setEtcqFlid("{}");
		}
		
		if(entTlTragcalquad.getEtcqJh()==null)
		{ 
			entTlTragcalquad.setEtcqJh("{}");
		}
		
		if(entTlTragcalquad.getEtcql1Date()==null)
		{ 
			entTlTragcalquad.setEtcql1Date(dateTime);
		}
		
		if(entTlTragcalquad.getEtcql2Date()==null)
		{ 
			entTlTragcalquad.setEtcql2Date(dateTime);
		}
		if(entTlTragcalquad.getEtcql3Date()==null)
		{ 
			entTlTragcalquad.setEtcql3Date(dateTime);
		}
		
		if(entTlTragcalquad.getEtcql4Date()==null)
		{ 
			entTlTragcalquad.setEtcql4Date(dateTime);
		}
		
		if(entTlTragcalquad.getEtcql1Pass()==null)
		{ 
			entTlTragcalquad.setEtcql1Pass("F");
		}
		
		if(entTlTragcalquad.getEtcql2Pass()==null)
		{ 
			entTlTragcalquad.setEtcql2Pass("F");
		}
		
		if(entTlTragcalquad.getEtcql3Pass()==null)
		{ 
			entTlTragcalquad.setEtcql3Pass("F");
		}
		
		if(entTlTragcalquad.getEtcql4Pass()==null)
		{ 
			entTlTragcalquad.setEtcql4Pass("F");
		}
		
		if(entTlTragcalquad.getEtcql1Remarks()==null)
		{ 
			entTlTragcalquad.setEtcql1Remarks("{}");
		}
		
		if(entTlTragcalquad.getEtcql2Remarks()==null)
		{ 
			entTlTragcalquad.setEtcql2Remarks("{}");
		}
		
		if(entTlTragcalquad.getEtcql3Remarks()==null)
		{ 
			entTlTragcalquad.setEtcql3Remarks("{}");
		}
		
		if(entTlTragcalquad.getEtcql4Remarks()==null)
		{ 
			entTlTragcalquad.setEtcql4Remarks("{}");
		}
		
		if(entTlTragcalquad.getEtcql1TrgCalId()==null)
		{ 
			entTlTragcalquad.setEtcql1TrgCalId("{}");
		}
		
		if(entTlTragcalquad.getEtcql2TrgCalId()==null)
		{ 
			entTlTragcalquad.setEtcql2TrgCalId("{}");
		}
		
		if(entTlTragcalquad.getEtcql3Udy()==null)
		{ 
			entTlTragcalquad.setEtcql3Udy("{}");
		}
		
		if(entTlTragcalquad.getEtcql4Udy()==null)
		{ 
			entTlTragcalquad.setEtcql4Udy("{}");
		}
		
		if(entTlTragcalquad.getEtcqLocation()==null)
		{ 
			entTlTragcalquad.setEtcqLocation("{}");
		}
		
		if(entTlTragcalquad.getEtcqTempfield1()==null)
		{ 
			entTlTragcalquad.setEtcqTempfield1("-");
		}
		
		if(entTlTragcalquad.getEtcqTempfield2()==null)
		{ 
			entTlTragcalquad.setEtcqTempfield2("-");
		}
		
		if(entTlTragcalquad.getEtcqTempfield3()==null)
		{ 
			entTlTragcalquad.setEtcqTempfield3("-");
		}
		
		if(entTlTragcalquad.getEtcqTempfield4()==null)
		{ 
			entTlTragcalquad.setEtcqTempfield4("-");
		}
		
		if(entTlTragcalquad.getEtcqTempfield5()==null)
		{ 
			entTlTragcalquad.setEtcqTempfield5("-");
		}
		
	return entTlTragcalquad;
}
	public EntTlTrgCalEmp EmployeebyCreate(EntTlTrgCalEmp newEntTlTrgCalEmp) throws Exception{
	List<String> sqls = new ArrayList<String>(); 
	CommonMessage.debugMsg("Inside the Insert");
	newEntTlTrgCalEmp.setEtceKeyid(dbActionTemplate.getSequenceNumber(EntTlTrgCalEmpSql.TBL_ENT_TL_TRGCALEMP,15,"ETE",null,null));
	sqls.add(EntTlTrgCalEmpSql.getInsertSql(entTlTrgCalEmpSql.getFtymDbFields(),newEntTlTrgCalEmp.getSaveArray()));
	dbActionTemplate.executeStatements(sqls);
    CommonMessage.debugMsg("After Save Data Query"+sqls);
    return newEntTlTrgCalEmp;
}

@Override
public String chkUniqueposition(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	
	String uniqcnt = null;
	//if(commonFilter.getCellch()=="true" || commonFilter.getCellch()=="false" )
	//{
		String sql="select count(*) from ENT_TL_TRGCALUNQP " +
			"WHERE ETCU_ETCM_KEYID='"+commonFilter.getKey()+"' AND ETCU_ROLE_KEYID='"+commonFilter.getUtil()+"'"; 
		CommonMessage.debugMsg("sql in unique position"+sql);
		uniqcnt=dbActionTemplate.getSingleValue(sql);
		return uniqcnt;
	//}
	//else{
	//	return "0";
	//}

	
}

	@Override
	public String chkEmployee(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
	
		
		String sql="select count(*) from ENT_TL_TRGCALEMP " +
				"WHERE ETCE_ETCM_KEYID='"+commonFilter.getKey()+"' AND ETCE_EMPM_KEYID='"+commonFilter.getUtil()+"' AND ETCE_ETCS_KEYID='"+commonFilter.getKK()+"'"; 

		String empcnt=dbActionTemplate.getSingleValue(sql);
		return empcnt;
	}
	
	@Override
	public List<String[]> chkJHforRole(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		String sql="SELECT sect_keyid,cell_keyid FROM GEN_VW_FNLN  WHERE fnln_keyid IN (select ROLE_FLID from GEN_TL_ROLEMST " +
				"WHERE ROLE_KEYID='"+commonFilter.getKey()+"' )"; 
         CommonMessage.debugMsg("sql in unique position"+sql);
		String roleflid=dbActionTemplate.getSingleValue(sql);
		if(roleflid!=null)
		{
			
			List<String[]> keyids=dbActionTemplate.getDataList(sql);
			return keyids;
		}
		return null;
	}

   // -- chnaging topic id to training id
	@Override
	public String deleteDetailRecord(String keyId, String gridId, String TrainingId) throws Exception,BusinessApplicationExceptions{
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("keyid inside delete" + keyId);
		try{
			String sqlsDelete ="";			
		if("Gengrid".equals(gridId))
		{
			sqlsDelete = "delete from "+TableNames.TBL_ENT_TL_TRGFACULTY+" where ETCF_KEYID = '"+keyId+"'";
				
		}else if("UniqueGrid".equals(gridId)){
			sqlsDelete= "delete from "+TableNames.TBL_ENT_TL_TRGCALUNQP+" where ETCU_KEYID='"+keyId+"' ";
		  }
		else{
			sqlsDelete = "delete from "+TableNames.TBL_ENT_TL_TRGCALSESSION+" where ETCS_KEYID = '"+keyId+"'" ;
		}
		  CommonMessage.debugMsg("The Sqls Delete:::"+sqlsDelete);
		  dbActionTemplate.executeStatement(sqlsDelete);
		  return "Data Deleted Successfully";
		}
		catch(BusinessApplicationExceptions e){
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
			return "Data Not Deleted";	
		}
	}
	@Override
	public List<EntTlTtgCalEmpatScore> UpdateEmployeeAttendance(
			List<EntTlTtgCalEmpatScore> fillValuesEmployeeAttendance)
			throws Exception {
		// TODO Auto-generated method stub
		EntTlTtgCalEmpatScoreSql entTlTtgCalEmpatScoreSql = new EntTlTtgCalEmpatScoreSql(); // contains dbtable,field names, Field types and related sqls  of master table
		try
		{
			     List<String> sqls = new ArrayList<String>();
			     List <EntTlTtgCalEmpatScore> Emplist = fillValuesEmployeeAttendance;				 
				 for(EntTlTtgCalEmpatScore empEmployeeLink:Emplist)
				 {
						sqls.add(EntTlTtgCalEmpatScoreSql.getUpdateSql(entTlTtgCalEmpatScoreSql.getFtymDbFields(),empEmployeeLink.getSaveArray()));
					}
				 dbActionTemplate.executeStatements(sqls);
		}
		catch(BusinessApplicationExceptions e)
		{
			CommonMessage.debugMsg("Business Application   :"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage()); 
		}
		return null;
	}
	@Override
	public String getempattn(String calid) throws Exception {
		// TODO Auto-generated method stub
		String sql="select COUNT(*) FROM ENT_TL_TRGCALEMPATSCORE WHERE ETCA_ETCM_KEYID='"+calid+"'";
		CommonMessage.debugMsg("sql"+sql);
		return dbActionTemplate.getSingleValue(sql);
	}
	@Override
	public String getCutoff(String calid) throws Exception {
		// TODO Auto-generated method stub
		String sql="select ETCA_CUTOFF FROM ENT_TL_TRGCALEMPATSCORE WHERE ETCA_KEYID = (SELECT MIN(ETCA_KEYID) from  ENT_TL_TRGCALEMPATSCORE where ETCA_ETCM_KEYID='"+calid+"')";
		CommonMessage.debugMsg("sql"+sql);
		return dbActionTemplate.getSingleValue(sql);
	}
	@Override
	public String getAssesType(String calid) throws Exception {
		// TODO Auto-generated method stub
		String sql="select ETCA_TYPE from ENT_TL_TRGCALEMPATSCORE where ETCA_ETCM_KEYID='"+calid+"'";
		CommonMessage.debugMsg("sql"+sql);
		return dbActionTemplate.getSingleValue(sql);
	}
	@Override
	public String getMaxmarks(String calid) throws Exception {
		// TODO Auto-generated method stub
		String sql="select ETCA_MAXMARKS FROM ENT_TL_TRGCALEMPATSCORE WHERE ETCA_KEYID = (SELECT MIN(ETCA_KEYID) from  ENT_TL_TRGCALEMPATSCORE where  ETCA_ETCM_KEYID='"+calid+"')";
		CommonMessage.debugMsg("sql"+sql);
		return dbActionTemplate.getSingleValue(sql);
	}
	//----------------------------VIGNESH 28NOV2025 -----------------------------------------------------//
	
	
	@Override
	public String chkAssesmentComplted(CommonFilter commonFilter) throws Exception {

	    String key = commonFilter.getKey();
	    if (key == null || key.trim().isEmpty()) {
	        CommonMessage.debugMsg("chkAssesmentComplted – no key, returning 0");
	        return "0";
	    }

	    // 1) Detect NO-ASSESSMENT training (attendance-only)
	    String maxMarksSql =
	        "SELECT COALESCE(MAX(ETCA_MAXMARKS), 0) " +
	        "FROM ENT_TL_TRGCALEMPATSCORE " +
	        "WHERE ETCA_ETCM_KEYID = '" + key + "'";

	    String cutOffSql =
	        "SELECT COALESCE(MAX(ETCA_CUTOFF), 0) " +
	        "FROM ENT_TL_TRGCALEMPATSCORE " +
	        "WHERE ETCA_ETCM_KEYID = '" + key + "'";

	    String maxMarksStr = dbActionTemplate.getSingleValue(maxMarksSql);
	    String cutOffStr   = dbActionTemplate.getSingleValue(cutOffSql);

	    if (maxMarksStr == null || maxMarksStr.trim().isEmpty()) maxMarksStr = "0";
	    if (cutOffStr == null || cutOffStr.trim().isEmpty())     cutOffStr   = "0";

	    boolean noAssessment = "0".equals(maxMarksStr) && "0".equals(cutOffStr);

	    // 2) Planned employees count
	    String empCntSql =
	        "SELECT COUNT(*) " +
	        "FROM ENT_TL_TRGCALEMP " +
	        "WHERE ETCE_ETCM_KEYID = '" + key + "'";

	    String empStr = dbActionTemplate.getSingleValue(empCntSql);
	    if (empStr == null || empStr.trim().isEmpty()) empStr = "0";

	    // 3) Completed employees count
	    String completedCntSql;
	    if (noAssessment) {
	        // Attendance-only: any attendance row -> completed
	        completedCntSql =
	            "SELECT COUNT(DISTINCT ETCA_ETCE_KEYID) " +
	            "FROM ENT_TL_TRGCALEMPATSCORE " +
	            "WHERE ETCA_ETCM_KEYID = '" + key + "'";
	    } else {
	        // Mark-based: require attendance + result (P/F)
	        completedCntSql =
	            "SELECT COUNT(DISTINCT ETCA_ETCE_KEYID) " +
	            "FROM ENT_TL_TRGCALEMPATSCORE " +
	            "WHERE ETCA_ETCM_KEYID = '" + key + "' " +
	            "  AND ETCA_PRSENTABSENT IS NOT NULL " +
	            "  AND ETCA_RESULT IN ('P','F')";
	    }

	    String completedStr = dbActionTemplate.getSingleValue(completedCntSql);
	    if (completedStr == null || completedStr.trim().isEmpty()) completedStr = "0";

	    CommonMessage.debugMsg(
	        "chkAssesmentComplted – key=" + key +
	        ", empCnt=" + empStr +
	        ", completedCnt=" + completedStr +
	        ", noAssessment=" + noAssessment
	    );

	    // If counts mismatch -> not fully completed
	    if (!completedStr.equals(empStr)) {
	        return "0";
	    }

	    // 4) All employees completed -> ONLY NOW update ETCA_ASSESSMENTCOM
	    //    NOTE: use executeStatement, NOT getSingleValue
	    String updateSql =
	        "UPDATE ENT_TL_TRGCALEMPATSCORE " +
	        "SET ETCA_ASSESSMENTCOM = 'Y' " +
	        "WHERE ETCA_ETCM_KEYID = '" + key + "'";

	    dbActionTemplate.executeStatement(updateSql);
	    CommonMessage.debugMsg("chkAssesmentComplted – updated ETCA_ASSESSMENTCOM = 'Y' for key=" + key);

	    return "1";
	}

//	@Override
//	public String chkAssesmentComplted(CommonFilter commonFilter) throws Exception {
//
//	    String key = commonFilter.getKey();
//	    if (key == null || key.trim().isEmpty()) {
//	        // No key -> be safe and say not completed
//	        return "0";
//	    }
//
//	    // ------------------------------------------------------------
//	    // 1) Detect "NO ASSESSMENT" (attendance-only program)
//	    //    Based on your existing rule: MAXMARKS = 0 and CUTOFF = 0
//	    // ------------------------------------------------------------
//	    String maxMarksSql =
//	        "SELECT COALESCE(MAX(ETCA_MAXMARKS), 0) " +
//	        "FROM ENT_TL_TRGCALEMPATSCORE " +
//	        "WHERE ETCA_ETCM_KEYID = '" + key + "'";
//
//	    String cutOffSql =
//	        "SELECT COALESCE(MAX(ETCA_CUTOFF), 0) " +
//	        "FROM ENT_TL_TRGCALEMPATSCORE " +
//	        "WHERE ETCA_ETCM_KEYID = '" + key + "'";
//
//	    String maxMarksStr = dbActionTemplate.getSingleValue(maxMarksSql);
//	    String cutOffStr   = dbActionTemplate.getSingleValue(cutOffSql);
//
//	    if (maxMarksStr == null || maxMarksStr.trim().isEmpty()) {
//	        maxMarksStr = "0";
//	    }
//	    if (cutOffStr == null || cutOffStr.trim().isEmpty()) {
//	        cutOffStr = "0";
//	    }
//
//	    boolean noAssessment = "0".equals(maxMarksStr) && "0".equals(cutOffStr);
//
//	    // ------------------------------------------------------------
//	    // 2) Count planned employees
//	    // ------------------------------------------------------------
//	    String empCntSql =
//	        "SELECT COUNT(*) " +
//	        "FROM ENT_TL_TRGCALEMP " +
//	        "WHERE ETCE_ETCM_KEYID = '" + key + "'";
//
//	    String empStr = dbActionTemplate.getSingleValue(empCntSql);
//	    if (empStr == null || empStr.trim().isEmpty()) {
//	        empStr = "0";
//	    }
//
//	    // ------------------------------------------------------------
//	    // 3) Count completed employees
//	    //    - For NO-ASSESSMENT trainings: any ETCA row counts
//	    //    - For MARK-BASED trainings: require attendance + result
//	    // ------------------------------------------------------------
//	    String completedCntSql;
//
//	    if (noAssessment) {
//	        // Attendance-only: one ETCA row per employee is enough
//	        completedCntSql =
//	            "SELECT COUNT(*) " +
//	            "FROM ENT_TL_TRGCALEMPATSCORE " +
//	            "WHERE ETCA_ETCM_KEYID = '" + key + "'";
//	    } else {
//	        // Mark-based: an employee is "completed" when result is present
//	        completedCntSql =
//	            "SELECT COUNT(*) " +
//	            "FROM ENT_TL_TRGCALEMPATSCORE " +
//	            "WHERE ETCA_ETCM_KEYID = '" + key + "' " +
//	            "  AND ETCA_PRSENTABSENT IS NOT NULL " +
//	            "  AND ETCA_RESULT IN ('P','F')";
//	            // if you prefer score-based:
//	            // "  AND COALESCE(ETCA_SCORE, '') <> ''"
//	    }
//
//	    String completedStr = dbActionTemplate.getSingleValue(completedCntSql);
//	    if (completedStr == null || completedStr.trim().isEmpty()) {
//	        completedStr = "0";
//	    }
//
//	    // ------------------------------------------------------------
//	    // 4) Compare counts and optionally update ETCA_ASSESSMENTCOM
//	    // ------------------------------------------------------------
//	    if ("modify".equalsIgnoreCase(commonFilter.getMaintMode())) {
//	        // In MODIFY mode: just validate, don’t update anything
//	        return completedStr.equals(empStr) ? "1" : "0";
//	    } else {
//	        // In normal mode: if all employees completed, update flag and return "1"
//	        if (completedStr.equals(empStr)) {
//	            String updateSql =
//	                "UPDATE ENT_TL_TRGCALEMPATSCORE " +
//	                "SET ETCA_ASSESSMENTCOM = 'Y' " +
//	                "WHERE ETCA_ETCM_KEYID = '" + key + "'";
//	            dbActionTemplate.getSingleValue(updateSql); // same style as your existing code
//	            return "1";
//	        }
//
//	    	    
//	        else {
//	            return "0";
//	        }
//	    }
//	}
 
	
	 
	
//	@Override
//	public String chkAssesmentComplted(CommonFilter commonFilter) throws Exception {
//
//	    String key = commonFilter.getKey();
//	    if (key == null || key.trim().isEmpty()) {
//	        // No key -> be safe and say not completed
//	        return "0";
//	    }
//
//	    // 1) Detect "NO ASSESSMENT" using MAXMARKS / CUTOFF = 0
//	    //    (this matches your current data: attendance-only rows)
//	    String maxMarksSql =
//	        "SELECT COALESCE(MAX(ETCA_MAXMARKS), 0) " +
//	        "FROM ENT_TL_TRGCALEMPATSCORE " +
//	        "WHERE ETCA_ETCM_KEYID = '" + key + "'";
//
//	    String cutOffSql =
//	        "SELECT COALESCE(MAX(ETCA_CUTOFF), 0) " +
//	        "FROM ENT_TL_TRGCALEMPATSCORE " +
//	        "WHERE ETCA_ETCM_KEYID = '" + key + "'";
//
//	    String maxMarksStr = dbActionTemplate.getSingleValue(maxMarksSql);
//	    String cutOffStr   = dbActionTemplate.getSingleValue(cutOffSql);
//
//	    if (maxMarksStr == null || maxMarksStr.trim().isEmpty()) {
//	        maxMarksStr = "0";
//	    }
//	    if (cutOffStr == null || cutOffStr.trim().isEmpty()) {
//	        cutOffStr = "0";
//	    }
//
//	    boolean noAssessment = "0".equals(maxMarksStr) && "0".equals(cutOffStr);
//
//	    // If training has NO assessment configured → always allow completion
//	    // (this fixes your current case ETC000000022871)
//	    if (noAssessment) {
//	        return "1";
//	    }
//
//	    // 2) For trainings WITH assessment (maxmarks/cutoff > 0),
//	    //    use your original logic comparing employee vs completed count.
//
//	    String completedCntSql =
//	        "SELECT COUNT(*) " +
//	        "FROM ENT_TL_TRGCALEMPATSCORE " +
//	        "WHERE ETCA_ASSESSMENTCOM = 'Y' " +
//	        "AND ETCA_ETCM_KEYID = '" + key + "'";
//
//	    String empCntSql =
//	        "SELECT COUNT(*) " +
//	        "FROM ENT_TL_TRGCALEMP " +
//	        "WHERE ETCE_ETCM_KEYID = '" + key + "'";
//
//	    String completedStr = dbActionTemplate.getSingleValue(completedCntSql);
//	    String empStr       = dbActionTemplate.getSingleValue(empCntSql);
//
//	    if (completedStr == null || completedStr.trim().isEmpty()) {
//	        completedStr = "0";
//	    }
//	    if (empStr == null || empStr.trim().isEmpty()) {
//	        empStr = "0";
//	    }
//
//	    // 3) For MODIFY mode: just validate, don't update flags (same as your old code)
//	    if ("modify".equalsIgnoreCase(commonFilter.getMaintMode())) {
//
//	        if (completedStr.equals(empStr)) {
//	            return "1";
//	        } else {
//	            return "0";
//	        }
//
//	    } else {
//	        // 4) For non-modify (create / first complete): if all employees are completed,
//	        //    mark ETCA_ASSESSMENTCOM = 'Y' for this program and return "1"
//	        if (completedStr.equals(empStr)) {
//	            String updateSql =
//	                "UPDATE ENT_TL_TRGCALEMPATSCORE " +
//	                "SET ETCA_ASSESSMENTCOM = 'Y' " +
//	                "WHERE ETCA_ETCM_KEYID = '" + key + "'";
//	            dbActionTemplate.getSingleValue(updateSql); // you used this style earlier
//	            return "1";
//	        } else {
//	            return "0";
//	        }
//	    }
//	}

//	@Override
//	public String chkAssesmentComplted(CommonFilter commonFilter)
//			throws Exception {
//		// TODO Auto-generated method stub
//		if(commonFilter.getMaintMode().equals("modify"))
//		{
//			String sql1="select COUNT(*) FROM ENT_TL_TRGCALEMPATSCORE where ETCA_ASSESSMENTCOM='Y' and ETCA_ETCM_KEYID='"+commonFilter.getKey()+"'";
//			String quad=dbActionTemplate.getSingleValue(sql1);
//			//CommonMessage.debugMsg("sql"+sql1);
//			//CommonMessage.debugMsg("quad"+quad);
//			String sql2="SELECT COUNT(*) FROM ENT_TL_TRGCALEMP where ETCE_ETCM_KEYID='"+commonFilter.getKey()+"'";
//			String emp=dbActionTemplate.getSingleValue(sql2);
//			//CommonMessage.debugMsg("sql"+sql2);
//			//CommonMessage.debugMsg("emp"+emp);
//			if(quad.equals(emp))
//			{
//				//String sql3="UPDATE ENT_TL_TRgcalempatscore  SET ETCA_ASSESSMENTCOM='Y' where  ETCA_ETCM_KEYID='"+commonFilter.getKey()+"'";
//			   // dbActionTemplate.getSingleValue(sql3);
//				return "1";
//			}
//			else{
//				return "0";
//			}
//		
//		}
//		else{
//			
//			String sql1="select COUNT(*) FROM ENT_TL_TRGCALEMPATSCORE where  ETCA_ETCM_KEYID='"+commonFilter.getKey()+"'";
//			String quad=dbActionTemplate.getSingleValue(sql1);
//			//CommonMessage.debugMsg("sql"+sql1);
//			//CommonMessage.debugMsg("quad"+quad);
//			String sql2="SELECT COUNT(*) FROM ENT_TL_TRGCALEMP where ETCE_ETCM_KEYID='"+commonFilter.getKey()+"'";
//			String emp=dbActionTemplate.getSingleValue(sql2);
//			//CommonMessage.debugMsg("sql"+sql2);
//			//CommonMessage.debugMsg("emp"+emp);
//			if(quad.equals(emp))
//			{
//				String sql3="UPDATE ENT_TL_TRGCALEMPATSCORE  SET ETCA_ASSESSMENTCOM='Y' where  ETCA_ETCM_KEYID='"+commonFilter.getKey()+"'";
//			    dbActionTemplate.getSingleValue(sql3);
//				return "1";
//			}
//			else{
//				return "0";
//			}
//		}
//		
//	}
	


	//----------------------------VIGNESH 28NOV2025 -----------------------------------------------------//
	public Workbook getAllEmployeeExcel(JSONObject colmodel, String rptformat,
			CommonFilter commonFilter) throws Exception {
		  ResultSet rs = null;
		// TODO Auto-generated method stub
		 try{
				
			 rs =   getEmployeeResultSet(commonFilter);
			 
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
				return excelUtils.writeToExcel(rs,rptformat, 1,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}
	private ResultSet getEmployeeResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_TRNATTENDENCEENEW", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String  condParam= FilterCondSql.getETRelatedStr(commonFilter);
		String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
		//condParam += "CALENID="+commonFilter.getProgram()+";";
		condParam += "TRAININGID="+commonFilter.getKey()+";";
		paramValues.add(condParam);
		paramValues.add(commonParam);
		return paramValues;
}

public Workbook getTrainingCalendarListExcel(JSONObject colmodel, String rptformat,
		CommonFilter commonFilter) throws Exception {
	  ResultSet rs = null;
	// TODO Auto-generated method stub
	 try{
			
		 rs =   getTrainingCalenderResultSet(commonFilter);
		 
		 ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptformat,2,1,0 ); //elumalai
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
}

public Workbook getEmpWiseTrainingCalendarListExcel(JSONObject colmodel, String rptformat,
		CommonFilter commonFilter) throws Exception {
	  ResultSet rs = null;
	// TODO Auto-generated method stub
	 try{
			
		 rs =   getEmpWiseTrainingCalenderResultSet(commonFilter);
		 
		 ExcelUtils excelUtils = new ExcelUtils(colmodel);
		 
			return excelUtils.writeToExcel(rs,rptformat,3,1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
}

private ResultSet getEmpWiseTrainingCalenderResultSet(CommonFilter commonFilter) throws Exception {
	List<String> paramValues = getFilterParamValues1(commonFilter);
	
	CommonMessage.debugMsg("IN Get Excel"+paramValues);
	return dbActionTemplate.NewdbFunctionCall2("ENT_TL_EMPLOYEEWISEGRD_SB", paramValues);
}

/*private List<String> getFilterParamValues2(CommonFilter commonFilter) {
	List <String>  paramvalues = new ArrayList<String>();
	String condParms = FilterCondSql.getETRelatedStr(commonFilter);
	CommonMessage.debugMsg("Con Parms"+condParms);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
	paramvalues.add(condParms);
	paramvalues.add(commonParams);
	return paramvalues;
}*/
//- vignesh 04dec2025 ------------------------//

//private ResultSet getTrainingCalenderResultSet(CommonFilter commonFilter) throws Exception {
//	List<String> paramValues = getFilterParamValues1(commonFilter);
//	return dbActionTemplate.NewdbFunctionCall2("ENT_TL_NEWTRAININGCALMSTGRD", paramValues);
//}

private ResultSet getTrainingCalenderResultSet(CommonFilter commonFilter) throws Exception {
    String condParms    = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

    // *** ADD THIS BLOCK, same as grid ***
    String mode = commonFilter.getType();
    if (UIUtils.isValidKeyId(mode)) {
        condParms += "Mode=" + mode + ";";
    }

    List<String> paramValues = new ArrayList<>();
    paramValues.add(condParms);
    paramValues.add(commonParams);

    CommonMessage.debugMsg("Excel condParms   = " + condParms);
    CommonMessage.debugMsg("Excel commonParams= " + commonParams);

    return dbActionTemplate.NewdbFunctionCall2("ENT_TL_NEWTRAININGCALMSTGRD", paramValues);
}

//private ResultSet getTrainingCalenderResultSet(CommonFilter commonFilter) throws Exception {
//    String condParms    = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
//    String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // includes GRIDFILTER, FROMTOROW, etc.
//
//    List<String> paramValues = new ArrayList<>();
//    paramValues.add(condParms);
//    paramValues.add(commonParams);
//
//    CommonMessage.debugMsg("Excel condParms   = " + condParms);
//    CommonMessage.debugMsg("Excel commonParams= " + commonParams);
//
//    return dbActionTemplate.NewdbFunctionCall2("ENT_TL_NEWTRAININGCALMSTGRD", paramValues);
//}

 //- vignesh 04dec2025 ------------------------//
private List<String> getFilterParamValues1(CommonFilter commonFilter) {
	List <String>  paramvalues = new ArrayList<String>();
	String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
	paramvalues.add(condParms);
	paramvalues.add(commonParams);
	return paramvalues;
}
@Override
public String getTrainingMode(String topicid) throws Exception {
	// TODO Auto-generated method stub
	String trainingmode=dbActionTemplate.getSingleValue("SELECT TOPI_TRAININGMODE FROM ENT_TL_TOPICMST WHERE TOPI_KEYID='"+topicid+"' ");
    CommonMessage.debugMsg("trainingmode"+trainingmode);	
	return trainingmode;
}
@Override
public String getTrainingTopicType(String topicid) throws Exception {
	// TODO Auto-generated method stub
	String trainingtype=dbActionTemplate.getSingleValue("SELECT TOPI_RELATEDTO FROM ENT_TL_TOPICMST WHERE TOPI_KEYID='"+topicid+"' ");
    CommonMessage.debugMsg("trainingmode"+trainingtype);	
	return trainingtype;
}

public EntTlTrgCalEmp EmployeeDelete(EntTlTrgCalEmp newEntTlTrgCalEmp) throws Exception{
	List<String> sqls = new ArrayList<String>();
	EntTlTrgCalEmpSql entTlTrgCalEmpSql=new EntTlTrgCalEmpSql();
	try{
			sqls.add(entTlTrgCalEmpSql.getDeleteSql(entTlTrgCalEmpSql.getFtymDbFields(),newEntTlTrgCalEmp.getSaveArray()));
        dbActionTemplate.executeStatements(sqls);            
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return newEntTlTrgCalEmp;
}

// -------- Vignesh --- Using new for getting Multiple grid -------- 11Dec2025 -----------------//
public List<String[]> gwtJHRoleUniquePos(CommonFilter commonFilter) throws Exception {

    String calendarFlid = commonFilter.getFlid();
    CommonMessage.debugMsg("The Calendarflid " + calendarFlid);

    String calendarId = commonFilter.getKey();
    CommonMessage.debugMsg("The CalendarId " + calendarId);

    StringBuilder sql = new StringBuilder();

    try {
        sql.append("SELECT '', ")
           .append("       u.ETCU_KEYID, ")
           .append("       r.ROLE_KEYID, ")
           .append("       r.ROLE_NAME || '-' || r.FNLN_DISPLAYCODE AS uniqposition, ")
           .append("       f.SECT_KEYID, ")
           .append("       f.CELL_KEYID ")
           .append("FROM ENT_VW_ROLEMST r ")
           .append("LEFT JOIN GEN_VW_FNLN f ")
           .append("       ON f.FNLN_KEYID = r.FLID ")
           .append("LEFT JOIN ENT_TL_TRGCALUNQP u ")
           .append("       ON r.ROLE_KEYID = u.ETCU_ROLE_KEYID ")
           .append("      AND u.ETCU_ETCM_KEYID = '").append(calendarId).append("' ")
           .append("WHERE 1 = 1 ")
           .append("  AND r.ROLE_FLID = r.FLID ");

        // same hierarchy filter as combo: POSITION('FNL...' IN (PARENTFLIDS || FLID))
        if (UIUtils.isValidKeyId(calendarFlid)) {
            sql.append("  AND POSITION('")
               .append(calendarFlid)
               .append("' IN (r.PARENTFLIDS || r.FLID)) > 0 ");
        }

        // jqGrid extra filters, if any
        String codSql = FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter());
        CommonMessage.debugMsg("sqls of codSql " + codSql);
        if (codSql != null && !codSql.trim().isEmpty()) {
            sql.append(' ').append(codSql);
        }

        // ensure selected ones (with ETCU_KEYID) come first, but show ALL roles
        sql.append(" ORDER BY u.ETCU_KEYID NULLS FIRST, r.ROLE_NAME");

        CommonMessage.debugMsg("sqls of doc " + sql.toString());
    } catch (Exception e) {
        e.printStackTrace();
    }

    return dbActionTemplate.getDataList(sql.toString());
}

//public List<String[]> gwtJHRoleUniquePos(CommonFilter commonFilter) throws Exception{
//    String Calendarflid=commonFilter.getFlid();
//    CommonMessage.debugMsg("The Calendarflid"+Calendarflid);
//    String CalendarId=commonFilter.getKey();
//    /*List <String[]> grid = 
//    		
//    		commonFilter.getGridFilter() ;*/
//    GridFilter gridFilter = new GridFilter();
//    //String codSql=FilterCOncommonFilter.getGridFilter();//gridFilter.getData();// getData();
//    
//	String sql=null;
//	try{		
//		//-------- Vignesh ---//
//		
//	//	sql="SELECT '',ETCU_KEYID,ROLE_KEYID,ROLE_NAME||'-' || FNLN_DISPLAYCODE uniqposition ,sect_keyid,cell_keyid ";
//	//	sql+=" FROM GEN_TL_ROLEMST,GEN_MV_FLIDHIERARCHY,ENT_TL_TRGCALUNQP,GEN_VW_FNLN WHERE ROLE_FLID=FLID AND ROLE_KEYID=ETCU_ROLE_KEYID(+) and  fnln_keyid=flid ";
//	//	sql+=" AND ETCU_ETCM_KEYID(+)='"+CalendarId+"' AND INSTR( PARENTFLIDS||FLID ,'"+Calendarflid+"')>0 ";
//		sql  = "SELECT '',"
//			     + "       ETCU_KEYID,"
//			     + "       ROLE_KEYID,"
//			     + "       ROLE_NAME || '-' || FNLN_DISPLAYCODE uniqposition,"
//			     + "       sect_keyid,"
//			     + "       cell_keyid ";
//			sql += "  FROM GEN_TL_ROLEMST "
//			     + "  JOIN GEN_MV_FLIDHIERARCHY "
//			     + "    ON ROLE_FLID = FLID "
//			     + "  JOIN GEN_VW_FNLN "
//			     + "    ON fnln_keyid = flid "
//			     + "  LEFT JOIN ENT_TL_TRGCALUNQP "
//			     + "    ON ROLE_KEYID = ETCU_ROLE_KEYID "
//			     + "   AND ETCU_ETCM_KEYID = '" + CalendarId + "' ";
//			sql += " WHERE POSITION('" + Calendarflid + "' IN (PARENTFLIDS || FLID)) > 0 ";
//
//			// --------------------- Vignesh 27Nov2025 -----------------------------------------------//
//		
//		String codSql=FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter());
//		CommonMessage.debugMsg("sqls of codSql"+codSql);
//		if(codSql!=null||codSql!=" "){
//			sql += ""+codSql+"";
//			CommonMessage.debugMsg(" Query inside the DAOimpl intrg CAlendar "+sql);
//		}
//		sql+=" ORDER BY ETCU_KEYID";
//		CommonMessage.debugMsg("sqls of doc"+sql);
//		
//	}catch(Exception e){
//			e.printStackTrace();
//		}
//	
//		return dbActionTemplate.getDataList(sql);	
//}



//-------- Vignesh --- Using new for getting Multiple grid -------- 11Dec2025 -----------------//
public String getJHFlid(String Flid)throws Exception{
	 String sql="SELECT FNLN_ORIGINALID FROM GEN_MV_FLIDHIERARCHY WHERE FLID='"+Flid+"' ";
     CommonMessage.debugMsg("Sql:"+sql);
     return dbActionTemplate.getSingleValue(sql);
}

public List<EntTlTrgCalUnqp> CreateMultipleUnique(List<EntTlTrgCalUnqp> entTlTrgCalUnqpLink) throws Exception{
	EntTlTrgCalUnqpSql entTlTrgCalUnqpSql=new EntTlTrgCalUnqpSql();
	
	try
	{
		     List<String> sqls = new ArrayList<String>();
		     List <EntTlTrgCalUnqp> methodslist = entTlTrgCalUnqpLink;
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),EntTlTrgCalUnqpSql.TBL_ENT_TL_TRGCALUNQP,15,"ETU", null,null);
			 CommonMessage.debugMsg("sequenceNumber in IF"+sequenceNumber);
			 for(EntTlTrgCalUnqp trgcalUnqPositionLink:methodslist){
					String seqNo=sequenceNumber.getSequnceNumber();
					CommonMessage.debugMsg("The seqNo::::"+seqNo);
					trgcalUnqPositionLink.setEtcuKeyid(seqNo);
					trgcalUnqPositionLink.setEtcuEtcmKeyid(trgcalUnqPositionLink.getEtcuEtcmKeyid());
				//	trgcalUnqPositionLink.setEtcuRoleDmt(trgcalUnqPositionLink.getEtcuRoleDmt());
					sqls.add(EntTlTrgCalUnqpSql.getInsertSql(entTlTrgCalUnqpSql.getFtymDbFields(),trgcalUnqPositionLink.getSaveArray()));
			        CommonMessage.debugMsg("The sqls Query:"+sqls);
			 }
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		CommonMessage.debugMsg("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return null;
}


}